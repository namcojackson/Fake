/*
 * <pre>Copyright (c) 2015-2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1330.common;

import static business.blap.NSAL1330.constant.NSAL1330Constant.CPO_DTL_LINE_NUM;
import static business.blap.NSAL1330.constant.NSAL1330Constant.CPO_DTL_LINE_SUB_NUM;
import static business.blap.NSAL1330.constant.NSAL1330Constant.DPLY_LINE_NUM;
import static business.blap.NSAL1330.constant.NSAL1330Constant.DS_CONTR_NUM;
import static business.blap.NSAL1330.constant.NSAL1330Constant.FLEET;
import static business.blap.NSAL1330.constant.NSAL1330Constant.MDSE_CD;
import static business.blap.NSAL1330.constant.NSAL1330Constant.MDSE_DESC_SHORT_TXT;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0627E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0647E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0648W;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0657E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0663E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0664E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0669E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0670W;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0671E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0672E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0674E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0678E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0680E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0682E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSZM0698E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0781E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0782E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NZZM0003E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.POP_UP_COVERED_ITEM;
import static business.blap.NSAL1330.constant.NSAL1330Constant.PRC_CATG_CD;
import static business.blap.NSAL1330.constant.NSAL1330Constant.PRC_CATG_NM;
import static business.blap.NSAL1330.constant.NSAL1330Constant.PRC_LIST_EQUIP_CONFIG_NM;
import static business.blap.NSAL1330.constant.NSAL1330Constant.PRC_LIST_EQUIP_CONFIG_NUM;
import static business.blap.NSAL1330.constant.NSAL1330Constant.PRC_MTR_PKG_NM;
import static business.blap.NSAL1330.constant.NSAL1330Constant.PRC_MTR_PKG_PK;
import static business.blap.NSAL1330.constant.NSAL1330Constant.PRC_TIER_SVC_OFFER_CD;
import static business.blap.NSAL1330.constant.NSAL1330Constant.XX_FLG_HARD;
import static business.blap.NSAL1330.constant.NSAL1330Constant.XX_FLG_PARENT;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import parts.common.EZDBMsg;
import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSBigDecimalItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL1330.NSAL1330CMsg;
import business.blap.NSAL1330.NSAL1330Query;
import business.blap.NSAL1330.NSAL1330SMsg;
import business.blap.NSAL1330.NSAL1330_ACMsg;
import business.blap.NSAL1330.NSAL1330_ASMsg;
import business.blap.NSAL1330.NSAL1330_BCMsg;
import business.blap.NSAL1330.NSAL1330_CCMsg;
import business.blap.NSAL1330.NSAL1330_ICMsg;
import business.blap.NSAL1330.NSAL1330_JCMsg;
import business.blap.NSAL1330.NSAL1330_MCMsg;
import business.blap.NSAL1330.NSAL1330_RCMsg;
import business.blap.NSAL1330.NSAL1330_RSMsg;
import business.blap.NSAL1330.NSAL1330_UCMsg;
import business.blap.NSAL1330.NSAL1330_UCMsgArray;
import business.blap.NSAL1330.NSAL1330_USMsg;
import business.blap.NSAL1330.NSAL1330_VCMsg;
import business.blap.NSAL1330.NSAL1330_VCMsgArray;
import business.blap.NSAL1330.NSAL1330_VSMsg;
import business.blap.NSAL1330.NSAL1330_XCMsg;
import business.blap.NSAL1330.NSAL1330_XCMsgArray;
import business.blap.NSAL1330.NSAL1330_XSMsg;
import business.blap.NSAL1330.NSAL1330_ZCMsg;
import business.blap.NSAL1330.NSAL1330_ZCMsgArray;
import business.blap.NSAL1330.NSAL1330_ZSMsg;
import business.blap.NSAL1330.constant.NSAL1330Constant.XX_PAGE;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CCYTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.MTR_LBTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.db.PRC_CATGTMsg;
import business.db.PRC_LIST_TPTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NSZC115001PMsg;
import business.parts.NSZC115001_svcAddlBasePrcListPMsg;
import business.parts.NSZC115001_svcAddlChrgPrcListPMsg;
import business.parts.NSZC115001_svcConfigRefListPMsg;
import business.parts.NSZC115001_svcDtlListPMsg;
import business.parts.NSZC115001_svcDtlListPMsgArray;
import business.parts.NSZC115001_svcPrcListPMsg;
import business.parts.NSZC115001_svcUsgPrcListPMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157001_xxPrcListPMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157002PMsgArray;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC115001.NSZC115001;
import com.canon.cusa.s21.api.NSZ.NSZC115001.constant.NSZC115001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRC_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 * 2017/06/06   Hitachi         Y.Takeno        Update          QC#18318
 * 2017/06/07   Hitachi         Y.Takeno        Update          QC#18827
 * 2017/06/12   Hitachi         Y.Takeno        Update          QC#18873
 * 2017/06/12   Hitachi         A.Kohinata      Update          QC#18935
 * 2017/06/12   Hitachi         A.Kohinata      Update          QC#18931
 * 2017/06/12   Hitachi         Y.Takeno        Update          QC#18842
 * 2017/06/15   Hitachi         Y.Takeno        Update          QC#19035
 * 2017/06/17   Hitachi         Y.Takeno        Update          QC#18829
 * 2017/06/20   Hitachi         Y.Takeno        Update          QC#19216
 * 2017/06/20   Hitachi         K.Kitachi       Update          QC#19377
 * 2017/06/26   Hitachi         Y.Takeno        Update          QC#19500
 * 2017/06/26   Hitachi         Y.Takeno        Update          QC#19500-1
 * 2017/07/03   Hitachi         Y.Takeno        Update          QC#19479
 * 2017/07/12   Hitachi         Y.Takeno        Update          QC#19472
 * 2017/07/20   Hitachi         Y.Takeno        Update          QC#20003
 * 2017/07/24   Hitachi         Y.Takeno        Update          QC#20055
 * 2017/10/03   Hitachi         Y.Takeno        Update          QC#20059
 * 2017/10/18   Fujitsu         R.Nakamura      Update          QC#21860
 * 2017/10/18   Hitachi         Y.Takeno        Update          QC#21851
 * 2017/10/19   Hitachi         Y.Takeno        Update          QC#21656
 * 2017/10/24   Hitachi         K.Kojima        Update          QC#21810
 * 2017/10/24   Hitachi         K.Kojima        Update          QC#21690
 * 2017/10/25   Hitachi         Y.Takeno        Update          QC#21556
 * 2018/03/12   Fujitsu         K.Ishizuka      Update          QC#24090
 * 2018/03/13   CITS            M.Naito         Update          QC#23378
 * 2018/03/20   Hitachi         U.Kim           Update          QC#24088
 * 2018/03/27   Hitachi         K.Kojima        Update          QC#24929
 * 2018/04/24   Fujitsu         A.Kosai         Update          QC#20162
 * 2018/04/24   CITS            M.Naito         Update          QC#23378
 * 2018/05/09   Fujitsu         M.Ohno          Update          QC#25030
 * 2018/06/19   Hitachi         K.Kojima        Update          QC#26591
 * 2018/07/02   Hitachi         T.Tomita        Update          QC#26738
 * 2018/07/09   Fujitsu         K.Ishizuka      Update          QC#26528
 * 2018/07/10   Hitachi         K.Kojima        Update          QC#27135
 * 2018/08/20   Fujitsu         M.Yamada        Update          QC#25104
 * 2018/08/23   Fujitsu         K.Ishizuka      Update          QC#25105
 * 2018/11/06   Hitachi         T.Tomita        Update          QC#29144
 * 2018/11/15   Hitachi         K.Kim           Update          QC#28638
 * 2018/12/11   Hitachi         T.Tomita        Update          QC#29423
 * 2018/12/12   Fujitsu         W.Honda         Update          QC#29423
 * 2018/12/19   Fujitsu         M.Yamada        Update          QC#29248
 * 2019/04/01   Hitachi         K.Kitachi       Update          QC#30966
 * 2019/05/08   Fujitsu         K.Kato          Update          QC#50174
 * 2019/06/21   Fujitsu         W.Honda         Update          QC#50842
 * 2019/06/25   Fujitsu         K.Kato          Update          QC#50895
 * 2019/07/18   Fujitsu         K.Kato          Update          S21_NA#51327
 * 2019/09/25   Fujitsu         K.Kato          Update          QC#51323
 * 2023/07/05   CITS            T.Kojima        Update          QC#61472
 * 2023/11/08   CITS            R.Jin           Update          QC#62108
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NSAL1330CommonLogic {

    static S21LRUMap<String, String> cacheForPrcListTpCd = new S21LRUMap<String, String>();

    /**
     * set Header
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean setHeader(NSAL1330CMsg bizMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(bizMsg.manContrOvrdFlg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.svcMemoRsnCd)) {
            S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getContrRsnNm(bizMsg, glblCmpyCd);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (ssmResult.getQueryResultCount() == 0) {
                bizMsg.setMessageInfo(NSAM0647E, new String[] {"manual reason", bizMsg.svcMemoRsnCd.getValue() });
                return false;
            }
            Map<String, Object> resultMap = resList.get(0);
//            ZYPEZDItemValueSetter.setValue(bizMsg.manContrOvrdRsnNm, (String) resultMap.get(MAN_CONTR_OVRD_RSN_NM));
            ZYPEZDItemValueSetter.setValue(bizMsg.svcMemoRsnDescTxt, (String) resultMap.get("SVC_MEMO_RSN_DESC_TXT"));
        }

        if (ZYPCommonFunc.hasValue(bizMsg.dsContrPk_AD)) {

            S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getDsContrNum(bizMsg, glblCmpyCd);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (ssmResult.getQueryResultCount() == 0) {
                bizMsg.setMessageInfo(NSAM0647E, new String[] {"Contract", bizMsg.dsContrPk_AD.getValue().toPlainString() });
                return false;
            }
            Map<String, Object> resultMap = resList.get(0);
            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrNum_AD, (String) resultMap.get(DS_CONTR_NUM));
        }
        String prcCatgNmB = getPrcCatgNm(bizMsg.prcCatgCd_HB.getValue(), glblCmpyCd);
        String prcCatgNmC = getPrcCatgNm(bizMsg.prcCatgCd_C.getValue(), glblCmpyCd);
        String prcCatgNmJ = getPrcCatgNm(bizMsg.prcCatgCd_HJ.getValue(), glblCmpyCd); // 2018/08/24 S21_NA#25105 Add
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_HB, prcCatgNmB);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_C, prcCatgNmC);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_HJ, prcCatgNmJ); // 2018/08/24 S21_NA#25105 Add

        return true;
    }

    /**
     * set Header
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean setHeaderUpdate(NSAL1330CMsg bizMsg, String glblCmpyCd) {

        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getHeaderUpdate(bizMsg, glblCmpyCd);
        if (ssmResult.getQueryResultCount() > 0) {
            @SuppressWarnings("unchecked")
            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();

            String manContrOvrdFlg = (String) resultMap.get("MAN_CONTR_OVRD_FLG");
            String svcMemoRsnDescTxt = (String) resultMap.get("MAN_CONTR_OVRD_RSN_NM");
            String manContrOvrdRsnCd = (String) resultMap.get("MAN_CONTR_OVRD_RSN_CD");
            String manContrOvrdCmntTxt = (String) resultMap.get("MAN_CONTR_OVRD_CMNT_TXT");
            BigDecimal dsContrPk = (BigDecimal) resultMap.get("DS_CONTR_PK");

            ZYPEZDItemValueSetter.setValue(bizMsg.svcCmntTxt, manContrOvrdCmntTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.manContrOvrdFlg, manContrOvrdFlg);
            ZYPEZDItemValueSetter.setValue(bizMsg.svcMemoRsnDescTxt, svcMemoRsnDescTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.svcMemoRsnCd, manContrOvrdRsnCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrPk_AD, dsContrPk);

        } else {
            return false;
        }

        return true;
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @return Map<BigDecimal, Integer>
     */
    public static Map<BigDecimal, Integer> countQtyI(NSAL1330CMsg bizMsg) {
        Map<BigDecimal, Integer> qtyMap = new HashMap<BigDecimal, Integer>();

        for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
            BigDecimal modelId = bizMsg.I.no(i).mdlId_I.getValue();
            if (qtyMap.containsKey(modelId)) {
                int qty = qtyMap.get(modelId).intValue();
                qty++;
                qtyMap.put(modelId, qty);
            } else {
                qtyMap.put(modelId, 1);
            }
        }
        return qtyMap;
    }

    /**
     * set SvcPrc Init Fl
     * @param bizMsg NSAL1330CMsg
     * @param countQtyList Map<BigDecimal, Integer>
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean setSvcPrcInitFl(NSAL1330CMsg bizMsg, Map<BigDecimal, Integer> countQtyList, String glblCmpyCd) {

        BigDecimal qty = BigDecimal.ZERO;
        for (Integer val : countQtyList.values()) {
            qty = qty.add(new BigDecimal(val));
        }

        // START 2017/10/18 [QC#21851, ADD]
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.addAsryFlg.getValue())) {
            return true;
        }
        // END   2017/10/18 [QC#21851, ADD]
        // START 2017/10/25 [QC#21556, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.dsContrPk_AD)) {
            return setSvcPrcInitFlAddMachine(bizMsg, countQtyList, glblCmpyCd);
        }
        // END   2017/10/25 [QC#21556, ADD]

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).t_MdlNm_A, FLEET);
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(0).t_MdlNm_Z, FLEET);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).xxTotQtyCnt_A, qty);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).termMthAot_A, bizMsg.termMthAot);
        // START 2017/10/06 [QC#20059, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).xxSelFlg_A, ZYPConstant.FLG_OFF_N);
        // END   2017/10/06 [QC#20059, ADD]
        // Mod Start 2017/10/18 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
//        bizMsg.A.no(0).xxTotPrcAmt_PB.setValue(BigDecimal.ZERO);
//        bizMsg.A.no(0).xxTotPrcAmt_EB.setValue(BigDecimal.ZERO);
//        bizMsg.A.no(0).xxTotPrcAmt_TB.setValue(BigDecimal.ZERO);
        bizMsg.A.no(0).xxTotPrcAmt_PB.setValue(round(BigDecimal.ZERO, scale));
        bizMsg.A.no(0).xxTotPrcAmt_EB.setValue(round(BigDecimal.ZERO, scale));
        bizMsg.A.no(0).xxTotPrcAmt_TB.setValue(round(BigDecimal.ZERO, scale));
        // Mod End 2017/10/18 QC#21860
        bizMsg.Z.no(0).xxFlgNm_Z.setValue(XX_FLG_PARENT);

        bizMsg.A.setValidCount(1);
        bizMsg.Z.setValidCount(1);

        // START 2017/06/12 [QC#18842, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).scrEntAvalFlg_A, ZYPConstant.FLG_ON_Y);
        for (int ixR = 0; ixR < bizMsg.R.getValidCount(); ixR++) {
            String scrEntAvalFlg = bizMsg.R.no(ixR).scrEntAvalFlg_R.getValue();
            if (ZYPConstant.FLG_OFF_N.equals(scrEntAvalFlg)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).scrEntAvalFlg_A, scrEntAvalFlg);
                break;
            }
        }
        // END   2017/06/12 [QC#18842, ADD]

        return true;
    }

    /**
     * set SvcPrc at Init
     * @param bizMsg NSAL1330CMsg
     * @param countQtyList Map<BigDecimal, Integer>
     * @param modelId BigDecimal
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean setSvcPrcInit(NSAL1330CMsg bizMsg, Map<BigDecimal, Integer> countQtyList, BigDecimal modelId, String glblCmpyCd) {

        // START 2017/10/18 [QC#21851, ADD]
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.addAsryFlg.getValue())) {
            return true;
        }
        // END   2017/10/18 [QC#21851, ADD]

        int aNum = bizMsg.A.getValidCount();

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aNum).mdlId_A, modelId);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aNum).xxTotQtyCnt_A, new BigDecimal(countQtyList.get(modelId)));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aNum).termMthAot_A, bizMsg.termMthAot);
        // START 2017/10/06 [QC#20059, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aNum).xxSelFlg_A, ZYPConstant.FLG_OFF_N);
        // END   2017/10/06 [QC#20059, ADD]
        // Mod Start 2017/10/18 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
//        bizMsg.A.no(aNum).xxTotPrcAmt_PB.setValue(BigDecimal.ZERO);
//        bizMsg.A.no(aNum).xxTotPrcAmt_EB.setValue(BigDecimal.ZERO);
//        bizMsg.A.no(aNum).xxTotPrcAmt_TB.setValue(BigDecimal.ZERO);
        bizMsg.A.no(aNum).xxTotPrcAmt_PB.setValue(round(BigDecimal.ZERO, scale));
        bizMsg.A.no(aNum).xxTotPrcAmt_EB.setValue(round(BigDecimal.ZERO, scale));
        bizMsg.A.no(aNum).xxTotPrcAmt_TB.setValue(round(BigDecimal.ZERO, scale));
        // Mod Start 2017/10/18 QC#21860

        bizMsg.A.setValidCount(aNum + 1);

        int zNum = bizMsg.Z.getValidCount();

        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zNum).mdlId_Z, modelId);
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zNum).xxFlgNm_Z, XX_FLG_PARENT);
        bizMsg.Z.setValidCount(zNum + 1);

        getMdlNm(bizMsg, aNum, glblCmpyCd);

        for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
            setSvcPrcInitByConfig(bizMsg, bizMsg.R.no(i), modelId, glblCmpyCd);
        }

        // START 2017/06/12 [QC#18842, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aNum).scrEntAvalFlg_A, ZYPConstant.FLG_ON_Y);
        for (int ixR = 0; ixR < bizMsg.R.getValidCount(); ixR++) {
            if (!modelId.equals(bizMsg.R.no(ixR).mdlId_R.getValue())) {
                continue;
            }
            String scrEntAvalFlg = bizMsg.R.no(ixR).scrEntAvalFlg_R.getValue();
            if (ZYPConstant.FLG_OFF_N.equals(scrEntAvalFlg)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aNum).scrEntAvalFlg_A, scrEntAvalFlg);
                break;
            }
        }
        // END   2017/06/12 [QC#18842, ADD]

        return true;
    }

    /**
     * set SvcPrc at Init by Config
     * @param bizMsg NSAL1330CMsg
     * @param countQtyList Map<BigDecimal, Integer>
     * @param modelId BigDecimal
     * @return boolean
     */
    public static void setSvcPrcInitByConfig(NSAL1330CMsg bizMsg, NSAL1330_RCMsg rBizMsg, BigDecimal modelId, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(modelId) || !ZYPCommonFunc.hasValue(rBizMsg.mdlId_R.getValue())) {
            return;
        }

        if (modelId.compareTo(rBizMsg.mdlId_R.getValue()) != 0) {
            return;
        }

        String prcCatgNm = getPrcCatgNm(rBizMsg.prcCatgCd_R.getValue(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rBizMsg.prcCatgNm_R, prcCatgNm);

        String prcListTpCd = getPrcListTpCd(rBizMsg.prcCatgCd_R.getValue(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rBizMsg.prcListTpCd_R, prcListTpCd);

        // START 2017/06/12 [QC#18873, MOD]
        BigDecimal baseBllgCycle = getBllgCycle(bizMsg);
        // Add Start 2017/10/18 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/18 QC#21860
        if (ZYPCommonFunc.hasValue(rBizMsg.xxTotPrcAmt_PR) && ZYPCommonFunc.hasValue(baseBllgCycle)) {
            // set Price
            BigDecimal periodicBase = rBizMsg.xxTotPrcAmt_PR.getValue();
            if (ZYPCommonFunc.hasValue(baseBllgCycle)) {

                BigDecimal term = bizMsg.termMthAot.getValue();

                BigDecimal extendedBase = periodicBase;
                BigDecimal totalBase = BigDecimal.ZERO;
                if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bizMsg.baseBllgCycleCd.getValue())) {
                    totalBase = extendedBase;
                } else {
                    // Mod Start 2017/10/19 QC#21860
//                    totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
                    totalBase = multiply(extendedBase, divide(term, baseBllgCycle, scale), scale);
                    // Mod End 2017/10/19 QC#21860
                }

                // Mod Start 2017/10/18 QC#21860
//                ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_ER, extendedBase);
//                ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_TR, totalBase);
                ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_ER, round(extendedBase, scale));
                ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_TR, round(totalBase, scale));
                // Mod Start 2017/10/18 QC#21860
            }
        } else {
            // Mod Start 2017/10/18 QC#21860
//            rBizMsg.xxTotPrcAmt_PR.setValue(BigDecimal.ZERO);
//            rBizMsg.xxTotPrcAmt_ER.setValue(BigDecimal.ZERO);
//            rBizMsg.xxTotPrcAmt_TR.setValue(BigDecimal.ZERO);
            rBizMsg.xxTotPrcAmt_PR.setValue(round(BigDecimal.ZERO, scale));
            rBizMsg.xxTotPrcAmt_ER.setValue(round(BigDecimal.ZERO, scale));
            rBizMsg.xxTotPrcAmt_TR.setValue(round(BigDecimal.ZERO, scale));
            // Mod End 2017/10/18 QC#21860
        }
        // rBizMsg.xxTotPrcAmt_PR.setValue(BigDecimal.ZERO);
        // rBizMsg.xxTotPrcAmt_ER.setValue(BigDecimal.ZERO);
        // rBizMsg.xxTotPrcAmt_TR.setValue(BigDecimal.ZERO);
        // END 2017/06/12 [QC#18873, MOD]


        int ixU = bizMsg.U.getValidCount();

        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(ixU).mdlId_U, modelId);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(ixU).dsOrdPosnNum_U, rBizMsg.dsOrdPosnNum_R);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(ixU).dsContrDtlPk_U, rBizMsg.dsContrDtlPk_R);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(ixU).xxFlgNm_U, XX_FLG_PARENT);
        bizMsg.U.setValidCount(ixU + 1);
    }

    /**
     * set SvcPricing at Update
     * @param bizMsg NSAL1330CMsg
     * @param mdlId BigDecimal
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean setSvcPricingReOpen(NSAL1330CMsg bizMsg, BigDecimal mdlId, String glblCmpyCd) {

        int aIndex = 0;
        for (; aIndex < bizMsg.A.getValidCount(); aIndex++) {
            if (mdlId.compareTo(bizMsg.A.no(aIndex).mdlId_A.getValue()) == 0) {
                break;
            }
        }

        NSAL1330_ACMsg aBizMsg = bizMsg.A.no(aIndex);

        // START 2017/10/10 [QC#20059, ADD]
        if (isConfigLevelPriceSetting(aBizMsg)) {
            aBizMsg.xxTotPrcAmt_PB.clear();
            getMdlNm(bizMsg, aIndex, glblCmpyCd);
            return true;
        }
        // END   2017/10/10 [QC#20059, ADD]

        if (ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_A)) {

            S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getSvcPricingPkList(bizMsg, aIndex, glblCmpyCd);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resList != null && !resList.isEmpty()) {

                for (int j = 0; j < resList.size(); j++) {
                    Map<String, Object> result = resList.get(j);
                    ZYPEZDItemValueSetter.setValue(aBizMsg.prcMtrPkgPk_KP.no(j), (BigDecimal) result.get("PRC_MTR_PKG_PK"));
                    ZYPEZDItemValueSetter.setValue(aBizMsg.prcMtrPkgNm_VW.no(j), (String) result.get("PRC_MTR_PKG_NM"));
                }
            }
        }
        // get Name
        getMdlNm(bizMsg, aIndex, glblCmpyCd);
        String prcCatgNm = getPrcCatgNm(aBizMsg.prcCatgCd_A.getValue(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcCatgNm_A, prcCatgNm);

        String prcListTpCd = getPrcListTpCd(aBizMsg.prcCatgCd_A.getValue(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcListTpCd_A, prcListTpCd);

        // Add Start 2017/10/19 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21860
        for (int j = 0; j < bizMsg.Z.getValidCount(); j++) {
            NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(j);
            if (!ZYPCommonFunc.hasValue(zBizMsg.mdlId_Z)) {
                continue;
            }
            if (aBizMsg.mdlId_A.getValue().compareTo(zBizMsg.mdlId_Z.getValue()) == 0) {
                // set BackUp
                ZYPEZDItemValueSetter.setValue(zBizMsg.mlyCopyInclPrcQty_BK, zBizMsg.mlyCopyInclPrcQty_Z);
                ZYPEZDItemValueSetter.setValue(zBizMsg.contrMtrMultRate_BK, zBizMsg.contrMtrMultRate_Z);
                // Mod Start 2017/10/18 QC#21860
//                ZYPEZDItemValueSetter.setValue(zBizMsg.xsMtrAmtRate_BK, zBizMsg.xsMtrAmtRate_Z);
                ZYPEZDItemValueSetter.setValue(zBizMsg.xsMtrAmtRate_BK, round(zBizMsg.xsMtrAmtRate_Z.getValue(), scale));
                // Mod End 2017/10/18 QC#21860
                ZYPEZDItemValueSetter.setValue(zBizMsg.minCopyVolCnt_BK, zBizMsg.minCopyVolCnt_Z);
                ZYPEZDItemValueSetter.setValue(zBizMsg.maxCopyVolCnt_BK, zBizMsg.maxCopyVolCnt_Z);

//                if (ZYPCommonFunc.hasValue(zBizMsg.regMtrLbCd_Z)) {
//                    zBizMsg.xxFlgNm_Z.setValue(XX_FLG_HARD);
//                } else if (ZYPCommonFunc.hasValue(zBizMsg.prcSvcTierTpCd_Z)) {
//                    zBizMsg.xxFlgNm_Z.setValue(XX_FLG_TIER);
//                } else {
//                    zBizMsg.xxFlgNm_Z.setValue(XX_FLG_PARENT);
//                }
                // set Name
                if (ZYPCommonFunc.hasValue(zBizMsg.bllgMtrLbCd_Z)) {
                    String bllgMtrNm = getMtrNm(zBizMsg.bllgMtrLbCd_Z.getValue(), glblCmpyCd);
                    if (!ZYPCommonFunc.hasValue(bllgMtrNm)) {
                        bizMsg.setMessageInfo(NSAM0647E, new String[] {"MTR_LB", zBizMsg.bllgMtrLbCd_Z.getValue() });
                    } else {
                        ZYPEZDItemValueSetter.setValue(zBizMsg.mtrLbDescTxt_ZB, bllgMtrNm);
                    }
                }
                String mdseNm = getMdseNm(zBizMsg.usgMdseCd_Z.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(zBizMsg.mdseDescShortTxt_Z, mdseNm);

                if (ZYPCommonFunc.hasValue(zBizMsg.regMtrLbCd_Z)) {
                    String regMtrNm = getMtrNm(zBizMsg.regMtrLbCd_Z.getValue(), glblCmpyCd);
                    if (!ZYPCommonFunc.hasValue(regMtrNm)) {
                        bizMsg.setMessageInfo(NSAM0647E, new String[] {"MTR_LB", zBizMsg.regMtrLbCd_Z.getValue() });
                    } else {
                        ZYPEZDItemValueSetter.setValue(zBizMsg.mtrLbDescTxt_Z, regMtrNm);
                    }
                }
            }
        }
        BigDecimal baseBllgCycle = getBllgCycle(bizMsg);
        // START 2017/06/15 [QC#19035, ADD]
        if (ZYPCommonFunc.hasValue(aBizMsg.xxTotPrcAmt_PB)) {
            // set Price
            BigDecimal periodicBase = aBizMsg.xxTotPrcAmt_PB.getValue();
            // START 2017/10/06 [QC#20059, DEL]
            // BigDecimal qty = aBizMsg.xxTotQtyCnt_A.getValue();
            // END   2017/10/06 [QC#20059, DEL]
            // START 2018/03/20 U.Kim [QC#24088,ADD]
            BigDecimal qty = aBizMsg.xxTotQtyCnt_A.getValue();
            // END 2018/03/20 U.Kim [QC#24088,ADD]
            if (ZYPCommonFunc.hasValue(baseBllgCycle)) {

                BigDecimal term = bizMsg.termMthAot.getValue();

                BigDecimal extendedBase = BigDecimal.ZERO;
                BigDecimal totalBase = BigDecimal.ZERO;

                // START 2017/10/06 [QC#20059, MOD]
                // if (ZYPCommonFunc.hasValue(periodicBase) && ZYPCommonFunc.hasValue(qty)) {
                //     extendedBase = periodicBase.multiply(qty);
                // }
                // START 2018/03/20 U.Kim [QC#24088,MOD]
                // extendedBase = periodicBase;
                // START 2018/06/19 K.Kojima [QC#26591,MOD]
                // extendedBase = getExtendedBase(periodicBase, qty);
                extendedBase = getExtendedBase(periodicBase, qty, bizMsg.dsContrCatgCd.getValue());
                // END 2018/06/19 K.Kojima [QC#26591,MOD]
                // END 2018/03/20 U.Kim [QC#24088,MOD]
                // END   2017/10/06 [QC#20059, MOD]

                if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bizMsg.baseBllgCycleCd.getValue())) {
                    totalBase = extendedBase;
                } else {
                    // Mod Start 2017/10/19 QC#21860
//                    totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
                    totalBase = multiply(extendedBase, divide(term, baseBllgCycle, scale), scale);
                    // Mod End 2017/10/19 QC#21860
                }

                // Mod Start 2017/10/18 QC#21860
//                ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_EB, extendedBase);
//                ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_TB, totalBase);
                ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_EB, round(extendedBase, scale));
                ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_TB, round(totalBase, scale));
                // Mod End 2017/10/18 QC#21860
            }
        }
        // END   2017/06/15 [QC#19035, ADD]

        // START 2017/10/10 [QC#20059, DEL]
        // for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
        //     setSvcPricingReOpenConfig(bizMsg, bizMsg.R.no(i), glblCmpyCd, baseBllgCycle);
        // }
        // END   2017/10/10 [QC#20059, DEL]
        return true;
    }

    /**
     * set SvcPricing at Update
     * @param bizMsg NSAL1330CMsg
     * @param rBizMsg BigDecimal
     * @param glblCmpyCd String
     * @param baseBllgCycle billing cycle
     * @return boolean
     */
    public static boolean setSvcPricingReOpenConfig(//
            NSAL1330CMsg bizMsg, NSAL1330_RCMsg rBizMsg, String glblCmpyCd, BigDecimal baseBllgCycle) {

        //        if (ZYPCommonFunc.hasValue(rBizMsg.prcMtrPkgPk_R)) {
        //            S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getSvcPricingPkList(rBizMsg.prcMtrPkgPk_R.getValue());
        //            @SuppressWarnings("unchecked")
        //            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        //            if (resList != null && !resList.isEmpty()) {
        //
        //                for (int j = 0; j < resList.size(); j++) {
        //                    Map<String, Object> result = resList.get(j);
        //                    ZYPEZDItemValueSetter.setValue(rBizMsg.prcMtrPkgPk_RL.no(j), (BigDecimal) result.get("PRC_MTR_PKG_PK"));
        //                    ZYPEZDItemValueSetter.setValue(rBizMsg.prcMtrPkgNm_RL.no(j), (String) result.get("PRC_MTR_PKG_NM"));
        //                }
        //            }
        //        }
        // get Name
        String prcCatgNm = getPrcCatgNm(rBizMsg.prcCatgCd_R.getValue(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rBizMsg.prcCatgNm_R, prcCatgNm);

        String prcListTpCd = getPrcListTpCd(rBizMsg.prcCatgCd_R.getValue(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rBizMsg.prcListTpCd_R, prcListTpCd);

        for (int j = 0; j < bizMsg.U.getValidCount(); j++) {
            NSAL1330_UCMsg uBizMsg = bizMsg.U.no(j);
            if (!ZYPCommonFunc.hasValue(uBizMsg.mdlId_U)) {
                continue;
            }
            if (rBizMsg.mdlId_R.getValue().compareTo(uBizMsg.mdlId_U.getValue()) == 0 //
                    && rBizMsg.dsOrdPosnNum_R.getValue().equals(uBizMsg.dsOrdPosnNum_U.getValue())) { //QC#16141
//                if (!ZYPCommonFunc.hasValue(rBizMsg.cpoSvcConfigRefPk_R) || !ZYPCommonFunc.hasValue(uBizMsg.cpoSvcConfigRefPk_U)) {
                if (!ZYPCommonFunc.hasValue(rBizMsg.dsContrDtlPk_R) || !ZYPCommonFunc.hasValue(uBizMsg.dsContrDtlPk_U)) {
                    continue;
                }
//                if (rBizMsg.cpoSvcConfigRefPk_R.getValue().compareTo(uBizMsg.cpoSvcConfigRefPk_U.getValue()) != 0) {
                if (rBizMsg.dsContrDtlPk_R.getValue().compareTo(uBizMsg.dsContrDtlPk_U.getValue()) != 0) {
                    continue;
                }

//                if (ZYPCommonFunc.hasValue(uBizMsg.regMtrLbCd_U)) {
//                    uBizMsg.xxFlgNm_U.setValue(XX_FLG_HARD);
//                } else if (ZYPCommonFunc.hasValue(uBizMsg.prcSvcTierTpCd_U)) {
//                    uBizMsg.xxFlgNm_U.setValue(XX_FLG_TIER);
//                } else {
//                    uBizMsg.xxFlgNm_U.setValue(XX_FLG_PARENT);
//                }
                // set Name
                if (ZYPCommonFunc.hasValue(uBizMsg.bllgMtrLbCd_U)) {
                    String bllgMtrNm = getMtrNm(uBizMsg.bllgMtrLbCd_U.getValue(), glblCmpyCd);
                    if (!ZYPCommonFunc.hasValue(bllgMtrNm)) {
                        bizMsg.setMessageInfo(NSAM0647E, new String[] {"MTR_LB", uBizMsg.bllgMtrLbCd_U.getValue() });
                    } else {
                        ZYPEZDItemValueSetter.setValue(uBizMsg.mtrLbDescTxt_UB, bllgMtrNm);
                    }
                }
                String mdseNm = getMdseNm(uBizMsg.usgMdseCd_U.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(uBizMsg.mdseDescShortTxt_U, mdseNm);

                if (ZYPCommonFunc.hasValue(uBizMsg.regMtrLbCd_U)) {
                    String regMtrNm = getMtrNm(uBizMsg.regMtrLbCd_U.getValue(), glblCmpyCd);
                    if (!ZYPCommonFunc.hasValue(regMtrNm)) {
                        bizMsg.setMessageInfo(NSAM0647E, new String[] {"MTR_LB", uBizMsg.regMtrLbCd_U.getValue() });
                    } else {
                        ZYPEZDItemValueSetter.setValue(uBizMsg.mtrLbDescTxt_U, regMtrNm);
                    }
                }
            }
        }
        if (ZYPCommonFunc.hasValue(rBizMsg.xxTotPrcAmt_PR)) {
            // set Price
            BigDecimal periodicBase = rBizMsg.xxTotPrcAmt_PR.getValue();
            if (ZYPCommonFunc.hasValue(baseBllgCycle)) {

                BigDecimal term = bizMsg.termMthAot.getValue();

                BigDecimal extendedBase = periodicBase;
                BigDecimal totalBase = BigDecimal.ZERO;
                // Mod Start 2017/10/18 QC#21860
                GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
                int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
                if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bizMsg.baseBllgCycleCd.getValue())) {
                    totalBase = extendedBase;
                } else {
//                    totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
                    totalBase = multiply(extendedBase, divide(term, baseBllgCycle, scale), scale);
                }

//                ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_ER, extendedBase);
//                ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_TR, totalBase);
                ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_ER, round(extendedBase, scale));
                ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_TR, round(totalBase, scale));
                // Mod End 2017/10/18 QC#21860
            }
        }

        return true;
    }

    /**
     * set SvcPricing at Update
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean setSvcPricingReOpenFL(NSAL1330CMsg bizMsg, String glblCmpyCd) {

        int aIndex = 0;

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(aIndex).prcMtrPkgPk_A)) {

            S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getSvcPricingPkList(bizMsg, aIndex, glblCmpyCd);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resList != null && !resList.isEmpty()) {

                for (int j = 0; j < resList.size(); j++) {
                    Map<String, Object> result = resList.get(j);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).prcMtrPkgPk_KP.no(j), (BigDecimal) result.get("PRC_MTR_PKG_PK"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).prcMtrPkgNm_VW.no(j), (String) result.get("PRC_MTR_PKG_NM"));
                }
            }
        }
        // get Name
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).t_MdlNm_A, FLEET);
        String prcCatgNm = getPrcCatgNm(bizMsg.A.no(aIndex).prcCatgCd_A.getValue(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).prcCatgNm_A, prcCatgNm);

        String prcListTpCd = getPrcListTpCd(bizMsg.A.no(aIndex).prcCatgCd_A.getValue(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).prcListTpCd_A, prcListTpCd);

        for (int j = 0; j < bizMsg.Z.getValidCount(); j++) {
            // set BackUp
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).mlyCopyInclPrcQty_BK, bizMsg.Z.no(j).mlyCopyInclPrcQty_Z);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).contrMtrMultRate_BK, bizMsg.Z.no(j).contrMtrMultRate_Z);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).xsMtrAmtRate_BK, bizMsg.Z.no(j).xsMtrAmtRate_Z);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).minCopyVolCnt_BK, bizMsg.Z.no(j).minCopyVolCnt_Z);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).maxCopyVolCnt_BK, bizMsg.Z.no(j).maxCopyVolCnt_Z);

// START 2017/06/07 [QC#18827, DEL]
//            if (ZYPCommonFunc.hasValue(bizMsg.Z.no(j).regMtrLbCd_Z)) {
//                bizMsg.Z.no(j).xxFlgNm_Z.setValue(XX_FLG_HARD);
//            } else if (ZYPCommonFunc.hasValue(bizMsg.Z.no(j).prcSvcTierTpCd_Z)) {
//                bizMsg.Z.no(j).xxFlgNm_Z.setValue(XX_FLG_TIER);
//            } else {
//                bizMsg.Z.no(j).xxFlgNm_Z.setValue(XX_FLG_PARENT);
//            }
// END   2017/06/07 [QC#18827, DEL]

            if (ZYPCommonFunc.hasValue(bizMsg.Z.no(j).bllgMtrLbCd_Z)) {
                String bllgMtrNm = getMtrNm(bizMsg.Z.no(j).bllgMtrLbCd_Z.getValue(), glblCmpyCd);
                if (!ZYPCommonFunc.hasValue(bllgMtrNm)) {
                    bizMsg.setMessageInfo(NSAM0647E, new String[] {"MTR_LB", bizMsg.Z.no(j).bllgMtrLbCd_Z.getValue() });
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).mtrLbDescTxt_ZB, bllgMtrNm);
                }
            }
            if (ZYPCommonFunc.hasValue(bizMsg.Z.no(j).usgMdseCd_Z)) {
                String mdseNm = getMdseNm(bizMsg.Z.no(j).usgMdseCd_Z.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).mdseDescShortTxt_Z, mdseNm);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.Z.no(j).regMtrLbCd_Z)) {
                String regMtrNm = getMtrNm(bizMsg.Z.no(j).regMtrLbCd_Z.getValue(), glblCmpyCd);
                if (!ZYPCommonFunc.hasValue(regMtrNm)) {
                    bizMsg.setMessageInfo(NSAM0647E, new String[] {"MTR_LB", bizMsg.Z.no(j).regMtrLbCd_Z.getValue() });
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).mtrLbDescTxt_Z, regMtrNm);
                }
            }

        }
        if (ZYPCommonFunc.hasValue(bizMsg.A.no(aIndex).xxTotPrcAmt_PB)) {
            // set Price
            BigDecimal periodicBase = bizMsg.A.no(aIndex).xxTotPrcAmt_PB.getValue();
            //            BigDecimal qty = bizMsg.A.no(aIndex).xxTotQtyCnt_A.getValue();
            BigDecimal baseBllgCycle = getBllgCycle(bizMsg);
            // START 2018/03/20 U.Kim [QC#24088,ADD]
            BigDecimal qty = bizMsg.A.no(aIndex).xxTotQtyCnt_A.getValue();
            // END 2018/03/20 U.Kim [QC#24088,ADD]
            if (ZYPCommonFunc.hasValue(baseBllgCycle)) {

                BigDecimal term = bizMsg.termMthAot.getValue();

                // START 2018/03/20 U.Kim [QC#24088,MOD]
                // BigDecimal extendedBase = periodicBase;
                // START 2018/06/19 K.Kojima [QC#26591,MOD]
                // BigDecimal extendedBase = getExtendedBase(periodicBase, qty);
                BigDecimal extendedBase = getExtendedBase(periodicBase, qty, bizMsg.dsContrCatgCd.getValue());
                // END 2018/06/19 K.Kojima [QC#26591,MOD]
                // END 2018/03/20 U.Kim [QC#24088,MOD]
                //                BigDecimal totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
                BigDecimal totalBase = BigDecimal.ZERO;
                // Mod Start 2017/10/18 QC#21860
                GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
                int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());

                if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bizMsg.baseBllgCycleCd.getValue())) {
                    totalBase = extendedBase;
                } else {
//                    totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
                    totalBase = multiply(extendedBase, divide(term, baseBllgCycle, scale), scale);
                }

//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_EB, extendedBase);
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_TB, totalBase);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_EB, round(extendedBase, scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_TB, round(totalBase, scale));
                // Mod End 2017/10/18 QC#21860
            }
        }

        return true;
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return BigDecimal
     */
    public static BigDecimal getBllgCycle(NSAL1330CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getbllgCycle(bizMsg);
        if (ssmResult.isCodeNormal()) {

            BigDecimal bllgCycle = (BigDecimal) ssmResult.getResultObject();
            if (ZYPCommonFunc.hasValue(bllgCycle)) {
                return bllgCycle;
            }
        }
        bizMsg.setMessageInfo(NSAM0647E, new String[] {"BLLG_CYCLE", bizMsg.baseBllgCycleCd.getValue() });
        return null;
    }

    /**
     * get ModelNm
     * @param bizMsg NSAL1330CMsg
     * @param index int
     * @param glblCmpyCd String
     */
    public static void getMdlNm(NSAL1330CMsg bizMsg, int index, String glblCmpyCd) {

        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getMdlNm(bizMsg, index, glblCmpyCd);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        NSAL1330_ACMsg aBizMsg = bizMsg.A.no(index);
        BigDecimal mdlId = aBizMsg.mdlId_A.getValue();
        if (resList != null && !resList.isEmpty() && ZYPCommonFunc.hasValue(aBizMsg.mdlId_A)) {

            Map<String, Object> result = resList.get(0);
            String mdlNm = (String) result.get("T_MDL_NM");
            ZYPEZDItemValueSetter.setValue(aBizMsg.t_MdlNm_A, mdlNm);
            for (int j = 0; j < bizMsg.Z.getValidCount(); j++) {
                NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(j);
                if (!ZYPCommonFunc.hasValue(zBizMsg.mdlId_Z)) {
                    continue;
                }
                if (mdlId.compareTo(zBizMsg.mdlId_Z.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(zBizMsg.t_MdlNm_Z, mdlNm);
                }
            }
            for (int ixU = 0; ixU < bizMsg.U.getValidCount(); ixU++) {
                NSAL1330_UCMsg uBizMsg = bizMsg.U.no(ixU);
                if (!ZYPCommonFunc.hasValue(uBizMsg.mdlId_U)) {
                    continue;
                }
                if (mdlId.compareTo(uBizMsg.mdlId_U.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(uBizMsg.t_MdlNm_U, mdlNm);
                }
            }
        } else {
            bizMsg.setMessageInfo(NSAM0647E, new String[] {"Model", mdlId.toPlainString() });

        }
    }

    /**
     * get MeterNm
     * @param mtrLbCd String
     * @param glblCmpyCd String
     * @return String
     */
    public static String getMtrNm(String mtrLbCd, String glblCmpyCd) {
        // QC#28398
        MTR_LBTMsg tMsg = new MTR_LBTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mtrLbCd, mtrLbCd);
        tMsg = (MTR_LBTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if(tMsg == null){
            return null;
        }
        return tMsg.mtrLbDescTxt.getValue();
        

//        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getMtrNm(mtrLbCd, glblCmpyCd);
//        @SuppressWarnings("unchecked")
//        List<String> resList = (List<String>) ssmResult.getResultObject();
//        if (resList != null && !resList.isEmpty() && ZYPCommonFunc.hasValue(mtrLbCd)) {
//
//            String resultStr = resList.get(0);
//            return resultStr;
//        }
//
//        return null;
    }

    /**
     * /** get PrcListNm
     * @param prcCatgCd String
     * @param glblCmpyCd String
     * @return String
     */
    public static String getPrcCatgNm(String prcCatgCd, String glblCmpyCd) {
        // QC#28398
        PRC_CATGTMsg tMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, prcCatgCd);
        tMsg = (PRC_CATGTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return "";
        }
        return tMsg.prcCatgNm.getValue();

//        String prcCatgNm = "";
//
//        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcCatgNm(prcCatgCd, glblCmpyCd);
//        @SuppressWarnings("unchecked")
//        Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();
//        if (result != null && !result.isEmpty()) {
//
//            prcCatgNm = (String) result.get("PRC_CATG_NM");
//        }
//        return prcCatgNm;
    }

    /**
     * /** get PrcListNm
     * @param prcCatgCd String
     * @param glblCmpyCd String
     * @return String
     */
    public static String getPrcListTpCd(String prcCatgCd, String glblCmpyCd) {
        // QC#28398
        return getPrcListTpCd(prcCatgCd, glblCmpyCd, cacheForPrcListTpCd);

//        String prcListTpCd = "";
//
//        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcListTp(prcCatgCd, glblCmpyCd);
//        @SuppressWarnings("unchecked")
//        Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();
//        if (result != null && !result.isEmpty()) {
//
//            prcListTpCd = (String) result.get("PRC_LIST_TP_CD");
//        }
//        return prcListTpCd;
    }

    // QC#28398
    private static String getPrcListTpCd(String prcCatgCd, String glblCmpyCd, S21LRUMap<String, String> cache) {

        if (cache.contains(prcCatgCd)) {
            return cache.get(prcCatgCd);
        }
        String prcListTpCd = "";
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcListTp(prcCatgCd, glblCmpyCd);
        Map<String, String> result = (Map<String, String>) ssmResult.getResultObject();
        if (result != null && !result.isEmpty()) {

            prcListTpCd = (String) result.get("PRC_LIST_TP_CD");
        }
        cache.put(prcCatgCd, prcListTpCd);
        return prcListTpCd;
    }

    /**
     * get MdseNm
     * @param mdseCd String
     * @param glblCmpyCd String
     * @return String
     */
    public static String getMdseNm(String mdseCd, String glblCmpyCd) {
        // Add Start 2018/11/06 QC#29144
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return null;
        }
        // Add End 2018/11/06 QC#29144

        // QC#28398
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
        // Add Start 2018/11/06 QC#29144
        if (mdseTMsg == null) {
            return null;
        }
        // Add End 2018/11/06 QC#29144
        return mdseTMsg.mdseDescShortTxt.getValue();

//        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getMdseNm(mdseCd, glblCmpyCd);
//        String result1 = (String) ssmResult.getResultObject();
//        if (ZYPCommonFunc.hasValue(result1)) {
//
//            return result1;
//        }
//        return null;
    }

    /**
     * set AccessoryChrg Init
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean setAcsryChrgInitForRental(NSAL1330CMsg bizMsg, String glblCmpyCd) {
        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.rntlOrdFlg.getValue())) {
            return true;
        }

        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getInitAcsryForRental(bizMsg, glblCmpyCd, ZYPConstant.FLG_OFF_N);
        if (ssmResult.isCodeNotFound()) {
            return true;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();

        int ixB = bizMsg.B.getValidCount();
        int ixJ = bizMsg.J.getValidCount();
        for (int i = 0; i < resList.size(); i++) {
            Map<String, Object> resultMap = resList.get(i);
            String rentalFlg = (String) resultMap.get("RENTAL_FLG");
            String acsryFlg = (String) resultMap.get("ACSRY_FLG");

            String lineNo = (String) resultMap.get(DPLY_LINE_NUM);
            String mdseCd = (String) resultMap.get(MDSE_CD);
            String mdseNm = (String) resultMap.get(MDSE_DESC_SHORT_TXT);
            boolean newLineFlag = true;

            if (ZYPConstant.FLG_ON_Y.equals(rentalFlg)) {
                for (int j = 0; j < bizMsg.B.getValidCount(); j++) {
                    if (lineNo.equals(bizMsg.B.no(j).xxLineNum_B.getValue())) {
                        newLineFlag = false;
                        break;
                    }
                }
                if (!newLineFlag) {
                    continue;
                }

                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).shellLineNum_B, bizMsg.shellLineNum);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).xxLineNum_B, lineNo);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).mdseCd_B, mdseCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).mdseDescShortTxt_B, mdseNm);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).cpoDtlLineNum_B, (String) resultMap.get(CPO_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).cpoDtlLineSubNum_B, (String) resultMap.get(CPO_DTL_LINE_SUB_NUM));

                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).addlBasePrcCatgCd_B, (String) resultMap.get(PRC_CATG_CD));
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).prcCatgNm_B, (String) resultMap.get(PRC_CATG_NM));
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).prcListEquipConfigNum_B, (BigDecimal) resultMap.get(PRC_LIST_EQUIP_CONFIG_NUM));
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).prcListEquipConfigNm_B, (String) resultMap.get(PRC_LIST_EQUIP_CONFIG_NM));
                ixB++;
            }

            if (ZYPConstant.FLG_ON_Y.equals(acsryFlg)) {
                for (int j = 0; j < bizMsg.J.getValidCount(); j++) {
                    if (lineNo.equals(bizMsg.J.no(j).xxLineNum_J.getValue())) {
                        newLineFlag = false;
                        break;
                    }
                }
                if (!newLineFlag) {
                    continue;
                }

                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ixJ).shellLineNum_J, bizMsg.shellLineNum);
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ixJ).xxLineNum_J, lineNo);
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ixJ).mdseCd_J, mdseCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ixJ).mdseDescShortTxt_J, mdseNm);
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ixJ).cpoDtlLineNum_J, (String) resultMap.get(CPO_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ixJ).cpoDtlLineSubNum_J, (String) resultMap.get(CPO_DTL_LINE_SUB_NUM));

                ixJ++;
            }
        }
        bizMsg.B.setValidCount(ixB);
        bizMsg.J.setValidCount(ixJ);

        setDefaultPrcForRental(bizMsg);

        //    }
        //    else {
        //                int ixJ = bizMsg.B.getValidCount();
        //                for (int i = 0; i < resList.size(); i++) {
        //                    Map<String, Object> resultMap = resList.get(i);
        //                    String lineNo = (String) resultMap.get(DPLY_LINE_NUM);
        //                    String mdseCd = (String) resultMap.get(MDSE_CD);
        //                    String mdseNm = (String) resultMap.get(MDSE_DESC_SHORT_TXT);
        //
        //                    ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ixJ).xxLineNum_J, lineNo);
        //                    ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ixJ).mdseCd_J, mdseCd);
        //                    ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ixJ).mdseDescShortTxt_J, mdseNm);
        //                    ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ixJ).cpoDtlLineNum_J, (String) resultMap.get(CPO_DTL_LINE_NUM));
        //                    ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ixJ).cpoDtlLineSubNum_J, (String) resultMap.get(CPO_DTL_LINE_SUB_NUM));
        //                    ixJ++;
        //                }
        //                bizMsg.J.setValidCount(ixJ);
        //            }
        //        } else {
        //            bizMsg.B.setValidCount(1);
        // NSAL1330CommonLogic.setInitAccessory(bizMsg, 0, glblCmpyCd);

        return true;
    }

    public static void setDefaultPrcForRental(NSAL1330CMsg bizMsg) {
        if (bizMsg.getScreenAplID().endsWith("Save")) {
            return;
        }
        // Add Start 2018/09/04 QC#
        if (!isScrEntAvalForRental(bizMsg)) {
            return;
        }
        // Add End 2018/09/04 QC#

        final NWZC157001 pricAPI = new NWZC157001();

        // Mod Start 2018/12/11 QC#29423
        String msgId = null;
        Map<String, BigDecimal> mdseMap = new HashMap<String, BigDecimal>();
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NSAL1330_BCMsg bBizMsg = bizMsg.B.no(i);

            // if already have price, not necessary
            if (ZYPCommonFunc.hasValue(bBizMsg.addlBasePrcDealAmt_B)) {
                continue;
            }

            // if same item, not necessary
            BigDecimal unitPrc = mdseMap.get(bBizMsg.mdseCd_B.getValue());
            if (unitPrc != null) {
                mdseMap.put(bBizMsg.mdseCd_B.getValue(), unitPrc);
            }

            NWZC157001PMsg pMsg = getPrcingAPiPMsgForRentalPrc(bizMsg, i);

            // [NWZC157001] : Pricing API
            pricAPI.execute(pMsg, ONBATCH_TYPE.ONLINE);

            // has API errors.
            boolean errFlg = false;
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                for (int j = 0; j < msgList.size(); j++) {
                    S21ApiMessage msg = msgList.get(j);
                    msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    if (bizMsg.getScreenAplID().endsWith("Delete")) {
                        String wkMsg = S21MessageFunc.clspGetMessage(msgId, msgPrms);
                        bizMsg.setMessageInfo("NSAM0673W", new String[] {"NWZC1570 Pricing API", wkMsg });
                    } else {
                        bizMsg.setMessageInfo(msgId, msgPrms);
                    }
                    if (msgId.endsWith("E")) {
                        errFlg = true;
                        break;
                    }
                }
            }
            if (errFlg) {
                continue;
            }

            for (int j = 0; j < pMsg.NWZC157002PMsg.getValidCount(); j++) {
                NWZC157002PMsg p2Msg = pMsg.NWZC157002PMsg.no(j);
                if (S21ApiUtil.isXxMsgId(p2Msg)) {
                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(p2Msg);
                    for (int k = 0; k < msgList.size(); k++) {
                        S21ApiMessage msg = msgList.get(k);
                        msgId = msg.getXxMsgid();
                        String[] msgPrms = msg.getXxMsgPrmArray();
                        if (bizMsg.getScreenAplID().endsWith("Delete")) {
                            String wkMsg = S21MessageFunc.clspGetMessage(msgId, msgPrms);
                            bizMsg.setMessageInfo("NSAM0673W", new String[] {"NWZC1570 Pricing API", wkMsg });
                        } else {
                            bizMsg.setMessageInfo(msgId, msgPrms);
                        }
                        if (msgId.endsWith("E")) {
                            errFlg = true;
                            break;
                        }
                    }
                }
            }
            if (errFlg) {
                continue;
            }

            GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
            int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
            for (int j = 0; j < pMsg.NWZC157004PMsg.getValidCount(); j++) {
                NWZC157004PMsg p4Msg = pMsg.NWZC157004PMsg.no(j);
                if (S21ApiUtil.isXxMsgId(p4Msg)) {
                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(p4Msg);
                    for (int k = 0; k < msgList.size(); k++) {
                        S21ApiMessage msg = msgList.get(k);
                        msgId = msg.getXxMsgid();
                        String[] msgPrms = msg.getXxMsgPrmArray();
                        if (bizMsg.getScreenAplID().endsWith("Delete")) {
                            String wkMsg = S21MessageFunc.clspGetMessage(msgId, msgPrms);
                            bizMsg.setMessageInfo("NSAM0673W", new String[] {"NWZC1570 Pricing API", wkMsg });
                        } else {
                            bizMsg.setMessageInfo(msgId, msgPrms);
                        }
                        if (msgId.endsWith("E")) {
                            errFlg = true;
                            break;
                        }
                    }
                }
                if (errFlg) {
                    continue;
                }

                if (ZYPCommonFunc.hasValue(p4Msg.xxTotBaseAmt.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).addlBasePrcDealAmt_B, round(p4Msg.xxTotBaseAmt.getValue(), scale));
                    mdseMap.put(bBizMsg.mdseCd_B.getValue(), round(p4Msg.xxTotBaseAmt.getValue(), scale));
                }
            }
        }
//
//        final NWZC157001PMsg pMsg = getPrcingAPiPMsgForRentalPrc(bizMsg);
//
//        // [NWZC157001] : Pricing API
//        pricAPI.execute(pMsg, ONBATCH_TYPE.ONLINE);
//
//        String msgId = null;
//        // has API errors.
//        if (S21ApiUtil.isXxMsgId(pMsg)) {
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
//            for (int i = 0; i < msgList.size(); i++) {
//                S21ApiMessage msg = msgList.get(i);
//                msgId = msg.getXxMsgid();
//                String[] msgPrms = msg.getXxMsgPrmArray();
//                if (bizMsg.getScreenAplID().endsWith("Delete")) {
//                    String wkMsg = S21MessageFunc.clspGetMessage(msgId, msgPrms);
//                    bizMsg.setMessageInfo("NSAM0673W", new String[] {"NWZC1570 Pricing API", wkMsg });
//                } else {
//                    bizMsg.setMessageInfo(msgId, msgPrms);
//                }
//                if (msgId.endsWith("E")) {
//                    return;
//                }
//            }
//        }
//        for (int j = 0; j < pMsg.NWZC157002PMsg.getValidCount(); j++) {
//            NWZC157002PMsg p2Msg = pMsg.NWZC157002PMsg.no(j);
//            if (S21ApiUtil.isXxMsgId(p2Msg)) {
//                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(p2Msg);
//                for (int i = 0; i < msgList.size(); i++) {
//                    S21ApiMessage msg = msgList.get(i);
//                    msgId = msg.getXxMsgid();
//                    String[] msgPrms = msg.getXxMsgPrmArray();
//                    if (bizMsg.getScreenAplID().endsWith("Delete")) {
//                        String wkMsg = S21MessageFunc.clspGetMessage(msgId, msgPrms);
//                        bizMsg.setMessageInfo("NSAM0673W", new String[] {"NWZC1570 Pricing API", wkMsg });
//                    } else {
//                        bizMsg.setMessageInfo(msgId, msgPrms);
//                    }
//                    if (msgId.endsWith("E")) {
//                        return;
//                    }
//                }
//            }
//        }
//        // Add Start 2017/10/18 QC#21860
//        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
//        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
//        // Add End 2017/10/18 QC#21860
//        for (int j = 0; j < pMsg.NWZC157004PMsg.getValidCount(); j++) {
//            NWZC157004PMsg p4Msg = pMsg.NWZC157004PMsg.no(j);
//            if (S21ApiUtil.isXxMsgId(p4Msg)) {
//                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(p4Msg);
//                for (int i = 0; i < msgList.size(); i++) {
//                    S21ApiMessage msg = msgList.get(i);
//                    msgId = msg.getXxMsgid();
//                    String[] msgPrms = msg.getXxMsgPrmArray();
//                    if (bizMsg.getScreenAplID().endsWith("Delete")) {
//                        String wkMsg = S21MessageFunc.clspGetMessage(msgId, msgPrms);
//                        bizMsg.setMessageInfo("NSAM0673W", new String[] {"NWZC1570 Pricing API", wkMsg });
//                    } else {
//                        bizMsg.setMessageInfo(msgId, msgPrms);
//                    }
//                    if (msgId.endsWith("E")) {
//                        return;
//                    }
//                }
//            }
//
//            NSAL1330_BCMsg bBizMsg = bizMsg.B.no(j);
//            // Mod Start 2017/10/18 QC#21860
////            ZYPEZDItemValueSetter.setValue(bBizMsg.addlBasePrcDealAmt_B, p4Msg.xxTotBaseAmt);
//            // START 2017/10/24 K.Kojima [QC#21810,MOD]
//            // ZYPEZDItemValueSetter.setValue(bBizMsg.addlBasePrcDealAmt_B, round(p4Msg.xxTotBaseAmt.getValue(), scale));
//            // START 2018/03/13 M.Naito [QC#23378, MOD]
////            if (!ZYPCommonFunc.hasValue(bBizMsg.dsContrAddlChrgPk_B)) {
//              if (!ZYPCommonFunc.hasValue(bBizMsg.addlBasePrcDealAmt_B)) {
//             // END 2018/03/13 M.Naito [QC#23378, MOD]
//                ZYPEZDItemValueSetter.setValue(bBizMsg.addlBasePrcDealAmt_B, round(p4Msg.xxTotBaseAmt.getValue(), scale));
//            }
//            // END 2017/10/24 K.Kojima [QC#21810,MOD]
//            // Mod End 2017/10/18 QC#21860
//        }
        // Mod End 2018/12/11 QC#29423

    }

    // Mod Start 2018/12/11 QC#29423
//    private static NWZC157001PMsg getPrcingAPiPMsgForRentalPrc(NSAL1330CMsg bizMsg) {
    private static NWZC157001PMsg getPrcingAPiPMsgForRentalPrc(NSAL1330CMsg bizMsg, int index) {
        NWZC157001PMsg prcApiMsg = new NWZC157001PMsg();

        ZYPEZDItemValueSetter.setValue(prcApiMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.xxModeCd, NWZC157001.GET_ORDER_ALL);

        String lineBizTpCd = getLineBizTpCd(bizMsg);

        ZYPEZDItemValueSetter.setValue(prcApiMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.prcBaseDt, bizMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.lineBizTpCd, lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.dsAcctNum, bizMsg.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.taxCalcFlg, bizMsg.billWithEquipFlg);

        ZYPEZDItemValueSetter.setValue(prcApiMsg.xxRqstFlg_RN, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.prcTermAot, bizMsg.termMthAot);

        NWZC157002PMsg prcApi2Msg = prcApiMsg.NWZC157002PMsg.no(0);
        NSAL1330_BCMsg bBizMsg = bizMsg.B.no(index);

        CPO_DTLTMsg cpoDtlTMsg //
        = getCpoDtl(bizMsg, bBizMsg.cpoDtlLineNum_B.getValue(), bBizMsg.cpoDtlLineSubNum_B.getValue());

        setApiDataB(prcApiMsg, bizMsg, 0);

        ZYPEZDItemValueSetter.setValue(prcApi2Msg.trxLineNum, bBizMsg.cpoDtlLineNum_B);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.trxLineSubNum, bBizMsg.cpoDtlLineSubNum_B);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.prcCatgCd, bBizMsg.addlBasePrcCatgCd_B);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.prcListEquipConfigNum, bBizMsg.prcListEquipConfigNum_B);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.ccyCd, cpoDtlTMsg.ccyCd);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.mdseCd, bBizMsg.mdseCd_B);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.ordQty, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.invQty, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(prcApi2Msg.pkgUomCd, "EA");
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.ordCustUomQty, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);

        prcApiMsg.NWZC157002PMsg.setValidCount(1);

//        BigDecimal wkNum = bizMsg.xxNum_A.getValue();
//        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//            NWZC157002PMsg prcApi2Msg = prcApiMsg.NWZC157002PMsg.no(i);
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_A, new BigDecimal(i));
//            NSAL1330_BCMsg bBizMsg = bizMsg.B.no(i);
//
//            CPO_DTLTMsg cpoDtlTMsg //
//            = getCpoDtl(bizMsg, bBizMsg.cpoDtlLineNum_B.getValue(), bBizMsg.cpoDtlLineSubNum_B.getValue());
//
//            setApiDataB(prcApiMsg, bizMsg, i);
//            //            ZYPEZDItemValueSetter.setValue(prcApi2Msg.billToCustCd, prcApiMsg.NWZC157002PMsg.no(0).billToCustCd);
//            //            ZYPEZDItemValueSetter.setValue(prcApi2Msg.shipToCustCd, prcApiMsg.NWZC157002PMsg.no(0).shipToCustCd);
//            //            ZYPEZDItemValueSetter.setValue(prcApi2Msg.dsAcctNum_SH, prcApiMsg.NWZC157002PMsg.no(0).dsAcctNum_SH);
//            //            ZYPEZDItemValueSetter.setValue(prcApi2Msg.dsAcctNum_BL, prcApiMsg.NWZC157002PMsg.no(0).dsAcctNum_BL);
//            //
//            //            ZYPEZDItemValueSetter.setValue(prcApi2Msg.ctryCd_SH, prcApiMsg.NWZC157002PMsg.no(0).ctryCd_SH);
//            //            ZYPEZDItemValueSetter.setValue(prcApi2Msg.ctyAddr_SH, prcApiMsg.NWZC157002PMsg.no(0).ctyAddr_SH);
//            //            ZYPEZDItemValueSetter.setValue(prcApi2Msg.stCd_SH, prcApiMsg.NWZC157002PMsg.no(0).stCd_SH);
//
//            ZYPEZDItemValueSetter.setValue(prcApi2Msg.trxLineNum, bBizMsg.cpoDtlLineNum_B);
//            ZYPEZDItemValueSetter.setValue(prcApi2Msg.trxLineSubNum, bBizMsg.cpoDtlLineSubNum_B);
//            ZYPEZDItemValueSetter.setValue(prcApi2Msg.prcCatgCd, bBizMsg.addlBasePrcCatgCd_B);
//            ZYPEZDItemValueSetter.setValue(prcApi2Msg.prcListEquipConfigNum, bBizMsg.prcListEquipConfigNum_B);
//            ZYPEZDItemValueSetter.setValue(prcApi2Msg.ccyCd, cpoDtlTMsg.ccyCd);
//            ZYPEZDItemValueSetter.setValue(prcApi2Msg.mdseCd, bBizMsg.mdseCd_B);
//            ZYPEZDItemValueSetter.setValue(prcApi2Msg.ordQty, BigDecimal.ONE);
//            ZYPEZDItemValueSetter.setValue(prcApi2Msg.invQty, BigDecimal.ZERO);
//
//            ZYPEZDItemValueSetter.setValue(prcApi2Msg.pkgUomCd, "EA");
//            ZYPEZDItemValueSetter.setValue(prcApi2Msg.ordCustUomQty, BigDecimal.ONE);
//            ZYPEZDItemValueSetter.setValue(prcApi2Msg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
//        }
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_A, wkNum);
//
//        prcApiMsg.NWZC157002PMsg.setValidCount(bizMsg.B.getValidCount());

        return prcApiMsg;
    }
    // Mod End 2018/12/11 QC#29423

    /**
     * set AccessoryChrg Close
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean setAcsryChrgCloseForRental(NSAL1330CMsg bizMsg, String glblCmpyCd) {
        boolean isError = false;

        List<String> dplyLineNumRental = new ArrayList<String>();
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).xxLineNum_B)) {
                dplyLineNumRental.add(bizMsg.B.no(i).xxLineNum_B.getValue());
            }
        }
        List<String> dplyLineNumAcsry = new ArrayList<String>();
        for (int i = 0; i < bizMsg.J.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.J.no(i).xxLineNum_J)) {
                dplyLineNumAcsry.add(bizMsg.J.no(i).xxLineNum_J.getValue());
            }
        }
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getInitAcsryForRental(bizMsg, glblCmpyCd, ZYPConstant.FLG_ON_Y);

        if (ssmResult.getQueryResultCount() > 0) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();

            int ixB = bizMsg.B.getValidCount();
            for (int i = 0; i < resList.size(); i++) {
                Map<String, Object> resultMap = resList.get(i);
                String rentalFlg = (String) resultMap.get("RENTAL_FLG");
                if (!ZYPConstant.FLG_ON_Y.equals(rentalFlg)) {
                    continue;
                }
                String lineNo = (String) resultMap.get(DPLY_LINE_NUM);
                if (dplyLineNumRental.contains(lineNo)) {
                    continue;
                }

                String mdseCd = (String) resultMap.get(MDSE_CD);
                String mdseNm = (String) resultMap.get(MDSE_DESC_SHORT_TXT);

                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).xxLineNum_B, lineNo);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).mdseCd_B, mdseCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).mdseDescShortTxt_B, mdseNm);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).cpoDtlLineNum_B, (String) resultMap.get(CPO_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).cpoDtlLineSubNum_B, (String) resultMap.get(CPO_DTL_LINE_SUB_NUM));

                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).prcCatgNm_B, (String) resultMap.get(PRC_CATG_NM));
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).prcListEquipConfigNum_B, (BigDecimal) resultMap.get(PRC_LIST_EQUIP_CONFIG_NUM));
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ixB).prcListEquipConfigNm_B, (String) resultMap.get(PRC_LIST_EQUIP_CONFIG_NM));

                bizMsg.xxPopPrm_P1.clear();
                ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_A, BigDecimal.valueOf(ixB));
                getPeriodicServicePrice(bizMsg, glblCmpyCd, PRC_CTX_TP.SALES_PRICING);

                bizMsg.B.no(ixB).addlBasePrcDealAmt_B.setErrorInfo(1, NSAM0663E);
                bizMsg.B.no(ixB).prcCatgNm_B.setErrorInfo(1, NSAM0663E);
                bizMsg.setMessageInfo(NSAM0663E);
                isError = true;
                ixB++;
            }
            bizMsg.B.setValidCount(ixB);

            int ixJ = bizMsg.J.getValidCount();
            for (int i = 0; i < resList.size(); i++) {
                Map<String, Object> resultMap = resList.get(i);
                String acsryFlg = (String) resultMap.get("ACSRY_FLG");
                if (!ZYPConstant.FLG_ON_Y.equals(acsryFlg)) {
                    continue;
                }
                String lineNo = (String) resultMap.get(DPLY_LINE_NUM);
                if (dplyLineNumAcsry.contains(lineNo)) {
                    continue;
                }

                String mdseCd = (String) resultMap.get(MDSE_CD);
                String mdseNm = (String) resultMap.get(MDSE_DESC_SHORT_TXT);

                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ixJ).xxLineNum_J, lineNo);
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ixJ).mdseCd_J, mdseCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ixJ).mdseDescShortTxt_J, mdseNm);
                ZYPEZDItemValueSetter.setValue(//
                        bizMsg.J.no(ixJ).cpoDtlLineNum_J, (String) resultMap.get(CPO_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(//
                        bizMsg.J.no(ixJ).cpoDtlLineSubNum_J, (String) resultMap.get(CPO_DTL_LINE_SUB_NUM));

                String prcCatgNm = "";
                String prcCatgCd = "";
                if (ZYPCommonFunc.hasValue(bizMsg.J.no(0).prcCatgNm_J)) {
                    prcCatgNm = bizMsg.J.no(0).prcCatgNm_J.getValue();
                    prcCatgCd = bizMsg.J.no(0).addlBasePrcCatgCd_J.getValue();
                } else if (ZYPCommonFunc.hasValue(bizMsg.prcCatgNm_HJ)) {
                    prcCatgNm = bizMsg.prcCatgNm_HJ.getValue();
                    prcCatgCd = bizMsg.prcCatgCd_HJ.getValue();
                } else {
                    prcCatgNm = bizMsg.A.no(0).prcCatgNm_A.getValue();
                    prcCatgCd = bizMsg.A.no(0).prcCatgCd_A.getValue();
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ixJ).prcCatgNm_J, prcCatgNm);
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ixJ).addlBasePrcCatgCd_J, prcCatgCd);

                ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P1, POP_UP_COVERED_ITEM);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_A, BigDecimal.valueOf(ixJ));
                getPeriodicServicePrice(bizMsg, glblCmpyCd, null);

                bizMsg.J.no(ixJ).addlBasePrcDealAmt_J.setErrorInfo(1, NSAM0663E);
                bizMsg.J.no(ixJ).prcCatgNm_J.setErrorInfo(1, NSAM0663E);
                bizMsg.setMessageInfo(NSAM0663E);
                isError = true;
                ixJ++;
            }
            bizMsg.J.setValidCount(ixJ);
        }
        return isError;
    }

    //    /**
    //     * setSvcPricingUpdate
    //     * @param bizMsg NSAL1330CMsg
    //     * @param qtyMap Map<BigDecimal, Integer>
    //     * @param modelId BigDecimal
    //     * @param glblCmpyCd String
    //     * @return boolean
    //     */
    //    public static boolean setSvcPricingUpdate(NSAL1330CMsg bizMsg, Map<BigDecimal, Integer> qtyMap, BigDecimal modelId, String glblCmpyCd) {
    //
    //        int aLine = bizMsg.A.getValidCount();
    //        int zLine = bizMsg.Z.getValidCount();
    //
    //        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getUpdateSvcPricingHeader(bizMsg, modelId, glblCmpyCd);
    //
    //        if (ssmResult.getQueryResultCount() > 0) {
    //            @SuppressWarnings("unchecked")
    //            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
    //            Map<String, Object> resultMap = resList.get(0);
    //
    //            String modelNm = (String) resultMap.get(T_MDL_NM);
    //            String priceList = (String) resultMap.get(PRC_LIST_DPLY_NM);
    //            String priceListCd = (String) resultMap.get(PRC_LIST_TP_CD);
    //            String priceTierCd = (String) resultMap.get(PRC_TIER_SVC_OFFER_CD);
    //            BigDecimal prcMtrPkgPk = (BigDecimal) resultMap.get(PRC_MTR_PKG_PK);
    //            BigDecimal periodicBase = (BigDecimal) resultMap.get(BASE_PRC_DEAL_AMT);
    //
    //            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aLine).mdlId_A, modelId);
    //            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aLine).t_MdlNm_A, modelNm);
    //            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aLine).xxTotQtyCnt_A, new BigDecimal(qtyMap.get(modelId)));
    //            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aLine).prcCatgNm_A, priceList);
    //            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aLine).prcCatgCd_A, priceListCd);
    //            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aLine).prcTierSvcOfferCd_A, priceTierCd);
    //            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aLine).prcTierSvcOfferCd_AB, priceTierCd);
    //            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aLine).prcMtrPkgPk_A, prcMtrPkgPk);
    //            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aLine).xxTotPrcAmt_PB, periodicBase);
    //            ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_A, new BigDecimal(aLine));
    //
    //            bizMsg.A.setValidCount(aLine + 1);
    //
    //            NSAL1330CommonLogic.createPrcMtrPkgPulldown(bizMsg, glblCmpyCd);
    //
    //            ssmResult = NSAL1330Query.getInstance().getUpdateSvcPricing(bizMsg, modelId, glblCmpyCd);
    //            if (ssmResult.getQueryResultCount() > 0) {
    //                @SuppressWarnings("unchecked")
    //                List<Map<String, Object>> resList2 = (List<Map<String, Object>>) ssmResult.getResultObject();
    //
    //                for (Map<String, Object> resMap : resList2) {
    //
    //                    String usageItem = (String) resMap.get(USG_MDSE_CD);
    //                    String usageItemNm = (String) resMap.get("USG_MDSE_NM");
    //                    String hardCounterNm = (String) resMap.get(MTR_LB_NM_REG);
    //                    String hardCounterCd = (String) resMap.get("MTR_LB_CD_REG");
    //                    String priceListBandDescTxt = (String) resMap.get(PRC_LIST_BAND_DESC_TXT);
    //                    String priceBookMdseCd = (String) resMap.get(PRC_BOOK_MDSE_CD);
    //                    String bllgCounterNm = (String) resMap.get(MTR_LB_NM_BLLG);
    //                    String bllgCounterCd = (String) resMap.get("MTR_LB_CD_BLLG");
    //                    String tierTpCd = (String) resMap.get(PRC_SVC_TIER_TP_CD);
    //                    BigDecimal rangeFr = (BigDecimal) resMap.get(RANGE_FROM);
    //                    BigDecimal rangeTo = (BigDecimal) resMap.get(RANGE_TO);
    //                    BigDecimal excessPerImgChrg = (BigDecimal) resMap.get(XS_MTR_AMT_RATE);
    //                    BigDecimal multiplier = (BigDecimal) resMap.get(CONTR_MTR_MULT_RATE);
    //
    //                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).t_MdlNm_Z, modelNm);
    //                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).mdlId_Z, modelId);
    //                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).prcCatgCd_Z, priceListCd);
    //                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).prcMtrPkgPk_Z, prcMtrPkgPk);
    //
    //                    //                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).xxScrEdtTpCd_Z, "Y"); // @@@ if has Tier then "Y".
    //                    // Service Pricing table - Parent
    //                    //                    if (ZYPCommonFunc.hasValue(usageItem)) {
    //                    if (!ZYPCommonFunc.hasValue(hardCounterNm) && !ZYPCommonFunc.hasValue(tierTpCd)) {
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).xxFlgNm_Z, XX_FLG_PARENT);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).prcListBandDescTxt_Z, priceListBandDescTxt);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).prcBookMdseCd_Z, priceBookMdseCd);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).bllgMtrLbCd_Z, bllgCounterCd);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).mtrLbDescTxt_ZB, bllgCounterNm);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).usgMdseCd_Z, usageItem);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).mdseDescShortTxt_Z, usageItemNm);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).minCopyVolCnt_Z, rangeFr);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).minCopyVolCnt_BK, rangeFr);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).maxCopyVolCnt_Z, rangeTo);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).maxCopyVolCnt_BK, rangeTo);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).xsMtrAmtRate_Z, excessPerImgChrg);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).xsMtrAmtRate_BK, excessPerImgChrg);
    //                        zLine++;
    //
    //                        // Service Pricing table - last
    //                    } else if (ZYPCommonFunc.hasValue(hardCounterCd) && ZYPCommonFunc.hasValue(multiplier)) {
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).xxFlgNm_Z, XX_FLG_HARD);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).prcListBandDescTxt_Z, priceListBandDescTxt);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).prcBookMdseCd_Z, priceBookMdseCd);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).bllgMtrLbCd_Z, bllgCounterCd);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).mtrLbDescTxt_ZB, bllgCounterNm);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).regMtrLbCd_Z, hardCounterCd);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).mtrLbDescTxt_Z, hardCounterNm);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).contrMtrMultRate_Z, multiplier);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).contrMtrMultRate_BK, multiplier);
    //                        zLine++;
    //                        // Service Pricing - Tier
    //                    } else {
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).xxFlgNm_Z, XX_FLG_TIER);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).prcListBandDescTxt_Z, priceListBandDescTxt);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).prcBookMdseCd_Z, priceBookMdseCd);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).bllgMtrLbCd_Z, bllgCounterCd);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).mtrLbDescTxt_ZB, bllgCounterNm);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).prcSvcTierTpCd_Z, tierTpCd);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).minCopyVolCnt_Z, rangeFr);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).minCopyVolCnt_BK, rangeFr);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).maxCopyVolCnt_Z, rangeTo);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).maxCopyVolCnt_BK, rangeTo);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).xsMtrAmtRate_Z, excessPerImgChrg);
    //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zLine).xsMtrAmtRate_BK, excessPerImgChrg);
    //                        zLine++;
    //                    }
    //                }
    //                bizMsg.Z.setValidCount(zLine);
    //            }
    //        }
    //        return true;
    //    }

    /**
     * setAcsryChrgUpdate
     * @param bizMsg NSAL1330CMsg
     * @param index int
     * @param glblCmpyCd String
     * @param target String J / B
     * @return boolean
     */
    public static boolean setAcsryChrgUpdate(NSAL1330CMsg bizMsg, int index, String glblCmpyCd, String target) {

        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getUpdateAcsry(bizMsg, index, glblCmpyCd, target);

        if (ssmResult.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();

            // String priceList = (String) resultMap.get(PRC_LIST_DPLY_NM);
            // String priceListCd = (String) resultMap.get(ADDL_BASE_PRC_CATG_CD);
            // BigDecimal svcPrice = (BigDecimal) resultMap.get(ADDL_BASE_PRC_DEAL_AMT);
            // String lineNo = (String) resultMap.get(CPO_DTL_LINE_NUM);
            // String lineSubNo = (String) resultMap.get(CPO_DTL_LINE_SUB_NUM);
            //            BigDecimal priceConfNum = (BigDecimal) resultMap.get(PRC_LIST_EQUIP_CONFIG_NUM);
            String mdseCd = (String) resultMap.get(MDSE_CD);
            String mdseNm = (String) resultMap.get(MDSE_DESC_SHORT_TXT);
            String dplyLineNum = (String) resultMap.get("DPLY_LINE_NUM");

            if ("B".equals(target)) { // Rental Equip Accessory Charge
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).mdseCd_B, mdseCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).mdseDescShortTxt_B, mdseNm);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).xxLineNum_B, dplyLineNum);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).shellLineNum_B, bizMsg.shellLineNum);
                ZYPEZDItemValueSetter.setValue(//
                        bizMsg.B.no(index).prcListEquipConfigNum_B, (BigDecimal) resultMap.get("PRC_LIST_EQUIP_CONFIG_NUM"));
                ZYPEZDItemValueSetter.setValue(//
                        bizMsg.B.no(index).prcListEquipConfigNm_B, (String) resultMap.get("PRC_LIST_EQUIP_CONFIG_NM"));
            } else { // Accessory Charge
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(index).mdseCd_J, mdseCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(index).mdseDescShortTxt_J, mdseNm);
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(index).xxLineNum_J, dplyLineNum);
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(index).shellLineNum_J, bizMsg.shellLineNum);
            }
            //            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).mdseCd_B, mdseCd);
            //            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).mdseDescShortTxt_B, mdseNm);
            //            //            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).prcListEquipConfigNum_B, priceConfNum);
            //            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).xxLineNum_B, dplyLineNum);
            // ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).prcListDplyNm_B, priceList);
            // ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).addlBasePrcCatgCd_B, priceListCd);
            // ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).addlBasePrcDealAmt_B, svcPrice);
            // // set BackUp
            // ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).addlBasePrcDealAmt_BB, svcPrice);

        }
        return true;
    }

    // QC#28398
//    /**
//     * setAddChrgUpdate
//     * @param bizMsg NSAL1330CMsg
//     * @param index int
//     * @param glblCmpyCd String
//     * @return boolean
//     */
//    public static boolean setAddChrgUpdate(NSAL1330CMsg bizMsg, int index, String glblCmpyCd) {
//
//        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getUpdateAddChrg(bizMsg, index, glblCmpyCd);
//        if (ssmResult.isCodeNormal()) {
//            @SuppressWarnings("unchecked")
//            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
//
//            // String priceList = (String) resultMap.get(PRC_LIST_DPLY_NM);
//            // String priceListCd = (String) resultMap.get(ADDL_CHRG_PRC_CATG_CD);
//            // ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_C, priceList);
//            // ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_C, priceListCd);
//
//            // String lineNo = (String) resultMap.get(CPO_DTL_LINE_NUM);
//            // String lineSubNo = (String) resultMap.get(CPO_DTL_LINE_SUB_NUM);
//            String dplyLineNum = (String) resultMap.get("DPLY_LINE_NUM");
//            String unitMdseCd = (String) resultMap.get(MDSE_CD);
//            String unitMdseNm = (String) resultMap.get(UNIT_MDSE);
//            // BigDecimal svcPrice = (BigDecimal) resultMap.get(ADDL_CHRG_PRC_DEAL_AMT);
//
//            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).xxLineNum_C, dplyLineNum);
//            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).mdseCd_CU, unitMdseCd);
//            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).mdseDescShortTxt_CU, unitMdseNm);
//            // ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).addlChrgPrcDealAmt_C, svcPrice);
//            // ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).addlChrgPrcDealAmt_CB, svcPrice);
//        }
//        return true;
//    }

    //    /**
    //     * get FloorPrcList
    //     * @param bizMsg NSAL1330CMsg
    //     * @param glblCmpyCd String
    //     * @return if error exists then return true.
    //     */
    //    public static boolean getFloorPrcList(NSAL1330CMsg bizMsg, String glblCmpyCd) {
    //        NWZC157001PMsg pMsg = new NWZC157001PMsg();
    //        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
    //        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
    //        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, bizMsg.prcBaseDt);
    //        ZYPEZDItemValueSetter.setValue(pMsg.prcCtxTpCd, PRC_CTX_TP.FLOOR_PRICE);
    //        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, bizMsg.sellToCustCd);
    //
    //        String linBizTpCd = getLineBizTpCd(bizMsg, glblCmpyCd);
    //
    //        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, linBizTpCd);
    //
    //        new NWZC157001().execute(pMsg, ONBATCH_TYPE.ONLINE);
    //
    //        if (S21ApiUtil.isXxMsgId(pMsg)) {
    //            bizMsg.setMessageInfo(//
    //                    pMsg.xxMsgIdList.no(0).xxMsgId.getValue() //
    //                    , new String[] {pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() //
    //                            , pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue() //
    //                            , pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_2.getValue() } //
    //                    );
    //            return true;
    //        }
    //        String flPrcCatgCd = pMsg.xxPrcList.no(0).prcCatgCd.getValue();
    //        if (!ZYPCommonFunc.hasValue(bizMsg.maintFlPrcCatgCd)) {
    //            ZYPEZDItemValueSetter.setValue(bizMsg.maintFlPrcCatgCd, flPrcCatgCd);
    //        }
    //
    //        return false;
    //    }

    /**
     * @param bizMsg
     * @param glblCmpyCd
     * @return
     */
    private static String getLineBizTpCd(NSAL1330CMsg bizMsg) {
        String lineBizTpCd = "";

        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getLineBizTpCd(bizMsg);
        if (ssmResult.getQueryResultCount() > 0) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();

            lineBizTpCd = (String) resList.get(0).get("LINE_BIZ_TP_CD");
        }
        return lineBizTpCd;
    }

    /**
     * @param bizMsg
     * @param glblCmpyCd
     * @return
     */
    private static CPO_DTLTMsg getCpoDtl(NSAL1330CMsg bizMsg, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        CPO_DTLTMsg cpoDtlTmsg = new CPO_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.cpoOrdNum, bizMsg.xxScrItem50Txt);
        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.cpoDtlLineNum, cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.cpoDtlLineSubNum, cpoDtlLineSubNum);

        cpoDtlTmsg = (CPO_DTLTMsg) EZDTBLAccessor.findByKey(cpoDtlTmsg);
        if (cpoDtlTmsg == null) {
            return new CPO_DTLTMsg();
        }

        return cpoDtlTmsg;
    }

    /**
     * @param bizMsg
     * @param glblCmpyCd
     * @return
     */
    private static String getCcyCdAcsyChrg(NSAL1330CMsg bizMsg) {
        int index = bizMsg.xxNum_A.getValueInt();

        CPO_DTLTMsg cpoDtlTmsg = new CPO_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.cpoOrdNum, bizMsg.xxScrItem50Txt);
        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.cpoOrdNum, bizMsg.refCpoOrdNum);
        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.cpoDtlLineNum, bizMsg.B.no(index).cpoDtlLineNum_B);
        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.cpoDtlLineSubNum, bizMsg.B.no(index).cpoDtlLineSubNum_B);

        cpoDtlTmsg = (CPO_DTLTMsg) EZDTBLAccessor.findByKey(cpoDtlTmsg);
        if (cpoDtlTmsg == null) {
            return null;
        }

        return cpoDtlTmsg.ccyCd.getValue();
    }

    /**
     * @param bizMsg
     * @param glblCmpyCd
     * @return
     */
    private static String getCcyCdAcsyChrg(NSAL1330CMsg bizMsg, int index, String glblCmpyCd) {

        CPO_DTLTMsg cpoDtlTmsg = new CPO_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.cpoOrdNum, bizMsg.xxScrItem50Txt);
        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.cpoOrdNum, bizMsg.refCpoOrdNum);
        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.cpoDtlLineNum, bizMsg.B.no(index).cpoDtlLineNum_B);
        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.cpoDtlLineSubNum, bizMsg.B.no(index).cpoDtlLineSubNum_B);

        cpoDtlTmsg = (CPO_DTLTMsg) EZDTBLAccessor.findByKey(cpoDtlTmsg);
        if (cpoDtlTmsg == null) {
            return null;
        }

        return cpoDtlTmsg.ccyCd.getValue();
    }

    /**
     * @param bizMsg
     * @param glblCmpyCd
     * @return
     */
    private static String getCcyCdAddChrg(NSAL1330CMsg bizMsg, String glblCmpyCd) {
        int index = bizMsg.xxNum_A.getValueInt();

        CPO_DTLTMsg cpoDtlTmsg = new CPO_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.cpoOrdNum, bizMsg.refCpoOrdNum);
        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.cpoDtlLineNum, bizMsg.C.no(index).cpoDtlLineNum_C);
        ZYPEZDItemValueSetter.setValue(cpoDtlTmsg.cpoDtlLineSubNum, bizMsg.C.no(index).cpoDtlLineSubNum_C);

        cpoDtlTmsg = (CPO_DTLTMsg) EZDTBLAccessor.findByKey(cpoDtlTmsg);
        if (cpoDtlTmsg == null) {
            return null;
        }

        return cpoDtlTmsg.ccyCd.getValue();
    }

    /**
     * get ServicePriceAccossory
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean getSvcPrcAccs(NSAL1330CMsg bizMsg, String glblCmpyCd) {

        String lineBizTpCd = getLineBizTpCd(bizMsg);

        NWZC157001PMsg pMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC157001.GET_ORDER_ALL);
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, bizMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(pMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.taxCalcFlg, bizMsg.billWithEquipFlg);

        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, bizMsg.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);

        NWZC157002PMsgArray p2MsgArray = pMsg.NWZC157002PMsg;

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWZC157002PMsg p2Msg = new NWZC157002PMsg();

            String ccyCd = getCcyCdAcsyChrg(bizMsg, i, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(p2Msg.pkgUomCd, "EA");
            ZYPEZDItemValueSetter.setValue(p2Msg.ordQty, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(p2Msg.invQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(p2Msg.ordCustUomQty, BigDecimal.ONE);

            ZYPEZDItemValueSetter.setValue(p2Msg.trxLineNum, bizMsg.B.no(i).cpoDtlLineNum_B);
            ZYPEZDItemValueSetter.setValue(p2Msg.trxLineSubNum, bizMsg.B.no(i).cpoDtlLineSubNum_B);
            ZYPEZDItemValueSetter.setValue(p2Msg.prcCatgCd, bizMsg.B.no(i).addlBasePrcCatgCd_B);
            ZYPEZDItemValueSetter.setValue(p2Msg.ccyCd, ccyCd);

            if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).prcListEquipConfigNum_B) //
                    && bizMsg.B.no(i).prcListEquipConfigNum_B.getValue().compareTo(BigDecimal.ZERO) == 0) {
                p2Msg.prcListEquipConfigNum.clear();
            } else {
                ZYPEZDItemValueSetter.setValue(p2Msg.prcListEquipConfigNum, bizMsg.B.no(i).prcListEquipConfigNum_B);
            }
            ZYPEZDItemValueSetter.setValue(p2Msg.mdseCd, bizMsg.B.no(i).mdseCd_B);
            ZYPEZDItemValueSetter.setValue(p2Msg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
            EZDMsg.copy(p2Msg, null, p2MsgArray.no(i), null);
            setApiDataBList(pMsg, bizMsg, i, glblCmpyCd);
        }
        pMsg.NWZC157002PMsg.setValidCount(bizMsg.B.getValidCount());

        new NWZC157001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (S21ApiMessage msg : msgList) {
                if (!msg.getXxMsgid().endsWith("E")) {
                    continue;
                }
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                return false;
            }
        }
        for (int i = 0; i < pMsg.NWZC157002PMsg.getValidCount(); i++) {
            if (S21ApiUtil.isXxMsgId(pMsg.NWZC157002PMsg.no(i))) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg.NWZC157002PMsg.no(i));
                for (int j = 0; j < msgList.size(); j++) {
                    S21ApiMessage msg = msgList.get(j);
                    String msgId = msg.getXxMsgid();
                    if (!msgId.endsWith("E")) {
                        continue;
                    }
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);
                    return false;
                }
            }
        }

        List<BigDecimal> price = new ArrayList<BigDecimal>();
        for (int i = 0; i < pMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg p2Msg = pMsg.NWZC157002PMsg.no(i);
            for (int j = 0; j < p2Msg.NWZC157003PMsg.getValidCount(); j++) {
                NWZC157003PMsg p3Msg = p2Msg.NWZC157003PMsg.no(j);
                if (p3Msg.trxLineNum.getValue().equals(p2Msg.trxLineNum.getValue()) //
                        && p3Msg.trxLineSubNum.getValue().equals(p2Msg.trxLineSubNum.getValue())) {
                    price.add(p3Msg.unitPrcAmt.getValue());
                    break;
                }
            }
        }
        // Add Start 2017/10/18 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/18 QC#21860
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (price.size() > i) {
                // Mod Start 2017/10/18 QC#21860
//                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).addlBasePrcDealAmt_B, price.get(i));
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).addlBasePrcDealAmt_B, round(price.get(i), scale));
                // Mod End 2017/10/18 QC#21860
            }
        }

        return true;
    }

    //    /**
    //     * reset
    //     * @param bizMsg NSAL1330CMsg
    //     */
    //    public static void reset(NSAL1330CMsg bizMsg) {
    //
    //        int zIndex = bizMsg.xxNum_Z.getValueInt();
    //        String zModelNm = bizMsg.Z.no(zIndex).mdlDescTxt_Z.getValue();
    //        int nextRow = zIndex;
    //        for (; nextRow < bizMsg.Z.getValidCount(); nextRow++) {
    //            String mdlNm = bizMsg.A.no(nextRow).mdlDescTxt_A.getValue();
    //            if (!mdlNm.equals(zModelNm)) {
    //                break;
    //            }
    //        }
    //
    //        int tierLeng = nextRow - zIndex - 2;
    //        List<Integer> deleteList = new ArrayList<Integer>();
    //
    //        for (int i = 0; i < tierLeng; i++) {
    //            deleteList.add(i + zIndex + 1);
    //        }
    //        ZYPTableUtil.deleteRows(bizMsg.Z, deleteList);
    //    }
    //
    //    /**
    //     * insertTierRow
    //     * @param bizMsg NSAL1330CMsg
    //     * @param rowNum int
    //     * @param rowLength int
    //     */
    //    public static void insertTierRow(NSAL1330CMsg bizMsg, int rowNum, int rowLength) {
    //
    //        if (bizMsg.Z.getValidCount() + rowLength > bizMsg.Z.length()) {
    //            return;
    //        }
    //
    //        for (int i = bizMsg.Z.getValidCount() + rowLength; i < rowNum + rowLength; i--) {
    //            EZDMsg.copy(bizMsg.Z.no(i - rowLength), null, bizMsg.Z.no(i), null);
    //
    //            if (i - rowLength <= rowNum + rowLength) {
    //                bizMsg.Z.no(i - rowLength).clear();
    //            }
    //        }
    //        bizMsg.Z.setValidCount(bizMsg.Z.getValidCount() + rowLength);
    //    }
    //
    //    /**
    //     * zCopyHeader
    //     * @param bizMsg NSAL1330CMsg
    //     * @param fromRowNum int
    //     * @param toRowNum int
    //     */
    //    public static void zCopyHeader(NSAL1330CMsg bizMsg, int fromRowNum, int toRowNum) {
    //
    //        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(toRowNum).mdlId_Z, bizMsg.Z.no(fromRowNum).mdlId_Z);
    //        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(toRowNum).t_MdlNm_Z, bizMsg.Z.no(fromRowNum).t_MdlNm_Z);
    //        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(toRowNum).prcListTpCd_Z, bizMsg.Z.no(fromRowNum).prcListTpCd_Z);
    //        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(toRowNum).prcMtrPkgPk_Z, bizMsg.Z.no(fromRowNum).prcMtrPkgPk_Z);
    //        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(toRowNum).xxFlgNm_Z, XX_FLG_PARENT);
    //    }

    //    /**
    //     * getAccessoryChargePrcConfigPullDown
    //     * @param bizMsg NSAL1330CMsg
    //     * @param index int
    //     * @param glblCmpyCd String
    //     * @return boolean
    //     */
    //    public static boolean getAccessoryChargePrcConfigPullDown(NSAL1330CMsg bizMsg, int index, String glblCmpyCd) {
    //
    //        S21SsmEZDResult ssmResult = null;
    //
    //        ssmResult = NSAL1330Query.getInstance().getPrcConfNameListBox(bizMsg, index, glblCmpyCd);
    //
    //        if (ssmResult.isCodeNotFound()) {
    //            bizMsg.B.no(index).prcListEquipConfigNum_B.clear();
    //            bizMsg.B.no(index).prcListEquipConfigNum_KP.clear();
    //            bizMsg.B.no(index).prcListEquipConfigNm_VW.clear();
    //            //            bizMsg.setMessageInfo(NSAM0646E, new String[] {"Price Configuration PullDown" });
    //            return false;
    //        }
    //        @SuppressWarnings("unchecked")
    //        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
    //        if (resList != null && !resList.isEmpty()) {
    //
    //            for (int j = 0; j < resList.size(); j++) {
    //                Map<String, Object> result = resList.get(j);
    //
    //                BigDecimal confNum = (BigDecimal) result.get("PRC_LIST_EQUIP_CONFIG_NUM");
    //                String confNm = (String) result.get("PRC_LIST_EQUIP_CONFIG_NM");
    //
    //                if (!ZYPCommonFunc.hasValue(confNum)) {
    //                    confNum = BigDecimal.ZERO;
    //                }
    //                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).prcListEquipConfigNum_KP.no(j), confNum);
    //                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).prcListEquipConfigNm_VW.no(j), confNm);
    //
    //            }
    //        }
    //        return true;
    //    }

    /**
     * get PeriodicServicePrice
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @param priceContextTp String
     * @return boolean
     */
    public static boolean getPeriodicServicePrice(NSAL1330CMsg bizMsg, String glblCmpyCd, String priceContextTp) {

        String popupNm = bizMsg.xxPopPrm_P1.getValue();
        // Add Start 2017/10/18 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/18 QC#21860
        if (POP_UP_COVERED_ITEM.equals(popupNm)) {
            int ix = bizMsg.xxNum_A.getValueInt(); // QC#13555
            ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_B, bizMsg.xxNum_A); // QC#13555
            BigDecimal basePrc //
            = NSAL1330CommonLogic.getPeriodicServicePriceForAccesoryCharge(bizMsg);
            if (ZYPCommonFunc.hasValue(basePrc)) {
                // Mod Start 2017/10/18 QC#21860
//                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).addlBasePrcDealAmt_J, basePrc);
//                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).addlBasePrcDealAmt_BJ, basePrc);
//                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).dealPrcListPrcAmt_J, basePrc);
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).addlBasePrcDealAmt_J, round(basePrc, scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).addlBasePrcDealAmt_BJ, round(basePrc, scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).dealPrcListPrcAmt_J, round(basePrc, scale));
                // Mod Start 2017/10/18 QC#21860
            }
            return true;
        }

        NWZC157001PMsg prcApiMsg = new NWZC157001PMsg();

        if (PRC_CTX_TP.SALES_PRICING.equals(priceContextTp)) {

            setPricingAPIMsgRentalEquip(prcApiMsg, bizMsg, glblCmpyCd);

        } else if (PRC_CTX_TP.SPECIAL_CHARGE.equals(priceContextTp)) {
            setPricingAPIMsgAddChargeItem(prcApiMsg, bizMsg, glblCmpyCd);

        } else {
            return false;
        }

        NWZC157001 pricingApi = new NWZC157001();
        pricingApi.execute(prcApiMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(prcApiMsg)) {
            bizMsg.setMessageInfo(//
                    prcApiMsg.xxMsgIdList.no(0).xxMsgId.getValue() //
                    , new String[] {prcApiMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() //
                            , prcApiMsg.xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue() //
                            , prcApiMsg.xxMsgIdList.no(0).xxMsgPrmTxt_2.getValue() } //
                    );
            return false;
        }
        for (int i = 0; i < prcApiMsg.NWZC157002PMsg.getValidCount(); i++) {
            if (S21ApiUtil.isXxMsgId(prcApiMsg.NWZC157002PMsg.no(i))) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiMsg.NWZC157002PMsg.no(i));
                for (int j = 0; j < msgList.size(); j++) {
                    S21ApiMessage msg = msgList.get(j);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);
                    return false;
                }
            }
        }
        List<BigDecimal> price = new ArrayList<BigDecimal>();

        // Rental Equip Accessory set result
        if (PRC_CTX_TP.SALES_PRICING.equals(priceContextTp)) {

            for (int i = 0; i < prcApiMsg.NWZC157002PMsg.getValidCount(); i++) {
                NWZC157002PMsg p2Msg = prcApiMsg.NWZC157002PMsg.no(i);
                for (int j = 0; j < p2Msg.NWZC157003PMsg.getValidCount(); j++) {
                    NWZC157003PMsg p3Msg = p2Msg.NWZC157003PMsg.no(j);
                    if (p3Msg.trxLineNum.getValue().equals(p2Msg.trxLineNum.getValue()) //
                            && p3Msg.trxLineSubNum.getValue().equals(p2Msg.trxLineSubNum.getValue())) {
                        price.add(p3Msg.unitPrcAmt.getValue());
                    }
                }
            }
            int index = bizMsg.xxNum_A.getValueInt();

            if (price.size() == 1) {
                // Mod Start 2017/10/18 QC#21860
//                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).addlBasePrcDealAmt_B, price.get(0));
//                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).addlBasePrcDealAmt_BB, price.get(0));
//                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).dealPrcListPrcAmt_B, price.get(0));
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).addlBasePrcDealAmt_B, round(price.get(0), scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).addlBasePrcDealAmt_BB, round(price.get(0), scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).dealPrcListPrcAmt_B, round(price.get(0), scale));
                // Mod End 2017/10/18 QC#21860
            }

            // Additional service set Result
        } else if (PRC_CTX_TP.SPECIAL_CHARGE.equals(priceContextTp)) {
            for (int i = 0; i < prcApiMsg.NWZC157002PMsg.getValidCount(); i++) {
                NWZC157002PMsg p2Msg = prcApiMsg.NWZC157002PMsg.no(i);
                for (int j = 0; j < p2Msg.NWZC157003PMsg.getValidCount(); j++) {
                    NWZC157003PMsg p3Msg = p2Msg.NWZC157003PMsg.no(j);
                    if (p3Msg.trxLineNum.getValue().equals(p2Msg.trxLineNum.getValue()) && p3Msg.trxLineSubNum.getValue().equals(p2Msg.trxLineSubNum.getValue())) {
                        price.add(p3Msg.unitPrcAmt.getValue());
                    }
                }
            }
            int index = bizMsg.xxNum_A.getValueInt();

            if (price.size() == 1) {
                // Mod Start 2017/10/18 QC#21860
//                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).addlChrgPrcDealAmt_C, price.get(0));
//                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).addlChrgPrcDealAmt_CB, price.get(0));
//                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).dealPrcListPrcAmt_C, price.get(0));
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).addlChrgPrcDealAmt_C, round(price.get(0), scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).addlChrgPrcDealAmt_CB, round(price.get(0), scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).dealPrcListPrcAmt_C, round(price.get(0), scale));
                // Mod End 2017/10/18 QC#21860
            }
        }
        return true;
    }

    /**
     * setPricingAPIMsgAccsryBase
     * @param prcApiMsg NWZC157001PMsg
     * @param bizMsg Business Msg
     * @param glblCmpyCd String
     */
    private static void setPricingAPIMsgRentalEquip(NWZC157001PMsg prcApiMsg, NSAL1330CMsg bizMsg, String glblCmpyCd) {

        String lineBizTpCd = getLineBizTpCd(bizMsg);
        String ccyCd = getCcyCdAcsyChrg(bizMsg);

        int index = bizMsg.xxNum_A.getValueInt();
        ZYPEZDItemValueSetter.setValue(prcApiMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.xxModeCd, NWZC157001.GET_BASE_PRICE);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.prcBaseDt, bizMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.lineBizTpCd, lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.dsAcctNum, bizMsg.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.taxCalcFlg, bizMsg.billWithEquipFlg);

        NWZC157002PMsg prcApi2Msg = prcApiMsg.NWZC157002PMsg.no(0);

        setApiDataB(prcApiMsg, bizMsg);

        ZYPEZDItemValueSetter.setValue(prcApi2Msg.trxLineNum, bizMsg.B.no(index).cpoDtlLineNum_B);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.trxLineSubNum, bizMsg.B.no(index).cpoDtlLineSubNum_B);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.prcCatgCd, bizMsg.B.no(index).addlBasePrcCatgCd_B);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.prcListEquipConfigNum, bizMsg.B.no(index).prcListEquipConfigNum_B);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.ccyCd, ccyCd);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.mdseCd, bizMsg.B.no(index).mdseCd_B);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.ordQty, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.invQty, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(prcApi2Msg.pkgUomCd, "EA");
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.ordCustUomQty, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);

        prcApiMsg.NWZC157002PMsg.setValidCount(1);
    }

    /**
     * setApiData
     * @param prcApiMsg NWZC157001PMsg
     * @param scrnMsg Business Msg
     * @param glblCmpyCd String
     */
    private static void setApiDataB(NWZC157001PMsg prcApiMsg, NSAL1330CMsg bizMsg) {

        String billToCust = "";
        String shipToCust = "";
        String shipToAcct = "";
        String billToAcct = "";
        String shipToCtryCd = "";
        String shipToCtyAddr = "";
        String shipToStCd = "";

        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getApiDataB(bizMsg);
        if (ssmResult.getQueryResultCount() > 0) {
            @SuppressWarnings("unchecked")
            Map<String, Object> resMap = (Map<String, Object>) ssmResult.getResultObject();

            billToCust = (String) resMap.get("BILL_TO_CUST_LOC_CD");
            shipToCust = (String) resMap.get("SHIP_TO_CUST_LOC_CD");
            shipToAcct = (String) resMap.get("SHIP_TO_CUST_ACCT_CD");
            billToAcct = (String) resMap.get("BILL_TO_CUST_ACCT_CD");

            shipToCtryCd = (String) resMap.get("SHIP_TO_CTRY_CD");
            // START 2018/04/24 M.Naito [QC#23378, MOD]
//            shipToStCd = (String) resMap.get("SHIP_TO_CTY_ADDR");
            shipToCtyAddr = (String) resMap.get("SHIP_TO_CTY_ADDR");
            shipToStCd = (String) resMap.get("SHIP_TO_ST_CD");
            // END 2018/04/24 M.Naito [QC#23378, MOD]
        }

        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(0).billToCustCd, billToCust);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(0).shipToCustCd, shipToCust);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(0).dsAcctNum_SH, shipToAcct);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(0).dsAcctNum_BL, billToAcct);

        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(0).ctryCd_SH, shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(0).ctyAddr_SH, shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(0).stCd_SH, shipToStCd);
    }

    /**
     * setApiData
     * @param prcApiMsg NWZC157001PMsg
     * @param scrnMsg Business Msg
     * @param glblCmpyCd String
     */
    private static void setApiDataB(NWZC157001PMsg prcApiMsg, NSAL1330CMsg bizMsg, int ix) {

        String billToCust = "";
        String shipToCust = "";
        String shipToAcct = "";
        String billToAcct = "";
        String shipToCtryCd = "";
        String shipToCtyAddr = "";
        String shipToStCd = "";

        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getApiDataB(bizMsg);
        if (ssmResult.getQueryResultCount() > 0) {
            @SuppressWarnings("unchecked")
            Map<String, Object> resMap = (Map<String, Object>) ssmResult.getResultObject();

            billToCust = (String) resMap.get("BILL_TO_CUST_LOC_CD");
            shipToCust = (String) resMap.get("SHIP_TO_CUST_LOC_CD");
            shipToAcct = (String) resMap.get("SHIP_TO_CUST_ACCT_CD");
            billToAcct = (String) resMap.get("BILL_TO_CUST_ACCT_CD");

            shipToCtryCd = (String) resMap.get("SHIP_TO_CTRY_CD");
            shipToCtyAddr = (String) resMap.get("SHIP_TO_CTY_ADDR");
            shipToStCd = (String) resMap.get("SHIP_TO_ST_CD");
        }

        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(ix).billToCustCd, billToCust);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(ix).shipToCustCd, shipToCust);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(ix).dsAcctNum_SH, shipToAcct);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(ix).dsAcctNum_BL, billToAcct);

        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(ix).ctryCd_SH, shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(ix).ctyAddr_SH, shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(ix).stCd_SH, shipToStCd);
    }

    /**
     * setPricingAPIMsgAccsryBase
     * @param prcApiMsg NWZC157001PMsg
     * @param bizMsg Business Msg
     * @param glblCmpyCd String
     */
    private static void setPricingAPIMsgAddChargeItem(NWZC157001PMsg prcApiMsg, NSAL1330CMsg bizMsg, String glblCmpyCd) {
        int index = bizMsg.xxNum_A.getValueInt();

        String lineBizTpCd = getLineBizTpCd(bizMsg);
        String ccyCd = getCcyCdAddChrg(bizMsg, glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(prcApiMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.xxModeCd, NWZC157001.GET_BASE_PRICE);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.prcBaseDt, bizMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.prcCtxTpCd, PRC_CTX_TP.SPECIAL_CHARGE);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.lineBizTpCd, lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.dsAcctNum, bizMsg.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.taxCalcFlg, bizMsg.billWithEquipFlg);

        NWZC157002PMsg prcApi2Msg = prcApiMsg.NWZC157002PMsg.no(0);

        setApiDataC(prcApiMsg, bizMsg, glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(prcApi2Msg.trxLineNum, bizMsg.C.no(index).cpoDtlLineNum_C);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.trxLineSubNum, bizMsg.C.no(index).cpoDtlLineSubNum_C);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.ccyCd, ccyCd);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.prcCatgCd, bizMsg.prcCatgCd_C);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.mdseCd, bizMsg.C.no(index).mdseCd_CI);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.ordQty, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.invQty, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(prcApi2Msg.pkgUomCd, "EA");
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.ordCustUomQty, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(prcApi2Msg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);

        prcApiMsg.NWZC157002PMsg.setValidCount(1);
    }

    /**
     * setApiData
     * @param prcApiMsg NWZC157001PMsg
     * @param scrnMsg Business Msg
     * @param glblCmpyCd String
     */
    private static void setApiDataC(NWZC157001PMsg prcApiMsg, NSAL1330CMsg bizMsg, String glblCmpyCd) {

        String billToCust = "";
        String shipToCust = "";
        String shipToAcct = "";
        String billToAcct = "";

        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getApiDataC(bizMsg, glblCmpyCd);
        if (ssmResult.getQueryResultCount() > 0) {
            @SuppressWarnings("unchecked")
            Map<String, Object> resMap = (Map<String, Object>) ssmResult.getResultObject();

            billToCust = (String) resMap.get("BILL_TO_CUST_LOC_CD");
            shipToCust = (String) resMap.get("SHIP_TO_CUST_LOC_CD");
            shipToAcct = (String) resMap.get("SHIP_TO_CUST_ACCT_CD");
            billToAcct = (String) resMap.get("BILL_TO_CUST_ACCT_CD");
        }

        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(0).billToCustCd, billToCust);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(0).shipToCustCd, shipToCust);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(0).dsAcctNum_SH, shipToAcct);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(0).dsAcctNum_BL, billToAcct);
    }

    /**
     * setApiData
     * @param prcApiMsg NWZC157001PMsg
     * @param scrnMsg Business Msg
     * @param glblCmpyCd String
     */
    private static void setApiDataBList(NWZC157001PMsg prcApiMsg, NSAL1330CMsg bizMsg, int index, String glblCmpyCd) {

        String billToCust = "";
        String shipToCust = "";
        String shipToAcct = "";
        String billToAcct = "";
        String shipToCtyAddr = "";
        String shipToStCd = "";
        String shipToCtryCd = "";
        String dsOrdLineCatgCd = "";

        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getApiDataBList(bizMsg, index, glblCmpyCd);
        if (ssmResult.getQueryResultCount() > 0) {
            @SuppressWarnings("unchecked")
            Map<String, Object> resMap = (Map<String, Object>) ssmResult.getResultObject();

            billToCust = (String) resMap.get("BILL_TO_CUST_LOC_CD");
            shipToCust = (String) resMap.get("SHIP_TO_CUST_LOC_CD");
            shipToAcct = (String) resMap.get("SHIP_TO_CUST_ACCT_CD");
            billToAcct = (String) resMap.get("BILL_TO_CUST_ACCT_CD");
            shipToCtyAddr = (String) resMap.get("SHIP_TO_CTY_ADDR");
            shipToStCd = (String) resMap.get("SHIP_TO_ST_CD");
            shipToCtryCd = (String) resMap.get("SHIP_TO_CTRY_CD");
            dsOrdLineCatgCd = (String) resMap.get("DS_ORD_LINE_CATG_CD");
        }
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(index).billToCustCd, billToCust);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(index).shipToCustCd, shipToCust);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(index).dsAcctNum_SH, shipToAcct);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(index).dsAcctNum_BL, billToAcct);

        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(index).ctyAddr_SH, shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(index).stCd_SH, shipToStCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(index).ctryCd_SH, shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(prcApiMsg.NWZC157002PMsg.no(index).dsOrdLineCatgCd, dsOrdLineCatgCd);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     */
    public static void getMtrLbFL(NSAL1330CMsg bizMsg) {

        List<BigDecimal> mdlList = new ArrayList<BigDecimal>();
        List<String> mdlNmList = new ArrayList<String>();
        for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
            BigDecimal mdl = bizMsg.I.no(i).mdlId_I.getValue();
            if (!mdlList.contains(mdl)) {
                mdlList.add(mdl);
                mdlNmList.add(bizMsg.I.no(i).t_MdlNm_I.getValue());
            }
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.A.no(0).prcMtrPkgPk_A)) {

            //            int zStartRow = 0;
            List<Integer> delList = new ArrayList<Integer>();
            for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
                //                if (delList.size() == 0) {
                //                    zStartRow = i;
                //                }
                delList.add(i);
            }

            ZYPTableUtil.deleteRows(bizMsg.Z, delList);

            //            bizMsg.Z.setValidCount(bizMsg.Z.getValidCount() + 1);
            //            bizMsg.xxNum_Z.setValue(0);
            //
            //            // set First Line
            //            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).mdlId_Z, bizMsg.A.no(0).mdlId_A);
            //            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).t_MdlNm_Z, bizMsg.A.no(0).t_MdlNm_A);
            //            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).prcCatgCd_Z, bizMsg.A.no(0).prcCatgCd_A);
            //            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).prcMtrPkgPk_Z, bizMsg.A.no(0).prcMtrPkgPk_A);
            //            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).xxFlgNm_Z, XX_FLG_PARENT);
            //            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).xxSmryLineFlg_Z, ZYPConstant.FLG_ON_Y); // [+]
            return;
        }
        // QC#29248
        if (PRC_RATE_TP.ANNUAL.equals(bizMsg.A.no(0).prcRateTpCd_A.getValue())) {
            return;
        }
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getMtrLbFL(bizMsg, mdlList);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NSAM0670W, new String[] {mdlNmList.toString() });
            return;
        }

        S21SsmEZDResult ssmResultWk = NSAL1330Query.getInstance().isInMtrDB(bizMsg, mdlList);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResultWk.getResultObject();
        //        int count = resList.size();
        //        if (count != mdlList.size()) {
        if (resList == null || resList.size() == 0) {
            bizMsg.setMessageInfo(NSAM0657E, new String[] {mdlNmList.toString() });
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList2 = (List<Map<String, Object>>) ssmResult.getResultObject();

        int ixZ = 0;
        for (int j = 0; j < resList2.size(); j++) {

            Map<String, Object> resMap = resList2.get(j);

            if (j == 0 || !resMap.get("BILLING_COUNTER_CD").equals(resList2.get(j - 1).get("BILLING_COUNTER_CD"))) {

                bizMsg.Z.no(ixZ).clear();
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).xxFlgNm_Z, XX_FLG_PARENT);
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).xxSmryLineFlg_Z, ZYPConstant.FLG_ON_Y); // [+]

                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).t_MdlNm_Z, bizMsg.A.no(0).t_MdlNm_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).mdlId_Z, bizMsg.A.no(0).mdlId_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).prcCatgCd_Z, bizMsg.A.no(0).prcCatgCd_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).prcMtrPkgPk_Z, bizMsg.A.no(0).prcMtrPkgPk_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).bllgMtrLbCd_Z, (String) resMap.get("BILLING_COUNTER_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).mtrLbDescTxt_ZB, (String) resMap.get("BILLING_COUNTER_NM"));

                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).mdseDescShortTxt_Z, (String) resMap.get("USAGE_ITEM_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).usgMdseCd_Z, (String) resMap.get("USAGE_ITEM_CD"));
                ixZ++;
            }
            bizMsg.Z.no(ixZ).clear();

            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).xxFlgNm_Z, XX_FLG_HARD);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).t_MdlNm_Z, bizMsg.A.no(0).t_MdlNm_A);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).mdlId_Z, bizMsg.A.no(0).mdlId_A);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).prcCatgCd_Z, bizMsg.A.no(0).prcCatgCd_A);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).prcMtrPkgPk_Z, bizMsg.A.no(0).prcMtrPkgPk_A);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).bllgMtrLbCd_Z, (String) resMap.get("BILLING_COUNTER_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).mtrLbDescTxt_ZB, (String) resMap.get("BILLING_COUNTER_NM"));

            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).mtrLbDescTxt_Z, (String) resMap.get("HARD_COUNTER_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).regMtrLbCd_Z, (String) resMap.get("HARD_COUNTER_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).contrMtrMultRate_Z, (BigDecimal) resMap.get("MTR_MULT_RATE"));
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).contrMtrMultRate_BK, (BigDecimal) resMap.get("MTR_MULT_RATE"));
            ixZ++;
        }
        bizMsg.xxNum_Z.setValue(0);
        bizMsg.Z.setValidCount(ixZ);

        //        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        //            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcTierSvcOfferCd_A)) {
        //                NSAL1330CommonLogic.deriveTierInfoForFleet(bizMsg, i, mdlList, glblCmpyCd);
        //            }
        //        }

    }

    //    public static void setUsgInfoFromGlblMsgToBizMsg(NSAL1330SMsg glblMsg, NSAL1330CMsg bizMsg) {
    //        int ixB = 0;
    //        String xxSmryLineFlg = "";
    //        for (int ixG = 0; ixG < glblMsg.Z.getValidCount(); ixG++) {
    //            NSAL1330_ZSMsg zGlblMsg = glblMsg.Z.no(ixG);
    //            if (XX_FLG_PARENT.equals(zGlblMsg.xxFlgNm_Z.getValue())) {
    //                xxSmryLineFlg = zGlblMsg.xxSmryLineFlg_Z.getValue();
    //                NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(ixB);
    //
    //                EZDMsg.copy(zGlblMsg, null, zBizMsg, null);
    //                ixB++;
    //
    //            } else if (!ZYPConstant.FLG_ON_Y.equals(xxSmryLineFlg)) {
    //                NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(ixB);
    //                EZDMsg.copy(zGlblMsg, null, zBizMsg, null);
    //                ixB++;
    //            }
    //        }
    //        bizMsg.Z.setValidCount(ixB);
    //    }

    //    private static void deriveTierInfoForFleet(NSAL1330CMsg bizMsg, int ixA, List<BigDecimal> mdlList, String glblCmpyCd) {
    //        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
    //            NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(i);
    //            S21SsmEZDResult ssmResult //
    //            = NSAL1330Query.getInstance().getTierInfoForFleet(bizMsg, zBizMsg, mdlList, glblCmpyCd);
    //            if (ssmResult == null || ssmResult.isCodeNotFound()) {
    //                continue;
    //            }
    //            NSAL1330CommonLogic.setTierInfo(bizMsg, ssmResult, i, ixA);
    //            i = i + ssmResult.getQueryResultCount() + 1;
    //        }
    //    }

    /**
     * @param bizMsg NSAL1330SMsg
     * @param glblMsg
     * @param glblCmpyCd glblCmpyCd
     */
    public static void getMtrLb(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {

        int ixA = bizMsg.xxNum_A.getValueInt();

        int zStartRow = bizMsg.Z.getValidCount();
        List<Integer> delList = new ArrayList<Integer>();
        NSAL1330_ACMsg aBizMsg = bizMsg.A.no(ixA);
        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
            if (aBizMsg.mdlId_A.getValue().compareTo(bizMsg.Z.no(i).mdlId_Z.getValue()) == 0) {
                if (delList.size() == 0) {
                    zStartRow = i;
                }
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.Z, delList); // delete exists data

        delList.clear();
        for (int i = 0; i < bizMsg.X.getValidCount(); i++) {
            if (aBizMsg.mdlId_A.getValue().compareTo(bizMsg.X.no(i).mdlId_X.getValue()) == 0) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.X, delList); // delete exists data

        if (!ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_A) //
                || isRateTypeAnnual(getPrcRateTpCd(bizMsg, aBizMsg))) {
            return;
        }

        // START 2017/10/26 [QC#21556, MOD]
        // S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getMtrLb(bizMsg);
        S21SsmEZDResult ssmResult = null;
        if (DS_CONTR_CATG.AGGREGATE.equals(bizMsg.dsContrCatgCd.getValue()) 
                && ZYPCommonFunc.hasValue(bizMsg.dsContrPk_AD) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.addAsryFlg.getValue())) {
            ssmResult = getMtrLbAggAddMachine(bizMsg, aBizMsg);
        } else {
            ssmResult = NSAL1330Query.getInstance().getMtrLb(bizMsg);
        }
        // END   2017/10/26 [QC#21556, MOD]
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NSAM0670W, new String[] {aBizMsg.t_MdlNm_A.getValue() });
            return;
        }

        for (int i = bizMsg.Z.getValidCount() - 1; i > zStartRow - 1; i--) {
            EZDMsg.copy(bizMsg.Z.no(i), null, bizMsg.Z.no(i + 1), null);
        }

        // set First Line
        bizMsg.Z.no(zStartRow).clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).mdlId_Z, aBizMsg.mdlId_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).t_MdlNm_Z, aBizMsg.t_MdlNm_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).prcCatgCd_Z, aBizMsg.prcCatgCd_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).prcMtrPkgPk_Z, aBizMsg.prcMtrPkgPk_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).xxFlgNm_Z, XX_FLG_PARENT);
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).xxSmryLineFlg_Z, ZYPConstant.FLG_ON_Y); // [+]
        //        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).xxScrEdtTpCd_Z, "Y"); // @@@ if has Tier then "Y".

        bizMsg.Z.setValidCount(bizMsg.Z.getValidCount() + 1);
        bizMsg.xxNum_Z.setValue(zStartRow);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();

        int zRowCount = 0;
        zStartRow = 0;
        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
            if (aBizMsg.mdlId_A.getValue().compareTo(bizMsg.Z.no(i).mdlId_Z.getValue()) == 0) {
                if (zRowCount == 0) {
                    zStartRow = i;
                }
                zRowCount++;
            }
        }
        bizMsg.xxNum_Z.setValue(zStartRow);

        int addLineCount = 0;
        for (int j = 0; j < resList.size(); j++) {
            Map<String, Object> resMap = resList.get(j);
            if (j == 0 || !resMap.get("BILLING_COUNTER_CD").equals(resList.get(j - 1).get("BILLING_COUNTER_CD"))) {
                addLineCount++;
            }
            addLineCount++;
        }

        for (int i = bizMsg.Z.getValidCount() - 1; i > zStartRow; i--) {
            EZDMsg.copy(bizMsg.Z.no(i), null, bizMsg.Z.no(i + addLineCount - 1), null);
        }

        int ixZ = 0;
        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) { // usage area

            if (aBizMsg.mdlId_A.getValue().compareTo(bizMsg.Z.no(i).mdlId_Z.getValue()) == 0) {
                for (int j = 0; j < resList.size(); j++) {

                    Map<String, Object> resMap = resList.get(j);
                    if (j == 0 || !resMap.get("BILLING_COUNTER_CD").equals(resList.get(j - 1).get("BILLING_COUNTER_CD"))) {

                        bizMsg.Z.no(i + ixZ).clear();
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).xxFlgNm_Z, XX_FLG_PARENT);
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).xxSmryLineFlg_Z, ZYPConstant.FLG_ON_Y); // [+]

                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).t_MdlNm_Z, aBizMsg.t_MdlNm_A);
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).mdlId_Z, aBizMsg.mdlId_A);
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).prcCatgCd_Z, aBizMsg.prcCatgCd_A);
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).prcMtrPkgPk_Z, aBizMsg.prcMtrPkgPk_A);
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).bllgMtrLbCd_Z, (String) resMap.get("BILLING_COUNTER_CD"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).mtrLbDescTxt_ZB, (String) resMap.get("BILLING_COUNTER_NM"));

                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).mdseDescShortTxt_Z, (String) resMap.get("USAGE_ITEM_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).usgMdseCd_Z, (String) resMap.get("USAGE_ITEM_CD"));
                        //                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).xxScrEdtTpCd_Z, "Y"); // @@@ if has Tier then "Y".

                        if (ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_A) //
                                && ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd)) {
                            if (deriveDefaultBand(bizMsg, aBizMsg, bizMsg.Z.no(i + ixZ), null)) {
                                ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_Z, new BigDecimal(i + ixZ));
                                bizMsg.Z.setValidCount(i + ixZ + 1);
                                NSAL1330CommonLogic.afterBandPopupProc(bizMsg, glblMsg);
                            }
                        }
                        ixZ++;
                    }
                    bizMsg.Z.no(i + ixZ).clear();

                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).xxFlgNm_Z, XX_FLG_HARD);
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).t_MdlNm_Z, aBizMsg.t_MdlNm_A);
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).mdlId_Z, aBizMsg.mdlId_A);
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).prcCatgCd_Z, aBizMsg.prcCatgCd_A);
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).prcMtrPkgPk_Z, aBizMsg.prcMtrPkgPk_A);
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).bllgMtrLbCd_Z, (String) resMap.get("BILLING_COUNTER_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).mtrLbDescTxt_ZB, (String) resMap.get("BILLING_COUNTER_NM"));

                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).mtrLbDescTxt_Z, (String) resMap.get("HARD_COUNTER_NM"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).regMtrLbCd_Z, (String) resMap.get("HARD_COUNTER_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).contrMtrMultRate_Z, (BigDecimal) resMap.get("MTR_MULT_RATE"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).contrMtrMultRate_BK, (BigDecimal) resMap.get("MTR_MULT_RATE"));

                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).prcListBandDescTxt_Z //
                            , bizMsg.Z.no(i + ixZ - 1).prcListBandDescTxt_Z);
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).prcBookMdseCd_Z //
                            , bizMsg.Z.no(i + ixZ - 1).prcBookMdseCd_Z);
                    //                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).xxScrEdtTpCd_Z, "Y"); // @@@ if has Tier then "Y".

                    ixZ++;
                }
                break;
            }
        }
        for (int i = 0; i < bizMsg.Z.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.Z.no(i).mdlId_Z)) {
                break;
            }
            bizMsg.Z.setValidCount(i + 1);
        }

        if (bizMsg.A.getValidCount() > 0) {
            reOrdUsg(bizMsg);
        }

        //        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        //            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(ixA).mdlId_A)) {
        //                continue;
        //            }
        //            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).mdlId_A)) {
        //                continue;
        //            }
        //            if (bizMsg.A.no(ixA).mdlId_A.getValue().compareTo(bizMsg.A.no(i).mdlId_A.getValue()) != 0) {
        //                continue;
        //            }
        //            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcTierSvcOfferCd_A)) {
        //                NSAL1330CommonLogic.deriveTierInfo(bizMsg, i, glblCmpyCd);
        //            }
        //        }
    }

    public static void getMtrLbConfig(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd)) {
            return;
        }

        int ixR = bizMsg.xxNum_A.getValueInt();

        int uStartRow = bizMsg.U.getValidCount();
        List<Integer> delList = new ArrayList<Integer>();
        NSAL1330_RCMsg rBizMsg = bizMsg.R.no(ixR);
        for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
            if (rBizMsg.mdlId_R.getValue().compareTo(bizMsg.U.no(i).mdlId_U.getValue()) == 0 //
//                    && rBizMsg.cpoSvcConfigRefPk_R.getValue().compareTo(bizMsg.U.no(i).cpoSvcConfigRefPk_U.getValue()) == 0) {
                    && rBizMsg.dsContrDtlPk_R.getValue().compareTo(bizMsg.U.no(i).dsContrDtlPk_U.getValue()) == 0) {
                if (delList.size() == 0) {
                    uStartRow = i;
                }
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.U, delList); // delete exists data

        delList.clear();
        for (int i = 0; i < bizMsg.V.getValidCount(); i++) {
            if (rBizMsg.mdlId_R.getValue().compareTo(bizMsg.V.no(i).mdlId_V.getValue()) == 0 //
//                    && rBizMsg.cpoSvcConfigRefPk_R.getValue().compareTo(bizMsg.V.no(i).cpoSvcConfigRefPk_V.getValue()) == 0) {
                    && rBizMsg.dsContrDtlPk_R.getValue().compareTo(bizMsg.V.no(i).dsContrDtlPk_V.getValue()) == 0) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.V, delList); // delete exists data

        if (!ZYPCommonFunc.hasValue(rBizMsg.prcMtrPkgPk_R) //
                || isRateTypeAnnual(getPrcRateTpCd(bizMsg, rBizMsg))) {
            return;
        }

        // START 2017/10/26 [QC#21556, MOD]
        // S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getMtrLbConfig(rBizMsg);
        S21SsmEZDResult ssmResult = null;
        if (DS_CONTR_CATG.AGGREGATE.equals(bizMsg.dsContrCatgCd.getValue())
                && ZYPCommonFunc.hasValue(bizMsg.dsContrPk_AD) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.addAsryFlg.getValue())) {
            ssmResult = getMtrLbAggAddMachineConfig(bizMsg, rBizMsg);
        } else {
            // START 2018/07/10 K.Kojima [QC#27135,MOD]
            // ssmResult = NSAL1330Query.getInstance().getMtrLbConfig(rBizMsg);
            ssmResult = NSAL1330Query.getInstance().getMtrLbConfig(rBizMsg, bizMsg);
            // END 2018/07/10 K.Kojima [QC#27135,MOD]
        }
        // END   2017/10/26 [QC#21556, MOD]
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NSAM0670W, new String[] {rBizMsg.t_MdlNm_R.getValue() });
            return;
        }

        for (int i = bizMsg.U.getValidCount() - 1; i > uStartRow - 1; i--) {
            EZDMsg.copy(bizMsg.U.no(i), null, bizMsg.U.no(i + 1), null);
        }

        // set First Line
        bizMsg.U.no(uStartRow).clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).mdlId_U, rBizMsg.mdlId_R);
//        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).cpoSvcConfigRefPk_U, rBizMsg.cpoSvcConfigRefPk_R);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).dsContrDtlPk_U, rBizMsg.dsContrDtlPk_R);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).dsOrdPosnNum_U, rBizMsg.dsOrdPosnNum_R);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).t_MdlNm_U, rBizMsg.t_MdlNm_R);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).prcCatgCd_U, rBizMsg.prcCatgCd_R);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).prcMtrPkgPk_U, rBizMsg.prcMtrPkgPk_R);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).xxFlgNm_U, XX_FLG_PARENT);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).xxSmryLineFlg_U, ZYPConstant.FLG_ON_Y); // [+]
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).actvFlg_U, ZYPConstant.FLG_ON_Y);
        //        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).xxScrEdtTpCd_U, "Y"); // @@@ if has Tier then "Y".

        bizMsg.U.setValidCount(bizMsg.U.getValidCount() + 1);
        bizMsg.xxNum_Z.setValue(uStartRow);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();

        int zRowCount = 0;
        uStartRow = 0;
        for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
            if (rBizMsg.mdlId_R.getValue().compareTo(bizMsg.U.no(i).mdlId_U.getValue()) == 0 //
//                    && rBizMsg.cpoSvcConfigRefPk_R.getValue().compareTo(bizMsg.U.no(i).cpoSvcConfigRefPk_U.getValue()) == 0) {
                    && rBizMsg.dsContrDtlPk_R.getValue().compareTo(bizMsg.U.no(i).dsContrDtlPk_U.getValue()) == 0) {
                if (zRowCount == 0) {
                    uStartRow = i;
                }
                zRowCount++;
            }
        }
        bizMsg.xxNum_Z.setValue(uStartRow);

        int addLineCount = 0;
        for (int j = 0; j < resList.size(); j++) {
            Map<String, Object> resMap = resList.get(j);
            if (j == 0 || !resMap.get("BILLING_COUNTER_CD").equals(resList.get(j - 1).get("BILLING_COUNTER_CD"))) {
                addLineCount++;
            }
            addLineCount++;
        }

        for (int i = bizMsg.U.getValidCount() - 1; i > uStartRow; i--) {
            EZDMsg.copy(bizMsg.U.no(i), null, bizMsg.U.no(i + addLineCount - 1), null);
        }

        int ixU = 0;
        for (int i = 0; i < bizMsg.U.getValidCount(); i++) { // usage area

//            if (rBizMsg.mdlId_R.getValue().compareTo(bizMsg.U.no(i).mdlId_U.getValue()) == 0 && rBizMsg.cpoSvcConfigRefPk_R.getValue().compareTo(bizMsg.U.no(i).cpoSvcConfigRefPk_U.getValue()) == 0) {
            if (rBizMsg.mdlId_R.getValue().compareTo(bizMsg.U.no(i).mdlId_U.getValue()) == 0 && rBizMsg.dsContrDtlPk_R.getValue().compareTo(bizMsg.U.no(i).dsContrDtlPk_U.getValue()) == 0) {
                for (int j = 0; j < resList.size(); j++) {

                    Map<String, Object> resMap = resList.get(j);
                    if (j == 0 || !resMap.get("BILLING_COUNTER_CD").equals(resList.get(j - 1).get("BILLING_COUNTER_CD"))) {

                        bizMsg.U.no(i + ixU).clear();
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).xxFlgNm_U, XX_FLG_PARENT);
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).actvFlg_U, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).xxSmryLineFlg_U, ZYPConstant.FLG_ON_Y); // [+]

                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).t_MdlNm_U, rBizMsg.t_MdlNm_R);
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).mdlId_U, rBizMsg.mdlId_R);
//                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).cpoSvcConfigRefPk_U, rBizMsg.cpoSvcConfigRefPk_R);
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).dsContrDtlPk_U, rBizMsg.dsContrDtlPk_R);
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).dsOrdPosnNum_U, rBizMsg.dsOrdPosnNum_R);
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).prcCatgCd_U, rBizMsg.prcCatgCd_R);
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).prcMtrPkgPk_U, rBizMsg.prcMtrPkgPk_R);
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).bllgMtrLbCd_U, (String) resMap.get("BILLING_COUNTER_CD"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).mtrLbDescTxt_UB, (String) resMap.get("BILLING_COUNTER_NM"));

                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).mdseDescShortTxt_U, (String) resMap.get("USAGE_ITEM_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).usgMdseCd_U, (String) resMap.get("USAGE_ITEM_CD"));
                        //                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).xxScrEdtTpCd_U, "Y"); // @@@ if has Tier then "Y".

//                        if (!ZYPCommonFunc.hasValue(rBizMsg.cpoSvcPrcPk_R) //
                        if (!ZYPCommonFunc.hasValue(rBizMsg.dsContrDtlPk_R) //
                                || (!isInitialEvent(bizMsg))) {
                            if (ZYPCommonFunc.hasValue(rBizMsg.prcMtrPkgPk_R) //
                                    && ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd)) {
                                if (deriveDefaultBandConfig(bizMsg, rBizMsg, bizMsg.U.no(i + ixU), null)) {
                                    ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_Z, new BigDecimal(i + ixU));
                                    bizMsg.U.setValidCount(i + ixU + 1);
                                    NSAL1330CommonLogic.afterBandPopupProcConfig(bizMsg, glblMsg);
                                }
                            }
                        }
                        ixU++;
                    }
                    bizMsg.U.no(i + ixU).clear();

                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).xxFlgNm_U, XX_FLG_HARD);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).actvFlg_U, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).t_MdlNm_U, rBizMsg.t_MdlNm_R);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).mdlId_U, rBizMsg.mdlId_R);
//                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).cpoSvcConfigRefPk_U, rBizMsg.cpoSvcConfigRefPk_R);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).dsContrDtlPk_U, rBizMsg.dsContrDtlPk_R);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).dsOrdPosnNum_U, rBizMsg.dsOrdPosnNum_R);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).prcCatgCd_U, rBizMsg.prcCatgCd_R);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).prcMtrPkgPk_U, rBizMsg.prcMtrPkgPk_R);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).bllgMtrLbCd_U, (String) resMap.get("BILLING_COUNTER_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).mtrLbDescTxt_UB, (String) resMap.get("BILLING_COUNTER_NM"));

                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).mtrLbDescTxt_U, (String) resMap.get("HARD_COUNTER_NM"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).regMtrLbCd_U, (String) resMap.get("HARD_COUNTER_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).contrMtrMultRate_U, (BigDecimal) resMap.get("MTR_MULT_RATE"));

                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).prcListBandDescTxt_U //
                            , bizMsg.U.no(i + ixU - 1).prcListBandDescTxt_U);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).prcBookMdseCd_U //
                            , bizMsg.U.no(i + ixU - 1).prcBookMdseCd_U);
                    //                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).xxScrEdtTpCd_U, "Y"); // @@@ if has Tier then "Y".

                    ixU++;
                }
                break;
            }
        }
        for (int i = 0; i < bizMsg.U.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.U.no(i).mdlId_U)) {
                break;
            }
            bizMsg.U.setValidCount(i + 1);
        }

        if (bizMsg.R.getValidCount() > 0) {
            reOrdUsgConfig(bizMsg);
        }
    }

    /**
     * @param bizMsg
     * @return
     */
    private static boolean isInitialEvent(NSAL1330CMsg bizMsg) {
        return "NSAL1330_INIT".equals(bizMsg.getScreenAplID()) //
                || "NSAL1330Scrn00_CMN_Reset".equals(bizMsg.getScreenAplID()) //
                // START 2018/03/27 K.Kojima [QC#24929,ADD]
                || "NSAL1330_NSAL1380".equals(bizMsg.getScreenAplID()) //
                // END 2018/03/27 K.Kojima [QC#24929,ADD]
                || "NSAL1330Scrn00_CMN_Save".equals(bizMsg.getScreenAplID());
    }

    /**
     * deriveDefaultBand
     * @param bizMsg NSAL1330CMsg
     * @param aBizMsg NSAL1330_ACMsg
     * @param zBizMsg NSAL1330_ZCMsg
     * @param prcListBandDescTxt String
     * @return boolean
     */
    public static boolean deriveDefaultBand(//
            NSAL1330CMsg bizMsg, NSAL1330_ACMsg aBizMsg, NSAL1330_ZCMsg zBizMsg, String prcListBandDescTxt) {
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcListStruTp(aBizMsg.prcCatgCd_A.getValue());
        if (ssmResult.isCodeNotFound()) {
            aBizMsg.prcCatgCd_A.setErrorInfo(1, NSAM0647E //
                    , new String[] {"DS_PRC_CATG", "getPrcListStruTp : " + aBizMsg.prcCatgCd_A.getValue() });
            return false;
        }

        if (ZYPCommonFunc.hasValue(prcListBandDescTxt)) {
            S21SsmEZDResult ssmBandResult //
            = NSAL1330Query.getInstance().getCountPrcListBandFromDescTxt(prcListBandDescTxt);
            if ((Integer) ssmBandResult.getResultObject() == 0) {
                zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NSAM0647E //
                        , new String[] {"PRC_LIST_BAND", "PRC_LIST_BAND_DESC_TXT : " + prcListBandDescTxt });
                return false;
            }
        }

        String prcListStruTp = (String) ssmResult.getResultObject();

        if (PRC_LIST_STRU_TP.SERVICE.equals(prcListStruTp)) {
            ssmResult = NSAL1330Query.getInstance().getDefaultBandForService(bizMsg, aBizMsg, zBizMsg, prcListBandDescTxt);

        } else if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTp)) {
            ssmResult = NSAL1330Query.getInstance().getDefaultBandForServiceTiers(bizMsg, aBizMsg, zBizMsg, prcListBandDescTxt);

        } else if (PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(prcListStruTp)) {
            ssmResult = NSAL1330Query.getInstance().getDefaultBandForSupplyProgram(bizMsg, aBizMsg, zBizMsg, prcListBandDescTxt);
            if (ssmResult.getQueryResultCount() == 0) {
                S21SsmEZDResult sapn = NSAL1330Query.getInstance().getSplyAgmtPlnNm(aBizMsg, zBizMsg, bizMsg);
                List<Map<String, String>> splyAgmtPlnNmList = (List<Map<String, String>>) sapn.getResultObject();
                if (splyAgmtPlnNmList == null || splyAgmtPlnNmList.size() == 0) {
                    zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NSAM0671E //
                            , new String[] {"Band:" + prcListBandDescTxt });
                } else {
                    for (Map<String, String> splyAgmtPlnNmMap : splyAgmtPlnNmList) {
                        if (ZYPConstant.FLG_ON_Y.equals(splyAgmtPlnNmMap.get("EXISTS_FLG"))) {
                            continue;
                        }
                        zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NSAM0682E //
                                , new String[] {splyAgmtPlnNmMap.get("EDIT_SPLY_AGMT_PLN_NM") });
                        break;
                    }
                }
                return false;
            }

        } else {
            aBizMsg.prcCatgCd_A.setErrorInfo(1, NSAM0647E //
                    , new String[] {"PRC_LIST_STRU_TP", "PRC_LIST_STRU_TP : " + prcListStruTp });
            return false;
        }

        // START 2017/10/26 [QC#21556, ADD]
        if (DS_CONTR_CATG.AGGREGATE.equals(bizMsg.dsContrCatgCd.getValue()) 
                && ZYPCommonFunc.hasValue(bizMsg.dsContrPk_AD.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.addAsryFlg.getValue())) {
            int xsCount = NSAL1330Query.getInstance().getTierInfo_BandSearchForAggAddMachine(bizMsg, zBizMsg).getQueryResultCount();
            if (xsCount > 1) {
                String prcTierSvcOfferCd = 
                    (String) NSAL1330Query.getInstance().getPrcTierSvcOfferCdFromContrForAddMachine(bizMsg).getResultObject();
                ZYPEZDItemValueSetter.setValue(aBizMsg.prcTierSvcOfferCd_A, prcTierSvcOfferCd);
                return true;
            }
        }
        // END   2017/10/26 [QC#21556, ADD]

        if (ssmResult.getQueryResultCount() != 1) {
            //            zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NSAM0671E //
            //                    , new String[] {"Band:" + prcListBandDescTxt + "(" + stmtId + ")" });

            // START 2017/10/26 [QC#21556, ADD]
            if (DS_CONTR_CATG.AGGREGATE.equals(bizMsg.dsContrCatgCd.getValue()) 
                    && ZYPCommonFunc.hasValue(bizMsg.dsContrPk_AD.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.addAsryFlg.getValue())) {

                BigDecimal aggXsMtrAmtRate = (BigDecimal) NSAL1330Query.getInstance().getAggContrXsMtrAmtRate(bizMsg, zBizMsg).getResultObject();
                if (ZYPCommonFunc.hasValue(aggXsMtrAmtRate)) {
                    ZYPEZDItemValueSetter.setValue(zBizMsg.xsMtrAmtRate_Z, aggXsMtrAmtRate);
                }
            }
            // END   2017/10/26 [QC#21556, ADD]

            return true;
        }
        Map<String, Object> rsltMap = (Map<String, Object>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(zBizMsg.prcListBandDescTxt_Z, (String) rsltMap.get("PRC_LIST_BAND_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(zBizMsg.prcBookMdseCd_Z, (String) rsltMap.get("MDSE_CD"));

        // START 2017/07/12 [QC#19472, MOD]
        // if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTp)) {
        if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTp) || ZYPCommonFunc.hasValue(aBizMsg.prcTierSvcOfferCd_A)) {
            return true;
        }
        // END   2017/07/12 [QC#19472, MOD]

        BigDecimal minCopyVolCnt = (BigDecimal) rsltMap.get("MIN_COPY_VOL_CNT");
        BigDecimal maxCopyVolCnt = (BigDecimal) rsltMap.get("MAX_COPY_VOL_CNT");

        if (!ZYPCommonFunc.hasValue(minCopyVolCnt)//
                || !ZYPCommonFunc.hasValue(maxCopyVolCnt)) {
            zBizMsg.mlyCopyInclPrcQty_Z.setValue(BigDecimal.ZERO);
        } else if (minCopyVolCnt.compareTo(maxCopyVolCnt) == 0) {
            ZYPEZDItemValueSetter.setValue(zBizMsg.mlyCopyInclPrcQty_Z, minCopyVolCnt);
        } else {
            zBizMsg.mlyCopyInclPrcQty_Z.setValue(BigDecimal.ZERO);
        }

        ZYPEZDItemValueSetter.setValue(zBizMsg.xsMtrAmtRate_Z, (BigDecimal) rsltMap.get("CPC_AMT_RATE"));

        // START 2017/10/26 [QC#21556, ADD]
        if (DS_CONTR_CATG.AGGREGATE.equals(bizMsg.dsContrCatgCd.getValue()) 
                && ZYPCommonFunc.hasValue(bizMsg.dsContrPk_AD.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.addAsryFlg.getValue())) {

            BigDecimal aggXsMtrAmtRate = (BigDecimal) NSAL1330Query.getInstance().getAggContrXsMtrAmtRate(bizMsg, zBizMsg).getResultObject();
            if (ZYPCommonFunc.hasValue(aggXsMtrAmtRate)) {
                ZYPEZDItemValueSetter.setValue(zBizMsg.xsMtrAmtRate_Z, aggXsMtrAmtRate);
            }
        }
        // END   2017/10/26 [QC#21556, ADD]

        return true;
    }

    /**
     * deriveDefaultBand
     * @param bizMsg NSAL1330CMsg
     * @param rBizMsg NSAL1330_ACMsg
     * @param uBizMsg NSAL1330_ZCMsg
     * @param prcListBandDescTxt String
     * @return boolean
     */
    public static boolean deriveDefaultBandConfig(//
            NSAL1330CMsg bizMsg, NSAL1330_RCMsg rBizMsg, NSAL1330_UCMsg uBizMsg, String prcListBandDescTxt) {
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcListStruTp(rBizMsg.prcCatgCd_R.getValue());
        if (ssmResult.isCodeNotFound()) {
            rBizMsg.prcCatgCd_R.setErrorInfo(1, NSAM0647E //
                    , new String[] {"DS_PRC_CATG", "getPrcListStruTp : " + rBizMsg.prcCatgCd_R.getValue() });
            return false;
        }

        if (ZYPCommonFunc.hasValue(prcListBandDescTxt)) {
            S21SsmEZDResult ssmBandResult //
            = NSAL1330Query.getInstance().getCountPrcListBandFromDescTxt(prcListBandDescTxt);
            if ((Integer) ssmBandResult.getResultObject() == 0) {
                uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NSAM0647E //
                        , new String[] {"PRC_LIST_BAND", "PRC_LIST_BAND_DESC_TXT : " + prcListBandDescTxt });
                return false;
            }
        }

        String prcListStruTp = (String) ssmResult.getResultObject();

        if (PRC_LIST_STRU_TP.SERVICE.equals(prcListStruTp)) {
            ssmResult = NSAL1330Query.getInstance().getDefaultBandForService(bizMsg, rBizMsg, uBizMsg, prcListBandDescTxt);

        } else if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTp)) {
            ssmResult = NSAL1330Query.getInstance().getDefaultBandForServiceTiers(bizMsg, rBizMsg, uBizMsg, prcListBandDescTxt);

        } else if (PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(prcListStruTp)) {
            ssmResult = NSAL1330Query.getInstance().getDefaultBandForSupplyProgram(bizMsg, rBizMsg, uBizMsg, prcListBandDescTxt);
            if (ssmResult.getQueryResultCount() == 0) {
                S21SsmEZDResult sapn = NSAL1330Query.getInstance().getSplyAgmtPlnNm(rBizMsg, uBizMsg, bizMsg);
                List<Map<String, String>> splyAgmtPlnNmList = (List<Map<String, String>>) sapn.getResultObject();
                if (splyAgmtPlnNmList == null || splyAgmtPlnNmList.size() == 0) {
                    uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NSAM0671E //
                            , new String[] {"Band:" + prcListBandDescTxt });
                } else {
                    for (Map<String, String> splyAgmtPlnNmMap : splyAgmtPlnNmList) {
                        if (ZYPConstant.FLG_ON_Y.equals(splyAgmtPlnNmMap.get("EXISTS_FLG"))) {
                            continue;
                        }
                        uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NSAM0682E //
                                , new String[] {splyAgmtPlnNmMap.get("EDIT_SPLY_AGMT_PLN_NM") });
                        break;
                    }
                }
                return false;
            }

        } else {
            rBizMsg.prcCatgCd_R.setErrorInfo(1, NSAM0647E //
                    , new String[] {"PRC_LIST_STRU_TP", "PRC_LIST_STRU_TP : " + prcListStruTp });
            return false;
        }

        // START 2017/10/26 [QC#21556, ADD]
        if (DS_CONTR_CATG.AGGREGATE.equals(bizMsg.dsContrCatgCd.getValue()) 
                && ZYPCommonFunc.hasValue(bizMsg.dsContrPk_AD.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.addAsryFlg.getValue())) {
            int xsCount = NSAL1330Query.getInstance().getTierInfo_BandSearchForConfigAggAddMachine(bizMsg, uBizMsg).getQueryResultCount();
            if (xsCount > 1) {
                String prcTierSvcOfferCd = 
                    (String) NSAL1330Query.getInstance().getPrcTierSvcOfferCdFromContrForAddMachine(bizMsg).getResultObject();
                ZYPEZDItemValueSetter.setValue(rBizMsg.prcTierSvcOfferCd_R, prcTierSvcOfferCd);
                return true;
            }
        }
        // END   2017/10/26 [QC#21556, ADD]

        if (ssmResult.getQueryResultCount() != 1) {

            // START 2017/10/26 [QC#21556, ADD]
            if (DS_CONTR_CATG.AGGREGATE.equals(bizMsg.dsContrCatgCd.getValue()) 
                    && ZYPCommonFunc.hasValue(bizMsg.dsContrPk_AD.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.addAsryFlg.getValue())) {

                BigDecimal aggXsMtrAmtRate = (BigDecimal) NSAL1330Query.getInstance().getAggContrXsMtrAmtRateConfig(bizMsg, uBizMsg).getResultObject();
                if (ZYPCommonFunc.hasValue(aggXsMtrAmtRate)) {
                    ZYPEZDItemValueSetter.setValue(uBizMsg.xsMtrAmtRate_U, aggXsMtrAmtRate);
                }
            }
            // END   2017/10/26 [QC#21556, ADD]

            return true;
        }
        Map<String, Object> rsltMap = (Map<String, Object>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(uBizMsg.prcListBandDescTxt_U, (String) rsltMap.get("PRC_LIST_BAND_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(uBizMsg.prcBookMdseCd_U, (String) rsltMap.get("MDSE_CD"));

        // START 2017/07/12 [QC#19472, MOD]
        // if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTp)) {
        if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTp) || ZYPCommonFunc.hasValue(rBizMsg.prcTierSvcOfferCd_R)) {
            return true;
        }
        // END   2017/07/12 [QC#19472, MOD]

        BigDecimal minCopyVolCnt = (BigDecimal) rsltMap.get("MIN_COPY_VOL_CNT");
        BigDecimal maxCopyVolCnt = (BigDecimal) rsltMap.get("MAX_COPY_VOL_CNT");

        if (!ZYPCommonFunc.hasValue(minCopyVolCnt)//
                || !ZYPCommonFunc.hasValue(maxCopyVolCnt)) {
            uBizMsg.mlyCopyInclPrcQty_U.setValue(BigDecimal.ZERO);
        } else if (minCopyVolCnt.compareTo(maxCopyVolCnt) == 0) {
            ZYPEZDItemValueSetter.setValue(uBizMsg.mlyCopyInclPrcQty_U, minCopyVolCnt);
        } else {
            uBizMsg.mlyCopyInclPrcQty_U.setValue(BigDecimal.ZERO);
        }

        ZYPEZDItemValueSetter.setValue(uBizMsg.xsMtrAmtRate_U, (BigDecimal) rsltMap.get("CPC_AMT_RATE"));

        // START 2017/10/26 [QC#21556, ADD]
        if (DS_CONTR_CATG.AGGREGATE.equals(bizMsg.dsContrCatgCd.getValue()) 
                && ZYPCommonFunc.hasValue(bizMsg.dsContrPk_AD.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.addAsryFlg.getValue())) {

            BigDecimal aggXsMtrAmtRate = (BigDecimal) NSAL1330Query.getInstance().getAggContrXsMtrAmtRateConfig(bizMsg, uBizMsg).getResultObject();
            if (ZYPCommonFunc.hasValue(aggXsMtrAmtRate)) {
                ZYPEZDItemValueSetter.setValue(uBizMsg.xsMtrAmtRate_U, aggXsMtrAmtRate);
            }
        }
        // END   2017/10/26 [QC#21556, ADD]

        //        ZYPEZDItemValueSetter.setValue(uBizMsg.xxScrEdtTpCd_U, "Y"); // @@@ if has Tier then "Y".
        return true;
    }

    private static boolean isRateTypeAnnual(String prcRateTpCd) {
        return PRC_RATE_TP.ANNUAL.equals(prcRateTpCd);
    }

    public static void reOrdUsg(NSAL1330CMsg bizMsg) {
        if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
            return;
        }
        List<NSAL1330_ZCMsg> zMsgList = new ArrayList<NSAL1330_ZCMsg>();
        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
            NSAL1330_ZCMsg zBizMsg = new NSAL1330_ZCMsg();
            EZDMsg.copy(bizMsg.Z.no(i), null, zBizMsg, null);
            zMsgList.add(zBizMsg);
        }

        List<String> xKeyList = new ArrayList<String>();
        for (int i = 0; i < bizMsg.X.getValidCount(); i++) {
            NSAL1330_XCMsg xBizMsg = bizMsg.X.no(i);
            String vKey = S21StringUtil.concatStrings(//
                    xBizMsg.mdlId_X.getValue().toPlainString() //
                    , "," //
                    , xBizMsg.bllgMtrLbCd_X.getValue());
            xKeyList.add(vKey);
        }

        ZYPTableUtil.clear(bizMsg.Z);
        int ixZ = 0;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            BigDecimal mdlId = bizMsg.A.no(i).mdlId_A.getValue();
            for (NSAL1330_ZCMsg zBizMsgWk : zMsgList) {
                if (mdlId.compareTo(zBizMsgWk.mdlId_Z.getValue()) == 0) {
                    NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(ixZ);
                    EZDMsg.copy(zBizMsgWk, null, zBizMsg, null);

                    String uKey = S21StringUtil.concatStrings(//
                            zBizMsg.mdlId_Z.getValue().toPlainString() //
                            , "," //
                            , zBizMsg.bllgMtrLbCd_Z.getValue());
                    // 2018/07/09 S21_NA#26528 Mod Start
                    // if (xKeyList.contains(uKey)) {
                    if (xKeyList.contains(uKey) || ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcTierSvcOfferCd_A)) {
                        // 2018/07/09 S21_NA#26528 Mod End
                        zBizMsg.xxScrEdtTpCd_Z.setValue(ZYPConstant.FLG_ON_Y);
                    } else {
                        zBizMsg.xxScrEdtTpCd_Z.setValue(ZYPConstant.FLG_OFF_N);
                    }
                    ixZ++;
                }
            }
        }
        bizMsg.Z.setValidCount(ixZ);
    }

    public static void reOrdUsgConfig(NSAL1330CMsg bizMsg) {
        List<NSAL1330_UCMsg> uMsgList = new ArrayList<NSAL1330_UCMsg>();
        for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
            NSAL1330_UCMsg uBizMsg = new NSAL1330_UCMsg();
            EZDMsg.copy(bizMsg.U.no(i), null, uBizMsg, null);
            uMsgList.add(uBizMsg);
        }

        List<String> wkKeyList = new ArrayList<String>();
        List<String> vKeyList = new ArrayList<String>();
        for (int i = 0; i < bizMsg.V.getValidCount(); i++) {
            NSAL1330_VCMsg vBizMsg = bizMsg.V.no(i);
            String vKey = S21StringUtil.concatStrings(//
                    vBizMsg.mdlId_V.getValue().toPlainString() //
                    , "," //
                    , vBizMsg.dsOrdPosnNum_V.getValue() //
                    , "," //
                    , vBizMsg.bllgMtrLbCd_V.getValue());
            if (wkKeyList.contains(vKey)) {
                vKeyList.add(vKey); // if vKey exists multiple records then set to vKeyList.
            } else {
                wkKeyList.add(vKey);
            }
        }

        ZYPTableUtil.clear(bizMsg.U);
        int ixU = 0;
        for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
            BigDecimal mdlId = bizMsg.R.no(i).mdlId_R.getValue();
//            BigDecimal cpoSvcConfigRefPk = bizMsg.R.no(i).cpoSvcConfigRefPk_R.getValue();
            BigDecimal dsContrDtlPk = bizMsg.R.no(i).dsContrDtlPk_R.getValue();
            for (NSAL1330_UCMsg uBizMsgWk : uMsgList) {
                if (!isSameValue(mdlId, uBizMsgWk.mdlId_U.getValue())) {
                    continue;
                }
//                if (!ZYPCommonFunc.hasValue(cpoSvcConfigRefPk) || !ZYPCommonFunc.hasValue(uBizMsgWk.cpoSvcConfigRefPk_U)) {
                if (!ZYPCommonFunc.hasValue(dsContrDtlPk) || !ZYPCommonFunc.hasValue(uBizMsgWk.dsContrDtlPk_U)) {
                    continue;
                }
//                if (cpoSvcConfigRefPk.compareTo(uBizMsgWk.cpoSvcConfigRefPk_U.getValue()) == 0) {
                if (dsContrDtlPk.compareTo(uBizMsgWk.dsContrDtlPk_U.getValue()) == 0) {
                    NSAL1330_UCMsg uBizMsg = bizMsg.U.no(ixU);
                    EZDMsg.copy(uBizMsgWk, null, uBizMsg, null);

                    String uKey = S21StringUtil.concatStrings(//
                            uBizMsg.mdlId_U.getValue().toPlainString() //
                            , "," //
                            , uBizMsg.dsOrdPosnNum_U.getValue() //
                            , "," //
                            , uBizMsg.bllgMtrLbCd_U.getValue());
                    // 2018/07/09 S21_NA#26528 Mod Start
                    // if (vKeyList.contains(uKey)) {
                    if (vKeyList.contains(uKey) || ZYPCommonFunc.hasValue(bizMsg.R.no(i).prcTierSvcOfferCd_R)) {
                        // 2018/07/09 S21_NA#26528 Mod End
                        uBizMsg.xxScrEdtTpCd_U.setValue(ZYPConstant.FLG_ON_Y);
                    } else {
                        uBizMsg.xxScrEdtTpCd_U.setValue(ZYPConstant.FLG_OFF_N);
                    }
                    ixU++;
                }
            }
        }
        bizMsg.U.setValidCount(ixU);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd String
     * @return
     */
    public static void createPrcMtrPkgPulldown(NSAL1330CMsg bizMsg, String glblCmpyCd) {
        S21SsmEZDResult ssmResult = null;

        int index = bizMsg.xxNum_A.getValueInt();
        if (!ZYPCommonFunc.hasValue(bizMsg.A.no(index).mdlId_A)) {
            return;
        }
        bizMsg.A.no(index).prcMtrPkgPk_KP.clear();
        bizMsg.A.no(index).prcMtrPkgNm_VW.clear();
        String prcListTp = bizMsg.A.no(index).prcListTpCd_A.getValue();

        if (PRC_LIST_TP.EASYPACII_OPTIMIZEIT.equals(prcListTp)) {
            ssmResult = NSAL1330Query.getInstance().getSvcPkg21(bizMsg, index, glblCmpyCd);

        } else if (PRC_LIST_TP.SERVICE_TIERS.equals(prcListTp)) {
            ssmResult = NSAL1330Query.getInstance().getSvcPkg07(bizMsg, index, glblCmpyCd);

        } else {
            ssmResult = NSAL1330Query.getInstance().getSvcPkg(bizMsg, index, glblCmpyCd);

        }

        // set Service Package
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resList == null || resList.isEmpty()) {
            return;
        }
        for (int i = 0; i < resList.size(); i++) {
            Map<String, Object> resMap = resList.get(i);
            BigDecimal pkgPk = (BigDecimal) resMap.get(PRC_MTR_PKG_PK);
            String pkgNm = (String) resMap.get(PRC_MTR_PKG_NM);

            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).prcMtrPkgPk_KP.no(i), pkgPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).prcMtrPkgNm_VW.no(i), pkgNm);

        }
    }

    public static void createPrcMtrPkgPulldownConfig(NSAL1330CMsg bizMsg) {
        int index = bizMsg.xxNum_A.getValueInt();
        createPrcMtrPkgPulldownConfig(bizMsg, index);
    }

    public static void createPrcMtrPkgPulldownConfig(NSAL1330CMsg bizMsg, int index) {
        S21SsmEZDResult ssmResult = null;

        NSAL1330_RCMsg rBizMsg = bizMsg.R.no(index);
        if (!ZYPCommonFunc.hasValue(rBizMsg.mdlId_R)) {
            return;
        }
        rBizMsg.prcMtrPkgPk_RL.clear();
        rBizMsg.prcMtrPkgNm_RL.clear();
        String prcListTp = rBizMsg.prcListTpCd_R.getValue();

        if (PRC_LIST_TP.EASYPACII_OPTIMIZEIT.equals(prcListTp)) {
            ssmResult = NSAL1330Query.getInstance().getSvcPkg21(bizMsg, rBizMsg);

        } else if (PRC_LIST_TP.SERVICE_TIERS.equals(prcListTp)) {
            ssmResult = NSAL1330Query.getInstance().getSvcPkg07(bizMsg, rBizMsg);

        } else {
            ssmResult = NSAL1330Query.getInstance().getSvcPkg(bizMsg, rBizMsg);

        }

        // set Service Package
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resList == null || resList.isEmpty()) {
            return;
        }
        for (int i = 0; i < resList.size(); i++) {
            Map<String, Object> resMap = resList.get(i);
            BigDecimal pkgPk = (BigDecimal) resMap.get(PRC_MTR_PKG_PK);
            String pkgNm = (String) resMap.get(PRC_MTR_PKG_NM);

            ZYPEZDItemValueSetter.setValue(rBizMsg.prcMtrPkgPk_RL.no(i), pkgPk);
            ZYPEZDItemValueSetter.setValue(rBizMsg.prcMtrPkgNm_RL.no(i), pkgNm);
        }
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @param mdlList List<BigDecimal>
     * @param glblCmpyCd String
     * @return
     */
    public static void createPrcMtrPkgPulldownFL(NSAL1330CMsg bizMsg, List<BigDecimal> mdlList, String glblCmpyCd) {
        S21SsmEZDResult ssmResult = null;

        int index = 0;
        bizMsg.A.no(index).prcMtrPkgPk_KP.clear();
        bizMsg.A.no(index).prcMtrPkgNm_VW.clear();
        String prcListTp = bizMsg.A.no(index).prcListTpCd_A.getValue();

        if (PRC_LIST_TP.EASYPACII_OPTIMIZEIT.equals(prcListTp)) {
            ssmResult = NSAL1330Query.getInstance().getSvcPkg21FL(bizMsg, mdlList, glblCmpyCd);

        } else if (PRC_LIST_TP.SERVICE_TIERS.equals(prcListTp)) {
            ssmResult = NSAL1330Query.getInstance().getSvcPkg07FL(bizMsg, mdlList, glblCmpyCd);

        } else {
            ssmResult = NSAL1330Query.getInstance().getSvcPkgFL(bizMsg, mdlList, glblCmpyCd);

        }

        // set Service Package
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resList == null || resList.isEmpty()) {
            return;
        }
        for (int i = 0; i < resList.size(); i++) {
            Map<String, Object> resMap = resList.get(i);
            BigDecimal pkgPk = (BigDecimal) resMap.get(PRC_MTR_PKG_PK);
            String pkgNm = (String) resMap.get(PRC_MTR_PKG_NM);

            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).prcMtrPkgPk_KP.no(i), pkgPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).prcMtrPkgNm_VW.no(i), pkgNm);

        }
    }

    //
    // public static void addMtrMultRateLine(NSAL1330CMsg bizMsg, String glblCmpyCd) {
    // int ixZ = bizMsg.Z.getValidCount();
    //
    // bizMsg.Z.no(ixZ).clear();
    //
    // // S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcListSplyPgmInfo(bizMsg, glblCmpyCd);
    //
    // ixZ++;
    // bizMsg.Z.setValidCount(ixZ);
    //
    // }

    /**
     * @param bizMsg NSAL1330CMsg
     */
    public static void setRentalOrderFlag(NSAL1330CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().cntRentalOrder(bizMsg);
        @SuppressWarnings("unchecked")
        List<Integer> cnt = (List<Integer>) ssmResult.getResultObject();

        if (cnt.get(0) > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rntlOrdFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.rntlOrdFlg, ZYPConstant.FLG_OFF_N);
        }
    }

    //    public static void closeTierCheck(NSAL1330CMsg bizMsg, BigDecimal mdlId) {
    //        boolean isFleet = DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue());
    //        if (!ZYPCommonFunc.hasValue(mdlId) && !isFleet) {
    //            return;
    //        }
    //        Map<String, Integer> bllgMtrLbMap = new HashMap<String, Integer>();
    //        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
    //            NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(i);
    //            if (!isFleet) {
    //                if (!ZYPCommonFunc.hasValue(zBizMsg.mdlId_Z)) {
    //                    continue;
    //                }
    //                if (mdlId.compareTo(zBizMsg.mdlId_Z.getValue()) != 0) {
    //                    continue;
    //                }
    //            }
    //            if (!ZYPCommonFunc.hasValue(zBizMsg.bllgMtrLbCd_Z)) {
    //                continue;
    //            }
    //            Integer cnt = bllgMtrLbMap.get(zBizMsg.bllgMtrLbCd_Z.getValue());
    //            if (cnt == null) {
    //                bllgMtrLbMap.put(zBizMsg.bllgMtrLbCd_Z.getValue(), 0);
    //            }
    //            if (!ZYPCommonFunc.hasValue(zBizMsg.prcSvcTierTpCd_Z)) {
    //                continue;
    //            }
    //            cnt = bllgMtrLbMap.get(zBizMsg.bllgMtrLbCd_Z.getValue());
    //            if (cnt == null) {
    //                bllgMtrLbMap.put(zBizMsg.bllgMtrLbCd_Z.getValue(), 1);
    //            } else {
    //                bllgMtrLbMap.put(zBizMsg.bllgMtrLbCd_Z.getValue(), cnt + 1);
    //            }
    //        }
    //        for (Map.Entry<String, Integer> entry : bllgMtrLbMap.entrySet()) {
    //            String bllgMtrLb = entry.getKey();
    //            Integer cnt = entry.getValue();
    //            if (cnt <= 1) {
    //                setTierError(bizMsg, mdlId, bllgMtrLb, isFleet);
    //            }
    //        }
    //    }
    //
    //    private static void setTierError(NSAL1330CMsg bizMsg, BigDecimal mdlId, String bllgMtrLbCd, boolean isFleet) {
    //        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
    //            if (!isFleet) {
    //                if (!ZYPCommonFunc.hasValue(bizMsg.Z.no(i).mdlId_Z)) {
    //                    continue;
    //                }
    //                if (mdlId.compareTo(bizMsg.Z.no(i).mdlId_Z.getValue()) != 0) {
    //                    continue;
    //                }
    //            }
    //            if (!bllgMtrLbCd.equals(bizMsg.Z.no(i).bllgMtrLbCd_Z.getValue())) {
    //                continue;
    //            }
    //            if (ZYPCommonFunc.hasValue(bizMsg.Z.no(i).regMtrLbCd_Z.getValue())) {
    //                continue;
    //            }
    //            bizMsg.Z.no(i).xxChkBox_Z.setErrorInfo(1, NSAM0662E);
    //        }
    //    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @return if error then return true.
     */
    public static boolean acsryDuplicateCheck(NSAL1330CMsg bizMsg) {
        boolean isError = false;
        List<String> lineNum = new ArrayList<String>();
        for (int i = 0; i < bizMsg.J.getValidCount(); i++) {
            if (lineNum.contains(bizMsg.J.no(i).xxLineNum_J.getValue())) {
                setDuplicateErrorJ(bizMsg, bizMsg.J.no(i).xxLineNum_J.getValue());
                isError = true;
            } else {
                lineNum.add(bizMsg.J.no(i).xxLineNum_J.getValue());
            }
        }
        lineNum.clear();
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (lineNum.contains(bizMsg.B.no(i).xxLineNum_B.getValue())) {
                setDuplicateErrorB(bizMsg, bizMsg.B.no(i).xxLineNum_B.getValue());
                isError = true;
            } else {
                lineNum.add(bizMsg.B.no(i).xxLineNum_B.getValue());
            }
        }
        return isError;
    }

    private static void setDuplicateErrorB(NSAL1330CMsg bizMsg, String lineNum) {
        if (!ZYPCommonFunc.hasValue(lineNum)) {
            return;
        }
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (lineNum.equals(bizMsg.B.no(i).xxLineNum_B.getValue())) {
                bizMsg.B.no(i).xxChkBox_B.setErrorInfo(1, NSAM0664E);
                bizMsg.B.no(i).mdseCd_B.setErrorInfo(1, NSAM0664E);
                bizMsg.setMessageInfo(NSAM0664E);
            }
        }
    }

    private static void setDuplicateErrorJ(NSAL1330CMsg bizMsg, String lineNum) {
        if (!ZYPCommonFunc.hasValue(lineNum)) {
            return;
        }
        for (int i = 0; i < bizMsg.J.getValidCount(); i++) {
            if (lineNum.equals(bizMsg.J.no(i).xxLineNum_J.getValue())) {
                bizMsg.J.no(i).xxChkBox_J.setErrorInfo(1, NSAM0664E);
                bizMsg.J.no(i).mdseCd_J.setErrorInfo(1, NSAM0664E);
                bizMsg.setMessageInfo(NSAM0664E);
            }
        }
    }

    //    /**
    //     * <pre>
    //     * deriveTierInfo
    //     * @param glblMsg       NSAL1330CMsg
    //     * @param ixA           ixA
    //     * @param glblCmpyCd    glblCmpyCd
    //     * </pre>
    //     */
    //    public static void deriveTierInfo(NSAL1330SMsg glblMsg, int ixA, String glblCmpyCd) {
    //        for (int i = 0; i < glblMsg.Z.getValidCount(); i++) {
    //            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(ixA).mdlId_A.getValue())) {
    //                continue;
    //            }
    //            NSAL1330_ZSMsg zBizMsg = glblMsg.Z.no(i);
    //            if (!ZYPCommonFunc.hasValue(zBizMsg.mdlId_Z)) {
    //                continue;
    //            }
    //            if (zBizMsg.mdlId_Z.getValue().compareTo(glblMsg.A.no(ixA).mdlId_A.getValue()) != 0) {
    //                continue;
    //            }
    //            if (ZYPCommonFunc.hasValue(zBizMsg.regMtrLbCd_Z.getValue())) {
    //                continue;
    //            }
    //            if (ZYPCommonFunc.hasValue(zBizMsg.prcSvcTierTpCd_Z.getValue())) {
    //                continue;
    //            }
    //            S21SsmEZDResult ssmResult //
    //            = NSAL1330Query.getInstance().getTierInfo(glblMsg, zBizMsg, glblCmpyCd);
    //            if (ssmResult == null || ssmResult.isCodeNotFound()) {
    //                return;
    //            }
    //            NSAL1330CommonLogic.setTierInfo(glblMsg, ssmResult, i, ixA);
    //            i = i + ssmResult.getQueryResultCount() + 1;
    //        }
    //    }
    //

    /**
     * <pre>
     * @param bizMsg    NSAL1330SMsg
     * @param ssmResult S21SsmEZDResult
     * @param zIndex    zIndex
     * @param aIndex    aIndex
     * </pre>
     */
    public static void setTierInfo(NSAL1330CMsg bizMsg, S21SsmEZDResult ssmResult, int zIndex, int aIndex) {
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resList == null || resList.isEmpty()) {
            return;
        }

        //        for (int i = bizMsg.X.getValidCount() - 1; i > zIndex; i--) {
        //            EZDMsg.copy(bizMsg.Z.no(i), null, bizMsg.Z.no(i + resList.size()), null);
        //        }
        //        bizMsg.Z.setValidCount(bizMsg.Z.getValidCount() + resList.size());
        //        String prcListBandCd = (String) resList.get(0).get("PRC_LIST_BAND_CD");
        //        if (ZYPCommonFunc.hasValue(prcListBandCd) //
        //                && !ZYPCommonFunc.hasValue(bizMsg.Z.no(zIndex).prcListBandCd_Z)) {
        //            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zIndex).prcListBandCd_Z, prcListBandCd);
        //        }
        //
        //        //        NSAL1330CommonLogic.reset(bizMsg);
        //
        //        // NSAL1330CommonLogic.insertTierRow(bizMsg, index, insertLeng);

        NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(zIndex);
        BigDecimal mdlId = zBizMsg.mdlId_Z.getValue();
        String bllgMterLbCd = zBizMsg.bllgMtrLbCd_Z.getValue();

        List<Integer> tierIxList = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.X.getValidCount(); i++) {
            if (mdlId.compareTo(bizMsg.X.no(i).mdlId_X.getValue()) != 0) {
                continue;
            }
            if (!bllgMterLbCd.equals(bizMsg.X.no(i).bllgMtrLbCd_X.getValue())) {
                continue;
            }
            tierIxList.add(i);
        }
        ZYPTableUtil.deleteRows(bizMsg.X, tierIxList);

        boolean hasError = false;
        BigDecimal prevMaxCnt = BigDecimal.ZERO;
        int ixX = bizMsg.X.getValidCount();
        for (Map<String, Object> resMap : resList) {
            String tierCd = (String) resMap.get("PRC_SVC_TIER_TP_CD");
            BigDecimal rangeFrom = (BigDecimal) resMap.get("MIN_COPY_VOL_CNT");
            BigDecimal rangeTo = (BigDecimal) resMap.get("MAX_COPY_VOL_CNT");
            BigDecimal imageCharge = (BigDecimal) resMap.get("CPC_AMT_RATE");
            String tierDescTxt = (String) resMap.get("PRC_SVC_TIER_TP_DESC_TXT");

            NSAL1330_XCMsg xBizMsg = bizMsg.X.no(ixX);
            xBizMsg.clear();
            ZYPEZDItemValueSetter.setValue(xBizMsg.mdlId_X, zBizMsg.mdlId_Z);
            ZYPEZDItemValueSetter.setValue(xBizMsg.bllgMtrLbCd_X, zBizMsg.bllgMtrLbCd_Z);
            ZYPEZDItemValueSetter.setValue(xBizMsg.prcSvcTierTpCd_X, tierCd);
            ZYPEZDItemValueSetter.setValue(xBizMsg.prcSvcTierTpDescTxt_X, tierDescTxt);
            ZYPEZDItemValueSetter.setValue(xBizMsg.minCopyVolCnt_X, rangeFrom);
            ZYPEZDItemValueSetter.setValue(xBizMsg.maxCopyVolCnt_X, rangeTo);
            ZYPEZDItemValueSetter.setValue(xBizMsg.xsMtrAmtRate_X, imageCharge);

            ixX++;

            if (hasError) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(xBizMsg.minCopyVolCnt_X)//
                    || !ZYPCommonFunc.hasValue(xBizMsg.maxCopyVolCnt_X)) {
                hasError = true;
                continue;
            }
            if ((prevMaxCnt.add(BigDecimal.ONE)).compareTo(xBizMsg.minCopyVolCnt_X.getValue()) != 0) {
                hasError = true;
            }
            prevMaxCnt = xBizMsg.maxCopyVolCnt_X.getValue();

        }
        if (hasError) {
            zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NSAM0669E, new String[] {"Tier Information" });
        }
        bizMsg.X.setValidCount(ixX);
    }

    /**
     * <pre>
     * @param bizMsg    NSAL1330SMsg
     * @param ssmResult S21SsmEZDResult
     * @param ixU    uIndex
     * @param ixR    rIndex
     * </pre>
     */
    public static void setTierInfoConfig(NSAL1330CMsg bizMsg, S21SsmEZDResult ssmResult, int ixU, int ixR) {
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resList == null || resList.isEmpty()) {
            return;
        }

        NSAL1330_UCMsg uBizMsg = bizMsg.U.no(ixU);
        BigDecimal mdlId = uBizMsg.mdlId_U.getValue();
//        BigDecimal cpoSvcConfigRefPk = uBizMsg.cpoSvcConfigRefPk_U.getValue();
        // START 2017/07/12 [QC#19472, MOD]
        // BigDecimal dsContrBllgMtrPk = uBizMsg.dsContrBllgMtrPk_U.getValue();
        BigDecimal dsContrDtlPk = uBizMsg.dsContrDtlPk_U.getValue();
        // START 2017/07/12 [QC#19472, MOD]
        String bllgMterLbCd = uBizMsg.bllgMtrLbCd_U.getValue();

        List<Integer> tierIxList = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.V.getValidCount(); i++) {
//            if (mdlId.compareTo(bizMsg.V.no(i).mdlId_V.getValue()) != 0 || cpoSvcConfigRefPk.compareTo(bizMsg.V.no(i).cpoSvcConfigRefPk_V.getValue()) != 0) {
            // START 2017/07/12 [QC#19472, MOD]
            // if (mdlId.compareTo(bizMsg.V.no(i).mdlId_V.getValue()) != 0 || dsContrBllgMtrPk.compareTo(bizMsg.V.no(i).dsContrBllgMtrPk_V.getValue()) != 0) {
            if (mdlId.compareTo(bizMsg.V.no(i).mdlId_V.getValue()) != 0 || !isSameValue(dsContrDtlPk, bizMsg.V.no(i).dsContrDtlPk_V.getValue())) {
            // END   2017/07/12 [QC#19472, MOD]
                continue;
            }
            if (!bllgMterLbCd.equals(bizMsg.V.no(i).bllgMtrLbCd_V.getValue())) {
                continue;
            }
            tierIxList.add(i);
        }
        ZYPTableUtil.deleteRows(bizMsg.V, tierIxList);

        boolean hasError = false;
        BigDecimal prevMaxCnt = BigDecimal.ZERO;
        int ixV = bizMsg.V.getValidCount();
        for (Map<String, Object> resMap : resList) {
            String tierCd = (String) resMap.get("PRC_SVC_TIER_TP_CD");
            BigDecimal rangeFrom = (BigDecimal) resMap.get("MIN_COPY_VOL_CNT");
            BigDecimal rangeTo = (BigDecimal) resMap.get("MAX_COPY_VOL_CNT");
            BigDecimal imageCharge = (BigDecimal) resMap.get("CPC_AMT_RATE");
            String tierDescTxt = (String) resMap.get("PRC_SVC_TIER_TP_DESC_TXT");

            NSAL1330_VCMsg vBizMsg = bizMsg.V.no(ixV);
            vBizMsg.clear();
            ZYPEZDItemValueSetter.setValue(vBizMsg.mdlId_V, uBizMsg.mdlId_U);
            // START 2017/07/12 [QC#19472, ADD]
            ZYPEZDItemValueSetter.setValue(vBizMsg.dsContrDtlPk_V, uBizMsg.dsContrDtlPk_U);
            // END   2017/07/12 [QC#19472, ADD]
//            ZYPEZDItemValueSetter.setValue(vBizMsg.cpoSvcConfigRefPk_V, uBizMsg.cpoSvcConfigRefPk_U);
            ZYPEZDItemValueSetter.setValue(vBizMsg.dsContrBllgMtrPk_V, uBizMsg.dsContrBllgMtrPk_U);
            ZYPEZDItemValueSetter.setValue(vBizMsg.dsOrdPosnNum_V, uBizMsg.dsOrdPosnNum_U);
            ZYPEZDItemValueSetter.setValue(vBizMsg.bllgMtrLbCd_V, uBizMsg.bllgMtrLbCd_U);
            ZYPEZDItemValueSetter.setValue(vBizMsg.prcSvcTierTpCd_V, tierCd);
            ZYPEZDItemValueSetter.setValue(vBizMsg.prcSvcTierTpDescTxt_V, tierDescTxt);
            ZYPEZDItemValueSetter.setValue(vBizMsg.minCopyVolCnt_V, rangeFrom);
            ZYPEZDItemValueSetter.setValue(vBizMsg.maxCopyVolCnt_V, rangeTo);
            ZYPEZDItemValueSetter.setValue(vBizMsg.xsMtrAmtRate_V, imageCharge);

            ixV++;

            if (hasError) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(vBizMsg.minCopyVolCnt_V)//
                    || !ZYPCommonFunc.hasValue(vBizMsg.maxCopyVolCnt_V)) {
                hasError = true;
                continue;
            }
            if ((prevMaxCnt.add(BigDecimal.ONE)).compareTo(vBizMsg.minCopyVolCnt_V.getValue()) != 0) {
                hasError = true;
            }
            prevMaxCnt = vBizMsg.maxCopyVolCnt_V.getValue();

        }
        if (hasError) {
            uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NSAM0669E, new String[] {"Tier Information" });
        }
        bizMsg.V.setValidCount(ixV);
    }

    //    /**
    //     * <pre>
    //     * @param bizMsg        NSAL1330CMsg
    //     * @param glblCmpyCd    glblCmpyCd
    //     * @return
    //     * </pre>
    //     */
    //    public static boolean isUsgBllgCycleEmpty(NSAL1330CMsg bizMsg, String glblCmpyCd) {
    //        S21SsmEZDResult rsltSvc = NSAL1330Query.getInstance().getInitDataFromCpoSvc(glblCmpyCd, bizMsg);
    //
    //        if (rsltSvc == null || rsltSvc.isCodeNotFound()) {
    //            return false;
    //        }
    //        @SuppressWarnings("unchecked")
    //        List<Map<String, Object>> rsList = (List<Map<String, Object>>) rsltSvc.getResultObject();
    //
    //        String usgBllgCycleCd = (String) rsList.get(0).get("USG_BLLG_CYCLE_CD");
    //        ZYPEZDItemValueSetter.setValue(bizMsg.usgBllgCycleCd, usgBllgCycleCd);
    //
    //        return !ZYPCommonFunc.hasValue(usgBllgCycleCd);
    //    }

    /**
     * <pre>
     * @param bizMsg        NSAL1330CMsg
     * @param aBizMsg       NSAL1330_ACMsg
     * @param glblCmpyCd    glblCmpyCd
     * </pre>
     */
    public static void deriveBasePrcForNonMeter(NSAL1330CMsg bizMsg, NSAL1330_ACMsg aBizMsg, String glblCmpyCd) {
        String prcListStruTpCd = getPrcListStruTp(glblCmpyCd, aBizMsg.prcListTpCd_A.getValue());
        if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTpCd) //
                || PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(prcListStruTpCd)) {
            return;
        }

        S21SsmEZDResult rslt = NSAL1330Query.getInstance().getAnnualPrc(glblCmpyCd, bizMsg, aBizMsg);
        if (rslt == null || rslt.isCodeNotFound()) {
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsList = (List<Map<String, Object>>) rslt.getResultObject();
        if (rsList.size() > 1) {
            bizMsg.setMessageInfo(NSAM0648W, new String[] {"PRC_LIST_SVC" });
            return;
        }
        // START 2017/07/20 [QC#20003, MOD]
        // ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_PB, (BigDecimal) rsList.get(0).get("BASE_AMT"));
        BigDecimal baseAmt = (BigDecimal) rsList.get(0).get("BASE_AMT");
        BigDecimal termFromMthAot = (BigDecimal) rsList.get(0).get("TERM_FROM_MTH_AOT");
        BigDecimal termThruMthAot = (BigDecimal) rsList.get(0).get("TERM_THRU_MTH_AOT");
        BigDecimal baseBllgCycleMthAot = NSAL1330CommonLogic.getBllgCycle(bizMsg);
        // Mod Start 2017/10/19 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());

        if (ZYPCommonFunc.hasValue(termFromMthAot) && ZYPCommonFunc.hasValue(termThruMthAot)
                && isSameValue(termFromMthAot, termThruMthAot) && ZYPCommonFunc.hasValue(baseBllgCycleMthAot)) {
//            baseAmt = baseAmt.multiply(baseBllgCycleMthAot).divide(termFromMthAot);
            baseAmt = divide(multiply(baseAmt, baseBllgCycleMthAot, scale), termFromMthAot, scale);
        }
//        ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_PB, baseAmt);
        ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_PB, round(baseAmt, scale));
        // Mod End 2017/10/19 QC#21860
        // END   2017/07/20 [QC#20003, MOD]
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRateTpCd_A, PRC_RATE_TP.ANNUAL);
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

    //    private static List<BigDecimal> getMdlList(NSAL1330_ZCMsgArray zBizMsg) {
    //        List<BigDecimal> mdlList = new ArrayList<BigDecimal>();
    //        for (int i = 0; i < zBizMsg.getValidCount(); i++) {
    //            if (ZYPCommonFunc.hasValue(zBizMsg.no(i).mdlId_Z)) {
    //                BigDecimal mdlId = zBizMsg.no(i).mdlId_Z.getValue();
    //                if (mdlList.contains(mdlId)) {
    //                    continue;
    //                }
    //                mdlList.add(mdlId);
    //            }
    //        }
    //        return mdlList;
    //    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param slsDt         slsDt
     * @param bizMsg        NSAL1330CMsg
     * </pre>
     */
    public static void createPulldownList(String glblCmpyCd, String slsDt, NSAL1330CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList("PRC_TIER_SVC_OFFER", bizMsg.prcTierSvcOfferCd_KP, bizMsg.prcTierSvcOfferDescTxt_VW);
        ZYPCodeDataUtil.createPulldownList("PRC_SVC_CONTR_TP", bizMsg.prcSvcContrTpCd_L, bizMsg.prcSvcContrTpDescTxt_L);
        ZYPCodeDataUtil.createPulldownList("PRC_SVC_PLN_TP", bizMsg.prcSvcPlnTpCd_L, bizMsg.prcSvcPlnTpDescTxt_L);

        createBillWithEquipPulldown(bizMsg);
        createContractIndicatorPulldown(bizMsg); // DS_CONTR_CATG
        //        ZYPCodeDataUtil.createPulldownList("DS_CONTR_CATG", bizMsg.dsContrCatgCd_L, bizMsg.dsContrCatgDescTxt_L);

        createBllgCyclePulldown(bizMsg);

        ZYPCodeDataUtil.createPulldownList("BILL_BY_TP", bizMsg.billByTpCd_L, bizMsg.billByTpDescTxt_L);
        ZYPCodeDataUtil.createPulldownList("MTR_READ_METH", bizMsg.mtrReadMethCd_L, bizMsg.mtrReadMethDescTxt_L);
    }

    private static void createContractIndicatorPulldown(NSAL1330CMsg bizMsg) {
        S21SsmEZDResult rsltSvc = NSAL1330Query.getInstance().getContractIndicatorList();
        if (rsltSvc.isCodeNotFound()) {
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltSvc.getResultObject();
        int i = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrCatgCd_L.no(i), (String) rsltMap.get("DS_CONTR_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrCatgDescTxt_L.no(i), (String) rsltMap.get("DS_CONTR_CATG_DESC_TXT"));
            i++;
        }
    }

    private static void createBllgCyclePulldown(NSAL1330CMsg bizMsg) {
        S21SsmEZDResult rsltSvc = NSAL1330Query.getInstance().getBllgCycleList(bizMsg);
        if (rsltSvc.isCodeNotFound()) {
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltSvc.getResultObject();
        int i = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgCycleCd_L.no(i), (String) rsltMap.get("BLLG_CYCLE_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgCycleDescTxt_L.no(i), (String) rsltMap.get("BLLG_CYCLE_DESC_TXT"));

            i++;
        }
    }

    private static void createBillWithEquipPulldown(NSAL1330CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.billWithEquipFlg_L.no(0), ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.billWithEquipFlg_L.no(1), ZYPConstant.FLG_ON_Y);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxFlgNm_L.no(0), ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxFlgNm_L.no(1), ZYPConstant.FLG_ON_Y);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd glblCmpyCd
     * @param glblMsg global message
     */
    public static void setInitData(NSAL1330CMsg bizMsg, String glblCmpyCd, NSAL1330SMsg glblMsg) {

        bizMsg.xxPageNm.setValue(XX_PAGE.PAGE_SHELL.getName());

        bizMsg.X.setValidCount(0);
        bizMsg.Z.setValidCount(0);
        bizMsg.U.setValidCount(0);
        bizMsg.V.setValidCount(0);
        // add start 2017/06/12 QC#18935
        bizMsg.M.setValidCount(0);
        // add end 2017/06/12 QC#18935

        // Header
        S21SsmEZDResult rsltHdr = NSAL1330Query.getInstance().getInitDataFromShellContrHeader(bizMsg);
        if (rsltHdr.isCodeNormal()) {
            Map<String, Object> rsltMap = (Map<String, Object>) rsltHdr.getResultObject();
            setHdrRsltToBizMsg(bizMsg, rsltMap);
        }
        // Add Start 2018/12/11 QC#29423
        setRentalOrderFlag(bizMsg);
        // Add End 2018/12/11 QC#29423

        // QC#29248
        // Order Position Information
        S21SsmEZDResult rsltDsOrdPosn = NSAL1330Query.getInstance().getDsOrdPosnInfo(bizMsg);
        if (rsltDsOrdPosn.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltDsOrdPosn.getResultObject();
            setDsOrdPosnRsltToBizMsg(bizMsg, rsltList);
        } else {
            bizMsg.I.setValidCount(0);
        }

        // Detail Header(Model)
        S21SsmEZDResult rsltModelDtlHdr = NSAL1330Query.getInstance().getInitDataFromModelPricingHeader(bizMsg);
        if (rsltModelDtlHdr.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltModelDtlHdr.getResultObject();
            setModelDtlHeaderRsltToBizMsg(bizMsg, rsltList);
        } else {
            bizMsg.A.setValidCount(0);
        }

        // Detail (Model)
        S21SsmEZDResult rsltModelDtl = NSAL1330Query.getInstance().getInitDataFromModelPricingDetail(bizMsg);
        if (rsltModelDtl.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltModelDtl.getResultObject();
            setModelDtlRsltToBizMsg(bizMsg, rsltList);
        } else {
            bizMsg.X.setValidCount(0);
            bizMsg.Z.setValidCount(0);
        }

        // 2019/05/08 QC#50174 Add Start
        // 2019/06/25 QC#50895 Del Start
        //if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.ordBookFlg.getValue())) {
        // 2019/06/25 QC#50895 Del End
        // 2019/05/08 QC#50174 Add End
        // Detail Header(Config)
        S21SsmEZDResult rsltConfigDtlHdr = NSAL1330Query.getInstance().getInitDataFromConfigPricingHeader(bizMsg);
        if (rsltConfigDtlHdr.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltConfigDtlHdr.getResultObject();
            setConfigDtlHeaderRsltToBizMsg(bizMsg, rsltList, glblMsg);
        } else {
            bizMsg.R.setValidCount(0);
        }

        // Detail (Config)
        S21SsmEZDResult rsltConfigDtl = NSAL1330Query.getInstance().getInitDataFromConfigPricingDetail(bizMsg);
        if (rsltConfigDtl.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltConfigDtl.getResultObject();
            setConfigDtlRsltToBizMsg(bizMsg, rsltList);
        } else {
            bizMsg.U.setValidCount(0);
            bizMsg.V.setValidCount(0);
        }

        // 2019/05/08 QC#50174 Add Start
        // 2019/06/25 QC#50895 Del Start
        //}
        // 2019/06/25 QC#50895 Del End
        // 2019/05/08 QC#50174 Add End

        // Accessory Charge
        S21SsmEZDResult rsltAccChrg = NSAL1330Query.getInstance().getInitDataFromAccCharge(bizMsg);
        if (rsltAccChrg.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltAccChrg.getResultObject();
            setAccChrgRsltToBizMsg(bizMsg, rsltList);
        } else {
            bizMsg.J.setValidCount(0);
        }

        // Rental Eq Charge Base & Accessory
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.rntlOrdFlg.getValue())) {
            S21SsmEZDResult rsltRntlEqChrg = NSAL1330Query.getInstance().getInitDataFromRentalEqChargeBaseAcc(bizMsg);
            if (rsltRntlEqChrg.isCodeNormal()) {
                List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltRntlEqChrg.getResultObject();
                setRntlEqChrgRsltToBizMsg(bizMsg, rsltList);
            } else {
                bizMsg.B.setValidCount(0);
            }

        } else {
            bizMsg.B.setValidCount(0);
        }

        // Additional Service Charge
        S21SsmEZDResult rsltAddlSvcChrg = NSAL1330Query.getInstance().getInitDataFromAddlSvcCharge(bizMsg);
        if (rsltAddlSvcChrg.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltAddlSvcChrg.getResultObject();
            setAddlSvcChrgRsltToBizMsg(bizMsg, rsltList);
        } else {
            bizMsg.C.setValidCount(0);
        }

        // QC#29248
//        // Order Position Information
//        S21SsmEZDResult rsltDsOrdPosn = NSAL1330Query.getInstance().getDsOrdPosnInfo(bizMsg);
//        if (rsltDsOrdPosn.isCodeNormal()) {
//            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltDsOrdPosn.getResultObject();
//            setDsOrdPosnRsltToBizMsg(bizMsg, rsltList);
//        } else {
//            bizMsg.I.setValidCount(0);
//        }
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @return if import process then return true.
     */
    public static boolean isImport(NSAL1330CMsg bizMsg) {
        return isImport(bizMsg.xxPageCd.getValue());
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @return if import process then return true.
     */
    public static boolean isImport(String val) {
        return XX_PAGE.PAGE_IMPT.getCode().equals(val);
    }

    /**
     * getPrcRateTpCd
     * @param bizMsg NSAL1330CMsg
     * @param aBizMsg NSAL1330_ACMsg
     * @return prcRateTpCd
     */
    public static String getPrcRateTpCd(NSAL1330CMsg bizMsg, NSAL1330_ACMsg aBizMsg) {
        //        String prcListStruTpCd = getPrcListStruTpFromPrcCatg(aBizMsg.prcCatgCd_A.getValue());
        // QC#29248
        boolean isFleet = isFleet(bizMsg.dsContrCatgCd.getValue());
        if (!isFleet) {
            if (!ZYPCommonFunc.hasValue(aBizMsg.mdlId_A)) {
                return "";
            }
        }
        S21SsmEZDResult rslt = NSAL1330Query.getInstance().getPrcRateTpCd(bizMsg, aBizMsg, isFleet);
        if (rslt.isCodeNotFound()) {
            return "";
        }
        String prcRateTpCd = (String) rslt.getResultObject();

        return prcRateTpCd;
    }

    public static List<String> getPrcListBandCdList(String prcListBandDescTxt) {
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcListBandCd(prcListBandDescTxt);
        List<String> bandList = (List<String>) ssmResult.getResultObject();
        if (bandList == null || bandList.size() == 0) {
            return new ArrayList<String>(0);
        }
        return bandList;
    }

    /**
     * @param glblMsg NSAL1330SMsg
     * @return if Contract Indicator is "Fleet" then return true.
     */
    public static boolean isFleet(NSAL1330SMsg glblMsg) {
        return isFleet(glblMsg.dsContrCatgCd.getValue());
    }

    /**
     * @param dsContrCatgCd dsContrCatgCd
     * @return if Contract Indicator is "Fleet" then return true.
     */
    public static boolean isFleet(String dsContrCatgCd) {
        return DS_CONTR_CATG.FLEET.equals(dsContrCatgCd);
    }

    private static boolean isSameValue(BigDecimal val1, BigDecimal val2) {
        if (ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {
            return true;
        }
        return val1.compareTo(val2) == 0;
    }

    private static boolean isOverlappingTerm(NSAL1330_UCMsg uBizMsg, NSAL1330CMsg bizMsg) {
        S21SsmEZDResult ssmResult //
        = NSAL1330Query.getInstance().cntOverlappingTerm(uBizMsg, bizMsg);
        Integer cnt = (Integer) ssmResult.getResultObject();

        return cnt > 0;
    }

    private static boolean checkAcsyChrg(NSAL1330_JCMsg jBizMsg, NSAL1330CMsg bizMsg, boolean isError) {
        if (!isValidItem(//
                bizMsg.xxScrItem50Txt.getValue() //
                , jBizMsg.cpoDtlLineNum_J.getValue() //
                , jBizMsg.cpoDtlLineSubNum_J.getValue() //
                , new String[] {MDSE_TP_CTX_TP.CPO_SERVICE_CONFIGURATION_ITEMS } //
                , "acsyChrg")) {
            jBizMsg.mdseCd_J.setErrorInfo(1, NSAM0674E, new String[] {"item" });
            return true;
        }

        String svcPrcList = jBizMsg.prcCatgNm_J.getValue();
        if (!ZYPCommonFunc.hasValue(svcPrcList)) {
            return isError;
        }

        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcCatgInfoForSave(jBizMsg.prcCatgNm_J.getValue());
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcCatgInfoForSave(jBizMsg.prcCatgNm_J.getValue(), bizMsg);
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        if (ssmResult.isCodeNotFound()) {
            //error
            jBizMsg.prcCatgNm_J.setErrorInfo(1, NSAM0647E, new String[] {"Price Category", jBizMsg.prcCatgNm_J.getValue() });
            return true;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        String prcCatgCd = (String) resList.get(0).get("PRC_CATG_CD");

        ZYPEZDItemValueSetter.setValue(jBizMsg.addlBasePrcCatgCd_J, prcCatgCd);

        return isError;
    }

    private static boolean isValidItem(//
            String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum, String[] mdseTpCtxTp, String target) {
        S21SsmEZDResult ssmResult //
        = NSAL1330Query.getInstance().getCountValidItem(cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum, mdseTpCtxTp, target);
        Integer cnt = (Integer) ssmResult.getResultObject();

        return cnt > 0;
    }

    private static boolean checkAddlSvcChrg(NSAL1330CMsg bizMsg, boolean isError) {
        String prcCatgNm = bizMsg.prcCatgNm_C.getValue();
        if (!ZYPCommonFunc.hasValue(prcCatgNm)) {
            return isError;
        }

        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcCatgInfoForSave(prcCatgNm);
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcCatgInfoForSave(prcCatgNm, bizMsg);
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        if (ssmResult.isCodeNotFound()) {
            //error
            bizMsg.prcCatgNm_C.setErrorInfo(1, NSAM0647E, new String[] {"Price Category", bizMsg.prcCatgNm_C.getValue() });
            return true;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        String prcCatgCd = (String) resList.get(0).get("PRC_CATG_CD");

        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_C, prcCatgCd);

        return isError;
    }

    private static boolean checkRentalChrg(NSAL1330_BCMsg bBizMsg, NSAL1330CMsg bizMsg, boolean isError) {
        if (!isValidItem(//
                bizMsg.xxScrItem50Txt.getValue() //
                , bBizMsg.cpoDtlLineNum_B.getValue() //
                , bBizMsg.cpoDtlLineSubNum_B.getValue() //
                , null //
                , "rentalChrg")) {
            bBizMsg.mdseCd_B.setErrorInfo(1, NSAM0674E, new String[] {"item" });
            return true;
        }

        String svcPrcList = bBizMsg.prcCatgNm_B.getValue();
        if (!ZYPCommonFunc.hasValue(svcPrcList)) {
            return isError;
        }

        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcCatgInfoForSave(bBizMsg.prcCatgNm_B.getValue());
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcCatgInfoForSave(bBizMsg.prcCatgNm_B.getValue(), bizMsg);
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        if (ssmResult.isCodeNotFound()) {
            //error
            bBizMsg.prcCatgNm_B.setErrorInfo(1, NSAM0647E, new String[] {"Price Category", bBizMsg.prcCatgNm_B.getValue() });
            return true;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        String prcCatgCd = (String) resList.get(0).get("PRC_CATG_CD");

        ZYPEZDItemValueSetter.setValue(bBizMsg.prcCatgCd_B, prcCatgCd);

        return isError;
    }

    // START 2018/07/10 K.Kojima [QC#27135,MOD]
    // private static boolean checkPrcHdr(NSAL1330_ACMsg aBizMsg, boolean isError) {
    private static boolean checkPrcHdr(NSAL1330_ACMsg aBizMsg, boolean isError, NSAL1330CMsg bizMsg) {
    // END 2018/07/10 K.Kojima [QC#27135,MOD]
        String svcPrcList = aBizMsg.prcCatgNm_A.getValue();
        if (!ZYPCommonFunc.hasValue(svcPrcList)) {
            return isError;
        }

        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcCatgInfoForSave(aBizMsg.prcCatgNm_A.getValue());
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcCatgInfoForSave(aBizMsg.prcCatgNm_A.getValue(), bizMsg);
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        if (ssmResult.isCodeNotFound()) {
            //error
            aBizMsg.prcCatgNm_A.setErrorInfo(1, NSAM0647E, new String[] {"Price Category", aBizMsg.prcCatgNm_A.getValue() });
            return true;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        String prcCatgCd = (String) resList.get(0).get("PRC_CATG_CD");
        String prcListTpCd = (String) resList.get(0).get("PRC_LIST_TP_CD");

        ZYPEZDItemValueSetter.setValue(aBizMsg.prcCatgCd_A, prcCatgCd);
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcListTpCd_A, prcListTpCd);

        return isError;
    }

    private static boolean checkPrcHdrConfig(NSAL1330_RCMsg rBizMsg, NSAL1330CMsg bizMsg, boolean isError) {
        String svcPrcList = rBizMsg.prcCatgNm_R.getValue();
        if (!ZYPCommonFunc.hasValue(svcPrcList)) {
            return isError;
        }

        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcCatgInfoForSave(rBizMsg.prcCatgNm_R.getValue());
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getPrcCatgInfoForSave(rBizMsg.prcCatgNm_R.getValue(), bizMsg);
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        if (ssmResult.isCodeNotFound()) {
            //error
            rBizMsg.prcCatgNm_R.setErrorInfo(1, NSAM0647E, new String[] {"Price Category", rBizMsg.prcCatgNm_R.getValue() });
            return true;
        }

        // START 2017/07/21 [QC#20055, ADD]
        if (ZYPCommonFunc.hasValue(rBizMsg.billToCustCd_R) && ZYPCommonFunc.hasValue(rBizMsg.billToLocNum_R) && 
                // START 2018/07/10 K.Kojima [QC#27135,MOD]
                // !NSXC001001ContrValidation.checkAcctBillEligible(bizMsg.glblCmpyCd.getValue(), ZYPDateUtil.getSalesDate(), rBizMsg.billToCustCd_R.getValue(), rBizMsg.billToLocNum_R.getValue(), ONBATCH_TYPE.ONLINE)) {
                !NSXC001001ContrValidation.checkAcctBillEligible(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), rBizMsg.billToCustCd_R.getValue(), rBizMsg.billToLocNum_R.getValue(), ONBATCH_TYPE.ONLINE)) {
                // END 2018/07/10 K.Kojima [QC#27135,MOD]
            rBizMsg.billToCustCd_R.setErrorInfo(1, NSZM0698E, new String[] {"Customer", "Location" });
            rBizMsg.billToLocNum_R.setErrorInfo(1, NSZM0698E, new String[] {"Customer", "Location" });
            return true;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.sellToCustCd) && ZYPCommonFunc.hasValue(rBizMsg.billToLocNum_R) && 
                // START 2018/07/10 K.Kojima [QC#27135,MOD]
                // !NSXC001001ContrValidation.checkAcctBillEligible(bizMsg.glblCmpyCd.getValue(), ZYPDateUtil.getSalesDate(), bizMsg.sellToCustCd.getValue(), rBizMsg.billToLocNum_R.getValue(), ONBATCH_TYPE.ONLINE)) {
                !NSXC001001ContrValidation.checkAcctBillEligible(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), bizMsg.sellToCustCd.getValue(), rBizMsg.billToLocNum_R.getValue(), ONBATCH_TYPE.ONLINE)) {
                // END 2018/07/10 K.Kojima [QC#27135,MOD]
            rBizMsg.billToLocNum_R.setErrorInfo(1, NSZM0698E, new String[] {"Customer(Header)", "Location" });
            return true;
        }
        // END   2017/07/21 [QC#20055, ADD]

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        String prcCatgCd = (String) resList.get(0).get("PRC_CATG_CD");
        String prcListTpCd = (String) resList.get(0).get("PRC_LIST_TP_CD");

        ZYPEZDItemValueSetter.setValue(rBizMsg.prcCatgCd_R, prcCatgCd);
        ZYPEZDItemValueSetter.setValue(rBizMsg.prcListTpCd_R, prcListTpCd);

        return isError;
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NSAL1330CMsg
     * @return  ExclusionCtrl result : if error then return true.
     * </pre>
     */
    public static boolean checkExclusionCtrl(String glblCmpyCd, NSAL1330CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.dsContrPk)) {
            return false;
        }
        DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, bizMsg.dsContrPk);

        tMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            bizMsg.setMessageInfo(NZZM0003E);
            return true;
        }

        boolean isError = !ZYPDateUtil.isSameTimeStamp(//
                bizMsg.ezUpTime.getValue(), bizMsg.ezUpTimeZone.getValue() //
                , tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue());
        if (isError) {
            bizMsg.setMessageInfo(NZZM0003E);
        }

        return isError;
    }

    //    private static boolean hasSvcPkg(NSAL1330CMsg bizMsg, BigDecimal mdlId) {
    //        int ixA = 0;
    //        if (!DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
    //            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
    //                NSAL1330_ACMsg aScrnMsg = bizMsg.A.no(i);
    //                if (mdlId.compareTo(aScrnMsg.mdlId_A.getValue()) == 0) {
    //                    ixA = i;
    //                    break;
    //                }
    //            }
    //        }
    //        return ZYPCommonFunc.hasValue(bizMsg.A.no(ixA).prcMtrPkgPk_A);
    //    }

    /**
     * set Base Total Price
     * @param bizMsg NSAL1330CMsg
     */
    public static void setBaseTotal(NSAL1330CMsg bizMsg) {
        boolean isFleet = DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue());
        if (!isFleet) {
            setBaseTotalConfig(bizMsg);
            return;
        }
        // Set Total Price
        BigDecimal baseBllgCycle = NSAL1330CommonLogic.getBllgCycle(bizMsg);
        if (ZYPCommonFunc.hasValue(baseBllgCycle)) {
            // Add Start 2017/10/19 QC#21860
            GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
            int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
            // Add End 2017/10/19 QC#21860
            for (int aIndex = 0; aIndex < bizMsg.A.getValidCount(); aIndex++) {

                BigDecimal term = bizMsg.termMthAot.getValue();

                BigDecimal extendedBase = BigDecimal.ZERO;
                NSAL1330_ACMsg aBizMsg = bizMsg.A.no(aIndex);
                BigDecimal pbPrc = BigDecimal.ZERO;
                if (ZYPCommonFunc.hasValue(aBizMsg.xxTotPrcAmt_PB)) {
                    pbPrc = aBizMsg.xxTotPrcAmt_PB.getValue();
                }
                // START 2018/03/20 U.Kim [QC#24088,ADD]
                BigDecimal qty = aBizMsg.xxTotQtyCnt_A.getValue();
                // END 2018/03/20 U.Kim [QC#24088,ADD]
                // START 2017/10/06 [QC#20059, MOD]
                // if (isFleet) {
                //     extendedBase = pbPrc;
                // } else {
                //     if (ZYPCommonFunc.hasValue(aBizMsg.xxTotQtyCnt_A)) {
                //         extendedBase = pbPrc.multiply(aBizMsg.xxTotQtyCnt_A.getValue());
                //     }
                // }
                // START 2018/03/20 U.Kim [QC#24088,MOD]
                // extendedBase = pbPrc;
                // START 2018/06/19 K.Kojima [QC#26591,MOD]
                // extendedBase = getExtendedBase(pbPrc, qty);
                extendedBase = getExtendedBase(pbPrc, qty, bizMsg.dsContrCatgCd.getValue());
                // END 2018/06/19 K.Kojima [QC#26591,MOD]
                // END 2018/03/20 U.Kim [QC#24088,MOD]
                // END   2017/10/06 [QC#20059, MOD]

                //                BigDecimal totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
                BigDecimal totalBase = BigDecimal.ZERO;
                if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bizMsg.baseBllgCycleCd.getValue())) {
                    totalBase = extendedBase;
                } else {
                    if (ZYPCommonFunc.hasValue(extendedBase) && ZYPCommonFunc.hasValue(baseBllgCycle)) {
                        // Mod Start 2017/10/19 QC#21860
//                        totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
                        totalBase = multiply(extendedBase, divide(term, baseBllgCycle, scale), scale);
                        // Mod End 2017/10/19 QC#21860
                    }
                }

                // Mod Start 2017/10/19 QC#21860
//                ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_EB, extendedBase);
//                ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_TB, totalBase);
                ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_EB, round(extendedBase, scale));
                ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_TB, round(totalBase, scale));
                // Mod End 2017/10/19 QC#21860
            }
        }

    }

    /**
     * set Base Total Price
     * @param bizMsg NSAL1330CMsg
     */
    public static void setBaseTotalConfig(NSAL1330CMsg bizMsg) {
        // Set Total Price
        BigDecimal baseBllgCycle = NSAL1330CommonLogic.getBllgCycle(bizMsg);
        BigDecimal term = bizMsg.termMthAot.getValue();

        if (ZYPCommonFunc.hasValue(baseBllgCycle)) {
            // Add Start 2017/10/19 QC#21860
            GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
            int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
            // Add End 2017/10/19 QC#21860
            for (int ixR = 0; ixR < bizMsg.R.getValidCount(); ixR++) {

                BigDecimal extendedBase = BigDecimal.ZERO;
                NSAL1330_RCMsg rBizMsg = bizMsg.R.no(ixR);
                BigDecimal pbPrc = BigDecimal.ZERO;
                if (ZYPCommonFunc.hasValue(rBizMsg.xxTotPrcAmt_PR)) {
                    pbPrc = rBizMsg.xxTotPrcAmt_PR.getValue();
                }
                extendedBase = pbPrc;

                BigDecimal totalBase = BigDecimal.ZERO;
                if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bizMsg.baseBllgCycleCd.getValue())) {
                    totalBase = extendedBase;
                } else {
                    // Mod Start 2017/10/19 QC#21860
//                    totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
                    totalBase = multiply(extendedBase, divide(term, baseBllgCycle, scale), scale);
                    // Mod End 2017/10/19 QC#21860
                }

                // Mod Start 2017/10/19 QC#21860
//                ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_ER, extendedBase);
//                ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_TR, totalBase);
                ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_ER, round(extendedBase, scale));
                ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_TR, round(totalBase, scale));
                // Mod End 2017/10/19 QC#21860
            }
        }

    }

    //    public static void setUsgFromBizMsgToGlblMsg(NSAL1330SMsg glblMsg, NSAL1330CMsg bizMsg, String xxSmryLineFlg) {
    //        int ixGz = 0;
    //        boolean isExpand = false;
    //        for (int ixBz = 0; ixBz < bizMsg.Z.getValidCount(); ixBz++) {
    //            NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(ixBz);
    //            NSAL1330_ZSMsg zGlblMsg = glblMsg.Z.no(ixGz);
    //            if ("P".equals(zBizMsg.xxFlgNm_Z.getValue())) {
    //                EZDMsg.copy(zBizMsg, null, zGlblMsg, null);
    //                ixGz++;
    //
    //                isExpand = ZYPConstant.FLG_ON_Y.equals(zBizMsg.xxSmryLineFlg_Z.getValue());
    //                if (isExpand) {
    //                    for (; ixGz < glblMsg.Z.getValidCount(); ixGz++) {
    //                        if ("P".equals(zBizMsg.xxFlgNm_Z.getValue())) {
    //                            break;
    //                        }
    //                    }
    //                }
    //            } else {
    //                EZDMsg.copy(zBizMsg, null, zGlblMsg, null);
    //                ixGz++;
    //            }
    //            if (ixBz == bizMsg.xxNum_Z.getValueInt()) {
    //                ZYPEZDItemValueSetter.setValue(zGlblMsg.xxSmryLineFlg_Z, xxSmryLineFlg);
    //            }
    //        }
    //
    //    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @param glblMsg NSAL1330SMsg
     * @param glblCmpyCd glblCmpyCd
     */
    public static void deriveDefaultMdlSvcPrcList(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg, String glblCmpyCd) {
        // START 2017/06/20 K.Kitachi [QC#19377, MOD]
        // START 2017/10/25 K.Kojima [QC#21690,DEL]
        // S21SsmEZDResult ssmResult;
        // List<Map<String, Object>> resList;
        // Map<String, Object> resultMap;
        // END 2017/10/25 K.Kojima [QC#21690,DEL]
        String prcCatgCd = null;
        String prcCatgNm = null;
        String prcListTpCd = null;
        // START 2017/10/24 K.Kojima [QC#21690,MOD]
        // ssmResult = NSAL1330Query.getInstance().getDefaultMdlSvcPrc(bizMsg);
        // if (ssmResult.getQueryResultCount() > 0) {
        //     resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        //     resultMap = resList.get(0);
        //     prcCatgCd = (String) resultMap.get("DEF_MAINT_PRC_CATG_CD");
        //     prcCatgNm = (String) resultMap.get("PRC_CATG_NM");
        //     prcListTpCd = (String) resultMap.get("PRC_LIST_TP_CD");
        // }

        // 2018/08/24 S21_NA#25105 Mod Start
        // QC#25104 add
        // DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
        // ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        // tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);
        // if (tMsg == null //
        //         || S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, tMsg.actvFlg.getValue()) //
        //         || tMsg.effFromDt.getValue().compareTo(bizMsg.slsDt.getValue()) > 0 //
        //         || (ZYPCommonFunc.hasValue(tMsg.effThruDt) && tMsg.effThruDt.getValue().compareTo(bizMsg.slsDt.getValue()) < 0)) {
        //     return;
        // }

        // NWZC157001_xxPrcListPMsg prcList = callPricingAPIforDefaultPrcList(bizMsg, glblCmpyCd, tMsg.defMaintPrcCatgCd.getValue()); // QC#25104
        
        if (!ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_HJ)) {
            // QC#25104 add
            DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
            tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);
            if (tMsg == null //
                    || S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, tMsg.actvFlg.getValue()) //
                    || tMsg.effFromDt.getValue().compareTo(bizMsg.slsDt.getValue()) > 0 //
                    || (ZYPCommonFunc.hasValue(tMsg.effThruDt) && tMsg.effThruDt.getValue().compareTo(bizMsg.slsDt.getValue()) < 0)) {
                return;
            }

            NWZC157001_xxPrcListPMsg prcList = callPricingAPIforDefaultPrcList(bizMsg, glblCmpyCd, tMsg.defMaintPrcCatgCd.getValue()); // QC#25104
            if (prcList != null) {
                prcCatgCd = prcList.prcCatgCd.getValue();
            }
        } else{
            prcCatgCd = bizMsg.prcCatgCd_HJ.getValue();
        }
        // 2018/08/24 S21_NA#25105 Mod End
        
        // 2018/08/24 S21_NA#25105 Mod Start
        // if (prcList != null) {
        if (ZYPCommonFunc.hasValue(prcCatgCd)) {
            // prcCatgCd = prcList.prcCatgCd.getValue();
            // 2018/08/24 S21_NA#25105 Mod End
            S21SsmEZDResult ssmResult1 = NSAL1330Query.getInstance().getPriceCategoryNm(prcCatgCd, null);
            if (!ssmResult1.isCodeNotFound()) {
                List<Map<String, Object>> resListMap = (List<Map<String, Object>>) ssmResult1.getResultObject();
                if (resListMap != null && !resListMap.isEmpty()) {
                    prcCatgNm = (String) resListMap.get(0).get("PRC_CATG_NM");
                    prcListTpCd = (String) resListMap.get(0).get("PRC_LIST_TP_CD");
                }
            }
        }
        // END 2017/10/24 K.Kojima [QC#21690,MOD]
        // END 2017/06/20 K.Kitachi [QC#19377, MOD]

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
                break;
            }
            NSAL1330_ACMsg aBizMsg = bizMsg.A.no(i);
            bizMsg.xxNum_A.setValue(i);

            if (!ZYPCommonFunc.hasValue(aBizMsg.mdlId_A)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(aBizMsg.prcCatgCd_A)) {
                continue;
            }
            // START 2017/10/10 [QC#20059, ADD]
            if (isConfigLevelPriceSetting(bizMsg, aBizMsg.mdlId_A.getValue())) {
                continue;
            }
            // START 2017/10/10 [QC#20059, ADD]

            // START 2017/10/25 K.Kojima [QC#21690,DEL]
            // ssmResult = NSAL1330Query.getInstance().getDefaultMdlSvcPrcByQlfy(bizMsg, aBizMsg);
            // if (ssmResult.getQueryResultCount() == 1) {
            //     resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            //     resultMap = resList.get(0);
            //     prcCatgCd = (String) resultMap.get("PRC_CATG_CD");
            //     prcCatgNm = (String) resultMap.get("PRC_CATG_NM");
            //     prcListTpCd = (String) resultMap.get("PRC_LIST_TP_CD");
            // }
            // END 2017/10/25 K.Kojima [QC#21690,DEL]

            ZYPEZDItemValueSetter.setValue(aBizMsg.prcCatgCd_A, prcCatgCd);
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcCatgNm_A, prcCatgNm);
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcListTpCd_A, prcListTpCd);

            // START 2017/06/26 [QC#19500, MOD]
            // if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
            //     deriveMtrPkgPulldownForFleet(bizMsg);
            //     //            } else {
            //     //                deriveMtrPkgPulldownForNonFleetConfig(bizMsg, glblMsg, i);
            // }
            if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
                deriveMtrPkgPulldownForFleet(bizMsg);
            } else {
                deriveMtrPkgPulldownForNonFleet(bizMsg, glblMsg, i);
            }
            // END   2017/06/26 [QC#19500, MOD]
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRateTpCd_A, getPrcRateTpCd(bizMsg, aBizMsg)); // QC#29248
        }

        // START 2017/06/14 [QC#18934, ADD]
        if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
            return;
        }
        // END   2017/06/14 [QC#18934, ADD]

        // 2018/08/24 S21_NA#25105 Del Start
        // if (bizMsg.J.getValidCount() > 0) {
            // START 2017/06/20 K.Kitachi [QC#19377, MOD]
        //     if (ZYPCommonFunc.hasValue(bizMsg.J.no(0).addlBasePrcCatgCd_J)) {
        //         ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_HJ, bizMsg.J.no(0).addlBasePrcCatgCd_J);
        //         ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_HJ, bizMsg.J.no(0).prcCatgNm_J);
        //         return;
        //     }
            // END 2017/06/20 K.Kitachi [QC#19377, MOD]
        // }

        // for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        //     if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcCatgCd_A)) {
        //         ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_HJ, bizMsg.A.no(0).prcCatgCd_A);
        //         ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_HJ, bizMsg.A.no(0).prcCatgNm_A);
        //         return;
        //     }
        // }
        // 2018/08/24 S21_NA#25105 Del End
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_HJ, prcCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_HJ, prcCatgNm);

        // START 2019/04/01 K.Kitachi [QC#30966, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_HJ)) {
            GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
            int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
            for (int i = 0; i < bizMsg.J.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(bizMsg.J.no(i).addlBasePrcCatgCd_J)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).addlBasePrcCatgCd_J, bizMsg.prcCatgCd_HJ);
                    ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).prcCatgNm_J, bizMsg.prcCatgNm_HJ);

                    bizMsg.xxNum_B.setValue(i);
                    BigDecimal basePrc //
                    = NSAL1330CommonLogic.getPeriodicServicePriceForAccesoryCharge(bizMsg);
                    if (ZYPCommonFunc.hasValue(basePrc)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).addlBasePrcDealAmt_J, round(basePrc, scale));
                        ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).addlBasePrcDealAmt_BJ, round(basePrc, scale));
                        ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).dealPrcListPrcAmt_J, round(basePrc, scale));
                    }
                }
            }
        }
        // END 2019/04/01 K.Kitachi [QC#30966, ADD]
    }

    /**
     * @param bizMsg NSAL1330CMsg
     */
    public static void deriveMtrPkgPulldownForFleet(NSAL1330CMsg bizMsg) {
        // START 2017/06/17 [QC#18829, DEL]
        // if (!ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd)) {
        //    // does not derive price. in case of fleet, there are no price lists.
        //    return;
        // }
        // END   2017/06/17 [QC#18829, DEL]

        List<BigDecimal> mdlList = new ArrayList<BigDecimal>();
        for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
            BigDecimal mdl = bizMsg.I.no(i).mdlId_I.getValue();
            if (!mdlList.contains(mdl)) {
                mdlList.add(mdl);
            }
        }

        NSAL1330CommonLogic.createPrcMtrPkgPulldownFL(bizMsg, mdlList, bizMsg.glblCmpyCd.getValue());
        NSAL1330_ACMsg aBizMsg = bizMsg.A.no(0);
        if (ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_KP.no(0)) //
                && !ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_KP.no(1))) {
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcMtrPkgPk_A, aBizMsg.prcMtrPkgPk_KP.no(0).getValue());
            // START 2017/06/17 [QC#18829, MOD]
            // QC#4631
            // if (!ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd)) {
            //     NSAL1330CommonLogic.deriveBasePrcForNonMeter(bizMsg, aBizMsg, bizMsg.glblCmpyCd.getValue());
            // }
            String prcListStruTpCd = getPrcListStruTp(bizMsg.glblCmpyCd.getValue(), aBizMsg.prcListTpCd_A.getValue());
            if (!ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd)
                    && !PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTpCd)
                    && !PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(prcListStruTpCd)) {
                ZYPEZDItemValueSetter.setValue(aBizMsg.prcRateTpCd_A, PRC_RATE_TP.ANNUAL);
            }
            // END   2017/06/17 [QC#18829, MOD]
        } else {
            aBizMsg.prcMtrPkgPk_A.clear();
        }

        deriveTieredPricing(aBizMsg);
        NSAL1330CommonLogic.getMtrLbFL(bizMsg);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @param index selected line number.
     * @param glblCmpyCd
     * @param glblMsg
     */
    public static void deriveMtrPkgPulldownForNonFleet(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg, int index) {

        NSAL1330CommonLogic.createPrcMtrPkgPulldown(bizMsg, bizMsg.glblCmpyCd.getValue());
        NSAL1330_ACMsg aBizMsg = bizMsg.A.no(index);
        if (ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_KP.no(0)) //
                && !ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_KP.no(1))) {
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcMtrPkgPk_A, aBizMsg.prcMtrPkgPk_KP.no(0).getValue());
            // QC#4631
            if (!ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd)) {
                NSAL1330CommonLogic.deriveBasePrcForNonMeter(bizMsg, aBizMsg, bizMsg.glblCmpyCd.getValue());
            }
            //        } else {
            //            aBizMsg.prcMtrPkgPk_A.clear();
        }

        deriveTieredPricing(aBizMsg);
        NSAL1330CommonLogic.getMtrLb(bizMsg, glblMsg);
    }

    public static void deriveMtrPkgPulldownForNonFleetConfig(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg, int index) {

        NSAL1330CommonLogic.createPrcMtrPkgPulldownConfig(bizMsg, index);
        if (isImport(bizMsg)) {
            return;
        }

        NSAL1330_RCMsg rBizMsg = bizMsg.R.no(index);
        if (!isInitialEvent(bizMsg) //
                && !ZYPCommonFunc.hasValue(rBizMsg.prcMtrPkgPk_R) //
                && ZYPCommonFunc.hasValue(rBizMsg.prcMtrPkgPk_RL.no(0)) //
                && !ZYPCommonFunc.hasValue(rBizMsg.prcMtrPkgPk_RL.no(1))) {
            ZYPEZDItemValueSetter.setValue(rBizMsg.prcMtrPkgPk_R, rBizMsg.prcMtrPkgPk_RL.no(0).getValue());
            // QC#4631
            if (!ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd)) {
                NSAL1330CommonLogic.deriveBasePrcForNonMeter(bizMsg, rBizMsg, bizMsg.glblCmpyCd.getValue());
            }
            //        } else {
            //            rBizMsg.prcMtrPkgPk_R.clear();
        }

//        if (!isInitialEvent(bizMsg) || !ZYPCommonFunc.hasValue(rBizMsg.cpoSvcPrcPk_R)) {
        if (!isInitialEvent(bizMsg) || !ZYPCommonFunc.hasValue(rBizMsg.dsContrDtlPk_R)) {
            deriveTieredPricing(rBizMsg);
            NSAL1330CommonLogic.getMtrLbConfig(bizMsg, glblMsg);
        }
    }

    public static void deriveTieredPricing(NSAL1330_RCMsg rBizMsg) {
        // set Tiered Pricing
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getTierdByCatg(rBizMsg);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> tierList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (tierList != null && !tierList.isEmpty()) {
            Map<String, Object> resMap = tierList.get(0);
            String tierCd = (String) resMap.get(PRC_TIER_SVC_OFFER_CD);

            ZYPEZDItemValueSetter.setValue(rBizMsg.prcTierSvcOfferCd_R, tierCd);
            //        } else {
            //            rBizMsg.prcTierSvcOfferCd_R.clear();
        }
    }

    private static void deriveBasePrcForNonMeter(NSAL1330CMsg bizMsg, NSAL1330_RCMsg aBizMsg, String glblCmpyCd) {
        String prcListStruTpCd = getPrcListStruTp(glblCmpyCd, aBizMsg.prcListTpCd_R.getValue());
        if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTpCd) //
                || PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(prcListStruTpCd)) {
            return;
        }

        S21SsmEZDResult rslt = NSAL1330Query.getInstance().getAnnualPrc(glblCmpyCd, bizMsg, aBizMsg);
        if (rslt == null || rslt.isCodeNotFound()) {
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsList = (List<Map<String, Object>>) rslt.getResultObject();
        if (rsList.size() > 1) {
            bizMsg.setMessageInfo(NSAM0648W, new String[] {"PRC_LIST_SVC" });
            return;
        }
        // Mod Start 2017/10/19 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
//        ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_PR, (BigDecimal) rsList.get(0).get("BASE_AMT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_PR, round((BigDecimal) rsList.get(0).get("BASE_AMT"), scale));
        // Mod Start 2017/10/19 QC#21860
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRateTpCd_R, PRC_RATE_TP.ANNUAL);
    }

    /**
     * @param bizMsg
     * @param index
     */
    public static void deriveTieredPricing(NSAL1330_ACMsg aBizMsg) {
        // set Tiered Pricing
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getTierdByCatg(aBizMsg);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> tierList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (tierList != null && !tierList.isEmpty()) {
            Map<String, Object> resMap = tierList.get(0);
            String tierCd = (String) resMap.get(PRC_TIER_SVC_OFFER_CD);

            ZYPEZDItemValueSetter.setValue(aBizMsg.prcTierSvcOfferCd_A, tierCd);
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcTierSvcOfferCd_AB, tierCd);
        } else {
            aBizMsg.prcTierSvcOfferCd_A.clear();
            aBizMsg.prcTierSvcOfferCd_AB.clear();
        }
    }

    /**
     * @param bizMsg NSAL1330CMsg
     */
    public static void resetBizMsgBeforeInit(NSAL1330CMsg bizMsg) {
        String funcId = bizMsg.xxFuncId_UP.getValue();
//        BigDecimal cpoSvcDtlPk = bizMsg.cpoSvcDtlPk.getValue();
        BigDecimal dsContrPk = bizMsg.dsContrPk.getValue();
        String refCpoOrdNum = bizMsg.refCpoOrdNum.getValue();
        BigDecimal shellLineNum = bizMsg.shellLineNum.getValue();
        String xxPageCd = bizMsg.xxPageCd.getValue();

        bizMsg.clear();

        ZYPEZDItemValueSetter.setValue(bizMsg.xxFuncId_UP, funcId);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsContrPk, dsContrPk);
        ZYPEZDItemValueSetter.setValue(bizMsg.refCpoOrdNum, refCpoOrdNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.shellLineNum, shellLineNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageCd, xxPageCd);
    }

    /**
     * @param prcCatgNmItem EZDCStringItem
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd glblCmpyCd
     * @param glblMsg
     */
    public static void deriveFromMdlPrcCatgNm(EZDCStringItem prcCatgNmItem, NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg, String glblCmpyCd) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        String prcCatgNm = "";
        if (ZYPCommonFunc.hasValue(prcCatgNmItem)) {
            prcCatgNm = prcCatgNmItem.getValue();
        }
        String prcListTpCd = "";
        List<String> prcList = getPrcList(bizMsg, PRC_CTX_TP.SERVICE_PRICING);
        if (prcList == null) {
            return;
        }
        List<String> prcNmList = new ArrayList<String>();
        String rtnPrcCatgCd = "";
        for (String prcCatgCd : prcList) {
            prcCatgNm = "";
            if (ZYPCommonFunc.hasValue(prcCatgCd)) {
                S21SsmEZDResult ssmResult1 = NSAL1330Query.getInstance().getPriceCategoryNm(prcCatgCd, prcCatgNmItem.getValue());

                if (!ssmResult1.isCodeNotFound()) {
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult1.getResultObject();
                    if (resList != null && !resList.isEmpty()) {
                        prcCatgNm = (String) resList.get(0).get("PRC_CATG_NM");
                        prcNmList.add(prcCatgNm);
                        rtnPrcCatgCd = prcCatgCd;
                        prcListTpCd = (String) resList.get(0).get("PRC_LIST_TP_CD");
                    }
                }
            }
        }
        if (prcNmList.size() == 0) {
            prcCatgNmItem.setErrorInfo(1, NSAM0647E, new String[] {"Price List", prcCatgNmItem.getValue() });
            return;
        }
        //        S21SsmEZDResult rslt = NSAL1330Query.getInstance().getPrcCatgNmForOnBlur(prcCatgNmItem);
        //        if (rslt == null || rslt.isCodeNotFound()) {
        //            prcCatgNmItem.setErrorInfo(1, NSAM0647E, new String[] {"Price List", prcCatgNm });
        //            return;
        //        }
        //        @SuppressWarnings("unchecked")
        //        List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();

        if (prcNmList.size() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return;
        }

        int ix = bizMsg.xxNum_A.getValueInt();
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(ix).prcCatgCd_A, rtnPrcCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(ix).prcCatgNm_A, prcNmList.get(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(ix).prcListTpCd_A, prcListTpCd);

        if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
            NSAL1330CommonLogic.deriveMtrPkgPulldownForFleet(bizMsg);
        } else {
            NSAL1330CommonLogic.deriveMtrPkgPulldownForNonFleet(bizMsg, glblMsg, ix);
        }
    }

    /**
     * @param prcCatgNmItem EZDCStringItem
     * @param bizMsg NSAL1330CMsg
     * @param glblCmpyCd glblCmpyCd
     * @param glblMsg
     */
    public static void deriveFromMdlPrcCatgNmConfig(EZDCStringItem prcCatgNmItem, NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg, String glblCmpyCd) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        String prcCatgNm = "";
        if (ZYPCommonFunc.hasValue(prcCatgNmItem)) {
            prcCatgNm = prcCatgNmItem.getValue();
        }
        String prcListTpCd = "";
        List<String> prcList = getPrcList(bizMsg, PRC_CTX_TP.SERVICE_PRICING);
        if (prcList == null) {
            return;
        }
        List<String> prcNmList = new ArrayList<String>();
        String rtnPrcCatgCd = "";
        for (String prcCatgCd : prcList) {
            prcCatgNm = "";
            if (ZYPCommonFunc.hasValue(prcCatgCd)) {
                S21SsmEZDResult ssmResult1 = NSAL1330Query.getInstance().getPriceCategoryNm(prcCatgCd, prcCatgNmItem.getValue());

                if (!ssmResult1.isCodeNotFound()) {
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult1.getResultObject();
                    if (resList != null && !resList.isEmpty()) {
                        prcCatgNm = (String) resList.get(0).get("PRC_CATG_NM");
                        prcNmList.add(prcCatgNm);
                        rtnPrcCatgCd = prcCatgCd;
                        prcListTpCd = (String) resList.get(0).get("PRC_LIST_TP_CD");
                    }
                }
            }
        }
        if (prcNmList.size() == 0) {
            prcCatgNmItem.setErrorInfo(1, NSAM0647E, new String[] {"Price List", prcCatgNmItem.getValue() });
            return;
        }

        if (prcNmList.size() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return;
        }

        int ix = bizMsg.xxNum_A.getValueInt();
        ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ix).prcCatgCd_R, rtnPrcCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ix).prcCatgNm_R, prcNmList.get(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ix).prcListTpCd_R, prcListTpCd);

        NSAL1330CommonLogic.deriveMtrPkgPulldownForNonFleetConfig(bizMsg, glblMsg, ix);
        //        if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
        //            NSAL1330CommonLogic.deriveMtrPkgPulldownForFleet(bizMsg);
        //        } else {
        //            NSAL1330CommonLogic.deriveMtrPkgPulldownForNonFleetConfig(bizMsg, glblMsg, ix, glblCmpyCd);
        //        }
    }

    private static List<String> getPrcList(NSAL1330CMsg bizMsg, String prcCtxTpCd) {
        List<String> prcList = new ArrayList<String>();
        /**************************************************
         * call [NWZC157001] : Pricing API
         **************************************************/
        final NWZC157001 pricAPI = new NWZC157001();

        final NWZC157001PMsg pMsg = getPrcingAPiPMsg(bizMsg, prcCtxTpCd);

        // [NWZC157001] : Pricing API
        pricAPI.execute(pMsg, ONBATCH_TYPE.ONLINE);

        // has API errors.
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                return prcList;
            }
        }

        if (pMsg.xxPrcList.getValidCount() == 0) {
            bizMsg.setMessageInfo(NSAM0647E, new String[] {"Price List", "PRC_CTX_TP_CD:" + prcCtxTpCd });
            return prcList;
        }
        for (int j = 0; j < pMsg.xxPrcList.getValidCount(); j++) {
            String prcCatgCd = pMsg.xxPrcList.no(j).prcCatgCd.getValue();
            prcList.add(prcCatgCd);
        }
        return prcList;
    }

    /**
     * @param bizMsg
     * @param pMsg
     */
    private static NWZC157001PMsg getPrcingAPiPMsg(NSAL1330CMsg bizMsg, String prcCtxTpCd) {
        String lineOfBusinessCd = getLob(bizMsg);

        NWZC157001PMsg pMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, bizMsg.prcBaseDt);
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // ZYPEZDItemValueSetter.setValue(pMsg.orgRqstDelyDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.orgRqstDelyDt, bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.prcCtxTpCd, prcCtxTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, lineOfBusinessCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, bizMsg.dsAcctNum);
        pMsg.csmpNum.clear();
        pMsg.dlrRefNum.clear();
        pMsg.prcContrNum.clear();
        pMsg.coaBrCd.clear();
        ZYPEZDItemValueSetter.setValue(pMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_ON_Y);

        return pMsg;
    }

    /**
     * @param bizMsg
     * @return
     */
    private static String getLob(NSAL1330CMsg bizMsg) {
        S21SsmEZDResult ssmResult;
        String lineOfBusinessCd = "";

        ssmResult = NSAL1330Query.getInstance().getLineOfBusinessCd(bizMsg);

        if (!ssmResult.isCodeNotFound()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resList != null && !resList.isEmpty()) {
                lineOfBusinessCd = (String) resList.get(0).get("LINE_BIZ_TP_CD");
            }
        }
        return lineOfBusinessCd;
    }

    /**
     * @param prcCatgNmItem EZDCStringItem
     * @param bizMsg NSAL1330CMsg
     * @param target target area
     * @param prcCtxTpCd PRC_CTX_TP
     * @param glblCmpyCd glblCmpyCd
     */
    public static void deriveFromAsryChrgPrcCatgNm(//
            EZDCStringItem prcCatgNmItem, NSAL1330CMsg bizMsg, String target, String prcCtxTpCd, String glblCmpyCd) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        String prcCatgNm = "";
        if (ZYPCommonFunc.hasValue(prcCatgNmItem)) {
            prcCatgNm = prcCatgNmItem.getValue();
        }

        List<String> prcList = getPrcList(bizMsg, prcCtxTpCd);
        List<String> prcNmList = new ArrayList<String>();
        String rtnPrcCatgCd = "";
        for (String prcCatgCd : prcList) {
            prcCatgNm = "";
            if (!ZYPCommonFunc.hasValue(prcCatgCd)) {
                continue;
            }
            S21SsmEZDResult ssmResult1 = NSAL1330Query.getInstance().getPriceCategoryNm(prcCatgCd, prcCatgNmItem.getValue());
            if (ssmResult1.isCodeNotFound() || !ssmResult1.isCodeNormal()) {
                continue;
            }

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult1.getResultObject();
            if (resList == null || resList.isEmpty()) {
                continue;
            }
            for (Map<String, Object> rsMap : resList) {
                prcCatgNm = (String) rsMap.get("PRC_CATG_NM");
                prcNmList.add(prcCatgNm);
                rtnPrcCatgCd = prcCatgCd;
                if (prcNmList.size() > 1) {
                    break;
                }
            }
        }
        if (prcNmList.size() == 0) {
            prcCatgNmItem.setErrorInfo(1, NSAM0647E, new String[] {"Price List", prcCatgNmItem.getValue() });
            return;
        }
        //
        if (prcNmList.size() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return;
        }

        if ("B".equals(target)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_HB, rtnPrcCatgCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_HB, prcNmList.get(0));

            if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_HB)) {
                for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                    if (!ZYPCommonFunc.hasValue(bizMsg.B.no(i).addlBasePrcCatgCd_B)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).addlBasePrcCatgCd_B, rtnPrcCatgCd);
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).prcCatgNm_B, bizMsg.prcCatgNm_HB);

                        ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_B, new BigDecimal(i));
                        NSAL1330CommonLogic.getPrcConfName(bizMsg);

                        //                        NSAL1330CommonLogic.getAccessoryChargePrcConfigPullDown(bizMsg, i, glblCmpyCd);
                    }
                }
                NSAL1330CommonLogic.setDefaultPrcForRental(bizMsg);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_HJ, rtnPrcCatgCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_HJ, prcNmList.get(0));

            if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_HJ)) {
                // Add Start 2017/10/19 QC#21860
                GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
                int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
                // Add End 2017/10/19 QC#21860
                for (int i = 0; i < bizMsg.J.getValidCount(); i++) {
                    if (!ZYPCommonFunc.hasValue(bizMsg.J.no(i).addlBasePrcCatgCd_J)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).addlBasePrcCatgCd_J, rtnPrcCatgCd);
                        ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).prcCatgNm_J, bizMsg.prcCatgNm_HJ);

                        bizMsg.xxNum_B.setValue(i);
                        BigDecimal basePrc //
                        = NSAL1330CommonLogic.getPeriodicServicePriceForAccesoryCharge(bizMsg);
                        if (ZYPCommonFunc.hasValue(basePrc)) {
                            // Mod Start 2017/10/19 QC#21860
//                            ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).addlBasePrcDealAmt_J, basePrc);
//                            ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).addlBasePrcDealAmt_BJ, basePrc);
//                            ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).dealPrcListPrcAmt_J, basePrc);
                            ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).addlBasePrcDealAmt_J, round(basePrc, scale));
                            ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).addlBasePrcDealAmt_BJ, round(basePrc, scale));
                            ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).dealPrcListPrcAmt_J, round(basePrc, scale));
                            // Mod End 2017/10/19 QC#21860
                        }
                    }
                }
            }
        }
    }

    /**
     * @param prcCatgNmItem EZDCStringItem
     * @param bizMsg NSAL1330CMsg
     * @param prcCtxTpCd PRC_CTX_TP
     * @param target String J/B
     */
    public static void deriveFromAsryChrgPPrcCatgNm(//
            EZDCStringItem prcCatgNmItem, NSAL1330CMsg bizMsg, String prcCtxTpCd, String target) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        String prcCatgNm = "";
        if (ZYPCommonFunc.hasValue(prcCatgNmItem)) {
            prcCatgNm = prcCatgNmItem.getValue();
        }
        //
        List<String> prcList = getPrcList(bizMsg, prcCtxTpCd);
        List<String> prcNmList = new ArrayList<String>();
        String rtnPrcCatgCd = "";
        for (String prcCatgCd : prcList) {
            prcCatgNm = "";
            if (ZYPCommonFunc.hasValue(prcCatgCd)) {
                S21SsmEZDResult ssmResult1 //
                = NSAL1330Query.getInstance().getPriceCategoryNm(prcCatgCd, prcCatgNmItem.getValue());

                if (!ssmResult1.isCodeNotFound()) {
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult1.getResultObject();
                    if (resList != null && !resList.isEmpty()) {
                        prcCatgNm = (String) resList.get(0).get("PRC_CATG_NM");
                        prcNmList.add(prcCatgNm);
                        rtnPrcCatgCd = prcCatgCd;
                    }
                }
            }
        }
        if (prcNmList.size() == 0) {
            prcCatgNmItem.setErrorInfo(1, NSAM0647E, new String[] {"Price List", prcCatgNmItem.getValue() });
            return;
        }
        //

        if (prcNmList.size() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return;
        }

        int ix = bizMsg.xxNum_B.getValueInt();
        if ("B".equals(target)) {
            NSAL1330_BCMsg bBizMsg = bizMsg.B.no(ix);
            ZYPEZDItemValueSetter.setValue(bBizMsg.addlBasePrcCatgCd_B, rtnPrcCatgCd);
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcCatgNm_B, prcNmList.get(0));

            getPrcConfName(bizMsg);

        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).addlBasePrcCatgCd_J, rtnPrcCatgCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).prcCatgNm_J, prcNmList.get(0));
        }

        setDefaultPrcForRental(bizMsg);
    }

    /**
     * @param bizMsg
     * @param ix
     * @param bBizMsg
     */
    public static void getPrcConfName(NSAL1330CMsg bizMsg) {

        int ix = bizMsg.xxNum_B.getValueInt();
        NSAL1330_BCMsg bBizMsg = bizMsg.B.no(ix);
        if (!ZYPCommonFunc.hasValue(bBizMsg.prcCatgNm_B)) {
            bBizMsg.prcListEquipConfigNm_B.clear();
            bBizMsg.prcListEquipConfigNum_B.clear();
            return;
        }

        S21SsmEZDResult ssmResult //
        = NSAL1330Query.getInstance().getPrcConfName(bizMsg, ix);
        if (!ssmResult.isCodeNotFound()) {
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resList != null && !resList.isEmpty()) {
                ZYPEZDItemValueSetter.setValue(//
                        bBizMsg.prcListEquipConfigNum_B //
                        , (BigDecimal) resList.get(0).get("PRC_LIST_EQUIP_CONFIG_NUM"));
                ZYPEZDItemValueSetter.setValue(//
                        bBizMsg.prcListEquipConfigNm_B //
                        , (String) resList.get(0).get("PRC_LIST_EQUIP_CONFIG_NM"));
            }
        }
    }

    /**
     * @param prcCatgNmItem EZDCStringItem
     * @param bizMsg NSAL1330CMsg
     */
    public static void deriveFromAddlChrgPrcCatgNm(EZDCStringItem prcCatgNmItem, NSAL1330CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        String prcCatgNm = "";
        if (ZYPCommonFunc.hasValue(prcCatgNmItem)) {
            prcCatgNm = prcCatgNmItem.getValue();
        }

        List<String> prcList = getPrcList(bizMsg, PRC_CTX_TP.SPECIAL_CHARGE);
        List<String> prcNmList = new ArrayList<String>();
        String rtnPrcCatgCd = "";
        for (String prcCatgCd : prcList) {
            prcCatgNm = "";
            if (ZYPCommonFunc.hasValue(prcCatgCd)) {
                S21SsmEZDResult ssmResult1 = NSAL1330Query.getInstance().getPriceCategoryNm(prcCatgCd, prcCatgNmItem.getValue());

                if (!ssmResult1.isCodeNotFound()) {
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult1.getResultObject();
                    if (resList != null && !resList.isEmpty()) {
                        prcCatgNm = (String) resList.get(0).get("PRC_CATG_NM");
                        prcNmList.add(prcCatgNm);
                        rtnPrcCatgCd = prcCatgCd;
                    }
                }
            }
        }
        if (prcNmList.size() == 0) {
            prcCatgNmItem.setErrorInfo(1, NSAM0647E, new String[] {"Price List", prcCatgNmItem.getValue() });
            return;
        }
        //
        if (prcNmList.size() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_C, rtnPrcCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_C, prcNmList.get(0));
    }

    /**
     * @param mdseCdItem EZDCStringItem
     * @param bizMsg NSAL1330CMsg
     */
    public static void deriveFromAddlChrgItemCd(EZDCStringItem mdseCdItem, NSAL1330CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        String mdseCd = "";
        if (ZYPCommonFunc.hasValue(mdseCdItem)) {
            mdseCd = mdseCdItem.getValue();
        }
        boolean isLike = false;
        if (mdseCd.indexOf("%") >= 0 || mdseCd.indexOf("_") >= 0) {
            isLike = true;
        }
        S21SsmEZDResult rslt //
        = NSAL1330Query.getInstance().getMdseInfoForOnBlurAddlChrg(mdseCdItem, MDSE_TP_CTX_TP.CPO_SERVICE_ADDITIONAL_CHARGE_ITEMS, isLike);
        if (rslt == null || rslt.isCodeNotFound()) {
            mdseCdItem.setErrorInfo(1, NSAM0647E, new String[] {"DS Merchandise Information", mdseCd });
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();

        if (rsltList.size() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return;
        }

        int ix = bizMsg.xxNum_C.getValueInt();
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(ix).mdseCd_CI //
                , rsltList.get(0).get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(ix).mdseDescShortTxt_CI //
                , rsltList.get(0).get("MDSE_DESC_SHORT_TXT"));

        ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_A, bizMsg.xxNum_C);
        getPeriodicServicePrice(bizMsg, bizMsg.glblCmpyCd.getValue(), PRC_CTX_TP.SPECIAL_CHARGE);
    }

    /**
     * @param mdseCdItem EZDCStringItem
     * @param bizMsg NSAL1330CMsg
     */
    public static void deriveFromCoveredUnitItemCd(EZDCStringItem mdseCdItem, NSAL1330CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        //        String mdseNm = "";
        //        if (ZYPCommonFunc.hasValue(mdseCdItem)) {
        //            mdseNm = mdseCdItem.getValue();
        //        }
        S21SsmEZDResult rslt //
        = NSAL1330Query.getInstance().getMdseInfoForOnBlurCoveredUnit(//
//                mdseCdItem, bizMsg.xxScrItem50Txt.getValue(), bizMsg.shellLineNum.getValue(), MDSE_TP_CTX_TP.CPO_SERVICE_CONFIGURATION_ITEMS);
                mdseCdItem, bizMsg.refCpoOrdNum.getValue(), bizMsg.shellLineNum.getValue(), MDSE_TP_CTX_TP.CPO_SERVICE_CONFIGURATION_ITEMS);
        if (rslt == null || rslt.isCodeNotFound()) {
            mdseCdItem.setErrorInfo(1, NSAM0672E, new String[] {"Customer Purchase Order Detail" });
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();

        if (rsltList.size() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return;
        }

        int ix = bizMsg.xxNum_C.getValueInt();
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(ix).mdseCd_CU, rsltList.get(0).get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(ix).mdseDescShortTxt_CU, rsltList.get(0).get("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(ix).xxLineNum_C, rsltList.get(0).get("DPLY_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(ix).cpoDtlLineNum_C, rsltList.get(0).get("CPO_DTL_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(ix).cpoDtlLineSubNum_C, rsltList.get(0).get("CPO_DTL_LINE_SUB_NUM"));
    }

    /**
     * @param mdseCdItem EZDCStringItem
     * @param bizMsg NSAL1330CMsg
     */
    public static void deriveFromCoveredItemItemCd(EZDCStringItem mdseCdItem, NSAL1330CMsg bizMsg, String target) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        //        String mdseNm = "";
        //        if (ZYPCommonFunc.hasValue(mdseCdItem)) {
        //            mdseNm = mdseCdItem.getValue();
        //        }
        String mdseTpCtxTp = "";
        if (!"B".equals(target)) {
            mdseTpCtxTp = MDSE_TP_CTX_TP.CPO_SERVICE_CONFIGURATION_ITEMS;
        }
        S21SsmEZDResult rslt //
        = NSAL1330Query.getInstance().getMdseInfoForOnBlurCoveredItem(//
//                mdseCdItem, bizMsg.xxScrItem50Txt.getValue(), bizMsg.shellLineNum.getValue(), mdseTpCtxTp);
                mdseCdItem, bizMsg.refCpoOrdNum.getValue(), bizMsg.shellLineNum.getValue(), mdseTpCtxTp);
        if (rslt == null || rslt.isCodeNotFound()) {
            mdseCdItem.setErrorInfo(1, NSAM0672E, new String[] {"Customer Purchase Order Detail" });
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();

        if (rsltList.size() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return;
        }

        int ix = bizMsg.xxNum_B.getValueInt();
        if ("B".equals(target)) { // Rental Eq.
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ix).shellLineNum_B, bizMsg.shellLineNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ix).mdseCd_B, rsltList.get(0).get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ix).mdseDescShortTxt_B, rsltList.get(0).get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ix).xxLineNum_B, rsltList.get(0).get("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ix).cpoDtlLineNum_B, rsltList.get(0).get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(ix).cpoDtlLineSubNum_B, rsltList.get(0).get("CPO_DTL_LINE_SUB_NUM"));

        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).shellLineNum_J, bizMsg.shellLineNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).mdseCd_J, rsltList.get(0).get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).mdseDescShortTxt_J, rsltList.get(0).get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).xxLineNum_J, rsltList.get(0).get("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).cpoDtlLineNum_J, rsltList.get(0).get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).cpoDtlLineSubNum_J, rsltList.get(0).get("CPO_DTL_LINE_SUB_NUM"));

            BigDecimal basePrc //
            = NSAL1330CommonLogic.getPeriodicServicePriceForAccesoryCharge(bizMsg);
            if (ZYPCommonFunc.hasValue(basePrc)) {
                // Mod Start 2017/10/19 QC#21860
                GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
                int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
//                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).addlBasePrcDealAmt_J, basePrc);
//                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).addlBasePrcDealAmt_BJ, basePrc);
//                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).dealPrcListPrcAmt_J, basePrc);
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).addlBasePrcDealAmt_J, round(basePrc, scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).addlBasePrcDealAmt_BJ, round(basePrc, scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(ix).dealPrcListPrcAmt_J, round(basePrc, scale));
                // Mod End 2017/10/19 QC#21860
            }
        }
        if (NSAL1330CommonLogic.acsryDuplicateCheck(bizMsg)) {
            return; // error
        }
    }

    /**
     * getBasePrcForNonMeter
     * @param bizMsg NSAL1330CMsg
     * @param aBizMsg NSAL1330_ACMsg
     * @return base price
     */
    public static BigDecimal getBasePrcForNonMeter(NSAL1330CMsg bizMsg, NSAL1330_ACMsg aBizMsg) {
        BigDecimal basePrc = BigDecimal.ZERO;
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getBasePrcForNonMeter(bizMsg, aBizMsg);
        // Add Start 2017/10/19 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21860

        if (!ssmResult.isCodeNotFound()) {
            // START 2017/07/20 [QC#20003, MOD]
            // basePrc = (BigDecimal) ssmResult.getResultObject();
            Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();
            basePrc = (BigDecimal) result.get("BASE_AMT");
            String prcRateTpCd = (String) result.get("PRC_RATE_TP_CD");
            if (PRC_RATE_TP.ANNUAL.equals(prcRateTpCd)) {
                BigDecimal termFromMthAot = (BigDecimal) result.get("TERM_FROM_MTH_AOT");
                BigDecimal termThruMthAot = (BigDecimal) result.get("TERM_THRU_MTH_AOT");
                BigDecimal baseBllgCycleMthAot = NSAL1330CommonLogic.getBllgCycle(bizMsg);

                if (ZYPCommonFunc.hasValue(termFromMthAot) && ZYPCommonFunc.hasValue(termThruMthAot)
                        && isSameValue(termFromMthAot, termThruMthAot) && ZYPCommonFunc.hasValue(baseBllgCycleMthAot)) {
                    // Mod Start 2017/10/19 QC#21860
//                    basePrc = basePrc.multiply(baseBllgCycleMthAot).divide(termFromMthAot);
                    basePrc = divide(multiply(basePrc, baseBllgCycleMthAot, scale), termFromMthAot, scale);
                    // Mod End 2017/10/19 QC#21860
                }
            }
            // END   2017/07/20 [QC#20003, MOD]
        }
        // Mod Start 2017/10/19 QC#21860
//        return basePrc;
        return round(basePrc, scale);
        // Mod End 2017/10/19 QC#21860
    }

    /**
     * getPeriodicServicePriceForAccesoryCharge
     * @param bizMsg NSAL1330CMsg
     * @return basePrc
     */
    public static BigDecimal getPeriodicServicePriceForAccesoryCharge(NSAL1330CMsg bizMsg) {
        NSAL1330_JCMsg jBizMsg = bizMsg.J.no(bizMsg.xxNum_B.getValueInt());
        if (!ZYPCommonFunc.hasValue(jBizMsg.mdseCd_J)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(jBizMsg.addlBasePrcCatgCd_J)) {
            return null;
        }

        BigDecimal basePrc = BigDecimal.ZERO;
        S21SsmEZDResult ssmResult //
        = NSAL1330Query.getInstance().getPeriodicServicePriceFromPriceList(bizMsg);

        if (!ssmResult.isCodeNotFound()) {
            basePrc = (BigDecimal) ssmResult.getResultObject();
        }
        return basePrc;
    }

    public static BigDecimal getPeriodicBase(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        if (isFleet(glblMsg)) {
            return null;
        }
        int ixA = bizMsg.xxNum_A.getValueInt();
        BigDecimal mdlId = bizMsg.A.no(ixA).mdlId_A.getValue();

        BigDecimal basePrc = BigDecimal.ZERO;
        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
            NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(i);
            if (mdlId.compareTo(zBizMsg.mdlId_Z.getValue()) != 0) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(zBizMsg.prcListBandDescTxt_Z)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(zBizMsg.regMtrLbCd_Z)) {
                continue;
            }
            basePrc = basePrc.add(getBandBasePrc(bizMsg, i));
        }
        return basePrc;
    }

    public static BigDecimal getPeriodicBaseConfig(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        if (isFleet(glblMsg)) {
            return null;
        }
        int ixR = bizMsg.xxNum_A.getValueInt();
        BigDecimal mdlId = bizMsg.R.no(ixR).mdlId_R.getValue();
//        BigDecimal cpoSvcConfigRefPk = bizMsg.R.no(ixR).cpoSvcConfigRefPk_R.getValue();
        BigDecimal dsContrDtlPk = bizMsg.R.no(ixR).dsContrDtlPk_R.getValue();

        BigDecimal basePrc = BigDecimal.ZERO;
        for (int ixU = 0; ixU < bizMsg.U.getValidCount(); ixU++) {
            NSAL1330_UCMsg uBizMsg = bizMsg.U.no(ixU);
            if (mdlId.compareTo(uBizMsg.mdlId_U.getValue()) != 0) {
                continue;
            }
//            if (cpoSvcConfigRefPk.compareTo(uBizMsg.cpoSvcConfigRefPk_U.getValue()) != 0) {
            if (dsContrDtlPk.compareTo(uBizMsg.dsContrDtlPk_U.getValue()) != 0) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(uBizMsg.prcListBandDescTxt_U)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(uBizMsg.regMtrLbCd_U)) {
                continue;
            }
            basePrc = basePrc.add(getBandBasePrcConfig(bizMsg, ixU));
        }
        return basePrc;
    }

    private static BigDecimal getBandBasePrc(NSAL1330CMsg bizMsg, int ixZ) {
        S21SsmEZDResult ssmResult //
        = NSAL1330Query.getInstance().getBandBasePrc(bizMsg, ixZ);

        BigDecimal basePrc = BigDecimal.ZERO;
        if (!ssmResult.isCodeNotFound()) {
            basePrc = (BigDecimal) ssmResult.getResultObject();
        }
        if (ZYPCommonFunc.hasValue(basePrc)) {
            return basePrc;
        }
        return BigDecimal.ZERO;
    }

    private static BigDecimal getBandBasePrcConfig(NSAL1330CMsg bizMsg, int ixU) {
        S21SsmEZDResult ssmResult //
        = NSAL1330Query.getInstance().getBandBasePrcConfig(bizMsg, ixU);

        BigDecimal basePrc = BigDecimal.ZERO;
        if (!ssmResult.isCodeNotFound()) {
            basePrc = (BigDecimal) ssmResult.getResultObject();
        }
        if (ZYPCommonFunc.hasValue(basePrc)) {
            return basePrc;
        }
        return BigDecimal.ZERO;
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @param glblMsg NSAL1330SMsg
     */
    public static void afterBandPopupProc(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        S21SsmEZDResult ssmResult = null;

        int aIndex = bizMsg.xxNum_A.getValueInt();

        // Set Total Price
        BigDecimal baseBllgCycle = NSAL1330CommonLogic.getBllgCycle(bizMsg);
        NSAL1330_ACMsg aBizMsg = bizMsg.A.no(aIndex);
        if (ZYPCommonFunc.hasValue(baseBllgCycle)) {

            //            BigDecimal periodicBase = bizMsg.A.no(aIndex).xxTotPrcAmt_PB.getValue();
            BigDecimal periodicBase = NSAL1330CommonLogic.getPeriodicBase(bizMsg, glblMsg); // QC#10379

            if (ZYPCommonFunc.hasValue(periodicBase) && ZYPCommonFunc.hasValue(baseBllgCycle)) {

                BigDecimal qty = bizMsg.A.no(aIndex).xxTotQtyCnt_A.getValue();
                BigDecimal term = bizMsg.termMthAot.getValue();

                if (!ZYPCommonFunc.hasValue(qty)) {
                    qty = BigDecimal.ZERO;
                }
                // START 2017/10/06 [QC#20059, MOD]
                // BigDecimal extendedBase = periodicBase.multiply(qty);
                // START 2018/03/20 U.Kim [QC#24088, MOD]
                // BigDecimal extendedBase = periodicBase;
                // START 2018/06/19 K.Kojima [QC#26591,MOD]
                // BigDecimal extendedBase = getExtendedBase(periodicBase, qty);
                BigDecimal extendedBase = getExtendedBase(periodicBase, qty, bizMsg.dsContrCatgCd.getValue());
                // END 2018/06/19 K.Kojima [QC#26591,MOD]
                // END 2018/03/20 U.Kim [QC#24088, MOD]
                // END   2017/10/06 [QC#20059, MOD]
                BigDecimal totalBase = BigDecimal.ZERO;
                // Mod Start 2017/10/19 QC#21860
                GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
                int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
                if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bizMsg.baseBllgCycleCd.getValue())) {
                    totalBase = extendedBase;
                } else {
//                    totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
                    totalBase = multiply(extendedBase, divide(term, baseBllgCycle, scale), scale);
                }

//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_PB, periodicBase);
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_EB, extendedBase);
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_TB, totalBase);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_PB, round(periodicBase, scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_EB, round(extendedBase, scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_TB, round(totalBase, scale));
                // Mod End 2017/10/19 QC#21860
            }
        }

        // START 2017/10/26 [QC#21556, MOD]
        if (DS_CONTR_CATG.AGGREGATE.equals(bizMsg.dsContrCatgCd.getValue()) 
                && ZYPCommonFunc.hasValue(bizMsg.dsContrPk_AD)) {
            ssmResult = NSAL1330Query.getInstance().getTierInfo_BandSearchForAggAddMachine(bizMsg);
        } else {
            if (!PRC_LIST_TP.SERVICE_TIERS.equals(aBizMsg.prcListTpCd_A.getValue()) //
                    && !ZYPCommonFunc.hasValue(aBizMsg.prcTierSvcOfferCd_A)) {
                return;
            }
            if (!DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
                ssmResult = NSAL1330Query.getInstance().getTierInfo_BandSearch(bizMsg);
            }
        }
        // END   2017/10/26 [QC#21556, MOD]

        if (ssmResult == null || ssmResult.isCodeNotFound()) {
            return;
        }

        NSAL1330CommonLogic.setTierInfo(bizMsg, ssmResult, bizMsg.xxNum_Z.getValueInt(), aIndex);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @param glblMsg NSAL1330SMsg
     */
    public static void afterBandPopupProcConfig(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        S21SsmEZDResult ssmResult = null;

        int ixR = bizMsg.xxNum_A.getValueInt();

        // Set Total Price
        BigDecimal baseBllgCycle = NSAL1330CommonLogic.getBllgCycle(bizMsg);
        NSAL1330_RCMsg rBizMsg = bizMsg.R.no(ixR);
        if (ZYPCommonFunc.hasValue(baseBllgCycle)) {

            BigDecimal periodicBase = NSAL1330CommonLogic.getPeriodicBaseConfig(bizMsg, glblMsg);

            if (ZYPCommonFunc.hasValue(periodicBase) && ZYPCommonFunc.hasValue(baseBllgCycle)) {

                BigDecimal term = bizMsg.termMthAot.getValue();

                BigDecimal extendedBase = periodicBase;
                BigDecimal totalBase = BigDecimal.ZERO;
                // Mod Start 2017/10/19 QC#21860
                GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
                int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
                if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bizMsg.baseBllgCycleCd.getValue())) {
                    totalBase = extendedBase;
                } else {
//                    totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
                    totalBase = multiply(extendedBase, divide(term, baseBllgCycle, scale), scale);
                }

//                ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ixR).xxTotPrcAmt_PR, periodicBase);
//                ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ixR).xxTotPrcAmt_ER, extendedBase);
//                ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ixR).xxTotPrcAmt_TR, totalBase);
                ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ixR).xxTotPrcAmt_PR, round(periodicBase, scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ixR).xxTotPrcAmt_ER, round(extendedBase, scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ixR).xxTotPrcAmt_TR, round(totalBase, scale));
                // Mod End 2017/10/19 QC#21860
            }
        }

        // START 2017/10/26 [QC#21556, MOD]
        if (DS_CONTR_CATG.AGGREGATE.equals(bizMsg.dsContrCatgCd.getValue()) 
                && ZYPCommonFunc.hasValue(bizMsg.dsContrPk_AD)) {
            ssmResult = NSAL1330Query.getInstance().getTierInfo_BandSearchForConfigAggAddMachine(bizMsg);
        } else {
            if (!PRC_LIST_TP.SERVICE_TIERS.equals(rBizMsg.prcListTpCd_R.getValue()) //
                    && !ZYPCommonFunc.hasValue(rBizMsg.prcTierSvcOfferCd_R)) {
                return;
            }
            if (!DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
                ssmResult = NSAL1330Query.getInstance().getTierInfo_BandSearchConfig(bizMsg);
            }
        }
        // END   2017/10/26 [QC#21556, MOD]

        if (ssmResult == null || ssmResult.isCodeNotFound()) {
            return;
        }

        // ixU = bizMsg.xxNum_Z.getValueInt()
        NSAL1330CommonLogic.setTierInfoConfig(bizMsg, ssmResult, bizMsg.xxNum_Z.getValueInt(), ixR);
    }

    public static void setDefaultHeaderRentalEqPrcList(NSAL1330CMsg bizMsg) {
        // 2018/08/24 S21_NA#25105 Add Start
        if(ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_HB)){
            return;
        }
        // 2018/08/24 S21_NA#25105 Add End
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getDefaultHeaderRentalEqPrcListFromCPO(bizMsg);
        if (ssmResult.getQueryResultCount() == 1) {
            // 2018/08/24 S21_NA#25105 Mod Start
            // String prcCatgNm = (String) ssmResult.getResultObject();
            Map<String, String> resultMap = (Map<String, String>) ssmResult.getResultObject();
            String prcCatgNm = resultMap.get("PRC_CATG_NM");
            String prcCatgCd = resultMap.get("PRC_CATG_CD");
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_HB, prcCatgCd);
            // 2018/08/24 S21_NA#25105 Mod End
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_HB, prcCatgNm);
        }
    }

    public static String getPrcRateTpCd(NSAL1330CMsg bizMsg, NSAL1330_RCMsg rBizMsg) {
        if (!ZYPCommonFunc.hasValue(rBizMsg.mdlId_R)) {
            return "";
        }
        S21SsmEZDResult rslt = NSAL1330Query.getInstance().getPrcRateTpCd(bizMsg, rBizMsg);
        if (rslt.isCodeNotFound()) {
            return "";
        }
        String prcRateTpCd = (String) rslt.getResultObject();

        return prcRateTpCd;
    }

    public static BigDecimal getBasePrcForNonMeter(NSAL1330CMsg bizMsg, NSAL1330_RCMsg aBizMsg) {
        BigDecimal basePrc = BigDecimal.ZERO;
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getBasePrcForNonMeter(bizMsg, aBizMsg);
        // Add Start 2017/10/19 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21860

        if (!ssmResult.isCodeNotFound()) {
            // START 2017/07/20 [QC#20003, MOD]
            // basePrc = (BigDecimal) ssmResult.getResultObject();
            Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();
            basePrc = (BigDecimal) result.get("BASE_AMT");
            String prcRateTpCd = (String) result.get("PRC_RATE_TP_CD");
            if (PRC_RATE_TP.ANNUAL.equals(prcRateTpCd)) {
                BigDecimal termFromMthAot = (BigDecimal) result.get("TERM_FROM_MTH_AOT");
                BigDecimal termThruMthAot = (BigDecimal) result.get("TERM_THRU_MTH_AOT");
                BigDecimal baseBllgCycleMthAot = NSAL1330CommonLogic.getBllgCycle(bizMsg);

                if (ZYPCommonFunc.hasValue(termFromMthAot) && ZYPCommonFunc.hasValue(termThruMthAot)
                        && isSameValue(termFromMthAot, termThruMthAot) && ZYPCommonFunc.hasValue(baseBllgCycleMthAot)) {
                    // Mod Start 2017/10/19 QC#21860
//                    basePrc = basePrc.multiply(baseBllgCycleMthAot).divide(termFromMthAot);
                    basePrc = divide(multiply(basePrc, baseBllgCycleMthAot, scale), termFromMthAot, scale);
                    // Mod End 2017/10/19 QC#21860
                }
            }
            // END   2017/07/20 [QC#20003, MOD]
        }
        // Mod Start 2017/10/19 QC#21860
//        return basePrc;
        return round(basePrc, scale);
        // Mod End 2017/10/19 QC#21860
    }

    public static void copyConfigToModel(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        Map<BigDecimal, NSAL1330_ACMsg> defaultMap = new HashMap<BigDecimal, NSAL1330_ACMsg>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1330_ACMsg aBizMsg = new NSAL1330_ACMsg();
            EZDBMsg.copy(bizMsg.A.no(i), null, aBizMsg, null);
            defaultMap.put(aBizMsg.mdlId_A.getValue(), aBizMsg);
        }
        BigDecimal mdlId = BigDecimal.ONE.negate();
        int ixA = 0;
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        int ixZ = 0;
        bizMsg.Z.clear();
        bizMsg.Z.setValidCount(0);
        int ixX = 0;
        bizMsg.X.clear();
        bizMsg.X.setValidCount(0);

        Map<BigDecimal, BigDecimal> modelMap = new HashMap<BigDecimal, BigDecimal>(bizMsg.R.getValidCount());
        for (int ixR = 0; ixR < bizMsg.R.getValidCount(); ixR++) {
            NSAL1330_RCMsg rBizMsg = bizMsg.R.no(ixR);
            if (!ZYPCommonFunc.hasValue(rBizMsg.mdlId_R)) {
                continue;
            }
            BigDecimal mdlIdR = rBizMsg.mdlId_R.getValue();
            if (!modelMap.containsKey(mdlIdR)) {
                modelMap.put(mdlIdR, BigDecimal.ZERO);
            }
            BigDecimal cnt = modelMap.get(mdlIdR);
            modelMap.put(mdlIdR, cnt.add(BigDecimal.ONE));

            if (mdlId.compareTo(mdlIdR) != 0) {
                NSAL1330_ACMsg aBizMsg = bizMsg.A.no(ixA);
//                if (!ZYPCommonFunc.hasValue(rBizMsg.cpoSvcPrcPk_R)) {
                if (!ZYPCommonFunc.hasValue(rBizMsg.dsContrDtlPk_R)) {
                    NSAL1330_ACMsg defBizMsg = defaultMap.get(mdlIdR);
                    if (defBizMsg != null) {
                        EZDBMsg.copy(defBizMsg, null, aBizMsg, null);
                        bizMsg.xxNum_A.setValue(ixA);
                        deriveMtrPkgPulldownForNonFleet(bizMsg, glblMsg, ixA);
                    }
                } else {
                    EZDBMsg.copy(rBizMsg, "R", aBizMsg, "A");
                    EZDBMsg.copy(rBizMsg, "RL", aBizMsg, "KP");
                    EZDBMsg.copy(rBizMsg, "RL", aBizMsg, "VW");
                    EZDBMsg.copy(rBizMsg, "PR", aBizMsg, "PB");
                }
//                aBizMsg.cpoSvcPrcPk_A.clear();
                aBizMsg.dsContrDtlPk_A.clear();
                ixA++;
                mdlId = mdlIdR;
                String dsOrdPosnNum = rBizMsg.dsOrdPosnNum_R.getValue();
                if (!ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
                    continue;
                }

//                if (ZYPCommonFunc.hasValue(rBizMsg.cpoSvcPrcPk_R)) {
                if (ZYPCommonFunc.hasValue(rBizMsg.dsContrDtlPk_R)) {
                    for (int ixU = 0; ixU < bizMsg.U.getValidCount(); ixU++) {
                        if (mdlId.compareTo(bizMsg.U.no(ixU).mdlId_U.getValue()) == 0 //
                                && dsOrdPosnNum.equals(bizMsg.U.no(ixU).dsOrdPosnNum_U.getValue())) {
                            NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(ixZ);
                            EZDBMsg.copy(bizMsg.U.no(ixU), "U", zBizMsg, "Z");
                            EZDBMsg.copy(bizMsg.U.no(ixU), "UB", zBizMsg, "ZB");
//                            zBizMsg.cpoSvcUsgPrcPk_Z.clear();
                            zBizMsg.dsContrDtlPk_Z.clear();
                            ixZ++;
                        }
                    }

                    for (int ixV = 0; ixV < bizMsg.V.getValidCount(); ixV++) {
                        if (mdlId.compareTo(bizMsg.V.no(ixV).mdlId_V.getValue()) == 0 //
                                && dsOrdPosnNum.equals(bizMsg.V.no(ixV).dsOrdPosnNum_V.getValue())) {
                            NSAL1330_XCMsg xBizMsg = bizMsg.X.no(ixX);
                            EZDBMsg.copy(bizMsg.V.no(ixV), "V", xBizMsg, "X");
//                            xBizMsg.cpoSvcUsgPrcPk_X.clear();
                            xBizMsg.contrXsCopyPk_X.clear();
                            ixX++;
                        }
                    }
                    bizMsg.Z.setValidCount(ixZ);
                    bizMsg.X.setValidCount(ixX);

                } else {
                    ixZ = bizMsg.Z.getValidCount();
                    ixX = bizMsg.X.getValidCount();
                }
            }
        }
        bizMsg.A.setValidCount(ixA);
        //        bizMsg.Z.setValidCount(ixZ);
        //        bizMsg.X.setValidCount(ixX);

        // Add Start 2017/10/19 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21860
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1330_ACMsg aBizMsg = bizMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotQtyCnt_A, modelMap.get(aBizMsg.mdlId_A.getValue()));

            BigDecimal baseBllgCycle = getBllgCycle(bizMsg);
            if (ZYPCommonFunc.hasValue(aBizMsg.xxTotPrcAmt_PB)) {
                // set Price
                BigDecimal periodicBase = aBizMsg.xxTotPrcAmt_PB.getValue();
                // START 2017/10/06 [QC#20059, DEL]
                // BigDecimal qty = aBizMsg.xxTotQtyCnt_A.getValue();
                // END   2017/10/06 [QC#20059, DEL]
                // START 2018/03/20 U.Kim [QC#24088,ADD]
                BigDecimal qty = aBizMsg.xxTotQtyCnt_A.getValue();
                // END 2018/03/20 U.Kim [QC#24088,ADD]
                if (ZYPCommonFunc.hasValue(baseBllgCycle)) {

                    BigDecimal term = bizMsg.termMthAot.getValue();
                    BigDecimal extendedBase = BigDecimal.ZERO;
                    BigDecimal totalBase = BigDecimal.ZERO;

                    // START 2017/10/06 [QC#20059, MOD]
                    // if (ZYPCommonFunc.hasValue(periodicBase) && ZYPCommonFunc.hasValue(qty)) {
                    //    extendedBase = periodicBase.multiply(qty);
                    // }
                    if (ZYPCommonFunc.hasValue(periodicBase)) {
                        // START 2018/03/20 U.Kim [QC#24088,MOD]
                        // extendedBase = periodicBase;
                        // START 2018/06/19 K.Kojima [QC#26591,MOD]
                        // extendedBase = getExtendedBase(periodicBase, qty);
                        extendedBase = getExtendedBase(periodicBase, qty, bizMsg.dsContrCatgCd.getValue());
                        // END 2018/06/19 K.Kojima [QC#26591,MOD]
                        // END 2018/03/20 U.Kim [QC#24088,MOD]
                    }
                    // END   2017/10/06 [QC#20059, MOD]

                    // Mod Start 2017/10/19 QC#21860
                    if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bizMsg.baseBllgCycleCd.getValue())) {
                        totalBase = extendedBase;
                    } else {
//                        totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
                        totalBase = multiply(extendedBase, divide(term, baseBllgCycle, scale), scale);
                    }

//                    ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_EB, extendedBase);
//                    ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_TB, totalBase);
                    ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_EB, round(extendedBase, scale));
                    ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_TB, round(totalBase, scale));
                    // Mod End 2017/10/19 QC#21860
                }
            }
        }
    }

    public static void deleteTierInfo(NSAL1330CMsg bizMsg, NSAL1330_ZCMsg zBizMsg, boolean isFleet) {
        List<Integer> delList = new ArrayList<Integer>(bizMsg.X.getValidCount());
        for (int i = 0; i < bizMsg.X.getValidCount(); i++) {
            if (isFleet || zBizMsg.mdlId_Z.getValue().compareTo(bizMsg.X.no(i).mdlId_X.getValue()) == 0) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.X, delList);
    }

    public static void deleteTierInfo(NSAL1330CMsg bizMsg, NSAL1330_UCMsg uBizMsg) {
        List<Integer> delList = new ArrayList<Integer>(bizMsg.V.getValidCount());
        for (int i = 0; i < bizMsg.V.getValidCount(); i++) {
            if (uBizMsg.mdlId_U.getValue().compareTo(bizMsg.V.no(i).mdlId_V.getValue()) == 0 //
//                    && uBizMsg.cpoSvcConfigRefPk_U.getValue().compareTo(bizMsg.V.no(i).cpoSvcConfigRefPk_V.getValue()) == 0) {
                    // START 2017/07/12 [QC#19472, MOD]
                    // && uBizMsg.dsContrBllgMtrPk_U.getValue().compareTo(bizMsg.V.no(i).dsContrBllgMtrPk_V.getValue()) == 0) {
                    && isSameValue(uBizMsg.dsContrDtlPk_U.getValue(), bizMsg.V.no(i).dsContrDtlPk_V.getValue())) {
                    // END   2017/07/12 [QC#19472, MOD]
                delList.add(i);
            }
            // START 2017/07/12 [QC#19472, DEL]
            // ZYPTableUtil.deleteRows(bizMsg.V, delList);
            // END   2017/07/12 [QC#19472, DEL]
        }
        // START 2017/07/12 [QC#19472, MOD]
        ZYPTableUtil.deleteRows(bizMsg.V, delList);
        // END   2017/07/12 [QC#19472, MOD]
    }

    // QC#16141 ADD START
    public static SELL_TO_CUSTTMsg getDsAcctCustInfo(NSAL1330CMsg bizMsg, boolean isCallNameFld, EZDCStringItem targetItem, String msgParm, String glblCmpyCd) {
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
     * Common Process For Derive From Bill To
     * @param bizMsg NSAL1330CMsg
     * @param dsAcctCustTMsg SELL_TO_CUSTTMsg
     * @param glblCmpyCd glblCmpyCd
     */
    public static void cmnProcForDeriveFromBillTo(NSAL1330CMsg bizMsg, SELL_TO_CUSTTMsg dsAcctCustTMsg, String glblCmpyCd, boolean isConfig) {

        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, dsAcctCustTMsg.sellToCustCd.getValue(), glblCmpyCd);
        if (nMZC610001PMsg == null) {
            return;
        }
        if (isConfig) {
            setBillToInfoConfig(bizMsg, dsAcctCustTMsg, nMZC610001PMsg);
        } else {
            setBillToInfoMeter(bizMsg, dsAcctCustTMsg, nMZC610001PMsg);
        }
    }

    /**
     * Call Customer Information Get API For Default Mode
     * @param bizMsg NSAL1330CMsg
     * @param dsAcctNum Direct Sales Account Number
     * @param glblCmpyCd glblCmpyCd
     * @return NMZC610001PMsg
     */
    public static NMZC610001PMsg callCustInfoGetApiForDefaultMode(NSAL1330CMsg bizMsg, String dsAcctNum, String glblCmpyCd) {
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

    private static SELL_TO_CUSTTMsgArray getDsSlsAcctCustForName(NSAL1330CMsg bizMsg, String custNm, String glblCmpyCd) {

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

    private static SELL_TO_CUSTTMsgArray getDsSlsAcctCustForAcct(NSAL1330CMsg bizMsg, String acctNum, String glblCmpyCd) {

        final SELL_TO_CUSTTMsg condition = new SELL_TO_CUSTTMsg();
        // 2018/03/12 S21_NA#24090 Mod Start
        // condition.setSQLID("004");
        condition.setSQLID("507");
        // 2018/03/12 S21_NA#24090 Mod End
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("sellToCustCd01", acctNum);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    private static String getDsTrxRuleTpCd(NSAL1330CMsg bizMsg, String glblCmpyCd) {

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();
        // START 2019/06/21 W.Honda [QC#50842, MOD]
//        condition.setSQLID("003");
//        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
//        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
//        condition.setConditionValue("dsOrdCatgCd01", bizMsg.dsOrdCatgCd.getValue());
//        condition.setConditionValue("dsOrdTpCd01", bizMsg.dsOrdTpCd.getValue());
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
//        condition.setSQLID("002");
//        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
//        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
//        condition.setConditionValue("dsOrdCatgCd01", bizMsg.dsOrdCatgCd.getValue());
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

    private static void setBillToInfoConfig(NSAL1330CMsg bizMsg, SELL_TO_CUSTTMsg dsAcctCustTMsg, NMZC610001PMsg nMZC610001PMsg) {

        int selectIndex = bizMsg.xxNum_A.getValueInt();

        ZYPEZDItemValueSetter.setValue(bizMsg.R.no(selectIndex).dsAcctNm_R, dsAcctCustTMsg.dsAcctNm); // 2017/03/29 S21_NA#18171 Mod
        ZYPEZDItemValueSetter.setValue(bizMsg.R.no(selectIndex).billToCustCd_R, dsAcctCustTMsg.sellToCustCd);

        ZYPEZDItemValueSetter.setValue(bizMsg.R.no(selectIndex).billToLocNum_R, nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd);
        Map<String, String> billToInfo = NSAL1330CommonLogic.getBillToInfo(bizMsg, bizMsg.R.no(selectIndex).billToLocNum_R, "Location Code");
        if (billToInfo == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.R.no(selectIndex).xxGenlFldAreaTxt_R, billToInfo.get("BILL_TO_ADDR"));
    }

    private static void setBillToInfoMeter(NSAL1330CMsg bizMsg, SELL_TO_CUSTTMsg dsAcctCustTMsg, NMZC610001PMsg nMZC610001PMsg) {

        int selectIndex = bizMsg.xxNum_Z.getValueInt();

        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(selectIndex).dsAcctNm_U, dsAcctCustTMsg.dsAcctNm); // 2017/03/29 S21_NA#18171 Mod
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(selectIndex).billToCustCd_U, dsAcctCustTMsg.sellToCustCd);

        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(selectIndex).billToLocNum_U, nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd);
        Map<String, String> billToInfo = NSAL1330CommonLogic.getBillToInfo(bizMsg, bizMsg.U.no(selectIndex).billToLocNum_U, "Location Code");
        if (billToInfo == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(selectIndex).xxGenlFldAreaTxt_U, billToInfo.get("BILL_TO_ADDR"));
    }

    /**
     * Get Bill To Info
     * @param bizMsg NSAL1330CMsg
     * @param custLocNum Customer Location Number
     * @param msgParm Message Parameter
     * @return Bill To Info
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getBillToInfo(NSAL1330CMsg bizMsg, EZDCStringItem custLocNum, String msgParm) {

        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getBillToCustInfoList(bizMsg, custLocNum.getValue());

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

    // QC#16141 ADD END

    /**
     * insert new row
     * @param list
     * @param insertRow
     * @return new line
     */
    private static EZDMsg insertNewLine(EZDMsgArray list, int insertRow) {

        if (list.getValidCount() >= list.length()) {
            return null;
        }

        for (int i = list.getValidCount() - 1; insertRow <= i; i--) {
            EZDMsg.copy(list.get(i), null, list.get(i + 1), null);
        }
        list.get(insertRow).clear();
        list.setValidCount(list.getValidCount() + 1);
        return list.get(insertRow);
    }

    /**
     * get Apply All count(Z)
     * @param zBizMsgArray
     * @param mdlId
     * @return Apply All count
     */
    public static int getApplyAllCountZ(NSAL1330_ZCMsgArray zBizMsgArray, BigDecimal mdlId) {
        int applyAllCount = 0;
        for (int i = 0; i < zBizMsgArray.getValidCount(); i++) {
            NSAL1330_ZCMsg zcMsg = zBizMsgArray.no(i);
            if (!ZYPCommonFunc.hasValue(zcMsg.mdlId_Z)) {
                continue;
            }
            if (zcMsg.mdlId_Z.getValue().compareTo(mdlId) == 0) {
                applyAllCount++;
            }
        }
        return applyAllCount;
    }

    /**
     * get Apply All count(X)
     * @param xBizMsgArray
     * @param mdlId
     * @return Apply All count
     */
    public static int getApplyAllCountX(NSAL1330_XCMsgArray xBizMsgArray, BigDecimal mdlId) {
        int applyAllCount = 0;
        for (int i = 0; i < xBizMsgArray.getValidCount(); i++) {
            NSAL1330_XCMsg xcMsg = xBizMsgArray.no(i);
            if (!ZYPCommonFunc.hasValue(xcMsg.mdlId_X)) {
                continue;
            }
            if (xcMsg.mdlId_X.getValue().compareTo(mdlId) == 0) {
                applyAllCount++;
            }
        }
        return applyAllCount;
    }

    /**
     * get target message List(U)
     * @param ucMsgArray
     * @param mdlId
     * @param dsOrdPosnNum
     * @param applyAllCount
     * @param dsContrDtlPk
     * @param bizMsg
     * @return NSAL1330_UCMsg List
     */
    public static List<NSAL1330_UCMsg> getTargetMsgListU(NSAL1330_UCMsgArray ucMsgArray, BigDecimal mdlId, String dsOrdPosnNum, int applyAllCount, BigDecimal dsContrDtlPk, NSAL1330CMsg bizMsg) {
        List<NSAL1330_UCMsg> targetList = new ArrayList<NSAL1330_UCMsg>();
        for (int i = 0; i < ucMsgArray.getValidCount(); i++) {
            NSAL1330_UCMsg copyUCMsg = new NSAL1330_UCMsg();
            if (!ZYPCommonFunc.hasValue(ucMsgArray.no(i).mdlId_U) || !ZYPCommonFunc.hasValue(ucMsgArray.no(i).dsOrdPosnNum_U)) {
                continue;
            }
            if (ucMsgArray.no(i).mdlId_U.getValue().compareTo(mdlId) == 0 && ucMsgArray.no(i).dsOrdPosnNum_U.getValue().equals(dsOrdPosnNum)) {
                if (targetList.size() == applyAllCount) {
                    break;
                }
                EZDBMsg.copy(ucMsgArray.no(i), "U", copyUCMsg, "U");
                EZDBMsg.copy(ucMsgArray.no(i), "UB", copyUCMsg, "UB");
                EZDBMsg.copy(ucMsgArray.no(i), "UL", copyUCMsg, "UL");
                targetList.add(copyUCMsg);
            }
        }
        while (targetList.size() < applyAllCount) {
            NSAL1330_UCMsg addUCMsg = new NSAL1330_UCMsg();
            ZYPEZDItemValueSetter.setValue(addUCMsg.dsOrdPosnNum_U, dsOrdPosnNum);
//            ZYPEZDItemValueSetter.setValue(addUCMsg.cpoSvcConfigRefPk_U, cpoSvcConfigRefPk);
            ZYPEZDItemValueSetter.setValue(addUCMsg.dsContrDtlPk_U, dsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(addUCMsg.mdlId_U, mdlId);
            ZYPEZDItemValueSetter.setValue(addUCMsg.actvFlg_U, ZYPConstant.FLG_ON_Y);

            // del start 2017/06/12 QC#18931
            //ZYPEZDItemValueSetter.setValue(addUCMsg.billToCustCd_U, bizMsg.dsAcctNum);
            //ZYPEZDItemValueSetter.setValue(addUCMsg.dsAcctNm_U, bizMsg.dsAcctNm); // 2017/03/29 S21_NA#18171 Mod
            //ZYPEZDItemValueSetter.setValue(addUCMsg.billToLocNum_U, bizMsg.soldToCustLocCd);
            //ZYPEZDItemValueSetter.setValue(addUCMsg.xxGenlFldAreaTxt_U, bizMsg.xxGenlFldAreaTxt_BI);
            // del end 2017/06/12 QC#18931

            targetList.add(addUCMsg);
        }
        return targetList;
    }

    /**
     * get target message List(V)
     * @param vcMsgArray
     * @param mdlId
     * @param dsOrdPosnNum
     * @param applyAllCount
     * @param contrXsCopyPk_V
     * @param bizMsg
     * @return NSAL1330_VCMsg List
     */
    public static List<NSAL1330_VCMsg> getTargetMsgListV(NSAL1330_VCMsgArray vcMsgArray, BigDecimal mdlId, String dsOrdPosnNum, int applyAllCount, BigDecimal contrXsCopyPk_V, NSAL1330CMsg bizMsg) {
        List<NSAL1330_VCMsg> targetList = new ArrayList<NSAL1330_VCMsg>();
        for (int i = 0; i < vcMsgArray.getValidCount(); i++) {
            NSAL1330_VCMsg copyVCMsg = new NSAL1330_VCMsg();
            if (!ZYPCommonFunc.hasValue(vcMsgArray.no(i).mdlId_V) || !ZYPCommonFunc.hasValue(vcMsgArray.no(i).dsOrdPosnNum_V)) {
                continue;
            }
            if (vcMsgArray.no(i).mdlId_V.getValue().compareTo(mdlId) == 0 && vcMsgArray.no(i).dsOrdPosnNum_V.getValue().equals(dsOrdPosnNum)) {
                if (targetList.size() == applyAllCount) {
                    break;
                }
                EZDBMsg.copy(vcMsgArray.no(i), "V", copyVCMsg, "V");
                targetList.add(copyVCMsg);
            }
        }
        while (targetList.size() < applyAllCount) {
            NSAL1330_VCMsg addVCMsg = new NSAL1330_VCMsg();
            ZYPEZDItemValueSetter.setValue(addVCMsg.dsOrdPosnNum_V, dsOrdPosnNum);
//            ZYPEZDItemValueSetter.setValue(addVCMsg.cpoSvcConfigRefPk_V, cpoSvcConfigRefPk);
            ZYPEZDItemValueSetter.setValue(addVCMsg.contrXsCopyPk_V, contrXsCopyPk_V);
            ZYPEZDItemValueSetter.setValue(addVCMsg.actvFlg_V, ZYPConstant.FLG_ON_Y);

            targetList.add(addVCMsg);
        }
        return targetList;
    }

    /**
     * replace target message List(U)
     * @param ucMsgArray NSAL1330_UCMsgArray
     * @param mdlId BigDecimal
     * @param dsOrdPosnNum String
     * @param targetList List<NSAL1330_UCMsg>
     */
    public static void replaceTargetMsgArrayU(NSAL1330_UCMsgArray ucMsgArray, BigDecimal mdlId, String dsOrdPosnNum, List<NSAL1330_UCMsg> targetList) {
        int startIdx = getStartIdxU(ucMsgArray, mdlId, dsOrdPosnNum);
        int validCount = ucMsgArray.getValidCount();
        List<Integer> delList = new ArrayList<Integer>();

        for (int i = 0; i < ucMsgArray.getValidCount(); i++) {
            NSAL1330_UCMsg ucMsg = ucMsgArray.no(i);
            if (!ZYPCommonFunc.hasValue(ucMsg.mdlId_U) || !ZYPCommonFunc.hasValue(ucMsg.dsOrdPosnNum_U)) {
                continue;
            }
            if (ucMsg.mdlId_U.getValue().compareTo(mdlId) == 0 && ucMsg.dsOrdPosnNum_U.getValue().equals(dsOrdPosnNum)) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(ucMsgArray, delList);

        for (NSAL1330_UCMsg target : targetList) {
            NSAL1330_UCMsg ucMsg = (NSAL1330_UCMsg) insertNewLine(ucMsgArray, startIdx);
            EZDBMsg.copy(target, "U", ucMsg, "U");
            EZDBMsg.copy(target, "UB", ucMsg, "UB");
            EZDBMsg.copy(target, "UL", ucMsg, "UL");
            startIdx++;
        }

        validCount = validCount - delList.size() + targetList.size();
        ucMsgArray.setValidCount(validCount);
    }

    /**
     * replace target message List(V)
     * @param ucMsgArray NSAL1330_VCMsgArray
     * @param mdlId BigDecimal
     * @param dsOrdPosnNum String
     * @param targetList List<NSAL1330_VCMsg>
     */
    public static void replaceTargetMsgArrayV(//
            NSAL1330_VCMsgArray vcMsgArray, BigDecimal mdlId, String dsOrdPosnNum, List<NSAL1330_VCMsg> targetList) {
        int startIdx = getStartIdxV(vcMsgArray, mdlId, dsOrdPosnNum);
        int validCount = vcMsgArray.getValidCount();
        List<Integer> delList = new ArrayList<Integer>();

        if (targetList == null || targetList.size() == 0) {
            for (int i = 0; i < vcMsgArray.getValidCount(); i++) {
                if (mdlId.compareTo(vcMsgArray.no(i).mdlId_V.getValue()) != 0) {
                    continue;
                }
                delList.add(i);
            }
            ZYPTableUtil.deleteRows(vcMsgArray, delList);
            return;
        }

        for (int i = 0; i < vcMsgArray.getValidCount(); i++) {
            NSAL1330_VCMsg vcMsg = vcMsgArray.no(i);
            if (!ZYPCommonFunc.hasValue(vcMsg.mdlId_V) || !ZYPCommonFunc.hasValue(vcMsg.dsOrdPosnNum_V)) {
                continue;
            }
            if (vcMsg.mdlId_V.getValue().compareTo(mdlId) == 0 && vcMsg.dsOrdPosnNum_V.getValue().equals(dsOrdPosnNum)) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(vcMsgArray, delList);

        for (NSAL1330_VCMsg target : targetList) {
            NSAL1330_VCMsg vcMsg = (NSAL1330_VCMsg) insertNewLine(vcMsgArray, startIdx);
            EZDBMsg.copy(target, "V", vcMsg, "V");
            startIdx++;
        }

        validCount = validCount - delList.size() + targetList.size();
        vcMsgArray.setValidCount(validCount);
    }

    /**
     * get Start Index for Replace(U)
     * @param ucMsgArray
     * @param mdlId
     * @param dsOrdPosnNum
     * @param targetList
     * @return
     */
    private static int getStartIdxU(NSAL1330_UCMsgArray ucMsgArray, BigDecimal mdlId, String dsOrdPosnNum) {
        if (ucMsgArray.getValidCount() == 0) {
            return 0;
        }
        //(1) Same Model ID, Same Position Number
        for (int i = 0; i < ucMsgArray.getValidCount(); i++) {
            NSAL1330_UCMsg ucMsg = ucMsgArray.no(i);
            if (!ZYPCommonFunc.hasValue(ucMsg.mdlId_U) || !ZYPCommonFunc.hasValue(ucMsg.dsOrdPosnNum_U)) {
                continue;
            }
            if (ucMsg.mdlId_U.getValue().compareTo(mdlId) == 0 && ucMsg.dsOrdPosnNum_U.getValue().equals(dsOrdPosnNum)) {
                return i;
            }
        }

        //(2) Same Model ID, Greater Position Number
        for (int i = 0; i < ucMsgArray.getValidCount(); i++) {
            NSAL1330_UCMsg ucMsg = ucMsgArray.no(i);
            if (!ZYPCommonFunc.hasValue(ucMsg.mdlId_U) || !ZYPCommonFunc.hasValue(ucMsg.dsOrdPosnNum_U)) {
                continue;
            }
            if (ucMsg.mdlId_U.getValue().compareTo(mdlId) == 0 && ucMsg.dsOrdPosnNum_U.getValue().compareTo(dsOrdPosnNum) > 0) {
                return i;
            }
        }

        //(3) Greater Model ID
        for (int i = 0; i < ucMsgArray.getValidCount(); i++) {
            NSAL1330_UCMsg ucMsg = ucMsgArray.no(i);
            if (!ZYPCommonFunc.hasValue(ucMsg.mdlId_U) || !ZYPCommonFunc.hasValue(ucMsg.dsOrdPosnNum_U)) {
                continue;
            }
            if (ucMsg.mdlId_U.getValue().compareTo(mdlId) > 0) {
                return i;
            }
        }

        //(4) Less Model ID
        return ucMsgArray.getValidCount();
    }

    /**
     * get Start Index for Replace(V)
     * @param vcMsgArray
     * @param mdlId
     * @param dsOrdPosnNum
     * @param targetList
     * @return
     */
    private static int getStartIdxV(NSAL1330_VCMsgArray vcMsgArray, BigDecimal mdlId, String dsOrdPosnNum) {
        if (vcMsgArray.getValidCount() == 0) {
            return 0;
        }
        //(1) Same Model ID, Same Position Number
        for (int i = 0; i < vcMsgArray.getValidCount(); i++) {
            NSAL1330_VCMsg vcMsg = vcMsgArray.no(i);
            if (!ZYPCommonFunc.hasValue(vcMsg.mdlId_V) || !ZYPCommonFunc.hasValue(vcMsg.dsOrdPosnNum_V)) {
                continue;
            }
            if (vcMsg.mdlId_V.getValue().compareTo(mdlId) == 0 && vcMsg.dsOrdPosnNum_V.getValue().equals(dsOrdPosnNum)) {
                return i;
            }
        }

        //(2) Same Model ID, Greater Position Number
        for (int i = 0; i < vcMsgArray.getValidCount(); i++) {
            NSAL1330_VCMsg vcMsg = vcMsgArray.no(i);
            if (!ZYPCommonFunc.hasValue(vcMsg.mdlId_V) || !ZYPCommonFunc.hasValue(vcMsg.dsOrdPosnNum_V)) {
                continue;
            }
            if (vcMsg.mdlId_V.getValue().compareTo(mdlId) == 0 && vcMsg.dsOrdPosnNum_V.getValue().compareTo(dsOrdPosnNum) > 0) {
                return i;
            }
        }

        //(3) Greater Model ID
        for (int i = 0; i < vcMsgArray.getValidCount(); i++) {
            NSAL1330_VCMsg vcMsg = vcMsgArray.no(i);
            if (!ZYPCommonFunc.hasValue(vcMsg.mdlId_V) || !ZYPCommonFunc.hasValue(vcMsg.dsOrdPosnNum_V)) {
                continue;
            }
            if (vcMsg.mdlId_V.getValue().compareTo(mdlId) > 0) {
                return i;
            }
        }

        //(4) Less Model ID
        return vcMsgArray.getValidCount();
    }

    /**
     * @param bizMsg NSAL1330CMsg
     */
    public static void applyAllFromModel(NSAL1330CMsg bizMsg) {
        int ixA = bizMsg.xxNum_A.getValueInt();
        NSAL1330_ACMsg aBizMsg = bizMsg.A.no(ixA);
        BigDecimal mdlId = aBizMsg.mdlId_A.getValue();

        for (int ixR = 0; ixR < bizMsg.R.getValidCount(); ixR++) {
            NSAL1330_RCMsg rBizMsg = bizMsg.R.no(ixR);
            if (!ZYPCommonFunc.hasValue(rBizMsg.mdlId_R)) {
                continue;
            }
            if (mdlId.compareTo(rBizMsg.mdlId_R.getValue()) != 0) {
                continue;
            }
            String dsOrdPosnNum = rBizMsg.dsOrdPosnNum_R.getValue();
            BigDecimal dsContrDtlPk = rBizMsg.dsContrDtlPk_R.getValue();

            applyABizMsgToRBizMsg(bizMsg, aBizMsg, rBizMsg, null);

            //Meter Area
            List<NSAL1330_UCMsg> uAplyMsgList = null;
            int applyAllCountZ = NSAL1330CommonLogic.getApplyAllCountZ(bizMsg.Z, mdlId);
            int ixUAply = 0;

            for (int ixZ = 0; ixZ < bizMsg.Z.getValidCount(); ixZ++) {
                NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(ixZ);
                if (!ZYPCommonFunc.hasValue(zBizMsg.mdlId_Z)) {
                    continue;
                }
                if (mdlId.compareTo(zBizMsg.mdlId_Z.getValue()) != 0) {
                    continue;
                }
                //get U List( U.size() = Z.size() )
                if (uAplyMsgList == null) {
                    uAplyMsgList = NSAL1330CommonLogic.getTargetMsgListU(bizMsg.U, mdlId, dsOrdPosnNum, applyAllCountZ, dsContrDtlPk, bizMsg);
                }

                NSAL1330CommonLogic.copyZBizMsgToUBizMsg(bizMsg, zBizMsg, uAplyMsgList.get(ixUAply), null);
                ixUAply++;
            }
            // START 2017/06/20 [QC#19216, ADD]
            List<Integer> delList = new ArrayList<Integer>();
            for (int ixU = 0; ixU < bizMsg.U.getValidCount(); ixU++) {
                NSAL1330_UCMsg uBizMsg = bizMsg.U.no(ixU);
                if (!ZYPCommonFunc.hasValue(uBizMsg.mdlId_U)) {
                    continue;
                }
                if (mdlId.compareTo(uBizMsg.mdlId_U.getValue()) != 0) {
                    continue;
                }
                if (applyAllCountZ >= 0) {
                    continue;
                }
                delList.add(ixU);
            }
            if (!delList.isEmpty()) {
                ZYPTableUtil.deleteRows(bizMsg.U, delList);
            }
            // END   2017/06/20 [QC#19216, ADD]

            //Replace Target
            if (uAplyMsgList != null && uAplyMsgList.size() > 0) {
                NSAL1330CommonLogic.replaceTargetMsgArrayU(bizMsg.U, mdlId, dsOrdPosnNum, uAplyMsgList);
            }

            //Tier Area
            List<NSAL1330_VCMsg> vAplyMsgList = null;
            int applyAllCountX = NSAL1330CommonLogic.getApplyAllCountX(bizMsg.X, mdlId);
            int ixVAply = 0;

            for (int ixX = 0; ixX < bizMsg.X.getValidCount(); ixX++) {
                NSAL1330_XCMsg xBizMsg = bizMsg.X.no(ixX);
                BigDecimal dsContrBllgMtrPk = xBizMsg.dsContrBllgMtrPk_X.getValue();
                if (!ZYPCommonFunc.hasValue(xBizMsg.mdlId_X)) {
                    continue;
                }
                if (mdlId.compareTo(xBizMsg.mdlId_X.getValue()) != 0) {
                    continue;
                }
                //get V List( V.size() = X.size() )
                if (vAplyMsgList == null) {
                    vAplyMsgList = NSAL1330CommonLogic.getTargetMsgListV(bizMsg.V, mdlId, dsOrdPosnNum, applyAllCountX, dsContrBllgMtrPk, bizMsg);
                }

                NSAL1330CommonLogic.copyXBizMsgToVBizMsg(bizMsg, xBizMsg, vAplyMsgList.get(ixVAply), null);
                ixVAply++;
            }

            //Replace Target
            NSAL1330CommonLogic.replaceTargetMsgArrayV(bizMsg.V, mdlId, dsOrdPosnNum, vAplyMsgList);
        }
    }

    /**
     * applyABizMsgtoRBizMsg
     * @param bizMsg NSAL1330CMsg
     * @param aBizMsg aBizMsg
     * @param rBizMsg rBizMsg
     * @param aplyConfigMap Map<String, BigDecimal>
     */
    private static void applyABizMsgToRBizMsg(//
            NSAL1330CMsg bizMsg, NSAL1330_ACMsg aBizMsg, NSAL1330_RCMsg rBizMsg, Map<String, BigDecimal> aplyConfigMap) {
        ZYPEZDItemValueSetter.setValue(rBizMsg.prcCatgNm_R, aBizMsg.prcCatgNm_A);
        ZYPEZDItemValueSetter.setValue(rBizMsg.prcCatgCd_R, aBizMsg.prcCatgCd_A);
        ZYPEZDItemValueSetter.setValue(rBizMsg.maintFlPrcCatgCd_R, aBizMsg.maintFlPrcCatgCd_A);
        for (int i = 0; i < aBizMsg.prcMtrPkgPk_KP.length(); i++) {
            ZYPEZDItemValueSetter.setValue(rBizMsg.prcMtrPkgPk_RL.no(i), aBizMsg.prcMtrPkgPk_KP.no(i));
            ZYPEZDItemValueSetter.setValue(rBizMsg.prcMtrPkgNm_RL.no(i), aBizMsg.prcMtrPkgNm_VW.no(i));
        }
        ZYPEZDItemValueSetter.setValue(rBizMsg.prcMtrPkgPk_R, aBizMsg.prcMtrPkgPk_A);
        ZYPEZDItemValueSetter.setValue(rBizMsg.prcTierSvcOfferCd_R, aBizMsg.prcTierSvcOfferCd_A);
        // Mod Start 2017/10/19 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
//        ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_PR, aBizMsg.xxTotPrcAmt_PB);
//        ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_ER, aBizMsg.xxTotPrcAmt_PB); // set same value.
        ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_PR, round(aBizMsg.xxTotPrcAmt_PB.getValue(), scale));
        ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_ER, round(aBizMsg.xxTotPrcAmt_PB.getValue(), scale)); // set same value.

        if (ZYPCommonFunc.hasValue(aBizMsg.xxTotPrcAmt_TB) && ZYPCommonFunc.hasValue(aBizMsg.xxTotQtyCnt_A)) {
//            ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_TR, aBizMsg.xxTotPrcAmt_TB.getValue().divide(aBizMsg.xxTotQtyCnt_A.getValue()));
            ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_TR, divide(aBizMsg.xxTotPrcAmt_TB.getValue(), aBizMsg.xxTotQtyCnt_A.getValue(), scale));
        }
        ZYPEZDItemValueSetter.setValue(rBizMsg.termMthAot_R, aBizMsg.termMthAot_A);
//        ZYPEZDItemValueSetter.setValue(rBizMsg.basePrcDealAmt_R, aBizMsg.basePrcDealAmt_A);
//        ZYPEZDItemValueSetter.setValue(rBizMsg.dealPrcListPrcAmt_R, aBizMsg.dealPrcListPrcAmt_A);
        ZYPEZDItemValueSetter.setValue(rBizMsg.basePrcDealAmt_R, round(aBizMsg.basePrcDealAmt_A.getValue(), scale));
        ZYPEZDItemValueSetter.setValue(rBizMsg.dealPrcListPrcAmt_R, round(aBizMsg.dealPrcListPrcAmt_A.getValue(), scale));
        // Mod End 2017/10/19 QC#21860
        ZYPEZDItemValueSetter.setValue(rBizMsg.prcRateTpCd_R, aBizMsg.prcRateTpCd_A);
        ZYPEZDItemValueSetter.setValue(rBizMsg.scrEntAvalFlg_R, aBizMsg.scrEntAvalFlg_A);

        ZYPEZDItemValueSetter.setValue(rBizMsg.billToCustCd_R, bizMsg.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(rBizMsg.dsAcctNm_R, bizMsg.dsAcctNm); // 2017/03/29 S21_NA#18171 Mod
        ZYPEZDItemValueSetter.setValue(rBizMsg.billToLocNum_R, bizMsg.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(rBizMsg.xxGenlFldAreaTxt_R, bizMsg.xxGenlFldAreaTxt_BI);

        ZYPEZDItemValueSetter.setValue(rBizMsg.t_MdlNm_R, aBizMsg.t_MdlNm_A);
        ZYPEZDItemValueSetter.setValue(rBizMsg.mdlId_R, aBizMsg.mdlId_A);
        ZYPEZDItemValueSetter.setValue(rBizMsg.prcListTpCd_R, aBizMsg.prcListTpCd_A);

        if (aplyConfigMap != null) {
            aplyConfigMap.put(rBizMsg.dsOrdPosnNum_R.getValue(), rBizMsg.dsContrDtlPk_R.getValue());
        }
    }

    /**
     * copyZBizMsgToUBizMsg
     * @param bizMsg NSAL1330CMsg
     * @param zBizMsg zBizMsg
     * @param uBizMsg uBizMsg
     * @param newDsOrdPosnNum newDsOrdPosnNum
     */
    private static void copyZBizMsgToUBizMsg(
            NSAL1330CMsg bizMsg, NSAL1330_ZCMsg zBizMsg, NSAL1330_UCMsg uBizMsg, String newDsOrdPosnNum) {

        // backup original value
        String dsOrdPosnNum = uBizMsg.dsOrdPosnNum_U.getValue();
        BigDecimal dsContrDtlPk = uBizMsg.dsContrDtlPk_U.getValue();
        BigDecimal dsContrBllgMtrPk = uBizMsg.dsContrBllgMtrPk_U.getValue();
        BigDecimal contrXsCopyPk = uBizMsg.contrXsCopyPk_U.getValue();
        BigDecimal contrPhysBllgMtrRelnPk = uBizMsg.contrPhysBllgMtrRelnPk_U.getValue();

        EZDBMsg.copy(zBizMsg, "Z",  uBizMsg, "U");
        EZDBMsg.copy(zBizMsg, "ZB", uBizMsg, "UB");

        // restore original value
        ZYPEZDItemValueSetter.setValue(uBizMsg.dsOrdPosnNum_U, dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(uBizMsg.dsContrDtlPk_U, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(uBizMsg.dsContrBllgMtrPk_U, dsContrBllgMtrPk);
        ZYPEZDItemValueSetter.setValue(uBizMsg.contrXsCopyPk_U, contrXsCopyPk);
        ZYPEZDItemValueSetter.setValue(uBizMsg.contrPhysBllgMtrRelnPk_U, contrPhysBllgMtrRelnPk);

        ZYPEZDItemValueSetter.setValue(uBizMsg.actvFlg_U, ZYPConstant.FLG_ON_Y);

        if (newDsOrdPosnNum != null) {
            ZYPEZDItemValueSetter.setValue(uBizMsg.dsOrdPosnNum_U, newDsOrdPosnNum);
        }
    }

    /**
     * copyXBizMsgToVBizMsg
     * @param bizMsg NSAL1330CMsg
     * @param xBizMsg xBizMsg
     * @param vBizMsg vBizMsg
     * @param newDsOrdPosnNum newDsOrdPosnNum
     */
    private static void copyXBizMsgToVBizMsg(//
            NSAL1330CMsg bizMsg, NSAL1330_XCMsg xBizMsg, NSAL1330_VCMsg vBizMsg, String newDsOrdPosnNum) {

        ZYPEZDItemValueSetter.setValue(vBizMsg.mdlId_V, xBizMsg.mdlId_X);
        ZYPEZDItemValueSetter.setValue(vBizMsg.bllgMtrLbCd_V, xBizMsg.bllgMtrLbCd_X);
        ZYPEZDItemValueSetter.setValue(vBizMsg.prcSvcTierTpCd_V, xBizMsg.prcSvcTierTpCd_X);
        ZYPEZDItemValueSetter.setValue(vBizMsg.prcSvcTierTpDescTxt_V, xBizMsg.prcSvcTierTpDescTxt_X);
        ZYPEZDItemValueSetter.setValue(vBizMsg.minCopyVolCnt_V, xBizMsg.minCopyVolCnt_X);
        ZYPEZDItemValueSetter.setValue(vBizMsg.maxCopyVolCnt_V, xBizMsg.maxCopyVolCnt_X);
        ZYPEZDItemValueSetter.setValue(vBizMsg.xsMtrAmtRate_V, xBizMsg.xsMtrAmtRate_X);

        ZYPEZDItemValueSetter.setValue(vBizMsg.actvFlg_V, ZYPConstant.FLG_ON_Y);

        if (newDsOrdPosnNum != null) {
            ZYPEZDItemValueSetter.setValue(vBizMsg.dsOrdPosnNum_V, newDsOrdPosnNum);
        }
    }

    /**
     * forceApplyAllForNewEntry
     * @param bizMsg NSAL1330CMsg
     * @param glblMsg NSAL1330SMsg
     */
    public static void forceApplyAllForSave(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        if (isFleet(glblMsg)) {
            return;
        }
        Map<String, BigDecimal> aplyConfigMap = new HashMap<String, BigDecimal>(bizMsg.R.getValidCount());
        List<BigDecimal> aplyMdlList = new ArrayList<BigDecimal>(bizMsg.U.getValidCount());
        for (int ixA = 0; ixA < bizMsg.A.getValidCount(); ixA++) {
            NSAL1330_ACMsg aBizMsg = bizMsg.A.no(ixA);
            // START 2017/10/10 [QC#20059, ADD]
            if (isConfigLevelPriceSetting(aBizMsg)) {
                continue;
            }
            // END   2017/10/10 [QC#20059, ADD]
            if (!isChangedModelPricing(aBizMsg, glblMsg)) {
                continue;
            }
            for (int ixR = 0; ixR < bizMsg.R.getValidCount(); ixR++) {
                NSAL1330_RCMsg rBizMsg = bizMsg.R.no(ixR);
                if (!isSameValue(aBizMsg.mdlId_A, rBizMsg.mdlId_R)) {
                    continue;
                }

                // START 2017/10/10 [QC#20059, DEL]
                // if (isChangedConfigPricing(rBizMsg, glblMsg)) {
                //     continue;
                // }
                // // START 2017/06/06 [QC#18318, ADD]
                // if(hasDifferencePricing(rBizMsg, aBizMsg) && !isEmptyPricing(rBizMsg)) {
                //     continue;
                // }
                // // END   2017/06/06 [QC#18318, ADD]
                // END   2017/10/10 [QC#20059, DEL]

                aplyMdlList.add(rBizMsg.mdlId_R.getValue());
                applyABizMsgToRBizMsg(bizMsg, aBizMsg, rBizMsg, aplyConfigMap);
            }
        }

        // Usage
        for (int ixZ = 0; ixZ < bizMsg.Z.getValidCount(); ixZ++) {
            NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(ixZ);
            // START 2017/10/10 [QC#20059, ADD]
            if (isConfigLevelPriceSetting(bizMsg, zBizMsg.mdlId_Z.getValue())) {
                continue;
            }
            // END   2017/10/10 [QC#20059, ADD]
            if (!aplyMdlList.contains(zBizMsg.mdlId_Z.getValue()) //
                    && !isChangedModelPricingUsage(zBizMsg, glblMsg)) {
                continue;
            }
            for (int ixU = 0; ixU < bizMsg.U.getValidCount(); ixU++) {
                NSAL1330_UCMsg uBizMsg = bizMsg.U.no(ixU);
                if (!isSameValue(zBizMsg.mdlId_Z, uBizMsg.mdlId_U)) {
                    continue;
                }
                // START 2017/06/06 [QC#18318, MOD]
                // if (!S21StringUtil.isEquals(zBizMsg.bllgMtrLbCd_Z.getValue(), uBizMsg.bllgMtrLbCd_U.getValue())) {
                //     continue;
                // }
                // if (!S21StringUtil.isEquals(zBizMsg.regMtrLbCd_Z.getValue(), uBizMsg.regMtrLbCd_U.getValue())) {
                //     continue;
                // }
                if (ZYPCommonFunc.hasValue(uBizMsg.bllgMtrLbCd_U) && !S21StringUtil.isEquals(zBizMsg.bllgMtrLbCd_Z.getValue(), uBizMsg.bllgMtrLbCd_U.getValue())) {
                    continue;
                }
                if (ZYPCommonFunc.hasValue(zBizMsg.regMtrLbCd_Z) && ZYPCommonFunc.hasValue(uBizMsg.regMtrLbCd_U) 
                        && !S21StringUtil.isEquals(zBizMsg.regMtrLbCd_Z.getValue(), uBizMsg.regMtrLbCd_U.getValue())) {
                    continue;
                } else if (ZYPCommonFunc.hasValue(zBizMsg.regMtrLbCd_Z) || ZYPCommonFunc.hasValue(uBizMsg.regMtrLbCd_U)) {
                    continue;
                }
                // END  2017/06/06 [QC#18318, MOD]

                // START 2017/10/10 [QC#20059, DEL]
                // if (isChangedConfigPricingUsage(uBizMsg, glblMsg)) {
                //     if (ZYPCommonFunc.hasValue(uBizMsg.dsContrDtlPk_U) //
                //             && aplyConfigMap.containsKey(uBizMsg.dsOrdPosnNum_U.getValue())) {
                //         aplyConfigMap.remove(uBizMsg.dsOrdPosnNum_U.getValue());
                //     }
                //     continue;
                // }
                // // START 2017/06/06 [QC#18318, ADD]
                // if(hasDifferencePricingUsage(uBizMsg, zBizMsg) && !isEmptyPricingUsage(uBizMsg)) {
                //     if (ZYPCommonFunc.hasValue(uBizMsg.dsContrDtlPk_U) //
                //             && aplyConfigMap.containsKey(uBizMsg.dsOrdPosnNum_U.getValue())) {
                //        aplyConfigMap.remove(uBizMsg.dsOrdPosnNum_U.getValue());
                //     }
                //     continue;
                // }
                // // END   2017/06/06 [QC#18318, ADD]
                // END   2017/10/10 [QC#20059, DEL]
                if (!aplyMdlList.contains(zBizMsg.mdlId_Z.getValue())) {
                    aplyMdlList.add(zBizMsg.mdlId_Z.getValue());
                }
                if (!aplyConfigMap.containsKey(uBizMsg.dsOrdPosnNum_U.getValue())) {
                    aplyConfigMap.put(uBizMsg.dsOrdPosnNum_U.getValue(), uBizMsg.dsContrDtlPk_U.getValue());
                }
            }
        }
        applyZBizMsgToUBizMsg(bizMsg, aplyMdlList, aplyConfigMap);
        // START 2017/06/20 [QC#19216, ADD]
        for (BigDecimal mdlId : aplyMdlList) {
            List<Integer> delList = new ArrayList<Integer>();
            int applyAllCountZ = NSAL1330CommonLogic.getApplyAllCountZ(bizMsg.Z, mdlId);

            if (applyAllCountZ > 0) {
                continue;
            }

            for (int ixU = 0; ixU < bizMsg.U.getValidCount(); ixU++) {
                NSAL1330_UCMsg uBizMsg = bizMsg.U.no(ixU);
                if (!ZYPCommonFunc.hasValue(uBizMsg.mdlId_U)) {
                    continue;
                }
                if (mdlId.compareTo(uBizMsg.mdlId_U.getValue()) != 0) {
                    continue;
                }
                if (isChangedConfigPricingUsage(uBizMsg, glblMsg)) {
                    continue;
                }
                if(!isEmptyPricingUsage(uBizMsg)) {
                    continue;
                }
                delList.add(ixU);
            }
            if (!delList.isEmpty()) {
                ZYPTableUtil.deleteRows(bizMsg.U, delList);
            }
        }
        // END   2017/06/20 [QC#19216, ADD]

        // Tier
        for (int ixX = 0; ixX < bizMsg.X.getValidCount(); ixX++) {
            NSAL1330_XCMsg xBizMsg = bizMsg.X.no(ixX);
            // START 2017/10/10 [QC#20059, ADD]
            if (isConfigLevelPriceSetting(bizMsg, xBizMsg.mdlId_X.getValue())) {
                continue;
            }
            // END   2017/10/10 [QC#20059, ADD]
            if (!aplyMdlList.contains(xBizMsg.mdlId_X.getValue()) //
                    && !isChangedModelPricingTier(xBizMsg, glblMsg)) {
                continue;
            }
            for (int ixV = 0; ixV < bizMsg.V.getValidCount(); ixV++) {
                NSAL1330_VCMsg vBizMsg = bizMsg.V.no(ixV);
                if (!isSameValue(xBizMsg.mdlId_X, vBizMsg.mdlId_V)) {
                    continue;
                }
                if (!S21StringUtil.isEquals(xBizMsg.prcSvcTierTpCd_X.getValue(), vBizMsg.prcSvcTierTpCd_V.getValue())) {
                    continue;
                }

                // START 2017/10/10 [QC#20059, DEL]
                // if (isChangedConfigPricingTier(vBizMsg, glblMsg)) {
                //     if (aplyConfigMap.containsKey(vBizMsg.dsOrdPosnNum_V.getValue())) {
                //         aplyConfigMap.remove(vBizMsg.dsOrdPosnNum_V.getValue());
                //     }
                //     continue;
                // }
                // // START 2017/06/06 [QC#18318, ADD]
                // if(hasDifferencePricingTier(vBizMsg, xBizMsg)) {
                //     if (aplyConfigMap.containsKey(vBizMsg.dsOrdPosnNum_V.getValue())) {
                //         aplyConfigMap.remove(vBizMsg.dsOrdPosnNum_V.getValue());
                //     }
                //     continue;
                // }
                // END   2017/06/06 [QC#18318, ADD]
                // END   2017/10/10 [QC#20059, DEL]
                if (!aplyMdlList.contains(xBizMsg.mdlId_X.getValue())) {
                    aplyMdlList.add(xBizMsg.mdlId_X.getValue());
                }
                if (!aplyConfigMap.containsKey(vBizMsg.dsOrdPosnNum_V.getValue())) {
                    aplyConfigMap.put(vBizMsg.dsOrdPosnNum_V.getValue(), vBizMsg.dsContrBllgMtrPk_V.getValue());
                }
            }
        }
        applyXBizMsgToVBizMsg(bizMsg, aplyMdlList, aplyConfigMap);

        reOrdUsgConfig(bizMsg);
    }

    private static void applyXBizMsgToVBizMsg(//
            NSAL1330CMsg bizMsg, List<BigDecimal> aplyMdlList, Map<String, BigDecimal> aplyConfigMap) {

        int vValidCount = bizMsg.V.getValidCount();
        List<Integer> delList = new ArrayList<Integer>(vValidCount);
        List<NSAL1330_XCMsg> xAplyMsgList = new ArrayList<NSAL1330_XCMsg>();

        for (int ixX = 0; ixX < bizMsg.X.getValidCount(); ixX++) {
            NSAL1330_XCMsg xBizMsg = bizMsg.X.no(ixX);
            if (!aplyMdlList.contains(xBizMsg.mdlId_X.getValue())) {
                continue;
            }
            xAplyMsgList.add(xBizMsg);
            for (int ixV = 0; ixV < vValidCount; ixV++) {
                NSAL1330_VCMsg vBizMsg = bizMsg.V.no(ixV);
                if (!aplyMdlList.contains(vBizMsg.mdlId_V.getValue())) {
                    continue;
                }
                if (!aplyConfigMap.containsKey(vBizMsg.dsOrdPosnNum_V.getValue())) {
                    continue;
                }
                if (!delList.contains(ixV)) {
                    delList.add(ixV);
                }
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.V, delList);

        // START 2017/07/03 [QC#19479, MOD]
        for (Entry<String, BigDecimal> aplyConfig : aplyConfigMap.entrySet()) {
            NSAL1330_RCMsg rBizMsg = getSvcPrcInfoByDsContrDtlPk(bizMsg, aplyConfig.getValue());
            if (rBizMsg == null) {
                continue;
            }
            for (NSAL1330_XCMsg xBizMsg : xAplyMsgList) {
                BigDecimal xMdlId = xBizMsg.mdlId_X.getValue();
                BigDecimal rMdlId = rBizMsg.mdlId_R.getValue();
                if (rMdlId.compareTo(xMdlId) != 0) {
                    continue;
                }
                NSAL1330_VCMsg vAplyMsg = bizMsg.V.no(bizMsg.V.getValidCount());
                copyXBizMsgToVBizMsg(bizMsg, xBizMsg, vAplyMsg, aplyConfig.getKey());
                // START 2017/06/06 [QC#18318, ADD]
                ZYPEZDItemValueSetter.setValue(vAplyMsg.dsContrDtlPk_V, aplyConfig.getValue());
                bizMsg.V.setValidCount(bizMsg.V.getValidCount() + 1);
                // END   2017/06/06 [QC#18318, ADD]
            }
        }
        // END   2017/07/03 [QC#19479, MOD]
    }

    // START 2017/07/03 [QC#19479, ADD]
    private static NSAL1330_RCMsg getSvcPrcInfoByDsContrDtlPk(NSAL1330CMsg bizMsg, BigDecimal dsContrDtlPk) {
        for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
            NSAL1330_RCMsg rBizMsg = bizMsg.R.no(i);
            if (dsContrDtlPk.compareTo(rBizMsg.dsContrDtlPk_R.getValue()) == 0) {
                return rBizMsg;
            }
        }
        return null;
    }
    // END   2017/07/03 [QC#19479, ADD]

    private static void applyZBizMsgToUBizMsg(NSAL1330CMsg bizMsg, List<BigDecimal> aplyMdlList, Map<String, BigDecimal> aplyConfigMap) {
        int uValidCount = bizMsg.U.getValidCount();
        List<Integer> delList = new ArrayList<Integer>(uValidCount);
        List<NSAL1330_ZCMsg> zAplyMsgList = new ArrayList<NSAL1330_ZCMsg>();

        for (int ixZ = 0; ixZ < bizMsg.Z.getValidCount(); ixZ++) {
            NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(ixZ);
            if (!aplyMdlList.contains(zBizMsg.mdlId_Z.getValue())) {
                continue;
            }
            zAplyMsgList.add(zBizMsg);
            for (int ixU = 0; ixU < uValidCount; ixU++) {
                NSAL1330_UCMsg uBizMsg = bizMsg.U.no(ixU);
                if (!aplyMdlList.contains(uBizMsg.mdlId_U.getValue())) {
                    continue;
                }
                if (!aplyConfigMap.containsKey(uBizMsg.dsOrdPosnNum_U.getValue())) {
                    continue;
                }
                if (!delList.contains(ixU)) {
                    delList.add(ixU);
                }
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.U, delList);

        for (Entry<String, BigDecimal> aplyConfig : aplyConfigMap.entrySet()) {
            for (NSAL1330_ZCMsg zBizMsg : zAplyMsgList) {
                NSAL1330_UCMsg uAplyMsg = bizMsg.U.no(bizMsg.U.getValidCount());
                copyZBizMsgToUBizMsg(bizMsg, zBizMsg, uAplyMsg, aplyConfig.getKey());
                // START 2017/06/06 [QC#18318, ADD]
                ZYPEZDItemValueSetter.setValue(uAplyMsg.dsContrDtlPk_U, aplyConfig.getValue());
                bizMsg.U.setValidCount(bizMsg.U.getValidCount() + 1);
                // END   2017/06/06 [QC#18318, ADD]
            }
        }

    }

    private static boolean isChangedConfigPricing(NSAL1330_RCMsg rBizMsg, NSAL1330SMsg glblMsg) {
        boolean isChange = true;
        for (int ixR = 0; ixR < glblMsg.R.getValidCount(); ixR++) {
            NSAL1330_RSMsg rGlblMsg = glblMsg.R.no(ixR);
            if (!isSameValue(rBizMsg.mdlId_R, rGlblMsg.mdlId_R)) {
                continue;
            }
            if (!isSameValue(rBizMsg.dsContrDtlPk_R, rGlblMsg.dsContrDtlPk_R)) {
                continue;
            }

            isChange = false;
            if (!ZYPCommonFunc.hasValue(rBizMsg.dsContrDtlPk_R)) {
                continue;
            }
            if (!S21StringUtil.isEquals(rBizMsg.prcCatgNm_R.getValue(), rGlblMsg.prcCatgNm_R.getValue())) {
                return true;
            }
            if (!isSameValue(rBizMsg.prcMtrPkgPk_R, rGlblMsg.prcMtrPkgPk_R)) {
                return true;
            }
            if (!S21StringUtil.isEquals(rBizMsg.prcTierSvcOfferCd_R.getValue(), rGlblMsg.prcTierSvcOfferCd_R.getValue())) {
                return true;
            }
            if (!isSameValue(rBizMsg.xxTotPrcAmt_PR, rGlblMsg.xxTotPrcAmt_PR)) {
                return true;
            }
        }
        return isChange;
    }

    private static boolean isChangedConfigPricingTier(NSAL1330_VCMsg vBizMsg, NSAL1330SMsg glblMsg) {
        boolean isChange = true;
        for (int ixX = 0; ixX < glblMsg.V.getValidCount(); ixX++) {
            NSAL1330_VSMsg vGlblMsg = glblMsg.V.no(ixX);
            if (!isSameValue(vBizMsg.mdlId_V, vGlblMsg.mdlId_V)) {
                continue;
            }
            if (!isSameValue(vBizMsg.dsContrBllgMtrPk_V, vGlblMsg.dsContrBllgMtrPk_V)) {
                continue;
            }
            if (!S21StringUtil.isEquals(vBizMsg.bllgMtrLbCd_V.getValue(), vGlblMsg.bllgMtrLbCd_V.getValue())) {
                continue;
            }
            if (!S21StringUtil.isEquals(vBizMsg.prcSvcTierTpCd_V.getValue(), vGlblMsg.prcSvcTierTpCd_V.getValue())) {
                continue;
            }

            isChange = false;
            if (!isSameValue(vBizMsg.minCopyVolCnt_V, vGlblMsg.minCopyVolCnt_V)) {
                return true;
            }
            if (!isSameValue(vBizMsg.maxCopyVolCnt_V, vGlblMsg.maxCopyVolCnt_V)) {
                return true;
            }
            if (!isSameValue(vBizMsg.xsMtrAmtRate_V, vGlblMsg.xsMtrAmtRate_V)) {
                return true;
            }
        }
        return isChange;
    }

    private static boolean isChangedConfigPricingUsage(NSAL1330_UCMsg uBizMsg, NSAL1330SMsg glblMsg) {
        boolean isChange = true;
        for (int ixU = 0; ixU < glblMsg.U.getValidCount(); ixU++) {
            NSAL1330_USMsg uGlblMsg = glblMsg.U.no(ixU);
            if (!isSameValue(uBizMsg.mdlId_U, uGlblMsg.mdlId_U)) {
                continue;
            }
            if (!isSameValue(uBizMsg.dsContrDtlPk_U, uGlblMsg.dsContrDtlPk_U)) {
                continue;
            }
            if (!S21StringUtil.isEquals(uBizMsg.bllgMtrLbCd_U.getValue(), uGlblMsg.bllgMtrLbCd_U.getValue())) {
                continue;
            }
            if (!S21StringUtil.isEquals(uBizMsg.regMtrLbCd_U.getValue(), uGlblMsg.regMtrLbCd_U.getValue())) {
                continue;
            }

            isChange = false;
            if (!ZYPCommonFunc.hasValue(uBizMsg.dsContrDtlPk_U)) {
                continue;
            }
            if (!S21StringUtil.isEquals(uBizMsg.prcListBandDescTxt_U.getValue(), uGlblMsg.prcListBandDescTxt_U.getValue())) {
                return true;
            }
            if (!S21StringUtil.isEquals(uBizMsg.prcBookMdseCd_U.getValue(), uGlblMsg.prcBookMdseCd_U.getValue())) {
                return true;
            }
            if (!isSameValue(uBizMsg.mlyCopyInclPrcQty_U, uGlblMsg.mlyCopyInclPrcQty_U)) {
                return true;
            }
            if (!isSameValue(uBizMsg.xsMtrAmtRate_U, uGlblMsg.xsMtrAmtRate_U)) {
                return true;
            }
            if (!isSameValue(uBizMsg.contrMtrMultRate_U, uGlblMsg.contrMtrMultRate_U)) {
                return true;
            }
            if (!S21StringUtil.isEquals(uBizMsg.billToCustCd_U.getValue(), uGlblMsg.billToCustCd_U.getValue())) {
                return true;
            }
            if (!S21StringUtil.isEquals(uBizMsg.billToLocNum_U.getValue(), uGlblMsg.billToLocNum_U.getValue())) {
                return true;
            }
            // 2018/05/09 QC#25030 add start
            if (!isSameValue(uBizMsg.bllgFreeCopyCnt_U, uGlblMsg.bllgFreeCopyCnt_U)) {
                return true;
            }
            if (!isSameValue(uBizMsg.bllgMinCnt_U, uGlblMsg.bllgMinCnt_U)) {
                return true;
            }
            if (!isSameValue(uBizMsg.bllgMinAmtRate_U, uGlblMsg.bllgMinAmtRate_U)) {
                return true;
            }
            if (!isSameValue(uBizMsg.bllgRollOverRatio_U, uGlblMsg.bllgRollOverRatio_U)) {
                return true;
            }
            // 2018/05/09 QC#25030 add end
        }
        return isChange;
    }

    // START 2017/06/06 [QC#18318, ADD]
    private static boolean isEmptyPricing(NSAL1330_RCMsg rBizMsg) {
        if (ZYPCommonFunc.hasValue(rBizMsg.prcCatgCd_R)) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(rBizMsg.prcMtrPkgPk_R)) {
            return false;
        }
        return true;
    }

    private static boolean isEmptyPricingUsage(NSAL1330_UCMsg uBizMsg) {
        if (ZYPCommonFunc.hasValue(uBizMsg.bllgMtrLbCd_U)) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(uBizMsg.billToCustCd_U)) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(uBizMsg.billToLocNum_U)) {
            return false;
        }
        return true;
    }

    private static boolean hasDifferencePricing(NSAL1330_RCMsg rBizMsg, NSAL1330_ACMsg aBizMsg) {

        if (!S21StringUtil.isEquals(rBizMsg.prcCatgNm_R.getValue(), aBizMsg.prcCatgNm_A.getValue())) {
            return true;
        }
        if (!isSameValue(rBizMsg.prcMtrPkgPk_R, aBizMsg.prcMtrPkgPk_A)) {
            return true;
        }
        if (!S21StringUtil.isEquals(rBizMsg.prcTierSvcOfferCd_R.getValue(), aBizMsg.prcTierSvcOfferCd_A.getValue())) {
            return true;
        }
        if (!isSameValue(rBizMsg.xxTotPrcAmt_PR, aBizMsg.xxTotPrcAmt_PB)) {
            return true;
        }

        return false;
    }

    private static boolean hasDifferencePricingUsage(NSAL1330_UCMsg uBizMsg, NSAL1330_ZCMsg zBizMsg) {

        
        
        if (!S21StringUtil.isEquals(uBizMsg.prcListBandDescTxt_U.getValue(), zBizMsg.prcListBandDescTxt_Z.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(uBizMsg.prcBookMdseCd_U.getValue(), zBizMsg.prcBookMdseCd_Z.getValue())) {
            return true;
        }
        if (!isSameValue(uBizMsg.mlyCopyInclPrcQty_U, zBizMsg.mlyCopyInclPrcQty_Z)) {
            return true;
        }

        if (!isSameValue(uBizMsg.xsMtrAmtRate_U, zBizMsg.xsMtrAmtRate_Z)) {
            return true;
        }
        if (!isSameValue(uBizMsg.contrMtrMultRate_U, zBizMsg.contrMtrMultRate_Z)) {
            return true;
        }

        return false;
    }

    private static boolean hasDifferencePricingTier(NSAL1330_VCMsg vBizMsg, NSAL1330_XCMsg xBizMsg) {

        if (!isSameValue(vBizMsg.minCopyVolCnt_V, xBizMsg.minCopyVolCnt_X)) {
            return true;
        }
        if (!isSameValue(vBizMsg.maxCopyVolCnt_V, xBizMsg.maxCopyVolCnt_X)) {
            return true;
        }
        if (!isSameValue(vBizMsg.xsMtrAmtRate_V, xBizMsg.xsMtrAmtRate_X)) {
            return true;
        }

        return false;
    }
    // END   2017/06/06 [QC#18318, ADD]

    private static boolean isChangedModelPricing(NSAL1330_ACMsg aBizMsg, NSAL1330SMsg glblMsg) {
        for (int ixA = 0; ixA < glblMsg.A.getValidCount(); ixA++) {
            NSAL1330_ASMsg aGlblMsg = glblMsg.A.no(ixA);
            if (!isSameValue(aBizMsg.mdlId_A, aGlblMsg.mdlId_A)) {
                continue;
            }

            if (!S21StringUtil.isEquals(aBizMsg.prcCatgNm_A.getValue(), aGlblMsg.prcCatgNm_A.getValue())) {
                return true;
            }
            if (!isSameValue(aBizMsg.prcMtrPkgPk_A, aGlblMsg.prcMtrPkgPk_A)) {
                return true;
            }
            if (!S21StringUtil.isEquals(aBizMsg.prcTierSvcOfferCd_A.getValue(), aGlblMsg.prcTierSvcOfferCd_A.getValue())) {
                return true;
            }
            if (!isSameValue(aBizMsg.xxTotPrcAmt_PB, aGlblMsg.xxTotPrcAmt_PB)) {
                return true;
            }
            // START 2017/06/26 [QC#19500-1, ADD]
            if (!ZYPCommonFunc.hasValue(aBizMsg.dsContrDtlPk_A)) {
                return true;
            }
            // END   2017/06/26 [QC#19500-1, ADD]
        }
        return glblMsg.A.getValidCount() == 0;
    }

    private static boolean isChangedModelPricingTier(NSAL1330_XCMsg xBizMsg, NSAL1330SMsg glblMsg) {
        boolean isChange = true;
        for (int ixX = 0; ixX < glblMsg.X.getValidCount(); ixX++) {
            NSAL1330_XSMsg xGlblMsg = glblMsg.X.no(ixX);
            if (!isSameValue(xBizMsg.mdlId_X, xGlblMsg.mdlId_X)) {
                continue;
            }
            // START 2017/07/03 [QC#19479, MOD]
            // if (!isSameValue(xBizMsg.contrXsCopyPk_X, xGlblMsg.dsContrBllgMtrPk_X)) {
            if (!isSameValue(xBizMsg.contrXsCopyPk_X, xGlblMsg.contrXsCopyPk_X)) {
                continue;
            }
            // END   2017/07/03 [QC#19479, MOD]
            if (!S21StringUtil.isEquals(xBizMsg.bllgMtrLbCd_X.getValue(), xGlblMsg.bllgMtrLbCd_X.getValue())) {
                continue;
            }
            if (!S21StringUtil.isEquals(xBizMsg.prcSvcTierTpCd_X.getValue(), xGlblMsg.prcSvcTierTpCd_X.getValue())) {
                continue;
            }

            isChange = false;
            if (!isSameValue(xBizMsg.minCopyVolCnt_X, xGlblMsg.minCopyVolCnt_X)) {
                return true;
            }
            if (!isSameValue(xBizMsg.maxCopyVolCnt_X, xGlblMsg.maxCopyVolCnt_X)) {
                return true;
            }
            if (!isSameValue(xBizMsg.xsMtrAmtRate_X, xGlblMsg.xsMtrAmtRate_X)) {
                return true;
            }
        }
        return isChange;
    }

    private static boolean isChangedModelPricingUsage(NSAL1330_ZCMsg zBizMsg, NSAL1330SMsg glblMsg) {
        boolean isChange = true;
        for (int ixZ = 0; ixZ < glblMsg.Z.getValidCount(); ixZ++) {
            NSAL1330_ZSMsg zGlblMsg = glblMsg.Z.no(ixZ);
            if (!isSameValue(zBizMsg.mdlId_Z, zGlblMsg.mdlId_Z)) {
                continue;
            }
            if (!S21StringUtil.isEquals(zBizMsg.bllgMtrLbCd_Z.getValue(), zGlblMsg.bllgMtrLbCd_Z.getValue())) {
                continue;
            }
            if (!S21StringUtil.isEquals(zBizMsg.regMtrLbCd_Z.getValue(), zGlblMsg.regMtrLbCd_Z.getValue())) {
                continue;
            }

            isChange = false;
            if (!S21StringUtil.isEquals(zBizMsg.prcListBandDescTxt_Z.getValue(), zGlblMsg.prcListBandDescTxt_Z.getValue())) {
                return true;
            }
            if (!S21StringUtil.isEquals(zBizMsg.prcBookMdseCd_Z.getValue(), zGlblMsg.prcBookMdseCd_Z.getValue())) {
                return true;
            }
            if (!isSameValue(zBizMsg.mlyCopyInclPrcQty_Z, zGlblMsg.mlyCopyInclPrcQty_Z)) {
                return true;
            }
            if (!isSameValue(zBizMsg.xsMtrAmtRate_Z, zGlblMsg.xsMtrAmtRate_Z)) {
                return true;
            }
            if (!isSameValue(zBizMsg.contrMtrMultRate_Z, zGlblMsg.contrMtrMultRate_Z)) {
                return true;
            }
            // 2018/05/09 QC#25030 add start
            if (!isSameValue(zBizMsg.bllgFreeCopyCnt_Z, zGlblMsg.bllgFreeCopyCnt_Z)) {
                return true;
            }
            if (!isSameValue(zBizMsg.bllgMinCnt_Z, zGlblMsg.bllgMinCnt_Z)) {
                return true;
            }
            if (!isSameValue(zBizMsg.bllgMinAmtRate_Z, zGlblMsg.bllgMinAmtRate_Z)) {
                return true;
            }
            if (!isSameValue(zBizMsg.bllgRollOverRatio_Z, zGlblMsg.bllgRollOverRatio_Z)) {
                return true;
            }
            // 2018/05/09 QC#25030 add end
        }
        return isChange;
    }

    private static boolean isSameValue(EZDCBigDecimalItem item1, EZDSBigDecimalItem item2) {
        return isSameValue(item1.getValue(), item2.getValue());
    }

    private static boolean isSameValue(EZDCBigDecimalItem item1, EZDCBigDecimalItem item2) {
        return isSameValue(item1.getValue(), item2.getValue());
    }

    public static void checkAvailableToSave(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        if (isFleet(glblMsg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.submtFlg_W, ZYPConstant.FLG_ON_Y);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.submtFlg_W.getValue())) {
            return;
        }

        List<BigDecimal> aplyMdlList = new ArrayList<BigDecimal>(bizMsg.U.getValidCount());
        for (int ixA = 0; ixA < bizMsg.A.getValidCount(); ixA++) {
            NSAL1330_ACMsg aBizMsg = bizMsg.A.no(ixA);
// START 2017/06/06 [QC#18318, DEL]
//            if (!isChangedModelPricing(aBizMsg, glblMsg)) {
//                continue;
//            }
// END   2017/06/06 [QC#18318, DEL]
            for (int ixR = 0; ixR < bizMsg.R.getValidCount(); ixR++) {
                NSAL1330_RCMsg rBizMsg = bizMsg.R.no(ixR);
                // START 2017/06/06 [QC#18318, DEL]
                // if (!ZYPCommonFunc.hasValue(rBizMsg.dsContrDtlPk_R)) {
                //     continue;
                // }
                // END   2017/06/06 [QC#18318, MOD]
                if (!isSameValue(aBizMsg.mdlId_A, rBizMsg.mdlId_R)) {
                    continue;
                }
                if (isChangedConfigPricing(rBizMsg, glblMsg)) {
                    // START 2017/06/06 [QC#18318, ADD]
                    if (!hasDifferencePricing(rBizMsg, aBizMsg)) {
                        continue;
                    }
                    // END   2017/06/06 [QC#18318, ADD]
                    bizMsg.submtFlg_W.clear();
                    return;
                }
                // START 2017/06/06 [QC#18318, ADD]
                if(hasDifferencePricing(rBizMsg, aBizMsg) && !isEmptyPricing(rBizMsg)) {
                    bizMsg.submtFlg_W.clear();
                    return;
                }
                // END   2017/06/06 [QC#18318, ADD]
            }
            aplyMdlList.add(aBizMsg.mdlId_A.getValue());
        }
        for (int ixZ = 0; ixZ < bizMsg.Z.getValidCount(); ixZ++) {
            NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(ixZ);
// START 2017/06/06 [QC#18318, DEL]
//            if (!aplyMdlList.contains(zBizMsg.mdlId_Z.getValue()) //
//                    && !isChangedModelPricingUsage(zBizMsg, glblMsg)) {
//                continue;
//            }
// END   2017/06/06 [QC#18318, DEL]
            for (int ixU = 0; ixU < bizMsg.U.getValidCount(); ixU++) {
                NSAL1330_UCMsg uBizMsg = bizMsg.U.no(ixU);
                // START 2017/06/06 [QC#18318, DEL]
                // if (!ZYPCommonFunc.hasValue(uBizMsg.dsContrDtlPk_U)) {
                //     continue;
                // }
                // END   2017/06/06 [QC#18318, DEL]
                if (!isSameValue(zBizMsg.mdlId_Z, uBizMsg.mdlId_U)) {
                    continue;
                }
                // START 2017/06/06 [QC#18318, MOD]
                // if (!S21StringUtil.isEquals(zBizMsg.bllgMtrLbCd_Z.getValue(), uBizMsg.bllgMtrLbCd_U.getValue())) {
                //     continue;
                // }
                // if (!S21StringUtil.isEquals(zBizMsg.regMtrLbCd_Z.getValue(), uBizMsg.regMtrLbCd_U.getValue())) {
                //     continue;
                // }
                if (ZYPCommonFunc.hasValue(uBizMsg.bllgMtrLbCd_U) && !S21StringUtil.isEquals(zBizMsg.bllgMtrLbCd_Z.getValue(), uBizMsg.bllgMtrLbCd_U.getValue())) {
                    continue;
                }
                if (ZYPCommonFunc.hasValue(zBizMsg.regMtrLbCd_Z) && ZYPCommonFunc.hasValue(uBizMsg.regMtrLbCd_U) 
                        && !S21StringUtil.isEquals(zBizMsg.regMtrLbCd_Z.getValue(), uBizMsg.regMtrLbCd_U.getValue())) {
                    continue;
                } else if (ZYPCommonFunc.hasValue(zBizMsg.regMtrLbCd_Z) || ZYPCommonFunc.hasValue(uBizMsg.regMtrLbCd_U)) {
                    continue;
                }
                // END   2017/06/06 [QC#18318, MOD]                
                if (isChangedConfigPricingUsage(uBizMsg, glblMsg)) {
                    // START 2017/06/06 [QC#18318, ADD]
                    if (!hasDifferencePricingUsage(uBizMsg, zBizMsg)) {
                        continue;
                    }
                    // END   2017/06/06 [QC#18318, ADD]
                    bizMsg.submtFlg_W.clear();
                    return;
                }
                if (hasDifferencePricingUsage(uBizMsg, zBizMsg) && !isEmptyPricingUsage(uBizMsg)) {
                    bizMsg.submtFlg_W.clear();
                    return;
                }
            }
        }
        for (int ixX = 0; ixX < bizMsg.X.getValidCount(); ixX++) {
            NSAL1330_XCMsg xBizMsg = bizMsg.X.no(ixX);
// START 2017/06/06 [QC#18318, DEL]
//            if (!aplyMdlList.contains(xBizMsg.mdlId_X.getValue()) //
//                    && !isChangedModelPricingTier(xBizMsg, glblMsg)) {
//                continue;
//            }
// END   2017/06/06 [QC#18318, DEL]
            for (int ixV = 0; ixV < bizMsg.V.getValidCount(); ixV++) {
                NSAL1330_VCMsg vBizMsg = bizMsg.V.no(ixV);
                // START 2017/06/06 [QC#18318, DEL]
                // if (!ZYPCommonFunc.hasValue(vBizMsg.dsContrBllgMtrPk_V)) {
                //     continue;
                // }
                // END   2017/06/06 [QC#18318, DEL]
                if (!isSameValue(xBizMsg.mdlId_X, vBizMsg.mdlId_V)) {
                    continue;
                }
                if (!S21StringUtil.isEquals(xBizMsg.bllgMtrLbCd_X.getValue(), vBizMsg.bllgMtrLbCd_V.getValue())) {
                    continue;
                }
                if (!S21StringUtil.isEquals(xBizMsg.prcSvcTierTpCd_X.getValue(), vBizMsg.prcSvcTierTpCd_V.getValue())) {
                    continue;
                }
                if (isChangedConfigPricingTier(vBizMsg, glblMsg)) {
                    // START 2017/06/06 [QC#18318, ADD]
                    if (!hasDifferencePricingTier(vBizMsg, xBizMsg)) {
                        continue;
                    }
                    // END   2017/06/06 [QC#18318, ADD]

                    bizMsg.submtFlg_W.clear();
                    return;
                }
                // START 2017/06/06 [QC#18318, ADD]
                if (hasDifferencePricingTier(vBizMsg, xBizMsg)) {
                    bizMsg.submtFlg_W.clear();
                    return;
                }
                // END   2017/06/06 [QC#18318, ADD]
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.submtFlg_W, ZYPConstant.FLG_ON_Y);

    }

    public static void setTierInfoInputted(NSAL1330CMsg bizMsg) {
        List<String> tierKeyList = new ArrayList<String>(bizMsg.X.getValidCount());
        List<String> tierWkList = new ArrayList<String>(bizMsg.X.getValidCount());
        for (int i = 0; i < bizMsg.X.getValidCount(); i++) {
            // START 2017/06/12 [QC#18842, MOD]
            // String tierKey = bizMsg.X.no(i).bllgMtrLbCd_X.getValue();
            String tierKey;
            if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
                tierKey = bizMsg.X.no(i).bllgMtrLbCd_X.getValue();

            } else {
                tierKey = S21StringUtil.concatStrings(
                        bizMsg.X.no(i).mdlId_X.getValue().toPlainString()
                      , ","
                      , bizMsg.X.no(i).bllgMtrLbCd_X.getValue());
            }
            // END   2017/06/12 [QC#18842, MOD]
            if (tierWkList.contains(tierKey)) {
                tierKeyList.add(tierKey);
            } else {
                tierWkList.add(tierKey);
            }
        }

        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
            // START 2017/06/12 [QC#18842, MOD]
            // String tierKey = bizMsg.Z.no(i).bllgMtrLbCd_Z.getValue();
            String tierKey;
            if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
                tierKey = bizMsg.Z.no(i).bllgMtrLbCd_Z.getValue();
            } else {
                tierKey = S21StringUtil.concatStrings(
                        bizMsg.Z.no(i).mdlId_Z.getValue()
                      , ","
                      , bizMsg.Z.no(i).bllgMtrLbCd_Z.getValue());
            }
            // END   2017/06/12 [QC#18842, MOD]
            if (tierKeyList.contains(tierKey)) {
                bizMsg.Z.no(i).xxScrEdtTpCd_Z.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                bizMsg.Z.no(i).xxScrEdtTpCd_Z.setValue(ZYPConstant.FLG_OFF_N);
            }
        }
    }

    private static void setHdrRsltToBizMsg(NSAL1330CMsg bizMsg, Map<String, Object> rsltMap) {
        if (rsltMap != null && !rsltMap.isEmpty()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrPk, (BigDecimal) rsltMap.get("DS_CONTR_PK"));
            ZYPEZDItemValueSetter.setValue(bizMsg.refCpoOrdNum, (String) rsltMap.get("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem50Txt, (String) rsltMap.get("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shellLineNum, (BigDecimal) rsltMap.get("SHELL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.svcPgmMdseCd, (String) rsltMap.get("SVC_PGM_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.prcSvcContrTpCd, (String) rsltMap.get("PRC_SVC_CONTR_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.prcSvcContrTpDescTxt, (String) rsltMap.get("PRC_SVC_CONTR_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.prcSvcPlnTpCd, (String) rsltMap.get("PRC_SVC_PLN_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.prcSvcPlnTpDescTxt, (String) rsltMap.get("PRC_SVC_PLN_TP_DESC_TXT"));

            ZYPEZDItemValueSetter.setValue(bizMsg.fromPerMthNum, (BigDecimal) rsltMap.get("FROM_PER_MTH_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.toPerMthNum, (BigDecimal) rsltMap.get("TO_PER_MTH_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.termMthAot, (BigDecimal) rsltMap.get("TERM_MTH_AOT"));

            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrCatgCd, (String) rsltMap.get("DS_CONTR_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrCatgDescTxt, (String) rsltMap.get("DS_CONTR_CATG_DESC_TXT"));

            if (ZYPConstant.FLG_ON_Y.equals((String) rsltMap.get("ADD_CONTR_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsContrNum_AD, (String) rsltMap.get("DS_CONTR_NUM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsContrPk_AD, (BigDecimal) rsltMap.get("DS_CONTR_PK"));
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.baseBllgCycleCd, (String) rsltMap.get("BASE_BLLG_CYCLE_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.baseBllgCycleDescTxt, (String) rsltMap.get("BASE_BLLG_CYCLE_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.usgBllgCycleCd, (String) rsltMap.get("MTR_BLLG_CYCLE_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.usgBllgCycleDescTxt, (String) rsltMap.get("MTR_BLLG_CYCLE_DESC_TXT"));

            // START 2018/11/15 [QC#28638, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.fixTermInMthAot, (BigDecimal) rsltMap.get("FIX_TERM_IN_MTH_AOT"));
            // END 2018/11/15 [QC#28638, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.billWithEquipFlg, (String) rsltMap.get("BILL_WITH_EQUIP_FLG"));

            ZYPEZDItemValueSetter.setValue(bizMsg.billByTpCd, (String) rsltMap.get("BILL_BY_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.billByTpDescTxt, (String) rsltMap.get("BILL_BY_TP_DESC_TXT"));

            // Mod Start 2017/10/19 QC#21860
            GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
            int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
//            ZYPEZDItemValueSetter.setValue(bizMsg.basePrcDealAmt, (BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.basePrcDealAmt, round((BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"), scale));
            // Mod End 2017/10/19 QC#21860

            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm, (String) rsltMap.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum, (String) rsltMap.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxGenlFldAreaTxt_BI, (String) rsltMap.get("BILL_TO_SRCH_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, (String) rsltMap.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, (String) rsltMap.get("ALT_PAYER_CUST_CD"));

            ZYPEZDItemValueSetter.setValue(bizMsg.cpoSvcAgmtVerNum, (String) rsltMap.get("CPO_SVC_AGMT_VER_NUM"));

            ZYPEZDItemValueSetter.setValue(bizMsg.manContrOvrdFlg, (String) rsltMap.get("MAN_CONTR_OVRD_FLG"));
            ZYPEZDItemValueSetter.setValue(bizMsg.svcMemoRsnCd, (String) rsltMap.get("SVC_MEMO_RSN_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.svcCmntTxt, (String) rsltMap.get("SVC_CMNT_TXT"));

            // Mod Start 2017/10/19 QC#21860
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt, (BigDecimal) rsltMap.get("TOT_BASE_PRC_DEAL_AMT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt, round((BigDecimal) rsltMap.get("TOT_BASE_PRC_DEAL_AMT"), scale));
            // Mod End 2017/10/19 QC#21860

            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, (String) rsltMap.get("DS_ORD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, (String) rsltMap.get("DS_ORD_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.addAsryFlg, (String) rsltMap.get("ADD_ASRY_FLG"));
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime, (String) rsltMap.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone, (String) rsltMap.get("EZUPTIMEZONE"));

            ZYPEZDItemValueSetter.setValue(bizMsg.contrAvalFlg, (String) rsltMap.get("CONTR_CRAT_FLG"));
            // Add Start 2018/07/02 QC#26738
            String ordBookFlg = (String) rsltMap.get("ORD_BOOK_FLG");
            if (!ZYPCommonFunc.hasValue(ordBookFlg)) {
                ordBookFlg = ZYPConstant.FLG_OFF_N;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.ordBookFlg, ordBookFlg);
            // Add End 2018/07/02 QC#26738
            // 2018/08/24 S21_NA#25105 Add Start
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_HJ, (String) rsltMap.get("ADDL_BASE_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_HB, (String) rsltMap.get("RNTL_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_C, (String) rsltMap.get("ADDL_CHRG_PRC_CATG_CD"));
            // 2018/08/24 S21_NA#25105 Add End
        }
    }

    private static void setModelDtlHeaderRsltToBizMsg(NSAL1330CMsg bizMsg, List<Map<String, Object>> rsltList) {

        int ixA = 0;
        // Add Start 2017/10/19 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21860
        for (Map<String, Object> rsltMap : rsltList) {
            NSAL1330_ACMsg aBizMsg = bizMsg.A.no(ixA);

            ZYPEZDItemValueSetter.setValue(aBizMsg.dsContrDtlPk_A, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.mdlId_A, (BigDecimal) rsltMap.get("MDL_ID"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.t_MdlNm_A, (String) rsltMap.get("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotQtyCnt_A, (BigDecimal) rsltMap.get("MDL_QTY"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcCatgCd_A, (String) rsltMap.get("PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcCatgNm_A, (String) rsltMap.get("PRC_CATG_NM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcListTpCd_A, (String) rsltMap.get("PRC_LIST_TP_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcMtrPkgPk_A, (BigDecimal) rsltMap.get("PRC_MTR_PKG_PK"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcTierSvcOfferCd_A, (String) rsltMap.get("PRC_TIER_SVC_OFFER_CD"));
            // Mod Start 2017/10/19 QC#21860
//            ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_PB, (BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"));
//            ZYPEZDItemValueSetter.setValue(aBizMsg.basePrcDealAmt_A, (BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"));
//            ZYPEZDItemValueSetter.setValue(aBizMsg.dealPrcListPrcAmt_A, (BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_PB, round((BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(aBizMsg.basePrcDealAmt_A, round((BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(aBizMsg.dealPrcListPrcAmt_A, round((BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"), scale));
            // Mod End 2017/10/19 QC#21860
            ZYPEZDItemValueSetter.setValue(aBizMsg.termMthAot_A, (BigDecimal) rsltMap.get("TERM_MTH_AOT"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.scrEntAvalFlg_A, (String) rsltMap.get("SCR_ENT_AVAL_FLG"));
            // START 2017/10/05 [QC#20059, ADD]
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxSelFlg_A, (String) rsltMap.get("CONFIG_PRC_SET_FLG"));
            // END   2017/10/05 [QC#20059, ADD]

//            if (!DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) { // QC#29248
                if (ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_A)) {
                    ZYPEZDItemValueSetter.setValue(aBizMsg.prcRateTpCd_A, getPrcRateTpCd(bizMsg, aBizMsg));
                }
//            }

            ixA++;
        }

        if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
            ixA = 1;
        }

        bizMsg.A.setValidCount(ixA);
    }

    private static void setModelDtlRsltToBizMsg(NSAL1330CMsg bizMsg, List<Map<String, Object>> rsltList) {

        int ixX = 0;
        int ixZ = 0;
        // START 2017/07/05 [QC#19479, ADD]
        BigDecimal contrXsCopyPkPrev = BigDecimal.ONE.negate();
        // END   2017/07/05 [QC#19479, ADD]
        for (Map<String, Object> rsltMap : rsltList) {
            BigDecimal prcSvcTierTpSortNum = (BigDecimal) rsltMap.get("PRC_SVC_TIER_TP_SORT_NUM");
            String xxFlgNm = (String) rsltMap.get("XX_FLG_NM");

            if (ZYPCommonFunc.hasValue(prcSvcTierTpSortNum)) {
                // Tier line
                NSAL1330_XCMsg xBizMsg = bizMsg.X.no(ixX);

                BigDecimal minCopyVolCnt = (BigDecimal) rsltMap.get("XS_MTR_COPY_QTY");
                minCopyVolCnt = minCopyVolCnt.add(BigDecimal.ONE);
                BigDecimal maxCopyVolCnt = (BigDecimal) rsltMap.get("MAX_COPY_VOL_CNT");

                // START 2017/07/05 [QC#19479, ADD]
                BigDecimal contrXsCopyPk = (BigDecimal) rsltMap.get("CONTR_XS_COPY_PK");
                if (ZYPCommonFunc.hasValue(contrXsCopyPkPrev) && contrXsCopyPkPrev.compareTo(contrXsCopyPk) == 0) {
                    continue;
                }
                // END   2017/07/05 [QC#19479, ADD]

                ZYPEZDItemValueSetter.setValue(xBizMsg.contrXsCopyPk_X, (BigDecimal) rsltMap.get("CONTR_XS_COPY_PK"));
                ZYPEZDItemValueSetter.setValue(xBizMsg.contrPhysBllgMtrRelnPk_X, (BigDecimal) rsltMap.get("CONTR_PHYS_BLLG_MTR_RELN_PK"));
                ZYPEZDItemValueSetter.setValue(xBizMsg.dsContrBllgMtrPk_X, (BigDecimal) rsltMap.get("DS_CONTR_BLLG_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(xBizMsg.mdlId_X, (BigDecimal) rsltMap.get("MDL_ID"));
                ZYPEZDItemValueSetter.setValue(xBizMsg.bllgMtrLbCd_X, (String) rsltMap.get("BLLG_MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(xBizMsg.minCopyVolCnt_X, minCopyVolCnt);
                ZYPEZDItemValueSetter.setValue(xBizMsg.maxCopyVolCnt_X, maxCopyVolCnt);
                ZYPEZDItemValueSetter.setValue(xBizMsg.xsMtrAmtRate_X, (BigDecimal) rsltMap.get("XS_MTR_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(xBizMsg.prcSvcTierTpCd_X, (String) rsltMap.get("PRC_SVC_TIER_TP_CD"));
                ZYPEZDItemValueSetter.setValue(xBizMsg.prcSvcTierTpDescTxt_X, (String) rsltMap.get("PRC_SVC_TIER_TP_DESC_TXT"));

                // START 2017/07/05 [QC#19479, ADD]
                contrXsCopyPkPrev = (BigDecimal) rsltMap.get("CONTR_XS_COPY_PK");
                // END   2017/07/05 [QC#19479, ADD]

                ixX++;
            }

            if (XX_FLG_PARENT.equals(xxFlgNm) || XX_FLG_HARD.equals(xxFlgNm)) {
                // Usage line
                NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(ixZ);

                ZYPEZDItemValueSetter.setValue(zBizMsg.dsContrDtlPk_Z, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.dsContrBllgMtrPk_Z, (BigDecimal) rsltMap.get("DS_CONTR_BLLG_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.contrXsCopyPk_Z, (BigDecimal) rsltMap.get("CONTR_XS_COPY_PK"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.mdlId_Z, (BigDecimal) rsltMap.get("MDL_ID"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.t_MdlNm_Z, (String) rsltMap.get("T_MDL_NM"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.prcCatgCd_Z, (String) rsltMap.get("PRC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.prcListBandDescTxt_Z, (String) rsltMap.get("PRC_LIST_BAND_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.prcBookMdseCd_Z, (String) rsltMap.get("PRC_BOOK_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.bllgMtrLbCd_Z, (String) rsltMap.get("BLLG_MTR_LB_CD"));
                // START 2017/06/12 [QC#18873, ADD]
                ZYPEZDItemValueSetter.setValue(zBizMsg.mtrLbDescTxt_ZB, (String) rsltMap.get("BLLG_MTR_LB_DESC_TXT"));
                // END   2017/06/12 [QC#18873, ADD]
                ZYPEZDItemValueSetter.setValue(zBizMsg.usgMdseCd_Z, (String) rsltMap.get("INTG_MDSE_CD"));
                if (!ZYPCommonFunc.hasValue(prcSvcTierTpSortNum)) {
                    ZYPEZDItemValueSetter.setValue(zBizMsg.mlyCopyInclPrcQty_Z, (BigDecimal) rsltMap.get("XS_MTR_COPY_QTY"));
                    ZYPEZDItemValueSetter.setValue(zBizMsg.xsMtrAmtRate_Z, (BigDecimal) rsltMap.get("XS_MTR_AMT_RATE"));
                } else {
                    zBizMsg.mlyCopyInclPrcQty_Z.clear();
                    zBizMsg.xsMtrAmtRate_Z.clear();
                }
                ZYPEZDItemValueSetter.setValue(zBizMsg.regMtrLbCd_Z, (String) rsltMap.get("REG_MTR_LB_CD"));
                // START 2017/06/12 [QC#18873, ADD]
                ZYPEZDItemValueSetter.setValue(zBizMsg.mtrLbDescTxt_Z, (String) rsltMap.get("REG_MTR_LB_DESC_TXT"));
                // END   2017/06/12 [QC#18873, ADD]
                ZYPEZDItemValueSetter.setValue(zBizMsg.contrMtrMultRate_Z, (BigDecimal) rsltMap.get("CONTR_MTR_MULT_RATE"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.prcSvcTierTpCd_Z, (String) rsltMap.get("PRC_SVC_TIER_TP_CD"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.minCopyVolCnt_Z, (BigDecimal) rsltMap.get("XS_MTR_COPY_QTY"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.maxCopyVolCnt_Z, (BigDecimal) rsltMap.get("MAX_COPY_VOL_CNT"));
                if (XX_FLG_PARENT.equals(xxFlgNm)) {
                    ZYPEZDItemValueSetter.setValue(zBizMsg.xxSmryLineFlg_Z, ZYPConstant.FLG_ON_Y);
                }
                ZYPEZDItemValueSetter.setValue(zBizMsg.xxFlgNm_Z, (String) rsltMap.get("XX_FLG_NM"));
                // 2018/05/09 QC#25030 add start
                ZYPEZDItemValueSetter.setValue(zBizMsg.bllgFreeCopyCnt_Z, (BigDecimal) rsltMap.get("BLLG_FREE_COPY_CNT"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.bllgMinCnt_Z, (BigDecimal) rsltMap.get("BLLG_MIN_CNT"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.bllgMinAmtRate_Z, (BigDecimal) rsltMap.get("BLLG_MIN_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.bllgRollOverRatio_Z, (BigDecimal) rsltMap.get("BLLG_ROLL_OVER_RATIO"));
                // 2018/05/09 QC#25030 add end

                ixZ++;
            }
        }
        bizMsg.X.setValidCount(ixX);
        bizMsg.Z.setValidCount(ixZ);
    }

    private static void setConfigDtlHeaderRsltToBizMsg(NSAL1330CMsg bizMsg, List<Map<String, Object>> rsltList, NSAL1330SMsg glblMsg) {

        int ixR = 0;
        // Add Start 2017/10/19 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21860
        for (Map<String, Object> rsltMap : rsltList) {
            NSAL1330_RCMsg rBizMsg = bizMsg.R.no(ixR);

            ZYPEZDItemValueSetter.setValue(rBizMsg.dsContrDtlPk_R, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.mdlId_R, (BigDecimal) rsltMap.get("MDL_ID"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.t_MdlNm_R, (String) rsltMap.get("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.prcCatgCd_R, (String) rsltMap.get("PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.prcCatgNm_R, (String) rsltMap.get("PRC_CATG_NM"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.prcMtrPkgPk_R, (BigDecimal) rsltMap.get("PRC_MTR_PKG_PK"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.prcTierSvcOfferCd_R, (String) rsltMap.get("PRC_TIER_SVC_OFFER_CD"));
            // Mod Start 2017/10/19 QC#21860
//            ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_PR, (BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"));
//            ZYPEZDItemValueSetter.setValue(rBizMsg.basePrcDealAmt_R, (BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"));
//            ZYPEZDItemValueSetter.setValue(rBizMsg.dealPrcListPrcAmt_R, (BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_PR, round((BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(rBizMsg.basePrcDealAmt_R, round((BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(rBizMsg.dealPrcListPrcAmt_R, round((BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"), scale));
            // Mod End 2017/10/19 QC#21860
            ZYPEZDItemValueSetter.setValue(rBizMsg.termMthAot_R, (BigDecimal) rsltMap.get("TERM_MTH_AOT"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.scrEntAvalFlg_R, (String) rsltMap.get("SCR_ENT_AVAL_FLG"));

            if (ZYPCommonFunc.hasValue(rBizMsg.prcMtrPkgPk_R)) {
                ZYPEZDItemValueSetter.setValue(rBizMsg.prcRateTpCd_R, getPrcRateTpCd(bizMsg, rBizMsg));
            }

            ZYPEZDItemValueSetter.setValue(rBizMsg.dsOrdPosnNum_R, (String) rsltMap.get("DS_ORD_POSN_NUM"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.billToCustCd_R, (String) rsltMap.get("SELL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.dsAcctNm_R, (String) rsltMap.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.billToLocNum_R, (String) rsltMap.get("BASE_BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.prcListTpCd_R, (String) rsltMap.get("PRC_LIST_TP_CD"));
            if (ZYPCommonFunc.hasValue(rBizMsg.billToLocNum_R)) {
                ZYPEZDItemValueSetter.setValue(rBizMsg.xxGenlFldAreaTxt_R, (String) rsltMap.get("BILL_TO_SRCH_TXT"));
            }

            // START 2017/10/25 K.Kojima [QC#21690,DEL]
            // if (!ZYPCommonFunc.hasValue(rBizMsg.dsContrDtlPk_R)) {
            //     S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getDefaultMdlSvcPrc(bizMsg);
            //     List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            //     if (ssmResult.getQueryResultCount() > 0) {
            //         Map<String, Object> resultMap = resList.get(0);
            //         ZYPEZDItemValueSetter.setValue(rBizMsg.prcCatgCd_R, (String) resultMap.get("DEF_MAINT_PRC_CATG_CD"));
            //         ZYPEZDItemValueSetter.setValue(rBizMsg.prcCatgNm_R, (String) resultMap.get("PRC_CATG_NM"));
            //         ZYPEZDItemValueSetter.setValue(rBizMsg.prcListTpCd_R, (String) resultMap.get("PRC_LIST_TP_CD"));
            //     }
            // }
            // END 2017/10/25 K.Kojima [QC#21690,DEL]

            deriveMtrPkgPulldownForNonFleetConfig(bizMsg, glblMsg, ixR);

            ixR++;
        }
        bizMsg.R.setValidCount(ixR);
    }

    private static void setConfigDtlRsltToBizMsg(NSAL1330CMsg bizMsg, List<Map<String, Object>> rsltList) {

        int ixU = 0;
        int ixV = 0;
        // START 2017/07/05 [QC#19479, ADD]
        BigDecimal contrXsCopyPkPrev = BigDecimal.ONE.negate();
        // END   2017/07/05 [QC#19479, ADD]

        for (Map<String, Object> rsltMap : rsltList) {
            BigDecimal prcSvcTierTpSortNum = (BigDecimal) rsltMap.get("PRC_SVC_TIER_TP_SORT_NUM");
            String xxFlgNm = (String) rsltMap.get("XX_FLG_NM");

            if (ZYPCommonFunc.hasValue(prcSvcTierTpSortNum)) {
                // Tier line
                NSAL1330_VCMsg vBizMsg = bizMsg.V.no(ixV);

                BigDecimal minCopyVolCnt = (BigDecimal) rsltMap.get("XS_MTR_COPY_QTY");
                minCopyVolCnt = minCopyVolCnt.add(BigDecimal.ONE);
                BigDecimal maxCopyVolCnt = (BigDecimal) rsltMap.get("MAX_COPY_VOL_CNT");

                // START 2017/07/05 [QC#19479, ADD]
                BigDecimal contrXsCopyPk = (BigDecimal) rsltMap.get("CONTR_XS_COPY_PK");
                if (ZYPCommonFunc.hasValue(contrXsCopyPkPrev) && contrXsCopyPkPrev.compareTo(contrXsCopyPk) == 0) {
                    continue;
                }
                // END   2017/07/05 [QC#19479, ADD]

                ZYPEZDItemValueSetter.setValue(vBizMsg.dsContrDtlPk_V, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
                ZYPEZDItemValueSetter.setValue(vBizMsg.dsContrBllgMtrPk_V, (BigDecimal) rsltMap.get("DS_CONTR_BLLG_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(vBizMsg.contrXsCopyPk_V, (BigDecimal) rsltMap.get("CONTR_XS_COPY_PK"));
                ZYPEZDItemValueSetter.setValue(vBizMsg.dsOrdPosnNum_V, (String) rsltMap.get("DS_ORD_POSN_NUM"));
                ZYPEZDItemValueSetter.setValue(vBizMsg.mdlId_V, (BigDecimal) rsltMap.get("MDL_ID"));
                ZYPEZDItemValueSetter.setValue(vBizMsg.bllgMtrLbCd_V, (String) rsltMap.get("BLLG_MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(vBizMsg.minCopyVolCnt_V, minCopyVolCnt);
                ZYPEZDItemValueSetter.setValue(vBizMsg.maxCopyVolCnt_V, maxCopyVolCnt);
                ZYPEZDItemValueSetter.setValue(vBizMsg.xsMtrAmtRate_V, (BigDecimal) rsltMap.get("XS_MTR_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(vBizMsg.prcSvcTierTpCd_V, (String) rsltMap.get("PRC_SVC_TIER_TP_CD"));
                ZYPEZDItemValueSetter.setValue(vBizMsg.prcSvcTierTpDescTxt_V, (String) rsltMap.get("PRC_SVC_TIER_TP_DESC_TXT"));

                // START 2017/07/05 [QC#19479, ADD]
                contrXsCopyPkPrev = (BigDecimal) rsltMap.get("CONTR_XS_COPY_PK");
                // END   2017/07/05 [QC#19479, ADD]

                ixV++;
            }

            if (XX_FLG_PARENT.equals(xxFlgNm) || XX_FLG_HARD.equals(xxFlgNm)) {
                // Usage line
                NSAL1330_UCMsg uBizMsg = bizMsg.U.no(ixU);

                ZYPEZDItemValueSetter.setValue(uBizMsg.dsContrDtlPk_U, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.dsContrBllgMtrPk_U, (BigDecimal) rsltMap.get("DS_CONTR_BLLG_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.contrXsCopyPk_U, (BigDecimal) rsltMap.get("CONTR_XS_COPY_PK"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.mdlId_U, (BigDecimal) rsltMap.get("MDL_ID"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.prcCatgCd_U, (String) rsltMap.get("PRC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.prcListBandDescTxt_U, (String) rsltMap.get("PRC_LIST_BAND_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.prcBookMdseCd_U, (String) rsltMap.get("PRC_BOOK_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.bllgMtrLbCd_U, (String) rsltMap.get("BLLG_MTR_LB_CD"));
                // START 2017/06/12 [QC#18873, ADD]
                ZYPEZDItemValueSetter.setValue(uBizMsg.mtrLbDescTxt_UB, (String) rsltMap.get("BLLG_MTR_LB_DESC_TXT"));
                // END   2017/06/12 [QC#18873, ADD]
                ZYPEZDItemValueSetter.setValue(uBizMsg.usgMdseCd_U, (String) rsltMap.get("INTG_MDSE_CD"));

                if (!ZYPCommonFunc.hasValue(prcSvcTierTpSortNum)) {
                    ZYPEZDItemValueSetter.setValue(uBizMsg.mlyCopyInclPrcQty_U, (BigDecimal) rsltMap.get("XS_MTR_COPY_QTY"));
                    ZYPEZDItemValueSetter.setValue(uBizMsg.xsMtrAmtRate_U, (BigDecimal) rsltMap.get("XS_MTR_AMT_RATE"));
                } else {
                    uBizMsg.mlyCopyInclPrcQty_U.clear();
                    uBizMsg.xsMtrAmtRate_U.clear();
                }

                ZYPEZDItemValueSetter.setValue(uBizMsg.regMtrLbCd_U, (String) rsltMap.get("REG_MTR_LB_CD"));
                // START 2017/06/12 [QC#18873, ADD]
                ZYPEZDItemValueSetter.setValue(uBizMsg.mtrLbDescTxt_U, (String) rsltMap.get("REG_MTR_LB_DESC_TXT"));
                // END   2017/06/12 [QC#18873, ADD]
                ZYPEZDItemValueSetter.setValue(uBizMsg.contrMtrMultRate_U, (BigDecimal) rsltMap.get("CONTR_MTR_MULT_RATE"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.prcSvcTierTpCd_U, (String) rsltMap.get("PRC_SVC_TIER_TP_CD"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.minCopyVolCnt_U, (BigDecimal) rsltMap.get("XS_MTR_COPY_QTY"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.maxCopyVolCnt_U, (BigDecimal) rsltMap.get("MAX_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.actvFlg_U, (String) rsltMap.get("ACTV_FLG"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.dsOrdPosnNum_U, (String) rsltMap.get("DS_ORD_POSN_NUM"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.billToCustCd_U, (String) rsltMap.get("SELL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.dsAcctNm_U, (String) rsltMap.get("DS_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.billToLocNum_U, (String) rsltMap.get("BILL_TO_CUST_CD"));
                if (ZYPCommonFunc.hasValue(uBizMsg.billToLocNum_U)) {
                    ZYPEZDItemValueSetter.setValue(uBizMsg.xxGenlFldAreaTxt_U, (String) rsltMap.get("BILL_TO_SRCH_TXT"));
                }
                if (XX_FLG_PARENT.equals((String) rsltMap.get("XX_FLG_NM"))) {
                    ZYPEZDItemValueSetter.setValue(uBizMsg.xxSmryLineFlg_U, ZYPConstant.FLG_ON_Y);
                }
                ZYPEZDItemValueSetter.setValue(uBizMsg.xxFlgNm_U, (String) rsltMap.get("XX_FLG_NM"));
                // 2018/05/09 QC#25030 add start
                ZYPEZDItemValueSetter.setValue(uBizMsg.bllgFreeCopyCnt_U, (BigDecimal) rsltMap.get("BLLG_FREE_COPY_CNT"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.bllgMinCnt_U, (BigDecimal) rsltMap.get("BLLG_MIN_CNT"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.bllgMinAmtRate_U, (BigDecimal) rsltMap.get("BLLG_MIN_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.bllgRollOverRatio_U, (BigDecimal) rsltMap.get("BLLG_ROLL_OVER_RATIO"));
                // 2018/05/09 QC#25030 add end

                ixU++;
            }

        }
        bizMsg.U.setValidCount(ixU);
        bizMsg.V.setValidCount(ixV);
    }

    private static void setAccChrgRsltToBizMsg(NSAL1330CMsg bizMsg, List<Map<String, Object>> rsltList) {
        int ixJ = 0;
        // Add Start 2017/10/19 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21860
        for (Map<String, Object> rsltMap : rsltList) {
            // Accessory Charge
            NSAL1330_JCMsg jBizMsg = bizMsg.J.no(ixJ);

            ZYPEZDItemValueSetter.setValue(jBizMsg.xxLineNum_J, (String) rsltMap.get("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(jBizMsg.mdseCd_J, (String) rsltMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(jBizMsg.mdseDescShortTxt_J, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(jBizMsg.prcCatgCd_J, (String) rsltMap.get("PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(jBizMsg.prcCatgNm_J, (String) rsltMap.get("PRC_CATG_NM"));
            ZYPEZDItemValueSetter.setValue(jBizMsg.prcListEquipConfigNum_J, (BigDecimal) rsltMap.get("PRC_LIST_EQUIP_CONFIG_NUM"));
            ZYPEZDItemValueSetter.setValue(jBizMsg.cpoDtlLineNum_J, (String) rsltMap.get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(jBizMsg.cpoDtlLineSubNum_J, (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(jBizMsg.addlBasePrcCatgCd_J,  (String) rsltMap.get("PRC_CATG_CD"));
            // Mod Start 2017/10/19 QC#21507
//            ZYPEZDItemValueSetter.setValue(jBizMsg.addlBasePrcDealAmt_J, (BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"));
//            ZYPEZDItemValueSetter.setValue(jBizMsg.dealPrcListPrcAmt_J, (BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(jBizMsg.addlBasePrcDealAmt_J, round((BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(jBizMsg.dealPrcListPrcAmt_J, round((BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"), scale));
            // Mod End 2017/10/19 QC#21507
            ZYPEZDItemValueSetter.setValue(jBizMsg.svcPrcCatgCd_J, (String) rsltMap.get("SVC_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(jBizMsg.billWithEquipInvdFlg_J, (String) rsltMap.get("BILL_WITH_EQUIP_INVD_FLG"));
            ZYPEZDItemValueSetter.setValue(jBizMsg.crRebilCd_J, (String) rsltMap.get("CR_REBIL_CD"));
            ZYPEZDItemValueSetter.setValue(jBizMsg.shellLineNum_J, (BigDecimal) rsltMap.get("SHELL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(jBizMsg.scrEntAvalFlg_J, (String) rsltMap.get("SCR_ENT_AVAL_FLG"));

            ZYPEZDItemValueSetter.setValue(jBizMsg.dsContrDtlPk_J, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));

            ixJ++;
        }
        bizMsg.J.setValidCount(ixJ);
    }

    private static void setRntlEqChrgRsltToBizMsg(NSAL1330CMsg bizMsg, List<Map<String, Object>> rsltList) {
        int ixB = 0;
        // Add Start 2017/10/19 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21860
        for (Map<String, Object> rsltMap : rsltList) {
            NSAL1330_BCMsg bBizMsg = bizMsg.B.no(ixB);

            // Rental Eq Charge Base & Accessory
            ZYPEZDItemValueSetter.setValue(bBizMsg.dsContrDtlPk_B, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.dsContrAddlChrgPk_B, (BigDecimal) rsltMap.get("DS_CONTR_ADDL_CHRG_PK"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.shellLineNum_B, (BigDecimal) rsltMap.get("SHELL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.cpoDtlLineNum_B, (String) rsltMap.get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.cpoDtlLineSubNum_B, (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.xxLineNum_B, (String) rsltMap.get("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.mdseCd_B, (String) rsltMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.mdseDescShortTxt_B, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcCatgCd_B, (String) rsltMap.get("PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcCatgNm_B, (String) rsltMap.get("PRC_CATG_NM"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.addlBasePrcCatgCd_B, (String) rsltMap.get("PRC_CATG_CD"));
            // Mod Start 2017/10/19 QC#21507
//            ZYPEZDItemValueSetter.setValue(bBizMsg.addlBasePrcDealAmt_B, (BigDecimal) rsltMap.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
//            ZYPEZDItemValueSetter.setValue(bBizMsg.dealPrcListPrcAmt_B, (BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"));
            // Mod Start 2018/12/11 QC#29423
//            ZYPEZDItemValueSetter.setValue(bBizMsg.addlBasePrcDealAmt_B, round((BigDecimal) rsltMap.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"), scale));
            if (ZYPCommonFunc.hasValue((BigDecimal) rsltMap.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"))) {
                ZYPEZDItemValueSetter.setValue(bBizMsg.addlBasePrcDealAmt_B, round((BigDecimal) rsltMap.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"), scale));
            }
            // Mod End 2018/12/11 QC#29423
            ZYPEZDItemValueSetter.setValue(bBizMsg.dealPrcListPrcAmt_B, round((BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"), scale));
            // Mod End 2017/10/19 QC#21507
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcListEquipConfigNum_B, (BigDecimal) rsltMap.get("PRC_LIST_EQUIP_CONFIG_NUM"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcListEquipConfigNm_B, (String) rsltMap.get("PRC_LIST_EQUIP_CONFIG_NM"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.svcPrcCatgCd_B, (String) rsltMap.get("SVC_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.billWithEquipInvdFlg_B, (String) rsltMap.get("BILL_WITH_EQUIP_INVD_FLG"));
            ZYPEZDItemValueSetter.setValue(bBizMsg.crRebilCd_B, (String) rsltMap.get("CR_REBIL_CD"));

            ZYPEZDItemValueSetter.setValue(bBizMsg.scrEntAvalFlg_B, (String) rsltMap.get("SCR_ENT_AVAL_FLG"));

            ixB++;
        }
        bizMsg.B.setValidCount(ixB);
    }

    private static void setAddlSvcChrgRsltToBizMsg(NSAL1330CMsg bizMsg, List<Map<String, Object>> rsltList) {
        if (rsltList.size() > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_C, (String) rsltList.get(0).get("PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_C, (String) rsltList.get(0).get("PRC_CATG_NM"));
        }

        int i = 0;
        // Add Start 2017/10/19 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21860
        for (Map<String, Object> rsltMap : rsltList) {
            NSAL1330_CCMsg cBizMsg = bizMsg.C.no(i);

            ZYPEZDItemValueSetter.setValue(cBizMsg.xxLineNum_C, (String) rsltMap.get("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.mdseCd_CU, (String) rsltMap.get("MDSE_CD_CU"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.mdseDescShortTxt_CU, (String) rsltMap.get("MDSE_DESC_SHORT_TXT_CU"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.mdseCd_CI, (String) rsltMap.get("MDSE_CD_CI"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.mdseDescShortTxt_CI, (String) rsltMap.get("MDSE_DESC_SHORT_TXT_CI"));
            // Mod Start 2017/10/19 QC#21860
//            ZYPEZDItemValueSetter.setValue(cBizMsg.addlChrgPrcDealAmt_C, (BigDecimal) rsltMap.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
//            ZYPEZDItemValueSetter.setValue(cBizMsg.dealPrcListPrcAmt_C, (BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.addlChrgPrcDealAmt_C, round((BigDecimal) rsltMap.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(cBizMsg.dealPrcListPrcAmt_C, round((BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"), scale));
            // Mod End 2017/10/19 QC#21860
            ZYPEZDItemValueSetter.setValue(cBizMsg.cpoDtlLineNum_C, (String) rsltMap.get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.cpoDtlLineSubNum_C, (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.addlChrgPrcCatgCd_C, (String) rsltMap.get("PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.svcPrcCatgCd_C, (String) rsltMap.get("SVC_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.billWithEquipInvdFlg_C, (String) rsltMap.get("BILL_WITH_EQUIP_INVD_FLG"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.crRebilCd_C, (String) rsltMap.get("CR_REBIL_CD"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.printDtlFlg_C, (String) rsltMap.get("PRINT_DTL_FLG"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.scrEntAvalFlg_C, (String) rsltMap.get("SCR_ENT_AVAL_FLG"));

            ZYPEZDItemValueSetter.setValue(cBizMsg.shellLineNum_C, (BigDecimal) rsltMap.get("SHELL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.dsContrAddlChrgPk_C, (BigDecimal) rsltMap.get("DS_CONTR_ADDL_CHRG_PK"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.dsContrDtlPk_C, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));

            i++;
        }
        bizMsg.C.setValidCount(i);
    }

    private static void setDsOrdPosnRsltToBizMsg(NSAL1330CMsg bizMsg, List<Map<String, Object>> rsltList) {
        int i = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            NSAL1330_ICMsg iBizMsg = bizMsg.I.no(i);

            ZYPEZDItemValueSetter.setValue(iBizMsg.dsOrdPosnNum_I, (String) rsltMap.get("DS_ORD_POSN_NUM"));
            ZYPEZDItemValueSetter.setValue(iBizMsg.mdlId_I, (BigDecimal) rsltMap.get("MDL_ID"));
            ZYPEZDItemValueSetter.setValue(iBizMsg.t_MdlNm_I, (String) rsltMap.get("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(iBizMsg.cpoDtlLineNum_I, (String) rsltMap.get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(iBizMsg.cpoDtlLineSubNum_I, (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(iBizMsg.mtrReadMethCd_I, (String) rsltMap.get("MTR_READ_METH_CD"));
            ZYPEZDItemValueSetter.setValue(iBizMsg.dsContrDtlPk_I, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(iBizMsg.crRebilCd_I, (String) rsltMap.get("CR_REBIL_CD"));
            ZYPEZDItemValueSetter.setValue(iBizMsg.svcConfigMstrPk_I, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(iBizMsg.custPoNum_I, (String) rsltMap.get("CUST_PO_NUM"));
            ZYPEZDItemValueSetter.setValue(iBizMsg.poDt_I, (String) rsltMap.get("PO_DT"));
            // 2018/04/24 QC#20162 Add Start
            ZYPEZDItemValueSetter.setValue(iBizMsg.dsPoExprDt_I, (String) rsltMap.get("DS_PO_EXPR_DT"));
            // 2018/04/24 QC#20162 Add End

            i++;
        }
        bizMsg.I.setValidCount(i);
    }

    /**
     * @param bizMsg NSAL1330CMsg
     * @param glblMsg NSAL1330SMsg
     */
    public static void saveShell(String glblCmpyCd, NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {

        if (checkShell(glblCmpyCd, bizMsg, glblMsg)) {
            return; // error
        }

        NSZC115001PMsg pMsg = getShellApiPMsg(glblCmpyCd, bizMsg, glblMsg);
        // START 2017/10/20 [QC#21656, ADD]
        if (pMsg == null) {
            return;
        }
        // END   2017/10/20 [QC#21656, ADD]
        new NSZC115001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);
            if (NSAM0682E.equals(msgList.get(0))) {
                setErrorMessageForNSAM0682E(bizMsg);
            // START 2023/11/08 R.Jin [QC#62108, DEL]
            // START 2023/07/05 T.Kojima [QC#61472, ADD]
//            } else if (NSZM1406E.equals(msgList.get(0))) {
//                bizMsg.setMessageInfo(NSAM0779E);
            // END 2023/07/05 T.Kojima [QC#61472, ADD]
            // END 2023/11/08 R.Jin [QC#62108, DEL]
            } else {
                bizMsg.setMessageInfo(msgList.get(0));
            }
            return;
        }
    }

    private static boolean checkShell(String glblCmpyCd, NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        boolean isError = false;
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.rntlOrdFlg.getValue())) {
            if (NSAL1330CommonLogic.setAcsryChrgCloseForRental(bizMsg, bizMsg.glblCmpyCd.getValue())) {
                return true; // error
            }
            // 2019/07/18 S21_NA#51327 Add Start
//            if (NSAL1330CommonLogic.checkExistsContrDtl(bizMsg)) {
//                return true; // error
//            }
            // 2019/07/18 S21_NA#51327 Add End
        }
        if (NSAL1330CommonLogic.acsryDuplicateCheck(bizMsg)) {
            return true; // error
        }

        // 
        if (isFleet(glblMsg)) {
            isError = checkShellForUsage(bizMsg, isError);
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                // START 2018/07/10 K.Kojima [QC#27135,MOD]
                // isError = checkPrcHdr(bizMsg.A.no(i), isError);
                isError = checkPrcHdr(bizMsg.A.no(i), isError, bizMsg);
                // END 2018/07/10 K.Kojima [QC#27135,MOD]
            }
        } else {
            isError = checkShellForUsageConfig(bizMsg, isError);
            for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
                isError = checkPrcHdrConfig(bizMsg.R.no(i), bizMsg, isError);
            }
        }

        for (int i = 0; i < bizMsg.J.getValidCount(); i++) { // Accessory Charge
            isError = checkAcsyChrg(bizMsg.J.no(i), bizMsg, isError);
        }
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) { // Rental Equipment Charge Base & Accessory
            isError = checkRentalChrg(bizMsg.B.no(i), bizMsg, isError);
        }
        isError = checkAddlSvcChrg(bizMsg, isError);
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) { // Additional Service Charge
            NSAL1330_CCMsg cBizMsg = bizMsg.C.no(i);
            if (!isValidItem(//
                    bizMsg.xxScrItem50Txt.getValue() //
                    , cBizMsg.cpoDtlLineNum_C.getValue() //
                    , cBizMsg.cpoDtlLineSubNum_C.getValue() //
                    , new String[] {MDSE_TP_CTX_TP.CPO_SERVICE_CONFIGURATION_ITEMS } //
                    , "addlSvcChrg")) {
                cBizMsg.mdseCd_CU.setErrorInfo(1, NSAM0674E, new String[] {"item" });
                return true;
            }
        }
        return isError;
    }

    /**
     * item Check - Band, BookItem
     * @param bizMsg NSAL1330CMsg
     * @param isError boolean
     * @return if error then return true.
     */
    private static boolean checkShellForUsage(NSAL1330CMsg bizMsg, boolean isError) {
        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
            NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(i);

            // set hidden from parent
            if (i != 0) {
                if (ZYPCommonFunc.hasValue(zBizMsg.bllgMtrLbCd_Z) //
                        && zBizMsg.bllgMtrLbCd_Z.getValue().equals(bizMsg.Z.no(i - 1).bllgMtrLbCd_Z.getValue()) //
                        && zBizMsg.t_MdlNm_Z.getValue().equals(bizMsg.Z.no(i - 1).t_MdlNm_Z.getValue())) {
                    ZYPEZDItemValueSetter.setValue(zBizMsg.prcListBandDescTxt_Z, bizMsg.Z.no(i - 1).prcListBandDescTxt_Z);
                    ZYPEZDItemValueSetter.setValue(zBizMsg.prcBookMdseCd_Z, bizMsg.Z.no(i - 1).prcBookMdseCd_Z);
                }
            }

            if (ZYPCommonFunc.hasValue(zBizMsg.prcListBandDescTxt_Z)) {
                //                String bandCdNm = ZYPCodeDataUtil.getName("PRC_LIST_BAND", bizMsg.glblCmpyCd.getValue(), bizMsg.Z.no(i).prcListBandDescTxt_Z.getValue());
                List<String> bandList = getPrcListBandCdList(zBizMsg.prcListBandDescTxt_Z.getValue());
                if (bandList == null || bandList.size() == 0) {
                    zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NSAM0647E //
                            , new String[] {"PRC_LIST_BAND", "Band : " + zBizMsg.prcListBandDescTxt_Z.getValue() });
                    isError = true;
                }
                if (bandList != null && bandList.size() > 1) {
                    zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NSAM0680E //
                            , new String[] {"PRC_LIST_BAND", "Band", zBizMsg.prcListBandDescTxt_Z.getValue() });
                    isError = true;
                }
            }
            if (ZYPCommonFunc.hasValue(zBizMsg.prcBookMdseCd_Z)) {

                S21SsmEZDResult ssmResult = null;

                ssmResult = NSAL1330Query.getInstance().getBookItemNm(zBizMsg.prcBookMdseCd_Z.getValue());
                String bookItem = (String) ssmResult.getResultObject();
                if (!ZYPCommonFunc.hasValue(bookItem)) {
                    zBizMsg.prcBookMdseCd_Z.setErrorInfo(1, NSAM0647E, new String[] {"MDSE", zBizMsg.prcBookMdseCd_Z.getValue() });
                    bizMsg.setMessageInfo(NSAM0647E, new String[] {"MDSE", zBizMsg.prcBookMdseCd_Z.getValue() });
                    isError = true;
                }
            }
        }
        return isError;
    }

    /**
     * item Check - Band, BookItem
     * @param bizMsg NSAL1330CMsg
     * @param isError boolean
     * @return if error then return true.
     */
    private static boolean checkShellForUsageConfig(NSAL1330CMsg bizMsg, boolean isError) {
        String bllgMtrLbCd = "";
        BigDecimal dsContrDtlPk = BigDecimal.ZERO;
        for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
            NSAL1330_UCMsg uBizMsg = bizMsg.U.no(i);

            // set hidden from parent
            if (i != 0) {
                if (ZYPCommonFunc.hasValue(uBizMsg.bllgMtrLbCd_U) //
                        && uBizMsg.bllgMtrLbCd_U.getValue().equals(bizMsg.U.no(i - 1).bllgMtrLbCd_U.getValue()) //
                        && uBizMsg.t_MdlNm_U.getValue().equals(bizMsg.U.no(i - 1).t_MdlNm_U.getValue()) //
                        && uBizMsg.dsOrdPosnNum_U.getValue().equals(bizMsg.U.no(i - 1).dsOrdPosnNum_U.getValue())) {
                    ZYPEZDItemValueSetter.setValue(uBizMsg.prcListBandDescTxt_U, bizMsg.U.no(i - 1).prcListBandDescTxt_U);
                    ZYPEZDItemValueSetter.setValue(uBizMsg.prcBookMdseCd_U, bizMsg.U.no(i - 1).prcBookMdseCd_U);
                }
            }
            if (!ZYPConstant.FLG_ON_Y.equals(uBizMsg.actvFlg_U.getValue())) {
                continue;
            }
//            if (!S21StringUtil.isEquals(bllgMtrLbCd, uBizMsg.bllgMtrLbCd_U.getValue()) //
//                    || !isSameValue(cpoSvcConfigRefPk, uBizMsg.cpoSvcConfigRefPk_U.getValue())) {
//                bllgMtrLbCd = uBizMsg.bllgMtrLbCd_U.getValue();
//                cpoSvcConfigRefPk = uBizMsg.cpoSvcConfigRefPk_U.getValue();
//                if (isOverlappingTerm(uBizMsg, bizMsg)) {
//                    uBizMsg.mtrLbDescTxt_UB.setErrorInfo(1, NSAM0678E);
//                    isError = true;
//                    for (int ixA = 0; ixA < bizMsg.A.getValidCount(); ixA++) {
//                        bizMsg.A.no(ixA).xxSmryLineFlg_A.setValue(ZYPConstant.FLG_ON_Y);
//                    }
//                }
//            }
            if (!S21StringUtil.isEquals(bllgMtrLbCd, uBizMsg.bllgMtrLbCd_U.getValue()) //
                    || !isSameValue(dsContrDtlPk, uBizMsg.dsContrDtlPk_U.getValue())) {
                bllgMtrLbCd = uBizMsg.bllgMtrLbCd_U.getValue();
                dsContrDtlPk = uBizMsg.dsContrDtlPk_U.getValue();
                if (isOverlappingTerm(uBizMsg, bizMsg)) {
                    uBizMsg.mtrLbDescTxt_UB.setErrorInfo(1, NSAM0678E);
                    isError = true;
                    for (int ixA = 0; ixA < bizMsg.A.getValidCount(); ixA++) {
                        bizMsg.A.no(ixA).xxSmryLineFlg_A.setValue(ZYPConstant.FLG_ON_Y);
                    }
                }
            }
            
            if (ZYPCommonFunc.hasValue(uBizMsg.prcListBandDescTxt_U)) {
                List<String> bandList = getPrcListBandCdList(uBizMsg.prcListBandDescTxt_U.getValue());
                if (bandList == null || bandList.size() == 0) {
                    uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NSAM0647E //
                            , new String[] {"PRC_LIST_BAND", "Band : " + uBizMsg.prcListBandDescTxt_U.getValue() });
                    isError = true;
                }
                if (bandList != null && bandList.size() > 1) {
                    uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NSAM0680E //
                            , new String[] {"PRC_LIST_BAND", "Band", uBizMsg.prcListBandDescTxt_U.getValue() });
                    isError = true;
                }
            }
            if (ZYPCommonFunc.hasValue(uBizMsg.prcBookMdseCd_U)) {

                S21SsmEZDResult ssmResult = null;

                ssmResult = NSAL1330Query.getInstance().getBookItemNm(uBizMsg.prcBookMdseCd_U.getValue());
                String bookItem = (String) ssmResult.getResultObject();
                if (!ZYPCommonFunc.hasValue(bookItem)) {
                    uBizMsg.prcBookMdseCd_U.setErrorInfo(1, NSAM0647E, new String[] {"MDSE", uBizMsg.prcBookMdseCd_U.getValue() });
                    bizMsg.setMessageInfo(NSAM0647E, new String[] {"MDSE", uBizMsg.prcBookMdseCd_U.getValue() });
                    isError = true;
                }
            }

            // START 2017/07/21 [QC#20055, ADD]
            if (ZYPCommonFunc.hasValue(uBizMsg.billToCustCd_U) && ZYPCommonFunc.hasValue(uBizMsg.billToLocNum_U)
                    // START 2018/07/10 K.Kojima [QC#27135,MOD]
                    // && !NSXC001001ContrValidation.checkAcctBillEligible(bizMsg.glblCmpyCd.getValue(), ZYPDateUtil.getSalesDate(), uBizMsg.billToCustCd_U.getValue(), uBizMsg.billToLocNum_U.getValue(), ONBATCH_TYPE.ONLINE)) {
                    && !NSXC001001ContrValidation.checkAcctBillEligible(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), uBizMsg.billToCustCd_U.getValue(), uBizMsg.billToLocNum_U.getValue(), ONBATCH_TYPE.ONLINE)) {
                    // END 2018/07/10 K.Kojima [QC#27135,MOD]
                uBizMsg.billToCustCd_U.setErrorInfo(1, NSZM0698E, new String[] {"Customer", "Location" });
                uBizMsg.billToLocNum_U.setErrorInfo(1, NSZM0698E, new String[] {"Customer", "Location" });
                isError = true;
            }

            if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum) && ZYPCommonFunc.hasValue(uBizMsg.billToLocNum_U)
                    // START 2018/07/10 K.Kojima [QC#27135,MOD]
                    // && !NSXC001001ContrValidation.checkAcctBillEligible(bizMsg.glblCmpyCd.getValue(), ZYPDateUtil.getSalesDate(), bizMsg.dsAcctNum.getValue(), uBizMsg.billToLocNum_U.getValue(), ONBATCH_TYPE.ONLINE)) {
                    && !NSXC001001ContrValidation.checkAcctBillEligible(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), bizMsg.dsAcctNum.getValue(), uBizMsg.billToLocNum_U.getValue(), ONBATCH_TYPE.ONLINE)) {
                    // END 2018/07/10 K.Kojima [QC#27135,MOD]
                uBizMsg.billToLocNum_U.setErrorInfo(1, NSZM0698E, new String[] {"Customer(Header)", "Location" });
                isError = true;
            }
            // END   2017/07/21 [QC#20055, ADD]
        }
        return isError;
    }

    private static NSZC115001PMsg getShellApiPMsg(String glblCmpyCd, NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        NSZC115001PMsg pMsg = new NSZC115001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.refCpoOrdNum, bizMsg.refCpoOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC115001Constant.PROC_MODE_MOD);

        // edit Service Detail List
        editSvcDtlList(glblCmpyCd, pMsg, bizMsg, glblMsg);
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.svcDtlList.no(0).manContrOvrdFlg.getValue())) {
            return pMsg;
        }

        // edit Service Price List
        editSvcPriceList(glblCmpyCd, pMsg, bizMsg, glblMsg);

        // edit Service Config Reference List
        editSvcConfigRefList(glblCmpyCd, pMsg, bizMsg, glblMsg);

        // edit Service Usage Price List
        editSvcUsgPriceList(glblCmpyCd, pMsg, bizMsg, glblMsg);

        // edit Service Additional Base Price List
        editSvcAddlBasePriceList(glblCmpyCd, pMsg, bizMsg, glblMsg);

        // edit Service Additional Charge Price List
        editSvcAddlChrgPriceList(glblCmpyCd, pMsg, bizMsg, glblMsg);

        // add start 2017/06/12 QC#18935
        // edit Service Additional Base/Charge Delete List
        editSvcAddlBaseChrgDeleteList(glblCmpyCd, pMsg, bizMsg, glblMsg);
        // add end 2017/06/12 QC#18935

        // START 2017/10/19 [QC#21656, ADD]
        if (pMsg.svcPrcList.getValidCount() == 0 && pMsg.svcUsgPrcList.getValidCount() == 0
                && pMsg.svcAddlBasePrcList.getValidCount() == 0 && pMsg.svcAddlChrgPrcList.getValidCount() == 0) {
            return null;
        }
        // END   2017/10/19 [QC#21656, ADD]

        return pMsg;
    }

    private static void editSvcDtlList(String glblCmpyCd, NSZC115001PMsg pMsg, NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        NSZC115001_svcDtlListPMsgArray svcDtlPMsgAry = pMsg.svcDtlList;
        NSZC115001_svcDtlListPMsg svcDtlPMsg = svcDtlPMsgAry.no(0);
        svcDtlPMsgAry.setValidCount(1);

        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.dsContrPk, bizMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.shellLineNum, bizMsg.shellLineNum);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.svcPgmMdseCd, bizMsg.svcPgmMdseCd);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.prcSvcContrTpCd, bizMsg.prcSvcContrTpCd);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.prcSvcPlnTpCd, bizMsg.prcSvcPlnTpCd);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.dsContrCatgCd, bizMsg.dsContrCatgCd);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.baseBllgCycleCd, bizMsg.baseBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.usgBllgCycleCd, bizMsg.usgBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.fromPerMthNum, bizMsg.fromPerMthNum);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.toPerMthNum, bizMsg.toPerMthNum);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.fixTermInMthAot, bizMsg.fixTermInMthAot);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.maxUplftPct, bizMsg.maxUplftPct);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.billWithEquipFlg, bizMsg.billWithEquipFlg);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.billByTpCd, bizMsg.billByTpCd);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.soldToCustLocCd, bizMsg.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.sellToCustCd, bizMsg.dsAcctNum);

        if (ZYPCommonFunc.hasValue(bizMsg.manContrOvrdFlg)) {
            ZYPEZDItemValueSetter.setValue(svcDtlPMsg.manContrOvrdFlg, bizMsg.manContrOvrdFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(svcDtlPMsg.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(svcDtlPMsg.manContrOvrdFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(svcDtlPMsg.manContrOvrdRsnCd, bizMsg.svcMemoRsnCd);
            ZYPEZDItemValueSetter.setValue(svcDtlPMsg.manContrOvrdCmntTxt, bizMsg.svcCmntTxt);
        } else {
            svcDtlPMsg.manContrOvrdRsnCd.clear();
            svcDtlPMsg.manContrOvrdCmntTxt.clear();
        }

        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.applyEquipBillToFlg, bizMsg.applyEquipBillToFlg);

        if (ZYPCommonFunc.hasValue(svcDtlPMsg.dsContrPk)) {
            ZYPEZDItemValueSetter.setValue(svcDtlPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
        } else {
            ZYPEZDItemValueSetter.setValue(svcDtlPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
        }
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.dsContrPk_AD, bizMsg.dsContrPk_AD);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.cpoSvcAgmtVerNum, bizMsg.cpoSvcAgmtVerNum);

        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.addAsryFlg, bizMsg.addAsryFlg);
        // 2018/08/23 S21_NA#25105 Add Start
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.addlBasePrcCatgCd, bizMsg.prcCatgCd_HJ);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.rntlPrcCatgCd, bizMsg.prcCatgCd_HB);
        ZYPEZDItemValueSetter.setValue(svcDtlPMsg.addlChrgPrcCatgCd, bizMsg.prcCatgCd_C);
        // 2018/08/23 S21_NA#25105 Add End
    }

    private static void editSvcPriceList(String glblCmpyCd, NSZC115001PMsg pMsg, NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        // clear Service Price List
        pMsg.svcPrcList.clear();
        pMsg.svcPrcList.setValidCount(0);

            // set Service Price List (Model)
            editSvcPriceListModel(glblCmpyCd, pMsg, bizMsg, glblMsg);

            // set Service Price List (Config)
            if (!DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
                editSvcPriceListConfig(glblCmpyCd, pMsg, bizMsg, glblMsg);
            }
     }

    private static void editSvcPriceListModel(String glblCmpyCd, NSZC115001PMsg pMsg, NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        int ixP = pMsg.svcPrcList.getValidCount();
        // Add Start 2017/10/19 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21860

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1330_ACMsg aBizMsg = bizMsg.A.no(i);
            if (isMdlPrcNoEntry(aBizMsg, bizMsg.usgBllgCycleCd.getValue())) {
                continue;
            }
            // START 2017/10/19 [QC#21656, ADD]
            if (ZYPCommonFunc.hasValue(aBizMsg.dsContrDtlPk_A)
                    && ZYPConstant.FLG_ON_Y.equals(bizMsg.contrAvalFlg.getValue())
                    && ZYPConstant.FLG_OFF_N.equals(aBizMsg.scrEntAvalFlg_A.getValue())) {
                continue;
            }
            // END   2017/10/19 [QC#21656, ADD]

            NSZC115001_svcPrcListPMsg cspPMsg = pMsg.svcPrcList.no(ixP);

            ZYPEZDItemValueSetter.setValue(cspPMsg.shellLineNum, bizMsg.shellLineNum);
//            ZYPEZDItemValueSetter.setValue(cspPMsg.dsOrdPosnNum, getDsOrdPosnNum(bizMsg, aBizMsg.mdlId_A.getValue()));
            ZYPEZDItemValueSetter.setValue(cspPMsg.dsContrDtlPk, aBizMsg.dsContrDtlPk_A);
            ZYPEZDItemValueSetter.setValue(cspPMsg.dsContrPk, bizMsg.dsContrPk);
            ZYPEZDItemValueSetter.setValue(cspPMsg.mdlId, aBizMsg.mdlId_A);
            ZYPEZDItemValueSetter.setValue(cspPMsg.maintPrcCatgCd, aBizMsg.prcCatgCd_A);
            ZYPEZDItemValueSetter.setValue(cspPMsg.prcMtrPkgPk, aBizMsg.prcMtrPkgPk_A);
            // Mod Start 2017/10/19 QC#21860
//            ZYPEZDItemValueSetter.setValue(cspPMsg.basePrcDealAmt, aBizMsg.xxTotPrcAmt_PB);
            ZYPEZDItemValueSetter.setValue(cspPMsg.basePrcDealAmt, round(aBizMsg.xxTotPrcAmt_PB.getValue(), scale));
            if (!ZYPCommonFunc.hasValue(cspPMsg.basePrcDealAmt)) {
//                ZYPEZDItemValueSetter.setValue(cspPMsg.basePrcDealAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cspPMsg.basePrcDealAmt, round(BigDecimal.ZERO, scale));
            }
//            ZYPEZDItemValueSetter.setValue(cspPMsg.dealPrcListPrcAmt, aBizMsg.dealPrcListPrcAmt_A);
            ZYPEZDItemValueSetter.setValue(cspPMsg.dealPrcListPrcAmt, round(aBizMsg.dealPrcListPrcAmt_A.getValue(), scale));
            if (!ZYPCommonFunc.hasValue(cspPMsg.dealPrcListPrcAmt)) {
//                ZYPEZDItemValueSetter.setValue(cspPMsg.dealPrcListPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cspPMsg.dealPrcListPrcAmt, round(BigDecimal.ZERO, scale));
            }
            // Mod End 2017/10/19 QC#21860
            ZYPEZDItemValueSetter.setValue(cspPMsg.prcTierSvcOfferCd, aBizMsg.prcTierSvcOfferCd_A);

            if (ZYPCommonFunc.hasValue(cspPMsg.dsContrDtlPk)) {
                ZYPEZDItemValueSetter.setValue(cspPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
            } else {
                ZYPEZDItemValueSetter.setValue(cspPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
            }

            ixP++;
        }

        pMsg.svcPrcList.setValidCount(ixP);
    }

    private static void editSvcPriceListConfig(String glblCmpyCd, NSZC115001PMsg pMsg, NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        int ixP = pMsg.svcPrcList.getValidCount();
        // Add Start 2017/10/19 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21860

        for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
            NSAL1330_RCMsg rBizMsg = bizMsg.R.no(i);
            if (isMdlPrcNoEntry(rBizMsg, bizMsg.usgBllgCycleCd.getValue())) {
                continue;
            }
            // START 2017/10/19 [QC#21656, ADD]
            if (ZYPCommonFunc.hasValue(rBizMsg.dsContrDtlPk_R)
                    && ZYPConstant.FLG_ON_Y.equals(bizMsg.contrAvalFlg.getValue())
                    && ZYPConstant.FLG_OFF_N.equals(rBizMsg.scrEntAvalFlg_R.getValue())) {
                continue;
            }
            // END   2017/10/19 [QC#21656, ADD]

            NSZC115001_svcPrcListPMsg cspPMsg = pMsg.svcPrcList.no(ixP);

            ZYPEZDItemValueSetter.setValue(cspPMsg.shellLineNum, bizMsg.shellLineNum);
            ZYPEZDItemValueSetter.setValue(cspPMsg.dsOrdPosnNum, getDsOrdPosnNumConfig(bizMsg, rBizMsg.mdlId_R.getValue(), rBizMsg.dsContrDtlPk_R.getValue()));
            ZYPEZDItemValueSetter.setValue(cspPMsg.dsContrPk, bizMsg.dsContrPk);
            ZYPEZDItemValueSetter.setValue(cspPMsg.dsContrDtlPk, rBizMsg.dsContrDtlPk_R);
            ZYPEZDItemValueSetter.setValue(cspPMsg.mdlId, rBizMsg.mdlId_R);
            ZYPEZDItemValueSetter.setValue(cspPMsg.maintPrcCatgCd, rBizMsg.prcCatgCd_R);
            ZYPEZDItemValueSetter.setValue(cspPMsg.prcMtrPkgPk, rBizMsg.prcMtrPkgPk_R);
            // Mod Start 2017/10/19 QC#21860
//            ZYPEZDItemValueSetter.setValue(cspPMsg.basePrcDealAmt, rBizMsg.xxTotPrcAmt_PR);
            ZYPEZDItemValueSetter.setValue(cspPMsg.basePrcDealAmt, round(rBizMsg.xxTotPrcAmt_PR.getValue(), scale));
            if (!ZYPCommonFunc.hasValue(cspPMsg.basePrcDealAmt)) {
//                ZYPEZDItemValueSetter.setValue(cspPMsg.basePrcDealAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cspPMsg.basePrcDealAmt, round(BigDecimal.ZERO, scale));
            }
//            ZYPEZDItemValueSetter.setValue(cspPMsg.dealPrcListPrcAmt, rBizMsg.dealPrcListPrcAmt_R);
            ZYPEZDItemValueSetter.setValue(cspPMsg.dealPrcListPrcAmt, round(rBizMsg.dealPrcListPrcAmt_R.getValue(), scale));
            if (!ZYPCommonFunc.hasValue(cspPMsg.dealPrcListPrcAmt)) {
//                ZYPEZDItemValueSetter.setValue(cspPMsg.dealPrcListPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cspPMsg.dealPrcListPrcAmt, round(BigDecimal.ZERO, scale));
            }
            // Mod End 2017/10/19 QC#21860
            ZYPEZDItemValueSetter.setValue(cspPMsg.prcTierSvcOfferCd, rBizMsg.prcTierSvcOfferCd_R);
            ZYPEZDItemValueSetter.setValue(cspPMsg.billToCustCd, rBizMsg.billToLocNum_R);
            ZYPEZDItemValueSetter.setValue(cspPMsg.sellToCustCd, rBizMsg.billToCustCd_R);

            if (ZYPCommonFunc.hasValue(cspPMsg.dsContrDtlPk)) {
                ZYPEZDItemValueSetter.setValue(cspPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
            } else {
                ZYPEZDItemValueSetter.setValue(cspPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
            }

            ixP++;
        }

        pMsg.svcPrcList.setValidCount(ixP);
    }

    private static void editSvcUsgPriceList(String glblCmpyCd, NSZC115001PMsg pMsg, NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        // clear Service Usage Price List
        pMsg.svcUsgPrcList.clear();
        pMsg.svcUsgPrcList.setValidCount(0);

        // set Service Usage Price List (Model)
        editSvcUsgPriceListModel(glblCmpyCd, pMsg, bizMsg, glblMsg);

        // set Service Usage Price List (Config)
        if (!DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
            editSvcUsgPriceListConfig(glblCmpyCd, pMsg, bizMsg, glblMsg);
        }
    }
    
    private static void editSvcUsgPriceListModel(String glblCmpyCd, NSZC115001PMsg pMsg, NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        int ixP = pMsg.svcUsgPrcList.getValidCount();

        Map<String, NSAL1330_ZCMsg> zsMsgMap = new HashMap<String, NSAL1330_ZCMsg>();
        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
            NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(i);
            BigDecimal mdlId = zBizMsg.mdlId_Z.getValue();
            NSAL1330_ACMsg aBizMsg = getSvcPrcInfo(bizMsg, mdlId);

            // START 2017/10/19 [QC#21656, ADD]
            if (ZYPCommonFunc.hasValue(aBizMsg.dsContrDtlPk_A)
                    && ZYPConstant.FLG_ON_Y.equals(bizMsg.contrAvalFlg.getValue())
                    && ZYPConstant.FLG_OFF_N.equals(aBizMsg.scrEntAvalFlg_A.getValue())) {
                continue;
            }
            // END   2017/10/19 [QC#21656, ADD]

            if (ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_A) //
                    && !isRateTypeAnnual(aBizMsg.prcRateTpCd_A.getValue())) {
                NSZC115001_svcUsgPrcListPMsg usgPMsg = pMsg.svcUsgPrcList.no(ixP);

                ZYPEZDItemValueSetter.setValue(usgPMsg.shellLineNum, bizMsg.shellLineNum);
                ZYPEZDItemValueSetter.setValue(usgPMsg.mdlId, zBizMsg.mdlId_Z);
                ZYPEZDItemValueSetter.setValue(usgPMsg.dsContrPk, bizMsg.dsContrPk);
                ZYPEZDItemValueSetter.setValue(usgPMsg.dsContrDtlPk, aBizMsg.dsContrDtlPk_A);
                ZYPEZDItemValueSetter.setValue(usgPMsg.copyInclPrcQty, zBizMsg.mlyCopyInclPrcQty_Z);
                //
                ZYPEZDItemValueSetter.setValue(usgPMsg.prcBookMdseCd, zBizMsg.prcBookMdseCd_Z);
                ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMtrLbCd, zBizMsg.bllgMtrLbCd_Z);
                if (XX_FLG_PARENT.equals(zBizMsg.xxFlgNm_Z.getValue())) {
                    ZYPEZDItemValueSetter.setValue(usgPMsg.usgMdseCd, zBizMsg.usgMdseCd_Z);
                }
                ZYPEZDItemValueSetter.setValue(usgPMsg.regMtrLbCd, zBizMsg.regMtrLbCd_Z);
                ZYPEZDItemValueSetter.setValue(usgPMsg.xsMtrAmtRate, zBizMsg.xsMtrAmtRate_Z);
                ZYPEZDItemValueSetter.setValue(usgPMsg.contrMtrMultRate, zBizMsg.contrMtrMultRate_Z);
//                ZYPEZDItemValueSetter.setValue(usgPMsg.prcSvcTierTpCd, zBizMsg.prcSvcTierTpCd_Z);
                ZYPEZDItemValueSetter.setValue(usgPMsg.actvFlg, ZYPConstant.FLG_ON_Y);
                // 2018/05/09 QC#25030 add start
                ZYPEZDItemValueSetter.setValue(usgPMsg.bllgFreeCopyCnt, zBizMsg.bllgFreeCopyCnt_Z);
                ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMinCnt, zBizMsg.bllgMinCnt_Z);
                ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMinAmtRate, zBizMsg.bllgMinAmtRate_Z);
                ZYPEZDItemValueSetter.setValue(usgPMsg.bllgRollOverRatio, zBizMsg.bllgRollOverRatio_Z);
                // 2018/05/09 QC#25030 add end
                //
                if (ZYPCommonFunc.hasValue(zBizMsg.prcListBandDescTxt_Z)) {
                    List<String> bandList = getPrcListBandCdList(zBizMsg.prcListBandDescTxt_Z.getValue());
                    if (bandList == null || bandList.size() == 0) {
                        zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NSAM0647E //
                                , new String[] {"PRC_LIST_BAND", "Band : " + zBizMsg.prcListBandDescTxt_Z.getValue() });
                        return;
                    }
                    if (bandList.size() > 1) {
                        zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NSAM0680E //
                                , new String[] {"PRC_LIST_BAND", "Band", zBizMsg.prcListBandDescTxt_Z.getValue() });
                        return;
                    }
                    ZYPEZDItemValueSetter.setValue(usgPMsg.prcListBandCd, bandList.get(0));
                }
                //
                // START 2017/06/13 [QC#18842, DEL]
                // if (!ZYPCommonFunc.hasValue(usgPMsg.bllgMtrLbCd)) {
                //     ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMtrLbCd, pMsg.svcUsgPrcList.no(0).bllgMtrLbCd);
                // }
                // END   2017/06/13 [QC#18842, DEL]

                if (ZYPCommonFunc.hasValue(usgPMsg.dsContrDtlPk)) {
                    ZYPEZDItemValueSetter.setValue(usgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
                } else {
                    ZYPEZDItemValueSetter.setValue(usgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
                }
                if (!zsMsgMap.containsKey(zBizMsg.bllgMtrLbCd_Z.getValue())) {
                    zsMsgMap.put(zBizMsg.bllgMtrLbCd_Z.getValue(), zBizMsg);
                }

                ixP++;
            }
        }

        for (int i = 0; i < bizMsg.X.getValidCount(); i++) {
            NSAL1330_XCMsg xBizMsg = bizMsg.X.no(i);
            BigDecimal mdlId = xBizMsg.mdlId_X.getValue();
            NSAL1330_ACMsg aBizMsg = getSvcPrcInfo(bizMsg, mdlId);

            // START 2017/10/19 [QC#21656, ADD]
            if (ZYPCommonFunc.hasValue(aBizMsg.dsContrDtlPk_A)
                    && ZYPConstant.FLG_ON_Y.equals(bizMsg.contrAvalFlg.getValue())
                    && ZYPConstant.FLG_OFF_N.equals(aBizMsg.scrEntAvalFlg_A.getValue())) {
                continue;
            }
            // END   2017/10/19 [QC#21656, ADD]

            if (ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_A) //
                    && !isRateTypeAnnual(aBizMsg.prcRateTpCd_A.getValue())) {
                NSZC115001_svcUsgPrcListPMsg usgPMsg = pMsg.svcUsgPrcList.no(ixP);

                ZYPEZDItemValueSetter.setValue(usgPMsg.shellLineNum, bizMsg.shellLineNum);
                ZYPEZDItemValueSetter.setValue(usgPMsg.mdlId, xBizMsg.mdlId_X);
                ZYPEZDItemValueSetter.setValue(usgPMsg.dsContrPk, bizMsg.dsContrPk);
                ZYPEZDItemValueSetter.setValue(usgPMsg.dsContrDtlPk, aBizMsg.dsContrDtlPk_A);
                ZYPEZDItemValueSetter.setValue(usgPMsg.contrPhysBllgMtrRelnPk, xBizMsg.contrPhysBllgMtrRelnPk_X);
                ZYPEZDItemValueSetter.setValue(usgPMsg.actvFlg, ZYPConstant.FLG_ON_Y);

                // START 2017/06/13 [QC#18842, MOD]
                // if (!ZYPCommonFunc.hasValue(usgPMsg.bllgMtrLbCd)) {
                //     ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMtrLbCd, pMsg.svcUsgPrcList.no(0).bllgMtrLbCd);
                // }
                ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMtrLbCd, xBizMsg.bllgMtrLbCd_X);
                // END   2017/06/13 [QC#18842, MOD]
                NSAL1330_ZCMsg zBizMsg = zsMsgMap.get(xBizMsg.bllgMtrLbCd_X.getValue());
                if (zBizMsg != null) {
                    if (ZYPCommonFunc.hasValue(zBizMsg.prcListBandDescTxt_Z)) {
                        List<String> bandList = getPrcListBandCdList(zBizMsg.prcListBandDescTxt_Z.getValue());
                        if (bandList == null || bandList.size() == 0) {
                            zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NSAM0647E //
                                    , new String[] {"PRC_LIST_BAND", "Band : " + zBizMsg.prcListBandDescTxt_Z.getValue() });
                            return;
                        }
                        if (bandList.size() > 1) {
                            zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NSAM0680E //
                                    , new String[] {"PRC_LIST_BAND", "Band", zBizMsg.prcListBandDescTxt_Z.getValue() });
                            return;
                        }
                        ZYPEZDItemValueSetter.setValue(usgPMsg.prcListBandCd, bandList.get(0));
                    }
                    ZYPEZDItemValueSetter.setValue(usgPMsg.prcBookMdseCd, zBizMsg.prcBookMdseCd_Z);
                }

                ZYPEZDItemValueSetter.setValue(usgPMsg.prcSvcTierTpCd, xBizMsg.prcSvcTierTpCd_X);
                ZYPEZDItemValueSetter.setValue(usgPMsg.xsMtrAmtRate, xBizMsg.xsMtrAmtRate_X);
                ZYPEZDItemValueSetter.setValue(usgPMsg.minCopyVolCnt, xBizMsg.minCopyVolCnt_X);
                ZYPEZDItemValueSetter.setValue(usgPMsg.maxCopyVolCnt, xBizMsg.maxCopyVolCnt_X);

                if (ZYPCommonFunc.hasValue(usgPMsg.dsContrDtlPk)) {
                    if (ZYPCommonFunc.hasValue(aBizMsg.prcTierSvcOfferCd_A)) {
                        ZYPEZDItemValueSetter.setValue(usgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
                    } else {
                        ZYPEZDItemValueSetter.setValue(usgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_DEL);
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(usgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
                }

                ixP++;
            }
        }

        pMsg.svcUsgPrcList.setValidCount(ixP);
    }

    private static void editSvcUsgPriceListConfig(String glblCmpyCd, NSZC115001PMsg pMsg, NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        int ixP = pMsg.svcUsgPrcList.getValidCount();

        Map<String, NSAL1330_UCMsg> usMsgMap = new HashMap<String, NSAL1330_UCMsg>();
        for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
            NSAL1330_UCMsg uBizMsg = bizMsg.U.no(i);
            BigDecimal mdlId = uBizMsg.mdlId_U.getValue();
            String dsOrdPosnNum = uBizMsg.dsOrdPosnNum_U.getValue();
            NSAL1330_RCMsg rBizMsg = getSvcPrcInfo(bizMsg, mdlId, dsOrdPosnNum);

            // START 2017/10/19 [QC#21656, ADD]
            if (ZYPCommonFunc.hasValue(rBizMsg.dsContrDtlPk_R)
                    && ZYPConstant.FLG_ON_Y.equals(bizMsg.contrAvalFlg.getValue())
                    && ZYPConstant.FLG_OFF_N.equals(rBizMsg.scrEntAvalFlg_R.getValue())) {
                continue;
            }
            // END   2017/10/19 [QC#21656, ADD]

            if (ZYPCommonFunc.hasValue(rBizMsg.prcMtrPkgPk_R) //
                    && !isRateTypeAnnual(rBizMsg.prcRateTpCd_R.getValue())) {
                NSZC115001_svcUsgPrcListPMsg usgPMsg = pMsg.svcUsgPrcList.no(ixP);

                ZYPEZDItemValueSetter.setValue(usgPMsg.shellLineNum, bizMsg.shellLineNum);
                ZYPEZDItemValueSetter.setValue(usgPMsg.mdlId, rBizMsg.mdlId_R);
                ZYPEZDItemValueSetter.setValue(usgPMsg.dsContrPk, bizMsg.dsContrPk);
                ZYPEZDItemValueSetter.setValue(usgPMsg.dsContrDtlPk, rBizMsg.dsContrDtlPk_R);
                ZYPEZDItemValueSetter.setValue(usgPMsg.contrPhysBllgMtrRelnPk, uBizMsg.contrPhysBllgMtrRelnPk_U);
                ZYPEZDItemValueSetter.setValue(usgPMsg.copyInclPrcQty, uBizMsg.mlyCopyInclPrcQty_U);
                //
                ZYPEZDItemValueSetter.setValue(usgPMsg.prcBookMdseCd, uBizMsg.prcBookMdseCd_U);
                ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMtrLbCd, uBizMsg.bllgMtrLbCd_U);
                if (XX_FLG_PARENT.equals(uBizMsg.xxFlgNm_U.getValue())) {
                    ZYPEZDItemValueSetter.setValue(usgPMsg.usgMdseCd, uBizMsg.usgMdseCd_U);
                }
                ZYPEZDItemValueSetter.setValue(usgPMsg.usgMdseCd, uBizMsg.usgMdseCd_U);
                ZYPEZDItemValueSetter.setValue(usgPMsg.regMtrLbCd, uBizMsg.regMtrLbCd_U);
                ZYPEZDItemValueSetter.setValue(usgPMsg.xsMtrAmtRate, uBizMsg.xsMtrAmtRate_U);
                ZYPEZDItemValueSetter.setValue(usgPMsg.contrMtrMultRate, uBizMsg.contrMtrMultRate_U);
//                ZYPEZDItemValueSetter.setValue(usgPMsg.prcSvcTierTpCd, uBizMsg.prcSvcTierTpCd_U);
                ZYPEZDItemValueSetter.setValue(usgPMsg.sellToCustCd, uBizMsg.billToCustCd_U);
                ZYPEZDItemValueSetter.setValue(usgPMsg.billToCustCd, uBizMsg.billToLocNum_U);
                ZYPEZDItemValueSetter.setValue(usgPMsg.actvFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(usgPMsg.dsOrdPosnNum, uBizMsg.dsOrdPosnNum_U);
                // 2018/05/09 QC#25030 add start
                ZYPEZDItemValueSetter.setValue(usgPMsg.bllgFreeCopyCnt, uBizMsg.bllgFreeCopyCnt_U);
                ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMinCnt, uBizMsg.bllgMinCnt_U);
                ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMinAmtRate, uBizMsg.bllgMinAmtRate_U);
                ZYPEZDItemValueSetter.setValue(usgPMsg.bllgRollOverRatio, uBizMsg.bllgRollOverRatio_U);
                // 2018/05/09 QC#25030 add end
                //
                if (ZYPCommonFunc.hasValue(uBizMsg.prcListBandDescTxt_U)) {
                    List<String> bandList = getPrcListBandCdList(uBizMsg.prcListBandDescTxt_U.getValue());
                    if (bandList == null || bandList.size() == 0) {
                        uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NSAM0647E //
                                , new String[] {"PRC_LIST_BAND", "Band : " + uBizMsg.prcListBandDescTxt_U.getValue() });
                        return;
                    }
                    if (bandList.size() > 1) {
                        uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NSAM0680E //
                                , new String[] {"PRC_LIST_BAND", "Band", uBizMsg.prcListBandDescTxt_U.getValue() });
                        return;
                    }
                    ZYPEZDItemValueSetter.setValue(usgPMsg.prcListBandCd, bandList.get(0));
                }
                //
                ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMtrLbCd, uBizMsg.bllgMtrLbCd_U);
                // 
                // START 2017/06/13 [QC#18842, DEL]
                // if (!ZYPCommonFunc.hasValue(usgPMsg.bllgMtrLbCd)) {
                //     ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMtrLbCd, pMsg.svcUsgPrcList.no(0).bllgMtrLbCd);
                // }
                // END   2017/06/13 [QC#18842, DEL]

                if (ZYPCommonFunc.hasValue(usgPMsg.contrPhysBllgMtrRelnPk)) {
                    ZYPEZDItemValueSetter.setValue(usgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
                } else {
                    ZYPEZDItemValueSetter.setValue(usgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
                }
                if (!usMsgMap.containsKey(uBizMsg.bllgMtrLbCd_U.getValue())) {
                    usMsgMap.put(uBizMsg.bllgMtrLbCd_U.getValue(), uBizMsg);
                }

                ixP++;
            }
        }

        for (int i = 0; i < bizMsg.V.getValidCount(); i++) {
            NSAL1330_VCMsg vBizMsg = bizMsg.V.no(i);
            BigDecimal mdlId = vBizMsg.mdlId_V.getValue();
            String dsOrdPosnNum = vBizMsg.dsOrdPosnNum_V.getValue();
            NSAL1330_RCMsg rBizMsg = getSvcPrcInfo(bizMsg, mdlId, dsOrdPosnNum);

            // START 2017/10/19 [QC#21656, ADD]
            if (ZYPCommonFunc.hasValue(rBizMsg.dsContrDtlPk_R)
                    && ZYPConstant.FLG_ON_Y.equals(bizMsg.contrAvalFlg.getValue())
                    && ZYPConstant.FLG_OFF_N.equals(rBizMsg.scrEntAvalFlg_R.getValue())) {
                continue;
            }
            // END   2017/10/19 [QC#21656, ADD]

            if (ZYPCommonFunc.hasValue(rBizMsg.prcMtrPkgPk_R) //
                    && !isRateTypeAnnual(rBizMsg.prcRateTpCd_R.getValue())) {
                NSZC115001_svcUsgPrcListPMsg usgPMsg = pMsg.svcUsgPrcList.no(ixP);

                ZYPEZDItemValueSetter.setValue(usgPMsg.shellLineNum, bizMsg.shellLineNum);
                ZYPEZDItemValueSetter.setValue(usgPMsg.mdlId, rBizMsg.mdlId_R);
                ZYPEZDItemValueSetter.setValue(usgPMsg.dsContrPk, bizMsg.dsContrPk);
                ZYPEZDItemValueSetter.setValue(usgPMsg.dsContrDtlPk, rBizMsg.dsContrDtlPk_R);
                ZYPEZDItemValueSetter.setValue(usgPMsg.dsOrdPosnNum, rBizMsg.dsOrdPosnNum_R);
                ZYPEZDItemValueSetter.setValue(usgPMsg.actvFlg, ZYPConstant.FLG_ON_Y);

                // START 2017/06/13 [QC#18842, MOD]
                ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMtrLbCd, vBizMsg.bllgMtrLbCd_V);
                // if (!ZYPCommonFunc.hasValue(usgPMsg.bllgMtrLbCd)) {
                //     ZYPEZDItemValueSetter.setValue(usgPMsg.bllgMtrLbCd, pMsg.svcUsgPrcList.no(0).bllgMtrLbCd);
                // }
                // END   2017/06/13 [QC#18842, MOD]
                NSAL1330_UCMsg uBizMsg = usMsgMap.get(vBizMsg.bllgMtrLbCd_V.getValue());
                if (uBizMsg != null) {
                    if (ZYPCommonFunc.hasValue(uBizMsg.prcListBandDescTxt_U)) {
                        List<String> bandList = getPrcListBandCdList(uBizMsg.prcListBandDescTxt_U.getValue());
                        if (bandList == null || bandList.size() == 0) {
                            uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NSAM0647E //
                                    , new String[] {"PRC_LIST_BAND", "Band : " + uBizMsg.prcListBandDescTxt_U.getValue() });
                            return;
                        }
                        if (bandList.size() > 1) {
                            uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NSAM0680E //
                                    , new String[] {"PRC_LIST_BAND", "Band", uBizMsg.prcListBandDescTxt_U.getValue() });
                            return;
                        }
                        ZYPEZDItemValueSetter.setValue(usgPMsg.prcListBandCd, bandList.get(0));
                    }
                    ZYPEZDItemValueSetter.setValue(usgPMsg.prcBookMdseCd, uBizMsg.prcBookMdseCd_U);
                }

                ZYPEZDItemValueSetter.setValue(usgPMsg.prcSvcTierTpCd, vBizMsg.prcSvcTierTpCd_V);
                ZYPEZDItemValueSetter.setValue(usgPMsg.xsMtrAmtRate, vBizMsg.xsMtrAmtRate_V);
                ZYPEZDItemValueSetter.setValue(usgPMsg.minCopyVolCnt, vBizMsg.minCopyVolCnt_V);
                ZYPEZDItemValueSetter.setValue(usgPMsg.maxCopyVolCnt, vBizMsg.maxCopyVolCnt_V);

                if (ZYPCommonFunc.hasValue(usgPMsg.dsContrDtlPk)) {
                    if (ZYPCommonFunc.hasValue(rBizMsg.prcTierSvcOfferCd_R)) {
                        ZYPEZDItemValueSetter.setValue(usgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
                    } else {
                        ZYPEZDItemValueSetter.setValue(usgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_DEL);
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(usgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
                }

                ixP++;
            }
        }

        pMsg.svcUsgPrcList.setValidCount(ixP);
    }

    private static NSAL1330_ACMsg getSvcPrcInfo(NSAL1330CMsg bizMsg, BigDecimal mdlId) {
        int ixA = 0;
        if (!DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NSAL1330_ACMsg aScrnMsg = bizMsg.A.no(i);
                if (mdlId.compareTo(aScrnMsg.mdlId_A.getValue()) == 0) {
                    ixA = i;
                    break;
                }
            }
        }
        return bizMsg.A.no(ixA);
    }

    private static NSAL1330_RCMsg getSvcPrcInfo(NSAL1330CMsg bizMsg, BigDecimal mdlId, String dsOrdPosnNum) {
        int ixR = 0;
        for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
            NSAL1330_RCMsg rScrnMsg = bizMsg.R.no(i);
            if (mdlId.compareTo(rScrnMsg.mdlId_R.getValue()) == 0 //
                    && dsOrdPosnNum.equals(rScrnMsg.dsOrdPosnNum_R.getValue())) {
                ixR = i;
                break;
            }
        }
        return bizMsg.R.no(ixR);
    }

    private static void editSvcAddlBasePriceList(String glblCmpyCd, NSZC115001PMsg pMsg, NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        int ixP = 0;
        // Add Start 2017/10/19 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21860

        // Accessory Charge
        for (int i = 0; i < bizMsg.J.getValidCount(); i++) {
            NSAL1330_JCMsg jBizMsg = bizMsg.J.no(i);

            // START 2017/10/19 [QC#21656, ADD]
            if (ZYPCommonFunc.hasValue(jBizMsg.dsContrDtlPk_J)
                    && ZYPConstant.FLG_ON_Y.equals(bizMsg.contrAvalFlg.getValue())
                    && ZYPConstant.FLG_OFF_N.equals(jBizMsg.scrEntAvalFlg_J.getValue())) {
                continue;
            }
            // END   2017/10/19 [QC#21656, ADD]

            NSZC115001_svcAddlBasePrcListPMsg basePMsg = pMsg.svcAddlBasePrcList.no(ixP++);

            ZYPEZDItemValueSetter.setValue(basePMsg.shellLineNum, bizMsg.shellLineNum);
            ZYPEZDItemValueSetter.setValue(basePMsg.dsContrPk, bizMsg.dsContrPk);
            ZYPEZDItemValueSetter.setValue(basePMsg.dsContrDtlPk, jBizMsg.dsContrDtlPk_J);
            ZYPEZDItemValueSetter.setValue(basePMsg.addlChrgCatgCd, ADDL_CHRG_CATG.ACCESSORY);
            ZYPEZDItemValueSetter.setValue(basePMsg.cpoOrdNum, bizMsg.refCpoOrdNum);
            ZYPEZDItemValueSetter.setValue(basePMsg.cpoDtlLineNum, jBizMsg.cpoDtlLineNum_J);
            ZYPEZDItemValueSetter.setValue(basePMsg.cpoDtlLineSubNum, jBizMsg.cpoDtlLineSubNum_J);
            ZYPEZDItemValueSetter.setValue(basePMsg.addlBasePrcCatgCd, jBizMsg.addlBasePrcCatgCd_J);
            ZYPEZDItemValueSetter.setValue(basePMsg.svcPrcCatgCd, SVC_PRC_CATG.ACC_UNIT_BASE_CHARGE);

            // Mod Start 2017/10/19 QC#21860
//            ZYPEZDItemValueSetter.setValue(basePMsg.addlBasePrcDealAmt, jBizMsg.addlBasePrcDealAmt_J);
            ZYPEZDItemValueSetter.setValue(basePMsg.addlBasePrcDealAmt, round(jBizMsg.addlBasePrcDealAmt_J.getValue(), scale));
            if (!ZYPCommonFunc.hasValue(basePMsg.dealPrcListPrcAmt)) {
//                ZYPEZDItemValueSetter.setValue(basePMsg.dealPrcListPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(basePMsg.dealPrcListPrcAmt, round(BigDecimal.ZERO, scale));
            }
            // Mod End 2017/10/19 QC#21860

            if (ZYPCommonFunc.hasValue(basePMsg.dsContrDtlPk)) {
                ZYPEZDItemValueSetter.setValue(basePMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
            } else {
                ZYPEZDItemValueSetter.setValue(basePMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
            }
        }

        // Rental Eq. Charge Base & Accessory
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NSAL1330_BCMsg bBizMsg = bizMsg.B.no(i);

            // START 2017/10/19 [QC#21656, ADD]
            // START 2018/03/13 M.Naito [QC#23378, MOD]
//            if (ZYPCommonFunc.hasValue(bBizMsg.dsContrAddlChrgPk_B)
            if (ZYPCommonFunc.hasValue(bBizMsg.addlBasePrcDealAmt_B)
            // END 2018/03/13 M.Naito [QC#23378, MOD]
                    && ZYPConstant.FLG_ON_Y.equals(bizMsg.contrAvalFlg.getValue())
                    && ZYPConstant.FLG_OFF_N.equals(bBizMsg.scrEntAvalFlg_B.getValue())) {
                continue;
            }
            // END   2017/10/19 [QC#21656, ADD]

            NSZC115001_svcAddlBasePrcListPMsg basePMsg = pMsg.svcAddlBasePrcList.no(ixP++);

            ZYPEZDItemValueSetter.setValue(basePMsg.shellLineNum, bizMsg.shellLineNum);
            ZYPEZDItemValueSetter.setValue(basePMsg.dsContrAddlChrgPk, bBizMsg.dsContrAddlChrgPk_B);
            ZYPEZDItemValueSetter.setValue(basePMsg.dsContrPk, bizMsg.dsContrPk);
            ZYPEZDItemValueSetter.setValue(basePMsg.dsContrDtlPk, bBizMsg.dsContrDtlPk_B);
            ZYPEZDItemValueSetter.setValue(basePMsg.addlChrgCatgCd, ADDL_CHRG_CATG.RENTAL);
            ZYPEZDItemValueSetter.setValue(basePMsg.cpoOrdNum, bizMsg.refCpoOrdNum);
            ZYPEZDItemValueSetter.setValue(basePMsg.cpoDtlLineNum, bBizMsg.cpoDtlLineNum_B);
            ZYPEZDItemValueSetter.setValue(basePMsg.cpoDtlLineSubNum, bBizMsg.cpoDtlLineSubNum_B);
            ZYPEZDItemValueSetter.setValue(basePMsg.addlBasePrcCatgCd, bBizMsg.addlBasePrcCatgCd_B);
            ZYPEZDItemValueSetter.setValue(basePMsg.svcPrcCatgCd, SVC_PRC_CATG.RENTAL_EQUIPMENT_BASE_CHARGE);
            ZYPEZDItemValueSetter.setValue(basePMsg.prcListEquipConfigNum, bBizMsg.prcListEquipConfigNum_B);

            // Mod Start 2017/10/19 QC#21860
//            ZYPEZDItemValueSetter.setValue(basePMsg.addlBasePrcDealAmt, bBizMsg.addlBasePrcDealAmt_B);
//            ZYPEZDItemValueSetter.setValue(basePMsg.dealPrcListPrcAmt, bBizMsg.dealPrcListPrcAmt_B);
            ZYPEZDItemValueSetter.setValue(basePMsg.addlBasePrcDealAmt, round(bBizMsg.addlBasePrcDealAmt_B.getValue(), scale));
            ZYPEZDItemValueSetter.setValue(basePMsg.dealPrcListPrcAmt, round(bBizMsg.dealPrcListPrcAmt_B.getValue(), scale));
            if (!ZYPCommonFunc.hasValue(basePMsg.dealPrcListPrcAmt)) {
//                ZYPEZDItemValueSetter.setValue(basePMsg.dealPrcListPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(basePMsg.dealPrcListPrcAmt, round(BigDecimal.ZERO, scale));
            }
            // Mod End 2017/10/19 QC#21860

            // START 2018/03/13 M.Naito [QC#23378, MOD]
//            if (ZYPCommonFunc.hasValue(basePMsg.dsContrAddlChrgPk)) {
//            if (ZYPCommonFunc.hasValue(bBizMsg.addlBasePrcDealAmt_B)) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.rntlOrdFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(basePMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
            } else {
                ZYPEZDItemValueSetter.setValue(basePMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_DEL);
            }
//            } else {
//                ZYPEZDItemValueSetter.setValue(basePMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
//            }
            // END 2018/03/13 M.Naito [QC#23378, MOD]
        }

        pMsg.svcAddlBasePrcList.setValidCount(ixP);
    }

    private static void editSvcAddlChrgPriceList(String glblCmpyCd, NSZC115001PMsg pMsg, NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        // Add Start 2017/10/19 QC#21860
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21860

        // START 2017/10/19 [QC#21656, ADD]
        int ixC = 0;
        // END   2017/10/19 [QC#21656, ADD]

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NSAL1330_CCMsg cBizMsg = bizMsg.C.no(i);

            // START 2017/10/19 [QC#21656, ADD]
            if (ZYPCommonFunc.hasValue(cBizMsg.dsContrAddlChrgPk_C)
                    && ZYPConstant.FLG_ON_Y.equals(bizMsg.contrAvalFlg.getValue())
                    && ZYPConstant.FLG_OFF_N.equals(cBizMsg.scrEntAvalFlg_C.getValue())) {
                continue;
            }
            // END   2017/10/19 [QC#21656, ADD]

            // Additional Service Charge
            // START 2017/10/19 [QC#21656, MOD]
            // NSZC115001_svcAddlChrgPrcListPMsg chrgPMsg = pMsg.svcAddlChrgPrcList.no(i);
            NSZC115001_svcAddlChrgPrcListPMsg chrgPMsg = pMsg.svcAddlChrgPrcList.no(ixC++);
            // END   2017/10/19 [QC#21656, MOD]

            ZYPEZDItemValueSetter.setValue(chrgPMsg.shellLineNum, bizMsg.shellLineNum);
            ZYPEZDItemValueSetter.setValue(chrgPMsg.dsContrPk, bizMsg.dsContrPk);
            ZYPEZDItemValueSetter.setValue(chrgPMsg.dsContrDtlPk, cBizMsg.dsContrDtlPk_C);
            ZYPEZDItemValueSetter.setValue(chrgPMsg.dsContrAddlChrgPk, cBizMsg.dsContrAddlChrgPk_C);
            ZYPEZDItemValueSetter.setValue(chrgPMsg.cpoOrdNum, bizMsg.refCpoOrdNum);
            ZYPEZDItemValueSetter.setValue(chrgPMsg.cpoDtlLineNum, cBizMsg.cpoDtlLineNum_C);
            ZYPEZDItemValueSetter.setValue(chrgPMsg.cpoDtlLineSubNum, cBizMsg.cpoDtlLineSubNum_C);
            ZYPEZDItemValueSetter.setValue(chrgPMsg.addlChrgMdseCd, cBizMsg.mdseCd_CI);
            ZYPEZDItemValueSetter.setValue(chrgPMsg.addlChrgPrcCatgCd, bizMsg.prcCatgCd_C);
            // Mod Start 2017/10/19 QC#21860
//            ZYPEZDItemValueSetter.setValue(chrgPMsg.addlChrgPrcDealAmt, cBizMsg.addlChrgPrcDealAmt_C);
//            ZYPEZDItemValueSetter.setValue(chrgPMsg.dealPrcListPrcAmt, cBizMsg.dealPrcListPrcAmt_C);
            ZYPEZDItemValueSetter.setValue(chrgPMsg.addlChrgPrcDealAmt, round(cBizMsg.addlChrgPrcDealAmt_C.getValue(), scale));
            ZYPEZDItemValueSetter.setValue(chrgPMsg.dealPrcListPrcAmt, round(cBizMsg.dealPrcListPrcAmt_C.getValue(), scale));
            if (!ZYPCommonFunc.hasValue(chrgPMsg.dealPrcListPrcAmt)) {
//                ZYPEZDItemValueSetter.setValue(chrgPMsg.dealPrcListPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(chrgPMsg.dealPrcListPrcAmt, round(BigDecimal.ZERO, scale));
            }
            // Mod End 2017/10/19 QC#21860

            ZYPEZDItemValueSetter.setValue(chrgPMsg.svcPrcCatgCd, SVC_PRC_CATG.MAIN_UNIT_ADDITIONAL_CHARGE);
            ZYPEZDItemValueSetter.setValue(chrgPMsg.crRebilCd, cBizMsg.crRebilCd_C);

            if (ZYPCommonFunc.hasValue(chrgPMsg.dsContrAddlChrgPk)) {
                ZYPEZDItemValueSetter.setValue(chrgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
            } else {
                ZYPEZDItemValueSetter.setValue(chrgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
            }
            // 2018/05/09 QC#25030 add start
            ZYPEZDItemValueSetter.setValue(chrgPMsg.printDtlFlg, cBizMsg.printDtlFlg_C);
            // 2018/05/09 QC#25030 add end
        }
        // START 2017/10/19 [QC#21656, MOD]
        // pMsg.svcAddlChrgPrcList.setValidCount(bizMsg.C.getValidCount());
        pMsg.svcAddlChrgPrcList.setValidCount(ixC);
        // END   2017/10/19 [QC#21656, MOD]
    }

    private static void editSvcConfigRefList(String glblCmpyCd, NSZC115001PMsg pMsg, NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {

        int i;
        // START 2017/10/19 [QC#21656, ADD]
        int idxI = 0;
        // END   2017/10/19 [QC#21656, ADD]
        for (i = 0; i < bizMsg.I.getValidCount(); i++) {
            NSAL1330_ICMsg iBizMsg = bizMsg.I.no(i);

            // START 2017/10/19 [QC#21656, ADD]
            if (ZYPCommonFunc.hasValue(iBizMsg.dsContrDtlPk_I)) {
                NSAL1330_RCMsg rBizMsg = getSvcPrcInfoByDsContrDtlPk(bizMsg, iBizMsg.dsContrDtlPk_I.getValue());
                if (rBizMsg != null && ZYPConstant.FLG_OFF_N.equals(rBizMsg.scrEntAvalFlg_R.getValue())
                        && ZYPConstant.FLG_ON_Y.equals(bizMsg.contrAvalFlg.getValue())) {
                    continue;
                }
            }
            // END   2017/10/19 [QC#21656, ADD]

            // START 2017/10/19 [QC#21656, MOD]
            // NSZC115001_svcConfigRefListPMsg cscPMsg = pMsg.svcConfigRefList.no(i);
            NSZC115001_svcConfigRefListPMsg cscPMsg = pMsg.svcConfigRefList.no(idxI++);
            // END   2017/10/19 [QC#21656, MOD]

            ZYPEZDItemValueSetter.setValue(cscPMsg.dsOrdPosnNum, iBizMsg.dsOrdPosnNum_I);
            ZYPEZDItemValueSetter.setValue(cscPMsg.shellLineNum, bizMsg.shellLineNum);
            ZYPEZDItemValueSetter.setValue(cscPMsg.dsContrDtlPk, iBizMsg.dsContrDtlPk_I);
            ZYPEZDItemValueSetter.setValue(cscPMsg.dsContrPk, bizMsg.dsContrPk);
            ZYPEZDItemValueSetter.setValue(cscPMsg.cpoOrdNum, bizMsg.refCpoOrdNum);
            ZYPEZDItemValueSetter.setValue(cscPMsg.cpoDtlLineNum, iBizMsg.cpoDtlLineNum_I);
            ZYPEZDItemValueSetter.setValue(cscPMsg.cpoDtlLineSubNum, iBizMsg.cpoDtlLineSubNum_I);
            ZYPEZDItemValueSetter.setValue(cscPMsg.custIssPoNum, iBizMsg.custPoNum_I);
            ZYPEZDItemValueSetter.setValue(cscPMsg.custIssPoDt, iBizMsg.poDt_I);
            // 2018/04/24 QC#20162 Add Start
            ZYPEZDItemValueSetter.setValue(cscPMsg.dsPoExprDt, iBizMsg.dsPoExprDt_I);
            // 2018/04/24 QC#20162 Add End
            ZYPEZDItemValueSetter.setValue(cscPMsg.mtrReadMethCd, iBizMsg.mtrReadMethCd_I);
            ZYPEZDItemValueSetter.setValue(cscPMsg.crRebilCd, iBizMsg.crRebilCd_I);
            ZYPEZDItemValueSetter.setValue(cscPMsg.svcConfigMstrPk, iBizMsg.svcConfigMstrPk_I);

            if (ZYPCommonFunc.hasValue(cscPMsg.dsContrDtlPk)) {
                ZYPEZDItemValueSetter.setValue(cscPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_MOD);
            } else {
                ZYPEZDItemValueSetter.setValue(cscPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_NEW);
            }
        }
 
        // START 2017/10/19 [QC#21656, MOD]
        // pMsg.svcConfigRefList.setValidCount(i);
        pMsg.svcConfigRefList.setValidCount(idxI);
        // END   2017/10/19 [QC#21656, MOD]
    }

    // add start 2017/06/12 QC#18935
    private static void editSvcAddlBaseChrgDeleteList(String glblCmpyCd, NSZC115001PMsg pMsg, NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        int ixB = pMsg.svcAddlBasePrcList.getValidCount();
        int ixC = pMsg.svcAddlChrgPrcList.getValidCount();

        for (int i = 0; i < bizMsg.M.getValidCount(); i++) {
            NSAL1330_MCMsg mBizMsg = bizMsg.M.no(i);

            if (SVC_PRC_CATG.MAIN_UNIT_ADDITIONAL_CHARGE.equals(mBizMsg.svcPrcCatgCd_M.getValue())) {
                NSZC115001_svcAddlChrgPrcListPMsg chrgPMsg = pMsg.svcAddlChrgPrcList.no(ixC);

                ZYPEZDItemValueSetter.setValue(chrgPMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_DEL);
                ZYPEZDItemValueSetter.setValue(chrgPMsg.shellLineNum, bizMsg.shellLineNum);
                ZYPEZDItemValueSetter.setValue(chrgPMsg.dsContrAddlChrgPk, mBizMsg.dsContrAddlChrgPk_M);
                ZYPEZDItemValueSetter.setValue(chrgPMsg.dsContrPk, bizMsg.dsContrPk);
                ZYPEZDItemValueSetter.setValue(chrgPMsg.dsContrDtlPk, mBizMsg.dsContrDtlPk_M);
                ZYPEZDItemValueSetter.setValue(chrgPMsg.svcPrcCatgCd, mBizMsg.svcPrcCatgCd_M);
                ixC++;

            } else {
                NSZC115001_svcAddlBasePrcListPMsg basePMsg = pMsg.svcAddlBasePrcList.no(ixB);

                ZYPEZDItemValueSetter.setValue(basePMsg.xxRqstTpCd, NSZC115001Constant.RQST_TP_DEL);
                ZYPEZDItemValueSetter.setValue(basePMsg.shellLineNum, bizMsg.shellLineNum);
                ZYPEZDItemValueSetter.setValue(basePMsg.dsContrAddlChrgPk, mBizMsg.dsContrAddlChrgPk_M);
                ZYPEZDItemValueSetter.setValue(basePMsg.dsContrPk, bizMsg.dsContrPk);
                ZYPEZDItemValueSetter.setValue(basePMsg.dsContrDtlPk, mBizMsg.dsContrDtlPk_M);
                ZYPEZDItemValueSetter.setValue(basePMsg.svcPrcCatgCd, mBizMsg.svcPrcCatgCd_M);
                ZYPEZDItemValueSetter.setValue(basePMsg.addlChrgCatgCd, mBizMsg.addlChrgCatgCd_M);
                ixB++;
            }
        }
        pMsg.svcAddlBasePrcList.setValidCount(ixB);
        pMsg.svcAddlChrgPrcList.setValidCount(ixC);
    }
    // add end 2017/06/12 QC#18935

    private static void setErrorMessageForNSAM0682E(NSAL1330CMsg bizMsg) {
        List<Map<String, String>> rsList = null;
        for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
            NSAL1330_RCMsg rBizMsg = bizMsg.R.no(i);
            S21SsmEZDResult ssmResult = null;
            for (int ixU = 0; ixU < bizMsg.U.getValidCount(); ixU++) {
                NSAL1330_UCMsg uBizMsg = bizMsg.U.no(ixU);
                if (!ZYPCommonFunc.hasValue(uBizMsg.prcListBandDescTxt_U)) {
                    continue;
                }
                if (ZYPCommonFunc.hasValue(uBizMsg.mdlId_U) && ZYPCommonFunc.hasValue(rBizMsg.mdlId_R) //
                        && uBizMsg.mdlId_U.getValue().compareTo(rBizMsg.mdlId_R.getValue()) == 0 //
                        && ZYPCommonFunc.hasValue(uBizMsg.dsOrdPosnNum_U) && ZYPCommonFunc.hasValue(rBizMsg.dsOrdPosnNum_R) //
                        && uBizMsg.dsOrdPosnNum_U.getValue().equals(rBizMsg.dsOrdPosnNum_R.getValue())) {
                    NSAL1330_ACMsg aBizMsg = new NSAL1330_ACMsg();
                    NSAL1330_ZCMsg zBizMsg = new NSAL1330_ZCMsg();
                    EZDBMsg.copy(rBizMsg, "R", aBizMsg, "A");
                    EZDBMsg.copy(uBizMsg, "U", zBizMsg, "Z");
                    ssmResult = NSAL1330Query.getInstance().getSplyAgmtPlnNm(aBizMsg, zBizMsg, bizMsg);
                    if (ssmResult == null) {
                        uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NSAM0671E, new String[] {"Band:" + uBizMsg.prcListBandDescTxt_U.getValue() });
                    }
                    rsList = (List<Map<String, String>>) ssmResult.getResultObject();
                    if (rsList == null || rsList.size() == 0) {
                        uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NSAM0671E, new String[] {"Band:" + uBizMsg.prcListBandDescTxt_U.getValue() });
                        continue;
                    }
                    for (Map<String, String> rsMap : rsList) {
                        if (ZYPConstant.FLG_ON_Y.equals(rsMap.get("EXISTS_FLG"))) {
                            continue;
                        }
                        uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NSAM0682E, new String[] {rsMap.get("EDIT_SPLY_AGMT_PLN_NM") });
                        return;
                    }
                }
            }
        }
        return;
    }

    /**
     * deleteShellPrice
     * 
     * @param glblCmpyCd String
     * @param bizMsg NSAL1330CMsg
     */
    public static void deleteShellPrice(String glblCmpyCd, NSAL1330CMsg bizMsg) {
        NSZC115001PMsg pMsg = new NSZC115001PMsg();

        pMsg.xxProcMd.setValue(NSZC115001Constant.PROC_MODE_MOD);
        pMsg.glblCmpyCd.setValue(glblCmpyCd);
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // pMsg.slsDt.setValue(ZYPDateUtil.getSalesDate());
        pMsg.slsDt.setValue(bizMsg.slsDt.getValue());
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
        pMsg.refCpoOrdNum.setValue(bizMsg.refCpoOrdNum.getValue());

        pMsg.svcDtlList.no(0).xxRqstTpCd.setValue(NSZC115001Constant.RQST_TP_DEL);
        pMsg.svcDtlList.no(0).dsContrPk.setValue(bizMsg.dsContrPk.getValue());
        pMsg.svcDtlList.no(0).delFlg_PI.setValue(ZYPConstant.FLG_ON_Y);
        pMsg.svcDtlList.setValidCount(0);

        new NSZC115001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);
            bizMsg.setMessageInfo(msgList.get(0));
            return;
        }
    }

    private static boolean isMdlPrcNoEntry(NSAL1330_ACMsg aBizMsg, String usgBllgCycleCd) {
        // START 2017/10/11 [QC#20059, ADD]
        if (isConfigLevelPriceSetting(aBizMsg)) {
            // create dummy record
            return false;
        }
        // END   2017/10/11 [QC#20059, ADD]
        if (!ZYPCommonFunc.hasValue(usgBllgCycleCd)) { // Non-Meter
            if (ZYPCommonFunc.hasValue(aBizMsg.prcCatgCd_A)) {
                return false;
            }
            if (ZYPCommonFunc.hasValue(aBizMsg.basePrcDealAmt_A)) {
                return false;
            }
            return true;
        }

        if (ZYPCommonFunc.hasValue(aBizMsg.prcRateTpCd_A) //
                && !isRateTypeAnnual(aBizMsg.prcRateTpCd_A.getValue())) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(aBizMsg.prcTierSvcOfferCd_A)) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(aBizMsg.xxTotPrcAmt_PB)) {
            return false;
        }
        return true;
    }

    private static boolean isMdlPrcNoEntry(NSAL1330_RCMsg rBizMsg, String usgBllgCycleCd) {
        if (!ZYPCommonFunc.hasValue(usgBllgCycleCd)) { // Non-Meter
            if (ZYPCommonFunc.hasValue(rBizMsg.prcCatgCd_R)) {
                return false;
            }
            if (ZYPCommonFunc.hasValue(rBizMsg.basePrcDealAmt_R)) {
                return false;
            }
            return true;
        }

        if (ZYPCommonFunc.hasValue(rBizMsg.prcRateTpCd_R) //
                && !isRateTypeAnnual(rBizMsg.prcRateTpCd_R.getValue())) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(rBizMsg.prcTierSvcOfferCd_R)) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(rBizMsg.xxTotPrcAmt_PR)) {
            return false;
        }
        return true;
    }

    private static String getDsOrdPosnNumConfig(NSAL1330CMsg bizMsg, BigDecimal mdlId, BigDecimal dsContrDtlPk) {
        if (!ZYPCommonFunc.hasValue(mdlId) || !ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            return null;
        }
        for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
            NSAL1330_ICMsg iBizMsg = bizMsg.I.no(i);
            if (mdlId.compareTo(iBizMsg.mdlId_I.getValue()) == 0 //
                    && dsContrDtlPk.compareTo(iBizMsg.dsContrDtlPk_I.getValue()) == 0) {
                return iBizMsg.dsOrdPosnNum_I.getValue();
            }
        }
        return null;
    }

    // START 2017/10/05 [QC#20059, ADD]
    public static boolean isConfigLevelPriceSetting(NSAL1330_ACMsg aBizMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(aBizMsg.xxSelFlg_A.getValue())) {
            return true;
        }
        return false;
    }

    public static boolean isConfigLevelPriceSetting(NSAL1330CMsg bizMsg, BigDecimal mdlId) {
        for (int ixA = 0; ixA < bizMsg.A.getValidCount(); ixA++) {
            NSAL1330_ACMsg aBizMsg = bizMsg.A.no(ixA);
            if (!isSameValue(mdlId, aBizMsg.mdlId_A.getValue())) {
                continue;
            }
            return isConfigLevelPriceSetting(aBizMsg);
        }
        return false;
    }

    public static void clearModelPricing(NSAL1330CMsg bizMsg) {
        int ixA = bizMsg.xxNum_A.getValueInt();

        NSAL1330_ACMsg aBizMsg = bizMsg.A.no(ixA);
        aBizMsg.prcCatgCd_A.clear();
        aBizMsg.prcCatgNm_A.clear();
        aBizMsg.prcMtrPkgPk_A.clear();
        aBizMsg.prcMtrPkgPk_KP.clear();
        aBizMsg.prcMtrPkgNm_VW.clear();
        aBizMsg.prcTierSvcOfferCd_A.clear();
        aBizMsg.xxTotPrcAmt_PB.clear();
        aBizMsg.xxTotPrcAmt_EB.clear();
        aBizMsg.xxTotPrcAmt_TB.clear();

        BigDecimal mdlId = aBizMsg.mdlId_A.getValue();
        List<Integer> delList = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
            if (isSameValue(mdlId, bizMsg.Z.no(i).mdlId_Z.getValue())) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.Z, delList);

        delList.clear();
        for (int i = 0; i < bizMsg.X.getValidCount(); i++) {
            if (isSameValue(mdlId, bizMsg.X.no(i).mdlId_X.getValue())) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.X, delList);
    }

    public static void clearConfigPricing(NSAL1330CMsg bizMsg) {
        int ixA = bizMsg.xxNum_A.getValueInt();
        NSAL1330_ACMsg aBizMsg = bizMsg.A.no(ixA);

        for (int ixR = 0; ixR < bizMsg.R.getValidCount(); ixR++) {
            NSAL1330_RCMsg rBizMsg = bizMsg.R.no(ixR);

            if (!isSameValue(aBizMsg.mdlId_A.getValue(), rBizMsg.mdlId_R.getValue())) {
                continue;
            }

            rBizMsg.prcCatgCd_R.clear();
            rBizMsg.prcCatgNm_R.clear();
            rBizMsg.prcMtrPkgPk_R.clear();
            rBizMsg.prcMtrPkgPk_RL.clear();
            rBizMsg.prcMtrPkgNm_RL.clear();
            rBizMsg.prcTierSvcOfferCd_R.clear();
            rBizMsg.xxTotPrcAmt_PR.clear();
            rBizMsg.xxTotPrcAmt_ER.clear();
            rBizMsg.xxTotPrcAmt_TR.clear();
            rBizMsg.billToCustCd_R.clear();
            rBizMsg.dsAcctNm_R.clear();
            rBizMsg.billToLocNum_R.clear();
            rBizMsg.xxGenlFldAreaTxt_R.clear();

            BigDecimal mdlId = rBizMsg.mdlId_R.getValue();
            String dsOrdPosnNum = rBizMsg.dsOrdPosnNum_R.getValue();
            List<Integer> delList = new ArrayList<Integer>();
            for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
                if ((ZYPCommonFunc.hasValue(mdlId) //
                        && mdlId.compareTo(bizMsg.U.no(i).mdlId_U.getValue()) == 0) //
                        && (ZYPCommonFunc.hasValue(dsOrdPosnNum) //
                        && dsOrdPosnNum.compareTo(bizMsg.U.no(i).dsOrdPosnNum_U.getValue()) == 0)) {
                    delList.add(i);
                }
            }
            ZYPTableUtil.deleteRows(bizMsg.U, delList);

            delList.clear();
            for (int i = 0; i < bizMsg.V.getValidCount(); i++) {
                if ((ZYPCommonFunc.hasValue(mdlId) && mdlId.compareTo(bizMsg.V.no(i).mdlId_V.getValue()) == 0) && (ZYPCommonFunc.hasValue(dsOrdPosnNum) && dsOrdPosnNum.compareTo(bizMsg.V.no(i).dsOrdPosnNum_V.getValue()) == 0)) {
                    delList.add(i);
                }
            }
            ZYPTableUtil.deleteRows(bizMsg.V, delList);
        }
    }
    // END   2017/10/05 [QC#20059, ADD]

    // Add Start 2017/10/18 QC#21860
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

    private static BigDecimal multiply(BigDecimal num1, BigDecimal num2, int scale) {
        if (num1 == null || num2 == null) {
            return BigDecimal.ZERO;
        }
        return round(num1.multiply(num2), scale);
    }

    private static BigDecimal divide(BigDecimal num1, BigDecimal num2, int scale) {
        if (num1 == null || num2 == null) {
            return BigDecimal.ZERO;
        }
        if (BigDecimal.ZERO.compareTo(num2) == 0) {
            return BigDecimal.ZERO;
        }
        num1 = round(num1, scale);
        return round(num1.divide(num2, scale, BigDecimal.ROUND_HALF_UP), scale);
    }
    // Add End 2017/10/18 QC#21860

    // START 2017/10/24 K.Kojima [QC#21690,ADD]
    private static NWZC157001_xxPrcListPMsg callPricingAPIforDefaultPrcList(NSAL1330CMsg bizMsg, String glblCmpyCd, String prcCatgCd) {
        NWZC157001PMsg pMsg = new NWZC157001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, bizMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(pMsg.orgRqstDelyDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(pMsg.prcCtxTpCd, PRC_CTX_TP.SERVICE_PRICING);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, getLineBizTpCd(bizMsg));
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, bizMsg.sellToCustCd);
        pMsg.csmpNum.clear();
        pMsg.dlrRefNum.clear();
        pMsg.prcContrNum.clear();
        pMsg.coaBrCd.clear();
        ZYPEZDItemValueSetter.setValue(pMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_ON_Y);

        new NWZC157001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                return null;
            }
        }

        if (pMsg.xxPrcList.getValidCount() == 0) {
            return null;
        }
        // QC#25104 mod
        NWZC157001_xxPrcListPMsg firstPrcListPMsg = pMsg.xxPrcList.no(0);
        if (!ZYPCommonFunc.hasValue(prcCatgCd)) {
            if (!ZYPCommonFunc.hasValue(firstPrcListPMsg.prcCatgCd)) {
                return null;
            }
            return firstPrcListPMsg;
        }
        String prtyNum = firstPrcListPMsg.prtyNum.getValue();
        for (int i = 0; i < pMsg.xxPrcList.getValidCount(); i++) {
            NWZC157001_xxPrcListPMsg xxPrcListPMsg = pMsg.xxPrcList.no(i);
            if (!S21StringUtil.isEquals(prtyNum, xxPrcListPMsg.prtyNum.getValue())) {
                continue;
            }
            if (S21StringUtil.isEquals(prcCatgCd, xxPrcListPMsg.prcCatgCd.getValue())) {
                return xxPrcListPMsg;
            }
        }
        return firstPrcListPMsg;
    }
    // END 2017/10/24 K.Kojima [QC#21690,ADD]


    // START 2017/10/25 [QC#21556, ADD]
    /**
     * setSvcPrcInitFlAddMachine
     * @param bizMsg NSAL1330CMsg
     * @param countQtyList Map<BigDecimal, Integer>
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean setSvcPrcInitFlAddMachine(NSAL1330CMsg bizMsg, Map<BigDecimal, Integer> countQtyList, String glblCmpyCd) {

        // Detail Header(Model)
        S21SsmEZDResult rsltModelDtlHdr = NSAL1330Query.getInstance().getInitDataFromModelPricingHeaderForFleetAddMachine(bizMsg);
        if (rsltModelDtlHdr.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltModelDtlHdr.getResultObject();
            if (rsltList != null && rsltList.size() > 1) {
                rsltList.remove(1);
            }
            setModelDtlHeaderRsltToBizMsg(bizMsg, rsltList);
        } else {
            bizMsg.A.setValidCount(0);
        }

        // Detail (Model)
        S21SsmEZDResult rsltModelDtl = NSAL1330Query.getInstance().getInitDataFromModelPricingDetailForFleetAddMachine(bizMsg);
        if (rsltModelDtl.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltModelDtl.getResultObject();
            List<Map<String, Object>> rsltSubList = new ArrayList<Map<String, Object>>();
            BigDecimal priority = null;
            for (Map<String, Object> rslt : rsltList) {
                if (priority == null) {
                    priority = (BigDecimal)rslt.get("PRIORITY");
                }
                if (priority != null && priority.compareTo((BigDecimal)rslt.get("PRIORITY")) != 0) {
                    break;
                }
                rsltSubList.add(rslt);
            }
            setModelDtlRsltToBizMsg(bizMsg, rsltSubList);

        } else {
            bizMsg.X.setValidCount(0);
            bizMsg.Z.setValidCount(0);
        }

        if (bizMsg.A.getValidCount() > 0) {
            int aIndex = 0;

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(0).prcMtrPkgPk_A)) {
                S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getSvcPricingPkList(bizMsg, aIndex, glblCmpyCd);
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
                if (resList != null && !resList.isEmpty()) {

                    for (int j = 0; j < resList.size(); j++) {
                        Map<String, Object> result = resList.get(j);
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).prcMtrPkgPk_KP.no(j), (BigDecimal) result.get("PRC_MTR_PKG_PK"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).prcMtrPkgNm_VW.no(j), (String) result.get("PRC_MTR_PKG_NM"));
                    }
                }
            }
            // get Name
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).t_MdlNm_A, FLEET);
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(aIndex).prcCatgCd_A)) {
                String prcCatgNm = getPrcCatgNm(bizMsg.A.no(aIndex).prcCatgCd_A.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).prcCatgNm_A, prcCatgNm);
                String prcListTpCd = getPrcListTpCd(bizMsg.A.no(aIndex).prcCatgCd_A.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).prcListTpCd_A, prcListTpCd);
            }

            for (int j = 0; j < bizMsg.Z.getValidCount(); j++) {
                // set BackUp
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).mlyCopyInclPrcQty_BK, bizMsg.Z.no(j).mlyCopyInclPrcQty_Z);
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).contrMtrMultRate_BK, bizMsg.Z.no(j).contrMtrMultRate_Z);
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).xsMtrAmtRate_BK, bizMsg.Z.no(j).xsMtrAmtRate_Z);
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).minCopyVolCnt_BK, bizMsg.Z.no(j).minCopyVolCnt_Z);
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).maxCopyVolCnt_BK, bizMsg.Z.no(j).maxCopyVolCnt_Z);

                if (ZYPCommonFunc.hasValue(bizMsg.Z.no(j).bllgMtrLbCd_Z)) {
                    String bllgMtrNm = getMtrNm(bizMsg.Z.no(j).bllgMtrLbCd_Z.getValue(), glblCmpyCd);
                    if (!ZYPCommonFunc.hasValue(bllgMtrNm)) {
                        bizMsg.setMessageInfo(NSAM0647E, new String[] {"MTR_LB", bizMsg.Z.no(j).bllgMtrLbCd_Z.getValue() });
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).mtrLbDescTxt_ZB, bllgMtrNm);
                    }
                }
                if (ZYPCommonFunc.hasValue(bizMsg.Z.no(j).usgMdseCd_Z)) {
                    String mdseNm = getMdseNm(bizMsg.Z.no(j).usgMdseCd_Z.getValue(), glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).mdseDescShortTxt_Z, mdseNm);
                }
                if (ZYPCommonFunc.hasValue(bizMsg.Z.no(j).regMtrLbCd_Z)) {
                    String regMtrNm = getMtrNm(bizMsg.Z.no(j).regMtrLbCd_Z.getValue(), glblCmpyCd);
                    if (!ZYPCommonFunc.hasValue(regMtrNm)) {
                        bizMsg.setMessageInfo(NSAM0647E, new String[] {"MTR_LB", bizMsg.Z.no(j).regMtrLbCd_Z.getValue() });
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).mtrLbDescTxt_Z, regMtrNm);
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(aIndex).xxTotPrcAmt_PB)) {
                // set Price
                BigDecimal periodicBase = bizMsg.A.no(aIndex).xxTotPrcAmt_PB.getValue();
                BigDecimal baseBllgCycle = getBllgCycle(bizMsg);
                if (ZYPCommonFunc.hasValue(baseBllgCycle)) {
                    // START 2018/03/20 U.Kim [QC#24088,ADD]
                    BigDecimal qty = bizMsg.A.no(aIndex).xxTotQtyCnt_A.getValue();
                    // END 2018/03/20 U.Kim [QC#24088,ADD]

                    BigDecimal term = bizMsg.termMthAot.getValue();

                    // START 2018/03/20 U.Kim [QC#24088,MOD]
                    // BigDecimal extendedBase = periodicBase;
                    // START 2018/06/19 K.Kojima [QC#26591,MOD]
                    // BigDecimal extendedBase = getExtendedBase(periodicBase, qty);
                    BigDecimal extendedBase = getExtendedBase(periodicBase, qty, bizMsg.dsContrCatgCd.getValue());
                    // END 2018/06/19 K.Kojima [QC#26591,MOD]
                    // END 2018/03/20 U.Kim [QC#24088,MOD]
                    BigDecimal totalBase = BigDecimal.ZERO;
                    GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
                    int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());

                    if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bizMsg.baseBllgCycleCd.getValue())) {
                        totalBase = extendedBase;
                    } else {
                        totalBase = multiply(extendedBase, divide(term, baseBllgCycle, scale), scale);
                    }

                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_EB, round(extendedBase, scale));
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_TB, round(totalBase, scale));
                }
            }
        }

        return true;
    }

    /**
     * getMtrLbAggAddMachine
     * @param bizMsg NSAL1330CMsg
     * @param aBizMsg NSAL1330_ACMsg
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult getMtrLbAggAddMachine(NSAL1330CMsg bizMsg, NSAL1330_ACMsg aBizMsg) {
        List<String> bllgMtrLbCdList = (List<String>) NSAL1330Query.getInstance().getAggContrBllgLbCdList(bizMsg).getResultObject();
        return NSAL1330Query.getInstance().getMtrLbAggAddMachine(bizMsg, bllgMtrLbCdList.toArray(new String[] {}));
    }

    /**
     * getMtrLbAggAddMachineConfig
     * @param bizMsg NSAL1330CMsg
     * @param rBizMsg NSAL1330_RCMsg
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult getMtrLbAggAddMachineConfig(NSAL1330CMsg bizMsg, NSAL1330_RCMsg rBizMsg) {
        List<String> bllgMtrLbCdList = (List<String>) NSAL1330Query.getInstance().getAggContrBllgLbCdList(bizMsg).getResultObject();
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // return NSAL1330Query.getInstance().getMtrLbAggAddMachineConfig(rBizMsg, bllgMtrLbCdList.toArray(new String[] {}));
        return NSAL1330Query.getInstance().getMtrLbAggAddMachineConfig(rBizMsg, bllgMtrLbCdList.toArray(new String[] {}), bizMsg);
        // END 2018/07/10 K.Kojima [QC#27135,MOD]
    }
    // END   2017/10/25 [QC#21556, ADD]

    // START 2018/03/20 U.Kim [QC#24088,ADD]
    // START 2018/06/19 K.Kojima [QC#26591,MOD]
    // private static BigDecimal getExtendedBase(BigDecimal periodicBase, BigDecimal qty) {
    private static BigDecimal getExtendedBase(BigDecimal periodicBase, BigDecimal qty, String dsContrCatgCd) {
    // END 2018/06/19 K.Kojima [QC#26591,MOD]
        if (periodicBase == null) {
            return BigDecimal.ZERO;
        }
        if (qty == null) {
            return BigDecimal.ZERO;
        }
        // START 2018/06/19 K.Kojima [QC#26591,MOD]
        // return periodicBase.multiply(qty);
        if (ZYPCommonFunc.hasValue(dsContrCatgCd) && DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            return periodicBase;
        } else {
            return periodicBase.multiply(qty);
        }
        // END 2018/06/19 K.Kojima [QC#26591,MOD]
    }
    // END 2018/03/20 U.Kim [QC#24088,ADD]
    // Add Start 2018/09/04 QC#28055
    private static boolean isScrEntAvalForRental(NSAL1330CMsg bizMsg) {
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (ZYPConstant.FLG_OFF_N.equals(bizMsg.B.no(i).scrEntAvalFlg_B.getValue())) {
                return false;
            }
        }
        return true;
    }
    // Add End 2018/09/04 QC#28055

    // 2019/07/18 S21_NA#51327 Add Start
//    private static boolean checkExistsContrDtl(NSAL1330CMsg bizMsg) {
//
//        if (bizMsg.I.getValidCount() > 0) {
//
//            List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();
//            for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
//                if (ZYPCommonFunc.hasValue(bizMsg.I.no(i).dsContrDtlPk_I)) {
//                    dsContrDtlPkList.add(bizMsg.I.no(i).dsContrDtlPk_I.getValue());
//                }
//            }
//            S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().checkCreatedContract(bizMsg, dsContrDtlPkList);
//
//            List<Map<String, Object>> rsltMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
//            if (rsltMapList != null && rsltMapList.size() > 0) {
//                bizMsg.setMessageInfo(NSAM0750E);
//                return true;
//            }
//        }
//
//        return false;
//    }
    // 2019/07/18 S21_NA#51327 Add End

    // 2019/09/25 QC#51323 Add Start
    public static Map<BigDecimal, String> getSmryLineFlgMap(NSAL1330CMsg bizMsg) {
        Map<BigDecimal, String> flgMap = new HashMap<BigDecimal, String>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            flgMap.put(bizMsg.A.no(i).mdlId_A.getValue(), bizMsg.A.no(i).xxSmryLineFlg_A.getValue());
        }

        return flgMap;
    }

    public static void setSmryLineFlgMap(NSAL1330CMsg bizMsg, Map<BigDecimal, String> flgMap) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (!flgMap.isEmpty() && flgMap.containsKey(bizMsg.A.no(i).mdlId_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxSmryLineFlg_A, flgMap.get(bizMsg.A.no(i).mdlId_A.getValue()));
            }
        }
    }
// 2019/09/25 QC#51323 Add End

 // 2023/11/06 QC#61924 Add Start
    /**
     * hasDeactivateAccountOrLocation
     * @param bizMsg NSAL1330CMsg
     * @param glblMsg NSAL1330SMsg
     * @return boolean
     */
    public static boolean hasDeactivateAccountOrLocation(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {

        boolean errorFlg = false;

        SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();
        sellToCustTMsg = getSellToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.dsAcctNum.getValue());
        if (null != sellToCustTMsg) {
            if (ZYPConstant.FLG_ON_Y.equals(sellToCustTMsg.deacNewTrxFlg.getValue())) {
                bizMsg.dsAcctNum.setErrorInfo(1, NSAM0782E, new String[] {bizMsg.dsAcctNum.getValue() });
                errorFlg = true;
            }
        }

        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        billToCustTMsg = getBillToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustLocCd.getValue());
        if (null != billToCustTMsg) {
            if (ZYPConstant.FLG_ON_Y.equals(billToCustTMsg.deacNewTrxFlg.getValue())) {
                bizMsg.billToCustLocCd.setErrorInfo(1, NSAM0781E, new String[] {bizMsg.billToCustLocCd.getValue() });
                errorFlg = true;
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
}
