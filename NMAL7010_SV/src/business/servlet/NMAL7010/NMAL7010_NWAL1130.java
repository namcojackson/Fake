/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.BIZ_ID;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_LEASE_RATE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SPLY_PGM;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_SPEC_CHRG;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_TIER;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_TI;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7010.NMAL7010CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CUST_AUDC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_TRX_AUDC_CATG;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7010_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/02   SRAA            Y.Chen          Update          QC#2175
 * 2018/11/17   Fujitsu         N.Sugiura       Update          QC#29147
 *</pre>
 */
public class NMAL7010_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        if ("OpenWin_PrcFormula".equals(scrnMsg.xxScrEventNm_DH.getValue())) {

            setPrcFmla(scrnMsg);

            NMAL7010CMsg bizMsg = new NMAL7010CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
//        } else if ("OpenWin_MtrPkg".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
//
//            setMtrPkg(scrnMsg);
//
//            NMAL7010CMsg bizMsg = new NMAL7010CMsg();
//            bizMsg.setBusinessID(BIZ_ID);
//            bizMsg.setFunctionCode("20");
//            EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        if ("OpenWin_CustAudcVal_01_Gen".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            setCustAudcVal01Gen(scrnMsg);

        } else if ("OpenWin_CustAudcVal_02_Gen".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            setCustAudcVal02Gen(scrnMsg);

        } else if ("OpenWin_CustAudcVal_03_Gen".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            setCustAudcVal03Gen(scrnMsg);

        } else if ("OpenWin_TrxAudcVal_01_Gen".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            setTrxAudcVal01Gen(scrnMsg);

        } else if ("OpenWin_TrxAudcVal_02_Gen".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            setTrxAudcVal02Gen(scrnMsg);

        } else if ("OpenWin_Model".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            setModel(scrnMsg);

        } else if ("OpenWin_MtrPkg".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            setMtrPkg(scrnMsg);

        } else if ("OpenWin_PrcFormula".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            NMAL7010CMsg bizMsg  = (NMAL7010CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

        } else if ("OpenWin_SubPrcList".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            setSubPrcList(scrnMsg);

        } else if ("OpenWin_LeaseRatePrcList".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            setLeaseRatePrcList(scrnMsg);

        } else if ("OpenWin_SupplyPlan".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            setSupplyPlan(scrnMsg);

        } else if ("OpenWin_DsAcctCust".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            setDsAcctCust(scrnMsg);
        } else if ("OpenWin_MtrLb".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            setMeterLb(scrnMsg);
        // 2018/11/17 QC#29147 Add Start
        } else if ("OpenWin_PrcListBand".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            setPrcListBand(scrnMsg);
        // 2018/11/17 QC#29147 Add End
        } else if ("OpenWin_QualifierValueProd-1".equals(scrnMsg.xxScrEventNm_DH.getValue())
        		|| "OpenWin_QualifierValueProd-2".equals(scrnMsg.xxScrEventNm_DH.getValue())
        		|| "OpenWin_QualifierValueProd-3".equals(scrnMsg.xxScrEventNm_DH.getValue())
        		|| "OpenWin_QualifierValueProd-4".equals(scrnMsg.xxScrEventNm_DH.getValue())
        		|| "OpenWin_QualifierValueProd-5".equals(scrnMsg.xxScrEventNm_DH.getValue())
        		) {
        	setProdHirarchy(scrnMsg);
        } else if ("OpenWin_ContrNum".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
            setContrNum(scrnMsg);
        }

        scrnMsg.xxScrEventNm_DH.clear();
        scrnMsg.xxCellIdx.clear();

    }

    private void setCustAudcVal01Gen(NMAL7010BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();

        if (PRC_CUST_AUDC_CATG.ACCT_NUM.equals(scrnMsg.X.no(idx).prcCustAudcCatgCd_X1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(idx).xxScrItem30Txt_X1, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(idx).xxRecNmTxt_X1, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (PRC_CUST_AUDC_CATG.CUST_PRICE_GROUP.equals(scrnMsg.X.no(idx).prcCustAudcCatgCd_X1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(idx).xxScrItem30Txt_X1, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(idx).xxRecNmTxt_X1, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (PRC_CUST_AUDC_CATG.CSMP_NUM.equals(scrnMsg.X.no(idx).prcCustAudcCatgCd_X1.getValue())) {
            // QC#7303 Add
            ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(idx).xxScrItem30Txt_X1, scrnMsg.R.no(2).xxComnScrColValTxt);
            scrnMsg.X.no(idx).xxRecNmTxt_X1.clear();
        }
        scrnMsg.setFocusItem(scrnMsg.X.no(idx).xxScrItem30Txt_X1);
    }

    private void setCustAudcVal02Gen(NMAL7010BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();

        if (PRC_CUST_AUDC_CATG.ACCT_NUM.equals(scrnMsg.X.no(idx).prcCustAudcCatgCd_X2.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(idx).xxScrItem30Txt_X2, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(idx).xxRecNmTxt_X2, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (PRC_CUST_AUDC_CATG.CUST_PRICE_GROUP.equals(scrnMsg.X.no(idx).prcCustAudcCatgCd_X2.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(idx).xxScrItem30Txt_X2, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(idx).xxRecNmTxt_X2, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (PRC_CUST_AUDC_CATG.CSMP_NUM.equals(scrnMsg.X.no(idx).prcCustAudcCatgCd_X1.getValue())) {
            // QC#7303 Add
            ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(idx).xxScrItem30Txt_X2, scrnMsg.R.no(2).xxComnScrColValTxt);
            scrnMsg.X.no(idx).xxRecNmTxt_X2.clear();
        }
        scrnMsg.setFocusItem(scrnMsg.X.no(idx).xxScrItem30Txt_X2);
    }

    private void setCustAudcVal03Gen(NMAL7010BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();

        if (PRC_CUST_AUDC_CATG.ACCT_NUM.equals(scrnMsg.X.no(idx).prcCustAudcCatgCd_X3.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(idx).xxScrItem30Txt_X3, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(idx).xxRecNmTxt_X3, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (PRC_CUST_AUDC_CATG.CUST_PRICE_GROUP.equals(scrnMsg.X.no(idx).prcCustAudcCatgCd_X3.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(idx).xxScrItem30Txt_X3, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(idx).xxRecNmTxt_X3, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (PRC_CUST_AUDC_CATG.CSMP_NUM.equals(scrnMsg.X.no(idx).prcCustAudcCatgCd_X1.getValue())) {
            // QC#7303 Add
            ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(idx).xxScrItem30Txt_X3, scrnMsg.R.no(2).xxComnScrColValTxt);
            scrnMsg.X.no(idx).xxRecNmTxt_X3.clear();
        }
        scrnMsg.setFocusItem(scrnMsg.X.no(idx).xxRecNmTxt_X3);
    }

    private void setTrxAudcVal01Gen(NMAL7010BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();

        if (PRC_TRX_AUDC_CATG.TRANSACTION_PRICE_GROUP.equals(scrnMsg.Y.no(idx).prcTrxAudcCatgCd_Y1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(idx).xxScrItem30Txt_Y1, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(idx).xxRecNmTxt_Y1, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.Y.no(idx).xxRecNmTxt_Y1);
    }

    private void setTrxAudcVal02Gen(NMAL7010BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();

        if (PRC_TRX_AUDC_CATG.TRANSACTION_PRICE_GROUP.equals(scrnMsg.Y.no(idx).prcTrxAudcCatgCd_Y2.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(idx).xxScrItem30Txt_Y2, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(idx).xxRecNmTxt_Y2, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.Y.no(idx).xxRecNmTxt_Y2);
    }

    private void setSubPrcList(NMAL7010BMsg scrnMsg) {
        // QC#2175
        int idx = scrnMsg.xxCellIdx.getValueInt();
        ZYPEZDItemValueSetter.setValue(scrnMsg.W.no(idx).subPrcCatgCd_SW, scrnMsg.R.no(0).xxComnScrColValTxt.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.W.no(idx).prcCatgNm_SW, scrnMsg.R.no(1).xxComnScrColValTxt);
        scrnMsg.setFocusItem(scrnMsg.W.no(idx).effFromDt_SW);
    }

    private void setLeaseRatePrcList(NMAL7010BMsg scrnMsg) {
        // QC#7080
        ZYPEZDItemValueSetter.setValue(scrnMsg.leaseRatePrcCatgCd_CA, scrnMsg.R.no(0).xxComnScrColValTxt.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm_CA, scrnMsg.R.no(1).xxComnScrColValTxt);
        scrnMsg.setFocusItem(scrnMsg.prcCatgNm_CA);
    }

    private void setSupplyPlan(NMAL7010BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();

        ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(idx).splyAgmtPlnPk_PE, new BigDecimal(scrnMsg.R.no(0).xxComnScrColValTxt.getValue()));
        ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(idx).splyAgmtPlnNm_PE, scrnMsg.R.no(2).xxComnScrColValTxt);
        scrnMsg.setFocusItem(scrnMsg.E.no(idx).splyAgmtPlnNm_PE);
    }

    private void setDsAcctCust(NMAL7010BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();

        ZYPEZDItemValueSetter.setValue(scrnMsg.F.no(idx).dsAcctNum_PF, scrnMsg.R.no(0).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.F.no(idx).dsAcctNm_PF, scrnMsg.R.no(1).xxComnScrColValTxt);
        scrnMsg.setFocusItem(scrnMsg.F.no(idx).dsAcctNm_PF);
    }

    private void setPrcFmla(NMAL7010BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();

        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).prcFmlaPk_PA, new BigDecimal(scrnMsg.R.no(0).xxComnScrColValTxt.getValue()));
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).prcFmlaNm_PA, scrnMsg.R.no(2).xxComnScrColValTxt);
        scrnMsg.setFocusItem(scrnMsg.A.no(idx).prcFmlaNm_PA);
    }

    private void setMtrPkg(NMAL7010BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            if (idx < 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcMtrPkgNm_D1, scrnMsg.R.no(2).xxComnScrColValTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).mdlId_PB, new BigDecimal(scrnMsg.R.no(0).xxComnScrColValTxt.getValue()));
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).mdlNm_PB, scrnMsg.R.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcMtrPkgPk_PB,  new BigDecimal(scrnMsg.R.no(4).xxComnScrColValTxt.getValue()));
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcMtrPkgNm_PB, scrnMsg.R.no(2).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcMtrPkgNm_PB);
            }
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            if (idx < 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcMtrPkgNm_D2, scrnMsg.R.no(2).xxComnScrColValTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).mdlId_PC, new BigDecimal(scrnMsg.R.no(0).xxComnScrColValTxt.getValue()));
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).mdlNm_PC, scrnMsg.R.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).prcMtrPkgPk_PC, new BigDecimal(scrnMsg.R.no(4).xxComnScrColValTxt.getValue()));
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).prcMtrPkgNm_PC, scrnMsg.R.no(2).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.C.no(idx).prcMtrPkgNm_PC);
            }
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            if (idx < 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcMtrPkgNm_D3, scrnMsg.R.no(2).xxComnScrColValTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(idx).mdlId_PE, new BigDecimal(scrnMsg.R.no(0).xxComnScrColValTxt.getValue()));
                ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(idx).mdlNm_PE, scrnMsg.R.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(idx).prcMtrPkgPk_PE, new BigDecimal(scrnMsg.R.no(4).xxComnScrColValTxt.getValue()));
                ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(idx).prcMtrPkgNm_PE, scrnMsg.R.no(2).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.E.no(idx).prcMtrPkgNm_PE);
            }
        }
    }

    private void setModel(NMAL7010BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            if (idx < 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdlNm_D1, scrnMsg.R.no(1).xxComnScrColValTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).mdlNm_PB, scrnMsg.R.no(1).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.B.no(idx).mdlNm_PB);
            }
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            if (idx < 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdlNm_D2, scrnMsg.R.no(1).xxComnScrColValTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).mdlNm_PC, scrnMsg.R.no(1).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.C.no(idx).mdlNm_PC);
            }
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(idx).mdlNm_PD, scrnMsg.R.no(1).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.D.no(idx).mdlNm_PD);
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            if (idx < 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdlNm_D3, scrnMsg.R.no(1).xxComnScrColValTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(idx).mdlNm_PE, scrnMsg.R.no(1).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.E.no(idx).mdlNm_PE);
            }
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            if (idx < 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdlNm_D4, scrnMsg.R.no(1).xxComnScrColValTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.F.no(idx).mdlNm_PF, scrnMsg.R.no(1).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.F.no(idx).mdlNm_PF);
            }
        } else if (TAB_PRC_LIST_VAL_TI.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            if (idx < 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdlNm_D5, scrnMsg.R.no(1).xxComnScrColValTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(idx).mdlNm_PH, scrnMsg.R.no(1).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.H.no(idx).mdlNm_PH);
            }
        }
    }
    private void setMeterLb(NMAL7010BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).mtrLbCd_PB, scrnMsg.R.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).mtrLbNm_PB, scrnMsg.R.no(1).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.B.no(idx).mtrLbNm_PB);
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).mtrLbCd_PC, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).mtrLbNm_PC, scrnMsg.R.no(1).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.C.no(idx).mtrLbNm_PC);
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(idx).mtrLbCd_PE, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(idx).mtrLbNm_PE, scrnMsg.R.no(1).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.E.no(idx).mtrLbNm_PE);
        }
    }
    // 2018/11/17 QC#29147 Add Start
    private void setPrcListBand(NMAL7010BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcListBandCd_PB, scrnMsg.R.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcListBandDescTxt_PB, scrnMsg.R.no(1).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcListBandDescTxt_PB);
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).prcListBandCd_PC, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).prcListBandDescTxt_PC, scrnMsg.R.no(1).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.C.no(idx).prcListBandDescTxt_PC);
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(idx).prcListBandCd_PE, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(idx).prcListBandDescTxt_PE, scrnMsg.R.no(1).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.E.no(idx).prcListBandDescTxt_PE);
        }
    }
    // 2018/11/17 QC#29147 Add End
    private void setProdHirarchy(NMAL7010BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).prcQlfyValTxt_PA, scrnMsg.R.no(0).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).mdseDescShortTxt_PA, scrnMsg.R.no(1).xxComnScrColValTxt);
        scrnMsg.setFocusItem(scrnMsg.A.no(idx).prcQlfyValTxt_PA);
    }

    private void setContrNum(NMAL7010BMsg scrnMsg) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcContrNum_H1, scrnMsg.R.no(1).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcContrNm_H1, scrnMsg.R.no(2).xxComnScrColValTxt);
        scrnMsg.setFocusItem(scrnMsg.prcContrNm_H1);
    }
}
