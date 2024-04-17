/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840.common;

import static business.blap.NWAL1840.constant.NWAL1840Constant.ALLOWED_QTY;
import static business.blap.NWAL1840.constant.NWAL1840Constant.BIZ_ID;
import static business.blap.NWAL1840.constant.NWAL1840Constant.BLANK;
import static business.blap.NWAL1840.constant.NWAL1840Constant.DATE_PATTERN_START_TS;
import static business.blap.NWAL1840.constant.NWAL1840Constant.DEF_SLS_CR_PCT_WRITER;
import static business.blap.NWAL1840.constant.NWAL1840Constant.DELIVERED_QTY;
import static business.blap.NWAL1840.constant.NWAL1840Constant.EQUIPMENT_ORDER_VALUE_SET;
import static business.blap.NWAL1840.constant.NWAL1840Constant.IDX_3;
import static business.blap.NWAL1840.constant.NWAL1840Constant.NEW_LINE;
import static business.blap.NWAL1840.constant.NWAL1840Constant.ORDERED_QTY;
import static business.blap.NWAL1840.constant.NWAL1840Constant.ORDR_SUBMT_DT_LIST_MAX_LEN;
import static business.blap.NWAL1840.constant.NWAL1840Constant.PCT_100;
import static business.blap.NWAL1840.constant.NWAL1840Constant.SHPG_CMT_TXT_LIMIT_SIZE;
import static business.blap.NWAL1840.constant.NWAL1840Constant.SUPPLIES_ORDER_VALUE_SET;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_CATG;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_CONF;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_FRT_TERMS;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_LINE;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SCHD_LINE;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0181E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0667E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0757W;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0801E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0831E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0981W;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.ZZM9000E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCItem;
import parts.common.EZDCStringItem;
import parts.common.EZDCStringItemArray;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSItem;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1840.NWAL1840CMsg;
import business.blap.NWAL1840.NWAL1840Query;
import business.blap.NWAL1840.NWAL1840QueryForCustomer;
import business.blap.NWAL1840.NWAL1840QueryForPricing;
import business.blap.NWAL1840.NWAL1840QueryForSearch;
import business.blap.NWAL1840.NWAL1840SMsg;
import business.blap.NWAL1840.NWAL1840_ACMsg;
import business.blap.NWAL1840.NWAL1840_ACMsgArray;
import business.blap.NWAL1840.NWAL1840_BCMsg;
import business.blap.NWAL1840.NWAL1840_BCMsgArray;
import business.blap.NWAL1840.NWAL1840_CCMsg;
import business.blap.NWAL1840.NWAL1840_CCMsgArray;
import business.blap.NWAL1840.NWAL1840_ECMsg;
import business.blap.NWAL1840.constant.NWAL1840Constant;
import business.db.COA_BRTMsg;
import business.db.COA_BRTMsgArray;
import business.db.CPO_SRC_TPTMsg;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTRTMsgArray;
import business.db.DS_ORD_TPTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.FRT_CONDTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsgArray;
import business.db.MDSETMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.PRC_CATGTMsg;
import business.db.PRC_COND_TPTMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;
import business.parts.NMZC260001_defSlsRepListPMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC611001PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157001_xxPrcListPMsgArray;

import com.canon.cusa.s21.api.NMZ.NMZC260001.NMZC260001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC611001.NMZC611001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_AGMT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_INTVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   Fujitsu         Y.Murai         Create          N/A
 * 2016/05/13   Fujitsu         Y.Murai         Create          N/A
 * 2016/07/13   Fujitsu         H.Ikeda         Update          S21_NA#11654
 * 2016/08/03   Fujitsu         M.Hara          Update          S21_NA#7306
 * 2016/08/04   Fujitsu         H.Ikeda         Update          S21_NA#11884
 * 2016/07/21   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 * 2016/08/30   Fujitsu         M.Yamada        Update          QC#10754
 * 2016/08/30   Fujitsu         H.Ikeda         Update          QC#13036
 * 2016/09/09   Fujitsu         T.Murai         Update          QC#13035
 * 2016/09/20   Fujitsu         T.Murai         Update          S21_NA#14578
 * 2016/10/20   Fujitsu         H.Ikeda         Update          S21_NA#13033
 * 2016/11/16   Fujitsu         H.Ikeda         Update          S21_NA#15875
 * 2016/12/19   Fujitsu         T.Yoshida       Update          S21_NA#16410
 * 2017/02/21   Fujitsu         Y.Kanefusa      Update          S21_NA#17474
 * 2017/02/24   Fujitsu         Y.Kanefusa      Update          S21_NA#13688
 * 2017/03/01   Fujitsu         Y.Kanefusa      Update          S21_NA#17637
 * 2017/03/15   Fujitsu         M.Ohno          Update          S21_NA#16855
 * 2017/06/26   Fujitsu         A.Kosai         Update          QC#18332
 * 2017/08/04   Fujitsu         H.Nagashima     Update          QC#16452
 * 2017/09/18   Fujitsu         S.Takami        Update          S21_NA#21190
 * 2017/12/12   Fujitsu         A.Kosai         Update          Sol#349(QC#19804)
 * 2018/02/09   Fujitsu         K.Ishizuka      Update          S21_NA#20297(Sol#379)
 * 2018/04/02   Fujitsu         K.Ishizuka      Update          S21_NA#24860
 * 2018/04/10   Fujitsu         M.Ohno          Update          S21_NA#25013
 * 2018/04/18   Fujitsu         K.Ishizuka      Update          S21_NA#25418
 * 2018/05/21   Fujitsu         Y.Kanefusa      Update          S21_NA#21841
 * 2018/06/05   Fujitsu         Y.Kanefusa      Update          S21_NA#22965
 * 2018/06/27   Fujitsu         H.Nagashima     Update          S21_NA#23726
 * 2018/08/03   Fujitsu         Y.Kanefusa      Update          S21_NA#27479
 * 2018/10/05   Fujitsu         Hd.Sugawara     Update          S21_NA#28510
 * 2018/12/11   Fujitsu         M.Ohno          Update          S21_NA#29315
 * 2019/12/20   Fujitsu         A.Kazuki        Update          QC#53055
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 * 2022/06/01   Hitachi         D.Yoshida       Update          QC#59973
 * 2023/03/14   CITS            R.Azucena       Update          QC#61286
 * 2023/10/10   Hitachi         T.Fukuta        Update          CSA-QC#61730
 * </pre>
 */
public class NWAL1840CommonLogic {

    /**
     * Set Initial Values
     * @param bizMsg NWAL1840CMsg
     * @param glblCmpyCd Global Company Code
     * @param adminPsnCd Admin Person Code
     */
    public static void setInitialValues(NWAL1840CMsg bizMsg, String glblCmpyCd, String adminPsnCd) {

        // Common
        String slsDt = ZYPDateUtil.getSalesDate();
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.adminPsnCd, adminPsnCd);

        // Header
        ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt, slsDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCalcDt, slsDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSubTotCalcPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotChrgPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt, BigDecimal.ZERO);

        CPO_SRC_TPTMsg rsltTMsg = (CPO_SRC_TPTMsg) ZYPCodeDataUtil.findByCode("CPO_SRC_TP", glblCmpyCd, CPO_SRC_TP.SCHEDULE_AGREEMENT_ENTRY);
        if (null != rsltTMsg) {
            bizMsg.cpoSrcTpDescTxt.setValue(rsltTMsg.cpoSrcTpDescTxt.getValue());
            bizMsg.cpoSrcTpCd.setValue(rsltTMsg.cpoSrcTpCd.getValue());
        }
        // Customer / Contact Tab
        ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
        
        // Add Start 2016/09/09 S21_NA#13035
        // Item Tab
        if (bizMsg.A.getValidCount() == 0) {
            setNewLine(bizMsg);
        }
        // Add End 2016/09/09 S21_NA#13035
    }

    /**
     * Set Authority
     * @param bizMsg NWAL1840CMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setAuthority(NWAL1840CMsg bizMsg, S21UserProfileService userProfileService) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);

        int funcIdCnt = 0;
        for (String functionId : functionIds) {
            bizMsg.Z.no(funcIdCnt++).xxFuncId.setValue(functionId);
        }
        bizMsg.Z.setValidCount(funcIdCnt);
    }

    /**
     * Create Pulldown
     * @param bizMsg NWAL1840CMsg
     */
    public static void createPulldown(NWAL1840CMsg bizMsg) {

        ZYPCodeDataUtil.createPulldownList("SCHD_AGMT_DELY_HLD", bizMsg.schdAgmtDelyHldCd_CD, bizMsg.schdAgmtDelyHldDescTxt_NM);
        ZYPCodeDataUtil.createPulldownList("SPCL_HDLG_TP", bizMsg.spclHdlgTpCd_CD, bizMsg.spclHdlgTpDescTxt_NM);
//        ZYPCodeDataUtil.createPulldownList("SHPG_SVC_LVL", bizMsg.shpgSvcLvlCd_CD, bizMsg.shpgSvcLvlDescTxt_NM);
        // S21_NA#7306
//        ZYPCodeDataUtil.createPulldownList("CTAC_TP", bizMsg.ctacTpCd_CD, bizMsg.ctacTpDescTxt_NM);
        createCtacPsnTpPulldownList(bizMsg);

        createRsnCdPulldown(bizMsg);

        ZYPCodeDataUtil.createPulldownList("CTAC_CUST_REF_TP", bizMsg.ctacCustRefTpCd_CD, bizMsg.ctacCustRefTpDescTxt_NM);  // QC#16452 add
        // START 2022/06/01 [QC#59973, ADD]
        createFrequencyPulldown(bizMsg);
        // END   2022/06/01 [QC#59973, ADD]
    }

    /**
     * Check Input Category
     * @param bizMsg NWAL1840CMsg
     * @return check OK : true
     */
    public static boolean checkExistCatg(NWAL1840CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1840Query.getInstance().getDsOrdCatgList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CATG });
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);
        List<Map<String, String>> dsOrdCatgCdList = (List<Map<String, String>>) ssmResult.getResultObject();
        if (dsOrdCatgCdList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, dsOrdCatgCdList.get(0).get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgDescTxt, dsOrdCatgCdList.get(0).get("DS_ORD_CATG_DESC_TXT"));

        return true;
    }

    // S21_NA#7306
    /**
     * setCtacPsnTpPulldown
     * @param bizMsg NWAL1840CMsg
     */
    @SuppressWarnings("unchecked")
    public static void createCtacPsnTpPulldownList(NWAL1840CMsg bizMsg) {
        S21SsmEZDResult result = NWAL1840Query.getInstance().getCtacPsnTpList(bizMsg);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                bizMsg.ctacTpCd_CD.no(i).setValue((String) map.get("CTAC_TP_CD"));
                bizMsg.ctacTpDescTxt_NM.no(i).setValue((String) map.get("CTAC_TP_DESC_TXT"));
                // QC#16452 add Start
                String ctacCustRefTpCd = (String) map.get("CTAC_CUST_REF_TP_CD");
                if (ZYPCommonFunc.hasValue(ctacCustRefTpCd)) {
                    bizMsg.L.no(i).ctacCustRefTpCd_L.setValue(ctacCustRefTpCd);
                }
                // QC#16452 add End
                i++;
            }
        }
    }

    /**
     * Create Reason Code PullDown
     * @param bizMsg NWAL1840CMsg
     */
    public static void createRsnCdPulldown(NWAL1840CMsg bizMsg) {

        bizMsg.dsOrdTpCd_CD.clear();
        bizMsg.dsOrdTpDescTxt_NM.clear();

        S21SsmEZDResult ssmResult = NWAL1840Query.getInstance().getDsOrdTpList(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_CD.no(i), resultMap.get("DS_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpDescTxt_NM.no(i), resultMap.get("DS_ORD_TP_DESC_TXT"));
            }
        }
    }

    /**
     * Get CPO Order Type Code
     * @param bizMsg NWAL1840CMsg
     * @return CPO Order Type Code
     */
    public static String getCpoOrdTpCd(NWAL1840CMsg bizMsg) {

        DS_ORD_TPTMsg tMsg = new DS_ORD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        tMsg = (DS_ORD_TPTMsg) S21FastTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return null;
        }

        return tMsg.cpoOrdTpCd.getValue();
    }

    /**
     * Derive Default Data
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    public static void deriveDefaultData(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        deriveDefaultCustAddl(bizMsg);
        setDefShipInfo(bizMsg); // 2018/02/09 S21_NA#20297(Sol#379) Add

        if (!deriveDefaultSlsRep(bizMsg)) {
            return;
        }

        if (!deriveDefaultCarrSvcLvl(bizMsg)) {
            return;
        }

        deriveDefaultPrcList(bizMsg);
        // 2018/12/11 S21_NA#29315 Add Start
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd) && ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsBizAreaCd, NWXC150001DsCheck.getDsBizArea(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue(), bizMsg.dsOrdRsnCd.getValue()));
        }
        // 2018/12/11 S21_NA#29315 Add End
    }

    /**
     * Derive Default Customer And Additional Data
     * @param bizMsg NWAL1840CMsg
     */
    private static void deriveDefaultCustAddl(NWAL1840CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1840Query.getInstance().getCustAddlInfo(bizMsg);
        String billToCustCd = null;

        if (ssmResult.isCodeNormal()) {

            bizMsg.frtCondCd.clear();
            bizMsg.frtCondDescTxt.clear();
            bizMsg.carrSvcLvlDescTxt.clear();
            bizMsg.shpgSvcLvlCd.clear();
            bizMsg.prcCatgCd.clear();
            bizMsg.prcCatgNm.clear();
            bizMsg.flPrcListCd.clear();
            bizMsg.dropShipAvalFlg.clear();

            Map<String, String> resultMap = (Map<String, String>) ssmResult.getResultObject();
            if (ZYPCommonFunc.hasValue(resultMap.get("LINE_BIZ_TP_CD"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd, resultMap.get("LINE_BIZ_TP_CD"));
            }
            if (ZYPCommonFunc.hasValue(resultMap.get("DEF_BILL_TO_CUST_ACCT_CD")) || ZYPCommonFunc.hasValue(resultMap.get("DEF_BILL_TO_CUST_CD"))) {
                billToCustCd = resultMap.get("DEF_BILL_TO_CUST_CD");
                ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, resultMap.get("DEF_BILL_TO_CUST_ACCT_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.billToCustLocCd, resultMap.get("DEF_BILL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm, resultMap.get("DS_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, resultMap.get("BILL_TO_ADDR"));
            }
            if (ZYPCommonFunc.hasValue(resultMap.get("FRT_COND_CD"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, resultMap.get("FRT_COND_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.frtCondDescTxt, resultMap.get("FRT_COND_DESC_TXT"));
                NWAL1840CommonLogic.setShpgSvcLvlPullDown(bizMsg);
            }
            if (ZYPCommonFunc.hasValue(resultMap.get("CARR_SVC_LVL_DESC_TXT"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlDescTxt, resultMap.get("CARR_SVC_LVL_DESC_TXT"));
            } else if (ZYPCommonFunc.hasValue(resultMap.get("DEF_SHPG_SVC_LVL_CD"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd, resultMap.get("DEF_SHPG_SVC_LVL_CD"));
            }
            if (ZYPCommonFunc.hasValue(resultMap.get("DROP_SHIP_AVAL_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dropShipAvalFlg, resultMap.get("DROP_SHIP_AVAL_FLG"));
            }
            if (ZYPCommonFunc.hasValue(resultMap.get("PRC_CATG_NM"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd, resultMap.get("PRC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm, resultMap.get("PRC_CATG_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.flPrcListCd, resultMap.get("PRC_CATG_CD"));

            }
            controlDropShipField(bizMsg);

            if (ZYPCommonFunc.hasValue(billToCustCd)) {
                deriveDefaultPmtTerm(bizMsg, billToCustCd);
            }

        }
    }

    /**
     * Derive Default Payment Term Data
     * @param bizMsg NWAL1840CMsg
     * @param billToCustCd Bill To Customer Code
     */
    public static void deriveDefaultPmtTerm(NWAL1840CMsg bizMsg, String billToCustCd) {

        bizMsg.pmtTermCashDiscDescTxt.clear();
        String pmtTermCashDiscCd = getPmtTermCashDiscCd(bizMsg, billToCustCd);

        if (ZYPCommonFunc.hasValue(pmtTermCashDiscCd)) {
            PMT_TERM_CASH_DISCTMsg tMsg = new PMT_TERM_CASH_DISCTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCashDiscCd, pmtTermCashDiscCd);
            tMsg = (PMT_TERM_CASH_DISCTMsg) S21FastTBLAccessor.findByKey(tMsg);

            if (tMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.pmtTermCashDiscDescTxt, tMsg.pmtTermCashDiscDescTxt);
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.ovrdPmtTermFlg, NWXC150001DsCheck.getOvrdPmtTermFlg(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustAcctCd.getValue(), bizMsg.billToCustLocCd.getValue()));         // QC#17474 2017/02/21 Add
    }

    /**
     * Get Payment Term Cash Discount Code
     * @param bizMsg NWAL1840CMsg
     * @param billToCustCd Bill To Customer Code
     * @return Payment Term Cash Discount Code
     */
    private static String getPmtTermCashDiscCd(NWAL1840CMsg bizMsg, String billToCustCd) {

        if (ZYPCommonFunc.hasValue(billToCustCd)) {
            S21SsmEZDResult ssmResult = NWAL1840Query.getInstance().getPmtTermCashDiscCdForBillToCust(bizMsg, billToCustCd);
            if (ssmResult.isCodeNotFound()) {
                DS_ACCT_CR_PRFLTMsg tMsg = new DS_ACCT_CR_PRFLTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, bizMsg.billToCustAcctCd);
                tMsg = (DS_ACCT_CR_PRFLTMsg) EZDTBLAccessor.findByKey(tMsg);
                if (tMsg == null) {
                    return null;
                }
                return tMsg.pmtTermCashDiscCd.getValue();
            }

            return (String) ssmResult.getResultObject();
        }

        return null;
    }

    /**
     * Control Drop Ship Field
     * @param bizMsg NWAL1840CMsg
     */
    public static void controlDropShipField(NWAL1840CMsg bizMsg) {

        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.dropShipAvalFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.dropShipFlg.getValue())) {
            S21SsmEZDResult ssmResult = NWAL1840QueryForCustomer.getInstance().getShipToCustInfoList(bizMsg);

            if (ssmResult.isCodeNotFound()) {
                bizMsg.shipToCustLocCd.clear();
                return;
            }

            List<Map<String, String>> shipToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
            Map<String, String> shipToInfo = shipToCustInfoList.get(0);

            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, shipToInfo.get("SELL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, shipToInfo.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustLocCd, shipToInfo.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm, shipToInfo.get("LOC_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm, shipToInfo.get("ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SH, shipToInfo.get("SHIP_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr, shipToInfo.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr, shipToInfo.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr, shipToInfo.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr, shipToInfo.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipTo01RefCmntTxt, shipToInfo.get("FIRST_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipTo02RefCmntTxt, shipToInfo.get("SCD_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr, shipToInfo.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd, shipToInfo.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd, shipToInfo.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm, shipToInfo.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd, shipToInfo.get("CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm, shipToInfo.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * Derive Default Sales Rep Data
     * @param bizMsg Business Message
     * @return Succeed drive : true
     */
    public static boolean deriveDefaultSlsRep(NWAL1840CMsg bizMsg) {

        // S21_NA#16410 Add Start
        bizMsg.slsRepTocCd.clear();
        bizMsg.slsRepTocNm.clear();
        bizMsg.psnNum.clear();
        bizMsg.coaBrCd.clear();
        bizMsg.coaBrDescTxt.clear();
        bizMsg.coaExtnCd.clear();
        bizMsg.coaExtnDescTxt.clear();
        bizMsg.xxScrItem54Txt_CB.clear();
        bizMsg.xxScrItem54Txt_CE.clear();
        delExistingSlsRep(bizMsg);
        // S21_NA#16410 Add End

        // call NMZC2600 Default Sales Rep API
        NMZC260001PMsg nMZC260001PMsg = new NMZC260001PMsg();
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.shipToCustCd, bizMsg.shipToCustLocCd);
        // 2018/04/02 S21_NA#24860 Add Start
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        // 2018/04/02 S21_NA#24860 Add End
        // 2020/04/27 QC#56638 Add Start
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.billToCustCd, bizMsg.soldToCustLocCd);
        // 2020/04/27 QC#56638 Add End
        if (!callDefSlsRepApi(bizMsg, nMZC260001PMsg)) {
            return false;
        }

        String lineBizTpCd = bizMsg.lineBizTpCd.getValue();
        // 2017/03/14 S21_NA#16855 Add Start
        // 2017/12/12 Sol#349(QC#19804) Mod Start
//        String trtyGrpTpCd = getTrtyGrpTpCdFromDsOrdTpCd(bizMsg);
        String trtyGrpTpTxt = getTrtyGrpTpTxtFromDsOrdTpCd(bizMsg);
        List<String> trtyGrpTpCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(trtyGrpTpTxt)) {
            trtyGrpTpCdList = Arrays.asList(trtyGrpTpTxt.split(","));
        }
        // 2017/12/12 Sol#349(QC#19804) Mod End
        LINE_BIZ_ROLE_TPTMsg lineBizRoleTpTMsg = new LINE_BIZ_ROLE_TPTMsg();
        lineBizRoleTpTMsg.setSQLID("001");
        lineBizRoleTpTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        lineBizRoleTpTMsg.setConditionValue("primRepRoleFlg01", ZYPConstant.FLG_ON_Y);
        LINE_BIZ_ROLE_TPTMsgArray TMsgArray = (LINE_BIZ_ROLE_TPTMsgArray) EZDTBLAccessor.findByCondition(lineBizRoleTpTMsg);
        List<String> targetWriterList = new ArrayList<String>();
        if (TMsgArray != null && TMsgArray.length() > 0) {
            for (int i = 0; i < TMsgArray.length(); i++) {
                LINE_BIZ_ROLE_TPTMsg tMsg = TMsgArray.no(i);
                targetWriterList.add(tMsg.lineBizRoleTpCd.getValue());
            }
        }
        // 2017/03/14 S21_NA#16855 Add End

        NMZC260001_defSlsRepListPMsgArray defSlsRepMsgArray = nMZC260001PMsg.defSlsRepList;

        List<NMZC260001_defSlsRepListPMsg> matchLobSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>(defSlsRepMsgArray.getValidCount());
        // 2017/12/12 Sol#349(QC#19804) Add Start
        List<NMZC260001_defSlsRepListPMsg> matchLobRoleSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>();
        // 2017/12/12 Sol#349(QC#19804) Add End
        for (int i = 0; i < defSlsRepMsgArray.getValidCount(); i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = defSlsRepMsgArray.no(i);
            
            // 2018/04/18 S21_NA#25418 Add Start
            if(ZYPCommonFunc.hasValue(defSlsRepPMsg.xxRsltFlg) && ZYPConstant.FLG_ON_Y.equals(defSlsRepPMsg.xxRsltFlg.getValue())//
                    && !matchLobRoleSlsRepPMsgList.isEmpty()){
                continue;
            }
            // 2018/04/18 S21_NA#25418 Add End

            // 2017/03/14 S21_NA#16855 Mod Start
//            if (defSlsRepPMsg.lineBizTpCd.getValue().equals(lineBizTpCd)) {
            // 2017/12/12 Sol#349(QC#19804) Mod Start
//            if (defSlsRepPMsg.lineBizTpCd.getValue().equals(lineBizTpCd) //
//                    && (!ZYPCommonFunc.hasValue(trtyGrpTpCd) //
//                    || trtyGrpTpCd.equals(defSlsRepPMsg.trtyGrpTpCd.getValue()))) {
            if ((trtyGrpTpCdList.isEmpty() && defSlsRepPMsg.lineBizTpCd.getValue().equals(lineBizTpCd))
                    || (trtyGrpTpCdList.size() > 0 && trtyGrpTpCdList.contains(defSlsRepPMsg.trtyGrpTpCd.getValue()))) {
            // 2017/12/12 Sol#349(QC#19804) Mod End
            // 2017/03/14 S21_NA#16855 Mod End
                matchLobSlsRepPMsgList.add(defSlsRepPMsg);

                String lineBizRoleTpCd = defSlsRepPMsg.lineBizRoleTpCd.getValue();
                // 2017/03/14 S21_NA#16855 Mod Start
//                if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTpCd)) {
                if (targetWriterList.contains(lineBizRoleTpCd)) {
                // 2017/03/14 S21_NA#16855 Mod End
                    // 2017/12/12 Sol#349(QC#19804) Mod Start
//                    getSlsRepInfo(bizMsg, defSlsRepPMsg.tocCd.getValue());
                    matchLobRoleSlsRepPMsgList.add(defSlsRepPMsg);
                    // 2017/12/12 Sol#349(QC#19804) Mod End
                }
            }
        }

        // 2017/12/12 Sol#349(QC#19804) Add Start
        if (defSlsRepMsgArray.getValidCount() > 0) {
            if (matchLobRoleSlsRepPMsgList.size() == 1) {
                getSlsRepInfo(bizMsg, matchLobRoleSlsRepPMsgList.get(0).tocCd.getValue());
            } else {
                // 2020/04/27 QC#56638 Add Start
                if (isSlsReqDef(bizMsg)) {
                    bizMsg.setMessageInfo(NWAM0757W);
                } else {
                    bizMsg.setMessageInfo(NWAM0981W);
                }
                // 2020/04/27 QC#56638 Add End
            }
        }
        // 2017/12/12 Sol#349(QC#19804) Add End

        if (matchLobSlsRepPMsgList.size() > 0) {
            // 2017/03/14 S21_NA#16685 Mod Start
//            setNewSlsRep(bizMsg, matchLobSlsRepPMsgList);
            setNewSlsRep(bizMsg, matchLobSlsRepPMsgList, targetWriterList);
            // 2017/03/14 S21_NA#16685 Mod End
        }

        return true;
    }

    /**
     * Call NMZC2600 Default Sales Rep API
     * @param bizMsg NWAL1840CMsg
     * @param pMsg NMZC260001PMsg
     * @return has API error : false
     */
    public static boolean callDefSlsRepApi(NWAL1840CMsg bizMsg, NMZC260001PMsg pMsg) {

        // call NMZC2600 Dafault Sales Rep API
        new NMZC260001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();

                if (msgId.endsWith("E")) {
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);
                    return false;
                // 2020/04/27 QC#56638 Add Start
                } else if (ZYPCommonFunc.hasValue(bizMsg.shipToCustLocCd) && msgId.endsWith(NWAM0757W)) {
                    bizMsg.setMessageInfo(NWAM0757W);
                } else if (ZYPCommonFunc.hasValue(pMsg.billToCustCd) && msgId.endsWith(NWAM0981W)) {
                    bizMsg.setMessageInfo(NWAM0981W);
                }
                boolean isShipBase = isSlsReqDef(bizMsg);
                if (isShipBase && ZYPCommonFunc.hasValue(bizMsg.shipToCustLocCd) && msgId.endsWith("W")) {
                    bizMsg.setMessageInfo(NWAM0757W);
                } else if (!isShipBase && ZYPCommonFunc.hasValue(pMsg.billToCustCd) && msgId.endsWith("W")) {
                    bizMsg.setMessageInfo(NWAM0981W);
                }
                // 2020/04/27 QC#56638 Add End
            }
        }

        return true;
    }

    /**
     * Derive Default Sales Rep Data For Header
     * @param bizMsg NWAL1840CMsg
     * @param slsRepTocCd Sales Rep TOC Code
     */
    public static void getSlsRepInfo(NWAL1840CMsg bizMsg, String slsRepTocCd) {

        S21SsmEZDResult ssmResult = NWAL1840Query.getInstance().getSlsRepInfo(bizMsg, slsRepTocCd);

        if (ssmResult.isCodeNormal()) {
            Map<String, String> resultMap = (Map<String, String>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocCd, slsRepTocCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocNm, resultMap.get("TOC_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd, resultMap.get("COA_BR_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.coaBrDescTxt, resultMap.get("COA_BR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd, resultMap.get("COA_EXTN_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnDescTxt, resultMap.get("COA_EXTN_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CB, resultMap.get("COA_BR_ITEM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CE, resultMap.get("COA_EXTN_ITEM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.psnNum, resultMap.get("PSN_NUM")); 
        }
    }

    /**
     * Delete Existing Sales Rep Info
     * @param bizMsg NWAL1840CMsg
     */
    private static void delExistingSlsRep(NWAL1840CMsg bizMsg) {

        // delete Header
        List<Integer> deleteRows = new ArrayList<Integer>(bizMsg.C.getValidCount());
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL1840_CCMsg slsRepMsg = bizMsg.C.no(i);

            if (ZYPCommonFunc.hasValue(slsRepMsg.schdAgmtSlsCrPk_C)) {
                ZYPEZDItemValueSetter.setValue(slsRepMsg.xxRqstTpCd_C, NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
            } else {
                deleteRows.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.C, deleteRows);
    }

    /**
     * Set New Sales Rep Info
     * @param bizMsg NWAL1840CMsg
     * @param slsRepPMsgList List<NMZC260001_defSlsRepListPMsg>
     */
//    private static void setNewSlsRep(NWAL1840CMsg bizMsg, List<NMZC260001_defSlsRepListPMsg> slsRepPMsgList) { // 2017/03/14 S21_NA#16685 Mod
    private static void setNewSlsRep(NWAL1840CMsg bizMsg, List<NMZC260001_defSlsRepListPMsg> slsRepPMsgList, List<String> targetWriterList) {

        // set Header
        // QC#17637 2017/03/01 Add Start
        String mode = getSalesCreditPrecentMode(slsRepPMsgList);
        BigDecimal writerPct = BigDecimal.ZERO;
        BigDecimal installerPct = BigDecimal.ZERO;
        if(S21StringUtil.isEquals("2",mode)){
            BigDecimal pct = ZYPCodeDataUtil.getNumConstValue(DEF_SLS_CR_PCT_WRITER, bizMsg.glblCmpyCd.getValue());
            if(pct != null){
                writerPct = pct;
                installerPct = PCT_100.subtract(writerPct);
            }
        }
        // QC#17637 2017/03/01 Add End
        int vldCnt = bizMsg.C.getValidCount();
        for (int i = 0; i < slsRepPMsgList.size(); i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = slsRepPMsgList.get(i);

            NWAL1840_CCMsg slsRepMsg = bizMsg.C.no(vldCnt);
            ZYPEZDItemValueSetter.setValue(slsRepMsg.xxRqstTpCd_C, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepTocCd_C, defSlsRepPMsg.tocCd);
            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_C, defSlsRepPMsg.lineBizRoleTpCd);
            // Add Start 2019/12/20 QC#53055 ISW check
            // When LINE_BIZ_TP = LFS and TRTY_GRP_TP_TXT = IS, Set "IS Writer".
            DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
            tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);
            if (tMsg != null && NWAL1840Constant.TRTY_GRP_TP_IS.equals(tMsg.trtyGrpTpTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_C, LINE_BIZ_ROLE_TP.IS_WRITER);
            }
            // Add End   2019/12/20 QC#53055
            ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_C, defSlsRepPMsg.lineBizRoleTpCd);

            // QC#17727 2017/03/01 Mod Start
            // if (i == 0) {
            //     ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepCrPct_C, PCT_100);
            // } else {
            //     ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepCrPct_C, BigDecimal.ZERO);
            // }
            if (S21StringUtil.isEquals("1", mode)) {
                ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepCrPct_C, PCT_100);
            } else if (S21StringUtil.isEquals("2", mode)) {
                // 2017/03/14 S21_NA#16685 Mod Start
//              if (isWriter(defSlsRepPMsg.lineBizRoleTpCd.getValue())) {
                if (targetWriterList.contains(defSlsRepPMsg.lineBizRoleTpCd.getValue())) {
                // 2017/03/14 S21_NA#16685 Mod End
                    ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepCrPct_C, writerPct);
                } else {
                    ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepCrPct_C, installerPct);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepCrPct_C, BigDecimal.ZERO);
            }
            // QC#17727 2017/03/01 Mod End

            // Mod 2016/09/20 S21_NA#14578
            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsCrQuotFlg_C, ZYPConstant.FLG_ON_Y);
            // ZYPEZDItemValueSetter.setValue(slsRepMsg.slsCrQuotFlg_C, ZYPConstant.FLG_OFF_N);
            vldCnt++;

        }
        bizMsg.C.setValidCount(vldCnt);
    }

    // QC#17637 2017/03/01 Add Start
    private static String getSalesCreditPrecentMode(List<NMZC260001_defSlsRepListPMsg> slsRepPMsgList){

        boolean hasWriter = false;
        boolean hasInstaller = false;
        if (slsRepPMsgList.size() == 1) {
            return "1";
        } else if (slsRepPMsgList.size() == 2) {
            for (NMZC260001_defSlsRepListPMsg data : slsRepPMsgList) {
                if (isWriter(data.lineBizRoleTpCd.getValue())) {
                    hasWriter = true;
                } else if (isInstaller(data.lineBizRoleTpCd.getValue())) {
                    hasInstaller = true;
                }
            }
            if (hasWriter && hasInstaller) {
                return "2";
            }
        }
        return "0";
    }

    /**
     * isWriter
     * @param slsRepRoleTpCd
     * @return
     */
    private static boolean isWriter(String slsRepRoleTpCd){
        List<String> writerList = Arrays.asList(LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER);
        return writerList.contains(slsRepRoleTpCd);
    }

    /**
     * isInstaller
     * @param slsRepRoleTpCd
     * @return
     */
    private static boolean isInstaller(String slsRepRoleTpCd){
        List<String> installerList = Arrays.asList(LINE_BIZ_ROLE_TP.ESS_INSTALLER, LINE_BIZ_ROLE_TP.LFS_INSTALLER, LINE_BIZ_ROLE_TP.PPS_INSTALLER);
        return installerList.contains(slsRepRoleTpCd);
    }
    // QC#17637 2017/03/01 Add End
    /**
     * Derive Default Carrier Service Level
     * @param bizMsg NWAL1840CMsg
     * @return No API Error : true
     */
    public static boolean deriveDefaultCarrSvcLvl(NWAL1840CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd) || !ZYPCommonFunc.hasValue(bizMsg.shpgSvcLvlCd)) {
            return true;
        }
        // QC#23726 2018/06/27 del Start
//        if (!FRT_COND.COLLECT.equals(bizMsg.frtCondCd.getValue())) {  // QC#13688 2017/02/24 Add
//            return true;
//        }
        // QC#23726 2018/06/27 del Start

        NMZC611001PMsg defCarrApiPMsg = callDefaultCarrierApi(bizMsg);

        if (S21ApiUtil.isXxMsgId(defCarrApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(defCarrApiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);

                if (msgId.endsWith("E")) {
                    return false;
                }
            }
        }

        String vndCd = defCarrApiPMsg.vndCd_O.getValue();
        if (ZYPCommonFunc.hasValue(vndCd)) {
            if (FRT_COND.COLLECT.equals(bizMsg.frtCondCd.getValue())) { // QC#23726 add
                // QC#13688 2017/02/24 Add Start
                // ZYPEZDItemValueSetter.setValue(bizMsg.carrAcctNum, vndCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.carrAcctNum, defCarrApiPMsg.dsCarrAcctNum);
                // QC#13688 2017/02/24 Add End
            }
            S21SsmEZDResult ssmResult = NWAL1840Query.getInstance().getCarrSvcLvlTxt(bizMsg, vndCd);

            if (ssmResult.isCodeNormal()) {
                String carrSvcLvlDescTxt = (String) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlDescTxt, carrSvcLvlDescTxt);
            }
        }
        return true;
    }

    /*
     * Call NMZC6110 Default Carrier API
     * @param bizMsg NWAL1840CMsg
     * @return NMZC611001PMsg
     */
    private static NMZC611001PMsg callDefaultCarrierApi(NWAL1840CMsg bizMsg) {

        NMZC611001PMsg pMsg = new NMZC611001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, bizMsg.shipToCustAcctCd);
        // 2018/12/11 S21_NA#29315 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.locNum, bizMsg.shipToLocNum);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsBizAreaCd, bizMsg.dsBizAreaCd);
     // 2018/12/11 S21_NA#29315 Add Start
        new NMZC611001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        return pMsg;
    }

    /**
     * Derive Default Price List
     * @param bizMsg NWAL1840CMsg
     * @return has API Error : false
     */
    public static boolean deriveDefaultPrcList(NWAL1840CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
            return true;
        }
        // QC#11884 2016/08/03 Del End
        //if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd)) {
        //    return true;
        //}
        // QC#11340 2016/08/03 Del End
        // call NWZC1570 Pricing API
        NWZC157001PMsg prcApiPMsg = callPricingApiOfGetPriceListMode(bizMsg);
        if (prcApiPMsg == null) {
            return false;
        }

        // set Default Price List
        NWZC157001_xxPrcListPMsgArray prcListArray = prcApiPMsg.xxPrcList;
        if (prcListArray.getValidCount() == 1) {
            String defPrcList = getPrcCatgNm(bizMsg, prcListArray.no(0).prcCatgCd.getValue());
            String defPrcCatgCd = prcListArray.no(0).prcCatgCd.getValue();

            if (ZYPCommonFunc.hasValue(defPrcList)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm, defPrcList);
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd, defPrcCatgCd);
            }
        }

        return true;
    }

    /**
     * Call NWZC1570 Pricing API (01:Get Price List Mode)
     * @param bizMsg NWAL1840CMsg
     * @return NWZC157001PMsg
     */
    private static NWZC157001PMsg callPricingApiOfGetPriceListMode(NWAL1840CMsg bizMsg) {

        NWZC157001PMsg pMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, bizMsg.sellToCustCd);
      //  ZYPEZDItemValueSetter.setValue(pMsg.csmpNum, bizMsg.csmpNum_N);
       // ZYPEZDItemValueSetter.setValue(pMsg.dlrRefNum, bizMsg.dlrRefNum_N);
        ZYPEZDItemValueSetter.setValue(pMsg.prcContrNum, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.coaBrCd, getCoaBrCd(bizMsg));
        ZYPEZDItemValueSetter.setValue(pMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);

        
        // call NWZC1570 Pricing API
        new NWZC157001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);

            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);

                if (msgId.endsWith("E")) {
                    return null;
                }
            }
        }
        return pMsg;
    }

    /**
     * Get COA Branch Code
     * @param bizMsg NWAL1840CMsg
     * @return COA Branch Code
     */
//    private static String getCoaBrCd(NWAL1840CMsg bizMsg) {
    public static String getCoaBrCd(NWAL1840CMsg bizMsg) {      //QC#22965 mod

        if (ZYPCommonFunc.hasValue(bizMsg.coaBrDescTxt)) {
            return null;
        }

        COA_BRTMsg condition = new COA_BRTMsg();
        condition.setSQLID("803");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("coaBrDescTxt01", bizMsg.coaBrDescTxt.getValue());

        COA_BRTMsgArray tMsgArray = (COA_BRTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tMsgArray.getValidCount() == 0) {
            return null;
        }

        return tMsgArray.no(0).coaBrCd.getValue();
    }

    /**
     * Get Price Category Desc Text
     * @param bizMsg NWAL1840CMsg
     * @param prcCatgCd Price Category Code
     * @return Price Category Desc Text
     */
//    private static String getPrcCatgNm(NWAL1840CMsg bizMsg, String prcCatgCd) {
    public static String getPrcCatgNm(NWAL1840CMsg bizMsg, String prcCatgCd) {  //QC#22965 mod

        PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, prcCatgCd);
        prcCatgTMsg = (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatgTMsg);

        if (prcCatgTMsg == null) {
            return null;
        }

        return prcCatgTMsg.prcCatgNm.getValue();
    }

    /**
     * Get Ship To Customer Address Info (For Header Hidden)
     * @param bizMsg NWAL1840CMsg
     */
    public static void getShipToCustAddrInfo(NWAL1840CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1840Query.getInstance().getLocNum(bizMsg, bizMsg.shipToCustLocCd.getValue());

        if (!ssmResult.isCodeNotFound()) {
            Map<String, String> shipToCustAddrInfo = (Map<String, String>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.locNum, shipToCustAddrInfo.get("LOC_NUM"));
        }
    }

    /**
     * Calculation Line to Get Line Info bizMsg NWAL1840CMsg
     * @param bizMsg NWAL1840CMsg
     */
    public static void calcLineInfo(NWAL1840CMsg bizMsg) {
        calcPlnForLine(bizMsg);
        setLinePrice(bizMsg);
    }

    /**
     * bizMsg NWAL1840CMsg
     * @param bizMsg NWAL1840CMsg
     */
    public static void calcPlnForLine(NWAL1840CMsg bizMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            BigDecimal schdAgmtLineNumA = bizMsg.A.no(i).schdAgmtLineNum_A.getValue();
            BigDecimal schdQty = BigDecimal.ZERO;
            BigDecimal dlvrQty = BigDecimal.ZERO;

            boolean countFlg = true;
            for (int j = 0; j < bizMsg.B.getValidCount(); j++) {

                BigDecimal schdAgmtLineNumB = bizMsg.B.no(j).schdAgmtLineNum_B.getValue();
                if (schdAgmtLineNumA.compareTo(schdAgmtLineNumB) == 0) {
                    schdQty = schdQty.add(bizMsg.B.no(j).ordQty_BS.getValue());
                    dlvrQty = dlvrQty.add(bizMsg.B.no(j).ordQty_BD.getValue());
                    countFlg = false;
                } else if (!countFlg) {
                    break;
                }
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ordQty_SC, schdQty);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ordQty_DE, dlvrQty);
        }
    }

    /**
     * Set Line Price
     * @param bizMsg NWAL1840CMsg
     */
    private static void setLinePrice(NWAL1840CMsg bizMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1840_ACMsg itemLineMsg = bizMsg.A.no(i);
            BigDecimal targetLineNum = itemLineMsg.schdAgmtLineNum_A.getValue();

            BigDecimal discAmt = BigDecimal.ZERO;
            BigDecimal frtChrgAmt = BigDecimal.ZERO;
            BigDecimal taxAmt = BigDecimal.ZERO;

            for (int j = 0; j < bizMsg.E.getValidCount(); j++) {
                NWAL1840_ECMsg calcBaseMsg = bizMsg.E.no(j);
                String prcDtlGrpCd = calcBaseMsg.prcDtlGrpCd_E.getValue();
                BigDecimal calcPrcAmt = calcBaseMsg.calcPrcAmtRate_E.getValue();

                if (!ZYPCommonFunc.hasValue(calcPrcAmt)) {
                    continue;
                }
                // QC#22965 2018/06/05 Add Start
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, calcBaseMsg.prcCondManDelFlg_E.getValue())) {
                    continue;
                }
                // QC#22965 2018/06/05 Add End

                if (targetLineNum.compareTo(calcBaseMsg.schdAgmtLineNum_E.getValue()) == 0) {
                    if (PRC_DTL_GRP.DISCOUNT.equals(prcDtlGrpCd) || PRC_DTL_GRP.SPECIAL_PRICE.equals(prcDtlGrpCd) || PRC_DTL_GRP.NET_DISCOUT.equals(prcDtlGrpCd)) {
                        discAmt = discAmt.add(calcPrcAmt);
                    } else if (PRC_DTL_GRP.FREIGHT.equals(prcDtlGrpCd) || PRC_DTL_GRP.SPECIAL_CHARGE.equals(prcDtlGrpCd)) {
                        frtChrgAmt = frtChrgAmt.add(calcPrcAmt);
                    // QC#9694 2016/07/21 Add Start
                    } else if (PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(prcDtlGrpCd)){
                        discAmt = discAmt.add(calcPrcAmt);
                    } else if (PRC_DTL_GRP.ROUNDING_FACTOR_1.equals(prcDtlGrpCd)){
                        frtChrgAmt = frtChrgAmt.add(calcPrcAmt);
                    // QC#9694 2016/07/21 Add End
                    } else if (PRC_DTL_GRP.TAX.equals(prcDtlGrpCd)) {
                        taxAmt = taxAmt.add(calcPrcAmt);
                    // QC#21841 2018/05/21 Add Start
                    } else if (PRC_DTL_GRP.HANDLING_FEE.equals(prcDtlGrpCd) || PRC_DTL_GRP.FUEL_SURCHARGE.equals(prcDtlGrpCd) || PRC_DTL_GRP.SHIPPING_FEE.equals(prcDtlGrpCd)) {
                        frtChrgAmt = frtChrgAmt.add(calcPrcAmt);
                    // QC#27479 2018/08/03 Add Start
                    } else if (PRC_DTL_GRP.RESTOCKING_FEE.equals(prcDtlGrpCd)) {
                        frtChrgAmt = frtChrgAmt.add(calcPrcAmt);
                    // QC#27479 2018/08/03 Add End
                    } else if (PRC_DTL_GRP.ROUNDING_FACTOR_2.equals(prcDtlGrpCd)) {
                        frtChrgAmt = frtChrgAmt.add(calcPrcAmt);
                    // QC#21841 2018/05/21 Add End
                    }
                }
            }

            // QC#18332 2017/06/26 Mod Start
//            ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_A, discAmt);
            // 2018/04/10 QC#25013 add start
            if (BigDecimal.ZERO.compareTo(itemLineMsg.schdAllwQty_A.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_A, BigDecimal.ZERO);
            } else {
                //// 2018/04/10 QC#25013 add end
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotDiscAmt_A, discAmt.divide(itemLineMsg.schdAllwQty_A.getValue(), BigDecimal.ROUND_HALF_UP));
            }
            // QC#18332 2017/06/26 Mod End
            ZYPEZDItemValueSetter.setValue(itemLineMsg.schdAgmtLineDealFrtAmt_A, frtChrgAmt);
            ZYPEZDItemValueSetter.setValue(itemLineMsg.schdAgmtLineDealTaxAmt_A, taxAmt);
            // QC#13036 2016/09/02 Add Start
            BigDecimal total = itemLineMsg.schdAgmtLineDealNetAmt_A.getValue().add(frtChrgAmt).add(taxAmt);
            ZYPEZDItemValueSetter.setValue(itemLineMsg.xxTotAmt_A, total);
            // QC#13036 2016/09/02 Add End
        }
    }

    /**
     * get Schedule Summary for Header
     * @param bizMsg NWAL1840CMsg
     */
    public static void getSummary(NWAL1840CMsg bizMsg) {

        getScheduleSummary(bizMsg);
        getSchedulePricingSummary(bizMsg);

    }

    /**
     * set SubmitDate Pulldown List
     * @param bizMsg NWAL1840CMsg
     */
    public static void getOrderSubmitDtList(NWAL1840CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1840QueryForSearch.getInstance().getPlnTsTxtList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            return;
        }

        List<Map<String, String>> ordCratTsInfo = (List<Map<String, String>>) ssmResult.getResultObject();

        // START 2023/03/14 R.Azucena [QC#61286 MOD]
        // for (int i = 0; i < ordCratTsInfo.size(); i++) {
        int maxLen = ordCratTsInfo.size();

        if (maxLen > ORDR_SUBMT_DT_LIST_MAX_LEN) {
            maxLen = ORDR_SUBMT_DT_LIST_MAX_LEN;
        }

        for (int i = 0; i < maxLen; i++) {
        // END 2023/03/14 R.Azucena [QC#61286 MOD]
            Map<String, String> resultMap = (Map<String, String>) ordCratTsInfo.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxViewNm_CD.no(i), resultMap.get("XX_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxViewNm_NM.no(i), resultMap.get("DISP_ORD_CRAT_TS"));
        }
    }

    /**
     * set BackUp to glblMsg
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    public static void getBackUp(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        EZDMsg.copy(bizMsg, null, glblMsg, null);

        ZYPTableUtil.clear(glblMsg.D);
    }

    /**
     * get Schedule Summary
     * @param bizMsg NWAL1840CMsg
     */
    public static void getScheduleSummary(NWAL1840CMsg bizMsg) {

        Map<String, Map<String, BigDecimal>> summaryMap = new HashMap<String, Map<String, BigDecimal>>();
        BigDecimal allwQtySum;
        BigDecimal schdQtySum;
        BigDecimal dlvrQtySum;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // Only Not Cancels
            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).schdAgmtLineCancFlg_A.getValue())) {

                String itemNm = bizMsg.A.no(i).mdseDescShortTxt_A.getValue();
                if (summaryMap.containsKey(itemNm)) {
                    Map<String, BigDecimal> qtyMap = summaryMap.get(itemNm);
                    allwQtySum = qtyMap.get(ALLOWED_QTY).add(bizMsg.A.no(i).schdAllwQty_A.getValue());
                    schdQtySum = qtyMap.get(ORDERED_QTY).add(bizMsg.A.no(i).ordQty_SC.getValue());
                    dlvrQtySum = qtyMap.get(DELIVERED_QTY).add(bizMsg.A.no(i).ordQty_DE.getValue());

                    qtyMap.put(ALLOWED_QTY, allwQtySum);
                    qtyMap.put(ORDERED_QTY, schdQtySum);
                    qtyMap.put(DELIVERED_QTY, dlvrQtySum);
                } else {
                    Map<String, BigDecimal> qtyMap = new HashMap<String, BigDecimal>();

                    qtyMap.put(ALLOWED_QTY, bizMsg.A.no(i).schdAllwQty_A.getValue());
                    qtyMap.put(ORDERED_QTY, bizMsg.A.no(i).ordQty_SC.getValue());
                    qtyMap.put(DELIVERED_QTY, bizMsg.A.no(i).ordQty_DE.getValue());

                    summaryMap.put(bizMsg.A.no(i).mdseDescShortTxt_A.getValue(), qtyMap);
                }

            }
        }

        Set<String> keySet = summaryMap.keySet();

        int i = 0;
        for (Iterator<String> ite = keySet.iterator(); ite.hasNext();) {
            if (i == bizMsg.G.length()) {
                return;
            }
            String itemNm = ite.next();
            Map<String, BigDecimal> qtyMap = summaryMap.get(itemNm);

            ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).mdseDescShortTxt_G, itemNm);
            ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).schdAllwQty_G, qtyMap.get(ALLOWED_QTY));
            ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).ordQty_G, qtyMap.get(ORDERED_QTY));
            ZYPEZDItemValueSetter.setValue(bizMsg.G.no(i).shipQty_G, qtyMap.get(DELIVERED_QTY));
            i++;
        }
        bizMsg.G.setValidCount(summaryMap.size());
    }

    /**
     * @param bizMsg NWAL1840CMsg
     */
    public static void getSchedulePricingSummary(NWAL1840CMsg bizMsg) {
        BigDecimal subTotAmt = BigDecimal.ZERO;
        BigDecimal chargeAmt = BigDecimal.ZERO;
        BigDecimal taxAmt = BigDecimal.ZERO;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // Only Not Cancels
            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).schdAgmtLineCancFlg_A.getValue())) {

                subTotAmt = subTotAmt.add(bizMsg.A.no(i).schdAgmtLineDealNetAmt_A.getValue());
                chargeAmt = chargeAmt.add(bizMsg.A.no(i).schdAgmtLineDealFrtAmt_A.getValue());
                taxAmt = taxAmt.add(bizMsg.A.no(i).schdAgmtLineDealTaxAmt_A.getValue());
            }
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxSubTotCalcPrcAmt, subTotAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotChrgPrcAmt, chargeAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt, taxAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt, subTotAmt.add(chargeAmt).add(taxAmt));

    }

    /**
     * Mandatory Check for Additional Header
     * @param bizMsg Business Message
     * @return true: normal false: has error
     */
    public static boolean checkMandatoryHeaderTab(NWAL1840CMsg bizMsg) {

        boolean isNormal = true;

        // S21NA#7861 Mod Start
        // if (!ZYPCommonFunc.hasValue(bizMsg.slsRepPsnCd)) {
        // bizMsg.slsRepPsnCd.setErrorInfo(1, ZZM9000E, new String[] {"Number" });
        // isNormal = false;
        // }
        if (!ZYPCommonFunc.hasValue(bizMsg.psnNum)) {
            bizMsg.psnNum.setErrorInfo(1, ZZM9000E, new String[] {"Number" });
            isNormal = false;
        }
        // S21NA#7861 Mod End 
        if (!ZYPCommonFunc.hasValue(bizMsg.slsRepTocNm)) {
            bizMsg.slsRepTocNm.setErrorInfo(1, ZZM9000E, new String[] {"Sales Rep" });
            isNormal = false;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.frtCondDescTxt)) {
            bizMsg.frtCondDescTxt.setErrorInfo(1, ZZM9000E, new String[] {"Freight Terms" });
            isNormal = false;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.shpgSvcLvlCd)) {
            bizMsg.shpgSvcLvlCd.setErrorInfo(1, ZZM9000E, new String[] {"Service Level" });
            isNormal = false;
        }

        return isNormal;
    }

    /**
     * Set new Line for Add_line
     * @param bizMsg NWAL1840CMsg
     */
    public static void setNewLine(NWAL1840CMsg bizMsg) {

        NWAL1840_ACMsg newLine = bizMsg.A.no(bizMsg.A.getValidCount());

        ZYPEZDItemValueSetter.setValue(newLine.schdAgmtNum_A, bizMsg.schdAgmtNum);
        ZYPEZDItemValueSetter.setValue(newLine.schdAgmtLineNum_A, new BigDecimal(getAddLineNum(bizMsg.A)));
        ZYPEZDItemValueSetter.setValue(newLine.xxLineNum_A, getAddLineNum(bizMsg.A));
        ZYPEZDItemValueSetter.setValue(newLine.supdLockFlg_A, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(newLine.supdLockFlg_A, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(newLine.ccyCd_A, bizMsg.ccyCd);
        ZYPEZDItemValueSetter.setValue(newLine.ordQty_SC, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.ordQty_DE, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.dealPrcListPrcAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.xxTotDiscAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.dealNetUnitPrcAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.schdAgmtLineDealNetAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.schdAgmtLineDealFrtAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.schdAgmtLineDealTaxAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.xxTotAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.stkStsCd_A, STK_STS.GOOD);
        // START 2022/06/01 [QC#59973, MOD]
//      ZYPEZDItemValueSetter.setValue(newLine.shpgIntvlCd_A, SHPG_INTVL.MTH);
//      ZYPEZDItemValueSetter.setValue(newLine.otmShipQty_A, BigDecimal.ZERO);
        newLine.shpgIntvlCd_A.clear();
        newLine.otmShipQty_A.clear();
        // END   2022/06/01 [QC#59973, MOD]
        ZYPEZDItemValueSetter.setValue(newLine.funcNetUnitPrcAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.funcPrcListPrcAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.schdAgmtLineFuncNetAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.schdAgmtLineFuncFrtAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.schdAgmtLineFuncTaxAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.exchRate_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.manPrcFlg_A, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(newLine.schdAgmtLineCancFlg_A, ZYPConstant.FLG_OFF_N);

        bizMsg.A.setValidCount(bizMsg.A.getValidCount() + 1);
    }

    /**
     * get new Line Number for Add_line
     * @param lineMsg NWAL1840_ACMsgArray
     * @return String
     */
    public static String getAddLineNum(NWAL1840_ACMsgArray lineMsg) {
        return String.valueOf(lineMsg.getValidCount() + 1);
    }

    /**
     * insert new row
     * @param list EZDMsgArray
     * @param insertRow int
     * @return new line
     */
    public static EZDMsg insertNewLine(EZDMsgArray list, int insertRow) {

        if (list.getValidCount() >= list.length()) {
            return null;
        }

        if (list.getValidCount() == 0) {

            list.setValidCount(1);
            return list.get(insertRow);
        }

        for (int i = list.getValidCount() - 1; insertRow <= i; i--) {
            EZDMsg.copy(list.get(i), null, list.get(i + 1), null);
        }
        list.get(insertRow).clear();
        list.setValidCount(list.getValidCount() + 1);
        return list.get(insertRow);
    }

    /**
     * Set new Line for Add_line
     * @param bizMsg NWAL1840CMsg
     */
    public static void setNewSchdLine(NWAL1840CMsg bizMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);
        if (checkList.size() == 0) {
            if (bizMsg.A.getValidCount() != 1) {
                bizMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_LINE });
                return;
            }
            checkList.add(0);
        }

        for (int checkIndex : checkList) {

            NWAL1840_ACMsg lineMsg = bizMsg.A.no(checkIndex);
            if (ZYPConstant.FLG_ON_Y.equals(lineMsg.xxSmryLineFlg_A.getValue())) {
                continue;
            }

            BigDecimal lineNum = lineMsg.schdAgmtLineNum_A.getValue();

            int maxSchdNum = getMaxLineNum(bizMsg.B, lineNum);

            int newShcdRowLineNum = getAddLineRow(bizMsg.B, lineNum, maxSchdNum);

            NWAL1840_BCMsg newSchdLine = (NWAL1840_BCMsg) insertNewLine(bizMsg.B, newShcdRowLineNum);
            if (newSchdLine == null) {
                lineMsg.xxChkBox_A.setErrorInfo(1, NWAM0831E);
            }

            ZYPEZDItemValueSetter.setValue(newSchdLine.schdAgmtNum_B, bizMsg.schdAgmtNum);
            ZYPEZDItemValueSetter.setValue(newSchdLine.schdAgmtLineNum_B, lineNum);
            ZYPEZDItemValueSetter.setValue(newSchdLine.schdAgmtSchdLineNum_B, new BigDecimal(maxSchdNum + 1));
            ZYPEZDItemValueSetter.setValue(newSchdLine.xxLineNum_B, getAddSchdLineNum(lineNum, bizMsg.B, newShcdRowLineNum, maxSchdNum));
            ZYPEZDItemValueSetter.setValue(newSchdLine.ordQty_B, lineMsg.otmShipQty_A);
            ZYPEZDItemValueSetter.setValue(newSchdLine.schdAgmtPlnCancFlg_B, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(newSchdLine.ordQty_BD, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(newSchdLine.ordQty_BS, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(newSchdLine.xxExstFlg_B, ZYPConstant.FLG_OFF_N);

        }
    }

    /**
     * Get Schedule Line Number in the Line
     * @param schdLineList NWAL1840_BCMsgArray
     * @param lineNum int
     * @return int
     */
    public static int getMaxLineNum(NWAL1840_BCMsgArray schdLineList, BigDecimal lineNum) {

        int maxSchdNum = 0;
        for (int i = 0; i < schdLineList.getValidCount(); i++) {

            NWAL1840_BCMsg schdLine = schdLineList.no(i);

            if (schdLine.schdAgmtLineNum_B.getValueInt() == lineNum.intValue()) {
                maxSchdNum = schdLine.schdAgmtSchdLineNum_B.getValueInt();
            }
        }
        return maxSchdNum;
    }

    /**
     * Get Schedule LineNumber to Insert in the Schedule Order
     * @param schdLineList NWAL1840_BCMsgArray
     * @param lineNum BigDecimal
     * @param maxSchdNum int
     * @return int
     */
    public static int getAddLineRow(NWAL1840_BCMsgArray schdLineList, BigDecimal lineNum, int maxSchdNum) {

        int insertRowNum = schdLineList.getValidCount();
        for (int i = 0; i < schdLineList.getValidCount(); i++) {

            NWAL1840_BCMsg schdLine = schdLineList.no(i);

            if (lineNum.intValue() > schdLine.schdAgmtLineNum_B.getValueInt()) {
                insertRowNum = i + 1;
            }
            if (lineNum.intValue() == schdLine.schdAgmtLineNum_B.getValueInt()) {
                if (schdLine.schdAgmtSchdLineNum_B.getValueInt() == maxSchdNum) {
                    return i + 1;
                }
            }
            if (lineNum.intValue() < schdLine.schdAgmtLineNum_B.getValueInt()) {
                return i;
            }
        }

        return insertRowNum;
    }

    /**
     * get new Schedule LineNumber for Display
     * @param lineNum BigDecimal
     * @param schdLineMsgArray NWAL1840_BCMsgArray
     * @param schdLineNum int
     * @param maxSchdNum int
     * @return String
     */
    public static String getAddSchdLineNum(BigDecimal lineNum, NWAL1840_BCMsgArray schdLineMsgArray, int schdLineNum, int maxSchdNum) {

        if (maxSchdNum == 0) {

            String nextSchdLineNum = lineNum.toEngineeringString().concat(".").concat("1");
            return nextSchdLineNum;
        }

        String[] schdLineNums = schdLineMsgArray.no(schdLineNum - 1).xxLineNum_B.getValue().split("\\.");
        int nextSchdNum = Integer.parseInt(schdLineNums[1]) + 1;
        schdLineNums[1] = String.valueOf(nextSchdNum);
        String nextSchdLineNum = schdLineNums[0].concat(".").concat(schdLineNums[1]);
        return nextSchdLineNum;
    }

    /**
     * get Status List After "Shipped"
     * @return List<String>
     */
    public static List<String> getAfterShippedSts() {
        // Mod Start 2018/10/05 QC#28510
        //List<String> afterShippedStslist = new ArrayList<String>(IDX_8);
        //afterShippedStslist.add(SHPG_STS.SHIPPED);
        //afterShippedStslist.add(SHPG_STS.S_OR_O_CANCELLED);
        //afterShippedStslist.add(SHPG_STS.P_OR_O_CANCELLED);
        //afterShippedStslist.add(SHPG_STS.P_OR_O_PRINTED);
        List<String> afterShippedStslist = new ArrayList<String>(IDX_3);
        // Mod End 2018/10/05 QC#28510
        afterShippedStslist.add(SHPG_STS.ARRIVED);
        // Del Start 2018/10/05 QC#28510
        //afterShippedStslist.add(SHPG_STS.N_INVOICE_READY);
        // Del End 2018/10/05 QC#28510
        afterShippedStslist.add(SHPG_STS.INVOICED);
        afterShippedStslist.add(SHPG_STS.INSTALLED);

        return afterShippedStslist;
    }

    /**
     * get Merchandise Short Text by Merchandise Code
     * @param mdseCd String
     * @param glblCmpyCd String
     * @return String
     */
    public static String getMdseShortTxt(String mdseCd, String glblCmpyCd) {

        MDSETMsg dsMdseInfoTmsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(dsMdseInfoTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsMdseInfoTmsg.mdseCd, mdseCd);

        dsMdseInfoTmsg = (MDSETMsg) S21FastTBLAccessor.findByKey(dsMdseInfoTmsg);

        if (dsMdseInfoTmsg == null) {
            return null;
        }
        return (String) dsMdseInfoTmsg.mdseDescShortTxt.getValue();
    }

    /**
     * order Cooperated check
     * @param bizMsg NWAL1840CMsg
     * @return boolean
     */
    public static boolean orderCooperateCheck(NWAL1840CMsg bizMsg) {

        boolean checkFlg = true;
        List<Integer> selectedRowsA = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);
        List<Integer> selectedRowsB = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_B", ZYPConstant.FLG_ON_Y);

        // Check Schedule Line
        for (int i : selectedRowsB) {
            NWAL1840_BCMsg schdLine = bizMsg.B.no(i);

            if (ZYPConstant.FLG_ON_Y.equals(schdLine.xxExstFlg_B.getValue())) {
                schdLine.xxChkBox_B.setErrorInfo(1, NWAM0801E, new String[] {MSG_PARAM_SCHD_LINE });
                checkFlg = false;
            }
        }

        // Check Line

        for (int i : selectedRowsA) {
            NWAL1840_ACMsg itemLine = bizMsg.A.no(i);
            BigDecimal lineNum = itemLine.schdAgmtLineNum_A.getValue();

            for (int j = 0; j < bizMsg.B.getValidCount(); j++) {
                BigDecimal lineNumB = bizMsg.B.no(j).schdAgmtLineNum_B.getValue();

                if (lineNumB.compareTo(lineNum) == 0) {
                    if (ZYPConstant.FLG_ON_Y.equals(bizMsg.B.no(j).xxExstFlg_B.getValue())) {
                        itemLine.xxChkBox_A.setErrorInfo(1, NWAM0801E, new String[] {MSG_PARAM_LINE });
                        checkFlg = false;
                        continue;
                    }
                }
            }
        }

        return checkFlg;
    }

    /**
     * Cancel Schedule Line
     * @param bizMsg NWAL1840CMsg
     */
    public static void scheduleCancel(NWAL1840CMsg bizMsg) {

        List<Integer> selectedRowsB = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_B", ZYPConstant.FLG_ON_Y);

        for (int i = selectedRowsB.size() - 1; i >= 0; i--) {
            NWAL1840_BCMsg cancelSchdLine = bizMsg.B.no(selectedRowsB.get(i));

            if (ZYPConstant.FLG_ON_Y.equals(cancelSchdLine.schdAgmtPlnCancFlg_B.getValue()) //
                    && ZYPConstant.FLG_ON_Y.equals(cancelSchdLine.xxExstFlg_B.getValue())) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(cancelSchdLine.schdAgmtSchdLineNum_DB)) {
                // Cancel Registered data
                cancelSchdLine.xxChkBox_B.clear();
                ZYPEZDItemValueSetter.setValue(cancelSchdLine.schdAgmtPlnCancFlg_B, ZYPConstant.FLG_ON_Y);
            } else {
                // Cancel Unregistered data
                deleteSchdLine(bizMsg.B, selectedRowsB.get(i).intValue());
            }
        }

    }

    /**
     * Cancel Line
     * @param bizMsg NWAL1840CMsg
     */
    public static void lineCancel(NWAL1840CMsg bizMsg) {

        List<Integer> selectedRowsA = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);

        for (int i = selectedRowsA.size() - 1; i >= 0; i--) {
            NWAL1840_ACMsg cancelLine = bizMsg.A.no(selectedRowsA.get(i));

            int cancelLineNum = cancelLine.schdAgmtLineNum_A.getValueInt();

            if (ZYPCommonFunc.hasValue(cancelLine.schdAgmtLineNum_DB)) {
                // Cancel Registered data
                cancelLine.xxChkBox_A.clear();
                ZYPEZDItemValueSetter.setValue(cancelLine.schdAgmtLineCancFlg_A, ZYPConstant.FLG_ON_Y);

            } else {
                // Cancel Unregistered data
                deleteLine(bizMsg.A, cancelLine.schdAgmtLineNum_A.getValueInt());
            }

            // cancel Linked Schedule
            for (int j = 0; j < bizMsg.B.getValidCount(); j++) {
                if (cancelLineNum == bizMsg.B.no(j).schdAgmtLineNum_B.getValueInt()) {
                    bizMsg.B.no(j).xxChkBox_B.setValue(ZYPConstant.FLG_ON_Y);
                }
            }
            scheduleCancel(bizMsg);
        }

    }

    /**
     * delete Schedule Line
     * @param schdLineArray NWAL1840_BCMsgArray
     * @param selectSchdLineNum int
     */
    public static void deleteSchdLine(NWAL1840_BCMsgArray schdLineArray, int selectSchdLineNum) {

        NWAL1840_BCMsg deleteSchdLine = schdLineArray.no(selectSchdLineNum);
        int deleteLineNum = deleteSchdLine.schdAgmtLineNum_B.getValueInt();
        int deleteSchdLineNum = deleteSchdLine.schdAgmtSchdLineNum_B.getValueInt();

        for (int i = 0; i < schdLineArray.getValidCount(); i++) {

            int checkLineNum = schdLineArray.no(i).schdAgmtLineNum_B.getValueInt();
            int checkSchdLineNum = schdLineArray.no(i).schdAgmtSchdLineNum_B.getValueInt();

            if (deleteLineNum > checkLineNum) {
                continue;

            } else if (deleteLineNum == checkLineNum) {

                if (deleteSchdLineNum > checkSchdLineNum) {
                    continue;
                } else if (deleteSchdLineNum == checkSchdLineNum) {
                    deleteSchdLine.xxChkBox_B.clear();
                    deleteSchdLine.rddDt_B.clear();
                    deleteSchdLine.ordQty_B.clear();
                    continue;

                } else if (deleteSchdLineNum < checkSchdLineNum) {
                    String xxLineNum = schdLineArray.no(i - 1).xxLineNum_B.getValue();
                    BigDecimal schdAgmtSchdLineNum = schdLineArray.no(i - 1).schdAgmtSchdLineNum_B.getValue();

                    EZDMsg.copy(schdLineArray.no(i), null, schdLineArray.no(i - 1), null);
                    schdLineArray.no(i - 1).xxLineNum_B.setValue(xxLineNum);
                    schdLineArray.no(i - 1).schdAgmtSchdLineNum_B.setValue(schdAgmtSchdLineNum);
                }

            } else if (deleteLineNum < checkLineNum) {
                EZDMsg.copy(schdLineArray.no(i), null, schdLineArray.no(i - 1), null);
            }

        }

        schdLineArray.setValidCount(schdLineArray.getValidCount() - 1);
    }

    /**
     * delete Line
     * @param lineArray NWAL1840_ACMsg
     * @param deleteLineNum int
     */
    // S21NA#13033 Mod Start
    public static void deleteLine(NWAL1840_ACMsgArray lineArray, int deleteLineNum) {
        String xxLineNum = "";
        for (int i = 0; i < lineArray.getValidCount(); i++) {

            int checkLineNum = lineArray.no(i).schdAgmtLineNum_A.getValueInt();
            if (deleteLineNum > checkLineNum) {
                continue;

            } else if (deleteLineNum == checkLineNum) {
                xxLineNum = lineArray.no(i).xxLineNum_A.getValue();
                lineArray.no(i).clear();
                continue;
            } else if (deleteLineNum < checkLineNum) {
                EZDMsg.copy(lineArray.no(i), null, lineArray.no(i - 1), null);

                lineArray.no(i - 1).xxLineNum_A.setValue(xxLineNum);
                xxLineNum = String.valueOf(Integer.valueOf(xxLineNum) + 1);

                lineArray.no(i - 1).schdAgmtLineNum_A.setValue(checkLineNum - 1);
            }

        }
        lineArray.no(lineArray.getValidCount() - 1).clear();
        lineArray.setValidCount(lineArray.getValidCount() - 1);
    }
    // S21NA#13033 Mod End

    /**
     * get Bill/Sold Location for 1240
     * @param bizMsg NWAL1840
     */
    public static void getCustInfoFor1240(NWAL1840CMsg bizMsg) {
        // 2016/07/11 S21_NA#5030 Mod Start
        S21SsmEZDResult ssmResult = null;
        if (ZYPCommonFunc.hasValue(bizMsg.serNum)) {
            ssmResult = NWAL1840Query.getInstance().getDefCust(bizMsg);
        } else {
            return;
        }
        // 2016/07/11 S21_NA#5030 Mod End
        if (ssmResult.getQueryResultCount() != 0) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            Map<String, String> resultMap = (Map<String, String>) resultList.get(0);
            // QC#11654 2016/09/02 Mod Start
            // ZYPEZDItemValueSetter.setValue(bizMsg.billToCustLocCd, resultMap.get("BASE_BILL_TO_CUST_CD"));
            // ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, resultMap.get("OWNR_LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustLocCd, resultMap.get("BILL_TO_LOC_NUM"));
            // 2017/09/18 S21_NA#21190 Mod Start
//            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustLocCd, resultMap.get("OWNR_LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustLocCd, resultMap.get("CUR_LOC_NUM"));
            // 2017/09/18 S21_NA#21190 Mod Start
            ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, resultMap.get("BILL_TO_LOC_NUM"));
            // QC#11654 2016/09/02 Mod End

        }
    }

    // QC#15875 2016/11/16 Add Start
    /**
     * get Machine Info for 0110
     * @param bizMsg NWAL1840
     */
    public static void getMachineInfoFor0110(NWAL1840CMsg bizMsg) {
        S21SsmEZDResult ssmResult = null;
        if (ZYPCommonFunc.hasValue(bizMsg.dsContrDtlPk)) {
            ssmResult = NWAL1840Query.getInstance().getMachineInfo(bizMsg);
            if (ssmResult.getQueryResultCount() != 0) {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
                if (null != resultMap.get("SVC_CONFIG_MSTR_PK")) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk, (BigDecimal)resultMap.get("SVC_CONFIG_MSTR_PK"));    
                }
                if (null != resultMap.get("SER_NUM")) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.serNum, (String)resultMap.get("SER_NUM"));
                }
                if (null != resultMap.get("MDL_NM")) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.mdlNm, (String)resultMap.get("MDL_NM"));
                }
                if (null != resultMap.get("MDL_ID")) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.mdlId, (BigDecimal)resultMap.get("MDL_ID"));
                }
            }
        }
    }
    // QC#15875 2016/11/16 Add End

    /**
     * get Sold Location for 1240
     * @param bizMsg NWAL1840
     */
//    public static void getSoldCustInfo(NWAL1840CMsg bizMsg) {
//
//        S21SsmEZDResult ssmResult = NWAL1840Query.getInstance().getDefSold(bizMsg);
//
//        if (ssmResult.getQueryResultCount() != 0) {
//            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
//            Map<String, String> resultMap = (Map<String, String>) resultList.get(0);
//            ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, resultMap.get("OWNR_LOC_NUM"));
//
//        }
//    }

    /**
     * get Bill Location for 1240
     * @param bizMsg NWAL1840
     */
//    public static void getBillCustInfo(NWAL1840CMsg bizMsg) {
//
//        S21SsmEZDResult ssmResult = NWAL1840Query.getInstance().getDefBill(bizMsg);
//
//        if (ssmResult.getQueryResultCount() != 0) {
//            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
//            Map<String, String> resultMap = (Map<String, String>) resultList.get(0);
//            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustLocCd, resultMap.get("BASE_BILL_TO_CUST_CD"));
//
//        }
//    }

    /**
     * Master Check :Configuration Id
     * @param bizMsg NWAL1840CMsg
     * @return boolean - Error: false
     */
    public static boolean configMasterCheck(NWAL1840CMsg bizMsg) {
        SVC_CONFIG_MSTRTMsg svcConfMstrTmsg = new SVC_CONFIG_MSTRTMsg();
        svcConfMstrTmsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        svcConfMstrTmsg.svcConfigMstrPk.setValue(bizMsg.svcConfigMstrPk.getValue());

        svcConfMstrTmsg = (SVC_CONFIG_MSTRTMsg) EZDTBLAccessor.findByKey(svcConfMstrTmsg);

        if (svcConfMstrTmsg == null) {
            bizMsg.svcConfigMstrPk.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CONF });
            return false;
        }
        return true;
    }

    /**
     * get Contract Sequence number
     * @param bizMsg NWAL1840CMsg
     * @return String
     */
    public static String getDsContrSqNum(NWAL1840CMsg bizMsg) {

        // get contract End Date
        if (ZYPCommonFunc.hasValue(bizMsg.dsContrNum)) {
            DS_CONTRTMsg dsContrTmsg = new DS_CONTRTMsg();
            dsContrTmsg.setSQLID("003");
            dsContrTmsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
            dsContrTmsg.setConditionValue("dsContrNum01", bizMsg.dsContrNum.getValue());

            DS_CONTRTMsgArray dsContrTmsgArray = (DS_CONTRTMsgArray) EZDTBLAccessor.findByCondition(dsContrTmsg);

            if (dsContrTmsgArray.getValidCount() == 0) {
                return null;
            }

            String dsContrSqNum = "";
            for (int i = 0; i < dsContrTmsgArray.length(); i++) {
                if (!ZYPCommonFunc.hasValue(dsContrSqNum)) {
                    dsContrSqNum = dsContrTmsgArray.no(i).dsContrSqNum.getValue();

                } else if (dsContrSqNum.compareTo(dsContrTmsgArray.no(i).dsContrSqNum.getValue()) < 0) {
                    dsContrSqNum = dsContrTmsgArray.no(i).dsContrSqNum.getValue();
                }

            }
            return dsContrSqNum;
        } else {
            return null;
        }
    }

    /**
     * <pre>
     * set Shipping Service Level Pull Down 
     * @param bizMsg
     */
    public static void setShpgSvcLvlPullDown(NWAL1840CMsg bizMsg) {
        S21SsmEZDResult resltShpgSvcLvlRec = NWAL1840Query.getInstance().getShpgSvcLvlDataList(bizMsg.glblCmpyCd.getValue(), bizMsg.lineBizTpCd.getValue(), bizMsg.frtCondCd.getValue());
        if (resltShpgSvcLvlRec.isCodeNormal()) {
            List<Map<String, Object>> shpgSvcLvlRecList = (List<Map<String, Object>>) resltShpgSvcLvlRec.getResultObject();

            bizMsg.shpgSvcLvlCd_CD.clear();
            bizMsg.shpgSvcLvlDescTxt_NM.clear();
            int validCnt = 0;
            for (Map<String, Object> shpgSvcLvlRec : shpgSvcLvlRecList) {
                bizMsg.shpgSvcLvlCd_CD.no(validCnt).setValue((String) shpgSvcLvlRec.get("SHPG_SVC_LVL_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlDescTxt_NM.no(validCnt), (String) shpgSvcLvlRec.get("SHPG_SVC_LVL_DESC_TXT"));
                validCnt++;
            }
        }
    }

    /**
     * Compare EZDCItem and EZDSItem
     * @param cMsgItem EZDCItem
     * @param glblMsgItem EZDSItem
     * @return Same Item : true
     */
    public static boolean isEqualsEZDItem(EZDCItem cMsgItem, EZDSItem glblMsgItem) {

        if (!ZYPCommonFunc.hasValue(cMsgItem) && !ZYPCommonFunc.hasValue(glblMsgItem)) {
            return true;
        }

        if ((!ZYPCommonFunc.hasValue(cMsgItem) && ZYPCommonFunc.hasValue(glblMsgItem)) || (ZYPCommonFunc.hasValue(cMsgItem) && !ZYPCommonFunc.hasValue(glblMsgItem))) {
            return false;
        }

        if (cMsgItem instanceof EZDCStringItem && glblMsgItem instanceof EZDSStringItem) {
            return ((EZDCStringItem) cMsgItem).getValue().equals(((EZDSStringItem) glblMsgItem).getValue());
        }

        if (cMsgItem instanceof EZDCDateItem && glblMsgItem instanceof EZDSDateItem) {
            return ((EZDCDateItem) cMsgItem).getValue().equals(((EZDSDateItem) glblMsgItem).getValue());
        }

        if (cMsgItem instanceof EZDCBigDecimalItem && glblMsgItem instanceof EZDSBigDecimalItem) {
            int compareRslt = ((EZDCBigDecimalItem) cMsgItem).getValue().compareTo(((EZDSBigDecimalItem) glblMsgItem).getValue());
            if (0 == compareRslt) {
                return true;
            }
        }

        return false;
    }

    /**
     * Compare EZDCItem and EZDCItem
     * @param origCMsgItem EZDCItem
     * @param trgtCMsgItem EZDCItem
     * @return Same Item : true
     */
    public static boolean isEqualsEZDItem(EZDCItem origCMsgItem, EZDCItem trgtCMsgItem) {

        if (!ZYPCommonFunc.hasValue(origCMsgItem) && !ZYPCommonFunc.hasValue(trgtCMsgItem)) {
            return true;
        }

        if ((!ZYPCommonFunc.hasValue(origCMsgItem) && ZYPCommonFunc.hasValue(trgtCMsgItem)) || (ZYPCommonFunc.hasValue(origCMsgItem) && !ZYPCommonFunc.hasValue(trgtCMsgItem))) {
            return false;
        }

        if (origCMsgItem instanceof EZDCStringItem && trgtCMsgItem instanceof EZDCStringItem) {
            return ((EZDCStringItem) origCMsgItem).getValue().equals(((EZDCStringItem) trgtCMsgItem).getValue());
        }

        if (origCMsgItem instanceof EZDCDateItem && trgtCMsgItem instanceof EZDCDateItem) {
            return ((EZDCDateItem) origCMsgItem).getValue().equals(((EZDCDateItem) trgtCMsgItem).getValue());
        }

        if (origCMsgItem instanceof EZDCBigDecimalItem && trgtCMsgItem instanceof EZDCBigDecimalItem) {
            int compRslt = ((EZDCBigDecimalItem) origCMsgItem).getValue().compareTo(((EZDCBigDecimalItem) trgtCMsgItem).getValue());
            if (0 == compRslt) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get Merchandise Message
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return MDSETmsg
     */
    public static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

        return NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
    }

    public static Map<String, String> getFreightTermInfo(NWAL1840CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NWAL1840Query.getInstance().getFreightTermInfoList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.frtCondDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_FRT_TERMS });
            return null;
        }

        List<Map<String, String>> freightTermInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (freightTermInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return freightTermInfoList.get(0);
    }

    // 2017/03/14 S21_NA#16855 Add Start
    /**
     * <pre>
     * Get Territory Group Type Code From DS Order Type Code
     * @param bizMsg
     * </pre>
     */
    // 2017/12/12 Sol#349(QC#19804) Mod Start
//    public static String getTrtyGrpTpCdFromDsOrdTpCd(NWAL1840CMsg bizMsg) {
    public static String getTrtyGrpTpTxtFromDsOrdTpCd(NWAL1840CMsg bizMsg) {
    // 2017/12/12 Sol#349(QC#19804) Mod End

        if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            return null;
        }

        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);

        dsOrdTpProcDfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(dsOrdTpProcDfnTMsg);
        if (dsOrdTpProcDfnTMsg != null) {
            // 2017/12/12 Sol#349(QC#19804) Mod Start
            //return dsOrdTpProcDfnTMsg.trtyGrpTpCd.getValue();
            return dsOrdTpProcDfnTMsg.trtyGrpTpTxt.getValue();
            // 2017/12/12 Sol#349(QC#19804) Mod End
        }

        return null;
    }
    // 2017/03/14 S21_NA#16855 Add End
    
    // 2018/02/08 S21_NA#20297(Sol#379) Add Start
    static void setDefShipInfo(NWAL1840CMsg bizMsg) {
        
        if(!hasValue(bizMsg.lineBizTpCd) || !hasValue(bizMsg.dsOrdCatgCd) || !hasValue(bizMsg.dsOrdTpCd)//
                || !hasValue(bizMsg.shipToCustLocCd) || !hasValue(bizMsg.shipToCustAcctCd) || !containDsOrdTp(bizMsg)) { 
            return;
        }
        setDefFrtInfo(bizMsg);
        setDefShpgCmt(bizMsg);
    }
    
    private static void setDefFrtInfo(NWAL1840CMsg bizMsg) {
        
        NMZC610001PMsg nmzc6100PMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_SHIPPING_DEFAULT_INFORMATION);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.shipToCustCd, bizMsg.shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.dsAcctNum_I1, bizMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.slsDt, bizMsg.slsDt);

        new NMZC610001().execute(nmzc6100PMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(nmzc6100PMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nmzc6100PMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
            }
        }
        
        if (hasValue(nmzc6100PMsg.ShippingDefaultInfoList.no(0).frtCondCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, nmzc6100PMsg.ShippingDefaultInfoList.no(0).frtCondCd);
            setFrtCondDescTxt(bizMsg);
            NWAL1840CommonLogic.setShpgSvcLvlPullDown(bizMsg);
        }
        
        if (hasValue(nmzc6100PMsg.ShippingDefaultInfoList.no(0).shpgSvcLvlCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd, nmzc6100PMsg.ShippingDefaultInfoList.no(0).shpgSvcLvlCd);
        }
    }

    private static void setDefShpgCmt(NWAL1840CMsg bizMsg) {
        
        if(hasValue(bizMsg.shpgCmntTxt)){
            return;
        }
        
        NMZC610001PMsg nmzc6100PMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_INSTRUCTION);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.dsBizAreaCd, getDsBizAreaCd(bizMsg));
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.dsCustMsgTpCd, DS_CUST_MSG_TP.SHIP);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.shipToCustCd, bizMsg.shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.slsDt, bizMsg.slsDt);

        new NMZC610001().execute(nmzc6100PMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(nmzc6100PMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nmzc6100PMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
            }
        }

        int count = nmzc6100PMsg.InstructionList.getValidCount();
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < count; i++) {
            if (hasValue(nmzc6100PMsg.InstructionList.no(i).dsCustMsgTxt)) {
                if (i != 0) {
                    sb.append(NEW_LINE);
                }
                sb.append(nmzc6100PMsg.InstructionList.no(i).dsCustMsgTxt.getValue());
                if (sb.length() > SHPG_CMT_TXT_LIMIT_SIZE) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.shpgCmntTxt, sb.substring(0, SHPG_CMT_TXT_LIMIT_SIZE));
                    return;
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.shpgCmntTxt, sb.toString());
    }

    private static String getDsBizAreaCd(NWAL1840CMsg bizMsg) {

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();

        condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("ordCatgCtxTpCd01A", EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", bizMsg.dsOrdCatgCd.getValue());

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).scdBizCtxAttrbTxt.getValue();
        }

        return BLANK;
    }
    
    private static void setFrtCondDescTxt(NWAL1840CMsg bizMsg){
        
        FRT_CONDTMsg frtCondTMsg = new FRT_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(frtCondTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(frtCondTMsg.frtCondCd, bizMsg.frtCondCd);
        frtCondTMsg = (FRT_CONDTMsg) S21ApiTBLAccessor.findByKey(frtCondTMsg);
        if (frtCondTMsg != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.frtCondDescTxt, frtCondTMsg.frtCondDescTxt.getValue());
        }
    }
    
    private static boolean containDsOrdTp(NWAL1840CMsg bizMsg) {
        boolean result = false;
        if (!hasValue(bizMsg.dsOrdTpCd) || bizMsg.dsOrdTpCd_CD == null) {
            return false;
        }
        for (int i = 0; i < bizMsg.dsOrdTpCd_CD.length(); i++) {
            if (!hasValue(bizMsg.dsOrdTpCd_CD.no(i))) {
                break;
            }
            if (bizMsg.dsOrdTpCd.getValue().equals(bizMsg.dsOrdTpCd_CD.no(i).getValue())) {
                return true;
            }
        }
        return result;
    }
    // 2018/02/08 S21_NA#20297(Sol#379) Add End

    //QC#22965 add Start
    public static PRC_CATGTMsg getPriceCategory(String glblCmpyCd, String prcCatgCd) {
        PRC_CATGTMsg prcCatg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(prcCatg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcCatg.prcCatgCd, prcCatgCd);
        return (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatg);
    }

    public static String getDsLineCatgCd(NWAL1840CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd);
        ssmParam.put("dsOrdTpCd", bizMsg.dsOrdTpCd);
        ssmParam.put("dsOrdLineDrctnCd", DS_ORD_LINE_DRCTN.OUTBOUND);
        ssmParam.put("slsDt", bizMsg.slsDt);

        String result = NWAL1840QueryForPricing.getInstance().getDsLineCatgCd(ssmParam);

        return result;
    }

    public static String getPrcCondTpDescTxt(String glblCmpyCd, String prcCondTpCd) {
        PRC_COND_TPTMsg prcCondTpTMsg = getPrcCondTp(glblCmpyCd, prcCondTpCd);
        if (null != prcCondTpTMsg) {
            return prcCondTpTMsg.prcCondTpDescTxt.getValue();
        }
        return null;
    }

    private static PRC_COND_TPTMsg getPrcCondTp(String glblCmpyCd, String prcCondTpCd) {
        PRC_COND_TPTMsg prcCondTpTMsgKey = new PRC_COND_TPTMsg();
        prcCondTpTMsgKey.glblCmpyCd.setValue(glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcCondTpTMsgKey.prcCondTpCd, prcCondTpCd);
        return (PRC_COND_TPTMsg) S21CodeTableAccessor.findByKey(prcCondTpTMsgKey);
    }

    public static String getWriterLineConfigRepCd(NWAL1840_CCMsgArray slsCrArrayConfig) {

        for (int n = 0; n < slsCrArrayConfig.getValidCount(); n++) {
            String roleTpCd = slsCrArrayConfig.no(n).slsRepRoleTpCd_C.getValue();
            if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(roleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(roleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(roleTpCd)) {
                return slsCrArrayConfig.no(n).slsRepTocCd_C.getValue();
            }
        }

        return null;
    }

    public static String getCcyCd(NWAL1840CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd)) {
            PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(prcCatgTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, bizMsg.prcCatgCd);
            prcCatgTMsg = (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatgTMsg);

            if (null != prcCatgTMsg) {
                return prcCatgTMsg.ccyCd.getValue();
            }
        }

        return null;
    }
    //QC#22965 add End

    /**
     * 2020/04/27 QC#56638 Add
     * Get Salse Req Defaulting
     * @param bizMsg NWAL1500CMsg
     * @return Boolean
     */
    public static boolean isSlsReqDef(NWAL1840CMsg bizMsg) {

        boolean isShipBase = true;
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd.getValue());
            tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);

            if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.baseLocTxt)) {
                if ("Ship To Location".equals(tMsg.baseLocTxt.getValue())) {
                    isShipBase = true;
                } else if ("Sold To Location".equals(tMsg.baseLocTxt.getValue())) {
                    isShipBase = false;
                } else {
                    isShipBase = true;
                }
            }
        }

        return isShipBase;
    }

    // START 2022/06/01 [QC#59973, ADD]
    /**
     * Create Frequency Pulldown List
     * @param bizMsg
     */
    private static void createFrequencyPulldown(NWAL1840CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList(SHPG_INTVL.class, bizMsg.shpgIntvlCd, bizMsg.shpgIntvlDescTxt);
        deletePulldownList(bizMsg.shpgIntvlCd, bizMsg.shpgIntvlDescTxt, SHPG_INTVL.UPON_REQUEST_BY_CUSTOMER);
    }

    /**
     * deletePulldownList
     * @param cdArray EZDCStringItemArray Code Array
     * @param txtArray EZDCStringItemArray Text Array
     * @param delCd delete Code
     */
    public static void deletePulldownList(EZDCStringItemArray cdArray, EZDCStringItemArray txtArray, String delCd) {
        int index = -1;
        for (int i = 0; i < cdArray.length(); i++) {
            if (delCd.equals(cdArray.no(i).getValue())) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            int i = index;
            for (; i < cdArray.length() - 1; i++) {
                ZYPEZDItemValueSetter.setValue(cdArray.no(i), cdArray.no(i + 1));
                ZYPEZDItemValueSetter.setValue(txtArray.no(i), txtArray.no(i + 1));
            }
            cdArray.no(i).clear();
            txtArray.no(i).clear();
        }
    }
    // END 2022/06/01 [QC#59973, ADD]

    // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
    /**
     * initialize start timestamp
     * @param bizMsg NWAL1840CMsg
     */
    public static void initStartTs(NWAL1840CMsg bizMsg) {

        bizMsg.startTs_NT.clear();

        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {
            return;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.schdAgmtStsCd) || SCHD_AGMT_STS.SAVED.equals(bizMsg.schdAgmtStsCd.getValue())) {
            return;
        }

        String screenAplID = bizMsg.getScreenAplID();
        String startTs = ZYPDateUtil.getCurrentSystemTime(DATE_PATTERN_START_TS);
        ZYPEZDItemValueSetter.setValue(bizMsg.startTs_NT, startTs);
        EZDDebugOutput.println(1, "*** Initialize startTs: event = " + screenAplID + ", ts = " + startTs + ", schdAgmtNum = " + bizMsg.schdAgmtNum.getValue(), null);
    }
    // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
}
