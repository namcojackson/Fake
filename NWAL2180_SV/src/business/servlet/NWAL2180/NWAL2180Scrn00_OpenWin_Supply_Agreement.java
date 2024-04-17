/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2180;

import static business.servlet.NWAL2180.constant.NWAL2180Constant.BIZ_ID;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.NWAL2190_PRM_NUM;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.ZZZM9025E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2180.NWAL2180CMsg;
import business.servlet.NWAL2180.common.NWAL2180CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         T.Murai         Create          N/A
 * 2016/07/22   Fujitsu         M.Yamada        Update          QC#11339
 * 2016/12/27   Fujitsu         S.Iidaka        Update          QC#16141
 * 2017/02/15   Fujitsu         M.Yamada        Update          QC#17582
 * 2017/05/31   Fujitsu         S.Ohki          Update          RS#8233
 *</pre>
 */
public class NWAL2180Scrn00_OpenWin_Supply_Agreement extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;

        int index = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_A, new BigDecimal(index));
        NWAL2180_RBMsg aScrnMsg = scrnMsg.R.no(index);
        BigDecimal mdlId = aScrnMsg.mdlId_R.getValue();
        BigDecimal cpoSvcConfigRefPk = aScrnMsg.cpoSvcConfigRefPk_R.getValue();

        if (!ZYPCommonFunc.hasValue(aScrnMsg.prcMtrPkgPk_R)) {
            aScrnMsg.prcMtrPkgPk_R.setErrorInfo(1, ZZZM9025E, new String[] {"Service Package" });
            scrnMsg.addCheckItem(aScrnMsg.prcMtrPkgPk_R);
        }

        for (int j = 0; j < scrnMsg.U.getValidCount(); j++) {
            if (mdlId.compareTo(scrnMsg.U.no(j).mdlId_U.getValue()) == 0 //
                    && cpoSvcConfigRefPk.compareTo(scrnMsg.U.no(j).cpoSvcConfigRefPk_U.getValue()) == 0) {
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

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;

        NWAL2180CMsg bizMsg = new NWAL2180CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;
        NWAL2180CMsg bizMsg = (NWAL2180CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL2180CommonLogic.setUsgPrcAreaCtrl(scrnMsg, this);

        NWAL2180CommonLogic.addCheckItems(scrnMsg);
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        ZYPTableUtil.clear(scrnMsg.P);

        int index = getButtonSelectNumber();
        scrnMsg.xxNum_A.setValue(index);

        BigDecimal modelIdR = scrnMsg.R.no(index).mdlId_R.getValue();
        String dsOrdPosnNum = scrnMsg.R.no(index).dsOrdPosnNum_R.getValue();

        int i = 0;
        for (; i < scrnMsg.U.getValidCount(); i++) {
            if (modelIdR.compareTo(scrnMsg.U.no(i).mdlId_U.getValue()) == 0 //
                    && dsOrdPosnNum.equals(scrnMsg.U.no(i).dsOrdPosnNum_U.getValue())) {
                break;
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm_P, scrnMsg.dsImptSvcMdseCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm_P, scrnMsg.prcSvcPlnTpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm_P, scrnMsg.prcSvcContrTpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm_P, scrnMsg.U.no(i).bllgMtrLbCd_U);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm_P, scrnMsg.U.no(i).prcListBandDescTxt_U);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm_P, scrnMsg.U.no(i).prcCatgCd_U);

        int ixP = 0;
        Object[] param = new Object[NWAL2190_PRM_NUM];
        param[ixP] = scrnMsg.dsImptSvcLineNum; // 0
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 1
        ixP++;
        param[ixP] = scrnMsg.shpgIntvlMthNum; // 2
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
        param[ixP] = scrnMsg.R.no(index).cpoSvcConfigRefPk_R; // 10
        ixP++;
        param[ixP] = scrnMsg.xxPageCd; // 11
        ixP++;
        param[ixP] = scrnMsg.xxScrItem50Txt; // 12
        setArgForSubScreen(param);
    }
}
