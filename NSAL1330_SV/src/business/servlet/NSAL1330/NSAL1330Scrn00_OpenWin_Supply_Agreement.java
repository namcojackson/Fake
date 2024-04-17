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
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_OpenWin_Supply_Agreement extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        int index = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_A, new BigDecimal(index));
        NSAL1330_RBMsg aScrnMsg = scrnMsg.R.no(index);
        BigDecimal mdlId = aScrnMsg.mdlId_R.getValue();
//        BigDecimal cpoSvcConfigRefPk = aScrnMsg.cpoSvcConfigRefPk_R.getValue();
        BigDecimal dsContrDtlPk = aScrnMsg.dsContrDtlPk_R.getValue();

        if (!ZYPCommonFunc.hasValue(aScrnMsg.prcMtrPkgPk_R)) {
            aScrnMsg.prcMtrPkgPk_R.setErrorInfo(1, ZZZM9025E, new String[] {"Service Package" });
            scrnMsg.addCheckItem(aScrnMsg.prcMtrPkgPk_R);
        }

        for (int j = 0; j < scrnMsg.U.getValidCount(); j++) {
//            if (mdlId.compareTo(scrnMsg.U.no(j).mdlId_U.getValue()) == 0 //
//                    && cpoSvcConfigRefPk.compareTo(scrnMsg.U.no(j).cpoSvcConfigRefPk_U.getValue()) == 0) {
            if (mdlId.compareTo(scrnMsg.U.no(j).mdlId_U.getValue()) == 0 //
                    && dsContrDtlPk.compareTo(scrnMsg.U.no(j).dsContrDtlPk_U.getValue()) == 0) {

                if (!ZYPCommonFunc.hasValue(scrnMsg.U.no(j).prcListBandDescTxt_U)) {
                    scrnMsg.U.no(j).prcListBandDescTxt_U.setErrorInfo(1, ZZZM9025E, new String[] {"Band" });
                    scrnMsg.addCheckItem(scrnMsg.U.no(j).prcListBandDescTxt_U);

                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.U.no(j).bllgMtrLbCd_U)) {
                    scrnMsg.U.no(j).bllgMtrLbCd_U.setErrorInfo(1, ZZZM9025E, new String[] {"Billing Counter Name" });
                    scrnMsg.addCheckItem(scrnMsg.U.no(j).bllgMtrLbCd_U);
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
        if (NSAL1330CommonLogic.isImport(scrnMsg)) {
            bizMsg.setFunctionCode("20");
        } else {
            bizMsg.setFunctionCode("40"); // At first execute "Save", then move to Supply Agreement Screen.
        }
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
        //
        //        scrnMsg.putErrorScreen();
        //
        //        if ("E".equals(bizMsg.getMessageKind())) {
        //            throw new EZDFieldErrorException();
        //        }
        ZYPTableUtil.clear(scrnMsg.P);

        int index = getButtonSelectNumber();
        scrnMsg.xxNum_A.setValue(index);

        // QC#16141 MOD START
        BigDecimal modelIdR = scrnMsg.R.no(index).mdlId_R.getValue();
        String dsOrdPosnNum = scrnMsg.R.no(index).dsOrdPosnNum_R.getValue();

        int i = 0;
        for (; i < scrnMsg.U.getValidCount(); i++) {
            if (modelIdR.compareTo(scrnMsg.U.no(i).mdlId_U.getValue()) == 0 //
                    && dsOrdPosnNum.equals(scrnMsg.U.no(i).dsOrdPosnNum_U.getValue())) {
                break;
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm_P, scrnMsg.svcPgmMdseCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm_P, scrnMsg.prcSvcPlnTpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm_P, scrnMsg.prcSvcContrTpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm_P, scrnMsg.U.no(i).bllgMtrLbCd_U);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm_P, scrnMsg.U.no(i).prcListBandDescTxt_U);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm_P, scrnMsg.U.no(i).prcCatgCd_U);

        int ixP = 0;
        Object[] param = new Object[NSAL1380_PRM_NUM];
        param[ixP] = scrnMsg.shellLineNum; // 0
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 1
        ixP++;
        param[ixP] = scrnMsg.termMthAot; // 2
        ixP++;
        param[ixP] = scrnMsg.R.no(index).mdlId_R; // 3
        ixP++;
        param[ixP] = scrnMsg.R.no(index).prcMtrPkgPk_R; // 4
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
//        param[ixP] = scrnMsg.R.no(index).cpoSvcConfigRefPk_R; // 10
//        ixP++;
        param[ixP] = scrnMsg.R.no(index).dsContrDtlPk_R; // 10
        ixP++;
        param[ixP] = scrnMsg.xxPageCd; // 11
        ixP++;
        param[ixP] = scrnMsg.xxScrItem50Txt; // 12
        setArgForSubScreen(param);
    }
    // QC#16141 MOD START
}
