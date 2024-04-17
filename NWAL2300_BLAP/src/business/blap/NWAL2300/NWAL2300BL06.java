/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2300;

import static business.blap.NWAL2300.constant.NWAL2300Constant.BIZ_ID;
import static business.blap.NWAL2300.constant.NWAL2300Constant.DEF_LINE_SUB_NUM;
import static business.blap.NWAL2300.constant.NWAL2300Constant.DROP_SHIP_WH;
import static business.blap.NWAL2300.constant.NWAL2300Constant.LINE_SEPARATOR;
import static business.blap.NWAL2300.constant.NWAL2300Constant.MODE_CANCEL;
import static business.blap.NWAL2300.constant.NWAL2300Constant.MODE_SAVE;
import static business.blap.NWAL2300.constant.NWAL2300Constant.MODE_SUBMIT;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM0790E;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM0809E;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM0944E;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWZM0073E;
import static business.blap.NWAL2300.constant.NWAL2300Constant.PULLDOWN_CD_C_R;
import static business.blap.NWAL2300.constant.NWAL2300Constant.PULLDOWN_CD_RMA_RB;
import static business.blap.NWAL2300.constant.NWAL2300Constant.REASON_CD_I;
import static business.blap.NWAL2300.constant.NWAL2300Constant.RQST_TP_CONFIG_MODIFY;
import static business.blap.NWAL2300.constant.NWAL2300Constant.RQST_TP_CONFIG_NEW;
import static business.blap.NWAL2300.constant.NWAL2300Constant.RQST_TP_DTL_CANCEL;
import static business.blap.NWAL2300.constant.NWAL2300Constant.RQST_TP_DTL_NEW;
import static business.blap.NWAL2300.constant.NWAL2300Constant.RQST_TP_SLS_CR_MODIFY;
import static business.blap.NWAL2300.constant.NWAL2300Constant.RQST_TP_SLS_CR_NEW;
import static business.blap.NWAL2300.constant.NWAL2300Constant.SHIP_FROM_INVTY_LOC_CD_CR;
import static business.blap.NWAL2300.constant.NWAL2300Constant.VARCHAR_CONST_NWAL2300_PMT_TERM_CC_CR;
import static business.blap.NWAL2300.constant.NWAL2300Constant.VARCHAR_CONST_NWAL2300_PMT_TERM_CC_RB;
import static business.blap.NWAL2300.constant.NWAL2300Constant.VARCHAR_CONST_NWAL2300_PMT_TERM_EX_CC_CR;
import static business.blap.NWAL2300.constant.NWAL2300Constant.VARCHAR_CONST_NWAL2300_RB_INTERNAL;
import static business.blap.NWAL2300.constant.NWAL2300Constant.VARCHAR_CONST_PKG_UOM_FOR_PRC;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_CTAC_PSN_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_DELY_INFO_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_INSTL_INFO_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_SITE_SRVY_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.MODE_CREDIT;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2300.common.NWAL2300CommonLogic;
import business.db.CCYTMsg;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_RTRN_PRC_CALC_BASETMsg;
import business.db.CPO_RTRN_PRC_CALC_BASETMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_CPO_CR_REBIL_RSNTMsg;
import business.db.DS_CPO_CTAC_PSNTMsg;
import business.db.DS_CPO_CTAC_PSNTMsgArray;
import business.db.DS_CPO_DELY_INFOTMsg;
import business.db.DS_CPO_DELY_INFOTMsgArray;
import business.db.DS_CPO_ISTL_INFOTMsg;
import business.db.DS_CPO_ISTL_INFOTMsgArray;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_CPO_SLS_CRTMsg;
import business.db.DS_CPO_SLS_CRTMsgArray;
import business.db.DS_INV_SLS_CRTMsg;
import business.db.DS_INV_SLS_CRTMsgArray;
import business.db.DS_SITE_SRVYTMsg;
import business.db.DS_SITE_SRVYTMsgArray;
import business.db.INVTMsg;
import business.db.INVTMsgArray;
import business.db.INV_BOLTMsg;
import business.db.INV_BOLTMsgArray;
import business.db.INV_LINETMsg;
import business.db.MSG_TXT_DTLTMsg;
import business.db.MSG_TXT_DTLTMsgArray;
import business.db.ORD_PRC_CALC_BASETMsg;
import business.db.ORD_PRC_CALC_BASETMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.parts.NMZC611001PMsg;
import business.parts.NSZC115001PMsg;
import business.parts.NSZC115001_svcAddlBasePrcListPMsg;
import business.parts.NSZC115001_svcAddlChrgPrcListPMsg;
import business.parts.NSZC115001_svcConfigRefListPMsg;
import business.parts.NSZC115001_svcDtlListPMsg;
import business.parts.NSZC115001_svcPrcListPMsg;
import business.parts.NSZC115001_svcUsgPrcListPMsg;
import business.parts.NWZC042001PMsg;
import business.parts.NWZC042001_APMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_cpoCtacPsnInfoListPMsg;
import business.parts.NWZC150001_cpoDlvyInfoListPMsg;
import business.parts.NWZC150001_cpoIstlInfoListPMsg;
import business.parts.NWZC150001_cpoSlsCrPMsg;
import business.parts.NWZC150001_priceListPMsg;
import business.parts.NWZC150001_rtnDtlPMsg;
import business.parts.NWZC150001_rtnPriceListPMsg;
import business.parts.NWZC150001_siteSrvInfoListPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;
import business.parts.NWZC156001PMsg;
import business.parts.NWZC156002PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC611001.NMZC611001;
import com.canon.cusa.s21.api.NSZ.NSZC115001.constant.NSZC115001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC042001.NWZC042001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC156001.NWZC156001;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001SalesRep;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_SPL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TXT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDown;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownParameter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL2300BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         H.Ikeda         Create          N/A
 * 2016/08/31   Fujitsu         Y.Taoka         Update          QC#12892
 * 2016/09/02   Fujitsu         Y.Taoka         Update          QC#12875
 * 2016/09/21   Fujitsu         Y.Taoka         Update          QC#14715
 * 2016/10/07   Fujitsu         S.Iidaka        Update          QC#15028
 * 2016/12/27   Fujitsu         Y.Kanefusa      Update          S21_NA#16568
 * 2017/01/11   Fujitsu         N.Sugiura       Update          QC#16669
 * 2017/01/17   Fujitsu         N.Aoyama        Update          QC#16691
 * 2017/01/31   Fujitsu         H.Nagashima     Update          QC#15624
 * 2017/02/07   Fujitsu         M.Ohno          Update          QC#17405
 * 2017/02/21   Fujitsu         M.Ohno          Update          QC#17416
 * 2017/02/22   Fujitsu         M.Ohno          Update          QC#17683
 * 2017/03/07   Fujitsu         H.Nagashima     Update          QC#17874
 * 2017/05/12   Fujitsu         N.Sugiura       Update          RS#8348,8349
 * 2017/06/30   Fujitsu         S.Iidaka        Update          QC#19711
 * 2017/07/04   Fujitsu         K.Ishizuka      Update          QC#19674
 * 2017/07/05   Fujitsu         S.Iidaka        Update          QC#19718
 * 2017/07/14   Fujitsu         S.Iidaka        Update          QC#19925,19943
 * 2017/08/15   Fujitsu         N.Sugiura       Update          QC#16452
 * 2017/09/25   Fujitsu         S.Iidaka        Update          QC#20511
 * 2017/10/04   Fujitsu         M.Yamada        Update          QC#21521
 * 2017/10/16   Fujitsu         R.Nakamura      Update          QC#21729
 * 2017/10/16   Fujitsu         R.Nakamura      Update          QC#21592
 * 2017/10/18   Fujitsu         T.Aoi           Update          QC#21730
 * 2017/10/19   Fujitsu         S.Takami        Update          QC#21708
 * 2017/10/19   Fujitsu         H.ikeda         Update          QC#21723
 * 2017/10/20   Fujitsu         A.Kosai         Update          QC#21780
 * 2017/10/20   Fujitsu         H.Sugawara      Update          QC#21773
 * 2017/10/24   Fujitsu         Mz.Takahashi    Update          L3#430 QC#16437
 * 2017/10/27   Fujitsu         R.Nakamura      Update          QC#22125
 * 2017/10/27   Fujitsu         K.Ishizuka      Update          QC#22118
 * 2017/10/31   Fujitsu         R.Nakamura      Update          QC#22197
 * 2017/12/05   Fujitsu         M.Yamada        Update          QC#22337
 * 2017/12/12   Fujitsu         Mz.Takahashi    Update          QC#22969
 * 2017/12/25   Fujitsu         S.Takami        Update          QC#23081
 * 2018/01/16   Fujitsu         K.Ishizuka      Update          QC#23237
 * 2018/01/22   Fujitsu         K.Ishizuka      Update          QC#23321
 * 2018/04/06   Fujitsu         T.Aoi           Update          QC#22122
 * 2018/04/25   Fujitsu         T.Aoi           Update          QC#22122-1
 * 2018/05/09   Fujitsu         S.Takami        Update          QC#25030
 * 2018/05/25   Fujitsu         T.Noguchi       Update          QC#25860
 * 2018/05/29   Fujitsu         S.Takami        Update          QC#21841
 * 2018/06/04   Fujitsu         S.Takami        Update          QC#26479
 * 2018/06/27   Fujitsu         S.Takami        Update          QC#26578
 * 2018/07/31   Fujitsu         Y.Kanefusa      Update          S21_NA#27478
 * 2018/08/13   Fujitsu         K.Ishizuka      Update          QC#27718
 * 2018/08/15   Fujitsu         M.Ishii         Update          QC#26199
 * 2018/08/27   Fujitsu         K.Ishizuka      Update          QC#25105
 * 2018/09/03   Fujitsu         Y.Kanefusa      Create          S21_NA#9700
 * 2018/10/03   Fujitsu         Hd.Sugawara     Update          QC#28417
 * 2018/11/15   Fujitsu         K.Ishizuka      Update          QC#27299
 * 2018/12/19   Fujitsu         K.Ishizuka      Update          QC#29561
 * 2018/12/19   Fujitsu         W.Honda         Update          QC#29636
 * 2019/04/24   Fujitsu         Hd.Sugawara     Update          QC#31125,QC#31126
 * 2019/05/15   Fujitsu         Y.Kanefusa      Update          S21_NA#50130
 * 2019/06/05   Fujitsu         R.Nakamura      Update          S21_NA#50134
 * 2019/06/10   Fujitsu         Y.Kanefusa      Update          S21_NA#50776
 * 2019/08/14   Fujitsu         Y.Kanefusa      Update          S21_NA#52623
 *</pre>
 */
public class NWAL2300BL06 extends S21BusinessHandler {

    // 2018/05/09 QC#25030 Add Start
    /**
     * if this flag is false, the method setShellRebill() will be not processed.
     * if you need process creation shell method "setShellRebill", please change this 
     * flag to true.
     */
    private static boolean callShell = false;
    // 2018/05/09 QC#25030 Add End

    // 2018/05/29 QC#21841 Add Start
    /** Price Detail Group code -> price condition type exchange map */
    private static final Map<String, String> PRC_DTL_GRP_COND_TP_MAP = new LinkedHashMap<String, String>();
    // QC#50130 2019/05/15 Add Start
    private static final Map<String, String> PRC_DTL_GRP_COND_TP_RMA_MAP = new LinkedHashMap<String, String>();
    // QC#50130 2019/05/15 Add End

    /** */
    private static final String SET_PRC_DATA_MODE_ORDER_CREDIT = "1";
    /** */
    private static final String SET_PRC_DATA_MODE_RETURN_CREDIT = "2";
    // 2018/05/29 QC#21841 Add End
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        // 2018/05/29 QC#21841 Add Start
        PRC_DTL_GRP_COND_TP_MAP.put(PRC_DTL_GRP.FREIGHT,        PRC_COND_TP.CREDIT_REBILL_FREIGHT);
        PRC_DTL_GRP_COND_TP_MAP.put(PRC_DTL_GRP.HANDLING_FEE,   PRC_COND_TP.CR_RB_HANDLING_FEE);
        PRC_DTL_GRP_COND_TP_MAP.put(PRC_DTL_GRP.FUEL_SURCHARGE, PRC_COND_TP.CR_RB_FUEL_SURCHARGE);
        PRC_DTL_GRP_COND_TP_MAP.put(PRC_DTL_GRP.SHIPPING_FEE,   PRC_COND_TP.CR_RB_SHIPPING_FEE);
        // 2018/05/29 QC#21841 Add End
        PRC_DTL_GRP_COND_TP_MAP.put(PRC_DTL_GRP.RESTOCKING_FEE,   PRC_COND_TP.CR_RB_RESTOCK_FEE);

        // QC#50130 2019/05/15 Add Start
        PRC_DTL_GRP_COND_TP_RMA_MAP.put(PRC_DTL_GRP.FREIGHT,        PRC_COND_TP.RMA_FREIGHT);
        PRC_DTL_GRP_COND_TP_RMA_MAP.put(PRC_DTL_GRP.HANDLING_FEE,   PRC_COND_TP.RMA_HANDLING_FEE);
        PRC_DTL_GRP_COND_TP_RMA_MAP.put(PRC_DTL_GRP.FUEL_SURCHARGE, PRC_COND_TP.RMA_FUEL_SURCHARGE);
        PRC_DTL_GRP_COND_TP_RMA_MAP.put(PRC_DTL_GRP.SHIPPING_FEE,   PRC_COND_TP.RMA_SHIPPING_FEE);
        PRC_DTL_GRP_COND_TP_RMA_MAP.put(PRC_DTL_GRP.RESTOCKING_FEE, PRC_COND_TP.RMA_RESTOCK_FEE);
        // QC#50130 2019/05/15 Add End

        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2300CMsg bizMsg = (NWAL2300CMsg) cMsg;

            if ("NWAL2300Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL2300Scrn00_CMN_Submit(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2300Scrn00_CMN_Submit(NWAL2300CMsg bizMsg) {

        // 2018/04/06 QC#22122 Mod Start
        if (BigDecimal.ONE.equals(bizMsg.xxRadioBtn.getValue())) {
            // Order
            /***************************************************************************
             * Error Check
             ***************************************************************************/
            if (NWAL2300CommonLogic.errChkSubmit(bizMsg)) {
                return;
            }

            /***************************************************************************
             * Process Credit Order
             ***************************************************************************/
            NWZC150001PMsg pmsg4Credit = new NWZC150001PMsg();
            NWZC150001PMsg pmsg4CreditOrig = new NWZC150001PMsg();
            // 2018/04/06 QC#22122 Mod Start
            //setDsCpoUpdateApiParam4Credit(bizMsg, pmsg4Credit, pmsg4CreditOrig);
            setDsCpoUpdateApiParam4Credit(bizMsg, pmsg4Credit, pmsg4CreditOrig, null);
            // 2018/04/06 QC#22122 Mod End
            if (hasErr(bizMsg)) {
                return;
            }

            /***************************************************************************
             * Process Rebill Order
             ***************************************************************************/
            NWZC150001PMsg pmsg4Rebill = new NWZC150001PMsg();
            NSZC115001PMsg shellPmsg = new NSZC115001PMsg();
            // 2018/04/06 QC#22122 Mod Start
            //setDsCpoUpdateApiParam4Rebill(bizMsg, pmsg4CreditOrig, pmsg4Rebill, shellPmsg);
            setDsCpoUpdateApiParam4Rebill(bizMsg, pmsg4CreditOrig, pmsg4Rebill, shellPmsg, null);
            // 2018/04/06 QC#22122 Mod End
            if (hasErr(bizMsg)) {
                return;
            }

            /***************************************************************************
             * Process Original Order
             ***************************************************************************/
            NWZC150001PMsg pmsg4Orig = new NWZC150001PMsg();
            setDsCpoUpdateApiParam4Orig(bizMsg, pmsg4CreditOrig, pmsg4Orig);

            // 2017/01/11 QC#16669 ADD START
            /***************************************************************************
             * Process Original Order Partial
             ***************************************************************************/
            NWZC150001PMsg pmsg4OrigPartial = new NWZC150001PMsg();
            setDsCpoUpdateApiParam4OrigPartial(bizMsg, pmsg4CreditOrig, pmsg4OrigPartial);
            // 2017/01/11 QC#16669 ADD END

            if (hasErr(bizMsg)) {
                return;
            }

            // 2017/01/11 QC#16669 ADD START
            // Original Order Partial
            if (!callApi(bizMsg, pmsg4OrigPartial)) {
                return;
            }
            // 2017/01/11 QC#16669 ADD END

            // 2018/05/29 QC#21841 Add Start
            boolean isOrigOrderClosed = isOrderClosed(bizMsg);
            // 2018/05/29 QC#21841 Add End
            // Original Order
            if (!isOrigOrderClosed) { // 2018/05/29 QC#21841 Add Condition
                if (!callApi(bizMsg, pmsg4Orig)) {
                    return;
                }
            } // // 2018/05/29 QC#21841 Add Condition
            // Credit Order
            if (!callApi(bizMsg, pmsg4Credit)) {
                return;
            }

            // Rebill Order
            // Add Start 2019/04/24 QC#31125,QC#31126
            NWXC150001SalesRep.updateToLatestSalesRep(pmsg4Rebill);
            // Add End 2019/04/24 QC#31125,QC#31126

            if (!callApi(bizMsg, pmsg4Rebill)) {
                return;
            }

            String creCpoNum = pmsg4Credit.cpoOrdNum.getValue();
            String rblCpoNum = pmsg4Rebill.cpoOrdNum.getValue();
            /***************************************************************************
             * Update Credit and Rebill Order to Original
             * Order/Invoice#
             ***************************************************************************/
            if (!updateCreateOrder(bizMsg, creCpoNum, rblCpoNum)) {
                return;
            }
            /***************************************************************************
             * Create Order Profitability for Credit Order
             ***************************************************************************/
            if (!insertProfitability(bizMsg, creCpoNum, pmsg4Credit)) {
                return;
            }

            /***************************************************************************
             * Create Credit and Rebill Comments
             ***************************************************************************/
            if (!insertComments(bizMsg, creCpoNum, rblCpoNum)) {
                return;
            }

            /***************************************************************************
             * Create Credit and Rebill Reason
             ***************************************************************************/
            if (!insertCreditRebillReason(bizMsg, creCpoNum, rblCpoNum)) {
                return;
            }

            /***************************************************************************
             * Create Shell
             ***************************************************************************/
            // QC#19718 DEL START
            // if (!insertShell(bizMsg, pmsg4Rebill, shellPmsg,
            // rblCpoNum)) {
            // return;
            // }
            // QC#19718 DEL END
            /***************************************************************************
             * Warning Message Control Flag Reset
             ***************************************************************************/
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H2, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H3, ZYPConstant.FLG_OFF_N);

        } else {
            // Partial Invoice

            List<String> invNumList = new ArrayList<String>();
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).xxChkBox_B1)) {
                    invNumList.add(bizMsg.B.no(i).invNum_B1.getValue());
                }
            }

            /***************************************************************************
             * Error Check(for Partial Invoice)
             ***************************************************************************/
            if (NWAL2300CommonLogic.errChkSubmitForPartialInvoice(bizMsg, invNumList)) {
                return;
            }

            for (String invNum : invNumList) {
                /***************************************************************************
                 * Process Credit Order(for Partial Invoice)
                 ***************************************************************************/
                NWZC150001PMsg pmsg4Credit = new NWZC150001PMsg();
                NWZC150001PMsg pmsg4CreditOrig = new NWZC150001PMsg();
                setDsCpoUpdateApiParam4Credit(bizMsg, pmsg4Credit, pmsg4CreditOrig, invNum);
                if (hasErr(bizMsg)) {
                    return;
                }

                /***************************************************************************
                 * Process Rebill Order(for Partial Invoice)
                 ***************************************************************************/
                NWZC150001PMsg pmsg4Rebill = new NWZC150001PMsg();
                NSZC115001PMsg shellPmsg = new NSZC115001PMsg();
                setDsCpoUpdateApiParam4Rebill(bizMsg, pmsg4CreditOrig, pmsg4Rebill, shellPmsg, invNum);
                if (hasErr(bizMsg)) {
                    return;
                }

                if (hasErr(bizMsg)) {
                    return;
                }

                // Credit Order
                if (!callApi(bizMsg, pmsg4Credit)) {
                    return;
                }

                // Rebill Order
                if (!callApi(bizMsg, pmsg4Rebill)) {
                    return;
                }

                String creCpoNum = pmsg4Credit.cpoOrdNum.getValue();
                String rblCpoNum = pmsg4Rebill.cpoOrdNum.getValue();
                /***************************************************************************
                 * Update Credit and Rebill Order to Original
                 * Order/Invoice#(for Partial Invoice)
                 ***************************************************************************/
                if (!updateCreateOrder(bizMsg, creCpoNum, rblCpoNum)) {
                    return;
                }
                /***************************************************************************
                 * Create Order Profitability for Credit Order(for
                 * Partial Invoice)
                 ***************************************************************************/
                if (!insertProfitability(bizMsg, creCpoNum, pmsg4Credit)) {
                    return;
                }

                /***************************************************************************
                 * Create Credit and Rebill Comments(for Partial
                 * Invoice)
                 ***************************************************************************/
                if (!insertComments(bizMsg, creCpoNum, rblCpoNum)) {
                    return;
                }

                /***************************************************************************
                 * Create Credit and Rebill Reason(for Partial
                 * Invoice)
                 ***************************************************************************/
                if (!insertCreditRebillReason(bizMsg, creCpoNum, rblCpoNum)) {
                    return;
                }

                /***************************************************************************
                 * Warning Message Control Flag Reset(for Partial
                 * Invoice)
                 ***************************************************************************/
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H1, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H2, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H3, ZYPConstant.FLG_OFF_N);

            }
        }
        // 2018/04/06 QC#22122 Mod End

    }

    // 2018/04/06 QC#22122 Mod Start
    //private void setDsCpoUpdateApiParam4Credit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4CreditOrig) {
    private void setDsCpoUpdateApiParam4Credit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4CreditOrig, String invNum) {
    // 2018/04/06 QC#22122 Mod End

        // 2018/04/06 QC#22122 Mod Start
        //setOrderHeaderCredit(bizMsg, pmsg4Credit);
        setOrderHeaderCredit(bizMsg, pmsg4Credit, invNum);
        // 2018/04/06 QC#22122 Mod End

        // Outbound Credit
        List<String> procActions = new ArrayList<String>();
        procActions.add(PULLDOWN_CD_C_R);

        Set<String> procdInvNums4FrtFact = new HashSet<String>();

        // 2018/04/06 QC#22122 Mod Start
        //setOrderConfigCredit(bizMsg, pmsg4Credit, procActions, CONFIG_CATG.OUTBOUND);
        //setOrderDetailPrcCredit(bizMsg, pmsg4Credit, procActions, procdInvNums4FrtFact);
        //setOrderDetailCredit(bizMsg, pmsg4Credit, procActions);
        //setOrderSalesCreditCredit(bizMsg, pmsg4Credit, procActions, CONFIG_CATG.OUTBOUND);
        //setOrderDiscCredit(bizMsg, pmsg4Credit, procActions, CONFIG_CATG.OUTBOUND);
        setOrderConfigCredit(bizMsg, pmsg4Credit, procActions, invNum, CONFIG_CATG.OUTBOUND);
        setOrderDetailPrcCredit(bizMsg, pmsg4Credit, procActions, procdInvNums4FrtFact, invNum);
        setOrderDetailCredit(bizMsg, pmsg4Credit, procActions, invNum);
        setOrderSalesCreditCredit(bizMsg, pmsg4Credit, procActions, invNum, CONFIG_CATG.OUTBOUND);
        setOrderDiscCredit(bizMsg, pmsg4Credit, procActions, invNum, CONFIG_CATG.OUTBOUND);
        // 2018/04/06 QC#22122 Mod End

        // QC#19718
        // Mod Start 2017/10/16 QC#21729
        if (isExistNegoDealAmt(bizMsg)) {
            setNegoDealAmtForCredit(pmsg4Credit, BigDecimal.valueOf(1));
        }
        // Mod End 2017/10/16 QC#21729

        EZDMsg.copy(pmsg4Credit, null, pmsg4CreditOrig, null);

        // Inbound Credit
        procActions = new ArrayList<String>();
        procActions.add(PULLDOWN_CD_RMA_RB);

        // 2018/04/06 QC#22122 Mod Start
        //setOrderConfigCredit(bizMsg, pmsg4Credit, procActions, CONFIG_CATG.INBOUND);
        //setReturnDetailPrcCredit(bizMsg, pmsg4Credit, procActions, procdInvNums4FrtFact);
        //setReturnDetailCredit(bizMsg, pmsg4Credit, procActions);
        //setOrderSalesCreditCredit(bizMsg, pmsg4Credit, procActions, CONFIG_CATG.INBOUND);
        //setOrderDiscCredit(bizMsg, pmsg4Credit, procActions, CONFIG_CATG.INBOUND);
        setOrderConfigCredit(bizMsg, pmsg4Credit, procActions, invNum, CONFIG_CATG.INBOUND);
        setReturnDetailPrcCredit(bizMsg, pmsg4Credit, procActions, procdInvNums4FrtFact, invNum);
        setReturnDetailCredit(bizMsg, pmsg4Credit, procActions, invNum);
        setOrderSalesCreditCredit(bizMsg, pmsg4Credit, procActions, invNum, CONFIG_CATG.INBOUND);
        setOrderDiscCredit(bizMsg, pmsg4Credit, procActions, invNum, CONFIG_CATG.INBOUND);
        // 2018/04/06 QC#22122 Mod End

        numberingLineNum(pmsg4Credit);
    }

    // 2018/04/06 QC#22122 Mod Start
    //private void setOrderHeaderCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pMsg) {
    //    setOrderHeader(bizMsg, pMsg, CR_REBIL.CREDIT);
    //}
    private void setOrderHeaderCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pMsg, String invNum) {
        setOrderHeader(bizMsg, pMsg, CR_REBIL.CREDIT, invNum);
    }
    // 2018/04/06 QC#22122 Mod End

    // 2018/04/06 QC#22122 Mod Start
    //private void setOrderHeader(NWAL2300CMsg bizMsg, NWZC150001PMsg pMsg,  String crRebilCd) {
    private void setOrderHeader(NWAL2300CMsg bizMsg, NWZC150001PMsg pMsg,  String crRebilCd, String invNum) {
    // 2018/04/06 QC#22122 Mod End

        // 2018/04/06 QC#22122 Mod Start
        // Copy INV
        //INVTMsgArray invList = getINVTMsgList(bizMsg);
        //if (invList != null) {
        //    EZDMsg.copy(invList.no(0), null, pMsg, null);
        //}
        INVTMsgArray invList = new INVTMsgArray();
        if (!ZYPCommonFunc.hasValue(invNum)) {
            invList = getINVTMsgList(bizMsg);
            if (invList != null) {
                EZDMsg.copy(invList.no(0), null, pMsg, null);
            }
        } else {
            INVTMsg inv = getINVTMsg(bizMsg, null, invNum);
            if (inv != null) {
                invList.setMsgList(new INVTMsg[] {inv});
                EZDMsg.copy(inv, null, pMsg, null);
            }
        }
        // 2018/04/06 QC#22122 Mod End

        // 2018/04/06 QC#22122 Mod Start
        // Copy INV_BOL
        //INV_BOLTMsgArray invBolList = getINV_BOLTMsgList(bizMsg);
        INV_BOLTMsgArray invBolList = getINV_BOLTMsgList(bizMsg, invNum);
        // 2018/04/06 QC#22122 Mod End
        if (invBolList != null) {
            EZDMsg.copy(invBolList.no(0), null, pMsg, null);
            // QC#52623 2019/08/14 Add Start
            ZYPEZDItemValueSetter.setValue(pMsg.addShpgSvcLvlCd, invBolList.no(0).shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(pMsg.addFrtChrgToCd, invBolList.no(0).frtChrgToCd);
            ZYPEZDItemValueSetter.setValue(pMsg.addFrtChrgMethCd, invBolList.no(0).frtChrgMethCd);
            ZYPEZDItemValueSetter.setValue(pMsg.addDropShipFlg, invBolList.no(0).dropShipFlg);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToLocNm, invBolList.no(0).shipToLocNm);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToAddlLocNm, invBolList.no(0).shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToFirstLineAddr, invBolList.no(0).shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToScdLineAddr, invBolList.no(0).shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToThirdLineAddr, invBolList.no(0).shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToFrthLineAddr, invBolList.no(0).shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtyAddr, invBolList.no(0).shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToStCd, invBolList.no(0).shipToStCd);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToProvNm, invBolList.no(0).shipToProvNm);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToCntyNm, invBolList.no(0).shipToCntyNm);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToPostCd, invBolList.no(0).shipToPostCd);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtryCd, invBolList.no(0).shipToCtryCd);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipTo01RefCmntTxt, invBolList.no(0).shipToFirstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(pMsg.addShipTo02RefCmntTxt, invBolList.no(0).shipToScdRefCmntTxt);
            // QC#52623 2019/08/14 Add End
        }
        // Copy CPO
        CPOTMsg cpo = getCPOTMsg(bizMsg);
        ZYPEZDItemValueSetter.setValue(pMsg.crRebilRsnCatgCd, NWAL2300CommonLogic.getCrRebilRsnCatg(bizMsg));
        String dt = getDt(bizMsg, invList.no(0).invDt.getValue(), pMsg.crRebilRsnCatgCd.getValue(), crRebilCd); // QC#12892 MOD

        pMsg.cpoOrdNum.clear();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWAL2300CommonLogic.getRequestType(bizMsg));
        ZYPEZDItemValueSetter.setValue(pMsg.usrId, this.getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.bizAppId, BIZ_ID);
        // Mod Start 2017/10/20 QC#21773
        //ZYPEZDItemValueSetter.setValue(pMsg.cpoSrcTpCd, CPO_SRC_TP.CREDIT_AND_REBILL_ENTRY);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoSrcTpCd, CPO_SRC_TP.CREDIT);
        // Mod End 2017/10/20 QC#21773
        ZYPEZDItemValueSetter.setValue(pMsg.slsRepCd, invList.no(0).slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(pMsg.addShpgSvcLvlCd, NWAL2300CommonLogic.getShpgSvcLvlCdFromInvBol(invBolList));
        ZYPEZDItemValueSetter.setValue(pMsg.ordSrcRefNum, bizMsg.cpoOrdNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.adminPsnCd, getContextUserInfo().getUserId());
        if (!ZYPCommonFunc.hasValue(pMsg.addRddDt) && cpo != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.addRddDt, cpo.addRddDt);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.addShpgSvcLvlCd) && cpo != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.addShpgSvcLvlCd, cpo.addShpgSvcLvlCd);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCustCd, NWAL2300CommonLogic.getShipToCustCdFromInvBol(invBolList));

        if (!ZYPCommonFunc.hasValue(pMsg.prcBaseDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, dt);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.prcCalcDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prcCalcDt, dt);
        }

        // 2017/10/19 QC#21708 Add Start
        if (cpo != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.prcCatgCd, cpo.prcCatgCd);
        }
        // 2017/10/19 QC#21708 Add End
        if (!ZYPCommonFunc.hasValue(pMsg.prcCatgCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prcCatgCd//
                    , getPrcCatgCd(bizMsg, bizMsg.glblCmpyCd.getValue()//
                            , NWZC157001.GET_PRICE_LIST//
                            , dt, PRC_CTX_TP.SALES_PRICING//
                            , pMsg.dsOrdCatgCd.getValue()//
                            , pMsg.dsOrdTpCd.getValue()//
                            , pMsg.sellToCustCd.getValue()//
                            , bizMsg.cpoOrdNum_H1.getValue()));
        }

        // 2017/10/19 QC#21708 Add Start
        if (cpo != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.flPrcListCd, cpo.flPrcListCd);
        }
        // 2017/10/19 QC#21708 Add End
        if (!ZYPCommonFunc.hasValue(pMsg.flPrcListCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.flPrcListCd//
                    , getPrcCatgCd(bizMsg, bizMsg.glblCmpyCd.getValue()//
                            , NWZC157001.GET_PRICE_LIST//
                            , dt//
                            , PRC_CTX_TP.FLOOR_PRICE//
                            , pMsg.dsOrdCatgCd.getValue()//
                            , pMsg.dsOrdTpCd.getValue()//
                            , pMsg.sellToCustCd.getValue()//
                            , bizMsg.cpoOrdNum_H1.getValue()));
        }

        if (!ZYPCommonFunc.hasValue(pMsg.csmpContrNum)) {
            S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getCsmpContrNum(bizMsg);
            if (ssmResult.isCodeNormal()) {
                ZYPEZDItemValueSetter.setValue(pMsg.csmpContrNum, (String) ssmResult.getResultObject());
            }
        }

//        ZYPEZDItemValueSetter.setValue(pMsg.aquNum, pMsg.prcContrNum);
        if (!ZYPCommonFunc.hasValue(pMsg.ordSrcImptDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.ordSrcImptDt, bizMsg.slsDt);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.dlrRefNum)) {
            S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getDlrRefNum(bizMsg);
            if (ssmResult.isCodeNormal()) {
                ZYPEZDItemValueSetter.setValue(pMsg.dlrRefNum, (String) ssmResult.getResultObject());
            }
        }

        if (!ZYPCommonFunc.hasValue(pMsg.frtCondCd)) {
            S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getFrtCondCdFcc(bizMsg, invBolList);
            if (ssmResult.isCodeNormal() && 0 < ssmResult.getQueryResultCount()) {
                String frtCondCd = (String) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(pMsg.frtCondCd, frtCondCd);
            }
            // 2017/09/25 QC#20511 ADD START
            if (!ZYPCommonFunc.hasValue(pMsg.frtCondCd)) {
                bizMsg.setMessageInfo(NWAM0944E);
            }
            // 2017/09/25 QC#20511 ADD END
        }

        // QC#11538 START
        pMsg.carrSvcLvlCd.clear();
        if (cpo != null && ZYPCommonFunc.hasValue(cpo.carrSvcLvlCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.carrSvcLvlCd, cpo.carrSvcLvlCd.getValue());
        }
        pMsg.carrAcctNum.clear();
        if (cpo != null && ZYPCommonFunc.hasValue(cpo.carrAcctNum)) {
            ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, cpo.carrAcctNum.getValue());
        }

        // if (!ZYPCommonFunc.hasValue(pMsg.carrSvcLvlCd)) {
        //     S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getReasonCodeCategory(bizMsg.glblCmpyCd.getValue(), invBolList);
        //     if (ssmResult.isCodeNormal()) {
        //         String carrSvcLvlCd = (String) ssmResult.getResultObject();
        //         ZYPEZDItemValueSetter.setValue(pMsg.carrSvcLvlCd, carrSvcLvlCd);
        //     }
        // }

        // QC#11538 END

        ZYPEZDItemValueSetter.setValue(pMsg.xxValUpdFlg, ZYPConstant.FLG_ON_Y);

        // QC#12123
        ZYPEZDItemValueSetter.setValue(pMsg.addPmtTermCashDiscCd, getPmtTermCashDiscCd(bizMsg, bizMsg.pmtTermCashDiscCd_H1.getValue(), crRebilCd));
        pMsg.dsPmtMethCd.clear();
        pMsg.dsCrCardPk.clear();
        // QC#19718
//        pMsg.negoDealAmt.clear();

        // 2017/10/19 QC#21708 Add Start
        if (cpo != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.ordLogTpCd, cpo.ordLogTpCd);
            ZYPEZDItemValueSetter.setValue(pMsg.dclnSvcCd, cpo.dclnSvcCd);
            ZYPEZDItemValueSetter.setValue(pMsg.dsCpoPrcInd, cpo.dsCpoPrcInd);
        }
        // 2017/10/19 QC#21708 Add End
    }

    // QC#19718 ADD START
    private void setNegoDealAmtForCredit(NWZC150001PMsg msg, BigDecimal multVal) {
        BigDecimal negoDealAmt = BigDecimal.ZERO;

        for (int i = 0; i < msg.A.getValidCount(); i++) {
            NWZC150001_APMsg dtlPMsg = msg.A.no(i);
            if (ZYPCommonFunc.hasValue(dtlPMsg.entDealNetUnitPrcAmt_A1) && ZYPCommonFunc.hasValue(dtlPMsg.ordQty_A1)) {
                negoDealAmt = negoDealAmt.add(dtlPMsg.entDealNetUnitPrcAmt_A1.getValue().multiply(dtlPMsg.ordQty_A1.getValue()));
            }
        }
        ZYPEZDItemValueSetter.setValue(msg.negoDealAmt, negoDealAmt.multiply(multVal));
    }
    // QC#19718 ADD END

    // 2018/04/06 QC#22122 Mod Start
    //private void setOrderConfigCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, List<String> procActions, String configCatgCd) {
    private void setOrderConfigCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, List<String> procActions, String invNum, String configCatgCd) {
    // 2018/04/06 QC#22122 Mod End

        Set<String> procdPosnNums = new HashSet<String>();

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            // 2018/04/06 QC#22122 Add Start
            if (ZYPCommonFunc.hasValue(invNum) && !invNum.equals(bizMsg.C.no(i).invNum_C1.getValue())) {
                continue;
            }
            // 2018/04/06 QC#22122 Add End
            NWAL2300_CCMsg invLineMsg = bizMsg.C.no(i);
            if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLineMsg.dsOrdLineDrctnCd_C1.getValue())) {
                continue;
            }

            if (!procActions.contains(invLineMsg.xxTpCd_C1.getValue())) {
                continue;
            }

            String dsOrdPosnNum = invLineMsg.dsOrdPosnNum_C1.getValue();
            if (procdPosnNums.contains(dsOrdPosnNum)) {
                continue;
            }

            // Add Start 2019/06/05 QC#50134
            if (NWAL2300CommonLogic.checkMdseRmaNotAllow(invLineMsg, bizMsg.glblCmpyCd.getValue())) {
                continue;
            }
            // Add End 2019/06/05 QC#50134

            procdPosnNums.add(dsOrdPosnNum);
            setOrderConfig(bizMsg, invLineMsg, pmsg4Credit, configCatgCd, CONFIG_TP.CREDIT_REBILL_CONFIG);
        }
    }

    private void setOrderConfig(NWAL2300CMsg bizMsg, NWAL2300_CCMsg invLineMsg, NWZC150001PMsg pmsg, String configCatgCd, String configTpCd) {

        final NWZC150001_cpoConfigPMsg cpoConfigPMsg = pmsg.cpoConfig.no(pmsg.cpoConfig.getValidCount());
        pmsg.cpoConfig.setValidCount(pmsg.cpoConfig.getValidCount() + 1);

        // Add Start 2018/10/03 QC#28417
        boolean isRetailEquipOrder = NWXC150001DsCheck.isRetailEquipOrder( //
                pmsg.glblCmpyCd.getValue(), //
                pmsg.dsOrdCatgCd.getValue(), //
                pmsg.dsOrdTpCd.getValue(), //
                pmsg.dsOrdRsnCd.getValue());
        // Add End 2018/10/03 QC#28417

        if (!ZYPConstant.FLG_ON_Y.equals(invLineMsg.openFlg_C1.getValue())) {
            /** Closed Line **/
            // Copy INV
            // 2018/04/06 QC#22122 Mod Start
            //INVTMsg inv = getINVTMsg(bizMsg, invLineMsg);
            INVTMsg inv = getINVTMsg(bizMsg, invLineMsg, null);
            // 2018/04/06 QC#22122 Mod End
            if (inv != null) {
                EZDMsg.copy(inv, null, cpoConfigPMsg, null);
            }
            // Copy INV_BOL
            INV_BOLTMsg invBol = getINV_BOLTMsg(bizMsg, invLineMsg);
            if (invBol != null) {
                EZDMsg.copy(invBol, null, cpoConfigPMsg, null);
                setOrigShipInfo(cpoConfigPMsg, invBol); // 2018/11/15 S21_NA#27299 Add
            }
            // Copy INV_LINE
            INV_LINETMsg invLine = getINV_LINETMsg(bizMsg, invLineMsg);
            if (invLine != null) {
                EZDMsg.copy(invLine, null, cpoConfigPMsg, null);
            }

            // Add Start 2018/10/03 QC#28417
            if (!isRetailEquipOrder) {
                cpoConfigPMsg.svcConfigMstrPk.clear();
            }
            // Add End 2018/10/03 QC#28417

        } else {
            /** Open Line **/
            // Copy DS_CPO_CONFIG
            DS_CPO_CONFIGTMsg config = getDS_CPO_CONFIGTMsg(bizMsg, invLineMsg);
            if (config != null) {
                EZDMsg.copy(config, null, cpoConfigPMsg, null);
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustCd, config.billToCustLocCd);
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustCd, config.shipToCustLocCd);
            }
        }

        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.xxRqstTpCd, RQST_TP_CONFIG_NEW);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsOrdPosnNum, invLineMsg.dsOrdPosnNum_C1);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configCatgCd, configCatgCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsCpoConfigPk, invLineMsg.dsCpoConfigPk_C1);
        if (!ZYPConstant.FLG_ON_Y.equals(invLineMsg.openFlg_C1.getValue())) {
            // Mod Start 2018/10/03 QC#28417
            //if (!ZYPCommonFunc.hasValue(cpoConfigPMsg.svcConfigMstrPk)) {
            //    // Not exists IB in the config.
            if (!ZYPCommonFunc.hasValue(cpoConfigPMsg.svcConfigMstrPk) || !isRetailEquipOrder) {
                // Not exists IB in the config. or Not Machine Order
                // Mod End 2018/10/03 QC#28417
                if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
                    ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configTpCd, CONFIG_TP.NEW);
                } else {
                    ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configTpCd, CONFIG_TP.RETURN_NEW);
                }
            } else {
                if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
                    // Outbund Close
                    ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configTpCd, configTpCd);
                } else {
                    // Inbound Close
                    ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configTpCd, CONFIG_TP.RETURN_EXISTING_IB);
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(cpoConfigPMsg.mdlId) && ZYPCommonFunc.hasValue(cpoConfigPMsg.svcConfigMstrPk)) {
            S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getMdlId(bizMsg.glblCmpyCd.getValue(), cpoConfigPMsg.svcConfigMstrPk.getValue());
            if (ssmResult.isCodeNormal()) {
                BigDecimal mdlId = (BigDecimal) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlId, mdlId);
            }
        }

        if (!ZYPCommonFunc.hasValue(cpoConfigPMsg.mdlDescTxt) && ZYPCommonFunc.hasValue(cpoConfigPMsg.mdlId)) {
            S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getMdlDescTxt(bizMsg.glblCmpyCd.getValue(), cpoConfigPMsg.mdlId.getValue());
            if (ssmResult.isCodeNormal()) {
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlDescTxt, (String) ssmResult.getResultObject());
            }
        }
    }

    // 2018/04/06 QC#22122 Mod Start
    //private void setOrderDetailPrcCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, List<String> procActions, Set<String> procdInvNums4FrtFact) {
    private void setOrderDetailPrcCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, List<String> procActions, Set<String> procdInvNums4FrtFact, String invoiceNumber) {
    // 2018/04/06 QC#22122 Mod End

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            // 2018/04/06 QC#22122 Add Start
            if (ZYPCommonFunc.hasValue(invoiceNumber) && !invoiceNumber.equals(bizMsg.C.no(i).invNum_C1.getValue())) {
                continue;
            }
            // 2018/04/06 QC#22122 Add End
            NWAL2300_CCMsg invLineMsg = bizMsg.C.no(i);
            if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLineMsg.dsOrdLineDrctnCd_C1.getValue())) {
                continue;
            }

            if (!procActions.contains(invLineMsg.xxTpCd_C1.getValue())) {
                continue;
            }

            // 2018/05/29 QC#21841 Del Start
//            // 2017/10/19 QC#21708 Add Start
//            if (setCreditPrcPMsgFromOrdPrcCalcBase(bizMsg, pmsg4Credit, invLineMsg)) {
//                continue;
//            }
//            // 2017/10/19 QC#21708 Add End
            // 2018/05/29 QC#21841 Del End
            S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getPriceElementOutbound(bizMsg, invLineMsg);
            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> rstMapList = (List<Map<String, Object>>) ssmResult.getResultObject();

                for (Map<String, Object> rstMap : rstMapList) {

                    // 2018/05/29 QC#21841 Del Start
//                    // BASE
//                    final NWZC150001_priceListPMsg basePrcPMsg = pmsg4Credit.priceList.no(pmsg4Credit.priceList.getValidCount());
//                    pmsg4Credit.priceList.setValidCount(pmsg4Credit.priceList.getValidCount() + 1);
//
//                    // QC#22106 2018/03/30 Add Start
//                    //ZYPEZDItemValueSetter.setValue(basePrcPMsg.cpoDtlLineNum, (String) rstMap.get("CPO_DTL_LINE_NUM"));
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.cpoDtlLineNum, invLineMsg.cpoDtlLineNum_T);
//                    // QC#22106 2018/03/30 Add End
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.cpoDtlLineSubNum, (String) rstMap.get("CPO_DTL_LINE_SUB_NUM"));
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondTpCd, PRC_COND_TP.BASE_PRICE);
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.BASE_PRICE);
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.pkgUomCd, (String) rstMap.get("UOM_CD"));
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondUnitCd, PRC_COND_UNIT.AMT);
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.BASE_PRICE));
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.autoPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"))));
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.manPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"))));
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_NET_AMT"))));
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.unitPrcAmt, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"))));
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.autoPrcCcyCd, (String) rstMap.get("DEAL_CCY_CD"));
//                    // QC#22106 2018/03/30 Add Start
//                    //QC#15624 add Start
//                    //if (!ZYPCommonFunc.hasValue(basePrcPMsg.cpoDtlLineNum)) {
//                    //    ZYPEZDItemValueSetter.setValue(basePrcPMsg.cpoDtlLineNum, String.valueOf(i + 1));
//                    //}
//                    //QC#15624 add End
//                    // QC#22106 2018/03/30 Add End
//
//                    // BASE TAX
//                    final NWZC150001_priceListPMsg baseTaxPrcPMsg = pmsg4Credit.priceList.no(pmsg4Credit.priceList.getValidCount());
//                    pmsg4Credit.priceList.setValidCount(pmsg4Credit.priceList.getValidCount() + 1);
//                    EZDMsg.copy(basePrcPMsg, null, baseTaxPrcPMsg, null);
//
//                    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.prcCondTpCd, PRC_COND_TP.ITEM_TAX1);
//                    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.TAX);
//                    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.ITEM_TAX1));
//                    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.autoPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
//                    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.manPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
//                    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
//                    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.unitPrcAmt, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
//                    baseTaxPrcPMsg.pkgUomCd.clear();
//
//                    String invNum = (String) rstMap.get("INV_NUM");
//                    if (procdInvNums4FrtFact.contains(invNum)) {
//                        continue;
//                    } else {
//                        procdInvNums4FrtFact.add(invNum);
//                    }
//
//                    if (BigDecimal.ZERO.compareTo((BigDecimal) rstMap.get("SHIP_DEAL_FRT_AMT")) == 0 && BigDecimal.ZERO.compareTo((BigDecimal) rstMap.get("FRT_DEAL_TAX_AMT")) == 0) {
//                        // When Freight Amt is Zero, Skip.
//                        continue;
//                    }
//                    // FRT
//                    final NWZC150001_priceListPMsg frtPrcPMsg = pmsg4Credit.priceList.no(pmsg4Credit.priceList.getValidCount());
//                    pmsg4Credit.priceList.setValidCount(pmsg4Credit.priceList.getValidCount() + 1);
//                    EZDMsg.copy(basePrcPMsg, null, frtPrcPMsg, null);
//
//                    // 2017/10/20 QC#21780 Add Start
//                    int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), (String) rstMap.get("DEAL_CCY_CD"));
//                    BigDecimal shipDealFrtAmt = (BigDecimal) rstMap.get("SHIP_DEAL_FRT_AMT");
//
//                    BigDecimal frtUnitPrc = shipDealFrtAmt.divide(invLineMsg.ordQty_C1.getValue(), scale, BigDecimal.ROUND_FLOOR);
//                    BigDecimal frtAmt = frtUnitPrc.multiply(invLineMsg.ordQty_C1.getValue());
//                    BigDecimal fraction = shipDealFrtAmt.subtract(frtUnitPrc.multiply(invLineMsg.ordQty_C1.getValue()));
//                    // 2017/10/20 QC#21780 Add End
//
//                    frtPrcPMsg.prcCondTpCd.clear();
//                    ZYPEZDItemValueSetter.setValue(frtPrcPMsg.prcCondTpCd, PRC_COND_TP.CREDIT_REBILL_FREIGHT);
//                    ZYPEZDItemValueSetter.setValue(frtPrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.FREIGHT);
//                    ZYPEZDItemValueSetter.setValue(frtPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.CREDIT_REBILL_FREIGHT));
//
//                    // 2017/10/20 QC#21780 Mod Start
//                    //ZYPEZDItemValueSetter.setValue(frtPrcPMsg.autoPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("SHIP_DEAL_FRT_AMT"))));
//                    //ZYPEZDItemValueSetter.setValue(frtPrcPMsg.manPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("SHIP_DEAL_FRT_AMT"))));
//                    //ZYPEZDItemValueSetter.setValue(frtPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("SHIP_DEAL_FRT_AMT"))));
//                    //ZYPEZDItemValueSetter.setValue(frtPrcPMsg.unitPrcAmt, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("SHIP_DEAL_FRT_AMT"))));
//                    ZYPEZDItemValueSetter.setValue(frtPrcPMsg.autoPrcAmtRate, NWAL2300CommonLogic.negate(frtUnitPrc));
//                    ZYPEZDItemValueSetter.setValue(frtPrcPMsg.manPrcAmtRate, NWAL2300CommonLogic.negate(frtUnitPrc));
//                    ZYPEZDItemValueSetter.setValue(frtPrcPMsg.calcPrcAmtRate, NWAL2300CommonLogic.negate(frtAmt));
//                    ZYPEZDItemValueSetter.setValue(frtPrcPMsg.unitPrcAmt, NWAL2300CommonLogic.negate(frtUnitPrc));
//                    // 2017/10/20 QC#21780 Mod End
//
//                    // FRT TAX
//                    final NWZC150001_priceListPMsg frtTaxPrcPMsg = pmsg4Credit.priceList.no(pmsg4Credit.priceList.getValidCount());
//                    pmsg4Credit.priceList.setValidCount(pmsg4Credit.priceList.getValidCount() + 1);
//                    EZDMsg.copy(basePrcPMsg, null, frtTaxPrcPMsg, null);
//
//                    ZYPEZDItemValueSetter.setValue(frtTaxPrcPMsg.prcCondTpCd, PRC_COND_TP.FREIGHT_TAX1);
//                    ZYPEZDItemValueSetter.setValue(frtTaxPrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.TAX);
//                    ZYPEZDItemValueSetter.setValue(frtTaxPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.FREIGHT_TAX1));
//                    ZYPEZDItemValueSetter.setValue(frtTaxPrcPMsg.autoPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("FRT_DEAL_TAX_AMT"))));
//                    ZYPEZDItemValueSetter.setValue(frtTaxPrcPMsg.manPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("FRT_DEAL_TAX_AMT"))));
//                    ZYPEZDItemValueSetter.setValue(frtTaxPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("FRT_DEAL_TAX_AMT"))));
//                    ZYPEZDItemValueSetter.setValue(frtTaxPrcPMsg.unitPrcAmt, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("FRT_DEAL_TAX_AMT"))));
//                    frtTaxPrcPMsg.pkgUomCd.clear();
//
//                    // 2017/10/20 QC#21780 Add Start
//                    if (fraction.compareTo(BigDecimal.ZERO) != 0) {
//                        // RND1
//                        final NWZC150001_priceListPMsg rnd1PrcPMsg = pmsg4Credit.priceList.no(pmsg4Credit.priceList.getValidCount());
//                        pmsg4Credit.priceList.setValidCount(pmsg4Credit.priceList.getValidCount() + 1);
//                        EZDMsg.copy(basePrcPMsg, null, rnd1PrcPMsg, null);
//
//                        rnd1PrcPMsg.prcCondTpCd.clear();
//                        ZYPEZDItemValueSetter.setValue(rnd1PrcPMsg.prcCondTpCd, PRC_COND_TP.CREDIT_REBILL_FREIGHT);
//                        ZYPEZDItemValueSetter.setValue(rnd1PrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.ROUNDING_FACTOR_1);
//                        ZYPEZDItemValueSetter.setValue(rnd1PrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.CREDIT_REBILL_FREIGHT));
//                        ZYPEZDItemValueSetter.setValue(rnd1PrcPMsg.autoPrcAmtRate, BigDecimal.ZERO);
//                        ZYPEZDItemValueSetter.setValue(rnd1PrcPMsg.manPrcAmtRate, BigDecimal.ZERO);
//                        ZYPEZDItemValueSetter.setValue(rnd1PrcPMsg.calcPrcAmtRate, NWAL2300CommonLogic.negate(fraction));
//                        ZYPEZDItemValueSetter.setValue(rnd1PrcPMsg.unitPrcAmt, BigDecimal.ZERO);
//                    }
//                    // 2017/10/20 QC#21780 Add End
                    // 2018/05/29 QC#21841 Del End

                    // 2018/05/29 QC#21841 Add Start
                    setPrcCalcData(rstMap, bizMsg, invLineMsg, pmsg4Credit, SET_PRC_DATA_MODE_ORDER_CREDIT); // setOrderDetailPrcCredit
                    // 2018/05/29 QC#21841 Add End
                }
            }
        }
    }

    // 2018/04/06 QC#22122 Mod Start
    //private void setOrderDetailCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, List<String> procActions) {
    private void setOrderDetailCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, List<String> procActions, String invNum) {
    // 2018/04/06 QC#22122 Mod End

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            // 2018/04/06 QC#22122 Add Start
            if (ZYPCommonFunc.hasValue(invNum) && !invNum.equals(bizMsg.C.no(i).invNum_C1.getValue())) {
                continue;
            }
            // 2018/04/06 QC#22122 Add End
            NWAL2300_CCMsg invLineMsg = bizMsg.C.no(i);
            // QC#22106 2018/03/30 Add Start
            //QC#15624 add Start
            //String lineNum = invLineMsg.cpoDtlLineNum_C1.getValue();
            String lineNum = invLineMsg.cpoDtlLineNum_T.getValue();
            String lineSubNum = invLineMsg.cpoDtlLineSubNum_C1.getValue();
            //if (!ZYPCommonFunc.hasValue(lineNum)) {
            //    // bill with equipment
            //    lineNum = String.valueOf(i + 1);
            //    lineSubNum = DEF_LINE_SUB_NUM;
            //}
            //QC#15624 add End
            // QC#22106 2018/03/30 Add End
            if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLineMsg.dsOrdLineDrctnCd_C1.getValue())) {
                continue;
            }

            if (!procActions.contains(invLineMsg.xxTpCd_C1.getValue())) {
                continue;
            }
//            setOrderDetail(bizMsg, pmsg4Credit, invLineMsg, CR_REBIL.CREDIT);  //QC#15624 mod
            setOrderDetail(bizMsg, pmsg4Credit, invLineMsg, CR_REBIL.CREDIT, lineNum, lineSubNum);
        }
    }

//    private void setOrderDetail(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg, NWAL2300_CCMsg invLineMsg, String crRebilCd) {  //QC#15624 mod
    private void setOrderDetail(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg, NWAL2300_CCMsg invLineMsg, String crRebilCd, String lineNum, String lineSubNum) {

        final NWZC150001_APMsg dtlPMsg = pmsg.A.no(pmsg.A.getValidCount());
        pmsg.A.setValidCount(pmsg.A.getValidCount() + 1);
        String dt = bizMsg.slsDt.getValue();
        String uomCd = null;
        String setMdseCd = null;
        String slsRepCd = null;
        // 2018/08/15 QC#26199 Add Start
        String serNum = null;
        // 2018/08/15 QC#26199 Add End
        BigDecimal entDealNetUnitPrcAmt = BigDecimal.ZERO;

        BigDecimal funcSvcRevTrnsfAmt = null; // QC#16568 2016/12/27 Add
        BigDecimal dealSvcRevTrnsfAmt = null; // QC#16568 2016/12/27 Add

        CPOTMsg cpo = getCPOTMsg(bizMsg);
        CPO_DTLTMsg cpoDtl = geCPO_DTLTMsg(bizMsg, invLineMsg);
        // 2016/10/07 QC#15028 MOD START
        INV_LINETMsg invLine = null;
        // 2016/10/07 QC#15028 MOD END

        if (ZYPConstant.FLG_OFF_N.equals(invLineMsg.openFlg_C1.getValue())) {
            /** Closed Line **/
            // Copy INV
            // 2018/04/06 QC#22122 Mod Start
            //INVTMsg inv = getINVTMsg(bizMsg, invLineMsg);
            INVTMsg inv = getINVTMsg(bizMsg, invLineMsg, null);
            // 2018/04/06 QC#22122 Mod End
            if (inv != null) {
                EZDMsg.copy(inv, "", dtlPMsg, "A1");
            }
            // Copy INV_BOL
            INV_BOLTMsg invBol = getINV_BOLTMsg(bizMsg, invLineMsg);
            if (invBol != null) {
                EZDMsg.copy(invBol, "", dtlPMsg, "A1");
            }
            // Copy INV_LINE
            invLine = getINV_LINETMsg(bizMsg, invLineMsg); // 2016/10/07 QC#15028 MOD
            if (invLine != null) {
                EZDMsg.copy(invLine, "", dtlPMsg, "A1");
            }

            // When CPO exists
            //// Get CPO_DTL
            if (cpoDtl != null) {
                EZDMsg.copy(cpoDtl, "", dtlPMsg, "A1");
            }

            // 2018/08/15 QC#26199 Add Start
            serNum = getSerNum(pmsg, invLineMsg);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.serNum_A1, serNum);
            // 2018/08/15 QC#26199 Add End

            dt = getDt(bizMsg, inv.invDt.getValue(), pmsg.crRebilRsnCatgCd.getValue(), crRebilCd); // QC#12892 MOD
            uomCd = invLine.uomCd.getValue();
            setMdseCd = invLine.setMdseCd.getValue();
            slsRepCd = invLine.slsRepTocCd.getValue();
            entDealNetUnitPrcAmt = invLine.dealGrsUnitPrcAmt.getValue();

            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_A1, SHIP_FROM_INVTY_LOC_CD_CR);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_A1, SHIP_FROM_INVTY_LOC_CD_CR);
            dtlPMsg.rtlSwhCd_A1.clear();
            ZYPEZDItemValueSetter.setValue(dtlPMsg.crRebilCd_A1, crRebilCd);
            // QC#16568 2016/12/27 Add Start
            DS_INV_SLS_CRTMsg dsInvSlsCrTMsg = getDS_INV_SLS_CRTMsgForDeviation(bizMsg, invLineMsg);
            if (dsInvSlsCrTMsg != null) {
                funcSvcRevTrnsfAmt = dsInvSlsCrTMsg.funcSlsCrAmt.getValue();
                dealSvcRevTrnsfAmt = dsInvSlsCrTMsg.dealSlsCrAmt.getValue();
            }
            // QC#16568 2016/12/27 Add End

            // QC#22969 Mod Start
            //if (CR_REBIL.CREDIT.equals(cpoDtl.crRebilCd.getValue()) || CR_REBIL.REBILL.equals(cpoDtl.crRebilCd.getValue())){ // 2018/01/16 S21_NA#23237 MOD 
            if (cpoDtl != null && (CR_REBIL.CREDIT.equals(cpoDtl.crRebilCd.getValue()) || CR_REBIL.REBILL.equals(cpoDtl.crRebilCd.getValue()))) { // 2018/01/16 S21_NA#23237 MOD Condition For Bill w eq
                ZYPEZDItemValueSetter.setValue(dtlPMsg.origInvtyLocCd_A1, cpoDtl.origInvtyLocCd.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.origInvtyLocCd_A1, invBol.origInvtyLocCd.getValue()); // 2017/10/18 QC#21730 Add
            }
            // QC#22969 Mod End
        } else {
            /** Open Line **/
            // Copy CPO
            if (cpo != null) {
                EZDMsg.copy(cpo, "", dtlPMsg, "A1");
            }
            // Copy CPO_DTL
            if (cpoDtl != null) {
                EZDMsg.copy(cpoDtl, "", dtlPMsg, "A1");
            }
            dtlPMsg.crRebilCd_A1.clear();
        }

        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd_A1, RQST_TP_DTL_NEW);
        //QC#15624 mod Start
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineNum_A1, invLineMsg.cpoDtlLineNum_C1);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineSubNum_A1, invLineMsg.cpoDtlLineSubNum_C1);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineNum_A1, lineNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineSubNum_A1, lineSubNum);
        //QC#15624 mod End
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdPosnNum_A1, invLineMsg.dsOrdPosnNum_C1);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordLineSrcCd_A1, ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NWAL2300_RB_INTERNAL, bizMsg.glblCmpyCd.getValue()));

        ZYPEZDItemValueSetter.setValue(dtlPMsg.prcBaseDt_A1, pmsg.prcBaseDt);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.baseCmptFlg_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_A1, invLineMsg.ordQty_C1);
        if (!ZYPCommonFunc.hasValue(dtlPMsg.slsRepOrSlsTeamTocCd_A1)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_A1, slsRepCd);
        }

        if (!ZYPCommonFunc.hasValue(dtlPMsg.custUomCd_A1)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.custUomCd_A1, uomCd);
        }

        // QC#11538 START
        dtlPMsg.carrCd_A1.clear();
        if (cpoDtl != null && ZYPCommonFunc.hasValue(cpoDtl.carrCd)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.carrCd_A1, cpoDtl.carrCd.getValue());
        }

        dtlPMsg.carrAcctNum_A1.clear();
        if (cpoDtl != null && ZYPCommonFunc.hasValue(cpoDtl.carrAcctNum)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.carrAcctNum_A1, cpoDtl.carrAcctNum.getValue());
        }

        dtlPMsg.carrSvcLvlCd_A1.clear();
        if (cpoDtl != null && ZYPCommonFunc.hasValue(cpoDtl.carrSvcLvlCd)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.carrSvcLvlCd_A1, cpoDtl.carrSvcLvlCd.getValue());
        }

        // if (!ZYPCommonFunc.hasValue(dtlPMsg.carrAcctNum_A1)) {
        // DS_CPO_CONFIGTMsg dsCpoConfig =
        // getDS_CPO_CONFIGTMsg(bizMsg, invLineMsg);
        // String shipToCustAcctCd = null;
        // if (dsCpoConfig == null) {
        // NWZC150001_cpoConfigPMsg pmsgConfig =
        // getOrderConfigFromCredit(pmsg,
        // invLineMsg.dsOrdPosnNum_C1.getValue(),
        // invLineMsg.dsOrdLineDrctnCd_C1.getValue());
        // if (pmsgConfig != null) {
        // shipToCustAcctCd = pmsgConfig.shipToCustAcctCd.getValue();
        // }
        // } else {
        // shipToCustAcctCd = dsCpoConfig.shipToCustAcctCd.getValue();
        // }
        // NMZC611001PMsg nmzc611001PMsg =
        // callDefaultCarrierApi(bizMsg, bizMsg.glblCmpyCd.getValue(),
        // pmsg.slsDt.getValue(), shipToCustAcctCd);
        // if (ZYPCommonFunc.hasValue(nmzc611001PMsg.vndCd_O)) {
        // ZYPEZDItemValueSetter.setValue(dtlPMsg.carrAcctNum_A1,
        // nmzc611001PMsg.vndCd_O.getValue());
        // }
        // }

        // QC#11538 END

        if (!ZYPCommonFunc.hasValue(dtlPMsg.setItemShipCpltFlg_A1)) {
            if (ZYPCommonFunc.hasValue(setMdseCd)) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.setItemShipCpltFlg_A1, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.setItemShipCpltFlg_A1, ZYPConstant.FLG_OFF_N);
            }
        }

        ZYPEZDItemValueSetter.setValue(dtlPMsg.unitNetWt_A1, createInPoundWt(bizMsg.glblCmpyCd.getValue(), dtlPMsg.mdseCd_A1.getValue(), dtlPMsg.ordQty_A1.getValue()));

        if (!ZYPCommonFunc.hasValue(dtlPMsg.flPrcListCd_A1)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.flPrcListCd_A1, pmsg.flPrcListCd);
        }

        if (!ZYPCommonFunc.hasValue(dtlPMsg.dealPrcListPrcAmt_A1)) {
            // 2017/02/21 S21_NA#17416 Mod Start
//          ZYPEZDItemValueSetter.setValue(dtlPMsg.dealPrcListPrcAmt_A1
//          , new BigDecimal(getPrcCatgCd(bizMsg, bizMsg.glblCmpyCd.getValue(), NWZC157001.GET_PRICE_LIST, dt, PRC_CTX_TP.SALES_PRICING
//                  , pmsg.dsOrdCatgCd.getValue(), pmsg.dsOrdTpCd.getValue(), pmsg.sellToCustCd.getValue(), bizMsg.cpoOrdNum_H1.getValue())));

            ZYPEZDItemValueSetter.setValue(dtlPMsg.prcCatgCd_A1, getPrcCatgCd(bizMsg, bizMsg.glblCmpyCd.getValue(), NWZC157001.GET_PRICE_LIST, dt, PRC_CTX_TP.SALES_PRICING
                    , pmsg.dsOrdCatgCd.getValue(), pmsg.dsOrdTpCd.getValue(), pmsg.sellToCustCd.getValue(), bizMsg.cpoOrdNum_H1.getValue()));
            NWZC157001PMsg prcPMsg = new NWZC157001PMsg();
            setPricingApiPMsgHeaderForShell(bizMsg, pmsg, prcPMsg);
            setPricingApiPMsgDetailForShell(pmsg, dtlPMsg, prcPMsg);
            setPricingApiPMsgPriceFact(pmsg, dtlPMsg, prcPMsg);
            new NWZC157001().execute(prcPMsg, ONBATCH_TYPE.ONLINE);

            if (ZYPCommonFunc.hasValue(prcPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(0).autoPrcAmtRate)) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dealPrcListPrcAmt_A1, prcPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(0).autoPrcAmtRate);
                if (!ZYPCommonFunc.hasValue(dtlPMsg.funcPrcListPrcAmt_A1)) {
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_A1, prcPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(0).autoPrcAmtRate);
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(dtlPMsg.funcPrcListPrcAmt_A1)) {
            // 2017/02/21 S21_NA#17416 Mod
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_A1
//                    , new BigDecimal(getPrcCatgCd(bizMsg, bizMsg.glblCmpyCd.getValue(), NWZC157001.GET_PRICE_LIST, dt, PRC_CTX_TP.FLOOR_PRICE
//                            , pmsg.dsOrdCatgCd.getValue(), pmsg.dsOrdTpCd.getValue(), pmsg.sellToCustCd.getValue(), bizMsg.cpoOrdNum_H1.getValue())));

            ZYPEZDItemValueSetter.setValue(dtlPMsg.prcCatgCd_A1, getPrcCatgCd(bizMsg, bizMsg.glblCmpyCd.getValue(), NWZC157001.GET_PRICE_LIST, dt, PRC_CTX_TP.FLOOR_PRICE
                    , pmsg.dsOrdCatgCd.getValue(), pmsg.dsOrdTpCd.getValue(), pmsg.sellToCustCd.getValue(), bizMsg.cpoOrdNum_H1.getValue()));
            NWZC157001PMsg prcPMsg = new NWZC157001PMsg();
            setPricingApiPMsgHeaderForShell(bizMsg, pmsg, prcPMsg);
            setPricingApiPMsgDetailForShell(pmsg, dtlPMsg, prcPMsg);
            setPricingApiPMsgPriceFact(pmsg, dtlPMsg, prcPMsg);
            new NWZC157001().execute(prcPMsg, ONBATCH_TYPE.ONLINE);

            if (ZYPCommonFunc.hasValue(prcPMsg.NWZC157004PMsg.no(0).xxTotAmt)) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_A1, prcPMsg.NWZC157004PMsg.no(0).xxTotAmt);
            }

            // 2017/02/21 S21_NA#17416 Mod End
        }

        // QC#11538 START
        // if (!ZYPCommonFunc.hasValue(dtlPMsg.carrSvcLvlCd_A1)) {
        //     ZYPEZDItemValueSetter.setValue(dtlPMsg.carrSvcLvlCd_A1, pmsg.carrSvcLvlCd);
        // }
        // QC#11538 END

        if (!ZYPCommonFunc.hasValue(dtlPMsg.supdLockFlg_A1)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.supdLockFlg_A1, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_OFF_N.equals(invLineMsg.openFlg_C1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.frtCondCd_A1, pmsg.frtCondCd);

            // Add Start 2018/10/03 QC#28417
            if (CR_REBIL.CREDIT.equals(crRebilCd)) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoSrcTpCd_A1, CPO_SRC_TP.CREDIT);
            } else if (CR_REBIL.REBILL.equals(crRebilCd)) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoSrcTpCd_A1, CPO_SRC_TP.REBILL);
            }
            // Add End 2018/10/03 QC#28417
        }
        ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoOrdNum_A1, bizMsg.cpoOrdNum_H1);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.origInvNum_A1, invLineMsg.invNum_C1);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.loanBalQty_A1, BigDecimal.ZERO);
        if (!ZYPCommonFunc.hasValue(dtlPMsg.entDealNetUnitPrcAmt_A1)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.entDealNetUnitPrcAmt_A1, entDealNetUnitPrcAmt);
        }
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineNum_A1, invLineMsg.cpoDtlLineNum_C1);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineSubNum_A1, invLineMsg.cpoDtlLineSubNum_C1);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dplyLineRefNum_A1, NWAL2300CommonLogic.getDplyLineRefNum(dtlPMsg));

        dtlPMsg.origCpoOrdNum_A1.clear();
        dtlPMsg.origCpoDtlLineNum_A1.clear();
        dtlPMsg.origCpoDtlLineSubNum_A1.clear();

        // QC#12123
        ZYPEZDItemValueSetter.setValue(dtlPMsg.pmtTermCashDiscCd_A1, getPmtTermCashDiscCd(bizMsg, invLineMsg.pmtTermCashDiscCd_C1.getValue(), crRebilCd));

        // QC#12875 ADD
        if (ZYPConstant.FLG_OFF_N.equals(invLineMsg.openFlg_C1.getValue()) && invLine != null) {
            // Close Line
            if (invLine.mdseCd.getValue().equals(invLine.origOrCustMdseCd.getValue())) {

                ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, invLine.origOrCustMdseCd);
                ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_A1, invLine.origOrCustMdseCd);

            } else {
                // 2017/10/18 QC#21723 ADD START
                if (existManufactureItem(bizMsg, invLine.origOrCustMdseCd.getValue())) {
                    // Manufacture Item
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, invLine.mdseCd);
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_A1, invLine.origOrCustMdseCd);
                // 2017/10/18 QC#21723 ADD END
                } else if (existOrdTakeMdse(bizMsg, invLine.origOrCustMdseCd.getValue(), invLine.mdseCd.getValue())) {
                    // Order Taken Mdse
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, invLine.origOrCustMdseCd);
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_A1, invLine.origOrCustMdseCd);

                } else if (existCustMdseXref(bizMsg, invLine.origOrCustMdseCd.getValue())) {
                    // Customer Item
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, invLine.mdseCd);
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_A1, invLine.mdseCd);
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.custMdseCd_A1, invLine.origOrCustMdseCd);

                } else {
                    // Substitute Item
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, invLine.mdseCd);
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.sbstMdseCd_A1, invLine.mdseCd);
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_A1, invLine.origOrCustMdseCd);
                }
            }
        }
        //S21_NA ADD START QC#22118
        // Mod Start 2017/10/31 QC#22197
//        if(ZYPCommonFunc.hasValue(cpoDtl.dsCpoLineSubNum.getValue())){
        if (NWAL2300CommonLogic.isSetCompornentChild(bizMsg.glblCmpyCd.getValue(), invLineMsg)) {
            return;
        }
        // Mod End 2017/10/31 QC#22197
        //S21_NA ADD END QC#22118
        // Call Pricing API for Recalculation
        NWZC157001PMsg nwzc157001PMsg = new NWZC157001PMsg();
        setPricingApiPMsgHeader(bizMsg, pmsg, nwzc157001PMsg);
        setPricingApiPMsgDetail(pmsg, dtlPMsg, nwzc157001PMsg);
        setPricingApiPMsgPriceFact(pmsg, dtlPMsg, nwzc157001PMsg);

        new NWZC157001().execute(nwzc157001PMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(nwzc157001PMsg)) {
            bizMsg.setMessageInfo(//
                    nwzc157001PMsg.xxMsgIdList.no(0).xxMsgId.getValue() //
                    , new String[] {nwzc157001PMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() //
                        , nwzc157001PMsg.xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue() //
                        , nwzc157001PMsg.xxMsgIdList.no(0).xxMsgPrmTxt_2.getValue() } //
            );
        }
        if (S21ApiUtil.isXxMsgId(nwzc157001PMsg.NWZC157002PMsg.no(0))) {
            bizMsg.setMessageInfo(//
                    nwzc157001PMsg.NWZC157002PMsg.no(0).xxMsgIdList.no(0).xxMsgId.getValue() //
                    , new String[] {nwzc157001PMsg.NWZC157002PMsg.no(0).xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() //
                        , nwzc157001PMsg.NWZC157002PMsg.no(0).xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue() //
                        , nwzc157001PMsg.NWZC157002PMsg.no(0).xxMsgIdList.no(0).xxMsgPrmTxt_2.getValue() } //
            );
        }

        EZDMsg.copy(nwzc157001PMsg.NWZC157004PMsg.no(0), "", dtlPMsg, "A1");
        if (CR_REBIL.CREDIT.equals(crRebilCd)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.entDealNetUnitPrcAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.entDealNetUnitPrcAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotBaseAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.xxTotBaseAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotDiscAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.xxTotDiscAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotSpclPrcAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.xxTotSpclPrcAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotNetDiscAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.xxTotNetDiscAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotNetPrcAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.xxTotNetPrcAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotFrtAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.xxTotFrtAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotSpclChrgAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.xxTotSpclChrgAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotTaxAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.xxTotTaxAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxSlsAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.xxSlsAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.xxTotAmt_A1.getValue()));
            // QC#16568 2016/12/27 Add Start
            ZYPEZDItemValueSetter.setValue(dtlPMsg.funcSvcRevTrnsfAmt_A1, NWAL2300CommonLogic.negate(funcSvcRevTrnsfAmt));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dealSvcRevTrnsfAmt_A1, NWAL2300CommonLogic.negate(dealSvcRevTrnsfAmt));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.funcSvcCostTrnsfAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.funcSvcCostTrnsfAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dealSvcCostTrnsfAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.dealSvcCostTrnsfAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.funcManFlAdjAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.funcManFlAdjAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dealManFlAdjAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.dealManFlAdjAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.funcManRepRevAdjAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.funcManRepRevAdjAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dealManRepRevAdjAmt_A1, NWAL2300CommonLogic.negate(dtlPMsg.dealManRepRevAdjAmt_A1.getValue()));
            // QC#16568 2016/12/27 Add End
        }
    }

    // 2018/04/06 QC#22122 Mod Start
    //private void setOrderSalesCreditCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, List<String> procActions, String configCatgCd) {
    private void setOrderSalesCreditCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, List<String> procActions, String invNum, String configCatgCd) {
    // 2018/04/06 QC#22122 Mod End

        Set<String> procdPosnNums = new HashSet<String>();

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            // 2018/04/06 QC#22122 Add Start
            if (ZYPCommonFunc.hasValue(invNum) && !invNum.equals(bizMsg.C.no(i).invNum_C1.getValue())) {
                continue;
            }
            // 2018/04/06 QC#22122 Add End
            NWAL2300_CCMsg invLineMsg = bizMsg.C.no(i);
            if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLineMsg.dsOrdLineDrctnCd_C1.getValue())) {
                continue;
            }

            if (!procActions.contains(invLineMsg.xxTpCd_C1.getValue())) {
                continue;
            }

            String dsOrdPosnNum = invLineMsg.dsOrdPosnNum_C1.getValue() + invLineMsg.dsOrdLineDrctnCd_C1.getValue();
            if (procdPosnNums.contains(dsOrdPosnNum)) {
                continue;
            }
            // Add Start 2019/06/05 QC#50134
            if (NWAL2300CommonLogic.checkMdseRmaNotAllow(invLineMsg, bizMsg.glblCmpyCd.getValue())) {
                continue;
            }
            // Add End 2019/06/05 QC#50134
            setOrderSalesCredit(bizMsg, pmsg4Credit, invLineMsg, configCatgCd);
            procdPosnNums.add(dsOrdPosnNum);
        }
        // For Header
        if (0 < pmsg4Credit.cpoSlsCr.getValidCount() && !existOrderSalesCreditHeader(pmsg4Credit)) {
            setOrderSalesCreditHeader(bizMsg, pmsg4Credit);
        }
    }

    private void setOrderSalesCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg, NWAL2300_CCMsg invLineMsg, String configCatgCd) {

        if (!ZYPConstant.FLG_ON_Y.equals(invLineMsg.openFlg_C1.getValue())) {
            /** Closed Line */
            // Get Sales Credit from INV
            // 2017/12/25 QC#23081 Mod Start
//            DS_INV_SLS_CRTMsgArray dsInvSlsCrList = getDS_INV_SLS_CRTMsgList(bizMsg, invLineMsg);
            List<DS_INV_SLS_CRTMsg> dsInvSlsCrList = getDS_INV_SLS_CRTMsgList(bizMsg, invLineMsg);
            // 2017/12/25 QC#23081 Mod End
            if (dsInvSlsCrList != null) {
                // 2017/12/25 QC#23081 Mod Start
//                for (int i = 0; i < dsInvSlsCrList.getValidCount(); i++) {
//                    DS_INV_SLS_CRTMsg dsInvSlsCr = dsInvSlsCrList.no(i);
                for (DS_INV_SLS_CRTMsg dsInvSlsCr : dsInvSlsCrList) {
                    // 2017/12/25 QC#23081 Mod End
                    // 2017/12/25 QC#23081 Del Start
//                    // QC#16568 2016/12/27 Add Start
//                    if (S21StringUtil.isEquals(INV_LINE_SPL_TP.DEVIATION, dsInvSlsCr.invLineSplTpCd.getValue()) 
//                            || S21StringUtil.isEquals(INV_LINE_SPL_TP.ALLOCATION, dsInvSlsCr.invLineSplTpCd.getValue())) {
//                        continue;
//                    }
//                    // QC#16568 2016/12/27 Add End
                    // 2017/12/25 QC#23081 Del End
                    final NWZC150001_cpoSlsCrPMsg slsCrPMsg = pmsg.cpoSlsCr.no(pmsg.cpoSlsCr.getValidCount());
                    pmsg.cpoSlsCr.setValidCount(pmsg.cpoSlsCr.getValidCount() + 1);
                    // Copy
                    EZDMsg.copy(dsInvSlsCr, null, slsCrPMsg, null);
                    ZYPEZDItemValueSetter.setValue(slsCrPMsg.xxRqstTpCd, RQST_TP_SLS_CR_NEW);
                    ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsOrdPosnNum, invLineMsg.dsOrdPosnNum_C1);
                    ZYPEZDItemValueSetter.setValue(slsCrPMsg.configCatgCd, configCatgCd);
                    ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsCrQuotFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCd, dsInvSlsCr.slsRepTocCd);
                }
                // return;
            }

            // 2017/02/22 S21_NA#17683 Add Start
            DS_CPO_SLS_CRTMsgArray dsCpoSlsCrList = getDS_CPO_SLS_CRTMsg(bizMsg, invLineMsg);
            if (dsCpoSlsCrList != null) {
                for (int i = 0; i < dsCpoSlsCrList.getValidCount(); i++) {
                    DS_CPO_SLS_CRTMsg dsCpoSlsCr = dsCpoSlsCrList.no(i);
                    if (ZYPConstant.FLG_ON_Y.equals(dsCpoSlsCr.slsCrQuotFlg.getValue())) {
                        continue;
                    }

                    final NWZC150001_cpoSlsCrPMsg slsCrPMsg = pmsg.cpoSlsCr.no(pmsg.cpoSlsCr.getValidCount());
                    pmsg.cpoSlsCr.setValidCount(pmsg.cpoSlsCr.getValidCount() + 1);
                    // Copy
                    EZDMsg.copy(dsCpoSlsCr, null, slsCrPMsg, null);
                    ZYPEZDItemValueSetter.setValue(slsCrPMsg.xxRqstTpCd, RQST_TP_SLS_CR_NEW);
                    ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsOrdPosnNum, invLineMsg.dsOrdPosnNum_C1);
                    ZYPEZDItemValueSetter.setValue(slsCrPMsg.configCatgCd, configCatgCd);
                    ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCd, dsCpoSlsCr.slsRepTocCd);
                    slsCrPMsg.dsCpoConfigPk.clear();
                }
                return;
            }
            // 2017/02/22 S21_NA#17683 Add End
        } else {
            /** Open Line */
            // Get Sales Credit from CPO
            DS_CPO_SLS_CRTMsgArray dsCpoSlsCrList = getDS_CPO_SLS_CRTMsg(bizMsg, invLineMsg);
            if (dsCpoSlsCrList != null) {
                for (int i = 0; i < dsCpoSlsCrList.getValidCount(); i++) {
                    DS_CPO_SLS_CRTMsg dsCpoSlsCr = dsCpoSlsCrList.no(i);
                    final NWZC150001_cpoSlsCrPMsg slsCrPMsg = pmsg.cpoSlsCr.no(pmsg.cpoSlsCr.getValidCount());
                    pmsg.cpoSlsCr.setValidCount(pmsg.cpoSlsCr.getValidCount() + 1);
                    // Copy
                    EZDMsg.copy(dsCpoSlsCr, null, slsCrPMsg, null);
                    ZYPEZDItemValueSetter.setValue(slsCrPMsg.xxRqstTpCd, RQST_TP_SLS_CR_NEW);
                    ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsOrdPosnNum, invLineMsg.dsOrdPosnNum_C1);
                    ZYPEZDItemValueSetter.setValue(slsCrPMsg.configCatgCd, configCatgCd);
                    ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCd, dsCpoSlsCr.slsRepTocCd);
                }
                return;
            }
        }
    }

    private boolean existOrderSalesCreditHeader(NWZC150001PMsg pmsg) {

        for (int i = 0; i < pmsg.cpoSlsCr.getValidCount(); i++) {
            NWZC150001_cpoSlsCrPMsg slsCrPMsg = pmsg.cpoSlsCr.no(i);
            if (!ZYPCommonFunc.hasValue(slsCrPMsg.dsOrdPosnNum)) {
                return true;
            }
        }
        return false;
    }

    private void setOrderSalesCreditHeader(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg) {

        String firstPosnNum = "";
        List<NWZC150001_cpoSlsCrPMsg> slsCrList4Hdr = new ArrayList<NWZC150001_cpoSlsCrPMsg>();
        // Get Sales Credit of first postion config
        for (int i = 0; i < pmsg.cpoSlsCr.getValidCount(); i++) {
            NWZC150001_cpoSlsCrPMsg slsCrPMsg = pmsg.cpoSlsCr.no(i);
            if (!ZYPCommonFunc.hasValue(firstPosnNum)) {
                firstPosnNum = slsCrPMsg.dsOrdPosnNum.getValue();
            }
            if (firstPosnNum.equals(slsCrPMsg.dsOrdPosnNum.getValue())) {
                slsCrList4Hdr.add(slsCrPMsg);
            }
        }
        // Copy to Header
        for (NWZC150001_cpoSlsCrPMsg slsCr : slsCrList4Hdr) {
            NWZC150001_cpoSlsCrPMsg slsCrPMsg = pmsg.cpoSlsCr.no(pmsg.cpoSlsCr.getValidCount());
            pmsg.cpoSlsCr.setValidCount(pmsg.cpoSlsCr.getValidCount() + 1);
            EZDMsg.copy(slsCr, null, slsCrPMsg, null);
            slsCrPMsg.dsOrdPosnNum.clear();
            slsCrPMsg.configCatgCd.clear();
        }
    }

    // 2018/04/06 QC#22122 Mod Start
    //private void setOrderDiscCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, List<String> procActions, String configCatgCd) {
    private void setOrderDiscCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, List<String> procActions, String invNum, String configCatgCd) {
    // 2018/04/06 QC#22122 Mod End

        Set<String> procdPosnNums = new HashSet<String>();

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            // 2018/04/06 QC#22122 Add Start
            if (ZYPCommonFunc.hasValue(invNum) && !invNum.equals(bizMsg.C.no(i).invNum_C1.getValue())) {
                continue;
            }
            // 2018/04/06 QC#22122 Add End
            NWAL2300_CCMsg invLineMsg = bizMsg.C.no(i);
            if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLineMsg.dsOrdLineDrctnCd_C1.getValue())) {
                continue;
            }

            if (!procActions.contains(invLineMsg.xxTpCd_C1.getValue())) {
                continue;
            }

            String dsOrdPosnNum = invLineMsg.dsOrdPosnNum_C1.getValue();
            if (procdPosnNums.contains(dsOrdPosnNum)) {
                continue;
            }

            // Add Start 2019/06/05 QC#50134
            if (NWAL2300CommonLogic.checkMdseRmaNotAllow(invLineMsg, bizMsg.glblCmpyCd.getValue())) {
                continue;
            }
            // Add End 2019/06/05 QC#50134

            setOrderDeliveryInfoCredit(bizMsg, pmsg4Credit, invLineMsg, configCatgCd);
            setOrderSiteSurveyCredit(bizMsg, pmsg4Credit, invLineMsg, configCatgCd);
            setOrderInstallInfoCredit(bizMsg, pmsg4Credit, invLineMsg, configCatgCd);
            setOrderContactPersonCredit(bizMsg, pmsg4Credit, invLineMsg, configCatgCd);

            procdPosnNums.add(dsOrdPosnNum);
        }

        // Header
        if (0 < procdPosnNums.size()) {
            if (!existDeliveryInfoHeader(pmsg4Credit)) {
                setOrderDeliveryInfoHeaderCredit(bizMsg, pmsg4Credit);
            }
            if (!existSiteSurveyHeader(pmsg4Credit)) {
                setOrderSiteSurveyHeaderCredit(bizMsg, pmsg4Credit);
            }
            if (!existInstallInfoHeader(pmsg4Credit)) {
                setOrderInstallInfoHeaderCredit(bizMsg, pmsg4Credit);
            }
            if (!existContactPersonHeader(pmsg4Credit)) {
                setOrderContactPersonHeaderCredit(bizMsg, pmsg4Credit);
            }
        }
    }

    private void setOrderDeliveryInfoCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWAL2300_CCMsg invLineMsg, String configCatgCd) {

        DS_CPO_DELY_INFOTMsg delyInfo = getDS_CPO_DELY_INFOTMsgConfig(bizMsg, invLineMsg);
        if (delyInfo != null) {
            // Copy
            final NWZC150001_cpoDlvyInfoListPMsg cpoDlvyInfoListPMsg = pmsg4Credit.cpoDlvyInfoList.no(pmsg4Credit.cpoDlvyInfoList.getValidCount());
            pmsg4Credit.cpoDlvyInfoList.setValidCount(pmsg4Credit.cpoDlvyInfoList.getValidCount() + 1);
            EZDMsg.copy(delyInfo, null, cpoDlvyInfoListPMsg, null);

            ZYPEZDItemValueSetter.setValue(cpoDlvyInfoListPMsg.xxRqstTpCd, RQST_TP_DELY_INFO_NEW);
            ZYPEZDItemValueSetter.setValue(cpoDlvyInfoListPMsg.configCatgCd, configCatgCd);
            ZYPEZDItemValueSetter.setValue(cpoDlvyInfoListPMsg.dsOrdPosnNum, invLineMsg.dsOrdPosnNum_C1);
            cpoDlvyInfoListPMsg.dsCpoDelyInfoPk.clear();
        }
    }

    private void setOrderSiteSurveyCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWAL2300_CCMsg invLineMsg, String configCatgCd) {

        DS_SITE_SRVYTMsg siteSrvy = getDS_SITE_SRVYTMsgConfig(bizMsg, invLineMsg);
        if (siteSrvy != null) {
            // Copy
            final NWZC150001_siteSrvInfoListPMsg siteSrvInfoListPMsg = pmsg4Credit.siteSrvInfoList.no(pmsg4Credit.siteSrvInfoList.getValidCount());
            pmsg4Credit.siteSrvInfoList.setValidCount(pmsg4Credit.siteSrvInfoList.getValidCount() + 1);
            EZDMsg.copy(siteSrvy, null, siteSrvInfoListPMsg, null);

            ZYPEZDItemValueSetter.setValue(siteSrvInfoListPMsg.xxRqstTpCd, RQST_TP_SITE_SRVY_NEW);
            ZYPEZDItemValueSetter.setValue(siteSrvInfoListPMsg.configCatgCd, configCatgCd);
            ZYPEZDItemValueSetter.setValue(siteSrvInfoListPMsg.dsOrdPosnNum, invLineMsg.dsOrdPosnNum_C1);
            siteSrvInfoListPMsg.dsSiteSrvyPk.clear();
        }
    }

    private void setOrderInstallInfoCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWAL2300_CCMsg invLineMsg, String configCatgCd) {

        DS_CPO_ISTL_INFOTMsg istlInfo = getDS_CPO_ISTL_INFOTMsgConfig(bizMsg, invLineMsg);
        if (istlInfo != null) {
            // Copy
            final NWZC150001_cpoIstlInfoListPMsg cpoIstlInfoListPMsg = pmsg4Credit.cpoIstlInfoList.no(pmsg4Credit.cpoIstlInfoList.getValidCount());
            pmsg4Credit.cpoIstlInfoList.setValidCount(pmsg4Credit.cpoIstlInfoList.getValidCount() + 1);
            EZDMsg.copy(istlInfo, null, cpoIstlInfoListPMsg, null);

            ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg.xxRqstTpCd, RQST_TP_INSTL_INFO_NEW);
            ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg.configCatgCd, configCatgCd);
            ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg.dsOrdPosnNum, invLineMsg.dsOrdPosnNum_C1);
            cpoIstlInfoListPMsg.dsCpoIstlInfoPk.clear();
        }
    }

    private void setOrderContactPersonCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWAL2300_CCMsg invLineMsg, String configCatgCd) {

        DS_CPO_CTAC_PSNTMsgArray ctacPsnList = getDS_CPO_CTAC_PSNTMsgConfig(bizMsg, invLineMsg);
        if (ctacPsnList == null) {
            return;
        }
        for (int i = 0; i < ctacPsnList.getValidCount(); i++) {
            DS_CPO_CTAC_PSNTMsg ctacPsn = ctacPsnList.no(i);
            if (ctacPsn != null) {
                // Copy
                final NWZC150001_cpoCtacPsnInfoListPMsg cpoCtacPsnInfoListPMsg = pmsg4Credit.cpoCtacPsnInfoList.no(pmsg4Credit.cpoCtacPsnInfoList.getValidCount());
                pmsg4Credit.cpoCtacPsnInfoList.setValidCount(pmsg4Credit.cpoCtacPsnInfoList.getValidCount() + 1);
                EZDMsg.copy(ctacPsn, null, cpoCtacPsnInfoListPMsg, null);

                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.xxRqstTpCd, RQST_TP_CTAC_PSN_NEW);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.configCatgCd, configCatgCd);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.ctacTpCd, ctacPsn.ctacPsnTpCd);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.firstNm, ctacPsn.ctacPsnFirstNm);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.lastNm, ctacPsn.ctacPsnLastNm);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.emlAddr, ctacPsn.ctacPsnEmlAddr);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.telNum, ctacPsn.ctacPsnTelNum);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.faxNum, ctacPsn.ctacPsnFaxNum);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.sortNum, ctacPsn.ctacPsnSortNum);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.ctacPsnExtnNum, ctacPsn.ctacPsnExtnNum);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.dsOrdPosnNum, invLineMsg.dsOrdPosnNum_C1);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.ctacCustRefTpCd, ctacPsn.ctacCustRefTpCd); // QC#16452 Add
                cpoCtacPsnInfoListPMsg.dsCpoCtacPsnPk.clear();
            }
        }
    }

    private boolean existDeliveryInfoHeader(NWZC150001PMsg pmsg) {

        for (int i = 0; i < pmsg.cpoDlvyInfoList.getValidCount(); i++) {
            NWZC150001_cpoDlvyInfoListPMsg cpoDlvyInfoPMsg = pmsg.cpoDlvyInfoList.no(i);
            if (!ZYPCommonFunc.hasValue(cpoDlvyInfoPMsg.dsCpoConfigPk)) {
                return true;
            }
        }
        return false;
    }

    private void setOrderDeliveryInfoHeaderCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit) {

        DS_CPO_DELY_INFOTMsg delyInfo = getDS_CPO_DELY_INFOTMsgHeader(bizMsg);
        if (delyInfo != null) {
            // Copy
            final NWZC150001_cpoDlvyInfoListPMsg cpoDlvyInfoListPMsg = pmsg4Credit.cpoDlvyInfoList.no(pmsg4Credit.cpoDlvyInfoList.getValidCount());
            pmsg4Credit.cpoDlvyInfoList.setValidCount(pmsg4Credit.cpoDlvyInfoList.getValidCount() + 1);
            EZDMsg.copy(delyInfo, null, cpoDlvyInfoListPMsg, null);

            ZYPEZDItemValueSetter.setValue(cpoDlvyInfoListPMsg.xxRqstTpCd, RQST_TP_DELY_INFO_NEW);
            cpoDlvyInfoListPMsg.dsCpoDelyInfoPk.clear();
        }
    }

    private boolean existSiteSurveyHeader(NWZC150001PMsg pmsg) {

        for (int i = 0; i < pmsg.siteSrvInfoList.getValidCount(); i++) {
            NWZC150001_siteSrvInfoListPMsg siteSrvInfoPMsg = pmsg.siteSrvInfoList.no(i);
            if (!ZYPCommonFunc.hasValue(siteSrvInfoPMsg.dsCpoConfigPk)) {
                return true;
            }
        }
        return false;
    }

    private void setOrderSiteSurveyHeaderCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit) {

        DS_SITE_SRVYTMsg siteSrvy = getDS_SITE_SRVYTMsgHeader(bizMsg);
        if (siteSrvy != null) {
            // Copy
            final NWZC150001_siteSrvInfoListPMsg siteSrvInfoListPMsg = pmsg4Credit.siteSrvInfoList.no(pmsg4Credit.siteSrvInfoList.getValidCount());
            pmsg4Credit.siteSrvInfoList.setValidCount(pmsg4Credit.siteSrvInfoList.getValidCount() + 1);
            EZDMsg.copy(siteSrvy, null, siteSrvInfoListPMsg, null);

            ZYPEZDItemValueSetter.setValue(siteSrvInfoListPMsg.xxRqstTpCd, RQST_TP_SITE_SRVY_NEW);
            siteSrvInfoListPMsg.dsSiteSrvyPk.clear();
        }
    }

    private boolean existInstallInfoHeader(NWZC150001PMsg pmsg) {

        for (int i = 0; i < pmsg.cpoIstlInfoList.getValidCount(); i++) {
            NWZC150001_cpoIstlInfoListPMsg cpoIstlInfoPMsg = pmsg.cpoIstlInfoList.no(i);
            if (!ZYPCommonFunc.hasValue(cpoIstlInfoPMsg.dsCpoConfigPk)) {
                return true;
            }
        }
        return false;
    }

    private void setOrderInstallInfoHeaderCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit) {

        DS_CPO_ISTL_INFOTMsg istlInfo = getDS_CPO_ISTL_INFOTMsgHeader(bizMsg);
        if (istlInfo != null) {
            // Copy
            final NWZC150001_cpoIstlInfoListPMsg cpoIstlInfoListPMsg = pmsg4Credit.cpoIstlInfoList.no(pmsg4Credit.cpoIstlInfoList.getValidCount());
            pmsg4Credit.cpoIstlInfoList.setValidCount(pmsg4Credit.cpoIstlInfoList.getValidCount() + 1);
            EZDMsg.copy(istlInfo, null, cpoIstlInfoListPMsg, null);

            ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg.xxRqstTpCd, RQST_TP_INSTL_INFO_NEW);
            cpoIstlInfoListPMsg.dsCpoIstlInfoPk.clear();
        }
    }

    private boolean existContactPersonHeader(NWZC150001PMsg pmsg) {

        for (int i = 0; i < pmsg.cpoCtacPsnInfoList.getValidCount(); i++) {
            NWZC150001_cpoCtacPsnInfoListPMsg cpoCtacPsnInfo = pmsg.cpoCtacPsnInfoList.no(i);
            if (!ZYPCommonFunc.hasValue(cpoCtacPsnInfo.dsCpoConfigPk)) {
                return true;
            }
        }
        return false;
    }

    private void setOrderContactPersonHeaderCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit) {

        DS_CPO_CTAC_PSNTMsgArray ctacPsnList = getDS_CPO_CTAC_PSNTMsgHeader(bizMsg);
        if (ctacPsnList == null) {
            return;
        }
        for (int i = 0; i < ctacPsnList.getValidCount(); i++) {
            DS_CPO_CTAC_PSNTMsg ctacPsn = ctacPsnList.no(i);
            if (ctacPsn != null) {
                // Copy
                final NWZC150001_cpoCtacPsnInfoListPMsg cpoCtacPsnInfoListPMsg = pmsg4Credit.cpoCtacPsnInfoList.no(pmsg4Credit.cpoCtacPsnInfoList.getValidCount());
                pmsg4Credit.cpoCtacPsnInfoList.setValidCount(pmsg4Credit.cpoCtacPsnInfoList.getValidCount() + 1);
                EZDMsg.copy(ctacPsn, null, cpoCtacPsnInfoListPMsg, null);

                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.xxRqstTpCd, RQST_TP_CTAC_PSN_NEW);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.ctacTpCd, ctacPsn.ctacPsnTpCd);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.firstNm, ctacPsn.ctacPsnFirstNm);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.lastNm, ctacPsn.ctacPsnLastNm);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.emlAddr, ctacPsn.ctacPsnEmlAddr);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.telNum, ctacPsn.ctacPsnTelNum);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.faxNum, ctacPsn.ctacPsnFaxNum);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.sortNum, ctacPsn.ctacPsnSortNum);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.ctacPsnExtnNum, ctacPsn.ctacPsnExtnNum);
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.ctacCustRefTpCd, ctacPsn.ctacCustRefTpCd); // QC#16452 Add
                cpoCtacPsnInfoListPMsg.dsCpoCtacPsnPk.clear();
            }
        }
    }

    // 2018/04/06 QC#22122 Mod Start
    //private void setReturnDetailPrcCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4RMACredit, List<String> procActions, Set<String> procdInvNums4FrtFact) {
    private void setReturnDetailPrcCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4RMACredit, List<String> procActions, Set<String> procdInvNums4FrtFact, String invoiceNumber) {
    // 2018/04/06 QC#22122 Mod End

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            // 2018/04/06 QC#22122 Add Start
            if (ZYPCommonFunc.hasValue(invoiceNumber) && !invoiceNumber.equals(bizMsg.C.no(i).invNum_C1.getValue())) {
                continue;
            }
            // 2018/04/06 QC#22122 Add End
            NWAL2300_CCMsg invLineMsg = bizMsg.C.no(i);
            if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLineMsg.dsOrdLineDrctnCd_C1.getValue())) {
                continue;
            }

            if (!procActions.contains(invLineMsg.xxTpCd_C1.getValue())) {
                continue;
            }
            // Add Start 2019/06/05 QC#50134
            if (NWAL2300CommonLogic.checkMdseRmaNotAllow(invLineMsg, bizMsg.glblCmpyCd.getValue())) {
                continue;
            }
            // Add End 2019/06/05 QC#50134
            S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getPriceElementOutbound(bizMsg, invLineMsg);
            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> rstMapList = (List<Map<String, Object>>) ssmResult.getResultObject();

                for (Map<String, Object> rstMap : rstMapList) {

                    // 2018/05/29 QC#21841 Del Start
//                    // BASE
//                    final NWZC150001_rtnPriceListPMsg basePrcPMsg = pmsg4RMACredit.rtnPriceList.no(pmsg4RMACredit.rtnPriceList.getValidCount());
//                    pmsg4RMACredit.rtnPriceList.setValidCount(pmsg4RMACredit.rtnPriceList.getValidCount() + 1);
//
//                    // QC#22106 2018/03/30 Add Start
//                    // ZYPEZDItemValueSetter.setValue(basePrcPMsg.cpoDtlLineNum, (String) rstMap.get("CPO_DTL_LINE_NUM"));
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.cpoDtlLineNum, invLineMsg.cpoDtlLineNum_T);
//                    // QC#22106 2018/03/30 Add End
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.cpoDtlLineSubNum, (String) rstMap.get("CPO_DTL_LINE_SUB_NUM"));
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondTpCd, PRC_COND_TP.BASE_PRICE);
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.BASE_PRICE);
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.pkgUomCd, (String) rstMap.get("UOM_CD"));
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondUnitCd, PRC_COND_UNIT.AMT);
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.BASE_PRICE));
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.autoPrcAmtRate, (BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"));
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.manPrcAmtRate, (BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"));
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_NET_AMT"))));
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.unitPrcAmt, (BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"));
//                    ZYPEZDItemValueSetter.setValue(basePrcPMsg.autoPrcCcyCd, (String) rstMap.get("DEAL_CCY_CD"));
//                    //QC#15624 add Start
//                    //if (!ZYPCommonFunc.hasValue(basePrcPMsg.cpoDtlLineNum)) {
//                    //    ZYPEZDItemValueSetter.setValue(basePrcPMsg.cpoDtlLineNum, String.valueOf(i + 1));
//                    //}
//                    //QC#15624 add End
//
//                    // BASE TAX
//                    final NWZC150001_rtnPriceListPMsg baseTaxPrcPMsg = pmsg4RMACredit.rtnPriceList.no(pmsg4RMACredit.rtnPriceList.getValidCount());
//                    pmsg4RMACredit.rtnPriceList.setValidCount(pmsg4RMACredit.rtnPriceList.getValidCount() + 1);
//                    EZDMsg.copy(basePrcPMsg, null, baseTaxPrcPMsg, null);
//
//                    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.prcCondTpCd, PRC_COND_TP.ITEM_TAX1);
//                    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.TAX);
//                    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.ITEM_TAX1));
//                    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.autoPrcAmtRate, (BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"));
//                    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.manPrcAmtRate, (BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"));
//                    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
//                    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.unitPrcAmt, (BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"));
//
//                    String invNum = (String) rstMap.get("INV_NUM");
//                    if (procdInvNums4FrtFact.contains(invNum)) {
//                        continue;
//                    } else {
//                        procdInvNums4FrtFact.add(invNum);
//                    }
//
//                    if (BigDecimal.ZERO.compareTo((BigDecimal) rstMap.get("SHIP_DEAL_FRT_AMT")) == 0 && BigDecimal.ZERO.compareTo((BigDecimal) rstMap.get("FRT_DEAL_TAX_AMT")) == 0) {
//                        // When Freight Amt is Zero, Skip. 
//                        continue;
//                    }
//                    // FRT
//                    final NWZC150001_rtnPriceListPMsg frtPrcPMsg = pmsg4RMACredit.rtnPriceList.no(pmsg4RMACredit.rtnPriceList.getValidCount());
//                    pmsg4RMACredit.rtnPriceList.setValidCount(pmsg4RMACredit.rtnPriceList.getValidCount() + 1);
//                    EZDMsg.copy(basePrcPMsg, null, frtPrcPMsg, null);
//
//                    frtPrcPMsg.prcCondTpCd.clear();
//                    ZYPEZDItemValueSetter.setValue(frtPrcPMsg.prcCondTpCd, PRC_COND_TP.CREDIT_REBILL_FREIGHT);
//                    ZYPEZDItemValueSetter.setValue(frtPrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.FREIGHT);
//                    ZYPEZDItemValueSetter.setValue(frtPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.CREDIT_REBILL_FREIGHT));
//                    ZYPEZDItemValueSetter.setValue(frtPrcPMsg.autoPrcAmtRate, (BigDecimal) rstMap.get("SHIP_DEAL_FRT_AMT"));
//                    ZYPEZDItemValueSetter.setValue(frtPrcPMsg.manPrcAmtRate, (BigDecimal) rstMap.get("SHIP_DEAL_FRT_AMT"));
//                    ZYPEZDItemValueSetter.setValue(frtPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("SHIP_DEAL_FRT_AMT"))));
//                    ZYPEZDItemValueSetter.setValue(frtPrcPMsg.unitPrcAmt, (BigDecimal) rstMap.get("SHIP_DEAL_FRT_AMT"));
//
//                    // FRT TAX
//                    final NWZC150001_rtnPriceListPMsg frtTaxPrcPMsg = pmsg4RMACredit.rtnPriceList.no(pmsg4RMACredit.rtnPriceList.getValidCount());
//                    pmsg4RMACredit.rtnPriceList.setValidCount(pmsg4RMACredit.rtnPriceList.getValidCount() + 1);
//                    EZDMsg.copy(basePrcPMsg, null, frtTaxPrcPMsg, null);
//
//                    ZYPEZDItemValueSetter.setValue(frtTaxPrcPMsg.prcCondTpCd, PRC_COND_TP.FREIGHT_TAX1);
//                    ZYPEZDItemValueSetter.setValue(frtTaxPrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.TAX);
//                    ZYPEZDItemValueSetter.setValue(frtTaxPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.FREIGHT_TAX1));
//                    ZYPEZDItemValueSetter.setValue(frtTaxPrcPMsg.autoPrcAmtRate, (BigDecimal) rstMap.get("FRT_DEAL_TAX_AMT"));
//                    ZYPEZDItemValueSetter.setValue(frtTaxPrcPMsg.manPrcAmtRate, (BigDecimal) rstMap.get("FRT_DEAL_TAX_AMT"));
//                    ZYPEZDItemValueSetter.setValue(frtTaxPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("FRT_DEAL_TAX_AMT"))));
//                    ZYPEZDItemValueSetter.setValue(frtTaxPrcPMsg.unitPrcAmt, (BigDecimal) rstMap.get("FRT_DEAL_TAX_AMT"));
                    // 2018/05/29 QC#21841 Del End

                    // 2018/05/29 QC#21841 Add Start
                    setPrcCalcData(rstMap, bizMsg, invLineMsg, pmsg4RMACredit, SET_PRC_DATA_MODE_RETURN_CREDIT);
                    // 2018/05/29 QC#21841 Add End
                }
            }
        }
    }

    // 2018/04/06 QC#22122 Mod Start
    //private void setReturnDetailCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4RMACredit, List<String> procActions) {
    private void setReturnDetailCredit(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4RMACredit, List<String> procActions, String invNum) {
    // 2018/04/06 QC#22122 Mod End

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            // 2018/04/06 QC#22122 Add Start
            if (ZYPCommonFunc.hasValue(invNum) && !invNum.equals(bizMsg.C.no(i).invNum_C1.getValue())) {
                continue;
            }
            // 2018/04/06 QC#22122 Add End
            NWAL2300_CCMsg invLineMsg = bizMsg.C.no(i);
            // QC#22106 2018/03/30 Add Start
            //QC#15624 add Start
            //String lineNum = invLineMsg.cpoDtlLineNum_C1.getValue();
            String lineNum = invLineMsg.cpoDtlLineNum_T.getValue();
            String lineSubNum = invLineMsg.cpoDtlLineSubNum_C1.getValue();
            //if (!ZYPCommonFunc.hasValue(lineNum)) {
            //    // bill with equipment
            //    lineNum = String.valueOf(i + 1);
            //    lineSubNum = DEF_LINE_SUB_NUM;
            //}
            // QC#22106 2018/03/30 Add End
            if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLineMsg.dsOrdLineDrctnCd_C1.getValue())) {
                continue;
            }

            if (!procActions.contains(invLineMsg.xxTpCd_C1.getValue())) {
                continue;
            }
            // Add Start 2019/06/05 QC#50134
            if (NWAL2300CommonLogic.checkMdseRmaNotAllow(invLineMsg, bizMsg.glblCmpyCd.getValue())) {
                continue;
            }
            // Add End 2019/06/05 QC#50134
//            setOrderDetailReturn(bizMsg, pmsg4RMACredit, invLineMsg, CR_REBIL.CREDIT);  //QC#15624 mod
            setOrderDetailReturn(bizMsg, pmsg4RMACredit, invLineMsg, CR_REBIL.CREDIT, lineNum, lineSubNum);
        }
    }

    // Set Return Detail
//    private void setOrderDetailReturn(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg, NWAL2300_CCMsg invLineMsg, String crRebilCd) {  //QC#15624 mod
    private void setOrderDetailReturn(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg, NWAL2300_CCMsg invLineMsg, String crRebilCd, String lineNum, String lineSubNum) {

        final NWZC150001_rtnDtlPMsg dtlPMsg = pmsg.rtnDtl.no(pmsg.rtnDtl.getValidCount());
        pmsg.rtnDtl.setValidCount(pmsg.rtnDtl.getValidCount() + 1);

        String dt = bizMsg.slsDt.getValue();
        String shipFromInvtyLocCd = null;
        String uomCd = null;
        String slsRepCd = null;
        String serNum = null;

        if (ZYPConstant.FLG_OFF_N.equals(invLineMsg.openFlg_C1.getValue())) {
            /** Closed Line **/
            // Copy INV
            // 2018/04/06 QC#22122 Mod Start
            //INVTMsg inv = getINVTMsg(bizMsg, invLineMsg);
            INVTMsg inv = getINVTMsg(bizMsg, invLineMsg, null);
            // 2018/04/06 QC#22122 Mod End
            if (inv != null) {
                EZDMsg.copy(inv, "", dtlPMsg, "B1");
            }
            // Copy INV_BOL
            INV_BOLTMsg invBol = getINV_BOLTMsg(bizMsg, invLineMsg);
            if (invBol != null) {
                EZDMsg.copy(invBol, "", dtlPMsg, "B1");
            }
            // Copy INV_LINE
            INV_LINETMsg invLine = getINV_LINETMsg(bizMsg, invLineMsg);
            if (invLine != null) {
                EZDMsg.copy(invLine, "", dtlPMsg, "B1");

                // QC#19925 ADD START
                if (!ZYPCommonFunc.hasValue(invLine.cpoDtlLineNum)) {
                    dtlPMsg.svcMachMstrPk_B1.clear();
                }
                // QC#19925 ADD END
                // 2018/08/13 S21_NA#27718 Add Start
                ZYPEZDItemValueSetter.setValue(dtlPMsg.origInvLineNum_B1, invLine.invLineNum);
                ZYPEZDItemValueSetter.setValue(dtlPMsg.origInvLineSubNum_B1, invLine.invLineSubNum);
                // 2018/08/13 S21_NA#27718 Add End
            }

            // When CPO exists
                // QC#12569 MOD START
                // Get CPO_DTL
                // original order line is outbound then from CPO_DTL

            if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(invLineMsg.dsOrdLineDrctnCd_C1.getValue())) {
                CPO_DTLTMsg cpoDtl = geCPO_DTLTMsg(bizMsg, invLineMsg);
                if (cpoDtl != null) {
                    // QC#19711 MOD START
//                    EZDMsg.copy(cpoDtl, "", dtlPMsg, "B1");
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.svcConfigMstrPk_B1, cpoDtl.svcConfigMstrPk);
                    // QC#19711 MOD END
                }

            } else {
                // original order line is inbound then from DS_CPO_RTRN_DTL
                // Get DS_CPO_RTRN_DTL
                DS_CPO_RTRN_DTLTMsg cpoRtrnDtl = geDS_CPO_RTRN_DTLTMsg(bizMsg, invLineMsg);
                if (cpoRtrnDtl != null) {
                    // QC#19711 MOD START
//                    EZDMsg.copy(cpoRtrnDtl, "", dtlPMsg, "B1");
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.svcConfigMstrPk_B1, cpoRtrnDtl.svcConfigMstrPk);
                    // QC#19711 MOD END
                }
            }
                // QC#12569 MOD END

            dt = getDt(bizMsg, inv.invDt.getValue(), pmsg.crRebilRsnCatgCd.getValue(), crRebilCd); // QC#12892 MOD
            shipFromInvtyLocCd = invBol.shipFromInvtyLocCd.getValue();
            uomCd = invLine.uomCd.getValue();
            slsRepCd = invLine.slsRepTocCd.getValue();

        } else {
            /** Open Line **/
            // Copy CPO
            CPOTMsg cpo = getCPOTMsg(bizMsg);
            if (cpo != null) {
                EZDMsg.copy(cpo, "", dtlPMsg, "B1");
            }
            // Copy DS_CPO_RTRN_DTL
            DS_CPO_RTRN_DTLTMsg cpoRtrnDtl = geDS_CPO_RTRN_DTLTMsg(bizMsg, invLineMsg);
            if (cpoRtrnDtl != null) {
                EZDMsg.copy(cpoRtrnDtl, "", dtlPMsg, "B1");
                serNum = dtlPMsg.serNum_B1.getValue();
            }
        }

        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd_B1, RQST_TP_DTL_NEW);
        //QC#15624 mod Start
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineNum_B1, invLineMsg.cpoDtlLineNum_C1);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineSubNum_B1, invLineMsg.cpoDtlLineSubNum_C1);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineNum_B1, lineNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineSubNum_B1, lineSubNum);
        //QC#15624 mod End

        //L3#430 QC#16437 Mod Start
        if (ZYPConstant.FLG_OFF_N.equals(invLineMsg.openFlg_C1.getValue()) && CR_REBIL.CREDIT.equals(crRebilCd)) {
            String inBoundOrdLineCatgCd = NWAL2300Query.getInstance().getInBoundLineCatg(pmsg, dtlPMsg);

            if (ZYPCommonFunc.hasValue(inBoundOrdLineCatgCd)) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_B1, inBoundOrdLineCatgCd);
            }

            // Add Start 2018/10/03 QC#28417
            ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoSrcTpCd_B1, CPO_SRC_TP.CREDIT);
            // Add End 2018/10/03 QC#28417
        }
        //L3#430 QC#16437 Mod End

        dtlPMsg.machConfigNum_B1.clear();
        if (!ZYPCommonFunc.hasValue(dtlPMsg.slsRepOrSlsTeamTocCd_B1)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_B1, slsRepCd);
        }

        if (!ZYPCommonFunc.hasValue(dtlPMsg.custUomCd_B1)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.custUomCd_B1, uomCd);
        }

        ZYPEZDItemValueSetter.setValue(dtlPMsg.lineNetWt_B1, createInPoundWt(pmsg.glblCmpyCd.getValue()//
                , dtlPMsg.mdseCd_B1.getValue()//
                , dtlPMsg.ordQty_B1.getValue()));
        if (ZYPConstant.FLG_ON_Y.equals(invLineMsg.openFlg_C1.getValue())) {
            // Open Line
            ZYPEZDItemValueSetter.setValue(dtlPMsg.serNum_B1, serNum);
        } else {
            // Closed Line 
            ZYPEZDItemValueSetter.setValue(dtlPMsg.serNum_B1, getSerNum(pmsg, invLineMsg));
        }

        if (!ZYPCommonFunc.hasValue(dtlPMsg.svcMachMstrPk_B1)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.svcMachMstrPk_B1, getSvcMachMstrPk(pmsg, dtlPMsg));
        }
        dtlPMsg.dsCpoConfigPk_B1.clear();
        dtlPMsg.ordLineSrcCd_B1.clear();

        // 2018/12/19 S21_NA#29561 Del Start
        // 2017/01/17 QC#16691 ADD START
//        if (checkIntangibleWh(bizMsg.glblCmpyCd.getValue(), dtlPMsg.mdseCd_B1.getValue(), bizMsg.cpoOrdNum_H1.getValue(), dtlPMsg.rtlWhCd_B1.getValue())) {
//            // case not Vender drop ship , intangible , not dummy wh , not set
//            dtlPMsg.rtlWhCd_B1.clear();
//            dtlPMsg.rtlSwhCd_B1.clear();
//            dtlPMsg.invtyLocCd_B1.clear();
//        } else {
//        // 2017/01/17 QC#16691 ADD E N D
//            // case of drop ship then WH/SWH will be populated by
//            // NWZC1800 Default WH API
//
//            // QC#21521 mod start
////            if (ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_DROP_SHIP_RTL_WH_CD, bizMsg.glblCmpyCd.getValue()).equals(dtlPMsg.rtlWhCd_B1.getValue())) {
//            NWZC180001PMsg whPMsg = getDefultWhSwhCd(bizMsg, pmsg, dtlPMsg);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_B1, whPMsg.rtlWhCd.getValue());
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlSwhCd_B1, whPMsg.rtlSwhCd.getValue());
//            if (!ZYPCommonFunc.hasValue(dtlPMsg.rtlWhCd_B1) && NWAL2300CommonLogic.isVendor(shipFromInvtyLocCd)) {
//                return;
//            }
//
////            } else {
//                if (!ZYPCommonFunc.hasValue(dtlPMsg.rtlWhCd_B1) && ZYPCommonFunc.hasValue(shipFromInvtyLocCd)) {
//                    ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_B1, getRtlWhCd(pmsg, shipFromInvtyLocCd));
//                }
//                ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlSwhCd_B1, getRtlSwhCd(bizMsg, pmsg, dtlPMsg));
////            }
//            if (!ZYPCommonFunc.hasValue(dtlPMsg.rtlWhCd_B1)) {
//                return;
//            }
//                // QC#21521 mod end
//
//            if (ZYPCommonFunc.hasValue(dtlPMsg.rtlSwhCd_B1)) {
//                ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_B1, dtlPMsg.rtlWhCd_B1.getValue() + dtlPMsg.rtlSwhCd_B1.getValue());
//            } else {
//                ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_B1, dtlPMsg.rtlWhCd_B1.getValue());
//            }
//        // 2017/01/17 QC#16691 ADD START
//        }
        // 2017/01/17 QC#16691 ADD E N D
        // 2018/12/19 S21_NA#29561 Del End

        if (!ZYPCommonFunc.hasValue(dtlPMsg.flPrcListCd_B1)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.flPrcListCd_B1, pmsg.flPrcListCd);
        }
        if (!ZYPCommonFunc.hasValue(dtlPMsg.dealPrcListPrcAmt_B1)) {
//            // 2017/02/21 S21_NA#17416 Mod Start
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.dealPrcListPrcAmt_B1, new BigDecimal(getPrcCatgCd(bizMsg, pmsg.glblCmpyCd.getValue()//
//                    , NWZC157001.GET_LINE_PRICE//
//                    , dt//
//                    , PRC_CTX_TP.SALES_PRICING//
//                    , pmsg.dsOrdCatgCd.getValue()//
//                    , pmsg.dsOrdTpCd.getValue()//
//                    , pmsg.sellToCustCd.getValue()//
//                    , bizMsg.cpoOrdNum_H1.getValue())));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.prcCatgCd_B1, getPrcCatgCd(bizMsg, bizMsg.glblCmpyCd.getValue(), NWZC157001.GET_PRICE_LIST, dt, PRC_CTX_TP.SALES_PRICING
                    , pmsg.dsOrdCatgCd.getValue(), pmsg.dsOrdTpCd.getValue(), pmsg.sellToCustCd.getValue(), bizMsg.cpoOrdNum_H1.getValue()));
            NWZC157001PMsg prcPMsg = new NWZC157001PMsg();
            setPricingApiPMsgHeaderForShell(bizMsg, pmsg, prcPMsg);
            setPricingApiPMsgDetailForShell(pmsg, dtlPMsg, prcPMsg);
            setPricingApiPMsgPriceFact(pmsg, dtlPMsg, prcPMsg);
            new NWZC157001().execute(prcPMsg, ONBATCH_TYPE.ONLINE);

            if (ZYPCommonFunc.hasValue(prcPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(0).autoPrcAmtRate)) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dealPrcListPrcAmt_B1, prcPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(0).autoPrcAmtRate);
                if (!ZYPCommonFunc.hasValue(dtlPMsg.funcPrcListPrcAmt_B1)) {
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_B1, prcPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(0).autoPrcAmtRate);
                }
            }
            // 2017/02/21 S21_NA#17416 Mod End
        }
        //QC#17874 mod Start
//        if (ZYPConstant.FLG_OFF_N.equals(invLineMsg.openFlg_C1.getValue())) {
//            // Close Line
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.crRebilCd_B1, crRebilCd);
//        } else {
//            // Opne Line
//            dtlPMsg.crRebilCd_B1.clear();
//        }
        dtlPMsg.crRebilCd_B1.clear();
        //QC#17874 mod End
        if (!ZYPCommonFunc.hasValue(dtlPMsg.prcBaseDt_B1)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.prcBaseDt_B1, pmsg.prcBaseDt);
        }
        dtlPMsg.ordUpldRefNum_B1.clear();
        dtlPMsg.hddRmvCd_B1.clear();
        // ZYPEZDItemValueSetter.setValue(dtlPMsg.rtrnRsnCd_B1, RTRN_RSN.REGULAR_RETURN); // 2018/12/19 S21_NA#29561 Del
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtrnRsnCd_B1, "");
        dtlPMsg.thirdPtyDspTpCd_B1.clear();
        if (BigDecimal.ZERO.compareTo(invLineMsg.ordQty_C1.getValue()) < 0) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_B1, NWAL2300CommonLogic.negate(invLineMsg.ordQty_C1.getValue()));
        } else {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_B1, invLineMsg.ordQty_C1);
        }
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordCustUomQty_B1, dtlPMsg.ordQty_B1);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtrnQty_B1, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoOrdNum_B1, bizMsg.cpoOrdNum_H1);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.origInvNum_B1, invLineMsg.invNum_C1);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineNum_B1, invLineMsg.cpoDtlLineNum_C1);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineSubNum_B1, invLineMsg.cpoDtlLineSubNum_C1);

        dtlPMsg.origCpoOrdNum_B1.clear();
        dtlPMsg.origCpoDtlLineNum_B1.clear();
        dtlPMsg.origCpoDtlLineSubNum_B1.clear();
        // QC#12123
        ZYPEZDItemValueSetter.setValue(dtlPMsg.pmtTermCd_B1, getPmtTermCd(bizMsg, invLineMsg.pmtTermCashDiscCd_C1.getValue(), crRebilCd));

        // Call Pricing API for Recalculation
        NWZC157001PMsg nwzc157001PMsg = new NWZC157001PMsg();
        setPricingApiPMsgHeader(bizMsg, pmsg, nwzc157001PMsg);
        setPricingApiPMsgDetail(pmsg, dtlPMsg, nwzc157001PMsg);
        setPricingApiPMsgPriceFact(pmsg, dtlPMsg, nwzc157001PMsg);

        new NWZC157001().execute(nwzc157001PMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(nwzc157001PMsg)) {
            bizMsg.setMessageInfo(//
                    nwzc157001PMsg.xxMsgIdList.no(0).xxMsgId.getValue() //
                    , new String[] {nwzc157001PMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() //
                        , nwzc157001PMsg.xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue() //
                        , nwzc157001PMsg.xxMsgIdList.no(0).xxMsgPrmTxt_2.getValue() } //
            );
        }
        if (S21ApiUtil.isXxMsgId(nwzc157001PMsg.NWZC157002PMsg.no(0))) {
            bizMsg.setMessageInfo(//
                    nwzc157001PMsg.NWZC157002PMsg.no(0).xxMsgIdList.no(0).xxMsgId.getValue() //
                    , new String[] {nwzc157001PMsg.NWZC157002PMsg.no(0).xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() //
                        , nwzc157001PMsg.NWZC157002PMsg.no(0).xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue() //
                        , nwzc157001PMsg.NWZC157002PMsg.no(0).xxMsgIdList.no(0).xxMsgPrmTxt_2.getValue() } //
            );
        }

        EZDMsg.copy(nwzc157001PMsg.NWZC157004PMsg.no(0), "", dtlPMsg, "B1");
    }

    private void setPricingApiPMsgHeader(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg, NWZC157001PMsg nwzc157001PMsg) {

        ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        nwzc157001PMsg.xxModeCd.setValue(NWZC157001.RECALC);
        ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.prcBaseDt, pmsg.prcBaseDt);
        nwzc157001PMsg.prcCtxTpCd.setValue(PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.dsOrdCatgCd, pmsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.dsOrdTpCd, pmsg.dsOrdTpCd);
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getLineBizTpCd(bizMsg, bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), bizMsg.cpoOrdNum_H1.getValue());
        if (ssmResult.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.lineBizTpCd, (String) ssmResult.getResultObject());
        }
        ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.dsAcctNum, pmsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.taxFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
    }

    private void setPricingApiPMsgHeaderForShell(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg, NWZC157001PMsg nwzc157001PMsg) {

        ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        nwzc157001PMsg.xxModeCd.setValue(NWZC157001.GET_LINE_PRICE);
        ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.prcBaseDt, pmsg.prcBaseDt);
        nwzc157001PMsg.prcCtxTpCd.setValue(PRC_CTX_TP.SERVICE_PRICING);
        ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.dsOrdCatgCd, pmsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.dsOrdTpCd, pmsg.dsOrdTpCd);
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getLineBizTpCd(bizMsg, bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), bizMsg.cpoOrdNum_H1.getValue());
        if (ssmResult.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.lineBizTpCd, (String) ssmResult.getResultObject());
        }
        ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.dsAcctNum, pmsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.taxFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(nwzc157001PMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
    }

    private void setPricingApiPMsgDetail(NWZC150001PMsg pmsg, NWZC150001_APMsg dtlPMsg, NWZC157001PMsg nwzc157001PMsg) {

        NWZC150001_cpoConfigPMsg cpoConfigPMsg = null;
        for (int i = 0; i < pmsg.cpoConfig.getValidCount(); i++) {
            cpoConfigPMsg = pmsg.cpoConfig.no(i);
            if (dtlPMsg.dsOrdPosnNum_A1.getValue().equals(cpoConfigPMsg.dsOrdPosnNum.getValue())) {
                break;
            }
        }

        NWZC157002PMsg dtlPrcApiPMsg = nwzc157001PMsg.NWZC157002PMsg.no(0);
        nwzc157001PMsg.NWZC157002PMsg.setValidCount(1);

        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.trxLineNum, dtlPMsg.cpoDtlLineNum_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.trxLineSubNum, dtlPMsg.cpoDtlLineSubNum_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.billToCustCd, cpoConfigPMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.shipToCustCd, dtlPMsg.shipToCustCd_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.dsAcctNum_SH, cpoConfigPMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.dsAcctNum_BL, cpoConfigPMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.mdseCd, dtlPMsg.mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.pkgUomCd, dtlPMsg.custUomCd_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.ordQty, dtlPMsg.ordQty_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.ordCustUomQty, dtlPMsg.ordCustUomQty_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.invQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.ctyAddr_SH, dtlPMsg.shipToCtyAddr_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.stCd_SH, dtlPMsg.shipToStCd_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.cntyNm_SH, dtlPMsg.shipToCntyNm_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.postCd_SH, dtlPMsg.shipToPostCd_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.ctryCd_SH, dtlPMsg.shipToCtryCd_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
        // 2017/10/18 QC#21730 Add Start
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.slsRepOrSlsTeamTocCd, dtlPMsg.slsRepOrSlsTeamTocCd_A1);
        String origInvtyLocCd = dtlPMsg.origInvtyLocCd_A1.getValue();
        // QC#22031 2017/10/31 Mod Start
        //if (origInvtyLocCd.length() > 2) {
        //    ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.rtlWhCd, origInvtyLocCd.substring(0, 3));
        //}
        // 2017/10/18 QC#21730 Add End
        if (isVendorCode(pmsg.glblCmpyCd.getValue(), origInvtyLocCd)) {
            ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.rtlWhCd, DROP_SHIP_WH);
        } else {
            ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.rtlWhCd, NWAL2300Query.getInstance().getRtlWh(pmsg.glblCmpyCd.getValue(), origInvtyLocCd));
        }
        // QC#22031 2017/10/31 Mod End

    }

    private void setPricingApiPMsgDetailForShell(NWZC150001PMsg pmsg, NWZC150001_APMsg dtlPMsg, NWZC157001PMsg nwzc157001PMsg) {

        NWZC150001_cpoConfigPMsg cpoConfigPMsg = null;
        for (int i = 0; i < pmsg.cpoConfig.getValidCount(); i++) {
            cpoConfigPMsg = pmsg.cpoConfig.no(i);
            if (dtlPMsg.dsOrdPosnNum_A1.getValue().equals(cpoConfigPMsg.dsOrdPosnNum.getValue())) {
                break;
            }
        }

        NWZC157002PMsg dtlPrcApiPMsg = nwzc157001PMsg.NWZC157002PMsg.no(0);
        nwzc157001PMsg.NWZC157002PMsg.setValidCount(1);

        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.trxLineNum, dtlPMsg.cpoDtlLineNum_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.trxLineSubNum, dtlPMsg.cpoDtlLineSubNum_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.billToCustCd, cpoConfigPMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.shipToCustCd, dtlPMsg.shipToCustCd_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.dsAcctNum_SH, cpoConfigPMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.dsAcctNum_BL, cpoConfigPMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.mdseCd, dtlPMsg.mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.pkgUomCd, dtlPMsg.custUomCd_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.ordQty, dtlPMsg.ordQty_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.ordCustUomQty, dtlPMsg.ordCustUomQty_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.invQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.ctyAddr_SH, dtlPMsg.shipToCtyAddr_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.stCd_SH, dtlPMsg.shipToStCd_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.cntyNm_SH, dtlPMsg.shipToCntyNm_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.postCd_SH, dtlPMsg.shipToPostCd_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.ctryCd_SH, dtlPMsg.shipToCtryCd_A1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.prcCatgCd, dtlPMsg.prcCatgCd_A1);
    }

    private void setPricingApiPMsgDetail(NWZC150001PMsg pmsg, NWZC150001_rtnDtlPMsg dtlPMsg, NWZC157001PMsg nwzc157001PMsg) {

        NWZC150001_cpoConfigPMsg cpoConfigPMsg = null;
        for (int i = 0; i < pmsg.cpoConfig.getValidCount(); i++) {
            cpoConfigPMsg = pmsg.cpoConfig.no(i);
            if (dtlPMsg.dsOrdPosnNum_B1.getValue().equals(cpoConfigPMsg.dsOrdPosnNum.getValue())) {
                break;
            }
        }

        NWZC157002PMsg dtlPrcApiPMsg = nwzc157001PMsg.NWZC157002PMsg.no(0);
        nwzc157001PMsg.NWZC157002PMsg.setValidCount(1);

        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.trxLineNum, dtlPMsg.cpoDtlLineNum_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.trxLineSubNum, dtlPMsg.cpoDtlLineSubNum_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.billToCustCd, cpoConfigPMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.shipToCustCd, dtlPMsg.shipToCustCd_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.dsAcctNum_SH, cpoConfigPMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.dsAcctNum_BL, cpoConfigPMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.mdseCd, dtlPMsg.mdseCd_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.pkgUomCd, dtlPMsg.custUomCd_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.ordQty, dtlPMsg.ordQty_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.ordCustUomQty, dtlPMsg.ordCustUomQty_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.invQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.ctyAddr_SH, dtlPMsg.shipToCtyAddr_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.stCd_SH, dtlPMsg.shipToStCd_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.cntyNm_SH, dtlPMsg.shipToCntyNm_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.postCd_SH, dtlPMsg.shipToPostCd_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.ctryCd_SH, dtlPMsg.shipToCtryCd_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
    }

    private void setPricingApiPMsgDetailForShell(NWZC150001PMsg pmsg, NWZC150001_rtnDtlPMsg dtlPMsg, NWZC157001PMsg nwzc157001PMsg) {

        NWZC150001_cpoConfigPMsg cpoConfigPMsg = null;
        for (int i = 0; i < pmsg.cpoConfig.getValidCount(); i++) {
            cpoConfigPMsg = pmsg.cpoConfig.no(i);
            if (dtlPMsg.dsOrdPosnNum_B1.getValue().equals(cpoConfigPMsg.dsOrdPosnNum.getValue())) {
                break;
            }
        }

        NWZC157002PMsg dtlPrcApiPMsg = nwzc157001PMsg.NWZC157002PMsg.no(0);
        nwzc157001PMsg.NWZC157002PMsg.setValidCount(1);

        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.trxLineNum, dtlPMsg.cpoDtlLineNum_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.trxLineSubNum, dtlPMsg.cpoDtlLineSubNum_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.billToCustCd, cpoConfigPMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.shipToCustCd, dtlPMsg.shipToCustCd_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.dsAcctNum_SH, cpoConfigPMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.dsAcctNum_BL, cpoConfigPMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.mdseCd, dtlPMsg.mdseCd_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.pkgUomCd, dtlPMsg.custUomCd_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.ordQty, dtlPMsg.ordQty_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.ordCustUomQty, dtlPMsg.ordCustUomQty_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.invQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.ctyAddr_SH, dtlPMsg.shipToCtyAddr_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.stCd_SH, dtlPMsg.shipToStCd_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.cntyNm_SH, dtlPMsg.shipToCntyNm_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.postCd_SH, dtlPMsg.shipToPostCd_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.ctryCd_SH, dtlPMsg.shipToCtryCd_B1);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtlPrcApiPMsg.prcCatgCd, dtlPMsg.prcCatgCd_B1);
    }

    private void setPricingApiPMsgPriceFact(NWZC150001PMsg pmsg, NWZC150001_APMsg dtlPMsg, NWZC157001PMsg nwzc157001PMsg) {

        int factIndex = 0;
        for (int i = 0; i < pmsg.priceList.getValidCount(); i++) {
            NWZC150001_priceListPMsg priceListPMsg = pmsg.priceList.no(i);
            if (dtlPMsg.cpoDtlLineNum_A1.getValue().equals(priceListPMsg.cpoDtlLineNum.getValue())) {

                NWZC157003PMsg prcFactApiPMsg = nwzc157001PMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(factIndex++);
                nwzc157001PMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.setValidCount(factIndex);
                EZDMsg.copy(priceListPMsg, null, prcFactApiPMsg, null);

                prcFactApiPMsg.ordPrcCalcBasePk.clear();
                ZYPEZDItemValueSetter.setValue(prcFactApiPMsg.trxLineNum, priceListPMsg.cpoDtlLineNum);
                ZYPEZDItemValueSetter.setValue(prcFactApiPMsg.trxLineSubNum, priceListPMsg.cpoDtlLineSubNum);
            }
        }
    }

    private void setPricingApiPMsgPriceFact(NWZC150001PMsg pmsg, NWZC150001_rtnDtlPMsg dtlPMsg, NWZC157001PMsg nwzc157001PMsg) {

        int factIndex = 0;
        for (int i = 0; i < pmsg.priceList.getValidCount(); i++) {
            NWZC150001_priceListPMsg priceListPMsg = pmsg.priceList.no(i);
            if (dtlPMsg.cpoDtlLineNum_B1.getValue().equals(priceListPMsg.cpoDtlLineNum.getValue())) {

                NWZC157003PMsg prcFactApiPMsg = nwzc157001PMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(factIndex++);
                nwzc157001PMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.setValidCount(factIndex);
                EZDMsg.copy(priceListPMsg, null, prcFactApiPMsg, null);

                prcFactApiPMsg.ordPrcCalcBasePk.clear();
                ZYPEZDItemValueSetter.setValue(prcFactApiPMsg.trxLineNum, priceListPMsg.cpoDtlLineNum);
                ZYPEZDItemValueSetter.setValue(prcFactApiPMsg.trxLineSubNum, priceListPMsg.cpoDtlLineSubNum);
            }
        }
    }

    private void numberingLineNum(NWZC150001PMsg pMsg) {

        /***************************************************************************
         * Original Position# Sort and Numbering
         ***************************************************************************/
        List<Integer> sortOrigPosiObjOut = new ArrayList<Integer>();
        List<Integer> sortOrigPosiObjIn = new ArrayList<Integer>();
        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            final NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(i);
            if (CONFIG_CATG.OUTBOUND.equals(cpoConfigPMsg.configCatgCd.getValue())) {
                sortOrigPosiObjOut.add(Integer.parseInt(cpoConfigPMsg.dsOrdPosnNum.getValue()));
            } else {
                sortOrigPosiObjIn.add(Integer.parseInt(cpoConfigPMsg.dsOrdPosnNum.getValue()));
            }
        }
        Collections.sort(sortOrigPosiObjOut);
        Collections.sort(sortOrigPosiObjIn);

        int posnNum = 0;
        Map<String, String> posnNumMapOut = new LinkedHashMap<String, String>();
        for (int origPositionNum : sortOrigPosiObjOut) {
            posnNum++;
            posnNumMapOut.put(Integer.toString(origPositionNum), Integer.toString(posnNum));
        }

        posnNum = 0;
        Map<String, String> posnNumMapIn = new LinkedHashMap<String, String>();
        for (int origPositionNum : sortOrigPosiObjIn) {
            posnNum++;
            posnNumMapIn.put(Integer.toString(origPositionNum), Integer.toString(posnNum));
        }

        /***************************************************************************
         * DS_ORD_POSN_NUM
         ***************************************************************************/
        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            final NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(i);
            if (CONFIG_CATG.OUTBOUND.equals(cpoConfigPMsg.configCatgCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsOrdPosnNum, posnNumMapOut.get(cpoConfigPMsg.dsOrdPosnNum.getValue()));
            } else {
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsOrdPosnNum, posnNumMapIn.get(cpoConfigPMsg.dsOrdPosnNum.getValue()));
            }
        }

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            final NWZC150001_APMsg dtlPMsg = pMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdPosnNum_A1, posnNumMapOut.get(dtlPMsg.dsOrdPosnNum_A1.getValue()));
        }

        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            final NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.dsOrdPosnNum_B1, posnNumMapIn.get(rtnDtlPMsg.dsOrdPosnNum_B1.getValue()));
        }

        for (int i = 0; i < pMsg.cpoSlsCr.getValidCount(); i++) {
            final NWZC150001_cpoSlsCrPMsg slsCrPMsg = pMsg.cpoSlsCr.no(i);
            if (CONFIG_CATG.OUTBOUND.equals(slsCrPMsg.configCatgCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsOrdPosnNum, posnNumMapOut.get(slsCrPMsg.dsOrdPosnNum.getValue()));
            } else {
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.dsOrdPosnNum, posnNumMapIn.get(slsCrPMsg.dsOrdPosnNum.getValue()));
            }
        }

        for (int i = 0; i < pMsg.cpoDlvyInfoList.getValidCount(); i++) {
            final NWZC150001_cpoDlvyInfoListPMsg cpoDlvyInfoListPMsg = pMsg.cpoDlvyInfoList.no(i);
            if (CONFIG_CATG.OUTBOUND.equals(cpoDlvyInfoListPMsg.configCatgCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(cpoDlvyInfoListPMsg.dsOrdPosnNum, posnNumMapOut.get(cpoDlvyInfoListPMsg.dsOrdPosnNum.getValue()));
            } else {
                ZYPEZDItemValueSetter.setValue(cpoDlvyInfoListPMsg.dsOrdPosnNum, posnNumMapIn.get(cpoDlvyInfoListPMsg.dsOrdPosnNum.getValue()));
            }
        }

        for (int i = 0; i < pMsg.siteSrvInfoList.getValidCount(); i++) {
            final NWZC150001_siteSrvInfoListPMsg siteSrvInfoListPMsg = pMsg.siteSrvInfoList.no(i);
            if (CONFIG_CATG.OUTBOUND.equals(siteSrvInfoListPMsg.configCatgCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(siteSrvInfoListPMsg.dsOrdPosnNum, posnNumMapOut.get(siteSrvInfoListPMsg.dsOrdPosnNum.getValue()));
            } else {
                ZYPEZDItemValueSetter.setValue(siteSrvInfoListPMsg.dsOrdPosnNum, posnNumMapIn.get(siteSrvInfoListPMsg.dsOrdPosnNum.getValue()));
            }
        }

        for (int i = 0; i < pMsg.cpoIstlInfoList.getValidCount(); i++) {
            final NWZC150001_cpoIstlInfoListPMsg cpoIstlInfoListPMsg = pMsg.cpoIstlInfoList.no(i);
            if (CONFIG_CATG.OUTBOUND.equals(cpoIstlInfoListPMsg.configCatgCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg.dsOrdPosnNum, posnNumMapOut.get(cpoIstlInfoListPMsg.dsOrdPosnNum.getValue()));
            } else {
                ZYPEZDItemValueSetter.setValue(cpoIstlInfoListPMsg.dsOrdPosnNum, posnNumMapIn.get(cpoIstlInfoListPMsg.dsOrdPosnNum.getValue()));
            }
        }

        for (int i = 0; i < pMsg.cpoCtacPsnInfoList.getValidCount(); i++) {
            final NWZC150001_cpoCtacPsnInfoListPMsg cpoCtacPsnInfoListPMsg = pMsg.cpoCtacPsnInfoList.no(i);
            if (CONFIG_CATG.OUTBOUND.equals(cpoCtacPsnInfoListPMsg.configCatgCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.dsOrdPosnNum, posnNumMapOut.get(cpoCtacPsnInfoListPMsg.dsOrdPosnNum.getValue()));
            } else {
                ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfoListPMsg.dsOrdPosnNum, posnNumMapIn.get(cpoCtacPsnInfoListPMsg.dsOrdPosnNum.getValue()));
            }
        }

        /***************************************************************************
         * DS_CPO_LINE_NUM Numbering (DS_CPO_LINE_SUB_NUM is keep)
         ***************************************************************************/
        // Outbound
        Map<String, BigDecimal> dsCpoLineNumMapOut = new LinkedHashMap<String, BigDecimal>();
        Set<String> procdPosnNumsOut = new HashSet<String>();
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            final NWZC150001_APMsg dtlPMsg = pMsg.A.no(i);
            String dsOrdPosnNum = dtlPMsg.dsOrdPosnNum_A1.getValue();
            if (!procdPosnNumsOut.contains(dsOrdPosnNum)) {
                dsCpoLineNumMapOut.put(dsOrdPosnNum, BigDecimal.ONE);
            } else if (ZYPCommonFunc.hasValue(dtlPMsg.dsCpoLineSubNum_A1)) {
                // Keep DS_CPO_LINE_NUM
            } else {
                BigDecimal newDsCpoLineNum = dsCpoLineNumMapOut.get(dsOrdPosnNum);
                dsCpoLineNumMapOut.put(dsOrdPosnNum, newDsCpoLineNum.add(BigDecimal.ONE));
            }
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoLineNum_A1, dsCpoLineNumMapOut.get(dsOrdPosnNum));
            procdPosnNumsOut.add(dsOrdPosnNum);
        }

        // Inbound
        Map<String, BigDecimal> dsCpoLineNumMapIn = new LinkedHashMap<String, BigDecimal>();
        Set<String> procdPosnNumsIn = new HashSet<String>();
        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            final NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
            String dsOrdPosnNum = rtnDtlPMsg.dsOrdPosnNum_B1.getValue();
            if (!procdPosnNumsIn.contains(dsOrdPosnNum)) {
                dsCpoLineNumMapIn.put(dsOrdPosnNum, BigDecimal.ONE);
            } else if (ZYPCommonFunc.hasValue(rtnDtlPMsg.dsCpoLineSubNum_B1)) {
             // Keep DS_CPO_LINE_NUM
            } else {
                BigDecimal newDsCpoLineNum = dsCpoLineNumMapIn.get(dsOrdPosnNum);
                dsCpoLineNumMapIn.put(dsOrdPosnNum, newDsCpoLineNum.add(BigDecimal.ONE));
            }
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.dsCpoLineNum_B1, dsCpoLineNumMapIn.get(dsOrdPosnNum));
            procdPosnNumsIn.add(dsOrdPosnNum);
        }

        /***************************************************************************
         * CPO_DTL_LINE_NUM (CPO_DTL_LINE_SUB_NUM is keep)
         ***************************************************************************/
        // Outbound
        String cpoDtlLineNum = "000";
        Map<String, String> cpoDtlLineNumMapOut = new LinkedHashMap<String, String>();

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            final NWZC150001_APMsg dtlPMsg = pMsg.A.no(i);
            if (!cpoDtlLineNumMapOut.containsKey(dtlPMsg.cpoDtlLineNum_A1.getValue())) {
                cpoDtlLineNum = NWAL2300CommonLogic.getNextCpoDtlLineNum(cpoDtlLineNum);
                cpoDtlLineNumMapOut.put(dtlPMsg.cpoDtlLineNum_A1.getValue(), cpoDtlLineNum);
            }
            ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineNum_A1, cpoDtlLineNumMapOut.get(dtlPMsg.cpoDtlLineNum_A1.getValue()));
        }
        for (int i = 0; i < pMsg.priceList.getValidCount(); i++) {
            final NWZC150001_priceListPMsg priceListPMsg = pMsg.priceList.no(i);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineNum, cpoDtlLineNumMapOut.get(priceListPMsg.cpoDtlLineNum.getValue()));
        }

        // Inbound
        cpoDtlLineNum = "000";
        Map<String, String> cpoDtlLineNumMapIn = new LinkedHashMap<String, String>();

        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            final NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
            if (!cpoDtlLineNumMapIn.containsKey(rtnDtlPMsg.cpoDtlLineNum_B1.getValue())) {
                cpoDtlLineNum = NWAL2300CommonLogic.getNextCpoDtlLineNum(cpoDtlLineNum);
                cpoDtlLineNumMapIn.put(rtnDtlPMsg.cpoDtlLineNum_B1.getValue(), cpoDtlLineNum);
            }

            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.cpoDtlLineNum_B1, cpoDtlLineNumMapIn.get(rtnDtlPMsg.cpoDtlLineNum_B1.getValue()));
        }
        for (int i = 0; i < pMsg.rtnPriceList.getValidCount(); i++) {
            final NWZC150001_rtnPriceListPMsg rtnPriceListPMsg = pMsg.rtnPriceList.no(i);
            ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.cpoDtlLineNum, cpoDtlLineNumMapIn.get(rtnPriceListPMsg.cpoDtlLineNum.getValue()));
        }

        /***************************************************************************
         * REF_CPO_DTL_LINE_NUM / REF_CPO_DTL_LINE_NUM / DPLY_LINE_REF_NUM
         ***************************************************************************/
        List<NWZC150001_APMsg> baseCompList = NWAL2300CommonLogic.getBaseCompList(pMsg.A);
        // Outbound
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWAL2300CommonLogic.setRefCpoDtlLineNum(baseCompList, pMsg.A.no(i));
        }

        // Inbound
        List<NWZC150001_rtnDtlPMsg> baseCompListRtrn = NWAL2300CommonLogic.getBaseCompListRtrn(pMsg.rtnDtl);
        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWAL2300CommonLogic.setRefCpoDtlLineNumRtrn(baseCompListRtrn, pMsg.rtnDtl.no(i));
        }
    }
    // 2018/04/06 QC#22122 Mod Start
    //private void setDsCpoUpdateApiParam4Rebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4Rebill, NSZC115001PMsg shellPmsg) {
    private void setDsCpoUpdateApiParam4Rebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4Rebill, NSZC115001PMsg shellPmsg, String invNum) {
    // 2018/04/06 QC#22122 Mod End

        setOrderHeaderRebill(bizMsg, pmsg4Credit, pmsg4Rebill);
        // 2018/04/06 QC#22122 Mod Start
        //setOrderConfigRebill(bizMsg, pmsg4Credit, pmsg4Rebill);
        //setOrderDetailPrcRebill(bizMsg, pmsg4Credit, pmsg4Rebill);
        //setOrderDetailRebill(bizMsg, pmsg4Credit, pmsg4Rebill);
        //setOrderSalesCreditRebill(bizMsg, pmsg4Credit, pmsg4Rebill);
        //setOrderDiscRebill(bizMsg, pmsg4Credit, pmsg4Rebill);
        setOrderConfigRebill(bizMsg, pmsg4Credit, pmsg4Rebill, invNum);
        setOrderDetailPrcRebill(bizMsg, pmsg4Credit, pmsg4Rebill, invNum);
        setOrderDetailRebill(bizMsg, pmsg4Credit, pmsg4Rebill, invNum);
        setOrderSalesCreditRebill(bizMsg, pmsg4Credit, pmsg4Rebill, invNum);
        setOrderDiscRebill(bizMsg, pmsg4Credit, pmsg4Rebill, invNum);
        // 2018/04/06 QCC#22122 Mod End

        // 2018/01/22 S21_NA#23321 Add Start
        if (isExistNegoDealAmt(bizMsg)) {
            setNegoDealAmtForCredit(pmsg4Rebill, BigDecimal.valueOf(1));
        }
        // 2018/01/22 S21_NA#23321 Add End

        // QC#19943
        setCrRebilRsnCatg(pmsg4Rebill);

        // Shell
        // 2018/04/06 QC#22122 Mod Start
        //setShellRebill(bizMsg, pmsg4Rebill, shellPmsg);
        if (!ZYPCommonFunc.hasValue(invNum)) {
            setShellRebill(bizMsg, pmsg4Rebill, shellPmsg);
        }
        // 2018/04/06 QC#22122 Mod End

        numberingLineNum(pmsg4Rebill);

    }

    // QC#19943 ADD START
    private void setCrRebilRsnCatg(NWZC150001PMsg pmsg4Rebill) {
        for (int i = 0; i < pmsg4Rebill.A.getValidCount(); i++) {
            NWZC150001_APMsg dtlPMsg = pmsg4Rebill.A.no(i);
            if (ZYPCommonFunc.hasValue(dtlPMsg.crRebilCd_A1)) {
                return;
            }
        }
        pmsg4Rebill.crRebilRsnCatgCd.clear();
    }
    // QC#19943 ADD END

    private void setOrderHeaderRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4Rebill) {
        EZDMsg.copy(pmsg4Credit, null, pmsg4Rebill, null);
        ZYPEZDItemValueSetter.setValue(pmsg4Rebill.xxModeCd, MODE_SAVE);
        // QC#12123
        ZYPEZDItemValueSetter.setValue(pmsg4Rebill.addPmtTermCashDiscCd, getPmtTermCashDiscCd(bizMsg, bizMsg.pmtTermCashDiscCd_H1.getValue(), CR_REBIL.REBILL));
        // Add Start 2017/10/20 QC#21773
        ZYPEZDItemValueSetter.setValue(pmsg4Rebill.cpoSrcTpCd, CPO_SRC_TP.REBILL);
        // Add End 2017/10/20 QC#21773

        // QC#12892 ADD START
        INVTMsgArray invList = getINVTMsgList(bizMsg);
        if (invList != null) {
            String dt = getDt(bizMsg, invList.no(0).invDt.getValue(), pmsg4Rebill.crRebilRsnCatgCd.getValue(), CR_REBIL.REBILL);
            ZYPEZDItemValueSetter.setValue(pmsg4Rebill.prcBaseDt, dt);
            ZYPEZDItemValueSetter.setValue(pmsg4Rebill.prcCalcDt, dt);
        }
        // QC#12892 ADD END
        pmsg4Rebill.dsPmtMethCd.clear();
        pmsg4Rebill.dsCrCardPk.clear();
        // QC#19718
        // Mod Start 2017/10/16 QC#21592
        // 2018/01/22 S21_NA#23321 Del Start, Moved to setDsCpoUpdateApiParam4Rebill(...)
//        pmsg4Rebill.negoDealAmt.clear();
//        if (isExistNegoDealAmt(bizMsg)) {
//            setNegoDealAmtForCredit(pmsg4Rebill, BigDecimal.valueOf(-1));
//        }
        // 2018/01/22 S21_NA#23321 Del End
        // Mod End 2017/10/16 QC#21592
    }

    //2018/04/06 QC#22122 Mod Start
    //private void setOrderConfigRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4Rebill) {
    private void setOrderConfigRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4Rebill, String invNum) {
    // 2018/04/06 QC#22122 Mod End

        // Clear
        pmsg4Rebill.cpoConfig.clear();
        ZYPTableUtil.clear(pmsg4Rebill.cpoConfig);

        // Add Start 2018/10/03 QC#28417
        boolean isRetailEquipOrder = NWXC150001DsCheck.isRetailEquipOrder( //
                pmsg4Rebill.glblCmpyCd.getValue(), //
                pmsg4Rebill.dsOrdCatgCd.getValue(), //
                pmsg4Rebill.dsOrdTpCd.getValue(), //
                pmsg4Rebill.dsOrdRsnCd.getValue());
        // Add End 2018/10/03 QC#28417

        Set<String> procdPosnNums = new HashSet<String>();
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            // 2018/04/06 QC#22122 Add Start
            if (ZYPCommonFunc.hasValue(invNum) && !invNum.equals(bizMsg.C.no(i).invNum_C1.getValue())) {
                continue;
            }
            // 2018/04/06 QC#22122 Add End
            NWAL2300_CCMsg invLine = bizMsg.C.no(i);
            String procdPosnNum = invLine.dsOrdPosnNum_C1.getValue() + invLine.dsOrdLineDrctnCd_C1.getValue();
            if (procdPosnNums.contains(procdPosnNum)) {
                continue;
            }
            if (PULLDOWN_CD_C_R.equals(invLine.xxTpCd_C1.getValue())) {
                // Outbound Close
                NWZC150001_cpoConfigPMsg pmsgConfig4Credit = getOrderConfigFromCredit(pmsg4Credit, invLine.dsOrdPosnNum_C1.getValue(), invLine.dsOrdLineDrctnCd_C1.getValue());
                // Copy from Credit info
                EZDMsg.copy(pmsgConfig4Credit, null, pmsg4Rebill.cpoConfig.no(pmsg4Rebill.cpoConfig.getValidCount()), null);
                // Update original value
                NWZC150001_cpoConfigPMsg pmsgConfig4Rebill = pmsg4Rebill.cpoConfig.no(pmsg4Rebill.cpoConfig.getValidCount());

                // Mod Start 2018/10/03 QC#28417
                //if (existSvcConfigMstr(bizMsg, pmsgConfig4Rebill.svcConfigMstrPk.getValue())) {
                if (existSvcConfigMstr(bizMsg, pmsgConfig4Rebill.svcConfigMstrPk.getValue()) && isRetailEquipOrder) {
                    // Mod End 2018/10/03 QC#28417
                    ZYPEZDItemValueSetter.setValue(pmsgConfig4Rebill.configTpCd, CONFIG_TP.ADD_TO_CONFIG);
                } else {
                    ZYPEZDItemValueSetter.setValue(pmsgConfig4Rebill.configTpCd, CONFIG_TP.NEW);
                }
                ZYPEZDItemValueSetter.setValue(pmsgConfig4Rebill.dclnSvcCd, ZYPConstant.FLG_ON_1); // QC#19674 2017/07/04 MOD
                pmsg4Rebill.cpoConfig.setValidCount(pmsg4Rebill.cpoConfig.getValidCount() + 1);
                procdPosnNums.add(procdPosnNum);

            } else if (PULLDOWN_CD_RMA_RB.equals(invLine.xxTpCd_C1.getValue())) {
                // Skip
                continue;

            } else if (ZYPConstant.FLG_ON_Y.equals(invLine.openFlg_C1.getValue())) {
                // Open Line
                if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())) {
                    // Outbound
                    String configTp = null;
                    if (existCreditRebillInConfig(bizMsg, invLine.dsOrdPosnNum_C1.getValue(), invLine.dsOrdLineDrctnCd_C1.getValue())) {
                        configTp = CONFIG_TP.ADD_TO_CONFIG;
                    } else if (!existClosedLineInConfig(bizMsg, invLine.dsOrdPosnNum_C1.getValue(), invLine.dsOrdLineDrctnCd_C1.getValue())) {
                        configTp = CONFIG_TP.NEW;
                    }
                    if (ZYPCommonFunc.hasValue(configTp)) {
                        // [Exist a action"Credit/Rebill in the config.]  or [Not Exist closed line in the config.] 
                        setOrderConfig(bizMsg, invLine, pmsg4Rebill, CONFIG_CATG.OUTBOUND, configTp);
                        procdPosnNums.add(procdPosnNum);

                        // 2018/05/25 QC#25860 Mod Start
                        // NWZC150001_cpoConfigPMsg pmsgConfig4Rebill = pmsg4Rebill.cpoConfig.no(pmsg4Rebill.cpoConfig.getValidCount());
                        NWZC150001_cpoConfigPMsg pmsgConfig4Rebill = pmsg4Rebill.cpoConfig.no(pmsg4Rebill.cpoConfig.getValidCount() - 1);
                        // 2018/05/25 QC#25860 Mod End
                        if (!existSvcConfigMstr(bizMsg, pmsgConfig4Rebill.svcConfigMstrPk.getValue())) {
                            ZYPEZDItemValueSetter.setValue(pmsgConfig4Rebill.configTpCd, CONFIG_TP.NEW);
                        // 2018/05/25 QC#25860 Add Start
                        } else {
                            ZYPEZDItemValueSetter.setValue(pmsgConfig4Rebill.configTpCd, configTp);
                        // 2018/05/25 QC#25860 Add End
                        }
                    }

                } else if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue()) && ZYPConstant.CHKBOX_ON_Y.equals(invLine.xxChkBox_C1.getValue())) {
                    // Inbound
                    setOrderConfig(bizMsg, invLine, pmsg4Rebill, CONFIG_CATG.INBOUND, CONFIG_TP.ADD_TO_CONFIG);
                    procdPosnNums.add(procdPosnNum);
                }
            }
        }
    }

    private NWZC150001_cpoConfigPMsg getOrderConfigFromCredit(NWZC150001PMsg pmsg4Credit, String posnNum, String configCatgCd) {

        for (int i = 0; i < pmsg4Credit.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg cpoConfig = pmsg4Credit.cpoConfig.no(i);
            if (posnNum.equals(cpoConfig.dsOrdPosnNum.getValue()) && configCatgCd.equals(cpoConfig.configCatgCd.getValue())) {
                return cpoConfig;
            }
        }
        return null;
    }

    private boolean existCreditRebillInConfig(NWAL2300CMsg bizMsg, String posnNum, String dsOrdLineDrctnCd) {

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL2300_CCMsg invLine = bizMsg.C.no(i);
            if (!posnNum.equals(invLine.dsOrdPosnNum_C1.getValue()) || !dsOrdLineDrctnCd.equals(invLine.dsOrdLineDrctnCd_C1.getValue())) {
                continue;
            }
            if (PULLDOWN_CD_C_R.equals(invLine.xxTpCd_C1.getValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean existClosedLineInConfig(NWAL2300CMsg bizMsg, String posnNum, String dsOrdLineDrctnCd) {

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL2300_CCMsg invLine = bizMsg.C.no(i);
            if (!posnNum.equals(invLine.dsOrdPosnNum_C1.getValue()) || !dsOrdLineDrctnCd.equals(invLine.dsOrdLineDrctnCd_C1.getValue())) {
                continue;
            }
            if (ZYPConstant.FLG_OFF_N.equals(invLine.openFlg_C1.getValue())) {
                return true;
            }
        }
        return false;
    }

    // 2018/04/06 QC#22122 Mod Start
    //private void setOrderDetailPrcRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4Rebill) {
    private void setOrderDetailPrcRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4Rebill, String invNum) {
    // 2018/04/06 QC#22122 Mod End

        pmsg4Rebill.priceList.clear();
        ZYPTableUtil.clear(pmsg4Rebill.priceList);
        pmsg4Rebill.rtnPriceList.clear();
        ZYPTableUtil.clear(pmsg4Rebill.rtnPriceList);

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            // 2018/04/06 QC#22122 Add Start
            if (ZYPCommonFunc.hasValue(invNum) && !invNum.equals(bizMsg.C.no(i).invNum_C1.getValue())) {
                continue;
            }
            // 2018/04/06 QC#22122 Add End
            NWAL2300_CCMsg invLine = bizMsg.C.no(i);
            // QC#22106 2018/03/30 Add Start
            //QC#15624 add Start
            // String lineNum = invLine.cpoDtlLineNum_C1.getValue();
            String lineNum = invLine.cpoDtlLineNum_T.getValue();
            String lineSubNum = invLine.cpoDtlLineSubNum_C1.getValue();
            //if (!ZYPCommonFunc.hasValue(lineNum)) {
            //    // bill with equipment
            //    lineNum = String.valueOf(i + 1);
            //    lineSubNum = DEF_LINE_SUB_NUM;
            //}
            //QC#15624 add End
            // QC#22106 2018/03/30 Add End
            if (PULLDOWN_CD_C_R.equals(invLine.xxTpCd_C1.getValue())) {
                // Outbound Close
                //QC#15624 mod Start
//                List<NWZC150001_priceListPMsg> pmsgPrc4CreditList = getOrderPriceFromCredit(pmsg4Credit//
//                        , invLine.cpoDtlLineNum_C1.getValue()//
//                        , invLine.cpoDtlLineSubNum_C1.getValue());
                List<NWZC150001_priceListPMsg> pmsgPrc4CreditList = getOrderPriceFromCredit(pmsg4Credit, lineNum, lineSubNum);
                //QC#15624 mod End
                // Copy from Credit info
                for (NWZC150001_priceListPMsg prc4Credit : pmsgPrc4CreditList) {
                    NWZC150001_priceListPMsg prc = pmsg4Rebill.priceList.no(pmsg4Rebill.priceList.getValidCount());
                    EZDMsg.copy(prc4Credit, null, prc, null);
                    pmsg4Rebill.priceList.setValidCount(pmsg4Rebill.priceList.getValidCount() + 1);
                    // Update sign
                    ZYPEZDItemValueSetter.setValue(prc.autoPrcAmtRate, (NWAL2300CommonLogic.negate(prc.autoPrcAmtRate.getValue())));
                    ZYPEZDItemValueSetter.setValue(prc.maxPrcAmtRate, (NWAL2300CommonLogic.negate(prc.maxPrcAmtRate.getValue())));
                    ZYPEZDItemValueSetter.setValue(prc.minPrcAmtRate, (NWAL2300CommonLogic.negate(prc.minPrcAmtRate.getValue())));
                    ZYPEZDItemValueSetter.setValue(prc.manPrcAmtRate, (NWAL2300CommonLogic.negate(prc.manPrcAmtRate.getValue())));
                    ZYPEZDItemValueSetter.setValue(prc.calcPrcAmtRate, (NWAL2300CommonLogic.negate(prc.calcPrcAmtRate.getValue())));
                    ZYPEZDItemValueSetter.setValue(prc.unitPrcAmt, (NWAL2300CommonLogic.negate(prc.unitPrcAmt.getValue())));

                    // 2018/06/27 QC#26578 Add Start
                    prc.prcCondManEntryFlg.setValue(ZYPConstant.FLG_OFF_N);
                    prc.manPrcAmtRate.clear();
                    // 2018/06/27 QC#26578 Add End
                    ZYPEZDItemValueSetter.setValue(prc.autoPrcAmtRate, prc.unitPrcAmt); // QC#50776 2019/06/10 Add
                    // QC#22136 2017/10/17 Del Start
                    //QC#11699
                    // ZYPEZDItemValueSetter.setValue(prc.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
                    // prc.manPrcAmtRate.clear();
                    // QC#22136 2017/10/17 Del End

                }

            } else if (PULLDOWN_CD_RMA_RB.equals(invLine.xxTpCd_C1.getValue())) {
                // Skip
                continue;

            } else if (ZYPConstant.FLG_ON_Y.equals(invLine.openFlg_C1.getValue())) {
                // Open Line
                if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())) {
                    // Outbound
                    if (existCreditRebillInConfig(bizMsg, invLine.dsOrdPosnNum_C1.getValue(), invLine.dsOrdLineDrctnCd_C1.getValue())
                            || !existClosedLineInConfig(bizMsg, invLine.dsOrdPosnNum_C1.getValue(), invLine.dsOrdLineDrctnCd_C1.getValue())) {
                        // [Exist a action"Credit/Rebill in the config.] or [Not Exist closed line in the config.]
                        setOrderDetailPrcOpenLineRebill(bizMsg, pmsg4Rebill, invLine);
                    }

                } else if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())//
                        && ZYPConstant.CHKBOX_ON_Y.equals(invLine.xxChkBox_C1.getValue())) {
                    // Inbound
                    setOrderDetailPrcOpenLineRebill(bizMsg, pmsg4Rebill, invLine);
                }
            }
        }
    }

    private List<NWZC150001_priceListPMsg> getOrderPriceFromCredit(NWZC150001PMsg pmsg4Credit, String lineNum, String lineSubNum) {

        List<NWZC150001_priceListPMsg> prcList = new ArrayList<NWZC150001_priceListPMsg>();
        for (int i = 0; i < pmsg4Credit.priceList.getValidCount(); i++) {
            NWZC150001_priceListPMsg ordPrc = pmsg4Credit.priceList.no(i);
            if (lineNum.equals(ordPrc.cpoDtlLineNum.getValue()) && lineSubNum.equals(ordPrc.cpoDtlLineSubNum.getValue())) {
                prcList.add(ordPrc);
            }
        }
        return prcList;
    }

    private void setOrderDetailPrcOpenLineRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Rebill, NWAL2300_CCMsg invLine) {

        if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())) {
            // Ountbound
            ORD_PRC_CALC_BASETMsgArray prcCalcBaseList = getORD_PRC_CALC_BASETMsgList(bizMsg, invLine);
            if (prcCalcBaseList != null) {
                for (int i = 0; i < prcCalcBaseList.getValidCount(); i++) {
                    ORD_PRC_CALC_BASETMsg prcCalcBase = prcCalcBaseList.no(i);
                    NWZC150001_priceListPMsg prc = pmsg4Rebill.priceList.no(pmsg4Rebill.priceList.getValidCount());
                    pmsg4Rebill.priceList.setValidCount(pmsg4Rebill.priceList.getValidCount() + 1);
                    EZDMsg.copy(prcCalcBase, null, prc, null);
                    // QC#22106 2018/03/30 Add Start
                    ZYPEZDItemValueSetter.setValue(prc.cpoDtlLineNum, invLine.cpoDtlLineNum_T);
                    // QC#22106 2018/03/30 Add End
                }
            }
        } else {
            // Inbound
            CPO_RTRN_PRC_CALC_BASETMsgArray prcCalcBaseList = getCPO_RTRN_PRC_CALC_BASETMsgList(bizMsg, invLine);
            if (prcCalcBaseList != null) {
                for (int i = 0; i < prcCalcBaseList.getValidCount(); i++) {
                    CPO_RTRN_PRC_CALC_BASETMsg prcCalcBase = prcCalcBaseList.no(i);
                    NWZC150001_rtnPriceListPMsg prc = pmsg4Rebill.rtnPriceList.no(pmsg4Rebill.rtnPriceList.getValidCount());
                    pmsg4Rebill.rtnPriceList.setValidCount(pmsg4Rebill.rtnPriceList.getValidCount() + 1);
                    EZDMsg.copy(prcCalcBase, null, prc, null);
                    // QC#22106 2018/03/30 Mod Start
                    //ZYPEZDItemValueSetter.setValue(prc.cpoDtlLineNum, invLine.cpoDtlLineNum_C1);
                    ZYPEZDItemValueSetter.setValue(prc.cpoDtlLineNum, invLine.cpoDtlLineNum_T);
                    // QC#22106 2018/03/30 Mod End
                    ZYPEZDItemValueSetter.setValue(prc.cpoDtlLineSubNum, invLine.cpoDtlLineSubNum_C1);
//                    //QC#15624 add Start
//                    if (!ZYPCommonFunc.hasValue(prc.cpoDtlLineNum)) {
//                        // bill with equipment
//                        ZYPEZDItemValueSetter.setValue(prc.cpoDtlLineNum, String.valueOf(i + 1));
//                        ZYPEZDItemValueSetter.setValue(prc.cpoDtlLineSubNum, DEF_LINE_SUB_NUM);
//                    }
//                    //QC#15624 add End
                }
            }
        }
    }

    // 2018/04/06 QC#22122 Mod Start
    //private void setOrderDetailRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4Rebill) {
    private void setOrderDetailRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4Rebill, String invNum) {
    // 2018/04/06 QC#22122 Mod End

        pmsg4Rebill.A.clear();
        ZYPTableUtil.clear(pmsg4Rebill.A);
        pmsg4Rebill.rtnDtl.clear();
        ZYPTableUtil.clear(pmsg4Rebill.rtnDtl);

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            // 2018/04/06 QC#22122 Add Start
            if (ZYPCommonFunc.hasValue(invNum) && !invNum.equals(bizMsg.C.no(i).invNum_C1.getValue())) {
                continue;
            }
            // 2018/04/06 QC#22122 Add End
            NWAL2300_CCMsg invLine = bizMsg.C.no(i);

            // 2017/02/21 S21_NA#17416 Add Start
//            if (ZYPConstant.FLG_OFF_N.equals(invLine.openFlg_C1.getValue())) {
//                INV_LINETMsg dsInvLine = null;
//                dsInvLine = getINV_LINETMsg(bizMsg, invLine);
//
//                if (invLine != null && ZYPCommonFunc.hasValue(dsInvLine.cpoSvcDtlPk)) {
//                    continue;
//                }
//            }
            // 2017/02/21 S21_NA#17416 Add End

            // QC#22106 2018/03/30 Add Start
            // QC#15624 add Start
            // String lineNum = invLine.cpoDtlLineNum_C1.getValue();
            String lineNum = invLine.cpoDtlLineNum_T.getValue();
            String lineSubNum = invLine.cpoDtlLineSubNum_C1.getValue();
            //if (!ZYPCommonFunc.hasValue(lineNum)) {
            //    // bill with equipment
            //    lineNum = String.valueOf(i + 1);
            //    lineSubNum = DEF_LINE_SUB_NUM;
            //}
            //QC#15624 add End
            // QC#22106 2018/03/30 Add End
            if (PULLDOWN_CD_C_R.equals(invLine.xxTpCd_C1.getValue())) {
                // Outbound Close
                //QC#15624 mod Start
//                NWZC150001_APMsg pmsgDtl4Credit = getOrderDtlFromCredit(pmsg4Credit, invLine.cpoDtlLineNum_C1.getValue(), invLine.cpoDtlLineSubNum_C1.getValue());
                NWZC150001_APMsg pmsgDtl4Credit = getOrderDtlFromCredit(pmsg4Credit, lineNum, lineSubNum);
                //QC#15624 mod End
                // Copy from Credit info
                NWZC150001_APMsg pmsgDtl4Rebil = pmsg4Rebill.A.no(pmsg4Rebill.A.getValidCount());
                EZDMsg.copy(pmsgDtl4Credit, null, pmsgDtl4Rebil, null);

                pmsg4Rebill.A.setValidCount(pmsg4Rebill.A.getValidCount() + 1);
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.crRebilCd_A1, CR_REBIL.REBILL);
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.prcBaseDt_A1, pmsg4Rebill.prcBaseDt); // QC#12892 ADD
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.entDealNetUnitPrcAmt_A1, NWAL2300CommonLogic.negate(pmsgDtl4Rebil.entDealNetUnitPrcAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.xxTotBaseAmt_A1, NWAL2300CommonLogic.negate(pmsgDtl4Rebil.xxTotBaseAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.xxTotDiscAmt_A1, NWAL2300CommonLogic.negate(pmsgDtl4Rebil.xxTotDiscAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.xxTotSpclPrcAmt_A1, NWAL2300CommonLogic.negate(pmsgDtl4Rebil.xxTotSpclPrcAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.xxTotNetDiscAmt_A1, NWAL2300CommonLogic.negate(pmsgDtl4Rebil.xxTotNetDiscAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.xxTotNetPrcAmt_A1, NWAL2300CommonLogic.negate(pmsgDtl4Rebil.xxTotNetPrcAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.xxTotFrtAmt_A1, NWAL2300CommonLogic.negate(pmsgDtl4Rebil.xxTotFrtAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.xxTotSpclChrgAmt_A1, NWAL2300CommonLogic.negate(pmsgDtl4Rebil.xxTotSpclChrgAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.xxTotTaxAmt_A1, NWAL2300CommonLogic.negate(pmsgDtl4Rebil.xxTotTaxAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.xxSlsAmt_A1, NWAL2300CommonLogic.negate(pmsgDtl4Rebil.xxSlsAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.xxTotAmt_A1, NWAL2300CommonLogic.negate(pmsgDtl4Rebil.xxTotAmt_A1.getValue()));
                // QC#16568 2016/12/27 Add Start
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.funcSvcRevTrnsfAmt_A1,  NWAL2300CommonLogic.negate(pmsgDtl4Rebil.funcSvcRevTrnsfAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.dealSvcRevTrnsfAmt_A1,  NWAL2300CommonLogic.negate(pmsgDtl4Rebil.dealSvcRevTrnsfAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.funcSvcCostTrnsfAmt_A1, NWAL2300CommonLogic.negate(pmsgDtl4Rebil.funcSvcCostTrnsfAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.dealSvcCostTrnsfAmt_A1, NWAL2300CommonLogic.negate(pmsgDtl4Rebil.dealSvcCostTrnsfAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.funcManFlAdjAmt_A1,     NWAL2300CommonLogic.negate(pmsgDtl4Rebil.funcManFlAdjAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.dealManFlAdjAmt_A1,     NWAL2300CommonLogic.negate(pmsgDtl4Rebil.dealManFlAdjAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.funcManRepRevAdjAmt_A1, NWAL2300CommonLogic.negate(pmsgDtl4Rebil.funcManRepRevAdjAmt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.dealManRepRevAdjAmt_A1, NWAL2300CommonLogic.negate(pmsgDtl4Rebil.dealManRepRevAdjAmt_A1.getValue()));
                // QC#16568 2016/12/27 Add End
                // QC#12123
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.pmtTermCashDiscCd_A1, getPmtTermCashDiscCd(bizMsg, invLine.pmtTermCashDiscCd_C1.getValue(), CR_REBIL.REBILL));
                // Add Start 2018/10/03 QC#28417
                ZYPEZDItemValueSetter.setValue(pmsgDtl4Rebil.cpoSrcTpCd_A1, CPO_SRC_TP.REBILL);
                // Add End 2018/10/03 QC#28417

            } else if (PULLDOWN_CD_RMA_RB.equals(invLine.xxTpCd_C1.getValue())) {
                // Skip
                continue;

            } else if (ZYPConstant.FLG_ON_Y.equals(invLine.openFlg_C1.getValue())) {
                // Open Line
                if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())) {
                    // Outbound
                    if (existCreditRebillInConfig(bizMsg, invLine.dsOrdPosnNum_C1.getValue(), invLine.dsOrdLineDrctnCd_C1.getValue())
                            || !existClosedLineInConfig(bizMsg, invLine.dsOrdPosnNum_C1.getValue(), invLine.dsOrdLineDrctnCd_C1.getValue())) {
                        // [Exist a action"Credit/Rebill in the config.]  or [Not Exist closed line in the config.] 
//                        setOrderDetail(bizMsg, pmsg4Rebill, invLine, CR_REBIL.REBILL);  //QC#15624 mod
                        setOrderDetail(bizMsg, pmsg4Rebill, invLine, CR_REBIL.REBILL, lineNum, lineSubNum);
                    }

                } else if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue()) && ZYPConstant.CHKBOX_ON_Y.equals(invLine.xxChkBox_C1.getValue())) {
                    // Inbound
//                    setOrderDetailReturn(bizMsg, pmsg4Rebill, invLine, CR_REBIL.REBILL);  //QC#15624 mod
                    setOrderDetailReturn(bizMsg, pmsg4Rebill, invLine, CR_REBIL.REBILL, lineNum, lineSubNum);
                }
            }
        }
    }

    private NWZC150001_APMsg getOrderDtlFromCredit(NWZC150001PMsg pmsg4Credit, String lineNum, String lineSubNum) {

        for (int i = 0; i < pmsg4Credit.A.getValidCount(); i++) {
            NWZC150001_APMsg cpoDtl = pmsg4Credit.A.no(i);
            if (lineNum.equals(cpoDtl.cpoDtlLineNum_A1.getValue()) && lineSubNum.equals(cpoDtl.cpoDtlLineSubNum_A1.getValue())) {
                return cpoDtl;
            }
        }
        return null;
    }

    // 2018/04/06 QC#22122 Mod Start
    //private void setOrderSalesCreditRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4Rebill) {
    private void setOrderSalesCreditRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4Rebill, String invNum) {
    // 2018/04/06 QC#22122 Mod End

        pmsg4Rebill.cpoSlsCr.clear();
        ZYPTableUtil.clear(pmsg4Rebill.cpoSlsCr);
        boolean createdRebill = false;
        Set<String> procdPosnNums = new HashSet<String>();

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            // 2018/04/06 QC#22122 Add Start
            if (ZYPCommonFunc.hasValue(invNum) && !invNum.equals(bizMsg.C.no(i).invNum_C1.getValue())) {
                continue;
            }
            // 2018/04/06 QC#22122 Add End
            NWAL2300_CCMsg invLine = bizMsg.C.no(i);
            String posnNumIO = invLine.dsOrdPosnNum_C1.getValue() + invLine.dsOrdLineDrctnCd_C1.getValue();
            if (procdPosnNums.contains(posnNumIO)) {
                continue;
            }
            if (PULLDOWN_CD_C_R.equals(invLine.xxTpCd_C1.getValue())) {
                // Outbound Close
                List<NWZC150001_cpoSlsCrPMsg> pmsgSlsCr4CreditList = getCpoSlsCrFromCredit(pmsg4Credit, invLine.dsOrdPosnNum_C1.getValue(), CONFIG_CATG.OUTBOUND);
                // Copy from Credit info
                for (NWZC150001_cpoSlsCrPMsg slsCr4Credit : pmsgSlsCr4CreditList) {
                    EZDMsg.copy(slsCr4Credit, null, pmsg4Rebill.cpoSlsCr.no(pmsg4Rebill.cpoSlsCr.getValidCount()), null);
                    pmsg4Rebill.cpoSlsCr.setValidCount(pmsg4Rebill.cpoSlsCr.getValidCount() + 1);
                }
                createdRebill = true;
                procdPosnNums.add(posnNumIO);

            } else if (PULLDOWN_CD_RMA_RB.equals(invLine.xxTpCd_C1.getValue())) {
                // Skip
                continue;

            } else if (ZYPConstant.FLG_ON_Y.equals(invLine.openFlg_C1.getValue())) {
                // Open Line
                if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())) {
                    // Outbound
                    if (existCreditRebillInConfig(bizMsg, invLine.dsOrdPosnNum_C1.getValue(), invLine.dsOrdLineDrctnCd_C1.getValue())
                            || !existClosedLineInConfig(bizMsg, invLine.dsOrdPosnNum_C1.getValue(), invLine.dsOrdLineDrctnCd_C1.getValue())) {
                        // [Exist a action"Credit/Rebill in the config.]  or [Not Exist closed line in the config.] 
                        setOrderSalesCredit(bizMsg, pmsg4Rebill, invLine, invLine.dsOrdLineDrctnCd_C1.getValue());
                        createdRebill = true;
                        procdPosnNums.add(posnNumIO);
                    }

                } else if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue()) && ZYPConstant.CHKBOX_ON_Y.equals(invLine.xxChkBox_C1.getValue())) {
                    // Inbound
                    setOrderSalesCredit(bizMsg, pmsg4Rebill, invLine, invLine.dsOrdLineDrctnCd_C1.getValue());
                    createdRebill = true;
                    procdPosnNums.add(posnNumIO);
                }
            }
        }
        // Header
        if (createdRebill) {
            for (int i = 0; i < pmsg4Credit.cpoSlsCr.getValidCount(); i++) {
                NWZC150001_cpoSlsCrPMsg slsCr4Credit = pmsg4Credit.cpoSlsCr.no(i);
                if (!ZYPCommonFunc.hasValue(slsCr4Credit.dsOrdPosnNum)) {
                    EZDMsg.copy(slsCr4Credit, null, pmsg4Rebill.cpoSlsCr.no(pmsg4Rebill.cpoSlsCr.getValidCount()), null);
                    pmsg4Rebill.cpoSlsCr.setValidCount(pmsg4Rebill.cpoSlsCr.getValidCount() + 1);
                }
            }
        }
    }

    private List<NWZC150001_cpoSlsCrPMsg> getCpoSlsCrFromCredit(NWZC150001PMsg pmsg4Credit, String posnNum, String configCatgCd) {

        List<NWZC150001_cpoSlsCrPMsg> slsCrList = new ArrayList<NWZC150001_cpoSlsCrPMsg>();
        for (int i = 0; i < pmsg4Credit.cpoSlsCr.getValidCount(); i++) {
            NWZC150001_cpoSlsCrPMsg cpoSlsCr = pmsg4Credit.cpoSlsCr.no(i);
            if (posnNum.equals(cpoSlsCr.dsOrdPosnNum.getValue()) && configCatgCd.equals(cpoSlsCr.configCatgCd.getValue())) {
                slsCrList.add(cpoSlsCr);
            }
        }
        return slsCrList;
    }

    // 2018/04/06 QC#22122 Mod Start
    //private void setOrderDiscRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4Rebill) {
    private void setOrderDiscRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4Rebill, String invNum) {
    // 2018/04/06 QC#22122 Mod End

        pmsg4Rebill.cpoDlvyInfoList.clear();
        ZYPTableUtil.clear(pmsg4Rebill.cpoDlvyInfoList);
        pmsg4Rebill.siteSrvInfoList.clear();
        ZYPTableUtil.clear(pmsg4Rebill.siteSrvInfoList);
        pmsg4Rebill.cpoIstlInfoList.clear();
        ZYPTableUtil.clear(pmsg4Rebill.cpoIstlInfoList);
        pmsg4Rebill.cpoCtacPsnInfoList.clear();
        ZYPTableUtil.clear(pmsg4Rebill.cpoCtacPsnInfoList);

        Set<String> procdPosnNums = new HashSet<String>();
        boolean createdRebill = false;

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            // 2018/04/06 QC#22122 Add Start
            if (ZYPCommonFunc.hasValue(invNum) && !invNum.equals(bizMsg.C.no(i).invNum_C1.getValue())) {
                continue;
            }
            // 2018/04/06 QC#22122 Add End
            NWAL2300_CCMsg invLine = bizMsg.C.no(i);
            String posnNumIO = invLine.dsOrdPosnNum_C1.getValue() + invLine.dsOrdLineDrctnCd_C1.getValue();
            if (procdPosnNums.contains(posnNumIO)) {
                continue;
            }
            if (PULLDOWN_CD_C_R.equals(invLine.xxTpCd_C1.getValue())) {
                // Outbound Close

                List<NWZC150001_cpoDlvyInfoListPMsg> pmsgCpoDlvyInfo4CreditList = getCpoDlvyInfoFromCredit(pmsg4Credit, invLine.dsOrdPosnNum_C1.getValue(), CONFIG_CATG.OUTBOUND);
                // Copy  Delivery info from Credit
                for (NWZC150001_cpoDlvyInfoListPMsg cpoDlvyInfo : pmsgCpoDlvyInfo4CreditList) {
                    EZDMsg.copy(cpoDlvyInfo, null, pmsg4Rebill.cpoDlvyInfoList.no(pmsg4Rebill.cpoDlvyInfoList.getValidCount()), null);
                    pmsg4Rebill.cpoDlvyInfoList.setValidCount(pmsg4Rebill.cpoDlvyInfoList.getValidCount() + 1);
                }

                List<NWZC150001_siteSrvInfoListPMsg> pmsgSiteSrvInfo4CreditList = getSiteSrvInfoFromCredit(pmsg4Credit, invLine.dsOrdPosnNum_C1.getValue(), CONFIG_CATG.OUTBOUND);
                // Copy site survey info from Credit
                for (NWZC150001_siteSrvInfoListPMsg siteSrvInfo : pmsgSiteSrvInfo4CreditList) {
                    EZDMsg.copy(siteSrvInfo, null, pmsg4Rebill.siteSrvInfoList.no(pmsg4Rebill.siteSrvInfoList.getValidCount()), null);
                    pmsg4Rebill.siteSrvInfoList.setValidCount(pmsg4Rebill.siteSrvInfoList.getValidCount() + 1);
                }

                List<NWZC150001_cpoIstlInfoListPMsg> pmsgCpoIstlInfo4CreditList = getCpoIstlInfoFromCredit(pmsg4Credit, invLine.dsOrdPosnNum_C1.getValue(), CONFIG_CATG.OUTBOUND);
                // Copy install info from Credit
                for (NWZC150001_cpoIstlInfoListPMsg cpoIstlInfo : pmsgCpoIstlInfo4CreditList) {
                    EZDMsg.copy(cpoIstlInfo, null, pmsg4Rebill.cpoIstlInfoList.no(pmsg4Rebill.cpoIstlInfoList.getValidCount()), null);
                    pmsg4Rebill.cpoIstlInfoList.setValidCount(pmsg4Rebill.cpoIstlInfoList.getValidCount() + 1);
                }

                List<NWZC150001_cpoCtacPsnInfoListPMsg> pmsgCpoCtacPsnInfo4CreditList = getCpoCtacPsnInfoFromCredit(pmsg4Credit, invLine.dsOrdPosnNum_C1.getValue(), CONFIG_CATG.OUTBOUND);
                //  Copy contact person info from Credit
                for (NWZC150001_cpoCtacPsnInfoListPMsg cpoCtacPsnInfo : pmsgCpoCtacPsnInfo4CreditList) {
                    EZDMsg.copy(cpoCtacPsnInfo, null, pmsg4Rebill.cpoCtacPsnInfoList.no(pmsg4Rebill.cpoCtacPsnInfoList.getValidCount()), null);
                    pmsg4Rebill.cpoCtacPsnInfoList.setValidCount(pmsg4Rebill.cpoCtacPsnInfoList.getValidCount() + 1);
                }
                procdPosnNums.add(posnNumIO);
                createdRebill = true;

            } else if (PULLDOWN_CD_RMA_RB.equals(invLine.xxTpCd_C1.getValue())) {
                // Skip
                continue;

            } else if (ZYPConstant.FLG_ON_Y.equals(invLine.openFlg_C1.getValue())) {
                // Open Line
                if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())) {
                    // Outbound
                    if (existCreditRebillInConfig(bizMsg, invLine.dsOrdPosnNum_C1.getValue(), invLine.dsOrdLineDrctnCd_C1.getValue())
                            || !existClosedLineInConfig(bizMsg, invLine.dsOrdPosnNum_C1.getValue(), invLine.dsOrdLineDrctnCd_C1.getValue())) {
                        // [Exist a action"Credit/Rebill in the config.]  or [Not Exist closed line in the config.] 
                        setOrderDeliveryInfoRebill(bizMsg, pmsg4Rebill, invLine, invLine.dsOrdLineDrctnCd_C1.getValue());
                        setOrderSiteSurveyRebill(bizMsg, pmsg4Rebill, invLine, invLine.dsOrdLineDrctnCd_C1.getValue());
                        setOrderInstallInfoRebill(bizMsg, pmsg4Rebill, invLine, invLine.dsOrdLineDrctnCd_C1.getValue());
                        setOrderContactPersonRebill(bizMsg, pmsg4Rebill, invLine, invLine.dsOrdLineDrctnCd_C1.getValue());
                        createdRebill = true;
                        procdPosnNums.add(posnNumIO);
                    }

                } else if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue()) && ZYPConstant.CHKBOX_ON_Y.equals(invLine.xxChkBox_C1.getValue())) {
                    // Inbound
                    setOrderDeliveryInfoRebill(bizMsg, pmsg4Rebill, invLine, invLine.dsOrdLineDrctnCd_C1.getValue());
                    setOrderSiteSurveyRebill(bizMsg, pmsg4Rebill, invLine, invLine.dsOrdLineDrctnCd_C1.getValue());
                    setOrderInstallInfoRebill(bizMsg, pmsg4Rebill, invLine, invLine.dsOrdLineDrctnCd_C1.getValue());
                    setOrderContactPersonRebill(bizMsg, pmsg4Rebill, invLine, invLine.dsOrdLineDrctnCd_C1.getValue());
                    createdRebill = true;
                    procdPosnNums.add(posnNumIO);
                }
            }
        }
        // Header Copy
        if (createdRebill) {
            // Delivery Info
            boolean copied = false;
            for (int i = 0; i < pmsg4Credit.cpoDlvyInfoList.getValidCount(); i++) {
                NWZC150001_cpoDlvyInfoListPMsg dlvyInfo4Credit = pmsg4Credit.cpoDlvyInfoList.no(i);
                if (!ZYPCommonFunc.hasValue(dlvyInfo4Credit.dsOrdPosnNum)) {
                    EZDMsg.copy(dlvyInfo4Credit, null, pmsg4Rebill.cpoDlvyInfoList.no(pmsg4Rebill.cpoDlvyInfoList.getValidCount()), null);
                    pmsg4Rebill.cpoDlvyInfoList.setValidCount(pmsg4Rebill.cpoDlvyInfoList.getValidCount() + 1);
                    copied = true;
                }
            }
            if (!copied) {
                setOrderDeliveryInfoHeaderCredit(bizMsg, pmsg4Rebill);
            }

            // Site Survey Info
            copied = false;
            for (int i = 0; i < pmsg4Credit.siteSrvInfoList.getValidCount(); i++) {
                NWZC150001_siteSrvInfoListPMsg siteSrv4Credit = pmsg4Credit.siteSrvInfoList.no(i);
                if (!ZYPCommonFunc.hasValue(siteSrv4Credit.dsOrdPosnNum)) {
                    EZDMsg.copy(siteSrv4Credit, null, pmsg4Rebill.siteSrvInfoList.no(pmsg4Rebill.siteSrvInfoList.getValidCount()), null);
                    pmsg4Rebill.siteSrvInfoList.setValidCount(pmsg4Rebill.siteSrvInfoList.getValidCount() + 1);
                    copied = true;
                }
            }
            if (!copied) {
                setOrderSiteSurveyHeaderCredit(bizMsg, pmsg4Rebill);
            }

            // Install Info
            copied = false;
            for (int i = 0; i < pmsg4Credit.cpoIstlInfoList.getValidCount(); i++) {
                NWZC150001_cpoIstlInfoListPMsg istlInfo4Credit = pmsg4Credit.cpoIstlInfoList.no(i);
                if (!ZYPCommonFunc.hasValue(istlInfo4Credit.dsOrdPosnNum)) {
                    EZDMsg.copy(istlInfo4Credit, null, pmsg4Rebill.cpoIstlInfoList.no(pmsg4Rebill.cpoIstlInfoList.getValidCount()), null);
                    pmsg4Rebill.cpoIstlInfoList.setValidCount(pmsg4Rebill.cpoIstlInfoList.getValidCount() + 1);
                    copied = true;
                }
            }
            if (!copied) {
                setOrderInstallInfoHeaderCredit(bizMsg, pmsg4Rebill);
            }

            // Contact Person
            copied = false;
            for (int i = 0; i < pmsg4Credit.cpoCtacPsnInfoList.getValidCount(); i++) {
                NWZC150001_cpoCtacPsnInfoListPMsg ctacPsn4Credit = pmsg4Credit.cpoCtacPsnInfoList.no(i);
                if (!ZYPCommonFunc.hasValue(ctacPsn4Credit.dsOrdPosnNum)) {
                    EZDMsg.copy(ctacPsn4Credit, null, pmsg4Rebill.cpoCtacPsnInfoList.no(pmsg4Rebill.cpoCtacPsnInfoList.getValidCount()), null);
                    pmsg4Rebill.cpoCtacPsnInfoList.setValidCount(pmsg4Rebill.cpoCtacPsnInfoList.getValidCount() + 1);
                    copied = true;
                }
            }
            if (!copied) {
                setOrderContactPersonHeaderCredit(bizMsg, pmsg4Rebill);
            }
        }
    }

    private List<NWZC150001_cpoDlvyInfoListPMsg> getCpoDlvyInfoFromCredit(NWZC150001PMsg pmsg4Credit, String posnNum, String configCatgCd) {

        List<NWZC150001_cpoDlvyInfoListPMsg> cpoDlvyInfoList = new ArrayList<NWZC150001_cpoDlvyInfoListPMsg>();
        for (int i = 0; i < pmsg4Credit.cpoDlvyInfoList.getValidCount(); i++) {
            NWZC150001_cpoDlvyInfoListPMsg cpoDlvyInfo = pmsg4Credit.cpoDlvyInfoList.no(i);
            if (posnNum.equals(cpoDlvyInfo.dsOrdPosnNum.getValue()) && configCatgCd.equals(cpoDlvyInfo.configCatgCd.getValue())) {
                cpoDlvyInfoList.add(cpoDlvyInfo);
            }
        }
        return cpoDlvyInfoList;
    }

    private List<NWZC150001_siteSrvInfoListPMsg> getSiteSrvInfoFromCredit(NWZC150001PMsg pmsg4Credit, String posnNum, String configCatgCd) {

        List<NWZC150001_siteSrvInfoListPMsg> siteSrvInfoList = new ArrayList<NWZC150001_siteSrvInfoListPMsg>();
        for (int i = 0; i < pmsg4Credit.cpoDlvyInfoList.getValidCount(); i++) {
            NWZC150001_siteSrvInfoListPMsg siteSrvInfo = pmsg4Credit.siteSrvInfoList.no(i);
            if (posnNum.equals(siteSrvInfo.dsOrdPosnNum.getValue()) && configCatgCd.equals(siteSrvInfo.configCatgCd.getValue())) {
                siteSrvInfoList.add(siteSrvInfo);
            }
        }
        return siteSrvInfoList;
    }

    private List<NWZC150001_cpoIstlInfoListPMsg> getCpoIstlInfoFromCredit(NWZC150001PMsg pmsg4Credit, String posnNum, String configCatgCd) {

        List<NWZC150001_cpoIstlInfoListPMsg> cpoIstlInfoList = new ArrayList<NWZC150001_cpoIstlInfoListPMsg>();
        for (int i = 0; i < pmsg4Credit.cpoIstlInfoList.getValidCount(); i++) {
            NWZC150001_cpoIstlInfoListPMsg cpoIstlInfo = pmsg4Credit.cpoIstlInfoList.no(i);
            if (posnNum.equals(cpoIstlInfo.dsOrdPosnNum.getValue()) && configCatgCd.equals(cpoIstlInfo.configCatgCd.getValue())) {
                cpoIstlInfoList.add(cpoIstlInfo);
            }
        }
        return cpoIstlInfoList;
    }

    private List<NWZC150001_cpoCtacPsnInfoListPMsg> getCpoCtacPsnInfoFromCredit(NWZC150001PMsg pmsg4Credit, String posnNum, String configCatgCd) {

        List<NWZC150001_cpoCtacPsnInfoListPMsg> cpoCtacPsnInfoList = new ArrayList<NWZC150001_cpoCtacPsnInfoListPMsg>();
        for (int i = 0; i < pmsg4Credit.cpoCtacPsnInfoList.getValidCount(); i++) {
            NWZC150001_cpoCtacPsnInfoListPMsg cpoCtacPsnInfo = pmsg4Credit.cpoCtacPsnInfoList.no(i);
            if (posnNum.equals(cpoCtacPsnInfo.dsOrdPosnNum.getValue()) && configCatgCd.equals(cpoCtacPsnInfo.configCatgCd.getValue())) {
                cpoCtacPsnInfoList.add(cpoCtacPsnInfo);
            }
        }
        return cpoCtacPsnInfoList;
    }

    private void setOrderDeliveryInfoRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Rebill, NWAL2300_CCMsg invLineMsg, String configCatgCd) {
        setOrderDeliveryInfoCredit(bizMsg, pmsg4Rebill, invLineMsg, configCatgCd);
    }

    private void setOrderSiteSurveyRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Rebill, NWAL2300_CCMsg invLineMsg, String configCatgCd) {
        setOrderSiteSurveyCredit(bizMsg, pmsg4Rebill, invLineMsg, configCatgCd);
    }

    private void setOrderInstallInfoRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Rebill, NWAL2300_CCMsg invLineMsg, String configCatgCd) {
        setOrderInstallInfoCredit(bizMsg, pmsg4Rebill, invLineMsg, configCatgCd);
    }

    private void setOrderContactPersonRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Rebill, NWAL2300_CCMsg invLineMsg, String configCatgCd) {
        setOrderContactPersonCredit(bizMsg, pmsg4Rebill, invLineMsg, configCatgCd);
    }

    private void setDsCpoUpdateApiParam4Orig(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4Orig) {
        setOrderHeaderOrig(bizMsg, pmsg4Credit, pmsg4Orig);
        setOrderConfigAndDetailOrig(bizMsg, pmsg4Credit, pmsg4Orig);
    }

    private void setOrderHeaderOrig(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4Orig) {
        EZDMsg.copy(pmsg4Credit, null, pmsg4Orig, null);
        ZYPEZDItemValueSetter.setValue(pmsg4Orig.xxModeCd, MODE_CANCEL);
        ZYPEZDItemValueSetter.setValue(pmsg4Orig.cpoOrdNum, bizMsg.cpoOrdNum_H1);
        // QC#12123
        CPOTMsg cpo = getCPOTMsg(bizMsg);
        if (cpo != null) {
            ZYPEZDItemValueSetter.setValue(pmsg4Orig.addPmtTermCashDiscCd, cpo.addPmtTermCashDiscCd);
            ZYPEZDItemValueSetter.setValue(pmsg4Orig.dsPmtMethCd, cpo.dsPmtMethCd);
            ZYPEZDItemValueSetter.setValue(pmsg4Orig.dsCrCardPk, cpo.dsCrCardPk);
        }
    }

    private void setOrderConfigAndDetailOrig(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4Orig) {

        BigDecimal dsCpoConfigPk = null;

        // Clear Config
        pmsg4Orig.cpoConfig.clear();
        ZYPTableUtil.clear(pmsg4Orig.cpoConfig);

        // Clear Details
        pmsg4Orig.A.clear();
        ZYPTableUtil.clear(pmsg4Orig.A);

        // Clear Return Details
        pmsg4Orig.rtnDtl.clear();
        ZYPTableUtil.clear(pmsg4Orig.rtnDtl);

        // Clear Delivery Info
        pmsg4Orig.cpoDlvyInfoList.clear();
        ZYPTableUtil.clear(pmsg4Orig.cpoDlvyInfoList);

        // Clear Install Info
        pmsg4Orig.cpoIstlInfoList.clear();
        ZYPTableUtil.clear(pmsg4Orig.cpoIstlInfoList);

        // Clear Contact Person Info
        pmsg4Orig.cpoCtacPsnInfoList.clear();
        ZYPTableUtil.clear(pmsg4Orig.cpoCtacPsnInfoList);

        // Clear Site Survey Info
        pmsg4Orig.siteSrvInfoList.clear();
        ZYPTableUtil.clear(pmsg4Orig.siteSrvInfoList);

        // Clear Sales Credit
        pmsg4Orig.cpoSlsCr.clear();
        ZYPTableUtil.clear(pmsg4Orig.cpoSlsCr);


        boolean hasRebill = hasRebillLine(bizMsg);

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL2300_CCMsg invLine = bizMsg.C.no(i);
            //QC#15624 add Start
            String lineNum = invLine.cpoDtlLineNum_C1.getValue();
            String lineSubNum = invLine.cpoDtlLineSubNum_C1.getValue();
            if (!ZYPCommonFunc.hasValue(lineNum)) {
                // bill with equipment
                lineNum = String.valueOf(i + 1);
                lineSubNum = DEF_LINE_SUB_NUM;
            }
            //QC#15624 add End

            if (ZYPConstant.FLG_OFF_N.equals(invLine.openFlg_C1.getValue())) {
                continue;

                // Outbound & Open & Rebil Order exist---Case2/Case7
            } else if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())//
                    && ZYPConstant.FLG_ON_Y.equals(invLine.openFlg_C1.getValue())//
                    && hasRebill) {

                // Config dsCpoConfigPk != dsCpoConfigPk_C1
                if (!ZYPCommonFunc.hasValue(dsCpoConfigPk) || dsCpoConfigPk.compareTo(invLine.dsCpoConfigPk_C1.getValue()) != 0) {

                    //Config
                    setOrderConfig(bizMsg, invLine, pmsg4Orig, CONFIG_CATG.OUTBOUND, CONFIG_TP.ADD_TO_CONFIG);
                    ZYPEZDItemValueSetter.setValue(pmsg4Orig.cpoConfig.no(pmsg4Orig.cpoConfig.getValidCount() - 1).xxRqstTpCd, RQST_TP_CONFIG_MODIFY);
                    dsCpoConfigPk = invLine.dsCpoConfigPk_C1.getValue();
                }

                // 2017/01/11 QC#16669 MOD START
                // Detail
                // setOrderDetail(bizMsg, pmsg4Orig, invLine, null);

                // Check Partial Ship
                BigDecimal shipQtyAmt = getShipQtyAmt(bizMsg, pmsg4Orig, invLine);

                // Partial Ship
//                if (ZYPCommonFunc.hasValue(shipQtyAmt)) {
//                    ZYPEZDItemValueSetter.setValue(pmsg4Orig.A.no(pmsg4Orig.A.getValidCount() - 1).xxRqstTpCd_A1, RQST_TP_DTL_MODIFY);
//                    ZYPEZDItemValueSetter.setValue(pmsg4Orig.A.no(pmsg4Orig.A.getValidCount() - 1).ordQty_A1, shipQtyAmt);
//                    ZYPEZDItemValueSetter.setValue(pmsg4Orig.A.no(pmsg4Orig.A.getValidCount() - 1).ordCustUomQty_A1, shipQtyAmt);
//                    shipQtyAmt = null;
//
//                } else {
//                    ZYPEZDItemValueSetter.setValue(pmsg4Orig.A.no(pmsg4Orig.A.getValidCount() - 1).xxRqstTpCd_A1, RQST_TP_DTL_CANCEL);
//                }
                // QC#22106 2018/03/30 Add Start
//                if (!ZYPCommonFunc.hasValue(shipQtyAmt)) {
//                    // Detail
////                    setOrderDetail(bizMsg, pmsg4Orig, invLine, null);     //QC#15624 mod
//                    setOrderDetail(bizMsg, pmsg4Orig, invLine, null, lineNum, lineSubNum);
//                    ZYPEZDItemValueSetter.setValue(pmsg4Orig.A.no(pmsg4Orig.A.getValidCount() - 1).xxRqstTpCd_A1, RQST_TP_DTL_CANCEL);
//                }
                // 2017/01/11 QC#16669 MOD END
                // 2018/05/29 QC#21841 Add Start
                if (shipQtyAmt != null && BigDecimal.ZERO.compareTo(shipQtyAmt) < 0) {
                    continue;
                }
                // 2018/05/29 QC#21841 Add End
                setOrderDetail(bizMsg, pmsg4Orig, invLine, null, lineNum, lineSubNum);
                ZYPEZDItemValueSetter.setValue(pmsg4Orig.A.no(pmsg4Orig.A.getValidCount() - 1).xxRqstTpCd_A1, RQST_TP_DTL_CANCEL);
                // QC#22106 2018/03/30 Add End


                // Inbound & Open & RMA Cancel---Case4a
            } else if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())//
                    && ZYPConstant.CHKBOX_ON_Y.equals(invLine.xxChkBox_C1.getValue())) {

                // Config dsCpoConfigPk != dsCpoConfigPk_C1
                if (!ZYPCommonFunc.hasValue(dsCpoConfigPk) || dsCpoConfigPk.compareTo(invLine.dsCpoConfigPk_C1.getValue()) != 0) {

                    //Config
                    setOrderConfig(bizMsg, invLine, pmsg4Orig, CONFIG_CATG.INBOUND, CONFIG_TP.ADD_TO_CONFIG);
                    ZYPEZDItemValueSetter.setValue(pmsg4Orig.cpoConfig.no(pmsg4Orig.cpoConfig.getValidCount() - 1).xxRqstTpCd, RQST_TP_CONFIG_MODIFY);
                    dsCpoConfigPk = invLine.dsCpoConfigPk_C1.getValue();
                }


                // Return Detail
//                setOrderDetailReturn(bizMsg, pmsg4Orig, invLine, null);   //QC#15624 mod
                setOrderDetailReturn(bizMsg, pmsg4Orig, invLine, null, lineNum, lineSubNum);
                ZYPEZDItemValueSetter.setValue(pmsg4Orig.rtnDtl.no(pmsg4Orig.rtnDtl.getValidCount() - 1).xxRqstTpCd_B1, RQST_TP_DTL_CANCEL);
            }
        }
    }

    private boolean hasRebillLine(NWAL2300CMsg bizMsg) {

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL2300_CCMsg invLine = bizMsg.C.no(i);
            if (PULLDOWN_CD_C_R.equals(invLine.xxTpCd_C1.getValue()) || PULLDOWN_CD_RMA_RB.equals(invLine.xxTpCd_C1.getValue())) {
                return true;
            }
        }
        return false;
    }

    private BigDecimal getShipQtyAmt(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Orig, NWAL2300_CCMsg invLine) {

        S21SsmEZDResult ssmResult = null;
        // get Open Qty
        ssmResult = NWAL2300Query.getInstance().getShipQtyAmt(bizMsg, pmsg4Orig.glblCmpyCd.getValue()//
                , pmsg4Orig.cpoOrdNum.getValue()//
                , invLine.cpoDtlLineNum_C1.getValue()//
                , invLine.cpoDtlLineSubNum_C1.getValue());
        if (ssmResult.isCodeNormal()) {
            return (BigDecimal) ssmResult.getResultObject();
        }
        return null;
    }

    private boolean updateCreateOrder(NWAL2300CMsg bizMsg, String creCpoNum, String rblCpoNum) {

        // Update Credit Order
        if (ZYPCommonFunc.hasValue(creCpoNum)) {
            CPOTMsg cpoCreTMsg = new CPOTMsg();
            ZYPEZDItemValueSetter.setValue(cpoCreTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoCreTMsg.cpoOrdNum, creCpoNum);
            cpoCreTMsg = (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdate(cpoCreTMsg);

            if (cpoCreTMsg == null) {
                bizMsg.setMessageInfo(NWZM0073E);
                return false;
            }

            ZYPEZDItemValueSetter.setValue(cpoCreTMsg.reBillPairCpoOrdNum, rblCpoNum);
            ZYPEZDItemValueSetter.setValue(cpoCreTMsg.addOrigCpoOrdNum, bizMsg.cpoOrdNum_H1);
            S21FastTBLAccessor.update(cpoCreTMsg);
        }

        // Update Rebill Order
        if (ZYPCommonFunc.hasValue(rblCpoNum)) {
            CPOTMsg cpoRblTMsg = new CPOTMsg();
            ZYPEZDItemValueSetter.setValue(cpoRblTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoRblTMsg.cpoOrdNum, rblCpoNum);
            cpoRblTMsg = (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdate(cpoRblTMsg);

            if (cpoRblTMsg == null) {
                bizMsg.setMessageInfo(NWZM0073E);
                return false;
            }

            ZYPEZDItemValueSetter.setValue(cpoRblTMsg.reBillPairCpoOrdNum, creCpoNum);
            ZYPEZDItemValueSetter.setValue(cpoRblTMsg.addOrigCpoOrdNum, bizMsg.cpoOrdNum_H1);
            S21FastTBLAccessor.update(cpoRblTMsg);
        }
        return true;
    }

    private boolean insertProfitability(NWAL2300CMsg bizMsg, String cpoOrdNum, NWZC150001PMsg pmsg) {

        // Call Profitability Calculation API
        NWZC156001PMsg pMsg = new NWZC156001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, MODE_CREDIT);
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, pmsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, pmsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dlrRefNum, pmsg.dlrRefNum);

        List<NWZC156002PMsg> prftApiPMsg2List = new ArrayList<NWZC156002PMsg>();
        NWZC156001 prftApi = new NWZC156001();
        prftApi.execute(pMsg, prftApiPMsg2List, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            bizMsg.setMessageInfo(S21ApiUtil.getXxMsgIdList(pMsg).get(0));
            return false;
        }
        for (NWZC156002PMsg prftApiPMsg2 : prftApiPMsg2List) {
            if (S21ApiUtil.isXxMsgId(prftApiPMsg2)) {
                bizMsg.setMessageInfo(S21ApiUtil.getXxMsgIdList(prftApiPMsg2).get(0));
                return false;
            }
        }
        return true;
    }

    /**
     * insert comments
     * @param bizMsg NWAL2300CMsg
     * @param newCreCpoOrderNum String
     * @param newRebCpoOrderNum String
     */
    private boolean insertComments(NWAL2300CMsg bizMsg, String newCreCpoOrderNum, String newRebCpoOrderNum) {

        String comments = bizMsg.xxOrdMemoTxt_H1.getValue();
        // Get Original Invoice Comment
        MSG_TXT_DTLTMsgArray invCmtOrig = getMSG_TXT_DTLTMsgTMsgList(bizMsg);

        if (!ZYPCommonFunc.hasValue(comments) && invCmtOrig == null) {
            return true;
        }

        if (!insertMsgTxtDtl(bizMsg, newCreCpoOrderNum, invCmtOrig)) {
            return false;
        }

        if (!insertMsgTxtDtl(bizMsg, newRebCpoOrderNum, invCmtOrig)) {
            return false;
        }
        return true;
    }

    private boolean insertMsgTxtDtl(NWAL2300CMsg bizMsg, String cpoOrdNum, MSG_TXT_DTLTMsgArray invCmtOrig) {

        if (!ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return true;
        }

        NWZC042001PMsg atPMsg = new NWZC042001PMsg();

        ZYPEZDItemValueSetter.setValue(atPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(atPMsg.cpoOrdNum, cpoOrdNum);
        int i = 0;
        for (String msgTxtInfoTxt : separeteCrRebComment(bizMsg.xxOrdMemoTxt_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(atPMsg.A.no(i).txtTpCd, TXT_TP.CREDIT_REBILL_COMMENT);
            ZYPEZDItemValueSetter.setValue(atPMsg.A.no(i).msgTxtInfoTxt, msgTxtInfoTxt);
            i++;
        }
        atPMsg.A.setValidCount(i);

        // Copy Invoice Comment from original order.
        if (invCmtOrig != null) {
            for (int j = 0; j < invCmtOrig.getValidCount(); j++) {
                MSG_TXT_DTLTMsg cmtOrig = invCmtOrig.no(j);
                NWZC042001_APMsg cmt = atPMsg.A.no(atPMsg.A.getValidCount());
                EZDMsg.copy(cmtOrig, null, cmt, null);
                atPMsg.A.setValidCount(atPMsg.A.getValidCount() + 1);
            }
        }

        NWZC042001 atApi = new NWZC042001();
        atApi.execute(atPMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(atPMsg)) {
            List<String> ml = S21ApiUtil.getXxMsgIdList(atPMsg);
            bizMsg.setMessageInfo(ml.get(0));
            return false;
        }
        return true;
    }

    private List<String> separeteCrRebComment(String crRbCmtTxt) {

        List<String> ls = new ArrayList<String>();
        for (int i = 0; i < crRbCmtTxt.length(); i += 65) {
            String s = S21StringUtil.subStringByLength(crRbCmtTxt, i, 65);
            ls.add(s);
        }
        return ls;
    }

    /**
     * insertCreditRebillReason
     * @param bizMsg NWAL2300CMsg
     * @param cpoOrdNumCredit String
     * @param cpoOrdNumRebill String
     * @return boolean
     */
    private boolean insertCreditRebillReason(NWAL2300CMsg bizMsg, String cpoOrdNumCredit, String cpoOrdNumRebill) {

        // get glblCmpyCd
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        StringBuilder rsnNmCr = new StringBuilder();
        StringBuilder rsnNmRebill = new StringBuilder();
        // get select Reason CD
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL2300_ACMsg aBizMsg = bizMsg.A.no(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(aBizMsg.xxChkBox_A1.getValue())) {
                String reasonCd = aBizMsg.crRebilRsnCd_A1.getValue();

                // For Credit
                DS_CPO_CR_REBIL_RSNTMsg dsCpoCrRebilRsnTMsg = new DS_CPO_CR_REBIL_RSNTMsg();
                ZYPEZDItemValueSetter.setValue(dsCpoCrRebilRsnTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsCpoCrRebilRsnTMsg.cpoOrdNum, cpoOrdNumCredit);
                ZYPEZDItemValueSetter.setValue(dsCpoCrRebilRsnTMsg.crRebilRsnCd, reasonCd);
                S21FastTBLAccessor.insert(dsCpoCrRebilRsnTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsCpoCrRebilRsnTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NWAM0790E, new String[] {"Credit Rebill Reason" });
                    return false;
                }
                rsnNmCr.append(aBizMsg.crRebilRsnNm_A1.getValue()).append(LINE_SEPARATOR);
                // For Rebill
                if (ZYPCommonFunc.hasValue(cpoOrdNumRebill)) {
                    dsCpoCrRebilRsnTMsg = new DS_CPO_CR_REBIL_RSNTMsg();
                    ZYPEZDItemValueSetter.setValue(dsCpoCrRebilRsnTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dsCpoCrRebilRsnTMsg.cpoOrdNum, cpoOrdNumRebill);
                    ZYPEZDItemValueSetter.setValue(dsCpoCrRebilRsnTMsg.crRebilRsnCd, reasonCd);
                    S21FastTBLAccessor.insert(dsCpoCrRebilRsnTMsg);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsCpoCrRebilRsnTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NWAM0790E, new String[] {"Credit Rebill Reason" });
                        return false;
                    }
                    rsnNmRebill.append(aBizMsg.crRebilRsnNm_A1.getValue()).append(LINE_SEPARATOR);
                }
            }
        }
        if (rsnNmCr.length() > 0) {
            registerAttData(bizMsg, rsnNmCr.toString(), cpoOrdNumCredit);
        }
        if (rsnNmRebill.length() > 0) {
            registerAttData(bizMsg, rsnNmRebill.toString(), cpoOrdNumRebill);
        }
        return true;
    }

    private void registerAttData(NWAL2300CMsg bizMsg, String cmntTxt, String cpoOrdNum) {

        ZYPFileUpDownParameter params = new ZYPFileUpDownParameter();
        params.setBusinessId("NWAL1500");
        params.setAttDataGrp(cpoOrdNum);
        params.setAttDataCmnt(cmntTxt);
        params.setBusinessNm("Order Entry");
        params.setAttDataNm("Order Number");
        params.setAttDataTpCd("NT"); // Note
        params.setAttDocTpCd("110"); // Order Attachment

        ZYPFileUpDown.uploadNote(params);
    }

    private boolean callApi(NWAL2300CMsg bizMsg, NWZC150001PMsg cpoUpdApiMsg) {

        if (cpoUpdApiMsg.A.getValidCount() == 0//
                && cpoUpdApiMsg.rtnDtl.getValidCount() == 0) {
            return true;
        }

        /**************************************************
         * NWZC150001 : DS CPO Update API
         **************************************************/
        final List<NWZC150002PMsg> cpoUpdApiOutMsgList = new ArrayList<NWZC150002PMsg>();
        final List<NWZC150003PMsg> cpoUpdApiOutMsgList03 = new ArrayList<NWZC150003PMsg>();

        new NWZC150001().execute(cpoUpdApiMsg, cpoUpdApiOutMsgList, cpoUpdApiOutMsgList03, ONBATCH_TYPE.ONLINE);

        //        boolean rc = true;
        for (int i = 0; i < cpoUpdApiMsg.xxMsgIdList.getValidCount(); i++) {
            final String xxMsgId = cpoUpdApiMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            if (xxMsgId.endsWith("E")) {
                bizMsg.setMessageInfo(xxMsgId);
                //                rc = false;
                return false;
            }
        }
        for (NWZC150002PMsg pMsg : cpoUpdApiOutMsgList) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (xxMsgId.endsWith("E")) {
                    bizMsg.setMessageInfo(xxMsgId);
                    //                    rc = false;
                    return false;
                }
            }
        }
        for (NWZC150003PMsg pMsg : cpoUpdApiOutMsgList03) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (xxMsgId.endsWith("E")) {
                    bizMsg.setMessageInfo(xxMsgId);
                    //                    rc = false;
                    return false;
                }
            }
        }
        //        return rc;
        return true;

    }

    private static void setPricingApiParam(NWAL2300CMsg bizMsg, NWZC157001PMsg prcApiPMsg, String glblCmpyCd, String processMode, String dt, String prcCtxTpCd, String dsOrdCatgCd, String dsOrdTpCd, String dsAcctNum, String cpoOrdNum) {

        // Header
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, glblCmpyCd);
        prcApiPMsg.xxModeCd.setValue(processMode);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, dt);
        prcApiPMsg.prcCtxTpCd.setValue(prcCtxTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, dsOrdTpCd);
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getLineBizTpCd(bizMsg, glblCmpyCd, dt, cpoOrdNum);
        if (ssmResult.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, (String) ssmResult.getResultObject());
        }
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, dsAcctNum);
        prcApiPMsg.csmpNum.clear();
        prcApiPMsg.dlrRefNum.clear();
        prcApiPMsg.prcContrNum.clear();
        prcApiPMsg.coaBrCd.clear();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);
    }

    private String getSerNum(NWZC150001PMsg pMsg, NWAL2300_CCMsg invLineMsg) {

        String rtnStr = null;
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getSerNum(pMsg.glblCmpyCd.getValue()//
                , invLineMsg.invNum_C1.getValue()//
                , invLineMsg.invBolLineNum_C1.getValue()//
                , invLineMsg.invLineNum_C1.getValue()//
                , invLineMsg.invLineSubNum_C1.getValue()//
                , invLineMsg.invLineSubTrxNum_C1.getValue());
        if (ssmResult.isCodeNormal()) {
            rtnStr = (String) ssmResult.getResultObject();
        }
        return rtnStr;
    }

    private BigDecimal getSvcMachMstrPk(NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg dtlPMsg) {

        if (!ZYPCommonFunc.hasValue(dtlPMsg.svcConfigMstrPk_B1)) {
            return null;
        }

        BigDecimal rtnBd = null;
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getSvcMachMstrPk(pMsg.glblCmpyCd.getValue()//
                , dtlPMsg.svcConfigMstrPk_B1.getValue()//
                , (String) dtlPMsg.mdseCd_B1.getValue()//
                , dtlPMsg.serNum_B1.getValue());
        if (ssmResult.isCodeNormal()) {
            rtnBd = (BigDecimal) ssmResult.getResultObject();
        }
        return rtnBd;
    }

    // 2018/12/19 S21_NA#29561 Del Start
//    private String getRtlWhCd(NWZC150001PMsg pMsg, String shipFromInvtyLocCd) {
//
//        String rtnStr = null;
//        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getRtlWhCd(pMsg.glblCmpyCd.getValue(), shipFromInvtyLocCd);
//        if (ssmResult.isCodeNormal()) {
//            rtnStr = (String) ssmResult.getResultObject();
//        }
//        return rtnStr;
//    }
//
//    private String getRtlSwhCd(NWAL2300CMsg bizMsg, NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg dtlPMsg) {
//
//        String rtnStr = null;
//        NLZC215001PMsg nlzc215001PMsg = new NLZC215001PMsg();
//        nlzc215001PMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
//        nlzc215001PMsg.slsDt.setValue(pMsg.slsDt.getValue());
//        nlzc215001PMsg.xxModeCd.setValue(NLZC215001Constant.MODE_RMA);
//        nlzc215001PMsg.mdseCd.setValue(dtlPMsg.mdseCd_B1.getValue());
//        nlzc215001PMsg.dsOrdCatgCd.setValue(pMsg.dsOrdCatgCd.getValue());
//        nlzc215001PMsg.destRtlWhCd.setValue(dtlPMsg.rtlWhCd_B1.getValue());
//        new NLZC215001().execute(nlzc215001PMsg, ONBATCH_TYPE.ONLINE);
//
//        for (int i = 0; i < nlzc215001PMsg.xxMsgIdList.getValidCount(); i++) {
//            final String xxMsgId = nlzc215001PMsg.xxMsgIdList.no(i).xxMsgId.getValue();
//            if (xxMsgId.endsWith("E")) {
//                bizMsg.setMessageInfo(xxMsgId
//                , new String[] {nlzc215001PMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() //
//                        , nlzc215001PMsg.xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue()} //
//                );
//                return rtnStr;
//            }
//        }
//
//        if (ZYPCommonFunc.hasValue(nlzc215001PMsg.destRtlSwhCd)) {
//            rtnStr = nlzc215001PMsg.destRtlSwhCd.getValue();
//        }
//        return rtnStr;
//    }

//    private NWZC180001PMsg getDefultWhSwhCd(NWAL2300CMsg bizMsg, NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg dtlPMsg) {
//        // case of drop ship then WH/SWH will be populate default value by NWZC1800 Default WH API
//        NWZC180001PMsg whPMsg = new NWZC180001PMsg();
//        ZYPEZDItemValueSetter.setValue(whPMsg.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(whPMsg.xxModeCd, NWZC180001Constant.PROC_MODE_INBD);
//        ZYPEZDItemValueSetter.setValue(whPMsg.slsDt, pMsg.slsDt);
//        ZYPEZDItemValueSetter.setValue(whPMsg.dsOrdCatgCd, pMsg.dsOrdCatgCd);
//        ZYPEZDItemValueSetter.setValue(whPMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
//        ZYPEZDItemValueSetter.setValue(whPMsg.mdseCd, dtlPMsg.mdseCd_B1.getValue());
//        ZYPEZDItemValueSetter.setValue(whPMsg.postCd, dtlPMsg.shipToPostCd_B1);
//        ZYPEZDItemValueSetter.setValue(whPMsg.ordQty, dtlPMsg.ordQty_B1.getValue());
//        ZYPEZDItemValueSetter.setValue(whPMsg.dsRtrnRsnCd, RTRN_RSN.REGULAR_RETURN);
//
//        // call NWZC1800 Default WH API
//        new NWZC180001().execute(whPMsg, ONBATCH_TYPE.ONLINE);
//
//        if (S21ApiUtil.isXxMsgId(whPMsg)) {
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
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
//        return whPMsg;
//    }
    // 2018/12/19 S21_NA#29561 Del End

    private static BigDecimal createInPoundWt(String glblCmpyCd, String mdseCd, BigDecimal ordQty) {

        // VarCharConst
        String pkgUomCd = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_PKG_UOM_FOR_PRC, glblCmpyCd);
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getInPoundWt(glblCmpyCd, pkgUomCd, mdseCd);
        if (ssmResult.isCodeNormal()) {
            BigDecimal inPoundWt = (BigDecimal) ssmResult.getResultObject();
            return inPoundWt.multiply(ordQty);
        }
        return BigDecimal.ZERO;
    }

    /**
     * Call NMZC6110 Default Carrier API
     * @param bizMsg NWAL2300CMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @param dsAcctNum String
     * @return NMZC611001PMsg
     */
    public static NMZC611001PMsg callDefaultCarrierApi(NWAL2300CMsg bizMsg, String glblCmpyCd, String slsDt, String dsAcctNum) {

        NMZC611001PMsg pMsg = new NMZC611001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, dsAcctNum);
        new NMZC611001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            if (xxMsgId.endsWith("E")) {
                bizMsg.setMessageInfo(xxMsgId);
                return pMsg;
            }
        }
        return pMsg;
    }

    private INVTMsgArray getINVTMsgList(NWAL2300CMsg bizMsg) {

        INVTMsg tMsg = new INVTMsg();
        tMsg.setSQLID("006");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum_H1.getValue());

        INVTMsgArray tMsgArray = (INVTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray == null) {
            bizMsg.setMessageInfo(NWAM0809E, new String[]{"INV"});
            return null;
        }
        return tMsgArray;
    }

    // 2018/04/06 QC#22122 Mod Start
    //private INV_BOLTMsgArray getINV_BOLTMsgList(NWAL2300CMsg bizMsg) {
    private INV_BOLTMsgArray getINV_BOLTMsgList(NWAL2300CMsg bizMsg, String invNum) {
    // 2018/04/06 QC#22122 Mod End

        // 2018/04/06 QC#22122 Mod Start
        //S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getINV_BOL(bizMsg, bizMsg.cpoOrdNum_H1.getValue());
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getINV_BOL(bizMsg, bizMsg.cpoOrdNum_H1.getValue(), invNum);
        // 2018/04/06 QC#22122 Mod End
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NWAM0809E, new String[]{"INV_BOL"});
            return null;
        }

        INV_BOLTMsgArray tMsgArray = new INV_BOLTMsgArray();
        List<INV_BOLTMsg> list = (List<INV_BOLTMsg>) ssmResult.getResultObject();

        INV_BOLTMsg[] tmsgs = (INV_BOLTMsg[]) list.toArray(new INV_BOLTMsg[list.size()]);
        tMsgArray.setMsgList(tmsgs);
        tMsgArray.setValidCount(list.size());

        return tMsgArray;
    }

    private CPOTMsg getCPOTMsg(NWAL2300CMsg bizMsg) {

        CPOTMsg tMsg = new CPOTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, bizMsg.cpoOrdNum_H1);
        tMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return null;
        }
        return tMsg;
    }

    private CPO_DTLTMsg geCPO_DTLTMsg(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {

        CPO_DTLTMsg tMsg = new CPO_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, bizMsg.cpoOrdNum_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlLineNum, detailDataMsg.cpoDtlLineNum_C1);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlLineSubNum, detailDataMsg.cpoDtlLineSubNum_C1);
        tMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return null;
        }
        return tMsg;
    }

    private DS_CPO_RTRN_DTLTMsg geDS_CPO_RTRN_DTLTMsg(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {

        DS_CPO_RTRN_DTLTMsg tMsg = new DS_CPO_RTRN_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, bizMsg.cpoOrdNum_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.dsCpoRtrnLineNum, detailDataMsg.cpoDtlLineNum_C1);
        ZYPEZDItemValueSetter.setValue(tMsg.dsCpoRtrnLineSubNum, detailDataMsg.cpoDtlLineSubNum_C1);
        tMsg = (DS_CPO_RTRN_DTLTMsg) S21FastTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return null;
        }
        return tMsg;
    }

    // 2018/04/06 QC#22122 Mod Start
    //private INVTMsg getINVTMsg(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {
    private INVTMsg getINVTMsg(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg, String invNum) {
    // 2018/04/06 QC#22122 Mod End

        INVTMsg tMsg = new INVTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        // 2018/04/06 QC#22122 Mod Start
        //ZYPEZDItemValueSetter.setValue(tMsg.invNum, detailDataMsg.invNum_C1);
        if (!ZYPCommonFunc.hasValue(invNum)) {
            ZYPEZDItemValueSetter.setValue(tMsg.invNum, detailDataMsg.invNum_C1);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.invNum, invNum);
        }
        // 2018/04/06 QC#22122 Mod End
        tMsg = (INVTMsg) S21FastTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            bizMsg.setMessageInfo(NWAM0809E, new String[]{"INV"});
            return null;
        }
        return tMsg;
    }

    private INV_BOLTMsg getINV_BOLTMsg(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {

        INV_BOLTMsg tMsg = new INV_BOLTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.invNum, detailDataMsg.invNum_C1);
        ZYPEZDItemValueSetter.setValue(tMsg.invBolLineNum, detailDataMsg.invBolLineNum_C1);
        tMsg = (INV_BOLTMsg) S21FastTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            bizMsg.setMessageInfo(NWAM0809E, new String[]{"INV_BOL"});
            return null;
        }
        return tMsg;
    }

    private INV_LINETMsg getINV_LINETMsg(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {

        INV_LINETMsg tMsg = new INV_LINETMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.invNum, detailDataMsg.invNum_C1);
        ZYPEZDItemValueSetter.setValue(tMsg.invBolLineNum, detailDataMsg.invBolLineNum_C1);
        ZYPEZDItemValueSetter.setValue(tMsg.invLineNum, detailDataMsg.invLineNum_C1);
        ZYPEZDItemValueSetter.setValue(tMsg.invLineSubNum, detailDataMsg.invLineSubNum_C1);
        ZYPEZDItemValueSetter.setValue(tMsg.invLineSubTrxNum, detailDataMsg.invLineSubTrxNum_C1);
        tMsg = (INV_LINETMsg) S21FastTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            bizMsg.setMessageInfo(NWAM0809E, new String[]{"INV_LINE"});
            return null;
        }
        return tMsg;
    }

//    private DS_INV_SLS_CRTMsgArray getDS_INV_SLS_CRTMsgList(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {
    private List<DS_INV_SLS_CRTMsg> getDS_INV_SLS_CRTMsgList(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {

        // 2017/12/25 AC#23081 Mod Start
//        DS_INV_SLS_CRTMsg tMsg = new DS_INV_SLS_CRTMsg();
//        tMsg.setSQLID("002");
//        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        tMsg.setConditionValue("invNum01", detailDataMsg.invNum_C1.getValue());
//        tMsg.setConditionValue("invBolLineNum01", detailDataMsg.invBolLineNum_C1.getValue());
//        tMsg.setConditionValue("invLineNum01", detailDataMsg.invLineNum_C1.getValue());
//        tMsg.setConditionValue("invLineSubNum01", detailDataMsg.invLineSubNum_C1.getValue());
//        tMsg.setConditionValue("invTrxLineSubNum01", detailDataMsg.invLineSubTrxNum_C1.getValue());
//        DS_INV_SLS_CRTMsgArray tMsgArray = (DS_INV_SLS_CRTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
//
//        if (null == tMsgArray || tMsgArray.getValidCount() == 0) {
////            bizMsg.setMessageInfo(NWAM0809E, new String[]{"DS_INV_SLS_CR"}); // TODO Report to Mr.Nagashima.
//            return null;
//        }
//        return tMsgArray;

        List<DS_INV_SLS_CRTMsg> rslt = null;
        S21SsmEZDResult ssmRslt = NWAL2300Query.getInstance().getDsInvSlsCrTMsgList(bizMsg, detailDataMsg);
        if (!ssmRslt.isCodeNormal()) {
            rslt = new ArrayList<DS_INV_SLS_CRTMsg>(0);
        } else {
            rslt = (List<DS_INV_SLS_CRTMsg>) ssmRslt.getResultObject();
        }
        return rslt;
        // 2017/12/25 QC#23081 Mod End
    }

    private DS_CPO_CONFIGTMsg getDS_CPO_CONFIGTMsg(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {

        if (!ZYPCommonFunc.hasValue(detailDataMsg.dsCpoConfigPk_C1)) {
            return null;
        }

        DS_CPO_CONFIGTMsg tMsg = new DS_CPO_CONFIGTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsCpoConfigPk, detailDataMsg.dsCpoConfigPk_C1);
        tMsg = (DS_CPO_CONFIGTMsg) S21FastTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            bizMsg.setMessageInfo(NWAM0809E, new String[]{"DS_CPO_CONFIG"});
            return null;
        }
        return tMsg;
    }

    private DS_CPO_SLS_CRTMsgArray getDS_CPO_SLS_CRTMsg(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {

        if (!ZYPCommonFunc.hasValue(detailDataMsg.dsCpoConfigPk_C1)) {
            return null;
        }

        DS_CPO_SLS_CRTMsg tMsg = new DS_CPO_SLS_CRTMsg();

        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("dsCpoConfigPk01", detailDataMsg.dsCpoConfigPk_C1.getValue());
        DS_CPO_SLS_CRTMsgArray tMsgArray = (DS_CPO_SLS_CRTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (null == tMsgArray || tMsgArray.getValidCount() == 0) {
            bizMsg.setMessageInfo(NWAM0809E, new String[]{"DS_CPO_SLS_CR"});
            return null;
        }
        return tMsgArray;
    }

    private DS_CPO_DELY_INFOTMsg getDS_CPO_DELY_INFOTMsgHeader(NWAL2300CMsg bizMsg) {

        DS_CPO_DELY_INFOTMsg tMsg = new DS_CPO_DELY_INFOTMsg();

        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum_H1.getValue());
        DS_CPO_DELY_INFOTMsgArray tMsgArray = (DS_CPO_DELY_INFOTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (null == tMsgArray || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray.no(0);
    }

    private DS_CPO_DELY_INFOTMsg getDS_CPO_DELY_INFOTMsgConfig(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {

        if (!ZYPCommonFunc.hasValue(detailDataMsg.dsCpoConfigPk_C1)) {
            return null;
        }

        DS_CPO_DELY_INFOTMsg tMsg = new DS_CPO_DELY_INFOTMsg();

        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("dsCpoConfigPk01", detailDataMsg.dsCpoConfigPk_C1.getValue());
        DS_CPO_DELY_INFOTMsgArray tMsgArray = (DS_CPO_DELY_INFOTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (null == tMsgArray || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray.no(0);
    }

    private DS_SITE_SRVYTMsg getDS_SITE_SRVYTMsgHeader(NWAL2300CMsg bizMsg) {

        DS_SITE_SRVYTMsg tMsg = new DS_SITE_SRVYTMsg();

        tMsg.setSQLID("005");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum_H1.getValue());
        DS_SITE_SRVYTMsgArray tMsgArray = (DS_SITE_SRVYTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (null == tMsgArray || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray.no(0);
    }

    private DS_SITE_SRVYTMsg getDS_SITE_SRVYTMsgConfig(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {

        if (!ZYPCommonFunc.hasValue(detailDataMsg.dsCpoConfigPk_C1)) {
            return null;
        }

        DS_SITE_SRVYTMsg tMsg = new DS_SITE_SRVYTMsg();

        tMsg.setSQLID("006");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("dsCpoConfigPk01", detailDataMsg.dsCpoConfigPk_C1.getValue());
        DS_SITE_SRVYTMsgArray tMsgArray = (DS_SITE_SRVYTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (null == tMsgArray || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray.no(0);
    }

    private DS_CPO_ISTL_INFOTMsg getDS_CPO_ISTL_INFOTMsgHeader(NWAL2300CMsg bizMsg) {

        DS_CPO_ISTL_INFOTMsg tMsg = new DS_CPO_ISTL_INFOTMsg();

        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum_H1.getValue());
        DS_CPO_ISTL_INFOTMsgArray tMsgArray = (DS_CPO_ISTL_INFOTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (null == tMsgArray || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray.no(0);
    }

    private DS_CPO_ISTL_INFOTMsg getDS_CPO_ISTL_INFOTMsgConfig(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {

        if (!ZYPCommonFunc.hasValue(detailDataMsg.dsCpoConfigPk_C1)) {
            return null;
        }

        DS_CPO_ISTL_INFOTMsg tMsg = new DS_CPO_ISTL_INFOTMsg();

        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("dsCpoConfigPk01", detailDataMsg.dsCpoConfigPk_C1.getValue());
        DS_CPO_ISTL_INFOTMsgArray tMsgArray = (DS_CPO_ISTL_INFOTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (null == tMsgArray || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray.no(0);
    }

    private DS_CPO_CTAC_PSNTMsgArray getDS_CPO_CTAC_PSNTMsgHeader(NWAL2300CMsg bizMsg) {

        DS_CPO_CTAC_PSNTMsg tMsg = new DS_CPO_CTAC_PSNTMsg();

        tMsg.setSQLID("009");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum_H1.getValue());
        DS_CPO_CTAC_PSNTMsgArray tMsgArray = (DS_CPO_CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (null == tMsgArray || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray;
    }

    private DS_CPO_CTAC_PSNTMsgArray getDS_CPO_CTAC_PSNTMsgConfig(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {

        if (!ZYPCommonFunc.hasValue(detailDataMsg.dsCpoConfigPk_C1)) {
            return null;
        }

        DS_CPO_CTAC_PSNTMsg tMsg = new DS_CPO_CTAC_PSNTMsg();

        tMsg.setSQLID("012");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("dsCpoConfigPk01", detailDataMsg.dsCpoConfigPk_C1.getValue());
        DS_CPO_CTAC_PSNTMsgArray tMsgArray = (DS_CPO_CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (null == tMsgArray || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray;
    }

    private ORD_PRC_CALC_BASETMsgArray getORD_PRC_CALC_BASETMsgList(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {

        ORD_PRC_CALC_BASETMsg tMsg = new ORD_PRC_CALC_BASETMsg();

        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum_H1.getValue());
        tMsg.setConditionValue("cpoDtlLineNum01", detailDataMsg.cpoDtlLineNum_C1.getValue());
        tMsg.setConditionValue("cpoDtlLineSubNum01", detailDataMsg.cpoDtlLineSubNum_C1.getValue());
        ORD_PRC_CALC_BASETMsgArray tMsgArray = (ORD_PRC_CALC_BASETMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (null == tMsgArray || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray;
    }

    private CPO_RTRN_PRC_CALC_BASETMsgArray getCPO_RTRN_PRC_CALC_BASETMsgList(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {

        CPO_RTRN_PRC_CALC_BASETMsg tMsg = new CPO_RTRN_PRC_CALC_BASETMsg();

        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum_H1.getValue());
        tMsg.setConditionValue("dsCpoRtrnLineNum01", detailDataMsg.cpoDtlLineNum_C1.getValue());
        tMsg.setConditionValue("dsCpoRtrnLineSubNum01", detailDataMsg.cpoDtlLineSubNum_C1.getValue());
        CPO_RTRN_PRC_CALC_BASETMsgArray tMsgArray = (CPO_RTRN_PRC_CALC_BASETMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (null == tMsgArray || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray;
    }

    private MSG_TXT_DTLTMsgArray getMSG_TXT_DTLTMsgTMsgList(NWAL2300CMsg bizMsg) {

        MSG_TXT_DTLTMsg tMsg = new MSG_TXT_DTLTMsg();

        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum_H1.getValue());
        tMsg.setConditionValue("txtTpCd01", TXT_TP.INVOICE_COMMENT);
        MSG_TXT_DTLTMsgArray tMsgArray = (MSG_TXT_DTLTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (null == tMsgArray || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray;
    }

    // QC#16568 2016/12/27 Add Start
    private DS_INV_SLS_CRTMsg getDS_INV_SLS_CRTMsgForDeviation(NWAL2300CMsg bizMsg, NWAL2300_CCMsg detailDataMsg) {

        DS_INV_SLS_CRTMsg tMsg = new DS_INV_SLS_CRTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("invNum01", detailDataMsg.invNum_C1.getValue());
        tMsg.setConditionValue("invBolLineNum01", detailDataMsg.invBolLineNum_C1.getValue());
        tMsg.setConditionValue("invLineNum01", detailDataMsg.invLineNum_C1.getValue());
        tMsg.setConditionValue("invLineSubNum01", detailDataMsg.invLineSubNum_C1.getValue());
        tMsg.setConditionValue("invTrxLineSubNum01", detailDataMsg.invLineSubTrxNum_C1.getValue());
        tMsg.setConditionValue("invLineSplTpCd01", INV_LINE_SPL_TP.DEVIATION);
        DS_INV_SLS_CRTMsgArray tMsgArray = (DS_INV_SLS_CRTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (null == tMsgArray || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray.no(0);
    }
    // QC#16568 2016/12/27 Add End

    private BigDecimal getSpecCondPrcPk(NWAL2300CMsg bizMsg, String prcCondTpCd) {

        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getSpecCondPrcPk(bizMsg, prcCondTpCd);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        return (BigDecimal) ssmResult.getResultObject();
    }

    private static String getDt(NWAL2300CMsg bizMsg, String invDt, String category, String crRebilCd) {

        String dt = null;
        if (CR_REBIL.CREDIT.equals(crRebilCd)) { // QC#12892 ADD
            return invDt;
        }
        if (REASON_CD_I.equals(category)) {
            dt = invDt;
        } else {
            dt = bizMsg.slsDt.getValue();
        }
        return dt;
    }

    private static String getPrcCatgCd(NWAL2300CMsg bizMsg, String glblCmpyCd, String processMode, String dt, String prcCtxTpCd, String dsOrdCatgCd, String dsOrdTpCd, String dsAcctNum, String cpoOrdNum) {

        String prcCatgCd = "";
        // Pricing API PMsg
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        setPricingApiParam(bizMsg, prcApiPMsg, glblCmpyCd, processMode, dt, prcCtxTpCd, dsOrdCatgCd, dsOrdTpCd, dsAcctNum, cpoOrdNum);
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);
        if (0 < prcApiPMsg.xxPrcList.getValidCount()) {
            prcCatgCd = prcApiPMsg.xxPrcList.no(0).prcCatgCd.getValue();
        }
        if (!ZYPCommonFunc.hasValue(prcCatgCd)) {
            prcCatgCd = "0";
        }
        return prcCatgCd;
    }

    private static boolean hasErr(NWAL2300CMsg bizMsg) {
        if ("E".equals(bizMsg.getMessageKind())) {
            return true;
        }
        return false;
    }

    private static String getPmtTermCashDiscCd(NWAL2300CMsg bizMsg, String pmtTermCashDiscCd, String crRebilCd) {

        if (isPmtTermCashDiscCC(bizMsg, pmtTermCashDiscCd)) {

            if (CR_REBIL.CREDIT.equals(crRebilCd)) {
                return ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NWAL2300_PMT_TERM_CC_CR, bizMsg.glblCmpyCd.getValue());

            } else {
                return ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NWAL2300_PMT_TERM_CC_RB, bizMsg.glblCmpyCd.getValue());
            }

        } else {
            if (CR_REBIL.CREDIT.equals(crRebilCd)) {
                return ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NWAL2300_PMT_TERM_EX_CC_CR, bizMsg.glblCmpyCd.getValue());

            } else {
                return pmtTermCashDiscCd;
            }
        }
    }

    private static boolean isPmtTermCashDiscCC(NWAL2300CMsg bizMsg, String pmtTermCashDiscCd) {

        PMT_TERM_CASH_DISCTMsg pmtTermCashDisc = new PMT_TERM_CASH_DISCTMsg();
        ZYPEZDItemValueSetter.setValue(pmtTermCashDisc.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmtTermCashDisc.pmtTermCashDiscCd, pmtTermCashDiscCd);
        pmtTermCashDisc = (PMT_TERM_CASH_DISCTMsg) S21CacheTBLAccessor.findByKey(pmtTermCashDisc);

        if (pmtTermCashDisc != null && ZYPConstant.FLG_ON_Y.equals(pmtTermCashDisc.pmtCcFlg.getValue())) {
            return true;
        }
        return false;
    }

    private static String getPmtTermCd(NWAL2300CMsg bizMsg, String pmtTermCashDiscCd, String crRebilCd) {

        String convPmtTermCashDiscCd = getPmtTermCashDiscCd(bizMsg, pmtTermCashDiscCd, crRebilCd);
        if (!ZYPCommonFunc.hasValue(convPmtTermCashDiscCd)) {
            return null;
        }
        PMT_TERM_CASH_DISCTMsg pmtTermCashDisc = new PMT_TERM_CASH_DISCTMsg();
        ZYPEZDItemValueSetter.setValue(pmtTermCashDisc.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmtTermCashDisc.pmtTermCashDiscCd, convPmtTermCashDiscCd);
        pmtTermCashDisc = (PMT_TERM_CASH_DISCTMsg) S21CacheTBLAccessor.findByKey(pmtTermCashDisc);

        if (pmtTermCashDisc != null) {
            return pmtTermCashDisc.pmtTermCd.getValue();
        }
        return null;
    }

    private void setShellRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Rebill, NSZC115001PMsg shellPmsg) {

        // 2018/05/09 QC#25030 Add Start
        if (!callShell) {
            return;
        }
        // 2018/05/09 QC#25030 Add End
        ZYPEZDItemValueSetter.setValue(shellPmsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shellPmsg.xxProcMd, NSZC115001Constant.PROC_MODE_NEW);
        ZYPEZDItemValueSetter.setValue(shellPmsg.slsDt, bizMsg.slsDt);

        setShellSvcConfigRefRebill(bizMsg, pmsg4Rebill, shellPmsg);
        setShellSvcDtlRebill(bizMsg, shellPmsg);
        setShellSvcPrcRebill(bizMsg, shellPmsg);
        setShellSvcAddlChrgPrcRebill(bizMsg, shellPmsg);
        setShellSvcAddlBasePrcRebill(bizMsg, pmsg4Rebill, shellPmsg);
        setShellSvcUsgPrcRebill(bizMsg, shellPmsg);
    }

    private void setShellSvcConfigRefRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Rebill, NSZC115001PMsg shellPmsg) {

        for (int i = 0; i < pmsg4Rebill.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg cpoConfig = pmsg4Rebill.cpoConfig.no(i);
            if (CONFIG_CATG.INBOUND.equals(cpoConfig.configCatgCd.getValue())) {
                // Inbound Skip
                continue;
            }
            if (isAllOpenLineInConfig4Shell(bizMsg, cpoConfig.dsOrdPosnNum.getValue())) {
                // All open line in the Config.
                for (int j = 0; j < pmsg4Rebill.A.getValidCount(); j++) {
                    NWZC150001_APMsg dtlPMsg = pmsg4Rebill.A.no(j);
                    if (dtlPMsg.dsOrdPosnNum_A1.getValue().equals(cpoConfig.dsOrdPosnNum.getValue())) {

                        S21SsmEZDResult svcConfigRefList = NWAL2300Query.getInstance().getSvcConfigRefList(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum_H1.getValue()
                                , dtlPMsg.cpoDtlLineNum_A1.getValue(), dtlPMsg.cpoDtlLineSubNum_A1.getValue());
                        if (svcConfigRefList.isCodeNormal()) {
                            List<Map<String, Object>> rstMapSvcConfigRefList = (List<Map<String, Object>>) svcConfigRefList.getResultObject();
                            for (Map<String, Object> rstMapSvcConfigRef : rstMapSvcConfigRefList) {
                                NSZC115001_svcConfigRefListPMsg pMsgSvcConfigRef = shellPmsg.svcConfigRefList.no(shellPmsg.svcConfigRefList.getValidCount());

                                if (DS_CONTR_DTL_TP.BASE_ONLY.equals(rstMapSvcConfigRef.get("DS_CONTR_DTL_TP_CD"))) {
                                    shellPmsg.svcConfigRefList.setValidCount(shellPmsg.svcConfigRefList.getValidCount() + 1);

                                    ZYPEZDItemValueSetter.setValue(pMsgSvcConfigRef.xxRqstTpCd, NSZC115001Constant.PROC_MODE_NEW);
                                    ZYPEZDItemValueSetter.setValue(pMsgSvcConfigRef.shellLineNum, (BigDecimal) rstMapSvcConfigRef.get("SHELL_LINE_NUM"));
                                    ZYPEZDItemValueSetter.setValue(pMsgSvcConfigRef.dsOrdPosnNum, cpoConfig.dsOrdPosnNum);
                                    ZYPEZDItemValueSetter.setValue(pMsgSvcConfigRef.cpoOrdNum, (String) rstMapSvcConfigRef.get("CPO_ORD_NUM"));
                                    ZYPEZDItemValueSetter.setValue(pMsgSvcConfigRef.cpoDtlLineNum, (String) rstMapSvcConfigRef.get("CPO_DTL_LINE_NUM"));
                                    ZYPEZDItemValueSetter.setValue(pMsgSvcConfigRef.cpoDtlLineSubNum, (String) rstMapSvcConfigRef.get("CPO_DTL_LINE_SUB_NUM"));
                                    ZYPEZDItemValueSetter.setValue(pMsgSvcConfigRef.svcConfigMstrPk, (BigDecimal) rstMapSvcConfigRef.get("SVC_CONFIG_MSTR_PK"));
                                    ZYPEZDItemValueSetter.setValue(pMsgSvcConfigRef.custIssPoNum, (String) rstMapSvcConfigRef.get("CUST_PO_NUM"));
                                    ZYPEZDItemValueSetter.setValue(pMsgSvcConfigRef.custIssPoDt, (String) rstMapSvcConfigRef.get("PO_DT"));
                                    ZYPEZDItemValueSetter.setValue(pMsgSvcConfigRef.mtrReadMethCd, (String) rstMapSvcConfigRef.get("MTR_READ_METH_CD"));
                                    ZYPEZDItemValueSetter.setValue(pMsgSvcConfigRef.crRebilCd, CR_REBIL.REBILL);
                                    ZYPEZDItemValueSetter.setValue(pMsgSvcConfigRef.dsContrPk, (BigDecimal) rstMapSvcConfigRef.get("DS_CONTR_PK"));
                                    ZYPEZDItemValueSetter.setValue(pMsgSvcConfigRef.dsContrDtlPk, (BigDecimal) rstMapSvcConfigRef.get("DS_CONTR_DTL_PK"));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void setShellSvcDtlRebill(NWAL2300CMsg bizMsg, NSZC115001PMsg shellPmsg) {

        Map<BigDecimal, BigDecimal> procDsContrPkSet = new LinkedHashMap<BigDecimal, BigDecimal>();
        for (int i = 0; i < shellPmsg.svcConfigRefList.getValidCount(); i++) {
            NSZC115001_svcConfigRefListPMsg pMsgSvcConfigRef = shellPmsg.svcConfigRefList.no(i);

            if (procDsContrPkSet.containsKey(pMsgSvcConfigRef.dsContrPk.getValue())) {
                continue;
            }

            S21SsmEZDResult svcDtl = NWAL2300Query.getInstance().getSvcDtl(bizMsg.glblCmpyCd.getValue(), pMsgSvcConfigRef.dsContrPk.getValue());
            if (svcDtl.isCodeNormal()) {
                Map<String, Object> rstMapSvcDtl = (Map<String, Object>) svcDtl.getResultObject();
                NSZC115001_svcDtlListPMsg pMsgSvcDtl = shellPmsg.svcDtlList.no(shellPmsg.svcDtlList.getValidCount());
                shellPmsg.svcDtlList.setValidCount(shellPmsg.svcDtlList.getValidCount() + 1);

                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.xxRqstTpCd, NSZC115001Constant.PROC_MODE_NEW);
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.shellLineNum, (BigDecimal) rstMapSvcDtl.get("SHELL_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.svcPgmMdseCd, (String) rstMapSvcDtl.get("SVC_PGM_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.prcSvcContrTpCd, (String) rstMapSvcDtl.get("PRC_SVC_CONTR_TP_CD"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.prcSvcPlnTpCd, (String) rstMapSvcDtl.get("PRC_SVC_PLN_TP_CD"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.dsContrCatgCd, (String) rstMapSvcDtl.get("DS_CONTR_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.baseBllgCycleCd, (String) rstMapSvcDtl.get("BASE_BLLG_CYCLE_CD"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.usgBllgCycleCd, (String) rstMapSvcDtl.get("MTR_BLLG_CYCLE_CD"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.fromPerMthNum, (BigDecimal) rstMapSvcDtl.get("FROM_PER_MTH_NUM"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.toPerMthNum, (BigDecimal) rstMapSvcDtl.get("TO_PER_MTH_NUM"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.billWithEquipFlg, (String) rstMapSvcDtl.get("BILL_WITH_EQUIP_FLG"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.billByTpCd, (String) rstMapSvcDtl.get("BILL_BY_TP_CD"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.soldToCustLocCd, (String) rstMapSvcDtl.get("ALT_PAYER_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.sellToCustCd, (String) rstMapSvcDtl.get("DS_ACCT_NUM"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.cpoSvcAgmtVerNum, (String) rstMapSvcDtl.get("CPO_SVC_AGMT_VER_NUM"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.manContrOvrdFlg, (String) rstMapSvcDtl.get("MAN_CONTR_OVRD_FLG"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.manContrOvrdRsnCd, (String) rstMapSvcDtl.get("SVC_MEMO_RSN_CD"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.manContrOvrdCmntTxt, (String) rstMapSvcDtl.get("SVC_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.crRebilCd, CR_REBIL.REBILL);
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.applyEquipBillToFlg, (String) rstMapSvcDtl.get("APPLY_EQUIP_BILL_TO_FLG"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.fixTermInMthAot, (BigDecimal) rstMapSvcDtl.get("FIX_TERM_IN_MTH_AOT"));
                // 2018/12/19 QC#29636 Del Start
//              ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.maxUplftPct, (BigDecimal) rstMapSvcDtl.get("MAX_PRC_UP_RATIO"));
                // 2018/12/19 QC#29636 Del End
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.dsContrPk, (BigDecimal) rstMapSvcDtl.get("DS_CONTR_PK"));
                // 2018/08/27 S21_NA#25105 Add Start
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.addlBasePrcCatgCd, (String) rstMapSvcDtl.get("ADDL_BASE_PRC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.rntlPrcCatgCd, (String) rstMapSvcDtl.get("RNTL_PRC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(pMsgSvcDtl.addlChrgPrcCatgCd, (String) rstMapSvcDtl.get("ADDL_CHRG_PRC_CATG_CD"));
                // 2018/08/27 S21_NA#25105 Add End

                procDsContrPkSet.put(pMsgSvcConfigRef.dsContrPk.getValue(), pMsgSvcDtl.shellLineNum.getValue());
            }
        }
    }

    private void setShellSvcPrcRebill(NWAL2300CMsg bizMsg, NSZC115001PMsg shellPmsg) {

        Set<String> procPkSet = new HashSet<String>();
        for (int i = 0; i < shellPmsg.svcConfigRefList.getValidCount(); i++) {
            NSZC115001_svcConfigRefListPMsg svcConfigRef = shellPmsg.svcConfigRefList.no(i);

            String procPk = svcConfigRef.dsContrPk.getValue().toString() + svcConfigRef.dsContrDtlPk.getValue().toString();
            if (procPkSet.contains(procPk)) {
                continue;
            }

            S21SsmEZDResult svcPrc = NWAL2300Query.getInstance().getSvcPrc(bizMsg.glblCmpyCd.getValue(), svcConfigRef.dsContrDtlPk.getValue(), svcConfigRef.dsContrPk.getValue());
            if (svcPrc.isCodeNormal()) {
                Map<String, Object> rstMapSvcPrc = (Map<String, Object>) svcPrc.getResultObject();
                if (!DS_CONTR_CATG.FLEET.equals(rstMapSvcPrc.get("DS_CONTR_CATG_CD"))) {
                    NSZC115001_svcPrcListPMsg pMsgSvcPrc = shellPmsg.svcPrcList.no(shellPmsg.svcPrcList.getValidCount());
                    shellPmsg.svcPrcList.setValidCount(shellPmsg.svcPrcList.getValidCount() + 1);

                    ZYPEZDItemValueSetter.setValue(pMsgSvcPrc.xxRqstTpCd, NSZC115001Constant.PROC_MODE_NEW);
                    ZYPEZDItemValueSetter.setValue(pMsgSvcPrc.shellLineNum, (BigDecimal) rstMapSvcPrc.get("SHELL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcPrc.dsOrdPosnNum, svcConfigRef.dsOrdPosnNum);
                    ZYPEZDItemValueSetter.setValue(pMsgSvcPrc.mdlId, (BigDecimal) rstMapSvcPrc.get("MDL_ID"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcPrc.maintPrcCatgCd, (String) rstMapSvcPrc.get("PRC_CATG_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcPrc.prcMtrPkgPk, (BigDecimal) rstMapSvcPrc.get("PRC_MTR_PKG_PK"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcPrc.basePrcDealAmt, (BigDecimal) rstMapSvcPrc.get("BASE_PRC_DEAL_AMT"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcPrc.dealPrcListPrcAmt, (BigDecimal) rstMapSvcPrc.get("DEAL_PRC_LIST_PRC_AMT"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcPrc.prcTierSvcOfferCd, (String) rstMapSvcPrc.get("PRC_TIER_SVC_OFFER_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcPrc.billToCustCd, (String) rstMapSvcPrc.get("BASE_BILL_TO_CUST_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcPrc.sellToCustCd, (String) rstMapSvcPrc.get("SELL_TO_CUST_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcPrc.dsContrPk, (BigDecimal) rstMapSvcPrc.get("DS_CONTR_PK"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcPrc.dsContrDtlPk, (BigDecimal) rstMapSvcPrc.get("DS_CONTR_DTL_PK"));
                }
            }
            procPkSet.add(procPk);
        }
    }

    private void setShellSvcAddlChrgPrcRebill(NWAL2300CMsg bizMsg, NSZC115001PMsg shellPmsg) {

        Set<String> procPkSet = new HashSet<String>();
        for (int i = 0; i < shellPmsg.svcConfigRefList.getValidCount(); i++) {
            NSZC115001_svcConfigRefListPMsg svcConfigRef = shellPmsg.svcConfigRefList.no(i);

            String procPk = svcConfigRef.dsContrPk.getValue().toString() + svcConfigRef.cpoDtlLineNum.getValue() + svcConfigRef.cpoDtlLineSubNum.getValue();
            if (procPkSet.contains(procPk)) {
                continue;
            }

            S21SsmEZDResult svcAddlChrgPrcList = NWAL2300Query.getInstance().getSvcAddlChrgPrcList(bizMsg.glblCmpyCd.getValue(), svcConfigRef.dsContrPk.getValue()
                    , bizMsg.cpoOrdNum_H1.getValue(), svcConfigRef.cpoDtlLineNum.getValue(), svcConfigRef.cpoDtlLineSubNum.getValue());
            if (svcAddlChrgPrcList.isCodeNormal()) {
                List<Map<String, Object>> rstMapSvcAddlChrgPrcList = (List<Map<String, Object>>) svcAddlChrgPrcList.getResultObject();
                for (Map<String, Object> rstMapSvcAddlChrgPrc : rstMapSvcAddlChrgPrcList) {
                    NSZC115001_svcAddlChrgPrcListPMsg pMsgSvcAddlChrgPrc = shellPmsg.svcAddlChrgPrcList.no(shellPmsg.svcAddlChrgPrcList.getValidCount());
                    shellPmsg.svcAddlChrgPrcList.setValidCount(shellPmsg.svcAddlChrgPrcList.getValidCount() + 1);

                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlChrgPrc.xxRqstTpCd, NSZC115001Constant.PROC_MODE_NEW);
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlChrgPrc.shellLineNum, (BigDecimal) rstMapSvcAddlChrgPrc.get("SHELL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlChrgPrc.cpoOrdNum, (String) rstMapSvcAddlChrgPrc.get("CPO_ORD_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlChrgPrc.cpoDtlLineNum, (String) rstMapSvcAddlChrgPrc.get("CPO_DTL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlChrgPrc.cpoDtlLineSubNum, (String) rstMapSvcAddlChrgPrc.get("CPO_DTL_LINE_SUB_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlChrgPrc.addlChrgPrcCatgCd, (String) rstMapSvcAddlChrgPrc.get("PRC_CATG_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlChrgPrc.addlChrgMdseCd, (String) rstMapSvcAddlChrgPrc.get("ADDL_CHRG_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlChrgPrc.addlChrgPrcDealAmt, (BigDecimal) rstMapSvcAddlChrgPrc.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlChrgPrc.dealPrcListPrcAmt, (BigDecimal) rstMapSvcAddlChrgPrc.get("DEAL_PRC_LIST_PRC_AMT"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlChrgPrc.svcPrcCatgCd, (String) rstMapSvcAddlChrgPrc.get("SVC_PRC_CATG_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlChrgPrc.crRebilCd, CR_REBIL.REBILL);
                    // 2018/05/09 QC#25030 Mod Start
//                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlChrgPrc.printDtlFlg, ZYPConstant.FLG_OFF_N);
                    String printDtlFlg = (String) rstMapSvcAddlChrgPrc.get("PRINT_DTL_FLG");
                    if (!ZYPCommonFunc.hasValue(printDtlFlg)) {
                        printDtlFlg = ZYPConstant.FLG_OFF_N;
                    }
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlChrgPrc.printDtlFlg, printDtlFlg);
                    // 2018/05/09 QC#25030 Mod End
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlChrgPrc.dsContrPk, (BigDecimal) rstMapSvcAddlChrgPrc.get("DS_CONTR_PK"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlChrgPrc.dsContrDtlPk, (BigDecimal) rstMapSvcAddlChrgPrc.get("DS_CONTR_DTL_PK"));

                }
            }
            procPkSet.add(procPk);
        }
    }

    private void setShellSvcAddlBasePrcRebill(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Rebill, NSZC115001PMsg shellPmsg) {

        Set<String> procPkSet = new HashSet<String>();
        for (int i = 0; i < shellPmsg.svcConfigRefList.getValidCount(); i++) {
            NSZC115001_svcConfigRefListPMsg svcConfigRef = shellPmsg.svcConfigRefList.no(i);
            String procPk = svcConfigRef.dsContrPk.getValue().toString() + svcConfigRef.cpoDtlLineNum.getValue() + svcConfigRef.cpoDtlLineSubNum.getValue();
            if (procPkSet.contains(procPk)) {
                continue;
            }

            // For Rental
            S21SsmEZDResult svcAddlBasePrcRentalList = NWAL2300Query.getInstance().getSvcAddlBasePrcRentalList(bizMsg.glblCmpyCd.getValue(), svcConfigRef.dsContrPk.getValue()
                    , bizMsg.cpoOrdNum_H1.getValue(), svcConfigRef.cpoDtlLineNum.getValue(), svcConfigRef.cpoDtlLineSubNum.getValue());
            if (svcAddlBasePrcRentalList.isCodeNormal()) {
                List<Map<String, Object>> rstMapSvcAddlBasePrcRentalList = (List<Map<String, Object>>) svcAddlBasePrcRentalList.getResultObject();
                for (Map<String, Object> rstMapSvcAddlBasePrcRental : rstMapSvcAddlBasePrcRentalList) {
                    NSZC115001_svcAddlBasePrcListPMsg pMsgSvcAddlBasePrcRental = shellPmsg.svcAddlBasePrcList.no(shellPmsg.svcAddlBasePrcList.getValidCount());
                    shellPmsg.svcAddlBasePrcList.setValidCount(shellPmsg.svcAddlBasePrcList.getValidCount() + 1);

                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcRental.xxRqstTpCd, NSZC115001Constant.PROC_MODE_NEW);
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcRental.shellLineNum, (BigDecimal) rstMapSvcAddlBasePrcRental.get("SHELL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcRental.addlChrgCatgCd, ADDL_CHRG_CATG.RENTAL);
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcRental.cpoOrdNum, (String) rstMapSvcAddlBasePrcRental.get("CPO_ORD_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcRental.cpoDtlLineNum, (String) rstMapSvcAddlBasePrcRental.get("CPO_DTL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcRental.cpoDtlLineSubNum, (String) rstMapSvcAddlBasePrcRental.get("CPO_DTL_LINE_SUB_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcRental.addlBasePrcCatgCd, (String) rstMapSvcAddlBasePrcRental.get("PRC_CATG_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcRental.addlBasePrcDealAmt, (BigDecimal) rstMapSvcAddlBasePrcRental.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcRental.dealPrcListPrcAmt, (BigDecimal) rstMapSvcAddlBasePrcRental.get("DEAL_PRC_LIST_PRC_AMT"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcRental.prcListEquipConfigNum, (BigDecimal) rstMapSvcAddlBasePrcRental.get("PRC_LIST_EQUIP_CONFIG_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcRental.svcPrcCatgCd, (String) rstMapSvcAddlBasePrcRental.get("SVC_PRC_CATG_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcRental.crRebilCd, CR_REBIL.REBILL);
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcRental.dsContrPk, (BigDecimal) rstMapSvcAddlBasePrcRental.get("DS_CONTR_PK"));
                    ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcRental.dsContrDtlPk, (BigDecimal) rstMapSvcAddlBasePrcRental.get("DS_CONTR_DTL_PK"));

                }
            }
            procPkSet.add(procPk);

            // For Accesary
            List<String[]> accLineNumList = getAccLineNum(pmsg4Rebill, svcConfigRef.cpoDtlLineNum.getValue(), svcConfigRef.cpoDtlLineSubNum.getValue(), svcConfigRef.dsOrdPosnNum.getValue());
            for (String[] lineNum : accLineNumList) {
                if (lineNum != null) {

                    String procAccPk = svcConfigRef.dsContrPk.getValue().toString() + lineNum[0] + lineNum[1];

                    S21SsmEZDResult svcAddlBasePrcAccList = NWAL2300Query.getInstance().getSvcAddlBasePrcAccList(bizMsg.glblCmpyCd.getValue(), svcConfigRef.dsContrPk.getValue()
                            , bizMsg.cpoOrdNum_H1.getValue(), svcConfigRef.cpoDtlLineNum.getValue(), svcConfigRef.cpoDtlLineSubNum.getValue());
                    if (svcAddlBasePrcAccList.isCodeNormal()) {
                        List<Map<String, Object>> rstMapSvcAddlBasePrcAccList = (List<Map<String, Object>>) svcAddlBasePrcAccList.getResultObject();
                        for (Map<String, Object> rstMapSvcAddlBasePrcAcc : rstMapSvcAddlBasePrcAccList) {
                            NSZC115001_svcAddlBasePrcListPMsg pMsgSvcAddlBasePrcAcc = shellPmsg.svcAddlBasePrcList.no(shellPmsg.svcAddlBasePrcList.getValidCount());
                            shellPmsg.svcAddlBasePrcList.setValidCount(shellPmsg.svcAddlBasePrcList.getValidCount() + 1);

                            ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcAcc.xxRqstTpCd, NSZC115001Constant.PROC_MODE_NEW);
                            ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcAcc.shellLineNum, (BigDecimal) rstMapSvcAddlBasePrcAcc.get("SHELL_LINE_NUM"));
                            ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcAcc.addlChrgCatgCd, ADDL_CHRG_CATG.ACCESSORY);
                            ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcAcc.cpoOrdNum, (String) rstMapSvcAddlBasePrcAcc.get("CPO_ORD_NUM"));
                            ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcAcc.cpoDtlLineNum, (String) rstMapSvcAddlBasePrcAcc.get("CPO_DTL_LINE_NUM"));
                            ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcAcc.cpoDtlLineSubNum, (String) rstMapSvcAddlBasePrcAcc.get("CPO_DTL_LINE_SUB_NUM"));
                            ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcAcc.addlBasePrcCatgCd, (String) rstMapSvcAddlBasePrcAcc.get("PRC_CATG_CD"));
                            ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcAcc.addlBasePrcDealAmt, (BigDecimal) rstMapSvcAddlBasePrcAcc.get("BASE_PRC_DEAL_AMT"));
                            ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcAcc.dealPrcListPrcAmt, (BigDecimal) rstMapSvcAddlBasePrcAcc.get("DEAL_PRC_LIST_PRC_AMT"));
                            ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcAcc.prcListEquipConfigNum, (BigDecimal) rstMapSvcAddlBasePrcAcc.get("PRC_LIST_EQUIP_CONFIG_NUM"));
                            ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcAcc.crRebilCd, CR_REBIL.REBILL);
                            ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcAcc.dsContrPk, (BigDecimal) rstMapSvcAddlBasePrcAcc.get("DS_CONTR_PK"));
                            ZYPEZDItemValueSetter.setValue(pMsgSvcAddlBasePrcAcc.dsContrDtlPk, (BigDecimal) rstMapSvcAddlBasePrcAcc.get("DS_CONTR_DTL_PK"));

                        }
                    }
                    procPkSet.add(procAccPk);
                }
            }
        }
    }

    private void setShellSvcUsgPrcRebill(NWAL2300CMsg bizMsg, NSZC115001PMsg shellPmsg) {

        Set<String> procPkSet = new HashSet<String>();
        for (int i = 0; i < shellPmsg.svcPrcList.getValidCount(); i++) {
            NSZC115001_svcPrcListPMsg svcPrc = shellPmsg.svcPrcList.no(i);

            String procPk = svcPrc.dsContrPk.getValue().toString() + svcPrc.dsContrDtlPk.getValue().toString();
            if (procPkSet.contains(procPk)) {
                continue;
            }
            DS_CONTRTMsg dsContr = NWAL2300CommonLogic.getDsContr(bizMsg.glblCmpyCd.getValue(), svcPrc.dsContrPk.getValue());

            S21SsmEZDResult svcUsgPrcList = NWAL2300Query.getInstance().getsvcUsgPrcList(bizMsg.glblCmpyCd.getValue()
                    , svcPrc.dsContrPk.getValue(), svcPrc.dsContrDtlPk.getValue(), dsContr.dsContrCatgCd.getValue());
            if (svcUsgPrcList.isCodeNormal()) {
                List<Map<String, Object>> rstMapSvcUsgPrcList = (List<Map<String, Object>>) svcUsgPrcList.getResultObject();
                for (Map<String, Object> rstMapSvcUsgPrc : rstMapSvcUsgPrcList) {
                    if (!DS_CONTR_CATG.FLEET.equals(rstMapSvcUsgPrc.get("DS_CONTR_CATG_CD"))) {
                        NSZC115001_svcUsgPrcListPMsg pMsgSvcUsgPrc = shellPmsg.svcUsgPrcList.no(shellPmsg.svcUsgPrcList.getValidCount());
                        shellPmsg.svcUsgPrcList.setValidCount(shellPmsg.svcUsgPrcList.getValidCount() + 1);

                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.xxRqstTpCd, NSZC115001Constant.PROC_MODE_NEW);
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.shellLineNum, (BigDecimal) rstMapSvcUsgPrc.get("SHELL_LINE_NUM"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.mdlId, (BigDecimal) rstMapSvcUsgPrc.get("MDL_ID"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.contrPhysBllgMtrRelnPk, (BigDecimal) rstMapSvcUsgPrc.get("CONTR_PHYS_BLLG_MTR_RELN_PK"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.prcListBandCd, (String) rstMapSvcUsgPrc.get("PRC_LIST_BAND_CD"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.prcBookMdseCd, (String) rstMapSvcUsgPrc.get("PRC_BOOK_MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.bllgMtrLbCd, (String) rstMapSvcUsgPrc.get("BLLG_MTR_LB_CD"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.usgMdseCd, (String) rstMapSvcUsgPrc.get("INTG_MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.copyInclPrcQty, (BigDecimal) rstMapSvcUsgPrc.get("COPY_INCL_PRC_QTY"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.regMtrLbCd, (String) rstMapSvcUsgPrc.get("REG_MTR_LB_CD"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.xsMtrAmtRate, (BigDecimal) rstMapSvcUsgPrc.get("XS_MTR_AMT_RATE"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.contrMtrMultRate, (BigDecimal) rstMapSvcUsgPrc.get("CONTR_MTR_MULT_RATE"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.prcSvcTierTpCd, (String) rstMapSvcUsgPrc.get("PRC_SVC_TIER_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.minCopyVolCnt, (BigDecimal) rstMapSvcUsgPrc.get("MIN_COPY_VOL_CNT"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.maxCopyVolCnt, (BigDecimal) rstMapSvcUsgPrc.get("MAX_COPY_VOL_CNT"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.billToCustCd, (String) rstMapSvcUsgPrc.get("BILL_TO_CUST_CD"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.sellToCustCd, (String) rstMapSvcUsgPrc.get("SELL_TO_CUST_CD"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.actvFlg, (String) rstMapSvcUsgPrc.get("ACTV_FLG"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.dsOrdPosnNum, svcPrc.dsOrdPosnNum);
                        // 2018/05/09 QC#25030 Add Start
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.bllgFreeCopyCnt, (BigDecimal) rstMapSvcUsgPrc.get("BLLG_FREE_COPY_CNT"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.bllgMinCnt, (BigDecimal) rstMapSvcUsgPrc.get("BLLG_MIN_CNT"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.bllgMinAmtRate, (BigDecimal) rstMapSvcUsgPrc.get("BLLG_MIN_AMT_RATE"));
                        ZYPEZDItemValueSetter.setValue(pMsgSvcUsgPrc.bllgRollOverRatio, (BigDecimal) rstMapSvcUsgPrc.get("BLLG_ROLL_OVER_RATIO"));
                        // 2018/05/09 QC#25030 Add End
                    }
                }
            }
            procPkSet.add(procPk);
        }
    }

    private static boolean isAllOpenLineInConfig4Shell(NWAL2300CMsg bizMsg, String dsOrdPosnNum) {

        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getClosedLineInConfig4Shell(bizMsg, dsOrdPosnNum);
        if (ssmResult.isCodeNormal()) {
            BigDecimal closedLineCnt = (BigDecimal) ssmResult.getResultObject();
            if (closedLineCnt != null && BigDecimal.ZERO.compareTo(closedLineCnt) < 0) {
                return false;
            }
        }
        return true;
    }

    private void numberingLineNumShell(NWZC150001PMsg pMsg, NSZC115001PMsg shellPmsg, String rblCpoNum) {

        /***************************************************************************
         * CPO_ORD_NUM
         ***************************************************************************/
        ZYPEZDItemValueSetter.setValue(shellPmsg.refCpoOrdNum, rblCpoNum);

        // SVC_CONFIG_REF
        for (int i = 0; i < shellPmsg.svcConfigRefList.getValidCount(); i++) {
            NSZC115001_svcConfigRefListPMsg svcConfigRef = shellPmsg.svcConfigRefList.no(i);
            ZYPEZDItemValueSetter.setValue(svcConfigRef.cpoOrdNum, rblCpoNum);
        }

        // SVC_ADDL_CHRG_PRC
        for (int i = 0; i < shellPmsg.svcAddlChrgPrcList.getValidCount(); i++) {
            NSZC115001_svcAddlChrgPrcListPMsg svcAddlChrgPrc = shellPmsg.svcAddlChrgPrcList.no(i);
            ZYPEZDItemValueSetter.setValue(svcAddlChrgPrc.cpoOrdNum, rblCpoNum);
        }

        // SVC_ADDL_BASE_PRC
        for (int i = 0; i < shellPmsg.svcAddlBasePrcList.getValidCount(); i++) {
            NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrc = shellPmsg.svcAddlBasePrcList.no(i);
            ZYPEZDItemValueSetter.setValue(svcAddlBasePrc.cpoOrdNum, rblCpoNum);

        }
        /***************************************************************************
         * CPO_DTL_LINE_NUM, CPO_DTL_LINE_SUB_NUM, DS_ORD_POSN_NUM
         ***************************************************************************/

        // SVC_CONFIG_REF
        Map<String, String> dsOrdPosnNumMap = new LinkedHashMap<String, String>();
        for (int i = 0; i < shellPmsg.svcConfigRefList.getValidCount(); i++) {
            NSZC115001_svcConfigRefListPMsg svcConfigRef = shellPmsg.svcConfigRefList.no(i);
            String[] lineNum = getConvLineNumFromNWZC150001PMsg(pMsg, svcConfigRef.cpoDtlLineNum.getValue(), svcConfigRef.cpoDtlLineSubNum.getValue());
            if (lineNum != null) {
                ZYPEZDItemValueSetter.setValue(svcConfigRef.cpoDtlLineNum, lineNum[0]);
                ZYPEZDItemValueSetter.setValue(svcConfigRef.cpoDtlLineSubNum, lineNum[1]);
            }

            String posnNum = getConvPosnNumFromNWZC150001PMsg(pMsg, svcConfigRef.cpoDtlLineNum.getValue(), svcConfigRef.cpoDtlLineSubNum.getValue());
            ZYPEZDItemValueSetter.setValue(svcConfigRef.dsOrdPosnNum, posnNum);
            String key = svcConfigRef.dsContrDtlPk.getValue().toString();
            dsOrdPosnNumMap.put(key, posnNum);
        }

        // SVC_PRC
        for (int i = 0; i < shellPmsg.svcPrcList.getValidCount(); i++) {
            NSZC115001_svcPrcListPMsg svcPrc = shellPmsg.svcPrcList.no(i);
            ZYPEZDItemValueSetter.setValue(svcPrc.dsOrdPosnNum, dsOrdPosnNumMap.get(svcPrc.dsContrDtlPk.getValue().toString()));
        }

        // SVC_ADDL_CHRG_PRC
        for (int i = 0; i < shellPmsg.svcAddlChrgPrcList.getValidCount(); i++) {
            NSZC115001_svcAddlChrgPrcListPMsg svcAddlChrgPrc = shellPmsg.svcAddlChrgPrcList.no(i);
            String[] lineNum = getConvLineNumFromNWZC150001PMsg(pMsg, svcAddlChrgPrc.cpoDtlLineNum.getValue(), svcAddlChrgPrc.cpoDtlLineSubNum.getValue());
            if (lineNum != null) {
                ZYPEZDItemValueSetter.setValue(svcAddlChrgPrc.cpoDtlLineNum, lineNum[0]);
                ZYPEZDItemValueSetter.setValue(svcAddlChrgPrc.cpoDtlLineSubNum, lineNum[1]);
            }
        }

        // SVC_ADDL_BASE_PRC
        for (int i = 0; i < shellPmsg.svcAddlBasePrcList.getValidCount(); i++) {
            NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrc = shellPmsg.svcAddlBasePrcList.no(i);
            String[] lineNum = getConvLineNumFromNWZC150001PMsg(pMsg, svcAddlBasePrc.cpoDtlLineNum.getValue(), svcAddlBasePrc.cpoDtlLineSubNum.getValue());
            if (lineNum != null) {
                ZYPEZDItemValueSetter.setValue(svcAddlBasePrc.cpoDtlLineNum, lineNum[0]);
                ZYPEZDItemValueSetter.setValue(svcAddlBasePrc.cpoDtlLineSubNum, lineNum[1]);
            }
        }

        /***************************************************************************
         * SHELL_LINE_NUM and Sort
         ***************************************************************************/
        List<BigDecimal> sortOrigShellLineNum = new ArrayList<BigDecimal>();
        for (int i = 0; i < shellPmsg.svcDtlList.getValidCount(); i++) {
            NSZC115001_svcDtlListPMsg svcDtl = shellPmsg.svcDtlList.no(i);
            sortOrigShellLineNum.add(svcDtl.shellLineNum.getValue());
        }
        Collections.sort(sortOrigShellLineNum);

        BigDecimal shellLineNum = BigDecimal.ZERO;
        Map<BigDecimal, BigDecimal> shellLineNumMapOut = new LinkedHashMap<BigDecimal, BigDecimal>();
        for (BigDecimal origShellLineNum : sortOrigShellLineNum) {
            shellLineNum = shellLineNum.add(BigDecimal.ONE);
            shellLineNumMapOut.put(origShellLineNum, shellLineNum);
        }

        // SVC_DTL
        for (int i = 0; i < shellPmsg.svcDtlList.getValidCount(); i++) {
            NSZC115001_svcDtlListPMsg svcDtl = shellPmsg.svcDtlList.no(i);
            ZYPEZDItemValueSetter.setValue(svcDtl.shellLineNum, shellLineNumMapOut.get(svcDtl.shellLineNum.getValue()));
        }

        // SVC_CONFIG_REF
        for (int i = 0; i < shellPmsg.svcConfigRefList.getValidCount(); i++) {
            NSZC115001_svcConfigRefListPMsg svcConfigRef = shellPmsg.svcConfigRefList.no(i);
            ZYPEZDItemValueSetter.setValue(svcConfigRef.shellLineNum, shellLineNumMapOut.get(svcConfigRef.shellLineNum.getValue()));
        }

        // SVC_PRC
        for (int i = 0; i < shellPmsg.svcPrcList.getValidCount(); i++) {
            NSZC115001_svcPrcListPMsg svcPrc = shellPmsg.svcPrcList.no(i);
            ZYPEZDItemValueSetter.setValue(svcPrc.shellLineNum, shellLineNumMapOut.get(svcPrc.shellLineNum.getValue()));
        }

        // SVC_ADDL_CHRG_PRC
        for (int i = 0; i < shellPmsg.svcAddlChrgPrcList.getValidCount(); i++) {
            NSZC115001_svcAddlChrgPrcListPMsg svcAddlChrgPrc = shellPmsg.svcAddlChrgPrcList.no(i);
            ZYPEZDItemValueSetter.setValue(svcAddlChrgPrc.shellLineNum, shellLineNumMapOut.get(svcAddlChrgPrc.shellLineNum.getValue()));
        }

        // SVC_ADDL_BASE_PRC
        for (int i = 0; i < shellPmsg.svcAddlBasePrcList.getValidCount(); i++) {
            NSZC115001_svcAddlBasePrcListPMsg svcAddlBasePrc = shellPmsg.svcAddlBasePrcList.no(i);
            ZYPEZDItemValueSetter.setValue(svcAddlBasePrc.shellLineNum, shellLineNumMapOut.get(svcAddlBasePrc.shellLineNum.getValue()));
        }

        // SVC_USG_PRC
        for (int i = 0; i < shellPmsg.svcUsgPrcList.getValidCount(); i++) {
            NSZC115001_svcUsgPrcListPMsg svcUsgPrc = shellPmsg.svcUsgPrcList.no(i);
            ZYPEZDItemValueSetter.setValue(svcUsgPrc.shellLineNum, shellLineNumMapOut.get(svcUsgPrc.shellLineNum.getValue()));
        }
    }

    private String[] getConvLineNumFromNWZC150001PMsg(NWZC150001PMsg pMsg, String oldLineNum, String oldLineSubNum) {

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg dtlPMsg = pMsg.A.no(i);
            if (dtlPMsg.ordSrcRefLineNum_A1.getValue().equals(oldLineNum) && dtlPMsg.ordSrcRefLineSubNum_A1.getValue().equals(oldLineSubNum)) {
                return new String[]{dtlPMsg.cpoDtlLineNum_A1.getValue(), dtlPMsg.cpoDtlLineSubNum_A1.getValue()};
            }
        }
        return null;
    }

    private String getConvPosnNumFromNWZC150001PMsg(NWZC150001PMsg pMsg, String lineNum, String lineSubNum) {

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg dtlPMsg = pMsg.A.no(i);
            if (dtlPMsg.cpoDtlLineNum_A1.getValue().equals(lineNum) && dtlPMsg.cpoDtlLineSubNum_A1.getValue().equals(lineSubNum)) {
                return dtlPMsg.dsOrdPosnNum_A1.getValue();
            }
        }
        return null;
    }

//    private boolean insertShell(NWAL2300CMsg bizMsg, NWZC150001PMsg pMsg, NSZC115001PMsg shellPmsg, String rblCpoNum) {
//
//        if (!ZYPCommonFunc.hasValue(rblCpoNum) ||  shellPmsg.svcDtlList.getValidCount() < 1) {
//            return true;
//        }
//        numberingLineNumShell(pMsg, shellPmsg, rblCpoNum);
//
//        // Call API
//        new NSZC115001().execute(shellPmsg, ONBATCH_TYPE.ONLINE);
//
//        if (S21ApiUtil.isXxMsgId(shellPmsg)) {
//            List<String> msgList = S21ApiUtil.getXxMsgIdList(shellPmsg);
//            bizMsg.setMessageInfo(msgList.get(0));
//            return false;
//        }
//        return true;
//    }

    private boolean existSvcConfigMstr(NWAL2300CMsg bizMsg, BigDecimal pk) {

        if (!ZYPCommonFunc.hasValue(pk)) {
            return false;
        }
        SVC_CONFIG_MSTRTMsg tMsg = new SVC_CONFIG_MSTRTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, pk);

        tMsg = (SVC_CONFIG_MSTRTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    private List<String []> getAccLineNum(NWZC150001PMsg pMsg, String lineNumMach, String lineSubNumMach, String posnNum) {

        List<String []> lineNumList = new ArrayList<String []>();
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg dtlPMsg = pMsg.A.no(i);
            if (posnNum.equals(dtlPMsg.dsOrdPosnNum_A1.getValue())) {
                if (!lineNumMach.equals(dtlPMsg.cpoDtlLineNum_A1.getValue()) || !lineSubNumMach.equals(dtlPMsg.cpoDtlLineSubNum_A1.getValue())) {
                    lineNumList.add(new String[]{dtlPMsg.cpoDtlLineNum_A1.getValue(), dtlPMsg.cpoDtlLineSubNum_A1.getValue()});
                }
            }
        }
        return lineNumList;
    }

    // 2017/10/18 QC#21723 ADD START
    private static boolean existManufactureItem(NWAL2300CMsg bizMsg, String origOrCustMdseCd) {

        if (!ZYPCommonFunc.hasValue(origOrCustMdseCd)) {
            return false;
        }

        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getCntManufactureItem(bizMsg, origOrCustMdseCd);
        if (ssmResult.isCodeNormal()) {
            BigDecimal closedLineCnt = (BigDecimal) ssmResult.getResultObject();
            if (closedLineCnt != null && BigDecimal.ZERO.compareTo(closedLineCnt) < 0) {
                // Exist
                return true;
            }
        }
        // Not Exist
        return false;
    }
    // 2017/10/18 QC#21723 ADD END

    //  QC#12875 ADD START
    private static boolean existCustMdseXref(NWAL2300CMsg bizMsg, String dsOrdPosnNum) {

        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getCntCustMdseXref(bizMsg, dsOrdPosnNum);
        if (ssmResult.isCodeNormal()) {
            BigDecimal closedLineCnt = (BigDecimal) ssmResult.getResultObject();
            if (closedLineCnt != null && BigDecimal.ZERO.compareTo(closedLineCnt) < 0) {
                // Exist
                return true;
            }
        }
        // Not Exist
        return false;
    }

    private static boolean existOrdTakeMdse(NWAL2300CMsg bizMsg, String ordTakeMdseCd, String mdseCd) {

        if (!ZYPCommonFunc.hasValue(ordTakeMdseCd) || !ZYPCommonFunc.hasValue(mdseCd) || 8 < ordTakeMdseCd.length()) {
            return false;
        }
        ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, ordTakeMdseCd);

        tMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(tMsg);
        // QC#14715 MOD
        if (tMsg != null) {
            return true;
        }
        return false;
    }
    //  QC#12875 ADD END
    // 2017/01/11 QC#16669 ADD START
    private void setOrderDetailPartial(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg, NWAL2300_CCMsg invLineMsg) {

        // 2018/06/04 QC#26479 Add Start
        int cpoDtlIdx = getRelatedDetailPMsg(pmsg, invLineMsg);
        // 2018/06/04 QC#26479 Add End
        // 2018/06/04 QC#26479 Del Start
//        final NWZC150001_APMsg dtlPMsg = pmsg.A.no(pmsg.A.getValidCount());
//        pmsg.A.setValidCount(pmsg.A.getValidCount() + 1);
        // 2018/06/04 QC#26479 De End
        // 2018/06/04 QC#26479 Add Start
        NWZC150001_APMsg dtlPMsg = null;
        if (cpoDtlIdx < 0) {
            dtlPMsg = pmsg.A.no(pmsg.A.getValidCount());
            pmsg.A.setValidCount(pmsg.A.getValidCount() + 1);
        } else {
            dtlPMsg = pmsg.A.no(cpoDtlIdx);
        }
        // 2018/06/04 QC#26479 Add End

        if (cpoDtlIdx < 0) { // 2018/06/04 QC#26479 Add Condition
            CPO_DTLTMsg cpoDtl = geCPO_DTLTMsg(bizMsg, invLineMsg);

            // Copy CPO_DTL
            if (cpoDtl != null) {
                EZDMsg.copy(cpoDtl, "", dtlPMsg, "A1");
            }

            dtlPMsg.crRebilCd_A1.clear();
            dtlPMsg.cpoOrdTpCd_A1.clear();
            dtlPMsg.rsdDt_A1.clear();
            dtlPMsg.uomCpltFlg_A1.clear();
            dtlPMsg.frtChrgToCd_A1.clear();
            dtlPMsg.frtChrgMethCd_A1.clear();
            dtlPMsg.shpgSvcLvlCd_A1.clear();
            dtlPMsg.slsRepOrSlsTeamTocCd_A1.clear();
            dtlPMsg.manPrcFlg_A1.clear();
            dtlPMsg.setItemShipCpltFlg_A1.clear();
            dtlPMsg.custIstlFlg_A1.clear();
            dtlPMsg.coaCmpyCd_A1.clear();
            dtlPMsg.coaAfflCd_A1.clear();
            dtlPMsg.coaBrCd_A1.clear();
            dtlPMsg.coaChCd_A1.clear();
            dtlPMsg.coaCcCd_A1.clear();
            dtlPMsg.coaAcctCd_A1.clear();
            dtlPMsg.coaProjCd_A1.clear();
            dtlPMsg.coaExtnCd_A1.clear();
            dtlPMsg.coaProdCd_A1.clear();
            dtlPMsg.refCpoDtlLineNum_A1.clear();
            dtlPMsg.refCpoDtlLineSubNum_A1.clear();

            // 2018/06/04 QC#26479 Add Start
            dtlPMsg.ordQty_A1.setValue(BigDecimal.ZERO);
            dtlPMsg.ordCustUomQty_A1.setValue(BigDecimal.ZERO);
            dtlPMsg.unitNetWt_A1.setValue(BigDecimal.ZERO);
            // 2018/06/04 QC#26479 Add End
        } // 2018/06/04 QC#26479 Add Condition

        // 2018/06/04 QC#26479 Mod Start
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_A1, invLineMsg.ordQty_C1);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordCustUomQty_A1, invLineMsg.ordQty_C1);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd_A1, NWZC150001Constant.RQST_TP_DTL_MODIFY);
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.unitNetWt_A1, createInPoundWt(bizMsg.glblCmpyCd.getValue(), dtlPMsg.mdseCd_A1.getValue(), dtlPMsg.ordQty_A1.getValue()));

        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_A1, addBigDecimal(dtlPMsg.ordQty_A1.getValue(), invLineMsg.ordQty_C1.getValue()));
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordCustUomQty_A1, addBigDecimal(dtlPMsg.ordCustUomQty_A1.getValue(), invLineMsg.ordQty_C1.getValue()));
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd_A1, NWZC150001Constant.RQST_TP_DTL_MODIFY);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.unitNetWt_A1, addBigDecimal(dtlPMsg.unitNetWt_A1.getValue(), createInPoundWt(bizMsg.glblCmpyCd.getValue(), dtlPMsg.mdseCd_A1.getValue(), dtlPMsg.ordQty_A1.getValue())));
        // 2018/06/04 QC#26479 Mod End

    }
    private void setOrderConfigPartial(NWAL2300CMsg bizMsg, NWAL2300_CCMsg invLineMsg, NWZC150001PMsg pmsg) {

        final NWZC150001_cpoConfigPMsg cpoConfigPMsg = pmsg.cpoConfig.no(pmsg.cpoConfig.getValidCount());
        pmsg.cpoConfig.setValidCount(pmsg.cpoConfig.getValidCount() + 1);

        // Copy DS_CPO_CONFIG
        DS_CPO_CONFIGTMsg config = getDS_CPO_CONFIGTMsg(bizMsg, invLineMsg);
        if (config != null) {
            EZDMsg.copy(config, null, cpoConfigPMsg, null);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustCd, config.billToCustLocCd);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustCd, config.shipToCustLocCd);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CONFIG_MODIFY);
        }
    }
    private void setOrderSalesCreditOrigPartial(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4OrigPartial) {

        int slsCrPMsgCount = 0;

        // Get Sales Credit Header
        DS_CPO_SLS_CRTMsgArray dsCpoSlsCrAry = getCposlsCrAryHdr(bizMsg.glblCmpyCd.getValue(), pmsg4OrigPartial.cpoOrdNum.getValue());
        if (dsCpoSlsCrAry != null) {
            for (int i = 0; i < dsCpoSlsCrAry.getValidCount(); i++) {
                NWZC150001_cpoSlsCrPMsg slsCrPMsg = pmsg4OrigPartial.cpoSlsCr.no(slsCrPMsgCount);

                // Copy
                EZDMsg.copy(dsCpoSlsCrAry.no(i), null, slsCrPMsg, null);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.xxRqstTpCd, RQST_TP_SLS_CR_MODIFY);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCd, dsCpoSlsCrAry.no(i).slsRepTocCd);
                slsCrPMsg.configCatgCd.clear();

                slsCrPMsgCount++;
            }
        }
        // Get Sales Credit Config
        S21SsmEZDResult result = NWAL2300Query.getInstance().getSalesCreditForConfigOut(bizMsg);

        if (!result.isCodeNotFound()) {
            List<DS_CPO_SLS_CRTMsg> dsCpoSlsCrList = (List) result.getResultObject();
            for (int i = 0; i < dsCpoSlsCrList.size(); i++) {
                NWZC150001_cpoSlsCrPMsg slsCrPMsg = pmsg4OrigPartial.cpoSlsCr.no(slsCrPMsgCount);
                // Copy
                EZDMsg.copy(dsCpoSlsCrList.get(i), null, slsCrPMsg, null);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.xxRqstTpCd, RQST_TP_SLS_CR_MODIFY);
                // 2018/04/06 QC#22122 Mod Start
                //ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCd, dsCpoSlsCrAry.no(i).slsRepTocCd);
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.slsRepCd, dsCpoSlsCrList.get(i).slsRepTocCd);
                // 2018/04/06 QC#22122 Mod End
                ZYPEZDItemValueSetter.setValue(slsCrPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);

                slsCrPMsgCount++;
            }
        }
        pmsg4OrigPartial.cpoSlsCr.setValidCount(slsCrPMsgCount);
    }
    private void setOrderPrcListOrigPartial(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4OrigPartial) {
        int priceListPMsgCount = 0;
        // Get Price List
        S21SsmEZDResult result = NWAL2300Query.getInstance().getPriceInfo(bizMsg);

        if (!result.isCodeNotFound()) {
            List<ORD_PRC_CALC_BASETMsg> ordPrcCalcBase = (List) result.getResultObject();
            for (int i = 0; i < ordPrcCalcBase.size(); i++) {
                NWZC150001_priceListPMsg priceList = pmsg4OrigPartial.priceList.no(priceListPMsgCount);
                // Copy
                EZDMsg.copy(ordPrcCalcBase.get(i), null, priceList, null);

                priceListPMsgCount++;
            }
        }
        pmsg4OrigPartial.priceList.setValidCount(priceListPMsgCount);
    }
    /**
     * getCposlsCrAryHdr
     * @param glblCmpyCd    String
     * @param cpoOrdNum     String
     * @return  DS_CPO_SLS_CRTMsgArray
     */
    public static DS_CPO_SLS_CRTMsgArray getCposlsCrAryHdr(String glblCmpyCd, String cpoOrdNum) {
        DS_CPO_SLS_CRTMsg inTMsg = new DS_CPO_SLS_CRTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        DS_CPO_SLS_CRTMsgArray array = (DS_CPO_SLS_CRTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (array == null || array.length() == 0) {
            return null;
        }
        return array;
    }
    private void setDsCpoUpdateApiParam4OrigPartial(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4OrigPartial) {

        boolean partialFlg = false;
        HashSet<String> chkPartialShip = new HashSet<String>();

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL2300_CCMsg invLine = bizMsg.C.no(i);
            if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())//
                    && ZYPConstant.FLG_ON_Y.equals(invLine.openFlg_C1.getValue())) {

                S21SsmEZDResult ssmResult = null;
                // get Open Qty
                ssmResult = NWAL2300Query.getInstance().getShipQtyAmt(bizMsg, pmsg4Credit.glblCmpyCd.getValue()//
                        , bizMsg.cpoOrdNum_H1.getValue()//
                        , invLine.cpoDtlLineNum_C1.getValue()//
                        , invLine.cpoDtlLineSubNum_C1.getValue());
                if (ssmResult.isCodeNormal()) {
                    BigDecimal partialQty = (BigDecimal) ssmResult.getResultObject();
                    if (ZYPCommonFunc.hasValue(partialQty)) {
                        if (!chkPartialShip.contains(invLine.dplyLineNum_C1.getValue())) {
                            chkPartialShip.add(invLine.dplyLineNum_C1.getValue());
                        }
                        partialFlg = true;
                    }
                }
            }
        }
        if (partialFlg) {
            setOrderHeaderOrigPartial(bizMsg, pmsg4Credit, pmsg4OrigPartial);
            setOrderConfigAndDetailOrigPartial(bizMsg, pmsg4OrigPartial, chkPartialShip);
            setOrderSalesCreditOrigPartial(bizMsg, pmsg4OrigPartial);
            setOrderPrcListOrigPartial(bizMsg, pmsg4OrigPartial);
        }
    }
    private void setOrderHeaderOrigPartial(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWZC150001PMsg pmsg4OrigPartial) {
        EZDMsg.copy(pmsg4Credit, null, pmsg4OrigPartial, null);

        // Add Start 2017/10/20 QC#21773
        ZYPEZDItemValueSetter.setValue(pmsg4OrigPartial.cpoSrcTpCd, CPO_SRC_TP.REBILL);
        // Add End 2017/10/20 QC#21773

        CPOTMsg cpo = getCPOTMsg(bizMsg);
        if (cpo != null) {
            EZDMsg.copy(cpo, null, pmsg4OrigPartial, null);
        }

        ZYPEZDItemValueSetter.setValue(pmsg4OrigPartial.xxModeCd, MODE_SUBMIT);
        pmsg4OrigPartial.cpoOrdTpCd.clear();
        pmsg4OrigPartial.ordFuflLvlCd.clear();
        pmsg4OrigPartial.addFrtChrgToCd.clear();
        pmsg4OrigPartial.addFrtChrgMethCd.clear();
        ZYPEZDItemValueSetter.setValue(pmsg4OrigPartial.prcBaseDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pmsg4OrigPartial.xxPgFlg_OE, ZYPConstant.FLG_ON_Y);
    }
    private void setOrderConfigAndDetailOrigPartial(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4OrigPartial, HashSet<String> chkPartialShip) {

        if (pmsg4OrigPartial != null) {

            Set<String> procdPosnNums = new HashSet<String>();
            pmsg4OrigPartial.cpoConfig.clear();
            ZYPTableUtil.clear(pmsg4OrigPartial.cpoConfig);
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                NWAL2300_CCMsg invLineMsg = bizMsg.C.no(i);
                if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLineMsg.dsOrdLineDrctnCd_C1.getValue())) {
                    continue;
                }

                String dsOrdPosnNum = invLineMsg.dsOrdPosnNum_C1.getValue();
                if (procdPosnNums.contains(dsOrdPosnNum)) {
                    continue;
                }
                procdPosnNums.add(dsOrdPosnNum);
                setOrderConfigPartial(bizMsg, invLineMsg, pmsg4OrigPartial);
            }

            pmsg4OrigPartial.A.clear();
            ZYPTableUtil.clear(pmsg4OrigPartial.A);
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                NWAL2300_CCMsg invLineMsg = bizMsg.C.no(i);
                if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLineMsg.dsOrdLineDrctnCd_C1.getValue())) {
                    continue;
                }

                if (ZYPConstant.FLG_ON_Y.equals(invLineMsg.openFlg_C1.getValue())) {

                    if (chkPartialShip.contains(invLineMsg.dplyLineNum_C1.getValue())) {
                        continue;
                    }
                }
                setOrderDetailPartial(bizMsg, pmsg4OrigPartial, invLineMsg);
            }
        }
    }
    // 2017/01/11 QC#16669 ADD END
    // 2017/01/17 QC#16691 ADD START
    /**
     * checkIntangibleWh
     * 
     * case not Vender drop ship and intangible and not dummy wh Then true
     * case other false
     * 
     * @param glblCmpyCd    String
     * @param mdseCd        String
     * @param cpoOrdNum     String
     * @param rtlWhCd       String
     * @return  boolean
     */
    private boolean checkIntangibleWh(String glblCmpyCd, String mdseCd, String cpoOrdNum, String rtlWhCd) {
        // check credit&rebill dummy wh cd
        String crRebillDummyWhCdCsv = ZYPCodeDataUtil.getVarCharConstValue("CR_AND_BILL_ONLY_DUMMY_WH_CD", glblCmpyCd);
        if (crRebillDummyWhCdCsv != null) {
            String[] crRebillDummyWhCd = crRebillDummyWhCdCsv.split(",");
            for (int i = 0; i < crRebillDummyWhCd.length; i++) {
                if (crRebillDummyWhCd[i] != null && crRebillDummyWhCd[i].equals(rtlWhCd)) {
                 // case dummy wh
                    return false;
                }
            }
        }
        // case not dummy wh

        // check Vender drop ship
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getCntDropShip(glblCmpyCd, mdseCd, cpoOrdNum);
        if (ssmResult.isCodeNormal()) {
            BigDecimal dropShipCnt = (BigDecimal) ssmResult.getResultObject();
            if (dropShipCnt != null && BigDecimal.ZERO.compareTo(dropShipCnt) < 0) {
                // case Vender drop ship
                return false;
            }
        }
        // case no Vender drop ship

        // check tangible or intangible
        S21SsmEZDResult ssmResult2 = NWAL2300Query.getInstance().getMdseInfo(glblCmpyCd, mdseCd);
        if (ssmResult2.isCodeNormal()) {
            List<Map<String, Object>> rstMapList = (List<Map<String, Object>>) ssmResult2.getResultObject();
            for (Map<String, Object> rstMap : rstMapList) {
                String invtyCtrlFlg = (String) rstMap.get("INVTY_CTRL_FLG");
                String mdseTpCd = (String) rstMap.get("MDSE_TP_CD"); // 2017/02/07 S21_NA#17405 Add
                if (ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg) || MDSE_TP.SET.equals(mdseTpCd)) {
                    // case tangible or set
                    return false;
                }
            }
        }

        // case not Vender drop ship , intangible , not dummy wh
        return true;
    }
    // 2017/01/17 QC#16691 ADD E N D

    // Add Start 2017/10/16 QC#21729
    private boolean isExistNegoDealAmt(NWAL2300CMsg bizMsg) {

        CPOTMsg tMsg = getCPOTMsg(bizMsg);

        if (tMsg == null) {
            return false;
        } else if (ZYPCommonFunc.hasValue(tMsg.negoDealAmt)) {
            return true;
        }

        return false;
    }
    // Add End 2017/10/16 QC#21729

    // 2018/05/29 QC#21841 Del Start
//    // 2017/10/19 QC#21708 Add Start
//    private boolean setCreditPrcPMsgFromOrdPrcCalcBase(NWAL2300CMsg bizMsg, NWZC150001PMsg pmsg4Credit, NWAL2300_CCMsg invLineMsg) {
//
//        ORD_PRC_CALC_BASETMsg ordPrcCalcBaseTMsg = new ORD_PRC_CALC_BASETMsg();
//
//        ordPrcCalcBaseTMsg.setSQLID("001");
//        ordPrcCalcBaseTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
//        ordPrcCalcBaseTMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum_H1.getValue());
//        ordPrcCalcBaseTMsg.setConditionValue("cpoDtlLineNum01", invLineMsg.cpoDtlLineNum_C1.getValue());
//        ordPrcCalcBaseTMsg.setConditionValue("cpoDtlLineSubNum01", invLineMsg.cpoDtlLineSubNum_C1.getValue());
//        ORD_PRC_CALC_BASETMsgArray ordPrcCalcBaseTMsgArray = (ORD_PRC_CALC_BASETMsgArray) EZDTBLAccessor.findByCondition(ordPrcCalcBaseTMsg);
//
//        // Add Start 2017/10/27 QC#22125
//        if (NWAL2300CommonLogic.isSetCompornentChild(bizMsg.glblCmpyCd.getValue(), invLineMsg)) {
//            return true;
//        }
//        // Add End 2017/10/27 QC#22125
//
//        if (null == ordPrcCalcBaseTMsgArray || ordPrcCalcBaseTMsgArray.getValidCount() == 0) {
//            return false;
//        }
//
//        int i = pmsg4Credit.priceList.getValidCount();
//        for (int n = 0; n < ordPrcCalcBaseTMsgArray.getValidCount(); n++) {
//            ORD_PRC_CALC_BASETMsg ordPrcCalcBaseRsltTMsg = ordPrcCalcBaseTMsgArray.no(n);
//            EZDMsg.copy(ordPrcCalcBaseRsltTMsg, null, pmsg4Credit.priceList.no(i), null);
//
//            // QC#22106 2018/03/30 Add Start
//            ZYPEZDItemValueSetter.setValue(pmsg4Credit.priceList.no(i).cpoDtlLineNum, invLineMsg.cpoDtlLineNum_T);
//            // QC#22106 2018/03/30 Add End
//            ZYPEZDItemValueSetter.setValue(pmsg4Credit.priceList.no(i).autoPrcAmtRate, (NWAL2300CommonLogic.negate(pmsg4Credit.priceList.no(i).autoPrcAmtRate.getValue())));
//            ZYPEZDItemValueSetter.setValue(pmsg4Credit.priceList.no(i).manPrcAmtRate, (NWAL2300CommonLogic.negate(pmsg4Credit.priceList.no(i).manPrcAmtRate.getValue())));
//            // 2018/04/25 QC#22122-1 Mod Start
//            //ZYPEZDItemValueSetter.setValue(pmsg4Credit.priceList.no(i).calcPrcAmtRate, (NWAL2300CommonLogic.negate(pmsg4Credit.priceList.no(i).calcPrcAmtRate.getValue())));
//            ZYPEZDItemValueSetter.setValue(pmsg4Credit.priceList.no(i).calcPrcAmtRate, (NWAL2300CommonLogic.negate(pmsg4Credit.priceList.no(i).unitPrcAmt.getValue().multiply(invLineMsg.ordQty_C1.getValue()))));
//            // 2018/04/25 QC#22122-1 Mod End
//            ZYPEZDItemValueSetter.setValue(pmsg4Credit.priceList.no(i).unitPrcAmt, (NWAL2300CommonLogic.negate(pmsg4Credit.priceList.no(i).unitPrcAmt.getValue())));
//
//            i++;
//        }
//        pmsg4Credit.priceList.setValidCount(i);
//        return true;
//    }
//    // 2017/10/19 QC#21708 Add End
    // 2018/05/29 QC#21841 Del End
    // 2017/10/20 QC#21780 Add Start
    private int getDealCcyDigit(String glblCmpyCd, String dealCcyCd) {
        CCYTMsg ccyTMsg = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(ccyTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ccyTMsg.ccyCd, dealCcyCd);
        ccyTMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(ccyTMsg);
        if (ccyTMsg != null) {
            return ccyTMsg.aftDeclPntDigitNum.getValueInt();
        }
        return 0;
    }
    // 2017/10/20 QC#21780 Add End
    // QC#22031 2017/10/31 Add Start
    private static boolean isVendorCode(String glblCmpyCd, String vndCd) {
        VNDTMsg inMsg = new VNDTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("vndCd01", vndCd);
        VNDTMsgArray tmsgArray = (VNDTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (tmsgArray.length() == 0) {
            return false;
        }
        return true;
    }
    // QC#22031 2017/10/31 Add End
    // 2018/05/29 QC#21841 Add Start
    private void setPrcCalcData(Map<String, Object> rstMap, NWAL2300CMsg bizMsg, NWAL2300_CCMsg invLineMsg, NWZC150001PMsg cpoUpdtApiPMsg, String mode) {

        String invLineCatgCd = (String) rstMap.get("INV_LINE_CATG_CD");

        if (S21StringUtil.isEquals(INV_LINE_CATG.ITEM, invLineCatgCd)) {
            setBasePrcListData(rstMap, bizMsg, invLineMsg, cpoUpdtApiPMsg, mode);
        } else if (S21StringUtil.isEquals(INV_LINE_CATG.CHARGE, invLineCatgCd) //
                || S21StringUtil.isEquals(INV_LINE_CATG.FREIGHT, invLineCatgCd)) {
            setFreightChargePrcListData(rstMap, bizMsg, invLineMsg, cpoUpdtApiPMsg,  mode);
        }
    }

    private void setBasePrcListData(Map<String, Object> rstMap, NWAL2300CMsg bizMsg, NWAL2300_CCMsg invLineMsg, NWZC150001PMsg cpoUpdtApiPMsg, String mode) {

        // QC#27478 2018/07/31 Mod Start
        //// BASE
        //final NWZC150001_priceListPMsg basePrcPMsg = cpoUpdtApiPMsg.priceList.no(cpoUpdtApiPMsg.priceList.getValidCount());
        //cpoUpdtApiPMsg.priceList.setValidCount(cpoUpdtApiPMsg.priceList.getValidCount() + 1);
        //
        // QC#22106 2018/03/30 Add Start
        //ZYPEZDItemValueSetter.setValue(basePrcPMsg.cpoDtlLineNum, (String) rstMap.get("CPO_DTL_LINE_NUM"));
        //ZYPEZDItemValueSetter.setValue(basePrcPMsg.cpoDtlLineNum, invLineMsg.cpoDtlLineNum_T);
        //// QC#22106 2018/03/30 Add End
        //ZYPEZDItemValueSetter.setValue(basePrcPMsg.cpoDtlLineSubNum, (String) rstMap.get("CPO_DTL_LINE_SUB_NUM"));
        //ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondTpCd, PRC_COND_TP.BASE_PRICE);
        //ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.BASE_PRICE);
        //ZYPEZDItemValueSetter.setValue(basePrcPMsg.pkgUomCd, (String) rstMap.get("UOM_CD"));
        //ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondUnitCd, PRC_COND_UNIT.AMT);
        //ZYPEZDItemValueSetter.setValue(basePrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.BASE_PRICE));
        //ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
        //ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
        //ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
        //if (SET_PRC_DATA_MODE_ORDER_CREDIT.equals(mode)) {
        //    ZYPEZDItemValueSetter.setValue(basePrcPMsg.autoPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"))));
        //    ZYPEZDItemValueSetter.setValue(basePrcPMsg.manPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"))));
        //    ZYPEZDItemValueSetter.setValue(basePrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate(((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT")).multiply(invLineMsg.ordQty_C1.getValue()))));
        //    ZYPEZDItemValueSetter.setValue(basePrcPMsg.unitPrcAmt, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"))));
        //} else if (SET_PRC_DATA_MODE_RETURN_CREDIT.equals(mode)) {
        //    ZYPEZDItemValueSetter.setValue(basePrcPMsg.autoPrcAmtRate, (BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"));
        //    ZYPEZDItemValueSetter.setValue(basePrcPMsg.manPrcAmtRate, (BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"));
        //    ZYPEZDItemValueSetter.setValue(basePrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate(((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT")).multiply(invLineMsg.ordQty_C1.getValue()))));
        //    ZYPEZDItemValueSetter.setValue(basePrcPMsg.unitPrcAmt, (BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"));
        //}
        //ZYPEZDItemValueSetter.setValue(basePrcPMsg.autoPrcCcyCd, (String) rstMap.get("DEAL_CCY_CD"));
        //
        //// QC#22106 2018/03/30 Add Start
        ////QC#15624 add Start
        ////if (!ZYPCommonFunc.hasValue(basePrcPMsg.cpoDtlLineNum)) {
        ////    ZYPEZDItemValueSetter.setValue(basePrcPMsg.cpoDtlLineNum, String.valueOf(i + 1));
        ////}
        ////QC#15624 add End
        //// QC#22106 2018/03/30 Add End
        //
        //// Rounding QC#21841
        //BigDecimal fraction = ((BigDecimal) rstMap.get("INV_LINE_DEAL_NET_AMT")).subtract(((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT")).multiply(invLineMsg.ordQty_C1.getValue()));
        //if (BigDecimal.ZERO.compareTo(fraction) != 0) {
        //    final NWZC150001_priceListPMsg baseRoundingPrcPMsg = cpoUpdtApiPMsg.priceList.no(cpoUpdtApiPMsg.priceList.getValidCount());
        //    cpoUpdtApiPMsg.priceList.setValidCount(cpoUpdtApiPMsg.priceList.getValidCount() + 1);
        //    EZDMsg.copy(basePrcPMsg, null, baseRoundingPrcPMsg, null);
        //
        //    ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.ROUNDING_FACTOR_0);
        //    ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.ROUNDING_FACTOR));
        //    if (SET_PRC_DATA_MODE_ORDER_CREDIT.equals(mode)) {
        //        ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.autoPrcAmtRate, (NWAL2300CommonLogic.negate(fraction)));
        //        ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.manPrcAmtRate, (NWAL2300CommonLogic.negate(fraction)));
        //        ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate(fraction)));
        //        ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.unitPrcAmt, (NWAL2300CommonLogic.negate(fraction)));
        //    } else if (SET_PRC_DATA_MODE_RETURN_CREDIT.equals(mode)) {
        //        ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.autoPrcAmtRate, fraction);
        //        ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.manPrcAmtRate, fraction);
        //        ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate(fraction)));
        //        ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.unitPrcAmt, fraction);
        //    }
        //    baseRoundingPrcPMsg.pkgUomCd.clear();
        //}
        //
        //// BASE TAX
        //final NWZC150001_priceListPMsg baseTaxPrcPMsg = cpoUpdtApiPMsg.priceList.no(cpoUpdtApiPMsg.priceList.getValidCount());
        //cpoUpdtApiPMsg.priceList.setValidCount(cpoUpdtApiPMsg.priceList.getValidCount() + 1);
        //EZDMsg.copy(basePrcPMsg, null, baseTaxPrcPMsg, null);
        //
        //ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.prcCondTpCd, PRC_COND_TP.ITEM_TAX1);
        //ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.TAX);
        //ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.ITEM_TAX1));
        //if (SET_PRC_DATA_MODE_ORDER_CREDIT.equals(mode)) {
        //    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.autoPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
        //    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.manPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
        //    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
        //    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.unitPrcAmt, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
        //} else if (SET_PRC_DATA_MODE_RETURN_CREDIT.equals(mode)) {
        //    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.autoPrcAmtRate, (BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"));
        //    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.manPrcAmtRate, (BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"));
        //    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
        //    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.unitPrcAmt, (BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"));
        //}
        //baseTaxPrcPMsg.pkgUomCd.clear();
        // BASE
        if (SET_PRC_DATA_MODE_ORDER_CREDIT.equals(mode)) {
            final NWZC150001_priceListPMsg basePrcPMsg = cpoUpdtApiPMsg.priceList.no(cpoUpdtApiPMsg.priceList.getValidCount());
            cpoUpdtApiPMsg.priceList.setValidCount(cpoUpdtApiPMsg.priceList.getValidCount() + 1);

            ZYPEZDItemValueSetter.setValue(basePrcPMsg.cpoDtlLineNum, invLineMsg.cpoDtlLineNum_T);
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.cpoDtlLineSubNum, (String) rstMap.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondTpCd, PRC_COND_TP.BASE_PRICE);
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.BASE_PRICE);
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.pkgUomCd, (String) rstMap.get("UOM_CD"));
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondUnitCd, PRC_COND_UNIT.AMT);
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.BASE_PRICE));
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.autoPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"))));
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.manPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"))));
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate(((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT")).multiply(invLineMsg.ordQty_C1.getValue()))));
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.unitPrcAmt, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"))));
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.autoPrcCcyCd, (String) rstMap.get("DEAL_CCY_CD"));
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y); // QC#9700  2018/09/03 Add
            basePrcPMsg.prcRulePrcdPk.clear(); // QC#9700  2018/09/03 Add

            BigDecimal fraction = ((BigDecimal) rstMap.get("INV_LINE_DEAL_NET_AMT")).subtract(((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT")).multiply(invLineMsg.ordQty_C1.getValue()));
            if (BigDecimal.ZERO.compareTo(fraction) != 0) {
                final NWZC150001_priceListPMsg baseRoundingPrcPMsg = cpoUpdtApiPMsg.priceList.no(cpoUpdtApiPMsg.priceList.getValidCount());
                cpoUpdtApiPMsg.priceList.setValidCount(cpoUpdtApiPMsg.priceList.getValidCount() + 1);
                EZDMsg.copy(basePrcPMsg, null, baseRoundingPrcPMsg, null);

                ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.ROUNDING_FACTOR_0);
                ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.ROUNDING_FACTOR));
                ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.autoPrcAmtRate, (NWAL2300CommonLogic.negate(fraction)));
                // QC#50747 2019/06/10 Mod End
                ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.manPrcAmtRate, (NWAL2300CommonLogic.negate(fraction)));
                ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate(fraction)));
                ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.unitPrcAmt, (NWAL2300CommonLogic.negate(fraction)));
                baseRoundingPrcPMsg.pkgUomCd.clear();
            }

            // BASE TAX
            final NWZC150001_priceListPMsg baseTaxPrcPMsg = cpoUpdtApiPMsg.priceList.no(cpoUpdtApiPMsg.priceList.getValidCount());
            cpoUpdtApiPMsg.priceList.setValidCount(cpoUpdtApiPMsg.priceList.getValidCount() + 1);
            EZDMsg.copy(basePrcPMsg, null, baseTaxPrcPMsg, null);

            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.prcCondTpCd, PRC_COND_TP.ITEM_TAX1);
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.TAX);
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.ITEM_TAX1));
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.autoPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.manPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.unitPrcAmt, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
            baseTaxPrcPMsg.pkgUomCd.clear();

        } else {

            final NWZC150001_rtnPriceListPMsg basePrcPMsg = cpoUpdtApiPMsg.rtnPriceList.no(cpoUpdtApiPMsg.rtnPriceList.getValidCount());
            cpoUpdtApiPMsg.rtnPriceList.setValidCount(cpoUpdtApiPMsg.rtnPriceList.getValidCount() + 1);

            ZYPEZDItemValueSetter.setValue(basePrcPMsg.cpoDtlLineNum, invLineMsg.cpoDtlLineNum_T);
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.cpoDtlLineSubNum, (String) rstMap.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondTpCd, PRC_COND_TP.BASE_PRICE);
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.BASE_PRICE);
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.pkgUomCd, (String) rstMap.get("UOM_CD"));
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondUnitCd, PRC_COND_UNIT.AMT);
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.BASE_PRICE));
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.autoPrcAmtRate, (BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.manPrcAmtRate, (BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate(((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT")).multiply(invLineMsg.ordQty_C1.getValue()))));
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.unitPrcAmt, (BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.autoPrcCcyCd, (String) rstMap.get("DEAL_CCY_CD"));
            ZYPEZDItemValueSetter.setValue(basePrcPMsg.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y); // QC#9700  2018/09/03 Add
            basePrcPMsg.prcRulePrcdPk.clear(); // QC#9700  2018/09/03 Add

            BigDecimal fraction = ((BigDecimal) rstMap.get("INV_LINE_DEAL_NET_AMT")).subtract(((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT")).multiply(invLineMsg.ordQty_C1.getValue()));
            if (BigDecimal.ZERO.compareTo(fraction) != 0) {
                final NWZC150001_rtnPriceListPMsg baseRoundingPrcPMsg = cpoUpdtApiPMsg.rtnPriceList.no(cpoUpdtApiPMsg.rtnPriceList.getValidCount());
                cpoUpdtApiPMsg.rtnPriceList.setValidCount(cpoUpdtApiPMsg.rtnPriceList.getValidCount() + 1);
                EZDMsg.copy(basePrcPMsg, null, baseRoundingPrcPMsg, null);

                ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.ROUNDING_FACTOR_0);
                ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.ROUNDING_FACTOR));
                ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.autoPrcAmtRate, fraction);
                ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.manPrcAmtRate, fraction);
                ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate(fraction)));
                ZYPEZDItemValueSetter.setValue(baseRoundingPrcPMsg.unitPrcAmt, fraction);
                baseRoundingPrcPMsg.pkgUomCd.clear();
            }

            // BASE TAX
            final NWZC150001_rtnPriceListPMsg baseTaxPrcPMsg = cpoUpdtApiPMsg.rtnPriceList.no(cpoUpdtApiPMsg.rtnPriceList.getValidCount());
            cpoUpdtApiPMsg.rtnPriceList.setValidCount(cpoUpdtApiPMsg.rtnPriceList.getValidCount() + 1);
            EZDMsg.copy(basePrcPMsg, null, baseTaxPrcPMsg, null);

            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.prcCondTpCd, PRC_COND_TP.ITEM_TAX1);
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.TAX);
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, PRC_COND_TP.ITEM_TAX1));
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.autoPrcAmtRate, (BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"));
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.manPrcAmtRate, (BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"));
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.unitPrcAmt, (BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"));
            baseTaxPrcPMsg.pkgUomCd.clear();
        }
        // QC#27478 2018/07/31 Mod End
    }

    private void setFreightChargePrcListData(Map<String, Object> rstMap, NWAL2300CMsg bizMsg, NWAL2300_CCMsg invLineMsg, NWZC150001PMsg cpoUpdtApiPMsg, String mode) {

        String prcDtlGrpCd = (String) rstMap.get("PRC_DTL_GRP_CD");
        if (!ZYPCommonFunc.hasValue(prcDtlGrpCd)) {
            return;
        }

        // QC#50130 2019/05/15 Mod Start
        //String prcCondTpCd = PRC_DTL_GRP_COND_TP_MAP.get(prcDtlGrpCd);
        String prcCondTpCd = null;
        if (SET_PRC_DATA_MODE_ORDER_CREDIT.equals(mode)) {
            prcCondTpCd = PRC_DTL_GRP_COND_TP_MAP.get(prcDtlGrpCd);
        }else {
            prcCondTpCd = PRC_DTL_GRP_COND_TP_RMA_MAP.get(prcDtlGrpCd);
        }
        // QC#50130 2019/05/15 Mod End
        if (!ZYPCommonFunc.hasValue(prcCondTpCd)) {
            return;
        }

        String prcDtlGrpCdRound = null;
        String prcCondTpCdTax = null;
        String invLineCatgCd = (String) rstMap.get("INV_LINE_CATG_CD");
        if (S21StringUtil.isEquals(INV_LINE_CATG.FREIGHT, invLineCatgCd)) {
            prcDtlGrpCdRound = PRC_DTL_GRP.ROUNDING_FACTOR_1;
            prcCondTpCdTax = PRC_COND_TP.FREIGHT_TAX1;
        } else if (S21StringUtil.isEquals(INV_LINE_CATG.CHARGE, invLineCatgCd)) {
            prcDtlGrpCdRound = PRC_DTL_GRP.ROUNDING_FACTOR_2;
            prcCondTpCdTax = PRC_COND_TP.CHARGE_TAX1;
        }
        // QC#27478 2018/07/31 Mod Start
        //final NWZC150001_priceListPMsg chargePrcPMsg = cpoUpdtApiPMsg.priceList.no(cpoUpdtApiPMsg.priceList.getValidCount());
        //cpoUpdtApiPMsg.priceList.setValidCount(cpoUpdtApiPMsg.priceList.getValidCount() + 1);
        //
        // QC#22106 2018/03/30 Add Start
        //ZYPEZDItemValueSetter.setValue(chargePrcPMsg.cpoDtlLineNum, (String) rstMap.get("CPO_DTL_LINE_NUM"));
        //ZYPEZDItemValueSetter.setValue(chargePrcPMsg.cpoDtlLineNum, invLineMsg.cpoDtlLineNum_T);
        //// QC#22106 2018/03/30 Add End
        //ZYPEZDItemValueSetter.setValue(chargePrcPMsg.cpoDtlLineSubNum, (String) rstMap.get("CPO_DTL_LINE_SUB_NUM"));
        //ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcCondTpCd, prcCondTpCd);
        //ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcDtlGrpCd, prcDtlGrpCd);
        //ZYPEZDItemValueSetter.setValue(chargePrcPMsg.pkgUomCd, (String) rstMap.get("UOM_CD"));
        //ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcCondUnitCd, PRC_COND_UNIT.AMT);
        //ZYPEZDItemValueSetter.setValue(chargePrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, prcCondTpCd));
        //ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
        //ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
        //ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
        //if (SET_PRC_DATA_MODE_ORDER_CREDIT.equals(mode)) {
        //    ZYPEZDItemValueSetter.setValue(chargePrcPMsg.autoPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"))));
        //    ZYPEZDItemValueSetter.setValue(chargePrcPMsg.manPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"))));
        //    ZYPEZDItemValueSetter.setValue(chargePrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate(((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT")).multiply(invLineMsg.ordQty_C1.getValue()))));
        //    ZYPEZDItemValueSetter.setValue(chargePrcPMsg.unitPrcAmt, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"))));
        //} else if (SET_PRC_DATA_MODE_RETURN_CREDIT.equals(mode)) {
        //    ZYPEZDItemValueSetter.setValue(chargePrcPMsg.autoPrcAmtRate, (BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"));
        //    ZYPEZDItemValueSetter.setValue(chargePrcPMsg.manPrcAmtRate, (BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"));
        //    ZYPEZDItemValueSetter.setValue(chargePrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate(((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT")).multiply(invLineMsg.ordQty_C1.getValue()))));
        //    ZYPEZDItemValueSetter.setValue(chargePrcPMsg.unitPrcAmt, (BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"));
        //}
        //ZYPEZDItemValueSetter.setValue(chargePrcPMsg.autoPrcCcyCd, (String) rstMap.get("DEAL_CCY_CD"));
        //
        //// Rounding QC#21841
        //BigDecimal fraction = ((BigDecimal) rstMap.get("INV_LINE_DEAL_NET_AMT")).subtract(((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT")).multiply(invLineMsg.ordQty_C1.getValue()));
        //if (BigDecimal.ZERO.compareTo(fraction) != 0) {
        //    final NWZC150001_priceListPMsg chargeRoundingPrcPMsg = cpoUpdtApiPMsg.priceList.no(cpoUpdtApiPMsg.priceList.getValidCount());
        //    cpoUpdtApiPMsg.priceList.setValidCount(cpoUpdtApiPMsg.priceList.getValidCount() + 1);
        //    EZDMsg.copy(chargePrcPMsg, null, chargeRoundingPrcPMsg, null);
        //
        //    ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.prcDtlGrpCd, prcDtlGrpCdRound);
        //    ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, chargeRoundingPrcPMsg.prcCondTpCd.getValue()));
        //    if (SET_PRC_DATA_MODE_ORDER_CREDIT.equals(mode)) {
        //        ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.autoPrcAmtRate, (NWAL2300CommonLogic.negate(fraction)));
        //        ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.manPrcAmtRate, (NWAL2300CommonLogic.negate(fraction)));
        //        ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate(fraction)));
        //        ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.unitPrcAmt, (NWAL2300CommonLogic.negate(fraction)));
        //    } else if (SET_PRC_DATA_MODE_RETURN_CREDIT.equals(mode)) {
        //        ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.autoPrcAmtRate, fraction);
        //        ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.manPrcAmtRate, fraction);
        //        ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate(fraction)));
        //        ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.unitPrcAmt, fraction);
        //    }
        //    chargeRoundingPrcPMsg.pkgUomCd.clear();
        //}
        //
        //// BASE TAX
        //final NWZC150001_priceListPMsg baseTaxPrcPMsg = cpoUpdtApiPMsg.priceList.no(cpoUpdtApiPMsg.priceList.getValidCount());
        //cpoUpdtApiPMsg.priceList.setValidCount(cpoUpdtApiPMsg.priceList.getValidCount() + 1);
        //EZDMsg.copy(chargePrcPMsg, null, baseTaxPrcPMsg, null);
        //
        //ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.prcCondTpCd, prcCondTpCdTax);
        //ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.TAX);
        //ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, prcCondTpCdTax));
        //if (SET_PRC_DATA_MODE_ORDER_CREDIT.equals(mode)) {
        //    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.autoPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
        //    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.manPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
        //    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
        //    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.unitPrcAmt, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
        //} else if (SET_PRC_DATA_MODE_RETURN_CREDIT.equals(mode)) {
        //    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.autoPrcAmtRate, (BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"));
        //    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.manPrcAmtRate, (BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"));
        //    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
        //    ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.unitPrcAmt, (BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"));
        //}
        //baseTaxPrcPMsg.pkgUomCd.clear();
        if (SET_PRC_DATA_MODE_ORDER_CREDIT.equals(mode)) {
            final NWZC150001_priceListPMsg chargePrcPMsg = cpoUpdtApiPMsg.priceList.no(cpoUpdtApiPMsg.priceList.getValidCount());
            cpoUpdtApiPMsg.priceList.setValidCount(cpoUpdtApiPMsg.priceList.getValidCount() + 1);

            // QC#22106 2018/03/30 Add Start
            //ZYPEZDItemValueSetter.setValue(chargePrcPMsg.cpoDtlLineNum, (String) rstMap.get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.cpoDtlLineNum, invLineMsg.cpoDtlLineNum_T);
            // QC#22106 2018/03/30 Add End
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.cpoDtlLineSubNum, (String) rstMap.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcCondTpCd, prcCondTpCd);
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcDtlGrpCd, prcDtlGrpCd);
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.pkgUomCd, (String) rstMap.get("UOM_CD"));
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcCondUnitCd, PRC_COND_UNIT.AMT);
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, prcCondTpCd));
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
            // QC#50776 2019/06/10 Mod Start
            // ZYPEZDItemValueSetter.setValue(chargePrcPMsg.autoPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"))));
            chargePrcPMsg.autoPrcAmtRate.clear();
            // QC#50776 2019/06/10 Mod End
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.manPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"))));
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate(((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT")).multiply(invLineMsg.ordQty_C1.getValue()))));
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.unitPrcAmt, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"))));
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.autoPrcCcyCd, (String) rstMap.get("DEAL_CCY_CD"));
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y); // QC#9700  2018/09/03 Add
            chargePrcPMsg.prcRulePrcdPk.clear(); // QC#9700  2018/09/03 Add

            // Rounding QC#21841
            BigDecimal fraction = ((BigDecimal) rstMap.get("INV_LINE_DEAL_NET_AMT")).subtract(((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT")).multiply(invLineMsg.ordQty_C1.getValue()));
            if (BigDecimal.ZERO.compareTo(fraction) != 0) {
                final NWZC150001_priceListPMsg chargeRoundingPrcPMsg = cpoUpdtApiPMsg.priceList.no(cpoUpdtApiPMsg.priceList.getValidCount());
                cpoUpdtApiPMsg.priceList.setValidCount(cpoUpdtApiPMsg.priceList.getValidCount() + 1);
                EZDMsg.copy(chargePrcPMsg, null, chargeRoundingPrcPMsg, null);

                ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.prcDtlGrpCd, prcDtlGrpCdRound);
                ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, chargeRoundingPrcPMsg.prcCondTpCd.getValue()));
                // QC#50776 2019/06/10 Mod Start
                // ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.autoPrcAmtRate, (NWAL2300CommonLogic.negate(fraction)));
                ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.autoPrcAmtRate, BigDecimal.ZERO);
                // QC#50776 2019/06/10 Mod End
                ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.manPrcAmtRate, (NWAL2300CommonLogic.negate(fraction)));
                ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate(fraction)));
                ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.unitPrcAmt, (NWAL2300CommonLogic.negate(fraction)));
                chargeRoundingPrcPMsg.pkgUomCd.clear();
            }

            // BASE TAX
            final NWZC150001_priceListPMsg baseTaxPrcPMsg = cpoUpdtApiPMsg.priceList.no(cpoUpdtApiPMsg.priceList.getValidCount());
            cpoUpdtApiPMsg.priceList.setValidCount(cpoUpdtApiPMsg.priceList.getValidCount() + 1);
            EZDMsg.copy(chargePrcPMsg, null, baseTaxPrcPMsg, null);

            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.prcCondTpCd, prcCondTpCdTax);
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.TAX);
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, prcCondTpCdTax));
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.autoPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.manPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.unitPrcAmt, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
            baseTaxPrcPMsg.pkgUomCd.clear();
        } else {
            final NWZC150001_rtnPriceListPMsg chargePrcPMsg = cpoUpdtApiPMsg.rtnPriceList.no(cpoUpdtApiPMsg.rtnPriceList.getValidCount());
            cpoUpdtApiPMsg.rtnPriceList.setValidCount(cpoUpdtApiPMsg.rtnPriceList.getValidCount() + 1);

            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.cpoDtlLineNum, invLineMsg.cpoDtlLineNum_T);
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.cpoDtlLineSubNum, (String) rstMap.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcCondTpCd, prcCondTpCd);
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcDtlGrpCd, prcDtlGrpCd);
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.pkgUomCd, (String) rstMap.get("UOM_CD"));
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcCondUnitCd, PRC_COND_UNIT.AMT);
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, prcCondTpCd));
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
            // QC#50776 2019/06/10 Mod Start
            //ZYPEZDItemValueSetter.setValue(chargePrcPMsg.autoPrcAmtRate, (BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"));
            chargePrcPMsg.autoPrcAmtRate.clear();
            // QC#50776 2019/06/10 Mod End
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.manPrcAmtRate, (BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate(((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT")).multiply(invLineMsg.ordQty_C1.getValue()))));
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.unitPrcAmt, (BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.autoPrcCcyCd, (String) rstMap.get("DEAL_CCY_CD"));
            ZYPEZDItemValueSetter.setValue(chargePrcPMsg.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y); // QC#9700  2018/09/03 Add
            chargePrcPMsg.prcRulePrcdPk.clear(); // QC#9700  2018/09/03 Add

            // Rounding QC#21841
            BigDecimal fraction = ((BigDecimal) rstMap.get("INV_LINE_DEAL_NET_AMT")).subtract(((BigDecimal) rstMap.get("DEAL_NET_UNIT_PRC_AMT")).multiply(invLineMsg.ordQty_C1.getValue()));
            if (BigDecimal.ZERO.compareTo(fraction) != 0) {
                final NWZC150001_rtnPriceListPMsg chargeRoundingPrcPMsg = cpoUpdtApiPMsg.rtnPriceList.no(cpoUpdtApiPMsg.rtnPriceList.getValidCount());
                cpoUpdtApiPMsg.rtnPriceList.setValidCount(cpoUpdtApiPMsg.rtnPriceList.getValidCount() + 1);
                EZDMsg.copy(chargePrcPMsg, null, chargeRoundingPrcPMsg, null);

                ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.prcDtlGrpCd, prcDtlGrpCdRound);
                ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, chargeRoundingPrcPMsg.prcCondTpCd.getValue()));
                // QC#50776 2019/06/10 Mod Start
                // ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.autoPrcAmtRate, fraction);
                ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.autoPrcAmtRate, BigDecimal.ZERO);
                // QC#50776 2019/06/10 Mod Start
                ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.manPrcAmtRate, fraction);
                ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate(fraction)));
                ZYPEZDItemValueSetter.setValue(chargeRoundingPrcPMsg.unitPrcAmt, fraction);
                chargeRoundingPrcPMsg.pkgUomCd.clear();
            }
            // BASE TAX
            final NWZC150001_rtnPriceListPMsg baseTaxPrcPMsg = cpoUpdtApiPMsg.rtnPriceList.no(cpoUpdtApiPMsg.rtnPriceList.getValidCount());
            cpoUpdtApiPMsg.rtnPriceList.setValidCount(cpoUpdtApiPMsg.rtnPriceList.getValidCount() + 1);
            EZDMsg.copy(chargePrcPMsg, null, baseTaxPrcPMsg, null);

            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.prcCondTpCd, prcCondTpCdTax);
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.prcDtlGrpCd, PRC_DTL_GRP.TAX);
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.specCondPrcPk, getSpecCondPrcPk(bizMsg, prcCondTpCdTax));
            
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.autoPrcAmtRate, (BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"));
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.manPrcAmtRate, (BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"));
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.calcPrcAmtRate, (NWAL2300CommonLogic.negate((BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"))));
            ZYPEZDItemValueSetter.setValue(baseTaxPrcPMsg.unitPrcAmt, (BigDecimal) rstMap.get("INV_LINE_DEAL_TAX_AMT"));
            baseTaxPrcPMsg.pkgUomCd.clear();
        }
        // QC#27478 2018/07/31 Mod End
    }
    // 2018/05/29 QC#21841 Add End

    // 2018/05/29 QC#21841 Add Start
    private boolean isOrderClosed(NWAL2300CMsg bizMsg) {

        CPOTMsg cpoTMsg = getCPOTMsg(bizMsg);
        if (cpoTMsg == null) {
            return false;
        }
        return S21StringUtil.isEquals(ORD_HDR_STS.CLOSED, cpoTMsg.ordHdrStsCd.getValue());
    }
    // 2018/05/29 QC#21841 Add End

    // 2018/06/04 QC#26479 Add Start
    private int getRelatedDetailPMsg(NWZC150001PMsg pMsg, NWAL2300_CCMsg invLineMsg) {

        for (int idx = 0; idx < pMsg.A.getValidCount(); idx++) {
            if (S21StringUtil.isEquals(pMsg.A.no(idx).cpoDtlLineNum_A1.getValue(), invLineMsg.cpoDtlLineNum_C1.getValue()) //
                    && S21StringUtil.isEquals(pMsg.A.no(idx).cpoDtlLineSubNum_A1.getValue(), invLineMsg.cpoDtlLineSubNum_C1.getValue())) {
                return idx;
            }
        }
        return -1;
    }

    private BigDecimal addBigDecimal(BigDecimal arg1, BigDecimal arg2) {

        if (arg1 == null) {
            arg1 = BigDecimal.ZERO;
        }
        if (arg2 == null) {
            arg2 = BigDecimal.ZERO;
        }
        return arg1.add(arg2);
    }
    // 2018/06/04 QC#26479 Add End
    
    // 2018/11/16 S21_NA#27299 Add Start
    private void setOrigShipInfo(NWZC150001_cpoConfigPMsg cpoConfigPMsg, INV_BOLTMsg invBol){
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToCustLocCd, invBol.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToLocNm, invBol.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToAddlLocNm, invBol.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipFirstLineAddr, invBol.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipScdLineAddr, invBol.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipThirdLineAddr, invBol.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipFrthLineAddr, invBol.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToCntyNm, invBol.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToCtyAddr, invBol.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToStCd, invBol.shipToStCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToPostCd, invBol.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.origShipToCtryCd, invBol.shipToCtryCd);
    }
    // 2018/11/16 S21_NA#27299 Add End
}
