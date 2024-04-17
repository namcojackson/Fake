/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800;

import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_CTRY_CD;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_DS_ACCT_DUNS_NM;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_DS_CMPY_SIC_CD;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_DS_CMPY_SIC_DESC_TXT;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_DS_CUST_SIC_CD;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_DS_CUST_SIC_DESC_TXT;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_DS_LOC_EMP_NUM;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_DS_LOC_REV_AMT;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_DS_PRNT_DUNS_NUM;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_DS_ULT_DUNS_NUM;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_DUNS_NUM;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_FAX_NUM;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_HQ_DUNS_NUM;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_LOC_CTY_ADDR;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_LOC_FIRST_LINE_ADDR;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_LOC_FRTH_LINE_ADDR;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_LOC_POST_CD;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_LOC_SCD_LINE_ADDR;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_LOC_ST_CD;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_LOC_THIRD_LINE_ADDR;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_TEL_NUM;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_TRTY_ORG_CD;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.BIZ_ID;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.CNTY_NM;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.PROS_RVW_STS_CD;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.SRCH_OPT_NM;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.SRCH_OPT_PK;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2800.NMAL2800CMsg;
import business.servlet.NMAL2800.common.NMAL2800CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL2800_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/26   Fujitsu         N.Sugiura       Update          QC#12417
 * 2017/10/17   Fujitsu         M.Ohno          Update          QC#21782
 * 2018/03/28   Fujitsu         R.Nakamura      Update          QC#23145
 * 
 *</pre>
 */
public class NMAL2800_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;
        NMAL2800CMsg bizMsg = new NMAL2800CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;
        NMAL2800CMsg bizMsg = (NMAL2800CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2800CommonLogic.initCmnBtnProp(this, scrnMsg);

        setAppFracDigit(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.xxTpCd_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        scrnMsg.srchOptPk.setNameForMessage(SRCH_OPT_PK);
        scrnMsg.srchOptNm.setNameForMessage(SRCH_OPT_NM);

        scrnMsg.xxTpCd_H1.setNameForMessage("Mode");
        scrnMsg.lineBizTpCd_H1.setNameForMessage("Account Source Business Unit");
        scrnMsg.xxDt10Dt_H1.setNameForMessage("Prospects Created From");
        scrnMsg.xxDt10Dt_H2.setNameForMessage("Prospects Created To");
        scrnMsg.befDsAcctNm_H1.setNameForMessage("Prospect Name");
        scrnMsg.rvwProsNum_H1.setNameForMessage("Prospect#");
        scrnMsg.xxScrItem61Txt_H1.setNameForMessage("Owner Name");
        scrnMsg.befPsnNum_H1.setNameForMessage("Owner Employee ID");
        scrnMsg.orgNm_H1.setNameForMessage("Owner Territory Name");
        scrnMsg.prosRvwStsCd_H1.setNameForMessage("Processed");
        scrnMsg.dupAcctLocFlg_H1.setNameForMessage("Potential Duplicate");
        scrnMsg.shipAddrAllTxt_H1.setNameForMessage("Address");
        // 2017/10/17 QC#21782 mod start
//        scrnMsg.befBillToCtyAddr_H1.setNameForMessage("City");
//        scrnMsg.befBillToStCd_H1.setNameForMessage("State");
//        scrnMsg.befBillToPostCd_H1.setNameForMessage("Postal Code");
        scrnMsg.befShipToCtyAddr_H1.setNameForMessage("City");
        scrnMsg.befShipToStNm_H1.setNameForMessage("State");
        scrnMsg.befShipToPostCd_H1.setNameForMessage("Postal Code");
        // 2017/10/17 QC#21782 modend

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NMAL2800_ABMsg aBMsg = scrnMsg.A.no(i);
            aBMsg.prosRvwStsCd_A1.setNameForMessage(PROS_RVW_STS_CD);
            aBMsg.rvwProsNum_A1.setNameForMessage("Prospect Number");
            aBMsg.befDsAcctNm_A1.setNameForMessage("Prospect Name");
            // 2017/10/17 QC#21782 mod start
//            aBMsg.befBillToFirstLineAddr_A1.setNameForMessage("Address1");
//            aBMsg.befBillToScdLineAddr_A1.setNameForMessage("Address2");
//            aBMsg.befBillToThirdLineAddr_A1.setNameForMessage("Address3");
//            aBMsg.befBillToFrthLineAddr_A1.setNameForMessage("Address4");
//            aBMsg.befBillToCtyAddr_A1.setNameForMessage("City");
//            aBMsg.befBillToStCd_A1.setNameForMessage("State");
//            aBMsg.befBillToPostCd_A1.setNameForMessage("Postal Code");
//            aBMsg.befBillToCntyNm_A1.setNameForMessage("County");
            aBMsg.befLocFirstLineAddr_A1.setNameForMessage("Address1");
            aBMsg.befLocScdLineAddr_A1.setNameForMessage("Address2");
            aBMsg.befLocThirdLineAddr_A1.setNameForMessage("Address3");
            aBMsg.befLocFrthLineAddr_A1.setNameForMessage("Address4");
            aBMsg.befShipToCtyAddr_A1.setNameForMessage("City");
            aBMsg.befShipToStNm_A1.setNameForMessage("State");
            aBMsg.befShipToPostCd_A1.setNameForMessage("Postal Code");
            aBMsg.befShipToCntyNm_A1.setNameForMessage("County");
            // 2017/10/17 QC#21782 mod end
            aBMsg.xxScrItem61Txt_A1.setNameForMessage("Current Owner Name");
            aBMsg.lineBizTpDescTxt_A1.setNameForMessage("Line Of Business Type");
            aBMsg.resrcTrtyOrgNm_A1.setNameForMessage("Territory Candidates (Resource Base)");
            aBMsg.candiTrtyNm_A1.setNameForMessage("Territory Candidates (Rule Base)");
            aBMsg.xxDtTxt_A1.setNameForMessage("Creation Date");
            aBMsg.matchAcctLocNum_A1.setNameForMessage("Potential Duplicate Location ID");
            aBMsg.trtyOrgNm_A1.setNameForMessage(AFT_TRTY_ORG_CD);
            aBMsg.xxScrItem61Txt_A2.setNameForMessage("Assign To Sales Rep");
            aBMsg.aftLocNum_A1.setNameForMessage("Merge To Location ID");
            aBMsg.aftDsXrefAcctCd_A1.setNameForMessage("Merge To Prospect#");
            aBMsg.aftLocFirstLineAddr_A1.setNameForMessage(AFT_LOC_FIRST_LINE_ADDR);
            aBMsg.aftLocScdLineAddr_A1.setNameForMessage(AFT_LOC_SCD_LINE_ADDR);
            aBMsg.aftLocThirdLineAddr_A1.setNameForMessage(AFT_LOC_THIRD_LINE_ADDR);
            aBMsg.aftLocFrthLineAddr_A1.setNameForMessage(AFT_LOC_FRTH_LINE_ADDR);
            aBMsg.aftLocCtyAddr_A1.setNameForMessage(AFT_LOC_CTY_ADDR);
            aBMsg.aftLocStCd_A1.setNameForMessage(AFT_LOC_ST_CD);
            aBMsg.aftLocPostCd_A1.setNameForMessage(AFT_LOC_POST_CD);
            aBMsg.cntyNm_A1.setNameForMessage(CNTY_NM);
            aBMsg.aftCtryCd_A1.setNameForMessage(AFT_CTRY_CD);
            aBMsg.befTelNum_A1.setNameForMessage("Phone");
            aBMsg.aftTelNum_A1.setNameForMessage(AFT_TEL_NUM);
            aBMsg.befFaxNum_A1.setNameForMessage("Fax");
            aBMsg.aftFaxNum_A1.setNameForMessage(AFT_FAX_NUM);
            aBMsg.dsAcctUrl_A1.setNameForMessage("Web Site");
            aBMsg.xxDtTxt_A2.setNameForMessage("Last Update Date");
            aBMsg.befDunsNum_A1.setNameForMessage("DUNS Number");
            aBMsg.befDsCustSicCd_A1.setNameForMessage("SIC Code");
            aBMsg.befDsUltDunsNum_A1.setNameForMessage("Ultimate DUNS Number");
            aBMsg.aftDsAcctDunsNm_A1.setNameForMessage(AFT_DS_ACCT_DUNS_NM);
            aBMsg.aftDsLocRevAmt_A1.setNameForMessage(AFT_DS_LOC_REV_AMT);
            aBMsg.aftDunsNum_A1.setNameForMessage(AFT_DUNS_NUM);
            aBMsg.aftDsCustSicDescTxt_A1.setNameForMessage(AFT_DS_CUST_SIC_DESC_TXT);
            aBMsg.aftDsLocEmpNum_A1.setNameForMessage(AFT_DS_LOC_EMP_NUM);
            aBMsg.aftDsCustSicCd_A1.setNameForMessage(AFT_DS_CUST_SIC_CD);
            aBMsg.aftDsUltDunsNum_A1.setNameForMessage(AFT_DS_ULT_DUNS_NUM);
            // Del Start 2018/03/28 QC#23145
//            aBMsg.aftGlnNum_A1.setNameForMessage(AFT_GLN_NUM);
            // Del End 2018/03/28 QC#23145
            aBMsg.aftDsPrntDunsNum_A1.setNameForMessage(AFT_DS_PRNT_DUNS_NUM);
            aBMsg.aftHqDunsNum_A1.setNameForMessage(AFT_HQ_DUNS_NUM);
            aBMsg.aftDsCmpySicCd_A1.setNameForMessage(AFT_DS_CMPY_SIC_CD);
            aBMsg.aftDsCmpySicDescTxt_A1.setNameForMessage(AFT_DS_CMPY_SIC_DESC_TXT);
            aBMsg.dsAcctNum_A1.setNameForMessage("S21 Account Number");
            aBMsg.locNum_A1.setNameForMessage("S21 Location ID");
        }
    }

    private void setAppFracDigit(NMAL2800BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).aftDsLocRevAmt_A1.setAppFracDigit(2);
        }
    }
}
