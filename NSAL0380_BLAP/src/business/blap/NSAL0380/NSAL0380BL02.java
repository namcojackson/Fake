/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0380;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0380.common.NSAL0380CommonLogic;
import business.blap.NSAL0380.constant.NSAL0380Constant;
import business.db.CONTR_AUTO_RNW_TPTMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/24   Fujitsu         T.Yoshida       Create          N/A
 * 2015/12/03   Hitachi         T.Tsuchida      Update          QC#1431
 * 2016/02/15   Hitachi         O.Okuma         Update          QC3029
 * 2016/02/25   Hitachi         O.Okuma         Update          QC3024
 * 2016/03/04   Hitachi         A.Kohinata      Update          QC3024,QC3029
 * 2016/03/07   Hitachi         A.Kohinata      Update          QC5082
 * 2016/03/14   Hitachi         O.Okuma         Update          QC4900
 * 2016/04/06   Hitachi         T.Tomita        Update          QC#3024
 * 2016/07/15   Hitachi         A.Kohinata      Update          QC#8608
 * 2016/09/06   Hitachi         T.Tomita        Update          QC#14210
 * 2017/01/12   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/06/08   Hitachi         Y.Osawa         Update          QC#18470
 * 2017/08/22   CITS            T.Kikuhara      Update          QC#18799(L3)
 * 2017/09/29   Hitachi         K.Kojima        Update          QC#18378
 * 2017/10/30   Hitachi         M.Kidokoro      Update          QC#21931
 * 2017/11/01   Hitachi         K.Kojima        Update          QC#21931-1
 * 2018/12/20   Hitachi         K.Kim           Update          QC#29636
 * 2019/06/26   Hitachi         A.Kohinata      Update          QC#50931
 *</pre>
 */
public class NSAL0380BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0380_INIT".equals(screenAplID)) {
                doProcess_NSAL0380_INIT((NSAL0380CMsg) cMsg, (NSAL0380SMsg) sMsg);
            } else if ("NSAL0380Scrn00_Expansion".equals(screenAplID)) {
                doProcess_NSAL0380Scrn00_Expansion((NSAL0380CMsg) cMsg, (NSAL0380SMsg) sMsg);
            } else if ("NSAL0380Scrn00_Contraction".equals(screenAplID)) {
                doProcess_NSAL0380Scrn00_Contraction((NSAL0380CMsg) cMsg, (NSAL0380SMsg) sMsg);
            } else if ("NSAL0380Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0380Scrn00_PagePrev((NSAL0380CMsg) cMsg, (NSAL0380SMsg) sMsg);
            } else if ("NSAL0380Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0380Scrn00_PageNext((NSAL0380CMsg) cMsg, (NSAL0380SMsg) sMsg);
            // START 2017/01/12 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0380Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0380Scrn00_CMN_Reset((NSAL0380CMsg) cMsg, (NSAL0380SMsg) sMsg);
            // END 2017/01/12 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0380Scrn00_CMN_Return".equals(screenAplID)) {
                doProcess_NSAL0380Scrn00_CMN_Return((NSAL0380CMsg) cMsg, (NSAL0380SMsg) sMsg);
            } else if ("NSAL0380Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0380Scrn00_Search((NSAL0380CMsg) cMsg, (NSAL0380SMsg) sMsg);
            } else if ("NSAL0380Scrn00_AplToAll".equals(screenAplID)) {
                doProcess_NSAL0380Scrn00_AplToAll((NSAL0380CMsg) cMsg, (NSAL0380SMsg) sMsg);
            // add start 2016/07/15 CSA Defect#8608
            } else if ("NSAL0380Scrn00_OnChangeNewRnwTpCd".equals(screenAplID)) {
                doProcess_NSAL0380Scrn00_OnChangeNewRnwTpCd((NSAL0380CMsg) cMsg, (NSAL0380SMsg) sMsg);
            } else if ("NSAL0380Scrn00_OnChangeRnwTpCd".equals(screenAplID)) {
                doProcess_NSAL0380Scrn00_OnChangeRnwTpCd((NSAL0380CMsg) cMsg, (NSAL0380SMsg) sMsg);
            // add end 2016/07/15 CSA Defect#8608
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    private void doProcess_NSAL0380_INIT(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {
        doSearch(cMsg, sMsg);
    }
    /**
     * do process (Search)
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    private void doProcess_NSAL0380Scrn00_Search(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {
        doSearch(cMsg, sMsg);
    }
    /**
     * do process (Expansion)
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    private void doProcess_NSAL0380Scrn00_Expansion(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {

        // get Machine Level Number
        String machLvlNum = getMachLvlNum(cMsg);

        if (NSAL0380Constant.MACH_LVL_NUM_1.equals(machLvlNum)) {
            doExpansion(cMsg, sMsg, true);
        } else {
            doExpansion(cMsg, sMsg, false);
        }

        // set Paging Data
        cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - 1 + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * do process (Contraction)
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    private void doProcess_NSAL0380Scrn00_Contraction(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {

        // get Machine Level Number
        String machLvlNum = getMachLvlNum(cMsg);

        if (NSAL0380Constant.MACH_LVL_NUM_1.equals(machLvlNum)) {
            doContraction(cMsg, sMsg, true);
        } else {
            doContraction(cMsg, sMsg, false);
        }

        // set Paging Data
        cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - 1 + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * do process (Page Prev)
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    private void doProcess_NSAL0380Scrn00_PagePrev(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {

        NSAL0380CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0380_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        copyToASMsgForPaging(sMsg);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - acMsgArray.length() - 1;
        int i = pageFrom;
        for (; i < pageFrom + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageFrom);

        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + acMsgArray.getValidCount() - 1);
    }

    /**
     * do process (Page Next)
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    private void doProcess_NSAL0380Scrn00_PageNext(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {

        NSAL0380CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0380_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        copyToASMsgForPaging(sMsg);

        int pageTo = cMsg.xxPageShowToNum.getValueInt();
        int i = pageTo;
        for (; i < pageTo + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageTo), null);
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageTo);

        cMsg.xxPageShowFromNum.setValue(pageTo + 1);
        cMsg.xxPageShowToNum.setValue(pageTo + acMsgArray.getValidCount());
    }

    /**
     * do process (Apply To All)
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    private void doProcess_NSAL0380Scrn00_AplToAll(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {

        int checkCnt = 0;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSAL0380_ACMsg acMsg = cMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(acMsg.xxChkBox_A1.getValue())
                    || ZYPConstant.CHKBOX_ON_Y.equals(acMsg.xxChkBox_A2.getValue())) {

                setDetailForAplToAll(cMsg, acMsg);

                String xxDplyByCtrlAncrLvlCd = acMsg.xxDplyByCtrlAncrLvlCd_A.getValue();
                String dsContrCatgCd = acMsg.dsContrCatgCd_A.getValue();
                String dsContrDtlTpCd = acMsg.dsContrDtlTpCd_A.getValue();

                if (!NSAL0380Constant.MACH_LVL_NUM_1.equals(xxDplyByCtrlAncrLvlCd)) {
                    if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                        if (DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                            acMsg.basePrcUpRatio_A.clear();
                        } else {
                            acMsg.mtrPrcUpRatio_A.clear();
                        }
                    }
                }

                if (ZYPCommonFunc.hasValue(acMsg.dsContrBaseUsgNm_A)) {
                    acMsg.befEndRnwDaysAot_A.clear();
                    if (NSAL0380Constant.BASE_NM.equals(acMsg.dsContrBaseUsgNm_A.getValue())) {

                        acMsg.mtrPrcUpRatio_A.clear();
                    } else {
                        acMsg.basePrcUpRatio_A.clear();
                    }
                }
                checkCnt++;
            }
        }
        if (checkCnt == 0) {
            cMsg.setMessageInfo(NSAL0380Constant.NSAM0015E);
        } else {
            cMsg.contrAutoRnwTpCd_H.clear();
            // add start 2016/07/15 CSA Defect#8608
            cMsg.rnwBaseFlg_H.clear();
            cMsg.rnwUsgFlg_H.clear();
            // add start 2016/07/15 CSA Defect#8608
            // START 2017/09/29 K.Kojima [QC#18378,ADD]
            cMsg.autoRnwFlg_H.clear();
            // END 2017/09/29 K.Kojima [QC#18378,ADD]
            cMsg.rnwPrcMethCd_H.clear();
            cMsg.befEndRnwDaysAot_H.clear();
            cMsg.maxContrRnwCnt_H.clear();
            cMsg.maxPrcUpRatio_H.clear();
            cMsg.basePrcUpRatio_H.clear();
            cMsg.mtrPrcUpRatio_H.clear();
        }
    }

    // START 2017/01/12 K.Ochiai [QC#16331, MOD]
    /**
     * do process (Reset)
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    private void doProcess_NSAL0380Scrn00_CMN_Reset(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {

        doProcess_NSAL0380_INIT(cMsg, sMsg);
    }
    // END 2017/01/12 K.Ochiai [QC#16331, MOD]

    /**
     * do process (Return)
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    private void doProcess_NSAL0380Scrn00_CMN_Return(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {
    }

    // add start 2016/07/15 CSA Defect#8608
    /**
     * do process (OnChangeNewRnwTpCd)
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    private void doProcess_NSAL0380Scrn00_OnChangeNewRnwTpCd(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {

        CONTR_AUTO_RNW_TPTMsg contrAutoRnwTpTMsg =  NSAL0380CommonLogic.getContrAutoRnwTp(getGlobalCompanyCode(), cMsg.contrAutoRnwTpCd_H.getValue());
        if (contrAutoRnwTpTMsg != null) {
            setValue(cMsg.rnwBaseFlg_H, contrAutoRnwTpTMsg.rnwBaseFlg);
            setValue(cMsg.rnwUsgFlg_H, contrAutoRnwTpTMsg.rnwUsgFlg);
            // START 2017/09/29 K.Kojima [QC#18378,ADD]
            setValue(cMsg.autoRnwFlg_H, contrAutoRnwTpTMsg.autoRnwFlg);
            // END 2017/09/29 K.Kojima [QC#18378,ADD]
        } else {
            setValue(cMsg.rnwBaseFlg_H, ZYPConstant.FLG_OFF_N);
            setValue(cMsg.rnwUsgFlg_H, ZYPConstant.FLG_OFF_N);
            // START 2017/09/29 K.Kojima [QC#18378,ADD]
            setValue(cMsg.autoRnwFlg_H, ZYPConstant.FLG_OFF_N);
            // END 2017/09/29 K.Kojima [QC#18378,ADD]
        }
    }

    /**
     * do process (OnChangeRnwTpCd)
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    private void doProcess_NSAL0380Scrn00_OnChangeRnwTpCd(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {

        NSAL0380_ACMsg acMsg = cMsg.A.no(cMsg.xxNum_EV.getValueInt());
        CONTR_AUTO_RNW_TPTMsg contrAutoRnwTpTMsg =  NSAL0380CommonLogic.getContrAutoRnwTp(getGlobalCompanyCode(), acMsg.contrAutoRnwTpCd_A.getValue());
        if (contrAutoRnwTpTMsg != null) {
            setValue(acMsg.rnwBaseFlg_A, contrAutoRnwTpTMsg.rnwBaseFlg);
            setValue(acMsg.rnwUsgFlg_A, contrAutoRnwTpTMsg.rnwUsgFlg);
            // START 2017/09/29 K.Kojima [QC#18378,ADD]
            setValue(acMsg.autoRnwFlg_A, contrAutoRnwTpTMsg.autoRnwFlg);
            // END 2017/09/29 K.Kojima [QC#18378,ADD]
        } else {
            setValue(acMsg.rnwBaseFlg_A, ZYPConstant.FLG_OFF_N);
            setValue(acMsg.rnwUsgFlg_A, ZYPConstant.FLG_OFF_N);
            // START 2017/09/29 K.Kojima [QC#18378,ADD]
            setValue(acMsg.autoRnwFlg_A, ZYPConstant.FLG_OFF_N);
            // END 2017/09/29 K.Kojima [QC#18378,ADD]
        }
    }
    // add start 2016/07/15 CSA Defect#8608

    /**
     * check Input Parameter
     * @param cMsg NSAL0380CMsg
     * @return No Error : true
     */
    private boolean checkInparam(NSAL0380CMsg cMsg) {

        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(cMsg.P.no(i).dsContrPk_P1)) {
                return true;
            }
        }

        cMsg.setMessageInfo(NSAL0380Constant.NSAM0219E, new String[] {NSAL0380Constant.NO_INPUT_CONTR_PK });
        return false;
    }

    /**
     * create PullDown
     * @param cMsg NSAL0380CMsg
     */
    private void createPullDown(NSAL0380CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(NSAL0380Constant.TBL_CONTR_AUTO_RNW_TP, cMsg.contrAutoRnwTpCd_PL, cMsg.contrAutoRnwTpDescTxt_PL);
        ZYPCodeDataUtil.createPulldownList(NSAL0380Constant.TBL_RNW_PRC_METH, cMsg.rnwPrcMethCd_PL, cMsg.rnwPrcMethDescTxt_PL);
        createSvcMemoRsnPullDown(cMsg);
    }

    private static void createSvcMemoRsnPullDown(NSAL0380CMsg cMsg) {
        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(cMsg.glblCmpyCd.getValue(), SVC_MEMO_TP.RENEW_CONTRACT_OR_MACHINE);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnNm");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_PL, cMsg.svcMemoRsnDescTxt_PL);
    }

    private static SVC_MEMO_RSNTMsgArray getSvcMemoRsnPulldownList(String glblCmpyCd, String svcMemoTpCd) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMemoTpCd01", svcMemoTpCd);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * set To CSMsg
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    private void setToSMsgForInit(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {

        NSAL0380_ASMsgArray asMsgArray = sMsg.A;
        NSAL0380_CSMsgArray csMsgArray = sMsg.C;
        ZYPTableUtil.clear(csMsgArray);

        int indexCnt = 0;

        NSAL0380_ASMsg contrASMsg = null;
        NSAL0380_ASMsg serASMsg = null;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NSAL0380_ASMsg asMsg = asMsgArray.no(i);
            NSAL0380_CSMsg csMsg = csMsgArray.no(i);

            if (NSAL0380Constant.MACH_LVL_NUM_1.equals(asMsg.xxDplyByCtrlAncrLvlCd_A.getValue())) {
                contrASMsg = asMsg;
                serASMsg = null;
                ZYPEZDItemValueSetter.setValue(asMsg.xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(asMsg.xxSmryLineFlg_A, ZYPConstant.FLG_ON_Y);

            } else if (NSAL0380Constant.MACH_LVL_NUM_2.equals(asMsg.xxDplyByCtrlAncrLvlCd_A.getValue())
                    && !DS_CONTR_CATG.AGGREGATE.equals(asMsg.dsContrCatgCd_A.getValue())) {
                serASMsg = asMsg;
                // mod start 2016/07/15 CSA Defect#8608
                //if (!ZYPCommonFunc.hasValue(asMsg.dsContrRnwEsclPk_A)) {
                if (!ZYPCommonFunc.hasValue(asMsg.contrAutoRnwTpCd_A)) {
                // mod end 2016/07/15 CSA Defect#8608
                    setLowerDataLv2(contrASMsg, asMsg);
                }

                if (!DS_CONTR_CATG.FLEET.equals(asMsg.dsContrCatgCd_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(asMsg.xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(asMsg.xxSmryLineFlg_A, ZYPConstant.FLG_ON_Y);
                }

                if (DS_CONTR_DTL_TP.FLEET.equals(asMsg.dsContrDtlTpCd_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(asMsg.xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(asMsg.xxSmryLineFlg_A, ZYPConstant.FLG_ON_Y);
                }

            } else if (NSAL0380Constant.MACH_LVL_NUM_2.equals(asMsg.xxDplyByCtrlAncrLvlCd_A.getValue())
                    && DS_CONTR_CATG.AGGREGATE.equals(asMsg.dsContrCatgCd_A.getValue())) {

                if (DS_CONTR_DTL_TP.AGGREGATE.equals(asMsg.dsContrDtlTpCd_A.getValue())) {
                    serASMsg = asMsg;
//                    ZYPEZDItemValueSetter.setValue(asMsg.xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(asMsg.xxSmryLineFlg_A, ZYPConstant.FLG_ON_Y);
                }

                // mod start 2016/07/15 CSA Defect#8608
                //if (!ZYPCommonFunc.hasValue(asMsg.dsContrRnwEsclPk_A)) {
                if (!ZYPCommonFunc.hasValue(asMsg.contrAutoRnwTpCd_A)) {
                // mod end 2016/07/15 CSA Defect#8608
                    setLowerDataLv2ForAgg(contrASMsg, serASMsg, asMsg);
                }

            } else {
                // mod start 2016/07/15 CSA Defect#8608
                //if (!ZYPCommonFunc.hasValue(asMsg.dsContrRnwEsclPk_A)) {
                if (!ZYPCommonFunc.hasValue(asMsg.contrAutoRnwTpCd_A)) {
                // mod end 2016/07/15 CSA Defect#8608
                    setLowerDataLv3(contrASMsg, serASMsg, asMsg);
                }
            }
            ZYPEZDItemValueSetter.setValue(asMsg.xxNum_A, BigDecimal.valueOf(indexCnt++));

            EZDMsg.copy(asMsg, "A", csMsg, "C");
            EZDMsg.copy(asMsg, "A1", csMsg, "C1");
            EZDMsg.copy(asMsg, "A2", csMsg, "C2");
            EZDMsg.copy(asMsg, "A3", csMsg, "C3");
        }

        csMsgArray.setValidCount(sMsg.A.getValidCount());
    }

    private void setLowerDataLv2(NSAL0380_ASMsg upperASMsg, NSAL0380_ASMsg lowerASMsg) {
        // add start 2016/04/06 CSA Defect#3024
        String dsContrCtrlStsCd = lowerASMsg.dsContrCtrlStsCd_A2.getValue();
        if (DS_CONTR_DTL_STS.TERMINATED.equals(dsContrCtrlStsCd) || DS_CONTR_DTL_STS.EXPIRED.equals(dsContrCtrlStsCd) || DS_CONTR_DTL_STS.CANCELLED.equals(dsContrCtrlStsCd)) {
            return;
        }
        // add end 2016/04/06 CSA Defect#3024

        // del start 2016/07/15 CSA Defect#8608
        //if (DS_CONTR_CATG.FLEET.equals(lowerASMsg.dsContrCatgCd_A.getValue())) {
        //    return;
        //}
        // del end 2016/07/15 CSA Defect#8608
        if (!ZYPCommonFunc.hasValue(upperASMsg.dsContrRnwEsclPk_A)) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(lowerASMsg.contrAutoRnwTpCd_A, upperASMsg.contrAutoRnwTpCd_A);
        // add start 2016/07/15 CSA Defect#8608
        ZYPEZDItemValueSetter.setValue(lowerASMsg.rnwBaseFlg_A, upperASMsg.rnwBaseFlg_A);
        ZYPEZDItemValueSetter.setValue(lowerASMsg.rnwUsgFlg_A, upperASMsg.rnwUsgFlg_A);
        // add end 2016/07/15 CSA Defect#8608
        // START 2017/09/29 K.Kojima [QC#18378,ADD]
        ZYPEZDItemValueSetter.setValue(lowerASMsg.autoRnwFlg_A, upperASMsg.autoRnwFlg_A);
        // END 2017/09/29 K.Kojima [QC#18378,ADD]
        ZYPEZDItemValueSetter.setValue(lowerASMsg.rnwPrcMethCd_A, upperASMsg.rnwPrcMethCd_A);
        ZYPEZDItemValueSetter.setValue(lowerASMsg.befEndRnwDaysAot_A, upperASMsg.befEndRnwDaysAot_A);
        ZYPEZDItemValueSetter.setValue(lowerASMsg.basePrcUpRatio_A, upperASMsg.basePrcUpRatio_A);
        ZYPEZDItemValueSetter.setValue(lowerASMsg.mtrPrcUpRatio_A, upperASMsg.mtrPrcUpRatio_A);

        if (!ZYPCommonFunc.hasValue(lowerASMsg.maxContrRnwCnt_A)) {
            ZYPEZDItemValueSetter.setValue(lowerASMsg.maxContrRnwCnt_A, upperASMsg.maxContrRnwCnt_A);
        }

        if (!ZYPCommonFunc.hasValue(lowerASMsg.maxPrcUpRatio_A)) {
            ZYPEZDItemValueSetter.setValue(lowerASMsg.maxPrcUpRatio_A, upperASMsg.maxPrcUpRatio_A);
        }
    }

    private void setLowerDataLv2ForAgg(NSAL0380_ASMsg contrASMsg, NSAL0380_ASMsg serASMsg, NSAL0380_ASMsg asMsg) {
        // add start 2016/04/06 CSA Defect#3024
        String dsContrCtrlStsCd = asMsg.dsContrCtrlStsCd_A2.getValue();
        if (DS_CONTR_DTL_STS.TERMINATED.equals(dsContrCtrlStsCd) || DS_CONTR_DTL_STS.EXPIRED.equals(dsContrCtrlStsCd) || DS_CONTR_DTL_STS.CANCELLED.equals(dsContrCtrlStsCd)) {
            return;
        }
        // add end 2016/04/06 CSA Defect#3024

        if (!ZYPCommonFunc.hasValue(contrASMsg.dsContrRnwEsclPk_A) && !ZYPCommonFunc.hasValue(serASMsg.dsContrRnwEsclPk_A)) {
            return;
        }

        if (ZYPCommonFunc.hasValue(serASMsg.contrAutoRnwTpCd_A)) {
            ZYPEZDItemValueSetter.setValue(asMsg.contrAutoRnwTpCd_A, serASMsg.contrAutoRnwTpCd_A);
            // add start 2016/07/15 CSA Defect#8608
            ZYPEZDItemValueSetter.setValue(asMsg.rnwBaseFlg_A, serASMsg.rnwBaseFlg_A);
            ZYPEZDItemValueSetter.setValue(asMsg.rnwUsgFlg_A, serASMsg.rnwUsgFlg_A);
            // add end 2016/07/15 CSA Defect#8608
            // START 2017/09/29 K.Kojima [QC#18378,ADD]
            ZYPEZDItemValueSetter.setValue(asMsg.autoRnwFlg_A, serASMsg.autoRnwFlg_A);
            // END 2017/09/29 K.Kojima [QC#18378,ADD]
        } else {
            ZYPEZDItemValueSetter.setValue(asMsg.contrAutoRnwTpCd_A, contrASMsg.contrAutoRnwTpCd_A);
            // add start 2016/07/15 CSA Defect#8608
            ZYPEZDItemValueSetter.setValue(asMsg.rnwBaseFlg_A, contrASMsg.rnwBaseFlg_A);
            ZYPEZDItemValueSetter.setValue(asMsg.rnwUsgFlg_A, contrASMsg.rnwUsgFlg_A);
            // add end 2016/07/15 CSA Defect#8608
            // START 2017/09/29 K.Kojima [QC#18378,ADD]
            ZYPEZDItemValueSetter.setValue(asMsg.autoRnwFlg_A, contrASMsg.autoRnwFlg_A);
            // END 2017/09/29 K.Kojima [QC#18378,ADD]
        }

        if (ZYPCommonFunc.hasValue(serASMsg.rnwPrcMethCd_A)) {
            ZYPEZDItemValueSetter.setValue(asMsg.rnwPrcMethCd_A, serASMsg.rnwPrcMethCd_A);
        } else {
            ZYPEZDItemValueSetter.setValue(asMsg.rnwPrcMethCd_A, contrASMsg.rnwPrcMethCd_A);
        }

        if (ZYPCommonFunc.hasValue(serASMsg.befEndRnwDaysAot_A)) {
            ZYPEZDItemValueSetter.setValue(asMsg.befEndRnwDaysAot_A, serASMsg.befEndRnwDaysAot_A);
        } else {
            ZYPEZDItemValueSetter.setValue(asMsg.befEndRnwDaysAot_A, contrASMsg.befEndRnwDaysAot_A);
        }

        if (DS_CONTR_DTL_TP.AGGREGATE.equals(asMsg.dsContrDtlTpCd_A.getValue())) {
            if (ZYPCommonFunc.hasValue(serASMsg.mtrPrcUpRatio_A)) {
                ZYPEZDItemValueSetter.setValue(asMsg.mtrPrcUpRatio_A, serASMsg.mtrPrcUpRatio_A);
            } else {
                ZYPEZDItemValueSetter.setValue(asMsg.mtrPrcUpRatio_A, contrASMsg.mtrPrcUpRatio_A);
            }
        } else {
            if (ZYPCommonFunc.hasValue(serASMsg.basePrcUpRatio_A)) {
                ZYPEZDItemValueSetter.setValue(asMsg.basePrcUpRatio_A, serASMsg.basePrcUpRatio_A);
            } else {
                ZYPEZDItemValueSetter.setValue(asMsg.basePrcUpRatio_A, contrASMsg.basePrcUpRatio_A);
            }
        }

        if (!ZYPCommonFunc.hasValue(asMsg.maxContrRnwCnt_A)) {
            if (ZYPCommonFunc.hasValue(serASMsg.maxContrRnwCnt_A)) {
                ZYPEZDItemValueSetter.setValue(asMsg.maxContrRnwCnt_A, serASMsg.maxContrRnwCnt_A);
            } else {
                ZYPEZDItemValueSetter.setValue(asMsg.maxContrRnwCnt_A, contrASMsg.maxContrRnwCnt_A);
            }
        }

        if (!ZYPCommonFunc.hasValue(asMsg.maxPrcUpRatio_A)) {
            if (ZYPCommonFunc.hasValue(serASMsg.maxPrcUpRatio_A)) {
                ZYPEZDItemValueSetter.setValue(asMsg.maxPrcUpRatio_A, serASMsg.maxPrcUpRatio_A);
            } else {
                ZYPEZDItemValueSetter.setValue(asMsg.maxPrcUpRatio_A, contrASMsg.maxPrcUpRatio_A);
            }
        }
    }

    private void setLowerDataLv3(NSAL0380_ASMsg contrASMsg, NSAL0380_ASMsg serASMsg, NSAL0380_ASMsg asMsg) {
        // add start 2016/04/06 CSA Defect#3024
        String dsContrCtrlStsCd = asMsg.dsContrCtrlStsCd_A2.getValue();
        if (DS_CONTR_DTL_STS.TERMINATED.equals(dsContrCtrlStsCd) || DS_CONTR_DTL_STS.EXPIRED.equals(dsContrCtrlStsCd) || DS_CONTR_DTL_STS.CANCELLED.equals(dsContrCtrlStsCd)) {
            return;
        }
        // add end 2016/04/06 CSA Defect#3024

        if (!ZYPCommonFunc.hasValue(contrASMsg.dsContrRnwEsclPk_A) && !ZYPCommonFunc.hasValue(serASMsg.dsContrRnwEsclPk_A)) {
            return;
        }

        if (ZYPCommonFunc.hasValue(serASMsg.contrAutoRnwTpCd_A)) {
            ZYPEZDItemValueSetter.setValue(asMsg.contrAutoRnwTpCd_A, serASMsg.contrAutoRnwTpCd_A);
            // add start 2016/07/15 CSA Defect#8608
            ZYPEZDItemValueSetter.setValue(asMsg.rnwBaseFlg_A, serASMsg.rnwBaseFlg_A);
            ZYPEZDItemValueSetter.setValue(asMsg.rnwUsgFlg_A, serASMsg.rnwUsgFlg_A);
            // add end 2016/07/15 CSA Defect#8608
            // START 2017/09/29 K.Kojima [QC#18378,ADD]
            ZYPEZDItemValueSetter.setValue(asMsg.autoRnwFlg_A, serASMsg.autoRnwFlg_A);
            // END 2017/09/29 K.Kojima [QC#18378,ADD]
        } else {
            ZYPEZDItemValueSetter.setValue(asMsg.contrAutoRnwTpCd_A, contrASMsg.contrAutoRnwTpCd_A);
            // add start 2016/07/15 CSA Defect#8608
            ZYPEZDItemValueSetter.setValue(asMsg.rnwBaseFlg_A, contrASMsg.rnwBaseFlg_A);
            ZYPEZDItemValueSetter.setValue(asMsg.rnwUsgFlg_A, contrASMsg.rnwUsgFlg_A);
            // add end 2016/07/15 CSA Defect#8608
            // START 2017/09/29 K.Kojima [QC#18378,ADD]
            ZYPEZDItemValueSetter.setValue(asMsg.autoRnwFlg_A, contrASMsg.autoRnwFlg_A);
            // END 2017/09/29 K.Kojima [QC#18378,ADD]
        }

        if (ZYPCommonFunc.hasValue(serASMsg.rnwPrcMethCd_A)) {
            ZYPEZDItemValueSetter.setValue(asMsg.rnwPrcMethCd_A, serASMsg.rnwPrcMethCd_A);
        } else {
            ZYPEZDItemValueSetter.setValue(asMsg.rnwPrcMethCd_A, contrASMsg.rnwPrcMethCd_A);
        }

        if (ZYPCommonFunc.hasValue(serASMsg.befEndRnwDaysAot_A)) {
            ZYPEZDItemValueSetter.setValue(asMsg.befEndRnwDaysAot_A, serASMsg.befEndRnwDaysAot_A);
        } else {
            ZYPEZDItemValueSetter.setValue(asMsg.befEndRnwDaysAot_A, contrASMsg.befEndRnwDaysAot_A);
        }

        if (NSAL0380Constant.BASE_NM.equals(asMsg.dsContrBaseUsgNm_A.getValue())) {
            if (ZYPCommonFunc.hasValue(serASMsg.basePrcUpRatio_A)) {
                ZYPEZDItemValueSetter.setValue(asMsg.basePrcUpRatio_A, serASMsg.basePrcUpRatio_A);
            } else {
                ZYPEZDItemValueSetter.setValue(asMsg.basePrcUpRatio_A, contrASMsg.basePrcUpRatio_A);
            }
        } else {
            if (ZYPCommonFunc.hasValue(serASMsg.mtrPrcUpRatio_A)) {
                ZYPEZDItemValueSetter.setValue(asMsg.mtrPrcUpRatio_A, serASMsg.mtrPrcUpRatio_A);
            } else {
                ZYPEZDItemValueSetter.setValue(asMsg.mtrPrcUpRatio_A, contrASMsg.mtrPrcUpRatio_A);
            }
        }
        // START 2018/12/20 [QC#29636, ADD]
        if (!ZYPCommonFunc.hasValue(asMsg.maxPrcUpRatio_A)) {
            if (ZYPCommonFunc.hasValue(serASMsg.maxPrcUpRatio_A)) {
                ZYPEZDItemValueSetter.setValue(asMsg.maxPrcUpRatio_A, serASMsg.maxPrcUpRatio_A);
            } else {
                ZYPEZDItemValueSetter.setValue(asMsg.maxPrcUpRatio_A, contrASMsg.maxPrcUpRatio_A);
            }
        }
        // END 2018/12/20 [QC#29636, ADD]
    }

    /**
     * get getSearch Data
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     * @return Exist Data : true
     */
    private boolean getSearchData(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {

        S21SsmEZDResult ssmFstResult = NSAL0380Query.getInstance().getSearchData(cMsg, sMsg);

        if (ssmFstResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAL0380Constant.NSAM0219E, new String[] {NSAL0380Constant.INVALID_PARAM });
            return false;
        }

        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            NSAL0380_PCMsg pCmsg = cMsg.P.no(i);
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                NSAL0380_ASMsg asMsg = sMsg.A.no(j);
                if (NSAL0380Constant.MACH_LVL_NUM_1.equals(asMsg.xxDplyByCtrlAncrLvlCd_A.getValue())
                        && pCmsg.dsContrPk_P1.getValue().equals(asMsg.dsContrPk_A.getValue())) {

                    setValue(pCmsg.dsContrCatgCd_P1, asMsg.dsContrCatgCd_A);
                    setValue(pCmsg.contrAutoRnwTpCd_P1, asMsg.contrAutoRnwTpCd_A);
                    setValue(pCmsg.rnwPrcMethCd_P1, asMsg.rnwPrcMethCd_A);
                    setValue(pCmsg.befEndRnwDaysAot_P1, asMsg.befEndRnwDaysAot_A);
                    setValue(pCmsg.basePrcUpRatio_P1, asMsg.basePrcUpRatio_A);
                    setValue(pCmsg.mtrPrcUpRatio_P1, asMsg.mtrPrcUpRatio_A);
                }

            }
        }

        // add start 2016/07/15 CSA Defect#8608
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0380_ASMsg asMsg = sMsg.A.no(i);
            CONTR_AUTO_RNW_TPTMsg contrAutoRnwTpTMsg =  NSAL0380CommonLogic.getContrAutoRnwTp(getGlobalCompanyCode(), asMsg.contrAutoRnwTpCd_A.getValue());
            if (contrAutoRnwTpTMsg != null) {
                setValue(asMsg.rnwBaseFlg_A, contrAutoRnwTpTMsg.rnwBaseFlg);
                setValue(asMsg.rnwUsgFlg_A, contrAutoRnwTpTMsg.rnwUsgFlg);
                // START 2017/09/29 K.Kojima [QC#18378,ADD]
                setValue(asMsg.autoRnwFlg_A, contrAutoRnwTpTMsg.autoRnwFlg);
                // END 2017/09/29 K.Kojima [QC#18378,ADD]
            } else {
                setValue(asMsg.rnwBaseFlg_A, ZYPConstant.FLG_OFF_N);
                setValue(asMsg.rnwUsgFlg_A, ZYPConstant.FLG_OFF_N);
                // START 2017/09/29 K.Kojima [QC#18378,ADD]
                setValue(asMsg.autoRnwFlg_A, ZYPConstant.FLG_OFF_N);
                // END 2017/09/29 K.Kojima [QC#18378,ADD]
            }
        }
        // add end 2016/07/15 CSA Defect#8608
        return true;
    }

    /**
     * copy To BSMsg for Init
     * @param sMsg NSAL0380SMsg
     */
    private void copyToSMsgForInit(NSAL0380SMsg sMsg) {

        NSAL0380_CSMsgArray csMsgArray = sMsg.C;
        NSAL0380_BSMsgArray bsMsgArray = sMsg.B;
        ZYPTableUtil.clear(bsMsgArray);

        for (int i = 0; i < csMsgArray.getValidCount(); i++) {

            NSAL0380_CSMsg csMsg = csMsgArray.no(i);
            NSAL0380_BSMsg bsMsg = bsMsgArray.no(i);

            EZDMsg.copy(csMsg, "C", bsMsg, "B");
            EZDMsg.copy(csMsg, "C1", bsMsg, "B1");
            EZDMsg.copy(csMsg, "C2", bsMsg, "B2");
            EZDMsg.copy(csMsg, "C3", bsMsg, "B3");
        }
        bsMsgArray.setValidCount(csMsgArray.getValidCount());
    }

    /**
     * get Machine Level Number
     * @param cMsg NSAL0380CMsg
     * @return Machine Level Number
     */
    private String getMachLvlNum(NSAL0380CMsg cMsg) {

        // event line
        int eventLine = cMsg.xxNum_EV.getValueInt();
        NSAL0380_ACMsg acMsg = cMsg.A.no(eventLine);

        if (ZYPCommonFunc.hasValue(acMsg.dsContrNum_A.getValue())) {
            return NSAL0380Constant.MACH_LVL_NUM_1;
        } else if (ZYPCommonFunc.hasValue(acMsg.serNum_A.getValue())) {
            return NSAL0380Constant.MACH_LVL_NUM_2;
        } else {
            return NSAL0380Constant.MACH_LVL_NUM_3;
        }
    }

    /**
     * do Expansion
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     * @param isFstFlg First Flag
     */
    private void doExpansion(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg, boolean isFstFlg) {

        // set Contraction Button
        NSAL0380_ACMsg acMsg = cMsg.A.no(cMsg.xxNum_EV.getValueInt());
        ZYPEZDItemValueSetter.setValue(acMsg.xxSmryLineFlg_A, ZYPConstant.FLG_OFF_N);

        NSAL0380CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        // mod start 2019/06/26 QC#50931
        NSAL0380CommonLogic.copyToASMsgForExpansionAndContraction(sMsg);
        NSAL0380CommonLogic.copyToACMsgForDisplay(cMsg, sMsg);
        // mod end 2019/06/26 QC#50931
    }

    /**
     * do Contraction
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     * @param isFstFlg First Flag
     */
    private void doContraction(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg, boolean isFstFlg) {

        // set Expansion Button
        NSAL0380_ACMsg acMsg = cMsg.A.no(cMsg.xxNum_EV.getValueInt());
        ZYPEZDItemValueSetter.setValue(acMsg.xxSmryLineFlg_A, ZYPConstant.FLG_ON_Y);

        NSAL0380CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        // mod start 2019/06/26 QC#50931
        NSAL0380CommonLogic.copyToASMsgForExpansionAndContraction(sMsg);
        NSAL0380CommonLogic.copyToACMsgForDisplay(cMsg, sMsg);
        // mod end 2019/06/26 QC#50931
    }

    // del start 2019/06/26 QC#50931 (move to NSAL0380CommonLogic)
//    /**
//     * copy To ASMsg for Expansion And Contraction
//     * @param sMsg NSAL0380SMsg
//     */
//    private void copyToASMsgForExpansionAndContraction(NSAL0380SMsg sMsg) {
//
//        // clear NSAL0380_ASMsgArray
//        NSAL0380_ASMsgArray asMsgArray = sMsg.A;
//        ZYPTableUtil.clear(asMsgArray);
//
//        // NSAL0380_BSMsg -> NSAL0380_ASMsg
//        NSAL0380_BSMsgArray bsMsgArray = sMsg.B;
//        int validCnt = 0;
//        int asMsgMaxlength = asMsgArray.length() - 1;
//        boolean dispContr = true;
//        boolean dispMach = true;
//        BigDecimal dsContrPk = null;
//        BigDecimal dsContrDtlPk = null;
//        BigDecimal preDsContrPk = null;
//        BigDecimal preDsContrDtlPk = null;
//
//        for (int i = 0; i < bsMsgArray.getValidCount(); i++) {
//            NSAL0380_BSMsg bsMsg = bsMsgArray.no(i);
//
//            dsContrPk = bsMsg.dsContrPk_B.getValue();
//            dsContrDtlPk = bsMsg.dsContrDtlPk_B.getValue();
//
//            if (ZYPCommonFunc.hasValue(dsContrPk) && ZYPCommonFunc.hasValue(preDsContrPk) && dsContrPk.compareTo(preDsContrPk) == 0) {
//                if (dispContr) {
//                    if (!ZYPCommonFunc.hasValue(dsContrDtlPk) || !ZYPCommonFunc.hasValue(preDsContrDtlPk) || dsContrDtlPk.compareTo(preDsContrDtlPk) != 0) {
//                        if (ZYPConstant.FLG_OFF_N.equals(bsMsg.xxSmryLineFlg_B.getValue())) {
//                            dispMach = false;
//                        } else {
//                            dispMach = true;
//                        }
//                    }
//                }
//            } else {
//                if (ZYPConstant.FLG_OFF_N.equals(bsMsg.xxSmryLineFlg_B.getValue())) {
//                    dispContr = false;
//                    dispMach = false;
//                } else {
//                    dispContr = true;
//                    dispMach = true;
//                }
//            }
//
//            if (ZYPCommonFunc.hasValue(bsMsg.dsContrNum_B.getValue())) {
//                NSAL0380_ASMsg asMsg = asMsgArray.no(validCnt);
//                setToSMsgForExpansionAndContraction(asMsg, bsMsg);
//                validCnt++;
//            } else if (!dispContr) {
//                if (ZYPCommonFunc.hasValue(bsMsg.serNum_B) || (DS_CONTR_DTL_TP.FLEET.equals(bsMsg.dsContrDtlTpCd_B.getValue()) && NSAL0380Constant.MACH_LVL_NUM_2.equals(bsMsg.xxDplyByCtrlAncrLvlCd_B.getValue()))) {
//                    ZYPEZDItemValueSetter.setValue(bsMsg.xxSmryLineFlg_B, ZYPConstant.FLG_ON_Y);
//                }
//            } else if (ZYPCommonFunc.hasValue(bsMsg.serNum_B) || (DS_CONTR_DTL_TP.FLEET.equals(bsMsg.dsContrDtlTpCd_B.getValue()) && NSAL0380Constant.MACH_LVL_NUM_2.equals(bsMsg.xxDplyByCtrlAncrLvlCd_B.getValue()))) {
//                NSAL0380_ASMsg asMsg = asMsgArray.no(validCnt);
//                setToSMsgForExpansionAndContraction(asMsg, bsMsg);
//                validCnt++;
//                // START 2017/06/08 Y.Osawa [QC#18470, ADD]
//            } else if (!ZYPCommonFunc.hasValue(bsMsg.serNum_B) && (!(DS_CONTR_DTL_TP.FLEET.equals(bsMsg.dsContrDtlTpCd_B.getValue())) && NSAL0380Constant.MACH_LVL_NUM_2.equals(bsMsg.xxDplyByCtrlAncrLvlCd_B.getValue()))) {
//                NSAL0380_ASMsg asMsg = asMsgArray.no(validCnt);
//                setToSMsgForExpansionAndContraction(asMsg, bsMsg);
//                validCnt++;
//                // END   2017/06/08 Y.Osawa [QC#18470, ADD]
//            } else if (dispMach) {
//                NSAL0380_ASMsg asMsg = asMsgArray.no(validCnt);
//                setToSMsgForExpansionAndContraction(asMsg, bsMsg);
//                validCnt++;
//            }
//
//            if (validCnt == asMsgMaxlength) {
//                break;
//            }
//
//            preDsContrPk = dsContrPk;
//            preDsContrDtlPk = dsContrDtlPk;
//        }
//
//        asMsgArray.setValidCount(validCnt);
//    }
//
//    /**
//     * set To ASMsg for Expansion And Contraction
//     * @param asMsg NSAL0380_ASMsg
//     * @param bsMsg NSAL0380_BSMsg
//     */
//    private void setToSMsgForExpansionAndContraction(NSAL0380_ASMsg asMsg, NSAL0380_BSMsg bsMsg) {
//
//        EZDMsg.copy(bsMsg, "B", asMsg, "A");
//        EZDMsg.copy(bsMsg, "B1", asMsg, "A1");
//        EZDMsg.copy(bsMsg, "B2", asMsg, "A2");
//
//    }
//
//    /**
//     * copy To ACMsg for First Expansion
//     * @param sMsg NSAL0380SMsg
//     */
//    private void copyToACMsgForDisplay(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {
//
//        // clear NSAL0380_ACMsgArray
//        NSAL0380_ACMsgArray acMsgArray = cMsg.A;
//        ZYPTableUtil.clear(acMsgArray);
//
//        // NSAL0380_ASMsg -> NSAL0380_ACMsg
//        NSAL0380_ASMsgArray asMsgArray = sMsg.A;
//        int sMsgLine = cMsg.xxPageShowFromNum.getValueInt() - 1;
//        int validCnt = 0;
//
//        for (int cnt = 0; cnt < acMsgArray.length(); cnt++) {
//            if (sMsgLine >= asMsgArray.getValidCount()) {
//                break;
//            }
//
//            EZDMsg.copy(asMsgArray.no(sMsgLine), null, acMsgArray.no(cnt), null);
//            sMsgLine++;
//            validCnt++;
//        }
//        acMsgArray.setValidCount(validCnt);
//    }
    // del end 2019/06/26 QC#50931 (move to NSAL0380CommonLogic)

    /**
     * copy To ASMsg for Paging
     * @param sMsg NSAL0380SMsg
     */
    private void copyToASMsgForPaging(NSAL0380SMsg sMsg) {

        NSAL0380CommonLogic.copyASMsgToBSMsg(sMsg);
    }

    /**
     * setDetailForAplToAll
     * @param cMsg NSAL0380CMsg
     * @param acMsg NSAL0380_ACMsg
     */
    private void setDetailForAplToAll(NSAL0380CMsg cMsg, NSAL0380_ACMsg acMsg) {

        setValue(acMsg.contrAutoRnwTpCd_A, cMsg.contrAutoRnwTpCd_H);
        // add start 2016/07/15 CSA Defect#8608
        setValue(acMsg.rnwBaseFlg_A, cMsg.rnwBaseFlg_H);
        setValue(acMsg.rnwUsgFlg_A, cMsg.rnwUsgFlg_H);
        // add end 2016/07/15 CSA Defect#8608
        // START 2017/09/29 K.Kojima [QC#18378,ADD]
        setValue(acMsg.autoRnwFlg_A, cMsg.autoRnwFlg_H);
        // END 2017/09/29 K.Kojima [QC#18378,ADD]
        setValue(acMsg.rnwPrcMethCd_A, cMsg.rnwPrcMethCd_H);
        setValue(acMsg.basePrcUpRatio_A, cMsg.basePrcUpRatio_H);
        setValue(acMsg.mtrPrcUpRatio_A, cMsg.mtrPrcUpRatio_H);

        if (NSAL0380Constant.MACH_LVL_NUM_1.equals(acMsg.xxDplyByCtrlAncrLvlCd_A.getValue())
                || NSAL0380Constant.MACH_LVL_NUM_2.equals(acMsg.xxDplyByCtrlAncrLvlCd_A.getValue())) {
            setValue(acMsg.befEndRnwDaysAot_A, cMsg.befEndRnwDaysAot_H);
            setValue(acMsg.maxContrRnwCnt_A, cMsg.maxContrRnwCnt_H);
            // START 2018/12/20 [QC#29636, DEL]
            // setValue(acMsg.maxPrcUpRatio_A, cMsg.maxPrcUpRatio_H);
            // END 2018/12/20 [QC#29636, DEL]
        }
        // START 2018/12/20 [QC#29636, ADD]
        setValue(acMsg.maxPrcUpRatio_A, cMsg.maxPrcUpRatio_H);
        // END 2018/12/20 [QC#29636, ADD]
    }

    /**
     * doSearch
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    private void doSearch(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {

        if ("NSAL0380Scrn00_Search".equals(cMsg.getScreenAplID())) {
            NSAL0380CMsg cloneCMsg = (NSAL0380CMsg) cMsg.clone();
            String serNumHf = cMsg.serNum_HF.getValue();
            String serNumHt = cMsg.serNum_HT.getValue();
            BigDecimal svcMachMstrPkHf = cMsg.svcMachMstrPk_HF.getValue();
            // START 2016/09/06 T.Tomita [QC#14210, MOD]
//            BigDecimal svcMachMstrPkHt = cMsg.svcMachMstrPk_HF.getValue();
            BigDecimal svcMachMstrPkHt = cMsg.svcMachMstrPk_HT.getValue();
            // END 2016/09/06 T.Tomita [QC#14210, MOD]
            cMsg.clear();
            setValue(cMsg.serNum_HF, serNumHf);
            setValue(cMsg.serNum_HT, serNumHt);
            setValue(cMsg.svcMachMstrPk_HF, svcMachMstrPkHf);
            setValue(cMsg.svcMachMstrPk_HT, svcMachMstrPkHt);
            EZDMsg.copy(cloneCMsg.P, null, cMsg.P, null);
        } else {
            NSAL0380CMsg cloneCMsg = (NSAL0380CMsg) cMsg.clone();
            cMsg.clear();
            EZDMsg.copy(cloneCMsg.P, null, cMsg.P, null);
        }

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

        if (!checkInparam(cMsg)) {
            return;
        }

        createPullDown(cMsg);

        if (!getSearchData(cMsg, sMsg)) {
            return;
        }

        setToSMsgForInit(cMsg, sMsg);
        copyToSMsgForInit(sMsg);

        ZYPTableUtil.clear(cMsg.A);
        NSAL0380_ACMsgArray acMsgArray = cMsg.A;
        int pageFrom = 0;
        int i = pageFrom;
        for (; i < pageFrom + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageFrom), null);
            } else {
                break;
            }
        }

        acMsgArray.setValidCount(i - pageFrom);

        // QC#18799 ADD START
        // START 2017/11/01 K.Kojima [QC#21931-1,DEL]
        // setupDefaulRule(cMsg);
        // doProcess_NSAL0380Scrn00_OnChangeNewRnwTpCd(cMsg, sMsg);
        // END 2017/11/01 K.Kojima [QC#21931-1,DEL]
        // QC#18799 ADD END

        // set Paging Data
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());    }

    // QC#18799 ADD START
    // START 2017/11/01 K.Kojima [QC#21931-1,DEL]
    // private void setupDefaulRule(NSAL0380CMsg cMsg) {
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
    //         ZYPEZDItemValueSetter.setValue(cMsg.contrAutoRnwTpCd_H, outMsg.contrAutoRnwTpCd);
    //         ZYPEZDItemValueSetter.setValue(cMsg.rnwPrcMethCd_H, outMsg.rnwPrcMethCd);
    //         ZYPEZDItemValueSetter.setValue(cMsg.basePrcUpRatio_H, outMsg.basePrcUpRatio);
    //         ZYPEZDItemValueSetter.setValue(cMsg.mtrPrcUpRatio_H, outMsg.mtrPrcUpRatio);
    //         ZYPEZDItemValueSetter.setValue(cMsg.befEndRnwDaysAot_H, outMsg.befEndRnwDaysAot);
    //     }
    //     // END 2017/10/30 M.Kidokoro [QC#21931, DEL]
    // }
    // END 2017/11/01 K.Kojima [QC#21931-1,DEL]
    // QC#18799 ADD END

}
