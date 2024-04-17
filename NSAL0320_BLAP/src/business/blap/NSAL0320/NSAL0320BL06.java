/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0320;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0320.common.NSAL0320CommonLogic;
import business.blap.NSAL0320.constant.NSAL0320Constant;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsgArray;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_BLLG_MTR_USEDTMsgArray;
import business.db.DS_CONTR_BLLG_SCHDTMsgArray;
import business.db.DS_CONTR_BLLG_SCHD_MTRTMsgArray;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_PRC_EFF_MTRTMsgArray;
import business.db.MTR_LBTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsgArray;
import business.db.SVC_CONTR_BLLGTMsg;
import business.db.SVC_CONTR_BLLG_ALLOCTMsgArray;
import business.db.SVC_CONTR_MTR_BLLGTMsgArray;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsgArray;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;
import business.parts.NSZC047001PMsg;
import business.parts.NSZC047010PMsg;
import business.parts.NSZC047022PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            SRAA            Create          N/A
 * 2015/10/13   Hitachi         T.Tomita        Update          N/A
 * 2015/12/01   Hitachi         A.Kohinata      Update          QC496
 * 2016/01/07   Hitachi         T.Tomita        Update          QC#2806
 * 2016/01/18   Hitachi         T.Tomita        Update          QC#1088
 * 2016/02/03   Hitachi         T.Kanasaka      Update          QC#4029
 * 2016/02/08   Hitachi         T.Tomita        Update          QC#3271
 * 2016/02/09   Hitachi         T.Tomita        Update          QC#2424
 * 2016/03/14   Hitachi         T.Kanasaka      Update          QC#5005
 * 2016/05/20   Hitachi         T.Tomita        Update          QC#5472
 * 2016/05/25   Hitachi         T.Tomita        Update          QC#4958
 * 2016/06/01   Hitachi         T.Kanasaka      Update          QC#3964
 * 2016/06/07   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 2016/06/08   Hitachi         T.Kanasaka      Update          QC#3964, 5005
 * 2016/07/26   Hitachi         A.Kohinata      Update          QC#12106
 * 2016/10/17   Hitachi         K.Kishimoto     Update          QC#15133
 * 2016/11/15   Hitachi         K.Kishimoto     Update          QC#15133
 * 2016/12/15   Hitachi         T.Mizuki        Update          QC#16399
 * 2017/01/04   Hitachi         T.Mizuki        Update          QC#16399
 * 2017/02/01   Hitachi         T.Mizuki        Update          QC#17337
 * 2017/02/09   Hitachi         K.Kojima        Update          QC#17522
 * 2017/02/10   Hitachi         Y.Takeno        Update          QC#17494
 * 2017/05/26   Hitachi         N.Arai          Update          RS#7240
 * 2017/08/09   Hitachi         K.Kitachi       Update          QC#19122
 * 2017/08/07   Hitachi         T.Kanasaka      Update          QC#18193,18195
 * 2017/08/30   Hitachi         T.Kanasaka      Update          QC#20536
 * 2018/06/13   Hitachi         K.Kojima        Update          QC#26617
 * 2018/07/09   Hitachi         K.Kitachi       Update          QC#26834
 * 2020/04/16   CITS            T.Wada          Update          QC#55922
 * 2020/09/24   Hitachi         K.Kitachi       Update          QC#57667
 * 2022/06/22   CITS            E.Sanchez       Update          QC#59804
 * 2022/07/22   CITS            E.Sanchez       Update          QC#59804
 * 2022/10/25   CITS            E.Sanchez       Update          QC#60661
 *</pre>
 */
public class NSAL0320BL06 extends S21BusinessHandler {

    // mod start 2017/01/05 CSA QC#16399
    // START 2020/09/24 K.Kitachi [QC#57667, DEL]
    // /** prcEffDelFlg */
    // private boolean prcEffDelFlg = false;
    // END 2020/09/24 K.Kitachi [QC#57667, DEL]

    /** DsContrDtlPk List */
    private List<String> bllgMtrLbCdLst = null;

    // mod end 2017/01/05 CSA QC#16399

    // START 2020/09/24 K.Kitachi [QC#57667, DEL]
    // // QC#55922 Add Start
    // private boolean samePhysMtr = false;
    // // QC#55922 Add End
    // END 2020/09/24 K.Kitachi [QC#57667, DEL]

    // START 2020/09/24 K.Kitachi [QC#57667, ADD]
    /** Create Billing Meter List */
    private List<String> createBllgMtrList = null;

    /** Delete Billing Meter List */
    private List<String> deleteBllgMtrList = null;

    /** Delete Complete Billing Meter List */
    private List<String> delCpltBllgMtrList = null;

    // END 2020/09/24 K.Kitachi [QC#57667, ADD]

    // START 2022/10/25 E.Sanchez [QC#60661, ADD]
    /** List of DS_CONTR_DTL_PK already processed for cancellation */
    private List<BigDecimal> procDsContrDtlPkList = null;
    // END 2022/10/25 E.Sanchez [QC#60661, ADD]

    @Override
    protected boolean checkInput(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        NSAL0320CMsg cMsg = (NSAL0320CMsg) ezdCMsg;
        String screenAplId = cMsg.getScreenAplID();
        // mod start 2017/01/05 CSA QC#16399
        this.bllgMtrLbCdLst = new ArrayList<String>();
        // START 2020/09/24 K.Kitachi [QC#57667, DEL]
        // this.prcEffDelFlg = false;
        // END 2020/09/24 K.Kitachi [QC#57667, DEL]
        // mod end 2017/01/05 CSA QC#16399
        if ("NSAL0320Scrn00_CMN_Submit".equals(screenAplId)) {
            return checkInput_NSAL0320Scrn00_CMN_Submit(cMsg);
        } else {
            throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
        }
    }

    private boolean checkInput_NSAL0320Scrn00_CMN_Submit(NSAL0320CMsg cMsg) {
        // START 2016/06/01 T.Kanasaka [QC#3964, ADD]
        setBllgMtrMapLvlNum(cMsg);
        // END 2016/06/01 T.Kanasaka [QC#3964, ADD]

        boolean valid = NSAL0320Constant.INPUT_IS_VALID;
        valid &= isValidEntry(cMsg);
        valid &= isValidMultiplier(cMsg);
        valid &= isValidBillingMeterLevel(cMsg);
        return valid;
    }

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        super.preDoProcess(ezdCMsg, ezdSMsg);
        try {
            NSAL0320CMsg cMsg = (NSAL0320CMsg) ezdCMsg;
            NSAL0320SMsg sMsg = (NSAL0320SMsg) ezdSMsg;
            String screenAplId = cMsg.getScreenAplID();
            if ("NSAL0320Scrn00_CMN_Submit".equals(screenAplId)) {
                doProcess_NSAL0320Scrn00_CMN_Submit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(ezdCMsg, ezdSMsg);
        }
    }

    // START 2016/06/08 T.Kanasaka [QC#5005, MOD]
    private void doProcess_NSAL0320Scrn00_CMN_Submit(NSAL0320CMsg cMsg, NSAL0320SMsg sMsg) {
        // START 2015/10/13 T.Tomita [N/A, MOD]
        // String glblCmpyCd = getGlobalCompanyCode();
        // NSAL0320Query query = NSAL0320Query.getInstance();
        // NSAL0320CommonLogic.applyBllblFlg(cMsg);
        // NSAL0320DiffValueSetter diffSetter = new
        // NSAL0320DiffValueSetter();

        // START 2016/03/14 T.Kanasaka [QC5005, ADD]
        removeUnBllbRelnForFltAgg(cMsg);
        // END 2016/03/14 T.Kanasaka [QC5005, ADD]

        // START 2018/07/09 K.Kitachi [QC#26834, ADD]
        removeLineMtrNotExstMachMtrInfoForFltAgg(cMsg);
        // END 2018/07/09 K.Kitachi [QC#26834, ADD]

        List<BigDecimal> updContrPhysBllgMtrRelnPkList = new ArrayList<BigDecimal>();

        List<Map<String, Object>> fltAggMachList = new ArrayList<Map<String, Object>>();

        // START 2020/09/24 K.Kitachi [QC#57667, DEL]
        // // QC#55922 Add Start
        // samePhysMtr = isSamePhysMtr(cMsg);
        // // QC#55922 Add End
        // END 2020/09/24 K.Kitachi [QC#57667, DEL]

        // START 2020/09/24 K.Kitachi [QC#57667, ADD]
        setBllgMtrByInsOrDel(cMsg, sMsg);
        // END 2020/09/24 K.Kitachi [QC#57667, ADD]

        // START 2022/10/25 E.Sanchez [QC#60661, ADD]
        this.procDsContrDtlPkList = new ArrayList<BigDecimal>();
        // END 2022/10/25 E.Sanchez [QC#60661, ADD]

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            fltAggMachList = getDsContrDtlForFltAggMach(cMsg, i);
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).contrPhysBllgMtrRelnPk)) {
                // new
                createData(cMsg, i, fltAggMachList, true);
            } else {
                // update
                updateData(cMsg, i, sMsg, fltAggMachList, updContrPhysBllgMtrRelnPkList);
            }
        }
        // update DS_CONTR_DTL
        updateDsContrDtl(cMsg, fltAggMachList);
        // add start 2016/06/07 CSA Defect#
        if (!callContrTrkAPI(cMsg)) {
            return;
        }
        // add end 2016/06/07 CSA Defect#
        cMsg.setMessageInfo(NSAL0320Constant.ZZM8100I);
        // END 2015/10/13 T.Tomita [N/A, ADD]
        // START 2015/10/13 T.Tomita [N/A, DEL]
        // TreeSet<BigDecimal> dsContrBllgMtrPkList = new
        // TreeSet<BigDecimal>();
        // Map<String, DS_CONTR_BLLG_MTRTMsg> curMtrTMsgMap = new
        // HashMap<String, DS_CONTR_BLLG_MTRTMsg>();
        // for (int i = 0; i < cMsg.A.getValidCount(); i++) {
        // DS_CONTR_BLLG_MTRTMsg curMtrTMsg = null;
        // if (ZYPCommonFunc.hasValue(cMsg.A.no(i).dsContrBllgMtrPk))
        // {
        // if
        // (!dsContrBllgMtrPkList.contains(cMsg.A.no(i).dsContrBllgMtrPk.getValue()))
        // {
        // dsContrBllgMtrPkList.add(cMsg.A.no(i).dsContrBllgMtrPk.getValue());
        // curMtrTMsg = query.getDsContrBllgMtr(glblCmpyCd,
        // cMsg.A.no(i).dsContrBllgMtrPk.getValue());
        // if (curMtrTMsg == null) {
        // cMsg.setMessageInfo(NSAL0320Constant.NSAM0045E, new
        // String[] {"DS Contract Billing Meter" });
        // return;
        // }
        // if
        // (!ZYPDateUtil.isSameTimeStamp(cMsg.A.no(i).ezUpTime_AB.getValue(),
        // cMsg.A.no(i).ezUpTimeZone_AB.getValue(),
        // curMtrTMsg.ezUpTime.getValue(),
        // curMtrTMsg.ezUpTimeZone.getValue())) {
        // cMsg.setMessageInfo(NSAL0320Constant.ZZZM9004E);
        // return;
        // }
        // curMtrTMsgMap.put(curMtrTMsg.bllgMtrLbCd.getValue(),
        // curMtrTMsg);
        // }
        // }
        // }
        //
        // Map<String, NSAL0320_ACMsg> entMtrMsgMap = new
        // HashMap<String, NSAL0320_ACMsg>();
        // for (int i = 0; i < cMsg.A.getValidCount(); i++) {
        // if
        // (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).bllblFlg.getValue())
        // && ZYPCommonFunc.hasValue(cMsg.A.no(i).bllgMtrLbCd)) {
        // String bllgMtrLbCd = cMsg.A.no(i).bllgMtrLbCd.getValue();
        // if (!entMtrMsgMap.containsKey(bllgMtrLbCd)) {
        // entMtrMsgMap.put(bllgMtrLbCd, cMsg.A.no(i));
        // }
        // }
        // }
        //
        // Map<String, DS_CONTR_BLLG_MTRTMsg> insMtrMap = new
        // HashMap<String, DS_CONTR_BLLG_MTRTMsg>();
        // Map<String, DS_CONTR_BLLG_MTRTMsg> updMtrMap = new
        // HashMap<String, DS_CONTR_BLLG_MTRTMsg>();
        // Map<String, DS_CONTR_BLLG_MTRTMsg> delMtrMap = new
        // HashMap<String, DS_CONTR_BLLG_MTRTMsg>();
        // delMtrMap.putAll(curMtrTMsgMap);
        //
        // for (Map.Entry<String, NSAL0320_ACMsg> entMtr :
        // entMtrMsgMap.entrySet()) {
        // String bllgMtrLbCd = entMtr.getKey();
        // NSAL0320_ACMsg bllgMtrMsg = entMtr.getValue();
        // if (delMtrMap.containsKey(bllgMtrLbCd)) {
        // DS_CONTR_BLLG_MTRTMsg tMsg = delMtrMap.get(bllgMtrLbCd);
        // // update columns of DS_CONTR_BLLG_MTRTMsg
        // updMtrMap.put(bllgMtrLbCd, tMsg);
        // delMtrMap.remove(bllgMtrLbCd);
        // } else {
        // DS_CONTR_BLLG_MTRTMsg tMsg = new DS_CONTR_BLLG_MTRTMsg();
        // ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd,
        // getGlobalCompanyCode());
        // ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgMtrPk,
        // ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_MTR_SQ));
        // ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk,
        // cMsg.dsContrDtlPk);
        // tMsg.dsContrBllgMtrId.clear();
        // tMsg.mlyCopyInclPrcQty.clear();
        // ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrLbCd,
        // bllgMtrMsg.bllgMtrLbCd);
        // tMsg.bllgMtrBillToCustCd.clear();
        // tMsg.bllgMtrBllgCycleCd.clear();
        // tMsg.xsChrgTpCd.clear();
        // insMtrMap.put(bllgMtrLbCd, tMsg);
        // }
        // }
        //
        // if (!insMtrMap.isEmpty()) {
        // Collection<DS_CONTR_BLLG_MTRTMsg> insMtrList =
        // insMtrMap.values();
        // int insCnt =
        // S21FastTBLAccessor.insert(insMtrList.toArray(new
        // DS_CONTR_BLLG_MTRTMsg[insMtrList.size()]));
        // if (insCnt != insMtrList.size()) {
        // cMsg.setMessageInfo(NSAL0320Constant.NSAM0032E, new
        // String[] {"DS Contract Billing Meter" });
        // return;
        // }
        // }
        //
        // if (!updMtrMap.isEmpty()) {
        // Collection<DS_CONTR_BLLG_MTRTMsg> updMtrList =
        // updMtrMap.values();
        // int updCnt =
        // S21FastTBLAccessor.update(updMtrList.toArray(new
        // DS_CONTR_BLLG_MTRTMsg[updMtrList.size()]));
        // if (updCnt != updMtrList.size()) {
        // cMsg.setMessageInfo(NSAL0320Constant.NSAM0031E, new
        // String[] {"DS Contract Billing Meter" });
        // return;
        // }
        // }
        //
        // if (!delMtrMap.isEmpty()) {
        // Collection<DS_CONTR_BLLG_MTRTMsg> delMtrList =
        // delMtrMap.values();
        // int delCnt =
        // S21FastTBLAccessor.removeLogical(delMtrList.toArray(new
        // DS_CONTR_BLLG_MTRTMsg[delMtrList.size()]));
        // if (delCnt != delMtrList.size()) {
        // cMsg.setMessageInfo(NSAL0320Constant.NSAM0033E, new
        // String[] {"DS Contract Billing Meter" });
        // return;
        // }
        // }
        //
        // Map<String, DS_CONTR_BLLG_MTRTMsg> mtrMap = new
        // HashMap<String, DS_CONTR_BLLG_MTRTMsg>();
        // mtrMap.putAll(insMtrMap);
        // mtrMap.putAll(updMtrMap);
        //
        // for (int i = 0; i < cMsg.A.getValidCount(); i++) {
        // BigDecimal dsContrBllgMtrPk = null;
        // if
        // (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).bllblFlg.getValue()))
        // {
        // String bllgMtrLbCd = cMsg.A.no(i).bllgMtrLbCd.getValue();
        // DS_CONTR_BLLG_MTRTMsg mtrTMsg = mtrMap.get(bllgMtrLbCd);
        // dsContrBllgMtrPk = mtrTMsg.dsContrBllgMtrPk.getValue();
        // }
        // if
        // (ZYPCommonFunc.hasValue(cMsg.A.no(i).contrPhysBllgMtrRelnPk))
        // {
        // CONTR_PHYS_BLLG_MTR_RELNTMsg curRelnTMsg =
        // query.getContrPhysBllgMtrReln(glblCmpyCd,
        // cMsg.A.no(i).contrPhysBllgMtrRelnPk.getValue());
        // if (curRelnTMsg == null) {
        // cMsg.setMessageInfo(NSAL0320Constant.NSAM0045E, new
        // String[] {"Contract Physical Billing Meter Relation" });
        // return;
        // }
        // if
        // (!ZYPDateUtil.isSameTimeStamp(cMsg.A.no(i).ezUpTime_AR.getValue(),
        // cMsg.A.no(i).ezUpTimeZone_AR.getValue(),
        // curRelnTMsg.ezUpTime.getValue(),
        // curRelnTMsg.ezUpTimeZone.getValue())) {
        // cMsg.setMessageInfo(NSAL0320Constant.ZZZM9004E);
        // return;
        // }
        // diffSetter.clear();
        // diffSetter.setValue(curRelnTMsg.contrMtrMultRate,
        // cMsg.A.no(i).contrMtrMultRate);
        // diffSetter.setValue(curRelnTMsg.bllgMtrLbCd,
        // cMsg.A.no(i).bllgMtrLbCd);
        // diffSetter.setValue(curRelnTMsg.dsContrBllgMtrPk,
        // dsContrBllgMtrPk);
        // diffSetter.setValue(curRelnTMsg.bllblFlg,
        // cMsg.A.no(i).bllblFlg);
        // diffSetter.setValue(curRelnTMsg.bllgMtrLvlNum,
        // cMsg.A.no(i).bllgMtrMapLvlNum);
        // if (diffSetter.set()) {
        // S21FastTBLAccessor.update(curRelnTMsg);
        // String rtnCd = curRelnTMsg.getReturnCode();
        // if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
        // cMsg.setMessageInfo(NSAL0320Constant.NSAM0031E, new
        // String[] {"Contract Physical Billing Meter Relation" });
        // return;
        // }
        // }
        // } else {
        // CONTR_PHYS_BLLG_MTR_RELNTMsg newRelnTMsg =
        // buildInsertContrPhysBllgMtrReln(cMsg, i, dsContrBllgMtrPk);
        // S21FastTBLAccessor.insert(newRelnTMsg);
        // String rtnCd = newRelnTMsg.getReturnCode();
        // if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
        // cMsg.setMessageInfo(NSAL0320Constant.NSAM0032E, new
        // String[] {"Contract Physical Billing Meter Relation" });
        // return;
        // }
        // }
        //
        // }
        // cMsg.setMessageInfo(NSAL0320Constant.ZZM8100I);
        // END 2015/10/13 T.Tomita [N/A, DEL]
    }

    // END 2016/06/08 T.Kanasaka [QC#5005, MOD]

    private boolean isValidEntry(NSAL0320CMsg cMsg) {
        boolean valid = true;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).bllblFlg.getValue())) {
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).contrMtrMultRate)) {
                    cMsg.A.no(i).contrMtrMultRate.setErrorInfo(1, NSAL0320Constant.NSAM0315E, new String[] {"Multiplier" });
                    valid = false;
                }
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).bllgMtrLbCd)) {
                    cMsg.A.no(i).bllgMtrLbCd.setErrorInfo(1, NSAL0320Constant.NSAM0315E, new String[] {"Billing Counter" });
                    valid = false;
                }
            }
        }
        return valid;
    }

    private boolean isValidMultiplier(NSAL0320CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        BigDecimal spcRate = BigDecimal.ONE.negate();
        BigDecimal minRate = ZYPCodeDataUtil.getNumConstValue(NSAL0320Constant.NSAL0320_MTR_MULT_RATE_MIN_NUM, glblCmpyCd);
        BigDecimal maxRate = ZYPCodeDataUtil.getNumConstValue(NSAL0320Constant.NSAL0320_MTR_MULT_RATE_MAX_NUM, glblCmpyCd);
        BigDecimal fctNum = ZYPCodeDataUtil.getNumConstValue(NSAL0320Constant.NSAL0320_MTR_MULT_RATE_FCT_NUM, glblCmpyCd);
        boolean valid = true;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).bllblFlg.getValue())) {
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).contrMtrMultRate)) {
                    BigDecimal multRate = cMsg.A.no(i).contrMtrMultRate.getValue();
                    if (multRate.compareTo(spcRate) != 0) {
                        if (multRate.compareTo(minRate) < 0 || multRate.compareTo(maxRate) > 0) {
                            cMsg.A.no(i).contrMtrMultRate.setErrorInfo(1, NSAL0320Constant.NSAM0316E, new String[] {minRate.toPlainString(), maxRate.toPlainString() });
                            valid = false;
                        }
                        if (multRate.remainder(fctNum).compareTo(BigDecimal.ZERO) != 0) {
                            cMsg.A.no(i).contrMtrMultRate.setErrorInfo(1, NSAL0320Constant.NSAM0317E, new String[] {fctNum.toPlainString() });
                            valid = false;
                        }
                    }
                }
            }
        }
        // START 2017/02/10 [QC#17494, DEL]
        // for (int i = 0; i < cMsg.A.getValidCount(); i++) {
        // // add start 2016/05/20 CSA Defect#5472
        // if
        // (!ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).bllblFlg.getValue()))
        // {
        // continue;
        // }
        // // add end 2016/05/20 CSA Defect#5472
        // String bllgMtrLbCd = cMsg.A.no(i).bllgMtrLbCd.getValue();
        // BigDecimal multRate =
        // cMsg.A.no(i).contrMtrMultRate.getValue();
        // for (int j = i + 1; j < cMsg.A.getValidCount(); j++) {
        // // add start 2016/05/20 CSA Defect#5472
        // if
        // (!ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(j).bllblFlg.getValue()))
        // {
        // continue;
        // }
        // // add end 2016/05/20 CSA Defect#5472
        // String cmpBllgMtrLbCd =
        // cMsg.A.no(j).bllgMtrLbCd.getValue();
        // BigDecimal cmpMultRate =
        // cMsg.A.no(j).contrMtrMultRate.getValue();
        // if (NSAL0320CommonLogic.isEqualStr(bllgMtrLbCd,
        // cmpBllgMtrLbCd) &&
        // !NSAL0320CommonLogic.isEqualNum(multRate, cmpMultRate)) {
        // cMsg.A.no(i).contrMtrMultRate.setErrorInfo(1,
        // NSAL0320Constant.NSAM0319E);
        // cMsg.A.no(j).contrMtrMultRate.setErrorInfo(1,
        // NSAL0320Constant.NSAM0319E);
        // valid = false;
        // }
        // }
        // }
        // END 2017/02/10 [QC#17494, DEL]
        return valid;
    }

    private boolean isValidBillingMeterLevel(NSAL0320CMsg cMsg) {
        boolean valid = true;
        // Mod Start 2016/10/17 <QC#15133>
        if (doLvlChk(cMsg) == false) {
            return true;
        }
        // START 2015/10/13 T.Tomita [N/A, MOD]
        // START 2016/06/01 T.Kanasaka [QC#3964, MOD]
        String bllgMtrMapLvlNum = NSAL0320Constant.INVLD_BLLG_MTR_MAP_LVL_NUM;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).bllblFlg.getValue())) {
                String curBllgMtrMapLvlNum = cMsg.A.no(i).bllgMtrMapLvlNum.getValue();
                if (NSAL0320CommonLogic.isEqualStr(bllgMtrMapLvlNum, NSAL0320Constant.INVLD_BLLG_MTR_MAP_LVL_NUM)) {
                    bllgMtrMapLvlNum = curBllgMtrMapLvlNum;
                } else {
                    if (!NSAL0320CommonLogic.isEqualStr(bllgMtrMapLvlNum, curBllgMtrMapLvlNum)) {
                        valid = false;
                        break;
                    }
                }
            }
        }
        if (!valid) {
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).bllblFlg.getValue())) {
                    cMsg.A.no(i).bllgMtrLbCd.setErrorInfo(1, NSAL0320Constant.NSAM0318E);
                }
            }
        }
        // <End> temporary comment out by QC#15133
        // Mod End 2016/10/17 <QC#15133>

        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        NSAL0320Query query = NSAL0320Query.getInstance();
        S21SsmEZDResult rslt;
        List<String> prntBllgMtrList;
        String exclBllgMtrLbCd;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            // START 2016/01/07 T.Tomita [QC#2806, ADD]
            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).bllblFlg.getValue())) {
                continue;
            }
            // END 2016/01/07 T.Tomita [QC#2806, ADD]
            // get Parent Billing Meter
            // START 2017/08/07 T.Kanasaka [QC#18193,18195,MOD]
            // rslt = query.getPrntBllgMtr(glblCmpyCd,
            // cMsg.A.no(i).mdlMtrLbCd.getValue(),
            // cMsg.A.no(i).bllgMtrLbCd.getValue(), slsDt);
            rslt = query.getPrntBllgMtr(glblCmpyCd, cMsg.A.no(i).mdlMtrLbCd.getValue(), cMsg.A.no(i).bllgMtrLbCd.getValue(), slsDt, cMsg.dsContrCatgCd.getValue(), cMsg.dsContrDtlPk.getValue());
            // END 2017/08/07 T.Kanasaka [QC#18193,18195,MOD]
            if (rslt != null && rslt.isCodeNormal()) {
                prntBllgMtrList = (List<String>) rslt.getResultObject();
                exclBllgMtrLbCd = cMsg.A.no(i).mdlMtrLbCd.getValue();
                if (isExistsPrntBllgMtr(cMsg.A, prntBllgMtrList, exclBllgMtrLbCd)) {
                    cMsg.A.no(i).bllgMtrLbCd.setErrorInfo(1, NSAL0320Constant.NSAM0318E);
                    valid = false;
                }
            }
        }
        // END 2016/06/01 T.Kanasaka [QC#3964, MOD]
        // END 2015/10/13 T.Tomita [N/A, MOD]
        return valid;
    }

    // Add Start 2016/11/15 <QC#15133>
    private boolean doLvlChk(NSAL0320CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        BigDecimal mdlId = cMsg.mdlId.getValue();
        String prcVldFlg = NSAL0320Query.getInstance().getPrcVldFlg(glblCmpyCd, mdlId);
        if (!ZYPCommonFunc.hasValue(prcVldFlg) || ZYPConstant.FLG_OFF_N.equals(prcVldFlg)) {
            return false;
        }
        return true;
    }

    // Add End 2016/11/15 <QC#15133>

    // START 2015/10/13 T.Tomita [N/A, ADD]
    private boolean isExistsPrntBllgMtr(NSAL0320_ACMsgArray acMsgArray, List<String> prntBllgMtrList, String exclBllgMtrLbCd) {
        if (prntBllgMtrList.size() == 0) {
            return false;
        }

        for (String prntBllgMtrLbCd : prntBllgMtrList) {
            for (int i = 0; i < acMsgArray.getValidCount(); i++) {
                // START 2016/01/07 T.Tomita [QC#2806, ADD]
                if (!ZYPConstant.FLG_ON_Y.equals(acMsgArray.no(i).bllblFlg.getValue())) {
                    continue;
                }
                // END 2016/01/07 T.Tomita [QC#2806, ADD]
                if (exclBllgMtrLbCd.equals(acMsgArray.no(i).bllgMtrLbCd.getValue())) {
                    continue;
                }

                if (prntBllgMtrLbCd.equals(acMsgArray.no(i).bllgMtrLbCd.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    // END 2015/10/13 T.Tomita [N/A, ADD]
    // START 2015/10/13 T.Tomita [N/A, MOD]
    // START 2016/03/14 T.Kanasaka [QC5005, MOD]
    private CONTR_PHYS_BLLG_MTR_RELNTMsg buildInsertContrPhysBllgMtrReln(NSAL0320CMsg cMsg, int idx, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal svcPhysMtrPk, BigDecimal svcMachMstrPk) {

        String bllblFlg = cMsg.A.no(idx).bllblFlg.getValue();
        if (!ZYPCommonFunc.hasValue(bllblFlg)) {
            bllblFlg = ZYPConstant.FLG_OFF_N;
        }

        if (!DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue()) && ZYPConstant.FLG_OFF_N.equals(bllblFlg) && svcPhysMtrPk == null) {
            return null;
        }

        if (!DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue()) && ZYPConstant.FLG_ON_Y.equals(bllblFlg) && svcPhysMtrPk == null) {
            String bllgMtrLbCd = cMsg.A.no(idx).bllgMtrLbCd.getValue();
            for (int i = 0; i < idx; i++) {
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).bllblFlg.getValue()) && bllgMtrLbCd.equals(cMsg.A.no(i).bllgMtrLbCd.getValue())) {
                    return null;
                }
            }
        }

        // mod start 2016/05/20 CSA Defect#4958
        if (ZYPCommonFunc.hasValue(dsContrDtlPk) && ZYPCommonFunc.hasValue(svcPhysMtrPk) && ZYPCommonFunc.hasValue(cMsg.A.no(idx).bllgMtrLbCd)) {
            CONTR_PHYS_BLLG_MTR_RELNTMsgArray tMsgArray = NSAL0320Query.getInstance().getContrPhysBllgMtrRelnForUpdate(getGlobalCompanyCode(), dsContrDtlPk, svcPhysMtrPk);
            if (tMsgArray.getValidCount() > 0) {
                for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                    EZDTBLAccessor.remove(tMsgArray.no(i));
                }
            }
        }
        // mod end 2016/05/20 CSA Defect#4958
        CONTR_PHYS_BLLG_MTR_RELNTMsg tMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.contrPhysBllgMtrRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_PHYS_BLLG_MTR_RELN_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, dsContrDtlPk);
        if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            ZYPEZDItemValueSetter.setValue(tMsg.machMstrPk, svcMachMstrPk);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.machMstrPk, cMsg.svcMachMstrPk);
        }
        tMsg.dsMdlMtrPk.clear();
        tMsg.physMtrId.clear();
        tMsg.dsContrBllgMtrId.clear();
        if (ZYPCommonFunc.hasValue(svcPhysMtrPk)) {
            ZYPEZDItemValueSetter.setValue(tMsg.svcPhysMtrPk, svcPhysMtrPk);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.svcPhysMtrPk, cMsg.A.no(idx).svcPhysMtrPk);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.bllblFlg, bllblFlg);

        if (ZYPConstant.FLG_ON_Y.equals(bllblFlg)) {
            ZYPEZDItemValueSetter.setValue(tMsg.contrMtrMultRate, cMsg.A.no(idx).contrMtrMultRate);
            ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrLbCd, cMsg.A.no(idx).bllgMtrLbCd);
            if (ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
            } else {
                if (DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgMtrPk, cMsg.A.no(idx).dsContrBllgMtrPk);
                } else {
                    NSAL0320Query query = NSAL0320Query.getInstance();
                    DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = query.getDsContrBllgMtrForUpdate(getGlobalCompanyCode(), dsContrDtlPk, cMsg.A.no(idx).bllgMtrLbCd.getValue());
                    if (dsContrBllgMtrTMsgArray.getValidCount() > 0) {
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgMtrPk, dsContrBllgMtrTMsgArray.no(0).dsContrBllgMtrPk);
                    }
                }
            }
            // START 2017/08/30 T.Kanasaka [QC#20536,MOD]
            // ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrLvlNum,
            // cMsg.A.no(idx).bllgMtrMapLvlNum);
            ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrLvlNum, getBllgMtrLvlNum(cMsg, cMsg.A.no(idx)));
            // END 2017/08/30 T.Kanasaka [QC#20536,MOD]
        } else {
            tMsg.contrMtrMultRate.clear();
            tMsg.bllgMtrLbCd.clear();
            tMsg.dsContrBllgMtrPk.clear();
            tMsg.bllgMtrLvlNum.clear();
        }
        // START 2017/05/26 [RS#7240, ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.actvFlg, ZYPConstant.FLG_ON_Y);
        // END 2017/05/26 [RS#7240, ADD]
        return tMsg;
    }

    // END 2016/03/14 T.Kanasaka [QC5005, MOD]
    // END 2015/10/13 T.Tomita [N/A, MOD]

    // START 2017/08/30 T.Kanasaka [QC#20536,ADD]
    private String getBllgMtrLvlNum(NSAL0320CMsg cMsg, NSAL0320_ACMsg acMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        String bllgMtrLbCd = acMsg.bllgMtrLbCd.getValue();

        List<Map<String, Object>> bllgMtrMapList = null;

        if (DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue())) {
            bllgMtrMapList = NSAL0320CommonLogic.getBllgMtrMap(glblCmpyCd, acMsg.mdlMtrLbCd.getValue(), null, null, cMsg.dsContrDtlPk.getValue(), slsDt, cMsg.prcMtrPkgPk_B.getValue());
        } else {
            bllgMtrMapList = NSAL0320Query.getInstance().getBllgMtrLbForFltAgg(glblCmpyCd, slsDt, acMsg.mdlMtrLbCd.getValue(), cMsg.dsContrCatgCd.getValue(), null, cMsg.dsContrDtlPk.getValue(), cMsg.prcMtrPkgPk_B.getValue());
        }

        if (bllgMtrMapList != null) {
            for (int j = 0; j < bllgMtrMapList.size(); j++) {
                Map<String, Object> bllgMtrMap = bllgMtrMapList.get(j);
                if (ZYPCommonFunc.hasValue(bllgMtrLbCd) && NSAL0320CommonLogic.isEqualStr(bllgMtrLbCd, (String) bllgMtrMap.get("BLLG_MTR_LB_CD"))) {
                    return (String) bllgMtrMap.get("BLLG_MTR_MAP_LVL_NUM");
                }
            }
        }

        return acMsg.bllgMtrMapLvlNum.getValue();
    }

    // END 2017/08/30 T.Kanasaka [QC#20536,ADD]

    // START 2015/10/13 T.Tomita [N/A, ADD]
    private void createData(NSAL0320CMsg cMsg, int idx, List<Map<String, Object>> fltAggMachList, boolean createRelnFlg) {
        int insCnt;
        // insert DS_CONTR_BLLG_MTR
        List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTmsgList = setDsContrBllgMtr(cMsg, idx, fltAggMachList);
        if (dsContrBllgMtrTmsgList.size() > 0) {
            insCnt = S21FastTBLAccessor.insert(dsContrBllgMtrTmsgList.toArray(new DS_CONTR_BLLG_MTRTMsg[dsContrBllgMtrTmsgList.size()]));
            if (insCnt != dsContrBllgMtrTmsgList.size()) {
                cMsg.setMessageInfo(NSAL0320Constant.NSAM0032E, new String[] {"DS Contract Billing Meter" });
                return;
            }
        }
        // mod start 2017/02/01 CSA QC#17337
        if (dsContrBllgMtrTmsgList.size() == 0) {
            dsContrBllgMtrTmsgList = setDsContrBllgMtrForFltAgg(cMsg, idx, fltAggMachList);
        }
        // mod end 2017/02/01 CSA QC#17337

        // insert CONTR_XS_COPY
        List<CONTR_XS_COPYTMsg> contrXsCopyTmsgList = setContrXsCopy(cMsg, idx, dsContrBllgMtrTmsgList);
        if (contrXsCopyTmsgList.size() > 0) {
            insCnt = S21FastTBLAccessor.insert(contrXsCopyTmsgList.toArray(new CONTR_XS_COPYTMsg[contrXsCopyTmsgList.size()]));
            if (insCnt != contrXsCopyTmsgList.size()) {
                cMsg.setMessageInfo(NSAL0320Constant.NSAM0032E, new String[] {"Contract Excess Copy" });
                return;
            }
        }

        if (createRelnFlg) {
            // insert CONTR_PHYS_BLLG_MTR_RELN
            List<CONTR_PHYS_BLLG_MTR_RELNTMsg> contrPhysBllgMtrRelnTmsgList = setContrPhysBllgMtrReln(cMsg, idx, fltAggMachList, dsContrBllgMtrTmsgList);
            if (contrPhysBllgMtrRelnTmsgList.size() > 0) {
                insCnt = S21FastTBLAccessor.insert(contrPhysBllgMtrRelnTmsgList.toArray(new CONTR_PHYS_BLLG_MTR_RELNTMsg[contrPhysBllgMtrRelnTmsgList.size()]));
                if (insCnt != contrPhysBllgMtrRelnTmsgList.size()) {
                    cMsg.setMessageInfo(NSAL0320Constant.NSAM0032E, new String[] {"Contract Physical Billing Meter Relation" });
                    return;
                }
            }
        }
    }

    private List<Map<String, Object>> getDsContrDtlForFltAggMach(NSAL0320CMsg cMsg, int idx) {
        List<Map<String, Object>> rtnLst = new ArrayList<Map<String, Object>>();
        BigDecimal dsContrPk = cMsg.dsContrPk.getValue();
        String mdlMtrLbCd = cMsg.A.no(idx).mdlMtrLbCd.getValue();
        if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue()) || DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
            NSAL0320Query query = NSAL0320Query.getInstance();
            S21SsmEZDResult rslt = query.getDsContrDtlForFltAggMach(getGlobalCompanyCode(), dsContrPk, mdlMtrLbCd);
            if (rslt != null && rslt.isCodeNormal()) {
                rtnLst = (List<Map<String, Object>>) rslt.getResultObject();
            }
        }
        return rtnLst;
    }

    private List<DS_CONTR_BLLG_MTRTMsg> setDsContrBllgMtr(NSAL0320CMsg cMsg, int idx, List<Map<String, Object>> fltAggMachList) {
        List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTmsgList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        BigDecimal dsContrDtlPk = cMsg.dsContrDtlPk.getValue();
        String bllgMtrLbCd = cMsg.A.no(idx).bllgMtrLbCd.getValue();
        String bllbleFlg = cMsg.A.no(idx).bllblFlg.getValue();

        if (!ZYPConstant.FLG_ON_Y.equals(bllbleFlg)) {
            return dsContrBllgMtrTmsgList;
        }

        NSAL0320Query query = NSAL0320Query.getInstance();
        S21SsmEZDResult rslt = query.getDsContrBllgMtrPk(getGlobalCompanyCode(), dsContrDtlPk, bllgMtrLbCd);
        if (rslt != null && rslt.isCodeNormal()) {
            BigDecimal dsContrBllgMtrPk = (BigDecimal) rslt.getResultObject();
            if (ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).dsContrBllgMtrPk, dsContrBllgMtrPk);
                return dsContrBllgMtrTmsgList;
            }
        }

        String dsContrDtlStsCd = null;
        DS_CONTR_DTLTMsg dsContrDtlTmsg = query.getDsContrDtl(getGlobalCompanyCode(), dsContrDtlPk);
        if (dsContrDtlTmsg != null) {
            dsContrDtlStsCd = dsContrDtlTmsg.dsContrDtlStsCd.getValue();
        }

        DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTmsg;
        // START 2017/02/09 K.Kojima [QC#17522,MOD]
        // dsContrBllgMtrTmsg = setDsContrBllgMtrTmsg(dsContrDtlPk,
        // bllgMtrLbCd, dsContrDtlStsCd);
        dsContrBllgMtrTmsg = setDsContrBllgMtrTmsg(dsContrDtlPk, bllgMtrLbCd, dsContrDtlStsCd, dsContrDtlTmsg);
        // END 2017/02/09 K.Kojima [QC#17522,MOD]
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(idx).dsContrBllgMtrPk, dsContrBllgMtrTmsg.dsContrBllgMtrPk);
        dsContrBllgMtrTmsgList.add(dsContrBllgMtrTmsg);
        for (Map<String, Object> fltAggMach : fltAggMachList) {
            // START 2017/02/09 K.Kojima [QC#17522,MOD]
            // dsContrBllgMtrTmsg = setDsContrBllgMtrTmsg((BigDecimal)
            // fltAggMach.get("DS_CONTR_DTL_PK"), bllgMtrLbCd,
            // dsContrDtlStsCd);
            dsContrBllgMtrTmsg = setDsContrBllgMtrTmsg((BigDecimal) fltAggMach.get("DS_CONTR_DTL_PK"), bllgMtrLbCd, dsContrDtlStsCd, dsContrDtlTmsg);
            // END 2017/02/09 K.Kojima [QC#17522,MOD]
            dsContrBllgMtrTmsgList.add(dsContrBllgMtrTmsg);
        }

        return dsContrBllgMtrTmsgList;
    }

    // mod start 2017/02/01 CSA QC#17337
    private List<DS_CONTR_BLLG_MTRTMsg> setDsContrBllgMtrForFltAgg(NSAL0320CMsg cMsg, int idx, List<Map<String, Object>> fltAggMachList) {
        List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTmsgList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        String bllgMtrLbCd = cMsg.A.no(idx).bllgMtrLbCd.getValue();
        String bllbleFlg = cMsg.A.no(idx).bllblFlg.getValue();

        if (!ZYPConstant.FLG_ON_Y.equals(bllbleFlg)) {
            return dsContrBllgMtrTmsgList;
        }

        BigDecimal prntDsContrBllgMtrPk = cMsg.A.no(idx).dsContrBllgMtrPk.getValue();
        if (!ZYPCommonFunc.hasValue(prntDsContrBllgMtrPk)) {
            return dsContrBllgMtrTmsgList;
        }

        NSAL0320Query query = NSAL0320Query.getInstance();
        DS_CONTR_BLLG_MTRTMsg prntDsContrBllgMtrTMsg = query.getDsContrBllgMtr(getGlobalCompanyCode(), prntDsContrBllgMtrPk);
        if (prntDsContrBllgMtrTMsg == null) {
            return dsContrBllgMtrTmsgList;
        }

        for (Map<String, Object> fltAggMach : fltAggMachList) {
            BigDecimal curDsContrDtlPk = (BigDecimal) fltAggMach.get("DS_CONTR_DTL_PK");
            DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTmsg = new DS_CONTR_BLLG_MTRTMsg();
            DS_CONTR_BLLG_MTRTMsgArray curDsContrBllgMtrArray = query.getDsContrBllgMtrForUpdate(getGlobalCompanyCode(), curDsContrDtlPk, bllgMtrLbCd);
            if (curDsContrBllgMtrArray != null && curDsContrBllgMtrArray.length() > 0) {
                dsContrBllgMtrTmsg = (DS_CONTR_BLLG_MTRTMsg) curDsContrBllgMtrArray.get(0);
            } else {
                String dsContrDtlStsCd = null;
                DS_CONTR_DTLTMsg dsContrDtlTmsg = query.getDsContrDtl(getGlobalCompanyCode(), curDsContrDtlPk);
                if (dsContrDtlTmsg != null) {
                    dsContrDtlStsCd = dsContrDtlTmsg.dsContrDtlStsCd.getValue();
                }
                // START 2017/02/09 K.Kojima [QC#17522,MOD]
                // dsContrBllgMtrTmsg =
                // setDsContrBllgMtrTmsg(curDsContrDtlPk, bllgMtrLbCd,
                // dsContrDtlStsCd);
                dsContrBllgMtrTmsg = setDsContrBllgMtrTmsg(curDsContrDtlPk, bllgMtrLbCd, dsContrDtlStsCd, dsContrDtlTmsg);
                // END 2017/02/09 K.Kojima [QC#17522,MOD]
                S21FastTBLAccessor.insert(dsContrBllgMtrTmsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrBllgMtrTmsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSAL0320Constant.NSAM0032E, new String[] {"DS Contract Billing Meter" });
                }
            }
            dsContrBllgMtrTmsgList.add(dsContrBllgMtrTmsg);
        }
        return dsContrBllgMtrTmsgList;
    }

    // mod end 2017/02/01 CSA QC#17337

    // START 2017/02/09 K.Kojima [QC#17522,MOD]
    // private DS_CONTR_BLLG_MTRTMsg setDsContrBllgMtrTmsg(BigDecimal
    // dsContrDtlPk, String bllgMtrLbCd, String dsContrDtlStsCd) {
    private DS_CONTR_BLLG_MTRTMsg setDsContrBllgMtrTmsg(BigDecimal dsContrDtlPk, String bllgMtrLbCd, String dsContrDtlStsCd, DS_CONTR_DTLTMsg dtlTMsg) {
        // END 2017/02/09 K.Kojima [QC#17522,MOD]
        DS_CONTR_BLLG_MTRTMsg tMsg = new DS_CONTR_BLLG_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgMtrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_MTR_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, dsContrDtlPk);
        tMsg.dsContrBllgMtrId.clear();
        tMsg.mlyCopyInclPrcQty.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrLbCd, bllgMtrLbCd);
        tMsg.bllgMtrBillToCustCd.clear();
        tMsg.bllgMtrBllgCycleCd.clear();
        // START 2016/02/08 T.Tomita [QC#3271, MOD]
        // tMsg.xsChrgTpCd.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.xsChrgTpCd, XS_CHRG_TP.POINT);
        // START 2016/02/08 T.Tomita [QC#3271, MOD]
        // START 2017/02/09 K.Kojima [QC#17522,MOD]
        // ZYPEZDItemValueSetter.setValue(tMsg.qltyAsrnHldFlg,
        // ZYPConstant.FLG_OFF_N);
        // ZYPEZDItemValueSetter.setValue(tMsg.mtrHldFlg,
        // ZYPConstant.FLG_OFF_N);
        // ZYPEZDItemValueSetter.setValue(tMsg.contrHldFlg,
        // ZYPConstant.FLG_OFF_N);
        // ZYPEZDItemValueSetter.setValue(tMsg.bllgHldFlg,
        // ZYPConstant.FLG_OFF_N);
        if (dtlTMsg != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.qltyAsrnHldFlg, dtlTMsg.qltyAsrnHldFlg);
            ZYPEZDItemValueSetter.setValue(tMsg.mtrHldFlg, dtlTMsg.mtrHldFlg);
            ZYPEZDItemValueSetter.setValue(tMsg.contrHldFlg, dtlTMsg.contrHldFlg);
            ZYPEZDItemValueSetter.setValue(tMsg.bllgHldFlg, dtlTMsg.bllgHldFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
        }
        // END 2017/02/09 K.Kojima [QC#17522,MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgMtrStsCd, dsContrDtlStsCd);
        // START 2016/01/18 T.Tomita [QC#1088, ADD]
        // START 2017/02/09 K.Kojima [QC#17522,MOD]
        // ZYPEZDItemValueSetter.setValue(tMsg.qltyAsrnHldPendApvlFlg,
        // ZYPConstant.FLG_OFF_N);
        if (dtlTMsg != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.qltyAsrnHldPendApvlFlg, dtlTMsg.qltyAsrnHldPendApvlFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
        }
        // END 2017/02/09 K.Kojima [QC#17522,MOD]
        // END 2016/01/18 T.Tomita [QC#1088, ADD]
        // START 2016/02/09 T.Tomita [QC#2424, ADD]
        if (ZYPCommonFunc.hasValue(bllgMtrLbCd)) {
            MTR_LBTMsg mtrLbTMsg = NSAL0320Query.getInstance().getMtrLbTMsg(getGlobalCompanyCode(), bllgMtrLbCd);
            if (mtrLbTMsg != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.intgMdseCd, mtrLbTMsg.intgMdseCd);
            }
        }
        // END 2016/02/09 T.Tomita [QC#2424, ADD]
        return tMsg;
    }

    // START 2016/03/14 T.Kanasaka [QC5005, MOD]
    private List<CONTR_PHYS_BLLG_MTR_RELNTMsg> setContrPhysBllgMtrReln(NSAL0320CMsg cMsg, int idx, List<Map<String, Object>> fltAggMachList, List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTmsgList) {
        List<CONTR_PHYS_BLLG_MTR_RELNTMsg> contrPhysBllgMtrRelnTmsgList = new ArrayList<CONTR_PHYS_BLLG_MTR_RELNTMsg>();
        CONTR_PHYS_BLLG_MTR_RELNTMsg contrPhysBllgMtrRelnTmsg;
        BigDecimal dsContrBllgMtrPk;
        BigDecimal dsContrDtlPk = cMsg.dsContrDtlPk.getValue();
        for (DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTmsg : dsContrBllgMtrTmsgList) {
            if (dsContrDtlPk.compareTo(dsContrBllgMtrTmsg.dsContrDtlPk.getValue()) == 0) {
                dsContrBllgMtrPk = dsContrBllgMtrTmsg.dsContrBllgMtrPk.getValue();
                contrPhysBllgMtrRelnTmsg = buildInsertContrPhysBllgMtrReln(cMsg, idx, dsContrDtlPk, dsContrBllgMtrPk, null, null);
                if (contrPhysBllgMtrRelnTmsg != null) {
                    contrPhysBllgMtrRelnTmsgList.add(contrPhysBllgMtrRelnTmsg);
                }
            }
        }

        BigDecimal svcPhysMtrPk = null;
        BigDecimal svcMachMstrPk = null;
        BigDecimal machDsContrDtlPk;
        for (Map<String, Object> fltAggMach : fltAggMachList) {
            machDsContrDtlPk = (BigDecimal) fltAggMach.get("DS_CONTR_DTL_PK");
            svcPhysMtrPk = (BigDecimal) fltAggMach.get("SVC_PHYS_MTR_PK");
            svcMachMstrPk = (BigDecimal) fltAggMach.get("SVC_MACH_MSTR_PK");
            for (DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTmsg : dsContrBllgMtrTmsgList) {
                if (machDsContrDtlPk.compareTo(dsContrBllgMtrTmsg.dsContrDtlPk.getValue()) == 0) {
                    dsContrBllgMtrPk = dsContrBllgMtrTmsg.dsContrBllgMtrPk.getValue();
                    contrPhysBllgMtrRelnTmsg = buildInsertContrPhysBllgMtrReln(cMsg, idx, machDsContrDtlPk, dsContrBllgMtrPk, svcPhysMtrPk, svcMachMstrPk);
                    if (contrPhysBllgMtrRelnTmsg != null) {
                        contrPhysBllgMtrRelnTmsgList.add(contrPhysBllgMtrRelnTmsg);
                    }
                }
            }
        }

        if (dsContrBllgMtrTmsgList.size() == 0) {
            contrPhysBllgMtrRelnTmsg = buildInsertContrPhysBllgMtrReln(cMsg, idx, dsContrDtlPk, null, null, null);
            if (contrPhysBllgMtrRelnTmsg != null) {
                contrPhysBllgMtrRelnTmsgList.add(contrPhysBllgMtrRelnTmsg);
            }
            for (Map<String, Object> fltAggMach : fltAggMachList) {
                dsContrDtlPk = (BigDecimal) fltAggMach.get("DS_CONTR_DTL_PK");
                svcPhysMtrPk = (BigDecimal) fltAggMach.get("SVC_PHYS_MTR_PK");
                svcMachMstrPk = (BigDecimal) fltAggMach.get("SVC_MACH_MSTR_PK");
                contrPhysBllgMtrRelnTmsg = buildInsertContrPhysBllgMtrReln(cMsg, idx, dsContrDtlPk, null, svcPhysMtrPk, svcMachMstrPk);
                if (contrPhysBllgMtrRelnTmsg != null) {
                    contrPhysBllgMtrRelnTmsgList.add(contrPhysBllgMtrRelnTmsg);
                }
            }
        }
        return contrPhysBllgMtrRelnTmsgList;
    }

    // END 2016/03/14 T.Kanasaka [QC5005, MOD]

    private List<CONTR_XS_COPYTMsg> setContrXsCopy(NSAL0320CMsg cMsg, int idx, List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTmsgList) {
        List<CONTR_XS_COPYTMsg> contrXsCopyList = new ArrayList<CONTR_XS_COPYTMsg>();
        if (!DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue())) {
            return contrXsCopyList;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.prcMtrPkgPk_B)) {
            return contrXsCopyList;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        BigDecimal mdlId = cMsg.mdlId.getValue();
        BigDecimal prcMtrPkgPk = cMsg.prcMtrPkgPk_B.getValue();
        String bllgMtrLbCd = cMsg.A.no(idx).bllgMtrLbCd.getValue();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        NSAL0320Query query = NSAL0320Query.getInstance();
        S21SsmEZDResult rslt = query.getPrcListSvcTier(glblCmpyCd, mdlId, prcMtrPkgPk, bllgMtrLbCd, slsDt);
        List<Map<String, Object>> rtnLst = new ArrayList<Map<String, Object>>();
        if (rslt != null && rslt.isCodeNormal()) {
            rtnLst = (List<Map<String, Object>>) rslt.getResultObject();
        }

        if (rtnLst.size() == 0) {
            return contrXsCopyList;
        }

        BigDecimal dsContrBllgMtrPk;
        String xsMtrFirstFlg;
        CONTR_XS_COPYTMsg contrXsCopyTmsg;
        for (DS_CONTR_BLLG_MTRTMsg dsContrBllgMtr : dsContrBllgMtrTmsgList) {
            dsContrBllgMtrPk = dsContrBllgMtr.dsContrBllgMtrPk.getValue();
            xsMtrFirstFlg = ZYPConstant.FLG_ON_Y;
            for (Map<String, Object> prcListSvcTier : rtnLst) {
                contrXsCopyTmsg = setContrXsCopyTmsg(glblCmpyCd, prcListSvcTier, dsContrBllgMtrPk, xsMtrFirstFlg);
                contrXsCopyList.add(contrXsCopyTmsg);
                xsMtrFirstFlg = ZYPConstant.FLG_OFF_N;
            }
        }
        return contrXsCopyList;
    }

    private CONTR_XS_COPYTMsg setContrXsCopyTmsg(String glblCmpyCd, Map<String, Object> prcListSvcTier, BigDecimal dsContrBllgMtrPk, String xsMtrFirstFlg) {
        CONTR_XS_COPYTMsg tMsg = new CONTR_XS_COPYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.contrXsCopyPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_XS_COPY_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        BigDecimal xsMtrCopyQty = (BigDecimal) prcListSvcTier.get("MIN_COPY_VOL_CNT");
        if (ZYPCommonFunc.hasValue(xsMtrCopyQty) && BigDecimal.ZERO.compareTo(xsMtrCopyQty) < 0) {
            xsMtrCopyQty = xsMtrCopyQty.subtract(BigDecimal.ONE);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.xsMtrCopyQty, xsMtrCopyQty);
        ZYPEZDItemValueSetter.setValue(tMsg.xsMtrAmtRate, (BigDecimal) prcListSvcTier.get("CPC_AMT_RATE"));
        ZYPEZDItemValueSetter.setValue(tMsg.xsMtrFirstFlg, xsMtrFirstFlg);
        return tMsg;
    }

    private void updateDsContrDtl(NSAL0320CMsg cMsg, List<Map<String, Object>> fltAggMachList) {
        List<DS_CONTR_DTLTMsg> dsContrDtlList = new ArrayList<DS_CONTR_DTLTMsg>();
        NSAL0320Query query = NSAL0320Query.getInstance();
        String glblCmpyCd = getGlobalCompanyCode();
        BigDecimal dsContrDtlPk = cMsg.dsContrDtlPk.getValue();
        DS_CONTR_DTLTMsg dsContrDtlTmsg = query.getDsContrDtl(glblCmpyCd, dsContrDtlPk);
        if (dsContrDtlTmsg == null) {
            cMsg.setMessageInfo(NSAL0320Constant.NSAM0045E, new String[] {"DS Contract Detail" });
            return;
        }
        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime.getValue(), cMsg.ezUpTimeZone.getValue(), dsContrDtlTmsg.ezUpTime.getValue(), dsContrDtlTmsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(NSAL0320Constant.ZZZM9004E);
            return;
        }
        BigDecimal prcMtrPkgPk = cMsg.prcMtrPkgPk_B.getValue();
        ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.prcMtrPkgPk, prcMtrPkgPk);
        dsContrDtlList.add(dsContrDtlTmsg);

        for (Map<String, Object> fltAggMach : fltAggMachList) {
            dsContrDtlPk = (BigDecimal) fltAggMach.get("DS_CONTR_DTL_PK");
            dsContrDtlTmsg = query.getDsContrDtl(glblCmpyCd, dsContrDtlPk);
            if (dsContrDtlTmsg == null) {
                cMsg.setMessageInfo(NSAL0320Constant.NSAM0045E, new String[] {"DS Contract Detail" });
                return;
            }
            ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.prcMtrPkgPk, prcMtrPkgPk);
            dsContrDtlList.add(dsContrDtlTmsg);
        }

        if (dsContrDtlList.size() == 0) {
            return;
        }

        int updCnt = S21FastTBLAccessor.update(dsContrDtlList.toArray(new DS_CONTR_DTLTMsg[dsContrDtlList.size()]));
        if (updCnt != dsContrDtlList.size()) {
            cMsg.setMessageInfo(NSAL0320Constant.NSAM0031E, new String[] {"DS Contract Detail" });
        }
    }

    // START 2016/03/14 T.Kanasaka [QC5005, MOD]
    // START 2016/06/08 T.Kanasaka [QC#5005, ADD]
    private void updateData(NSAL0320CMsg cMsg, int idx, NSAL0320SMsg sMsg, List<Map<String, Object>> fltAggMachList, List<BigDecimal> updContrPhysBllgMtrRelnPkList) {

        // START 2020/09/24 K.Kitachi [QC#57667, DEL]
        // if
        // (!NSAL0320CommonLogic.isEqualNum(cMsg.prcMtrPkgPk_B.getValue(),
        // sMsg.prcMtrPkgPk_B.getValue())) {
        // // Delete
        // deleteData(cMsg, idx, sMsg, fltAggMachList, true);
        // // Insert
        // createData(cMsg, idx, fltAggMachList, true);
        // // START 2018/06/13 K.Kojima [QC#26617,ADD]
        // callContrBllgSchdApiByDelOrRstBllg(cMsg, idx, sMsg);
        // // END 2018/06/13 K.Kojima [QC#26617,ADD]
        // return;
        // }
        // END 2020/09/24 K.Kitachi [QC#57667, DEL]

        String mdlMtrLbCd = cMsg.A.no(idx).mdlMtrLbCd.getValue();
        NSAL0320_ASMsg asMsg = null;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (mdlMtrLbCd.equals(sMsg.A.no(i).mdlMtrLbCd.getValue())) {
                asMsg = sMsg.A.no(i);
                break;
            }
        }

        if (asMsg == null) {
            return;
        }
        String bllbFlg = cMsg.A.no(idx).bllblFlg.getValue();
        if (!ZYPCommonFunc.hasValue(bllbFlg)) {
            bllbFlg = ZYPConstant.FLG_OFF_N;
        }
        // mod start 2017/01/05 CSA QC#16399
        // START 2017/08/09 K.Kitachi [QC#19122, DEL]
        // callContrBllgSchdApi(cMsg, idx, sMsg);
        // END 2017/08/09 K.Kitachi [QC#19122, DEL]
        // mod end 2017/01/05 CSA QC#16399
        // START 2017/08/09 K.Kitachi [QC#19122, ADD]
        // START 2022/10/25 E.Sanchez [QC#60661, MOD]
        // callContrBllgSchdApiByDelOrRstBllg(cMsg, idx, sMsg);
        callContrBllgSchdApiByDelOrRstBllg(cMsg, idx, sMsg, fltAggMachList);
        // END 2022/10/25 E.Sanchez [QC#60661, MOD]
        String bllgMtrLbCd = cMsg.A.no(idx).bllgMtrLbCd.getValue();
        BigDecimal contrMtrMultRate = cMsg.A.no(idx).contrMtrMultRate.getValue();
        // END 2017/08/09 K.Kitachi [QC#19122, ADD]
        // mod start 2016/05/20 CSA Defect#4958
        if (!NSAL0320CommonLogic.isEqualStr(bllbFlg, asMsg.bllblFlg.getValue()) || (DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue()) && !ZYPCommonFunc.hasValue(asMsg.dsContrBllgMtrPk))) {
            if (NSAL0320CommonLogic.isEqualStr(bllbFlg, ZYPConstant.FLG_OFF_N)) {
                // bllblFlg Y -> N
                // Delete
                deleteData(cMsg, idx, sMsg, fltAggMachList, false);
                // update contract physical billing meter relation
                updateContrPhysBllgMtrReln(cMsg, idx, sMsg, fltAggMachList, updContrPhysBllgMtrRelnPkList);
            } else {
                // bllblFlg N -> Y
                // Insert
                createData(cMsg, idx, fltAggMachList, false);
                // update contract physical billing meter relation
                updateContrPhysBllgMtrReln(cMsg, idx, sMsg, fltAggMachList, updContrPhysBllgMtrRelnPkList);
            }

            // If machines added, need to create data.
            // START 2017/08/09 K.Kitachi [QC#19122, DEL]
            // addMachine(cMsg, idx, fltAggMachList);
            // END 2017/08/09 K.Kitachi [QC#19122, DEL]

            // mod start 2017/01/05 CSA QC#16399
            // START 2016/02/03 T.Kanasaka [QC#4029, MOD]
            // callContrBllgSchdApi(cMsg, idx);
            // END 2016/02/03 T.Kanasaka [QC#4029, MOD]
            // mod end 2017/01/05 CSA QC#16399
            // START 2017/08/09 K.Kitachi [QC#19122, DEL]
            // return;
            // }
            // END 2017/08/09 K.Kitachi [QC#19122, DEL]
            // mod end 2016/05/20 CSA Defect#4958
            // START 2016/01/07 T.Tomita [QC#2806, ADD]
            // START 2017/08/09 K.Kitachi [QC#19122, DEL]
            // String bllgMtrLbCd =
            // cMsg.A.no(idx).bllgMtrLbCd.getValue();
            // if (!NSAL0320CommonLogic.isEqualStr(bllgMtrLbCd,
            // asMsg.bllgMtrLbCd.getValue())) {
            // END 2017/08/09 K.Kitachi [QC#19122, DEL]
            // START 2017/08/09 K.Kitachi [QC#19122, ADD]
        } else if (!NSAL0320CommonLogic.isEqualStr(bllgMtrLbCd, asMsg.bllgMtrLbCd.getValue())) {
            // END 2017/08/09 K.Kitachi [QC#19122, ADD]
            // Delete
            deleteData(cMsg, idx, sMsg, fltAggMachList, true);
            // Insert
            createData(cMsg, idx, fltAggMachList, true);
            // START 2017/08/09 K.Kitachi [QC#19122, DEL]
            // return;
            // }
            // END 2017/08/09 K.Kitachi [QC#19122, DEL]
            // END 2016/01/07 T.Tomita [QC#2806, ADD]

            // START 2017/08/09 K.Kitachi [QC#19122, DEL]
            // BigDecimal contrMtrMultRate =
            // cMsg.A.no(idx).contrMtrMultRate.getValue();
            // if (!NSAL0320CommonLogic.isEqualNum(contrMtrMultRate,
            // asMsg.contrMtrMultRate.getValue())) {
            // END 2017/08/09 K.Kitachi [QC#19122, DEL]
            // START 2017/08/09 K.Kitachi [QC#19122, ADD]
        } else if (!NSAL0320CommonLogic.isEqualNum(contrMtrMultRate, asMsg.contrMtrMultRate.getValue())) {
            // END 2017/08/09 K.Kitachi [QC#19122, ADD]
            // update Multiplier
            updateContrPhysBllgMtrReln(cMsg, idx, sMsg, fltAggMachList, updContrPhysBllgMtrRelnPkList);

            // If machines added, need to create data.
            // START 2017/08/09 K.Kitachi [QC#19122, DEL]
            // addMachine(cMsg, idx, fltAggMachList);
            // END 2017/08/09 K.Kitachi [QC#19122, DEL]

            // mod start 2017/01/05 CSA QC#16399
            // START 2016/02/03 T.Kanasaka [QC#4029, MOD]
            // callContrBllgSchdApi(cMsg, idx);
            // END 2016/02/03 T.Kanasaka [QC#4029, MOD]
            // mod end 2017/01/05 CSA QC#16399
            // START 2017/08/09 K.Kitachi [QC#19122, DEL]
            // return;
            // END 2017/08/09 K.Kitachi [QC#19122, DEL]
        }

        // If machines added, need to create data.
        addMachine(cMsg, idx, fltAggMachList);
        // START 2017/08/09 K.Kitachi [QC#19122, ADD]
        callContrBllgSchdApiByContr(cMsg, idx, sMsg);
        // END 2017/08/09 K.Kitachi [QC#19122, ADD]
        // START 2022/07/22 E.Sanchez [QC#59804, MOD]
        // START 2022/06/22 E.Sanchez [QC#59804, ADD]
        if (!NSAL0320CommonLogic.isEqualStr(bllbFlg, asMsg.bllblFlg.getValue())) {
            clearStartRead(cMsg, sMsg, fltAggMachList);
        }
        // END 2022/06/22 E.Sanchez [QC#59804, ADD]
        // END 2022/07/22 E.Sanchez [QC#59804, MOD]
    }

    // END 2016/06/08 T.Kanasaka [QC#5005, ADD]
    // END 2016/03/14 T.Kanasaka [QC5005, MOD]

    // START 2016/03/14 T.Kanasaka [QC5005, MOD]
    // START 2016/06/08 T.Kanasaka [QC#5005, ADD]
    private void updateContrPhysBllgMtrReln(NSAL0320CMsg cMsg, int idx, NSAL0320SMsg sMsg, List<Map<String, Object>> fltAggMachList, List<BigDecimal> updContrPhysBllgMtrRelnPkList) {
        List<CONTR_PHYS_BLLG_MTR_RELNTMsg> updateRelnList = new ArrayList<CONTR_PHYS_BLLG_MTR_RELNTMsg>();
        NSAL0320Query query = NSAL0320Query.getInstance();
        String glblCmpyCd = getGlobalCompanyCode();

        String bllblFlg = cMsg.A.no(idx).bllblFlg.getValue();
        if (!ZYPCommonFunc.hasValue(bllblFlg)) {
            bllblFlg = ZYPConstant.FLG_OFF_N;
        }

        CONTR_PHYS_BLLG_MTR_RELNTMsg contrPhysBllgMtrReln = query.getContrPhysBllgMtrReln(glblCmpyCd, cMsg.A.no(idx).contrPhysBllgMtrRelnPk.getValue());
        if (contrPhysBllgMtrReln == null) {
            boolean isExist = false;
            for (int i = 0; i < idx; i++) {
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).contrPhysBllgMtrRelnPk) && cMsg.A.no(i).contrPhysBllgMtrRelnPk.getValue().compareTo(cMsg.A.no(idx).contrPhysBllgMtrRelnPk.getValue()) == 0) {
                    isExist = true;
                }
            }
            if (!isExist) {
                cMsg.setMessageInfo(NSAL0320Constant.NSAM0045E, new String[] {"Contract Physical Billing Meter Relation" });
                return;
            }
        } else if (!updContrPhysBllgMtrRelnPkList.contains(cMsg.A.no(idx).contrPhysBllgMtrRelnPk.getValue())) {
            if (!ZYPDateUtil.isSameTimeStamp(cMsg.A.no(idx).ezUpTime_AR.getValue(), cMsg.A.no(idx).ezUpTimeZone_AR.getValue(), contrPhysBllgMtrReln.ezUpTime.getValue(), contrPhysBllgMtrReln.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(NSAL0320Constant.ZZZM9004E);
                return;
            }

            if (ZYPConstant.FLG_OFF_N.equals(bllblFlg) && !fltAggMachList.isEmpty()) {
                if (!isExistsSameBllgMtr(cMsg, idx, sMsg.A.no(idx).bllgMtrLbCd.getValue())) {
                    EZDTBLAccessor.logicalRemove(contrPhysBllgMtrReln);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrReln.bllblFlg, bllblFlg);

                if (ZYPConstant.FLG_ON_Y.equals(bllblFlg)) {
                    ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrReln.bllgMtrLbCd, cMsg.A.no(idx).bllgMtrLbCd);
                    ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrReln.dsContrBllgMtrPk, cMsg.A.no(idx).dsContrBllgMtrPk);
                    ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrReln.bllgMtrLvlNum, cMsg.A.no(idx).bllgMtrMapLvlNum);
                    ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrReln.contrMtrMultRate, cMsg.A.no(idx).contrMtrMultRate);
                } else {
                    contrPhysBllgMtrReln.bllgMtrLbCd.clear();
                    contrPhysBllgMtrReln.dsContrBllgMtrPk.clear();
                    contrPhysBllgMtrReln.bllgMtrLvlNum.clear();
                    contrPhysBllgMtrReln.contrMtrMultRate.clear();
                }
                updateRelnList.add(contrPhysBllgMtrReln);
                updContrPhysBllgMtrRelnPkList.add(cMsg.A.no(idx).contrPhysBllgMtrRelnPk.getValue());
            }
        }

        CONTR_PHYS_BLLG_MTR_RELNTMsgArray contrPhysBllgMtrRelnList;
        BigDecimal dsContrDtlPk;
        BigDecimal svcPhysMtrPk;
        for (Map<String, Object> fltAggMach : fltAggMachList) {
            dsContrDtlPk = (BigDecimal) fltAggMach.get("DS_CONTR_DTL_PK");
            svcPhysMtrPk = (BigDecimal) fltAggMach.get("SVC_PHYS_MTR_PK");
            // mod start 2016/05/20 CSA Defect#4958
            contrPhysBllgMtrRelnList = query.getContrPhysBllgMtrRelnForUpdate(glblCmpyCd, dsContrDtlPk, svcPhysMtrPk);
            // mod end 2016/05/20 CSA Defect#4958
            for (int i = 0; i < contrPhysBllgMtrRelnList.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnList.no(i).bllblFlg, bllblFlg);
                if (ZYPConstant.FLG_ON_Y.equals(bllblFlg)) {
                    ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnList.no(i).contrMtrMultRate, cMsg.A.no(idx).contrMtrMultRate);
                } else {
                    contrPhysBllgMtrRelnList.no(i).bllgMtrLbCd.clear();
                    contrPhysBllgMtrRelnList.no(i).dsContrBllgMtrPk.clear();
                    contrPhysBllgMtrRelnList.no(i).bllgMtrLvlNum.clear();
                    contrPhysBllgMtrRelnList.no(i).contrMtrMultRate.clear();
                }
                updateRelnList.add(contrPhysBllgMtrRelnList.no(i));
            }
        }

        if (updateRelnList.size() == 0) {
            return;
        }

        int updCnt = S21FastTBLAccessor.update(updateRelnList.toArray(new CONTR_PHYS_BLLG_MTR_RELNTMsg[updateRelnList.size()]));
        if (updCnt != updateRelnList.size()) {
            cMsg.setMessageInfo(NSAL0320Constant.NSAM0031E, new String[] {"Contract Physical Billing Meter Relation" });
        }
    }

    // END 2016/06/08 T.Kanasaka [QC#5005, ADD]
    // END 2016/03/14 T.Kanasaka [QC5005, MOD]

    // START 2016/03/14 T.Kanasaka [QC5005, MOD]
    // START 2016/06/08 T.Kanasaka [QC#5005, ADD]
    private void deleteData(NSAL0320CMsg cMsg, int idx, NSAL0320SMsg sMsg, List<Map<String, Object>> fltAggMachList, boolean delRelnFlg) {
        NSAL0320Query query = NSAL0320Query.getInstance();
        String glblCmpyCd = getGlobalCompanyCode();
        int i;
        boolean sameBllgMtrFlg = false;

        // START 2016/01/07 T.Tomita [QC#2806, MOD]
        CONTR_XS_COPYTMsgArray contrXsCopyList;
        if (ZYPCommonFunc.hasValue(cMsg.A.no(idx).dsContrBllgMtrPk)) {
            sameBllgMtrFlg = isExistsSameBllgMtr(cMsg, idx, sMsg.A.no(idx).bllgMtrLbCd.getValue());
            // START 2020/09/24 K.Kitachi [QC#57667, MOD]
            // // mod QC#55922 Start
            // if (!sameBllgMtrFlg && !samePhysMtr) {
            // // if (!sameBllgMtrFlg) {
            // // mod QC#55922 End
            if (!sameBllgMtrFlg) {
                // END 2020/09/24 K.Kitachi [QC#57667, MOD]
                // DS_CONTR_BLLG_MTR
                DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTmsg = query.getDsContrBllgMtr(glblCmpyCd, cMsg.A.no(idx).dsContrBllgMtrPk.getValue());
                if (dsContrBllgMtrTmsg != null) {
                    if (!ZYPDateUtil.isSameTimeStamp(cMsg.A.no(idx).ezUpTime_AB.getValue(), cMsg.A.no(idx).ezUpTimeZone_AB.getValue(), dsContrBllgMtrTmsg.ezUpTime.getValue(), dsContrBllgMtrTmsg.ezUpTimeZone.getValue())) {
                        cMsg.setMessageInfo(NSAL0320Constant.ZZZM9004E);
                        return;
                    }
                    EZDTBLAccessor.logicalRemove(dsContrBllgMtrTmsg);

                    // CONTR_XS_COPY
                    contrXsCopyList = query.getContrXsCopy(glblCmpyCd, cMsg.A.no(idx).dsContrBllgMtrPk.getValue());
                    for (i = 0; i < contrXsCopyList.getValidCount(); i++) {
                        EZDTBLAccessor.logicalRemove(contrXsCopyList.no(i));
                    }
                }
            }
        }
        // END 2016/01/07 T.Tomita [QC#2806, MOD]

        if (delRelnFlg) {
            // START 2017/08/30 T.Kanasaka [QC#20536,MOD]
            if (DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue()) || !sameBllgMtrFlg) {
                // CONTR_PHYS_BLLG_MTR_RELN
                CONTR_PHYS_BLLG_MTR_RELNTMsg contrPhysBllgMtrReln = query.getContrPhysBllgMtrReln(glblCmpyCd, cMsg.A.no(idx).contrPhysBllgMtrRelnPk.getValue());
                // mod start 2016/05/25 CSA Defect#4958
                if (contrPhysBllgMtrReln != null) {
                    if (!ZYPDateUtil.isSameTimeStamp(cMsg.A.no(idx).ezUpTime_AR.getValue(), cMsg.A.no(idx).ezUpTimeZone_AR.getValue(), contrPhysBllgMtrReln.ezUpTime.getValue(), contrPhysBllgMtrReln.ezUpTimeZone.getValue())) {
                        cMsg.setMessageInfo(NSAL0320Constant.ZZZM9004E);
                        return;
                    }
                    EZDTBLAccessor.logicalRemove(contrPhysBllgMtrReln);
                }
                // mod end 2016/05/25 CSA Defect#4958
            }
            // END 2017/08/30 T.Kanasaka [QC#20536,MOD]
        }

        BigDecimal dsContrDtlPk;
        BigDecimal svcPhysMtrPk;
        DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrList;
        CONTR_PHYS_BLLG_MTR_RELNTMsgArray contrPhysBllgMtrRelnList;
        for (Map<String, Object> fltAggMach : fltAggMachList) {
            dsContrDtlPk = (BigDecimal) fltAggMach.get("DS_CONTR_DTL_PK");
            svcPhysMtrPk = (BigDecimal) fltAggMach.get("SVC_PHYS_MTR_PK");

            if (!sameBllgMtrFlg) {
                // DS_CONTR_BLLG_MTR
                dsContrBllgMtrList = query.getDsContrBllgMtrForUpdate(glblCmpyCd, dsContrDtlPk, sMsg.A.no(idx).bllgMtrLbCd.getValue());
                for (i = 0; i < dsContrBllgMtrList.getValidCount(); i++) {
                    EZDTBLAccessor.logicalRemove(dsContrBllgMtrList.no(i));
                }

                // CONTR_XS_COPY
                for (int x = 1; x < dsContrBllgMtrList.getValidCount(); x++) {
                    contrXsCopyList = query.getContrXsCopy(glblCmpyCd, dsContrBllgMtrList.no(x).dsContrBllgMtrPk.getValue());
                    for (i = 0; i < contrXsCopyList.getValidCount(); i++) {
                        EZDTBLAccessor.logicalRemove(contrXsCopyList.no(i));
                    }
                }
            }

            // mod start 2017/02/01 CSA QC#17337
            // if (delRelnFlg) {
            // CONTR_PHYS_BLLG_MTR_RELN
            contrPhysBllgMtrRelnList = query.getContrPhysBllgMtrRelnForUpdate(glblCmpyCd, dsContrDtlPk, sMsg.A.no(idx).bllgMtrLbCd.getValue(), svcPhysMtrPk);
            for (i = 0; i < contrPhysBllgMtrRelnList.getValidCount(); i++) {
                EZDTBLAccessor.logicalRemove(contrPhysBllgMtrRelnList.no(i));
            }
            // }
            // mod end 2017/02/01 CSA QC#17337
        }
    }

    // END 2016/06/08 T.Kanasaka [QC#5005, ADD]
    // END 2016/03/14 T.Kanasaka [QC5005, MOD]

    // START 2016/02/03 T.Kanasaka [QC#4029, MOD]
    // START 2017/08/09 K.Kitachi [QC#19122, MOD]
    // private void callContrBllgSchdApi(NSAL0320CMsg cMsg, int idx,
    // NSAL0320SMsg sMsg) {
    // START 2022/10/25 E.Sanchez [QC#60661, MOD]
    // private void callContrBllgSchdApiByDelOrRstBllg(NSAL0320CMsg cMsg, int idx, NSAL0320SMsg sMsg) {
    private void callContrBllgSchdApiByDelOrRstBllg(NSAL0320CMsg cMsg, int idx, NSAL0320SMsg sMsg, List<Map<String, Object>> fltAggMachList) {
    // END 2022/10/25 E.Sanchez [QC#60661, MOD]
        // END 2017/08/09 K.Kitachi [QC#19122, MOD]
        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // mod start 2016/12/15 CSA QC#16399
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg.getValue())) {
            // START 2020/09/24 K.Kitachi [QC#57667, MOD]
            // // mod QC#55922 Start
            // // if (!prcEffDelFlg) {
            // if (!prcEffDelFlg && !samePhysMtr) {
            // // mod QC#55922 End
            // if (!callApiByDelMode(cMsg, glblCmpyCd, slsDt)) {
            // return;
            // }
            // prcEffDelFlg = true;
            // }
            if (!this.deleteBllgMtrList.contains(sMsg.A.no(idx).bllgMtrLbCd.getValue())) {
                return;
            }
            if (this.delCpltBllgMtrList.contains(sMsg.A.no(idx).bllgMtrLbCd.getValue())) {
                return;
            }
            if (!callApiByDelMode(cMsg, glblCmpyCd, slsDt, idx, sMsg)) {
                return;
            }
            // START 2022/10/25 E.Sanchez [QC#60661, ADD]
            if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue()) && !callApiByDeleteModeForAggregateMachLine(cMsg, glblCmpyCd, slsDt, fltAggMachList)) {
                return;
            }
            // END 2022/10/25 E.Sanchez [QC#60661, ADD]
            this.delCpltBllgMtrList.add(sMsg.A.no(idx).bllgMtrLbCd.getValue());
            // END 2020/09/24 K.Kitachi [QC#57667, MOD]
            // START 2017/08/09 K.Kitachi [QC#19122, DEL]
            // if
            // (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(idx).bllblFlg.getValue()))
            // {
            // if
            // (cMsg.A.no(idx).bllgMtrLbCd.getValue().equals(sMsg.A.no(idx).bllgMtrLbCd.getValue()))
            // {
            // if
            // (!bllgMtrLbCdLst.contains(cMsg.A.no(idx).bllgMtrLbCd.getValue()))
            // {
            // callApiByContrMode(cMsg, idx, glblCmpyCd, slsDt);
            // bllgMtrLbCdLst.add(cMsg.A.no(idx).bllgMtrLbCd.getValue());
            // }
            // }
            // }
            // END 2017/08/09 K.Kitachi [QC#19122, DEL]
        } else {
            callApiByRstBllgMode(cMsg, idx, glblCmpyCd, slsDt);
        }
        // mod end 2016/12/15 CSA QC#16399
    }

    // END 2016/02/03 T.Kanasaka [QC#4029, MOD]

    // START 2017/08/09 K.Kitachi [QC#19122, ADD]
    private void callContrBllgSchdApiByContr(NSAL0320CMsg cMsg, int idx, NSAL0320SMsg sMsg) {

        // START 2020/09/24 K.Kitachi [QC#57667, ADD]
        if (!this.createBllgMtrList.contains(cMsg.A.no(idx).bllgMtrLbCd.getValue())) {
            return;
        }
        // END 2020/09/24 K.Kitachi [QC#57667, ADD]

        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(idx).bllblFlg.getValue())) {
                if (cMsg.A.no(idx).bllgMtrLbCd.getValue().equals(sMsg.A.no(idx).bllgMtrLbCd.getValue())) {
                    if (!this.bllgMtrLbCdLst.contains(cMsg.A.no(idx).bllgMtrLbCd.getValue())) {
                        callApiByContrMode(cMsg, idx, glblCmpyCd, slsDt);
                        this.bllgMtrLbCdLst.add(cMsg.A.no(idx).bllgMtrLbCd.getValue());
                    }
                }
            }
        }
    }

    // END 2017/08/09 K.Kitachi [QC#19122, ADD]

    // START 2020/09/24 K.Kitachi [QC#57667, MOD]
    // private boolean callApiByDelMode(NSAL0320CMsg cMsg, String
    // glblCmpyCd, String slsDt) {
    private boolean callApiByDelMode(NSAL0320CMsg cMsg, String glblCmpyCd, String slsDt, int idx, NSAL0320SMsg sMsg) {
        // END 2020/09/24 K.Kitachi [QC#57667, MOD]

        // mod start 2017/01/04 CSA QC#16399
        NSZC047010PMsg pMsg = new NSZC047010PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "10");
        // mod end 2017/01/04 CSA QC#16399
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, cMsg.dsContrDtlPk.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.delFlg, ZYPConstant.FLG_ON_Y);
        // START 2020/09/24 K.Kitachi [QC#57667, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrBllgMtrPk, sMsg.A.no(idx).dsContrBllgMtrPk);
        // END 2020/09/24 K.Kitachi [QC#57667, ADD]

        NSZC047001 api = new NSZC047001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            cMsg.setMessageInfo(NSAL0320Constant.NSAM0003E, new String[] {"NSZC047001", pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), "DS_CONTR_DTL_PK=" + cMsg.dsContrDtlPk.getValue() });
            return false;
        }
        return true;
    }

    // START 2016/01/07 T.Tomita [QC#2806, MOD]
    // START 2016/02/03 T.Kanasaka [QC#4029, MOD]
    private void callApiByContrMode(NSAL0320CMsg cMsg, int idx, String glblCmpyCd, String slsDt) {
        NSZC047001PMsg pMsg = setApiTmsgByContrMode(cMsg, idx, glblCmpyCd, slsDt);

        if (pMsg == null || pMsg.xxMtrLineList.getValidCount() == 0) {
            return;
        }

        NSZC047001 api = new NSZC047001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            cMsg.setMessageInfo(NSAL0320Constant.NSAM0003E, new String[] {"NSZC047001", pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), "DS_CONTR_DTL_PK=" + cMsg.dsContrDtlPk.getValue() });
            return;
        }
    }

    // END 2016/02/03 T.Kanasaka [QC#4029, MOD]
    // END 2016/01/07 T.Tomita [QC#2806, MOD]

    // START 2016/02/03 T.Kanasaka [QC#4029, MOD]
    private NSZC047001PMsg setApiTmsgByContrMode(NSAL0320CMsg cMsg, int idx, String glblCmpyCd, String slsDt) {
        NSZC047001PMsg pMsg = new NSZC047001PMsg();
        NSAL0320Query query = NSAL0320Query.getInstance();
        S21SsmEZDResult rslt = query.getScheduleApiParams(glblCmpyCd, cMsg.dsContrDtlPk.getValue(), cMsg.A.no(idx).dsContrBllgMtrPk.getValue());
        if (rslt == null || !rslt.isCodeNormal()) {
            return null;
        }
        List<Map<String, Object>> rsltMapList = (List<Map<String, Object>>) rslt.getResultObject();
        if (rsltMapList.size() == 0) {
            return null;
        }

        // Set Parameters
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "01");
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, (BigDecimal) rsltMapList.get(0).get("DS_CONTR_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(pMsg.baseChrgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.usgChrgFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.contrCloDay, (String) rsltMapList.get(0).get("CONTR_CLO_DAY"));
        ZYPEZDItemValueSetter.setValue(pMsg.baseBllgTmgCd, (String) rsltMapList.get(0).get("BASE_BLLG_TMG_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.contrBllgDay, (String) rsltMapList.get(0).get("CONTR_BLLG_DAY"));
        ZYPEZDItemValueSetter.setValue(pMsg.mtrCloDay, (String) rsltMapList.get(0).get("MTR_CLO_DAY"));
        ZYPEZDItemValueSetter.setValue(pMsg.mtrBllgTmgCd, (String) rsltMapList.get(0).get("MTR_BLLG_TMG_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.mtrBllgDay, (String) rsltMapList.get(0).get("MTR_BLLG_DAY"));
        ZYPEZDItemValueSetter.setValue(pMsg.contrEffFromDt, (String) rsltMapList.get(0).get("CONTR_EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, (String) rsltMapList.get(0).get("CONTR_EFF_THRU_DT"));
        int i = 0;
        for (Map<String, Object> rsltMap : rsltMapList) {
            if (!ZYPCommonFunc.hasValue((BigDecimal) rsltMap.get("XS_MTR_COPY_QTY")) || !ZYPCommonFunc.hasValue((BigDecimal) rsltMap.get("XS_MTR_AMT_RATE"))) {
                continue;
            }

            ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(i).mtrBllgCycleCd_ML, (String) rsltMap.get("MTR_BLLG_CYCLE_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(i).dsContrBllgMtrPk_ML, (BigDecimal) rsltMap.get("DS_CONTR_BLLG_MTR_PK"));
            ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(i).contrXsCopyPk_ML, (BigDecimal) rsltMap.get("CONTR_XS_COPY_PK"));
            ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(i).xsMtrCopyQty_ML, (BigDecimal) rsltMap.get("XS_MTR_COPY_QTY"));
            ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(i).xsMtrAmtRate_ML, (BigDecimal) rsltMap.get("XS_MTR_AMT_RATE"));
            if (ZYPCommonFunc.hasValue((String) rsltMap.get("XS_MTR_FIRST_FLG"))) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(i).xsMtrFirstFlg_ML, (String) rsltMap.get("XS_MTR_FIRST_FLG"));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(i).xsMtrFirstFlg_ML, ZYPConstant.FLG_OFF_N);
            }
            i++;
        }
        pMsg.xxMtrLineList.setValidCount(i);
        return pMsg;
    }

    // END 2016/02/03 T.Kanasaka [QC#4029, MOD]
    // END 2015/10/13 T.Tomita [N/A, ADD]

    // START 2016/03/14 T.Kanasaka [QC5005, ADD]
    private void removeUnBllbRelnForFltAgg(NSAL0320CMsg cMsg) {
        if (DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue())) {
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        NSAL0320Query query = NSAL0320Query.getInstance();
        S21SsmEZDResult rslt = query.getUnBllbRelnForFltAgg(glblCmpyCd, cMsg.dsContrPk.getValue());
        if (rslt == null || !rslt.isCodeNormal()) {
            return;
        }
        List<BigDecimal> rsltList = (List<BigDecimal>) rslt.getResultObject();
        if (rsltList.size() == 0) {
            return;
        }
        for (BigDecimal contrPhysBllgMtrRelnPk : rsltList) {
            CONTR_PHYS_BLLG_MTR_RELNTMsg tMsg = query.getContrPhysBllgMtrReln(glblCmpyCd, contrPhysBllgMtrRelnPk);
            if (tMsg == null) {
                cMsg.setMessageInfo(NSAL0320Constant.NSAM0045E, new String[] {"Contract Physical Billing Meter Relation" });
                return;
            }
            EZDTBLAccessor.logicalRemove(tMsg);
        }
    }

    // START 2016/06/08 T.Kanasaka [QC#5005, MOD]
    private void addMachine(NSAL0320CMsg cMsg, int idx, List<Map<String, Object>> fltAggMachList) {
        NSAL0320Query query = NSAL0320Query.getInstance();
        CONTR_PHYS_BLLG_MTR_RELNTMsgArray contrPhysBllgMtrRelnTMsgArray = null;
        DS_CONTR_DTLTMsg dsContrDtlTmsg = null;
        DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTmsg = null;
        CONTR_XS_COPYTMsg contrXsCopyTMsg = null;
        CONTR_PHYS_BLLG_MTR_RELNTMsg contrPhysBllgMtrRelnTmsg = null;
        String glblCmpyCd = getGlobalCompanyCode();
        String dsContrDtlStsCd = null;
        String bllgMtrLbCd = cMsg.A.no(idx).bllgMtrLbCd.getValue();
        BigDecimal dsContrDtlPk = null;
        BigDecimal svcPhysMtrPk = null;
        BigDecimal svcMachMstrPk = null;
        BigDecimal dsContrBllgMtrPk = null;
        List<CONTR_XS_COPYTMsg> contrXsCopyTmsgList = null;
        List<Map<String, Object>> contrXsCopyForAggList = null;
        String bllbFlg = cMsg.A.no(idx).bllblFlg.getValue();

        for (Map<String, Object> fltAggMach : fltAggMachList) {
            dsContrDtlPk = (BigDecimal) fltAggMach.get("DS_CONTR_DTL_PK");
            svcPhysMtrPk = (BigDecimal) fltAggMach.get("SVC_PHYS_MTR_PK");
            svcMachMstrPk = (BigDecimal) fltAggMach.get("SVC_MACH_MSTR_PK");

            contrPhysBllgMtrRelnTMsgArray = query.getContrPhysBllgMtrRelnForUpdate(glblCmpyCd, dsContrDtlPk, bllgMtrLbCd, svcPhysMtrPk);
            if (contrPhysBllgMtrRelnTMsgArray.getValidCount() == 0) {
                if (ZYPConstant.FLG_ON_Y.equals(bllbFlg)) {
                    dsContrBllgMtrPk = null;
                    S21SsmEZDResult rslt = query.getDsContrBllgMtrPk(glblCmpyCd, dsContrDtlPk, bllgMtrLbCd);
                    if (rslt != null && rslt.isCodeNormal()) {
                        dsContrBllgMtrPk = (BigDecimal) rslt.getResultObject();
                    }
                    if (!ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
                        // insert DS_CONTR_BLLG_MTR
                        dsContrDtlTmsg = query.getDsContrDtl(glblCmpyCd, dsContrDtlPk);
                        if (dsContrDtlTmsg != null) {
                            dsContrDtlStsCd = dsContrDtlTmsg.dsContrDtlStsCd.getValue();
                        }
                        // START 2017/02/09 K.Kojima [QC#17522,MOD]
                        // dsContrBllgMtrTmsg =
                        // setDsContrBllgMtrTmsg(dsContrDtlPk,
                        // bllgMtrLbCd, dsContrDtlStsCd);
                        dsContrBllgMtrTmsg = setDsContrBllgMtrTmsg(dsContrDtlPk, bllgMtrLbCd, dsContrDtlStsCd, dsContrDtlTmsg);
                        // END 2017/02/09 K.Kojima [QC#17522,MOD]
                        S21FastTBLAccessor.insert(dsContrBllgMtrTmsg);
                        dsContrBllgMtrPk = dsContrBllgMtrTmsg.dsContrBllgMtrPk.getValue();

                        if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
                            // insert CONTR_XS_COPY
                            rslt = query.getContrXsCopyForAgg(glblCmpyCd, cMsg.dsContrDtlPk.getValue(), bllgMtrLbCd);
                            if (rslt != null && rslt.isCodeNormal()) {
                                contrXsCopyTmsgList = new ArrayList<CONTR_XS_COPYTMsg>();
                                contrXsCopyForAggList = (List<Map<String, Object>>) rslt.getResultObject();
                                for (Map<String, Object> contrXsCopyForAggMap : contrXsCopyForAggList) {
                                    contrXsCopyTMsg = new CONTR_XS_COPYTMsg();
                                    ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.glblCmpyCd, glblCmpyCd);
                                    ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.contrXsCopyPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_XS_COPY_SQ));
                                    ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
                                    // ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.xsMtrCopyQty,
                                    // BigDecimal.ZERO);
                                    ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.xsMtrAmtRate, (BigDecimal) contrXsCopyForAggMap.get("XS_MTR_AMT_RATE"));
                                    ZYPEZDItemValueSetter.setValue(contrXsCopyTMsg.xsMtrFirstFlg, (String) contrXsCopyForAggMap.get("XS_MTR_FIRST_FLG"));
                                    contrXsCopyTmsgList.add(contrXsCopyTMsg);
                                }

                                if (contrXsCopyTmsgList.size() > 0) {
                                    int insCnt = S21FastTBLAccessor.insert(contrXsCopyTmsgList.toArray(new CONTR_XS_COPYTMsg[contrXsCopyTmsgList.size()]));
                                    if (insCnt != contrXsCopyTmsgList.size()) {
                                        cMsg.setMessageInfo(NSAL0320Constant.NSAM0032E, new String[] {"Contract Excess Copy" });
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }

                // insert CONTR_PHYS_BLLG_MTR_RELN
                contrPhysBllgMtrRelnTmsg = buildInsertContrPhysBllgMtrReln(cMsg, idx, dsContrDtlPk, dsContrBllgMtrPk, svcPhysMtrPk, svcMachMstrPk);
                S21FastTBLAccessor.insert(contrPhysBllgMtrRelnTmsg);
            }
        }
    }

    // END 2016/06/08 T.Kanasaka [QC#5005, MOD]

    private boolean isExistsSameBllgMtr(NSAL0320CMsg cMsg, int idx, String bllgMtrLbCd) {
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (i == idx) {
                continue;
            }

            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).bllblFlg.getValue())) {
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).bllgMtrLbCd) && cMsg.A.no(i).bllgMtrLbCd.getValue().equals(bllgMtrLbCd)) {
                    return true;
                }
            }
        }
        return false;
    }

    // END 2016/03/14 T.Kanasaka [QC5005, ADD]

    // START 2016/06/01 T.Kanasaka [QC#3964, ADD]
    private void setBllgMtrMapLvlNum(NSAL0320CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).bllblFlg.getValue())) {
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).bllgMtrLbCd)) {
                    // mod start 2016/07/26 CSA Defect#12106
                    String bllgMtrMapLvlNum = NSAL0320CommonLogic.getBllgMtrMapLvlNum(glblCmpyCd, cMsg.A.no(i).mdlMtrLbCd.getValue(), cMsg.A.no(i).bllgMtrLbCd.getValue(), cMsg.dsContrCatgCd.getValue(), slsDt, cMsg.dsContrDtlPk.getValue(),
                            cMsg.prcMtrPkgPk_B.getValue());
                    // mod end 2016/07/26 CSA Defect#12106
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).bllgMtrMapLvlNum, bllgMtrMapLvlNum);
                }
            }
        }
    }

    // END 2016/06/01 T.Kanasaka [QC#3964, ADD]
    // add start 2016/06/07 CSA Defect#1523, 4624
    private boolean callContrTrkAPI(NSAL0320CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String userId = getContextUserInfo().getUserId();
        BigDecimal dsContrPk = cMsg.dsContrPk.getValue();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        String stsMemoRsnCd = DS_CONTR_TRK_RSN.CONTRACT_MODE_CHANGE;
        if (!NSXC001001ContractTracking.callContrTrk(glblCmpyCd, ContrTrkProcMode.USER_OPERATION.code, dsContrPk, userId, slsDt, stsMemoRsnCd, null, ONBATCH_TYPE.ONLINE)) {
            cMsg.setMessageInfo(NSXC001001ContractTracking.ERR_MSG_ID);
            return false;
        }
        return true;
    }

    // add end 2016/06/07 CSA Defect#1523, 4624
    // add start 2016/12/15 CSA QC#16399
    private void callApiByRstBllgMode(NSAL0320CMsg cMsg, int idx, String glblCmpyCd, String slsDt) {
        List<NSZC047022PMsg> pMsgLst = setApiTmsgByRstBllgMode(cMsg, idx, glblCmpyCd, slsDt);

        if (pMsgLst == null || pMsgLst.size() == 0) {
            return;
        }

        NSZC047001 api = new NSZC047001();
        for (NSZC047022PMsg pMsg : pMsgLst) {
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (pMsg.xxMsgIdList.getValidCount() != 0) {
                cMsg.setMessageInfo(NSAL0320Constant.NSAM0003E, new String[] {"NSZC047001", pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), "DS_CONTR_DTL_PK=" + cMsg.dsContrDtlPk.getValue() });
                return;
            }
        }
    }

    private List<NSZC047022PMsg> setApiTmsgByRstBllgMode(NSAL0320CMsg cMsg, int idx, String glblCmpyCd, String slsDt) {
        List<NSZC047022PMsg> pMsgList = new ArrayList<NSZC047022PMsg>();
        NSAL0320Query query = NSAL0320Query.getInstance();
        S21SsmEZDResult rslt = query.getDsContrBllgSchdPk(glblCmpyCd, cMsg.dsContrDtlPk.getValue());
        if (rslt == null || !rslt.isCodeNormal()) {
            return null;
        }
        List<BigDecimal> rsltList = (List<BigDecimal>) rslt.getResultObject();
        if (rsltList.size() == 0) {
            return null;
        }
        for (BigDecimal contrPhysBllgMtrRelnPk : rsltList) {
            NSZC047022PMsg pMsg = new NSZC047022PMsg();
            // Set Parameters
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NSAL0320Constant.MODE_22);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrCatgCd, cMsg.dsContrCatgCd);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrBllgSchdPk, contrPhysBllgMtrRelnPk);
            if (!DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.prntDsContrBllgSchdPk, contrPhysBllgMtrRelnPk);
            }
            pMsgList.add(pMsg);
        }

        return pMsgList;
    }

    // add end 2016/12/15 CSA QC#16399

    // START 2018/07/09 K.Kitachi [QC#26834, ADD]
    private void removeLineMtrNotExstMachMtrInfoForFltAgg(NSAL0320CMsg cMsg) {
        if (DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue())) {
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        NSAL0320Query query = NSAL0320Query.getInstance();
        List<Map<String, Object>> mapList = query.getLineMtrNotExstMachMtrInfo(glblCmpyCd, cMsg.dsContrPk.getValue(), cMsg.dsContrCatgCd.getValue());
        for (Map<String, Object> map : mapList) {

            BigDecimal dsContrDtlPk = (BigDecimal) map.get("DS_CONTR_DTL_PK");
            BigDecimal dsContrBllgMtrPk = (BigDecimal) map.get("DS_CONTR_BLLG_MTR_PK");
            BigDecimal contrPhysBllgMtrRelnPk = (BigDecimal) map.get("CONTR_PHYS_BLLG_MTR_RELN_PK");

            CONTR_PHYS_BLLG_MTR_RELNTMsg contrPhysBllgMtrReln = query.getContrPhysBllgMtrReln(glblCmpyCd, contrPhysBllgMtrRelnPk);
            if (contrPhysBllgMtrReln == null) {
                cMsg.setMessageInfo(NSAL0320Constant.NSAM0045E, new String[] {"Contract Physical Billing Meter Relation" });
                return;
            }
            EZDTBLAccessor.logicalRemove(contrPhysBllgMtrReln);

            DS_CONTR_BLLG_MTRTMsg dsContrBllgMtr = query.getDsContrBllgMtr(glblCmpyCd, dsContrBllgMtrPk);
            if (dsContrBllgMtr == null) {
                cMsg.setMessageInfo(NSAL0320Constant.NSAM0045E, new String[] {"DS Contract Billing Meter" });
                return;
            }

            CONTR_XS_COPYTMsgArray contrXsCopyList = query.getContrXsCopy(glblCmpyCd, dsContrBllgMtrPk);
            for (int i = 0; i < contrXsCopyList.getValidCount(); i++) {
                EZDTBLAccessor.logicalRemove(contrXsCopyList.no(i));
            }

            DS_CONTR_BLLG_MTR_USEDTMsgArray dsContrBllgMtrUsedList = query.getDsContrBllgMtrUsedForUpdate(glblCmpyCd, dsContrBllgMtrPk);
            for (int i = 0; i < dsContrBllgMtrUsedList.getValidCount(); i++) {
                EZDTBLAccessor.logicalRemove(dsContrBllgMtrUsedList.no(i));
            }

            DS_CONTR_PRC_EFFTMsgArray dsContrPrcEffList = query.getDsContrPrcEffForUpdate(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk);
            for (int i = 0; i < dsContrPrcEffList.getValidCount(); i++) {

                BigDecimal dsContrPrcEffPk = dsContrPrcEffList.no(i).dsContrPrcEffPk.getValue();

                DS_CONTR_PRC_EFF_MTRTMsgArray dsContrPrcEffMtrList = query.getDsContrPrcEffMtrForUpdate(glblCmpyCd, dsContrBllgMtrPk, dsContrPrcEffPk);
                for (int j = 0; j < dsContrPrcEffMtrList.getValidCount(); j++) {
                    EZDTBLAccessor.logicalRemove(dsContrPrcEffMtrList.no(j));
                }

                DS_CONTR_BLLG_SCHD_SMRYTMsgArray dsContrBllgSchdSmryList = query.getDsContrBllgSchdSmryForUpdate(glblCmpyCd, dsContrPrcEffPk);
                for (int j = 0; j < dsContrBllgSchdSmryList.getValidCount(); j++) {

                    BigDecimal dsContrBllgSchdSmryPk = dsContrBllgSchdSmryList.no(j).dsContrBllgSchdSmryPk.getValue();

                    DS_CONTR_BLLG_SCHD_MTRTMsgArray dsContrBllgSchdMtrList = query.getDsContrBllgSchdMtrForUpdate(glblCmpyCd, dsContrBllgMtrPk, dsContrBllgSchdSmryPk);
                    for (int k = 0; k < dsContrBllgSchdMtrList.getValidCount(); k++) {
                        EZDTBLAccessor.logicalRemove(dsContrBllgSchdMtrList.no(k));
                    }

                    EZDTBLAccessor.logicalRemove(dsContrBllgSchdSmryList.no(j));
                }

                DS_CONTR_BLLG_SCHDTMsgArray dsContrBllgSchdList = query.getDsContrBllgSchdForUpdate(glblCmpyCd, dsContrPrcEffPk, ZYPConstant.FLG_OFF_N);
                for (int j = 0; j < dsContrBllgSchdList.getValidCount(); j++) {
                    EZDTBLAccessor.logicalRemove(dsContrBllgSchdList.no(j));
                }

                EZDTBLAccessor.logicalRemove(dsContrPrcEffList.no(i));
            }

            List<BigDecimal> svcContrBllgPkList = query.getLineMtrNotExstMachMtrBllgPk(glblCmpyCd, dsContrBllgMtrPk);
            for (BigDecimal svcContrBllgPk : svcContrBllgPkList) {

                SVC_CONTR_BLLGTMsg svcContrBllg = query.getSvcContrBllg(glblCmpyCd, svcContrBllgPk);
                if (svcContrBllg == null) {
                    cMsg.setMessageInfo(NSAL0320Constant.NSAM0045E, new String[] {"Service Contract Billing" });
                    return;
                }

                SVC_CONTR_MTR_BLLGTMsgArray svcContrMtrBllgList = query.getSvcContrMtrBllgForUpdate(glblCmpyCd, svcContrBllgPk);
                for (int i = 0; i < svcContrMtrBllgList.getValidCount(); i++) {

                    BigDecimal svcContrMtrBllgPk = svcContrMtrBllgList.no(i).svcContrMtrBllgPk.getValue();

                    SVC_CONTR_XS_MTR_BLLGTMsgArray svcContrXsMtrBllgList = query.getSvcContrXsMtrBllgForUpdate(glblCmpyCd, svcContrMtrBllgPk);
                    for (int j = 0; j < svcContrXsMtrBllgList.getValidCount(); j++) {
                        EZDTBLAccessor.logicalRemove(svcContrXsMtrBllgList.no(j));
                    }

                    SVC_CONTR_BLLG_ALLOCTMsgArray svcContrBllgAllocList = query.getSvcContrBllgAllocForUpdate(glblCmpyCd, svcContrMtrBllgPk);
                    for (int j = 0; j < svcContrBllgAllocList.getValidCount(); j++) {
                        EZDTBLAccessor.logicalRemove(svcContrBllgAllocList.no(j));
                    }

                    EZDTBLAccessor.logicalRemove(svcContrMtrBllgList.no(i));
                }

                SVC_CONTR_ADDL_CHRG_BLLGTMsgArray svcContrAddlChrgBllgList = query.getSvcContrAddlChrgBllgForUpdate(glblCmpyCd, svcContrBllgPk);
                for (int i = 0; i < svcContrAddlChrgBllgList.getValidCount(); i++) {
                    EZDTBLAccessor.logicalRemove(svcContrAddlChrgBllgList.no(i));
                }

                EZDTBLAccessor.logicalRemove(svcContrBllg);
            }

            EZDTBLAccessor.logicalRemove(dsContrBllgMtr);
        }
    }

    // END 2018/07/09 K.Kitachi [QC#26834, ADD]

    // START 2020/09/24 K.Kitachi [QC#57667, DEL]
    // // QC#55922 Add Start
    // /**
    // * isSamePhysMtr
    // */
    // private boolean isSamePhysMtr(NSAL0320CMsg cMsg) {
    // List<Map<String, Object>> rtnLst = new ArrayList<Map<String,
    // Object>>();
    // BigDecimal dsContrDtlPk = cMsg.dsContrDtlPk.getValue();
    // NSAL0320Query query = NSAL0320Query.getInstance();
    // List<Map<String, Object>> physBllgMtrRelnLst =
    // query.getContrPhysBllgMtrRelnByContrDtlPk(getGlobalCompanyCode(),
    // dsContrDtlPk);
    // if (physBllgMtrRelnLst != null && physBllgMtrRelnLst.size() >
    // 0) {
    // for (int i =0; i <physBllgMtrRelnLst.size(); i++) {
    // String svcPhysMtrReln = (String)
    // physBllgMtrRelnLst.get(i).get("BLLG_MTR_LB_CD") ;
    // String svcPhysMtr = cMsg.A.no(i).bllgMtrLbCd.getValue();
    // if (!ZYPCommonFunc.hasValue(svcPhysMtrReln)) {
    // svcPhysMtrReln = " ";
    // }
    // if (!ZYPCommonFunc.hasValue(svcPhysMtr)) {
    // svcPhysMtr = " ";
    // }
    // if (svcPhysMtrReln.equals(svcPhysMtr)) {
    // continue;
    // } else {
    // return false;
    // }
    // }
    // } else {
    // return false;
    // }
    //     
    // return true;
    // }
    // // QC#55922 Add End
    // END 2020/09/24 K.Kitachi [QC#57667, DEL]

    // START 2020/09/24 K.Kitachi [QC#57667, ADD]
    private void setBllgMtrByInsOrDel(NSAL0320CMsg cMsg, NSAL0320SMsg sMsg) {
        List<String> cBllgMtrList = new ArrayList<String>();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).bllblFlg.getValue())) {
                continue;
            }
            if (!cBllgMtrList.contains(cMsg.A.no(i).bllgMtrLbCd.getValue())) {
                cBllgMtrList.add(cMsg.A.no(i).bllgMtrLbCd.getValue());
            }
        }
        List<String> sBllgMtrList = new ArrayList<String>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).bllblFlg.getValue())) {
                continue;
            }
            if (!sBllgMtrList.contains(sMsg.A.no(i).bllgMtrLbCd.getValue())) {
                sBllgMtrList.add(sMsg.A.no(i).bllgMtrLbCd.getValue());
            }
        }
        this.createBllgMtrList = new ArrayList<String>();
        for (String cBllgMtr : cBllgMtrList) {
            if (!sBllgMtrList.contains(cBllgMtr)) {
                this.createBllgMtrList.add(cBllgMtr);
            }
        }
        this.deleteBllgMtrList = new ArrayList<String>();
        for (String sBllgMtr : sBllgMtrList) {
            if (!cBllgMtrList.contains(sBllgMtr)) {
                this.deleteBllgMtrList.add(sBllgMtr);
            }
        }
        this.delCpltBllgMtrList = new ArrayList<String>();
    }

    // END 2020/09/24 K.Kitachi [QC#57667, ADD]

    // START 2022/06/22 E.Sanchez [QC#59804, ADD]
    // START 2022/07/22 E.Sanchez [QC#59804, MOD]
    // private void clearStartRead(NSAL0320CMsg cMsg, NSAL0320SMsg sMsg) {
    private void clearStartRead(NSAL0320CMsg cMsg, NSAL0320SMsg sMsg, List<Map<String, Object>> fltAggMachList) {
        // END 2022/07/22 E.Sanchez [QC#59804, MOD]
        String glblCmpyCd = getGlobalCompanyCode();
        boolean isMeterReadInvoiced = false;
        // START 2022/07/22 E.Sanchez [QC#59804, ADD]
        List<Map<String, Object>> svcPhysMtrParamList = new ArrayList<Map<String, Object>>();
        BigDecimal dsContrDtlPk = cMsg.dsContrDtlPk.getValue();
        BigDecimal svcMachMstrPk = cMsg.svcMachMstrPk.getValue();

        if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("DS_CONTR_DTL_PK", dsContrDtlPk);
            param.put("SVC_MACH_MSTR_PK", svcMachMstrPk);
            svcPhysMtrParamList.add(param);
        } else if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
            for (Map<String, Object> fltAggMach : fltAggMachList) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("DS_CONTR_DTL_PK", (BigDecimal) fltAggMach.get("DS_CONTR_DTL_PK"));
                param.put("SVC_MACH_MSTR_PK", (BigDecimal) fltAggMach.get("SVC_MACH_MSTR_PK"));
                svcPhysMtrParamList.add(param);
            }
        }
        // END 2022/07/22 E.Sanchez [QC#59804, ADD]

        // START 2022/07/22 E.Sanchez [QC#59804, MOD]
        for (Map<String, Object> param : svcPhysMtrParamList) {
            // START 2022/07/22 E.Sanchez [QC#59804, MOD]
            // SVC_PHYS_MTR_READTMsgArray outArray = getSvcPhysMtrReadForUpdate(glblCmpyCd, cMsg.dsContrDtlPk.getValue(), cMsg.svcMachMstrPk.getValue());
            SVC_PHYS_MTR_READTMsgArray outArray = getSvcPhysMtrReadForUpdate(glblCmpyCd, (BigDecimal) param.get("DS_CONTR_DTL_PK"), (BigDecimal) param.get("SVC_MACH_MSTR_PK"));
            // END 2022/07/22 E.Sanchez [QC#59804, MOD]

            if (outArray != null && outArray.getValidCount() > 0) {
                BigDecimal bdNull = null;
                NSAL0320Query query = NSAL0320Query.getInstance();

                S21SsmEZDResult rslt = query.getBllgMtrSchdBySvcPhysMrReadGrpSq(glblCmpyCd, outArray.no(0).svcPhysMtrReadGrpSq.getValue());
                if (rslt != null && rslt.getQueryResultCount() > 0) {
                    isMeterReadInvoiced = true;
                }
                for (int i = 0; i < outArray.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(outArray.no(i).dsContrDtlPk, bdNull);
                    if (!isMeterReadInvoiced) {
                        ZYPEZDItemValueSetter.setValue(outArray.no(i).vldMtrFlg, ZYPConstant.FLG_OFF_N);
                    }
                    EZDTBLAccessor.update(outArray.no(i));
                }
            }
        }
        // END 2022/07/22 E.Sanchez [QC#59804, MOD]
    }

    private SVC_PHYS_MTR_READTMsgArray getSvcPhysMtrReadForUpdate(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcMachMstrPk) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("009");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        return (SVC_PHYS_MTR_READTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
    }
    // END 2022/06/22 E.Sanchez [QC#59804, ADD]

    // START 2022/10/25 E.Sanchez [QC#60661, ADD]
    private boolean callApiByDeleteModeForAggregateMachLine(NSAL0320CMsg cMsg, String glblCmpyCd, String slsDt, List<Map<String, Object>> fltAggMachList) {

        for (Map<String, Object> fltAggMach : fltAggMachList) {
            BigDecimal dsContrDtlPk = (BigDecimal) fltAggMach.get("DS_CONTR_DTL_PK");

            if (this.procDsContrDtlPkList.indexOf(dsContrDtlPk) >= 0) {
                continue;
            }

            List<BigDecimal> dsContrBllgMtrPkList = NSAL0320Query.getInstance().getDsContrBllgMtrPkByContrDtlPk(glblCmpyCd, dsContrDtlPk);
            for (BigDecimal dsContrBllgMtrPk : dsContrBllgMtrPkList) {
                NSZC047010PMsg pMsg = new NSZC047010PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NSAL0320Constant.MODE_10);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
                ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(pMsg.delFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(pMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);

                NSZC047001 api = new NSZC047001();
                api.execute(pMsg, ONBATCH_TYPE.ONLINE);
                if (pMsg.xxMsgIdList.getValidCount() != 0) {
                    cMsg.setMessageInfo(NSAL0320Constant.NSAM0003E, new String[] {"NSZC047001", pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), "DS_CONTR_DTL_PK=" + dsContrDtlPk });
                    return false;
                }
            }

            this.procDsContrDtlPkList.add(dsContrDtlPk);
        }

        return true;
    }
    // END 2022/10/25 E.Sanchez [QC#60661, ADD]
}
