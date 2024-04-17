/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770.common;

import static business.blap.NWAL1770.constant.NWAL1770Constant.DEF_SLS_CR_PCT_WRITER;
import static business.blap.NWAL1770.constant.NWAL1770Constant.IDX_100;
import static business.blap.NWAL1770.constant.NWAL1770Constant.PCT_100;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0181E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0757W;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0981W;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.blap.NWAL1770.NWAL1770QueryForSalesCredit;
import business.blap.NWAL1770.NWAL1770_DCMsg;
import business.blap.NWAL1770.NWAL1770_TCMsg;
import business.blap.NWAL1770.NWAL1770_TCMsgArray;
import business.blap.NWAL1770.constant.NWAL1770Constant;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsgArray;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;
import business.parts.NMZC260001_defSlsRepListPMsgArray;

import com.canon.cusa.s21.api.NMZ.NMZC260001.NMZC260001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/16   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/09/21   Fujitsu         H.Ikeda         Update          S21_NA#14578
 * 2017/03/01   Fujitsu         Y.Kanefusa      Update          S21_NA#17637
 * 2017/03/14   Fujitsu         M.Ohno          Update          S21_NA#16855
 * 2017/06/30   Fujitsu         S.Takami        Update          S21_NA#18811
 * 2017/12/12   Fujitsu         A.Kosai         Update          Sol#349(QC#19804)
 * 2018/04/02   Fujitsu         K.Ishizuka      Update          S21_NA#24860
 * 2018/04/18   Fujitsu         K.Ishizuka      Update          S21_NA#25418
 * 2019/12/20   Fujitsu         A.Kazuki        Update          QC#53055
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 * </pre>
 */
public class NWAL1770CommonLogicForSalesCredit {

    /**
     * Derive Default Sales Rep Data
     * @param bizMsg Business Message
     * @return Succeed drive : true
     */
    public static boolean deriveDefaultSlsRep(NWAL1770CMsg bizMsg) {

        // S21_NA#16410 Del Start
        // if (ZYPCommonFunc.hasValue(bizMsg.slsRepTocCd)) {
        // return true;
        // }
        // S21_NA#16410 Del End

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

        // Call NMZC2600 Default Sales Rep API
        NMZC260001PMsg nMZC260001PMsg = new NMZC260001PMsg();
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.shipToCustCd, bizMsg.shipToCustCd);
        // 2018/04/02 S21_NA#24860 Add Start
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdCatgCd, bizMsg.splyQuoteCatgCd);
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

        List<NMZC260001_defSlsRepListPMsg> matchLobSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>();
        // 2017/12/12 Sol#349(QC#19804) Add Start
        List<NMZC260001_defSlsRepListPMsg> matchLobRoleSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>();
        // 2017/12/12 Sol#349(QC#19804) Add End

        NMZC260001_defSlsRepListPMsgArray defSlsRepMsgArray = nMZC260001PMsg.defSlsRepList;
        // 2017/09/01 S21_NA#16855-2 Del Start
//        // 2017/06/30 S21_NA#18811 Add Start
//        List<String> psnCdList = new ArrayList<String>(0);
//        // 2017/06/30 S21_NA#18811 Add End
        // 2017/09/01 S21_NA#16855-2 Del End

        for (int i = 0; i < defSlsRepMsgArray.getValidCount(); i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = defSlsRepMsgArray.no(i);
            // 2017/09/01 S21_NA#16855-2 Del Start
//            // 2017/06/30 S21_NA#18811 Add Start
//            if (ZYPCommonFunc.hasValue(defSlsRepPMsg.psnCd)) {
//                if (psnCdList.contains(defSlsRepPMsg.psnCd.getValue())) {
//                    continue;
//                } else {
//                    psnCdList.add(defSlsRepPMsg.psnCd.getValue());
//                }
//            }
//            // 2017/06/30 S21_NA#18811 Add End
            // 2017/09/01 S21_NA#16855-2 Del Start
            
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
//                  && (!ZYPCommonFunc.hasValue(trtyGrpTpCd) //
//                  || trtyGrpTpCd.equals(defSlsRepPMsg.trtyGrpTpCd.getValue()))) {
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
//                	getSlsRepInfo(bizMsg, defSlsRepPMsg.tocCd.getValue());
                    matchLobRoleSlsRepPMsgList.add(defSlsRepPMsg);
                    // 2017/12/12 Sol#349(QC#19804) Mod End
                }
            }
        }


        // 2017/12/12 Sol#349(QC#19804) Mod Start
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
        // 2017/12/12 Sol#349(QC#19804) Mod End

        if (matchLobSlsRepPMsgList.size() > 0) {
        	setNewSlsRep(bizMsg, matchLobSlsRepPMsgList, targetWriterList); // 2017/03/14 S21_NA#16855 Mod
        }

        return true;
    }

    /**
     * Call NMZC2600 Default Sales Rep API
     * @param bizMsg NWAL1770CMsg
     * @param pMsg NMZC260001PMsg
     * @return has API error : false
     */
    private static boolean callDefSlsRepApi(NWAL1770CMsg bizMsg, NMZC260001PMsg pMsg) {

        // Call NMZC2600 Dafault Sales Rep API
        new NMZC260001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();

                if (msgId.endsWith("E")) {
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);
                    return false;
                // 2020/04/27 QC#56638 Add Start
                } else if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd) && msgId.endsWith(NWAM0757W)) {
                    bizMsg.setMessageInfo(msgId);
                    return false;
                } else if (ZYPCommonFunc.hasValue(pMsg.billToCustCd) && msgId.endsWith(NWAM0981W)) {
                    bizMsg.setMessageInfo(msgId);
                    return false;
                }
                boolean isShipBase = isSlsReqDef(bizMsg);
                if (isShipBase && ZYPCommonFunc.hasValue(bizMsg.shipToCustCd) && msgId.endsWith("W")) {
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
     * @param bizMsg NWAL1770CMsg
     * @param slsRepTocCd Sales Rep TOC Code
     */
    @SuppressWarnings("unchecked")
    public static void getSlsRepInfo(NWAL1770CMsg bizMsg, String slsRepTocCd) {

        S21SsmEZDResult ssmResult = NWAL1770QueryForSalesCredit.getInstance().getSlsRepInfo(bizMsg, slsRepTocCd);

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
     * @param bizMsg NWAL1770CMsg
     */
    private static void delExistingSlsRep(NWAL1770CMsg bizMsg) {

        // Delete Header
        List<Integer> deleteRows = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            NWAL1770_DCMsg slsRepMsg = bizMsg.D.no(i);

            if (ZYPCommonFunc.hasValue(slsRepMsg.splyQuoteSlsCrPk_D)) {
                ZYPEZDItemValueSetter.setValue(slsRepMsg.xxRqstTpCd_D, NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
            } else {
                deleteRows.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.D, deleteRows);
    }

    /**
     * Set New Sales Rep Info
     * @param bizMsg NWAL1770CMsg
     * @param slsRepPMsgList List<NMZC260001_defSlsRepListPMsg>
     */
//    private static void setNewSlsRep(NWAL1770CMsg bizMsg, List<NMZC260001_defSlsRepListPMsg> slsRepPMsgList) {
    private static void setNewSlsRep(NWAL1770CMsg bizMsg, List<NMZC260001_defSlsRepListPMsg> slsRepPMsgList,  List<String> targetWriterList) {

        // Set Header
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
        int vldCnt = bizMsg.D.getValidCount();
        for (int i = 0; i < slsRepPMsgList.size(); i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = slsRepPMsgList.get(i);

            NWAL1770_DCMsg slsRepMsg = bizMsg.D.no(vldCnt);
            ZYPEZDItemValueSetter.setValue(slsRepMsg.xxRqstTpCd_D, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepTocCd_D, defSlsRepPMsg.tocCd);
            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_D, defSlsRepPMsg.lineBizRoleTpCd);
            // Add Start 2019/12/20 QC#53055 ISW check
            // When LINE_BIZ_TP = LFS and TRTY_GRP_TP_TXT = IS, Set "IS Writer".
            DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
            tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);
            if (tMsg != null && NWAL1770Constant.TRTY_GRP_TP_IS.equals(tMsg.trtyGrpTpTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_D, LINE_BIZ_ROLE_TP.IS_WRITER);
            }
            // Add End   2019/12/20 QC#53055
            ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_D, defSlsRepPMsg.lineBizRoleTpCd);

            // QC#17727 2017/03/01 Mod Start
            // if (i == 0) {
            //     ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepCrPct_D, new BigDecimal(IDX_100));
            // } else {
            //     ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepCrPct_D, BigDecimal.ZERO);
            // }
            if (S21StringUtil.isEquals("1", mode)) {
                ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepCrPct_D, new BigDecimal(IDX_100));
            } else if (S21StringUtil.isEquals("2", mode)) {
                // 2017/03/14 S21_NA#16855 Mod Start
//                if (isWriter(defSlsRepPMsg.lineBizRoleTpCd.getValue())) {
                if (targetWriterList.contains(defSlsRepPMsg.lineBizRoleTpCd.getValue())) {
                // 2017/03/14 S21_NA#16855 Mod End
                    ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepCrPct_D, writerPct);
                } else {
                    ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepCrPct_D, installerPct);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepCrPct_D, BigDecimal.ZERO);
            }
            // QC#17727 2017/03/01 Mod End
            // Mod Start 2016/09/21 S21_NA#14578
            //ZYPEZDItemValueSetter.setValue(slsRepMsg.slsCrQuotFlg_D, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsCrQuotFlg_D, ZYPConstant.FLG_ON_Y);
            // Mod End 2016/09/21 S21_NA#14578
            vldCnt++;

        }
        bizMsg.D.setValidCount(vldCnt);
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
     * Copy Sales Credit From Popup
     * @param bizMsg NWAL1770CMsg
     */
    public static void copySlsCrFromPopup(NWAL1770CMsg bizMsg) {

        int hdrCnt = 0;
        List<NWAL1770_DCMsg> slsCreditList = new ArrayList<NWAL1770_DCMsg>();

        for (; hdrCnt < bizMsg.D.getValidCount(); hdrCnt++) {
            NWAL1770_DCMsg slsCreditMsg = new NWAL1770_DCMsg();
            EZDMsg.copy(bizMsg.D.no(hdrCnt), "D", slsCreditMsg, "D");
            slsCreditList.add(slsCreditMsg);
        }

        boolean isSetDefSlsRep = false;
        List<NWAL1770_DCMsg> hdrSlsCrList = new ArrayList<NWAL1770_DCMsg>(0);

        for (int i = 0; i < bizMsg.T.getValidCount(); i++) {
            NWAL1770_TCMsg paramSlsCreditMsg = bizMsg.T.no(i);
            String lineBizRoleTpCd = paramSlsCreditMsg.lineBizRoleTpCd_T.getValue();
            String slsRepTocCd = paramSlsCreditMsg.slsRepTocCd_T.getValue();

            if (!isSetDefSlsRep) {
                if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTpCd)) {
                    getSlsRepInfo(bizMsg, slsRepTocCd);
                    isSetDefSlsRep = true;
                }
            }

            boolean srchedFlg = false;

            for (NWAL1770_DCMsg currHdrSlsCr : slsCreditList) {
                // Add Start 2016/09/21 S21_NA#14578
                if (NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(currHdrSlsCr.xxRqstTpCd_D.getValue())) {
                    continue;
                }
                // Add End 2016/09/21 S21_NA#14578
                String hdrSlsRepTocCd = currHdrSlsCr.slsRepTocCd_D.getValue();
                String hdrSlsRepRoleTpCd = currHdrSlsCr.slsRepRoleTpCd_D.getValue();

                if (slsRepTocCd.equals(hdrSlsRepTocCd) && lineBizRoleTpCd.equals(hdrSlsRepRoleTpCd)) {
                    NWAL1770_DCMsg hdrSlsCr = new NWAL1770_DCMsg();
                    EZDMsg.copy(currHdrSlsCr, "D", hdrSlsCr, "D");

                    if (!NWAL1770CommonLogic.isEqualsEZDItem(hdrSlsCr.splyQuoteSlsCrPk_D, paramSlsCreditMsg.dsCpoSlsCrPk_T)) {
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.splyQuoteSlsCrPk_D, paramSlsCreditMsg.dsCpoSlsCrPk_T);
                    }

                    if (!NWAL1770CommonLogic.isEqualsEZDItem(hdrSlsCr.slsRepTocCd_D, paramSlsCreditMsg.slsRepTocCd_T)) {
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.slsRepTocCd_D, paramSlsCreditMsg.slsRepTocCd_T);
                    }

                    if (!NWAL1770CommonLogic.isEqualsEZDItem(hdrSlsCr.slsRepRoleTpCd_D, paramSlsCreditMsg.lineBizRoleTpCd_T)) {
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.slsRepRoleTpCd_D, paramSlsCreditMsg.lineBizRoleTpCd_T);
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.lineBizRoleTpCd_D, paramSlsCreditMsg.lineBizRoleTpCd_T);
                    }

                    if (!NWAL1770CommonLogic.isEqualsEZDItem(hdrSlsCr.slsRepCrPct_D, paramSlsCreditMsg.slsRepCrPct_T)) {
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.slsRepCrPct_D, paramSlsCreditMsg.slsRepCrPct_T);
                    }

                    if (!NWAL1770CommonLogic.isEqualsEZDItem(hdrSlsCr.slsCrQuotFlg_D, paramSlsCreditMsg.slsCrQuotFlg_T)) {
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.slsCrQuotFlg_D, bizMsg.T.no(i).slsCrQuotFlg_T);
                    }

                    if (!NWAL1770CommonLogic.isEqualsEZDItem(hdrSlsCr.xxRqstTpCd_D, paramSlsCreditMsg.xxRqstTpCd_T)) {
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.xxRqstTpCd_D, paramSlsCreditMsg.xxRqstTpCd_T);
                    }

                    hdrSlsCrList.add(hdrSlsCr);
                    srchedFlg = true;
                }
            }

            if (!srchedFlg) {
                NWAL1770_DCMsg hdrSlsCr = new NWAL1770_DCMsg();
                EZDMsg.copy(paramSlsCreditMsg, "T", hdrSlsCr, "D");
                ZYPEZDItemValueSetter.setValue(hdrSlsCr.splyQuoteSlsCrPk_D, paramSlsCreditMsg.dsCpoSlsCrPk_T);
                ZYPEZDItemValueSetter.setValue(hdrSlsCr.slsRepRoleTpCd_D, paramSlsCreditMsg.lineBizRoleTpCd_T);
                hdrSlsCrList.add(hdrSlsCr);
            }
        }

        for (NWAL1770_DCMsg curSlsCredit : slsCreditList) {
            // Add Start 2016/09/21 S21_NA#14578
            if (NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(curSlsCredit.xxRqstTpCd_D.getValue())) {
                NWAL1770_DCMsg hdrSlsCr = new NWAL1770_DCMsg();
                EZDMsg.copy(curSlsCredit, "D", hdrSlsCr, "D");
                hdrSlsCrList.add(hdrSlsCr);
                continue;
            }
            // Add End 2016/09/21 S21_NA#14578
            String hdrSlsRepTocCd = curSlsCredit.slsRepTocCd_D.getValue();
            String hdrSlsRepRoleTpCd = curSlsCredit.slsRepRoleTpCd_D.getValue();

            boolean srched = false;
            for (int i = 0; i < bizMsg.T.getValidCount(); i++) {
                NWAL1770_TCMsg paramSlsCreditMsg = bizMsg.T.no(i);
                String slsRepTocCd = paramSlsCreditMsg.slsRepTocCd_T.getValue();
                String lineBizRoleTpCd = paramSlsCreditMsg.lineBizRoleTpCd_T.getValue();
                if (hdrSlsRepTocCd.equals(slsRepTocCd) && hdrSlsRepRoleTpCd.equals(lineBizRoleTpCd)) {
                    srched = true;
                    break;
                }
            }

            if (!srched && ZYPCommonFunc.hasValue(curSlsCredit.splyQuoteSlsCrPk_D)) {
                NWAL1770_DCMsg slsCreditMsg = new NWAL1770_DCMsg();
                EZDMsg.copy(curSlsCredit, "D", slsCreditMsg, "D");
                ZYPEZDItemValueSetter.setValue(slsCreditMsg.xxRqstTpCd_D, NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
                hdrSlsCrList.add(slsCreditMsg);
            }
        }

        hdrCnt = 0;
        ZYPTableUtil.clear(bizMsg.D);

        for (NWAL1770_DCMsg hdrSlsCr : hdrSlsCrList) {
            EZDMsg.copy(hdrSlsCr, "D", bizMsg.D.no(hdrCnt), "D");
            hdrCnt++;
        }
        bizMsg.D.setValidCount(hdrCnt);
    }

    /**
     * Delete All Sales Credit Information
     * @param bizMsg NWAL1770CMsg
     */
    public static void delAllSlsCreditInfo(NWAL1770CMsg bizMsg) {

        List<Integer> deleteList = new ArrayList<Integer>();

        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            NWAL1770_DCMsg slsCreditMsg = bizMsg.D.no(i);

            if (ZYPCommonFunc.hasValue(slsCreditMsg.splyQuoteSlsCrPk_D)) {
                ZYPEZDItemValueSetter.setValue(slsCreditMsg.xxRqstTpCd_D, NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
            } else {
                deleteList.add(i);
            }
        }

        ZYPTableUtil.deleteRows(bizMsg.D, deleteList);
    }

    /**
     * Set Writer Sales Credit Information
     * @param bizMsg NWAL1770CMsg
     * @param writerSlsRepTocCd Write Sales Rep Toc Code
     */
    public static void setWriterSlsCreditInfo(NWAL1770CMsg bizMsg, String writerSlsRepTocCd) {

        String lineBizTpCd = bizMsg.lineBizTpCd.getValue();
        String slsRepRoleTpCd = null;
        if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.ESS_WRITER;
        } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.LFS_WRITER;
            // Add Start 2019/12/20 QC#53055 ISW check
            // When LINE_BIZ_TP = LFS and TRTY_GRP_TP_TXT = IS, Set "IS Writer".
            DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
            tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);
            if (tMsg != null && NWAL1770Constant.TRTY_GRP_TP_IS.equals(tMsg.trtyGrpTpTxt.getValue())) {
                slsRepRoleTpCd = LINE_BIZ_ROLE_TP.IS_WRITER;
            }
            // Add End 2019/12/20 QC#53055
        } else {
            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.PPS_WRITER;
        }

        NWAL1770_DCMsg slsCreditMsg = bizMsg.D.no(bizMsg.D.getValidCount());
        ZYPEZDItemValueSetter.setValue(slsCreditMsg.slsRepTocCd_D, writerSlsRepTocCd);
        ZYPEZDItemValueSetter.setValue(slsCreditMsg.xxRqstTpCd_D, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
        ZYPEZDItemValueSetter.setValue(slsCreditMsg.slsRepRoleTpCd_D, slsRepRoleTpCd);
        ZYPEZDItemValueSetter.setValue(slsCreditMsg.slsRepCrPct_D, PCT_100);
        ZYPEZDItemValueSetter.setValue(slsCreditMsg.slsCrQuotFlg_D, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(slsCreditMsg.lineBizRoleTpCd_D, slsRepRoleTpCd);
        bizMsg.D.setValidCount(bizMsg.D.getValidCount() + 1);
    }

    /**
     * Get Sales Rep Code
     * @param bizMsg NWAL1770CMsg
     * @param isCallName Call Name Field
     * @param msgParm Message Parameter
     * @return Sales Rep Code
     */
    @SuppressWarnings("unchecked")
    public static String getSlsRepCd(NWAL1770CMsg bizMsg, boolean isCallName, String msgParm) {

        S21SsmEZDResult ssmResult = NWAL1770QueryForSalesCredit.getInstance().getSlsRepInfoList(bizMsg, isCallName);

        if (ssmResult.isCodeNotFound()) {
            if (isCallName) {
                bizMsg.slsRepTocCd.clear();
                bizMsg.slsRepTocNm.setErrorInfo(1, NWAM0181E, new String[] {msgParm });
            } else {
                bizMsg.slsRepTocCd.clear();
                bizMsg.psnNum.setErrorInfo(1, NWAM0181E, new String[] {msgParm }); // S21_NA#7861 Mod
            }

            return null;
        }

        List<Map<String, String>> slsRepInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (slsRepInfoList.size() != 1) {
            if (isCallName) {
                bizMsg.psnNum.clear(); // S21_NA#7861 Mod
                bizMsg.slsRepTocCd.clear();
            } else {
                bizMsg.slsRepTocNm.clear();
                bizMsg.slsRepTocCd.clear();
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        Map<String, String> slsRepInfo = slsRepInfoList.get(0);
        String slsRepTocCd = slsRepInfo.get("TOC_CD");
        ZYPEZDItemValueSetter.setValue(bizMsg.psnNum, slsRepInfo.get("PSN_NUM")); // S21_NA#7861 Mod
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocCd, slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocNm, slsRepInfo.get("TOC_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd, slsRepInfo.get("COA_BR_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrDescTxt, slsRepInfo.get("COA_BR_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd, slsRepInfo.get("COA_EXTN_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnDescTxt, slsRepInfo.get("COA_EXTN_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CB, slsRepInfo.get("COA_BR_ITEM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CE, slsRepInfo.get("COA_EXTN_ITEM"));

        return slsRepTocCd;
    }

    /**
     * Check Sales Rep Deleted
     * @param slsCrParamMsgArray NWAL1770_TCMsgArray
     * @return All Deleted : true
     */
    public static boolean isParamDtAllDeleted(NWAL1770_TCMsgArray slsCrParamMsgArray) {

        for (int i = 0; i < slsCrParamMsgArray.getValidCount(); i++) {
            String xxRqstTpCd = slsCrParamMsgArray.no(i).xxRqstTpCd_T.getValue();
            if (!NWZC152001Constant.MODE_DEL.equals(xxRqstTpCd)) {
                return false;
            }
        }

        return true;
    }

    // 2017/03/14 S21_NA#16855 Add Start
    /**
     * <pre>
     * Get Territory Group Type Code From DS Order Type Code
     * @param bizMsg
     * </pre>
     */
    // 2017/12/12 Sol#349(QC#19804) Mod Start
//    public static String getTrtyGrpTpCdFromDsOrdTpCd(NWAL1770CMsg bizMsg) {
    public static String getTrtyGrpTpTxtFromDsOrdTpCd(NWAL1770CMsg bizMsg) {
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
//            return dsOrdTpProcDfnTMsg.trtyGrpTpCd.getValue();
            return dsOrdTpProcDfnTMsg.trtyGrpTpTxt.getValue();
            // 2017/12/12 Sol#349(QC#19804) Mod End
        }

        return null;
    }
    // 2017/03/14 S21_NA#16855 Add End

    /**
     * 2020/04/27 QC#56638 Add
     * Get Sales Req Defaulting
     * @param bizMsg NWAL1770CMsg
     * @return Boolean
     */
    public static boolean isSlsReqDef(NWAL1770CMsg bizMsg) {

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
}
