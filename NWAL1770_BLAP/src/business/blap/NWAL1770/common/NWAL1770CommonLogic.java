/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770.common;

import static business.blap.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.blap.NWAL1770.constant.NWAL1770Constant.COMMA;
import static business.blap.NWAL1770.constant.NWAL1770Constant.DEFAULT_NUM_INIT_BLANK_LINES;
import static business.blap.NWAL1770.constant.NWAL1770Constant.IDX_3;
import static business.blap.NWAL1770.constant.NWAL1770Constant.NUM_CONST_KEY_CORE_INIT_BLANK_LINES;
import static business.blap.NWAL1770.constant.NWAL1770Constant.PERIOD;
import static business.blap.NWAL1770.constant.NWAL1770Constant.PRNT_SUB_LINE_NUM;
import static business.blap.NWAL1770.constant.NWAL1770Constant.SHPG_CMT_TXT_LIMIT_SIZE;
import static business.blap.NWAL1770.constant.NWAL1770Constant.SPLY_QUOTE_STS_CD_CANCELLED;
import static business.blap.NWAL1770.constant.NWAL1770Constant.SPLY_QUOTE_STS_CD_SUBMITTED;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_CATG;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0181E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0796E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM8471W;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM2023E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM2024E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM2025E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM2254E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWZM2255E;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCItem;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSItem;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.blap.NWAL1770.NWAL1770Query;
import business.blap.NWAL1770.NWAL1770QueryForCustomer;
import business.blap.NWAL1770.NWAL1770SMsg;
import business.blap.NWAL1770.NWAL1770_BCMsg;
import business.blap.NWAL1770.NWAL1770_BSMsg;
import business.blap.NWAL1770.NWAL1770_DCMsgArray;
import business.db.CCYTMsg;
import business.db.COA_BRTMsg;
import business.db.COA_BRTMsgArray;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.FRT_CONDTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.PRC_CATGTMsg;
import business.db.PRC_COND_TPTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_WHTMsg;
import business.db.SPLY_QUOTE_STSTMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_EligibleCheckListPMsg;
import business.parts.NMZC610001_ShippingDefaultInfoListPMsg;
import business.parts.NMZC611001PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157001_xxPrcListPMsgArray;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC611001.NMZC611001;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetContr;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001NumberingUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPCL_HDLG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_STS;
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
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   Fujitsu         T.Yoshida       Create          N/A
 * 2016/08/03   Fujitsu         M.Hara          Update          S21_NA#7306
 * 2016/09/26   Fujitsu         H.Ikeda         Update          S21_NA#13516
 * 2016/09/29   Fujitsu         T.Murai         Update          S21_NA#13921
 * 2017/04/12   Fujitsu         Y.Kanefusa      Update          S21_NA#18235
 * 2017/06/09   Fujitsu         N.Aoyama        Update          S21_NA#18296
 * 2017/08/07   Fujitsu         Y.Kanefusa      Update          S21_NA#10347
 * 2017/08/17   Fujitsu         S.Takami        Update          S21_NA#20659
 * 2017/09/28   Fujitsu         T.Murai         Update          S21_NA#21121
 * 2017/10/24   Hitachi         J.Kim           Update          QC#21312
 * 2017/11/01   Fujitsu         H.Sugawara      Update          QC#18787(Sol#232)
 * 2018/02/13   Fujitsu         T.Aoi           Update          QC#21165
 * 2018/02/09   Fujitsu         A.Kosai         Update          S21_NA#20297(Sol#379)
 * 2018/02/26   Fujitsu         Hd.Sugawara     Update          QC#22967
 * 2018/03/08   Fujitsu         T.Aoi           Update          S21_NA#24460
 * 2018/03/19   Fujitsu         A.Kosai         Update          S21_NA#24810
 * 2018/04/04   Fujitsu         K.Ishizuka      Update          S21_NA#24988
 * 2018/04/16   Fujitsu         N.Sugiura       Update          S21_NA#22965
 * 2018/04/17   Fujitsu         A.Kosai         Update          S21_NA#25230
 * 2018/06/25   Fujitsu         H.Nagashima     Update          S21_NA#23726
 * 2018/07/06   Fujitsu         T.Noguchi       Update          S21_NA#27018
 * 2018/07/19   Fujitsu         M.Ishii         Update          S21_NA#26153
 * 2018/07/24   Fujitsu         T.Aoi           Update          S21_NA#26274
 * 2018/08/03   Fujitsu         K.Ishizuka      Update          S21_NA#25559
 * 2018/12/12   Fujitsu         K.Kato          Update          S21_NA#29315
 * 2019/01/08   Fujitsu         K.Kato          Update          S21_NA#29241
 * 2019/02/13   Fujitsu         W.Honda         Update          S21_NA#30287
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 * 2024/04/03   CITS            A.Shimada       Update          CSA-QC#63691
 * </pre>
 */
public class NWAL1770CommonLogic {

    /**
     * Set Initial Values
     * @param bizMsg NWAL1770CMsg
     * @param glblCmpyCd Global Company Code
     * @param adminPsnCd Admin Person Code
     */
    public static void setInitialValues(NWAL1770CMsg bizMsg, String glblCmpyCd, String adminPsnCd) {

        // Common
        String slsDt = ZYPDateUtil.getSalesDate();
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.adminPsnCd, adminPsnCd);

        // Header Tab
        ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteDt, slsDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteVldDaysAot, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteVldThruDt, slsDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSubTotCalcPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotChrgPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSrcTpCd, CPO_SRC_TP.SUPPLY_QUOTE_ENTRY);

        // Customer / Contact Tab
        ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);

        // Item Tab
        // 2017/08/17 S21_NA#20659 Mod Start
//        if (bizMsg.B.getValidCount() == 0) {
//            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(0);
//            ZYPEZDItemValueSetter.setValue(itemLineMsg.dplyQuoteLineNum_B, new BigDecimal(1));
//            ZYPEZDItemValueSetter.setValue(itemLineMsg.xxLineNum_B, NWAL1770CommonLogic.concatLineNum(itemLineMsg));
//            ZYPEZDItemValueSetter.setValue(itemLineMsg.prcCatgNm_B, bizMsg.prcCatgNm);
//            ZYPEZDItemValueSetter.setValue(itemLineMsg.supdLockFlg_B, ZYPConstant.FLG_OFF_N);
//            // QC#10347 2017/07/24 Add Start
//            ZYPEZDItemValueSetter.setValue(itemLineMsg.prcBaseDt_B, bizMsg.slsDt);
//            // QC#10347 2017/07/24 Add End
//            bizMsg.B.setValidCount(1);
//        }
//        createEmptyDetaiLine(bizMsg);
        // 2017/08/17 S21_NA#20659 Mod End
        //START 2024/04/03 [CSA-QC#63691,ADD]
        createBlankDetailLine(bizMsg);
        //END 2024/04/03 [CSA-QC#63691,ADD]
    }

    /**
     * Set Authority
     * @param bizMsg NWAL1770CMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setAuthority(NWAL1770CMsg bizMsg, S21UserProfileService userProfileService) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);

        int funcIdCnt = 0;
        for (String functionId : functionIds) {
            bizMsg.Z.no(funcIdCnt++).xxFuncId.setValue(functionId);
        }
        bizMsg.Z.setValidCount(funcIdCnt);
    }

    /**
     * Create Pulldown
     * @param bizMsg NWAL1770CMsg
     */
    public static void createPulldown(NWAL1770CMsg bizMsg) {

        ZYPCodeDataUtil.createPulldownList(SPLY_QUOTE_SRC_TP.class, bizMsg.splyQuoteSrcTpCd_PL, bizMsg.splyQuoteSrcTpDescTxt_PL);
        // S21_NA#7306
//        ZYPCodeDataUtil.createPulldownList(CTAC_TP.class, bizMsg.ctacTpCd_PL, bizMsg.ctacTpDescTxt_PL);
        createCtacPsnTpPulldownList(bizMsg);
        ZYPCodeDataUtil.createPulldownList(SPCL_HDLG_TP.class, bizMsg.spclHdlgTpCd_PL, bizMsg.spclHdlgTpDescTxt_PL);
        ZYPCodeDataUtil.createPulldownList(SHPG_SVC_LVL.class, bizMsg.shpgSvcLvlCd_PL, bizMsg.shpgSvcLvlDescTxt_PL);
        ZYPCodeDataUtil.createPulldownList(DS_PMT_METH.class, bizMsg.dsPmtMethCd_PL, bizMsg.dsPmtMethDescTxt_PL);
        createLineSrcPulldownList(bizMsg);

        ZYPCodeDataUtil.createPulldownList(CTAC_CUST_REF_TP.class, bizMsg.ctacCustRefTpCd_PL, bizMsg.ctacCustRefTpDescTxt_PL);  // QC#16452 add

    }

    // S21_NA#7306
    /**
     * setCtacPsnTpPulldown
     * @param bizMsg NWAL1770CMsg
     */
    @SuppressWarnings("unchecked")
    public static void createCtacPsnTpPulldownList(NWAL1770CMsg bizMsg) {
        S21SsmEZDResult result = NWAL1770Query.getInstance().getCtacPsnTpList(bizMsg);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                bizMsg.ctacTpCd_PL.no(i).setValue((String) map.get("CTAC_TP_CD"));
                bizMsg.ctacTpDescTxt_PL.no(i).setValue((String) map.get("CTAC_TP_DESC_TXT"));
                // QC#16452 add Start
                ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).ctacCustRefTpCd_L, (String) map.get("CTAC_CUST_REF_TP_CD"));
                // QC#16452 add End
                i++;
            }
        }
    }

    /**
     * Create Line Source PullDown
     * @param bizMsg NWAL1770CMsg
     */
    @SuppressWarnings("unchecked")
    private static void createLineSrcPulldownList(NWAL1770CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getOrdLineSrcList(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> result = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.ordLineSrcCd_PL.no(i), result.get("ORD_LINE_SRC_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ordLineSrcNm_PL.no(i), result.get("ORD_LINE_SRC_NM"));
            }
        }
    }

    /**
     * Create Reason Code PullDown
     * @param bizMsg NWAL1770CMsg
     */
    @SuppressWarnings("unchecked")
    public static void createRsnCdPulldown(NWAL1770CMsg bizMsg) {

        bizMsg.dsOrdTpCd_PL.clear();
        bizMsg.dsOrdTpDescTxt_PL.clear();

        S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getDsOrdTpList(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_PL.no(i), resultMap.get("DS_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpDescTxt_PL.no(i), resultMap.get("DS_ORD_TP_DESC_TXT"));
            }
        }
    }

    /**
     * Create Shipping Service Level PullDown
     * @param bizMsg NWAL1770CMsg
     */
    @SuppressWarnings("unchecked")
    public static void createShpgSvcLvlPulldown(NWAL1770CMsg bizMsg) {

        bizMsg.shpgSvcLvlCd_PL.clear();
        bizMsg.shpgSvcLvlDescTxt_PL.clear();

        S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getShpgSvcLvlList(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd_PL.no(i), resultMap.get("SHPG_SVC_LVL_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlDescTxt_PL.no(i), resultMap.get("SHPG_SVC_LVL_DESC_TXT"));
            }
        }
    }

    /**
     * Create Line Category PullDown
     * @param bizMsg NWAL1770CMsg
     * @param effDt Effective Date (YYYYMMDD)
     * @return Primary Line Catgory Code
     */
    @SuppressWarnings("unchecked")
    public static String createLineCatgPulldown(NWAL1770CMsg bizMsg, String effDt) {

        bizMsg.dsOrdLineCatgCd_PL.clear();
        bizMsg.dsOrdLineCatgDescTxt_PL.clear();

        S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getDsOrdLineCatgList(bizMsg, effDt);
        String primaryLineCatg = null;

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgCd_PL.no(i), resultMap.get("DS_ORD_LINE_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgDescTxt_PL.no(i), resultMap.get("DS_ORD_LINE_CATG_DESC_TXT"));

                if (!ZYPCommonFunc.hasValue(primaryLineCatg)) {
                    if (ZYPConstant.FLG_ON_Y.equals(resultMap.get("PRIM_LINE_CATG_FLG"))) {
                        primaryLineCatg = resultMap.get("DS_ORD_LINE_CATG_CD");
                    }
                }
            }
        }

        if (ZYPCommonFunc.hasValue(primaryLineCatg)) {
            return primaryLineCatg;
        }

        return bizMsg.dsOrdLineCatgCd_PL.no(0).getValue();
    }

    /**
     * Create Package UOM PullDown
     * @param bizMsg NWAL1770CMsg
     * @param lineMsg NWAL1770_BCMsg
     */
    @SuppressWarnings("unchecked")
    public static void createPkgUomPullDown(NWAL1770CMsg bizMsg, NWAL1770_BCMsg lineMsg) {

        lineMsg.pkgUomCd_PL.clear();
        lineMsg.pkgUomDescTxt_PL.clear();

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_B.getValue());
        if (mdseTMsg == null) {
            return;
        }

        S21SsmEZDResult ssmRslt = NWAL1770Query.getInstance().getPkgUomInfoList(bizMsg, mdseTMsg.mdseCd.getValue());

        int index = 0;
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, String>> pkgUomList = (List<Map<String, String>>) ssmRslt.getResultObject();
            for (Map<String, String> pkgUom : pkgUomList) {
                String pkgUomCd = pkgUom.get("PKG_UOM_CD");
                String pkgUomDescTxt = pkgUom.get("PKG_UOM_DESC_TXT");

                lineMsg.pkgUomCd_PL.no(index).setValue(pkgUomCd);
                lineMsg.pkgUomDescTxt_PL.no(index).setValue(pkgUomDescTxt);
                index++;

                if (index > lineMsg.pkgUomCd_PL.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Check Input Category
     * @param bizMsg NWAL1770CMsg
     * @return check OK : true
     */
    @SuppressWarnings("unchecked")
    public static boolean checkExistCatg(NWAL1770CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getDsOrdCatgList(bizMsg);

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

        ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteCatgCd, dsOrdCatgCdList.get(0).get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgDescTxt, dsOrdCatgCdList.get(0).get("DS_ORD_CATG_DESC_TXT"));

        return true;
    }

    /**
     * Derive Default Data
     * @param bizMsg NWAL1770CMsg
     */
    public static void deriveDefaultData(NWAL1770CMsg bizMsg) {

        // Add Start 2017/11/01 QC#18787(Sol#232)
        deriveDefalutDaysValid(bizMsg);
        // Add End 2017/11/01 QC#18787(Sol#232)

        deriveDefaultCustAddl(bizMsg);

        if (!NWAL1770CommonLogicForSalesCredit.deriveDefaultSlsRep(bizMsg)) {
            return;
        }

        if (!deriveDefaultCarrSvcLvl(bizMsg)) {
            return;
        }

        deriveDefaultPrcList(bizMsg);
    }

    /**
     * Derive Default Customer And Additional Data
     * @param bizMsg NWAL1770CMsg
     */
    @SuppressWarnings("unchecked")
    private static void deriveDefaultCustAddl(NWAL1770CMsg bizMsg) {

        bizMsg.frtCondCd.clear();
        bizMsg.frtCondDescTxt.clear();
        bizMsg.carrSvcLvlDescTxt.clear();
        bizMsg.shpgSvcLvlCd.clear();
        bizMsg.prcCatgNm.clear();
        bizMsg.flPrcListCd.clear();
        bizMsg.dropShipAvalFlg.clear();
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            bizMsg.B.no(i).prcCatgNm_B.clear();
        }

        S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getCustAddlInfo(bizMsg);
        String billToCustCd = null;

        if (ssmResult.isCodeNormal()) {
            Map<String, String> resultMap = (Map<String, String>) ssmResult.getResultObject();
            billToCustCd = resultMap.get("DEF_BILL_TO_CUST_CD");
            ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd, resultMap.get("LINE_BIZ_TP_CD"));
            // 2018/02/15 S21_NA#20297(Sol#379) Del Start
//            ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, resultMap.get("FRT_COND_CD"));
//            ZYPEZDItemValueSetter.setValue(bizMsg.frtCondDescTxt, resultMap.get("FRT_COND_DESC_TXT"));
            // 2018/02/15 S21_NA#20297(Sol#379) Del End
            ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlDescTxt, resultMap.get("CARR_SVC_LVL_DESC_TXT"));
            // 2018/02/15 S21_NA#20297(Sol#379) Del Start
//            ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd, resultMap.get("DEF_SHPG_SVC_LVL_CD"));
            // 2018/02/15 S21_NA#20297(Sol#379) Del End
            ZYPEZDItemValueSetter.setValue(bizMsg.flPrcListCd, resultMap.get("PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dropShipAvalFlg, resultMap.get("DROP_SHIP_AVAL_FLG"));

            String prcCatgNm = resultMap.get("PRC_CATG_NM");
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm, prcCatgNm);
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).prcCatgNm_B, prcCatgNm);
            }
            // 2018/02/15 S21_NA#20297(Sol#379) Del Start
//            // Add Start 2016/09/26 S21_NA#13516
//            if (ZYPCommonFunc.hasValue(resultMap.get("FRT_COND_CD"))) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, resultMap.get("FRT_COND_CD"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.frtCondDescTxt, resultMap.get("FRT_COND_DESC_TXT"));
//                setShpgSvcLvlPullDown(bizMsg);
//            }
//            // Add Start 2016/09/26 S21_NA#13516
            // 2018/02/15 S21_NA#20297(Sol#379) Del End
            controlDropShipField(bizMsg);
        }

        if (ZYPCommonFunc.hasValue(billToCustCd)) {
            deriveDefaultPmtTerm(bizMsg, billToCustCd);
        }

        // 2018/02/09 S21_NA#20297(Sol#379) Add Start
        if (!deriveDefaultShippingInfo(bizMsg)) {
            return;
        }

        if (!deriveDefaultShippingComment(bizMsg)) {
            return;
        }
        // 2018/02/09 S21_NA#20297(Sol#379) Add End
    }

    /**
     * Control Drop Ship Field
     * @param bizMsg NWAL1770CMsg
     */
    @SuppressWarnings("unchecked")
    public static void controlDropShipField(NWAL1770CMsg bizMsg) {

        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.dropShipAvalFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.dropShipFlg.getValue())) {
            S21SsmEZDResult ssmResult = NWAL1770QueryForCustomer.getInstance().getShipToCustInfoList(bizMsg);

            if (ssmResult.isCodeNotFound()) {
                bizMsg.shipToCustCd.clear();
                return;
            }

            List<Map<String, String>> shipToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
            Map<String, String> shipToInfo = shipToCustInfoList.get(0);

            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, shipToInfo.get("SELL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, shipToInfo.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, shipToInfo.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm, shipToInfo.get("LOC_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm, shipToInfo.get("ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SH, shipToInfo.get("SHIP_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr, shipToInfo.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr, shipToInfo.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr, shipToInfo.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr, shipToInfo.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstRefCmntTxt, shipToInfo.get("FIRST_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdRefCmntTxt, shipToInfo.get("SCD_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr, shipToInfo.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd, shipToInfo.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd, shipToInfo.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm, shipToInfo.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd, shipToInfo.get("CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm, shipToInfo.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
            // 2017/11/27 S21_NA#21155 add star
            bizMsg.shipToLocNm_DS.clear();
            // 2017/11/27 S21_NA#21155 add end
        }
    }

    /**
     * Derive Default Payment Term Data
     * @param bizMsg NWAL1770CMsg
     * @param billToCustCd Bill To Customer Code
     * @return Derive Success : true
     */
    public static boolean deriveDefaultPmtTerm(NWAL1770CMsg bizMsg, String billToCustCd) {

        bizMsg.pmtTermCashDiscDescTxt.clear();
        bizMsg.pmtCcFlg.clear();
        String pmtTermCashDiscCd = getPmtTermCashDiscCd(bizMsg, billToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.ovrdPmtTermFlg, NWXC150001DsCheck.getOvrdPmtTermFlg(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustAcctCd.getValue(), bizMsg.billToCustCd.getValue()));         // QC#17474 2017/02/21 Add

        if (ZYPCommonFunc.hasValue(pmtTermCashDiscCd)) {
            PMT_TERM_CASH_DISCTMsg tMsg = new PMT_TERM_CASH_DISCTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCashDiscCd, pmtTermCashDiscCd);
            tMsg = (PMT_TERM_CASH_DISCTMsg) S21FastTBLAccessor.findByKey(tMsg);

            if (tMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.pmtTermCashDiscCd, tMsg.pmtTermCashDiscCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.pmtTermCashDiscDescTxt, tMsg.pmtTermCashDiscDescTxt);
                ZYPEZDItemValueSetter.setValue(bizMsg.pmtCcFlg, tMsg.pmtCcFlg);
                return true;
            }
        }

        bizMsg.setMessageInfo(NWAM0796E, new String[] {bizMsg.billToCustAcctCd.getValue(), billToCustCd });
        return false;
    }

    /**
     * Get CPO Order Type Code
     * @param bizMsg NWAL1770CMsg
     * @return CPO Order Type Code
     */
    public static String getCpoOrdTpCd(NWAL1770CMsg bizMsg) {

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
     * Get Payment Term Cash Discount Code
     * @param bizMsg NWAL1770CMsg
     * @param billToCustCd Bill To Customer Code
     * @return Payment Term Cash Discount Code
     */
    private static String getPmtTermCashDiscCd(NWAL1770CMsg bizMsg, String billToCustCd) {

        if (ZYPCommonFunc.hasValue(billToCustCd)) {
            S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getPmtTermCashDiscCdForBillToCust(bizMsg, billToCustCd);
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
     * Derive Default Carrier Service Level
     * @param bizMsg NWAL1770CMsg
     * @return No API Error : true
     */
    public static boolean deriveDefaultCarrSvcLvl(NWAL1770CMsg bizMsg) {

        // QC#13688 2017/02/24 Mod Start
        //if (ZYPCommonFunc.hasValue(bizMsg.carrSvcLvlDescTxt) || !ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd) || !ZYPCommonFunc.hasValue(bizMsg.shpgSvcLvlCd)) {
        //    return true;
        //}
        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd) || !ZYPCommonFunc.hasValue(bizMsg.shpgSvcLvlCd)) {
            return true;
        }
        // QC#23726 2018/06/25 del Start
//        if (!FRT_COND.COLLECT.equals(bizMsg.frtCondCd.getValue())) { 
//            return true;
//        }
        // QC#23726 2018/06/25 del End
        // QC#13688 2017/02/24 Mod End

        NMZC611001PMsg defCarrApiPMsg = callDefaultCarrierApi(bizMsg);

        if (S21ApiUtil.isXxMsgId(defCarrApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(defCarrApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
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
            S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getCarrSvcLvlTxt(bizMsg, vndCd);

            if (ssmResult.isCodeNormal()) {
                String carrSvcLvlDescTxt = (String) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlDescTxt, carrSvcLvlDescTxt);
            }
        }

        return true;
    }

    /**
     * Call NMZC6110 Default Carrier API
     * @param bizMsg NWAL1770CMsg
     * @return NMZC611001PMsg
     */
    private static NMZC611001PMsg callDefaultCarrierApi(NWAL1770CMsg bizMsg) {

        NMZC611001PMsg pMsg = new NMZC611001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, bizMsg.shipToCustAcctCd);
        // 2018/12/12 QC#29315 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.locNum, bizMsg.shipToLocNum);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsBizAreaCd, bizMsg.dsBizAreaCd);
        // 2018/12/12 QC#29315 Add End
        new NMZC611001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        return pMsg;
    }

    /**
     * Derive Default Price List
     * @param bizMsg NWAL1770CMsg
     * @return has API Error : false
     */
    public static boolean deriveDefaultPrcList(NWAL1770CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
            return true;
        }

        // call NWZC1570 Pricing API
        NWZC157001PMsg prcApiPMsg = callPricingApiOfGetPriceListMode(bizMsg);
        if (prcApiPMsg == null) {
            // 2018/08/03 S21_NA#25559 Add Start
            bizMsg.prcCatgCd.clear();
            bizMsg.prcCatgNm.clear();
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                bizMsg.B.no(i).prcCatgCd_B.clear();
                bizMsg.B.no(i).prcCatgNm_B.clear();
            }
            // 2018/08/03 S21_NA#25559 Add End
            return false;
        }

        // set Default Price List
        NWZC157001_xxPrcListPMsgArray prcListArray = prcApiPMsg.xxPrcList;
        if (prcListArray.getValidCount() == 1) {
            String defPrcCatgCd = prcListArray.no(0).prcCatgCd.getValue();
            String defPrcCatgNm = getPrcCatgNm(bizMsg, defPrcCatgCd);

            if (ZYPCommonFunc.hasValue(defPrcCatgNm)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd, defPrcCatgCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm, defPrcCatgNm);
                for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).prcCatgCd_B, defPrcCatgCd);
                    ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).prcCatgNm_B, defPrcCatgNm);
                }
            }
        }

        return true;
    }

    /**
     * Call NWZC1570 Pricing API (01:Get Price List Mode)
     * @param bizMsg NWAL1770CMsg
     * @return NWZC157001PMsg
     */
    private static NWZC157001PMsg callPricingApiOfGetPriceListMode(NWAL1770CMsg bizMsg) {

        NWZC157001PMsg pMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.splyQuoteCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.prcContrNum, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.coaBrCd, getCoaBrCd(bizMsg));
        ZYPEZDItemValueSetter.setValue(pMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);

        // Call NWZC1570 Pricing API
        new NWZC157001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
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
     * @param bizMsg NWAL1770CMsg
     * @return COA Branch Code
     */
    public static String getCoaBrCd(NWAL1770CMsg bizMsg) {

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
     * @param bizMsg NWAL1770CMsg
     * @param prcCatgCd Price Category Code
     * @return Price Category Desc Text
     */
    public static String getPrcCatgNm(NWAL1770CMsg bizMsg, String prcCatgCd) {

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
     * Numbering SPLY_QUOTE_DTL_LINE_NUM, SPLY_QUOTE_DTL_LINE_SUB_NUM
     * @param bizMsg NWAL1770CMsg
     */
    public static void numberingQuoteLineNumber(NWAL1770CMsg bizMsg) {

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);
            // 2018/07/24 S21_NA#26274 Mod Start
            //itemLineMsg.splyQuoteDtlLineNum_B.clear();
            //itemLineMsg.splyQuoteDtlLineSubNum_B.clear();
            if (!ZYPCommonFunc.hasValue(itemLineMsg.splyQuoteLineStsCd_B)) {
                itemLineMsg.splyQuoteDtlLineNum_B.clear();
                itemLineMsg.splyQuoteDtlLineSubNum_B.clear();
            }
            // 2018/07/24 S21_NA#26274 Mod End
        }

        // 2019/02/13 S21_NA#30287 Mod Start
//        String nextLineNum = PRNT_SUB_LINE_NUM;
        String nextLineNum = NWAL1770Query.getInstance().getMaxLineNum(bizMsg.glblCmpyCd.getValue(), bizMsg.splyQuoteNum.getValue());
        if (nextLineNum == null) {
            nextLineNum = PRNT_SUB_LINE_NUM;
        }
        // 2019/02/13 S21_NA#30287 Mod End
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(itemLineMsg.splyQuoteDtlLineNum_B)) {
                nextLineNum = NWXC150001NumberingUtil.getNextCpoDtlLineNum(nextLineNum);
                itemLineMsg.splyQuoteDtlLineNum_B.setValue(nextLineNum);
                if (isSetMdse(bizMsg, itemLineMsg.mdseCd_B.getValue())) {
                    itemLineMsg.splyQuoteDtlLineSubNum_B.setValue(PRNT_SUB_LINE_NUM);

                    int subIndex = 1;
                    if (i + subIndex < bizMsg.B.getValidCount()) {
                        int lineSubNum = 1;
                        NWAL1770_BCMsg childLineMsg = bizMsg.B.no(i + subIndex);
                        while (isSameLineByDplyQuoteLineNum(itemLineMsg, childLineMsg)) {

                            childLineMsg.splyQuoteDtlLineNum_B.setValue(nextLineNum);
                            childLineMsg.splyQuoteDtlLineSubNum_B.setValue(ZYPCommonFunc.leftPad(String.valueOf(lineSubNum++), IDX_3, "0"));

                            subIndex++;
                            if (i + subIndex >= bizMsg.B.getValidCount()) {
                                break;
                            }
                            childLineMsg = bizMsg.B.no(i + subIndex);
                        }
                        i += (subIndex - 1);
                    }
                } else {
                    itemLineMsg.splyQuoteDtlLineSubNum_B.setValue("001");
                }
            // 2018/07/24 S21_NA#26274 Add Start
            // 2019/02/13 S21_NA#30287 Mod Start
//            } else {
//                nextLineNum = itemLineMsg.splyQuoteDtlLineNum_B.getValue();
//            }
            }
            // 2019/02/13 S21_NA#30287 Mod End
            // 2018/07/24 S21_NA#26274 Add End
        }
    }

    /**
     * Check Set Item
     * @param bizMsg NWAL1770CMsg
     * @param mdseCd MDSE Code
     * @return Set Item : true
     */
    private static boolean isSetMdse(NWAL1770CMsg bizMsg, String mdseCd) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), mdseCd);
        if (mdseTMsg == null) {
            return false;
        }

        return S21StringUtil.isEquals(mdseTMsg.mdseTpCd.getValue(), MDSE_TP.SET);
    }

    /**
     * Check Same Quote Line Number
     * @param lineMsg1 NWAL1770_BCMsg
     * @param lineMsg2 NWAL1770_BCMsg
     * @return Same Quote Line Number : true
     */
    private static boolean isSameLineByDplyQuoteLineNum(NWAL1770_BCMsg lineMsg1, NWAL1770_BCMsg lineMsg2) {

        if (compareBigDecimal(lineMsg1.dplyQuoteLineNum_B.getValue(), lineMsg2.dplyQuoteLineNum_B.getValue()) != 0) {
            return false;
        }
        return true;
    }

    /**
     * Compare To BigDecimal
     * @param source Source Value
     * @param target Target Value
     * @return result (0, > 0 , < 0)
     */
    private static int compareBigDecimal(BigDecimal source, BigDecimal target) {

        if (source == null) {
            if (target == null) {
                return 0;
            } else {
                return -1;
            }
        } else {
            if (target == null) {
                return 1;
            } else {
                return source.compareTo(target);
            }
        }
    }

    /**
     * Concat Position Number And Line Number
     * @param lineMsg EZDMsg
     * @return concatenated sring (Position + "." + Line)
     */
    public static String concatLineNum(EZDMsg lineMsg) {

        BigDecimal dplyQuoteLineNum = null;
        BigDecimal dplyQuoteLineSubNum = null;

        if (lineMsg instanceof NWAL1770_BCMsg) {
            NWAL1770_BCMsg bizLineMsg = (NWAL1770_BCMsg) lineMsg;
            dplyQuoteLineNum = bizLineMsg.dplyQuoteLineNum_B.getValue();
            dplyQuoteLineSubNum = bizLineMsg.dplyQuoteLineSubNum_B.getValue();
        } else if (lineMsg instanceof NWAL1770_BSMsg) {
            NWAL1770_BSMsg glblLineMsg = (NWAL1770_BSMsg) lineMsg;
            dplyQuoteLineNum = glblLineMsg.dplyQuoteLineNum_B.getValue();
            dplyQuoteLineSubNum = glblLineMsg.dplyQuoteLineSubNum_B.getValue();
        }

        StringBuilder xxLineNum = new StringBuilder();
        xxLineNum.append("1");
        xxLineNum.append(PERIOD);
        xxLineNum.append(dplyQuoteLineNum);
        if (dplyQuoteLineSubNum != null) {
            xxLineNum.append(PERIOD);
            xxLineNum.append(dplyQuoteLineSubNum);
        }

        return xxLineNum.toString();
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
     * Get Varchar Const Data List
     * @param bizMsg NWAL1770CMsg
     * @param varCharConstKey Varchar Const Key
     * @return Varchar Const Data List
     */
    public static List<String> getVarCharConstDataList(NWAL1770CMsg bizMsg, String varCharConstKey) {

        List<String> varcharConstValueList = new ArrayList<String>();

        String varcharConstValue = ZYPCodeDataUtil.getVarCharConstValue(varCharConstKey, bizMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(varcharConstValue)) {
            return varcharConstValueList;
        }

        String[] varcharConstValueArray = varcharConstValue.split(COMMA);
        for (String val : varcharConstValueArray) {
            varcharConstValueList.add(val);
        }

        return varcharConstValueList;
    }

    /**
     * Set Initial Values For Copy
     * @param bizMsg NWAL1770CMsg
     */
    public static void setInitialValuesForCopy(NWAL1770CMsg bizMsg) {

        // Header
        bizMsg.splyQuoteNum.clear();
        bizMsg.splyQuoteNum_BK.clear(); // Add 2017/09/28 S21_NA#21121
        ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteDt, bizMsg.slsDt);
        // 2018/07/05 S21_NA#27018 Del Start
        // ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteVldDaysAot, BigDecimal.ZERO);
        // 2018/07/05 S21_NA#27018 Del End
        ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteVldThruDt, bizMsg.slsDt);
        bizMsg.splyQuoteStsCd.clear();
        bizMsg.splyQuoteStsDescTxt.clear();
        bizMsg.cpoOrdNum.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSubTotCalcPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotChrgPrcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt, BigDecimal.ZERO);

        // Delivery / Payment Tab
        // 2018/03/08 QC#24460 Mod Start
        //if (ZYPCommonFunc.hasValue(bizMsg.rddDt)) {
        //    ZYPEZDItemValueSetter.setValue(bizMsg.rddDt, bizMsg.slsDt);
        //}
        bizMsg.rddDt.clear();
        // 2018/03/08 QC#24460 Mod End
        bizMsg.dsCrCardPk.clear();

        // Item Tab
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);
            itemLineMsg.splyQuoteLineStsCd_B.clear();
            itemLineMsg.splyQuoteStsDescTxt_B.clear();
            itemLineMsg.rddDt_B.clear(); // 2018/03/08 QC#24460 Add
        }
        
        NWAL1770CommonLogic.reNumberingItemLine(bizMsg); // 2018/04/04 S21_NA#24988 Add

        // Additional Data Tab
        bizMsg.xxPsnNm_SV.clear();
        bizMsg.xxTsDsp19Txt_SV.clear();
        bizMsg.xxPsnNm_SB.clear();
        bizMsg.xxTsDsp19Txt_SB.clear();
        bizMsg.splyQuoteRptOtptLogPk_PL.clear();
        bizMsg.xxDtlNm_PL.clear();
        bizMsg.dsAcctClsDescTxt.clear();
        bizMsg.xxScrItem54Txt_GL.clear();
        ZYPTableUtil.clear(bizMsg.C);

        // Contact
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            bizMsg.A.no(i).splyQuoteCtacPsnPk_A.clear();
        }

        // Sales Credit
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            bizMsg.D.no(i).splyQuoteSlsCrPk_D.clear();
        }
    }

    /**
     * Renumbering Item Line
     * @param bizMsg NWAL1770CMsg
     */
    public static void reNumberingItemLine(NWAL1770CMsg bizMsg) {

        int lineNum = 0;
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);

            if (ZYPCommonFunc.hasValue(itemLineMsg.dplyQuoteLineSubNum_B)) {
                itemLineMsg.dplyQuoteLineNum_B.setValue(lineNum);
                itemLineMsg.xxLineNum_B.setValue(NWAL1770CommonLogic.concatLineNum(itemLineMsg));
            } else {
                itemLineMsg.dplyQuoteLineNum_B.setValue(++lineNum);
                itemLineMsg.xxLineNum_B.setValue(NWAL1770CommonLogic.concatLineNum(itemLineMsg));
            }
        }
    }

    /**
     * Check Set MDSE
     * @param glblCmpyCd Global Company Code
     * @param mdseCd MDSE Code
     * @return Set MDSE : true
     */
    public static boolean isSetMdse(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdseTMsg == null) {
            return false;
        }

        return MDSE_TP.SET.equals(mdseTMsg.mdseTpCd.getValue());
    }

    /**
     * Check Tangible MDSE
     * @param glblCmpyCd Global Company Code
     * @param mdseCd MDSE Code
     * @return Set MDSE : true
     */
    public static boolean isTangibleMdse(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdseTMsg == null) {
            return false;
        }

        return ZYPConstant.FLG_ON_Y.equals(mdseTMsg.invtyCtrlFlg.getValue());
    }

    /**
     * Get Retail Warehouse Name
     * @param bizMsg NWAL1770CMsg
     * @param rtlWhCd Retail Warehouse Code
     * @return Retail Warehouse Name
     */
    public static String getRtlWhNm(NWAL1770CMsg bizMsg, String rtlWhCd) {

        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, rtlWhCd);
        rtlWhTMsg = (RTL_WHTMsg) S21FastTBLAccessor.findByKey(rtlWhTMsg);

        if (rtlWhTMsg == null) {
            return null;
        }

        return rtlWhTMsg.rtlWhNm.getValue();
    }

    /**
     * Get Retail Sub Warehouse Name
     * @param bizMsg NWAL1770CMsg
     * @param rtlWhCd Retail Warehouse Code
     * @param rtlSwhCd Retail Sub Warehouse Code
     * @return Retail Sub Warehouse Name
     */
    public static String getRtlSubWhNm(NWAL1770CMsg bizMsg, String rtlWhCd, String rtlSwhCd) {

        RTL_SWHTMsg rtlSubWhTMsg = new RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlSubWhTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlSubWhTMsg.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(rtlSubWhTMsg.rtlSwhCd, rtlSwhCd);
        rtlSubWhTMsg = (RTL_SWHTMsg) S21FastTBLAccessor.findByKey(rtlSubWhTMsg);

        if (rtlSubWhTMsg == null) {
            return null;
        }

        return rtlSubWhTMsg.rtlSwhNm.getValue();
    }

    // Add Start 2016/09/26 S21_NA#13516
    /**
     * <pre>
     * set Shipping Service Level Pull Down 
     * @param bizMsg
     */
    public static void setShpgSvcLvlPullDown(NWAL1770CMsg bizMsg) {
        S21SsmEZDResult resltShpgSvcLvlRec = NWAL1770Query.getInstance().getShpgSvcLvlDataList(bizMsg.glblCmpyCd.getValue(), bizMsg.lineBizTpCd.getValue(), bizMsg.frtCondCd.getValue());
        if (resltShpgSvcLvlRec.isCodeNormal()) {
            List<Map<String, Object>> shpgSvcLvlRecList = (List<Map<String, Object>>) resltShpgSvcLvlRec.getResultObject();

            bizMsg.shpgSvcLvlCd_PL.clear();
            bizMsg.shpgSvcLvlDescTxt_PL.clear();
            int validCnt = 0;
            for (Map<String, Object> shpgSvcLvlRec : shpgSvcLvlRecList) {
                bizMsg.shpgSvcLvlCd_PL.no(validCnt).setValue((String) shpgSvcLvlRec.get("SHPG_SVC_LVL_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlDescTxt_PL.no(validCnt), (String) shpgSvcLvlRec.get("SHPG_SVC_LVL_DESC_TXT"));
                validCnt++;
            }
        }
    }
    // Add End 2016/09/09 S21_NA#13516
    // Add Start 2016/09/29 S21_NA#13921
    /**
     * select merchandise master data with registration status code.
     * @param glblCmpyCd global company code
     * @param mdseCd merchandise code
     * @return merchandise master data
     */
    public static MDSETMsg getMdseWithRgtnStsCd(String glblCmpyCd, String mdseCd) {

        final MDSETMsg mdseTMsg = getMdse(glblCmpyCd, mdseCd);

        if (mdseTMsg == null) {
            return null;
        }

        final String rgtnStsCd = mdseTMsg.rgtnStsCd.getValue();
        if (asList(RGTN_STS.READY_FOR_ORDER_TAKING, RGTN_STS.TERMINATED).contains(rgtnStsCd)) {
            return mdseTMsg;
        } else {
            return null;
        }
    }

    /**
     * select merchandise data from merchandise master using NWXMdseTMsgThreadLocalCache#get()
     * @param glblCmpyCd global company code
     * @param mdseCd merchandise code
     * @return merchandise master data
     */
    public static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdseTMsg == null) {
            MDSETMsg queryMdseTMsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(queryMdseTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(queryMdseTMsg.mdseCd, mdseCd);

            mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(queryMdseTMsg);

            if (mdseTMsg == null) {

                ORD_TAKE_MDSETMsg ordTakeMdseMsg = new ORD_TAKE_MDSETMsg();
                ordTakeMdseMsg.setSQLID("002");
                ordTakeMdseMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                ordTakeMdseMsg.setConditionValue("ordTakeMdseCd01", mdseCd);

                ORD_TAKE_MDSETMsgArray ordTakeMdseMsgArray = (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(ordTakeMdseMsg);
                if (ordTakeMdseMsgArray != null && ordTakeMdseMsgArray.getValidCount() > 0) {
                    ZYPEZDItemValueSetter.setValue(queryMdseTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(queryMdseTMsg.mdseCd, ordTakeMdseMsgArray.no(0).mdseCd);

                    mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(queryMdseTMsg);
                }
            }
        }
        return mdseTMsg;
    }
    

    /**
     * checkBillShipSoldRelation
     * @param bizMsg
     * @param glblCmpyCd glblCmpyCd
     * @param billToCustCd
     * @param shipToCustCd
     * @param sellToCustCd
     * @return boolean if error then return true.
     */
    public static void checkBillShipSoldRelation(NWAL1770CMsg bizMsg) {

        // Add Start 2018/02/26 QC#22967
        // Check Sold To(Customer Code) <- Ship To(Account Number)
        // relation.
        callCustInfoGetApiForCheckRelation( //
                bizMsg, bizMsg.soldToCustLocCd.getValue(), bizMsg.shipToCustAcctCd.getValue(), //
                bizMsg.shipToCustCd, bizMsg.soldToCustLocCd, NWZM2254E);

        // Check Bill To(Customer Code) <- Sold To(Account Number)
        // relation.
        callCustInfoGetApiForCheckRelation( //
                bizMsg, bizMsg.billToCustCd.getValue(), bizMsg.sellToCustCd.getValue(), //
                bizMsg.soldToCustLocCd, bizMsg.billToCustCd, NWZM2255E);
        // Add End 2018/02/26 QC#22967

        // Del Start 2018/02/26 QC#22967
        //NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApi(//
        //        bizMsg.glblCmpyCd.getValue() //
        //        , bizMsg.billToCustCd.getValue() //
        //        // 2017/06/09 S21_NA#18296 Mod Start
        //        // , bizMsg.shipToCustCd.getValue() //
        //        , bizMsg.sellToCustCd.getValue() //
        //        // 2017/06/09 S21_NA#18296 Mod End
        //      //, bizMsg.sellToCustCd.getValue() // QC#18235 2017/04/12 Mod
        //        , bizMsg.shipToCustAcctCd.getValue() //
        //        , ONBATCH_TYPE.ONLINE);
        //
        //if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
        //    List<String> ml = S21ApiUtil.getXxMsgIdList(custInfoGetApiMsg);
        //    for (String msgId : ml) {
        //        bizMsg.setMessageInfo(msgId);
        //    }
        //}
        //for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
        //    NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiMsg.EligibleCheckList.no(i);
        //
        //    if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue())) {
        //        bizMsg.billToCustCd.setErrorInfo(1, NWZC167001Constant.NWZM1455E);
        //    }
        //    // 2017/06/09 S21_NA#18296 Mod Start
        //    // if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
        //        if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {
        //        // 2017/06/09 S21_NA#18296 Mod End
        //        bizMsg.shipToCustCd.setErrorInfo(1, NWZC167001Constant.NWZM1455E);
        //    }
        //}
        // Del End 2018/02/26 QC#22967
    }
    

    // Add End 2016/09/29 S21_NA#13921

    // Add Start 2018/02/26 QC#22967
    /**
     * @param bizMsg NWAL1770CMsg
     * @param billToCustCd String
     * @param acctNum String
     * @param err1 EZDCStringItem
     * @param err2 EZDCStringItem
     * @param errMsgId String
     */
    private static void callCustInfoGetApiForCheckRelation(NWAL1770CMsg bizMsg, String billToCustCd, String acctNum, EZDCStringItem err1, EZDCStringItem err2, String errMsgId) {
        NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApiBillTo( //
                bizMsg.glblCmpyCd.getValue(), billToCustCd, acctNum, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
            List<String> ml = S21ApiUtil.getXxMsgIdList(custInfoGetApiMsg);
            for (String msgId : ml) {
                bizMsg.setMessageInfo(msgId);
            }
        }

        for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
            NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiMsg.EligibleCheckList.no(i);
            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue())) {
                err1.setErrorInfo(1, errMsgId);
                err2.setErrorInfo(1, errMsgId);
            }
        }
    }
    // Add End 2018/02/26 QC#22967

    // 2017/08/17 S21_NA#20659 Add Start
    /**
     * <pre>
     * Create Empty Detail Line when there is no Detail Line.
     * @param bizMsg Business Message
     * </pre>
     */
    public static void createEmptyDetaiLine(NWAL1770CMsg bizMsg) {

        if (bizMsg.B.getValidCount() == 0) {
            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(0);
            ZYPEZDItemValueSetter.setValue(itemLineMsg.dplyQuoteLineNum_B, new BigDecimal(1));
            ZYPEZDItemValueSetter.setValue(itemLineMsg.xxLineNum_B, NWAL1770CommonLogic.concatLineNum(itemLineMsg));
            ZYPEZDItemValueSetter.setValue(itemLineMsg.prcCatgNm_B, bizMsg.prcCatgNm);
            ZYPEZDItemValueSetter.setValue(itemLineMsg.supdLockFlg_B, ZYPConstant.FLG_OFF_N);
            // QC#10347 2017/07/24 Add Start
            ZYPEZDItemValueSetter.setValue(itemLineMsg.prcBaseDt_B, bizMsg.slsDt);
            // QC#10347 2017/07/24 Add End
            // 2018/02/13 QC#21165 Add Start
            ZYPEZDItemValueSetter.setValue(itemLineMsg.rddDt_B, bizMsg.rddDt);
            // 2018/02/13 QC#21165 Add End
            bizMsg.B.setValidCount(1);
        }
    }
    // 2017/08/17 S21_NA#20659 Add End

    //START 2024/04/03 [CSA-QC#63691,ADD]
    /**
     * <pre>
     *  Create Default Blank Line when there is no Detail Line.
     * @param bizMsg Business Message
     * </pre>
     */
    public static void createBlankDetailLine(NWAL1770CMsg bizMsg) {
        boolean isSubmitted = bizMsg.splyQuoteStsCd.getValue().equals(SPLY_QUOTE_STS_CD_SUBMITTED);
        boolean isCancelled = bizMsg.splyQuoteStsCd.getValue().equals(SPLY_QUOTE_STS_CD_CANCELLED);
        if (isSubmitted || isCancelled) {
            return;
        }
        BigDecimal initblankLines = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_KEY_CORE_INIT_BLANK_LINES, bizMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(initblankLines) || initblankLines.intValue() < 0) {
            initblankLines = DEFAULT_NUM_INIT_BLANK_LINES;
        }
        if (initblankLines.intValue() >=  bizMsg.B.getValidCount()) {
            for (int i = 0; i < initblankLines.intValue(); i++) {
                // Set New ItemLine
                NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.dplyQuoteLineNum_B, new BigDecimal(i + 1));
                ZYPEZDItemValueSetter.setValue(itemLineMsg.xxLineNum_B, NWAL1770CommonLogic.concatLineNum(itemLineMsg));
                if (!ZYPCommonFunc.hasValue(itemLineMsg.prcCatgNm_B)) {
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.prcCatgNm_B, bizMsg.prcCatgNm);
                }
                if (!ZYPCommonFunc.hasValue(itemLineMsg.supdLockFlg_B)) {
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.supdLockFlg_B, ZYPConstant.FLG_OFF_N);
                }
                if (!ZYPCommonFunc.hasValue(itemLineMsg.prcBaseDt_B)) {
                   ZYPEZDItemValueSetter.setValue(itemLineMsg.prcBaseDt_B, bizMsg.slsDt);
                }
                if (!ZYPCommonFunc.hasValue(itemLineMsg.rddDt_B)) {
                   ZYPEZDItemValueSetter.setValue(itemLineMsg.rddDt_B, bizMsg.rddDt);
                }
                bizMsg.B.setValidCount(i + 1);

                // Renumbering Line Number
                NWAL1770CommonLogic.reNumberingItemLine(bizMsg);
            }
        }
    }
  //END 2024/04/03 [CSA-QC#63691,ADD]

    // START 2017/10/24 J.Kim [QC#21312,ADD]
    /**
     * check OrderQuantity
     * @param mdseTMsg MDSETMsg
     * @param itemLineMsg NWAL1770_BCMsg
     * @return boolean if error then return true.
     */
    // 2019/01/08 QC#29241 Mod Start
    //public static void checkOrdOty(MDSETMsg mdseTMsg, NWAL1770_BCMsg itemLineMsg) {
    public static void checkOrdOty(MDSETMsg mdseTMsg, NWAL1770CMsg bizMsg, NWAL1770_BCMsg itemLineMsg) {
    // 2019/01/08 QC#29241 Mod End

        // 2019/01/08 QC#29241 Mod Start
        if (NWXC150001DsCheck.checkOrdQtyVldFlg(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdTpCd.getValue(), itemLineMsg.dsOrdLineCatgCd_B.getValue(), bizMsg.slsDt.getValue())) {
            BigDecimal ordQty = itemLineMsg.ordQty_B.getValue();
            BigDecimal cpoMinOrdQty = mdseTMsg.cpoMinOrdQty.getValue();
            BigDecimal cpoMaxOrdQty = mdseTMsg.cpoMaxOrdQty.getValue();
            BigDecimal cpoIncrOrdQty = mdseTMsg.cpoIncrOrdQty.getValue();
            if (NWXC150001DsCheck.checkMinOrdQty(cpoMinOrdQty, ordQty)) {
                itemLineMsg.ordCustUomQty_B.setErrorInfo(1, NWZM2023E, new String[] {String.valueOf(cpoMinOrdQty) });
                return;
            }
            if (NWXC150001DsCheck.checkMaxOrdQty(cpoMaxOrdQty, ordQty)) {
                itemLineMsg.ordCustUomQty_B.setErrorInfo(1, NWZM2024E, new String[] {String.valueOf(cpoMaxOrdQty) });
                return;
            }
            if (NWXC150001DsCheck.checkIncrOrdQty(cpoIncrOrdQty, ordQty)) {
                itemLineMsg.ordCustUomQty_B.setErrorInfo(1, NWZM2025E, new String[] {String.valueOf(cpoIncrOrdQty) });
                return;
            }
        }
        // 2019/01/08 QC#29241 Mod End
    }
    // END 2017/10/24 J.Kim [QC#21312,ADD]

    // Add Start 2017/11/01 QC#18787(Sol#232)
    /**
     * Derive Default Days Valid
     * @param bizMsg NWAL1770CMsg
     */
    public static void deriveDefalutDaysValid(NWAL1770CMsg bizMsg) {
        BigDecimal daysValid = null;

        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getDefaultDayValid(bizMsg);

            if (ssmResult.isCodeNormal()) {
                List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
                Map<String, String> result = (Map<String, String>) resultList.get(0);
                daysValid = new BigDecimal(result.get("FIRST_BIZ_CTX_ATTRB_TXT"));
            } else {
                daysValid = BigDecimal.ZERO;
            }
        } else {
            daysValid = BigDecimal.ZERO;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteVldDaysAot, daysValid);
        ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteVldThruDt, //
                ZYPDateUtil.addDays(bizMsg.splyQuoteDt.getValue(), daysValid.intValue()));
    }
    // Add End 2017/11/01 QC#18787(Sol#232)

    // 2018/02/09 S21_NA#20297(Sol#379) Add Start
    public static boolean deriveDefaultShippingInfo(NWAL1770CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.splyQuoteCatgCd)
                && ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)
                && ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {

            NMZC610001PMsg custInfoGetApiMsg = new NMZC610001PMsg();
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_SHIPPING_DEFAULT_INFORMATION);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.slsDt, bizMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.shipToCustCd, bizMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsAcctNum_I1, bizMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsOrdCatgCd, bizMsg.splyQuoteCatgCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);

            new NMZC610001().execute(custInfoGetApiMsg, ONBATCH_TYPE.ONLINE);

            if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {

                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoGetApiMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);

                    if (msgId.endsWith("E")) {
                        return false;
                    }
                }
            }

            NMZC610001_ShippingDefaultInfoListPMsg shpgDefInfo = custInfoGetApiMsg.ShippingDefaultInfoList.no(0);
            if (!ZYPCommonFunc.hasValue(shpgDefInfo.frtCondCd)
                    && !ZYPCommonFunc.hasValue(shpgDefInfo.shpgSvcLvlCd)) {

                DS_ORD_TP_PROC_DFNTMsg ordTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
                ZYPEZDItemValueSetter.setValue(ordTpProcDfnTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(ordTpProcDfnTMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);

                ordTpProcDfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) EZDTBLAccessor.findByKey(ordTpProcDfnTMsg);
                if (ordTpProcDfnTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(shpgDefInfo.frtCondCd, ordTpProcDfnTMsg.frtCondCd);
                    ZYPEZDItemValueSetter.setValue(shpgDefInfo.shpgSvcLvlCd, ordTpProcDfnTMsg.defShpgSvcLvlCd);
                }
            }

            if (ZYPCommonFunc.hasValue(shpgDefInfo.frtCondCd)) {
                FRT_CONDTMsg frtCondTMsg = new FRT_CONDTMsg();
                ZYPEZDItemValueSetter.setValue(frtCondTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(frtCondTMsg.frtCondCd, shpgDefInfo.frtCondCd);
                frtCondTMsg = (FRT_CONDTMsg) S21FastTBLAccessor.findByKey(frtCondTMsg);

                if (frtCondTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, frtCondTMsg.frtCondCd);
                    ZYPEZDItemValueSetter.setValue(bizMsg.frtCondDescTxt, frtCondTMsg.frtCondDescTxt);
                }
            }
            if (ZYPCommonFunc.hasValue(shpgDefInfo.shpgSvcLvlCd)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd, shpgDefInfo.shpgSvcLvlCd);
                setShpgSvcLvlPullDown(bizMsg);
            }
        }

        return true;
    }

    public static boolean deriveDefaultShippingComment(NWAL1770CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.splyQuoteCatgCd)
                && ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)
                && ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {

            if (ZYPCommonFunc.hasValue(bizMsg.shpgCmntTxt)) {
                return true;
            }

            NMZC610001PMsg custInfoGetApiMsg = new NMZC610001PMsg();
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_INSTRUCTION);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.lineBizTpCd, bizMsg.lineBizTpCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsBizAreaCd, getBizAreaCd(bizMsg));
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsCustMsgTpCd, DS_CUST_MSG_TP.SHIP);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.billToCustCd, bizMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.shipToCustCd, bizMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsAcctNum_I1, bizMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.slsDt, bizMsg.slsDt);

            new NMZC610001().execute(custInfoGetApiMsg, ONBATCH_TYPE.ONLINE);

            if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {

                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoGetApiMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);

                    if (msgId.endsWith("E")) {
                        return false;
                    }
                }
            }

            StringBuilder defShpgCmnt = new StringBuilder();
            for (int i = 0; i < custInfoGetApiMsg.InstructionList.getValidCount(); i++) {
                if (defShpgCmnt.length() > 0) {
                    defShpgCmnt.append(System.getProperty("line.separator"));
                }
                defShpgCmnt.append(custInfoGetApiMsg.InstructionList.no(i).dsCustMsgTxt.getValue());
            }
            
            if (defShpgCmnt.length() > SHPG_CMT_TXT_LIMIT_SIZE) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shpgCmntTxt, defShpgCmnt.substring(0, SHPG_CMT_TXT_LIMIT_SIZE));
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.shpgCmntTxt, defShpgCmnt.toString());
            }
        }

        return true;
    }

    // 2018/12/12 QC#29315 Mod Start
    //private static String getBizAreaCd(NWAL1770CMsg bizMsg) {
    public static String getBizAreaCd(NWAL1770CMsg bizMsg) {
    // 2018/12/12 QC#29315 Mod End

        // 2018/12/12 QC#29315 Mod Start
        //ORD_CATG_BIZ_CTXTMsg ordCatgBizCtxTMsg = new ORD_CATG_BIZ_CTXTMsg();
        //ordCatgBizCtxTMsg.setSQLID("002");
        //ordCatgBizCtxTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        //ordCatgBizCtxTMsg.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        //ordCatgBizCtxTMsg.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        //ordCatgBizCtxTMsg.setConditionValue("dsOrdCatgCd01", bizMsg.splyQuoteCatgCd.getValue());
        //
        //ORD_CATG_BIZ_CTXTMsgArray ordCatgBizCtxTMsgArray = (ORD_CATG_BIZ_CTXTMsgArray) S21ApiTBLAccessor.findByCondition(ordCatgBizCtxTMsg);
        //
        //if (ordCatgBizCtxTMsgArray == null || ordCatgBizCtxTMsgArray.getValidCount() == 0) {
        //    return null;
        //}
        //
        //return ordCatgBizCtxTMsgArray.no(0).scdBizCtxAttrbTxt.getValue();
        return NWXC150001DsCheck.getDsBizArea(bizMsg.glblCmpyCd.getValue(), bizMsg.splyQuoteCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue(), null);
        // 2018/12/12 QC#29315 Mod End
    }
    // 2018/02/09 S21_NA#20297(Sol#379) Add End
    // 2018/03/19 S21_NA#24810 Add Start
    public static String getCancelStsTxt(NWAL1770CMsg bizMsg) {

        SPLY_QUOTE_STSTMsg splyQuoteStsTMsg = new SPLY_QUOTE_STSTMsg();
        ZYPEZDItemValueSetter.setValue(splyQuoteStsTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(splyQuoteStsTMsg.splyQuoteStsCd, SPLY_QUOTE_STS.CANCELLED);

        splyQuoteStsTMsg = (SPLY_QUOTE_STSTMsg) S21CacheTBLAccessor.findByKey(splyQuoteStsTMsg);

        if (splyQuoteStsTMsg == null) {
            return null;
        }

        return splyQuoteStsTMsg.splyQuoteStsDescTxt.getValue();
    }
    // 2018/03/19 S21_NA#24810 Add End
    // S21_NA#22965 Add Start
    public static PRC_CATGTMsg getPriceCategory(String glblCmpyCd, String prcCatgCd) {
        PRC_CATGTMsg prcCatg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(prcCatg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcCatg.prcCatgCd, prcCatgCd);
        return (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatg);
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

    public static String getWriterLineConfigRepCd(NWAL1770_DCMsgArray slsCrArrayConfig) {

        for (int n = 0; n < slsCrArrayConfig.getValidCount(); n++) {
            String roleTpCd = slsCrArrayConfig.no(n).slsRepRoleTpCd_D.getValue();
            if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(roleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(roleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(roleTpCd)) {
                return slsCrArrayConfig.no(n).slsRepTocCd_D.getValue();
            }
        }

        return null;
    }

    public static String getCcyCd(NWAL1770CMsg bizMsg) {

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
    // S21_NA#22965 Add End
    // 2018/04/17 S21_NA#25230 Add Start
    public static String getOrdTakeMdseCd(String glblCmpyCd, String mdseCd) {

        String subStrMdseCd = null;
        if (null != mdseCd && mdseCd.length() > 8) {
            subStrMdseCd = mdseCd.substring(0, 8);
        } else {
            subStrMdseCd = mdseCd;
        }
        ORD_TAKE_MDSETMsg ordTakeMdseMsg = new ORD_TAKE_MDSETMsg();
        ordTakeMdseMsg.setSQLID("002");
        ordTakeMdseMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        ordTakeMdseMsg.setConditionValue("ordTakeMdseCd01", subStrMdseCd);

        ORD_TAKE_MDSETMsgArray ordTakeMdseMsgArray = (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(ordTakeMdseMsg);

        String ordTakeMdseCd = mdseCd;
        if (ordTakeMdseMsgArray != null && ordTakeMdseMsgArray.getValidCount() > 0) {
            ordTakeMdseCd = ordTakeMdseMsgArray.no(0).ordTakeMdseCd.getValue();
        }

        return ordTakeMdseCd;
    }
    // 2018/04/17 S21_NA#25230 Add End
    
    // 2018/07/19 QC#26153 Add Start
    public static void doProcess_Init_BL02(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg, String glblCmpyCd, String adminPsnCd, S21UserProfileService userProfileService){

        // Initial Screen Objects
        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(bizMsg.D);
        ZYPTableUtil.clear(bizMsg.E);
        ZYPTableUtil.clear(bizMsg.T);
        ZYPTableUtil.clear(bizMsg.U);
        ZYPTableUtil.clear(bizMsg.V);
        ZYPTableUtil.clear(bizMsg.W);
        ZYPTableUtil.clear(bizMsg.X);
        ZYPTableUtil.clear(bizMsg.Y);
        ZYPTableUtil.clear(bizMsg.Z);
        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        ZYPTableUtil.clear(bizMsg.H);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.F);

        // Setting Initial Values
        NWAL1770CommonLogic.setInitialValues(bizMsg, glblCmpyCd, adminPsnCd);
        NWAL1770CommonLogic.setAuthority(bizMsg, userProfileService);

        // Create Pulldown
        NWAL1770CommonLogic.createPulldown(bizMsg);

        // Set Function Currency Digit Number
        GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblTMsg.glblCmpyCd, glblCmpyCd);
        glblTMsg = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(glblTMsg);
        if (glblTMsg != null) {
            CCYTMsg ccyMsg = new CCYTMsg();
            ZYPEZDItemValueSetter.setValue(ccyMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ccyMsg.ccyCd, glblTMsg.stdCcyCd);
            ccyMsg = (CCYTMsg) S21FastTBLAccessor.findByKey(ccyMsg);
            if (ccyMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd, ccyMsg.ccyCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.aftDeclPntDigitNum, ccyMsg.aftDeclPntDigitNum);
            }
        }
    }
    // 2018/07/19 QC#26153 Add End

    // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    /**
     * Check Supply Covered Contract and Set Warning Informations to Messages
     * @param bizMsg NWAL1770CMsg
     */
    @SuppressWarnings("unchecked")
    public static boolean checkSupplyCoveredContractInfo(NWAL1770CMsg bizMsg) {

        boolean checkResult = false;

        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
            return checkResult;
        }

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        NWAL1770Query query = NWAL1770Query.getInstance();

        S21SsmEZDResult ssmResult1 = query.getOrderContractByShipToCust(bizMsg);
        if (ssmResult1.isCodeNotFound()) {
            return checkResult;
        }

        Map<String, Map<String, Object>> mdseContrMap = new HashMap<String, Map<String, Object>>();
        Map<String, Map<String, Object>> ordTakeMdseContrMap = new HashMap<String, Map<String, Object>>();
        List<Map<String, Object>> dsContrDtlList = (List<Map<String, Object>>) ssmResult1.getResultObject();
        for (Map<String, Object> dsContrDtl : dsContrDtlList) {
            BigDecimal dsContrDtlPk = (BigDecimal) dsContrDtl.get("DS_CONTR_DTL_PK");
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
            if (isSuplIncl) {
                BigDecimal svcMachMstrPk = (BigDecimal)dsContrDtl.get("SVC_MACH_MSTR_PK");
                S21SsmEZDResult ssmResult2 = query.getMdseCdBySvcMachMstrPk(bizMsg, svcMachMstrPk);
                if (ssmResult2.isCodeNotFound()) {
                    continue;
                }

                List<Map<String, Object>> mdseList = (List<Map<String, Object>>) ssmResult2.getResultObject();
                for (Map<String, Object> mdse : mdseList) {
                    String mdseCd = (String)mdse.get("MDSE_CD");
                    if (ZYPCommonFunc.hasValue(mdseCd)) {
                        mdseContrMap.put(mdseCd, dsContrDtl);
                    }
                    String ordTakeMdseCd = (String)mdse.get("ORD_TAKE_MDSE_CD");
                    if (ZYPCommonFunc.hasValue(ordTakeMdseCd)) {
                        ordTakeMdseContrMap.put(ordTakeMdseCd, dsContrDtl);
                    }
                }
            }
        }

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            String mdseCd = bizMsg.B.no(i).mdseCd_B.getValue();
            Map<String, Object> dsContrDtl = null;

            if (mdseContrMap.containsKey(mdseCd)) {
                dsContrDtl = mdseContrMap.get(mdseCd);
            } else if (mdseCd.length() >= 8 && ordTakeMdseContrMap.containsKey(mdseCd.substring(0, 8))) {
                dsContrDtl = ordTakeMdseContrMap.get(mdseCd.substring(0, 8));
            }

            if (dsContrDtl != null) {
                NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);
                String serNum = (String)dsContrDtl.get("SER_NUM");
                String dsContrNum = (String)dsContrDtl.get("DS_CONTR_NUM");
                ZYPEZDItemValueSetter.setValue(itemLineMsg.serNum_B, serNum);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.dsContrNum_B, dsContrNum);

                String[] messageArgs = new String[]{serNum, dsContrNum};
                itemLineMsg.mdseDescShortTxt_B.setErrorInfo(2, NWAM8471W, messageArgs);

                checkResult = true;
            }
        }

        return checkResult;
    }

    /**
     * Check Supply Covered Contract
     * @param bizMsg NWAL1770CMsg
     */
    public static void checkSupplyCoveredContract(NWAL1770CMsg bizMsg) {

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);
            String serNum = itemLineMsg.serNum_B.getValue();
            String dsContrNum = itemLineMsg.dsContrNum_B.getValue();
            if (!S21StringUtil.isEmpty(serNum) && !S21StringUtil.isEmpty(dsContrNum)) {
                String[] messageArgs = new String[]{serNum, dsContrNum};
                bizMsg.B.no(i).mdseDescShortTxt_B.setErrorInfo(2, NWAM8471W, messageArgs);
            }
        }
    }
    // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
}
