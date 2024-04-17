/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0740;

import static business.servlet.NSAL0740.common.NSAL0740CommonLogic.controlScreenFields;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0740.NSAL0740CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Hitachi         Y.Takeno        Create          N/A
 * 2016/02/08   Hitachi         T.Aoyagi        Update          QC#4089
 * 2016/04/27   Hitachi         M.Gotou         Update          QC#6799
 * 2016/05/18   Hitachi         M.Gotou         Update          QC#4472
 * 2016/07/14   Hitachi         A.Kohinata      Update          QC#8608
 * 2017/08/22   CITS            T.Kikuhara      Update          QC#18799(L3)
 * 2018/11/16   Hitachi         K.Kitachi       Update          QC#28638
 *</pre>
 */
public class NSAL0740Scrn00_ApplyToAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.contrUplftTpCd_H3);
        scrnMsg.addCheckItem(scrnMsg.uplftPrcMethCd_H3);
        scrnMsg.addCheckItem(scrnMsg.uplftBasePrcUpRatio_H1);
        scrnMsg.addCheckItem(scrnMsg.uplftMtrPrcUpRatio_H1);

        // add start 2016/04/27 CSA Defect#6799
        if (ZYPCommonFunc.hasValue(scrnMsg.contrUplftTpCd_H3) && !CONTR_UPLFT_TP.DO_NOT_UPLIFT.equals(scrnMsg.contrUplftTpCd_H3.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.uplftPrcMethCd_H3)) {
                scrnMsg.uplftPrcMethCd_H3.setErrorInfo(1, ZZZM9025E, new String[] {"New Escalation Method" });
            }
        }
        // mod start 2016/07/13 CSA Defect#8608
        if (ZYPCommonFunc.hasValue(scrnMsg.uplftPrcMethCd_H3) && !UPLFT_PRC_METH.MODEL_PERCENT.equals(scrnMsg.uplftPrcMethCd_H3.getValue())) {
            if (CONTR_UPLFT_TP.UPLIFT_BASE_ONLY.equals(scrnMsg.contrUplftTpCd_H3.getValue()) || CONTR_UPLFT_TP.UPLIFT_BASE_OVERAGE.equals(scrnMsg.contrUplftTpCd_H3.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.uplftBasePrcUpRatio_H1)) {
                    scrnMsg.uplftBasePrcUpRatio_H1.setErrorInfo(1, ZZZM9025E, new String[] {"New Base%" });
                }
            }
            if (CONTR_UPLFT_TP.UPLIFT_OVERAGE_ONLY.equals(scrnMsg.contrUplftTpCd_H3.getValue()) || CONTR_UPLFT_TP.UPLIFT_BASE_OVERAGE.equals(scrnMsg.contrUplftTpCd_H3.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.uplftMtrPrcUpRatio_H1)) {
                    scrnMsg.uplftMtrPrcUpRatio_H1.setErrorInfo(1, ZZZM9025E, new String[] {"New Overage%" });
                }
            }
        }
        // mod end 2016/07/13 CSA Defect#8608
        // add end 2016/04/27 CSA Defect#6799

        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;

        NSAL0740CMsg bizMsg = new NSAL0740CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUCNTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, NSAL0740Bean.xxChkBox_S1, FLG_ON_Y);
        for (int index : selectedRows) {
            // add start 2016/05/18 CSA Defect#4472
            if (LINE_LEVEL_BASE_OVERAGE.equals(scrnMsg.A.no(index).dsContrMachLvlNum_D1.getValue())) {
                continue;
            }
            // add end 2016/05/18 CSA Defect#4472
            setValue(scrnMsg.A.no(index).contrUplftTpCd_D3, scrnMsg.contrUplftTpCd_H3);
            setValue(scrnMsg.A.no(index).uplftPrcMethCd_D3, scrnMsg.uplftPrcMethCd_H3);
            // START 2016/02/08 T.Aoyagi [QC#4089, MOD]
//            setValue(scrnMsg.A.no(index).basePrcUpRatio_D1, scrnMsg.uplftBasePrcUpRatio_H1);
//            setValue(scrnMsg.A.no(index).mtrPrcUpRatio_D1, scrnMsg.uplftMtrPrcUpRatio_H1);
            setValue(scrnMsg.A.no(index).uplftBasePrcUpRatio_D1, scrnMsg.uplftBasePrcUpRatio_H1);
            setValue(scrnMsg.A.no(index).uplftMtrPrcUpRatio_D1, scrnMsg.uplftMtrPrcUpRatio_H1);
            // END   2016/02/08 T.Aoyagi [QC#4089, MOD]
            // QC#18799 ADD START
            setValue(scrnMsg.A.no(index).uplftBefEndRnwDaysAot_D1, scrnMsg.befEndUplftDaysAot_H1);
            // QC#18799 ADD END
            // add start 2016/07/13 CSA Defect#8608
            setValue(scrnMsg.A.no(index).uplftBaseFlg_D1, scrnMsg.uplftBaseFlg_H1);
            setValue(scrnMsg.A.no(index).uplftUsgFlg_D1, scrnMsg.uplftUsgFlg_H1);
            clearUplftDetail(scrnMsg.A.no(index));
            // add end 2016/07/13 CSA Defect#8608
        }

        selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, NSAL0740Bean.xxChkBox_S2, FLG_ON_Y);
        for (int index : selectedRows) {
            // add start 2016/05/18 CSA Defect#4472
            if (LINE_LEVEL_CONTRACT.equals(scrnMsg.A.no(index).dsContrMachLvlNum_D1.getValue()) || LINE_LEVEL_CONTRACT_DETAIL.equals(scrnMsg.A.no(index).dsContrMachLvlNum_D1.getValue())) {
                continue;
            }
            // add end 2016/05/18 CSA Defect#4472
            setValue(scrnMsg.A.no(index).contrUplftTpCd_D3, scrnMsg.contrUplftTpCd_H3);
            setValue(scrnMsg.A.no(index).uplftPrcMethCd_D3, scrnMsg.uplftPrcMethCd_H3);
            if (BASE.equals(scrnMsg.A.no(index).dsContrBaseUsgNm_D1.getValue())) {
                // START 2016/02/08 T.Aoyagi [QC#4089, MOD]
//                setValue(scrnMsg.A.no(index).basePrcUpRatio_D1, scrnMsg.uplftBasePrcUpRatio_H1);
                setValue(scrnMsg.A.no(index).uplftBasePrcUpRatio_D1, scrnMsg.uplftBasePrcUpRatio_H1);
                // END   2016/02/08 T.Aoyagi [QC#4089, MOD]
            }

            if (OVERAGE.equals(scrnMsg.A.no(index).dsContrBaseUsgNm_D1.getValue())) {
                // START 2016/02/08 T.Aoyagi [QC#4089, MOD]
//                setValue(scrnMsg.A.no(index).mtrPrcUpRatio_D1, scrnMsg.uplftMtrPrcUpRatio_H1);
                setValue(scrnMsg.A.no(index).uplftMtrPrcUpRatio_D1, scrnMsg.uplftMtrPrcUpRatio_H1);
                // END   2016/02/08 T.Aoyagi [QC#4089, MOD]
            }
            // QC#18799 ADD START
            setValue(scrnMsg.A.no(index).uplftBefEndRnwDaysAot_D1, scrnMsg.befEndUplftDaysAot_H1);
            // QC#18799 ADD END
            // add start 2016/07/13 CSA Defect#8608
            setValue(scrnMsg.A.no(index).uplftBaseFlg_D1, scrnMsg.uplftBaseFlg_H1);
            setValue(scrnMsg.A.no(index).uplftUsgFlg_D1, scrnMsg.uplftUsgFlg_H1);
            clearUplftDetail(scrnMsg.A.no(index));
            // add end 2016/07/13 CSA Defect#8608
        }

        // add start 2016/07/13 CSA Defect#8608
        controlScreenFields(this, scrnMsg);
        // add end 2016/07/13 CSA Defect#8608
    }

    // add start 2016/07/13 CSA Defect#8608
    private void clearUplftDetail(NSAL0740_ABMsg abMsg) {
        if (!ZYPConstant.FLG_ON_Y.equals(abMsg.uplftBaseFlg_D1.getValue()) && !ZYPConstant.FLG_ON_Y.equals(abMsg.uplftUsgFlg_D1.getValue())) {
            abMsg.uplftBefEndRnwDaysAot_D1.clear();
            // START 2018/11/16 K.Kitachi [QC#28638, DEL]
//            abMsg.xxChkBox_E1.clear();
//            abMsg.xxChkBox_E2.clear();
//            abMsg.xxChkBox_E3.clear();
//            abMsg.xxChkBox_E4.clear();
//            abMsg.xxChkBox_E5.clear();
            // END 2018/11/16 K.Kitachi [QC#28638, DEL]
        }
    }
    // add end 2016/07/13 CSA Defect#8608
}
