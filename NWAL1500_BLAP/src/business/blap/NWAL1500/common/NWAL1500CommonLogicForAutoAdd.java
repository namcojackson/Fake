/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.DROP_SHIP_RTL_WH_CD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1500_RENTAL_CONV_DEFLNCATG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1500_RENTAL_CONV_DEFLNSRC;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1500_RENTAL_CONV_DEFWH;
import static business.blap.NWAL1500.constant.NWAL1500Constant.SERVICE_EXCHANGE_RTRN_RSN_CD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0100E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500QueryForAutoAdd;
import business.blap.NWAL1500.NWAL1500QueryForLineConfig;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ACMsg;
import business.blap.NWAL1500.NWAL1500_ASMsg;
import business.blap.NWAL1500.NWAL1500_BCMsg;
import business.blap.NWAL1500.NWAL1500_BSMsg;
import business.blap.NWAL1500.NWAL1500_BSMsgArray;
import business.blap.NWAL1500.NWAL1500_CCMsg;
import business.blap.NWAL1500.NWAL1500_CSMsg;
import business.blap.NWAL1500.NWAL1500_CSMsgArray;
import business.blap.NWAL1500.NWAL1500_DCMsg;
import business.blap.NWAL1500.NWAL1500_DSMsg;
import business.blap.NWAL1500.NWAL1500_DSMsgArray;
import business.blap.NWAL1500.NWAL1500_RCMsg;
import business.blap.NWAL1500.constant.NWAL1500MsgConstant;
import business.db.CPO_SRC_TPTMsg;
import business.db.MDL_NMTMsg;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.RTL_SWHTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.parts.NWZC180001PMsg;
import business.parts.NWZC181001PMsg;
import business.parts.NWZC181002PMsg;
import business.parts.NWZC181002PMsgArray;
import business.parts.NWZC182001PMsg;
import business.parts.NWZC182002PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC181001.NWZC181001;
import com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC182001.NWZC182001;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/13   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/08   Fujitsu         Y.Taoka         Update          S21_NA#1867
 * 2016/02/08   Fujitsu         S.Takami        Update          S21_NA#3398
 * 2016/03/05   Fujitsu         S.Takami        Update          S21_NA#5000#67
 * 2016/04/15   Fujitsu         S.Takami        Update          S21_NA#5321
 * 2016/04/22   Fujitsu         S.Takami        Update          S21_NA#7449
 * 2016/04/25   Fujitsu         Y.Kanefusa      Update          S21_NA#7505
 * 2016/05/19   Fujitsu         T.Murai         Update          S21_NA#5350
 * 2016/05/19   Fujitsu         M.Hara          Update          S21_NA#8409
 * 2016/05/26   Fujitsu         T.Murai         Update          S21_NA#2334
 * 2016/05/31   Fujitsu         T.Murai         Update          S21_NA#9104
 * 2016/06/02   Fujitsu         S.Yamamoto      Update          S21_NA#9277
 * 2016/06/07   Fujitsu         T.Ishii         Update          S21_NA#5340
 * 2016/06/07   Fujitsu         T.Ishii         Update          S21_NA#9191
 * 2016/06/16   Fujitsu         S.Takami        Update          S21_NA#9848
 * 2016/06/20   Fujitsu         H.Nagashima     Update          S21_NA#9849
 * 2016/06/21   Fujitsu         S.Takami        Update          S21_NA#9849-2
 * 2016/06/27   Fujitsu         S.Takami        Update          S21_NA#10841
 * 2016/07/11   Fujitsu         S.Takami        Update          S21_NA#7821
 * 2016/07/21   Fujitsu         M.Hara          Update          S21_NA#11838
 * 2016/10/05   Fujitsu         S.Iidaka        Update          S21_NA#11434
 * 2016/10/05   Fujitsu         S.Iidaka        Update          S21_NA#10363
 * 2016/11/04   Fujitsu         S.Takami        Update          S21_NA#15703
 * 2016/11/10   Fujitsu         S.Iidaka        Update          S21_NA#10363-2
 * 2016/11/14   Fujitsu         S.Iidaka        Update          S21_NA#11434-2
 * 2016/11/24   Fujitsu         S.Iidaka        Update          S21_NA#15962
 * 2017/01/27   Fujitsu         R.Nakamura      Update          S21_NA#17257
 * 2017/01/30   Fujitsu         R.Nakamura      Update          S21_NA#17186
 * 2017/03/02   Fujitsu         N.Aoyama        Update          S21_NA#16575
 * 2017/09/11   Fujitsu         W.Honda         Update          S21_NA#21014
 * 2017/10/05   Fujitsu         R.Nakamura      Update          S21_NA#21356
 * 2017/10/19   Fujitsu         A.Kosai         Update          S21_NA#21843
 * 2017/11/21   Fujitsu         A.Kosai         Update          S21_NA#22555
 * 2018/01/04   Fujitsu         K.Ishizuka      Update          S21_NA#21503
 * 2018/01/31   Fujitsu         T.Aoi           Update          S21_NA#23416
 * 2018/01/31   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/03/19   Fujitsu         K.Ishizuka      Update          S21_NA#24582
 * 2018/05/17   Fujitsu         S.Takami        Update          S21_NA#22988
 * 2018/06/07   Fujitsu         S.Takami        Update          S21_NA#22988-2
 * 2018/07/05   Fujitsu         H.Kumagai       Update          QC#26935
 * 2018/08/02   Fujitsu         M.Ohno          Update          S21_NA#26665
 * 2018/08/07   Fujitsu         M.Ohno          Update          S21_NA#26414-1
 * 2018/08/21   Fujitsu         S.Takami        Update          S21_NA#26767
 * 2018/09/14   Fujitsu         S.Takami        Update          S21_NA#28225
 * 2018/09/20   Fujitsu         S.Takami        Update          S21_NA#28199
 * 2018/09/26   Fujitsu         K.Ishizuka      Update          S21_NA#28482
 * 2018/10/16   Fujitsu         Y.Kanefusa      Update          S21_NA#28772
 * 2019/05/17   Fujitsu         M.Ohno          Update          S21_NA#50328
 * 2019/07/23   Fujitsu         T.Noguchi       Update          S21_NA#51743
 * 2019/09/24   Fujitsu         C.Hara          Update          S21_NA#53592
 * 2019/10/15   Fujitsu         C.Hara          Update          S21_NA#53592-1
 * 2019/11/15   Fujitsu         R.Nakamura      Update          S21_NA#54515
 * 2020/01/22   Fujitsu         Y.Kanefusa      Update          S21_NA#55546
 * </pre>
 */
public class NWAL1500CommonLogicForAutoAdd {

    /** For Debug Logout */
    private static final boolean PRINT_LOG = false;

    /**
     * Call NWZC1810 Auto Add Supply API
     * @param bizMsg NWAL1500CMsg
     * @param confMsg NWAL1500_ACMsg
     * @return NWZC181001PMsg
     */
    public static NWZC181001PMsg callAutoAddSplyApi(NWAL1500CMsg bizMsg, NWAL1500_ASMsg confMsg) { // 2018/01/25 S21_NA#19808

        NWZC181001PMsg pMsg = new NWZC181001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NWZC181001Constant.PROC_MD_ODR_ENT);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.mdlId, confMsg.mdlId_LC);
        ZYPEZDItemValueSetter.setValue(pMsg.dsCpoConfigPk, confMsg.dsCpoConfigPk_LC);

        // call NWZC181001 Auto Add Supply API
        new NWZC181001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            if (msgList.size() > 0) {
                bizMsg.setMessageInfo(msgList.get(0).getXxMsgid());
                return null;
            }
        }

        return pMsg;
    }

    /**
     * Derive Supply Item
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     * @param confMsg NWAL1500_ACMsg
     * @param configIdx Config Index
     * @param splyItemList NWZC181002PMsgArray
     */
    public static void deriveSplyItem(NWAL1500CMsg bizMsg //
            , NWAL1500SMsg glblMsg //
            , NWAL1500_ASMsg confMsg // 2018/01/25 S21_NA#19808
            , int configIdx //
            , NWZC181002PMsgArray splyItemList) {

        NWAL1500_BSMsgArray lineList = glblMsg.B; // 2018/01/31 S21_NA#19808 Mod

        NWAL1500CMsg cloneCMsg = (NWAL1500CMsg) bizMsg.clone();
        NWAL1500SMsg cloneSMsg = (NWAL1500SMsg) glblMsg.clone(); // 2018/01/25 S21_NA#19808

        String dsOrdPosnNum = confMsg.dsOrdPosnNum_LC.getValue();
        int lineStartIndex = NWAL1500CommonLogicForPagination.getAddLineRowForGlobal(lineList, dsOrdPosnNum); // 2018/01/31 S21_NA#19808 Mod
        int maxLineNum = NWAL1500CommonLogicForPagination.getMaxLineNumForGlobal(lineList, dsOrdPosnNum); // 2018/01/31 S21_NA#19808 Mod
        int insertRow = lineStartIndex;
        int lineCount = 0;
        int subLineNum = 0;
        List<String> skipList = new ArrayList<String>(); // QC#23192 2018/02/09 Add

        // 2018/01/31 S21_NA#19808 Add Start
//        int glblLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(line, glblMsg);
//        insertRow = glblLineIndex + 1;
        // 2018/01/31 S21_NA#19808 Add End

        //        NWAL1500CommonLogicForLineControl.storeLineByConfig(lineAllList, lineList, dsOrdPosnNum); // S21_NA#1670

        // S21_NA#8409
        NWAL1500_BSMsg baseCmpntMsg = deriveBaseCmpntMsg(lineList, dsOrdPosnNum); // 2018/01/31 S21_NA#19808 Mod
        // QC#24008 2018/02/07 Add Start
        if (baseCmpntMsg == null) {
            return;
        }
        // QC#24008 2018/02/07 Add End

        boolean addLineFlg = false;
        List<Integer> addSapplyLineList = new ArrayList<Integer>(0); // 2016/03/05 S21_NA#5000#67
        for (int i = 0; i < splyItemList.getValidCount(); i++) {
            NWZC181002PMsg splyItemInfo = splyItemList.no(i);

            // Mod Start 2017/10/05 QC#21356
//            if (isExistSameSplyItem(lineList, dsOrdPosnNum, splyItemInfo)) {
            if (isExistSameSplyItem(lineList, dsOrdPosnNum, splyItemInfo, bizMsg.glblCmpyCd.getValue())) {
                skipList.add(splyItemInfo.mdseCd.getValue()); // QC#23192 2018/02/09 Add
                continue;
            }
            // Mod End 2017/10/05 QC#21356

            NWAL1500_BSMsg newLine = (NWAL1500_BSMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineList, insertRow++); // 2018/01/31 S21_NA#19808 Mod
            if (newLine == null) {
                // full line
                EZDMsg.copy(cloneCMsg.B, null, glblMsg.B, null);
                EZDMsg.copy(cloneSMsg.B, null, glblMsg.B, null);
                bizMsg.setMessageInfo(NWAM0100E);
                return;
            }
            // QC#23192 2018/02/09 Mod Start
            //lineCount++;
            if (ZYPCommonFunc.hasValue(splyItemInfo.prntMdseCd)) {
                subLineNum++;
            } else {
                addSapplyLineList.add(new Integer(insertRow - 1));
                lineCount++;
                subLineNum = 0;
            }
            // QC#23192 2018/02/09 Mod End
            // S21_NA#8409
            // set up line
            if (!setItemInfoForSply(newLine, bizMsg, glblMsg, confMsg, splyItemInfo, maxLineNum + lineCount, subLineNum, baseCmpntMsg)) { // 2018/09/20 S21_NA#28199 Add glblMsg
                EZDMsg.copy(cloneCMsg.B, null, glblMsg.B, null);
                return;
            }
            // QC#23192 2018/02/09 Del Start
            //addSapplyLineList.add(new Integer(insertRow - 1)); // 2016/03/05 S21_NA#5000#67
            // QC#23192 2018/02/09 Del End
            addLineFlg = true;
        }

        if (!addLineFlg) {
            // 2016/09/11 S21_NA#21014 Del Start
//            EZDMsg.copy(cloneCMsg.B, null, glblMsg.B, null);
            // 2016/09/11 S21_NA#21014 Del End
            return;
        }

        // 2018/01/31 S21_NA#19808 Mod Start
//        ZYPEZDItemValueSetter.setValue(confMsg.xxSmryLineFlg_L, ZYPConstant.FLG_OFF_N);
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, confMsg.xxSmryLineFlg_L.getValue())) {
            confMsg.xxSmryLineFlg_L.clear();

            for (int lineIdx = 0; lineIdx < glblMsg.B.getValidCount(); lineIdx++) {
                if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(lineIdx).dsOrdPosnNum_LL.getValue())) {
                    glblMsg.B.no(lineIdx).xxSmryLineFlg_LL.clear();
                }
            }
            ZYPEZDItemValueSetter.setValue(confMsg.xxSmryLineFlg_L, ZYPConstant.FLG_OFF_N);
        }
        // 2018/01/31 S21_NA#19808 Mod End
//        NWAL1500CommonLogicForConfigId.prepareLineS2C(lineList, lineAllList, dsOrdPosnNum, lineStartIndex);

        // 2016/03/05 S21_NA#5000#67 Mod Start
        // set Line Price
//        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
//            if (dsOrdPosnNum.equals(bizMsg.B.no(i).dsOrdPosnNum_LL.getValue())) {
//                NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, bizMsg.xxCellIdx.getValueInt(), i);
//            }
//        }
        for (Integer splyIdx : addSapplyLineList) {
            int i = splyIdx.intValue();
            // 2016/04/25 S21_NA#7505 Mod Start
            //NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, bizMsg.xxCellIdx.getValueInt(), i);
            NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, configIdx, i);
            // 2016/04/25 S21_NA#7505 Mod End
            // QC#22372 2018/01/10 Add Start
            NWAL1500CommonLogicForLineConfig.deriveLineFloorPrice(bizMsg, glblMsg, configIdx, i);
            // QC#22372 2018/01/10 Add End
        }
        // 2016/03/05 S21_NA#5000#67 Mod End
    }

    /**
     * Set Item Infomation For Supply
     * @param newLine NWAL1500_JSMsg
     * @param bizMsg NWAL1500CMsg
     * @param confMsg NWAL1500_ACMsg
     * @param splyItemInfo Supply Item Info
     * @param lineNum Line Number
     * @param baseCmpntMsg base component flag Msg (// S21_NA#8409)
     * @return exist API error : false
     */
    @SuppressWarnings("unchecked")
    private static boolean setItemInfoForSply(NWAL1500_BSMsg newLineP, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg confMsg, NWZC181002PMsg splyItemInfo, int lineNum, int subLineNum, NWAL1500_BSMsg baseCmpntMsg) { // 2018/01/31 S21_NA#19808 Mod 2018/09/20 S21_NA#28199 Add glblMsg

        // 2018/01/31 S21_NA#19808 Add Start
        NWAL1500_BCMsg newLine = new NWAL1500_BCMsg();
        // 2018/01/31 S21_NA#19808 Add End
        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), splyItemInfo.mdseCd.getValue());

        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getMdseInfo(bizMsg, mdseTMsg.mdseCd.getValue(), true);
        Map<String, String> mdseInfo = new HashMap<String, String>();
        if (!ssmResult.isCodeNotFound()) {
            mdseInfo = (Map<String, String>) ssmResult.getResultObject();
        }

        // Create Line
        ZYPEZDItemValueSetter.setValue(newLine.dsOrdPosnNum_LL, confMsg.dsOrdPosnNum_LC.getValue());
        ZYPEZDItemValueSetter.setValue(newLine.dsCpoLineNum_LL, new BigDecimal(lineNum));
        // QC#23192 2018/02/09 Add Start
        if (subLineNum == 0) {
            newLine.dsCpoLineSubNum_LL.clear();
        } else {
            ZYPEZDItemValueSetter.setValue(newLine.dsCpoLineSubNum_LL, new BigDecimal(subLineNum));
        }
        // QC#23192 2018/02/09 Add End
        ZYPEZDItemValueSetter.setValue(newLine.xxLineNum_LL, NWAL1500CommonLogic.concatLineNum(newLine));
        ZYPEZDItemValueSetter.setValue(newLine.mdseCd_LL, splyItemInfo.mdseCd);
        ZYPEZDItemValueSetter.setValue(newLine.mdseNm_LL, mdseTMsg.mdseNm);
        ZYPEZDItemValueSetter.setValue(newLine.mdseDescShortTxt_LL, mdseInfo.get("MDSE_DESC_SHORT_TXT")); // S21_NA#2334 Add 
        NWAL1500CommonLogic.setUomPullDownForConfLine(bizMsg, newLine);
        ZYPEZDItemValueSetter.setValue(newLine.ordCustUomQty_LL, splyItemInfo.ordQty);
        ZYPEZDItemValueSetter.setValue(newLine.custUomCd_LL, mdseInfo.get("PKG_UOM_CD"));
        ZYPEZDItemValueSetter.setValue(newLine.ordQty_LL, splyItemInfo.ordQty);
        ZYPEZDItemValueSetter.setValue(newLine.entCpoDtlDealSlsAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.entDealNetUnitPrcAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.prcCatgCd_LL, bizMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(newLine.prcCatgNm_LL, bizMsg.prcCatgNm);
        ZYPEZDItemValueSetter.setValue(newLine.dsOrdLineCatgCd_LL, splyItemInfo.dsOrdLineCatgCd);

        // 2018/10/04 S21_NA#28383 add start
        CPO_SRC_TPTMsg cpoSrcTpTMsg = new CPO_SRC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.cpoSrcTpCd, CPO_SRC_TP.ORDER_ENTRY_SCREEN);

        cpoSrcTpTMsg = (CPO_SRC_TPTMsg) S21CodeTableAccessor.findByKey(cpoSrcTpTMsg);
        ZYPEZDItemValueSetter.setValue(newLine.cpoSrcTpDescTxt_LL, cpoSrcTpTMsg.cpoSrcTpDescTxt);
        // 2018/10/04 S21_NA#28383 add end

        // S21_NA#2522
//         //S21_NA#8409
//         //Call NWZC1800 Default WH API
//         //if (ZYPCommonFunc.hasValue(confMsg.shipToCustCd_LC) &&
//         //NWAL1500CommonLogic.isMdseTangible(bizMsg.glblCmpyCd.getValue(),
//         //newLine.mdseCd_LL.getValue())) {

//        if (ZYPCommonFunc.hasValue(confMsg.shipToCustCd_LC) && NWAL1500CommonLogic.needsWh(bizMsg.glblCmpyCd.getValue(), newLine.mdseCd_LL.getValue())) {
//            NWZC180001PMsg pMsg = new NWZC180001PMsg();
//            if (!NWAL1500CommonLogic.callDefWhApiForLineConf(pMsg, bizMsg, newLine.dsOrdPosnNum_LL.getValue(), newLine.mdseCd_LL.getValue(), BigDecimal.ZERO)) {
//                return false;
//            }
//
//            String rtlWhCd = pMsg.rtlWhCd.getValue();
//            String rtlSwhCd = pMsg.rtlSwhCd.getValue();
//
//            ZYPEZDItemValueSetter.setValue(newLine.rtlWhCd_LL, rtlWhCd);
//            ZYPEZDItemValueSetter.setValue(newLine.rtlWhNm_LL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
//            ZYPEZDItemValueSetter.setValue(newLine.rtlSwhCd_LL, rtlSwhCd);
//            ZYPEZDItemValueSetter.setValue(newLine.rtlSwhNm_LL, NWAL1500CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
//            ZYPEZDItemValueSetter.setValue(newLine.ordLineSrcCd_LL, pMsg.ordLineSrcCd);

//        }
// 2016/11/24 S21_NA#15962 Del Start
//        ZYPEZDItemValueSetter.setValue(newLine.rtlWhCd_LL, baseCmpntMsg.rtlWhCd_LL);
//        ZYPEZDItemValueSetter.setValue(newLine.rtlWhNm_LL, baseCmpntMsg.rtlWhNm_LL);
//        ZYPEZDItemValueSetter.setValue(newLine.rtlSwhCd_LL, baseCmpntMsg.rtlSwhCd_LL);
//        ZYPEZDItemValueSetter.setValue(newLine.rtlSwhNm_LL, baseCmpntMsg.rtlSwhNm_LL);
//        ZYPEZDItemValueSetter.setValue(newLine.ordLineSrcCd_LL, baseCmpntMsg.ordLineSrcCd_LL);
// 2016/11/24 S21_NA#15962 Del End

// 2018/01/31 S21_NA#23416 Mod Start
// 2016/11/24 S21_NA#15962 Add Start
//        // Call NWZC1800 Default WH API
//        if (ZYPCommonFunc.hasValue(confMsg.shipToCustCd_LC)) {
//            NWZC180001PMsg pMsg = new NWZC180001PMsg();
//            if (NWAL1500CommonLogic.callDefWhApiForLineConf(pMsg, bizMsg, newLine.dsOrdPosnNum_LL.getValue(), newLine.mdseCd_LL.getValue(), BigDecimal.ZERO)) {
//
//                String rtlWhCd = pMsg.rtlWhCd.getValue();
//                String rtlSwhCd = pMsg.rtlSwhCd.getValue();
//
//                ZYPEZDItemValueSetter.setValue(newLine.rtlWhCd_LL, rtlWhCd);
//                ZYPEZDItemValueSetter.setValue(newLine.rtlWhNm_LL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
//                ZYPEZDItemValueSetter.setValue(newLine.rtlSwhCd_LL, rtlSwhCd);
//                ZYPEZDItemValueSetter.setValue(newLine.rtlSwhNm_LL, NWAL1500CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
//                ZYPEZDItemValueSetter.setValue(newLine.ordLineSrcCd_LL, pMsg.ordLineSrcCd);
//            }
//        }
// 2016/11/24 S21_NA#15962 Add End
        if (ZYPCodeDataUtil.getVarCharConstValue(DROP_SHIP_RTL_WH_CD, bizMsg.glblCmpyCd.getValue()).equals(baseCmpntMsg.rtlWhCd_LL.getValue())) {
            ZYPEZDItemValueSetter.setValue(newLine.rtlWhCd_LL, baseCmpntMsg.rtlWhCd_LL);
            ZYPEZDItemValueSetter.setValue(newLine.rtlWhNm_LL, baseCmpntMsg.rtlWhNm_LL);
            ZYPEZDItemValueSetter.setValue(newLine.rtlSwhCd_LL, baseCmpntMsg.rtlSwhCd_LL);
            ZYPEZDItemValueSetter.setValue(newLine.rtlSwhNm_LL, baseCmpntMsg.rtlSwhNm_LL);
            ZYPEZDItemValueSetter.setValue(newLine.ordLineSrcCd_LL, baseCmpntMsg.ordLineSrcCd_LL);
        } else if (ZYPCommonFunc.hasValue(confMsg.shipToCustCd_LC)) {
            // 2018/09/20 S21_NA#28199 Del Start
//            NWZC180001PMsg pMsg = new NWZC180001PMsg();
//            if (NWAL1500CommonLogic.callDefWhApiForLineConf(pMsg, bizMsg, newLine.dsOrdPosnNum_LL.getValue(), newLine.mdseCd_LL.getValue(), BigDecimal.ZERO)) {
//
//                String rtlWhCd = pMsg.rtlWhCd.getValue();
//                String rtlSwhCd = pMsg.rtlSwhCd.getValue();
//
//                ZYPEZDItemValueSetter.setValue(newLine.rtlWhCd_LL, rtlWhCd);
//                ZYPEZDItemValueSetter.setValue(newLine.rtlWhNm_LL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
//                ZYPEZDItemValueSetter.setValue(newLine.rtlSwhCd_LL, rtlSwhCd);
//                ZYPEZDItemValueSetter.setValue(newLine.rtlSwhNm_LL, NWAL1500CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
//                ZYPEZDItemValueSetter.setValue(newLine.ordLineSrcCd_LL, pMsg.ordLineSrcCd);
//            }
            // 2018/09/20 S21_NA#28199 Del End
            // 2018/09/20 S21_NA#28199 Add Start
            List<NWZC180001PMsg> pMsgList = new ArrayList<NWZC180001PMsg>(0);
            NWAL1500_BSMsg forApiLineMsg = new NWAL1500_BSMsg();
            EZDMsg.copy(newLine, null, forApiLineMsg, null);
            forApiLineMsg.ordQty_LL.setValue(BigDecimal.ZERO);
            if (NWAL1500CommonLogic.callDefWhApiForLineConf(pMsgList, bizMsg, glblMsg, forApiLineMsg.dsOrdPosnNum_LL.getValue(), forApiLineMsg)) {
                for (NWZC180001PMsg pMsg : pMsgList) {
                    if (S21StringUtil.isEquals(forApiLineMsg.xxLineNum_LL.getValue(), pMsg.xxLineNum.getValue())) {
                      String rtlWhCd = pMsg.rtlWhCd.getValue();
                      String rtlSwhCd = pMsg.rtlSwhCd.getValue();

                      ZYPEZDItemValueSetter.setValue(newLine.rtlWhCd_LL, rtlWhCd);
                      ZYPEZDItemValueSetter.setValue(newLine.rtlWhNm_LL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
                      ZYPEZDItemValueSetter.setValue(newLine.rtlSwhCd_LL, rtlSwhCd);
                      ZYPEZDItemValueSetter.setValue(newLine.rtlSwhNm_LL, NWAL1500CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
                      ZYPEZDItemValueSetter.setValue(newLine.ordLineSrcCd_LL, pMsg.ordLineSrcCd);
                        break;
                    }
                }
            }
            // 2018/09/20 S21_NA#28199 Add End
        }
// 2018/01/31 S21_NA#23416 Mod End

        ZYPEZDItemValueSetter.setValue(newLine.supdLockFlg_LL, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(newLine.flPrcListCd_LL, bizMsg.flPrcListCd);
        ZYPEZDItemValueSetter.setValue(newLine.flPrcListNm_LL, bizMsg.flPrcListNm);
        ZYPEZDItemValueSetter.setValue(newLine.dealPrcListPrcAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.funcUnitFlPrcAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.cpoDtlDealTaxAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.xxLineTotPrcAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.coaMdseTpCd_LL, mdseInfo.get("COA_MDSE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(newLine.coaMdseTpDescTxt_LL, mdseInfo.get("COA_MDSE_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(newLine.coaProdCd_LL, mdseInfo.get("COA_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(newLine.coaProdDescTxt_LL, mdseInfo.get("COA_PROD_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(newLine.prcBaseDt_LL, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(newLine.rddDt_LL, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(newLine.allocQty_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.shipQty_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.istlQty_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.invQty_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.cancQty_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.dplyLineRefNum_LL, NWAL1500CommonLogicForLineConfig.getLineRefForChildLine(bizMsg, confMsg));
        ZYPEZDItemValueSetter.setValue(newLine.baseCmptFlg_LL, ZYPConstant.FLG_OFF_N);

        // 2018/01/31 S21_NA#19808 Add Start
        EZDMsg.copy(newLine, null, newLineP, null);
        // 2018/01/31 S21_NA#19808 Add End
        return true;
    }

    // S21_NA#8409
    /**
     * Derive Base Component Msg
     * @param lineAllList
     * @param dsOrdPosnNum
     * @return
     */
    private static NWAL1500_BSMsg deriveBaseCmpntMsg(NWAL1500_BSMsgArray lineAllList, String dsOrdPosnNum) { // 2018/01/31 S21_NA#19808 Mod
        NWAL1500_BSMsg lineMsg = null;
        for (int i = 0; i < lineAllList.getValidCount(); i++) {
            lineMsg = lineAllList.no(i);

            if (!lineMsg.dsOrdPosnNum_LL.getValue().equals(dsOrdPosnNum)) {
                continue;
            }

            if (ZYPConstant.FLG_ON_Y.equals(lineMsg.baseCmptFlg_LL.getValue())) {
                return lineMsg;
            }
        }

        return null;
    }

    /**
     * Check Exist Same Supply Item
     * @param lineAllList NWAL1500_JSMsgArray
     * @param dsOrdPosnNum DS Order Position Number
     * @param splyItemInfo Supply Item Info
     * @param glblCmpyCd Global Company Code
     * @return Exist Same Supply Item : true
     */
    private static boolean isExistSameSplyItem(NWAL1500_BSMsgArray lineAllList, String dsOrdPosnNum, NWZC181002PMsg splyItemInfo, String glblCmpyCd) { // 2018/01/31 S21_NA#19808 Mod

        // Mod Start 2017/10/05 QC#21356
        // S21_NA#11838
//        String splyMdseCd = splyItemInfo.mdseCd.getValue();
//        if (ZYPCommonFunc.hasValue(splyMdseCd) && splyMdseCd.length() > 8) {
//            splyMdseCd = splyMdseCd.substring(0, 8);
//        }
        String splyMdseCd = NWAL1500CommonLogic.getOrdTakeMdseCd(glblCmpyCd, splyItemInfo.mdseCd.getValue());
        // Mod End 2017/10/05 QC#21356

        for (int i = 0; i < lineAllList.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = lineAllList.no(i); // 2018/01/31 S21_NA#19808 Mod
            String trgtPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
            String trgtMdseCd = lineMsg.mdseCd_LL.getValue();
            String trgtDsOrdLineCatgCd = lineMsg.dsOrdLineCatgCd_LL.getValue();

            if (!trgtPosnNum.equals(dsOrdPosnNum)) {
                continue;
            }

            // Mod Start 2017/10/05 QC#21356
//            if (ZYPCommonFunc.hasValue(trgtMdseCd) && trgtMdseCd.length() > 8) {
//                trgtMdseCd = trgtMdseCd.substring(0, 8);
//            }
            trgtMdseCd = NWAL1500CommonLogic.getOrdTakeMdseCd(glblCmpyCd, trgtMdseCd);
            // Mod End 2017/10/05 QC#21356

            // S21_NA#11838
//            if (trgtMdseCd.equals(splyItemInfo.mdseCd.getValue()) && trgtDsOrdLineCatgCd.equals(splyItemInfo.dsOrdLineCatgCd.getValue())) {
            if (trgtMdseCd.equals(splyMdseCd) && trgtDsOrdLineCatgCd.equals(splyItemInfo.dsOrdLineCatgCd.getValue())) {
                return true;
            }
        }

        return false;
    }

    /**
     * <pre>
     * Auto add items for RMA Config when user chose Auto Add Action.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param lineAllList RMA Line Message Array
     * @return checkZeroFlg // S21_NA#9104 Add
     * </pre>
     */
    public static boolean doProcessAutoAddRMA(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_DSMsgArray lineAllList) { // 2018/01/31 S21_NA#19808 Mod
        printLog("********** Start Of Method doProcessAutoAddRMA ********");
        //QC743
        //copyBizMsgToGlblMsg(bizMsg.D, glblMsg.K); // 2016/01/12 S21_NA#2726 Add
        //printDMsg(bizMsg.D);
        //printKMsg(glblMsg.K);
        // 2018/01/31 S21_NA#19808 Add Start
        NWAL1500CommonLogicForPagination.saveRmaDataToGlbl(bizMsg, glblMsg);
        int curPageNum = bizMsg.xxPageShowCurNum_RL.getValueInt();
        // 2018/01/31 S21_NA#19808 Add End

        List<Integer> checkConfList = ZYPTableUtil.getSelectedRows(glblMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y); // 2018/01/31 S21_NA#19808 Mod
        List<Integer> checkLineList = ZYPTableUtil.getSelectedRows(glblMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y); // 2018/01/31 S21_NA#19808 Mod

        List<String> configNumList = new ArrayList<String>();
        // for config
        List<NWZC182001PMsg> paramForConfigList = new ArrayList<NWZC182001PMsg>();
        // Add Start 2017/01/27 QC#17257
        List<String> posnNums = new ArrayList<String>();
        // Add End 2017/01/27 QC#17257

        // S21_NA#5350 Mod Start
        // for (int checkConf : checkConfList) {
        // NWAL1500_CCMsgArray congigList = bizMsg.C;
        // paramForConfigList.add(setupApiParam(bizMsg, congigList.no(checkConf)));
        // configNumList.add(congigList.no(checkConf).dsOrdPosnNum_RC.getValue());
        // }
        boolean checkZeroFlg = checkConfList.size() + checkLineList.size() == 0;

        // S21_NA#9104 Add Start
        if (checkZeroFlg) {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) { // 2018/01/31 S21_NA#19808 Mod

                NWAL1500_CSMsg configMsg = glblMsg.C.no(i); // 2018/01/31 S21_NA#19808 Mod
                //QC#9849 add Start
                String configTpCd = configMsg.configTpCd_RC.getValue();
                boolean rslt = NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configTpCd, CONFIG_CATG.INBOUND, false, true, false);
                if (!rslt) {
                    continue;
                }
                //QC#9849 add End
                if (ZYPCommonFunc.hasValue(configMsg.svcConfigMstrPk_RC)) {
                    configMsg.xxChkBox_RC.setValue(ZYPConstant.FLG_ON_Y);
                    // Add Start 2017/01/27 QC#17257
                    String getPosnNum = configMsg.dsOrdPosnNum_RC.getValue();
                    if (!posnNums.contains(getPosnNum)) {
                        posnNums.add(getPosnNum);
                    }
                    // Add End 2017/01/27 QC#17257
                    continue;
                }
                for (int j = 0; j < glblMsg.D.getValidCount(); j++) { // 2018/01/31 S21_NA#19808 Mod
                    NWAL1500_DSMsg lineMsg = glblMsg.D.no(j); // 2018/01/31 S21_NA#19808 Mod
                    if (!lineMsg.dsOrdPosnNum_RL.getValue().equals(configMsg.dsOrdPosnNum_RC.getValue())) {
                        continue;
                    }
                    if (ZYPCommonFunc.hasValue(lineMsg.serNum_RL)) {
                        lineMsg.xxChkBox_RL.setValue(ZYPConstant.FLG_ON_Y); 
                        // Add Start 2017/01/27 QC#17257
                        String getPosnNum = configMsg.dsOrdPosnNum_RC.getValue();
                        if (!posnNums.contains(getPosnNum)) {
                            posnNums.add(getPosnNum);
                        }
                        // Add End 2017/01/27 QC#17257
                    }
                }
            }
        }

        checkConfList = ZYPTableUtil.getSelectedRows(glblMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y); // 2018/01/31 S21_NA#19808 Mod
        // S21_NA#9104 Add End

        
        for (int checkConf : checkConfList) {
            NWAL1500_CSMsgArray congigList = glblMsg.C;  // 2018/01/31 S21_NA#19808 Mod
            paramForConfigList.add(setupApiParam(bizMsg, congigList.no(checkConf)));
            configNumList.add(congigList.no(checkConf).dsOrdPosnNum_RC.getValue());
            // Add Start 2017/01/27 QC#17257
            String getPosnNum = congigList.no(checkConf).dsOrdPosnNum_RC.getValue();
            if (!posnNums.contains(getPosnNum)) {
                posnNums.add(getPosnNum);
            }
            // Add End 2017/01/27 QC#17257
        }
        // S21_NA#5350 Mod End

        new NWZC182001().execute(paramForConfigList, ONBATCH_TYPE.ONLINE);
        int errCnt = 0;
        for (NWZC182001PMsg paramForConfig : paramForConfigList) {
            if (paramForConfig.xxMsgIdList.getValidCount() > 0) {
                setAutoAddRmaErr(paramForConfig, glblMsg.C); // 2018/01/31 S21_NA#19808 Mod
                errCnt++;
            }
        }
        if (errCnt > 0) {
            return checkZeroFlg; // S21_NA#9104 Add void -> boolean
        }
        int configIndex = 0;
        int confAddCnt = 0; // 2016/01/15 S21_NA#3153 Add
        for (NWZC182001PMsg paramForConfig : paramForConfigList) {
            // seek config list

// 2016/11/14 S21_NA#11434-2 Del Start
//            // prepare component
//            String dsOrdPosnNum = configNumList.get(configIndex++);
//            NWAL1500CommonLogicForConfigId.deleteEmptyLine(lineAllList, dsOrdPosnNum);
//            int lineStartIndex = NWAL1500CommonLogicForConfigId.getAddLineRow(lineAllList, dsOrdPosnNum);
//            int maxLineNum = NWAL1500CommonLogicForConfigId.getMaxLineNum(lineAllList, dsOrdPosnNum);
//            int insertRow = lineStartIndex;
//            int lineCount = 0;
//            String alreadyAddedMdseCd = ""; // 2016/10/05 S21_NA#11434 Add
//            NWAL1500_DCMsg headerLine = new NWAL1500_DCMsg(); // 2016/11/10 S21_NA#10363-2 Add
//            for (int i = 0; i < paramForConfig.NWZC182002PMsg.getValidCount(); i++) {
//                NWZC182002PMsg component = paramForConfig.NWZC182002PMsg.no(i);
//                // 2016/06/16 S21_NA#9848 Mod Start
//                // 2016/01/15 S21_NA#3153 Add Start
//                // NWAL1500_DCMsg rmaLineMsg = getAlreadyAddedRMALine(bizMsg, glblMsg, paramForConfig.svcConfigMstrPk.getValue(), component.mdseCd.getValue(), component.serNum.getValue(), null); // S21_NA#5340
//                NWAL1500_RCMsg configIdMsg = new NWAL1500_RCMsg();
//                ZYPEZDItemValueSetter.setValue(configIdMsg.mdseCd_O, component.mdseCd);
//                ZYPEZDItemValueSetter.setValue(configIdMsg.serNum_O, component.serNum);
//                ZYPEZDItemValueSetter.setValue(configIdMsg.svcMachMstrPk_O, component.svcMachMstrPk);
//
//                // 2016/10/05 S21_NA#11434 Mod Start
//                NWAL1500_DCMsg rmaLineMsg = null;
//                if (ZYPCommonFunc.hasValue(configIdMsg.mdseCd_O) && !alreadyAddedMdseCd.equals(configIdMsg.mdseCd_O.getValue())) {
//                    rmaLineMsg = getAlreadyAddedRMALine(bizMsg, glblMsg, paramForConfig.svcConfigMstrPk.getValue(), configIdMsg, null);
//                    alreadyAddedMdseCd = configIdMsg.mdseCd_O.getValue();
//                }
//                // 2016/10/05 S21_NA#11434 Mod End
//
//                // 2016/06/16 S21_NA#9848 Mod End
//                if (rmaLineMsg != null) {
//                    ZYPEZDItemValueSetter.setValue(rmaLineMsg.svcMachMstrPk_RL, component.svcMachMstrPk); // S21_NA#5340
//                    // 2016/11/10 S21_NA#10363-2 Add Start
//                    if (!ZYPCommonFunc.hasValue(headerLine.rtrnRsnCd_RL) && ZYPCommonFunc.hasValue(rmaLineMsg.rtrnRsnCd_RL)) {
//                        ZYPEZDItemValueSetter.setValue(headerLine.rtrnRsnCd_RL, rmaLineMsg.rtrnRsnCd_RL);
//                    }
//                    if (!ZYPCommonFunc.hasValue(headerLine.rtlWhCd_RL) && ZYPCommonFunc.hasValue(rmaLineMsg.rtlWhCd_RL)) {
//                        ZYPEZDItemValueSetter.setValue(headerLine.rtlWhCd_RL, rmaLineMsg.rtlWhCd_RL);
//                        ZYPEZDItemValueSetter.setValue(headerLine.rtlWhNm_RL, rmaLineMsg.rtlWhNm_RL);
//                        ZYPEZDItemValueSetter.setValue(headerLine.rtlSwhCd_RL, rmaLineMsg.rtlSwhCd_RL);
//                        ZYPEZDItemValueSetter.setValue(headerLine.rtlSwhNm_RL, rmaLineMsg.rtlSwhNm_RL);
//                        ZYPEZDItemValueSetter.setValue(headerLine.thirdPtyDspTpCd_RL, rmaLineMsg.thirdPtyDspTpCd_RL);
//                    }
//                    // 2016/11/10 S21_NA#10363-2 Add End
//                    continue;
//                }
//                // 2016/01/15 S21_NA#3153 Add End
//                NWAL1500_DCMsg newLine = (NWAL1500_DCMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow);
//                if (newLine == null) {
//                    return checkZeroFlg; // S21_NA#9104 Add void -> boolean
//                }
//                // 2016/11/10 S21_NA#10363-2 Add Start
//                setRtrnRsnWh(newLine, headerLine);
//                // 2016/11/10 S21_NA#10363-2 Add End
//                lineCount++;
//                // set up line
//                if (!setUpLine(newLine, bizMsg, glblMsg, component, dsOrdPosnNum, maxLineNum + lineCount, insertRow++, null)) {
//                    return checkZeroFlg; // S21_NA#9104 Add void -> boolean
//                }
//                confAddCnt++; // 2016/01/15 S21_NA#3153 Add
// 2016/11/14 S21_NA#11434-2 Del End

            // 2016/11/14 S21_NA#11434-2 Add Start
            // prepare component
            String dsOrdPosnNum = configNumList.get(configIndex++);
            NWAL1500CommonLogicForConfigId.deleteEmptyLine(lineAllList, dsOrdPosnNum);
            // 2017/10/19 S21_NA#21843 Add Start
            NWAL1500_CSMsg rmaConfig = NWAL1500CommonLogic.getParentConfig(glblMsg.C, dsOrdPosnNum); // 2018/01/31 S21_NA#19808 Mod
            setMdlInfoToRmaConfig(bizMsg, rmaConfig, paramForConfig.svcConfigMstrPk.getValue());
            // 2017/10/19 S21_NA#21843 Add End
            int lineStartIndex = NWAL1500CommonLogicForPagination.getAddLineRowForGlobal(lineAllList, dsOrdPosnNum); // 2018/01/31 S21_NA#19808 Mod
            int maxLineNum = NWAL1500CommonLogicForPagination.getMaxLineNumForGlobal(lineAllList, dsOrdPosnNum); // 2018/01/31 S21_NA#19808 Mod
            int insertRow = lineStartIndex;
            int lineCount = 0;
            NWAL1500_DSMsg headerLine = new NWAL1500_DSMsg(); // 2016/11/10 S21_NA#10363-2 Add  // 2018/01/31 S21_NA#19808
            for (int i = 0; i < paramForConfig.NWZC182002PMsg.getValidCount(); i++) {
                NWZC182002PMsg component = paramForConfig.NWZC182002PMsg.no(i);
                boolean isMatch = false;
                int size = new ORD_TAKE_MDSETMsg().getAttr("ordTakeMdseCd").getDigit();
                BigDecimal dbSvcMachMstrPk = component.svcMachMstrPk.getValue();
                String dbSerNum = component.serNum.getValue();
                String dbMdse = component.mdseCd.getValue();
                if(dbMdse.length() > size) {
                    dbMdse = dbMdse.substring(0, size);
                }
                for (int j = 0; j < glblMsg.D.getValidCount(); j++) { // 2018/01/31 S21_NA#19808
                    NWAL1500_DSMsg lineMsg = glblMsg.D.no(j);  // 2018/01/31 S21_NA#19808

                    if (!dsOrdPosnNum.equals(lineMsg.dsOrdPosnNum_RL.getValue())) {
                        continue;
                    }
                    // Add Start 2019/11/15 QC#54515
                    if (ZYPCommonFunc.hasValue(lineMsg.ordLineStsCd_RL) //
                            && S21StringUtil.isEquals(ORD_LINE_STS.CANCELLED, lineMsg.ordLineStsCd_RL.getValue())) {
                        continue;
                    }
                    // Add End 2019/11/15 QC#54515

                    //is match by (MDSE + Ser#)
                    BigDecimal lineSvcMachMstrPk = lineMsg.svcMachMstrPk_RL.getValue();
                    String lineSerNum = lineMsg.serNum_RL.getValue();
                    String lineMdse = lineMsg.mdseCd_RL.getValue();
                    if(lineMdse.length() > size) {
                        lineMdse = lineMdse.substring(0, size);
                    }
                    //
                    if (!ZYPCommonFunc.hasValue(headerLine.rtrnRsnCd_RL) && ZYPCommonFunc.hasValue(lineMsg.rtrnRsnCd_RL)) {
                        ZYPEZDItemValueSetter.setValue(headerLine.rtrnRsnCd_RL, lineMsg.rtrnRsnCd_RL);
                    }
                    if (!ZYPCommonFunc.hasValue(headerLine.rtlWhCd_RL) && ZYPCommonFunc.hasValue(lineMsg.rtlWhCd_RL)) {
                        ZYPEZDItemValueSetter.setValue(headerLine.rtlWhCd_RL, lineMsg.rtlWhCd_RL);
                        ZYPEZDItemValueSetter.setValue(headerLine.rtlWhNm_RL, lineMsg.rtlWhNm_RL);
                        ZYPEZDItemValueSetter.setValue(headerLine.rtlSwhCd_RL, lineMsg.rtlSwhCd_RL);
                        ZYPEZDItemValueSetter.setValue(headerLine.rtlSwhNm_RL, lineMsg.rtlSwhNm_RL);
                        ZYPEZDItemValueSetter.setValue(headerLine.thirdPtyDspTpCd_RL, lineMsg.thirdPtyDspTpCd_RL);
                    }

                    //is match by (Machine Master ID)
                    if (ZYPCommonFunc.hasValue(lineSvcMachMstrPk) && dbSvcMachMstrPk.compareTo(lineSvcMachMstrPk) == 0) {
                        isMatch = true;
                        break;
                    }
                    //is match by (MDSE + Ser#)
                    if (!ZYPCommonFunc.hasValue(lineSvcMachMstrPk) && (dbMdse.equals(lineMdse)) && (dbSerNum.equals(lineSerNum))) {
                        ZYPEZDItemValueSetter.setValue(lineMsg.serNum_RL, component.serNum);
                        ZYPEZDItemValueSetter.setValue(lineMsg.svcMachMstrPk_RL, component.svcMachMstrPk);
                        setRtrnRsnWh(lineMsg, headerLine);
                        isMatch = true;
                        // 2018/08/21 S21_NA#26767 Add Start
                        if (ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_RL)) {
                            NWAL1500CommonLogicForLineConfig.defaultingRtrnRsn(bizMsg, lineMsg);
                        }
                        // 2018/08/21 S21_NA#26767 Add End
                        break;
                    }
                    //is match by (MDSE)
                    if (!ZYPCommonFunc.hasValue(lineSvcMachMstrPk) && dbMdse.equals(lineMdse)) {
                        ZYPEZDItemValueSetter.setValue(lineMsg.serNum_RL, component.serNum);
                        ZYPEZDItemValueSetter.setValue(lineMsg.svcMachMstrPk_RL, component.svcMachMstrPk);
                        setRtrnRsnWh(lineMsg, headerLine);
                        isMatch = true;
                        // 2018/08/21 S21_NA#26767 Add Start
                        if (ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_RL)) {
                            NWAL1500CommonLogicForLineConfig.defaultingRtrnRsn(bizMsg, lineMsg);
                        }
                        // 2018/08/21 S21_NA#26767 Add End
                        break;
                    }
                }
                if (isMatch) {
                    continue;
                }
                //prepare new line
                NWAL1500_DSMsg newLine = (NWAL1500_DSMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow);
                if (newLine == null) {
                    return checkZeroFlg; // S21_NA#9104 Add void -> boolean
                }
                setRtrnRsnWh(newLine, headerLine);
                // Add Start 2017/02/01 QC#17257
                lineCount++;
                // Add End 2017/02/01 QC#17257
                if (!setUpLine(newLine, bizMsg, glblMsg, component, dsOrdPosnNum, maxLineNum + lineCount, insertRow++, null)) {
                    return checkZeroFlg; // S21_NA#9104 Add void -> boolean
                }

                // 2019/05/17 S21_NA#50328 Del Start
//                MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), component.mdseCd.getValue());
//                if (mdseTMsg != null && S21StringUtil.isEquals(mdseTMsg.mdseTpCd.getValue(), MDSE_TP.SET)) {
//                    int lineSubNum = 0;
//                    List<Map<String, Object>> childMdseList = NWAL1500CommonLogicForConfigId.getChildMdse(bizMsg, mdseTMsg.mdseCd.getValue());
//                    for (Map<String, Object> childMdse : childMdseList) {
//                        NWAL1500_DSMsg newChildLine = (NWAL1500_DSMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow);// 2018/01/31 S21_NA#19808
//                        if (newLine == null) {
//                            return checkZeroFlg; // S21_NA#9104 Add void -> boolean
//                        }
//                        BigDecimal baseEachQty = newLine.ordQty_RL.getValue();
//                        // set up line
//                        if (!setUpLine(newChildLine, bizMsg, glblMsg, childMdse, baseEachQty, dsOrdPosnNum, maxLineNum + lineCount, lineSubNum, insertRow++)) {
//                            return checkZeroFlg; // S21_NA#9104 Add void -> boolean
//                        }
//                        MDSETMsg childMdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), (String) childMdse.get("MDSE_CD"));
//                        if (mdseTMsg != null && S21StringUtil.isEquals(childMdseTMsg.mdseTpCd.getValue(), MDSE_TP.SET)) {
//                            baseEachQty = newChildLine.ordQty_RL.getValue();
//                            List<Map<String, Object>> grandChildMdseList = NWAL1500CommonLogicForConfigId.getChildMdse(bizMsg, childMdseTMsg.mdseCd.getValue());
//                            if (grandChildMdseList.size() > 0) {
//                                insertRow--;
//                                List<Integer> deleteLisy = new ArrayList<Integer>();
//                                deleteLisy.add(insertRow);
//                                ZYPTableUtil.deleteRows(lineAllList, deleteLisy);
//                            }
//                            for (Map<String, Object> grandChildMdse : grandChildMdseList) {
//                                NWAL1500_DSMsg newGrandChildLine = (NWAL1500_DSMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow); // 2018/01/31 S21_NA#19808
//                                if (newGrandChildLine == null) {
//                                    return checkZeroFlg; // S21_NA#9104 Add void -> boolean
//                                }
//                                if (!setUpLine(newGrandChildLine, bizMsg, glblMsg, grandChildMdse, baseEachQty, dsOrdPosnNum, maxLineNum + lineCount, lineSubNum++, insertRow++)) {
//                                    return checkZeroFlg; // S21_NA#9104 Add void -> boolean
//                                }
//                            }
//                        }
//                    }
//                }
             // 2019/05/17 S21_NA#50328 Del END
                confAddCnt++; // 2016/01/15 S21_NA#3153 Add
                // 2016/11/14 S21_NA#11434-2 Add End
            }
        }

        // for line
        List<Map<String, Object>> lineNumList = new ArrayList<Map<String, Object>>();
        List<NWZC182001PMsg> paramForLineList = new ArrayList<NWZC182001PMsg>();
        
        // S21_NA#9104 Add Start
        checkLineList = ZYPTableUtil.getSelectedRows(glblMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y); // 2018/01/31 S21_NA#19808
        // S21_NA#9104 Add End
        for (int checkLine : checkLineList) {
            NWAL1500_DSMsgArray lineList = glblMsg.D; // 2018/01/31 S21_NA#19808
            // 2016/06/21 S21_NA#9849-2 Add Start
            NWAL1500_CSMsg rmaConfigMsg = NWAL1500CommonLogic.getParentConfig(glblMsg, lineList.no(checkLine)); // 2018/01/31 S21_NA#19808 // 2018/01/31 S21_NA#19808
            boolean rslt = NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), rmaConfigMsg.configTpCd_RC.getValue(), CONFIG_CATG.INBOUND, false, true, false);
            if (!rslt) {
                rmaConfigMsg.configTpCd_RC.setErrorInfo(1, NWAL1500MsgConstant.NWAM0864E);
                continue;
            }
            // 2016/06/21 S21_NA#9849-2 Add End
            paramForLineList.add(setupApiParam(bizMsg, glblMsg, lineList.no(checkLine))); // 2018/01/31 S21_NA#19808
            Map<String, Object> lineNum = new HashMap<String, Object>();
            lineNum.put("dsOrdPosnNum", lineList.no(checkLine).dsOrdPosnNum_RL.getValue());
            lineNum.put("dsCpoLineNum", lineList.no(checkLine).dsCpoLineNum_RL.getValue());
            lineNum.put("dsCpoLineSubNum", lineList.no(checkLine).dsCpoLineSubNum_RL.getValue());
            lineNum.put("mdseCd", lineList.no(checkLine).mdseCd_RL.getValue()); // 2016/01/08 S21_NA#2726 Add
            lineNum.put("serNum", lineList.no(checkLine).serNum_RL.getValue()); // 2016/01/08 S21_NA#2726 Add
            // 2016/11/14 S21_NA#11434-2 Add
            lineNum.put("svcMachMstrPk", lineList.no(checkLine).svcMachMstrPk_RL.getValue());
            lineNumList.add(lineNum);

            // Add Start 2017/01/27 QC#17257
            String getPosnNum = lineList.no(checkLine).dsOrdPosnNum_RL.getValue();
            if (!posnNums.contains(getPosnNum)) {
                posnNums.add(getPosnNum);
            }
            // Add End 2017/01/27 QC#17257
        }
        new NWZC182001().execute(paramForLineList, ONBATCH_TYPE.ONLINE);
        for (NWZC182001PMsg paramForLine : paramForLineList) {
            if (paramForLine.xxMsgIdList.getValidCount() > 0) {
                bizMsg.setMessageInfo(paramForLine.xxMsgIdList.no(0).xxMsgId.getValue());
                return checkZeroFlg; // S21_NA#9104 Add void -> boolean
            }
        }
        int lineIndex = 0;
        // for (NWZC182001PMsg paramForConfig : paramForConfigList) { 2015/01/05 S21_NA#2585 Del
        printLog("********** Before Line API Of Method doProcessAutoAddRMA ********");
        printDMsg(glblMsg.D);
        // QC743
        // printKMsg(glblMsg.K);
        int lineAddCnt = 0; // 2016/01/15 S21_NA#3153 Add
        for (NWZC182001PMsg paramForLine : paramForLineList) { // 2015/01/05 S21_NA#2585 Add
            // seek config list

// 2016/11/14 S21_NA#11434-2 Del Start
//            // prepare component
//            Map<String, Object> lineNum = lineNumList.get(lineIndex++);
//            String dsOrdPosnNum = (String) lineNum.get("dsOrdPosnNum");
//            // 2016/01/08 S21_NA#2726 Add Start
//            String mdseCd = (String) lineNum.get("mdseCd");
//            String serNum = (String) lineNum.get("serNum");
//            NWAL1500_CCMsg rmaConfig = NWAL1500CommonLogic.getParentConfig(bizMsg, dsOrdPosnNum);
//            ZYPEZDItemValueSetter.setValue(rmaConfig.svcConfigMstrPk_RC, paramForLine.svcConfigMstrPk);
//            // 2016/01/08 S21_NA#2726 Add End
//            int lineStartIndex = NWAL1500CommonLogicForConfigId.getAddLineRow(lineAllList, dsOrdPosnNum);
//            int maxLineNum = NWAL1500CommonLogicForConfigId.getMaxLineNum(lineAllList, dsOrdPosnNum);
//            int insertRow = lineStartIndex;
//            int lineCount = 0;
//            String alreadyAddedMdseCd = ""; // 2016/10/05 S21_NA#11434 Add
//            NWAL1500_DCMsg headerLine = new NWAL1500_DCMsg(); // 2016/11/10 S21_NA#10363-2 Add
//            // for (int i = 0; i < paramForConfig.NWZC182002PMsg.getValidCount(); i++) { 2015/01/05 S21_NA#2585 Del
//            for (int i = 0; i < paramForLine.NWZC182002PMsg.getValidCount(); i++) { // 2015/01/05 S21_NA#2585 Add
//                // NWZC182002PMsg component = paramForConfig.NWZC182002PMsg.no(i); 2015/01/05 S21_NA#2585 Del
//                NWZC182002PMsg component = paramForLine.NWZC182002PMsg.no(i); // 2015/01/05 S21_NA#2585 Add
//                // 2016/06/16 S21_NA#9848 Mod Start
//                // 2016/01/15 S21_NA#3153 Add Start
//                // NWAL1500_DCMsg rmaLineMsg = getAlreadyAddedRMALine(bizMsg, glblMsg, paramForLine.svcConfigMstrPk.getValue(), component.mdseCd.getValue(), component.serNum.getValue(), lineNum); // S21_NA#5340
//                NWAL1500_RCMsg configIdMsg = new NWAL1500_RCMsg();
//                ZYPEZDItemValueSetter.setValue(configIdMsg.mdseCd_O, component.mdseCd);
//                ZYPEZDItemValueSetter.setValue(configIdMsg.serNum_O, component.serNum);
//                ZYPEZDItemValueSetter.setValue(configIdMsg.svcMachMstrPk_O, component.svcMachMstrPk);
//                // 2016/10/05 S21_NA#11434 Mod Start
//                NWAL1500_DCMsg rmaLineMsg = null;
//                if (ZYPCommonFunc.hasValue(configIdMsg.mdseCd_O) && !alreadyAddedMdseCd.equals(configIdMsg.mdseCd_O.getValue())) {
//                    rmaLineMsg = getAlreadyAddedRMALine(bizMsg, glblMsg, paramForLine.svcConfigMstrPk.getValue(), configIdMsg, lineNum);
//                    alreadyAddedMdseCd = configIdMsg.mdseCd_O.getValue();
//                }
//                // 2016/10/05 S21_NA#11434 Mod End
//                // 2016/06/16 S21_NA#9848 Mod End
//                if (rmaLineMsg != null) {
//                    ZYPEZDItemValueSetter.setValue(rmaLineMsg.svcMachMstrPk_RL, component.svcMachMstrPk); // S21_NA#5340
//                    // 2016/11/10 S21_NA#10363-2 Add Start
//                    if (!ZYPCommonFunc.hasValue(headerLine.rtrnRsnCd_RL) && ZYPCommonFunc.hasValue(rmaLineMsg.rtrnRsnCd_RL)) {
//                        ZYPEZDItemValueSetter.setValue(headerLine.rtrnRsnCd_RL, rmaLineMsg.rtrnRsnCd_RL);
//                    }
//                    if (!ZYPCommonFunc.hasValue(headerLine.rtlWhCd_RL) && ZYPCommonFunc.hasValue(rmaLineMsg.rtlWhCd_RL)) {
//                        ZYPEZDItemValueSetter.setValue(headerLine.rtlWhCd_RL, rmaLineMsg.rtlWhCd_RL);
//                        ZYPEZDItemValueSetter.setValue(headerLine.rtlWhNm_RL, rmaLineMsg.rtlWhNm_RL);
//                        ZYPEZDItemValueSetter.setValue(headerLine.rtlSwhCd_RL, rmaLineMsg.rtlSwhCd_RL);
//                        ZYPEZDItemValueSetter.setValue(headerLine.rtlSwhNm_RL, rmaLineMsg.rtlSwhNm_RL);
//                        ZYPEZDItemValueSetter.setValue(headerLine.thirdPtyDspTpCd_RL, rmaLineMsg.thirdPtyDspTpCd_RL);
//                    }
//                    // 2016/11/10 S21_NA#10363-2 Add End
//                    continue;
//                }
//                // 2016/01/15 S21_NA#3153 Add End
//                // 2016/01/12 S21_NA#2726 Mod Start
//                //NWAL1500_KSMsg newLine = (NWAL1500_KSMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow);
//                NWAL1500_DCMsg newLine = null;
//                String cmpMdseCd = component.mdseCd.getValue();
//                String cmpSerNum = String.valueOf(component.serNum.getValue());
//                if (mdseCd.equals(cmpMdseCd) && serNum.equals(cmpSerNum)) {
//                    setUpLine(bizMsg, lineNum, component); //S21_NA#5340
//                    continue;
//                } else if (!ZYPCommonFunc.hasValue(mdseCd)
//                        && serNum.equals(cmpSerNum)) { // 2016/06/21 S21_NA#9849-2 Add Start
//                    Integer slctLineIdx = new Integer(-1);
//                    rmaLineMsg = getSameSerialRmaLineMsg(bizMsg, cmpSerNum, slctLineIdx);
//                    setUpLine(rmaLineMsg, bizMsg, glblMsg, component, dsOrdPosnNum, rmaLineMsg.dsCpoLineNum_RL.getValueInt(), slctLineIdx.intValue(), null);
//                    continue;
//                    // 2016/06/21 S21_NA#9849-2 Add End
//                } else {
//                    newLine = (NWAL1500_DCMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow);
//                }
//                // 2016/01/12 S21_NA#2726 Mod End
//                if (newLine == null) {
//                    return checkZeroFlg; // S21_NA#9104 Add void -> boolean
//                }
//                // 2016/11/10 S21_NA#10363-2 Add Start
//                setRtrnRsnWh(newLine, headerLine);
//                // 2016/11/10 S21_NA#10363-2 Add End
//                lineCount++;
//
//                // set up line
//                if (!setUpLine(newLine, bizMsg, glblMsg, component, dsOrdPosnNum, maxLineNum + lineCount, insertRow++, null)) {
//                    return checkZeroFlg; // S21_NA#9104 Add void -> boolean
//                }
//                MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), component.mdseCd.getValue());
//                if (mdseTMsg != null && S21StringUtil.isEquals(mdseTMsg.mdseTpCd.getValue(), MDSE_TP.SET)) {
//                    int lineSubNum = 0;
//                    List<Map<String, Object>> childMdseList = NWAL1500CommonLogicForConfigId.getChildMdse(bizMsg, mdseTMsg.mdseCd.getValue());
//                    for (Map<String, Object> childMdse : childMdseList) {
//                        NWAL1500_DCMsg newChildLine = (NWAL1500_DCMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow);
//                        if (newLine == null) {
//                            return checkZeroFlg; // S21_NA#9104 Add void -> boolean
//                        }
//                        BigDecimal baseEachQty = newLine.ordQty_RL.getValue();
//                        // set up line
//                        if (!setUpLine(newChildLine, bizMsg, glblMsg, childMdse, baseEachQty, dsOrdPosnNum, maxLineNum + lineCount, lineSubNum, insertRow++)) {
//                            return checkZeroFlg; // S21_NA#9104 Add void -> boolean
//                        }
//                        MDSETMsg childMdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), (String) childMdse.get("MDSE_CD"));
//                        if (mdseTMsg != null && S21StringUtil.isEquals(childMdseTMsg.mdseTpCd.getValue(), MDSE_TP.SET)) {
//                            baseEachQty = newChildLine.ordQty_RL.getValue();
//                            List<Map<String, Object>> grandChildMdseList = NWAL1500CommonLogicForConfigId.getChildMdse(bizMsg, childMdseTMsg.mdseCd.getValue());
//                            if (grandChildMdseList.size() > 0) {
//                                insertRow--;
//                                List<Integer> deleteLisy = new ArrayList<Integer>();
//                                deleteLisy.add(insertRow);
//                                ZYPTableUtil.deleteRows(lineAllList, deleteLisy);
//                            }
//                            for (Map<String, Object> grandChildMdse : grandChildMdseList) {
//                                NWAL1500_DCMsg newGrandChildLine = (NWAL1500_DCMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow);
//                                if (newGrandChildLine == null) {
//                                    return checkZeroFlg; // S21_NA#9104 Add void -> boolean
//                                }
//                                if (!setUpLine(newGrandChildLine, bizMsg, glblMsg, grandChildMdse, baseEachQty, dsOrdPosnNum, maxLineNum + lineCount, lineSubNum++, insertRow++)) {
//                                    return checkZeroFlg; // S21_NA#9104 Add void -> boolean
//                                }
//                            }
//                        }
//                    }
//                }
//                lineAddCnt++; // 2016/01/15 S21_NA#3153 Add
// 2016/11/14 S21_NA#11434-2 Del End

            // 2016/11/14 S21_NA#11434-2 Add Start
            // prepare component
            Map<String, Object> lineNum = lineNumList.get(lineIndex++);
            String dsOrdPosnNum = (String) lineNum.get("dsOrdPosnNum");
            NWAL1500_CSMsg rmaConfig = NWAL1500CommonLogic.getParentConfig(glblMsg.C, dsOrdPosnNum); // 2018/01/31 S21_NA#19808
            ZYPEZDItemValueSetter.setValue(rmaConfig.svcConfigMstrPk_RC, paramForLine.svcConfigMstrPk);
            // 2017/10/19 S21_NA#21843 Add Start
            setMdlInfoToRmaConfig(bizMsg, rmaConfig, paramForLine.svcConfigMstrPk.getValue());
            // 2017/10/19 S21_NA#21843 Add End
            int lineStartIndex = NWAL1500CommonLogicForPagination.getAddLineRowForGlobal(lineAllList, dsOrdPosnNum); // 2018/01/31 S21_NA#19808 Mod
            int maxLineNum = NWAL1500CommonLogicForPagination.getMaxLineNumForGlobal(lineAllList, dsOrdPosnNum); // 2018/01/31 S21_NA#19808 Mod
            int insertRow = lineStartIndex;
            int lineCount = 0;
            NWAL1500_DSMsg headerLine = new NWAL1500_DSMsg(); // 2016/11/10 S21_NA#10363-2 Add // 2018/01/31 S21_NA#19808
            for (int i = 0; i < paramForLine.NWZC182002PMsg.getValidCount(); i++) { // 2015/01/05 S21_NA#2585 Add
                NWZC182002PMsg component = paramForLine.NWZC182002PMsg.no(i); // 2015/01/05 S21_NA#2585 Add
                boolean isMatch = false;
                int size = new ORD_TAKE_MDSETMsg().getAttr("ordTakeMdseCd").getDigit();
                BigDecimal dbSvcMachMstrPk = component.svcMachMstrPk.getValue();
                String dbSerNum = component.serNum.getValue();
                String dbMdse = component.mdseCd.getValue();
                if(dbMdse.length() > size) {
                    dbMdse = dbMdse.substring(0, size);
                }
                for (int j = 0; j < glblMsg.D.getValidCount(); j++) { // 2018/01/31 S21_NA#19808
                    NWAL1500_DSMsg lineMsg = glblMsg.D.no(j); // 2018/01/31 S21_NA#19808

                    if (!dsOrdPosnNum.equals(lineMsg.dsOrdPosnNum_RL.getValue())) {
                        continue;
                    }
                    // Add Start 2019/11/15 QC#54515
                    if (ZYPCommonFunc.hasValue(lineMsg.ordLineStsCd_RL) //
                            && S21StringUtil.isEquals(ORD_LINE_STS.CANCELLED, lineMsg.ordLineStsCd_RL.getValue())) {
                        continue;
                    }
                    // Add End 2019/11/15 QC#54515

                    //is match by (MDSE + Ser#)
                    BigDecimal lineSvcMachMstrPk = lineMsg.svcMachMstrPk_RL.getValue();
                    String lineSerNum = lineMsg.serNum_RL.getValue();
                    String lineMdse = lineMsg.mdseCd_RL.getValue();
                    if(lineMdse.length() > size) {
                        lineMdse = lineMdse.substring(0, size);
                    }
                    //
                    if (!ZYPCommonFunc.hasValue(headerLine.rtrnRsnCd_RL) && ZYPCommonFunc.hasValue(lineMsg.rtrnRsnCd_RL)) {
                        ZYPEZDItemValueSetter.setValue(headerLine.rtrnRsnCd_RL, lineMsg.rtrnRsnCd_RL);
                    }
                    if (!ZYPCommonFunc.hasValue(headerLine.rtlWhCd_RL) && ZYPCommonFunc.hasValue(lineMsg.rtlWhCd_RL)) {
                        ZYPEZDItemValueSetter.setValue(headerLine.rtlWhCd_RL, lineMsg.rtlWhCd_RL);
                        ZYPEZDItemValueSetter.setValue(headerLine.rtlWhNm_RL, lineMsg.rtlWhNm_RL);
                        ZYPEZDItemValueSetter.setValue(headerLine.rtlSwhCd_RL, lineMsg.rtlSwhCd_RL);
                        ZYPEZDItemValueSetter.setValue(headerLine.rtlSwhNm_RL, lineMsg.rtlSwhNm_RL);
                        ZYPEZDItemValueSetter.setValue(headerLine.thirdPtyDspTpCd_RL, lineMsg.thirdPtyDspTpCd_RL);
                    }

                    //is match by (Machine Master ID)
                    if (ZYPCommonFunc.hasValue(lineSvcMachMstrPk) && dbSvcMachMstrPk.compareTo(lineSvcMachMstrPk) == 0) {
                        isMatch = true;
                        break;
                    }
                    //is match by (MDSE + Ser#)
                    if (!ZYPCommonFunc.hasValue(lineSvcMachMstrPk) && (dbMdse.equals(lineMdse)) && (dbSerNum.equals(lineSerNum))) {
                        ZYPEZDItemValueSetter.setValue(lineMsg.serNum_RL, component.serNum);
                        ZYPEZDItemValueSetter.setValue(lineMsg.svcMachMstrPk_RL, component.svcMachMstrPk);
                        setRtrnRsnWh(lineMsg, headerLine);
                        isMatch = true;
                        // 2018/08/21 S21_NA#26767 Add Start
                        if (ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_RL)) {
                            NWAL1500CommonLogicForLineConfig.defaultingRtrnRsn(bizMsg, lineMsg);
                        }
                        // 2018/08/21 S21_NA#26767 Add End
                        break;
                    }
                    //is match by (MDSE)
                    if (!ZYPCommonFunc.hasValue(lineSvcMachMstrPk) && dbMdse.equals(lineMdse)) {
                        ZYPEZDItemValueSetter.setValue(lineMsg.serNum_RL, component.serNum);
                        ZYPEZDItemValueSetter.setValue(lineMsg.svcMachMstrPk_RL, component.svcMachMstrPk);
                        setRtrnRsnWh(lineMsg, headerLine);
                        isMatch = true;
                        // 2018/08/21 S21_NA#26767 Add Start
                        if (ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_RL)) {
                            NWAL1500CommonLogicForLineConfig.defaultingRtrnRsn(bizMsg, lineMsg);
                        }
                        // 2018/08/21 S21_NA#26767 Add End
                        break;
                    }
                }
                if (isMatch) {
                    continue;
                }
                //prepare new line
                NWAL1500_DSMsg newLine = (NWAL1500_DSMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow); // 2018/01/31 S21_NA#19808
                if (newLine == null) {
                    return checkZeroFlg; // S21_NA#9104 Add void -> boolean
                }
                setRtrnRsnWh(newLine, headerLine);
                // Add Start 2017/02/01 QC#17257
                lineCount++;
                // Add End 2017/02/01 QC#17257
                if (!setUpLine(newLine, bizMsg, glblMsg, component, dsOrdPosnNum, maxLineNum + lineCount, insertRow++, null)) {
                    return checkZeroFlg; // S21_NA#9104 Add void -> boolean
                }
                MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), component.mdseCd.getValue());
                if (mdseTMsg != null && S21StringUtil.isEquals(mdseTMsg.mdseTpCd.getValue(), MDSE_TP.SET)) {
                    int lineSubNum = 0;
                    List<Map<String, Object>> childMdseList = NWAL1500CommonLogicForConfigId.getChildMdse(bizMsg, mdseTMsg.mdseCd.getValue());
                    for (Map<String, Object> childMdse : childMdseList) {
                        NWAL1500_DSMsg newChildLine = (NWAL1500_DSMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow); // 2018/01/31 S21_NA#19808
                        if (newLine == null) {
                            return checkZeroFlg; // S21_NA#9104 Add void -> boolean
                        }
                        BigDecimal baseEachQty = newLine.ordQty_RL.getValue();
                        // set up line
                        if (!setUpLine(newChildLine, bizMsg, glblMsg, childMdse, baseEachQty, dsOrdPosnNum, maxLineNum + lineCount, lineSubNum, insertRow++)) {
                            return checkZeroFlg; // S21_NA#9104 Add void -> boolean
                        }
                        MDSETMsg childMdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), (String) childMdse.get("MDSE_CD"));
                        if (mdseTMsg != null && S21StringUtil.isEquals(childMdseTMsg.mdseTpCd.getValue(), MDSE_TP.SET)) {
                            baseEachQty = newChildLine.ordQty_RL.getValue();
                            List<Map<String, Object>> grandChildMdseList = NWAL1500CommonLogicForConfigId.getChildMdse(bizMsg, childMdseTMsg.mdseCd.getValue());
                            if (grandChildMdseList.size() > 0) {
                                insertRow--;
                                List<Integer> deleteLisy = new ArrayList<Integer>();
                                deleteLisy.add(insertRow);
                                ZYPTableUtil.deleteRows(lineAllList, deleteLisy);
                            }
                            for (Map<String, Object> grandChildMdse : grandChildMdseList) {
                                NWAL1500_DSMsg newGrandChildLine = (NWAL1500_DSMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow); // 2018/01/31 S21_NA#19808
                                if (newGrandChildLine == null) {
                                    return checkZeroFlg; // S21_NA#9104 Add void -> boolean
                                }
                                if (!setUpLine(newGrandChildLine, bizMsg, glblMsg, grandChildMdse, baseEachQty, dsOrdPosnNum, maxLineNum + lineCount, lineSubNum++, insertRow++)) {
                                    return checkZeroFlg; // S21_NA#9104 Add void -> boolean
                                }
                            }
                        }
                    }
                }
                lineAddCnt++; // 2016/01/15 S21_NA#3153 Add
                // 2016/11/14 S21_NA#11434-2 Add End
            }
        }
        // 2016/01/15 S21_NA#3153 Add Start
        if (confAddCnt == 0 && lineAddCnt == 0) {
            return checkZeroFlg; // S21_NA#9104 Add void -> boolean
        }
        // 2016/01/15 S21_NA#3153 Add End
        printLog("********** After Line API Of Method doProcessAutoAddRMA ********");
        printDMsg(glblMsg.D);
        // QC743
        // printKMsg(glblMsg.K);

        List<String> preparedConfigNumList = new ArrayList<String>();
        for (String configNum : configNumList) {
            if (!preparedConfigNumList.contains(configNum)) {
                preparedConfigNumList.add(configNum);
                // QC743
                // NWAL1500CommonLogicForConfigId.prepareLineS2C(bizMsg.D, lineAllList, configNum, 0);
            }
        }
        for (Map<String, Object> lineNum : lineNumList) {
            if (!preparedConfigNumList.contains((String) lineNum.get("dsOrdPosnNum"))) {
                preparedConfigNumList.add((String) lineNum.get("dsOrdPosnNum"));
                // QC743
                // NWAL1500CommonLogicForConfigId.prepareLineS2C(bizMsg.D, lineAllList, (String) lineNum.get("dsOrdPosnNum"), 0);
            }
        }
        for (String preparedConfigNum : preparedConfigNumList) {
//            int slctConfIndex = NWAL1500CommonLogicForConfigId.getConfigIndex(bizMsg.C, preparedConfigNum); // 2018/01/31 S21_NA#19808
            NWAL1500_CSMsg rmaConfMsg = NWAL1500CommonLogic.getParentConfig(glblMsg.C, preparedConfigNum); // 2018/01/31 S21_NA#19808
            rmaConfMsg.xxSmryLineFlg_R.setValue(ZYPConstant.FLG_OFF_N);
        }
        // 2016/01/08 S21_NA#2726 Add Start
        boolean needCopyFlg = false;
        for (int slctLineIndex = 0; slctLineIndex < glblMsg.D.getValidCount(); slctLineIndex++) {
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(slctLineIndex);
            int slctConfIndex = 0;
            for (; slctConfIndex < glblMsg.C.getValidCount(); slctConfIndex++) {
                if (rmaLineMsg.dsOrdPosnNum_RL.getValue().equals(glblMsg.C.no(slctConfIndex).dsOrdPosnNum_RC.getValue())) {
                    break;
                }
            }

            if (!ZYPCommonFunc.hasValue(rmaLineMsg.entCpoDtlDealSlsAmt_RL)) {
                NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, slctConfIndex); // 2018/01/31 S21_NA#19808
                NWAL1500CommonLogicForLineConfig.setEachQty(bizMsg, glblMsg, slctLineIndex, rmaLineMsg.mdseCd_RL.getValue()); // 2018/01/31 S21_NA#19808
                NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
                // 2017/11/21 S21_NA#22555 Del Start
//                NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, slctConfIndex);
                // 2017/11/21 S21_NA#22555 Del End
                // QC#22372 2018/01/10 Add Start
                NWAL1500CommonLogicForLineConfig.deriveLineFloorPrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
                // QC#22372 2018/01/10 Add End
                needCopyFlg = true;
            }
        }
//        if (needCopyFlg) {
//            copyBizMsgToGlblMsg(bizMsg.D, glblMsg.K);
//        }

        // Add Start 2017/01/27 QC#17257
        if (posnNums.size() > 0) {
            NWAL1500CommonLogicForLineConfig.updateBaseCmptFlg(bizMsg, glblMsg, posnNums, false); // 2018/01/31 S21_NA#19808
        }
        // Add End 2017/01/27 QC#17257

        // 2018/01/31 S21_NA#19808 Add Start
        NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
        NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg, curPageNum);
        // 2018/01/31 S21_NA#19808 Add End

        // 2016/01/08 S21_NA#2726 Add End
        printLog("********** End Of Method doProcessAutoAddRMA ********");
        printDMsg(glblMsg.D);
//        printKMsg(glblMsg.K);

        return checkZeroFlg; // S21_NA#9104 Add void -> boolean
    }

    @SuppressWarnings("unchecked")
    private static boolean setUpLine(NWAL1500_DSMsg newLineP, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, Map<String, Object> childMdse, BigDecimal baseEachQty, String dsOrdPosnNum, int lineNum, int lineSubNum, int slctLineIndex) { // 2018/01/31 S21_NA#19808

        // 2018/01/31 S21_NA#19808 Add Start
        NWAL1500_DCMsg newLine = new NWAL1500_DCMsg();
        // 2018/01/31 S21_NA#19808 Add End
        ZYPEZDItemValueSetter.setValue(newLine.dsOrdPosnNum_RL, dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(newLine.dsCpoLineNum_RL, new BigDecimal(lineNum));
        ZYPEZDItemValueSetter.setValue(newLine.dsCpoLineSubNum_RL, new BigDecimal(lineSubNum));
        ZYPEZDItemValueSetter.setValue(newLine.xxLineNum_RL, NWAL1500CommonLogic.concatLineNum(newLine));

        ZYPEZDItemValueSetter.setValue(newLine.mdseCd_RL, (String) childMdse.get("MDSE_CD"));
        // ZYPEZDItemValueSetter.setValue(newLine.serNum_RL, component.serNum);
        ZYPEZDItemValueSetter.setValue(newLine.ordCustUomQty_RL, baseEachQty.multiply((BigDecimal) childMdse.get("CHILD_MDSE_QTY")));
        ZYPEZDItemValueSetter.setValue(newLine.ordQty_RL, baseEachQty.multiply((BigDecimal) childMdse.get("CHILD_MDSE_QTY")));

        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), (String) childMdse.get("MDSE_CD"));

        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getMdseInfo(bizMsg, mdseTMsg.mdseCd.getValue(), false);
        Map<String, String> mdseInfo = new HashMap<String, String>();
        if (!ssmResult.isCodeNotFound()) {
            mdseInfo = (Map<String, String>) ssmResult.getResultObject();
        }

        ZYPEZDItemValueSetter.setValue(newLine.mdseCd_RL, mdseTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(newLine.mdseNm_RL, mdseTMsg.mdseNm);
        ZYPEZDItemValueSetter.setValue(newLine.mdseDescShortTxt_RL, mdseInfo.get("MDSE_DESC_SHORT_TXT")); // S21_NA#2334 Add 
        NWAL1500CommonLogic.setUomPullDownForRma(bizMsg, newLine);
        ZYPEZDItemValueSetter.setValue(newLine.custUomCd_RL, mdseInfo.get("PKG_UOM_CD"));
        ZYPEZDItemValueSetter.setValue(newLine.cpoDtlDealGrsAmt_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.entDealNetUnitPrcAmt_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.prcCatgCd_RL, bizMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(newLine.prcCatgNm_RL, bizMsg.prcCatgNm);
        String primaryLineCatgRmaCd = NWAL1500CommonLogic.createLineCatgPulldownForRma(bizMsg, glblMsg, bizMsg.slsDt.getValue()); // 2018/01/31 S21_NA#19808 Mod
        NWAL1500CommonLogicForCategory.setInitValueForLineCatgPulldown(glblMsg, null, primaryLineCatgRmaCd); // 2018/01/31 S21_NA#19808 Mod
        // 2018/01/31 S21_NA#19808 Add Start
        for (int i = 0; i < newLine.dsOrdLineCatgCd_CR.length(); i++) {
            ZYPEZDItemValueSetter.setValue(newLine.dsOrdLineCatgCd_CR.no(i), glblMsg.D.no(slctLineIndex).dsOrdLineCatgCd_CR.no(i));
            ZYPEZDItemValueSetter.setValue(newLine.dsOrdLineCatgDescTxt_NR.no(i), glblMsg.D.no(slctLineIndex).dsOrdLineCatgDescTxt_NR.no(i));
        }
        ZYPEZDItemValueSetter.setValue(newLine.dsOrdLineCatgCd_RL, primaryLineCatgRmaCd);
        // 2018/01/31 S21_NA#19808 Add End
        newLine.rtrnLineStsDescTxt_RL.clear();

        // Call NWZC1800 Default WH API
        // 2018/01/31 S21_NA#19808 Mod Start
//        int slctConfIndex = NWAL1500CommonLogicForConfigId.getConfigIndex(bizMsg.C, dsOrdPosnNum);
//        NWAL1500_CCMsg rmaConfMsg = bizMsg.C.no(slctConfIndex);
        NWAL1500_CSMsg rmaConfMsg = NWAL1500CommonLogic.getParentConfig(glblMsg.C, dsOrdPosnNum);
        // 2018/01/31 S21_NA#19808 Mod End
        if (ZYPCommonFunc.hasValue(rmaConfMsg.shipToCustCd_RC)) {
            NWZC180001PMsg pMsg = callDefWhApiForRma(bizMsg, newLine);
            if (pMsg == null) {
                return false;
            }

            String rtlWhCd = pMsg.rtlWhCd.getValue();
            String rtlSwhCd = pMsg.rtlSwhCd.getValue();

            // 2016/11/10 S21_NA#10363-2 Mod Start
            if (!ZYPCommonFunc.hasValue(newLine.rtlWhCd_RL)) {
                ZYPEZDItemValueSetter.setValue(newLine.rtlWhCd_RL, rtlWhCd);
                ZYPEZDItemValueSetter.setValue(newLine.rtlWhNm_RL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
                ZYPEZDItemValueSetter.setValue(newLine.rtlSwhCd_RL, rtlSwhCd);
                ZYPEZDItemValueSetter.setValue(newLine.rtlSwhNm_RL, NWAL1500CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
                ZYPEZDItemValueSetter.setValue(newLine.thirdPtyDspTpCd_RL, pMsg.thirdPtyDspTpCd); // 2016/11/04 S21_NA#15703 Add
            }
            // 2016/11/10 S21_NA#10363-2 Mod End
        }

        newLine.serNum_RL.clear();
        ZYPEZDItemValueSetter.setValue(newLine.flPrcListCd_RL, bizMsg.flPrcListCd);
        ZYPEZDItemValueSetter.setValue(newLine.flPrcListNm_RL, bizMsg.flPrcListNm);
        ZYPEZDItemValueSetter.setValue(newLine.dealPrcListPrcAmt_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.cpoDtlDealTaxAmt_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.xxLineTotPrcAmt_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.coaMdseTpCd_RL, mdseInfo.get("COA_MDSE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(newLine.coaMdseTpDescTxt_RL, mdseInfo.get("COA_MDSE_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(newLine.coaProdCd_RL, mdseInfo.get("COA_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(newLine.coaProdDescTxt_RL, mdseInfo.get("COA_PROD_DESC_TXT"));
        newLine.dplyLineRefNum_RL.clear();
        ZYPEZDItemValueSetter.setValue(newLine.prcBaseDt_RL, bizMsg.slsDt);
        // ZYPEZDItemValueSetter.setValue(newLine.rqstPickUpDt_RL, bizMsg.slsDt); // S21_NA#9191 del
        ZYPEZDItemValueSetter.setValue(newLine.rqstPickUpDt_RL, bizMsg.addRddDt); // S21_NA#9191 add
        ZYPEZDItemValueSetter.setValue(newLine.rtrnQty_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.cancQty_RL, BigDecimal.ZERO);

//        NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, slctConfIndex);
//        NWAL1500CommonLogicForLineConfig.setEachQty(bizMsg, slctLineIndex, mdseTMsg.mdseCd.getValue());
//        NWAL1500CommonLogicForLineConfig.applyQtyToSetCmpt(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
//        NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
//        NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, slctConfIndex);

        // 2018/01/31 S21_NA#19808 Add Start
        EZDMsg.copy(newLine, null, newLineP, null);
        // 2018/01/31 S21_NA#19808 Add End
        return true;
    }

    @SuppressWarnings("unchecked")
    private static boolean setUpLine(NWAL1500_BSMsg newLineP, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_RCMsg configIdMsg, String dsOrdPosnNum, int lineNum, int slctLineIndex) { // 2018/01/31 S21_NA#19808

        // 2018/01/31 S21_NA#19808 Add Start
        NWAL1500_BCMsg newLine = new NWAL1500_BCMsg();
        // 2018/01/31 S21_NA#19808 Ad End
        ZYPEZDItemValueSetter.setValue(newLine.dsOrdPosnNum_LL, dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(newLine.dsCpoLineNum_LL, new BigDecimal(lineNum));
        ZYPEZDItemValueSetter.setValue(newLine.xxLineNum_LL, NWAL1500CommonLogic.concatLineNum(newLine));

        ZYPEZDItemValueSetter.setValue(newLine.mdseCd_LL, configIdMsg.mdseCd_O);
        ZYPEZDItemValueSetter.setValue(newLine.serNum_LL, configIdMsg.serNum_O);
        ZYPEZDItemValueSetter.setValue(newLine.svcMachMstrPk_LL, configIdMsg.svcMachMstrPk_O);
        ZYPEZDItemValueSetter.setValue(newLine.ordCustUomQty_LL, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(newLine.ordQty_RL, BigDecimal.ZERO.subtract(BigDecimal.ONE));

        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), configIdMsg.mdseCd_O.getValue());

        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getMdseInfo(bizMsg, mdseTMsg.mdseCd.getValue(), false);
        Map<String, String> mdseInfo = new HashMap<String, String>();
        if (!ssmResult.isCodeNotFound()) {
            mdseInfo = (Map<String, String>) ssmResult.getResultObject();
        }

        ZYPEZDItemValueSetter.setValue(newLine.mdseCd_LL, mdseTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(newLine.mdseNm_LL, mdseTMsg.mdseNm);
        ZYPEZDItemValueSetter.setValue(newLine.mdseDescShortTxt_LL, mdseInfo.get("MDSE_DESC_SHORT_TXT")); // S21_NA#2334 Add 
        NWAL1500CommonLogic.setUomPullDownForConfLine(bizMsg, newLine);
        ZYPEZDItemValueSetter.setValue(newLine.custUomCd_LL, mdseInfo.get("PKG_UOM_CD"));

        ZYPEZDItemValueSetter.setValue(newLine.ordQty_LL, NWAL1500CommonLogicForLineConfig.getEachQty(bizMsg, configIdMsg.mdseCd_O.getValue(), newLine.custUomCd_LL.getValue(), newLine.ordCustUomQty_LL.getValue()));

        ZYPEZDItemValueSetter.setValue(newLine.cpoDtlDealGrsAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.entDealNetUnitPrcAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.prcCatgCd_LL, bizMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(newLine.prcCatgNm_LL, bizMsg.prcCatgNm);
        String primaryLineCatgRmaCd = NWAL1500CommonLogic.createLineCatgPulldown(bizMsg, bizMsg.slsDt.getValue());
        NWAL1500CommonLogicForCategory.setInitValueForLineCatgPulldown(glblMsg, primaryLineCatgRmaCd, primaryLineCatgRmaCd); // 2018/01/31 S21_NA#19808 Mod
        newLine.ordLineStsDescTxt_LL.clear();

        // 2018/06/07 S21_NA#22988-2 Add Start
        NWAL1500_ASMsg confMsg = NWAL1500CommonLogic.getParentConfig(glblMsg.A, dsOrdPosnNum);
        boolean isToSales = SVC_MACH_MSTR_STS.INSTALLED.equals(configIdMsg.svcMachMstrStsCd_O.getValue()) //
            || SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(configIdMsg.svcMachMstrStsCd_O.getValue());
        isToSales = isToSales & NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), //
                confMsg.configTpCd_LC.getValue(), //
                CONFIG_CATG.OUTBOUND, //
                false, true, false);
        // 2018/06/07 S21_NA#22988-2 Add End
        if (!isToSales) { // 2018/06/07 S21_NA#22988-2 Add Condition
            // Call NWZC1800 Default WH API
            if (ZYPCommonFunc.hasValue(configIdMsg.curLocNum_O)) {
                RTL_SWHTMsg rtlSwhTMsg = NWAL1500QueryForAutoAdd.getInstance().getRtlSwhRecByInvtyLocCd(bizMsg.glblCmpyCd.getValue(), configIdMsg.curLocNum_O.getValue());
                if (null != rtlSwhTMsg) {
                    ZYPEZDItemValueSetter.setValue(newLine.rtlWhCd_LL, rtlSwhTMsg.rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(newLine.rtlWhNm_LL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlSwhTMsg.rtlWhCd.getValue()));
                    ZYPEZDItemValueSetter.setValue(newLine.rtlSwhCd_LL, rtlSwhTMsg.rtlSwhCd);
                    ZYPEZDItemValueSetter.setValue(newLine.rtlSwhNm_LL, rtlSwhTMsg.rtlSwhNm);
                    newLine.ordLineSrcCd_LL.setValue(ORD_LINE_SRC.INTERNAL);
                } else {
                    // 2018/01/31 S21_NA#19808 Mod Start
    //                int slctConfIndex = NWAL1500CommonLogicForConfigId.getConfigIndex(bizMsg.A, dsOrdPosnNum);
    //                NWAL1500_ACMsg confMsg = bizMsg.A.no(slctConfIndex);
                    // 2018/06/07 S21_NA#22988-2 Del Start
    //                NWAL1500_ASMsg confMsg = NWAL1500CommonLogic.getParentConfig(glblMsg.A, dsOrdPosnNum);
                    // 2018/06/07 S21_NA#22988-2 Del End
                    // 2018/01/31 S21_NA#19808 Mod End
                    if (ZYPCommonFunc.hasValue(confMsg.shipToCustCd_LC)) {
                        // 2018/09/20 S21_NA#28199 Del Start
//                        NWZC180001PMsg pMsg = new NWZC180001PMsg();
//                        if (NWAL1500CommonLogic.callDefWhApiForLineConf(pMsg, bizMsg, newLine.dsOrdPosnNum_LL.getValue(), newLine.mdseCd_LL.getValue(), BigDecimal.ZERO, configIdMsg.svcMachMstrPk_O.getValue())) {
//
//                            String rtlWhCd = pMsg.rtlWhCd.getValue();
//                            String rtlSwhCd = pMsg.rtlSwhCd.getValue();
//
//                            ZYPEZDItemValueSetter.setValue(newLine.rtlWhCd_LL, rtlWhCd);
//                            ZYPEZDItemValueSetter.setValue(newLine.rtlWhNm_LL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
//                            ZYPEZDItemValueSetter.setValue(newLine.rtlSwhCd_LL, rtlSwhCd);
//                            ZYPEZDItemValueSetter.setValue(newLine.rtlSwhNm_LL, NWAL1500CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
//                            ZYPEZDItemValueSetter.setValue(newLine.ordLineSrcCd_LL, pMsg.ordLineSrcCd);
//                        }
                        // 2018/09/20 S21_NA#28199 Del End
                        // 2018/09/20 S21_NA#28199 Add Start
                        List<NWZC180001PMsg> pMsgList = new ArrayList<NWZC180001PMsg>(0);
                        NWAL1500_BSMsg forApiLineMsg = new NWAL1500_BSMsg();
                        EZDMsg.copy(newLine, null, forApiLineMsg, null);
                        forApiLineMsg.ordQty_LL.setValue(BigDecimal.ZERO);
                        // 2018/09/26 S21_NA#28482 Mod Start
                        boolean ret = false;
                        boolean skipCheck = false;
                        if (SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(configIdMsg.svcMachMstrStsCd_O.getValue())){
                            skipCheck = true;
                            if (NWAL1500CommonLogic.callDefWhApiForRma(pMsgList, bizMsg, glblMsg, newLine.dsOrdPosnNum_LL.getValue(), newLine.mdseCd_LL.getValue(), BigDecimal.ZERO, configIdMsg.svcMachMstrPk_O.getValue())) {
                                ret = true;
                            }
                            newLine.xxYesNoCd_LL.setValue(ZYPConstant.FLG_OFF_N);
                        } else{
                            if (NWAL1500CommonLogic.callDefWhApiForLineConf(pMsgList, bizMsg, glblMsg, forApiLineMsg.dsOrdPosnNum_LL.getValue(), forApiLineMsg)) {
                                ret = true;
                            }
                        }
                        // if (NWAL1500CommonLogic.callDefWhApiForLineConf(pMsgList, bizMsg, glblMsg, forApiLineMsg.dsOrdPosnNum_LL.getValue(), forApiLineMsg)) {
                        if (ret) {
                            // 2018/09/26 S21_NA#28482 Mod End
                            for (NWZC180001PMsg pMsg : pMsgList) {
                                if (S21StringUtil.isEquals(forApiLineMsg.xxLineNum_LL.getValue(), pMsg.xxLineNum.getValue()) || skipCheck) {
                                  String rtlWhCd = pMsg.rtlWhCd.getValue();
                                  String rtlSwhCd = pMsg.rtlSwhCd.getValue();

                                  ZYPEZDItemValueSetter.setValue(newLine.rtlWhCd_LL, rtlWhCd);
                                  ZYPEZDItemValueSetter.setValue(newLine.rtlWhNm_LL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
                                  ZYPEZDItemValueSetter.setValue(newLine.rtlSwhCd_LL, rtlSwhCd);
                                  ZYPEZDItemValueSetter.setValue(newLine.rtlSwhNm_LL, NWAL1500CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
                                  ZYPEZDItemValueSetter.setValue(newLine.ordLineSrcCd_LL, pMsg.ordLineSrcCd);
                                  // 2018/09/26 S21_NA#28482 Add Start
                                  if (skipCheck && !ZYPCommonFunc.hasValue(newLine.ordLineSrcCd_LL)) {
                                      ZYPEZDItemValueSetter.setValue(newLine.ordLineSrcCd_LL, ORD_LINE_SRC.INTERNAL);
                                  }
                                  // 2018/09/26 S21_NA#28482 Add End
                                    break;
                                }
                            }
                        }
                        // 2018/09/20 S21_NA#28199 Add End
                        // 2018/09/27 S21_NA#28482 Add Start
                        if (!ZYPCommonFunc.hasValue(newLine.ordLineSrcCd_LL)) {
                            ZYPEZDItemValueSetter.setValue(newLine.ordLineSrcCd_LL, ORD_LINE_SRC.INTERNAL);
                        }
                        // 2018/09/27 S21_NA#28482 Add End
                    }
                }
            }
        } // 2018/06/07 S21_NA#22988-2 Add Condition

        // newLine.serNum_RL.clear(); 2016/01/08 S21_NA#2726 Del
        ZYPEZDItemValueSetter.setValue(newLine.flPrcListCd_LL, bizMsg.flPrcListCd);
        ZYPEZDItemValueSetter.setValue(newLine.flPrcListNm_LL, bizMsg.flPrcListNm);
        ZYPEZDItemValueSetter.setValue(newLine.dealPrcListPrcAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.cpoDtlDealTaxAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.xxLineTotPrcAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.coaMdseTpCd_LL, mdseInfo.get("COA_MDSE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(newLine.coaMdseTpDescTxt_LL, mdseInfo.get("COA_MDSE_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(newLine.coaProdCd_LL, mdseInfo.get("COA_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(newLine.coaProdDescTxt_LL, mdseInfo.get("COA_PROD_DESC_TXT"));
        newLine.dplyLineRefNum_LL.clear();
        ZYPEZDItemValueSetter.setValue(newLine.prcBaseDt_LL, bizMsg.slsDt);
//        ZYPEZDItemValueSetter.setValue(newLine.rqstPickUpDt_LL, bizMsg.slsDt);
//        ZYPEZDItemValueSetter.setValue(newLine.rtrnQty_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.cancQty_LL, BigDecimal.ZERO);

//        NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, slctConfIndex);
//        NWAL1500CommonLogicForLineConfig.setEachQty(bizMsg, slctLineIndex, mdseTMsg.mdseCd.getValue());
//        NWAL1500CommonLogicForLineConfig.applyQtyToSetCmpt(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
//        NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
//        NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, slctConfIndex);
        ZYPEZDItemValueSetter.setValue(newLine.supdLockFlg_LL, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(newLine.rddDt_LL, bizMsg.slsDt);

        // 2018/08/02 S21_NA#26665 add start
        CPO_SRC_TPTMsg cpoSrcTpTMsg = new CPO_SRC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.cpoSrcTpCd, CPO_SRC_TP.ORDER_ENTRY_SCREEN);

        cpoSrcTpTMsg = (CPO_SRC_TPTMsg) S21CodeTableAccessor.findByKey(cpoSrcTpTMsg);
        ZYPEZDItemValueSetter.setValue(newLine.cpoSrcTpDescTxt_LL, cpoSrcTpTMsg.cpoSrcTpDescTxt);
        // 2018/08/02 S21_NA#26665 add end

        // 2018/09/14 S21_NA#28225 Add Start
        ZYPEZDItemValueSetter.setValue(newLine.dsOrdLineCatgCd_LL, bizMsg.dsOrdLineCatgCd_LD);
        // 2018/09/14 S21_NA#28225 Add End
        // 2016/06/07 S21_NA#9277 Add Start
        // 2018/06/07 S21_NA#22988-2 Mod Start
//        if (SVC_MACH_MSTR_STS.INSTALLED.equals(configIdMsg.svcMachMstrStsCd_O.getValue())//
//                || SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(configIdMsg.svcMachMstrStsCd_O.getValue())) {
        if (isToSales) {
            // 2018/06/07 S21_NA#22988-2 Mod End
            String rtlWhCd = ZYPCodeDataUtil.getVarCharConstValue(NWAL1500_RENTAL_CONV_DEFWH, bizMsg.glblCmpyCd.getValue());
            if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                ZYPEZDItemValueSetter.setValue(newLine.rtlWhCd_LL, rtlWhCd);
                ZYPEZDItemValueSetter.setValue(newLine.rtlWhNm_LL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
                newLine.rtlSwhCd_LL.clear();
                newLine.rtlSwhNm_LL.clear();
            }
            // 2018/05/17 S21_NA#22988 Add Start
            String defLineCatgCd =  ZYPCodeDataUtil.getVarCharConstValue(NWAL1500_RENTAL_CONV_DEFLNCATG, bizMsg.glblCmpyCd.getValue());
            if (ZYPCommonFunc.hasValue(defLineCatgCd) //
                && isAvalLineCatgForDefWh(bizMsg.glblCmpyCd.getValue(), rtlWhCd, defLineCatgCd)
                && isIncludedLineConfig(bizMsg, defLineCatgCd)) {
                    newLine.dsOrdLineCatgCd_LL.setValue(defLineCatgCd);
            }
            // 2018/05/17 S21_NA#22988 Add End
            // 2016/06/27 S21_NA#10841 Add Start
            // if (!ZYPCommonFunc.hasValue(newLine.ordLineSrcCd_LL)) { 2018/06/07 S21_NA#22988-2 Del Condition
            String ordLineSrcCd = ZYPCodeDataUtil.getVarCharConstValue(NWAL1500_RENTAL_CONV_DEFLNSRC, bizMsg.glblCmpyCd.getValue());
            // 2018/06/07 S21_NA#22988-2 Add Start
            if (!ZYPCommonFunc.hasValue(ordLineSrcCd)) {
                ordLineSrcCd = ORD_LINE_SRC.INTERNAL;
            }
            // 2018/06/07 S21_NA#22988-2 Add End
            ZYPEZDItemValueSetter.setValue(newLine.ordLineSrcCd_LL, ordLineSrcCd);
            // } 2018/06/07 S21_NA#22988-2 Del Condition
            // 2016/06/27 S21_NA#10841 Add End
        }
        // 2016/06/07 S21_NA#9277 Add End
        // 2018/08/21 S21_NA#26767 Add Start
        NWAL1500CommonLogicForLineConfig.defaultingLineCateg(bizMsg, confMsg, newLine);
        // 2018/08/21 S21_NA#26767 Add End
        // 2018/01/31 S21_NA#19808 Add Start
        EZDMsg.copy(newLine, null, newLineP, null);
        // 2018/01/31 S21_NA#19808 Add End
        return true;
    }

    @SuppressWarnings("unchecked")
    private static boolean setUpLine(NWAL1500_DSMsg newLineP, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWZC182002PMsg component, String dsOrdPosnNum, int lineNum, int slctLineIndex, NWAL1500_RCMsg configIdMsg) {  // 2018/01/31 S21_NA#19808

        // 2018/01/31 S21_NA#19808 Add Start
        NWAL1500_DCMsg newLine = new NWAL1500_DCMsg();
        // 2018/01/31 S21_NA#19808 Add End

        ZYPEZDItemValueSetter.setValue(newLine.dsOrdPosnNum_RL, dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(newLine.dsCpoLineNum_RL, new BigDecimal(lineNum));
        ZYPEZDItemValueSetter.setValue(newLine.xxLineNum_RL, NWAL1500CommonLogic.concatLineNum(newLine));

        String mdseCd = "";
        String serNum = "";
        BigDecimal svcMachMstrPk = null; // 2016/04/22 S21_NA#7449 Add Start
        if (null == configIdMsg) {
            mdseCd = component.mdseCd.getValue();
            serNum = component.serNum.getValue();
            svcMachMstrPk = component.svcMachMstrPk.getValue(); // S21_NA#5340
        } else {
            mdseCd = configIdMsg.mdseCd_O.getValue();
            serNum = configIdMsg.serNum_O.getValue();
            svcMachMstrPk = configIdMsg.svcMachMstrPk_O.getValue(); // 2016/04/22 S21_NA#7449 Add Start
        }
        ZYPEZDItemValueSetter.setValue(newLine.mdseCd_RL, mdseCd);
        ZYPEZDItemValueSetter.setValue(newLine.serNum_RL, serNum);
        ZYPEZDItemValueSetter.setValue(newLine.ordCustUomQty_RL, BigDecimal.ZERO.subtract(BigDecimal.ONE));
//        ZYPEZDItemValueSetter.setValue(newLine.ordQty_RL, BigDecimal.ZERO.subtract(BigDecimal.ONE));

        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), mdseCd);

        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getMdseInfo(bizMsg, mdseTMsg.mdseCd.getValue(), false);
        Map<String, String> mdseInfo = new HashMap<String, String>();
        if (!ssmResult.isCodeNotFound()) {
            mdseInfo = (Map<String, String>) ssmResult.getResultObject();
        }

        ZYPEZDItemValueSetter.setValue(newLine.mdseCd_RL, mdseTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(newLine.mdseNm_RL, mdseTMsg.mdseNm);
        ZYPEZDItemValueSetter.setValue(newLine.mdseDescShortTxt_RL, mdseInfo.get("MDSE_DESC_SHORT_TXT")); // S21_NA#2334 Add 
        NWAL1500CommonLogic.setUomPullDownForRma(bizMsg, newLine);
        ZYPEZDItemValueSetter.setValue(newLine.custUomCd_RL, mdseInfo.get("PKG_UOM_CD"));
        ZYPEZDItemValueSetter.setValue(newLine.cpoDtlDealGrsAmt_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.entDealNetUnitPrcAmt_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.prcCatgCd_RL, bizMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(newLine.prcCatgNm_RL, bizMsg.prcCatgNm);
        String primaryLineCatgRmaCd = NWAL1500CommonLogic.createLineCatgPulldownForRma(bizMsg, glblMsg, bizMsg.slsDt.getValue()); // 2018/01/31 S21_NA#19808 Mod
        NWAL1500CommonLogicForCategory.setInitValueForLineCatgPulldown(glblMsg, null, primaryLineCatgRmaCd); // 2018/01/31 S21_NA#19808 Mod
        // 2018/01/30 S21_NA#19808 Add Start
        for (int i = 0; i < glblMsg.D.no(slctLineIndex).dsOrdLineCatgCd_CR.length(); i++) {
            ZYPEZDItemValueSetter.setValue(newLine.dsOrdLineCatgCd_CR.no(i), glblMsg.D.no(slctLineIndex).dsOrdLineCatgCd_CR.no(i));
            ZYPEZDItemValueSetter.setValue(newLine.dsOrdLineCatgDescTxt_NR.no(i), glblMsg.D.no(slctLineIndex).dsOrdLineCatgDescTxt_NR.no(i));
        }
        ZYPEZDItemValueSetter.setValue(newLine.dsOrdLineCatgCd_RL, primaryLineCatgRmaCd);
        // 2018/01/30 S21_NA#19808 Add End
        newLine.rtrnLineStsDescTxt_RL.clear();
        ZYPEZDItemValueSetter.setValue(newLine.svcMachMstrPk_RL, svcMachMstrPk); // 2016/04/22 S21_NA#7449 Add Start

        // Call NWZC1800 Default WH API
        // 2018/01/31 S21_NA#19808 Mod Start
//        int slctConfIndex = NWAL1500CommonLogic.getConfigIndex(glblMsg.C, dsOrdPosnNum);  // 2018/01/31 S21_NA#19808
//        NWAL1500_CCMsg rmaConfMsg = bizMsg.C.no(slctConfIndex);
        NWAL1500_CSMsg rmaConfMsg = NWAL1500CommonLogic.getParentConfig(glblMsg.C, dsOrdPosnNum);
        // 2018/01/31 S21_NA#19808 Mod End
        // 2018/08/21 S21_NA#26767 Add Start
        NWAL1500CommonLogicForLineConfig.defaultingRtrnRsn(bizMsg, newLine);
        // 2018/08/21 S21_NA#26767 Add End
        if (ZYPCommonFunc.hasValue(rmaConfMsg.shipToCustCd_RC)) {
            NWZC180001PMsg pMsg = callDefWhApiForRma(bizMsg, newLine);
            if (pMsg != null) {
                String rtlWhCd = pMsg.rtlWhCd.getValue();
                String rtlSwhCd = pMsg.rtlSwhCd.getValue();

                // 2016/11/10 S21_NA#10363-2 Mod Start
                if (!ZYPCommonFunc.hasValue(newLine.rtlWhCd_RL)) {
                    ZYPEZDItemValueSetter.setValue(newLine.rtlWhCd_RL, rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(newLine.rtlWhNm_RL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
                    ZYPEZDItemValueSetter.setValue(newLine.rtlSwhCd_RL, rtlSwhCd);
                    ZYPEZDItemValueSetter.setValue(newLine.rtlSwhNm_RL, NWAL1500CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
                    ZYPEZDItemValueSetter.setValue(newLine.thirdPtyDspTpCd_RL, pMsg.thirdPtyDspTpCd); // 2016/11/04 S21_NA#15703 Add
                }
                // 2016/11/10 S21_NA#10363-2 Mod End
            }
        }

        // newLine.serNum_RL.clear(); 2016/01/08 S21_NA#2726 Del
        ZYPEZDItemValueSetter.setValue(newLine.flPrcListCd_RL, bizMsg.flPrcListCd);
        ZYPEZDItemValueSetter.setValue(newLine.flPrcListNm_RL, bizMsg.flPrcListNm);
        ZYPEZDItemValueSetter.setValue(newLine.dealPrcListPrcAmt_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.cpoDtlDealTaxAmt_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.xxLineTotPrcAmt_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.coaMdseTpCd_RL, mdseInfo.get("COA_MDSE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(newLine.coaMdseTpDescTxt_RL, mdseInfo.get("COA_MDSE_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(newLine.coaProdCd_RL, mdseInfo.get("COA_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(newLine.coaProdDescTxt_RL, mdseInfo.get("COA_PROD_DESC_TXT"));
        newLine.dplyLineRefNum_RL.clear();
        ZYPEZDItemValueSetter.setValue(newLine.prcBaseDt_RL, bizMsg.slsDt);
        //ZYPEZDItemValueSetter.setValue(newLine.rqstPickUpDt_RL, bizMsg.slsDt); // S21_NA#9191 del
        ZYPEZDItemValueSetter.setValue(newLine.rqstPickUpDt_RL, bizMsg.addRddDt); // S21_NA#9191 add
        ZYPEZDItemValueSetter.setValue(newLine.rtrnQty_RL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.cancQty_RL, BigDecimal.ZERO);
        // 2018/08/02 S21_NA#26665 add start
        CPO_SRC_TPTMsg cpoSrcTpTMsg = new CPO_SRC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.cpoSrcTpCd, CPO_SRC_TP.ORDER_ENTRY_SCREEN);

        cpoSrcTpTMsg = (CPO_SRC_TPTMsg) S21CodeTableAccessor.findByKey(cpoSrcTpTMsg);
        ZYPEZDItemValueSetter.setValue(newLine.cpoSrcTpDescTxt_RL, cpoSrcTpTMsg.cpoSrcTpDescTxt);
        // 2018/08/02 S21_NA#26665 add end

//        NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, slctConfIndex);
//        NWAL1500CommonLogicForLineConfig.setEachQty(bizMsg, slctLineIndex, mdseTMsg.mdseCd.getValue());
//        NWAL1500CommonLogicForLineConfig.applyQtyToSetCmpt(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
//        NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
//        NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, slctConfIndex);
        // QC#55546 2020/01/22 Del Start
        //// 2018/08/07 S21_NA#26414-1 add start
        //if (NWXC150001DsCheck.isAvalOrderCtxType( //
        //        bizMsg.glblCmpyCd.getValue(), //
        //        ORD_CATG_CTX_TP.SERVICE_EXCHANGE_ORDER_VALUE_SET, //
        //        bizMsg.dsOrdCatgCd.getValue(), //
        //        bizMsg.dsOrdTpCd.getValue(), //
        //        bizMsg.dsOrdRsnCd.getValue())) {
        //    String defaultRsnCd = ZYPCodeDataUtil.getVarCharConstValue(SERVICE_EXCHANGE_RTRN_RSN_CD, bizMsg.glblCmpyCd.getValue());
        //    if (ZYPCommonFunc.hasValue(defaultRsnCd)) {
        //        newLine.rtrnRsnCd_RL.setValue(defaultRsnCd);
        //    }
        //}
        //// 2018/08/07 S21_NA#26414-1 add end
        // QC#55546 2020/01/22 Del End
        // 2018/01/31 S21_NA#19808 Add Start
        EZDMsg.copy(newLine, null, newLineP, null);
        // 2018/01/31 S21_NA#19808 Add End
        return true;
    }

    private static NWZC182001PMsg setupApiParam(NWAL1500CMsg bizMsg, NWAL1500_CSMsg config) { // 2018/01/31 S21_NA#19808 Mod

        NWZC182001PMsg param = new NWZC182001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.svcConfigMstrPk, config.svcConfigMstrPk_RC);
        ZYPEZDItemValueSetter.setValue(param.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(param.dsOrdTpCd, bizMsg.dsOrdTpCd);

        return param;
    }

    private static NWZC182001PMsg setupApiParam(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_DSMsg line) { // 2018/01/31 S21_NA#19808

        NWZC182001PMsg param = new NWZC182001PMsg();
        NWAL1500_CSMsg config = NWAL1500CommonLogic.getParentConfig(glblMsg.C, line.dsOrdPosnNum_RL.getValue()); // 2016/11/14 S21_NA#11434-2 Add // 2018/01/31 S21_NA#19808

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.svcConfigMstrPk, config.svcConfigMstrPk_RC); // 2016/11/14 S21_NA#11434-2 Add
        ZYPEZDItemValueSetter.setValue(param.serNum, line.serNum_RL);
        // 2017/03/02 QC#16575 ADD START
        ZYPEZDItemValueSetter.setValue(param.mdseCd, line.mdseCd_RL);
        // 2017/03/02 QC#16575 ADD E N D
        ZYPEZDItemValueSetter.setValue(param.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(param.dsOrdTpCd, bizMsg.dsOrdTpCd);

        return param;
    }

    /**
     * Call NWZC1800 Default WH API For RMA
     * @param bizMsg NWAL1500CMsg
     * @param rmaLineMsg NWAL1500_DCMsg
     * @return NWZC180001PMsg
     */
    public static NWZC180001PMsg callDefWhApiForRma(NWAL1500CMsg bizMsg, NWAL1500_DCMsg rmaLineMsg) { // 2018/01/31 S21_NA#19808

        NWZC180001PMsg pMsg = new NWZC180001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC180001Constant.PROC_MODE_INBD);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsRtrnRsnCd, rmaLineMsg.rtrnRsnCd_RL);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, rmaLineMsg.mdseCd_RL);
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, getConfPostCd(bizMsg, rmaLineMsg.dsOrdPosnNum_RL.getValue(), false));
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, rmaLineMsg.ordQty_RL);
        // Add Start 2017/01/30 QC#17186
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, rmaLineMsg.svcMachMstrPk_RL.getValue());
        // Add End 2017/01/30 QC#17186

        if (!ZYPCommonFunc.hasValue(pMsg.dsRtrnRsnCd)) {
            String rtrnRsnCd = "";
            for (int i = 0; i < bizMsg.rtrnRsnCd_CD.length(); i++) {
                if (ZYPCommonFunc.hasValue(bizMsg.rtrnRsnCd_CD.no(i))) {
                    rtrnRsnCd = bizMsg.rtrnRsnCd_CD.no(i).getValue();
                    // Add Start 2017/02/01 QC#17257
                    ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtrnRsnCd_RL, rtrnRsnCd);
                    // Add End 2017/02/01 QC#17257
                    break;
                }
            }
            ZYPEZDItemValueSetter.setValue(pMsg.dsRtrnRsnCd, rtrnRsnCd);
        }
        // call NWZC180001 Dafault Rtl Warehouse API
        new NWZC180001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, msgId, msgPrms);
        }

        return pMsg;
    }

    /**
     * Get Config Post Code
     * @param bizMsg NWAL1500CMsg
     * @param dsOrdPosnNum DS Order Position Number
     * @param isLineConfFlag Called Line Config
     */
    private static String getConfPostCd(NWAL1500CMsg bizMsg, String dsOrdPosnNum, boolean isLineConfFlag) {

        if (isLineConfFlag) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NWAL1500_ACMsg aCMsg = bizMsg.A.no(i);
                if (dsOrdPosnNum.equals(aCMsg.dsOrdPosnNum_LC.getValue())) {
                    return aCMsg.shipToPostCd_LC.getValue();
                }
            }
        } else {
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                NWAL1500_CCMsg cCMsg = bizMsg.C.no(i);
                if (dsOrdPosnNum.equals(cCMsg.dsOrdPosnNum_RC.getValue())) {
                    return cCMsg.shipToPostCd_RC.getValue();
                }
            }
        }

        return null;
    }

    private static void setAutoAddRmaErr(NWZC182001PMsg paramForConfig, NWAL1500_CSMsgArray rmaConfigMsgArray) {  // 2018/01/31 S21_NA#19808 Mod
        String firstMsgId = paramForConfig.xxMsgIdList.no(0).xxMsgId.getValue();
        BigDecimal configNum = paramForConfig.svcConfigMstrPk.getValue();
        for (int i = 0; i < rmaConfigMsgArray.getValidCount(); i++) {
            NWAL1500_CSMsg rmaConfigMsg = rmaConfigMsgArray.no(i);
            if (ZYPCommonFunc.hasValue(rmaConfigMsg.svcConfigMstrPk_RC) && configNum.compareTo(rmaConfigMsg.svcConfigMstrPk_RC.getValue()) == 0) {
                rmaConfigMsg.svcConfigMstrPk_RC.setErrorInfo(1, firstMsgId);
            }
        }
        return;
    }

    // 2018/01/31 S21_NA#19808 Del Start
    // 2016/01/12 S21_NA#2726 Add Start
//    private static void copyBizMsgToGlblMsg(NWAL1500_BCMsgArray bizLineMsgArray, NWAL1500_BCMsgArray glblLineMsgArray) {
//        for (int bizIdx = 0; bizIdx < bizLineMsgArray.getValidCount(); bizIdx++) {
//            String dsOrdPosnNum = bizLineMsgArray.no(bizIdx).dsOrdPosnNum_LL.getValue();
//            BigDecimal dsCpoLineNum = bizLineMsgArray.no(bizIdx).dsCpoLineNum_LL.getValue();
//            String dsCpoLineSubNumStr = String.valueOf(bizLineMsgArray.no(bizIdx).dsCpoLineSubNum_LL.getValue());
//            for (int glblIdx = 0; glblIdx < glblLineMsgArray.getValidCount(); glblIdx++) {
//                String glblDsOrdPosnNum = glblLineMsgArray.no(glblIdx).dsOrdPosnNum_LL.getValue();
//                BigDecimal glblDsCpoLineNum = glblLineMsgArray.no(glblIdx).dsCpoLineNum_LL.getValue();
//                String glblDsCpoLineSubNumString = String.valueOf(glblLineMsgArray.no(glblIdx).dsCpoLineSubNum_LL.getValue());
//
//                if (dsOrdPosnNum.equals(glblDsOrdPosnNum)
//                        && dsCpoLineNum.compareTo(glblDsCpoLineNum) == 0
//                        && dsCpoLineSubNumStr.equals(glblDsCpoLineSubNumString)) {
//                    EZDMsg.copy(bizLineMsgArray.no(bizIdx), null, glblLineMsgArray.no(glblIdx), null);
//                    break;
//                }
//            }
//        }
//    }
//
//    private static void copyBizMsgToGlblMsg(NWAL1500_DCMsgArray bizRmaLineMsgArray, NWAL1500_DCMsgArray glblRmaLineMsgArray) {
//        for (int bizIdx = 0; bizIdx < bizRmaLineMsgArray.getValidCount(); bizIdx++) {
//            String dsOrdPosnNum = bizRmaLineMsgArray.no(bizIdx).dsOrdPosnNum_RL.getValue();
//            BigDecimal dsCpoLineNum = bizRmaLineMsgArray.no(bizIdx).dsCpoLineNum_RL.getValue();
//            String dsCpoLineSubNumStr = String.valueOf(bizRmaLineMsgArray.no(bizIdx).dsCpoLineSubNum_RL.getValue());
//            for (int glblIdx = 0; glblIdx < glblRmaLineMsgArray.getValidCount(); glblIdx++) {
//                String glblDsOrdPosnNum = glblRmaLineMsgArray.no(glblIdx).dsOrdPosnNum_RL.getValue();
//                BigDecimal glblDsCpoLineNum = glblRmaLineMsgArray.no(glblIdx).dsCpoLineNum_RL.getValue();
//                String glblDsCpoLineSubNumString = String.valueOf(glblRmaLineMsgArray.no(glblIdx).dsCpoLineSubNum_RL.getValue());
//
//                if (dsOrdPosnNum.equals(glblDsOrdPosnNum)
//                        && dsCpoLineNum.compareTo(glblDsCpoLineNum) == 0
//                        && dsCpoLineSubNumStr.equals(glblDsCpoLineSubNumString)) {
//                    EZDMsg.copy(bizRmaLineMsgArray.no(bizIdx), null, glblRmaLineMsgArray.no(glblIdx), null);
//                    break;
//                }
//            }
//        }
//    }
    // 2018/01/31 S21_NA#19808 Del End

    private static void setUpLine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, Map<String, Object> selLineInfo, NWZC182002PMsg component) { // S21_NA#5340 // 2018/01/31 S21_NA#19808
        String dsOrdPosnNum = (String) selLineInfo.get("dsOrdPosnNum");
        BigDecimal dsCpoLineNum = (BigDecimal) selLineInfo.get("dsCpoLineNum");
        String dsCpoLineSubNumstr = String.valueOf((BigDecimal) selLineInfo.get("dsCpoLineSubNum"));
        String mdseCd = (String) selLineInfo.get("mdseCd");
        String serNum = (String) selLineInfo.get("serNum");

        NWAL1500_DSMsg glblRmaLineMsg = null; // 2018/01/31 S21_NA#19808
        int slctLineIndex = 0;
        for (; slctLineIndex < glblMsg.D.getValidCount(); slctLineIndex++) { // 2018/01/31 S21_NA#19808
            glblRmaLineMsg = glblMsg.D.no(slctLineIndex); // 2018/01/31 S21_NA#19808
            String glblDsOrdPosnNum = glblRmaLineMsg.dsOrdPosnNum_RL.getValue();
            BigDecimal glblDsCpoLineNum = glblRmaLineMsg.dsCpoLineNum_RL.getValue();
            String glblDsCpoLineSubNumStr = String.valueOf(glblRmaLineMsg.dsCpoLineSubNum_RL.getValue());
            String glblMdseCd = glblRmaLineMsg.mdseCd_RL.getValue();
            String glblSerNum = String.valueOf(glblRmaLineMsg.serNum_RL.getValue());

            if (dsOrdPosnNum.equals(glblDsOrdPosnNum)
                    && dsCpoLineNum.compareTo(glblDsCpoLineNum) == 0
                    && dsCpoLineSubNumstr.equals(glblDsCpoLineSubNumStr)
                    && mdseCd.equals(glblMdseCd)
                    && serNum.equals(glblSerNum)) {
                if (!ZYPCommonFunc.hasValue(glblRmaLineMsg.ordCustUomQty_RL)) {
                    glblRmaLineMsg.ordCustUomQty_RL.setValue(BigDecimal.ZERO.subtract(BigDecimal.ONE));
                }
                if (!ZYPCommonFunc.hasValue(glblRmaLineMsg.svcMachMstrPk_RL)) {
                    ZYPEZDItemValueSetter.setValue(glblRmaLineMsg.svcMachMstrPk_RL, component.svcMachMstrPk);
                }

                // Call NWZC1800 Default WH API S21_NA#9191 add start
                // 2018/01/31 S21_NA#19808 Mod Start
//                int slctConfIndex = NWAL1500CommonLogicForConfigId.getConfigIndex(bizMsg.C, dsOrdPosnNum);
//                NWAL1500_CCMsg rmaConfMsg = bizMsg.C.no(slctConfIndex);
                NWAL1500_CSMsg rmaConfMsg = NWAL1500CommonLogic.getParentConfig(glblMsg.C, dsOrdPosnNum);
                // 2018/01/31 S21_NA#19808 Mod End
                if (ZYPCommonFunc.hasValue(rmaConfMsg.shipToCustCd_RC)) {
                    // 2018/01/31 S21_NA#19808 Add Start
                    NWAL1500_DCMsg newLine = new NWAL1500_DCMsg();
                    EZDMsg.copy(glblRmaLineMsg, null, newLine, null);
                    // 2018/01/31 S21_NA#19808 Add End
                    NWZC180001PMsg pMsg = callDefWhApiForRma(bizMsg, newLine);
                    if (pMsg != null) {
                        String rtlWhCd = pMsg.rtlWhCd.getValue();
                        String rtlSwhCd = pMsg.rtlSwhCd.getValue();

                        // 2016/11/10 S21_NA#10363-2 Mod Start
                        if (!ZYPCommonFunc.hasValue(glblRmaLineMsg.rtlWhCd_RL)) {
                            ZYPEZDItemValueSetter.setValue(glblRmaLineMsg.rtlWhCd_RL, rtlWhCd);
                            ZYPEZDItemValueSetter.setValue(glblRmaLineMsg.rtlWhNm_RL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
                            ZYPEZDItemValueSetter.setValue(glblRmaLineMsg.rtlSwhCd_RL, rtlSwhCd);
                            ZYPEZDItemValueSetter.setValue(glblRmaLineMsg.rtlSwhNm_RL, NWAL1500CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
                            ZYPEZDItemValueSetter.setValue(glblRmaLineMsg.thirdPtyDspTpCd_RL, pMsg.thirdPtyDspTpCd); // 2016/11/04 S21_NA#15703 Add
                        }
                        // 2016/11/10 S21_NA#10363-2 Mod End
                    }
                }
                // S21_NA#9191 add end
                break;
            }
        }
    }

    private static void printDMsg(NWAL1500_DSMsgArray bizRmaLineMsgArray) {
        if (!PRINT_LOG) {
            return;
        }
        printLog("+-+-+-+-+- Biz RmaLineMsg Start -+-+-+-+-+");
        StringBuilder strBuf = new StringBuilder();
        for (int i = 0; i < bizRmaLineMsgArray.getValidCount(); i++) {
            NWAL1500_DSMsg bizRmaLineMsg = bizRmaLineMsgArray.no(i);
            strBuf.append("\r\n-------- LineNum: [" + String.valueOf(i) + "] --------");
            strBuf.append("\r\n    Position: " + String.valueOf(bizRmaLineMsg.dsOrdPosnNum_RL.getValue()));
            strBuf.append("\r\n    dsCpoLineNum: " + String.valueOf(bizRmaLineMsg.dsCpoLineNum_RL.getValue()));
            strBuf.append("\r\n    dsCpoLineSubNum: " + String.valueOf(bizRmaLineMsg.dsCpoLineSubNum_RL.getValue()));
            strBuf.append("\r\n    cpoDtlLineNum: " + String.valueOf(bizRmaLineMsg.cpoDtlLineNum_RL.getValue()));
            strBuf.append("\r\n    cpoDtlLineSubNum: " + String.valueOf(bizRmaLineMsg.cpoDtlLineSubNum_RL.getValue()));
            strBuf.append("\r\n    mdseCd: " + String.valueOf(bizRmaLineMsg.mdseCd_RL.getValue()));
            strBuf.append("\r\n    mdseNm: " + String.valueOf(bizRmaLineMsg.mdseNm_RL.getValue()));
            strBuf.append("\r\n    serNum: " + String.valueOf(bizRmaLineMsg.serNum_RL.getValue()));
            strBuf.append("\r\n    rtlWhCd: " + String.valueOf(bizRmaLineMsg.rtlWhCd_RL.getValue()));
            strBuf.append("\r\n    rtlWhNm: " + String.valueOf(bizRmaLineMsg.rtlWhNm_RL.getValue()));
            strBuf.append("\r\n    rtlSwhCd: " + String.valueOf(bizRmaLineMsg.rtlSwhCd_RL.getValue()));
            strBuf.append("\r\n    rtlSwhNm: " + String.valueOf(bizRmaLineMsg.rtlSwhNm_RL.getValue()));
            strBuf.append("\r\n    entDealNetUnitPrcAmt: " + String.valueOf(bizRmaLineMsg.entDealNetUnitPrcAmt_RL.getValue()));
            strBuf.append("\r\n    entCpoDtlDealSlsAmt: " + String.valueOf(bizRmaLineMsg.entCpoDtlDealSlsAmt_RL.getValue()));
            strBuf.append("\r\n    svcMachMstrPk: " + String.valueOf(bizRmaLineMsg.svcMachMstrPk_RL.getValue()));
        }
        printLog(strBuf.toString());
        printLog("+-+-+-+-+- Biz RmaLineMsg End -+-+-+-+-+");
    }

//    private static void printKMsg(NWAL1500_KSMsgArray glblRmaLineMsgArray) {
//        if (!PRINT_LOG) {
//            return;
//        }
//        printLog("+-+-+-+-+- Global RmaLineMsg Start -+-+-+-+-+");
//        StringBuilder strBuf = new StringBuilder();
//        for (int i = 0; i < glblRmaLineMsgArray.getValidCount(); i++) {
//            NWAL1500_KSMsg glblRmaLineMsg = glblRmaLineMsgArray.no(i);
//            strBuf.append("\r\n-------- LineNum: [" + String.valueOf(i) + "] --------");
//            strBuf.append("\r\n    Position: " + String.valueOf(glblRmaLineMsg.dsOrdPosnNum_RL.getValue()));
//            strBuf.append("\r\n    dsCpoLineNum: " + String.valueOf(glblRmaLineMsg.dsCpoLineNum_RL.getValue()));
//            strBuf.append("\r\n    dsCpoLineSubNum: " + String.valueOf(glblRmaLineMsg.dsCpoLineSubNum_RL.getValue()));
//            strBuf.append("\r\n    cpoDtlLineNum: " + String.valueOf(glblRmaLineMsg.cpoDtlLineNum_RL.getValue()));
//            strBuf.append("\r\n    cpoDtlLineSubNum: " + String.valueOf(glblRmaLineMsg.cpoDtlLineSubNum_RL.getValue()));
//            strBuf.append("\r\n    mdseCd: " + String.valueOf(glblRmaLineMsg.mdseCd_RL.getValue()));
//            strBuf.append("\r\n    mdseNm: " + String.valueOf(glblRmaLineMsg.mdseNm_RL.getValue()));
//            strBuf.append("\r\n    serNum: " + String.valueOf(glblRmaLineMsg.serNum_RL.getValue()));
//            strBuf.append("\r\n    rtlWhCd: " + String.valueOf(glblRmaLineMsg.rtlWhCd_RL.getValue()));
//            strBuf.append("\r\n    rtlWhNm: " + String.valueOf(glblRmaLineMsg.rtlWhNm_RL.getValue()));
//            strBuf.append("\r\n    rtlSwhCd: " + String.valueOf(glblRmaLineMsg.rtlSwhCd_RL.getValue()));
//            strBuf.append("\r\n    rtlSwhNm: " + String.valueOf(glblRmaLineMsg.rtlSwhNm_RL.getValue()));
//            strBuf.append("\r\n    entDealNetUnitPrcAmt: " + String.valueOf(glblRmaLineMsg.entDealNetUnitPrcAmt_RL.getValue()));
//            strBuf.append("\r\n    entCpoDtlDealSlsAmt: " + String.valueOf(glblRmaLineMsg.entCpoDtlDealSlsAmt_RL.getValue()));
//        }
//        printLog(strBuf.toString());
//        printLog("+-+-+-+-+- Global RmaLineMsg End -+-+-+-+-+");
//    }

    private static void printLog(String logLine) {
        if (!PRINT_LOG) {
            return;
        }
        System.out.println(logLine);
    }
    // 2016/01/12 S21_NA#2726 Add End
    // 2016/01/15 S21_NA#3153 Add Start
//    private static boolean isAlreadyExistsConfigId(NWAL1500_CCMsgArray rmaConfMsgArray, NWAL1500_CCMsg curRmaConfigMsg) {
//        String curDsOrdPosnNum = curRmaConfigMsg.dsOrdPosnNum_RC.getValue();
//        String svcConfigMstrPkStr = String.valueOf(curRmaConfigMsg.svcConfigMstrPk_RC.getValue());
//        for (int i = 0; i < rmaConfMsgArray.getValidCount(); i++) {
//            if (curDsOrdPosnNum.equals(rmaConfMsgArray.no(i).dsOrdPosnNum_RC.getValue())) {
//                continue;
//            }
//            String chkSvcConfigMstrPkStr = String.valueOf(rmaConfMsgArray.no(i).svcConfigMstrPk_RC.getValue());
//            if (svcConfigMstrPkStr.equals(chkSvcConfigMstrPkStr)) {
//                return true;
//            }
//        }
//        return false;
//    }

//    private static NWAL1500_DCMsg getAlreadyAddedRMALine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, BigDecimal svcConfigMstrPk, String mdseCd, String serNum, Map<String, Object> lineInfoMap) { 2016/06/16 S21_NA#9848
    private static NWAL1500_DSMsg getAlreadyAddedRMALine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, BigDecimal svcConfigMstrPk, NWAL1500_RCMsg configIdMsg, Map<String, Object> lineInfoMap) { // 2018/01/31 S21_NA#19808

        // 2016/06/16 S21_NA#9848 Add Start
        String mdseCd = configIdMsg.mdseCd_O.getValue();
        String serNum = configIdMsg.serNum_O.getValue();
        BigDecimal svcMachMstrPk = configIdMsg.svcMachMstrPk_O.getValue();
        if (null == svcMachMstrPk) {
            svcMachMstrPk = new BigDecimal(-1);
        }
        // 2016/06/16 S21_NA#9848 Add End

        for (int rmaConfIdx = 0; rmaConfIdx < glblMsg.C.getValidCount(); rmaConfIdx++) {  // 2018/01/31 S21_NA#19808
            NWAL1500_CSMsg rmaConfigMsg = glblMsg.C.no(rmaConfIdx); // 2018/01/31 S21_NA#19808
            if (ZYPCommonFunc.hasValue(rmaConfigMsg.svcConfigMstrPk_RC) && svcConfigMstrPk.compareTo(rmaConfigMsg.svcConfigMstrPk_RC.getValue()) == 0) {
                String dsOrdPosnNum = rmaConfigMsg.dsOrdPosnNum_RC.getValue();
                for (int rmaLineIdx = 0; rmaLineIdx < glblMsg.D.getValidCount(); rmaLineIdx++) {
                    NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(rmaLineIdx); // 2018/01/31 S21_NA#19808
                    String lineDsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
//                    String lineMdseCd = rmaLineMsg.mdseCd_RL.getValue(); 2016/06/21 S21_NA#9849-2 Del
//                    String lineSerNum = String.valueOf(rmaLineMsg.serNum_RL.getValue());2016/06/16 S21_NA#9848 Del
                    String lineSerNum = rmaLineMsg.serNum_RL.getValue(); // 2016/06/16 S21_NA#9848 Add
                    String lineMdseCd = rmaLineMsg.mdseCd_RL.getValue(); // 2016/10/05 S21_NA#11434 Add
                    // 2016/06/16 S21_NA#9848 Add Start
                    BigDecimal lineSvcMachMstrPk = rmaLineMsg.svcMachMstrPk_RL.getValue();
                    if (null == lineSvcMachMstrPk) {
                        lineSvcMachMstrPk = new BigDecimal(-1);
                    }
                    // 2016/06/16 S21_NA#9848 Add End
                    if (null != lineInfoMap) {
                        String targetDsOrdPosnNum = (String) lineInfoMap.get("dsOrdPosnNum");
                        String targetDsCpoLineNumStr = String.valueOf((BigDecimal) lineInfoMap.get("dsCpoLineNum"));
                        String targetDsCpoLineSubNumStr = String.valueOf((BigDecimal) lineInfoMap.get("dsCpoLineSubNum"));
                        String targetMdseCd = (String) lineInfoMap.get("mdseCd");
                        String targetSerNum = (String) lineInfoMap.get("serNum");
                        if (targetDsOrdPosnNum.equals(lineDsOrdPosnNum)
                                && targetDsCpoLineNumStr.equals(String.valueOf(rmaLineMsg.dsCpoLineNum_RL.getValue()))
                                && targetDsCpoLineSubNumStr.equals(String.valueOf(rmaLineMsg.dsCpoLineSubNum_RL.getValue()))
                                && targetMdseCd.equals(rmaLineMsg.mdseCd_RL.getValue())
                                && targetSerNum.equals(rmaLineMsg.serNum_RL.getValue())) {
                            continue;
                        }
                    }
                    // 2016/06/16 S21_NA#9848 Mod Start
//                    if (dsOrdPosnNum.equals(lineDsOrdPosnNum) //
//                            && mdseCd.equals(String.valueOf(lineMdseCd)) //
//                            && String.valueOf(serNum).equals(lineSerNum)) { //
//                        return rmaLineMsg;
//                    }
                    if (dsOrdPosnNum.equals(lineDsOrdPosnNum)) {
                        // boolean isEqualMdseCd = mdseCd.equals(String.valueOf(lineMdseCd)); 2016/06/21 S21_NA#9849-2 Del
                        boolean isEqualSerNum = false;
                        if (ZYPCommonFunc.hasValue(serNum) //
                                && ZYPCommonFunc.hasValue(lineSerNum)) {
                            isEqualSerNum = serNum.equals(lineSerNum);
                        }
                        boolean isEqualMachMstrPk  = 0 == svcMachMstrPk.compareTo(lineSvcMachMstrPk);
                        // if ((isEqualMdseCd && isEqualSerNum) || isEqualMachMstrPk) { 2016/06/21 S21_NA#9849-2 Mod Condition

                        // 2016/10/05 S21_NA#11434 Add Start
                        boolean isEqualMdseCd = false;
                        if (ZYPCommonFunc.hasValue(mdseCd)
                                && ZYPCommonFunc.hasValue(lineMdseCd)) {
                            isEqualMdseCd = mdseCd.equals(lineMdseCd);

                        }
                        // 2016/10/05 S21_NA#11434 Add End

                        // 2018/07/05 Update Start QC#26935
//                        if (isEqualSerNum || isEqualMachMstrPk || isEqualMdseCd ) { // 2016/10/05 S21_NA#11434 Mod
                        if ((isEqualMdseCd && isEqualSerNum) || isEqualMachMstrPk) {
                        // 2018/07/05 Update End QC#26935
                            return rmaLineMsg;
                        }
                    }
                    // 2016/06/16 S21_NA#9848 Mod End
                }
            }
        }
        return null;
    }
    // 2016/01/15 S21_NA#3153 Add End
    // 2016/01/21 S21_NA#3398 Add Start

    // S21_NA#5340 add start
//    private static NWAL1500_BCMsg getAlreadyAddedConfigLine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, BigDecimal svcConfigMstrPk, String mdseCd, String serNum, Map<String, Object> lineInfoMap) { 2016/06/16 S21_NA#9848
    private static NWAL1500_BSMsg getAlreadyAddedConfigLine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, BigDecimal svcConfigMstrPk, NWAL1500_RCMsg configIdMsg, Map<String, Object> lineInfoMap) { // 2018/01/31 S21_NA#19808

        // 2016/06/16 S21_NA#9848 Add Start
        String mdseCd = configIdMsg.mdseCd_O.getValue();
        String serNum = configIdMsg.serNum_O.getValue();
        BigDecimal svcMachMstrPk = configIdMsg.svcMachMstrPk_O.getValue();
        if (null == svcMachMstrPk) {
            svcMachMstrPk = new BigDecimal(-1);
        }
        // 2016/06/16 S21_NA#9848 Add End

        // 2018/01/31 S21_NA#19808 bizMsg => glblMsg
        for (int lineConfIdx = 0; lineConfIdx < glblMsg.A.getValidCount(); lineConfIdx++) {
            NWAL1500_ASMsg lineConfigMsg = glblMsg.A.no(lineConfIdx);
            if (ZYPCommonFunc.hasValue(lineConfigMsg.svcConfigMstrPk_LC) && svcConfigMstrPk.compareTo(lineConfigMsg.svcConfigMstrPk_LC.getValue()) == 0) {
                String dsOrdPosnNum = lineConfigMsg.dsOrdPosnNum_LC.getValue();
                for (int lineIdx = 0; lineIdx < glblMsg.B.getValidCount(); lineIdx++) {
                    NWAL1500_BSMsg lineMsg = glblMsg.B.no(lineIdx);
                    String lineDsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
                    String lineMdseCd = lineMsg.mdseCd_LL.getValue();
                    // String lineSerNum = String.valueOf(lineMsg.serNum_LL.getValue()); 2016/06/16 S21_NA#9848 Del
                    String lineSerNum = lineMsg.serNum_LL.getValue(); // 2016/06/16 S21_NA#9848 Add
                    // 2016/06/16 S21_NA#9848 Add Start
                    BigDecimal lineSvcMachMstrPk = lineMsg.svcMachMstrPk_LL.getValue();
                    if (null == lineSvcMachMstrPk) {
                        lineSvcMachMstrPk = new BigDecimal(-1);
                    }
                    // 2016/06/16 S21_NA#9848 Add End
                    if (null != lineInfoMap) {
                        String targetDsOrdPosnNum = (String) lineInfoMap.get("dsOrdPosnNum");
                        String targetDsCpoLineNumStr = String.valueOf((BigDecimal) lineInfoMap.get("dsCpoLineNum"));
                        String targetDsCpoLineSubNumStr = String.valueOf((BigDecimal) lineInfoMap.get("dsCpoLineSubNum"));
                        String targetMdseCd = (String) lineInfoMap.get("mdseCd");
                        String targetSerNum = (String) lineInfoMap.get("serNum");
                        if (targetDsOrdPosnNum.equals(lineDsOrdPosnNum)
                                && targetDsCpoLineNumStr.equals(String.valueOf(lineMsg.dsCpoLineNum_LL.getValue()))
                                && targetDsCpoLineSubNumStr.equals(String.valueOf(lineMsg.dsCpoLineSubNum_LL.getValue()))
                                && targetMdseCd.equals(lineMsg.mdseCd_LL.getValue())
                                && targetSerNum.equals(lineMsg.serNum_LL.getValue())) {
                            continue;
                        }
                    }
                    // 2016/06/16 S21_NA#9848 Mod Start
//                    if (dsOrdPosnNum.equals(lineDsOrdPosnNum) //
//                            && mdseCd.equals(String.valueOf(lineMdseCd)) //
//                            && String.valueOf(serNum).equals(lineSerNum)) { //
//                        return lineMsg;
//                    }
                    if (dsOrdPosnNum.equals(lineDsOrdPosnNum)) {
                        boolean isEqualMdseCd = mdseCd.equals(String.valueOf(lineMdseCd));
                        boolean isEqualSerNum = false;
                        if (ZYPCommonFunc.hasValue(serNum) && ZYPCommonFunc.hasValue(lineSerNum)) {
                            isEqualSerNum = serNum.equals(lineSerNum);
                        }
                        boolean isEqualMachMstrPk = 0 == svcMachMstrPk.compareTo(lineSvcMachMstrPk);
                        if ((isEqualMdseCd && isEqualSerNum) || isEqualMachMstrPk) {
                            return lineMsg;
                        }
                    }
                    // 2016/06/16 S21_NA#9848 Mod End
                }
            }
        }
        return null;
    }
    // S21_NA#5340 add end

    /**
     * <pre>
     * drive config id.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * </pre>
     */
    public static void doProcessConfiId(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        int slctIdx = bizMsg.xxCellIdx.getValueInt();
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500_ACMsg configMsg = bizMsg.A.no(slctIdx);
            ZYPEZDItemValueSetter.setValue(configMsg.svcConfigMstrPk_LC, bizMsg.svcConfigMstrPk_RO);
            configMsg.xxYesNoCd_LC.setValue(ZYPConstant.FLG_ON_Y);
            // 2018/01/31 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(configMsg, glblMsg);
            // 2018/01/31 S21_NA#19808 Add End
            if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configMsg.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, false, true)) { // S21_NA#955
                // QC#28772 2018/10/16 Add Start
                // setSvcMachMstrInfo(bizMsg, glblMsg, glblMsg.B, configMsg); // 2018/01/31 S21_NA#19808
                setSvcMachMstrInfoConfig(bizMsg, glblMsg, glblMsg.B, configMsg);
                // QC#28772 2018/10/16 Add End
            }
            // 2016/06/02 S21_NA#9277 Add Start
            else if (NWXC150001DsCheck.isExpandCmptMatchConfigTp(bizMsg.glblCmpyCd.getValue(), configMsg.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, true)) {
                setSvcMachMstrInfo(bizMsg, glblMsg, glblMsg.B, configMsg); // 2018/01/31 S21_NA#19808
            }
            // 2016/06/02 S21_NA#9277 Add End
            // 2018/01/04 S21_NA#21503 ADD START 
            else if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configMsg.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, true, false)) {
                String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
                int slctConfIndex = NWAL1500CommonLogicForConfigId.getConfigIndex(glblMsg.A, dsOrdPosnNum); // 2018/01/31 S21_NA#19808
                NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex)); // 2018/01/31 S21_NA#19808
                EZDMsg.copy(glblMsg.A.no(slctConfIndex), null, configMsg, null);
            }
            // 2018/01/04 S21_NA#21503 ADD END
            // 2018/03/15 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.setPageNumForLineConfig(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingLineConfig(bizMsg, glblMsg);
            // 2018/03/15 S21_NA#19808 Add End
        } else {
            NWAL1500_CCMsg rmaConfigMsg = bizMsg.C.no(slctIdx);
            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.svcConfigMstrPk_RC, bizMsg.svcConfigMstrPk_RO);
            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.mdlId_RC, bizMsg.mdlId_RO); // 2016/04/15 S21_NA#5321 Add Start
            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.mdlNm_RC, bizMsg.mdlNm_RO); // 2016/04/15 S21_NA#5321 Add Start
            ZYPEZDItemValueSetter.setValue(rmaConfigMsg.mdlDescTxt_RC, bizMsg.mdlNm_RO); // 2016/04/15 S21_NA#5321 Add Start
            rmaConfigMsg.xxYesNoCd_RC.setValue(ZYPConstant.FLG_ON_Y);
            checkDiffShipToLoc(bizMsg, rmaConfigMsg); // 2019/09/24 QC#53592 Add
            // 2018/01/31 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(rmaConfigMsg, glblMsg);
            // 2018/01/31 S21_NA#19808 Add End
            setSvcMachMstrInfo(bizMsg, glblMsg, glblMsg.D, rmaConfigMsg); // 2018/01/31 S21_NA#19808
            // 2018/03/15 S21_NA#19808 Add Start
            NWAL1500CommonLogicForPagination.setPageNumForRma(bizMsg, glblMsg);
            NWAL1500CommonLogicForPagination.pagingRma(bizMsg, glblMsg);
            // 2018/03/15 S21_NA#19808 Add End
        }
    }

    private static void setSvcMachMstrInfoConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_BSMsgArray lineAllList, NWAL1500_ACMsg configMsg) { // 2018/01/31 S21_NA#19808
        String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
        NWAL1500CommonLogicForConfigId.deleteEmptyLine(glblMsg.B, dsOrdPosnNum);
        int lineStartIndex = NWAL1500CommonLogicForConfigId.getAddLineRow(lineAllList, dsOrdPosnNum);
        int maxLineNum = NWAL1500CommonLogicForConfigId.getMaxLineNum(lineAllList, dsOrdPosnNum);
        int insertRow = lineStartIndex;
        int lineCount = 0;
        int confAddCnt = 0;
        S21SsmEZDResult ssmRslt = NWAL1500QueryForAutoAdd.getInstance().getSvcConfig(bizMsg.glblCmpyCd.getValue(), configMsg.svcConfigMstrPk_LC.getValue(), bizMsg.slsDt.getValue());
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmRslt.getResultObject();
            for (Map<String, Object> map : resultList) {
                NWAL1500_RCMsg configIdMsg = new NWAL1500_RCMsg();
                ZYPEZDItemValueSetter.setValue(configIdMsg.serNum_O, (String) map.get("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(configIdMsg.mdseCd_O, (String) map.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(configIdMsg.curLocNum_O, (String) map.get("CUR_LOC_NUM"));
                ZYPEZDItemValueSetter.setValue(configIdMsg.svcMachUsgStsCd_O, (String) map.get("SVC_MACH_USG_STS_CD"));
                ZYPEZDItemValueSetter.setValue(configIdMsg.svcMachMstrStsCd_O, (String) map.get("SVC_MACH_MSTR_STS_CD"));
                ZYPEZDItemValueSetter.setValue(configIdMsg.svcMachMstrPk_O, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(configIdMsg.svcMachTpCd_O, (String) map.get("SVC_MACH_TP_CD"));
                NWAL1500_BSMsg lineMsg = getAlreadyAddedConfigLine(bizMsg, glblMsg, configMsg.svcConfigMstrPk_LC.getValue(), configIdMsg, null);
                if (lineMsg != null) {
                    ZYPEZDItemValueSetter.setValue(lineMsg.svcMachMstrPk_LL, configIdMsg.svcMachMstrPk_O);
                    continue;
                }
                NWAL1500_BSMsg newLine = (NWAL1500_BSMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow);
                if (newLine == null) {
                    return;
                }
                lineCount++;
                // set up line
                if (!setUpLine(newLine, bizMsg, glblMsg, configIdMsg, dsOrdPosnNum, maxLineNum + lineCount, insertRow++)) {
                    return;
                }
                if (!ZYPCommonFunc.hasValue(newLine.xxYesNoCd_LL)) {
                    newLine.xxYesNoCd_LL.setValue(ZYPConstant.FLG_ON_Y);
                }
                confAddCnt++;
            }
        }
        if (confAddCnt == 0) {
            return;
        }

        int slctConfIndex = NWAL1500CommonLogicForConfigId.getConfigIndex(glblMsg.A, dsOrdPosnNum);
        configMsg.xxSmryLineFlg_L.setValue(ZYPConstant.FLG_OFF_N);

        int startLineIndex = 0;
        for (startLineIndex = 0; startLineIndex < bizMsg.B.getValidCount(); startLineIndex++) {
            String curDsOrdPosnNum = bizMsg.B.no(startLineIndex).dsOrdPosnNum_LL.getValue();
            if (dsOrdPosnNum.equals(curDsOrdPosnNum)) {
                break;
            }
        }
        NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex));
        for (int slctLineIndex = startLineIndex; slctLineIndex < glblMsg.B.getValidCount(); slctLineIndex++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(slctLineIndex);
            String curDsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
            if (!dsOrdPosnNum.equals(curDsOrdPosnNum)) {
                continue;
            }
            NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, slctConfIndex);
            NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
            NWAL1500CommonLogicForLineConfig.deriveLineFloorPrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
            NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex));
        }
        return;
    }

    private static void setSvcMachMstrInfo(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_BSMsgArray lineAllList, NWAL1500_ACMsg configMsg) { // 2018/01/31 S21_NA#19808
        String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
        NWAL1500CommonLogicForConfigId.deleteEmptyLine(glblMsg.B, dsOrdPosnNum); // 2018/01/31 S21_NA#19808 Mod
        int lineStartIndex = NWAL1500CommonLogicForConfigId.getAddLineRow(lineAllList, dsOrdPosnNum);
        int maxLineNum = NWAL1500CommonLogicForConfigId.getMaxLineNum(lineAllList, dsOrdPosnNum);
        int insertRow = lineStartIndex;
        int lineCount = 0;
        int confAddCnt = 0;
        for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
            NWAL1500_RCMsg configIdMsg = bizMsg.R.no(i);
            // NWAL1500_BCMsg lineMsg = getAlreadyAddedConfigLine(bizMsg, glblMsg, configMsg.svcConfigMstrPk_LC.getValue(), configIdMsg.mdseCd_O.getValue(), configIdMsg.serNum_O.getValue(), null); // S21_NA#5340 2016/06/16 S21_NA#9848 
            NWAL1500_BSMsg lineMsg = getAlreadyAddedConfigLine(bizMsg, glblMsg, configMsg.svcConfigMstrPk_LC.getValue(), configIdMsg, null); // 2016/06/16 S21_NA#9848 // 2018/01/31 S21_NA#19808
            if (lineMsg != null) {
                ZYPEZDItemValueSetter.setValue(lineMsg.svcMachMstrPk_LL, configIdMsg.svcMachMstrPk_O); // S21_NA#5340
                continue;
            }

            NWAL1500_BSMsg newLine = (NWAL1500_BSMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow);  // 2018/01/31 S21_NA#19808
            if (newLine == null) {
                return;
            }
            lineCount++;
            // set up line
            if (!setUpLine(newLine, bizMsg, glblMsg, configIdMsg, dsOrdPosnNum, maxLineNum + lineCount, insertRow++)) {
                return;
            }
            // 2018/09/27 S21_NA#28482 Mod Start
            // newLine.xxYesNoCd_LL.setValue(ZYPConstant.FLG_ON_Y);
            if (!ZYPCommonFunc.hasValue(newLine.xxYesNoCd_LL)) {
                newLine.xxYesNoCd_LL.setValue(ZYPConstant.FLG_ON_Y);
            }
            // 2018/09/27 S21_NA#28482 Mod End
            confAddCnt++;
        }
        if (confAddCnt == 0) {
            return;
        }
//        NWAL1500CommonLogicForConfigId.prepareLineS2C(bizMsg.B, lineAllList, dsOrdPosnNum, 0);

        int slctConfIndex = NWAL1500CommonLogicForConfigId.getConfigIndex(glblMsg.A, dsOrdPosnNum); // 2018/01/31 S21_NA#19808
        configMsg.xxSmryLineFlg_L.setValue(ZYPConstant.FLG_OFF_N);

        int startLineIndex = 0;
        for (startLineIndex = 0; startLineIndex < bizMsg.B.getValidCount(); startLineIndex++) {
            String curDsOrdPosnNum = bizMsg.B.no(startLineIndex).dsOrdPosnNum_LL.getValue();
            if (dsOrdPosnNum.equals(curDsOrdPosnNum)) {
                break;
            }
        }
        NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex)); // 2016/07/11 S21_NA#7821 Add // 2018/01/31 S21_NA#19808
        for (int slctLineIndex = startLineIndex; slctLineIndex < glblMsg.B.getValidCount(); slctLineIndex++) { // 2018/01/31 S21_NA#19808
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(slctLineIndex);  // 2018/01/31 S21_NA#19808
            String curDsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
            if (!dsOrdPosnNum.equals(curDsOrdPosnNum)) {
                continue;
            }
            // NWAL1500CommonLogic.setBaseComponentFlag(bizMsg, slctConfIndex, slctLineIndex); 2016/07/11 S21_NA#7821 Del
            NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, slctConfIndex);  // 2018/01/31 S21_NA#19808
            NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
            // QC#22372 2018/01/10 Add Start
            NWAL1500CommonLogicForLineConfig.deriveLineFloorPrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
            // QC#22372 2018/01/10 Add End
            NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, glblMsg.A.no(slctConfIndex));  // 2018/01/31 S21_NA#19808
        }

//        copyBizMsgToGlblMsg(bizMsg.B, glblMsg.J);
        return;
    }

    private static void setSvcMachMstrInfo(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_DSMsgArray lineAllList, NWAL1500_CCMsg rmaConfigMsg) { // 2018/01/31 S21_NA#19808
        String dsOrdPosnNum = rmaConfigMsg.dsOrdPosnNum_RC.getValue();
        NWAL1500CommonLogicForConfigId.deleteEmptyLine(glblMsg.D, dsOrdPosnNum); // 2018/01/31 S21_NA#19808 Mod
        int lineStartIndex = NWAL1500CommonLogicForConfigId.getAddLineRow(lineAllList, dsOrdPosnNum);
        int maxLineNum = NWAL1500CommonLogicForConfigId.getMaxLineNum(lineAllList, dsOrdPosnNum);
        int insertRow = lineStartIndex;
        int lineCount = 0;
        int confAddCnt = 0;
        // 2018/03/19 S21_NA#24582 Mod Start
        // String alreadyAddedMdseCd = ""; // 2016/10/05 S21_NA#11434 Add
        // 2019/07/23 S21_NA#51743 Del Start
        //List<String> alreadyAddedMdseCdList = new ArrayList<String>();
        // 2019/07/23 S21_NA#51743 Del End
        // 2018/03/19 S21_NA#24582 Mod End
        for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
            NWAL1500_RCMsg configIdMsg = bizMsg.R.no(i);
            // NWAL1500_DCMsg rmaLineMsg = getAlreadyAddedRMALine(bizMsg, glblMsg, rmaConfigMsg.svcConfigMstrPk_RC.getValue(), configIdMsg.mdseCd_O.getValue(), configIdMsg.serNum_O.getValue(), null); // S21_NA#5340 2016/06/16 S21_NA#9848
            // 2016/10/05 S21_NA#11434 Add Start
            NWAL1500_DSMsg rmaLineMsg = null;  // 2018/01/31 S21_NA#19808
            // 2019/07/23 S21_NA#51743 Mod Start
            rmaLineMsg = getAlreadyAddedRMALine(bizMsg, glblMsg, rmaConfigMsg.svcConfigMstrPk_RC.getValue(), configIdMsg, null);
            //// 2018/03/19 S21_NA#24582 Mod Start
            //// if (ZYPCommonFunc.hasValue(configIdMsg.mdseCd_O) && !alreadyAddedMdseCd.equals(configIdMsg.mdseCd_O.getValue())) {
            //if (ZYPCommonFunc.hasValue(configIdMsg.mdseCd_O) && !alreadyAddedMdseCdList.contains(configIdMsg.mdseCd_O.getValue())) {  // 2019/07/23 S21_NA#51743 Delete
            //// 2018/03/19 S21_NA#24582 Mod End
            //    rmaLineMsg = getAlreadyAddedRMALine(bizMsg, glblMsg, rmaConfigMsg.svcConfigMstrPk_RC.getValue(), configIdMsg, null); // 2016/06/16 S21_NA#9848
            //    // 2018/03/19 S21_NA#24582 Mod Start
            //    // alreadyAddedMdseCd = configIdMsg.mdseCd_O.getValue();
            //    alreadyAddedMdseCdList.add(configIdMsg.mdseCd_O.getValue());
            //    // 2018/03/19 S21_NA#24582 Mod End
            //}
            // 2019/07/23 S21_NA#51743 Mod End
            // 2016/10/05 S21_NA#11434 Add End

            if (rmaLineMsg != null) {
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.svcMachMstrPk_RL, configIdMsg.svcMachMstrPk_O); // S21_NA#5340
                continue;
            }

            NWAL1500_DSMsg newLine = (NWAL1500_DSMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow); // 2018/01/31 S21_NA#19808
            if (newLine == null) {
                return;
            }
            lineCount++;
            // set up line
            if (!setUpLine(newLine, bizMsg, glblMsg, null, dsOrdPosnNum, maxLineNum + lineCount, insertRow++, configIdMsg)) {
                return;
            }
            newLine.xxYesNoCd_RL.setValue(ZYPConstant.FLG_ON_Y);
            confAddCnt++;
        }
        if (confAddCnt == 0) {
            return;
        }
//        NWAL1500CommonLogicForConfigId.prepareLineS2C(glblMsg.D, lineAllList, dsOrdPosnNum, 0);

        int slctConfIndex = NWAL1500CommonLogicForConfigId.getConfigIndex(glblMsg.C, dsOrdPosnNum); // 2018/01/31 S21_NA#19808
        rmaConfigMsg.xxSmryLineFlg_R.setValue(ZYPConstant.FLG_OFF_N);

        int startLineIndex = 0;
        for (startLineIndex = 0; startLineIndex < glblMsg.D.getValidCount(); startLineIndex++) {
            String curDsOrdPosnNum = glblMsg.D.no(startLineIndex).dsOrdPosnNum_RL.getValue();
            if (dsOrdPosnNum.equals(curDsOrdPosnNum)) {
                break;
            }
        }

        for (int slctLineIndex = startLineIndex; slctLineIndex < glblMsg.D.getValidCount(); slctLineIndex++) {
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(slctLineIndex);
            String curDsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
            if (!dsOrdPosnNum.equals(curDsOrdPosnNum)) {
                continue;
            }
            NWAL1500CommonLogicForLineConfig.setLineRef(bizMsg, glblMsg, slctConfIndex); // 2018/01/31 S21_NA#19808
            NWAL1500CommonLogicForLineConfig.setEachQty(bizMsg, glblMsg, slctLineIndex, rmaLineMsg.mdseCd_RL.getValue()); // 2018/01/31 S21_NA#19808
            NWAL1500CommonLogicForLineConfig.deriveLinePrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
            // 2017/11/21 S21_NA#22555 Del Start
//            NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, slctConfIndex);
            // 2017/11/21 S21_NA#22555 Del End
            // QC#22372 2018/01/10 Add Start
            NWAL1500CommonLogicForLineConfig.deriveLineFloorPrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
            // QC#22372 2018/01/10 Add End
        }
//        copyBizMsgToGlblMsg(glblMsg.D, glblMsg.K);

        // Add Start 2017/01/27 QC#17257
        List<String> posnNums = new ArrayList<String>();
        posnNums.add(dsOrdPosnNum);
        NWAL1500CommonLogicForLineConfig.updateBaseCmptFlg(bizMsg, glblMsg, posnNums, false); // 2018/01/31 S21_NA#19808
        // Add End 2017/01/27 QC#17257

        return;
    }
    // 2016/01/21 S21_NA#3398 Add Start

    // 2019/09/24 QC#53592 Add Start
    private static void checkDiffShipToLoc(NWAL1500CMsg bizMsg, NWAL1500_CCMsg rmaConfigMsg) {
    	String smmShipToCust = "";
        SVC_MACH_MSTRTMsg smmTMsg = new SVC_MACH_MSTRTMsg();
        smmTMsg.setSQLID("005");
        smmTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        smmTMsg.setConditionValue("svcConfigMstrPk01", bizMsg.svcConfigMstrPk_RO.getValue());
        smmTMsg.setConditionValue("svcMachTpCd01", SVC_MACH_TP.MACHINE);
        SVC_MACH_MSTRTMsgArray svcMachMstr = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(smmTMsg);
        if (svcMachMstr == null || svcMachMstr.getValidCount() == 0) {
            return;
        }
        smmTMsg = (SVC_MACH_MSTRTMsg) svcMachMstr.get(0);
        smmShipToCust = smmTMsg.curLocNum.getValue();
        if(!ZYPCommonFunc.hasValue(smmShipToCust)){
            return;
        }
        SHIP_TO_CUSTTMsg stcTMsg = new SHIP_TO_CUSTTMsg();
        stcTMsg.setSQLID("004");
        stcTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        stcTMsg.setConditionValue("shipToCustCd01",smmShipToCust);
        SHIP_TO_CUSTTMsgArray shipToCust = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(stcTMsg);
        if (shipToCust == null || shipToCust.getValidCount() == 0) {
            return;
        }
        stcTMsg = (SHIP_TO_CUSTTMsg) shipToCust.get(0);
        if(LOC_GRP.CUSTOMER.equals(stcTMsg.locGrpCd.getValue())){
            if(!rmaConfigMsg.shipToCustCd_RC.equals(smmShipToCust)){
                ZYPEZDItemValueSetter.setValue(rmaConfigMsg.shipToCustCd_RC, smmShipToCust);
                ZYPEZDItemValueSetter.setValue(rmaConfigMsg.shipToCustAcctCd_RC, stcTMsg.sellToCustCd); // 2019/10/15 QC#53592-1 Add
            }
        }
    }
    // 2019/09/24 QC#53592 Add End

    // 2018/01/31 S21_NA#19808 Del Start
//    //2016/06/21 S21_NA#9849-2 Add Start
//    private static NWAL1500_DSMsg getSameSerialRmaLineMsg(NWAL1500SMsg glblMsg, String serNum, Integer slctLineNum) { // 2018/01/31 S21_NA#19808
//
//        for (int i = 0; i < glblMsg.D.getValidCount(); i++) { // 2018/01/31 S21_NA#19808
//            if (S21StringUtil.isEquals(serNum, glblMsg.D.no(i).serNum_RL.getValue())) { // 2018/01/31 S21_NA#19808
//                slctLineNum = i;
//                return glblMsg.D.no(i);
//            }
//        }
//        return null;
//    }
//    //2016/06/21 S21_NA#9849-2 Add End
    // 2018/01/31 S21_NA#19808 Del End

    // 2016/11/10 S21_NA#10363-2 Del
//    private Object newLine;

    // 2016/11/10 S21_NA#10363-2 Add Start
    private static void setRtrnRsnWh(NWAL1500_DSMsg newLine, NWAL1500_DSMsg headerLine) { // 2018/01/31 S21_NA#19808
        if (newLine == null) {
            return;
        }
        if (!ZYPCommonFunc.hasValue(newLine.rtrnRsnCd_RL) && ZYPCommonFunc.hasValue(headerLine.rtrnRsnCd_RL)) {
            ZYPEZDItemValueSetter.setValue(newLine.rtrnRsnCd_RL, headerLine.rtrnRsnCd_RL.getValue());
        }
        // Dell Start 2017/02/01 QC#17257
//        if (!ZYPCommonFunc.hasValue(newLine.rtlWhCd_RL) && ZYPCommonFunc.hasValue(headerLine.rtlWhCd_RL)) {
//            ZYPEZDItemValueSetter.setValue(newLine.rtlWhCd_RL, headerLine.rtlWhCd_RL.getValue());
//            ZYPEZDItemValueSetter.setValue(newLine.rtlWhNm_RL, headerLine.rtlWhNm_RL.getValue());
//            ZYPEZDItemValueSetter.setValue(newLine.rtlSwhCd_RL, headerLine.rtlSwhCd_RL.getValue());
//            ZYPEZDItemValueSetter.setValue(newLine.rtlSwhNm_RL, headerLine.rtlSwhNm_RL.getValue());
//            ZYPEZDItemValueSetter.setValue(newLine.thirdPtyDspTpCd_RL, headerLine.thirdPtyDspTpCd_RL.getValue());
//        }
        // Dell End 2017/02/01 QC#17257
    }
    // 2016/11/10 S21_NA#10363-2 Add End
    // 2017/10/19 S21_NA#21843 Add Start
    private static void setMdlInfoToRmaConfig(NWAL1500CMsg bizMsg, NWAL1500_CSMsg rmaConfig, BigDecimal svcConfigMstrPk) { // 2018/01/31 S21_NA#19808

        MDL_NMTMsg mdlInfo = NWAL1500QueryForAutoAdd.getInstance().getMdlInfoBySvcConfigMstrPk(
                bizMsg.glblCmpyCd.getValue(),
                svcConfigMstrPk);

        if (mdlInfo != null) {

            ZYPEZDItemValueSetter.setValue(rmaConfig.mdlId_RC, mdlInfo.t_MdlId);
            ZYPEZDItemValueSetter.setValue(rmaConfig.mdlNm_RC, mdlInfo.t_MdlNm);
            ZYPEZDItemValueSetter.setValue(rmaConfig.mdlDescTxt_RC, mdlInfo.t_MdlNm);
        }
    }
    // 2017/10/19 S21_NA#21843 Add End
    // 2018/05/17 S21_NA#22988 Add Start
    private static boolean isAvalLineCatgForDefWh(String glblCmpyCd, String rtlWhCd, String dsOrdLineCatgCd) {

        BigDecimal lnCnt = NWAL1500QueryForAutoAdd.getInstance().isAvalLineCatgForDefWh(glblCmpyCd, rtlWhCd, dsOrdLineCatgCd);
        if (BigDecimal.ZERO.compareTo(lnCnt) < 0) {
            return true;
        }
        return false;
    }

    private static boolean isIncludedLineConfig(NWAL1500CMsg bizMsg, String dsOrdLineCatgCd) {

        for (int i = 0; i < bizMsg.dsOrdLineCatgCd_CD.length(); i++) {
            if (S21StringUtil.isEquals(bizMsg.dsOrdLineCatgCd_CD.no(i).getValue(), dsOrdLineCatgCd)) {
                return true;
            }
        }
        return false;
    }
    // 2018/05/17 S21_NA#22988 Add End
}
