/*
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 */
package business.servlet.NWAL1500.common;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_AUTO_ADD_RMA_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_AUTO_ADD_SUPPLY_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CHANGEORDERMOD_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_DWL_BTN_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_DWL_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_DWL_LABEL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_COPYFROM_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_COPYLINE_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CREDITCARD_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_DELIVERYINFO_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_DICHECK_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_HOLDS_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_LINE_CANCEL_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_MASSUPDATE_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_METERENTRY_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_ORDERAMTCALC_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_ORDER_CANCEL_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_ORDER_COPY_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_ORDER_SRCH_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_PRC_CHG_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_PROFITABILITY_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_SERVICEPRIC_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_SHIPPINGDETAIL_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_SPESIAL_INS_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_STC_OVER_VIEW_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.CPO_DTL_LINE_SUB_NUM_SET_PRNT;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_ADDL_HEADER;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_BOOKED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_CANCELLED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_CLOSED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_CREDIT_HOLD;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_DI_CHECK_HOLD;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_ENTERED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_PENDING;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_PROFIITABILITY_HOLD;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_SUPPLY_ABUSE_HOLD;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_VALIDATION_HOLD;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_BACK_ORDER;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_BILLING_HOLD;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_BOOKED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_CANCELLED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_CLOSED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_CLOSED_LOAN_RETURN;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_CLOSED_LOAN_SOLD;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_DELIVERED_TO_SHOP;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_ENTERED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_INVOICED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_IN_SHOP_OR_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_ON_LOAN;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_PARTIAL_RECEIVED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_PENDING_ALLOCATION;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_PENDING_DEALER_INSTALL;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_PENDING_DELIVERY_CONF;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_PENDING_FULFILLMENT;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_PENDING_INSPECTION;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_PENDING_INSTALL;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_PENDING_INVOICE;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_PENDING_RETURN;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_PENDING_SHIP;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_PO_CANCEL;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_RWS_CANCELLED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_SHIPPED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_SHIPPED_CLOSED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_SO_CANCEL;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_UNREGISTERED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_WAITING_PICK;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_WAITING_RECEIPT;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.MOD_LOGISTICS;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.MOD_PRICE_AUTH;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.MOD_PRICE_INBOUND;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.MOD_PRICE_OUTBOUND;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.MOD_QTY_AUTH;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.MOD_WF_ITEMS_AUTH;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.REF_AUTH;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL1500.NWAL1500BMsg;
import business.servlet.NWAL1500.NWAL1500_ABMsg;
import business.servlet.NWAL1500.NWAL1500_BBMsg;
import business.servlet.NWAL1500.NWAL1500_BBMsgArray;
import business.servlet.NWAL1500.NWAL1500_CBMsg;
import business.servlet.NWAL1500.NWAL1500_DBMsg;
import business.servlet.NWAL1500.NWAL1500_DBMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/09   Fujitsu         T.Yoshida       Create          N/A
 * 2016/01/12   Fujitsu         T.Ishii         Update          S21_NA#2911
 * 2016/02/02   Fujitsu         S.Takami        Update          S21_NA#3398
 * 2016/02/08   Fujitsu         M.Mikio         Update          QC#1615
 * 2016/02/22   Fujitsu         Y.Taoka         Update          QC#1694/QC#1699
 * 2016/02/28   Fujitsu         K.Sato          Update          S21_NA#1738
 * 2016/02/29   Fujitsu         M.Nakamura      Update          S21_NA#1693/1700
 * 2016/02/28   Fujitsu         K.Sato          Update          S21_NA#4377
 * 2016/03/04   Fujitsu         K.Sato          Update          S21_NA#2178
 * 2016/03/25   Fujitsu         S.Takami        Update          S21_NA#4693
 * 2016/04/26   Fujitsu         M.Yamada        Update          S21_NA#6312
 * 2016/05/11   Fujitsu         S.Iidaka        Update          S21_NA#7262
 * 2016/05/11   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/05/18   Fujitsu         T.Murai         Update          S21_NA#5337
 * 2016/05/19   Fujitsu         T.ishii         Update          S21_NA#5340
 * 2016/05/23   Fujitsu         T.Murai         Update          S21_NA#7145
 * 2016/05/26   Fujitsu         T.Murai         Update          S21_NA#2334
 * 2016/05/30   Fujitsu         K.Sato          Update          S21_NA#8509
 * 2016/06/28   Fujitsu         S.Takami        Update          S21_NA#10946
 * 2016/07/04   Fujitsu         K.Sato          Update          S21_NA#7504/10723
 * 2016/08/02   Fujitsu         Y.Taoka         Update          S21_NA#_12685
 * 2016/08/09   Fujitsu         S.Iidaka        Update          S21_NA#11592
 * 2016/08/09   Fujitsu         Y.Taoka         Update          S21_NA#8388
 * 2016/08/26   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2016/09/06   Fujitsu         S.Iidaka        Update          S21_NA#8775
 * 2016/09/20   Fujitsu         S.Takami        Update          S21_NA#6100-2
 * 2016/10/13   Fujitsu         N.Sugiura       Update          S21_NA#7700
 * 2016/10/19   Fujitsu         N.Sugiura       Update          S21_NA#14728
 * 2016/10/25   Fujitsu         M.Ohno          Update          S21_NA#14937
 * 2016/12/06   Fujitsu         S.Ohki          Update          S21_NA#15898
 * 2016/12/07   Fujitsu         S.Ohki          Update          S21_NA#15934
 * 2016/12/19   Fujitsu         S.Ohki          Update          S21_NA#15898-2
 * 2017/02/21   Fujitsu         Y.Kanefusa      Update          S21_NA#17474
 * 2017/10/03   Fujitsu         S.Takami        Update          S21_NA#21167
 * 2017/11/02   Fujitsu         T.Aoi           Update          S21_NA#20146(Sol#92)
 * 2017/11/07   Fujitsu         S.Takami        Update          S21_NA#22351
 * 2017/11/07   Fujitsu         A.Kosai         Update          S21_NA#22252
 * 2017/11/24   Fujitsu         Y.Kanefusa      Update          S21_NA#22789
 * 2017/12/07   Fujitsu         A.Kosai         Update          S21_NA#21621
 * 2017/12/12   Fujitsu         N.Sugiura       Update          S21_NA#20164
 * 2018/01/10   Fujitsu         Y.Kanefusa      Update          S21_NA#22372
 * 2018/02/02   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/04/12   Fujitsu         K.Ishizuka      Update          S21_NA#23704
 * 2018/04/24   Fujitsu         S.Takami        Update          S21_NA#25534
 * 2018/05/10   Fujitsu         S.Takami        Update          S21_NA#25251
 * 2018/05/11   Fujitsu         Y.Kanefusa      Update          S21_NA#26085
 * 2018/05/22   Fujitsu         S.Takami        Update          S21_NA#25251-2
 * 2018/06/19   Fujitsu         T.Aoi           Update          S21_NA#20154
 * 2018/06/25   Fujitsu         Mz.Takahashi    Update          S21_NA#23726
 * 2018/08/07   Fujitsu         H.Kumagai       Update          QC#23587
 * 2018/09/27   Fujitsu         K.Ishizuka      Update          S21_NA#28482
 * 2018/10/25   Fujitsu         T.Aoi           Update          S21_NA#28897
 * 2018/11/16   Fujitsu         K.Ishizuka      Update          S21_NA#27299
 * 2018/12/03   Fujitsu         R.Nakamura      Update          S21_NA#27299-2
 * 2018/12/17   Fujitsu         S.Kosaka        Update          QC#29521
 * 2018/12/18   Fujitsu         M.Ishii         Update          QC#29285
 * 2019/05/07   Fujitsu         R.Nakamura      Update          S21_NA#50015
 * 2019/07/11   Fujitsu         T.Noguchi       Update          S21_NA#51287
 * 2019/08/01   Fujitsu         M.Ohno          Update          S21_NA#52156
 * 2019/08/15   Fujitsu         K.Kato          Update          S21_NA#52620
 * 2020/01/07   Fujitsu         M.Ohno          Update          S21_NA#54996
 * 2023/02/07   Hitachi         S.Fujita        Update          QC#61010
 * 2023/10/13   Hitachi         D.Yoshida       Update          QC#61077
 *</pre>
 */
public class NWAL1500CommonLogicForScrnFields {

    /**
     * Set Screen Protect
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    public static void setProtect(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Set All Active Field
        setControlFields(scrnMsg);
        setAllActiveFields(handler, scrnMsg);

        // 2016/10/13 S21_NA#7700 Add Start
        // Set Protect By Editable
        if (!NWAL1500CommonLogic.chkOrdEntryScrEdtblFlg(scrnMsg)) {

            setProtectByEditable(handler, scrnMsg);
        // 2016/10/13 S21_NA#7700 Add End
        } else {
            // Set Protect By Status
            setProtectByStatus(handler, scrnMsg);

            // Set Protect By Authority
            setProtectByAuthority(handler, scrnMsg);

            // 2018/05/10 S21_NA#25251 Add Start
            setActivePriceFieldByAuth(scrnMsg);
            // 2018/05/10 S21_NA#25251 Add End
        }
        // Set Protect By Inherent Control
        setProtectByInherentControl(handler, scrnMsg);

        // S21_NA#3398 Add Start
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            setProtectForSetFromConfigIdPopup(scrnMsg.A.no(i), scrnMsg.B);
        }
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            setProtectForSetFromConfigIdPopup(scrnMsg.C.no(i), scrnMsg.D);
        }
        // S21_NA#3398 Add End
        // 2018/02/02 S21_NA#19808 Add Start
        if ("1".equals(scrnMsg.dsOrdPosnNum_AT.getValue()) //
                || "0".equals(scrnMsg.dsOrdPosnNum_AT.getValue()) //
                || !ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum_AT)) {
            scrnMsg.dsOrdPosnNum_AS.setInputProtected(true);
        } else  {
            scrnMsg.dsOrdPosnNum_AS.setInputProtected(false);
        }
        if ("1".equals(scrnMsg.dsOrdPosnNum_CT.getValue()) //
                || "0".equals(scrnMsg.dsOrdPosnNum_CT.getValue()) //
                || !ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum_CT)) {
            scrnMsg.dsOrdPosnNum_CS.setInputProtected(true);
        } else  {
            scrnMsg.dsOrdPosnNum_CS.setInputProtected(false);
        }
        // 2018/02/02 S21_NA#19808 Add End
    }

    /**
     * Set Control Fields
     * @param scrnMsg Screen Msg
     */
    private static void setControlFields(NWAL1500BMsg scrnMsg) {

        setControlFieldsForLink(scrnMsg);
        setControlFieldsForDigit(scrnMsg);
    }

    /**
     * Set Control Fields For Link
     * @param scrnMsg Screen Msg
     */
    private static void setControlFieldsForLink(NWAL1500BMsg scrnMsg) {

        // Header
        scrnMsg.dsOrdCatgDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.dsOrdTpDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);

        // Header Tab
        scrnMsg.billToCustAcctNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.billToCustAcctCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.billToCustCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.shipToCustAcctNm_LK.setValue(ZYPConstant.FLG_ON_Y);

        // 2016/03/25 S21_NA#4693 Mod Start
        // scrnMsg.dropShipFlg_LK.setValue(ZYPConstant.FLG_ON_Y);
        boolean isDsLinkEnable = ZYPConstant.FLG_ON_Y.equals(scrnMsg.dropShipAvalFlg.getValue());
        if (isDsLinkEnable) {
            scrnMsg.dropShipFlg_LK.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.dropShipFlg_LK.clear();
        }
        // 2016/03/25 S21_NA#4693 Mod End
        scrnMsg.shipToCustAcctCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.shipToCustCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.soldToCustAcctNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.sellToCustCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.soldToCustLocCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.slsRepTocCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.slsRepTocNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.psnNum_LK.setValue(ZYPConstant.FLG_ON_Y); // 2016/05/11 S21_NA#7861 Mod slsRepPsnCd_LK -> psnNum_LK
        scrnMsg.prcCatgNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        // QC#1692
        scrnMsg.ordLogTpCd_LK.setValue(ZYPConstant.FLG_ON_Y);

        // Addl Header Tab
        scrnMsg.frtCondDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.carrSvcLvlDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.pmtTermCashDiscDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.prcContrNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.prcContrNum_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.flPrcListDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.csmpContrNum_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.dlrRefNum_LK.setValue(ZYPConstant.FLG_ON_Y);

        // Line Config Tab (Config)
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1500_ABMsg configMsg = scrnMsg.A.no(i);
            configMsg.xxImageTxt_AC.setValue(ZYPConstant.FLG_ON_Y);

            // 2016/03/25 S21_NA#4693 Mod Start
            // configMsg.xxImageTxt_AD.setValue(ZYPConstant.FLG_ON_Y);
            if (isDsLinkEnable) {
                configMsg.xxImageTxt_AD.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                configMsg.xxImageTxt_AD.clear();
            }
            // 2016/03/25 S21_NA#4693 Mod End
            // Ship To
            configMsg.xxImageTxt_AS.setValue(ZYPConstant.FLG_ON_Y);
            configMsg.xxImageTxt_AA.setValue(ZYPConstant.FLG_ON_Y);
            configMsg.xxImageTxt_AN.setValue(ZYPConstant.FLG_ON_Y);
            // Bill To
            configMsg.xxImageTxt_AB.setValue(ZYPConstant.FLG_ON_Y);
            configMsg.xxImageTxt_AT.setValue(ZYPConstant.FLG_ON_Y);
            configMsg.xxImageTxt_AM.setValue(ZYPConstant.FLG_ON_Y);

            // 2016/08/26 S21_NA#9806 Add Start
            configMsg.xxImageTxt_LN.setValue(ZYPConstant.FLG_ON_Y);
            configMsg.xxImageTxt_LD.setValue(ZYPConstant.FLG_ON_Y);
            // 2016/08/26 S21_NA#9806 Add End
        }

        // Line Config Tab (Line)
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
            lineMsg.xxImageTxt_BI.setValue(ZYPConstant.FLG_ON_Y);
            lineMsg.xxImageTxt_BP.setValue(ZYPConstant.FLG_ON_Y);
            lineMsg.xxImageTxt_BW.setValue(ZYPConstant.FLG_ON_Y);
            lineMsg.xxImageTxt_BS.setValue(ZYPConstant.FLG_ON_Y);
            lineMsg.xxImageTxt_BF.setValue(ZYPConstant.FLG_ON_Y);
            // QC#1615
            lineMsg.xxImageTxt_BU.setValue(ZYPConstant.FLG_ON_Y);
        }

        // RMA Tab (Config)
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NWAL1500_CBMsg configMsg = scrnMsg.C.no(i);
            configMsg.xxImageTxt_CC.setValue(ZYPConstant.FLG_ON_Y);
            // 2016/03/25 S21_NA#4693 Mod Start
            // configMsg.xxImageTxt_CD.setValue(ZYPConstant.FLG_ON_Y);
            if (isDsLinkEnable) {
                configMsg.xxImageTxt_CD.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                configMsg.xxImageTxt_CD.clear();
            }
            // 2016/03/25 S21_NA#4693 Mod End
            // Ship To
            configMsg.xxImageTxt_CS.setValue(ZYPConstant.FLG_ON_Y);
            configMsg.xxImageTxt_CA.setValue(ZYPConstant.FLG_ON_Y);
            configMsg.xxImageTxt_CN.setValue(ZYPConstant.FLG_ON_Y);
            // Bill To
            configMsg.xxImageTxt_CB.setValue(ZYPConstant.FLG_ON_Y);
            configMsg.xxImageTxt_CT.setValue(ZYPConstant.FLG_ON_Y);
            configMsg.xxImageTxt_CM.setValue(ZYPConstant.FLG_ON_Y);

            // 2016/08/26 S21_NA#9806 Add Start
            configMsg.xxImageTxt_RN.setValue(ZYPConstant.FLG_ON_Y);
            configMsg.xxImageTxt_RD.setValue(ZYPConstant.FLG_ON_Y);
            // 2016/08/26 S21_NA#9806 Add End
        }

        // RMA Tab (Line)
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NWAL1500_DBMsg lineMsg = scrnMsg.D.no(i);
            lineMsg.xxImageTxt_DI.setValue(ZYPConstant.FLG_ON_Y);
            lineMsg.xxImageTxt_DP.setValue(ZYPConstant.FLG_ON_Y);
            lineMsg.xxImageTxt_DW.setValue(ZYPConstant.FLG_ON_Y);
            lineMsg.xxImageTxt_DF.setValue(ZYPConstant.FLG_ON_Y);
            // 2017/11/17 S21_NA#22252 Add Start
            lineMsg.xxImageTxt_DU.setValue(ZYPConstant.FLG_ON_Y);
            // 2017/11/17 S21_NA#22252 Add End
        }
    }

    /**
     * Set Control Fields For Digit
     * @param scrnMsg Screen Msg
     */
    private static void setControlFieldsForDigit(NWAL1500BMsg scrnMsg) {

        // Header
        scrnMsg.xxTotBaseAmt_LN.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotTaxAmt_LN.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotFrtAmt_LN.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotAmt_LN.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotBaseAmt_MT.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotTaxAmt_MT.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotFrtAmt_MT.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotAmt_MT.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotBaseAmt_RT.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotTaxAmt_RT.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotFrtAmt_RT.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotAmt_RT.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotBaseAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotTaxAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotFrtAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotInvApplyAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotInvPct.setAppFracDigit(0);

        // Header Tab
        scrnMsg.negoDealAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());

        // Addl Header Tab
        scrnMsg.prePmtAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.leaseTotPmtAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());

        // Line Config Tab (Config Line)
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1500_ABMsg lineMsg = scrnMsg.A.no(i);
            lineMsg.configTotDealNetAmt_LC.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.configTotDealChrgAmt_LC.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.configTotDealTaxAmt_LC.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.configSubTotDealAmt_LC.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        }

        // Line Config Tab (Line)
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
            lineMsg.entCpoDtlDealSlsAmt_LL.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.entDealNetUnitPrcAmt_LL.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.dealPrcListPrcAmt_LL.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.funcUnitFlPrcAmt_LL.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt()); // QC#22372 2018/01/10 Add
            lineMsg.lineDealSubTotAmt_LL.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt()); //QC#26085 2018/05/11 Add
            lineMsg.lineDealChrgAmt_LL.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.lineDealTaxAmt_LL.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.lineDealTotAmt_LL.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        }

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NWAL1500_CBMsg lineMsg = scrnMsg.C.no(i);
            lineMsg.configTotDealNetAmt_RC.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.configTotDealChrgAmt_RC.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.configTotDealTaxAmt_RC.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.configSubTotDealAmt_RC.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        }

        // RMA Tab (Line)
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NWAL1500_DBMsg lineMsg = scrnMsg.D.no(i);
            lineMsg.entCpoDtlDealSlsAmt_RL.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.entDealNetUnitPrcAmt_RL.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.dealPrcListPrcAmt_RL.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.funcUnitFlPrcAmt_RL.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt()); // QC#22372 2018/01/10 Add
            lineMsg.lineDealSubTotAmt_RL.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.lineDealChrgAmt_RL.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.lineDealTaxAmt_RL.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            lineMsg.lineDealTotAmt_RL.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        }
    }

    /**
     * Set Screen Protect By Inherent Control
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByInherentControl(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        setProtectByCpoSrcTp(handler, scrnMsg);
        setProtectByRtlSwhCd(handler, scrnMsg); // 2018/04/12 S21_NA#23704 Add
        setProtectBySetComponent(scrnMsg);
        setProtectByCrRebillCd(handler, scrnMsg);
        setProtectByPickConfiguration(scrnMsg);
        setProtectBySearchSucceeded(handler, scrnMsg);
        setProtectByPricingErrHandling(handler, scrnMsg);
        setProtectByPmtMeth(handler, scrnMsg);
        setProtectByOrdCatgBizCtx(handler, scrnMsg);
        setProtectByFrtCond(handler, scrnMsg);
        setProtectByBillTo(handler, scrnMsg);     // QC#17474 2017/02/21 Add
        setProtectByOrdLineCtxTp(handler, scrnMsg); // 2016/08/08 S21_NA#11592 Add
        // 2019/08/01 S21_NA#52156 Add Start
        setProtectByOrdLinePRApproved(handler, scrnMsg);
        // 2019/08/01 S21_NA#52156 Add End
        // 2020/01/07 S21_NA#54996 Add Start
        setProtectByFreightInfo(handler, scrnMsg);
//        setProtectByBaseCompFlg(handler, scrnMsg);// 2016/12/06 S21_NA#15898 Add // 2016/12/19 S21_NA#15898-2 Del
    }
    
    // 2018/04/12 S21_NA#23704 Add Start
    public static void setProtectByRtlSwhCd(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Mod Start 2019/05/15 QC#50015
//        if (scrnMsg.L.getValidCount() == 1 && REF_AUTH.equals(scrnMsg.L.no(0).xxFuncId.getValue())) {
        if (scrnMsg.L.getValidCount() == 1 
                && (REF_AUTH.equals(scrnMsg.L.no(0).xxFuncId.getValue()) || MOD_LOGISTICS.equals(scrnMsg.L.no(0).xxFuncId.getValue()))) {
            return;
        }
        // Mod End 2019/05/15 QC#50015

        NWAL1500_BBMsg lineMsg = null;
        NWAL1500_DBMsg rtrnMsg = null;
        String rtlSwhCd = null;
        String rtlSwhCdUsedLineConf = null;
        boolean doProtect = true;
        boolean hasCredit = NWAL1500CommonLogic.isOrderCredit(scrnMsg); // 2018/11/16 S21_NA#27299 Add
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            lineMsg = scrnMsg.B.no(i);
            final String lineStsNm = lineMsg.ordLineStsDescTxt_LL.getValue();
            if (LINE_STS_NM_SHIPPED_CLOSED.equals(lineStsNm) || //
                    LINE_STS_NM_INVOICED.equals(lineStsNm) || //
                    LINE_STS_NM_CANCELLED.equals(lineStsNm) || //
                    LINE_STS_NM_ON_LOAN.equals(lineStsNm) || //
                    LINE_STS_NM_CLOSED_LOAN_RETURN.equals(lineStsNm) || //
                    LINE_STS_NM_CLOSED_LOAN_SOLD.equals(lineStsNm)) {
                continue;
            }
            // 2018/11/16 S21_NA#27299 Add Start
            if (hasCredit) {
                break;
            }
            // 2018/11/16 S21_NA#27299 Add End
            rtlSwhCd = lineMsg.rtlSwhCd_LL.getValue();
            if (ZYPCommonFunc.hasValue(rtlSwhCd)) {
                doProtect = true;
                for (int j = 0; j < scrnMsg.rtlSwhCd_SW.length(); j++) {
                    rtlSwhCdUsedLineConf = scrnMsg.rtlSwhCd_SW.no(j).getValue();
                    if (!ZYPCommonFunc.hasValue(rtlSwhCdUsedLineConf)) {
                        break;
                    }
                    if (rtlSwhCdUsedLineConf.equals(rtlSwhCd)) {
                        doProtect = false;
                        break;
                    }
                }
                lineMsg.funcUnitFlPrcAmt_LL.setInputProtected(doProtect);
            }
        }

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            rtrnMsg = scrnMsg.D.no(i);
            final String rmaLineStsNm = rtrnMsg.rtrnLineStsDescTxt_RL.getValue();
            if (LINE_STS_NM_CANCELLED.equals(rmaLineStsNm) || LINE_STS_NM_CLOSED.equals(rmaLineStsNm)) {
                continue;
            }
            // 2018/11/16 S21_NA#27299 Add Start
            if (hasCredit) {
                break;
            }
            // 2018/11/16 S21_NA#27299 Add End
            rtlSwhCd = rtrnMsg.rtlSwhCd_RL.getValue();
            if (ZYPCommonFunc.hasValue(rtlSwhCd)) {
                doProtect = true;
                for (int j = 0; j < scrnMsg.rtlSwhCd_SW.length(); j++) {
                    rtlSwhCdUsedLineConf = scrnMsg.rtlSwhCd_SW.no(j).getValue();
                    if (!ZYPCommonFunc.hasValue(rtlSwhCdUsedLineConf)) {
                        break;
                    }
                    if (rtlSwhCdUsedLineConf.equals(rtlSwhCd)) {
                        doProtect = false;
                        break;
                    }
                }
                rtrnMsg.funcUnitFlPrcAmt_RL.setInputProtected(doProtect);
            }
        }
    }
    // 2018/04/12 S21_NA#23704 Add End

    // START 2016/08/08 S21_NA#11592 Add
    /**
     * Set Screen Protect By Order Line Ctx Tp(ORD_LINE_CTX_TP_CD = FORCE_DUMMY_WH)
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByOrdLineCtxTp(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {
        NWAL1500_BBMsg lineMsg = null;
        String dsOrdLineCatgCd = null;

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            lineMsg = scrnMsg.B.no(i);
            dsOrdLineCatgCd = lineMsg.dsOrdLineCatgCd_LL.getValue();
            if (ZYPCommonFunc.hasValue(dsOrdLineCatgCd)) {
                for (int j = 0; j < scrnMsg.P.getValidCount(); j++) {
                    if (scrnMsg.P.no(j).dsOrdLineCatgCd_P.getValue().equals(dsOrdLineCatgCd)) {
                        lineMsg.rtlWhCd_LL.setInputProtected(true);
                        lineMsg.rtlWhNm_LL.setInputProtected(true);
                        lineMsg.rtlSwhCd_LL.setInputProtected(true);
                        lineMsg.rtlSwhNm_LL.setInputProtected(true);
                        lineMsg.xxImageTxt_BW.setInputProtected(true);
                        lineMsg.xxImageTxt_BW.clear();
                        lineMsg.xxImageTxt_BU.setInputProtected(true);
                        lineMsg.xxImageTxt_BU.clear();
                        break;
                    }
                }
            }
        }
    }
    // END   2016/08/08 S21_NA#11592 Add

    /**
     * Set Screen Protect By CPO Source Type
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByCpoSrcTp(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        if (CPO_SRC_TP.CHANGE_ORDER_MODIFICATION.equals(scrnMsg.cpoSrcTpCd.getValue())) {
            scrnMsg.negoDealAmt.setInputProtected(true);
            handler.setButtonEnabled(BTN_ORDERAMTCALC_EVENT_NM, false);
            handler.setButtonEnabled(BTN_PRC_CHG_EVENT_NM, false);

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
                lineMsg.entCpoDtlDealSlsAmt_LL.setInputProtected(true);
                // 2018/12/18 QC#29285 Add Start
                lineMsg.custMdseCd_LL.setInputProtected(true);
                // 2018/12/18 QC#29285 Add End
            }

            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(i);
                rmaLineMsg.entCpoDtlDealSlsAmt_RL.setInputProtected(true);
                // 2018/12/18 QC#29285 Add Start
                rmaLineMsg.custMdseCd_RL.setInputProtected(true);
                // 2018/12/18 QC#29285 Add End
            }
        // 2016/12/07 S21_NA#15934 Add Start
        } else if (CPO_SRC_TP.LOAN_WORKBENCH.equals(scrnMsg.cpoSrcTpCd.getValue())) {

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
                if (DS_ORD_LINE_CATG.LOAN_BILL_ONLY.equals(lineMsg.dsOrdLineCatgCd_LL.getValue())) {
                    lineMsg.mdseCd_LL.setInputProtected(true);
                    lineMsg.ordCustUomQty_LL.setInputProtected(true);
                    lineMsg.ordLineSrcCd_LL.setInputProtected(true);
                    lineMsg.dsOrdLineCatgCd_LL.setInputProtected(true);
                    lineMsg.custUomCd_LL.setInputProtected(true);
                    lineMsg.serNum_LL.setInputProtected(true);
                    lineMsg.sbstMdseCd_LL.setInputProtected(true);
                }
            }

            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(i);
                if (DS_ORD_LINE_CATG.LOAN_RETURNS.equals(rmaLineMsg.dsOrdLineCatgCd_RL.getValue())) {
                    rmaLineMsg.mdseCd_RL.setInputProtected(true);
                    rmaLineMsg.ordCustUomQty_RL.setInputProtected(true);
                    rmaLineMsg.dsOrdLineCatgCd_RL.setInputProtected(true);
                    rmaLineMsg.custUomCd_RL.setInputProtected(true);
                    rmaLineMsg.serNum_RL.setInputProtected(true);
                }
            }
        // 2016/12/07 S21_NA#15934 Add End
        }
    }

    /**
     * Set Screen Protect By Set Component
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectBySetComponent(NWAL1500BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
            if (ZYPCommonFunc.hasValue(lineMsg.dsCpoLineSubNum_LL)) {
                lineMsg.xxChkBox_LL.setInputProtected(true);
                lineMsg.xxChkBox_LL.clear();
                lineMsg.mdseCd_LL.setInputProtected(true);
                lineMsg.xxImageTxt_BI.clear();
                lineMsg.ordCustUomQty_LL.setInputProtected(true);
                lineMsg.custUomCd_LL.setInputProtected(true);
                lineMsg.entCpoDtlDealSlsAmt_LL.setInputProtected(true);
                lineMsg.prcCatgNm_LL.setInputProtected(true);
                lineMsg.xxImageTxt_BP.clear();
                lineMsg.prcListEquipConfigNum_LL.setInputProtected(true);
                lineMsg.dsOrdLineCatgCd_LL.setInputProtected(true);
                lineMsg.ordLineSrcCd_LL.setInputProtected(true);
                lineMsg.rtlWhNm_LL.setInputProtected(true);
                lineMsg.rtlSwhNm_LL.setInputProtected(true);
                lineMsg.xxImageTxt_BW.clear();
                // QC#1615
                lineMsg.xxImageTxt_BU.clear();
//                lineMsg.serNum_LL.setInputProtected(true); // 2016/12/07 S21_NA#15934 Del
                lineMsg.supdLockFlg_LL.setInputProtected(true);
                lineMsg.sbstMdseCd_LL.setInputProtected(true);
                // 2018/12/18 QC#29285 Add Start
                lineMsg.custMdseCd_LL.setInputProtected(true);
                // 2018/12/18 QC#29285 Add End
                lineMsg.xxImageTxt_BS.clear();
                lineMsg.vndInvtyLocCd_LL.setInputProtected(true);
                lineMsg.flPrcListNm_LL.setInputProtected(true);
                lineMsg.funcUnitFlPrcAmt_LL.setInputProtected(true); // QC#22372 2018/01/10 Add
                lineMsg.xxImageTxt_BF.clear();
                lineMsg.dplyLineRefNum_LL.setInputProtected(true);
                lineMsg.prcBaseDt_LL.setInputProtected(true);
                lineMsg.rddDt_LL.setInputProtected(true);
            } else {
                // 2016/12/07 S21_NA#15934 Add Start
                if (CPO_DTL_LINE_SUB_NUM_SET_PRNT.equals(lineMsg.cpoDtlLineSubNum_LL.getValue())) {
                    lineMsg.serNum_LL.setInputProtected(true);
                }
                // 2016/12/07 S21_NA#15934 Add End
            }
        }

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(i);
            if (ZYPCommonFunc.hasValue(rmaLineMsg.dsCpoLineSubNum_RL)) {
                rmaLineMsg.mdseCd_RL.setInputProtected(true);
                rmaLineMsg.xxImageTxt_DI.clear();
                rmaLineMsg.ordCustUomQty_RL.setInputProtected(true);
                rmaLineMsg.custUomCd_RL.setInputProtected(true);
                rmaLineMsg.entCpoDtlDealSlsAmt_RL.setInputProtected(true);
                rmaLineMsg.prcCatgNm_RL.setInputProtected(true);
                rmaLineMsg.xxImageTxt_DP.clear();
                rmaLineMsg.dsOrdLineCatgCd_RL.setInputProtected(true);
                rmaLineMsg.rtlWhNm_RL.setInputProtected(true);
                rmaLineMsg.xxImageTxt_DW.clear();
                // 2017/11/17 S21_NA#22252 Add Start
                rmaLineMsg.rtlSwhNm_RL.setInputProtected(true);
                rmaLineMsg.xxImageTxt_DU.clear();
                // 2017/11/17 S21_NA#22252 Add End
                // rmaLineMsg.serNum_RL.setInputProtected(true); // S21_NA#7145 Del
                rmaLineMsg.flPrcListNm_RL.setInputProtected(true);
                rmaLineMsg.funcUnitFlPrcAmt_RL.setInputProtected(true); // QC#22372 2018/01/10 Add
                rmaLineMsg.xxImageTxt_DF.clear();
                rmaLineMsg.dplyLineRefNum_RL.setInputProtected(true);
                // 2018/12/18 QC#29285 Add Start
                rmaLineMsg.custMdseCd_RL.setInputProtected(true);
                // 2018/12/18 QC#29285 Add End
                rmaLineMsg.prcBaseDt_RL.setInputProtected(true);
                rmaLineMsg.rqstPickUpDt_RL.setInputProtected(true);
                rmaLineMsg.rtrnRsnCd_RL.setInputProtected(true);
                rmaLineMsg.hddRmvCd_RL.setInputProtected(true);
            } else {
                // 2016/12/07 S21_NA#15934 Add Start
                if (CPO_DTL_LINE_SUB_NUM_SET_PRNT.equals(rmaLineMsg.cpoDtlLineSubNum_RL.getValue())) {
                    rmaLineMsg.serNum_RL.setInputProtected(true);
                }
                // 2016/12/07 S21_NA#15934 Add End
            }
        }
    }

    /**
     * Set Screen Protect By Credit Rebill Code
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByCrRebillCd(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // 2017/10/13 S21_NA#21267 Mod Start
//        boolean hasCredit = false;
//        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
//            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
//            if (CR_REBIL.CREDIT.equals(lineMsg.crRebilCd_LL.getValue())) {
//                hasCredit = true;
//                break;
//            }
//        }
        // 2018/11/20 S21_NA#27299 Add Start
        final String hdrStsNm = scrnMsg.ordHdrStsDescTxt.getValue();
        if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm)) {
            return;
        }
        // 2018/11/20 S21_NA#27299 Add End
        boolean hasCredit = NWAL1500CommonLogic.isOrderCredit(scrnMsg);
        // 2017/10/13 S21_NA#21267 Mod End

        if (hasCredit) {
            setAllInactiveFields(handler, scrnMsg);
            // 2018/11/16 S21_NA#27299 Add Start
            boolean isDsLinkEnable = ZYPConstant.FLG_ON_Y.equals(scrnMsg.dropShipAvalFlg.getValue());
            boolean existLine = false;
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                NWAL1500_DBMsg lineMsg = scrnMsg.D.no(i);
                if(ZYPCommonFunc.hasValue(lineMsg.rtrnLineStsDescTxt_RL)){
                    existLine = true;
                }
                break;
            }
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                NWAL1500_CBMsg configMsg = scrnMsg.C.no(i);
                if (!existLine) {
                    break;
                }

                // Add Start 2018/12/03 QC#27299-2
                boolean configLinkFlg = true;
                boolean configProtFlg = false;
                for (int j = 0; j < scrnMsg.D.getValidCount(); j++) {

                    NWAL1500_DBMsg lineMsg = scrnMsg.D.no(j);
                    if (configMsg.dsOrdPosnNum_RC.getValue().equals(lineMsg.dsOrdPosnNum_RL.getValue())) {
                        final String rmaLineStsNm = lineMsg.rtrnLineStsDescTxt_RL.getValue();
                        String rwsOpenFlg = lineMsg.rwsOpenFlg_RL.getValue();
                        boolean lineLinkFlg = true;
                        boolean lineProtFlg = false;

                        if ((LINE_STS_NM_PENDING_RETURN.equals(rmaLineStsNm) && ZYPConstant.FLG_ON_Y.equals(rwsOpenFlg)) //
                                || LINE_STS_NM_PARTIAL_RECEIVED.equals(rmaLineStsNm) //
                                || LINE_STS_NM_PENDING_INSPECTION.equals(rmaLineStsNm) //
                                || LINE_STS_NM_BILLING_HOLD.equals(rmaLineStsNm) //
                                || LINE_STS_NM_PENDING_INVOICE.equals(rmaLineStsNm)) {
                            lineLinkFlg = false;
                            lineProtFlg = true;
                            configLinkFlg = false;
                            configProtFlg = true;
                        } else {
                            lineLinkFlg = true;
                            lineProtFlg = false;
                        }

                        lineMsg.rtrnRsnCd_RL.setInputProtected(lineProtFlg);
                        lineMsg.rtlWhNm_RL.setInputProtected(lineProtFlg);
                        if (lineLinkFlg) {
                            lineMsg.xxImageTxt_DW.setValue(ZYPConstant.FLG_ON_Y);
                        } else {
                            lineMsg.xxImageTxt_DW.clear();
                        }
                        lineMsg.xxImageTxt_DW.setInputProtected(lineProtFlg);
                        lineMsg.rtlSwhNm_RL.setInputProtected(lineProtFlg);
                        if (lineLinkFlg) {
                            lineMsg.xxImageTxt_DU.setValue(ZYPConstant.FLG_ON_Y);
                        } else {
                            lineMsg.xxImageTxt_DU.clear();
                        }
                        lineMsg.xxImageTxt_DU.setInputProtected(lineProtFlg);
                        lineMsg.rqstPickUpDt_RL.setInputProtected(lineProtFlg);
                        lineMsg.dplyLineRefNum_RL.setInputProtected(lineProtFlg);
                        lineMsg.hddRmvCd_RL.setInputProtected(lineProtFlg);
                    }
                }
                // Add End 2018/12/03 QC#27299-2

                // Mod Start 2018/12/03 QC#27299-2
//                configMsg.shipToCustLocAddr_RC.setInputProtected(false);
//                configMsg.shipToCustAcctCd_RC.setInputProtected(false);
//                configMsg.xxImageTxt_CA.setValue(ZYPConstant.FLG_ON_Y);
//                configMsg.xxImageTxt_CA.setInputProtected(false);
//                configMsg.shipToCustCd_RC.setInputProtected(false);
//                configMsg.xxImageTxt_CS.setValue(ZYPConstant.FLG_ON_Y);
//                configMsg.xxImageTxt_CS.setInputProtected(false);
//                configMsg.shipToCustAcctNm_RC.setInputProtected(false);
//                configMsg.xxImageTxt_CN.setValue(ZYPConstant.FLG_ON_Y);
//                configMsg.xxImageTxt_CN.setInputProtected(false);
//                if (isDsLinkEnable) {
//                    configMsg.xxImageTxt_CD.setValue(ZYPConstant.FLG_ON_Y);
//                }
                configMsg.shipToCustLocAddr_RC.setInputProtected(configProtFlg);
                configMsg.shipToCustAcctCd_RC.setInputProtected(configProtFlg);
                if (configLinkFlg) {
                    configMsg.xxImageTxt_CA.setValue(ZYPConstant.FLG_ON_Y);
                } else {
                    configMsg.xxImageTxt_CA.clear();
                }
                configMsg.xxImageTxt_CA.setInputProtected(configProtFlg);
                configMsg.shipToCustCd_RC.setInputProtected(configProtFlg);
                if (configLinkFlg) {
                    configMsg.xxImageTxt_CS.setValue(ZYPConstant.FLG_ON_Y);
                } else {
                    configMsg.xxImageTxt_CS.clear();
                }
                configMsg.xxImageTxt_CS.setInputProtected(configProtFlg);
                configMsg.shipToCustAcctNm_RC.setInputProtected(configProtFlg);
                if (configLinkFlg) {
                    configMsg.xxImageTxt_CN.setValue(ZYPConstant.FLG_ON_Y);
                } else {
                    configMsg.xxImageTxt_CN.clear();
                }
                configMsg.xxImageTxt_CN.setInputProtected(configProtFlg);
                if (isDsLinkEnable) {
                    if (configLinkFlg) {
                        configMsg.xxImageTxt_CD.setValue(ZYPConstant.FLG_ON_Y);
                    } else {
                        configMsg.xxImageTxt_CD.clear();
                    }
                }
                // Mod End 2018/12/03 QC#27299-2
                // 2018/12/17 QC#29521 Add Start
                configMsg.xxChkBox_RC.setInputProtected(configProtFlg);
                // 2018/12/17 QC#29521 Add End
            }
            // Del Start 2018/12/03 QC#27299-2
//            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
//                NWAL1500_DBMsg lineMsg = scrnMsg.D.no(i);
//                if (!existLine) {
//                    break;
//                }
//                lineMsg.rtrnRsnCd_RL.setInputProtected(false);
//                lineMsg.rtlWhNm_RL.setInputProtected(false);
//                lineMsg.xxImageTxt_DW.setValue(ZYPConstant.FLG_ON_Y);
//                lineMsg.xxImageTxt_DW.setInputProtected(false);
//                lineMsg.rtlSwhNm_RL.setInputProtected(false);
//                lineMsg.xxImageTxt_DU.setValue(ZYPConstant.FLG_ON_Y);
//                lineMsg.xxImageTxt_DU.setInputProtected(false);
//                lineMsg.rqstPickUpDt_RL.setInputProtected(false);
//                lineMsg.dplyLineRefNum_RL.setInputProtected(false);
//                lineMsg.hddRmvCd_RL.setInputProtected(false);
//            }
            // Del End 2018/12/03 QC#27299-2
            // 2018/11/16 S21_NA#27299 Add End
            handler.setButtonEnabled(BTN_ORDER_CANCEL_EVENT_NM, true);
            return;
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
            if (CR_REBIL.REBILL.equals(lineMsg.crRebilCd_LL.getValue())) {
//                lineMsg.xxChkBox_LL.setInputProtected(true);
                lineMsg.xxChkBox_LL.setInputProtected(false);
//                lineMsg.xxChkBox_LL.clear(); 2017/11/08 S21_NA#22351 Del
                lineMsg.mdseCd_LL.setInputProtected(true);
                lineMsg.xxImageTxt_BI.clear();
                lineMsg.ordCustUomQty_LL.setInputProtected(true);
                lineMsg.custUomCd_LL.setInputProtected(true);
                // 2018/04/24 S21_NA#25534 Del Start
//                lineMsg.prcCatgNm_LL.setInputProtected(true);
//                lineMsg.xxImageTxt_BP.clear();
                // 2018/04/24 S21_NA#25534 Del End
                lineMsg.prcListEquipConfigNum_LL.setInputProtected(true);
                lineMsg.dsOrdLineCatgCd_LL.setInputProtected(true);
                lineMsg.ordLineSrcCd_LL.setInputProtected(true);
                lineMsg.rtlWhNm_LL.setInputProtected(true);
                lineMsg.rtlSwhNm_LL.setInputProtected(true);
                lineMsg.xxImageTxt_BW.clear();
                // QC#1615
                lineMsg.xxImageTxt_BU.clear();
                lineMsg.serNum_LL.setInputProtected(true);
                lineMsg.supdLockFlg_LL.setInputProtected(true);
                lineMsg.sbstMdseCd_LL.setInputProtected(true);
                lineMsg.xxImageTxt_BS.clear();
                lineMsg.vndInvtyLocCd_LL.setInputProtected(true);
                // 2018/04/24 S21_NA#25534 Del Start
//                lineMsg.flPrcListNm_LL.setInputProtected(true);
                // 2018/04/24 S21_NA#25534 Del End
                lineMsg.funcUnitFlPrcAmt_LL.setInputProtected(true); // QC#22372 2018/01/10 Add
                // 2018/04/24 S21_NA#25534 Del Start
//                lineMsg.xxImageTxt_BF.clear();
                // 2018/04/24 S21_NA#25534 Del End
                lineMsg.dplyLineRefNum_LL.setInputProtected(true);
                // 2018/04/24 S21_NA#25534 Del Start
//                lineMsg.prcBaseDt_LL.setInputProtected(true);
                // 2018/04/24 S21_NA#25534 Del End
                lineMsg.rddDt_LL.setInputProtected(true);
            }
        }
    }

    /**
     * Set Screen Protect By PickConfiguration ID
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByPickConfiguration(NWAL1500BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1500_ABMsg configMsg = scrnMsg.A.no(i);
            String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
            BigDecimal pickSvcConfigMstrPk = configMsg.pickSvcConfigMstrPk_LC.getValue();

            for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
                NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
                // 2018/09/27 S21_NA#28482 Mod Start
                // if (dsOrdPosnNum.equals(lineMsg.dsOrdPosnNum_LL.getValue()) && ZYPCommonFunc.hasValue(pickSvcConfigMstrPk)) {
                if (dsOrdPosnNum.equals(lineMsg.dsOrdPosnNum_LL.getValue()) && ZYPCommonFunc.hasValue(pickSvcConfigMstrPk) //
                        && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineMsg.xxYesNoCd_LL.getValue())) {
                    // 2018/09/27 S21_NA#28482 Mod End
                    lineMsg.rtlWhNm_LL.setInputProtected(true);
                    lineMsg.rtlSwhNm_LL.setInputProtected(true);
                    lineMsg.xxImageTxt_BW.clear();
                    // QC#1615
                    lineMsg.xxImageTxt_BU.clear();
                }
            }
        }
    }

    /**
     * Set Screen Protect By Search Succeeded
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectBySearchSucceeded(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.xxSrchNum)) {
            scrnMsg.cpoOrdNum.setInputProtected(true);
            handler.setButtonEnabled(BTN_ORDER_SRCH_EVENT_NM, false);
        }
    }

    /**
     * Set Screen Protect By Pricing Error Handling
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByPricingErrHandling(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
            if (ZYPCommonFunc.hasValue(lineMsg.xxErrFlg_LL)) {
                lineMsg.entCpoDtlDealSlsAmt_LL.setInputProtected(true);
                // 2018/12/18 QC#29285 Add Start
                lineMsg.custMdseCd_LL.setInputProtected(true);
                // 2018/12/18 QC#29285 Add End
            }
        }

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(i);
            if (ZYPCommonFunc.hasValue(rmaLineMsg.xxErrFlg_RL)) {
                rmaLineMsg.entCpoDtlDealSlsAmt_RL.setInputProtected(true);
                // 2018/12/18 QC#29285 Add Start
                rmaLineMsg.custMdseCd_RL.setInputProtected(true);
                // 2018/12/18 QC#29285 Add End
            }
        }
    }

    /**
     * Set Screen Protect By Payment Method
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    public static void setProtectByPmtMeth(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        if (!scrnMsg.pmtTermCashDiscDescTxt.isInputProtected()) {
            if (DS_PMT_METH.CREDIT_CARD.equals(scrnMsg.dsPmtMethCd.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.pmtCcFlg.getValue())) {
                handler.setButtonEnabled(BTN_CREDITCARD_EVENT_NM, true);
                scrnMsg.prePmtChkNum.setInputProtected(true);
                scrnMsg.prePmtTpCd.setInputProtected(true);
                scrnMsg.prePmtAmt.setInputProtected(true);
                scrnMsg.prePmtTpCd.clear();
            } else if (DS_PMT_METH.CHECK.equals(scrnMsg.dsPmtMethCd.getValue()) || DS_PMT_METH.INVOICE.equals(scrnMsg.dsPmtMethCd.getValue())) {
                handler.setButtonEnabled(BTN_CREDITCARD_EVENT_NM, false);
                scrnMsg.prePmtChkNum.setInputProtected(false);
                scrnMsg.prePmtTpCd.setInputProtected(false);
                scrnMsg.prePmtAmt.setInputProtected(false);
            } else {
                handler.setButtonEnabled(BTN_CREDITCARD_EVENT_NM, false);
                scrnMsg.prePmtChkNum.setInputProtected(true);
                scrnMsg.prePmtTpCd.setInputProtected(true);
                scrnMsg.prePmtAmt.setInputProtected(true);
            }
        }
        NWAL1500CommonLogic.controlPrePmtField(scrnMsg);

        final String hdrStsNm = scrnMsg.ordHdrStsDescTxt.getValue();
        if (!ZYPCommonFunc.hasValue(hdrStsNm)) {
            handler.setButtonEnabled(BTN_CREDITCARD_EVENT_NM, false);
        }
    }

    /**
     * Set Screen Protect By Status
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByStatus(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // set Header Protect
        setProtectByHdrSts(handler, scrnMsg);

        // set Line Detail or RMA Detail Protect
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            setProtectByConfStsForLineTab(handler, scrnMsg);
            setProtectByLineStsForLineTab(handler, scrnMsg);
        } else if (TAB_RMA.equals(dplyTab)) {
            setProtectByConfStsForRmaTab(handler, scrnMsg);
            setProtectByLineStsForRmaTab(handler, scrnMsg);
        }
    }

    /**
     * Set Screen Protect By Authority
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByAuthority(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
            String funcId = scrnMsg.L.no(i).xxFuncId.getValue();

            // Only Reference Authority
            if (scrnMsg.L.getValidCount() == 1 && REF_AUTH.equals(funcId)) {
                 NWAL1500CommonLogic.inactiveRegistrationButton(handler);
                 setProtectByEditable(handler, scrnMsg); // 2016/10/19 S21_NA#14728 Add

            }

            // Modify Authority (Workflow Items)
            if (MOD_WF_ITEMS_AUTH.equals(funcId)) {
                setProtectByWfAuth(handler, scrnMsg);
            }

            // Modify Authority (Price Items after Book)
            if (MOD_PRICE_AUTH.equals(funcId)) {
                setProtectByPriceAuth(handler, scrnMsg);
            }

            // Modify Authority (Qty Items after Book)
            if (MOD_QTY_AUTH.equals(funcId)) {
                setProtectByQtyAuth(handler, scrnMsg);
            }

            // Add Start 2019/05/07 QC#50015
            // Modify Authority (Logistics)
            if (scrnMsg.L.getValidCount() == 1 && MOD_LOGISTICS.equals(funcId)) {
                setProtectByLogistics(handler, scrnMsg);
                NWAL1500CommonLogic.activeRegistrationButton(handler, scrnMsg);
            }
            // Add End 2019/05/07 QC#50015
        }
    }

    /**
     * Set Screen Protect By Modify Authority (Workflow Items)
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByWfAuth(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        final String hdrStsNm = scrnMsg.ordHdrStsDescTxt.getValue();

        if (HEADER_STS_NM_VALIDATION_HOLD.equals(hdrStsNm) || HEADER_STS_NM_SUPPLY_ABUSE_HOLD.equals(hdrStsNm) || HEADER_STS_NM_PROFIITABILITY_HOLD.equals(hdrStsNm) || HEADER_STS_NM_CREDIT_HOLD.equals(hdrStsNm)) {
            setActiveFieldByWfAuth(handler, scrnMsg);
        }
    }

    /**
     * Set Screen Protect By Modify Authority (Price Items after Book)
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByPriceAuth(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        final String hdrStsNm = scrnMsg.ordHdrStsDescTxt.getValue();

        if (HEADER_STS_NM_BOOKED.equals(hdrStsNm)) {
            setActiveFieldByPriceAuth(handler, scrnMsg);
        }
    }

    /**
     * Set Screen Protect By Modify Authority (Qty Items after Book)
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByQtyAuth(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        final String hdrStsNm = scrnMsg.ordHdrStsDescTxt.getValue();

        if (HEADER_STS_NM_BOOKED.equals(hdrStsNm)) {
            setActiveFieldByQtyAuth(handler, scrnMsg);
        }
    }

    /**
     * Set Active Field By Modify Authority (Workflow Items)
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setActiveFieldByWfAuth(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Header Tab
        scrnMsg.negoDealAmt.setInputProtected(false);
        scrnMsg.prcCatgNm.setInputProtected(false);
        scrnMsg.prcCatgNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        // QC#1692
        scrnMsg.ordLogTpCd_LK.setValue(ZYPConstant.FLG_ON_Y);

        // Addl Header Tab
        scrnMsg.frtCondDescTxt.setInputProtected(false);
        scrnMsg.frtCondDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.carrSvcLvlDescTxt.setInputProtected(false);
        scrnMsg.carrSvcLvlDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.spclHdlgTpCd.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd.setInputProtected(false);
        scrnMsg.prcContrNum.setInputProtected(false);
        scrnMsg.prcContrNm.setInputProtected(false);
        scrnMsg.flPrcListNm.setInputProtected(false);
        scrnMsg.flPrcListDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.csmpContrNum.setInputProtected(false);
        scrnMsg.csmpContrNum_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.dlrRefNum.setInputProtected(false);
        scrnMsg.dlrRefNum_LK.setValue(ZYPConstant.FLG_ON_Y);

        // RMA Tab (Common Button)
        handler.setButtonEnabled(BTN_MASSUPDATE_EVENT_NM, true);

        // Line Config Tab (Line)
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
            final String lineStsNm = lineMsg.ordLineStsDescTxt_LL.getValue();
            // 2016/07/04 S21_NA#10723 Mod Start
            // 2016/05/11 S21_NA#7262 Mod Start
//            if (!LINE_STS_NM_SHIPPED_CLOSED.equals(lineStsNm) && !LINE_STS_NM_INVOICED.equals(lineStsNm) && !LINE_STS_NM_CANCELLED.equals(lineStsNm) && !LINE_STS_NM_ON_LOAN.equals(lineStsNm)) {
            if (!LINE_STS_NM_SHIPPED_CLOSED.equals(lineStsNm)
                    && !LINE_STS_NM_CLOSED_LOAN_RETURN.equals(lineStsNm)
                    && !LINE_STS_NM_CLOSED_LOAN_SOLD.equals(lineStsNm)
                    && !LINE_STS_NM_INVOICED.equals(lineStsNm)
                    && !LINE_STS_NM_CANCELLED.equals(lineStsNm)
                    && !LINE_STS_NM_ON_LOAN.equals(lineStsNm)) {
            // 2016/05/11 S21_NA#7262 Mod End
            // 2016/07/04 S21_NA#10723 Mod End
                lineMsg.ordCustUomQty_LL.setInputProtected(false);
                lineMsg.custUomCd_LL.setInputProtected(false);
                lineMsg.entCpoDtlDealSlsAmt_LL.setInputProtected(false);
                // 2018/12/18 QC#29285 Add Start
                lineMsg.custMdseCd_LL.setInputProtected(false);
                // 2018/12/18 QC#29285 Add End
                lineMsg.prcCatgNm_LL.setInputProtected(false);
                lineMsg.xxImageTxt_BP.setValue(ZYPConstant.FLG_ON_Y);
                lineMsg.prcListEquipConfigNum_LL.setInputProtected(false);
                lineMsg.dsOrdLineCatgCd_LL.setInputProtected(false);
                lineMsg.flPrcListNm_LL.setInputProtected(false);
                lineMsg.funcUnitFlPrcAmt_LL.setInputProtected(false); // QC#22372 2018/01/10 Add
                lineMsg.xxImageTxt_BF.setValue(ZYPConstant.FLG_ON_Y);
                lineMsg.prcBaseDt_LL.setInputProtected(false);
            }
        }

        // RMA Tab (Line)
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(i);
            final String rmaLineStsNm = rmaLineMsg.rtrnLineStsDescTxt_RL.getValue();
            // 2016/09/06 S21_NA#8775 MOD START
            if (!LINE_STS_NM_CANCELLED.equals(rmaLineStsNm) && !LINE_STS_NM_CLOSED.equals(rmaLineStsNm)) {
            // 2016/09/06 S21_NA#8775 MOD END
                rmaLineMsg.ordCustUomQty_RL.setInputProtected(false);
                rmaLineMsg.custUomCd_RL.setInputProtected(false);
                rmaLineMsg.entCpoDtlDealSlsAmt_RL.setInputProtected(false);
                // 2018/12/18 QC#29285 Add Start
                rmaLineMsg.custMdseCd_RL.setInputProtected(false);
                // 2018/12/18 QC#29285 Add End
                rmaLineMsg.prcCatgNm_RL.setInputProtected(false);
                rmaLineMsg.xxImageTxt_DP.setValue(ZYPConstant.FLG_ON_Y);
                rmaLineMsg.dsOrdLineCatgCd_RL.setInputProtected(false);
                rmaLineMsg.flPrcListNm_RL.setInputProtected(false);
                rmaLineMsg.funcUnitFlPrcAmt_RL.setInputProtected(false); // QC#22372 2018/01/10 Add
                rmaLineMsg.xxImageTxt_DF.setValue(ZYPConstant.FLG_ON_Y);
                rmaLineMsg.prcBaseDt_RL.setInputProtected(false);
                rmaLineMsg.rtrnRsnCd_RL.setInputProtected(false);
            }
        }
    }

    /**
     * Set Active Field By Modify Authority (Price Items after Book)
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setActiveFieldByPriceAuth(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Header Tab
        scrnMsg.negoDealAmt.setInputProtected(false);
        scrnMsg.prcCatgNm.setInputProtected(false);
        scrnMsg.prcCatgNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        // QC#1692
        scrnMsg.ordLogTpCd_LK.setValue(ZYPConstant.FLG_ON_Y);

        // Addl Header Tab
        scrnMsg.prcContrNum.setInputProtected(false);
        scrnMsg.prcContrNm.setInputProtected(false);
        scrnMsg.flPrcListNm.setInputProtected(false);
        scrnMsg.flPrcListDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.csmpContrNum.setInputProtected(false);
        scrnMsg.csmpContrNum_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.dlrRefNum.setInputProtected(false);
        scrnMsg.dlrRefNum_LK.setValue(ZYPConstant.FLG_ON_Y);

        // Line Config Tab (Line)
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
            final String lineStsNm = lineMsg.ordLineStsDescTxt_LL.getValue();

            // 2016/07/04 S21_NA#10723 Mod Start
            // 2016/05/11 S21_NA#7262 Mod Start
//            if (!LINE_STS_NM_SHIPPED_CLOSED.equals(lineStsNm) && !LINE_STS_NM_INVOICED.equals(lineStsNm) && !LINE_STS_NM_CANCELLED.equals(lineStsNm) && !LINE_STS_NM_ON_LOAN.equals(lineStsNm)) {
            if (!LINE_STS_NM_SHIPPED_CLOSED.equals(lineStsNm)
                    && !LINE_STS_NM_CLOSED_LOAN_RETURN.equals(lineStsNm)
                    && !LINE_STS_NM_CLOSED_LOAN_SOLD.equals(lineStsNm)
                    && !LINE_STS_NM_INVOICED.equals(lineStsNm)
                    && !LINE_STS_NM_CANCELLED.equals(lineStsNm)
                    && !LINE_STS_NM_ON_LOAN.equals(lineStsNm)) {
            // 2016/05/11 S21_NA#7262 Mod End
            // 2016/07/04 S21_NA#10723 Mod End
                lineMsg.entCpoDtlDealSlsAmt_LL.setInputProtected(false);
                // 2018/12/18 QC#29285 Add Start
                lineMsg.custMdseCd_LL.setInputProtected(false);
                // 2018/12/18 QC#29285 Add End
                lineMsg.prcCatgNm_LL.setInputProtected(false);
                lineMsg.xxImageTxt_BP.setValue(ZYPConstant.FLG_ON_Y);
                lineMsg.prcListEquipConfigNum_LL.setInputProtected(false);
                // Del Start 2016/10/25 M.Ohno S21_NA#14937
                // lineMsg.dsOrdLineCatgCd_LL.setInputProtected(false);
                // Del End   2016/10/25 M.Ohno S21_NA#14937
                lineMsg.flPrcListNm_LL.setInputProtected(false);
                lineMsg.funcUnitFlPrcAmt_LL.setInputProtected(false); // QC#22372 2018/01/10 Add
                lineMsg.xxImageTxt_BF.setValue(ZYPConstant.FLG_ON_Y);
                lineMsg.prcBaseDt_LL.setInputProtected(false);
            }
        }

        // RMA Tab (Line)
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(i);
            final String rmaLineStsNm = rmaLineMsg.rtrnLineStsDescTxt_RL.getValue();

            // 2016/09/06 S21_NA#8775 MOD START
            if (!LINE_STS_NM_CANCELLED.equals(rmaLineStsNm) && !LINE_STS_NM_CLOSED.equals(rmaLineStsNm)) {
            // 2016/09/06 S21_NA#8775 MOD END
                rmaLineMsg.entCpoDtlDealSlsAmt_RL.setInputProtected(false);
                // 2018/12/18 QC#29285 Add Start
                rmaLineMsg.custMdseCd_RL.setInputProtected(false);
                // 2018/12/18 QC#29285 Add End
                rmaLineMsg.prcCatgNm_RL.setInputProtected(false);
                rmaLineMsg.xxImageTxt_DP.setValue(ZYPConstant.FLG_ON_Y);
                rmaLineMsg.dsOrdLineCatgCd_RL.setInputProtected(false);
                rmaLineMsg.flPrcListNm_RL.setInputProtected(false);
                rmaLineMsg.funcUnitFlPrcAmt_RL.setInputProtected(false); // QC#22372 2018/01/10 Add
                rmaLineMsg.xxImageTxt_DF.setValue(ZYPConstant.FLG_ON_Y);
                rmaLineMsg.prcBaseDt_RL.setInputProtected(false);
                rmaLineMsg.rtrnRsnCd_RL.setInputProtected(false);
            }
        }
    }

    /**
     * Set Active Field By Modify Authority (Qty Items after Book)
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setActiveFieldByQtyAuth(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Line Config Tab (Line)
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
            final String lineStsNm = lineMsg.ordLineStsDescTxt_LL.getValue();

            // 2016/07/04 S21_NA#7504 Mod Start
            if (LINE_STS_NM_SO_CANCEL.equals(lineStsNm) || LINE_STS_NM_PO_CANCEL.equals(lineStsNm) || LINE_STS_NM_BOOKED.equals(lineStsNm) || LINE_STS_NM_PENDING_ALLOCATION.equals(lineStsNm) || LINE_STS_NM_BACK_ORDER.equals(lineStsNm)) {
                lineMsg.ordCustUomQty_LL.setInputProtected(false);
                lineMsg.custUomCd_LL.setInputProtected(false);
            }
            // 2016/07/04 S21_NA#7504 Mod End
        }

        // RMA Tab (Line)
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(i);
            final String rmaLineStsNm = rmaLineMsg.rtrnLineStsDescTxt_RL.getValue();

            if (LINE_STS_NM_BOOKED.equals(rmaLineStsNm)) {
                rmaLineMsg.ordCustUomQty_RL.setInputProtected(false);
                rmaLineMsg.custUomCd_RL.setInputProtected(false);
            }
        }
    }

    /**
     * Set Screen Protect By Header Status
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByHdrSts(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        final String hdrStsNm = scrnMsg.ordHdrStsDescTxt.getValue();

        if (ZYPCommonFunc.hasValue(hdrStsNm)) {
            if (HEADER_STS_NM_ENTERED.equals(hdrStsNm)) {
                setProtectHdrStsForEntered(handler, scrnMsg);
            } else if (HEADER_STS_NM_DI_CHECK_HOLD.equals(hdrStsNm)) {
                setProtectHdrStsForDICheckHold(handler, scrnMsg);
            } else if (HEADER_STS_NM_VALIDATION_HOLD.equals(hdrStsNm)) {
                setProtectHdrStsForValidationHold(handler, scrnMsg);
            } else if (HEADER_STS_NM_SUPPLY_ABUSE_HOLD.equals(hdrStsNm)) {
                setProtectHdrStsForSupplyAbuseHold(handler, scrnMsg);
            } else if (HEADER_STS_NM_PROFIITABILITY_HOLD.equals(hdrStsNm)) {
                setProtectHdrStsForProfitabilityHold(handler, scrnMsg);
            } else if (HEADER_STS_NM_CREDIT_HOLD.equals(hdrStsNm)) {
                setProtectHdrStsForCreditHold(handler, scrnMsg);
            } else if (HEADER_STS_NM_PENDING.equals(hdrStsNm)) {
                setProtectHdrStsForPending(handler, scrnMsg);
            } else if (HEADER_STS_NM_BOOKED.equals(hdrStsNm)) {
                setProtectHdrStsForBooked(handler, scrnMsg);
            } else if (HEADER_STS_NM_CLOSED.equals(hdrStsNm)) {
                setProtectHdrStsForClosed(handler, scrnMsg);
            } else if (HEADER_STS_NM_CANCELLED.equals(hdrStsNm)) {
                setProtectHdrStsForCancelled(handler, scrnMsg);
            }
        } else {
            setProtectHdrStsForUnregistered(handler, scrnMsg);
        }
    }

    /**
     * Set Screen Protect By Config Status For Line Tab
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByConfStsForLineTab(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1500_ABMsg confMsg = scrnMsg.A.no(i);
            final String confStsNm = getConfStsNm(scrnMsg, confMsg);

            // 2016/07/04 S21_NA#7504/10723 Mod Start
            // 2016/03/01 S21_NA#1738 Mod Start
            if (LINE_STS_NM_ENTERED.equals(confStsNm) || LINE_STS_NM_UNREGISTERED.equals(confStsNm)) {
                setConfItemProtectForEntered(confMsg);
            } else if (LINE_STS_NM_SO_CANCEL.equals(confStsNm) || LINE_STS_NM_PO_CANCEL.equals(confStsNm) || LINE_STS_NM_BOOKED.equals(confStsNm) || LINE_STS_NM_PENDING_ALLOCATION.equals(confStsNm) || LINE_STS_NM_BACK_ORDER.equals(confStsNm)) {
                setConfItemProtectForBooked(confMsg);
            } else if (LINE_STS_NM_WAITING_PICK.equals(confStsNm) || LINE_STS_NM_DELIVERED_TO_SHOP.equals(confStsNm) || LINE_STS_NM_IN_SHOP_OR_CONFIG.equals(confStsNm) || LINE_STS_NM_PENDING_SHIP.equals(confStsNm)
                    || LINE_STS_NM_SHIPPED.equals(confStsNm) || LINE_STS_NM_PENDING_DELIVERY_CONF.equals(confStsNm) || LINE_STS_NM_PENDING_INSTALL.equals(confStsNm) || LINE_STS_NM_WAITING_RECEIPT.equals(confStsNm)
                    || LINE_STS_NM_PENDING_INVOICE.equals(confStsNm) || LINE_STS_NM_BILLING_HOLD.equals(confStsNm)
                    || LINE_STS_NM_PENDING_FULFILLMENT.equals(confStsNm)) {
                setConfItemProtectForWaitingPick(confMsg);
            // 2016/05/11 S21_NA#7262 Mod Start
            } else if (LINE_STS_NM_SHIPPED_CLOSED.equals(confStsNm) || LINE_STS_NM_INVOICED.equals(confStsNm) || LINE_STS_NM_CANCELLED.equals(confStsNm) || LINE_STS_NM_ON_LOAN.equals(confStsNm)
                    || LINE_STS_NM_CLOSED_LOAN_RETURN.equals(confStsNm) || LINE_STS_NM_CLOSED_LOAN_SOLD.equals(confStsNm)) {
            // 2016/05/11 S21_NA#7262 Mod End
                setConfItemProtectForShippedClosed(confMsg);
            }
            // 2016/03/01 S21_NA#1738 Mod End
            // 2016/07/04 S21_NA#7504/10723 Mod End
        }
    }

    /**
     * Set Screen Protect By Config Status For RMA Tab
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByConfStsForRmaTab(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NWAL1500_CBMsg rmaConfMsg = scrnMsg.C.no(i);
            final String rmaConfStsNm = getRmaConfStsNm(scrnMsg, rmaConfMsg);

            // 2016/03/01 S21_NA#1738 Mod Start
            if (LINE_STS_NM_ENTERED.equals(rmaConfStsNm) || LINE_STS_NM_UNREGISTERED.equals(rmaConfStsNm)) {
                setRmaConfItemProtectForEntered(rmaConfMsg);
            } else if (LINE_STS_NM_BOOKED.equals(rmaConfStsNm) || LINE_STS_NM_RWS_CANCELLED.equals(rmaConfStsNm)) { // S21_NA#_12685 MOD
                setRmaConfItemProtectForBooked(rmaConfMsg);
            } else if (LINE_STS_NM_PENDING_RETURN.equals(rmaConfStsNm) || LINE_STS_NM_PARTIAL_RECEIVED.equals(rmaConfStsNm) || LINE_STS_NM_PENDING_INSPECTION.equals(rmaConfStsNm)
                    || LINE_STS_NM_BILLING_HOLD.equals(rmaConfStsNm) || LINE_STS_NM_PENDING_INVOICE.equals(rmaConfStsNm)) {
                setRmaConfItemProtectForPendingReturn(rmaConfMsg);
            } else if (LINE_STS_NM_CANCELLED.equals(rmaConfStsNm) || LINE_STS_NM_CLOSED.equals(rmaConfStsNm)) {
                setRmaConfItemProtectForClosed(rmaConfMsg);
            }
            // 2016/03/01 S21_NA#1738 Mod End
        }
    }

    /**
     * Set Screen Protect By Line Status For Line Tab
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByLineStsForLineTab(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        final String hdrStsNm = scrnMsg.ordHdrStsDescTxt.getValue();

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
            final String lineStsNm = lineMsg.ordLineStsDescTxt_LL.getValue();

            // 2016/07/04 S21_NA#7504/10723 Mod Start
            if (LINE_STS_NM_ENTERED.equals(lineStsNm)) {
                // 2016/02/28 S21_NA#1738 Mod Start
                if (HEADER_STS_NM_ENTERED.equals(hdrStsNm) || HEADER_STS_NM_DI_CHECK_HOLD.equals(hdrStsNm)) {
                    setLineItemProtectForEntered(lineMsg);
                } else if (HEADER_STS_NM_VALIDATION_HOLD.equals(hdrStsNm) || HEADER_STS_NM_SUPPLY_ABUSE_HOLD.equals(hdrStsNm) || HEADER_STS_NM_PROFIITABILITY_HOLD.equals(hdrStsNm) || HEADER_STS_NM_CREDIT_HOLD.equals(hdrStsNm) || HEADER_STS_NM_PENDING.equals(hdrStsNm)) {
                    setLineItemProtectForValidationHold(lineMsg);
                }
                // 2016/02/28 S21_NA#1738 Mod End
            } else if (LINE_STS_NM_SO_CANCEL.equals(lineStsNm) || LINE_STS_NM_PO_CANCEL.equals(lineStsNm) || LINE_STS_NM_BOOKED.equals(lineStsNm) || LINE_STS_NM_PENDING_ALLOCATION.equals(lineStsNm) || LINE_STS_NM_BACK_ORDER.equals(lineStsNm)) {
                setLineItemProtectForSoCancelled(lineMsg);
            } else if (LINE_STS_NM_WAITING_PICK.equals(lineStsNm) || LINE_STS_NM_DELIVERED_TO_SHOP.equals(lineStsNm) || LINE_STS_NM_IN_SHOP_OR_CONFIG.equals(lineStsNm) || LINE_STS_NM_PENDING_SHIP.equals(lineStsNm)
                    || LINE_STS_NM_SHIPPED.equals(lineStsNm) || LINE_STS_NM_PENDING_DELIVERY_CONF.equals(lineStsNm) || LINE_STS_NM_PENDING_INSTALL.equals(lineStsNm) || LINE_STS_NM_WAITING_RECEIPT.equals(lineStsNm)
                    || LINE_STS_NM_PENDING_INVOICE.equals(lineStsNm) || LINE_STS_NM_BILLING_HOLD.equals(lineStsNm)
                    || LINE_STS_NM_PENDING_FULFILLMENT.equals(lineStsNm) //
                    || LINE_STS_NM_PENDING_DEALER_INSTALL.equals(lineStsNm)) { // 2017/10/03 S21_NA#21167 Add
                setLineItemProtectForWaitingPick(lineMsg);
            // 2016/05/11 S21_NA#7262 Mod Start
            } else if (LINE_STS_NM_SHIPPED_CLOSED.equals(lineStsNm) || LINE_STS_NM_INVOICED.equals(lineStsNm) || LINE_STS_NM_CANCELLED.equals(lineStsNm) || LINE_STS_NM_ON_LOAN.equals(lineStsNm)
                    || LINE_STS_NM_CLOSED_LOAN_RETURN.equals(lineStsNm) || LINE_STS_NM_CLOSED_LOAN_SOLD.equals(lineStsNm)) {
            // 2016/05/11 S21_NA#7262 Mod End
                setLineItemProtectForShippedClosed(lineMsg);
            // Start 2023/2/28 S.Fujita [QC#61010, MOD]
            //}
            } else if (!ZYPCommonFunc.hasValue(lineStsNm)) {
                if (HEADER_STS_NM_ENTERED.equals(hdrStsNm) || HEADER_STS_NM_DI_CHECK_HOLD.equals(hdrStsNm)) {
                    setLineItemProtectForEntered(lineMsg);
                } else if (HEADER_STS_NM_VALIDATION_HOLD.equals(hdrStsNm) || HEADER_STS_NM_SUPPLY_ABUSE_HOLD.equals(hdrStsNm) || HEADER_STS_NM_PROFIITABILITY_HOLD.equals(hdrStsNm) || HEADER_STS_NM_CREDIT_HOLD.equals(hdrStsNm) || HEADER_STS_NM_PENDING.equals(hdrStsNm)) {
                    setLineItemProtectForValidationHold(lineMsg);
                }
            }
            // End 2023/2/28 S.Fujita [QC#61010, MOD]
            // 2016/07/04 S21_NA#7504/10723 Mod End
        }
    }

    /**
     * Set Screen Protect By Line Status For RMA Tab
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByLineStsForRmaTab(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        final String hdrStsNm = scrnMsg.ordHdrStsDescTxt.getValue();

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(i);
            final String rmaLineStsNm = rmaLineMsg.rtrnLineStsDescTxt_RL.getValue();
            String rwsOpenFlg = rmaLineMsg.rwsOpenFlg_RL.getValue(); // 2018/10/25 S21_NA#28897 Add

            // 2016/02/28 S21_NA#1738 Mod Start
            if (LINE_STS_NM_ENTERED.equals(rmaLineStsNm)) {
                if (HEADER_STS_NM_ENTERED.equals(hdrStsNm) || HEADER_STS_NM_DI_CHECK_HOLD.equals(hdrStsNm)) {
                    setRmaItemProtectForEntered(rmaLineMsg);
                } else if (HEADER_STS_NM_VALIDATION_HOLD.equals(hdrStsNm) || HEADER_STS_NM_SUPPLY_ABUSE_HOLD.equals(hdrStsNm) || HEADER_STS_NM_PROFIITABILITY_HOLD.equals(hdrStsNm) || HEADER_STS_NM_CREDIT_HOLD.equals(hdrStsNm) || HEADER_STS_NM_PENDING.equals(hdrStsNm)) {
                    setRmaItemProtectForValidationHold(rmaLineMsg);
                }
            } else if (LINE_STS_NM_BOOKED.equals(rmaLineStsNm) || LINE_STS_NM_RWS_CANCELLED.equals(rmaLineStsNm)) { // S21_NA#_12685 MOD
                setRmaItemProtectForValidationHold(rmaLineMsg);
            } else if (LINE_STS_NM_PENDING_RETURN.equals(rmaLineStsNm) || LINE_STS_NM_PARTIAL_RECEIVED.equals(rmaLineStsNm) || LINE_STS_NM_PENDING_INSPECTION.equals(rmaLineStsNm)
                    || LINE_STS_NM_BILLING_HOLD.equals(rmaLineStsNm) || LINE_STS_NM_PENDING_INVOICE.equals(rmaLineStsNm)) {
                // 2018/10/25 S21_NA#28897 Mod Start
                //setRmaItemProtectForPendingReturn(rmaLineMsg);
                setRmaItemProtectForPendingReturn(rmaLineMsg, rmaLineStsNm, rwsOpenFlg);
                // 2018/10/25 S21_NA#28897 Mod End
            } else if (LINE_STS_NM_CANCELLED.equals(rmaLineStsNm) || LINE_STS_NM_CLOSED.equals(rmaLineStsNm)) {
                setRmaItemProtectForClosed(rmaLineMsg);
            }
            // 2016/02/28 S21_NA#1738 Mod End
        }
    }

    /**
     * Screen protecting control [Header Status : Unregistered]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectHdrStsForUnregistered(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Header
        setHdrBtnProtectForUnregistered(handler, scrnMsg);

        // Addl Header Tab
        setAddlHdrBtnProtectForUnregistered(handler, scrnMsg);

        // Line Config Tab
        setLineTabBtnProtectForUnregistered(handler, scrnMsg);
    }

    /**
     * Screen protecting control [Header Status : Entered]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectHdrStsForEntered(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Header
        setHdrItemProtectForEntered(scrnMsg);
        setHdrBtnProtectForEnterd(handler, scrnMsg);
    }

    /**
     * Screen protecting control [Header Status : DICheckHold]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectHdrStsForDICheckHold(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Header
        // START 2023/10/13 [QC#61077, MOD]
//        setHdrItemProtectForEntered(scrnMsg);
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.ordBookFlg.getValue())) {
            setHdrItemProtectForValidationHold(scrnMsg);
        } else {
            setHdrItemProtectForEntered(scrnMsg);
        }
        // END   2023/10/13 [QC#61077, MOD]
        setHdrBtnProtectForEnterd(handler, scrnMsg);
    }

    /**
     * Screen protecting control [Header Status : ValidationHold]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectHdrStsForValidationHold(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Header
        // START 2023/10/13 [QC#61077, MOD]
//        setHdrItemProtectForValidationHold(scrnMsg);
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.ordBookFlg.getValue())) {
            setHdrItemProtectForValidationHold(scrnMsg);
        } else {
            setHdrItemProtectForEntered(scrnMsg);
        }
        // END   2023/10/13 [QC#61077, MOD]
        setHdrBtnProtectForEnterd(handler, scrnMsg);

        // 2016/02/28 S21_NA#1738 Del Start
//        // Header Tab
//        setHdrTabItemProtectForValidationHold(scrnMsg);
//
//        // Addl Header Tab
//        setAddlTabItemProtectForValidationHold(scrnMsg);
//
//        // Line Config Tab
//        setLineTabBtnProtectForValidationHold(handler, scrnMsg);
//
//        // RMA Tab
//        setRmaTabBtnProtectForValidationHold(handler, scrnMsg);
        // 2016/02/28 S21_NA#1738 Del End
    }

    /**
     * Screen protecting control [Header Status : SupplyAbuseHold]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectHdrStsForSupplyAbuseHold(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Header
        // START 2023/10/13 [QC#61077, MOD]
//        setHdrItemProtectForValidationHold(scrnMsg);
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.ordBookFlg.getValue())) {
            setHdrItemProtectForValidationHold(scrnMsg);
        } else {
            setHdrItemProtectForEntered(scrnMsg);
        }
        // END   2023/10/13 [QC#61077, MOD]
        setHdrBtnProtectForEnterd(handler, scrnMsg);

        // 2016/02/28 S21_NA#1738 Del Start
//        // Header Tab
//        setHdrTabItemProtectForValidationHold(scrnMsg);
//
//        // Addl Header Tab
//        setAddlTabItemProtectForValidationHold(scrnMsg);
//
//        // Line Config Tab
//        setLineTabBtnProtectForValidationHold(handler, scrnMsg);
//
//        // RMA Tab
//        setRmaTabBtnProtectForValidationHold(handler, scrnMsg);
        // 2016/02/28 S21_NA#1738 Del End
    }

    /**
     * Screen protecting control [Header Status : ProfitabilityHold]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectHdrStsForProfitabilityHold(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Header
        // START 2023/10/13 [QC#61077, MOD]
//        setHdrItemProtectForValidationHold(scrnMsg);
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.ordBookFlg.getValue())) {
            setHdrItemProtectForValidationHold(scrnMsg);
        } else {
            setHdrItemProtectForEntered(scrnMsg);
        }
        // END   2023/10/13 [QC#61077, MOD]
        setHdrBtnProtectForEnterd(handler, scrnMsg);

        // 2016/02/28 S21_NA#1738 Del Start
//        // Header Tab
//        setHdrTabItemProtectForValidationHold(scrnMsg);
//
//        // Addl Header Tab
//        setAddlTabItemProtectForValidationHold(scrnMsg);
//
//        // Line Config Tab
//        setLineTabBtnProtectForValidationHold(handler, scrnMsg);
//
//        // RMA Tab
//        setRmaTabBtnProtectForValidationHold(handler, scrnMsg);
        // 2016/02/28 S21_NA#1738 Del End
    }

    /**
     * Screen protecting control [Header Status : CreditHold]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectHdrStsForCreditHold(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Header
        // START 2023/10/13 [QC#61077, MOD]
//        setHdrItemProtectForValidationHold(scrnMsg);
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.ordBookFlg.getValue())) {
            setHdrItemProtectForValidationHold(scrnMsg);
        } else {
            setHdrItemProtectForEntered(scrnMsg);
        }
        // END   2023/10/13 [QC#61077, MOD]
        setHdrBtnProtectForEnterd(handler, scrnMsg);

        // 2016/02/28 S21_NA#1738 Del Start
//        // Header Tab
//        setHdrTabItemProtectForValidationHold(scrnMsg);
//
//        // Addl Header Tab
//        setAddlTabItemProtectForValidationHold(scrnMsg);
//
//        // Line Config Tab
//        setLineTabBtnProtectForValidationHold(handler, scrnMsg);
//
//        // RMA Tab
//        setRmaTabBtnProtectForValidationHold(handler, scrnMsg);
        // 2016/02/28 S21_NA#1738 Del End
    }

    /**
     * Screen protecting control [Header Status : Pending]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectHdrStsForPending(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Header
        // START 2023/10/13 [QC#61077, MOD]
//        setHdrItemProtectForValidationHold(scrnMsg);
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.ordBookFlg.getValue())) {
            setHdrItemProtectForValidationHold(scrnMsg);
        } else {
            setHdrItemProtectForEntered(scrnMsg);
        }
        // END   2023/10/13 [QC#61077, MOD]
        setHdrBtnProtectForEnterd(handler, scrnMsg);
    }

    /**
     * Screen protecting control [Header Status : Booked]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectHdrStsForBooked(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Header
        setHdrItemProtectForValidationHold(scrnMsg);
        setHdrBtnProtectForEnterd(handler, scrnMsg);

        // 2016/02/28 S21_NA#1738 Del Start
//        // Header Tab
//        setHdrTabItemProtectForBooked(scrnMsg);
//        setHdrBtnProtectForBooked(handler, scrnMsg);
//
//        // Addl Header Tab
//        setAddlTabItemProtectForBooked(scrnMsg);
//        setAddlHdrBtnProtectForUnregistered(handler, scrnMsg);
//
//        // Line Config Tab
//        setLineTabBtnProtectForBooked(handler, scrnMsg);
//
//        // RMA Tab
//        setRmaTabBtnProtectForBooked(handler, scrnMsg);
        // 2016/02/28 S21_NA#1738 Del End
    }

    /**
     * Screen protecting control [Header Status : Closed]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectHdrStsForClosed(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Header
        // 2016/02/28 S21_NA#1738 Mod Start
        setHdrItemProtectForClosed(scrnMsg);
        // 2016/02/28 S21_NA#1738 Mod End
        setHdrBtnProtectForClosed(handler, scrnMsg);

        // Header Tab
        setHdrTabItemProtectForClosed(scrnMsg);
        setHdrBtnProtectForBooked(handler, scrnMsg);

        // Addl Header Tab
        setAddlTabItemProtectForClosed(scrnMsg);
        // 2016/10/03 S21_NA#13958 MOD START
//        setAddlHdrBtnProtectForUnregistered(handler, scrnMsg);
        // Mod Start 2019/05/09 QC#50015
        boolean isLogistics = false;
        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
            String funcId = scrnMsg.L.no(i).xxFuncId.getValue();
            if (scrnMsg.L.getValidCount() == 1 && MOD_LOGISTICS.equals(funcId)) {
                isLogistics = true;
                break;
            }
        }
        if (isLogistics) {
            setAddlHdrBtnProtectForUnregistered(handler, scrnMsg);
        } else {
            setAddlHdrBtnProtectForClosed(handler, scrnMsg);
        }
        // Mod End 2019/05/09 QC#50015
        // 2016/10/03 S21_NA#13958 MOD END

        // Add Start 2018/08/07 QC#23587
        // Line Config Tab
        setLineTabBtnProtectForClosed(handler, scrnMsg, true);

        // RMA Tab
        setRmaTabBtnProtectForClosed(handler, scrnMsg, true);
        // Add End 2018/08/07 QC#23587

        // Common
        NWAL1500CommonLogic.inactiveRegistrationButton(handler);
    }

    /**
     * Screen protecting control [Header Status : Cancelled]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectHdrStsForCancelled(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Header
        // 2016/02/28 S21_NA#1738 Mod Start
        setHdrItemProtectForClosed(scrnMsg);
        // 2016/02/28 S21_NA#1738 Mod End
        setHdrBtnProtectForCancelled(handler, scrnMsg);

        // Header Tab
        setHdrTabItemProtectForClosed(scrnMsg);
        setHdrBtnProtectForBooked(handler, scrnMsg);

        // Addl Header Tab
        setAddlTabItemProtectForClosed(scrnMsg);
        setAddlHdrBtnProtectForUnregistered(handler, scrnMsg);

        // Add Start 2018/08/07 QC#23587
        // Line Config Tab
        setLineTabBtnProtectForClosed(handler, scrnMsg, false);

        // RMA Tab
        setRmaTabBtnProtectForClosed(handler, scrnMsg, false);
        // Add End 2018/08/07 QC#23587

        // Common
        NWAL1500CommonLogic.inactiveRegistrationButton(handler);
    }

    /**
     * Set All Active Fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setAllActiveFields(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Header
        scrnMsg.cpoOrdNum.setInputProtected(false);
        scrnMsg.dsOrdCatgDescTxt.setInputProtected(false);
        scrnMsg.dsOrdCatgDescTxt_LK.setInputProtected(false);
        scrnMsg.dsOrdTpDescTxt.setInputProtected(false);
        scrnMsg.dsOrdTpDescTxt_LK.setInputProtected(false);
        scrnMsg.ordDt.setInputProtected(false);
        // 2016/03/04 S21_NA#2178 Mod Start
        scrnMsg.dsOrdRsnCd.setInputProtected(false);
        // 2016/03/04 S21_NA#2178 Mod End
        scrnMsg.ordDt_DP.setInputProtected(true);
        scrnMsg.ordHdrStsDescTxt.setInputProtected(true);
        handler.setButtonEnabled(BTN_ORDER_SRCH_EVENT_NM, true);
        handler.setButtonEnabled(BTN_ORDER_COPY_EVENT_NM, true);
        handler.setButtonEnabled(BTN_ORDER_CANCEL_EVENT_NM, true);
        handler.setButtonEnabled(BTN_HOLDS_EVENT_NM, true);
        handler.setButtonEnabled(BTN_DELIVERYINFO_EVENT_NM, true);
        handler.setButtonEnabled(BTN_SHIPPINGDETAIL_EVENT_NM, true);
        handler.setButtonEnabled(BTN_PROFITABILITY_EVENT_NM, true);
        handler.setButtonEnabled(BTN_DICHECK_EVENT_NM, true);
        handler.setButtonEnabled(BTN_SERVICEPRIC_EVENT_NM, true);
        handler.setButtonEnabled(BTN_CHANGEORDERMOD_EVENT_NM, true);
        handler.setButtonEnabled(BTN_ORDERAMTCALC_EVENT_NM, true);

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        if (TAB_ADDL_HEADER.equals(dplyTab)) {
            scrnMsg.cpoOrdNum.setInputProtected(true);
            scrnMsg.dsOrdCatgDescTxt.setInputProtected(true);
            scrnMsg.dsOrdCatgDescTxt_LK.setInputProtected(true);
            scrnMsg.dsOrdCatgDescTxt_LK.clear();
            scrnMsg.dsOrdTpDescTxt.setInputProtected(true);
            scrnMsg.dsOrdTpDescTxt_LK.setInputProtected(true);
            scrnMsg.dsOrdTpDescTxt_LK.clear();
            scrnMsg.ordDt.setInputProtected(true);
            scrnMsg.ordDt_DP.setInputProtected(true);
            scrnMsg.ordHdrStsDescTxt.setInputProtected(true);
            scrnMsg.dsOrdRsnCd.setInputProtected(true);
        }

        // Header Tab
        scrnMsg.billToCustAcctNm.setInputProtected(false);
        scrnMsg.billToCustAcctNm_LK.setInputProtected(false);
        scrnMsg.billToCustAcctCd.setInputProtected(false);
        scrnMsg.billToCustAcctCd_LK.setInputProtected(false);
        scrnMsg.billToCustCd.setInputProtected(false);
        scrnMsg.billToCustCd_LK.setInputProtected(false);
        scrnMsg.xxAllLineAddr_BT.setInputProtected(true);
        scrnMsg.entBillToCustLocAddr.setInputProtected(true);
        scrnMsg.shipToCustAcctNm.setInputProtected(false);
        scrnMsg.shipToCustAcctNm_LK.setInputProtected(false);
        scrnMsg.shipToCustAcctCd.setInputProtected(false);
        scrnMsg.shipToCustAcctCd_LK.setInputProtected(false);
        scrnMsg.shipToCustCd.setInputProtected(false);
        scrnMsg.shipToCustCd_LK.setInputProtected(false);
        scrnMsg.xxAllLineAddr_SH.setInputProtected(true);
        scrnMsg.entShipToCustLocAddr.setInputProtected(true);
        // 2017/12/07 S21_NA#21621 Add Start
        scrnMsg.addShipToLocNm.setInputProtected(true);
        // 2017/12/07 S21_NA#21621 Add End
        scrnMsg.sellToFirstRefCmntTxt.setInputProtected(false); // 2017/12/12 S21_NA#20164 Add
        scrnMsg.soldToCustAcctNm.setInputProtected(false);
        scrnMsg.soldToCustAcctNm_LK.setInputProtected(false);
        scrnMsg.sellToCustCd.setInputProtected(false);
        scrnMsg.sellToCustCd_LK.setInputProtected(false);
        scrnMsg.soldToCustLocCd.setInputProtected(false);
        scrnMsg.soldToCustLocCd_LK.setInputProtected(false);
        scrnMsg.xxAllLineAddr_SE.setInputProtected(true);
        scrnMsg.entSoldToCustLocAddr.setInputProtected(true);
        scrnMsg.negoDealAmt.setInputProtected(false);
        scrnMsg.slsRepTocCd.setInputProtected(false);
        scrnMsg.slsRepTocCd_LK.setInputProtected(false);
        // 2016/05/11 S21_NA#7861 Mod Start
//        scrnMsg.slsRepPsnCd.setInputProtected(false);
//        scrnMsg.slsRepPsnCd_LK.setInputProtected(false);
        scrnMsg.psnNum.setInputProtected(false);
        scrnMsg.psnNum_LK.setInputProtected(false);
        // 2016/05/11 S21_NA#7861 Mod End
        scrnMsg.slsRepTocNm.setInputProtected(false);
        scrnMsg.slsRepTocNm_LK.setInputProtected(false);
        scrnMsg.prcCatgNm.setInputProtected(false);
        scrnMsg.prcCatgNm_LK.setInputProtected(false);
        scrnMsg.custIssPoNum.setInputProtected(false);
        scrnMsg.leaseCmpyPoNum.setInputProtected(false);
        scrnMsg.custIssPoDt.setInputProtected(false);
        scrnMsg.aquNum.setInputProtected(false);
        // QC#1692
        scrnMsg.ordLogTpCd.setInputProtected(false);
        scrnMsg.ordLogTpCd_LK.setInputProtected(false);
        scrnMsg.ordLogTpDescTxt_NM.setInputProtected(true); // S21_NA#5337 Mod
        scrnMsg.xxInvCmntTxt.setInputProtected(false);
        scrnMsg.invCmntTxt.setInputProtected(false);
        handler.setButtonEnabled(BTN_SPESIAL_INS_EVENT_NM, true);
        scrnMsg.xxScrItem54Txt_CB.setInputProtected(true);
        scrnMsg.xxScrItem54Txt_CE.setInputProtected(true);
        scrnMsg.cpoSrcTpDescTxt.setInputProtected(true);

        // Addl Header Tab
        scrnMsg.frtCondDescTxt.setInputProtected(false);
        scrnMsg.frtCondDescTxt_LK.setInputProtected(false);
        scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
        scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(true);
        scrnMsg.carrSvcLvlDescTxt_LK.clear();
        scrnMsg.spclHdlgTpCd.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd.setInputProtected(false);
        scrnMsg.carrAcctNum.setInputProtected(true);
        scrnMsg.pmtTermCashDiscDescTxt.setInputProtected(false);
        scrnMsg.pmtTermCashDiscDescTxt_LK.setInputProtected(false);
        scrnMsg.dsPmtMethCd.setInputProtected(false);
        scrnMsg.prePmtChkNum.setInputProtected(false);
        scrnMsg.prePmtAmt.setInputProtected(false);
        scrnMsg.prePmtTpCd.setInputProtected(false);
        scrnMsg.addRddDt.setInputProtected(false);
        scrnMsg.prcContrNum.setInputProtected(false);
        scrnMsg.prcContrNum_LK.setInputProtected(false);
        scrnMsg.prcContrNm.setInputProtected(false);
        scrnMsg.prcContrNm_LK.setInputProtected(false);
        scrnMsg.prcContrNm.setInputProtected(false); // 2016/10/13 S21_NA#7700 Mod
        scrnMsg.flPrcListNm.setInputProtected(false);
        scrnMsg.flPrcListDescTxt_LK.setInputProtected(false);
        scrnMsg.loanPerDaysAot.setInputProtected(false);
        scrnMsg.csmpContrNum.setInputProtected(false);
        scrnMsg.csmpContrNum_LK.setInputProtected(false);
        scrnMsg.dlrRefNum.setInputProtected(false);
        scrnMsg.dlrRefNum_LK.setInputProtected(false);
        scrnMsg.ordSgnDt.setInputProtected(false);
        scrnMsg.dclnSvcCd.setInputProtected(false); // S21_NA#8388 ADD
        scrnMsg.leasePrchOptCd.setInputProtected(false);
        scrnMsg.leaseTermMthAot.setInputProtected(false);
        scrnMsg.leasePmtFreqCd.setInputProtected(false);
        scrnMsg.leaseTotPmtAmt.setInputProtected(false);
        scrnMsg.leaseTermMthAot_EM.setInputProtected(false); // QC#22789 2017/11/28 Add
        // 2017/11/02 S21_NA#20146 Add Start
        scrnMsg.psnNum_GS.setInputProtected(true);
        scrnMsg.tocNm_GS.setInputProtected(true);
        scrnMsg.coaExtnCd_GS.setInputProtected(true);
        scrnMsg.coaExtnDescTxt_GS.setInputProtected(true);
        scrnMsg.coaBrCd_GS.setInputProtected(true);
        scrnMsg.coaBrDescTxt_GS.setInputProtected(true);
        scrnMsg.coaCcCd_GS.setInputProtected(true);
        scrnMsg.coaCcDescTxt_GS.setInputProtected(true);
        // 2017/11/02 S21_NA#20146 Add End
        scrnMsg.cratTsDplyTxt.setInputProtected(true);
        scrnMsg.cratUsrNm.setInputProtected(true);
        scrnMsg.updTsDplyTxt.setInputProtected(true);
        scrnMsg.updUsrNm.setInputProtected(true);
        scrnMsg.ordBookReqTsDplyTxt.setInputProtected(true); // 2016/09/20 S21_NA#6100-2 Add
        scrnMsg.ordBookReqUsrNm.setInputProtected(true); // 2016/09/20 S21_NA#6100-2 Add
        scrnMsg.ordBookTsDplyTxt.setInputProtected(true);
        handler.setButtonEnabled(BTN_CREDITCARD_EVENT_NM, true);

        // Line Config Tab (Common Button)
        NWAL1500CommonLogic.activeAddButton(handler);
        handler.setButtonEnabled(BTN_LINE_CANCEL_EVENT_NM, true);
        handler.setButtonEnabled(BTN_COPYLINE_EVENT_NM, true);
        handler.setButtonEnabled(BTN_COPYFROM_EVENT_NM, true);
        handler.setButtonEnabled(BTN_AUTO_ADD_SUPPLY_EVENT_NM, true);
        handler.setButtonEnabled(BTN_MASSUPDATE_EVENT_NM, true);
        handler.setButtonEnabled(BTN_STC_OVER_VIEW_EVENT_NM, true);
        handler.setButtonEnabled(BTN_METERENTRY_EVENT_NM, true);

        // RMA Tab (Common Button)
        handler.setButtonEnabled(BTN_LINE_CANCEL_EVENT_NM, true);
        handler.setButtonEnabled(BTN_COPYLINE_EVENT_NM, true);
        handler.setButtonEnabled(BTN_COPYFROM_EVENT_NM, true);
        handler.setButtonEnabled(BTN_AUTO_ADD_RMA_EVENT_NM, true);
        handler.setButtonEnabled(BTN_MASSUPDATE_EVENT_NM, true);

        // Line Config Tab (Config)
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1500_ABMsg configMsg = scrnMsg.A.no(i);
            configMsg.xxChkBox_LC.setInputProtected(false);
            configMsg.configTpCd_LC.setInputProtected(false);
            configMsg.mdlNm_LC.setInputProtected(true);
            configMsg.svcConfigMstrPk_LC.setInputProtected(false);
            configMsg.xxImageTxt_AC.setInputProtected(false);
            configMsg.mdlDescTxt_LC.setInputProtected(true);

            configMsg.shipToCustLocAddr_LC.setInputProtected(true);
            configMsg.shipToCustAcctCd_LC.setInputProtected(false);
            configMsg.xxImageTxt_AA.setInputProtected(false);
            configMsg.shipToCustCd_LC.setInputProtected(false);
            configMsg.xxImageTxt_AS.setInputProtected(false);
            configMsg.shipToCustAcctNm_LC.setInputProtected(false);
            configMsg.xxImageTxt_AN.setInputProtected(false);

            configMsg.billToCustLocAddr_LC.setInputProtected(true);
            configMsg.billToCustAcctCd_LC.setInputProtected(false);
            configMsg.xxImageTxt_AT.setInputProtected(false);
            configMsg.billToCustCd_LC.setInputProtected(false);
            configMsg.xxImageTxt_AB.setInputProtected(false);
            configMsg.billToCustAcctNm_LC.setInputProtected(false);
            configMsg.xxImageTxt_AM.setInputProtected(false);

            configMsg.soldToCustLocAddr_LC.setInputProtected(true);
            configMsg.sellToCustCd_LC.setInputProtected(true);
            configMsg.soldToCustLocCd_LC.setInputProtected(true);
            configMsg.soldToCustAcctNm_LC.setInputProtected(true);

            configMsg.mdlGrpDescTxt_LC.setInputProtected(true);
            configMsg.mdlDescTxt_LC.setInputProtected(true);
            configMsg.svcSegDescTxt_LC.setInputProtected(true);
            configMsg.cratUsrNm_LC.setInputProtected(true);
            configMsg.updUsrNm_LC.setInputProtected(true);

            configMsg.dropShipFlg_LC.setInputProtected(false);
            // 2017/12/08 S21_NA#21621 Add Start
            configMsg.addShipToLocNm_LC.setInputProtected(true);
            // 2017/12/08 S21_NA#21621 Add End

            configMsg.dclnSvcCd_LC.setInputProtected(false); // S21_NA#8388 ADD

            configMsg.prcCatgNm_LC.setInputProtected(true); // 2016/08/26 S21_NA#9806 Add Start

            // 2019/07/11 S21_NA#51287 Add Start
            configMsg.csmpContrNum_LC.setInputProtected(false);
            configMsg.dlrRefNum_LC.setInputProtected(false);
            // 2019/07/11 S21_NA#51287 Add End
        }

        // Line Config Tab (Line)
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
            lineMsg.xxChkBox_LL.setInputProtected(false);
            lineMsg.mdseCd_LL.setInputProtected(false);
            lineMsg.mdseNm_LL.setInputProtected(true);
            lineMsg.mdseDescShortTxt_LL.setInputProtected(true); // S21_NA#2334 Add 
            lineMsg.xxImageTxt_BI.setInputProtected(false);
            lineMsg.ordCustUomQty_LL.setInputProtected(false);
            lineMsg.custUomCd_LL.setInputProtected(false);
            lineMsg.entCpoDtlDealSlsAmt_LL.setInputProtected(false);
            lineMsg.prcCatgNm_LL.setInputProtected(false);
            lineMsg.xxImageTxt_BP.setInputProtected(false);
            lineMsg.prcListEquipConfigNum_LL.setInputProtected(false);
            lineMsg.dsOrdLineCatgCd_LL.setInputProtected(false);
            lineMsg.ordLineSrcCd_LL.setInputProtected(false);
            lineMsg.rtlWhNm_LL.setInputProtected(false);
            lineMsg.rtlSwhNm_LL.setInputProtected(false);
            lineMsg.xxImageTxt_BW.setInputProtected(false);
            // QC#1615
            lineMsg.xxImageTxt_BU.setInputProtected(false);
            lineMsg.serNum_LL.setInputProtected(false);
            // 2016/03/01 S21_NA#1738 Add Start
            // 2018/12/06 QC#29285 Mod Start
//            lineMsg.custMdseCd_LL.setInputProtected(true);
            lineMsg.custMdseCd_LL.setInputProtected(false);
            // 2018/12/06 QC#29285 Mod End
            // 2016/03/01 S21_NA#1738 Add End
            lineMsg.supdLockFlg_LL.setInputProtected(false);
            lineMsg.sbstMdseCd_LL.setInputProtected(false);
            lineMsg.xxImageTxt_BS.setInputProtected(false);
            lineMsg.vndInvtyLocCd_LL.setInputProtected(false);
            lineMsg.flPrcListNm_LL.setInputProtected(false);
            lineMsg.xxImageTxt_BF.setInputProtected(false);
            // 2018/04/12 S21_NA#23704 Mod Start
            // lineMsg.funcUnitFlPrcAmt_LL.setInputProtected(false); // QC#22372 2018/01/10 Add
            lineMsg.funcUnitFlPrcAmt_LL.setInputProtected(true);
            // 2018/04/12 S21_NA#23704 Mod End
            lineMsg.dplyLineRefNum_LL.setInputProtected(false);
            lineMsg.prcBaseDt_LL.setInputProtected(false);
            lineMsg.rddDt_LL.setInputProtected(false);

            lineMsg.cratTsDplyTxt_LL.setInputProtected(true);
            lineMsg.ordBookTsDplyTxt_LL.setInputProtected(true);
            lineMsg.shipDtTsDplyTxt_LL.setInputProtected(true);
            lineMsg.schdDelyTsDplyTxt_LL.setInputProtected(true);
            lineMsg.cancRsnDescTxt_LL.setInputProtected(true);
            lineMsg.custIssPoNum_LL.setInputProtected(true);
            lineMsg.cpoSrcTpDescTxt_LL.setInputProtected(true);
            lineMsg.ordSrcRefNum_LL.setInputProtected(true);
            lineMsg.lineSrcRefNum_LL.setInputProtected(true);
            lineMsg.setPrntLineNum_LL.setInputProtected(true);
            lineMsg.cmpsnRevnNum_LL.setInputProtected(true);
            lineMsg.setMdseCd_LL.setInputProtected(true);
            lineMsg.cratUsrNm_LL.setInputProtected(true);
            lineMsg.cratTsDplyTxt_LL.setInputProtected(true);
            lineMsg.updUsrNm_LL.setInputProtected(true);
            lineMsg.updTsDplyTxt_LL.setInputProtected(true);
            lineMsg.prcListEquipConfigNm_LL.setInputProtected(true);
            lineMsg.mdseItemClsTpDescTxt_LL.setInputProtected(true);
            lineMsg.svcMachMstrPk_LL.setInputProtected(true); // S21_NA#5340
        }

        // RMA Tab (Config)
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NWAL1500_CBMsg configMsg = scrnMsg.C.no(i);
            configMsg.xxChkBox_RC.setInputProtected(false);
            configMsg.configTpCd_RC.setInputProtected(false);
            configMsg.mdlNm_RC.setInputProtected(true);
            configMsg.svcConfigMstrPk_RC.setInputProtected(false);
            configMsg.xxImageTxt_CC.setInputProtected(false);
            configMsg.mdlDescTxt_RC.setInputProtected(true);

            configMsg.shipToCustLocAddr_RC.setInputProtected(true);
            configMsg.shipToCustAcctCd_RC.setInputProtected(false);
            configMsg.xxImageTxt_CA.setInputProtected(false);
            configMsg.shipToCustCd_RC.setInputProtected(false);
            configMsg.xxImageTxt_CS.setInputProtected(false);
            configMsg.shipToCustAcctNm_RC.setInputProtected(false);
            configMsg.xxImageTxt_CN.setInputProtected(false);

            configMsg.billToCustLocAddr_RC.setInputProtected(true);
            configMsg.billToCustAcctCd_RC.setInputProtected(false);
            configMsg.xxImageTxt_CT.setInputProtected(false);
            configMsg.billToCustCd_RC.setInputProtected(false);
            configMsg.xxImageTxt_CB.setInputProtected(false);
            configMsg.billToCustAcctNm_RC.setInputProtected(false);
            configMsg.xxImageTxt_CM.setInputProtected(false);

            configMsg.soldToCustLocAddr_RC.setInputProtected(true);
            configMsg.sellToCustCd_RC.setInputProtected(true);
            configMsg.soldToCustLocCd_RC.setInputProtected(true);
            configMsg.soldToCustAcctNm_RC.setInputProtected(true);

            configMsg.mdlGrpDescTxt_RC.setInputProtected(true);
            configMsg.mdlDescTxt_RC.setInputProtected(true);
            configMsg.svcSegDescTxt_RC.setInputProtected(true);
            configMsg.cratUsrNm_RC.setInputProtected(true);
            configMsg.updUsrNm_RC.setInputProtected(true);

            configMsg.dropShipFlg_RC.setInputProtected(false);
            // 2017/12/08 S21_NA#21621 Add Start
            configMsg.addShipToLocNm_RC.setInputProtected(true);
            // 2017/12/08 S21_NA#21621 Add End
            configMsg.prcCatgNm_RC.setInputProtected(true); // 2016/08/26 S21_NA#9806 Add Start

            // 2019/07/11 S21_NA#51287 Add Start
            configMsg.csmpContrNum_RC.setInputProtected(false);
            configMsg.dlrRefNum_RC.setInputProtected(false);
            // 2019/07/11 S21_NA#51287 Add End
        }

        // RMA Tab (Line)
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NWAL1500_DBMsg lineMsg = scrnMsg.D.no(i);
            lineMsg.xxChkBox_RL.setInputProtected(false);
            lineMsg.mdseCd_RL.setInputProtected(false);
            lineMsg.mdseNm_RL.setInputProtected(true);
            lineMsg.mdseDescShortTxt_RL.setInputProtected(true); // S21_NA#2334 Add
            lineMsg.xxImageTxt_DI.setInputProtected(false);
            lineMsg.ordCustUomQty_RL.setInputProtected(false);
            lineMsg.rtrnRsnCd_RL.setInputProtected(false);
            lineMsg.entCpoDtlDealSlsAmt_RL.setInputProtected(false);
            lineMsg.rtrnLineStsDescTxt_RL.setInputProtected(true);
            lineMsg.dsOrdLineCatgCd_RL.setInputProtected(false);
            lineMsg.rtlWhNm_RL.setInputProtected(false);
            lineMsg.xxImageTxt_DW.setInputProtected(false);
            // 2017/11/17 S21_NA#22252 Mod Start
            //lineMsg.rtlSwhNm_RL.setInputProtected(true);
            lineMsg.rtlSwhNm_RL.setInputProtected(false);
            lineMsg.xxImageTxt_DU.setInputProtected(false);
            // 2017/11/17 S21_NA#22252 Mod End
            lineMsg.cancRsnDescTxt_RL.setInputProtected(true);
            lineMsg.custUomCd_RL.setInputProtected(false);
            lineMsg.prcCatgNm_RL.setInputProtected(false);
            lineMsg.xxImageTxt_DP.setInputProtected(false);
            lineMsg.flPrcListNm_RL.setInputProtected(false);
            lineMsg.xxImageTxt_DF.setInputProtected(false);
            // 2018/04/12 S21_NA#23704 Mod Start
            // lineMsg.funcUnitFlPrcAmt_RL.setInputProtected(false);
            lineMsg.funcUnitFlPrcAmt_RL.setInputProtected(true);// QC#22372 2018/01/10 Add
            // 2018/04/12 S21_NA#23704 Mod End
            lineMsg.serNum_RL.setInputProtected(false);
            // 2016/03/01 S21_NA#1738 Mod Start
            // 2018/12/06 QC#29285 Mod Start
//            lineMsg.custMdseCd_RL.setInputProtected(true);
            lineMsg.custMdseCd_RL.setInputProtected(false);
            // 2018/12/06 QC#29285 Mod End
            // 2016/03/01 S21_NA#1738 Mod End
            lineMsg.rqstPickUpDt_RL.setInputProtected(false);
            lineMsg.prcBaseDt_RL.setInputProtected(false);
            lineMsg.cancRsnDescTxt_RL.setInputProtected(true);
            lineMsg.coaMdseTpDescTxt_RL.setInputProtected(true);
            lineMsg.coaProdDescTxt_RL.setInputProtected(true);
            lineMsg.mdseItemClsTpDescTxt_RL.setInputProtected(true);
            lineMsg.dplyLineRefNum_RL.setInputProtected(false);
            lineMsg.custIssPoNum_RL.setInputProtected(true);
            lineMsg.cpoSrcTpDescTxt_RL.setInputProtected(true);
            lineMsg.ordSrcRefNum_RL.setInputProtected(true);
            lineMsg.lineSrcRefNum_RL.setInputProtected(true);
            lineMsg.setPrntLineNum_RL.setInputProtected(true);
            lineMsg.cmpsnRevnNum_RL.setInputProtected(true);
            lineMsg.setMdseCd_RL.setInputProtected(true);
            lineMsg.cratUsrNm_RL.setInputProtected(true);
            lineMsg.updUsrNm_RL.setInputProtected(true);
            lineMsg.hddRmvCd_RL.setInputProtected(false);
            lineMsg.svcMachMstrPk_RL.setInputProtected(true); // S21_NA#5340
        }
    }

    /**
     * Set All Inactive Fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setAllInactiveFields(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // 2017/10/13 S21_NA#21267 Add Start
        boolean isOrderCredit = NWAL1500CommonLogic.isOrderCredit(scrnMsg);
        // 2017/10/13 S21_NA#21267 Add End

        // Header
        scrnMsg.cpoOrdNum.setInputProtected(true);
        scrnMsg.dsOrdCatgDescTxt.setInputProtected(true);
        scrnMsg.dsOrdCatgDescTxt_LK.clear();
        scrnMsg.dsOrdTpCd.setInputProtected(true);
        scrnMsg.dsOrdTpDescTxt.setInputProtected(true);
        scrnMsg.dsOrdTpDescTxt_LK.setInputProtected(true);
        scrnMsg.dsOrdRsnCd.setInputProtected(true);
        scrnMsg.ordDt.setInputProtected(true);
        scrnMsg.ordDt_DP.setInputProtected(true);
        scrnMsg.ordHdrStsDescTxt.setInputProtected(true);
        handler.setButtonEnabled(BTN_ORDER_COPY_EVENT_NM, false);
        handler.setButtonEnabled(BTN_ORDER_CANCEL_EVENT_NM, false);
        handler.setButtonEnabled(BTN_HOLDS_EVENT_NM, false);
        handler.setButtonEnabled(BTN_DELIVERYINFO_EVENT_NM, false);
        handler.setButtonEnabled(BTN_SHIPPINGDETAIL_EVENT_NM, false);
        handler.setButtonEnabled(BTN_PROFITABILITY_EVENT_NM, false);
        handler.setButtonEnabled(BTN_DICHECK_EVENT_NM, false);
        handler.setButtonEnabled(BTN_SERVICEPRIC_EVENT_NM, false);
        handler.setButtonEnabled(BTN_CHANGEORDERMOD_EVENT_NM, false);
        handler.setButtonEnabled(BTN_ORDERAMTCALC_EVENT_NM, false);

        // Header Tab
        scrnMsg.billToCustAcctNm.setInputProtected(true);
        scrnMsg.billToCustAcctNm_LK.clear();
        scrnMsg.billToCustAcctCd.setInputProtected(true);
        scrnMsg.billToCustAcctCd_LK.clear();
        scrnMsg.billToCustCd.setInputProtected(true);
        scrnMsg.billToCustCd_LK.clear();
        scrnMsg.xxAllLineAddr_BT.setInputProtected(true);
        scrnMsg.entBillToCustLocAddr.setInputProtected(true);
        scrnMsg.shipToCustAcctNm.setInputProtected(true);
        scrnMsg.shipToCustAcctNm_LK.clear();
        scrnMsg.shipToCustAcctCd.setInputProtected(true);
        scrnMsg.shipToCustAcctCd_LK.clear();
        scrnMsg.shipToCustCd.setInputProtected(true);
        scrnMsg.shipToCustCd_LK.clear();
        scrnMsg.dropShipFlg_LK.setInputProtected(true); // 2018/11/16 S21_NA#27299 Add
        scrnMsg.xxAllLineAddr_SH.setInputProtected(true);
        scrnMsg.sellToFirstRefCmntTxt.setInputProtected(true); // 2017/12/12 S21_NA#20164 Add
        scrnMsg.entShipToCustLocAddr.setInputProtected(true);
        scrnMsg.soldToCustAcctNm.setInputProtected(true);
        scrnMsg.soldToCustAcctNm_LK.clear();
        scrnMsg.sellToCustCd.setInputProtected(true);
        scrnMsg.sellToCustCd_LK.clear();
        scrnMsg.soldToCustLocCd.setInputProtected(true);
        scrnMsg.soldToCustLocCd_LK.clear();
        scrnMsg.xxAllLineAddr_SE.setInputProtected(true);
        scrnMsg.entSoldToCustLocAddr.setInputProtected(true);
        scrnMsg.negoDealAmt.setInputProtected(true);
        scrnMsg.slsRepTocCd.setInputProtected(true);
        scrnMsg.slsRepTocCd_LK.clear();
        // 2016/05/11 S21_NA#7861 Mod Start
//        scrnMsg.slsRepPsnCd.setInputProtected(true);
//        scrnMsg.slsRepPsnCd_LK.clear();
        scrnMsg.psnNum.setInputProtected(true);
        scrnMsg.psnNum_LK.clear();
        // 2016/05/11 S21_NA#7861 Mod End
        scrnMsg.slsRepTocNm.setInputProtected(true);
        scrnMsg.slsRepTocNm_LK.clear();
        scrnMsg.prcCatgNm.setInputProtected(true);
        scrnMsg.prcCatgNm_LK.clear();
        scrnMsg.custIssPoNum.setInputProtected(true);
        scrnMsg.leaseCmpyPoNum.setInputProtected(true);
        scrnMsg.custIssPoDt.setInputProtected(true);
        scrnMsg.aquNum.setInputProtected(true);
        // QC#1692
        scrnMsg.ordLogTpCd.setInputProtected(true);
        scrnMsg.ordLogTpCd_LK.clear();
        scrnMsg.ordLogTpDescTxt_NM.setInputProtected(true);
        scrnMsg.xxInvCmntTxt.setInputProtected(true);
        scrnMsg.invCmntTxt.setInputProtected(true);
        handler.setButtonEnabled(BTN_SPESIAL_INS_EVENT_NM, false);
        scrnMsg.xxScrItem54Txt_CB.setInputProtected(true);
        scrnMsg.xxScrItem54Txt_CE.setInputProtected(true);
        scrnMsg.cpoSrcTpDescTxt.setInputProtected(true);

        // Addl Header Tab
        scrnMsg.frtCondDescTxt.setInputProtected(true);
        scrnMsg.frtCondDescTxt_LK.clear();
        scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
        scrnMsg.carrSvcLvlDescTxt_LK.clear();
        scrnMsg.spclHdlgTpCd.setInputProtected(true);
        scrnMsg.shpgSvcLvlCd.setInputProtected(true);
        scrnMsg.carrAcctNum.setInputProtected(true);
        scrnMsg.pmtTermCashDiscDescTxt.setInputProtected(true);
        scrnMsg.pmtTermCashDiscDescTxt_LK.clear();
        scrnMsg.dsPmtMethCd.setInputProtected(true);
        scrnMsg.prePmtChkNum.setInputProtected(true);
        scrnMsg.prePmtAmt.setInputProtected(true);
        scrnMsg.prePmtTpCd.setInputProtected(true);
        scrnMsg.addRddDt.setInputProtected(true);
        scrnMsg.prcContrNum.setInputProtected(true);
        scrnMsg.prcContrNum_LK.clear();
        scrnMsg.prcContrNm.setInputProtected(true);
        scrnMsg.prcContrNm_LK.clear();
        scrnMsg.flPrcListNm.setInputProtected(true);
        scrnMsg.flPrcListDescTxt_LK.clear();
        scrnMsg.loanPerDaysAot.setInputProtected(true);
        scrnMsg.csmpContrNum.setInputProtected(true);
        scrnMsg.csmpContrNum_LK.clear();
        scrnMsg.dlrRefNum.setInputProtected(true);
        scrnMsg.dlrRefNum_LK.clear();
        scrnMsg.ordSgnDt.setInputProtected(true);
        scrnMsg.dclnSvcCd.setInputProtected(true);  // S21_NA#8388 ADD
        scrnMsg.leasePrchOptCd.setInputProtected(true);
        scrnMsg.leaseTermMthAot.setInputProtected(true);
        scrnMsg.leasePmtFreqCd.setInputProtected(true);
        scrnMsg.leaseTotPmtAmt.setInputProtected(true);
        scrnMsg.leaseTermMthAot_EM.setInputProtected(true); // QC#22789 2017/11/28 Add
        // 2017/11/02 S21_NA#20146 Add Start
        scrnMsg.psnNum_GS.setInputProtected(true);
        scrnMsg.tocNm_GS.setInputProtected(true);
        scrnMsg.coaExtnCd_GS.setInputProtected(true);
        scrnMsg.coaExtnDescTxt_GS.setInputProtected(true);
        scrnMsg.coaBrCd_GS.setInputProtected(true);
        scrnMsg.coaBrDescTxt_GS.setInputProtected(true);
        scrnMsg.coaCcCd_GS.setInputProtected(true);
        scrnMsg.coaCcDescTxt_GS.setInputProtected(true);
        // 2017/11/02 S21_NA#20146 Add End
        scrnMsg.cratTsDplyTxt.setInputProtected(true);
        scrnMsg.cratUsrNm.setInputProtected(true);
        scrnMsg.updTsDplyTxt.setInputProtected(true);
        scrnMsg.updUsrNm.setInputProtected(true);
        scrnMsg.ordBookReqTsDplyTxt.setInputProtected(true); // 2016/09/20 S21_NA#6100-2 Add
        scrnMsg.ordBookReqUsrNm.setInputProtected(true); // 2016/09/20 S21_NA#6100-2 Add
        scrnMsg.ordBookTsDplyTxt.setInputProtected(true);
        handler.setButtonEnabled(BTN_CREDITCARD_EVENT_NM, false);

        // Line Config Tab (Common Button)
        NWAL1500CommonLogic.activeAddButton(handler);
        handler.setButtonEnabled(BTN_LINE_CANCEL_EVENT_NM, false);
        handler.setButtonEnabled(BTN_COPYLINE_EVENT_NM, false);
        handler.setButtonEnabled(BTN_COPYFROM_EVENT_NM, false);
        handler.setButtonEnabled(BTN_AUTO_ADD_SUPPLY_EVENT_NM, false);
        handler.setButtonEnabled(BTN_MASSUPDATE_EVENT_NM, false);
        handler.setButtonEnabled(BTN_STC_OVER_VIEW_EVENT_NM, false);
        handler.setButtonEnabled(BTN_METERENTRY_EVENT_NM, false);

        // RMA Tab (Common Button)
        handler.setButtonEnabled(BTN_LINE_CANCEL_EVENT_NM, false);
        handler.setButtonEnabled(BTN_COPYLINE_EVENT_NM, false);
        handler.setButtonEnabled(BTN_COPYFROM_EVENT_NM, false);
        handler.setButtonEnabled(BTN_AUTO_ADD_RMA_EVENT_NM, false);
        handler.setButtonEnabled(BTN_MASSUPDATE_EVENT_NM, false);

        // Line Config Tab (Config)
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1500_ABMsg configMsg = scrnMsg.A.no(i);
            // 2017/10/13 S21_NA#21267 Mod Start
//            configMsg.xxChkBox_LC.setInputProtected(true);
            if (isOrderCredit) {
                configMsg.xxChkBox_LC.setInputProtected(false);
            } else {
                configMsg.xxChkBox_LC.setInputProtected(true);
            }
            // 2017/10/13 S21_NA#21267 Mod End
            configMsg.configTpCd_LC.setInputProtected(true);
            configMsg.mdlNm_LC.setInputProtected(true);
            configMsg.svcConfigMstrPk_LC.setInputProtected(true);
            configMsg.xxImageTxt_AC.clear();
            configMsg.mdlDescTxt_LC.setInputProtected(true);

            configMsg.shipToCustLocAddr_LC.setInputProtected(true);
            configMsg.shipToCustAcctCd_LC.setInputProtected(true);
            configMsg.xxImageTxt_AA.clear();
            configMsg.shipToCustCd_LC.setInputProtected(true);
            configMsg.xxImageTxt_AS.clear();
            configMsg.shipToCustAcctNm_LC.setInputProtected(true);
            configMsg.xxImageTxt_AN.clear();

            configMsg.billToCustLocAddr_LC.setInputProtected(true);
            configMsg.billToCustAcctCd_LC.setInputProtected(true);
            configMsg.xxImageTxt_AT.clear();
            configMsg.billToCustCd_LC.setInputProtected(true);
            configMsg.xxImageTxt_AB.clear();
            configMsg.billToCustAcctNm_LC.setInputProtected(true);
            configMsg.xxImageTxt_AM.clear();

            configMsg.soldToCustLocAddr_LC.setInputProtected(true);
            configMsg.sellToCustCd_LC.setInputProtected(true);
            configMsg.soldToCustLocCd_LC.setInputProtected(true);
            configMsg.soldToCustAcctNm_LC.setInputProtected(true);

            configMsg.mdlGrpDescTxt_LC.setInputProtected(true);
            configMsg.mdlDescTxt_LC.setInputProtected(true);
            configMsg.svcSegDescTxt_LC.setInputProtected(true);
            configMsg.cratUsrNm_LC.setInputProtected(true);
            configMsg.updUsrNm_LC.setInputProtected(true);
            configMsg.dropShipFlg_LC.setInputProtected(true);

            configMsg.dclnSvcCd_LC.setInputProtected(true); // S21_NA#8388 ADD
            configMsg.prcCatgNm_LC.setInputProtected(true); // 2016/08/26 S21_NA#9806 Add Start
            configMsg.xxImageTxt_AD.clear(); // 2018/11/16 S21_NA#27299 Add

            // 2019/07/11 S21_NA#51287 Add Start
            configMsg.csmpContrNum_LC.setInputProtected(true);
            configMsg.dlrRefNum_LC.setInputProtected(true);
            // 2019/07/11 S21_NA#51287 Add End
        }

        // Line Config Tab (Line)
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
            // 2017/10/13 S21_NA#21267 Mod Start
//            lineMsg.xxChkBox_LL.setInputProtected(true);
            if (isOrderCredit) {
                lineMsg.xxChkBox_LL.setInputProtected(false);
            } else {
                lineMsg.xxChkBox_LL.setInputProtected(true);
            }
            // 2017/10/13 S21_NA#21267 Mod End
            lineMsg.mdseCd_LL.setInputProtected(true);
            lineMsg.mdseDescShortTxt_LL.setInputProtected(true); // S21_NA#2334 Add
            lineMsg.xxImageTxt_BI.clear();
            lineMsg.ordCustUomQty_LL.setInputProtected(true);
            lineMsg.custUomCd_LL.setInputProtected(true);
            lineMsg.entCpoDtlDealSlsAmt_LL.setInputProtected(true);
            lineMsg.prcCatgNm_LL.setInputProtected(true);
            lineMsg.xxImageTxt_BP.clear();
            lineMsg.prcListEquipConfigNum_LL.setInputProtected(true);
            lineMsg.dsOrdLineCatgCd_LL.setInputProtected(true);
            lineMsg.ordLineSrcCd_LL.setInputProtected(true);
            lineMsg.rtlWhNm_LL.setInputProtected(true);
            lineMsg.rtlSwhNm_LL.setInputProtected(true);
            lineMsg.xxImageTxt_BW.clear();
            // QC#1615
            lineMsg.xxImageTxt_BU.clear();
            lineMsg.serNum_LL.setInputProtected(true);
            lineMsg.supdLockFlg_LL.setInputProtected(true);
            lineMsg.sbstMdseCd_LL.setInputProtected(true);
            lineMsg.xxImageTxt_BS.clear();
            lineMsg.vndInvtyLocCd_LL.setInputProtected(true);
            lineMsg.flPrcListNm_LL.setInputProtected(true);
            lineMsg.xxImageTxt_BF.clear();
            lineMsg.funcUnitFlPrcAmt_LL.setInputProtected(true); // QC#22372 2018/01/10 Add
            lineMsg.dplyLineRefNum_LL.setInputProtected(true);
            lineMsg.prcBaseDt_LL.setInputProtected(true);
            lineMsg.rddDt_LL.setInputProtected(true);

            lineMsg.custMdseCd_LL.setInputProtected(true);
            lineMsg.cratTsDplyTxt_LL.setInputProtected(true);
            lineMsg.ordBookTsDplyTxt_LL.setInputProtected(true);
            lineMsg.shipDtTsDplyTxt_LL.setInputProtected(true);
            lineMsg.schdDelyTsDplyTxt_LL.setInputProtected(true);
            lineMsg.cancRsnDescTxt_LL.setInputProtected(true);
            lineMsg.custIssPoNum_LL.setInputProtected(true);
            lineMsg.cpoSrcTpDescTxt_LL.setInputProtected(true);
            lineMsg.ordSrcRefNum_LL.setInputProtected(true);
            lineMsg.lineSrcRefNum_LL.setInputProtected(true);
            lineMsg.setPrntLineNum_LL.setInputProtected(true);
            lineMsg.cmpsnRevnNum_LL.setInputProtected(true);
            lineMsg.setMdseCd_LL.setInputProtected(true);
            lineMsg.cratUsrNm_LL.setInputProtected(true);
            lineMsg.cratTsDplyTxt_LL.setInputProtected(true);
            lineMsg.updUsrNm_LL.setInputProtected(true);
            lineMsg.updTsDplyTxt_LL.setInputProtected(true);
            lineMsg.prcListEquipConfigNm_LL.setInputProtected(true);
            lineMsg.mdseItemClsTpDescTxt_LL.setInputProtected(true);
        }

        // RMA Tab (Config)
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NWAL1500_CBMsg configMsg = scrnMsg.C.no(i);

            configMsg.xxChkBox_RC.setInputProtected(true);
            configMsg.configTpCd_RC.setInputProtected(true);
            configMsg.mdlNm_RC.setInputProtected(true);
            configMsg.svcConfigMstrPk_RC.setInputProtected(true);
            configMsg.xxImageTxt_CC.clear();
            configMsg.mdlDescTxt_RC.setInputProtected(true);

            configMsg.shipToCustLocAddr_RC.setInputProtected(true);
            configMsg.shipToCustAcctCd_RC.setInputProtected(true);
            configMsg.xxImageTxt_CA.clear();
            configMsg.shipToCustCd_RC.setInputProtected(true);
            configMsg.xxImageTxt_CS.clear();
            configMsg.shipToCustAcctNm_RC.setInputProtected(true);
            configMsg.xxImageTxt_CN.clear();

            configMsg.billToCustLocAddr_RC.setInputProtected(true);
            configMsg.billToCustAcctCd_RC.setInputProtected(true);
            configMsg.xxImageTxt_CT.clear();
            configMsg.billToCustCd_RC.setInputProtected(true);
            configMsg.xxImageTxt_CB.clear();
            configMsg.billToCustAcctNm_RC.setInputProtected(true);
            configMsg.xxImageTxt_CM.clear();

            configMsg.soldToCustLocAddr_RC.setInputProtected(true);
            configMsg.sellToCustCd_RC.setInputProtected(true);
            configMsg.soldToCustLocCd_RC.setInputProtected(true);
            configMsg.soldToCustAcctNm_RC.setInputProtected(true);

            configMsg.mdlGrpDescTxt_RC.setInputProtected(true);
            configMsg.mdlDescTxt_RC.setInputProtected(true);
            configMsg.svcSegDescTxt_RC.setInputProtected(true);
            configMsg.cratUsrNm_RC.setInputProtected(true);
            configMsg.updUsrNm_RC.setInputProtected(true);
            configMsg.dropShipFlg_RC.setInputProtected(true);
            configMsg.prcCatgNm_RC.setInputProtected(true); // 2016/08/26 S21_NA#9806 Add

            // 2019/07/11 S21_NA#51287 Add Start
            configMsg.csmpContrNum_RC.setInputProtected(true);
            configMsg.dlrRefNum_RC.setInputProtected(true);
            // 2019/07/11 S21_NA#51287 Add End
        }

        // RMA Tab (Line)
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NWAL1500_DBMsg lineMsg = scrnMsg.D.no(i);
            lineMsg.xxChkBox_RL.setInputProtected(true);
            lineMsg.mdseCd_RL.setInputProtected(true);
            lineMsg.mdseNm_RL.setInputProtected(true);
            lineMsg.mdseDescShortTxt_RL.setInputProtected(true); // S21_NA#2334 Add
            lineMsg.xxImageTxt_DI.setInputProtected(true);
            lineMsg.ordCustUomQty_RL.setInputProtected(true);
            lineMsg.rtrnRsnCd_RL.setInputProtected(true);
            lineMsg.entCpoDtlDealSlsAmt_RL.setInputProtected(true);
            lineMsg.rtrnLineStsDescTxt_RL.setInputProtected(true);
            lineMsg.dsOrdLineCatgCd_RL.setInputProtected(true);
            lineMsg.rtlWhNm_RL.setInputProtected(true);
            lineMsg.xxImageTxt_DW.setInputProtected(true);
            lineMsg.rtlSwhNm_RL.setInputProtected(true);
            // 2017/11/17 S21_NA#22252 Add Start
            lineMsg.xxImageTxt_DU.setInputProtected(true);
            // 2017/11/17 S21_NA#22252 Add End
            lineMsg.cancRsnDescTxt_RL.setInputProtected(true);
            lineMsg.custUomCd_RL.setInputProtected(true);
            lineMsg.prcCatgNm_RL.setInputProtected(true);
            lineMsg.xxImageTxt_DP.setInputProtected(true);
            lineMsg.flPrcListNm_RL.setInputProtected(true);
            lineMsg.xxImageTxt_DF.setInputProtected(true);
            lineMsg.funcUnitFlPrcAmt_RL.setInputProtected(true); // QC#22372 2018/01/10 Add
            lineMsg.serNum_RL.setInputProtected(true);
            lineMsg.custMdseCd_RL.setInputProtected(true);
            lineMsg.rqstPickUpDt_RL.setInputProtected(true);
            lineMsg.prcBaseDt_RL.setInputProtected(true);
            lineMsg.cancRsnDescTxt_RL.setInputProtected(true);
            lineMsg.coaMdseTpDescTxt_RL.setInputProtected(true);
            lineMsg.coaProdDescTxt_RL.setInputProtected(true);
            lineMsg.mdseItemClsTpDescTxt_RL.setInputProtected(true);
            lineMsg.dplyLineRefNum_RL.setInputProtected(true);
            lineMsg.custIssPoNum_RL.setInputProtected(true);
            lineMsg.cpoSrcTpDescTxt_RL.setInputProtected(true);
            lineMsg.ordSrcRefNum_RL.setInputProtected(true);
            lineMsg.lineSrcRefNum_RL.setInputProtected(true);
            lineMsg.setPrntLineNum_RL.setInputProtected(true);
            lineMsg.cmpsnRevnNum_RL.setInputProtected(true);
            lineMsg.setMdseCd_RL.setInputProtected(true);
            lineMsg.cratUsrNm_RL.setInputProtected(true);
            lineMsg.updUsrNm_RL.setInputProtected(true);
            lineMsg.hddRmvCd_RL.setInputProtected(true);
        }
    }

    /**
     * Set Header Item Protect [Header Status : Entered]
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setHdrItemProtectForEntered(NWAL1500BMsg scrnMsg) {

        scrnMsg.ordDt_DP.setInputProtected(true);
    }

    /**
     * Set Header Item Protect [Header Status : ValidationHold]
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setHdrItemProtectForValidationHold(NWAL1500BMsg scrnMsg) {

        scrnMsg.dsOrdCatgDescTxt.setInputProtected(true);
        scrnMsg.dsOrdCatgDescTxt_LK.clear();
        scrnMsg.dsOrdTpDescTxt.setInputProtected(true);
        scrnMsg.dsOrdTpDescTxt_LK.clear();
        // 2016/02/28 S21_NA#1738 Del Start
//        scrnMsg.dsOrdRsnCd.setInputProtected(true);
        // 2016/02/28 S21_NA#1738 Del End
        scrnMsg.ordDt_DP.setInputProtected(true);
    }

    // 2016/02/29 S21_NA#1738 Add Start
    /**
     * Set Header Item Protect [Header Status : ValidationHold]
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setHdrItemProtectForClosed(NWAL1500BMsg scrnMsg) {

        scrnMsg.dsOrdCatgDescTxt.setInputProtected(true);
        scrnMsg.dsOrdCatgDescTxt_LK.clear();
        scrnMsg.dsOrdTpDescTxt.setInputProtected(true);
        scrnMsg.dsOrdTpDescTxt_LK.clear();
        scrnMsg.dsOrdRsnCd.setInputProtected(true);
        scrnMsg.ordDt_DP.setInputProtected(true);
    }
    // 2016/02/29 S21_NA#1738 Add Start

    /**
     * Set Header Tab Item Protect [Header Status : ValidationHold]
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setHdrTabItemProtectForValidationHold(NWAL1500BMsg scrnMsg) {

        scrnMsg.billToCustAcctNm.setInputProtected(true);
        scrnMsg.billToCustAcctNm_LK.clear();
        scrnMsg.billToCustAcctCd.setInputProtected(true);
        scrnMsg.billToCustAcctCd_LK.clear();
        scrnMsg.shipToCustAcctNm.setInputProtected(true);
        scrnMsg.shipToCustAcctNm_LK.clear();
        scrnMsg.shipToCustAcctCd.setInputProtected(true);
        scrnMsg.shipToCustAcctCd_LK.clear();
        scrnMsg.soldToCustAcctNm.setInputProtected(true);
        scrnMsg.soldToCustAcctNm_LK.clear();
        scrnMsg.sellToCustCd.setInputProtected(true);
        scrnMsg.sellToCustCd_LK.clear();
        scrnMsg.negoDealAmt.setInputProtected(true);
        scrnMsg.prcCatgNm.setInputProtected(true);
        scrnMsg.prcCatgNm_LK.clear();
    }

    /**
     * Set Header Tab Item Protect [Header Status : Booked]
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setHdrTabItemProtectForBooked(NWAL1500BMsg scrnMsg) {

        scrnMsg.billToCustAcctNm.setInputProtected(true);
        scrnMsg.billToCustAcctNm_LK.clear();
        scrnMsg.billToCustAcctCd.setInputProtected(true);
        scrnMsg.billToCustAcctCd_LK.clear();
        scrnMsg.shipToCustAcctNm.setInputProtected(true);
        scrnMsg.shipToCustAcctNm_LK.clear();
        scrnMsg.shipToCustAcctCd.setInputProtected(true);
        scrnMsg.shipToCustAcctCd_LK.clear();
        scrnMsg.shipToCustCd.setInputProtected(true);
        scrnMsg.shipToCustCd_LK.clear();
        scrnMsg.soldToCustAcctNm.setInputProtected(true);
        scrnMsg.soldToCustAcctNm_LK.clear();
        scrnMsg.sellToCustCd.setInputProtected(true);
        scrnMsg.sellToCustCd_LK.clear();
        scrnMsg.soldToCustLocCd.setInputProtected(true);
        scrnMsg.soldToCustLocCd_LK.clear();
        scrnMsg.negoDealAmt.setInputProtected(true);
        scrnMsg.slsRepTocCd.setInputProtected(true);
        scrnMsg.slsRepTocCd_LK.clear();
        scrnMsg.slsRepTocNm.setInputProtected(true);
        scrnMsg.slsRepTocNm_LK.clear();
        // 2016/05/11 S21_NA#7861 Mod Start
//        scrnMsg.slsRepPsnCd.setInputProtected(true);
//        scrnMsg.slsRepPsnCd_LK.clear();
        scrnMsg.psnNum.setInputProtected(true);
        scrnMsg.psnNum_LK.clear();
        // 2016/05/11 S21_NA#7861 Mod End
        scrnMsg.prcCatgNm.setInputProtected(true);
        scrnMsg.prcCatgNm_LK.clear();
        scrnMsg.custIssPoNum.setInputProtected(true);
        scrnMsg.leaseCmpyPoNum.setInputProtected(true);
        scrnMsg.custIssPoDt.setInputProtected(true);
        scrnMsg.aquNum.setInputProtected(true);
        // QC#1692
        scrnMsg.ordLogTpCd.setInputProtected(true);
        scrnMsg.ordLogTpCd_LK.clear();
        scrnMsg.ordLogTpDescTxt_NM.setInputProtected(true);
    }

    /**
     * Set Header Tab Item Protect [Header Status : Closed]
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setHdrTabItemProtectForClosed(NWAL1500BMsg scrnMsg) {

        scrnMsg.billToCustAcctNm.setInputProtected(true);
        scrnMsg.billToCustAcctNm_LK.clear();
        scrnMsg.billToCustAcctCd.setInputProtected(true);
        scrnMsg.billToCustAcctCd_LK.clear();
        scrnMsg.billToCustCd.setInputProtected(true);
        scrnMsg.billToCustCd_LK.clear();
        scrnMsg.shipToCustAcctNm.setInputProtected(true);
        scrnMsg.shipToCustAcctNm_LK.clear();
        scrnMsg.shipToCustAcctCd.setInputProtected(true);
        scrnMsg.shipToCustAcctCd_LK.clear();
        scrnMsg.shipToCustCd.setInputProtected(true);
        scrnMsg.shipToCustCd_LK.clear();
        scrnMsg.sellToFirstRefCmntTxt.setInputProtected(true); // 2017/12/12 S21_NA#20164 Add
        scrnMsg.soldToCustAcctNm.setInputProtected(true);
        scrnMsg.soldToCustAcctNm_LK.clear();
        scrnMsg.sellToCustCd.setInputProtected(true);
        scrnMsg.sellToCustCd_LK.clear();
        scrnMsg.soldToCustLocCd.setInputProtected(true);
        scrnMsg.soldToCustLocCd_LK.clear();
        scrnMsg.negoDealAmt.setInputProtected(true);
        scrnMsg.slsRepTocCd.setInputProtected(true);
        scrnMsg.slsRepTocCd_LK.clear();
        scrnMsg.slsRepTocNm.setInputProtected(true);
        scrnMsg.slsRepTocNm_LK.clear();
        // 2016/05/11 S21_NA#7861 Mod End
//        scrnMsg.slsRepPsnCd.setInputProtected(true);
//        scrnMsg.slsRepPsnCd_LK.clear();
        scrnMsg.psnNum.setInputProtected(true);
        scrnMsg.psnNum_LK.clear();
        // 2016/05/11 S21_NA#7861 Mod End
        scrnMsg.prcCatgNm.setInputProtected(true);
        scrnMsg.prcCatgNm_LK.clear();
        scrnMsg.custIssPoNum.setInputProtected(true);
        scrnMsg.leaseCmpyPoNum.setInputProtected(true);
        scrnMsg.custIssPoDt.setInputProtected(true);
        scrnMsg.aquNum.setInputProtected(true);
        // QC#1692
        scrnMsg.ordLogTpCd.setInputProtected(true);
        scrnMsg.ordLogTpCd_LK.clear();
        scrnMsg.ordLogTpDescTxt_NM.setInputProtected(true);
        scrnMsg.xxInvCmntTxt.setInputProtected(true);
        scrnMsg.invCmntTxt.setInputProtected(true);
    }

    /**
     * Set Addl Tab Item Protect [Header Status : ValidationHold]
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setAddlTabItemProtectForValidationHold(NWAL1500BMsg scrnMsg) {

        scrnMsg.frtCondDescTxt.setInputProtected(true);
        scrnMsg.frtCondDescTxt_LK.clear();
        scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
        scrnMsg.carrSvcLvlDescTxt_LK.clear();
        scrnMsg.spclHdlgTpCd.setInputProtected(true);
        scrnMsg.shpgSvcLvlCd.setInputProtected(true);
        scrnMsg.prcContrNum.setInputProtected(true);
        scrnMsg.prcContrNum_LK.setInputProtected(true);
        scrnMsg.prcContrNm.setInputProtected(true);
        scrnMsg.prcContrNm_LK.setInputProtected(true);
        scrnMsg.flPrcListNm.setInputProtected(true);
        scrnMsg.flPrcListDescTxt_LK.clear();
        scrnMsg.csmpContrNum.setInputProtected(true);
        scrnMsg.csmpContrNum_LK.clear();
        scrnMsg.dlrRefNum.setInputProtected(true);
        scrnMsg.dlrRefNum_LK.clear();
    }

    // 2016/03/02 S21_NA#4377 Mod Start
//    /**
//     * Set Addl Tab Item Protect [Header Status : Booked]
//     * @param scrnMsg NWAL1500BMsg
//     */
//    private static void setAddlTabItemProtectForBooked(NWAL1500BMsg scrnMsg) {
//
//    }

    /**
     * Set Addl Tab Item Protect [Header Status : Closed/Cancelled]
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setAddlTabItemProtectForClosed(NWAL1500BMsg scrnMsg) {

        scrnMsg.frtCondDescTxt.setInputProtected(true);
        scrnMsg.frtCondDescTxt_LK.clear();
        scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
        scrnMsg.carrSvcLvlDescTxt_LK.clear();
        scrnMsg.spclHdlgTpCd.setInputProtected(true);
        scrnMsg.shpgSvcLvlCd.setInputProtected(true);
        scrnMsg.carrAcctNum.setInputProtected(true);
        scrnMsg.pmtTermCashDiscDescTxt.setInputProtected(true);
        scrnMsg.pmtTermCashDiscDescTxt_LK.clear();
        scrnMsg.dsPmtMethCd.setInputProtected(true);
        scrnMsg.prePmtChkNum.setInputProtected(true);
        scrnMsg.prePmtAmt.setInputProtected(true);
        scrnMsg.prePmtTpCd.setInputProtected(true);
        scrnMsg.addRddDt.setInputProtected(true);
        scrnMsg.prcContrNum.setInputProtected(true);
        scrnMsg.prcContrNum_LK.clear(); // 2016/10/13 S21_NA#7700 Mod
        scrnMsg.prcContrNm.setInputProtected(true);
        scrnMsg.prcContrNm_LK.clear(); // 2016/10/13 S21_NA#7700 Mod
        scrnMsg.flPrcListNm.setInputProtected(true);
        scrnMsg.flPrcListDescTxt_LK.clear();
        scrnMsg.loanPerDaysAot.setInputProtected(true);
        scrnMsg.csmpContrNum.setInputProtected(true);
        scrnMsg.csmpContrNum_LK.clear();
        scrnMsg.dlrRefNum.setInputProtected(true);
        scrnMsg.dlrRefNum_LK.clear();
        scrnMsg.ordSgnDt.setInputProtected(true);
        scrnMsg.dclnSvcCd.setInputProtected(true);  // S21_NA#8388 ADD
        scrnMsg.leasePrchOptCd.setInputProtected(true);
        scrnMsg.leaseTermMthAot.setInputProtected(true);
        scrnMsg.leasePmtFreqCd.setInputProtected(true);
        scrnMsg.leaseTotPmtAmt.setInputProtected(true);
        scrnMsg.leaseTermMthAot_EM.setInputProtected(true); // QC#22789 2017/11/28 Add
        // 2017/11/02 S21_NA#20146 Add Start
        scrnMsg.psnNum_GS.setInputProtected(true);
        scrnMsg.tocNm_GS.setInputProtected(true);
        scrnMsg.coaExtnCd_GS.setInputProtected(true);
        scrnMsg.coaExtnDescTxt_GS.setInputProtected(true);
        scrnMsg.coaBrCd_GS.setInputProtected(true);
        scrnMsg.coaBrDescTxt_GS.setInputProtected(true);
        scrnMsg.coaCcCd_GS.setInputProtected(true);
        scrnMsg.coaCcDescTxt_GS.setInputProtected(true);
        // 2017/11/02 S21_NA#20146 Add End
    }
    // 2016/03/02 S21_NA#4377 Mod End
    /**
     * Set Header Button Protect [Header Status : Unregistered]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setHdrBtnProtectForUnregistered(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_ORDER_CANCEL_EVENT_NM, false);
        handler.setButtonEnabled(BTN_HOLDS_EVENT_NM, false);
        handler.setButtonEnabled(BTN_DELIVERYINFO_EVENT_NM, false);
        handler.setButtonEnabled(BTN_SHIPPINGDETAIL_EVENT_NM, false);
        handler.setButtonEnabled(BTN_PROFITABILITY_EVENT_NM, false);
        handler.setButtonEnabled(BTN_DICHECK_EVENT_NM, false);
        handler.setButtonEnabled(BTN_SERVICEPRIC_EVENT_NM, false);
    }

    /**
     * Set Header Button Protect [Header Status : Booked]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setHdrBtnProtectForBooked(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_SPESIAL_INS_EVENT_NM, false);
    }

    /**
     * Set Header Button Protect [Header Status : Entered]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setHdrBtnProtectForEnterd(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_CHANGEORDERMOD_EVENT_NM, false);
    }

    /**
     * Set Header Button Protect [Header Status : Closed]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setHdrBtnProtectForClosed(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_ORDER_CANCEL_EVENT_NM, false);
        handler.setButtonEnabled(BTN_ORDERAMTCALC_EVENT_NM, false);
    }

    /**
     * Set Header Button Protect [Header Status : Cancelled]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setHdrBtnProtectForCancelled(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_ORDER_CANCEL_EVENT_NM, false);
        handler.setButtonEnabled(BTN_CHANGEORDERMOD_EVENT_NM, false);
        handler.setButtonEnabled(BTN_ORDERAMTCALC_EVENT_NM, false);
    }

    // 2016/10/03 S21_NA#13958 ADD START
    /**
     * Set Header Button Protect [Header Status : Closed]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setAddlHdrBtnProtectForClosed(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_CREDITCARD_EVENT_NM, true);
    }
    // 2016/10/03 S21_NA#13958 ADD END

    /**
     * Set Header Button Protect [Header Status : Unregistered]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setAddlHdrBtnProtectForUnregistered(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_CREDITCARD_EVENT_NM, false);
    }

    /**
     * Set Line Tab Button Protect [Header Status : Unregistered]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setLineTabBtnProtectForUnregistered(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_METERENTRY_EVENT_NM, false);
    }

    /**
     * Set Line Tab Button Protect [Header Status : ValidationHold]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setLineTabBtnProtectForValidationHold(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        NWAL1500CommonLogic.inactiveAddButton(handler);
        handler.setButtonEnabled(BTN_LINE_CANCEL_EVENT_NM, false);
        handler.setButtonEnabled(BTN_COPYLINE_EVENT_NM, false);
        handler.setButtonEnabled(BTN_COPYFROM_EVENT_NM, false);
        handler.setButtonEnabled(BTN_AUTO_ADD_SUPPLY_EVENT_NM, false);
        handler.setButtonEnabled(BTN_MASSUPDATE_EVENT_NM, false);
    }

    /**
     * Set Line Tab Button Protect [Header Status : Booked]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setLineTabBtnProtectForBooked(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_MASSUPDATE_EVENT_NM, false);
    }

    /**
     * Set Line Tab Button Protect [Header Status : Closed]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setLineTabBtnProtectForClosed(EZDCommonHandler handler, NWAL1500BMsg scrnMsg, boolean dwlFlg) {

        NWAL1500CommonLogic.inactiveAddButton(handler);
        handler.setButtonEnabled(BTN_LINE_CANCEL_EVENT_NM, false);
        handler.setButtonEnabled(BTN_COPYLINE_EVENT_NM, false);
        handler.setButtonEnabled(BTN_COPYFROM_EVENT_NM, false);
        handler.setButtonEnabled(BTN_AUTO_ADD_SUPPLY_EVENT_NM, false);
        handler.setButtonEnabled(BTN_MASSUPDATE_EVENT_NM, false);
        handler.setButtonEnabled(BTN_STC_OVER_VIEW_EVENT_NM, false);
        handler.setButtonEnabled(BTN_METERENTRY_EVENT_NM, false);
        // Add Start 2018/08/07 QC#23587
        if (dwlFlg) {
            handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 1, null);
        }
        // Add End 2018/08/07 QC#23587
    }

    /**
     * Set RMA Tab Button Protect [Header Status : ValidationHold]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setRmaTabBtnProtectForValidationHold(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        NWAL1500CommonLogic.inactiveAddButton(handler);
        handler.setButtonEnabled(BTN_LINE_CANCEL_EVENT_NM, false);
        handler.setButtonEnabled(BTN_COPYLINE_EVENT_NM, false);
        handler.setButtonEnabled(BTN_COPYFROM_EVENT_NM, false);
        handler.setButtonEnabled(BTN_AUTO_ADD_RMA_EVENT_NM, false);
        handler.setButtonEnabled(BTN_MASSUPDATE_EVENT_NM, false);
    }

    /**
     * Set RMA Tab Button Protect [Header Status : Booked]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setRmaTabBtnProtectForBooked(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_MASSUPDATE_EVENT_NM, false);
    }

    /**
     * Set RMA Tab Button Protect [Header Status : Closed]
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setRmaTabBtnProtectForClosed(EZDCommonHandler handler, NWAL1500BMsg scrnMsg, boolean dwlFlg) {

        NWAL1500CommonLogic.inactiveAddButton(handler);
        handler.setButtonEnabled(BTN_LINE_CANCEL_EVENT_NM, false);
        handler.setButtonEnabled(BTN_COPYLINE_EVENT_NM, false);
        handler.setButtonEnabled(BTN_COPYFROM_EVENT_NM, false);
        handler.setButtonEnabled(BTN_AUTO_ADD_RMA_EVENT_NM, false);
        handler.setButtonEnabled(BTN_MASSUPDATE_EVENT_NM, false);
        // Add Start 2018/08/07 QC#23587
        if (dwlFlg) {
            handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 1, null);
        }
        // Add End 2018/08/07 QC#23587
    }

    /**
     * Set Config Item Protect [Config Status : Entered]
     * @param confMsg NWAL1500_ABMsg
     */
    public static void setConfItemProtectForEntered(NWAL1500_ABMsg confMsg) {
        // S21_NA#6312
        boolean isProtect = ZYPConstant.FLG_ON_Y.equals(confMsg.configNewFlg_LC.getValue());
        confMsg.svcConfigMstrPk_LC.setInputProtected(isProtect);
        confMsg.xxImageTxt_AC.setInputProtected(isProtect);
        if (isProtect) {
            confMsg.xxImageTxt_AC.clear();
        } else {
            confMsg.xxImageTxt_AC.setValue(ZYPConstant.FLG_ON_Y);
        }

        // 2016/02/28 S21_NA#1738 Del Start
        // confMsg.configTpCd_LC.setInputProtected(true);
        // confMsg.svcConfigMstrPk_LC.setInputProtected(true);
        // confMsg.xxImageTxt_AC.clear();
        // 2016/02/28 S21_NA#1738 Del End
    }

    // 2016/03/01 S21_NA#1738 Add Start
    /**
     * Set Config Item Protect [Config Status : Booked]
     * @param confMsg NWAL1500_ABMsg
     */
    private static void setConfItemProtectForBooked(NWAL1500_ABMsg confMsg) {

        confMsg.configTpCd_LC.setInputProtected(true);
        confMsg.svcConfigMstrPk_LC.setInputProtected(true);
        confMsg.xxImageTxt_AC.clear();
    }
    // 2016/03/01 S21_NA#1738 Add End

    /**
     * Set Config Item Protect [Config Status : Waiting Pick]
     * @param confMsg NWAL1500_ABMsg
     */
    private static void setConfItemProtectForWaitingPick(NWAL1500_ABMsg confMsg) {

        confMsg.configTpCd_LC.setInputProtected(true);
        confMsg.svcConfigMstrPk_LC.setInputProtected(true);
        confMsg.xxImageTxt_AC.clear();
        // 2018/06/19 QC#20154 Del Start
        //// 2016/03/01 S21_NA#1738 Add Start
        //confMsg.shipToCustAcctCd_LC.setInputProtected(true);
        //confMsg.xxImageTxt_AA.clear();
        //confMsg.shipToCustAcctNm_LC.setInputProtected(true);
        //confMsg.xxImageTxt_AN.clear();
        //// 2016/03/01 S21_NA#1738 Add End
        //confMsg.shipToCustCd_LC.setInputProtected(true);
        //confMsg.xxImageTxt_AS.clear();
        //confMsg.xxImageTxt_AD.clear();
        // 2018/06/19 QC#20154 Del End
    }

    /**
     * Set Config Item Protect [Config Status : Shipped Closed]
     * @param confMsg NWAL1500_ABMsg
     */
    private static void setConfItemProtectForShippedClosed(NWAL1500_ABMsg confMsg) {

        confMsg.configTpCd_LC.setInputProtected(true);
        confMsg.svcConfigMstrPk_LC.setInputProtected(true);
        confMsg.xxImageTxt_AC.clear();
        // 2016/03/01 S21_NA#1738 Add Start
        confMsg.shipToCustAcctCd_LC.setInputProtected(true);
        confMsg.xxImageTxt_AA.clear();
        confMsg.shipToCustAcctNm_LC.setInputProtected(true);
        confMsg.xxImageTxt_AN.clear();
        // 2016/03/01 S21_NA#1738 Add End
        confMsg.shipToCustCd_LC.setInputProtected(true);
        confMsg.xxImageTxt_AS.clear();
        confMsg.xxImageTxt_AD.clear();
        // 2016/03/01 S21_NA#1738 Add Start
        confMsg.billToCustAcctCd_LC.setInputProtected(true);
        confMsg.xxImageTxt_AT.clear();
        confMsg.billToCustAcctNm_LC.setInputProtected(true);
        confMsg.xxImageTxt_AM.clear();
        // 2016/03/01 S21_NA#1738 Add End
        confMsg.billToCustCd_LC.setInputProtected(true);
        confMsg.xxImageTxt_AB.clear();
        // S21_NA#8388 ADD
        confMsg.dclnSvcCd_LC.setInputProtected(true);
        // 2016/08/26 S21_NA#9806 Add Start
        confMsg.csmpContrNum_LC.setInputProtected(true);
        confMsg.xxImageTxt_LN.clear();
        confMsg.dlrRefNum_LC.setInputProtected(true);
        confMsg.xxImageTxt_LD.clear();
        // 2016/08/26 S21_NA#9806 Add End
    }

    /**
     * Set RMA Config Item Protect [Config Status : Entered]
     * @param rmaConfMsg NWAL1500_CBMsg
     */
    public static void setRmaConfItemProtectForEntered(NWAL1500_CBMsg rmaConfMsg) {
        // S21_NA#6312
        boolean isProtect = ZYPConstant.FLG_ON_Y.equals(rmaConfMsg.configNewFlg_RC.getValue());
        rmaConfMsg.svcConfigMstrPk_RC.setInputProtected(isProtect);
        rmaConfMsg.xxImageTxt_CC.setInputProtected(isProtect);
        if (isProtect) {
            rmaConfMsg.xxImageTxt_CC.clear();
        } else {
            rmaConfMsg.xxImageTxt_CC.setValue(ZYPConstant.FLG_ON_Y);
        }

        // 2016/02/28 S21_NA#1738 Del Start
//        rmaConfMsg.configTpCd_RC.setInputProtected(true);
//        rmaConfMsg.svcConfigMstrPk_RC.setInputProtected(true);
//        rmaConfMsg.xxImageTxt_CC.clear();
        // 2016/02/28 S21_NA#1738 Del End
    }

    // 2016/03/01 S21_NA#1738 Add Start
    /**
     * Set RMA Config Item Protect [Config Status : Booked]
     * @param rmaConfMsg NWAL1500_CBMsg
     */
    private static void setRmaConfItemProtectForBooked(NWAL1500_CBMsg rmaConfMsg) {

        rmaConfMsg.configTpCd_RC.setInputProtected(true);
        rmaConfMsg.svcConfigMstrPk_RC.setInputProtected(true);
        rmaConfMsg.xxImageTxt_CC.clear();
    }

    // 2016/03/01 S21_NA#1738 Add End

    /**
     * Set RMA Config Item Protect [Config Status : Pending Return]
     * @param rmaConfMsg NWAL1500_CBMsg
     */
    private static void setRmaConfItemProtectForPendingReturn(NWAL1500_CBMsg rmaConfMsg) {

        rmaConfMsg.configTpCd_RC.setInputProtected(true);
        rmaConfMsg.svcConfigMstrPk_RC.setInputProtected(true);
        rmaConfMsg.xxImageTxt_CC.clear();
        // 2016/03/01 S21_NA#1738 Add Start
        rmaConfMsg.shipToCustAcctCd_RC.setInputProtected(true);
        rmaConfMsg.xxImageTxt_CA.clear();
        rmaConfMsg.shipToCustAcctNm_RC.setInputProtected(true);
        rmaConfMsg.xxImageTxt_CN.clear();
        // 2016/03/01 S21_NA#1738 Add End
        rmaConfMsg.shipToCustCd_RC.setInputProtected(true);
        rmaConfMsg.xxImageTxt_CS.clear();
        rmaConfMsg.xxImageTxt_CD.clear();
    }

    /**
     * Set RMA Config Item Protect [Config Status : Closed]
     * @param rmaConfMsg NWAL1500_CBMsg
     */
    private static void setRmaConfItemProtectForClosed(NWAL1500_CBMsg rmaConfMsg) {

        rmaConfMsg.configTpCd_RC.setInputProtected(true);
        rmaConfMsg.svcConfigMstrPk_RC.setInputProtected(true);
        rmaConfMsg.xxImageTxt_CC.clear();
        // 2016/03/01 S21_NA#1738 Add Start
        rmaConfMsg.shipToCustAcctCd_RC.setInputProtected(true);
        rmaConfMsg.xxImageTxt_CA.clear();
        rmaConfMsg.shipToCustAcctNm_RC.setInputProtected(true);
        rmaConfMsg.xxImageTxt_CN.clear();
        // 2016/03/01 S21_NA#1738 Add End
        rmaConfMsg.shipToCustCd_RC.setInputProtected(true);
        rmaConfMsg.xxImageTxt_CS.clear();
        rmaConfMsg.xxImageTxt_CD.clear();
        // 2016/03/01 S21_NA#1738 Add Start
        rmaConfMsg.billToCustAcctCd_RC.setInputProtected(true);
        rmaConfMsg.xxImageTxt_CT.clear();
        rmaConfMsg.billToCustAcctNm_RC.setInputProtected(true);
        rmaConfMsg.xxImageTxt_CM.clear();
        // 2016/03/01 S21_NA#1738 Add End
        rmaConfMsg.billToCustCd_RC.setInputProtected(true);
        rmaConfMsg.xxImageTxt_CB.clear();
        // 2016/08/26 S21_NA#9806 Add Start
        rmaConfMsg.csmpContrNum_RC.setInputProtected(true);
        rmaConfMsg.xxImageTxt_RN.clear();
        rmaConfMsg.dlrRefNum_RC.setInputProtected(true);
        rmaConfMsg.xxImageTxt_RD.clear();
        // 2016/08/26 S21_NA#9806 Add End
    }

    /**
     * Set Line Item Protect [Line Status : Entered]
     * @param lineMsg NWAL1500_BBMsg
     */
    private static void setLineItemProtectForEntered(NWAL1500_BBMsg lineMsg) {
        // 2023/02/07 QC#61010 Add Start
        if (ZYPConstant.FLG_OFF_N.equals(lineMsg.serTakeFlg_LL.getValue())) {
            lineMsg.serNum_LL.setInputProtected(true);
        } else if (ZYPConstant.FLG_ON_Y.equals(lineMsg.serTakeFlg_LL.getValue())) {
            lineMsg.serNum_LL.setInputProtected(false);
        }
        // 2023/02/07 QC#61010 Add End
        // 2016/02/28 S21_NA#1738 Del Start
//        lineMsg.mdseCd_LL.setInputProtected(true);
//        lineMsg.xxImageTxt_BI.clear();
//        lineMsg.supdLockFlg_LL.setInputProtected(true);
        // 2016/02/28 S21_NA#1738 Del End
    }

    /**
     * Set Line Item Protect [Line Status : ValidationHold]
     * @param lineMsg NWAL1500_BBMsg
     */
    private static void setLineItemProtectForValidationHold(NWAL1500_BBMsg lineMsg) {

        lineMsg.mdseCd_LL.setInputProtected(true);
        lineMsg.xxImageTxt_BI.clear();
        // 2023/02/07 QC#61010 Add Start
        if (ZYPConstant.FLG_OFF_N.equals(lineMsg.serTakeFlg_LL.getValue())) {
            lineMsg.serNum_LL.setInputProtected(true);
        } else if (ZYPConstant.FLG_ON_Y.equals(lineMsg.serTakeFlg_LL.getValue())) {
            lineMsg.serNum_LL.setInputProtected(false);
        }
        // 2023/02/07 QC#61010 Add End
        // 2016/02/28 S21_NA#1738 Del Start
//        lineMsg.ordCustUomQty_LL.setInputProtected(true);
//        lineMsg.custUomCd_LL.setInputProtected(true);
//        lineMsg.entCpoDtlDealSlsAmt_LL.setInputProtected(true);
//        lineMsg.prcCatgNm_LL.setInputProtected(true);
//        lineMsg.xxImageTxt_BP.clear();
//        lineMsg.prcListEquipConfigNum_LL.setInputProtected(true);
//        lineMsg.dsOrdLineCatgCd_LL.setInputProtected(true);
//        lineMsg.supdLockFlg_LL.setInputProtected(true);
//        lineMsg.sbstMdseCd_LL.setInputProtected(true);
//        lineMsg.xxImageTxt_BS.clear();
//        lineMsg.flPrcListNm_LL.setInputProtected(true);
//        lineMsg.xxImageTxt_BF.clear();
//        lineMsg.prcBaseDt_LL.setInputProtected(true);
        // 2016/02/28 S21_NA#1738 Del End
    }

    /**
     * Set Line Item Protect [Line Status : SO Cancelled]
     * @param lineMsg NWAL1500_BBMsg
     */
    private static void setLineItemProtectForSoCancelled(NWAL1500_BBMsg lineMsg) {

        lineMsg.mdseCd_LL.setInputProtected(true);
        lineMsg.xxImageTxt_BI.clear();
        // 2016/02/28 S21_NA#1738 Del Start
//        lineMsg.ordCustUomQty_LL.setInputProtected(true);
//        lineMsg.custUomCd_LL.setInputProtected(true);
//        lineMsg.entCpoDtlDealSlsAmt_LL.setInputProtected(true);
//        lineMsg.prcCatgNm_LL.setInputProtected(true);
//        lineMsg.xxImageTxt_BP.clear();
//        lineMsg.prcListEquipConfigNum_LL.setInputProtected(true);
//        lineMsg.dsOrdLineCatgCd_LL.setInputProtected(true);
//        lineMsg.supdLockFlg_LL.setInputProtected(true);
//        lineMsg.flPrcListNm_LL.setInputProtected(true);
//        lineMsg.xxImageTxt_BF.clear();
//        lineMsg.prcBaseDt_LL.setInputProtected(true);
        // 2016/02/28 S21_NA#1738 Del End
    }

    /**
     * Set Line Item Protect [Line Status : Waiting Pick]
     * @param lineMsg NWAL1500_BBMsg
     */
    private static void setLineItemProtectForWaitingPick(NWAL1500_BBMsg lineMsg) {

        lineMsg.mdseCd_LL.setInputProtected(true);
        lineMsg.xxImageTxt_BI.clear();
        lineMsg.ordCustUomQty_LL.setInputProtected(true);
        lineMsg.custUomCd_LL.setInputProtected(true);
        // 2016/02/28 S21_NA#1738 Del Start
//        lineMsg.entCpoDtlDealSlsAmt_LL.setInputProtected(true);
//        lineMsg.prcCatgNm_LL.setInputProtected(true);
//        lineMsg.xxImageTxt_BP.clear();
//        lineMsg.prcListEquipConfigNum_LL.setInputProtected(true);
        // Mod Start 2016/10/24 M.Ohno S21_NA#14937
        lineMsg.dsOrdLineCatgCd_LL.setInputProtected(true);
        // Mod End   2016/10/24 M.Ohno S21_NA#14937
        // 2016/02/28 S21_NA#1738 Del End
        lineMsg.ordLineSrcCd_LL.setInputProtected(true);
        lineMsg.rtlWhNm_LL.setInputProtected(true);
        lineMsg.rtlSwhNm_LL.setInputProtected(true);
        lineMsg.xxImageTxt_BW.clear();
        // QC#1615
        lineMsg.xxImageTxt_BU.clear();
        lineMsg.serNum_LL.setInputProtected(true);
        lineMsg.supdLockFlg_LL.setInputProtected(true);
        lineMsg.sbstMdseCd_LL.setInputProtected(true);
        lineMsg.xxImageTxt_BS.clear();
        lineMsg.vndInvtyLocCd_LL.setInputProtected(true);
        // 2016/02/28 S21_NA#1738 Del Start
//        lineMsg.flPrcListNm_LL.setInputProtected(true);
//        lineMsg.xxImageTxt_BF.clear();
//        lineMsg.prcBaseDt_LL.setInputProtected(true);
        // 2016/02/28 S21_NA#1738 Del End
        lineMsg.rddDt_LL.setInputProtected(true);
    }

    /**
     * Set Line Item Protect [Line Status : Shipped Closed]
     * @param lineMsg NWAL1500_BBMsg
     */
    private static void setLineItemProtectForShippedClosed(NWAL1500_BBMsg lineMsg) {

        lineMsg.mdseCd_LL.setInputProtected(true);
        lineMsg.xxImageTxt_BI.clear();
        lineMsg.ordCustUomQty_LL.setInputProtected(true);
        lineMsg.custUomCd_LL.setInputProtected(true);
        lineMsg.entCpoDtlDealSlsAmt_LL.setInputProtected(true);
        lineMsg.prcCatgNm_LL.setInputProtected(true);
        lineMsg.xxImageTxt_BP.clear();
        lineMsg.prcListEquipConfigNum_LL.setInputProtected(true);
        lineMsg.dsOrdLineCatgCd_LL.setInputProtected(true);
        lineMsg.ordLineSrcCd_LL.setInputProtected(true);
        lineMsg.rtlWhNm_LL.setInputProtected(true);
        lineMsg.rtlSwhNm_LL.setInputProtected(true);
        lineMsg.xxImageTxt_BW.clear();        // QC#1615
        lineMsg.xxImageTxt_BU.clear();
        lineMsg.serNum_LL.setInputProtected(true);
        lineMsg.supdLockFlg_LL.setInputProtected(true);
        lineMsg.sbstMdseCd_LL.setInputProtected(true);
        // 2018/12/18 QC#29285 Add Start
        lineMsg.custMdseCd_LL.setInputProtected(true);
        // 2018/12/18 QC#29285 Add End
        lineMsg.xxImageTxt_BS.clear();
        lineMsg.vndInvtyLocCd_LL.setInputProtected(true);
        lineMsg.flPrcListNm_LL.setInputProtected(true);
        lineMsg.funcUnitFlPrcAmt_LL.setInputProtected(true); // QC#22372 2018/01/10 Add
        lineMsg.xxImageTxt_BF.clear();
        lineMsg.dplyLineRefNum_LL.setInputProtected(true);
        lineMsg.prcBaseDt_LL.setInputProtected(true);
        lineMsg.rddDt_LL.setInputProtected(true);
    }

    // Add Start 2019/05/07 QC#50015
    /**
     * Set Line Item Protect [Line Status : Logistics]
     * @param lineMsg NWAL1500_BBMsg
     * @param confStsNm String
     */
    private static void setLineItemProtectForLogistics(NWAL1500_BBMsg lineMsg, String confStsNm) {

        lineMsg.mdseCd_LL.setInputProtected(true);
        lineMsg.xxImageTxt_BI.clear();
        lineMsg.ordCustUomQty_LL.setInputProtected(true);
        lineMsg.custUomCd_LL.setInputProtected(true);
        lineMsg.entCpoDtlDealSlsAmt_LL.setInputProtected(true);
        lineMsg.prcCatgNm_LL.setInputProtected(true);
        lineMsg.xxImageTxt_BP.clear();
        lineMsg.prcListEquipConfigNum_LL.setInputProtected(true);
        lineMsg.dsOrdLineCatgCd_LL.setInputProtected(true);
        lineMsg.supdLockFlg_LL.setInputProtected(true);
        lineMsg.sbstMdseCd_LL.setInputProtected(true);
        lineMsg.custMdseCd_LL.setInputProtected(true);
        lineMsg.xxImageTxt_BS.clear();
        lineMsg.vndInvtyLocCd_LL.setInputProtected(true);
        lineMsg.flPrcListNm_LL.setInputProtected(true);
        lineMsg.funcUnitFlPrcAmt_LL.setInputProtected(true);
        lineMsg.xxImageTxt_BF.clear();
        lineMsg.dplyLineRefNum_LL.setInputProtected(true);
        lineMsg.prcBaseDt_LL.setInputProtected(true);
    }
    // Add End 2019/05/07 QC#50015

    /**
     * Set RMA Item Protect [RMA Line Status : Entered]
     * @param rmaLineMsg NWAL1500_DBMsg
     */
    private static void setRmaItemProtectForEntered(NWAL1500_DBMsg rmaLineMsg) {

        // 2016/02/28 S21_NA#1738 Del Start
//        rmaLineMsg.mdseCd_RL.setInputProtected(true);
//        rmaLineMsg.xxImageTxt_DI.clear();
        // 2016/02/28 S21_NA#1738 Del End
    }

    /**
     * Set RMA Item Protect [RMA Line Status : ValidationHold]
     * @param rmaLineMsg NWAL1500_DBMsg
     */
    private static void setRmaItemProtectForValidationHold(NWAL1500_DBMsg rmaLineMsg) {

        rmaLineMsg.mdseCd_RL.setInputProtected(true);
        rmaLineMsg.xxImageTxt_DI.clear();
        // 2016/02/28 S21_NA#1738 Del Start
//        rmaLineMsg.ordCustUomQty_RL.setInputProtected(true);
//        rmaLineMsg.custUomCd_RL.setInputProtected(true);
//        rmaLineMsg.entCpoDtlDealSlsAmt_RL.setInputProtected(true);
//        rmaLineMsg.prcCatgNm_RL.setInputProtected(true);
//        rmaLineMsg.xxImageTxt_DP.clear();
//        rmaLineMsg.dsOrdLineCatgCd_RL.setInputProtected(true);
//        rmaLineMsg.flPrcListNm_RL.setInputProtected(true);
//        rmaLineMsg.xxImageTxt_DF.clear();
//        rmaLineMsg.prcBaseDt_RL.setInputProtected(true);
//        rmaLineMsg.rtrnRsnCd_RL.setInputProtected(true);
        // 2016/02/28 S21_NA#1738 Del End
    }

    /**
     * Set RMA Item Protect [RMA Line Status : PendingReturn]
     * @param rmaLineMsg NWAL1500_DBMsg
     * @param rmaLineStsNm String
     * @param rwsOpenFlg String
     */
    // 2018/10/25 S21_NA#28897 Mod Start
//    private static void setRmaItemProtectForPendingReturn(NWAL1500_DBMsg rmaLineMsg) {
    private static void setRmaItemProtectForPendingReturn(NWAL1500_DBMsg rmaLineMsg, String rmaLineStsNm, String rwsOpenFlg) {
    // 2018/10/25 S21_NA#28897 Mod End

        rmaLineMsg.mdseCd_RL.setInputProtected(true);
        rmaLineMsg.xxImageTxt_DI.clear();
        rmaLineMsg.ordCustUomQty_RL.setInputProtected(true);
        rmaLineMsg.custUomCd_RL.setInputProtected(true);
        // 2016/02/28 S21_NA#1738 Del Start
//        rmaLineMsg.entCpoDtlDealSlsAmt_RL.setInputProtected(true);
//        rmaLineMsg.prcCatgNm_RL.setInputProtected(true);
//        rmaLineMsg.xxImageTxt_DP.clear();
//        rmaLineMsg.dsOrdLineCatgCd_RL.setInputProtected(true);
        // 2016/02/28 S21_NA#1738 Del End
        // 2018/10/25 S21_NA#28897 Mod Start
//        rmaLineMsg.rtlWhNm_RL.setInputProtected(true);
//        rmaLineMsg.xxImageTxt_DW.clear();
//        // 2017/11/17 S21_NA#22252 Add Start
//        rmaLineMsg.rtlSwhNm_RL.setInputProtected(true);
//        rmaLineMsg.xxImageTxt_DU.clear();
        if (!LINE_STS_NM_PENDING_RETURN.equals(rmaLineStsNm) || ZYPConstant.FLG_ON_Y.equals(rwsOpenFlg)) {
            rmaLineMsg.rtlWhNm_RL.setInputProtected(true);
            rmaLineMsg.xxImageTxt_DW.clear();
            rmaLineMsg.rtlSwhNm_RL.setInputProtected(true);
            rmaLineMsg.xxImageTxt_DU.clear();
        }
        // 2018/10/25 S21_NA#28897 Mod End
        // 2017/11/17 S21_NA#22252 Add End
        rmaLineMsg.serNum_RL.setInputProtected(true);
        // 2016/02/28 S21_NA#1738 Del Start
//        rmaLineMsg.flPrcListNm_RL.setInputProtected(true);
//        rmaLineMsg.xxImageTxt_DF.clear();
//        rmaLineMsg.prcBaseDt_RL.setInputProtected(true);
        // 2016/02/28 S21_NA#1738 Del End
        rmaLineMsg.rqstPickUpDt_RL.setInputProtected(true);
        // 2016/02/28 S21_NA#1738 Del Start
//        rmaLineMsg.rtrnRsnCd_RL.setInputProtected(true);
        // 2016/02/28 S21_NA#1738 Del End
        rmaLineMsg.hddRmvCd_RL.setInputProtected(true);
    }

    /**
     * Set RMA Item Protect [RMA Line Status : Closed]
     * @param rmaLineMsg NWAL1500_DBMsg
     */
    private static void setRmaItemProtectForClosed(NWAL1500_DBMsg rmaLineMsg) {

        rmaLineMsg.mdseCd_RL.setInputProtected(true);
        rmaLineMsg.xxImageTxt_DI.clear();
        rmaLineMsg.ordCustUomQty_RL.setInputProtected(true);
        rmaLineMsg.custUomCd_RL.setInputProtected(true);
        rmaLineMsg.entCpoDtlDealSlsAmt_RL.setInputProtected(true);
        rmaLineMsg.prcCatgNm_RL.setInputProtected(true);
        rmaLineMsg.xxImageTxt_DP.clear();
        rmaLineMsg.dsOrdLineCatgCd_RL.setInputProtected(true);
        rmaLineMsg.rtlWhNm_RL.setInputProtected(true);
        rmaLineMsg.xxImageTxt_DW.clear();
        // 2017/11/17 S21_NA#22252 Add Start
        rmaLineMsg.rtlSwhNm_RL.setInputProtected(true);
        rmaLineMsg.xxImageTxt_DU.clear();
        // 2017/11/17 S21_NA#22252 Add End
        rmaLineMsg.serNum_RL.setInputProtected(true);
        // 2018/12/18 QC#29285 Add Start
        rmaLineMsg.custMdseCd_RL.setInputProtected(true);
        // 2018/12/18 QC#29285 Add End
        rmaLineMsg.flPrcListNm_RL.setInputProtected(true);
        rmaLineMsg.funcUnitFlPrcAmt_RL.setInputProtected(true); // QC#22372 2018/01/10 Add
        rmaLineMsg.xxImageTxt_DF.clear();
        rmaLineMsg.dplyLineRefNum_RL.setInputProtected(true);
        rmaLineMsg.prcBaseDt_RL.setInputProtected(true);
        rmaLineMsg.rqstPickUpDt_RL.setInputProtected(true);
        rmaLineMsg.rtrnRsnCd_RL.setInputProtected(true);
        rmaLineMsg.hddRmvCd_RL.setInputProtected(true);
    }

    // Add Start 2019/05/08 QC#50015
    /**
     * Set RMA Item Protect [RMA Line Status : Closed]
     * @param rmaLineMsg NWAL1500_DBMsg
     */
    private static void setRmaItemProtectForLogistics(NWAL1500_DBMsg rmaLineMsg) {

        rmaLineMsg.mdseCd_RL.setInputProtected(true);
        rmaLineMsg.xxImageTxt_DI.clear();
        rmaLineMsg.ordCustUomQty_RL.setInputProtected(true);
        rmaLineMsg.custUomCd_RL.setInputProtected(true);
        rmaLineMsg.entCpoDtlDealSlsAmt_RL.setInputProtected(true);
        rmaLineMsg.prcCatgNm_RL.setInputProtected(true);
        rmaLineMsg.xxImageTxt_DP.clear();
        rmaLineMsg.dsOrdLineCatgCd_RL.setInputProtected(true);
        rmaLineMsg.custMdseCd_RL.setInputProtected(true);
        rmaLineMsg.flPrcListNm_RL.setInputProtected(true);
        rmaLineMsg.funcUnitFlPrcAmt_RL.setInputProtected(true);
        rmaLineMsg.xxImageTxt_DF.clear();
        rmaLineMsg.dplyLineRefNum_RL.setInputProtected(true);
        rmaLineMsg.prcBaseDt_RL.setInputProtected(true);
        rmaLineMsg.rtrnRsnCd_RL.setInputProtected(true);
        rmaLineMsg.hddRmvCd_RL.setInputProtected(true);
    }
    // Add End 2019/05/08 QC#50015

    /**
     * get Config Status Name
     * @param scrnMsg NWAL1500BMsg
     * @param confMsg NWAL1500_ABMsg
     * @return Config Status Name
     */
    private static String getConfStsNm(NWAL1500BMsg scrnMsg, NWAL1500_ABMsg confMsg) {

        String dsOrdPosnNum = confMsg.dsOrdPosnNum_LC.getValue();
        Set<String> lineStsList = new HashSet<String>();

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
            String targetDsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();

            if (dsOrdPosnNum.equals(targetDsOrdPosnNum)) {
                lineStsList.add(convertLineStsDescTxt(lineMsg.ordLineStsDescTxt_LL.getValue()));
            }
        }

        if (lineStsList.contains(LINE_STS_NM_UNREGISTERED)) {
            return LINE_STS_NM_UNREGISTERED;
        } else if (lineStsList.contains(LINE_STS_NM_ENTERED)) {
            return LINE_STS_NM_ENTERED;
        } else if (lineStsList.contains(LINE_STS_NM_SO_CANCEL)) {
            return LINE_STS_NM_SO_CANCEL;
        } else if (lineStsList.contains(LINE_STS_NM_PO_CANCEL)) {
            return LINE_STS_NM_PO_CANCEL;
        } else if (lineStsList.contains(LINE_STS_NM_BOOKED)) {
            return LINE_STS_NM_BOOKED;
        // 2016/07/04 S21_NA#7504 Mod Start
        } else if (lineStsList.contains(LINE_STS_NM_PENDING_ALLOCATION)) {
            return LINE_STS_NM_PENDING_ALLOCATION;
        // 2016/07/04 S21_NA#7504 Mod End
        } else if (lineStsList.contains(LINE_STS_NM_BACK_ORDER)) {
            return LINE_STS_NM_BACK_ORDER;
        } else if (lineStsList.contains(LINE_STS_NM_WAITING_PICK)) {
            return LINE_STS_NM_WAITING_PICK;
        } else if (lineStsList.contains(LINE_STS_NM_DELIVERED_TO_SHOP)) {
            return LINE_STS_NM_DELIVERED_TO_SHOP;
        } else if (lineStsList.contains(LINE_STS_NM_IN_SHOP_OR_CONFIG)) {
            return LINE_STS_NM_IN_SHOP_OR_CONFIG;
        } else if (lineStsList.contains(LINE_STS_NM_PENDING_SHIP)) {
            return LINE_STS_NM_PENDING_SHIP;
        } else if (lineStsList.contains(LINE_STS_NM_SHIPPED)) {
            return LINE_STS_NM_SHIPPED;
        } else if (lineStsList.contains(LINE_STS_NM_PENDING_DELIVERY_CONF)) {
            return LINE_STS_NM_PENDING_DELIVERY_CONF;
        } else if (lineStsList.contains(LINE_STS_NM_PENDING_INSTALL)) {
            return LINE_STS_NM_PENDING_INSTALL;
        } else if (lineStsList.contains(LINE_STS_NM_WAITING_RECEIPT)) {
            return LINE_STS_NM_WAITING_RECEIPT;
        } else if (lineStsList.contains(LINE_STS_NM_PENDING_INVOICE)) {
            return LINE_STS_NM_PENDING_INVOICE;
        // 2016/07/04 S21_NA#7504 Mod Start
        } else if (lineStsList.contains(LINE_STS_NM_PENDING_FULFILLMENT)) {
            return LINE_STS_NM_PENDING_FULFILLMENT;
        // 2016/07/04 S21_NA#7504 Mod End
        } else if (lineStsList.contains(LINE_STS_NM_BILLING_HOLD)) {
            return LINE_STS_NM_BILLING_HOLD;
        } else if (lineStsList.contains(LINE_STS_NM_SHIPPED_CLOSED)) {
            return LINE_STS_NM_SHIPPED_CLOSED;
        } else if (lineStsList.contains(LINE_STS_NM_ON_LOAN)) {
            return LINE_STS_NM_ON_LOAN;
        // 2016/07/04 S21_NA#10723 Mod Start
        } else if (lineStsList.contains(LINE_STS_NM_CLOSED_LOAN_RETURN)) {
            return LINE_STS_NM_CLOSED_LOAN_RETURN;
        } else if (lineStsList.contains(LINE_STS_NM_CLOSED_LOAN_SOLD)) {
            return LINE_STS_NM_CLOSED_LOAN_SOLD;
        // 2016/07/04 S21_NA#10723 Mod End
        } else if (lineStsList.contains(LINE_STS_NM_INVOICED)) {
            return LINE_STS_NM_INVOICED;
        } else if (lineStsList.contains(LINE_STS_NM_CANCELLED)) {
            return LINE_STS_NM_CANCELLED;
        }

        return LINE_STS_NM_UNREGISTERED;
    }

    /**
     * get RMA Config Status Name
     * @param scrnMsg NWAL1500BMsg
     * @param rmaConfMsg NWAL1500_CBMsg
     * @return RMA Config Status Name
     */
    private static String getRmaConfStsNm(NWAL1500BMsg scrnMsg, NWAL1500_CBMsg rmaConfMsg) {

        String dsOrdPosnNum = rmaConfMsg.dsOrdPosnNum_RC.getValue();
        Set<String> rmaLineStsList = new HashSet<String>();

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(i);
            String targetDsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();

            if (dsOrdPosnNum.equals(targetDsOrdPosnNum)) {
                rmaLineStsList.add(convertLineStsDescTxt(rmaLineMsg.rtrnLineStsDescTxt_RL.getValue()));
            }
        }

        if (rmaLineStsList.contains(LINE_STS_NM_UNREGISTERED)) {
            return LINE_STS_NM_UNREGISTERED;
        } else if (rmaLineStsList.contains(LINE_STS_NM_ENTERED)) {
            return LINE_STS_NM_ENTERED;
        } else if (rmaLineStsList.contains(LINE_STS_NM_BOOKED)) {
            return LINE_STS_NM_BOOKED;
        } else if (rmaLineStsList.contains(LINE_STS_NM_PENDING_RETURN)) {
            return LINE_STS_NM_PENDING_RETURN;
        } else if (rmaLineStsList.contains(LINE_STS_NM_RWS_CANCELLED)) {
            return LINE_STS_NM_RWS_CANCELLED;
        } else if (rmaLineStsList.contains(LINE_STS_NM_PARTIAL_RECEIVED)) {
            return LINE_STS_NM_PARTIAL_RECEIVED;
        } else if (rmaLineStsList.contains(LINE_STS_NM_PENDING_INSPECTION)) {
            return LINE_STS_NM_PENDING_INSPECTION;
        } else if (rmaLineStsList.contains(LINE_STS_NM_BILLING_HOLD)) {
            return LINE_STS_NM_BILLING_HOLD;
        } else if (rmaLineStsList.contains(LINE_STS_NM_PENDING_INVOICE)) {
            return LINE_STS_NM_PENDING_INVOICE;
        } else if (rmaLineStsList.contains(LINE_STS_NM_CANCELLED)) {
            return LINE_STS_NM_CANCELLED;
        } else if (rmaLineStsList.contains(LINE_STS_NM_CLOSED)) {
            return LINE_STS_NM_CLOSED;
        }

        return LINE_STS_NM_UNREGISTERED;
    }

    /**
     * get Line Status Desc Text
     * @param ordLineStsDescTxt Line Status Desc Text
     * @return Line Status Desc Text
     */
    private static String convertLineStsDescTxt(String lineStsDescTxt) {

        if (ZYPCommonFunc.hasValue(lineStsDescTxt)) {
            return lineStsDescTxt;
        }

        return LINE_STS_NM_UNREGISTERED;
    }

    // S21_NA#3398 Add Start
    /**
     * <pre>
     * Set Protect for Config Id Popup returned data.
     * Set protect for Config and Line
     * @param configMsg line config tab config message
     * @param lineMsgArray line config tab line message array
     * </pre>
     */
    public static void setProtectForSetFromConfigIdPopup(NWAL1500_ABMsg configMsg, NWAL1500_BBMsgArray lineMsgArray) {
        setProtectForSetFromConfigIdPopup(configMsg);
        if (ZYPConstant.FLG_ON_Y.equals(configMsg.xxYesNoCd_LC.getValue())) {
            String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
            for (int i = 0; i < lineMsgArray.getValidCount(); i++) {
                NWAL1500_BBMsg lineMsg = lineMsgArray.no(i);
                if (dsOrdPosnNum.equals(lineMsg.dsOrdPosnNum_LL.getValue())) {
                    setProtectForSetFromConfigIdPopup(lineMsg);
                }
            }
        }
    }

    /**
     * <pre>
     * Set Protect for Config Id Popup returned data.
     * Set protect for Config
     * @param configMsg line config tab config message
     * </pre>
     */
    public static void setProtectForSetFromConfigIdPopup(NWAL1500_ABMsg configMsg) {
        // QC#24245 2018/06/13 Del Start
        // if (ZYPConstant.FLG_ON_Y.equals(configMsg.xxYesNoCd_LC.getValue())) {
        //     configMsg.configTpCd_LC.setInputProtected(true);
        //     configMsg.svcConfigMstrPk_LC.setInputProtected(true);
        //     configMsg.xxImageTxt_AC.setInputProtected(true);
        // }
        // QC#24245 2018/06/13 Del End
    }

    /**
     * <pre>
     * Set Protect for Config Id Popup returned data.
     * Set protect for Line
     * @param lineMsg line config tab line message
     * </pre>
     */
    public static void setProtectForSetFromConfigIdPopup(NWAL1500_BBMsg lineMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(lineMsg.xxYesNoCd_LL.getValue())) {
            if (ZYPCommonFunc.hasValue(lineMsg.mdseCd_LL)) {
                lineMsg.mdseCd_LL.setInputProtected(true);
                lineMsg.xxImageTxt_BI.setInputProtected(true);
                lineMsg.ordCustUomQty_LL.setInputProtected(true);
                lineMsg.custUomCd_LL.setInputProtected(true);
                lineMsg.ordLineSrcCd_LL.setInputProtected(true);
                if (ZYPCommonFunc.hasValue(lineMsg.rtlWhNm_LL) && ZYPCommonFunc.hasValue(lineMsg.rtlWhNm_LL)) {
                    lineMsg.rtlWhNm_LL.setInputProtected(true);
                    lineMsg.xxImageTxt_BW.setInputProtected(true);
                    lineMsg.rtlSwhNm_LL.setInputProtected(true);
                    // QC#1615
                    lineMsg.xxImageTxt_BU.setInputProtected(true);
                }
                lineMsg.serNum_LL.setInputProtected(true);
                lineMsg.supdLockFlg_LL.setInputProtected(true);
                lineMsg.sbstMdseCd_LL.setInputProtected(true);
                lineMsg.xxImageTxt_BS.setInputProtected(true);
                lineMsg.vndInvtyLocCd_LL.setInputProtected(true);
            }
        }
    }

    /**
     * <pre>
     * Set Protect for Config Id Popup returned data.
     * Set protect for RMA tab Config and Line
     * @param rmaConfigMsg line RMA tab config message
     * @param rmaLineMsgArray RMA tab line message array
     * </pre>
     */
    public static void setProtectForSetFromConfigIdPopup(NWAL1500_CBMsg rmaConfigMsg, NWAL1500_DBMsgArray rmaLineMsgArray) {
        setProtectForSetFromConfigIdPopup(rmaConfigMsg);
        if (ZYPConstant.FLG_ON_Y.equals(rmaConfigMsg.xxYesNoCd_RC.getValue())) {
            String dsOrdPosnNum = rmaConfigMsg.dsOrdPosnNum_RC.getValue();
            for (int i = 0; i < rmaLineMsgArray.getValidCount(); i++) {
                NWAL1500_DBMsg rmaLineMsg = rmaLineMsgArray.no(i);
                if (dsOrdPosnNum.equals(rmaLineMsg.dsOrdPosnNum_RL.getValue())) {
                    setProtectForSetFromConfigIdPopup(rmaLineMsg);
                }
            }
        }
    }

    /**
     * <pre>
     * Set Protect for Config Id Popup returned data.
     * Set protect for RMA tab Config
     * @param rmaConfigMsg line RMA tab config message
     * </pre>
     */
    public static void setProtectForSetFromConfigIdPopup(NWAL1500_CBMsg rmaConfigMsg) {
        // QC#24245 2018/06/13 Add Start
        //if (ZYPConstant.FLG_ON_Y.equals(rmaConfigMsg.xxYesNoCd_RC.getValue())) {
        //    rmaConfigMsg.configTpCd_RC.setInputProtected(true);
        //    rmaConfigMsg.svcConfigMstrPk_RC.setInputProtected(true);
        //    rmaConfigMsg.xxImageTxt_CC.setInputProtected(true);
        //   rmaConfigMsg.mdlDescTxt_RC.setInputProtected(true);
        //}
        // QC#24245 2018/06/13 Add End
    }

    /**
     * <pre>
     * Set Protect for Config Id Popup returned data.
     * Set protect for RMA tab Line
     * @param rmaLineMsg RMA tab line message
     * </pre>
     */
    public static void setProtectForSetFromConfigIdPopup(NWAL1500_DBMsg rmaLineMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(rmaLineMsg.xxYesNoCd_RL.getValue())) {
            if (ZYPCommonFunc.hasValue(rmaLineMsg.mdseCd_RL)) {
                rmaLineMsg.mdseCd_RL.setInputProtected(true);
                rmaLineMsg.xxImageTxt_DI.setInputProtected(true);
                rmaLineMsg.ordCustUomQty_RL.setInputProtected(true);
                rmaLineMsg.custUomCd_RL.setInputProtected(true);
                // 2016/06/28 S21_NA#10946 Del Start
//                if (ZYPCommonFunc.hasValue(rmaLineMsg.rtlWhNm_RL) && ZYPCommonFunc.hasValue(rmaLineMsg.rtlSwhNm_RL)) {
//                    rmaLineMsg.rtlWhNm_RL.setInputProtected(true);
//                    rmaLineMsg.xxImageTxt_DW.setInputProtected(true);
//                }
                // 2016/06/28 S21_NA#10946 Del End
                // 2017/11/17 S21_NA#22252 Del Start
                //rmaLineMsg.rtlSwhNm_RL.setInputProtected(true);
                // 2017/11/17 S21_NA#22252 Del End
                rmaLineMsg.serNum_RL.setInputProtected(true);
            }
        }
    }
    // S21_NA#3398 Add End

    // S21_NA#1693/1700 Add Start
    /**
     * setProtectByOrdCatgBizCtx.
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    public static void setProtectByOrdCatgBizCtx(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Mod Start 2019/05/08 QC#50015
        boolean isLogistics = false;
        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
            String funcId = scrnMsg.L.no(i).xxFuncId.getValue();
            if (scrnMsg.L.getValidCount() == 1 && MOD_LOGISTICS.equals(funcId)) {
                isLogistics = true;
                break;
            }
        }
//        setProtectOrderLogAvailable(handler, scrnMsg, scrnMsg.xxDplyCtrlFlg_LT.getValue());
        setProtectOrderLogAvailable(handler, scrnMsg, scrnMsg.xxDplyCtrlFlg_LT.getValue(), isLogistics);
        // Mod End 2019/05/08 QC#50015

        // xxDplyCtrlFlg_LD : Use for JSP.
        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_LD.getValue())) {
            scrnMsg.leasePrchOptCd.clear();
            scrnMsg.leaseTermMthAot.clear();
            scrnMsg.leasePmtFreqCd.clear();
            scrnMsg.leaseTotPmtAmt.clear();
        }
        // QC#22789 2017/11/28 Add Start
        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_EM.getValue())) {
            scrnMsg.leaseTermMthAot_EM.clear();
        }
       // QC#22789 2017/11/28 Add End
        // 2017/11/02 S21_NA#20146 Add Start
        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_GS.getValue())) {
            scrnMsg.psnNum_GS.clear();
            scrnMsg.tocNm_GS.clear();
            scrnMsg.coaExtnCd_GS.clear();
            scrnMsg.coaExtnDescTxt_GS.clear();
            scrnMsg.coaBrCd_GS.clear();
            scrnMsg.coaBrDescTxt_GS.clear();
            scrnMsg.coaCcCd_GS.clear();
            scrnMsg.coaCcDescTxt_GS.clear();
        }
        // 2017/11/02 S21_NA#20146 Add End
        NWAL1500CommonLogic.setDropShipFlgProtectProperty(scrnMsg); // 2016/03/25 S21_NA#4693 Add
    }

    /**
     * setProtectOrderLogAvailable.
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     * @param flg String
     * @param isLogistics boolean
     */
    public static void setProtectOrderLogAvailable(EZDCommonHandler handler, NWAL1500BMsg scrnMsg, String flg, boolean isLogistics) {
        if (ZYPConstant.FLG_ON_Y.equals(flg)) {
            // Mod Start 2019/05/13 QC#50015
            if (isLogistics) {
                scrnMsg.ordLogTpCd_LK.clear();
                scrnMsg.ordLogTpCd_LK.setInputProtected(true);
                scrnMsg.ordLogTpCd.setInputProtected(true);
            } else {
                scrnMsg.ordLogTpCd_LK.setInputProtected(false);
                scrnMsg.ordLogTpCd_LK.setValue(ZYPConstant.FLG_ON_Y);
                scrnMsg.ordLogTpCd.setInputProtected(false); // S21_NA#5337 Mod
            }
            // Mod End 2019/05/13 QC#50015
        } else {
            scrnMsg.ordLogTpCd_LK.clear();
            scrnMsg.ordLogTpCd_LK.setInputProtected(true);
            scrnMsg.ordLogTpCd.setInputProtected(true); // S21_NA#5337 Mod

            // Mod Start 2019/05/13 QC#50015
            if (!isLogistics) {
                scrnMsg.ordLogTpCd.clear();
                scrnMsg.ordLogTpDescTxt_NM.clear();
            }
            // Mod End 2019/05/13 QC#50015
        }
        // 2016/03/02 S21_NA#4377 Add Start
        if (NWAL1500CommonLogic.checkReadOnlyByStatus(scrnMsg)) {
            scrnMsg.ordLogTpCd.setInputProtected(true);
            scrnMsg.ordLogTpCd_LK.setInputProtected(true);
            scrnMsg.ordLogTpCd.setInputProtected(true); // S21_NA#5337 Mod
        }
        // 2016/03/02 S21_NA#4377 Add End
    }
    // S21_NA#1693/1700 Add End

    /**
     * Set ProtectBy Fright Cond Code
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByFrtCond(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // Add Start 2019/05/08 QC#50015
        boolean isLogistics = false;
        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
            String funcId = scrnMsg.L.no(i).xxFuncId.getValue();
            if (scrnMsg.L.getValidCount() == 1 && MOD_LOGISTICS.equals(funcId)) {
                isLogistics = true;
                break;
            }
        }
        // Add End 2019/05/08 QC#50015

        final String hdrStsNm = scrnMsg.ordHdrStsDescTxt.getValue();
        // Mod Start 2019/05/08 QC#50015
//        if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm)) {
        if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm) || isLogistics) {
            return;
        }
        // Mod End 2019/05/08 QC#50015

        // 2018/06/25 S21_NA#23726 Mod Start
        if (FRT_COND.COLLECT.equals(scrnMsg.frtCondCd.getValue())) {
        scrnMsg.carrAcctNum.setInputProtected(false);
        } else {
            scrnMsg.carrAcctNum.setInputProtected(true);
        }
        scrnMsg.carrSvcLvlDescTxt.setInputProtected(false);
        scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(false);
        scrnMsg.carrSvcLvlDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);

        /*
        if (FRT_COND.COLLECT.equals(scrnMsg.frtCondCd.getValue())) {
            scrnMsg.carrAcctNum.setInputProtected(false);
            scrnMsg.carrSvcLvlDescTxt.setInputProtected(false);
            scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(false);
            scrnMsg.carrSvcLvlDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.carrAcctNum.setInputProtected(true);
            scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
            scrnMsg.carrAcctNum.clear();
            scrnMsg.carrSvcLvlDescTxt.clear();
            scrnMsg.carrSvcLvlDescTxt_LK.clear();
        }
        */
        // 2018/06/25 S21_NA#23726 Mod End
    }
    // QC#17474 2017/02/21 Add Start
    public static void setProtectByBillTo(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {
        // Protect Item by billTo Credit Profile
        final String hdrStsNm = scrnMsg.ordHdrStsDescTxt.getValue();
        if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm)) {
            return;
        }
        if (!scrnMsg.billToCustAcctCd.isInputProtected()) {
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, scrnMsg.ovrdPmtTermFlg.getValue())) {
                scrnMsg.pmtTermCashDiscDescTxt.setInputProtected(false);
                scrnMsg.pmtTermCashDiscDescTxt_LK.setInputProtected(false);
            } else {
                scrnMsg.pmtTermCashDiscDescTxt.setInputProtected(true);
                scrnMsg.pmtTermCashDiscDescTxt_LK.setInputProtected(true);
            }
        }
    }
    // QC#17474 2017/02/21 Add End
    // 2016/10/13 S21_NA#7700 Add Start
    /**
     * Set Screen Protect By Editable(CPO_SRC_TP.ORD_ENTRY_SCR_EDTBL_FLG)
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByEditable(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // set Header Protect(The same control with Close Status.)
        setProtectHdrStsForClosed(handler, scrnMsg);

        // set Line Detail or RMA Detail Protect
        String dplyTab = scrnMsg.xxDplyTab.getValue();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NWAL1500_ABMsg confMsg = scrnMsg.A.no(i);
                setConfItemProtectForShippedClosed(confMsg);
            }
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
                setLineItemProtectForShippedClosed(lineMsg);
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                NWAL1500_CBMsg rmaConfMsg = scrnMsg.C.no(i);
                setRmaConfItemProtectForClosed(rmaConfMsg);
            }
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(i);
                setRmaItemProtectForClosed(rmaLineMsg);
            }
        }
    }

    // Add Start 2019/05/07 QC#50015
    /**
     * Set Screen Protect By Editable(CPO_SRC_TP.ORD_ENTRY_SCR_EDTBL_FLG)
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1500BMsg
     */
    private static void setProtectByLogistics(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        // set Header Protect(The same control with Close Status.)
        setProtectHdrStsForClosed(handler, scrnMsg);

        // set Line Detail or RMA Detail Protect
        String dplyTab = scrnMsg.xxDplyTab.getValue();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NWAL1500_ABMsg confMsg = scrnMsg.A.no(i);
                setConfItemProtectForShippedClosed(confMsg);

                final String confStsNm = getConfStsNm(scrnMsg, confMsg);

                for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
                    NWAL1500_BBMsg lineMsg = scrnMsg.B.no(j);
                    if (S21StringUtil.isEquals(confMsg.dsOrdPosnNum_LC.getValue(), lineMsg.dsOrdPosnNum_LL.getValue())) {
                        setLineItemProtectForLogistics(lineMsg, confStsNm);
                    }
                }
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                NWAL1500_CBMsg rmaConfMsg = scrnMsg.C.no(i);
                setRmaConfItemProtectForClosed(rmaConfMsg);
            }
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(i);
                setRmaItemProtectForLogistics(rmaLineMsg);
            }
        }
    }
    // Add End 2019/05/07 QC#50015

    // 2016/10/13 S21_NA#7700 Add End

    // 2016/12/19 S21_NA#15898-2 Del Start
    // 2016/12/06 S21_NA#15898 Add Start
//    /**
//     * Set Screen Protect By Base Compornent Flag
//     * @param handler EZDCommonHandler
//     * @param scrnMsg NWAL1500BMsg
//     */
//    private static void setProtectByBaseCompFlg(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {
//
//        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
//            NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(i);
//            if (ZYPConstant.FLG_ON_Y.equals(rmaLineMsg.baseCmptFlg_RL.getValue())) {
//                rmaLineMsg.hddRmvCd_RL.setInputProtected(false);
//            } else {
//                rmaLineMsg.hddRmvCd_RL.setInputProtected(true);
//            }
//        }
//    }
    // 2016/12/06 S21_NA#15898 Add End
    // 2016/12/19 S21_NA#15898-2 Del End

    // 2018/05/10 S21_NA#25251 Add Start
    private static void setActivePriceFieldByAuth(NWAL1500BMsg scrnMsg) {

        boolean outBndPrcProtect = true;
        boolean inBndPrcProtect = true;

        // Function Version
        String funcId = null;
        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
            funcId = scrnMsg.L.no(i).xxFuncId.getValue();
            if (MOD_PRICE_OUTBOUND.equals(funcId)) {
                outBndPrcProtect = false;
            }
            if (MOD_PRICE_INBOUND.equals(funcId)) {
                inBndPrcProtect = false;
            }
        }

        if (outBndPrcProtect) { // 2018/05/22 S21_NA#25251-2 Add Condition
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.B.no(i).entCpoDtlDealSlsAmt_LL.setInputProtected(outBndPrcProtect);
                // 2018/12/18 QC#29285 Add Start
                scrnMsg.B.no(i).custMdseCd_LL.setInputProtected(outBndPrcProtect);
                // 2018/12/18 QC#29285 Add End
            }
        } // 2018/05/22 S21_NA#25251-2 Add Condition
        if (inBndPrcProtect) { // 2018/05/22 S21_NA#25251-2 Add Condition
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                scrnMsg.D.no(i).entCpoDtlDealSlsAmt_RL.setInputProtected(inBndPrcProtect);
                // 2018/12/18 QC#29285 Add Start
                scrnMsg.D.no(i).custMdseCd_RL.setInputProtected(inBndPrcProtect);
                // 2018/12/18 QC#29285 Add End
            }
        }  // 2018/05/22 S21_NA#25251-2 Add Condition
    }
    // 2018/05/10 S21_NA#25251 Add End
    // 2019/08/01 S21_NA#52156 Add Start
    private static void setProtectByOrdLinePRApproved(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxDplyCtrlFlg_LL.getValue())) {
                // 2019/08/15 S21_NA#52620 Mod Start
                //setLineItemProtectForWaitingPick(scrnMsg.B.no(i));
                setLineItemProtectForPRApproved(scrnMsg.B.no(i));
                // 2019/08/01 S21_NA#52620 Mod End
            }
        }
    }
    // 2019/08/01 S21_NA#52156 Add End

    // 2019/08/15 S21_NA#52620 Add Start
    /**
     * Set Line Item Protect [PR Approved]
     * @param lineMsg NWAL1500_BBMsg
     */
    private static void setLineItemProtectForPRApproved(NWAL1500_BBMsg lineMsg) {

        lineMsg.mdseCd_LL.setInputProtected(true);
        lineMsg.xxImageTxt_BI.clear();
        lineMsg.ordCustUomQty_LL.setInputProtected(true);
        lineMsg.custUomCd_LL.setInputProtected(true);
        lineMsg.dsOrdLineCatgCd_LL.setInputProtected(true);
        lineMsg.ordLineSrcCd_LL.setInputProtected(true);
        lineMsg.rtlWhNm_LL.setInputProtected(true);
        lineMsg.rtlSwhNm_LL.setInputProtected(true);
        lineMsg.xxImageTxt_BW.clear();
        lineMsg.xxImageTxt_BU.clear();
        lineMsg.serNum_LL.setInputProtected(false);
        lineMsg.supdLockFlg_LL.setInputProtected(true);
        lineMsg.sbstMdseCd_LL.setInputProtected(true);
        lineMsg.xxImageTxt_BS.clear();
        lineMsg.vndInvtyLocCd_LL.setInputProtected(true);
        lineMsg.rddDt_LL.setInputProtected(true);
    }
    // 2019/08/15 S21_NA#52620 Add End

    // 2020/01/07 S21_NA#54996 Add Start
    private static void setProtectByFreightInfo(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {
       if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_FI.getValue())) {
           scrnMsg.frtCondDescTxt.setInputProtected(true);
           scrnMsg.frtCondDescTxt_LK.setInputProtected(true);
           scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
           scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(true);
           scrnMsg.spclHdlgTpCd.setInputProtected(true);
           scrnMsg.shpgSvcLvlCd.setInputProtected(true);
           scrnMsg.carrAcctNum.setInputProtected(true);
       }
    }
    // 2020/01/07 S21_NA#54996 Add End
}
