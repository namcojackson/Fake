/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSBL0010.constant.NSBL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 * 2016/06/16   Hitach          A.Kohinata      Update          QC#9677
 * 2016/11/08   Hitach          N.Arai          Update          QC#15784
 * 2016/11/15   Hitach          N.Arai          Update          QC#15784
 *</pre>
 */
public class NSBL0010Scrn00_OpenWin_ServiceMemo extends S21CommonHandler implements NSBL0010Constant {

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

// START 2016/11/07 N.Arai [QC#15784, MOD]
        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();

        EZDBStringItem item = null;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, SVC_MEMO_CATG.DISPATCH_MEMO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, MEMO_POP_LABEL_01);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, MEMO_POP_LABEL_02);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, MEMO_POP_LABEL_03);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, MEMO_POP_LABEL_04);
        // mod start 2016/06/16 QC#9677
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, MEMO_POP_COND_01);
        item = scrnMsg.A.no(selectIdx).svcTaskNum_A;
        EZDBStringItem item2 = null;
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIdx).fsrNum_A)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, MEMO_POP_COND_02);
            item2 = scrnMsg.A.no(selectIdx).fsrNum_A;
        } else {
            scrnMsg.xxPopPrm_P8.clear();
        }
        // mod end 2016/06/16 QC#9677
        scrnMsg.xxPopPrm_P7.clear();

// START 2016/11/15 N.Arai [QC#15784, MOD]
//        Object[] params = new Object[PARAM_21 + 1];
        Object[] params = new Object[PARAM_22 + 1];
// END 2016/11/15 N.Arai [QC#15784, MOD]
        // 1 : Service Memo Category Code
        params[PARAM_0] = scrnMsg.xxPopPrm_P1;
        // 2 : Service Memo Type Code
        params[PARAM_1] = scrnMsg.xxPopPrm_P7;
        // 3 : Service Memo First Column Label Name
        params[PARAM_2] = scrnMsg.xxPopPrm_P2;
        // 4 : Service Memo First Column Label Code
        params[PARAM_3] = scrnMsg.A.no(selectIdx).svcTaskNum_A;
        // 5 : Service Memo Second Column Label Name
        params[PARAM_4] = scrnMsg.xxPopPrm_P3;
        // 6 : Service Memo Second Column Label Code
        params[PARAM_5] = scrnMsg.A.no(selectIdx).fsrNum_A;
        // 7 : Service Memo Third Column Label Name
        params[PARAM_6] = scrnMsg.xxPopPrm_P4;
        // 8 : Service Memo Third Column Label Code
        params[PARAM_7] = scrnMsg.A.no(selectIdx).serNum_A;
        // 9 : Service Memo Fourth Column Label Name
        params[PARAM_8] = scrnMsg.xxPopPrm_P5;
        // 10 : Service Memo Fourth Column Label Code
        params[PARAM_9] = scrnMsg.A.no(selectIdx).mdlNm_A;
        // 11 : Service Memo Fifth Column Label Name
        params[PARAM_10] = scrnMsg.xxPopPrm_P7;
        // 12 : Service Memo Fifth Column Label Code
        params[PARAM_11] = scrnMsg.xxPopPrm_P7;
        // 13 : Service Memo First Condition Name
        params[PARAM_12] = scrnMsg.xxPopPrm_P6;
        // 14 : Service Memo First Condition Code
        params[PARAM_13] = item;
        // mod start 2016/06/16 QC#9677
        // 15 : Service Memo Second Condition Name
        params[PARAM_14] = scrnMsg.xxPopPrm_P8;
        // 16 : Service Memo Second Condition Code
        params[PARAM_15] = item2;
        // mod end 2016/06/16 QC#9677
        // 17 : Service Memo Third Condition Name
        params[PARAM_16] = scrnMsg.xxPopPrm_P7;
        // 18 : Service Memo Third Condition Code
        params[PARAM_17] = scrnMsg.xxPopPrm_P7;
        // 19 : Service Memo Fourth Condition Name
        params[PARAM_18] = scrnMsg.xxPopPrm_P7;
        // 20 : Service Memo Fourth Condition Code
        params[PARAM_19] = scrnMsg.xxPopPrm_P7;
        // 21 : Service Memo Fifth Condition Name
        params[PARAM_20] = scrnMsg.xxPopPrm_P7;
        // 22 : Service Memo Fifth Condition Code
        params[PARAM_21] = scrnMsg.xxPopPrm_P7;
// START 2016/11/15 N.Arai [QC#15784, MOD]
        // 23 : Screen arguments(Service Memo Read Only Flg)
        params[PARAM_22] = ZYPConstant.FLG_ON_Y;
// END 2016/11/15 N.Arai [QC#15784, MOD]

        setArgForSubScreen(params);
// END 2016/11/07 N.Arai [QC#15784, MOD]
    }
}
