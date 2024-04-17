/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0010.common.NSAL0010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/11/20   Hitachi         T.Tomita        Update          QC#949
 * 2016/09/26   Hitachi         Y.Zhang         Update          QC#12582
 *</pre>
 */
public class NSAL0010Scrn00_MoveWin_SvcMemo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        int rowCnt = getButtonSelectNumber();

        NSAL0010CommonLogic.clearPopupParameter(scrnMsg);
        setParams(scrnMsg, rowCnt);

        Object[] params = new Object[23];

        params[0] = scrnMsg.xxPopPrm_00;
        params[1] = scrnMsg.xxPopPrm_01;
        params[2] = scrnMsg.xxPopPrm_02;
        params[3] = scrnMsg.xxPopPrm_03;
        params[4] = scrnMsg.xxPopPrm_04;
        params[5] = scrnMsg.xxPopPrm_05;
        params[6] = scrnMsg.xxPopPrm_06;
        params[7] = scrnMsg.xxPopPrm_07;
        params[8] = scrnMsg.xxPopPrm_08;
        params[9] = scrnMsg.xxPopPrm_09;
        params[10] = scrnMsg.xxPopPrm_10;
        params[11] = scrnMsg.xxPopPrm_11;
        params[12] = scrnMsg.xxPopPrm_12;
        params[13] = scrnMsg.xxPopPrm_13;
        params[14] = scrnMsg.xxPopPrm_14;
        params[15] = scrnMsg.xxPopPrm_15;
        params[16] = scrnMsg.xxPopPrm_16;
        params[17] = scrnMsg.xxPopPrm_17;
        params[18] = scrnMsg.xxPopPrm_18;
        params[19] = scrnMsg.xxPopPrm_19;
        params[20] = scrnMsg.xxPopPrm_20;
        params[21] = scrnMsg.xxPopPrm_21;
        params[22] = scrnMsg.xxPopPrm_22;

        setArgForSubScreen(params);
    }

    private void setParams(NSAL0010BMsg scrnMsg, int rowCnt) {
        setValue(scrnMsg.xxPopPrm_00, SVC_MEMO_CATG.MACHINE_MEMO);
        scrnMsg.xxPopPrm_01.clear();
        setValue(scrnMsg.xxPopPrm_02, "Serial Num");
        setValue(scrnMsg.xxPopPrm_03, scrnMsg.serNum_H1);
        // START 2016/09/26 Y.Zhang [QC#12582, MOD]
        setValue(scrnMsg.xxPopPrm_04, "Item Code");
        // END 2016/09/26 Y.Zhang [QC#12582, MOD] 
        setValue(scrnMsg.xxPopPrm_05, scrnMsg.mdseCd_H1);
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        setValue(scrnMsg.xxPopPrm_12, "SVC_MACH_MSTR_PK");
        setValue(scrnMsg.xxPopPrm_13, scrnMsg.svcMachMstrPk_H1.getValue().toString());
        setValue(scrnMsg.xxPopPrm_14, "SVC_MEMO_TRX_NUM");
        setValue(scrnMsg.xxPopPrm_15, scrnMsg.I.no(rowCnt).svcMachMstrTrkPk_I.getValue().toString());
        setValue(scrnMsg.xxPopPrm_16, "SVC_MEMO_TRX_NM");
        setValue(scrnMsg.xxPopPrm_17, "SVC_MACH_MSTR_TRK_PK");
        setValue(scrnMsg.xxPopPrm_18, "");
        setValue(scrnMsg.xxPopPrm_19, "");
        setValue(scrnMsg.xxPopPrm_20, "");
        setValue(scrnMsg.xxPopPrm_21, "");
        setValue(scrnMsg.xxPopPrm_22, ZYPConstant.FLG_OFF_N);
    }
}
