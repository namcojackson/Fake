/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import static business.servlet.NWAL1570.constant.NWAL1570Constant.Y;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.BIZ_ID;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.SCRN_ID_02;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.STS_NM_PEND_IMPT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1570.NWAL1570CMsg;
import business.servlet.NWAL1570.constant.NWAL1570Constant.HDR_STS;
import business.servlet.NWAL1570.constant.NWAL1570Constant.LINE_STS;
import business.servlet.NWAL1570.constant.NWAL1570Constant.RTRN_LINE_STS;

import business.servlet.NWAL1570.common.NWAL1570CommonLogic;
import business.servlet.NWAL1570.constant.NWAL1570Constant.RSLT_MODE;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1570Scrn02_toInquiryByStatus
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 * 2016/07/26   Fujitsu         K.Sato          Update          QC#11581
 * 2016/11/22   Fujitsu         K.Sato          Update          QC#15760
 * 2017/11/21   Fujitsu         T.Aoi           Update          QC#22550
 * 2018/07/05   Fujitsu         M.Ishii         Update          QC#25786
 * 2018/08/01   Fujitsu         T.Aoi           Update          QC#26304
 *</pre>
 */
public class NWAL1570Scrn02_toInquiryByStatus extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        this.getSubmitedFieldNm(ctx);
        this.getSubmitedBusinessAplId(ctx);

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        // backUp
        NWAL1570CommonLogic.backUpSearchCriteria(scrnMsg);

        // Mode:Inquiry
        scrnMsg.xxRsltModeCd.setValue(RSLT_MODE.ORDER_INQUIRY.getRsltModeCd());

        scrnMsg.xxRowNum.setValue(getButtonSelectNumber());
        NWAL1570_ABMsg recordMsg = scrnMsg.A.no(getButtonSelectNumber());
        String dplyBy01ItemNm = scrnMsg.dplyBy01ItemNm.getValue();
        String dplyBy02ItemNm = scrnMsg.dplyBy02ItemNm.getValue();
        String dplyBy03ItemNm = scrnMsg.dplyBy03ItemNm.getValue();

        if (ZYPCommonFunc.hasValue(dplyBy01ItemNm)) {
            if ("SRC_REF_OR_CPO_ORD_NUM".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoOrdNumSrchTxt_H1, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("ADD_ORIG_CPO_ORD_NUM".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoOrdNumSrchTxt_H2, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("CUST_ISS_PO_NUM".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.custIssPoNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("LEASE_CMPY_PO_NUM".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxLeasePoNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("SOLD_TO_CUST_ACCT_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToAcctCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("SOLD_TO_CUST_LOC_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToLocCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("SHIP_TO_CUST_ACCT_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToAcctCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("SHIP_TO_CUST_LOC_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToLocCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("BILL_TO_CUST_ACCT_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToAcctCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("BILL_TO_CUST_LOC_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToLocCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("COA_EXTN_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaExtnSrchTxt, recordMsg.xxOrdInqSrchTxt_N1.getValue().toUpperCase());
            }
            if ("COA_BR_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaBrSrchTxt, recordMsg.xxOrdInqSrchTxt_N1.getValue().toUpperCase());
            }
            if ("SLS_REP_TOC_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSlsRepTocSrchTxt, recordMsg.xxOrdInqSrchTxt_N1.getValue().toUpperCase());
            }
            if ("CPO_SRC_TP_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoSrcTpSrchTxt, recordMsg.xxOrdInqSrchTxt_N1);
            }
            if ("LINE_BIZ_TP_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsBizLineSrchTxt, recordMsg.xxOrdInqSrchTxt_N1);
            }
            if ("DS_ORD_CATG_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdCatgSrchTxt, recordMsg.xxOrdInqSrchTxt_N1);
            }
            if ("DS_ORD_TP_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdTpSrchTxt, recordMsg.xxOrdInqSrchTxt_N1);
            }
            if ("DS_ORD_RSN_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdRsnSrchTxt, recordMsg.xxOrdInqSrchTxt_N1);
            }
            if ("CSMP_CONTR_NUM".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCsmpContrNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("PRC_CONTR_NUM".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPrcContrNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("ORD_SRC_REF_NUM".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcRefNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            // 2018/08/01 QC#26304 Add Start
            if ("AQU_NUM".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxAquNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            // 2018/08/01 QC#26304 Add End
            if ("MDSE_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxMdseSrchTxt, recordMsg.xxOrdInqSrchTxt_N1);
            }
            if ("ZEROTH_PROD_CTRL_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.zerothProdCtrlSrchTxt, recordMsg.xxOrdInqSrchTxt_N1.getValue().toUpperCase());
            }
            if ("FIRST_PROD_CTRL_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.firstProdCtrlSrchTxt, recordMsg.xxOrdInqSrchTxt_N1.getValue().toUpperCase());
            }
            if ("SCD_PROD_CTRL_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.scdProdCtrlSrchTxt, recordMsg.xxOrdInqSrchTxt_N1.getValue().toUpperCase());
            }
            if ("THIRD_PROD_CTRL_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlSrchTxt, recordMsg.xxOrdInqSrchTxt_N1.getValue().toUpperCase());
            }
            if ("FRTH_PROD_CTRL_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.frthProdCtrlSrchTxt, recordMsg.xxOrdInqSrchTxt_N1.getValue().toUpperCase());
            }
            if ("COA_PROD_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaProdSrchTxt, recordMsg.xxOrdInqSrchTxt_N1.getValue().toUpperCase());
            }
            if ("COA_MDSE_TP_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaMdseTpSrchTxt, recordMsg.xxOrdInqSrchTxt_N1.getValue().toUpperCase());
            }
            if ("MDL_ID".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxMdlSrchTxt, recordMsg.xxOrdInqSrchTxt_N1);
            }
            if ("SER_NUM".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSerNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("DS_ORD_LINE_CATG_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxLineCatgSrchTxt, recordMsg.xxOrdInqSrchTxt_N1);
            }
            if ("ORD_LINE_SRC_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdLineSrcSrchTxt, recordMsg.xxOrdInqSrchTxt_N1);
            }
            if ("RTL_WH_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtlWhSrchTxt, recordMsg.xxOrdInqSrchTxt_N1);
            }
            if ("RTL_SWH_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtlSwhSrchTxt, recordMsg.xxOrdInqSrchTxt_N1);
            }
            if ("INVTY_LOC_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxVndSrchTxt, recordMsg.xxOrdInqSrchTxt_N1.getValue().toUpperCase());
            }
            if ("PO_ORD_NUM".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoOrdNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("SO_NUM".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.soNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("INV_NUM".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.invNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("DS_CONTR_NUM".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsContrNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("SVC_CONFIG_MSTR_PK".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSvcConfigMstrSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            // QC#15760 Add Start
            if ("SVC_MACH_MSTR_PK".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSvcMachMstrSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            // QC#15760 Add End
            if ("RTRN_RSN_CD".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnRsnCd, recordMsg.xxOrdInqSrchTxt_N1);
            }
            if ("ORD_TEAM_MSTR_NM".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdTeamSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("ORD_ZN_DESC_TXT".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdZnSrchTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("CRAT_BY_USR_ID".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCratByUsrSrchTxt, recordMsg.xxOrdInqSrchTxt_C1.getValue().toUpperCase());
            }
            if ("CPO_ORD_TS".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.ordFromDt, recordMsg.xxOrdInqSrchTxt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.ordToDt, recordMsg.xxOrdInqSrchTxt_C1.getValue());
            }
            if ("ORD_BOOK_TS".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBookFromDt, recordMsg.xxOrdInqSrchTxt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBookToDt, recordMsg.xxOrdInqSrchTxt_C1.getValue());
            }
            if ("ACTL_SHIP_DT".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxActlShipFromDt, recordMsg.xxOrdInqSrchTxt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxActlShipToDt, recordMsg.xxOrdInqSrchTxt_C1.getValue());
            }
            if ("INV_DT".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.invFromDt, recordMsg.xxOrdInqSrchTxt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.invToDt, recordMsg.xxOrdInqSrchTxt_C1.getValue());
            }
            if ("ORD_AGING_BCKT_DESC_TXT".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.ordAgingBcktDescTxt, recordMsg.xxOrdInqSrchTxt_C1);
            }
            if ("ORD_SRC_IMPT_DT".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcImptFromDt, recordMsg.xxOrdInqSrchTxt_C1.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcImptToDt, recordMsg.xxOrdInqSrchTxt_C1.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(dplyBy02ItemNm)) {
            if ("SRC_REF_OR_CPO_ORD_NUM".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoOrdNumSrchTxt_H1, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("ADD_ORIG_CPO_ORD_NUM".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoOrdNumSrchTxt_H2, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("CUST_ISS_PO_NUM".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.custIssPoNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("LEASE_CMPY_PO_NUM".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxLeasePoNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("SOLD_TO_CUST_ACCT_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToAcctCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("SOLD_TO_CUST_LOC_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToLocCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("SHIP_TO_CUST_ACCT_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToAcctCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("SHIP_TO_CUST_LOC_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToLocCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("BILL_TO_CUST_ACCT_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToAcctCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("BILL_TO_CUST_LOC_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToLocCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("COA_EXTN_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaExtnSrchTxt, recordMsg.xxOrdInqSrchTxt_N2.getValue().toUpperCase());
            }
            if ("COA_BR_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaBrSrchTxt, recordMsg.xxOrdInqSrchTxt_N2.getValue().toUpperCase());
            }
            if ("SLS_REP_TOC_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSlsRepTocSrchTxt, recordMsg.xxOrdInqSrchTxt_N2.getValue().toUpperCase());
            }
            if ("CPO_SRC_TP_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoSrcTpSrchTxt, recordMsg.xxOrdInqSrchTxt_N2);
            }
            if ("LINE_BIZ_TP_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsBizLineSrchTxt, recordMsg.xxOrdInqSrchTxt_N2);
            }
            if ("DS_ORD_CATG_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdCatgSrchTxt, recordMsg.xxOrdInqSrchTxt_N2);
            }
            if ("DS_ORD_TP_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdTpSrchTxt, recordMsg.xxOrdInqSrchTxt_N2);
            }
            if ("DS_ORD_RSN_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdRsnSrchTxt, recordMsg.xxOrdInqSrchTxt_N2);
            }
            if ("CSMP_CONTR_NUM".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCsmpContrNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("PRC_CONTR_NUM".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPrcContrNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("ORD_SRC_REF_NUM".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcRefNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            // 2018/08/01 QC#26304 Add Start
            if ("AQU_NUM".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxAquNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            // 2018/08/01 QC#26304 Add End
            if ("MDSE_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxMdseSrchTxt, recordMsg.xxOrdInqSrchTxt_N2);
            }
            if ("ZEROTH_PROD_CTRL_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.zerothProdCtrlSrchTxt, recordMsg.xxOrdInqSrchTxt_N2.getValue().toUpperCase());
            }
            if ("FIRST_PROD_CTRL_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.firstProdCtrlSrchTxt, recordMsg.xxOrdInqSrchTxt_N2.getValue().toUpperCase());
            }
            if ("SCD_PROD_CTRL_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.scdProdCtrlSrchTxt, recordMsg.xxOrdInqSrchTxt_N2.getValue().toUpperCase());
            }
            if ("THIRD_PROD_CTRL_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlSrchTxt, recordMsg.xxOrdInqSrchTxt_N2.getValue().toUpperCase());
            }
            if ("FRTH_PROD_CTRL_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.frthProdCtrlSrchTxt, recordMsg.xxOrdInqSrchTxt_N2.getValue().toUpperCase());
            }
            if ("COA_PROD_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaProdSrchTxt, recordMsg.xxOrdInqSrchTxt_N2.getValue().toUpperCase());
            }
            if ("COA_MDSE_TP_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaMdseTpSrchTxt, recordMsg.xxOrdInqSrchTxt_N2.getValue().toUpperCase());
            }
            if ("MDL_ID".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxMdlSrchTxt, recordMsg.xxOrdInqSrchTxt_N2);
            }
            if ("SER_NUM".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSerNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("DS_ORD_LINE_CATG_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxLineCatgSrchTxt, recordMsg.xxOrdInqSrchTxt_N2);
            }
            if ("ORD_LINE_SRC_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdLineSrcSrchTxt, recordMsg.xxOrdInqSrchTxt_N2);
            }
            if ("RTL_WH_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtlWhSrchTxt, recordMsg.xxOrdInqSrchTxt_N2);
            }
            if ("RTL_SWH_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtlSwhSrchTxt, recordMsg.xxOrdInqSrchTxt_N2);
            }
            if ("INVTY_LOC_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxVndSrchTxt, recordMsg.xxOrdInqSrchTxt_N2.getValue().toUpperCase());
            }
            if ("PO_ORD_NUM".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoOrdNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("SO_NUM".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.soNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("INV_NUM".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.invNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("DS_CONTR_NUM".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsContrNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("SVC_CONFIG_MSTR_PK".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSvcConfigMstrSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            // QC#15760 Add Start
            if ("SVC_MACH_MSTR_PK".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSvcMachMstrSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            // QC#15760 Add End
            if ("RTRN_RSN_CD".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnRsnCd, recordMsg.xxOrdInqSrchTxt_N2);
            }
            if ("ORD_TEAM_MSTR_NM".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdTeamSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("ORD_ZN_DESC_TXT".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdZnSrchTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("CRAT_BY_USR_ID".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCratByUsrSrchTxt, recordMsg.xxOrdInqSrchTxt_C2.getValue().toUpperCase());
            }
            if ("CPO_ORD_TS".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.ordFromDt, recordMsg.xxOrdInqSrchTxt_C2.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.ordToDt, recordMsg.xxOrdInqSrchTxt_C2.getValue());
            }
            if ("ORD_BOOK_TS".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBookFromDt, recordMsg.xxOrdInqSrchTxt_C2.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBookToDt, recordMsg.xxOrdInqSrchTxt_C2.getValue());
            }
            if ("ACTL_SHIP_DT".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxActlShipFromDt, recordMsg.xxOrdInqSrchTxt_C2.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxActlShipToDt, recordMsg.xxOrdInqSrchTxt_C2.getValue());
            }
            if ("INV_DT".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.invFromDt, recordMsg.xxOrdInqSrchTxt_C2.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.invToDt, recordMsg.xxOrdInqSrchTxt_C2.getValue());
            }
            if ("ORD_AGING_BCKT_DESC_TXT".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.ordAgingBcktDescTxt, recordMsg.xxOrdInqSrchTxt_C2);
            }
            if ("ORD_SRC_IMPT_DT".equals(dplyBy02ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcImptFromDt, recordMsg.xxOrdInqSrchTxt_C2.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcImptToDt, recordMsg.xxOrdInqSrchTxt_C2.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(dplyBy03ItemNm)) {
            if ("SRC_REF_OR_CPO_ORD_NUM".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoOrdNumSrchTxt_H1, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("ADD_ORIG_CPO_ORD_NUM".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoOrdNumSrchTxt_H2, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("CUST_ISS_PO_NUM".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.custIssPoNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("LEASE_CMPY_PO_NUM".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxLeasePoNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("SOLD_TO_CUST_ACCT_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToAcctCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("SOLD_TO_CUST_LOC_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToLocCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("SHIP_TO_CUST_ACCT_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToAcctCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("SHIP_TO_CUST_LOC_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToLocCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("BILL_TO_CUST_ACCT_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToAcctCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("BILL_TO_CUST_LOC_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToLocCdSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("COA_EXTN_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaExtnSrchTxt, recordMsg.xxOrdInqSrchTxt_N3.getValue().toUpperCase());
            }
            if ("COA_BR_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaBrSrchTxt, recordMsg.xxOrdInqSrchTxt_N3.getValue().toUpperCase());
            }
            if ("SLS_REP_TOC_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSlsRepTocSrchTxt, recordMsg.xxOrdInqSrchTxt_N3.getValue().toUpperCase());
            }
            if ("CPO_SRC_TP_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoSrcTpSrchTxt, recordMsg.xxOrdInqSrchTxt_N3);
            }
            if ("LINE_BIZ_TP_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsBizLineSrchTxt, recordMsg.xxOrdInqSrchTxt_N3);
            }
            if ("DS_ORD_CATG_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdCatgSrchTxt, recordMsg.xxOrdInqSrchTxt_N3);
            }
            if ("DS_ORD_TP_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdTpSrchTxt, recordMsg.xxOrdInqSrchTxt_N3);
            }
            if ("DS_ORD_RSN_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdRsnSrchTxt, recordMsg.xxOrdInqSrchTxt_N3);
            }
            if ("CSMP_CONTR_NUM".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCsmpContrNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("PRC_CONTR_NUM".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPrcContrNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("ORD_SRC_REF_NUM".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcRefNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            // 2018/08/01 QC#26304 Add Start
            if ("AQU_NUM".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxAquNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            // 2018/08/01 QC#26304 Add End
            if ("MDSE_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxMdseSrchTxt, recordMsg.xxOrdInqSrchTxt_N3);
            }
            if ("ZEROTH_PROD_CTRL_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.zerothProdCtrlSrchTxt, recordMsg.xxOrdInqSrchTxt_N3.getValue().toUpperCase());
            }
            if ("FIRST_PROD_CTRL_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.firstProdCtrlSrchTxt, recordMsg.xxOrdInqSrchTxt_N3.getValue().toUpperCase());
            }
            if ("SCD_PROD_CTRL_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.scdProdCtrlSrchTxt, recordMsg.xxOrdInqSrchTxt_N3.getValue().toUpperCase());
            }
            if ("THIRD_PROD_CTRL_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlSrchTxt, recordMsg.xxOrdInqSrchTxt_N3.getValue().toUpperCase());
            }
            if ("FRTH_PROD_CTRL_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.frthProdCtrlSrchTxt, recordMsg.xxOrdInqSrchTxt_N3.getValue().toUpperCase());
            }
            if ("COA_PROD_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaProdSrchTxt, recordMsg.xxOrdInqSrchTxt_N3.getValue().toUpperCase());
            }
            if ("COA_MDSE_TP_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaMdseTpSrchTxt, recordMsg.xxOrdInqSrchTxt_N3.getValue().toUpperCase());
            }
            if ("MDL_ID".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxMdlSrchTxt, recordMsg.xxOrdInqSrchTxt_N3);
            }
            if ("SER_NUM".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSerNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("DS_ORD_LINE_CATG_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxLineCatgSrchTxt, recordMsg.xxOrdInqSrchTxt_N3);
            }
            if ("ORD_LINE_SRC_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdLineSrcSrchTxt, recordMsg.xxOrdInqSrchTxt_N3);
            }
            if ("RTL_WH_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtlWhSrchTxt, recordMsg.xxOrdInqSrchTxt_N3);
            }
            if ("RTL_SWH_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtlSwhSrchTxt, recordMsg.xxOrdInqSrchTxt_N3);
            }
            if ("INVTY_LOC_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxVndSrchTxt, recordMsg.xxOrdInqSrchTxt_N3.getValue().toUpperCase());
            }
            if ("PO_ORD_NUM".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoOrdNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("SO_NUM".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.soNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("INV_NUM".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.invNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("DS_CONTR_NUM".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsContrNumSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("SVC_CONFIG_MSTR_PK".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSvcConfigMstrSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            // QC#15760 Add Start
            if ("SVC_MACH_MSTR_PK".equals(dplyBy01ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSvcMachMstrSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            // QC#15760 Add End
            if ("RTRN_RSN_CD".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnRsnCd, recordMsg.xxOrdInqSrchTxt_N3);
            }
            if ("ORD_TEAM_MSTR_NM".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdTeamSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("ORD_ZN_DESC_TXT".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdZnSrchTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("CRAT_BY_USR_ID".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCratByUsrSrchTxt, recordMsg.xxOrdInqSrchTxt_C3.getValue().toUpperCase());
            }
            if ("CPO_ORD_TS".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.ordFromDt, recordMsg.xxOrdInqSrchTxt_C3.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.ordToDt, recordMsg.xxOrdInqSrchTxt_C3.getValue());
            }
            if ("ORD_BOOK_TS".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBookFromDt, recordMsg.xxOrdInqSrchTxt_C3.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxBookToDt, recordMsg.xxOrdInqSrchTxt_C3.getValue());
            }
            if ("ACTL_SHIP_DT".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxActlShipFromDt, recordMsg.xxOrdInqSrchTxt_C3.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxActlShipToDt, recordMsg.xxOrdInqSrchTxt_C3.getValue());
            }
            if ("INV_DT".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.invFromDt, recordMsg.xxOrdInqSrchTxt_C3.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.invToDt, recordMsg.xxOrdInqSrchTxt_C3.getValue());
            }
            if ("ORD_AGING_BCKT_DESC_TXT".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.ordAgingBcktDescTxt, recordMsg.xxOrdInqSrchTxt_C3);
            }
            if ("ORD_SRC_IMPT_DT".equals(dplyBy03ItemNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcImptFromDt, recordMsg.xxOrdInqSrchTxt_C3.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcImptToDt, recordMsg.xxOrdInqSrchTxt_C3.getValue());
            }
        }

        String dplyStsNm = scrnMsg.xxDplyStsNm.getValue();

        // Header Status
        if (HDR_STS.ENT.getHdrStsNm().equals(dplyStsNm)
                || HDR_STS.DI_HLD.getHdrStsNm().equals(dplyStsNm)
                || HDR_STS.VLD.getHdrStsNm().equals(dplyStsNm)
                || HDR_STS.PRFT.getHdrStsNm().equals(dplyStsNm)
                || HDR_STS.CR_HLD.getHdrStsNm().equals(dplyStsNm)
                // 2018/07/05 QC#25786 mod start
//                || HDR_STS.SPLY_ABUSE.getHdrStsNm().equals(dplyStsNm)
                || HDR_STS.SPLY_ENFORCEMENT.getHdrStsNm().equals(dplyStsNm)
                // 2018/07/05 QC#25786 mod end
                || HDR_STS.PEND_RE_SUBMT.getHdrStsNm().equals(dplyStsNm)) {
            for (int i = 0; i <  scrnMsg.H.length(); i++) {
                if (scrnMsg.H.no(i).ordHdrStsNm_HS.getValue().equals(dplyStsNm)) {
                    scrnMsg.H.no(i).xxOrdHdrStsSelFlg_HS.setValue(Y);
                }
            }
        }
        // Line Status
        if (LINE_STS.BOOK.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.AWAIT_DROP_SHIP.getLineStsNm().equals(dplyStsNm) // 2017/11/21 QC#22550 Add
                //|| LINE_STS.SO_CANC.getLineStsNm().equals(dplyStsNm) // 2017/11/21 QC#22550 Del
                || LINE_STS.PEND_RE_ALLOC.getLineStsNm().equals(dplyStsNm) // 2017/11/21 QC#22550 Add
                || LINE_STS.PO_CANC.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.PEND_FUFL.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.PEND_ALLOC.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.BO.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.PEND_PICK.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.DELY_TO_SHOP.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.IN_SHOP_CONFIG.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.PEND_SHIP.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.SHIP.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.PEND_DELY_CONF.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.PEND_ISTL.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.ON_LOAN.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.WAIT_RCPT.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.PEND_INV.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.BLLG_HLD.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.PEND_DLR_ISTL.getLineStsNm().equals(dplyStsNm) // 2017/11/21 QC#22550 Add
                || LINE_STS.SHIP_CLO.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.CLO_LOAN_RTRN.getLineStsNm().equals(dplyStsNm)
                || LINE_STS.CLO_LOAN_SOLD.getLineStsNm().equals(dplyStsNm)
                // QC#11581 DEL Start
//                || LINE_STS.INV.getLineStsNm().equals(dplyStsNm)
                // QC#11581 DEL End
                || LINE_STS.CANC.getLineStsNm().equals(dplyStsNm)) {
            for (int i = 0; i <  scrnMsg.L.length(); i++) {
                if (scrnMsg.L.no(i).ordLineStsNm_LS.getValue().equals(dplyStsNm)) {
                    scrnMsg.L.no(i).xxLineStsSelFlg_LS.setValue(Y);
                }
            }
        }
        // Return Line Status
        if (RTRN_LINE_STS.BOOK.getRtrnLineStsNm().equals(dplyStsNm)
                || RTRN_LINE_STS.PEND_RTRN.getRtrnLineStsNm().equals(dplyStsNm)
                || RTRN_LINE_STS.PEND_INSP.getRtrnLineStsNm().equals(dplyStsNm)
                || RTRN_LINE_STS.RWS_CANC.getRtrnLineStsNm().equals(dplyStsNm)
                || RTRN_LINE_STS.PRTL_RCV.getRtrnLineStsNm().equals(dplyStsNm)
                || RTRN_LINE_STS.PEND_INV.getRtrnLineStsNm().equals(dplyStsNm)
                || RTRN_LINE_STS.BLLG_HLD.getRtrnLineStsNm().equals(dplyStsNm)
                // QC#11581 DEL Start
//                || RTRN_LINE_STS.INV.getRtrnLineStsNm().equals(dplyStsNm)
                // QC#11581 DEL End
                || RTRN_LINE_STS.CLO.getRtrnLineStsNm().equals(dplyStsNm)
                || RTRN_LINE_STS.CANC.getRtrnLineStsNm().equals(dplyStsNm)) {
            for (int i = 0; i <  scrnMsg.R.length(); i++) {
                if (scrnMsg.R.no(i).rtrnLineStsNm_RS.getValue().equals(dplyStsNm)) {
                    scrnMsg.R.no(i).xxRtrnLineStsSelFlg_RS.setValue(Y);
                }
            }
        }

//        if (STS_NM_PEND_IMPT.equals(dplyStsNm)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxInclPendImptOrdFlg, ZYPConstant.FLG_ON_Y);
//        } else {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxInclPendImptOrdFlg, ZYPConstant.FLG_OFF_N);
//        }
        if (STS_NM_PEND_IMPT.equals(dplyStsNm) && !ZYPCommonFunc.hasValue(scrnMsg.xxInclPendImptOrdFlg)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxInclPendImptOrdFlg, ZYPConstant.FLG_OFF_N);
        }

        NWAL1570CMsg bizMsg = new NWAL1570CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;
        NWAL1570CMsg bizMsg  = (NWAL1570CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1570CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();

        if (NWAL1570CommonLogic.hasMsgError(bizMsg)) {
            // Mode:Summary
            scrnMsg.xxRsltModeCd.setValue(RSLT_MODE.STATUS_SUMMARY.getRsltModeCd());

            // retrieve Search Criteria
            NWAL1570CommonLogic.retrieveSearchCriteria(scrnMsg);

            throw new EZDFieldErrorException();
        }

        scrnMsg.xxScrId.setValue(SCRN_ID_02);

        // 1. initialize GUI attribute.
        NWAL1570CommonLogic.initGuiAttrScrnRslt(this, scrnMsg);

        // 2. initialize GUI value.
        NWAL1570CommonLogic.initGuiValueScrnRslt(scrnMsg);

        // 3. set alternate rows back-ground color
        NWAL1570CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

    }
}
