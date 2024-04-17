/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0150;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMessageEnv;
import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NSAL0150.common.NSAL0150CommonLogic;
import business.blap.NSAL0150.constant.NSAL0150Constant;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_CATGTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_INV_TGTR_TPTMsg;
import business.db.DS_MTR_READ_TPTMsgArray;
import business.db.DS_ORD_CATGTMsg;
import business.db.MTR_READ_METHTMsg;
import business.db.SVC_CONTR_BRTMsg;
import business.db.SVC_COV_TPTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_SPLY_ORD_TP_RELNTMsg;
import business.db.SVC_SPLY_ORD_TP_RELNTMsgArray;
import business.parts.NSZC049001PMsg;
import business.parts.NSZC050001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC049001.NSZC049001;
import com.canon.cusa.s21.api.NSZ.NSZC050001.NSZC050001;
import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetContr;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001MtrCntTwoPntEst;
import com.canon.cusa.s21.common.NSX.NSXC003001.SvcPhysMtrReadInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CNTR_RESET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/03   CUSA            SRAA            Create          N/A
 * 2015/11/26   HITACHI         K.Kasai         Update          Unit Test #71
 * 2016/02/23   Hitachi         T.Tsuchida      Update          QC#4117
 * 2016/03/24   Hitachi         K.Yamada        Update          QC#4402
 * 2016/03/24   Hitachi         A.Kohinata      Update          QC#5808
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4397
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4399
 * 2016/03/29   Hitachi         T.Tomita        Update          QC#4394
 * 2016/04/27   Hitachi         T.Kanasaka      Update          QC#7583
 * 2016/06/10   Hitachi         Y.Tsuchimoto    Update          QC#9591
 * 2016/06/29   Hitachi         M.Gotou         Update          QC#10532
 * 2016/08/22   Hitachi         A.Kohinata      Update          QC#9628
 * 2016/09/07   Hitachi         T.Kanasaka      Update          QC#2870
 * 2016/09/20   Hitachi         A.Kohinata      Update          QC#12898
 * 2016/10/03   Hitachi         K.Kihsimoto     Update          QC#12274
 * 2016/10/17   Hitachi         A.Kohinata      Update          QC#15293
 * 2016/10/18   Hitachi         T.Tomita        Update          QC#15312
 * 2016/10/26   Hitachi         Y.Takeno        Update          QC#15525
 * 2016/11/24   Hitachi         Y.Takeno        Update          QC#14992
 * 2016/12/06   Hitachi         Y.Takeno        Update          QC#14992-1
 * 2016/12/06   Hitachi         Y.Takeno        Update          QC#15200
 * 2017/01/19   Hitachi         Y.Takeno        Update          QC#16644
 * 2017/08/31   Hitachi         K.Kim           Update          QC#20863
 * 2017/09/07   Hitachi         A.Kohinata      Update          QC#15134
 * 2017/09/26   Hitachi         K.Kim           Update          QC#18744
 * 2017/10/05   CITS            M.Naito         Update          QC#20440
 * 2017/12/13   Hitachi         M.Kidokoro      Update          QC#21681
 * 2018/02/22   Hitachi         U.Kim           Update          QC#24202
 * 2018/03/08   Hitachi         K.Kojima        Update          QC#24507
 * 2018/03/27   Hitachi         U.Kim           Update          QC#23071
 * 2018/04/12   Hitachi         K.Kojima        Update          QC#23602
 * 2018/05/10   Hitachi         K.Kojima        Update          QC#24817
 * 2018/05/25   CITS            M.Naito         Update          QC#15410
 * 2018/06/11   Hitachi         U.Kim           Update          QC#22480
 * 2018/06/13   Hitachi         K.Kojima        Update          QC#26452
 * 2018/06/25   Fujitsu         T.Ogura         Update          QC#26336
 * 2018/07/06   Hitachi         T.Tomita        Update          QC#26972
 * 2018/07/09   Hitachi         A.Kohinata      Update          QC#26923
 * 2018/07/20   Hitachi         A.Kohinata      Update          QC#26974
 * 2018/07/24   Hitachi         K.Kojima        Update          QC#26791
 * 2018/09/26   Hitachi         K.Kojima        Update          QC#28388
 * 2018/10/15   Hitachi         K.Kitachi       Update          QC#28652
 * 2018/10/15   Hitachi         K.Kojima        Update          QC#28748
 * 2018/10/16   Hitachi         K.Kojima        Update          QC#28392-1
 * 2018/10/18   Hitachi         K.Kojima        Update          QC#28848
 * 2018/11/06   Hitachi         S.Kitamura      Update          QC#28868
 * 2018/12/13   Hitachi         S.Kitamura      Update          QC#28860
 * 2019/02/18   Hitachi         S.Kitamura      Update          QC#30339
 * 2019/02/20   Hitachi         S.Kitamura      Update          QC#30431
 * 2019/03/27   Hitachi         T.Tomita        Update          QC#30791
 * 2019/04/05   Hitachi         K.Kitachi       Update          QC#30921
 * 2019/04/09   Hitachi         K.Kitachi       Update          QC#31089
 * 2019/04/09   Hitachi         K.Kitachi       Update          QC#31139
 * 2020/03/03   Hitachi         K.Kishimoto     Update          QC#56123
 * 2020/04/03   Hitachi         K.Kitachi       Update          QC#55661
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2022/08/12   Hitachi         H.Watanabe      Update          QC#60046
 * 2023/04/12   CITS            R.Avelino       Update          QC#61357
 * 2024/01/25   Hitachi         T.Kawasue       Update          QC#63467
 * 2024/03/08   Hitachi         K.Watanabe      Update          QC#63539
 * 2024/04/08   Hitachi         S.Moriai        Update          QC#63540
 * 2024/04/11   Hitachi         T.Kawasue       Update          QC#63717
 *</pre>
 */
public class NSAL0150BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        super.preDoProcess(ezdCMsg, ezdSMsg);
        try {
            NSAL0150CMsg cMsg = (NSAL0150CMsg) ezdCMsg;
            NSAL0150SMsg sMsg = (NSAL0150SMsg) ezdSMsg;
            String screenAplId = cMsg.getScreenAplID();
            if ("NSAL0150_INIT".equals(screenAplId)) {
                doProcess_NSAL0150_INIT(cMsg, sMsg);
            } else if ("NSAL0150_NSAL0240".equals(screenAplId)) {
                doProcess_NSAL0150_NSAL0240(cMsg, sMsg);
            } else if ("NSAL0150_NSAL0290".equals(screenAplId)) {
                doProcess_NSAL0150_NSAL0290(cMsg, sMsg);
            } else if ("NSAL0150_NSAL0430".equals(screenAplId)) {
                doProcess_NSAL0150_NSAL0430(cMsg, sMsg);
            } else if ("NSAL0150Scrn00_CMN_Reset".equals(screenAplId)) {
                doProcess_NSAL0150Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NSAL0150Scrn00_CMN_Submit".equals(screenAplId)) {
                // START 2016/04/27 T.Kanasaka [QC#7583, MOD]
                if (cMsg.getMessageCode().endsWith("W")) {
                    cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                } else {
                    doProcess_NSAL0150Scrn00_CMN_Submit(cMsg, sMsg);
                }
                // END 2016/04/27 T.Kanasaka [QC#7583, MOD]
            } else if ("NSAL0150Scrn00_Deregister".equals(screenAplId)) {
                NSAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
                doProcess_NSAL0150Scrn00_Deregister(cMsg, sMsg);
            } else if ("NSAL0150Scrn00_InsertAsActual".equals(screenAplId)) {
                // START 2016/05/25 [QC#15410, MOD]
                if (cMsg.getMessageCode().endsWith("W")) {
                    cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                } else {
                    doProcess_NSAL0150Scrn00_InsertAsActual(cMsg, sMsg);
                }
                // END   2016/05/25 [QC#15410, MOD]
            // START 2024/04/08 S.Moriai [QC#63540, ADD]
            } else  if ("NSAL0150Scrn00_SearchOfDate".equals(screenAplId)){
                NSAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
                doProcess_NSAL0150Scrn00_SearchOfDate(cMsg, sMsg);
            // END 2024/04/08 S.Moriai [QC#63540, ADD]
            } else if ("NSAL0150Scrn00_OnChange_CounterName_Row".equals(screenAplId)) {
                NSAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
                doProcess_NSAL0150Scrn00_OnChange_CounterName_Row(cMsg, sMsg);
            } else if ("NSAL0150Scrn00_OnChange_MeterType_Row".equals(screenAplId)) {
                NSAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
                doProcess_NSAL0150Scrn00_OnChange_MeterType_Row(cMsg, sMsg);
            } else if ("NSAL0150Scrn00_OnChange_ReadingType_Row".equals(screenAplId)) {
                NSAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
                doProcess_NSAL0150Scrn00_OnChange_ReadingType_Row(cMsg, sMsg);
            } else if ("NSAL0150Scrn00_OpenWin_Estimate".equals(screenAplId)) {
                NSAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
                doProcess_NSAL0150Scrn00_OpenWin_Estimate(cMsg, sMsg);
            } else if ("NSAL0150Scrn00_OpenWin_OrderHistory".equals(screenAplId)) {
                NSAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
                doProcess_NSAL0150Scrn00_OpenWin_OrderHistory(cMsg, sMsg);
            } else if ("NSAL0150Scrn00_OpenWin_OrderSupplies".equals(screenAplId)) {
                NSAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
                doProcess_NSAL0150Scrn00_OpenWin_OrderSupplies(cMsg, sMsg);
            } else if ("NSAL0150Scrn00_Register".equals(screenAplId)) {
                NSAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
                doProcess_NSAL0150Scrn00_Register(cMsg, sMsg);
            // ADD START 2015/11/26 K.Kasai [Unit Test #71]
            } else if ("NSAL0150Scrn00_PagePrev".equals(screenAplId)) {
                doProcess_NSAL0150Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL0150Scrn00_PageNext".equals(screenAplId)) {
                doProcess_NSAL0150Scrn00_PageNext(cMsg, sMsg);
            // ADD END 2015/11/26 K.Kasai [Unit Test #71]
            // START 2018/05/10 K.Kojima [QC#24817,ADD]
            } else if ("NSAL0150Scrn00_ChangeDsMtrReadTpGrpCd".equals(screenAplId)) {
                doProcess_NSAL0150Scrn00_ChangeDsMtrReadTpGrpCd(cMsg, sMsg);
            // END 2018/05/10 K.Kojima [QC#24817,ADD]
                // START 2018/06/25 T.Ogura [QC#26336,ADD]
            } else if ("NSAL0150Scrn00_CMN_Download".equals(screenAplId)) {
                doProcess_NSAL0150Scrn00_CMN_Download(cMsg, sMsg);
            // END   2018/06/25 T.Ogura [QC#26336,ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(ezdCMsg, ezdSMsg);
        }
    }

    private void doProcess_NSAL0150_INIT(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        // START 2018/11/06 S.Kitamura [QC#28868,ADD]
        outputInitLog(cMsg);
        // END 2018/11/06 S.Kitamura [QC#28868,ADD]
        search(cMsg, sMsg);
    }

    // START 2018/06/13 K.Kojima [QC#26452,ADD]
    private void search(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        search(cMsg, sMsg, null);
    }
    // END 2018/06/13 K.Kojima [QC#26452,ADD]

    @SuppressWarnings("unchecked")
    // START 2018/06/13 K.Kojima [QC#26452,MOD]
    // private void search(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
    private void search(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg, String dsMtrReadTpGrpCd_BS) {
    // END 2018/06/13 K.Kojima [QC#26452,MOD]

        EZDI18NLabelConv lbCnv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        NSAL0150CommonLogic.resetParameter(cMsg, sMsg);

        NSAL0150CommonLogic.checkParameter(cMsg);
        if (NSAL0150CommonLogic.hasError(cMsg)) {
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();

        NSAL0150Query query = NSAL0150Query.getInstance();

        S21SsmEZDResult rslt = null;

        // START 2018/05/10 K.Kojima [QC#24817,ADD]
        List<BigDecimal> countMeterEntryContr = query.getMeterEntryContrDtlPk(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
        // START 2018/12/13 S.Kitamura [QC#28860,ADD]
        Map<String, Object> primaryCountMeterEntryContr = query.getPrimaryMeterEntryContr(glblCmpyCd, countMeterEntryContr, NSAL0150CommonLogic.getBizIdSlsDt());
        // END 2018/12/13 S.Kitamura [QC#28860,ADD]
        // END 2018/05/10 K.Kojima [QC#24817,ADD]

        // MOD START 2015/11/26 K.Kasai [Unit Test #71]
//        rslt = query.getDsMtrReadTpGrp(glblCmpyCd);
//        if (rslt != null && rslt.isCodeNormal()) {
//            int rsltCnt = rslt.getQueryResultCount();
//            ZYPEZDItemValueSetter.setValue(cMsg.D.no(0).xxChkBox_D, ZYPConstant.FLG_ON_Y);
//            ZYPEZDItemValueSetter.setValue(cMsg.D.no(0).dsMtrReadTpGrpCd_D, NSAL0150Constant.PSEUDO_DS_MTR_READ_TP_GRP_CD_ALL);
//            ZYPEZDItemValueSetter.setValue(cMsg.D.no(0).xxGenlFldAreaTxt_D, lbCnv.convLabel2i18nLabel("", NSAL0150Constant.PSEUDO_DS_MTR_READ_TP_GRP_DESC_TXT_ALL));
//            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
//            for (int i = 0; i < rsltCnt; i++) {
//                Map<String, Object> rsltMap = rsltList.get(i);
//                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i + 1).xxChkBox_D, ZYPConstant.FLG_OFF_N);
//                String dsMtrReadTpGrpCd = (String) rsltMap.get("DS_MTR_READ_TP_GRP_CD");
//                String dsMtrReadTpGrpDescTxt = (String) rsltMap.get("DS_MTR_READ_TP_GRP_DESC_TXT");
//                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i + 1).dsMtrReadTpGrpCd_D, dsMtrReadTpGrpCd);
//                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i + 1).xxGenlFldAreaTxt_D, String.format("%s - %s", dsMtrReadTpGrpCd, dsMtrReadTpGrpDescTxt));
//            }
//            cMsg.D.setValidCount(rsltCnt + 1);
//        }
        List<String> functionIds = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0150Constant.BIZ_ID);
        List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
        for (int i = 0; i < functionIds.size(); i++) {
            String functionId = functionIds.get(i);
            if (functionId.equals(NSAL0150Constant.FUNC_ID_BILL)) {
                dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);
            } else if (functionId.equals(NSAL0150Constant.FUNC_ID_SVC)) {
                dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_READS);
                // START 2019/04/05 K.Kitachi [QC#30921, ADD]
                dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
                // END 2019/04/05 K.Kitachi [QC#30921, ADD]
            } else if (functionId.equals(NSAL0150Constant.FUNC_ID_SUPPLY)) {
                dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SUPPLY_READS);
            } else if (functionId.equals(NSAL0150Constant.FUNC_ID_ODR)) {
                dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SUPPLY_READS);
            }
        }
        if (dsMtrReadTpGrpCdList != null) {
            rslt = query.getDsMtrReadTpGrp(glblCmpyCd, dsMtrReadTpGrpCdList);
        }
        if (rslt != null && rslt.isCodeNormal()) {
            int rsltCnt = rslt.getQueryResultCount();
            // START 2019/04/09 K.Kitachi [QC#31139, DEL]
//            ZYPEZDItemValueSetter.setValue(cMsg.D.no(0).xxChkBox_D, ZYPConstant.FLG_ON_Y);
//            ZYPEZDItemValueSetter.setValue(cMsg.D.no(0).dsMtrReadTpGrpCd_D, NSAL0150Constant.PSEUDO_DS_MTR_READ_TP_GRP_CD_ALL);
//            ZYPEZDItemValueSetter.setValue(cMsg.D.no(0).xxGenlFldAreaTxt_D, lbCnv.convLabel2i18nLabel("", NSAL0150Constant.PSEUDO_DS_MTR_READ_TP_GRP_DESC_TXT_ALL));
            // END 2019/04/09 K.Kitachi [QC#31139, DEL]
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            // START 2018/05/10 K.Kojima [QC#24817,ADD]
            int pulldownCount = 0;
            // END 2018/05/10 K.Kojima [QC#24817,ADD]
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                // START 2019/04/09 K.Kitachi [QC#31139, DEL]
//                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i + 1).xxChkBox_D, ZYPConstant.FLG_OFF_N);
                // END 2019/04/09 K.Kitachi [QC#31139, DEL]
                String dsMtrReadTpGrpCd = (String) rsltMap.get("DS_MTR_READ_TP_GRP_CD");
                String dsMtrReadTpGrpDescTxt = (String) rsltMap.get("DS_MTR_READ_TP_GRP_DESC_TXT");
                // START 2019/04/09 K.Kitachi [QC#31139, DEL]
//                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i + 1).dsMtrReadTpGrpCd_D, dsMtrReadTpGrpCd);
//                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i + 1).xxGenlFldAreaTxt_D, String.format("%s - %s", dsMtrReadTpGrpCd, dsMtrReadTpGrpDescTxt));
                // END 2019/04/09 K.Kitachi [QC#31139, DEL]
                // START 2018/05/10 K.Kojima [QC#24817,MOD]
                // ZYPEZDItemValueSetter.setValue(cMsg.dsMtrReadTpGrpCd_BD.no(i), dsMtrReadTpGrpCd);
                // ZYPEZDItemValueSetter.setValue(cMsg.xxGenlFldAreaTxt_BC.no(i), String.format("%s - %s", dsMtrReadTpGrpCd, dsMtrReadTpGrpDescTxt));
                if (!dsMtrReadTpGrpCd.equals(DS_MTR_READ_TP_GRP.BILLABLE_READS)
                        || (dsMtrReadTpGrpCd.equals(DS_MTR_READ_TP_GRP.BILLABLE_READS) && countMeterEntryContr.size() > 0)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.dsMtrReadTpGrpCd_BD.no(pulldownCount), dsMtrReadTpGrpCd);
                    ZYPEZDItemValueSetter.setValue(cMsg.xxGenlFldAreaTxt_BC.no(pulldownCount), String.format("%s - %s", dsMtrReadTpGrpCd, dsMtrReadTpGrpDescTxt));
                    pulldownCount++;
                }
                // END 2018/05/10 K.Kojima [QC#24817,MOD]
            }
            // START 2019/04/09 K.Kitachi [QC#31139, DEL]
//            cMsg.D.setValidCount(rsltCnt + 1);
            // END 2019/04/09 K.Kitachi [QC#31139, DEL]
        }
        // START 2018/05/10 K.Kojima [QC#24817,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.dsMtrReadTpGrpCd_BS, cMsg.dsMtrReadTpGrpCd_BD.no(0));
        // END 2018/05/10 K.Kojima [QC#24817,ADD]
        // MOD END 2015/11/26 K.Kasai [Unit Test #71]
        // START 2019/04/09 K.Kitachi [QC#31139, ADD]
        rslt = query.getDsMtrReadTpGrp(glblCmpyCd, null);
        if (rslt != null && rslt.isCodeNormal()) {
            int rsltCnt = rslt.getQueryResultCount();
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(0).xxChkBox_D, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(0).dsMtrReadTpGrpCd_D, NSAL0150Constant.PSEUDO_DS_MTR_READ_TP_GRP_CD_ALL);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(0).xxGenlFldAreaTxt_D, lbCnv.convLabel2i18nLabel("", NSAL0150Constant.PSEUDO_DS_MTR_READ_TP_GRP_DESC_TXT_ALL));
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i + 1).xxChkBox_D, ZYPConstant.FLG_OFF_N);
                String dsMtrReadTpGrpCd = (String) rsltMap.get("DS_MTR_READ_TP_GRP_CD");
                String dsMtrReadTpGrpDescTxt = (String) rsltMap.get("DS_MTR_READ_TP_GRP_DESC_TXT");
                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i + 1).dsMtrReadTpGrpCd_D, dsMtrReadTpGrpCd);
                ZYPEZDItemValueSetter.setValue(cMsg.D.no(i + 1).xxGenlFldAreaTxt_D, String.format("%s - %s", dsMtrReadTpGrpCd, dsMtrReadTpGrpDescTxt));
            }
            cMsg.D.setValidCount(rsltCnt + 1);
        }
        // END 2019/04/09 K.Kitachi [QC#31139, ADD]
        //Add Start 2016/10/03 <QC#12274>
        //TODO CodeUtil
        // ZYPCodeDataUtil.createPulldownList(TEST_COPY_IN_OUT.class, cMsg.testCopyInOutCd_BC, cMsg.testCopyInOutDescTxt_BD);
        ZYPEZDItemValueSetter.setValue(cMsg.dsTestCopyClsCd_BC.no(0), "I");
        ZYPEZDItemValueSetter.setValue(cMsg.dsTestCopyClsDescTxt_BD.no(0), "In");
        ZYPEZDItemValueSetter.setValue(cMsg.dsTestCopyClsCd_BC.no(1), "O");
        ZYPEZDItemValueSetter.setValue(cMsg.dsTestCopyClsDescTxt_BD.no(1), "Out");
        //Add Start 2016/10/03 <QC#12274>

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = query.getSvcMachMstr(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), cMsg.serNum.getValue(), cMsg.mdseCd.getValue());
        if (svcMachMstrTMsg == null) {
            cMsg.setMessageInfo(NSAL0150Constant.NSZM0006E);
            EZDDebugOutput.println(3, String.format("svcMachMstrPk=%s, serNum=%s, mdseCd=%s", cMsg.svcMachMstrPk.getValue(), cMsg.serNum.getValue(), cMsg.mdseCd.getValue()), NSAL0150BL02.class);
            return;
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk);
            // ADD START 2015/11/26 K.Kasai [Unit Test #71]
            ZYPEZDItemValueSetter.setValue(cMsg.serNum, svcMachMstrTMsg.serNum);
            ZYPEZDItemValueSetter.setValue(cMsg.istlDt, svcMachMstrTMsg.istlDt);
            ZYPEZDItemValueSetter.setValue(cMsg.shrDlrFlg, svcMachMstrTMsg.shrDlrFlg);
            // ADD END 2015/11/26 K.Kasai [Unit Test #71]
            // START 2018/10/15 K.Kitachi [QC#28652, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.svcByLineBizTpCd, svcMachMstrTMsg.svcByLineBizTpCd);
            ZYPEZDItemValueSetter.setValue(cMsg.sldByLineBizTpCd, svcMachMstrTMsg.sldByLineBizTpCd);
            // END 2018/10/15 K.Kitachi [QC#28652, ADD]
        }

        rslt = query.getMachDtl(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
        if (rslt != null && rslt.isCodeNormal()) {
            Map<String, Object> rsltMap = (Map<String, Object>) rslt.getResultObject();
            ZYPEZDItemValueSetter.setValue(cMsg.billToLocNm, (String) rsltMap.get("BILL_TO_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm, (String) rsltMap.get("SHIP_TO_LOC_NM"));
            // MOD START 2015/11/26 K.Kasai [Unit Test #71]
            ZYPEZDItemValueSetter.setValue(cMsg.xxGenlFldAreaTxt_CT, (String) rsltMap.get("CTAC_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.xxGenlFldAreaTxt_FC, (String) rsltMap.get("FAX_CTAC_NM"));
// Add Start 2016/11/24 <QC#14992>
//            ZYPEZDItemValueSetter.setValue(cMsg.xxGenlFldAreaTxt_LS, (String) rsltMap.get("MAX_RCV_DT"));
            ZYPEZDItemValueSetter.setValue(cMsg.xxGenlFldAreaTxt_LS, (String) rsltMap.get("LAST_SVC_CALL_CTAC"));
// End Start 2016/11/24 <QC#14992>
            ZYPEZDItemValueSetter.setValue(cMsg.xxGenlFldAreaTxt_TL, (String) rsltMap.get("TEL"));
            ZYPEZDItemValueSetter.setValue(cMsg.xxGenlFldAreaTxt_FX, (String) rsltMap.get("FAX"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsCtacPntValTxt_EM, (String) rsltMap.get("EML"));
            // ZYPEZDItemValueSetter.setValue(cMsg.svcMachFlNm, (String) rsltMap.get("SVC_MACH_FL_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.addrline1L3000If, (String) rsltMap.get("MACH_LOC"));
            ZYPEZDItemValueSetter.setValue(cMsg.mdlNm, (String) rsltMap.get("MDL_NM"));
            // ZYPEZDItemValueSetter.setValue(cMsg.serNum, (String) rsltMap.get("SER_NUM"));
            // ZYPEZDItemValueSetter.setValue(cMsg.istlDt, (String) rsltMap.get("ISTL_DT"));
            ZYPEZDItemValueSetter.setValue(cMsg.iwrCondNm, (String) rsltMap.get("IWR_COND_NM"));
            // MOD END 2015/11/26 K.Kasai [Unit Test #71]
            // START 2016/06/10 [QC#9591, MOD]
            ZYPEZDItemValueSetter.setValue(cMsg.iwrCondDescTxt, (String) rsltMap.get("IWR_COND_DESC_TXT"));

            String iwrEnblFlg = (String) rsltMap.get("IWR_ENBL_FLG");
            if (ZYPCommonFunc.hasValue(iwrEnblFlg) && ZYPConstant.FLG_ON_Y.equals(iwrEnblFlg)) {
                ZYPEZDItemValueSetter.setValue(cMsg.iwrEnblFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.iwrEnblFlg, ZYPConstant.FLG_OFF_N);
            }
            // END 2016/06/10 [QC#9591, MOD]
            // START 2016/06/29 [QC#10532, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.schdAgmtNum, (String) rsltMap.get("SCHD_AGMT_NUM"));
            // END 2016/06/29 [QC#10532, ADD]
            // START 2019/04/09 K.Kitachi [QC#31089, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.svcMemoPk, (BigDecimal) rsltMap.get("SVC_MEMO_PK"));
            ZYPEZDItemValueSetter.setValue(cMsg.svcCmntTxt, (String) rsltMap.get("SVC_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(cMsg.svcCmntTxt_HD, cMsg.svcCmntTxt);
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_SM, (String) rsltMap.get("EZUPTIME_SM"));
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_SM, (String) rsltMap.get("EZUPTIMEZONE_SM"));
            // END 2019/04/09 K.Kitachi [QC#31089, ADD]
        }
        // ADD START 2015/11/26 K.Kasai [Unit Test #71]
        // START 2019/04/09 K.Kitachi [QC#31089, DEL]
//        SVC_MEMOTMsgArray getSvcMemo = NSAL0150Query.getSvcMemo(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
//        StringBuilder svcMemoComment = new StringBuilder();
//        for (int i = 0; i < getSvcMemo.getValidCount(); i++) {
//            SVC_MEMOTMsg outSvcMemo = (SVC_MEMOTMsg) getSvcMemo.no(i);
//            svcMemoComment = svcMemoComment.append(outSvcMemo.svcCmntTxt.getValue());
//        }
//        ZYPEZDItemValueSetter.setValue(cMsg.svcCmntTxt, svcMemoComment.toString());
        // END 2019/04/09 K.Kitachi [QC#31089, DEL]

        Map<String, Object> getIwrRgtnCond = NSAL0150Query.getInstance().getIwrRgtnCond(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
        if (getIwrRgtnCond != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxOpsDt_CM, (String) getIwrRgtnCond.get("IWR_LAST_COMM_DT"));
            ZYPEZDItemValueSetter.setValue(cMsg.iwrRgtnDt, (String) getIwrRgtnCond.get("IWR_RGTN_DT"));
            ZYPEZDItemValueSetter.setValue(cMsg.iwrDeinsDt, (String) getIwrRgtnCond.get("IWR_DEINS_DT"));
        }

        // mod start 2016/10/17 CSA Defect#15293
        //DS_CONTRTMsg contrInfo = NSXC001001GetContr.getContrByMachMstrPkInclWarranty(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), ZYPDateUtil.getSalesDate());
        //DS_CONTR_DTLTMsg contrDtlInfo = NSXC001001GetContr.getContrDtlByMachMstrPkInclWarranty(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), ZYPDateUtil.getSalesDate());
        // mod start 2016/12/06 CSA Defect #15200
//      DS_CONTRTMsg contrInfo = NSXC001001GetContr.getContrByMachMstrPk(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), ZYPDateUtil.getSalesDate());
//      DS_CONTR_DTLTMsg contrDtlInfo = NSXC001001GetContr.getContrDtlByMachMstrPk(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), ZYPDateUtil.getSalesDate());
        // START 2018/05/25 M.Naito [QC#15410, MOD]
//      Map<String, Object> dsContrDtlKey = NSAL0150Query.getInstance().getContrDtlKeysByMachMstrPk(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), ZYPDateUtil.getSalesDate());
        // Mod Start 2019/03/27 QC#30791
        List<Map<String, Object>> dsContrDtlKeyList = NSAL0150Query.getInstance().getContrDtlKeysByMachMstrPk(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), ZYPDateUtil.getSalesDate());
//        // START 2019/02/18 S.Kitamura [QC#30339,ADD]
//        // Sort by CAP in Term & Condition
//        List<Map<String, Object>> dsContrDtlKeyList;
//        if (dsContrDtlKeyListBeforeSortedByCap.size() > 1){
//            List<Map<String, Object>> capDsContrDtlKeyList = new ArrayList<Map<String,Object>>();
//            List<Map<String, Object>> nonCapDsContrDtlKeyList = new ArrayList<Map<String,Object>>();
//            for (Map<String, Object> dsContrDtlKey : dsContrDtlKeyListBeforeSortedByCap) {
//                BigDecimal dsContrPk = (BigDecimal) dsContrDtlKey.get("DS_CONTR_PK");
//                BigDecimal dsContrDtlPk = (BigDecimal) dsContrDtlKey.get("DS_CONTR_DTL_PK");
//                String dsContrCatgCd = (String) dsContrDtlKey.get("DS_CONTR_CATG_CD");
//                if (NSAL0150CommonLogic.hasCapSvcTermCond(glblCmpyCd, dsContrPk, dsContrDtlPk, dsContrCatgCd)){
//                    capDsContrDtlKeyList.add(dsContrDtlKey);
//                } else {
//                    nonCapDsContrDtlKeyList.add(dsContrDtlKey);
//                }
//            }
//            dsContrDtlKeyList = capDsContrDtlKeyList;
//            dsContrDtlKeyList.addAll(nonCapDsContrDtlKeyList);
//        } else {
//            dsContrDtlKeyList = dsContrDtlKeyListBeforeSortedByCap;
//        }
//        // END 2019/02/18 S.Kitamura [QC#30339,ADD]
//        // START 2019/02/20 S.Kitamura [QC#30431,ADD]
//        // Sort by ContrDtlSts
//        if (dsContrDtlKeyList.size() > 1){
//            List<Map<String, Object>> dsContrDtlKeyListBeforeSortedByContrDtlSts = dsContrDtlKeyList;
//            List<Map<String, Object>> ettlFlgYDsContrDtlKeyList = new ArrayList<Map<String,Object>>();
//            List<Map<String, Object>> ettlFlgNDsContrDtlKeyList = new ArrayList<Map<String,Object>>();
//            for (Map<String, Object> dsContrDtlKey : dsContrDtlKeyListBeforeSortedByContrDtlSts) {
//                if (ZYPConstant.FLG_ON_Y.equals((String) dsContrDtlKey.get("ETTL_AVAL_FLG"))){
//                    ettlFlgYDsContrDtlKeyList.add(dsContrDtlKey);
//                } else {
//                    ettlFlgNDsContrDtlKeyList.add(dsContrDtlKey);
//                }
//            }
//            if (ettlFlgYDsContrDtlKeyList.size() > 0) {
//                dsContrDtlKeyList = ettlFlgYDsContrDtlKeyList;
//            } else {
//                dsContrDtlKeyList = ettlFlgNDsContrDtlKeyList;
//            }
//        }
//        // END 2019/02/20 S.Kitamura [QC#30431,ADD]
        // Mod End 2019/03/27 QC#30791
        DS_CONTRTMsg contrInfo = null;
        DS_CONTR_DTLTMsg contrDtlInfo = null;
        boolean setContrInfoFlg = false;
        DS_CONTRTMsg dplyContrInfo = new DS_CONTRTMsg();
        DS_CONTR_DTLTMsg dplyContrDtlInfo = new DS_CONTR_DTLTMsg();
        // Add Start 2019/03/27 QC#30791
        List<Map<String, Object>> splyOrdDsContrDtlKeyList = new ArrayList<Map<String,Object>>();
        // Add End 2019/03/27 QC#30791
        for (Map<String, Object> dsContrDtlKey : dsContrDtlKeyList) {

            if (dsContrDtlKey != null) {
                BigDecimal dsContrPk = (BigDecimal)dsContrDtlKey.get("DS_CONTR_PK");
                BigDecimal dsContrDtlPk = (BigDecimal)dsContrDtlKey.get("DS_CONTR_DTL_PK");

                contrInfo = new DS_CONTRTMsg();
                ZYPEZDItemValueSetter.setValue(contrInfo.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrInfo.dsContrPk, dsContrPk);
                contrInfo = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(contrInfo);

                contrDtlInfo = new DS_CONTR_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(contrDtlInfo.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrDtlInfo.dsContrPk, dsContrPk);
                ZYPEZDItemValueSetter.setValue(contrDtlInfo.dsContrDtlPk, dsContrDtlPk);
                contrDtlInfo = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(contrDtlInfo);
            }
            // mod end 2016/12/06 CSA Defect #15200

            // mod end 2016/10/17 CSA Defect#15293
            if (contrDtlInfo != null) {
                // mod start 2016/09/20 CSA Defect#12898
                 // mod start 2016/10/17 CSA Defect#15293
                 // mod start 2016/12/07 CSA Defect#15200
                 DS_CONTR_DTLTMsg contrDtlForSplyOrd = NSXC001001GetContr.getContrDtlInclWarrantyForSplyOrd(glblCmpyCd, contrDtlInfo.dsContrDtlPk.getValue());
                 // DS_CONTR_DTLTMsg contrDtlForSplyOrd = NSXC001001GetContr.getContrDtlByMachMstrPkForSplyOrd(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), ZYPDateUtil.getSalesDate());
                 // mod end 2016/12/07 CSA Defect#15200
                 // mod end 2016/10/17 CSA Defect#15293
                 if (ZYPCommonFunc.hasValue(contrDtlForSplyOrd.svcPgmMdseCd)) {
                    // START 2020/04/03 K.Kitachi [QC#55661, DEL]
//                    NSXC001001GetCovTmpl covTmpl = new NSXC001001GetCovTmpl();
//                    CovTmplInfo tmplInfo = new CovTmplInfo();
//                    tmplInfo.setGlblCmpyCd(glblCmpyCd);
//                    tmplInfo.setSlsDt(ZYPDateUtil.getSalesDate());
//                    tmplInfo.setSvcPgmMdseCd(contrDtlForSplyOrd.svcPgmMdseCd.getValue());
//                    boolean isSuplIncl = covTmpl.isSuplIncl(tmplInfo);
//                    // Add Start 2019/03/27 QC#30791
//                    boolean isLaserPlusContr = covTmpl.isLaserPlusContr(tmplInfo);
//                    String ettlFlg = (String) dsContrDtlKey.get("ETTL_AVAL_FLG");
//                    if ((isSuplIncl || isLaserPlusContr) && ZYPConstant.FLG_ON_Y.equals(ettlFlg)) {
//                        splyOrdDsContrDtlKeyList.add(dsContrDtlKey);
//                    }
//                    // Add End 2019/03/27 QC#30791
                    // END 2020/04/03 K.Kitachi [QC#55661, DEL]
                    // START 2018/05/25 M.Naito [QC#15410, ADD]
                    // START 2020/04/03 K.Kitachi [QC#55661, MOD]
//                    if (ZYPConstant.FLG_ON_Y.equals(cMsg.splyContrChkFlg.getValue()) || (!isSuplIncl && setContrInfoFlg)) {
                    if (setContrInfoFlg) {
                    // END 2020/04/03 K.Kitachi [QC#55661, MOD]
                        continue;
                    }
                    dplyContrInfo = contrInfo;
                    dplyContrDtlInfo = contrDtlInfo;
                    // END 2018/05/25 M.Naito [QC#15410, ADD]
                    // START 2020/04/03 K.Kitachi [QC#55661, DEL]
//                    if (isSuplIncl) {
//                        ZYPEZDItemValueSetter.setValue(cMsg.splyContrChkFlg, ZYPConstant.FLG_ON_Y);
//                    } else {
//                        ZYPEZDItemValueSetter.setValue(cMsg.splyContrChkFlg, ZYPConstant.FLG_OFF_N);
//                    }   
                    // END 2020/04/03 K.Kitachi [QC#55661, DEL]
                    // Del Start 2019/03/27 QC#30791
//                    boolean isLaserPlusContr = covTmpl.isLaserPlusContr(tmplInfo);
//                    // START 2019/02/20 S.Kitamura [QC#30431,MOD]
//                    String ettlFlg = (String) dsContrDtlKey.get("ETTL_AVAL_FLG");
//                    // if (isSuplIncl || isLaserPlusContr) {
//                    if ((isSuplIncl || isLaserPlusContr) && ZYPConstant.FLG_ON_Y.equals(ettlFlg)) {
//                    // END 2019/02/20 S.Kitamura [QC#30431,MOD]
//                        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);
//                    } else {
//                        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg, ZYPConstant.FLG_OFF_N);
//                    }
//
//                    // START 2017/09/26 K.Kim [QC#18744, ADD]
//                    String splyInclFlg = NSAL0150Query.getInstance().getSplyInclFlg(glblCmpyCd, contrDtlInfo.dsContrDtlPk.getValue());
//                    if (ZYPConstant.FLG_ON_Y.equals(splyInclFlg)) {
//                        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg, ZYPConstant.FLG_OFF_N);
//                    }
//                    // END 2017/09/26 K.Kim [QC#18744, ADD]
                    // Del End 2019/03/27 QC#30791
                 }
                 // mod end 2016/09/20 CSA Defect#12898
                 contrDtlInfo.svcCovTpCd.getValue();

                 // Mod Start 2016/12/06 <QC#14992-1>
                 // START 2018/12/13 S.Kitamura [QC#28860,MOD]
//               ZYPEZDItemValueSetter.setValue(cMsg.mtrReadMethCd, contrDtlInfo.mtrReadMethCd);
//               if (ZYPCommonFunc.hasValue(contrDtlInfo.mtrReadMethCd)) {
//                   MTR_READ_METHTMsg mtrReadMeth = NSAL0150Query.getMtrReadMeth(glblCmpyCd, contrDtlInfo.mtrReadMethCd.getValue());
//                   if (mtrReadMeth != null) {
//                       ZYPEZDItemValueSetter.setValue(cMsg.mtrReadMethDescTxt, mtrReadMeth.mtrReadMethDescTxt);
//                   }
//               }
                 MTR_READ_METHTMsg mtrReadMeth = null;
                 if (primaryCountMeterEntryContr == null) {
                     if (ZYPCommonFunc.hasValue(contrDtlInfo.mtrReadMethCd)) {
                         mtrReadMeth = NSAL0150Query.getMtrReadMeth(glblCmpyCd, contrDtlInfo.mtrReadMethCd.getValue());
                     }
                 } else {
                     if (ZYPCommonFunc.hasValue((String)primaryCountMeterEntryContr.get("MTR_READ_METH_CD"))) {
                         mtrReadMeth = NSAL0150Query.getMtrReadMeth(glblCmpyCd, (String)primaryCountMeterEntryContr.get("MTR_READ_METH_CD"));
                     }
                 }
                 if (mtrReadMeth != null) {
                     ZYPEZDItemValueSetter.setValue(cMsg.mtrReadMethDescTxt, mtrReadMeth.mtrReadMethDescTxt);
                 }
                 // END 2018/12/13 S.Kitamura [QC#28860,MOD]
                // Mod End   2016/12/06 <QC#14992-1>
                // START 2016/03/28 M.Gotou [QC#4397, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrPk, contrInfo.dsContrPk);
                // END 2016/03/28 M.Gotou [QC#4397, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrNum, contrInfo.dsContrNum);
                // START 2024/01/25 T.Kawasue [QC#63467, ADD]
                String dsContrCtrlStsNm = query.getDsContrCtrlStsNm(glblCmpyCd, contrDtlInfo.dsContrDtlPk.getValue(), cMsg.svcMachMstrPk.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm, dsContrCtrlStsNm);
                // END 2024/01/25 T.Kawasue [QC#63467, ADD]
                // START 2017/10/05 M.Naito [QC#20440, MOD]
//                StringBuilder contrBranch = new StringBuilder();
//                contrBranch.append(contrInfo.svcContrBrCd.getValue());
//                contrBranch.append(NSAL0150Constant.SLASH);
//                BRTMsgArray brTMsgArray = NSAL0150Query.getBr(glblCmpyCd, contrInfo.svcContrBrCd.getValue());
//                if (brTMsgArray.getValidCount() > 0) {
//                    contrBranch.append(brTMsgArray.no(0).locNm.getValue());
//                }
//                ZYPEZDItemValueSetter.setValue(cMsg.xxCoaBrSrchTxt, contrBranch.toString().trim());
                SVC_CONTR_BRTMsg svcContrBrTMsg = NSAL0150Query.getSvcContrBr(glblCmpyCd, contrInfo.svcContrBrCd.getValue());
                if (svcContrBrTMsg != null) {
                    // mod start 2018/07/20 QC#26974
                    //ZYPEZDItemValueSetter.setValue(cMsg.xxCoaBrSrchTxt, svcContrBrTMsg.svcContrBrDescTxt);
                    StringBuilder contrBranch = new StringBuilder();
                    contrBranch.append(contrInfo.svcLineBizCd.getValue().trim());
                    contrBranch.append(NSAL0150Constant.HYPHEN);
                    contrBranch.append(svcContrBrTMsg.svcContrBrDescTxt.getValue().trim());
                    ZYPEZDItemValueSetter.setValue(cMsg.xxCoaBrSrchTxt, contrBranch.toString());
                    // mod end 2018/07/20 QC#26974
                }
                // END 2017/10/05 M.Naito [QC#20440, MOD]
                Map<String, Object> getSubContr = NSAL0150Query.getInstance().getSubContr(glblCmpyCd, contrDtlInfo.dsContrDtlPk.getValue(), ZYPDateUtil.getSalesDate());
                if (getSubContr != null) {
                    ZYPEZDItemValueSetter.setValue(cMsg.dsSubContrPk, (BigDecimal) getSubContr.get("DS_SUB_CONTR_PK"));
                    // START 2016/09/07 T.Kanasaka [QC#2870, MOD]
//                ZYPEZDItemValueSetter.setValue(cMsg.vndNm, (String) getSubContr.get("LOC_NM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, (String) getSubContr.get("PRNT_VND_NM"));
                    // END 2016/09/07 T.Kanasaka [QC#2870, MOD]
                // START 2018/06/11 U.Kim [QC#22480, ADD]
                String splyInclFlg = (String) getSubContr.get("SPLY_INCL_FLG");
                ZYPEZDItemValueSetter.setValue(cMsg.splyInclFlg, splyInclFlg);
                if (ZYPConstant.FLG_ON_Y.equals(splyInclFlg)) {
                    String telNum = NSAL0150Query.getInstance().getTelNum(glblCmpyCd, (String) getSubContr.get("LOC_NUM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.contrMsgTxt, getSplyContrMsg((String) getSubContr.get("PRNT_VND_NM"), telNum));
                }
                // END   2018/06/11 U.Kim [QC#22480, ADD]
                }
                DS_CONTR_CATGTMsg outDsContrCatgTMsg = NSAL0150Query.getDsContrCatg(glblCmpyCd, contrInfo.dsContrCatgCd.getValue());
                if (outDsContrCatgTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(cMsg.dsContrCatgDescTxt, outDsContrCatgTMsg.dsContrCatgDescTxt.getValue());
                }
                SVC_COV_TPTMsg outSvcCovTpTMsg = NSAL0150Query.getSvcCovTp(glblCmpyCd, contrDtlInfo.svcCovTpCd.getValue());
                if (outSvcCovTpTMsg != null) {
                    if (ZYPConstant.FLG_ON_Y.equals(outSvcCovTpTMsg.mpsContrFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(cMsg.xxYesNoNm_MC, "Yes");
                    } else {
                        ZYPEZDItemValueSetter.setValue(cMsg.xxYesNoNm_MC, "No");
                    }
                }
                // START 2018/12/13 S.Kitamura [QC#28860,MOD]
//                Map<String, Object> getDsContrBllgSchdList = NSAL0150Query.getInstance().getDsContrBllgSchdInv(glblCmpyCd, contrDtlInfo.dsContrDtlPk.getValue());
                Map<String, Object> getDsContrBllgSchdList = null;
                if (primaryCountMeterEntryContr == null) {                    
                    getDsContrBllgSchdList = NSAL0150Query.getInstance().getDsContrBllgSchdInv(glblCmpyCd, contrDtlInfo.dsContrDtlPk.getValue());
                } else {
                    getDsContrBllgSchdList = NSAL0150Query.getInstance().getDsContrBllgSchdInv(glblCmpyCd, (BigDecimal)primaryCountMeterEntryContr.get("DS_CONTR_DTL_PK"));
                }
                // END 2018/12/13 S.Kitamura [QC#28860,MOD]
                if (getDsContrBllgSchdList != null) {
                    ZYPEZDItemValueSetter.setValue(cMsg.bllgSchdThruDt, (String) getDsContrBllgSchdList.get("MIN_BLLG_SCHD_THRU_DT"));
                }
                ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffFromDt, contrDtlInfo.contrEffFromDt);
                ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffThruDt, contrDtlInfo.contrEffThruDt);
                // START 2018/12/13 S.Kitamura [QC#28860,MOD]
                //Map<String, Object> getBllgCycleList = NSAL0150Query.getInstance().getBllgCycle(glblCmpyCd, contrDtlInfo.dsContrDtlPk.getValue());
                Map<String, Object> getBllgCycleList = null;
                if (primaryCountMeterEntryContr == null){
                    getBllgCycleList = NSAL0150Query.getInstance().getBllgCycle(glblCmpyCd, contrDtlInfo.dsContrDtlPk.getValue());
                } else {
                    getBllgCycleList = NSAL0150Query.getInstance().getBllgCycle(glblCmpyCd, (BigDecimal)primaryCountMeterEntryContr.get("DS_CONTR_DTL_PK"));
                }
                // END 2018/12/13 S.Kitamura [QC#28860,MOD]
                if (getBllgCycleList != null) {
                    ZYPEZDItemValueSetter.setValue(cMsg.bllgCycleDescTxt, (String) getBllgCycleList.get("BLLG_CYCLE_UOM_NM"));
                }
       			// START 2022/03/22 [QC#59683, MOD]
//                String consoliFlg;
//                if (ZYPConstant.FLG_ON_Y.equals(contrInfo.invSeptBaseUsgFlg.getValue())) {
//                    consoliFlg = ZYPConstant.FLG_OFF_N;
//                } else {
//                    consoliFlg = ZYPConstant.FLG_ON_Y;
//                }
//                ZYPEZDItemValueSetter.setValue(cMsg.conslInvFlg, consoliFlg);
                if (ZYPCommonFunc.hasValue(contrInfo.dsInvTgtrTpCd)) {
                    DS_INV_TGTR_TPTMsg dsInvTgtrTpTMsg = NSAL0150Query.getInstance().getDsInvTgtrTp(glblCmpyCd, contrInfo.dsInvTgtrTpCd.getValue());
                    if (dsInvTgtrTpTMsg != null) {
                        ZYPEZDItemValueSetter.setValue(cMsg.dsInvTgtrTpDescTxt, dsInvTgtrTpTMsg.dsInvTgtrTpDescTxt.getValue());
                    }
                }
		        // END   2022/03/22 [QC#59683, MOD]
                String svcTermCondAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(NSAL0150Constant.TERM_CONDITION_STAPLES_INCL_NAME, glblCmpyCd);
                // START 2018/03/08 K.Kojima [QC#24507,MOD]
                // if (svcTermCondAttrbNm != null) {
                //     if (NSAL0150Query.getInstance().existStaplesIncl(glblCmpyCd, contrDtlInfo.dsContrDtlPk.getValue(), svcTermCondAttrbNm)) {
                //         ZYPEZDItemValueSetter.setValue(cMsg.stplInclSvcFlg, ZYPConstant.FLG_ON_Y);
                //     } else {
                //         ZYPEZDItemValueSetter.setValue(cMsg.stplInclSvcFlg, ZYPConstant.FLG_OFF_N);
                //     }
                // }
                String stplIncl = query.getSvcTermCondDataDispTxt(glblCmpyCd, ZYPDateUtil.getSalesDate(), contrDtlInfo.dsContrDtlPk.getValue(), contrDtlInfo.dsContrPk.getValue(), svcTermCondAttrbNm, contrInfo.dsContrCatgCd.getValue());
                if (!ZYPCommonFunc.hasValue(stplIncl)) {
                    stplIncl = ZYPConstant.FLG_OFF_N;
                }
                ZYPEZDItemValueSetter.setValue(cMsg.svcTermCondDataDispTxt, stplIncl);
                // END 2018/03/08 K.Kojima [QC#24507,MOD]
                // START 2016/03/28 M.Gotou [QC#4399, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.xxSetFlg, ZYPConstant.FLG_OFF_N);
                // END 2016/03/28 M.Gotou [QC#4399, ADD]
                // START 2016/02/23 T.Tsuchida [QC#4117, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrDtlPk, contrDtlInfo.dsContrDtlPk);
                // END 2016/02/23 T.Tsuchida [QC#4117, ADD]
                // START 2018/05/25 M.Naito [QC#15410, ADD]
                setContrInfoFlg = true;
                // END 2018/05/25 M.Naito [QC#15410, ADD]
            }
            // ADD END 2015/11/26 K.Kasai [Unit Test #71]
        }
        // END 2018/05/25 M.Naito [QC#15410, MOD]

        // START 2020/04/03 K.Kitachi [QC#55661, ADD]
        for (Map<String, Object> dsContrDtlKey : dsContrDtlKeyList) {
            BigDecimal dsContrDtlPk = (BigDecimal) dsContrDtlKey.get("DS_CONTR_DTL_PK");
            DS_CONTR_DTLTMsg contrDtlForSplyOrd = NSXC001001GetContr.getContrDtlInclWarrantyForSplyOrd(glblCmpyCd, dsContrDtlPk);
            if (contrDtlForSplyOrd == null || !ZYPCommonFunc.hasValue(contrDtlForSplyOrd.svcPgmMdseCd)) {
                continue;
            }
            NSXC001001GetCovTmpl covTmpl = new NSXC001001GetCovTmpl();
            CovTmplInfo tmplInfo = new CovTmplInfo();
            tmplInfo.setGlblCmpyCd(glblCmpyCd);
            tmplInfo.setSlsDt(ZYPDateUtil.getSalesDate());
            tmplInfo.setSvcPgmMdseCd(contrDtlForSplyOrd.svcPgmMdseCd.getValue());
            boolean isSuplIncl = covTmpl.isSuplIncl(tmplInfo);
            boolean isLaserPlusContr = covTmpl.isLaserPlusContr(tmplInfo);
            String ettlFlg = (String) dsContrDtlKey.get("ETTL_AVAL_FLG");
            if ((isSuplIncl || isLaserPlusContr) && ZYPConstant.FLG_ON_Y.equals(ettlFlg)) {
                splyOrdDsContrDtlKeyList.add(dsContrDtlKey);
            }
            if (isSuplIncl) {
                ZYPEZDItemValueSetter.setValue(cMsg.splyContrChkFlg, ZYPConstant.FLG_ON_Y);
            }
        }
        // END 2020/04/03 K.Kitachi [QC#55661, ADD]

        // Add Start 2019/03/27 QC#30791
        setSplyOrdContract(cMsg, splyOrdDsContrDtlKeyList);
        // Add End 2019/03/27 QC#30791
        // START 2018/05/25 M.Naito [QC#15410, ADD]
        contrInfo = dplyContrInfo;
        contrDtlInfo = dplyContrDtlInfo;
        // END 2018/05/25 M.Naito [QC#15410, ADD]
        // START 2016/08/22 [QC#9628, ADD]
        if (!ZYPCommonFunc.hasValue(cMsg.splyContrChkFlg)) {
            ZYPEZDItemValueSetter.setValue(cMsg.splyContrChkFlg, ZYPConstant.FLG_OFF_N);
        }
        // START 2022/03/22 [QC#59683, DEL]
//        if (!ZYPCommonFunc.hasValue(cMsg.conslInvFlg)) {
//            ZYPEZDItemValueSetter.setValue(cMsg.conslInvFlg, ZYPConstant.FLG_OFF_N);
//        }
        // END   2022/03/22 [QC#59683, DEL]
        // START 2018/03/08 K.Kojima [QC#24507,MOD]
        // if (!ZYPCommonFunc.hasValue(cMsg.stplInclSvcFlg)) {
        //     ZYPEZDItemValueSetter.setValue(cMsg.stplInclSvcFlg, ZYPConstant.FLG_OFF_N);
        // }
        if (!ZYPCommonFunc.hasValue(cMsg.svcTermCondDataDispTxt)) {
            ZYPEZDItemValueSetter.setValue(cMsg.svcTermCondDataDispTxt, ZYPConstant.FLG_OFF_N);
        }
        // END 2018/03/08 K.Kojima [QC#24507,MOD]
        if (!ZYPCommonFunc.hasValue(cMsg.xxSetFlg)) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxSetFlg, ZYPConstant.FLG_OFF_N);
        }
        // END 2016/08/22 [QC#9628, ADD]

        // mod start 2016/12/06 CSA Defect #15200
        DS_CONTRTMsg contrInfo_OH = contrInfo;
        if (contrInfo_OH == null) {
            contrInfo_OH = getContrByMachMstrPkForOrderHistory(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), ZYPDateUtil.getSalesDate());
        }

        if (contrInfo_OH != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrNum_OH, contrInfo_OH.dsContrNum);
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCatgCd_OH, contrInfo_OH.dsContrCatgCd);

            if (ZYPCommonFunc.hasValue(contrInfo_OH.svcPgmMdseCd)) {
                CovTmplInfo tmplInfo = new CovTmplInfo();
                tmplInfo.setGlblCmpyCd(glblCmpyCd);
                tmplInfo.setSlsDt(ZYPDateUtil.getSalesDate());
                tmplInfo.setSvcPgmMdseCd(contrInfo_OH.svcPgmMdseCd.getValue());

                String dsOrdCatgDescTxt = getDsOrdCatgDescTxt(glblCmpyCd, contrInfo_OH.svcLineBizCd.getValue(), tmplInfo);
                if (ZYPCommonFunc.hasValue(dsOrdCatgDescTxt)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.dsOrdCatgDescTxt_OH, dsOrdCatgDescTxt);
                }
            }
        }
        // mod end 2016/12/06 CSA Defect #15200
        // mod start 2018/07/09 QC#26923
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxDplyCtrlFlg.getValue()) && !ZYPCommonFunc.hasValue(cMsg.schdAgmtNum)) {
            // START 2024/04/11 T.Kawasue [QC#63717, ADD]
            String salesDt = ZYPDateUtil.getSalesDate();
            String lineBizTp = getLineBizTp(cMsg);
            BigDecimal mtrEntryIntervalDays = null;

            if (LINE_BIZ_TP.LFS.equals(lineBizTp)) {
                mtrEntryIntervalDays = ZYPCodeDataUtil.getNumConstValue(NSAL0150Constant.NSAL0150_INTVL_DAYS_LFS, glblCmpyCd);
            } else if (LINE_BIZ_TP.PPS.equals(lineBizTp)) {
                mtrEntryIntervalDays = ZYPCodeDataUtil.getNumConstValue(NSAL0150Constant.NSAL0150_INTVL_DAYS_PPS, glblCmpyCd);
            } else if (LINE_BIZ_TP.ESS.equals(lineBizTp)) {
                mtrEntryIntervalDays = ZYPCodeDataUtil.getNumConstValue(NSAL0150Constant.NSAL0150_INTVL_DAYS_WTS, glblCmpyCd);
            }

            if (!ZYPCommonFunc.hasValue(mtrEntryIntervalDays)) {
                mtrEntryIntervalDays = NSAL0150Constant.DEF_SPLY_ODR_MTR_ENTRY_INTVL_DAYS;
            }

            String mtrEntryLimitDt = ZYPDateUtil.addDays(salesDt, mtrEntryIntervalDays.intValue() * -1);

            SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
            inMsg.setSQLID("011");
            inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            inMsg.setConditionValue("svcMachMstrPk01", cMsg.svcMachMstrPk.getValue());
            inMsg.setConditionValue("vldMtrFlg01", ZYPConstant.FLG_ON_Y);
            inMsg.setConditionValue("mtrReadDt01", mtrEntryLimitDt);
            int meteReadrCount = EZDTBLAccessor.count(inMsg);
            // END 2024/04/11 T.Kawasue [QC#63717, ADD]

            // START 2024/04/11 T.Kawasue [QC#63717, MOD]
//            if (existSplyReadExclCust(cMsg.svcMachMstrPk.getValue())) {
            if (existSplyReadExclCust(cMsg.svcMachMstrPk.getValue())
                    || (MTR_READ_METH.IMAGEWARE.equals(contrDtlInfo.mtrReadMethCd.getValue()) && meteReadrCount > 0)) {
            // END 2024/04/11 T.Kawasue [QC#63717, MOD]
                StringBuilder msgTxt = new StringBuilder(cMsg.contrMsgTxt.getValue());
                if (msgTxt.length() != 0) {
                    msgTxt.append(NSAL0150Constant.SPACE);
                    msgTxt.append(NSAL0150Constant.SEMICOLON);
                }
                String msg = EZDMessageEnv.getMessage(NSAL0150Constant.NSAM0731W);
                msgTxt.append(msg);
                if (msgTxt.length() > 0) {
                    String strMsgTxt = msgTxt.toString();
                    int cmntLen = cMsg.getAttr("contrMsgTxt").getDigit();
                    if (cmntLen < strMsgTxt.length()) {
                        strMsgTxt = strMsgTxt.substring(0, cmntLen);
                    }
                    ZYPEZDItemValueSetter.setValue(cMsg.contrMsgTxt, strMsgTxt);
                }
            }
        }
        // mod end 2018/07/09 QC#26923

        rslt = query.getLastUgwSvcPhysMtrRead(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
        // START 2016/06/10 [QC#9591, ADD]
        List<Map<String, Object>> readMtrCntList = query.getReadMtrCntList(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
        // END   2016/06/10 [QC#9591, ADD]
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).mtrLbDescTxt_A, (String) rsltMap.get("MTR_LB_DESC_TXT"));
                // START 2016/06/10 [QC#9591, MOD]
                //ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).readMtrCnt_A, (BigDecimal) rsltMap.get("READ_MTR_CNT"));
                String targetMtrCntrId = (String) rsltMap.get("MTR_CNTR_ID");
                for (int j = 0; j < readMtrCntList.size(); j++) {
                    Map<String, Object> readMtrCntValue = readMtrCntList.get(j);
                    String mtrCntrId = (String) readMtrCntValue.get("MTR_CNTR_ID");
                    if (ZYPCommonFunc.hasValue(mtrCntrId) && mtrCntrId.equals(targetMtrCntrId)) {
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).readMtrCnt_A, (BigDecimal) readMtrCntValue.get("READ_MTR_CNT"));
                        break;
                    }
                }
                // END   2016/06/10 [QC#9591, MOD]
            }
            cMsg.A.setValidCount(rsltCnt);
        }

        // START 2018/05/10 K.Kojima [QC#24817,DEL]
        // rslt = query.getSvcPhysMtrReadTmpl(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), NSAL0150Constant.DEF_DS_MTR_READ_TP_CD);
        // if (rslt != null && rslt.isCodeNormal()) {
        //     List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
        //     int rsltCnt = rslt.getQueryResultCount();
        //     for (int i = 0; i < rsltCnt; i++) {
        //         Map<String, Object> rsltMap = rsltList.get(i);
        //         ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).svcPhysMtrPk_B, (BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
        //         ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mdlMtrId_B, (String) rsltMap.get("MDL_MTR_ID"));
        //         ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrLbCd_B, (String) rsltMap.get("MTR_LB_CD"));
        //         ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrLbDescTxt_B, (String) rsltMap.get("MTR_LB_DESC_TXT"));
        //         ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).dsMtrReadTpCd_B, (String) rsltMap.get("DS_MTR_READ_TP_CD"));
        //         // mod start 2017/09/07 QC#15134
        //         //ZYPCodeDataUtil.createPulldownList(DS_MTR_READ_TP.class, cMsg.B.no(i).dsMtrReadTpCd_B1, cMsg.B.no(i).dsMtrReadTpDescTxt_B);
        //         createDsMtrReadTpPulldown(glblCmpyCd, cMsg.B.no(i));
        //         // mod end 2017/09/07 QC#15134
        //         // START 2016/03/31 T.Tomita [QC#4394, MOD]
        //         ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrReadDt_B, query.getDefaultMeterReadDate(glblCmpyCd, contrDtlInfo, NSAL0150CommonLogic.getBizIdSlsDt()));
        //         // END 2016/03/31 T.Tomita [QC#4394, MOD]
        //         ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).prevReadMtrCnt_B, (BigDecimal) rsltMap.get("PREV_MTR_CNT"));
        //         cMsg.B.no(i).readMtrCnt_B.clear();
        //         // MOD START 2015/11/26 K.Kasai [Unit Test #71]
        //         // cMsg.B.no(i).mtrEntryCmntTxt_B.clear();
        //         ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrEntryCmntTxt_B, cMsg.cpoOrdNum);
        //         // MOD END 2015/11/26 K.Kasai [Unit Test #71]
        //     }
        //     cMsg.B.setValidCount(rsltCnt);
        // }
        // END 2018/05/10 K.Kojima [QC#24817,DEL]

        // START 2018/06/13 K.Kojima [QC#26452,ADD]
        if (!ZYPCommonFunc.hasValue(dsMtrReadTpGrpCd_BS)) {
            dsMtrReadTpGrpCd_BS = cMsg.dsMtrReadTpGrpCd_BS.getValue();
        }
        // END 2018/06/13 K.Kojima [QC#26452,ADD]
        // START 2018/05/10 K.Kojima [QC#24817,ADD]
        // START 2018/06/13 K.Kojima [QC#26452,MOD]
        // if (cMsg.dsMtrReadTpGrpCd_BS.getValue().equals(DS_MTR_READ_TP_GRP.BILLABLE_READS)) {
        if (dsMtrReadTpGrpCd_BS.equals(DS_MTR_READ_TP_GRP.BILLABLE_READS)) {
        // END 2018/06/13 K.Kojima [QC#26452,MOD]
            createNewInputMeterList(cMsg, glblCmpyCd, contrDtlInfo, true, countMeterEntryContr);
        } else {
            createNewInputMeterList(cMsg, glblCmpyCd, contrDtlInfo, false, null);
        }
        // END 2018/05/10 K.Kojima [QC#24817,ADD]

        rslt = query.getSvcPhysMtr(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(0).xxChkBox_C, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(0).mtrLbCd_C, NSAL0150Constant.PSEUDO_MTR_LB_CD_ALL);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(0).mtrLbDescTxt_C, lbCnv.convLabel2i18nLabel("", NSAL0150Constant.PSEUDO_MTR_LB_DESC_TXT_ALL));
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.C.no(i + 1).xxChkBox_C, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cMsg.C.no(i + 1).mtrLbCd_C, (String) rsltMap.get("MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.C.no(i + 1).mtrLbDescTxt_C, (String) rsltMap.get("MTR_LB_DESC_TXT"));
            }
            cMsg.C.setValidCount(rsltCnt + 1);
        }

        // START 2018/09/26 K.Kojima [QC#28388,ADD]
        BigDecimal rollOverExchReadCnt = query.getRollOverExchReadCnt(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
        // END 2018/09/26 K.Kojima [QC#28388,ADD]

        // START 2020/03/03 [QC#56123, ADD]
        String maxInvThruDt = query.getMaxInvThruDt(glblCmpyCd, cMsg.svcMachMstrPk.getValue());;
        // END   2020/03/03 [QC#56123, ADD]
        rslt = query.getSvcPhysMtrRead(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), sMsg.E.length() + 1);
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();

            // START 2018/10/16 K.Kojima [QC#28392-1,ADD]
            // START 2020/03/03 [QC#56123, DEL]
//            List<Map<String, Object>> startReadInvoiceThruDtList = query.getStartReadInvoiceThruDt(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
//            Map<BigDecimal, String> startReadInvoiceThruDtMap = new HashMap<BigDecimal, String>();
//            for (int i = 0; i < startReadInvoiceThruDtList.size(); i++) {
//                Map<String, Object> startReadInvoiceThruDt = startReadInvoiceThruDtList.get(i);
//                BigDecimal svcPhysMtrReadPk = (BigDecimal) startReadInvoiceThruDt.get("SVC_PHYS_MTR_READ_PK");
//                String bllgSchdThruDt = (String) startReadInvoiceThruDt.get("BLLG_SCHD_THRU_DT");
//                startReadInvoiceThruDtMap.put(svcPhysMtrReadPk, bllgSchdThruDt);
//            }
            // END   2020/03/03 [QC#56123, DEL]

            Map<BigDecimal, Map<String, Object>> startReadDataMap = new HashMap<BigDecimal, Map<String, Object>>();
            Map<String, Map<String, Object>> beforeReadDataMap = new HashMap<String, Map<String, Object>>();
            for (int i = rsltCnt - 1; i >= 0; i--) {
                Map<String, Object> rsltMap = rsltList.get(i);

                BigDecimal dsContrDtlPk = (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK");
                BigDecimal svcPhysMtrPk = (BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK");
                BigDecimal svcPhysMtrReadPk = (BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_PK");
                BigDecimal readMtrCnt = (BigDecimal) rsltMap.get("READ_MTR_CNT");
                String dsMtrReadTpGrpCd = (String) rsltMap.get("DS_MTR_READ_TP_GRP_CD");
                String mtrReadDt = (String) rsltMap.get("MTR_READ_DT");

                // set Start Read Data
                if (!startReadDataMap.containsKey(svcPhysMtrPk) || ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                    Map<String, Object> startReadData = new HashMap<String, Object>();
                    startReadData.put("SVC_PHYS_MTR_READ_PK", svcPhysMtrReadPk);
                    startReadData.put("MTR_READ_DT", mtrReadDt);
                    startReadData.put("READ_MTR_CNT", readMtrCnt);
                    startReadDataMap.put(svcPhysMtrPk, startReadData);
                }
                Map<String, Object> startReadData = startReadDataMap.get(svcPhysMtrPk);
                rsltMap.put("START_SVC_PHYS_MTR_READ_PK", startReadData.get("SVC_PHYS_MTR_READ_PK"));
                rsltMap.put("START_MTR_READ_DT", startReadData.get("MTR_READ_DT"));
                rsltMap.put("START_READ_MTR_CNT", startReadData.get("READ_MTR_CNT"));

                // set Before Read Data
                String beforeDataKey = svcPhysMtrPk + " - " + dsMtrReadTpGrpCd;
                if (beforeReadDataMap.containsKey(beforeDataKey)) {
                    Map<String, Object> beforeReadData = beforeReadDataMap.get(beforeDataKey);
                    rsltMap.put("BEF_SVC_PHYS_MTR_READ_PK", beforeReadData.get("SVC_PHYS_MTR_READ_PK"));
                    rsltMap.put("BEF_MTR_READ_DT", beforeReadData.get("MTR_READ_DT"));
                    rsltMap.put("BEF_READ_MTR_CNT", beforeReadData.get("READ_MTR_CNT"));
                } else {
                    rsltMap.put("BEF_SVC_PHYS_MTR_READ_PK", svcPhysMtrReadPk);
                    rsltMap.put("BEF_MTR_READ_DT", mtrReadDt);
                    rsltMap.put("BEF_READ_MTR_CNT", readMtrCnt);
                }
                Map<String, Object> beforeReadData = new HashMap<String, Object>();
                beforeReadData.put("SVC_PHYS_MTR_READ_PK", svcPhysMtrReadPk);
                beforeReadData.put("MTR_READ_DT", mtrReadDt);
                beforeReadData.put("READ_MTR_CNT", readMtrCnt);
                beforeReadDataMap.put(beforeDataKey, beforeReadData);

                // set Invalid Flag
                // START 2020/03/03 [QC#56123, [MOD]
                BigDecimal startSvcPhysMtrReadPk = (BigDecimal) rsltMap.get("START_SVC_PHYS_MTR_READ_PK");
                String mtrReadTpGrpCd = (String) rsltMap.get("DS_MTR_READ_TP_GRP_CD");
                if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                    rsltMap.put("INVLD_MTR_FLG", ZYPConstant.FLG_ON_Y);
                } else if (DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(mtrReadTpGrpCd) || DS_MTR_READ_TP_GRP.SUPPLY_READS.equals(mtrReadTpGrpCd)) {
//                    String bllgSchdThruDt = startReadInvoiceThruDtMap.get(startSvcPhysMtrReadPk);
                    if (ZYPCommonFunc.hasValue(maxInvThruDt) && mtrReadDt.compareTo(maxInvThruDt) <= 0) {
                        rsltMap.put("INVLD_MTR_FLG", ZYPConstant.FLG_ON_Y);
                    } else {
                        rsltMap.put("INVLD_MTR_FLG", ZYPConstant.FLG_OFF_N);
                    }
                } else {
                    rsltMap.put("INVLD_MTR_FLG", ZYPConstant.FLG_OFF_N);
                }
                // END  2020/03/03 [QC#56123, [MOD]
            }
            // END 2018/10/16 K.Kojima [QC#28392-1,ADD]

            if (rsltCnt > sMsg.E.length()) {
                rsltCnt = sMsg.E.length();
                cMsg.setMessageInfo(NSAL0150Constant.NZZM0001W);
            }
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).svcPhysMtrReadPk_E, (BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).ezUpTime_E, (String) rsltMap.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).ezUpTimeZone_E, (String) rsltMap.get("EZUPTIMEZONE"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).svcPhysMtrPk_E, (BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).svcPhysMtrReadGrpSq_E, (BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_GRP_SQ"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).xxBtnFlg_E, (String) rsltMap.get("XX_BTN_FLG"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).vldMtrFlg_E, (String) rsltMap.get("VLD_MTR_FLG"));
                // START 2016/03/24 K.Yamada [QC#4402, ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).vldMtrFlg_OG, sMsg.E.no(i).vldMtrFlg_E);
                // END 2016/03/24 K.Yamada [QC#4402, ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mtrLbCd_E, (String) rsltMap.get("MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mtrLbDescTxt_E, (String) rsltMap.get("MTR_LB_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).dsMtrReadTpCd_E, (String) rsltMap.get("DS_MTR_READ_TP_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).dsMtrReadTpDescTxt_E, (String) rsltMap.get("DS_MTR_READ_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).dsMtrReadTpGrpCd_E, (String) rsltMap.get("DS_MTR_READ_TP_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mtrReadDt_E, (String) rsltMap.get("MTR_READ_DT"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).readMtrCnt_E, (BigDecimal) rsltMap.get("READ_MTR_CNT"));
                // START 2018/09/26 K.Kojima [QC#28388,ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mtrReadDt_EB, (String) rsltMap.get("BEF_MTR_READ_DT"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).readMtrCnt_EB, (BigDecimal) rsltMap.get("BEF_READ_MTR_CNT"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).svcPhysMtrReadPk_EB, (BigDecimal) rsltMap.get("BEF_SVC_PHYS_MTR_READ_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).startMtrReadDt_E, (String) rsltMap.get("START_MTR_READ_DT"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).startReadMtrCnt_E, (BigDecimal) rsltMap.get("START_READ_MTR_CNT"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).startSvcPhysMtrReadPk_E, (BigDecimal) rsltMap.get("START_SVC_PHYS_MTR_READ_PK"));
                // END 2018/09/26 K.Kojima [QC#28388,ADD]
                // mod start 2017/09/07 QC#15134
//                // Add Start 2016/11/24 <QC#14992>
//                BigDecimal netMtrCnt = (BigDecimal) rsltMap.get("NET_MTR_CNT");
//                BigDecimal avgMtrCnt = (BigDecimal) rsltMap.get("AVG_MTR_CNT");
//                if(ZYPCommonFunc.hasValue(netMtrCnt)) {
//                    netMtrCnt = netMtrCnt.setScale(0, RoundingMode.HALF_UP);
//                    ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mtrCnt_EN, netMtrCnt);
//                }
//                if(ZYPCommonFunc.hasValue(avgMtrCnt)) {
//                    avgMtrCnt = avgMtrCnt.setScale(0, RoundingMode.HALF_UP);
//                    ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mtrCnt_EA, avgMtrCnt);
//                }
//                // Add End   2016/11/24 <QC#14992>
                // START 2018/09/26 K.Kojima [QC#28388,MOD]
                // BigDecimal netMtrCnt = getNetMtrCnt(glblCmpyCd, rsltMap);
                // BigDecimal avgMtrCnt = getAvgMtrCnt(glblCmpyCd, rsltMap);
                // ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mtrCnt_EN, netMtrCnt);
                // ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mtrCnt_EA, avgMtrCnt);
                if (i < cMsg.E.length()) {
                    BigDecimal netMtrCnt = getNetMtrCnt(glblCmpyCd, sMsg.E.no(i), rollOverExchReadCnt);
                    BigDecimal avgMtrCnt = getAvgMtrCnt(glblCmpyCd, sMsg.E.no(i), rollOverExchReadCnt);
                    // START 2018/10/18 K.Kojima [QC#28848,MOD]
                    // ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mtrCnt_EN, netMtrCnt);
                    // ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mtrCnt_EA, avgMtrCnt);
                    ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).avgCopyVolCnt_EN, netMtrCnt);
                    ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).avgCopyVolCnt_EA, avgMtrCnt);
                    // END 2018/10/18 K.Kojima [QC#28848,MOD]
                }
                // END 2018/09/26 K.Kojima [QC#28388,MOD]
                // mod end 2017/09/07 QC#15134
                // mod start 2017/09/07 QC#15134
                //ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mtrEntryCmntTxt_E, (String) rsltMap.get("MTR_ENTRY_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mtrEntryCmntTxt_E, addRollOverExchComment((String) rsltMap.get("CNTR_RESET_TP_CD"), (String) rsltMap.get("MTR_ENTRY_CMNT_TXT")));
                // mod end 2017/09/07 QC#15134
                // Add Start 2016/10/03 <QC#12274>
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).testMtrCnt_E, (BigDecimal) rsltMap.get("TEST_MTR_CNT"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).svcTaskNum_E, (String) rsltMap.get("SVC_TASK_NUM"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).dsTestCopyClsDescTxt_E, (String) rsltMap.get("DS_TEST_COPY_CLS_DESC_TXT"));
                // Add End   2016/10/03 <QC#12274>
                // START 2017/12/13 M.Kidokoro [QC#21681, ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).vldMtrFlg_EI, (String) rsltMap.get("INVLD_MTR_FLG"));
                // END 2017/12/13 M.Kidokoro [QC#21681, ADD]
                // START 2018/04/12 K.Kojima [QC#23602,ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).xxRecHistCratTs_E, (String) rsltMap.get("XX_REC_HIST_CRAT_TS"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).xxRecHistCratByNm_E, (String) rsltMap.get("XX_REC_HIST_CRAT_BY_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).xxRecHistUpdTs_E, (String) rsltMap.get("XX_REC_HIST_UPD_TS"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).xxRecHistUpdByNm_E, (String) rsltMap.get("XX_REC_HIST_UPD_BY_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).xxRecHistTblNm_E, (String) rsltMap.get("XX_REC_HIST_TBL_NM"));
                // END 2018/04/12 K.Kojima [QC#23602,ADD]
            }
            sMsg.E.setValidCount(rsltCnt);
            // ADD START 2015/11/26 K.Kasai [Unit Test #71]
            int j = 0;
            for(; j < sMsg.E.getValidCount(); j++) {
                if(j == cMsg.E.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.E.no(j), null, cMsg.E.no(j), null);
            }
            cMsg.E.setValidCount(j);

            cMsg.xxPageShowFromNum.setValue(BigDecimal.ONE);
            cMsg.xxPageShowToNum.setValue(cMsg.E.getValidCount());
            cMsg.xxPageShowOfNum.setValue(rsltCnt);
            // ADD END 2015/11/26 K.Kasai [Unit Test #71]
        // ADD START 2015/11/26 K.Kasai [Unit Test #71]
        } else {
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        // ADD END 2015/11/26 K.Kasai [Unit Test #71]
        }

        // DEL START 2015/11/26 K.Kasai [Unit Test #71]
        //EZDMsg.copy(sMsg.E, null, cMsg.E, null);
        // DEL END 2015/11/26 K.Kasai [Unit Test #71]

        // Add Start 2018/07/06 QC#26972
        NSAL0150CommonLogic.setSvcCmntTxt(glblCmpyCd, cMsg);
        // Add End 2018/07/06 QC#26972
        // START 2020/03/03 [QC#56123, ADD]
        Map<String, Object>maxUpTm =  NSAL0150Query.getInstance().getMaxUpTm(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
        if (maxUpTm != null) {
            ZYPEZDItemValueSetter.setValue(sMsg.ezUpTime_MR, (String)maxUpTm.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(sMsg.ezUpTimeZone_MR, (String)maxUpTm.get("EZUPTIMEZONE"));
        } else {
            sMsg.ezUpTime_MR.clear();
            sMsg.ezUpTimeZone_MR.clear();
        }
        // END   2020/03/03 [QC#56123, ADD]
    }

    private void doProcess_NSAL0150_NSAL0240(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        // TODO Auto-generated method stub

    }

    private void doProcess_NSAL0150_NSAL0290(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        // TODO Auto-generated method stub

    }

    private void doProcess_NSAL0150_NSAL0430(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        for (int p = 0; p < cMsg.P.getValidCount(); p++) {
            BigDecimal pSvcPhysMtrPk = cMsg.P.no(p).svcPhysMtrPk.getValue();
            for (int b = 0; b < cMsg.B.getValidCount(); b++) {
                BigDecimal bSvcPhysMtrPk = cMsg.B.no(b).svcPhysMtrPk_B.getValue();
                if (NSAL0150CommonLogic.isEqualNum(pSvcPhysMtrPk, bSvcPhysMtrPk)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(b).readMtrCnt_B, cMsg.P.no(p).readMtrCnt);
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(b).mtrEntryCmntTxt_B, cMsg.P.no(p).mtrEntryCmntTxt);
                    // START 2017/08/31 K.Kim [QC#20863, ADD]
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(b).dsMtrReadTpCd_B, DS_MTR_READ_TP.ESTIMATED);
                    // END 2017/08/31 K.Kim [QC#20863, ADD]
                }
            }
        }
    }

    private void doProcess_NSAL0150Scrn00_CMN_Reset(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0150Scrn00_CMN_Submit(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        // START 2017/01/19 [QC#16644, ADD]
        String dsMtrReadTpGrpCd_BS = cMsg.dsMtrReadTpGrpCd_BS.getValue();
        // END   2017/01/19 [QC#16644, ADD]
        // START 2018/06/13 K.Kojima [QC#26452,MOD]
        // search(cMsg, sMsg);
        search(cMsg, sMsg, dsMtrReadTpGrpCd_BS);
        // END 2018/06/13 K.Kojima [QC#26452,MOD]
        // START 2017/01/19 [QC#16644, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.dsMtrReadTpGrpCd_BS, dsMtrReadTpGrpCd_BS);
        // END   2017/01/19 [QC#16644, ADD]
    }

    private void doProcess_NSAL0150Scrn00_Deregister(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        // START 2016/06/10 [QC#9591, ADD]
        NSZC050001PMsg nszc050001PMsg = new NSZC050001PMsg();
        setParamForNSZC050001(nszc050001PMsg, cMsg);

        S21ApiMessage msg = new S21ApiMessage();
        String msgId = null;

        NSZC050001 apiNSZC050001 = new NSZC050001();
        apiNSZC050001.execute(nszc050001PMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(nszc050001PMsg)) {
            for (int j = 0; j < S21ApiUtil.getXxMsgList(nszc050001PMsg).size(); j++) {
                msg = S21ApiUtil.getXxMsgList(nszc050001PMsg).get(j);
                msgId = msg.getXxMsgid();

                // START 2018/03/27 U.Kim [QC#23071, MOD]
                // if (msgId.endsWith("E")) {
                if (msgId.endsWith("E") || msgId.endsWith("W")) {
                // END 2018/03/27 U.Kim [QC#23071, MOD]
                    cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                    return;
                }
            }
        }

        cMsg.setMessageInfo(NSAL0150Constant.NZZM0002I);
        search(cMsg, sMsg);
        // END   2016/06/10 [QC#9591, ADD]
    }

    private void doProcess_NSAL0150Scrn00_InsertAsActual(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        // DEL START 2022/08/12 H.Watanabe [QC#60046]
//      search(cMsg, sMsg);
        // DEL END 2022/08/12 H.Watanabe [QC#60046]
        // ADD START 2022/08/12 H.Watanabe [QC#60046]
        if (!ZYPCommonFunc.hasValue(cMsg.xxRowNum)) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(cMsg.dsMtrReadTpGrpCd_BS, DS_MTR_READ_TP_GRP.BILLABLE_READS);
        doProcess_NSAL0150Scrn00_ChangeDsMtrReadTpGrpCd(cMsg, sMsg);
        int rowNum = cMsg.xxRowNum.getValueInt();
        BigDecimal grpSq = cMsg.E.no(rowNum).svcPhysMtrReadGrpSq_E.getValue();
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            for (int j = 0; j < cMsg.E.getValidCount(); j++) {
                if (!grpSq.equals(cMsg.E.no(j).svcPhysMtrReadGrpSq_E.getValue())) {
                    continue;
                }
                if (cMsg.B.no(i).svcPhysMtrPk_B.getValue().equals(cMsg.E.no(j).svcPhysMtrPk_E.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrReadDt_B, cMsg.E.no(j).mtrReadDt_E.getValue());
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).readMtrCnt_B, cMsg.E.no(j).readMtrCnt_E.getValue());
                    Object[] array = {cMsg.E.no(j).readMtrCnt_E.getValue(), ZYPDateUtil.formatEzd8ToDisp(cMsg.E.no(j).mtrReadDt_E.getValue()) };
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrEntryCmntTxt_B, MessageFormat.format(NSAL0150Constant.MTR_ENTRY_CMNT_TXT, array));
                }
            }
        }
    // ADD END 2022/08/12 H.Watanabe [QC#60046]
    }
    
    // START 2024/04/08 S.Moriai [QC#63540, ADD]
    private void doProcess_NSAL0150Scrn00_SearchOfDate(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        doProcess_NSAL0150Scrn00_Filter(cMsg, sMsg);
    }
    // END 2024/04/08 S.Moriai [QC#63540, ADD]

    private void doProcess_NSAL0150Scrn00_OnChange_CounterName_Row(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        doProcess_NSAL0150Scrn00_Filter(cMsg, sMsg);
    }

    @SuppressWarnings("unchecked")
    private void doProcess_NSAL0150Scrn00_OnChange_MeterType_Row(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {

        // del start 2017/09/07 QC#15134
//        String glblCmpyCd = getGlobalCompanyCode();
//        String dsMtrReadTpCd = cMsg.B.no(0).dsMtrReadTpCd_B.getValue();
//        String mtrReadDt = cMsg.B.no(0).mtrReadDt_B.getValue();
//
//        Map<String, BigDecimal> readMtrCntMap = new HashMap<String, BigDecimal>();
//        Map<String, String> mtrEntryCmntTxtMap = new HashMap<String, String>();
//        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
//            String svcPhysMtrPkStr = cMsg.B.no(i).svcPhysMtrPk_B.getValue().toPlainString();
//            readMtrCntMap.put(svcPhysMtrPkStr, cMsg.B.no(i).readMtrCnt_B.getValue());
//            mtrEntryCmntTxtMap.put(svcPhysMtrPkStr, cMsg.B.no(i).mtrEntryCmntTxt_B.getValue());
//        }
//
//        ZYPTableUtil.clear(cMsg.B);
//
//        NSAL0150Query query = NSAL0150Query.getInstance();
//
//        S21SsmEZDResult rslt = null;
//
//        // FIXME billing meters are missing.
//        rslt = query.getSvcPhysMtrReadTmpl(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), dsMtrReadTpCd);
//        if (rslt != null && rslt.isCodeNormal()) {
//            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
//            int rsltCnt = rslt.getQueryResultCount();
//            for (int i = 0; i < rsltCnt; i++) {
//                Map<String, Object> rsltMap = rsltList.get(i);
//                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).svcPhysMtrPk_B, (BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
//                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mdlMtrId_B, (String) rsltMap.get("MDL_MTR_ID"));
//                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrLbCd_B, (String) rsltMap.get("MTR_LB_CD"));
//                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrLbDescTxt_B, (String) rsltMap.get("MTR_LB_DESC_TXT"));
//                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).dsMtrReadTpCd_B, (String) rsltMap.get("DS_MTR_READ_TP_CD"));
//                ZYPCodeDataUtil.createPulldownList(DS_MTR_READ_TP.class, cMsg.B.no(i).dsMtrReadTpCd_B1, cMsg.B.no(i).dsMtrReadTpDescTxt_B);
//                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrReadDt_B, mtrReadDt);
//                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).prevReadMtrCnt_B, (BigDecimal) rsltMap.get("PREV_MTR_CNT"));
//                // 
//                String svcPhysMtrPkStr = cMsg.B.no(i).svcPhysMtrPk_B.getValue().toPlainString();
//                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).readMtrCnt_B, readMtrCntMap.get(svcPhysMtrPkStr));
//                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrEntryCmntTxt_B, mtrEntryCmntTxtMap.get(svcPhysMtrPkStr));
//            }
//            cMsg.B.setValidCount(rsltCnt);
//        }
        // del end 2017/09/07 QC#15134
    }

    private void doProcess_NSAL0150Scrn00_OnChange_ReadingType_Row(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        doProcess_NSAL0150Scrn00_Filter(cMsg, sMsg);
    }

    private void doProcess_NSAL0150Scrn00_OpenWin_Estimate(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        // TODO Auto-generated method stub
    }

    private void doProcess_NSAL0150Scrn00_OpenWin_OrderHistory(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        // TODO Auto-generated method stub
    }

    private void doProcess_NSAL0150Scrn00_OpenWin_OrderSupplies(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        // START 2024/03/08 K.Watanabe [QC#63539, DEL]
//        // START 2018/10/15 K.Kitachi [QC#28652, ADD]
//        if (isExclLineBizTp(cMsg)) {
//            return;
//        }
//        // END 2018/10/15 K.Kitachi [QC#28652, ADD]
        // END 2024/03/08 K.Watanabe [QC#63539, DEL]
        // mod start 2018/07/09 QC#26923
        if (!existSplyReadExclCust(cMsg.svcMachMstrPk.getValue())) {
            // START 2016/10/25 [QC#15525, ADD]
            String glblCmpyCd = getGlobalCompanyCode();
            String salesDt = ZYPDateUtil.getSalesDate();
            // START 2024/03/08 K.Watanabe [QC#63539, MOD]
            //BigDecimal mtrEntryIntervalDays = ZYPCodeDataUtil.getNumConstValue(NSAL0150Constant.NSAL0150_MTR_ENTRY_INTVL_DAYS, glblCmpyCd);
            String lineBizTp = getLineBizTp(cMsg);
            BigDecimal mtrEntryIntervalDays = null;
            if (LINE_BIZ_TP.LFS.equals(lineBizTp)) {
                mtrEntryIntervalDays = ZYPCodeDataUtil.getNumConstValue(NSAL0150Constant.NSAL0150_INTVL_DAYS_LFS, glblCmpyCd);
            } else if (LINE_BIZ_TP.PPS.equals(lineBizTp)) {
                mtrEntryIntervalDays = ZYPCodeDataUtil.getNumConstValue(NSAL0150Constant.NSAL0150_INTVL_DAYS_PPS, glblCmpyCd);
            } else if (LINE_BIZ_TP.ESS.equals(lineBizTp)) {
                mtrEntryIntervalDays = ZYPCodeDataUtil.getNumConstValue(NSAL0150Constant.NSAL0150_INTVL_DAYS_WTS, glblCmpyCd);
            }
            // END 2024/03/08 K.Watanabe [QC#63539, MOD]
            if (!ZYPCommonFunc.hasValue(mtrEntryIntervalDays)) {
                mtrEntryIntervalDays = NSAL0150Constant.DEF_SPLY_ODR_MTR_ENTRY_INTVL_DAYS;
            }
            String mtrEntryLimitDt = ZYPDateUtil.addDays(salesDt, mtrEntryIntervalDays.intValue() * -1);

            SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
            inMsg.setSQLID("011");
            inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            inMsg.setConditionValue("svcMachMstrPk01", cMsg.svcMachMstrPk.getValue());
            inMsg.setConditionValue("vldMtrFlg01", ZYPConstant.FLG_ON_Y);
            inMsg.setConditionValue("mtrReadDt01", mtrEntryLimitDt);
            int count = EZDTBLAccessor.count(inMsg);
            if (count == 0) {
                cMsg.setMessageInfo(NSAL0150Constant.NSAM0615E, new String[] {String.valueOf(mtrEntryIntervalDays.intValue()) });
                return;
            }
            // END 2016/10/25 [QC#15525, ADD]
        }
        // mod end 2018/07/09 QC#26923
    }

    private void doProcess_NSAL0150Scrn00_Register(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        // START 2016/06/10 [QC#9591, ADD]
        NSZC049001PMsg nszc049001PMsg = new NSZC049001PMsg();
        setParamForNSZC049001(nszc049001PMsg, cMsg);

        S21ApiMessage msg = new S21ApiMessage();
        String msgId = null;

        NSZC049001 apiNSZC049001 = new NSZC049001();
        apiNSZC049001.execute(nszc049001PMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(nszc049001PMsg)) {
            for (int j = 0; j < S21ApiUtil.getXxMsgList(nszc049001PMsg).size(); j++) {
                msg = S21ApiUtil.getXxMsgList(nszc049001PMsg).get(j);
                msgId = msg.getXxMsgid();

                // START 2018/03/27 U.Kim [QC#23071, MOD]
                // if (msgId.endsWith("E")) {
                if (msgId.endsWith("E") || msgId.endsWith("W")) {
                // END 2018/03/27 U.Kim [QC#23071, MOD]
                    cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                    return;
                }
            }
        }

        cMsg.setMessageInfo(NSAL0150Constant.NZZM0002I);
        search(cMsg, sMsg);
        // END   2016/06/10 [QC#9591, ADD]
    }

    private void doProcess_NSAL0150Scrn00_Filter(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {

        // START 2024/04/08 S.Moriai [QC#63540, ADD]
        String allFromDateFlg = ZYPConstant.FLG_OFF_N;
        String effFromDate = cMsg.effFromDt.getValue();
        if (effFromDate.equals("")) {
            allFromDateFlg = ZYPConstant.FLG_ON_Y;
        }
        
        String allThruDateFlg = ZYPConstant.FLG_OFF_N;
        String effThruDate = cMsg.effThruDt.getValue();
        if (effThruDate.equals("")) {
            allThruDateFlg = ZYPConstant.FLG_ON_Y;
        }
        // END 2024/04/08 S.Moriai [QC#63540, ADD]
        
        String allMtrLbFlg = ZYPConstant.FLG_OFF_N;
        List<String> mtrLbList = new ArrayList<String>();
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.C.no(i).xxChkBox_C.getValue())) {
                if (NSAL0150Constant.PSEUDO_MTR_LB_CD_ALL.equals(cMsg.C.no(i).mtrLbCd_C.getValue())) {
                    allMtrLbFlg = ZYPConstant.FLG_ON_Y;
                } else {
                    mtrLbList.add(cMsg.C.no(i).mtrLbCd_C.getValue());
                }

            }
        }

        String allDsMtrReadTpGrpFlg = ZYPConstant.FLG_OFF_N;
        List<String> dsMtrReadTpGrpList = new ArrayList<String>();
        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.D.no(i).xxChkBox_D.getValue())) {
                if (NSAL0150Constant.PSEUDO_DS_MTR_READ_TP_GRP_CD_ALL.equals(cMsg.D.no(i).dsMtrReadTpGrpCd_D.getValue())) {
                    allDsMtrReadTpGrpFlg = ZYPConstant.FLG_ON_Y;
                } else {
                    dsMtrReadTpGrpList.add(cMsg.D.no(i).dsMtrReadTpGrpCd_D.getValue());
                }
            }
        }

        ZYPTableUtil.clear(cMsg.E);

        // START 2018/10/15 K.Kojima [QC#28748,ADD]
        String glblCmpyCd = getGlobalCompanyCode();
        BigDecimal rollOverExchReadCnt = NSAL0150Query.getInstance().getRollOverExchReadCnt(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
        // END 2018/10/15 K.Kojima [QC#28748,ADD]

        // START 2023/04/12 R.Avelino [QC#61357, ADD]
        int totalFltrCnt = 0;
        for (int s = 0; s < sMsg.E.getValidCount(); s++) {
            // START 2024/04/08 S.Moriai [QC#63540, MOD]
            // if ((ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) || mtrLbList.contains(sMsg.E.no(s).mtrLbCd_E.getValue()))
            //         && (ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg) || dsMtrReadTpGrpList.contains(sMsg.E.no(s).dsMtrReadTpGrpCd_E.getValue()))) {
            if ((ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) || mtrLbList.contains(sMsg.E.no(s).mtrLbCd_E.getValue()))
                    && (ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg) || dsMtrReadTpGrpList.contains(sMsg.E.no(s).dsMtrReadTpGrpCd_E.getValue()))
                            && (ZYPConstant.FLG_ON_Y.equals(allFromDateFlg) || NSAL0150CommonLogic.compareDate(effFromDate, sMsg.E.no(s).mtrReadDt_E.getValue()))
                                        && (ZYPConstant.FLG_ON_Y.equals(allThruDateFlg) || NSAL0150CommonLogic.compareDate(sMsg.E.no(s).mtrReadDt_E.getValue(), effThruDate))) {
            // END 2024/04/08 S.Moriai [QC#63540, MOD]
                totalFltrCnt++;
            }
        }
        // START 2024/04/08 S.Moriai [QC#63540, MOD]
        // if (ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) && ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg)) {
        //     totalFltrCnt = sMsg.E.getValidCount();
        // }
        if (ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) && ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg)
                && ZYPConstant.FLG_ON_Y.equals(allFromDateFlg) && ZYPConstant.FLG_ON_Y.equals(allThruDateFlg)) {
            totalFltrCnt = sMsg.E.getValidCount();
        }
        // END 2024/04/08 S.Moriai [QC#63540, MOD]
        // END 2023/04/12 R.Avelino [QC#61357, ADD]

        int c = 0;
        for (int s = 0; s < sMsg.E.getValidCount(); s++) {
            // START 2024/04/08 S.Moriai [QC#63540, MOD]
            // if ((ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) || mtrLbList.contains(sMsg.E.no(s).mtrLbCd_E.getValue()))
            //        && (ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg) || dsMtrReadTpGrpList.contains(sMsg.E.no(s).dsMtrReadTpGrpCd_E.getValue()))) {
                if ((ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) || mtrLbList.contains(sMsg.E.no(s).mtrLbCd_E.getValue()))
                        && (ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg) || dsMtrReadTpGrpList.contains(sMsg.E.no(s).dsMtrReadTpGrpCd_E.getValue()))
                                && (ZYPConstant.FLG_ON_Y.equals(allFromDateFlg) || NSAL0150CommonLogic.compareDate(effFromDate, sMsg.E.no(s).mtrReadDt_E.getValue()))
                                        && (ZYPConstant.FLG_ON_Y.equals(allThruDateFlg) || NSAL0150CommonLogic.compareDate(sMsg.E.no(s).mtrReadDt_E.getValue(), effThruDate))) {
            // END 2024/04/08 S.Moriai [QC#63540, MOD]
                // START 2018/10/15 K.Kojima [QC#28748,ADD]
                // START 2018/10/18 K.Kojima [QC#28848,MOD]
                // if (!ZYPCommonFunc.hasValue(sMsg.E.no(s).mtrCnt_EN)) {
                //     BigDecimal netMtrCnt = getNetMtrCnt(glblCmpyCd, sMsg.E.no(s), rollOverExchReadCnt);
                //     ZYPEZDItemValueSetter.setValue(sMsg.E.no(s).mtrCnt_EN, netMtrCnt);
                // }
                // if (!ZYPCommonFunc.hasValue(sMsg.E.no(s).mtrCnt_EA)) {
                //     BigDecimal avgMtrCnt = getAvgMtrCnt(glblCmpyCd, sMsg.E.no(s), rollOverExchReadCnt);
                //     ZYPEZDItemValueSetter.setValue(sMsg.E.no(s).mtrCnt_EA, avgMtrCnt);
                // }
                if (!ZYPCommonFunc.hasValue(sMsg.E.no(s).avgCopyVolCnt_EN)) {
                    BigDecimal netMtrCnt = getNetMtrCnt(glblCmpyCd, sMsg.E.no(s), rollOverExchReadCnt);
                    ZYPEZDItemValueSetter.setValue(sMsg.E.no(s).avgCopyVolCnt_EN, netMtrCnt);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.E.no(s).avgCopyVolCnt_EA)) {
                    BigDecimal avgMtrCnt = getAvgMtrCnt(glblCmpyCd, sMsg.E.no(s), rollOverExchReadCnt);
                    ZYPEZDItemValueSetter.setValue(sMsg.E.no(s).avgCopyVolCnt_EA, avgMtrCnt);
                }
                // END 2018/10/18 K.Kojima [QC#28848,MOD]
                // END 2018/10/15 K.Kojima [QC#28748,ADD]
                EZDMsg.copy(sMsg.E.no(s), null, cMsg.E.no(c), null);
                c++;
                // START 2016/10/18 T.Tomita [QC#15312, ADD]
                if (cMsg.E.length() <= c) {
                    break;
                }
                // END 2016/10/18 T.Tomita [QC#15312, ADD]
            }
        }
        cMsg.E.setValidCount(c);

        // START 2023/04/12 R.Avelino [QC#61357, ADD]
        // START 2024/04/08 S.Moriai [QC#63540, MOD]
        // if ((ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) || !mtrLbList.isEmpty()) && (ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg) || !dsMtrReadTpGrpList.isEmpty())) {
        if ((ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) || !mtrLbList.isEmpty()) && (ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg) || !dsMtrReadTpGrpList.isEmpty())
                && (ZYPConstant.FLG_ON_Y.equals(allFromDateFlg) || !effFromDate.equals("")) && (ZYPConstant.FLG_ON_Y.equals(allThruDateFlg) || !effThruDate.equals(""))) {
        // END 2024/04/08 S.Moriai [QC#63540, MOD]
            cMsg.xxPageShowFromNum.setValue(BigDecimal.ONE);
            cMsg.xxPageShowToNum.setValue(cMsg.E.getValidCount());
            cMsg.xxPageShowOfNum.setValue(totalFltrCnt);
        }
        // END 2023/04/12 R.Avelino [QC#61357, ADD]

    }
    // ADD START 2015/11/26 K.Kasai [Unit Test #71]
    private void doProcess_NSAL0150Scrn00_PagePrev(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {

        // START 2023/04/12 R.Avelino [QC#61357, ADD]
        List<String> mtrLbList = new ArrayList<String>();
        List<String> dsMtrReadTpGrpList = new ArrayList<String>();
        // START 2024/04/08 S.Moriai [QC#63540, ADD]
        String effFromDate = cMsg.effFromDt.getValue();
        String effThruDate = cMsg.effThruDt.getValue();
        // END 2024/04/08 S.Moriai [QC#63540, ADD]
        // START 2024/04/08 S.Moriai [QC#63540, MOD]
        // HashMap<String, String> allFlg = checkMtrHistoryFilter(cMsg, sMsg, mtrLbList, dsMtrReadTpGrpList);
        HashMap<String, String> allFlg = checkMtrHistoryFilter(cMsg, sMsg, mtrLbList, dsMtrReadTpGrpList, effFromDate, effThruDate);
        // END 2024/04/08 S.Moriai [QC#63540, MOD]
        String allMtrLbFlg = allFlg.get(NSAL0150Constant.MTR_LB_FLG);
        String allDsMtrReadTpGrpFlg = allFlg.get(NSAL0150Constant.DS_MTR_READ_TP_GRP_FLG);
        // START 2024/04/08 S.Moriai [QC#63540, ADD]
        String allFromDateFlg = allFlg.get(NSAL0150Constant.FROM_DATE_FLG);
        String allThruDateFlg = allFlg.get(NSAL0150Constant.THRU_DATE_FLG);
        // END 2024/04/08 S.Moriai [QC#63540, ADD]

        // Count the total of filtered entries
        int totalFltrCnt = 0;
        for (int s = 0; s < sMsg.E.getValidCount(); s++) {
            // START 2024/04/08 S.Moriai [QC#63540, MOD]
            // if ((ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) || mtrLbList.contains(sMsg.E.no(s).mtrLbCd_E.getValue()))
            //         && (ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg) || dsMtrReadTpGrpList.contains(sMsg.E.no(s).dsMtrReadTpGrpCd_E.getValue()))) {
            if ((ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) || mtrLbList.contains(sMsg.E.no(s).mtrLbCd_E.getValue()))
                    && (ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg) || dsMtrReadTpGrpList.contains(sMsg.E.no(s).dsMtrReadTpGrpCd_E.getValue()))
                            && (ZYPConstant.FLG_ON_Y.equals(allFromDateFlg) || NSAL0150CommonLogic.compareDate(effFromDate, sMsg.E.no(s).mtrReadDt_E.getValue()))
                                    && (ZYPConstant.FLG_ON_Y.equals(allThruDateFlg) || NSAL0150CommonLogic.compareDate(sMsg.E.no(s).mtrReadDt_E.getValue(), effThruDate))) {
            // END 2024/04/08 S.Moriai [QC#63540, MOD]
                totalFltrCnt++;
            }
        }
        // START 2024/04/08 S.Moriai [QC#63540, MOD]
        // if (ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) && ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg)) {
        if (ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) && ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg)
                && ZYPConstant.FLG_ON_Y.equals(allFromDateFlg) && ZYPConstant.FLG_ON_Y.equals(allThruDateFlg)) {
        // END 2024/04/08 S.Moriai [QC#63540, MOD]
            totalFltrCnt = sMsg.E.length();
        }
        // END 2023/04/12 R.Avelino [QC#61357, ADD]

        // copy data from SMsg onto CMsg
        // START 2018/09/26 K.Kojima [QC#28388,ADD]
        String glblCmpyCd = getGlobalCompanyCode();
        BigDecimal rollOverExchReadCnt = NSAL0150Query.getInstance().getRollOverExchReadCnt(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
        // END 2018/09/26 K.Kojima [QC#28388,ADD]
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
// START 2023/04/12 R.Avelino [QC#61357, MOD]
//        int i = pagenationFrom;
//        for(; i < pagenationFrom + cMsg.E.length(); i++) {
//            if(i < sMsg.E.getValidCount()) {
                // START 2018/09/26 K.Kojima [QC#28388,ADD]
                // START 2018/10/18 K.Kojima [QC#28848,MOD]
                // if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).mtrCnt_EN)) {
                //     BigDecimal netMtrCnt = getNetMtrCnt(glblCmpyCd, sMsg.E.no(i), rollOverExchReadCnt);
                //     ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mtrCnt_EN, netMtrCnt);
                // }
                // if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).mtrCnt_EA)) {
                //     BigDecimal avgMtrCnt = getAvgMtrCnt(glblCmpyCd, sMsg.E.no(i), rollOverExchReadCnt);
                //     ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mtrCnt_EA, avgMtrCnt);
                // }
//                if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).avgCopyVolCnt_EN)) {
//                    BigDecimal netMtrCnt = getNetMtrCnt(glblCmpyCd, sMsg.E.no(i), rollOverExchReadCnt);
//                    ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).avgCopyVolCnt_EN, netMtrCnt);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).avgCopyVolCnt_EA)) {
//                    BigDecimal avgMtrCnt = getAvgMtrCnt(glblCmpyCd, sMsg.E.no(i), rollOverExchReadCnt);
//                    ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).avgCopyVolCnt_EA, avgMtrCnt);
//                }
                // END 2018/10/18 K.Kojima [QC#28848,MOD]
                // END 2018/09/26 K.Kojima [QC#28388,ADD]
//                EZDMsg.copy(sMsg.E.no(i), null, cMsg.E.no(i - pagenationFrom), null);
//           } else {
//                break;
//            }
//        }
//        cMsg.E.setValidCount(i - pagenationFrom);
        int cMsgCount = pagenationFrom;
        int fltrCnt = 0;
        for (int sMsgCount = 0; sMsgCount < sMsg.E.getValidCount(); sMsgCount++) {
            //Check all entries if filter applies
            // START 2024/04/08 S.Moriai [QC#63540, MOD]
            // if ((ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) || mtrLbList.contains(sMsg.E.no(sMsgCount).mtrLbCd_E.getValue()))
            //         && (ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg) || dsMtrReadTpGrpList.contains(sMsg.E.no(sMsgCount).dsMtrReadTpGrpCd_E.getValue()))) {
            if ((ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) || mtrLbList.contains(sMsg.E.no(sMsgCount).mtrLbCd_E.getValue()))
                    && (ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg) || dsMtrReadTpGrpList.contains(sMsg.E.no(sMsgCount).dsMtrReadTpGrpCd_E.getValue()))
                            && (ZYPConstant.FLG_ON_Y.equals(allFromDateFlg) || NSAL0150CommonLogic.compareDate(effFromDate, sMsg.E.no(sMsgCount).mtrReadDt_E.getValue()))
                                    && (ZYPConstant.FLG_ON_Y.equals(allThruDateFlg) || NSAL0150CommonLogic.compareDate(sMsg.E.no(sMsgCount).mtrReadDt_E.getValue(), effThruDate))) {
            // END 2024/04/08 S.Moriai [QC#63540, MOD]
                fltrCnt++;
                // Allow filtered entries when there count is greater than pagenationFrom
                if (fltrCnt <= totalFltrCnt && fltrCnt > pagenationFrom) {
                    if (cMsgCount < (pagenationFrom + cMsg.E.length()) && cMsgCount < sMsg.E.getValidCount()) {
                        insertMtrHistoryTable(cMsg, sMsg, cMsgCount, sMsgCount, pagenationFrom, rollOverExchReadCnt);
                        cMsgCount++;
                    } else {
                        break;
                    }
                }
            }
        }

        cMsg.E.setValidCount(cMsgCount - pagenationFrom);
// END 2023/04/12 R.Avelino [QC#61357, MOD]

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.E.getValidCount() - 1);
    }
    // START 2023/04/12 R.Avelino [QC#61357, ADD]
    // START 2024/04/08 S.Moriai [QC#63540, MOD]
//    /**
//     * checkMtrHistoryFilter
//     * @param NSAL0150CMsg cMsg
//     * @param NSAL0150SMsg sMsg
//     * @param List<String> mtrLbList
//     * @param List<String> dsMtrReadTpGrpList
//     * @return HashMap<String, String>
//     */
//    private static HashMap<String, String> checkMtrHistoryFilter(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg, List<String> mtrLbList, List<String> dsMtrReadTpGrpList) {
//
//        String allMtrLbFlg = ZYPConstant.FLG_OFF_N;
//        String allDsMtrReadTpGrpFlg = ZYPConstant.FLG_OFF_N;
//        HashMap<String, String> allFlg = new HashMap<String, String>();
//        // Checking if there is a filter
//        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
//            if (ZYPConstant.FLG_ON_Y.equals(cMsg.C.no(i).xxChkBox_C.getValue())) {
//                if (NSAL0150Constant.PSEUDO_MTR_LB_CD_ALL.equals(cMsg.C.no(i).mtrLbCd_C.getValue())) {
//                    allMtrLbFlg = ZYPConstant.FLG_ON_Y;
//                } else {
//                    mtrLbList.add(cMsg.C.no(i).mtrLbCd_C.getValue());
//                }
//
//            }
//        }
//
//        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
//            if (ZYPConstant.FLG_ON_Y.equals(cMsg.D.no(i).xxChkBox_D.getValue())) {
//                if (NSAL0150Constant.PSEUDO_DS_MTR_READ_TP_GRP_CD_ALL.equals(cMsg.D.no(i).dsMtrReadTpGrpCd_D.getValue())) {
//                    allDsMtrReadTpGrpFlg = ZYPConstant.FLG_ON_Y;
//                } else {
//                    dsMtrReadTpGrpList.add(cMsg.D.no(i).dsMtrReadTpGrpCd_D.getValue());
//                }
//            }
//        }
//
//        allFlg.put(NSAL0150Constant.MTR_LB_FLG, allMtrLbFlg);
//        allFlg.put(NSAL0150Constant.DS_MTR_READ_TP_GRP_FLG, allDsMtrReadTpGrpFlg);
//
//        return allFlg;
//    }
    
    /**
     * checkMtrHistoryFilter
     * @param NSAL0150CMsg cMsg
     * @param NSAL0150SMsg sMsg
     * @param List<String> mtrLbList
     * @param List<String> dsMtrReadTpGrpList
     * @param String effFromDate
     * @param String effThruDate
     * @return HashMap<String, String>
     */
    private static HashMap<String, String> checkMtrHistoryFilter(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg, List<String> mtrLbList, List<String> dsMtrReadTpGrpList, String effFromDate, String effThruDate) {

        String allMtrLbFlg = ZYPConstant.FLG_OFF_N;
        String allDsMtrReadTpGrpFlg = ZYPConstant.FLG_OFF_N;
        String allFromDateFlg = ZYPConstant.FLG_OFF_N;
        String allThruDateFlg = ZYPConstant.FLG_OFF_N;
        HashMap<String, String> allFlg = new HashMap<String, String>();
        // Checking if there is a filter
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.C.no(i).xxChkBox_C.getValue())) {
                if (NSAL0150Constant.PSEUDO_MTR_LB_CD_ALL.equals(cMsg.C.no(i).mtrLbCd_C.getValue())) {
                    allMtrLbFlg = ZYPConstant.FLG_ON_Y;
                } else {
                    mtrLbList.add(cMsg.C.no(i).mtrLbCd_C.getValue());
                }

            }
        }

        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.D.no(i).xxChkBox_D.getValue())) {
                if (NSAL0150Constant.PSEUDO_DS_MTR_READ_TP_GRP_CD_ALL.equals(cMsg.D.no(i).dsMtrReadTpGrpCd_D.getValue())) {
                    allDsMtrReadTpGrpFlg = ZYPConstant.FLG_ON_Y;
                } else {
                    dsMtrReadTpGrpList.add(cMsg.D.no(i).dsMtrReadTpGrpCd_D.getValue());
                }
            }
        }
        
        if (effFromDate.equals("")) {
            allFromDateFlg = ZYPConstant.FLG_ON_Y;
        }

        if (effThruDate.equals("")) {
            allThruDateFlg =  ZYPConstant.FLG_ON_Y;
        }

        allFlg.put(NSAL0150Constant.MTR_LB_FLG, allMtrLbFlg);
        allFlg.put(NSAL0150Constant.DS_MTR_READ_TP_GRP_FLG, allDsMtrReadTpGrpFlg);
        allFlg.put(NSAL0150Constant.FROM_DATE_FLG, allFromDateFlg);
        allFlg.put(NSAL0150Constant.THRU_DATE_FLG, allThruDateFlg);

        return allFlg;
    }
    // END 2024/04/08 S.Moriai [QC#63540, MOD]

    /**
     * insertMtrHistoryTable
     * @param NSAL0150CMsg cMsg
     * @param NSAL0150SMsg sMsg
     * @param int cMsgCount
     * @param int sMsgCount
     * @param int pagenationFrom
     * @param BigDecimal rollOverExchReadCnt
     */
    private void insertMtrHistoryTable(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg, int cMsgCount, int sMsgCount, int pagenationFrom, BigDecimal rollOverExchReadCnt) {

        String glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(sMsg.E.no(sMsgCount).avgCopyVolCnt_EN)) {
            BigDecimal netMtrCnt = getNetMtrCnt(glblCmpyCd, sMsg.E.no(sMsgCount), rollOverExchReadCnt);
            ZYPEZDItemValueSetter.setValue(sMsg.E.no(sMsgCount).avgCopyVolCnt_EN, netMtrCnt);
        }
        if (!ZYPCommonFunc.hasValue(sMsg.E.no(sMsgCount).avgCopyVolCnt_EA)) {
            BigDecimal avgMtrCnt = getAvgMtrCnt(glblCmpyCd, sMsg.E.no(sMsgCount), rollOverExchReadCnt);
            ZYPEZDItemValueSetter.setValue(sMsg.E.no(sMsgCount).avgCopyVolCnt_EA, avgMtrCnt);
        }
        EZDMsg.copy(sMsg.E.no(sMsgCount), null, cMsg.E.no(cMsgCount - pagenationFrom), null);
    }
    // END 2023/04/12 R.Avelino [QC#61357, ADD]

    private void doProcess_NSAL0150Scrn00_PageNext(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {

        // START 2023/04/12 R.Avelino [QC#61357, ADD]
        List<String> mtrLbList = new ArrayList<String>();
        List<String> dsMtrReadTpGrpList = new ArrayList<String>();
        // START 2024/04/08 S.Moriai [QC#63540, ADD]
        String effFromDate = cMsg.effFromDt.getValue();
        String effThruDate = cMsg.effThruDt.getValue();
        // END 2024/04/08 S.Moriai [QC#63540, ADD]
        // START 2024/04/08 S.Moriai [QC#63540, MOD]
        // HashMap<String, String> allFlg = checkMtrHistoryFilter(cMsg, sMsg, mtrLbList, dsMtrReadTpGrpList);
        HashMap<String, String> allFlg = checkMtrHistoryFilter(cMsg, sMsg, mtrLbList, dsMtrReadTpGrpList, effFromDate, effThruDate);
        // END 2024/04/08 S.Moriai [QC#63540, MOD]
        String allMtrLbFlg = allFlg.get(NSAL0150Constant.MTR_LB_FLG);
        String allDsMtrReadTpGrpFlg = allFlg.get(NSAL0150Constant.DS_MTR_READ_TP_GRP_FLG);
        // START 2024/04/08 S.Moriai [QC#63540, ADD]
        String allFromDateFlg = allFlg.get(NSAL0150Constant.FROM_DATE_FLG);
        String allThruDateFlg = allFlg.get(NSAL0150Constant.THRU_DATE_FLG);
        // END 2024/04/08 S.Moriai [QC#63540, ADD]

        // Count the total of filtered entries
        int totalFltrCnt = 0;
        for (int s = 0; s < sMsg.E.getValidCount(); s++) {
        // START 2024/04/08 S.Moriai [#QC63540, MOD]
            // if ((ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) || mtrLbList.contains(sMsg.E.no(s).mtrLbCd_E.getValue()))
            //         && (ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg) || dsMtrReadTpGrpList.contains(sMsg.E.no(s).dsMtrReadTpGrpCd_E.getValue()))){
            if ((ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) || mtrLbList.contains(sMsg.E.no(s).mtrLbCd_E.getValue()))
                    && (ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg) || dsMtrReadTpGrpList.contains(sMsg.E.no(s).dsMtrReadTpGrpCd_E.getValue()))
                            && (ZYPConstant.FLG_ON_Y.equals(allFromDateFlg) || NSAL0150CommonLogic.compareDate(effFromDate, sMsg.E.no(s).mtrReadDt_E.getValue()))
                                    && (ZYPConstant.FLG_ON_Y.equals(allThruDateFlg) || NSAL0150CommonLogic.compareDate(sMsg.E.no(s).mtrReadDt_E.getValue(), effThruDate))) {
        // END 2024/04/08 S.Moriai [#QC63540, MOD]
                totalFltrCnt++;
            }
        }
        // START 2024/04/08 S.Moriai [#QC63540, MOD]
        // if (ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) && ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg)) {
        if (ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) && ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg)
                && ZYPConstant.FLG_ON_Y.equals(allFromDateFlg) && ZYPConstant.FLG_ON_Y.equals(allThruDateFlg)) {
        // END 2024/04/08 S.Moriai [#QC63540, MOD]
            totalFltrCnt = sMsg.E.length();
        }
        // END 2023/04/12 R.Avelino [QC#61357, ADD]

        // copy data from SMsg onto CMsg
        // START 2018/09/26 K.Kojima [QC#28388,ADD]
        String glblCmpyCd = getGlobalCompanyCode();
        BigDecimal rollOverExchReadCnt = NSAL0150Query.getInstance().getRollOverExchReadCnt(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
        // END 2018/09/26 K.Kojima [QC#28388,ADD]
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
// START 2023/04/12 R.Avelino [QC#61357, MOD]
//        int i = pagenationFrom;
//        for(; i < pagenationFrom + cMsg.A.length(); i++) {
//            if(i < sMsg.E.getValidCount()) {
                // START 2018/09/26 K.Kojima [QC#28388,ADD]
                // START 2018/10/18 K.Kojima [QC#28848,MOD]
                // if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).mtrCnt_EN)) {
                //     BigDecimal netMtrCnt = getNetMtrCnt(glblCmpyCd, sMsg.E.no(i), rollOverExchReadCnt);
                //     ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mtrCnt_EN, netMtrCnt);
                // }
                // if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).mtrCnt_EA)) {
                //     BigDecimal avgMtrCnt = getAvgMtrCnt(glblCmpyCd, sMsg.E.no(i), rollOverExchReadCnt);
                //     ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).mtrCnt_EA, avgMtrCnt);
                // }
//                if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).avgCopyVolCnt_EN)) {
//                    BigDecimal netMtrCnt = getNetMtrCnt(glblCmpyCd, sMsg.E.no(i), rollOverExchReadCnt);
//                    ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).avgCopyVolCnt_EN, netMtrCnt);
//                }
//                if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).avgCopyVolCnt_EA)) {
//                    BigDecimal avgMtrCnt = getAvgMtrCnt(glblCmpyCd, sMsg.E.no(i), rollOverExchReadCnt);
//                    ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).avgCopyVolCnt_EA, avgMtrCnt);
//                }
                // END 2018/10/18 K.Kojima [QC#28848,MOD]
                // END 2018/09/26 K.Kojima [QC#28388,ADD]
//                EZDMsg.copy(sMsg.E.no(i), null, cMsg.E.no(i - pagenationFrom), null);
//            } else {
//                break;
//            }
//        }

//        cMsg.E.setValidCount(i - pagenationFrom);
        int cMsgCount = pagenationFrom;
        int fltrCnt = 0;
        for (int sMsgCount = 0; sMsgCount < sMsg.E.getValidCount(); sMsgCount++) {
            //Check all entries if filter applies
            // START 2024/04/08 S.Moriai [QC#63540 ,MOD]
            // if ((ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) || mtrLbList.contains(sMsg.E.no(sMsgCount).mtrLbCd_E.getValue()))
            //         && (ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg) || dsMtrReadTpGrpList.contains(sMsg.E.no(sMsgCount).dsMtrReadTpGrpCd_E.getValue()))) {
            if ((ZYPConstant.FLG_ON_Y.equals(allMtrLbFlg) || mtrLbList.contains(sMsg.E.no(sMsgCount).mtrLbCd_E.getValue()))
                    && (ZYPConstant.FLG_ON_Y.equals(allDsMtrReadTpGrpFlg) || dsMtrReadTpGrpList.contains(sMsg.E.no(sMsgCount).dsMtrReadTpGrpCd_E.getValue()))
                            && (ZYPConstant.FLG_ON_Y.equals(allFromDateFlg) || NSAL0150CommonLogic.compareDate(effFromDate, sMsg.E.no(sMsgCount).mtrReadDt_E.getValue()))
                                    && (ZYPConstant.FLG_ON_Y.equals(allThruDateFlg) || NSAL0150CommonLogic.compareDate(sMsg.E.no(sMsgCount).mtrReadDt_E.getValue(), effThruDate))) {
            // END 2024/04/08 S.Moriai [QC#63540, MOD]
                fltrCnt++;
                // Allow filtered entries when there count is greater than pagenationFrom
                if (fltrCnt <= totalFltrCnt && fltrCnt > pagenationFrom) {
                    if (cMsgCount < pagenationFrom + cMsg.E.length() && cMsgCount < sMsg.E.getValidCount()) {
                        insertMtrHistoryTable(cMsg, sMsg, cMsgCount, sMsgCount, pagenationFrom, rollOverExchReadCnt);
                        cMsgCount++;
                    } else {
                        break;
                    }
                }
            }
        }

        cMsg.E.setValidCount(cMsgCount - pagenationFrom);
// END 2023/04/12 R.Avelino [QC#61357, MOD]

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.E.getValidCount());
    }
    // ADD END 2015/11/26 K.Kasai [Unit Test #71]

    // START 2018/05/10 K.Kojima [QC#24817,ADD]
    private void doProcess_NSAL0150Scrn00_ChangeDsMtrReadTpGrpCd(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        // START 2018/05/25 M.Naito [QC#15410, MOD]
//        Map<String, Object> dsContrDtlKey = NSAL0150Query.getInstance().getContrDtlKeysByMachMstrPk(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), ZYPDateUtil.getSalesDate());
        DS_CONTRTMsg contrInfo = null;
        DS_CONTR_DTLTMsg contrDtlInfo = null;
        if (ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk)) {
            BigDecimal dsContrPk = cMsg.dsContrPk.getValue();
            BigDecimal dsContrDtlPk = cMsg.dsContrDtlPk.getValue();

            contrInfo = new DS_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(contrInfo.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(contrInfo.dsContrPk, dsContrPk);
            contrInfo = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(contrInfo);

            contrDtlInfo = new DS_CONTR_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(contrDtlInfo.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(contrDtlInfo.dsContrPk, dsContrPk);
            ZYPEZDItemValueSetter.setValue(contrDtlInfo.dsContrDtlPk, dsContrDtlPk);
            contrDtlInfo = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(contrDtlInfo);
        }
        // END   2018/05/25 M.Naito [QC#15410, MOD]
        List<BigDecimal> countMeterEntryContr = NSAL0150Query.getInstance().getMeterEntryContrDtlPk(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
        if (cMsg.dsMtrReadTpGrpCd_BS.getValue().equals(DS_MTR_READ_TP_GRP.BILLABLE_READS)) {
            createNewInputMeterList(cMsg, glblCmpyCd, contrDtlInfo, true, countMeterEntryContr);
        } else {
            createNewInputMeterList(cMsg, glblCmpyCd, contrDtlInfo, false, null);
        }
    }
    // END 2018/05/10 K.Kojima [QC#24817,ADD]

    // START 2018/06/25 T.Ogura [QC#26336,ADD]
    /**
     * CMN_Download Event
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NSAL0150Scrn00_CMN_Download(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        NSAL0150Query.getInstance().createCSV(cMsg, sMsg);
    }
    // END   2018/06/25 T.Ogura [QC#26336,ADD]

    // START 2016/06/10 [QC#9591, ADD]
    private void setParamForNSZC049001(NSZC049001PMsg pMsg, NSAL0150CMsg cMsg){
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.usrId, NSAL0150Constant.BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, cMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.svcCallFlg, ZYPConstant.FLG_ON_Y);
    }

    private void setParamForNSZC050001(NSZC050001PMsg pMsg, NSAL0150CMsg cMsg){
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, cMsg.svcMachMstrPk);
    }
    // END   2016/06/10 [QC#9591, ADD]

    // START 2016/12/07 [QC#15200, ADD]
    private DS_CONTRTMsg getContrByMachMstrPkForOrderHistory(String glblCmpyCd, BigDecimal svcMachMstrPk, String trxDt) {
        Map<String, Object> dsContrDtlKey_OH = NSAL0150Query.getInstance().getContrDtlKeysByMachMstrPkForOrderHistory(glblCmpyCd, svcMachMstrPk, trxDt);
        if (dsContrDtlKey_OH != null) {
            BigDecimal dsContrPk_OH = (BigDecimal)dsContrDtlKey_OH.get("DS_CONTR_PK");
            DS_CONTRTMsg contrInfo_OH = new DS_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(contrInfo_OH.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(contrInfo_OH.dsContrPk, dsContrPk_OH);
            return (DS_CONTRTMsg) EZDTBLAccessor.findByKey(contrInfo_OH);
        }
        return null;
    }

    private String getDsOrdCatgDescTxt(String glblCmpyCd, String lineBizTpCd, CovTmplInfo tmplInfo) {
        String dsOrdCatgCd = null;
        String dsOrdCatgDescTxt = null;

        NSXC001001GetCovTmpl covTmpl = new NSXC001001GetCovTmpl();
        boolean isSuplIncl = covTmpl.isSuplIncl(tmplInfo);
        boolean isLaserPlusContr = covTmpl.isLaserPlusContr(tmplInfo);

        SVC_SPLY_ORD_TP_RELNTMsg condition = new SVC_SPLY_ORD_TP_RELNTMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("lineBizTpCd01", lineBizTpCd);
        if (isSuplIncl) {
            condition.setConditionValue("splyInclFlg01", ZYPConstant.FLG_ON_Y);
        } else {
            condition.setConditionValue("splyInclFlg01", ZYPConstant.FLG_OFF_N);
        }
        if (isLaserPlusContr) {
            condition.setConditionValue("laserPlusFlg01", ZYPConstant.FLG_ON_Y);
        } else {
            condition.setConditionValue("laserPlusFlg01", ZYPConstant.FLG_OFF_N);
        }

        SVC_SPLY_ORD_TP_RELNTMsgArray outTMsgArray = (SVC_SPLY_ORD_TP_RELNTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (outTMsgArray.getValidCount() > 0) {
            dsOrdCatgCd = outTMsgArray.no(0).dsOrdCatgCd.getValue();

        } else {
            condition.setConditionValue("lineBizTpCd01", NSAL0150Constant.DEF_LINE_BIZ_CD);
            outTMsgArray = (SVC_SPLY_ORD_TP_RELNTMsgArray) EZDTBLAccessor.findByCondition(condition);
            if (outTMsgArray.getValidCount() > 0) {
                dsOrdCatgCd = outTMsgArray.no(0).dsOrdCatgCd.getValue();
            }
        }

        if (ZYPCommonFunc.hasValue(dsOrdCatgCd))  {
            DS_ORD_CATGTMsg dsOrdCatg = new DS_ORD_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(dsOrdCatg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsOrdCatg.dsOrdCatgCd, dsOrdCatgCd);
            dsOrdCatg = (DS_ORD_CATGTMsg) EZDTBLAccessor.findByKey(dsOrdCatg);
            if (dsOrdCatg != null) {
                dsOrdCatgDescTxt = dsOrdCatg.dsOrdCatgDescTxt.getValue();
            }
        }

        return dsOrdCatgDescTxt;
    }
    // END   2016/12/07 [QC#15200, ADD]

    // add start 2017/09/07 QC#15134
    private void createDsMtrReadTpPulldown(String glblCmpyCd, NSAL0150_BCMsg bCMsg) {
        DS_MTR_READ_TPTMsgArray tMsgArray = NSAL0150Query.getInstance().getDsMtrReadTpList(glblCmpyCd);
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bCMsg.dsMtrReadTpCd_B1.no(i), tMsgArray.no(i).dsMtrReadTpCd);
            ZYPEZDItemValueSetter.setValue(bCMsg.dsMtrReadTpDescTxt_B.no(i), tMsgArray.no(i).dsMtrReadTpDescTxt);
        }
    }

    private String addRollOverExchComment(String cntrResetTpCd, String mtrEntryCmntTxt) {
        String addMtrEntryCmntTxt = mtrEntryCmntTxt;
        if (ZYPCommonFunc.hasValue(cntrResetTpCd)) {
            if (CNTR_RESET_TP.METER_ROLLOVER.equals(cntrResetTpCd)) {
                if (ZYPCommonFunc.hasValue(mtrEntryCmntTxt)) {
                    addMtrEntryCmntTxt = NSAL0150Constant.ROLL_OVER_COMMENT.concat(NSAL0150Constant.SPACE).concat(mtrEntryCmntTxt);
                } else {
                    addMtrEntryCmntTxt = NSAL0150Constant.ROLL_OVER_COMMENT;
                }
            }
            if (CNTR_RESET_TP.METER_EXCHANGE.equals(cntrResetTpCd)) {
                if (ZYPCommonFunc.hasValue(mtrEntryCmntTxt)) {
                    addMtrEntryCmntTxt = NSAL0150Constant.EXCHANGE_COMMENT.concat(NSAL0150Constant.SPACE).concat(mtrEntryCmntTxt);
                } else {
                    addMtrEntryCmntTxt = NSAL0150Constant.EXCHANGE_COMMENT;
                }
            }
        }
        return S21StringUtil.subStringByLength(addMtrEntryCmntTxt, 0, NSAL0150Constant.COMMENT_LENGTH);
    }

    // START 2018/09/26 K.Kojima [QC#28388,MOD]
    // private BigDecimal getNetMtrCnt(String glblCmpyCd, Map<String, Object> rsltMap) {
    private BigDecimal getNetMtrCnt(String glblCmpyCd, NSAL0150_ESMsg esMsg, BigDecimal rollOverExchReadCnt) {
    // END 2018/09/26 K.Kojima [QC#28388,MOD]
        // START 2018/02/22 U.Kim [QC#24202, MOD]
        // SvcPhysMtrReadInfoBean starRead = new SvcPhysMtrReadInfoBean();
        // starRead.setSvcPhysMtrReadPk((BigDecimal) rsltMap.get("START_SVC_PHYS_MTR_READ_PK"));
        // starRead.setSvcPhysMtrPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
        // starRead.setMtrReadDt((String) rsltMap.get("START_MTR_READ_DT"));
        // starRead.setReadMtrCnt((BigDecimal) rsltMap.get("START_READ_MTR_CNT"));
        SvcPhysMtrReadInfoBean befRead = new SvcPhysMtrReadInfoBean();
        // START 2018/09/26 K.Kojima [QC#28388,MOD]
        // befRead.setSvcPhysMtrReadPk((BigDecimal) rsltMap.get("BEF_SVC_PHYS_MTR_READ_PK"));
        // befRead.setSvcPhysMtrPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
        // befRead.setMtrReadDt((String) rsltMap.get("BEF_MTR_READ_DT"));
        // befRead.setReadMtrCnt((BigDecimal) rsltMap.get("BEF_READ_MTR_CNT"));
        befRead.setSvcPhysMtrReadPk(esMsg.svcPhysMtrReadPk_EB.getValue());
        befRead.setSvcPhysMtrPk(esMsg.svcPhysMtrPk_E.getValue());
        befRead.setMtrReadDt(esMsg.mtrReadDt_EB.getValue());
        befRead.setReadMtrCnt(esMsg.readMtrCnt_EB.getValue());
        // END 2018/09/26 K.Kojima [QC#28388,MOD]
        // END 2018/02/22 U.Kim [QC#24202, MOD]

        SvcPhysMtrReadInfoBean endRead = new SvcPhysMtrReadInfoBean();
        // START 2018/09/26 K.Kojima [QC#28388,MOD]
        // endRead.setSvcPhysMtrReadPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_PK"));
        // endRead.setSvcPhysMtrPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
        // endRead.setMtrReadDt((String) rsltMap.get("MTR_READ_DT"));
        // endRead.setReadMtrCnt((BigDecimal) rsltMap.get("READ_MTR_CNT"));
        endRead.setSvcPhysMtrReadPk(esMsg.svcPhysMtrReadPk_E.getValue());
        endRead.setSvcPhysMtrPk(esMsg.svcPhysMtrPk_E.getValue());
        endRead.setMtrReadDt(esMsg.mtrReadDt_E.getValue());
        endRead.setReadMtrCnt(esMsg.readMtrCnt_E.getValue());
        // END 2018/09/26 K.Kojima [QC#28388,MOD]

        NSXC003001MtrCntTwoPntEst mtrCntTwoPntEst = new NSXC003001MtrCntTwoPntEst();
        // START 2018/02/22 U.Kim [QC#24202, MOD]
        // BigDecimal rollOverExchCnt = mtrCntTwoPntEst.getRollOverExchCnt(glblCmpyCd, starRead, endRead);
        // return endRead.getReadMtrCnt().subtract(starRead.getReadMtrCnt()).add(rollOverExchCnt);
        // START 2018/09/26 K.Kojima [QC#28388,MOD]
        // BigDecimal rollOverExchCnt = mtrCntTwoPntEst.getRollOverExchCnt(glblCmpyCd, befRead, endRead);
        BigDecimal rollOverExchCnt = BigDecimal.ZERO;
        if (rollOverExchReadCnt != null && rollOverExchReadCnt.compareTo(BigDecimal.ZERO) > 0) {
            rollOverExchCnt = mtrCntTwoPntEst.getRollOverExchCnt(glblCmpyCd, befRead, endRead);
        }
        // END 2018/09/26 K.Kojima [QC#28388,MOD]
        return endRead.getReadMtrCnt().subtract(befRead.getReadMtrCnt()).add(rollOverExchCnt);
        // END 2018/02/22 U.Kim [QC#24202, MOD]
    }

    // START 2018/09/26 K.Kojima [QC#28388,MOD]
    // private BigDecimal getAvgMtrCnt(String glblCmpyCd, Map<String, Object> rsltMap) {
    private BigDecimal getAvgMtrCnt(String glblCmpyCd, NSAL0150_ESMsg esMsg, BigDecimal rollOverExchReadCnt) {
    // END 2018/09/26 K.Kojima [QC#28388,MOD]
        List<SvcPhysMtrReadInfoBean> mtrReadList = new ArrayList<SvcPhysMtrReadInfoBean>();

        SvcPhysMtrReadInfoBean starRead = new SvcPhysMtrReadInfoBean();
        // START 2018/09/26 K.Kojima [QC#28388,MOD]
        // starRead.setSvcPhysMtrReadPk((BigDecimal) rsltMap.get("START_SVC_PHYS_MTR_READ_PK"));
        // starRead.setSvcPhysMtrPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
        // starRead.setMtrReadDt((String) rsltMap.get("START_MTR_READ_DT"));
        // starRead.setReadMtrCnt((BigDecimal) rsltMap.get("START_READ_MTR_CNT"));
        starRead.setSvcPhysMtrReadPk(esMsg.startSvcPhysMtrReadPk_E.getValue());
        starRead.setSvcPhysMtrPk(esMsg.svcPhysMtrPk_E.getValue());
        starRead.setMtrReadDt(esMsg.startMtrReadDt_E.getValue());
        starRead.setReadMtrCnt(esMsg.startReadMtrCnt_E.getValue());
        // END 2018/09/26 K.Kojima [QC#28388,MOD]
        mtrReadList.add(starRead);

        SvcPhysMtrReadInfoBean endRead = new SvcPhysMtrReadInfoBean();
        // START 2018/09/26 K.Kojima [QC#28388,MOD]
        // endRead.setSvcPhysMtrReadPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_PK"));
        // endRead.setSvcPhysMtrPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
        // endRead.setMtrReadDt((String) rsltMap.get("MTR_READ_DT"));
        // endRead.setReadMtrCnt((BigDecimal) rsltMap.get("READ_MTR_CNT"));
        endRead.setSvcPhysMtrReadPk(esMsg.svcPhysMtrReadPk_E.getValue());
        endRead.setSvcPhysMtrPk(esMsg.svcPhysMtrPk_E.getValue());
        endRead.setMtrReadDt(esMsg.mtrReadDt_E.getValue());
        endRead.setReadMtrCnt(esMsg.readMtrCnt_E.getValue());
        // END 2018/09/26 K.Kojima [QC#28388,MOD]
        mtrReadList.add(endRead);

        NSXC003001MtrCntTwoPntEst mtrCntTwoPntEst = new NSXC003001MtrCntTwoPntEst();
        // START 2018/09/26 K.Kojima [QC#28388,MOD]
        // BigDecimal avgDlyCopyVol = mtrCntTwoPntEst.calcAvgDlyCopyVol(glblCmpyCd, mtrReadList);
        BigDecimal avgDlyCopyVol = null;
        if (rollOverExchReadCnt != null && rollOverExchReadCnt.compareTo(BigDecimal.ZERO) > 0) {
            avgDlyCopyVol = mtrCntTwoPntEst.calcAvgDlyCopyVol(glblCmpyCd, mtrReadList, true);
        } else {
            avgDlyCopyVol = mtrCntTwoPntEst.calcAvgDlyCopyVol(glblCmpyCd, mtrReadList, false);
        }
        // END 2018/09/26 K.Kojima [QC#28388,MOD]
        if (!ZYPCommonFunc.hasValue(avgDlyCopyVol)) {
            return null;
        }
        BigDecimal setDays = ZYPCodeDataUtil.getNumConstValue(NSAL0150Constant.MTR_HIST_AVG_DAYS, glblCmpyCd);
        if (setDays == null) {
            setDays = NSAL0150Constant.DEF_MTR_HIST_AVG_DAYS;
        }
        return avgDlyCopyVol.multiply(setDays).setScale(0, RoundingMode.HALF_UP);
    }
    // add end 2017/09/07 QC#15134

    // START 2018/05/10 K.Kojima [QC#24817,ADD]
    public void createNewInputMeterList(NSAL0150CMsg cMsg, String glblCmpyCd, DS_CONTR_DTLTMsg contrDtlInfo, boolean billableReadsFlg, List<BigDecimal> dsContrDtlPkList) {
        NSAL0150Query query = NSAL0150Query.getInstance();
        List<String> targetMtrLbCdList = new ArrayList<String>();
        if (billableReadsFlg) {
            targetMtrLbCdList = query.getBilliableMeterNonMtrFmla(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), dsContrDtlPkList);
        }
        // START 2018/07/24 K.Kojima [QC#26791,MOD]
        // S21SsmEZDResult rslt = query.getSvcPhysMtrReadTmpl(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), NSAL0150Constant.DEF_DS_MTR_READ_TP_CD, targetMtrLbCdList);
        S21SsmEZDResult rslt = query.getSvcPhysMtrReadTmpl(glblCmpyCd, cMsg.svcMachMstrPk.getValue(), NSAL0150Constant.DEF_DS_MTR_READ_TP_CD, targetMtrLbCdList, billableReadsFlg);
        // END 2018/07/24 K.Kojima [QC#26791,MOD]
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).svcPhysMtrPk_B, (BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mdlMtrId_B, (String) rsltMap.get("MDL_MTR_ID"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrLbCd_B, (String) rsltMap.get("MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrLbDescTxt_B, (String) rsltMap.get("MTR_LB_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).dsMtrReadTpCd_B, (String) rsltMap.get("DS_MTR_READ_TP_CD"));
                createDsMtrReadTpPulldown(glblCmpyCd, cMsg.B.no(i));
                // START 2018/05/25 M.Naito [QC#15410, MOD]
//                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrReadDt_B, query.getDefaultMeterReadDate(glblCmpyCd, contrDtlInfo, NSAL0150CommonLogic.getBizIdSlsDt()));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrReadDt_B, query.getDefaultMeterReadDate(glblCmpyCd, dsContrDtlPkList, NSAL0150CommonLogic.getBizIdSlsDt()));
                // END 2018/05/25 M.Naito [QC#15410, MOD]
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).prevReadMtrCnt_B, (BigDecimal) rsltMap.get("PREV_MTR_CNT"));
                cMsg.B.no(i).readMtrCnt_B.clear();
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrEntryCmntTxt_B, cMsg.cpoOrdNum);
            }
            cMsg.B.setValidCount(rsltCnt);
        }
    }
    // END 2018/05/10 K.Kojima [QC#24817,ADD]

    // START 2018/06/11 U.Kim [QC#22480, ADD]
    private String getSplyContrMsg (String prntVndNm, String telNum) {
        if (prntVndNm == null) {
            prntVndNm = "";
        }
        if (telNum == null) {
            telNum = "";
        }
        return NSAL0150CommonLogic.getRtnMsg(NSAL0150Constant.NSZM1337I, new String[] {prntVndNm, telNum});
    }
    // END 2018/06/11 U.Kim [QC#22480, ADD]

    // add start 2018/07/09 QC#26923
    private boolean existSplyReadExclCust(BigDecimal svcMachMstrPk) {
        if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            return false;
        }
        BigDecimal count = NSAL0150Query.getInstance().getSplyReadExclCustCnt(getGlobalCompanyCode(), ZYPDateUtil.getSalesDate(), svcMachMstrPk);
        if (BigDecimal.ZERO.compareTo(count) == 0) {
            return false;
        }
        return true;
    }
    // add end 2018/07/09 QC#26923

    // START 2024/03/08 K.Watanabe [QC#63539, DEL]
//    // START 2018/10/15 K.Kitachi [QC#28652, ADD]
//    private boolean isExclLineBizTp(NSAL0150CMsg cMsg) {
//        if (!ZYPCommonFunc.hasValue(cMsg.svcByLineBizTpCd) && !ZYPCommonFunc.hasValue(cMsg.sldByLineBizTpCd)) {
//            return false;
//        }
//        String lineBizTpCd = cMsg.sldByLineBizTpCd.getValue();
//        if (!ZYPCommonFunc.hasValue(lineBizTpCd)) {
//            lineBizTpCd = cMsg.svcByLineBizTpCd.getValue();
//        }
//        String svcLineBizCd = NSAL0150Query.getInstance().convLineBizTpToSvcLineBiz(getGlobalCompanyCode(), lineBizTpCd);
//        List<String> exclLineBizTpList = new ArrayList<String>();
//        exclLineBizTpList.add(LINE_BIZ_TP.LFS);
//        exclLineBizTpList.add(LINE_BIZ_TP.PPS);
//        if (exclLineBizTpList.contains(svcLineBizCd)) {
//            return true;
//        }
//        return false;
//    }
//    // END 2018/10/15 K.Kitachi [QC#28652, ADD]
    // END 2024/03/08 K.Watanabe [QC#63539, DEL]

    // START 2018/11/06 S.Kitamura [QC#28868,ADD]
    private void outputInitLog(NSAL0150CMsg cMsg) {
        StringBuffer sb = new StringBuffer();
        sb.append("NSAL0150 Init Condition : ");
        if (ZYPCommonFunc.hasValue(cMsg.svcMachMstrPk)) {
            sb.append(" Install Base#[").append(cMsg.svcMachMstrPk.getValue()).append("]");
        }
        S21InfoLogOutput.println(sb.toString());
    }
    // END 2018/11/06 S.Kitamura [QC#28868,ADD]
    // Add Start 2019/03/27 QC#30791
    private void setSplyOrdContract(NSAL0150CMsg cMsg, List<Map<String, Object>> splyOrdDsContrDtlKeyList) {
        if (splyOrdDsContrDtlKeyList.size() == 0) {
            cMsg.dsContrPk_SP.clear();
            cMsg.dsContrDtlPk_SP.clear();
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg, ZYPConstant.FLG_OFF_N);
            return;
        }

        // Sort T&C(Cap)
        String glblCmpyCd = getGlobalCompanyCode();
        List<Map<String, Object>> dsContrDtlKeyList;
        if (splyOrdDsContrDtlKeyList.size() > 1){
            List<Map<String, Object>> capDsContrDtlKeyList = new ArrayList<Map<String,Object>>();
            List<Map<String, Object>> nonCapDsContrDtlKeyList = new ArrayList<Map<String,Object>>();
            for (Map<String, Object> dsContrDtlKey : splyOrdDsContrDtlKeyList) {
                BigDecimal dsContrPk = (BigDecimal) dsContrDtlKey.get("DS_CONTR_PK");
                BigDecimal dsContrDtlPk = (BigDecimal) dsContrDtlKey.get("DS_CONTR_DTL_PK");
                String dsContrCatgCd = (String) dsContrDtlKey.get("DS_CONTR_CATG_CD");
                if (NSAL0150CommonLogic.hasCapSvcTermCond(glblCmpyCd, dsContrPk, dsContrDtlPk, dsContrCatgCd)){
                    capDsContrDtlKeyList.add(dsContrDtlKey);
                } else {
                    nonCapDsContrDtlKeyList.add(dsContrDtlKey);
                }
            }
            dsContrDtlKeyList = capDsContrDtlKeyList;
            dsContrDtlKeyList.addAll(nonCapDsContrDtlKeyList);
        } else {
            dsContrDtlKeyList = splyOrdDsContrDtlKeyList;
        }

        BigDecimal dsContrPk = (BigDecimal) dsContrDtlKeyList.get(0).get("DS_CONTR_PK");
        BigDecimal dsContrDtlPk = (BigDecimal) dsContrDtlKeyList.get(0).get("DS_CONTR_DTL_PK");
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrPk_SP, dsContrPk);
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrDtlPk_SP, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);

        // Sub Contract Supply Incl Check
        String splyInclFlg = NSAL0150Query.getInstance().getSplyInclFlg(glblCmpyCd, dsContrDtlPk);
        if (ZYPConstant.FLG_ON_Y.equals(splyInclFlg)) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg, ZYPConstant.FLG_OFF_N);
        }
    }
    // Add End 2019/03/27 QC#30791

    // START 2024/03/08 K.Watanabe [QC#63539, ADD]
    /**
     * getLineBizTp
     * @param cMsg Business Msg
     * @return String
     */
    private String getLineBizTp(NSAL0150CMsg cMsg) {
        String lineBizTpCd = "";
        if (!ZYPCommonFunc.hasValue(cMsg.svcByLineBizTpCd) && !ZYPCommonFunc.hasValue(cMsg.sldByLineBizTpCd)) {
            return lineBizTpCd;
        }
        lineBizTpCd = cMsg.sldByLineBizTpCd.getValue();
        if (!ZYPCommonFunc.hasValue(lineBizTpCd)) {
            lineBizTpCd = cMsg.svcByLineBizTpCd.getValue();
        }
        lineBizTpCd = NSAL0150Query.getInstance().convLineBizTpToSvcLineBiz(getGlobalCompanyCode(), lineBizTpCd);
        return lineBizTpCd;
    }
    // END 2024/03/08 K.Watanabe [QC#63539, ADD]
}
