/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_0;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_1;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_5;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_ADDL_HEADER;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/01   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/08   Fujitsu         M.Hara          Update          QC#1615
 * 2016/02/15   Fujitsu         M.Hara          Create          QC#1692
 * 2016/02/22   Fujitsu         M.Ohno          Update          QC#3243
 * 2016/02/29   Fujitsu         M.Nakamura      Update          QC#1693/1700
 * 2016/03/10   Fujitsu         Y.kanefusa      Update          QC#1680
 * 2016/05/11   Fujitsu         T.Murai         Update          QC#7861
 * 2016/08/26   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2017/07/21   Fujitsu         S.Takami        Update          S21_NA#19847
 * 2017/11/17   Fujitsu         A.Kosai         Update          S21_NA#22252
 * 2018/04/12   Fujitsu         K.Ishizuka      Update          S21_NA#23704
 * 2019/11/21   Fujitsu         S.Takami        Update          S21_NA#54202
 * 2020/03/16   Fujitsu         C.Hara          Update          QC#56132
 *</pre>
 */
public class NWAL1500_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if ("OpenWin_OrderCategory".equals(scrEventNm) || "OnBlur_DeriveFromCategory".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgCd, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgDescTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_OrderReason".equals(scrEventNm) || "OnBlur_DeriveFromReason".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdTpCd, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdTpDescTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_Salesrep".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepCode".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepName".equals(scrEventNm)) {
            // 2016/05/11 S21_NA#7861 Mod Start
//            ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepPsnCd, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnNum, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            // 2016/05/11 S21_NA#7861 Mod End
            ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepTocNm, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepTocCd, scrnMsg.Z.no(IDX_5).xxComnScrColValTxt.getValue());
        } else if ("OpenWin_CarrierServiceLevel".equals(scrEventNm) || "OnBlur_DeriveFromCarrierServiceLevel".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.carrSvcLvlCd, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.carrSvcLvlDescTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            return null;
        } else if ("OpenWin_CSMPNumber".equals(scrEventNm) || "OnBlur_DeriveFromCSMPNumber".equals(scrEventNm) || "OnBlur_DeriveFromDealerRefNumber".equals(scrEventNm)) {
            int cellIdx = scrnMsg.xxCellIdx.getValueInt(); // 2016/08/26 S21_NA#9806 Add
            if (cellIdx < 0) { // 2016/08/26 S21_NA#9806 Add Condition 
                ZYPEZDItemValueSetter.setValue(scrnMsg.csmpContrNum, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.dlrRefNum, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
                // 2017/07/21 S21_NA#19847 Del Start
//                return null;
                // 2017/07/21 S21_NA#19847 Del End
            } else { // 2016/08/26 S21_NA#9806 Add Start
                String dplyTab = scrnMsg.xxDplyTab.getValue();
                if (TAB_LINE_CONFIG.equals(dplyTab)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(cellIdx).csmpContrNum_LC, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(cellIdx).dlrRefNum_LC, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
                } else if (TAB_RMA.equals(dplyTab)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(cellIdx).csmpContrNum_RC, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(cellIdx).dlrRefNum_RC, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
                }
            } // 2016/08/26 S21_NA#9806 Add End
        } else if ("OpenWin_Warehouse".equals(scrEventNm) || "OnBlur_ChangeWH".equals(scrEventNm)) {
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIndex).rtlWhCd_LL, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIndex).rtlWhNm_LL, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(selectIndex).rtlWhCd_RL, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(selectIndex).rtlWhNm_RL, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            }
        } else if ("OpenWin_SubWarehouse".equals(scrEventNm) || "OnBlur_ChangeSubWH".equals(scrEventNm)) {
            // 2017/11/17 S21_NA#22252 Mod Start
            //ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIndex).rtlSwhCd_LL, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            //ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIndex).rtlSwhNm_LL, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIndex).rtlSwhCd_LL, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIndex).rtlSwhNm_LL, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(selectIndex).rtlSwhCd_RL, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(selectIndex).rtlSwhNm_RL, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            }
            // 2017/11/17 S21_NA#22252 Mod End
        } else if ("OpenWin_SubstituteItem".equals(scrEventNm) || "OnBlur_DeriveFromSubstituteItem".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectIndex).sbstMdseCd_LL, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            // 2019/11/21 S21_NA#54202 Del Start
//            return null;
            // 2019/11/21 S21_NA#54202 Del End
        } else if ("OpenWin_FreightTerms".equals(scrEventNm) || "OnBlur_DeriveFromFreightTerms".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.frtCondCd, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.frtCondDescTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            // return null; 2016/03/04 S21_NA#1679 Del
        } else if ("OpenWin_OrdLogTp".equals(scrEventNm) || "OnBlur_OrdLogTpNm".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ordLogTpCd, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.ordLogTpDescTxt_NM, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            return null;
        } else if ("OpenWin_AssnProgram".equals(scrEventNm) || "OnBlur_DeriveFromAssnProgramNumber".equals(scrEventNm) || "OnBlur_DeriveFromAssnProgramName".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcContrNum, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcContrNm, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
            return null;
        }

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        // 2019/11/21 S21_NA#54202 Del Start
//        if ("OpenWin_SubstituteItem".equals(scrEventNm) || "OnBlur_DeriveFromSubstituteItem".equals(scrEventNm)) {
//            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).custMdseCd_LL);
//            return;
//        } else 2019/11/21 S21_NA#54202 Del End
        if ("OpenWin_OrdLogTp".equals(scrEventNm) || "OnBlur_OrdLogTpNm".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.invCmntTxt);
            return;
        // DEL #1680 2016/03/11
        //  } else if ("OpenWin_FreightTerms".equals(scrEventNm) || "OnBlur_DeriveFromFreightTerms".equals(scrEventNm)) {
        //      scrnMsg.setFocusItem(scrnMsg.shpgSvcLvlCd);
        //    // return; 2016/03/04 S21_NA#1679 Del
        } else if ("OpenWin_CarrierServiceLevel".equals(scrEventNm) || "OnBlur_DeriveFromCarrierServiceLevel".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.carrAcctNum);
            return;
        } else if ("OpenWin_CSMPNumber".equals(scrEventNm) || "OnBlur_DeriveFromCSMPNumber".equals(scrEventNm) || "OnBlur_DeriveFromDealerRefNumber".equals(scrEventNm)) {
            // 2017/07/21 S21_NA#19847 Del Start
//            // 2016/08/26 S21_NA#9806 Mod Start
////            scrnMsg.setFocusItem(scrnMsg.ordSgnDt);
////            return;
//            int cellIdx = scrnMsg.xxCellIdx.getValueInt();
//            if (cellIdx < 0) {
//                scrnMsg.setFocusItem(scrnMsg.ordSgnDt);
//                return;
//            }
//            // 2016/08/26 S21_NA#9806 Mod Start
            // 2017/07/21 S21_NA#19847 Del End
        } else if ("OpenWin_AssnProgram".equals(scrEventNm) || "OnBlur_DeriveFromAssnProgramNumber".equals(scrEventNm) || "OnBlur_DeriveFromAssnProgramName".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.ordSgnDt);
            return;
        }

        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if ("OpenWin_OrderCategory".equals(scrEventNm) || "OnBlur_DeriveFromCategory".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.dsOrdTpDescTxt);
            NWAL1500CommonLogicForScrnFields.setProtectByOrdCatgBizCtx(this, scrnMsg);
        } else if ("OpenWin_OrderReason".equals(scrEventNm) || "OnBlur_DeriveFromReason".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.dsOrdRsnCd);
            NWAL1500CommonLogicForScrnFields.setProtectByOrdCatgBizCtx(this, scrnMsg);
        } else if ("OpenWin_Salesrep".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepCode".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepName".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.prcCatgNm);
        } else if ("OpenWin_Warehouse".equals(scrEventNm) || "OnBlur_ChangeWH".equals(scrEventNm)) {
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).rtlSwhNm_LL);
            } else {
                // 2020/03/16 QC#56132 Add Start
                scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).rtlWhNm_RL);
                scrnMsg.putErrorScreen();
                // 2020/03/16 QC#56132 Add End
                // 2017/11/17 S21_NA#22252 Mod Start
                //scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).custUomCd_RL);
                scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).rtlSwhNm_RL);
                // 2017/11/17 S21_NA#22252 Mod End
            }
        } else if ("OpenWin_SubWarehouse".equals(scrEventNm) || "OnBlur_ChangeSubWH".equals(scrEventNm)) {
            // 2017/11/17 S21_NA#22252 Mod Start
            //scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).ordLineSrcCd_LL);
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).ordLineSrcCd_LL);
            } else {
                scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).custUomCd_RL);
            }
            // 2017/11/17 S21_NA#22252 Mod End
            NWAL1500CommonLogicForScrnFields.setProtectByRtlSwhCd(this, scrnMsg); // 2018/04/12 S21_NA#23704 Add
        // ADD #1680 2016/03/11
        } else if ("OpenWin_FreightTerms".equals(scrEventNm) || "OnBlur_DeriveFromFreightTerms".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.shpgSvcLvlCd);
            NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);
        } else if ("OpenWin_CSMPNumber".equals(scrEventNm) //
                || "OnBlur_DeriveFromCSMPNumber".equals(scrEventNm) //
                || "OnBlur_DeriveFromDealerRefNumber".equals(scrEventNm)) { // 2016/08/26 S21_NA#9806 Add Start
            int cellIdx = scrnMsg.xxCellIdx.getValueInt();
            String dplyTab = scrnMsg.xxDplyTab.getValue();
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                scrnMsg.setFocusItem(scrnMsg.A.no(cellIdx).csmpContrNum_LC);
            } else if (TAB_RMA.equals(dplyTab)) {
                scrnMsg.setFocusItem(scrnMsg.C.no(cellIdx).csmpContrNum_RC);
            } else if (TAB_ADDL_HEADER.equals(dplyTab)) {
                scrnMsg.setFocusItem(scrnMsg.csmpContrNum);
            }
            return;
        } else if ("OpenWin_SubstituteItem".equals(scrEventNm) || "OnBlur_DeriveFromSubstituteItem".equals(scrEventNm)) { // 2019/11/21 S21_NA#54202 Add Start
            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).custMdseCd_LL);
        } // 2019/11/21 S21_NA#54202 Add End
    }
}
