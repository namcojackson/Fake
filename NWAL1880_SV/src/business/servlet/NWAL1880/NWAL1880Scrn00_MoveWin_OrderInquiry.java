/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1880;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NWAL1880.constant.NWAL1880Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import business.db.CPO_SRC_TPTMsg;
import business.servlet.NWAL1880.constant.NWAL1880Constant.DPLY_MODE;
import business.servlet.NWAL1880.constant.NWAL1880Constant.HDR_STS;
import business.servlet.NWAL1880.constant.NWAL1880Constant.RSLT_MODE;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/07/27   Fujitsu         K.Sato          Update          QC#11581
 * 2016/11/22   Fujitsu         K.Sato          Update          QC#15760
 * 2017/01/25   Fujitsu         W.Honda         Update          QC#16992
 * 2018/01/15   Fujitsu         A.Kosai         Update          QC#22330
 *</pre>
 */
public class NWAL1880Scrn00_MoveWin_OrderInquiry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1880BMsg scrnMsg = (NWAL1880BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1880BMsg scrnMsg = (NWAL1880BMsg) bMsg;

        //NWAL1880CMsg bizMsg = new NWAL1880CMsg();
        //bizMsg.setBusinessID("NWAL1880");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1880BMsg scrnMsg = (NWAL1880BMsg) bMsg;

        int rowNum = getButtonSelectNumber();

        List<Object> param = null;
        param = createNWAL1570Parameters(scrnMsg, rowNum);

        setArgForSubScreen(param.toArray(new Object[0]));
    }

    private List<Object> createNWAL1570Parameters(NWAL1880BMsg scrnMsg, int rowNum) {

        List<Object> param = new ArrayList<Object>();
        NWAL1880_ABMsg recordMsg = scrnMsg.A.no(rowNum);

        // [0]Real Time Inquiry
        param.add(ZYPConstant.FLG_OFF_N);
        // [1]Result Mode
        param.add(RSLT_MODE.ORDER_INQUIRY.getRsltModeCd());
        // [2]Display Mode
        param.add(DPLY_MODE.LINE.getDplyModeCd());
        // [3]Display By Item Name
        param.add("SRC_REF_OR_CPO_ORD_NUM");
        // [4]Header Status
        List<String> hdrStsList = new ArrayList<String>();
        if (ORD_CNT_ENT.equals(scrnMsg.xxScrEventNm.getValue())) {
            hdrStsList.add(HDR_STS.ENT.getHdrStsNm());
        } else if (ORD_CNT_CR_HLD.equals(scrnMsg.xxScrEventNm.getValue())) {
            hdrStsList.add(HDR_STS.CR_HLD.getHdrStsNm());
        } else if (ORD_CNT_PRFT_HLD.equals(scrnMsg.xxScrEventNm.getValue())) {
            hdrStsList.add(HDR_STS.PRFT.getHdrStsNm());
        } else if (ORD_CNT_VLD_HLD.equals(scrnMsg.xxScrEventNm.getValue())) {
            hdrStsList.add(HDR_STS.VLD.getHdrStsNm());
        } else if (ORD_CNT_ALL_OTH.equals(scrnMsg.xxScrEventNm.getValue())) {
            hdrStsList.add(HDR_STS.DI_HLD.getHdrStsNm());
            hdrStsList.add(HDR_STS.SPLY_ABUSE.getHdrStsNm());
            hdrStsList.add(HDR_STS.PEND_RE_SUBMT.getHdrStsNm());
        } else if (ORD_CNT_BOOK.equals(scrnMsg.xxScrEventNm.getValue())) {
            hdrStsList.add(HDR_STS.BOOK.getHdrStsNm());
        } else if (ORD_AGING_TODAY.equals(scrnMsg.xxScrEventNm.getValue())
                || ORD_AGING_1_2.equals(scrnMsg.xxScrEventNm.getValue())
                || ORD_AGING_3_10.equals(scrnMsg.xxScrEventNm.getValue())
                || ORD_AGING_11_30.equals(scrnMsg.xxScrEventNm.getValue())
                || ORD_AGING_31_50.equals(scrnMsg.xxScrEventNm.getValue())
                || ORD_AGING_OVER_50.equals(scrnMsg.xxScrEventNm.getValue())
                || ORD_AGING_TOT.equals(scrnMsg.xxScrEventNm.getValue())) {
            hdrStsList.add(HDR_STS.ENT.getHdrStsNm());
            hdrStsList.add(HDR_STS.DI_HLD.getHdrStsNm());
            hdrStsList.add(HDR_STS.VLD.getHdrStsNm());
            hdrStsList.add(HDR_STS.SPLY_ABUSE.getHdrStsNm());
            hdrStsList.add(HDR_STS.PRFT.getHdrStsNm());
            hdrStsList.add(HDR_STS.CR_HLD.getHdrStsNm());
            hdrStsList.add(HDR_STS.PEND_RE_SUBMT.getHdrStsNm());
            hdrStsList.add(HDR_STS.BOOK.getHdrStsNm());
        }
        param.add(hdrStsList);
        // [5]Line Status
        List<String> lineStsList = new ArrayList<String>();
        if (ORD_INV_TODAY.equals(scrnMsg.xxScrEventNm.getValue())
                || ORD_INV_CUR_MTH.equals(scrnMsg.xxScrEventNm.getValue())
                || ORD_INV_TOT.equals(scrnMsg.xxScrEventNm.getValue())) {
            // QC#11581 MOD Start
//            lineStsList.add(LINE_STS.INV.getLineStsNm());
            lineStsList.add(LINE_STS.SHIP_CLO.getLineStsNm());
            lineStsList.add(LINE_STS.CLO_LOAN_RTRN.getLineStsNm());
            lineStsList.add(LINE_STS.CLO_LOAN_SOLD.getLineStsNm());
            // QC#11581 MOD End
        } else if (BILL_HLD_CNT.equals(scrnMsg.xxScrEventNm.getValue())
                || BILL_HLD_AMT.equals(scrnMsg.xxScrEventNm.getValue())) {
            lineStsList.add(LINE_STS.BLLG_HLD.getLineStsNm());
//        } else if (ORD_AGING_TODAY.equals(scrnMsg.xxScrEventNm.getValue())
//                || ORD_AGING_1_2.equals(scrnMsg.xxScrEventNm.getValue())
//                || ORD_AGING_3_10.equals(scrnMsg.xxScrEventNm.getValue())
//                || ORD_AGING_11_30.equals(scrnMsg.xxScrEventNm.getValue())
//                || ORD_AGING_31_50.equals(scrnMsg.xxScrEventNm.getValue())
//                || ORD_AGING_OVER_50.equals(scrnMsg.xxScrEventNm.getValue())
//                || ORD_AGING_TOT.equals(scrnMsg.xxScrEventNm.getValue())) {
//            lineStsList.add(LINE_STS.ENT.getLineStsNm());
//            lineStsList.add(LINE_STS.BOOK.getLineStsNm());
//            lineStsList.add(LINE_STS.SO_CANC.getLineStsNm());
//            lineStsList.add(LINE_STS.PO_CANC.getLineStsNm());
//            lineStsList.add(LINE_STS.PEND_ALLOC.getLineStsNm());
//            lineStsList.add(LINE_STS.BO.getLineStsNm());
//            lineStsList.add(LINE_STS.PEND_PICK.getLineStsNm());
//            lineStsList.add(LINE_STS.DELY_TO_SHOP.getLineStsNm());
//            lineStsList.add(LINE_STS.IN_SHOP_CONFIG.getLineStsNm());
//            lineStsList.add(LINE_STS.PEND_SHIP.getLineStsNm());
//            lineStsList.add(LINE_STS.SHIP.getLineStsNm());
//            lineStsList.add(LINE_STS.PEND_DELY_CONF.getLineStsNm());
//            lineStsList.add(LINE_STS.PEND_ISTL.getLineStsNm());
//            lineStsList.add(LINE_STS.ON_LOAN.getLineStsNm());
//            lineStsList.add(LINE_STS.WAIT_RCPT.getLineStsNm());
//            lineStsList.add(LINE_STS.PEND_INV.getLineStsNm());
//            lineStsList.add(LINE_STS.BLLG_HLD.getLineStsNm());
//            lineStsList.add(LINE_STS.SHIP_CLO.getLineStsNm());
//            lineStsList.add(LINE_STS.INV.getLineStsNm());
        }
        param.add(lineStsList);
        // [6]Return Line Status
        List<String> rtrnLineStsList = new ArrayList<String>();
        if (ORD_INV_TODAY.equals(scrnMsg.xxScrEventNm.getValue())
                || ORD_INV_CUR_MTH.equals(scrnMsg.xxScrEventNm.getValue())
                || ORD_INV_TOT.equals(scrnMsg.xxScrEventNm.getValue())) {
            // QC#11581 MOD Start
//            rtrnLineStsList.add(RTRN_LINE_STS.INV.getRtrnLineStsNm());
            rtrnLineStsList.add(RTRN_LINE_STS.CLO.getRtrnLineStsNm());
            // QC#11581 MOD End
        } else if (BILL_HLD_CNT.equals(scrnMsg.xxScrEventNm.getValue())
                || BILL_HLD_AMT.equals(scrnMsg.xxScrEventNm.getValue())) {
            rtrnLineStsList.add(RTRN_LINE_STS.BLLG_HLD.getRtrnLineStsNm());
//        } else if (ORD_AGING_TODAY.equals(scrnMsg.xxScrEventNm.getValue())
//                || ORD_AGING_1_2.equals(scrnMsg.xxScrEventNm.getValue())
//                || ORD_AGING_3_10.equals(scrnMsg.xxScrEventNm.getValue())
//                || ORD_AGING_11_30.equals(scrnMsg.xxScrEventNm.getValue())
//                || ORD_AGING_31_50.equals(scrnMsg.xxScrEventNm.getValue())
//                || ORD_AGING_OVER_50.equals(scrnMsg.xxScrEventNm.getValue())
//                || ORD_AGING_TOT.equals(scrnMsg.xxScrEventNm.getValue())) {
//            rtrnLineStsList.add(RTRN_LINE_STS.ENT.getRtrnLineStsNm());
//            rtrnLineStsList.add(RTRN_LINE_STS.BOOK.getRtrnLineStsNm());
//            rtrnLineStsList.add(RTRN_LINE_STS.PEND_RTRN.getRtrnLineStsNm());
//            rtrnLineStsList.add(RTRN_LINE_STS.PEND_INSP.getRtrnLineStsNm());
//            rtrnLineStsList.add(RTRN_LINE_STS.RWS_CANC.getRtrnLineStsNm());
//            rtrnLineStsList.add(RTRN_LINE_STS.PRTL_RCV.getRtrnLineStsNm());
//            rtrnLineStsList.add(RTRN_LINE_STS.PEND_INV.getRtrnLineStsNm());
//            rtrnLineStsList.add(RTRN_LINE_STS.BLLG_HLD.getRtrnLineStsNm());
//            rtrnLineStsList.add(RTRN_LINE_STS.INV.getRtrnLineStsNm());
        }
        param.add(rtrnLineStsList);
        // [7]Invoice Date From
        String invFromDt = null;
        if (ORD_INV_TODAY.equals(scrnMsg.xxScrEventNm.getValue())) {
            invFromDt = scrnMsg.slsDt.getValue();
        } else if (ORD_INV_CUR_MTH.equals(scrnMsg.xxScrEventNm.getValue())
                || ORD_INV_TOT.equals(scrnMsg.xxScrEventNm.getValue())) {
            invFromDt = getDate(scrnMsg.slsDt.getValue(), true, false, false, 0);
        }
        param.add(invFromDt);
        // [8]Invoice Date To
        String invToDt = null;
        if (ORD_INV_TODAY.equals(scrnMsg.xxScrEventNm.getValue())) {
            invToDt = scrnMsg.slsDt.getValue();
        } else if (ORD_INV_CUR_MTH.equals(scrnMsg.xxScrEventNm.getValue())) {
            invToDt = getDate(scrnMsg.slsDt.getValue(), false, false, true, -1);
        } else if (ORD_INV_TOT.equals(scrnMsg.xxScrEventNm.getValue())) {
            invToDt = scrnMsg.slsDt.getValue();
        }
        param.add(invToDt);
        // [9]Aging
        String ordAgingBcktDescTxt = null;
        if (ORD_AGING_TODAY.equals(scrnMsg.xxScrEventNm.getValue())) {
            ordAgingBcktDescTxt = ORD_AGING_BCKT_DESC_TXT_TODAY;
        } else if (ORD_AGING_1_2.equals(scrnMsg.xxScrEventNm.getValue())) {
            ordAgingBcktDescTxt = ORD_AGING_BCKT_DESC_TXT_1_2;
        } else if (ORD_AGING_3_10.equals(scrnMsg.xxScrEventNm.getValue())) {
            ordAgingBcktDescTxt = ORD_AGING_BCKT_DESC_TXT_3_10;
        } else if (ORD_AGING_11_30.equals(scrnMsg.xxScrEventNm.getValue())) {
            ordAgingBcktDescTxt = ORD_AGING_BCKT_DESC_TXT_11_30;
        } else if (ORD_AGING_31_50.equals(scrnMsg.xxScrEventNm.getValue())) {
            ordAgingBcktDescTxt = ORD_AGING_BCKT_DESC_TXT_31_50;
        } else if (ORD_AGING_OVER_50.equals(scrnMsg.xxScrEventNm.getValue())) {
            ordAgingBcktDescTxt = ORD_AGING_BCKT_DESC_TXT_OVER_50;
        } else if (ORD_AGING_TOT.equals(scrnMsg.xxScrEventNm.getValue())) {
            ordAgingBcktDescTxt = S21StringUtil.concatStrings(ORD_AGING_BCKT_DESC_TXT_TODAY, scrnMsg.xxSplCharTxt.getValue()
                    , ORD_AGING_BCKT_DESC_TXT_1_2, scrnMsg.xxSplCharTxt.getValue()
                    , ORD_AGING_BCKT_DESC_TXT_3_10, scrnMsg.xxSplCharTxt.getValue()
                    , ORD_AGING_BCKT_DESC_TXT_11_30, scrnMsg.xxSplCharTxt.getValue()
                    , ORD_AGING_BCKT_DESC_TXT_31_50, scrnMsg.xxSplCharTxt.getValue()
                    , ORD_AGING_BCKT_DESC_TXT_OVER_50);
        }
        param.add(ordAgingBcktDescTxt);
        // [10]Order Source
        String cpoSrcTpDescTxt = null;
        if (ORD_SRC_RVW_DEAL_CONFIG.equals(scrnMsg.xxScrEventNm.getValue())
                || ORD_SRC_ACPT_DEAL_CONFIG.equals(scrnMsg.xxScrEventNm.getValue())) {
            cpoSrcTpDescTxt = getCpoSrcTpDescTxt(CPO_SRC_TP.DEAL_CONFIGURATOR);
        } else if (ORD_SRC_RVW_SOM.equals(scrnMsg.xxScrEventNm.getValue())
                || ORD_SRC_ACPT_SOM.equals(scrnMsg.xxScrEventNm.getValue())) {
            cpoSrcTpDescTxt = getCpoSrcTpDescTxt(CPO_SRC_TP.SOM);
        } else if (CR_REBIL_TOTAY.equals(scrnMsg.xxScrEventNm.getValue())
                || CR_REBIL_CUR_MTH.equals(scrnMsg.xxScrEventNm.getValue())
                || CR_REBIL_TOT.equals(scrnMsg.xxScrEventNm.getValue())) {
            // 2018/01/15 S21_NA#22330 Mod Start
            //cpoSrcTpDescTxt = getCpoSrcTpDescTxt(CPO_SRC_TP.CREDIT_AND_REBILL_ENTRY);
            cpoSrcTpDescTxt = S21StringUtil.concatStrings(getCpoSrcTpDescTxt(CPO_SRC_TP.CREDIT), scrnMsg.xxSplCharTxt.getValue()
                    , getCpoSrcTpDescTxt(CPO_SRC_TP.REBILL));
            // 2018/01/15 S21_NA#22330 Mod End
        }
        param.add(cpoSrcTpDescTxt);
        // [11]Include Pending Import Order Flag
        String inclPendImptOrdFlg = null;
        if (ORD_SRC_RVW_DEAL_CONFIG.equals(scrnMsg.xxScrEventNm.getValue())
                || ORD_SRC_RVW_SOM.equals(scrnMsg.xxScrEventNm.getValue())) {
            inclPendImptOrdFlg = ZYPConstant.FLG_ON_Y;
        } else {
            inclPendImptOrdFlg = ZYPConstant.FLG_OFF_N;
        }
        param.add(inclPendImptOrdFlg);
        // [12]Order Date From
        String ordFromDt = null;
        if (CR_REBIL_TOTAY.equals(scrnMsg.xxScrEventNm.getValue())) {
            ordFromDt = scrnMsg.slsDt.getValue();
        } else if (CR_REBIL_CUR_MTH.equals(scrnMsg.xxScrEventNm.getValue())
                || CR_REBIL_TOT.equals(scrnMsg.xxScrEventNm.getValue())) {
            ordFromDt = getDate(scrnMsg.slsDt.getValue(), true, false, false, 0);
        }
        param.add(ordFromDt);
        // [13]Order Date To
        String ordToDt = null;
        if (CR_REBIL_TOTAY.equals(scrnMsg.xxScrEventNm.getValue())) {
            ordToDt = scrnMsg.slsDt.getValue();
        } else if (CR_REBIL_CUR_MTH.equals(scrnMsg.xxScrEventNm.getValue())) {
            ordToDt = getDate(scrnMsg.slsDt.getValue(), false, false, true, -1);
        } else if (CR_REBIL_TOT.equals(scrnMsg.xxScrEventNm.getValue())) {
            ordToDt = scrnMsg.slsDt.getValue();
        }
        param.add(ordToDt);
        // [14]Team
        String ordTeamSrchTxt = null;
        if (DISP_BY_ITEM_LBL_NM_USER.equals(scrnMsg.dplyByItemLbNm.getValue())
                && ZYPCommonFunc.hasValue(scrnMsg.xxOrdTeamSrchTxt_BK)) {
            ordTeamSrchTxt = scrnMsg.xxOrdTeamSrchTxt_BK.getValue();
        } else if (DISP_BY_ITEM_LBL_NM_TEAM.equals(scrnMsg.dplyByItemLbNm.getValue())) {
            ordTeamSrchTxt = recordMsg.xxDplyByItemNm_A1.getValue();
        }
        param.add(ordTeamSrchTxt);
        // [15]Zone
        String ordZnSrchTxt = null;
        if (DISP_BY_ITEM_LBL_NM_USER.equals(scrnMsg.dplyByItemLbNm.getValue())
                && ZYPCommonFunc.hasValue(scrnMsg.xxOrdZnSrchTxt_BK)) {
            ordZnSrchTxt = scrnMsg.xxOrdZnSrchTxt_BK.getValue();
        }
        if (DISP_BY_ITEM_LBL_NM_ZN.equals(scrnMsg.dplyByItemLbNm.getValue())) {
            ordZnSrchTxt = recordMsg.xxDplyByItemNm_A1.getValue();
        }
        param.add(ordZnSrchTxt);
        // [16]Create By User
        String cratByUsrSrchTxt = null;
        if (DISP_BY_ITEM_LBL_NM_USER.equals(scrnMsg.dplyByItemLbNm.getValue())) {
            // QC#16992 MOD Start
            //cratByUsrSrchTxt = recordMsg.xxDplyByItemNm_A1.getValue();
            if (ZYPCommonFunc.hasValue(recordMsg.cratByUsrId_A1)) {
                cratByUsrSrchTxt = recordMsg.cratByUsrId_A1.getValue();
            } else {
                cratByUsrSrchTxt = CONDITION_IS_NULL;
            }
            // QC#16992 MOD End
        }
        param.add(cratByUsrSrchTxt);
        // QC#15760 Add Start
        // [17]Order Category
        param.add(null);
        // [18]Service Machine Master PK
        param.add(null);
        // [19]Contract Number
        param.add(null);
        // QC#15760 Add End

        return param;
    }

    private String getCpoSrcTpDescTxt(String cpoSrcTpCd) {

        CPO_SRC_TPTMsg rsltTMsg = (CPO_SRC_TPTMsg) ZYPCodeDataUtil.findByCode("CPO_SRC_TP", getGlobalCompanyCode(), cpoSrcTpCd);
        if (null == rsltTMsg) {
            return null;
        }
        return rsltTMsg.cpoSrcTpDescTxt.getValue();
    }

    private String getDate(String strDate, boolean startOfMonth, boolean endOfMonth, boolean addDays, int days) {
        String rtnStrDate = null;
        if (!ZYPCommonFunc.hasValue(strDate)) {
            return null;
        }

        int yyyy = Integer.parseInt(strDate.substring(0, 4));
        int mm = Integer.parseInt(strDate.substring(4, 6));
        int dd = Integer.parseInt(strDate.substring(6, 8));

        Calendar cal = Calendar.getInstance();
        cal.set(yyyy, mm - 1 , dd);

        if (startOfMonth) {
            cal.set(yyyy, mm - 1 , cal.getActualMinimum(Calendar.DATE));
        } else if (endOfMonth) {
            cal.set(yyyy, mm - 1 , cal.getActualMaximum(Calendar.DATE));
        } else if (addDays) {
            cal.add(Calendar.DATE, days);
        }

        SimpleDateFormat sdf = new SimpleDateFormat(DT_FMT_YYYYMMDD);
        rtnStrDate = sdf.format(cal.getTime());
        return rtnStrDate;
    }

}
