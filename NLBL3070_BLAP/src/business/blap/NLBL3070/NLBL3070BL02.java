/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3070;

import static business.blap.NLBL3070.constant.NLBL3070Constant.SO_LINE_001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3070.common.NLBL3070CommonLogic;
import business.blap.NLBL3070.constant.NLBL3070Constant;
import business.db.MDL_NMTMsg;
import business.db.MDL_NMTMsgArray;
import business.db.OTBD_CARR_VTMsg;
import business.db.OTBD_CARR_VTMsgArray;
import business.db.RTL_SWHTMsg;
import business.db.RTL_SWHTMsgArray;
import business.db.RTL_WHTMsg;
import business.db.S21_PSNTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SWHTMsg;
import business.file.NLBL3070F00FMsg;
import business.file.NLBL3070F01FMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_COORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TEMP_SCHD_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Delivery Scheduling / Manage Deliveries
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 * 02/26/2016   CSAI            Y.Imazu         Update          QC#2046, 2201
 * 05/03/2016   CSAI            Y.Imazu         Update          QC#5125
 * 08/31/2016   CSAI            Y.Imazu         Update          QC#9845
 * 10/17/2016   CITS            K.Ogino         Update          QC#10406
 * 11/21/2016   CSAI            Y.Imazu         Update          QC#15969
 * 12/13/2016   CITS            T.Kikuhara      Update          QC#15622
 * 01/30/2017   CITS            M.Naito         Update          QC#16924
 * 06/15/2017   CITS            R.Shimamoto     Update          QC#18272
 * 07/05/2017   CITS            R.Shimamoto     Update          QC#19687
 * 09/07/2017   CITS            S.Katsuma       Update          Sol#032(QC#13117)
 * 2017/12/28   CITS            T.Hakodate      Update          QC#18460(SOL#087)
 * 2018/01/24   CITS            T.Hakodate      Update          QC#18460(SOL#087)
 * 2018/02/13   CITS            T.Tokutomi      Update          QC#22613
 * 2018/02/23   CITS            K.Ogino         Update          QC#24123,QC#24122,QC#20043
 * 03/07/2018   CITS            T.Tokutomi      Update          QC#21913
 * 03/23/2018   CITS            S.Katsuma       Update          QC#24697
 * 04/20/2018   CITS            K.Ogino         Update          QC#25427
 * 02/14/2020   CITS            M.Furugoori     Update          QC#50121
 *</pre>
 */
public class NLBL3070BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL3070_INIT".equals(screenAplID)) {

                doProcess_NLBL3070_INIT((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);
                getColData((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_Search".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Search((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_CMN_Reset".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_CMN_Reset((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_CMN_Clear".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_CMN_Clear((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_PageNext".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_PageNext((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_PagePrev".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_PagePrev((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_PageJump".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_PageJump((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_Select_All".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Select_All((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_UnSelect_All".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_UnSelect_All((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_Tab_Schd".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Tab_Schd((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);
                getColData((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_Tab_Dely".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Tab_Dely((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);
                getColData((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            }  else if ("NLBL3070Scrn00_Apply".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Apply((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            }  else if ("NLBL3070Scrn00_Search_CarrInfo".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Search_CarrInfo((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            }  else if ("NLBL3070Scrn00_Search_CoordInfo".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Search_CoordInfo((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            }  else if ("NLBL3070Scrn00_Search_RtlSWHInfo".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Search_RtlSWHInfo((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            }  else if ("NLBL3070Scrn00_Search_RtlWHInfo".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Search_RtlWHInfo((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            }  else if ("NLBL3070Scrn00_Search_ShipToCustInfo".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Search_ShipToCustInfo((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            }  else if ("NLBL3070Scrn00_ContractHolds".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_ContractHolds((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            }  else if ("NLBL3070Scrn00_ExpandHolds".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_ExpandHolds((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            }  else if ("NLBL3070Scrn00_OnChange_ChkBoxSoNum".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_OnChange_ChkBoxSoNum((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            }  else if ("NLBL3070Scrn00_OnChange_ChkBoxSoSlipNum".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_OnChange_ChkBoxSoSlipNum((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            }  else if ("NLBL3070Scrn00_OpenWin_SerEntry".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_OpenWin_SerEntry((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            }  else if ("NLBL3070_NLBL3000".equals(screenAplID)) {

                doProcess_NLBL3070_NLBL3000((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {

                doProcess_NLBL3070_OnChangeSavedSearchOption((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_CMN_Download".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_CMN_Download((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_TBLColumnSort".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_TBLColumnSort((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            // After update process
            } else if ("NLBL3070Scrn00_CMN_Submit".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_CMN_Submit((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_Release_SO".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Release_SO((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_Send_Rqst".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Send_Rqst((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_SO_Cancel".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_SO_Cancel((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_Ship".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Ship((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_SO_Close".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_SO_Close((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_Delivery_Conf".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_Delivery_Conf((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            // No process
            } else if ("NLBL3070Scrn00_SaveSearch".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_SaveSearch((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_DeleteSearch".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_DeleteSearch((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_CMN_ColClear".equals(screenAplID)) {

                doProcess_NNLBL3070Scrn00_CMN_ColClear((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_CMN_ColSave".equals(screenAplID)) {

                doProcess_NLBL3070Scrn00_CMN_ColSave((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070Scrn00_OpenWin_Tracking".equals(screenAplID)) {
                // QC#21913 Add.
                doProcess_NLBL3070Scrn00_OpenWin_Tracking((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else if ("NLBL3070_NLBL3170".equals(screenAplID)) {
                // QC#21913 Add.
                doProcess_NLBL3070_NLBL3170((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);

            } else {

                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {

            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3070_INIT
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070_INIT(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        clearAll(cMsg, sMsg);

        //Setup select box 
        NLBL3070CommonLogic.createSavedSearchOptionsPullDown(cMsg, getContextUserInfo().getUserId());

        // create Pull-Down
        createPulldownList(cMsg);

        boolean doSearch = false;

        if (ZYPCommonFunc.hasValue(cMsg.trxHdrNum_BK)) {

            ZYPEZDItemValueSetter.setValue(cMsg.trxHdrNum, cMsg.trxHdrNum_BK);
            doSearch = true;
        }

        if (ZYPCommonFunc.hasValue(cMsg.t_MdlNm_BK)) {

            ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm, cMsg.t_MdlNm_BK);
            doSearch = true;
        }

        if (ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk_BK)) {

            ZYPEZDItemValueSetter.setValue(cMsg.svcConfigMstrPk, cMsg.svcConfigMstrPk_BK);
            doSearch = true;
        }

        if (ZYPCommonFunc.hasValue(cMsg.soNum_BK)) {

            ZYPEZDItemValueSetter.setValue(cMsg.soNum, cMsg.soNum_BK);
            doSearch = true;
        }

        if (ZYPCommonFunc.hasValue(cMsg.bolNum_BK)) {

            ZYPEZDItemValueSetter.setValue(cMsg.bolNum, cMsg.bolNum_BK);
            doSearch = true;
        }

        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_BK)) {

            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, cMsg.rtlWhCd_BK);
            doSearch = true;
        }

        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_BK)) {

            ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, cMsg.shipToCustCd_BK);
            doSearch = true;
        }

        if (ZYPCommonFunc.hasValue(cMsg.carrCd_BK)) {

            ZYPEZDItemValueSetter.setValue(cMsg.carrCd, cMsg.carrCd_BK);
            doSearch = true;
        }

        if (ZYPCommonFunc.hasValue(cMsg.schdCoordPsnCd_BK)) {

            ZYPEZDItemValueSetter.setValue(cMsg.schdCoordPsnCd, cMsg.schdCoordPsnCd_BK);
            doSearch = true;
        }

        if (ZYPCommonFunc.hasValue(cMsg.xxDplyTab_BK)) {

            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, cMsg.xxDplyTab_BK);
            doSearch = true;

        } else if (doSearch) {

            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, NLBL3070Constant.TAB_ID_SCHD);
        }

        if (doSearch) {

            search(cMsg, sMsg);

        } else {

            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, NLBL3070Constant.TAB_ID_SCHD);
        }
    }

    /**
     * createPulldownList
     * @param cMsg NLBL3070CMsg
     */
    private void createPulldownList(NLBL3070CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(DS_ORD_CATG.class, cMsg.dsOrdCatgCd_P, cMsg.dsOrdCatgDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(DS_ORD_TP.class, cMsg.dsOrdTpCd_P, cMsg.dsOrdTpDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(DS_SO_LINE_STS.class, cMsg.dsSoLineStsCd_P, cMsg.dsSoLineStsDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(SHPG_SVC_LVL.class, cMsg.shpgSvcLvlCd_P, cMsg.shpgSvcLvlDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(SCHD_COORD_STS.class, cMsg.schdCoordStsCd_P, cMsg.schdCoordStsDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(TEMP_SCHD_RSN.class, cMsg.tempSchdRsnCd_P, cMsg.tempSchdRsnDescTxt_P);
        NLBL3070CommonLogic.setPulldownTimeAmPm(cMsg);

        // Carrier Reason
        S21SsmEZDResult ssmResult = NLBL3070Query.getInstance().getOtbdCarrRsnList(cMsg.glblCmpyCd.getValue());

        if (ssmResult.isCodeNotFound()) {

            return;
        }

        List<Map<String, String>> otbdCarrRsnList = (List<Map<String, String>>) ssmResult.getResultObject();

        for (int i = 0; i < otbdCarrRsnList.size(); i++) {

            if (i >= cMsg.carrRsnCd_P.length()) {

                break;
            }

            ZYPEZDItemValueSetter.setValue(cMsg.carrRsnCd_P.no(i), otbdCarrRsnList.get(i).get("CARR_RSN_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.carrRsnDescTxt_P.no(i), otbdCarrRsnList.get(i).get("CARR_RSN_DESC_TXT"));
        }

        // QC#15622
        ssmResult = NLBL3070Query.getInstance().getInitSchdCoordStsList(cMsg.glblCmpyCd.getValue());

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {

                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.schdCoordStsCd_BP.no(i), (String) resultMap.get("SCHD_COORD_STS_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.schdCoordStsDescTxt_BP.no(i), (String) resultMap.get("SCHD_COORD_STS_DESC_TXT"));
            }
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_Search
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Search(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (checkInputHeader(cMsg)) {

            search(cMsg, sMsg);
        }

        cMsg.xxWrnSkipFlg.clear();
    }

    /**
     * doProcess_NLBL3070Scrn00_CMN_Clear
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_CMN_Clear(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        String col = cMsg.xxComnColOrdTxt.getValue();
        clearAll(cMsg, sMsg);

        // create Pull-Down
        createPulldownList(cMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.xxComnColOrdTxt, col);
    }

    /**
     * doProcess_NLBL3070Scrn00_CMN_Reset
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_CMN_Reset(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        doProcess_NLBL3070_INIT(cMsg, sMsg);
        getColData((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg);
    }

    /**
     * doProcess_NLBL3070Scrn00_PageNext
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_PageNext(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (NLBL3070Constant.TAB_ID_SCHD.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3070CommonLogic.saveCurrentPageToSMsgScheduling(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.A);
            NLBL3070CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowToNum_A.getValueInt());

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3070CommonLogic.saveCurrentPageToSMsgDeliveries(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.B);
            NLBL3070CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowToNum_B.getValueInt());
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_PagePrev
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_PagePrev(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (NLBL3070Constant.TAB_ID_SCHD.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3070CommonLogic.saveCurrentPageToSMsgScheduling(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.A);
            NLBL3070CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowFromNum_A.getValueInt() - cMsg.A.length() - 1);

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3070CommonLogic.saveCurrentPageToSMsgDeliveries(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.B);
            NLBL3070CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowFromNum_B.getValueInt() - cMsg.B.length() - 1);
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_PageJump
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_PageJump(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (NLBL3070Constant.TAB_ID_SCHD.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3070CommonLogic.saveCurrentPageToSMsgScheduling(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.A);
            NLBL3070CommonLogic.pagenation(cMsg, sMsg, cMsg.A.length() * (cMsg.xxPageShowCurNum_A.getValueInt() - 1));

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3070CommonLogic.saveCurrentPageToSMsgDeliveries(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.B);
            NLBL3070CommonLogic.pagenation(cMsg, sMsg, cMsg.B.length() * (cMsg.xxPageShowCurNum_B.getValueInt() - 1));
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_Select_All
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Select_All(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (NLBL3070Constant.TAB_ID_SCHD.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < cMsg.A.getValidCount(); i++) {

                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
            }

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
            }

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(cMsg.xxDplyTab.getValue())) {

            String preSoNum = "";

            for (int i = 0; i < cMsg.B.getValidCount(); i++) {

                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).soNum_B1)) {

                    if (!preSoNum.equals(cMsg.B.no(i).soNum_B1.getValue())) {

                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxChkBox_B1, ZYPConstant.CHKBOX_ON_Y);
                    }

                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxChkBox_B2, ZYPConstant.CHKBOX_ON_Y);
                }

                // QC#20043
                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).actlDelyDt_BK)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxChkBox_B1, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxChkBox_B2, ZYPConstant.FLG_OFF_N);
                }

                preSoNum = cMsg.B.no(i).soNum_B1.getValue();
            }

            preSoNum = "";

            for (int i = 0; i < sMsg.B.getValidCount(); i++) {

                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).soNum_B1)) {

                    if (!preSoNum.equals(sMsg.B.no(i).soNum_B1.getValue())) {

                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxChkBox_B1, ZYPConstant.CHKBOX_ON_Y);
                    }

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxChkBox_B2, ZYPConstant.CHKBOX_ON_Y);
                }

                // QC#20043
                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).actlDelyDt_BK)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxChkBox_B1, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxChkBox_B2, ZYPConstant.FLG_OFF_N);
                }

                preSoNum = sMsg.B.no(i).soNum_B1.getValue();
            }
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_UnSelect_All
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_UnSelect_All(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (NLBL3070Constant.TAB_ID_SCHD.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < cMsg.A.getValidCount(); i++) {

                cMsg.A.no(i).xxChkBox_A1.clear();
            }

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                sMsg.A.no(i).xxChkBox_A1.clear();
            }

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < cMsg.B.getValidCount(); i++) {

                cMsg.B.no(i).xxChkBox_B1.clear();
                cMsg.B.no(i).xxChkBox_B2.clear();
            }

            for (int i = 0; i < sMsg.B.getValidCount(); i++) {

                sMsg.B.no(i).xxChkBox_B1.clear();
                sMsg.B.no(i).xxChkBox_B2.clear();
            }
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_Tab_Schd
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Tab_Schd(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (!NLBL3070Constant.TAB_ID_SCHD.equals(cMsg.xxDplyTab.getValue())) {

            if (sMsg.B.getValidCount() != 0) {

                NLBL3070CommonLogic.saveCurrentPageToSMsgDeliveries(cMsg, sMsg);
                boolean chkOnCount = false;

                for (int i = 0; i < sMsg.B.getValidCount(); i++) {

                    NLBL3070_BSMsg sMsgBLine = sMsg.B.no(i);

                    if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgBLine.xxChkBox_B1.getValue())) {

                        chkOnCount = true;
                        break;
                    }
                }

                if (chkOnCount) {

                    // Search scheduling Tab
                    searchScheduling(cMsg, sMsg, NLBL3070Constant.SEARCH_TP_TAB);
                }
            }

            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, NLBL3070Constant.TAB_ID_SCHD);
            cMsg.xxWrnSkipFlg.clear();
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_Tab_Dely
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Tab_Dely(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (!NLBL3070Constant.TAB_ID_DELV.equals(cMsg.xxDplyTab.getValue())) {

            if (sMsg.A.getValidCount() != 0) {

                NLBL3070CommonLogic.saveCurrentPageToSMsgScheduling(cMsg, sMsg);
                boolean chkOnCount = false;

                for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                    NLBL3070_ASMsg sMsgALine = sMsg.A.no(i);

                    if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxChkBox_A1.getValue())) {

                        chkOnCount = true;
                        break;
                    }
                }

                if (chkOnCount) {

                    // Search Delivery Tab
                    searchDeliveries(cMsg, sMsg, NLBL3070Constant.SEARCH_TP_TAB);
                }
            }

            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, NLBL3070Constant.TAB_ID_DELV);
            cMsg.xxWrnSkipFlg.clear();
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_Apply
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Apply(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (NLBL3070Constant.TAB_ID_SCHD.equals(cMsg.xxDplyTab.getValue())) {

            applyScheduling(cMsg, sMsg);

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(cMsg.xxDplyTab.getValue())) {

            applyDeliveries(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_Search_CarrInfo
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Search_CarrInfo(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.carrCd)) {

            OTBD_CARR_VTMsg otbdCarrV = new OTBD_CARR_VTMsg();
            otbdCarrV.setSQLID("001");
            otbdCarrV.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            otbdCarrV.setConditionValue("carrCd01", cMsg.carrCd.getValue());
            OTBD_CARR_VTMsgArray otbdCarrVList = (OTBD_CARR_VTMsgArray) EZDTBLAccessor.findByCondition(otbdCarrV);

            if (otbdCarrVList == null || otbdCarrVList.getValidCount() == 0) {

                cMsg.carrCd.setErrorInfo(1, NLBL3070Constant.NLZM2278E, new String[]{"Carrier"});
                cMsg.carrNm.clear();
                return;
            }

            ZYPEZDItemValueSetter.setValue(cMsg.carrNm, otbdCarrVList.no(0).carrNm);

        } else {

            cMsg.carrCd.setErrorInfo(1, NLBL3070Constant.ZZM9000E, new String[]{"Carrier"});
            cMsg.carrNm.clear();
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_Search_CoordInfo
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Search_CoordInfo(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.schdCoordPsnCd)) {

            S21_PSNTMsg psn = new S21_PSNTMsg();
            ZYPEZDItemValueSetter.setValue(psn.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(psn.psnCd, cMsg.schdCoordPsnCd.getValue());
            psn = (S21_PSNTMsg) EZDFastTBLAccessor.findByKey(psn);

            if (psn == null) {

                cMsg.schdCoordPsnCd.setErrorInfo(1, NLBL3070Constant.NLZM2278E, new String[]{"Coordinator"});
                cMsg.xxPsnFirstMidLastNm.clear();
                return;
            }

            ZYPEZDItemValueSetter.setValue(cMsg.xxPsnFirstMidLastNm, psn.psnFirstNm.getValue() + " " + psn.psnLastNm.getValue());

        } else {

            cMsg.schdCoordPsnCd.setErrorInfo(1, NLBL3070Constant.ZZM9000E, new String[]{"Coordinator"});
            cMsg.xxPsnFirstMidLastNm.clear();
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_Search_RtlSWHInfo
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Search_RtlSWHInfo(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.rtlSwhCd)) {

            RTL_SWHTMsg rtlSwh = new RTL_SWHTMsg();
            rtlSwh.setSQLID("002");
            rtlSwh.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            rtlSwh.setConditionValue("rtlSwhCd01", cMsg.rtlSwhCd.getValue());
            RTL_SWHTMsgArray rtlSwhList = (RTL_SWHTMsgArray) EZDTBLAccessor.findByCondition(rtlSwh);

            if (rtlSwhList == null || rtlSwhList.getValidCount() == 0) {

                cMsg.rtlSwhCd.setErrorInfo(1, NLBL3070Constant.NLZM2278E, new String[]{"Source SWH"});
                cMsg.rtlSwhNm.clear();
                return;
            }

            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, rtlSwhList.no(0).rtlSwhNm);

        } else {

            cMsg.rtlSwhCd.setErrorInfo(1, NLBL3070Constant.ZZM9000E, new String[]{"Source SWH"});
            cMsg.rtlSwhNm.clear();
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_Search_RtlWHInfo
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Search_RtlWHInfo(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {

            RTL_WHTMsg rtlWh = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWh.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rtlWh.rtlWhCd, cMsg.rtlWhCd.getValue());
            rtlWh = (RTL_WHTMsg) EZDFastTBLAccessor.findByKey(rtlWh);

            if (rtlWh == null) {

                cMsg.rtlWhCd.setErrorInfo(1, NLBL3070Constant.NLZM2278E, new String[]{"Source WH"});
                cMsg.rtlWhNm.clear();
                return;
            }

            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, rtlWh.rtlWhNm);

        } else {

            cMsg.rtlWhCd.setErrorInfo(1, NLBL3070Constant.ZZM9000E, new String[]{"Source WH"});
            cMsg.rtlWhNm.clear();
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_Search_ShipToCustInfo
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Search_ShipToCustInfo(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd)) {

            S21SsmEZDResult ssmResult = null;
            ssmResult = NLBL3070Query.getInstance().getShipToCustNm(cMsg);

            if (ssmResult.getQueryResultCount() == 0) {

                cMsg.shipToCustCd.setErrorInfo(1, NLBL3070Constant.NLZM2278E, new String[]{"Ship To Customer"});
                cMsg.shipToCustNm.clear();
                return;
            }

            String shipToCustNm = (String) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(cMsg.shipToCustNm, shipToCustNm);

        } else {

            cMsg.shipToCustCd.setErrorInfo(1, NLBL3070Constant.ZZM9000E, new String[]{"Ship To Customer"});
            cMsg.shipToCustNm.clear();
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_ContractHolds ([-] --> [+])
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_ContractHolds(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        NLBL3070CommonLogic.saveCurrentPageToSMsgDeliveries(cMsg, sMsg);

        int eventLine = cMsg.xxNum_EV.getValueInt();
        int sMsgIdxB = cMsg.B.no(eventLine).xxNum_B1.getValueInt();
        String preSoNum = cMsg.B.no(eventLine).soNum_B1.getValue();

        for (int i = sMsgIdxB; i < sMsg.B.getValidCount(); i++) {

            if (sMsgIdxB == i) {

                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxSmryLineFlg_B1, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxDplyCtrlFlg_B1, ZYPConstant.FLG_ON_Y);

            } else if (preSoNum.equals(sMsg.B.no(i).soNum_B1.getValue())) {

                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxSmryLineFlg_B1, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxDplyCtrlFlg_B1, ZYPConstant.FLG_OFF_N);

            } else {

                break;
            }
        }

        ZYPTableUtil.clear(cMsg.B);
        NLBL3070CommonLogic.pagenation(cMsg, sMsg, cMsg.B.length() * (cMsg.xxPageShowCurNum_B.getValueInt() - 1));
    }

    /**
     * doProcess_NLBL3070Scrn00_ExpandHolds ([+] --> [-])
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_ExpandHolds(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        NLBL3070CommonLogic.saveCurrentPageToSMsgDeliveries(cMsg, sMsg);

        int eventLine = cMsg.xxNum_EV.getValueInt();
        int sMsgIdxB = cMsg.B.no(eventLine).xxNum_B1.getValueInt();
        String preSoNum = cMsg.B.no(eventLine).soNum_B1.getValue();

        for (int i = sMsgIdxB; i < sMsg.B.getValidCount(); i++) {

            if (sMsgIdxB == i) {

                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxSmryLineFlg_B1, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxDplyCtrlFlg_B1, ZYPConstant.FLG_ON_Y);

            } else if (preSoNum.equals(sMsg.B.no(i).soNum_B1.getValue())) {

                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxSmryLineFlg_B1, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxDplyCtrlFlg_B1, ZYPConstant.FLG_ON_Y);

            } else {

                break;
            }
        }

        ZYPTableUtil.clear(cMsg.B);
        NLBL3070CommonLogic.pagenation(cMsg, sMsg, cMsg.B.length() * (cMsg.xxPageShowCurNum_B.getValueInt() - 1));
    }

    /**
     * doProcess_NLBL3070Scrn00_OnChange_ChkBoxSoNum
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_OnChange_ChkBoxSoNum(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        int eventLine = cMsg.xxNum_EV.getValueInt();
        String val =  "";
        String soNum = "";
        int sIndex = 0;

        /** QC#16924# 01/30/2017 M.Naito Start **/
        for (int i = eventLine; i < cMsg.B.getValidCount(); i++) {

            if (eventLine == i) {

                val = cMsg.B.no(i).xxChkBox_B1.getValue();

                if (!SO_LINE_001.equals(cMsg.B.no(eventLine).soSlpNum_B1.getValue())) {
                    // If check xxChkBox_A1 of detail line
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxChkBox_B1, "");
                } else {
                    soNum = cMsg.B.no(i).soNum_B1.getValue();
                    sIndex = cMsg.B.no(i).xxNum_B1.getValueInt();
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxChkBox_B2, val);
                }
                continue;
            }

            if (soNum.equals(cMsg.B.no(i).soNum_B1.getValue()) && ZYPCommonFunc.hasValue(cMsg.B.no(i).soSlpNum_B1.getValue())) {

                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxChkBox_B2, val);

            } else {

                break;
            }
        }
        /** QC#16924# 01/30/2017 M.Naito End **/

        for (int i = sIndex; i < sMsg.B.getValidCount(); i++) {

            if (sIndex == i) {

                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxChkBox_B2, val);
                continue;
            }

            if (soNum.equals(sMsg.B.no(i).soNum_B1.getValue())) {

                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxChkBox_B2, val);

            } else {

                break;
            }
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_OnChange_ChkBoxSoSlipNum
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_OnChange_ChkBoxSoSlipNum(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        int eventLine = cMsg.xxNum_EV.getValueInt();
        String val =  cMsg.B.no(eventLine).xxChkBox_B2.getValue();
        int sLineNum = cMsg.B.no(eventLine).xxNum_B1.getValueInt();
        int soHdrLineNum = sLineNum;

        // ON --> OFF
        String preSoNum = sMsg.B.no(sLineNum).soNum_B1.getValue();

        // sMsg
        if (!ZYPConstant.CHKBOX_ON_Y.equals(val)) {

            for (int i = sLineNum; i >= 0; i--) {

                if (i == sLineNum) {

                    sMsg.B.no(i).xxChkBox_B2.clear();
                }

                if (preSoNum.equals(sMsg.B.no(i).soNum_B1.getValue())) {

                    sMsg.B.no(i).xxChkBox_B1.clear();
                    soHdrLineNum = i;

                } else {

                    soHdrLineNum = i + 1;
                    break;
                }
            }

            // cMsg
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {

                if (soHdrLineNum == cMsg.B.no(i).xxNum_B1.getValueInt()) {

                    cMsg.B.no(i).xxChkBox_B1.clear();
                    break;
                }
            }

            return;
        }

        // OFF --> ON
        int offCount = 0;
        soHdrLineNum = sLineNum;

        if (ZYPConstant.CHKBOX_ON_Y.equals(val)) {

            // Back Line
            preSoNum = sMsg.B.no(sLineNum).soNum_B1.getValue();

            for (int i = sLineNum; i >= 0; i--) {

                if (i == sLineNum) {

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxChkBox_B2, ZYPConstant.CHKBOX_ON_Y);
                    continue;
                }

                if (preSoNum.equals(sMsg.B.no(i).soNum_B1.getValue())) {

                    if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B2.getValue())) {

                        // Off
                        offCount++;
                    }

                    soHdrLineNum = i;

                } else {

                    soHdrLineNum = i + 1;
                    break;
                }
            }

            // Ahead Line
            preSoNum = sMsg.B.no(sLineNum).soNum_B1.getValue();

            for (int i = sLineNum; i < sMsg.B.getValidCount(); i++) {

                if (i == sLineNum) {

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxChkBox_B2, ZYPConstant.CHKBOX_ON_Y);
                    continue;
                }

                if (preSoNum.equals(sMsg.B.no(i).soNum_B1.getValue())) {

                    if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B2.getValue())) {

                        // Off
                        offCount++;
                    }

                } else {

                    break;
                }
            }

            if (offCount == 0) {

                ZYPEZDItemValueSetter.setValue(sMsg.B.no(soHdrLineNum).xxChkBox_B1, ZYPConstant.CHKBOX_ON_Y);
            }

            // cMsg
            if (offCount == 0) {

                for (int i = 0; i < cMsg.B.getValidCount(); i++) {

                    NLBL3070_BCMsg cMsgBLine = cMsg.B.no(i);

                    if (soHdrLineNum == cMsgBLine.xxNum_B1.getValueInt()) {

                        ZYPEZDItemValueSetter.setValue(cMsgBLine.xxChkBox_B1, ZYPConstant.CHKBOX_ON_Y);
                        break;
                    }
                }
            }

            return;
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_OpenWin_SerEntry
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_OpenWin_SerEntry(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        // Get Serial# in sMsg
        String soNum = cMsg.B.no(cMsg.xxNum_EV.getValueInt()).soNum_B1.getValue();
        String soSlip = cMsg.B.no(cMsg.xxNum_EV.getValueInt()).soSlpNum_B1.getValue();
        String bolNum = cMsg.B.no(cMsg.xxNum_EV.getValueInt()).proNum_B1.getValue();
        String openFlg = cMsg.B.no(cMsg.xxNum_EV.getValueInt()).soLineOpenFlg_B1.getValue();
        ZYPTableUtil.clear(cMsg.S);
        int serNumCnt = 0;

        for (int i = 0; i < sMsg.S.getValidCount(); i++) {

            if (soNum.equals(sMsg.S.no(i).soNum_S1.getValue()) && soSlip.equals(sMsg.S.no(i).soSlpNum_S1.getValue())) {

                if (ZYPCommonFunc.hasValue(bolNum)) {

                    if (bolNum.equals(sMsg.S.no(i).bolNum_S1.getValue())) {

                        // Copy
                        EZDMsg.copy(sMsg.S.no(i), "S1", cMsg.S.no(serNumCnt), "SN");
                        ZYPEZDItemValueSetter.setValue(cMsg.S.no(serNumCnt).xxHdrNum_SN, sMsg.S.no(i).soNum_S1.getValue());
                        ZYPEZDItemValueSetter.setValue(cMsg.S.no(serNumCnt).xxDplyTrxNumTxt_SN, sMsg.S.no(i).soSlpNum_S1.getValue());

                        if (ZYPConstant.FLG_ON_Y.equals(openFlg)) {

                            ZYPEZDItemValueSetter.setValue(cMsg.S.no(serNumCnt).xxEdtModeFlg_SN, ZYPConstant.FLG_ON_Y);

                        } else {

                            ZYPEZDItemValueSetter.setValue(cMsg.S.no(serNumCnt).xxEdtModeFlg_SN, ZYPConstant.FLG_OFF_N);
                        }

                        ZYPEZDItemValueSetter.setValue(cMsg.S.no(serNumCnt).xxRqstTpCd_SN, "2");
                        serNumCnt++;
                    }

                } else {

                    // Copy
                    EZDMsg.copy(sMsg.S.no(i), "S1", cMsg.S.no(serNumCnt), "SN");
                    ZYPEZDItemValueSetter.setValue(cMsg.S.no(serNumCnt).xxHdrNum_SN, sMsg.S.no(i).soNum_S1.getValue());
                    ZYPEZDItemValueSetter.setValue(cMsg.S.no(serNumCnt).xxDplyTrxNumTxt_SN, sMsg.S.no(i).soSlpNum_S1.getValue());

                    if (ZYPConstant.FLG_ON_Y.equals(openFlg)) {

                        ZYPEZDItemValueSetter.setValue(cMsg.S.no(serNumCnt).xxEdtModeFlg_SN, ZYPConstant.FLG_ON_Y);

                    } else {

                        ZYPEZDItemValueSetter.setValue(cMsg.S.no(serNumCnt).xxEdtModeFlg_SN, ZYPConstant.FLG_OFF_N);
                    }

                    ZYPEZDItemValueSetter.setValue(cMsg.S.no(serNumCnt).xxRqstTpCd_SN, "2");
                    serNumCnt++;
                }

                cMsg.S.setValidCount(serNumCnt);
            }
        }

        if (serNumCnt == 0) {

            // Get Serial# in DB
            S21SsmEZDResult result = NLBL3070Query.getInstance().getSerNumList(cMsg.B.no(cMsg.xxNum_EV.getValueInt()), cMsg.glblCmpyCd.getValue());

            if (result.isCodeNormal()) {

                if (result.getQueryResultCount() == 0) {

                    return;

                } else {

                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) result.getResultObject();
                    int size = 0;

                    for (int i = 0; i < resultList.size() && i < cMsg.S.length(); i++) {

                        Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);

                        ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxHdrNum_SN, (String) resultMap.get("SO_NUM"));
                        ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxDplyTrxNumTxt_SN, (String) resultMap.get("SO_SLP_NUM"));
                        ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).serNum_SN, (String) resultMap.get("SER_NUM"));
                        ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).mdseCd_SN, (String) resultMap.get("MDSE_CD"));

                        if (ZYPCommonFunc.hasValue((String) resultMap.get("BOL_NUM"))) {

                            ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxEdtModeFlg_SN, ZYPConstant.FLG_OFF_N);

                        } else {

                            ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxEdtModeFlg_SN, ZYPConstant.FLG_ON_Y);
                        }

                        ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxTrxRefPk_SN, (BigDecimal) resultMap.get("SO_SER_PK"));
                        ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxRqstTpCd_SN, "2");
                        size++;

                        NLBL3070_SSMsg sMsgSLine = sMsg.S.no(sMsg.S.getValidCount());
                        ZYPEZDItemValueSetter.setValue(sMsgSLine.soNum_S1, (String) resultMap.get("SO_NUM"));
                        ZYPEZDItemValueSetter.setValue(sMsgSLine.soSlpNum_S1, (String) resultMap.get("SO_SLP_NUM"));
                        ZYPEZDItemValueSetter.setValue(sMsgSLine.serNum_S1, (String) resultMap.get("SER_NUM"));
                        ZYPEZDItemValueSetter.setValue(sMsgSLine.mdseCd_S1, (String) resultMap.get("MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(sMsgSLine.bolNum_S1, (String) resultMap.get("BOL_NUM"));
                        ZYPEZDItemValueSetter.setValue(sMsgSLine.xxTrxRefPk_S1, (BigDecimal) resultMap.get("SO_SER_PK"));
                        ZYPEZDItemValueSetter.setValue(sMsgSLine.svcMachMstrPk_S1, (BigDecimal) resultMap.get("SVC_MACH_MSTR_PK"));
                    }

                    cMsg.S.setValidCount(size);
                }
            }
        }
    }

    /**
     * doProcess_NLBL3070_NLBL3000
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070_NLBL3000(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        // Delete
        List<Integer> delRows = new ArrayList<Integer>();

        for (int i = 0; i < cMsg.S.getValidCount(); i++) {

            // Delete 
            for (int j = 0; j < sMsg.S.getValidCount(); j++) {

                if (cMsg.S.no(i).xxHdrNum_SN.getValue().equals(sMsg.S.no(j).soNum_S1.getValue())
                        && cMsg.S.no(i).xxDplyTrxNumTxt_SN.getValue().equals(sMsg.S.no(j).soSlpNum_S1.getValue())) {
                    delRows.add(new Integer(j));
                }
            }
        }

        ZYPTableUtil.deleteRows(sMsg.S, delRows);

        int eventLine = cMsg.xxNum_EV.getValueInt();
        int maxLength = cMsg.B.no(eventLine).getAttr("serNum_B1").getDigit();
        StringBuilder sb = new StringBuilder();
        boolean setSerVal = false;

        // Insert
        for (int i = 0; i < cMsg.S.getValidCount(); i++) {

            NLBL3070_SSMsg serNumList = sMsg.S.no(sMsg.S.getValidCount());
            ZYPEZDItemValueSetter.setValue(serNumList.serNum_S1, cMsg.S.no(i).serNum_SN.getValue());
            ZYPEZDItemValueSetter.setValue(serNumList.mdseCd_S1, cMsg.S.no(i).mdseCd_SN.getValue());
            ZYPEZDItemValueSetter.setValue(serNumList.soNum_S1, cMsg.S.no(i).xxHdrNum_SN.getValue());
            ZYPEZDItemValueSetter.setValue(serNumList.soSlpNum_S1, cMsg.S.no(i).xxDplyTrxNumTxt_SN.getValue());
            sMsg.S.setValidCount(sMsg.S.getValidCount() + 1);

            if (ZYPCommonFunc.hasValue(serNumList.serNum_S1)) {

                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(serNumList.serNum_S1.getValue());

                if (sb.length() <= maxLength) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(eventLine).serNum_B1, sb.toString());
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(eventLine).serNum_B1, sb.toString().substring(0, maxLength));
                }
                setSerVal = true;
            } else {
                if (!setSerVal) {
                    cMsg.B.no(eventLine).serNum_B1.clear();
                }
            }

        }
    }

    /**
     * doProcess_NLBL3070Scrn00_SaveSearch
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_SaveSearch(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {
        // There is no process.
        return;
    }

    /**
     * doProcess_NLBL3070Scrn00_DeleteSearch
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_DeleteSearch(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {
        // There is no process.
        return;
    }

    /**
     * doProcess_NNLBL3070Scrn00_CMN_ColClear
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NNLBL3070Scrn00_CMN_ColClear(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {
        // There is no process.
        return;
    }

    /**
     * doProcess_NLBL3070Scrn00_CMN_ColSave
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_CMN_ColSave(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {
        // There is no process.
        return;
    }

    /**
     * doProcess_NLBL3070_OnChangeSavedSearchOption
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070_OnChangeSavedSearchOption(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {

            NLBL3070CommonLogic.callNszc0330forSearchOption(cMsg, getContextUserInfo().getUserId());
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_CMN_Download
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_CMN_Download(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NLBL3070Constant.MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NLBL3070Query.getInstance().getClass());

            //create csv file, parameters
            Map<String, Object> ssMParam = null;
            String ssmId = "";

            if (NLBL3070Constant.TAB_ID_SCHD.equals(cMsg.xxDplyTab.getValue())) {

                cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NLBL3070Constant.CSV_FILE_NAME_SCHD), ".csv");
                ssMParam = NLBL3070Query.getSsmParamDownLoadScheduling(cMsg, NLBL3070Constant.LIMIT_DL_ROWNUM + 1);
                ssmId = "SearchSchd";

            } else if (NLBL3070Constant.TAB_ID_DELV.equals(cMsg.xxDplyTab.getValue())) {

                cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NLBL3070Constant.CSV_FILE_NAME_DELV), ".csv");
                ssMParam = NLBL3070Query.getSsmParamDownLoadDeliveries(cMsg, NLBL3070Constant.LIMIT_DL_ROWNUM + 1);
                ssmId = "SearchDely";
            }

            ps = ssmLLClient.createPreparedStatement(ssmId, ssMParam, execParam);
            rs = ps.executeQuery();

            if (NLBL3070Constant.TAB_ID_SCHD.equals(cMsg.xxDplyTab.getValue())) {

                writeCsvFileScheduling(cMsg, rs);

            } else if (NLBL3070Constant.TAB_ID_DELV.equals(cMsg.xxDplyTab.getValue())) {

                writeCsvFileDeliveries(cMsg, rs);
            }

        } catch (SQLException e) {

            throw new S21AbendException(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_TBLColumnSort
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_TBLColumnSort(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (NLBL3070Constant.TAB_ID_SCHD.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3070CommonLogic.saveCurrentPageToSMsgScheduling(cMsg, sMsg);

            String sortTblNm = cMsg.xxSortTblNm_A.getValue();
            String sortItemNm = cMsg.xxSortItemNm_A.getValue();
            String sortOrdBy  = cMsg.xxSortOrdByTxt_A.getValue();

            if ("A".equals(sortTblNm)) {

                // execute column sort function
                NLBL3070CommonLogic.sort(sMsg.A, sortItemNm, sortOrdBy, sMsg.A.no(0).getBaseContents(), false);

                int i = 0;

                for ( ; i < sMsg.A.getValidCount(); i++) {

                    if (i == cMsg.A.length()) {

                        break;
                    }

                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }

                cMsg.A.setValidCount(i);
                cMsg.xxPageShowFromNum_A.setValue(1);
                cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
            }

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3070CommonLogic.saveCurrentPageToSMsgDeliveries(cMsg, sMsg);

            String sortTblNm = cMsg.xxSortTblNm_B.getValue();
            String sortItemNm = cMsg.xxSortItemNm_B.getValue();
            String sortOrdBy  = cMsg.xxSortOrdByTxt_B.getValue();

            if ("B".equals(sortTblNm)) {

                // execute column sort function
                NLBL3070CommonLogic.sort(sMsg.B, sortItemNm, sortOrdBy, sMsg.B.no(0).getBaseContents(), false);

                for (int i = 0; i < sMsg.B.getValidCount(); i++) {

                    NLBL3070_BSMsg sMsgBLine = sMsg.B.no(i);
                    ZYPEZDItemValueSetter.setValue(sMsgBLine.xxNum_B1, new BigDecimal(i));
                }

                NLBL3070CommonLogic.pagenation(cMsg, sMsg, 0);
            }
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_CMN_Submit
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_CMN_Submit(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue())) {

            EZDMessageInfo msgInfo =  cMsg.getMessageInfo();
            searchScheduling(cMsg, sMsg, NLBL3070Constant.SEARCH_TP_RESEARCH);

            if (msgInfo != null) {

                cMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_Release_SO
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Release_SO(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind())) {

            EZDMessageInfo msgInfo =  cMsg.getMessageInfo();
            searchScheduling(cMsg, sMsg, NLBL3070Constant.SEARCH_TP_RESEARCH);

            if (msgInfo != null) {

                cMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_Send_Rqst
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Send_Rqst(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind())) {

            EZDMessageInfo msgInfo =  cMsg.getMessageInfo();
            searchScheduling(cMsg, sMsg, NLBL3070Constant.SEARCH_TP_RESEARCH);

            if (msgInfo != null) {

                cMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_SO_Cancel
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_SO_Cancel(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind())) {

            EZDMessageInfo msgInfo =  cMsg.getMessageInfo();
            searchDeliveries(cMsg, sMsg, NLBL3070Constant.SEARCH_TP_RESEARCH);

            if (0 < sMsg.A.getValidCount()) {

                searchScheduling(cMsg, sMsg, NLBL3070Constant.SEARCH_TP_RESEARCH);
            }

            if (msgInfo != null) {

                cMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_Ship
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Ship(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue())) {

            EZDMessageInfo msgInfo =  cMsg.getMessageInfo();
            searchDeliveries(cMsg, sMsg, NLBL3070Constant.SEARCH_TP_RESEARCH);

            if (0 < sMsg.A.getValidCount()) {

                searchScheduling(cMsg, sMsg, NLBL3070Constant.SEARCH_TP_RESEARCH);
            }

            if (msgInfo != null) {

                cMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_SO_Close
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_SO_Close(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind())) {

            EZDMessageInfo msgInfo =  cMsg.getMessageInfo();
            searchDeliveries(cMsg, sMsg, NLBL3070Constant.SEARCH_TP_RESEARCH);

            if (0 < sMsg.A.getValidCount()) {

                searchScheduling(cMsg, sMsg, NLBL3070Constant.SEARCH_TP_RESEARCH);
            }

            if (msgInfo != null) {

                cMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }
    }

    /**
     * doProcess_NLBL3070Scrn00_Delivery_Conf
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_Delivery_Conf(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        EZDMessageInfo msgInfo =  cMsg.getMessageInfo();

        if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind())) {

            searchDeliveries(cMsg, sMsg, NLBL3070Constant.SEARCH_TP_RESEARCH);

            if (0 < sMsg.A.getValidCount()) {

                searchScheduling(cMsg, sMsg, NLBL3070Constant.SEARCH_TP_RESEARCH);
            }

            if (msgInfo != null) {

                cMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }
    }

    /**
     * checkInputHeader
     * @param cMsg NLBL3070CMsg
     */
    private boolean checkInputHeader(NLBL3070CMsg cMsg) {

        boolean rc = true;

        // Source WH
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {

            RTL_WHTMsg rtlWh = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWh.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rtlWh.rtlWhCd, cMsg.rtlWhCd.getValue());
            rtlWh = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWh);

            if (rtlWh == null) {

                cMsg.rtlWhCd.setErrorInfo(1, NLBL3070Constant.NLZM2278E, new String[] {"Source WH" });
                cMsg.rtlWhNm.clear();
                rc = false;

            } else {

                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, rtlWh.rtlWhNm);
            }

        } else {

            cMsg.rtlWhNm.clear();
        }

        // Source SWH
        if (ZYPCommonFunc.hasValue(cMsg.rtlSwhCd)) {

            SWHTMsg swh = new SWHTMsg();
            ZYPEZDItemValueSetter.setValue(swh.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(swh.rtlSwhCd, cMsg.rtlSwhCd.getValue());
            swh = (SWHTMsg) EZDTBLAccessor.findByKey(swh);

            if (swh == null) {

                cMsg.rtlSwhCd.setErrorInfo(1, NLBL3070Constant.NLZM2278E, new String[] {"Source SWH" });
                cMsg.rtlSwhNm.clear();
                rc = false;

            } else {

                ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, swh.rtlSwhNm);
            }

        } else {

            cMsg.rtlSwhNm.clear();
        }

        // Combination Source WH and Source SWH
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd) && ZYPCommonFunc.hasValue(cMsg.rtlSwhCd) && rc) {

            RTL_SWHTMsg rtlSwh = new RTL_SWHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlSwh.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rtlSwh.rtlWhCd, cMsg.rtlWhCd.getValue());
            ZYPEZDItemValueSetter.setValue(rtlSwh.rtlSwhCd, cMsg.rtlSwhCd.getValue());
            rtlSwh = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSwh);

            if (rtlSwh == null) {

                cMsg.rtlWhCd.setErrorInfo(1, NLBL3070Constant.NLZM2279E, new String[] {"Source WH", "Source SWH" });
                cMsg.rtlSwhCd.setErrorInfo(1, NLBL3070Constant.NLZM2279E, new String[] {"Source WH", "Source SWH" });
                rc = false;
            }
        }

        // Ship To Customer
        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd)) {

            SHIP_TO_CUSTTMsg shipToCust = new SHIP_TO_CUSTTMsg();
            shipToCust.setSQLID("004");
            shipToCust.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            shipToCust.setConditionValue("shipToCustCd01", cMsg.shipToCustCd.getValue());
            SHIP_TO_CUSTTMsgArray shipToCustList = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipToCust);

            if (shipToCustList == null || shipToCustList.getValidCount() == 0) {

                cMsg.shipToCustCd.setErrorInfo(1, NLBL3070Constant.NLZM2278E, new String[] {"Ship To Customer" });
                cMsg.shipToCustNm.clear();
                rc = false;

            } else {

                // sMsg does not use.
                doProcess_NLBL3070Scrn00_Search_ShipToCustInfo(cMsg, null);
            }

        } else {

            cMsg.shipToCustNm.clear();
        }

        // Carrier
        if (ZYPCommonFunc.hasValue(cMsg.carrCd)) {

            OTBD_CARR_VTMsg otbdCarrV = new OTBD_CARR_VTMsg();
            otbdCarrV.setSQLID("001");
            otbdCarrV.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            otbdCarrV.setConditionValue("carrCd01", cMsg.carrCd.getValue());
            OTBD_CARR_VTMsgArray otbdCarrVList = (OTBD_CARR_VTMsgArray) EZDTBLAccessor.findByCondition(otbdCarrV);

            if (otbdCarrVList == null || otbdCarrVList.getValidCount() == 0) {

                cMsg.carrCd.setErrorInfo(1, NLBL3070Constant.NLZM2278E, new String[] {"Carrier" });
                cMsg.carrNm.clear();
                rc = false;

            } else {

                // sMsg does not use.
                doProcess_NLBL3070Scrn00_Search_CarrInfo(cMsg, null);
            }

        } else {

            cMsg.carrNm.clear();
        }

        // Coordinator
        if (ZYPCommonFunc.hasValue(cMsg.schdCoordPsnCd)) {

            S21_PSNTMsg psn = new S21_PSNTMsg();
            ZYPEZDItemValueSetter.setValue(psn.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(psn.psnCd, cMsg.schdCoordPsnCd.getValue());
            psn = (S21_PSNTMsg) EZDTBLAccessor.findByKey(psn);

            if (psn == null) {

                cMsg.schdCoordPsnCd.setErrorInfo(1, NLBL3070Constant.NLZM2278E, new String[] {"Coordinator" });
                cMsg.xxPsnFirstMidLastNm.clear();
                rc = false;

            } else {

                ZYPEZDItemValueSetter.setValue(cMsg.xxPsnFirstMidLastNm, psn.psnFirstNm.getValue() + " " + psn.psnLastNm.getValue());
            }

        } else {

            cMsg.xxPsnFirstMidLastNm.clear();
        }

        // Model
        if (ZYPCommonFunc.hasValue(cMsg.t_MdlNm)) {

            MDL_NMTMsg mdlNm = new MDL_NMTMsg();
            mdlNm.setSQLID("001");
            mdlNm.setConditionValue("t_GlblCmpyCd01", cMsg.glblCmpyCd.getValue());
            mdlNm.setConditionValue("t_MdlNm01", cMsg.t_MdlNm.getValue());
            MDL_NMTMsgArray mdlNmList = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlNm);

            if (mdlNmList == null || mdlNmList.getValidCount() == 0) {

                cMsg.t_MdlNm.setErrorInfo(1, NLBL3070Constant.NLZM2278E, new String[] {"Model" });
                rc = false;
            }
        }

        return rc;
    }

    /**
     * clearAll
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void clearAll(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        // Clear Table
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.S);
        sMsg.clear();

        // Header
        cMsg.srchOptPk_S.clear();
        cMsg.srchOptNm_S.clear();
        cMsg.trxHdrNum.clear();
        cMsg.dsOrdCatgCd.clear();
        cMsg.dsOrdCatgCd_P.clear();
        cMsg.dsOrdCatgDescTxt_P.clear();
        cMsg.dsOrdTpCd.clear();
        cMsg.dsOrdTpCd_P.clear();
        cMsg.dsOrdTpDescTxt_P.clear();
        cMsg.xxLinkAncr_ML.clear();
        cMsg.t_MdlNm.clear();
        cMsg.svcConfigMstrPk.clear();
        cMsg.soNum.clear();
        cMsg.dsSoLineStsCd.clear();
        cMsg.dsSoLineStsCd_P.clear();
        cMsg.dsSoLineStsDescTxt_P.clear();
        cMsg.shpgSvcLvlCd.clear();
        cMsg.shpgSvcLvlCd_P.clear();
        cMsg.shpgSvcLvlDescTxt_P.clear();
        cMsg.schdCoordStsCd.clear();
        cMsg.schdCoordStsCd_P.clear();
        cMsg.schdCoordStsDescTxt_P.clear();
        cMsg.bolNum.clear();
        cMsg.rtlWhCd.clear();
        cMsg.rtlWhNm.clear();
        cMsg.rtlSwhCd.clear();
        cMsg.rtlSwhNm.clear();
        cMsg.shipToCustCd.clear();
        cMsg.shipToCustNm.clear();
        cMsg.carrCd.clear();
        cMsg.carrNm.clear();
        cMsg.schdCoordPsnCd.clear();
        cMsg.xxPsnFirstMidLastNm.clear();
        cMsg.rddDt_FR.clear();
        cMsg.rddDt_TO.clear();
        cMsg.schdCarrPickUpDt_FR.clear();
        cMsg.schdCarrPickUpDt_TO.clear();
        cMsg.schdDelyDt_FR.clear();
        cMsg.schdDelyDt_TO.clear();
        cMsg.actlDelyDt_FR.clear();
        cMsg.actlDelyDt_TO.clear();
        cMsg.wmsDropFlg_Y.clear();
        cMsg.wmsDropFlg_N.clear();
        cMsg.delyReqFlg_Y.clear();
        cMsg.delyReqFlg_N.clear();

        // Scheduling Tab
        cMsg.xxPageShowFromNum_A.clear();
        cMsg.xxPageShowToNum_A.clear();
        cMsg.xxPageShowOfNum_A.clear();
        cMsg.xxPageShowCurNum_A.clear();
        cMsg.xxPageShowTotNum_A.clear();
        cMsg.xxSortTblNm_A.clear();
        cMsg.xxSortItemNm_A.clear();
        cMsg.xxSortOrdByTxt_A.clear();
        cMsg.schdCarrPickUpDt_AP.clear();
        cMsg.schdDelyDt_AP.clear();
        // QC#18272 Add.
        cMsg.schdDelyTsDplyTxt_AP.clear();
        cMsg.rqstRcvDtTxt_P2.clear();

        cMsg.shpgSvcLvlCd_AP.clear();
        cMsg.schdCoordStsCd_AP.clear();
        cMsg.carrCd_AP.clear();
        cMsg.carrNm_AP.clear();
        cMsg.rqstRcvDtTxt_CD.clear();
        cMsg.rqstRcvDtTxt_DI.clear();
        cMsg.tempSchdRsnCd_P.clear();
        cMsg.tempSchdRsnDescTxt_P.clear();

        // Deliveries Tab
        cMsg.xxPageShowFromNum_B.clear();
        cMsg.xxPageShowToNum_B.clear();
        cMsg.xxPageShowOfNum_B.clear();
        cMsg.xxPageShowCurNum_B.clear();
        cMsg.xxPageShowTotNum_B.clear();
        cMsg.xxSortTblNm_B.clear();
        cMsg.xxSortItemNm_B.clear();
        cMsg.xxSortOrdByTxt_B.clear();
        cMsg.carrCd_BP.clear();
        cMsg.carrNm_BP.clear();
        cMsg.proNum_BP.clear();
        cMsg.totFrtAmt_BP.clear();
        cMsg.carrRsnCd_BP.clear();
        cMsg.actlDelyDt_BP.clear();
        // QC#18272 Mod.
        cMsg.schdDelyTsDplyTxt_BP.clear();
        cMsg.carrRsnCd_P.clear();
        cMsg.carrRsnDescTxt_P.clear();

        // Hidden
        cMsg.xxWrnSkipFlg.clear();
    }

    /**
     * search
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void search(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        cMsg.xxWrnSkipFlg.clear();

        if (NLBL3070Constant.TAB_ID_SCHD.equals(cMsg.xxDplyTab.getValue())) {

            // Clear Apply Area
            cMsg.schdCarrPickUpDt_AP.clear();
            cMsg.schdDelyDt_AP.clear();
            // QC#18272 Add.
            cMsg.schdDelyTsDplyTxt_AP.clear();
            cMsg.rqstRcvDtTxt_P2.clear();

            cMsg.shpgSvcLvlCd_AP.clear();
            cMsg.carrCd_AP.clear();
            cMsg.carrNm_AP.clear();
            cMsg.schdCoordStsCd_AP.clear();

            searchScheduling(cMsg, sMsg, NLBL3070Constant.SEARCH_TP_COND);

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(cMsg.xxDplyTab.getValue())) {

            // Clear Apply Area
            cMsg.carrCd_BP.clear();
            cMsg.carrNm_BP.clear();
            cMsg.proNum_BP.clear();
            cMsg.totFrtAmt_BP.clear();
            cMsg.carrRsnCd_BP.clear();
            cMsg.actlDelyDt_BP.clear();
            // QC#18272 Add.
            cMsg.schdDelyTsDplyTxt_BP.clear();
            cMsg.rqstRcvDtTxt_BP.clear();

            searchDeliveries(cMsg, sMsg, NLBL3070Constant.SEARCH_TP_COND);
        }
    }

    /**
     * searchScheduling
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @param searchTp String
     */
    private void searchScheduling(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg, String searchTp) {

        S21SsmEZDResult result = null;

        if (NLBL3070Constant.SEARCH_TP_COND.equals(searchTp) || NLBL3070Constant.SEARCH_TP_COND_SCHD.equals(searchTp)) {

            result = NLBL3070Query.getInstance().searchSchd(cMsg, sMsg);

            ZYPTableUtil.clear(cMsg.B);
            ZYPTableUtil.clear(sMsg.B);
            ZYPTableUtil.clear(sMsg.S);

        } else if (NLBL3070Constant.SEARCH_TP_RESEARCH.equals(searchTp)) {

            result = NLBL3070Query.getInstance().reSearchSchd(cMsg, sMsg);

        } else if (NLBL3070Constant.SEARCH_TP_TAB.equals(searchTp)) {

            result = NLBL3070Query.getInstance().searchSchdTab(cMsg, sMsg);
        }

        if (result.isCodeNormal()) {

            int queryResCnt = result.getQueryResultCount();

            if (queryResCnt > sMsg.A.length() - 1) {

                cMsg.setMessageInfo(NLBL3070Constant.NZZM0001W);
                sMsg.A.setValidCount(sMsg.A.length() - 1);
                queryResCnt = sMsg.A.length() - 1;
            }

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).schdOpenFlg_A1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdCoordStsCd_AL.no(0), sMsg.A.no(i).schdCoordStsCd_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdCoordStsDescTxt_AL.no(0), sMsg.A.no(i).schdCoordStsDescTxt_A1);

                } else {

                    createSchdCoordStsPullDown(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i));
                }

                // QC18272 Scheduled Delivery Time
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyTsDplyTxt_A2)) {

                 // 201/12/28 QC#18460(SOL#087) T,Hakodate ADD START

                    String systemTimeZoneTs = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());

                    if(ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyDt_A1)){
                        systemTimeZoneTs = sMsg.A.no(i).schdDelyDt_A1.getValue();
                    }

                    // Date + time
                    systemTimeZoneTs = systemTimeZoneTs + sMsg.A.no(i).schdDelyTsDplyTxt_A2.getValue();

                    SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, systemTimeZoneTs, sMsg.A.no(i).ctryCd_A1.getValue(), sMsg.A.no(i).postCd_A1.getValue());

                    String date = timeInfo.getDateTime().substring(0, 8);
                    String time = timeInfo.getDateTime().substring(8, 12);

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdDelyDt_A1, date);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdDelyDt_BK, date);

                    NLBL3070CommonLogic.splitHourMinute(sMsg.A.no(i).schdDelyTsDplyTxt_A2, sMsg.A.no(i).rqstRcvDtTxt_S2, time);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdDelyTsDplyTxt_K1, sMsg.A.no(i).schdDelyTsDplyTxt_A2);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rqstRcvDtTxt_K1, sMsg.A.no(i).rqstRcvDtTxt_S2);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rqstRcvDtTxt_BK, sMsg.A.no(i).rqstRcvDtTxt_S2);
                    // START 2020/02/14 [QC#50121, ADD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rqstRcvDtTxt_A1, sMsg.A.no(i).rqstRcvDtTxt_S2);
                    // END   2020/02/14 [QC#50121, ADD]
                }

             // 201/12/28 QC#18460(SOL#087) T,Hakodate DEL START
                // Timestop(Schedule Install Time)
//                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyTsDplyTxt_A1)) {
//
//                    NLBL3070CommonLogic.splitHourMinute(sMsg.A.no(i).schdDelyTsDplyTxt_A1, sMsg.A.no(i).rqstRcvDtTxt_A1, sMsg.A.no(i).schdDelyTsDplyTxt_A1.getValue());
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdDelyTsDplyTxt_BK, sMsg.A.no(i).schdDelyTsDplyTxt_A1);
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rqstRcvDtTxt_BK, sMsg.A.no(i).rqstRcvDtTxt_A1);
//                }
             // 201/12/28 QC#18460(SOL#087) T,Hakodate DEL END
            }

            // Copy from SMsg to cMsg
            int i = 0;

            for (; i < queryResCnt; i++) {

                if (i == cMsg.A.length()) {

                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            cMsg.A.setValidCount(i);

            // Set page number
            cMsg.xxPageShowFromNum_A.setValue(1);
            cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_A.setValue(queryResCnt);
            cMsg.setMessageInfo(NLBL3070Constant.ZZZM9003I, new String[]{"Search"});

        } else {

            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.A);
            cMsg.xxPageShowFromNum_A.clear();
            cMsg.xxPageShowToNum_A.clear();
            cMsg.xxPageShowOfNum_A.clear();

            if (!NLBL3070Constant.SEARCH_TP_COND_SCHD.equals(searchTp)) {

                cMsg.setMessageInfo(NLBL3070Constant.NZZM0000E);
            }
        }
    }

    /**
     * createSchdCoordStsPullDown
     * @param glblCmpyCd String
     * @param sMsgALine NLBL3070_ASMsg
     */
    private void createSchdCoordStsPullDown(String glblCmpyCd, NLBL3070_ASMsg sMsgALine) {

        S21SsmEZDResult ssmResult = NLBL3070Query.getInstance().getSchdCoordStsList(glblCmpyCd, sMsgALine);

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {

                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);

                if (i >= sMsgALine.schdCoordStsCd_AL.length()) {

                    break;
                }

                ZYPEZDItemValueSetter.setValue(sMsgALine.schdCoordStsCd_AL.no(i), (String) resultMap.get("SCHD_COORD_STS_CD"));
                ZYPEZDItemValueSetter.setValue(sMsgALine.schdCoordStsDescTxt_AL.no(i), (String) resultMap.get("SCHD_COORD_STS_DESC_TXT"));
            }
        }
    }

    /**
     * searchDeliveries
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @param searchTp String
     */
    private void searchDeliveries(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg, String searchTp) {

        cMsg.clearErrorInfo();

        S21SsmEZDResult result = null;

        if (NLBL3070Constant.SEARCH_TP_COND.equals(searchTp)) {

            result = NLBL3070Query.getInstance().searchDely(cMsg, sMsg);

            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.A);
            ZYPTableUtil.clear(sMsg.S);

        } else if (NLBL3070Constant.SEARCH_TP_RESEARCH.equals(searchTp)) {

            result = NLBL3070Query.getInstance().reSearchDely(cMsg, sMsg);

        } else if (NLBL3070Constant.SEARCH_TP_TAB.equals(searchTp)) {

            result = NLBL3070Query.getInstance().searchDelyTab(cMsg, sMsg);
        }

        if (result.isCodeNormal()) {

            int queryResCnt = result.getQueryResultCount();

            if (queryResCnt > sMsg.B.length() - 1) {

                cMsg.setMessageInfo(NLBL3070Constant.NZZM0001W);
                sMsg.B.setValidCount(sMsg.B.length() - 1);
                queryResCnt = sMsg.B.length() - 1;
            }

            for (int i = 0; i < sMsg.B.getValidCount(); i++) {

                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxNum_B1, new BigDecimal(i));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxSmryLineFlg_B1, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxDplyCtrlFlg_B1, ZYPConstant.FLG_ON_Y);

                // Get Serial# in DB
                if (ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).serNumTakeFlg_B1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).serNum_B1, NLBL3070CommonLogic.getSerListText(cMsg.glblCmpyCd.getValue(), sMsg.B.no(i), null, sMsg.B.no(i).shipFlg_B1.getValue()));
                }

                // QC18272 Actual Delivery Time
                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).schdDelyTsDplyTxt_B2)) {
                    // QC#20043
                    String hh = "00";
                    String mm = "00";
                    String ss = "00";
                    String hhmmss = sMsg.B.no(i).schdDelyTsDplyTxt_B2.getValue();
                    if (hhmmss.length() == NLBL3070Constant.HOUR_MINUTE_STR_LENGTH) {

                        hh = hhmmss.substring(0, 2);
                        mm = hhmmss.substring(2);

                    } else if (hhmmss.length() == NLBL3070Constant.HOUR_MINUTE_STR_LENGTH - 1) {

                        hhmmss = "0" + hhmmss;
                        hh = hhmmss.substring(0, 2);
                        mm = hhmmss.substring(2);

                    } else if (hhmmss.length() == NLBL3070Constant.HOUR_MINUTE_STR_LENGTH + 2) {
                        hh = hhmmss.substring(0, 2);
                        mm = hhmmss.substring(2, 4);
                        ss = hhmmss.substring(4);

                    }

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).schdDelyTsDplyTxt_B2, ZYPDateUtil.formatDispHHmmss(hh, mm, ss, true));
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).schdDelyTsDplyTxt_K2, sMsg.B.no(i).schdDelyTsDplyTxt_B2);
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rqstRcvDtTxt_K2, sMsg.B.no(i).rqstRcvDtTxt_B2);

                }

                // START 2018/03/23 S.Katsuma [QC#24697,MOD]
                // QC#18460 Scheduled Delivery Time
                String schdDelyTsDplyTxt = convSchdDelyTs(sMsg.B.no(i).schdDelyDt_B1.getValue(),
                        sMsg.B.no(i).schdDelyTsDplyTxt_B1.getValue(),
                        sMsg.B.no(i).ctryCd_B1.getValue(),
                        sMsg.B.no(i).postCd_B1.getValue());
                if (ZYPCommonFunc.hasValue(schdDelyTsDplyTxt)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).schdDelyTsDplyTxt_B1, schdDelyTsDplyTxt);
                } else {
                    sMsg.B.no(i).schdDelyTsDplyTxt_B1.clear();
                }
                // END 2018/03/23 S.Katsuma [QC#24697,MOD]
            }

            // Copy from SMsg to cMsg
            int i = 0;

            for (; i < queryResCnt; i++) {

                if (i == cMsg.B.length()) {

                    break;
                }

                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }

            cMsg.B.setValidCount(i);

            // Set page number
            cMsg.xxPageShowFromNum_B.setValue(1);
            cMsg.xxPageShowToNum_B.setValue(cMsg.B.getValidCount());
            cMsg.xxPageShowOfNum_B.setValue(queryResCnt);
            cMsg.setMessageInfo(NLBL3070Constant.ZZZM9003I, new String[]{"Search"});

        } else {

            ZYPTableUtil.clear(cMsg.B);
            ZYPTableUtil.clear(sMsg.B);
            cMsg.xxPageShowFromNum_B.clear();
            cMsg.xxPageShowToNum_B.clear();
            cMsg.xxPageShowOfNum_B.clear();
            // 07/05/2017 QC#19687 Mod.
//            cMsg.setMessageInfo(NLBL3070Constant.NZZM0000E);
            cMsg.setMessageInfo(NLBL3070Constant.NLBM1232I);

        }
    }

    /**
     * applyScheduling
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void applyScheduling(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        NLBL3070CommonLogic.saveCurrentPageToSMsgScheduling(cMsg, sMsg);

        // Input check in Detail
        boolean chkBoxOn = false;
        boolean hasErr = false;
        int firstErrIdx = -1;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {

                chkBoxOn = true;

                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).schdOpenFlg_A1.getValue())) {

                    // Not Open Status
                    sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NLBL3070Constant.NLBM1307E);
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }
            }
        }

        if (!chkBoxOn) {

            for (int i = 0; i < cMsg.A.getValidCount(); i++) {

                NLBL3070_ACMsg cMsgALine = cMsg.A.no(i);
                cMsgALine.xxChkBox_A1.setErrorInfo(1, NLBL3070Constant.NLBM0002E);
            }

            hasErr = true;
        }

        // Schedule Carrier Pickup Date
        if (ZYPCommonFunc.hasValue(cMsg.schdCarrPickUpDt_AP)) {

            if (0 < cMsg.slsDt.getValue().compareTo(cMsg.schdCarrPickUpDt_AP.getValue())) {

                // Past Date
                cMsg.schdCarrPickUpDt_AP.setErrorInfo(1, NLBL3070Constant.NLBM1231E, new String[]{"Scheduled Carrier Pickup Date"});
                hasErr = true;

            } else if (ZYPCommonFunc.hasValue(cMsg.schdDelyDt_AP) && 0 < cMsg.schdCarrPickUpDt_AP.getValue().compareTo(cMsg.schdDelyDt_AP.getValue())) {

                // Carrier Pickup > Delivery
                cMsg.schdCarrPickUpDt_AP.setErrorInfo(1, NLBL3070Constant.NFCM0780E, new String[]{"Schedule Delivery Date", "Schedule Carrier Pickup Date"});
                hasErr = true;
            }
        }

        // Schedule Delivery Date
        if (ZYPCommonFunc.hasValue(cMsg.schdDelyDt_AP)) {

            if (0 < cMsg.slsDt.getValue().compareTo(cMsg.schdDelyDt_AP.getValue())) {

                // Past Date
                cMsg.schdDelyDt_AP.setErrorInfo(1, NLBL3070Constant.NLBM1231E, new String[]{"Scheduled Delivery Date"});
                hasErr = true;

            } else if (ZYPCommonFunc.hasValue(cMsg.schdCarrPickUpDt_AP) && 0 < cMsg.schdCarrPickUpDt_AP.getValue().compareTo(cMsg.schdDelyDt_AP.getValue())) {

                // Carrier Pickup > Delivery
                cMsg.schdDelyDt_AP.setErrorInfo(1, NLBL3070Constant.NFCM0780E, new String[]{"Schedule Delivery Date", "Schedule Carrier Pickup Date"});
                hasErr = true;
            }
        }

        // Check Carrier Master
        if (ZYPCommonFunc.hasValue(cMsg.carrNm_AP)) {

            List<String> carrCdList = NLBL3070CommonLogic.getCarrCdList(cMsg.glblCmpyCd.getValue(), cMsg.carrNm_AP.getValue());

            if (carrCdList == null || carrCdList.isEmpty()) {

                cMsg.carrNm_AP.setErrorInfo(1, NLBL3070Constant.NLZM2278E, new String[]{"Carrier"});
                cMsg.carrCd_AP.clear();
                hasErr = true;

            } else if (carrCdList.size() > 1) {

                cMsg.carrNm_AP.setErrorInfo(1, NLBL3070Constant.NLBM1341E, new String[]{"Carriers"});
                cMsg.carrCd_AP.clear();
                hasErr = true;

            } else {

                ZYPEZDItemValueSetter.setValue(cMsg.carrCd_AP, carrCdList.get(0));
            }

        } else {

            cMsg.carrCd_AP.clear();
        }

        // Check Shipping Service Level
        if (ZYPCommonFunc.hasValue(cMsg.carrCd_AP) && ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_AP)) {

            List<String> carrSvcLvlCdList = NLBL3070CommonLogic.getCarrSvcLvlCdList(cMsg, cMsg.shpgSvcLvlCd_AP.getValue(), cMsg.carrCd_AP.getValue());

            if (carrSvcLvlCdList == null || carrSvcLvlCdList.isEmpty()) {

                cMsg.carrNm_AP.setErrorInfo(1, NLBL3070Constant.NLBM1308E, new String[]{"Service Level", "Carrier"});
                cMsg.shpgSvcLvlCd_AP.setErrorInfo(1, NLBL3070Constant.NLBM1308E, new String[]{"Service Level", "Carrier"});
                hasErr = true;
            }
        }

        if (hasErr) {

            if (firstErrIdx != -1) {

                NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            }

            return;
        }

        // Copy to selected lines
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {

                if (ZYPCommonFunc.hasValue(cMsg.schdCarrPickUpDt_AP)) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdCarrPickUpDt_A1, cMsg.schdCarrPickUpDt_AP.getValue());
                }

                if (ZYPCommonFunc.hasValue(cMsg.schdDelyDt_AP)) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdDelyDt_A1, cMsg.schdDelyDt_AP.getValue());
                }
                // QC#18272 Add.
                if (ZYPCommonFunc.hasValue(cMsg.schdDelyTsDplyTxt_AP)) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdDelyTsDplyTxt_A2, cMsg.schdDelyTsDplyTxt_AP.getValue());
                }

                if (ZYPCommonFunc.hasValue(cMsg.rqstRcvDtTxt_P2)) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rqstRcvDtTxt_S2, cMsg.rqstRcvDtTxt_P2.getValue());
                }

                if (ZYPCommonFunc.hasValue(cMsg.shpgSvcLvlCd_AP)) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shpgSvcLvlCd_A1, cMsg.shpgSvcLvlCd_AP.getValue());
                }

                if (ZYPCommonFunc.hasValue(cMsg.carrCd_AP)) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).carrCd_A1, cMsg.carrCd_AP.getValue());
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).carrNm_A1, cMsg.carrNm_AP.getValue());
                }

                if (ZYPCommonFunc.hasValue(cMsg.schdCoordStsCd_AP)) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdCoordStsCd_A1, cMsg.schdCoordStsCd_AP.getValue());
                }

                // Carrier Relation Check
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).carrCd_A1)) {

                    // Check Carrier Account
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(i).carrAcctNum_A1) && NLBL3070CommonLogic.changedValue(sMsg.A.no(i).carrCd_A1.getValue(), sMsg.A.no(i).carrCd_BK.getValue())) {

                        BigDecimal acctCarrCnt = NLBL3070CommonLogic.getAcctCarrCnt(cMsg, sMsg.A.no(i).carrCd_A1.getValue(), sMsg.A.no(i).dsAcctNum_A1.getValue(), sMsg.A.no(i).carrAcctNum_A1.getValue());

                        if (acctCarrCnt == null || acctCarrCnt.intValue() == 0) {

                            sMsg.A.no(i).carrNm_A1.setErrorInfo(2, NLBL3070Constant.NLBM1328W, new String[]{"Carrier", "Carrier Account#"});
                            sMsg.A.no(i).carrAcctNum_A1.setErrorInfo(2, NLBL3070Constant.NLBM1328W, new String[]{"Carrier", "Carrier Account#"});
                            hasErr = true;

                            if (firstErrIdx == -1) {

                                firstErrIdx = i;
                            }
                        }
                    }

                    // Check Shipping Service Level
                    if (NLBL3070CommonLogic.changedValue(sMsg.A.no(i).carrCd_A1.getValue(), sMsg.A.no(i).carrCd_BK.getValue())
                            || (NLBL3070CommonLogic.changedValue(sMsg.A.no(i).shpgSvcLvlCd_A1.getValue(), sMsg.A.no(i).shpgSvcLvlCd_BK.getValue()) && ZYPCommonFunc.hasValue(sMsg.A.no(i).shpgSvcLvlCd_A1))) {

                        List<String> carrSvcLvlCdList = NLBL3070CommonLogic.getCarrSvcLvlCdList(cMsg, sMsg.A.no(i).shpgSvcLvlCd_A1.getValue(), sMsg.A.no(i).carrCd_A1.getValue());

                        if (carrSvcLvlCdList == null || carrSvcLvlCdList.isEmpty()) {

                            sMsg.A.no(i).carrNm_A1.setErrorInfo(1, NLBL3070Constant.NLBM1308E, new String[]{"Service Level", "Carrier"});
                            sMsg.A.no(i).shpgSvcLvlCd_A1.setErrorInfo(1, NLBL3070Constant.NLBM1308E, new String[]{"Service Level", "Carrier"});
                            hasErr = true;

                            if (firstErrIdx == -1) {

                                firstErrIdx = i;
                            }
                        }
                    }
                }
            }
        }

        if (hasErr) {

            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            return;
        }

        // Clear Apply Area
        // START 2016/08/12 QC#9841 DEL
        // cMsg.schdDelyDt_AP.clear();
        // cMsg.shpgSvcLvlCd_AP.clear();
        // cMsg.carrCd_AP.clear();
        // cMsg.carrNm_AP.clear();
        // cMsg.schdCoordStsCd_AP.clear();
        // END 2016/08/12 QC#9841 DEL

        NLBL3070CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowFromNum_A.getValueInt() - 1);
    }

    /**
     * applyDeliveries
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void applyDeliveries(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        NLBL3070CommonLogic.saveCurrentPageToSMsgDeliveries(cMsg, sMsg);

        // Input check in Detail
        boolean chkBoxOn = false;
        boolean hasErr = false;
        int firstErrIdx = -1;

        // QC#20043
        String slsDt = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B2.getValue())) {

                chkBoxOn = true;

                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).soLineOpenFlg_B1.getValue())
                        && (ZYPCommonFunc.hasValue(cMsg.carrNm_BP)
                                || ZYPCommonFunc.hasValue(cMsg.proNum_BP)
                                || ZYPCommonFunc.hasValue(cMsg.totFrtAmt_BP))) {

                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM1342E, new String[]{"Shipping Order Lines which are not open", "shipping attributes"});
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                } else if (!ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).shipFlg_B1.getValue())
                        && (ZYPCommonFunc.hasValue(cMsg.carrRsnCd_BP)
                                || ZYPCommonFunc.hasValue(cMsg.actlDelyDt_BP))) {

                    sMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM1342E, new String[]{"Shipping Order Lines which are not shipped", "delivery attributes"});
                    hasErr = true;

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }
                }
            }
        }

        if (!chkBoxOn) {

            for (int i = 0; i < cMsg.B.getValidCount(); i++) {

                cMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NLBL3070Constant.NLBM0002E);
            }

            hasErr = true;
        }

        // Check Carrier Master
        if (ZYPCommonFunc.hasValue(cMsg.carrNm_BP)) {

            List<String> carrCdList = NLBL3070CommonLogic.getCarrCdList(cMsg.glblCmpyCd.getValue(), cMsg.carrNm_BP.getValue());

            if (carrCdList == null || carrCdList.isEmpty()) {

                cMsg.carrNm_BP.setErrorInfo(1, NLBL3070Constant.NLZM2278E, new String[]{"Carrier"});
                cMsg.carrCd_BP.clear();
                hasErr = true;

            } else if (carrCdList.size() > 1) {

                cMsg.carrNm_BP.setErrorInfo(1, NLBL3070Constant.NLBM1341E, new String[]{"Carriers"});
                cMsg.carrCd_BP.clear();
                hasErr = true;

            } else {

                ZYPEZDItemValueSetter.setValue(cMsg.carrCd_BP, carrCdList.get(0));
            }

        } else {

            cMsg.carrCd_BP.clear();
        }

        if (hasErr) {

            if (firstErrIdx != -1) {

                NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            }

            return;
        }

        // Copy to selected lines
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B2.getValue())) {

                if (ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).soLineOpenFlg_B1.getValue())) {

                    if (ZYPCommonFunc.hasValue(cMsg.carrCd_BP)) {

                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).carrCd_B1, cMsg.carrCd_BP.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).carrNm_B1, cMsg.carrNm_BP.getValue());
                    }

                    if (ZYPCommonFunc.hasValue(cMsg.proNum_BP)) {

                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).proNum_B1, cMsg.proNum_BP.getValue());
                    }

                    if (ZYPCommonFunc.hasValue(cMsg.totFrtAmt_BP)) {

                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).totFrtAmt_B1, cMsg.totFrtAmt_BP.getValue());
                    }

                } else if (ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).shipFlg_B1.getValue())) {

                    if (ZYPCommonFunc.hasValue(cMsg.carrRsnCd_BP)) {

                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).carrRsnCd_B1, cMsg.carrRsnCd_BP.getValue());
                    }

                    if (ZYPCommonFunc.hasValue(cMsg.actlDelyDt_BP)) {

                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).actlDelyDt_B1, cMsg.actlDelyDt_BP.getValue());
                    }

                    // QC#20043
                    String HHmmss = ZYPDateUtil.getCurrentSystemTime("HH:mm:ss");
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).schdDelyTsDplyTxt_B2, HHmmss);
                }

                // Carrier Relation Check
                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).carrCd_B1)) {
                    // Check Shipping Service Level
                    if (NLBL3070CommonLogic.changedValue(sMsg.B.no(i).carrCd_B1.getValue(), sMsg.B.no(i).carrCd_BK.getValue()) && ZYPCommonFunc.hasValue(sMsg.B.no(i).shpgSvcLvlCd_B1)) {

                        List<String> carrSvcLvlCdList = NLBL3070CommonLogic.getCarrSvcLvlCdList(cMsg, sMsg.B.no(i).shpgSvcLvlCd_B1.getValue(), sMsg.B.no(i).carrCd_B1.getValue());

                        if (carrSvcLvlCdList == null || carrSvcLvlCdList.isEmpty()) {

                            sMsg.B.no(i).carrNm_B1.setErrorInfo(1, NLBL3070Constant.NLBM1308E, new String[]{"Service Level", "Carrier"});
                            sMsg.B.no(i).shpgSvcLvlDescTxt_B1.setErrorInfo(1, NLBL3070Constant.NLBM1308E, new String[]{"Service Level", "Carrier"});
                            hasErr = true;

                            if (firstErrIdx == -1) {

                                firstErrIdx = i;
                            }
                        }
                    }
                }

                boolean isSameShipAndDelyDt = false;

                // Actual Delivery Date
                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).actlDelyDt_B1) && NLBL3070CommonLogic.changedValue(sMsg.B.no(i).actlDelyDt_B1.getValue(), sMsg.B.no(i).actlDelyDt_BK.getValue())) {

                    if (ZYPCommonFunc.hasValue(sMsg.B.no(i).shipDtTmTs_B1)) {

                        String shipDt = sMsg.B.no(i).shipDtTmTs_B1.getValue().substring(0, 8);

                        if (ZYPDateUtil.compare(shipDt, sMsg.B.no(i).actlDelyDt_B1.getValue()) > 0) {

                            sMsg.B.no(i).actlDelyDt_B1.setErrorInfo(1, NLBL3070Constant.NLBM1316E);
                            hasErr = true;

                            if (firstErrIdx == -1) {

                                firstErrIdx = i;
                            }

                        }  else if (ZYPDateUtil.compare(shipDt, sMsg.B.no(i).actlDelyDt_B1.getValue()) == 0) {

                            isSameShipAndDelyDt = true;
                        }
                    }

                    // QC#20043 Mod QC#25427
                    if (ZYPCommonFunc.hasValue(sMsg.B.no(i).shipDtTmTs_B1)) {
                        String shipDt = sMsg.B.no(i).shipDtTmTs_B1.getValue().substring(0, 8);

                        if (ZYPDateUtil.compare(shipDt, sMsg.B.no(i).actlDelyDt_B1.getValue()) > 0) {

                            sMsg.B.no(i).actlDelyDt_B1.setErrorInfo(1, NLBL3070Constant.NLBM1316E);
                            hasErr = true;

                            if (firstErrIdx == -1) {

                                firstErrIdx = i;
                            }
                        }
                    }
                    // QC#20043
                    if (sMsg.B.no(i).actlDelyDt_B1.getValue().compareTo(slsDt) > 0) {

                        sMsg.B.no(i).actlDelyDt_B1.setErrorInfo(1, NLBL3070Constant.NLAM1087E);
                        hasErr = true;

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }
                    }
                }
            }
        }

        if (hasErr) {

            NLBL3070CommonLogic.pagenation(cMsg, sMsg, NLBL3070CommonLogic.getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            return;
        }

        // Clear Apply Area
        // START 2016/08/12 QC#9841 DEL
        // cMsg.carrCd_BP.clear();
        // cMsg.carrNm_BP.clear();
        // cMsg.proNum_BP.clear();
        // cMsg.totFrtAmt_BP.clear();
        // cMsg.carrRsnCd_BP.clear();
        // cMsg.actlDelyDt_BP.clear();
        // cMsg.actlDelyTm_BP.clear();
        // END 2016/08/12 QC#9841 DEL

        NLBL3070CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowFromNum_B.getValueInt() - 1);
    }

    /**
     * getColData
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private static void getColData(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        if (NLBL3070Constant.TAB_ID_SCHD.equals(cMsg.xxDplyTab.getValue())) {

            ZYPGUITableColumn.getColData((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg, "AHEAD");

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(cMsg.xxDplyTab.getValue())) {

            ZYPGUITableColumn.getColData((NLBL3070CMsg) cMsg, (NLBL3070SMsg) sMsg, "BHEAD");
        }
    }

    /**
     * writeCsvFileScheduling
     * @param cMsg NLBL3070CMsg
     * @param rs ResultSet
     * @throws SQLException
     */
    private void writeCsvFileScheduling(NLBL3070CMsg cMsg, ResultSet rs) throws SQLException {

        NLBL3070F00FMsg fMsg = new NLBL3070F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        fMsg.addExclusionItem("xxChkBox");
        fMsg.addExclusionItem("xxSelRadioBtnObj_1");
        fMsg.addExclusionItem("xxSelRadioBtnObj_2");

        //write header
        csvOutFile.writeHeader(NLBL3070Constant.CSV_HDR_SCHD, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        if (!rs.next()) {

            cMsg.setMessageInfo(NLBL3070Constant.NZZM0000E);
            csvOutFile.close();
            return;
        }

        //write contents
        do {

            if (rs.getRow() >= NLBL3070Constant.LIMIT_DL_ROWNUM) {

                cMsg.setMessageInfo(NLBL3070Constant.NZZM0001W);
                break;
            }

            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.trxHdrNum, rs.getString("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk, rs.getBigDecimal("SHIP_SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.backOrdImpctTpDescTxt, rs.getString("BACK_ORD_IMPCT_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.soNum, rs.getString("SO_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.t_MdlNm, rs.getString("MDL_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_1, ZYPDateUtil.formatEzd8ToDisp(rs.getString("RDD_DT")));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_2, ZYPDateUtil.formatEzd8ToDisp(rs.getString("SCHD_CARR_PICK_UP_DT")));

            // START 2018/03/23 S.Katsuma [QC#24697,MOD]
            String schdDelyTsDplyTxt = convSchdDelyTs(rs.getString("SCHD_DELY_DT"),
                    rs.getString("SCHD_DELY_TM"),
                    rs.getString("CTRY_CD"),
                    rs.getString("POST_CD"));
            if (ZYPCommonFunc.hasValue(schdDelyTsDplyTxt)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_SD, schdDelyTsDplyTxt);
            } else {
                fMsg.xxDtTm_SD.clear();
            }
            // END 2018/03/23 S.Katsuma [QC#24697,MOD]

            // START 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.techMeetTruckFlg, rs.getString("TECH_MEET_TRUCK_FLG"));
            // END   2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]

            // START 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.nextActDt, rs.getString("NEXT_ACT_DT"));
            // END 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.carrNm, rs.getString("CARR_NM"));
            
            // START 2017/12/28 T.Hakodate [QC#18460(SOL#087),DEL]
            // ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_TS, rs.getString("TIME_STOP"));
            // END 2017/12/28 T.Hakodate [QC#18460(SOL#087),DEL]
            
            ZYPEZDItemValueSetter.setValue(fMsg.schdDurnTmNum, rs.getBigDecimal("SCHD_DURN_TM_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.fsrNum, rs.getString("FSR_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcTaskSchdDt, rs.getString("SVC_TASK_SCHD_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.techNm, rs.getString("TECH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.schdCoordStsDescTxt, rs.getString("SCHD_COORD_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxPsnFirstMidLastNm_1, rs.getString("XX_PSN_FIRST_MID_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.tocNm, rs.getString("TOC_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.ctacPsnTelNum, rs.getString("CTAC_PSN_TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, rs.getString("SHIP_TO_CUST_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.firstLineAddr, rs.getString("SO_CUST_LINE_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(fMsg.scdLineAddr, rs.getString("SO_CUST_LINE_ADDR_02"));
            ZYPEZDItemValueSetter.setValue(fMsg.ctyAddr, rs.getString("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.stCd, rs.getString("ST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.postCd, rs.getString("POST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm, rs.getString("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.wmsDropFlg, rs.getString("WMS_DROP_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.wmsDropRqstFlg, rs.getString("WMS_DROP_READY_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.sendRqstFlg,  rs.getString("SEND_RQST_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.shpgSvcLvlDescTxt, rs.getString("SHPG_SVC_LVL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.carrAcctNum, rs.getString("CARR_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxPsnFirstMidLastNm,  rs.getString("SCHD_COORD_PSN_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.tempSchdRsnDescTxt, rs.getString("TEMP_SCHD_RSN_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.tempSchdCmntTxt, rs.getString("TEMP_SCHD_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsOrdCatgDescTxt, rs.getString("DS_ORD_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsOrdTpDescTxt, rs.getString("DS_ORD_TP_DESC_TXT"));

            csvOutFile.write();

        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * writeCsvFileDeliveries
     * @param cMsg NLBL3070CMsg
     * @param rs ResultSet
     * @throws SQLException
     */
    private void writeCsvFileDeliveries(NLBL3070CMsg cMsg, ResultSet rs) throws SQLException {

        NLBL3070F01FMsg fMsg = new NLBL3070F01FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        fMsg.addExclusionItem("xxSmryLineFlg");
        fMsg.addExclusionItem("xxChkBox_1");
        fMsg.addExclusionItem("xxChkBox_2");
        fMsg.addExclusionItem("xxSelRadioBtnObj");

        //write header
        csvOutFile.writeHeader(NLBL3070Constant.CSV_HDR_DELV, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        if (!rs.next()) {

            cMsg.setMessageInfo(NLBL3070Constant.NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        //write contents
        do {

            if (rs.getRow() >= NLBL3070Constant.LIMIT_DL_ROWNUM) {

                cMsg.setMessageInfo(NLBL3070Constant.NZZM0001W, null);
                break;
            }

            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.shipSvcConfigMstrPk, rs.getBigDecimal("SHIP_SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.cpoOrdNum, rs.getString("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.dplyLineNum, rs.getString("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.soNum, rs.getString("SO_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.soSlpNum, rs.getString("SO_SLP_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.schdCoordPsnNm, rs.getString("SCHD_COORD_PSN_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm, rs.getString("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhCd, rs.getString("RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToStCd, rs.getString("SHIP_TO_ST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxPsnFirstMidLastNm, rs.getString("XX_PSN_FIRST_MID_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.backOrdImpctTpDescTxt, rs.getString("BACK_ORD_IMPCT_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.shipQty, rs.getBigDecimal("SHIP_QTY"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_RD, ZYPDateUtil.formatEzd8ToDisp(rs.getString("RDD_DT")));

            // START 2018/03/23 S.Katsuma [QC#24697,MOD]
            // QC#18460 Scheduled Delivery Time
            String schdDelyTsDplyTxt = convSchdDelyTs(rs.getString("SCHD_DELY_DT"),
                    rs.getString("SCHD_DELY_TM"),
                    rs.getString("CTRY_CD"),
                    rs.getString("SHIP_TO_POST_CD"));
            if (ZYPCommonFunc.hasValue(schdDelyTsDplyTxt)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_SD, schdDelyTsDplyTxt);
            } else {
                fMsg.xxDtTm_SD.clear();
            }
            // QC#22613 Add.
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_OR, rs.getString("PKT_STS_TS_OR"));
            // END 2018/03/23 S.Katsuma [QC#24697,MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_PC, rs.getString("PKT_STS_TS_PC"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_DS, rs.getString("PKT_STS_TS_DS"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_TA, rs.getString("PKT_STS_TS_TA"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_DD, rs.getString("PKT_STS_TS_DD"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_SP, rs.getString("SHIP_DT_TM_TS2"));
            ZYPEZDItemValueSetter.setValue(fMsg.carrRsnDescTxt, rs.getString("CARR_RSN_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_AD, rs.getString("ACTL_DELY_DT_TM"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_ID, ZYPDateUtil.formatEzd8ToDisp(rs.getString("SVC_TASK_CPLT_DT")));
            ZYPEZDItemValueSetter.setValue(fMsg.svcTaskStsDescTxt, rs.getString("SVC_TASK_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsSoLineStsDescTxt, rs.getString("DS_SO_LINE_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.carrNm, rs.getString("CARR_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.proNum, rs.getString("PRO_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.shpgSvcLvlDescTxt, rs.getString("SHPG_SVC_LVL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.schdCoordStsDescTxt, rs.getString("SCHD_COORD_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_DR, rs.getString("DELY_REQ_TS"));
            ZYPEZDItemValueSetter.setValue(fMsg.totFrtAmt, rs.getBigDecimal("TOT_FRT_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, rs.getString("SHIP_TO_CUST_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToCtyAddr, rs.getString("SHIP_TO_CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.pickSvcConfigMstrPk, rs.getBigDecimal("PICK_SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.stkStsCd, rs.getString("STK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsOrdCatgDescTxt, rs.getString("DS_ORD_CATG_DESC_TXT"));

            // Get Serial#
            if (ZYPConstant.FLG_ON_Y.equals(rs.getString("SER_NUM_TAKE_FLG"))) {

                ZYPEZDItemValueSetter.setValue(fMsg.serNum, NLBL3070CommonLogic.getSerListText(cMsg.glblCmpyCd.getValue(), null, fMsg, rs.getString("SHIP_FLG")));

            } else {

                fMsg.serNum.clear();
            }

            csvOutFile.write();

        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * doProcess_NLBL3070Scrn00_OpenWin_Tracking
     * QC#21913 Add method.
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070Scrn00_OpenWin_Tracking(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        int selectIndex = cMsg.xxNum.getValueInt();

        // Set write Data.
        cMsg.T.clear();

        String soNum = cMsg.B.no(selectIndex).soNum_B1.getValue();
        int cnt = 0;
        for (int i = 0; i < sMsg.T.getValidCount() && cnt < cMsg.T.length(); i++) {
            if (soNum.equals(sMsg.T.no(i).soNum_T1.getValue())) {
                EZDMsg.copy(sMsg.T.no(i), null, cMsg.T.no(cnt), null);
                cnt++;
            }
        }

        cMsg.T.setValidCount(cnt);
    }

    /**
     * doProcess_NLBL3070_NLBL3170
     * QC#21913 Add method.
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    private void doProcess_NLBL3070_NLBL3170(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg){

        HashMap<String, ArrayList<NLBL3070_TSMsg>> map = new HashMap<String, ArrayList<NLBL3070_TSMsg>>();
        for (int i = 0; i < sMsg.T.getValidCount(); i++) {
            NLBL3070_TSMsg tmp = new NLBL3070_TSMsg();
            EZDMsg.copy(sMsg.T.no(i), null, tmp, null);

            if (map.containsKey(tmp.soNum_T1.getValue())) {
                ArrayList<NLBL3070_TSMsg> list = map.get(tmp.soNum_T1.getValue());
                list.add(tmp);
            } else {
                ArrayList<NLBL3070_TSMsg> listNew = new ArrayList<NLBL3070_TSMsg>();
                listNew.add(tmp);
                map.put(tmp.soNum_T1.getValue(), listNew);
            }
        }

        // change TrackingList of soNum
        int selectIndex = cMsg.xxNum.getValueInt();
        String soNum = cMsg.B.no(selectIndex).soNum_B1.getValue();
        ArrayList<NLBL3070_TSMsg> targetList = map.get(soNum);
        if (targetList == null) {
            targetList = new ArrayList<NLBL3070_TSMsg>();
            map.put(soNum, targetList);
        } else {
            targetList.clear();
        }
        ArrayList<String> duplicateCheckList = new ArrayList<String>();
        for (int i = 0; i < cMsg.T.getValidCount(); i++) {
            NLBL3070_TCMsg tCMsg = cMsg.T.no(i);
            String proNum = tCMsg.proNum.getValue();

            if (!duplicateCheckList.contains(proNum)) {
                NLBL3070_TSMsg tmp = new NLBL3070_TSMsg();
                EZDMsg.copy(tCMsg, null, tmp, null);
                ZYPEZDItemValueSetter.setValue(tmp.soNum_T1, soNum);

                targetList.add(tmp);
                duplicateCheckList.add(proNum);
            }
        }

        // return sMsg data.
        int cnt = 0;
        sMsg.T.clear();
        for (Map.Entry<String, ArrayList<NLBL3070_TSMsg>> entry : map.entrySet()) {
            ArrayList<NLBL3070_TSMsg> list = entry.getValue();

            for (NLBL3070_TSMsg t : list) {
                EZDMsg.copy(t, null, sMsg.T.no(cnt), null);
                cnt++;
            }
        }

        sMsg.T.setValidCount(cnt);
    }

    // START 2018/03/23 S.Katsuma [QC#24697,ADD]
    /**
     * convSchdDelyTs
     * @param schdDelyDt
     * @param schdDelyTm
     * @param ctryCd
     * @param postCd
     * @return String
     */
    private String convSchdDelyTs(String schdDelyDt, String schdDelyTm, String ctryCd, String postCd) {
        String schdDelyTsDplyTxt = null;

        if (ZYPCommonFunc.hasValue(schdDelyDt)) {
            if (ZYPCommonFunc.hasValue(schdDelyTm)) {
                String systemTimeZoneTs = schdDelyDt + schdDelyTm;

                if (ZYPCommonFunc.hasValue(ctryCd) && ZYPCommonFunc.hasValue(postCd)) {
                    schdDelyTsDplyTxt = convertTime(systemTimeZoneTs, ctryCd, postCd);
                } else {
                    schdDelyTsDplyTxt = convFormatTimeInclTime(systemTimeZoneTs);
                }
            } else {
                schdDelyTsDplyTxt = convFormatTime(schdDelyDt);
            }
        }

        return schdDelyTsDplyTxt;
    }

    /**
     * convertTime
     * @param ts
     * @param ctryCd
     * @param postCd
     * @return String
     */
    private String convertTime(String ts, String ctryCd, String postCd) {
        SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, ts, ctryCd, postCd);
        return convFormatTimeInclTime(timeInfo.getDateTime().substring(0, 12));
    }

    /**
     * convFormatTimeInclTime
     * @param ts
     * @return String
     */
    private String convFormatTimeInclTime(String ts) {
        SimpleDateFormat sfdDate = new SimpleDateFormat("yyyyMMddHHmm");
        SimpleDateFormat sfdString = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date formatDate = null;

        try {
            formatDate = sfdDate.parse(ts);
            return sfdString.format(formatDate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * convFormatTimeInclTime
     * @param ts
     * @return String
     */
    private String convFormatTime(String ts) {
        SimpleDateFormat sfdDate = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sfdString = new SimpleDateFormat("MM/dd/yyyy");
        Date formatDate = null;

        try {
            formatDate = sfdDate.parse(ts);
            return sfdString.format(formatDate);
        } catch (ParseException e) {
            return null;
        }
    }
    // END 2018/03/23 S.Katsuma [QC#24697,ADD]
}
