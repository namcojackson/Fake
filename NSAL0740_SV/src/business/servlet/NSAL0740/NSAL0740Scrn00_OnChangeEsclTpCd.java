/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0740;

import static business.servlet.NSAL0740.common.NSAL0740CommonLogic.controlScreenFields;
import static business.servlet.NSAL0740.common.NSAL0740CommonLogic.getLowerLinesForContract;
import static business.servlet.NSAL0740.common.NSAL0740CommonLogic.getLowerLinesForSer;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.BUSINESS_ID;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.FUCNTION_CD_SEARCH;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.LINE_LEVEL_CONTRACT;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.LINE_LEVEL_CONTRACT_DETAIL;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.LINE_LEVEL_BASE_OVERAGE;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0740.NSAL0740CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/25   Hitachi         M.Gotou         Create          N/A
 * 2016/07/13   Hitachi         A.Kohinata      Update          QC#8608
 * 2017/11/08   Hitachi         M.Kidokoro      Update          QC#21928
 * 2018/11/16   Hitachi         K.Kitachi       Update          QC#28638
 *</pre>
 */
public class NSAL0740Scrn00_OnChangeEsclTpCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;

        // event line
        final int eventLine = getButtonSelectNumber();
        scrnMsg.xxNum_EV.setValue(eventLine);

        // mod start 2016/07/13 CSA Defect#8608
        NSAL0740CMsg bizMsg = new NSAL0740CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUCNTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        setValue(bizMsg.xxRowNum, BigDecimal.valueOf(eventLine));

        return bizMsg;
        // mod end 2016/07/13 CSA Defect#8608
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // mod start 2016/07/13 CSA Defect#8608
        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;
        NSAL0740CMsg bizMsg = (NSAL0740CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int currentCnt = scrnMsg.xxNum_EV.getValueInt();
        NSAL0740_ABMsg abMsg = scrnMsg.A.no(currentCnt);
        clearUplftDetail(abMsg);
        // mod end 2016/07/13 CSA Defect#8608
        // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
        // START 2018/11/16 K.Kitachi [QC#28638, DEL]
//        setEscalationYear(abMsg);
        // END 2018/11/16 K.Kitachi [QC#28638, DEL]
        // END 2017/11/08 M.Kidokoro [QC#21928, ADD]

        List<NSAL0740_ABMsg> lowerList = null;

        if (DS_CONTR_CATG.FLEET.equals(abMsg.dsContrCatgCd_D1.getValue())) {
            lowerList = getLowerLinesForContract(scrnMsg, currentCnt);
        } else {
            if (LINE_LEVEL_CONTRACT.equals(scrnMsg.A.no(currentCnt).dsContrMachLvlNum_D1.getValue())) {
                lowerList = getLowerLinesForContract(scrnMsg, currentCnt);
            } else if (LINE_LEVEL_CONTRACT_DETAIL.equals(scrnMsg.A.no(currentCnt).dsContrMachLvlNum_D1.getValue())) {
                lowerList = getLowerLinesForSer(scrnMsg, currentCnt);
            }
        }
        // mod start 2016/07/13 CSA Defect#8608
        if (lowerList != null && lowerList.size() > 0) {
            setContrAutoEsclTpCd(lowerList, abMsg);
        }

        controlScreenFields(this, scrnMsg);
        if (!ZYPConstant.FLG_ON_Y.equals(abMsg.uplftBaseFlg_D1.getValue()) && !ZYPConstant.FLG_ON_Y.equals(abMsg.uplftUsgFlg_D1.getValue())) {
            scrnMsg.setFocusItem(abMsg.contrUplftTpCd_D3);
        } else {
            scrnMsg.setFocusItem(abMsg.uplftPrcMethCd_D3);
        }
        // mod end 2016/07/13 CSA Defect#8608
    }

    private void setContrAutoEsclTpCd(List<NSAL0740_ABMsg> list, NSAL0740_ABMsg abMsg) {

        for (int i = 0; i < list.size(); i++) {
            NSAL0740_ABMsg targetABMsg = list.get(i);
            // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
            if (LINE_LEVEL_CONTRACT_DETAIL.equals(targetABMsg.dsContrMachLvlNum_D1.getValue())) {
                if (targetABMsg.xxChkBox_S1.isInputProtected()) {
                    continue;
                }
            } else if (LINE_LEVEL_BASE_OVERAGE.equals(targetABMsg.dsContrMachLvlNum_D1.getValue())) {
                if (targetABMsg.xxChkBox_S2.isInputProtected()) {
                    continue;
                }
            }
            // END 2017/11/08 M.Kidokoro [QC#21928, ADD]
            setValue(targetABMsg.contrUplftTpCd_D3, abMsg.contrUplftTpCd_D3);
            // add start 2016/07/13 CSA Defect#8608
            setValue(targetABMsg.uplftBaseFlg_D1, abMsg.uplftBaseFlg_D1);
            setValue(targetABMsg.uplftUsgFlg_D1, abMsg.uplftUsgFlg_D1);
            clearUplftDetail(targetABMsg);
            // add start 2016/07/13 CSA Defect#8608
            // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
            // START 2018/11/16 K.Kitachi [QC#28638, DEL]
//            setEscalationYear(targetABMsg);
            // END 2018/11/16 K.Kitachi [QC#28638, DEL]
            // END 2017/11/08 M.Kidokoro [QC#21928, ADD]
        }
    }

    // add start 2016/07/13 CSA Defect#8608
    private void clearUplftDetail(NSAL0740_ABMsg abMsg) {
        if (!ZYPConstant.FLG_ON_Y.equals(abMsg.uplftBaseFlg_D1.getValue()) && !ZYPConstant.FLG_ON_Y.equals(abMsg.uplftUsgFlg_D1.getValue())) {
            abMsg.uplftPrcMethCd_D3.clear();
            abMsg.uplftBefEndRnwDaysAot_D1.clear();
            // START 2018/11/16 K.Kitachi [QC#28638, MOD]
//            abMsg.xxChkBox_E1.clear();
//            abMsg.xxChkBox_E2.clear();
//            abMsg.xxChkBox_E3.clear();
//            abMsg.xxChkBox_E4.clear();
//            abMsg.xxChkBox_E5.clear();
            abMsg.maxPrcUpRatio_D1.clear();
            abMsg.fixTermInMthAot_D1.clear();
            abMsg.uplftFixedDt_D1.clear();
            abMsg.uplftPcyDt_D1.clear();
            // END 2018/11/16 K.Kitachi [QC#28638, MOD]
        }
        if (!ZYPConstant.FLG_ON_Y.equals(abMsg.uplftBaseFlg_D1.getValue())) {
            abMsg.uplftBasePrcUpRatio_D1.clear();
        }
        if (!ZYPConstant.FLG_ON_Y.equals(abMsg.uplftUsgFlg_D1.getValue())) {
            abMsg.uplftMtrPrcUpRatio_D1.clear();
        }
    }
    // add end 2016/07/13 CSA Defect#8608
    // START 2017/11/08 M.Kidokoro [QC#21928, ADD]
    // START 2018/11/16 K.Kitachi [QC#28638, DEL]
//    private void setEscalationYear(NSAL0740_ABMsg abMsg) {
//        if (CONTR_UPLFT_TP.DO_NOT_UPLIFT.equals(abMsg.contrUplftTpCd_D3.getValue()) || "".equals(abMsg.contrUplftTpCd_D3.getValue())) {
//            abMsg.xxChkBox_E1.clear();
//            abMsg.xxChkBox_E2.clear();
//            abMsg.xxChkBox_E3.clear();
//            abMsg.xxChkBox_E4.clear();
//            abMsg.xxChkBox_E5.clear();
//        } else {
//            setValue(abMsg.xxChkBox_E1, ZYPConstant.CHKBOX_ON_Y);
//            setValue(abMsg.xxChkBox_E2, ZYPConstant.CHKBOX_ON_Y);
//            setValue(abMsg.xxChkBox_E3, ZYPConstant.CHKBOX_ON_Y);
//            setValue(abMsg.xxChkBox_E4, ZYPConstant.CHKBOX_ON_Y);
//            setValue(abMsg.xxChkBox_E5, ZYPConstant.CHKBOX_ON_Y);
//        }
//    }
    // END 2018/11/16 K.Kitachi [QC#28638, DEL]
    // END 2017/11/08 M.Kidokoro [QC#21928, ADD]
}
