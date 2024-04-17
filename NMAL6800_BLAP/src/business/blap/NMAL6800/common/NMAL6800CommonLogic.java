package business.blap.NMAL6800.common;

import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL6800.NMAL6800CMsg;
import business.blap.NMAL6800.NMAL6800Query;
import business.blap.NMAL6800.NMAL6800SMsg;
import business.blap.NMAL6800.NMAL6800_ASMsg;
import business.db.COA_PRODTMsg;
import business.db.COA_PROJTMsg;
import business.db.MKT_MDLTMsg;
import business.db.MKT_MDL_SEGTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Item Master Search(Popup) Common Logic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         E.Yoshitake     Create          N/A
 * 2019/10/18   Fujitsu         C.Hara          Update          QC#53815
 *</pre>
 */
public class NMAL6800CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    public static void setInitialItemStsPulldown(NMAL6800CMsg bizMsg, String globalCompanyCode) {
        S21SsmEZDResult result = NMAL6800Query.getInstance().getItemStatusList(bizMsg, globalCompanyCode);
        if (result.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                if (map != null && map.get("MDSE_ITEM_STS_CD") != null) {
                    bizMsg.mdseItemStsCd_L1.no(i).setValue((String) map.get("MDSE_ITEM_STS_CD"));
                    bizMsg.mdseItemStsNm_L1.no(i).setValue((String) map.get("MDSE_ITEM_STS_NM"));
                    i++;
                }
            }
        }
    }

    public static void setInitialPlanningGroupPulldown(NMAL6800CMsg bizMsg, String globalCompanyCode) {
        S21SsmEZDResult result = NMAL6800Query.getInstance().getPlanningGroupList(bizMsg, globalCompanyCode);
        if (result.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                if (map != null && map.get("PRCH_GRP_CD") != null) {
                    bizMsg.prchGrpCd_L1.no(i).setValue((String) map.get("PRCH_GRP_CD"));
                    bizMsg.prchGrpNm_L1.no(i).setValue((String) map.get("PRCH_GRP_NM"));
                    i++;
                }
            }
        }
    }

    public static void setProductLevelName(NMAL6800CMsg bizMsg, String globalCompanyCode) {
        S21SsmEZDResult result = NMAL6800Query.getInstance().getProductLevelName(bizMsg, globalCompanyCode);
        if (result.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> list = (List<Map<String, String>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            for (Map<String, String> map : list) {
                if (map != null && map.get("MDSE_STRU_ELMNT_TP_CD") != null) {
                    // PLG
                    if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        bizMsg.mdseStruElmntTpNm_L1.setValue((String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                        // PL
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        bizMsg.mdseStruElmntTpNm_L2.setValue((String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                        // PL2
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        bizMsg.mdseStruElmntTpNm_L3.setValue((String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                        // PL3
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        bizMsg.mdseStruElmntTpNm_L4.setValue((String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                        // PL4
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        bizMsg.mdseStruElmntTpNm_L5.setValue((String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                    }
                }
            }
        }
    }

    public static void setSearchResultToGlblMsg(S21SsmEZDResult ssmResult, NMAL6800SMsg glblMsg) {
        @SuppressWarnings("unchecked")
        List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

        glblMsg.A.clear();
        glblMsg.A.setValidCount(0);
        for (int i = 0; i < resultList.size() && i < glblMsg.A.length(); i++) {
            Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
            NMAL6800_ASMsg glblLineMsg = glblMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseCd_A1, (String) resultMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseNm_A1, (String) resultMap.get("MDSE_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseDescShortTxt_A1, (String) resultMap.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mnfItemCd_A1, (String) resultMap.get("MNF_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemClsTpCd_A1, (String) resultMap.get("MDSE_ITEM_CLS_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemClsTpNm_A1, (String) resultMap.get("MDSE_ITEM_CLS_TP_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemTpCd_A1, (String) resultMap.get("MDSE_ITEM_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemTpNm_A1, (String) resultMap.get("MDSE_ITEM_TP_NM"));
            if (resultMap.get("COA_MDSE_TP_CD") != null) {
                ZYPEZDItemValueSetter.setValue(glblLineMsg.xxScrItem54Txt_A1, (String) resultMap.get("COA_MDSE_TP_CD") + ":" + resultMap.get("COA_PROJ_DESC_TXT"));
            } else {
                ZYPEZDItemValueSetter.setValue(glblLineMsg.xxScrItem54Txt_A1, "");
            }
            ZYPEZDItemValueSetter.setValue(glblLineMsg.coaMdseTpCd_A1, (String) resultMap.get("COA_MDSE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.coaProjDescTxt_A1, (String) resultMap.get("COA_PROJ_DESC_TXT"));
            if (resultMap.get("COA_PROD_CD") != null) {
                ZYPEZDItemValueSetter.setValue(glblLineMsg.xxScrItem61Txt_A1, (String) resultMap.get("COA_PROD_CD") + ":" + resultMap.get("COA_PROD_NM"));
            } else {
                ZYPEZDItemValueSetter.setValue(glblLineMsg.xxScrItem61Txt_A1, "");
            }
            ZYPEZDItemValueSetter.setValue(glblLineMsg.coaProdCd_A1, (String) resultMap.get("COA_PROD_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.coaProdNm_A1, (String) resultMap.get("COA_PROD_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemStsCd_A1, (String) resultMap.get("MDSE_ITEM_STS_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemStsNm_A1, (String) resultMap.get("MDSE_ITEM_STS_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mktMdlCd_A1, (String) resultMap.get("MKT_MDL_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mktMdlNm_A1, (String) resultMap.get("MKT_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mktMdlSegCd_A1, (String) resultMap.get("MKT_MDL_SEG_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mktMdlSegNm_A1, (String) resultMap.get("MKT_MDL_SEG_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.zerothProdCtrlCd_A1, (String) resultMap.get("ZEROTH_PROD_CTRL_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.zerothProdCtrlNm_A1, (String) resultMap.get("ZEROTH_PROD_CTRL_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.firstProdCtrlCd_A1, (String) resultMap.get("FIRST_PROD_CTRL_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.firstProdCtrlNm_A1, (String) resultMap.get("FIRST_PROD_CTRL_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.scdProdCtrlCd_A1, (String) resultMap.get("SCD_PROD_CTRL_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.scdProdCtrlNm_A1, (String) resultMap.get("SCD_PROD_CTRL_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.thirdProdCtrlCd_A1, (String) resultMap.get("THIRD_PROD_CTRL_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.thirdProdCtrlNm_A1, (String) resultMap.get("THIRD_PROD_CTRL_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.frthProdCtrlCd_A1, (String) resultMap.get("FRTH_PROD_CTRL_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.frthProdCtrlNm_A1, (String) resultMap.get("FRTH_PROD_CTRL_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemMnfCd_A1, (String) resultMap.get("MDSE_ITEM_MNF_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemMnfNm_A1, (String) resultMap.get("MDSE_ITEM_MNF_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.upcCd_A1, (String) resultMap.get("UPC_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemActvDt_A1, (String) resultMap.get("MDSE_ITEM_ACTV_DT"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.prchGrpCd_A1, (String) resultMap.get("PRCH_GRP_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.prchGrpNm_A1, (String) resultMap.get("PRCH_GRP_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdsePrcGrpCd_A1, (String) resultMap.get("MDSE_PRC_GRP_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdsePrcGrpNm_A1, (String) resultMap.get("MDSE_PRC_GRP_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseDescLongTxt_A1, (String) resultMap.get("MDSE_DESC_LONG_TXT"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseCratTmplNm_A1, (String) resultMap.get("MDSE_CRAT_TMPL_NM"));

            ZYPEZDItemValueSetter.setValue(glblLineMsg.slsMatGrpCd_A1, (String) resultMap.get("SLS_MAT_GRP_CD_01"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.slsMatGrpDescTxt_A1, (String) resultMap.get("SLS_MAT_GRP_DESC_TXT_01"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.slsMatGrpCd_A2, (String) resultMap.get("SLS_MAT_GRP_CD_02"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.slsMatGrpDescTxt_A2, (String) resultMap.get("SLS_MAT_GRP_DESC_TXT_02"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.slsMatGrpCd_A3, (String) resultMap.get("SLS_MAT_GRP_CD_03"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.slsMatGrpDescTxt_A3, (String) resultMap.get("SLS_MAT_GRP_DESC_TXT_03"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.dsCmsnGrpCd_A1, (String) resultMap.get("DS_CMSN_GRP_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.dsCmsnGrpDescTxt_A1, (String) resultMap.get("DS_CMSN_GRP_DESC_TXT"));
            
            glblMsg.A.setValidCount(i + 1);
        }

    }

    public static void preSearchForSetName(NMAL6800CMsg bizMsg, String glblCmpyCd) {
        // MERCHANDISE TYPE
        if (ZYPCommonFunc.hasValue(bizMsg.coaMdseTpCd_H1)) {
        	COA_PROJTMsg coaProjMsg = new COA_PROJTMsg();
            ZYPEZDItemValueSetter.setValue(coaProjMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(coaProjMsg.coaProjCd, bizMsg.coaMdseTpCd_H1);
            coaProjMsg = (COA_PROJTMsg) EZDTBLAccessor.findByKey(coaProjMsg);
            if (coaProjMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaProjDescTxt_H1, coaProjMsg.coaProjDescTxt);
            } else {
                bizMsg.coaProjDescTxt_H1.clear();
            }
        } else {
            bizMsg.coaProjDescTxt_H1.clear();
        }

        // PRODUCT NAME
        if (ZYPCommonFunc.hasValue(bizMsg.coaProdCd_H1)) {
            COA_PRODTMsg coaProdMsg = new COA_PRODTMsg();
            ZYPEZDItemValueSetter.setValue(coaProdMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(coaProdMsg.coaProdCd, bizMsg.coaProdCd_H1);
            coaProdMsg = (COA_PRODTMsg) EZDTBLAccessor.findByKey(coaProdMsg);
            if (coaProdMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaProdNm_H1, coaProdMsg.coaProdNm);
            } else {
                bizMsg.coaProdNm_H1.clear();
            }
        } else {
            bizMsg.coaProdNm_H1.clear();
        }

        // MARKETING MODEL NAME
        if (ZYPCommonFunc.hasValue(bizMsg.mktMdlCd_H1)) {
            MKT_MDLTMsg mktMdlTMsg = new MKT_MDLTMsg();
            ZYPEZDItemValueSetter.setValue(mktMdlTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mktMdlTMsg.mktMdlCd, bizMsg.mktMdlCd_H1);
            mktMdlTMsg = (MKT_MDLTMsg) EZDTBLAccessor.findByKey(mktMdlTMsg);
            if (mktMdlTMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlNm_H1, mktMdlTMsg.mktMdlNm);
            } else {
                bizMsg.mktMdlNm_H1.clear();
            }
        } else {
            bizMsg.mktMdlNm_H1.clear();
        }

        // MARKETING SEGMENT NAME
        if (ZYPCommonFunc.hasValue(bizMsg.mktMdlSegCd_H1)) {
            MKT_MDL_SEGTMsg mktMdlSegTMsg = new MKT_MDL_SEGTMsg();
            ZYPEZDItemValueSetter.setValue(mktMdlSegTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mktMdlSegTMsg.mktMdlSegCd, bizMsg.mktMdlSegCd_H1);
            mktMdlSegTMsg = (MKT_MDL_SEGTMsg) EZDTBLAccessor.findByKey(mktMdlSegTMsg);
            if (mktMdlSegTMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlSegNm_H1, mktMdlSegTMsg.mktMdlSegNm);
            } else {
                bizMsg.mktMdlSegNm_H1.clear();
            }
        } else {
            bizMsg.mktMdlSegNm_H1.clear();
        }
    }

    public static void createSsmPrm(NMAL6800CMsg cMsg, String glblCmpyCd, int rownum, Map<String, Object> ssmParam) {
        ssmParam.put("rowNum", rownum);
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", cMsg.mdseCd_H1.getValue()); // ITEM
                                                           // NUMBER
        ssmParam.put("mdseDescShortTxt", cMsg.mdseDescShortTxt_H1.getValue()); // ITEM
                                                           // DESCRIPTION
        // 2019/10/18 QC#53815 Mod Start
        // ssmParam.put("mdseItemMnfCd", cMsg.mdseItemMnfCd_H1.getValue()); //MANUFACTURER
        ssmParam.put("mdseItemMnfNm", cMsg.mdseItemMnfNm_H1.getValue()); //MANUFACTURER
        // 2019/10/18 QC#53815 Mod End
        ssmParam.put("mnfItemCd", cMsg.mnfItemCd_H1.getValue()); // MANUFACTURER
                                                                 // ITEM
                                                                 // #
        ssmParam.put("upcCd", cMsg.upcCd_H1.getValue()); // UPC CODE
        ssmParam.put("mdseItemStsCd", cMsg.mdseItemStsCd_H1.getValue()); // STATUS
        ssmParam.put("mdseDescLongTxt", cMsg.mdseDescLongTxt_H1.getValue()); // LONG
                                                                 // DESCRIPTION
        ssmParam.put("mdseItemActvDtFrom", cMsg.mdseItemActvDt_H1.getValue()); // IMPLEMENTATION
                                                                               // DATE
        ssmParam.put("mdseItemActvDtTo", cMsg.mdseItemActvDt_H2.getValue()); // IMPLEMENTATION
                                                                             // DATE
        ssmParam.put("mdseCratTmplNm", cMsg.mdseCratTmplNm_H1.getValue()); // CREATED
                                                                           // FROM
                                                                           // TEMPLATE
        ssmParam.put("mdseItemTpCd", cMsg.mdseItemTpCd_H1.getValue()); // ITEM
                                                                       // TYPE
        ssmParam.put("mdseItemClsTpCd", cMsg.mdseItemClsTpCd_H1.getValue()); // ITEM
                                                                             // CLASSIFICATION
        ssmParam.put("coaMdseTpCd", cMsg.coaMdseTpCd_H1.getValue()); // MERCHANDISE
                                                                     // TYPE
        ssmParam.put("coaProdCd", cMsg.coaProdCd_H1.getValue()); // PRODUCT
                                                                 // CODE
        ssmParam.put("prchGrpCd", cMsg.prchGrpCd_H1.getValue()); // PLANNING
                                                                 // GROUP
        ssmParam.put("mdsePrcGrpCd", cMsg.mdsePrcGrpCd_H1.getValue()); // PRICING
                                                                       // GROUP
        ssmParam.put("mktMdlCd", cMsg.mktMdlCd_H1.getValue()); // MARKETING
                                                               // MODEL
        ssmParam.put("mktMdlSegCd", cMsg.mktMdlSegCd_H1.getValue()); // MARKETING
                                                                     // SEGMENT
        ssmParam.put("zerothProdCtrlNm", cMsg.zerothProdCtrlNm_H1.getValue()); // PRODUCT
                                                                               // LEVEL
                                                                               // 1
        ssmParam.put("firstProdCtrlNm", cMsg.firstProdCtrlNm_H1.getValue()); // PRODUCT
                                                                             // LEVEL
                                                                             // 2
        ssmParam.put("scdProdCtrlNm", cMsg.scdProdCtrlNm_H1.getValue()); // PRODUCT
                                                                         // LEVEL
                                                                         // 3
        ssmParam.put("thirdProdCtrlNm", cMsg.thirdProdCtrlNm_H1.getValue()); // PRODUCT
                                                                             // LEVEL
                                                                             // 4
        ssmParam.put("frthProdCtrlNm", cMsg.frthProdCtrlNm_H1.getValue()); // PRODUCT
                                                                           // LEVEL
                                                                           // 5

        ssmParam.put("slsMatGrpDescTxt_01", cMsg.slsMatGrpDescTxt_01.getValue()); // Material Group1
        ssmParam.put("slsMatGrpDescTxt_02", cMsg.slsMatGrpDescTxt_02.getValue()); // Material Group1
        ssmParam.put("slsMatGrpDescTxt_03", cMsg.slsMatGrpDescTxt_03.getValue()); // Material Group1
        ssmParam.put("dsCmsnGrpDescTxt", cMsg.dsCmsnGrpDescTxt_H1.getValue()); // Commition Group
        
        ssmParam.put("thirdPtyItemFlg_Y", cMsg.thirdPtyItemFlg_HY.getValue());
        ssmParam.put("thirdPtyItemFlg_N", cMsg.thirdPtyItemFlg_HN.getValue());
        
        ssmParam.put("xxModeCd", cMsg.xxModeCd_H1.getValue());
        

    }

    @SuppressWarnings("unchecked")
	public static Map<String, Object> getThirdProdHrch(NMAL6800CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NMAL6800Query.getInstance().getThirdProdHrch(bizMsg, globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list != null && list.size() > 0) {
				for(Map<String, Object> map : list) {
					if (map != null && map.get("THIRD_PROD_CTRL_CD") != null) {
						return map;
					}
				}
			}
		}
		return null;
	}
}
