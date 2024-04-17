/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2160;

import static business.servlet.NWAL2160.constant.NWAL2160Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL2160.constant.NWAL2160Constant.BIZ_ID;
import static business.servlet.NWAL2160.constant.NWAL2160Constant.BTN_ADD_TIER;
import static business.servlet.NWAL2160.constant.NWAL2160Constant.BTN_CMN_CLR;
import static business.servlet.NWAL2160.constant.NWAL2160Constant.BTN_CTRL_INACTIVE;
import static business.servlet.NWAL2160.constant.NWAL2160Constant.BTN_DEL_TIER;
import static business.servlet.NWAL2160.constant.NWAL2160Constant.NWAM0199E;
import static business.servlet.NWAL2160.constant.NWAL2160Constant.NWAM0759E;
import static business.servlet.NWAL2160.constant.NWAL2160Constant.PRM_CNT;
import static business.servlet.NWAL2160.constant.NWAL2160Constant.PROC_MD_REF;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2160.NWAL2160CMsg;
import business.servlet.NWAL2160.common.NWAL2160CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL2160_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL2160_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2160BMsg scrnMsg = (NWAL2160BMsg) bMsg;
        NWAL2160CMsg bizMsg = new NWAL2160CMsg();

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
                scrnMsg.setMessageInfo(NWAM0759E, new String[] {"Model" });
                return null;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.bllgMtrLbCd_P)) {
                scrnMsg.setMessageInfo(NWAM0759E, new String[] {"Billing Counter" });
                return null;
            }
            if (params.length > PRM_CNT) {
                String suffix = (String) params[ixP++];
                EZDBMsgArray array = (EZDBMsgArray) params[ixP++];
                EZDMsg.copy(array, suffix, scrnMsg.Q, "Q");
            }
        } else {
            scrnMsg.setMessageInfo(NWAM0199E, new String[] {"" });
            return null;
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2160BMsg scrnMsg = (NWAL2160BMsg) bMsg;
        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            scrnMsg.setInputProtected(true);
            return;
        }
        NWAL2160CMsg bizMsg = (NWAL2160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2160CommonLogic.initCmnBtnProp(this);
        NWAL2160CommonLogic.initItemCtrl(this, scrnMsg);
        NWAL2160CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        if (PROC_MD_REF.equals(scrnMsg.xxProcMd_P.getValue())) {
            NWAL2160CommonLogic.setCmnBtnProp(this, BTN_CMN_CLR, BTN_CTRL_INACTIVE);
            NWAL2160CommonLogic.setBizBtnProp(this, BTN_ADD_TIER, false);
            NWAL2160CommonLogic.setBizBtnProp(this, BTN_DEL_TIER, false);
            NWAL2160CommonLogic.protectDetail(scrnMsg);
        } else if (scrnMsg.A.getValidCount() > 0) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(0).maxCopyVolCnt_A)) {
                scrnMsg.setFocusItem(scrnMsg.A.no(0).maxCopyVolCnt_A);
            }
        }
        setAppFracDigit(scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL2160BMsg scrnMsg = (NWAL2160BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).minCopyVolCnt_A.setNameForMessage("Range From");
            scrnMsg.A.no(i).maxCopyVolCnt_A.setNameForMessage("Range To");
            scrnMsg.A.no(i).xsMtrAmtRate_A.setNameForMessage("Excess Per Image Charge");
        }
    }

    private void setAppFracDigit(NWAL2160BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).minCopyVolCnt_A.setAppFracDigit(0);
            scrnMsg.A.no(i).maxCopyVolCnt_A.setAppFracDigit(0);
            scrnMsg.A.no(i).xsMtrAmtRate_A.setAppFracDigit(5);
        }
    }
}
