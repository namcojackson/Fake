/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

//import static business.servlet.NSAL1330.constant.NSAL1330Constant.BIZ_ID;
import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg; //import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; //import business.blap.NSAL1330.NSAL1330CMsg;
//import business.servlet.NSAL1330.constant.NSAL1330Constant;

import business.blap.NSAL1330.NSAL1330CMsg;
import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1330Scrn00_OnChange_SvcPkg_forConfigPricing
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_OnChange_SvcPkg_forConfigPricing extends S21CommonHandler {

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
        NSAL1330CommonLogic.setUsgPrcAreaCtrlConfig(scrnMsg, this);

        int ixR = scrnMsg.xxNum_A.getValueInt();
        NSAL1330CommonLogic.setTierLinkCtrlConfig(this, scrnMsg, ixR);
        NSAL1330CommonLogic.setSupplyButton(this, scrnMsg.R.no(ixR), ixR, scrnMsg);

//        BigDecimal mdlId = scrnMsg.R.no(ixR).mdlId_R.getValue();
//        BigDecimal cpoSvcConfigRefPk = scrnMsg.R.no(ixR).cpoSvcConfigRefPk_R.getValue();
//        int ixU = -1;
//        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
//            NSAL1330CommonLogic.setBandButtonConfig(//
//                    this, scrnMsg, NSAL1330CommonLogic.isParentLine(scrnMsg.U.no(i)), i);
//            if (!DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
//                if (ixU < 0 && mdlId.compareTo(scrnMsg.U.no(i).mdlId_U.getValue()) == 0 //
//                        && cpoSvcConfigRefPk.compareTo(scrnMsg.U.no(i).cpoSvcConfigRefPk_U.getValue()) == 0) {
//                    ixU = i;
//                    break;
//                }
//            }
//        }
        BigDecimal mdlId = scrnMsg.R.no(ixR).mdlId_R.getValue();
        BigDecimal dsContrDtlPk = scrnMsg.R.no(ixR).dsContrDtlPk_R.getValue();
        int ixU = -1;
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            NSAL1330CommonLogic.setBandButtonConfig(//
                    this, scrnMsg, NSAL1330CommonLogic.isParentLine(scrnMsg.U.no(i)), i);
            if (!DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
                if (ixU < 0 && mdlId.compareTo(scrnMsg.U.no(i).mdlId_U.getValue()) == 0 //
                        && dsContrDtlPk.compareTo(scrnMsg.U.no(i).dsContrDtlPk_U.getValue()) == 0) {
                    ixU = i;
                    break;
                }
            }
        }
        if (scrnMsg.U.getValidCount() > 0 && ixU >= 0) {
            scrnMsg.setFocusItem(scrnMsg.U.no(ixU).prcListBandDescTxt_U);
        } else if (scrnMsg.U.getValidCount() > 0 && DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.U.no(0).prcListBandDescTxt_U);
        } else {
            scrnMsg.setFocusItem(scrnMsg.R.no(ixR).prcMtrPkgPk_R);
        }
        NSAL1330CommonLogic.overrideProtected(this, scrnMsg);
    }
}
