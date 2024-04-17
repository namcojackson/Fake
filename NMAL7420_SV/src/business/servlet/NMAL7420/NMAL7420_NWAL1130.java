/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7420;

import static business.servlet.NMAL7420.constant.NMAL7420Constant.*;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.OPENWIN_FORMULA;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.OPENWIN_FREIGHTZONE;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.OPENWIN_PRCGRPCUST_SOLD;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.OPENWIN_PRCGRPMAT;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.OPENWIN_PRCGRPMATBRK;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.OPENWIN_PRCGRPTRX;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.OPENWIN_PRCLIST;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.OPENWIN_PRODCTRL;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.OPENWIN_PRODCTRL2;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.OPENWIN_PRODCTRL3;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.OPENWIN_PRODCTRL4;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.OPENWIN_PRODCTRL5;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.OPENWIN_SERVICEMDL;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/09   Fujitsu         K.Ishizuka      Create          N/A
 * 2018/04/24   Fujitsu         M.Ohno          Update          QC#22569
 * 2019/01/08   Fujitsu         Hd.Sugawara     Update          QC#29751
 *</pre>
 */
public class NMAL7420_NWAL1130 extends S21CommonHandler {

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

        NMAL7420BMsg scrnMsg = (NMAL7420BMsg) bMsg;

        // 2018/04/24 QC#22569 add start
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        // 2018/04/24 QC#22569 add end

        if (OPENWIN_CSMPNUM.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondFromTxt_04, scrnMsg.R.no(2).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.csmpNumCmntTxt_04, scrnMsg.R.no(6).xxComnScrColValTxt);
        } else if (OPENWIN_PRCGRPMAT.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondFromTxt_05, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcGrpNm_05, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (OPENWIN_PRODCTRL.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondFromTxt_06, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prodCtrlNm_06, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (OPENWIN_PRODCTRL2.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondFromTxt_07, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prodCtrlNm_07, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (OPENWIN_PRODCTRL3.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondFromTxt_34, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prodCtrlNm_34, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (OPENWIN_PRODCTRL4.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondFromTxt_35, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prodCtrlNm_35, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (OPENWIN_PRODCTRL5.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondFromTxt_36, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prodCtrlNm_36, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (OPENWIN_PRCGRPTRX.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondFromTxt_14, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcGrpNm_14, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (OPENWIN_PRCLIST.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondFromTxt_32, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm_32, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (OPENWIN_SERVICEMDL.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondFromTxt_40, scrnMsg.R.no(0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdlNm_40, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (OPENWIN_PRCGRPCUST_SOLD.equals(scrnMsg.xxScrEventNm.getValue())) {
            // Mod Start 2019/01/08 QC#29751
            //ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondFromTxt_53, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPrcQlfyValSrchTxt_53, scrnMsg.R.no(0).xxComnScrColValTxt);
            // Mod End 2019/01/08 QC#29751
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcGrpNm_53, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (OPENWIN_FREIGHTZONE.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondFromTxt_49, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.fill40Txt_49, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (OPENWIN_FORMULA.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcFmlaPk, new BigDecimal(scrnMsg.R.no(0).xxComnScrColValTxt.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcFmlaNm, scrnMsg.R.no(2).xxComnScrColValTxt);
        } else if (OPENWIN_PRCGRPMATBRK.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondFromTxt_57, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcGrpNm_57, scrnMsg.R.no(1).xxComnScrColValTxt);
        // 2018/04/24 QC#22569 add start
        } else if (OPENWIN_MATGRP1.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondFromTxt_59, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.slsMatGrpDescTxt_59, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (OPENWIN_MATGRP2.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondFromTxt_60, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.slsMatGrpDescTxt_60, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if (OPENWIN_MATGRP3.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondFromTxt_61, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.slsMatGrpDescTxt_61, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        // 2018/04/24 QC#22569 add end

        scrnMsg.xxScrEventNm.clear();

    }
}
