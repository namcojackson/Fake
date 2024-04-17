/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1330.NSAL1330CMsg;
import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1330Scrn00_OnChange_SvcPkg
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_OnChange_SvcPkg extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        int aIndex = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_A, new BigDecimal(aIndex));
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        NSAL1330CMsg bizMsg = new NSAL1330CMsg();
        bizMsg.setBusinessID("NSAL1330");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL1330CommonLogic.setUsgPrcAreaCtrl(scrnMsg, this);

        int aIndex = scrnMsg.xxNum_A.getValueInt();
        NSAL1330CommonLogic.setTierLinkCtrl(this, scrnMsg, aIndex);
        NSAL1330CommonLogic.setSupplyButton(this, scrnMsg.A.no(aIndex), aIndex, scrnMsg);

        BigDecimal mdlId = scrnMsg.A.no(aIndex).mdlId_A.getValue();
        int ixZ = -1;
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            //            NSAL1330CommonLogic.setBandButton(this, scrnMsg.Z.no(i).usgMdseCd_Z.getValue(), i);
            NSAL1330CommonLogic.setBandButton(this, scrnMsg, NSAL1330CommonLogic.isParentLine(scrnMsg.Z.no(i)), i);
            if (!DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
                if (ixZ < 0 && mdlId.compareTo(scrnMsg.Z.no(i).mdlId_Z.getValue()) == 0) {
                    ixZ = i;
                }
            }
        }
        if (scrnMsg.Z.getValidCount() > 0 && ixZ >= 0) {
            scrnMsg.setFocusItem(scrnMsg.Z.no(ixZ).prcListBandDescTxt_Z);
        } else if (scrnMsg.Z.getValidCount() > 0 && DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(aIndex).xxTotPrcAmt_PB)) {
                scrnMsg.setFocusItem(scrnMsg.Z.no(0).prcListBandDescTxt_Z);
            } else {
                scrnMsg.setFocusItem(scrnMsg.A.no(aIndex).xxTotPrcAmt_PB);
            }
        } else if (ZYPCommonFunc.hasValue(bizMsg.A.no(aIndex).prcMtrPkgPk_A)) {
            scrnMsg.setFocusItem(scrnMsg.A.no(aIndex).xxTotPrcAmt_PB);
        } else {
            scrnMsg.setFocusItem(scrnMsg.A.no(aIndex).prcMtrPkgPk_A);
        }
        NSAL1330CommonLogic.overrideProtected(this, scrnMsg);
    }
}
