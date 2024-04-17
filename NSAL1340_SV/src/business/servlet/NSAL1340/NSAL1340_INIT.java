/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1340;

import static business.servlet.NSAL1340.constant.NSAL1340Constant.*;
import static business.servlet.NSAL1340.constant.NSAL1340Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NSAL1340.constant.NSAL1340Constant.NSAM0644E;
import static business.servlet.NSAL1340.constant.NSAL1340Constant.NZZM0012E;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1340.NSAL1340CMsg;
import business.servlet.NSAL1340.common.NSAL1340CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NSAL1340_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/09   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public class NSAL1340_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // checkBusinessAppGranted(getContextUserInfo().getUserId(),
        // BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1340BMsg scrnMsg = (NSAL1340BMsg) bMsg;

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == NSAL1340_PRM_CNT) {
            int ixP = 0;
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgCd, (EZDBStringItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdlId, (EZDBBigDecimalItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdlNm, (EZDBStringItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcMtrPkgPk, (EZDBBigDecimalItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcSvcPlnTpCd, (EZDBStringItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcSvcContrTpCd, (EZDBStringItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.bllgMtrLbCd, (EZDBStringItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcBaseDt, (EZDBDateItem) params[ixP++]);
            // 20160308 add
            ZYPEZDItemValueSetter.setValue(scrnMsg.fromPerMthNum_I, (EZDBBigDecimalItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.toPerMthNum_I, (EZDBBigDecimalItem) params[ixP++]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcListBandDescTxt_I, (EZDBStringItem) params[ixP++]);

            String errMsg = NSAL1340CommonLogic.checkInputParam(scrnMsg);
            if (ZYPCommonFunc.hasValue(errMsg)) {
                scrnMsg.setMessageInfo(NZZM0012E, new String[] {errMsg });
            }
        } else {
            scrnMsg.setMessageInfo(NSAM0644E);
        }
        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            return null;
        }

        NSAL1340CMsg bizMsg = new NSAL1340CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1340BMsg scrnMsg = (NSAL1340BMsg) bMsg;

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            scrnMsg.setInputProtected(true);
            NSAL1340CommonLogic.protectCmnBtnProp(this, scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.prcCatgNm);
            return;
        }
        NSAL1340CMsg bizMsg = (NSAL1340CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1340CommonLogic.initCmnBtnProp(this);
        NSAL1340CommonLogic.setInputProtect(scrnMsg);
        setAppFracDigit(scrnMsg);

        NSAL1340CommonLogic.setRadioButton(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.xxRadioBtn);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL1340BMsg scrnMsg = (NSAL1340BMsg) bMsg;

        scrnMsg.prcCatgNm.setNameForMessage("Price List");
        scrnMsg.mdlNm.setNameForMessage("Model");
        scrnMsg.prcMtrPkgNm.setNameForMessage("package Name");
        scrnMsg.mtrLbNm.setNameForMessage("Billing Counter Name");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).prcListBandDescTxt.setNameForMessage("Band");
            scrnMsg.A.no(i).mdseCd.setNameForMessage("Price Book Item");
            scrnMsg.A.no(i).minCopyVolCnt.setNameForMessage("Min Volume");
            scrnMsg.A.no(i).maxCopyVolCnt.setNameForMessage("Max Volume");
            scrnMsg.A.no(i).prcSvcTierTpNm.setNameForMessage("Tier Type");
            scrnMsg.A.no(i).cpcAmtRate.setNameForMessage("Overage Rate");
            scrnMsg.A.no(i).totBaseAmt.setNameForMessage("BasePrice");
        }
    }

    private void setAppFracDigit(NSAL1340BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).cpcAmtRate.setAppFracDigit(4);
            scrnMsg.A.no(i).totBaseAmt.setAppFracDigit(2);
        }
    }
}
