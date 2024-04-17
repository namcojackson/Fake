/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0380;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0380.NSAL0380CMsg;
import business.servlet.NSAL0380.common.NSAL0380CommonLogic;
import business.servlet.NSAL0380.constant.NSAL0380Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/23   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/15   Hitachi         O.Okuma         Update          QC3029
 *</pre>
 */
public class NSAL0380_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), NSAL0380Constant.BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;

        // set Invoker screen value.
        Serializable arg = super.getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            NSAL0380CommonLogic.getInputParam(scrnMsg, params);
        }

        NSAL0380CMsg bizMsg = new NSAL0380CMsg();
        bizMsg.setBusinessID(NSAL0380Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NSAL0380Constant.FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;
        NSAL0380CMsg bizMsg = (NSAL0380CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0380CommonLogic.initControlCommonButton(this);
        NSAL0380CommonLogic.controlScreenFields(this, scrnMsg);
        NSAL0380CommonLogic.controlDtl(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;

        scrnMsg.serNum_HF.setNameForMessage(NSAL0380Constant.ITEM_NM_SER_NUM_FROM);
        scrnMsg.serNum_HT.setNameForMessage(NSAL0380Constant.ITEM_NM_SER_NUM_THRU);
        scrnMsg.svcMachMstrPk_HF.setNameForMessage(NSAL0380Constant.ITEM_NM_SVC_MACH_MSTR_PK_FROM);
        scrnMsg.svcMachMstrPk_HT.setNameForMessage(NSAL0380Constant.ITEM_NM_SVC_MACH_MSTR_PK_THRU);
        scrnMsg.contrAutoRnwTpCd_H.setNameForMessage(NSAL0380Constant.ITEM_NM_CONTR_AUTO_RNW_TP_CD_NEW);
        scrnMsg.rnwPrcMethCd_H.setNameForMessage(NSAL0380Constant.ITEM_NM_RNW_PRC_METH_CD_NEW);
        scrnMsg.befEndRnwDaysAot_H.setNameForMessage(NSAL0380Constant.ITEM_NM_BEF_END_RNW_DAYS_AOT_NEW);
        scrnMsg.maxPrcUpRatio_H.setNameForMessage(NSAL0380Constant.ITEM_NM_MAX_PRC_UP_RATIO_NEW);
        scrnMsg.basePrcUpRatio_H.setNameForMessage(NSAL0380Constant.ITEM_NM_BASE_PRC_UP_RATIO_NEW);
        scrnMsg.mtrPrcUpRatio_H.setNameForMessage(NSAL0380Constant.ITEM_NM_MTR_PRC_UP_RATIO_NEW);
        scrnMsg.maxContrRnwCnt_H.setNameForMessage(NSAL0380Constant.ITEM_NM_MAX_CONTR_RNW_CNT_NEW);
        scrnMsg.svcMemoRsnCd_H.setNameForMessage(NSAL0380Constant.ITEM_NM_SVC_MEMO_RSN_CD);
        scrnMsg.svcCmntTxt_H.setNameForMessage(NSAL0380Constant.ITEM_NM_SVC_CMNT_TXT);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).contrAutoRnwTpCd_A.setNameForMessage(NSAL0380Constant.ITEM_NM_CONTR_AUTO_RNW_TP_CD);
            scrnMsg.A.no(i).rnwPrcMethCd_A.setNameForMessage(NSAL0380Constant.ITEM_NM_RNW_PRC_METH_CD);
            scrnMsg.A.no(i).befEndRnwDaysAot_A.setNameForMessage(NSAL0380Constant.ITEM_NM_BEF_END_RNW_DAYS_AOT);
            scrnMsg.A.no(i).maxContrRnwCnt_A.setNameForMessage(NSAL0380Constant.ITEM_NM_MAX_CONTR_RNW_CNT);
            scrnMsg.A.no(i).maxPrcUpRatio_A.setNameForMessage(NSAL0380Constant.ITEM_NM_MAX_PRC_UP_RATIO);
            scrnMsg.A.no(i).basePrcUpRatio_A.setNameForMessage(NSAL0380Constant.ITEM_NM_BASE_PRC_UP_RATIO);
            scrnMsg.A.no(i).mtrPrcUpRatio_A.setNameForMessage(NSAL0380Constant.ITEM_NM_MTR_PRC_UP_RATIO);
        }
    }
}
