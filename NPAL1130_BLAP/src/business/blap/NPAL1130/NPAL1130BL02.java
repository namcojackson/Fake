/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1130;

import static business.blap.NPAL1130.constant.NPAL1130Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1130.constant.NPAL1130Constant.DB_PARAM_ETA_ETD_DT;
import static business.blap.NPAL1130.constant.NPAL1130Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1130.constant.NPAL1130Constant.DB_PARAM_INVTY_LOC_CD;
import static business.blap.NPAL1130.constant.NPAL1130Constant.DB_PARAM_MDSE_CD;
import static business.blap.NPAL1130.constant.NPAL1130Constant.DB_PARAM_MRP_INFO_RGTN_STS_CD;
import static business.blap.NPAL1130.constant.NPAL1130Constant.DB_PARAM_XX_MDSE_CD;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPAL11130_CMN_DOWNLOAD;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPAL1130_CMN_RESET;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPAL1130_INIT;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPAL1130_ONCHANGE_DPLY_CONF_ORD_ONLY;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPAL1130_OPEN_WIN_ATTACHMENTS;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPAL1130_PAGENEXT;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPAL1130_PAGEPREV;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPAL1130_SEARCH;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPAL1130_SEARCH_LOCATION_NAME;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPAL1130_SEARCH_MDSE_NAME;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPZC101001_BASE_CONTENTS;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPZC1010_INVTY_IN;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPZC1010_INVTY_OUT;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPZC1010_INVTY_STK;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPZC1010_ORDER_CPO;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPZC1010_ORDER_INVREQ;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPZC1010_ORDER_PO;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPZC1010_ORDER_POREQ;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPZC1010_ORDER_REMAN;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPZC1010_ORDER_TECHREQ;
import static business.blap.NPAL1130.constant.NPAL1130Constant.NPZC1010_ORDER_WO;
import static business.blap.NPAL1130.constant.NPAL1130Constant.SORT_BY_ASC;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDPStringItem;
import parts.common.EZDSMsg;
import business.blap.NPAL1130.common.NPAL1130CommonLogic;
import business.blap.NPAL1130.constant.NPAL1130Constant;
import business.file.NPAL1130F00FMsg;
import business.parts.NPZC101001PMsg;
import business.parts.NPZC108001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC101001.NPZC101001;
import com.canon.cusa.s21.api.NPZ.NPZC108001.NPZC108001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_INFO_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NPAL1130 PO Approval Setting Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/21   Hitachi         A.Kohinata      Create          N/A
 * 2016/03/01   CITS            K.Masaki        Update          CSA Project
 * 2016/12/14   CITS            T.Kikuhara      Update          QC#14774
 * 2017/07/04   CITS            M.Naito         Update          QC#19656
 * 2017/07/27   CITS            S.Katsuma       Update          QC#19656
 * 2017/08/01   CITS            S.Katsuma       Update          QC#19656
 * 2017/08/30   CITS            S.Katsuma       Update          QC#20806
 * 2017/11/07   CITS            S.Katsuma       Update          SOL#014(QC#18401)
 * 2018/01/17   CITS            T.Tokutomi      Update          QC#20832
 * 2018/03/06   CITS            T.Tokutomi      Update          QC#24581
 * 2018/04/11   CITS            K.Ogino         Update          QC#21229
 * 2018/05/01   CITS            T.Tokutomi      Update          QC#24814
 * 2018/06/21   CITS            T.Tokutomi      Update          QC#26532
 * 2018/06/22   CITS            T.Tokutomi      Update          QC#26534
 * 2018/07/09   CITS            Y.Iwasaki       Update          QC#27013
 * 2018/07/20   CITS            T.Hakodate      Update          QC#20962
 * 2018/07/24   CITS            T.hakodate      Update          QC#26532
 * 2018/08/21   CITS            T.Hakodate      Update          QC#27636
 * 2019/01/10   CITS            T.Tokutomi      Update          QC#29894
 * 2020/05/07   CITS            K.Ogino         Update          QC#56544-1
 * 2023/04/17   CSA             G.Quan          Insert          QC#61206
 *</pre>
 */
public class NPAL1130BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();

            if (NPAL1130_INIT.equals(screenAplID)
                || NPAL1130_CMN_RESET.equals(screenAplID)) {
                doProcess_NPAL1130_INIT((NPAL1130CMsg) cMsg, (NPAL1130SMsg) sMsg);
                ZYPGUITableColumn.getColData((NPAL1130CMsg) cMsg, (NPAL1130SMsg) sMsg);
            } else if (NPAL1130_SEARCH.equals(screenAplID)) {
                doProcess_NPAL1130Scrn00_Search((NPAL1130CMsg) cMsg, (NPAL1130SMsg) sMsg);
            } else if (NPAL1130_PAGEPREV.equals(screenAplID)) {
                doProcess_NPAL1130Scrn00_Prev((NPAL1130CMsg) cMsg, (NPAL1130SMsg) sMsg);
            } else if (NPAL1130_PAGENEXT.equals(screenAplID)) {
                doProcess_NPAL1130Scrn00_Next((NPAL1130CMsg) cMsg, (NPAL1130SMsg) sMsg);
            } else if (NPAL1130_SEARCH_MDSE_NAME.equals(screenAplID)) {
                doProcess_NPAL1130Scrn00_SearchMdseName((NPAL1130CMsg) cMsg, (NPAL1130SMsg) sMsg);
            } else if (NPAL1130_SEARCH_LOCATION_NAME.equals(screenAplID)) {
                doProcess_NPAL1130Scrn00_SearchLocationName((NPAL1130CMsg) cMsg, (NPAL1130SMsg) sMsg);
            } else if (NPAL11130_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_NPAL1130Scrn00_CMN_Download((NPAL1130CMsg) cMsg, (NPAL1130SMsg) sMsg);
            // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
            } else if (NPAL1130_ONCHANGE_DPLY_CONF_ORD_ONLY.equals(screenAplID)) {
                doProcess_NPAL1130Scrn00_OnChange_DplyConfOrdOnly((NPAL1130CMsg) cMsg, (NPAL1130SMsg) sMsg);
            // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
            // QC#21229
            } else if (NPAL1130_OPEN_WIN_ATTACHMENTS.equals(screenAplID)) {
                doProcess_NPAL1130_Attachment((NPAL1130CMsg) cMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NPAL1130_INIT(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg) {

        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),DEL]
//        cMsg.clear();
//        sMsg.clear();
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),DEL]
        // QC#29894 Update.
        clearMsg(cMsg, sMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));

        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        // QC#29894 Update.
        if(ZYPCommonFunc.hasValue(cMsg.mdseCd) && ZYPCommonFunc.hasValue(cMsg.rtlWhCd)){
            doProcess_NPAL1130Scrn00_Search(cMsg, sMsg);
        }
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
    }

    private void doProcess_NPAL1130Scrn00_Search(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg) {

        clearMsg(cMsg, sMsg);

        checkSearch(cMsg, sMsg, NPAL1130_SEARCH);
    }

    private void clearMsg(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg) {

        cMsg.mdseDescShortTxt.clear();
        cMsg.ordTakeMdseCd.clear();
        cMsg.supdFlg.clear();
        cMsg.rtlWhNm.clear();
        cMsg.rtlSwhNm.clear();
        cMsg.invtyLocCd.clear();
        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        cMsg.mrpPlnNm.clear();
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        cMsg.ropQty.clear();
        cMsg.maxOrdQty.clear();
        cMsg.procrTpDescTxt.clear();
        cMsg.srcRtlWhCd_S1.clear();
        cMsg.rtlWhNm_S1.clear();

        cMsg.xxPageShowFromNum.setValue(BigDecimal.ZERO);
        cMsg.xxPageShowToNum.setValue(BigDecimal.ZERO);
        cMsg.xxPageShowOfNum.setValue(BigDecimal.ZERO);

        ZYPTableUtil.clear(cMsg.S);
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C);
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
    }

    private void checkSearch(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg, String eventName) {

        // check item code
        String mdseName = NPAL1130CommonLogic.getMdseName(cMsg);

        if (ZYPCommonFunc.hasValue(mdseName)) {
            ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, mdseName);
        } else {
            cMsg.mdseDescShortTxt.clear();
            cMsg.mdseCd.setErrorInfo(1, "NZZM0010E", new String[] {cMsg.mdseCd.getValue() });
            return;
        }

        // check WH code
        String rtlWhNm;
        rtlWhNm = NPAL1130CommonLogic.getWhName(cMsg);

        if (ZYPCommonFunc.hasValue(rtlWhNm)) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, rtlWhNm);
        } else {
            cMsg.rtlWhNm.clear();
            cMsg.rtlWhCd.setErrorInfo(1, "NZZM0010E", new String[] {cMsg.rtlWhCd.getValue() });
            return;
        }

        // check sub WH code
        if (ZYPCommonFunc.hasValue(cMsg.rtlSwhCd.getValue())) {
            String rtlSwhNm;
            rtlSwhNm = NPAL1130CommonLogic.getSwhName(cMsg);
            if (ZYPCommonFunc.hasValue(rtlSwhNm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, rtlSwhNm);
            } else {
                cMsg.rtlSwhNm.clear();
                cMsg.rtlSwhCd.setErrorInfo(1, "NZZM0010E", new String[] {cMsg.rtlSwhCd.getValue() });
                return;
            }
        }

        // get Supersede Flag
        // same logic by NLBL3080CommonLogic
        ZYPEZDItemValueSetter.setValue(cMsg.supdFlg, NPAL1130CommonLogic.getSupdFlg(cMsg));

        // get Stock Status
        NPAL1130CommonLogic.getStockStatus(cMsg);

        // get MRP Info
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_MRP_INFO_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);

        S21SsmEZDResult result = NPAL1130Query.getInstance().getMrpInfo(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            Map recoad = resultList.get(0);
            // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.mrpPlnNm, (String) recoad.get("MRP_PLN_NM"));
            // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.ropQty, (BigDecimal) recoad.get("ROP_QTY"));
            ZYPEZDItemValueSetter.setValue(cMsg.maxOrdQty, (BigDecimal) recoad.get("MAX_INVTY_QTY"));
            ZYPEZDItemValueSetter.setValue(cMsg.invtyLocCd, (String) recoad.get("INVTY_LOC_CD"));
            //QC#26532 MOD START
            // START 2018/05/01 T.Tokutomi [QC#24814,ADD]
//            String calcOrdProcCd = (String) recoad.get("CALC_ORD_PROC_CD");
//            if (ZYPConstant.FLG_ON_1.equals(calcOrdProcCd)) {
//                // Include Entered Sales Order.
//                ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_CO, ZYPConstant.FLG_ON_Y);
//            } else {
//                ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_CO, ZYPConstant.FLG_OFF_N);
//            }
//            ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_CO, ZYPConstant.FLG_OFF_N);
            // END 2018/05/01 T.Tokutomi [QC#24814,ADD]
            // QC#26532 MOD END
            // QC#27636 mod start
        }/* else {

            // QC#26532 Update.
            ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_CO, ZYPConstant.FLG_OFF_N);
        }*/
        // QC#27636 mod end
        
        // get Procurement Source
        NPZC108001 npzc108001Api = new NPZC108001();
        NPZC108001PMsg npzc108001Pmsg = new NPZC108001PMsg();

        ZYPEZDItemValueSetter.setValue(npzc108001Pmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(npzc108001Pmsg.mdseCd, cMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(npzc108001Pmsg.invtyLocCd, cMsg.invtyLocCd.getValue());
        // QC#20962 ADD START
        ZYPEZDItemValueSetter.setValue(npzc108001Pmsg.slsDt, cMsg.slsDt.getValue());
        // QC#20962 ADD END
        

        npzc108001Api.execute(npzc108001Pmsg, ONBATCH_TYPE.ONLINE);

        if (ZYPCommonFunc.hasValue(npzc108001Pmsg.procrTpCd.getValue())) {
            String procrTpName = ZYPCodeDataUtil.getName("PROCR_TP", getGlobalCompanyCode(), npzc108001Pmsg.procrTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.procrTpDescTxt, procrTpName);
        }
        // QC#20962 ADD START
        if (ZYPCommonFunc.hasValue(npzc108001Pmsg.srcRtlWhCd.getValue())) {

            // get RTL WH Info
            Map<String, Object> ssmRtlWhParam = new HashMap<String, Object>();

            ssmRtlWhParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
            ssmRtlWhParam.put("rtlWhCd", npzc108001Pmsg.srcRtlWhCd.getValue());

            S21SsmEZDResult rtlWhRs = NPAL1130Query.getInstance().getRtlWhInfo(ssmRtlWhParam);

            List<Map> rtlWh = (List<Map>) rtlWhRs.getResultObject();

            if (rtlWhRs.isCodeNormal()) {

                Map recoad = rtlWh.get(0);
                ZYPEZDItemValueSetter.setValue(cMsg.srcRtlWhCd_S1, (String) recoad.get("RTL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_S1, (String) recoad.get("RTL_WH_NM"));
            }

        }

        // QC#20962 ADD END

        // MRP Calculation API
        NPZC101001 npzc101001Api = new NPZC101001();
        NPZC101001PMsg npzc101001Pmsg = new NPZC101001PMsg();

        ZYPEZDItemValueSetter.setValue(npzc101001Pmsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(npzc101001Pmsg.xxModeCd, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(npzc101001Pmsg.slsDt, cMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(npzc101001Pmsg.mdseCd, cMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(npzc101001Pmsg.invtyLocCd, cMsg.rtlWhCd.getValue() + cMsg.rtlSwhCd.getValue());
        ZYPEZDItemValueSetter.setValue(npzc101001Pmsg.xxModeInd, ZYPConstant.FLG_ON_1);

        npzc101001Api.execute(npzc101001Pmsg, ONBATCH_TYPE.ONLINE);
        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]. QC#20832 Update.
        if (S21ApiUtil.isXxMsgId(npzc101001Pmsg)) {
            cMsg.setMessageInfo(npzc101001Pmsg.xxMsgIdList.no(0).xxMsgId.getValue());
            if (npzc101001Pmsg.xxMsgIdList.no(0).xxMsgId.getValue().endsWith("E")) {
                return;
            }
        }
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]

        if (npzc101001Pmsg.scheduledOrdList.getValidCount() == 0) {
            // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
            EZDMsg.copy(cMsg, null, sMsg, null);
            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(cMsg.B);
            ZYPTableUtil.clear(cMsg.C);
            ZYPTableUtil.clear(sMsg.A);
            ZYPTableUtil.clear(sMsg.B);
            ZYPTableUtil.clear(sMsg.C);
            cMsg.xxFileData.deleteTempFile();
            sMsg.xxFileData.deleteTempFile();
            cMsg.setMessageInfo("NPAM1609I");
            // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        } else {
            if (eventName.equals(NPAL1130_SEARCH)) {
                EZDMsg.copy(cMsg, null, sMsg, null);
                createLines(cMsg, sMsg, npzc101001Pmsg);

                // START 2018/05/01 T.Tokutomi [QC#24814,ADD]
                // Modify 2018/06/21 T.Tokutomi [QC#26532]
                doProcess_NPAL1130Scrn00_OnChange_DplyConfOrdOnly(cMsg, sMsg);
                // END 2018/05/01 T.Tokutomi [QC#24814,ADD]

                // QC#27636 add start
                cMsg.setMessageInfo(NPAL1130Constant.ZZZM9003I, new String[] {"Search" });
                // QC#27636 add end

            } else if (eventName.equals(NPAL11130_CMN_DOWNLOAD)) {
                // START 2017/08/01 S.Katsuma [QC#19656,ADD]
                NPAL1130SMsg s2Msg = new NPAL1130SMsg();
                EZDMsg.copy(sMsg, null, s2Msg, null);
                s2Msg.A.clear();
                s2Msg.A.setValidCount(0);
                // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
                s2Msg.B.clear();
                s2Msg.C.clear();
                s2Msg.B.setValidCount(0);
                s2Msg.C.setValidCount(0);
                // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
                // END 2017/08/01 S.Katsuma [QC#19656,ADD]
                createLinesCsvDownload(cMsg, s2Msg, npzc101001Pmsg);
            }
        }
    }

    private void createLines(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg, NPZC101001PMsg npzc101001Pmsg) {
        // START 2017/08/01 S.Katsuma [QC#19656,ADD]
        setLines(cMsg, sMsg, npzc101001Pmsg);

        // QC#24814. Delete comment out code.

        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        // Calc Available Qty
        calcAvailableQty(sMsg);
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]

        // set valid count of cMsg
        if (sMsg.A.getValidCount() <= cMsg.A.length()) {
            cMsg.A.setValidCount(sMsg.A.getValidCount());
        } else {
            cMsg.A.setValidCount(cMsg.A.length());
        }

        // QC#27636 add start
        String invtyOut = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_INVTY_OUT, sMsg.glblCmpyCd.getValue());

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (sMsg.A.no(i).inbdOtbdNm_A0.getValue().equals(invtyOut)) {
                BigDecimal ordQty = sMsg.A.no(i).ordQty_A0.getValue();
                ordQty = ordQty.negate();
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ordQty_A0, ordQty);
            }
        }
        // QC#27636 add end
        
        EZDMsg.copy(sMsg, null, cMsg, null);

        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
    }

    private void createLinesCsvDownload(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg, NPZC101001PMsg npzc101001Pmsg) {
        
        // QC#27636 add start
        String invtyOut = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_INVTY_OUT, sMsg.glblCmpyCd.getValue());
        // QC#27636 add end
        
        // START 2017/08/01 S.Katsuma [QC#19656,ADD]
        setLines(cMsg, sMsg, npzc101001Pmsg);
        // END 2017/08/01 S.Katsuma [QC#19656,ADD]
        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        // Calc Available Qty
        calcAvailableQty(sMsg);
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]

        NPAL1130F00FMsg fMsg = new NPAL1130F00FMsg();

        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NPAL1130Constant.CSV_FILE_NAME), ".csv");
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(fMsg));

        // write header
        csvOutFile.writeHeader(NPAL1130Constant.CSV_HDR, fMsg, ZYPGUITableColumn.getColOrder(fMsg));

        // START 2017/08/01 S.Katsuma [QC#19656,ADD]
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, sMsg.A.no(i).mdseCd_A0);
            ZYPEZDItemValueSetter.setValue(fMsg.invtyLocCd, sMsg.A.no(i).invtyLocCd_A0);
            ZYPEZDItemValueSetter.setValue(fMsg.etaEtdDt, sMsg.A.no(i).etaEtdDt_A0);
            ZYPEZDItemValueSetter.setValue(fMsg.inbdOtbdNm, sMsg.A.no(i).inbdOtbdNm_A0);
            ZYPEZDItemValueSetter.setValue(fMsg.arTrxTpNm, sMsg.A.no(i).arTrxTpNm_A0);
            ZYPEZDItemValueSetter.setValue(fMsg.origOrdNum, sMsg.A.no(i).origOrdNum_A0);
            // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
//            ZYPEZDItemValueSetter.setValue(fMsg.srcOrdLineNum, sMsg.A.no(i).srcOrdLineNum_A0);
            ZYPEZDItemValueSetter.setValue(fMsg.xxDplyOrdLineNum, sMsg.A.no(i).xxDplyOrdLineNum_A0);
            // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.ordTpDescTxt, sMsg.A.no(i).ordTpDescTxt_A0);
            // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.ordHdrDplyStsDescTxt, sMsg.A.no(i).ordHdrDplyStsDescTxt_A0);
            ZYPEZDItemValueSetter.setValue(fMsg.shpgStsNm, sMsg.A.no(i).shpgStsNm_A0);
            ZYPEZDItemValueSetter.setValue(fMsg.apvlStsNm, sMsg.A.no(i).apvlStsNm_A0);
            // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
            // QC#27636 add start
            // ZYPEZDItemValueSetter.setValue(fMsg.ordQty,
            // sMsg.A.no(i).ordQty_A0);
            if (sMsg.A.no(i).inbdOtbdNm_A0.getValue().equals(invtyOut)) {
                BigDecimal ordQty = sMsg.A.no(i).ordQty_A0.getValue();
                ordQty = ordQty.negate();
                ZYPEZDItemValueSetter.setValue(fMsg.ordQty, ordQty);
            } else {
                ZYPEZDItemValueSetter.setValue(fMsg.ordQty, sMsg.A.no(i).ordQty_A0);
            }
            // QC#27636 add end
            ZYPEZDItemValueSetter.setValue(fMsg.avalQty, sMsg.A.no(i).avalQty_A0);
            ZYPEZDItemValueSetter.setValue(fMsg.supdFlg, sMsg.A.no(i).supdFlg_A0);
            ZYPEZDItemValueSetter.setValue(fMsg.ordDt, sMsg.A.no(i).ordDt_A0);
            ZYPEZDItemValueSetter.setValue(fMsg.rqstRcvDt, sMsg.A.no(i).rqstRcvDt_A0);
            csvOutFile.write();
        }
        // END 2017/08/01 S.Katsuma [QC#19656,ADD]

        // START 2017/08/01 S.Katsuma [QC#19656,DEL]
        /*
        BigDecimal curInvtyQty = BigDecimal.ZERO;
        BigDecimal inbdQty = BigDecimal.ZERO;
        BigDecimal outbdQty = BigDecimal.ZERO;
        int j = 0;
        
        // sort MRP calculation API output list
        S21SortTarget sortTarget = new S21SortTarget(npzc101001Pmsg.scheduledOrdList, NPZC101001_BASE_CONTENTS);
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(DB_PARAM_ETA_ETD_DT, SORT_BY_ASC);
        sortKey.add(DB_PARAM_XX_MDSE_CD, SORT_BY_ASC);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, npzc101001Pmsg.scheduledOrdList.getValidCount());

        String invtyStock = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_INVTY_STK, cMsg.glblCmpyCd.getValue());
        // add Stock lines
        for (int i = 0; i < npzc101001Pmsg.scheduledOrdList.getValidCount(); i++) {
            // START 2017/07/27 S.Katsuma [QC#19656,DEL]
//            curInvtyQty = BigDecimal.ZERO;
            // END 2017/07/27 S.Katsuma [QC#19656,DEL]
            if (invtyStock.equals(npzc101001Pmsg.scheduledOrdList.no(i).inbdOtbdNm.getValue())) {
                // calculate current available inventory
                curInvtyQty = curInvtyQty.add(npzc101001Pmsg.scheduledOrdList.no(i).curInvtyQty.getValue());

                // START 2017/07/27 S.Katsuma [QC#19656,DEL]
//              if (j == 0) {
                // END 2017/07/27 S.Katsuma [QC#19656,DEL]
                    ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, npzc101001Pmsg.scheduledOrdList.no(i).xxMdseCd);
                    // START 2017/07/27 S.Katsuma [QC#19656,ADD]
//                    ZYPEZDItemValueSetter.setValue(fMsg.invtyLocCd, cMsg.rtlWhCd.getValue() + cMsg.rtlSwhCd.getValue());
                    ZYPEZDItemValueSetter.setValue(fMsg.invtyLocCd, npzc101001Pmsg.scheduledOrdList.no(i).xxInvtyLocCd);
//                    ZYPEZDItemValueSetter.setValue(fMsg.etaEtdDt, npzc101001Pmsg.scheduledOrdList.no(i).etaEtdDt);
                    // END 2017/07/27 S.Katsuma [QC#19656,ADD]
                    ZYPEZDItemValueSetter.setValue(fMsg.inbdOtbdNm, npzc101001Pmsg.scheduledOrdList.no(i).inbdOtbdNm);
                    ZYPEZDItemValueSetter.setValue(fMsg.arTrxTpNm, npzc101001Pmsg.scheduledOrdList.no(i).arTrxTpNm);
                    ZYPEZDItemValueSetter.setValue(fMsg.origOrdNum, npzc101001Pmsg.scheduledOrdList.no(i).origOrdNum);
                    ZYPEZDItemValueSetter.setValue(fMsg.srcOrdLineNum, npzc101001Pmsg.scheduledOrdList.no(i).ordLineNum);
                    ZYPEZDItemValueSetter.setValue(fMsg.ordTpNm, npzc101001Pmsg.scheduledOrdList.no(i).ordTpNm);
                    ZYPEZDItemValueSetter.setValue(fMsg.ordDt, npzc101001Pmsg.scheduledOrdList.no(i).ordDt);
                    ZYPEZDItemValueSetter.setValue(fMsg.rqstRcvDt, npzc101001Pmsg.scheduledOrdList.no(i).rqstRcvDt);
                    fMsg.ordQty.clear();
                    ZYPEZDItemValueSetter.setValue(fMsg.supdFlg, cMsg.supdFlg.getValue());

                    // START 2017/07/27 S.Katsuma [QC#19656,ADD]
                    ZYPEZDItemValueSetter.setValue(fMsg.avalQty, npzc101001Pmsg.scheduledOrdList.no(i).curInvtyQty.getValue());
                    // END 2017/07/27 S.Katsuma [QC#19656,ADD]
                    j++;

                    // START 2017/07/27 S.Katsuma [QC#19656,ADD]
                    // write csv file
                    csvOutFile.write();
//                }
                 // END 2017/07/27 S.Katsuma [QC#19656,ADD]
            }
        }

        if (j == 0) {
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, cMsg.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.invtyLocCd, cMsg.rtlWhCd.getValue() + cMsg.rtlSwhCd.getValue());
            // START 2017/07/27 S.Katsuma [QC#19656,DEL]
//            ZYPEZDItemValueSetter.setValue(fMsg.etaEtdDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
            // END 2017/07/27 S.Katsuma [QC#19656,DEL]
            ZYPEZDItemValueSetter.setValue(fMsg.inbdOtbdNm, invtyStock);
            fMsg.arTrxTpNm.clear();
            fMsg.origOrdNum.clear();
            fMsg.srcOrdLineNum.clear();
            fMsg.ordTpNm.clear();
            fMsg.ordQty.clear();
            fMsg.ordDt.clear();
            fMsg.rqstRcvDt.clear();
            ZYPEZDItemValueSetter.setValue(fMsg.supdFlg, ZYPConstant.FLG_OFF_N);
            // START 2017/07/27 S.Katsuma [QC#19656,ADD]
            // set current available inventory for Stock line
            ZYPEZDItemValueSetter.setValue(fMsg.avalQty, curInvtyQty);
            // write csv file
            csvOutFile.write();
            // END 2017/07/27 S.Katsuma [QC#19656,ADD]
        }

        // add In-Bound/Out-Bound lines
        for (int i = 0; i < npzc101001Pmsg.scheduledOrdList.getValidCount(); i++) {
            if (invtyStock.equals(npzc101001Pmsg.scheduledOrdList.no(i).inbdOtbdNm.getValue())) {
                continue;
            } else {
                inbdQty = BigDecimal.ZERO;
                outbdQty = BigDecimal.ZERO;
                // download csv file 
                ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, npzc101001Pmsg.scheduledOrdList.no(i).xxMdseCd);
                // START 2017/07/27 S.Katsuma [QC#19656,ADD]
//                ZYPEZDItemValueSetter.setValue(fMsg.invtyLocCd, cMsg.rtlWhCd.getValue() + cMsg.rtlSwhCd.getValue());
                ZYPEZDItemValueSetter.setValue(fMsg.invtyLocCd, npzc101001Pmsg.scheduledOrdList.no(i).xxInvtyLocCd);
                // END 2017/07/27 S.Katsuma [QC#19656,ADD]
                ZYPEZDItemValueSetter.setValue(fMsg.etaEtdDt, npzc101001Pmsg.scheduledOrdList.no(i).etaEtdDt);
                ZYPEZDItemValueSetter.setValue(fMsg.inbdOtbdNm, npzc101001Pmsg.scheduledOrdList.no(i).inbdOtbdNm);
                ZYPEZDItemValueSetter.setValue(fMsg.arTrxTpNm, npzc101001Pmsg.scheduledOrdList.no(i).arTrxTpNm);
                ZYPEZDItemValueSetter.setValue(fMsg.origOrdNum, npzc101001Pmsg.scheduledOrdList.no(i).origOrdNum);
                ZYPEZDItemValueSetter.setValue(fMsg.srcOrdLineNum, npzc101001Pmsg.scheduledOrdList.no(i).ordLineNum);
                ZYPEZDItemValueSetter.setValue(fMsg.ordTpNm, npzc101001Pmsg.scheduledOrdList.no(i).ordTpNm);
                ZYPEZDItemValueSetter.setValue(fMsg.ordDt, npzc101001Pmsg.scheduledOrdList.no(i).ordDt);
                ZYPEZDItemValueSetter.setValue(fMsg.rqstRcvDt, npzc101001Pmsg.scheduledOrdList.no(i).rqstRcvDt);
                // START 2017/07/27 S.Katsuma [QC#19656,ADD]
                fMsg.supdFlg.clear();
                // END 2017/07/27 S.Katsuma [QC#19656,ADD]
                if (ZYPCommonFunc.hasValue(npzc101001Pmsg.scheduledOrdList.no(i).schdInbdQty)) {
                    ZYPEZDItemValueSetter.setValue(fMsg.ordQty, npzc101001Pmsg.scheduledOrdList.no(i).schdInbdQty);
                    inbdQty = npzc101001Pmsg.scheduledOrdList.no(i).schdInbdQty.getValue();
                } else if (ZYPCommonFunc.hasValue(npzc101001Pmsg.scheduledOrdList.no(i).schdOtbdQty)) {
                    ZYPEZDItemValueSetter.setValue(fMsg.ordQty, npzc101001Pmsg.scheduledOrdList.no(i).schdOtbdQty);
                    outbdQty = npzc101001Pmsg.scheduledOrdList.no(i).schdOtbdQty.getValue();
                }
                // set available quantity (Stock Qty + In-Bound Qty -
                // Out-Bound Qty)
                curInvtyQty = curInvtyQty.add(inbdQty.subtract(outbdQty));
                ZYPEZDItemValueSetter.setValue(fMsg.avalQty, curInvtyQty);
                // write csv file
                csvOutFile.write();
            }
        }
        */
        // END 2017/08/01 S.Katsuma [QC#19656,DEL]
        csvOutFile.close();
    }

    private void setLines(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg, NPZC101001PMsg npzc101001Pmsg) {
        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),DEL]
//        String invtyIn = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_INVTY_IN, cMsg.glblCmpyCd.getValue());
//        String invtyOut = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_INVTY_OUT, cMsg.glblCmpyCd.getValue());
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),DEL]
        String invtyStock = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_INVTY_STK, cMsg.glblCmpyCd.getValue());

        // sort MRP calculation API output list
        S21SortTarget sortTarget = new S21SortTarget(npzc101001Pmsg.scheduledOrdList, NPZC101001_BASE_CONTENTS);
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(DB_PARAM_INVTY_LOC_CD, SORT_BY_ASC);
        // QC#56544-1
        sortKey.add("curInvtyQty", "DESC");
        sortKey.add(DB_PARAM_ETA_ETD_DT, SORT_BY_ASC);
        sortKey.add(DB_PARAM_XX_MDSE_CD, SORT_BY_ASC);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, npzc101001Pmsg.scheduledOrdList.getValidCount());

        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        // Create not confirmed order and confirmed order line
        createSMsgB(cMsg, sMsg, npzc101001Pmsg, invtyStock);
        // Create only confirmed order line
        createSMsgC(sMsg, invtyStock);

        setSMsgA(sMsg);
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
    }

    private void setDummyStockLine(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg, int idx, String invtyLocCd, String invtyStock) {
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(idx).mdseCd_A0, cMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(idx).invtyLocCd_A0, invtyLocCd);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(idx).inbdOtbdNm_A0, invtyStock);
        sMsg.B.no(idx).ordDt_A0.clear();
        sMsg.B.no(idx).arTrxTpNm_A0.clear();
        sMsg.B.no(idx).origOrdNum_A0.clear();
        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
//        sMsg.A.no(idx).srcOrdLineNum_A0.clear();
        sMsg.B.no(idx).xxDplyOrdLineNum_A0.clear();
        sMsg.B.no(idx).ordHdrDplyStsCd_A0.clear();
        sMsg.B.no(idx).ordHdrDplyStsDescTxt_A0.clear();
        sMsg.B.no(idx).shpgStsCd_A0.clear();
        sMsg.B.no(idx).shpgStsNm_A0.clear();
        sMsg.B.no(idx).apvlStsCd_A0.clear();
        sMsg.B.no(idx).apvlStsNm_A0.clear();
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        sMsg.B.no(idx).ordTpDescTxt_A0.clear();
        sMsg.B.no(idx).ordQty_A0.clear();
        sMsg.B.no(idx).rqstRcvDt_A0.clear();
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(idx).supdFlg_A0, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(idx).avalQty_A0, new BigDecimal(0));
        sMsg.B.setValidCount(sMsg.B.getValidCount()+1);
    }

    private void setStockLine(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg, NPZC101001PMsg npzc101001Pmsg, int sMsgIdx, int pMsgIdx) {
        setCommonValueOfLine(cMsg, sMsg, npzc101001Pmsg, sMsgIdx, pMsgIdx);
        sMsg.B.no(sMsgIdx).ordQty_A0.clear();
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).supdFlg_A0, cMsg.supdFlg.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).avalQty_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).curInvtyQty.getValue());
    }

    private void setInbdOutbdLine(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg, NPZC101001PMsg npzc101001Pmsg, int sMsgIdx, int pMsgIdx) {
        setCommonValueOfLine(cMsg, sMsg, npzc101001Pmsg, sMsgIdx, pMsgIdx);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).etaEtdDt_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).etaEtdDt);
        if (ZYPCommonFunc.hasValue(npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).schdInbdQty)) {
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).ordQty_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).schdInbdQty);
        } else if (ZYPCommonFunc.hasValue(npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).schdOtbdQty)) {
            ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).ordQty_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).schdOtbdQty);
        }
    }

    private void setCommonValueOfLine(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg, NPZC101001PMsg npzc101001Pmsg, int sMsgIdx, int pMsgIdx) {
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).mdseCd_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).xxMdseCd);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).invtyLocCd_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).xxInvtyLocCd);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).rtlWhCd_A0, cMsg.rtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).rtlSwhCd_A0, cMsg.rtlSwhCd.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).inbdOtbdNm_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).inbdOtbdNm);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).ordDt_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).ordDt);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).arTrxTpNm_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).arTrxTpNm);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).origOrdNum_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).origOrdNum);
        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
//        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgIdx).srcOrdLineNum_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).ordLineNum);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).xxDplyOrdLineNum_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).xxDplyOrdLineNum);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).ordHdrDplyStsCd_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).ordHdrDplyStsCd);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).ordHdrDplyStsDescTxt_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).ordHdrDplyStsDescTxt);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).shpgStsCd_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).shpgStsCd);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).shpgStsNm_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).shpgStsNm);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).apvlStsCd_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).apvlStsCd);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).apvlStsNm_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).apvlStsNm);
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).ordTpDescTxt_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).ordTpDescTxt);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).rqstRcvDt_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).rqstRcvDt);
        // START 2023/04/17 G.Quan [QC#61206, ADD]
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).xxRecHistCratTs_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).xxRecHistCratTs_A0);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).xxRecHistCratByNm_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).xxRecHistCratByNm_A0);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).xxRecHistUpdTs_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).xxRecHistUpdTs_A0);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).xxRecHistUpdByNm_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).xxRecHistUpdByNm_A0);
        ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsgIdx).xxRecHistTblNm_A0, npzc101001Pmsg.scheduledOrdList.no(pMsgIdx).xxRecHistTblNm_A0);
        // END 2023/04/17 G.Quan [QC#61206, ADD]
        // set valid count of sMsg
        sMsg.B.setValidCount(sMsg.B.getValidCount()+1);
    }

    private void doProcess_NPAL1130Scrn00_Prev(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg) {

        NPAL1130CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.A);
        NPAL1130CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1);
    }

    private void doProcess_NPAL1130Scrn00_Next(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg) {

        NPAL1130CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.A);
        NPAL1130CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowToNum.getValueInt());
    }

    private void doProcess_NPAL1130Scrn00_SearchMdseName(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg) {

        EZDMsg.copy(cMsg, null, sMsg, null);
        String mdseName = NPAL1130CommonLogic.getMdseName(cMsg);

        if (hasValue(mdseName)) {
            cMsg.mdseDescShortTxt.setValue(mdseName);
        } else {
            cMsg.mdseDescShortTxt.clear();
            cMsg.mdseCd.setErrorInfo(1, "NZZM0010E", new String[] {cMsg.mdseCd.getValue() });
        }
    }

    private void doProcess_NPAL1130Scrn00_SearchLocationName(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg) {

        EZDMsg.copy(cMsg, null, sMsg, null);
        String rtlWhNm;
        rtlWhNm = NPAL1130CommonLogic.getWhName(cMsg);

        if (ZYPCommonFunc.hasValue(rtlWhNm)) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, rtlWhNm);
        } else {
            cMsg.rtlWhNm.clear();
            cMsg.rtlWhCd.setErrorInfo(1, "NZZM0010E", new String[] {cMsg.rtlWhCd.getValue() });
            return;
        }
    }
    /**
     * doProcess_NPAL1130Scrn00_CMN_Download
     * @param cMsg NPAL1130CMsg
     * @param sMsg NPAL1130SMsg
     */
    private void doProcess_NPAL1130Scrn00_CMN_Download(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg) {

        // QC#14194
        //clearMsg(cMsg, sMsg);

        checkSearch(cMsg, sMsg, NPAL11130_CMN_DOWNLOAD);
    }

    // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
    /**
     * doProcess_NPAL1130Scrn00_OnChange_DplyConfOrdOnly
     * @param cMsg NPAL1130CMsg
     * @param sMsg NPAL1130SMsg
     */
    private void doProcess_NPAL1130Scrn00_OnChange_DplyConfOrdOnly(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg) {
        cMsg.xxPageShowFromNum.setValue(BigDecimal.ZERO);
        cMsg.xxPageShowToNum.setValue(BigDecimal.ZERO);
        cMsg.xxPageShowOfNum.setValue(BigDecimal.ZERO);
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPEZDItemValueSetter.setValue(sMsg.xxChkBox_CO, cMsg.xxChkBox_CO);

        // QC#24814 Update.
        if (!ZYPCommonFunc.hasValue(sMsg.xxChkBox_CO)) {
            ZYPEZDItemValueSetter.setValue(sMsg.xxChkBox_CO, ZYPConstant.FLG_OFF_N);
        }

        setSMsgA(sMsg);
        // Calc available Qty
        if (sMsg.A.getValidCount() > 0) {
            calcAvailableQty(sMsg);
        }

        // set valid count of cMsg
        if (sMsg.A.getValidCount() <= cMsg.A.length()) {
            cMsg.A.setValidCount(sMsg.A.getValidCount());
        } else {
            cMsg.A.setValidCount(cMsg.A.length());
        }

        // QC#27636 add start
        // QC#29894 Update
        String invtyOut = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_INVTY_OUT, getGlobalCompanyCode());

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (sMsg.A.no(i).inbdOtbdNm_A0.getValue().equals(invtyOut)) {
                BigDecimal ordQty = sMsg.A.no(i).ordQty_A0.getValue();
                ordQty = ordQty.negate();
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ordQty_A0, ordQty);
            }
        }
        // QC#27636 add end
        // QC#29894 Update
        EZDMsg.copy(sMsg.A, null, cMsg.A, null);

        if (cMsg.A.getValidCount() > 0) {
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        } else {
            cMsg.setMessageInfo("NPAM1609I");
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowOfNum.setValue(0);
            cMsg.xxPageShowToNum.setValue(0);
        }
    }

    /**
     * createSMsgB
     * @param cMsg
     * @param sMsg
     * @param npzc101001Pmsg
     * @param invtyStock
     */
    private void createSMsgB(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg, NPZC101001PMsg npzc101001Pmsg, String invtyStock) {
        int stkLine = 0;
        boolean stkFlag = false;
        String lastInvtyLocCd = "";
        for (int i = 0; i < npzc101001Pmsg.scheduledOrdList.getValidCount(); i++) {
            String invtyLocCd = npzc101001Pmsg.scheduledOrdList.no(i).xxInvtyLocCd.getValue();
            String inbdOtbdNm = npzc101001Pmsg.scheduledOrdList.no(i).inbdOtbdNm.getValue();

            if (invtyLocCd != null && invtyLocCd.equals(lastInvtyLocCd)) {
                if (inbdOtbdNm != null && inbdOtbdNm.equals(invtyStock)) {
                    // START 2017/08/30 S.Katsuma [QC#20806,ADD]
                    if (!stkFlag) {
                        // add Stock lines
                        setStockLine(cMsg, sMsg, npzc101001Pmsg, stkLine, i);
                        stkFlag = true;
                    } else {
                        // 8 digit item number and duplicated stock line
                        // QC#26534 Update.
                        if(NPAL1130CommonLogic.chkOrdTakeMdseCd(cMsg.glblCmpyCd.getValue(), cMsg.mdseCd.getValue(), sMsg.B.no(stkLine).mdseCd_A0.getValue() )){
                            sMsg.B.no(stkLine).mdseCd_A0.setValue(cMsg.mdseCd.getValue());
                            
                            BigDecimal curInvtyQty = BigDecimal.ZERO;
                            curInvtyQty = curInvtyQty.add(sMsg.B.no(stkLine).avalQty_A0.getValue()).add(npzc101001Pmsg.scheduledOrdList.no(i).curInvtyQty.getValue());
                            sMsg.B.no(stkLine).avalQty_A0.setValue(curInvtyQty);
                        
                        } else {
                            // New Item Stock Record.
                            setStockLine(cMsg, sMsg, npzc101001Pmsg, sMsg.B.getValidCount(), i);
                            stkFlag = true;
                        }
                    }
                    // END 2017/08/30 S.Katsuma [QC#20806,ADD]
                } else {
                    // add inbound/outbound lines
                    if (!stkFlag) {
//                        setInbdOutbdLine(cMsg, sMsg, npzc101001Pmsg, i + 1, i);
                        setInbdOutbdLine(cMsg, sMsg, npzc101001Pmsg, sMsg.B.getValidCount() + 1, i);
                    } else {
//                        setInbdOutbdLine(cMsg, sMsg, npzc101001Pmsg, i, i);
                        setInbdOutbdLine(cMsg, sMsg, npzc101001Pmsg, sMsg.B.getValidCount(), i);
                    }
                }

                // Handle Last Record
                if ((i+1) == npzc101001Pmsg.scheduledOrdList.getValidCount()) {
                    if (!stkFlag) {
                        // Dummy stock line(invtyLocCd)
                        setDummyStockLine(cMsg, sMsg, stkLine, invtyLocCd, invtyStock);
                    }
                }
            } else {
                if (i != 0 ) {
                    if (!stkFlag) {
                        // Dummy stock line(lastInvtyLocCd)
                        setDummyStockLine(cMsg, sMsg, stkLine, lastInvtyLocCd, invtyStock);
                        stkFlag = true;
                    }
                } else {
                    // Handle first line of npzc101001Pmsg.scheduledOrdList(i = 0)
                    if (npzc101001Pmsg.scheduledOrdList.getValidCount() == 1) {
                        if (!stkFlag) {
                            if (inbdOtbdNm != null && !inbdOtbdNm.equals(invtyStock)) {
                                // Dummy stock line
                                setDummyStockLine(cMsg, sMsg, stkLine, invtyLocCd, invtyStock);
                                stkFlag = true;
                            }
                        }
                    }
                }

                stkLine = sMsg.B.getValidCount();
                if (inbdOtbdNm != null && inbdOtbdNm.equals(invtyStock)) {
                    // add Stock lines
                    setStockLine(cMsg, sMsg, npzc101001Pmsg, sMsg.B.getValidCount(), i);
                    stkFlag = true;
                } else {
                    // START 2017/08/30 S.Katsuma [QC#20806,ADD]
                    if (npzc101001Pmsg.scheduledOrdList.getValidCount() != 1) {
                        // add inbound/outbound lines
//                      setInbdOutbdLine(cMsg, sMsg, npzc101001Pmsg, i + 1, i);
                        setInbdOutbdLine(cMsg, sMsg, npzc101001Pmsg, sMsg.B.getValidCount() + 1, i);
                    } else {
                        setInbdOutbdLine(cMsg, sMsg, npzc101001Pmsg, sMsg.B.getValidCount(), i);
                    }
                    // END 2017/08/30 S.Katsuma [QC#20806,ADD]
                    stkFlag = false;
                }

                // QC#24581 Add.
                // Handle Last Record
                if ((i + 1) == npzc101001Pmsg.scheduledOrdList.getValidCount() //
                        && ZYPCommonFunc.hasValue(lastInvtyLocCd) //
                        && !invtyLocCd.equals(lastInvtyLocCd)) {
                    if (!stkFlag) {
                        // Dummy stock line(invtyLocCd)
                        setDummyStockLine(cMsg, sMsg, stkLine, invtyLocCd, invtyStock);
                    }
                }

                lastInvtyLocCd = invtyLocCd;
            }
        }
    }

    /**
     * createSMsgC
     * @param cMsg
     * @param sMsg
     * @param npzc101001Pmsg
     * @param invtyStock
     */
    private void createSMsgC(NPAL1130SMsg sMsg, String invtyStock) {
        int sMsgCIdx = 0;
        String arTrxTpNmPo = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_ORDER_PO, sMsg.glblCmpyCd.getValue());
        String arTrxTpNmPr = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_ORDER_POREQ, sMsg.glblCmpyCd.getValue());
        String arTrxTpNmInvReq = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_ORDER_INVREQ, sMsg.glblCmpyCd.getValue());
        String arTrxTpNmWo = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_ORDER_WO, sMsg.glblCmpyCd.getValue());
        String arTrxTpNmCpo = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_ORDER_CPO, sMsg.glblCmpyCd.getValue());
        String arTrxTpNmTechReq = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_ORDER_TECHREQ, sMsg.glblCmpyCd.getValue());
        String arTrxTpNmReman = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_ORDER_REMAN, sMsg.glblCmpyCd.getValue());
        for(int i = 0; i < sMsg.B.getValidCount(); i++ ) {
            String inbdOtbdNm = sMsg.B.no(i).inbdOtbdNm_A0.getValue();
            if (inbdOtbdNm != null && inbdOtbdNm.equals(invtyStock)) {
                EZDMsg.copy(sMsg.B.no(i), null, sMsg.C.no(sMsgCIdx++), null);
            } else {
                String arTrxTpNm = sMsg.B.no(i).arTrxTpNm_A0.getValue();
                String xxStsCd10Txt = sMsg.B.no(i).ordHdrDplyStsCd_A0.getValue();
                String apvlStsCd = sMsg.B.no(i).apvlStsCd_A0.getValue();
                if (arTrxTpNm.equals(arTrxTpNmPo)) {
                    // QC#24814 Update.
                    EZDMsg.copy(sMsg.B.no(i), null, sMsg.C.no(sMsgCIdx++), null);

//                    if (ZYPCommonFunc.hasValue(apvlStsCd)) {
//                        if (PO_APVL_STS.APPROVED.equals(apvlStsCd) || PO_APVL_STS.PRE_APPROVED.equals(apvlStsCd)) {
//                            EZDMsg.copy(sMsg.B.no(i), null, sMsg.C.no(sMsgCIdx++), null);
//                        }
//                    }
                } else if (arTrxTpNm.equals(arTrxTpNmPr)) {
                    //  QC#24814 Update.
                    EZDMsg.copy(sMsg.B.no(i), null, sMsg.C.no(sMsgCIdx++), null);

//                    if (ZYPCommonFunc.hasValue(apvlStsCd)) {
//                        if (PRCH_REQ_APVL_STS.APPROVED.equals(apvlStsCd) || PRCH_REQ_APVL_STS.PRE_APPROVED.equals(apvlStsCd)) {
//                            EZDMsg.copy(sMsg.B.no(i), null, sMsg.C.no(sMsgCIdx++), null);
//                        }
//                    }
                } else if (arTrxTpNm.equals(arTrxTpNmInvReq)) {
                    // QC#24814 Update.
                    EZDMsg.copy(sMsg.B.no(i), null, sMsg.C.no(sMsgCIdx++), null);

//                    if (ZYPCommonFunc.hasValue(apvlStsCd)) {
//                        if (PRCH_REQ_APVL_STS.APPROVED.equals(apvlStsCd) || PRCH_REQ_APVL_STS.PRE_APPROVED.equals(apvlStsCd)) {
//                            EZDMsg.copy(sMsg.B.no(i), null, sMsg.C.no(sMsgCIdx++), null);
//                        }
//                    }
                } else if (arTrxTpNm.equals(arTrxTpNmWo)) {
                    // QC#24814 Update.
                    EZDMsg.copy(sMsg.B.no(i), null, sMsg.C.no(sMsgCIdx++), null);

                } else if (arTrxTpNm.equals(arTrxTpNmCpo)) {
                    if (ZYPCommonFunc.hasValue(xxStsCd10Txt)) {
                        if (ORD_HDR_DPLY_STS.BOOKED.equals(xxStsCd10Txt)) {
                            EZDMsg.copy(sMsg.B.no(i), null, sMsg.C.no(sMsgCIdx++), null);
                        }
                    }
                } else if (arTrxTpNm.equals(arTrxTpNmTechReq)) {
                    // QC#24814 Update.
                    EZDMsg.copy(sMsg.B.no(i), null, sMsg.C.no(sMsgCIdx++), null);

//                    if (ZYPCommonFunc.hasValue(apvlStsCd)) {
//                        if (PRCH_REQ_APVL_STS.APPROVED.equals(apvlStsCd) || PRCH_REQ_APVL_STS.PRE_APPROVED.equals(apvlStsCd)) {
//                            EZDMsg.copy(sMsg.B.no(i), null, sMsg.C.no(sMsgCIdx++), null);
//                        }
//                    }
                } else if (arTrxTpNm.equals(arTrxTpNmReman)) {
                    EZDMsg.copy(sMsg.B.no(i), null, sMsg.C.no(sMsgCIdx++), null);
                }
            }
        }
        sMsg.C.setValidCount(sMsgCIdx);
    }

    /**
     * calcAvailableQty
     * @param sMsg
     */
    private void calcAvailableQty(NPAL1130SMsg sMsg) {
        String invtyIn = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_INVTY_IN, sMsg.glblCmpyCd.getValue());
        String invtyOut = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_INVTY_OUT, sMsg.glblCmpyCd.getValue());
        String invtyStock = ZYPCodeDataUtil.getVarCharConstValue(NPZC1010_INVTY_STK, sMsg.glblCmpyCd.getValue());
        BigDecimal curInvtyQty = BigDecimal.ZERO;
        // QC#56544-1 Start
        BigDecimal totStockQty = BigDecimal.ZERO;
        boolean isStockLine = false;
        String preInvtyLocCd = "";
        // Calc Available Qty
        for (int j = 0; j < sMsg.A.getValidCount(); j++) {
            if (sMsg.A.no(j).inbdOtbdNm_A0.getValue().equals(invtyStock)) {
                curInvtyQty = sMsg.A.no(j).avalQty_A0.getValue();
                if (ZYPCommonFunc.hasValue(preInvtyLocCd) && preInvtyLocCd.equals(sMsg.A.no(j).invtyLocCd_A0.getValue())) {
                    totStockQty = totStockQty.add(curInvtyQty);
                } else {
                    totStockQty = BigDecimal.ZERO;
                    totStockQty = totStockQty.add(curInvtyQty);
                }
                isStockLine = true;
            } else if (sMsg.A.no(j).inbdOtbdNm_A0.getValue().equals(invtyIn)) {
                totStockQty = totStockQty.add(sMsg.A.no(j).ordQty_A0.getValue());
                isStockLine = false;
            } else if (sMsg.A.no(j).inbdOtbdNm_A0.getValue().equals(invtyOut)) {
                totStockQty =  totStockQty.subtract(sMsg.A.no(j).ordQty_A0.getValue());
                isStockLine = false;
            }
            if (isStockLine) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).avalQty_A0, curInvtyQty);
            } else {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).avalQty_A0, totStockQty);
            }
            preInvtyLocCd = sMsg.A.no(j).invtyLocCd_A0.getValue();
        }
        // QC#56544-1 End
    }

    /**
     * changeSMsgA
     * @param sMsg
     */
    private void setSMsgA(NPAL1130SMsg sMsg) {
        // Set confirmed order and confirmed order line or only confirmed order line
        // QC#24814 Update.
        if (ZYPConstant.FLG_OFF_N.equals(sMsg.xxChkBox_CO.getValue())) {
            for(int i = 0; i < sMsg.C.getValidCount(); i++ ) {
                EZDMsg.copy(sMsg.C.no(i), null, sMsg.A.no(i), null);
            }
            sMsg.A.setValidCount(sMsg.C.getValidCount());
        } else {
            for(int i = 0; i < sMsg.B.getValidCount(); i++ ) {
                EZDMsg.copy(sMsg.B.no(i), null, sMsg.A.no(i), null);
            }
            sMsg.A.setValidCount(sMsg.B.getValidCount());
        }
    }
    // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]


    /**
     * Add QC#21229 Attachment
     * @param cMsg NPAL1130CMsg
     */
    private void doProcess_NPAL1130_Attachment(NPAL1130CMsg cMsg) {

        String mdseCd = cMsg.A.no(cMsg.xxNum.getValueInt()).mdseCd_A0.getValue();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_MDSE_CD, mdseCd);

        S21SsmEZDResult result = NPAL1130Query.getInstance().getMdseCd(ssmParam);

        if (result.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(cMsg.P.no(0).xxPopPrm, (String) result.getResultObject());
            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.P.no(0).xxPopPrm, mdseCd);
    }
}
