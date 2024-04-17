/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0350;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSMsg;
import business.blap.NSAL0350.common.NSAL0350CommonLogic;
import business.blap.NSAL0350.constant.NSAL0350Constant;
import business.db.SVC_INV_CHRG_TPTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/10   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Hitachi         A.Kohinata      Update          N/A
 * 2015/12/07   Hitachi         A.Kohinata      Update          QC1537
 * 2015/12/10   Hitachi         A.Kohinata      Update          QC1718
 * 2016/01/21   Hitachi         K.Kishimoto     Update          QC3309
 * 2016/11/08   Hitachi         K.Ochiai        Update          QC15232
 * 2016/11/30   Hitachi         K.Ochiai        Update          QC14204
 * 2017/08/07   Hitachi         K.Kitachi       Update          QC#20048
 * 2017/09/12   Hitachi         U.Kim           Update          QC#20071
 * 2017/10/17   Hitachi         U.Kim           Update          QC#21699
 * 2017/12/06   Hitachi         U.Kim           Update          QC#23001
 * 2018/06/21   Fujitsu         T.Ogura         Update          QC#21347
 * 2018/06/29   Hitachi         U.Kim           Update          QC#26933
 * 2018/08/02   Hitachi         K.Kojima        Update          QC#27487
 * 2022/10/12   Hitachi         T.Doi           Update          QC#60007
 *</pre>
 */
public class NSAL0350BL02 extends S21BusinessHandler {

    /**
     * Method name: doProcess <dd>The method explanation: Call each
     * process by screen id. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NSAL0350CMsg bizMsg = (NSAL0350CMsg) cMsg;
        NSAL0350SMsg sharedMsg = (NSAL0350SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0350_INIT".equals(screenAplID)) {
                doProcess_NSAL0350_INIT(bizMsg, sharedMsg);
            } else if ("NSAL0350Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0350_INIT(bizMsg, sharedMsg);
            } else if ("NSAL0350Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0350Scrn00_CMN_Submit(bizMsg, sharedMsg);
            // START 2017/08/07 K.Kitachi [QC#20048, ADD]
            } else if ("NSAL0350Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0350Scrn00_Search(bizMsg, sharedMsg);
            // END 2017/08/07 K.Kitachi [QC#20048, ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(bizMsg, sharedMsg);
        }
    }

    /**
     * Method name: doProcess_NSAL0350_INIT <dd>The method
     * explanation: Initializing. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0350_INIT(NSAL0350CMsg cMsg, NSAL0350SMsg sMsg) {

        // START 2015/12/10 [QC1718, MOD]
        if (!ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk_H1)) {
        // END 2015/12/10 [QC1718, MOD]
            String[] args = {"No input parameter" };
            EZDMessageInfo msg = new EZDMessageInfo(NSAL0350Constant.NSAM0353E, args, 1);
            ZYPEZDItemValueSetter.setValue(cMsg.xxAbendMsgTxt_H1, msg.getMessage());
            return;
        }

        if (ZYPCommonFunc.hasValue(cMsg.svcInvChrgTpCd_H1)) {
            SVC_INV_CHRG_TPTMsg tMsg = NSAL0350CommonLogic.getSvcInvChrgTp(getGlobalCompanyCode(), cMsg.svcInvChrgTpCd_H1.getValue());
            if (tMsg == null) {
                String[] args = {"Invalid parameter" };
                EZDMessageInfo msg = new EZDMessageInfo(NSAL0350Constant.NSAM0353E, args, 1);
                ZYPEZDItemValueSetter.setValue(cMsg.xxAbendMsgTxt_H1, msg.getMessage());
                return;
            }
        }
        // START 2017/08/07 K.Kitachi [QC#20048, ADD]
        NSAL0350CommonLogic.clearSearchCriteria(cMsg);
        // END 2017/08/07 K.Kitachi [QC#20048, ADD]
        // START 2015/10/15 [N/A, ADD]
        NSAL0350CommonLogic.createScheduleRowPulldownList(cMsg);
        // END 2015/10/15 [N/A, ADD]
        // START 2017/10/17 U.Kim [QC#21699, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_HD, ZYPConstant.FLG_ON_Y);
        // END 2017/10/17 U.Kim [QC#21699, ADD]
        search(cMsg, sMsg);
    }

    /**
     * Method name: doProcess_NSAL0350Scrn00_CMN_Submit <dd>The method
     * explanation: Submit.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0350Scrn00_CMN_Submit(NSAL0350CMsg cMsg, NSAL0350SMsg sMsg) {
        search(cMsg, sMsg);
    }

    @SuppressWarnings("unchecked")
    private void search(NSAL0350CMsg cMsg, NSAL0350SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        S21SsmEZDResult res = NSAL0350Query.getInstance().getDsContrDtlInfo(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue());
        if (!res.isCodeNormal()) {
            String[] args = {"The DS Contract Detail pk" };
            EZDMessageInfo msg = new EZDMessageInfo(NSAL0350Constant.NSAM0354E, args, 1);
            ZYPEZDItemValueSetter.setValue(cMsg.xxAbendMsgTxt_H1, msg.getMessage());
            return;
        }

        List list = (List) res.getResultObject();
        Map map = (Map) list.get(0);
        setDsContrDtlInfo(cMsg, map);

        // START 2017/09/12 U.Kim [QC#20071, ADD]
        String dsContrDtlTpCd = (String) map.get("DS_CONTR_DTL_TP_CD");
        // END 2017/09/12 U.Kim [QC#20071, ADD]
        // START 2017/09/12 U.Kim [QC#20071, MOD]
        // searchSchedules(cMsg, sMsg);
        searchSchedules(cMsg, sMsg, dsContrDtlTpCd);
        // END 2017/09/12 U.Kim [QC#20071, MOD]

        EZDMsg.copy(cMsg, null, sMsg, null);
    }

    // START 2017/09/12 U.Kim [QC#20071, MOD]
    // private void searchSchedules(NSAL0350CMsg cMsg, NSAL0350SMsg sMsg) {
    private void searchSchedules(NSAL0350CMsg cMsg, NSAL0350SMsg sMsg, String dsContrDtlTpCd) {
    // END 2017/09/12 U.Kim [QC#20071, MOD]
        // START 2017/08/07 K.Kitachi [QC#20048, ADD]
        ZYPTableUtil.clear(cMsg.O);
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(cMsg.D);
        ZYPTableUtil.clear(cMsg.E);
        ZYPTableUtil.clear(cMsg.F);
        ZYPTableUtil.clear(cMsg.G);
        ZYPTableUtil.clear(cMsg.H);
        ZYPTableUtil.clear(cMsg.I);
        ZYPTableUtil.clear(cMsg.J);
        // END 2017/08/07 K.Kitachi [QC#20048, ADD]

        String glblCmpyCd = getGlobalCompanyCode();

        if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(cMsg.svcInvChrgTpCd_H1.getValue())) {
            // START 2017/09/12 U.Kim [QC#20071, MOD]
            // searchBaseSchedules(cMsg, glblCmpyCd);
            searchBaseSchedules(cMsg, glblCmpyCd, dsContrDtlTpCd);
            // END 2017/09/12 U.Kim [QC#20071, MOD]
        } else if (SVC_INV_CHRG_TP.METER_CHARGE.equals(cMsg.svcInvChrgTpCd_H1.getValue())) {
            // START 2018/08/02 K.Kojima [QC#27487,MOD]
            // searchUsageSchedules(cMsg, glblCmpyCd);
            searchUsageSchedules(cMsg, glblCmpyCd, dsContrDtlTpCd);
            // END 2018/08/02 K.Kojima [QC#27487,MOD]
        }

        // START 2017/08/07 K.Kitachi [QC#20048, ADD]
        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NSAL0350Constant.NSAM0200I);
        }
        // END 2017/08/07 K.Kitachi [QC#20048, ADD]
    }

    // START 2017/08/07 K.Kitachi [QC#20048, ADD]
    private void doProcess_NSAL0350Scrn00_Search(NSAL0350CMsg cMsg, NSAL0350SMsg sMsg) {
        search(cMsg, sMsg);
    }
    // END 2017/08/07 K.Kitachi [QC#20048, ADD]

    @SuppressWarnings("unchecked")
    // START 2017/09/12 U.Kim [QC#20071, MOD]
    // private void searchBaseSchedules(NSAL0350CMsg cMsg, String glblCmpyCd) {
    private void searchBaseSchedules(NSAL0350CMsg cMsg, String glblCmpyCd, String dsContrDtlTpCd) {
    // END 2017/09/12 U.Kim [QC#20071, MOD]
        // START 2018/06/29 U.Kim [QC#26933, MOD]
        // int maxListCnt = cMsg.O.length();
        int maxListCnt = cMsg.O.length() - NSAL0350Constant.INT_200;
        // END 2018/06/29 U.Kim [QC#26933, MOD]
        // START 2017/10/17 U.Kim [QC#21699, MOD]
        // START 2015/10/15 [N/A, MOD]
        // START 2017/08/07 K.Kitachi [QC#20048, MOD]
//        S21SsmEZDResult res = NSAL0350Query.getInstance().getBaseScheduleInfoList(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue(), cMsg.dsContrPrcEffPk_H1.getValue(), maxListCnt + 1);
        // START 2017/09/12 U.Kim [QC#20071, MOD]
        // S21SsmEZDResult res = NSAL0350Query.getInstance().getBaseScheduleInfoList(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue(), cMsg.dsContrPrcEffPk_H1.getValue(), cMsg.bllgSchdFromDt_HD.getValue(), cMsg.bllgSchdThruDt_HD.getValue(), cMsg.dsBllgSchdStsCd_HD.getValue(), cMsg.skipRecovTpCd_HD.getValue(), maxListCnt + 1);
        //S21SsmEZDResult res = NSAL0350Query.getInstance().getBaseScheduleInfoList(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue(), cMsg.dsContrPrcEffPk_H1.getValue(), cMsg.bllgSchdFromDt_HD.getValue(), cMsg.bllgSchdThruDt_HD.getValue(), cMsg.dsBllgSchdStsCd_HD.getValue(), cMsg.skipRecovTpCd_HD.getValue(), maxListCnt + 1, dsContrDtlTpCd);
        S21SsmEZDResult res = NSAL0350Query.getInstance().getBaseScheduleInfoList(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue(), cMsg.dsContrPrcEffPk_H1.getValue(), cMsg.bllgSchdFromDt_HD.getValue(), cMsg.bllgSchdThruDt_HD.getValue(), cMsg.dsBllgSchdStsCd_HD.getValue(), cMsg.skipRecovTpCd_HD.getValue(), maxListCnt + 1, dsContrDtlTpCd, cMsg.xxChkBox_HD.getValue());
        // END 2017/09/12 U.Kim [QC#20071, MOD]
        // END 2017/08/07 K.Kitachi [QC#20048, MOD]
        // END 2015/10/15 [N/A, MOD]
        // START 2017/08/07 K.Kitachi [QC#20048, ADD]
        // END 2017/10/17 U.Kim [QC#21699, MOD]
        if (res.getQueryResultCount() == 0) {
            cMsg.setMessageInfo(NSAL0350Constant.NSAM0194I);
            return;
        }
        // END 2017/08/07 K.Kitachi [QC#20048, ADD]
        if (res.isCodeNormal()) {
            String tblNm = NSAL0350Constant.BASE_TBL_NM;
            List list = (List) res.getResultObject();
            int listSize = list.size();

            if (listSize > maxListCnt) {
                listSize = maxListCnt;
                cMsg.setMessageInfo(NSAL0350Constant.NSAM0024W, new String[] {String.valueOf(maxListCnt), String.valueOf(maxListCnt) });
            }

            for (int i = 0; i < listSize; i++) {
                Map map = (Map) list.get(i);
                NSAL0350_OCMsg ocMsg = cMsg.O.no(i);
                setScheduleInfo(map, tblNm, ocMsg, cMsg);
                // START 2015/10/15 [N/A, DEL]
                //NSAL0350CommonLogic.createScheduleRowPulldownList(ocMsg, tblNm);
                // END 2015/10/15 [N/A, DEL]
            }
            cMsg.O.setValidCount(listSize);
        }
    }

    @SuppressWarnings("unchecked")
    // START 2018/08/02 K.Kojima [QC#27487,MOD]
    // private void searchUsageSchedules(NSAL0350CMsg cMsg, String glblCmpyCd) {
    private void searchUsageSchedules(NSAL0350CMsg cMsg, String glblCmpyCd, String dsContrDtlTpCd) {
    // END 2018/08/02 K.Kojima [QC#27487,MOD]
        S21SsmEZDResult res = NSAL0350Query.getInstance().getMtrInfoList(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue());
        // START 2017/08/07 K.Kitachi [QC#20048, ADD]
        if (res.getQueryResultCount() == 0) {
            cMsg.setMessageInfo(NSAL0350Constant.NSAM0194I);
            return;
        }
        // END 2017/08/07 K.Kitachi [QC#20048, ADD]
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            int listSize = list.size();
            int maxListCnt = NSAL0350Constant.USAGE_TBL_NM_ARRAY.length;

            if (listSize > maxListCnt) {
                listSize = maxListCnt;
                cMsg.setMessageInfo(NSAL0350Constant.NSAM0360W, new String[] {"Meter", String.valueOf(maxListCnt), String.valueOf(maxListCnt) });
            }

            for (int i = 0; i < listSize; i++) {
                String tblNm = NSAL0350Constant.USAGE_TBL_NM_ARRAY[i];
                // START 2018/08/02 K.Kojima [QC#27487,MOD]
                // searchUsageSchedules(cMsg, glblCmpyCd, (Map) list.get(i), tblNm);
                searchUsageSchedules(cMsg, glblCmpyCd, (Map) list.get(i), tblNm, dsContrDtlTpCd);
                // END 2018/08/02 K.Kojima [QC#27487,MOD]
            }

            // START 2017/08/07 K.Kitachi [QC#20048, ADD]
            int srchRsltCnt = 0;
            srchRsltCnt = srchRsltCnt + cMsg.A.getValidCount();
            srchRsltCnt = srchRsltCnt + cMsg.B.getValidCount();
            srchRsltCnt = srchRsltCnt + cMsg.C.getValidCount();
            srchRsltCnt = srchRsltCnt + cMsg.D.getValidCount();
            srchRsltCnt = srchRsltCnt + cMsg.E.getValidCount();
            srchRsltCnt = srchRsltCnt + cMsg.F.getValidCount();
            srchRsltCnt = srchRsltCnt + cMsg.G.getValidCount();
            srchRsltCnt = srchRsltCnt + cMsg.H.getValidCount();
            srchRsltCnt = srchRsltCnt + cMsg.I.getValidCount();
            srchRsltCnt = srchRsltCnt + cMsg.J.getValidCount();
            if (srchRsltCnt == 0) {
                cMsg.setMessageInfo(NSAL0350Constant.NSAM0194I);
            }
            // END 2017/08/07 K.Kitachi [QC#20048, ADD]
        }
    }

    @SuppressWarnings("unchecked")
    // START 2018/08/02 K.Kojima [QC#27487,MOD]
    // private void searchUsageSchedules(NSAL0350CMsg cMsg, String glblCmpyCd, Map mtrInfoMap, String tblNm) {
    private void searchUsageSchedules(NSAL0350CMsg cMsg, String glblCmpyCd, Map mtrInfoMap, String tblNm, String dsContrDtlTpCd) {
    // END 2018/08/02 K.Kojima [QC#27487,MOD]
        BigDecimal dsContrBllgMtrPk = (BigDecimal) mtrInfoMap.get("DS_CONTR_BLLG_MTR_PK");
        String mtrLbNm = (String) mtrInfoMap.get("MTR_LB_NM");

        EZDMsgArray ezdMsgArray = NSAL0350CommonLogic.getTableArrayFromEZDMsg(cMsg, tblNm);
        // START 2018/06/29 U.Kim [QC#26933, MOD]
        // int maxListCnt = ezdMsgArray.length();
        int maxListCnt = ezdMsgArray.length() - NSAL0350Constant.INT_200;
        // END 2018/06/29 U.Kim [QC#26933, MOD]
        // START 2017/10/17 U.Kim [QC#21699, MOD]
        // START 2015/10/15 [N/A, MOD]
        // START 2017/08/07 K.Kitachi [QC#20048, MOD]
//        S21SsmEZDResult res = NSAL0350Query.getInstance().getUsageScheduleInfoList(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue(), cMsg.dsContrPrcEffPk_H1.getValue(), dsContrBllgMtrPk, maxListCnt + 1);
        //S21SsmEZDResult res = NSAL0350Query.getInstance().getUsageScheduleInfoList(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue(), cMsg.dsContrPrcEffPk_H1.getValue(), dsContrBllgMtrPk, cMsg.bllgSchdFromDt_HD.getValue(), cMsg.bllgSchdThruDt_HD.getValue(), cMsg.dsBllgSchdStsCd_HD.getValue(), cMsg.skipRecovTpCd_HD.getValue(), maxListCnt + 1);
        // START 2018/08/02 K.Kojima [QC#27487,MOD]
        // S21SsmEZDResult res = NSAL0350Query.getInstance().getUsageScheduleInfoList(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue(), cMsg.dsContrPrcEffPk_H1.getValue(), dsContrBllgMtrPk, cMsg.bllgSchdFromDt_HD.getValue(), cMsg.bllgSchdThruDt_HD.getValue(), cMsg.dsBllgSchdStsCd_HD.getValue(), cMsg.skipRecovTpCd_HD.getValue(), maxListCnt + 1, cMsg.xxChkBox_HD.getValue());
        S21SsmEZDResult res = NSAL0350Query.getInstance().getUsageScheduleInfoList(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue(), cMsg.dsContrPrcEffPk_H1.getValue(),
                dsContrBllgMtrPk,cMsg.bllgSchdFromDt_HD.getValue(), cMsg.bllgSchdThruDt_HD.getValue(), cMsg.dsBllgSchdStsCd_HD.getValue(), cMsg.skipRecovTpCd_HD.getValue(),
                maxListCnt + 1, cMsg.xxChkBox_HD.getValue(), dsContrDtlTpCd);
        // END 2018/08/02 K.Kojima [QC#27487,MOD]
        // END 2017/08/07 K.Kitachi [QC#20048, MOD]
        // END 2015/10/15 [N/A, MOD]
        // END 2017/10/17 U.Kim [QC#21699, MOD]
        if (res.isCodeNormal()) {

            List list = (List) res.getResultObject();
            int listSize = list.size();

            if (listSize > maxListCnt) {
                listSize = maxListCnt;
                cMsg.setMessageInfo(NSAL0350Constant.NSAM0024W, new String[] {String.valueOf(maxListCnt), String.valueOf(maxListCnt) });
            }

            for (int i = 0; i < listSize; i++) {
                Map map = (Map) list.get(i);
                EZDMsg ezdMsg = ezdMsgArray.get(i);
                setScheduleInfo(map, tblNm, ezdMsg, cMsg);
                // START 2015/10/15 [N/A, DEL]
                //NSAL0350CommonLogic.createScheduleRowPulldownList(ezdMsg, tblNm);
                // END 2015/10/15 [N/A, DEL]
            }
            ezdMsgArray.setValidCount(listSize);

            ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getBigDecimalValueFromEZDMsg(cMsg, tblNm, "dsContrBllgMtrPk_A"), dsContrBllgMtrPk);
            ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getStringValueFromEZDMsg(cMsg, tblNm, "mtrLbNm_A"), mtrLbNm);
        }
    }

    @SuppressWarnings("unchecked")
    private void setScheduleInfo(Map map, String tblNm, EZDMsg ezdMsg, NSAL0350CMsg cMsg) {
        ezdMsg.clear();

        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ezUpTime_A1"), (String) map.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ezUpTimeZone_A1"), (String) map.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdPk_A1"), (BigDecimal) map.get("DS_CONTR_BLLG_SCHD_PK"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSmryPk_A1"), (BigDecimal) map.get("DS_CONTR_BLLG_SCHD_SMRY_PK"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgMtrPk_A1"), (BigDecimal) map.get("DS_CONTR_BLLG_MTR_PK"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSqNum_A1"), (String) map.get("DS_CONTR_BLLG_SCHD_SQ_NUM"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdLvlNum_A1"), (String) map.get("DS_CONTR_BLLG_SCHD_LVL_NUM"));
        // START 2015/10/15 [N/A, ADD]
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrPrcEffSqNum_A1"), (BigDecimal) map.get("DS_CONTR_PRC_EFF_SQ_NUM"));
        // END 2015/10/15 [N/A, ADD]
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "skipRecovTpCd_A3"), (String) map.get("SKIP_RECOV_TP_CD"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdFromDt_A1"), (String) map.get("BLLG_SCHD_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdThruDt_A1"), (String) map.get("BLLG_SCHD_THRU_DT"));
        if (ZYPConstant.FLG_ON_Y.equals((String) map.get("BLLBL_FLG"))) {
            // START 2016/11/08 [QC#15232, MOD]
            ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "nextBllgDt_A1"), (String) map.get("INTF_DT"));
            // END 2016/11/08 [QC#15232, MOD]
        } else {
            NSAL0350CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "nextBllgDt_A1").clear();
        }
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "nextBllgDt_A2"), (String) map.get("NEXT_BLLG_DT"));
        // START 2016/11/08 [QC#15232, ADD]
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "rvsSchdDt_A1"), (String) map.get("RVS_SCHD_DT"));
        // END 2016/11/08 [QC#15232, ADD]
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "basePrcDealAmt_A1"), (BigDecimal) map.get("BASE_PRC_DEAL_AMT"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "mtrEntryCpltFlg_A1"), (String) map.get("MTR_ENTRY_CPLT_FLG"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsBllgSchdStsCd_A1"), (String) map.get("DS_BLLG_SCHD_STS_CD"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsBllgSchdStsDescTxt_A1"), (String) map.get("DS_BLLG_SCHD_STS_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "svcInvNum_A1"), (String) map.get("SVC_INV_NUM"));
        // START 2017/12/06 U.Kim [QC#23001,ADD]
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "eipRptRqstPk_A1"), (BigDecimal) map.get("EIP_RPT_RQST_PK"));
        // END 2017/12/06 U.Kim [QC#23001,ADD]
        // START 2018/06/21 T.Ogura [QC#21347,MOD]
//        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "invDt_A1"), (String) map.get("INV_DT"));
        String invDate = (String) map.get("INV_DT");
        String intfDate = (String) map.get("INTF_DT");
        String salesDate = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(invDate)) {
            ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "invDt_A1"), invDate);
        } else {
            if (ZYPCommonFunc.hasValue(intfDate) && intfDate.compareTo(salesDate) >= 0) {
                ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "invDt_A1"), intfDate);
            } else {
                ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "invDt_A1"), salesDate);
            }
        }
        // END   2018/06/21 T.Ogura [QC#21347,MOD]
        // START 2016/01/21 [QC3309, MOD]
//        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "baseActlPrcDealAmt_A1"), (BigDecimal) map.get("BASE_ACTL_PRC_DEAL_AMT"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "baseActlPrcDealAmt_A1"), (BigDecimal) map.get("ACTL_PRC_DEAL_AMT"));
        // END 2016/01/21 [QC3309, MOD]
        // START 2015/10/15 [N/A, ADD]
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dealTaxAmt_A1"), (BigDecimal) map.get("DEAL_TAX_AMT"));
        // END 2015/10/15 [N/A, ADD]
        // START 2016/11/30 [QC#14204, ADD]
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxRecHistCratTs_A1"), (String) map.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxRecHistCratByNm_A1"), (String) map.get("XX_REC_HIST_CRAT_BY_NM"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxRecHistUpdTs_A1"), (String) map.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxRecHistUpdByNm_A1"), (String) map.get("XX_REC_HIST_UPD_BY_NM"));
        ZYPEZDItemValueSetter.setValue(NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxRecHistTblNm_A1"), (String) map.get("XX_REC_HIST_TBL_NM"));
        // END 2016/11/30 [QC#14204, ADD]

    }

    @SuppressWarnings("unchecked")
    private void setDsContrDtlInfo(NSAL0350CMsg cMsg, Map map) {
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrPk_H1, (BigDecimal) map.get("DS_CONTR_PK"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrDtlPk_H1, (BigDecimal) map.get("DS_CONTR_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrNum_H1, (String) map.get("DS_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.serNum_H1, (String) map.get("SER_NUM"));
        // START 2022/10/12 T.Doi [QC#60007, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrPk_H1, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt_H1, (String) map.get("MDSE_DESC_SHORT_TXT"));
        // END 2022/10/12 T.Doi [QC#60007, ADD]
       // START 2015/12/07 [QC1537, MOD]
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_H1, (String) map.get("DS_CONTR_CTRL_STS_CD"));
       // END 2015/12/07 [QC1537, MOD]
        if (availableUpdate(cMsg)) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxEdtModeFlg_H1, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.xxEdtModeFlg_H1, ZYPConstant.FLG_OFF_N);
        }
    }

    private boolean availableUpdate(NSAL0350CMsg cMsg) {
       // START 2015/12/07 [QC1537, MOD]
        String dsContrCtrlStsCd = cMsg.dsContrCtrlStsCd_H1.getValue();
        if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd)
                || DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlStsCd)
                || DS_CONTR_CTRL_STS.QA_HOLD.equals(dsContrCtrlStsCd)) {
            return true;
        }
        return false;
       // END 2015/12/07 [QC1537, MOD]
    }

}
