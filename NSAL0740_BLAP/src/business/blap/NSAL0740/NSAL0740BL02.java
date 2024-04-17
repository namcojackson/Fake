/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0740;

import static business.blap.NSAL0740.constant.NSAL0740Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0740.common.NSAL0740CommonLogic;
import business.db.CONTR_UPLFT_TPTMsg;
import business.db.DS_CONTRTMsg;
import business.db.PRC_SVC_PLN_TPTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001CalcUplftInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.UpliftInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/27   Hitachi         Y.Takeno        Create          N/A
 * 2015/12/22   Hitachi         T.Tsuchida      Update          QC#1731
 * 2016/02/19   Hitachi         T.Aoyagi        Update          QC3694
 * 2016/03/16   Hitachi         M.Gotou         Update          QC#4090
 * 2016/05/12   Hitachi         T.Tomita        Update          QC#4472
 * 2016/05/17   Hitachi         M.Gotou         Update          QC#4472
 * 2016/07/06   Hitachi         T.Tomita        Update          QC#11031
 * 2016/07/13   Hitachi         A.Kohinata      Update          QC#8608
 * 2016/12/05   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/02/14   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/06/19   Hitachi         T.Mizuki        Update          QC#19256
 * 2017/08/22   CITS            T.Kikuhara      Update          QC#18799(L3)
 * 2017/09/05   CITS            M.Naito         Update          QC#18724
 * 2017/09/08   Hitachi         K.Kim           Update          QC#20880
 * 2017/10/30   Hitachi         M.Kidokoro      Update          QC#21931
 * 2017/11/01   Hitachi         K.Kojima        Update          QC#21931-1
 * 2017/11/02   Hitachi         K.Kojima        Update          QC#21672-1
 * 2018/08/16   Hitachi         K.Kojima        Update          QC#23067
 * 2018/11/16   Hitachi         K.Kitachi       Update          QC#28638
 * 2018/11/28   Hitachi         T.Tomita        Update          QC#28638
 * 2019/01/24   Fujitsu         R.Nakamura      Update          QC#29782
 *</pre>
 */
public class NSAL0740BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0740CMsg cMsg = (NSAL0740CMsg) arg0;
        NSAL0740SMsg sMsg = (NSAL0740SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0740_INIT".equals(screenAplID)) {
                doProcess_NSAL0740_INIT(cMsg, sMsg);

            } else if ("NSAL0740Scrn00_ApplyToAll".equals(screenAplID)) {
                doProcess_NSAL0740Scrn00_ApplyToAll(cMsg, sMsg);

            } else if ("NSAL0740Scrn00_Collapse".equals(screenAplID)) {
                doProcess_NSAL0740Scrn00_Collapse(cMsg, sMsg);
            // del start 2016/05/17 CSA Defect#4472
            //} else if ("NSAL0740Scrn00_CMN_Submit".equals(screenAplID)) {
            //    doProcess_NSAL0740Scrn00_CMN_Submit(cMsg, sMsg);
            // del end 2016/05/17 CSA Defect#4472
            // START 2017/02/14 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0740Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0740Scrn00_CMN_Reset(cMsg, sMsg);
            // END   2017/02/14 K.Ochiai [QC#16331, MOD]
            // add start 2016/07/13 CSA Defect#8608
            } else if ("NSAL0740Scrn00_OnChangeNewEsclTpCd".equals(screenAplID)) {
                doProcess_NSAL0740Scrn00_OnChangeNewEsclTpCd(cMsg, sMsg);
            } else if ("NSAL0740Scrn00_OnChangeEsclTpCd".equals(screenAplID)) {
                doProcess_NSAL0740Scrn00_OnChangeEsclTpCd(cMsg, sMsg);
            // add end 2016/07/13 CSA Defect#8608
            // mod start 2016/12/05 CSA QC#4210
            } else if ("NSAL0740Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0740Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0740Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0740Scrn00_PagePrev(cMsg, sMsg);
            // mod end 2016/12/05 CSA QC#4210
            // START 2018/11/16 K.Kitachi [QC#28638, ADD]
            } else if ("NSAL0740Scrn00_OnChangeFixMth".equals(screenAplID)) {
                doProcess_NSAL0740Scrn00_OnChangeFixMth(cMsg, sMsg);
            } else if ("NSAL0740Scrn00_OnChangeHldPrcUtilDt".equals(screenAplID)) {
                doProcess_NSAL0740Scrn00_OnChangeHldPrcUtilDt(cMsg, sMsg);
            // END 2018/11/16 K.Kitachi [QC#28638, ADD]
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0740_INIT(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {
        init(cMsg, sMsg);
    }

// mod start 2016/05/17 CSA Defect#4472
    private void doProcess_NSAL0740Scrn00_ApplyToAll(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {

        if (!NSAL0740CommonLogic.validateCheckBox(cMsg)) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }
        reflectCMsgToSMsg(cMsg, sMsg);
    }
// mod end 2016/05/17 CSA Defect#4472

    private void doProcess_NSAL0740Scrn00_Collapse(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {

        int selectedCMsgIndex = cMsg.xxRowNum.getValueInt();

        reflectAllCMsgToSMsg(cMsg, sMsg);

        collapseSelectedRow(cMsg, sMsg, selectedCMsgIndex);

        reflectSMsgToCMsg(cMsg, sMsg);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

        NSAL0740CommonLogic.createPullDown(cMsg);
    }
// del start 2016/05/17 CSA Defect#4472
//    private void doProcess_NSAL0740Scrn00_CMN_Submit(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {
//
//        init(cMsg, sMsg);
//    }
// del end 2016/05/17 CSA Defect#4472

    // START 2017/02/14 K.Ochiai [QC#16331, MOD]
    private void doProcess_NSAL0740Scrn00_CMN_Reset(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {
        init(cMsg, sMsg);
    }

    // END 2017/02/14 K.Ochiai [QC#16331, MOD]

    // add start 2016/07/13 CSA Defect#8608
    private void doProcess_NSAL0740Scrn00_OnChangeNewEsclTpCd(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {

        CONTR_UPLFT_TPTMsg contrUplftTpTMsg = NSAL0740CommonLogic.getContrUplftTp(getGlobalCompanyCode(), cMsg.contrUplftTpCd_H3.getValue());
        if (contrUplftTpTMsg != null) {
            setValue(cMsg.uplftBaseFlg_H1, contrUplftTpTMsg.uplftBaseFlg);
            setValue(cMsg.uplftUsgFlg_H1, contrUplftTpTMsg.uplftUsgFlg);
        } else {
            setValue(cMsg.uplftBaseFlg_H1, ZYPConstant.FLG_OFF_N);
            setValue(cMsg.uplftUsgFlg_H1, ZYPConstant.FLG_OFF_N);
        }
    }

    private void doProcess_NSAL0740Scrn00_OnChangeEsclTpCd(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {

        int selectedCMsgIndex = cMsg.xxRowNum.getValueInt();
        NSAL0740_ACMsg aCMsg = cMsg.A.no(selectedCMsgIndex);

        CONTR_UPLFT_TPTMsg contrUplftTpTMsg = NSAL0740CommonLogic.getContrUplftTp(getGlobalCompanyCode(), aCMsg.contrUplftTpCd_D3.getValue());
        if (contrUplftTpTMsg != null) {
            setValue(aCMsg.uplftBaseFlg_D1, contrUplftTpTMsg.uplftBaseFlg);
            setValue(aCMsg.uplftUsgFlg_D1, contrUplftTpTMsg.uplftUsgFlg);
        } else {
            setValue(aCMsg.uplftBaseFlg_D1, ZYPConstant.FLG_OFF_N);
            setValue(aCMsg.uplftUsgFlg_D1, ZYPConstant.FLG_OFF_N);
        }
    }
    // add end 2016/07/13 CSA Defect#8608

    private void init(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {

        clearScreen(cMsg);

        findContractInfo(cMsg, sMsg);

        // START 2017/11/02 K.Kojima [QC#21672-1,MOD]
        // setupSMsg(sMsg);
        setupSMsg(sMsg, cMsg.glblCmpyCd.getValue());
        // END 2017/11/02 K.Kojima [QC#21672-1,MOD]

        setupCMsg(cMsg, sMsg);

        NSAL0740CommonLogic.createPullDown(cMsg);

        // QC#18799 ADD START
        // START 2017/11/01 K.Kojima [QC#21931-1,DEL]
        // setupDefaulRule(cMsg);
        // doProcess_NSAL0740Scrn00_OnChangeNewEsclTpCd(cMsg, sMsg);
        // END 2017/11/01 K.Kojima [QC#21931-1,DEL]
        // QC#18799 ADD END


    }

    private void clearScreen(NSAL0740CMsg cMsg) {

        cMsg.contrUplftTpCd_H3.clear();
        cMsg.uplftPrcMethCd_H3.clear();
        cMsg.uplftBasePrcUpRatio_H1.clear();
        cMsg.uplftMtrPrcUpRatio_H1.clear();
        cMsg.svcMemoRsnCd_H3.clear();
        cMsg.svcCmntTxt_H1.clear();

        cMsg.xxChkBox_X1.clear();
        cMsg.xxChkBox_X2.clear();
        // START 2018/11/16 K.Kitachi [QC#28638, DEL]
//        cMsg.xxChkBox_Y1.clear();
//        cMsg.xxChkBox_Y2.clear();
//        cMsg.xxChkBox_Y3.clear();
//        cMsg.xxChkBox_Y4.clear();
//        cMsg.xxChkBox_Y5.clear();
        // END 2018/11/16 K.Kitachi [QC#28638, DEL]

        ZYPTableUtil.clear(cMsg.A);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
    }

    private void findContractInfo(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        BigDecimal[] dsContrPkList = new BigDecimal[cMsg.P.getValidCount()];
        // START 2016/02/19 T.Aoyagi [QC3694, MOD]
        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>(cMsg.P.getValidCount());

        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            dsContrPkList[i] = cMsg.P.no(i).dsContrPk_P1.getValue();
            if (ZYPCommonFunc.hasValue(cMsg.P.no(i).dsContrDtlPk_P1)) {
                dsContrDtlPkList.add(cMsg.P.no(i).dsContrDtlPk_P1.getValue());
            }
            // add start 2016/03/16 CSA Defect#4090
            DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrPk, cMsg.P.no(i).dsContrPk_P1);
            DS_CONTRTMsg findTMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(dsContrTMsg);
            cMsg.P.no(i).contrEffFromDt_P1.setValue(findTMsg.contrVrsnEffFromDt.getValue().toString());
            cMsg.P.no(i).contrEffThruDt_P1.setValue(findTMsg.contrVrsnEffThruDt.getValue().toString());
            // START 2017/09/05 M.Naito [QC#18724, ADD]
            if (ZYPCommonFunc.hasValue(findTMsg.prcSvcPlnTpCd)) {
                PRC_SVC_PLN_TPTMsg prcSvcPlnTpTMsg = NSAL0740Query.getInstance().getPrcSvcPlnTpInfo(cMsg.glblCmpyCd.getValue(), findTMsg.prcSvcPlnTpCd.getValue());
                cMsg.P.no(i).contrFixedYearsAot_P1.setValue(prcSvcPlnTpTMsg.contrFixedYearsAot.getValue());
            }
            // END 2017/09/05 M.Naito [QC#18724, ADD]

            // del start 2016/07/06 CSA Defect#11031
//            int durCnt = getContrDuration(cMsg.P.no(i).contrEffFromDt_P1.getValue().toString(), cMsg.P.no(i).contrEffThruDt_P1.getValue().toString());
//            cMsg.P.no(i).contrDurnAot_P1.setValue(durCnt);
            // del end 2016/07/06 CSA Defect#11031
            // add end 2016/03/16 CSA Defect#4090
        }

        queryMap.put("dsContrPkList", dsContrPkList);
        if (!dsContrDtlPkList.isEmpty()) {
            queryMap.put("existContrDtlPk", ZYPConstant.FLG_ON_Y);
            queryMap.put("dsContrDtlPkList", dsContrDtlPkList);
            queryMap.put("dsContrDtlTpCdAgg", DS_CONTR_DTL_TP.AGGREGATE);
        }
        // END 2016/02/19 T.Aoyagi [QC3694, MOD]

        queryMap.put("dsContrBllgMtrStsList", new String[] {DS_CONTR_DTL_STS.EXPIRED, DS_CONTR_DTL_STS.CANCELLED, DS_CONTR_DTL_STS.TERMINATED });
        queryMap.put("dsContrCatgCd", DS_CONTR_CATG.FLEET);
        queryMap.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.FLEET);
        queryMap.put("dsContrDtlTpCd2", DS_CONTR_DTL_TP.ACCESSORIES);
        // Add Start 2018/11/28 QC#28638
        queryMap.put("dsContrCatgCdAgg", DS_CONTR_CATG.AGGREGATE);
        queryMap.put("dsContrDtlTpCdAgg", DS_CONTR_DTL_TP.AGGREGATE);
        // Add End 2018/11/28 QC#28638
        queryMap.put("dsContrBaseUsgNm1", BASE);
        queryMap.put("dsContrBaseUsgNm2", OVERAGE);
        queryMap.put("dsContrMachLvlNum1", LINE_LEVEL_CONTRACT);
        queryMap.put("dsContrMachLvlNum2", LINE_LEVEL_CONTRACT_DETAIL);
        queryMap.put("dsContrMachLvlNum3", LINE_LEVEL_BASE_OVERAGE);
        queryMap.put("flgOffN", ZYPConstant.FLG_OFF_N);
        // START 2017/06/19 T.Mizuki [QC#19256,ADD]
        queryMap.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        // END 2017/06/19 T.Mizuki [QC#19256,ADD]
        // Add Start 2019/01/24 QC#29782
        queryMap.put("dsContrCratTp", DS_CONTR_CRAT_TP.OTHER);
        // Add End 2019/01/24 QC#29782

        S21SsmEZDResult ssmResult = NSAL0740Query.getInstance().getContrInfo(queryMap, cMsg.A, sMsg.A);
        if (ssmResult.getQueryResultCount() < sMsg.A.length()) {
            sMsg.A.setValidCount(ssmResult.getQueryResultCount());
        } else {
            sMsg.A.setValidCount(sMsg.A.length());
            cMsg.setMessageInfo(NZZM0001W);
            return;
        }

        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NZZM0000E);
            return;
        }
        return;
    }

    // mod start 2016/05/12 CSA Defect#4472
    // START 2017/11/02 K.Kojima [QC#21672-1,MOD]
    // private void setupSMsg(NSAL0740SMsg sMsg) {
    private void setupSMsg(NSAL0740SMsg sMsg, String glblCmpyCd) {
    // END 2017/11/02 K.Kojima [QC#21672-1,MOD]

        NSAL0740_ASMsg contrASMsg = null;
        NSAL0740_ASMsg serASMsg = null;
        for (int index = 0; index < sMsg.A.getValidCount(); index++) {
            NSAL0740_ASMsg current = sMsg.A.no(index);

            setValue(current.xxRowNum_D1, new BigDecimal(index));

            // add start 2016/07/13 CSA Defect#8608
            CONTR_UPLFT_TPTMsg contrUplftTpTMsg =  NSAL0740CommonLogic.getContrUplftTp(getGlobalCompanyCode(), current.contrUplftTpCd_D3.getValue());
            if (contrUplftTpTMsg != null) {
                setValue(current.uplftBaseFlg_D1, contrUplftTpTMsg.uplftBaseFlg);
                setValue(current.uplftUsgFlg_D1, contrUplftTpTMsg.uplftUsgFlg);
            } else {
                setValue(current.uplftBaseFlg_D1, ZYPConstant.FLG_OFF_N);
                setValue(current.uplftUsgFlg_D1, ZYPConstant.FLG_OFF_N);
            }
            // add end 2016/07/13 CSA Defect#8608

            if (LINE_LEVEL_CONTRACT.equals(current.dsContrMachLvlNum_D1.getValue())) {
                // contract level line
                // START 2017/10/30 M.Kidokoro [QC#21931, MOD]
//                setValue(current.xxBtnFlg_D1, ZYPConstant.FLG_OFF_N);
                setValue(current.xxBtnFlg_D1, ZYPConstant.FLG_ON_Y);
                // END 2017/10/30 M.Kidokoro [QC#21931, MOD]
                setValue(current.xxDplyCtrlFlg_D1, ZYPConstant.FLG_ON_Y);

                // START 2017/11/02 K.Kojima [QC#21672-1,ADD]
                setValue(current.xxNum_Y, getContrDurationForHeader(glblCmpyCd, current.dsContrPk_D1.getValue()));
                // END 2017/11/02 K.Kojima [QC#21672-1,ADD]

                contrASMsg = new NSAL0740_ASMsg();
                EZDMsg.copy(current, null, contrASMsg, null);
                serASMsg = null;
            }

            if (LINE_LEVEL_CONTRACT_DETAIL.equals(current.dsContrMachLvlNum_D1.getValue())) {
                // contract detail level line
                // START 2017/10/30 M.Kidokoro [QC#21931, MOD]
//                setValue(current.xxBtnFlg_D1, ZYPConstant.FLG_OFF_N);
//                setValue(current.xxDplyCtrlFlg_D1, ZYPConstant.FLG_OFF_N);
                setValue(current.xxBtnFlg_D1, ZYPConstant.FLG_ON_Y);
                setValue(current.xxDplyCtrlFlg_D1, ZYPConstant.FLG_ON_Y);
                // END 2017/10/30 M.Kidokoro [QC#21931, MOD]

                // Copy Upper Data
                // START 2018/08/16 K.Kojima [QC#23067,MOD]
                // if (!ZYPCommonFunc.hasValue(current.dsContrRnwEsclPk_D1)) {
                if (!ZYPCommonFunc.hasValue(current.contrUplftTpCd_D3)) {
                // END 2018/08/16 K.Kojima [QC#23067,MOD]
                    setLowerData(contrASMsg, null, current);
                }

                serASMsg = new NSAL0740_ASMsg();
                EZDMsg.copy(current, null, serASMsg, null);
            }

            if (LINE_LEVEL_BASE_OVERAGE.equals(current.dsContrMachLvlNum_D1.getValue())) {
                // base/overage level line
                // START 2017/10/30 M.Kidokoro [QC#21931, MOD]
//                setValue(current.xxBtnFlg_D1, ZYPConstant.FLG_OFF_N);
//                setValue(current.xxDplyCtrlFlg_D1, ZYPConstant.FLG_OFF_N);
                setValue(current.xxBtnFlg_D1, ZYPConstant.FLG_ON_Y);
                setValue(current.xxDplyCtrlFlg_D1, ZYPConstant.FLG_ON_Y);
                // END 2017/10/30 M.Kidokoro [QC#21931, MOD]

                // Copy Upper Data
                // START 2018/08/16 K.Kojima [QC#23067,MOD]
                // if (!ZYPCommonFunc.hasValue(current.dsContrRnwEsclPk_D1)) {
                if (!ZYPCommonFunc.hasValue(current.contrUplftTpCd_D3)) {
                // END 2018/08/16 K.Kojima [QC#23067,MOD]
                    setLowerData(contrASMsg, serASMsg, current);
                }
            }
        }
    }
    // mod end 2016/05/12 CSA Defect#4472

    private void setupCMsg(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {
        // mod start 2016/12/05 CSA QC#4210
        reflectSMsgToCMsg(cMsg, sMsg, 0);
//        reflectSMsgToCMsg(cMsg, sMsg);
        // mod end 2016/12/05 CSA QC#4210
    }

    private void collapseSelectedRow(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg, int selectedCMsgIndex) {

        NSAL0740_ACMsg aCMsg = cMsg.A.no(selectedCMsgIndex);
        BigDecimal cMsgDsContrPk = aCMsg.dsContrPk_D1.getValue();
        BigDecimal cMsgDsContrDtlPk = aCMsg.dsContrDtlPk_D1.getValue();

        String levelNumber = aCMsg.dsContrMachLvlNum_D1.getValue();
        int sIndexStart = cMsg.A.no(selectedCMsgIndex).xxRowNum_D1.getValueInt();

        // update button flag
        toggleButtonFlag(aCMsg, sMsg.A.no(sIndexStart));

        // update display control flag
        for (int sIndex = sIndexStart + 1; sIndex < sMsg.A.getValidCount(); sIndex++) {

            NSAL0740_ASMsg aSMsg = sMsg.A.no(sIndex);

            BigDecimal sMsgDsContrPk = aSMsg.dsContrPk_D1.getValue();
            BigDecimal sMsgDsContrDtlPk = aSMsg.dsContrDtlPk_D1.getValue();
            String sMsgLevelNumber = aSMsg.dsContrMachLvlNum_D1.getValue();

            if (LINE_LEVEL_CONTRACT.equals(levelNumber)) {

                if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(sIndexStart).xxBtnFlg_D1.getValue())) {
                    if (cMsgDsContrPk.compareTo(sMsgDsContrPk) == 0) {
                        setValue(aSMsg.xxDplyCtrlFlg_D1, ZYPConstant.FLG_OFF_N);
                    }
                } else {
                    if (cMsgDsContrPk.compareTo(sMsgDsContrPk) == 0) {
                        if (LINE_LEVEL_CONTRACT_DETAIL.equals(sMsgLevelNumber)) {
                            toggleDisplayCtrlFlag(aCMsg, aSMsg);
                        }
                    } else {
                        break;
                    }
                }

            } else if (LINE_LEVEL_CONTRACT_DETAIL.equals(levelNumber)) {
                if (cMsgDsContrPk.compareTo(sMsgDsContrPk) == 0 && cMsgDsContrDtlPk.compareTo(sMsgDsContrDtlPk) == 0) {
                    toggleDisplayCtrlFlag(aCMsg, aSMsg);

                } else {
                    break;
                }
            }
        }
    }

    private void toggleButtonFlag(NSAL0740_ACMsg aCMsg, NSAL0740_ASMsg aSMsg) {

        String buttonFlag = aSMsg.xxBtnFlg_D1.getValue();

        if (ZYPConstant.FLG_ON_Y.equals(buttonFlag)) {
            setValue(aSMsg.xxBtnFlg_D1, ZYPConstant.FLG_OFF_N);

        } else {
            setValue(aSMsg.xxBtnFlg_D1, ZYPConstant.FLG_ON_Y);

        }
    }

    private void toggleDisplayCtrlFlag(NSAL0740_ACMsg aCMsg, NSAL0740_ASMsg aSMsg) {

        String displayCtrlFlag = aSMsg.xxDplyCtrlFlg_D1.getValue();

        if (ZYPConstant.FLG_ON_Y.equals(displayCtrlFlag)) {
            setValue(aSMsg.xxDplyCtrlFlg_D1, ZYPConstant.FLG_OFF_N);

        } else {
            setValue(aSMsg.xxDplyCtrlFlg_D1, ZYPConstant.FLG_ON_Y);

        }
    }

    private void reflectCMsgToSMsg(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {
        for (int cIndex = 0; cIndex < cMsg.A.getValidCount(); cIndex++) {
            int sIndex = cMsg.A.no(cIndex).xxRowNum_D1.getValueInt();

            NSAL0740_ASMsg aSMsg = sMsg.A.no(sIndex);
            NSAL0740_ACMsg aCMsg = cMsg.A.no(cIndex);
// mod start 2016/12/05 CSA QC#4210
//            String levelNumber = aCMsg.dsContrMachLvlNum_D1.getValue();
//            if (LINE_LEVEL_CONTRACT.equals(levelNumber) || LINE_LEVEL_CONTRACT_DETAIL.equals(levelNumber)) {
//
//                if (ZYPConstant.FLG_ON_Y.equals(aCMsg.xxChkBox_S1.getValue())) {
//                    EZDMsg.copy(aCMsg, null, aSMsg, null);
//                }
//
//            } else if (LINE_LEVEL_BASE_OVERAGE.equals(levelNumber)) {
//                if (ZYPConstant.FLG_ON_Y.equals(aCMsg.xxChkBox_S2.getValue())) {
            EZDMsg.copy(aCMsg, null, aSMsg, null);
//                }
//            }
// mod end 2016/12/05 CSA QC#4210
        }
    }

    private void reflectAllCMsgToSMsg(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {

        NSAL0740_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0740_ACMsg acMsg = acMsgArray.no(i);
            int targetIdxNum = acMsg.xxRowNum_D1.getValueInt();

            NSAL0740_ASMsg asMsg = sMsg.A.no(targetIdxNum);
            EZDMsg.copy(acMsg, null, asMsg, null);
        }
    }

    private void reflectSMsgToCMsg(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {

        int aCMsgCount = 0;
        // mod start 2016/12/05 CSA QC#4210
        int sumCnt = 0;
        for (int sIndex = 0; sIndex < sMsg.A.getValidCount(); sIndex++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(sIndex).xxDplyCtrlFlg_D1.getValue())) {
                sumCnt++;
                if (sumCnt >= cMsg.xxPageShowFromNum.getValueInt() && aCMsgCount < cMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(sIndex), null, cMsg.A.no(aCMsgCount), null);
                    aCMsgCount++;
                }
            }
        }

        cMsg.A.setValidCount(aCMsgCount);
        cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.getValidCount() - 1);
        cMsg.xxPageShowOfNum.setValue(sumCnt);
    }

    private void reflectSMsgToCMsg(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg, int pagenationFrom) {

        int cIndex = 0;
        int sumCnt = 0;
        for (int sIndex = pagenationFrom; sIndex < pagenationFrom + sMsg.A.getValidCount(); sIndex++) {

            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(sIndex).xxDplyCtrlFlg_D1.getValue())) {
                if (cIndex < cMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(sIndex), null, cMsg.A.no(cIndex), null);
                    cIndex++;
                }
                sumCnt++;
            }
        }
        cMsg.A.setValidCount(cIndex);
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sumCnt);
        // mod end 2016/12/05 CSA QC#4210
    }

    // add start 2016/03/16 CSA Defect#4090
    private static int getContrDuration(String contrEffFromDt, String contrEffThruDt) {

        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date startDt;
        try {
            startDt = df.parse(contrEffFromDt);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        String endDate = contrEffThruDt;

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDt);
        String calcEndDate = "";
        int durCnt = 1;
        while (endDate.compareTo(calcEndDate) > 0) {
            cal.add(Calendar.YEAR, 1);
            calcEndDate = df.format(cal.getTime());
            Date extDt;
            try {
                extDt = df.parse(calcEndDate);
            } catch (ParseException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            cal.setTime(extDt);
            durCnt++;
        }
        return (durCnt - 1);
    }
    // add end 2016/03/16 CSA Defect#4090

    // START 2017/11/02 K.Kojima [QC#21672-1,ADD]
    private static BigDecimal getContrDurationForHeader(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrPk, dsContrPk);
        DS_CONTRTMsg findTMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(dsContrTMsg);
        if (findTMsg == null) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(getContrDuration(findTMsg.contrVrsnEffFromDt.getValue(), findTMsg.contrVrsnEffThruDt.getValue()));
    }

    // END 2017/11/02 K.Kojima [QC#21672-1,ADD]

    // add start 2016/05/12 CSA Defect#4472
    private void setLowerData(NSAL0740_ASMsg contrASMsg, NSAL0740_ASMsg serASMsg, NSAL0740_ASMsg current) {
        String dsContrCtrlStsCd = current.dsContrDtlStsCd_D1.getValue();
        if (DS_CONTR_DTL_STS.TERMINATED.equals(dsContrCtrlStsCd) || DS_CONTR_DTL_STS.EXPIRED.equals(dsContrCtrlStsCd) || DS_CONTR_DTL_STS.CANCELLED.equals(dsContrCtrlStsCd)) {
            return;
        }
        // mod start 2016/07/06 CSA Defect#11031
        int durCnt = 0;
        if (serASMsg != null) {
            // Base Overage Line
            setValue(current.contrUplftTpCd_D3, serASMsg.contrUplftTpCd_D3);
            // add start 2016/07/13 CSA Defect#8608
            setValue(current.uplftBaseFlg_D1, serASMsg.uplftBaseFlg_D1);
            setValue(current.uplftUsgFlg_D1, serASMsg.uplftUsgFlg_D1);
            // add end 2016/07/13 CSA Defect#8608
            setValue(current.uplftPrcMethCd_D3, serASMsg.uplftPrcMethCd_D3);
            durCnt = getContrDuration(serASMsg.contrEffFromDt_D1.getValue(), serASMsg.contrEffThruDt_D1.getValue());
            if (durCnt == 1) {
                setValue(current.contrUplftTpCd_D3, CONTR_UPLFT_TP.DO_NOT_UPLIFT);
                // add start 2016/07/13 CSA Defect#8608
                setValue(current.uplftBaseFlg_D1, ZYPConstant.FLG_OFF_N);
                setValue(current.uplftUsgFlg_D1, ZYPConstant.FLG_OFF_N);
                // add end 2016/07/13 CSA Defect#8608
                current.uplftPrcMethCd_D3.clear();
            }

            if (LINE_LEVEL_BASE_OVERAGE.equals(current.dsContrMachLvlNum_D1.getValue()) && BASE.equals(current.dsContrBaseUsgNm_D1.getValue())) {
                current.uplftBefEndRnwDaysAot_D1.clear();
                setValue(current.uplftBasePrcUpRatio_D1, serASMsg.uplftBasePrcUpRatio_D1);
                current.uplftMtrPrcUpRatio_D1.clear();
            } else if (LINE_LEVEL_BASE_OVERAGE.equals(current.dsContrMachLvlNum_D1.getValue()) && OVERAGE.equals(current.dsContrBaseUsgNm_D1.getValue())) {
                current.uplftBefEndRnwDaysAot_D1.clear();
                current.uplftBasePrcUpRatio_D1.clear();
                setValue(current.uplftMtrPrcUpRatio_D1, serASMsg.uplftMtrPrcUpRatio_D1);
            } else {
                setValue(current.uplftBefEndRnwDaysAot_D1, serASMsg.uplftBefEndRnwDaysAot_D1);
                setValue(current.uplftBasePrcUpRatio_D1, serASMsg.uplftBasePrcUpRatio_D1);
                setValue(current.uplftMtrPrcUpRatio_D1, serASMsg.uplftMtrPrcUpRatio_D1);
            }
            // START 2017/09/08 K.Kim [QC#20880, MOD]
//            setValue(current.xxChkBox_E1, serASMsg.xxChkBox_E1);
//            setValue(current.xxChkBox_E2, serASMsg.xxChkBox_E2);
//            setValue(current.xxChkBox_E3, serASMsg.xxChkBox_E3);
//            setValue(current.xxChkBox_E4, serASMsg.xxChkBox_E4);
//            setValue(current.xxChkBox_E5, serASMsg.xxChkBox_E5);
            // START 2018/11/16 K.Kitachi [QC#28638, DEL]
//            if (contrASMsg != null) {
//              setValue(current.xxChkBox_E1, contrASMsg.xxChkBox_E1);
//              setValue(current.xxChkBox_E2, contrASMsg.xxChkBox_E2);
//              setValue(current.xxChkBox_E3, contrASMsg.xxChkBox_E3);
//              setValue(current.xxChkBox_E4, contrASMsg.xxChkBox_E4);
//              setValue(current.xxChkBox_E5, contrASMsg.xxChkBox_E5);
//            }
            // END 2018/11/16 K.Kitachi [QC#28638, DEL]
            // END 2017/09/08 K.Kim [QC#20880, MOD]
            // Add Start 2018/11/28 QC#28638
            setValue(current.maxPrcUpRatio_D1, serASMsg.maxPrcUpRatio_D1);
            setValue(current.fixTermInMthAot_D1, serASMsg.fixTermInMthAot_D1);
            setValue(current.uplftFixedDt_D1, serASMsg.uplftFixedDt_D1);
            setValue(current.uplftPcyDt_D1, serASMsg.uplftPcyDt_D1);
            // Add End 2018/11/28 QC#28638
        } else if (contrASMsg != null) {
            // Machine Line
            setValue(current.contrUplftTpCd_D3, contrASMsg.contrUplftTpCd_D3);
            // add start 2016/07/13 CSA Defect#8608
            setValue(current.uplftBaseFlg_D1, contrASMsg.uplftBaseFlg_D1);
            setValue(current.uplftUsgFlg_D1, contrASMsg.uplftUsgFlg_D1);
            // add end 2016/07/13 CSA Defect#8608
            setValue(current.uplftPrcMethCd_D3, contrASMsg.uplftPrcMethCd_D3);
            durCnt = getContrDuration(current.contrEffFromDt_D1.getValue(), current.contrEffThruDt_D1.getValue());
            if (durCnt == 1) {
                setValue(current.contrUplftTpCd_D3, CONTR_UPLFT_TP.DO_NOT_UPLIFT);
                // add start 2016/07/13 CSA Defect#8608
                setValue(current.uplftBaseFlg_D1, ZYPConstant.FLG_OFF_N);
                setValue(current.uplftUsgFlg_D1, ZYPConstant.FLG_OFF_N);
                // add end 2016/07/13 CSA Defect#8608
                current.uplftPrcMethCd_D3.clear();
            }

            if (LINE_LEVEL_BASE_OVERAGE.equals(current.dsContrMachLvlNum_D1.getValue()) && BASE.equals(current.dsContrBaseUsgNm_D1.getValue())) {
                current.uplftBefEndRnwDaysAot_D1.clear();
                setValue(current.uplftBasePrcUpRatio_D1, contrASMsg.uplftBasePrcUpRatio_D1);
                current.uplftMtrPrcUpRatio_D1.clear();
            } else if (LINE_LEVEL_BASE_OVERAGE.equals(current.dsContrMachLvlNum_D1.getValue()) && OVERAGE.equals(current.dsContrBaseUsgNm_D1.getValue())) {
                current.uplftBefEndRnwDaysAot_D1.clear();
                current.uplftBasePrcUpRatio_D1.clear();
                setValue(current.uplftMtrPrcUpRatio_D1, contrASMsg.uplftMtrPrcUpRatio_D1);
            } else {
                setValue(current.uplftBefEndRnwDaysAot_D1, contrASMsg.uplftBefEndRnwDaysAot_D1);
                setValue(current.uplftBasePrcUpRatio_D1, contrASMsg.uplftBasePrcUpRatio_D1);
                setValue(current.uplftMtrPrcUpRatio_D1, contrASMsg.uplftMtrPrcUpRatio_D1);
                // Add Start 2018/11/28 QC#28638
                if (DS_CONTR_CATG.AGGREGATE.equals(current.dsContrCatgCd_D1.getValue())) {
                    if (DS_CONTR_DTL_TP.AGGREGATE.equals(current.dsContrDtlTpCd_D1.getValue())) {
                        current.uplftBasePrcUpRatio_D1.clear();
                    } else {
                        current.uplftMtrPrcUpRatio_D1.clear();
                    }
                }
                // Add End 2018/11/28 QC#28638
            }
            // START 2018/11/16 K.Kitachi [QC#28638, DEL]
//            setValue(current.xxChkBox_E1, contrASMsg.xxChkBox_E1);
//            setValue(current.xxChkBox_E2, contrASMsg.xxChkBox_E2);
//            setValue(current.xxChkBox_E3, contrASMsg.xxChkBox_E3);
//            setValue(current.xxChkBox_E4, contrASMsg.xxChkBox_E4);
//            setValue(current.xxChkBox_E5, contrASMsg.xxChkBox_E5);
            // END 2018/11/16 K.Kitachi [QC#28638, DEL]
            // Add Start 2018/11/28 QC#28638
            setValue(current.maxPrcUpRatio_D1, contrASMsg.maxPrcUpRatio_D1);
            setValue(current.fixTermInMthAot_D1, contrASMsg.fixTermInMthAot_D1);
            setValue(current.uplftFixedDt_D1, contrASMsg.uplftFixedDt_D1);
            setValue(current.uplftPcyDt_D1, contrASMsg.uplftPcyDt_D1);
            // Add End 2018/11/28 QC#28638
        }
        // mod end 2016/07/06 CSA Defect#11031
    }
    // add end 2016/05/12 CSA Defect#4472

    // mod start 2016/12/05 CSA QC#4210
    private void doProcess_NSAL0740Scrn00_PageNext(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {

        reflectCMsgToSMsg(cMsg, sMsg);
        int aCMsgCount = 0;
        int sumCnt = 0;
        for (int sIndex = 0; sIndex < sMsg.A.getValidCount(); sIndex++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(sIndex).xxDplyCtrlFlg_D1.getValue())) {
                sumCnt++;
                if (sumCnt > cMsg.xxPageShowToNum.getValueInt() && aCMsgCount < cMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(sIndex), null, cMsg.A.no(aCMsgCount), null);
                    aCMsgCount++;
                }
            }
        }

        cMsg.A.setValidCount(aCMsgCount);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt() + 1);
        cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + aCMsgCount - 1);
    }

    private void doProcess_NSAL0740Scrn00_PagePrev(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {

        reflectCMsgToSMsg(cMsg, sMsg);
        int aCMsgCount = 0;
        int sumCnt = 0;
        for (int sIndex = 0; sIndex < sMsg.A.getValidCount(); sIndex++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(sIndex).xxDplyCtrlFlg_D1.getValue())) {
                sumCnt++;
                if (sumCnt >= cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() && aCMsgCount < cMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(sIndex), null, cMsg.A.no(aCMsgCount), null);
                    aCMsgCount++;
                }
            }
        }

        cMsg.A.setValidCount(aCMsgCount);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
        cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + aCMsgCount - 1);
    }
    // mod end 2016/12/05 CSA QC#4210

    // QC#18799 ADD START
    // START 2017/11/01 K.Kojima [QC#21931-1,DEL]
    // private void setupDefaulRule(NSAL0740CMsg cMsg) {
    // 
    //     BigDecimal dsContrPk = cMsg.P.no(0).dsContrPk_P1.getValue();
    //     for (int i = 0; i < cMsg.P.getValidCount(); i++) {
    //         if (dsContrPk.compareTo(cMsg.P.no(i).dsContrPk_P1.getValue()) != 0) {
    //             return;
    //         }
    //     }
    // 
    //     DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
    //     ZYPEZDItemValueSetter.setValue(dsContrTMsg.glblCmpyCd, cMsg.glblCmpyCd);
    //     ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrPk, cMsg.P.no(0).dsContrPk_P1);
    //     DS_CONTRTMsg findTMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(dsContrTMsg);
    // 
    //     if (findTMsg == null) {
    //         return;
    //     }
    // 
    //     DS_CONTR_INTFC_DEF_RULETMsg inMsg = new DS_CONTR_INTFC_DEF_RULETMsg();
    //     ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
    //     ZYPEZDItemValueSetter.setValue(inMsg.contrIntfcSrcTpCd, findTMsg.dsContrSrcTpCd.getValue());
    //     ZYPEZDItemValueSetter.setValue(inMsg.dsContrClsCd, findTMsg.dsContrClsCd.getValue());
    //     ZYPEZDItemValueSetter.setValue(inMsg.svcLineBizCd, findTMsg.svcLineBizCd.getValue());
    // 
    //     // START 2017/10/30 M.Kidokoro [QC#21931, DEL]
    //     DS_CONTR_INTFC_DEF_RULETMsg outMsg =  (DS_CONTR_INTFC_DEF_RULETMsg) EZDTBLAccessor.findByKey(inMsg);
    //     if (outMsg != null) {
    //         ZYPEZDItemValueSetter.setValue(cMsg.contrUplftTpCd_H3, outMsg.contrUplftTpCd);
    //         ZYPEZDItemValueSetter.setValue(cMsg.uplftPrcMethCd_H3, outMsg.uplftPrcMethCd);
    //         ZYPEZDItemValueSetter.setValue(cMsg.uplftBasePrcUpRatio_H1, outMsg.uplftBasePrcUpRatio);
    //         ZYPEZDItemValueSetter.setValue(cMsg.uplftMtrPrcUpRatio_H1, outMsg.uplftMtrPrcUpRatio);
    //         ZYPEZDItemValueSetter.setValue(cMsg.befEndUplftDaysAot_H1, outMsg.befEndUplftDaysAot);
    //     }
    //     // END 2017/10/30 M.Kidokoro [QC#21931, DEL]
    // }
    // END 2017/11/01 K.Kojima [QC#21931-1,DEL]
    // QC#18799 ADD END

    // START 2018/11/16 K.Kitachi [QC#28638, ADD]
    private void doProcess_NSAL0740Scrn00_OnChangeFixMth(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {

        int idx = cMsg.xxRowNum.getValueInt();
        NSAL0740_ACMsg aCMsg = cMsg.A.no(idx);

        UpliftInfoBean bean = new UpliftInfoBean();
        bean.setContrEffFromDt(aCMsg.contrEffFromDt_BK.getValue());
        bean.setContrEffThruDt(aCMsg.contrEffThruDt_BK.getValue());
        bean.setFixTermInMthAot(aCMsg.fixTermInMthAot_D1.getValue());
        NSXC001001CalcUplftInfo.calcUplftInfoByChngFixTermInMthAot(bean);
        String uplftFixedDt = bean.getUplftFixedDt();
        String uplftPcyDt = bean.getUplftPcyDt();

        if (ZYPCommonFunc.hasValue(uplftFixedDt) && ZYPDateUtil.compare(uplftFixedDt, aCMsg.contrEffThruDt_BK.getValue()) > 0) {
            aCMsg.fixTermInMthAot_D1.setErrorInfo(1, NSAM0740E, new String[] {ZYPDateUtil.formatEzd8ToDisp(aCMsg.contrEffFromDt_BK.getValue()), ZYPDateUtil.formatEzd8ToDisp(aCMsg.contrEffThruDt_BK.getValue()) });
            return;
        }

        setValue(aCMsg.uplftFixedDt_D1, uplftFixedDt);
        setValue(aCMsg.uplftPcyDt_D1, uplftPcyDt);
    }

    private void doProcess_NSAL0740Scrn00_OnChangeHldPrcUtilDt(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg) {

        int idx = cMsg.xxRowNum.getValueInt();
        NSAL0740_ACMsg aCMsg = cMsg.A.no(idx);

        UpliftInfoBean bean = new UpliftInfoBean();
        bean.setContrEffFromDt(aCMsg.contrEffFromDt_BK.getValue());
        bean.setContrEffThruDt(aCMsg.contrEffThruDt_BK.getValue());
        bean.setUplftFixedDt(aCMsg.uplftFixedDt_D1.getValue());
        NSXC001001CalcUplftInfo.calcUplftInfoByChngUplftFixedDt(bean);
        BigDecimal fixTermInMthAot = bean.getFixTermInMthAot();
        String uplftPcyDt = bean.getUplftPcyDt();

        if (ZYPDateUtil.compare(aCMsg.uplftFixedDt_D1.getValue(), aCMsg.contrEffFromDt_BK.getValue()) < 0 || ZYPDateUtil.compare(aCMsg.uplftFixedDt_D1.getValue(), aCMsg.contrEffThruDt_BK.getValue()) > 0) {
            aCMsg.uplftFixedDt_D1.setErrorInfo(1, NSAM0741E, new String[] {ZYPDateUtil.formatEzd8ToDisp(aCMsg.contrEffFromDt_BK.getValue()), ZYPDateUtil.formatEzd8ToDisp(aCMsg.contrEffThruDt_BK.getValue()) });
            return;
        }

        setValue(aCMsg.fixTermInMthAot_D1, fixTermInMthAot);
        setValue(aCMsg.uplftPcyDt_D1, uplftPcyDt);

        if (ZYPCommonFunc.hasValue(fixTermInMthAot) && BigDecimal.ZERO.compareTo(fixTermInMthAot) >= 0) {
            aCMsg.fixTermInMthAot_D1.setErrorInfo(1, ZZM9003E, new String[] {"Fixed Month" });
            return;
        }
    }
    // END 2018/11/16 K.Kitachi [QC#28638, ADD]
}
