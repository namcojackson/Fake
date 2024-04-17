/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1610;

import static business.blap.NWAL1610.constant.NWAL1610Constant.CONFIG_MODE;
import static business.blap.NWAL1610.constant.NWAL1610Constant.LINE_MODE;
import static business.blap.NWAL1610.constant.NWAL1610Constant.NWAM8250E;
import static business.blap.NWAL1610.constant.NWAL1610Constant.NWZM1108E;
import static business.blap.NWAL1610.constant.NWAL1610Constant.NWZM1583E;
import static business.blap.NWAL1610.constant.NWAL1610Constant.RMA_MODE;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1610.common.NWAL1610CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HDD_RMV;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1610BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         C.Yokoi         Create          N/A
 * 2016/08/30   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2018/04/11   Fujitsu         R.Nakamura      Update          S21_NA#25420
 * 2019/12/20   Fujitsu         S.Kosaka        Update          QC#54999
 *</pre>
 */
public class NWAL1610BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1610CMsg bizMsg = (NWAL1610CMsg) cMsg;
            NWAL1610SMsg glblMsg = (NWAL1610SMsg) sMsg;

            if ("NWAL1610_INIT".equals(screenAplID)) {
                doProcess_NWAL1610_INIT(bizMsg, glblMsg);
            } else if ("NWAL1610Scrn00_CMN_OK".equals(screenAplID)) {
                doProcess_NWAL1610Scrn00_CMN_OK(bizMsg, glblMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1610_INIT(NWAL1610CMsg bizMsg, NWAL1610SMsg glblMsg) {

        if (!CONFIG_MODE.equals(bizMsg.xxModeCd.getValue())) {
            if (!NWAL1610CommonLogic.getOrdLineCatg(bizMsg)) {
                bizMsg.setMessageInfo(NWZM1583E);
            }
            if (LINE_MODE.equals(bizMsg.xxModeCd.getValue())) {
                if (!NWAL1610CommonLogic.getOrdLineSrc(bizMsg)) {
                    bizMsg.setMessageInfo(NWAM8250E);
                }
            }
            if (RMA_MODE.equals(bizMsg.xxModeCd.getValue())) {
                // Mod Start 2018/04/11 QC#25420
//                ZYPCodeDataUtil.createPulldownList(RTRN_RSN.class, bizMsg.rtrnRsnCd_CD, bizMsg.rtrnRsnNm_DI);
                ZYPCodeDataUtil.createPulldownList(RTRN_RSN.class, bizMsg.rtrnRsnCd_CD, bizMsg.rtrnRsnDescTxt_DI);
                // Mod End 2018/04/11 QC#25420
                ZYPCodeDataUtil.createPulldownList(HDD_RMV.class, bizMsg.hddRmvCd_CD, bizMsg.hddRmvNm_DI);
            }
        }
        bizMsg.slsDt.setValue(ZYPDateUtil.getSalesDate()); // 2016/08/30 S21_NA#9806 Add Start

        // 2019/12/20 QC#54999 Add Start
        NWAL1610CommonLogic.setAuthority(bizMsg, getUserProfileService());
        // 2019/12/20 QC#54999 Add End
    }

    /**
     * CMN_OK Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1610Scrn00_CMN_OK(NWAL1610CMsg bizMsg, NWAL1610SMsg glblMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        if (CONFIG_MODE.equals(bizMsg.xxModeCd.getValue())) {
            // Master Check : SHIP_TO_CUST
            if (ZYPCommonFunc.hasValue(bizMsg.shipToCustLocCd)) {
                if (!NWAL1610CommonLogic.existsShipToCustCd(bizMsg.shipToCustLocCd.getValue(), glblCmpyCd)) {
                    bizMsg.setMessageInfo(NWZM1108E, new String[] {"Ship To Customer" });
                    return;
                }
            }

            // Master Check : BILL_TO_CUST
            if (ZYPCommonFunc.hasValue(bizMsg.billToCustLocCd)) {
                if (!NWAL1610CommonLogic.existsBillToCustCd(bizMsg.billToCustLocCd.getValue(), glblCmpyCd)) {
                    bizMsg.setMessageInfo(NWZM1108E, new String[] {"Bill To Customer" });
                    return;
                }
            }
        } else {
            // Master Check : RTL_WH
            if (ZYPCommonFunc.hasValue(bizMsg.rtlWhNm)) {
                if (!NWAL1610CommonLogic.existsRtlWh(bizMsg.rtlWhCd.getValue(), glblCmpyCd)) {
                    bizMsg.setMessageInfo(NWZM1108E, new String[] {"Ware House" });
                    return;
                }
            }

            // Master Check : RTL_SWH
            if (ZYPCommonFunc.hasValue(bizMsg.rtlSwhNm)) {
                if (!NWAL1610CommonLogic.existsRtlSubWh(bizMsg.rtlWhCd.getValue(), bizMsg.rtlSwhCd.getValue(), glblCmpyCd)) {
                    bizMsg.setMessageInfo(NWZM1108E, new String[] {"Sub Ware House" });
                    return;
                }
            }

            // Master Check : PRC_CATG (Price List Code)
            if (ZYPCommonFunc.hasValue(bizMsg.prcCatgNm)) {
                if (!NWAL1610CommonLogic.existsPrcCatg(bizMsg.prcCatgNm.getValue(), glblCmpyCd)) {
                    bizMsg.setMessageInfo(NWZM1108E, new String[] {"Price List" });
                    return;
                }
            }

            // Master Check : PRC_CATG (Floor Price List Code)
            if (ZYPCommonFunc.hasValue(bizMsg.flPrcListNm)) {
                if (!NWAL1610CommonLogic.existsFlrPrcList(bizMsg.flPrcListNm.getValue(), glblCmpyCd)) {
                    bizMsg.setMessageInfo(NWZM1108E, new String[] {"Floor Price List" });
                    return;
                }
            }

            if (RMA_MODE.equals(bizMsg.xxModeCd.getValue())) {
                return;
            }

            // Master Check : MDSE_CD
            if (ZYPCommonFunc.hasValue(bizMsg.xxScrItem20Txt)) {
                if (!NWAL1610CommonLogic.existsSbstItem(bizMsg.xxScrItem20Txt.getValue(), glblCmpyCd)) {
                    bizMsg.setMessageInfo(NWZM1108E, new String[] {"Substitute Item" });
                    return;
                }
            }
        }
    }
}
