/**
 * <pre>
 * Update / Inser CPO Table
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/07   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * 2017/06/12   Fujitsu         H.Ikeda         Update          QC#18820
 * 2017/06/13   Fujitsu         S.Takami        Update          S21_NA#18869-2
 * 2017/06/16   Fujitsu         S.Takami        Update          S21_NA#19288
 * 2017/06/20   Fujitsu         S.Takami        Update          S21_NA#19288-2
 * 2017/10/13   Fujitsu         R.Nakamura      Update          S21_NA#20246(L3 Sol#454)
 * 2017/12/11   Fujitsu         N.Sugiura       Update          S21_NA#20164
 * 2018/04/17   Fujitsu         R.Nakamura      Update          S21_NA#22187
 * 2018/05/11   Fujitsu         T.Aoi           Update          S21_NA#22139
 * 2018/08/20   Fujitsu         K.Ishizuka      Update          S21_NA#27823
 * 2019/11/12   Fujitsu         S.Kosaka        Update          QC#54413-1
 * 2023/10/13   Hitachi         D.Yoshida       Update          QC#61077
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.dbAccs;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByCondition;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.insert;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.remove;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.update;
import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;
import static parts.dbcommon.EZDTBLAccessor.RTNCD_NORMAL;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTStringItem;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC153001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Query;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.logWriter.NWZC150001CpouLogWriter;
import com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

public class NWZC150001CpouUpdCpo {

    // 2017/06/13 S21_NA#18869-2 Del Start
//    /**  Instance */
//    private static NWZC150001CpouUpdCpo MY_INSTANCE = new NWZC150001CpouUpdCpo();
    // 2017/06/13 S21_NA#18869-2 Del End

    private S21SsmBatchClient ssmClient = null;
    /** Class Name */
    private static final String CLASS_NM = "NWZC150001CpouUpdCpo";

    public NWZC150001CpouUpdCpo() {
        ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    // 2017/06/13 S21_NA#18869-2 Del Start
//    public static NWZC150001CpouUpdCpo getInstance() {
//        return MY_INSTANCE;
//    }
    // 2017/06/13 S21_NA#18869-2 Del End

    /**
     * CPO update
     * 
     * <pre>
     * Save   : Data is already deleted, and it has registered newly when having registered once.
     * Submit : Data is already deleted, and it has registered newly when having registered once.
     * Modify : It updates it to the registered data.
     * Cancel : It updates it to the registered data.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param pList List<NWZC150002PMsg>
     */
    public void updateCpo(NWZC150001PMsg pMsg, NWZC153001PMsg rtnPMsg, NWZC150001CpouBean cpouBean, CPOTMsg dbCpoTMsg) {

        final String methodNm = "updateCpo";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(CLASS_NM, methodNm);

        boolean isExistsCpo = false;
        // 2017/06/12 QC#18820 Add Start
        boolean diChkHldFlg = false;
        // 2017/06/12 QC#18820 Add End
        // 2019/11/12 QC#54413-1 Add Start
        String beforeOrdHdrStsCd = null;
        // 2019/11/12 QC#54413-1 Add End
        CPOTMsg reqCpoTMsg = null;
        try {
            // 2017/06/12 QC#18820 Add Start
            if (dbCpoTMsg != null) {
                // 2017/07/03 S21_NA#19737 Mod Start
//                if (ZYPConstant.FLG_ON_Y.equals(dbCpoTMsg.diChkHldFlg.getValue())) {
//                    diChkHldFlg = true;
//                }
                boolean isDiCheckHld = ZYPConstant.FLG_ON_Y.equals(dbCpoTMsg.diChkHldFlg.getValue());
                boolean isAlreadyBooked = ZYPCommonFunc.hasValue(dbCpoTMsg.ordBookReqTs) || ZYPCommonFunc.hasValue(dbCpoTMsg.ordBookReqUsrId);
                if (isDiCheckHld && isAlreadyBooked) {
                    diChkHldFlg = true;
                }
                // 2017/07/03 S21_NA#19737 Mod End
                // 2019/11/12 QC#54413-1 Add Start
                beforeOrdHdrStsCd = dbCpoTMsg.ordHdrStsCd.getValue();
                // 2019/11/12 QC#54413-1 Add End
            }
            // 2017/06/12 QC#18820 Add End

            // 1.2WDS modify start -->
//            CPOTMsg dbCpoTMsg = null;
            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            DS_CPOTMsg dbDsCpoTMsg = null;
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End
            if (hasValue(cpouBean.getCpoOrdNum())) {
//                reqCpoTMsg = new CPOTMsg();
//                setValue(reqCpoTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
//                setValue(reqCpoTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
//                dbCpoTMsg = (CPOTMsg) findByKey(reqCpoTMsg);

                // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                DS_CPOTMsg reqDsCpoTMsg = new DS_CPOTMsg();
//                setValue(reqDsCpoTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
//                setValue(reqDsCpoTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
//                dbDsCpoTMsg = (DS_CPOTMsg) findByKey(reqDsCpoTMsg);
//                // S21_NA#1952
//                if (dbDsCpoTMsg != null) {
//                    cpoBean.setCpoUpdVrsnNum(dbDsCpoTMsg.cpoUpdVrsnNum.getValue());
//                }
                // 2017/03/29 S21_NA#Review structure Lv.1 Del End
                // 2017/03/29 S21_NA#Review structure Lv.1 Add Start
                if (dbCpoTMsg != null) {
                    cpouBean.setCpoUpdVrsnNum(dbCpoTMsg.cpoUpdVrsnNum.getValue());
                    isExistsCpo = true;
                }
                // 2017/03/29 S21_NA#Review structure Lv.1 Add End
            }

            final String rqstTpCd = cpouBean.getRqstTpCd();

            if (NWZC150001CpouConstant.CPO_SAVE.equals(rqstTpCd) //
                    || NWZC150001CpouConstant.CPO_SUBMIT.equals(rqstTpCd)) {

                // Del Start 2017/12/11 QC#20164
                // Add Start 2017/10/13 QC#20246(L3 Sol#454)
                // String oldSellToFirstRefCmntTxt = null;
                // if (null != dbCpoTMsg) {
                //     oldSellToFirstRefCmntTxt = dbCpoTMsg.sellToFirstRefCmntTxt.getValue();
                // }
                // Add End 2017/10/13 QC#20246(L3 Sol#454)
                // Del End 2017/12/11 QC#20164
                if (dbCpoTMsg != null //
                        && !S21StringUtil.isEquals(cpouBean.getReNumCpoOrdNum(), cpouBean.getCpoOrdNum_A1())) {
                    // ***** [remove]
                    remove(dbCpoTMsg);
                    isExistsCpo = false;
                }

                // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                if (dbDsCpoTMsg != null) {
//                    // ***** [remove]
//                    remove(dbDsCpoTMsg);
//                }
                // 2017/03/29 S21_NA#Review structure Lv.1 Del End

                reqCpoTMsg = new CPOTMsg();
                // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                DS_CPOTMsg reqDsCpoTMsg = new DS_CPOTMsg();
                // 2017/03/29 S21_NA#Review structure Lv.1 Del End

                // --------------------------------------------------
                // SAVE
                // --------------------------------------------------
                if (NWZC150001CpouConstant.CPO_SAVE.equals(rqstTpCd)) {
                    boolean isExistSaveRqst = false;
                    for (NWZC150001CpouDetailBean cpouDtlBean : cpouBean.getDtlBeanList()) {
                        if (NWZC150001CpouConstant.CPO_DTL_SAVE.equals(cpouDtlBean.getDtlRqstTpCd())) {
                            isExistSaveRqst = true;
                            break;
                        }
                    }
                    // 2017/04/21 S21_NA#Review structure Lv.2 Add Start check exists return save data.
                    if (!isExistSaveRqst && rtnPMsg != null) {
                        for (int i = 0; i < rtnPMsg.ordRtrnDtlList.getValidCount(); i++) {
                            if (S21StringUtil.isEquals(NWZC153001Constant.RQST_DTL_SAVE, rtnPMsg.ordRtrnDtlList.no(i).xxRqstTpCd.getValue())) {
                                isExistSaveRqst = true;
                                break;
                            }
                        }
                    }
                    // 2017/04/21 S21_NA#Review structure Lv.2 Add End check exists return save data.
                    // 2017/04/25 S21_NA#Review structure Lv.2 Add Start Save Header Mode
                    if (!isExistSaveRqst //
                            && cpouBean.getDtlBeanList().isEmpty() //
                            && (rtnPMsg == null || rtnPMsg.ordRtrnDtlList.getValidCount() == 0)) {
                        // Header Save Mode
                        isExistSaveRqst = true;
                    }
                    // 2017/04/25 S21_NA#Review structure Lv.2 Add End Save Header Mode
                    if (!isExistSaveRqst) {
                        return;
                    }
                    // Mod Start 2017/12/11 QC#20164
                    // 2017/06/12 QC#18820 Mod Start
                    // Mod Start 2017/10/13 QC#20247(L3 Sol#454)
//                    setCpoTMsgForSave(cpouBean, reqCpoTMsg, diChkHldFlg);
                    // setCpoTMsgForSave(cpouBean, reqCpoTMsg, diChkHldFlg, isExistsCpo, oldSellToFirstRefCmntTxt);
                    // Mod End 2017/10/13 QC#20247(L3 Sol#454)
                    // 2017/06/12 QC#18820 Mod End
                    setCpoTMsgForSave(cpouBean, reqCpoTMsg, diChkHldFlg);
                    // Mod End 2017/12/11 QC#20164

                    // --------------------------------------------------
                    // SUBMIT
                    // --------------------------------------------------
                } else {
                    // Mod Start 2017/12/11 QC#20164
                    // Mod Start 2017/10/13 QC#20247(L3 Sol#454)
//                    setCpoTMsgForSubmit(cpouBean, reqCpoTMsg);
                    // setCpoTMsgForSubmit(cpouBean, reqCpoTMsg, isExistsCpo, oldSellToFirstRefCmntTxt);
                    // Mod End 2017/10/13 QC#20247(L3 Sol#454)
                    setCpoTMsgForSubmit(cpouBean, reqCpoTMsg);
                    // Mod End 2017/12/11 QC#20164
                }
                // 2017/03/29 S21_NA#Review structure Lv.1 Mod Start
//                setDsCpoTMsg(rqstTpCd, cpoBean, reqDsCpoTMsg); // 2015/08/27 CSA Mod
                // 2018/08/20 S21_NA#27823 Mod Start
                // setWdsColumnsOfCpo(rqstTpCd, cpouBean, reqCpoTMsg);
                // 2019/11/12 QC#54413-1 Mod Start
                //setWdsColumnsOfCpo(rqstTpCd, cpouBean, reqCpoTMsg, dbCpoTMsg);
                setWdsColumnsOfCpo(rqstTpCd, cpouBean, reqCpoTMsg, dbCpoTMsg, beforeOrdHdrStsCd);
                // 2019/11/12 QC#54413-1 Mod End
                // 2018/08/20 S21_NA#27823 Mod End
                // 2017/03/29 S21_NA#Review structure Lv.1 Mod End

                // ***** [insert]
                if (isExistsCpo) {
                    update(reqCpoTMsg);
                } else {
                    insert(reqCpoTMsg);
                }
                if (!RTNCD_NORMAL.equals(reqCpoTMsg.getReturnCode())) {
                    throw new S21AbendException("updateCPO : Insert Error. CPO_ORD_NUM=[" + reqCpoTMsg.cpoOrdNum.getValue() + "]");
                }

                // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                // ***** [insert]
//                insert(reqDsCpoTMsg);
//                if (!RTNCD_NORMAL.equals(reqDsCpoTMsg.getReturnCode())) {
//                    throw new S21AbendException(NWZC150001CpouConstant.NWZM1023E, new String[] {reqDsCpoTMsg.getTableName(), reqDsCpoTMsg.cpoOrdNum.getValue() });
//                }
                // 2017/03/29 S21_NA#Review structure Lv.1 Del End

            } else {

                reqCpoTMsg = dbCpoTMsg;
                // 2019/11/12 QC#54413-1 Add Start
                beforeOrdHdrStsCd = dbCpoTMsg.ordHdrStsCd.getValue();
                // 2019/11/12 QC#54413-1 Add End
                // Del Start 2017/12/11 QC#20164
                // Add Start 2017/10/13 QC#20246(L3 Sol#454)
                // String oldSellToFirstRefCmntTxt = null;
                // if (null != dbCpoTMsg) {
                //     oldSellToFirstRefCmntTxt = dbCpoTMsg.sellToFirstRefCmntTxt.getValue();
                // }
                // Add End 2017/10/13 QC#20246(L3 Sol#454)
                // Del End 2017/12/11 QC#20164
                // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                DS_CPOTMsg reqDsCpoTMsg = dbDsCpoTMsg;
                // 2017/03/29 S21_NA#Review structure Lv.1 Del End

                // --------------------------------------------------
                // MODIFY
                // --------------------------------------------------
                if (NWZC150001CpouConstant.CPO_MODIFY.equals(rqstTpCd) && existsAvalCpoDtl(cpouBean)) {
                    // Mod Start 2017/12/11 QC#20164
                    // Mod Start 2017/10/13 QC#20246(L3 Sol#454)
//                    setCpoTMsgForModify(cpouBean, reqCpoTMsg);
                    // setCpoTMsgForModify(cpouBean, reqCpoTMsg, oldSellToFirstRefCmntTxt);
                    // Mod End 2017/10/13 QC#20246(L3 Sol#454)
                    setCpoTMsgForModify(cpouBean, reqCpoTMsg);
                    // Mod End 2017/12/11 QC#20164
                    // 2017/03/29 S21_NA#Review structure Lv.1 Mod Start
//                    setDsCpoTMsg(rqstTpCd, cpoBean, reqDsCpoTMsg); // 2015/08/27 CSA Mod
                    // 2018/08/20 S21_NA#27823 Mod Start
                    // setWdsColumnsOfCpo(rqstTpCd, cpouBean, reqCpoTMsg);
                    // 2019/11/12 QC#54413-1 Mod Start
                    // setWdsColumnsOfCpo(rqstTpCd, cpouBean, reqCpoTMsg, dbCpoTMsg);
                    setWdsColumnsOfCpo(rqstTpCd, cpouBean, reqCpoTMsg, dbCpoTMsg, beforeOrdHdrStsCd);
                    // 2019/11/12 QC#54413-1 Mod End
                    // 2018/08/20 S21_NA#27823 Mod End
                    // 2017/03/29 S21_NA#Review structure Lv.1 Mod End

                    // --------------------------------------------------
                    // CANCEL
                    // --------------------------------------------------
                } else {
                    setCpoTMsgForCancel(cpouBean, reqCpoTMsg);
                }

                // ***** [update]
                update(reqCpoTMsg);
                if (!RTNCD_NORMAL.equals(reqCpoTMsg.getReturnCode())) {
                    throw new S21AbendException("updateCPO : Update Error. CPO_ORD_NUM=[" + reqCpoTMsg.cpoOrdNum.getValue() + "]");
                }

                // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//                if (NWZC150001CpouConstant.CPO_MODIFY.equals(rqstTpCd)) {
//                    // ***** [update]
//                    update(reqDsCpoTMsg);
//                    if (!RTNCD_NORMAL.equals(reqDsCpoTMsg.getReturnCode())) {
//                        throw new S21AbendException(NWZC150001CpouConstant.NWZM1024E, new String[] {reqDsCpoTMsg.getTableName(), reqDsCpoTMsg.cpoOrdNum.getValue() });
//                    }
//                }
                // 2017/03/29 S21_NA#Review structure Lv.1 Del End
            }
            // 1.2WDS modify end <--
        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(CLASS_NM, methodNm);
        }
    }

    /**
     * CPO update value setting (RequestType Save)
     * 
     * <pre>
     * The value registered in CPO is set.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param cpoTMsg CPOTMsg
     * @param diChkHldFlg boolean
     * @return CPOTMsg
     */
    private CPOTMsg setCpoTMsgForSave(NWZC150001CpouBean cpoBean, CPOTMsg cpoTMsg, boolean diChkHldFlg) {

        // Mod Start 2017/12/11 QC#20164
        // Mod Start 2017/10/13 QC#20246(L3 Sol#454)
//        beanToTMsg(cpoBean, cpoTMsg, isExistsCpo);
        // beanToTMsg(cpoBean, cpoTMsg, isExistsCpo, oldSellToFirstRefCmntTxt);
        // Mod End 2017/10/13 QC#20246(L3 Sol#454)
        beanToTMsg(cpoBean, cpoTMsg);
        // Mod End 2017/12/11 QC#20164

        final String cpoOrdNum;
        if (hasValue(cpoBean.getReNumCpoOrdNum())) {
            cpoOrdNum = cpoBean.getReNumCpoOrdNum();
        } else {
            cpoOrdNum = cpoBean.getCpoOrdNum();
        }
        setValue(cpoTMsg.cpoOrdNum, cpoOrdNum);

        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        final String cpoOrdTpCd = cpoBean.getCpoOrdTpCd();
//        if (!(CPO_ORD_TP.TRIAL.equals(cpoOrdTpCd) || CPO_ORD_TP.LOAN.equals(cpoOrdTpCd))) {
//            cpoTMsg.trialLoanDurnFromDt.clear();
//            cpoTMsg.trialLoanDurnThruDt.clear();
//        }
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End

        setValue(cpoTMsg.ordHdrStsCd, ORD_LINE_STS.SAVED);
        // 2017/06/12 QC#18820 Mod Start
        if (diChkHldFlg) {
            setValue(cpoTMsg.ordHdrDplyStsCd, ORD_HDR_DPLY_STS.DI_CHECK_HOLD);
            setValue(cpoTMsg.diChkHldFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(cpoTMsg.ordHdrDplyStsCd, ORD_HDR_DPLY_STS.ENTERED);
        }
        // 2017/06/12 QC#18820 Mod End
        setValue(cpoTMsg.cpoHldFlg, ZYPConstant.FLG_OFF_N);
        setValue(cpoTMsg.cpoCancFlg, ZYPConstant.FLG_OFF_N);
        setValue(cpoTMsg.sendInvFlg, ZYPConstant.FLG_OFF_N);
        setValue(cpoTMsg.submtFlg, ZYPConstant.FLG_OFF_N);
        setValue(cpoTMsg.openFlg, ZYPConstant.FLG_ON_Y);

        setDefaultValues(cpoTMsg);

        return cpoTMsg;
    }

    /**
     * CPO update value setting (RequestType Submit)
     * 
     * <pre>
     * The value registered in CPO is set.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param cpoTMsg CPOTMsg
     * @return CPOTMsg
     */
    private CPOTMsg setCpoTMsgForSubmit(NWZC150001CpouBean cpoBean, CPOTMsg cpoTMsg) {

        // Mod Start 2017/12/11 QC#20164
        // Mod Start 2017/10/13 QC#20246(L3 Sol#454)
//        beanToTMsg(cpoBean, cpoTMsg);
        // beanToTMsg(cpoBean, cpoTMsg, isExistsCpo, oldSellToFirstRefCmntTxt);
        // Mod End 2017/10/13 QC#20246(L3 Sol#454)
        beanToTMsg(cpoBean, cpoTMsg);
        // Mod End 2017/12/11 QC#20164

        if (hasValue(cpoBean.getReNumCpoOrdNum())) {
            setValue(cpoTMsg.cpoOrdNum, cpoBean.getReNumCpoOrdNum());
        } else {
            setValue(cpoTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
        }

        cpoTMsg.cpoOrdTs.setValue(cpoBean.getCpoOrdTs());

        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        if (!(CPO_ORD_TP.TRIAL.equals(cpoBean.getCpoOrdTpCd()) || CPO_ORD_TP.LOAN.equals(cpoBean.getCpoOrdTpCd()))) {
//            cpoTMsg.trialLoanDurnFromDt.clear();
//            cpoTMsg.trialLoanDurnThruDt.clear();
//        }
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End

        setValue(cpoTMsg.ordHdrStsCd, ORD_LINE_STS.VALIDATED);
        setValue(cpoTMsg.cpoHldFlg, ZYPConstant.FLG_OFF_N);
        setValue(cpoTMsg.cpoCancFlg, ZYPConstant.FLG_OFF_N);
        setValue(cpoTMsg.sendInvFlg, ZYPConstant.FLG_OFF_N);
        setValue(cpoTMsg.submtFlg, ZYPConstant.FLG_ON_Y);
        setValue(cpoTMsg.openFlg, ZYPConstant.FLG_ON_Y);

        setDefaultValues(cpoTMsg);

        return cpoTMsg;
    }

    /**
     * DS_CPO update value setting (RequestType Save, Submit or
     * Modify)
     * 
     * <pre>
     * The value registered in DS_CPO is set.
     * </pre>
     * @param rqstTp request type
     * @param cpoBean NWZC150001CpouBean
     * @param cpoTMsg DS_CPOTMsg
     * @param dsCpoTMsg DS_CPOTMsg
     * @param beforeOrdHdrStsCd String
     */
    // private void setDsCpoTMsg(String rqstTp, NWZC150001CpouBean cpoBean, DS_CPOTMsg dsCpoTMsg) {
    // 2018/08/20 S21_NA#27823 Mod Start 
    // private void setWdsColumnsOfCpo(String rqstTp, NWZC150001CpouBean cpoBean, CPOTMsg cpoTMsg) {
    // 2019/11/12 QC#54413-1 Mod Start
    //private void setWdsColumnsOfCpo(String rqstTp, NWZC150001CpouBean cpoBean, CPOTMsg cpoTMsg, CPOTMsg dbCpoTMsg) {
    private void setWdsColumnsOfCpo(String rqstTp, NWZC150001CpouBean cpoBean, CPOTMsg cpoTMsg, CPOTMsg dbCpoTMsg, String beforeOrdHdrStsCd) {
    // 2019/11/12 QC#54413-1 Mod End
    // 2018/08/20 S21_NA#27823 Mod End
        // 1.2WDS add start -->
        setValue(cpoTMsg.cpoOrdNum, NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
        setValue(cpoTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        setValue(cpoTMsg.dsPmtMethCd, cpoBean.getDsPmtMethCd());

        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        // 20130206 Defect#156 M.Fuji Start
//        if (DS_PMT_METH.CREDIT_CARD.equals(cpoBean.getDsPmtMethCd())) {
//
//            if (!CR_CARD_AUTH_STS.COMPLETED_PRE_AUTH.equals(cpoTMsg.crCardAuthStsCd.getValue())) {
//                setValue(cpoTMsg.crCardCustRefNum, cpoBean.getCustRefNum());
//                setValue(cpoTMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.WAITING_FOR_PRE_AUTH);
//                setValue(cpoTMsg.crCardTpCd, cpoBean.getCrCardTpCd());
//            }
//
//        } else if (DS_PMT_METH.INVOICE.equals(cpoBean.getDsPmtMethCd())) {
//            cpoTMsg.crCardCustRefNum.clear();
//            setValue(cpoTMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.NO_NEED_PRE_AUTH);
//            cpoTMsg.crCardTpCd.clear();
//            cpoTMsg.crCardAuthDt.clear();
//            cpoTMsg.crCardAuthRefNum.clear();
//            cpoTMsg.crCardAuthAmt.clear();
//            cpoTMsg.crCardAuthTaxAmt.clear();
//        }
//        // 20130206 Defect#156 M.Fuji End
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End

        // START MODIFY S.Yamamoto [OM004]
        setValue(cpoTMsg.dsOrdTpCd, cpoBean.getDSOrdTpCd());
        setValue(cpoTMsg.dsOrdRsnCd, cpoBean.getDSOrdRsnCd());
        // END   MODIFY S.Yamamoto [OM004]

        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        // 20130226 Defect#621 S.Iidaka Start
//        setValue(cpoTMsg.contrCmplCd, cpoBean.getContrCmplCd());
//        // 20130226 Defect#621 S.Iidaka End
//        setValue(cpoTMsg.slcnfTrxNum, cpoBean.getslcnfTrxNum());
//
//        // 20130129 Defect#2 M.Fuji Start
//        setValue(cpoTMsg.fastTrkId, cpoBean.getFastTrkId());
//        setValue(cpoTMsg.fastTrkNum, cpoBean.getFastTrkNum());
//        // 20130129 Defect#2 M.Fuji End
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoTMsg.ordSgnDt, cpoBean.getOrdSgnDt());
        setValue(cpoTMsg.orgRqstDelyDt, cpoBean.getOrgRqstDelyDt());

        setValue(cpoTMsg.invRcpntCustCd, cpoBean.getInvRcpntCustCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        setValue(cpoTMsg.xrefRmaNum, cpoBean.getXrefRmaNum());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        // 20130124 M.Fuji Defect#111 Start
        //setValue(cpoTMsg.pickUpMachTpCd, cpoBean.getPickUpMachTpCd());
        // 20130124 M.Fuji Defect#111 End

        // 20121129 M.Fuji WDS Solution#104,105 Pricing Start
        setValue(cpoTMsg.prcBaseDt, cpoBean.getPrcBaseDt());
        setValue(cpoTMsg.prcCalcDt, cpoBean.getPrcCalcDt());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        setValue(cpoTMsg.prcMgtGrpCd, cpoBean.getPrcMgtGrpCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        // 20121129 M.Fuji WDS Solution#104,105 Pricing End

        // 2015/08/27 CSA Add Start
        setValue(cpoTMsg.dsOrdCatgCd, cpoBean.getDsOrdCatgCd());
        setValue(cpoTMsg.billToCustAcctCd, cpoBean.getBillToCustAcctCd());
        setValue(cpoTMsg.shipToCustAcctCd, cpoBean.getShipToCustAcctCd());
        setValue(cpoTMsg.soldToCustLocCd, cpoBean.getSoldToCustLocCd());
        setValue(cpoTMsg.negoDealAmt, cpoBean.getNegoDealAmt());
        setValue(cpoTMsg.slsRepTocCd, cpoBean.getSlsRepTocCd());
        setValue(cpoTMsg.prcCatgCd, cpoBean.getPrcCatgCd());
        setValue(cpoTMsg.flPrcListCd, cpoBean.getFlPrcListCd());
        setValue(cpoTMsg.aquNum, cpoBean.getAquNum());
        setValue(cpoTMsg.ordSrcImptDt, cpoBean.getOrdSrcImptDt());
        setValue(cpoTMsg.ordSrcRefNum, cpoBean.getOrdSrcRefNum());
        setValue(cpoTMsg.prcContrNum, cpoBean.getPrcContrNum()); // S21_NA#2386
        setValue(cpoTMsg.loanPerDaysAot, cpoBean.getLoanPerDaysAot());
        setValue(cpoTMsg.csmpContrNum, cpoBean.getCsmpContrNum());
        //        setValue(cpoTMsg.custAgmtNum, cpoBean.getCustAgmtNum());
        setValue(cpoTMsg.leaseCmpyPoNum, cpoBean.getLeaseCmpyPoNum());
        setValue(cpoTMsg.leasePrchOptCd, cpoBean.getLeasePrchOptCd());
        setValue(cpoTMsg.leaseTermCd, cpoBean.getLeaseTermCd());
        setValue(cpoTMsg.leaseTermMthAot, cpoBean.getLeaseTermMthAot()); //S21_NA#4564
        setValue(cpoTMsg.leasePmtFreqCd, cpoBean.getLeasePmtFreqCd());
        setValue(cpoTMsg.leaseTotPmtAmt, cpoBean.getLeaseTotPmtAmt()); //S21_NA#4564
        // 2017/06/12 QC#18820 Del Start
        //if (NWZC150001CpouConstant.CPO_SAVE.equals(rqstTp)) {
        //    setValue(cpoTMsg.diChkHldFlg, cpoBean.getDiChkHldFlg());
        //}
        // 2017/06/12 QC#18820 Del End
        setValue(cpoTMsg.ordLogTpCd, cpoBean.getOrdLogTpCd());
        if (NWZC150001CpouConstant.CPO_SAVE.equals(rqstTp) || NWZC150001CpouConstant.CPO_SUBMIT.equals(rqstTp)) {
            setValue(cpoTMsg.wfRejFlg, ZYPConstant.FLG_OFF_N);
            // START 2023/10/13 [QC#61077, MOD]
//            cpoTMsg.ordBookTs.clear();
//            setValue(cpoTMsg.ordBookFlg, ZYPConstant.FLG_OFF_N);
            if (dbCpoTMsg != null && ZYPConstant.FLG_ON_Y.equals(dbCpoTMsg.ordBookFlg.getValue())) {
                setValue(cpoTMsg.ordBookFlg, ZYPConstant.FLG_ON_Y);
                setValue(cpoTMsg.ordBookTs, dbCpoTMsg.ordBookTs.getValue());
            } else {
                setValue(cpoTMsg.ordBookFlg, ZYPConstant.FLG_OFF_N);
                cpoTMsg.ordBookTs.clear();
            }
            // END   2023/10/13 [QC#61077, MOD]
            //setValue(cpoTMsg.cpoUpdVrsnNum, BigDecimal.ONE);
        } else {
            //setValue(cpoTMsg.cpoUpdVrsnNum, cpoTMsg.cpoUpdVrsnNum.getValue().add(BigDecimal.ONE));
        }
        // setValue(cpoTMsg.dlrRefNum, cpoBean.getOrdLogTpCd());2015/12/02 S21_NA#1292 Update
        setValue(cpoTMsg.dlrRefNum, cpoBean.getDlrRefNum());// 2015/12/02 S21_NA#1292 Update
        setValue(cpoTMsg.frtCondCd, cpoBean.getFrtCondCd());
        setValue(cpoTMsg.carrSvcLvlCd, cpoBean.getCarrSvcLvlCd());
        setValue(cpoTMsg.spclHdlgTpCd, cpoBean.getSpclHdlgTpCd());
        setValue(cpoTMsg.prePmtChkNum, cpoBean.getPrePmtChkNum());
        setValue(cpoTMsg.prePmtAmt, cpoBean.getPrePmtAmt());
        setValue(cpoTMsg.prePmtTpCd, cpoBean.getPrePmtTpCd());
        setValue(cpoTMsg.crRebilRsnCatgCd, cpoBean.getCrRebilRsnCatgCd());
        setValue(cpoTMsg.dsCrCardPk, cpoBean.getDsCrCardPk());
        // 2015/08/27 CSA Add End

        // 2017/03/29 S21_NA#Review structure Lv.1 Mod Start
//        setDefaultValues(cpoTMsg);
        setDefaultValuesForWdsColumnsOfCpo(cpoTMsg);
        // 2017/03/29 S21_NA#Review structure Lv.1 Mod End
        // 1.2WDS add end <--

        setValue(cpoTMsg.carrAcctNum, cpoBean.getCarrAcctNum()); // 2015/12/02 S21_NA#1304 add

        // S21_NA#1952 start
        BigDecimal cpoUpdVrsnNum = cpoBean.getCpoUpdVrsnNum();
        if (!ZYPCommonFunc.hasValue(cpoUpdVrsnNum)) {
            setValue(cpoTMsg.cpoUpdVrsnNum, BigDecimal.ONE);
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(cpoBean.getXxValUpdFlg())) {
                setValue(cpoTMsg.cpoUpdVrsnNum, cpoUpdVrsnNum.add(BigDecimal.ONE));
            } else {
                setValue(cpoTMsg.cpoUpdVrsnNum, cpoUpdVrsnNum);
            }
        }
        // S21_NA#1952 end

        setValue(cpoTMsg.dclnSvcCd, cpoBean.getDclnSvcCd()); // 2016/08/09 S21_NA#8388 Add

        setValue(cpoTMsg.dsCpoPrcInd, cpoBean.getDsCpoPrcInd()); // 2016/09/05 S21_NA#6523 Add
        // 2016/09/05 S21_NA#6100 Add Start
        setValue(cpoTMsg.dsCpoCratUsrId, cpoBean.getDsCpoCratUsrId());
        setValue(cpoTMsg.dsCpoCratTs, cpoBean.getDsCpoCratTs());
        setValue(cpoTMsg.dsCpoUpdUsrId, cpoBean.getDsCpoUpdUsrId());
        setValue(cpoTMsg.dsCpoUpdTs, cpoBean.getDsCpoUpdTs());
        // 2018/08/20 S21_NA#27823 Mod Start
        // setValue(cpoTMsg.ordBookReqUsrId, cpoBean.getOrdBookReqUsrId());
        // setValue(cpoTMsg.ordBookReqTs, cpoBean.getOrdBookReqTs());
        if (dbCpoTMsg != null && ZYPCommonFunc.hasValue(dbCpoTMsg.ordBookReqTs) && ZYPCommonFunc.hasValue(dbCpoTMsg.ordBookReqUsrId)) {
            // 2019/11/12 QC#54413-1 Mod Start
//            setValue(cpoTMsg.ordBookReqUsrId, dbCpoTMsg.ordBookReqUsrId);
//            setValue(cpoTMsg.ordBookReqTs, dbCpoTMsg.ordBookReqTs);
            if (S21StringUtil.isEquals(NWZC150001CpouConstant.CPO_SUBMIT, rqstTp) && S21StringUtil.isEquals(ORD_HDR_STS.SAVED, beforeOrdHdrStsCd)) {
                setValue(cpoTMsg.ordBookReqUsrId, cpoBean.getOrdBookReqUsrId());
                setValue(cpoTMsg.ordBookReqTs, cpoBean.getOrdBookReqTs());
            } else {
                setValue(cpoTMsg.ordBookReqUsrId, dbCpoTMsg.ordBookReqUsrId);
                setValue(cpoTMsg.ordBookReqTs, dbCpoTMsg.ordBookReqTs);
            }
            // 2019/11/12 QC#54413-1 Mod End
        } else {
            setValue(cpoTMsg.ordBookReqUsrId, cpoBean.getOrdBookReqUsrId());
            setValue(cpoTMsg.ordBookReqTs, cpoBean.getOrdBookReqTs());
        }
        // 2018/08/20 S21_NA#27823 Mod End
        // 2016/09/05 S21_NA#6100 Add End
        // Mod Start 2018/04/17 QC#22187
        if (S21StringUtil.isEquals(CPO_SRC_TP.ORDER_UPLOAD, cpoBean.getCpoSrcTpCd())) {
            CPOTMsg tMsg = new CPOTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
            tMsg = (CPOTMsg) S21ApiTBLAccessor.findByKey(tMsg);

            if (null != tMsg) {
                setValue(cpoTMsg.ordSrcImptTs, tMsg.ordSrcImptTs);
            }
        }
        if (!ZYPCommonFunc.hasValue(cpoTMsg.ordSrcImptTs)) {
            setValue(cpoTMsg.ordSrcImptTs, cpoBean.getOrdSrcImptTs()); // 2016/10/12 S21_NA#11964 Add
        }
        // Mod End 2018/04/17 QC#22187
        // 2017/02/15 S21_NA#16184 Add Start
        if (ZYPCommonFunc.hasValue(cpoBean.getDsImptOrdPk())) {
            setValue(cpoTMsg.dsImptOrdPk, cpoBean.getDsImptOrdPk());
        }
        // 2017/02/15 S21_NA#16184 Add End
    }

    /**
     * postCPO
     * 
     * <pre>
     * NWZC150001CpouBean => CPOTMsg The registration data is posted.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param cpoTMsg CPOTMsg
     */
    private void beanToTMsg(NWZC150001CpouBean cpoBean, CPOTMsg cpoTMsg) {

        final String cpoOrdNum;
        if (!hasValue(cpoBean.getReNumCpoOrdNum())) {
            cpoOrdNum = cpoBean.getCpoOrdNum();
        } else {
            cpoOrdNum = cpoBean.getReNumCpoOrdNum();
        }
        setValue(cpoTMsg.cpoOrdNum, cpoOrdNum);

        setValue(cpoTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        setValue(cpoTMsg.cpoOrdTpCd, cpoBean.getCpoOrdTpCd());
        setValue(cpoTMsg.cpoOrdTs, cpoBean.getCpoOrdTs());
        setValue(cpoTMsg.ordHdrStsCd, cpoBean.getOrdHdrStsCd());
        setValue(cpoTMsg.custIssPoNum, cpoBean.getCustIssPoNum());
        setValue(cpoTMsg.custIssPoDt, cpoBean.getCustIssPoDt());
        setValue(cpoTMsg.ordFuflLvlCd, cpoBean.getOrdFuflLvlCd());
        setValue(cpoTMsg.adminPsnCd, cpoBean.getAdminPsnCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        setValue(cpoTMsg.endUsrNm, cpoBean.getEndUsrNm());
//        setValue(cpoTMsg.advRcptNum, cpoBean.getAdvRcptNum());
//        setValue(cpoTMsg.trialLoanDurnFromDt, cpoBean.getTrialLoanDurnFromDt());
//        setValue(cpoTMsg.trialLoanDurnThruDt, cpoBean.getTrialLoanDurnThruDt());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoTMsg.entCpoTotDealSlsAmt, cpoBean.getEntCpoTotDealSlsAmt());
        setValue(cpoTMsg.entCpoTotDealDiscAmt, cpoBean.getEntCpoTotDealDiscAmt());
        setValue(cpoTMsg.entCpoTotDealNetAmt, cpoBean.getEntCpoTotDealNetAmt());
        setValue(cpoTMsg.entCpoTotFuncSlsAmt, cpoBean.getEntCpoTotFuncSlsAmt());
        setValue(cpoTMsg.entCpoTotFuncDiscAmt, cpoBean.getEntCpoTotFuncDiscAmt());
        setValue(cpoTMsg.entCpoTotFuncNetAmt, cpoBean.getEntCpoTotFuncNetAmt());
        setValue(cpoTMsg.cpoTotDealSlsAmt, cpoBean.getCpoTotDealSlsAmt());
        setValue(cpoTMsg.cpoTotDealDiscAmt, cpoBean.getCpoTotDealDiscAmt());
        setValue(cpoTMsg.cpoTotDealNetAmt, cpoBean.getCpoTotDealNetAmt());
        setValue(cpoTMsg.cpoTotFuncSlsAmt, cpoBean.getCpoTotFuncSlsAmt());
        setValue(cpoTMsg.cpoTotFuncDiscAmt, cpoBean.getCpoTotFuncDiscAmt());
        setValue(cpoTMsg.cpoTotFuncNetAmt, cpoBean.getCpoTotFuncNetAmt());
        setValue(cpoTMsg.addRddDt, cpoBean.getAddRddDt());
        setValue(cpoTMsg.addRsdDt, cpoBean.getAddRsdDt());
        setValue(cpoTMsg.addShpgSvcLvlCd, cpoBean.getAddShpgSvcLvlCd());
        setValue(cpoTMsg.addFrtChrgToCd, cpoBean.getAddFrtChrgToCd());
        setValue(cpoTMsg.addFrtChrgMethCd, cpoBean.getAddFrtChrgMethCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        setValue(cpoTMsg.cancDelyLimitDt, cpoBean.getCancDelyLimitDt());
//        setValue(cpoTMsg.cancShipLimitDt, cpoBean.getCancShipLimitDt());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoTMsg.addDropShipFlg, cpoBean.getAddDropShipFlg());
        setValue(cpoTMsg.addShipToLocNm, cpoBean.getAddShipToLocNm());
        setValue(cpoTMsg.addShipToAddlLocNm, cpoBean.getAddShipToAddlLocNm());
        setValue(cpoTMsg.addShipToAddlLocNm, cpoBean.getAddShipToAddlLocNm());
        setValue(cpoTMsg.addShipToFirstLineAddr, cpoBean.getAddShipToFirstLineAddr());
        setValue(cpoTMsg.addShipToScdLineAddr, cpoBean.getAddShipToScdLineAddr());
        setValue(cpoTMsg.addShipToThirdLineAddr, cpoBean.getAddShipToThirdLineAddr());
        setValue(cpoTMsg.addShipToFrthLineAddr, cpoBean.getAddShipToFrthLineAddr());
        setValue(cpoTMsg.addShipToCtyAddr, cpoBean.getAddShipToCtyAddr());
        setValue(cpoTMsg.addShipToStCd, cpoBean.getAddShipToStCd());
        setValue(cpoTMsg.addShipToProvNm, cpoBean.getAddShipToProvNm());
        setValue(cpoTMsg.addShipToCtryCd, cpoBean.getAddShipToCtryCd());
        setValue(cpoTMsg.addShipToPostCd, cpoBean.getAddShipToPostCd());
        setValue(cpoTMsg.addShipToCntyNm, cpoBean.getAddShipToCntyNm());
        setValue(cpoTMsg.addShipTo01RefCmntTxt, cpoBean.getAddShipTo01RefCmntTxt());
        setValue(cpoTMsg.addShipTo02RefCmntTxt, cpoBean.getAddShipTo02RefCmntTxt());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        setValue(cpoTMsg.dslpInfoFlg, cpoBean.getDslpInfoFlg());
//        setValue(cpoTMsg.addPmtMethCd, cpoBean.getAddPmtMethCd());
//        setValue(cpoTMsg.addPmtCondCd, cpoBean.getAddPmtCondCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoTMsg.addPmtTermCashDiscCd, cpoBean.getAddPmtTermCashDiscCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        setValue(cpoTMsg.addPmtTermCd, cpoBean.getAddPmtTermCd());
//        setValue(cpoTMsg.addCashDiscTermCd, cpoBean.getAddCashDiscTermCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoTMsg.addOrigCpoOrdNum, cpoBean.getAddOrigCpoOrdNum());
        setValue(cpoTMsg.addOrigInvNum, cpoBean.getAddOrigInvNum());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        setValue(cpoTMsg.crDrRsnCd, cpoBean.getCrDrRsnCd());
//        setValue(cpoTMsg.crDrSubRsnCd, cpoBean.getCrDrRsnSubCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoTMsg.cpoCancFlg, cpoBean.getCpoCancFlg());
        setValue(cpoTMsg.cpoCancDt, cpoBean.getCpoCancDt());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        setValue(cpoTMsg.trialLoanRqstNum, cpoBean.getTrialLoanRqstNum());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoTMsg.origOrdNum, cpoBean.getOrigOrdNum());
        setValue(cpoTMsg.sendInvFlg, cpoBean.getSendInvFlg());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        setValue(cpoTMsg.attFileNm, cpoBean.getAttFileNm());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoTMsg.reBillPairCpoOrdNum, cpoBean.getReBillPairCpoOrdNum());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        setValue(cpoTMsg.addDslpPmtMethCd, cpoBean.getAddDslpPmtMethCd());
//        setValue(cpoTMsg.addDslpPmtCondCd, cpoBean.getAddDslpPmtCondCd());
//        setValue(cpoTMsg.addDslpPmtTermDiscCd, cpoBean.getAddDslpPmtTermDiscCd());
//        setValue(cpoTMsg.addDslpPmtTermCd, cpoBean.getAddDslpPmtTermCd());
//        setValue(cpoTMsg.addDslpCashDiscTermCd, cpoBean.getAddDslpCashDiscTermCd());
//        setValue(cpoTMsg.addDslpShpgMethCd, cpoBean.getAddDslpShpgMethCd());
//        setValue(cpoTMsg.addDslpShpgCondCd, cpoBean.getAddDslpShpgCondCd());
//        setValue(cpoTMsg.addDslpShpgTermCd, cpoBean.getAddDslpShpgTermCd());
//        setValue(cpoTMsg.addDslpFrtChrgToCd, cpoBean.getAddDslpFrtChrgToCd());
//        setValue(cpoTMsg.addDslpFrtChrgMethCd, cpoBean.getAddDslpFrtChrgMethCd());
//        setValue(cpoTMsg.addDslpSellToCustCd, cpoBean.getAddDslpSellToCustCd());
//        setValue(cpoTMsg.addShipMethCd, cpoBean.getAddShipMethCd());
//        setValue(cpoTMsg.addShipTermCd, cpoBean.getAddShipTermCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoTMsg.submtFlg, cpoBean.getSubmtFlg());
        setValue(cpoTMsg.openFlg, cpoBean.getOpenFlg());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        setValue(cpoTMsg.endUsrDropFlg, cpoBean.getEndUsrDropFlg());
//        setValue(cpoTMsg.custStoreNum, cpoBean.getCustStoreNum());
//        setValue(cpoTMsg.custDeptNum, cpoBean.getCustDeptNum());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        // Mod Start 2018/04/17 QC#22187
        if (S21StringUtil.isEquals(CPO_SRC_TP.ORDER_UPLOAD, cpoBean.getCpoSrcTpCd())) {
            CPOTMsg tMsg = new CPOTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
            tMsg = (CPOTMsg) S21ApiTBLAccessor.findByKey(tMsg);

            if (null != tMsg) {
                setValue(cpoTMsg.cpoSrcTpCd, tMsg.cpoSrcTpCd);
            }
        }
        if (!ZYPCommonFunc.hasValue(cpoTMsg.cpoSrcTpCd)) {
            setValue(cpoTMsg.cpoSrcTpCd, cpoBean.getCpoSrcTpCd());
        }
        // Mod End 2018/04/17 QC#22187
        setValue(cpoTMsg.payerCustCd, cpoBean.getPayerCustCd());
        setValue(cpoTMsg.billToCustCd, cpoBean.getBillToCustCd());
        setValue(cpoTMsg.addShipToCustCd, cpoBean.getAddShipToCustCd());
        setValue(cpoTMsg.sellToCustCd, cpoBean.getSellToCustCd());
        // Mod Start 2017/12/11 QC#20164
        // Mod Start 2017/10/13 QC#20246(L3 Sol#454)
        // if (!isExistsCpo) {
        //    setValue(cpoTMsg.sellToFirstRefCmntTxt, cpoBean.getSellToFirstRefCmntTxt());
        // } else {
        //     setValue(cpoTMsg.sellToFirstRefCmntTxt, oldSellToFirstRefCmntTxt);
        // }
        // Mod End 2017/10/13 QC#20246(L3 Sol#454)
        setValue(cpoTMsg.sellToFirstRefCmntTxt, cpoBean.getSellToFirstRefCmntTxt());
        // Mod End 2017/12/11 QC#20164
        setValue(cpoTMsg.sellToScdRefCmntTxt, cpoBean.getSellToScdRefCmntTxt());
        setValue(cpoTMsg.sysSrcCd, cpoBean.getSysSrcCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        setValue(cpoTMsg.exptLoadPortCd, cpoBean.getExptLoadPortCd());
//        setValue(cpoTMsg.destPortNm, cpoBean.getDestPortNm());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoTMsg.coaBrCd, cpoBean.getCoaBrCd());
        setValue(cpoTMsg.coaCcCd, cpoBean.getCoaCcCd());
        setValue(cpoTMsg.coaAcctCd, cpoBean.getCoaAcctCd());
        setValue(cpoTMsg.coaProdCd, cpoBean.getCoaProdCd());
        setValue(cpoTMsg.coaChCd, cpoBean.getCoaChCd());
        setValue(cpoTMsg.coaProjCd, cpoBean.getCoaProjCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        setValue(cpoTMsg.reqOvngtLimitDt, cpoBean.getReqOvngtLimitDt());
//        setValue(cpoTMsg.origOvngtLimitDt, cpoBean.getReqOvngtLimitDt());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoTMsg.revRecogMethCd, cpoBean.getRevRecogMethCd());
        // 2018/05/11 QC#22139 Add Start
        setValue(cpoTMsg.quotePrintCmntTxt, cpoBean.getQuotePrintCmntTxt());
        setValue(cpoTMsg.ordPrintCmntTxt, cpoBean.getOrdPrintCmntTxt());
        setValue(cpoTMsg.shpgCmntPrintCd, cpoBean.getshpgCmntPrintCd());
        // 2018/05/11 QC#22139 Add End
    }

    private void setDefaultValues(CPOTMsg cpoTMsg) {

        // QTY, AMT
        final Set<EZDTBigDecimalItem> amtItemList = new HashSet<EZDTBigDecimalItem>();
        amtItemList.add(cpoTMsg.entCpoTotDealSlsAmt);
        amtItemList.add(cpoTMsg.entCpoTotDealDiscAmt);
        amtItemList.add(cpoTMsg.entCpoTotDealNetAmt);
        amtItemList.add(cpoTMsg.entCpoTotFuncSlsAmt);
        amtItemList.add(cpoTMsg.entCpoTotFuncDiscAmt);
        amtItemList.add(cpoTMsg.entCpoTotFuncNetAmt);
        amtItemList.add(cpoTMsg.cpoTotDealSlsAmt);
        amtItemList.add(cpoTMsg.cpoTotDealDiscAmt);
        amtItemList.add(cpoTMsg.cpoTotDealNetAmt);
        amtItemList.add(cpoTMsg.cpoTotFuncNetAmt);
        amtItemList.add(cpoTMsg.cpoTotFuncSlsAmt);
        amtItemList.add(cpoTMsg.cpoTotFuncDiscAmt);

        for (EZDTBigDecimalItem amtItem : amtItemList) {
            if (!hasValue(amtItem)) {
                setValue(amtItem, ZERO);
            }
        }

        // FLG
        final Set<EZDTStringItem> flgItemList = new HashSet<EZDTStringItem>();
        flgItemList.add(cpoTMsg.addDropShipFlg);
        flgItemList.add(cpoTMsg.dslpInfoFlg);
        flgItemList.add(cpoTMsg.cpoHldFlg);
        flgItemList.add(cpoTMsg.cpoCancFlg);
        flgItemList.add(cpoTMsg.sendInvFlg);
        flgItemList.add(cpoTMsg.submtFlg);
        flgItemList.add(cpoTMsg.openFlg);
        flgItemList.add(cpoTMsg.endUsrDropFlg);
        flgItemList.add(cpoTMsg.dslpInfoFlg);

        for (EZDTStringItem flgItem : flgItemList) {
            if (!hasValue(flgItem)) {
                setValue(flgItem, ZYPConstant.FLG_OFF_N);
            }
        }
    }

    // ********** add by K.Tajima [Def# 741] - START
    private boolean existsAvalCpoDtl(final NWZC150001CpouBean cpouBean) {

        // --------------------------------------------------
        // judge [ORD_LINE_STS] of API parameters.
        // --------------------------------------------------
        for (NWZC150001CpouDetailBean dtlBean : cpouBean.getDtlBeanList()) {
            final String ordLineStsCd = dtlBean.getOrdLineStsCd();
            if (!asList(ORD_LINE_STS.CLOSED, ORD_LINE_STS.CANCELLED).contains(ordLineStsCd)) {
                // ***** exists available CpoDtl.
                return true;
            }
        }

        final Set<String> reqCpoDtlLineNumSet = new HashSet<String>();
        for (NWZC150001CpouDetailBean dtlBean : cpouBean.getDtlBeanList()) {
            reqCpoDtlLineNumSet.add(dtlBean.getCpoDtlLineNum() + dtlBean.getCpoDtlLineSubNum());
        }

        // --------------------------------------------------
        // judge [ORD_LINE_STS] of DB.
        // --------------------------------------------------
        final CPO_DTLTMsg reqCpoDtlTMsg = new CPO_DTLTMsg();
        reqCpoDtlTMsg.setSQLID("001");
        reqCpoDtlTMsg.setConditionValue("glblCmpyCd01", cpouBean.getGlblCmpyCd());
        reqCpoDtlTMsg.setConditionValue("cpoOrdNum01", cpouBean.getCpoOrdNum());
        final CPO_DTLTMsgArray resCpoDtlTMsgArray = (CPO_DTLTMsgArray) findByCondition(reqCpoDtlTMsg);

        for (int i = 0; i < resCpoDtlTMsgArray.getValidCount(); i++) {

            final CPO_DTLTMsg resCpoDtlTMsg = resCpoDtlTMsgArray.no(i);

            final String cpoDtlLineNum = resCpoDtlTMsg.cpoDtlLineNum.getValue();
            final String cpoDtlLineSubNum = resCpoDtlTMsg.cpoDtlLineSubNum.getValue();

            if (reqCpoDtlLineNumSet.contains(cpoDtlLineNum + cpoDtlLineSubNum)) {
                // skip.
                continue;
            }

            final String ordLineStsCd = resCpoDtlTMsg.ordLineStsCd.getValue();
            if (!asList(ORD_LINE_STS.CLOSED, ORD_LINE_STS.CANCELLED).contains(ordLineStsCd)) {
                // ***** exists available CpoDtl.
                return true;
            }
        }

        // 2017/06/20 S21_NA#19288-2 Add Start
        if (isReturnUpdate(cpouBean)) {
            return true;
        }
        // 2017/06/20 S21_NA#19288-2 Add End
        return false;
    }
    // ********** add by K.Tajima [Def# 741] - E N D

    /**
     * CPO update value setting (RequestType Modify)
     * 
     * <pre>
     * The value in which CPO is renewed is set.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param cpoTMsg CPOTMsg
     * @param oldSellToFirstRefCmntTxt String
     * @return CPOTMsg
     */
    private void setCpoTMsgForModify(NWZC150001CpouBean cpoBean, CPOTMsg cpoTMsg) {

        final String cpoOrdTs = cpoTMsg.cpoOrdTs.getValue();
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        final String reqOvngtLimitDt = cpoTMsg.reqOvngtLimitDt.getValue();
//        final String origOvngtLimitDt = cpoTMsg.origOvngtLimitDt.getValue();
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End

        // Mod Start 2017/12/11 QC#20164
        // Mod Start 2017/10/13 QC#20247(L3 Sol#454)
//        beanToTMsg(cpoBean, cpoTMsg);
        // beanToTMsg(cpoBean, cpoTMsg, true, oldSellToFirstRefCmntTxt);
        // Mod End 2017/10/13 QC#20247(L3 Sol#454)
        beanToTMsg(cpoBean, cpoTMsg);
        // Mod End 2017/12/11 QC#20164

        setValue(cpoTMsg.cpoOrdTs, cpoOrdTs);
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        setValue(cpoTMsg.reqOvngtLimitDt, reqOvngtLimitDt);
//        setValue(cpoTMsg.origOvngtLimitDt, origOvngtLimitDt);
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End

        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        final String cpoOrdTpCd = cpoBean.getCpoOrdTpCd();
//        if (!(CPO_ORD_TP.TRIAL.equals(cpoOrdTpCd) || CPO_ORD_TP.LOAN.equals(cpoOrdTpCd))) {
//            cpoTMsg.trialLoanDurnFromDt.clear();
//            cpoTMsg.trialLoanDurnThruDt.clear();
//        }
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End

        setValue(cpoTMsg.ordHdrStsCd, ORD_LINE_STS.VALIDATED);
        setValue(cpoTMsg.cpoCancFlg, ZYPConstant.FLG_OFF_N);
        setValue(cpoTMsg.sendInvFlg, ZYPConstant.FLG_OFF_N);
        setValue(cpoTMsg.submtFlg, ZYPConstant.FLG_ON_Y);
        setValue(cpoTMsg.openFlg, ZYPConstant.FLG_ON_Y);

        setDefaultValues(cpoTMsg);
    }

    /**
     * CPO update value setting (RequestType Cancel)
     * 
     * <pre>
     * The value in which CPO is renewed is set.
     * @param cpoBean           NWZC150001CpouBean
     * @param cpoTMsg           CPOTMsg
     * @param cpoRtrnDtlUpdFlg  cpoRtrnDtlUpdFlg
     * @return CPOTMsg
     * </pre>
     */
    private CPOTMsg setCpoTMsgForCancel(NWZC150001CpouBean cpouBean, CPOTMsg cpoTMsg) {

        boolean closed = false;
        boolean cancelled = false;

        setValue(cpoTMsg.entCpoTotDealSlsAmt, cpouBean.getEntCpoTotDealSlsAmt());
        setValue(cpoTMsg.entCpoTotDealDiscAmt, cpouBean.getEntCpoTotDealDiscAmt());
        setValue(cpoTMsg.entCpoTotDealNetAmt, cpouBean.getEntCpoTotDealNetAmt());
        setValue(cpoTMsg.entCpoTotFuncSlsAmt, cpouBean.getEntCpoTotFuncSlsAmt());
        setValue(cpoTMsg.entCpoTotFuncDiscAmt, cpouBean.getEntCpoTotFuncDiscAmt());
        setValue(cpoTMsg.entCpoTotFuncNetAmt, cpouBean.getEntCpoTotFuncNetAmt());

        setValue(cpoTMsg.cpoTotDealSlsAmt, cpouBean.getCpoTotDealSlsAmt());
        setValue(cpoTMsg.cpoTotDealDiscAmt, cpouBean.getCpoTotDealDiscAmt());
        setValue(cpoTMsg.cpoTotDealNetAmt, cpouBean.getCpoTotDealNetAmt());
        setValue(cpoTMsg.cpoTotFuncSlsAmt, cpouBean.getCpoTotFuncSlsAmt());
        setValue(cpoTMsg.cpoTotFuncDiscAmt, cpouBean.getCpoTotFuncDiscAmt());
        setValue(cpoTMsg.cpoTotFuncNetAmt, cpouBean.getCpoTotFuncNetAmt());

        // 2017/06/16 S21_NA#19288 Add Start
        // 2017/06/20 S21_NA#19288-2 Mod Start
//        boolean hasOpenCpoDtl = hasOpenCpoDtl(cpouBean);
        boolean hasOpenCpoDtl = NWZC150001Query.getInstance().hasOpenCpoDtl(cpouBean);
        // 2017/06/20 S21_NA#19288-2 Mod Start
        boolean hasOpenRtnDtl = isReturnUpdate(cpouBean);
        if (!hasOpenCpoDtl && !hasOpenRtnDtl) {
            List<Map<String, Object>> closedDataMapList = hasDetectedStsDtl(cpouBean, ORD_LINE_STS.CLOSED, RTRN_LINE_STS.CLOSED);
            for (Map<String, Object> closedDataMap : closedDataMapList) {
                BigDecimal dataCnt = (BigDecimal) closedDataMap.get("CNT_DAT");
                if (dataCnt.compareTo(BigDecimal.ZERO) > 0) {
                    closed = true;
                    break;
                }
            }

            List<Map<String, Object>> cancelledDataMapList = hasDetectedStsDtl(cpouBean, ORD_LINE_STS.CANCELLED, RTRN_LINE_STS.CANCELLED);
            for (Map<String, Object> cancelledDataMap : cancelledDataMapList) {
                BigDecimal dataCnt = (BigDecimal) cancelledDataMap.get("CNT_DAT");
                if (dataCnt.compareTo(BigDecimal.ZERO) > 0) {
                    cancelled = true;
                    break;
                }
            }

            if (closed) {
                cpoTMsg.ordHdrStsCd.setValue(ORD_HDR_STS.CLOSED);
                cpoTMsg.ordHdrDplyStsCd.setValue(ORD_HDR_DPLY_STS.CLOSED);
                cpoTMsg.openFlg.setValue(ZYPConstant.FLG_OFF_N);
            } else if (cancelled) {
                cpoTMsg.ordHdrStsCd.setValue(ORD_HDR_STS.CANCELLED);
                cpoTMsg.ordHdrDplyStsCd.setValue(ORD_HDR_DPLY_STS.CANCELLED);
                cpoTMsg.cpoHldFlg.setValue(ZYPConstant.FLG_OFF_N);
                cpoTMsg.openFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
        }
        // 2017/06/16 S21_NA#19288 Add End

        // 2017/06/16 S21_NA#19288 Del Start
//        // Details passed by the parameter confirm whether Cancel was
//        // done. It is not possible occasionally to cancel when having
//        // shipped it.
//        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {
//            NWZC150001CpouDetailBean detailMsg = cpoBean.getDtlBeanList().get(i);
//            String ordLineStsCd = detailMsg.getOrdLineStsCd();
//            if (ORD_LINE_STS.CLOSED.equals(ordLineStsCd)) {
//                closed = true;
//            } else if (ORD_LINE_STS.CANCELLED.equals(ordLineStsCd)) {
//                cancelled = true;
//            } else {
//                closed = false;
//                cancelled = false;
//                break;
//            }
//        }

//        if (closed || cancelled) {

//            Set<String> orderLineSet = new HashSet<String>();
//            for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {
//                NWZC150001CpouDetailBean detailMsg = cpoBean.getDtlBeanList().get(i);
//                String orderLineNum = detailMsg.getCpoDtlLineNum();
//                String orderLineSubNum = detailMsg.getCpoDtlLineSubNum();
//                orderLineSet.add(orderLineNum + orderLineSubNum);
//            }

//            CPO_DTLTMsg reqCpoDtlTMsg = new CPO_DTLTMsg();
//            reqCpoDtlTMsg.setSQLID("001");
//            reqCpoDtlTMsg.setConditionValue("glblCmpyCd01", cpouBean.getGlblCmpyCd());
//            reqCpoDtlTMsg.setConditionValue("cpoOrdNum01", cpouBean.getCpoOrdNum());
//            CPO_DTLTMsgArray cpoDtlTMsgArray = (CPO_DTLTMsgArray) findByCondition(reqCpoDtlTMsg);

//            for (int i = 0; i < cpoDtlTMsgArray.getValidCount(); i++) {

//                String orderLineNum = cpoDtlTMsgArray.no(i).cpoDtlLineNum.getValue();
//                String orderLineSubNum = cpoDtlTMsgArray.no(i).cpoDtlLineSubNum.getValue();
//                if (orderLineSet.contains(orderLineNum + orderLineSubNum)) {
//                    continue;
//                }

//                String ordLineStsCd = cpoDtlTMsgArray.no(i).ordLineStsCd.getValue();
//                if (ORD_LINE_STS.CLOSED.equals(ordLineStsCd)) {
//                    closed = true;
//                } else if (ORD_LINE_STS.CANCELLED.equals(ordLineStsCd)) {
//                    cancelled = true;
//                } else {
//                    closed = false;
//                    cancelled = false;
//                    break;
//                }
//            }
//            if (!isReturnUpdate(cpouBean)) { // 20151019 add
//                if (closed) {
//                    cpoTMsg.ordHdrStsCd.setValue(ORD_HDR_STS.CLOSED);
//                    cpoTMsg.openFlg.setValue(ZYPConstant.FLG_OFF_N);
//                } else if (cancelled) {
//                    cpoTMsg.ordHdrStsCd.setValue(ORD_HDR_STS.CANCELLED);
//                    cpoTMsg.cpoHldFlg.setValue(ZYPConstant.FLG_OFF_N);
//                    cpoTMsg.openFlg.setValue(ZYPConstant.FLG_OFF_N);
//                }
//            } // 20151019 add
//        }
        // 2017/06/16 S21_NA#19288 Del End

        if (ORD_LINE_STS.CANCELLED.equals(cpoTMsg.ordHdrStsCd.getValue())) {
            cpoTMsg.cpoCancFlg.setValue(ZYPConstant.FLG_ON_Y);
            cpoTMsg.cpoCancDt.setValue(cpouBean.getSlsDt());
        }
        return cpoTMsg;
    }

    //private void setDefaultValues(DS_CPOTMsg dsCpoTMsg) {
    private void setDefaultValuesForWdsColumnsOfCpo(CPOTMsg cpoTMsg) {
        // 1.2WDS add start -->
        // FLG
        Set<EZDTStringItem> flgItemList = new HashSet<EZDTStringItem>();
        flgItemList.add(cpoTMsg.addlCustInstlFlg);
        // 20121122 M.Fuji WDS Solution#L-312a/L-312b OCS Interface
        flgItemList.add(cpoTMsg.sendLgcyContrFlg);
        // 20121122 M.Fuji WDS Solution#L-312a/L-312b OCS Interface

        // 20121122 M.Fuji WDS Solution#112 LeaseOrder
        flgItemList.add(cpoTMsg.leaseOrdFlg);
        // 20121122 M.Fuji WDS Solution#112 LeaseOrder

        flgItemList.add(cpoTMsg.convCompFlg);
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End

        // CSA(not firm) S.Yamamoto TODO
        flgItemList.add(cpoTMsg.diChkHldFlg);

        for (EZDTStringItem flgItem : flgItemList) {
            if (!hasValue(flgItem)) {
                setValue(flgItem, ZYPConstant.FLG_OFF_N);
            }
        }
        // 1.2WDS add end <--

        // 2015/08/27 CSA Add Start
        // S21_NA#1833 delete start
        // final Set<EZDTBigDecimalItem> amtItemList = new
        // HashSet<EZDTBigDecimalItem>();
        // amtItemList.add(cpoTMsg.negoDealAmt);
        // amtItemList.add(cpoTMsg.loanPerDaysAot);
        // for (EZDTBigDecimalItem amtItem : amtItemList) {
        // if (!hasValue(amtItem)) {
        // setValue(amtItem, ZERO);
        // }
        // }
        // S21_NA#1833 delete end
        // 2015/08/27 CSA Add End

    }

    /**
     * <pre>
     * @param cpoBean   NWZC150001CpouBean
     * @return if Return update then return true.
     * </pre>
     */
    private boolean isReturnUpdate(NWZC150001CpouBean cpouBean) {
        // 2017/06/16 S21_NA#19288 Del Start
//        if (ZYPConstant.FLG_OFF_N.equals(cpoBean.getCpoRtrnDtlUpdFlg())) {
//            return true;
//        }
        // 2017/06/16 S21_NA#19288 Del End

        // 2017/06/20 S21_NA#19288-2 Mod Start
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("glblCmpyCd", cpouBean.getGlblCmpyCd());
//        map.put("cpoOrdNum", cpouBean.getCpoOrdNum());
//        map.put("closed", RTRN_LINE_STS.CLOSED);
//        map.put("cancelled", RTRN_LINE_STS.CANCELLED);
//
//        Integer cnt = (Integer) ssmClient.queryObject("getRtrnDtlCnt", map);
//
//        return (cnt > 0);

        return NWZC150001Query.getInstance().hasOpenReturnDtl(cpouBean);
        // 2017/06/20 S21_NA#19288-2 Mod End
    }

    // 2017/06/16 S21_NA#19288 Add Start
    // 2017/06/20 S21_NA#19288-2 Del Start
//    private boolean hasOpenCpoDtl(NWZC150001CpouBean cpouBean) {
//
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("glblCmpyCd", cpouBean.getGlblCmpyCd());
//        map.put("cpoOrdNum", cpouBean.getCpoOrdNum());
//        map.put("closed", ORD_LINE_STS.CLOSED);
//        map.put("cancelled", ORD_LINE_STS.CANCELLED);
//
//        Integer cnt = (Integer) ssmClient.queryObject("hasOpenCpoDtl", map);
//
//        return (cnt > 0);
//    }
    // 2017/06/20 S21_NA#19288-2 Del End

    private List<Map<String, Object>> hasDetectedStsDtl(NWZC150001CpouBean cpouBean, String ordLineStsCd, String rtrnLineStsCd) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", cpouBean.getGlblCmpyCd());
        map.put("cpoOrdNum", cpouBean.getCpoOrdNum());
        map.put("ordLineSts", ordLineStsCd);
        map.put("rtrnLineStsCd", rtrnLineStsCd);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("hasDetectedStsDtl", map);
    }
    // 2017/06/16 S21_NA#19288 Add End
}
