/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0430;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import business.blap.NSAL0430.common.NSAL0430CommonLogic;
import business.blap.NSAL0430.constant.NSAL0430Constant;

import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;
import com.canon.cusa.s21.common.NSX.NSXC003001.SvcPhysMtrReadInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            SRAA            Create          N/A
 * 2015/12/14   Hitachi         K.Kasai         Update          QC1896
 * 2015/12/15   Hitachi         K.Kasai         Update          QC1899
 * 2016/09/16   Hitachi         N.Arai          Update          QC#11616
 * 2017/09/06   Hitachi         A.Kohinata      Update          QC#15134
 * 2018/12/28   Hitachi         S.Kitamura      Update          QC#29718
 * 2019/01/23   Hitachi         K.Kim           Update          QC#29718
 * 2019/09/19   Hitachi         A.Kohinata      Update          QC#53403
 *</pre>
 */
public class NSAL0430BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        super.preDoProcess(ezdCMsg, ezdSMsg);
        try {
            NSAL0430CMsg cMsg = (NSAL0430CMsg) ezdCMsg;
            String screenAplId = cMsg.getScreenAplID();
            if ("NSAL0430_INIT".equals(screenAplId)) {
                doProcess_NSAL0430_INIT(cMsg);
            } else if ("NSAL0430Scrn00_CMN_Clear".equals(screenAplId)) {
                doProcess_NSAL0430Scrn00_CMN_Clear(cMsg);
            } else if ("NSAL0430Scrn00_CMN_Close".equals(screenAplId)) {
                doProcess_NSAL0430Scrn00_CMN_Close(cMsg);
            } else if ("NSAL0430Scrn00_OnChange_MeterReadGroup".equals(screenAplId)) {
                doProcess_NSAL0430Scrn00_OnChange_MeterReadGroup(cMsg);
            } else if ("NSAL0430Scrn00_Populate".equals(screenAplId)) {
                doProcess_NSAL0430_NSAL0430Scrn00_Populate(cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(ezdCMsg, ezdSMsg);
        }
    }

    private void doProcess_NSAL0430_INIT(NSAL0430CMsg cMsg) {
        search(cMsg);
    }

    private void doProcess_NSAL0430Scrn00_CMN_Clear(NSAL0430CMsg cMsg) {
        search(cMsg);
    }

    private void doProcess_NSAL0430Scrn00_CMN_Close(NSAL0430CMsg cMsg) {
    }

    private void doProcess_NSAL0430Scrn00_OnChange_MeterReadGroup(NSAL0430CMsg cMsg) {
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(cMsg.D, "xxChkBox_D", ZYPConstant.FLG_ON_Y);
        if (selectedRows.size() < 2) {
            NSAL0430CommonLogic.clearEstimation(cMsg);
            return;
        }
        if (selectedRows.size() == 2) {
            int newReadRow = selectedRows.get(0);
            int oldReadRow = selectedRows.get(1);

            BigDecimal newGrpSq = cMsg.D.no(newReadRow).svcPhysMtrReadGrpSq_D.getValue();
            BigDecimal oldGrpSq = cMsg.D.no(oldReadRow).svcPhysMtrReadGrpSq_D.getValue();

            Map<BigDecimal, List<SvcPhysMtrReadInfoBean>> map = new TreeMap<BigDecimal, List<SvcPhysMtrReadInfoBean>>();

            for (int i = 0; i < cMsg.D.getValidCount(); i++) {
                BigDecimal svcPhysMtrReadGrpSq = cMsg.D.no(i).svcPhysMtrReadGrpSq_D.getValue();
                BigDecimal svcPhysMtrPk = cMsg.D.no(i).svcPhysMtrPk_D.getValue();
                if (newGrpSq.compareTo(svcPhysMtrReadGrpSq) == 0 || oldGrpSq.compareTo(svcPhysMtrReadGrpSq) == 0) {
                    List<SvcPhysMtrReadInfoBean> beanList = null;
                    if (map.containsKey(svcPhysMtrPk)) {
                        beanList = map.get(svcPhysMtrPk);
                    } else {
                        beanList = new ArrayList<SvcPhysMtrReadInfoBean>();
                        map.put(svcPhysMtrPk, beanList);
                    }
                    SvcPhysMtrReadInfoBean bean = NSAL0430CommonLogic.toBean(cMsg.D.no(i));
                    beanList.add(bean);
                }
            }
            // MOD START 2015/12/14 K.Kasai [QC1896]
            String mtrEstProcDt = NSAL0430CommonLogic.getBizIdSlsDt();
            if (ZYPCommonFunc.hasValue(cMsg.mtrEstProcDt)) {
                mtrEstProcDt = cMsg.mtrEstProcDt.getValue();
            }
            // MOD END 2015/12/14 K.Kasai [QC1896]
            // add start 2017/09/06 QC#15134
            String glblCmpyCd = getGlobalCompanyCode();
            // add end 2017/09/06 QC#15134
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                BigDecimal svcPhysMtrPk = cMsg.A.no(i).svcPhysMtrPk_A.getValue();
                if (map.containsKey(svcPhysMtrPk)) {
                    List<SvcPhysMtrReadInfoBean> mtrReadList = map.get(svcPhysMtrPk);
                    // Assume that cMsg.D is sorted in reverse order
                    // of a meter reading date
                    Collections.reverse(mtrReadList);
                    // mod start 2017/09/06 QC#15134
                    //BigDecimal avgDlyCopyVol = NSXC003001SvcPhysMtrRead.calcAvgDlyCopyVol(mtrReadList);
                    BigDecimal avgDlyCopyVol = NSXC003001SvcPhysMtrRead.calcAvgDlyCopyVol(glblCmpyCd, mtrReadList);
                    // mod end 2017/09/06 QC#15134
                    // MOD START 2015/12/14 K.Kasai [QC1896]
                    // BigDecimal estMtrCnt =
                    // NSXC003001SvcPhysMtrRead.estMtrCnt(slsDt,
                    // mtrReadList);
                    // mod start 2017/09/06 QC#15134
                    //BigDecimal estMtrCnt = NSXC003001SvcPhysMtrRead.estMtrCnt(mtrEstProcDt, mtrReadList);
                    // mod start 2019/09/19 QC#53403
                    //BigDecimal estMtrCnt = NSXC003001SvcPhysMtrRead.estMtrCnt(glblCmpyCd, mtrEstProcDt, mtrReadList);
                    SvcPhysMtrReadInfoBean latestMtrRead = new SvcPhysMtrReadInfoBean();
                    for (int j = 0; j < cMsg.D.getValidCount(); j++) {
                        if (svcPhysMtrPk.compareTo(cMsg.D.no(j).svcPhysMtrPk_D.getValue()) == 0 ) {
                            latestMtrRead = NSAL0430CommonLogic.toBean(cMsg.D.no(i));
                            break;
                        }
                    }
                    BigDecimal estMtrCnt = NSXC003001SvcPhysMtrRead.estMtrCnt(glblCmpyCd, mtrEstProcDt, avgDlyCopyVol, latestMtrRead);
                    // mod end 2019/09/19 QC#53403
                    // mod end 2017/09/06 QC#15134
                    // MOD END 2015/12/14 K.Kasai [QC1896]
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxCopyUnitDaysQty_AE, avgDlyCopyVol);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).readMtrCnt_AE, estMtrCnt);
                }
            }
        }
        if (selectedRows.size() > 2) {
            int lastCheckedIndex = cMsg.xxRowNum.getValueInt();
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.D.no(lastCheckedIndex).xxChkBox_D.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.D.no(lastCheckedIndex).xxChkBox_D, ZYPConstant.FLG_OFF_N);
                cMsg.setMessageInfo(NSAL0430Constant.NSAM0334E);
            }
        }
    }

    private void doProcess_NSAL0430_NSAL0430Scrn00_Populate(NSAL0430CMsg cMsg) {
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(cMsg.D, "xxChkBox_D", ZYPConstant.FLG_ON_Y);
        if (selectedRows.size() < 2) {
            cMsg.setMessageInfo(NSAL0430Constant.NSAM0335E);
            return;
        }
        int newReadRow = selectedRows.get(0);
        int oldReadRow = selectedRows.get(1);
        BigDecimal newSq = cMsg.D.no(newReadRow).svcPhysMtrReadGrpSq_D.getValue();
        BigDecimal oldSq = cMsg.D.no(oldReadRow).svcPhysMtrReadGrpSq_D.getValue();
        // MOD START 2015/12/15 K.Kasai [QC#1899]
        // String newDt =
        // cMsg.D.no(newReadRow).mtrReadDt_D.getValue();
        // String oldDt =
        // cMsg.D.no(oldReadRow).mtrReadDt_D.getValue();
        String newDt = ZYPDateUtil.formatEzd8ToDisp(cMsg.D.no(newReadRow).mtrReadDt_D.getValue());
        String oldDt = ZYPDateUtil.formatEzd8ToDisp(cMsg.D.no(oldReadRow).mtrReadDt_D.getValue());
        // MOD START 2015/12/25 K.Kasai [QC#1899]
        BigDecimal newCnt = null;
        BigDecimal oldCnt = null;

        // TODO
        // ZYPCodeDataUtil.getVarCharConstValue("",
        // getGlobalCompanyCode());
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.P.no(i).svcPhysMtrPk, cMsg.A.no(i).svcPhysMtrPk_A);
            ZYPEZDItemValueSetter.setValue(cMsg.P.no(i).readMtrCnt, cMsg.A.no(i).readMtrCnt_AE);
            newCnt = NSAL0430CommonLogic.getReadMtrCnt(cMsg.D, cMsg.A.no(i).svcPhysMtrPk_A.getValue(), newSq);
            oldCnt = NSAL0430CommonLogic.getReadMtrCnt(cMsg.D, cMsg.A.no(i).svcPhysMtrPk_A.getValue(), oldSq);
            String txt = String.format("%s[%s/%s to %s/%s]", "", newDt, newCnt.toPlainString(), oldDt, oldCnt.toPlainString());
            ZYPEZDItemValueSetter.setValue(cMsg.P.no(i).mtrEntryCmntTxt, txt);
        }
        cMsg.P.setValidCount(cMsg.A.getValidCount());
    }

    private void search(NSAL0430CMsg cMsg) {

        NSAL0430CommonLogic.resetParameter(cMsg);

        NSAL0430CommonLogic.checkParameter(cMsg);
        if (NSAL0430CommonLogic.hasError(cMsg)) {
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        NSAL0430Query query = NSAL0430Query.getInstance();
        S21SsmEZDResult rslt = null;

        rslt = query.getDsMtrReadTpGrp(glblCmpyCd);
        if (rslt != null && rslt.isCodeNormal()) {
            int rsltCnt = rslt.getQueryResultCount();
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                String dsMtrReadTpGrpCd = (String) rsltMap.get("DS_MTR_READ_TP_GRP_CD");
                String dsMtrReadTpGrpDescTxt = (String) rsltMap.get("DS_MTR_READ_TP_GRP_DESC_TXT");
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).dsMtrReadTpGrpCd_B, dsMtrReadTpGrpCd);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxGenlFldAreaTxt_B, String.format("%s - %s", dsMtrReadTpGrpCd, dsMtrReadTpGrpDescTxt));
            }
            cMsg.B.setValidCount(rsltCnt);
        }

        rslt = query.getSvcMachMstrInfo(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
        if (rslt != null && rslt.isCodeNormal()) {
            Map<String, Object> rsltMap = (Map<String, Object>) rslt.getResultObject();
            ZYPEZDItemValueSetter.setValue(cMsg.serNum, (String) rsltMap.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, (String) rsltMap.get("MDSE_CD"));
            // START 2016/09/15 N.Arai [QC#11616, MOD]
            // ZYPEZDItemValueSetter.setValue(cMsg.mdseNm, (String)
            // rsltMap.get("MDSE_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
            // END 2016/09/15 N.Arai [QC#11616, MOD]
            ZYPEZDItemValueSetter.setValue(cMsg.mdlId, (BigDecimal) rsltMap.get("MDL_ID"));
            ZYPEZDItemValueSetter.setValue(cMsg.mdlNm, (String) rsltMap.get("MDL_NM"));
        } else {
            cMsg.setMessageInfo(NSAL0430Constant.NSAM0219E, new String[] {"Machine info on the input parameter is not correct." });
            EZDDebugOutput.println(3, String.format("svcMachMstrPk=%s", cMsg.svcMachMstrPk.getValue()), NSAL0430BL02.class);
            return;
        }

        rslt = query.getPhysMtrLb(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).mtrLbCd_C, (String) rsltMap.get("MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).mtrLbDescTxt_C, (String) rsltMap.get("MTR_LB_DESC_TXT"));
            }
            cMsg.C.setValidCount(rsltCnt);

            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).svcPhysMtrPk_A, (BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mtrLbCd_A, (String) rsltMap.get("MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mtrLbDescTxt_A, (String) rsltMap.get("MTR_LB_DESC_TXT"));
            }
            cMsg.A.setValidCount(rsltCnt);
        }

        // START 2018/12/28 S.Kitamura [QC#29718,MOD]
        // rslt = query.getSvcPhysMtrRead(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), cMsg.D.length() + 1);
        rslt = query.getSvcPhysMtrRead(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
        // END 2018/12/28 S.Kitamura [QC#29718,MOD]
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            // START 2018/12/28 S.Kitamura [QC#29718,MOD]
            int rsltCntForView = rsltCnt;
            if (rsltCntForView > cMsg.D.length()) {
                // START 2019/01/23 [QC#29718,MOD]
                // rsltCntForView = cMsg.D.length();
                rsltCntForView = cMsg.D.length() - (cMsg.D.length() % cMsg.A.getValidCount());
                // END 2019/01/23 [QC#29718,MOD]
                cMsg.setMessageInfo(NSAL0430Constant.NZZM0001W);
            }
            for (int i = 0; i < rsltCntForView; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).svcPhysMtrReadGrpSq_D, (BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_GRP_SQ"));
                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).mtrReadDt_D, (String) rsltMap.get("MTR_READ_DT"));
                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).dsMtrReadTpGrpCd_D, (String) rsltMap.get("DS_MTR_READ_TP_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).dsMtrReadTpCd_D, (String) rsltMap.get("DS_MTR_READ_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).dsMtrReadTpDescTxt_D, (String) rsltMap.get("DS_MTR_READ_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).svcPhysMtrReadPk_D, (BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).svcPhysMtrPk_D, (BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).mtrLbCd_D, (String) rsltMap.get("MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).readMtrCnt_D, (BigDecimal) rsltMap.get("READ_MTR_CNT"));
                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).xxCopyUnitDaysQty_DH, BigDecimal.ZERO);
            }
            cMsg.D.setValidCount(rsltCntForView);
            // END 2018/12/28 S.Kitamura [QC#29718,MOD]

            ZYPEZDItemValueSetter.setValue(cMsg.D.no(0).xxChkBox_D, ZYPConstant.FLG_ON_Y);

            if (cMsg.D.getValidCount() > 1) {
                // START 2018/12/28 S.Kitamura [QC#29718,MOD]
                BigDecimal newGrpSq = (BigDecimal) rsltList.get(0).get("SVC_PHYS_MTR_READ_GRP_SQ");
                BigDecimal oldGrpSq = (BigDecimal) rsltList.get(rsltCnt - 1).get("SVC_PHYS_MTR_READ_GRP_SQ");
                Map<BigDecimal, List<SvcPhysMtrReadInfoBean>> map = new TreeMap<BigDecimal, List<SvcPhysMtrReadInfoBean>>();
                for (int i = 0; i < rsltCnt; i++) {
                    Map<String, Object> rsltMap = rsltList.get(i);
                    BigDecimal svcPhysMtrReadGrpSq = (BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_GRP_SQ");
                    BigDecimal svcPhysMtrPk = (BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK");
                    if (newGrpSq.compareTo(svcPhysMtrReadGrpSq) == 0) {
                        List<SvcPhysMtrReadInfoBean> mtrReadList = new ArrayList<SvcPhysMtrReadInfoBean>();
                        map.put(svcPhysMtrPk, mtrReadList);
                        SvcPhysMtrReadInfoBean bean = NSAL0430CommonLogic.toBean(rsltMap);
                        mtrReadList.add(bean);
                    } else {
                        break;
                    }
                }
                for (int i = rsltCnt - 1; i >= 0; i--) {
                    Map<String, Object> rsltMap = rsltList.get(i);
                    BigDecimal svcPhysMtrReadGrpSq = (BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_GRP_SQ");
                    BigDecimal svcPhysMtrPk = (BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK");
                    if (oldGrpSq.compareTo(svcPhysMtrReadGrpSq) == 0) {
                        if (map.containsKey(svcPhysMtrPk)) {
                            List<SvcPhysMtrReadInfoBean> mtrReadList = map.get(svcPhysMtrPk);
                            SvcPhysMtrReadInfoBean bean = NSAL0430CommonLogic.toBean(rsltMap);
                            mtrReadList.add(bean);
                        }
                    } else {
                        break;
                    }
                }

                // END 2018/12/28 S.Kitamura [QC#29718,MOD]
                // History ADCV
                for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                    BigDecimal svcPhysMtrPk = cMsg.A.no(i).svcPhysMtrPk_A.getValue();
                    if (map.containsKey(svcPhysMtrPk)) {
                        List<SvcPhysMtrReadInfoBean> mtrReadList = map.get(svcPhysMtrPk);
                        // Assume that cMsg.D is sorted in reverse
                        // order
                        // of a meter reading date
                        Collections.reverse(mtrReadList);
                        // mod start 2017/09/06 QC#15134
                        //BigDecimal avgDlyCopyVol = NSXC003001SvcPhysMtrRead.calcAvgDlyCopyVol(mtrReadList);
                        BigDecimal avgDlyCopyVol = NSXC003001SvcPhysMtrRead.calcAvgDlyCopyVol(glblCmpyCd, mtrReadList);
                        // mod end 2017/09/06 QC#15134
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxCopyUnitDaysQty_AH, avgDlyCopyVol);
                    }
                }

                for (int i = 0; i < cMsg.D.getValidCount(); i++) {
                    BigDecimal svcPhysMtrPk = cMsg.D.no(i).svcPhysMtrPk_D.getValue();
                    BigDecimal svcPhysMtrReadGrpPk = cMsg.D.no(i).svcPhysMtrReadGrpSq_D.getValue();
                    BigDecimal avgDlyCopyVol = NSAL0430CommonLogic.getAvgDlyCopyVol(cMsg.D, svcPhysMtrPk, svcPhysMtrReadGrpPk);
                    ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).xxCopyUnitDaysQty_DH, avgDlyCopyVol);
                }
            }
        }
    }
}
