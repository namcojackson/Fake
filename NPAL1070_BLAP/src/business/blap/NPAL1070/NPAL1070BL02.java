/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1070;

import static business.blap.NPAL1070.constant.NPAL1070Constant.BIZ_APP_NM;
import static business.blap.NPAL1070.constant.NPAL1070Constant.CSV_HDR_UPLOAD;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_ATT_DATA_KEY_TXT;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_BIZ_APP_NM;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_EZBUSINESSID;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_MDSE_CD;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_RGTN_STS_CD;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_ROWNUM;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_ADD;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_APPLY_TO_ALL;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_CMN_CLEAR;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_CMN_DOWNLOAD;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_CMN_RESET;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_CMN_SUBMIT;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_COPY;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_DELETEROW;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_DISABLE;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_IMPORT;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_INIT;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_ITEM_MASTER_ATTACHMENT;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_OPEN_SWH;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_OPEN_WH;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_PAGE_NEXT;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_PAGE_PREV;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_SEARCH;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_SET_ITEM_DESCRIPTION;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_SET_MANAGERNAME;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_SET_SOURCE_SUB_WAREHOUSENAME;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_SET_SOURCE_WAREHOUSENAME;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_SET_SOURCE_WAREHOUSENAME_DETAIL;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_SET_SUB_WAREHOUSENAME;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_SET_WAREHOUSENAME;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_TEMPLETE_FILE_FOR_UPLOAD;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EZBUSINESSID;
import static business.blap.NPAL1070.constant.NPAL1070Constant.ROWNUM_DOWNLOAD;
import static business.blap.NPAL1070.constant.NPAL1070Constant.BIZ_APP_ID;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1070.common.NPAL1070CommonLogic;
import business.blap.NPAL1070.constant.NPAL1070Constant;
import business.file.NPAL1070F00FMsg;
import business.file.NPAL1070F01FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_INFO_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
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
 * Business ID : NPAL1070 Min-Max Planning Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/24/2016   CITS            Keiichi Masaki  Create          N/A
 * 12/01/2016   CITS            R.Shimamoto     Update          QC#15124
 * 01/05/2016   CITS            R.Shimamoto     Update          QC#16943
 * 02/09/2017   CITS            Y.IWASAKI       Update          QC#17478
 * 02/17/2017   CITS            T.Tokutomi      Update          QC#17574
 * 10/05/2017   CITS            K.Ogino         Update          QC#21229
 * 11/07/2017   CITS            S.Katsuma       Update          Sol#014(QC#18401)
 * 01/09/2018   CITS            T.Tokutomi      Update          QC#17571
 * 03/27/2018   CITS            T.Wada          Update          QC#24877
 * 2018/04/09   CITS            K.Ogino         Update          QC#21229
 * 2018/04/14   CITS            K.Ogino         Update          QC#24796
 * 2018/07/18   CITS            S.Katsuma       Update          QC#26709
 * 2018/08/29   CITS            K.Ogino         Update          QC#26709-1
 * 2018/12/05   Hitachi         J.Kim           Update          QC#18224
 * 2019/08/20   Fujitsu         T.Ogura         Update          QC#51796
 * 2019/08/29   CITS            M.Naito         Update          QC#51796
 * 2019/09/17   Fujitsu         T.Ogura         Update          QC#53302
 * 2022/10/05   Hitachi         M.Kikushima     Update          QC#60560
 * 2023/04/17   Hitachi         S.Dong          Update          QC#61348
 *</pre>
 */

public class NPAL1070BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1070_INIT.equals(screenAplID)) {
                doProcess_NPAL1070_INIT((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg, EVENT_NM_NPAL1070_INIT);
                ZYPGUITableColumn.getColData((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_CMN_RESET.equals(screenAplID)) {
                    doProcess_NPAL1070_INIT((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg, EVENT_NM_NPAL1070_CMN_RESET);
            } else if (EVENT_NM_NPAL1070_CMN_CLEAR.equals(screenAplID)) {
                        doProcess_NPAL1070_INIT((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg, EVENT_NM_NPAL1070_CMN_CLEAR);
            } else if (EVENT_NM_NPAL1070_SET_ITEM_DESCRIPTION.equals(screenAplID)) {
                doProcess_NPAL1070_SetItemDescription((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_SET_WAREHOUSENAME.equals(screenAplID)) {
                doProcess_NPAL1070_SetWarehouseName((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_SET_SUB_WAREHOUSENAME.equals(screenAplID)) {
                doProcess_NPAL1070_SetSubWarehouseName((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_SET_MANAGERNAME.equals(screenAplID)) {
                doProcess_NPAL1070_SetManagerName((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_SET_SOURCE_WAREHOUSENAME.equals(screenAplID)) {
                doProcess_NPAL1070_SetSourceWarehouseName((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_SET_SOURCE_WAREHOUSENAME_DETAIL.equals(screenAplID)) {
                doProcess_NPAL1070_SetSourceWarehouseNameDetail((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_SET_SOURCE_SUB_WAREHOUSENAME.equals(screenAplID)) {
                doProcess_NPAL1070_SetSourceSubWarehouseName((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_SEARCH.equals(screenAplID)) {
                doProcess_NPAL1070_Search((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_PAGE_NEXT.equals(screenAplID)) {
                doProcess_NPAL1070_PageNext((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_PAGE_PREV.equals(screenAplID)) {
                doProcess_NPAL1070_PagePrev((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_ADD.equals(screenAplID)) {
                doProcess_NPAL1070_Add((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_COPY.equals(screenAplID)) {
                doProcess_NPAL1070_Copy((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_DISABLE.equals(screenAplID)) {
                doProcess_NPAL1070_Disable((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NPAL1070_Submit((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_IMPORT.equals(screenAplID)) {
                doProcess_NPAL1070_Import((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_TEMPLETE_FILE_FOR_UPLOAD.equals(screenAplID)) {
                doProcess_NPAL1070_UploadTemplate((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_NPAL1070_Download((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_OPEN_WH.equals(screenAplID)) {
                doProcess_NPAL1070_OpenWh((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            } else if (EVENT_NM_NPAL1070_OPEN_SWH.equals(screenAplID)) {
                doProcess_NPAL1070_OpenSwh((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            // QC#24877
            } else if (EVENT_NM_NPAL1070_DELETEROW.equals(screenAplID)) {
                doProcess_NPAL1070_Search((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            // QC#21229
            } else if (EVENT_NM_NPAL1070_ITEM_MASTER_ATTACHMENT.equals(screenAplID)) {
                doProcess_NPAL1070_ItemMasterAttachment((NPAL1070CMsg) cMsg);
            // START 2022/10/05 M.Kikushima [QC#60560,ADD]
            } else if (EVENT_NM_NPAL1070_APPLY_TO_ALL.equals(screenAplID)) {
            	doProcess_NPAL1070_ApplyToAll((NPAL1070CMsg) cMsg, (NPAL1070SMsg) sMsg);
            }
            // END 2022/10/05 M.Kikushima [QC#60560,ADD]
        } finally {
            // START 2023/04/17 S.Dong [QC#61348,ADD]
            String screenAplID = cMsg.getScreenAplID();

            if (!EVENT_NM_NPAL1070_CMN_SUBMIT.equals(screenAplID)) {
                setWrnSkipFlg((NPAL1070CMsg) cMsg);
            }
            // END 2023/04/17 S.Dong [QC#61348,ADD]
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
    * Init
    * @param cMsg NPAL1060CMsg
    * @param sMsg NPAL1060SMsg
    * @param eventName String
    */
    private void doProcess_NPAL1070_INIT(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg, String eventName) {

        // NPAL1070 Screen Parameters
        String mrpPlnNmH1 = cMsg.mrpPlnNm_H1.getValue();
        String mdseCdH2 = cMsg.mdseCd_H2.getValue();
        String rtlWhCdH3 = cMsg.rtlWhCd_H3.getValue();
        String rtlSwhCdH4 = cMsg.rtlSwhCd_H4.getValue();

        // ACMsg clear, ASMsg clear
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        EZDMsg.copy(cMsg, null, sMsg, null);
        cMsg.clear();
        cMsg.A.setValidCount(0);
        sMsg.clear();
        sMsg.A.setValidCount(0);

        ZYPEZDItemValueSetter.setValue(cMsg.mrpPlnNm_H1, mrpPlnNmH1);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd_H2, mdseCdH2);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_H3, rtlWhCdH3);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd_H4, rtlSwhCdH4);

        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        ZYPCodeDataUtil.createPulldownList(RTL_WH_CATG.class, cMsg.rtlWhCatgCd_PD, cMsg.rtlWhCatgDescTxt_PD);
        ZYPCodeDataUtil.createPulldownList(PROCR_TP.class, cMsg.procrTpCd_PD, cMsg.procrTpDescTxt_PD);
        ZYPCodeDataUtil.createPulldownList(PROCR_TP.class, cMsg.procrTpCd_AP, cMsg.procrTpDescTxt_AP);

        if (eventName.equals(EVENT_NM_NPAL1070_INIT)
            || eventName.equals(EVENT_NM_NPAL1070_CMN_RESET)) {
            if (ZYPCommonFunc.hasValue(cMsg.mrpPlnNm_H1)
                || ZYPCommonFunc.hasValue(cMsg.mdseCd_H2)
                || ZYPCommonFunc.hasValue(cMsg.rtlWhCd_H3)
                || ZYPCommonFunc.hasValue(cMsg.rtlSwhCd_H4)) {
                ZYPEZDItemValueSetter.setValue(cMsg.mrpPlnNm, mrpPlnNmH1);
                ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, mdseCdH2);
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, rtlWhCdH3);
                ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd, rtlSwhCdH4);

                NPAL1070CommonLogic.search(cMsg, sMsg, EVENT_NM_NPAL1070_INIT);
            }
        }
    }

    /**
     * Set Item Description
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
     private void doProcess_NPAL1070_SetItemDescription(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

         NPAL1070CommonLogic.setItemDescription(cMsg, sMsg, getGlobalCompanyCode(), ZYPConstant.FLG_ON_Y);

     }

     /**
     * Set Warehouse Name
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
     private void doProcess_NPAL1070_SetWarehouseName(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {


         NPAL1070CommonLogic.setWarehouseName(cMsg, sMsg, getGlobalCompanyCode(), ZYPConstant.FLG_ON_Y);

    }

     /**
     * Set Sub Warehouse Name
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
     private void doProcess_NPAL1070_SetSubWarehouseName(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

         NPAL1070CommonLogic.setSubWarehouseName(cMsg, sMsg, getGlobalCompanyCode(), ZYPConstant.FLG_ON_Y);

  }

     /**
     * Set Manager Name
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
     private void doProcess_NPAL1070_SetManagerName(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

         NPAL1070CommonLogic.setManagerName(cMsg, sMsg, getGlobalCompanyCode(), ZYPConstant.FLG_ON_Y);

    }

     /**
     * Set Source Warehouse Name
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
     private void doProcess_NPAL1070_SetSourceWarehouseName(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

         NPAL1070CommonLogic.setSourceWarehouseName(cMsg, sMsg, getGlobalCompanyCode(), ZYPConstant.FLG_ON_Y);
     }

     /**
      * Set Source Warehouse Name Detail
      * @param cMsg NPAL1070CMsg
      * @param sMsg NPAL1070SMsg
      */
      private void doProcess_NPAL1070_SetSourceWarehouseNameDetail(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

          NPAL1070CommonLogic.setSourceWarehouseNameDetail(cMsg, sMsg, getGlobalCompanyCode(), ZYPConstant.FLG_ON_Y);
      }


     /**
     * Set Source Sub Warehouse Name
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
     private void doProcess_NPAL1070_SetSourceSubWarehouseName(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

         NPAL1070CommonLogic.setSourceSubWarehouseName(cMsg, sMsg, getGlobalCompanyCode(), ZYPConstant.FLG_ON_Y);

    }

     /**
     * Open Warehouse
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
    private void doProcess_NPAL1070_OpenWh(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        NPAL1070CommonLogic.getRtlWhCatg(cMsg, sMsg);

    }

    /**
     * Open Sub Warehouse
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
    private void doProcess_NPAL1070_OpenSwh(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        NPAL1070CommonLogic.getRtlWhCatg(cMsg, sMsg);

    }

     /**
      * Search
      * @param cMsg NPAL1070CMsg
      * @param sMsg NPAL1070SMsg
      */
     private void doProcess_NPAL1070_Search(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

         String glblCmpyCd = getGlobalCompanyCode();

         // Set Item Description to header
         if (ZYPCommonFunc.hasValue(cMsg.mdseCd)) {
             NPAL1070CommonLogic.setItemDescription(cMsg, sMsg, glblCmpyCd, ZYPConstant.FLG_OFF_N);
         }

         // Set Warehouse Name to header
         if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {
             NPAL1070CommonLogic.setWarehouseName(cMsg, sMsg, glblCmpyCd, ZYPConstant.FLG_OFF_N);
         }

         // Set Sub Warehouse Name to header
         if (ZYPCommonFunc.hasValue(cMsg.rtlSwhCd)) {
             NPAL1070CommonLogic.setSubWarehouseName(cMsg, sMsg, glblCmpyCd, ZYPConstant.FLG_OFF_N);
         }

         // Set Manager Name to header
         if (ZYPCommonFunc.hasValue(cMsg.whMgrPsnCd)) {
             NPAL1070CommonLogic.setManagerName(cMsg, sMsg, glblCmpyCd, ZYPConstant.FLG_OFF_N);
         }

         // Set Source Warehouse Name to header
         if (ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd)) {
             NPAL1070CommonLogic.setSourceWarehouseName(cMsg, sMsg, glblCmpyCd, ZYPConstant.FLG_OFF_N);
         }

         // Set Source Sub Warehouse Name to header
         if (ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd)) {
             NPAL1070CommonLogic.setSourceSubWarehouseName(cMsg, sMsg, glblCmpyCd, ZYPConstant.FLG_OFF_N);
         }

         NPAL1070CommonLogic.search(cMsg, sMsg, EVENT_NM_NPAL1070_SEARCH);

    }

     /**
      * Add
      * @param cMsg NPAL1070CMsg
      * @param sMsg NPAL1070SMsg
      */
      private void doProcess_NPAL1070_Add(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

          String glblCmpyCd = getGlobalCompanyCode();

          // START 2018/07/18 S.Katsuma [QC#26709,MOD]
          if (NPAL1070CommonLogic.addCheck(cMsg, sMsg)) {
              // Copy from cMsg to sMsg
              NPAL1070CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
              // Add new line
              NPAL1070CommonLogic.addLine(cMsg, sMsg, glblCmpyCd, functionList());
              // Copy from sMsg to cMsg
              NPAL1070CommonLogic.copyFromSmsgOntoCmsg(cMsg, sMsg);
          }
          // END 2018/07/18 S.Katsuma [QC#26709,MOD]

      }

    /**
     * Copy
     * @param cMsg NPAL1060CMsg
     * @param sMsg NPAL1060SMsg
     */
    private void doProcess_NPAL1070_Copy(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        NPAL1070CommonLogic.search(cMsg, sMsg, EVENT_NM_NPAL1070_COPY);

        NPAL1070CommonLogic.copy(cMsg, sMsg, functionList());

        // Copy from sMsg to cMsg
        NPAL1070CommonLogic.copyFromSmsgOntoCmsg(cMsg, sMsg);

    }

    /**
     * Page Next
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
    private void doProcess_NPAL1070_PageNext(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        // copy data from CMsg to SMsg
        NPAL1070CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        // copy data from SMsg to CMsg
        int pagenationFrom = cMsg.xxPageShowToNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                // START 2022/10/05 M.Kikushima [QC#60560,ADD]
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).calcOrdProcCd_A1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).calcOrdProcCd_A1, ZYPConstant.FLG_OFF_N);
                } else if (ZYPConstant.FLG_ON_1.equals(sMsg.A.no(i).calcOrdProcCd_A1.getValue())){
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).calcOrdProcCd_A1, ZYPConstant.FLG_ON_Y);
                } else if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).calcOrdProcCd_A1.getValue())){
                    ;
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).calcOrdProcCd_A1, ZYPConstant.FLG_OFF_0);
                }
                // END 2022/10/05 M.Kikushima [QC#60560,ADD]
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagination items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
        // copy data from SMsg to CMsg
        NPAL1070CommonLogic.copyFromSmsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * Page Prev
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
    private void doProcess_NPAL1070_PagePrev(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {
        // copy data from CMsg to SMsg
        NPAL1070CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        // copy data from SMsg onto CMsg
//        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                // START 2022/10/05 M.Kikushima [QC#60560,ADD]
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).calcOrdProcCd_A1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).calcOrdProcCd_A1, ZYPConstant.FLG_OFF_N);
                } else if (ZYPConstant.FLG_ON_1.equals(sMsg.A.no(i).calcOrdProcCd_A1.getValue())){
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).calcOrdProcCd_A1, ZYPConstant.FLG_ON_Y);
                } else if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).calcOrdProcCd_A1.getValue())){
                    ;
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).calcOrdProcCd_A1, ZYPConstant.FLG_OFF_0);
                }
                // END 2022/10/05 M.Kikushima [QC#60560,ADD]
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagination items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
        // copy data from SMsg to CMsg
        NPAL1070CommonLogic.copyFromSmsgOntoCmsg(cMsg, sMsg);
    }

    /**
    * Disable
    * @param cMsg NPAL1060CMsg
    * @param sMsg NPAL1060SMsg
    */
    private void doProcess_NPAL1070_Disable(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        NPAL1070CommonLogic.search(cMsg, sMsg, EVENT_NM_NPAL1070_DISABLE);

    }
    /**
    * Submit
    * @param cMsg NPAL1060CMsg
    * @param sMsg NPAL1060SMsg
    */
    private void doProcess_NPAL1070_Submit(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        NPAL1070CommonLogic.search(cMsg, sMsg, EVENT_NM_NPAL1070_CMN_SUBMIT);

      }
    /**
    * IMPORT
    * @param cMsg NPAL1070CMsg
    * @param sMsg NPAL1070SMsg
    */
    private void doProcess_NPAL1070_Import(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {


        /** QC#17574 02/17/2017 T.Tokutomi START **/
        ZYPTableUtil.clear(sMsg.B);

        NPAL1070CommonLogic.copyDetailAtoB(sMsg);
        ZYPTableUtil.clear(sMsg.A);
        /** QC#17574 02/17/2017 T.Tokutomi END **/

        String path = cMsg.xxFileData_UP.getTempFilePath();

        NPAL1070F01FMsg fMsg = new NPAL1070F01FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        int count = 0;

        try {
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.setMessageInfo("ZYEM0004E");
            }
            fMsg.clear();
            int status = -1;
            /** QC#17574 02/17/2017 T.Tokutomi START **/
            int i = sMsg.B.getValidCount() - 1;
            /** QC#17574 02/17/2017 T.Tokutomi END **/
            // QC#24796
            List<String> existChkLst = new ArrayList<String>();
            for (int c = 0; c < sMsg.B.getValidCount(); c++) {
                String chkKey = ZYPCommonFunc.concatString(sMsg.B.no(c).rtlWhCd_B1.getValue(), sMsg.B.no(c).rtlSwhCd_B1.getValue(), sMsg.B.no(c).mdseCd_B1.getValue());
                existChkLst.add(chkKey);
            }
            while ((status = mappedFile.read()) != 1) {
                count++;

                // 12/1/2016 QC#15124 Add.
                if (ZYPCommonFunc.hasValue(fMsg.mrpPlnNm_D1)) {
                    ZYPEZDItemValueSetter.setValue(fMsg.mrpPlnNm_D1, NPAL1070CommonLogic.toUpperCase(fMsg.mrpPlnNm_D1.getValue()));
                }
                if (ZYPCommonFunc.hasValue(fMsg.rtlSwhCd_D1)) {
                    ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhCd_D1, NPAL1070CommonLogic.toUpperCase(fMsg.rtlSwhCd_D1.getValue()));
                }
                if (ZYPCommonFunc.hasValue(fMsg.rtlWhCd_D1)) {
                    ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCd_D1, NPAL1070CommonLogic.toUpperCase(fMsg.rtlWhCd_D1.getValue()));
                }
                if (ZYPCommonFunc.hasValue(fMsg.mdseCd_D1)) {
                    ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_D1, NPAL1070CommonLogic.toUpperCase(fMsg.mdseCd_D1.getValue()));
                }
                if (ZYPCommonFunc.hasValue(fMsg.srcRtlWhCd_D1)) {
                    ZYPEZDItemValueSetter.setValue(fMsg.srcRtlWhCd_D1, NPAL1070CommonLogic.toUpperCase(fMsg.srcRtlWhCd_D1.getValue()));
                }
                if (ZYPCommonFunc.hasValue(fMsg.srcRtlSwhCd_D1)) {
                    ZYPEZDItemValueSetter.setValue(fMsg.srcRtlSwhCd_D1, NPAL1070CommonLogic.toUpperCase(fMsg.srcRtlSwhCd_D1.getValue()));
                }
                if (ZYPCommonFunc.hasValue(fMsg.calcOrdProcCd_D1)) {
                    ZYPEZDItemValueSetter.setValue(fMsg.calcOrdProcCd_D1, NPAL1070CommonLogic.toUpperCase(fMsg.calcOrdProcCd_D1.getValue()));
                }

                if (count == 1) {
                    ZYPEZDItemValueSetter.setValue(cMsg.mrpPlnNm, fMsg.mrpPlnNm_D1);
                    ZYPEZDItemValueSetter.setValue(cMsg.rplshDlyFlg, fMsg.rplshDlyFlg_D1);
                    ZYPEZDItemValueSetter.setValue(cMsg.rplshMonFlg, fMsg.rplshMonFlg_D1);
                    ZYPEZDItemValueSetter.setValue(cMsg.rplshTueFlg, fMsg.rplshTueFlg_D1);
                    ZYPEZDItemValueSetter.setValue(cMsg.rplshWedFlg, fMsg.rplshWedFlg_D1);
                    ZYPEZDItemValueSetter.setValue(cMsg.rplshThuFlg, fMsg.rplshThuFlg_D1);
                    ZYPEZDItemValueSetter.setValue(cMsg.rplshFriFlg, fMsg.rplshFriFlg_D1);
                    // START 2022/10/05 M.Kikushima [QC#60560,DEL]
                    // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
                    //if (!ZYPCommonFunc.hasValue(fMsg.calcOrdProcCd_D1)) {
                    //    ZYPEZDItemValueSetter.setValue(fMsg.calcOrdProcCd_D1, ZYPConstant.FLG_OFF_0);
                    //} else if (ZYPConstant.FLG_ON_1.equals(fMsg.calcOrdProcCd_D1.getValue())){
                    //    ZYPEZDItemValueSetter.setValue(fMsg.calcOrdProcCd_D1, ZYPConstant.FLG_ON_1);
                    //} else if (ZYPConstant.FLG_ON_Y.equals(fMsg.calcOrdProcCd_D1.getValue())){
                    //    ZYPEZDItemValueSetter.setValue(fMsg.calcOrdProcCd_D1, ZYPConstant.FLG_ON_1);
                    //} else {
                    //    ZYPEZDItemValueSetter.setValue(fMsg.calcOrdProcCd_D1, ZYPConstant.FLG_OFF_0);
                    //}
                    // END 2022/10/05 M.Kikushima [QC#60560,DEL]
                    ZYPEZDItemValueSetter.setValue(cMsg.calcOrdProcCd, fMsg.calcOrdProcCd_D1);
                    // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
                }

                if (!validateUploadFile(status, count, cMsg, sMsg, fMsg)) {
                    if ("E".equals(cMsg.getMessageKind())) {
                        break;
                    }
                } else {

                    //Check Header Info and Upload File is matched. --Start-- 
                    if (!cMsg.mrpPlnNm.getValue().equals(fMsg.mrpPlnNm_D1.getValue())) {
                        cMsg.setMessageInfo("NPAM1563E", new String[]{"Plan Name"});
                        if ("E".equals(cMsg.getMessageKind())) {
                            break;
                        }
                    }
                    if (!cMsg.rplshDlyFlg.getValue().equals(fMsg.rplshDlyFlg_D1.getValue())) {
                        cMsg.setMessageInfo("NPAM1563E", new String[]{"Daily Flag"});
                        if ("E".equals(cMsg.getMessageKind())) {
                            break;
                        }
                    }
                    if (!cMsg.rplshMonFlg.getValue().equals(fMsg.rplshMonFlg_D1.getValue())) {
                        cMsg.setMessageInfo("NPAM1563E", new String[]{"Monday Flag"});
                        if ("E".equals(cMsg.getMessageKind())) {
                            break;
                        }
                    }
                    if (!cMsg.rplshTueFlg.getValue().equals(fMsg.rplshTueFlg_D1.getValue())) {
                        cMsg.setMessageInfo("NPAM1563E", new String[]{"Tuesday Flag"});
                        if ("E".equals(cMsg.getMessageKind())) {
                            break;
                        }
                    }
                    if (!cMsg.rplshWedFlg.getValue().equals(fMsg.rplshWedFlg_D1.getValue())) {
                        cMsg.setMessageInfo("NPAM1563E", new String[]{"Wednesday Flag"});
                        if ("E".equals(cMsg.getMessageKind())) {
                            break;
                        }
                    }
                    if (!cMsg.rplshThuFlg.getValue().equals(fMsg.rplshThuFlg_D1.getValue())) {
                        cMsg.setMessageInfo("NPAM1563E", new String[]{"Thursday Flag"});
                        if ("E".equals(cMsg.getMessageKind())) {
                            break;
                        }
                    }
                    if (!cMsg.rplshFriFlg.getValue().equals(fMsg.rplshFriFlg_D1.getValue())) {
                        cMsg.setMessageInfo("NPAM1563E", new String[]{"Friday Flag"});
                        if ("E".equals(cMsg.getMessageKind())) {
                            break;
                        }
                    }
                    // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
//                    if (!cMsg.calcOrdProcCd.getValue().equals(fMsg.calcOrdProcCd_D1.getValue())) {
//                        cMsg.setMessageInfo("NPAM1563E", new String[]{"Include Entered Sales Order"});
//                        if ("E".equals(cMsg.getMessageKind())) {
//                            break;
//                        }
//                    }
                    // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
                    //Check Header Info and Upload File is matched. --End--

                    // QC#17478
                    // Duplicate check is moved to NPAL1070CommonLogic#addCheck().

                    // set import file to sMsg work
                    i++;
                    // QC#24796. Mod QC#26709-1
                    String chkKey = ZYPCommonFunc.concatString(fMsg.rtlWhCd_D1.getValue(), fMsg.rtlSwhCd_D1.getValue(), fMsg.mdseCd_D1.getValue());
                    if (!ZYPCommonFunc.hasValue(cMsg.mrpInfoPk_IM) && existChkLst.contains(chkKey)) {
                        cMsg.setMessageInfo("NPAM0073E", new String[] {"Import file(Item Number:" + fMsg.mdseCd_D1.getValue() +",WH Code:" + fMsg.rtlWhCd_D1.getValue() + ",SWH Code:" + fMsg.rtlSwhCd_D1.getValue() +")" });
                        if ("E".equals(cMsg.getMessageKind())) {
                            break;
                        }
                    }
                    existChkLst.add(chkKey);

                    // START 2019/09/17 T.Ogura [QC#53302,ADD]
                    if (i+1 > sMsg.B.length()) {
                        cMsg.setMessageInfo("NPAM1199E");
                        break;
                    }
                    // END   2019/09/17 T.Ogura [QC#53302,ADD]

                    if (!ZYPCommonFunc.hasValue(cMsg.mrpInfoPk)) {
                        // Insert Record
                        // copy from CMsg to SMsg work 
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).glblCmpyCd_B1, cMsg.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).mrpInfoPk_B1, cMsg.mrpInfoPk);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rtlWhCatgCd_B1, cMsg.rtlWhCatgCd_SL);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rtlWhCatgDescTxt_B1, cMsg.rtlWhCatgDescTxt);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).invtyLocCd_B1, cMsg.rtlWhCd.getValue() +  cMsg.rtlSwhCd.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rtlWhCd_B1, cMsg.rtlWhCd);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rtlWhNm_B1, cMsg.rtlWhNm_W1);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rtlSwhCd_B1, cMsg.rtlSwhCd);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rtlSwhNm_B1, cMsg.rtlSwhNm_S1);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).mdseCd_B1, cMsg.mdseCd);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).mdseDescShortTxt_B1, cMsg.mdseDescShortTxt);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).mdseTpCd_B1, cMsg.mdseTpCd);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).coaMdseTpCd_B1 , cMsg.coaMdseTpCd);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).coaProdCd_B1, cMsg.coaProdCd);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).srcRtlWhCd_B1, cMsg.srcRtlWhCd);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rtlWhNm_B2, cMsg.rtlWhNm_W2);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).srcRtlSwhCd_B1, cMsg.srcRtlSwhCd);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rtlSwhNm_B2, cMsg.rtlSwhNm_S2);
                        // copy from FMsg to SMsg work 
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).mrpPlnNm_B1, fMsg.mrpPlnNm_D1);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).ropQty_B1, fMsg.ropQty_D1);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).maxInvtyQty_B1, fMsg.maxInvtyQty_D1);
                        // QC#17571 Update.
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).ovrdMaxInvtyQty_B1, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).mrpEnblFlg_B1, fMsg.mrpEnblFlg_D1);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).procrTpCd_BS, fMsg.procrTpCd_D1);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).srcLocCd_B1, fMsg.srcRtlWhCd_D1.getValue() + fMsg.srcRtlSwhCd_D1.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rplshDlyFlg_B1, fMsg.rplshDlyFlg_D1);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rplshMonFlg_B1, fMsg.rplshMonFlg_D1);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rplshTueFlg_B1, fMsg.rplshTueFlg_D1);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rplshWedFlg_B1, fMsg.rplshWedFlg_D1);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rplshThuFlg_B1, fMsg.rplshThuFlg_D1);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rplshFriFlg_B1, fMsg.rplshFriFlg_D1);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).mrpInfoCmntTxt_B1, fMsg.mrpInfoCmntTxt_D1);
                        // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).calcOrdProcCd_B1, fMsg.calcOrdProcCd_D1);
                        // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]

                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).supdFlg_B1 , ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).mrpInfoRgtnStsCd_B1, MRP_INFO_RGTN_STS.AVAILABLE);

                        // QC#21229
                        if (ZYPCommonFunc.hasValue(sMsg.B.no(i).mdseCd_B1)) {
                            Map<String, Object> ssmParam = new HashMap<String, Object>();
                            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
                            ssmParam.put(DB_PARAM_MDSE_CD, sMsg.B.no(i).mdseCd_B1.getValue());
                            ssmParam.put(DB_PARAM_EZBUSINESSID, EZBUSINESSID);
                            ssmParam.put(DB_PARAM_BIZ_APP_NM, BIZ_APP_NM);

                            S21SsmEZDResult result = NPAL1070Query.getInstance().countAttData(ssmParam);

                            if (result.isCodeNormal()) {
                                BigDecimal cnt = (BigDecimal) result.getResultObject();

                                if (BigDecimal.ZERO.compareTo(cnt) == 0) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRsltFlg_B1, ZYPConstant.FLG_OFF_N);
                                } else {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRsltFlg_B1, ZYPConstant.FLG_ON_Y);
                                }

                            } else {
                                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRsltFlg_B1, ZYPConstant.FLG_OFF_N);
                            }
                        } else {
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRsltFlg_B1, ZYPConstant.FLG_OFF_N);
                        }

                        // START 2018/12/05 J.Kim [QC#18224,ADD]
                        Map<String, Object> fsmRtlWhInfo = null;
                        // START 2019/08/29 M.Naito [QC#51796,MOD]
//                        if (NPAL1070CommonLogic.hasRegisterAuthority(functionList())) {
                        if (NPAL1070CommonLogic.hasRegisterAuthority(functionList()) && (!ZYPCommonFunc.hasValue(sMsg.A.no(i).procrTpCd_AS) || !PROCR_TP.SUPPLIER.equals(sMsg.A.no(i).procrTpCd_AS.getValue()))) {
                            String glblCmpyCd = cMsg.glblCmpyCd.getValue();
                            String rtlWhCd = fMsg.rtlWhCd_D1.getValue();
//                            String mdseCd = fMsg.mdseCd_D1.getValue();        // 2019/08/20 T.Ogura [QC#51796,DEL]
//                            String rtlSwhCd = fMsg.rtlSwhCd_D1.getValue();    // 2019/08/20 T.Ogura [QC#51796,DEL]
                            fsmRtlWhInfo = NPAL1070CommonLogic.getRtlWhInfo(glblCmpyCd, rtlWhCd);
                            // START 2019/08/20 T.Ogura [QC#51796,MOD]
//                            if (fsmRtlWhInfo == null) {
//                                cMsg.setMessageInfo(NMAM0039E, new String[] {"Import file(Item Number:" + mdseCd + ",WH Code:" + rtlWhCd + ",SWH Code:" + rtlSwhCd + ")" });
//                                if ("E".equals(cMsg.getMessageKind())) {
//                                    break;
//                                }
//                            }
//                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).procrTpCd_BS, (String) fsmRtlWhInfo.get("DEF_SRC_PROCR_TP_CD"));
//                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).srcRtlWhCd_B1, (String) fsmRtlWhInfo.get("DEF_SRC_RTL_WH_CD"));
//                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rtlWhNm_B2, (String) fsmRtlWhInfo.get("DEF_SRC_RTL_WH_NM"));
//                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).srcRtlSwhCd_B1, (String) fsmRtlWhInfo.get("DEF_SRC_RTL_SWH_NM"));
                            if (fsmRtlWhInfo != null) {
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).procrTpCd_AS, (String) fsmRtlWhInfo.get("DEF_SRC_PROCR_TP_CD"));
                                if (!PROCR_TP.SUPPLIER.equals(sMsg.A.no(i).procrTpCd_AS.getValue())) {
                                    String rtlWHcd = (String) fsmRtlWhInfo.get("DEF_SRC_RTL_WH_CD");
                                    String rtlWhNm = (String) fsmRtlWhInfo.get("DEF_SRC_RTL_WH_NM");
                                    String rtlSwhNm = (String) fsmRtlWhInfo.get("DEF_SRC_RTL_SWH_NM");
                                    if (ZYPCommonFunc.hasValue(rtlWHcd)) {
                                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).srcRtlWhCd_B1,rtlWHcd);
                                    }
                                    if (ZYPCommonFunc.hasValue(rtlWhNm)) {
                                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rtlWhNm_B2,rtlWhNm);
                                    }
                                    if (ZYPCommonFunc.hasValue(rtlSwhNm)) {
                                        ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).srcRtlSwhCd_B1, rtlSwhNm);
                                    }
                                }
                            }
                            // END   2019/08/20 T.Ogura [QC#51796,MOD]
                        }
                        // END 2019/08/29 M.Naito [QC#51796,MOD]
                        // END 2018/12/05 J.Kim [QC#18224,ADD]
                        sMsg.B.setValidCount(i + 1);
                    } else {
                        // Update Record
                        // set SMsg work
                        NPAL1070CommonLogic.search(cMsg, sMsg, EVENT_NM_NPAL1070_IMPORT);

                        if (sMsg.A.getValidCount() > 0) {

                            int updateLine = NPAL1070CommonLogic.getUpdateLineForImportProcess(cMsg, sMsg);
                            boolean addLineFlg = false;

                            if (updateLine < 0) {
                                // New Line
                                updateLine = i;
                                addLineFlg = true;
                            } else {
                                // Update Line.Not Add Line.
                                i--;
                            }

                            // copy to SMsg work
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).glblCmpyCd_B1, sMsg.A.no(0).glblCmpyCd_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).mrpInfoPk_B1, sMsg.A.no(0).mrpInfoPk_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).xxRqstTs_B1, sMsg.A.no(0).xxRqstTs_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).xxRqstTz_B1, sMsg.A.no(0).xxRqstTz_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).mrpPlnNm_B1, sMsg.A.no(0).mrpPlnNm_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rtlWhCatgCd_B1, sMsg.A.no(0).rtlWhCatgCd_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rtlWhCatgDescTxt_B1, sMsg.A.no(0).rtlWhCatgDescTxt_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).invtyLocCd_B1, sMsg.A.no(0).invtyLocCd_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rtlWhCd_B1, sMsg.A.no(0).rtlWhCd_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rtlWhNm_B1, sMsg.A.no(0).rtlWhNm_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rtlSwhCd_B1, sMsg.A.no(0).rtlSwhCd_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rtlSwhNm_B1, sMsg.A.no(0).rtlSwhNm_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).mdseCd_B1, sMsg.A.no(0).mdseCd_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).mdseDescShortTxt_B1, sMsg.A.no(0).mdseDescShortTxt_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).mdseTpCd_B1, sMsg.A.no(0).mdseTpCd_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).coaMdseTpCd_B1, sMsg.A.no(0).coaMdseTpCd_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).coaProdCd_B1, sMsg.A.no(0).coaProdCd_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).ropQty_B1, sMsg.A.no(0).ropQty_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).maxInvtyQty_B1, sMsg.A.no(0).maxInvtyQty_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).ovrdMaxInvtyQty_B1, sMsg.A.no(0).ovrdMaxInvtyQty_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).mrpEnblFlg_B1, sMsg.A.no(0).mrpEnblFlg_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).procrTpCd_BS, sMsg.A.no(0).procrTpCd_AS);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).srcLocCd_B1, sMsg.A.no(0).srcLocCd_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).srcRtlWhCd_B1, sMsg.A.no(0).srcRtlWhCd_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rtlWhNm_B2, sMsg.A.no(0).rtlWhNm_A2);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).srcRtlSwhCd_B1, sMsg.A.no(0).srcRtlSwhCd_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rtlSwhNm_B2, sMsg.A.no(0).rtlSwhNm_A2);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rplshDlyFlg_B1, sMsg.A.no(0).rplshDlyFlg_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rplshMonFlg_B1, sMsg.A.no(0).rplshMonFlg_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rplshTueFlg_B1, sMsg.A.no(0).rplshTueFlg_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rplshWedFlg_B1, sMsg.A.no(0).rplshWedFlg_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rplshThuFlg_B1, sMsg.A.no(0).rplshThuFlg_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rplshFriFlg_B1, sMsg.A.no(0).rplshFriFlg_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).supdFlg_B1, sMsg.A.no(0).supdFlg_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).mrpInfoCmntTxt_B1, sMsg.A.no(0).mrpInfoCmntTxt_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).mrpInfoRgtnStsCd_B1, sMsg.A.no(0).mrpInfoRgtnStsCd_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).xxRsltFlg_B1, sMsg.A.no(0).xxRsltFlg_A1);
                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).calcOrdProcCd_B1, sMsg.A.no(0).calcOrdProcCd_A1);

                            if (ZYPCommonFunc.hasValue(fMsg.mrpPlnNm_D1)) {
                                if (!sMsg.A.no(0).mrpPlnNm_A1.getValue().equals(fMsg.mrpPlnNm_D1.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).mrpPlnNm_B1, fMsg.mrpPlnNm_D1);
                                }
                            }
                            if (ZYPCommonFunc.hasValue(fMsg.ropQty_D1)) {
                                if (!sMsg.A.no(0).ropQty_A1.getValue().equals(fMsg.ropQty_D1.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).ropQty_B1, fMsg.ropQty_D1);
                                }
                            }
                            if (ZYPCommonFunc.hasValue(fMsg.maxInvtyQty_D1)) {
                                if (!sMsg.A.no(0).maxInvtyQty_A1.getValue().equals(fMsg.maxInvtyQty_D1.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).maxInvtyQty_B1, fMsg.maxInvtyQty_D1);
                                }
                            }

                            if (ZYPCommonFunc.hasValue(fMsg.mrpEnblFlg_D1)) {
                                if (!sMsg.A.no(0).mrpEnblFlg_A1.getValue().equals(fMsg.mrpEnblFlg_D1.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).mrpEnblFlg_B1, fMsg.mrpEnblFlg_D1);
                                }
                            }
                            if (ZYPCommonFunc.hasValue(fMsg.procrTpCd_D1)) {
                                if (!sMsg.A.no(0).procrTpCd_AS.getValue().equals(fMsg.procrTpCd_D1.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).procrTpCd_BS, fMsg.procrTpCd_D1);
                                }
                            }
                            if (ZYPCommonFunc.hasValue(fMsg.srcRtlWhCd_D1)) {
                                if (!sMsg.A.no(0).srcRtlWhCd_A1.getValue().equals(fMsg.srcRtlWhCd_D1.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).srcRtlWhCd_B1, fMsg.srcRtlWhCd_D1);
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rtlWhNm_B2, cMsg.rtlWhNm_W2);
                                }
                            }
                            if (ZYPCommonFunc.hasValue(fMsg.srcRtlSwhCd_D1)) {
                                if (!sMsg.A.no(0).srcRtlSwhCd_A1.getValue().equals(fMsg.srcRtlSwhCd_D1.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).srcRtlSwhCd_B1, fMsg.srcRtlSwhCd_D1);
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rtlSwhNm_B2, cMsg.rtlSwhNm_S2);
                                }
                            }
                            if (ZYPCommonFunc.hasValue(fMsg.rplshDlyFlg_D1)) {
                                if (!sMsg.A.no(0).rplshDlyFlg_A1.getValue().equals(fMsg.rplshDlyFlg_D1.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rplshDlyFlg_B1, fMsg.rplshDlyFlg_D1);
                                }
                            }
                            if (ZYPCommonFunc.hasValue(fMsg.rplshMonFlg_D1)) {
                                if (!sMsg.A.no(0).rplshMonFlg_A1.getValue().equals(fMsg.rplshMonFlg_D1.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rplshMonFlg_B1, fMsg.rplshMonFlg_D1);
                                }
                            }
                            if (ZYPCommonFunc.hasValue(fMsg.rplshTueFlg_D1)) {
                                if (!sMsg.A.no(0).rplshTueFlg_A1.getValue().equals(fMsg.rplshTueFlg_D1.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rplshTueFlg_B1, sMsg.A.no(0).rplshTueFlg_A1);
                                }
                            }
                            if (ZYPCommonFunc.hasValue(fMsg.rplshWedFlg_D1)) {
                                if (!sMsg.A.no(0).rplshWedFlg_A1.getValue().equals(fMsg.rplshWedFlg_D1.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rplshWedFlg_B1, sMsg.A.no(0).rplshWedFlg_A1);
                                }
                            }
                            if (ZYPCommonFunc.hasValue(fMsg.rplshThuFlg_D1)) {
                                if (!sMsg.A.no(0).rplshThuFlg_A1.getValue().equals(fMsg.rplshThuFlg_D1.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rplshThuFlg_B1, sMsg.A.no(0).rplshThuFlg_A1);
                                }
                            }
                            if (ZYPCommonFunc.hasValue(fMsg.rplshFriFlg_D1)) {
                                if (!sMsg.A.no(0).rplshFriFlg_A1.getValue().equals(fMsg.rplshFriFlg_D1.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rplshFriFlg_B1, fMsg.rplshFriFlg_D1);
                                }
                            }
                            if (ZYPCommonFunc.hasValue(fMsg.mrpInfoCmntTxt_D1)) {
                                if (!sMsg.A.no(0).mrpInfoCmntTxt_A1.getValue().equals(fMsg.mrpInfoCmntTxt_D1.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).mrpInfoCmntTxt_B1, fMsg.mrpInfoCmntTxt_D1);
                                }
                            }
                            // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
                            if (ZYPCommonFunc.hasValue(fMsg.calcOrdProcCd_D1)) {
                                if (!sMsg.A.no(0).calcOrdProcCd_A1.getValue().equals(fMsg.calcOrdProcCd_D1.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).calcOrdProcCd_B1, fMsg.calcOrdProcCd_D1);
                                }
                                if (count == 1) {
                                    ZYPEZDItemValueSetter.setValue(cMsg.calcOrdProcCd, fMsg.calcOrdProcCd_D1);
                                    ZYPEZDItemValueSetter.setValue(sMsg.calcOrdProcCd, fMsg.calcOrdProcCd_D1);
                                }
                            }
                            // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
                            // START 2018/12/05 J.Kim [QC#18224,ADD]
                            Map<String, Object> fsmRtlWhInfo = null;
                            // START 2019/08/29 M.Naito [QC#51796,MOD]
//                            if (NPAL1070CommonLogic.hasRegisterAuthority(functionList())) {
                            if (NPAL1070CommonLogic.hasRegisterAuthority(functionList()) && (!ZYPCommonFunc.hasValue(sMsg.A.no(i).procrTpCd_AS) || !PROCR_TP.SUPPLIER.equals(sMsg.A.no(i).procrTpCd_AS.getValue()))) {
                            // END 2019/08/29 M.Naito [QC#51796,MOD]
                                String glblCmpyCd = cMsg.glblCmpyCd.getValue();
                                String rtlWhCd = fMsg.rtlWhCd_D1.getValue();
                                String mdseCd = fMsg.mdseCd_D1.getValue();
                                String rtlSwhCd = fMsg.rtlSwhCd_D1.getValue();
                                fsmRtlWhInfo = NPAL1070CommonLogic.getRtlWhInfo(glblCmpyCd, rtlWhCd);
                                // START 2019/08/20 T.Ogura [QC#51796,MOD]
//                                if (fsmRtlWhInfo == null) {
//                                    cMsg.setMessageInfo(NMAM0039E, new String[] {"Import file(Item Number:" + mdseCd + ",WH Code:" + rtlWhCd + ",SWH Code:" + rtlSwhCd + ")" });
//                                    if ("E".equals(cMsg.getMessageKind())) {
//                                        break;
//                                    }
//                                }
//                                ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).procrTpCd_BS, (String) fsmRtlWhInfo.get("DEF_SRC_PROCR_TP_CD"));
//                                ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).srcRtlWhCd_B1, (String) fsmRtlWhInfo.get("DEF_SRC_RTL_WH_CD"));
//                                ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).rtlWhNm_B2, (String) fsmRtlWhInfo.get("DEF_SRC_RTL_WH_NM"));
//                                ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).srcRtlSwhCd_B1, (String) fsmRtlWhInfo.get("DEF_SRC_RTL_SWH_NM"));
                                if (fsmRtlWhInfo != null) {
                                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(updateLine).procrTpCd_BS, (String) fsmRtlWhInfo.get("DEF_SRC_PROCR_TP_CD"));
                                    if (!PROCR_TP.SUPPLIER.equals(sMsg.B.no(updateLine).procrTpCd_BS.getValue())) {
                                        String rtlWHcd = (String) fsmRtlWhInfo.get("DEF_SRC_RTL_WH_CD");
                                        String rtlWhNm = (String) fsmRtlWhInfo.get("DEF_SRC_RTL_WH_NM");
                                        String rtlSwhNm = (String) fsmRtlWhInfo.get("DEF_SRC_RTL_SWH_NM");
                                        if (ZYPCommonFunc.hasValue(rtlWHcd)) {
                                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).srcRtlWhCd_B1,rtlWHcd);
                                        }
                                        if (ZYPCommonFunc.hasValue(rtlWhNm)) {
                                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rtlWhNm_B2,rtlWhNm);
                                        }
                                        if (ZYPCommonFunc.hasValue(rtlSwhNm)) {
                                            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).srcRtlSwhCd_B1, rtlSwhNm);
                                        }
                                    }
                                }
                                // END   2019/08/20 T.Ogura [QC#51796,MOD]
                            }
                            // END 2018/12/05 J.Kim [QC#18224,ADD]
                            if (addLineFlg) {
                                sMsg.B.setValidCount(i + 1);
                            }
                        }
                    }
                }
            }
        } finally {
            mappedFile.close();
            cMsg.xxFileData_UP.deleteTempFile();
        }

        if (sMsg.B.getValidCount() > 0) {
            // copy from sMsg.B to sMsg.A
            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.A);
            sMsg.A.setValidCount(sMsg.B.getValidCount());
            for (int k = 0; k < sMsg.B.getValidCount(); k++) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).glblCmpyCd_A1, sMsg.B.no(k).glblCmpyCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).mrpInfoPk_A1, sMsg.B.no(k).mrpInfoPk_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).xxRqstTs_A1, sMsg.B.no(k).xxRqstTs_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).xxRqstTz_A1, sMsg.B.no(k).xxRqstTz_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).mrpPlnNm_A1, sMsg.B.no(k).mrpPlnNm_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).rtlWhCatgCd_A1, sMsg.B.no(k).rtlWhCatgCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).rtlWhCatgDescTxt_A1, sMsg.B.no(k).rtlWhCatgDescTxt_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).invtyLocCd_A1, sMsg.B.no(k).invtyLocCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).rtlWhCd_A1, sMsg.B.no(k).rtlWhCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).rtlWhNm_A1, sMsg.B.no(k).rtlWhNm_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).rtlSwhCd_A1, sMsg.B.no(k).rtlSwhCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).rtlSwhNm_A1, sMsg.B.no(k).rtlSwhNm_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).mdseCd_A1, sMsg.B.no(k).mdseCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).mdseDescShortTxt_A1, sMsg.B.no(k).mdseDescShortTxt_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).mdseTpCd_A1, sMsg.B.no(k).mdseTpCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).coaMdseTpCd_A1, sMsg.B.no(k).coaMdseTpCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).coaProdCd_A1, sMsg.B.no(k).coaProdCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).ropQty_A1, sMsg.B.no(k).ropQty_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).maxInvtyQty_A1, sMsg.B.no(k).maxInvtyQty_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).ovrdMaxInvtyQty_A1, sMsg.B.no(k).ovrdMaxInvtyQty_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).mrpEnblFlg_A1, sMsg.B.no(k).mrpEnblFlg_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).procrTpCd_AS, sMsg.B.no(k).procrTpCd_BS);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).srcLocCd_A1, sMsg.B.no(k).srcLocCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).srcRtlWhCd_A1, sMsg.B.no(k).srcRtlWhCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).rtlWhNm_A2, sMsg.B.no(k).rtlWhNm_B2);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).srcRtlSwhCd_A1, sMsg.B.no(k).srcRtlSwhCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).rtlSwhNm_A2, sMsg.B.no(k).rtlSwhNm_B2);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).rplshDlyFlg_A1, sMsg.B.no(k).rplshDlyFlg_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).rplshMonFlg_A1, sMsg.B.no(k).rplshMonFlg_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).rplshTueFlg_A1, sMsg.B.no(k).rplshTueFlg_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).rplshWedFlg_A1, sMsg.B.no(k).rplshWedFlg_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).rplshThuFlg_A1, sMsg.B.no(k).rplshThuFlg_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).rplshFriFlg_A1, sMsg.B.no(k).rplshFriFlg_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).supdFlg_A1, sMsg.B.no(k).supdFlg_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).mrpInfoCmntTxt_A1, sMsg.B.no(k).mrpInfoCmntTxt_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).mrpInfoRgtnStsCd_A1, sMsg.B.no(k).mrpInfoRgtnStsCd_B1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).xxRsltFlg_A1, sMsg.B.no(k).xxRsltFlg_B1);
                // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(k).calcOrdProcCd_A1, sMsg.B.no(k).calcOrdProcCd_B1);
                // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
            }
            // copy data from SMsg to CMsg
            NPAL1070CommonLogic.copyFromSmsgOntoCmsg(cMsg, sMsg);
        }
    }
    /**
     * <pre>
     * validateAndCopyToSMsg_UPLOAD
     * </pre>
     * @param int status
     * @param int count
     * @param NPAL1070CMsg cMsg
     * @param NPAL1070_ASMsgArray aSMsg
     * @param NPAL1070F00FMsg FileMsg
     */
    private boolean validateUploadFile(int status, int count, NPAL1070CMsg cMsg, NPAL1070SMsg sMsg, NPAL1070F01FMsg fMsg) {
        if (count > sMsg.A.length()) {
            cMsg.setMessageInfo("NPAM1199E");
            return false;
        }
        if (status == 1000) {
            cMsg.setMessageInfo("NMAM0052E", new String[]{"CSV"});
            return false;
        }

        if (!checkMandatoryCheck(fMsg, cMsg)) {
            return false;
        }

        cMsg.mrpInfoPk.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, fMsg.rtlWhCd_D1);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd, fMsg.rtlSwhCd_D1);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, fMsg.mdseCd_D1);
        ZYPEZDItemValueSetter.setValue(cMsg.procrTpCd_SL, fMsg.procrTpCd_D1);

        // START 2019/08/29 M.Naito [QC#51796,MOD]
        if (!PROCR_TP.SUPPLIER.equals(fMsg.procrTpCd_D1.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.srcRtlWhCd, fMsg.srcRtlWhCd_D1);
            ZYPEZDItemValueSetter.setValue(cMsg.srcRtlSwhCd, fMsg.srcRtlSwhCd_D1);
        } else {
            fMsg.srcRtlWhCd_D1.clear();
            fMsg.srcRtlSwhCd_D1.clear();
            fMsg.rtlWhNm_D2.clear();
            fMsg.rtlSwhNm_D2.clear();
        }
        // END 2019/08/29 M.Naito [QC#51796,MOD]
        // QC#16225 Add.
        ZYPEZDItemValueSetter.setValue(cMsg.mrpPlnNm, fMsg.mrpPlnNm_D1);

        // START 2018/07/18 S.Katsuma [QC#26709,MOD]
//        if (!NPAL1070CommonLogic.addCheck(cMsg, sMsg, EVENT_NM_NPAL1070_IMPORT)) {
        if (!NPAL1070CommonLogic.addCheck4Upload(cMsg, sMsg)) {
            return false;
        }
        // END 2018/07/18 S.Katsuma [QC#26709,MOD]

        if (PROCR_TP.WAREHOUSE.equals(fMsg.procrTpCd_D1.getValue())
            || PROCR_TP.WORK_ORDER.equals(fMsg.procrTpCd_D1.getValue())) {
            if (!ZYPCommonFunc.hasValue(fMsg.srcRtlWhCd_D1)) {
                cMsg.setMessageInfo("ZZM9000E", new String[]{"Source WH SWH"});
                return false;
            }
            if (!ZYPCommonFunc.hasValue(fMsg.srcRtlSwhCd_D1)) {
                cMsg.setMessageInfo("ZZM9000E", new String[]{"Source WH SWH"});
                return false;
            }
        }

        // START 2019/08/29 M.Naito [QC#51796,DEL]
//        if (PROCR_TP.SUPPLIER.equals(fMsg.procrTpCd_D1.getValue())) {
//            if (ZYPCommonFunc.hasValue(fMsg.srcRtlWhCd_D1)
//                || (ZYPCommonFunc.hasValue(fMsg.srcRtlSwhCd_D1))) {
//                cMsg.setMessageInfo("NPAM0016E", new String[]{"Source WH SWH"});
//                return false;
//            }
//        }
        // END 2019/08/29 M.Naito [QC#51796,DEL]

        if (cMsg.getMessageInfo() != null) {
            return false;
        }

        return true;
    }

    /**
    * <pre>
    * copySMsgAToSMsgB
    * </pre>
    * @param fMsg NPAL1070F00FMsg 
    * @param cMsg NPAL1070CMsg 
    * @return boolean 
    */
    private static boolean checkMandatoryCheck(NPAL1070F01FMsg fMsg, NPAL1070CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(fMsg.rtlWhCd_D1.getValue())) {
            cMsg.setMessageInfo("ZZZM9025E", new String[]{"WH Code"});
            return false;
        }
        if (!ZYPCommonFunc.hasValue(fMsg.rtlSwhCd_D1.getValue())) {
            cMsg.setMessageInfo("ZZZM9025E", new String[]{"SWH Code"});
            return false;
        }
        if (!ZYPCommonFunc.hasValue(fMsg.mdseCd_D1.getValue())) {
            cMsg.setMessageInfo("ZZZM9025E", new String[]{"Mdse Code"});
            return false;
        }
        return true;
    }

    /**
    * <pre>
    * Template file for Upload
    * </pre>
    * @param cMsg NPAL1070CMsg
    * @param sMsg NPAL1070SMsg
    */
    private void doProcess_NPAL1070_UploadTemplate(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        NPAL1070F00FMsg fMsg = new NPAL1070F00FMsg();

        cMsg.xxFileData_UP.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("Min_Max_Planning"), ".csv");
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_UP.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_HDR_UPLOAD);

        csvOutFile.close();
    }
    /**
    * <pre>
    * Common Download
    * </pre>
    * @param cMsg NPAL1070CMsg
    * @param sMsg NPAL1070SMsg
    */
    private void doProcess_NPAL1070_Download(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NPAL1070Constant.MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NPAL1070Query.getInstance().getClass());
            //create csv file, parameters
            String ssmId = "search";
            cMsg.xxFileData_DW.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NPAL1070Constant.CSV_FILE_NAME), ".csv");

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_CMSG, cMsg);
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
            ssmParam.put(DB_PARAM_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);
            // QC#21229
            ssmParam.put(DB_PARAM_EZBUSINESSID, EZBUSINESSID);
            ssmParam.put(DB_PARAM_BIZ_APP_NM, BIZ_APP_NM);
            ssmParam.put(DB_PARAM_ROWNUM, ROWNUM_DOWNLOAD);
            // QC#24796
            ssmParam.put(DB_PARAM_BIZ_APP_NM, "Item Master Attachments");
            ssmParam.put(DB_PARAM_ATT_DATA_KEY_TXT, "Item Number");
            ps = ssmLLClient.createPreparedStatement(ssmId, ssmParam, execParam);
            rs = ps.executeQuery();
            writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }
    /**
     * Download Event
     * @param cMsg      NPAL1070CMsg
     * @param ResultSet rs
     */
    private void writeCsvFile(NPAL1070CMsg cMsg, ResultSet rs) throws SQLException {

        NPAL1070F01FMsg fMsg = new NPAL1070F01FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_DW.getTempFilePath(), fMsg);

        //write header
        csvOutFile.writeHeader(NPAL1070Constant.CSV_HDR_DOWNLOAD);

        if (!rs.next()) {
            cMsg.setMessageInfo("NZZM0000E", null);
            csvOutFile.close();
            return;
        }

        //write contents
        do {
            if (rs.getRow() >= NPAL1070Constant.LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo("NZZM0001W", null);
                break;
            }

            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.mrpPlnNm_D1, rs.getString("MRP_PLN_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCatgDescTxt_D1, rs.getString("RTL_WH_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCd_D1, rs.getString("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_D1, rs.getString("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhCd_D1, rs.getString("RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhNm_D1, rs.getString("RTL_SWH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_D1, rs.getString("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_D1, rs.getString("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxRsltFlg_D1, rs.getString("XX_RSLT_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.coaMdseTpCd_D1, rs.getString("COA_MDSE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.coaProdCd_D1, rs.getString("COA_PROD_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.ropQty_D1, rs.getBigDecimal("ROP_QTY"));
            ZYPEZDItemValueSetter.setValue(fMsg.maxInvtyQty_D1, rs.getBigDecimal("MAX_INVTY_QTY"));
            ZYPEZDItemValueSetter.setValue(fMsg.mrpEnblFlg_D1, rs.getString("MRP_ENBL_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.procrTpCd_D1, rs.getString("PROCR_TP_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.procrTpDescTxt_D1, rs.getString("PROCR_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.srcRtlWhCd_D1, rs.getString("SRC_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_D2, rs.getString("SRC_RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.srcRtlSwhCd_D1, rs.getString("SRC_RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhNm_D2, rs.getString("SRC_RTL_SWH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.rplshDlyFlg_D1, rs.getString("RPLSH_DLY_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.rplshMonFlg_D1, rs.getString("RPLSH_MON_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.rplshTueFlg_D1, rs.getString("RPLSH_TUE_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.rplshWedFlg_D1, rs.getString("RPLSH_WED_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.rplshThuFlg_D1, rs.getString("RPLSH_THU_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.rplshFriFlg_D1, rs.getString("RPLSH_FRI_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.supdFlg_D1, rs.getString("SUPD_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.mrpInfoCmntTxt_D1, rs.getString("MRP_INFO_CMNT_TXT"));
            // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
            if (!ZYPCommonFunc.hasValue(rs.getString("CALC_ORD_PROC_CD"))) {
                ZYPEZDItemValueSetter.setValue(fMsg.calcOrdProcCd_D1, ZYPConstant.FLG_OFF_N);
            } else if (ZYPConstant.FLG_ON_1.equals(rs.getString("CALC_ORD_PROC_CD"))){
                ZYPEZDItemValueSetter.setValue(fMsg.calcOrdProcCd_D1, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(fMsg.calcOrdProcCd_D1, ZYPConstant.FLG_OFF_N);
            }
            // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * Add QC#21229 ItemMaster Attachment
     * @param cMsg NPAL1060CMsg
     */
    private void doProcess_NPAL1070_ItemMasterAttachment(NPAL1070CMsg cMsg) {

        String mdseCd = cMsg.A.no(cMsg.xxNum.getValueInt()).mdseCd_A1.getValue();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_MDSE_CD, mdseCd);
        ssmParam.put(DB_PARAM_BIZ_APP_NM, "Item Master Attachments");
        ssmParam.put(DB_PARAM_ATT_DATA_KEY_TXT, "Item Number");

        S21SsmEZDResult result = NPAL1070Query.getInstance().getMdseCd(ssmParam);

        if (result.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(cMsg.X.no(2).xxPopPrm_AT, (String) result.getResultObject());
            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.X.no(2).xxPopPrm_AT, mdseCd);
    }

    // START 2018/12/03 J.Kim [QC#18224,ADD]
    private List<String> functionList() {
        // Role Function
        return getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
    }
    // END 2018/12/03 J.Kim [QC#18224,ADD]

    // START 2022/10/05 M.Kikushima [QC#60560,ADD]
    /**NPAL1070Scrn00_ApplyToAll
     * Add
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
     private void doProcess_NPAL1070_ApplyToAll(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        NPAL1070CommonLogic.copyFromSmsgOntoCmsg(cMsg, sMsg);

     }
     // END 2022/10/05 M.Kikushima [QC#60560,ADD]
     // START 2023/04/17 S.Dong [QC#61348,ADD]
     /**
      * Set WrnSkipFlg
      * @param cMsg NPAL1070CMsg
      */
      private void setWrnSkipFlg(NPAL1070CMsg cMsg) {
          ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg_SB, ZYPConstant.FLG_OFF_N);
      }
      // END 2023/04/17 S.Dong [QC#61348,ADD]
}
