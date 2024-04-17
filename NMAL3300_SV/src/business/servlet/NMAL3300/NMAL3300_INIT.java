/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3300;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NMAL3300.constant.NMAL3300Constant.*;

import business.blap.NMAL3300.NMAL3300CMsg;
import business.servlet.NMAL3300.common.NMAL3300CommonLogic;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         N.Sugiura       Create          N/A
 * 2018/07/10   Fujitsu         T.Noguchi       Update          QC#26661
 * 2018/11/12   Fujitsu         Hd.Sugawara     Update          QC#28683
 *</pre>
 */
public class NMAL3300_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;
        scrnMsg.clear();

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;

            // [0] : Global Company Code
            ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd_P, (EZDBStringItem) params[PARAM_INDEX_0]);

            // [1] : Function ID
            ZYPEZDItemValueSetter.setValue(scrnMsg.funcMstrId_P, (EZDBStringItem) params[PARAM_INDEX_1]);

            // [2] : Function Category ID
            ZYPEZDItemValueSetter.setValue(scrnMsg.funcMstrIdDescTxt_P, (EZDBStringItem) params[PARAM_INDEX_2]);

            // [3] : Transaction Type
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsTrxRuleTpCd_P, (EZDBStringItem) params[PARAM_INDEX_3]);

            // [4] : Business Area
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsBizAreaCd_P, (EZDBStringItem) params[PARAM_INDEX_4]);

            // [5] : Customer Special Instruction Line Suffix
            String suffix = null;
            if (params[PARAM_INDEX_5] instanceof String) {
                suffix = (String) params[PARAM_INDEX_5];
            }
            if (suffix == null) {
                suffix = "";
            }

            // [6] : Customer Special Instruction Line
            EZDMsg.copy((EZDMsgArray) params[PARAM_INDEX_6], suffix, scrnMsg.P, NMAL3300Bean.P);

            // 2018/07/10 QC#26661 Add Start
            // [7] : Line of Business Code
            if (params.length > PARAM_INDEX_7) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.lineBizTpCd_P, (EZDBStringItem) params[PARAM_INDEX_7]);
            }
            // 2018/07/10 QC#26661 Add End
        }

        int idx = 0;
        for (int i = 0; i < scrnMsg.P.length(); i++) {

            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).dsAcctNum_P)
                    || ZYPCommonFunc.hasValue(scrnMsg.P.no(i).billToCustCd_P)
                    || ZYPCommonFunc.hasValue(scrnMsg.P.no(i).shipToCustCd_P)) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(idx).dsAcctNum_S1, scrnMsg.P.no(i).dsAcctNum_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(idx).billToCustCd_S1, scrnMsg.P.no(i).billToCustCd_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(idx).shipToCustCd_S1, scrnMsg.P.no(i).shipToCustCd_P);
                idx++;

                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).dsAcctNum_P)
                        && !ZYPCommonFunc.hasValue(scrnMsg.P.no(i).billToCustCd_P)
                        && !ZYPCommonFunc.hasValue(scrnMsg.P.no(i).shipToCustCd_P)) {
                    continue;
                } else if (!ZYPCommonFunc.hasValue(scrnMsg.P.no(i).dsAcctNum_P)
                        && ZYPCommonFunc.hasValue(scrnMsg.P.no(i).billToCustCd_P)
                        && !ZYPCommonFunc.hasValue(scrnMsg.P.no(i).shipToCustCd_P)) {
                    continue;
                } else if (!ZYPCommonFunc.hasValue(scrnMsg.P.no(i).dsAcctNum_P)
                        && !ZYPCommonFunc.hasValue(scrnMsg.P.no(i).billToCustCd_P)
                        && ZYPCommonFunc.hasValue(scrnMsg.P.no(i).shipToCustCd_P)) {
                    continue;
                } else {
                    scrnMsg.setMessageInfo(NMZM0025E);
                    scrnMsg.putErrorScreen();
                    return;
                }
            }

        }
        if (idx == 0) {
            scrnMsg.setMessageInfo(NMZM0025E);
            scrnMsg.putErrorScreen();
        }
        // Set Search Parameter
        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd_S, scrnMsg.glblCmpyCd_P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.funcMstrId_S, scrnMsg.funcMstrId_P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.funcMstrIdDescTxt_S, scrnMsg.funcMstrIdDescTxt_P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsTrxRuleTpCd_S, scrnMsg.dsTrxRuleTpCd_P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsBizAreaCd_S, scrnMsg.dsBizAreaCd_P);
        // 2018/07/10 QC#26661 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.lineBizTpCd_S, scrnMsg.lineBizTpCd_P);
        // 2018/07/10 QC#26661 Add End
        scrnMsg.S.setValidCount(idx);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;
        NMAL3300CMsg bizMsg = new NMAL3300CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CODE_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;
        NMAL3300CMsg bizMsg = (NMAL3300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Del Start 2018/11/12 QC#28683
        //scrnMsg.xxDplyTab_A.setValue(DPLY_TAB_EXPAND);
        //scrnMsg.xxDplyTab_B.setValue(DPLY_TAB_EXPAND);
        //scrnMsg.xxDplyTab_C.setValue(DPLY_TAB_EXPAND);
        //scrnMsg.xxDplyTab_D.setValue(DPLY_TAB_EXPAND);

        //scrnMsg.xxDplyTab_E.setValue(SCROLL_DPLY_HIDDEN);
        // Del End 2018/11/12 QC#28683

        NMAL3300CommonLogic.initCommonButton(this);
        NMAL3300CommonLogic.initButton(this);
        NMAL3300CommonLogic.protectScrn(this, scrnMsg);
        NMAL3300CommonLogic.setBgColor(scrnMsg);
        // Mod Start 2018/11/12 QC#28683
        //NMAL3300CommonLogic.clearFilter(scrnMsg);
        //S21SortColumnIMGController.clearIMG(SCREEN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(SCREEN_ID_00, scrnMsg, scrnMsg.D.no(0).getBaseContents());
        // Mod End 2018/11/12 QC#28683

        scrnMsg.putErrorScreen();

    }
}
