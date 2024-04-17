/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1900;

import static business.servlet.NWAL1900.constant.NWAL1900Constant.BIZ_ID;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.FUNCTION_CODE_SEARCH;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.MANUAL_ADJUSTMENT;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.PARAM_INDEX_0;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.PARAM_INDEX_1;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.PARAM_INDEX_2;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.PARAM_INDEX_3;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.PARAM_INDEX_4;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.PARAM_INDEX_5;
import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1900.NWAL1900CMsg;
import business.servlet.NWAL1900.common.NWAL1900CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ADJ_LVL;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/13   CUSA            H.Kumagai       Create          N/A
 * 2018/11/27   Fujitsu         M.Ishii         Update          QC#29361
 *</pre>
 */
public class NWAL1900_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1900BMsg scrnMsg = (NWAL1900BMsg) bMsg;
        NWAL1900CMsg bizMsg = new NWAL1900CMsg();
        scrnMsg.clear();
        Object[] params = (Object[]) getArgForSubScreen();
        if (params instanceof Object[]) {

            // [0] : xxModeCd_PM
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, (String) params[PARAM_INDEX_0]);
            if (!ZYPCommonFunc.hasValue(scrnMsg.xxModeCd)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, PRC_RULE_ADJ_LVL.LINE);
            }

            // [1] : LINE_BIZ_TP_CD
            ZYPEZDItemValueSetter.setValue(scrnMsg.lineBizTpCd, (String) params[PARAM_INDEX_1]);

            // [2] : PRC_RULE_MOD_TP
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleModTpCd, (String) params[PARAM_INDEX_2]);

            // [3] : CALC_BASE
            EZDBMsgArray prcList = (EZDBMsgArray) params[PARAM_INDEX_3];
            EZDMsg.copy(prcList, null, scrnMsg.L, null);
            // 2018/11/27 QC#29361 Add Start
            // [4] : DS_ORD_CATG_CD
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgCd, (String) params[PARAM_INDEX_4]);

            // [5] : DS_ORD_TP_CD
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdTpCd, (String) params[PARAM_INDEX_5]);
            // 2018/11/27 QC#29361 Add End
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CODE_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1900BMsg scrnMsg = (NWAL1900BMsg) bMsg;
        NWAL1900CMsg bizMsg  = (NWAL1900CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1900CommonLogic.initCmnBtnProp(this);

        //Hightlight
        NWAL1900CommonLogic.setBgColor(scrnMsg);
        NWAL1900CommonLogic.setProtect(scrnMsg); // QC#29752 2018/12/28 Add
        
        // 2018/11/27 QC#29361 Add Start
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (MANUAL_ADJUSTMENT.equals(scrnMsg.A.no(i).prcRuleNm_A.getValue())) {
                scrnMsg.A.no(i).prcRuleNm_LK.clear();
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleNm_LK, ZYPConstant.FLG_ON_Y);
            }
        }
        // 2018/11/27 QC#29361 Add End

        scrnMsg.putErrorScreen();

    }
}
