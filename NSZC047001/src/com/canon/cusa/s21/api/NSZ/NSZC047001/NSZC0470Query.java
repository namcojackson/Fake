/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.AGGR_USG_RECALTMsg;
import business.db.AGGR_USG_RECAL_DTLTMsg;
import business.db.AGGR_USG_RECAL_XS_MTRTMsg;
import business.db.BLLG_CYCLETMsg;
import business.db.BLLG_SCHD_TEST_MTR_SMRYTMsg;
import business.db.CALC_MTR_SCHD_RELNTMsg;
import business.db.CCYTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsgArray;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHD_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFF_MTRTMsg;
import business.db.DS_CONTR_PRC_EFF_MTRTMsgArray;
import business.db.DS_WIN_DAYSTMsg;
import business.db.FLEET_READ_ROLL_UPTMsg;
import business.db.FLEET_READ_ROLL_UP_DTLTMsg;
import business.db.SKIP_RECOV_TPTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsgArray;
import business.db.SVC_CONTR_BASE_BLLGTMsg;
import business.db.SVC_CONTR_BASE_BLLGTMsgArray;
import business.db.SVC_CONTR_BLLGTMsg;
import business.db.SVC_CONTR_BLLGTMsgArray;
import business.db.SVC_CONTR_BLLG_ALLOCTMsg;
import business.db.SVC_CONTR_BLLG_ALLOCTMsgArray;
import business.db.SVC_CONTR_MTR_BLLGTMsg;
import business.db.SVC_CONTR_MTR_BLLGTMsgArray;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsg;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsgArray;
import business.db.SVC_INVTMsg;
import business.db.SVC_INVTMsgArray;
import business.db.SVC_INV_LINETMsg;
import business.db.SVC_INV_LINETMsgArray;
import business.db.SVC_INV_LINE_ADDL_CHRGTMsg;
import business.db.SVC_INV_LINE_ALLOCTMsg;
import business.db.SVC_INV_LINE_ALLOCTMsgArray;
import business.db.SVC_INV_LINE_MTRTMsg;
import business.db.SVC_INV_LINE_MTRTMsgArray;
import business.db.SVC_INV_LINE_XS_MTRTMsg;
import business.db.SVC_INV_LINE_XS_MTRTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001CalcDate;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CPLT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/14/2015   Hitachi         T.Aoyagi        Create          N/A
 * 01/21/2015   Hitachi         K.Kishimoto     Update          QC#3331
 * 01/25/2015   Hitachi         T.Aoyagi        Update          QC#3623
 * 02/26/2016   Hitachi         T.Aoyagi        Update          QC#4690
 * 02/29/2016   Hitachi         T.Aoyagi        Update          QC#4690
 * 03/11/2016   Hitachi         T.Kanasaka      Update          QC#5377
 * 03/15/2016   Hitachi         T.Kanasaka      Update          QC#5377
 * 03/24/2016   Hitachi         T.Aoyagi        Update          QC#5901
 * 03/28/2016   Hitachi         K.Kishimoto     Update          QC#1003
 * 04/18/2016   Hitachi         T.Aoyagi        Update          QC#7056
 * 05/16/2016   Hitachi         T.Aoyagi        Update          QC#8183
 * 06/29/2016   Hitachi         K.Kishimoto     Update          QC#7428,QC#7429
 * 06/29/2016   Hitachi         K.Kishimoto     Update          QC#7402
 * 01/06/2017   Hitachi         T.Mizuki        Update          QC#16399
 * 02/28/2017   Hitachi         K.Kishimoto     Update          QC#17809
 * 06/06/2017   Hitachi         Y.Osawa         Update          QC#18565
 * 06/29/2017   Hitachi         A.Kohinata      Update          QC#18349
 * 06/30/2017   Hitachi         K.Kitachi       Update          QC#18288
 * 07/21/2017   Hitachi         T.Tomita        Update          QC#20045
 * 2017/08/15   Hitachi         A.Kohinata      Update          QC#18799
 * 08/23/2017   Hitachi         K.Kasai         Update          QC#18639
 * 08/29/2017   Hitachi         K.Yamada        Update          QC#20759
 * 2017/08/29   Hitachi         K.Kojima        Update          QC#20057
 * 2017/09/21   Hitachi         K.Kasai         Update          QC#21060
 * 2017/09/26   Hitachi         T.Kanasaka      Update          QC#21414
 * 2017/10/02   Hitachi         A.Kohinata      Update          QC#21567
 * 2017/10/03   Hitachi         E.Kameishi      Update          QC#18636
 * 2017/10/06   Hitachi         K.Kim           Update          QC#21212
 * 2017/10/10   Hitachi         K.Kim           Update          QC#21654
 * 2017/10/24   Hitachi         K.Kishimoto     Update          QC#22008
 * 2017/10/26   Hitachi         T.Tomita        Update          QC#21815
 * 2017/11/01   Hitachi         K.Kojima        Update          QC#21859
 * 2017/11/20   Hitachi         K.Yamada        Update          QC#22683
 * 2017/11/24   Hitachi         T.Tomita        Update          QC#22683
 * 2017/12/05   Hitachi         K.Kojima        Update          QC#21595
 * 2018/03/30   Hitachi         U.Kim           Update          QC#24924
 * 2018/04/09   Hitachi         K.Kojima        Update          QC#24802
 * 2018/04/20   Hitachi         K.Kojima        Update          QC#25595
 * 2018/05/07   Hitachi         T.Tomita        Update          QC#25409
 * 2018/05/18   Hitachi         U.Kim           Update          QC#24854
 * 2018/05/23   Hitachi         K.Kojima        Update          QC#23302
 * 2018/06/20   Hitachi         T.Tomita        Update          QC#26766
 * 2018/07/03   Hitachi         K.Kim           Update          QC#26726
 * 2018/07/17   Hitachi         K.Kishimoto     Update          QC#25959
 * 2018/12/10   Hitachi         K.Kitachi       Update          QC#29387
 * 2018/12/20   Hitachi         K.Kitachi       Update          QC#29647
 * 2019/02/14   Hitachi         K.Kitachi       Update          QC#30066
 * 2019/03/05   Hitachi         K.Kitachi       Update          QC#30619
 * 2019/06/13   Hitachi         K.Kitachi       Update          QC#50811
 * 2019/07/18   Hitachi         K.Kishimoto     Update          QC#51706
 * 2020/03/18   Hitachi         K.Kitachi       Update          QC#55693
 * 2020/07/03   CITS            T.Sakurai       Update          QC#57297
 * 2020/09/24   Hitachi         K.Kitachi       Update          QC#57667
 * 2021/04/06   CITS            S.Go            Update          QC#58642
 * 2022/03/14   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/05/09   CITS            T.Suzuki        Update          QC#59979
 * 2022/07/07   Hitachi         A.Kohinata      Update          QC#60167
 * 2023/05/09   Hitachi         K.Kitachi       Update          QC#61469
 * 2023/08/18   CITS            T.Kojima        Update          QC#60846
 * 2023/10/05   Hitachi         H.Iinuma        Update          QC#61675
 * 2024/02/21   Hitachi         D.Yoshida       Update          QC#61468
 * </pre>
 */
public final class NSZC0470Query extends S21SsmEZDQuerySupport implements ZYPConstant {

    /** List size : dsContrDtlStsCd */
    private static final int DS_CONTR_DTL_STS_CD_LIST_SIZE = 3;

    /**
     * Singleton instance.
     */
    private static final NSZC0470Query INSTANCE = new NSZC0470Query();

    /**
     * Constructor.
     */
    private NSZC0470Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0500Query
     */
    public static NSZC0470Query getInstance() {
        return INSTANCE;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getContrInfo(String glblCmpyCd, BigDecimal dsContrDtlPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getContrInfo", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrDtlTpCd String
     * @return Map<String, Object>
     */
    public BigDecimal getPrntContrDtlInfo(String glblCmpyCd, BigDecimal dsContrDtlPk, String dsContrDtlTpCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrDtlTpCd", dsContrDtlTpCd);
        return (BigDecimal) getSsmEZDClient().queryObject("getPrntContrDtlInfo", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param bllgCycleCd String
     * @return BLLG_CYCLETMsg
     */
    public BLLG_CYCLETMsg getBllgCycleTMsg(String glblCmpyCd, String bllgCycleCd) {

        BLLG_CYCLETMsg inMsg = new BLLG_CYCLETMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.bllgCycleCd, bllgCycleCd);
        return (BLLG_CYCLETMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param svcInvChrgTpCd String
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getSkipRecovInfo(String glblCmpyCd, BigDecimal dsContrDtlPk, String svcInvChrgTpCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("svcInvChrgTpCd", svcInvChrgTpCd);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSkipRecovInfo", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getDsContrPrcEffMtr(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getDsContrPrcEffMtr", ssmParam).getResultObject();
    }

    /**
     * @param dsContrPrcEffTMsg DS_CONTR_PRC_EFFTMsg
     * @param baseChrgFlg String
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getOtherPrcEff(DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", dsContrPrcEffTMsg.glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrPrcEffTMsg.dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrPrcEffTMsg.dsContrBllgMtrPk);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffTMsg.dsContrPrcEffPk);
        ssmParam.put("contrPrcEffFromDt", dsContrPrcEffTMsg.contrPrcEffFromDt);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        List<String> dsContrDtlStsCdList = new ArrayList<String>(DS_CONTR_DTL_STS_CD_LIST_SIZE);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.CANCELLED);
        ssmParam.put("dsContrDtlStsCdList", dsContrDtlStsCdList);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getOtherPrcEff", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param baseChrgFlg String
     * @return List<Map<Strting, Object>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getPrcEffList(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
            ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        }
        List<String> dsContrDtlStsCdList = new ArrayList<String>(DS_CONTR_DTL_STS_CD_LIST_SIZE);
        // Del Start 06/06/2017 <QC#18565>
        //dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.EXPIRED);
        // Del End   06/06/2017 <QC#18565>
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.CANCELLED);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.TERMINATED);
        ssmParam.put("dsContrDtlStsCdList", dsContrDtlStsCdList);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getPrcEff", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param baseChrgFlg String
     * @return String
     */
    public String getInvSchdMaxThruDt(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
            ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        }
        // Add Start 06/29/2016 <QC#7428,QC#7429>
        ssmParam.put("stsClose", DS_BLLG_SCHD_STS.CLOSE);
        // Add End   06/29/2016 <QC#7428,QC#7429>
        ssmParam.put("dsBllgSchdTpCd", DS_BLLG_SCHD_TP.REGULAR);
        return (String) getSsmEZDClient().queryObject("getInvSchdMaxThruDt", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param baseChrgFlg String
     * @param contrPrcEffFromDt String
     * @return String
     */
    public String getLastBilledDtByDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrDtlPk, String baseChrgFlg, String contrPrcEffFromDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        ssmParam.put("dsBllgSchdTpCd", DS_BLLG_SCHD_TP.REGULAR);
        ssmParam.put("contrPrcEffFromDt", contrPrcEffFromDt);
        return (String) getSsmEZDClient().queryObject("getLastBilledDt", ssmParam).getResultObject();
    }

    /**
     * @param dsContrPrcEffTMsg DS_CONTR_PRC_EFFTMsg
     * @param baseChrgFlg String
     * @return String
     */
    public String getLastBilledDt(DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", dsContrPrcEffTMsg.glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrPrcEffTMsg.dsContrDtlPk);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffTMsg.dsContrPrcEffPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        ssmParam.put("dsBllgSchdTpCd", DS_BLLG_SCHD_TP.REGULAR);
        ssmParam.put("contrPrcEffFromDt", dsContrPrcEffTMsg.contrPrcEffFromDt);
        return (String) getSsmEZDClient().queryObject("getLastBilledDt", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return DS_CONTR_PRC_EFFTMsg
     */
    public DS_CONTR_PRC_EFFTMsg getDsContrPrcEffTMsg(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        // mod start 2017/07/21 QC#20045
//        return (DS_CONTR_PRC_EFFTMsg) S21CacheTBLAccessor.findByKey(inMsg);
        return (DS_CONTR_PRC_EFFTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        // mod end   2017/07/21 QC#20045
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgSchdSmryPk BigDecimal
     * @return DS_CONTR_BLLG_SCHD_SMRYTMsg
     */
    public DS_CONTR_BLLG_SCHD_SMRYTMsg getDsContrBllgSchdSmryTMsg(String glblCmpyCd, BigDecimal dsContrBllgSchdSmryPk) {

        DS_CONTR_BLLG_SCHD_SMRYTMsg inMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdSmryPk, dsContrBllgSchdSmryPk);
        // mod start 2017/07/21 QC#20045
//        return (DS_CONTR_BLLG_SCHD_SMRYTMsg) S21CacheTBLAccessor.findByKey(inMsg);
        return (DS_CONTR_BLLG_SCHD_SMRYTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        // mod end   2017/07/21 QC#20045
    }

    // START 2017/12/05 K.Kojima [QC#21595,ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_DTLTMsg
     */
    public DS_CONTRTMsg getDsContrTMsg(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }
    // END 2017/12/05 K.Kojima [QC#21595,ADD]

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_DTLTMsg
     */
    public DS_CONTR_DTLTMsg getDsContrDtlTMsg(String glblCmpyCd, BigDecimal dsContrDtlPk) {

        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        // mod start 2017/07/21 QC#20045
//        return (DS_CONTR_DTLTMsg) S21CacheTBLAccessor.findByKey(inMsg);
        return (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        // mod end   2017/07/21 QC#20045
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getDeleteSchdList(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("invFlg", FLG_OFF_N);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDeleteSchdList", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return  List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getDeleteSchdSmryList(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("invFlg", FLG_ON_Y);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDeleteSchdSmryList", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getDeletePrcEffList(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("invFlg", FLG_ON_Y);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDeletePrcEffList", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getDeletePrcEffMtrList(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDeletePrcEffMtrList", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param schdSmryPkList List<BigDecimal>
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getDeleteSchdMtrList(String glblCmpyCd, List<BigDecimal> schdSmryPkList) {

        // START 2016/02/26 T.Aoyagi [QC4690, MOD]
        List<BigDecimal> deleteSchdMtrList = new ArrayList<BigDecimal>();
        if (schdSmryPkList.size() > 0) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("dsContrBllgSchdSmryPkList", schdSmryPkList);
            deleteSchdMtrList = (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDeleteSchdMtrList", ssmParam).getResultObject();
        }
        return deleteSchdMtrList;
        // END 2016/02/26 T.Aoyagi [QC4690, MOD]
    }

    /**
     * @param glblCmpyCd String
     * @param pk BigDecimal
     * @return boolean
     */
    public boolean removeSchdSmry(String glblCmpyCd, BigDecimal pk) {

        DS_CONTR_BLLG_SCHD_SMRYTMsg inTMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdSmryPk, pk);
        S21ApiTBLAccessor.logicalRemove(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pk BigDecimal
     * @return boolean
     */
    public boolean removeSchdMeter(String glblCmpyCd, BigDecimal pk) {

        DS_CONTR_BLLG_SCHD_MTRTMsg inTMsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdMtrPk, pk);
        S21ApiTBLAccessor.logicalRemove(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pk BigDecimal
     * @return boolean
     */
    public boolean removeSchd(String glblCmpyCd, BigDecimal pk) {

        DS_CONTR_BLLG_SCHDTMsg inTMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdPk, pk);
        S21ApiTBLAccessor.logicalRemove(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeSchd(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            DS_CONTR_BLLG_SCHDTMsg inTMsg = new DS_CONTR_BLLG_SCHDTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.dsContrBllgSchdPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    // START 2017/10/03 E.Kameishi [QC18636, ADD]
    /**
     * @param glblCmpyCd String
     * @param pk BigDecimal
     * @return boolean
     */
    public boolean removeSchdTestMtrSmry(String glblCmpyCd, BigDecimal pk) {

        BLLG_SCHD_TEST_MTR_SMRYTMsg inTMsg = new BLLG_SCHD_TEST_MTR_SMRYTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdPk, pk);
        S21ApiTBLAccessor.logicalRemoveByPartialKey(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeSchdTestMtrSmry(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            BLLG_SCHD_TEST_MTR_SMRYTMsg inTMsg = new BLLG_SCHD_TEST_MTR_SMRYTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.dsContrBllgSchdPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }
    // END 2017/10/03 E.Kameishi [QC18636, ADD]
    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeSchdSmry(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            DS_CONTR_BLLG_SCHD_SMRYTMsg inTMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.dsContrBllgSchdSmryPk, pk);

            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pk BigDecimal
     * @return boolean
     */
    public boolean removePrcEff(String glblCmpyCd, BigDecimal pk) {

        DS_CONTR_PRC_EFFTMsg inTMsg = new DS_CONTR_PRC_EFFTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.dsContrPrcEffPk, pk);

        S21ApiTBLAccessor.logicalRemove(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removePrcEff(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            DS_CONTR_PRC_EFFTMsg inTMsg = new DS_CONTR_PRC_EFFTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.dsContrPrcEffPk, pk);

            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pk BigDecimal
     * @return boolean
     */
    public boolean removePrcEffMtr(String glblCmpyCd, BigDecimal pk) {

        DS_CONTR_PRC_EFF_MTRTMsg inTMsg = new DS_CONTR_PRC_EFF_MTRTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.dsContrPrcEffMtrPk, pk);

        S21ApiTBLAccessor.logicalRemove(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removePrcEffMtr(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            DS_CONTR_PRC_EFF_MTRTMsg inTMsg = new DS_CONTR_PRC_EFF_MTRTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.dsContrPrcEffMtrPk, pk);

            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeSchdMtr(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            DS_CONTR_BLLG_SCHD_MTRTMsg inTMsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.dsContrBllgSchdMtrPk, pk);

            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param dsContrPrcEffTMsg DS_CONTR_PRC_EFFTMsg
     * @param lastBilledDt String
     * @param termAmt BigDecimal
     * @return boolean
     */
    public boolean updatePrcEff(DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg, String lastBilledDt, BigDecimal termAmt) {

        DS_CONTR_PRC_EFFTMsg inTMsg = new DS_CONTR_PRC_EFFTMsg();
        setValue(inTMsg.glblCmpyCd, dsContrPrcEffTMsg.glblCmpyCd.getValue());
        setValue(inTMsg.dsContrPrcEffPk, dsContrPrcEffTMsg.dsContrPrcEffPk.getValue());
        inTMsg = (DS_CONTR_PRC_EFFTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
        if (inTMsg == null) {
            return false;
        }

        setValue(inTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.EXPIRED);
        setValue(inTMsg.contrPrcEffThruDt, lastBilledDt);
        setValue(inTMsg.basePrcTermDealAmtRate, termAmt);
        S21ApiTBLAccessor.update(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * @param dsContrPrcEffTMsg DS_CONTR_PRC_EFFTMsg
     * @param lastBilledDt String
     * @param schdSmryMap v
     * @return boolean
     */
    public boolean updateSchdSmry(DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg, String lastBilledDt, Map<String, Object> schdSmryMap) {

        if (schdSmryMap == null) {
            return true;
        }

        BigDecimal dsContrBllgSchdSmryPk = (BigDecimal) schdSmryMap.get("DS_CONTR_BLLG_SCHD_SMRY_PK");
        BigDecimal perSchdNum = (BigDecimal) schdSmryMap.get("PER_SCHD_NUM");
        BigDecimal baseSubTotPrcDealAmt = (BigDecimal) schdSmryMap.get("BASE_SUB_TOT_PRC_DEAL_AMT");
        BigDecimal basePrcDealAdjAmt = (BigDecimal) schdSmryMap.get("BASE_PRC_DEAL_ADJ_AMT");

        DS_CONTR_BLLG_SCHD_SMRYTMsg inTMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
        setValue(inTMsg.glblCmpyCd, dsContrPrcEffTMsg.glblCmpyCd.getValue());
        setValue(inTMsg.dsContrBllgSchdSmryPk, dsContrBllgSchdSmryPk);
        inTMsg = (DS_CONTR_BLLG_SCHD_SMRYTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }

        setValue(inTMsg.perSchdNum, perSchdNum);
        setValue(inTMsg.baseSubTotPrcDealAmt, baseSubTotPrcDealAmt);
        setValue(inTMsg.basePrcDealAdjAmt, basePrcDealAdjAmt);
        setValue(inTMsg.bllgSchdThruDt, lastBilledDt);

        S21ApiTBLAccessor.update(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * @param dsContrPrcEffTMsg DS_CONTR_PRC_EFFTMsg
     * @param lastBilledDt String
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getSchdSmryWithBilled(DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg, String lastBilledDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", dsContrPrcEffTMsg.glblCmpyCd.getValue());
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffTMsg.dsContrPrcEffPk.getValue());
        ssmParam.put("lastBilledDt", lastBilledDt);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getSchdSmryWithBilled", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param contrEffFromDt String
     * @param contrEffTruuDt String
     * @param contrCloDt String
     * @param dsContrBllgMtrPk BigDecimal
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getFleetMachContrInfo(String glblCmpyCd, BigDecimal dsContrDtlPk
            , String contrEffFromDt, String contrEffTruuDt, String contrCloDt, BigDecimal dsContrBllgMtrPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("contrEffFromDt", contrEffFromDt);
        ssmParam.put("contrEffTruuDt", contrEffTruuDt);
        ssmParam.put("contrCloDt", contrCloDt);
        List<String> dsContrDtlStsCdList = new ArrayList<String>(DS_CONTR_DTL_STS_CD_LIST_SIZE);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.EXPIRED);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.CANCELLED);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.TERMINATED);
        ssmParam.put("dsContrDtlStsCdList", dsContrDtlStsCdList);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getFleetMachContrInfo", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param prntDsContrBllgSchdPk BigDecimal
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getFleetMachContrInfoRebil(String glblCmpyCd, BigDecimal prntDsContrBllgSchdPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prntDsContrBllgSchdPk", prntDsContrBllgSchdPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getFleetMachContrInfoRebil", ssmParam).getResultObject();
    }

    /**
     * @param dsContrPrcEffTMsg DS_CONTR_PRC_EFFTMsg
     * @return BigDecimal
     */
    public BigDecimal getSumBaseDealAmt(DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", dsContrPrcEffTMsg.glblCmpyCd.getValue());
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffTMsg.dsContrPrcEffPk.getValue());
        return (BigDecimal) getSsmEZDClient().queryObject("getSumBaseDealAmt", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param lastBilledDt String
     * @param dsContrBllgMtrPk BigDecimal
     * @param baseChrgFlg String
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getDeletePrcEffListByDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrDtlPk, String lastBilledDt, BigDecimal dsContrBllgMtrPk, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("contrPrcEffFromDt", lastBilledDt);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        List<String> dsContrPrcEffStsCdList = new ArrayList<String>(DS_CONTR_DTL_STS_CD_LIST_SIZE);
        dsContrPrcEffStsCdList.add(DS_CONTR_DTL_STS.EXPIRED);
        dsContrPrcEffStsCdList.add(DS_CONTR_DTL_STS.CANCELLED);
        dsContrPrcEffStsCdList.add(DS_CONTR_DTL_STS.TERMINATED);
        ssmParam.put("dsContrDtlStsCdList", dsContrPrcEffStsCdList);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDeletePrcEffListByDsContrDtlPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getDeleteSchdSmryListByPrcEffPk(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDeleteSchdSmryListByPrcEffPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getDeleteSchdListByPrcEffPk(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDeleteSchdListByPrcEffPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    // START 2018/03/09 K.Kojima [QC#23600,MOD]
    // public List<Map<String, Object>> getBllgSchdForSkipMonthBase(String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt) {
    public List<Map<String, Object>> getBllgSchdForSkipMonthBase(String glblCmpyCd, BigDecimal dsContrDtlPk) {
    // END 2018/03/09 K.Kojima [QC#23600,MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        // START 2018/03/09 K.Kojima [QC#23600,DEL]
        // ssmParam.put("slsDt", slsDt);
        // END 2018/03/09 K.Kojima [QC#23600,DEL]
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getBllgSchdForSkipMonthBase", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    // START 2018/03/09 K.Kojima [QC#23600,MOD]
    // public List<Map<String, Object>> getBllgSchdForSkipMonthUsg(String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt, BigDecimal dsContrBllgMtrPk) {
    public List<Map<String, Object>> getBllgSchdForSkipMonthUsg(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
    // END 2018/03/09 K.Kojima [QC#23600,MOD]

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        // START 2018/03/09 K.Kojima [QC#23600,DEL]
        // ssmParam.put("slsDt", slsDt);
        // END 2018/03/09 K.Kojima [QC#23600,DEL]
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getBllgSchdForSkipMonthUsg", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    // START 2018/03/09 K.Kojima [QC#23600,MOD]
    // public List<BigDecimal> getBllgMtrForSkipMonth(String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt) {
    public List<BigDecimal> getBllgMtrForSkipMonth(String glblCmpyCd, BigDecimal dsContrDtlPk) {
    // END 2018/03/09 K.Kojima [QC#23600,MOD]

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        // START 2018/03/09 K.Kojima [QC#23600,DEL]
        // ssmParam.put("slsDt", slsDt);
        // END 2018/03/09 K.Kojima [QC#23600,DEL]
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getBllgMtrForSkipMonth", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param bllgSchdThruDt String
     * @param baseChrgFlg String
     * @return String
     */
    public String getRvcSchdDt(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String bllgSchdThruDt, String baseChrgFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("bllgSchdThruDt", bllgSchdThruDt);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        return (String) getSsmEZDClient().queryObject("getRvcSchdDt", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param skipRecovTpCd String
     * @return SKIP_RECOV_TPTMsg
     */
    public SKIP_RECOV_TPTMsg getSkipRecovTpTMsg(String glblCmpyCd, String skipRecovTpCd) {

        SKIP_RECOV_TPTMsg inMsg = new SKIP_RECOV_TPTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.skipRecovTpCd, skipRecovTpCd);
        return (SKIP_RECOV_TPTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgSchdPk BigDecimal
     * @return DS_CONTR_BLLG_SCHDTMsg
     */
    public DS_CONTR_BLLG_SCHDTMsg getDsContrBllgSchdTMsg(String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {

        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        return (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param prntDsContrBllgSchdPk BigDecimal
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getBllgSchdForFleetMach(String glblCmpyCd, BigDecimal prntDsContrBllgSchdPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prntDsContrBllgSchdPk", prntDsContrBllgSchdPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getBllgSchdForFleetMach", ssmParam).getResultObject();
    }

    // Service Contract Billing
    /**
     * @param glblCmpyCd String
     * @param svcContrBllgGrpSq BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getSvcContrBllgByGrpSq(String glblCmpyCd, BigDecimal svcContrBllgGrpSq) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgGrpSq", svcContrBllgGrpSq);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSvcContrBllgByGrpSq", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String 
     * @param dsContrBllgSchdPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getSvcContrBllgGrpSq(String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgSchdPk", dsContrBllgSchdPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSvcContrBllgGrpSq", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgSchdPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getSvcContrBllgPk(String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgSchdPk", dsContrBllgSchdPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSvcContrBllgPk", ssmParam).getResultObject();
    }

    // ADD Start 01/21/2016 <QC#3331>
    /**
     * @param glblCmpyCd String
     * @param prntSvcContrBllgPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getSvcContrBllgPkForAddl(String glblCmpyCd, BigDecimal prntSvcContrBllgPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prntSvcContrBllgPk", prntSvcContrBllgPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSvcContrBllgPkForAddl", ssmParam).getResultObject();
    }
    // ADD End 01/21/2016 <QC#3331>

    /**
     * @param glblCmpyCd String
     * @param svcContrBllgPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getSvcContrBllgAllocPk(String glblCmpyCd, BigDecimal svcContrBllgPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgPk", svcContrBllgPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSvcContrBllgAllocPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcContrBllgPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getSvcContrBaseBllgPk(String glblCmpyCd, BigDecimal svcContrBllgPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgPk", svcContrBllgPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSvcContrBaseBllgPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcContrBllgPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getSvcContrMtrBllgPk(String glblCmpyCd, BigDecimal svcContrBllgPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgPk", svcContrBllgPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSvcContrMtrBllgPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcContrBllgPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getSvcContrXsMtrBllgPk(String glblCmpyCd, BigDecimal svcContrBllgPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgPk", svcContrBllgPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSvcContrXsMtrBllgPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcContrBllgPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getSvcContrAddlChrgBllgPk(String glblCmpyCd, BigDecimal svcContrBllgPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgPk", svcContrBllgPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSvcContrAddlChrgBllgPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param prntDsContrBllgSchdPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getFleetReadRollUpPk(String glblCmpyCd, BigDecimal prntDsContrBllgSchdPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prntDsContrBllgSchdPk", prntDsContrBllgSchdPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getFleetReadRollUpPk", ssmParam).getResultObject();
    }

    // Mod Start 03/11/2016 <QC#5377>
    /**
     * @param glblCmpyCd String
     * @param fleetReadRollUpPkList List<BigDecimal>
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getFleetReadRollUpDtlPk(String glblCmpyCd, List<BigDecimal> fleetReadRollUpPkList) {

        if (fleetReadRollUpPkList == null || fleetReadRollUpPkList.isEmpty()) {
            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("fleetReadRollUpPkList", fleetReadRollUpPkList);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getFleetReadRollUpDtlPk", ssmParam).getResultObject();
    }
    // Mod End 03/11/2016 <QC#5377>

    /**
     * @param glblCmpyCd String
     * @param svcContrBllgGrpSq BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getAggrUsgRecalPk(String glblCmpyCd, BigDecimal svcContrBllgGrpSq) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgGrpSq", svcContrBllgGrpSq);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getAggrUsgRecalPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param aggrUsgRecalPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getAggrUsgRecalDtlPk(String glblCmpyCd, BigDecimal aggrUsgRecalPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("aggrUsgRecalPk", aggrUsgRecalPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getAggrUsgRecalDtlPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param aggrUsgRecalDtlPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getAggrUsgRecalXsMtrPk(String glblCmpyCd, BigDecimal aggrUsgRecalDtlPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("aggrUsgRecalDtlPk", aggrUsgRecalDtlPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getAggrUsgRecalXsMtrPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeSvcContrBllg(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            SVC_CONTR_BLLGTMsg inTMsg = new SVC_CONTR_BLLGTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.svcContrBllgPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeSvcContrBllgAlloc(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            SVC_CONTR_BLLG_ALLOCTMsg inTMsg = new SVC_CONTR_BLLG_ALLOCTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.svcContrBllgAllocPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeSvcContrBaseBllg(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            SVC_CONTR_BASE_BLLGTMsg inTMsg = new SVC_CONTR_BASE_BLLGTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.svcContrBaseBllgPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeSvcContrMtrBllg(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            SVC_CONTR_MTR_BLLGTMsg inTMsg = new SVC_CONTR_MTR_BLLGTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.svcContrMtrBllgPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeSvcContrXsMtrBllg(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            SVC_CONTR_XS_MTR_BLLGTMsg inTMsg = new SVC_CONTR_XS_MTR_BLLGTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.svcContrXsMtrBllgPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeSvcContrAddlChrgBllg(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            SVC_CONTR_ADDL_CHRG_BLLGTMsg inTMsg = new SVC_CONTR_ADDL_CHRG_BLLGTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.svcContrAddlChrgBllgPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeFleetReadRollUp(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            FLEET_READ_ROLL_UPTMsg inTMsg = new FLEET_READ_ROLL_UPTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.fleetReadRollUpPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeFleetReadRollUpDtl(String glblCmpyCd, List<BigDecimal> pkList) {
        // Add Start 03/15/2016 <QC#5377>
        if (pkList == null) {
            return true;
        }
        // Add End 03/15/2016 <QC#5377>

        for (BigDecimal pk : pkList) {

            FLEET_READ_ROLL_UP_DTLTMsg inTMsg = new FLEET_READ_ROLL_UP_DTLTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.fleetReadRollUpDtlPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeAggrUsgRecal(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            AGGR_USG_RECALTMsg inTMsg = new AGGR_USG_RECALTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.aggrUsgRecalPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param aggrUsgRecalPk BigDecimal
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeAggrUsgRecalDtl(String glblCmpyCd, BigDecimal aggrUsgRecalPk, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            AGGR_USG_RECAL_DTLTMsg inTMsg = new AGGR_USG_RECAL_DTLTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.aggrUsgRecalPk, aggrUsgRecalPk);
            setValue(inTMsg.aggrUsgRecalDtlPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param aggrUsgRecalDtlPk BigDecimal
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeAggrUsgRecalXsMtr(String glblCmpyCd, BigDecimal aggrUsgRecalDtlPk, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            AGGR_USG_RECAL_XS_MTRTMsg inTMsg = new AGGR_USG_RECAL_XS_MTRTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.aggrUsgRecalDtlPk, aggrUsgRecalDtlPk);
            setValue(inTMsg.aggrUsgRecalXsMtrPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return List<BigDecimal>
     */
    public String  getDsContrDtlTp(String glblCmpyCd, BigDecimal dsContrDtlPk) {

        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        inMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return "";
        }

        return (String) inMsg.dsContrDtlTpCd.getValue();
    }
    
    // QC#57297 Add Start
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @param delFlg String
     * @param dsContrBllgMtrPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    // START 2020/09/24 K.Kitachi [QC#57667, MOD]
//    public List<BigDecimal> getPrcEffMtrForCancel(String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt, String delFlg) {
    public List<BigDecimal> getPrcEffMtrForCancel(String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt, String delFlg, BigDecimal dsContrBllgMtrPk) {
    // END 2020/09/24 K.Kitachi [QC#57667, MOD]

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("slsDt", slsDt);
        if (FLG_ON_Y.equals(delFlg)) {
            // Usage Charge
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
            // START 2020/09/24 K.Kitachi [QC#57667, ADD]
            ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
            // END 2020/09/24 K.Kitachi [QC#57667, ADD]
        } else {
            // Base Charge
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        }
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getPrcEffMtrForCancel", ssmParam).getResultObject();
    }
    // QC#57297 Add End
    
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @param delFlg String
     * @param dsContrBllgMtrPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    // START 2020/09/24 K.Kitachi [QC#57667, MOD]
//    public List<BigDecimal> getPrcEffForCancel(String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt, String delFlg) {
    public List<BigDecimal> getPrcEffForCancel(String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt, String delFlg, BigDecimal dsContrBllgMtrPk) {
    // END 2020/09/24 K.Kitachi [QC#57667, MOD]

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("slsDt", slsDt);
        if (FLG_ON_Y.equals(delFlg)) {
            // Usage Charge
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
            // START 2020/09/24 K.Kitachi [QC#57667, ADD]
            ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
            // END 2020/09/24 K.Kitachi [QC#57667, ADD]
        } else {
            // Base Charge
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        }
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getPrcEffForCancel", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param delFlg String
     * @param dsContrBllgMtrPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    // START 2020/09/24 K.Kitachi [QC#57667, MOD]
//    public List<BigDecimal> getDsContrBllgSchdSmryForCancel(String glblCmpyCd, BigDecimal dsContrDtlPk, String delFlg) {
    public List<BigDecimal> getDsContrBllgSchdSmryForCancel(String glblCmpyCd, BigDecimal dsContrDtlPk, String delFlg, BigDecimal dsContrBllgMtrPk) {
    // END 2020/09/24 K.Kitachi [QC#57667, MOD]

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        // mod start 2017/01/10 CSA QC#16399
//        ssmParam.put("slsDt", slsDt);
        // mod end 2017/01/10 CSA QC#16399
        if (FLG_ON_Y.equals(delFlg)) {
            // Usage Charge
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
            // START 2020/09/24 K.Kitachi [QC#57667, ADD]
            ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
            // END 2020/09/24 K.Kitachi [QC#57667, ADD]
        } else {
            // Base Charge
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        }
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDsContrBllgSchdSmryForCancel", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgSmryPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getDsContrBllgSchdMtrForCancel(String glblCmpyCd, BigDecimal dsContrBllgSmryPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgSmryPk", dsContrBllgSmryPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDsContrBllgSchdMtrForCancel", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param delFlg String
     * @param dsContrBllgMtrPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    // START 2020/09/24 K.Kitachi [QC#57667, MOD]
//    public List<BigDecimal> getDsContrBllgSchdForCancel(String glblCmpyCd, BigDecimal dsContrDtlPk, String delFlg) {
    public List<BigDecimal> getDsContrBllgSchdForCancel(String glblCmpyCd, BigDecimal dsContrDtlPk, String delFlg, BigDecimal dsContrBllgMtrPk) {
    // END 2020/09/24 K.Kitachi [QC#57667, MOD]

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        // mod start 2017/01/10 CSA QC#16399
//        ssmParam.put("slsDt", slsDt);
        // mod end 2017/01/10 CSA QC#16399
        if (FLG_ON_Y.equals(delFlg)) {
            // Usage Charge
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
            // START 2020/09/24 K.Kitachi [QC#57667, ADD]
            ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
            // END 2020/09/24 K.Kitachi [QC#57667, ADD]
        } else {
            // Base Charge
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        }
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDsContrBllgSchdForCancel", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param prntDsContrBllgSchdPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getChildDsContrBllgSchdForCancel(String glblCmpyCd, BigDecimal prntDsContrBllgSchdPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prntDsContrBllgSchdPk", prntDsContrBllgSchdPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getChildDsContrBllgSchdForCancel", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param baseChrgFlg String
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getLtstPrcEffSqNum(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        return (Map<String, Object>) getSsmEZDClient().queryObject("getLtstPrcEffSqNum", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getPrcEffBasePrcAmt(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        return (BigDecimal) getSsmEZDClient().queryObject("getPrcEffBasePrcAmt", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @param contrXsCopyPk BigDecimal
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getPrcEffMtrInfo(String glblCmpyCd, BigDecimal dsContrPrcEffPk, BigDecimal contrXsCopyPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("contrXsCopyPk", contrXsCopyPk);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getPrcEffMtrInfo", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param baseChrgFlg String
     * @return BigDecimal
     */
    public BigDecimal getMaxPrcEffSqNum(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        return (BigDecimal) getSsmEZDClient().queryObject("getMaxPrcEffSqNum", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param prntDsContrBllgSchdPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getDeleteChildSchdList(String glblCmpyCd, BigDecimal prntDsContrBllgSchdPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prntDsContrBllgSchdPk", prntDsContrBllgSchdPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDeleteChildSchdList", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param contrPrcEffFromDt String
     * @param baseChrgFlg String
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getPrcEffPkByEffFromDt(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String contrPrcEffFromDt, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("contrPrcEffFromDt", contrPrcEffFromDt);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getPrcEffPkByEffFromDt", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param effFromDt String
     * @param baseChrgFlg String
     * @return List<Map<String, BigDecimal>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, BigDecimal>> getBllgSchdForUpdate(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String effFromDt, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("effFromDt", effFromDt);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        return (List<Map<String, BigDecimal>>) getSsmEZDClient().queryObjectList("getBllgSchdForUpdate", ssmParam).getResultObject();
    }

    // Add Start 06/29/2016 <QC#7428,QC#7429>
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param effFromDt String
     * @param baseChrgFlg String
     * @return List<Map<String, BigDecimal>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, BigDecimal>> getBllgSchdForUpdateByFromDt(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String effFromDt, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("effFromDt", effFromDt);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        return (List<Map<String, BigDecimal>>) getSsmEZDClient().queryObjectList("getBllgSchdForUpdateByFromDt", ssmParam).getResultObject();
    }
    // Add End   06/29/2016 <QC#7428,QC#7429>

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param effFromDt String
     * @param baseChrgFlg String
     * @return List<Map<String, BigDecimal>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, BigDecimal>> getBllgSchdForDelete(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String effFromDt, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("effFromDt", effFromDt);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        return (List<Map<String, BigDecimal>>) getSsmEZDClient().queryObjectList("getBllgSchdForDelete", ssmParam).getResultObject();
    }

    // START 2023/08/18 T.Kojima [QC#60846, ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param effFromDt String
     * @param baseChrgFlg String
     * @return List<Map<String, BigDecimal>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, BigDecimal>> getPastBllgSchdForDelete(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String effFromDt, String baseChrgFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("effFromDt", effFromDt);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        return (List<Map<String, BigDecimal>>) getSsmEZDClient().queryObjectList("getPastBllgSchdForDelete", ssmParam).getResultObject();
    }
    // END 2023/08/18 T.Kojima [QC#60846, ADD]
    // Add Start 06/29/2016 <QC#7428,QC#7429>
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param effFromDt String
     * @param baseChrgFlg String
     * @return List<Map<String, BigDecimal>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, BigDecimal>> getBllgSchdForDeleteByFromDt(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String effFromDt, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("effFromDt", effFromDt);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        return (List<Map<String, BigDecimal>>) getSsmEZDClient().queryObjectList("getBllgSchdForDeleteByFromDt", ssmParam).getResultObject();
    }
    // Add End   06/29/2016 <QC#7428,QC#7429>

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param bllgSchdFromDt String
     * @param bllgSchdThruDt String
     * @param baseChrgFlg String
     * @return Map<String, BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public Map<String, BigDecimal> getSummaryBasePrcAmt(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String bllgSchdFromDt, String bllgSchdThruDt, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("bllgSchdFromDt", bllgSchdFromDt);
        ssmParam.put("bllgSchdThruDt", bllgSchdThruDt);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        return (Map<String, BigDecimal>) getSsmEZDClient().queryObject("getSummaryBasePrcAmt", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param baseChrgFlg String
     * @return String
     */
    public String getMaxBllgSchdThruDt(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        return (String) getSsmEZDClient().queryObject("getMaxBllgSchdThruDt", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param baseChrgFlg String
     * @return String
     */
    public String getInvedMaxBllgSchdThruDt(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("invFlg", FLG_ON_Y);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        return (String) getSsmEZDClient().queryObject("getInvedMaxBllgSchdThruDt", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param contrXsCopyPk BigDecimal
     * @return CONTR_XS_COPYTMsg
     */
    public CONTR_XS_COPYTMsg  getContrXsCopy(String glblCmpyCd, BigDecimal contrXsCopyPk) {

        CONTR_XS_COPYTMsg inMsg = new CONTR_XS_COPYTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.contrXsCopyPk, contrXsCopyPk);
        return (CONTR_XS_COPYTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    // START 07/12/2018 [QC#25959, MOD]
    // mod start 2017/07/21 QC#20045
    // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param bllgSchdFromDt String
     * @param bllgSchdThruDt String
     * @param baseChrgFlg String
     * @param invTpCd String
     * @param childDsContrDtlPk BigDecimal
     * @return Map<String, Object>
     */
    // START 2019/02/14 K.Kitachi [QC#30066, MOD]
//    public Map<String, Object> getPrntBllgSchdPk(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String bllgSchdFromDt, String bllgSchdThruDt, String baseChrgFlg, String invTpCd) {
    public Map<String, Object> getPrntBllgSchdPk(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String bllgSchdFromDt, String bllgSchdThruDt, String baseChrgFlg, String invTpCd, BigDecimal childDsContrDtlPk) {
    // END 2019/02/14 K.Kitachi [QC#30066, MOD]

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
//        ssmParam.put("nextBllgDt", nextBllgDt);
        ssmParam.put("bllgSchdFromDt", bllgSchdFromDt);
        ssmParam.put("bllgSchdThruDt", bllgSchdThruDt);
        ssmParam.put("invTpCd", invTpCd);
        // START 2018/12/10 K.Kitachi [QC#29387, ADD]
        ssmParam.put("bllgTmgTpAdvance", BLLG_TMG_TP.ADVANCE);
        ssmParam.put("bllgTmgTpArrears", BLLG_TMG_TP.ARREARS);
        // END 2018/12/10 K.Kitachi [QC#29387, ADD]
        // START 2019/02/14 K.Kitachi [QC#30066, ADD]
        int baseBllgCycleMthAot = getBaseBllgCycleMthAot(glblCmpyCd, childDsContrDtlPk);
        String nextBllgSchdFromDt = NSXC001001CalcDate.addMonths(bllgSchdFromDt, baseBllgCycleMthAot);
        String prevBllgSchdThruDt = NSXC001001CalcDate.addMonths(bllgSchdThruDt, -baseBllgCycleMthAot);
        ssmParam.put("nextBllgSchdFromDt", nextBllgSchdFromDt);
        ssmParam.put("prevBllgSchdThruDt", prevBllgSchdThruDt);
        ssmParam.put("childDsContrDtlPk", childDsContrDtlPk);
        // END 2019/02/14 K.Kitachi [QC#30066, ADD]
        return (Map<String, Object>)getSsmEZDClient().queryObject("getPrntBllgSchdPk", ssmParam).getResultObject();
    }
    // mod end   2017/07/21 QC#20045

    // START 2019/02/14 K.Kitachi [QC#30066, ADD]
    private int getBaseBllgCycleMthAot(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlTMsg(glblCmpyCd, dsContrDtlPk);
        if (dsContrDtlTMsg == null) {
            return 0;
        }
        BLLG_CYCLETMsg bllgCycleTMsg = getBllgCycleTMsg(glblCmpyCd, dsContrDtlTMsg.baseBllgCycleCd.getValue());
        if (bllgCycleTMsg == null) {
            return 0;
        }
        return bllgCycleTMsg.bllgCycleMthAot.getValueInt();
    }
    // END 2019/02/14 K.Kitachi [QC#30066, ADD]

    // mod start 2017/07/21 QC#20045
    // START 2016/01/25 T.Aoyagi [CSA-QC#3623, ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param bllgSchdFromDt String
     * @param bllgSchdThruDt String
     * @param invTpCd String
     * @return Map<String, Object>
     */
    public Map<String, Object> getPrntBllgSchdPkForUsg(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String bllgSchdFromDt, String bllgSchdThruDt, String invTpCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
//        ssmParam.put("nextBllgDt", nextBllgDt);
        ssmParam.put("bllgSchdFromDt", bllgSchdFromDt);
        ssmParam.put("bllgSchdThruDt", bllgSchdThruDt);
        ssmParam.put("invTpCd", invTpCd);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getPrntBllgSchdPkForUsg", ssmParam).getResultObject();
    }
    // END   2016/01/25 T.Aoyagi [CSA-QC#3623, ADD]
    // END 04/18/2016 T.Aoyagi [QC#7056, MOD]
    // mod end   2017/07/21 QC#20045
    // END   07/12/2018 [QC#25959, MOD]

    // START 05/16/2016 T.Aoyagi [QC#8183, ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param nextBllgDt String
     * @param baseChrgFlg String
     * @param invTpCd String
     * @param svcCrRebilDtlPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getPrntBllgSchdPkForRebil(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String nextBllgDt, String baseChrgFlg, String invTpCd, BigDecimal svcCrRebilDtlPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        ssmParam.put("invTpCd", invTpCd);
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        return (BigDecimal) getSsmEZDClient().queryObject("getPrntBllgSchdPkForRebil", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param nextBllgDt String
     * @param invTpCd String
     * @param svcCrRebilDtlPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getPrntBllgSchdPkForUsgRebil(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String nextBllgDt, String invTpCd, BigDecimal svcCrRebilDtlPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("invTpCd", invTpCd);
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        return (BigDecimal) getSsmEZDClient().queryObject("getPrntBllgSchdPkForUsgRebil", ssmParam).getResultObject();
    }
    // END 05/16/2016 T.Aoyagi [QC#8183, ADD]

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrPrcEffPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param baseChrgFlg String
     * @return String
     */
    public String getMaxBllgSchdThruDtByPrcEffPk(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPrcEffPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (String) getSsmEZDClient().queryObject("getMaxBllgSchdThruDtByPrcEffPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrPrcEffPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param baseChrgFlg String
     * @param lastBilledDt String
     * @return List<Map<String, BigDecimal>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, BigDecimal>> getBllgSchdByPrcEffPk(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPrcEffPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg, String lastBilledDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        ssmParam.put("lastBilledDt", lastBilledDt);
        return (List<Map<String, BigDecimal>>) getSsmEZDClient().queryObjectList("getBllgSchdByPrcEffPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getSchdSmryByPrcEffPk(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSchdSmryByPrcEffPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getTermAmtRate(String glblCmpyCd, BigDecimal dsContrDtlPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        List<String> dsContrDtlStsCdList = new ArrayList<String>(DS_CONTR_DTL_STS_CD_LIST_SIZE);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.CANCELLED);
        ssmParam.put("dsContrDtlStsCdList", dsContrDtlStsCdList);
        return (BigDecimal) getSsmEZDClient().queryObject("getTermAmtRate", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param ccyCd String
     * @return CCYTMsg
     */
    public CCYTMsg getCcy(String glblCmpyCd, String ccyCd) {

        CCYTMsg inMsg = new CCYTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.ccyCd, ccyCd);
        return (CCYTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getSchdSmrySqNum(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        return (BigDecimal) getSsmEZDClient().queryObject("getSchdSmrySqNum", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param baseChrgFlg String
     * @return Map<String, String>
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getPrcEffPeriod(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        List<String> dsContrDtlStsCdList = new ArrayList<String>(DS_CONTR_DTL_STS_CD_LIST_SIZE);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.CANCELLED);
        ssmParam.put("dsContrDtlStsCdList", dsContrDtlStsCdList);
        return (Map<String, String>) getSsmEZDClient().queryObject("getPrcEffPeriod", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param baseChrgFlg String
     * @param trmnDt String
     * @return List<Map<String, BigDecimal>>
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getBllgSchdForTrmn(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg, String trmnDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        ssmParam.put("trmnDt", trmnDt);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getBllgSchdForTrmn", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getBllgMtrPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getBllgMtrPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param baseChrgFlg String
     * @param trmnDt String    
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getBilledAmtAfterTrmnDt(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg, String trmnDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        ssmParam.put("trmnDt", trmnDt);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getBilledAmtAfterTrmnDt", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param baseChrgFlg String
     * @return String
     */
    // START 2019/05/17 [QC#50338, MOD]
    // public String getLastSvcInvNum(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg) {
    public Map<String, Object> getLastSvcInvNum(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg) {
    // END 2019/05/17 [QC#50338, MOD]

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        // START 2024/02/21 [QC#61468, ADD]
        ssmParam.put("invTpFc", SVC_INV_CHRG_TP.FREIGHT_CHARGE);
        // END   2024/02/21 [QC#61468, ADD]

        // START 2019/05/17 [QC#50338, MOD]
        // return (String) getSsmEZDClient().queryObject("getLastSvcInvNum", ssmParam).getResultObject();
        return (Map<String, Object>) getSsmEZDClient().queryObject("getLastSvcInvNum", ssmParam).getResultObject();
        // END 2019/05/17 [QC#50338, MOD]
    }

    /**
     * @param glblCmpyCd String
     * @param svcInvNum String
     * @return SVC_INVTMsg
     */
    public SVC_INVTMsg getSvcInvTMsg(String glblCmpyCd, String svcInvNum) {

        SVC_INVTMsg inMsg = new SVC_INVTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcInvNum01", svcInvNum);
        inMsg.setMaxCount(1);
        inMsg.setSQLID("001");

        SVC_INVTMsgArray svcInvTMsgArray = (SVC_INVTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (svcInvTMsgArray != null && svcInvTMsgArray.length() > 0) {
            return svcInvTMsgArray.no(0);
        } else {
            return null;
        }
    }

    /**
     * @param glblCmpyCd String
     * @param svcInvLinePk BigDecimal
     * @return SVC_INV_LINETMsg
     */
    public SVC_INV_LINETMsg getSvcInvLineTMsgByPk(String glblCmpyCd, BigDecimal svcInvLinePk) {

        SVC_INV_LINETMsg inMsg = new SVC_INV_LINETMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcInvLinePk, svcInvLinePk);
        return (SVC_INV_LINETMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param svcInvNum String
     * @return SVC_INV_LINETMsg
     */
    // START 2019/05/17 [QC#50338, MOD]
    public SVC_INV_LINETMsg getSvcInvLineTMsg(String glblCmpyCd, String svcInvNum, String svcInvLineNum) {
    // public SVC_INV_LINETMsg getSvcInvLineTMsg(String glblCmpyCd, String svcInvNum) {
    // END 2019/05/17 [QC#50338, MOD]

        SVC_INV_LINETMsg inMsg = new SVC_INV_LINETMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcInvNum01", svcInvNum);
        // START 2019/05/17 [QC#50338, ADD]
        inMsg.setConditionValue("svcInvLineNum01", svcInvLineNum);
        // END   2019/05/17 [QC#50338, ADD]
        inMsg.setConditionValue("svcInvChrgTpCd01", SVC_INV_CHRG_TP.BASE_CHARGE);
        inMsg.setMaxCount(1);
        // START 2019/05/17 [QC#50338, MOD]
        // inMsg.setSQLID("004");
        if (ZYPCommonFunc.hasValue(svcInvLineNum)) {
            inMsg.setSQLID("006");
        } else {
            inMsg.setSQLID("004");
        }
        // END   2019/05/17 [QC#50338, MOD]

        SVC_INV_LINETMsgArray svcInvLineTMsgArray = (SVC_INV_LINETMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (svcInvLineTMsgArray != null && svcInvLineTMsgArray.length() > 0) {
            return svcInvLineTMsgArray.no(0);
        } else {
            return null;
        }
    }

    /**
     * @param glblCmpyCd String
     * @param svcInvLinePk BigDecimal
     * @return SVC_INV_LINE_MTRTMsg
     */
    public SVC_INV_LINE_MTRTMsg getSvcInvLineMtrTMsg(String glblCmpyCd, BigDecimal svcInvLinePk) {

        SVC_INV_LINE_MTRTMsg inMsg = new SVC_INV_LINE_MTRTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcInvLinePk01", svcInvLinePk);
        inMsg.setMaxCount(1);
        inMsg.setSQLID("001");

        SVC_INV_LINE_MTRTMsgArray svcInvLineTMsgArray = (SVC_INV_LINE_MTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (svcInvLineTMsgArray != null && svcInvLineTMsgArray.length() > 0) {
            return svcInvLineTMsgArray.no(0);
        } else {
            return null;
        }
    }

    /**
     * @param glblCmpyCd String
     * @param svcInvLinePk BigDecimal
     * @return SVC_INV_LINE_XS_MTRTMsg
     */
    public SVC_INV_LINE_XS_MTRTMsgArray getSvcInvLineXsMtrTMsg(String glblCmpyCd, BigDecimal svcInvLinePk) {

        SVC_INV_LINE_XS_MTRTMsg inMsg = new SVC_INV_LINE_XS_MTRTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcInvLinePk01", svcInvLinePk);
        inMsg.setSQLID("001");
        return (SVC_INV_LINE_XS_MTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param svcInvLinePk BigDecimal
     * @return SVC_INV_LINE_ALLOCTMsg
     */
    public SVC_INV_LINE_ALLOCTMsgArray getSvcInvLineAllocTMsgArray(String glblCmpyCd, BigDecimal svcInvLinePk) {

        SVC_INV_LINE_ALLOCTMsg inMsg = new SVC_INV_LINE_ALLOCTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcInvLinePk01", svcInvLinePk);
        inMsg.setSQLID("001");
        return (SVC_INV_LINE_ALLOCTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgSchdPk BigDecimal
     * @return SVC_INV_LINE_ALLOCTMsg
     */
    public SVC_CONTR_BLLGTMsg getSvcContrBllgTMsg(String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {

        SVC_CONTR_BLLGTMsg inMsg = new SVC_CONTR_BLLGTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrBllgSchdPk01", dsContrBllgSchdPk);
        inMsg.setConditionValue("addlChrgFlg01", FLG_OFF_N);
        inMsg.setSQLID("002");
        SVC_CONTR_BLLGTMsgArray svcContrBllgTMsgArray = (SVC_CONTR_BLLGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (svcContrBllgTMsgArray != null && svcContrBllgTMsgArray.length() > 0) {
            return svcContrBllgTMsgArray.no(0);
        } else {
            return null;
        }
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgSchdMtrPk BigDecimal
     * @return DS_CONTR_BLLG_SCHD_MTRTMsg
     */
    public DS_CONTR_BLLG_SCHD_MTRTMsg getSchdMtrTMsg(String glblCmpyCd, BigDecimal dsContrBllgSchdMtrPk) {

        DS_CONTR_BLLG_SCHD_MTRTMsg inMsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdMtrPk, dsContrBllgSchdMtrPk);
        return (DS_CONTR_BLLG_SCHD_MTRTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @param svcCrRebilDtlPk BigDecimal
     * @return List<Map<String, BigDecimal>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, BigDecimal>> getBllgSchdBySvcCrRebilPk(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal svcCrRebilDtlPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        return (List<Map<String, BigDecimal>>) getSsmEZDClient().queryObjectList("getBllgSchdBySvcCrRebilPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcInvLinePk BigDecimal
     * @return SVC_INV_LINE_ADDL_CHRGTMsg
     */
    public SVC_INV_LINE_ADDL_CHRGTMsg getSvcInvLineAddlChrg(String glblCmpyCd, BigDecimal svcInvLinePk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcInvLinePk", svcInvLinePk);
        BigDecimal svcInvLineAddlChrgPk = (BigDecimal) getSsmEZDClient().queryObject("getSvcInvLineAddlChrg", ssmParam).getResultObject();

        if (ZYPCommonFunc.hasValue(svcInvLineAddlChrgPk)) {
            SVC_INV_LINE_ADDL_CHRGTMsg inMsg = new SVC_INV_LINE_ADDL_CHRGTMsg();
            setValue(inMsg.glblCmpyCd, glblCmpyCd);
            setValue(inMsg.svcInvLineAddlChrgPk, svcInvLineAddlChrgPk);
            return (SVC_INV_LINE_ADDL_CHRGTMsg) EZDTBLAccessor.findByKey(inMsg);
        }
        return null;
    }

    /**
     * @param glblCmpyCd String
     * @param svcInvNum String
     * @param svcInvChrgTpCd String
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getSvcInvLinePk(String glblCmpyCd, String svcInvNum, String svcInvChrgTpCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcInvNum", svcInvNum);
        ssmParam.put("svcInvChrgTpCd", svcInvChrgTpCd);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSvcInvLinePk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param prntSvcInvLinePk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getSvcInvLinePkForAddl(String glblCmpyCd, BigDecimal prntSvcInvLinePk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prntSvcInvLinePk", prntSvcInvLinePk);
        ssmParam.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSvcInvLinePkForAddl", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param svcInvNum String
     * @param dsContrBllgMtrPk BigDecimal
     * @param baseChrgFlg String
     * @return List<Map<String, BigDecimal>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, BigDecimal>> getBllgSchdBySvcInvNum(String glblCmpyCd, BigDecimal dsContrDtlPk, String svcInvNum, BigDecimal dsContrBllgMtrPk, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // START 2016/03/24 T.Aoyagi [QC#5901, ADD]
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        // END 2016/03/24 T.Aoyagi [QC#5901, ADD]
        ssmParam.put("svcInvNum", svcInvNum);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (List<Map<String, BigDecimal>>) getSsmEZDClient().queryObjectList("getBllgSchdBySvcInvNum", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgSchdSmryPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getSchdMtrInfo(String glblCmpyCd, BigDecimal dsContrBllgSchdSmryPk, BigDecimal dsContrBllgMtrPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgSchdSmryPk", dsContrBllgSchdSmryPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSchdMtrInfo", ssmParam).getResultObject();
    }

    // Add Start 03/28/2016 <QC#1003>
    /**
     * @param glblCmpyCd String
     * @param slsDt String
     * @param dsContrPk BigDecimal
     * @return List<Map<String, Object>>
     */
    // mod start 2017/08/15 QC#18799
//    public List<Map<String, Object>> getMtrEntryStsUpd(String glblCmpyCd, String thruDt, BigDecimal dsContrPk) {
    public List<Map<String, Object>> getMtrEntryStsUpd(String glblCmpyCd, String slsDt, BigDecimal dsContrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("thruDt", thruDt);
        ssmParam.put("dsContrPk", dsContrPk);
        ssmParam.put("agg", DS_CONTR_DTL_TP.AGGREGATE);
        ssmParam.put("flt", DS_CONTR_DTL_TP.FLEET);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("dateFormat", DATE_FORMAT);
        ssmParam.put("months", BLLG_CYCLE_UOM.MONTHS);
        ssmParam.put("defBefDays", DEF_WINDOW_BEF_DAYS);
        // START 2023/05/09 K.Kitachi [QC#61469, ADD]
        BigDecimal mtrReadWinMlyDaysAot = BigDecimal.ZERO;
        BigDecimal mtrReadWinOthDaysAot = BigDecimal.ZERO;
        DS_WIN_DAYSTMsg dsWinDaysTMsg = new DS_WIN_DAYSTMsg();
        setValue(dsWinDaysTMsg.glblCmpyCd, glblCmpyCd);
        setValue(dsWinDaysTMsg.dsWinDaysTrgtPerTxt, "*");
        setValue(dsWinDaysTMsg.svcLineBizCd, "*");
        dsWinDaysTMsg = (DS_WIN_DAYSTMsg) S21ApiTBLAccessor.findByKey(dsWinDaysTMsg);
        if (dsWinDaysTMsg != null) {
            mtrReadWinMlyDaysAot = dsWinDaysTMsg.mtrReadWinMlyDaysAot.getValue();
            mtrReadWinOthDaysAot = dsWinDaysTMsg.mtrReadWinOthDaysAot.getValue();
        }
        ssmParam.put("mtrReadWinMlyDaysAot", mtrReadWinMlyDaysAot);
        ssmParam.put("mtrReadWinOthDaysAot", mtrReadWinOthDaysAot);
        // END 2023/05/09 K.Kitachi [QC#61469, ADD]

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> getList = (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getMtrEntryStsUpd", ssmParam).getResultObject();
        return getList;
    }
    // mod end 2017/08/15 QC#18799

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getPhysMtrPk(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        CONTR_PHYS_BLLG_MTR_RELNTMsg inMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        inMsg.setMaxCount(1);
        inMsg.setSQLID("002");

        CONTR_PHYS_BLLG_MTR_RELNTMsgArray getTMsgArray = (CONTR_PHYS_BLLG_MTR_RELNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (getTMsgArray != null && getTMsgArray.length() > 0) {
            return getTMsgArray.no(0).svcPhysMtrPk.getValue();
        } else {
            return null;
        }
    }
    // Add End   03/28/2016 <QC#1003>
    // START 04/18/2016 T.Aoyagi [QC#7056, ADD]
    /**
     * @param glblCmpyCd String
     * @param svcInvLinePk BigDecimal
     * @return Map<String, Object>
     */
    public Map<String, Object> getSchdPkForAggLine(String glblCmpyCd, BigDecimal svcInvLinePk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcInvLinePk", svcInvLinePk);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getSchdPkForAggLine", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @return String
     */
    public String getMtrLbCd(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        // mod start 2017/07/21 QC#20045
//        inMsg = (DS_CONTR_BLLG_MTRTMsg) S21CacheTBLAccessor.findByKey(inMsg);
        inMsg = (DS_CONTR_BLLG_MTRTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        // mod end   2017/07/21 QC#20045
        if (inMsg != null) {
            return inMsg.bllgMtrLbCd.getValue();
        }
        return null;
    }
    // END 04/18/2016 T.Aoyagi [QC#7056, ADD]
    // Add Start 06/29/2016 <QC#7402>
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @return List<DS_CONTR_PRC_EFFTMsg>
     */
    public List<DS_CONTR_PRC_EFFTMsg> getAsisPeList(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        List<DS_CONTR_PRC_EFFTMsg> asisPeList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("stsCancel", DS_CONTR_DTL_STS.CANCELLED);
        List<BigDecimal> peKeyList = (List<BigDecimal>) getSsmEZDClient().queryObjectList("getAsisPeKeyList", ssmParam).getResultObject();
        if (peKeyList.size() == 0) {
            return asisPeList;
        }
        for (BigDecimal peKey : peKeyList) {
            DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
            setValue(inMsg.glblCmpyCd, glblCmpyCd);
            setValue(inMsg.dsContrPrcEffPk, peKey);
            inMsg = (DS_CONTR_PRC_EFFTMsg) S21ApiTBLAccessor.findByKey(inMsg);
            if (inMsg != null) {
                asisPeList.add(inMsg);
            }
        }
        return asisPeList;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return List<DS_CONTR_PRC_EFFTMsg>
     */
    public List<DS_CONTR_BLLG_SCHD_SMRYTMsg> getAsisTopSchdList(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        List<DS_CONTR_BLLG_SCHD_SMRYTMsg> asisTopSchdList = new ArrayList<DS_CONTR_BLLG_SCHD_SMRYTMsg>();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        List<BigDecimal> topSchdKeyList = (List<BigDecimal>) getSsmEZDClient().queryObjectList("getAsisTopSchdKeyList", ssmParam).getResultObject();
        if (topSchdKeyList.size() == 0) {
            return asisTopSchdList;
        }
        for (BigDecimal topSchdKey : topSchdKeyList) {
            DS_CONTR_BLLG_SCHD_SMRYTMsg inMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
            setValue(inMsg.glblCmpyCd, glblCmpyCd);
            setValue(inMsg.dsContrBllgSchdSmryPk, topSchdKey);
            inMsg = (DS_CONTR_BLLG_SCHD_SMRYTMsg) S21ApiTBLAccessor.findByKey(inMsg);
            if (inMsg != null) {
                asisTopSchdList.add(inMsg);
            }
        }
        return asisTopSchdList;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPK BigDecimal
     * @param fromDt String
     * @return DS_CONTR_BLLG_SCHDTMsg
     */
    public DS_CONTR_BLLG_SCHDTMsg getAsisSchdByPePkDt(String glblCmpyCd, BigDecimal dsContrPrcEffPk, String fromDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("fromDt", fromDt);
        // START 2018/03/30 U.Kim [QC#24924, ADD]
        ssmParam.put("invTpCd", INV_TP.CREDIT_MEMO);
        // END 2018/03/30 U.Kim [QC#24924, ADD]
        BigDecimal schdPk = (BigDecimal) getSsmEZDClient().queryObject("getAsisSchdByPePkDt", ssmParam).getResultObject();
        if (schdPk == null) {
            return null;
        }
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdPk, schdPk);
        inMsg = (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        return inMsg;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgSchdSmryPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getDelSchdPkListByTopSchdPk(String glblCmpyCd, BigDecimal dsContrBllgSchdSmryPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgSchdSmryPk", dsContrBllgSchdSmryPk);
        List<BigDecimal> delSchdPkList = (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDelSchdPkListByTopSchdPk", ssmParam).getResultObject();
        return delSchdPkList;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEfflPk BigDecimal
     * @return List<Map<String, BigDecimal>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, BigDecimal>> getBllgSchdForDeleteByPePk(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        return (List<Map<String, BigDecimal>>) getSsmEZDClient().queryObjectList("getBllgSchdForDeleteByPePk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEfflPk BigDecimal
     * @return List<Map<String, BigDecimal>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, BigDecimal>> getBllgSchdForDeleteByPePkAndDt(String glblCmpyCd, BigDecimal dsContrPrcEffPk, String fixDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("fixDt", fixDt);
        return (List<Map<String, BigDecimal>>) getSsmEZDClient().queryObjectList("getBllgSchdForDeleteByPePkAndDt", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEfflPk BigDecimal
     * @return List<Map<String, BigDecimal>>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getTopSchdForDeleteByPePkAndDt(String glblCmpyCd, BigDecimal dsContrPrcEffPk, String fixDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("fixDt", fixDt);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getTopSchdForDeleteByPePkAndDt", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
0     * @return List<Map<String, BigDecimal>>
     */
    public String getFixDtFromSchd(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("stsClose", DS_BLLG_SCHD_STS.CLOSE);
        return (String) getSsmEZDClient().queryObject("getFixDtFromSchd", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @return List<Map<String, BigDecimal>>
     */
    public String getFixDtFromPe(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("stsExpr", DS_CONTR_DTL_STS.EXPIRED);
        return (String) getSsmEZDClient().queryObject("getFixDtFromPe", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @return BigDecimal
     */
    public DS_CONTR_PRC_EFF_MTRTMsgArray getPeMtrArray(String glblCmpyCd, BigDecimal dsContrPrcEffPk, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_PRC_EFF_MTRTMsg inMsg = new DS_CONTR_PRC_EFF_MTRTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrPrcEffPk01", dsContrPrcEffPk);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        inMsg.setSQLID("002");

        DS_CONTR_PRC_EFF_MTRTMsgArray getTMsgArray = (DS_CONTR_PRC_EFF_MTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        return getTMsgArray;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @param dsContrBllgSchdSmryPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getFixBaseTermAmtRate(String glblCmpyCd, BigDecimal dsContrPrcEffPk, BigDecimal dsContrBllgSchdSmryPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("dsContrBllgSchdSmryPk", dsContrBllgSchdSmryPk);
        ssmParam.put("stsClose", DS_BLLG_SCHD_STS.CLOSE);
        BigDecimal fixBaseTermAmtRate = (BigDecimal) getSsmEZDClient().queryObject("getFixBaseTermAmtRate", ssmParam).getResultObject();
        if (fixBaseTermAmtRate == null) {
            fixBaseTermAmtRate = BigDecimal.ZERO;
        }

        return fixBaseTermAmtRate;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @param dsContrBllgSchdSmryPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getFixBaseAdjAmt(String glblCmpyCd, BigDecimal dsContrPrcEffPk, BigDecimal dsContrBllgSchdSmryPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("dsContrBllgSchdSmryPk", dsContrBllgSchdSmryPk);
        ssmParam.put("stsClose", DS_BLLG_SCHD_STS.CLOSE);
        BigDecimal fixBaseAdjAmt = (BigDecimal) getSsmEZDClient().queryObject("getFixBaseAdjAmt", ssmParam).getResultObject();
        if (fixBaseAdjAmt == null) {
            fixBaseAdjAmt = BigDecimal.ZERO;
        }

        return fixBaseAdjAmt;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgSchdSmryPk BigDecimal
     * @return Map<String, BigDecimal>
     */
    public Map<String, BigDecimal> getBaseTermAmtAndAdjAmt(String glblCmpyCd, BigDecimal dsContrBllgSchdSmryPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgSchdSmryPk", dsContrBllgSchdSmryPk);
        Map<String, BigDecimal> resultMap = (Map<String, BigDecimal>) getSsmEZDClient().queryObject("getBaseTermAmtAndAdjAmt", ssmParam).getResultObject();

        return resultMap;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param prntDsContrBllgSchdPk BigDecimal
     * @return DS_CONTR_BLLG_SCHDTMsg
     */
    @SuppressWarnings("unchecked")
    public DS_CONTR_BLLG_SCHDTMsg getFleetChildSchd(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal prntDsContrBllgSchdPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("prntDsContrBllgSchdPk", prntDsContrBllgSchdPk);
        BigDecimal childSchdPk = (BigDecimal) getSsmEZDClient().queryObject("getFleetChildSchdPk", ssmParam).getResultObject();
        
        if (childSchdPk == null) {
            return null;
        }
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdPk, childSchdPk);
        return (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @return DS_CONTR_BLLG_MTRTMsg
     */
    public DS_CONTR_BLLG_MTRTMsg getBllgMtrTMsg(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        // mod start 2017/07/21 QC#20045
//        inMsg = (DS_CONTR_BLLG_MTRTMsg) S21CacheTBLAccessor.findByKey(inMsg);
        inMsg = (DS_CONTR_BLLG_MTRTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        // mod end   2017/07/21 QC#20045
        return inMsg;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getFixedTermAmtRate(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("closeSts", DS_BLLG_SCHD_STS.CLOSE);
        BigDecimal fixedTermAmtRate = (BigDecimal) getSsmEZDClient().queryObject("getFixedTermAmtRate", ssmParam).getResultObject();
        if (fixedTermAmtRate == null) {
            fixedTermAmtRate = BigDecimal.ZERO;
        }
        return fixedTermAmtRate;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @return BigDecimal
     */
    public BigDecimal getCurPeBasePrcDealAmt(String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("cancelSts", DS_CONTR_DTL_STS.CANCELLED);
        BigDecimal curPeBasePrcDealAmt = (BigDecimal) getSsmEZDClient().queryObject("getCurPeBasePrcDealAmt", ssmParam).getResultObject();
        if (curPeBasePrcDealAmt == null) {
            curPeBasePrcDealAmt = BigDecimal.ZERO;
        }
        return curPeBasePrcDealAmt;
    }
    // Add End   06/29/2016 <QC#7402>

    // START 2017/08/29 K.Kojima [QC#20057,ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @return String
     */
    public String getCurPeBllgCycleCdForBase(String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("cancelSts", DS_CONTR_DTL_STS.CANCELLED);
        String curPeBllgCycleCd = (String) getSsmEZDClient().queryObject("getCurPeBllgCycleCdForBase", ssmParam).getResultObject();
        return curPeBllgCycleCd;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @param slsDt String
     * @return String
     */
    public String getCurPeBllgCycleCdForUsage(String glblCmpyCd, BigDecimal dsContrBllgMtrPk, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("cancelSts", DS_CONTR_DTL_STS.CANCELLED);
        String curPeBllgCycleCd = (String) getSsmEZDClient().queryObject("getCurPeBllgCycleCdForUsage", ssmParam).getResultObject();
        return curPeBllgCycleCd;
    }
    // END 2017/08/29 K.Kojima [QC#20057,ADD]

    // START 2021/04/06 S.Go [QC#58642,ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @return String
     */
    public String getCurPeCumCopyEndDtForUsage(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        String curPeCumCopyEndDt = (String) getSsmEZDClient().queryObject("getCurPeCumCopyEndDtForUsage", ssmParam).getResultObject();
        return curPeCumCopyEndDt;
    }
    // END 2021/04/06 S.Go [QC#58642,ADD]

    // QC#57297 Add Start
    /**
     * @param glblCmpyCd String
     * @param preEfMtrfPk BigDecimal
     * @return boolean
     */
    public boolean removeContrPrcEffMtr(String glblCmpyCd, BigDecimal preEffMtrPk) {

        DS_CONTR_PRC_EFF_MTRTMsg inTMsg = new DS_CONTR_PRC_EFF_MTRTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.dsContrPrcEffMtrPk, preEffMtrPk);
        S21ApiTBLAccessor.logicalRemove(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }
    // QC#57297 Add End
    
    // mod start 2017/01/05 CSA QC#16399
    /**
     * @param glblCmpyCd String
     * @param preEffPk BigDecimal
     * @return boolean
     */
    public boolean removeContrPrcEff(String glblCmpyCd, BigDecimal preEffPk) {

        DS_CONTR_PRC_EFFTMsg inTMsg = new DS_CONTR_PRC_EFFTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.dsContrPrcEffPk, preEffPk);
        S21ApiTBLAccessor.logicalRemove(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }
    // mod end 2017/01/05 CSA QC#16399
    // Add Start 02/28/2017 <QC#17809>
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrPk BigDecimal
     * @return List<Map<String, Object>>
     */
    // START 2018/04/09 K.Kojima [QC#24802,MOD]
    // public List<Map<String, Object>> getSumAggBllgSchd(String glblCmpyCd, BigDecimal dsContrDtlPk) {
    public List<Map<String, Object>> getSumAggBllgSchd(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPk) {
    // END 2018/04/09 K.Kojima [QC#24802,MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        // START 2018/04/09 K.Kojima [QC#24802,ADD]
        ssmParam.put("dsContrPk", dsContrPk);
        // END 2018/04/09 K.Kojima [QC#24802,ADD]
        // START 2017/11/20 K.Kojima [QC#22683,ADD]
        ssmParam.put("credit", INV_TP.CREDIT_MEMO);
        // END 2017/11/20 K.Kojima [QC#22683,ADD]

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> getList = (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSumAggBllgSchd", ssmParam).getResultObject();
        return getList;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    // START 2018/05/18 U.Kim [QC#24854, MOD]
    // public List<Map<String, Object>> getSumAggBllgSchdSmry(String glblCmpyCd, BigDecimal dsContrDtlPk) {
    public List<Map<String, Object>> getSumAggBllgSchdSmry(String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt) {
    // END 2018/05/18 U.Kim [QC#24854, MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        // START 2017/11/20 K.Kojima [QC#22683,ADD]
        ssmParam.put("credit", INV_TP.CREDIT_MEMO);
        // END 2017/11/20 K.Kojima [QC#22683,ADD]
        // START 2018/05/18 U.Kim [QC#24854, ADD]
        ssmParam.put("slsDt", slsDt);
        // END 2018/05/18 U.Kim [QC#24854, ADD]

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> getList = (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSumAggBllgSchdSmry", ssmParam).getResultObject();
        return getList;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    // START 2018/05/18 U.Kim [QC#24854, MOD]
    // public List<Map<String, Object>> getSumAggPrcEff(String glblCmpyCd, BigDecimal dsContrDtlPk) {
    public List<Map<String, Object>> getSumAggPrcEff(String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt) {
    // END 2018/05/18 U.Kim [QC#24854, MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        // START 2017/11/20 K.Kojima [QC#22683,ADD]
        ssmParam.put("credit", INV_TP.CREDIT_MEMO);
        // END 2017/11/20 K.Kojima [QC#22683,ADD]
        // START 2018/05/18 U.Kim [QC#24854, ADD]
        ssmParam.put("slsDt", slsDt);
        // END 2018/05/18 U.Kim [QC#24854, ADD]

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> getList = (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSumAggPrcEff", ssmParam).getResultObject();
        return getList;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSumAggContrDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        // START 2017/11/20 K.Kojima [QC#22683,ADD]
        ssmParam.put("credit", INV_TP.CREDIT_MEMO);
        // END 2017/11/20 K.Kojima [QC#22683,ADD]

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> getList = (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSumAggContrDtl", ssmParam).getResultObject();
        return getList;
    }

    // Add End   02/28/2017 <QC#17809>

    // add start 2017/06/29 QC#18349
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param bllgSchdThruDt String
     * @return String
     */
    public String getInvedMaxBllgSchdThruDtForBase(String glblCmpyCd, BigDecimal dsContrDtlPk, String bllgSchdThruDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("bllgSchdThruDt", bllgSchdThruDt);
        return (String) getSsmEZDClient().queryObject("getInvedMaxBllgSchdThruDtForBase", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param bllgSchdThruDt String
     * @return BigDecimal
     */
    public BigDecimal getBllgSchdSmryForUpdateBase(String glblCmpyCd, BigDecimal dsContrDtlPk, String lastBilledDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("lastBilledDt", lastBilledDt);
        return (BigDecimal) getSsmEZDClient().queryObject("getBllgSchdSmryForUpdateBase", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param bllgSchdThruDt String
     * @return BigDecimal
     */
    public BigDecimal getPcrEffForUpdateBase(String glblCmpyCd, BigDecimal dsContrDtlPk, String lastBilledDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("lastBilledDt", lastBilledDt);
        return (BigDecimal) getSsmEZDClient().queryObject("getPcrEffForUpdateBase", ssmParam).getResultObject();
    }
    // add end 2017/06/29 QC#18349

    // START 2017/06/30 K.Kitachi [QC#18288, ADD]
    /**
     * @param glblCmpyCd String
     * @param slsDt String
     * @param dsContrDtlPk BigDecimal
     * @param baseChrgFlg String
     * @param usgChrgFlg String
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getPrcEffPkForCur(String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk, String baseChrgFlg, String usgChrgFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("baseChrgFlg", baseChrgFlg);
        ssmParam.put("usgChrgFlg", usgChrgFlg);

        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getPrcEffForCur", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @return CONTR_XS_COPYTMsgArray
     */
    public CONTR_XS_COPYTMsgArray getXsCopyTMsgArray(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        CONTR_XS_COPYTMsg inMsg = new CONTR_XS_COPYTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        inMsg.setSQLID("002");
        return (CONTR_XS_COPYTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }
    // END 2017/06/30 K.Kitachi [QC#18288, ADD]

    //START 2017/08/22 K.Kasai [QC#18639,ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, BigDecimal>> getPrcEffPkListForAllowAdj(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (List<Map<String, BigDecimal>>) getSsmEZDClient().queryObjectList("getPrcEffPkListForAllowAdj", ssmParam).getResultObject();
    }

    //START 2017/09/21 K.Kasai [QC#21060,ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @param tierNum BigDecimal
     * @return List<Map<String, BigDecimal>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getDsContrBllgSchdMtrForAllowAdj(String glblCmpyCd, BigDecimal dsContrPrcEffPk, BigDecimal tierNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("tierNum", tierNum);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getDsContrBllgSchdMtrForAllowAdj", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @param tierNum BigDecimal
     * @return List<Map<String, BigDecimal>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, BigDecimal>> getSumAllowAdjInfo(String glblCmpyCd, BigDecimal dsContrPrcEffPk, BigDecimal tierNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("tierNum", tierNum);
        return (List<Map<String, BigDecimal>>) getSsmEZDClient().queryObjectList("getSumAllowAdjInfo", ssmParam).getResultObject();
    }
    //END 2017/09/21 K.Kasai [QC#21060,MOD]
    //END 2017/08/22 K.Kasai [QC#18639,ADD]
    //START 2017/09/21 K.Kasai [QC#21060,ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return List<Map<String, BigDecimal>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, BigDecimal>> getTierCnt(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        return (List<Map<String, BigDecimal>>) getSsmEZDClient().queryObjectList("getTierCnt", ssmParam).getResultObject();
    }
    //END 2017/09/21 K.Kasai [QC#21060,ADD]

    // START 2017/09/26 T.Kanasaka [QC#21414,ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrBllgSchdPk BigDecimal
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getCalcMtrSchdRelnPk(String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgSchdPk", dsContrBllgSchdPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getCalcMtrSchdRelnPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<Map<String, Object>>
     * @return boolean
     */
    public boolean removeCalcMtrSchdReln(String glblCmpyCd, List<Map<String, Object>> pkList) {
        if (pkList == null) {
            return true;
        }

        for (Map<String, Object> pk : pkList) {
            CALC_MTR_SCHD_RELNTMsg inTMsg = new CALC_MTR_SCHD_RELNTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.dsContrBllgSchdPk, (BigDecimal) pk.get("DS_CONTR_BLLG_SCHD_PK"));
            setValue(inTMsg.svcPhysMtrReadPk, (BigDecimal) pk.get("SVC_PHYS_MTR_READ_PK"));
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }
    // END 2017/09/26 T.Kanasaka [QC#21414,ADD]

    // add start 2017/10/02 QC#21567
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param baseChrgFlg String
     * @param usgChrgFlg String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getBllgSchdForNextBllgDtUpd(String glblCmpyCd, BigDecimal dsContrDtlPk, String baseChrgFlg, String usgChrgFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("baseChrgFlg", baseChrgFlg);
        ssmParam.put("usgChrgFlg", usgChrgFlg);
        ssmParam.put("aggregate", DS_CONTR_DTL_TP.AGGREGATE);

        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getBllgSchdForNextBllgDtUpd", ssmParam).getResultObject();
    }
    // add end 2017/10/02 QC#21567

    // Add Start 10/24/2017 <QC#22008>
    /**
     * @param glblCmpyCd String
     * @param prntSvcInvLinePk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getOrgSvcINvLinePkFleetMachine(String glblCmpyCd, BigDecimal prntSvcInvLinePk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prntSvcInvLinePk", prntSvcInvLinePk);

        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getOrgSvcINvLinePkFleetMachine", ssmParam).getResultObject();
    }
    // Add End   10/24/2017 <QC#22008>
    // Add Start 2017/10/26 QC#21815
    public BigDecimal getNewXsCopyQtyForAggLine(String glblCmpyCd, BigDecimal svcCrRebilDtlPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, int tierCnt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("tierCnt", tierCnt);
        return (BigDecimal) getSsmEZDClient().queryObject("getNewXsCopyQtyForAggLine", ssmParam).getResultObject();
    }
    // Add End 2017/10/26 QC#21815

    // START 2017/11/01 K.Kojima [QC#21859,ADD]
    public BigDecimal getUnScheduleBasePrice(String glblCmpyCd, BigDecimal dsContrPrcEffPk, String bllgSchdThruDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("bllgSchdThruDt", bllgSchdThruDt);
        return (BigDecimal) getSsmEZDClient().queryObject("getUnScheduleBasePrice", ssmParam).getResultObject();
    }
    // END 2017/11/01 K.Kojima [QC#21859,ADD]
    // Add Start 2017/11/24 QC#22683
    public Map<String, Object> getSumPrcEff(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("credit", INV_TP.CREDIT_MEMO);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getSumPrcEff", ssmParam).getResultObject();
    }

    public Map<String, Object> getSumBllgSchdSmry(String glblCmpyCd, BigDecimal dsContrBllgSchdSmryPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgSchdSmryPk", dsContrBllgSchdSmryPk);
        ssmParam.put("credit", INV_TP.CREDIT_MEMO);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getSumBllgSchdSmry", ssmParam).getResultObject();
    }
    // Add End 2017/11/24 QC#22683

    // START 2018/04/20 K.Kojima [QC#25595,ADD]
    public List<Map<String, Object>> getSumBllgSchdMtr(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPk", dsContrPk);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("perBllgCycleDaily", BLLG_CYCLE.DAILY);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSumBllgSchdMtr", ssmParam).getResultObject();
    }
    // END 2018/04/20 K.Kojima [QC#25595,ADD]

    // Add Start 2018/05/07 QC#25409
    public List<BigDecimal> getUnapprovedUsgChrgForDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("bllgCpltStsCd", BLLG_CPLT_STS.SCHEDULED);
        // START 2018/12/20 K.Kitachi [QC#29647, ADD]
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        // END 2018/12/20 K.Kitachi [QC#29647, ADD]
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getUnapprovedUsgChrgForDtl", param).getResultObject();
    }

    public List<BigDecimal> getUnapprovedUsgChrg(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("bllgCpltStsCd", BLLG_CPLT_STS.SCHEDULED);
        // START 2018/12/20 K.Kitachi [QC#29647, ADD]
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        // END 2018/12/20 K.Kitachi [QC#29647, ADD]
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getUnapprovedUsgChrg", param).getResultObject();
    }

    public void updateBllgHoldFlgForDsContrDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        inMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
        if (inMsg == null) {
            return;
        }
        // START 2019/07/18 [QC#51706, MOD]
        if (hasManualHld(glblCmpyCd, inMsg.dsContrPk.getValue(), dsContrDtlPk, null)) {
            return;
        }
        // END 2019/07/18 [QC#51706, MOD]
        if (ZYPCommonFunc.hasValue(inMsg.bllgHldFlg) && ZYPConstant.FLG_OFF_N.equals(inMsg.bllgHldFlg.getValue())) {
            return;
        }
        setValue(inMsg.bllgHldFlg, FLG_OFF_N);

        S21ApiTBLAccessor.update(inMsg);
    }

    public void updateBllgHoldFlgForDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        // START 2019/07/18 [QC#51706, MOD]
        if (hasManualHld(glblCmpyCd, dsContrPk, null, null)) {
            return;
        }
        // END   2019/07/18 [QC#51706, MOD]
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrPk, dsContrPk);
        inMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
        if (inMsg == null) {
            return;
        }
        if (ZYPCommonFunc.hasValue(inMsg.bllgHldFlg) && ZYPConstant.FLG_OFF_N.equals(inMsg.bllgHldFlg.getValue())) {
            return;
        }
        setValue(inMsg.bllgHldFlg, FLG_OFF_N);

        S21ApiTBLAccessor.update(inMsg);
    }
    // Add End 2018/05/07 QC#25409

    // START 2019/07/18 [QC#51706, MOD]
    // START 2018/12/20 K.Kitachi [QC#29647, ADD]
    public void updateBllgHoldFlgForDsContrBllgMtr(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        if (hasManualHld(glblCmpyCd, dsContrPk, dsContrDtlPk, dsContrBllgMtrPk)) {
            return;
        }

        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        inMsg = (DS_CONTR_BLLG_MTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
        if (inMsg == null) {
            return;
        }
        if (ZYPCommonFunc.hasValue(inMsg.bllgHldFlg) && ZYPConstant.FLG_OFF_N.equals(inMsg.bllgHldFlg.getValue())) {
            return;
        }
        setValue(inMsg.bllgHldFlg, FLG_OFF_N);

        S21ApiTBLAccessor.update(inMsg);
    }
    // END 2019/07/18 [QC#51706, MOD]

    public void updateBllgHoldFlgForDsContrPrcEff(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        inMsg = (DS_CONTR_PRC_EFFTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
        if (inMsg == null) {
            return;
        }
        if (ZYPCommonFunc.hasValue(inMsg.bllgHldFlg) && ZYPConstant.FLG_OFF_N.equals(inMsg.bllgHldFlg.getValue())) {
            return;
        }
        setValue(inMsg.bllgHldFlg, FLG_OFF_N);

        S21ApiTBLAccessor.update(inMsg);
    }

    public List<BigDecimal> getBillingHoldBllgMtr(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getBillingHoldBllgMtr", paramMap).getResultObject();
    }

    public List<BigDecimal> getBillingHoldPrcEff(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getBillingHoldPrcEff", paramMap).getResultObject();
    }

    public BigDecimal getBillingHoldAggLine(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("dsContrPk", dsContrPk);
        paramMap.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        return (BigDecimal) getSsmEZDClient().queryObject("getBillingHoldAggLine", paramMap).getResultObject();
    }
    // END 2018/12/20 K.Kitachi [QC#29647, ADD]

    // START 2018/05/23 K.Kojima [QC#23302,ADD]
    public List<Map<String, Object>> getDsContrAddlChrgPkForTerminate(String glblCmpyCd, BigDecimal dsContrDtlPk, String trmnDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("trmnDt", trmnDt);
        param.put("svcInvChrgTpAddlChrg", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        param.put("addlChrgInvTpBase", ADDL_CHRG_INV_TP.BASE);
        param.put("invTpCredit", INV_TP.CREDIT_MEMO);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getDsContrAddlChrgPkForTerminate", param).getResultObject();
    }

    public Map<String, Object> getSvcInvLineInfoForAdditionalChargeTerminate(String glblCmpyCd, BigDecimal svcInvLinePk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcInvLinePk", svcInvLinePk);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getSvcInvLineInfoForAdditionalChargeTerminate", param).getResultObject();
    }

    public String getIntgMdseCdForAddlChrgTpV(String glblCmpCd, String addlChrgTpCd) {
        if (!ZYPCommonFunc.hasValue(addlChrgTpCd)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpCd);
        param.put("addlChrgTpCd", addlChrgTpCd);
        return (String) getSsmEZDClient().queryObject("getIntgMdseCdForAddlChrgTpV", param).getResultObject();
    }
    // END 2018/05/23 K.Kojima [QC#23302,ADD]
    // Add Start 2018/06/20 QC#26766
    public String getContrCatgByDtlPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        return (String) getSsmEZDClient().queryObject("getContrCatgByDtlPk", param).getResultObject();
    }

    // SVC_CONTR_BLLG           (DS_CONTR_BLLG_SCHD_PK) SQL_ID:004
    public SVC_CONTR_BLLGTMsgArray getSvcContrBllgTMsgArray(String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {
        SVC_CONTR_BLLGTMsg inMsg = new SVC_CONTR_BLLGTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrBllgSchdPk01", dsContrBllgSchdPk);
        return (SVC_CONTR_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    // SVC_CONTR_BASE_BLLG      (SVC_CONTR_BLLG_PK)     SQL_ID:001
    public SVC_CONTR_BASE_BLLGTMsgArray getSvcContrBaseBllgTMsgArray(String glblCmpyCd, BigDecimal svcContrBllgPk) {
        SVC_CONTR_BASE_BLLGTMsg inMsg = new SVC_CONTR_BASE_BLLGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcContrBllgPk01", svcContrBllgPk);
        return (SVC_CONTR_BASE_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    // SVC_CONTR_MTR_BLLG       (SVC_CONTR_BLLG_PK)     SQL_ID:001
    public SVC_CONTR_MTR_BLLGTMsgArray getSvcContrMtrBllgTMsgArray(String glblCmpyCd, BigDecimal svcContrBllgPk) {
        SVC_CONTR_MTR_BLLGTMsg inMsg = new SVC_CONTR_MTR_BLLGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcContrBllgPk01", svcContrBllgPk);
        return (SVC_CONTR_MTR_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    // SVC_CONTR_XS_MTR_BLLG    (SVC_CONTR_MTR_BLLG_PK) SQL_ID:001
    public SVC_CONTR_XS_MTR_BLLGTMsgArray getSvcContrXsMtrBllgTMsgArray(String glblCmpyCd, BigDecimal svcContrMtrBllgPk) {
        SVC_CONTR_XS_MTR_BLLGTMsg inMsg = new SVC_CONTR_XS_MTR_BLLGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcContrMtrBllgPk01", svcContrMtrBllgPk);
        return (SVC_CONTR_XS_MTR_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    // SVC_CONTR_ADDL_CHRG_BLLG (SVC_CONTR_BLLG_PK)     SQL_ID:001
    public SVC_CONTR_ADDL_CHRG_BLLGTMsgArray getSvcContrAddlChrgBllgTMsgArray(String glblCmpyCd, BigDecimal svcContrBllgPk) {
        SVC_CONTR_ADDL_CHRG_BLLGTMsg inMsg = new SVC_CONTR_ADDL_CHRG_BLLGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcContrBllgPk01", svcContrBllgPk);
        return (SVC_CONTR_ADDL_CHRG_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    // SVC_CONTR_BLLG_ALLOC     (SVC_CONTR_BASE_BLLG_PK) SQL_ID:001
    public SVC_CONTR_BLLG_ALLOCTMsgArray getSvcContrBaseBllgAllocTMsgArray(String glblCmpyCd, BigDecimal svcContrBaseBllgPk) {
        SVC_CONTR_BLLG_ALLOCTMsg inMsg = new SVC_CONTR_BLLG_ALLOCTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcContrBaseBllgPk01", svcContrBaseBllgPk);
        return (SVC_CONTR_BLLG_ALLOCTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    // SVC_CONTR_BLLG_ALLOC     (SVC_CONTR_BASE_BLLG_PK) SQL_ID:001
    public SVC_CONTR_BLLG_ALLOCTMsgArray getSvcContrMtrBllgAllocTMsgArray(String glblCmpyCd, BigDecimal svcContrMtrBllgPk) {
        SVC_CONTR_BLLG_ALLOCTMsg inMsg = new SVC_CONTR_BLLG_ALLOCTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcContrMtrBllgPk01", svcContrMtrBllgPk);
        return (SVC_CONTR_BLLG_ALLOCTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }
    // Add End 2018/06/20 QC#26766
    // START 2018/07/03 K.Kim [QC#26726,ADD]
    public BigDecimal getCreditAndRebillCnt(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        List<String> svcCrRebilStsCdList = new ArrayList<String>();
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.APPROVED);
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.PROCESSED);
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.CANCELLED);
        param.put("svcCrRebilStsCdList", svcCrRebilStsCdList);
        return (BigDecimal) getSsmEZDClient().queryObject("getCreditAndRebillCnt", param).getResultObject();
    }
    // END 2018/07/03 K.Kim [QC#26726,ADD]
    // START 2018/07/17 [QC#25959,ADD]
    public List<Map<String, Object>> getRecovPrntPkForAggBase(String glblCmpyCd, BigDecimal aggDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("aggDtlPk", aggDtlPk);
        param.put("invTpCd", INV_TP.INVOICE);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getRecovPrntPkForAggBase", param).getResultObject();
    }

    public List<Map<String, Object>> getRecovPrntPkForAggUsg(String glblCmpyCd, BigDecimal aggDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("aggDtlPk", aggDtlPk);
        param.put("invTpCd", INV_TP.INVOICE);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getRecovPrntPkForAggUsg", param).getResultObject();
    }
    // END   2018/07/17 [QC#25959,ADD]

    // START 2019/03/05 K.Kitachi [QC#30619, ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getResetAccSchdList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.ACCESSORIES);
        param.put("invTpCd", INV_TP.INVOICE);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getResetAccSchdList", param).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgSchdPk BigDecimal
     * @return DS_CONTR_BLLG_SCHDTMsg
     */
    public DS_CONTR_BLLG_SCHDTMsg getDsContrBllgSchdTMsgForUpdate(String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        return (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }
    // END 2019/03/05 K.Kitachi [QC#30619, ADD]

    // START 2019/06/13 K.Kitachi [QC#50811, ADD]
    /**
     * @param param Map<String, Object>
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getRecovAccSchdReln(Map<String, Object> param) {
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getRecovAccSchdReln", param).getResultObject();
    }
    // END 2019/06/13 K.Kitachi [QC#50811, ADD]
    // START 2019/07/18 [QC#51706,ADD]
    private boolean hasManualHld(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("dsContrBllgMtrPk", dsContrBllgMtrPk);

        BigDecimal manulaHldCnt = (BigDecimal) getSsmEZDClient().queryObject("getManualHldCnt", param).getResultObject();
        if (manulaHldCnt == null || manulaHldCnt.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        return true;
    }
    // END   2019/07/18 [QC#51706,ADD]

    // START 2020/03/18 K.Kitachi [QC#55693, ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getResetXsCopyPkOfBllgSchdMtrList(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getResetXsCopyPkOfBllgSchdMtrList", param).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgSchdMtrPk BigDecimal
     * @return DS_CONTR_BLLG_SCHDTMsg
     */
    public DS_CONTR_BLLG_SCHD_MTRTMsg getDsContrBllgSchdMtrTMsgForUpdate(String glblCmpyCd, BigDecimal dsContrBllgSchdMtrPk) {
        DS_CONTR_BLLG_SCHD_MTRTMsg inMsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdMtrPk, dsContrBllgSchdMtrPk);
        return (DS_CONTR_BLLG_SCHD_MTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }
    // END 2020/03/18 K.Kitachi [QC#55693, ADD]

    // START 2020/09/24 K.Kitachi [QC#57667, ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @param delFlg String
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getPrcEffMtrForCancel(String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt, String delFlg) {
        return getPrcEffMtrForCancel(glblCmpyCd, dsContrDtlPk, slsDt, delFlg, null);
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @param delFlg String
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getPrcEffForCancel(String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt, String delFlg) {
        return getPrcEffForCancel(glblCmpyCd, dsContrDtlPk, slsDt, delFlg, null);
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param delFlg String
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getDsContrBllgSchdSmryForCancel(String glblCmpyCd, BigDecimal dsContrDtlPk, String delFlg) {
        return getDsContrBllgSchdSmryForCancel(glblCmpyCd, dsContrDtlPk, delFlg, null);
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param delFlg String
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getDsContrBllgSchdForCancel(String glblCmpyCd, BigDecimal dsContrDtlPk, String delFlg) {
        return getDsContrBllgSchdForCancel(glblCmpyCd, dsContrDtlPk, delFlg, null);
    }
    // END 2020/09/24 K.Kitachi [QC#57667, ADD]

    // START 2022/03/14 K.Kitachi [QC#59684, ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param trmnDt String
     * @return List<BigDecimal>
     */
    // START 2023/10/05 H.Iinuma [QC#61675, MOD]
    // public List<BigDecimal> getInvoicedSchdPkByBllgMtrPk(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
    public List<BigDecimal> getInvoicedSchdPkByBllgMtrPk(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String trmnDt) {
    // END 2023/10/05 H.Iinuma [QC#61675, MOD]

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        // START 2023/10/05 H.Iinuma [QC#61675, ADD]
        ssmParam.put("trmnDt", trmnDt);
        // END 2023/10/05 H.Iinuma [QC#61675, ADD]
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getInvoicedSchdPkByBllgMtrPk", ssmParam).getResultObject();
    }
    // END 2022/03/14 K.Kitachi [QC#59684, ADD]

    // ADDD START 2022/05/09 QC#59979 [CCI-QC#3578, MOD]
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param trmnDt String
     * @return BigDecimal
     */
    public BigDecimal countRefundBllgSchd(String glblCmpyCd, BigDecimal dsContrDtlPk, String trmnDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("trmnDt", trmnDt);
        return (BigDecimal) getSsmEZDClient().queryObject("countRefundBllgSchd", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param trmnDt String
     * @return BigDecimal
     */
    public BigDecimal countUnbilledBllgSchd(String glblCmpyCd, BigDecimal dsContrDtlPk, String trmnDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("trmnDt", trmnDt);
        return (BigDecimal) getSsmEZDClient().queryObject("countUnbilledBllgSchd", ssmParam).getResultObject();
    }
    // ADD END   2022/05/09 QC#59979 [CCI-QC#3578, MOD]

    // add start 2022/07/07 QC#60167
    public Map<String, Object> getSvcInvForAddlChrgTerminate(String glblCmpyCd, BigDecimal dsContrDtlPk, String trmnDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("trmnDt", trmnDt);
        param.put("invTpInvoice", INV_TP.INVOICE);
        param.put("svcInvChrgTpAddlChrg", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        param.put("svcInvChrgTpBaseChrg", SVC_INV_CHRG_TP.BASE_CHARGE);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getSvcInvForAddlChrgTerminate", param).getResultObject();
    }
    // add end 2022/07/07 QC#60167
}
