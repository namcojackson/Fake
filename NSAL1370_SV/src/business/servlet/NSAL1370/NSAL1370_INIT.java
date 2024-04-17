/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1370;

import static business.servlet.NSAL1370.constant.NSAL1370Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NSAL1370.constant.NSAL1370Constant.BIZ_ID;
import static business.servlet.NSAL1370.constant.NSAL1370Constant.BTN_ADD_TIER;
import static business.servlet.NSAL1370.constant.NSAL1370Constant.BTN_CMN_CLR;
import static business.servlet.NSAL1370.constant.NSAL1370Constant.BTN_CTRL_INACTIVE;
import static business.servlet.NSAL1370.constant.NSAL1370Constant.BTN_DEL_TIER;
import static business.servlet.NSAL1370.constant.NSAL1370Constant.NSAM0626E;
import static business.servlet.NSAL1370.constant.NSAL1370Constant.NSAM0636E;
import static business.servlet.NSAL1370.constant.NSAL1370Constant.PRM_CNT;
import static business.servlet.NSAL1370.constant.NSAL1370Constant.PROC_MD_REF;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1370.NSAL1370CMsg;
import business.servlet.NSAL1370.common.NSAL1370CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NSAL1370_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Osawa         Create          N/A
 * 2017/10/26   Hitachi         K.Kojima        Update          QC#21556
 *</pre>
 */
public class NSAL1370_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1370BMsg scrnMsg = (NSAL1370BMsg) bMsg;
        NSAL1370CMsg bizMsg = new NSAL1370CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        int ixP = 0;
        if (params != null && params.length >= PRM_CNT) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxProcMd_P, (EZDBStringItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoSvcPrcPk_P, (EZDBBigDecimalItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoSvcDtlPk_P, (EZDBBigDecimalItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdlId_P, (EZDBBigDecimalItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.bllgMtrLbCd_P, (EZDBStringItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrCatgCd_P, (EZDBStringItem) params[ixP++]);
            if (!DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd_P.getValue()) //
                    && !ZYPCommonFunc.hasValue(scrnMsg.mdlId_P)) {
                scrnMsg.setMessageInfo(NSAM0636E, new String[] {"Model" });
                return null;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.bllgMtrLbCd_P)) {
                scrnMsg.setMessageInfo(NSAM0636E, new String[] {"Billing Counter" });
                return null;
            }
            if (params.length > PRM_CNT) {
                String suffix = (String) params[ixP++];
                EZDBMsgArray array = (EZDBMsgArray) params[ixP++];
                EZDMsg.copy(array, suffix, scrnMsg.Q, "Q");
                // START 2017/10/26 K.Kojima [QC#21556,ADD]
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrPk_P, (EZDBBigDecimalItem) params[ixP++]);
                // END 2017/10/26 K.Kojima [QC#21556,ADD]
            }
        } else {
            scrnMsg.setMessageInfo(NSAM0626E, new String[] {"" });
            return null;
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1370BMsg scrnMsg = (NSAL1370BMsg) bMsg;
        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            scrnMsg.setInputProtected(true);
            return;
        }
        NSAL1370CMsg bizMsg = (NSAL1370CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1370CommonLogic.initCmnBtnProp(this);
        NSAL1370CommonLogic.initItemCtrl(this, scrnMsg);
        NSAL1370CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        if (PROC_MD_REF.equals(scrnMsg.xxProcMd_P.getValue())) {
            NSAL1370CommonLogic.setCmnBtnProp(this, BTN_CMN_CLR, BTN_CTRL_INACTIVE);
            NSAL1370CommonLogic.setBizBtnProp(this, BTN_ADD_TIER, false);
            NSAL1370CommonLogic.setBizBtnProp(this, BTN_DEL_TIER, false);
            NSAL1370CommonLogic.protectDetail(scrnMsg);
        } else if (scrnMsg.A.getValidCount() > 0) {
            // START 2017/10/26 K.Kojima [QC#21556,ADD]
            if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyCtrlFlg_AG) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_AG.getValue())) {
                NSAL1370CommonLogic.setBizBtnProp(this, BTN_ADD_TIER, false);
                NSAL1370CommonLogic.setBizBtnProp(this, BTN_DEL_TIER, false);
                NSAL1370CommonLogic.protectDetailForAgg(scrnMsg);
            }
            // END 2017/10/26 K.Kojima [QC#21556,ADD]
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(0).maxCopyVolCnt_A)) {
                scrnMsg.setFocusItem(scrnMsg.A.no(0).maxCopyVolCnt_A);
            }
        }
        setAppFracDigit(scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1370BMsg scrnMsg = (NSAL1370BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).minCopyVolCnt_A.setNameForMessage("Range From");
            scrnMsg.A.no(i).maxCopyVolCnt_A.setNameForMessage("Range To");
            scrnMsg.A.no(i).xsMtrAmtRate_A.setNameForMessage("Excess Per Image Charge");
        }
    }

    private void setAppFracDigit(NSAL1370BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).minCopyVolCnt_A.setAppFracDigit(0);
            scrnMsg.A.no(i).maxCopyVolCnt_A.setAppFracDigit(0);
            scrnMsg.A.no(i).xsMtrAmtRate_A.setAppFracDigit(5);
        }
    }
}
