/**
 * <pre>
 * Update / Inser CPO_DTL Table
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/07   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * 2017/06/13   Fujitsu         S.Takami        Update          S21_NA#18869-2
 * 2017/06/16   Fujitsu         S.Takami        Update          S21_NA#19288
 * 2017/10/16   Fujitsu         R.nakamura      Update          S21_NA#21507
 * 2017/10/20   Fujitsu         T.Aoi           Update          S21_NA#21730
 * 2018/01/11   Fujitsu         A.Kosai         Update          S21_NA#22372
 * 2018/08/02   Fujitsu         S.Takami        Update          S21_NA#25404
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 * 2019/03/20   Fujitsu         Y.Kanefusa      Update          S21_NA#30855
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 * 2019/11/15   Fujitsu         S.Takami        Update          S21_NA#54199
 * 2019/11/27   Fujitsu         K.Kato          Update          QC#52339
 * 2021/04/19   CITS            A.Marte         Update          QC#58549
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.dbAccs;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByConditionForUpdate;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTStringItem;
import business.db.CPO_DTLTMsg;
import business.db.ORD_PRC_CALC_BASETMsg;
import business.db.ORD_PRC_CALC_BASETMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.parts.NWZC150001_priceListPMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.derive.NWZC150001CpouEditAmount;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.logWriter.NWZC150001CpouLogWriter;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation.NWZC150001CpouExistsCdInDbCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

public class NWZC150001CpouUpdCpoDtl {

    // 2017/06/13 S21_NA#18869-2 Del Start
//    static final NWZC150001CpouUpdCpoDtl MY_INSTANCE = new NWZC150001CpouUpdCpoDtl();
    // 2017/06/13 S21_NA#18869-2 Del End

    public NWZC150001CpouUpdCpoDtl() {

        super();
    }

    // 2017/06/13 S21_NA#18869-2 Del Start
//    public static NWZC150001CpouUpdCpoDtl getInstance() {
//        return MY_INSTANCE;
//    }
    // 2017/06/13 S21_NA#18869-2 Del End

    /**
     * CPO_DTL update
     * 
     * <pre>
     * Save   : Data is already deleted, and it has registered newly when having registered once.
     *          But the data which cancelled are not deleted.
     * Submit : Data is already deleted, and it has registered newly when having registered once.
     *          But the data which cancelled are not deleted.
     * Modify : It updates it to the registered data.
     * Cancel : It updates it to the registered data.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     */
    public void updateCpoDtl(NWZC150001CpouBean cpoBean, //
            List<CPO_DTLTMsg> insCpoDtlTMsgAry, //
            List<CPO_DTLTMsg> updCpoDtlTMsgAry) {

        final String methodNm = "updateCpoDtl";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            final String rqstTpCd = cpoBean.getRqstTpCd();

            if ((NWZC150001CpouConstant.CPO_SAVE.equals(rqstTpCd) || NWZC150001CpouConstant.CPO_SUBMIT.equals(rqstTpCd)) && hasValue(cpoBean.getCpoOrdNum())) {
                final CPO_DTLTMsg reqCpoDtlTMsg = new CPO_DTLTMsg();

                // 2016/02/05 CSA S21_NA#3255 Remod Start
                // // 2015/08/27 CSA Mod Start
                setValue(reqCpoDtlTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
                setValue(reqCpoDtlTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
                // ***** [removeByPartialKey]
                // For Performance QC#10321 Mod Start
                // removeByPartialKey(reqCpoDtlTMsg);
                S21FastTBLAccessor.removeByPartialValue(reqCpoDtlTMsg, new String[] {"glblCmpyCd", "cpoOrdNum" });
                // For Performance QC#10321 Mod End

                // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
                // DS_CPO_DTLTMsg reqDsCpoDtlTMsg = new
                // DS_CPO_DTLTMsg();
                // setValue(reqDsCpoDtlTMsg.glblCmpyCd,
                // cpoBean.getGlblCmpyCd());
                // setValue(reqDsCpoDtlTMsg.cpoOrdNum,
                // cpoBean.getCpoOrdNum());
                // ***** [removeByPartialKey]
                // For Performance QC#10321 Mod Start
                // removeByPartialKey(reqDsCpoDtlTMsg);
                // S21FastTBLAccessor.removeByPartialValue(reqDsCpoDtlTMsg,
                // new String[] {"glblCmpyCd", "cpoOrdNum" });
                // For Performance QC#10321 Mod End
                // 2017/03/29 S21_NA#Review structure Lv.1 Del End

                // reqCpoDtlTMsg.setSQLID("001");
                // reqCpoDtlTMsg.setConditionValue("glblCmpyCd01",
                // cpoBean.getGlblCmpyCd());
                // reqCpoDtlTMsg.setConditionValue("cpoOrdNum01",
                // cpoBean.getCpoOrdNum());
                // CPO_DTLTMsgArray resCpoDtlTMsgAry //
                // = (CPO_DTLTMsgArray)
                // EZDTBLAccessor.findByCondition(reqCpoDtlTMsg);
                // for (int i = 0; i <
                // resCpoDtlTMsgAry.getValidCount(); i++) {
                // CPO_DTLTMsg tMsg = resCpoDtlTMsgAry.no(i);
                //    
                // if
                // (ORD_LINE_STS.SAVED.equals(tMsg.ordLineStsCd.getValue())
                // //
                // ||
                // ORD_LINE_STS.VALIDATED.equals(tMsg.ordLineStsCd.getValue()))
                // {
                // deleteDetailData(tMsg);
                // }
                // }
                // // 2015/08/27 CSA Mod End
                // 2016/02/05 CSA S21_NA#3255 Remod Start
            }

            // For Performance QC#10321 Add Start
//            insCpoDtlTMsgAry = new ArrayList<CPO_DTLTMsg>();
            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
            // List<DS_CPO_DTLTMsg> insDsCpoDtlTMsgAry = new
            // ArrayList<DS_CPO_DTLTMsg>();
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End
//            updCpoDtlTMsgAry = new ArrayList<CPO_DTLTMsg>();
            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
            // List<DS_CPO_DTLTMsg> updDsCpoDtlTMsgAry = new
            // ArrayList<DS_CPO_DTLTMsg>();
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End
            // For Performance QC#10321 Add End

            for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

                NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);
                final String dtlRqstTpCd = cpoDtlBean.getDtlRqstTpCd();

                if (NWZC150001CpouConstant.CPO_SAVE.equals(rqstTpCd) || //
                        NWZC150001CpouConstant.CPO_SUBMIT.equals(rqstTpCd) //
                        || (NWZC150001CpouConstant.CPO_MODIFY.equals(rqstTpCd) //
                        && NWZC150001CpouConstant.CPO_DTL_SUBMIT.equals(dtlRqstTpCd))) {

                    if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(dtlRqstTpCd)) {
                        continue;
                    }

                    final CPO_DTLTMsg reqCpoDtlTMsg = new CPO_DTLTMsg();
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del
                    // Start
                    // DS_CPO_DTLTMsg reqDsCpoDtlTMsg = new
                    // DS_CPO_DTLTMsg();
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del End

                    // --------------------------------------------------
                    // SAVE
                    // --------------------------------------------------
                    if (NWZC150001CpouConstant.CPO_SAVE.equals(rqstTpCd)) {
                        setCpoDtlTMsgForSave(cpoBean, cpoDtlBean, reqCpoDtlTMsg);
                        // --------------------------------------------------
                        // SUBMIT
                        // --------------------------------------------------
                    } else {
                        setCpoDtlTMsgForSubmit(cpoBean, cpoDtlBean, reqCpoDtlTMsg);
                    }
                    // 2017/03/29 S21_NA#Review structure Lv.1 Mod
                    // Start
                    // setDsCpoDtlTMsgForSaveSubmit(cpoBean,
                    // cpoDtlBean, reqDsCpoDtlTMsg);
                    setWdsColumnOfCpoDtlForSaveSubmit(cpoBean, cpoDtlBean, reqCpoDtlTMsg);
                    // 2017/03/29 S21_NA#Review structure Lv.1 Mod End

                    // ***** [insert]
                    // For Performance QC#10321 Mod Start
                    // insert(reqCpoDtlTMsg);
                    // if
                    // (!RTNCD_NORMAL.equals(reqCpoDtlTMsg.getReturnCode()))
                    // {
                    // throw new
                    // S21AbendException("updateCPODetail index= " + i
                    // + ": Insert Error");
                    // }
                    insCpoDtlTMsgAry.add(reqCpoDtlTMsg);
                    // For Performance QC#10321 Mod End

                    // 2017/03/29 S21_NA#Review structure Lv.1 Del
                    // Start
                    // ***** [insert]
                    // For Performance QC#10321 Mod Start
                    // insert(reqDsCpoDtlTMsg);
                    // if
                    // (!RTNCD_NORMAL.equals(reqDsCpoDtlTMsg.getReturnCode()))
                    // {
                    // StringBuilder errSb = new StringBuilder();
                    // errSb.append("CPO_ORD_NUM:").append(reqDsCpoDtlTMsg.cpoOrdNum.getValue()).append(", CPO_DTL_LINE_NUM:").append(reqDsCpoDtlTMsg.cpoDtlLineNum.getValue()).append(", CPO_DTL_LINE_SUB_NUM:").append(
                    // reqDsCpoDtlTMsg.cpoDtlLineSubNum.getValue());
                    // throw new
                    // S21AbendException(NWZC150001CpouConstant.NWZM1023E,
                    // new String[] {reqDsCpoDtlTMsg.getTableName(),
                    // errSb.toString() });
                    // }
                    // insDsCpoDtlTMsgAry.add(reqDsCpoDtlTMsg);
                    // For Performance QC#10321 Mod End
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del End

                } else {

                    final CPO_DTLTMsg reqCpoDtlTMsg = NWZC150001CpouExistsCdInDbCheck.getCpoDtlByPK(cpoBean, cpoDtlBean);
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del
                    // Start
                    // DS_CPO_DTLTMsg reqDsCpoDtlTMsg =
                    // getDsCpoDtlByPK(cpoBean, cpoDtlBean);
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del End

                    // --------------------------------------------------
                    // MODIFY
                    // --------------------------------------------------
                    if (NWZC150001CpouConstant.CPO_MODIFY.equals(rqstTpCd)) {
                        setCpoDtlTMsgForModify(cpoBean, cpoDtlBean, reqCpoDtlTMsg);
                        // 2017/03/29 S21_NA#Review structure Lv.1 Mod
                        // Start
                        // setDsCpoDtlTMsgForModify(cpoBean,
                        // cpoDtlBean, reqDsCpoDtlTMsg);
                        setWdsColumnsOfCpoDtlTMsgForModify(cpoBean, cpoDtlBean, reqCpoDtlTMsg);
                        // 2017/03/29 S21_NA#Review structure Lv.1 Mod
                        // End
                        // --------------------------------------------------
                        // CANCEL
                        // --------------------------------------------------
                    } else {
                        setCpoDtlTMsgForCancel(cpoBean, cpoDtlBean, reqCpoDtlTMsg);
                    }

                    // ***** [update]
                    // For Performance QC#10321 Mod Start
                    // update(reqCpoDtlTMsg);
                    // if
                    // (!RTNCD_NORMAL.equals(reqCpoDtlTMsg.getReturnCode()))
                    // {
                    // throw new
                    // S21AbendException("updateCPODetail index= " + i
                    // + ": Update Error");
                    // }
                    updCpoDtlTMsgAry.add(reqCpoDtlTMsg);
                    // For Performance QC#10321 Mod End

                    // 2017/03/29 S21_NA#Review structure Lv.1 Del
                    // Start
                    // if
                    // (NWZC150001CpouConstant.CPO_MODIFY.equals(rqstTpCd))
                    // {
                    // // ***** [update]
                    // // For Performance QC#10321 Mod Start
                    // // update(reqDsCpoDtlTMsg);
                    // // if
                    // (!RTNCD_NORMAL.equals(reqDsCpoDtlTMsg.getReturnCode()))
                    // {
                    // // StringBuilder errSb = new StringBuilder();
                    // //
                    // errSb.append("CPO_ORD_NUM:").append(reqDsCpoDtlTMsg.cpoOrdNum.getValue()).append(", CPO_DTL_LINE_NUM:").append(reqDsCpoDtlTMsg.cpoDtlLineNum.getValue()).append(", CPO_DTL_LINE_SUB_NUM:").append(
                    // //
                    // reqDsCpoDtlTMsg.cpoDtlLineSubNum.getValue());
                    // // throw new
                    // S21AbendException(NWZC150001CpouConstant.NWZM1024E,
                    // new String[] {reqDsCpoDtlTMsg.getTableName(),
                    // errSb.toString() });
                    // // }
                    // updDsCpoDtlTMsgAry.add(reqDsCpoDtlTMsg);
                    // // For Performance QC#10321 Mod End
                    // }
                    // 2017/03/29 S21_NA#Review structure Lv.1 Del End
                }
            }

            // For Performance QC#10321 Add Start
            if (!insCpoDtlTMsgAry.isEmpty()) {
                int cnt = S21FastTBLAccessor.insert(insCpoDtlTMsgAry.toArray(new CPO_DTLTMsg[0]));
                if (cnt != insCpoDtlTMsgAry.size()) {
                    throw new S21AbendException("updateCPODetail : CPO_DTL Insert Error");
                }
            }

            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
            // if (!insDsCpoDtlTMsgAry.isEmpty()) {
            // int cnt =
            // S21FastTBLAccessor.insert(insDsCpoDtlTMsgAry.toArray(new
            // DS_CPO_DTLTMsg[0]));
            // if (cnt != insDsCpoDtlTMsgAry.size()) {
            // throw new
            // S21AbendException("updateCPODetail : DS_CPO_DTL Insert Error");
            // }
            // }
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End

            if (!updCpoDtlTMsgAry.isEmpty()) {
                int cnt = S21FastTBLAccessor.update(updCpoDtlTMsgAry.toArray(new CPO_DTLTMsg[0]));
                if (cnt != updCpoDtlTMsgAry.size()) {
                    throw new S21AbendException("updateCPODetail : CPO_DTL Update Error");
                }
            }

            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
            // if (!updDsCpoDtlTMsgAry.isEmpty()) {
            // int cnt =
            // S21FastTBLAccessor.update(updDsCpoDtlTMsgAry.toArray(new
            // DS_CPO_DTLTMsg[0]));
            // if (cnt != updDsCpoDtlTMsgAry.size()) {
            // throw new
            // S21AbendException("updateCPODetail : DS_CPO_DTL Update Error");
            // }
            // }
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End
            // For Performance QC#10321 Add End

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private static void setCpoDtlTMsgForSave(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean, CPO_DTLTMsg cpoDtlTMsg) {

        setCpoDtlTMsg(cpoBean, cpoDtlBean, cpoDtlTMsg);

        final String cpoOrdNum;
        if (hasValue(cpoBean.getReNumCpoOrdNum())) {
            cpoOrdNum = cpoBean.getReNumCpoOrdNum();
        } else {
            cpoOrdNum = cpoBean.getCpoOrdNum();
        }
        setValue(cpoDtlTMsg.cpoOrdNum, cpoOrdNum);

        setValue(cpoDtlTMsg.cpoDtlLineNum, NWZC150001Common.getCpoDtlLineNumFromBean(cpoDtlBean));
        setValue(cpoDtlTMsg.cpoDtlLineSubNum, NWZC150001Common.getCpoDtlLineSubNumFromBean(cpoDtlBean));

        setValue(cpoDtlTMsg.cancQty, ZERO);
        setValue(cpoDtlTMsg.ordLineStsCd, ORD_LINE_STS.SAVED);
        setValue(cpoDtlTMsg.ordLineDplyStsCd, ORD_LINE_DPLY_STS.ENTERED);
        setValue(cpoDtlTMsg.openFlg, ZYPConstant.FLG_ON_Y);

        setDefaultValues(cpoDtlTMsg);
    }

    private static void setCpoDtlTMsgForSubmit(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean, CPO_DTLTMsg cpoDtlTMsg) {

        setCpoDtlTMsg(cpoBean, cpoDtlBean, cpoDtlTMsg);

        final String cpoOrdNum;
        if (hasValue(cpoBean.getReNumCpoOrdNum())) {
            cpoOrdNum = cpoBean.getReNumCpoOrdNum();
        } else {
            cpoOrdNum = cpoBean.getCpoOrdNum();
        }
        cpoDtlTMsg.cpoOrdNum.setValue(cpoOrdNum);

        cpoDtlTMsg.cpoDtlLineNum.setValue(NWZC150001Common.getCpoDtlLineNumFromBean(cpoDtlBean));
        cpoDtlTMsg.cpoDtlLineSubNum.setValue(NWZC150001Common.getCpoDtlLineSubNumFromBean(cpoDtlBean));

        cpoDtlTMsg.cpoOrdTs.setValue(cpoBean.getSlsDt());
        cpoDtlTMsg.cpoOrdSubmtTs.setValue(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        cpoDtlBean.setCpoOrdSubmtTs(cpoDtlTMsg.cpoOrdSubmtTs.getValue());

        cpoDtlTMsg.cancQty.setValue(ZERO);
        cpoDtlTMsg.ordLineStsCd.setValue(ORD_LINE_STS.VALIDATED);
        cpoDtlTMsg.ordLineDplyStsCd.setValue(ORD_LINE_DPLY_STS.ENTERED);
        cpoDtlTMsg.cpoDtlHldFlg.setValue(ZYPConstant.FLG_OFF_N);
        cpoDtlTMsg.submtFlg.setValue(ZYPConstant.FLG_ON_Y);
        cpoDtlTMsg.openFlg.setValue(ZYPConstant.FLG_ON_Y);

        setDefaultValues(cpoDtlTMsg);
    }

    /**
     * DS_CPO_DTL update value setting(Save or Submit)
     * @param cpoBean NWZC150001CpouBean
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @param dsCpoDtlTMsg DS_CPO_DTLTMsg
     */
    // private void setDsCpoDtlTMsgForSaveSubmit(NWZC150001CpouBean
    // cpoBean, NWZC150001CpouDetailBean cpoDtlBean, DS_CPO_DTLTMsg
    // dsCpoDtlTMsg) {
    private void setWdsColumnOfCpoDtlForSaveSubmit(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean, CPO_DTLTMsg cpoDtlTMsg) {
        // 1.2WDS add start -->
        setValue(cpoDtlTMsg.cpoOrdNum, NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
        setValue(cpoDtlTMsg.glblCmpyCd, cpoDtlBean.getGlblCmpyCd());
        setValue(cpoDtlTMsg.cpoDtlLineNum, NWZC150001Common.getCpoDtlLineNumFromBean(cpoDtlBean));
        setValue(cpoDtlTMsg.cpoDtlLineSubNum, NWZC150001Common.getCpoDtlLineSubNumFromBean(cpoDtlBean));
        // START MODIFY M.Fuji [OM028]
        // setValue(cpoDtlTMsg.bomLvlLineNum,
        // cpoDtlBean.getBomLvlLineNum());
        setValue(cpoDtlTMsg.dsOrdPosnNum, cpoDtlBean.getDsOrdPosnNum());
        // END MODIFY M.Fuji [OM028]
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
        // setValue(cpoDtlTMsg.lgcyIntfcDsCpoLineNum,
        // getCpoDtlLineNum(cpoDtlBean) +
        // getCpoDtlLineSubNum(cpoDtlBean));

        // setValue(cpoDtlTMsg.configItemFlg,
        // cpoDtlBean.getConfigItmFlg());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoDtlTMsg.custIstlFlg, cpoDtlBean.getCustIstlFlg());
        // START MODIFY M.Fuji [OM028]
        // setValue(cpoDtlTMsg.machConfigNum,
        // cpoDtlBean.getMachConfigNum());
        setValue(cpoDtlTMsg.svcConfigMstrPk, cpoDtlBean.getSvcConfigMstrPk());
        // END MODIFY M.Fuji [OM028]

        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
        // 20130410 M.Fuji Order Driven Start
        // setValue(cpoDtlTMsg.vndInvtyLocCd,
        // cpoDtlBean.getVndInvtyLocCd());
        // 20130410 M.Fuji Order Driven End
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End

        // 20121129 M.Fuji WDS Solution#104,105 Pricing Start
        setValue(cpoDtlTMsg.unitNetWt, cpoDtlBean.getUnitNetWt());
        // START MODIFY S.Yamamoto [OM003]
        setValue(cpoDtlTMsg.mdseFrtClsCd, cpoDtlBean.getMdseFrtClsCd());
        setValue(cpoDtlTMsg.mdsePrcGrpCd, cpoDtlBean.getMdsePrcGrpCd());
        setValue(cpoDtlTMsg.mdsePrcGrpGrsWt, cpoDtlBean.getMdsePrcGrpGrsWt());
        setValue(cpoDtlTMsg.frtCondCd, cpoDtlBean.getFrtCondCd());
        // END MODIFY S.Yamamoto [OM003]
        // 20121129 M.Fuji WDS Solution#104,105 Pricing End

        // START ADD M.Fuji [OM010]
        setValue(cpoDtlTMsg.dsContrNum, cpoDtlBean.getDsContrNum());
        setValue(cpoDtlTMsg.dsContrSqNum, cpoDtlBean.getDsContrSqNum());
        // END ADD M.Fuji [OM010]

        // START ADD M.Fuji [OM028]
        setValue(cpoDtlTMsg.baseCmptFlg, cpoDtlBean.getBaseCmptFlg());
        // END ADD M.Fuji [OM028]

        // 2015/08/27 CSA Add Start
        setValue(cpoDtlTMsg.dsCpoLineNum, cpoDtlBean.getDsCpoLineNum());
        setValue(cpoDtlTMsg.dsCpoLineSubNum, cpoDtlBean.getDsCpoLineSubNum());
        setValue(cpoDtlTMsg.dsOrdLineCatgCd, cpoDtlBean.getDsOrdLineCatgCd());
        setValue(cpoDtlTMsg.ordLineSrcCd, cpoDtlBean.getOrdLineSrcCd());
        setValue(cpoDtlTMsg.rtlWhCd, cpoDtlBean.getRtlWhCd());
        setValue(cpoDtlTMsg.rtlSwhCd, cpoDtlBean.getRtlSwhCd());
        setValue(cpoDtlTMsg.serNum, cpoDtlBean.getSerNum());
        setValue(cpoDtlTMsg.flPrcListCd, cpoDtlBean.getFlPrcListCd());
        // setValue(cpoDtlTMsg.cpoDtlDealTaxAmt,
        // cpoDtlBean.getCpoDtlDealTaxAmt());
        setValue(cpoDtlTMsg.dealPrcListPrcAmt, cpoDtlBean.getDealPrcListPrcAmt());
        setValue(cpoDtlTMsg.funcPrcListPrcAmt, cpoDtlBean.getFuncPrcListPrcAmt());
        setValue(cpoDtlTMsg.refCpoDtlLineNum, cpoDtlBean.getRefCpoDtlLineNum());
        setValue(cpoDtlTMsg.refCpoDtlLineSubNum, cpoDtlBean.getRefCpoDtlLineSubNum());
        setValue(cpoDtlTMsg.dplyLineRefNum, cpoDtlBean.getDplyLineRefNum());
        setValue(cpoDtlTMsg.crRebilCd, cpoDtlBean.getCrRebilCd());
        setValue(cpoDtlTMsg.prcBaseDt, cpoDtlBean.getPrcBaseDt());
        setValue(cpoDtlTMsg.splyNm, cpoDtlBean.getSplyNm());
        setValue(cpoDtlTMsg.splyTpCd, cpoDtlBean.getSplyTpCd());
        setValue(cpoDtlTMsg.splyFirstAddr, cpoDtlBean.getSplyFirstAddr());
        setValue(cpoDtlTMsg.splyCtyAddr, cpoDtlBean.getSplyCtyAddr());
        setValue(cpoDtlTMsg.splyStCd, cpoDtlBean.getSplyStCd());
        setValue(cpoDtlTMsg.splyPostCd, cpoDtlBean.getSplyPostCd());
        setValue(cpoDtlTMsg.csmpContrNum, cpoDtlBean.getCsmpContrNum());
        setValue(cpoDtlTMsg.dlrRefNum, cpoDtlBean.getDlrRefNum());
        setValue(cpoDtlTMsg.csmpPrcListCd, cpoDtlBean.getCsmpPrcListCd());
        setValue(cpoDtlTMsg.rntlTrmnDt, cpoDtlBean.getRntlTrmnDt());
        setValue(cpoDtlTMsg.bllgAttrbCustAcctCd, cpoDtlBean.getBllgAttrbCustAcctCd());
        setValue(cpoDtlTMsg.firstBllgAttrbNm, cpoDtlBean.getFirstBllgAttrbNm());
        setValue(cpoDtlTMsg.firstBllgAttrbValTxt, cpoDtlBean.getFirstBllgAttrbValTxt());
        setValue(cpoDtlTMsg.scdBllgAttrbNm, cpoDtlBean.getScdBllgAttrbNm());
        setValue(cpoDtlTMsg.scdBllgAttrbValTxt, cpoDtlBean.getScdBllgAttrbValTxt());
        setValue(cpoDtlTMsg.thirdBllgAttrbNm, cpoDtlBean.getThirdBllgAttrbNm());
        setValue(cpoDtlTMsg.thirdBllgAttrbValTxt, cpoDtlBean.getThirdBllgAttrbValTxt());
        setValue(cpoDtlTMsg.frthBllgAttrbNm, cpoDtlBean.getFrthBllgAttrbNm());
        setValue(cpoDtlTMsg.frthBllgAttrbValTxt, cpoDtlBean.getFrthBllgAttrbValTxt());
        setValue(cpoDtlTMsg.fifthBllgAttrbNm, cpoDtlBean.getFifthBllgAttrbNm());
        setValue(cpoDtlTMsg.fifthBllgAttrbValTxt, cpoDtlBean.getFifthBllgAttrbValTxt());
        setValue(cpoDtlTMsg.sixthBllgAttrbNm, cpoDtlBean.getSixthBllgAttrbNm());
        setValue(cpoDtlTMsg.sixthBllgAttrbValTxt, cpoDtlBean.getSixthBllgAttrbValTxt());
        setValue(cpoDtlTMsg.sbstMdseCd, cpoDtlBean.getSbstMdseCd());
        setValue(cpoDtlTMsg.ordSrcRefLineNum, cpoDtlBean.getOrdSrcRefLineNum());
        setValue(cpoDtlTMsg.ordSrcRefLineSubNum, cpoDtlBean.getOrdSrcRefLineSubNum());
        setValue(cpoDtlTMsg.carrSvcLvlCd, cpoDtlBean.getCarrSvcLvlCd());
        if (ZYPCommonFunc.hasValue(cpoDtlBean.getSupdLockFlg())) {
            setValue(cpoDtlTMsg.supdLockFlg, cpoDtlBean.getSupdLockFlg());
        } else {
            setValue(cpoDtlTMsg.supdLockFlg, ZYPConstant.FLG_OFF_N);
        }
        setValue(cpoDtlTMsg.prcListEquipConfigNum, cpoDtlBean.getPrcListEquipConfigNum());
        setValue(cpoDtlTMsg.loanBalQty, cpoDtlBean.getLoanBalQty());
        setValue(cpoDtlTMsg.funcSvcRevTrnsfAmt, cpoDtlBean.getFuncSvcRevTrnsfAmt());
        setValue(cpoDtlTMsg.dealSvcRevTrnsfAmt, cpoDtlBean.getDealSvcRevTrnsfAmt());
        setValue(cpoDtlTMsg.funcSvcCostTrnsfAmt, cpoDtlBean.getFuncSvcCostTrnsfAmt());
        setValue(cpoDtlTMsg.dealSvcCostTrnsfAmt, cpoDtlBean.getDealSvcCostTrnsfAmt());
        setValue(cpoDtlTMsg.funcManFlAdjAmt, cpoDtlBean.getFuncManFlAdjAmt());
        setValue(cpoDtlTMsg.dealManFlAdjAmt, cpoDtlBean.getDealManFlAdjAmt());
        setValue(cpoDtlTMsg.funcManRepRevAdjAmt, cpoDtlBean.getFuncManRepRevAdjAmt());
        setValue(cpoDtlTMsg.dealManRepRevAdjAmt, cpoDtlBean.getDealManRepRevAdjAmt());
        if (ZYPCommonFunc.hasValue(cpoDtlBean.getShopItemFlg())) {
            setValue(cpoDtlTMsg.shopItemFlg, cpoDtlBean.getShopItemFlg());
        } else {
            setValue(cpoDtlTMsg.shopItemFlg, ZYPConstant.FLG_OFF_N);
        }
        setValue(cpoDtlTMsg.ordUpldRefNum, cpoDtlBean.getOrdUpldRefNum());

        setValue(cpoDtlTMsg.hardDriveRmvInclFlg, cpoDtlBean.getHardDriveRmvInclFlg());
        setValue(cpoDtlTMsg.hardDriveEraseInclFlg, cpoDtlBean.getHardDriveEraseInclFlg());
        setValue(cpoDtlTMsg.convCompFlg, ZYPConstant.FLG_OFF_N);
        setValue(cpoDtlTMsg.pickSvcConfigMstrPk, cpoDtlBean.getPickSvcConfigMstrPk());
        setValue(cpoDtlTMsg.svcMachMstrPk, cpoDtlBean.getSvcMachMstrPk());

        // 2015/08/27 CSA Add End
        setValue(cpoDtlTMsg.dsCpoConfigPk, cpoDtlBean.getDsCpoConfigPk()); // 2015/1203
        // S21_NA#1407
        // Add

        setValue(cpoDtlTMsg.origCpoDtlLineNum, cpoDtlBean.getOrigCpoDtlLineNum()); // S21_NA#7606
        setValue(cpoDtlTMsg.origCpoDtlLineSubNum, cpoDtlBean.getOrigCpoDtlLineSubNum()); // S21_NA#7606

        setDefaultValuesForWdsColmunsOfCpoDtl(cpoDtlTMsg);
        // 1.2WDS add end <--

        // 2016/09/05 S21_NA#6100 Add Start
        setValue(cpoDtlTMsg.dsCpoDtlCratUsrId, cpoDtlBean.getDsCpoDtlCratUsrId());
        setValue(cpoDtlTMsg.dsCpoDtlCratTs, cpoDtlBean.getDsCpoDtlCratTs());
        setValue(cpoDtlTMsg.dsCpoDtlUpdUsrId, cpoDtlBean.getDsCpoDtlUpdUsrId());
        setValue(cpoDtlTMsg.dsCpoDtlUpdTs, cpoDtlBean.getDsCpoDtlUpdTs());
        // 2016/09/05 S21_NA#6100 Add End
        setValue(cpoDtlTMsg.convProcStsCd, cpoDtlBean.getConvProcStsCd()); // QC#16425
        // 2016/12/14
        // Add

        // Add Start 2017/10/17 QC#21507
        setValue(cpoDtlTMsg.cpoSrcTpCd, cpoDtlBean.getCpoSrcTpCd());
        setValue(cpoDtlTMsg.ordSrcRefNum, cpoDtlBean.getOrdSrcRefNum());
        // Add End 2017/10/17 QC#21507

        // 2019/10/04 S21_NA#51372 Add Start
        setValue(cpoDtlTMsg.prntVndNm, cpoDtlBean.getPrntVndNm());
        // 2019/10/04 S21_NA#51372 Add End
    }

    // private void setDefaultValues(DS_CPO_DTLTMsg dsCpoDtlTMsg) {
    private void setDefaultValuesForWdsColmunsOfCpoDtl(CPO_DTLTMsg cpoDtlTMsg) {
        // 1.2WDS add start -->
        // FLG
        Set<EZDTStringItem> flgItemList = new HashSet<EZDTStringItem>();
        flgItemList.add(cpoDtlTMsg.custIstlFlg);
        flgItemList.add(cpoDtlTMsg.configItemFlg);
        flgItemList.add(cpoDtlTMsg.baseCmptFlg);

        for (EZDTStringItem flgItem : flgItemList) {
            if (!hasValue(flgItem)) {
                setValue(flgItem, ZYPConstant.FLG_OFF_N);
            }
        }
        // 1.2WDS add end <--

        // CSA(not firm) S.Yamamoto TODO
        final Set<EZDTBigDecimalItem> amtItemList = new HashSet<EZDTBigDecimalItem>();
        // amtItemList.add(dsCpoDtlTMsg.cpoDtlDealTaxAmt);
        for (EZDTBigDecimalItem amtItem : amtItemList) {
            if (!hasValue(amtItem)) {
                setValue(amtItem, ZERO);
            }
        }
    }

    /**
     * postCPO_DTL
     * 
     * <pre>
   * NWZC150001CpouDetailBean => CPO_DTLTMsg The registration data is posted.
   * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @param cpoDtlTMsg CPO_DTLTMsg
     */
    private static void setCpoDtlTMsg(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean, CPO_DTLTMsg cpoDtlTMsg) {

        if (!hasValue(cpoBean.getReNumCpoOrdNum())) {
            setValue(cpoDtlTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
        } else {
            setValue(cpoDtlTMsg.cpoOrdNum, cpoBean.getReNumCpoOrdNum());
        }

        setValue(cpoDtlTMsg.glblCmpyCd, cpoDtlBean.getGlblCmpyCd());
        setValue(cpoDtlTMsg.cpoOrdTpCd, cpoDtlBean.getCpoOrdTpCd());
        setValue(cpoDtlTMsg.cpoDtlLineNum, cpoDtlBean.getCpoDtlLineNum());
        setValue(cpoDtlTMsg.cpoDtlLineSubNum, cpoDtlBean.getCpoDtlLineSubNum());
        setValue(cpoDtlTMsg.cpoOrdTs, cpoDtlBean.getCpoOrdTs());
        setValue(cpoDtlTMsg.cpoOrdSubmtTs, cpoDtlBean.getCpoOrdSubmtTs());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
        // setValue(cpoDtlTMsg.fifthProdCtrlCd,
        // cpoDtlBean.getFifthProdCtrlCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoDtlTMsg.mdseCd, cpoDtlBean.getMdseCd());
        setValue(cpoDtlTMsg.mdseNm, cpoDtlBean.getMdseNm());
        setValue(cpoDtlTMsg.origMdseCd, cpoDtlBean.getOrigMdseCd());
        setValue(cpoDtlTMsg.origMdseNm, cpoDtlBean.getOrigMdseNm());
        setValue(cpoDtlTMsg.setMdseCd, cpoDtlBean.getSetMdseCd());
        setValue(cpoDtlTMsg.dropShipFlg, cpoDtlBean.getDropShipFlg());
        setValue(cpoDtlTMsg.shipToCustCd, cpoDtlBean.getShipToCustCd());
        setValue(cpoDtlTMsg.shipToLocNm, cpoDtlBean.getShipToLocNm());
        setValue(cpoDtlTMsg.shipToAddlLocNm, cpoDtlBean.getShipToAddlLocNm());
        setValue(cpoDtlTMsg.shipToFirstLineAddr, cpoDtlBean.getShipToFirstLineAddr());
        setValue(cpoDtlTMsg.shipToScdLineAddr, cpoDtlBean.getShipToScdLineAddr());
        setValue(cpoDtlTMsg.shipToThirdLineAddr, cpoDtlBean.getShipToThirdLineAddr());
        setValue(cpoDtlTMsg.shipToFrthLineAddr, cpoDtlBean.getShipToFrthLineAddr());
        setValue(cpoDtlTMsg.shipToFirstRefCmntTxt, cpoDtlBean.getShipToFirstRefCmntTxt());
        setValue(cpoDtlTMsg.shipToScdRefCmntTxt, cpoDtlBean.getShipToScdRefCmntTxt());
        setValue(cpoDtlTMsg.shipToCtyAddr, cpoDtlBean.getShipToCtyAddr());
        setValue(cpoDtlTMsg.shipToStCd, cpoDtlBean.getShipToStCd());
        setValue(cpoDtlTMsg.shipToProvNm, cpoDtlBean.getShipToProvNm());
        setValue(cpoDtlTMsg.shipToCntyNm, cpoDtlBean.getShipToCntyNm());
        setValue(cpoDtlTMsg.shipToPostCd, cpoDtlBean.getShipToPostCd());
        setValue(cpoDtlTMsg.shipToCtryCd, cpoDtlBean.getShipToCtryCd());
        setValue(cpoDtlTMsg.ordQty, cpoDtlBean.getOrdQty());
        setValue(cpoDtlTMsg.ordCustUomQty, cpoDtlBean.getOrdCustUomQty());
        setValue(cpoDtlTMsg.shipQty, cpoDtlBean.getShipQty());
        setValue(cpoDtlTMsg.cancQty, cpoDtlBean.getCancQty());
        setValue(cpoDtlTMsg.invtyLocCd, cpoDtlBean.getInvtyLocCd());
        setValue(cpoDtlTMsg.entDealNetUnitPrcAmt, cpoDtlBean.getEntDealNetUnitPrcAmt());
        setValue(cpoDtlTMsg.entCpoDtlDealNetAmt, cpoDtlBean.getEntCpoDtlDealNetAmt());
        setValue(cpoDtlTMsg.entCpoDtlDealSlsAmt, cpoDtlBean.getEntCpoDtlDealSlsAmt());
        setValue(cpoDtlTMsg.entFuncNetUnitPrcAmt, cpoDtlBean.getEntFuncNetUnitPrcAmt());
        setValue(cpoDtlTMsg.entCpoDtlFuncNetAmt, cpoDtlBean.getEntCpoDtlFuncNetAmt());
        setValue(cpoDtlTMsg.entCpoDtlFuncSlsAmt, cpoDtlBean.getEntCpoDtlFuncSlsAmt());
        setValue(cpoDtlTMsg.cpoDtlDealNetAmt, cpoDtlBean.getCpoDtlDealNetAmt());
        setValue(cpoDtlTMsg.cpoDtlDealSlsAmt, cpoDtlBean.getCpoDtlDealSlsAmt());
        setValue(cpoDtlTMsg.cpoDtlFuncNetAmt, cpoDtlBean.getCpoDtlFuncNetAmt());
        setValue(cpoDtlTMsg.cpoDtlFuncSlsAmt, cpoDtlBean.getCpoDtlFuncSlsAmt());
        setValue(cpoDtlTMsg.ccyCd, cpoDtlBean.getCcyCd());
        setValue(cpoDtlTMsg.exchRate, cpoDtlBean.getExchRate());
        setValue(cpoDtlTMsg.taxFlg, cpoDtlBean.getTaxFlg());
        setValue(cpoDtlTMsg.rddDt, cpoDtlBean.getRddDt());
        setValue(cpoDtlTMsg.rsdDt, cpoDtlBean.getRsdDt());
        setValue(cpoDtlTMsg.expdShipDt, cpoDtlBean.getExpdShipDt());
        setValue(cpoDtlTMsg.shipCpltCd, cpoDtlBean.getShipCpltCd());
        setValue(cpoDtlTMsg.uomCpltFlg, cpoDtlBean.getUomCpltFlg());
        setValue(cpoDtlTMsg.ordLineStsCd, cpoDtlBean.getOrdLineStsCd());
        setValue(cpoDtlTMsg.stkStsCd, cpoDtlBean.getStkStsCd());
        setValue(cpoDtlTMsg.pmtTermCashDiscCd, cpoDtlBean.getPmtTermCashDiscCd());
        setValue(cpoDtlTMsg.pmtTermCd, cpoDtlBean.getPmtTermCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
        // setValue(cpoDtlTMsg.pmtMethCd, cpoDtlBean.getPmtMethCd());
        // setValue(cpoDtlTMsg.pmtCondCd, cpoDtlBean.getPmtCondCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoDtlTMsg.cashDiscTermCd, cpoDtlBean.getCashDiscTermCd());
        setValue(cpoDtlTMsg.frtChrgToCd, cpoDtlBean.getFrtChrgToCd());
        setValue(cpoDtlTMsg.frtChrgMethCd, cpoDtlBean.getFrtChrgMethCd());
        setValue(cpoDtlTMsg.slsRepOrSlsTeamTocCd, cpoDtlBean.getSlsRepOrSlsTeamTocCd());
        setValue(cpoDtlTMsg.shpgSvcLvlCd, cpoDtlBean.getShpgSvcLvlCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
        // setValue(cpoDtlTMsg.cancDelyLimitDt,
        // cpoDtlBean.getCancDelyLimitDt());
        // setValue(cpoDtlTMsg.cancShipLimitDt,
        // cpoDtlBean.getCancShipLimitDt());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoDtlTMsg.cpoDtlCancDt, cpoDtlBean.getCpoDtlCancDt());
        setValue(cpoDtlTMsg.cpoDtlCancFlg, cpoDtlBean.getCpoDtlCancFlg());
        setValue(cpoDtlTMsg.custMdseCd, cpoDtlBean.getCustMdseCd());
        setValue(cpoDtlTMsg.custUomCd, cpoDtlBean.getCustUomCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
        // setValue(cpoDtlTMsg.ediStsCd, cpoDtlBean.getEdiStsCd());
        // setValue(cpoDtlTMsg.ediNum, cpoDtlBean.getEdiNum());
        // setValue(cpoDtlTMsg.ediSubNum, cpoDtlBean.getEdiSubNum());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoDtlTMsg.manPrcFlg, cpoDtlBean.getManPrcFlg());
        setValue(cpoDtlTMsg.prcTmgCd, cpoDtlBean.getPrcTmgCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
        // setValue(cpoDtlTMsg.trialLoanRqstNum,
        // cpoDtlBean.getTrialLoanRqstNum());
        // setValue(cpoDtlTMsg.trialLoanLineNum,
        // cpoDtlBean.getTrialLoanLineNum());
        // setValue(cpoDtlTMsg.trialLoanLineSubNum,
        // cpoDtlBean.getTrialLoanLineSubNum());
        // setValue(cpoDtlTMsg.crDrRsnSubCd,
        // cpoDtlBean.getCrDrRsnSubCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoDtlTMsg.origCpoOrdNum, cpoDtlBean.getOrigCpoOrdNum());
        setValue(cpoDtlTMsg.origInvNum, cpoDtlBean.getOrigInvNum());
        setValue(cpoDtlTMsg.origInvNum, cpoDtlBean.getOrigInvNum());
        // // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
        // setValue(cpoDtlTMsg.dslpDealNetUnitPrcAmt,
        // cpoDtlBean.getDslpDealNetUnitPrcAmt());
        // setValue(cpoDtlTMsg.dslpPmtTermCashDiscCd,
        // cpoDtlBean.getDslpPmtTermCashDiscCd());
        // setValue(cpoDtlTMsg.dslpPmtTermCd,
        // cpoDtlBean.getDslpPmtTermCd());
        // setValue(cpoDtlTMsg.dslpCashDiscTermCd,
        // cpoDtlBean.getDslpCashDiscTermCd());
        // setValue(cpoDtlTMsg.dslpFrtChrgToCd,
        // cpoDtlBean.getDslpFrtChrgToCd());
        // setValue(cpoDtlTMsg.dslpFrtChrgMethCd,
        // cpoDtlBean.getDslpFrtChrgMethCd());
        // setValue(cpoDtlTMsg.dslpCcyCd, cpoDtlBean.getDslpCcyCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoDtlTMsg.submtFlg, cpoDtlBean.getSubmtFlg());
        setValue(cpoDtlTMsg.openFlg, cpoDtlBean.getOpenFlg());
        setValue(cpoDtlTMsg.carrCd, cpoDtlBean.getCarrCd());
        setValue(cpoDtlTMsg.carrAcctNum, cpoDtlBean.getCarrAcctNum());
        setValue(cpoDtlTMsg.carrAcctShipNum, cpoDtlBean.getCarrAcctShipNum());
        setValue(cpoDtlTMsg.coaAcctCd, cpoDtlBean.getCoaAcctCd());
        setValue(cpoDtlTMsg.coaProjCd, cpoDtlBean.getCoaProjCd());
        setValue(cpoDtlTMsg.invNum, cpoDtlBean.getInvNum());
        setValue(cpoDtlTMsg.exptFlg, cpoDtlBean.getExptFlg());
        setValue(cpoDtlTMsg.setItemShipCpltFlg, cpoDtlBean.getSetItemShipCpltFlg());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
        setValue(cpoDtlTMsg.distItemFlg, cpoDtlBean.getDistItemFlg());
        setValue(cpoDtlTMsg.exptShpgMethCd, cpoDtlBean.getExptShpgMethCd());
        setValue(cpoDtlTMsg.exptShpgTermCd, cpoDtlBean.getExptShpgTermCd());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoDtlTMsg.coaCmpyCd, cpoDtlBean.getCoaCmpyCd());
        setValue(cpoDtlTMsg.coaAfflCd, cpoDtlBean.getCoaAfflCd());
        setValue(cpoDtlTMsg.coaBrCd, cpoDtlBean.getCoaBrCd());
        setValue(cpoDtlTMsg.coaChCd, cpoDtlBean.getCoaChCd());
        setValue(cpoDtlTMsg.coaCcCd, cpoDtlBean.getCoaCcCd());
        setValue(cpoDtlTMsg.coaExtnCd, cpoDtlBean.getCoaExtnCd());
        setValue(cpoDtlTMsg.coaProdCd, cpoDtlBean.getCoaProdCd());
        setValue(cpoDtlTMsg.prcCatgCd, cpoDtlBean.getPrcCatgCd());
        // Def#1864
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
        setValue(cpoDtlTMsg.manPmtFlg, cpoDtlBean.getManPmtFlg());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        setValue(cpoDtlTMsg.origCpoOrdNum, cpoDtlBean.getOrigCpoOrdNum()); // S21_NA#7606
        // Add Start 2017/10/16 QC#21507
        setValue(cpoDtlTMsg.cpoSrcTpCd, cpoDtlBean.getCpoSrcTpCd());
        setValue(cpoDtlTMsg.ordSrcRefNum, cpoDtlBean.getOrdSrcRefNum());
        // Add End 2017/10/16 QC#21507
        setValue(cpoDtlTMsg.origInvtyLocCd, cpoDtlBean.getOrigInvtyLocCd());// 2017/10/20 QC#21730 Add
        // 2018/01/11 S21_NA#22372 Add Start
        setValue(cpoDtlTMsg.funcUnitFlPrcAmt, cpoDtlBean.getFuncUnitFlPrcAmt());
        // 2018/01/11 S21_NA#22372 Add End
        // 2019/11/15 S21_NA#54199 Add Start
        // If the detail bean don't have display status code, DO NOT SET DISPLAY STATUS!!!!
        if (ZYPCommonFunc.hasValue(cpoDtlBean.getOrdLineDplyStsCd())) {
            cpoDtlTMsg.ordLineDplyStsCd.setValue(cpoDtlBean.getOrdLineDplyStsCd());
        }
        // 2019/11/15 S21_NA#54199 Add End
    }

    /**
     * CPO_DTL update value setting (RequestType Modify)
     * 
     * <pre>
   * The value in which CPO_DTL is renewed is set.
   * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @param cpoDtlMsg CPO_DTLTMsg
     * @return CPO_DTLTMsg
     */
    private static void setCpoDtlTMsgForModify(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean, CPO_DTLTMsg cpoDtlMsg) {

        // The value not changed is saved.
        final String cpoOrdTs = cpoDtlMsg.cpoOrdTs.getValue();
        cpoDtlBean.setCpoOrdTs(cpoOrdTs);
        final String cpoOrdSubmtTs = cpoDtlMsg.cpoOrdSubmtTs.getValue();
        cpoDtlBean.setCpoOrdSubmtTs(cpoOrdSubmtTs);
        final BigDecimal shipQty = cpoDtlMsg.shipQty.getValue();
        BigDecimal cancQty = cpoDtlMsg.cancQty.getValue(); // 2018/08/02 S21_NA#25404 Del "final"
        // 2018/08/02 S21_NA#25404 Add Start
        if (ZYPCommonFunc.hasValue(cpoDtlBean.getChngRsnTpCd()) //
                || ZYPCommonFunc.hasValue(cpoDtlBean.getBizProcCmntTxt())) {
            cancQty = cancQty.add(cpoDtlMsg.ordQty.getValue().subtract(cpoDtlBean.getOrdQty()));
        }
        // 2018/08/02 S21_NA#25404 Add End
        final BigDecimal exchRate = cpoDtlMsg.exchRate.getValue();
        final String ordLineStsCd = cpoDtlMsg.ordLineStsCd.getValue();
        final String cpoDtlCancFlg = cpoDtlMsg.cpoDtlCancFlg.getValue();
        final String cpoDtlHldFlg = cpoDtlMsg.cpoDtlHldFlg.getValue();
        final String submitFlg = cpoDtlMsg.submtFlg.getValue();
        final String openFlg = cpoDtlMsg.openFlg.getValue();

        setCpoDtlTMsg(cpoBean, cpoDtlBean, cpoDtlMsg);

        cpoDtlMsg.cpoDtlCancDt.clear();
        // Def#1664 delete ->
        // cpoDtlMsg.trialLoanRqstNum.clear();
        // cpoDtlMsg.trialLoanLineNum.clear();
        // cpoDtlMsg.trialLoanLineSubNum.clear();
        // cpoDtlMsg.crDrRsnSubCd.clear();
        // cpoDtlMsg.origInvNum.clear();
        // Def#1664 delete <-
        // QC#8499 DEL Start
        // cpoDtlMsg.origCpoOrdNum.clear();
        // QC#8499 DEL End
        cpoDtlMsg.invNum.clear();

        cpoDtlMsg.cpoOrdTs.setValue(cpoOrdTs);
        cpoDtlMsg.cpoOrdSubmtTs.setValue(cpoOrdSubmtTs);
        cpoDtlMsg.shipQty.setValue(shipQty);
        cpoDtlMsg.cancQty.setValue(cancQty);
        if (!hasValue(cpoDtlMsg.exchRate.getValue())) {
            cpoDtlMsg.exchRate.setValue(exchRate);
        }
        if (!hasValue(cpoDtlMsg.ordLineStsCd.getValue())) {
            cpoDtlMsg.ordLineStsCd.setValue(ordLineStsCd);
        }

        cpoDtlMsg.cpoDtlCancFlg.setValue(cpoDtlCancFlg);
        cpoDtlMsg.cpoDtlHldFlg.setValue(cpoDtlHldFlg);
        cpoDtlMsg.submtFlg.setValue(submitFlg);
        cpoDtlMsg.openFlg.setValue(openFlg);
        // START 2021/04/19 A.Marte [QC#58549, ADD]
        // update value of line OpenFlag, if it is updated
        if (ZYPCommonFunc.hasValue(cpoDtlBean.getOpenFlg()) && NWZC150001Constant.IR_SRC_TP_CD.equals(cpoBean.getCpoSrcTpCd())) {
            cpoDtlMsg.openFlg.setValue(cpoDtlBean.getOpenFlg());
        }
        // END 2021/04/19 A.Marte [QC#58549, ADD]

        setDefaultValues(cpoDtlMsg);
    }

    /**
     * DS_CPO_DTL update value setting(Modify)
     * @param cpoBean NWZC150001CpouBean
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @param dsCpoDtlTMsg DS_CPO_DTLTMsg
     */
    // private void setDsCpoDtlTMsgForModify(NWZC150001CpouBean
    // cpoBean, NWZC150001CpouDetailBean cpoDtlBean, DS_CPO_DTLTMsg
    // dsCpoDtlTMsg) {
    private void setWdsColumnsOfCpoDtlTMsgForModify(NWZC150001CpouBean cpoBean, //
            NWZC150001CpouDetailBean cpoDtlBean, //
            CPO_DTLTMsg cpoDtlTMsg) {
        // 1.2WDS add start -->
        setValue(cpoDtlTMsg.cpoOrdNum, NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
        setValue(cpoDtlTMsg.glblCmpyCd, cpoDtlBean.getGlblCmpyCd());
        setValue(cpoDtlTMsg.cpoDtlLineNum, cpoDtlBean.getCpoDtlLineNum());
        setValue(cpoDtlTMsg.cpoDtlLineSubNum, cpoDtlBean.getCpoDtlLineSubNum());
        // START MODIFY M.Fuji [OM028]
        // setValue(cpoDtlTMsg.bomLvlLineNum,
        // cpoDtlBean.getBomLvlLineNum());
        setValue(cpoDtlTMsg.dsOrdPosnNum, cpoDtlBean.getDsOrdPosnNum());
        // END MODIFY M.Fuji [OM028]
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
        // setValue(cpoDtlTMsg.lgcyIntfcDsCpoLineNum,
        // cpoDtlBean.getCpoDtlLineNum() +
        // cpoDtlBean.getCpoDtlLineSubNum());

        // setValue(cpoDtlTMsg.configItemFlg,
        // cpoDtlBean.getConfigItmFlg());
        setValue(cpoDtlTMsg.custIstlFlg, cpoDtlBean.getCustIstlFlg());
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End
        // START MODIFY M.Fuji [OM028]
        // setValue(cpoDtlTMsg.machConfigNum,
        // cpoDtlBean.getMachConfigNum());
        setValue(cpoDtlTMsg.svcConfigMstrPk, cpoDtlBean.getSvcConfigMstrPk());
        // END MODIFY M.Fuji [OM028]

        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
        // 20130410 M.Fuji Order Driven Start
        // setValue(cpoDtlTMsg.vndInvtyLocCd,
        // cpoDtlBean.getVndInvtyLocCd());
        // 20130410 M.Fuji Order Driven End
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End

        // 20121129 M.Fuji WDS Solution#104,105 Pricing Start
        setValue(cpoDtlTMsg.unitNetWt, cpoDtlBean.getUnitNetWt());
        // START MODIFY S.Yamamoto [OM003]
        setValue(cpoDtlTMsg.mdseFrtClsCd, cpoDtlBean.getMdseFrtClsCd());
        setValue(cpoDtlTMsg.mdsePrcGrpCd, cpoDtlBean.getMdsePrcGrpCd());
        setValue(cpoDtlTMsg.mdsePrcGrpGrsWt, cpoDtlBean.getMdsePrcGrpGrsWt());
        setValue(cpoDtlTMsg.frtCondCd, cpoDtlBean.getFrtCondCd());
        // END MODIFY S.Yamamoto [OM003]
        // 20121129 M.Fuji WDS Solution#104,105 Pricing End

        // START ADD M.Fuji [OM010]
        setValue(cpoDtlTMsg.dsContrNum, cpoDtlBean.getDsContrNum());
        setValue(cpoDtlTMsg.dsContrSqNum, cpoDtlBean.getDsContrSqNum());
        // END ADD M.Fuji [OM010]

        // START ADD M.Fuji [OM028]
        setValue(cpoDtlTMsg.baseCmptFlg, cpoDtlBean.getBaseCmptFlg());
        // END ADD M.Fuji [OM028]

        // 2015/08/27 CSA Add Start
        setValue(cpoDtlTMsg.dsOrdLineCatgCd, cpoDtlBean.getDsOrdLineCatgCd());
        setValue(cpoDtlTMsg.ordLineSrcCd, cpoDtlBean.getOrdLineSrcCd());
        setValue(cpoDtlTMsg.rtlWhCd, cpoDtlBean.getRtlWhCd());
        setValue(cpoDtlTMsg.rtlSwhCd, cpoDtlBean.getRtlSwhCd());
        setValue(cpoDtlTMsg.serNum, cpoDtlBean.getSerNum());
        setValue(cpoDtlTMsg.flPrcListCd, cpoDtlBean.getFlPrcListCd());
        setValue(cpoDtlTMsg.dealPrcListPrcAmt, cpoDtlBean.getDealPrcListPrcAmt());
        setValue(cpoDtlTMsg.funcPrcListPrcAmt, cpoDtlBean.getFuncPrcListPrcAmt());
        setValue(cpoDtlTMsg.refCpoDtlLineNum, cpoDtlBean.getRefCpoDtlLineNum());
        setValue(cpoDtlTMsg.refCpoDtlLineSubNum, cpoDtlBean.getRefCpoDtlLineSubNum());
        setValue(cpoDtlTMsg.dplyLineRefNum, cpoDtlBean.getDplyLineRefNum());
        setValue(cpoDtlTMsg.crRebilCd, cpoDtlBean.getCrRebilCd());
        setValue(cpoDtlTMsg.prcBaseDt, cpoDtlBean.getPrcBaseDt());
        setValue(cpoDtlTMsg.splyNm, cpoDtlBean.getSplyNm());
        setValue(cpoDtlTMsg.splyTpCd, cpoDtlBean.getSplyTpCd());
        setValue(cpoDtlTMsg.splyFirstAddr, cpoDtlBean.getSplyFirstAddr());
        setValue(cpoDtlTMsg.splyCtyAddr, cpoDtlBean.getSplyCtyAddr());
        setValue(cpoDtlTMsg.splyStCd, cpoDtlBean.getSplyStCd());
        setValue(cpoDtlTMsg.splyPostCd, cpoDtlBean.getSplyPostCd());
        setValue(cpoDtlTMsg.csmpContrNum, cpoDtlBean.getCsmpContrNum());
        setValue(cpoDtlTMsg.dlrRefNum, cpoDtlBean.getDlrRefNum());
        setValue(cpoDtlTMsg.csmpPrcListCd, cpoDtlBean.getCsmpPrcListCd());
        setValue(cpoDtlTMsg.rntlTrmnDt, cpoDtlBean.getRntlTrmnDt());
        setValue(cpoDtlTMsg.bllgAttrbCustAcctCd, cpoDtlBean.getBllgAttrbCustAcctCd());
        setValue(cpoDtlTMsg.firstBllgAttrbNm, cpoDtlBean.getFirstBllgAttrbNm());
        setValue(cpoDtlTMsg.firstBllgAttrbValTxt, cpoDtlBean.getFirstBllgAttrbValTxt());
        setValue(cpoDtlTMsg.scdBllgAttrbNm, cpoDtlBean.getScdBllgAttrbNm());
        setValue(cpoDtlTMsg.scdBllgAttrbValTxt, cpoDtlBean.getScdBllgAttrbValTxt());
        setValue(cpoDtlTMsg.thirdBllgAttrbNm, cpoDtlBean.getThirdBllgAttrbNm());
        setValue(cpoDtlTMsg.thirdBllgAttrbValTxt, cpoDtlBean.getThirdBllgAttrbValTxt());
        setValue(cpoDtlTMsg.frthBllgAttrbNm, cpoDtlBean.getFrthBllgAttrbNm());
        setValue(cpoDtlTMsg.frthBllgAttrbValTxt, cpoDtlBean.getFrthBllgAttrbValTxt());
        setValue(cpoDtlTMsg.fifthBllgAttrbNm, cpoDtlBean.getFifthBllgAttrbNm());
        setValue(cpoDtlTMsg.fifthBllgAttrbValTxt, cpoDtlBean.getFifthBllgAttrbValTxt());
        setValue(cpoDtlTMsg.sixthBllgAttrbNm, cpoDtlBean.getSixthBllgAttrbNm());
        setValue(cpoDtlTMsg.sixthBllgAttrbValTxt, cpoDtlBean.getSixthBllgAttrbValTxt());
        setValue(cpoDtlTMsg.sbstMdseCd, cpoDtlBean.getSbstMdseCd());
        setValue(cpoDtlTMsg.ordSrcRefLineNum, cpoDtlBean.getOrdSrcRefLineNum());
        setValue(cpoDtlTMsg.ordSrcRefLineSubNum, cpoDtlBean.getOrdSrcRefLineSubNum());
        setValue(cpoDtlTMsg.carrSvcLvlCd, cpoDtlBean.getCarrSvcLvlCd());
        setValue(cpoDtlTMsg.supdLockFlg, cpoDtlBean.getSupdLockFlg());
        setValue(cpoDtlTMsg.prcListEquipConfigNum, cpoDtlBean.getPrcListEquipConfigNum());
        setValue(cpoDtlTMsg.loanBalQty, cpoDtlBean.getLoanBalQty());
        setValue(cpoDtlTMsg.funcSvcRevTrnsfAmt, cpoDtlBean.getFuncSvcRevTrnsfAmt());
        setValue(cpoDtlTMsg.dealSvcRevTrnsfAmt, cpoDtlBean.getDealSvcRevTrnsfAmt());
        setValue(cpoDtlTMsg.funcSvcCostTrnsfAmt, cpoDtlBean.getFuncSvcCostTrnsfAmt());
        setValue(cpoDtlTMsg.dealSvcCostTrnsfAmt, cpoDtlBean.getDealSvcCostTrnsfAmt());
        setValue(cpoDtlTMsg.funcManFlAdjAmt, cpoDtlBean.getFuncManFlAdjAmt());
        setValue(cpoDtlTMsg.dealManFlAdjAmt, cpoDtlBean.getDealManFlAdjAmt());
        setValue(cpoDtlTMsg.funcManRepRevAdjAmt, cpoDtlBean.getFuncManRepRevAdjAmt());
        setValue(cpoDtlTMsg.dealManRepRevAdjAmt, cpoDtlBean.getDealManRepRevAdjAmt());
        //setValue(cpoDtlTMsg.shopItemFlg, cpoDtlBean.getShopItemFlg());
        // QC#30855 2019/03/20 Add Start
        if (ZYPCommonFunc.hasValue(cpoDtlBean.getShopItemFlg())) {
            setValue(cpoDtlTMsg.shopItemFlg, cpoDtlBean.getShopItemFlg());
        } else {
            setValue(cpoDtlTMsg.shopItemFlg, ZYPConstant.FLG_OFF_N);
        }
        // QC#30855 2019/03/20 Add End
        setValue(cpoDtlTMsg.ordUpldRefNum, cpoDtlBean.getOrdUpldRefNum());
        setValue(cpoDtlTMsg.hardDriveEraseInclFlg, cpoDtlBean.getHardDriveEraseInclFlg());
        setValue(cpoDtlTMsg.hardDriveRmvInclFlg, cpoDtlBean.getHardDriveRmvInclFlg());
        // setValue(cpoDtlTMsg.convCompFlg,
        // cpoDtlBean.getConvCompFlg());
        setValue(cpoDtlTMsg.pickSvcConfigMstrPk, cpoDtlBean.getPickSvcConfigMstrPk());
        // 2015/08/27 CSA Add End

        setDefaultValues(cpoDtlTMsg);
        // 1.2WDS add end <--

        // 2016/09/05 S21_NA#6100 Add Start
        setValue(cpoDtlTMsg.dsCpoDtlCratUsrId, cpoDtlBean.getDsCpoDtlCratUsrId());
        setValue(cpoDtlTMsg.dsCpoDtlCratTs, cpoDtlBean.getDsCpoDtlCratTs());
        setValue(cpoDtlTMsg.dsCpoDtlUpdUsrId, cpoDtlBean.getDsCpoDtlUpdUsrId());
        setValue(cpoDtlTMsg.dsCpoDtlUpdTs, cpoDtlBean.getDsCpoDtlUpdTs());
        // 2016/09/05 S21_NA#6100 Add End
        setValue(cpoDtlTMsg.convProcStsCd, cpoDtlBean.getConvProcStsCd()); // QC#16425
        // 2016/12/14
        // Add

        // 2019/10/04 S21_NA#51372 Add Start
        setValue(cpoDtlTMsg.prntVndNm, cpoDtlBean.getPrntVndNm());
        // 2019/10/04 S21_NA#51372 Add End
    }

    private void setCpoDtlTMsgForCancel(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean, CPO_DTLTMsg cpoDtlMsg) {

        setValue(cpoDtlMsg.ordQty, cpoDtlBean.getOrdQty());
        // 2018/08/02 S21_NA#25404 Mod Start
//        setValue(cpoDtlMsg.cancQty, cpoDtlBean.getCancQty());
        setValue(cpoDtlMsg.cancQty, cpoDtlMsg.cancQty.getValue().add(cpoDtlBean.getCancQty()));
        // 2018/08/02 S21_NA#25404 Mod End
        setValue(cpoDtlMsg.ordCustUomQty, cpoDtlBean.getOrdCustUomQty());
        setValue(cpoDtlMsg.entDealNetUnitPrcAmt, cpoDtlBean.getEntDealNetUnitPrcAmt());
        setValue(cpoDtlMsg.entCpoDtlDealNetAmt, cpoDtlBean.getEntCpoDtlDealNetAmt());
        setValue(cpoDtlMsg.entCpoDtlDealSlsAmt, cpoDtlBean.getEntCpoDtlDealSlsAmt());
        setValue(cpoDtlMsg.entFuncNetUnitPrcAmt, cpoDtlBean.getEntFuncNetUnitPrcAmt());
        setValue(cpoDtlMsg.entCpoDtlFuncNetAmt, cpoDtlBean.getEntCpoDtlFuncNetAmt());
        setValue(cpoDtlMsg.entCpoDtlFuncSlsAmt, cpoDtlBean.getEntCpoDtlFuncSlsAmt());
        setValue(cpoDtlMsg.cpoDtlDealNetAmt, cpoDtlBean.getCpoDtlDealNetAmt());
        setValue(cpoDtlMsg.cpoDtlDealSlsAmt, cpoDtlBean.getCpoDtlDealSlsAmt());
        setValue(cpoDtlMsg.cpoDtlFuncNetAmt, cpoDtlBean.getCpoDtlFuncNetAmt());
        setValue(cpoDtlMsg.cpoDtlFuncSlsAmt, cpoDtlBean.getCpoDtlFuncSlsAmt());

        SHPG_PLNTMsgArray resShpgPlnTMsgArray = NWZC150001CpouUpdShpgPln.getShpgPlnTMsgArray(cpoBean.getGlblCmpyCd(), cpoDtlBean.getCpoOrdNum(), cpoDtlBean.getCpoDtlLineNum(), cpoDtlBean.getCpoDtlLineSubNum());

        BigDecimal canShipQty = BigDecimal.ZERO;

        for (int j = 0; j < resShpgPlnTMsgArray.getValidCount(); j++) {

            SHPG_PLNTMsg resShpgPlnTMsg = resShpgPlnTMsgArray.no(j);
            String shpgStsCd = resShpgPlnTMsg.shpgStsCd.getValue();

            // 2019/11/27 QC#52339 Mod Start 
            //if (NWZC150001CpouEditAmount.getInstance().canCancelShpgSts(resShpgPlnTMsg)) {
            if (NWZC150001CpouEditAmount.getInstance().canCancelShpgSts(resShpgPlnTMsg, false)) {
            // 2019/11/27 QC#52339 Mod End 

                if (SHPG_STS.INSHED.equals(shpgStsCd)) {
                    canShipQty = canShipQty.add(resShpgPlnTMsg.ordQty.getValue());
                }
            }
        }

        BigDecimal shipQty = cpoDtlMsg.shipQty.getValue().subtract(canShipQty);
        if (BigDecimal.ZERO.compareTo(shipQty) > 0) {
            cpoDtlMsg.shipQty.setValue(BigDecimal.ZERO);
        } else {
            cpoDtlMsg.shipQty.setValue(shipQty);
        }

        if (hasValue(cpoDtlBean.getOrdLineStsCd())) {
            cpoDtlMsg.ordLineStsCd.setValue(cpoDtlBean.getOrdLineStsCd());
        } else {
            if (BigDecimal.ZERO.compareTo(shipQty) == 0) {
                cpoDtlMsg.ordLineStsCd.setValue(ORD_LINE_STS.VALIDATED);
            } else {
                if (cpoDtlMsg.ordQty.getValue().compareTo(shipQty) == 0) {
                    cpoDtlMsg.ordLineStsCd.setValue(ORD_LINE_STS.SHIPPED);
                }
            }
        }

        if (ORD_LINE_STS.CANCELLED.equals(cpoDtlMsg.ordLineStsCd.getValue())) {
            cpoDtlMsg.cpoDtlCancDt.setValue(cpoBean.getSlsDt());
            cpoDtlMsg.cpoDtlCancFlg.setValue(ZYPConstant.FLG_ON_Y);
            cpoDtlMsg.openFlg.setValue(ZYPConstant.FLG_OFF_N);
            cpoDtlMsg.cpoDtlHldFlg.setValue(ZYPConstant.FLG_OFF_N);
            // 2017/06/16 S21_NA#19288 Add Start
            cpoDtlMsg.ordLineDplyStsCd.setValue(ORD_LINE_DPLY_STS.CANCELLED);
            // 2017/06/16 S21_NA#19288 Add End
        }

        setDefaultValues(cpoDtlMsg);
    }

    private static void setDefaultValues(CPO_DTLTMsg cpoDtlTMsg) {

        // QTY, AMT
        final Set<EZDTBigDecimalItem> amtItemList = new HashSet<EZDTBigDecimalItem>();
        amtItemList.add(cpoDtlTMsg.ordQty);
        amtItemList.add(cpoDtlTMsg.ordCustUomQty);
        amtItemList.add(cpoDtlTMsg.shipQty);
        amtItemList.add(cpoDtlTMsg.cancQty);
        amtItemList.add(cpoDtlTMsg.entDealNetUnitPrcAmt);
        amtItemList.add(cpoDtlTMsg.entCpoDtlDealNetAmt);
        amtItemList.add(cpoDtlTMsg.entCpoDtlDealSlsAmt);
        amtItemList.add(cpoDtlTMsg.entFuncNetUnitPrcAmt);
        amtItemList.add(cpoDtlTMsg.entCpoDtlFuncNetAmt);
        amtItemList.add(cpoDtlTMsg.entCpoDtlFuncSlsAmt);
        amtItemList.add(cpoDtlTMsg.cpoDtlDealNetAmt);
        amtItemList.add(cpoDtlTMsg.cpoDtlDealSlsAmt);
        amtItemList.add(cpoDtlTMsg.cpoDtlFuncNetAmt);
        amtItemList.add(cpoDtlTMsg.cpoDtlFuncSlsAmt);
        amtItemList.add(cpoDtlTMsg.exchRate);
        // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
        // amtItemList.add(cpoDtlTMsg.dslpDealNetUnitPrcAmt);
        // 2017/03/29 S21_NA#Review structure Lv.1 Del End

        for (EZDTBigDecimalItem amtItem : amtItemList) {
            if (!hasValue(amtItem)) {
                setValue(amtItem, ZERO);
            }
        }

        // FLG
        final Set<EZDTStringItem> flgItemList = new HashSet<EZDTStringItem>();
        flgItemList.add(cpoDtlTMsg.dropShipFlg);
        flgItemList.add(cpoDtlTMsg.taxFlg);
        flgItemList.add(cpoDtlTMsg.uomCpltFlg);
        flgItemList.add(cpoDtlTMsg.cpoDtlCancFlg);
        flgItemList.add(cpoDtlTMsg.cpoDtlHldFlg);
        flgItemList.add(cpoDtlTMsg.manPrcFlg);
        flgItemList.add(cpoDtlTMsg.submtFlg);
        flgItemList.add(cpoDtlTMsg.openFlg);
        flgItemList.add(cpoDtlTMsg.exptFlg);
        flgItemList.add(cpoDtlTMsg.setItemShipCpltFlg);
        flgItemList.add(cpoDtlTMsg.distItemFlg);
        // Def#1864
        flgItemList.add(cpoDtlTMsg.manPmtFlg);

        for (EZDTStringItem flgItem : flgItemList) {
            if (!hasValue(flgItem)) {
                setValue(flgItem, ZYPConstant.FLG_OFF_N);
            }
        }
    }

    // 20121129 M.Fuji WDS Solution#104,105 Pricing Start
    /**
     * ORD_PRC_CALC_BASE update
     * @param cpoBean NWZC150001CpouBean
     */
    public void updateOrdPrcCalcBase(NWZC150001CpouBean cpoBean, List<ORD_PRC_CALC_BASETMsg> insCalcBaseTMsgAry, List<ORD_PRC_CALC_BASETMsg> updCalcBaseTMsgAry) {
        final String rqstTpCd = cpoBean.getRqstTpCd();

        // delete save data
        if (NWZC150001CpouConstant.CPO_SAVE.equals(rqstTpCd) || (NWZC150001CpouConstant.CPO_SUBMIT.equals(rqstTpCd) && hasValue(cpoBean.getCpoOrdNum()))) {
            // For Performance QC#10321 Mod Start
            // final ORD_PRC_CALC_BASETMsg reqTMsg = new ORD_PRC_CALC_BASETMsg();
            // reqTMsg.setSQLID("002");
            // reqTMsg.setConditionValue("glblCmpyCd01", cpoBean.getGlblCmpyCd());
            // reqTMsg.setConditionValue("cpoOrdNum01", cpoBean.getCpoOrdNum());

            // final ORD_PRC_CALC_BASETMsgArray delOrdPrcCalcBaseTMsgArray = (ORD_PRC_CALC_BASETMsgArray) findByConditionForUpdate(reqTMsg);
            // for (int i = 0; i < delOrdPrcCalcBaseTMsgArray.getValidCount(); i++) {
            //     final ORD_PRC_CALC_BASETMsg delOrdPrcCalcBaseTMsg = delOrdPrcCalcBaseTMsgArray.no(i);

            //     // ***** [removeByPartialKey]
            //     removeByPartialKey(delOrdPrcCalcBaseTMsg);
            // }

            ORD_PRC_CALC_BASETMsg calcBaseTMsg = new ORD_PRC_CALC_BASETMsg();
            ZYPEZDItemValueSetter.setValue(calcBaseTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(calcBaseTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
            S21FastTBLAccessor.removeByPartialValue(calcBaseTMsg, new String[] {"glblCmpyCd", "cpoOrdNum" });
            // For Performance QC#10321 Mod End
        }

        // For Performance QC#10321 Add Start
        List<ORD_PRC_CALC_BASETMsg> delCalcBaseTMsgAry = new ArrayList<ORD_PRC_CALC_BASETMsg>();
        // For Performance QC#10321 Add End

        for (NWZC150001CpouDetailBean cpoDtlBean : cpoBean.getDtlBeanList()) {

            final String glblCmpyCd = cpoBean.getGlblCmpyCd();
            final String cpoOrdNum = cpoBean.getCpoOrdNum();
            final String cpoDtlLineNum = cpoDtlBean.getCpoDtlLineNum();
            final String cpoDtlLineSubNum = cpoDtlBean.getCpoDtlLineSubNum();
            final String dtlRqstTpCd = cpoDtlBean.getDtlRqstTpCd();

            List<NWZC150001_priceListPMsg> linePriceList = NWZC150001CpouEditAmount.getLinePriceList(cpoBean, cpoDtlLineNum, cpoDtlLineSubNum);

            // --------------------------------------------------
            // SAVE, SUBMIT, MODIFY(Add)
            // --------------------------------------------------
            if (NWZC150001CpouConstant.CPO_SAVE.equals(rqstTpCd) || NWZC150001CpouConstant.CPO_SUBMIT.equals(rqstTpCd) || (NWZC150001CpouConstant.CPO_MODIFY.equals(rqstTpCd) && NWZC150001CpouConstant.CPO_DTL_SUBMIT.equals(dtlRqstTpCd))) {

                if (NWZC150001CpouConstant.CPO_DTL_CANCEL.equals(dtlRqstTpCd)) {
                    continue;
                }

                // create insert Data
                for (NWZC150001_priceListPMsg priceData : linePriceList) {

                    ORD_PRC_CALC_BASETMsg createPriceTMsg = new ORD_PRC_CALC_BASETMsg();

                    // 2016/02/04 S21_NA#3255 Mod Start
                    // setOrdPrcCalcBaseTMsgForInsert(cpoBean, priceData, createPriceTMsg);
                    setOrdPrcCalcBaseTMsgForInsert(cpoBean, cpoDtlBean, priceData, createPriceTMsg);
                    // 2016/02/04 S21_NA#3255 Mod End

                    // For Performance QC#10321 Mod Start
                    // insert(createPriceTMsg);

                    // if (!RTNCD_NORMAL.equals(createPriceTMsg.getReturnCode())) {
                    //     throw new S21AbendException("updateOrdPrcCalcBase ordPrcCalcBasePk = " + createPriceTMsg.ordPrcCalcBasePk.getValue().toString() + ": Insert Error");
                    // }
                    insCalcBaseTMsgAry.add(createPriceTMsg);
                    // For Performance QC#10321 Mod End
                }

                // --------------------------------------------------
                // MODIFY
                // --------------------------------------------------
            } else if (NWZC150001CpouConstant.CPO_MODIFY.equals(rqstTpCd)) {

                // get and lock Original Data
                final ORD_PRC_CALC_BASETMsg reqTMsg = new ORD_PRC_CALC_BASETMsg();
                reqTMsg.setSQLID("001");
                reqTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                reqTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
                reqTMsg.setConditionValue("cpoDtlLineNum01", cpoDtlLineNum);
                reqTMsg.setConditionValue("cpoDtlLineSubNum01", cpoDtlLineSubNum);

                final ORD_PRC_CALC_BASETMsgArray ordPrcCalcBaseTMsgArray = (ORD_PRC_CALC_BASETMsgArray) findByConditionForUpdate(reqTMsg);

                // delete
                if (ordPrcCalcBaseTMsgArray != null) {
                    for (int i = 0; i < ordPrcCalcBaseTMsgArray.getValidCount(); i++) {
                        final ORD_PRC_CALC_BASETMsg ordPrcCalcBaseTMsg = ordPrcCalcBaseTMsgArray.no(i);

                        if (isRemovePriceTMsg(cpoBean, linePriceList, ordPrcCalcBaseTMsg)) {
                            // For Performance QC#10321 Mod Start
                            // logicalRemove(ordPrcCalcBaseTMsg);
                            delCalcBaseTMsgAry.add(ordPrcCalcBaseTMsg);
                            // For Performance QC#10321 Mod End
                        }
                    }
                }

                // check insert/update Data
                for (NWZC150001_priceListPMsg priceData : linePriceList) {

                    ORD_PRC_CALC_BASETMsg updatePriceTMsg = new ORD_PRC_CALC_BASETMsg();

                    if (ordPrcCalcBaseTMsgArray != null) {
                        updatePriceTMsg = getUpdateOrdPrcCalcBase(cpoBean, ordPrcCalcBaseTMsgArray, priceData);
                    }
                    // insert
                    if (updatePriceTMsg == null) {

                        ORD_PRC_CALC_BASETMsg createPriceTMsg = new ORD_PRC_CALC_BASETMsg();

                        // 2016/02/04 S21_NA#3255 Mod Start
//                        setOrdPrcCalcBaseTMsgForInsert(cpoBean, priceData, createPriceTMsg);
                        setOrdPrcCalcBaseTMsgForInsert(cpoBean, cpoDtlBean, priceData, createPriceTMsg);
                        // 2016/02/04 S21_NA#3255 Mod End

                        // For Performance QC#10321 Mod Start
                        // insert(createPriceTMsg);

                        // if (!RTNCD_NORMAL.equals(createPriceTMsg.getReturnCode())) {
                        //     throw new S21AbendException("updateOrdPrcCalcBase ordPrcCalcBasePk = " + createPriceTMsg.ordPrcCalcBasePk.getValue().toString() + ": Insert Error");
                        // }
                        insCalcBaseTMsgAry.add(createPriceTMsg);
                        // For Performance QC#10321 Mod End

                    // update
                    } else {

                        setOrdPrcCalcBaseTMsgForUpdate(cpoBean, priceData, updatePriceTMsg);

                        // For Performance QC#10321 Mod Start
                        // update(updatePriceTMsg);

                        // if (!RTNCD_NORMAL.equals(updatePriceTMsg.getReturnCode())) {
                        //     throw new S21AbendException("updateOrdPrcCalcBase ordPrcCalcBasePk = " + updatePriceTMsg.ordPrcCalcBasePk.getValue().toString() + ": Insert Error");
                        // }
                        updCalcBaseTMsgAry.add(updatePriceTMsg);
                        // For Performance QC#10321 Mod End
                    }
                }
            }
        }

        // For Performance QC#10321 Add Start
        if (!insCalcBaseTMsgAry.isEmpty()) {
            int cnt = S21FastTBLAccessor.insert(insCalcBaseTMsgAry.toArray(new ORD_PRC_CALC_BASETMsg[0]));
            if (cnt != insCalcBaseTMsgAry.size()) {
                throw new S21AbendException("updateOrdPrcCalcBase : ORD_PRC_CALC_BASE Insert Error");
            }
        }

        if (!delCalcBaseTMsgAry.isEmpty()) {
            int cnt = S21FastTBLAccessor.removeLogical(delCalcBaseTMsgAry.toArray(new ORD_PRC_CALC_BASETMsg[0]));
            if (cnt != delCalcBaseTMsgAry.size()) {
                throw new S21AbendException("updateOrdPrcCalcBase : ORD_PRC_CALC_BASE Delete Error");
            }
        }

        if (!updCalcBaseTMsgAry.isEmpty()) {
            int cnt = S21FastTBLAccessor.update(updCalcBaseTMsgAry.toArray(new ORD_PRC_CALC_BASETMsg[0]));
            if (cnt != updCalcBaseTMsgAry.size()) {
                throw new S21AbendException("updateOrdPrcCalcBase : ORD_PRC_CALC_BASE Update Error");
            }
        }
        // For Performance QC#10321 Add End
    }

    // 20121129 M.Fuji WDS Solution#104,105 Pricing Start
    // 2016/02/04 S21_NA#3255 Mod Interface.
//    private static void setOrdPrcCalcBaseTMsgForInsert(NWZC150001CpouBean cpoBean, NWZC150001_priceListPMsg priceData, ORD_PRC_CALC_BASETMsg ordPrcCalcBaseTMsg) {
    private static void setOrdPrcCalcBaseTMsgForInsert(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean, NWZC150001_priceListPMsg priceData, ORD_PRC_CALC_BASETMsg ordPrcCalcBaseTMsg) {

        ordPrcCalcBaseTMsg.glblCmpyCd.setValue(cpoBean.getGlblCmpyCd());
        ordPrcCalcBaseTMsg.ordPrcCalcBasePk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_PRC_CALC_BASE_SQ));
        ordPrcCalcBaseTMsg.cpoOrdNum.setValue(NWZC150001Common.getCpoOrdNumFromBean(cpoBean));
        // 2016/02/04 S21_NA#3255 Mod Start
//        ordPrcCalcBaseTMsg.cpoDtlLineNum.setValue(priceData.cpoDtlLineNum.getValue());
//        ordPrcCalcBaseTMsg.cpoDtlLineSubNum.setValue(priceData.cpoDtlLineSubNum.getValue());
        if (ZYPCommonFunc.hasValue(cpoDtlBean.getReNumCpoDtlLineNum()) && !cpoDtlBean.getReNumCpoDtlLineNum().equals(priceData.cpoDtlLineNum.getValue())) {
            ordPrcCalcBaseTMsg.cpoDtlLineNum.setValue(cpoDtlBean.getReNumCpoDtlLineNum());
        } else {
            ordPrcCalcBaseTMsg.cpoDtlLineNum.setValue(priceData.cpoDtlLineNum.getValue());
        }
        ordPrcCalcBaseTMsg.cpoDtlLineSubNum.setValue(priceData.cpoDtlLineSubNum.getValue());
        // 2016/02/04 S21_NA#3255 Mod Start
        ordPrcCalcBaseTMsg.prcCondTpCd.setValue(priceData.prcCondTpCd.getValue());
        ordPrcCalcBaseTMsg.prcDtlGrpCd.setValue(priceData.prcDtlGrpCd.getValue());
        ordPrcCalcBaseTMsg.prcJrnlGrpCd.setValue(priceData.prcJrnlGrpCd.getValue());
        ordPrcCalcBaseTMsg.prcCondManEntryFlg.setValue(priceData.prcCondManEntryFlg.getValue());
        ordPrcCalcBaseTMsg.prcCondManAddFlg.setValue(priceData.prcCondManAddFlg.getValue());
        ordPrcCalcBaseTMsg.prcCondManDelFlg.setValue(priceData.prcCondManDelFlg.getValue());
        ordPrcCalcBaseTMsg.pkgUomCd.setValue(priceData.pkgUomCd.getValue());
        ordPrcCalcBaseTMsg.prcCondUnitCd.setValue(priceData.prcCondUnitCd.getValue());
        ordPrcCalcBaseTMsg.prcCalcMethCd.setValue(priceData.prcCalcMethCd.getValue());
        ordPrcCalcBaseTMsg.autoPrcAmtRate.setValue(priceData.autoPrcAmtRate.getValue());
        ordPrcCalcBaseTMsg.maxPrcAmtRate.setValue(priceData.maxPrcAmtRate.getValue());
        ordPrcCalcBaseTMsg.minPrcAmtRate.setValue(priceData.minPrcAmtRate.getValue());
        ordPrcCalcBaseTMsg.manPrcAmtRate.setValue(priceData.manPrcAmtRate.getValue());
        ordPrcCalcBaseTMsg.calcPrcAmtRate.setValue(priceData.calcPrcAmtRate.getValue());
        ordPrcCalcBaseTMsg.unitPrcAmt.setValue(priceData.unitPrcAmt.getValue());
        ordPrcCalcBaseTMsg.dsMdsePrcPk.setValue(priceData.dsMdsePrcPk.getValue());
        ordPrcCalcBaseTMsg.specCondPrcPk.setValue(priceData.specCondPrcPk.getValue());
        ordPrcCalcBaseTMsg.frtPerWtPk.setValue(priceData.frtPerWtPk.getValue());
        // START ADD S.Yamamoto [OM003]
        setValue(ordPrcCalcBaseTMsg.coaAcctCd, NWZC150001Common.getCoaAcctCdForPrc(cpoBean, priceData));
        ordPrcCalcBaseTMsg.autoPrcCcyCd.setValue(priceData.autoPrcCcyCd.getValue());
        // END   ADD S.Yamamoto [OM003]
        // QC#9700 2018/09/03 Add Start
        if (ZYPCommonFunc.hasValue(priceData.prcRuleApplyFlg)) {
            ordPrcCalcBaseTMsg.prcRuleApplyFlg.setValue(priceData.prcRuleApplyFlg.getValue());
        } else {
            ordPrcCalcBaseTMsg.prcRuleApplyFlg.setValue(ZYPConstant.FLG_ON_Y);
        }
        ordPrcCalcBaseTMsg.prcRulePrcdPk.setValue(priceData.prcRulePrcdPk.getValue());
        // QC#9700 2018/09/03 Add End
        setDefaultValues(ordPrcCalcBaseTMsg);
    }

    private static void setOrdPrcCalcBaseTMsgForUpdate(NWZC150001CpouBean cpoBean, NWZC150001_priceListPMsg priceData, ORD_PRC_CALC_BASETMsg ordPrcCalcBaseTMsg) {

        ordPrcCalcBaseTMsg.prcCondTpCd.setValue(priceData.prcCondTpCd.getValue());
        ordPrcCalcBaseTMsg.prcDtlGrpCd.setValue(priceData.prcDtlGrpCd.getValue());
        ordPrcCalcBaseTMsg.prcJrnlGrpCd.setValue(priceData.prcJrnlGrpCd.getValue());
        ordPrcCalcBaseTMsg.prcCondManEntryFlg.setValue(priceData.prcCondManEntryFlg.getValue());
        ordPrcCalcBaseTMsg.prcCondManAddFlg.setValue(priceData.prcCondManAddFlg.getValue());
        ordPrcCalcBaseTMsg.prcCondManDelFlg.setValue(priceData.prcCondManDelFlg.getValue());
        ordPrcCalcBaseTMsg.pkgUomCd.setValue(priceData.pkgUomCd.getValue());
        ordPrcCalcBaseTMsg.prcCondUnitCd.setValue(priceData.prcCondUnitCd.getValue());
        ordPrcCalcBaseTMsg.prcCalcMethCd.setValue(priceData.prcCalcMethCd.getValue());
        ordPrcCalcBaseTMsg.autoPrcAmtRate.setValue(priceData.autoPrcAmtRate.getValue());
        ordPrcCalcBaseTMsg.maxPrcAmtRate.setValue(priceData.maxPrcAmtRate.getValue());
        ordPrcCalcBaseTMsg.minPrcAmtRate.setValue(priceData.minPrcAmtRate.getValue());
        ordPrcCalcBaseTMsg.manPrcAmtRate.setValue(priceData.manPrcAmtRate.getValue());
        ordPrcCalcBaseTMsg.calcPrcAmtRate.setValue(priceData.calcPrcAmtRate.getValue());
        ordPrcCalcBaseTMsg.unitPrcAmt.setValue(priceData.unitPrcAmt.getValue());
        ordPrcCalcBaseTMsg.dsMdsePrcPk.setValue(priceData.dsMdsePrcPk.getValue());
        ordPrcCalcBaseTMsg.specCondPrcPk.setValue(priceData.specCondPrcPk.getValue());
        ordPrcCalcBaseTMsg.frtPerWtPk.setValue(priceData.frtPerWtPk.getValue());
        // START ADD S.Yamamoto [OM003]
        setValue(ordPrcCalcBaseTMsg.coaAcctCd, NWZC150001Common.getCoaAcctCdForPrc(cpoBean, priceData));
        ordPrcCalcBaseTMsg.autoPrcCcyCd.setValue(priceData.autoPrcCcyCd.getValue());
        // END   ADD S.Yamamoto [OM003]
        // QC#9700 2018/09/03 Add Start
        if (ZYPCommonFunc.hasValue(priceData.prcRuleApplyFlg)) {
            ordPrcCalcBaseTMsg.prcRuleApplyFlg.setValue(priceData.prcRuleApplyFlg.getValue());
        } else {
            ordPrcCalcBaseTMsg.prcRuleApplyFlg.setValue(ZYPConstant.FLG_ON_Y);
        }
        ordPrcCalcBaseTMsg.prcRulePrcdPk.setValue(priceData.prcRulePrcdPk.getValue());
        // QC#9700  2018/09/03 Add End
    }


    private boolean isRemovePriceTMsg(NWZC150001CpouBean cpoBean, List<NWZC150001_priceListPMsg> linePriceList, ORD_PRC_CALC_BASETMsg reqTMsg) {

        for (NWZC150001_priceListPMsg priceData : linePriceList) {
            if (isEqualsPriceKey(cpoBean, priceData, reqTMsg)) {
                return false;
            }
        }
        return true;
    }

    private boolean isEqualsPriceKey(NWZC150001CpouBean cpoBean, NWZC150001_priceListPMsg priceData, ORD_PRC_CALC_BASETMsg reqTMsg) {

        if (!(cpoBean.getGlblCmpyCd().equals(reqTMsg.glblCmpyCd.getValue()))) {
            return false;
        }

        if (!(cpoBean.getCpoOrdNum().equals(reqTMsg.cpoOrdNum.getValue()))) {
            return false;
        }

        if (!(priceData.cpoDtlLineNum.getValue().equals(reqTMsg.cpoDtlLineNum.getValue()))) {
            return false;
        }

        if (!(priceData.cpoDtlLineSubNum.getValue().equals(reqTMsg.cpoDtlLineSubNum.getValue()))) {
            return false;
        }

        // 2015/12/01 S21_NA#1288 Upd Start
        // 20150914 mod start
        //        if (!(priceData.prcCondTpCd.getValue().equals(reqTMsg.prcCondTpCd.getValue()))) {
        //            return false;
        //        }
        // if (!ZYPCommonFunc.hasValue(priceData.specCondPrcPk)) {
        //     return false;
        // }
        // if (priceData.specCondPrcPk.getValue().compareTo(reqTMsg.specCondPrcPk.getValue()) != 0) {
        //     return false;
        // }
        // 20150914 mod end
        if (!(priceData.prcDtlGrpCd.getValue().equals(reqTMsg.prcDtlGrpCd.getValue()))) {
            return false;
        }
        boolean isPriceDataSpecCondPrcEmpty = ZYPCommonFunc.hasValue(priceData.specCondPrcPk);
        boolean isReqDataSpecCondPrcEmpty =  ZYPCommonFunc.hasValue(reqTMsg.specCondPrcPk);
        if (isPriceDataSpecCondPrcEmpty && isReqDataSpecCondPrcEmpty) {
            if (priceData.specCondPrcPk.getValue().compareTo(reqTMsg.specCondPrcPk.getValue()) != 0) {
                return false;
            }
        }
        if (!isPriceDataSpecCondPrcEmpty && isReqDataSpecCondPrcEmpty) {
            return false;
        }
        if (isPriceDataSpecCondPrcEmpty && !isReqDataSpecCondPrcEmpty) {
            return false;
        }
        // 2015/12/01 S21_NA#1288 Add End

        return true;
    }

    private ORD_PRC_CALC_BASETMsg getUpdateOrdPrcCalcBase(NWZC150001CpouBean cpoBean, ORD_PRC_CALC_BASETMsgArray ordPrcCalcBaseTMsgArray, NWZC150001_priceListPMsg priceData) {

        for (int i = 0; i < ordPrcCalcBaseTMsgArray.getValidCount(); i++) {
            final ORD_PRC_CALC_BASETMsg ordPrcCalcBaseTMsg = ordPrcCalcBaseTMsgArray.no(i);

            if (isEqualsPriceKey(cpoBean, priceData, ordPrcCalcBaseTMsg)) {
                return ordPrcCalcBaseTMsg;
            }
        }
        return null;
    }
    // 20121129 M.Fuji WDS Solution#104,105 Pricing End
    // 20121129 M.Fuji WDS Solution#104,105 Pricing Start
    private static void setDefaultValues(ORD_PRC_CALC_BASETMsg ordPrcCalcBaseTMsg) {
        // FLG
        Set<EZDTStringItem> flgItemList = new HashSet<EZDTStringItem>();
        flgItemList.add(ordPrcCalcBaseTMsg.prcCondManEntryFlg);
        flgItemList.add(ordPrcCalcBaseTMsg.prcCondManAddFlg);
        flgItemList.add(ordPrcCalcBaseTMsg.prcCondManDelFlg);
        flgItemList.add(ordPrcCalcBaseTMsg.prcRuleApplyFlg); // QC#9700  2018/09/03 Add

        for (EZDTStringItem flgItem : flgItemList) {
            if (!hasValue(flgItem)) {
                setValue(flgItem, ZYPConstant.FLG_OFF_N);
            }
        }
    }
    // 20121129 M.Fuji WDS Solution#104,105 Pricing End
}
