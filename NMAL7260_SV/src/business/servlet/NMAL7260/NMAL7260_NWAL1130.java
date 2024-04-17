/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import static business.servlet.NMAL7260.constant.NMAL7260Constant.*;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_CSMPNUM;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_FORMULA;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_FREIGHTZONE;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_PRCGRPCUST_SOLD;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_PRCGRPMAT;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_PRCGRPMAT_QTYBRK;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_PRCGRPTRX;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_PRCLIST;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_PRODCTRL;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_PRODCTRL2;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_PRODCTRL3;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_PRODCTRL4;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_PRODCTRL5;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_SVCMDL;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7260_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2016/04/06   Fujitsu         Y.Kanefusa      Update          QC#6397
 * 2016/04/14   Fujitsu         Y.Kanefusa      Update          QC#6173
 * 2017/08/24   Fujitsu         S.Ohki          Update          QC#20729
 * 2017/11/10   Fujitsu         Y.Kanefusa      Update          S21_NA#20249
 * 2018/04/19   Fujitsu         M.Ohno          Update          QC#22569 
 * 2018/06/15   Fujitsu         M.Ishii         Update          QC#22594
 *</pre>
 */
public class NMAL7260_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // 2018/06/15 QC#22594 Delete Start
//        if ("CMN_Close".equals(getLastGuard())) {
//            return;
//        }
        // 2018/06/15 QC#22594 Delete End

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;

        if (OPENWIN_CSMPNUM.equals(scrnMsg.xxScrEventNm.getValue())) {
            setCsmpNum(scrnMsg);
        } else if (OPENWIN_PRODCTRL.equals(scrnMsg.xxScrEventNm.getValue())) {
            setProdCtrl(scrnMsg);
        } else if (OPENWIN_PRODCTRL2.equals(scrnMsg.xxScrEventNm.getValue())) {
            setProdCtrl2(scrnMsg);
        } else if (OPENWIN_COAPROD.equals(scrnMsg.xxScrEventNm.getValue())) {
            setCoaProd(scrnMsg);
        } else if (OPENWIN_PRCGRPMAT.equals(scrnMsg.xxScrEventNm.getValue())) {
            setPrcGrpMat(scrnMsg);
        } else if (OPENWIN_PRCGRPTRX.equals(scrnMsg.xxScrEventNm.getValue())) {
            setPrcGrpTrx(scrnMsg);
        } else if (OPENWIN_FORMULA.equals(scrnMsg.xxScrEventNm.getValue())) {
            setFormula(scrnMsg);
        } else if (OPENWIN_PRODCTRL3.equals(scrnMsg.xxScrEventNm.getValue())) {
            setProdCtrl3(scrnMsg);
        } else if (OPENWIN_PRODCTRL4.equals(scrnMsg.xxScrEventNm.getValue())) {
            setProdCtrl4(scrnMsg);
        } else if (OPENWIN_PRODCTRL5.equals(scrnMsg.xxScrEventNm.getValue())) {
            setProdCtrl5(scrnMsg);
        } else if (OPENWIN_PRCGRPCUST_SOLD.equals(scrnMsg.xxScrEventNm.getValue())) {
            setPrcGrpCustSold(scrnMsg);
        } else if (OPENWIN_PRCLIST.equals(scrnMsg.xxScrEventNm.getValue())) {
            setPrcList(scrnMsg);
        } else if (OPENWIN_FREIGHTZONE.equals(scrnMsg.xxScrEventNm.getValue())) {
            setFreightZone(scrnMsg);
        // 2017/08/24 QC#20729 Add Start
        } else if (OPENWIN_SVCMDL.equals(scrnMsg.xxScrEventNm.getValue())) {
            setServiceModel(scrnMsg);
        // 2017/08/24 QC#20729 Add End
        // QC#20249 2017/11/10 Add Start
        } else if (OPENWIN_PRCGRPMAT_QTYBRK.equals(scrnMsg.xxScrEventNm.getValue())) {
            setPrcGrpMatQtyBrk(scrnMsg);
        // QC#20249 2017/11/10 Add End
        // 2018/04/19 add start
        } else if (OPENWIN_MATGRP1.equals(scrnMsg.xxScrEventNm.getValue())) {
            setMatGrp1(scrnMsg);
        } else if (OPENWIN_MATGRP2.equals(scrnMsg.xxScrEventNm.getValue())) {
            setMatGrp2(scrnMsg);
        } else if (OPENWIN_MATGRP3.equals(scrnMsg.xxScrEventNm.getValue())) {
            setMatGrp3(scrnMsg);
        }
        // 2018/04/19 add end


        scrnMsg.xxScrEventNm.clear();
    }

    private void setCsmpNum(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_04, scrnMsg.R.no(2).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).csmpNumCmntTxt_04, scrnMsg.R.no(6).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_04, scrnMsg.R.no(2).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).csmpNumCmntTxt_04, scrnMsg.R.no(6).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_04);
        // 2018/06/15 QC#22594 Mod End
    }

    private void setProdCtrl(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_06, scrnMsg.R.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prodCtrlNm_06, scrnMsg.R.no(1).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_06, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prodCtrlNm_06, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_06);
        // 2018/06/15 QC#22594 Mod End
    }

    private void setProdCtrl2(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_07, scrnMsg.R.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prodCtrlNm_07, scrnMsg.R.no(1).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_07, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prodCtrlNm_07, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_07);
        // 2018/06/15 QC#22594 Mod End
    }

    private void setCoaProd(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_09, scrnMsg.R.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).coaProdDescTxt_09, scrnMsg.R.no(1).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_09, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).coaProdDescTxt_09, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_09);
        // 2018/06/15 QC#22594 Mod End
    }

    private void setPrcGrpMat(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_05, scrnMsg.R.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcGrpNm_05, scrnMsg.R.no(1).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_05, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcGrpNm_05, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_05);
        // 2018/06/15 QC#22594 Mod End
    }

    private void setPrcGrpTrx(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_14, scrnMsg.R.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcGrpNm_14, scrnMsg.R.no(1).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_14, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcGrpNm_14, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_14);
        // 2018/06/15 QC#22594 Mod End
    }

    private void setFormula(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcFmlaPk_B1, new BigDecimal(scrnMsg.R.no(0).xxComnScrColValTxt.getValue()));
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcFmlaNm_B1, scrnMsg.R.no(2).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcFmlaPk_B1, new BigDecimal(scrnMsg.R.no(0).xxComnScrColValTxt.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcFmlaNm_B1, scrnMsg.R.no(2).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcFmlaPk_B1);
        // 2018/06/15 QC#22594 Mod End
    }
    private void setProdCtrl3(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_34, scrnMsg.R.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prodCtrlNm_34, scrnMsg.R.no(1).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_34, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prodCtrlNm_34, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_34);
        // 2018/06/15 QC#22594 Mod End
    }

    private void setProdCtrl4(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_35, scrnMsg.R.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prodCtrlNm_35, scrnMsg.R.no(1).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_35, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prodCtrlNm_35, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_35);
        // 2018/06/15 QC#22594 Mod End
    }

    private void setProdCtrl5(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_36, scrnMsg.R.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prodCtrlNm_36, scrnMsg.R.no(1).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_36, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prodCtrlNm_36, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_36);
        // 2018/06/15 QC#22594 Mod End
    }
    private void setPrcGrpCustSold(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_53, scrnMsg.R.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcGrpNm_53, scrnMsg.R.no(1).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_53, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcGrpNm_53, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_53);
        // 2018/06/15 QC#22594 Mod End
    }
    private void setPrcList(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_32, scrnMsg.R.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcCatgNm_32, scrnMsg.R.no(1).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_32, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcCatgNm_32, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_32);
        // 2018/06/15 QC#22594 Mod End
    }
    private void setFreightZone(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_49, scrnMsg.R.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).fill40Txt_49, scrnMsg.R.no(1).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_49, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).fill40Txt_49, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_49);
        // 2018/06/15 QC#22594 Mod End
    }

    // 2017/08/24 QC#20729 Add Start
    private void setServiceModel(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_40, scrnMsg.R.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).mdlDescTxt_40, scrnMsg.R.no(1).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_40, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).mdlDescTxt_40, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_40);
        // 2018/06/15 QC#22594 Mod End
    }
    // 2017/08/24 QC#20729 Add End
    // QC#20249 2017/11/10 Add Start
    private void setPrcGrpMatQtyBrk(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_57, scrnMsg.R.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcGrpNm_57, scrnMsg.R.no(1).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_57, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcGrpNm_57, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_57);
        // 2018/06/15 QC#22594 Mod End
    }
    // QC#20249 2017/11/10 Add End
    // 2018/04/19 QC#22569 add start
    private void setMatGrp1(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
     // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_59, scrnMsg.R.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).slsMatGrpDescTxt_59, scrnMsg.R.no(1).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_59, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).slsMatGrpDescTxt_59, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_59);
     // 2018/06/15 QC#22594 Mod End
    }

    private void setMatGrp2(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_60, scrnMsg.R.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).slsMatGrpDescTxt_60, scrnMsg.R.no(1).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_60, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).slsMatGrpDescTxt_60, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_60);
        // 2018/06/15 QC#22594 Mod End
    }

    private void setMatGrp3(NMAL7260BMsg scrnMsg) {
        int idx = scrnMsg.xxCellIdx.getValueInt();
        // 2018/06/15 QC#22594 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_61, scrnMsg.R.no(0).xxComnScrColValTxt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).slsMatGrpDescTxt_61, scrnMsg.R.no(1).xxComnScrColValTxt);
        if (!getLastGuard().equals("CMN_Close")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_61, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).slsMatGrpDescTxt_61, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_61);
        // 2018/06/15 QC#22594 Mod End
    }

    // 2018/04/19 QC#22569 add end
}
