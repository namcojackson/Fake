/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0320;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0320.common.NSAL0320CommonLogic;
import business.blap.NSAL0320.constant.NSAL0320Constant;
import business.db.PRC_MTR_PKGTMsg;
import business.parts.NSZC048001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC048001.NSZC048001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            SRAA            Create          N/A
 * 2015/10/13   Hitachi         T.Tomita        Update          N/A
 * 2015/12/11   Hitachi         T.Kanasaka      Update          QC#1811
 * 2016/01/07   Hitachi         T.Tomita        Update          QC#2806
 * 2016/02/09   Hitachi         T.Tomita        Update          QC#4219
 * 2016/05/20   Hitachi         T.Tomita        Update          QC#4958
 * 2016/06/08   Hitachi         T.Kanasaka      Update          QC#5005
 * 2016/06/13   Hitachi         T.Kanasaka      Update          QC#5005
 * 2016/07/11   Hitachi         M.Gotou         Update          QC#11521
 * 2016/07/26   Hitachi         A.Kohinata      Update          QC#12106
 * 2016/08/22   Hitachi         A.Kohinata      Update          QC#12910
 * 2016/09/16   Hitachi         N.Arai          Update          QC#11616
 * 2016/09/20   Hitachi         T.Tomita        Update          QC#6316
 * 2016/09/23   Hitachi         T.Tomita        Update          QC#12178
 * 2016/10/06   Hitachi         T.Tomita        Update          QC#12178
 * 2016/11/28   Hitachi         K.Ochiai        Update          QC#14204
 * 2016/12/09   Hitachi         K.Kojima        Update          QC#16461
 * 2017/08/07   Hitachi         T.Kanasaka      Update          QC#18193,18195
 * 2017/08/30   Hitachi         T.Kanasaka      Update          QC#20536
 * 2017/09/01   Hitachi         K.Yamada        Update          QC#20850
 * 2017/09/13   Hitachi         K.Kitachi       Update          QC#21048
 * 2018/04/06   Hitachi         K.Kitachi       Update          QC#23628
 * 2018/08/26   Hitachi         A.Kohinata      Update          QC#27949
 * 2020/08/21   CITS            T.Sakurai       Update          QC#57297
 *</pre>
 */
public class NSAL0320BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        super.preDoProcess(ezdCMsg, ezdSMsg);
        try {
            NSAL0320CMsg cMsg = (NSAL0320CMsg) ezdCMsg;
            // START 2015/10/13 T.Tomita [N/A, MOD]
            NSAL0320SMsg sMsg = (NSAL0320SMsg) ezdSMsg;
            String screenAplId = cMsg.getScreenAplID();
            if ("NSAL0320_INIT".equals(screenAplId)) {
                doProcess_NSAL0320_INIT(cMsg, sMsg);
            } else if ("NSAL0320Scrn00_CMN_Reset".equals(screenAplId)) {
                doProcess_NSAL0320Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NSAL0320Scrn00_CMN_Return".equals(screenAplId)) {
                doProcess_NSAL0320Scrn00_CMN_Return(cMsg);
            } else if ("NSAL0320Scrn00_CMN_Submit".equals(screenAplId)) {
                doProcess_NSAL0320Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSAL0320Scrn00_OnChange_BillingCounter".equals(screenAplId)) {
                doProcess_NSAL0320Scrn00_OnChange_BillingCounter(cMsg);
            } else if ("NSAL0320Scrn00_OnChange_ServiceProgram".equals(screenAplId)) {
                doProcess_NSAL0320Scrn00_OnChange_ServiceProgram(cMsg);
            } else if ("NSAL0320Scrn00_OnChange_BillingMeterLevel".equals(screenAplId)) {
                doProcess_NSAL0320Scrn00_OnChange_BillingMeterLevel(cMsg);
            // END 2015/10/13 T.Tomita [N/A, MOD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(ezdCMsg, ezdSMsg);
        }
    }

    private void doProcess_NSAL0320_INIT(NSAL0320CMsg cMsg, NSAL0320SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0320Scrn00_CMN_Reset(NSAL0320CMsg cMsg, NSAL0320SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0320Scrn00_CMN_Return(NSAL0320CMsg cMsg) {
    }

    private void doProcess_NSAL0320Scrn00_CMN_Submit(NSAL0320CMsg cMsg, NSAL0320SMsg sMsg) {
        // START 2017/08/30 T.Kanasaka [QC#20536,MOD]
//        search(cMsg, sMsg);
        search(cMsg, sMsg, false);
        // END 2017/08/30 T.Kanasaka [QC#20536,MOD]
    }

    private void doProcess_NSAL0320Scrn00_OnChange_BillingCounter(NSAL0320CMsg cMsg) {
        // START 2016/07/11 M.Gotou [QC#11521, MOD]
        setContrMtrMultRate(cMsg, cMsg.xxRowNum.getValueInt());
        // END 2016/07/11 M.Gotou [QC#11521, MOD]
    }

    // mod start 2016/05/19 CSA Defect#4958
    private void doProcess_NSAL0320Scrn00_OnChange_ServiceProgram(NSAL0320CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        BigDecimal prcMtrPkgPk = cMsg.prcMtrPkgPk_B.getValue();
        BigDecimal mdlId = cMsg.mdlId.getValue();
        if (ZYPCommonFunc.hasValue(prcMtrPkgPk)) {
            if (!NSAL0320CommonLogic.isEqualNum(cMsg.prcMtrPkgPk_PR.getValue(), prcMtrPkgPk)) {
                for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                    // mod start 2016/09/23 CSA Defect#12178
                    setDefPhysMtrForSvcPkg(cMsg.A.no(i), glblCmpyCd, slsDt, prcMtrPkgPk, cMsg.dsContrCatgCd.getValue());
                    // mod end 2016/09/23 CSA Defect#12178
                    // mod start 2016/07/26 CSA Defect#12106
                    setSvcPkgBllgMtrPulldown(cMsg.A.no(i), glblCmpyCd, prcMtrPkgPk, mdlId, cMsg.dsContrCatgCd.getValue(), cMsg.dsContrDtlPk.getValue(), slsDt);
                    // mod end 2016/07/26 CSA Defect#12106
                }
            }
        } else {
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                // mod start 2016/07/26 CSA Defect#12106
                setBllgMtrPulldown(cMsg.A.no(i), glblCmpyCd, slsDt, cMsg.dsContrCatgCd.getValue(), cMsg.dsContrDtlPk.getValue(), prcMtrPkgPk);
                // mod end 2016/07/26 CSA Defect#12106
            }
        }
        ZYPEZDItemValueSetter.setValue(cMsg.prcMtrPkgPk_PR, prcMtrPkgPk);

        // mod start 2016/08/22 CSA Defect#12910
        cMsg.bllgMtrMapLvlNum_C.clear();
        // mod end 2016/08/22 CSA Defect#12910
    }
    // mod end 2016/05/19 CSA Defect#4958

    // START 2015/10/13 T.Tomita [N/A, ADD]
    private void doProcess_NSAL0320Scrn00_OnChange_BillingMeterLevel(NSAL0320CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String selBllgMtrMapLvlNum = cMsg.bllgMtrMapLvlNum_C.getValue();
        // START 2016/07/11 M.Gotou [QC#11521, ADD]
        if (!ZYPCommonFunc.hasValue(selBllgMtrMapLvlNum)) {
            cMsg.bllgMtrMapLvlNum_PR.clear();
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                cMsg.A.no(i).contrMtrMultRate.clear();
                cMsg.A.no(i).bllgMtrLbCd.clear();
                cMsg.A.no(i).bllgMtrLbCd_PR.clear();
            }
            return;
        }
        // END 2016/07/11 M.Gotou [QC#11521, ADD]

        if (!NSAL0320CommonLogic.isEqualStr(cMsg.bllgMtrMapLvlNum_PR.getValue(), selBllgMtrMapLvlNum) && ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg.getValue())) {
            String[] bllgMtrLbCdArray;
            // START 2016/01/07 T.Tomita [QC#2806, MOD]
            String mdlMtrLbCd;
            // mod start 2016/05/24 CSA Defect#4958
            String dsContrCatgCd = cMsg.dsContrCatgCd.getValue();
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                bllgMtrLbCdArray = convListToArray(cMsg.A.no(i));
                mdlMtrLbCd = cMsg.A.no(i).mdlMtrLbCd.getValue();

                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).bllblFlg.getValue())) {
                    // mod start 2016/07/26 CSA Defect#12106
                    List<Map<String, Object>> bllgMtrLvl = NSAL0320CommonLogic.getBllgMtrLb(glblCmpyCd, bllgMtrLbCdArray, selBllgMtrMapLvlNum, mdlMtrLbCd, dsContrCatgCd, cMsg.dsContrDtlPk.getValue(), ZYPDateUtil.getSalesDate(glblCmpyCd));
                    // mod end 2016/07/26 CSA Defect#12106
                    if (bllgMtrLvl.isEmpty()) {
                        cMsg.A.no(i).bllgMtrLbCd.clear();
                    } else {
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).bllgMtrLbCd, (String) bllgMtrLvl.get(0).get("BLLG_MTR_LB_CD"));
                    }
                }
                // START 2016/07/11 M.Gotou [QC#11521, ADD]
                setContrMtrMultRate(cMsg, i);
                // END 2016/07/11 M.Gotou [QC#11521, ADD]
            }
            // mod end 2016/05/24 CSA Defect#4958
            // START 2016/01/07 T.Tomita [QC#2806, MOD]
            ZYPEZDItemValueSetter.setValue(cMsg.bllgMtrMapLvlNum_PR, cMsg.bllgMtrMapLvlNum_C);
        }

        // START 2016/07/11 M.Gotou [QC#11521, DEL]
//        setContrMtrMultRate(cMsg);
        // END 2016/07/11 M.Gotou [QC#11521, DEL]
    }

    private String[] convListToArray(NSAL0320_ACMsg acMsg) {
        List<String> bllgMtrLbCdList = new ArrayList<String>();
        if (acMsg.bllgMtrLbCd_AB != null) {
            for (int i = 0; i < acMsg.bllgMtrLbCd_AB.length(); i++) {
                if (!ZYPCommonFunc.hasValue(acMsg.bllgMtrLbCd_AB.no(i))) {
                    break;
                }
                bllgMtrLbCdList.add(acMsg.bllgMtrLbCd_AB.no(i).getValue());
            }
        }
        String[] bllgMtrLbCdArray = new String[bllgMtrLbCdList.size()];
        return bllgMtrLbCdList.toArray(bllgMtrLbCdArray);
    }
    // END 2015/10/13 T.Tomita [N/A, ADD]

    // START 2017/08/30 T.Kanasaka [QC#20536,ADD]
    private void search(NSAL0320CMsg cMsg, NSAL0320SMsg sMsg) {
        search(cMsg, sMsg, true);
    }
    // END 2017/08/30 T.Kanasaka [QC#20536,ADD]

    // START 2017/08/30 T.Kanasaka [QC#20536,MOD]
//    private void search(NSAL0320CMsg cMsg, NSAL0320SMsg sMsg) {
    private void search(NSAL0320CMsg cMsg, NSAL0320SMsg sMsg, boolean isInit) {
    // END 2017/08/30 T.Kanasaka [QC#20536,MOD]

        if (NSAL0320CommonLogic.hasError(cMsg)) {
            return;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk)) {
            cMsg.setMessageInfo(NSAL0320Constant.NSAM0219E, new String[] {"No input parameter found" });
            return;
        }

        NSAL0320CommonLogic.resetParameter(cMsg);

        String glblCmpyCd = getGlobalCompanyCode();
        // START 2015/10/13 T.Tomita [N/A, ADD]
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        // END 2015/10/13 T.Tomita [N/A, ADD]

        NSAL0320Query query = NSAL0320Query.getInstance();

        S21SsmEZDResult rslt = query.gerDsContr(glblCmpyCd, cMsg.dsContrDtlPk.getValue());
        if (rslt == null || !rslt.isCodeNormal()) {
            cMsg.setMessageInfo(NSAL0320Constant.NSAM0219E, new String[] {"The DS Contract Detail pk is not valid" });
            return;
        } else {
            Map<String, Object> rsltMap = (Map<String, Object>) rslt.getResultObject();
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrPk, (BigDecimal) rsltMap.get("DS_CONTR_PK"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrNum, (String) rsltMap.get("DS_CONTR_NUM"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrSqNum, (String) rsltMap.get("DS_CONTR_SQ_NUM"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrStsCd, (String) rsltMap.get("DS_CONTR_STS_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrDtlPk, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrDtlStsCd, (String) rsltMap.get("DS_CONTR_DTL_STS_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrPk, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(cMsg.serNum, (String) rsltMap.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, (String) rsltMap.get("MDSE_CD"));
            // START 2016/09/16 N.Arai [QC#11616, MOD]
            // ZYPEZDItemValueSetter.setValue(cMsg.mdseNm, (String) rsltMap.get("MDSE_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
            // END 2016/09/16 N.Arai [QC#11616, MOD]
            ZYPEZDItemValueSetter.setValue(cMsg.mdlId, (BigDecimal) rsltMap.get("MDL_ID"));
            ZYPEZDItemValueSetter.setValue(cMsg.mdlNm, (String) rsltMap.get("MDL_NM"));
            // START 2015/10/13 T.Tomita [N/A, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCatgCd, (String) rsltMap.get("DS_CONTR_CATG_CD"));
            BigDecimal prcMtrPkgPk = (BigDecimal) rsltMap.get("PRC_MTR_PKG_PK");
            if (ZYPCommonFunc.hasValue(prcMtrPkgPk)) {
                ZYPEZDItemValueSetter.setValue(cMsg.prcMtrPkgPk_B, prcMtrPkgPk);
                ZYPEZDItemValueSetter.setValue(cMsg.prcMtrPkgPk_PR, prcMtrPkgPk);
            }
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime, (String) rsltMap.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone, (String) rsltMap.get("EZUPTIMEZONE"));
            // END 2015/10/13 T.Tomita [N/A, ADD]
        }

        // START 2015/10/13 T.Tomita [N/A, ADD]
        // mod start 2016/05/18 CSA Defect#4958
        // Get Service Package Pulldown
        setSvcPkgPulldown(cMsg, glblCmpyCd, slsDt);
        // mod end 2016/05/18 CSA Defect#4958

        // Get Billing Meter Level Pulldown
        if (ZYPCommonFunc.hasValue(cMsg.dsContrCatgCd)) {
            if (DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue())) {
                // mod start 2016/07/26 CSA Defect#12106
                rslt = query.getBllgMtrLvlForNonFleet(glblCmpyCd, cMsg.dsContrDtlPk.getValue(), slsDt);
                // mod end 2016/07/26 CSA Defect#12106
                setBllgMtrLvlPulldown(cMsg, rslt);
            } else if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue()) || DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
                // mod start 2016/10/06 CSA Defect#12178
                rslt = query.getBllgMtrLvlForFltAgg(glblCmpyCd, cMsg.dsContrPk.getValue(), slsDt);
                // mod end 2016/10/06 CSA Defect#12178
                setBllgMtrLvlPulldown(cMsg, rslt);
            }
        }

        // mod start 2016/05/18 CSA Defect#4958
        // Get Billing Meter Relation
        if (ZYPCommonFunc.hasValue(cMsg.dsContrCatgCd)) {
            if (DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue())) {
                setBllgMtrRelnForNonFleet(cMsg, glblCmpyCd, slsDt);
            } else if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue()) || DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
                // START 2017/08/30 T.Kanasaka [QC#20536,MOD]
//                setBllgMtrRelnForFltAgg(cMsg, glblCmpyCd, slsDt);
                setBllgMtrRelnForFltAgg(cMsg, glblCmpyCd, slsDt, isInit);
                // END 2017/08/30 T.Kanasaka [QC#20536,MOD]
            }
        }
        // mod end 2016/05/18 CSA Defect#4958

        List<Map<String, Object>> bllgMtrSchdList = NSAL0320CommonLogic.getBllgMtrSchd(glblCmpyCd, cMsg.dsContrDtlPk.getValue());
        if (bllgMtrSchdList == null || bllgMtrSchdList.size() == 0) {
            ZYPEZDItemValueSetter.setValue(cMsg.actvFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.actvFlg, ZYPConstant.FLG_OFF_N);
        }

        NSAL0320CommonLogic.applyBllblFlg(cMsg);
        // Copy Search Data
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        // START 2016/01/07 T.Tomita [QC#2806, ADD]
        EZDMsg.copy(cMsg, null, sMsg, null);
        // END 2016/01/07 T.Tomita [QC#2806, ADD]
        EZDMsg.copy(cMsg.A, null, sMsg.A, null);
        // END 2015/10/13 T.Tomita [N/A, ADD]
    }

    // add start 2016/05/18 CSA Defect#4958
    private void setSvcPkgPulldown(NSAL0320CMsg cMsg, String glblCmpyCd, String slsDt) {
        BigDecimal mdlId = getMdlId(cMsg, glblCmpyCd, slsDt);
        if (!ZYPCommonFunc.hasValue(mdlId)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.mdlId, mdlId);
        ZYPEZDItemValueSetter.setValue(cMsg.mdlNm, NSAL0320Query.getInstance().getMdlNm(glblCmpyCd, mdlId));

        // Add Start 2017/09/01 QC#20850
        boolean hasExistingPrcMtrPkg = false;
        BigDecimal existingPrcMtrPkg = cMsg.prcMtrPkgPk_B.getValue();
        int pulldownIdx = 0;
        // Add End 2017/09/01 QC#20850

        // START 2018/04/06 K.Kitachi [QC#23628, MOD]
//        S21SsmEZDResult rslt = NSAL0320Query.getInstance().getSvcPkgMap(glblCmpyCd, mdlId, slsDt);
        S21SsmEZDResult rslt;
        if (DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue())) {
            rslt = NSAL0320Query.getInstance().getSvcPkgMap(glblCmpyCd, mdlId, slsDt);
        } else {
            rslt = NSAL0320Query.getInstance().getSvcPkgMapForFltAgg(glblCmpyCd, cMsg.dsContrPk.getValue(), slsDt);
        }
        // END 2018/04/06 K.Kitachi [QC#23628, MOD]
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            if (rsltCnt > cMsg.prcMtrPkgPk_BC.length()) {
                rsltCnt = cMsg.prcMtrPkgPk_BC.length();
            }
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.prcMtrPkgPk_BC.no(i), (BigDecimal) rsltMap.get("PRC_MTR_PKG_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.prcMtrPkgDescTxt_BN.no(i), (String) rsltMap.get("PRC_MTR_PKG_NM"));

                // Add Start 2017/09/01 QC#20850
                pulldownIdx++;
                if (ZYPCommonFunc.hasValue(existingPrcMtrPkg) && cMsg.prcMtrPkgPk_BC.no(i).getValue().compareTo(existingPrcMtrPkg) == 0) {
                    hasExistingPrcMtrPkg = true;
                }
                // Add End 2017/09/01 QC#20850
            }
        }

        // Add Start 2017/09/01 QC#20850
        if (existingPrcMtrPkg != null && !hasExistingPrcMtrPkg) {
            PRC_MTR_PKGTMsg prcMtrPkg = new PRC_MTR_PKGTMsg();
            ZYPEZDItemValueSetter.setValue(prcMtrPkg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prcMtrPkg.prcMtrPkgPk, existingPrcMtrPkg);
            prcMtrPkg = (PRC_MTR_PKGTMsg) EZDTBLAccessor.findByKey(prcMtrPkg);

            ZYPEZDItemValueSetter.setValue(cMsg.prcMtrPkgPk_BC.no(pulldownIdx), prcMtrPkg.prcMtrPkgPk);
            ZYPEZDItemValueSetter.setValue(cMsg.prcMtrPkgDescTxt_BN.no(pulldownIdx), prcMtrPkg.prcMtrPkgNm);
        }
        // Add End 2017/09/01 QC#20850
    }

    private BigDecimal getMdlId(NSAL0320CMsg cMsg, String glblCmpyCd, String slsDt) {
        BigDecimal mdlId = cMsg.mdlId.getValue();
        if (ZYPCommonFunc.hasValue(cMsg.dsContrCatgCd) && !DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue())) {
            NSZC048001 svcMdlApi = new NSZC048001();
            NSZC048001PMsg svcMdlPMsg = new NSZC048001PMsg();
            ZYPEZDItemValueSetter.setValue(svcMdlPMsg.glblCmpyCd, glblCmpyCd);

            ZYPEZDItemValueSetter.setValue(svcMdlPMsg.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(svcMdlPMsg.prntMdseCd, getSpclMdseCd(glblCmpyCd, cMsg.dsContrCatgCd.getValue()));
            svcMdlApi.execute(svcMdlPMsg, ONBATCH_TYPE.ONLINE);
            if (S21ApiUtil.isXxMsgId(svcMdlPMsg)) {
                // START 2016/12/09 K.Kojima [QC#16461,ADD]
                cMsg.setMessageInfo(NSAL0320Constant.NSZM0569E);
                // END 2016/12/09 K.Kojima [QC#16461,ADD]
                return null;
            }

            return svcMdlPMsg.mdlId.getValue();
        }
        return mdlId;
    }

    private String getSpclMdseCd(String glblCmpyCd, String dsContrCatgCd) {
        String rtnVal = null;
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            rtnVal = ZYPCodeDataUtil.getVarCharConstValue(NSAL0320Constant.SPCL_FLT_MDSE_CD, glblCmpyCd);
        } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            rtnVal = ZYPCodeDataUtil.getVarCharConstValue(NSAL0320Constant.SPCL_AGG_MDSE_CD, glblCmpyCd);
        }
        return rtnVal;
    }
    // add end 2016/05/18 CSA Defect#4958

    // START 2015/10/13 T.Tomita [N/A, ADD]
    private void setBllgMtrLvlPulldown(NSAL0320CMsg cMsg, S21SsmEZDResult rslt) {
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            if (rsltCnt > cMsg.bllgMtrMapLvlNum_CC.length()) {
                rsltCnt = cMsg.bllgMtrMapLvlNum_CC.length();
            }
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.bllgMtrMapLvlNum_CC.no(i), (String) rsltMap.get("BLLG_MTR_MAP_LVL_NUM"));
                ZYPEZDItemValueSetter.setValue(cMsg.bllgMtrMapLvlNum_CN.no(i), (String) rsltMap.get("BLLG_MTR_MAP_LVL_NUM"));
            }
        }
    }

    // START 2016/06/13 T.Kanasaka [QC#5005, MOD]
    private void setBllgMtrRelnForNonFleet(NSAL0320CMsg cMsg, String glblCmpyCd, String slsDt) {
        NSAL0320Query query = NSAL0320Query.getInstance();
        S21SsmEZDResult rslt;
        // START 2016/02/09 T.Tomita[QC#4219, MOD]
        rslt = query.getBllgMtr(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), cMsg.dsContrDtlPk.getValue(), cMsg.A.length() + 1);
        // END 2016/02/09 T.Tomita[QC#4219, MOD]
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            if (rsltCnt > cMsg.A.length()) {
                rsltCnt = cMsg.A.length();
                cMsg.setMessageInfo(NSAL0320Constant.NZZM0001W);
            }
            // mod start 2016/05/18 CSA Defect#4958
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).ezUpTime_AR, (String) rsltMap.get("EZUPTIME_AR"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).ezUpTimeZone_AR, (String) rsltMap.get("EZUPTIMEZONE_AR"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).contrPhysBllgMtrRelnPk, (BigDecimal) rsltMap.get("CONTR_PHYS_BLLG_MTR_RELN_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).ezUpTime_AB, (String) rsltMap.get("EZUPTIME_AB"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).ezUpTimeZone_AB, (String) rsltMap.get("EZUPTIMEZONE_AB"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).dsContrBllgMtrPk, (BigDecimal) rsltMap.get("DS_CONTR_BLLG_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).svcPhysMtrPk, (BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mtrEntryMndFlg, (String) rsltMap.get("MTR_ENTRY_MND_FLG"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mdlMtrLbCd, (String) rsltMap.get("MDL_MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mtrLbDescTxt_AP, (String) rsltMap.get("MTR_LB_DESC_TXT_AP"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).bllblFlg, (String) rsltMap.get("BLLBL_FLG"));

                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).contrMtrMultRate, (BigDecimal) rsltMap.get("CONTR_MTR_MULT_RATE"));
                String bllgMtrLbCd = (String) rsltMap.get("BLLG_MTR_LB_CD");
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).bllgMtrLbCd, bllgMtrLbCd);

                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).mtrEntryMndFlg.getValue())) {
                    // START 2016/09/20 T.Tomita [QC#6316, MOD]
                    Map<String, Object> defBllgMtrMap = getBllgMtrLbCdForNonFleet(glblCmpyCd, slsDt, cMsg.A.no(i).mdlMtrLbCd.getValue(), bllgMtrLbCd, cMsg.A.no(i).contrPhysBllgMtrRelnPk.getValue(), (BigDecimal) rsltMap.get("MTR_GRP_PK"));
                    if (defBllgMtrMap != null && ZYPCommonFunc.hasValue((String) defBllgMtrMap.get("BLLG_MTR_LB_CD"))) {
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).bllblFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).bllgMtrLbCd, (String) defBllgMtrMap.get("BLLG_MTR_LB_CD"));
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).contrMtrMultRate, (BigDecimal) defBllgMtrMap.get("MTR_MULT_RATE"));
                    }
                    // END 2016/09/20 T.Tomita [QC#6316, MOD]
                }

                // mod start 2016/10/06 CSA Defect#12178
                if (ZYPCommonFunc.hasValue(cMsg.prcMtrPkgPk_B)) {
                    // del start 2018/08/26 QC#27949
                    //setDefPhysMtrForSvcPkg(cMsg.A.no(i), glblCmpyCd, slsDt, cMsg.prcMtrPkgPk_B.getValue(), cMsg.dsContrCatgCd.getValue());
                    // del end 2018/08/26 QC#27949
                    setSvcPkgBllgMtrPulldown(cMsg.A.no(i), glblCmpyCd, cMsg.prcMtrPkgPk_B.getValue(), cMsg.mdlId.getValue(), cMsg.dsContrCatgCd.getValue(), cMsg.dsContrDtlPk.getValue(), slsDt);
                } else {
                    setBllgMtrPulldownForNonFleet(cMsg.A.no(i), glblCmpyCd, cMsg.dsContrDtlPk.getValue(), slsDt, cMsg.prcMtrPkgPk_B.getValue());
                }
                // mod end 2016/10/06 CSA Defect#12178
                // START 2016/11/28 K.Ochiai [QC#14204, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxRecHistCratTs_A1, (String) rsltMap.get("XX_REC_HIST_CRAT_TS"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxRecHistCratByNm_A1, (String) rsltMap.get("XX_REC_HIST_CRAT_BY_NM"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxRecHistUpdTs_A1, (String) rsltMap.get("XX_REC_HIST_UPD_TS"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxRecHistUpdByNm_A1, (String) rsltMap.get("XX_REC_HIST_UPD_BY_NM"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxRecHistTblNm_A1, (String) rsltMap.get("XX_REC_HIST_TBL_NM"));
                // END 2016/11/28 K.Ochiai [QC#14204, ADD]
            }
            cMsg.A.setValidCount(rsltCnt);
            // mod end 2016/05/18 CSA Defect#4958
        }
    }
    // END 2016/06/13 T.Kanasaka [QC#5005, MOD]

    // mod start 2016/05/19 CSA Defect#4958
    // START 2016/06/08 T.Kanasaka [QC#5005, MOD]
    // START 2017/08/30 T.Kanasaka [QC#20536,MOD]
//    private void setBllgMtrRelnForFltAgg(NSAL0320CMsg cMsg, String glblCmpyCd, String slsDt) {
    private void setBllgMtrRelnForFltAgg(NSAL0320CMsg cMsg, String glblCmpyCd, String slsDt, boolean isInit) {
    // END 2017/08/30 T.Kanasaka [QC#20536,MOD]
        BigDecimal dsContrPk = cMsg.dsContrPk.getValue();
        BigDecimal dsContrDtlPk = cMsg.dsContrDtlPk.getValue();
        S21SsmEZDResult rslt = NSAL0320Query.getInstance().getPhysMtrForFltAgg(glblCmpyCd, dsContrPk);
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            if (rsltCnt > cMsg.A.length()) {
                rsltCnt = cMsg.A.length();
            }

            boolean defFlg = true;
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> physMtrMap = rsltList.get(i);
                String mdlMtrLbCd = (String) physMtrMap.get("MDL_MTR_LB_CD");
                // Physical Meter Label
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mdlMtrLbCd, mdlMtrLbCd);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mtrLbDescTxt_AP, (String) physMtrMap.get("MTR_LB_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mtrEntryMndFlg, (String) physMtrMap.get("MTR_ENTRY_MND_FLG"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).contrMtrMultRate, (BigDecimal) physMtrMap.get("MTR_MULT_RATE"));
                // START 2016/09/20 T.Tomita [QC#6316, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mtrGrpPk, (BigDecimal) physMtrMap.get("MTR_GRP_PK"));
                // END 2016/09/20 T.Tomita [QC#6316, ADD]

                // mod start 2016/10/06 CSA Defect#12178
                // Billing Meter Relation
                setBllgMtrForFltAgg(cMsg.A.no(i), glblCmpyCd, dsContrPk, dsContrDtlPk, mdlMtrLbCd);
                // mod end 2016/10/06 CSA Defect#12178

                // mod start 2016/10/06 CSA Defect#12178
                if (ZYPCommonFunc.hasValue(cMsg.prcMtrPkgPk_B)) {
                    // del start 2018/08/26 QC#27949
                    //setDefPhysMtrForSvcPkg(cMsg.A.no(i), glblCmpyCd, slsDt, cMsg.prcMtrPkgPk_B.getValue(), cMsg.dsContrCatgCd.getValue());
                    // del end 2018/08/26 QC#27949
                    setSvcPkgBllgMtrPulldown(cMsg.A.no(i), glblCmpyCd, cMsg.prcMtrPkgPk_B.getValue(), cMsg.mdlId.getValue(), cMsg.dsContrCatgCd.getValue(), cMsg.dsContrDtlPk.getValue(), slsDt);
                } else {
                    // Billing Meter Pulldown
                    setBllgMtrPulldownForFltAgg(cMsg.A.no(i), glblCmpyCd, slsDt, cMsg.dsContrCatgCd.getValue(), cMsg.dsContrDtlPk.getValue(), cMsg.prcMtrPkgPk_B.getValue());
                }
                // mod end 2016/10/06 CSA Defect#12178

                // exists Billing Meter Relation
                // START 2017/08/30 T.Kanasaka [QC#20536,MOD]
//                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).contrPhysBllgMtrRelnPk)) {
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).contrPhysBllgMtrRelnPk) || !isInit) {
                // END 2017/08/30 T.Kanasaka [QC#20536,MOD]
                    defFlg = false;
                }
            }
            cMsg.A.setValidCount(rsltCnt);

            // set Default Physical Meter
            if (defFlg) {
                // START 2020/08/21 T.Sakurai [QC#57297,ADD]
                S21SsmEZDResult chkRslt = NSAL0320Query.getInstance().getUnBllbRelnForFltAgg(glblCmpyCd, dsContrPk);
                if(chkRslt == null || chkRslt.getQueryResultCount() == 0){
                // END 2020/08/21 T.Sakurai [QC#57297,ADD]
                    for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                        setDefPhysMtrForFltAgg(cMsg.A.no(i), glblCmpyCd, slsDt, cMsg.dsContrCatgCd.getValue());
                    }
                // START 2020/08/21 T.Sakurai [QC#57297,ADD]
                }
                // END 2020/08/21 T.Sakurai [QC#57297,ADD]
            }
        }
    }

    private void setDefPhysMtrForFltAgg(NSAL0320_ACMsg acMsg, String glblCmpyCd, String slsDt, String dsContrCatgCd) {
        if (ZYPCommonFunc.hasValue(acMsg.mtrEntryMndFlg) && ZYPConstant.FLG_ON_Y.equals(acMsg.mtrEntryMndFlg.getValue())) {
            // START 2016/09/20 T.Tomita [QC#6316, MOD]
            // START 2017/08/07 T.Kanasaka [QC#18193,18195,MOD]
//            Map<String, Object> defBllgMtrMap = NSAL0320Query.getInstance().getDefaultBllgMtrLbForFltAgg(glblCmpyCd, slsDt, acMsg.mdlMtrLbCd.getValue(), dsContrCatgCd, acMsg.mtrGrpPk.getValue());
            Map<String, Object> defBllgMtrMap = NSAL0320Query.getInstance().getDefaultBllgMtrLbForNonFleet(glblCmpyCd, slsDt, acMsg.mdlMtrLbCd.getValue(), acMsg.mtrGrpPk.getValue());
            // END 2017/08/07 T.Kanasaka [QC#18193,18195,MOD]
            if (defBllgMtrMap != null && ZYPCommonFunc.hasValue((String) defBllgMtrMap.get("BLLG_MTR_LB_CD"))) {
                ZYPEZDItemValueSetter.setValue(acMsg.bllblFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(acMsg.bllgMtrLbCd, (String) defBllgMtrMap.get("BLLG_MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(acMsg.contrMtrMultRate, (BigDecimal) defBllgMtrMap.get("MTR_MULT_RATE"));
            }
            // END 2016/09/20 T.Tomita [QC#6316, MOD]
        }
    }
    // END 2016/06/08 T.Kanasaka [QC#5005, MOD]
    // mod end 2016/05/19 CSA Defect#4958
    // mod start 2016/10/06 CSA Defect#12178
    private void setBllgMtrForFltAgg(NSAL0320_ACMsg acMsg, String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String mdlMtrLbCd) {
        NSAL0320Query query = NSAL0320Query.getInstance();
        S21SsmEZDResult rslt;
        rslt = query.getBllgMtrForFltAgg(glblCmpyCd, dsContrPk, dsContrDtlPk, mdlMtrLbCd);
        if (rslt != null && rslt.isCodeNormal()) {
            // mod start 2016/05/25 CSA Defect#4958
            List<Map<String, Object>> bllgMtrMapList = (List<Map<String, Object>>) rslt.getResultObject();
            if (bllgMtrMapList.size() == 0) {
                return;
            }

            int idx = 0;
            if (bllgMtrMapList.size() > 1) {
                idx = getBllgMtrLbInd(bllgMtrMapList, glblCmpyCd, dsContrDtlPk, mdlMtrLbCd);
            }
            // START 2017/09/13 K.Kitachi [QC#21048, DEL]
//            ZYPEZDItemValueSetter.setValue(acMsg.contrMtrMultRate, (BigDecimal) bllgMtrMapList.get(idx).get("CONTR_MTR_MULT_RATE"));
            // END 2017/09/13 K.Kitachi [QC#21048, DEL]
            ZYPEZDItemValueSetter.setValue(acMsg.bllblFlg, (String) bllgMtrMapList.get(idx).get("BLLBL_FLG"));
            ZYPEZDItemValueSetter.setValue(acMsg.bllgMtrLbCd, (String) bllgMtrMapList.get(idx).get("BLLG_MTR_LB_CD"));
            ZYPEZDItemValueSetter.setValue(acMsg.bllgMtrMapLvlNum, (String) bllgMtrMapList.get(idx).get("BLLG_MTR_LVL_NUM"));
            ZYPEZDItemValueSetter.setValue(acMsg.contrPhysBllgMtrRelnPk, (BigDecimal) bllgMtrMapList.get(idx).get("CONTR_PHYS_BLLG_MTR_RELN_PK"));
            ZYPEZDItemValueSetter.setValue(acMsg.dsContrBllgMtrPk, (BigDecimal) bllgMtrMapList.get(idx).get("DS_CONTR_BLLG_MTR_PK"));
            ZYPEZDItemValueSetter.setValue(acMsg.ezUpTime_AR, (String) bllgMtrMapList.get(idx).get("EZUPTIME_AR"));
            ZYPEZDItemValueSetter.setValue(acMsg.ezUpTimeZone_AR, (String) bllgMtrMapList.get(idx).get("EZUPTIMEZONE_AR"));
            ZYPEZDItemValueSetter.setValue(acMsg.ezUpTime_AB, (String) bllgMtrMapList.get(idx).get("EZUPTIME_AB"));
            ZYPEZDItemValueSetter.setValue(acMsg.ezUpTimeZone_AB, (String) bllgMtrMapList.get(idx).get("EZUPTIMEZONE_AB"));
            // mod end 2016/05/25 CSA Defect#4958
            // START 2016/11/28 K.Ochiai [QC#14204, ADD]
            ZYPEZDItemValueSetter.setValue(acMsg.xxRecHistCratTs_A1, (String) bllgMtrMapList.get(idx).get("XX_REC_HIST_CRAT_TS"));
            ZYPEZDItemValueSetter.setValue(acMsg.xxRecHistCratByNm_A1, (String) bllgMtrMapList.get(idx).get("XX_REC_HIST_CRAT_BY_NM"));
            ZYPEZDItemValueSetter.setValue(acMsg.xxRecHistUpdTs_A1, (String) bllgMtrMapList.get(idx).get("XX_REC_HIST_UPD_TS"));
            ZYPEZDItemValueSetter.setValue(acMsg.xxRecHistUpdByNm_A1, (String) bllgMtrMapList.get(idx).get("XX_REC_HIST_UPD_BY_NM"));
            ZYPEZDItemValueSetter.setValue(acMsg.xxRecHistTblNm_A1, (String) bllgMtrMapList.get(idx).get("XX_REC_HIST_TBL_NM"));
            // END 2016/11/28 K.Ochiai [QC#14204, ADD]
        }
    }
    // mod end 2016/10/06 CSA Defect#12178
    // mod start 2016/07/26 CSA Defect#12106
    // mod start 2016/05/19 CSA Defect#4958
    private void setBllgMtrPulldown(NSAL0320_ACMsg acMsg, String glblCmpyCd, String slsDt, String dsContrCatgCd, BigDecimal dsContrDtlPk, BigDecimal prcMtrPkgPk) {
        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
            // NonFleet
            setBllgMtrPulldownForNonFleet(acMsg, glblCmpyCd, dsContrDtlPk, slsDt, prcMtrPkgPk);
        } else {
            // Fleet or Aggregate
            setBllgMtrPulldownForFltAgg(acMsg, glblCmpyCd, slsDt, dsContrCatgCd, dsContrDtlPk, prcMtrPkgPk);
        }
    }

    private void setBllgMtrPulldownForNonFleet(NSAL0320_ACMsg acMsg, String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt, BigDecimal prcMtrPkgPk) {
        String bllgMtrLbCd = acMsg.bllgMtrLbCd.getValue();
        String bllgMtrMapLvlNum = null;
        List<Map<String, Object>> bllgMtrMapList = NSAL0320CommonLogic.getBllgMtrMap(glblCmpyCd, acMsg.mdlMtrLbCd.getValue(), null, null, dsContrDtlPk, slsDt, prcMtrPkgPk);
        for (int j = 0; j < bllgMtrMapList.size(); j++) {
            Map<String, Object> bllgMtrMap = bllgMtrMapList.get(j);
            ZYPEZDItemValueSetter.setValue(acMsg.bllgMtrLbCd_AB.no(j), (String) bllgMtrMap.get("BLLG_MTR_LB_CD"));
            ZYPEZDItemValueSetter.setValue(acMsg.mtrLbDescTxt_AB.no(j), (String) bllgMtrMap.get("MTR_LB_DESC_TXT"));
            if (ZYPCommonFunc.hasValue(bllgMtrLbCd) && NSAL0320CommonLogic.isEqualStr(bllgMtrLbCd, (String) bllgMtrMap.get("BLLG_MTR_LB_CD"))) {
                bllgMtrMapLvlNum = (String) bllgMtrMap.get("BLLG_MTR_MAP_LVL_NUM");
            }
        }
        ZYPEZDItemValueSetter.setValue(acMsg.bllgMtrMapLvlNum, bllgMtrMapLvlNum);
    }

    // mod start 2016/10/07 CSA Defect#12178
    private void setBllgMtrPulldownForFltAgg(NSAL0320_ACMsg acMsg, String glblCmpyCd, String slsDt, String dsContrCatgCd, BigDecimal dsContrDtlPk, BigDecimal prcMtrPkgPk) {
        String bllgMtrLbCd = acMsg.bllgMtrLbCd.getValue();
        String bllgMtrMapLvlNum = null;
        // START 2016/06/07 T.Kanasaka [QC#5005, MOD]
        List<Map<String, Object>> bllgMtrMapList = NSAL0320Query.getInstance().getBllgMtrLbForFltAgg(glblCmpyCd, slsDt, acMsg.mdlMtrLbCd.getValue(), dsContrCatgCd, null, dsContrDtlPk, prcMtrPkgPk);
        // END 2016/06/07 T.Kanasaka [QC#5005, MOD]
        for (int j = 0; j < bllgMtrMapList.size(); j++) {
            Map<String, Object> bllgMtrMap = bllgMtrMapList.get(j);
            ZYPEZDItemValueSetter.setValue(acMsg.bllgMtrLbCd_AB.no(j), (String) bllgMtrMap.get("BLLG_MTR_LB_CD"));
            ZYPEZDItemValueSetter.setValue(acMsg.mtrLbDescTxt_AB.no(j), (String) bllgMtrMap.get("MTR_LB_DESC_TXT"));
            if (ZYPCommonFunc.hasValue(bllgMtrLbCd) && NSAL0320CommonLogic.isEqualStr(bllgMtrLbCd, (String) bllgMtrMap.get("BLLG_MTR_LB_CD"))) {
                bllgMtrMapLvlNum = (String) bllgMtrMap.get("BLLG_MTR_MAP_LVL_NUM");
            }
        }
        ZYPEZDItemValueSetter.setValue(acMsg.bllgMtrMapLvlNum, bllgMtrMapLvlNum);
    }
    // mod end 2016/10/07 CSA Defect#12178
    // mod end 2016/05/19 CSA Defect#4958

    // START 2015/12/11 T.Kanasaka [QC#1811, MOD]
    private void setSvcPkgBllgMtrPulldown(NSAL0320_ACMsg acMsg, String glblCmpyCd, BigDecimal prcMtrPkgPk, BigDecimal mdlId, String dsContrCatgCd, BigDecimal dsContrDtlPk, String slsDt) {
        String bllgMtrLbCd = acMsg.bllgMtrLbCd.getValue();
        String bllgMtrMapLvlNum = null;
        List<Map<String, Object>> bllgCntrInfoList = NSAL0320CommonLogic.getBllgCntrInfo(glblCmpyCd, prcMtrPkgPk, mdlId, acMsg.mdlMtrLbCd.getValue(), dsContrCatgCd, dsContrDtlPk, slsDt);
        acMsg.bllgMtrLbCd_AB.clear();
        acMsg.mtrLbDescTxt_AB.clear();
        for (int j = 0; j < bllgCntrInfoList.size(); j++) {
            Map<String, Object> bllgCntrInfo = bllgCntrInfoList.get(j);
            ZYPEZDItemValueSetter.setValue(acMsg.bllgMtrLbCd_AB.no(j), (String) bllgCntrInfo.get("MTR_LB_CD"));
            ZYPEZDItemValueSetter.setValue(acMsg.mtrLbDescTxt_AB.no(j), (String) bllgCntrInfo.get("MTR_LB_DESC_TXT"));
            if (ZYPCommonFunc.hasValue(bllgMtrLbCd) && NSAL0320CommonLogic.isEqualStr(bllgMtrLbCd, (String) bllgCntrInfo.get("MTR_LB_CD"))) {
                bllgMtrMapLvlNum = (String) bllgCntrInfo.get("BLLG_MTR_MAP_LVL_NUM");
            }
        }
        ZYPEZDItemValueSetter.setValue(acMsg.bllgMtrMapLvlNum, bllgMtrMapLvlNum);
    }
    // END 2015/12/11 T.Kanasaka [QC#1811, MOD]
    // mod end 2016/07/26 CSA Defect#12106

    // START 2016/07/11 M.Gotou [QC#11521, MOD]
    private void setContrMtrMultRate(NSAL0320CMsg cMsg, int rowNum) {
        if (!ZYPCommonFunc.hasValue(cMsg.A.no(rowNum).bllgMtrLbCd)) {
            cMsg.A.no(rowNum).contrMtrMultRate.clear();
            cMsg.A.no(rowNum).bllgMtrLbCd_PR.clear();
            return;
        }
        if (!cMsg.prcMtrPkgPk_B.isClear()) {
            String glblCmpyCd = getGlobalCompanyCode();
            String selMdlMtrLbCd = cMsg.A.no(rowNum).mdlMtrLbCd.getValue();
            String selBllgMtrLbCd = cMsg.A.no(rowNum).bllgMtrLbCd.getValue();
            if (!NSAL0320CommonLogic.isEqualStr(cMsg.A.no(rowNum).bllgMtrLbCd_PR.getValue(), selBllgMtrLbCd)) {
                List<Map<String, Object>> mtrMultRateList = NSAL0320CommonLogic.getMtrMultRate(glblCmpyCd, cMsg.prcMtrPkgPk_B.getValue(), selBllgMtrLbCd, selMdlMtrLbCd);
                if (mtrMultRateList.size() > 0) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(rowNum).contrMtrMultRate, (BigDecimal) mtrMultRateList.get(0).get("MTR_MULT_RATE"));
                } else {
                    cMsg.A.no(rowNum).contrMtrMultRate.clear();
                }
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(rowNum).bllgMtrLbCd_PR, selBllgMtrLbCd);
            }
        }
    }
    // END 2016/07/11 M.Gotou [QC#11521, MOD]
    // END 2015/10/13 T.Tomita [N/A, ADD]
    // add start 2016/05/18 CSA Defect#4958
    // START 2016/09/20 T.Tomita [QC#6316, MOD]
    private Map<String, Object> getBllgMtrLbCdForNonFleet(String glblCmpyCd, String slsDt, String mdlMtrLbCd, String bllgMtrLbCd, BigDecimal contrPhysBllgMtrRelnPk, BigDecimal mtrGrpPk) {
        if (ZYPCommonFunc.hasValue(contrPhysBllgMtrRelnPk) || ZYPCommonFunc.hasValue(bllgMtrLbCd)) {
            return null;
        }
        return NSAL0320Query.getInstance().getDefaultBllgMtrLbForNonFleet(glblCmpyCd, slsDt, mdlMtrLbCd, mtrGrpPk);
    }
    // END 2016/09/20 T.Tomita [QC#6316, MOD]

    // mod start 2016/09/23 CSA Defect#12178
    private void setDefPhysMtrForSvcPkg(NSAL0320_ACMsg acMsg, String glblCmpyCd, String slsDt, BigDecimal prcMtrPkgPk, String dsContrCatgCd) {
        ZYPEZDItemValueSetter.setValue(acMsg.bllblFlg, ZYPConstant.FLG_OFF_N);
        acMsg.contrMtrMultRate.clear();
        acMsg.bllgMtrLbCd.clear();
        acMsg.bllgMtrMapLvlNum.clear();

        Map<String, Object> resultMap;
        // START 2017/08/07 T.Kanasaka [QC#18193,18195,MOD]
//        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
//            resultMap = NSAL0320Query.getInstance().getDefSvcPkgForNonFleet(glblCmpyCd, prcMtrPkgPk, acMsg.mdlMtrLbCd.getValue());
//        } else {
//            resultMap = NSAL0320Query.getInstance().getDefSvcPkgForFltAgg(glblCmpyCd, slsDt, prcMtrPkgPk, acMsg.mdlMtrLbCd.getValue(), dsContrCatgCd);
//        }
        resultMap = NSAL0320Query.getInstance().getDefSvcPkgForNonFleet(glblCmpyCd, prcMtrPkgPk, acMsg.mdlMtrLbCd.getValue());
        // END 2017/08/07 T.Kanasaka [QC#18193,18195,MOD]

        if (resultMap == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(acMsg.bllblFlg, (String) resultMap.get("BLLBL_FLG"));
        ZYPEZDItemValueSetter.setValue(acMsg.contrMtrMultRate, (BigDecimal) resultMap.get("MTR_MULT_RATE"));
        ZYPEZDItemValueSetter.setValue(acMsg.bllgMtrLbCd, (String) resultMap.get("MTR_LB_CD"));
        ZYPEZDItemValueSetter.setValue(acMsg.bllgMtrMapLvlNum, (String) resultMap.get("BLLG_MTR_LVL_NUM"));
    }
    // mod end 2016/09/23 CSA Defect#12178

    private int getBllgMtrLbInd(List<Map<String, Object>> bllgMtrMapList, String glblCmpyCd, BigDecimal dsContrDtlPk, String mdlMtrLbCd) {
        String bllgMtrLbCd = NSAL0320Query.getInstance().getBllgMtrLbCdForMach(glblCmpyCd, dsContrDtlPk, mdlMtrLbCd);
        if (!ZYPCommonFunc.hasValue(bllgMtrLbCd)) {
            return 0;
        }
        int rtnIdx = 0;
        for (Map<String, Object> bllgMtrMap : bllgMtrMapList) {
            if (bllgMtrLbCd.equals((String) bllgMtrMap.get("BLLG_MTR_LB_CD"))) {
                break;
            }
            rtnIdx++;
        }
        return rtnIdx;
    }
    // add end 2016/05/18 CSA Defect#4958
}
