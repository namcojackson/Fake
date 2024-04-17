/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0720.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static business.blap.NSAL0720.constant.NSAL0720Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.api.NSZ.NSZC046001.NSZC046001;
import com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import business.blap.NSAL0720.NSAL0720Query;
import business.blap.NSAL0720.NSAL0720CMsg;
import business.blap.NSAL0720.NSAL0720SMsg;
import business.blap.NSAL0720.NSAL0720_ACMsg;
import business.blap.NSAL0720.NSAL0720_ACMsgArray;
import business.blap.NSAL0720.NSAL0720_ASMsg;
import business.db.BLLG_CYCLETMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTR_STS_VTMsg;
import business.db.DS_CONTR_BLLG_MTR_STS_VTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;
import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxDsContrBllgMtrListPMsg;
import business.parts.NSZC046001_xxDsContrCrCardPoListPMsg;
import business.parts.NSZC046001_xxSvcMemoListPMsg;
import business.parts.NSZC047099PMsg;

import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

import business.parts.NSZC046001_xxContrDtlListPMsg;

/**
 *<pre>
 * Update Bill To
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Hitachi         K.Kasai         Create          N/A
 * 2015/12/08   Hitachi         T.Tsuchida      Update          QC#1606
 * 2015/12/16   Hitachi         T.Tsuchida      Update          QC#1659
 * 2016/02/29   Hitachi         K.Kasai         Update          QC#2684
 * 2016/05/23   Hitachi         M.Gotou         Update          QC#5922
 * 2016/05/24   Hitachi         M.Gotou         Update          QC#7557
 * 2016/08/26   Hitachi         A.Kohinata      Update          QC#5922
 * 2016/11/11   Hitachi         T.Mizuki        Update          QC#4210
 * 2019/11/25   Hitachi         E.Kameishi      Update          QC#53071
 * 2020/06/05   Hitachi         K.Kitachi       Update          QC#56086
 * 2021/10/01   CITS            T.Wada          Update          QC#59240
 * 2022/03/16   CITS            R.Cabral        Update          QC#59748
 *</pre>
 */
public class NSAL0720CommonLogic {
    /**
     * Create Pull Down
     * @param cMsg NSAL0720CMsg
     */
    public static void createPullDown(NSAL0720CMsg cMsg) {
        createSvcMemoRsnPullDown(cMsg);
    }

    private static void createSvcMemoRsnPullDown(NSAL0720CMsg cMsg) {
        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(cMsg.glblCmpyCd.getValue(), SVC_MEMO_TP.UPDATE_BILL_TO);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnNm");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_H1, cMsg.svcMemoRsnNm_H2);
    }

    private static SVC_MEMO_RSNTMsgArray getSvcMemoRsnPulldownList(String glblCmpyCd, String svcMemoTpCd) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMemoTpCd01", svcMemoTpCd);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * @param cMsg NSAL0720CMsg
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> getDsContrDtl(NSAL0720CMsg cMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        S21SsmEZDResult ssmResult = NSAL0720Query.getInstance().getDsContrDtlPk(cMsg.glblCmpyCd.getValue(), dsContrPk, dsContrDtlPk);
        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    // START 2022/03/16 R.Cabral [QC#59748, ADD]
    /**
     * @param cMsg NSAL0720CMsg
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> getDsContrDtlAgg(NSAL0720CMsg cMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        S21SsmEZDResult ssmResult = NSAL0720Query.getInstance().getDsContrDtlPkAgg(cMsg.glblCmpyCd.getValue(), dsContrPk, dsContrDtlPk);
        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }
    // END   2022/03/16 R.Cabral [QC#59748, ADD]

    /**
     * @param cMsg NSAL0700CMsg
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_BLLG_MTR_STS_VTMsgArray
     */
    private static DS_CONTR_BLLG_MTR_STS_VTMsgArray getBllgMtrStsV(NSAL0720CMsg cMsg, BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTR_STS_VTMsg inBllgMtrStsVTMsg = setParamForBllgMtr(cMsg, dsContrDtlPk);
        DS_CONTR_BLLG_MTR_STS_VTMsgArray outBllgMtrStsVTMsgArray = (DS_CONTR_BLLG_MTR_STS_VTMsgArray) EZDTBLAccessor.findByCondition(inBllgMtrStsVTMsg);
        return outBllgMtrStsVTMsgArray;
    }

    /**
     * @param cMsg
     * @param outDtlTMsg
     * @return
     */
    private static DS_CONTR_BLLG_MTR_STS_VTMsg setParamForBllgMtr(NSAL0720CMsg cMsg, BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTR_STS_VTMsg inBllgMtrStsVTMsg = new DS_CONTR_BLLG_MTR_STS_VTMsg();
        inBllgMtrStsVTMsg.setSQLID("004");
        inBllgMtrStsVTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inBllgMtrStsVTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inBllgMtrStsVTMsg.setConditionValue("dsContrCtrlStsCd01", DS_CONTR_CTRL_STS.EXPIRED);
        inBllgMtrStsVTMsg.setConditionValue("dsContrCtrlStsCd02", DS_CONTR_CTRL_STS.CANCELLED);
        inBllgMtrStsVTMsg.setConditionValue("dsContrCtrlStsCd03", DS_CONTR_CTRL_STS.TERMINATED);
        inBllgMtrStsVTMsg.setConditionValue("dsContrCtrlStsCd04", DS_CONTR_CTRL_STS.TERMINATED_HOLD);
        inBllgMtrStsVTMsg.setConditionValue("dsContrCtrlStsCd05", DS_CONTR_CTRL_STS.EXPIRED_HOLD);
        return inBllgMtrStsVTMsg;
    }

    // mod start 2016/11/08 CSA QC#4210
    /**
     * update Bill To
     * @param cMsg NSAL0720CMsg
     */
    public static void updateBillTo(NSAL0720SMsg sMsg, NSAL0720CMsg cMsg) {
        // Update Process
        NSAL0720_ASMsg aSMsgIn = new NSAL0720_ASMsg();
        BigDecimal preContrPk = BigDecimal.ZERO;
        BigDecimal curContrPk = BigDecimal.ZERO;
        BigDecimal preContrDtlPk = BigDecimal.ZERO;
        BigDecimal curContrDtlPk = BigDecimal.ZERO;
        boolean callApiFlg = false;
        boolean callDtlApiFlg = false;
        boolean errFlg = false;

        // START 2022/03/16 R.Cabral [QC#59748, ADD]
        boolean aggContrLvlFlg = false;
        // END   2022/03/16 R.Cabral [QC#59748, ADD]

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSZC046001PMsg inPMsg = new NSZC046001PMsg();
            inPMsg.clear();
            aSMsgIn = sMsg.A.no(i);
            curContrPk = aSMsgIn.dsContrPk_A1.getValue();
            // add start 2016/05/24 CSA Defect#7557
            curContrDtlPk = aSMsgIn.dsContrDtlPk_A1.getValue();
            // add end 2016/05/24 CSA Defect#7557
            if (preContrPk.compareTo(curContrPk) != 0) {
                callApiFlg = false;
                // START 2022/03/16 R.Cabral [QC#59748, ADD]
                aggContrLvlFlg = false;
                // END   2022/03/16 R.Cabral [QC#59748, ADD]
            }
            if (preContrDtlPk.compareTo(curContrDtlPk) != 0) {
                callDtlApiFlg = false;
            }

            // START 2022/03/16 R.Cabral [QC#59748, ADD]
            if (preContrPk.compareTo(curContrPk) != 0) {
                aggContrLvlFlg = false;
            }

            if (aSMsgIn.dsContrCatgCd_A1.getValue().equals(DS_CONTR_CATG.AGGREGATE)) {
                if (FLG_ON_Y.equals(aSMsgIn.xxChkBox_A1.getValue()) && BigDecimal.ZERO.compareTo(aSMsgIn.dsContrDtlPk_A1.getValue()) == 0) {
                    aggContrLvlFlg = true;
                }
            }

            boolean machinesNotProcessedForAggContrLvlFlg = aggContrLvlFlg && !callDtlApiFlg;
            // END   2022/03/16 R.Cabral [QC#59748, ADD]

            if (FLG_ON_Y.equals(aSMsgIn.xxChkBox_A1.getValue()) && BigDecimal.ZERO.compareTo(aSMsgIn.dsContrDtlPk_A1.getValue()) == 0) {
                // set Header Parameter
                setHdrParam(cMsg, aSMsgIn, inPMsg);
                DS_CONTRTMsg inHdrTMsg = new DS_CONTRTMsg();
                setValue(inHdrTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                setValue(inHdrTMsg.dsContrPk, aSMsgIn.dsContrPk_A1.getValue());
                DS_CONTRTMsg outHdrTMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(inHdrTMsg);
                if (outHdrTMsg == null) {
                    setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                    errFlg = true;
                    continue;
                }
                if (!isSameTimeDsContrHdr(outHdrTMsg, aSMsgIn, cMsg)) {
                    setValue(aSMsgIn.xxGenlFldAreaTxt_A1, getRtnMsg(ZZZM9004E));
                    errFlg = true;
                    continue;
                }

                // set Detail Parameter
                // START 2022/03/16 R.Cabral [QC#59748, MOD]
//                List<Map<String, Object>> dsContrDtlPkList = getDsContrDtl(cMsg, aSMsgIn.dsContrPk_A1.getValue(), null);
                List<Map<String, Object>> dsContrDtlPkList = null;
                if (aggContrLvlFlg) {
                    dsContrDtlPkList = getDsContrDtlAgg(cMsg, aSMsgIn.dsContrPk_A1.getValue(), null);
                } else {
                    dsContrDtlPkList = getDsContrDtl(cMsg, aSMsgIn.dsContrPk_A1.getValue(), null);
                }
                // END   2022/03/16 R.Cabral [QC#59748, MOD]
                if (dsContrDtlPkList != null && !setDtlParamList(cMsg, aSMsgIn, dsContrDtlPkList, inPMsg, sMsg)) {
                    setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                    errFlg = true;
                    continue;
                }

                // set Overage Parameter
                if (dsContrDtlPkList != null && !setBllgMtrParamList(cMsg, aSMsgIn, dsContrDtlPkList, inPMsg, sMsg)) {
                    setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                    errFlg = true;
                    continue;
                }

                // call api
                // mod start 2016/05/24 CSA Defect#7557
                if (!executeApi(inPMsg, sMsg, i)) {
                // mod end 2016/05/24 CSA Defect#7557
                    errFlg = true;
                    continue;
                }
                inPMsg.clear();
                callApiFlg = true;
            } else if (!callApiFlg && FLG_ON_Y.equals(aSMsgIn.xxChkBox_A1.getValue()) && BigDecimal.ZERO.compareTo(aSMsgIn.dsContrDtlPk_A1.getValue()) != 0) {
                // set Header Parameter
                setHdrParamForDtlAndMtr(cMsg, aSMsgIn, inPMsg);
                // set Overage Parameter
                List<Map<String, Object>> dsContrDtlPkList = getDsContrDtl(cMsg, aSMsgIn.dsContrPk_A1.getValue(), aSMsgIn.dsContrDtlPk_A1.getValue());

                if (dsContrDtlPkList != null && !setDtlParamList(cMsg, aSMsgIn, dsContrDtlPkList, inPMsg, sMsg)) {
                    setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                    errFlg = true;
                    continue;
                }

                if (dsContrDtlPkList != null && !setBllgMtrParamList(cMsg, aSMsgIn, dsContrDtlPkList, inPMsg, sMsg)) {
                    setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                    errFlg = true;
                    continue;
                }
                // call api
                // mod start 2016/05/24 CSA Defect#7557
                if (!executeApi(inPMsg, sMsg, i)) {
                // mod end 2016/05/24 CSA Defect#7557
                    errFlg = true;
                    continue;
                }
                inPMsg.clear();
                callDtlApiFlg = true;
            // START 2022/03/16 R.Cabral [QC#59748, ADD]
            } else if (machinesNotProcessedForAggContrLvlFlg && BigDecimal.ZERO.compareTo(aSMsgIn.dsContrDtlPk_A1.getValue()) != 0 && ZYPCommonFunc.hasValue(aSMsgIn.mtrLbDescTxt_A1.getValue())
                && !DS_CONTR_DTL_TP.AGGREGATE.equals(aSMsgIn.dsContrDtlTpCd_A1.getValue())) {
                // set Header Parameter
                setHdrParamForDtlAndMtr(cMsg, aSMsgIn, inPMsg);
                //base level
                // mod start 2016/02/29 CSA Defect#2684
                if (MTR_LB_NM.equals(aSMsgIn.mtrLbDescTxt_A1.getValue())) {
                // mod end 2016/02/29 CSA Defect#2684
                    //set Detail Parameter
                    List<Map<String, Object>> dsContrDtlPkList = getDsContrDtl(cMsg, aSMsgIn.dsContrPk_A1.getValue(), aSMsgIn.dsContrDtlPk_A1.getValue());
                    if (dsContrDtlPkList != null && !setDtlParamList(cMsg, aSMsgIn, dsContrDtlPkList, inPMsg, sMsg)) {
                        setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                        errFlg = true;
                        continue;
                    }
                    // call api
                    // mod start 2016/05/24 CSA Defect#7557
                    if (!executeApi(inPMsg, sMsg, i)) {
                    // mod end 2016/05/24 CSA Defect#7557
                        errFlg = true;
                        continue;
                    }
                    inPMsg.clear();
                } else {
                    // set Overage Parameter
                    List<Map<String, Object>> dsContrDtlPkList = getDsContrDtl(cMsg, aSMsgIn.dsContrPk_A1.getValue(), aSMsgIn.dsContrDtlPk_A1.getValue());
                    if (dsContrDtlPkList != null && !setDtlParamListForMtr(cMsg, aSMsgIn, dsContrDtlPkList, inPMsg)) {
                        setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                        errFlg = true;
                        continue;
                    }
                    if (dsContrDtlPkList != null && !setBllgMtrParamListForMtr(cMsg, aSMsgIn, dsContrDtlPkList, inPMsg)) {
                        setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                        errFlg = true;
                        continue;
                    }
                    // call api
                    // mod start 2016/05/24 CSA Defect#7557
                    if (!executeApi(inPMsg, sMsg, i)) {
                    // mod end 2016/05/24 CSA Defect#7557
                        errFlg = true;
                        continue;
                    }
                    inPMsg.clear();
                }
            // END   2022/03/16 R.Cabral [QC#59748, ADD]
            } else if (!callApiFlg && !callDtlApiFlg && FLG_ON_Y.equals(aSMsgIn.xxChkBox_A2.getValue())) {
                // set Header Parameter
                setHdrParamForDtlAndMtr(cMsg, aSMsgIn, inPMsg);
                //base level
                // mod start 2016/02/29 CSA Defect#2684
                if (MTR_LB_NM.equals(aSMsgIn.mtrLbDescTxt_A1.getValue())) {
                // mod end 2016/02/29 CSA Defect#2684
                    //set Detail Parameter
                    List<Map<String, Object>> dsContrDtlPkList = getDsContrDtl(cMsg, aSMsgIn.dsContrPk_A1.getValue(), aSMsgIn.dsContrDtlPk_A1.getValue());
                    if (dsContrDtlPkList != null && !setDtlParamList(cMsg, aSMsgIn, dsContrDtlPkList, inPMsg, sMsg)) {
                        setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                        errFlg = true;
                        continue;
                    }
                    // call api
                    // mod start 2016/05/24 CSA Defect#7557
                    if (!executeApi(inPMsg, sMsg, i)) {
                    // mod end 2016/05/24 CSA Defect#7557
                        errFlg = true;
                        continue;
                    }
                    inPMsg.clear();
                } else {
                    // set Overage Parameter
                    List<Map<String, Object>> dsContrDtlPkList = getDsContrDtl(cMsg, aSMsgIn.dsContrPk_A1.getValue(), aSMsgIn.dsContrDtlPk_A1.getValue());
                    if (dsContrDtlPkList != null && !setDtlParamListForMtr(cMsg, aSMsgIn, dsContrDtlPkList, inPMsg)) {
                        setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                        errFlg = true;
                        continue;
                    }
                    if (dsContrDtlPkList != null && !setBllgMtrParamListForMtr(cMsg, aSMsgIn, dsContrDtlPkList, inPMsg)) {
                        setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                        errFlg = true;
                        continue;
                    }
                    // call api
                    // mod start 2016/05/24 CSA Defect#7557
                    if (!executeApi(inPMsg, sMsg, i)) {
                    // mod end 2016/05/24 CSA Defect#7557
                        errFlg = true;
                        continue;
                    }
                    inPMsg.clear();
                }
            }
            preContrPk = curContrPk;
            preContrDtlPk = curContrDtlPk;
        }
        if (errFlg) {
            cMsg.setMessageInfo(NSAM0394W, new String[] {});
        } else {
            cMsg.setMessageInfo(NSAM0200I, new String[] {});
        }
    }

    /**
     * @param cMsg
     * @param aCMsgIn
     * @param dsContrDtlPkList
     * @param inPMsg
     */
    private static boolean setBllgMtrParamList(NSAL0720CMsg cMsg, NSAL0720_ASMsg aSMsgIn, List<Map<String, Object>> dsContrDtlPkList, NSZC046001PMsg inPMsg, NSAL0720SMsg sMsg) {
        BigDecimal outDsContrDtlPk = null;
        for (Map<String, Object> outMap : dsContrDtlPkList) {
            outDsContrDtlPk = (BigDecimal) outMap.get("DS_CONTR_DTL_PK");
            // START 2016/08/26 A.Kohinata [QC#5922, ADD]
            DS_CONTR_DTLTMsg inDtlTMsg = new DS_CONTR_DTLTMsg();
            setValue(inDtlTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            setValue(inDtlTMsg.dsContrDtlPk, outDsContrDtlPk);
            DS_CONTR_DTLTMsg outDtlTMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inDtlTMsg);
            if (outDtlTMsg == null) {
                setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                return false;
            }
            if (DS_CONTR_CATG.FLEET.equals(inPMsg.dsContrCatgCd.getValue()) && !DS_CONTR_DTL_TP.FLEET.equals(outDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }
            // END 2016/08/26 A.Kohinata [QC#5922, ADD]
            DS_CONTR_BLLG_MTR_STS_VTMsgArray outBllgMtrStsVTMsgArray = getBllgMtrStsV(cMsg, outDsContrDtlPk);
            // set DS_CONTR_BLLG_MTR Parameter
            if (outBllgMtrStsVTMsgArray != null) {
                for (int j = 0; j < outBllgMtrStsVTMsgArray.getValidCount(); j++) {
                    DS_CONTR_BLLG_MTR_STS_VTMsg outBllgMtrStsVTMsg = outBllgMtrStsVTMsgArray.no(j);
                    DS_CONTR_BLLG_MTRTMsg inBllgMtrTMsg = new DS_CONTR_BLLG_MTRTMsg();
                    setValue(inBllgMtrTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                    setValue(inBllgMtrTMsg.dsContrBllgMtrPk, outBllgMtrStsVTMsg.dsContrBllgMtrPk.getValue());
                    DS_CONTR_BLLG_MTRTMsg outBllgMtrTMsg = (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inBllgMtrTMsg);
                    if (outBllgMtrTMsg == null) {
                        setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                        return false;
                    }
                    for (int k = 0; k < sMsg.A.getValidCount(); k++) {
                        if (hasValue(sMsg.A.no(k).dsContrBllgMtrPk_A1)
                                && outBllgMtrTMsg.dsContrBllgMtrPk.getValue().compareTo(sMsg.A.no(k).dsContrBllgMtrPk_A1.getValue()) == 0) {
                            if (!isSameTimeDsContrBllgMtr(outBllgMtrTMsg, sMsg.A.no(k), cMsg)) {
                                setValue(aSMsgIn.xxGenlFldAreaTxt_A1, getRtnMsg(ZZZM9004E));
                                return false;
                            }
                            break;
                        }
                    }
                    setBllgMtrParamForHdr(cMsg, aSMsgIn, outBllgMtrTMsg, inPMsg);
                    // del start 2016/07/27 CSA Defect#12001
                    //set DS_CONTR_CR_CARD_PO Parameter
                    //if (FLG_ON_Y.equals(cMsg.xxChkBox_LS.getValue())) {
                    //    DS_CONTR_CR_CARD_POTMsgArray outContrCrCardPoTMsgArray = NSAL0720Query.getInstance().getContrCrCardPo(cMsg.glblCmpyCd.getValue(), outBllgMtrTMsg.dsContrDtlPk.getValue(), outBllgMtrTMsg.dsContrBllgMtrPk.getValue());
                    //    for (int k = 0; k < outContrCrCardPoTMsgArray.getValidCount(); k++) {
                    //        DS_CONTR_CR_CARD_POTMsg inContrCrCardPoTMsg = outContrCrCardPoTMsgArray.no(k);
                    //        DS_CONTR_CR_CARD_POTMsg outContrCrCardPoTMsg = (DS_CONTR_CR_CARD_POTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inContrCrCardPoTMsg);
                    //        if (outContrCrCardPoTMsg == null) {
                    //            setValue(aCMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                    //            return false;
                    //        }
                    //        setContrCrCardPoParam(cMsg, outContrCrCardPoTMsg, inPMsg);
                    //    }
                    //}
                    // del end 2016/07/27 CSA Defect#12001
                }
            }
        }
        return true;
    }

    /**
     * @param cMsg
     * @param aCMsgIn
     * @param dsContrDtlPkList
     * @param inPMsg
     */
    private static boolean setBllgMtrParamListForMtr(NSAL0720CMsg cMsg, NSAL0720_ASMsg aSMsgIn, List<Map<String, Object>> dsContrDtlPkList, NSZC046001PMsg inPMsg) {
        DS_CONTR_BLLG_MTRTMsg inBllgMtrTMsg = new DS_CONTR_BLLG_MTRTMsg();
        setValue(inBllgMtrTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        setValue(inBllgMtrTMsg.dsContrBllgMtrPk, aSMsgIn.dsContrBllgMtrPk_A1.getValue());
        DS_CONTR_BLLG_MTRTMsg outBllgMtrTMsg = (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inBllgMtrTMsg);
        if (outBllgMtrTMsg == null) {
            setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
            return false;
        }
        if (!isSameTimeDsContrBllgMtr(outBllgMtrTMsg, aSMsgIn, cMsg)) {
            setValue(aSMsgIn.xxGenlFldAreaTxt_A1, getRtnMsg(ZZZM9004E));
            return false;
        }
        setBllgMtrParamForHdr(cMsg, aSMsgIn, outBllgMtrTMsg, inPMsg);
        // del start 2016/07/27 CSA Defect#12001
        //set DS_CONTR_CR_CARD_PO Parameter
        //if (FLG_ON_Y.equals(cMsg.xxChkBox_LS.getValue())) {
        //    DS_CONTR_CR_CARD_POTMsgArray outContrCrCardPoTMsgArray = NSAL0720Query.getInstance().getContrCrCardPo(cMsg.glblCmpyCd.getValue(), outBllgMtrTMsg.dsContrDtlPk.getValue(), outBllgMtrTMsg.dsContrBllgMtrPk.getValue());
        //    for (int k = 0; k < outContrCrCardPoTMsgArray.getValidCount(); k++) {
        //        DS_CONTR_CR_CARD_POTMsg inContrCrCardPoTMsg = outContrCrCardPoTMsgArray.no(k);
        //        DS_CONTR_CR_CARD_POTMsg outContrCrCardPoTMsg = (DS_CONTR_CR_CARD_POTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inContrCrCardPoTMsg);
        //        if (outContrCrCardPoTMsg == null) {
        //            setValue(aCMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
        //            return false;
        //        }
        //        setContrCrCardPoParam(cMsg, outContrCrCardPoTMsg, inPMsg);
        //    }
        //}
        // del end 2016/07/27 CSA Defect#12001
        return true;
    }

    /**
     * @param cMsg
     * @param aCMsgIn
     * @param dsContrDtlPkList
     * @param inPMsg
     */
    private static boolean setDtlParamList(NSAL0720CMsg cMsg, NSAL0720_ASMsg aSMsgIn, List<Map<String, Object>> dsContrDtlPkList, NSZC046001PMsg inPMsg, NSAL0720SMsg sMsg) {
        BigDecimal outDsContrDtlPk = null;
        for (Map<String, Object> outMap : dsContrDtlPkList) {
            outDsContrDtlPk = (BigDecimal) outMap.get("DS_CONTR_DTL_PK");
            DS_CONTR_DTLTMsg inDtlTMsg = new DS_CONTR_DTLTMsg();
            setValue(inDtlTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            setValue(inDtlTMsg.dsContrDtlPk, outDsContrDtlPk);
            DS_CONTR_DTLTMsg outDtlTMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inDtlTMsg);
            if (outDtlTMsg == null) {
                setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                return false;
            }
            // START 2016/08/26 A.Kohinata [QC#5922, ADD]
            if (DS_CONTR_CATG.FLEET.equals(inPMsg.dsContrCatgCd.getValue()) && !DS_CONTR_DTL_TP.FLEET.equals(outDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }
            // END 2016/08/26 A.Kohinata [QC#5922, ADD]
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (hasValue(sMsg.A.no(j).dsContrDtlPk_A1)
                        && outDtlTMsg.dsContrDtlPk.getValue().compareTo(sMsg.A.no(j).dsContrDtlPk_A1.getValue()) == 0) {
                    if (!isSameTimeDsContrDtl(outDtlTMsg, sMsg.A.no(j), cMsg)) {
                        setValue(aSMsgIn.xxGenlFldAreaTxt_A1, getRtnMsg(ZZZM9004E));
                        return false;
                    }
                    break;
                }
            }
            setDtlParamForHdr(cMsg, aSMsgIn, outDtlTMsg, inPMsg);
        }
        return true;
    }

    /**
     * @param cMsg
     * @param aCMsgIn
     * @param dsContrDtlPkList
     * @param inPMsg
     */
    private static boolean setDtlParamListForMtr(NSAL0720CMsg cMsg, NSAL0720_ASMsg aSMsgIn, List<Map<String, Object>> dsContrDtlPkList, NSZC046001PMsg inPMsg) {
        DS_CONTR_DTLTMsg inDtlTMsg = new DS_CONTR_DTLTMsg();
        setValue(inDtlTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        setValue(inDtlTMsg.dsContrDtlPk, aSMsgIn.dsContrDtlPk_A1.getValue());
        DS_CONTR_DTLTMsg outDtlTMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(inDtlTMsg);
        if (outDtlTMsg == null) {
            setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
            return false;
        }
        setDtlParamForMtr(cMsg, aSMsgIn, outDtlTMsg, inPMsg);
        return true;
    }

    /**
     * @param cMsg
     * @param aCMsgIn
     * @param inPMsg
     */
    private static void setHdrParam(NSAL0720CMsg cMsg, NSAL0720_ASMsg aSMsgIn, NSZC046001PMsg inPMsg) {
        DS_CONTRTMsg inTMsg = new DS_CONTRTMsg();
        setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inTMsg.dsContrPk, aSMsgIn.dsContrPk_A1);
        DS_CONTRTMsg outTMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inTMsg);

        EZDMsg.copy(outTMsg, null, inPMsg, null);
        setValue(inPMsg.xxModeCd, NSZC046001Constant.MODE_UPDATE);
        setValue(inPMsg.slsDt, ZYPDateUtil.getSalesDate());
        setValue(inPMsg.psnCd, cMsg.getUserID());
        if (BigDecimal.ZERO.equals(aSMsgIn.dsContrDtlPk_A1.getValue()) && BigDecimal.ZERO.equals(aSMsgIn.dsContrBllgMtrPk_A1.getValue())) {
            setValue(inPMsg.billToCustCd, aSMsgIn.billToCustCd_A2);
        } else {
            setValue(inPMsg.billToCustCd, outTMsg.altPayerCustCd);
        }
        setValue(inPMsg.dsContrLastUpdDt, ZYPDateUtil.getSalesDate());
        setValue(inPMsg.dsContrLastUpdPsnCd, cMsg.getUserID());
        setValue(inPMsg.dsContrSrcTpCd_HD, outTMsg.dsContrSrcTpCd);
        setValue(inPMsg.contrAdminPsnCd_HD, outTMsg.contrAdminPsnCd);

        setValue(inPMsg.xxSvcMemoList.no(0).xxModeCd, NSZC046001Constant.XX_MODE_CREATE);
        setValue(inPMsg.xxSvcMemoList.no(0).svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(inPMsg.xxSvcMemoList.no(0).svcMemoTpCd, SVC_MEMO_TP.UPDATE_BILL_TO);
        setValue(inPMsg.xxSvcMemoList.no(0).svcMemoRsnCd, cMsg.svcMemoRsnCd_H3);
        setValue(inPMsg.xxSvcMemoList.no(0).svcCmntTxt, cMsg.svcCmntTxt_H1);
        setValue(inPMsg.xxSvcMemoList.no(0).dsContrPk, aSMsgIn.dsContrPk_A1);
        setValue(inPMsg.xxSvcMemoList.no(0).dsContrDtlPk, aSMsgIn.dsContrDtlPk_A1);
        setValue(inPMsg.xxSvcMemoList.no(0).lastUpdUsrId, cMsg.getUserID());
        setValue(inPMsg.xxSvcMemoList.no(0).lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
        inPMsg.xxSvcMemoList.setValidCount(1);
    }

    /**
     * @param cMsg
     * @param aCMsgIn
     * @param inPMsg
     */
    private static void setHdrParamForDtlAndMtr(NSAL0720CMsg cMsg, NSAL0720_ASMsg aSMsgIn, NSZC046001PMsg inPMsg) {
        DS_CONTRTMsg inTMsg = new DS_CONTRTMsg();
        setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inTMsg.dsContrPk, aSMsgIn.dsContrPk_A1);
        DS_CONTRTMsg outTMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(inTMsg);
        EZDMsg.copy(outTMsg, null, inPMsg, null);
        setValue(inPMsg.xxModeCd, NSZC046001Constant.MODE_UPDATE);
        setValue(inPMsg.slsDt, ZYPDateUtil.getSalesDate());
        setValue(inPMsg.psnCd, cMsg.getUserID());
        setValue(inPMsg.billToCustCd, outTMsg.altPayerCustCd);
        setValue(inPMsg.dsContrSrcTpCd_HD, outTMsg.dsContrSrcTpCd);
        setValue(inPMsg.contrAdminPsnCd_HD, outTMsg.contrAdminPsnCd);

        setValue(inPMsg.xxSvcMemoList.no(0).xxModeCd, NSZC046001Constant.XX_MODE_CREATE);
        setValue(inPMsg.xxSvcMemoList.no(0).svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(inPMsg.xxSvcMemoList.no(0).svcMemoTpCd, SVC_MEMO_TP.UPDATE_BILL_TO);
        setValue(inPMsg.xxSvcMemoList.no(0).svcMemoRsnCd, cMsg.svcMemoRsnCd_H3);
        setValue(inPMsg.xxSvcMemoList.no(0).svcCmntTxt, cMsg.svcCmntTxt_H1);
        setValue(inPMsg.xxSvcMemoList.no(0).dsContrPk, aSMsgIn.dsContrPk_A1);
        setValue(inPMsg.xxSvcMemoList.no(0).dsContrDtlPk, aSMsgIn.dsContrDtlPk_A1);
        setValue(inPMsg.xxSvcMemoList.no(0).lastUpdUsrId, cMsg.getUserID());
        setValue(inPMsg.xxSvcMemoList.no(0).lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
        inPMsg.xxSvcMemoList.setValidCount(1);
    }

    /**
     * @param cMsg
     * @param aCMsgIn
     * @param outDtlTMsg
     * @param inPMsg
     */
    private static void setDtlParamForHdr(NSAL0720CMsg cMsg, NSAL0720_ASMsg aSMsgIn, DS_CONTR_DTLTMsg outTMsg, NSZC046001PMsg inPMsg) {
        int dtlCnt = inPMsg.xxContrDtlList.getValidCount();
        EZDMsg.copy(outTMsg, null, inPMsg.xxContrDtlList.no(dtlCnt), null);
        setValue(inPMsg.xxContrDtlList.no(dtlCnt).baseBillToCustCd, aSMsgIn.billToCustCd_A2);
        // QC#59240 T.WADA Add Start
        BigDecimal calcBasePrcTermDealAmtRate = calcBasePrcTermDealAmtRate(cMsg, aSMsgIn, outTMsg, inPMsg);
        setValue(inPMsg.xxContrDtlList.no(dtlCnt).basePrcTermDealAmtRate, calcBasePrcTermDealAmtRate);
        // QC#59240 T.WADA Add End
        inPMsg.xxContrDtlList.setValidCount(dtlCnt + 1);
    }

    // QC#59240 Add Start
    private static BigDecimal calcBasePrcTermDealAmtRate(NSAL0720CMsg cMsg, NSAL0720_ASMsg aSMsgIn, DS_CONTR_DTLTMsg outTMsg, NSZC046001PMsg inPMsg) {
        BigDecimal calculatedTermAmt = null;
        BigDecimal invoicedTermAmount = NSAL0720Query.getInstance().getInvoicedTermAmountForBase(cMsg.glblCmpyCd.getValue(), outTMsg.dsContrDtlPk.getValue());
        BigDecimal totalAmtOfUninvBefFinIn = NSAL0720Query.getInstance().getTotalAmtOfUninvBefFinInv(cMsg.glblCmpyCd.getValue(), outTMsg.dsContrDtlPk.getValue());
        if (!ZYPCommonFunc.hasValue(totalAmtOfUninvBefFinIn)) {
            totalAmtOfUninvBefFinIn = BigDecimal.ZERO;
        }
        boolean invoicedFlag = false;
        if (ZYPCommonFunc.hasValue(invoicedTermAmount)) {
            invoicedFlag = true;
        }
        int bllgCycleCnt = calcBllgCycleCntFromDuration(outTMsg, cMsg.glblCmpyCd.getValue(), invoicedFlag);
        if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(outTMsg.basePrcDealAmt)) {
            calculatedTermAmt = outTMsg.basePrcDealAmt.getValue().multiply(BigDecimal.valueOf(bllgCycleCnt));
            if (ZYPCommonFunc.hasValue(invoicedTermAmount)) {
                calculatedTermAmt = calculatedTermAmt.add(invoicedTermAmount).add(totalAmtOfUninvBefFinIn);
            }
        }
        return calculatedTermAmt;
    }
    private static int calcBllgCycleCntFromDuration(DS_CONTR_DTLTMsg outTMsg, String glblCmpyCd, boolean invoicedFlag ) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date startDt;
        try {
                String calcFromDt = null;
                if (invoicedFlag) {
                    calcFromDt = NSAL0720Query.getInstance().getMinUnbilledFromDt(glblCmpyCd, outTMsg.dsContrDtlPk.getValue());
                }
                if (!hasValue(calcFromDt)) {
                    calcFromDt = outTMsg.contrEffFromDt.getValue();
                } 
                
                startDt = df.parse(calcFromDt);
            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }

            String paramEndDate = outTMsg.contrEffThruDt.getValue();
            if (ZYPCommonFunc.hasValue(outTMsg.contrCloDt)) {
                paramEndDate = outTMsg.contrCloDt.getValue();
            }
            Calendar cal = Calendar.getInstance();
            String calcEndDate = "";
            BigDecimal durnCnt = BigDecimal.ZERO;

            while (paramEndDate.compareTo(calcEndDate) > 0) {
                cal.setTime(startDt);
                durnCnt = durnCnt.add(BigDecimal.ONE);

                cal.add(Calendar.MONTH, durnCnt.intValue());
                cal.add(Calendar.DATE, -1);

                calcEndDate = df.format(cal.getTime());
            }

            if (paramEndDate.compareTo(calcEndDate) != 0) {
                return 0;
            }

            // get BLLG_CYCLE Info
            BLLG_CYCLETMsg bcTMsg = new BLLG_CYCLETMsg();
            setValue(bcTMsg.glblCmpyCd, glblCmpyCd);
            setValue(bcTMsg.bllgCycleCd, outTMsg.baseBllgCycleCd);
            bcTMsg = (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(bcTMsg);

            if (durnCnt.intValue() % bcTMsg.bllgCycleMthAot.getValueInt() != 0) {
                return 0;
            }
            return durnCnt.intValue() / bcTMsg.bllgCycleMthAot.getValueInt();
    }
    // QC#59240 Add End
    
    /**
     * @param cMsg
     * @param aCMsgIn
     * @param outDtlTMsg
     * @param inPMsg
     */
    private static void setDtlParamForMtr(NSAL0720CMsg cMsg, NSAL0720_ASMsg aSMsgIn, DS_CONTR_DTLTMsg outTMsg, NSZC046001PMsg inPMsg) {
        int dtlCnt = inPMsg.xxContrDtlList.getValidCount();
        EZDMsg.copy(outTMsg, null, inPMsg.xxContrDtlList.no(dtlCnt), null);
        inPMsg.xxContrDtlList.setValidCount(dtlCnt + 1);
    }

    /**
     * @param cMsg
     * @param aCMsgIn
     * @param outDtlTMsg
     * @param inPMsg
     */
    private static void setBllgMtrParamForHdr(NSAL0720CMsg cMsg, NSAL0720_ASMsg aSMsgIn, DS_CONTR_BLLG_MTRTMsg outTMsg, NSZC046001PMsg inPMsg) {
        int bllgMtrCnt = inPMsg.xxDsContrBllgMtrList.getValidCount();
        EZDMsg.copy(outTMsg, null, inPMsg.xxDsContrBllgMtrList.no(bllgMtrCnt), null);
        setValue(inPMsg.xxDsContrBllgMtrList.no(bllgMtrCnt).bllgMtrBillToCustCd, aSMsgIn.billToCustCd_A2);
        setValue(inPMsg.xxDsContrBllgMtrList.no(bllgMtrCnt).xxModeCd, NSZC046001Constant.XX_MODE_UPDATE);
        inPMsg.xxDsContrBllgMtrList.setValidCount(bllgMtrCnt + 1);
    }

    /**
     * @param cMsg
     * @param outDtlTMsg
     * @param inPMsg
     */
    // del start 2016/07/27 CSA Defect#12001
    /*
    private static void setContrCrCardPoParam(NSAL0720CMsg cMsg, DS_CONTR_CR_CARD_POTMsg outTMsg, NSZC046001PMsg inPMsg) {
        int crCardPoCnt = inPMsg.xxDsContrCrCardPoList.getValidCount();
        EZDMsg.copy(outTMsg, null, inPMsg.xxDsContrCrCardPoList.no(crCardPoCnt), null);
        setValue(inPMsg.xxDsContrCrCardPoList.no(crCardPoCnt).leaseCmpyFlg, FLG_ON_Y);
        setValue(inPMsg.xxDsContrCrCardPoList.no(crCardPoCnt).xxModeCd, NSZC046001Constant.XX_MODE_UPDATE);
        inPMsg.xxContrDtlList.setValidCount(crCardPoCnt + 1);
    }
    */
    // del end 2016/07/27 CSA Defect#12001

    /**
     * @param pMsg NSZC046001PMsg
     * @param aCMsgIn NSAL0720_ACMsg
     * @return boolean
     */
    // mod start 2016/05/24 CSA Defect#7557
    private static boolean executeApi(NSZC046001PMsg pMsg, NSAL0720SMsg sMsg, int index) {
        NSZC046001 api = new NSZC046001();
        api.execute(pMsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);
        if (hasValueList(pMsg, sMsg, index)) {
            // ***** rollback
            EZDConnectionMgr.getInstance().rollback();
            return false;
        } else {
            NSAL0720_ASMsg aSMsgIn = sMsg.A.no(index);
            // START 2020/06/05 K.Kitachi [QC#56086, ADD]
            if (!callBllgSchdApiRecovMode(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.dsContrPk.getValue(), aSMsgIn)) {
                // ***** rollback
                EZDConnectionMgr.getInstance().rollback();
                return false;
            }
            // END 2020/06/05 K.Kitachi [QC#56086, ADD]
            setValue(aSMsgIn.xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
            // ***** commit
            EZDConnectionMgr.getInstance().commit();
        }
        return true;
    }
    // mod end 2016/05/24 CSA Defect#7557
    // mod end 2016/11/08 CSA QC#4210

    /**
     * Get Return Message
     * @param msgId String
     * @param prm String[]
     * @return String
     */
    private static String getRtnMsg(String msgId, String[] prm) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId, prm);
//            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    /**
     * Get Return Message
     * @param msgId String
     * @param prm String[]
     * @return String
     */
    private static String getRtnMsg(String msgId) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId);
//            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    // mod start 2016/11/08 CSA QC#4210
    /**
     * @param pMsg NSZC046001PMsg
     * @param aCMsgIn NSAL0720_ACMsg
     * @return boolean
     */
    // mod start 2016/05/24 CSA Defect#7557
    private static boolean hasValueList(NSZC046001PMsg pMsg, NSAL0720SMsg sMsg, int index) {
        NSAL0720_ASMsg aSMsgIn = sMsg.A.no(index);
        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            setValue(aSMsgIn.xxGenlFldAreaTxt_A1, getRtnMsg(msg.getXxMsgid(), msg.getXxMsgPrmArray()));
            return true;
        }
        if (hasValue(pMsg.xxMsgId_HD)) {
            setValue(aSMsgIn.xxGenlFldAreaTxt_A1, pMsg.xxMsgId_HD.getValue() + " " + pMsg.xxDsMultMsgDplyTxt_HD.getValue());
            //setValue(aCMsgIn.xxGenlFldAreaTxt_A1, pMsg.xxDsMultMsgDplyTxt_HD.getValue());
            return true;
        }
        for (int i = 0; i < pMsg.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg contrDtlPMsg = pMsg.xxContrDtlList.no(i);
            if (hasValue(contrDtlPMsg.xxMsgId_DT)) {
                setValue(aSMsgIn.xxGenlFldAreaTxt_A1, contrDtlPMsg.xxMsgId_DT.getValue() + " " + contrDtlPMsg.xxDsMultMsgDplyTxt_DT.getValue());
                //setValue(aCMsgIn.xxGenlFldAreaTxt_A1, contrDtlPMsg.xxDsMultMsgDplyTxt_DT.getValue());
                return true;
            // add start 2016/05/24 CSA Defect#7557
            } else {
                // set Success no select Base/Usage Line
                if (hasValue(sMsg.A.no(index).xxScrItem34Txt_A1)) {
                    for (int sCnt = index; sCnt < sMsg.A.getValidCount(); sCnt++) {
                        if (pMsg.xxContrDtlList.no(i).dsContrPk.getValue().equals(sMsg.A.no(sCnt).dsContrPk_A1.getValue())) {
                            setValue(sMsg.A.no(sCnt).xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
                        }
                    }
                } else if (hasValue(sMsg.A.no(index).serNum_A1)) {
                    for (int sCnt = index; sCnt < sMsg.A.getValidCount(); sCnt++) {
                        if (pMsg.xxContrDtlList.no(i).dsContrDtlPk.getValue().equals(sMsg.A.no(sCnt).dsContrDtlPk_A1.getValue())) {
                            setValue(sMsg.A.no(sCnt).xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
                        }
                    }
                // START 2019/11/25 E.Kameishi [QC#53071, ADD]
                } else if (FLG_ON_Y.equals(sMsg.A.no(index).xxChkBox_A1.getValue()) && !hasValue(sMsg.A.no(index).mtrLbDescTxt_A1)) {
                    for (int sCnt = index; sCnt < sMsg.A.getValidCount(); sCnt++) {
                        if (pMsg.xxContrDtlList.no(i).dsContrDtlPk.getValue().equals(sMsg.A.no(sCnt).dsContrDtlPk_A1.getValue())) {
                            setValue(sMsg.A.no(sCnt).xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
                        }
                    }
                }
                // END 2019/11/25 E.Kameishi [QC#53071, ADD]
                // set Success Serial# Line
                if (MTR_LB_NM.equals(sMsg.A.no(index).mtrLbDescTxt_A1.getValue())) {
                    for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                        if (sMsg.A.no(index).dsContrDtlPk_A1.getValue().equals(sMsg.A.no(j).dsContrDtlPk_A1.getValue()) && hasValue(sMsg.A.no(j).serNum_A1)) {
                            setValue(sMsg.A.no(j).xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
                            break;
                        }
                    }
                }
            }
            // add end 2016/05/24 CSA Defect#7557
        }
        for (int i = 0; i < pMsg.xxDsContrBllgMtrList.getValidCount(); i++) {
            NSZC046001_xxDsContrBllgMtrListPMsg contrBllgMtrPMsg = pMsg.xxDsContrBllgMtrList.no(i);
            if (hasValue(contrBllgMtrPMsg.xxMsgId)) {
                setValue(aSMsgIn.xxGenlFldAreaTxt_A1, contrBllgMtrPMsg.xxMsgId.getValue() + " " + contrBllgMtrPMsg.xxDsMultMsgDplyTxt.getValue());
                //setValue(aCMsgIn.xxGenlFldAreaTxt_A1, contrBllgMtrPMsg.xxDsMultMsgDplyTxt.getValue());
                return true;
            // add start 2016/05/24 CSA Defect#7557
            } else {
                // set Success no select Base/Usage Line
                if (hasValue(sMsg.A.no(index).serNum_A1)) {
                    for (int sCnt = index; sCnt < sMsg.A.getValidCount(); sCnt++) {
                        if (pMsg.xxDsContrBllgMtrList.no(i).dsContrDtlPk.getValue().equals(sMsg.A.no(sCnt).dsContrDtlPk_A1.getValue())) {
                            setValue(sMsg.A.no(sCnt).xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
                        }
                    }
                }
                // set Success Serial# Line
                if (MTR_LB_NM.equals(sMsg.A.no(index).mtrLbDescTxt_A1.getValue())) {
                    for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                        if (sMsg.A.no(index).dsContrDtlPk_A1.getValue().equals(sMsg.A.no(j).dsContrDtlPk_A1.getValue()) && hasValue(sMsg.A.no(j).serNum_A1)) {
                            setValue(sMsg.A.no(j).xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
                            break;
                        }
                    }
                }
            }
            // add end 2016/05/24 CSA Defect#7557
        }
        for (int i = 0; i < pMsg.xxDsContrCrCardPoList.getValidCount(); i++) {
            NSZC046001_xxDsContrCrCardPoListPMsg contrCrCardPoPMsg = pMsg.xxDsContrCrCardPoList.no(i);
            if (hasValue(contrCrCardPoPMsg.xxMsgId)) {
                setValue(aSMsgIn.xxGenlFldAreaTxt_A1, contrCrCardPoPMsg.xxMsgId.getValue() + " " + contrCrCardPoPMsg.xxDsMultMsgDplyTxt.getValue());
                //setValue(aCMsgIn.xxGenlFldAreaTxt_A1, contrCrCardPoPMsg.xxDsMultMsgDplyTxt.getValue());
                return true;
            // add start 2016/05/24 CSA Defect#7557
            } else {
                // set Success no select Base/Usage Line
                if (hasValue(sMsg.A.no(index).xxScrItem34Txt_A1)) {
                    for (int sCnt = index; sCnt < sMsg.A.getValidCount(); sCnt++) {
                        if (pMsg.xxDsContrCrCardPoList.no(i).dsContrPk.getValue().equals(sMsg.A.no(sCnt).dsContrPk_A1.getValue())) {
                            setValue(sMsg.A.no(sCnt).xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
                        }
                    }
                } else if (hasValue(sMsg.A.no(index).serNum_A1)) {
                    for (int sCnt = index; sCnt < sMsg.A.getValidCount(); sCnt++) {
                        if (pMsg.xxDsContrCrCardPoList.no(i).dsContrDtlPk.getValue().equals(sMsg.A.no(sCnt).dsContrDtlPk_A1.getValue())) {
                            setValue(sMsg.A.no(sCnt).xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
                        }
                    }
                // START 2019/11/25 E.Kameishi [QC#53071, ADD]
                } else if (FLG_ON_Y.equals(sMsg.A.no(index).xxChkBox_A1.getValue()) && !hasValue(sMsg.A.no(index).mtrLbDescTxt_A1)) {
                    for (int sCnt = index; sCnt < sMsg.A.getValidCount(); sCnt++) {
                        if (pMsg.xxDsContrCrCardPoList.no(i).dsContrDtlPk.getValue().equals(sMsg.A.no(sCnt).dsContrDtlPk_A1.getValue())) {
                            setValue(sMsg.A.no(sCnt).xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
                        }
                    }
                }
                // END 2019/11/25 E.Kameishi [QC#53071, ADD]
                // set Success Serial# Line
                if (MTR_LB_NM.equals(sMsg.A.no(index).mtrLbDescTxt_A1.getValue())) {
                    for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                        if (sMsg.A.no(index).dsContrDtlPk_A1.getValue().equals(sMsg.A.no(j).dsContrDtlPk_A1.getValue()) && hasValue(sMsg.A.no(j).serNum_A1)) {
                            setValue(sMsg.A.no(j).xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
                            break;
                        }
                    }
                }
            }
            // add end 2016/05/24 CSA Defect#7557
        }
        for (int i = 0; i < pMsg.xxSvcMemoList.getValidCount(); i++) {
            NSZC046001_xxSvcMemoListPMsg svcMemoPMsg = pMsg.xxSvcMemoList.no(i);
            if (hasValue(svcMemoPMsg.xxMsgId)) {
                setValue(aSMsgIn.xxGenlFldAreaTxt_A1, svcMemoPMsg.xxMsgId.getValue() + " " + svcMemoPMsg.xxDsMultMsgDplyTxt.getValue());
                //setValue(aCMsgIn.xxGenlFldAreaTxt_A1, svcMemoPMsg.xxDsMultMsgDplyTxt.getValue());
                return true;
            // add start 2016/05/24 CSA Defect#7557
            } else {
                // set Success no select Base/Usage Line
                if (hasValue(sMsg.A.no(index).xxScrItem34Txt_A1)) {
                    for (int sCnt = index; sCnt < sMsg.A.getValidCount(); sCnt++) {
                        if (pMsg.xxSvcMemoList.no(i).dsContrPk.getValue().equals(sMsg.A.no(sCnt).dsContrPk_A1.getValue())) {
                            setValue(sMsg.A.no(sCnt).xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
                        }
                    }
                } else if (hasValue(sMsg.A.no(index).serNum_A1)) {
                    for (int sCnt = index; sCnt < sMsg.A.getValidCount(); sCnt++) {
                        if (pMsg.xxSvcMemoList.no(i).dsContrDtlPk.getValue().equals(sMsg.A.no(sCnt).dsContrDtlPk_A1.getValue())) {
                            setValue(sMsg.A.no(sCnt).xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
                        }
                    }
                // START 2019/11/25 E.Kameishi [QC#53071, ADD]
                } else if (FLG_ON_Y.equals(sMsg.A.no(index).xxChkBox_A1.getValue()) && !hasValue(sMsg.A.no(index).mtrLbDescTxt_A1)) {
                    for (int sCnt = index; sCnt < sMsg.A.getValidCount(); sCnt++) {
                        if (pMsg.xxSvcMemoList.no(i).dsContrDtlPk.getValue().equals(sMsg.A.no(sCnt).dsContrDtlPk_A1.getValue())) {
                            setValue(sMsg.A.no(sCnt).xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
                        }
                    }
                }
                // END 2019/11/25 E.Kameishi [QC#53071, ADD]
                // set Success Serial# Line
                if (MTR_LB_NM.equals(sMsg.A.no(index).mtrLbDescTxt_A1.getValue())) {
                    for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                        if (sMsg.A.no(index).dsContrDtlPk_A1.getValue().equals(sMsg.A.no(j).dsContrDtlPk_A1.getValue()) && hasValue(sMsg.A.no(j).serNum_A1)) {
                            setValue(sMsg.A.no(j).xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
                            break;
                        }
                    }
                }
            }
            // add end 2016/05/24 CSA Defect#7557
        }
        return false;
    }
    // mod end 2016/05/24 CSA Defect#7557
    // mod end 2016/11/08 CSA QC#4210

    /**
     * Execute Contraction
     * @param cMsg NSAL0720CMsg
     * @param sMsg NSAL0720SMsg
     */
    public static void executeContraction(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {

        int targetIdxNum = cMsg.rowSqNum_H1.getValueInt();
        BigDecimal dsContrPk = cMsg.A.no(targetIdxNum).dsContrPk_A1.getValue();
        BigDecimal dsContrDtlPk = cMsg.A.no(targetIdxNum).dsContrDtlPk_A1.getValue();
        // mod start 2016/05/23 CSA Defect#5922
        if (!dsContrDtlPk.equals(BigDecimal.ZERO)) {
            setValue(cMsg.A.no(targetIdxNum).xxDplyCtrlFlg, FLG_ON_Y);
        }
        // mod end 2016/05/23 CSA Defect#5922
        setValue(cMsg.A.no(targetIdxNum).xxSmryLineFlg, FLG_ON_Y);
        copyCurrentPageToSMsg(cMsg, sMsg);

        // mod start 2016/11/11 CSA QC#4210
        int cnt = 0;
        //Header level
        if (hasValue(cMsg.A.no(targetIdxNum).dsContrDtlPk_A1)
                && BigDecimal.ZERO.compareTo(cMsg.A.no(targetIdxNum).dsContrDtlPk_A1.getValue()) == 0) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                BigDecimal dsContrPkLine = sMsg.A.no(i).dsContrPk_A1.getValue();
                BigDecimal dsContrDtlPkLine = sMsg.A.no(i).dsContrDtlPk_A1.getValue();
                if (hasValue(dsContrPkLine) && hasValue(dsContrDtlPkLine) && dsContrPk.compareTo(dsContrPkLine) == 0 && BigDecimal.ZERO.compareTo(dsContrDtlPkLine) != 0) {
                    setValue(sMsg.A.no(i).xxBtnFlg, FLG_ON_Y);
                    cnt++;
                }
            }
        } else {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                BigDecimal dsContrPkLine = sMsg.A.no(i).dsContrPk_A1.getValue();
                BigDecimal dsContrDtlPkLine = sMsg.A.no(i).dsContrDtlPk_A1.getValue();
                // mod start 2016/02/29 CSA Defect#2684
                String mtrLbNm = sMsg.A.no(i).mtrLbDescTxt_A1.getValue();
                // mod end 2016/02/29 CSA Defect#2684
                if (hasValue(dsContrPkLine) && hasValue(dsContrDtlPkLine) && hasValue(mtrLbNm) && dsContrPk.compareTo(dsContrPkLine) == 0 && dsContrDtlPk.compareTo(dsContrDtlPkLine) == 0) {
                    setValue(sMsg.A.no(i).xxBtnFlg, FLG_ON_Y);
                    cnt++;
                }
            }
        }

        copyFromSMsgOntoCmsg(cMsg, sMsg);

        cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.getValidCount() -1);
        if (cMsg.xxPageShowOfNum.getValueInt() - cnt >= 1) {
            cMsg.xxPageShowOfNum.setValue(cMsg.xxPageShowOfNum.getValueInt() - cnt);
        } else {
            cMsg.xxPageShowOfNum.setValue(cMsg.xxPageShowToNum.getValueInt());
        }
        // mod end 2016/11/11 CSA QC#4210
    }

    /**
     * Execute Expansion
     * @param cMsg NSAL0720CMsg
     * @param sMsg NSAL0720SMsg
     */
    public static void executeExpansion(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {

        int targetIdxNum = cMsg.rowSqNum_H1.getValueInt();
        BigDecimal dsContrPk = cMsg.A.no(targetIdxNum).dsContrPk_A1.getValue();
        BigDecimal dsContrDtlPk = cMsg.A.no(targetIdxNum).dsContrDtlPk_A1.getValue();
        setValue(cMsg.A.no(targetIdxNum).xxDplyCtrlFlg, FLG_ON_Y);
        setValue(cMsg.A.no(targetIdxNum).xxSmryLineFlg, FLG_OFF_N);
        copyCurrentPageToSMsg(cMsg, sMsg);

        // mod start 2016/11/11 CSA QC#4210
        int cnt = 0;
        if (hasValue(cMsg.A.no(targetIdxNum).dsContrDtlPk_A1)
                && BigDecimal.ZERO.compareTo(cMsg.A.no(targetIdxNum).dsContrDtlPk_A1.getValue()) == 0) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                BigDecimal dsContrPkLine = sMsg.A.no(i).dsContrPk_A1.getValue();
                BigDecimal dsContrDtlPkLine = sMsg.A.no(i).dsContrDtlPk_A1.getValue();
                // mod start 2016/05/23 CSA Defect#5922
                if (hasValue(dsContrPkLine) && hasValue(dsContrDtlPkLine) && dsContrPk.compareTo(dsContrPkLine) == 0 && dsContrDtlPk.compareTo(dsContrDtlPkLine) != 0) {
                    setValue(sMsg.A.no(i).xxSmryLineFlg, FLG_OFF_N);
                    // mod end 2016/05/23 CSA Defect#5922
                    setValue(sMsg.A.no(i).xxBtnFlg, FLG_OFF_N);
                    cnt++;
                }
            }
        } else {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                BigDecimal dsContrPkLine = sMsg.A.no(i).dsContrPk_A1.getValue();
                BigDecimal dsContrDtlPkLine = sMsg.A.no(i).dsContrDtlPk_A1.getValue();
                // mod start 2016/02/29 CSA Defect#2684
                String mtrLbNm = sMsg.A.no(i).mtrLbDescTxt_A1.getValue();
                // mod end 2016/02/29 CSA Defect#2684
                if (hasValue(dsContrPkLine) && hasValue(dsContrDtlPkLine) && hasValue(mtrLbNm) && dsContrPk.compareTo(dsContrPkLine) == 0 && dsContrDtlPk.compareTo(dsContrDtlPkLine) == 0) {
                    setValue(sMsg.A.no(i).xxBtnFlg, FLG_OFF_N);
                    cnt++;
                }
            }
        }

        copyFromSMsgOntoCmsg(cMsg, sMsg);
        cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.getValidCount() -1);
        cMsg.xxPageShowOfNum.setValue(cMsg.xxPageShowOfNum.getValueInt() + cnt);
        // mod end 2016/11/11 CSA QC#4210
    }

    // mod start 2016/11/08 CSA QC#4210
    /**
     * @param cMsg NSAL0720CMsg
     * @param sMsg NSAL0720SMsg
     */
    public static void copyFromSMsgOntoCmsg(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {

        int noCmsg = cMsg.A.no(0).xxRowNum_A.getValueInt();
        ZYPTableUtil.clear(cMsg.A);
        int count = 0;
        for (int i = noCmsg; i < sMsg.A.getValidCount(); i++) {
            if (count < cMsg.A.length()) {
                String flg = sMsg.A.no(i).xxBtnFlg.getValue();
                if (!ZYPConstant.FLG_ON_Y.equals(flg)) {
                    EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(count), null);
                    count++;
                }
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(count);
    }

    /**
     * copy To BSMsg for Current Page Info
     * @param cMsg NSAL0720CMsg
     * @param sMsg NSAL0720SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {

        // NSAL0720_ACMsg -> NSAL0720_BSMsg
        NSAL0720_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0720_ACMsg acMsg = acMsgArray.no(i);
            int targetIdxNum = acMsg.xxRowNum_A.getValueInt();

            NSAL0720_ASMsg asMsg = sMsg.A.no(targetIdxNum);
            setValue(asMsg.xxDplyCtrlFlg, acMsg.xxDplyCtrlFlg);
            setValue(asMsg.xxSmryLineFlg, acMsg.xxSmryLineFlg);
            setValue(asMsg.xxBtnFlg, acMsg.xxBtnFlg);
            setValue(asMsg.xxChkBox_A1, acMsg.xxChkBox_A1);
            setValue(asMsg.xxChkBox_A2, acMsg.xxChkBox_A2);
            setValue(asMsg.billToCustCd_A2, acMsg.billToCustCd_A2);
            setValue(asMsg.billToCustLocAddr_A2, acMsg.billToCustLocAddr_A2);
        }
    }

    /**
     * Check TimeStamp
     * @param currUpTime
     * @param currTimeZone
     * @param preUpTime
     * @param preTimeZone
     * @param cMsg NSAL0710CMsg
     * @return true/false
     */
    private static boolean isSameTimeStamp(String currUpTime, String currTimeZone, String preUpTime, String preTimeZone, NSAL0720CMsg cMsg) {

        if (!ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone)) {
            return false;
        }
        return true;
    }

    private static boolean isSameTimeDsContrHdr(DS_CONTRTMsg tMsg, NSAL0720_ASMsg asMsg, NSAL0720CMsg cMsg) {

        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();
        String preUpTime = asMsg.ezUpTime_HD.getValue();
        String preTimeZone = asMsg.ezUpTimeZone_HD.getValue();

        return isSameTimeStamp(currUpTime, currTimeZone, preUpTime, preTimeZone, cMsg);
    }

    private static boolean isSameTimeDsContrDtl(DS_CONTR_DTLTMsg tMsg, NSAL0720_ASMsg asMsg, NSAL0720CMsg cMsg) {

        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();
        String preUpTime = asMsg.ezUpTime_DT.getValue();
        String preTimeZone = asMsg.ezUpTimeZone_DT.getValue();

        return isSameTimeStamp(currUpTime, currTimeZone, preUpTime, preTimeZone, cMsg);
    }

    private static boolean isSameTimeDsContrBllgMtr(DS_CONTR_BLLG_MTRTMsg tMsg, NSAL0720_ASMsg asMsg, NSAL0720CMsg cMsg) {

        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();
        String preUpTime = asMsg.ezUpTime_BL.getValue();
        String preTimeZone = asMsg.ezUpTimeZone_BL.getValue();

        return isSameTimeStamp(currUpTime, currTimeZone, preUpTime, preTimeZone, cMsg);
    }
    // mod end 2016/11/08 CSA QC#4210

    // START 2020/06/05 K.Kitachi [QC#56086, ADD]
    private static boolean callBllgSchdApiRecovMode(String glblCmpyCd, String slsDt, BigDecimal dsContrPk, NSAL0720_ASMsg aSMsg) {
        NSZC047099PMsg pMsg = new NSZC047099PMsg();
        setValue(pMsg.glblCmpyCd, glblCmpyCd);
        setValue(pMsg.xxModeCd, "99");
        setValue(pMsg.slsDt, slsDt);
        setValue(pMsg.dsContrPk, dsContrPk);
        NSZC047001 api = new NSZC047001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            setValue(aSMsg.xxGenlFldAreaTxt_A1, getRtnMsg(msg.getXxMsgid(), msg.getXxMsgPrmArray()));
            return false;
        }
        return true;
    }
    // END 2020/06/05 K.Kitachi [QC#56086, ADD]
}
