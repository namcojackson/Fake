/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.DROP_SHIP_RTL_WH_CD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.HEADER_STS_NM_CANCELLED;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWAL1500_RENTAL_CONV_DEFWH;
import static business.blap.NWAL1500.constant.NWAL1500Constant.NWA_CONV_CONFIG_TP;
import static business.blap.NWAL1500.constant.NWAL1500Constant.SUB_WH_CD_NEW;
import static business.blap.NWAL1500.constant.NWAL1500Constant.SUPPLY_SUB_WH_CD;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TO_BE_CANCELLED;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_ITEM;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_MDL;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SBST_ITEM;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SUB_WH;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_WH;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0100E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0181E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0300E;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.ZZM9000E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500ItemNameList;
import business.blap.NWAL1500.NWAL1500QueryForLineConfig;
import business.blap.NWAL1500.NWAL1500QueryForSaveSubmit;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ACMsg;
import business.blap.NWAL1500.NWAL1500_ASMsg;
import business.blap.NWAL1500.NWAL1500_BCMsg;
import business.blap.NWAL1500.NWAL1500_BCMsgArray;
import business.blap.NWAL1500.NWAL1500_BSMsg;
import business.blap.NWAL1500.NWAL1500_BSMsgArray;
import business.blap.NWAL1500.NWAL1500_CCMsg;
import business.blap.NWAL1500.NWAL1500_CSMsg;
import business.blap.NWAL1500.NWAL1500_CSMsgArray;
import business.blap.NWAL1500.NWAL1500_DCMsg;
import business.blap.NWAL1500.NWAL1500_DCMsgArray;
import business.blap.NWAL1500.NWAL1500_DSMsg;
import business.blap.NWAL1500.NWAL1500_DSMsgArray;
import business.blap.NWAL1500.NWAL1500_ISMsg;
import business.blap.NWAL1500.NWAL1500_USMsg;
import business.blap.NWAL1500.NWAL1500_USMsgArray;
import business.blap.NWAL1500.NWAL1500_XSMsg;
import business.blap.NWAL1500.constant.NWAL1500Constant;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CONFIG_TPTMsg;
import business.db.CPO_SRC_TPTMsg;
import business.db.CUST_MDSE_XREFTMsg;
import business.db.CUST_MDSE_XREFTMsgArray;
import business.db.DS_IMPT_ORD_DTLTMsg;
import business.db.DS_MDLTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.ORD_LINE_VAL_SETTMsg;
import business.db.ORD_LINE_VAL_SETTMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.db.PRC_CATGTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_WHTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.parts.NLZC215001PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;
import business.parts.NWZC180001PMsg;
import business.parts.NWZC192001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC215001.NLZC215001;
import com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC192001.NWZC192001;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001CommonReturnReason;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001SvcMdlFunc;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001SvcMdlFuncParamBean;
import com.canon.cusa.s21.common.NWX.NWXC150001.cache.ConfigTpCache;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_ENTRY_ACT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CALC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_JRNL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MDL_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/21   Fujitsu         T.Yoshida       Create          n/a
 * 2016/01/04   Fujitsu         T.ishii         Update          S21_NA#955
 * 2016/01/19   Fujitsu         S.Takami        Update          S21_NA#3339
 * 2016/02/12   Fujitsu         Y.Taoka         Update          S21_NA#1303
 * 2016/03/01   Fujitsu         M.Nakamura      Update          S21_NA#4375
 * 2016/03/07   Fujitsu         M.Nakamura      Update          S21_NA#5000#78
 * 2016/03/15   Fujitsu         S.Takami        Update          S21_NA#4691
 * 2016/03/25   Fujitsu         S.Takami        Update          S21_NA#3236-2
 * 2016/04/08   Fujitsu         S.Takami        Update          S21_NA#1703, 5356
 * 2016/04/11   Fujitsu         S.Takami        Update          S21_NA#3236-3
 * 2016/05/16   Fujitsu         S.Takami        Update          S21_NA#8144
 * 2016/05/26   Fujitsu         T.Murai         Update          S21_NA#2334
 * 2016/06/10   Fujitsu         Y.Kanefusa      Update          S21_NA#9482
 * 2016/06/21   Fujitsu         Y.Kanefusa      Update          S21_NA#9437
 * 2016/07/02   Fujitsu         M.Hara          Update          S21_NA#1698,3235
 * 2016/07/04   Fujitsu         S.Takami        Update          S21_NA#7645
 * 2016/07/27   Fujitsu         S.Takami        Update          S21_NA#4691-2
 * 2016/08/01   Hitachi         Y.Takeno        Update          S21_NA#12599
 * 2016/08/02   Fujitsu         S.Takami        Update          S21_NA#4691-3
 * 2016/08/09   Fujitsu         S.Iidaka        Update          S21_NA#11592
 * 2016/09/09   Fujitsu         Y.Taoka         Update          S21_NA#14299
 * 2016/09/14   Fujitsu         T.Yoshida       Update          S21_NA#14510
 * 2016/09/27   Fujitsu         Y.Taoka         Update          S21_NA#9761
 * 2016/09/27   Fujitsu         N.Sugiura       Update          S21_NA#9192
 * 2016/09/28   Fujitsu         Y.Taoka         Update          S21_NA#9761
 * 2016/09/29   Fujitsu         S.Takami        Update          S21_NA#7645-2
 * 2016/09/29   Fujitsu         Y.Taoka         Update          S21_NA#14805
 * 2016/10/05   Fujitsu         S.Takami        Update          S21_NA#7645-3 (Delete calcBoQty)
 * 2016/10/19   Fujitsu         Y.Kanefusa      Update          S21_NA#14330
 * 2016/11/04   Fujitsu         S.Takami        Update          S21_NA#15703
 * 2016/11/29   Fujitsu         S.Ohki          Update          S21_NA#16214
 * 2016/11/30   Fujitsu         S.Ohki          Update          S21_NA#16098
 * 2016/12/27   Fujitsu         S.Ohki          Update          S21_NA#13768
 * 2017/01/27   Fujitsu         R.Nakamura      Update          S21_NA#17257
 * 2017/01/31   Fujitsu         R.Nakamura      Update          S21_NA#17186
 * 2017/02/02   Fujitsu         R.Nakamura      Update          S21_NA#17349
 * 2017/03/07   Fujitsu         Y.Kanefusa      Update          S21_NA#17673
 * 2017/06/21   Fujitsu         R.Nakamura      Update          S21_NA#19407
 * 2017/06/27   Fujitsu         R.Nakamura      Update          S21_NA#19585
 * 2017/07/03   Fujitsu         R.Nakamura      Update          S21_NA#19709
 * 2017/09/01   Fujitsu         T.Ogura         Update          S21_NA#19749
 * 2017/11/17   Fujitsu         A.Kosai         Update          S21_NA#22252
 * 2017/11/21   Fujitsu         A.Kosai         Update          S21_NA#22555
 * 2017/11/24   Fujitsu         Y.Kanefusa      Update          S21_NA#22789
 * 2018/01/04   Fujitsu         K.Ishizuka      Update          S21_NA#21503
 * 2018/01/10   Fujitsu         Y.Kanefusa      Update          S21_NA#22372
 * 2018/01/11   Fujitsu         S.Takami        Update          S21_NA#23329
 * 2018/01/30   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/02/08   Fujitsu         S.Ohki          Update          S21_NA#20173(Sol#453)
 * 2018/03/14   Fujitsu         M.Ohno          Update          S21_NA#24117-1
 * 2018/03/15   Fujitsu         R.Nakamura      Update          S21_NA#24258
 * 2018/03/15   Fujitsu         S.Takami        Update          S21_NA#19808-2(bizMsg.I->glblMsg.I without any comments)
 * 2018/03/20   Fujitsu         S.Takami        Update          S21_NA#24842
 * 2018/03/27   Fujitsu         S.Takami        Update          S21_NA#25027
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * 2018/05/24   Fujitsu         S.Takami        Update          S21_NA#25604-2
 * 2018/05/25   Fujitsu         S.Takami        Update          S21_NA#26352
 * 2018/06/01   Fujitsu         T.Noguchi       Update          S21_NA#26334
 * 2018/06/07   Fujitsu         Y.Kanefusa      Update          S21_NA#26415
 * 2018/07/03   Fujitsu         A.Kosai         Update          S21_NA#26908
 * 2018/08/21   Fujitsu         S.Takami        Update          S21_NA#26767
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          QC#9700
 * 2018/09/11   Fujitsu         K.Ishizuka      Update          S21_NA#25352
 * 2018/09/20   Fujitsu         S.Takami        Update          S21_NA#28199
 * 2018/12/19   Fujitsu         K.Ishizuka      Update          S21_NA#29561
 * 2019/03/29   Fujitsu         R.Nakamura      Update          S21_NA#30849
 * 2019/04/26   Fujitsu         Y.Kanefusa      Update          S21_NA#31345
 * 2019/08/09   Fujitsu         M.Ohno          Update          S21_NA#52472
 * 2019/08/20   Fujitsu         K.Kato          Update          S21_NA#52462
 * 2019/11/21   Fujitsu         S.Takami        Update          S21_NA#54202
 * 2020/04/14   Fujitsu         Y.Kanefusa      Update          S21_NA#56558
 * 2020/05/25   Fujitsu         C.Hara          Update          QC#56558-1
 * </pre>
 */
public class NWAL1500CommonLogicForLineConfig {

    /** Item Intangible */
    public static final String INTG = "INTG"; // 2016/07/04 S21_NA#7645 Add

    // /** Order Line Status Name: BACK ORDER */
    // public static final String STS_NM_BACK_ORDER = "BACK ORDER"; // 2016/09/29 S21_NA#7645-2 Add -> 2016/10/05 S21_NA#7645-3 Del
    /**
     * Check Merchandise Code
     * @param bizMsg NWAL1500CMsg
     * @param inputMdseCd Merchandise Code
     * @param custMdseCd Customer Merchandise Code
     * @param mnfItemCd Manufacturer Item Code
     * @param origMdseCd Original Merchandise Code
     * @param srchOrigItemFlg Search Original Item Flag
     * @return Checked Merchandise Code
     */
    public static String checkMdseCd(NWAL1500CMsg bizMsg, EZDSStringItem inputMdseCd, EZDSStringItem custMdseCd, EZDSStringItem mnfItemCd, EZDSStringItem origMdseCd, EZDSStringItem srchOrigItemFlg) { // 2018/01/30 S21_NA#19808 Mod

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);
        String inputMdseCdValue = inputMdseCd.getValue();
        // 2016/09/29 S21_NA#14805 ADD
        if (srchOrigItemFlg != null && !ZYPConstant.FLG_ON_Y.equals(bizMsg.srchOrigItemFlg_MF.getValue())) {
            ZYPEZDItemValueSetter.setValue(srchOrigItemFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.srchOrigItemFlg_MF, ZYPConstant.FLG_OFF_N); // S21_NA#9761 ADD

        ALL_MDSE_VTMsgArray allMdseViewArray = getAllMdseViewArray(bizMsg, inputMdseCdValue);
        if (allMdseViewArray.getValidCount() == 1) {
            String mdseCd = allMdseViewArray.no(0).mdseCd.getValue();
            ZYPEZDItemValueSetter.setValue(custMdseCd, getCustMdseCd(bizMsg, mdseCd));
            ZYPEZDItemValueSetter.setValue(mnfItemCd, getMnfItemCd(bizMsg, mdseCd));
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                ZYPEZDItemValueSetter.setValue(origMdseCd, mdseCd);
            } else {
                MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), mdseCd);
                ZYPEZDItemValueSetter.setValue(origMdseCd, mdseTMsg.mdseCd);
            }
            return mdseCd;
        } else if (allMdseViewArray.getValidCount() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        CUST_MDSE_XREFTMsgArray custMdseXrefArray = getCustMdseXrefArray(bizMsg, inputMdseCdValue);
        if (custMdseXrefArray.getValidCount() == 1) {
            String baseMdseCd = custMdseXrefArray.no(0).mdseCd.getValue();
            ZYPEZDItemValueSetter.setValue(custMdseCd, inputMdseCd);
            ZYPEZDItemValueSetter.setValue(mnfItemCd, getMnfItemCd(bizMsg, baseMdseCd));
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                ZYPEZDItemValueSetter.setValue(origMdseCd, inputMdseCd);
                baseMdseCd = chgBaseMdseCd(bizMsg.glblCmpyCd.getValue(), baseMdseCd); // ADD S21_NA#9761
            } else {
                // QC#26415 2018/06/07 Mod Start
//                MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), inputMdseCd.getValue());
//                // S21_NA#14299 MOD START
//                if (mdseTMsg != null) {
//                    ZYPEZDItemValueSetter.setValue(origMdseCd, mdseTMsg.mdseCd);
//                } else {
//                    inputMdseCd.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_ITEM });
//                    return null;
//                }
//                // S21_NA#14299 MOD END

                ZYPEZDItemValueSetter.setValue(origMdseCd, inputMdseCd);
                baseMdseCd = chgBaseMdseCd(bizMsg.glblCmpyCd.getValue(), baseMdseCd);

                // QC#26415 2018/06/07 Mod Start
            }
            ZYPEZDItemValueSetter.setValue(inputMdseCd, baseMdseCd);
            return baseMdseCd;
        } else if (custMdseXrefArray.getValidCount() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }
        String baseMdseCd = null;
        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getBaseMdseCdFromMnfItemCd(bizMsg, inputMdseCdValue);
        if (ssmResult.isCodeNotFound()) {
            inputMdseCd.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_ITEM });
            return null;

        } else if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            // S21_NA#9761 ADD START
            List<Map<String, Object>> mnfItemList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (srchOrigItemFlg != null) {
                ZYPEZDItemValueSetter.setValue(srchOrigItemFlg, ZYPConstant.FLG_ON_Y); // 2016/09/29 S21_NA#14805 ADD
            }
            if (1 < ssmResult.getQueryResultCount()) {

                Set<String> mdse8Set = new HashSet<String>();
                for (Map<String, Object> mnfItem : mnfItemList) {
                    String mdseCd = (String) mnfItem.get("MDSE_CD");
                    if (8 < mdseCd.length()) {
                        mdseCd = mdseCd.substring(0, 8);
                    }
                    mdse8Set.add(mdseCd);
                }
                if (1 < mdse8Set.size()) {
                    // Open Popup
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(bizMsg.srchOrigItemFlg_MF, ZYPConstant.FLG_ON_Y);
                    return null;

                } else {
                    // Found one MnfItemCd
                    for (String mdse8 : mdse8Set) {
                        baseMdseCd = mdse8;
                        break;
                    }
                    ORD_TAKE_MDSETMsg tMsg = getOrdTakeMdse2(bizMsg.glblCmpyCd.getValue(), baseMdseCd);
                    if (tMsg == null) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(bizMsg.srchOrigItemFlg_MF, ZYPConstant.FLG_ON_Y);
                        return null;
                    }
                }
            } else {
                // Found one MnfItemCd
                baseMdseCd = (String) mnfItemList.get(0).get("MDSE_CD");
            }
        } else {
            List<Map<String, Object>> mnfItemList = (List<Map<String, Object>>) ssmResult.getResultObject();
            // QC#26415 2018/06/07 Mod Start
            //baseMdseCd = (String) mnfItemList.get(0).get("MDSE_CD");
            if (srchOrigItemFlg != null) {
                ZYPEZDItemValueSetter.setValue(srchOrigItemFlg, ZYPConstant.FLG_ON_Y);
            }
            if (1 < ssmResult.getQueryResultCount()) {

                Set<String> mdse8Set = new HashSet<String>();
                for (Map<String, Object> mnfItem : mnfItemList) {
                    String mdseCd = (String) mnfItem.get("MDSE_CD");
                    mdse8Set.add(mdseCd);
                }
                // Open Popup
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(bizMsg.srchOrigItemFlg_MF, ZYPConstant.FLG_ON_Y);
                return null;
            } else {
                baseMdseCd = (String) mnfItemList.get(0).get("MDSE_CD");
            }
            // QC#26415 2018/06/07 Mod End
        }
        // S21_NA#9761 ADD END
//        String baseMdseCd = (String) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(custMdseCd, getCustMdseCd(bizMsg, baseMdseCd));
        ZYPEZDItemValueSetter.setValue(mnfItemCd, inputMdseCd);
        // QC#26415 2018/06/07 Add Start
        ZYPEZDItemValueSetter.setValue(origMdseCd, inputMdseCd);
        // QC#26415 2018/06/07 Add End
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            ORD_TAKE_MDSETMsgArray tMsgArray = getOrdTakeMdse(bizMsg.glblCmpyCd.getValue(), baseMdseCd);
            if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
                baseMdseCd = tMsgArray.no(0).ordTakeMdseCd.getValue();
            }
        }
// QC#26415 2018/06/07 Del Start
//        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
//            ZYPEZDItemValueSetter.setValue(origMdseCd, inputMdseCd);
//        } else {
//            MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), inputMdseCd.getValue());
//            // S21_NA#14299 MOD START
//            if (mdseTMsg != null) {
//                ZYPEZDItemValueSetter.setValue(origMdseCd, mdseTMsg.mdseCd);
//            } else {
//                inputMdseCd.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_ITEM });
//                return null;
//            }
//            // S21_NA#14299 MOD END
//        }
//
//        ORD_TAKE_MDSETMsgArray tMsgArray = getOrdTakeMdse(bizMsg.glblCmpyCd.getValue(), baseMdseCd);
//        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
//            baseMdseCd = tMsgArray.no(0).ordTakeMdseCd.getValue();
//        }
//        ZYPEZDItemValueSetter.setValue(inputMdseCd, baseMdseCd);
// QC#26415 2018/06/07 Del End
        return baseMdseCd;
    }

    /**
     * Get ALL_MDSE_V Array
     * @param bizMsg NWAL1500CMsg
     * @param mdseCd Merchandise Code
     * @return ALL_MDSE_V Array
     */
    private static ORD_TAKE_MDSETMsgArray getOrdTakeMdse(String glblCmpyCd, String mdseCd) {

        final ORD_TAKE_MDSETMsg condition = new ORD_TAKE_MDSETMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("mdseCd01", mdseCd);

        return (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get ALL_MDSE_V Array
     * @param bizMsg NWAL1500CMsg
     * @param mdseCd Merchandise Code
     * @return ALL_MDSE_V Array
     */
    private static ALL_MDSE_VTMsgArray getAllMdseViewArray(NWAL1500CMsg bizMsg, String mdseCd) {

        final ALL_MDSE_VTMsg condition = new ALL_MDSE_VTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("mdseCd01", mdseCd);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        return (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get CUST_MDSE_XREF Array
     * @param bizMsg NWAL1500CMsg
     * @param mdseCd Merchandise Code
     * @return CUST_MDSE_XREF Array
     */
    private static CUST_MDSE_XREFTMsgArray getCustMdseXrefArray(NWAL1500CMsg bizMsg, String mdseCd) {

        final CUST_MDSE_XREFTMsg condition = new CUST_MDSE_XREFTMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("custMdseCd01", mdseCd);
        condition.setConditionValue("dsAcctNum01", bizMsg.sellToCustCd.getValue());
        condition.setConditionValue("custMdseXrefEnblFlg01", ZYPConstant.FLG_ON_Y);
        return (CUST_MDSE_XREFTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get Customer MDSE Code
     * @param bizMsg NWAL1500CMsg
     * @param mdseCd Merchandise Code
     * @return Customer MDSE Code
     */
    private static String getCustMdseCd(NWAL1500CMsg bizMsg, String mdseCd) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), mdseCd);
        if (mdseTMsg == null) {
            return null;
        }

        final CUST_MDSE_XREFTMsg condition = new CUST_MDSE_XREFTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("mdseCd01", mdseTMsg.mdseCd.getValue());
        condition.setConditionValue("dsAcctNum01", bizMsg.sellToCustCd.getValue());
        condition.setConditionValue("custMdseXrefEnblFlg01", ZYPConstant.FLG_ON_Y);
        CUST_MDSE_XREFTMsgArray tMsgArray = (CUST_MDSE_XREFTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            return null;
        }

        return tMsgArray.no(0).custMdseCd.getValue();
    }

    /**
     * Get Manufacturer Item Code
     * @param bizMsg NWAL1500CMsg
     * @param mdseCd Merchandise Code
     * @return Manufacturer Item Code
     */
    private static String getMnfItemCd(NWAL1500CMsg bizMsg, String mdseCd) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), mdseCd);
        if (mdseTMsg == null) {
            return null;
        }

        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getMnfItemCd(bizMsg, mdseTMsg.mdseCd.getValue());
        if (ssmResult.isCodeNotFound()) {
            return null;
        }

        return (String) ssmResult.getResultObject();
    }

    // /**
    // * Derive From Model
    // * @param bizMsg NWAL1500CMsg
    // * @param glblMsg NWAL1500SMsg
    // * @param confMsg NWAL1500_ACMsg
    // */
    // public static void deriveItemFromModel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg,
    // NWAL1500_ACMsg confMsg) {
    //
    // List<Map<String, String>> mdlConfList =
    // NWAL1500CommonLogicForLineConfig.getMdlConfList(bizMsg, confMsg);
    // if (mdlConfList == null) {
    // return;
    // }
    //
    // if (NWAL1500CommonLogicForLineConfig.isExistMultiplePrntMdse(mdlConfList)) {
    // return;
    // }
    //
    // NWAL1500_JSMsgArray lineAllList = glblMsg.J;
    // NWAL1500_BCMsgArray lineList = bizMsg.B;
    //
    // NWAL1500SMsg cloneSMsg = (NWAL1500SMsg) glblMsg.clone();
    //
    // String dsOrdPosnNum = confMsg.dsOrdPosnNum_LC.getValue();
    // NWAL1500CommonLogicForLineControl.storeLineByConfig(lineAllList, lineList, dsOrdPosnNum); //
    // S21_NA#1670
    // NWAL1500CommonLogicForConfigId.deleteEmptyLine(lineAllList, dsOrdPosnNum);
    //
    // int lineStartIndex = NWAL1500CommonLogicForConfigId.getAddLineRow(lineAllList, dsOrdPosnNum);
    // int maxLineNum = NWAL1500CommonLogicForConfigId.getMaxLineNum(lineAllList, dsOrdPosnNum);
    // int insertRow = lineStartIndex;
    // int lineCount = 0;
    // for (Map<String, String> mdlConf : mdlConfList) {
    // NWAL1500_JSMsg newLine = (NWAL1500_JSMsg)
    // NWAL1500CommonLogicForConfigId.insertNewLine(lineAllList, insertRow++);
    // if (newLine == null) {
    // // full line
    // EZDMsg.copy(cloneSMsg.J, null, glblMsg.J, null);
    // bizMsg.setMessageInfo(NWAM0100E);
    // return;
    // }
    // lineCount++;
    //
    // // set up line
    // String prntMdseCd = mdlConf.get("PRNT_MDSE_CD");
    // String childMdseCd = mdlConf.get("CHILD_MDSE_CD");
    // MDSETMsg mdseTMsg = null;
    //
    // if (!ZYPCommonFunc.hasValue(childMdseCd)) {
    // mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), prntMdseCd);
    // if (!setItemInfoForOnBlurMdl(newLine, bizMsg, confMsg, mdlConf, maxLineNum + lineCount,
    // mdseTMsg, true)) {
    // EZDMsg.copy(cloneSMsg.J, null, glblMsg.J, null);
    // return;
    // }
    // } else {
    // mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), childMdseCd);
    // if (!setItemInfoForOnBlurMdl(newLine, bizMsg, confMsg, mdlConf, maxLineNum + lineCount,
    // mdseTMsg, false)) {
    // EZDMsg.copy(cloneSMsg.J, null, glblMsg.J, null);
    // return;
    // }
    // }
    // }
    //
    // ZYPEZDItemValueSetter.setValue(confMsg.xxSmryLineFlg_L, ZYPConstant.FLG_OFF_N);
    // NWAL1500CommonLogicForConfigId.prepareLineS2C(lineList, lineAllList, dsOrdPosnNum,
    // lineStartIndex);
    // }

    // /**
    // * Set Item Infomation For OnBlur Model
    // * @param newLine NWAL1500_JSMsg
    // * @param bizMsg NWAL1500CMsg
    // * @param confMsg NWAL1500_ACMsg
    // * @param mdlConf Model Config
    // * @param lineNum Line Number
    // * @param mdseTMsg MDSETMsg
    // * @param isPrntFlg Parent Flag
    // * @return exist API error : false
    // */
    // @SuppressWarnings("unchecked")
    // public static boolean setItemInfoForOnBlurMdl(NWAL1500_JSMsg newLine, NWAL1500CMsg bizMsg,
    // NWAL1500_ACMsg confMsg, Map<String, String> mdlConf, int lineNum, MDSETMsg mdseTMsg, boolean
    // isPrntFlg) {
    //
    // if (mdseTMsg == null) {
    // confMsg.mdlDescTxt_LC.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_MDL });
    // return false;
    // }
    //
    // S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getMdseInfo(bizMsg,
    // mdseTMsg.mdseCd.getValue(), true);
    // Map<String, String> mdseInfo = new HashMap<String, String>();
    // if (!ssmResult.isCodeNotFound()) {
    // mdseInfo = (Map<String, String>) ssmResult.getResultObject();
    // }
    //
    // // Create Line
    // ZYPEZDItemValueSetter.setValue(newLine.dsOrdPosnNum_LL, confMsg.dsOrdPosnNum_LC.getValue());
    // ZYPEZDItemValueSetter.setValue(newLine.dsCpoLineNum_LL, new BigDecimal(lineNum));
    // ZYPEZDItemValueSetter.setValue(newLine.xxLineNum_LL,
    // NWAL1500CommonLogic.concatLineNum(newLine));
    // ZYPEZDItemValueSetter.setValue(newLine.mdseCd_LL, mdseTMsg.mdseCd);
    // ZYPEZDItemValueSetter.setValue(newLine.mdseNm_LL, mdseTMsg.mdseNm);
    // NWAL1500CommonLogic.setUomPullDownForConfLine(bizMsg, newLine);
    // ZYPEZDItemValueSetter.setValue(newLine.custUomCd_LL, mdseInfo.get("PKG_UOM_CD"));
    // ZYPEZDItemValueSetter.setValue(newLine.entCpoDtlDealSlsAmt_LL, BigDecimal.ZERO);
    // ZYPEZDItemValueSetter.setValue(newLine.entDealNetUnitPrcAmt_LL, BigDecimal.ZERO);
    // ZYPEZDItemValueSetter.setValue(newLine.prcCatgCd_LL, bizMsg.prcCatgCd);
    // ZYPEZDItemValueSetter.setValue(newLine.prcCatgNm_LL, bizMsg.prcCatgNm);
    // String primaryLineCatgCd = NWAL1500CommonLogic.createLineCatgPulldown(bizMsg,
    // bizMsg.slsDt.getValue());
    // NWAL1500CommonLogicForCategory.setInitValueForLineCatgPulldown(bizMsg, primaryLineCatgCd,
    // null);
    //
    // // Call NWZC1800 Default WH API
    // // 2015/11/20 S21_NA#934 Add isMdseTangible
    // // if (ZYPCommonFunc.hasValue(confMsg.shipToCustCd_LC) &&
    // // NWAL1500CommonLogic.isMdseTangible(bizMsg.glblCmpyCd.getValue(),
    // // newLine.mdseCd_LL.getValue())) {
    // // S21_NA#2522
    // if (ZYPCommonFunc.hasValue(confMsg.shipToCustCd_LC) &&
    // NWAL1500CommonLogic.needsWh(bizMsg.glblCmpyCd.getValue(), newLine.mdseCd_LL.getValue())) {
    // NWZC180001PMsg pMsg = new NWZC180001PMsg();
    // if (!NWAL1500CommonLogic.callDefWhApiForLineConf(bizMsg, pMsg,
    // newLine.dsOrdPosnNum_LL.getValue(), newLine.mdseCd_LL.getValue(), BigDecimal.ZERO)) {
    // return false;
    // }
    //
    // String rtlWhCd = pMsg.rtlWhCd.getValue();
    // String rtlSwhCd = pMsg.rtlSwhCd.getValue();
    //
    // ZYPEZDItemValueSetter.setValue(newLine.rtlWhCd_LL, rtlWhCd);
    // ZYPEZDItemValueSetter.setValue(newLine.rtlWhNm_LL, NWAL1500CommonLogic.getRtlWhNm(bizMsg,
    // rtlWhCd));
    // ZYPEZDItemValueSetter.setValue(newLine.rtlSwhCd_LL, rtlSwhCd);
    // ZYPEZDItemValueSetter.setValue(newLine.rtlSwhNm_LL, NWAL1500CommonLogic.getRtlSubWhNm(bizMsg,
    // rtlWhCd, rtlSwhCd));
    // ZYPEZDItemValueSetter.setValue(newLine.ordLineSrcCd_LL, pMsg.ordLineSrcCd);
    // }
    // ZYPEZDItemValueSetter.setValue(newLine.supdLockFlg_LL, ZYPConstant.FLG_OFF_N);
    // ZYPEZDItemValueSetter.setValue(newLine.flPrcListCd_LL, bizMsg.flPrcListCd);
    // ZYPEZDItemValueSetter.setValue(newLine.flPrcListDescTxt_LL, bizMsg.flPrcListDescTxt);
    // ZYPEZDItemValueSetter.setValue(newLine.dealPrcListPrcAmt_LL, BigDecimal.ZERO);
    // ZYPEZDItemValueSetter.setValue(newLine.cpoDtlDealTaxAmt_LL, BigDecimal.ZERO);
    // ZYPEZDItemValueSetter.setValue(newLine.coaMdseTpCd_LL, mdseInfo.get("COA_MDSE_TP_CD"));
    // ZYPEZDItemValueSetter.setValue(newLine.coaMdseTpDescTxt_LL,
    // mdseInfo.get("COA_MDSE_TP_DESC_TXT"));
    // ZYPEZDItemValueSetter.setValue(newLine.coaProdCd_LL, mdseInfo.get("COA_PROD_CD"));
    // ZYPEZDItemValueSetter.setValue(newLine.coaProdDescTxt_LL, mdseInfo.get("COA_PROD_DESC_TXT"));
    // ZYPEZDItemValueSetter.setValue(newLine.prcBaseDt_LL, bizMsg.slsDt);
    // ZYPEZDItemValueSetter.setValue(newLine.rddDt_LL, bizMsg.slsDt);
    // ZYPEZDItemValueSetter.setValue(newLine.allocQty_LL, BigDecimal.ZERO);
    // ZYPEZDItemValueSetter.setValue(newLine.shipQty_LL, BigDecimal.ZERO);
    // ZYPEZDItemValueSetter.setValue(newLine.istlQty_LL, BigDecimal.ZERO);
    // ZYPEZDItemValueSetter.setValue(newLine.invQty_LL, BigDecimal.ZERO);
    // ZYPEZDItemValueSetter.setValue(newLine.cancQty_LL, BigDecimal.ZERO);
    //
    // if (isPrntFlg) {
    // ZYPEZDItemValueSetter.setValue(newLine.baseCmptFlg_LL, ZYPConstant.FLG_ON_Y);
    // } else {
    // ZYPEZDItemValueSetter.setValue(newLine.dplyLineRefNum_LL, getLineRefForChildLine(bizMsg,
    // confMsg));
    // ZYPEZDItemValueSetter.setValue(newLine.baseCmptFlg_LL, ZYPConstant.FLG_OFF_N);
    // }
    //
    // return true;
    // }

    /**
     * Get Line Reference For Child Line
     * @param bizMsg NWAL1500CMsg
     * @param confMsg NWAL1500_ASMsg
     * @return Line Reference For Child Line
     */
    public static String getLineRefForChildLine(NWAL1500CMsg bizMsg, NWAL1500_ASMsg confMsg) {

        if (!isExistOrdCatg(bizMsg, false)) {
            return null;
        }

        String dsOrdPosnNum = confMsg.dsOrdPosnNum_LC.getValue();

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1500_BCMsg lineMsg = bizMsg.B.no(i);

            if (dsOrdPosnNum.equals(lineMsg.dsOrdPosnNum_LL.getValue())) {
                return lineMsg.xxLineNum_LL.getValue();
            }
        }

        return null;
    }

    /**
     * Set Item Infomation For OnBlur Item
     * @param bizMsg NWAL1500CMsg
     * @param slctConfIndex Select Config Index
     * @param slctLineIndex Select Line Index
     * @param inputMdseCd Input MDSE Code
     * @param mdseTMsg MDSETMsg
     * @return exist API error : false
     */
    @SuppressWarnings("unchecked")
    public static boolean setItemInfoForOnBlurItem(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex, int slctLineIndex, String inputMdseCd, MDSETMsg mdseTMsg) { // 2018/01/30 S21_NA#19808 Mod

        // 2018/01/30 S21_NA#19808 bizMsg.B, D => glblMsg.B, D
        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getMdseInfo(bizMsg, mdseTMsg.mdseCd.getValue(), false);
        Map<String, String> mdseInfo = new HashMap<String, String>();
        if (!ssmResult.isCodeNotFound()) {
            mdseInfo = (Map<String, String>) ssmResult.getResultObject();
        }

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            // 2018/01/30 S21_NA#19808 Del Start
//            NWAL1500_BCMsg lineMsg = bizMsg.B.no(slctLineIndex);
            // 2018/01/30 S21_NA#19808 Del End
            // 2018/01/30 S21_NA#19808 Add Start
            NWAL1500_BCMsg lineMsg = new NWAL1500_BCMsg(); // 2018/01/30 S21_NA#19808 Mod
            EZDMsg.copy(glblMsg.B.no(slctLineIndex), null, lineMsg, null);
            // 2018/01/30 S21_NA#19808 Add End
            // NWAL1500CommonLogic.setUomPullDownForConfLine(bizMsg, lineMsg);  // QC#17227 2017/03/03 Del
            ZYPEZDItemValueSetter.setValue(lineMsg.mdseCd_LL, inputMdseCd);
            NWAL1500CommonLogic.setUomPullDownForConfLine(bizMsg, lineMsg);     // QC#17227 2017/03/03 Add
            ZYPEZDItemValueSetter.setValue(lineMsg.mdseNm_LL, mdseTMsg.mdseNm);
            ZYPEZDItemValueSetter.setValue(lineMsg.mdseDescShortTxt_LL, mdseInfo.get("MDSE_DESC_SHORT_TXT")); // S21_NA#2334 Add 
            ZYPEZDItemValueSetter.setValue(lineMsg.custUomCd_LL, mdseInfo.get("PKG_UOM_CD"));
            // ZYPEZDItemValueSetter.setValue(lineMsg.entCpoDtlDealSlsAmt_LL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(lineMsg.entDealNetUnitPrcAmt_LL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(lineMsg.prcCatgCd_LL, bizMsg.prcCatgCd);
            ZYPEZDItemValueSetter.setValue(lineMsg.prcCatgNm_LL, bizMsg.prcCatgNm);
            ZYPEZDItemValueSetter.setValue(lineMsg.supdLockFlg_LL, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(lineMsg.flPrcListCd_LL, bizMsg.flPrcListCd);
            ZYPEZDItemValueSetter.setValue(lineMsg.flPrcListNm_LL, bizMsg.flPrcListNm);
            ZYPEZDItemValueSetter.setValue(lineMsg.dealPrcListPrcAmt_LL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(lineMsg.funcUnitFlPrcAmt_LL, BigDecimal.ZERO); // QC#22372 2018/01/10 Add
            ZYPEZDItemValueSetter.setValue(lineMsg.cpoDtlDealTaxAmt_LL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(lineMsg.xxLineTotPrcAmt_LL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(lineMsg.coaMdseTpCd_LL, mdseInfo.get("COA_MDSE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(lineMsg.coaMdseTpDescTxt_LL, mdseInfo.get("COA_MDSE_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(lineMsg.coaProdCd_LL, mdseInfo.get("COA_PROD_CD"));
            ZYPEZDItemValueSetter.setValue(lineMsg.coaProdDescTxt_LL, mdseInfo.get("COA_PROD_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(lineMsg.prcBaseDt_LL, bizMsg.slsDt);
            // ZYPEZDItemValueSetter.setValue(lineMsg.rddDt_LL, bizMsg.slsDt); // S21_NA#5000#80
            ZYPEZDItemValueSetter.setValue(lineMsg.rddDt_LL, bizMsg.addRddDt); // S21_NA#5000#80
            ZYPEZDItemValueSetter.setValue(lineMsg.allocQty_LL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(lineMsg.shipQty_LL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(lineMsg.istlQty_LL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(lineMsg.invQty_LL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(lineMsg.cancQty_LL, BigDecimal.ZERO);
            lineMsg.ordLineStsDescTxt_LL.clear();
            lineMsg.serNum_LL.clear();
            lineMsg.sbstMdseCd_LL.clear();
            lineMsg.vndInvtyLocCd_LL.clear();
            lineMsg.dplyLineRefNum_LL.clear();
            lineMsg.crRebilCd_LL.clear();
            // START 2016/08/01 [S21_NA#12599,ADD]
            lineMsg.crRebilDescTxt_LL.clear();
            // END   2016/08/01 [S21_NA#12599,ADD]

            // QC#14510 Mod Start
            // String leaseByotMdseCd = ZYPCodeDataUtil.getVarCharConstValue(LEASE_BYOT_MDSE_CD, bizMsg.glblCmpyCd.getValue());
            String leaseByotMdseCd = bizMsg.varCharConstVal_LB.getValue();
            // QC#14510 Mod End

            String primaryLineCatgCd = NWAL1500CommonLogic.createLineCatgPulldown(bizMsg, bizMsg.slsDt.getValue());
            if (inputMdseCd.equals(leaseByotMdseCd)) {
                ZYPEZDItemValueSetter.setValue(lineMsg.dsOrdLineCatgCd_LL, DS_ORD_LINE_CATG.LEASE_BUYOUT);
            } else {
                ZYPEZDItemValueSetter.setValue(lineMsg.dsOrdLineCatgCd_LL, primaryLineCatgCd);
            }
            // 2018/01/30 S21_NA#19808 Add Start
            EZDMsg.copy(lineMsg, null, glblMsg.B.no(slctLineIndex), null);
            // 2018/01/30 S21_NA#19808 Add End
        } else {
            // 2018/01/30 S21_NA#19808 Del Start
//            NWAL1500_DCMsg rmaLineMsg = bizMsg.D.no(slctLineIndex);
            // 2018/01/30 S21_NA#19808 Del End
            // 2018/01/30 S21_NA#19808 Add Start
            NWAL1500_DCMsg rmaLineMsg = new NWAL1500_DCMsg();
            EZDMsg.copy(glblMsg.D.no(slctLineIndex), null, rmaLineMsg, null);
            // 2018/01/30 S21_NA#19808 Add End
            // NWAL1500CommonLogic.setUomPullDownForRma(bizMsg, rmaLineMsg);    // QC#17227 2017/03/03 Del
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.mdseCd_RL, mdseTMsg.mdseCd);
            NWAL1500CommonLogic.setUomPullDownForRma(bizMsg, rmaLineMsg);       // QC#17227 2017/03/03 Add
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.mdseNm_RL, mdseTMsg.mdseNm);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.mdseDescShortTxt_RL, mdseInfo.get("MDSE_DESC_SHORT_TXT")); // S21_NA#2334 Add 
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.custUomCd_RL, mdseInfo.get("PKG_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.cpoDtlDealGrsAmt_RL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.entDealNetUnitPrcAmt_RL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.prcCatgCd_RL, bizMsg.prcCatgCd);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.prcCatgNm_RL, bizMsg.prcCatgNm);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.flPrcListCd_RL, bizMsg.flPrcListCd);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.flPrcListNm_RL, bizMsg.flPrcListNm);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.dealPrcListPrcAmt_RL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.funcUnitFlPrcAmt_RL, BigDecimal.ZERO); // QC#22372 2018/01/10 Add
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.cpoDtlDealTaxAmt_RL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.xxLineTotPrcAmt_RL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.coaMdseTpCd_RL, mdseInfo.get("COA_MDSE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.coaMdseTpDescTxt_RL, mdseInfo.get("COA_MDSE_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.coaProdCd_RL, mdseInfo.get("COA_PROD_CD"));
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.coaProdDescTxt_RL, mdseInfo.get("COA_PROD_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.prcBaseDt_RL, bizMsg.slsDt);
            // ZYPEZDItemValueSetter.setValue(rmaLineMsg.rqstPickUpDt_RL, bizMsg.slsDt); // S21_NA#5000#80
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rqstPickUpDt_RL, bizMsg.addRddDt); // S21_NA#5000#80
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtrnQty_RL, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.cancQty_RL, BigDecimal.ZERO);
            rmaLineMsg.rtrnLineStsDescTxt_RL.clear();
            rmaLineMsg.serNum_RL.clear();
            rmaLineMsg.dplyLineRefNum_RL.clear();

            String primaryLineCatgRmaCd = NWAL1500CommonLogic.createLineCatgPulldownForRma(bizMsg, glblMsg, bizMsg.slsDt.getValue());
            NWAL1500CommonLogicForCategory.setInitValueForLineCatgPulldown(glblMsg, null, primaryLineCatgRmaCd); // 2018/01/30 S21_NA#19808 Mod

            // 2018/01/30 S21_NA#19808 Add Start
            for (int i = 0; i < glblMsg.D.no(slctLineIndex).dsOrdLineCatgCd_CR.length(); i++) {
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.dsOrdLineCatgCd_CR.no(i), glblMsg.D.no(slctLineIndex).dsOrdLineCatgCd_CR.no(i));
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.dsOrdLineCatgDescTxt_NR.no(i), glblMsg.D.no(slctLineIndex).dsOrdLineCatgDescTxt_NR.no(i));
            }
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.dsOrdLineCatgCd_RL, primaryLineCatgRmaCd);
            EZDMsg.copy(rmaLineMsg, null, glblMsg.D.no(slctLineIndex), null);
            // 2018/01/30 S21_NA#19808 Add End
        }

        return true;
    }

 // 2018/01/30 S21_NA#19808 Add Start
    /**
     * Get Config Index in Biz. Message
     * @param bizMsg NWAL1500CMsg
     * @param slctLineIndex Select Line Index
     * @return Config Index
     */
    public static int getConfIndexInBizMsg(NWAL1500CMsg bizMsg, int slctLineIndex) {

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            String dsOrdPosnNumOfLine = bizMsg.B.no(slctLineIndex).dsOrdPosnNum_LL.getValue();

            for (int confLineIndex = 0; confLineIndex < bizMsg.A.getValidCount(); confLineIndex++) {
                String dsOrdPosnNumOfConfig = bizMsg.A.no(confLineIndex).dsOrdPosnNum_LC.getValue();
                if (dsOrdPosnNumOfLine.equals(dsOrdPosnNumOfConfig)) {
                    return confLineIndex;
                }
            }
        } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
            String dsOrdPosnNumOfLine = bizMsg.D.no(slctLineIndex).dsOrdPosnNum_RL.getValue();

            for (int confLineIndex = 0; confLineIndex < bizMsg.C.getValidCount(); confLineIndex++) {
                String dsOrdPosnNumOfConfig = bizMsg.C.no(confLineIndex).dsOrdPosnNum_RC.getValue();
                if (dsOrdPosnNumOfLine.equals(dsOrdPosnNumOfConfig)) {
                    return confLineIndex;
                }
            }
        }

        return 0;
    }

    /**
     * Get Config Index
     * @param xxDplyTab Tab Name
     * @param glblMsg Global Message
     * @param lineMsg EZDCMsg for NWAL1500_BCMsg or NWAL1500_DCMsg
     * @return
     */
    public static int getConfIndex(String xxDplyTab, NWAL1500SMsg glblMsg, EZDCMsg lineMsg) {

        int slctLineIndex = NWAL1500CommonLogicForPagination.getDetectedDetailIndexOfGlobalMessage(lineMsg, glblMsg);

        return getConfIndex(xxDplyTab, glblMsg, slctLineIndex);
    }
    // 2018/01/30 S21_NA#19808 Add End
    /**
     * Get Config Index
     * @param xxDplyTab Tab Name
     * @param bizMsg Global Message
     * @param slctLineIndex Select Line Index (Global Message B Or D)
     * @return Config Index (In Global Message)
     */
    public static int getConfIndex(String xxDplyTab, NWAL1500SMsg glblMsg, int slctLineIndex) { // 2018/01/30 S21_NA#19808 Mod

        if (TAB_LINE_CONFIG.equals(xxDplyTab)) {
            String dsOrdPosnNumOfLine = glblMsg.B.no(slctLineIndex).dsOrdPosnNum_LL.getValue();

            for (int confLineIndex = 0; confLineIndex < glblMsg.A.getValidCount(); confLineIndex++) {
                String dsOrdPosnNumOfConfig = glblMsg.A.no(confLineIndex).dsOrdPosnNum_LC.getValue();
                if (dsOrdPosnNumOfLine.equals(dsOrdPosnNumOfConfig)) {
                    return confLineIndex;
                }
            }
        } else if (TAB_RMA.equals(xxDplyTab)) {
            String dsOrdPosnNumOfLine = glblMsg.D.no(slctLineIndex).dsOrdPosnNum_RL.getValue();

            for (int confLineIndex = 0; confLineIndex < glblMsg.C.getValidCount(); confLineIndex++) {
                String dsOrdPosnNumOfConfig = glblMsg.C.no(confLineIndex).dsOrdPosnNum_RC.getValue();
                if (dsOrdPosnNumOfLine.equals(dsOrdPosnNumOfConfig)) {
                    return confLineIndex;
                }
            }
        }

        return 0;
    }

    /**
     * Set Line Reference
     * @param bizMsg NWAL1500CMsg
     * @param slctConfIndex Select Config Index
     */
    public static void setLineRef(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex) {

        // 2018/01/30  S21_NA#19808 bizMsg.A, B, C, D => glblMsg.A, B, C, D
        if (!isExistOrdCatg(bizMsg, false)) {
            return;
        }

        // get Base Component Line Number
        String baseComtLineNum = null;
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            String dsOrdPosnNumOfConfig = glblMsg.A.no(slctConfIndex).dsOrdPosnNum_LC.getValue();
            int baseCmptIndex = 0;

            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                String dsOrdPosnNumOfLine = glblMsg.B.no(i).dsOrdPosnNum_LL.getValue();
                String baseCmptFlg = glblMsg.B.no(i).baseCmptFlg_LL.getValue();

                if (dsOrdPosnNumOfConfig.equals(dsOrdPosnNumOfLine) && ZYPConstant.FLG_ON_Y.equals(baseCmptFlg)) {
                    baseComtLineNum = glblMsg.B.no(i).xxLineNum_LL.getValue();
                    baseCmptIndex = i;
                    break;
                }
            }

            if (!ZYPCommonFunc.hasValue(baseComtLineNum)) {
                for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                    String dsOrdPosnNumOfLine = glblMsg.B.no(i).dsOrdPosnNum_LL.getValue();
                    if (dsOrdPosnNumOfConfig.equals(dsOrdPosnNumOfLine)) {
                        glblMsg.B.no(i).dplyLineRefNum_LL.clear();
                    }
                }
                return;
            }

            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                if (baseCmptIndex == i) {
                    continue;
                }

                String dsOrdPosnNumOfLine = glblMsg.B.no(i).dsOrdPosnNum_LL.getValue();
                if (dsOrdPosnNumOfConfig.equals(dsOrdPosnNumOfLine)) {
                    // if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).ordLineStsDescTxt_LL)) { 2016/05/16 S21_NA#8144 Dell
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).dplyLineRefNum_LL, baseComtLineNum);
                    // } 2016/05/16 S21_NA#8144 Del Start
                }
            }

        } else {
            String dsOrdPosnNumOfConfig = glblMsg.C.no(slctConfIndex).dsOrdPosnNum_RC.getValue();
            int baseCmptIndex = 0;

            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                String dsOrdPosnNumOfLine = glblMsg.D.no(i).dsOrdPosnNum_RL.getValue();
                String baseCmptFlg = glblMsg.D.no(i).baseCmptFlg_RL.getValue();

                if (dsOrdPosnNumOfConfig.equals(dsOrdPosnNumOfLine) && ZYPConstant.FLG_ON_Y.equals(baseCmptFlg)) {
                    baseComtLineNum = glblMsg.D.no(i).xxLineNum_RL.getValue();
                    baseCmptIndex = i;
                    break;
                }
            }

            if (!ZYPCommonFunc.hasValue(baseComtLineNum)) {
                return;
            }

            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                if (baseCmptIndex == i) {
                    continue;
                }

                String dsOrdPosnNumOfLine = glblMsg.D.no(i).dsOrdPosnNum_RL.getValue();
                if (dsOrdPosnNumOfConfig.equals(dsOrdPosnNumOfLine)) {
                    // if (!ZYPCommonFunc.hasValue(glblMsg.D.no(i).rtrnLineStsDescTxt_RL)) { 2016/05/16 S21_NA#8144 Del
                        ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).dplyLineRefNum_RL, baseComtLineNum);
                    // } 2016/05/16 S21_NA#8144 Del
                }
            }
        }
    }

    /**
     * check Exist Order Category
     * @param bizMsg NWAL1500CMsg
     * @param isCatgOnly Order Category Only
     * @return true: exist
     */
    public static boolean isExistOrdCatg(NWAL1500CMsg bizMsg, boolean isCatgOnly) {

        if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd)) {
            return false;
        }

        return NWAL1500QueryForLineConfig.getInstance().isExistOrdCatg(bizMsg, ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS, isCatgOnly);
    }

    /**
     * Set Each Quantity
     * @param bizMsg NWAL1500CMsg
     * @param slctLineIndex Select Line Index
     * @param mdseCd Merchandise Code
     * @return Each Qty
     */
    public static BigDecimal setEachQty(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctLineIndex, String mdseCd) { // 2018/01/30 S21_NA#19808

        // 2018/01/30 S21_NA#19808 bizMsg.B, D => glblMsg.B, D
        BigDecimal eachQty = null;

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(slctLineIndex);
            if (ZYPCommonFunc.hasValue(lineMsg.ordCustUomQty_LL)) {
                eachQty = getEachQty(bizMsg, mdseCd, lineMsg.custUomCd_LL.getValue(), lineMsg.ordCustUomQty_LL.getValue());
                ZYPEZDItemValueSetter.setValue(lineMsg.ordQty_LL, eachQty);
            }
        } else {
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(slctLineIndex);
            if (ZYPCommonFunc.hasValue(rmaLineMsg.ordCustUomQty_RL)) {
                eachQty = getEachQty(bizMsg, mdseCd, rmaLineMsg.custUomCd_RL.getValue(), rmaLineMsg.ordCustUomQty_RL.getValue());
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.ordQty_RL, eachQty);
            }
        }

        return eachQty;
    }

    /**
     * Get Each Quantity
     * @param bizMsg NWAL1500CMsg
     * @param mdseCd Merchandise Code
     * @param pkgUomCd Unit of Measure Code
     * @param uomQty Unit of Measure uomQty
     * @return Each Quantity
     */
    public static BigDecimal getEachQty(NWAL1500CMsg bizMsg, String mdseCd, String pkgUomCd, BigDecimal uomQty) {

        MDSE_STORE_PKGTMsg tMsg = new MDSE_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(tMsg.pkgUomCd, pkgUomCd);
        tMsg = (MDSE_STORE_PKGTMsg) S21CacheTBLAccessor.findByKey(tMsg); // 2018/03/08 S21_NA#19808

        if (tMsg == null) {
            return null;
        }

        return uomQty.multiply(tMsg.inEachQty.getValue());
    }

    // 2018/03/14 S21_NA#24117-1 add start
    public static void deriveLinePrice(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex, int slctLineIndex) {
        deriveLinePrice(bizMsg, glblMsg, slctConfIndex, slctLineIndex, false);
    }
    // 2018/03/14 S21_NA#24117-1 add end

    /**
     * Derive Line Price
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL150SCMsg
     * @param slctConfIndex Select Config Index
     * @param slctLineIndex Select Line Index
     */
    // 2018/03/14 S21_NA#24117-1 mod start
//    public static void deriveLinePrice(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex, int slctLineIndex) {
    public static void deriveLinePrice(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex, int slctLineIndex, boolean copyCompare) {
    // 2018/03/14 S21_NA#24117-1 mod end

        // 2018/01/30 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D
        NWAL1500_BSMsg lineMsg = null; // 2018/01/30 S21_NA#19808 Mod
        NWAL1500_DSMsg rmaLineMsg = null; // 2018/01/30 S21_NA#19808 Mod

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            lineMsg = glblMsg.B.no(slctLineIndex);
            if (!ZYPCommonFunc.hasValue(lineMsg.ordCustUomQty_LL)) {
                return;
            }
        } else {
            rmaLineMsg = glblMsg.D.no(slctLineIndex);
            if (!ZYPCommonFunc.hasValue(rmaLineMsg.ordCustUomQty_RL)) {
                return;
            }
        }

        NWAL1500CommonLogic.numberingOrderLineNumber(bizMsg, glblMsg); // 2018/01/30 S21_NA#19808 Mod
        // QC#31345 2019/04/26 Mod Start
        // delSlctLineCalcBase(bizMsg, glblMsg, slctLineIndex); // 2018/01/30 S21_NA#19808 Mod
        // QC#52462 2019/08/20 Del Start
        //if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
        //    delSlctLineCalcBase(bizMsg, glblMsg, slctLineIndex);
        //}
        // QC#52462 2019/08/20 Del End
        // QC#31345 2019/04/26 Mod End

        String cpoSrcTpCd = bizMsg.cpoSrcTpCd.getValue();
        boolean hasPricingApiError = false;

        String slctCpoDtlLineNum = null;
        String slctCpoDtlLineSubNum = null;
        String configCatgCd = null;
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            slctCpoDtlLineNum = lineMsg.cpoDtlLineNum_LL.getValue();
            slctCpoDtlLineSubNum = lineMsg.cpoDtlLineSubNum_LL.getValue();
            configCatgCd = CONFIG_CATG.OUTBOUND;
            lineMsg.xxErrFlg_LL.clear();
        } else {
            slctCpoDtlLineNum = rmaLineMsg.cpoDtlLineNum_RL.getValue();
            slctCpoDtlLineSubNum = rmaLineMsg.cpoDtlLineSubNum_RL.getValue();
            configCatgCd = CONFIG_CATG.INBOUND;
            rmaLineMsg.xxErrFlg_RL.clear();
        }

        if (!CPO_SRC_TP.CHANGE_ORDER_MODIFICATION.equals(cpoSrcTpCd)) {
            // call NWZC1570 Pricing API
            NWZC157001PMsg prcApiPMsg = callPricingApiOfGetBasePriceMode(bizMsg, glblMsg, slctConfIndex, slctLineIndex);
            if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
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

            NWZC157002PMsg prcLinePMsg = prcApiPMsg.NWZC157002PMsg.no(0);
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                ZYPEZDItemValueSetter.setValue(lineMsg.xxErrFlg_LL, prcLinePMsg.xxErrFlg);
            } else {
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.xxErrFlg_RL, prcLinePMsg.xxErrFlg);
            }
            if (S21ApiUtil.isXxMsgId(prcLinePMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcLinePMsg);
                for (int j = 0; j < msgList.size(); j++) {
                    S21ApiMessage msg = msgList.get(j);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);
                    if (msgId.endsWith("E")) {
                        if (ZYPConstant.FLG_ON_Y.equals(prcLinePMsg.xxErrFlg.getValue())) {
                            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                                lineMsg.entCpoDtlDealSlsAmt_LL.clear();
                            } else {
                                rmaLineMsg.entCpoDtlDealSlsAmt_RL.clear();
                            }
                        }
                        return;
                    }
                }
            }
            // QC#31345 2019/04/26 Add Start
            delSlctLineCalcBase(bizMsg, glblMsg, slctLineIndex);
            // QC#31345 2019/04/26 Add End
            // set Calc Base
            int validCnt = glblMsg.I.getValidCount();
            BigDecimal baseAutoPrcAmtRate = null;
            boolean isExistBasePrc = false;

            for (int i = 0; i < prcLinePMsg.NWZC157003PMsg.getValidCount(); i++) {
                NWZC157003PMsg prcElementPMsg = prcLinePMsg.NWZC157003PMsg.no(i);

                NWAL1500_ISMsg iCMsg = glblMsg.I.no(validCnt);
                ZYPEZDItemValueSetter.setValue(iCMsg.xxLineNum_LP, glblMsg.B.no(slctLineIndex).xxLineNum_LL);
                iCMsg.xxCellIdx_LP.setValue(i);
                ZYPEZDItemValueSetter.setValue(iCMsg.cpoDtlLineNum_LP, slctCpoDtlLineNum);
                ZYPEZDItemValueSetter.setValue(iCMsg.cpoDtlLineSubNum_LP, slctCpoDtlLineSubNum);
                ZYPEZDItemValueSetter.setValue(iCMsg.configCatgCd_LP, configCatgCd);
                ZYPEZDItemValueSetter.setValue(iCMsg.prcCondTpCd_LP, prcElementPMsg.prcCondTpCd);
                ZYPEZDItemValueSetter.setValue(iCMsg.prcCondTpDescTxt_LP, prcElementPMsg.prcCondTpDescTxt);
                ZYPEZDItemValueSetter.setValue(iCMsg.prcDtlGrpCd_LP, prcElementPMsg.prcDtlGrpCd);
                ZYPEZDItemValueSetter.setValue(iCMsg.prcJrnlGrpCd_LP, prcElementPMsg.prcJrnlGrpCd);
                ZYPEZDItemValueSetter.setValue(iCMsg.prcCatgCd_LP, prcElementPMsg.prcCatgCd);
                ZYPEZDItemValueSetter.setValue(iCMsg.prcCondManEntryFlg_LP, prcElementPMsg.prcCondManEntryFlg);
                ZYPEZDItemValueSetter.setValue(iCMsg.prcCondManAddFlg_LP, prcElementPMsg.prcCondManAddFlg);
                ZYPEZDItemValueSetter.setValue(iCMsg.prcCondManDelFlg_LP, prcElementPMsg.prcCondManDelFlg);
                ZYPEZDItemValueSetter.setValue(iCMsg.pkgUomCd_LP, prcElementPMsg.pkgUomCd);
                ZYPEZDItemValueSetter.setValue(iCMsg.prcCondUnitCd_LP, prcElementPMsg.prcCondUnitCd);
                ZYPEZDItemValueSetter.setValue(iCMsg.prcCalcMethCd_LP, prcElementPMsg.prcCalcMethCd);
                ZYPEZDItemValueSetter.setValue(iCMsg.autoPrcCcyCd_LP, prcElementPMsg.autoPrcCcyCd);
                ZYPEZDItemValueSetter.setValue(iCMsg.autoPrcAmtRate_LP, prcElementPMsg.autoPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(iCMsg.maxPrcAmtRate_LP, prcElementPMsg.maxPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(iCMsg.minPrcAmtRate_LP, prcElementPMsg.minPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(iCMsg.manPrcAmtRate_LP, prcElementPMsg.manPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(iCMsg.calcPrcAmtRate_LP, prcElementPMsg.calcPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(iCMsg.unitPrcAmt_LP, prcElementPMsg.unitPrcAmt);
                ZYPEZDItemValueSetter.setValue(iCMsg.dsMdsePrcPk_LP, prcElementPMsg.dsMdsePrcPk);
                ZYPEZDItemValueSetter.setValue(iCMsg.specCondPrcPk_LP, prcElementPMsg.specCondPrcPk);
                ZYPEZDItemValueSetter.setValue(iCMsg.frtPerWtPk_LP, prcElementPMsg.frtPerWtPk);
                ZYPEZDItemValueSetter.setValue(iCMsg.ordPrcCalcBasePk_LP, prcElementPMsg.ordPrcCalcBasePk);
                // QC#9700  2018/09/03 Add Start
                ZYPEZDItemValueSetter.setValue(iCMsg.prcRuleApplyFlg_LP, prcElementPMsg.prcRuleApplyFlg);
                ZYPEZDItemValueSetter.setValue(iCMsg.prcRulePrcdPk_LP, prcElementPMsg.prcRulePrcdPk);
                // QC#9700  2018/09/03 Add End

                if (PRC_DTL_GRP.BASE_PRICE.equals(prcElementPMsg.prcDtlGrpCd.getValue())) {
                    baseAutoPrcAmtRate = prcElementPMsg.autoPrcAmtRate.getValue();
                    isExistBasePrc = true;
                }
                // 2018/03/14 S21_NA#24117-1 add start
                if (copyCompare) {
                    NWAL1500_XSMsg xSMsg = (NWAL1500_XSMsg) NWAL1500CommonLogicForConfigId.insertNewLine(glblMsg.X, validCnt);
                    EZDMsg.copy(iCMsg, null, xSMsg, null);
                }
                // 2018/03/14 S21_NA#24117-1 add end
                validCnt++;
            }
            glblMsg.I.setValidCount(validCnt);

            // set Field Amount
            NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(0);
            // Add 2016/03/01 QC#4375 Start
            BigDecimal charges;
            if (ZYPCommonFunc.hasValue(prcTotalPMsg.xxTotSpclChrgAmt.getValue())) {
                charges = prcTotalPMsg.xxTotFrtAmt.getValue().subtract(prcTotalPMsg.xxTotSpclChrgAmt.getValue());
            } else {
                charges = prcTotalPMsg.xxTotFrtAmt.getValue();
            }
            // Add 2016/03/01 QC#4375 End
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                ZYPEZDItemValueSetter.setValue(lineMsg.prcCatgCd_LL, prcLinePMsg.prcCatgCd.getValue());
                ZYPEZDItemValueSetter.setValue(lineMsg.prcCatgNm_LL, NWAL1500CommonLogic.getPrcCatgNm(bizMsg, prcLinePMsg.prcCatgCd.getValue()));
                ZYPEZDItemValueSetter.setValue(lineMsg.entCpoDtlDealSlsAmt_LL, prcTotalPMsg.xxUnitGrsPrcAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.entDealNetUnitPrcAmt_LL, prcTotalPMsg.xxUnitNetPrcAmt);
                if (isExistBasePrc) {
                    ZYPEZDItemValueSetter.setValue(lineMsg.dealPrcListPrcAmt_LL, baseAutoPrcAmtRate);
                } else {
                    ZYPEZDItemValueSetter.setValue(lineMsg.dealPrcListPrcAmt_LL, BigDecimal.ZERO);
                }
                ZYPEZDItemValueSetter.setValue(lineMsg.cpoDtlDealTaxAmt_LL, prcTotalPMsg.xxTotTaxAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.xxLineTotPrcAmt_LL, prcTotalPMsg.xxTotAmt);

                // Add 2016/03/01 QC#4375 Start
                ZYPEZDItemValueSetter.setValue(lineMsg.lineDealSubTotAmt_LL,    prcTotalPMsg.xxTotNetPrcAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.lineDealChrgAmt_LL,      charges);
                ZYPEZDItemValueSetter.setValue(lineMsg.lineDealTaxAmt_LL,       prcTotalPMsg.xxTotTaxAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.lineDealTotAmt_LL,       prcTotalPMsg.xxTotAmt);
                // Add 2016/03/01 QC#4375 End
            } else {
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.prcCatgCd_RL, prcLinePMsg.prcCatgCd);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.prcCatgNm_RL, NWAL1500CommonLogic.getPrcCatgNm(bizMsg, prcLinePMsg.prcCatgCd.getValue()));
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.entCpoDtlDealSlsAmt_RL, prcTotalPMsg.xxUnitGrsPrcAmt);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.entDealNetUnitPrcAmt_RL, prcTotalPMsg.xxUnitNetPrcAmt);
                if (isExistBasePrc) {
                    ZYPEZDItemValueSetter.setValue(rmaLineMsg.dealPrcListPrcAmt_RL, baseAutoPrcAmtRate);
                } else {
                    ZYPEZDItemValueSetter.setValue(rmaLineMsg.dealPrcListPrcAmt_RL, BigDecimal.ZERO);
                }
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.cpoDtlDealTaxAmt_RL, prcTotalPMsg.xxTotTaxAmt);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.xxLineTotPrcAmt_RL, prcTotalPMsg.xxTotAmt);

                // Add 2016/03/01 QC#4375 Start
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.lineDealSubTotAmt_RL,    prcTotalPMsg.xxTotNetPrcAmt);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.lineDealChrgAmt_RL,      charges);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.lineDealTaxAmt_RL,       prcTotalPMsg.xxTotTaxAmt);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.lineDealTotAmt_RL,       prcTotalPMsg.xxTotAmt);
                // Add 2016/03/01 QC#4375 End
            }
        }

        if (CPO_SRC_TP.CHANGE_ORDER_MODIFICATION.equals(cpoSrcTpCd) || hasPricingApiError) {
            // set Calc Base
            int validCnt = glblMsg.I.getValidCount();
            NWAL1500_ISMsg iCMsg = glblMsg.I.no(validCnt);
            ZYPEZDItemValueSetter.setValue(iCMsg.xxLineNum_LP, glblMsg.B.no(slctLineIndex).xxLineNum_LL);
            iCMsg.xxCellIdx_LP.setValue(0);
            ZYPEZDItemValueSetter.setValue(iCMsg.cpoDtlLineNum_LP, slctCpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(iCMsg.cpoDtlLineSubNum_LP, slctCpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(iCMsg.configCatgCd_LP, configCatgCd);
            ZYPEZDItemValueSetter.setValue(iCMsg.prcDtlGrpCd_LP, PRC_DTL_GRP.BASE_PRICE);
            ZYPEZDItemValueSetter.setValue(iCMsg.prcJrnlGrpCd_LP, PRC_JRNL_GRP.BASE_PRICE);
            ZYPEZDItemValueSetter.setValue(iCMsg.prcCondManEntryFlg_LP, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(iCMsg.prcCondManAddFlg_LP, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(iCMsg.prcCondManDelFlg_LP, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(iCMsg.pkgUomCd_LP, PKG_UOM.EACH);
            ZYPEZDItemValueSetter.setValue(iCMsg.prcCondUnitCd_LP, PRC_COND_UNIT.AMT);
            ZYPEZDItemValueSetter.setValue(iCMsg.prcCalcMethCd_LP, PRC_CALC_METH.EACH_ACCOUNT);
            ZYPEZDItemValueSetter.setValue(iCMsg.autoPrcCcyCd_LP, CCY.US_DOLLAR);
            ZYPEZDItemValueSetter.setValue(iCMsg.autoPrcAmtRate_LP, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(iCMsg.manPrcAmtRate_LP, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(iCMsg.calcPrcAmtRate_LP, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(iCMsg.unitPrcAmt_LP, BigDecimal.ZERO);
            validCnt++;
            glblMsg.I.setValidCount(validCnt);

            // set Field Amount
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                NWAL1500_BSMsg bCMsg = glblMsg.B.no(slctLineIndex);
                ZYPEZDItemValueSetter.setValue(bCMsg.entCpoDtlDealSlsAmt_LL, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(bCMsg.entDealNetUnitPrcAmt_LL, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(bCMsg.dealPrcListPrcAmt_LL, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(bCMsg.cpoDtlDealTaxAmt_LL, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(bCMsg.xxLineTotPrcAmt_LL, BigDecimal.ZERO);
            } else {
                NWAL1500_DSMsg dCMsg = glblMsg.D.no(slctLineIndex);
                ZYPEZDItemValueSetter.setValue(dCMsg.entCpoDtlDealSlsAmt_RL, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dCMsg.entDealNetUnitPrcAmt_RL, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dCMsg.dealPrcListPrcAmt_RL, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dCMsg.cpoDtlDealTaxAmt_RL, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dCMsg.xxLineTotPrcAmt_RL, BigDecimal.ZERO);
            }
        }

//        ZYPTableUtil.clear(glblMsg.I);
//        EZDMsg.copy(glblMsg.I, null, glblMsg.I, null);
    }

    // QC#22372 2018/01/10 Add Start
    public static void deriveLineFloorPrice(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex, int slctLineIndex) {
        // 2018/01/30 S21_NA#19808 Mod glblMsg.B, D => glblMsg.B, D
        NWAL1500_BSMsg lineMsg = null;
        NWAL1500_DSMsg rmaLineMsg = null;
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            lineMsg = glblMsg.B.no(slctLineIndex);
            if (!ZYPCommonFunc.hasValue(lineMsg.mdseCd_LL)
                    || !ZYPCommonFunc.hasValue(lineMsg.ordCustUomQty_LL)
                    || !ZYPCommonFunc.hasValue(lineMsg.flPrcListNm_LL)) {
                return;
            }
        } else {
            rmaLineMsg = glblMsg.D.no(slctLineIndex);
            if (!ZYPCommonFunc.hasValue(rmaLineMsg.mdseCd_RL)
                    || !ZYPCommonFunc.hasValue(rmaLineMsg.ordCustUomQty_RL)
                    || !ZYPCommonFunc.hasValue(rmaLineMsg.flPrcListNm_RL)) {
                return;
            }
        }
        String cpoSrcTpCd = bizMsg.cpoSrcTpCd.getValue();
        if (!CPO_SRC_TP.CHANGE_ORDER_MODIFICATION.equals(cpoSrcTpCd)) {
            NWZC157001PMsg floorPrcApiPMsg = callPricingApiOfGetFloorPriceMode(bizMsg, glblMsg, slctConfIndex, slctLineIndex);

            NWZC157004PMsg floorPrcePMsg = floorPrcApiPMsg.NWZC157004PMsg.no(0);
            if (S21ApiUtil.isXxMsgId(floorPrcePMsg)) {
                ZYPEZDItemValueSetter.setValue(floorPrcePMsg.xxUnitNetPrcAmt, BigDecimal.ZERO);
            }
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                ZYPEZDItemValueSetter.setValue(lineMsg.funcUnitFlPrcAmt_LL, floorPrcePMsg.xxUnitNetPrcAmt);
            } else {
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.funcUnitFlPrcAmt_RL, floorPrcePMsg.xxUnitNetPrcAmt);
            }

        } else {
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                    NWAL1500_BSMsg bCMsg = glblMsg.B.no(slctLineIndex);
                    ZYPEZDItemValueSetter.setValue(bCMsg.funcUnitFlPrcAmt_LL, BigDecimal.ZERO);
                } else {
                    NWAL1500_DSMsg dCMsg = glblMsg.D.no(slctLineIndex);
                    ZYPEZDItemValueSetter.setValue(dCMsg.funcUnitFlPrcAmt_RL, BigDecimal.ZERO);
                }
            }
        }
    }
    // QC#22372 2018/01/10 Add End

    /**
     * Derive Line Manual Price
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL150SCMsg
     * @param slctConfIndex Select Config Index
     * @param slctLineIndex Select Line Index
     */
    public static void deriveLineManPrice(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex, int slctLineIndex) {
        // 2018/01/30 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D
        NWAL1500_BSMsg lineMsg = null;
        NWAL1500_DSMsg rmaLineMsg = null;

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            lineMsg = glblMsg.B.no(slctLineIndex);
            if (!ZYPCommonFunc.hasValue(lineMsg.ordCustUomQty_LL)
                    || !ZYPCommonFunc.hasValue(lineMsg.entCpoDtlDealSlsAmt_LL)) {
                return;
            }
        } else {
            rmaLineMsg = glblMsg.D.no(slctLineIndex);
            if (!ZYPCommonFunc.hasValue(rmaLineMsg.ordCustUomQty_RL)
                    || !ZYPCommonFunc.hasValue(rmaLineMsg.entCpoDtlDealSlsAmt_RL)) {
                return;
            }
        }

        // QC6480
        //ZYPEZDItemValueSetter.setValue(basePriceLine.manPrcAmtRate_LP, entCpoDtlDealSlsAmt);
        //ZYPEZDItemValueSetter.setValue(basePriceLine.prcCondManEntryFlg_LP, ZYPConstant.FLG_ON_Y);

        NWAL1500CommonLogic.numberingOrderLineNumber(bizMsg, glblMsg); // 2018/01/30 S21_NA#19808 Mod

        String cpoSrcTpCd = bizMsg.cpoSrcTpCd.getValue();
        boolean hasPricingApiError = false;

        String slctCpoDtlLineNum = null;
        String slctCpoDtlLineSubNum = null;
        String configCatgCd = null;
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            slctCpoDtlLineNum = lineMsg.cpoDtlLineNum_LL.getValue();
            slctCpoDtlLineSubNum = lineMsg.cpoDtlLineSubNum_LL.getValue();
            configCatgCd = CONFIG_CATG.OUTBOUND;
            lineMsg.xxErrFlg_LL.clear();
        } else {
            slctCpoDtlLineNum = rmaLineMsg.cpoDtlLineNum_RL.getValue();
            slctCpoDtlLineSubNum = rmaLineMsg.cpoDtlLineSubNum_RL.getValue();
            configCatgCd = CONFIG_CATG.INBOUND;
            rmaLineMsg.xxErrFlg_RL.clear();
        }

        if (!CPO_SRC_TP.CHANGE_ORDER_MODIFICATION.equals(cpoSrcTpCd)) {
            // call NWZC1570 Pricing API
            NWZC157001PMsg prcApiPMsg = NWAL1500CommonLogic.callPricingApiOfGetLineManPriceMode(bizMsg, glblMsg, slctConfIndex, slctLineIndex); // 2018/01/30 S21_NA#19808 Mod
            if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
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

            NWZC157002PMsg prcLinePMsg = prcApiPMsg.NWZC157002PMsg.no(0);
            if (S21ApiUtil.isXxMsgId(prcLinePMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcLinePMsg);
                if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                    ZYPEZDItemValueSetter.setValue(lineMsg.xxErrFlg_LL, prcLinePMsg.xxErrFlg);
                } else {
                    ZYPEZDItemValueSetter.setValue(rmaLineMsg.xxErrFlg_RL, prcLinePMsg.xxErrFlg);
                }
                for (int j = 0; j < msgList.size(); j++) {
                    S21ApiMessage msg = msgList.get(j);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    bizMsg.setMessageInfo(msgId, msgPrms);
                    if (msgId.endsWith("E")) {
                        if (ZYPConstant.FLG_ON_Y.equals(prcLinePMsg.xxErrFlg.getValue())) {
                            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                                lineMsg.entCpoDtlDealSlsAmt_LL.clear();
                            } else {
                                rmaLineMsg.entCpoDtlDealSlsAmt_RL.clear();
                            }
                        }
                        return;
                    }
                }
            }

            delSlctLineCalcBase(bizMsg, glblMsg, slctLineIndex); // 2018/01/30 S21_NA#19808
            int prcElmCnt = glblMsg.I.getValidCount();
            for (int j = 0; j < prcLinePMsg.NWZC157003PMsg.getValidCount(); j++) {
                NWZC157003PMsg prcElementPMsg = prcLinePMsg.NWZC157003PMsg.no(j);
                NWAL1500_ISMsg prcLineCMsg = glblMsg.I.no(prcElmCnt);

                ZYPEZDItemValueSetter.setValue(prcLineCMsg.configCatgCd_LP, prcElementPMsg.configCatgCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.ordPrcCalcBasePk_LP, prcElementPMsg.ordPrcCalcBasePk);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.cpoOrdNum_LP, bizMsg.cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.cpoDtlLineNum_LP, prcElementPMsg.trxLineNum);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.cpoDtlLineSubNum_LP, prcElementPMsg.trxLineSubNum);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondTpCd_LP, prcElementPMsg.prcCondTpCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcDtlGrpCd_LP, prcElementPMsg.prcDtlGrpCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcJrnlGrpCd_LP, prcElementPMsg.prcJrnlGrpCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.pkgUomCd_LP, prcElementPMsg.pkgUomCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondUnitCd_LP, prcElementPMsg.prcCondUnitCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCalcMethCd_LP, prcElementPMsg.prcCalcMethCd);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.dsMdsePrcPk_LP, prcElementPMsg.dsMdsePrcPk);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.specCondPrcPk_LP, prcElementPMsg.specCondPrcPk);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.frtPerWtPk_LP, prcElementPMsg.frtPerWtPk);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondManEntryFlg_LP, prcElementPMsg.prcCondManEntryFlg);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondManAddFlg_LP, prcElementPMsg.prcCondManAddFlg);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.prcCondManDelFlg_LP, prcElementPMsg.prcCondManDelFlg);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.autoPrcAmtRate_LP, prcElementPMsg.autoPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.maxPrcAmtRate_LP, prcElementPMsg.maxPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.minPrcAmtRate_LP, prcElementPMsg.minPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.manPrcAmtRate_LP, prcElementPMsg.manPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.calcPrcAmtRate_LP, prcElementPMsg.calcPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.unitPrcAmt_LP, prcElementPMsg.unitPrcAmt);
                ZYPEZDItemValueSetter.setValue(prcLineCMsg.autoPrcCcyCd_LP, prcElementPMsg.autoPrcCcyCd);

                prcElmCnt++;
            }
            glblMsg.I.setValidCount(prcElmCnt);

            // set Field Amount
            NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(0);
            // Add 2016/03/01 QC#4375 Start
            BigDecimal charges;
            if (ZYPCommonFunc.hasValue(prcTotalPMsg.xxTotSpclChrgAmt.getValue())) {
                charges = prcTotalPMsg.xxTotFrtAmt.getValue().subtract(prcTotalPMsg.xxTotSpclChrgAmt.getValue());
            } else {
                charges = prcTotalPMsg.xxTotFrtAmt.getValue();
            }
            // Add 2016/03/01 QC#4375 End
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                ZYPEZDItemValueSetter.setValue(lineMsg.entDealNetUnitPrcAmt_LL, prcTotalPMsg.xxUnitNetPrcAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.cpoDtlDealTaxAmt_LL,     prcTotalPMsg.xxTotTaxAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.xxLineTotPrcAmt_LL,      prcTotalPMsg.xxTotAmt);

                // Add 2016/03/01 QC#4375 Start
                ZYPEZDItemValueSetter.setValue(lineMsg.lineDealSubTotAmt_LL,    prcTotalPMsg.xxTotNetPrcAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.lineDealChrgAmt_LL,      charges);
                ZYPEZDItemValueSetter.setValue(lineMsg.lineDealTaxAmt_LL,       prcTotalPMsg.xxTotTaxAmt);
                ZYPEZDItemValueSetter.setValue(lineMsg.lineDealTotAmt_LL,       prcTotalPMsg.xxTotAmt);
                // Add 2016/03/01 QC#4375 End
            } else {
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.entDealNetUnitPrcAmt_RL, prcTotalPMsg.xxUnitNetPrcAmt);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.cpoDtlDealTaxAmt_RL,     prcTotalPMsg.xxTotTaxAmt);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.xxLineTotPrcAmt_RL,      prcTotalPMsg.xxTotAmt);

                // Add 2016/03/01 QC#4375 Start
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.lineDealSubTotAmt_RL,    prcTotalPMsg.xxTotNetPrcAmt);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.lineDealChrgAmt_RL,      charges);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.lineDealTaxAmt_RL,       prcTotalPMsg.xxTotTaxAmt);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.lineDealTotAmt_RL,       prcTotalPMsg.xxTotAmt);
                // Add 2016/03/01 QC#4375 End
            }
        }

        if (CPO_SRC_TP.CHANGE_ORDER_MODIFICATION.equals(cpoSrcTpCd) || hasPricingApiError) {
            // set Calc Base
            int validCnt = glblMsg.I.getValidCount();
            NWAL1500_ISMsg iCMsg = glblMsg.I.no(validCnt);
            ZYPEZDItemValueSetter.setValue(iCMsg.xxLineNum_LP, glblMsg.B.no(slctLineIndex).xxLineNum_LL);
            iCMsg.xxCellIdx_LP.setValue(0);
            ZYPEZDItemValueSetter.setValue(iCMsg.cpoDtlLineNum_LP, slctCpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(iCMsg.cpoDtlLineSubNum_LP, slctCpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(iCMsg.configCatgCd_LP, configCatgCd);
            ZYPEZDItemValueSetter.setValue(iCMsg.prcDtlGrpCd_LP, PRC_DTL_GRP.BASE_PRICE);
            ZYPEZDItemValueSetter.setValue(iCMsg.prcJrnlGrpCd_LP, PRC_JRNL_GRP.BASE_PRICE);
            ZYPEZDItemValueSetter.setValue(iCMsg.prcCondManEntryFlg_LP, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(iCMsg.prcCondManAddFlg_LP, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(iCMsg.prcCondManDelFlg_LP, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(iCMsg.pkgUomCd_LP, PKG_UOM.EACH);
            ZYPEZDItemValueSetter.setValue(iCMsg.prcCondUnitCd_LP, PRC_COND_UNIT.AMT);
            ZYPEZDItemValueSetter.setValue(iCMsg.prcCalcMethCd_LP, PRC_CALC_METH.EACH_ACCOUNT);
            ZYPEZDItemValueSetter.setValue(iCMsg.autoPrcCcyCd_LP, CCY.US_DOLLAR);
            ZYPEZDItemValueSetter.setValue(iCMsg.autoPrcAmtRate_LP, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(iCMsg.manPrcAmtRate_LP, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(iCMsg.calcPrcAmtRate_LP, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(iCMsg.unitPrcAmt_LP, BigDecimal.ZERO);
            validCnt++;
            glblMsg.I.setValidCount(validCnt);

            // set Field Amount
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                NWAL1500_BSMsg bCMsg = glblMsg.B.no(slctLineIndex);
                ZYPEZDItemValueSetter.setValue(bCMsg.entCpoDtlDealSlsAmt_LL, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(bCMsg.entDealNetUnitPrcAmt_LL, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(bCMsg.dealPrcListPrcAmt_LL, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(bCMsg.cpoDtlDealTaxAmt_LL, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(bCMsg.xxLineTotPrcAmt_LL, BigDecimal.ZERO);
            } else {
                NWAL1500_DSMsg dCMsg = glblMsg.D.no(slctLineIndex);
                ZYPEZDItemValueSetter.setValue(dCMsg.entCpoDtlDealSlsAmt_RL, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dCMsg.entDealNetUnitPrcAmt_RL, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dCMsg.dealPrcListPrcAmt_RL, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dCMsg.cpoDtlDealTaxAmt_RL, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dCMsg.xxLineTotPrcAmt_RL, BigDecimal.ZERO);
            }

//            ZYPTableUtil.clear(glblMsg.I);
//            EZDMsg.copy(glblMsg.I, null, glblMsg.I, null);
        }

    }

    /**
     * Call NWZC1570 Pricing API (02:Get Base Price Mode)
     * @param bizMsg NWAL1500CMsg
     * @param slctConfIndex Select Config Index
     * @param slctLineIndex Select Line Index
     * @return NWZC157001PMsg
     */
    public static NWZC157001PMsg callPricingApiOfGetBasePriceMode(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex, int slctLineIndex) { // 2018/01/30 S21_NA#19808 Mod

        // 2018/01/30 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();

        // Header
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_LINE_PRICE);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_BASE_PRICE);
        // QC#9437 2016/06/21 Mod Start
        // ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, getPrcBaseDt(bizMsg, slctLineIndex));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.slsDt);
        // QC#9437 2016/06/21 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.custIssPoNum, bizMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N); // 2016/02/29 Y -> N
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.spclHdlgTpCd, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.leasePrchOptCd, bizMsg.leasePrchOptCd);
        // QC#22789 2017/11/28 Mod Start
        // ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcTermAot, bizMsg.leaseTermMthAot); // QC#4375
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDplyCtrlFlg_LD.getValue())) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcTermAot, bizMsg.leaseTermMthAot);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDplyCtrlFlg_EM.getValue())) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcTermAot, bizMsg.leaseTermMthAot_EM);
        }
        // QC#22789 2017/11/28 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);

        // Detail : Order Line
        NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(0);

        // QC#31345 2019/04/26 Add Start
        // Pricing Element
        String xxLineNum;
        String cpoDtlLineNum;
        String cpoDtlLineSubNum;
        String configCatgCd;
        int prcCnt = 0;
        // QC#31345 2019/04/26 Add End
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500_ASMsg confMsg = glblMsg.A.no(slctConfIndex);
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(slctLineIndex);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineNum, lineMsg.cpoDtlLineNum_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineSubNum, lineMsg.cpoDtlLineSubNum_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcBaseDt, lineMsg.prcBaseDt_LL); //QC#9482 2016/06/10
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.billToCustCd, confMsg.billToCustCd_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shipToCustCd, confMsg.shipToCustCd_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_SH, confMsg.shipToCustAcctCd_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_BL, confMsg.billToCustAcctCd_LC);
            // Get Price Category Code
            if (ZYPCommonFunc.hasValue(lineMsg.prcCatgNm_LL)) {
                String prcCatgCd = getPrcCatgCd(bizMsg, lineMsg.prcCatgNm_LL.getValue());
                ZYPEZDItemValueSetter.setValue(lineMsg.prcCatgCd_LL, prcCatgCd);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCatgCd, prcCatgCd);
            }
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.csmpNum, lineMsg.csmpContrNum_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dlrRefNum, lineMsg.dlrRefNum_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcContrNum, bizMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaBrCd, bizMsg.coaBrCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ccyCd, getCcyCd(bizMsg, lineMsg));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcListEquipConfigNum, lineMsg.prcListEquipConfigNum_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdseCd, lineMsg.mdseCd_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.pkgUomCd, lineMsg.custUomCd_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsOrdLineCatgCd, lineMsg.dsOrdLineCatgCd_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordQty, lineMsg.ordQty_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordCustUomQty, lineMsg.ordCustUomQty_LL);
            // S21_NA#1263
            if (!ZYPCommonFunc.hasValue(lineMsg.invQty_LL)) {
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, BigDecimal.ZERO);
            } else {
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, lineMsg.invQty_LL);
            }
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdlId, confMsg.mdlId_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.cntyNm_SH, confMsg.shipToCntyNm_LC); // QC#9192 Add
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctyAddr_SH, confMsg.shipToCtyAddr_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.stCd_SH, confMsg.shipToStCd_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctryCd_SH, confMsg.shipToCtryCd_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.slsRepOrSlsTeamTocCd, NWAL1500CommonLogic.getWriterLineConfigRepCd(bizMsg.G, confMsg.dsOrdPosnNum_LC.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.rtlWhCd, lineMsg.rtlWhCd_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtCondCd, bizMsg.frtCondCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaExtnCd, bizMsg.coaExtnCd);

            // QC#31345 2019/04/26 Add Start
            xxLineNum = lineMsg.xxLineNum_LL.getValue();
            cpoDtlLineNum = lineMsg.cpoDtlLineNum_LL.getValue();
            cpoDtlLineSubNum = lineMsg.cpoDtlLineSubNum_LL.getValue();
            configCatgCd = CONFIG_CATG.OUTBOUND;
            // QC#31345 2019/04/26 Add End

        } else {
            NWAL1500_CSMsg rmaConfMsg = glblMsg.C.no(slctConfIndex);
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(slctLineIndex);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineNum, rmaLineMsg.cpoDtlLineNum_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineSubNum, rmaLineMsg.cpoDtlLineSubNum_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.configCatgCd, CONFIG_CATG.INBOUND);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcBaseDt, rmaLineMsg.prcBaseDt_RL); //QC#9482 2016/06/10
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.billToCustCd, rmaConfMsg.billToCustCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shipToCustCd, rmaConfMsg.shipToCustCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_SH, rmaConfMsg.shipToCustAcctCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_BL, rmaConfMsg.billToCustAcctCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCatgCd, rmaLineMsg.prcCatgCd_RL);
            // Get Price Category Code
            if (ZYPCommonFunc.hasValue(rmaLineMsg.prcCatgNm_RL)) {
                String prcCatgCd = getPrcCatgCd(bizMsg, rmaLineMsg.prcCatgNm_RL.getValue());
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.prcCatgCd_RL, prcCatgCd);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCatgCd, prcCatgCd);
            }
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcContrNum, bizMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaBrCd, bizMsg.coaBrCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ccyCd, getCcyCdForRma(bizMsg, rmaLineMsg));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdseCd, rmaLineMsg.mdseCd_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.pkgUomCd, rmaLineMsg.custUomCd_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsOrdLineCatgCd, rmaLineMsg.dsOrdLineCatgCd_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordQty, rmaLineMsg.ordQty_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordCustUomQty, rmaLineMsg.ordCustUomQty_RL);
            // ZYPEZDItemValueSetter.setValue(this.prcApiPMsg.NWZC157002PMsg.no(i).invQty, rmaLineMsg.ordCustUomQty_RL);
            // S21_NA#1263
            // 2019/08/09 S21_NA#52472 Mod Start
//            if (!ZYPCommonFunc.hasValue(rmaLineMsg.rtrnQty_RL)) {
            if (!NWAL1500Constant.LINE_STS_NM_CLOSED.equals(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue())) {
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, BigDecimal.ZERO);
            } else {
//                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, rmaLineMsg.rtrnQty_RL);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, rmaLineMsg.ordQty_RL);
            }
            // 2019/08/09 S21_NA#52472 Mod Start
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.rtrnRsnCd, rmaLineMsg.rtrnRsnCd_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdlId, rmaConfMsg.mdlId_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.cntyNm_SH, rmaConfMsg.shipToCntyNm_RC); // QC#9192 Add
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctyAddr_SH, rmaConfMsg.shipToCtyAddr_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.stCd_SH, rmaConfMsg.shipToStCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctryCd_SH, rmaConfMsg.shipToCtryCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.slsRepOrSlsTeamTocCd, NWAL1500CommonLogic.getWriterLineConfigRepCd(bizMsg.H, rmaConfMsg.dsOrdPosnNum_RC.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.rtlWhCd, rmaLineMsg.rtlWhCd_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtCondCd, bizMsg.frtCondCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaExtnCd, bizMsg.coaExtnCd);

            // QC#31345 2019/04/26 Add Start
            xxLineNum = rmaLineMsg.xxLineNum_RL.getValue();
            cpoDtlLineNum = rmaLineMsg.cpoDtlLineNum_RL.getValue();
            cpoDtlLineSubNum = rmaLineMsg.cpoDtlLineSubNum_RL.getValue();
            configCatgCd = CONFIG_CATG.INBOUND;
            // QC#31345 2019/04/26 Add End

        }
        prcApiPMsg.NWZC157002PMsg.setValidCount(1);

        // QC#31345 2019/04/26 Add Start
        Map<String, String> prcCondTpMap = new HashMap<String, String>();
        for (int j = 0; j < glblMsg.I.getValidCount(); j++) {
            NWAL1500_ISMsg priceElementMsg = glblMsg.I.no(j);

            String xxLineNumLP = priceElementMsg.xxLineNum_LP.getValue();
            String cpoDtlLineNumLP = priceElementMsg.cpoDtlLineNum_LP.getValue();
            String cpoDtlLineSubNumLP = priceElementMsg.cpoDtlLineSubNum_LP.getValue();
            String configCatgCdLP = priceElementMsg.configCatgCd_LP.getValue();
            if ((xxLineNum.equals(xxLineNumLP) || (cpoDtlLineNum.equals(cpoDtlLineNumLP) && cpoDtlLineSubNum.equals(cpoDtlLineSubNumLP))) && configCatgCd.equals(configCatgCdLP)) {
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).trxLineNum, priceElementMsg.cpoDtlLineNum_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).trxLineSubNum, priceElementMsg.cpoDtlLineSubNum_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).configCatgCd, priceElementMsg.configCatgCd_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcCondTpCd, priceElementMsg.prcCondTpCd_LP);

                String prcCondTpDescTxt = null;
                if (ZYPCommonFunc.hasValue(priceElementMsg.prcCondTpCd_LP)) {
                    prcCondTpDescTxt = prcCondTpMap.get(priceElementMsg.prcCondTpCd_LP.getValue());
                    if (null == prcCondTpDescTxt) {
                        prcCondTpDescTxt = NWAL1500CommonLogic.getPrcCondTpDescTxt(bizMsg.glblCmpyCd.getValue(), priceElementMsg.prcCondTpCd_LP.getValue());
                        if (null != prcCondTpDescTxt) {
                            prcCondTpMap.put(priceElementMsg.prcCondTpCd_LP.getValue(), prcCondTpDescTxt);
                        } else {
                            prcCondTpMap.put(priceElementMsg.prcCondTpCd_LP.getValue(), "");
                        }
                    }
                }

                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcCondTpDescTxt, prcCondTpDescTxt);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcDtlGrpCd, priceElementMsg.prcDtlGrpCd_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcJrnlGrpCd, priceElementMsg.prcJrnlGrpCd_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcCatgCd, priceElementMsg.prcCatgCd_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcCondManEntryFlg, priceElementMsg.prcCondManEntryFlg_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcCondManAddFlg, priceElementMsg.prcCondManAddFlg_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcCondManDelFlg, priceElementMsg.prcCondManDelFlg_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).pkgUomCd, priceElementMsg.pkgUomCd_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcCondUnitCd, priceElementMsg.prcCondUnitCd_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcCalcMethCd, priceElementMsg.prcCalcMethCd_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).autoPrcCcyCd, priceElementMsg.autoPrcCcyCd_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).autoPrcAmtRate, priceElementMsg.autoPrcAmtRate_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).maxPrcAmtRate, priceElementMsg.maxPrcAmtRate_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).minPrcAmtRate, priceElementMsg.minPrcAmtRate_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).manPrcAmtRate, priceElementMsg.manPrcAmtRate_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).calcPrcAmtRate, priceElementMsg.calcPrcAmtRate_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).unitPrcAmt, priceElementMsg.unitPrcAmt_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).dsMdsePrcPk, priceElementMsg.dsMdsePrcPk_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).specCondPrcPk, priceElementMsg.specCondPrcPk_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).frtPerWtPk, priceElementMsg.frtPerWtPk_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).ordPrcCalcBasePk, priceElementMsg.ordPrcCalcBasePk_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcRuleApplyFlg, priceElementMsg.prcRuleApplyFlg_LP);
                ZYPEZDItemValueSetter.setValue(prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(prcCnt).prcRulePrcdPk, priceElementMsg.prcRulePrcdPk_LP);
                prcCnt++;
            }
        }
        prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.setValidCount(prcCnt);        // call NWZC1570 Pricing API
        // QC#31345 2019/04/26 Add End

        // call NWZC1570 Pricing API
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);

        return prcApiPMsg;
    }

    // QC#22372 2018/01/10 Add Start
    /**
     * Call NWZC1570 Pricing API For Floor Price(02:Get Base Price Mode)
     * @param bizMsg NWAL1500CMsg
     * @param slctConfIndex Select Config Index (index of Global Message)
     * @param slctLineIndex Select Line Index (index of Global Message)
     * @return NWZC157001PMsg
     */
    public static NWZC157001PMsg callPricingApiOfGetFloorPriceMode(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex, int slctLineIndex) { // 2018/01/30 S21_NA#19808 Mod

        // 2018/01/30 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();

        // Header
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_BASE_PRICE);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.FLOOR_PRICE);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.custIssPoNum, bizMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.spclHdlgTpCd, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.leasePrchOptCd, bizMsg.leasePrchOptCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);

        NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(0);

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500_ASMsg confMsg = glblMsg.A.no(slctConfIndex);
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(slctLineIndex);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineNum, lineMsg.cpoDtlLineNum_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineSubNum, lineMsg.cpoDtlLineSubNum_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcBaseDt, lineMsg.prcBaseDt_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.billToCustCd, confMsg.billToCustCd_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shipToCustCd, confMsg.shipToCustCd_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_SH, confMsg.shipToCustAcctCd_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_BL, confMsg.billToCustAcctCd_LC);
            // Get Price Category Code
            if (ZYPCommonFunc.hasValue(lineMsg.flPrcListNm_LL)) {
                String prcCatgCd = getPrcCatgCd(bizMsg, lineMsg.flPrcListNm_LL.getValue());
                ZYPEZDItemValueSetter.setValue(lineMsg.prcCatgCd_LL, prcCatgCd);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCatgCd, prcCatgCd);
            }
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.csmpNum, lineMsg.csmpContrNum_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dlrRefNum, lineMsg.dlrRefNum_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcContrNum, bizMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaBrCd, bizMsg.coaBrCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ccyCd, getCcyCd(bizMsg, lineMsg));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcListEquipConfigNum, lineMsg.prcListEquipConfigNum_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdseCd, lineMsg.mdseCd_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.pkgUomCd, lineMsg.custUomCd_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsOrdLineCatgCd, lineMsg.dsOrdLineCatgCd_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordQty, lineMsg.ordQty_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordCustUomQty, lineMsg.ordCustUomQty_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, lineMsg.invQty_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdlId, confMsg.mdlId_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.cntyNm_SH, confMsg.shipToCntyNm_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctyAddr_SH, confMsg.shipToCtyAddr_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.stCd_SH, confMsg.shipToStCd_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctryCd_SH, confMsg.shipToCtryCd_LC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.slsRepOrSlsTeamTocCd, NWAL1500CommonLogic.getWriterLineConfigRepCd(bizMsg.G, confMsg.dsOrdPosnNum_LC.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.rtlWhCd, lineMsg.rtlWhCd_LL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtCondCd, bizMsg.frtCondCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaExtnCd, bizMsg.coaExtnCd);
        } else {
            NWAL1500_CSMsg rmaConfMsg = glblMsg.C.no(slctConfIndex);
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(slctLineIndex);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineNum, rmaLineMsg.cpoDtlLineNum_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineSubNum, rmaLineMsg.cpoDtlLineSubNum_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.configCatgCd, CONFIG_CATG.INBOUND);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcBaseDt, rmaLineMsg.prcBaseDt_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.billToCustCd, rmaConfMsg.billToCustCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shipToCustCd, rmaConfMsg.shipToCustCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_SH, rmaConfMsg.shipToCustAcctCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_BL, rmaConfMsg.billToCustAcctCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCatgCd, rmaLineMsg.prcCatgCd_RL);
            // Get Price Category Code
            if (ZYPCommonFunc.hasValue(rmaLineMsg.flPrcListNm_RL)) {
                String prcCatgCd = getPrcCatgCd(bizMsg, rmaLineMsg.flPrcListNm_RL.getValue());
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.prcCatgCd_RL, prcCatgCd);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCatgCd, prcCatgCd);
            }
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcContrNum, bizMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaBrCd, bizMsg.coaBrCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ccyCd, getCcyCdForRma(bizMsg, rmaLineMsg));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdseCd, rmaLineMsg.mdseCd_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.pkgUomCd, rmaLineMsg.custUomCd_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsOrdLineCatgCd, rmaLineMsg.dsOrdLineCatgCd_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordQty, rmaLineMsg.ordQty_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordCustUomQty, rmaLineMsg.ordCustUomQty_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, rmaLineMsg.rtrnQty_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.rtrnRsnCd, rmaLineMsg.rtrnRsnCd_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdlId, rmaConfMsg.mdlId_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.cntyNm_SH, rmaConfMsg.shipToCntyNm_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctyAddr_SH, rmaConfMsg.shipToCtyAddr_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.stCd_SH, rmaConfMsg.shipToStCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctryCd_SH, rmaConfMsg.shipToCtryCd_RC);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.slsRepOrSlsTeamTocCd, NWAL1500CommonLogic.getWriterLineConfigRepCd(bizMsg.H, rmaConfMsg.dsOrdPosnNum_RC.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.rtlWhCd, rmaLineMsg.rtlWhCd_RL);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtCondCd, bizMsg.frtCondCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shpgSvcLvlCd, bizMsg.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaExtnCd, bizMsg.coaExtnCd);
        }
        prcApiPMsg.NWZC157002PMsg.setValidCount(1);

        // call NWZC1570 Pricing API
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);

        return prcApiPMsg;
    }
    // QC#22372 2018/01/10 Add End

    /**
     * Delete Select Line Calculation Base
     * @param bizMsg NWAL1500CMsg
     * @param slctLineIndex Select Line Index
     */
    public static void delSlctLineCalcBase(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctLineIndex) { // 2018/01/30 S21_NA#19808

        List<Integer> deleteRows = new ArrayList<Integer>();

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            String slctCpoDtlLineNum = glblMsg.B.no(slctLineIndex).cpoDtlLineNum_LL.getValue(); // 2018/01/30 S21_NA#19808

            for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
                String cpoDtlLineNum = glblMsg.I.no(i).cpoDtlLineNum_LP.getValue();
                String configCatgCd = glblMsg.I.no(i).configCatgCd_LP.getValue();

                if (slctCpoDtlLineNum.equals(cpoDtlLineNum) && CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
                    deleteRows.add(i);
                }
            }
        } else {
            String slctCpoDtlLineNum = glblMsg.D.no(slctLineIndex).cpoDtlLineNum_RL.getValue(); // 2018/01/30 S21_NA#19808

            for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
                String cpoDtlLineNum = glblMsg.I.no(i).cpoDtlLineNum_LP.getValue();
                String configCatgCd = glblMsg.I.no(i).configCatgCd_LP.getValue();

                if (slctCpoDtlLineNum.equals(cpoDtlLineNum) && CONFIG_CATG.INBOUND.equals(configCatgCd)) {
                    deleteRows.add(i);
                }
            }
        }

        ZYPTableUtil.deleteRows(glblMsg.I, deleteRows);
    }

    /**
     * Get Price Date
     * @param bizMsg NWAL1500CMsg
     * @param slctLineIndex Select Line Index
     * @return Price Date
     */
    public static String getPrcBaseDt(NWAL1500CMsg bizMsg, int slctLineIndex) {

        String prcBaseDt;
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            prcBaseDt = bizMsg.B.no(slctLineIndex).prcBaseDt_LL.getValue();
        } else {
            prcBaseDt = bizMsg.D.no(slctLineIndex).prcBaseDt_RL.getValue();
        }

        if (ZYPCommonFunc.hasValue(prcBaseDt)) {
            return prcBaseDt;
        }

        return bizMsg.slsDt.getValue();
    }

    /**
     * Get CCY Code For Line
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_BCMsg
     * @return CCY Code
     */
    public static String getCcyCd(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg) { // 2018/01/30 S21_NA#19808 Mod

        if (ZYPCommonFunc.hasValue(lineMsg.prcCatgNm_LL)) {
            PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
            prcCatgTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, lineMsg.prcCatgCd_LL);
            prcCatgTMsg = (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatgTMsg);
            if (null != prcCatgTMsg) {
                return prcCatgTMsg.ccyCd.getValue();
            }
        }

        return null;
    }

    /**
     * Get CCY Code For RMA
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_DCMsg
     * @return CCY Code
     */
    public static String getCcyCdForRma(NWAL1500CMsg bizMsg, NWAL1500_DSMsg lineMsg) { // 2018/01/30 S21_NA#19808 Mod

        if (ZYPCommonFunc.hasValue(lineMsg.prcCatgNm_RL)) {
            PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
            prcCatgTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, lineMsg.prcCatgCd_RL);
            prcCatgTMsg = (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatgTMsg);
            if (null != prcCatgTMsg) {
                return prcCatgTMsg.ccyCd.getValue();
            }
        }

        return null;
    }
    // 2018/01/30 S21_NA#19808 Del Start
//    /**
//     * Derive Model
//     * @param bizMsg NWAL1500CMsg
//     * @param slctConfIndex Select Config Index
//     */
////    public static void deriveMdl(NWAL1500CMsg bizMsg, int slctConfIndex) { 2018/01/11 S21_NA#23329 Mod Interface
//    public static NSZC048001PMsg deriveMdl(NWAL1500CMsg bizMsg, int slctConfIndex) {
//
//        // 2017/11/21 S21_NA#22555 Mod Start
////        if (!TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue()) || !isExistOrdCatg(bizMsg, false)) {
////            return;
////        }
////
////        NWAL1500_ACMsg confMsg = bizMsg.A.no(slctConfIndex);
////
////        // Call Service Model API
////        NSZC048001PMsg svcMdlApiPMsg = callSvcMdlApi(bizMsg, confMsg);
////        if (svcMdlApiPMsg != null) {
////            BigDecimal mdlId = svcMdlApiPMsg.mdlId.getValue();
////            if (ZYPCommonFunc.hasValue(mdlId)) {
////                ZYPEZDItemValueSetter.setValue(confMsg.mdlId_LC, mdlId);
////                // Mod 2016/03/07 S21_NA#5000#78 Start
//////                ZYPEZDItemValueSetter.setValue(confMsg.mdlNm_LC, getMdlNm(bizMsg, mdlId));
//////                ZYPEZDItemValueSetter.setValue(confMsg.mdlDescTxt_LC, getMdlDescTxt(bizMsg, mdlId));
////                setMdlInfo(mdlId, bizMsg, confMsg);
////                // Mod 2016/03/07 S21_NA#5000#78 End
////            }
////        } else {
//////            confMsg.mdlNm_LC.clear();
//////            confMsg.mdlDescTxt_LC.clear();
//////            confMsg.mdlGrpDescTxt_LC.clear();
//////            confMsg.svcSegDescTxt_LC.clear();
//////            confMsg.svcIstlReqFlg_LC.clear();
//////            confMsg.siteSrvyReqFlg_LC.clear();
////            clearModelProperties(confMsg);
////        }
//
//        // 2018/01/11 S21_NA#23329 Add Start
//        NSZC048001PMsg svcMdlApiPMsg = null;
//        // 2018/01/11 S21_NA#23329 Add End
//        if (!isExistOrdCatg(bizMsg, false)) {
//            // 2018/01/11 S21_NA#23329 Mod Start
////            return;
//            return svcMdlApiPMsg;
//            // 2018/01/11 S21_NA#23329 Mod End
//        }
//
//        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
//            NWAL1500_ACMsg confMsg = bizMsg.A.no(slctConfIndex);
//            // 2018/01/11 S21_NA#23329 Mod Start
////            deriveMdl(bizMsg, confMsg);
//            svcMdlApiPMsg = deriveMdl(bizMsg, confMsg);
//            // 2018/01/11 S21_NA#23329 Mod End
//
//        } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
//            NWAL1500_CCMsg confMsg = bizMsg.C.no(slctConfIndex);
//            deriveMdl(bizMsg, confMsg);
//        }
//        // 2017/11/21 S21_NA#22555 Mod End
//        // 2018/01/11 S21_NA#23329 Add Start
//        return svcMdlApiPMsg;
//        // 2018/01/11 S21_NA#23329 Add End
//    }
    // 2018/01/30 S21_NA#19808 Del End

    // 2018/05/20 S21_NA#25604 Del Start
//    // 2017/11/21 S21_NA#22555 Add Start
////    private static void deriveMdl(NWAL1500CMsg bizMsg, NWAL1500_ACMsg confMsg) { 2018/01/11 S21_NA#23329 Mod Interface
//    public static NSZC048001PMsg deriveMdl(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg confMsg) { // 2018/01/31 S21_NA#19808 Mod
//
//        // Call Service Model API
//        NSZC048001PMsg svcMdlApiPMsg = callSvcMdlApi(bizMsg, glblMsg, confMsg);
//
//        if (svcMdlApiPMsg != null) {
//            BigDecimal mdlId = svcMdlApiPMsg.mdlId.getValue();
//            if (ZYPCommonFunc.hasValue(mdlId)) {
//                ZYPEZDItemValueSetter.setValue(confMsg.mdlId_LC, mdlId);
//                // 2018/01/11 S21_NA#23329 Add Start
//                ZYPEZDItemValueSetter.setValue(confMsg.prntMdseCd_LC, svcMdlApiPMsg.prntMdseCd);
//                // 2018/01/11 S21_NA#23329 Add End
//                setMdlInfo(mdlId, bizMsg, glblMsg, confMsg); // 2018/01/31 S21_NA#19808 Mod
//            }
//        } else {
//            clearModelProperties(confMsg);
//        }
//        // 2018/01/11 S21_NA#23329 Add Start
//        return svcMdlApiPMsg;
//        // 2018/01/11 S21_NA#23329 Add End
//    }
//
//    public static void deriveMdl(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_CSMsg confMsg) { // 2018/01/31 S21_NA#19808 Mod
//
//        // Call Service Model API
//        NSZC048001PMsg svcMdlApiPMsg = callSvcMdlApi(bizMsg, glblMsg, confMsg); // 2018/01/31 S21_NA#19808 Mod
//
//        if (svcMdlApiPMsg != null) {
//            BigDecimal mdlId = svcMdlApiPMsg.mdlId.getValue();
//            if (ZYPCommonFunc.hasValue(mdlId)) {
//                ZYPEZDItemValueSetter.setValue(confMsg.mdlId_RC, mdlId);
//                setMdlInfo(mdlId, bizMsg, glblMsg, confMsg); // 2018/01/31 S21_NA#19808 Mod
//            }
//        } else {
//            clearModelProperties(confMsg); // 2018/01/31 S21_NA#19808 Mod
//        }
//    }
//    // 2017/11/21 S21_NA#22555 Add End
//
//    /**
//     * Call NSZC0480 Service Model API
//     * @param bizMsg NWAL1500CMsg
//     * @param confMsg NWAL1500_ACMsg
//     * @return NSZC048001PMsg
//     */
//    @SuppressWarnings("unchecked")
//    private static NSZC048001PMsg callSvcMdlApi(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg confMsg) { // 2018/01/31 S21_NA#19808 Mod
//
//        NSZC048001PMsg svcMdlApiPMsg = new NSZC048001PMsg();
//        ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.slsDt, bizMsg.slsDt);
//        NSZC048001_xxChildMdseCdListPMsgArray childMdseCdList = svcMdlApiPMsg.xxChildMdseCdList;
//        int childMdseCdListSize = svcMdlApiPMsg.xxChildMdseCdList.length(); // 2016/04/11 S21_NA#3236-3 Add Start
//
//        int childMdseCnt = 0;
//        // if
//        // (CONFIG_TP.ADD_TO_CONFIG.equals(confMsg.configTpCd_LC.getValue()))
//        // {
//        // Out bound N Y N
//        //if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), confMsg.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, true, false)) { // S21_NA#955
//        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), confMsg.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, true, true)) { // QC#14330 2016/10/19 Add
//
//            // 2016/01/19 S21_NA#3339 Add Start
//            if (!ZYPCommonFunc.hasValue(confMsg.svcConfigMstrPk_LC)) {
//                // For Performance QC#8166 Mod Start
//                // NWAL1500ItemNameList itemNameList = NWAL1500ItemNameList.getInstance();
//                // itemNameList.setBizMsg(bizMsg);
//                // 2018/03/27 S21_NA#25027 Mod Start
////                NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(bizMsg);
//                NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(glblMsg);
//                // 2018/03/27 S21_NA#25027 Mod End
//                // For Performance QC#8166 Mod End
//
//                confMsg.svcConfigMstrPk_LC.setErrorInfo(1, ZZM9000E, new String[] {itemNameList.getItemName(confMsg.svcConfigMstrPk_LC) });
//                return null;
//            }
//            // 2016/01/19 S21_NA#3339 Add End
//            S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getSvcConfInfo(bizMsg, confMsg);
//            if (ssmResult.isCodeNotFound()) {
//                return null;
//            }
//
//            List<Map<String, String>> svcConfInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
//
//            // 2016/12/27 S21_NA#13768 Add Start
//            boolean isSvcExcg = NWAL1500QueryForLineConfig.getInstance().isExistOrdCatg(bizMsg, ORD_CATG_CTX_TP.SERVICE_EXCHANGE_ORDER_VALUE_SET, false);
//            if (isSvcExcg) {
//
//                List<String> rmaMdseList = new ArrayList<String>();
//                for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
//                    rmaMdseList.add(bizMsg.D.no(i).mdseCd_RL.getValue());
//                }
//
//                for (Map<String, String> svcConfInfo : svcConfInfoList) {
//
//                    String svcMachTpCd = svcConfInfo.get("SVC_MACH_TP_CD");
//                    String mdseCd = svcConfInfo.get("MDSE_CD");
//
//                    if (rmaMdseList.contains(mdseCd)) {
//                        continue;
//                    }
//
//                    if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
//                        ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.prntMdseCd, mdseCd);
//                    }
//                    NSZC048001_xxChildMdseCdListPMsg childMdseCdListPMsg = childMdseCdList.no(childMdseCnt++);
//                    ZYPEZDItemValueSetter.setValue(childMdseCdListPMsg.childMdseCd, mdseCd);
//                }
//            } else {
//            // 2016/12/27 S21_NA#13768 Add End
//
//                boolean firstFlg = true;
//                for (Map<String, String> svcConfInfo : svcConfInfoList) {
//                    // 2016/04/11 S21_NA#3236-3 Add Start
//                    if (childMdseCnt >= childMdseCdListSize) {
//                        break;
//                    }
//                    // 2016/04/11 S21_NA#3236-3 Add End
//
//                    String svcMachTpCd = svcConfInfo.get("SVC_MACH_TP_CD");
//                    String mdseCd = svcConfInfo.get("MDSE_CD");
//
//                    if (firstFlg) {
//                        if (!SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
//                            return null;
//                        }
//                        ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.prntMdseCd, mdseCd);
//                        firstFlg = false;
//                    }
//
//                    NSZC048001_xxChildMdseCdListPMsg childMdseCdListPMsg = childMdseCdList.no(childMdseCnt++);
//                    ZYPEZDItemValueSetter.setValue(childMdseCdListPMsg.childMdseCd, mdseCd);
//                }
//
//            } // 2016/12/27 S21_NA#13768 Add
//
//            String dsOrdPosnNum = confMsg.dsOrdPosnNum_LC.getValue();
////            for (int i = 0; i < bizMsg.B.getValidCount(); i++) { 2016/04/11 S21_NA#3236-3 Mod Condition
//            for (int i = 0; i < glblMsg.B.getValidCount() && childMdseCnt < childMdseCdListSize; i++) { // 2018/01/31 S21_NA#19808 Mod
//                NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
//                String lineDsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
//
//                // 2016/11/29 S21_NA#16214 Add Start
//                if (ORD_ENTRY_ACT.DELETE.equals(bizMsg.ordEntryActCd.getValue())) {
//                    if (ZYPConstant.FLG_ON_Y.equals(lineMsg.xxChkBox_LL.getValue())) {
//                        continue;
//                    }
//                }
//                // 2016/11/29 S21_NA#16214 Add End
//
//                // 2016/04/11 S21_NA#3236-3 Add Start
//                if (NWAL1500CommonLogic.isCancelLine(bizMsg, lineMsg)) {
//                    continue;
//                }
//                // 2016/04/11 S21_NA#3236-3 Add End
//
////                if (dsOrdPosnNum.equals(lineDsOrdPosnNum) || !ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL)) {  2016/03/25 S21_NA#3236-2 Mond Condtion
//                //if (dsOrdPosnNum.equals(lineDsOrdPosnNum)) { // 2018/01/04 S21_NA#21503 MOD
//                if (dsOrdPosnNum.equals(lineDsOrdPosnNum) && (ZYPCommonFunc.hasValue(lineMsg.sbstMdseCd_LL) || ZYPCommonFunc.hasValue(lineMsg.mdseCd_LL) )) { // 2018/01/04 S21_NA#21503 MOD
//                    NSZC048001_xxChildMdseCdListPMsg childMdseCdListPMsg = childMdseCdList.no(childMdseCnt++);
//
//                    // 2016/11/30 S21_NA#16098 Add Start
////                    ZYPEZDItemValueSetter.setValue(childMdseCdListPMsg.childMdseCd, lineMsg.mdseCd_LL);
//                    String lineMdseCd = null;
//                    if (ZYPCommonFunc.hasValue(lineMsg.sbstMdseCd_LL)) {
//                        lineMdseCd = lineMsg.sbstMdseCd_LL.getValue();
//                    } else {
//                        lineMdseCd = lineMsg.mdseCd_LL.getValue();
//                    }
//                    ZYPEZDItemValueSetter.setValue(childMdseCdListPMsg.childMdseCd, lineMdseCd);
//                    // 2016/11/30 S21_NA#16098 Add End
//
//                    // 2016/12/27 S21_NA#13768 Add Start
//                    if (isSvcExcg) {
//                        if (COA_MDSE_TP.MACHINE.equals(lineMsg.coaMdseTpCd_LL.getValue())) {
//                            ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.prntMdseCd, lineMdseCd);
//                        }
//                    }
//                    // 2016/12/27 S21_NA#13768 Add End
//                }
//            }
//        } else {
//            String dsOrdPosnNum = confMsg.dsOrdPosnNum_LC.getValue();
////            for (int i = 0; i < bizMsg.B.getValidCount(); i++) { 2016/04/11 S21_NA#3236-3 Mod Condition
//            for (int i = 0; i < glblMsg.B.getValidCount() && childMdseCnt < childMdseCdListSize; i++) { // 2018/01/31 S21_NA#19808 Mod
//                NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
//                String lineDsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
//
//                // 2016/11/30 S21_NA#16098 Add Start
////                String lineMdseCd = lineMsg.mdseCd_LL.getValue();
//                String lineMdseCd = null;
//                if (ZYPCommonFunc.hasValue(lineMsg.sbstMdseCd_LL)) {
//                    lineMdseCd = lineMsg.sbstMdseCd_LL.getValue();
//                } else {
//                    lineMdseCd = lineMsg.mdseCd_LL.getValue();
//                }
//                // 2016/11/30 S21_NA#16098 Add End
//
//                // 2016/11/29 S21_NA#16214 Add Start
//                if (ORD_ENTRY_ACT.DELETE.equals(bizMsg.ordEntryActCd.getValue())) {
//                    if (ZYPConstant.FLG_ON_Y.equals(lineMsg.xxChkBox_LL.getValue())) {
//                        continue;
//                    }
//                }
//                // 2016/11/29 S21_NA#16214 Add End
//
//                if (!ZYPCommonFunc.hasValue(lineMdseCd)) {
//                    continue;
//                }
//
//                // 2016/04/11 S21_NA#3236-3 Add Start
//                if (NWAL1500CommonLogic.isCancelLine(bizMsg, lineMsg)) {
//                    continue;
//                }
//                // 2016/04/11 S21_NA#3236-3 Add End
//
////                if (dsOrdPosnNum.equals(lineDsOrdPosnNum) && !ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL)) { 2016/03/25 S21_NA#3236-2 Mond Condtion
//                if (dsOrdPosnNum.equals(lineDsOrdPosnNum)) {
//                    if (ZYPConstant.FLG_ON_Y.equals(lineMsg.baseCmptFlg_LL.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.prntMdseCd, lineMdseCd);
//                    } else {
//                        NSZC048001_xxChildMdseCdListPMsg childMdseCdListPMsg = childMdseCdList.no(childMdseCnt++);
//                        ZYPEZDItemValueSetter.setValue(childMdseCdListPMsg.childMdseCd, lineMdseCd);
//                    }
//                }
//            }
//        }
//        childMdseCdList.setValidCount(childMdseCnt);
//
//        // call NSZC0480 Service Model API
//        new NSZC048001().execute(svcMdlApiPMsg, ONBATCH_TYPE.ONLINE);
//
//        if (S21ApiUtil.isXxMsgId(svcMdlApiPMsg)) {
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(svcMdlApiPMsg);
//            if (msgList.size() > 0) {
//                return null;
//            }
//        }
//
//        return svcMdlApiPMsg;
//    }
//
//    // 2017/11/21 S21_NA#22555 Add Start
//    /**
//     * Call NSZC0480 Service Model API
//     * @param bizMsg NWAL1500CMsg
//     * @param confMsg NWAL1500_CCMsg
//     * @return NSZC048001PMsg
//     */
//    @SuppressWarnings("unchecked")
//    private static NSZC048001PMsg callSvcMdlApi(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_CSMsg confMsg) { // 2018/01/31 S21_NA#19808 Mod
//
//        NSZC048001PMsg svcMdlApiPMsg = new NSZC048001PMsg();
//        ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.slsDt, bizMsg.slsDt);
//        NSZC048001_xxChildMdseCdListPMsgArray childMdseCdList = svcMdlApiPMsg.xxChildMdseCdList;
//        int childMdseCdListSize = svcMdlApiPMsg.xxChildMdseCdList.length();
//        int childMdseCnt = 0;
//
//        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), confMsg.configTpCd_RC.getValue(), CONFIG_CATG.INBOUND, false, true, true)) {
//
//            if (!ZYPCommonFunc.hasValue(confMsg.svcConfigMstrPk_RC)) {
//                NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(glblMsg); // 2018/01/30 S21_NA#19808
//
//                confMsg.svcConfigMstrPk_RC.setErrorInfo(1, ZZM9000E, new String[] {itemNameList.getItemName(confMsg.svcConfigMstrPk_RC) });
//                return null;
//            }
//
//            S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getSvcConfInfo(bizMsg, confMsg);
//            if (ssmResult.isCodeNotFound()) {
//                return null;
//            }
//
//            List<Map<String, String>> svcConfInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
//
//            boolean isSvcExcg = NWAL1500QueryForLineConfig.getInstance().isExistOrdCatg(bizMsg, ORD_CATG_CTX_TP.SERVICE_EXCHANGE_ORDER_VALUE_SET, false);
//            if (isSvcExcg) {
//
//                List<String> rmaMdseList = new ArrayList<String>();
//                for (int i = 0; i < glblMsg.D.getValidCount(); i++) { // 2018/01/31 S21_NA#19808 Mod
//                    rmaMdseList.add(glblMsg.D.no(i).mdseCd_RL.getValue()); // 2018/01/31 S21_NA#19808 Mod
//                }
//
//                for (Map<String, String> svcConfInfo : svcConfInfoList) {
//
//                    String svcMachTpCd = svcConfInfo.get("SVC_MACH_TP_CD");
//                    String mdseCd = svcConfInfo.get("MDSE_CD");
//
//                    if (rmaMdseList.contains(mdseCd)) {
//                        continue;
//                    }
//
//                    if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
//                        ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.prntMdseCd, mdseCd);
//                    }
//                    NSZC048001_xxChildMdseCdListPMsg childMdseCdListPMsg = childMdseCdList.no(childMdseCnt++);
//                    ZYPEZDItemValueSetter.setValue(childMdseCdListPMsg.childMdseCd, mdseCd);
//                }
//            } else {
//
//                boolean firstFlg = true;
//                for (Map<String, String> svcConfInfo : svcConfInfoList) {
//                    if (childMdseCnt >= childMdseCdListSize) {
//                        break;
//                    }
//
//                    String svcMachTpCd = svcConfInfo.get("SVC_MACH_TP_CD");
//                    String mdseCd = svcConfInfo.get("MDSE_CD");
//
//                    if (firstFlg) {
//                        if (!SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
//                            return null;
//                        }
//                        ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.prntMdseCd, mdseCd);
//                        firstFlg = false;
//                    }
//
//                    NSZC048001_xxChildMdseCdListPMsg childMdseCdListPMsg = childMdseCdList.no(childMdseCnt++);
//                    ZYPEZDItemValueSetter.setValue(childMdseCdListPMsg.childMdseCd, mdseCd);
//                }
//
//            }
//
//            String dsOrdPosnNum = confMsg.dsOrdPosnNum_RC.getValue();
//            for (int i = 0; i < glblMsg.D.getValidCount() && childMdseCnt < childMdseCdListSize; i++) {  // 2018/01/31 S21_NA#19808 Mod // 2018/01/31 S21_NA#19808 Mod
//                NWAL1500_DSMsg lineMsg = glblMsg.D.no(i); // 2018/01/31 S21_NA#19808 Mod
//                String lineDsOrdPosnNum = lineMsg.dsOrdPosnNum_RL.getValue();
//
//                if (ORD_ENTRY_ACT.DELETE.equals(bizMsg.ordEntryActCd.getValue())) {
//                    if (ZYPConstant.FLG_ON_Y.equals(lineMsg.xxChkBox_RL.getValue())) {
//                        continue;
//                    }
//                }
//
//                if (NWAL1500CommonLogic.isCancelLine(bizMsg, lineMsg)) {
//                    continue;
//                }
//
//                if (dsOrdPosnNum.equals(lineDsOrdPosnNum)) {
//                    NSZC048001_xxChildMdseCdListPMsg childMdseCdListPMsg = childMdseCdList.no(childMdseCnt++);
//
//                    ZYPEZDItemValueSetter.setValue(childMdseCdListPMsg.childMdseCd, lineMsg.mdseCd_RL);
//
//                    if (isSvcExcg) {
//                        if (COA_MDSE_TP.MACHINE.equals(lineMsg.coaMdseTpCd_RL.getValue())) {
//                            ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.prntMdseCd, lineMsg.mdseCd_RL);
//                        }
//                    }
//                }
//            }
//        } else {
//            String dsOrdPosnNum = confMsg.dsOrdPosnNum_RC.getValue();
//            for (int i = 0; i < glblMsg.D.getValidCount() && childMdseCnt < childMdseCdListSize; i++) { // 2018/01/31 S21_NA#19808 Mod
//                NWAL1500_DSMsg lineMsg = glblMsg.D.no(i); // 2018/01/31 S21_NA#19808 Mod
//                String lineDsOrdPosnNum = lineMsg.dsOrdPosnNum_RL.getValue();
//
//                if (NWAL1500CommonLogic.isCancelLine(bizMsg, lineMsg)) {
//                    continue;
//                }
//
//                if (dsOrdPosnNum.equals(lineDsOrdPosnNum)) {
//                    if (ZYPConstant.FLG_ON_Y.equals(lineMsg.baseCmptFlg_RL.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.prntMdseCd, lineMsg.mdseCd_RL);
//                    } else {
//                        NSZC048001_xxChildMdseCdListPMsg childMdseCdListPMsg = childMdseCdList.no(childMdseCnt++);
//                        ZYPEZDItemValueSetter.setValue(childMdseCdListPMsg.childMdseCd, lineMsg.mdseCd_RL);
//                    }
//                }
//            }
//        }
//        childMdseCdList.setValidCount(childMdseCnt);
//
//        // call NSZC0480 Service Model API
//        new NSZC048001().execute(svcMdlApiPMsg, ONBATCH_TYPE.ONLINE);
//
//        if (S21ApiUtil.isXxMsgId(svcMdlApiPMsg)) {
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(svcMdlApiPMsg);
//            if (msgList.size() > 0) {
//                return null;
//            }
//        }
//
//        return svcMdlApiPMsg;
//    }
//    // 2017/11/21 S21_NA#22555 Add End
    // 2018/05/20 S21_NA#25604 Del End

    // Del 2016/03/07 S21_NA#5000#78 Start
//    /**
//     * Get Model Name
//     * @param bizMsg NWAL1500CMsg
//     * @param mdlId Model ID
//     * @return Model Name
//     */
//    private static String getMdlNm(NWAL1500CMsg bizMsg, BigDecimal mdlId) {
//
//        MDL_NMTMsg mdlNmTMsg = new MDL_NMTMsg();
//        ZYPEZDItemValueSetter.setValue(mdlNmTMsg.t_GlblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(mdlNmTMsg.t_MdlId, mdlId);
//        mdlNmTMsg = (MDL_NMTMsg) EZDTBLAccessor.findByKey(mdlNmTMsg);
//
//        if (mdlNmTMsg != null) {
//            return mdlNmTMsg.t_MdlNm.getValue();
//        }
//
//        return null;
//    }
//
//    /**
//     * Get Model Desc Text
//     * @param bizMsg NWAL1500CMsg
//     * @param mdlId Model ID
//     * @return Model Desc Text
//     */
//    private static String getMdlDescTxt(NWAL1500CMsg bizMsg, BigDecimal mdlId) {
//
//        DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
//        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.mdlId, mdlId);
//        dsMdlTMsg = (DS_MDLTMsg) EZDTBLAccessor.findByKey(dsMdlTMsg);
//
//        if (dsMdlTMsg != null) {
//            return dsMdlTMsg.mdlDescTxt.getValue();
//        }
//
//        return null;
//    }
    // Del 2016/03/07 S21_NA#5000#78 End

    // Add 2016/03/07 S21_NA#5000#78 Start
    private static void setMdlInfo(BigDecimal mdlId, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg confMsg) { // 2018/01/31 S21_NA#19808 Mod
        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getMdlInfo(bizMsg.glblCmpyCd.getValue(), confMsg.mdlId_LC.getValue()); // 2018/01/31 S21_NA#19808 Mod
        if (ssmResult.isCodeNormal()) {
            Map< ? , ? > rsltMap = (Map< ? , ? >) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(confMsg.mdlNm_LC, (String) rsltMap.get("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(confMsg.mdlDescTxt_LC, (String) rsltMap.get("MDL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(confMsg.mdlGrpDescTxt_LC, (String) rsltMap.get("MDL_GRP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(confMsg.svcSegDescTxt_LC, (String) rsltMap.get("SVC_SEG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(confMsg.svcIstlReqFlg_LC, (String) rsltMap.get("SVC_ISTL_REQ_FLG"));
            ZYPEZDItemValueSetter.setValue(confMsg.siteSrvyReqFlg_LC, (String) rsltMap.get("SITE_SRVY_REQ_FLG"));
            // 2018/01/11 S21_NA#23329 Add Start
            ZYPEZDItemValueSetter.setValue(confMsg.svcMdlTpCd_LC, (String) rsltMap.get("SVC_MDL_TP_CD"));
            // 2018/01/11 S21_NA#23329 Add End
        } else {
            clearModelProperties(confMsg);
        }
    }
    // Add 2016/03/07 S21_NA#5000#78 End
    // 2017/11/21 S21_NA#22555 Add Start
    private static void setMdlInfo(BigDecimal mdlId, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_CSMsg confMsg) { // 2018/01/31 S21_NA#19808 Mod
        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getMdlInfo(bizMsg.glblCmpyCd.getValue(), confMsg.mdlId_RC.getValue()); // 2018/01/31 S21_NA#19808 Mod
        if (ssmResult.isCodeNormal()) {
            Map< ? , ? > rsltMap = (Map< ? , ? >) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(confMsg.mdlNm_RC, (String) rsltMap.get("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(confMsg.mdlDescTxt_RC, (String) rsltMap.get("MDL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(confMsg.mdlGrpDescTxt_RC, (String) rsltMap.get("MDL_GRP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(confMsg.svcSegDescTxt_RC, (String) rsltMap.get("SVC_SEG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(confMsg.svcIstlReqFlg_RC, (String) rsltMap.get("SVC_ISTL_REQ_FLG"));
            ZYPEZDItemValueSetter.setValue(confMsg.siteSrvyReqFlg_RC, (String) rsltMap.get("SITE_SRVY_REQ_FLG"));
            // 2018/01/11 S21_NA#23329 Add Start
            ZYPEZDItemValueSetter.setValue(confMsg.svcMdlTpCd_RC, (String) rsltMap.get("SVC_MDL_TP_CD"));
            // 2018/01/11 S21_NA#23329 Add End
        } else {
            clearModelProperties(confMsg);
        }
    }
    // 2017/11/21 S21_NA#22555 Add End

    /**
     * Check Exist Warehouse
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_BCMsg
     */
    public static void checkExistWh(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {

        // 2015/11/24 S21_NA#541 update start
//        if (!NWAL1500QueryForLineConfig.getInstance().isExistWh(bizMsg, lineMsg)) {
//            lineMsg.rtlWhNm_LL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_WH });
//        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            if (!NWAL1500QueryForLineConfig.getInstance().isExistWhWithDsOrdTp(bizMsg, lineMsg)) {
                lineMsg.rtlWhNm_LL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_WH });
            }
        } else {
            if (!NWAL1500QueryForLineConfig.getInstance().isExistWhWithOutDsOrdTp(bizMsg, lineMsg)) {
                lineMsg.rtlWhNm_LL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_WH });
            }
        }
        // 2015/11/24 S21_NA#541 update End
    }

    /**
     * Get Warehouse Code
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_BCMsg
     * @return Warehouse Code
     */
    @SuppressWarnings("unchecked")
    public static String getWhCd(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg) { // 2018/01/25 S21_NA#19808 Mod

        S21SsmEZDResult ssmResult = null;
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            ssmResult = NWAL1500QueryForLineConfig.getInstance().getWhInfoWithRsn(bizMsg, lineMsg.rtlWhNm_LL.getValue());
        } else {
            ssmResult = NWAL1500QueryForLineConfig.getInstance().getWhInfo(bizMsg, lineMsg.rtlWhNm_LL.getValue());
        }
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        return (String) ((List<Map< ? ,  ? >>) ssmResult.getResultObject()).get(0).get("RTL_WH_CD");
    }

    /**
     * Get Warehouse Information
     * @param bizMsg NWAL1500CMsg
     * @param rtlWhNm Warehouse Name
     * @return Warehouse Information
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getWhInfo(NWAL1500CMsg bizMsg, EZDSStringItem rtlWhNm) { // 2018/01/31 S21_NA#19808

        S21SsmEZDResult ssmResult = null;
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            ssmResult = NWAL1500QueryForLineConfig.getInstance().getWhInfoWithRsn(bizMsg, rtlWhNm.getValue());
        } else {
            ssmResult = NWAL1500QueryForLineConfig.getInstance().getWhInfo(bizMsg, rtlWhNm.getValue());
        }

        if (ssmResult.isCodeNotFound()) {
            if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
                rtlWhNm.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_WH });
            } else {
                rtlWhNm.setErrorInfo(1, NWAM0300E, new String[] {MSG_PARAM_WH });
            }
            return null;
        }

        List<Map<String, String>> whInfoList = (List<Map<String, String>>) ssmResult.getResultObject();

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);
        if (whInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return whInfoList.get(0);
    }

    /**
     * Get Warehouse Name
     * @param bizMsg NWAL1500CMsg
     * @param rtlWhCd Retail Warehouse Code
     * @return Sub Warehouse Name
     */
    public static String getWhNm(NWAL1500CMsg bizMsg, String rtlWhCd) {

        RTL_WHTMsg rtlWhTmsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTmsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlWhTmsg.rtlWhCd, rtlWhCd);
        rtlWhTmsg = (RTL_WHTMsg) S21CacheTBLAccessor.findByKey(rtlWhTmsg); // 2018/03/08 S21_NA#19808

        if (rtlWhTmsg == null) {
            return null;
        }

        return rtlWhTmsg.rtlWhNm.getValue();
    }

    /**
     * Get Sub Warehouse Name
     * @param bizMsg NWAL1500CMsg
     * @param rtlWhCd Retail Warehouse Code
     * @param rtlSwhCd Retail Sub Warehouse Code
     * @return Sub Warehouse Name
     */
    public static String getSubWhNm(NWAL1500CMsg bizMsg, String rtlWhCd, String rtlSwhCd) {

        RTL_SWHTMsg rtlSwhTmsg = new RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlSwhTmsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlSwhTmsg.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(rtlSwhTmsg.rtlSwhCd, rtlSwhCd);
        rtlSwhTmsg = (RTL_SWHTMsg) S21CacheTBLAccessor.findByKey(rtlSwhTmsg); // 2018/03/08 S21_NA#19808

        if (rtlSwhTmsg == null) {
            return null;
        }

        return rtlSwhTmsg.rtlSwhNm.getValue();
    }

    /**
     * Call NLZC2150 Get Default Sub Warehouse API
     * @param bizMsg NWAL1500CMsg
     * @param mdseCd MDSE Code
     * @param rtlWhCd Retail Warehouse Code
     * @param svcMachMstrPk Service Machine Master PK
     * @return NLZC215001PMsg
     */
    public static NLZC215001PMsg callDefSubWhApi(NWAL1500CMsg bizMsg, String mdseCd, String rtlWhCd, BigDecimal svcMachMstrPk) {

        NLZC215001PMsg defSubWhApiPMsg = new NLZC215001PMsg();
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.xxModeCd, NLZC215001Constant.MODE_RMA);
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.destRtlWhCd, rtlWhCd);
        // Add Start 2017/01/31 QC#17186
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.svcMachMstrPk, svcMachMstrPk);
        // Add End 2017/01/31 QC#17186

        // call NLZC2150 Get Default Sub Warehouse API
        new NLZC215001().execute(defSubWhApiPMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(defSubWhApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(defSubWhApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                return null;
            }
        }

        return defSubWhApiPMsg;
    }

    /**
     * Get Model ID Information
     * @param bizMsg NWAL1500CMsg
     * @param confMsg NWAL1500_ACMsg
     * @return Model ID Information
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getMdlIdInfo(NWAL1500CMsg bizMsg, NWAL1500_ACMsg confMsg) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getMdlIdInfoList(bizMsg, confMsg);

        if (ssmResult.isCodeNotFound()) {
            confMsg.mdlDescTxt_LC.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_MDL });
            return null;
        }

        List<Map<String, Object>> mdlIdInfoList = (List<Map<String, Object>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (mdlIdInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return mdlIdInfoList.get(0);
    }

    /**
     * Get Model Config List
     * @param bizMsg NWAL1500CMsg
     * @param confMsg NWAL1500_ACMsg
     * @return Model Config List
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, String>> getMdlConfList(NWAL1500CMsg bizMsg, NWAL1500_ACMsg confMsg) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getMdlConfList(bizMsg, confMsg);

        if (ssmResult.isCodeNotFound()) {
            confMsg.mdlDescTxt_LC.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_MDL });
            return null;
        }

        return (List<Map<String, String>>) ssmResult.getResultObject();
    }

    /**
     * Check Exist Multiple Parent Merchandise
     * @param mdlConfList List<Map<String, String>>
     * @return Exist Multiple : true
     */
    public static boolean isExistMultiplePrntMdse(List<Map<String, String>> mdlConfList) {

        if (mdlConfList.size() == 1) {
            return false;
        }
        // get Second Line Child Merchandise
        String scdChildMdseCd = mdlConfList.get(1).get("CHILD_MDSE_CD");
        if (ZYPCommonFunc.hasValue(scdChildMdseCd)) {
            return false;
        }

        return true;
    }

    /**
     * Set Sold To Info
     * @param bizMsg NWAL1500CMsg
     */
    public static void setSoldToInfo(NWAL1500CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.soldToCustLocCd)) {
            return;
        }

        BILL_TO_CUSTTMsg billToCustTMsg = getSoldToInfo(bizMsg);
        if (billToCustTMsg == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P0, bizMsg.soldToCustAcctNm);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P1, billToCustTMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P2, billToCustTMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P3, billToCustTMsg.stCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P4, billToCustTMsg.postCd);
    }

    /**
     * Get Sold To Info (Location)
     * @param bizMsg NWAL1500CMsg
     * @return Sold To Info (Location)
     */
    private static BILL_TO_CUSTTMsg getSoldToInfo(NWAL1500CMsg bizMsg) {

        final BILL_TO_CUSTTMsg condition = new BILL_TO_CUSTTMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("billToCustCd01", bizMsg.soldToCustLocCd.getValue());
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        BILL_TO_CUSTTMsgArray tmsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0);
        }

        return null;
    }

    /**
     * Set Bill To Info
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_BCMsg
     */
    @SuppressWarnings("unchecked")
    public static void setBillToInfo(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {

        String dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
        String billToLocCd = null;
        String billToCustAcctCd = null;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1500_ACMsg confMsg = bizMsg.A.no(i);
            if (dsOrdPosnNum.equals(confMsg.dsOrdPosnNum_LC.getValue())) {
                billToLocCd = confMsg.billToCustCd_LC.getValue();
                billToCustAcctCd = confMsg.billToCustAcctCd_LC.getValue();
                break;
            }
        }

        if (!ZYPCommonFunc.hasValue(billToLocCd) || !ZYPCommonFunc.hasValue(billToCustAcctCd)) {
            return;
        }

        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getBillToCustInfo(bizMsg, billToLocCd);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        SELL_TO_CUSTTMsg sellToCustTMsg = getSellToCust(bizMsg, billToCustAcctCd);
        if (sellToCustTMsg == null) {
            return;
        }

        Map<String, String> billToCustInfo = (Map<String, String>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P5, billToCustInfo.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P6, sellToCustTMsg.dsAcctNm);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P7, billToCustInfo.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P8, billToCustInfo.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_P9, billToCustInfo.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_PA, billToCustInfo.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_PB, billToLocCd);
    }

    /**
     * Get SellToCust
     * @param bizMsg NWAL1500CMsg
     * @param billToCustAcctCd String
     * @return SELL_TO_CUSTTMsg
     */
    public static SELL_TO_CUSTTMsg getSellToCust(NWAL1500CMsg bizMsg, String billToCustAcctCd) {

        final SELL_TO_CUSTTMsg condition = new SELL_TO_CUSTTMsg();
        condition.setSQLID("503");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("sellToCustCd01", billToCustAcctCd);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        SELL_TO_CUSTTMsgArray tmsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0);
        }
        return null;
    }

    /**
     * Get Line Index
     * @param lineList NWAL1500_BCMsgArray
     * @param dsOrdPosnNum Position Number
     * @param dsCpoLineNum line Number
     * @param dsCpoLineSubNum Line Sub Number
     * @return Line Index
     */
    public static int getLineIndex(NWAL1500_BCMsgArray lineList, String dsOrdPosnNum, BigDecimal dsCpoLineNum, BigDecimal dsCpoLineSubNum) {

        for (int lineIndex = 0; lineIndex < lineList.getValidCount(); lineIndex++) {
            NWAL1500_BCMsg line = lineList.no(lineIndex);
            if (!S21StringUtil.isEquals(dsOrdPosnNum, line.dsOrdPosnNum_LL.getValue())) {
                continue;
            }
            if (NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, line.dsCpoLineNum_LL.getValue()) != 0) {
                continue;
            }
            if (NWAL1500CommonLogic.compareBigDecimal(dsCpoLineSubNum, line.dsCpoLineSubNum_LL.getValue()) != 0) {
                continue;
            }
            return lineIndex;
        }
        return -1;
    }

    /**
     * Get Line Index
     * @param lineList NWAL1500_DCMsgArray
     * @param dsOrdPosnNum Position Number
     * @param dsCpoLineNum line Number
     * @param dsCpoLineSubNum Line Sub Number
     * @return Line Index
     */
    public static int getLineIndex(NWAL1500_DCMsgArray lineList, String dsOrdPosnNum, BigDecimal dsCpoLineNum, BigDecimal dsCpoLineSubNum) {

        for (int lineIndex = 0; lineIndex < lineList.getValidCount(); lineIndex++) {
            NWAL1500_DCMsg line = lineList.no(lineIndex);
            if (!S21StringUtil.isEquals(dsOrdPosnNum, line.dsOrdPosnNum_RL.getValue())) {
                continue;
            }
            if (NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, line.dsCpoLineNum_RL.getValue()) != 0) {
                continue;
            }
            if (NWAL1500CommonLogic.compareBigDecimal(dsCpoLineSubNum, line.dsCpoLineSubNum_RL.getValue()) != 0) {
                continue;
            }
            return lineIndex;
        }
        return -1;
    }

    /**
     * Get Price Category Code.
     * @param bizMsg NWAL1500CMsg
     * @param prcCatgNm String
     * @return String
     */
    @SuppressWarnings("unchecked")
    public static String getPrcCatgCd(NWAL1500CMsg bizMsg, String prcCatgNm) {
        String prcCatgCd = null;
        S21SsmEZDResult ssmRslt = NWAL1500QueryForSaveSubmit.getInstance().checkPriceCatgNm(bizMsg, prcCatgNm);
        if (ssmRslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmRslt.getResultObject();
            prcCatgCd = (String) rsltList.get(0).get("PRC_CATG_CD");
        }
        return prcCatgCd;
    }

    /**
     * Check Qty For Serialized Item
     * @param bizMsg NWAL1500CMsg
     * @param mdseCd MDSE Code
     * @param ordQty Order Qty
     * @return No Error : true (not serialized item)
     */
    public static boolean checkQtyForSerializedItem(NWAL1500CMsg bizMsg, String mdseCd, int ordQty) {
        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), mdseCd);
        return checkQtyForSerializedItem(bizMsg, mdseTMsg, ordQty);
    }

    /**
     * Check Qty For Serialized Item
     * @param bizMsg NWAL1500CMsg
     * @param mdseTMsg Merchandise TMessage
     * @param ordQty Order Qty
     * @return No Error : true (not serialized item)
     */
    public static boolean checkQtyForSerializedItem(NWAL1500CMsg bizMsg, MDSETMsg mdseTMsg, int ordQty) {

        // 2016/04/08 S21_NA#5356 Add Start
        BigDecimal absOrdQty = new BigDecimal(ordQty);
        absOrdQty = absOrdQty.abs();
        // 2016/04/08 S21_NA#5356 Add Start

//        if (ordQty == 1 || !isExistOrdCatg(bizMsg, true)) {
        if (absOrdQty.compareTo(BigDecimal.ONE) == 0 || !isExistOrdCatg(bizMsg, true)) {
            return true;
        }

        // 2018/03/08 S21_NA#19808 Del Start
        // 2016/04/08 S21_NA#1703 Mod Start
//        DS_MDSE_INFOTMsg tMsg = new DS_MDSE_INFOTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
//        tMsg = (DS_MDSE_INFOTMsg) EZDTBLAccessor.findByKey(tMsg);
//
//        if (tMsg == null || ZYPConstant.FLG_OFF_N.equals(tMsg.instlBaseCtrlFlg.getValue())) {
//            return true;
//        }

        // S21_NA#14510 Mod Start
//        // MDSETMsg tMsg = new MDSETMsg();
//        // ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        // ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
//        // tMsg = (MDSETMsg) EZDTBLAccessor.findByKey(tMsg);
//        MDSETMsg tMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), mdseCd);
//        // S21_NA#14510 Mod End
//
//        if (tMsg == null || ZYPConstant.FLG_OFF_N.equals(tMsg.shpgSerTakeFlg.getValue())) {
//            return true;
//        }
//        // 2016/04/08 S21_NA#1703 Mod End
        // 2018/03/08 S21_NA#19808 Del End

        // 2018/03/08 S21_NA#19808 Add Start
        if (mdseTMsg == null || ZYPConstant.FLG_OFF_N.equals(mdseTMsg.shpgSerTakeFlg.getValue())) {
            return true;
        }
        // 2018/03/08 S21_NA#19808 Add End

        return false;
    }

    /**
     * Call Default WH API
     * @param bizMsg NWAL1500CMsg
     * @param slctConfIndex int
     * @param slctLineIndex int
     * @param mdseCd String
     * @return No Error : true
     */
    public static boolean callDefWhApi(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex, int slctLineIndex, String mdseCd) { // 2018/01/30 S21_NA#19808 Mod 

        // 2018/01/30 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1500_ASMsg confMsg = glblMsg.A.no(slctConfIndex);
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(slctLineIndex);

            if (ZYPCommonFunc.hasValue(lineMsg.rtlWhNm_LL)) {
                return true;
            }

            if (ZYPCommonFunc.hasValue(confMsg.shipToCustCd_LC) && NWAL1500CommonLogic.needsWh(bizMsg.glblCmpyCd.getValue(), mdseCd)) {
                // 2018/09/20 S21_NA#28199 Del Start
//                NWZC180001PMsg pMsg = new NWZC180001PMsg();
//                if (!NWAL1500CommonLogic.callDefWhApiForLineConf(pMsg, bizMsg, lineMsg.dsOrdPosnNum_LL.getValue(), mdseCd, lineMsg.ordQty_LL.getValue())) {
//                    return false;
//                }
//
//                String rtlWhCd = pMsg.rtlWhCd.getValue();
//                String rtlSwhCd = pMsg.rtlSwhCd.getValue();
//
//                ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhCd_LL, rtlWhCd);
//                ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhNm_LL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
//                ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhCd_LL, rtlSwhCd);
//                ZYPEZDItemValueSetter.setValue(lineMsg.rtlSwhNm_LL, NWAL1500CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
//                ZYPEZDItemValueSetter.setValue(lineMsg.ordLineSrcCd_LL, pMsg.ordLineSrcCd);
                // 2018/09/20 S21_NA#28199 Del End
                // 2018/09/20 S21_NA#28199 Add Start
                List<NWZC180001PMsg> pMsgList = new ArrayList<NWZC180001PMsg>(0);
                if (!NWAL1500CommonLogic.callDefWhApiForLineConf(pMsgList, bizMsg, glblMsg, lineMsg.dsOrdPosnNum_LL.getValue(), lineMsg)) {
                    return false;
                }
                NWAL1500CommonLogic.setDefWh(bizMsg, glblMsg, pMsgList);
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineMsg.baseCmptFlg_LL.getValue())) {
                    NWAL1500CommonLogic.setRtlWhCdAsBaseComponent(bizMsg, glblMsg, lineMsg.dsOrdPosnNum_LL.getValue(), CONFIG_CATG.OUTBOUND);
                }
                // 2018/09/20 S21_NA#28199 Add End
            }

        } else {
            NWAL1500_CSMsg rmaConfMsg = glblMsg.C.no(slctConfIndex);
            NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(slctLineIndex);

            // 2017/09/01 QC#19749 Del Start
//            if (ZYPCommonFunc.hasValue(rmaLineMsg.rtlWhNm_RL)) {
//                return true;
//            }
            // 2017/09/01 QC#19749 Del End

            if (ZYPCommonFunc.hasValue(rmaConfMsg.shipToCustCd_RC) && NWAL1500CommonLogic.needsWh(bizMsg.glblCmpyCd.getValue(), rmaLineMsg.mdseCd_RL.getValue())) {
                NWZC180001PMsg pMsg = new NWZC180001PMsg();
                if (!NWAL1500CommonLogic.callDefWhApiForRma(pMsg, bizMsg, glblMsg, rmaLineMsg)) { // 2018/09/20 S21_NA#28199 Add glblMsg
                    return false;
                }

                String rtlWhCd = pMsg.rtlWhCd.getValue();
                String rtlSwhCd = pMsg.rtlSwhCd.getValue();

                ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlWhCd_RL, rtlWhCd);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlWhNm_RL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlSwhCd_RL, rtlSwhCd);
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlSwhNm_RL, NWAL1500CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
                ZYPEZDItemValueSetter.setValue(rmaLineMsg.thirdPtyDspTpCd_RL, pMsg.thirdPtyDspTpCd); // 2016/11/04 S21_NA#15703 Add
            }
        }

        return true;
    }

    /**
     * Call Supersede API
     * @param bizMsg NWAL1500CMsg
     * @param mdseCd MDSE Code
     * @return No Error : true
     */
    public static boolean callSupersedeApi(NWAL1500CMsg bizMsg, NWAL1500_BSMsg glblLineMsg, String mdseCd) { // 2018/01/30 S21_NA#19808 Mod

        bizMsg.mdseCd_SS.clear();
        bizMsg.mdseNm_SS.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        // 2018/01/30 S21_NA#19808 Mod Start
//        NWAL1500_BCMsg lineMsg = bizMsg.B.no(bizMsg.xxCellIdx.getValueInt());
        String rtlWhCd = glblLineMsg.rtlWhCd_LL.getValue();
        String rtlSwhCd = glblLineMsg.rtlSwhCd_LL.getValue();
        // 2018/01/30 S21_NA#19808 Mod End

        if (ZYPConstant.FLG_ON_Y.equals(glblLineMsg.supdLockFlg_LL.getValue()) || ZYPCommonFunc.hasValue(glblLineMsg.sbstMdseCd_LL) || ZYPCommonFunc.hasValue(glblLineMsg.ordLineStsDescTxt_LL) || !ZYPCommonFunc.hasValue(rtlWhCd)) { // 2018/01/30 S21_NA#19808 Mod
            return true;
        }

        List<String> dropShipRtlWhCd = NWAL1500CommonLogic.getVarCharConstDataList(bizMsg.glblCmpyCd.getValue(), DROP_SHIP_RTL_WH_CD);
        boolean isDropShipRtlWh = dropShipRtlWhCd.contains(rtlWhCd);

        String invtyLocCd = rtlWhCd;
        if (!isDropShipRtlWh) {
            if (ZYPCommonFunc.hasValue(rtlSwhCd)) {
                invtyLocCd = invtyLocCd + rtlSwhCd;
            } else {
                invtyLocCd = invtyLocCd + SUB_WH_CD_NEW;
            }
        }

        // 2018/02/08 S21_NA#20173 Del Start
//        NWZC206001PMsg supdApiPMsg = callSupersedeApi(bizMsg, mdseCd, isDropShipRtlWh, invtyLocCd);
//        if (supdApiPMsg == null) {
//            return false;
//        }
//
//        if (supdApiPMsg.A.getValidCount() == 0) {
//            return true;
//        }
//
//        String supdMdseCd = null;
//        String supdMdseNm = null;
//
//        if (isDropShipRtlWh) {
//            supdMdseCd = supdApiPMsg.A.no(0).mdseCd.getValue();
//            supdMdseNm = supdApiPMsg.A.no(0).mdseNm.getValue();
//        } else {
//            for (int i = 0; i < supdApiPMsg.A.getValidCount(); i++) {
//                NWZC206001_APMsg aPMsg = supdApiPMsg.A.no(i);
//                if (lineMsg.ordQty_LL.getValue().compareTo(aPMsg.invtyAvalQty.getValue()) <= 0) {
//                    if (checkPossessionSameUomCd(bizMsg, lineMsg, aPMsg.mdseCd.getValue())) {
//                        supdMdseCd = aPMsg.mdseCd.getValue();
//                        supdMdseNm = aPMsg.mdseNm.getValue();
//                        break;
//                    }
//                }
//            }
//        }
//
//        if (ZYPCommonFunc.hasValue(supdMdseCd) && !mdseCd.equals(supdMdseCd)) {
//            String ordTakeMdseCd = NWAL1500CommonLogicForSaveSubmit.getOrdTakeMdseCd(bizMsg.glblCmpyCd.getValue(), supdMdseCd);
//            ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_SS, ordTakeMdseCd);
//            ZYPEZDItemValueSetter.setValue(bizMsg.mdseNm_SS, supdMdseNm);
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
//            return false;
//        }
        // 2018/02/08 S21_NA#20173 Del End
        // 2018/02/08 S21_NA#20173 Add Start
        callSupersedeApi(bizMsg, glblLineMsg, mdseCd, isDropShipRtlWh, invtyLocCd); // 2018/01/30 S21_NA#19808 Mod
        // 2018/02/08 S21_NA#20173 Add End
        return true;
    }

    // 2018/02/08 S21_NA#20173 Del Start
//    /**
//     * Call Supersede API
//     * @param bizMsg NWAL1500CMsg
//     * @param mdseCd MDSE Code
//     * @param isDropShipRtlWh is Drop Ship WH
//     * @param invtyLocCd Inventory Location Code
//     * @return NWZC206001PMsg
//     */
//    private static NWZC206001PMsg callSupersedeApi(NWAL1500CMsg bizMsg, String mdseCd, boolean isDropShipRtlWh, String invtyLocCd) {
//
//        String modeCd = NWZC206001.SUPD_LIST_MODE;
//        if (isDropShipRtlWh) {
//            modeCd = NWZC206001.SUPD_LATEST_MODE;
//        }
//
//        NWZC206001PMsg supdApiPMsg = new NWZC206001PMsg();
//        ZYPEZDItemValueSetter.setValue(supdApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(supdApiPMsg.mdseCd, mdseCd);
//        ZYPEZDItemValueSetter.setValue(supdApiPMsg.slsDt, bizMsg.slsDt);
//        ZYPEZDItemValueSetter.setValue(supdApiPMsg.xxModeCd, modeCd);
//        ZYPEZDItemValueSetter.setValue(supdApiPMsg.xxAvalOrdFlg, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(supdApiPMsg.xxAvalPrchFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(supdApiPMsg.whCd, invtyLocCd);
//        ZYPEZDItemValueSetter.setValue(supdApiPMsg.stkStsCd, STK_STS.GOOD);
//        new NWZC206001().execute(supdApiPMsg, ONBATCH_TYPE.ONLINE);
//
//        if (S21ApiUtil.isXxMsgId(supdApiPMsg)) {
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(supdApiPMsg);
//            for (int i = 0; i < msgList.size(); i++) {
//                S21ApiMessage msg = msgList.get(i);
//                String msgId = msg.getXxMsgid();
//                String[] msgPrms = msg.getXxMsgPrmArray();
//                bizMsg.setMessageInfo(msgId, msgPrms);
//
//                if (msgId.endsWith("E")) {
//                    return null;
//                }
//            }
//        }
//
//        return supdApiPMsg;
//    }
//
//    /**
//     * Check Possession SameUOM Code Of Supersede MDSE Code
//     * @param bizMsg NWAL1500CMsg
//     * @param lineMsg NWAL1500_BCMsg
//     * @param supdMdseCd Supersede MDSE Code
//     * @return exist : true
//     */
//    @SuppressWarnings("unchecked")
//    private static boolean checkPossessionSameUomCd(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg, String supdMdseCd) {
//
//        String slctPkgUomCd = lineMsg.custUomCd_LL.getValue();
//        S21SsmEZDResult ssmRslt = NWAL1500Query.getInstance().getPkgUomPullDownList(bizMsg.glblCmpyCd.getValue(), supdMdseCd);
//
//        if (ssmRslt.isCodeNormal()) {
//            List<Map<String, String>> pkgUomList = (List<Map<String, String>>) ssmRslt.getResultObject();
//            for (Map<String, String> pkgUomInfo : pkgUomList) {
//                String pkgUomCd = pkgUomInfo.get("PKG_UOM_CD");
//                if (ZYPCommonFunc.hasValue(pkgUomCd) && pkgUomCd.equals(slctPkgUomCd)) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
    // 2018/02/08 S21_NA#20173 Del End

    // 2018/02/08 S21_NA#20173 Add Start
    /**
     * Call Supersede API
     * @param bizMsg  NWAL1500CMsg
     * @param lineMsg  NWAL1500_BCMsg
     * @param origMdseCd String
     * @param isDropShipRtlWh boolean
     * @param invtyLocCd String
     */
    private static void callSupersedeApi(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg, String origMdseCd, boolean isDropShipRtlWh, String invtyLocCd ) { // 2018/01/30 S21_NA#19808 Mod

        NWZC192001PMsg superSedeApiPMsg = new NWZC192001PMsg();
        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.origMdseCd, origMdseCd);
        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.ordQty, lineMsg.ordQty_LL);
        ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.rtlWhCd, invtyLocCd);

        SHIP_TO_CUSTTMsg condition = new SHIP_TO_CUSTTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("shipToCustCd01", bizMsg.shipToCustCd.getValue());
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        SHIP_TO_CUSTTMsgArray tmsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            SHIP_TO_CUSTTMsg tMsg = tmsgArray.no(0);
            ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.shipToPostCd, tMsg.postCd);
            ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.shipToLocNum, tMsg.locNum);
        }

        if (isDropShipRtlWh) {
            ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.dropShipFlg, ZYPConstant.FLG_ON_Y);  // SUPD_LATEST_MODE
        } else {
            ZYPEZDItemValueSetter.setValue(superSedeApiPMsg.dropShipFlg, ZYPConstant.FLG_OFF_N); // SUPD_LIST_MODE
        }

        new NWZC192001().execute(superSedeApiPMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(superSedeApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(superSedeApiPMsg);
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

        String superSedeMdseCd = superSedeApiPMsg.supdToMdseCd.getValue();
        if (!ZYPCommonFunc.hasValue(superSedeMdseCd)) {
            return;
        } else if (!origMdseCd.equals(superSedeMdseCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_SS, superSedeMdseCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.mdseNm_SS, superSedeApiPMsg.mdseDescShortTxt_SP);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
        }
    }
    // 2018/02/08 S21_NA#20173 Add End

    /**
     * Get Substitute MDSE Code
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_BCMsg
     * @return Substitute MDSE Code
     */
    public static String getSbstMdseCd(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {
//
//        // 2016/07/02 S21_NA#1698,3235 Mod Start
//        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getSbstMdseCdList(bizMsg, lineMsg);
//
//        if (ssmResult.isCodeNotFound()) {
//            lineMsg.sbstMdseCd_LL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_SBST_ITEM });
//            return null;
//        }
//
//        List<String> sbstMdseCdList = (List<String>) ssmResult.getResultObject();

        List<String> sbstMdseCdList = (List<String>) NWXC150001DsCheck.getSbstRelation(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue(), lineMsg.sbstMdseCd_LL.getValue());

        // 2016/07/02 S21_NA#1698,3235 Mod End

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        // 2016/07/28 S21_NA#1698,3235 Mode Start
//        if (sbstMdseCdList.size() != 1) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
//            return null;
//        }
        if (sbstMdseCdList.size() == 0) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }
        // 2016/07/28 S21_NA#1698,3235 Mode End

        return sbstMdseCdList.get(0);
    }

    /**
     * Get Sub Warehouse Information
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_BCMsg
     * @return Sub Warehouse Information
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getRtlSubWhInfo(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getRtlSubWhInfoList(bizMsg, lineMsg);

        if (ssmResult.isCodeNotFound()) {
            lineMsg.rtlSwhNm_LL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_SUB_WH });
            return null;
        }

        List<Map<String, String>> rtlSubWhInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (rtlSubWhInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return rtlSubWhInfoList.get(0);
    }

    // 2017/11/17 S21_NA#22252 Add Start
    /**
     * Get Sub Warehouse Information
     * @param bizMsg NWAL1500CMsg
     * @param lineMsg NWAL1500_DCMsg
     * @return Sub Warehouse Information
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getRtlSubWhInfo(NWAL1500CMsg bizMsg, NWAL1500_DCMsg rmaLineMsg) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getRtlSubWhInfoList(bizMsg, rmaLineMsg);

        if (ssmResult.isCodeNotFound()) {
            rmaLineMsg.rtlSwhNm_RL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_SUB_WH });
            return null;
        }

        List<Map<String, String>> rtlSubWhInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (rtlSubWhInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return rtlSubWhInfoList.get(0);
    }
    // 2017/11/17 S21_NA#22252 Add End

    // 2016/10/05 S21_NA#7645-3 Del Start
//    /**
//     * <pre>
//     * Calculation Back Order Qty for one Line.
//     * 2016/03/15 S21_NA#4691 add.
//     * @param bizMsg Business Message
//     * @param lineMsg Line Config Line Message
//     * @return true: have back order qty false: have not back order qty
//     * </pre>
//     */
//    public static boolean calcBoQty(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) { // 2016/07/27 S21_NA#4691-2 Mod return value
//
//        // 2016/09/29 S21_NA#7645-2 Add Start
//        if (!S21StringUtil.isEquals(STS_NM_BACK_ORDER, lineMsg.ordLineStsDescTxt_LL.getValue())) {
//            lineMsg.boQty_LL.setValue(BigDecimal.ZERO);
//            return false; // 2016/07/27 S21_NA#4691-2 Mod Return Value
//        }
//        // 2016/09/29 S21_NA#7645-2 Add End
//        // 2016/07/04 S21_NA#7645 Add Start
//        if (!ZYPCommonFunc.hasValue(lineMsg.ordLineStsDescTxt_LL)) {
//            lineMsg.boQty_LL.setValue(BigDecimal.ZERO);
//            return false; // 2016/07/27 S21_NA#4691-2 Mod Return Value
//        }
//        // 2016/07/04 S21_NA#7645 Add End
//        if (!ZYPCommonFunc.hasValue(lineMsg.mdseCd_LL) || !ZYPCommonFunc.hasValue(lineMsg.ordQty_LL)) {
//            lineMsg.boQty_LL.setValue(BigDecimal.ZERO); // 2016/07/04 S21_NA#7645 Add
//            return false; // 2016/07/27 S21_NA#4691-2 Mod Return Value
//        }
//        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue());
//        if (mdseTMsg != null && ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue())) {
//                // || ZYPConstant.FLG_ON_Y.equals(mdseTMsg.thirdPtyVndDropFlg.getValue())) {
//            lineMsg.boQty_LL.setValue(BigDecimal.ZERO); // 2016/07/04 S21_NA#7645 Add
//            return false; // 2016/07/27 S21_NA#4691-2 Mod Return Value
//        }
//
//        boolean isHaveBoQty = false; // 2016/07/27 S21_NA#4691-2 Add
//
//        // 2016/07/04 S21_NA#7645 Add Start
//        // Get Validated Qty
//        BigDecimal validQty = NWAL1500QueryForLineConfig.getInstance().getValidatedQty(bizMsg, lineMsg);
//        if (validQty == null) {
//            validQty = BigDecimal.ZERO;
//        }
//        // 2016/07/27 S21_NA#4691-2 Mod Start
////        SHPG_PLNTMsg queryShpgPlnMsg = new SHPG_PLNTMsg();
////        queryShpgPlnMsg.setSQLID("006");
////        queryShpgPlnMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
////        queryShpgPlnMsg.setConditionValue("trxHdrNum01", bizMsg.cpoOrdNum.getValue());
////        queryShpgPlnMsg.setConditionValue("trxLineNum01", lineMsg.cpoDtlLineNum_LL.getValue());
////        queryShpgPlnMsg.setConditionValue("trxLineSubNum01", lineMsg.cpoDtlLineSubNum_LL.getValue());
////        queryShpgPlnMsg.setConditionValue("shpgStsCd01", SHPG_STS.VALIDATED);
////        SHPG_PLNTMsgArray validatedShpgPlnMsgArray = (SHPG_PLNTMsgArray) EZDTBLAccessor.findByCondition(queryShpgPlnMsg);
////
////        BigDecimal lineBoQty = BigDecimal.ZERO;
////        if (validatedShpgPlnMsgArray != null && validatedShpgPlnMsgArray.getValidCount() > 0) {
////            for (int i = 0; i < validatedShpgPlnMsgArray.getValidCount(); i++) {
////                lineBoQty = lineBoQty.add(validatedShpgPlnMsgArray.no(i).ordQty.getValue());
////                isHaveBoQty = true; // 2016/07/27 S21_NA#4691-2 Add
////            }
////        }
//        BigDecimal lineBoQty = lineMsg.boQty_LL.getValue();
//        if (lineBoQty.compareTo(BigDecimal.ZERO) > 0) {
//            isHaveBoQty = true;
//        }
//        // 2016/07/27 S21_NA#4691-2 Mod End
//        // 2016/07/27 S21_NA#4691-2 Add Start
//        if (BigDecimal.ZERO.compareTo(lineBoQty) == 0) {
//            ZYPEZDItemValueSetter.setValue(lineMsg.boQty_LL, lineBoQty);
//            return false;
//        }
//        // 2016/07/27 S21_NA#4691-2 Add End
//        // 2016/07/04 S21_NA#7645 Add End
////        if (!(DIST_TP.NONE.equals(mdseTMsg.invtyDistTpCd.getValue()) && HARD_ALLOC_TP.AUTO.equals(mdseTMsg.invtyHardAllocTpCd.getValue()))) {
////            return;
////        }
//
//        // BigDecimal boQty = BigDecimal.ZERO; 2016/07/04 S21_NA#7645 Del
//        BigDecimal avlQty = BigDecimal.ZERO;
//
//        // search invty .
//        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getInvtyAvalQty(bizMsg, lineMsg);
//        if (ssmResult.isCodeNotFound()) {
//            // ZYPEZDItemValueSetter.setValue(lineMsg.boQty_LL, lineMsg.ordQty_LL); 2016/07/04 S21_NA#7645 Del
//            ZYPEZDItemValueSetter.setValue(lineMsg.boQty_LL, lineBoQty); // 2016/07/04 S21_NA#7645 Add
//            return isHaveBoQty;  // 2016/07/27 S21_NA#4691-2 Mod Return Value
//
//        } else {
//
//            Map<String, Object> invtyAvalQtyMap = (Map<String, Object>) ssmResult.getResultObject();
//
//            avlQty = (BigDecimal) invtyAvalQtyMap.get("INVTY_AVAL_QTY");
//
//            if (BigDecimal.ZERO.compareTo(avlQty) == 0) {
//                // ZYPEZDItemValueSetter.setValue(lineMsg.boQty_LL, lineMsg.ordQty_LL); 2016/07/04 S21_NA#7645 Del
//                ZYPEZDItemValueSetter.setValue(lineMsg.boQty_LL, lineBoQty); // 2016/07/04 S21_NA#7645 Add
//                return isHaveBoQty;  // 2016/07/27 S21_NA#4691-2 Mod Return Value
//            }
//
//            // 2016/07/04 S21_NA#7645 Add Start
//            if (avlQty.compareTo(validQty.add(lineBoQty)) < 0) { // 2016/08/02 S21_NA#4691-3 Add Start
//                ZYPEZDItemValueSetter.setValue(lineMsg.boQty_LL, lineBoQty);
//            } else {
//                ZYPEZDItemValueSetter.setValue(lineMsg.boQty_LL, BigDecimal.ZERO);
//            }
//            return isHaveBoQty;  // 2016/07/27 S21_NA#4691-2 Mod Return Value
//            // 2016/07/04 S21_NA#7645 Add Start
//            // 2016/07/04 S21_NA#7645 Del Start
////            // --------------------------------------------------
////            // [NWZC101001] : B/O Inquiry API
////            // --------------------------------------------------
////            final NWZC101001PMsg pMsg = new NWZC101001PMsg();
////            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
////
////            String invtyLocCd = lineMsg.rtlWhCd_LL.getValue();
////            if (ZYPCommonFunc.hasValue(lineMsg.rtlSwhCd_LL.getValue())) {
////                invtyLocCd = invtyLocCd + lineMsg.rtlSwhCd_LL.getValue();
////            }
////            ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, invtyLocCd);
////            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, mdseTMsg.mdseCd.getValue());
////            ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, STK_STS.GOOD);
////
////            new NWZC101001().execute(pMsg, ONBATCH_TYPE.ONLINE);
////
////            if (pMsg.xxMsgIdList.getValidCount() > 0) {
////                ZYPEZDItemValueSetter.setValue(lineMsg.boQty_LL, lineMsg.ordQty_LL);
////                return;
////            }
////
////            boQty = lineMsg.ordQty_LL.getValue().subtract(avlQty.subtract(pMsg.ordQty.getValue()));
////
////            if (boQty.compareTo(BigDecimal.ZERO) < 0) {
////                boQty = BigDecimal.ZERO;
////            }
//
////            ZYPEZDItemValueSetter.setValue(lineMsg.boQty_LL, boQty);
//            // 2016/07/04 S21_NA#7645 Del End
//        }
//        // 2016/10/05 S21_NA#7645-3 Del End
//    }
//    // 2016/04/11 S21_NA#3236-3 Add Start
    // 2016/10/05 S21_NA#7645-3 Del End
    /**
     * <pre>
     * Clear these items from NWAL1500_ACMsg
     * @param confMsg DS CPO Config Message
     * </pre>
     */
    public static void clearModelProperties(NWAL1500_ASMsg confMsg) { // 2018/01/31 S21_NA#19808 Mod
        confMsg.mdlNm_LC.clear();
        confMsg.mdlDescTxt_LC.clear();
        confMsg.mdlGrpDescTxt_LC.clear();
        confMsg.svcSegDescTxt_LC.clear();
        confMsg.svcIstlReqFlg_LC.clear();
        confMsg.siteSrvyReqFlg_LC.clear();
    }

    // 2016/04/11 S21_NA#3236-3 Add End
    // 2017/11/21 S21_NA#22555 Add Start
    /**
     * <pre>
     * Clear these items from NWAL1500_CCMsg
     * @param confMsg DS CPO Config Message
     * </pre>
     */
    public static void clearModelProperties(NWAL1500_CSMsg confMsg) { // 2018/01/31 S21_NA#19808 Mod
        confMsg.mdlNm_RC.clear();
        confMsg.mdlDescTxt_RC.clear();
        confMsg.mdlGrpDescTxt_RC.clear();
        confMsg.svcSegDescTxt_RC.clear();
        confMsg.svcIstlReqFlg_RC.clear();
        confMsg.siteSrvyReqFlg_RC.clear();
    }
    // 2017/11/21 S21_NA#22555 Add End

    // 2018/01/31 S21_NA#19808 Add Start
    /**
     * <pre>
     * Clear these items from NWAL1500_ACMsg
     * @param confMsg DS CPO Config Message
     * @param glblMsg Global Message
     * </pre>
     */
    public static void clearModelProperties(NWAL1500_ACMsg configMsg, NWAL1500SMsg glblMsg) {
        configMsg.mdlNm_LC.clear();
        configMsg.mdlDescTxt_LC.clear();
        configMsg.mdlGrpDescTxt_LC.clear();
        configMsg.svcSegDescTxt_LC.clear();
        configMsg.svcIstlReqFlg_LC.clear();
        configMsg.siteSrvyReqFlg_LC.clear();

        NWAL1500CommonLogicForPagination.copyConfigDataToSMsg(configMsg, glblMsg);
    }

    /**
     * <pre>
     * @param rmaConfigMsg RMA DS CPO Config Message
     * @param glblMsg Global Message
     * </pre>
     */
    public static void clearModelProperties(NWAL1500_CCMsg rmaConfigMsg, NWAL1500SMsg glblMsg) {
        rmaConfigMsg.mdlNm_RC.clear();
        rmaConfigMsg.mdlDescTxt_RC.clear();
        rmaConfigMsg.mdlGrpDescTxt_RC.clear();
        rmaConfigMsg.svcSegDescTxt_RC.clear();
        rmaConfigMsg.svcIstlReqFlg_RC.clear();
        rmaConfigMsg.siteSrvyReqFlg_RC.clear();

        NWAL1500CommonLogicForPagination.copyRmaConfigDataToSMsg(rmaConfigMsg, glblMsg);
    }
    // 2018/01/31 S21_NA#19808 Add End
    // 2016/10/05 S21_NA#7645-3 Del Start
//    // 2016/07/04 S21_NA#7645 Add Start
//    /**
//     * <pre>
//     * Get Back Order Qty Key
//     * @param bizMsg Business Message
//     * @param lineMsg Config Line Message
//     * @return Back Order Qty Key
//     * </pre>
//     */
//    public static String getBoQtyKey(NWAL1500CMsg bizMsg, NWAL1500_BCMsg lineMsg) {
//
//        String invtyLocCd = INTG;
//        if (ZYPCommonFunc.hasValue(lineMsg.rtlWhCd_LL)) {
//            invtyLocCd = lineMsg.rtlWhCd_LL.getValue();
//            if (ZYPCommonFunc.hasValue(lineMsg.rtlSwhCd_LL.getValue())) {
//                invtyLocCd = invtyLocCd + lineMsg.rtlSwhCd_LL.getValue();
//            }
//        }
//        BigDecimal ordQty = BigDecimal.ZERO;
//        if (ZYPCommonFunc.hasValue(lineMsg.ordQty_LL)) {
//            ordQty = lineMsg.ordQty_LL.getValue();
//        }
//        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue());
//        if (mdseTMsg == null) {
//            return invtyLocCd + ":" + lineMsg.mdseCd_LL.getValue() + ":" + ordQty.toString();
//        }
//        return invtyLocCd + ":" + mdseTMsg.mdseCd.getValue() + ":" + ordQty.toString();
//    }
//    // 2016/07/04 S21_NA#7645 Add End
    // 2016/10/05 S21_NA#7645-3 Del End

    // START 2016/08/08 S21_NA#11592 Add
    /**
     * getForceDummyWhByDsOrdLineCatgCd
     * @param glblCmpyCd String
     * @param dsOrdLineCatgCd String
     * @return String
     * 
     * retrun values is null then not Force Dummy Wh
     */
    public static String getForceDummyWhByDsOrdLineCatgCd(String glblCmpyCd, String dsOrdLineCatgCd) {
        ORD_LINE_VAL_SETTMsg ordLineValSet = new ORD_LINE_VAL_SETTMsg();
        ordLineValSet.setSQLID("001");
        ordLineValSet.setConditionValue("glblCmpyCd01", glblCmpyCd);
        ordLineValSet.setConditionValue("ordLineCtxTpCd01", ORD_LINE_CTX_TP.FORCE_DUMMY_WH);
        ordLineValSet.setConditionValue("dsOrdLineCatgCd01", dsOrdLineCatgCd);

        ORD_LINE_VAL_SETTMsgArray ordLineValSetAry = (ORD_LINE_VAL_SETTMsgArray) EZDTBLAccessor.findByCondition(ordLineValSet);
        if (ordLineValSetAry.getValidCount() == 0) {
            return null;

        } else {
            return ordLineValSetAry.no(0).scdBizCtxAttrbTxt.getValue();
        }
    }
    // END   2016/08/08 S21_NA#11592 Add

    // QC#9761 2016/09/27 Add Start
    private static String chgBaseMdseCd(String glblCmpyCd, String baseMdseCd) {
        if (baseMdseCd.length() > 8) {
            String mdseCd = baseMdseCd.substring(0, 8);
            ORD_TAKE_MDSETMsg tMsg = getOrdTakeMdse2(glblCmpyCd, mdseCd);
            if (tMsg != null) {
                baseMdseCd = tMsg.ordTakeMdseCd.getValue();
            }
        }
        return baseMdseCd;
    }

    /**
     * Get Ord Take Mdse
     * @param glblCmpyCd String
     * @param mdseCd Merchandise Code
     * @return ALL_MDSE_V Array
     */
    private static ORD_TAKE_MDSETMsg getOrdTakeMdse2(String glblCmpyCd, String mdseCd) {

        ORD_TAKE_MDSETMsg ordTakeMdse = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(ordTakeMdse.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTakeMdse.ordTakeMdseCd, mdseCd);

        return (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdse); // 2018/03/08 S21_NA#19808
    }
    // QC#9761 2016/09/27 Add End

    // 2019/11/21 S21_NA#54202 Del Start
//    // 2016/11/30 S21_NA#16098 Add Start
//    /**
//     * Is Change Model And Shell Relation
//     * @param bizMsg NWAL1500CMsg
//     * @param glblMsg NWAL1500SMsg
//     * @param configMsg NWAL1500_ASMsg
//     * @return boolean true: Model was Changed. false: not changed
//     */
//    public static boolean isChangeMdlAndShellReln(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg configMsg) { // 2018/01/31 S21_NA#19808 Mod
//
//        NWAL1500CommonLogicForLineConfig.deriveMdl(bizMsg, glblMsg, configMsg);  // 2018/01/31 S21_NA#19808 Mod
//
//        if (!NWAL1500CommonLogic.isEqualsEZDItem(configMsg.mdlId_LC, configMsg.mdlId_DB)) { // 2018/01/31 S21_NA#19808 Mod
//            BigDecimal shellCnt = NWAL1500QueryForLineConfig.getInstance().getRelatedShellDataCountFromConfig(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum.getValue(), configMsg.dsOrdPosnNum_LC.getValue());
//            if (BigDecimal.ZERO.compareTo(shellCnt) == 0) {
//                return false;
//            } else {
//                return true;
//            }
//        }
//        return false;
//    }
//    // 2016/11/30 S21_NA#16098 Add End
    // 2019/11/21 S21_NA#54202 Del End

    // Add Start 2016/01/27 QC#17257
    /**
     * Update Base Compornent Flag
     * @param bizMsg NWAL1500CMsg
     * @param posnNums ArrayList<String>
     * @param delFlg boolean
     */
    public static void updateBaseCmptFlg(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, List<String> posnNums, boolean delFlg) { // 2018/01/30 S21_NA#19808 Mod

        // 2018/01/30 S21_NA#19808 Mod bizMsg.A, B, C, D => glblMsg.A, B, C, D
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String dsOrdCatgCd = bizMsg.dsOrdCatgCd.getValue();
        String dsOrdTpCd = bizMsg.dsOrdTpCd.getValue();
        String dsOrdRsnCd = bizMsg.dsOrdRsnCd.getValue();

        String dplyTab = bizMsg.xxDplyTab.getValue();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_RMA);
        NWAL1500CommonLogic.numberingOrderLineNumber(bizMsg, glblMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, dplyTab);

        if (!NWAL1500QueryForLineConfig.getInstance().isOrderRetailEquipment(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd, dsOrdRsnCd)) {
            clearBaseCmptFlg(glblMsg.D);

            LinkedHashMap<String, List<NWAL1500_DSMsg>> cpoConfigMap = createConfigMap(glblMsg.D, posnNums); // 2018/01/30 S21_NA#19808 Mod
            for (List<NWAL1500_DSMsg> cpoDtlBeanList : cpoConfigMap.values()) {
                setDefaultRefNumAndSubWh(cpoDtlBeanList, null, bizMsg.glblCmpyCd.getValue()); // 2018/01/30 S21_NA#19808 Mod // 2018/12/19 S21_NA#29561 Add Param GlblCmpy
            }

            return;
        }

        LinkedHashMap<String, List<NWAL1500_DSMsg>> cpoConfigMap = createConfigMap(glblMsg.D, posnNums); // 2018/01/30 S21_NA#19808 Mod
        // Add Start 2017/02/02 QC#17349
        Map<String, BigDecimal> svcConfigMstrPkMap = getSvcConfigMstrPk(glblMsg.C, posnNums); // 2018/01/30 S21_NA#19808 Mod
        Map<String, String> getConfigTpCdMap = getConfigTpCd(glblMsg.C, posnNums); // 2018/01/30 S21_NA#19808 Mod
        // Add End 2017/02/02 QC#17349
        for (List<NWAL1500_DSMsg> cpoDtlBeanList : cpoConfigMap.values()) {
            // Add Start 2017/02/02 QC#17349
            BigDecimal svcConfigMstrPk = null;
            String configTCd = null;
            String postCd = null;
            if (cpoDtlBeanList.size() > 0) {
                svcConfigMstrPk = svcConfigMstrPkMap.get(cpoDtlBeanList.get(0).dsOrdPosnNum_RL.getValue());
                configTCd = getConfigTpCdMap.get(cpoDtlBeanList.get(0).dsOrdPosnNum_RL.getValue());
                postCd = getConfPostCd(glblMsg.C, cpoDtlBeanList.get(0).dsOrdPosnNum_RL.getValue());
            }
            // Add End 2017/02/02 QC#17349
            // Mod Start 2017/02/02 QC#17349
//            NWAL1500_DCMsg baseCmptCpoDtlMsg = getBaseCmptFlg(cpoDtlBeanList, glblCmpyCd);
            NWAL1500_DSMsg baseCmptCpoDtlMsg = getBaseCmptFlg(cpoDtlBeanList, glblCmpyCd, configTCd, dsOrdCatgCd, dsOrdTpCd, postCd, svcConfigMstrPk, delFlg);
            // Mod End 2017/02/02 QC#17349

            setDefaultRefNumAndSubWh(cpoDtlBeanList, baseCmptCpoDtlMsg, bizMsg.glblCmpyCd.getValue()); // 2018/12/19 S21_NA#29561 Add Param GlblCmpy
        }
    }

    /**
     * Clear Base Compornent Flag
     * @param dtlMsg NWAL1500_DCMsgArray
     */
    public static void clearBaseCmptFlg(NWAL1500_DSMsgArray dtlMsg) { // 2018/01/30 S21_NA#19808 Mod

        for (int i = 0; i < dtlMsg.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(dtlMsg.no(i).baseCmptFlg_RL, ZYPConstant.FLG_OFF_N);
        }
        return;
    }

    /**
     * Create Config Map
     * @param cpoMsg NWAL1500_DCMsgArray
     * @param posnNums List<String>
     * @return LinkedHashMap<String, List<NWAL1500_DCMsg>>
     */
    public static LinkedHashMap<String, List<NWAL1500_DSMsg>> createConfigMap(NWAL1500_DSMsgArray cpoMsg, List<String> posnNums) {

        // 2018/01/30 S21_NA#19808 Mod bizMsg.D => glblMsg.D
        LinkedHashMap<String, List<NWAL1500_DSMsg>> cpoConfigMap = new LinkedHashMap<String, List<NWAL1500_DSMsg>> ();
        List<NWAL1500_DSMsg> cpoRtrnDtlArray = null;
        String dsOrdPosnNum;

        for (int i = 0; i < cpoMsg.getValidCount(); i++) {
            dsOrdPosnNum = cpoMsg.no(i).dsOrdPosnNum_RL.getValue();

            if (null == posnNums || posnNums.contains(dsOrdPosnNum)) {
                if (!cpoConfigMap.containsKey(dsOrdPosnNum)) {
                    cpoRtrnDtlArray = new ArrayList<NWAL1500_DSMsg>();
                    cpoConfigMap.put(dsOrdPosnNum, cpoRtrnDtlArray);
                }
                ZYPEZDItemValueSetter.setValue(cpoMsg.no(i).baseCmptFlg_RL, ZYPConstant.FLG_OFF_N);
                cpoMsg.no(i).dplyLineRefNum_RL.clear();
                cpoRtrnDtlArray = cpoConfigMap.get(dsOrdPosnNum);

                cpoRtrnDtlArray.add(cpoMsg.no(i));
            }
        }

        return cpoConfigMap;
    }

    /**
     * Create Config Map
     * @param cpoDtlMsgList List<NWAL1500_DCMsg>
     * @param glblCmpyCd String
     * @param configTCd String
     * @pram dsOrdCatgCd String
     * @param dsOrdTpCd String
     * @param postCd String
     * @param svcConfigMstrPk BigDecimal
     * @param delFlg boolean
     * @return NWAL1500_DCMsg
     */
    public static NWAL1500_DSMsg getBaseCmptFlg(List<NWAL1500_DSMsg> cpoDtlMsgList, String glblCmpyCd, String configTCd, String dsOrdCatgCd, String dsOrdTpCd, String postCd, BigDecimal svcConfigMstrPk, boolean delFlg) { // 2018/01/30 S21_NA#19808 Mod

        NWAL1500_DSMsg baseCmptFlgCpoDtlMsg = null;
        MDSETMsg mdse;
        String mdseCd = null;
        String instlBaseCtrlFlg = null;
        String mdseTpCtxTpCd = null;
        // Add Start 2017/02/02 QC#17349
        String slsDt = null;
        String dsRtrnRsnCd = null;
        BigDecimal ordQty = null;
        BigDecimal svcMachMstrPk = null;
        String rtlWhCd = null;
        String serNum = null;
        boolean isDefWhRequested = true;
        boolean isBaseCompornent = false;

        if (getConfigExist(glblCmpyCd, configTCd)) {
            baseCmptFlgCpoDtlMsg = new NWAL1500_DSMsg();
            NWAL1500_DSMsg notBaseCmptFlgCpoDtlMsg = null;

            for (NWAL1500_DSMsg cpoDtlMsg : cpoDtlMsgList) {

                if (delFlg && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, cpoDtlMsg.xxChkBox_RL.getValue())) {
                    continue;
                }
                if (TO_BE_CANCELLED.equals(cpoDtlMsg.rtrnLineStsDescTxt_RL.getValue()) //
                        || HEADER_STS_NM_CANCELLED.equals(cpoDtlMsg.rtrnLineStsDescTxt_RL.getValue())) {
                    continue;
                }

                // Set Paramater
                slsDt = ZYPDateUtil.getSalesDate();
                dsRtrnRsnCd = cpoDtlMsg.rtrnRsnCd_RL.getValue();
                mdseCd = cpoDtlMsg.mdseCd_RL.getValue();
                ordQty = cpoDtlMsg.ordQty_RL.getValue();
                svcMachMstrPk = cpoDtlMsg.svcMachMstrPk_RL.getValue();
                rtlWhCd = cpoDtlMsg.rtlWhCd_RL.getValue();
                serNum = cpoDtlMsg.serNum_RL.getValue();

                Map<String, Object> getBaseCmpnt = NWXC150001DsCheck.getBaseComponentInfoForRma(//
                        glblCmpyCd, slsDt, dsOrdCatgCd, dsOrdTpCd, dsRtrnRsnCd, postCd, mdseCd, ordQty, //
                        svcConfigMstrPk, svcMachMstrPk, rtlWhCd, serNum, isDefWhRequested);

                // Check Base Compornent Flag
                if (S21StringUtil.isEquals((String) getBaseCmpnt.get("BASE_CMPT_FLG"), ZYPConstant.FLG_ON_Y)) {
                    isBaseCompornent = true;
                    ZYPEZDItemValueSetter.setValue(baseCmptFlgCpoDtlMsg.baseCmptFlg_RL, (String) getBaseCmpnt.get("BASE_CMPT_FLG"));
                    ZYPEZDItemValueSetter.setValue(baseCmptFlgCpoDtlMsg.svcMachMstrPk_RL, (BigDecimal) getBaseCmpnt.get("SVC_MACH_MSTR_PK"));
                    ZYPEZDItemValueSetter.setValue(baseCmptFlgCpoDtlMsg.rtlWhCd_RL, (String) getBaseCmpnt.get("RTL_WH_CD"));
                    ZYPEZDItemValueSetter.setValue(baseCmptFlgCpoDtlMsg.rtlWhNm_RL, (String) getBaseCmpnt.get("RTL_WH_NM"));
                    ZYPEZDItemValueSetter.setValue(baseCmptFlgCpoDtlMsg.rtlSwhCd_RL, (String) getBaseCmpnt.get("RTL_SWH_CD"));
                    ZYPEZDItemValueSetter.setValue(baseCmptFlgCpoDtlMsg.rtlSwhNm_RL, (String) getBaseCmpnt.get("RTL_SWH_NM"));
                    ZYPEZDItemValueSetter.setValue(baseCmptFlgCpoDtlMsg.thirdPtyDspTpCd_RL, (String) getBaseCmpnt.get("THIRD_PTY_DSP_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(baseCmptFlgCpoDtlMsg.dsOrdPosnNum_RL, cpoDtlMsg.dsOrdPosnNum_RL.getValue());
                    ZYPEZDItemValueSetter.setValue(baseCmptFlgCpoDtlMsg.dsCpoLineNum_RL, cpoDtlMsg.dsCpoLineNum_RL.getValue());
                    ZYPEZDItemValueSetter.setValue(baseCmptFlgCpoDtlMsg.dsCpoLineSubNum_RL, cpoDtlMsg.dsCpoLineSubNum_RL.getValue());
                    // Add Start 2017/07/03 QC#19709
                    ZYPEZDItemValueSetter.setValue(baseCmptFlgCpoDtlMsg.invtyLocCd_RL, (String) getBaseCmpnt.get("INVTY_LOC_CD"));
                    // Add End 2017/07/03 QC#19709
                    ZYPEZDItemValueSetter.setValue(cpoDtlMsg.baseCmptFlg_RL, ZYPConstant.FLG_ON_Y);
                }

                // Check Service Machine Master PK
                if (!ZYPCommonFunc.hasValue(cpoDtlMsg.svcMachMstrPk_RL)) {
                    ZYPEZDItemValueSetter.setValue(cpoDtlMsg.svcMachMstrPk_RL, (BigDecimal) getBaseCmpnt.get("SVC_MACH_MSTR_PK_FOR_COMPONENT"));
                }

                if (isDefWhRequested && ZYPCommonFunc.hasValue((String) getBaseCmpnt.get("RTL_WH_CD"))) {
                    if (null == notBaseCmptFlgCpoDtlMsg) {
                        notBaseCmptFlgCpoDtlMsg = new NWAL1500_DSMsg();
                    }
                    ZYPEZDItemValueSetter.setValue(notBaseCmptFlgCpoDtlMsg.rtlWhCd_RL, (String) getBaseCmpnt.get("RTL_WH_CD"));
                    ZYPEZDItemValueSetter.setValue(notBaseCmptFlgCpoDtlMsg.rtlWhNm_RL, (String) getBaseCmpnt.get("RTL_WH_NM"));
                    ZYPEZDItemValueSetter.setValue(notBaseCmptFlgCpoDtlMsg.rtlSwhCd_RL, (String) getBaseCmpnt.get("RTL_SWH_CD"));
                    ZYPEZDItemValueSetter.setValue(notBaseCmptFlgCpoDtlMsg.rtlSwhNm_RL, (String) getBaseCmpnt.get("RTL_SWH_NM"));
                    ZYPEZDItemValueSetter.setValue(notBaseCmptFlgCpoDtlMsg.thirdPtyDspTpCd_RL, (String) getBaseCmpnt.get("THIRD_PTY_DSP_TP_CD"));
                    // Add Start 2017/07/03 QC#19709
                    ZYPEZDItemValueSetter.setValue(notBaseCmptFlgCpoDtlMsg.invtyLocCd_RL, (String) getBaseCmpnt.get("INVTY_LOC_CD"));
                    // Add End 2017/07/03 QC#19709
                    isDefWhRequested = false;
                }
            }

            if (isBaseCompornent) {
                return baseCmptFlgCpoDtlMsg;
            } else {
                return notBaseCmptFlgCpoDtlMsg;
            }
        }
        // Add End 2017/02/02 QC#17349

        for (NWAL1500_DSMsg cpoDtlMsg : cpoDtlMsgList) {
            mdseCd = cpoDtlMsg.mdseCd_RL.getValue();
            mdse = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);

            if (delFlg && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, cpoDtlMsg.xxChkBox_RL.getValue())) {
                continue;
            }
            if (TO_BE_CANCELLED.equals(cpoDtlMsg.rtrnLineStsDescTxt_RL.getValue()) //
                    || HEADER_STS_NM_CANCELLED.equals(cpoDtlMsg.rtrnLineStsDescTxt_RL.getValue())) {
                continue;
            }
            if (null == mdse) {
                continue;
            }
            if (S21StringUtil.isEquals(mdse.mdseTpCd.getValue(), MDSE_TP.SET)) {
                continue;
            }

            // get base component flag
            S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getBaseCmptFlg(glblCmpyCd, mdseCd);
            if (ssmResult.isCodeNotFound()) {
                continue;
            }

            Map<?, ?> baseComponentFlag = (Map<?, ?>) ssmResult.getResultObject();
            instlBaseCtrlFlg = (String) baseComponentFlag.get("INSTL_BASE_CTRL_FLG");
            mdseTpCtxTpCd = (String) baseComponentFlag.get("MDSE_TP_CTX_TP_CD");

            if (S21StringUtil.isNotEmpty(mdseTpCtxTpCd)) {
                // main machine
                ZYPEZDItemValueSetter.setValue(cpoDtlMsg.baseCmptFlg_RL, ZYPConstant.FLG_ON_Y);
                cpoDtlMsg.dplyLineRefNum_RL.clear();
                return cpoDtlMsg;
            }

            if (null != baseCmptFlgCpoDtlMsg) {
                continue;
            }

            if (S21StringUtil.isEquals(instlBaseCtrlFlg, ZYPConstant.FLG_ON_Y)) {
                // first install base
                baseCmptFlgCpoDtlMsg = cpoDtlMsg;
            }
        }

        if (null != baseCmptFlgCpoDtlMsg) {
            ZYPEZDItemValueSetter.setValue(baseCmptFlgCpoDtlMsg.baseCmptFlg_RL, ZYPConstant.FLG_ON_Y);
            baseCmptFlgCpoDtlMsg.dplyLineRefNum_RL.clear();
        }

        return baseCmptFlgCpoDtlMsg;
    }

    /**
     * Set Default Reference Number And Sub Warehouse
     * @param cpoDtlBeanList List<NWAL1500_DCMsg>
     * @param baseCmptCpoDtlMsg NWAL1500_DCMsg
     */
    public static void setDefaultRefNumAndSubWh(List<NWAL1500_DSMsg> cpoDtlMsgList, NWAL1500_DSMsg baseCmptCpoDtlMsg, String glblCmpyCd) {  // 2018/01/30 S21_NA#19808 Mod

        String dplyLineRefNum = null;
        String refCpoDtlLineNum = null;
        String refCpoDtlLineSubNum = null;
        // Add Start 2017/06/21 QC#19407
        String thirdPtyDspTpCd = null;
        // Add End 2017/06/21 QC#19407
        for (NWAL1500_DSMsg cpoDtlMsg : cpoDtlMsgList) {
            // Del Start 2017/02/01 QC#17257
//            if (ZYPCommonFunc.hasValue(cpoDtlMsg.dplyLineRefNum_RL.getValue()) //
//                    && !ZYPCommonFunc.hasValue(dplyLineRefNum)) {
//                dplyLineRefNum = cpoDtlMsg.dplyLineRefNum_RL.getValue();
//            }
            // Del End 2017/02/01 QC#17257
            if (null == baseCmptCpoDtlMsg //
                    && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, cpoDtlMsg.baseCmptFlg_RL.getValue())) {
                baseCmptCpoDtlMsg = cpoDtlMsg;
            }
        }
        // Mod Start 2017/02/01 QC#17257
//        if (dplyLineRefNum == null && baseCmptCpoDtlMsg != null) {
        if (dplyLineRefNum == null && baseCmptCpoDtlMsg != null && ZYPCommonFunc.hasValue(baseCmptCpoDtlMsg.dsCpoLineNum_RL)) {
        // Mod End 2017/02/01 QC#17257
            dplyLineRefNum = getLineRefNum(baseCmptCpoDtlMsg.dsOrdPosnNum_RL.getValue(), getStringValue(baseCmptCpoDtlMsg.dsCpoLineNum_RL.getValue()));
            if (ZYPCommonFunc.hasValue(baseCmptCpoDtlMsg.dsCpoLineSubNum_RL.getValue())) {
                dplyLineRefNum = getLineRefNum(dplyLineRefNum, getStringValue(baseCmptCpoDtlMsg.dsCpoLineSubNum_RL.getValue()));
            }
        }

        if (dplyLineRefNum != null) {
            NWAL1500_DSMsg refCpoDtlMsg = getRefCpoDtlMsg(cpoDtlMsgList, dplyLineRefNum);
            if (refCpoDtlMsg != null) {
                refCpoDtlLineNum = getStringValue(refCpoDtlMsg.dsCpoLineNum_RL.getValue());
                refCpoDtlLineSubNum = getStringValue(refCpoDtlMsg.dsCpoLineSubNum_RL.getValue());
                // Add Start 2017/06/21 QC#19407
                if (ZYPCommonFunc.hasValue(refCpoDtlMsg.thirdPtyDspTpCd_RL)) {
                    thirdPtyDspTpCd = refCpoDtlMsg.thirdPtyDspTpCd_RL.getValue();
                }
                // Add End 2017/06/21 QC#19407
            }
        }

        for (NWAL1500_DSMsg cpoDtlMsg : cpoDtlMsgList) {
            // Del Start 2017/02/02 QC#17349
//            boolean isRefMsg = false;
//            if (S21StringUtil.isEquals(getStringValue(cpoDtlMsg.dsCpoLineNum_RL.getValue()), refCpoDtlLineNum) //
//                    && S21StringUtil.isEquals(getStringValue(cpoDtlMsg.dsCpoLineSubNum_RL.getValue()), refCpoDtlLineSubNum)) {
//                isRefMsg = true;
//            }
            // Del End 2017/02/02 QC#17349
            // Mod Start 2017/02/02 QC#17349
//            if (isRefMsg || S21StringUtil.isEquals(cpoDtlMsg.baseCmptFlg_RL.getValue(), ZYPConstant.FLG_ON_Y)) {
            if (S21StringUtil.isEquals(cpoDtlMsg.baseCmptFlg_RL.getValue(), ZYPConstant.FLG_ON_Y) //
                    || (baseCmptCpoDtlMsg != null //
                            && !S21StringUtil.isEquals(baseCmptCpoDtlMsg.baseCmptFlg_RL.getValue(), ZYPConstant.FLG_ON_Y))) {
            // Mod End 2017/02/02 QC#17349
                cpoDtlMsg.dplyLineRefNum_RL.clear();
                cpoDtlMsg.refCpoDtlLineNum_RL.clear();
                cpoDtlMsg.refCpoDtlLineSubNum_RL.clear();
                // Del Start 2017/06/27 QC#19585
//                cpoDtlMsg.thirdPtyDspTpCd_RL.clear();
                // Del End 2017/06/27 QC#19585
            } else {
                ZYPEZDItemValueSetter.setValue(cpoDtlMsg.dplyLineRefNum_RL, dplyLineRefNum);
                ZYPEZDItemValueSetter.setValue(cpoDtlMsg.refCpoDtlLineNum_RL, refCpoDtlLineNum);
                ZYPEZDItemValueSetter.setValue(cpoDtlMsg.refCpoDtlLineSubNum_RL, refCpoDtlLineSubNum);
                // Add Start 2017/06/21 QC#19407
                ZYPEZDItemValueSetter.setValue(cpoDtlMsg.thirdPtyDspTpCd_RL, thirdPtyDspTpCd);
                // Add End 2017/06/21 QC#19407
            }

            if (baseCmptCpoDtlMsg != null) {
                // 2018/12/19 S21_NA#29561 Mod Start
                // if (!SUPPLY_SUB_WH_CD.equals(cpoDtlMsg.rtlSwhCd_RL)) {
                if (!SUPPLY_SUB_WH_CD.equals(cpoDtlMsg.rtlSwhCd_RL) && NWAL1500CommonLogic.needsWh(glblCmpyCd, cpoDtlMsg.mdseCd_RL.getValue())) {
                    // 2018/12/19 S21_NA#29561 Mod End
                    // 2020/05/25 QC#56558-1 Del Start
                    // QC#56558 2020/04/14 Add Start
                    // ZYPEZDItemValueSetter.setValue(cpoDtlMsg.rtlWhCd_RL, baseCmptCpoDtlMsg.rtlWhCd_RL.getValue());
                    // ZYPEZDItemValueSetter.setValue(cpoDtlMsg.rtlWhNm_RL, baseCmptCpoDtlMsg.rtlWhNm_RL.getValue());
                    // QC#56558 2020/04/14 Add End
                    // 2020/05/25 QC#56558-1 Del End
                    ZYPEZDItemValueSetter.setValue(cpoDtlMsg.rtlSwhCd_RL, baseCmptCpoDtlMsg.rtlSwhCd_RL.getValue());
                    ZYPEZDItemValueSetter.setValue(cpoDtlMsg.rtlSwhNm_RL, baseCmptCpoDtlMsg.rtlSwhNm_RL.getValue());
                    // Add Start 2017/06/21 QC#19407
                    ZYPEZDItemValueSetter.setValue(cpoDtlMsg.thirdPtyDspTpCd_RL, baseCmptCpoDtlMsg.thirdPtyDspTpCd_RL.getValue());
                    // Add End 2017/06/21 QC#19407
                    // Add Start 2017/07/03 QC#19709
                    // Mod Start 2018/03/15 QC#24258
                    if (ZYPCommonFunc.hasValue(cpoDtlMsg.rtlWhCd_RL) //
                            && ZYPCommonFunc.hasValue(baseCmptCpoDtlMsg.rtlWhCd_RL) //
                            && cpoDtlMsg.rtlWhCd_RL.getValue().equals(baseCmptCpoDtlMsg.rtlWhCd_RL.getValue())) {
                        StringBuilder invtyLocCd = new StringBuilder();
                        invtyLocCd.append(cpoDtlMsg.rtlWhCd_RL.getValue());
                        invtyLocCd.append(baseCmptCpoDtlMsg.rtlSwhCd_RL.getValue());
                        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.invtyLocCd_RL, invtyLocCd.toString());
                    } else {
                        ZYPEZDItemValueSetter.setValue(cpoDtlMsg.invtyLocCd_RL, baseCmptCpoDtlMsg.invtyLocCd_RL.getValue());
                    }
                    
                    // Mod End 2018/03/15 QC#24258
                    // Add End 2017/07/03 QC#19709
                }
            }
        }
    }

    /**
     * get Get Reference Cpo Detail Message
     * @param cpoDtlMsgList List<NWAL1500_DCMsg>
     * @param dplyLineRefNum String
     * @return NWAL1500_DCMsg
     */
    public static NWAL1500_DSMsg getRefCpoDtlMsg(List<NWAL1500_DSMsg> cpoDtlMsgList, String dplyLineRefNum) { // 2018/01/30 S21_NA#19808 Mod

        for (NWAL1500_DSMsg cpoDtlMsg : cpoDtlMsgList) {
            String dsCpoLineNum = getStringValue(cpoDtlMsg.dsCpoLineNum_RL.getValue());
            String dsCpoLineSubNum = getStringValue(cpoDtlMsg.dsCpoLineSubNum_RL.getValue());
            String checkLineNum = getLineRefNum(cpoDtlMsg.dsOrdPosnNum_RL.getValue(), dsCpoLineNum);
            if (ZYPCommonFunc.hasValue(dsCpoLineSubNum)) {
                checkLineNum = getLineRefNum(checkLineNum, dsCpoLineSubNum);
            }
            boolean isRefMsg = S21StringUtil.isEquals(dplyLineRefNum, checkLineNum);
            if (isRefMsg) {
                return cpoDtlMsg;
            }
        }

        return null;
    }

    /**
     * get Line Reference Number
     * @param posnNum String
     * @param lineNum String
     * @return String
     */
    public static String getLineRefNum(String posnNum, String lineNum) {

        StringBuilder sbRefNum = new StringBuilder();
        sbRefNum.append(posnNum);
        sbRefNum.append(".");
        sbRefNum.append(lineNum);

        return sbRefNum.toString();
    }

    /**
     * get String Value
     * @param setValue BigDecimal
     * @return String
     */
    public static String getStringValue(BigDecimal setValue) {

        if (ZYPCommonFunc.hasValue(setValue)) {
            return setValue.toString();
        }

        return null;
    }
    // Add End 2016/01/27 QC#17257

    // Add Start 2016/01/27 QC#17349
    /**
     * get Service Machine Master PK Map
     * @param cMsg NWAL1500_CCMsg
     * @param posnNums List<String>
     * @return String
     */
    public static Map<String, BigDecimal> getSvcConfigMstrPk(NWAL1500_CSMsgArray hdrMsgArray, List<String> posnNums) { // 2018/01/30 S21_NA#19808 Mod

        // 2018/01/30 S21_NA#19808 Mod bizMsg.C => glblMsg.C
        Map<String, BigDecimal> svcConfigMstrPkMap = new HashMap<String, BigDecimal>();

        for (int i = 0; i < hdrMsgArray.getValidCount(); i++) {

            String getPosnNum = hdrMsgArray.no(i).dsOrdPosnNum_RC.getValue();
            if (posnNums.contains(getPosnNum)) {
                svcConfigMstrPkMap.put(getPosnNum, hdrMsgArray.no(i).svcConfigMstrPk_RC.getValue());
            }
        }

        return svcConfigMstrPkMap;
    }

    /**
     * get Config Type Code
     * @param cMsg NWAL1500_CCMsg
     * @param posnNums List<String>
     * @return String
     */
    public static Map<String, String> getConfigTpCd(NWAL1500_CSMsgArray hdrMsgArray, List<String> posnNums) { // 2018/01/30 S21_NA#19808 Mod

        Map<String, String> getConfigTpCdMap = new HashMap<String, String>();

        for (int i = 0; i < hdrMsgArray.getValidCount(); i++) {

            String getPosnNum = hdrMsgArray.no(i).dsOrdPosnNum_RC.getValue();
            if (posnNums.contains(getPosnNum)) {
                getConfigTpCdMap.put(getPosnNum, hdrMsgArray.no(i).configTpCd_RC.getValue());
            }
        }

        return getConfigTpCdMap;
    }

    /**
     * Get Config Post Code
     * @param hdrMsgArray NWAL1500_CCMsgArray
     * @param dsOrdPosnNum DS Order Position Number
     * @param isLineConfFlag Called Line Config
     */
    public static String getConfPostCd(NWAL1500_CSMsgArray hdrMsgArray, String dsOrdPosnNum) { // 2018/01/30 S21_NA#19808 Mod

        for (int i = 0; i < hdrMsgArray.getValidCount(); i++) {
            NWAL1500_CSMsg hdrMsg = hdrMsgArray.no(i);
            if (dsOrdPosnNum.equals(hdrMsg.dsOrdPosnNum_RC.getValue())) {
                return hdrMsg.shipToPostCd_RC.getValue();
            }
        }

        return null;
    }

    public static boolean getConfigExist(String glblCmpyCd, String configTpCd) {

        CONFIG_TPTMsg configTp = ConfigTpCache.getInstance().getTMsgByKey(glblCmpyCd, configTpCd);

        if (configTp == null) {
            return false;
        }
        if (S21StringUtil.isEquals(configTp.configNewFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            return false;
        }

        return true;
    }
    // Add End 2016/01/27 QC#17349
    // 2018/01/11 S21_NA#23329 Add Start
    /**
     * <pre>
     * Set Software Model Parent Item Information.
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     * @param slctConfIndex selected config index. (Global Index)
     * @param svcMdlApiPMsg Service Model API PMessage. If thie parameter is null, this method do nothing.
     * </pre>
     */
//    public static void setSoftModelParent(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex, String prntMdseCd) {
    public static void setSoftModelParent(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex, String prntMdseCd, boolean copyCompare) {

        if (!ZYPCommonFunc.hasValue(prntMdseCd)) {
            return;
        }
        // TODO S21_NA#23329
//        DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
//        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.mdlId, svcMdlApiPMsg.mdlId);
//        dsMdlTMsg = (DS_MDLTMsg) S21CacheTBLAccessor.findByKey(dsMdlTMsg);
//        if (dsMdlTMsg == null) {
//            return;
//        }
//        String svcMdlTpCd = dsMdlTMsg.svcMdlTpCd.getValue();
        if (!NWXC150001DsCheck.isModelSoftware(glblMsg.A.no(slctConfIndex).svcMdlTpCd_LC.getValue())) {
            return;
        }

        // Add Parent Item to the tail of the config.
        // 2018/03/14 S21_NA#24117-1 mod start
        insertParentMdse(bizMsg, glblMsg, slctConfIndex, prntMdseCd, copyCompare);
        // 2018/03/14 S21_NA#24117-1 mod end
    }

    private static int getPrntMdseIndex(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg confMsg, String prntMdseCd) { // 2018/01/30 S21_NA#19808 Mod

        int size = new ORD_TAKE_MDSETMsg().getAttr("ordTakeMdseCd").getDigit();
        String dsOrdPosnNum = confMsg.dsOrdPosnNum_LC.getValue();

        String prntOrdTakeMdseCd = new String(prntMdseCd);
        if (prntOrdTakeMdseCd.length() > size) {
            prntOrdTakeMdseCd = prntOrdTakeMdseCd.substring(0, size);
        }

        for (int n = 0; n < glblMsg.B.getValidCount(); n++) { // 2018/01/30 S21_NA#19808 Mod
            if (!S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(n).dsOrdPosnNum_LL.getValue())) {
                continue;
            }
            String mdseCd = glblMsg.B.no(n).mdseCd_LL.getValue();
            if (NWXC150001DsCheck.isNearEqualsItem(bizMsg.glblCmpyCd.getValue(), prntMdseCd, mdseCd)) {
                return n;
            }
        }
        return -1;
    }

    // 2018/03/14 S21_NA#24117-1 mod start
//    private static void insertParentMdse(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex, String prntMdseCd) {
    private static void insertParentMdse(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex, String prntMdseCd, boolean copyCompare) {

        // 2018/03/14 S21_NA#24117-1 mod end
        NWAL1500_ASMsg confMsg = glblMsg.A.no(slctConfIndex); // 2018/01/30 S21_NA#19808 Mod
        NWAL1500_BSMsgArray lineList = glblMsg.B; // 2018/01/30 S21_NA#19808 Mod
        NWAL1500_USMsgArray compareLineList = glblMsg.U;  // 2018/03/14 S21_NA#24117-1 add

        // 2018/03/20 S21_NA#24842 Add Start
        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), confMsg.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, true, true)) {
            return;
        }
        // 2018/03/20 S21_NA#24842 Add End
        NWAL1500CMsg cloneCMsg = (NWAL1500CMsg) bizMsg.clone();

        String dsOrdPosnNum = confMsg.dsOrdPosnNum_LC.getValue();
        int maxLineNum = NWAL1500CommonLogicForConfigId.getMaxLineNum(lineList, dsOrdPosnNum);
        NWAL1500_BSMsg newLine = null; // 2018/01/30 S21_NA#19808 Mod
        NWAL1500_USMsg newCompareLine = null;  // 2018/03/14 S21_NA#24117-1 add
        boolean addLineFlg = false;
        BigDecimal settingLineNum = BigDecimal.ZERO;

        int prntMdseIndex = getPrntMdseIndex(bizMsg, glblMsg, confMsg, prntMdseCd); // 2018/01/30 S21_NA#19808 Mod
        if (prntMdseIndex < 0) {
            int lineStartIndex = NWAL1500CommonLogicForConfigId.getAddLineRow(lineList, dsOrdPosnNum);
            int insertRow = lineStartIndex;
    
    //        NWAL1500_BCMsg baseCmpntMsg = deriveBaseCmpntMsg(lineList, dsOrdPosnNum);
    
            newLine = (NWAL1500_BSMsg) NWAL1500CommonLogicForConfigId.insertNewLine(lineList, insertRow++); // 2018/01/30 S21_NA#19808 Mod
            if (newLine == null) {
                // full line
                EZDMsg.copy(cloneCMsg.B, null, glblMsg.B, null);
                bizMsg.setMessageInfo(NWAM0100E);
                return;
            }
            settingLineNum = new BigDecimal(maxLineNum + 1);
            prntMdseIndex = lineStartIndex;
            // 2018/03/14 S21_NA#24117-1 add start
            if (copyCompare) {
                newCompareLine = (NWAL1500_USMsg) NWAL1500CommonLogicForConfigId.insertNewLine(compareLineList, insertRow - 1);
            
            }
            // 2018/03/14 S21_NA#24117-1 add end
        } else {
            newLine = glblMsg.B.no(prntMdseIndex); // 2018/01/30 S21_NA#19808 Mod
            settingLineNum = newLine.dsCpoLineNum_LL.getValue();
            if (!ZYPCommonFunc.hasValue(settingLineNum)) {
                settingLineNum = new BigDecimal(maxLineNum + 1);
            }
            // 2018/05/20 S21_NA#25604 Add Start
            if (ZYPCommonFunc.hasValue(newLine.ordLineStsDescTxt_LL)) {
                return;
            }
            // 2018/05/20 S21_NA#25604 Add End
        }

        // set up line
        if (!setItemInfoForParnt(newLine, bizMsg, glblMsg, confMsg, prntMdseCd, settingLineNum)) { // 2018/09/20 S21_NA#28199 Add glblMsg
            EZDMsg.copy(cloneCMsg.B, null, glblMsg.B, null);
            return;
        }
        addLineFlg = true;

        if (!addLineFlg) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(confMsg.xxSmryLineFlg_L, ZYPConstant.FLG_OFF_N);

        // 2018/03/14 S21_NA#24117-1 add start
        String tab = bizMsg.xxDplyTab.getValue();
        if (copyCompare) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_LINE_CONFIG);
        }
        // 2018/03/14 S21_NA#24117-1 add end

        deriveLinePrice(bizMsg, glblMsg, slctConfIndex, prntMdseIndex, copyCompare);
        deriveLineFloorPrice(bizMsg, glblMsg, slctConfIndex, prntMdseIndex);
        setLineRef(bizMsg, glblMsg, slctConfIndex); // 2018/01/30 S21_NA#19808 Mod

        // 2018/03/14 S21_NA#24117-1 add start
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, tab);

        if (copyCompare) {
            EZDMsg.copy(newLine, null, newCompareLine, null);
            if (glblMsg.A.getValidCount() == glblMsg.T.getValidCount()) {
                ZYPEZDItemValueSetter.setValue(glblMsg.T.no(slctConfIndex).mdlId_LC, glblMsg.A.no(slctConfIndex).mdlId_LC);
            }
        }
        // 2018/03/14 S21_NA#24117-1 add end
    }

    private static boolean setItemInfoForParnt(NWAL1500_BSMsg newLineP, NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg confMsg, String prntMdseCd, BigDecimal lineNum) { // 2018/01/30 S21_NA#19808 Mod 2018/09/20 S21_NA#28199 Add glblMsg

        // 2018/01/30 S21_NA#19808 Add Start
        NWAL1500_BCMsg newLine = new NWAL1500_BCMsg();
        // 2018/01/30 S21_NA#19808 Add End
        NWAL1500_BCMsg firstLine = null; // 2018/03/14 S21_NA#24117-1 add
        // Set Base Component Flag = 'N' for detected config
        String dsOrdPosnNum = confMsg.dsOrdPosnNum_LC.getValue();
        for (int n = 0; n < bizMsg.B.getValidCount(); n++) {
            if (!S21StringUtil.isEquals(dsOrdPosnNum, bizMsg.B.no(n).dsOrdPosnNum_LL.getValue())) {
                continue;
            }
            // 2018/03/14 S21_NA#24117-1 mod start
            //bizMsg.B.no(n).baseCmptFlg_LL.clear();
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(n).baseCmptFlg_LL, ZYPConstant.FLG_OFF_N);
            // 2018/03/14 S21_NA#24117-1 mod end
            bizMsg.B.no(n).dplyLineRefNum_LL.clear();
            // 2018/03/14 S21_NA#24117-1 add start
            if (bizMsg.B.no(n).dsCpoLineNum_LL.getValue().equals(BigDecimal.ONE)) {
                firstLine = bizMsg.B.no(n);
            }
            // 2018/03/14 S21_NA#24117-1 add end
        }
        MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), prntMdseCd);

        S21SsmEZDResult ssmResult = NWAL1500QueryForLineConfig.getInstance().getMdseInfo(bizMsg, mdseTMsg.mdseCd.getValue(), true);
        Map<String, String> mdseInfo = new HashMap<String, String>();
        if (!ssmResult.isCodeNotFound()) {
            mdseInfo = (Map<String, String>) ssmResult.getResultObject();
        }

        // Create Line
        ZYPEZDItemValueSetter.setValue(newLine.dsOrdPosnNum_LL, confMsg.dsOrdPosnNum_LC.getValue());
        ZYPEZDItemValueSetter.setValue(newLine.dsCpoLineNum_LL, lineNum);
        ZYPEZDItemValueSetter.setValue(newLine.xxLineNum_LL, NWAL1500CommonLogic.concatLineNum(newLine));
        ZYPEZDItemValueSetter.setValue(newLine.mdseCd_LL, prntMdseCd);
        ZYPEZDItemValueSetter.setValue(newLine.mdseNm_LL, mdseTMsg.mdseNm);
        ZYPEZDItemValueSetter.setValue(newLine.mdseDescShortTxt_LL, mdseInfo.get("MDSE_DESC_SHORT_TXT")); // S21_NA#2334 Add 
        NWAL1500CommonLogic.setUomPullDownForConfLine(bizMsg, newLine);
        ZYPEZDItemValueSetter.setValue(newLine.ordCustUomQty_LL, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(newLine.custUomCd_LL, mdseInfo.get("PKG_UOM_CD"));
        ZYPEZDItemValueSetter.setValue(newLine.ordQty_LL, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(newLine.entCpoDtlDealSlsAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.entDealNetUnitPrcAmt_LL, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(newLine.prcCatgCd_LL, bizMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(newLine.prcCatgNm_LL, bizMsg.prcCatgNm);
        // 2018/03/14 S21_NA#24117-1 add start
        if (firstLine != null) {
            ZYPEZDItemValueSetter.setValue(newLine.dsOrdLineCatgCd_LL, firstLine.dsOrdLineCatgCd_LL);
            ZYPEZDItemValueSetter.setValue(newLine.ordLineSrcCd_LL, firstLine.ordLineSrcCd_LL);
        }
        // 2018/03/14 S21_NA#24117-1 add end
//        ZYPEZDItemValueSetter.setValue(newLine.dsOrdLineCatgCd_LL, splyItemInfo.dsOrdLineCatgCd); TODO
        

        // Call NWZC1800 Default WH API
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.invtyCtrlFlg.getValue()) //
                && ZYPCommonFunc.hasValue(confMsg.shipToCustCd_LC)) {
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
        } else {
            newLine.rtlWhCd_LL.clear();
            newLine.rtlWhNm_LL.clear();
            newLine.rtlSwhCd_LL.clear();
            newLine.rtlSwhNm_LL.clear();
        }

        ZYPEZDItemValueSetter.setValue(newLine.supdLockFlg_LL, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(newLine.flPrcListCd_LL, bizMsg.flPrcListCd);
        ZYPEZDItemValueSetter.setValue(newLine.flPrcListNm_LL, bizMsg.flPrcListNm);
        ZYPEZDItemValueSetter.setValue(newLine.dealPrcListPrcAmt_LL, BigDecimal.ZERO);
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
        newLine.dplyLineRefNum_LL.clear();
        ZYPEZDItemValueSetter.setValue(newLine.baseCmptFlg_LL, ZYPConstant.FLG_ON_Y);
        // 2018/08/02 S21_NA#26665 add start
        CPO_SRC_TPTMsg cpoSrcTpTMsg = new CPO_SRC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoSrcTpTMsg.cpoSrcTpCd, CPO_SRC_TP.ORDER_ENTRY_SCREEN);

        cpoSrcTpTMsg = (CPO_SRC_TPTMsg) S21CodeTableAccessor.findByKey(cpoSrcTpTMsg);
        ZYPEZDItemValueSetter.setValue(newLine.cpoSrcTpDescTxt_LL, cpoSrcTpTMsg.cpoSrcTpDescTxt);
        // 2018/08/02 S21_NA#26665 add end

        // 2018/01/30 S21_NA#19808 Add Start
        EZDMsg.copy(newLine, null, newLineP, null);
        // 2018/01/30 S21_NA#19808 Add End
        return true;
    }
    // 2018/01/11 S21_NA#23329 Add End

    // 2018/03/20 S21_NA#24842 Add Start
    /**
     * <pre>
     * check added item has error, because the item is child merchandise of the model.
     * @param bizMsg Biz. Message
     * @param configMsg config message related lineMessage (Global Message)
     * @param lineMsg check target line message (Global Message)
     * @return true: has Error, false: no error
     * </pre>
     */
    public static boolean isAddItemHasErrorWithModel(NWAL1500CMsg bizMsg, NWAL1500_ASMsg configMsg, NWAL1500_BSMsg lineMsg) {

        boolean isConfigExists = NWAL1500CommonLogicForLineConfig.getConfigExist(bizMsg.glblCmpyCd.getValue(), configMsg.configTpCd_LC.getValue());

        SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = null;
        if (isConfigExists && ZYPCommonFunc.hasValue(configMsg.svcConfigMstrPk_LC)) {
            svcConfigMstrTMsg = NWAL1500CommonLogicForSaveSubmit.getSvcConfigData(bizMsg, configMsg.svcConfigMstrPk_LC.getValue());
        }
        BigDecimal mdlId = null;
        if (svcConfigMstrTMsg != null && isConfigExists) {
            mdlId = svcConfigMstrTMsg.mdlId.getValue();
        } else if (ZYPCommonFunc.hasValue(configMsg.mdlId_LC)) {
            mdlId = configMsg.mdlId_LC.getValue();
        }
        if (mdlId == null) {
            return false;
        }
        DS_MDLTMsg dsMdlTMsg = null;
        dsMdlTMsg = getDsMdl(bizMsg.glblCmpyCd.getValue(), mdlId);
        if (dsMdlTMsg == null) {
            return false;
        }
        if (!S21StringUtil.isEquals(SVC_MDL_TP.SOFTWARE, dsMdlTMsg.svcMdlTpCd.getValue())) {
            return false;
        }

        S21SsmEZDResult ssmRslt = NWAL1500QueryForLineConfig.getInstance().getMdlIdByChildMdseCd(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue(), bizMsg.slsDt.getValue());
        if (ssmRslt.isCodeNotFound()) {
            return false;
        }
        List<Map<String, BigDecimal>> rslt = (List<Map<String, BigDecimal>>) ssmRslt.getResultObject();

        if (rslt.size() > 1) {
            return true;
        }
        Map<String, BigDecimal> rsltMap = rslt.get(0);
        BigDecimal chkMdlId = rsltMap.get("MDL_ID");
        if (chkMdlId == null) {
            return false;
        }
        if (mdlId.compareTo(chkMdlId) == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * <pre>
     * get DS_MDL record from cache by primary key.
     * @param glblCmpyCd Global Company Code
     * @param mdlId Model ID
     * @return DS_MDLTMsg
     * </pre>
     */
    public static DS_MDLTMsg getDsMdl(String glblCmpyCd, BigDecimal mdlId) {

        DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.mdlId, mdlId);
        return (DS_MDLTMsg) EZDTBLAccessor.findByKey(dsMdlTMsg);
    }
    // 2018/03/20 S21_NA#24842 Add End
    // 2018/05/20 S21_NA#25604 Add Start
    /**
     * <pre>
     * Derive Base Component Flag and Model for detected Config (Line Config Tab)
     * @param bizMsg Busines Message
     * @param glblMsg Global Message
     * @param slctConfIndex confMsg number
     * </pre>
     */
    public static int deriveBaseComponentFlagAndModel(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, int slctConfIndex) {

        boolean isProcDelete = isProcDelete(bizMsg);

        NWAL1500_ASMsg confMsg = glblMsg.A.no(slctConfIndex);
        String dsOrdPosnNum = confMsg.dsOrdPosnNum_LC.getValue();
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
            if (S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue())) {
                lineMsg.baseCmptFlg_LL.setValue(ZYPConstant.FLG_OFF_N);
                // 2018/06/01 S21_NA#26334 Mod Start
                // lineMsg.dplyLineRefNum_LL.clear();
                if (!isProcDelete) {
                    lineMsg.dplyLineRefNum_LL.clear();
                }
                // 2018/06/01 S21_NA#26334 Mod End
            }
        }
        // 2018/05/24 S21_NA#25604-2 Logic Change
        NWXC150001SvcMdlFuncParamBean funcBean = deriveMdl(bizMsg, glblMsg, confMsg);

        // 2018/05/25 S21_NA#26352 Add Start
        if (funcBean == null) {
            return -1;
        }
        // 2018/05/25 S21_NA#26352 Add End

        if (!isProcDelete && funcBean.isSvcMdlTpSoftWare()) {
            setSoftModelParent(bizMsg, glblMsg, slctConfIndex, funcBean.getPrntMdseCd(), false);
        }
        String baseCmptMdseCd = funcBean.getBaseCmptMdseCd();
        //  2018/05/24 S21_NA#25604-2 Add Start
        if (!ZYPCommonFunc.hasValue(baseCmptMdseCd)) {
            return -1;
        }
        //  2018/05/24 S21_NA#25604-2 Add End
        int baseIdx = 0;
//        String dplyLineRefNum = ""; TODO

        // Mod Start 2019/03/29 QC#30849
        if (NWXC150001DsCheck.isReqBaseCmptFlgAtConfigTp(bizMsg.glblCmpyCd.getValue(), confMsg.configTpCd_LC.getValue())) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
                if (S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue()) //
                        && NWXC150001DsCheck.isNearEqualsItem(bizMsg.glblCmpyCd.getValue(), baseCmptMdseCd, lineMsg.mdseCd_LL.getValue())) {
                    lineMsg.baseCmptFlg_LL.setValue(ZYPConstant.FLG_ON_Y);
                    lineMsg.dplyLineRefNum_LL.clear();
//                dplyLineRefNum = NWAL1500CommonLogic.concatLineNum(lineMsg); TODO
                    baseIdx = i;
                    break;
                }
            }
        }
        // Mod End 2019/03/29 QC#30849
        // TODO Nead set Line Ref #? =>
//        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
//            if (i == baseIdx) {
//                continue;
//            }
//            NWAL1500_BSMsg lineMsg = glblMsg.B.no(i);
//            if (S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue())) {
//                lineMsg.baseCmptFlg_LL.setValue(ZYPConstant.FLG_OFF_N);
//                lineMsg.dplyLineRefNum_LL.setValue(dplyLineRefNum);
//            }
//        }
        // TODO Nead set Line Ref #? <=
        return baseIdx;
    }

    /**
     * <pre>
     * Derive Model using NWXC150001SvcMdlFunc.
     * @param bizMsg Busines Message
     * @param glblMsg Global Message
     * @param confMsg Config Message
     * @return result of deriving Model bean (NWXC150001SvcMdlFuncParamBean)
     * </pre>
     */
    public static NWXC150001SvcMdlFuncParamBean deriveMdl(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_ASMsg confMsg) {

        boolean isProcDelete = isProcDelete(bizMsg);

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        boolean isRetailEquipmentOrder = NWXC150001DsCheck.isAvalOrderCtxType( //
                glblCmpyCd, //
                ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS, //
                bizMsg.dsOrdCatgCd.getValue(), //
                bizMsg.dsOrdTpCd.getValue(), //
                bizMsg.dsOrdRsnCd.getValue());
        boolean isSvcExchangeOrder = NWXC150001DsCheck.isAvalOrderCtxType( //
                glblCmpyCd, //
                ORD_CATG_CTX_TP.SERVICE_EXCHANGE_ORDER_VALUE_SET, //
                bizMsg.dsOrdCatgCd.getValue(), //
                bizMsg.dsOrdTpCd.getValue(), //
                bizMsg.dsOrdRsnCd.getValue());
        boolean isNeadModel = isRetailEquipmentOrder || isSvcExchangeOrder;
        if (!isNeadModel) {
            setModelData(confMsg, null);
            return null;
        }

        boolean isNeadIbData = NWXC150001DsCheck.matchConfigTp( //
                glblCmpyCd, //
                confMsg.configTpCd_LC.getValue(), //
                CONFIG_CATG.OUTBOUND, //
                false, //
                true, //
                false);

        if (isNeadIbData && !ZYPCommonFunc.hasValue(confMsg.svcConfigMstrPk_LC)) {
            NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(glblMsg);
            confMsg.svcConfigMstrPk_LC.setErrorInfo(1, ZZM9000E, new String[] {itemNameList.getItemName(confMsg.svcConfigMstrPk_LC) });
            clearModelProperties(confMsg);
            return null;
        }
        NWXC150001SvcMdlFuncParamBean funcBean = new NWXC150001SvcMdlFuncParamBean();

        funcBean.setGlblCmpyCd(glblCmpyCd);
        funcBean.setSlsDt(bizMsg.slsDt.getValue());
        funcBean.setOnBatchType(ONBATCH_TYPE.ONLINE);

        if (isNeadIbData && ZYPCommonFunc.hasValue(confMsg.svcConfigMstrPk_LC)) {
            funcBean.setSvcConfigMstrPk(confMsg.svcConfigMstrPk_LC.getValue());
            BigDecimal svcConfigMstrPk = confMsg.svcConfigMstrPk_LC.getValue();

            // Add RMA Data parameter
            NWAL1500_CSMsg rmaConfMsg = null;
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                if (NWAL1500CommonLogic.compareBigDecimal(svcConfigMstrPk, glblMsg.C.no(i).svcConfigMstrPk_RC.getValue()) == 0) {
                    rmaConfMsg = glblMsg.C.no(i);
                    break;
                }
            }
            if (rmaConfMsg != null) {
                String dsOrdPosnNum = rmaConfMsg.dsOrdPosnNum_RC.getValue();
                for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                    NWAL1500_DSMsg rmaLineMsg = glblMsg.D.no(i);
                    if (S21StringUtil.isEquals(RTRN_LINE_STS.CANCELLED, rmaLineMsg.ordLineStsCd_RL.getValue())) {
                        continue;
                    }
                    if (S21StringUtil.isEquals(dsOrdPosnNum, rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
                        funcBean.addInBndChi(rmaLineMsg.mdseCd_RL.getValue(), rmaLineMsg.serNum_RL.getValue(), rmaLineMsg.svcMachMstrPk_RL.getValue());
                    }
                }
            }
        }
        String dsOrdPosnNum = confMsg.dsOrdPosnNum_LC.getValue();
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            if (S21StringUtil.isEquals(ORD_LINE_STS.CANCELLED, glblMsg.B.no(i).ordLineStsCd_LL.getValue())) {
                continue;
            }
            if (isProcDelete && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, glblMsg.B.no(i).xxChkBox_LL.getValue())) {
                continue;
            }
            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.B.no(i).dsOrdPosnNum_LL.getValue()) //
                    && ZYPCommonFunc.hasValue(glblMsg.B.no(i).mdseCd_LL)) {
                // 2019/11/21 S21_NA#54202 Mod Start
//                funcBean.addOutBndChildMdseCdList(glblMsg.B.no(i).mdseCd_LL.getValue());
                String mdseCd = checkSbstItemCd(bizMsg, glblMsg.B.no(i));
                if (mdseCd == null) {
                    return null;
                }
                funcBean.addOutBndChildMdseCdList(mdseCd);
                // 2019/11/21 S21_NA#54202 Mod End
            }
        }

        // 2018/05/24 S21_NA#25604-2 Add Start
        if (funcBean.getOutBndChildMdseCdList() == null //
                || funcBean.getOutBndChildMdseCdList().isEmpty()) {
            return funcBean;
        }
        // 2018/05/24 S21_NA#25604-2 Add End
        // Call Service Model API
        NWXC150001SvcMdlFunc.getModel(funcBean);
        setModelData(confMsg, funcBean);
        return funcBean;
    }

    /**
     * <pre>
     * Setup Model data base on NWXC150001SvcMdlFuncParamBean funcBean
     * @param confMsg Line Config Config message
     * @param funcBean NWXC150001SvcMdlFuncParamBean Model Data
     * </pre>
     */
    public static void setModelData(NWAL1500_ASMsg confMsg, NWXC150001SvcMdlFuncParamBean funcBean) {

        // 2018/05/25 S21_NA#26352 Add Start
        boolean doClear = false;
        if (funcBean == null) {
            doClear = true;
        }
        if (funcBean != null //
                && !ZYPCommonFunc.hasValue(funcBean.getMdlId())) {
            doClear = true;
        }
        if (funcBean != null //
                && (funcBean.getErrMsgIdList() != null && !funcBean.getErrMsgIdList().isEmpty())) {
            doClear = true;
        }
        // 2018/05/25 S21_NA#26352 Add End
        if (doClear) {
            clearModelProperties(confMsg);
            return;
        }
        ZYPEZDItemValueSetter.setValue(confMsg.mdlId_LC, funcBean.getMdlId());
        ZYPEZDItemValueSetter.setValue(confMsg.mdlDescTxt_LC, funcBean.getMdlNm());
        ZYPEZDItemValueSetter.setValue(confMsg.mdlNm_LC, funcBean.getMdlNm());
        ZYPEZDItemValueSetter.setValue(confMsg.svcMdlTpCd_LC, funcBean.getSvcMdlTpCd());
    }
    public static void setModelData(NWAL1500_CSMsg rmsConfMsg, NWXC150001SvcMdlFuncParamBean funcBean) {

        // 2018/05/25 S21_NA#26352 Add Start
        boolean doClear = false;
        if (funcBean == null) {
            doClear = true;
        }
        if (funcBean != null //
                && !ZYPCommonFunc.hasValue(funcBean.getMdlId())) {
            doClear = true;
        }
        if (funcBean != null //
                && (funcBean.getErrMsgIdList() != null && !funcBean.getErrMsgIdList().isEmpty())) {
            doClear = true;
        }
        // 2018/05/25 S21_NA#26352 Add End
        if (doClear) {
            clearModelProperties(rmsConfMsg);
            return;
        }
        ZYPEZDItemValueSetter.setValue(rmsConfMsg.mdlId_RC, funcBean.getMdlId());
        ZYPEZDItemValueSetter.setValue(rmsConfMsg.mdlDescTxt_RC, funcBean.getMdlNm());
        ZYPEZDItemValueSetter.setValue(rmsConfMsg.mdlNm_RC, funcBean.getMdlNm());
        ZYPEZDItemValueSetter.setValue(rmsConfMsg.svcMdlTpCd_RC, funcBean.getSvcMdlTpCd());
    }
    /**
     * <pre>
     * set Base Component flag for RMA line.
     * @param bizMsg Business Message
     * @param slctConfIndex selected Config for RMA index
     * @param baseComponentMap Base Component Map
     * @param delFlg true: delete Mode false: ordinal Mode
     * </pre>
     */
    public static NWXC150001SvcMdlFuncParamBean deriveMdl(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_CSMsg rmaConfMsg) {

        boolean isProcDelete = isProcDelete(bizMsg);

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        boolean isRetailEquipmentOrder = NWXC150001DsCheck.isAvalOrderCtxType( //
                glblCmpyCd, //
                ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS, //
                bizMsg.dsOrdCatgCd.getValue(), //
                bizMsg.dsOrdTpCd.getValue(), //
                bizMsg.dsOrdRsnCd.getValue());
        boolean isSvcExchangeOrder = NWXC150001DsCheck.isAvalOrderCtxType( //
                glblCmpyCd, //
                ORD_CATG_CTX_TP.SERVICE_EXCHANGE_ORDER_VALUE_SET, //
                bizMsg.dsOrdCatgCd.getValue(), //
                bizMsg.dsOrdTpCd.getValue(), //
                bizMsg.dsOrdRsnCd.getValue());
        boolean isNeadModel = isRetailEquipmentOrder || isSvcExchangeOrder;
        if (!isNeadModel) {
            setModelData(rmaConfMsg, null);
            return null;
        }

        boolean isNeadIbData = NWXC150001DsCheck.matchConfigTp( //
                glblCmpyCd, //
                rmaConfMsg.configTpCd_RC.getValue(), //
                CONFIG_CATG.INBOUND, //
                false, //
                true, //
                false);

        if (isNeadIbData && !ZYPCommonFunc.hasValue(rmaConfMsg.svcConfigMstrPk_RC)) {
            NWAL1500ItemNameList itemNameList = new NWAL1500ItemNameList(glblMsg);
            rmaConfMsg.svcConfigMstrPk_RC.setErrorInfo(1, ZZM9000E, new String[] {itemNameList.getItemName(rmaConfMsg.svcConfigMstrPk_RC) });
            clearModelProperties(rmaConfMsg);
            return null;
        }
        NWXC150001SvcMdlFuncParamBean funcBean = new NWXC150001SvcMdlFuncParamBean();

        funcBean.setGlblCmpyCd(glblCmpyCd);
        funcBean.setSlsDt(bizMsg.slsDt.getValue());
        funcBean.setOnBatchType(ONBATCH_TYPE.ONLINE);

        if (isNeadIbData && ZYPCommonFunc.hasValue(rmaConfMsg.svcConfigMstrPk_RC)) {
            funcBean.setSvcConfigMstrPk(rmaConfMsg.svcConfigMstrPk_RC.getValue());
        }
        String dsOrdPosnNum = rmaConfMsg.dsOrdPosnNum_RC.getValue();
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            if (S21StringUtil.isEquals(ORD_LINE_STS.CANCELLED, glblMsg.D.no(i).ordLineStsCd_RL.getValue())) {
                continue;
            }
            if (isProcDelete && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, glblMsg.D.no(i).xxChkBox_RL.getValue())) {
                continue;
            }
            if (S21StringUtil.isEquals(dsOrdPosnNum, glblMsg.D.no(i).dsOrdPosnNum_RL.getValue()) //
                    && ZYPCommonFunc.hasValue(glblMsg.D.no(i).mdseCd_RL)) {
                funcBean.addOutBndChildMdseCdList(glblMsg.D.no(i).mdseCd_RL.getValue());
            }
        }

        // Call Service Model API
        NWXC150001SvcMdlFunc.getModel(funcBean);
        setModelData(rmaConfMsg, funcBean);
        return funcBean;
    }

    /**
     * <pre>
     * check this process is deleteing or not.
     * @param bizMsg Business message
     * @return true: Delete Process, false: not Delete Proces
     * </pre>
     */
    public static boolean isProcDelete(NWAL1500CMsg bizMsg) {

        return S21StringUtil.isEquals(ORD_ENTRY_ACT.DELETE, bizMsg.ordEntryActCd.getValue());
    }
    
    // 2018/09/11 S21_NA#25352 Add Start
    public static boolean isProcCancel(NWAL1500CMsg bizMsg) {

        return S21StringUtil.isEquals(ORD_ENTRY_ACT.CANCEL_2, bizMsg.ordEntryActCd.getValue());
    }
    // 2018/09/11 S21_NA#25352 Add End

    // 2018/05/20 S21_NA#25604 Add End
    // 2018/07/03 S21_NA#26908 Add Start
    public static DS_IMPT_ORD_DTLTMsg getImptOrdDtl(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg) {

        BigDecimal dsImptOrdDtlPk = NWAL1500QueryForLineConfig.getInstance().getImptOrdDtlPk(
                bizMsg.glblCmpyCd.getValue(),
                bizMsg.dsImptOrdPk.getValue(),
                lineMsg.dsOrdPosnNum_LL.getValue(),
                lineMsg.ordSrcRefLineNum_LL.getValue(),
                lineMsg.ordSrcRefLineSubNum_LL.getValue());

        if (ZYPCommonFunc.hasValue(dsImptOrdDtlPk)) {

            DS_IMPT_ORD_DTLTMsg imptOrdDtlTMsg = new DS_IMPT_ORD_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(imptOrdDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(imptOrdDtlTMsg.dsImptOrdDtlPk, dsImptOrdDtlPk);

            return (DS_IMPT_ORD_DTLTMsg) S21FastTBLAccessor.findByKey(imptOrdDtlTMsg);
        }
        
        return null;
    }
    // 2018/07/03 S21_NA#26908 Add End

    // 2018/08/21 S21_NA#26767 Add Start
    /**
     * <pre>
     * Set up return reason pull down.
     * @param bizMsg NWAL1500 Biz Message.
     * <pre>
     */
    public static void setReturnReason(NWAL1500CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.glblCmpyCd) //
                || !ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            return;
        }
        NWXC150001CommonReturnReason.getRelatedReturnReasonPulldown( //
                bizMsg.glblCmpyCd.getValue(), //
                bizMsg.dsOrdTpCd.getValue(), //
                bizMsg.rtrnRsnCd_CD, //
                bizMsg.rtrnRsnDescTxt_NM);
    }

    /**
     * <pre>
     * get related to order reason config typ list.
     * @param bizMsg Business Message
     * </pre>
     */
    public static void setConfigType(NWAL1500CMsg bizMsg) {

        bizMsg.configTpCd_LD.clear();
        bizMsg.configTpDescTxt_LD.clear();
        bizMsg.configTpCd_RD.clear();
        bizMsg.configTpDescTxt_RD.clear();

        List<Map<String, String>> configTpMapList = NWXC150001CommonReturnReason.getRelatedConfigTpList(//
                bizMsg.glblCmpyCd.getValue(), //
                bizMsg.cpoSrcTpCd.getValue(), //
                bizMsg.dsOrdTpCd.getValue(), //
                null);

        if (configTpMapList == null || configTpMapList.isEmpty()) {
            return;
        }

        int outboundIndex = 0;
        int inboundIndex = 0;

        for (int n = 0; n < configTpMapList.size(); n++) {
            Map<String, String> rsltMap = configTpMapList.get(n);
            String configCatgCd = (String) rsltMap.get("CONFIG_CATG_CD");
            if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
                if (outboundIndex < bizMsg.configTpCd_LD.length()) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.configTpCd_LD.no(outboundIndex), (String) rsltMap.get("CONFIG_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.configTpDescTxt_LD.no(outboundIndex), (String) rsltMap.get("CONFIG_TP_DESC_TXT"));
                    outboundIndex++;
                }
            } else {
                if (outboundIndex < bizMsg.configTpCd_RD.length()) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.configTpCd_RD.no(inboundIndex), (String) rsltMap.get("CONFIG_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.configTpDescTxt_RD.no(inboundIndex), (String) rsltMap.get("CONFIG_TP_DESC_TXT"));
                    inboundIndex++;
                }
            }
        }
    }

    /**
     * <pre>
     * Defaulting Line Category.
     * @param bizMsg Business Message
     * @param confMsg Config Message (NWAL1500_ACMsg or NWAL1500_ASMsg)
     * @param lineMsg Line Message (NWAL1500_BCMsg or NWAL1500_BSMsg
     * @return true: set default line category code, false: not set line category code
     */
    public static boolean defaultingLineCateg(NWAL1500CMsg bizMsg, EZDMsg confMsg, EZDMsg lineMsg) {

        NWAL1500_ASMsg glblConfMsg = null;
        NWAL1500_ACMsg bizConfMsg = null;

        String configTpCd = null;
        if (confMsg instanceof NWAL1500_ASMsg) {
            glblConfMsg = (NWAL1500_ASMsg) confMsg;
            configTpCd = glblConfMsg.configTpCd_LC.getValue();
        } else if (confMsg instanceof NWAL1500_ACMsg) {
            bizConfMsg = (NWAL1500_ACMsg) confMsg;
            configTpCd = bizConfMsg.configTpCd_LC.getValue();
        }

        if (!ZYPCommonFunc.hasValue(configTpCd)) {
            return false;
        }

        if (!isConversionConfigTp(bizMsg.glblCmpyCd.getValue(), configTpCd)) {
            return false;
        }

        NWAL1500_BSMsg glblLineMsg = null;
        NWAL1500_BCMsg bizLineMsg = null;
        if (lineMsg instanceof NWAL1500_BSMsg) {
            glblLineMsg = (NWAL1500_BSMsg) lineMsg;
        } else if (lineMsg instanceof NWAL1500_BCMsg) {
            bizLineMsg = (NWAL1500_BCMsg) lineMsg;
        } else {
            return false;
        }
        if (glblLineMsg != null && (!ZYPCommonFunc.hasValue(glblLineMsg.mdseCd_LL) || !ZYPCommonFunc.hasValue(glblLineMsg.ordCustUomQty_LL))) {
            return false;
        }
        if (bizLineMsg != null && (!ZYPCommonFunc.hasValue(bizLineMsg.mdseCd_LL) || !ZYPCommonFunc.hasValue(bizLineMsg.ordCustUomQty_LL))) {
            return false;
        }

        // Get Default Line Category
        BigDecimal svcMachMstrPk = null;
        if (glblLineMsg != null) {
            if (ZYPCommonFunc.hasValue(glblLineMsg.svcMachMstrPk_LL)) {
                svcMachMstrPk = glblLineMsg.svcMachMstrPk_LL.getValue();
            } else {
                return false;
            }
        }
        if (bizLineMsg != null) {
            if (ZYPCommonFunc.hasValue(bizLineMsg.svcMachMstrPk_LL)) {
                svcMachMstrPk = bizLineMsg.svcMachMstrPk_LL.getValue();
            } else {
                return false;
            }
        }
        // Get Default Return Reason By IB.
        String lineCatgCd = NWXC150001CommonReturnReason.getDefaultLineCatgIB(bizMsg.glblCmpyCd.getValue(), svcMachMstrPk);
        if (!ZYPCommonFunc.hasValue(lineCatgCd)) {
            return false;
        } else {
            if (glblLineMsg != null) {
                glblLineMsg.dsOrdLineCatgCd_LL.setValue(lineCatgCd);
                return true;
            }
            if (bizLineMsg != null) {
                bizLineMsg.dsOrdLineCatgCd_LL.setValue(lineCatgCd);
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * Set defaulting return reason code.
     * @param bizMsg Busines Message
     * @param rmaLineMsg RMA Line Message (Global)
     * @return true: set default return reason code. false: not set
     */
    public static boolean defaultingRtrnRsn(NWAL1500CMsg bizMsg, EZDMsg rmaLineMsg) {

        NWAL1500_DSMsg glblRmaLineMsg = null;
        NWAL1500_DCMsg bizRmaLineMsg = null;
        if (rmaLineMsg instanceof NWAL1500_DSMsg) {
            glblRmaLineMsg = (NWAL1500_DSMsg) rmaLineMsg;
        } else if (rmaLineMsg instanceof NWAL1500_DCMsg) {
            bizRmaLineMsg = (NWAL1500_DCMsg) rmaLineMsg;
        } else {
            return false;
        }
        if (glblRmaLineMsg != null && (!ZYPCommonFunc.hasValue(glblRmaLineMsg.mdseCd_RL) || !ZYPCommonFunc.hasValue(glblRmaLineMsg.ordCustUomQty_RL))) {
            return false;
        }
        if (bizRmaLineMsg != null && (!ZYPCommonFunc.hasValue(bizRmaLineMsg.mdseCd_RL) || !ZYPCommonFunc.hasValue(bizRmaLineMsg.ordCustUomQty_RL))) {
            return false;
        }

        // Get Default Return Reason
        int rsnCnt = 0;
        String rtrnRsnCd = "";
        for (int i = 0; i < bizMsg.rtrnRsnCd_CD.length(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.rtrnRsnCd_CD.no(i))) {
                rtrnRsnCd = bizMsg.rtrnRsnCd_CD.no(i).getValue();
                rsnCnt++;
            }
        }
        if (rsnCnt == 1) {
            if (glblRmaLineMsg != null) {
                glblRmaLineMsg.rtrnRsnCd_RL.setValue(rtrnRsnCd);
            } else if (bizRmaLineMsg != null) {
                bizRmaLineMsg.rtrnRsnCd_RL.setValue(rtrnRsnCd);
            }
            return true;
        }
        BigDecimal svcMachMstrPk = null;
        if (glblRmaLineMsg != null) {
            if (ZYPCommonFunc.hasValue(glblRmaLineMsg.svcMachMstrPk_RL)) {
                svcMachMstrPk = glblRmaLineMsg.svcMachMstrPk_RL.getValue();
            } else {
                return false;
            }
        }
        if (bizRmaLineMsg != null) {
            if (ZYPCommonFunc.hasValue(bizRmaLineMsg.svcMachMstrPk_RL)) {
                svcMachMstrPk = bizRmaLineMsg.svcMachMstrPk_RL.getValue();
            } else {
                return false;
            }
        }
        // Get Default Return Reason By IB.
        rtrnRsnCd = NWXC150001CommonReturnReason.getDefaultRtrnRsnByIB(bizMsg.glblCmpyCd.getValue(), svcMachMstrPk);
        if (!ZYPCommonFunc.hasValue(rtrnRsnCd)) {
            return false;
        } else {
            if (glblRmaLineMsg != null) {
                glblRmaLineMsg.rtrnRsnCd_RL.setValue(rtrnRsnCd);
                return true;
            }
            if (bizRmaLineMsg != null) {
                bizRmaLineMsg.rtrnRsnCd_RL.setValue(rtrnRsnCd);
                return true;
            }
        }
        return false;
    }

    public static boolean setDefaultLineCategoryForNonSerial(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_BSMsg lineMsg) {

        if (!ZYPCommonFunc.hasValue(lineMsg.mdseCd_LL) //
                || !ZYPCommonFunc.hasValue(lineMsg.ordCustUomQty_LL)) {
            return false;
        }
        MDSETMsg mdseTMsg =  NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue());
        if (mdseTMsg == null) {
            return false;
        }
        NWAL1500_ASMsg configMsg = NWAL1500CommonLogic.getParentConfig(glblMsg.A, lineMsg.dsOrdPosnNum_LL.getValue());
        if (configMsg == null) {
            return false;
        }
        if (!NWAL1500CommonLogicForLineConfig.isConversionConfigTp(bizMsg.glblCmpyCd.getValue(), configMsg.configTpCd_LC.getValue())) {
            lineMsg.svcMachMstrPk_LL.clear();
            return false;
        }
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.shpgSerTakeFlg.getValue())) {
            return false;
        }
        setNonSerialSvcMachMstrPkForLineConfig(bizMsg, glblMsg, lineMsg);
        if (ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_LL)) {
            NWAL1500_ASMsg confMsg = NWAL1500CommonLogic.getParentConfig(glblMsg.A, lineMsg.dsOrdPosnNum_LL.getValue());
            boolean defaulting = defaultingLineCateg(bizMsg, confMsg, lineMsg);
            if (defaulting) {
                String rtlWhCd = ZYPCodeDataUtil.getVarCharConstValue(NWAL1500_RENTAL_CONV_DEFWH, bizMsg.glblCmpyCd.getValue());
                if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                    ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhCd_LL, rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(lineMsg.rtlWhNm_LL, NWAL1500CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
                    lineMsg.rtlSwhCd_LL.clear();
                    lineMsg.rtlSwhNm_LL.clear();
                }
            }
            return defaulting;
        } else {
            return false;
        }
    }

    /**
     * <pre>
     * Set Default Return Reason for Non Serial Line.
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     * @param rmaLineMsg RMA Lime Message
     * </pre>
     */
    public static boolean setDefaultReturnReasonForNonSerial(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_DSMsg rmaLineMsg) {

        if (!ZYPCommonFunc.hasValue(rmaLineMsg.mdseCd_RL) //
                || !ZYPCommonFunc.hasValue(rmaLineMsg.ordCustUomQty_RL)) {
            return false;
        }
        MDSETMsg mdseTMsg =  NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), rmaLineMsg.mdseCd_RL.getValue());
        if (mdseTMsg == null) {
            return false;
        }
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.shpgSerTakeFlg.getValue())) {
            return false;
        }
        setNonSerialSvcMachMstrPkForRmaLine(bizMsg, glblMsg, rmaLineMsg);
        if (ZYPCommonFunc.hasValue(rmaLineMsg.svcMachMstrPk_RL)) {
            return defaultingRtrnRsn(bizMsg, rmaLineMsg);
        } else {
            return false;
        }
    }

    private static void setNonSerialSvcMachMstrPkForLineConfig(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_BSMsg lineMsg) {

        if (ZYPCommonFunc.hasValue(lineMsg.serNum_LL) //
                || ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_LL)) {
            return;
        }
        String dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
        NWAL1500_ASMsg confMsg = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.A, dsOrdPosnNum);
        if (!ZYPCommonFunc.hasValue(confMsg.svcConfigMstrPk_LC)) {
            return;
        }
        BigDecimal svcConfigMstrPk = confMsg.svcConfigMstrPk_LC.getValue();

        List<BigDecimal> svcMachMstrList = NWXC150001CommonReturnReason.getSvcMachMstrPkByConfigIdAndMdseCd(//
                bizMsg.glblCmpyCd.getValue(), //
                bizMsg.slsDt.getValue(), //
                svcConfigMstrPk, //
                lineMsg.mdseCd_LL.getValue());
        if (svcMachMstrList == null || svcMachMstrList.isEmpty()) {
            return;
        }
        BigDecimal avalSvcMachMstrPk = null;
        for(BigDecimal svcMachMstrPk : svcMachMstrList) {
            boolean avalFlg = true;
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                NWAL1500_BSMsg lineMsgLocal = glblMsg.B.no(i);
                if (!S21StringUtil.isEquals(dsOrdPosnNum, lineMsgLocal.dsOrdPosnNum_LL.getValue())) {
                    continue;
                }
                if (ZYPCommonFunc.hasValue(lineMsgLocal.svcMachMstrPk_LL) //
                        && svcMachMstrPk.compareTo(lineMsgLocal.svcMachMstrPk_LL.getValue()) == 0) {
                    avalFlg = false;
                    break;
                }
            }
            if (avalFlg) {
                avalSvcMachMstrPk = svcMachMstrPk;
                break;
            }
        }
        if (avalSvcMachMstrPk == null) {
            return;
        }
        lineMsg.svcMachMstrPk_LL.setValue(avalSvcMachMstrPk);
    }

    private static void setNonSerialSvcMachMstrPkForRmaLine(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, NWAL1500_DSMsg rmaLineMsg) {

        if (ZYPCommonFunc.hasValue(rmaLineMsg.serNum_RL) //
                || ZYPCommonFunc.hasValue(rmaLineMsg.svcMachMstrPk_RL)) {
            return;
        }
        String dsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
        NWAL1500_CSMsg rmaConfMsg = NWAL1500CommonLogicForPagination.getParentConfigFromGlobal(glblMsg.C, dsOrdPosnNum);
        if (!ZYPCommonFunc.hasValue(rmaConfMsg.svcConfigMstrPk_RC)) {
            return;
        }
        BigDecimal svcConfigMstrPk = rmaConfMsg.svcConfigMstrPk_RC.getValue();

        List<BigDecimal> svcMachMstrList = NWXC150001CommonReturnReason.getSvcMachMstrPkByConfigIdAndMdseCd(//
                bizMsg.glblCmpyCd.getValue(), //
                bizMsg.slsDt.getValue(), //
                svcConfigMstrPk, //
                rmaLineMsg.mdseCd_RL.getValue());
        if (svcMachMstrList == null || svcMachMstrList.isEmpty()) {
            return;
        }
        BigDecimal avalSvcMachMstrPk = null;
        for(BigDecimal svcMachMstrPk : svcMachMstrList) {
            boolean avalFlg = true;
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                NWAL1500_DSMsg rmaLineMsgLocal = glblMsg.D.no(i);
                if (!S21StringUtil.isEquals(dsOrdPosnNum, rmaLineMsgLocal.dsOrdPosnNum_RL.getValue())) {
                    continue;
                }
                if (ZYPCommonFunc.hasValue(rmaLineMsgLocal.svcMachMstrPk_RL) //
                        && svcMachMstrPk.compareTo(rmaLineMsgLocal.svcMachMstrPk_RL.getValue()) == 0) {
                    avalFlg = false;
                    break;
                }
            }
            if (avalFlg) {
                avalSvcMachMstrPk = svcMachMstrPk;
                break;
            }
        }
        if (avalSvcMachMstrPk == null) {
            return;
        }
        rmaLineMsg.svcMachMstrPk_RL.setValue(avalSvcMachMstrPk);
    }

    /**
     * <pre>
     * Check Config Type as Conversion or not from VarcharConst "NWA_CONV_CONFIG_TP".
     * @param glblCmpyCd Global Company Code
     * @param configTpCd Config Type Code
     * @return true: Conversion false: not Conversion
     * </pre>
     */
    public static boolean isConversionConfigTp(String glblCmpyCd, String configTpCd) {

        String convConfTpConst = ZYPCodeDataUtil.getVarCharConstValue(NWA_CONV_CONFIG_TP, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(convConfTpConst)) {
            return false;
        }
        String[] convConfTpArray = convConfTpConst.split("\\,");
        List<String> convConfTpList = new ArrayList<String>(convConfTpArray.length);
        for (String convConfTp : convConfTpArray) {
            convConfTpList.add(convConfTp);
        }
        if (!convConfTpList.contains(configTpCd)) {
            return false;
        }
        return true;
    }
    // 2018/08/21 S21_NA#26767 Add End

    // 2019/11/21 S21_NA#54202 Add Start
    /**
     * <pre>
     * Return Substitute Item Code (if the line has it) or Item Code (if the line doesn't have Subsitute Item).
     * 
     * @param bizMsg
     * @param lineMsg
     * @return
     * </pre>
     */
    private static String checkSbstItemCd(NWAL1500CMsg bizMsg, NWAL1500_BSMsg lineMsg) {

        if (ZYPCommonFunc.hasValue(lineMsg.sbstMdseCd_LL)) {
            List<String> sbstMdseCdList = (List<String>) NWXC150001DsCheck.getSbstRelation(bizMsg.glblCmpyCd.getValue(), //
                    lineMsg.mdseCd_LL.getValue(), //
                    lineMsg.sbstMdseCd_LL.getValue());
            // 2016/07/28 S21_NA#1698,3235 Mode Start
            if (sbstMdseCdList == null || sbstMdseCdList.size() == 0) {
                lineMsg.sbstMdseCd_LL.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_SBST_ITEM });
                return null;
            }
            return lineMsg.sbstMdseCd_LL.getValue();
        } else {
            return lineMsg.mdseCd_LL.getValue();
        }
    }
    // 2019/11/21 S21_NA#54202 Add End
}
