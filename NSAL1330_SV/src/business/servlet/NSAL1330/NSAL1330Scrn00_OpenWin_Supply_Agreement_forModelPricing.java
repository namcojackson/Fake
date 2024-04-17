/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.BIZ_ID;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAL1380_PRM_NUM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.ZZZM9025E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1330.NSAL1330CMsg;
import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/10/16   Hitachi         Y.Takeno        Create          QC#20001
 * 2018/03/08   Hitachi         T.Tomita        Update          QC#24624
 * 2018/03/27   Hitachi         K.Kojima        Update          QC#25079
 *</pre>
 */
public class NSAL1330Scrn00_OpenWin_Supply_Agreement_forModelPricing extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        int index = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_A, new BigDecimal(index));
        NSAL1330_ABMsg aScrnMsg = scrnMsg.A.no(index);
        BigDecimal mdlId = aScrnMsg.mdlId_A.getValue();
        BigDecimal dsContrDtlPk = aScrnMsg.dsContrDtlPk_A.getValue();

        if (!ZYPCommonFunc.hasValue(aScrnMsg.prcMtrPkgPk_A)) {
            aScrnMsg.prcMtrPkgPk_A.setErrorInfo(1, ZZZM9025E, new String[] {"Service Package" });
            scrnMsg.addCheckItem(aScrnMsg.prcMtrPkgPk_A);
        }

        for (int j = 0; j < scrnMsg.Z.getValidCount(); j++) {
            // Mod Start 2018/03/08 QC#24624
            if (mdlId.compareTo(scrnMsg.Z.no(j).mdlId_Z.getValue()) == 0) {
            // Mod End 2018/03/07 QC#24624
                if (!ZYPCommonFunc.hasValue(scrnMsg.Z.no(j).prcListBandDescTxt_Z)) {
                    scrnMsg.Z.no(j).prcListBandDescTxt_Z.setErrorInfo(1, ZZZM9025E, new String[] {"Band" });
                    scrnMsg.addCheckItem(scrnMsg.Z.no(j).prcListBandDescTxt_Z);

                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.Z.no(j).bllgMtrLbCd_Z)) {
                    scrnMsg.Z.no(j).bllgMtrLbCd_Z.setErrorInfo(1, ZZZM9025E, new String[] {"Billing Counter Name" });
                    scrnMsg.addCheckItem(scrnMsg.Z.no(j).bllgMtrLbCd_Z);
                }
                break;
            }
        }

        NSAL1330CommonLogic.checkForSave(scrnMsg);
        NSAL1330CommonLogic.addCheckItems(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        NSAL1330CMsg bizMsg = new NSAL1330CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40"); // At first execute "Save", then move to Supply Agreement Screen.
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL1330CommonLogic.setUsgPrcAreaCtrl(scrnMsg, this);

        NSAL1330CommonLogic.addCheckItems(scrnMsg);
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        ZYPTableUtil.clear(scrnMsg.P);

        int index = getButtonSelectNumber();
        scrnMsg.xxNum_A.setValue(index);

        BigDecimal modelIdA = scrnMsg.A.no(index).mdlId_A.getValue();

        int i = 0;
        for (; i < scrnMsg.Z.getValidCount(); i++) {
            // START 2018/03/27 K.Kojima [QC#25079,MOD]
            // if (modelIdA.compareTo(scrnMsg.A.no(i).mdlId_A.getValue()) == 0) {
            if (modelIdA.compareTo(scrnMsg.Z.no(i).mdlId_Z.getValue()) == 0) {
            // END 2018/03/27 K.Kojima [QC#25079,MOD]
                break;
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm_P, scrnMsg.svcPgmMdseCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm_P, scrnMsg.prcSvcPlnTpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm_P, scrnMsg.prcSvcContrTpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm_P, scrnMsg.Z.no(i).bllgMtrLbCd_Z);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm_P, scrnMsg.Z.no(i).prcListBandDescTxt_Z);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm_P, scrnMsg.Z.no(i).prcCatgCd_Z);

        int ixP = 0;
        Object[] param = new Object[NSAL1380_PRM_NUM];
        param[ixP] = scrnMsg.shellLineNum; // 0
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 1
        ixP++;
        param[ixP] = scrnMsg.termMthAot; // 2
        ixP++;
        param[ixP] = scrnMsg.A.no(index).mdlId_A; // 3
        ixP++;
        param[ixP] = scrnMsg.A.no(index).prcMtrPkgPk_A; // 4
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 5
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 6
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 7
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 8
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 9
        ixP++;
        param[ixP] = scrnMsg.A.no(index).dsContrDtlPk_A; // 10
        ixP++;
        param[ixP] = scrnMsg.xxPageCd; // 11
        ixP++;
        param[ixP] = scrnMsg.xxScrItem50Txt; // 12
        setArgForSubScreen(param);
    }
}
