/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1360;

import static business.blap.NPAL1360.constant.NPAL1360Constant.EVENT_NM_NPAL1360_CANCEL;
import static business.blap.NPAL1360.constant.NPAL1360Constant.EVENT_NM_NPAL1360_CMN_CLEAR;
import static business.blap.NPAL1360.constant.NPAL1360Constant.EVENT_NM_NPAL1360_CMN_RESET;
import static business.blap.NPAL1360.constant.NPAL1360Constant.EVENT_NM_NPAL1360_CMN_SUBMIT;
import static business.blap.NPAL1360.constant.NPAL1360Constant.EVENT_NM_NPAL1360_COMPONENT;
import static business.blap.NPAL1360.constant.NPAL1360Constant.EVENT_NM_NPAL1360_INIT;
import static business.blap.NPAL1360.constant.NPAL1360Constant.EVENT_NM_NPAL1360_OPEN_WIN_KIT_SERIAL;
import static business.blap.NPAL1360.constant.NPAL1360Constant.EVENT_NM_NPAL1360_OPEN_WIN_SUPPLY_SERIAL;
import static business.blap.NPAL1360.constant.NPAL1360Constant.EVENT_NM_NPAL1360_SEARCH;
import static business.blap.NPAL1360.constant.NPAL1360Constant.EVENT_NM_NPAL1360_SET_KIT_ITEM_NAME;
import static business.blap.NPAL1360.constant.NPAL1360Constant.EVENT_NM_NPAL1360_SET_SWH_NAME;
import static business.blap.NPAL1360.constant.NPAL1360Constant.EVENT_NM_NPAL1360_SET_WH_NAME;
import static business.blap.NPAL1360.constant.NPAL1360Constant.KIT_ITEM_LINE_NUM;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1360.common.NPAL1360CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Business ID : NPAL1360 Kitting Work Order Entry
 * Function Name : Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2016   CITS            Keiichi Masaki  Create          N/A
 * 01/31/2017   CITS            Y.IWASAKI       Update          QC#16109
 * 03/28/2018   CITS            Y.Iwasaki       Update          QC#22519
 *</pre>
 */

public class NPAL1360BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1360_INIT.equals(screenAplID)) {
                doProcess_NPAL1360_Init((NPAL1360CMsg) cMsg, (NPAL1360SMsg) sMsg, EVENT_NM_NPAL1360_INIT);
            } else if (EVENT_NM_NPAL1360_CMN_RESET.equals(screenAplID)) {
                doProcess_NPAL1360_Init((NPAL1360CMsg) cMsg, (NPAL1360SMsg) sMsg, EVENT_NM_NPAL1360_CMN_RESET);
            } else if (EVENT_NM_NPAL1360_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NPAL1360_Init((NPAL1360CMsg) cMsg, (NPAL1360SMsg) sMsg, EVENT_NM_NPAL1360_CMN_CLEAR);
            } else if (EVENT_NM_NPAL1360_SEARCH.equals(screenAplID)) {
                doProcess_NPAL1360_Search((NPAL1360CMsg) cMsg, (NPAL1360SMsg) sMsg);
            } else if (EVENT_NM_NPAL1360_SET_KIT_ITEM_NAME.equals(screenAplID)) {
                doProcess_NPAL1360_SetKitItemName((NPAL1360CMsg) cMsg, (NPAL1360SMsg) sMsg);
            } else if (EVENT_NM_NPAL1360_SET_WH_NAME.equals(screenAplID)) {
                doProcess_NPAL1360_SetWarehouseName((NPAL1360CMsg) cMsg, (NPAL1360SMsg) sMsg);
            } else if (EVENT_NM_NPAL1360_SET_SWH_NAME.equals(screenAplID)) {
                doProcess_NPAL1360_SetSubWarehouseName((NPAL1360CMsg) cMsg, (NPAL1360SMsg) sMsg);
            } else if (EVENT_NM_NPAL1360_OPEN_WIN_KIT_SERIAL.equals(screenAplID)) {
                doProcess_NPAL1360_OpenWinKitSerial((NPAL1360CMsg) cMsg, (NPAL1360SMsg) sMsg);
            } else if (EVENT_NM_NPAL1360_OPEN_WIN_SUPPLY_SERIAL.equals(screenAplID)) {
                doProcess_NPAL1360_OpenWinSupplySerial((NPAL1360CMsg) cMsg, (NPAL1360SMsg) sMsg);
            } else if (EVENT_NM_NPAL1360_COMPONENT.equals(screenAplID)) {
                doProcess_NPAL1360_Component((NPAL1360CMsg) cMsg, (NPAL1360SMsg) sMsg);
            } else if (EVENT_NM_NPAL1360_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NPAL1360_Submit((NPAL1360CMsg) cMsg, (NPAL1360SMsg) sMsg);
            } else if (EVENT_NM_NPAL1360_CANCEL.equals(screenAplID)) {
                doProcess_NPAL1360_Cancel((NPAL1360CMsg) cMsg, (NPAL1360SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     * @param eventName String
     */
     private void doProcess_NPAL1360_Init(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg, String eventName) {

         // NPAL1360 Screen Parameters
         String param00 = null;
         String param01 = null;
         BigDecimal param02 = null;
         String param03 = null;
         String param04 = null;
         String param05 = null;
         String param06 = null;
         BigDecimal param07 = null;

         if (ZYPCommonFunc.hasValue(cMsg.wrkOrdNum_P1)) {
             param00 = cMsg.wrkOrdNum_P1.getValue();
         }
         if (ZYPCommonFunc.hasValue(cMsg.mdseCd_P1)) {
             param01 = cMsg.mdseCd_P1.getValue();
         }
         if (ZYPCommonFunc.hasValue(cMsg.ordQty_P1)) {
             param02 = cMsg.ordQty_P1.getValue();
         }
         if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_P1)) {
             param03 = cMsg.rtlWhCd_P1.getValue();
         }
         if (ZYPCommonFunc.hasValue(cMsg.rtlSwhCd_P1)) {
             param04 = cMsg.rtlSwhCd_P1.getValue();
         }
         if (ZYPCommonFunc.hasValue(cMsg.prchReqNum_P1)) {
             param05 = cMsg.prchReqNum_P1.getValue();
         }
         if (ZYPCommonFunc.hasValue(cMsg.prchReqLineNum_P1)) {
             param06 = cMsg.prchReqLineNum_P1.getValue();
         }
         if (ZYPCommonFunc.hasValue(cMsg.prchReqLineSubNum_P1)) {
             param07 = cMsg.prchReqLineSubNum_P1.getValue();
         }

         // clear cMsg except screen parameters
         cMsg.wrkOrdNum.clear();
         cMsg.wrkOrdNum_SV.clear();
         cMsg.wrkOrdStsCd.clear();
         cMsg.dsWrkOrdStsNm.clear();
         cMsg.wrkOrdDt.clear();
         cMsg.prchReqNum.clear();
         cMsg.xxLinkAncr_WH.clear();
         cMsg.rtlWhCd.clear();
         cMsg.rtlWhNm.clear();
         cMsg.xxLinkAncr_SW.clear();
         cMsg.cpltRtlSwhCd.clear();
         cMsg.rtlSwhNm.clear();
         cMsg.rqstRcvDt.clear();
         cMsg.xxLinkAncr_KT.clear();
         cMsg.fnshGoodsMdseCd.clear();
         cMsg.fnshMdseDescShortTxt.clear();
         cMsg.fnshGoodsMdseNm.clear();
         cMsg.firstProdCtrlCd.clear();
         cMsg.rcvSerTakeFlg.clear();
         cMsg.serNum.clear();
         cMsg.effFromDt.clear();
         cMsg.effThruDt.clear();
         cMsg.basePkgUomCd.clear();
         cMsg.fnshGoodsOrdQty.clear();
         cMsg.fnshGoodsRcvQty.clear();
         cMsg.fnshGoodsBalQty.clear();
         cMsg.fnshGoodsCancQty.clear();
         cMsg.wrkOrdMsgTxt.clear();
         cMsg.wrkOrdDtlLineNum_SL.clear();
         cMsg.cmpsnRevnNum.clear();
         cMsg.trxSrcTpCd.clear();
         cMsg.invtyLocCd.clear();
         cMsg.invtyCtrlFlg.clear();
         cMsg.instlBaseCtrlFlg.clear();
         cMsg.invtyAllocQty.clear();
         cMsg.totOrdQty.clear();
         cMsg.xxYesNoCd.clear();
         cMsg.xxRqstTs_WH.clear();
         cMsg.xxRqstTz_WH.clear();
         cMsg.xxRqstTs_DH.clear();
         cMsg.xxRqstTz_DH.clear();
         cMsg.xxFileData.clear();
         cMsg.eventNm.clear();

         ZYPEZDItemValueSetter.setValue(cMsg.dsWrkOrdTpCd_SL, DS_WRK_ORD_TP.KIT);

         // ACMsg clear, ASMsg clear
         ZYPTableUtil.clear(cMsg.A);
         // QC#9436
         //ZYPTableUtil.clear(cMsg.S);
         ZYPTableUtil.clear(cMsg.W);
         EZDMsg.copy(cMsg, null, sMsg, null);

         if (ZYPCommonFunc.hasValue(param00)) {
             ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdNum_P1, param00);
         }
         if (ZYPCommonFunc.hasValue(param01)) {
             ZYPEZDItemValueSetter.setValue(cMsg.mdseCd_P1, param01);
         }
         if (ZYPCommonFunc.hasValue(param02)) {
             ZYPEZDItemValueSetter.setValue(cMsg.ordQty_P1, param02);
         }
         if (ZYPCommonFunc.hasValue(param03)) {
             ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_P1, param03);
         }
         if (ZYPCommonFunc.hasValue(param04)) {
             ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd_P1, param04);
         }
         if (ZYPCommonFunc.hasValue(param05)) {
             ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum_P1, param05);
         }
         if (ZYPCommonFunc.hasValue(param06)) {
             ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineNum_P1, param06);
         }
         if (ZYPCommonFunc.hasValue(param07)) {
             ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineSubNum_P1, param07);
         }

         // get global company code
         cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
         // get sales date
         ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));

         // create Work Order Type pull down list
         ZYPCodeDataUtil.createPulldownList(DS_WRK_ORD_TP.class, cMsg.dsWrkOrdTpCd_CD, cMsg.dsWrkOrdTpDescTxt_NM);

         if (eventName.equals(EVENT_NM_NPAL1360_INIT)
             || eventName.equals(EVENT_NM_NPAL1360_CMN_RESET)) {
             if (ZYPCommonFunc.hasValue(cMsg.wrkOrdNum_P1)) {
                 ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdNum, cMsg.wrkOrdNum_P1);
                 NPAL1360CommonLogic.search(cMsg, sMsg, EVENT_NM_NPAL1360_SEARCH);
             } else if (ZYPCommonFunc.hasValue(cMsg.mdseCd_P1)
                     && ZYPCommonFunc.hasValue(cMsg.ordQty_P1)
                     && ZYPCommonFunc.hasValue(cMsg.rtlWhCd_P1)
                     && ZYPCommonFunc.hasValue(cMsg.rtlSwhCd_P1)
                     && ZYPCommonFunc.hasValue(cMsg.prchReqNum_P1)
                     && ZYPCommonFunc.hasValue(cMsg.prchReqLineNum_P1)
                     && ZYPCommonFunc.hasValue(cMsg.prchReqLineSubNum_P1)) {
                     ZYPEZDItemValueSetter.setValue(cMsg.fnshGoodsMdseCd, cMsg.mdseCd_P1);
                     ZYPEZDItemValueSetter.setValue(cMsg.fnshGoodsOrdQty, cMsg.ordQty_P1);
                     ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, cMsg.rtlWhCd_P1);
                     ZYPEZDItemValueSetter.setValue(cMsg.cpltRtlSwhCd, cMsg.rtlSwhCd_P1);
                     ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum, cMsg.prchReqNum_P1);
                if (ZYPCommonFunc.hasValue(cMsg.serNum_P1)) {
                    StringBuilder sbSerials = new StringBuilder(cMsg.serNum_P1.getValue());
                    if (sbSerials.length() > 30) {
                        sbSerials.delete(30, sbSerials.length());
                    }

                    ZYPEZDItemValueSetter.setValue(cMsg.serNum, sbSerials.toString());

                    String[] serNumArray = cMsg.serNum_P1.getValue().split(",");
                    cMsg.S.setValidCount(serNumArray.length);
                    if (serNumArray.length > 0) {
                        for (int i = 0; i < serNumArray.length; i++) {
                            ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).wrkOrdDtlLineNum_S1, KIT_ITEM_LINE_NUM);
                            ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).serNum_S1, serNumArray[i]);
                        }
                    }
                }
                if (ZYPCommonFunc.hasValue(cMsg.rqstRcvDt_P1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, cMsg.rqstRcvDt_P1);
                }
                NPAL1360CommonLogic.component(cMsg, sMsg);
             }
         }
     }

    /**
     * Set Kit Item Name
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     */
    private void doProcess_NPAL1360_SetKitItemName(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        NPAL1360CommonLogic.setKitItemName(cMsg);

    }

    /**
     * Set Warehouse Name
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     */
    private void doProcess_NPAL1360_SetWarehouseName(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        NPAL1360CommonLogic.setWarehouseName(cMsg);

    }

    /**
     * Set Sub Warehouse Name
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     */
    private void doProcess_NPAL1360_SetSubWarehouseName(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        NPAL1360CommonLogic.setSubWarehouseName(cMsg);

    }

    /**
     * Open Window Kit Item Serial#
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     */
    private void doProcess_NPAL1360_OpenWinKitSerial(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        NPAL1360CommonLogic.openWin_KitSerial(cMsg);

    }

    /**
     * Open Window Kit Item Serial#
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     */
    private void doProcess_NPAL1360_OpenWinSupplySerial(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        NPAL1360CommonLogic.openWin_SupplySerial(cMsg);

    }

    /**
     * Search
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     */
    private void doProcess_NPAL1360_Search(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        NPAL1360CommonLogic.search(cMsg, sMsg, EVENT_NM_NPAL1360_SEARCH);

    }

    /**
     * Component
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     */
    private void doProcess_NPAL1360_Component(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        NPAL1360CommonLogic.component(cMsg, sMsg);

        EZDMsg.copy(cMsg, null, sMsg, null);

    }

    /**
     * Submit
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     */
    private void doProcess_NPAL1360_Submit(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        NPAL1360CommonLogic.search(cMsg, sMsg, EVENT_NM_NPAL1360_CMN_SUBMIT);

    }

    /**
     * Cancel
     * @param cMsg NPAL1360CMsg
     * @param sMsg NPAL1360SMsg
     */
    private void doProcess_NPAL1360_Cancel(NPAL1360CMsg cMsg, NPAL1360SMsg sMsg) {

        NPAL1360CommonLogic.search(cMsg, sMsg, EVENT_NM_NPAL1360_CANCEL);

    }

}
