/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/01   SRA             Otsuji          Create          N/A
 *</pre>
 */
public class NSBL0040Scrn00_OpenWin_Memo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0040BMsg scrnMsg = (NSBL0040BMsg) bMsg;

        // Open NSBL0160
        int index = getButtonSelectNumber();

        ZYPEZDItemValueSetter.setValue(scrnMsg.svcMemoCatgCd_P1, SVC_MEMO_CATG.DISPATCH_MEMO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcMemoTpCd_P1, SVC_MEMO_TP.CREDIT);

        // Label
        //MOD START 11/19/2015 for CSA
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_H1, "Task Num");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_H1, "FSR Num");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_L1, scrnMsg.A.no(index).svcTaskNum_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_L1, scrnMsg.A.no(index).fsrNum_A1);
        //MOD END 11/19/2015 for CSA
        scrnMsg.xxComnScrColLbTxt_H2.clear();
        scrnMsg.xxComnScrColLbTxt_L2.clear();
        scrnMsg.xxComnScrColLbTxt_H3.clear();
        scrnMsg.xxComnScrColLbTxt_L3.clear();
        scrnMsg.xxComnScrColLbTxt_H4.clear();
        scrnMsg.xxComnScrColLbTxt_L4.clear();
        scrnMsg.xxComnScrColLbTxt_H5.clear();
        scrnMsg.xxComnScrColLbTxt_L5.clear();
        // Search criteria
        //MOD START 11/19/2015 for CSA
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_C1, "SVC_TASK_NUM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_V1, scrnMsg.A.no(index).svcTaskNum_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_C1, "FSR_NUM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_V1, scrnMsg.A.no(index).fsrNum_A1);
        //MOD END 11/19/2015 for CSA
        scrnMsg.xxComnScrColLbTxt_C2.clear();
        scrnMsg.xxComnScrColLbTxt_V2.clear();
        scrnMsg.xxComnScrColLbTxt_C3.clear();
        scrnMsg.xxComnScrColLbTxt_V3.clear();
        scrnMsg.xxComnScrColLbTxt_C4.clear();
        scrnMsg.xxComnScrColLbTxt_V4.clear();
        scrnMsg.xxComnScrColLbTxt_C5.clear();
        scrnMsg.xxComnScrColLbTxt_V5.clear();

        Object[] args = new Object[22];

        int i = 0;
        args[i++] = scrnMsg.svcMemoCatgCd_P1;
        args[i++] = scrnMsg.svcMemoTpCd_P1;
        args[i++] = scrnMsg.xxComnScrColLbTxt_H1;
        args[i++] = scrnMsg.xxComnScrColLbTxt_L1;
        args[i++] = scrnMsg.xxComnScrColLbTxt_H2;
        args[i++] = scrnMsg.xxComnScrColLbTxt_L2;
        args[i++] = scrnMsg.xxComnScrColLbTxt_H3;
        args[i++] = scrnMsg.xxComnScrColLbTxt_L3;
        args[i++] = scrnMsg.xxComnScrColLbTxt_H4;
        args[i++] = scrnMsg.xxComnScrColLbTxt_L4;
        args[i++] = scrnMsg.xxComnScrColLbTxt_H5;
        args[i++] = scrnMsg.xxComnScrColLbTxt_L5;
        args[i++] = scrnMsg.xxComnScrColLbTxt_C1;
        args[i++] = scrnMsg.xxComnScrColLbTxt_V1;
        args[i++] = scrnMsg.xxComnScrColLbTxt_C2;
        args[i++] = scrnMsg.xxComnScrColLbTxt_V2;
        args[i++] = scrnMsg.xxComnScrColLbTxt_C3;
        args[i++] = scrnMsg.xxComnScrColLbTxt_V3;
        args[i++] = scrnMsg.xxComnScrColLbTxt_C4;
        args[i++] = scrnMsg.xxComnScrColLbTxt_V4;
        args[i++] = scrnMsg.xxComnScrColLbTxt_C5;
        args[i++] = scrnMsg.xxComnScrColLbTxt_V5;

        setArgForSubScreen(args);
    }
}
