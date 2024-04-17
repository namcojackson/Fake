/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1320.common;

import static business.blap.NSAL1320.constant.NSAL1320Constant.BIZ_ID;
import static business.blap.NSAL1320.constant.NSAL1320Constant.DEL_DTL;
import static business.blap.NSAL1320.constant.NSAL1320Constant.DEL_SHELL;
import static business.blap.NSAL1320.constant.NSAL1320Constant.MAN_CONTR_OVRD_CMNT_TXT_LENGTH;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0064E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0138E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0347E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0627E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0630E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0632E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0634E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0636E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0698E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0716E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0742E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0755E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0781E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0782E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0789W;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0792E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NSZM0698E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NWAL2370_SHELL_MODE_CD;
import static business.blap.NSAL1320.constant.NSAL1320Constant.NZZM0000E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.XX_FLG_HARD;
import static business.blap.NSAL1320.constant.NSAL1320Constant.XX_FLG_PARENT;
import static business.blap.NSAL1320.constant.NSAL1320Constant.XX_FLG_TIER;
import static business.blap.NSAL1320.constant.NSAL1320Constant.ZZM9000E;
import static business.blap.NSAL1320.constant.NSAL1320Constant.ZZZM9006E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL1320.NSAL1320CMsg;
import business.blap.NSAL1320.NSAL1320Query;
import business.blap.NSAL1320.NSAL1320SMsg;
import business.blap.NSAL1320.NSAL1320_ACMsg;
import business.blap.NSAL1320.NSAL1320_ASMsg;
import business.blap.NSAL1320.NSAL1320_BCMsg;
import business.blap.NSAL1320.NSAL1320_CCMsg;
import business.blap.NSAL1320.NSAL1320_DCMsg;
import business.blap.NSAL1320.NSAL1320_LCMsg;
import business.blap.NSAL1320.constant.NSAL1320Constant;
import business.blap.NSAL1320.constant.NSAL1320Constant.XX_PAGE;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CCYTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_CR_CARD_POTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_MDLTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.db.PRC_LIST_TPTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_PGM_MDSE_MAPTMsg;
import business.db.SVC_PGM_MDSE_MAPTMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_TransactionRuleListPMsg;
import business.parts.NMZC610001_xxMsgIdListPMsg;
import business.parts.NSZC115001PMsg;
import business.parts.NSZC115001_svcConfigRefListPMsg;
import business.parts.NSZC115001_svcDtlListPMsg;
import business.parts.NSZC115001_svcPrcListPMsg;
import business.parts.NSZC115001_svcUsgPrcListPMsg;
import business.parts.NSZC115001_svcUsgPrcListPMsgArray;
import business.parts.NWZC155001PMsg;
import business.parts.NWZC188001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC115001.NSZC115001;
import com.canon.cusa.s21.api.NSZ.NSZC115001.constant.NSZC115001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC155001.NWZC155001;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001GetBrCd;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001GetBrCdBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NSAL1320CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 * 2017/06/07   Fujitsu         M.Yamada        Update          QC#18432(merge)
 * 2017/06/08   Hitachi         A.Kohinata      Update          QC#18853
 * 2017/06/19   Hitachi         A.Kohinata      Update          QC#19036
 * 2017/06/20   Hitachi         K.Kojima        Update          QC#19053
 * 2017/07/05   Hitachi         Y.Takeno        Update          QC#19479
 * 2017/07/06   Hitachi         Y.Takeno        Update          QC#19772
 * 2017/07/24   Hitachi         Y.Takeno        Update          QC#20055
 * 2017/08/18   Hitachi         Y.Takeno        Update          QC#20651
 * 2017/08/21   Hitachi         Y.Takeno        Update          QC#20670
 * 2017/08/29   Hitachi         Y.Takeno        Update          QC#20665
 * 2017/08/31   Hitachi         Y.Takeno        Update          QC#20773
 * 2017/09/20   Fujitsu         S.Fujita        Update          QC#18534
 * 2017/09/26   Hitachi         Y.Takeno        Update          QC#21154
 * 2017/10/11   Hitachi         Y.Takeno        Update          QC#20059
 * 2017/10/18   Fujitsu         R.Nakamura      Update          QC#21860
 * 2017/10/19   Hitachi         Y.Takeno        Update          QC#21656
 * 2017/10/24   Hitachi         K.Kojima        Update          QC#21810
 * 2017/10/24   Hitachi         Y.Takeno        Update          QC#21556
 * 2018/03/12   Fujitsu         K.Ishizuka      Update          QC#24090
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#20162
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#21919
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#10374
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 * 2018/05/09   Fujitsu         M.Ohno          Update          QC#25030
 * 2018/06/06   Hitachi         K.Kim           Update          QC#26500
 * 2018/07/02   Hitachi         T.Tomita        Update          QC#26738
 * 2018/07/18   Hitachi         K.Kitachi       Update          QC#26589
 * 2018/08/03   Fujitsu         W.Honda         Update          QC#26325
 * 2018/08/24   Fujitsu         K.Ishizuka      Update          QC#25105
 * 2018/08/28   Fujitsu         K.Ishizuka      Update          QC#27968
 * 2018/11/15   Hitachi         K.Kim           Update          QC#28638
 * 2018/12/12   Fujitsu         M.Yamada        Update          QC#29248
 * 2019/01/09   Hitachi         S.Kitamura      Update          QC#26928
 * 2019/05/23   Fujitsu         W.Honda         Update          QC#50157
 * 2019/06/21   Fujitsu         W.Honda         Update          QC#50842
 * 2019/11/29   Hitachi         K.Kitachi       Update          QC#53682
 * 2019/11/29   CITS            T.Wada          Update          QC#55922
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 * 2024/03/12   CITS            M.Kuroi         Update          QC#63638
 *</pre>
 */
public class NSAL1320CommonLogic {

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param slsDt         slsDt
     * @param bizMsg        NSAL1320CMsg
     * </pre>
     */
    public static void createPulldownList(String glblCmpyCd, String slsDt, NSAL1320CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList("PRC_SVC_CONTR_TP", bizMsg.prcSvcContrTpCd_L, bizMsg.prcSvcContrTpDescTxt_L);
        ZYPCodeDataUtil.createPulldownList("PRC_SVC_PLN_TP", bizMsg.prcSvcPlnTpCd_L, bizMsg.prcSvcPlnTpDescTxt_L);

        createBillWithEquipPulldown(bizMsg);
        createContractIndicatorPulldown(bizMsg); // DS_CONTR_CATG

        createBllgCyclePulldown(glblCmpyCd, slsDt, bizMsg);

        ZYPCodeDataUtil.createPulldownList("MTR_READ_METH", bizMsg.mtrReadMethCd_L, bizMsg.mtrReadMethDescTxt_L);
    }

    private static void createContractIndicatorPulldown(NSAL1320CMsg bizMsg) {
        S21SsmEZDResult rsltSvc = NSAL1320Query.getInstance().getContractIndicatorList();
        if (rsltSvc.isCodeNotFound()) {
            return;
        }
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltSvc.getResultObject();
        int i = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrCatgCd_L.no(i), (String) rsltMap.get("DS_CONTR_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrCatgDescTxt_L.no(i), (String) rsltMap.get("DS_CONTR_CATG_DESC_TXT"));
            i++;
        }
    }

    /**
     * <pre>
     * createBillByTpPulldown
     * @param aBizMsg   NSAL1320_ACMsg
     * @param bizMsg    NSAL1320CMsg
     * </pre>
     */
    public static void createBillByTpPulldown(NSAL1320_ACMsg aBizMsg, NSAL1320CMsg bizMsg) {
        S21SsmEZDResult rslt = NSAL1320Query.getInstance().getBillByTpList(bizMsg);
        if (rslt.isCodeNotFound()) {
            //
            bizMsg.A.no(0).billByTpCd.setErrorInfo(1, NSAM0636E, new String[] {"Billed By" });
            bizMsg.setMessageInfo(NSAM0636E, new String[] {"Billed By" });
            return;
        }

        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
        int i = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            ZYPEZDItemValueSetter.setValue(aBizMsg.billByTpCd_L.no(i), (String) rsltMap.get("BILL_BY_TP_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.billByTpDescTxt_L.no(i), (String) rsltMap.get("BILL_BY_TP_DESC_TXT"));

            i++;
        }
    }

    private static void createBllgCyclePulldown(String glblCmpyCd, String slsDt, NSAL1320CMsg bizMsg) {
        S21SsmEZDResult rsltSvc = NSAL1320Query.getInstance().getBllgCycleList(glblCmpyCd, slsDt, bizMsg);
        if (rsltSvc.isCodeNotFound()) {
            return;
        }
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltSvc.getResultObject();
        int i = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            NSAL1320_LCMsg lBizMsg = bizMsg.L.no(i);
            ZYPEZDItemValueSetter.setValue(lBizMsg.bllgCycleAot_L, (BigDecimal) rsltMap.get("BLLG_CYCLE_MTH_AOT"));

            ZYPEZDItemValueSetter.setValue(bizMsg.bllgCycleCd_L.no(i), (String) rsltMap.get("BLLG_CYCLE_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgCycleDescTxt_L.no(i), (String) rsltMap.get("BLLG_CYCLE_DESC_TXT"));

            i++;
        }
        bizMsg.L.setValidCount(i);
    }

    private static void createBillWithEquipPulldown(NSAL1320CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.billWithEquipFlg_L.no(0), ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.billWithEquipFlg_L.no(1), ZYPConstant.FLG_ON_Y);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxFlgNm_L.no(0), ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxFlgNm_L.no(1), ZYPConstant.FLG_ON_Y);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NSAL1320CMsg
     * </pre>
     */
    public static void getInitData(String glblCmpyCd, NSAL1320CMsg bizMsg) {
        bizMsg.xxPageCd_L.no(0).setValue(XX_PAGE.PAGE_SHELL.getCode());
        bizMsg.xxPageNm_L.no(0).setValue(XX_PAGE.PAGE_SHELL.getName());

        bizMsg.A.clear();
        if (!ZYPCommonFunc.hasValue(bizMsg.xxScrItem50Txt)) {
            bizMsg.A.setValidCount(0);
            return;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.refCpoOrdNum, bizMsg.xxScrItem50Txt);
        // Add Start 2018/07/02 QC#26738
        ZYPEZDItemValueSetter.setValue(bizMsg.ordBookFlg, ZYPConstant.FLG_OFF_N);
        // Add End 2018/07/02 QC#26738

        S21SsmEZDResult rsltCpo = NSAL1320Query.getInstance().getInitDataFromCpo(glblCmpyCd, bizMsg);
        S21SsmEZDResult rsltSvc = NSAL1320Query.getInstance().getInitDataFromShellContr(glblCmpyCd, bizMsg);

        bizMsg.A.setValidCount(0);
        bizMsg.B.setValidCount(0);
        if (rsltCpo.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltCpo.getResultObject();
            Map<String, Object> rsltMap = rsltList.get(0);
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_P, (String) rsltMap.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum_P, (String) rsltMap.get("SELL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxGenlFldAreaTxt_P, (String) rsltMap.get("SOLD_TO_CUST_LOC_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd_P, (String) rsltMap.get("SOLD_TO_CUST_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt, (String) rsltMap.get("PRC_BASE_DT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, (String) rsltMap.get("DS_ORD_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, (String) rsltMap.get("DS_ORD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.serNum, (String) rsltMap.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_H, (String) rsltMap.get("MDSE_CD"));
            // Add Start 2018/07/02 QC#26738
            String ordBookFlg = (String) rsltMap.get("ORD_BOOK_FLG");
            if (!hasValue(ordBookFlg)) {
                ordBookFlg = ZYPConstant.FLG_OFF_N;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.ordBookFlg, ordBookFlg);
            // Add End 2018/07/02 QC#26738
            // Add Start 2024/03/12 QC#63638
            ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdTs, (String) rsltMap.get("CPO_ORD_TS"));
            // Add End 2024/03/12 QC#63638
        }
        if (rsltSvc.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltSvc.getResultObject();
            setSvcRsltToBizMsg(bizMsg, rsltList, glblCmpyCd);
            setSvcPrcInfoToBizMsg(glblCmpyCd, bizMsg);
            setSvcPrcAmountToBizMsg(glblCmpyCd, bizMsg);
        } else {
            if (rsltCpo.isCodeNormal()) {
                List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltCpo.getResultObject();
                setCpoRsltToBizMsg(glblCmpyCd, bizMsg, rsltList);
            } else {
                bizMsg.xxScrItem50Txt.setErrorInfo(1, NZZM0000E);
                bizMsg.setMessageInfo(NZZM0000E);
            }
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1320CommonLogic.createBillByTpPulldown(bizMsg.A.no(i), bizMsg);
        }

    }

    private static void setSvcPrcAmountToBizMsg(String glblCmpyCd, NSAL1320CMsg bizMsg) {
        S21SsmEZDResult rsltAmount = NSAL1320Query.getInstance().getAmount(glblCmpyCd, bizMsg);

        if (rsltAmount.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltAmount.getResultObject();
            for (Map<String, Object> rsltMap : rsltList) {
                BigDecimal dsContrDtlPk = (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK");

                for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                    NSAL1320_BCMsg bBizMsg = bizMsg.B.no(i);
                    if (dsContrDtlPk.equals(bBizMsg.dsContrDtlPk_B.getValue())) {
                        // Mod Start 2017/10/18 QC#21860
                        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
                        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
                        // ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_LN,
                        // (BigDecimal) rsltMap.get("TOT_AMT_LN"));
                        // ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_SV,
                        // (BigDecimal) rsltMap.get("TOT_AMT_SV"));
                        // ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_EQ,
                        // (BigDecimal) rsltMap.get("TOT_AMT_EQ"));
                        // ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_AD,
                        // (BigDecimal) rsltMap.get("TOT_AMT_AD"));
                        // 2018/04/16 QC#10374 Mod Start
                        // ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_LN,
                        // round((BigDecimal)
                        // rsltMap.get("TOT_AMT_LN"), scale));
                        // ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_SV,
                        // round((BigDecimal)
                        // rsltMap.get("TOT_AMT_SV"), scale));
                        // ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_EQ,
                        // round((BigDecimal)
                        // rsltMap.get("TOT_AMT_EQ"), scale));
                        // ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_AD,
                        // round((BigDecimal)
                        // rsltMap.get("TOT_AMT_AD"), scale));
                        // 2018/05/07 QC#22482 Mod Start
                        // ZYPEZDItemValueSetter.setValue(bBizMsg.xxFreqCycleCnt,
                        // (BigDecimal) rsltMap.get("MULTIPLY"));
                        // 2018/08/28 S21_NA#27968 Mod Start
                        // if (ZYPCommonFunc.hasValue((BigDecimal)
                        // rsltMap.get("MULTIPLY")) && ((BigDecimal)
                        // rsltMap.get("MULTIPLY")).scale() == 0) {
                        // ZYPEZDItemValueSetter.setValue(bBizMsg.xxFreqCycleCnt,
                        // (BigDecimal) rsltMap.get("MULTIPLY"));
                        if (ZYPCommonFunc.hasValue((BigDecimal) rsltMap.get("MULTIPLY"))) {
                            ZYPEZDItemValueSetter.setValue(bBizMsg.xxFreqCycleCnt, ((BigDecimal) rsltMap.get("MULTIPLY")).setScale(0, BigDecimal.ROUND_UP));
                            // 2018/08/28 S21_NA#27968 Mod End
                        } else {
                            bBizMsg.xxFreqCycleCnt.clear();
                        }
                        // 2018/05/07 QC#22482 Mod End
                        ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_TT, round((BigDecimal) rsltMap.get("DEAL_AMT_TT"), scale));
                        ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_EQ, round((BigDecimal) rsltMap.get("DEAL_AMT_EQ"), scale));
                        ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_RT, round((BigDecimal) rsltMap.get("DEAL_AMT_RT"), scale));// 2018/08/03
                        // QC#26325
                        // Add
                        ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_AC, round((BigDecimal) rsltMap.get("DEAL_AMT_AC"), scale));
                        ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_AD, round((BigDecimal) rsltMap.get("DEAL_AMT_AD"), scale));
                        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_TT, round((BigDecimal) rsltMap.get("TERM_AMT_TT"), scale));
                        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_EQ, round((BigDecimal) rsltMap.get("TERM_AMT_EQ"), scale));
                        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_RT, round((BigDecimal) rsltMap.get("TERM_AMT_RT"), scale));// 2018/08/03
                        // QC#26325
                        // Add
                        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_AC, round((BigDecimal) rsltMap.get("TERM_AMT_AC"), scale));
                        ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_AD, round((BigDecimal) rsltMap.get("TERM_AMT_AD"), scale));
                        // 2018/04/16 QC#10374 Mod End
                        // Mod End 2017/10/18 QC#21860
                        break;
                    }
                }
            }
        }
    }

    private static void setSvcPrcInfoToBizMsg(String glblCmpyCd, NSAL1320CMsg bizMsg) {
        S21SsmEZDResult rsltCfg = NSAL1320Query.getInstance().getSvcConfigRef(glblCmpyCd, bizMsg);

        if (rsltCfg.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltCfg.getResultObject();
            setSvcCfgRefRsltToBizMsg(bizMsg, rsltList);
        } else {
            bizMsg.C.setValidCount(0);
        }
    }

    private static void setSvcCfgRefRsltToBizMsg(NSAL1320CMsg bizMsg, List<Map<String, Object>> rsltList) {
        int i = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            NSAL1320_CCMsg cBizMsg = bizMsg.C.no(i);

            ZYPEZDItemValueSetter.setValue(cBizMsg.shellLineNum_C, (BigDecimal) rsltMap.get("SHELL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.dsOrdPosnNum_C, (String) rsltMap.get("DS_ORD_POSN_NUM"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.dsContrDtlPk_C, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.dsContrPk_C, (BigDecimal) rsltMap.get("DS_CONTR_PK"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.cpoOrdNum_C, (String) rsltMap.get("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.cpoDtlLineNum_C, (String) rsltMap.get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.cpoDtlLineSubNum_C, (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.svcConfigMstrPk_C, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.custIssPoNum_C, (String) rsltMap.get("CUST_PO_NUM"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.custIssPoDt_C, (String) rsltMap.get("PO_DT"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.mtrReadMethCd_C, (String) rsltMap.get("MTR_READ_METH_CD"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.svcPrcCatgCd_C, (String) rsltMap.get("SVC_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.billWithEquipInvdFlg_C, (String) rsltMap.get("BILL_WITH_EQUIP_INVD_FLG"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.crRebilCd_C, (String) rsltMap.get("CR_REBIL_CD"));

            i++;
        }
        bizMsg.C.setValidCount(i);
    }

    private static void setCpoRsltToBizMsg(String glblCmpyCd, NSAL1320CMsg bizMsg, List<Map<String, Object>> rsltList) {
        int ixA = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(ixA);

            setCpoRsltToABizMsg(glblCmpyCd, bizMsg, rsltMap, aBizMsg);
            ixA++;
            break;
        }
        bizMsg.A.setValidCount(ixA);
        bizMsg.B.setValidCount(0);
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param bizMsg NSAL1320CMsg
     * @param rsltMap rsltMap
     * @param aBizMsg NSAL1320_ACMsg
     */
    public static void setCpoRsltToABizMsg(//
            String glblCmpyCd, NSAL1320CMsg bizMsg, Map<String, Object> rsltMap, NSAL1320_ACMsg aBizMsg) {
        ZYPEZDItemValueSetter.setValue(aBizMsg.shellLineNum, BigDecimal.ONE);

        // START 2017/09/26 [QC#21154, ADD]
        ZYPEZDItemValueSetter.setValue(aBizMsg.dsAcctNm, (String) rsltMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.dsAcctNum, (String) rsltMap.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.xxGenlFldAreaTxt_BI, (String) rsltMap.get("SOLD_TO_CUST_LOC_ADDR"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.soldToCustLocCd, (String) rsltMap.get("SOLD_TO_CUST_LOC_CD"));
        // END 2017/09/26 [QC#21154, ADD]

        ZYPEZDItemValueSetter.setValue(aBizMsg.billToCustAcctCd, (String) rsltMap.get("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.billToCustAcctNm, (String) rsltMap.get("BILL_TO_CUST_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.billToCustLocCd, (String) rsltMap.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.billToCustLocAddr, (String) rsltMap.get("BILL_TO_CUST_LOC_ADDR"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.termMthAot, (BigDecimal) rsltMap.get("TERM_MTH_NUM"));

        ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt, (String) rsltMap.get("PRC_BASE_DT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, (String) rsltMap.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, (String) rsltMap.get("DS_ORD_TP_CD"));

        ZYPEZDItemValueSetter.setValue(bizMsg.billByTpCd_P, aBizMsg.billByTpCd);

        if (isRentalOrder(glblCmpyCd, bizMsg.refCpoOrdNum.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rntlOrdFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.rntlOrdFlg, ZYPConstant.FLG_OFF_N);
        }

        // 2018/05/07 QC#22482 Add Start
        ZYPEZDItemValueSetter.setValue(aBizMsg.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
        // 2018/05/07 QC#22482 Add End
    }

    private static void setSvcRsltToBizMsg(NSAL1320CMsg bizMsg, List<Map<String, Object>> rsltList, String glblCmpyCd) {
        int ixA = 0;
        int ixB = 0;

        BigDecimal cpoSvcLineNumPre = BigDecimal.ZERO;
        List<Integer> ixList = new ArrayList<Integer>(bizMsg.A.length());
        String dsPoReqFlg = ZYPConstant.FLG_OFF_N;

        // 2018/04/16 QC#10374 Add Start
        if (isRentalOrder(glblCmpyCd, bizMsg.refCpoOrdNum.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rntlOrdFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.rntlOrdFlg, ZYPConstant.FLG_OFF_N);
        }
        // 2018/04/16 QC#10374 Add End

        NSAL1320_ACMsg aBizMsg = bizMsg.A.no(ixA);
        for (Map<String, Object> rsltMap : rsltList) {
            NSAL1320_BCMsg bBizMsg = bizMsg.B.no(ixB);

            if (cpoSvcLineNumPre.compareTo((BigDecimal) rsltMap.get("SHELL_LINE_NUM")) != 0) {
                cpoSvcLineNumPre = (BigDecimal) rsltMap.get("SHELL_LINE_NUM");
                // setTotAmt(bizMsg, ixList, sumAmt);
                ixList.clear();
                // sumAmt = BigDecimal.ZERO;
                aBizMsg = bizMsg.A.no(ixA);

                // mod start 2017/06/19 QC#19036
                setSvcRsltToABizMsg(rsltMap, aBizMsg, bizMsg, glblCmpyCd);
                // mod end 2017/06/19 QC#19036
                dsPoReqFlg = getDefaultDsPoReqFlg(glblCmpyCd, bizMsg, aBizMsg);

                ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime, (String) rsltMap.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone, (String) rsltMap.get("EZUPTIMEZONE"));
                ZYPEZDItemValueSetter.setValue(aBizMsg.ezUpTime_A, (String) rsltMap.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(aBizMsg.ezUpTimeZone_A, (String) rsltMap.get("EZUPTIMEZONE"));

                ixA++;
            }

            ixList.add(ixB);
            if (ZYPCommonFunc.hasValue((BigDecimal) rsltMap.get("DS_CONTR_PK")) && ZYPCommonFunc.hasValue((BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"))) {
                ZYPEZDItemValueSetter.setValue(bBizMsg.dsContrPk_B, (BigDecimal) rsltMap.get("DS_CONTR_PK"));
                ZYPEZDItemValueSetter.setValue(bBizMsg.dsContrDtlPk_B, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
                ZYPEZDItemValueSetter.setValue(bBizMsg.shellLineNum_B, (BigDecimal) rsltMap.get("SHELL_LINE_NUM"));
            } else {
                continue;
            }

            setSvcRsltToBBizMsg(rsltMap, bBizMsg, dsPoReqFlg);
            // 2018/04/16 QC#10374 Add Start
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxModeCd_B, NWAL2370_SHELL_MODE_CD);
            // 2018/04/16 QC#10374 Add End
            // 2018/04/18 QC#21919 Add Start
            ZYPEZDItemValueSetter.setValue(bBizMsg.svcContrBrDescTxt, getBrDescTxt(glblCmpyCd, rsltMap));
            // 2018/04/18 QC#21919 Add End

            if (ZYPCommonFunc.hasValue((String) rsltMap.get("PRC_BASE_DT"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt, (String) rsltMap.get("PRC_BASE_DT"));
            }
            if (ZYPCommonFunc.hasValue((String) rsltMap.get("DS_ORD_CATG_CD"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, (String) rsltMap.get("DS_ORD_CATG_CD"));
            }
            if (ZYPCommonFunc.hasValue((String) rsltMap.get("DS_ORD_TP_CD"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, (String) rsltMap.get("DS_ORD_TP_CD"));
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.ordHdrStsCd, (String) rsltMap.get("ORD_HDR_STS_CD"));

            // Add Start 2018/07/02 QC#26738
            String ordBookFlg = (String) rsltMap.get("ORD_BOOK_FLG");
            if (!hasValue(ordBookFlg)) {
                ordBookFlg = ZYPConstant.FLG_OFF_N;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.ordBookFlg, ordBookFlg);
            // End Start 2018/07/02 QC#26738
            ixB++;
        }
        bizMsg.A.setValidCount(ixA);
        bizMsg.B.setValidCount(ixB);
    }

    private static String getDefaultDsPoReqFlg(String glblCmpyCd, NSAL1320CMsg bizMsg, NSAL1320_ACMsg aBizMsg) {
        String dsPoReqFlg = ZYPConstant.FLG_OFF_N;
        NMZC610001PMsg pMsg = new NMZC610001PMsg();

        // 2018/05/07 QC#22482 Add Start
        if (!ZYPCommonFunc.hasValue(aBizMsg.soldToCustLocCd)) {
            return dsPoReqFlg;
        }
        if (!ZYPCommonFunc.hasValue(aBizMsg.dsAcctNum)) {
            return dsPoReqFlg;
        }
        // 2018/05/07 QC#22482 Add End

        if (callCustInfoGetApiForTransactionMode(glblCmpyCd, bizMsg, aBizMsg, pMsg)) {
            return dsPoReqFlg;
        }
        for (int i = 0; i < pMsg.TransactionRuleList.getValidCount(); i++) {
            NMZC610001_TransactionRuleListPMsg trPMsg = pMsg.TransactionRuleList.no(i);
            if (ZYPCommonFunc.hasValue(trPMsg.dsPoReqFlg)) {
                return trPMsg.dsPoReqFlg.getValue();
            }
        }
        return dsPoReqFlg;
    }

    /**
     * @param rsltMap Map<String, Object>
     * @param bBizMsg NSAL1320_BCMsg
     * @param dsPoReqFlg dsPoReqFlg
     */
    private static void setSvcRsltToBBizMsg(Map<String, Object> rsltMap, NSAL1320_BCMsg bBizMsg, String dsPoReqFlg) {
        ZYPEZDItemValueSetter.setValue(bBizMsg.dsOrdPosnNum, (String) rsltMap.get("DS_ORD_POSN_NUM"));
        // 2018/04/16 QC#10374 Add Start
        ZYPEZDItemValueSetter.setValue(bBizMsg.dsOrdPosnNum_BX, (String) rsltMap.get("DS_ORD_POSN_NUM"));
        // 2018/04/16 QC#10374 Add End
        ZYPEZDItemValueSetter.setValue(bBizMsg.mdlId, (BigDecimal) rsltMap.get("MDL_ID"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.t_MdlNm, (String) rsltMap.get("T_MDL_NM"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.dplyLineNum, (String) rsltMap.get("DPLY_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.cpoDtlLineNum, (String) rsltMap.get("CPO_DTL_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.cpoDtlLineSubNum, (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.mdseCd, (String) rsltMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.xxGenlFldAreaTxt_SH, (String) rsltMap.get("SHIP_TO_SRCH_TXT"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.mtrReadMethCd, (String) rsltMap.get("MTR_READ_METH_CD"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.custIssPoNum, (String) rsltMap.get("CUST_PO_NUM"));
        // 2018/04/16 QC#20162 Mod Start
        // ZYPEZDItemValueSetter.setValue(bBizMsg.custIssPoDt,
        // (String) rsltMap.get("PO_DT"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.custIssPoDt, (String) rsltMap.get("CUST_ISS_PO_DT"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.dsPoExprDt, (String) rsltMap.get("PO_DT"));
        // 2018/04/16 QC#20162 Mod End
        // 2018/04/16 QC#10374 Add Start
        ZYPEZDItemValueSetter.setValue(bBizMsg.baseBllgCycleDescTxt, (String) rsltMap.get("BASE_BLLG_CYCLE_DESC_TXT"));
        // 2018/04/16 QC#10374 Add End
        ZYPEZDItemValueSetter.setValue(bBizMsg.dsPoReqFlg, dsPoReqFlg);
        ZYPEZDItemValueSetter.setValue(bBizMsg.crRebilCd, (String) rsltMap.get("CR_REBIL_CD"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.contrAvalFlg, (String) rsltMap.get("CONTR_CRAT_FLG"));
        ZYPEZDItemValueSetter.setValue(bBizMsg.shpgStsCd, (String) rsltMap.get("SHPG_STS_CD"));
        // 2024/03/12 QC#63638 Add Start
        ZYPEZDItemValueSetter.setValue(bBizMsg.shipFlg, (String) rsltMap.get("SHIP_FLG"));
        // 2024/03/12 QC#63638 Add End
    }

    /**
     * @param rsltMap Map<String, Object>
     * @param aBizMsg NSAL1320_ACMsg
     */
    // mod start 2017/06/19 QC#19036
    private static void setSvcRsltToABizMsg(Map<String, Object> rsltMap, NSAL1320_ACMsg aBizMsg, NSAL1320CMsg bizMsg, String glblCmpyCd) {
        // mod end 2017/06/19 QC#19036
        ZYPEZDItemValueSetter.setValue(aBizMsg.dsContrPk_A, (BigDecimal) rsltMap.get("DS_CONTR_PK"));
        // 2018/04/16 QC#21919 Add Start
        ZYPEZDItemValueSetter.setValue(aBizMsg.dsContrNum, (String) rsltMap.get("DS_CONTR_NUM"));
        // 2018/04/16 QC#21919 Add End
        ZYPEZDItemValueSetter.setValue(aBizMsg.shellLineNum, (BigDecimal) rsltMap.get("SHELL_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.svcPgmMdseCd, (String) rsltMap.get("SVC_PGM_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.mdseDescShortTxt, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcSvcContrTpCd, (String) rsltMap.get("PRC_SVC_CONTR_TP_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcSvcPlnTpCd, (String) rsltMap.get("PRC_SVC_PLN_TP_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.billWithEquipFlg, (String) rsltMap.get("BILL_WITH_EQUIP_FLG"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.fromPerMthNum, (BigDecimal) rsltMap.get("FROM_PER_MTH_NUM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.toPerMthNum, (BigDecimal) rsltMap.get("TO_PER_MTH_NUM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.termMthAot, (BigDecimal) rsltMap.get("TERM_MTH_AOT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.fixTermInMthAot, (BigDecimal) rsltMap.get("FIX_TERM_IN_MTH_AOT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.maxUplftPct, (BigDecimal) rsltMap.get("MAX_UPLFT_PCT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.dsContrCatgCd, (String) rsltMap.get("DS_CONTR_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.baseBllgCycleCd, (String) rsltMap.get("BASE_BLLG_CYCLE_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.usgBllgCycleCd, (String) rsltMap.get("USG_BLLG_CYCLE_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.billByTpCd, (String) rsltMap.get("BILL_BY_TP_CD"));

        ZYPEZDItemValueSetter.setValue(aBizMsg.dsAcctNm, (String) rsltMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.dsAcctNum, (String) rsltMap.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.xxGenlFldAreaTxt_BI, (String) rsltMap.get("BILL_TO_SRCH_TXT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.soldToCustLocCd, (String) rsltMap.get("SOLD_TO_CUST_LOC_CD"));

        ZYPEZDItemValueSetter.setValue(aBizMsg.billToCustAcctCd, (String) rsltMap.get("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.billToCustAcctNm, (String) rsltMap.get("BILL_TO_CUST_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.billToCustLocAddr, (String) rsltMap.get("BILL_TO_CUST_LOC_ADDR"));

        // START 2017/10/19 [QC#21656, MOD]
        // ZYPEZDItemValueSetter.setValue(aBizMsg.contrAvalFlg_A,
        // (String) rsltMap.get("CONTR_AVAL_FLAG"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.contrAvalFlg_A, (String) rsltMap.get("CONTR_AVAL_FLG"));
        // END 2017/10/19 [QC#21656, MOD]

        ZYPEZDItemValueSetter.setValue(aBizMsg.manContrOvrdFlg, (String) rsltMap.get("MAN_CONTR_OVRD_FLG"));
        // 2018/05/07 QC#22482 Mod Start
        // ZYPEZDItemValueSetter.setValue(aBizMsg.manContrOvrdRsnCd,
        // (String) rsltMap.get("SVC_MEMO_RSN_CD"));
        // ZYPEZDItemValueSetter.setValue(aBizMsg.manContrOvrdCmntTxt,
        // (String) rsltMap.get("SVC_CMNT_TXT"));
        // ZYPEZDItemValueSetter.setValue(aBizMsg.manContrOvrdRsnNm,
        // (String) rsltMap.get("SVC_MEMO_RSN_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.svcMemoRsnCd, (String) rsltMap.get("SVC_MEMO_RSN_CD"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.svcCmntTxt, (String) rsltMap.get("SVC_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.svcMemoRsnDescTxt, (String) rsltMap.get("SVC_MEMO_RSN_DESC_TXT"));
        // 2018/05/07 QC#22482 Mod End

        ZYPEZDItemValueSetter.setValue(aBizMsg.applyEquipBillToFlg, (String) rsltMap.get("APPLY_EQUIP_BILL_TO_FLG"));

        // START 2018/07/18 K.Kitachi [QC#26589, ADD]
        ZYPEZDItemValueSetter.setValue(aBizMsg.invSeptBaseUsgFlg, (String) rsltMap.get("INV_SEPT_BASE_USG_FLG"));
        // END 2018/07/18 K.Kitachi [QC#26589, ADD]

        // mod start 2017/06/19 QC#19036
        String addContrFlg = ZYPConstant.FLG_OFF_N;
        if (ZYPCommonFunc.hasValue((String) rsltMap.get("ADD_CONTR_FLG"))) {
            addContrFlg = (String) rsltMap.get("ADD_CONTR_FLG");
        } else {
            Integer cnt = getDsContrDtlOtherCpoCount(glblCmpyCd, bizMsg.refCpoOrdNum.getValue(), (BigDecimal) rsltMap.get("DS_CONTR_PK"));
            if (cnt > 0) {
                addContrFlg = ZYPConstant.FLG_ON_Y;
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(addContrFlg)) {
            // START 2017/08/29 [QC#20665, ADD]
            // ZYPEZDItemValueSetter.setValue(aBizMsg.dsContrPk_AD,
            // (BigDecimal) rsltMap.get("DS_CONTR_PK"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.dsContrPk_AD, (BigDecimal) rsltMap.get("DS_CONTR_PK_AD"));
            // END 2017/08/29 [QC#20665, ADD]
            ZYPEZDItemValueSetter.setValue(aBizMsg.dsContrDtlPk_AD, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.dsContrNum_AD, (String) rsltMap.get("DS_CONTR_NUM"));
        }
        // mod end 2017/06/19 QC#19036

        ZYPEZDItemValueSetter.setValue(aBizMsg.cpoSvcAgmtVerNum, (String) rsltMap.get("CPO_SVC_AGMT_VER_NUM"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.addAsryFlg, (String) rsltMap.get("ADD_ASRY_FLG"));

        ZYPEZDItemValueSetter.setValue(aBizMsg.xxExstFlg_PR, (String) rsltMap.get("PRICE_XX_EXST_FLG"));

        ZYPEZDItemValueSetter.setValue(aBizMsg.xxSelFlg_CI, ZYPConstant.FLG_OFF_N);

        // Mod Start 2017/10/18 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
        // ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotAmt_S,
        // (BigDecimal) rsltMap.get("TOT_BASE_PRC_DEAL_AMT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotAmt_S, round((BigDecimal) rsltMap.get("TOT_BASE_PRC_DEAL_AMT"), scale));
        // Mod End 2017/10/18 QC#21860
    }

    private static BigDecimal getBllgCycle(NSAL1320CMsg bizMsg, String bllgCycleCd) {
        for (int i = 0; i < bizMsg.bllgCycleCd_L.length(); i++) {
            if (bllgCycleCd.equals(bizMsg.bllgCycleCd_L.no(i).getValue())) {
                return bizMsg.L.no(i).bllgCycleAot_L.getValue();
            }
        }
        return BigDecimal.ONE;
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NSAL1320CMsg
     * @return  UnRegistredShell DB selected result. Map<String, Object>
     * </pre>
     */
    public static Map<String, Object> getUnRegistredShell(String glblCmpyCd, NSAL1320CMsg bizMsg) {
        S21SsmEZDResult rsltCpo = NSAL1320Query.getInstance().getInitDataFromCpo(glblCmpyCd, bizMsg);
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltCpo.getResultObject();

        for (Map<String, Object> rsltMap : rsltList) {
            String dsOrdPosnNum = (String) rsltMap.get("DS_ORD_POSN_NUM");
            if (isDsOrdPosnNumExist(glblCmpyCd, dsOrdPosnNum, bizMsg)) {
                continue;
            }
            return rsltMap;
        }
        return null;
    }

    private static boolean isDsOrdPosnNumExist(String glblCmpyCd, String dsOrdPosnNum, NSAL1320CMsg bizMsg) {

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NSAL1320_BCMsg bBizMsg = bizMsg.B.no(i);
            if (dsOrdPosnNum.equals(bBizMsg.dsOrdPosnNum.getValue())) {
                return true;
            }
            if (isDsOrdPosnNumExistInCpoSvc(glblCmpyCd, dsOrdPosnNum, bizMsg.refCpoOrdNum.getValue())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDsOrdPosnNumExistInCpoSvc(String glblCmpyCd, String dsOrdPosnNum, String cpoOrdNum) {
        S21SsmEZDResult rslt = NSAL1320Query.getInstance().existShellContr(glblCmpyCd, dsOrdPosnNum, cpoOrdNum);
        return rslt.isCodeNormal();
    }

    private static boolean isExistsShellContract(String glblCmpyCd, String cpoOrdNum) {
        S21SsmEZDResult ssmResult = NSAL1320Query.getInstance().existShellContr(glblCmpyCd, null, cpoOrdNum);
        return ssmResult.isCodeNormal();
    }

    /**
     * <pre>
     * @param bizMsg        NSAL1320CMsg
     * @param selectRows    selectRows
     * </pre>
     */
    public static void delMaintenanceShell(NSAL1320CMsg bizMsg, List<Integer> selectRows) {
        List<Integer> delRows = new ArrayList<Integer>();
        List<BigDecimal> cpoSvcLineNumList = new ArrayList<BigDecimal>();
        for (int row : selectRows) {
            cpoSvcLineNumList.add(bizMsg.A.no(row).shellLineNum.getValue());
        }
        boolean hasError = false;
        for (BigDecimal cpoSvcLineNum : cpoSvcLineNumList) {
            for (int ixA = 0; ixA < bizMsg.B.getValidCount(); ixA++) {
                NSAL1320_BCMsg bBizMsg = bizMsg.B.no(ixA);
                if (ZYPCommonFunc.hasValue(bBizMsg.shellLineNum_B) //
                        && cpoSvcLineNum.compareTo(bBizMsg.shellLineNum_B.getValue()) == 0) {
                    bBizMsg.xxChkBox_B.setErrorInfo(1, NSAM0634E);
                    hasError = true;
                }
            }
        }
        if (hasError) {
            return;
        }
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (cpoSvcLineNumList.contains(bizMsg.A.no(i).shellLineNum.getValue())) {
                delRows.add(i);
            }
        }
        saveShellDeleteRows(bizMsg, delRows, DEL_SHELL);
        ZYPTableUtil.deleteRows(bizMsg.A, delRows);
    }

    /**
     * <pre>
     * @param bizMsg        NSAL1320CMsg
     * @param selectRows    select rows list
     * @param glblCmpyCd    glblCmpyCd
     * </pre>
     */
    public static void delDetail(String glblCmpyCd, NSAL1320CMsg bizMsg, List<Integer> selectRows) {
        int ixSelRow = bizMsg.xxCellIdx.getValueInt();
        BigDecimal cpoSvcLineNum = bizMsg.A.no(ixSelRow).shellLineNum.getValue();
        int dtlCnt = 0;
        List<BigDecimal> mdlList = new ArrayList<BigDecimal>();
        List<Integer> mdlCntList = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).shellLineNum_B) //
                    && cpoSvcLineNum.compareTo(bizMsg.B.no(i).shellLineNum_B.getValue()) == 0) {
                dtlCnt++;

                BigDecimal mdlId = bizMsg.B.no(i).mdlId.getValue();
                if (mdlList.contains(mdlId)) {
                    int ix = mdlList.indexOf(mdlId);
                    mdlCntList.set(ix, mdlCntList.get(ix) + 1);
                } else {
                    mdlList.add(mdlId);
                    mdlCntList.add(1);
                }
            }
        }

        List<Integer> delRows = new ArrayList<Integer>();
        BigDecimal totAmt = bizMsg.A.no(ixSelRow).xxTotAmt_S.getValue();
        if (!ZYPCommonFunc.hasValue(totAmt)) {
            totAmt = BigDecimal.ZERO;
        }
        for (int i : selectRows) {
            NSAL1320_BCMsg bBizMsg = bizMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(bBizMsg.shellLineNum_B)) {
                continue;
            }
            if (cpoSvcLineNum.compareTo(bBizMsg.shellLineNum_B.getValue()) == 0 //
                    && ZYPConstant.CHKBOX_ON_Y.equals(bBizMsg.xxChkBox_B.getValue())) {
                // 2018/04/16 QC#10374 Mod Start
                // if (ZYPCommonFunc.hasValue(bBizMsg.xxTotAmt_LN)) {
                // totAmt =
                // totAmt.subtract(bBizMsg.xxTotAmt_LN.getValue());
                if (ZYPCommonFunc.hasValue(bBizMsg.xxTermAmt_TT)) {
                    totAmt = totAmt.subtract(bBizMsg.xxTermAmt_TT.getValue());
                    // 2018/04/16 QC#10374 Mod End
                }
                delRows.add(i);
            }
        }
        if (delRows.isEmpty()) {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                NSAL1320_BCMsg aBizMsg = bizMsg.B.no(i);
                if (!ZYPCommonFunc.hasValue(aBizMsg.shellLineNum_B)) {
                    continue;
                }
                if (cpoSvcLineNum.compareTo(aBizMsg.shellLineNum_B.getValue()) == 0) {
                    aBizMsg.xxChkBox_B.setErrorInfo(1, NSAM0630E, new String[] {"Detail" });
                }
            }
            return;
        }
        deleteCpoSvcConfigRefFromBizMsg(bizMsg, delRows);

        // START 2017/10/24 K.Kojima [QC#21993,MOD]
        // saveDtlDeleteRows(bizMsg, delRows, DEL_DTL);
        saveDtlDeleteRows(bizMsg, delRows, DEL_DTL, glblCmpyCd);
        // END 2017/10/24 K.Kojima [QC#21993,MOD]
        ZYPTableUtil.deleteRows(bizMsg.B, delRows);
        // Mod Start 2017/10/18 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
        // ZYPEZDItemValueSetter.setValue(bizMsg.A.no(ixSelRow).xxTotAmt_S,
        // totAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(ixSelRow).xxTotAmt_S, round(totAmt, scale));
        // Mod End 2017/10/18 QC#21860
        bizMsg.A.no(ixSelRow).xxChkBox_BH.clear();
    }

    /**
     * @param bizMsg
     * @param delRows
     */
    private static void deleteCpoSvcConfigRefFromBizMsg(NSAL1320CMsg bizMsg, List<Integer> delRows) {
        List<Integer> delRowsC = new ArrayList<Integer>();
        List<BigDecimal> cPkList = new ArrayList<BigDecimal>();
        for (int ix : delRows) {
            NSAL1320_BCMsg bBizMsg = bizMsg.B.no(ix);

            BigDecimal cpoSvcLineNum = bBizMsg.shellLineNum_B.getValue();
            String dsOrdPosnNum = bBizMsg.dsOrdPosnNum.getValue();
            String cpoDtlLineNum = bBizMsg.cpoDtlLineNum.getValue();
            String cpoDtlLineSubNum = bBizMsg.cpoDtlLineSubNum.getValue();

            for (int ixC = 0; ixC < bizMsg.C.getValidCount(); ixC++) {
                NSAL1320_CCMsg cBizMsg = bizMsg.C.no(ixC);

                if (cpoSvcLineNum.compareTo(cBizMsg.shellLineNum_C.getValue()) == 0 //
                        && dsOrdPosnNum.equals(cBizMsg.dsOrdPosnNum_C.getValue()) //
                        && cpoDtlLineNum.equals(cBizMsg.cpoDtlLineNum_C.getValue()) //
                        && cpoDtlLineSubNum.equals(cBizMsg.cpoDtlLineSubNum_C.getValue())) {
                    if (!delRowsC.contains(ixC)) {
                        delRowsC.add(ixC);
                        cPkList.add(cBizMsg.dsContrDtlPk_C.getValue());
                    }
                }
            }

        }
        ZYPTableUtil.deleteRows(bizMsg.C, delRowsC);
    }

    // START 2017/10/24 K.Kojima [QC#21810,MOD]
    // private static void saveDtlDeleteRows(NSAL1320CMsg bizMsg,
    // List<Integer> delRows, String delFunc) {
    private static void saveDtlDeleteRows(NSAL1320CMsg bizMsg, List<Integer> delRows, String delFunc, String glblCmpyCd) {
        // END 2017/10/24 K.Kojima [QC#21810,MOD]
        int ixD = bizMsg.D.getValidCount();
        for (int ixB : delRows) {
            NSAL1320_BCMsg bBizMsg = bizMsg.B.no(ixB++);
            if (!ZYPCommonFunc.hasValue(bBizMsg.dsContrPk_B) || !ZYPCommonFunc.hasValue(bBizMsg.dsContrDtlPk_B)) {
                continue;
            }

            NSAL1320_DCMsg dBizMsg = bizMsg.D.no(ixD++);

            EZDMsg.copy(bBizMsg, "B", dBizMsg, "D");
            if (DEL_SHELL.equals(delFunc) //
                    && !ZYPCommonFunc.hasValue(dBizMsg.dsContrPk_D)) {
                dBizMsg.dsContrDtlPk_D.clear();
            }
            // Mod Start 2017/10/18 QC#21860
            // START 2017/10/24 K.Kojima [QC#21993,MOD]
            // GLBL_CMPYTMsg glblCmpyTMsg =
            // getGlblCmpy(bizMsg.glblCmpyCd.getValue());
            // int scale =
            // getDealCcyDigit(bizMsg.glblCmpyCd.getValue(),
            // glblCmpyTMsg.stdCcyCd.getValue());
            GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
            int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
            // END 2017/10/24 K.Kojima [QC#21993,MOD]
            // ZYPEZDItemValueSetter.setValue(dBizMsg.xxTotAmt_D1,
            // bBizMsg.xxTotAmt_LN);
            // ZYPEZDItemValueSetter.setValue(dBizMsg.xxTotAmt_D2,
            // bBizMsg.xxTotAmt_SV);
            // ZYPEZDItemValueSetter.setValue(dBizMsg.xxTotAmt_D3,
            // bBizMsg.xxTotAmt_EQ);
            // ZYPEZDItemValueSetter.setValue(dBizMsg.xxTotAmt_D4,
            // bBizMsg.xxTotAmt_AD);
            // 2018/04/16 QC#10374 Mod Start
            // ZYPEZDItemValueSetter.setValue(dBizMsg.xxTotAmt_D1,
            // round(bBizMsg.xxTotAmt_LN.getValue(), scale));
            // ZYPEZDItemValueSetter.setValue(dBizMsg.xxTotAmt_D2,
            // round(bBizMsg.xxTotAmt_SV.getValue(), scale));
            // ZYPEZDItemValueSetter.setValue(dBizMsg.xxTotAmt_D3,
            // round(bBizMsg.xxTotAmt_EQ.getValue(), scale));
            // ZYPEZDItemValueSetter.setValue(dBizMsg.xxTotAmt_D4,
            // round(bBizMsg.xxTotAmt_AD.getValue(), scale));
            ZYPEZDItemValueSetter.setValue(dBizMsg.xxTotAmt_D1, round(bBizMsg.xxTermAmt_TT.getValue(), scale));
            ZYPEZDItemValueSetter.setValue(dBizMsg.xxTotAmt_D2, round(bBizMsg.xxTermAmt_EQ.getValue(), scale));
            ZYPEZDItemValueSetter.setValue(dBizMsg.xxTotAmt_D3, round(bBizMsg.xxTermAmt_AC.getValue(), scale));
            ZYPEZDItemValueSetter.setValue(dBizMsg.xxTotAmt_D4, round(bBizMsg.xxTermAmt_AD.getValue(), scale));
            // 2018/04/16 QC#10374 Mod End
            // Mod Start 2017/10/18 QC#21860
            // START 2024/03/12 M.Kuroi [QC#63638,ADD]
            ZYPEZDItemValueSetter.setValue(dBizMsg.cpoDtlLineNum_D, bBizMsg.cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(dBizMsg.cpoDtlLineSubNum_D, bBizMsg.cpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(dBizMsg.shpgStsCd_D, bBizMsg.shpgStsCd);
            // END 2024/03/12 M.Kuroi [QC#63638,ADD]
        }
        bizMsg.D.setValidCount(ixD);
    }

    private static void saveShellDeleteRows(NSAL1320CMsg bizMsg, List<Integer> delRows, String delFunc) {
        int ixD = bizMsg.D.getValidCount();
        for (int ixA : delRows) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(ixA++);
            if (!ZYPCommonFunc.hasValue(aBizMsg.dsContrPk_A)) {
                continue;
            }
            NSAL1320_DCMsg dBizMsg = bizMsg.D.no(ixD++);

            EZDMsg.copy(aBizMsg, "A", dBizMsg, "D");
            // START 2017/08/29 [QC#20655, ADD]
            ZYPEZDItemValueSetter.setValue(dBizMsg.shellLineNum_D, aBizMsg.shellLineNum);
            // END 2017/08/29 [QC#20655, ADD]
        }
        bizMsg.D.setValidCount(ixD);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bBizMsg       NSAL1320_BCMsg
     * @param bizMsg        NSAL1320CMsg
     * </pre>
     */
    public static void deriveCustIssPoNum(String glblCmpyCd, NSAL1320_BCMsg bBizMsg, NSAL1320CMsg bizMsg) {
        NSAL1320_ACMsg aBizMsg = getABizMsg(bizMsg, bBizMsg.shellLineNum_B.getValue());
        if (!ZYPCommonFunc.hasValue(aBizMsg.soldToCustLocCd) //
                || !ZYPCommonFunc.hasValue(aBizMsg.dsAcctNum)) {
            return;
        }
        NMZC610001PMsg pMsg = new NMZC610001PMsg();

        if (callCustInfoGetApiForTransactionMode(glblCmpyCd, bizMsg, aBizMsg, pMsg)) {
            return;
        }
        for (int i = 0; i < pMsg.TransactionRuleList.getValidCount(); i++) {
            NMZC610001_TransactionRuleListPMsg trPMsg = pMsg.TransactionRuleList.no(i);
            if (!ZYPCommonFunc.hasValue(trPMsg.dsBlktPoNum)) {
                continue;
            }
            String slsDt = ZYPDateUtil.getSalesDate();
            if (ZYPCommonFunc.hasValue(trPMsg.dsPoExprDt) //
                    && trPMsg.dsPoExprDt.getValue().compareTo(slsDt) < 0) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(bBizMsg.dsPoReqFlg) //
                    && !ZYPCommonFunc.hasValue(bBizMsg.custIssPoNum)) {
                ZYPEZDItemValueSetter.setValue(bBizMsg.custIssPoNum, trPMsg.dsBlktPoNum);
            }
            // 2018/04/16 QC#20162 Add Start
            if (!ZYPCommonFunc.hasValue(bBizMsg.dsPoReqFlg) && !ZYPCommonFunc.hasValue(bBizMsg.dsPoExprDt)) {
                ZYPEZDItemValueSetter.setValue(bBizMsg.dsPoExprDt, trPMsg.dsPoExprDt);
            }
            // 2018/04/16 QC#20162 Add End
            ZYPEZDItemValueSetter.setValue(bBizMsg.dsPoReqFlg, trPMsg.dsPoReqFlg);
            break;
        }
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param bizMsg NSAL1320CMsg
     * @param aBizMsg NSAL1320_ACMsg
     * @param pMsg NMZC610001PMsg
     */
    private static boolean callCustInfoGetApiForTransactionMode(//
            String glblCmpyCd, NSAL1320CMsg bizMsg, NSAL1320_ACMsg aBizMsg, NMZC610001PMsg pMsg) {
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_TRANSACTION);
        ZYPEZDItemValueSetter.setValue(pMsg.dsTrxRuleTpCd, DS_TRX_RULE_TP.CONTRACT);
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, aBizMsg.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, aBizMsg.dsAcctNum);

        new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            NMZC610001_xxMsgIdListPMsg pErrMsg = pMsg.xxMsgIdList.no(0);
            bizMsg.setMessageInfo(//
                    pErrMsg.xxMsgId.getValue() //
                    , new String[] {//
                    pErrMsg.xxMsgPrmTxt_0.getValue() //
                            , pErrMsg.xxMsgPrmTxt_1.getValue() //
                            , pErrMsg.xxMsgPrmTxt_2.getValue() //
                            , pErrMsg.xxMsgPrmTxt_3.getValue() //
                            , pErrMsg.xxMsgPrmTxt_4.getValue() //
                    });
            return true;
        }
        return false;
    }

    private static NSAL1320_ACMsg getABizMsg(NSAL1320CMsg bizMsg, BigDecimal cpoSvcLineNum) {
        if (!ZYPCommonFunc.hasValue(cpoSvcLineNum)) {
            return new NSAL1320_ACMsg();
        }
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(aBizMsg.shellLineNum)) {
                continue;
            }
            if (cpoSvcLineNum.compareTo(aBizMsg.shellLineNum.getValue()) == 0) {
                return aBizMsg;
            }
        }
        return new NSAL1320_ACMsg();
    }

    /**
     * <pre>
     * @param aBizMsg   NSAL1320_ACMsg
     * @param bizMsg    NSAL1320CMsg
     * @return  toPerMthNum - fromPerMthNum.
     * </pre>
     */
    public static BigDecimal calcTermMthNum(NSAL1320_ACMsg aBizMsg, NSAL1320CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(aBizMsg.fromPerMthNum)) {
            return BigDecimal.ZERO;
        }
        if (!ZYPCommonFunc.hasValue(aBizMsg.toPerMthNum)) {
            return BigDecimal.ZERO;
        }
        if (aBizMsg.fromPerMthNum.getValue().compareTo(aBizMsg.toPerMthNum.getValue()) > 0) {
            aBizMsg.fromPerMthNum.setErrorInfo(1, NSAM0064E, new String[] {"Term From/To" });
            aBizMsg.toPerMthNum.setErrorInfo(1, NSAM0064E, new String[] {"Term From/To" });
            bizMsg.setMessageInfo(NSAM0064E);
            return BigDecimal.ZERO;
        }
        return aBizMsg.toPerMthNum.getValue().subtract(aBizMsg.fromPerMthNum.getValue()).add(BigDecimal.ONE);
    }

    /**
     * <pre>
     * derive mdseDescLongTxt, termMthNum
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NSAL1320CMsg
     * @param likeSearchFlg String
     * @return if error then return true.
     * </pre>
     */
    public static boolean deriveFromInputValue(String glblCmpyCd, NSAL1320CMsg bizMsg, String likeSearchFlg) {
        boolean hasErr = false;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(i);

            // 2018/05/07 QC#22482 Add Start
            if (isManOvrd(aBizMsg)) {
                continue;
            }
            // 2018/05/07 QC#22482 Add End

            if (ZYPCommonFunc.hasValue(aBizMsg.svcPgmMdseCd)) {
                hasErr = NSAL1320CommonLogic.deriveFromShellItemCd(//
                        glblCmpyCd, false, aBizMsg.svcPgmMdseCd, aBizMsg.mdseDescShortTxt, bizMsg, likeSearchFlg);

                // START 2017/09/20 S.Fujita [QC#18534,ADD]
                if (!NSXC001001ContrValidation.checkCsaWarranty(glblCmpyCd, aBizMsg.dsContrCatgCd.getValue(), aBizMsg.svcPgmMdseCd.getValue())) {
                    aBizMsg.mdseDescShortTxt.setErrorInfo(1, NSAM0698E);
                    hasErr = true;
                }
                // END 2017/09/20 S.Fujita [QC#18534,ADD]
            }

            if (ZYPCommonFunc.hasValue(aBizMsg.dsAcctNum)) {
                hasErr = NSAL1320CommonLogic.deriveFromDsAcctNum(hasErr, aBizMsg.dsAcctNum, bizMsg);
            }

            if (ZYPCommonFunc.hasValue(aBizMsg.soldToCustLocCd)) {
                hasErr = NSAL1320CommonLogic.deriveFromLocationCd(hasErr, aBizMsg.soldToCustLocCd, bizMsg);
            }

            // START 2017/07/21 [QC#20055, ADD]
            if (!NSXC001001ContrValidation.checkAcctBillEligible(glblCmpyCd, ZYPDateUtil.getSalesDate(), aBizMsg.dsAcctNum.getValue(), aBizMsg.soldToCustLocCd.getValue(), ONBATCH_TYPE.ONLINE)) {
                aBizMsg.dsAcctNum.setErrorInfo(1, NSZM0698E, new String[] {"Customer", "Location" });
                aBizMsg.soldToCustLocCd.setErrorInfo(1, NSZM0698E, new String[] {"Customer", "Location" });
                hasErr = true;
            }
            // END 2017/07/21 [QC#20055, ADD]

            if (ZYPCommonFunc.hasValue(aBizMsg.dsContrNum_AD)) {
                BigDecimal dsContrPk = NSAL1320CommonLogic.searchDsContrPk(//
                        glblCmpyCd, aBizMsg.dsContrNum_AD.getValue(), aBizMsg.dsAcctNum.getValue());
                if (!ZYPCommonFunc.hasValue(dsContrPk)) {
                    aBizMsg.dsContrNum_AD.setErrorInfo(1, NSAM0627E, new String[] {"Contract#" });
                    bizMsg.setMessageInfo(NSAM0627E);
                    hasErr = true;
                }
                ZYPEZDItemValueSetter.setValue(aBizMsg.dsContrPk_AD, dsContrPk);
            }

            ZYPEZDItemValueSetter.setValue(aBizMsg.termMthAot //
                    , NSAL1320CommonLogic.calcTermMthNum(aBizMsg, bizMsg));
            if (ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                hasErr = true;
            }

            if (checkBillCycle(bizMsg, aBizMsg.termMthAot.getValue(), aBizMsg.baseBllgCycleCd.getValue())) {
                aBizMsg.fromPerMthNum.setErrorInfo(1, NSAM0138E, new String[] {"Term Month", "Base Bill Cycle" });
                aBizMsg.toPerMthNum.setErrorInfo(1, NSAM0138E, new String[] {"Term Month", "Base Bill Cycle" });
                aBizMsg.baseBllgCycleCd.setErrorInfo(1, NSAM0138E, new String[] {"Term Month", "Base Bill Cycle" });
                bizMsg.setMessageInfo(NSAM0138E, new String[] {"Term Month", "Base Bill Cycle" });
                hasErr = true;
            }
            if (ZYPCommonFunc.hasValue(aBizMsg.usgBllgCycleCd)) {
                if (checkBillCycle(bizMsg, aBizMsg.termMthAot.getValue(), aBizMsg.usgBllgCycleCd.getValue())) {
                    aBizMsg.fromPerMthNum.setErrorInfo(1, NSAM0138E, new String[] {"Term Month", "Base Bill Cycle" });
                    aBizMsg.toPerMthNum.setErrorInfo(1, NSAM0138E, new String[] {"Term Month", "Base Bill Cycle" });
                    aBizMsg.usgBllgCycleCd.setErrorInfo(1, NSAM0138E, new String[] {"Term Month", "Usage Bill Cycle" });
                    bizMsg.setMessageInfo(NSAM0138E, new String[] {"Term Month", "Base Bill Cycle" });
                    hasErr = true;
                }
            }

            // START 2018/11/15 [QC#28638, ADD]
            if (ZYPCommonFunc.hasValue(aBizMsg.fixTermInMthAot) && aBizMsg.fixTermInMthAot.getValue().compareTo(aBizMsg.termMthAot.getValue()) > 0) {
                aBizMsg.fixTermInMthAot.setErrorInfo(1, NSAM0742E);
                bizMsg.setMessageInfo(NSAM0742E);
                hasErr = true;
            }
            // END 2018/11/15 [QC#28638, ADD]

            if (ZYPCommonFunc.hasValue(aBizMsg.dsAcctNum) //
                    && ZYPCommonFunc.hasValue(aBizMsg.soldToCustLocCd)) {

                NMZC610001PMsg pMsg = new NMZC610001PMsg();
                if (callCustInfoGetApiForTransactionMode(glblCmpyCd, bizMsg, aBizMsg, pMsg)) {
                    hasErr = true;
                } else {
                    String dsPoReqFlg = ZYPConstant.FLG_OFF_N;
                    for (int ixP = 0; ixP < pMsg.TransactionRuleList.getValidCount(); ixP++) {
                        NMZC610001_TransactionRuleListPMsg trPMsg = pMsg.TransactionRuleList.no(ixP);
                        dsPoReqFlg = trPMsg.dsPoReqFlg.getValue();
                        break;
                    }
                    setDsPoReqFlg(bizMsg, aBizMsg, dsPoReqFlg);
                }

            }
        }
        return hasErr;
    }

    /**
     * <pre>
     * derive mdseDescLongTxt, termMthNum
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NSAL1320CMsg
     * @param glblMsg       NSAL1320SMsg
     * @return if error then return true.
     * </pre>
     */
    public static boolean deriveImptFromInputValue(String glblCmpyCd, NSAL1320CMsg bizMsg, NSAL1320SMsg glblMsg) {
        boolean hasErr = false;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(i);
            NSAL1320_ASMsg aGlblMsg = glblMsg.A.no(i);
            if (S21StringUtil.isEquals(aBizMsg.dsAcctNum.getValue(), aGlblMsg.dsAcctNum.getValue()) //
                    && S21StringUtil.isEquals(aBizMsg.soldToCustLocCd.getValue(), aGlblMsg.soldToCustLocCd.getValue())) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(aBizMsg.dsAcctNum)) {
                hasErr = NSAL1320CommonLogic.deriveFromDsAcctNum(hasErr, aBizMsg.dsAcctNum, bizMsg);
            }

            if (ZYPCommonFunc.hasValue(aBizMsg.soldToCustLocCd)) {
                hasErr = NSAL1320CommonLogic.deriveFromLocationCd(hasErr, aBizMsg.soldToCustLocCd, bizMsg);
            }

            if (ZYPCommonFunc.hasValue(aBizMsg.dsAcctNum) //
                    && ZYPCommonFunc.hasValue(aBizMsg.soldToCustLocCd)) {

                NMZC610001PMsg pMsg = new NMZC610001PMsg();
                if (callCustInfoGetApiForTransactionMode(glblCmpyCd, bizMsg, aBizMsg, pMsg)) {
                    hasErr = true;
                } else {
                    String dsPoReqFlg = ZYPConstant.FLG_OFF_N;
                    for (int ixP = 0; ixP < pMsg.TransactionRuleList.getValidCount(); ixP++) {
                        NMZC610001_TransactionRuleListPMsg trPMsg = pMsg.TransactionRuleList.no(ixP);
                        dsPoReqFlg = trPMsg.dsPoReqFlg.getValue();
                        break;
                    }
                    setDsPoReqFlg(bizMsg, aBizMsg, dsPoReqFlg);
                }

            }
        }
        return hasErr;
    }

    private static void setDsPoReqFlg(NSAL1320CMsg bizMsg, NSAL1320_ACMsg aBizMsg, String dsPoReqFlg) {
        BigDecimal cpoSvcLineNum = aBizMsg.shellLineNum.getValue();
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(cpoSvcLineNum)) {
                continue;
            }
            NSAL1320_BCMsg bBizMsg = bizMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(bBizMsg.shellLineNum_B)) {
                continue;
            }
            if (cpoSvcLineNum.compareTo(bBizMsg.shellLineNum_B.getValue()) != 0) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(bBizMsg.dsPoReqFlg, dsPoReqFlg);
        }
    }

    private static boolean deriveFromLocationCd(boolean hasErr, EZDCStringItem soldToCustLocCd, NSAL1320CMsg bizMsg) {
        Integer cnt = getBillToCustCount(bizMsg, soldToCustLocCd, "Location Code");
        if (cnt == null || cnt == 0) {
            soldToCustLocCd.setErrorInfo(1, NSAM0627E, new String[] {"Location Code" });
            bizMsg.setMessageInfo(NSAM0627E);
            hasErr = true;
        }

        return hasErr;
    }

    private static boolean deriveFromDsAcctNum(boolean hasErr, EZDCStringItem dsAcctNumItem, NSAL1320CMsg bizMsg) {
        Integer cnt = getAcctCount(bizMsg, dsAcctNumItem, "Customer");
        if (cnt == null || cnt == 0) {
            dsAcctNumItem.setErrorInfo(1, NSAM0627E, new String[] {"Customer" });
            bizMsg.setMessageInfo(NSAM0627E);
            hasErr = true;
        }

        return hasErr;
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param hasErr        hasErr
     * @param mdseCdItem    EZDCStringItem
     * @param mdseNmItem    EZDCStringItem
     * @param bizMsg        NSAL1320CMsg
     * @param likeSearchFlg String(Y/N)
     * @return  if error then return true.
     * </pre>
     */
    public static boolean deriveFromShellItemCd(//
            String glblCmpyCd, boolean hasErr, EZDCStringItem mdseCdItem, EZDCStringItem mdseNmItem, NSAL1320CMsg bizMsg, String likeSearchFlg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        String mdseCd = "";
        if (ZYPCommonFunc.hasValue(mdseCdItem)) {
            mdseCd = mdseCdItem.getValue();
        }
        String mdseNm = "";
        S21SsmEZDResult rslt = NSAL1320Query.getInstance().searchShellItem(glblCmpyCd, mdseCd, mdseNm, likeSearchFlg);
        if (rslt.isCodeNotFound()) {
            mdseCdItem.setErrorInfo(1, NSAM0627E, new String[] {"Service Program Code" });
            bizMsg.setMessageInfo(NSAM0627E);
            return true;
        }
        List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();

        if (rsltList.size() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return true;
        }
        mdseCd = rsltList.get(0).get("MDSE_CD");
        mdseNm = rsltList.get(0).get("MDSE_DESC_SHORT_TXT");

        ZYPEZDItemValueSetter.setValue(mdseCdItem, mdseCd);
        ZYPEZDItemValueSetter.setValue(mdseNmItem, mdseNm);

        return hasErr;
    }

    private static BigDecimal searchDsContrPk(String glblCmpyCd, String dsContrNum, String dsAcctNum) {
        S21SsmEZDResult rslt = NSAL1320Query.getInstance().searchDsContrPk(glblCmpyCd, dsContrNum, dsAcctNum);
        if (rslt.isCodeNotFound()) {
            return null;
        }
        Map<String, Object> rsltMap = (Map<String, Object>) rslt.getResultObject();

        return (BigDecimal) rsltMap.get("DS_CONTR_PK");
    }

    private static boolean checkBillCycle(NSAL1320CMsg bizMsg, BigDecimal termMthNum, String bllgCycleCd) {
        BigDecimal cycle = getBllgCycle(bizMsg, bllgCycleCd);
        BigDecimal rmin = termMthNum.remainder(cycle);
        if (BigDecimal.ZERO.compareTo(rmin) == 0) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NSAL1320CMsg
     * @return  ExclusionCtrl result : if error then return true.
     * </pre>
     */
    public static boolean checkExclusionCtrl(String glblCmpyCd, NSAL1320CMsg bizMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(i);

            if (!ZYPCommonFunc.hasValue(aBizMsg.dsContrPk_A)) {
                continue;
            }
            DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrPk, aBizMsg.dsContrPk_A);

            dsContrTMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsContrTMsg);
            if (dsContrTMsg == null) {
                bizMsg.setMessageInfo(NSAL1320Constant.NZZM0003E);
                return true;
            }

            if (!ZYPDateUtil.isSameTimeStamp(//
                    aBizMsg.ezUpTime_A.getValue(), aBizMsg.ezUpTimeZone_A.getValue() //
                    , dsContrTMsg.ezUpTime.getValue(), dsContrTMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NSAL1320Constant.NZZM0003E);
                return true;
            }
        }

        return false;
    }

    /**
     * <pre>
     * @param bizMsg    NSAL1320CMsg
     * @return  if error then return true.
     * </pre>
     */
    public static boolean checkTermSeq(NSAL1320CMsg bizMsg) {
        List<String> configList = getConfigList(bizMsg);
        Map<BigDecimal, BigDecimal> termMap = new HashMap<BigDecimal, BigDecimal>();

        // 2018/05/07 QC#22482 Add Start
        if (containsManOvrd(bizMsg)) {
            return false;
        }
        // 2018/05/07 QC#22482 Add End

        for (String configId : configList) {
            termMap.clear();
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NSAL1320_ACMsg aBizMsg = bizMsg.A.no(i);
                if (!isExistConfig(aBizMsg, bizMsg, configId)) {
                    continue;
                }
                BigDecimal termFrom = aBizMsg.fromPerMthNum.getValue();
                BigDecimal termTo = aBizMsg.toPerMthNum.getValue();
                if (termMap.containsKey(termFrom) || termMap.containsValue(termTo)) {
                    aBizMsg.fromPerMthNum.setErrorInfo(1, NSAM0632E);
                    aBizMsg.toPerMthNum.setErrorInfo(1, NSAM0632E);
                    bizMsg.setMessageInfo(NSAM0632E);
                    return true;
                }
                termMap.put(termFrom, termTo);
            }
            TreeMap<BigDecimal, BigDecimal> treeMap = new TreeMap<BigDecimal, BigDecimal>();
            treeMap.putAll(termMap);

            BigDecimal term = BigDecimal.ONE;
            //
            for (Map.Entry<BigDecimal, BigDecimal> mEnt : treeMap.entrySet()) {
                BigDecimal termFrom = mEnt.getKey();
                BigDecimal termTo = mEnt.getValue();

                if (term.compareTo(termFrom) != 0) {
                    setTermErrMsg(bizMsg, configId);
                    return true;
                }
                term = termTo.add(BigDecimal.ONE);
            }
        }
        return false;
    }

    private static boolean isExistConfig(NSAL1320_ACMsg aBizMsg, NSAL1320CMsg bizMsg, String configId) {
        BigDecimal cpoSvcLineNum = aBizMsg.shellLineNum.getValue();
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NSAL1320_BCMsg bBizMsg = bizMsg.B.no(i);
            if (cpoSvcLineNum.compareTo(bBizMsg.shellLineNum_B.getValue()) == 0 && configId.equals(bBizMsg.dsOrdPosnNum.getValue())) {
                return true;
            }
        }
        return false;
    }

    private static void setTermErrMsg(NSAL1320CMsg bizMsg, String configId) {
        boolean isError = false;
        BigDecimal cpoSvcLineNum = BigDecimal.ZERO;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(i);
            isError = true;
            cpoSvcLineNum = aBizMsg.shellLineNum.getValue();
        }
        if (isError) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NSAL1320_ACMsg aBizMsg = bizMsg.A.no(i);
                if (cpoSvcLineNum.compareTo(aBizMsg.shellLineNum.getValue()) == 0) {
                    aBizMsg.fromPerMthNum.setErrorInfo(1, NSAM0632E);
                    aBizMsg.toPerMthNum.setErrorInfo(1, NSAM0632E);
                }
            }

        }
    }

    private static List<String> getConfigList(NSAL1320CMsg bizMsg) {
        List<String> configList = new ArrayList<String>();
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            String configId = bizMsg.B.no(i).dsOrdPosnNum.getValue();
            if (!configList.contains(configId)) {
                configList.add(configId);
            }
        }
        return configList;
    }

    /**
     * @param bizMsg NSAL1320CMsg
     */
    public static void setNSAL1360ReturnValues(NSAL1320CMsg bizMsg) {
        List<Integer> selList = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_B", ZYPConstant.CHKBOX_ON_Y);

        int ixA = bizMsg.xxCellIdx.getValueInt();
        BigDecimal cpoSvcLineNum = bizMsg.A.no(ixA).shellLineNum.getValue();
        if (!ZYPCommonFunc.hasValue(cpoSvcLineNum)) {
            return;
        }
        for (int ixB : selList) {
            NSAL1320_BCMsg bBizMsg = bizMsg.B.no(ixB);
            if (!ZYPCommonFunc.hasValue(bBizMsg.shellLineNum_B)) {
                continue;
            }
            if (cpoSvcLineNum.compareTo(bBizMsg.shellLineNum_B.getValue()) != 0) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(bizMsg.mtrReadMethCd_X)) {
                ZYPEZDItemValueSetter.setValue(bBizMsg.mtrReadMethCd, bizMsg.mtrReadMethCd_X);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.custIssPoNum_X)) {
                ZYPEZDItemValueSetter.setValue(bBizMsg.custIssPoNum, bizMsg.custIssPoNum_X);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.custIssPoDt_X)) {
                ZYPEZDItemValueSetter.setValue(bBizMsg.custIssPoDt, bizMsg.custIssPoDt_X);
            }
            // 2018/04/16 QC#20162 Add Start
            if (ZYPCommonFunc.hasValue(bizMsg.dsPoExprDt_X)) {
                ZYPEZDItemValueSetter.setValue(bBizMsg.dsPoExprDt, bizMsg.dsPoExprDt_X);
            }
            // 2018/04/16 QC#20162 Add End
        }
    }

    /**
     * <pre>
     * @param bizMsg        NSAL1320CMsg
     * @param glblCmpyCd    glblCmpyCd
     * @return
     * </pre>
     */
    public static boolean isModified(NSAL1320CMsg bizMsg, String glblCmpyCd) {
        if (bizMsg.D.getValidCount() > 0) {
            return true;
        }
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(aBizMsg.dsContrPk_A)) {
                return true;
            }
            BigDecimal dsContrPk = aBizMsg.dsContrPk_A.getValue();
            DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, dsContrPk);
            tMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(tMsg);
            if (tMsg == null) {
                return true;
            }

            if (!tMsg.svcPgmMdseCd.getValue().equals(aBizMsg.svcPgmMdseCd.getValue())) {
                return true;
            }
            if (!tMsg.prcSvcContrTpCd.getValue().equals(aBizMsg.prcSvcContrTpCd.getValue())) {
                return true;
            }
            if (!tMsg.prcSvcPlnTpCd.getValue().equals(aBizMsg.prcSvcPlnTpCd.getValue())) {
                return true;
            }
            if (!tMsg.dsContrCatgCd.getValue().equals(aBizMsg.dsContrCatgCd.getValue())) {
                return true;
            }
            if (!tMsg.baseBllgCycleCd.getValue().equals(aBizMsg.baseBllgCycleCd.getValue())) {
                return true;
            }
            // START 2017/07/06 [QC#19772, MOD]
            if (!isSameValue(tMsg.mtrBllgCycleCd.getValue(), aBizMsg.usgBllgCycleCd.getValue())) {
                return true;
            }
            // END 2017/07/06 [QC#19772, MOD]
            if (!tMsg.billByTpCd.getValue().equals(aBizMsg.billByTpCd.getValue())) {
                return true;
            }
            if (!tMsg.altPayerCustCd.getValue().equals(aBizMsg.soldToCustLocCd.getValue())) {
                return true;
            }
            // START 2017/07/06 [QC#19772, MOD]
            // if
            // (!tMsg.sellToCustCd.getValue().equals(aBizMsg.dsAcctNum.getValue()))
            // {
            if (!tMsg.dsAcctNum.getValue().equals(aBizMsg.dsAcctNum.getValue())) {
                return true;
            }
            // END 2017/07/06 [QC#19772, MOD]
            // START 2017/07/06 [QC#19772, DEL]
            // if (ZYPCommonFunc.hasValue(aBizMsg.dsContrPk_AD) //
            // && ZYPCommonFunc.hasValue(tMsg.dsContrPk) //
            // &&
            // tMsg.dsContrPk.getValue().compareTo(aBizMsg.dsContrPk_AD.getValue())
            // != 0) {
            // return true;
            // }
            // if (ZYPCommonFunc.hasValue(aBizMsg.dsContrPk_AD) //
            // && !ZYPCommonFunc.hasValue(tMsg.dsContrPk)) {
            // return true;
            // }
            // if (!ZYPCommonFunc.hasValue(aBizMsg.dsContrPk_AD) //
            // && ZYPCommonFunc.hasValue(tMsg.dsContrPk)) {
            // return true;
            // }
            // END 2017/07/06 [QC#19772, DEL]
            // 2018/05/07 QC#22482 Add Start
            if (!tMsg.manContrOvrdFlg.getValue().equals(aBizMsg.manContrOvrdFlg.getValue())) {
                return true;
            }
            // 2018/05/07 QC#22482 Add End
            // START 2018/11/15 [QC#28638, ADD]
            if (!isEqualBigDecimal(tMsg.fixTermInMthAot.getValue(), aBizMsg.fixTermInMthAot.getValue())) {
                return true;
            }
            // END 2018/11/15 [QC#28638, ADD]

            // START 2017/07/06 [QC#19772, MOD]
            DS_CONTR_DTLTMsg dtlInMsg = new DS_CONTR_DTLTMsg();
            dtlInMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            dtlInMsg.setConditionValue("cpoOrdNum01", bizMsg.refCpoOrdNum.getValue());
            dtlInMsg.setConditionValue("shellLineNum01", aBizMsg.shellLineNum.getValue());
            dtlInMsg.setSQLID("201");
            DS_CONTR_DTLTMsgArray dtlTMsgArray = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(dtlInMsg);
            if (dtlTMsgArray == null || dtlTMsgArray.getValidCount() == 0) {
                return true;
            }
            // END 2017/07/06 [QC#19772, MOD]

            DS_CONTR_DTLTMsg dtlTMsg = dtlTMsgArray.no(0);
            // START 2017/07/06 [QC#19772, DEL]
            // if
            // (!dtlTMsg.addAsryFlg.getValue().equals(aBizMsg.addAsryFlg.getValue()))
            // {
            // return true;
            // }
            // END 2017/07/06 [QC#19772, DEL]
            if (!dtlTMsg.billWithEquipFlg.getValue().equals(aBizMsg.billWithEquipFlg.getValue())) {
                return true;
            }
            // 2018/05/07 QC#22482 Mod Start
            // if
            // (dtlTMsg.fromPerMthNum.getValue().compareTo(aBizMsg.fromPerMthNum.getValue())
            // != 0) {
            // return true;
            // }
            // if
            // (dtlTMsg.toPerMthNum.getValue().compareTo(aBizMsg.toPerMthNum.getValue())
            // != 0) {
            // return true;
            // }
            if (ZYPCommonFunc.hasValue(dtlTMsg.fromPerMthNum) && ZYPCommonFunc.hasValue(aBizMsg.fromPerMthNum)) {
                if (dtlTMsg.fromPerMthNum.getValue().compareTo(aBizMsg.fromPerMthNum.getValue()) != 0) {
                    return true;
                }
            }
            if (ZYPCommonFunc.hasValue(dtlTMsg.toPerMthNum) && ZYPCommonFunc.hasValue(aBizMsg.toPerMthNum)) {
                if (dtlTMsg.toPerMthNum.getValue().compareTo(aBizMsg.toPerMthNum.getValue()) != 0) {
                    return true;
                }
            }
            // 2018/05/07 QC#22482 Mod End

            // START 2017/07/06 [QC#19772, ADD]
            for (int j = 0; j < dtlTMsgArray.getValidCount(); j++) {
                dtlTMsg = dtlTMsgArray.no(j);
                String addAsryFlg = dtlTMsg.addAsryFlg.getValue();
                String addContrFlg = dtlTMsg.addContrFlg.getValue();

                if (!isSameValue(bizMsg.refCpoOrdNum.getValue(), dtlTMsg.cpoOrdNum.getValue())) {
                    continue;
                }

                if (ZYPConstant.FLG_OFF_N.equals(addAsryFlg) && ZYPConstant.FLG_OFF_N.equals(addContrFlg)) {
                    if (ZYPCommonFunc.hasValue(aBizMsg.dsContrPk_AD)) {
                        return true;
                    }

                } else {
                    if (!ZYPCommonFunc.hasValue(aBizMsg.dsContrPk_AD)) {
                        return true;
                    }

                    if (ZYPCommonFunc.hasValue(aBizMsg.dsContrPk_AD) && aBizMsg.dsContrPk_AD.getValue().compareTo(dtlTMsg.dsContrPk.getValue()) != 0) {
                        return true;
                    }
                }
            }
            // END 2017/07/06 [QC#19772, ADD]
        }

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            // START 2017/07/06 [QC#19772, MOD]
            NSAL1320_BCMsg bBizMsg = bizMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(bBizMsg.dsContrPk_B) || !ZYPCommonFunc.hasValue(bBizMsg.dsContrDtlPk_B)) {
                return true;
            }
            BigDecimal dsContrDtlPk = bBizMsg.dsContrDtlPk_B.getValue();
            DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, dsContrDtlPk);
            tMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(tMsg);
            if (tMsg == null) {
                return true;
            }

            if (!isSameValue(tMsg.mtrReadMethCd.getValue(), bBizMsg.mtrReadMethCd.getValue())) {
                return true;
            }

            // START 2019/05/23 W.Honda [QC#50157, MOD]
            // DS_CONTR_CR_CARD_POTMsg inCardPoTMsg = new
            // DS_CONTR_CR_CARD_POTMsg();
            // inCardPoTMsg.setConditionValue("glblCmpyCd01",
            // glblCmpyCd);
            // inCardPoTMsg.setConditionValue("dsContrDtlPk01",
            // bBizMsg.dsContrDtlPk_B.getValue());
            // START 2019/01/09 S.Kitamura [QC#26928, MOD]
            // inCardPoTMsg.setSQLID("201");
            // DS_CONTR_CR_CARD_POTMsgArray cardPoTMsgArray =
            // (DS_CONTR_CR_CARD_POTMsgArray)
            // EZDTBLAccessor.findByCondition(inCardPoTMsg);
            // inCardPoTMsg.setConditionValue("poDt01",
            // ZYPDateUtil.getSalesDate());
            // inCardPoTMsg.setConditionValue("poFromDt01",
            // ZYPDateUtil.getSalesDate());
            // inCardPoTMsg.setSQLID("202");
            // DS_CONTR_CR_CARD_POTMsgArray cardPoTMsgArray =
            // (DS_CONTR_CR_CARD_POTMsgArray)
            // EZDTBLAccessor.findByCondition(inCardPoTMsg);
            BigDecimal dsContrCrCardPoPk = NSAL1320Query.getInstance().getExistCrCardPO(glblCmpyCd, bBizMsg.dsContrDtlPk_B.getValue());
            // END 2019/01/09 S.Kitamura [QC#26928, MOD]
            // if (cardPoTMsgArray == null ||
            // cardPoTMsgArray.getValidCount() == 0) {
            if (!ZYPCommonFunc.hasValue(dsContrCrCardPoPk)) {
                if (ZYPCommonFunc.hasValue(bBizMsg.custIssPoDt.getValue())) {
                    return true;
                }
                if (ZYPCommonFunc.hasValue(bBizMsg.custIssPoNum.getValue())) {
                    return true;
                }
                // 2018/04/16 QC#20162 Add Start
                if (ZYPCommonFunc.hasValue(bBizMsg.dsPoExprDt.getValue())) {
                    return true;
                }
                // 2018/04/16 QC#20162 Add End

            } else {
                // DS_CONTR_CR_CARD_POTMsg cardPoTMsg =
                // cardPoTMsgArray.no(0);
                DS_CONTR_CR_CARD_POTMsg cardPoTMsg = new DS_CONTR_CR_CARD_POTMsg();
                ZYPEZDItemValueSetter.setValue(cardPoTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cardPoTMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
                cardPoTMsg = (DS_CONTR_CR_CARD_POTMsg) S21FastTBLAccessor.findByKey(cardPoTMsg);
                if (cardPoTMsg == null) {
                    return true;
                }

                // if (!isSameValue(cardPoTMsg.poDt.getValue(),
                // bBizMsg.custIssPoDt.getValue())) {
                if (!isSameValue(cardPoTMsg.custIssPoDt.getValue(), bBizMsg.custIssPoDt.getValue())) {
                    return true;
                }
                if (!isSameValue(cardPoTMsg.custPoNum.getValue(), bBizMsg.custIssPoNum.getValue())) {
                    return true;
                }
                // 2018/04/16 QC#20162 Add Start
                // if (!isSameValue(cardPoTMsg.custIssPoDt.getValue(),
                // bBizMsg.dsPoExprDt.getValue())) {
                if (!isSameValue(cardPoTMsg.poDt.getValue(), bBizMsg.dsPoExprDt.getValue())) {
                    return true;
                }
                // 2018/04/16 QC#20162 Add End
            }
            // END 2019/05/23 W.Honda [QC#50157, MOD]
            // END 2017/07/06 [QC#19772, MOD]
        }

        return false;
    }

    // START 2017/07/06 [QC#19772, MOD]
    private static boolean isSameValue(String val1, String val2) {
        if (ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {
            return true;
        }
        return val1.equals(val2);
    }

    // END 2017/07/06 [QC#19772, MOD]

    // START 2018/11/15 [QC#28638, ADD]
    private static boolean isEqualBigDecimal(BigDecimal val1, BigDecimal val2) {
        if (ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {
            return (val1.compareTo(val2) == 0);
        }
        if (ZYPCommonFunc.hasValue(val1) || ZYPCommonFunc.hasValue(val2)) {
            return false;
        }
        return true;
    }

    // END 2018/11/15 [QC#28638, ADD]

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param hasErr        hasErr
     * @param mdseCdItem    EZDCStringItem
     * @param mdseNmItem    EZDCStringItem
     * @param bizMsg        NSAL1320CMsg
     * @param likeSearchFlg String(Y/N)
     * @return  if error then return true.
     * </pre>
     */
    public static boolean deriveFromShellItemNm(//
            String glblCmpyCd, boolean hasErr, EZDCStringItem mdseCdItem, EZDCStringItem mdseNmItem, NSAL1320CMsg bizMsg, String likeSearchFlg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        String mdseCd = "";
        String mdseNm = "";
        if (ZYPCommonFunc.hasValue(mdseNmItem)) {
            mdseNm = mdseNmItem.getValue();
        }
        S21SsmEZDResult rslt = NSAL1320Query.getInstance().searchShellItem(glblCmpyCd, mdseCd, mdseNm, likeSearchFlg);
        if (rslt.isCodeNotFound()) {
            mdseNmItem.setErrorInfo(1, NSAM0627E, new String[] {"Service Program Name" });
            return true;
        }
        List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();

        if (rsltList.size() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return true;
        }
        mdseCd = rsltList.get(0).get("MDSE_CD");
        mdseNm = rsltList.get(0).get("MDSE_DESC_SHORT_TXT");

        ZYPEZDItemValueSetter.setValue(mdseCdItem, mdseCd);
        ZYPEZDItemValueSetter.setValue(mdseNmItem, mdseNm);

        return hasErr;
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param bizMsg NSAL1320CMsg
     */
    public static void deleteShellPrice(String glblCmpyCd, NSAL1320CMsg bizMsg) {
        NSZC115001PMsg pMsg = new NSZC115001PMsg();

        pMsg.xxProcMd.setValue(NSZC115001Constant.PROC_MODE_MOD);
        pMsg.glblCmpyCd.setValue(glblCmpyCd);
        pMsg.slsDt.setValue(ZYPDateUtil.getSalesDate());
        pMsg.refCpoOrdNum.setValue(bizMsg.refCpoOrdNum.getValue());

        // mod start 2017/06/08 QC#18853
        List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>();
        int svcDtlIdx = 0;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            // 2020/05/27 QC#55922 Mod Start
            // if
            // (!ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxSelFlg_CI.getValue()))
            // {
            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxSelFlg_CI.getValue()) && !isClearUsageBillingCycle(glblCmpyCd, bizMsg.A.no(i))) {
                // 2020/05/27 QC#55922 Mod End
                continue;
            }
            pMsg.svcDtlList.no(svcDtlIdx).xxRqstTpCd.setValue(NSZC115001Constant.RQST_TP_DEL);
            pMsg.svcDtlList.no(svcDtlIdx).dsContrPk.setValue(bizMsg.A.no(i).dsContrPk_A.getValue());
            // mod start 2017/06/19 QC#19036
            pMsg.svcDtlList.no(svcDtlIdx).delFlg_PI.setValue(getDelFlg(glblCmpyCd, bizMsg.refCpoOrdNum.getValue(), bizMsg.A.no(i).dsContrPk_A.getValue()));
            // mod end 2017/06/19 QC#19036
            pMsg.svcDtlList.setValidCount(++svcDtlIdx);
            if (!dsContrPkList.contains(bizMsg.A.no(i).dsContrPk_A.getValue())) {
                dsContrPkList.add(bizMsg.A.no(i).dsContrPk_A.getValue());
            }
        }
        // mod end 2017/06/08 QC#18853

        if (svcDtlIdx == 0) {
            return;
        }

        new NSZC115001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);
            bizMsg.setMessageInfo(msgList.get(0));
            return;
        }

        // add start 2017/06/08 QC#18853
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (dsContrPkList.contains(bizMsg.A.no(i).dsContrPk_A.getValue())) {
                bizMsg.A.no(i).dsContrPk_A.clear();
                bizMsg.A.no(i).dsContrDtlPk_AD.clear();
                bizMsg.A.no(i).dsContrNum_AD.clear();
            }
        }
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (dsContrPkList.contains(bizMsg.B.no(i).dsContrPk_B.getValue())) {
                bizMsg.B.no(i).dsContrPk_B.clear();
                bizMsg.B.no(i).dsContrDtlPk_B.clear();
            }
        }
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if (dsContrPkList.contains(bizMsg.C.no(i).dsContrPk_C.getValue())) {
                bizMsg.C.no(i).dsContrPk_C.clear();
                bizMsg.C.no(i).dsContrDtlPk_C.clear();
            }
        }
        List<Integer> delRowsList = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            if (dsContrPkList.contains(bizMsg.D.no(i).dsContrPk_D.getValue())) {
                delRowsList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.D, delRowsList);
        // add end 2017/06/08 QC#18853
    }

    /**
     * @param bizMsg NSAL1320CMsg
     */
    public static void resetBizMsgBeforeInit(NSAL1320CMsg bizMsg) {
        List<String> functionIds = new ArrayList<String>();
        int cnt = 0;
        for (int i = 0; i < bizMsg.P.getValidCount(); i++) {
            functionIds.add(bizMsg.P.no(i).xxFuncId_P.getValue());
            cnt++;
        }
        bizMsg.P.setValidCount(cnt);
        String cpoOrdNum = bizMsg.xxScrItem50Txt_P.getValue();
        String xxPageCd = bizMsg.xxPageCd.getValue();

        bizMsg.clear();
        bizMsg.B.setValidCount(0);
        bizMsg.D.setValidCount(0);

        int ix = 0;
        for (String functionId : functionIds) {
            bizMsg.P.no(ix++).xxFuncId_P.setValue(functionId);
        }
        bizMsg.P.setValidCount(ix);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem50Txt_P, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem50Txt, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageCd, xxPageCd);
    }

    /**
     * Get DS Account Customer Info
     * @param bizMsg NSAL1320CMsg
     * @param isCallNameFld Called Name Field
     * @param targetItem Target Item
     * @param msgParm Message Parameter
     * @param glblCmpyCd glblCmpyCd
     * @return DS Account Customer Info
     */
    public static SELL_TO_CUSTTMsg getDsAcctCustInfo(NSAL1320CMsg bizMsg, boolean isCallNameFld, EZDCStringItem targetItem, String msgParm, String glblCmpyCd) {

        SELL_TO_CUSTTMsgArray dsAcctCustTMsgArray = null;
        if (isCallNameFld) {
            dsAcctCustTMsgArray = getDsSlsAcctCustForName(bizMsg, targetItem.getValue(), glblCmpyCd);
        } else {
            dsAcctCustTMsgArray = getDsSlsAcctCustForAcct(bizMsg, targetItem.getValue(), glblCmpyCd);
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (dsAcctCustTMsgArray.getValidCount() == 0) {
            targetItem.setErrorInfo(1, NSAM0627E, new String[] {msgParm });
            return null;
        } else if (dsAcctCustTMsgArray.getValidCount() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return dsAcctCustTMsgArray.no(0);
    }

    /**
     * Get Direct Sales Account Customer For Name
     * @param bizMsg NSAL1320CMsg
     * @param custNm Customer Name
     * @return Direct Sales Account Customer
     */
    private static SELL_TO_CUSTTMsgArray getDsSlsAcctCustForName(NSAL1320CMsg bizMsg, String custNm, String glblCmpyCd) {

        final SELL_TO_CUSTTMsg condition = new SELL_TO_CUSTTMsg();
        // 2018/03/12 S21_NA#24090 Mod Start
        // condition.setSQLID("502");
        condition.setSQLID("506");
        // 2018/03/12 S21_NA#24090 Mod End
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("dsAcctNm01", custNm);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get Direct Sales Account Customer For Account
     * @param bizMsg NSAL1320CMsg
     * @param acctNum Account Number
     * @return Direct Sales Account Customer
     */
    private static SELL_TO_CUSTTMsgArray getDsSlsAcctCustForAcct(NSAL1320CMsg bizMsg, String acctNum, String glblCmpyCd) {

        final SELL_TO_CUSTTMsg condition = new SELL_TO_CUSTTMsg();
        // 2018/03/12 S21_NA#24090 Mod Start
        // condition.setSQLID("503");
        condition.setSQLID("507");
        // 2018/03/12 S21_NA#24090 Mod Start
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("sellToCustCd01", acctNum);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Common Process For Derive From Bill To
     * @param bizMsg NSAL1320CMsg
     * @param dsAcctCustTMsg SELL_TO_CUSTTMsg
     * @param glblCmpyCd glblCmpyCd
     */
    public static void cmnProcForDeriveFromBillTo(NSAL1320CMsg bizMsg, SELL_TO_CUSTTMsg dsAcctCustTMsg, String glblCmpyCd) {

        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, dsAcctCustTMsg.sellToCustCd.getValue(), glblCmpyCd);
        if (nMZC610001PMsg == null) {
            return;
        }

        setBillToInfo(bizMsg, dsAcctCustTMsg, nMZC610001PMsg);
    }

    /**
     * Call Customer Information Get API For Default Mode
     * @param bizMsg NSAL1320CMsg
     * @param dsAcctNum Direct Sales Account Number
     * @param glblCmpyCd glblCmpyCd
     * @return NMZC610001PMsg
     */
    public static NMZC610001PMsg callCustInfoGetApiForDefaultMode(NSAL1320CMsg bizMsg, String dsAcctNum, String glblCmpyCd) {
        NMZC610001PMsg custApiPMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(custApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(custApiPMsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
        ZYPEZDItemValueSetter.setValue(custApiPMsg.dsTrxRuleTpCd, getDsTrxRuleTpCd(bizMsg, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(custApiPMsg.dsAcctNum_I1, dsAcctNum);

        // call NMZC6100 Customer Information Get API
        NMZC610001 api = new NMZC610001();
        api.execute(custApiPMsg, ONBATCH_TYPE.ONLINE);

        return custApiPMsg;
    }

    /**
     * Get Direct Sales Transaction Rule Type Code
     * @param bizMsg NSAL1320CMsg
     * @return Direct Sales Transaction Rule Type Code
     */
    private static String getDsTrxRuleTpCd(NSAL1320CMsg bizMsg, String glblCmpyCd) {

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();
        // START 2019/06/21 W.Honda [QC#50842, MOD]
        // condition.setSQLID("003");
        // condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        // condition.setConditionValue("ordCatgCtxTpCd01A",
        // ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        // condition.setConditionValue("ordCatgCtxTpCd01B",
        // ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        // condition.setConditionValue("dsOrdCatgCd01",
        // bizMsg.dsOrdCatgCd.getValue());
        // condition.setConditionValue("dsOrdTpCd01",
        // bizMsg.dsOrdTpCd.getValue());
        condition.setSQLID("004");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("ordCatgCtxTpCd01", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", bizMsg.dsOrdCatgCd.getValue());
        condition.setConditionValue("dsOrdTpCd01", bizMsg.dsOrdTpCd.getValue());
        // END 2019/06/21 W.Honda [QC#50842, MOD]

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        condition = new ORD_CATG_BIZ_CTXTMsg();
        // START 2019/06/21 W.Honda [QC#50842, MOD]
        // condition.setSQLID("002");
        // condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        // condition.setConditionValue("ordCatgCtxTpCd01A",
        // ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        // condition.setConditionValue("ordCatgCtxTpCd01B",
        // ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        // condition.setConditionValue("dsOrdCatgCd01",
        // bizMsg.dsOrdCatgCd.getValue());
        condition.setSQLID("004");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("ordCatgCtxTpCd01", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", bizMsg.dsOrdCatgCd.getValue());
        condition.setConditionValue("dsOrdTpCd01", bizMsg.dsOrdTpCd.getValue());
        // END 2019/06/21 W.Honda [QC#50842, MOD]

        tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        return "";
    }

    /**
     * Set Bill To Info
     * @param bizMsg NSAL1320CMsg
     * @param dsAcctCustTMsg SELL_TO_CUSTTMsg
     * @param nMZC610001PMsg NMZC610001PMsg
     */
    private static void setBillToInfo(NSAL1320CMsg bizMsg, SELL_TO_CUSTTMsg dsAcctCustTMsg, NMZC610001PMsg nMZC610001PMsg) {

        int selectIndex = bizMsg.xxCellIdx.getValueInt();

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIndex).dsAcctNm, dsAcctCustTMsg.dsAcctNm);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIndex).dsAcctNum, dsAcctCustTMsg.sellToCustCd);

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIndex).soldToCustLocCd, nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd);
        Map<String, String> billToInfo = NSAL1320CommonLogic.getBillToInfo(bizMsg, bizMsg.A.no(selectIndex).soldToCustLocCd, "Location Code");
        if (billToInfo == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(selectIndex).xxGenlFldAreaTxt_BI, billToInfo.get("BILL_TO_ADDR"));
    }

    /**
     * Get Bill To Info
     * @param bizMsg NSAL1320CMsg
     * @param custLocNum Customer Location Number
     * @param msgParm Message Parameter
     * @return Bill To Info
     */
    public static Map<String, String> getBillToInfo(NSAL1320CMsg bizMsg, EZDCStringItem custLocNum, String msgParm) {

        S21SsmEZDResult ssmResult = NSAL1320Query.getInstance().getBillToCustInfoList(bizMsg, custLocNum.getValue());

        if (ssmResult.isCodeNotFound()) {
            custLocNum.setErrorInfo(1, NSAM0627E, new String[] {msgParm });
            return null;
        }

        List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (billToCustInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return billToCustInfoList.get(0);
    }

    /**
     * Get Bill To Count
     * @param bizMsg NSAL1320CMsg
     * @param custLocNum Customer Location Number
     * @param msgParm Message Parameter
     * @return Bill To Info
     */
    public static Integer getBillToCustCount(NSAL1320CMsg bizMsg, EZDCStringItem custLocNum, String msgParm) {

        S21SsmEZDResult ssmResult = NSAL1320Query.getInstance().getBillToCustCount(bizMsg, custLocNum.getValue());

        if (ssmResult.isCodeNotFound()) {
            custLocNum.setErrorInfo(1, NSAM0627E, new String[] {msgParm });
            return null;
        }

        Integer cnt = (Integer) ssmResult.getResultObject();
        if (cnt == null) {
            return 0;
        }

        return cnt;
    }

    private static Integer getAcctCount(NSAL1320CMsg bizMsg, EZDCStringItem dsAcctNumItem, String msgParm) {
        S21SsmEZDResult ssmResult = NSAL1320Query.getInstance().getDsAcctCustCount(bizMsg, dsAcctNumItem.getValue());

        if (ssmResult.isCodeNotFound()) {
            dsAcctNumItem.setErrorInfo(1, NSAM0627E, new String[] {msgParm });
            return null;
        }

        Integer cnt = (Integer) ssmResult.getResultObject();
        if (cnt == null) {
            return 0;
        }

        return cnt;
    }

    /**
     * <pre>
     * @param aBizMsg   NSAL1320_ACMsg
     * @param bizMsg    NSAL1320CMsg
     * </pre>
     */
    public static void setBillToInfoToCustomer(NSAL1320_ACMsg aBizMsg, NSAL1320CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(aBizMsg.billByTpCd)) {
            aBizMsg.dsAcctNum.clear();
            aBizMsg.dsAcctNm.clear();
            aBizMsg.soldToCustLocCd.clear();
            aBizMsg.xxGenlFldAreaTxt_BI.clear();
            return;
        }
        S21SsmEZDResult ssmResult = NSAL1320Query.getInstance().getMntBillAsEquipInfo(aBizMsg, bizMsg);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        List<Map<String, String>> rsList = (List<Map<String, String>>) ssmResult.getResultObject();
        if (rsList == null || rsList.size() == 0) {
            return;
        }

        for (Map<String, String> rsMap : rsList) {
            ZYPEZDItemValueSetter.setValue(aBizMsg.dsAcctNum, rsMap.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.dsAcctNm, rsMap.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.soldToCustLocCd, rsMap.get("SOLD_TO_CUST_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxGenlFldAreaTxt_BI, rsMap.get("BILL_TO_SRCH_TXT"));
            break;
        }
    }

    /**
     * @param bizMsg NSAL1320CMsg
     * @return if error then return true.
     */
    public static boolean checkPoNumAndDt(NSAL1320CMsg bizMsg) {
        boolean hasErr = false;
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {

            NSAL1320_BCMsg bBizMsg = bizMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(bBizMsg.shellLineNum_B)) {
                continue;
            }
            // 2018/05/07 QC#22482 Add Start
            if (isManOvrd(bBizMsg, bizMsg)) {
                continue;
            }
            // 2018/05/07 QC#22482 Add End
            if (ZYPConstant.FLG_ON_Y.equals(bBizMsg.dsPoReqFlg.getValue())) {
                if (!ZYPCommonFunc.hasValue(bBizMsg.custIssPoNum)) {
                    bBizMsg.custIssPoNum.setErrorInfo(1, ZZM9000E, new String[] {"PO Number" });
                    hasErr = true;
                }
                if (!ZYPCommonFunc.hasValue(bBizMsg.custIssPoDt)) {
                    bBizMsg.custIssPoDt.setErrorInfo(1, ZZM9000E, new String[] {"PO Date" });
                    hasErr = true;
                }
                // 2018/04/16 QC#20162 Add Start
                if (!ZYPCommonFunc.hasValue(bBizMsg.dsPoExprDt)) {
                    bBizMsg.dsPoExprDt.setErrorInfo(1, ZZM9000E, new String[] {"PO Expr Date" });
                    hasErr = true;
                }
                // 2018/04/16 QC#20162 Add End
            }
            // 2018/04/16 QC#20162 Mod Start
            // if (!ZYPCommonFunc.hasValue(bBizMsg.custIssPoNum) //
            // && ZYPCommonFunc.hasValue(bBizMsg.custIssPoDt)) {
            // bBizMsg.custIssPoNum.setErrorInfo(1, NSAM0638E, new
            // String[] {"PO Number", "PO Date" });
            // hasErr = true;
            // }
            // if (ZYPCommonFunc.hasValue(bBizMsg.custIssPoNum) //
            // && !ZYPCommonFunc.hasValue(bBizMsg.custIssPoDt)) {
            // bBizMsg.custIssPoDt.setErrorInfo(1, NSAM0638E, new
            // String[] {"PO Date", "PO Number" });
            // hasErr = true;
            // }
            if (ZYPCommonFunc.hasValue(bBizMsg.custIssPoNum)) {
                if (!ZYPCommonFunc.hasValue(bBizMsg.custIssPoDt)) {
                    bBizMsg.custIssPoDt.setErrorInfo(1, NSAM0716E, new String[] {"PO Date", "PO Expr Date", "PO Number" });
                    hasErr = true;
                }
                if (!ZYPCommonFunc.hasValue(bBizMsg.dsPoExprDt)) {
                    bBizMsg.dsPoExprDt.setErrorInfo(1, NSAM0716E, new String[] {"PO Date", "PO Expr Date", "PO Number" });
                    hasErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(bBizMsg.custIssPoDt)) {
                if (!ZYPCommonFunc.hasValue(bBizMsg.custIssPoNum)) {
                    bBizMsg.custIssPoNum.setErrorInfo(1, NSAM0716E, new String[] {"PO Number", "PO Expr Date", "PO Date" });
                    hasErr = true;
                }
                if (!ZYPCommonFunc.hasValue(bBizMsg.dsPoExprDt)) {
                    bBizMsg.dsPoExprDt.setErrorInfo(1, NSAM0716E, new String[] {"PO Number", "PO Expr Date", "PO Date" });
                    hasErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(bBizMsg.dsPoExprDt)) {
                if (!ZYPCommonFunc.hasValue(bBizMsg.custIssPoNum)) {
                    bBizMsg.custIssPoNum.setErrorInfo(1, NSAM0716E, new String[] {"PO Number", "PO Date", "PO Expr Date" });
                    hasErr = true;
                }
                if (!ZYPCommonFunc.hasValue(bBizMsg.custIssPoDt)) {
                    bBizMsg.custIssPoDt.setErrorInfo(1, NSAM0716E, new String[] {"PO Number", "PO Date", "PO Expr Date" });
                    hasErr = true;
                }
            }
            // 2018/04/16 QC#20162 Mod End
        }
        return hasErr;
    }

    /**
     * @param bizMsg
     * @return if error then return true.
     */
    public static boolean checkContr(NSAL1320CMsg bizMsg) {
        int ixA = bizMsg.xxCellIdx.getValueInt();
        NSAL1320_ACMsg aBizMsg = bizMsg.A.no(ixA);
        if (!ZYPCommonFunc.hasValue(aBizMsg.dsContrNum_AD)) {
            if (ZYPConstant.FLG_ON_Y.equals(aBizMsg.addAsryFlg.getValue())) {
                aBizMsg.dsContrNum_AD.setErrorInfo(1, ZZM9000E, new String[] {"Contract#" });
                return true;
            }
            aBizMsg.dsContrPk_AD.clear();
            aBizMsg.serNum_A.clear();
            // 2017/02/13 QC#16575 ADD STRAT
            aBizMsg.mdseCd_A.clear();
            // 2017/02/13 QC#16575 ADD E N D
            ZYPEZDItemValueSetter.setValue(aBizMsg.addAsryFlg, ZYPConstant.FLG_OFF_N);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(aBizMsg.dsContrPk_AD)) {
            aBizMsg.dsContrNum_AD.setErrorInfo(1, NSAM0138E, new String[] {"Contract", "Configuration" });
            return true;
        }
        getSerNumFromContract(aBizMsg);
        S21SsmEZDResult ssmResult = NSAL1320Query.getInstance().getContrInfo(aBizMsg);
        if (ssmResult.isCodeNotFound()) {
            aBizMsg.dsContrNum_AD.setErrorInfo(1, NSAM0138E, new String[] {"Contract", "Configuration" });
            return true;
        }
        // START 2017/10/24 [QC#21556, DEL]
        // deriveHeaderInfoFromContrNum(bizMsg, aBizMsg);
        // END 2017/10/24 [QC#21556, DEL]
        return false;
    }

    private static void getSerNumFromContract(NSAL1320_ACMsg aBizMsg) {
        S21SsmEZDResult ssmResult = NSAL1320Query.getInstance().getSerNumFromContract(aBizMsg);
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        // 2017/02/13 QC#16575 UPD STRAT
        List<Map<String, String>> rsltList = (List<Map<String, String>>) ssmResult.getResultObject();
        for (Map<String, String> rs : rsltList) {
            ZYPEZDItemValueSetter.setValue(aBizMsg.serNum_A, rs.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.mdseCd_A, rs.get("MDSE_CD"));
            break;
        }
        // 2017/02/13 QC#16575 UPD E N D
    }

    public static void getContrInfo(NSAL1320CMsg bizMsg) {

        int ixA = bizMsg.xxCellIdx.getValueInt();
        NSAL1320_ACMsg aBizMsg = bizMsg.A.no(ixA);
        if (!ZYPCommonFunc.hasValue(aBizMsg.dsContrNum_AD)) {
            aBizMsg.dsContrPk_AD.clear();
            aBizMsg.serNum_A.clear();
            // 2017/02/13 QC#16575 ADD STRAT
            aBizMsg.mdseCd_A.clear();
            // 2017/02/13 QC#16575 ADD E N D
            ZYPEZDItemValueSetter.setValue(aBizMsg.addAsryFlg, ZYPConstant.FLG_OFF_N);
            return;
        }
        aBizMsg.dsContrPk_AD.clear();
        S21SsmEZDResult ssmResult = NSAL1320Query.getInstance().getContrInfo(aBizMsg);
        if (ssmResult.isCodeNotFound()) {
            aBizMsg.dsContrNum_AD.setErrorInfo(1, ZZZM9006E, new String[] {"Contract#" });
            return;
        }
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (Map<String, Object> rs : rsltList) {
            ZYPEZDItemValueSetter.setValue(aBizMsg.dsContrPk_AD, (BigDecimal) rs.get("DS_CONTR_PK"));
            if (checkContr(bizMsg)) {
                return; // error
            }
            break;
        }
        deriveHeaderInfoFromContrNum(bizMsg, aBizMsg);
    }

    private static void deriveHeaderInfoFromContrNum(NSAL1320CMsg bizMsg, NSAL1320_ACMsg aBizMsg) {
        S21SsmEZDResult ssmResult = NSAL1320Query.getInstance().getHeaderInfoFromContrNum(aBizMsg, bizMsg);
        if (ssmResult.isCodeNotFound()) {
            aBizMsg.dsContrNum_AD.setErrorInfo(1, ZZZM9006E, new String[] {"Contract#" });
            return;
        }
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();

        for (Map<String, Object> rs : rsltList) {
            ZYPEZDItemValueSetter.setValue(aBizMsg.svcPgmMdseCd, (String) rs.get("SVC_PGM_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.mdseDescShortTxt, (String) rs.get("MDSE_DESC_SHORT_TXT"));

            ZYPEZDItemValueSetter.setValue(aBizMsg.dsContrCatgCd, (String) rs.get("DS_CONTR_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.dsAcctNm, (String) rs.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.dsAcctNum, (String) rs.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.soldToCustLocCd, (String) rs.get("ALT_PAYER_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxGenlFldAreaTxt_BI, (String) rs.get("BILL_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.baseBllgCycleCd, (String) rs.get("BASE_BLLG_CYCLE_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.usgBllgCycleCd, (String) rs.get("MTR_BLLG_CYCLE_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcSvcPlnTpCd, (String) rs.get("PRC_SVC_PLN_TP_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcSvcContrTpCd, (String) rs.get("PRC_SVC_CONTR_TP_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(aBizMsg.billByTpCd, (String) rs.get("BILL_BY_TP_CD"));
            // 2018/05/17 QC#22482 Add Start
            ZYPEZDItemValueSetter.setValue(aBizMsg.manContrOvrdFlg, (String) rs.get("MAN_CONTR_OVRD_FLG"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.svcMemoRsnDescTxt, (String) rs.get("SVC_MEMO_RSN_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.svcCmntTxt, (String) rs.get("SVC_CMNT_TXT"));
            // 2018/05/17 QC#22482 Add End
            // START 2018/07/18 K.Kitachi [QC#26589, ADD]
            ZYPEZDItemValueSetter.setValue(aBizMsg.invSeptBaseUsgFlg, (String) rs.get("INV_SEPT_BASE_USG_FLG"));
            // END 2018/07/18 K.Kitachi [QC#26589, ADD]

            // START 2017/10/24 [QC#21556, MOD]
            if (ZYPConstant.FLG_ON_Y.equals(aBizMsg.addAsryFlg.getValue())) {
                // Add accessory
                ssmResult = NSAL1320Query.getInstance().getHeaderInfoFromContrNum(aBizMsg, bizMsg);
                if (ssmResult.isCodeNotFound()) {
                    aBizMsg.dsContrNum_AD.setErrorInfo(1, ZZZM9006E, new String[] {"Contract" });
                    return;
                }
                List<Map<String, Object>> rsltListAcc = (List<Map<String, Object>>) ssmResult.getResultObject();

                BigDecimal toPerMthNumMach = null;
                for (Map<String, Object> rsAcc : rsltListAcc) {
                    // START 2018/06/06 K.Kim [QC#26500, MOD]
                    // oPerMthNumMach = (BigDecimal)
                    // rsAcc.get("TERM_MTH_NUM_MACH");
                    // if
                    // (ZYPDateUtil.compare(ZYPDateUtil.getSalesDate(),
                    // (String)rsAcc.get("CONTR_EFF_FROM_DT")) > 0) {
                    // toPerMthNumMach = (BigDecimal)
                    // rsAcc.get("TERM_MTH_NUM_MACH_RMNG");
                    // }
                    toPerMthNumMach = (BigDecimal) rsAcc.get("TERM_MTH_NUM");
                    if (ZYPDateUtil.compare(ZYPDateUtil.getSalesDate(), (String) rsAcc.get("CONTR_VRSN_EFF_FROM_DT")) > 0) {
                        toPerMthNumMach = (BigDecimal) rsAcc.get("TERM_MTH_NUM_RMNG");
                    }
                    // END 2018/06/06 K.Kim [QC#26500, MOD]
                    break;
                }
                ZYPEZDItemValueSetter.setValue(aBizMsg.fromPerMthNum, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(aBizMsg.toPerMthNum, toPerMthNumMach);

            } else {
                // Add machine
                BigDecimal termMthNum = (BigDecimal) rs.get("TERM_MTH_NUM");
                if (ZYPDateUtil.compare(ZYPDateUtil.getSalesDate(), (String) rs.get("CONTR_VRSN_EFF_FROM_DT")) > 0) {
                    termMthNum = (BigDecimal) rs.get("TERM_MTH_NUM_RMNG");
                }
                ZYPEZDItemValueSetter.setValue(aBizMsg.termMthAot, termMthNum);
                if (!ZYPCommonFunc.hasValue(aBizMsg.fromPerMthNum) && !ZYPCommonFunc.hasValue(aBizMsg.toPerMthNum) && ZYPCommonFunc.hasValue(termMthNum) && BigDecimal.ONE.compareTo(termMthNum) <= 0) {
                    ZYPEZDItemValueSetter.setValue(aBizMsg.fromPerMthNum, BigDecimal.ONE);
                    ZYPEZDItemValueSetter.setValue(aBizMsg.toPerMthNum, termMthNum);
                }
            }
            // END 2017/10/24 [QC#21556, MOD]

            if (!ZYPCommonFunc.hasValue(aBizMsg.prcSvcPlnTpCd) //
                    || !ZYPCommonFunc.hasValue(aBizMsg.prcSvcContrTpCd) //
                    || !ZYPCommonFunc.hasValue(aBizMsg.billByTpCd)) {
                aBizMsg.dsContrNum_AD.setErrorInfo(1, ZZZM9006E, new String[] {"Original Shell" });
            }

            break;
        }
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NSAL1320CMsg
     * </pre>
     */
    public static void callContractImportApi(String glblCmpyCd, NSAL1320CMsg bizMsg) {
        NSZC115001PMsg pMsg = getContractImportApiPMsg(glblCmpyCd, bizMsg);

        // START 2017/08/29 [QC#20665, MOD]
        if (pMsg.svcDtlList.getValidCount() > 0) {
            new NSZC115001().execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);
                bizMsg.setMessageInfo(msgList.get(0));
                return;
            }
        }
        // END 2017/08/29 [QC#20665, MOD]

        // START 2017/06/20 K.Kojima [QC#19053,ADD]
        pMsg = getContractImportApiPMsgForNewConfig(glblCmpyCd, bizMsg);
        if (pMsg.svcDtlList.getValidCount() > 0) {
            new NSZC115001().execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);
                bizMsg.setMessageInfo(msgList.get(0));
                return;
            }
        }

        pMsg = getContractImportApiPMsgForDelte(glblCmpyCd, bizMsg);
        if (pMsg != null) {
            new NSZC115001().execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);
                bizMsg.setMessageInfo(msgList.get(0));
                return;
            }
        }
        // END 2017/06/20 K.Kojima [QC#19053,ADD]

        ZYPTableUtil.clear(bizMsg.D);
    }

    private static NSZC115001PMsg getContractImportApiPMsg(String glblCmpyCd, NSAL1320CMsg bizMsg) {
        NSZC115001PMsg pMsg = new NSZC115001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.refCpoOrdNum, bizMsg.refCpoOrdNum);

        if (isExistsShellContract(glblCmpyCd, bizMsg.refCpoOrdNum.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC115001Constant.PROC_MODE_MOD);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC115001Constant.PROC_MODE_NEW);
        }

        List<String> dplyLineNumList = new ArrayList<String>(pMsg.svcConfigRefList.length());
        List<BigDecimal> cpoSvcLineNumList = new ArrayList<BigDecimal>(pMsg.svcDtlList.length());
        // 2018/05/07 QC#22482 Add Start
        List<String> mdlKeyList = new ArrayList<String>();
        // 2018/05/07 QC#22482 Add End

        // set Service Detail List
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(i);
            int ixD = pMsg.svcDtlList.getValidCount();
            // START 2017/10/19 [QC#21656, ADD]
            if (ZYPConstant.FLG_ON_Y.equals(aBizMsg.contrAvalFlg_A.getValue())) {
                continue;
            }
            // END 2017/10/19 [QC#21656, ADD]
            // editSvcDtlList(pMsg.svcDtlList.no(ixD++), aBizMsg,
            // bizMsg); // 2018/08/27 S21_NA#25105 Mod
            editSvcDtlList(pMsg.svcDtlList.no(ixD++), aBizMsg, bizMsg, glblCmpyCd);
            pMsg.svcDtlList.setValidCount(ixD);
            cpoSvcLineNumList.add(aBizMsg.shellLineNum.getValue());
        }

        Map<BigDecimal, NSAL1320_CCMsg> cBizMsgMap = new HashMap<BigDecimal, NSAL1320_CCMsg>();
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NSAL1320_CCMsg cBizMsg = bizMsg.C.no(i);
            cBizMsgMap.put(cBizMsg.dsContrDtlPk_C.getValue(), cBizMsg);
        }

        // set Service Config Reference List
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NSAL1320_BCMsg bBizMsg = bizMsg.B.no(i);
            NSAL1320_ACMsg aBizMsg = getABizMsg(bizMsg, bBizMsg.shellLineNum_B.getValue());
            if (!ZYPCommonFunc.hasValue(bBizMsg.shellLineNum_B)) {
                continue;
            }

            if (!dplyLineNumList.contains(//
                    S21StringUtil.concatStrings(bBizMsg.shellLineNum_B.getValue(), ",", bBizMsg.dplyLineNum.getValue()))) {
                dplyLineNumList.add(//
                        S21StringUtil.concatStrings(bBizMsg.shellLineNum_B.getValue(), ",", bBizMsg.dplyLineNum.getValue()));
                // START 2017/06/20 K.Kojima [QC#19053,ADD]
                if (ZYPCommonFunc.hasValue(aBizMsg.dsContrPk_A) && !ZYPCommonFunc.hasValue(bBizMsg.dsContrDtlPk_B)) {
                    continue;
                }
                // END 2017/06/20 K.Kojima [QC#19053,ADD]
                // START 2017/10/19 [QC#21656, ADD]
                if (ZYPConstant.FLG_ON_Y.equals(aBizMsg.contrAvalFlg_A.getValue())) {
                    continue;
                }
                // END 2017/10/19 [QC#21656, ADD]
                int ixC = pMsg.svcConfigRefList.getValidCount();
                editSvcConfigRefList(pMsg.svcConfigRefList.no(ixC++), aBizMsg, bBizMsg, bizMsg, cBizMsgMap);
                pMsg.svcConfigRefList.setValidCount(ixC);

                // 2018/05/07 QC#22482 Add Start
                String mdlKey = createShellLineKey(bBizMsg.shellLineNum_B.getValue(), bBizMsg.mdlId.getValue(), aBizMsg.dsContrCatgCd.getValue());
                if (!mdlKeyList.contains(mdlKey)) {
                    if (isManOvrd(aBizMsg) && ZYPCommonFunc.hasValue(aBizMsg.dsContrCatgCd)) {
                        int idx = pMsg.svcPrcList.getValidCount();
                        NSZC115001_svcPrcListPMsg cspPMsg = pMsg.svcPrcList.no(idx);
                        editSvcPrcListForModel(glblCmpyCd, cspPMsg, aBizMsg, bBizMsg);
                        pMsg.svcPrcList.setValidCount(++idx);
                    }
                    mdlKeyList.add(mdlKey);
                }
                // 2018/05/07 QC#22482 Add End
            }
        }

        // START 2017/06/20 K.Kojima [QC#19053,DEL]
        // setDeleteInfoToNSZC1150PMsg(bizMsg, pMsg);
        // END 2017/06/20 K.Kojima [QC#19053,DEL]
        return pMsg;
    }

    // START 2017/06/20 K.Kojima [QC#19053,ADD]
    private static NSZC115001PMsg getContractImportApiPMsgForNewConfig(String glblCmpyCd, NSAL1320CMsg bizMsg) {
        NSZC115001PMsg pMsg = new NSZC115001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.refCpoOrdNum, bizMsg.refCpoOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC115001Constant.PROC_MODE_MOD);

        List<String> dplyLineNumList = new ArrayList<String>(pMsg.svcConfigRefList.length());
        Map<String, Map<String, Object>> mdlPrcHdrMap = new HashMap<String, Map<String, Object>>();
        Map<String, List<Map<String, Object>>> mdlPrcDtlListMap = new HashMap<String, List<Map<String, Object>>>();

        Map<BigDecimal, NSAL1320_CCMsg> cBizMsgMap = new HashMap<BigDecimal, NSAL1320_CCMsg>();
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NSAL1320_CCMsg cBizMsg = bizMsg.C.no(i);
            cBizMsgMap.put(cBizMsg.dsContrDtlPk_C.getValue(), cBizMsg);
        }

        for (int countA = 0; countA < bizMsg.A.getValidCount(); countA++) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(countA);
            int idxA = pMsg.svcDtlList.getValidCount();
            if (!ZYPCommonFunc.hasValue(aBizMsg.dsContrPk_A)) {
                continue;
            }
            boolean detailSetFlag = false;
            for (int countB = 0; countB < bizMsg.B.getValidCount(); countB++) {
                NSAL1320_BCMsg bBizMsg = bizMsg.B.no(countB);
                if (!ZYPCommonFunc.hasValue(bBizMsg.shellLineNum_B)) {
                    continue;
                }
                if (aBizMsg.shellLineNum.getValue().compareTo(bBizMsg.shellLineNum_B.getValue()) != 0) {
                    continue;
                }
                if (ZYPCommonFunc.hasValue(bBizMsg.dsContrDtlPk_B)) {
                    continue;
                }
                if (detailSetFlag == false) {
                    // editSvcDtlList(pMsg.svcDtlList.no(idxA++),
                    // aBizMsg, bizMsg); // 2018/08/27 S21_NA#25105
                    // Mod
                    editSvcDtlList(pMsg.svcDtlList.no(idxA++), aBizMsg, bizMsg, glblCmpyCd);
                    pMsg.svcDtlList.setValidCount(idxA);
                    detailSetFlag = true;
                }
                if (!dplyLineNumList.contains(//
                        S21StringUtil.concatStrings(bBizMsg.shellLineNum_B.getValue(), ",", bBizMsg.dplyLineNum.getValue()))) {
                    dplyLineNumList.add(//
                            S21StringUtil.concatStrings(bBizMsg.shellLineNum_B.getValue(), ",", bBizMsg.dplyLineNum.getValue()));
                    int ixC = pMsg.svcConfigRefList.getValidCount();
                    editSvcConfigRefList(pMsg.svcConfigRefList.no(ixC++), aBizMsg, bBizMsg, bizMsg, cBizMsgMap);
                    pMsg.svcConfigRefList.setValidCount(ixC);

                    Map<String, Object> mdlPrcHdr = null;
                    List<Map<String, Object>> mdlPrcDtlList = null;

                    String key = createShellLineKey(bBizMsg.shellLineNum_B.getValue(), bBizMsg.mdlId.getValue(), aBizMsg.dsContrCatgCd.getValue());
                    if (mdlPrcHdrMap.containsKey(key) && mdlPrcDtlListMap.containsKey(key)) {

                        // get Shell Line Data(Header)
                        mdlPrcHdr = mdlPrcHdrMap.get(key);

                        // get Shell Line Data(Detail)
                        mdlPrcDtlList = mdlPrcDtlListMap.get(key);

                    } else {

                        // get Shell Line Data(Header)
                        S21SsmEZDResult rsltMdlPrcHdr = NSAL1320Query.getInstance().getInitDataFromModelPricingHeader(glblCmpyCd, bizMsg, aBizMsg, bBizMsg);
                        if (!rsltMdlPrcHdr.isCodeNormal()) {
                            continue;
                        }
                        mdlPrcHdr = (Map<String, Object>) rsltMdlPrcHdr.getResultObject();
                        if (mdlPrcHdr == null) {
                            continue;
                        }

                        // set ServicePriceList(ForShellLine)
                        // START 2017/08/31 [QC#20773, ADD]
                        if (!isMdlPrcNoEntry(glblCmpyCd, aBizMsg, mdlPrcHdr, aBizMsg.usgBllgCycleCd.getValue())) {
                            // END 2017/08/31 [QC#20773, ADD]
                            ixC = pMsg.svcPrcList.getValidCount();
                            editSvcPrcListForModel(glblCmpyCd, pMsg.svcPrcList.no(ixC++), aBizMsg, mdlPrcHdr);
                            pMsg.svcPrcList.setValidCount(ixC);

                            mdlPrcHdrMap.put(key, mdlPrcHdr);

                            // get Shell Line Data(Detail)
                            if (ZYPCommonFunc.hasValue(aBizMsg.usgBllgCycleCd)) {
                                S21SsmEZDResult rsltMdlPrcDtl = NSAL1320Query.getInstance().getInitDataFromModelPricingDetail(glblCmpyCd, bizMsg, aBizMsg, bBizMsg);
                                // START 2017/09/04 [QC#20773, MOD]
                                // if (!rsltMdlPrcDtl.isCodeNormal())
                                // {
                                // continue;
                                // }
                                // mdlPrcDtlList = (List<Map<String,
                                // Object>>)
                                // rsltMdlPrcDtl.getResultObject();
                                // if (mdlPrcDtlList == null ||
                                // mdlPrcDtlList.size() == 0) {
                                // continue;
                                // }
                                //
                                // // set
                                // ServiceUsagePriceList(ForShellLine)
                                // editSvcUsgPrcListForModel(glblCmpyCd,
                                // pMsg.svcUsgPrcList, aBizMsg,
                                // mdlPrcHdr, mdlPrcDtlList);
                                // 
                                // mdlPrcDtlListMap.put(key,
                                // mdlPrcDtlList);
                                if (rsltMdlPrcDtl.isCodeNormal()) {
                                    mdlPrcDtlList = (List<Map<String, Object>>) rsltMdlPrcDtl.getResultObject();
                                    if (mdlPrcDtlList != null && mdlPrcDtlList.size() > 0) {
                                        // set
                                        // ServiceUsagePriceList(ForShellLine)
                                        editSvcUsgPrcListForModel(glblCmpyCd, pMsg.svcUsgPrcList, aBizMsg, mdlPrcHdr, mdlPrcDtlList);
                                        mdlPrcDtlListMap.put(key, mdlPrcDtlList);
                                    }
                                }
                                // END 2017/09/04 [QC#20773, MOD]
                            }
                            // START 2017/08/31 [QC#20773, ADD]
                        }
                        // END 2017/08/31 [QC#20773, ADD]
                    }

                    if (DS_CONTR_CATG.FLEET.equals(aBizMsg.dsContrCatgCd.getValue())) {
                        continue;
                    }

                    // set Service Price List (For New Config)
                    // START 2017/08/31 [QC#20773, MOD]
                    if (!isMdlPrcNoEntry(glblCmpyCd, aBizMsg, mdlPrcHdr, aBizMsg.usgBllgCycleCd.getValue())) {
                        ixC = pMsg.svcPrcList.getValidCount();
                        editSvcPrcListForNewConfig(glblCmpyCd, pMsg.svcPrcList.no(ixC++), aBizMsg, bBizMsg, mdlPrcHdr);
                        pMsg.svcPrcList.setValidCount(ixC);

                        // set Service Usage Price List (For New
                        // Config)
                        // START 2017/09/04 [QC#20773, MOD]
                        // if
                        // (ZYPCommonFunc.hasValue(aBizMsg.usgBllgCycleCd))
                        // {
                        if (ZYPCommonFunc.hasValue(aBizMsg.usgBllgCycleCd) && mdlPrcDtlList != null && mdlPrcDtlList.size() > 0) {
                            // END 2017/09/04 [QC#20773, MOD]
                            editSvcUsgPrcListForNewConfig(glblCmpyCd, pMsg.svcUsgPrcList, aBizMsg, bBizMsg, mdlPrcHdr, mdlPrcDtlList);
                        }
                    }
                    // END 2017/08/31 [QC#20773, MOD]
                }
            }
        }

        return pMsg;
    }

    private static NSZC115001PMsg getContractImportApiPMsgForDelte(String glblCmpyCd, NSAL1320CMsg bizMsg) {
        NSZC115001PMsg pMsg = new NSZC115001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.refCpoOrdNum, bizMsg.refCpoOrdNum);

        if (isExistsShellContract(glblCmpyCd, bizMsg.refCpoOrdNum.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC115001Constant.PROC_MODE_MOD);
        } else {
            return null;
        }

        // set Service Detail List
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(i);
            // START 2017/08/21 [QC#20670, ADD]
            // if (!ZYPCommonFunc.hasValue(aBizMsg.dsContrPk_A)) {
            // continue;
            // }
            if (!ZYPCommonFunc.hasValue(aBizMsg.dsContrPk_A) && !ZYPCommonFunc.hasValue(aBizMsg.dsContrPk_AD)) {
                continue;
            }
            // END 2017/08/21 [QC#20670, ADD]
            int ixD = pMsg.svcDtlList.getValidCount();
            // editSvcDtlList(pMsg.svcDtlList.no(ixD++), aBizMsg,
            // bizMsg); // 2018/08/27 S21_NA#25105 Mod
            editSvcDtlList(pMsg.svcDtlList.no(ixD++), aBizMsg, bizMsg, glblCmpyCd);
            pMsg.svcDtlList.setValidCount(ixD);
        }

        boolean setFlag = setDeleteInfoToNSZC1150PMsg(bizMsg, pMsg);
        if (setFlag == false) {
            return null;
        }
        return pMsg;
    }

    // END 2017/06/20 K.Kojima [QC#19053,ADD]

    /**
     * <pre>
     * @param bizMsg    NSAL1320CMsg
     * @param pMsg      NSZC115001PMsg
     * </pre>
     */
    // START 2017/06/21 K.Kojima [QC#19256,MOD]
    // private static void setDeleteInfoToNSZC1150PMsg(NSAL1320CMsg
    // bizMsg, NSZC115001PMsg pMsg) {
    private static boolean setDeleteInfoToNSZC1150PMsg(NSAL1320CMsg bizMsg, NSZC115001PMsg pMsg) {
        // END 2017/06/21 K.Kojima [QC#19256,MOD]
        // START 2017/06/20 K.Kojima [QC#19053,ADD]
        boolean setFlag = false;
        // END 2017/06/20 K.Kojima [QC#19053,ADD]
        int ix;
        List<BigDecimal> hdrDelDsContrPkList = new ArrayList<BigDecimal>();
        // Header Level
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.D.no(i).dsContrPk_D) //
                    && !ZYPCommonFunc.hasValue(bizMsg.D.no(i).dsContrDtlPk_D)) {
                ix = pMsg.svcDtlList.getValidCount();

                // START 2017/08/21 [QC#20670, ADD]
                if (isAddToContract(pMsg, bizMsg.D.no(i).dsContrPk_D.getValue())) {
                    continue;
                }
                // END 2017/08/21 [QC#20670, ADD]

                NSZC115001_svcDtlListPMsg csdPMsg = pMsg.svcDtlList.no(ix);
                ZYPEZDItemValueSetter.setValue(csdPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_DEL);
                ZYPEZDItemValueSetter.setValue(csdPMsg.dsContrPk, bizMsg.D.no(i).dsContrPk_D);
                // add start 2017/06/08 QC#18853
                // mod start 2017/06/19 QC#19036
                ZYPEZDItemValueSetter.setValue(csdPMsg.delFlg_PI, getDelFlg(pMsg.glblCmpyCd.getValue(), bizMsg.refCpoOrdNum.getValue(), bizMsg.D.no(i).dsContrPk_D.getValue()));
                // mod end 2017/06/19 QC#19036
                // add end 2017/06/08 QC#18853
                // START 2017/08/29 [QC#20665, ADD]
                ZYPEZDItemValueSetter.setValue(csdPMsg.shellLineNum, bizMsg.D.no(i).shellLineNum_D.getValue());
                // END 2017/08/29 [QC#20665, ADD]
                pMsg.svcDtlList.setValidCount(ix + 1);
                hdrDelDsContrPkList.add(bizMsg.D.no(i).dsContrPk_D.getValue());
                // START 2017/06/20 K.Kojima [QC#19053,ADD]
                setFlag = true;
                // END 2017/06/20 K.Kojima [QC#19053,ADD]
            }
        }

        // Detail Level
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.D.no(i).dsContrPk_D) //
                    && ZYPCommonFunc.hasValue(bizMsg.D.no(i).dsContrDtlPk_D)) {
                if (hdrDelDsContrPkList.contains(bizMsg.D.no(i).dsContrPk_D.getValue())) {
                    // Exists Header Level
                    continue;
                }

                ix = pMsg.svcConfigRefList.getValidCount();
                NSZC115001_svcConfigRefListPMsg cfgPMsg = pMsg.svcConfigRefList.no(ix);
                ZYPEZDItemValueSetter.setValue(cfgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_DEL);
                ZYPEZDItemValueSetter.setValue(cfgPMsg.dsContrDtlPk, bizMsg.D.no(i).dsContrDtlPk_D);
                ZYPEZDItemValueSetter.setValue(cfgPMsg.shellLineNum, bizMsg.D.no(i).shellLineNum_D);
                pMsg.svcConfigRefList.setValidCount(ix + 1);
                // START 2017/06/20 K.Kojima [QC#19053,ADD]
                setFlag = true;
                // END 2017/06/20 K.Kojima [QC#19053,ADD]
            }
        }
        // START 2017/06/20 K.Kojima [QC#19053,ADD]
        return setFlag;
        // END 2017/06/20 K.Kojima [QC#19053,ADD]
    }

    // START 2017/08/21 [QC#20670, ADD]
    private static boolean isAddToContract(NSZC115001PMsg pMsg, BigDecimal dsContrPk) {
        for (int ix = 0; ix < pMsg.svcDtlList.getValidCount(); ix++) {
            NSZC115001_svcDtlListPMsg csdPMsg = pMsg.svcDtlList.no(ix);
            if (NSZC115001Constant.RQST_TP_DEL.equals(csdPMsg.xxRqstTpCd.getValue())) {
                continue;
            }
            // START 2017/08/29 [QC#20665, MOD]
            // if (ZYPCommonFunc.hasValue(csdPMsg.dsContrPk_AD)) {
            // return true;
            // }
            if (ZYPCommonFunc.hasValue(csdPMsg.dsContrPk_AD) && ZYPCommonFunc.hasValue(dsContrPk)) {
                if (dsContrPk.compareTo(csdPMsg.dsContrPk_AD.getValue()) == 0 && ZYPConstant.FLG_ON_Y.equals(csdPMsg.addAsryFlg.getValue())) {
                    return true;
                }
            }
            // END 2017/08/29 [QC#20665, MOD]
        }
        return false;
    }

    // END 2017/08/21 [QC#20670, ADD]

    // private static void editSvcDtlList(NSZC115001_svcDtlListPMsg
    // csdPMsg, NSAL1320_ACMsg aBizMsg, NSAL1320CMsg bizMsg) { //
    // 2018/08/27 S21_NA#25105 Mod
    private static void editSvcDtlList(NSZC115001_svcDtlListPMsg csdPMsg, NSAL1320_ACMsg aBizMsg, NSAL1320CMsg bizMsg, String glblCmpyCd) {

        ZYPEZDItemValueSetter.setValue(csdPMsg.dsContrPk, aBizMsg.dsContrPk_A);
        ZYPEZDItemValueSetter.setValue(csdPMsg.shellLineNum, aBizMsg.shellLineNum);
        ZYPEZDItemValueSetter.setValue(csdPMsg.svcPgmMdseCd, aBizMsg.svcPgmMdseCd);
        ZYPEZDItemValueSetter.setValue(csdPMsg.prcSvcContrTpCd, aBizMsg.prcSvcContrTpCd);
        ZYPEZDItemValueSetter.setValue(csdPMsg.prcSvcPlnTpCd, aBizMsg.prcSvcPlnTpCd);
        ZYPEZDItemValueSetter.setValue(csdPMsg.dsContrCatgCd, aBizMsg.dsContrCatgCd);
        ZYPEZDItemValueSetter.setValue(csdPMsg.baseBllgCycleCd, aBizMsg.baseBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(csdPMsg.usgBllgCycleCd, aBizMsg.usgBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(csdPMsg.fromPerMthNum, aBizMsg.fromPerMthNum);
        ZYPEZDItemValueSetter.setValue(csdPMsg.toPerMthNum, aBizMsg.toPerMthNum);
        ZYPEZDItemValueSetter.setValue(csdPMsg.fixTermInMthAot, aBizMsg.fixTermInMthAot);
        ZYPEZDItemValueSetter.setValue(csdPMsg.maxUplftPct, aBizMsg.maxUplftPct);
        // 2018/05/07 QC#22482 Mod Start
        // ZYPEZDItemValueSetter.setValue(csdPMsg.billWithEquipFlg,
        // aBizMsg.billWithEquipFlg);
        if (ZYPCommonFunc.hasValue(aBizMsg.billWithEquipFlg)) {
            ZYPEZDItemValueSetter.setValue(csdPMsg.billWithEquipFlg, aBizMsg.billWithEquipFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(csdPMsg.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
        }
        // 2018/05/07 QC#22482 Mod End
        ZYPEZDItemValueSetter.setValue(csdPMsg.billByTpCd, aBizMsg.billByTpCd);
        ZYPEZDItemValueSetter.setValue(csdPMsg.soldToCustLocCd, aBizMsg.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(csdPMsg.sellToCustCd, aBizMsg.dsAcctNum);

        if (ZYPCommonFunc.hasValue(aBizMsg.manContrOvrdFlg)) {
            ZYPEZDItemValueSetter.setValue(csdPMsg.manContrOvrdFlg, aBizMsg.manContrOvrdFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(csdPMsg.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
        }
        // 2018/05/07 QC#22482 Mod Start
        // ZYPEZDItemValueSetter.setValue(csdPMsg.manContrOvrdRsnCd,
        // aBizMsg.manContrOvrdRsnCd);
        // ZYPEZDItemValueSetter.setValue(csdPMsg.manContrOvrdCmntTxt,
        // aBizMsg.manContrOvrdCmntTxt);
        if (isManOvrd(aBizMsg)) {
            ZYPEZDItemValueSetter.setValue(csdPMsg.manContrOvrdRsnCd, aBizMsg.svcMemoRsnCd);
            if (aBizMsg.svcCmntTxt.getValue().length() > MAN_CONTR_OVRD_CMNT_TXT_LENGTH) {
                ZYPEZDItemValueSetter.setValue(csdPMsg.manContrOvrdCmntTxt, aBizMsg.svcCmntTxt.getValue().substring(0, MAN_CONTR_OVRD_CMNT_TXT_LENGTH));
            } else {
                ZYPEZDItemValueSetter.setValue(csdPMsg.manContrOvrdCmntTxt, aBizMsg.svcCmntTxt);
            }
        }
        // 2018/05/07 QC#22482 Mod End

        if (ZYPCommonFunc.hasValue(aBizMsg.applyEquipBillToFlg)) {
            ZYPEZDItemValueSetter.setValue(csdPMsg.applyEquipBillToFlg, aBizMsg.applyEquipBillToFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(csdPMsg.applyEquipBillToFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(csdPMsg.dsContrPk)) {
            ZYPEZDItemValueSetter.setValue(csdPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
            // 2018/08/24 S21_NA#25105 Add Start
            DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrPk, aBizMsg.dsContrPk_A);

            dsContrTMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(dsContrTMsg);
            if (dsContrTMsg != null) {
                ZYPEZDItemValueSetter.setValue(csdPMsg.addlBasePrcCatgCd, dsContrTMsg.addlBasePrcCatgCd);
                ZYPEZDItemValueSetter.setValue(csdPMsg.rntlPrcCatgCd, dsContrTMsg.rntlPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(csdPMsg.addlChrgPrcCatgCd, dsContrTMsg.addlChrgPrcCatgCd);
            }
            // 2018/08/24 S21_NA#25105 Add End
        } else {
            ZYPEZDItemValueSetter.setValue(csdPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
        }
        ZYPEZDItemValueSetter.setValue(csdPMsg.dsContrPk_AD, aBizMsg.dsContrPk_AD);

        ZYPEZDItemValueSetter.setValue(csdPMsg.cpoSvcAgmtVerNum, aBizMsg.cpoSvcAgmtVerNum);
        if (ZYPCommonFunc.hasValue(aBizMsg.addAsryFlg)) {
            ZYPEZDItemValueSetter.setValue(csdPMsg.addAsryFlg, aBizMsg.addAsryFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(csdPMsg.addAsryFlg, ZYPConstant.FLG_OFF_N);
        }
    }

    private static void editSvcConfigRefList(//
            NSZC115001_svcConfigRefListPMsg cscPMsg //
            , NSAL1320_ACMsg aBizMsg //
            , NSAL1320_BCMsg bBizMsg //
            , NSAL1320CMsg bizMsg //
            , Map<BigDecimal, NSAL1320_CCMsg> cBizMsgMap) {
        ZYPEZDItemValueSetter.setValue(cscPMsg.dsOrdPosnNum, bBizMsg.dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(cscPMsg.shellLineNum, aBizMsg.shellLineNum);
        ZYPEZDItemValueSetter.setValue(cscPMsg.dsContrDtlPk, bBizMsg.dsContrDtlPk_B);
        ZYPEZDItemValueSetter.setValue(cscPMsg.dsContrPk, bBizMsg.dsContrPk_B);
        ZYPEZDItemValueSetter.setValue(cscPMsg.cpoOrdNum, bizMsg.refCpoOrdNum);
        ZYPEZDItemValueSetter.setValue(cscPMsg.cpoDtlLineNum, bBizMsg.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(cscPMsg.cpoDtlLineSubNum, bBizMsg.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(cscPMsg.custIssPoNum, bBizMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(cscPMsg.custIssPoDt, bBizMsg.custIssPoDt);
        // 2018/04/16 QC#20162 Add Start
        ZYPEZDItemValueSetter.setValue(cscPMsg.dsPoExprDt, bBizMsg.dsPoExprDt);
        // 2018/04/16 QC#20162 Add End
        ZYPEZDItemValueSetter.setValue(cscPMsg.mtrReadMethCd, bBizMsg.mtrReadMethCd);
        ZYPEZDItemValueSetter.setValue(cscPMsg.crRebilCd, bBizMsg.crRebilCd);
        ZYPEZDItemValueSetter.setValue(cscPMsg.svcConfigMstrPk, getSvcConfigMstrPk(bizMsg, cscPMsg));

        if (ZYPCommonFunc.hasValue(cscPMsg.dsContrDtlPk)) {
            ZYPEZDItemValueSetter.setValue(cscPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
        } else {
            ZYPEZDItemValueSetter.setValue(cscPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
        }
    }

    private static BigDecimal getSvcConfigMstrPk(NSAL1320CMsg bizMsg, NSZC115001_svcConfigRefListPMsg cscPMsg) {
        S21SsmEZDResult rslt = NSAL1320Query.getInstance().getSvcConfigMstrPk(bizMsg, cscPMsg.dsOrdPosnNum.getValue());
        if (rslt.isCodeNotFound()) {
            return null;
        }
        List<Map<String, BigDecimal>> rsltList = (List<Map<String, BigDecimal>>) rslt.getResultObject();

        return rsltList.get(0).get("SVC_CONFIG_MSTR_PK");
    }

    private static boolean isRentalOrder(String glblCmpyCd, String cpoOrdNum) {
        Integer cnt = NSAL1320Query.getInstance().getRentalOrderCnt(glblCmpyCd, cpoOrdNum);
        return (cnt > 0);
    }

    // add start 2017/06/19 QC#19036
    private static String getDelFlg(String glblCmpyCd, String cpoOrdNum, BigDecimal dsContrPk) {
        Integer cnt = getDsContrDtlOtherCpoCount(glblCmpyCd, cpoOrdNum, dsContrPk);
        if (cnt == 0) {
            return ZYPConstant.FLG_OFF_N;
        }
        return ZYPConstant.FLG_ON_Y;
    }

    private static Integer getDsContrDtlOtherCpoCount(String glblCmpyCd, String cpoOrdNum, BigDecimal dsContrPk) {
        Integer cnt = 0;
        S21SsmEZDResult ssmResult = NSAL1320Query.getInstance().getDsContrDtlOtherCpoCount(glblCmpyCd, cpoOrdNum, dsContrPk);
        if (ssmResult.isCodeNormal()) {
            cnt = (Integer) ssmResult.getResultObject();
            if (cnt == null) {
                cnt = 0;
            }
        }
        return cnt;
    }

    // add end 2017/06/19 QC#19036

    // START 2017/06/20 K.Kojima [QC#19053,ADD]
    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bBizMsg       NSAL1320_BCMsg
     * @param bizMsg        NSAL1320CMsg
     * </pre>
     */
    public static void setAmountForNewLine(String glblCmpyCd, NSAL1320_ACMsg aBizMsg, NSAL1320_BCMsg bBizMsg, NSAL1320CMsg bizMsg) {
        if (ZYPCommonFunc.hasValue(bBizMsg.dsContrDtlPk_B)) {
            return;
        }
        // 2018/04/16 QC#10374 Mod Start
        // if (ZYPCommonFunc.hasValue(bBizMsg.xxTotAmt_LN) &&
        // bBizMsg.xxTotAmt_LN.getValue().compareTo(BigDecimal.ZERO) >
        // 0) {
        if (ZYPCommonFunc.hasValue(bBizMsg.xxTermAmt_TT) && bBizMsg.xxTermAmt_TT.getValue().compareTo(BigDecimal.ZERO) > 0) {
            // 2018/04/16 QC#10374 Mod End
            return;
        }
        if (DS_CONTR_CATG.FLEET.equals(aBizMsg.dsContrCatgCd.getValue())) {
            return;
        }
        S21SsmEZDResult rsltAmount = NSAL1320Query.getInstance().getAmountForNewLine(glblCmpyCd, bizMsg, aBizMsg, bBizMsg);
        if (rsltAmount.isCodeNormal()) {
            Map<String, Object> rsltMap = (Map<String, Object>) rsltAmount.getResultObject();
            // Mod Start 2017/10/18 QC#21860
            GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
            int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
            // ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_LN,
            // (BigDecimal) rsltMap.get("TOT_AMT_LN"));
            // ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_SV,
            // (BigDecimal) rsltMap.get("TOT_AMT_SV"));
            // ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_EQ,
            // (BigDecimal) rsltMap.get("TOT_AMT_EQ"));
            // ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_AD,
            // (BigDecimal) rsltMap.get("TOT_AMT_AD"));
            // 2018/04/16 QC#10374 Mod Start
            // ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_LN,
            // round((BigDecimal) rsltMap.get("TOT_AMT_LN"), scale));
            // ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_SV,
            // round((BigDecimal) rsltMap.get("TOT_AMT_SV"), scale));
            // ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_EQ,
            // round((BigDecimal) rsltMap.get("TOT_AMT_EQ"), scale));
            // ZYPEZDItemValueSetter.setValue(bBizMsg.xxTotAmt_AD,
            // round((BigDecimal) rsltMap.get("TOT_AMT_AD"), scale));
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxFreqCycleCnt, (BigDecimal) rsltMap.get("MULTIPLY"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_TT, round((BigDecimal) rsltMap.get("DEAL_AMT_TT"), scale));
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_EQ, round((BigDecimal) rsltMap.get("DEAL_AMT_EQ"), scale));
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_AC, round((BigDecimal) rsltMap.get("DEAL_AMT_AC"), scale));
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxDealAmt_AD, round((BigDecimal) rsltMap.get("DEAL_AMT_AD"), scale));
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_TT, round((BigDecimal) rsltMap.get("TERM_AMT_TT"), scale));
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_EQ, round((BigDecimal) rsltMap.get("TERM_AMT_EQ"), scale));
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_AC, round((BigDecimal) rsltMap.get("TERM_AMT_AC"), scale));
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxTermAmt_AD, round((BigDecimal) rsltMap.get("TERM_AMT_AD"), scale));
            // 2018/04/16 QC#10374 Mod End
            // Mod End 2017/10/18 QC#21860
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (bizMsg.A.no(i).shellLineNum.getValue().compareTo(bBizMsg.shellLineNum_B.getValue()) == 0) {
                    // Mod Start 2017/10/18 QC#21860
                    // ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxTotAmt_S,
                    // bizMsg.A.no(i).xxTotAmt_S.getValue().add(bBizMsg.xxTotAmt_LN.getValue()));
                    // 2018/04/16 QC#10374 Mod Start
                    // ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxTotAmt_S,
                    // round(bizMsg.A.no(i).xxTotAmt_S.getValue().add(bBizMsg.xxTotAmt_LN.getValue()),
                    // scale));
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxTotAmt_S, round(bizMsg.A.no(i).xxTotAmt_S.getValue().add(bBizMsg.xxTermAmt_TT.getValue()), scale));
                    // 2018/04/16 QC#10374 Mod End
                    // Mod End 2017/10/18 QC#21860
                    break;
                }
            }
        }
    }

    private static void editSvcPrcListForModel(String glblCmpyCd, NSZC115001_svcPrcListPMsg cspPMsg, NSAL1320_ACMsg aBizMsg, Map<String, Object> mdlPrcHdr) {
        // START 2017/08/31 [QC#20773, ADD]
        // if (isMdlPrcNoEntry(glblCmpyCd, aBizMsg, mdlPrcHdr,
        // aBizMsg.usgBllgCycleCd.getValue())) {
        // return;
        // }
        // END 2017/08/31 [QC#20773, ADD]
        ZYPEZDItemValueSetter.setValue(cspPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
        ZYPEZDItemValueSetter.setValue(cspPMsg.shellLineNum, aBizMsg.shellLineNum);
        // cspPMsg.dsOrdPosnNum.clear();
        ZYPEZDItemValueSetter.setValue(cspPMsg.dsContrDtlPk, (BigDecimal) mdlPrcHdr.get("DS_CONTR_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(cspPMsg.dsContrPk, aBizMsg.dsContrPk_A);
        ZYPEZDItemValueSetter.setValue(cspPMsg.mdlId, (BigDecimal) mdlPrcHdr.get("MDL_ID"));
        ZYPEZDItemValueSetter.setValue(cspPMsg.maintPrcCatgCd, (String) mdlPrcHdr.get("PRC_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(cspPMsg.prcMtrPkgPk, (BigDecimal) mdlPrcHdr.get("PRC_MTR_PKG_PK"));
        // Mod Start 2017/10/18 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
        // ZYPEZDItemValueSetter.setValue(cspPMsg.basePrcDealAmt,
        // (BigDecimal) mdlPrcHdr.get("BASE_PRC_DEAL_AMT"));
        ZYPEZDItemValueSetter.setValue(cspPMsg.basePrcDealAmt, round((BigDecimal) mdlPrcHdr.get("BASE_PRC_DEAL_AMT"), scale));
        // Mod End 2017/10/18 QC#21860
        ZYPEZDItemValueSetter.setValue(cspPMsg.dealPrcListPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cspPMsg.prcTierSvcOfferCd, (String) mdlPrcHdr.get("PRC_TIER_SVC_OFFER_CD"));
        // cspPMsg.corpAdvPrcFlg.clear();
        // cspPMsg.billToCustCd.clear();
        // cspPMsg.sellToCustCd.clear();
    }

    private static void editSvcPrcListForNewConfig(String glblCmpyCd, NSZC115001_svcPrcListPMsg cspPMsg, NSAL1320_ACMsg aBizMsg, NSAL1320_BCMsg bBizMsg, Map<String, Object> mdlPrcHdr) {
        // START 2017/08/31 [QC#20773, ADD]
        // if (isMdlPrcNoEntry(glblCmpyCd, aBizMsg, mdlPrcHdr,
        // aBizMsg.usgBllgCycleCd.getValue())) {
        // return;
        // }
        // END 2017/08/31 [QC#20773, ADD]
        ZYPEZDItemValueSetter.setValue(cspPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
        ZYPEZDItemValueSetter.setValue(cspPMsg.shellLineNum, bBizMsg.shellLineNum_B);
        ZYPEZDItemValueSetter.setValue(cspPMsg.dsOrdPosnNum, bBizMsg.dsOrdPosnNum);
        // cspPMsg.dsContrDtlPk.clear();
        ZYPEZDItemValueSetter.setValue(cspPMsg.dsContrPk, aBizMsg.dsContrPk_A);
        ZYPEZDItemValueSetter.setValue(cspPMsg.mdlId, bBizMsg.mdlId);
        ZYPEZDItemValueSetter.setValue(cspPMsg.maintPrcCatgCd, (String) mdlPrcHdr.get("PRC_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(cspPMsg.prcMtrPkgPk, (BigDecimal) mdlPrcHdr.get("PRC_MTR_PKG_PK"));
        // Mod Start 2017/10/18 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
        // ZYPEZDItemValueSetter.setValue(cspPMsg.basePrcDealAmt,
        // (BigDecimal) mdlPrcHdr.get("BASE_PRC_DEAL_AMT"));
        // ZYPEZDItemValueSetter.setValue(cspPMsg.dealPrcListPrcAmt,
        // BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cspPMsg.basePrcDealAmt, round((BigDecimal) mdlPrcHdr.get("BASE_PRC_DEAL_AMT"), scale));
        ZYPEZDItemValueSetter.setValue(cspPMsg.dealPrcListPrcAmt, round(BigDecimal.ZERO, scale));
        // Mod End 2017/10/18 QC#21860
        ZYPEZDItemValueSetter.setValue(cspPMsg.prcTierSvcOfferCd, (String) mdlPrcHdr.get("PRC_TIER_SVC_OFFER_CD"));
        // cspPMsg.corpAdvPrcFlg.clear();
        // cspPMsg.billToCustCd.clear();
        // cspPMsg.sellToCustCd.clear();
    }

    // 2018/05/07 QC#22482 Add Start
    private static void editSvcPrcListForModel(String glblCmpyCd, NSZC115001_svcPrcListPMsg cspPMsg, NSAL1320_ACMsg aBizMsg, NSAL1320_BCMsg bBizMsg) {

        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());

        ZYPEZDItemValueSetter.setValue(cspPMsg.shellLineNum, bBizMsg.shellLineNum_B);
        ZYPEZDItemValueSetter.setValue(cspPMsg.dsContrPk, bBizMsg.dsContrPk_B);
        if (!DS_CONTR_CATG.FLEET.equals(aBizMsg.dsContrCatgCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(cspPMsg.mdlId, bBizMsg.mdlId);
        }
        cspPMsg.maintPrcCatgCd.clear();
        cspPMsg.prcMtrPkgPk.clear();
        ZYPEZDItemValueSetter.setValue(cspPMsg.basePrcDealAmt, round(BigDecimal.ZERO, scale));
        ZYPEZDItemValueSetter.setValue(cspPMsg.dealPrcListPrcAmt, round(BigDecimal.ZERO, scale));
        cspPMsg.prcTierSvcOfferCd.clear();
        if (ZYPCommonFunc.hasValue(bBizMsg.dsContrDtlPk_B)) {
            ZYPEZDItemValueSetter.setValue(cspPMsg.dsContrDtlPk, bBizMsg.dsContrDtlPk_B);
            ZYPEZDItemValueSetter.setValue(cspPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
        } else {
            ZYPEZDItemValueSetter.setValue(cspPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
        }
    }

    // 2018/05/07 QC#22482 Add End

    private static void editSvcUsgPrcListForModel(String glblCmpyCd, NSZC115001_svcUsgPrcListPMsgArray svcUsgPrcList, NSAL1320_ACMsg aBizMsg, Map<String, Object> mdlPrcHdr, List<Map<String, Object>> mdlPrcDtlList) {
        int ixP = svcUsgPrcList.getValidCount();
        String prcRateTpCd = getPrcRateTpCd(glblCmpyCd, aBizMsg, mdlPrcHdr);

        Map<String, String> prcListBandCdMap = new HashMap<String, String>();
        Map<String, String> prcBookMdseCdMap = new HashMap<String, String>();

        // START 2017/07/05 [QC#19479, ADD]
        BigDecimal contrXsCopyPkPrev = BigDecimal.ONE.negate();
        // END 2017/07/05 [QC#19479, ADD]

        for (int i = 0; i < mdlPrcDtlList.size(); i++) {
            Map<String, Object> mdlPrcDtl = mdlPrcDtlList.get(i);
            String xxFlgNm = (String) mdlPrcDtl.get("XX_FLG_NM");

            if (XX_FLG_PARENT.equals(xxFlgNm) || XX_FLG_HARD.equals(xxFlgNm)) {
                if (ZYPCommonFunc.hasValue((BigDecimal) mdlPrcHdr.get("PRC_MTR_PKG_PK")) //
                        && !isRateTypeAnnual(prcRateTpCd)) {
                    NSZC115001_svcUsgPrcListPMsg usgPMsg = svcUsgPrcList.no(ixP);

                    ZYPEZDItemValueSetter.setValue(usgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.shellLineNum, aBizMsg.shellLineNum);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.mdlId, (BigDecimal) mdlPrcDtl.get("MDL_ID"));
                    // usgPMsg.contrPhysBllgMtrRelnPk.clear();
                    ZYPEZDItemValueSetter.setValue(usgPMsg.dsContrPk, aBizMsg.dsContrPk_A);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.dsContrDtlPk, (BigDecimal) mdlPrcDtl.get("DS_CONTR_DTL_PK"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.prcListBandCd, (String) mdlPrcDtl.get("PRC_LIST_BAND_CD"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.prcBookMdseCd, (String) mdlPrcDtl.get("PRC_BOOK_MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMtrLbCd, (String) mdlPrcDtl.get("BLLG_MTR_LB_CD"));
                    if (XX_FLG_PARENT.equals(xxFlgNm)) {
                        ZYPEZDItemValueSetter.setValue(usgPMsg.usgMdseCd, (String) mdlPrcDtl.get("INTG_MDSE_CD"));
                    }
                    if (!ZYPCommonFunc.hasValue((String) mdlPrcHdr.get("PRC_TIER_SVC_OFFER_CD"))) {
                        ZYPEZDItemValueSetter.setValue(usgPMsg.copyInclPrcQty, (BigDecimal) mdlPrcDtl.get("XS_MTR_COPY_QTY"));
                    }
                    ZYPEZDItemValueSetter.setValue(usgPMsg.regMtrLbCd, (String) mdlPrcDtl.get("REG_MTR_LB_CD"));
                    if (!ZYPCommonFunc.hasValue((String) mdlPrcHdr.get("PRC_TIER_SVC_OFFER_CD"))) {
                        ZYPEZDItemValueSetter.setValue(usgPMsg.xsMtrAmtRate, (BigDecimal) mdlPrcDtl.get("XS_MTR_AMT_RATE"));
                    }
                    ZYPEZDItemValueSetter.setValue(usgPMsg.contrMtrMultRate, (BigDecimal) mdlPrcDtl.get("CONTR_MTR_MULT_RATE"));
                    // usgPMsg.prcSvcTierTpCd.clear();
                    // usgPMsg.minCopyVolCnt.clear();
                    // usgPMsg.maxCopyVolCnt.clear();
                    // usgPMsg.billToCustCd.clear();
                    // usgPMsg.sellToCustCd.clear();
                    ZYPEZDItemValueSetter.setValue(usgPMsg.actvFlg, ZYPConstant.FLG_ON_Y);
                    // usgPMsg.dsOrdPosnNum.clear();

                    String bllgMtrLbKey = createBllgMtrLbKey((BigDecimal) mdlPrcDtl.get("MDL_ID"), (String) mdlPrcDtl.get("BLLG_MTR_LB_CD"));
                    if (!prcListBandCdMap.containsKey(bllgMtrLbKey)) {
                        prcListBandCdMap.put(bllgMtrLbKey, usgPMsg.prcListBandCd.getValue());
                    }
                    if (!prcBookMdseCdMap.containsKey(bllgMtrLbKey)) {
                        prcBookMdseCdMap.put(bllgMtrLbKey, usgPMsg.prcBookMdseCd.getValue());
                    }

                    // 2018/05/09 QC#25030 add start
                    ZYPEZDItemValueSetter.setValue(usgPMsg.bllgFreeCopyCnt, (BigDecimal) mdlPrcDtl.get("BLLG_FREE_COPY_CNT"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMinCnt, (BigDecimal) mdlPrcDtl.get("BLLG_MIN_CNT"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMinAmtRate, (BigDecimal) mdlPrcDtl.get("BLLG_MIN_AMT_RATE"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.bllgRollOverRatio, (BigDecimal) mdlPrcDtl.get("BLLG_ROLL_OVER_RATIO"));
                    // 2018/05/09 QC#25030 add end

                    ixP++;
                }
            }

            if ((XX_FLG_PARENT.equals(xxFlgNm) && ZYPCommonFunc.hasValue((String) mdlPrcHdr.get("PRC_TIER_SVC_OFFER_CD"))) //
                    || XX_FLG_TIER.equals(xxFlgNm)) {
                if (ZYPCommonFunc.hasValue((BigDecimal) mdlPrcHdr.get("PRC_MTR_PKG_PK")) //
                        && !isRateTypeAnnual(prcRateTpCd)) {
                    NSZC115001_svcUsgPrcListPMsg usgPMsg = svcUsgPrcList.no(ixP);

                    // START 2017/07/05 [QC#19479, ADD]
                    BigDecimal contrXsCopyPk = (BigDecimal) mdlPrcDtl.get("CONTR_XS_COPY_PK");
                    if (ZYPCommonFunc.hasValue(contrXsCopyPkPrev) && contrXsCopyPkPrev.compareTo(contrXsCopyPk) == 0) {
                        continue;
                    }
                    // END 2017/07/05 [QC#19479, ADD]

                    String prcListBandCd = null;
                    String prcBookMdseCd = null;
                    String bllgMtrLbKey = createBllgMtrLbKey((BigDecimal) mdlPrcDtl.get("MDL_ID"), (String) mdlPrcDtl.get("BLLG_MTR_LB_CD"));
                    if (prcListBandCdMap.containsKey(bllgMtrLbKey)) {
                        prcListBandCd = prcListBandCdMap.get(bllgMtrLbKey);
                    }
                    if (prcBookMdseCdMap.containsKey(bllgMtrLbKey)) {
                        prcBookMdseCd = prcBookMdseCdMap.get(bllgMtrLbKey);
                    }

                    BigDecimal minCopyVolCnt = (BigDecimal) mdlPrcDtl.get("XS_MTR_COPY_QTY");
                    if (ZYPCommonFunc.hasValue(minCopyVolCnt)) {
                        minCopyVolCnt = minCopyVolCnt.add(BigDecimal.ONE);
                    }

                    ZYPEZDItemValueSetter.setValue(usgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.shellLineNum, aBizMsg.shellLineNum);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.mdlId, (BigDecimal) mdlPrcDtl.get("MDL_ID"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.contrPhysBllgMtrRelnPk, (BigDecimal) mdlPrcDtl.get("CONTR_PHYS_BLLG_MTR_RELN_PK"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.dsContrPk, aBizMsg.dsContrPk_A);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.dsContrDtlPk, (BigDecimal) mdlPrcDtl.get("DS_CONTR_DTL_PK"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.prcListBandCd, prcListBandCd);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.prcBookMdseCd, prcBookMdseCd);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMtrLbCd, (String) mdlPrcDtl.get("BLLG_MTR_LB_CD"));
                    // usgPMsg.usgMdseCd.clear();
                    // usgPMsg.copyInclPrcQty.clear();
                    // usgPMsg.regMtrLbCd.clear();
                    ZYPEZDItemValueSetter.setValue(usgPMsg.xsMtrAmtRate, (BigDecimal) mdlPrcDtl.get("XS_MTR_AMT_RATE"));
                    // usgPMsg.contrMtrMultRate.clear();
                    ZYPEZDItemValueSetter.setValue(usgPMsg.prcSvcTierTpCd, (String) mdlPrcDtl.get("PRC_SVC_TIER_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.minCopyVolCnt, minCopyVolCnt);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.maxCopyVolCnt, (BigDecimal) mdlPrcDtl.get("MAX_COPY_VOL_CNT"));
                    // usgPMsg.billToCustCd.clear();
                    // usgPMsg.sellToCustCd.clear();
                    ZYPEZDItemValueSetter.setValue(usgPMsg.actvFlg, ZYPConstant.FLG_ON_Y);
                    // usgPMsg.dsOrdPosnNum.clear();

                    // START 2017/07/05 [QC#19479, ADD]
                    contrXsCopyPkPrev = (BigDecimal) mdlPrcDtl.get("CONTR_XS_COPY_PK");
                    // END 2017/07/05 [QC#19479, ADD]

                    ixP++;
                }
            }
        }

        svcUsgPrcList.setValidCount(ixP);
    }

    private static void editSvcUsgPrcListForNewConfig(String glblCmpyCd, NSZC115001_svcUsgPrcListPMsgArray svcUsgPrcList, NSAL1320_ACMsg aBizMsg, NSAL1320_BCMsg bBizMsg, Map<String, Object> mdlPrcHdr, List<Map<String, Object>> mdlPrcDtlList) {
        int ixP = svcUsgPrcList.getValidCount();
        String prcRateTpCd = getPrcRateTpCd(glblCmpyCd, aBizMsg, mdlPrcHdr);

        Map<String, String> prcListBandCdMap = new HashMap<String, String>();
        Map<String, String> prcBookMdseCdMap = new HashMap<String, String>();
        // START 2017/07/05 [QC#19479, ADD]
        BigDecimal contrXsCopyPkPrev = BigDecimal.ONE.negate();
        // END 2017/07/05 [QC#19479, ADD]

        for (int i = 0; i < mdlPrcDtlList.size(); i++) {
            Map<String, Object> mdlPrcDtl = mdlPrcDtlList.get(i);
            String xxFlgNm = (String) mdlPrcDtl.get("XX_FLG_NM");

            if (XX_FLG_PARENT.equals(xxFlgNm) || XX_FLG_HARD.equals(xxFlgNm)) {
                if (ZYPCommonFunc.hasValue((BigDecimal) mdlPrcHdr.get("PRC_MTR_PKG_PK")) //
                        && !isRateTypeAnnual(prcRateTpCd)) {
                    NSZC115001_svcUsgPrcListPMsg usgPMsg = svcUsgPrcList.no(ixP);

                    ZYPEZDItemValueSetter.setValue(usgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.shellLineNum, aBizMsg.shellLineNum);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.mdlId, (BigDecimal) mdlPrcDtl.get("MDL_ID"));
                    // usgPMsg.contrPhysBllgMtrRelnPk.clear();
                    ZYPEZDItemValueSetter.setValue(usgPMsg.dsContrPk, aBizMsg.dsContrPk_A);
                    // usgPMsg.dsContrDtlPk.clear();
                    ZYPEZDItemValueSetter.setValue(usgPMsg.prcListBandCd, (String) mdlPrcDtl.get("PRC_LIST_BAND_CD"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.prcBookMdseCd, (String) mdlPrcDtl.get("PRC_BOOK_MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMtrLbCd, (String) mdlPrcDtl.get("BLLG_MTR_LB_CD"));
                    if (XX_FLG_PARENT.equals(xxFlgNm)) {
                        ZYPEZDItemValueSetter.setValue(usgPMsg.usgMdseCd, (String) mdlPrcDtl.get("INTG_MDSE_CD"));
                    }
                    if (!ZYPCommonFunc.hasValue((String) mdlPrcHdr.get("PRC_TIER_SVC_OFFER_CD"))) {
                        ZYPEZDItemValueSetter.setValue(usgPMsg.copyInclPrcQty, (BigDecimal) mdlPrcDtl.get("XS_MTR_COPY_QTY"));
                    }
                    ZYPEZDItemValueSetter.setValue(usgPMsg.regMtrLbCd, (String) mdlPrcDtl.get("REG_MTR_LB_CD"));
                    if (!ZYPCommonFunc.hasValue((String) mdlPrcHdr.get("PRC_TIER_SVC_OFFER_CD"))) {
                        ZYPEZDItemValueSetter.setValue(usgPMsg.xsMtrAmtRate, (BigDecimal) mdlPrcDtl.get("XS_MTR_AMT_RATE"));
                    }
                    ZYPEZDItemValueSetter.setValue(usgPMsg.contrMtrMultRate, (BigDecimal) mdlPrcDtl.get("CONTR_MTR_MULT_RATE"));
                    // usgPMsg.prcSvcTierTpCd.clear();
                    // usgPMsg.minCopyVolCnt.clear();
                    // usgPMsg.maxCopyVolCnt.clear();
                    ZYPEZDItemValueSetter.setValue(usgPMsg.billToCustCd, (String) mdlPrcDtl.get("BILL_TO_CUST_CD"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.sellToCustCd, (String) mdlPrcDtl.get("SELL_TO_CUST_CD"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.actvFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.dsOrdPosnNum, bBizMsg.dsOrdPosnNum);

                    String bllgMtrLbKey = createBllgMtrLbKey((BigDecimal) mdlPrcDtl.get("MDL_ID"), (String) mdlPrcDtl.get("BLLG_MTR_LB_CD"));
                    if (!prcListBandCdMap.containsKey(bllgMtrLbKey)) {
                        prcListBandCdMap.put(bllgMtrLbKey, usgPMsg.prcListBandCd.getValue());
                    }
                    if (!prcBookMdseCdMap.containsKey(bllgMtrLbKey)) {
                        prcBookMdseCdMap.put(bllgMtrLbKey, usgPMsg.prcBookMdseCd.getValue());
                    }
                    // 2018/05/09 QC#25030 add start
                    ZYPEZDItemValueSetter.setValue(usgPMsg.bllgFreeCopyCnt, (BigDecimal) mdlPrcDtl.get("BLLG_FREE_COPY_CNT"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMinCnt, (BigDecimal) mdlPrcDtl.get("BLLG_MIN_CNT"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMinAmtRate, (BigDecimal) mdlPrcDtl.get("BLLG_MIN_AMT_RATE"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.bllgRollOverRatio, (BigDecimal) mdlPrcDtl.get("BLLG_ROLL_OVER_RATIO"));
                    // 2018/05/09 QC#25030 add end

                    ixP++;
                }
            }

            if ((XX_FLG_PARENT.equals(xxFlgNm) && ZYPCommonFunc.hasValue((String) mdlPrcHdr.get("PRC_TIER_SVC_OFFER_CD"))) //
                    || XX_FLG_TIER.equals(xxFlgNm)) {
                if (ZYPCommonFunc.hasValue((BigDecimal) mdlPrcHdr.get("PRC_MTR_PKG_PK")) //
                        && !isRateTypeAnnual(prcRateTpCd)) {
                    NSZC115001_svcUsgPrcListPMsg usgPMsg = svcUsgPrcList.no(ixP);

                    // START 2017/07/05 [QC#19479, ADD]
                    BigDecimal contrXsCopyPk = (BigDecimal) mdlPrcDtl.get("CONTR_XS_COPY_PK");
                    if (ZYPCommonFunc.hasValue(contrXsCopyPkPrev) && contrXsCopyPkPrev.compareTo(contrXsCopyPk) == 0) {
                        continue;
                    }
                    // END 2017/07/05 [QC#19479, ADD]

                    String prcListBandCd = null;
                    String prcBookMdseCd = null;
                    String bllgMtrLbKey = createBllgMtrLbKey((BigDecimal) mdlPrcDtl.get("MDL_ID"), (String) mdlPrcDtl.get("BLLG_MTR_LB_CD"));
                    if (prcListBandCdMap.containsKey(bllgMtrLbKey)) {
                        prcListBandCd = prcListBandCdMap.get(bllgMtrLbKey);
                    }
                    if (prcBookMdseCdMap.containsKey(bllgMtrLbKey)) {
                        prcBookMdseCd = prcBookMdseCdMap.get(bllgMtrLbKey);
                    }

                    BigDecimal minCopyVolCnt = (BigDecimal) mdlPrcDtl.get("XS_MTR_COPY_QTY");
                    if (ZYPCommonFunc.hasValue(minCopyVolCnt)) {
                        minCopyVolCnt = minCopyVolCnt.add(BigDecimal.ONE);
                    }

                    ZYPEZDItemValueSetter.setValue(usgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.shellLineNum, aBizMsg.shellLineNum);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.mdlId, (BigDecimal) mdlPrcDtl.get("MDL_ID"));
                    // usgPMsg.contrPhysBllgMtrRelnPk.clear();
                    ZYPEZDItemValueSetter.setValue(usgPMsg.dsContrPk, aBizMsg.dsContrPk_A);
                    // usgPMsg.dsContrDtlPk.clear();
                    ZYPEZDItemValueSetter.setValue(usgPMsg.prcListBandCd, prcListBandCd);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.prcBookMdseCd, prcBookMdseCd);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMtrLbCd, (String) mdlPrcDtl.get("BLLG_MTR_LB_CD"));
                    // usgPMsg.usgMdseCd.clear();
                    // usgPMsg.copyInclPrcQty.clear();
                    // usgPMsg.regMtrLbCd.clear();
                    ZYPEZDItemValueSetter.setValue(usgPMsg.xsMtrAmtRate, (BigDecimal) mdlPrcDtl.get("XS_MTR_AMT_RATE"));
                    // usgPMsg.contrMtrMultRate.clear();
                    ZYPEZDItemValueSetter.setValue(usgPMsg.prcSvcTierTpCd, (String) mdlPrcDtl.get("PRC_SVC_TIER_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(usgPMsg.minCopyVolCnt, minCopyVolCnt);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.maxCopyVolCnt, (BigDecimal) mdlPrcDtl.get("MAX_COPY_VOL_CNT"));
                    // usgPMsg.billToCustCd.clear();
                    // usgPMsg.sellToCustCd.clear();
                    ZYPEZDItemValueSetter.setValue(usgPMsg.actvFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(usgPMsg.dsOrdPosnNum, bBizMsg.dsOrdPosnNum);

                    // START 2017/07/05 [QC#19479, ADD]
                    contrXsCopyPkPrev = (BigDecimal) mdlPrcDtl.get("CONTR_XS_COPY_PK");
                    // END 2017/07/05 [QC#19479, ADD]

                    ixP++;
                }
            }
        }

        svcUsgPrcList.setValidCount(ixP);
    }

    private static boolean isMdlPrcNoEntry(String glblCmpyCd, NSAL1320_ACMsg aBizMsg, Map<String, Object> mdlPrcHdr, String usgBllgCycleCd) {
        String prcCatgCd = (String) mdlPrcHdr.get("PRC_CATG_CD");
        BigDecimal basePrcDealAmt = (BigDecimal) mdlPrcHdr.get("BASE_PRC_DEAL_AMT");
        // START 2017/09/01 [QC#20773, DEL]
        // if (basePrcDealAmt.compareTo(BigDecimal.ZERO) == 0) {
        // basePrcDealAmt = null;
        // }
        // END 2017/09/01 [QC#20773, DEL]
        // START 2017/10/11 [QC#20059, ADD];
        if (!ZYPCommonFunc.hasValue(prcCatgCd)) {
            // Config Level Price Setting
            return true;
        }
        // END 2017/10/11 [QC#20059, ADD]
        String prcTierSvcOfferCd = (String) mdlPrcHdr.get("PRC_TIER_SVC_OFFER_CD");
        String prcRateTpCd = getPrcRateTpCd(glblCmpyCd, aBizMsg, mdlPrcHdr);

        if (!ZYPCommonFunc.hasValue(usgBllgCycleCd)) { // Non-Meter
            if (ZYPCommonFunc.hasValue(prcCatgCd)) {
                return false;
            }
            if (ZYPCommonFunc.hasValue(basePrcDealAmt)) {
                return false;
            }
            return true;
        }

        if (ZYPCommonFunc.hasValue(prcRateTpCd) //
                && !isRateTypeAnnual(prcRateTpCd)) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(prcTierSvcOfferCd)) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(basePrcDealAmt)) {
            return false;
        }
        return true;
    }

    private static String getPrcRateTpCd(String glblCmpyCd, NSAL1320_ACMsg aBizMsg, Map<String, Object> mdlPrcHdr) {
        if (DS_CONTR_CATG.FLEET.equals(aBizMsg.dsContrCatgCd.getValue())) {
            String prcListTpCd = (String) mdlPrcHdr.get("PRC_LIST_TP_CD");
            return getPrcRateTpCdForFleet(glblCmpyCd, prcListTpCd, aBizMsg.usgBllgCycleCd.getValue());
        } else {
            String prcCatgCd = (String) mdlPrcHdr.get("PRC_CATG_CD");
            BigDecimal mdlId = (BigDecimal) mdlPrcHdr.get("MDL_ID");
            BigDecimal prcMtrPkgPk = (BigDecimal) mdlPrcHdr.get("PRC_MTR_PKG_PK");
            String prcSvcPlnTpCd = aBizMsg.prcSvcPlnTpCd.getValue();
            String prcSvcContrTpCd = aBizMsg.prcSvcContrTpCd.getValue();
            BigDecimal termMthNum = aBizMsg.termMthAot.getValue();
            return getPrcRateTpCd(prcCatgCd, mdlId, prcMtrPkgPk, prcSvcPlnTpCd, prcSvcContrTpCd, termMthNum);
        }
    }

    private static String getPrcRateTpCdForFleet(String glblCmpyCd, String prcListTpCd, String usgBllgCycleCd) {
        String prcListStruTpCd = getPrcListStruTp(glblCmpyCd, prcListTpCd);

        String prcRateTpCd = null;

        if (!ZYPCommonFunc.hasValue(usgBllgCycleCd) //
                && !PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTpCd) //
                && !PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(prcListStruTpCd)) {
            prcRateTpCd = PRC_RATE_TP.ANNUAL;
        }

        return prcRateTpCd;
    }

    private static String getPrcListStruTp(String glblCmpyCd, String prcListTpCd) {
        PRC_LIST_TPTMsg tMsg = new PRC_LIST_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcListTpCd, prcListTpCd);

        tMsg = (PRC_LIST_TPTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return null;
        }
        return tMsg.prcListStruTpCd.getValue();
    }

    private static String getPrcRateTpCd(String prcCatgCd, BigDecimal mdlId, BigDecimal prcMtrPkgPk, String prcSvcPlnTpCd, String prcSvcContrTpCd, BigDecimal termMthNum) {
        S21SsmEZDResult rslt = NSAL1320Query.getInstance().getPrcRateTpCd(prcCatgCd, mdlId, prcMtrPkgPk, prcSvcPlnTpCd, prcSvcContrTpCd, termMthNum);
        if (rslt.isCodeNotFound()) {
            return "";
        }
        String prcRateTpCd = (String) rslt.getResultObject();

        return prcRateTpCd;
    }

    private static boolean isRateTypeAnnual(String prcRateTpCd) {
        return PRC_RATE_TP.ANNUAL.equals(prcRateTpCd);
    }

    private static String createShellLineKey(BigDecimal shellLineNum, BigDecimal mdlId, String dsContrCatgCd) {
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            if (shellLineNum == null) {
                return null;
            }
            return ZYPCommonFunc.leftPad(String.valueOf(shellLineNum), 10, "0");
        } else {
            if (shellLineNum == null || mdlId == null) {
                return null;
            }
            String key = ZYPCommonFunc.leftPad(String.valueOf(shellLineNum), 10, "0");
            key += ZYPCommonFunc.leftPad(String.valueOf(mdlId), 22, "0");
            return key;
        }
    }

    private static String createBllgMtrLbKey(BigDecimal mdlId, String bllgMtrLbCd) {
        if (mdlId == null || bllgMtrLbCd == null) {
            return null;
        }
        String key = ZYPCommonFunc.leftPad(String.valueOf(mdlId), 22, "0");
        key += ZYPCommonFunc.leftPad(bllgMtrLbCd, 2, "0");
        return key;
    }

    // END 2017/06/20 K.Kojima [QC#19053,ADD]

    // START 2017/08/18 [QC#20651, ADD]
    /**
     * <pre>
     * @param bizMsg    NSAL1320CMsg
     * @return  if error then return true.
     * </pre>
     */
    public static boolean checkMtrReadMeth(String glblCmpyCd, NSAL1320CMsg bizMsg) {
        boolean hasErr = false;
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NSAL1320_BCMsg bBizMsg = bizMsg.B.no(i);
            if (!hasUsgBllgCycle(bBizMsg.shellLineNum_B.getValue(), bizMsg)) {
                continue;
            }

            if (isAddAsry(bBizMsg.shellLineNum_B.getValue(), bizMsg)) {
                continue;
            }

            // 2018/05/07 QC#22482 Add Start
            if (isManOvrd(bBizMsg, bizMsg)) {
                continue;
            }
            // 2018/05/07 QC#22482 Add End

            DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
            ZYPEZDItemValueSetter.setValue(dsMdlTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsMdlTMsg.mdlId, bBizMsg.mdlId);
            dsMdlTMsg = (DS_MDLTMsg) S21FastTBLAccessor.findByKey(dsMdlTMsg);
            if (dsMdlTMsg == null) {
                bBizMsg.mtrReadMethCd.setErrorInfo(1, NSAM0627E, new String[] {"DS Model" });
                hasErr = true;
                continue;
            }

            // QC#29248
            // if (ZYPCommonFunc.hasValue(dsMdlTMsg.mtrGrpPk) &&
            // !ZYPCommonFunc.hasValue(bBizMsg.mtrReadMethCd)) {
            // bBizMsg.mtrReadMethCd.setErrorInfo(1, ZZM9000E, new
            // String[] {"Meter Method"});
            // hasErr = true;
            // }
        }
        return hasErr;
    }

    private static boolean isAddAsry(BigDecimal shellLineNum, NSAL1320CMsg bizMsg) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(aBizMsg.shellLineNum)) {
                continue;
            }
            if (shellLineNum.compareTo(aBizMsg.shellLineNum.getValue()) == 0) {
                return ZYPConstant.FLG_ON_Y.equals(aBizMsg.addAsryFlg.getValue());
            }
        }
        return false;
    }

    private static boolean hasUsgBllgCycle(BigDecimal shellLineNum, NSAL1320CMsg bizMsg) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(aBizMsg.shellLineNum)) {
                continue;
            }
            if (shellLineNum.compareTo(aBizMsg.shellLineNum.getValue()) == 0) {
                return ZYPCommonFunc.hasValue(aBizMsg.usgBllgCycleCd);
            }
        }
        return true;
    }

    // END 2017/08/18 [QC#20651, ADD]

    // START 2017/09/26 [QC#21154, ADD]
    /**
     * <pre>
     * @param aBizMsg   NSAL1320_ACMsg
     * @param bizMsg    NSAL1320CMsg
     * </pre>
     */
    public static void setSoldToCustomerInfo(NSAL1320_ACMsg aBizMsg, NSAL1320CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NSAL1320Query.getInstance().getSoldToCustInfo(aBizMsg, bizMsg);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        List<Map<String, String>> rsList = (List<Map<String, String>>) ssmResult.getResultObject();
        if (rsList == null || rsList.size() == 0) {
            return;
        }

        for (Map<String, String> rsMap : rsList) {
            ZYPEZDItemValueSetter.setValue(aBizMsg.dsAcctNum, rsMap.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.dsAcctNm, rsMap.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.soldToCustLocCd, rsMap.get("SOLD_TO_CUST_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxGenlFldAreaTxt_BI, rsMap.get("BILL_TO_SRCH_TXT"));
            break;
        }
    }

    // END 2017/09/26 [QC#21154, ADD]

    // Add Start 2017/10/17 QC#21860
    private static GLBL_CMPYTMsg getGlblCmpy(String glblCmpyCd) {
        GLBL_CMPYTMsg inTMsg = new GLBL_CMPYTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        GLBL_CMPYTMsg outTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private static int getDealCcyDigit(String glblCmpyCd, String dealCcyCd) {
        CCYTMsg ccyTMsg = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(ccyTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ccyTMsg.ccyCd, dealCcyCd);
        ccyTMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(ccyTMsg);
        if (ccyTMsg != null) {
            return ccyTMsg.aftDeclPntDigitNum.getValueInt();
        }
        return 0;
    }

    private static BigDecimal round(BigDecimal num, int digit) {
        if (num == null) {
            num = BigDecimal.ZERO;
        }
        return num.setScale(digit, BigDecimal.ROUND_HALF_UP);
    }

    // Add End 2017/10/17 QC#21860

    // START 2017/10/24 [QC#21556, ADD]
    /**
     * setHeaderInfoFromContrNum
     * @param bizMsg NSAL1320CMsg
     */
    public static void setHeaderInfoFromContrNum(NSAL1320CMsg bizMsg) {
        int ixA = bizMsg.xxCellIdx.getValueInt();
        NSAL1320_ACMsg aBizMsg = bizMsg.A.no(ixA);
        deriveHeaderInfoFromContrNum(bizMsg, aBizMsg);
    }

    /**
     * checkTermFromTo
     * @param bizMsg NSAL1320CMsg
     * @return if error then return true.
     */
    public static boolean checkTermFromTo(NSAL1320CMsg bizMsg) {
        boolean hasErr = false;
        for (int idxA = 0; idxA < bizMsg.A.getValidCount(); idxA++) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(idxA);

            if (ZYPConstant.FLG_ON_Y.equals(aBizMsg.contrAvalFlg_A.getValue())) {
                continue;
            }
            if (!DS_CONTR_CATG.FLEET.equals(aBizMsg.dsContrCatgCd.getValue())) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(aBizMsg.dsContrPk_AD)) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(aBizMsg.fromPerMthNum) || !ZYPCommonFunc.hasValue(aBizMsg.toPerMthNum)) {
                continue;
            }
            // 2018/05/07 QC#22482 Add Start
            if (isManOvrd(aBizMsg)) {
                continue;
            }
            // 2018/05/07 QC#22482 Add End

            for (int idxB = 0; idxB < bizMsg.B.getValidCount(); idxB++) {
                NSAL1320_BCMsg bBizMsg = bizMsg.B.no(idxB);
                if (!ZYPCommonFunc.hasValue(bBizMsg.shellLineNum_B)) {
                    continue;
                }
                if (aBizMsg.shellLineNum.getValue().compareTo(bBizMsg.shellLineNum_B.getValue()) != 0) {
                    continue;
                }

                boolean checkResult = false;
                if (ZYPConstant.FLG_ON_Y.equals(aBizMsg.addAsryFlg.getValue())) {
                    // add accessory
                    checkResult = checkTermFromToForAddAccessory(bizMsg, aBizMsg, bBizMsg);
                } else {
                    // add machine
                    checkResult = checkTermFromToForAddMachine(bizMsg, aBizMsg, bBizMsg);
                }

                if (checkResult) {
                    hasErr = true;
                }
            }
        }

        return hasErr;
    }

    public static boolean checkTermFromToForAddAccessory(NSAL1320CMsg bizMsg, NSAL1320_ACMsg aBizMsg, NSAL1320_BCMsg bBizMsg) {
        S21SsmEZDResult ssmResult = NSAL1320Query.getInstance().getContrTermInfo(bizMsg, aBizMsg, bBizMsg);
        if (ssmResult.isCodeNotFound()) {
            bBizMsg.xxChkBox_B.setErrorInfo(1, ZZZM9006E, new String[] {"Main Machine" });
            return true;
        }
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();

        BigDecimal toPerMthNumContr = null;
        BigDecimal toPerMthNumMach = null;
        for (Map<String, Object> rs : rsltList) {
            toPerMthNumContr = (BigDecimal) rs.get("TERM_MTH_NUM");
            if (ZYPDateUtil.compare(ZYPDateUtil.getSalesDate(), (String) rs.get("CONTR_VRSN_EFF_FROM_DT")) > 0) {
                toPerMthNumContr = (BigDecimal) rs.get("TERM_MTH_NUM_RMNG");
            }

            toPerMthNumMach = (BigDecimal) rs.get("TERM_MTH_NUM_MACH");
            if (ZYPDateUtil.compare(ZYPDateUtil.getSalesDate(), (String) rs.get("CONTR_EFF_FROM_DT")) > 0) {
                toPerMthNumMach = (BigDecimal) rs.get("TERM_MTH_NUM_MACH_RMNG");
            }
            break;
        }

        BigDecimal toPerMthNumAcc = aBizMsg.toPerMthNum.getValue();
        if (toPerMthNumAcc.compareTo(toPerMthNumMach) > 0) {
            aBizMsg.toPerMthNum.setErrorInfo(1, NSAM0347E, new String[] {"Term To", "Machine Effective Thru Date" });
            bBizMsg.xxChkBox_B.setErrorInfo(1, NSAM0347E, new String[] {"Term To", "Machine Effective Thru Date" });
        }
        if (DS_CONTR_CATG.FLEET.equals(aBizMsg.dsContrCatgCd.getValue()) && toPerMthNumAcc.compareTo(toPerMthNumContr) > 0) {
            aBizMsg.toPerMthNum.setErrorInfo(1, NSAM0347E, new String[] {"Term To", "Contract Effective Thru Date" });
            bBizMsg.xxChkBox_B.setErrorInfo(1, NSAM0347E, new String[] {"Term To", "Contract Effective Thru Date" });
            return true;
        }

        return false;
    }

    public static boolean checkTermFromToForAddMachine(NSAL1320CMsg bizMsg, NSAL1320_ACMsg aBizMsg, NSAL1320_BCMsg bBizMsg) {
        S21SsmEZDResult ssmResult = NSAL1320Query.getInstance().getHeaderInfoFromContrNum(aBizMsg, bizMsg);
        if (ssmResult.isCodeNotFound()) {
            aBizMsg.dsContrNum_AD.setErrorInfo(1, ZZZM9006E, new String[] {"Contract" });
            return true;
        }
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();

        BigDecimal toPerMthNumContr = null;
        for (Map<String, Object> rs : rsltList) {
            toPerMthNumContr = (BigDecimal) rs.get("TERM_MTH_NUM");
            if (ZYPDateUtil.compare(ZYPDateUtil.getSalesDate(), (String) rs.get("CONTR_VRSN_EFF_FROM_DT")) > 0) {
                toPerMthNumContr = (BigDecimal) rs.get("TERM_MTH_NUM_RMNG");
            }
            break;
        }

        BigDecimal toPerMthNumMach = aBizMsg.toPerMthNum.getValue();
        if (DS_CONTR_CATG.FLEET.equals(aBizMsg.dsContrCatgCd.getValue()) && toPerMthNumMach.compareTo(toPerMthNumContr) > 0) {
            aBizMsg.toPerMthNum.setErrorInfo(1, NSAM0347E, new String[] {"Term To", "Contract Effective Thru Date" });
            bBizMsg.xxChkBox_B.setErrorInfo(1, NSAM0347E, new String[] {"Term To", "Contract Effective Thru Date" });
            return true;
        }

        return false;
    }

    // END 2017/10/24 [QC#21556, ADD]
    // 2018/04/18 QC#21919 Add Start
    private static String getBrDescTxt(String glblCmpyCd, Map<String, Object> rsltMap) {

        String postCd = getShipToPostCd(glblCmpyCd, (String) rsltMap.get("SHIP_TO_CUST_LOC_CD"));
        String svcLineBizCd = getSvcByLineBizTpCd(glblCmpyCd, (String) rsltMap.get("CPO_ORD_NUM"), (String) rsltMap.get("CPO_DTL_LINE_NUM"), (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM"), (String) rsltMap.get("MDSE_CD"));

        NSXC002001GetBrCdBean bean = new NSXC002001GetBrCdBean();
        bean.setGlblCmpyCd(glblCmpyCd);
        bean.setPostCd(postCd);
        bean.setSvcLineBizCd(svcLineBizCd);

        NSXC002001GetBrCd.getBrCd(bean);
        return bean.getSvcBrCdDescTxt();
    }

    private static String getShipToPostCd(String glblCmpyCd, String shipToCustCd) {

        SHIP_TO_CUSTTMsg prmTMsg = new SHIP_TO_CUSTTMsg();
        prmTMsg.setSQLID("004");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        SHIP_TO_CUSTTMsgArray shipToCustArray = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(prmTMsg);

        if (shipToCustArray.length() > 0) {
            return shipToCustArray.no(0).postCd.getValue();
        }

        return null;
    }

    private static String getSvcByLineBizTpCd(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum, String mdseCd) {

        String rtnLineBizTpCd = null;

        S21SsmEZDResult ssmResult = NSAL1320Query.getInstance().getIstlInfo(glblCmpyCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);

        if (!ssmResult.isCodeNotFound()) {
            List<Map<String, Object>> dsCpoIstlInfoList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (Map<String, Object> dsCpoIstlInfo : dsCpoIstlInfoList) {
                rtnLineBizTpCd = (String) dsCpoIstlInfo.get("ISTL_DIV_CD");
                if (hasValue(rtnLineBizTpCd)) {
                    break;
                }
            }
        }

        if (!hasValue(rtnLineBizTpCd)) {
            MDSETMsg mdseTMsg = findMdse(glblCmpyCd, mdseCd);
            if (mdseTMsg != null) {
                rtnLineBizTpCd = mdseTMsg.lineBizTpCd.getValue();
            }
        }

        return NSAL1320Query.getInstance().convLineBizTpToSvcLineBiz(glblCmpyCd, rtnLineBizTpCd);
    }

    private static MDSETMsg findMdse(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        mdseTMsg = (MDSETMsg) S21FastTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {

            ORD_TAKE_MDSETMsg ordTakeMdseMsg = new ORD_TAKE_MDSETMsg();
            ordTakeMdseMsg.setSQLID("002");
            ordTakeMdseMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            ordTakeMdseMsg.setConditionValue("ordTakeMdseCd01", mdseCd);

            ORD_TAKE_MDSETMsgArray ordTakeMdseMsgArray = (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(ordTakeMdseMsg);
            if (ordTakeMdseMsgArray != null && ordTakeMdseMsgArray.getValidCount() > 0) {
                mdseTMsg = new MDSETMsg();
                ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, ordTakeMdseMsgArray.no(0).mdseCd);

                mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
            }
        }

        return mdseTMsg;
    }

    // 2018/04/18 QC#21919 Add End
    // 2018/05/07 QC#22482 Add Start
    private static boolean containsManOvrd(NSAL1320CMsg bizMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(i);
            if (isManOvrd(aBizMsg)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isManOvrd(NSAL1320_BCMsg bBizMsg, NSAL1320CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bBizMsg.shellLineNum_B)) {
            return false;
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(aBizMsg.shellLineNum)) {
                continue;
            }
            if (bBizMsg.shellLineNum_B.getValue().compareTo(aBizMsg.shellLineNum.getValue()) == 0) {
                return isManOvrd(aBizMsg);
            }
        }
        return false;
    }

    private static boolean isManOvrd(NSAL1320_ACMsg aBizMsg) {

        return ZYPConstant.FLG_ON_Y.equals(aBizMsg.manContrOvrdFlg.getValue());
    }

    // 2018/05/07 QC#22482 Add End

    // START 2018/07/18 K.Kitachi [QC#26589, ADD]
    /**
     * <pre>
     * @param glblCmpyCd String
     * @param mdseCdItem EZDCStringItem
     * @param prcSvcContrTpCdItem EZDCStringItem
     * </pre>
     */
    public static void deriveFromContrTp(String glblCmpyCd, EZDCStringItem mdseCdItem, EZDCStringItem prcSvcContrTpCdItem) {
        if (!ZYPCommonFunc.hasValue(mdseCdItem)) {
            return;
        }
        SVC_PGM_MDSE_MAPTMsg inMsg = new SVC_PGM_MDSE_MAPTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcPgmMdseCd01", mdseCdItem.getValue());
        inMsg.setConditionValue("enblFlg01", ZYPConstant.FLG_ON_Y);
        SVC_PGM_MDSE_MAPTMsgArray outArray = (SVC_PGM_MDSE_MAPTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            prcSvcContrTpCdItem.clear();
            return;
        }
        ZYPEZDItemValueSetter.setValue(prcSvcContrTpCdItem, outArray.no(0).prcSvcContrTpCd);
    }

    // END 2018/07/18 K.Kitachi [QC#26589, ADD]

    // START 2019/11/29 K.Kitachi [QC#53682, ADD]
    /**
     * <pre>
     * checkAddToContrAccReln
     * @param  bizMsg NSAL1320CMsg
     * @return if error then return true.
     * </pre>
     */
    public static boolean checkAddToContrAccReln(NSAL1320CMsg bizMsg) {
        boolean isError = false;
        for (int aBizMsgIdx = 0; aBizMsgIdx < bizMsg.A.getValidCount(); aBizMsgIdx++) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(aBizMsgIdx);
            if (!ZYPConstant.FLG_ON_Y.equals(aBizMsg.addAsryFlg.getValue())) {
                continue;
            }
            if (!hasValue(aBizMsg.dsContrPk_AD)) {
                continue;
            }
            for (int bBizMsgIdx = 0; bBizMsgIdx < bizMsg.B.getValidCount(); bBizMsgIdx++) {
                NSAL1320_BCMsg bBizMsg = bizMsg.B.no(bBizMsgIdx);
                if (aBizMsg.shellLineNum.getValue().compareTo(bBizMsg.shellLineNum_B.getValue()) != 0) {
                    continue;
                }
                boolean existReln = NSAL1320Query.getInstance().existAddToContrAccReln(bizMsg, aBizMsg, bBizMsg);
                if (!existReln) {
                    bBizMsg.xxChkBox_B.setErrorInfo(1, NSAM0755E);
                    isError = true;
                }
            }
        }
        return isError;
    }

    // END 2019/11/29 K.Kitachi [QC#53682, ADD]

    // 2020/05/27 QC#55922 Add Start
    private static boolean isClearUsageBillingCycle(String glblCmpyCd, NSAL1320_ACMsg bizMsg) {
        // Check Selected Result of BillgCycle
        if (ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd.getValue())) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.dsContrPk_A)) {
            return false;
        }
        // Check ContrBllgMtr
        int count = NSAL1320Query.getInstance().countDsContrBllgMtr(bizMsg.dsContrPk_A.getValue());
        if (count > 0) {
            return true;
        }
        return false;
    }

    // 2020/05/27 QC#55922 Add End

    // 2023/11/06 QC#61924 Add Start
    /**
     * hasDeactivateAccountOrLocation
     * @param glblCmpyCd String
     * @param bizMsg NSAL1320CMsg
     * @param glblMsg NSAL1320SMsg
     * @return boolean
     */
    public static boolean hasDeactivateAccountOrLocation(String glblCmpyCd, NSAL1320CMsg bizMsg, NSAL1320SMsg glblMsg) {

        boolean errorFlg = false;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            SELL_TO_CUSTTMsg lcSellToCustTMsg = new SELL_TO_CUSTTMsg();
            lcSellToCustTMsg = getSellToCustByCondition(glblCmpyCd, bizMsg.A.no(i).dsAcctNum.getValue());
            if (null != lcSellToCustTMsg) {
                if (ZYPConstant.FLG_ON_Y.equals(lcSellToCustTMsg.deacNewTrxFlg.getValue())) {
                    bizMsg.A.no(i).dsAcctNum.setErrorInfo(1, NSAM0782E, new String[] {bizMsg.A.no(i).dsAcctNum.getValue() });
                    errorFlg = true;
                }
            }

            BILL_TO_CUSTTMsg lcBillToCustTMsg = new BILL_TO_CUSTTMsg();
            lcBillToCustTMsg = getBillToCustByCondition(glblCmpyCd, bizMsg.A.no(i).soldToCustLocCd.getValue());
            if (null != lcBillToCustTMsg) {
                if (ZYPConstant.FLG_ON_Y.equals(lcBillToCustTMsg.deacNewTrxFlg.getValue())) {
                    bizMsg.A.no(i).soldToCustLocCd.setErrorInfo(1, NSAM0781E, new String[] {bizMsg.A.no(i).soldToCustLocCd.getValue() });
                    errorFlg = true;
                }
            }
        }

        if (errorFlg) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * <pre>
     * get Sell To Customer
     * @param glblCmpyCd Global Company Code
     * @param sellToCustCd Sell To Customer Code
     * @return SELL_TO_CUSTTMsg, if system can't find SELL_TO_CUST record, return null
     * </pre>
     */
    private static SELL_TO_CUSTTMsg getSellToCustByCondition(String glblCmpyCd, String sellToCustCd) {

        SELL_TO_CUSTTMsg condition = new SELL_TO_CUSTTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("sellToCustCd01", sellToCustCd);

        SELL_TO_CUSTTMsgArray tmsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0);
        } else {
            return null;
        }
    }

    /**
     * <pre>
     * get Bill To Customer
     * @param glblCmpyCd Global Company Code
     * @param billToCustCd Bill To Customer Code
     * @return BILL_TO_CUSTTMsg, if system can't find BILL_TO_CUST record, return null
     * </pre>
     */
    private static BILL_TO_CUSTTMsg getBillToCustByCondition(String glblCmpyCd, String billToCustCd) {

        BILL_TO_CUSTTMsg condition = new BILL_TO_CUSTTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("billToCustCd01", billToCustCd);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        BILL_TO_CUSTTMsgArray tmsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0);
        } else {
            return null;
        }
    }

    // 2023/11/06 QC#61924 Add End

    // 2024/03/12 QC#63638 Add Start
    /**
     * <pre>
     * checkLatestShippingStatus
     * @param bizMsg NSAL1320CMsg
     * </pre>
     */
    public static boolean checkLatestShpgSts(NSAL1320CMsg bizMsg) {

        String latestShpgStsCd = null;

        for (int aBizMsgIdx = 0; aBizMsgIdx < bizMsg.A.getValidCount(); aBizMsgIdx++) {
            NSAL1320_ACMsg aBizMsg = bizMsg.A.no(aBizMsgIdx);
            if (ZYPConstant.FLG_ON_Y.equals(aBizMsg.contrAvalFlg_A.getValue())) {
                continue;
            }
            for (int bBizMsgIdx = 0; bBizMsgIdx < bizMsg.B.getValidCount(); bBizMsgIdx++) {
                NSAL1320_BCMsg bBizMsg = bizMsg.B.no(bBizMsgIdx);
                if (aBizMsg.shellLineNum.getValue().compareTo(bBizMsg.shellLineNum_B.getValue()) != 0) {
                    continue;
                }
                latestShpgStsCd = NSAL1320Query.getInstance().getLatestShpgSts(bizMsg.refCpoOrdNum.getValue(), bBizMsg.cpoDtlLineNum.getValue(), bBizMsg.cpoDtlLineSubNum.getValue());
                if (!ZYPCommonFunc.hasValue(latestShpgStsCd)) {
                    continue;
                }
                if (bBizMsg.shpgStsCd.getValue().compareTo(latestShpgStsCd) == 0) {
                    continue;
                }
                if (latestShpgStsCd.compareTo(SHPG_STS.S_OR_O_CANCELLED) == 0 || latestShpgStsCd.compareTo(SHPG_STS.P_OR_O_CANCELLED) == 0 || latestShpgStsCd.compareTo(SHPG_STS.CANCELLED) == 0) {
                    continue;
                }
                if ((bBizMsg.shpgStsCd.getValue().compareTo(SHPG_STS.SHIPPED) < 0 && latestShpgStsCd.compareTo(SHPG_STS.SHIPPED) >= 0)
                        || (bBizMsg.shpgStsCd.getValue().compareTo(SHPG_STS.SHIPPED) == 0 && (latestShpgStsCd.compareTo(SHPG_STS.ARRIVED) == 0 || latestShpgStsCd.compareTo(SHPG_STS.INSTALLED) == 0))) {
                    bizMsg.setMessageInfo(NSAM0792E);
                    return true;
                }
            }
            for (int dBizMsgIdx = 0; dBizMsgIdx < bizMsg.D.getValidCount(); dBizMsgIdx++) {
                NSAL1320_DCMsg dBizMsg = bizMsg.D.no(dBizMsgIdx);
                if (aBizMsg.shellLineNum.getValue().compareTo(dBizMsg.shellLineNum_D.getValue()) != 0) {
                    continue;
                }
                latestShpgStsCd = NSAL1320Query.getInstance().getLatestShpgSts(bizMsg.refCpoOrdNum.getValue(), dBizMsg.cpoDtlLineNum_D.getValue(), dBizMsg.cpoDtlLineSubNum_D.getValue());
                if (!ZYPCommonFunc.hasValue(latestShpgStsCd)) {
                    continue;
                }
                if (dBizMsg.shpgStsCd_D.getValue().compareTo(latestShpgStsCd) == 0) {
                    continue;
                }
                if (latestShpgStsCd.compareTo(SHPG_STS.S_OR_O_CANCELLED) == 0 || latestShpgStsCd.compareTo(SHPG_STS.P_OR_O_CANCELLED) == 0 || latestShpgStsCd.compareTo(SHPG_STS.CANCELLED) == 0) {
                    continue;
                }
                if ((dBizMsg.shpgStsCd_D.getValue().compareTo(SHPG_STS.SHIPPED) < 0 && latestShpgStsCd.compareTo(SHPG_STS.SHIPPED) >= 0)
                        || (dBizMsg.shpgStsCd_D.getValue().compareTo(SHPG_STS.SHIPPED) == 0 && (latestShpgStsCd.compareTo(SHPG_STS.ARRIVED) == 0 || latestShpgStsCd.compareTo(SHPG_STS.INSTALLED) == 0))) {
                    bizMsg.setMessageInfo(NSAM0792E);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * <pre>
     * callDiCheckApi
     * @param glblCmpyCd String
     * @param slsDt String
     * @param userId String
     * @param copyBizMsg NSAL1320CMsg
     * @param bizMsg NSAL1320CMsg
     * </pre>
     */
    public static void callDiCheckApi(String glblCmpyCd, String slsDt, String userId, NSAL1320CMsg copyBizMsg, NSAL1320CMsg bizMsg) {

        final NWZC155001PMsg diCheckApiMsg = new NWZC155001PMsg();
        ZYPEZDItemValueSetter.setValue(diCheckApiMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(diCheckApiMsg.cpoOrdNum, copyBizMsg.refCpoOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(diCheckApiMsg.diChkSubmtPsnCd, userId);
        ZYPEZDItemValueSetter.setValue(diCheckApiMsg.diJobId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(diCheckApiMsg.contrExecFlg, ZYPConstant.FLG_ON_Y);

        String ordDt = copyBizMsg.cpoOrdTs.getValue();
        int dtLen = diCheckApiMsg.getAttr("ordDt").getDigit();
        if (ZYPCommonFunc.hasValue(ordDt) && ordDt.length() >= dtLen) {
            ZYPEZDItemValueSetter.setValue(diCheckApiMsg.slsDt, ordDt.substring(0, dtLen));
            ZYPEZDItemValueSetter.setValue(diCheckApiMsg.ordDt, ordDt.substring(0, dtLen));
        } else {
            ZYPEZDItemValueSetter.setValue(diCheckApiMsg.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(diCheckApiMsg.ordDt, slsDt);
        }

        new NWZC155001().execute(diCheckApiMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(diCheckApiMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(diCheckApiMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                copyBizMsg.setMessageInfo(msgId, msgPrms);
                if (msgId.endsWith("E")) {
                    return;
                }
            }
        }
        if (diCheckApiMsg.diChkHldFlg.getValue().equals("Y")) {
            bizMsg.setMessageInfo(NSAM0789W);
        }
        return;
    }

    /**
     * <pre>
     * callOrderStatusUpdateApi
     * @param glblCmpyCd String
     * @param bizMsg NSAL1320CMsg
     * </pre>
     */
    public static void callOrderStatusUpdateApi(String glblCmpyCd, NSAL1320CMsg bizMsg) {

        final NWZC188001PMsg orderStatusUpdatApiMsg = new NWZC188001PMsg();
        ZYPEZDItemValueSetter.setValue(orderStatusUpdatApiMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(orderStatusUpdatApiMsg.cpoOrdNum, bizMsg.refCpoOrdNum.getValue());

        new NWZC188001().execute(orderStatusUpdatApiMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(orderStatusUpdatApiMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(orderStatusUpdatApiMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                if (msgId.endsWith("E")) {
                    return;
                }
            }
        }
        return;
    }
    // 2024/03/12 QC#63638 Add End
}
