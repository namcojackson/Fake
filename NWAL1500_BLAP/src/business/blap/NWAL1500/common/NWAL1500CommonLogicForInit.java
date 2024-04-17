/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItemArray;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500QueryForInit;
import business.blap.NWAL1500.NWAL1500SMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/24   Fujitsu         S.Takami        Create          n/a
 * 2016/02/15   Fujitsu         M.Hara          Update          QC#1692
 * 2016/02/17   Fujitsu         Y.Taoka         Update          QC#1694/QC#1699
 * 2016/08/02   Fujitsu         T.Ishii         Update          QC#8043
 * 2018/04/12   Fujitsu         K.Ishizuka      Update          QC#23704
 * 2018/08/21   Fujitsu         S.Takami        Update          S21_NA#26767
 * 2019/05/07   Fujitsu         R.Nakamura      Update          S21_NA#50015
 * </pre>
 */
public class NWAL1500CommonLogicForInit {

    /**
     * get Pull Down Data List
     * @param bizMsg NWAL1500 C Message
     * @param glblMsg NWAL1500 S Message
     * @param glblCmpyCd global company code of the System
     */
    @SuppressWarnings("unchecked")
    public static void setScreenListData(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String glblCmpyCd) {

        // Header Tab ** Header Information
        // QC#1692
//        getScreenListData(glblCmpyCd, "ORD_LOG_TP", bizMsg.ordLogTpCd_CD, bizMsg.ordLogTpDescTxt_NM);
        // Addl Header Tab ** Freight Information
        getScreenListData(glblCmpyCd, "SPCL_HDLG_TP", bizMsg.spclHdlgTpCd_CD, bizMsg.spclHdlgTpDescTxt_NM);
        getScreenListData(glblCmpyCd, "SHPG_SVC_LVL", bizMsg.shpgSvcLvlCd_CD, bizMsg.shpgSvcLvlDescTxt_NM);
        // Addl Header Tab ** Prepayment
        getScreenListData(glblCmpyCd, "PRE_PMT_TP", bizMsg.prePmtTpCd_CD, bizMsg.prePmtTpDescTxt_NM);
        getScreenListData(glblCmpyCd, "DS_PMT_METH", bizMsg.dsPmtMethCd_CD, bizMsg.dsPmtMethDescTxt_NM);
        // Addl Header Tab ** Lease Details
        getScreenListData(glblCmpyCd, "LEASE_PRCH_OPT", bizMsg.leasePrchOptCd_CD, bizMsg.leasePrchOptDescTxt_NM);
        getScreenListData(glblCmpyCd, "LEASE_PMT_FREQ", bizMsg.leasePmtFreqCd_CD, bizMsg.leasePmtFreqDescTxt_NM);

        // 2018/08/21 S21_NA#26767 Del Start
//        // Line Config Tab ** Detail Fields
//        // getScreenListData(glblCmpyCd, "CONFIG_TP",
//        // bizMsg.configTpCd_CD, bizMsg.configTpDescTxt_NM); //
//        // S21_NA#955
//        createConfigTpPulldownList(bizMsg);
        // 2018/08/21 S21_NA#26767 Del End
        getSwhCdForProtected(bizMsg, glblCmpyCd); // 2018/04/12 S21_NA#23704 Add

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        S21SsmEZDResult ssmResult = NWAL1500QueryForInit.getInstance().getOrdLineSrcDataList(queryParam);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int n = 0; n < rsltList.size() && n < bizMsg.ordLineSrcCd_CD.length(); n++) {
                Map<String, Object> rsltMap = rsltList.get(n);
                ZYPEZDItemValueSetter.setValue(bizMsg.ordLineSrcCd_CD.no(n), (String) rsltMap.get("ORD_LINE_SRC_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ordLineSrcNm_NM.no(n), (String) rsltMap.get("ORD_LINE_SRC_NM"));
            }
        }

        // RMA Tab ** Detail Fields
        // 2018/08/21 S21_NA#26767 Del Start
//        getScreenListData(glblCmpyCd, "RTRN_RSN", bizMsg.rtrnRsnCd_CD, bizMsg.rtrnRsnDescTxt_NM);
        // 2018/08/21 S21_NA#26767 Del End
        getScreenListData(glblCmpyCd, "HDD_RMV", bizMsg.hddRmvCd_CD, bizMsg.hddRmvDescTxt_NM);

        // Create Action List Data
        createActionPulldownList(bizMsg, glblCmpyCd);
//        NWAL1500CommonLogic.copyActionPulldownHeader(bizMsg, glblMsg);
    }

    @SuppressWarnings("unchecked")
    private static void getScreenListData(String glblCmpyCd, String tblNm, EZDCStringItemArray cdArray, EZDCStringItemArray descArray) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("tblNm", tblNm);
        S21SsmEZDResult ssmResult = NWAL1500QueryForInit.getInstance().getCodeTableDataList(queryParam);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int n = 0; n < rsltList.size() && n < cdArray.length(); n++) {
                Map<String, Object> rsltMap = rsltList.get(n);
                ZYPEZDItemValueSetter.setValue(cdArray.no(n), (String) rsltMap.get(tblNm + "_CD"));
                ZYPEZDItemValueSetter.setValue(descArray.no(n), (String) rsltMap.get(tblNm + "_DESC_TXT"));
            }
        }
    }

    // 2018/08/21 S21_NA#26767 Del Start
//    @SuppressWarnings("unchecked")
//    private static void createConfigTpPulldownList(NWAL1500CMsg bizMsg) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        S21SsmEZDResult ssmResult = NWAL1500QueryForInit.getInstance().getConfigTpList(queryParam);
//        if (ssmResult.isCodeNormal()) {
//            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
//            int outboundIndex = 0;
//            int inboundIndex = 0;
//            for (int n = 0; n < rsltList.size(); n++) {
//                Map<String, Object> rsltMap = rsltList.get(n);
//                String configCatgCd = (String) rsltMap.get("CONFIG_CATG_CD");
//                if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
//                    if (outboundIndex < bizMsg.configTpCd_LD.length()) {
//                        ZYPEZDItemValueSetter.setValue(bizMsg.configTpCd_LD.no(outboundIndex), (String) rsltMap.get("CONFIG_TP_CD"));
//                        ZYPEZDItemValueSetter.setValue(bizMsg.configTpDescTxt_LD.no(outboundIndex), (String) rsltMap.get("CONFIG_TP_DESC_TXT"));
//                        outboundIndex++;
//                    }
//                } else {
//                    if (outboundIndex < bizMsg.configTpCd_RD.length()) {
//                        ZYPEZDItemValueSetter.setValue(bizMsg.configTpCd_RD.no(inboundIndex), (String) rsltMap.get("CONFIG_TP_CD"));
//                        ZYPEZDItemValueSetter.setValue(bizMsg.configTpDescTxt_RD.no(inboundIndex), (String) rsltMap.get("CONFIG_TP_DESC_TXT"));
//                        inboundIndex++;
//                    }
//                }
//            }
//        }
//    }
    // 2018/08/21 S21_NA#26767 Del End

    @SuppressWarnings("unchecked")
    private static void createActionPulldownList(NWAL1500CMsg bizMsg, String glblCmpyCd) {
        bizMsg.ordEntryActCd_HA.clear();
        bizMsg.ordEntryActDescTxt_HA.clear();
        bizMsg.ordEntryActCd_HI.clear();
        bizMsg.ordEntryActDescTxt_HI.clear();
        bizMsg.ordEntryActCd_HD.clear();
        bizMsg.ordEntryActDescTxt_HD.clear();
        bizMsg.ordEntryActCd_HT.clear();
        bizMsg.ordEntryActDescTxt_HT.clear();

        bizMsg.ordEntryActCd_LA.clear();
        bizMsg.ordEntryActDescTxt_LA.clear();
        // S21_NA#8043 add start
        bizMsg.ordEntryActCd_RA.clear();
        bizMsg.ordEntryActDescTxt_RA.clear();
        // S21_NA#8043 add start
        bizMsg.ordEntryActCd_LI.clear();
        bizMsg.ordEntryActDescTxt_LI.clear();
        bizMsg.ordEntryActCd_LD.clear();
        bizMsg.ordEntryActDescTxt_LD.clear();
        bizMsg.ordEntryActCd_LT.clear();
        bizMsg.ordEntryActDescTxt_LT.clear();

        S21SsmEZDResult ssmResult = null;
        // Add Start 2019/05/07 QC#50015
        boolean isLogistics = false;
        for (int i = 0; i < bizMsg.L.getValidCount(); i++) {
            if (bizMsg.L.getValidCount() == 1 && S21StringUtil.isEquals("NWAL1500T080", bizMsg.L.no(i).xxFuncId.getValue())) {
                isLogistics = true;
                break;
            }
        }
        // Add End 2019/05/07 QC#50015

        // Header Actions
        // Mod Start 2019/05/07 QC#50015
//        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, "HEADER ACTIONS");
        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, isLogistics, "HEADER ACTIONS");
        //// Mod End 2019/05/07 QC#50015
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < rsltList.size() && i < bizMsg.ordEntryActCd_HA.length(); i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_HA.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_HA.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_DESC_TXT"));
            }
        }
        // Header Addtional
        // Mod Start 2019/05/07 QC#50015
//        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, "HEADER ADDTIONAL");
        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, isLogistics, "HEADER ADDTIONAL");
        // Mod End 2019/05/07 QC#50015
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < rsltList.size() && i < bizMsg.ordEntryActCd_HI.length(); i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_HI.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_HI.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_DESC_TXT"));
            }
        }
        // Header DISC
        // Mod Start 2019/05/07 QC#50015
//        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, "HEADER DISC");
        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, isLogistics, "HEADER DISC");
        // Mod End 2019/05/07 QC#50015
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < rsltList.size() && i < bizMsg.ordEntryActCd_HD.length(); i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_HD.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_HD.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_DESC_TXT"));
            }
        }
        // Header Tools
        // Mod Start 2019/05/07 QC#50015
//        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, "HEADER TOOLS");
        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, isLogistics, "HEADER TOOLS");
        // Mod End 2019/05/07 QC#50015
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < rsltList.size() && i < bizMsg.ordEntryActCd_HT.length(); i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_HT.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_HT.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_DESC_TXT"));
            }
        }
        // CONFIG Line Actions
        // Mod Start 2019/05/07 QC#50015
//        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, "LINE ACTIONS", "LINE ACTIONS CONFIG"); // S21_NA#8043
        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, isLogistics, "LINE ACTIONS", "LINE ACTIONS CONFIG"); // S21_NA#8043
        // Mod End 2019/05/07 QC#50015
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < rsltList.size() && i < bizMsg.ordEntryActCd_LA.length(); i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_LA.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_LA.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_DESC_TXT"));
            }
        }
        // S21_NA#8043 add start
        // RMA Line Actions
        // Mod Start 2019/05/07 QC#50015
//        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, "LINE ACTIONS", "LINE ACTIONS RMA");
        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, isLogistics, "LINE ACTIONS", "LINE ACTIONS RMA");
        // Mod End 2019/05/07 QC#50015
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < rsltList.size() && i < bizMsg.ordEntryActCd_RA.length(); i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_RA.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_RA.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_DESC_TXT"));
            }
        }
        // S21_NA#8043 add end
        // Line Addtional
        // Mod Start 2019/05/07 QC#50015
//        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, "LINE ADDTIONAL");
        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, isLogistics, "LINE ADDTIONAL");
        // Mod End 2019/05/07 QC#50015
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < rsltList.size() && i < bizMsg.ordEntryActCd_LI.length(); i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_LI.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_LI.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_DESC_TXT"));
            }
        }
        // Line DISC
        // Mod Start 2019/05/07 QC#50015
//        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, "LINE DISC");
        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, isLogistics, "LINE DISC");
        // Mod End 2019/05/07 QC#50015
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < rsltList.size() && i < bizMsg.ordEntryActCd_LD.length(); i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_LD.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_LD.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_DESC_TXT"));
            }
        }
        // Line Tools
        // Mod Start 2019/05/07 QC#50015
//        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, "LINE TOOLS");
        ssmResult = NWAL1500QueryForInit.getInstance().getOrdEntryActList(glblCmpyCd, isLogistics, "LINE TOOLS");
        // Mod End 2019/05/07 QC#50015
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < rsltList.size() && i < bizMsg.ordEntryActCd_LT.length(); i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActCd_LT.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ordEntryActDescTxt_LT.no(i), (String) rsltMap.get("ORD_ENTRY_ACT_DESC_TXT"));
            }
        }
    }
    
    // 2018/04/12 S21_NA#23704 Add Start
    @SuppressWarnings("unchecked")
    private static void getSwhCdForProtected(NWAL1500CMsg bizMsg, String glblCmpyCd){
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        S21SsmEZDResult ssmResult = NWAL1500QueryForInit.getInstance().getSwhCdForProtected(queryParam);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int n = 0; n < rsltList.size() && n < bizMsg.rtlSwhCd_SW.length(); n++) {
                Map<String, Object> rsltMap = rsltList.get(n);
                ZYPEZDItemValueSetter.setValue(bizMsg.rtlSwhCd_SW.no(n), (String) rsltMap.get("RTL_SWH_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.rtlSwhDescTxt_SW.no(n), (String) rsltMap.get("RTL_SWH_DESC_TXT"));
            }
        }
    }
    // 2018/04/12 S21_NA#23704 Add End
}
