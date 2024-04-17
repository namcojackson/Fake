/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

import static business.servlet.NMAL6730.constant.NMAL6730Constant.NMAM8075E;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.ZZM9001E;

import java.math.BigDecimal;
import java.util.ArrayList;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6730.common.NMAL6730CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Fujitsu         N.Sugiura       Create          N/A
 * 2016/06/08   SRAA            Y.Chen          Update          QC#7781
 * 2017/07/19   Hitachi         J.Kim           Update          QC#19868
 * 2017/08/28   Fujitsu         H.Nagashima     Update          QC#20780
 *</pre>
 */
public class NMAL6730Scrn00_OpenWin_CtacPsn extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // QC#7811
        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();
        NMAL6730_ABMsg aBMsg = scrnMsg.A.no(selectIdx);
        
        scrnMsg.addCheckItem(aBMsg.xxGenlFldAreaTxt_A2);
        scrnMsg.putErrorScreen();
        
        String ctacPsnPkList = aBMsg.xxGenlFldAreaTxt_A2.getValue();
        String[] ctacPsnPkArray = NMAL6730CommonLogic.splitByComma(ctacPsnPkList);
        for(String ctacPsnPk : ctacPsnPkArray){
            if(!isNumber(ctacPsnPk)){
                aBMsg.xxGenlFldAreaTxt_A2.setErrorInfo(1, NMAM8075E, new String[] {"single byte numeric character, and can be separated by comma." });
                scrnMsg.addCheckItem(aBMsg.xxGenlFldAreaTxt_A2);
                break;
            } else {
                if(ctacPsnPk.length() > scrnMsg.Q.no(0).getAttr("ctacPsnPk_Q1").getDigit()){
                    aBMsg.xxGenlFldAreaTxt_A2.setErrorInfo(1, ZZM9001E, new String[] {"External Email Contact" });
                    scrnMsg.addCheckItem(aBMsg.xxGenlFldAreaTxt_A2);
                    break;
                }
            }
            
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIdx);
        NMAL6730_ABMsg aBMsg = scrnMsg.A.no(selectIdx);

        NMAL6730CommonLogic.clearParams(scrnMsg);
        
        // QC#7781
        ZYPTableUtil.clear(scrnMsg.Q);
        String ctacPsnPkList = aBMsg.xxGenlFldAreaTxt_A2.getValue();
        String[] ctacPsnPkArray = NMAL6730CommonLogic.splitByComma(ctacPsnPkList);
        EZDBBigDecimalItem[] inputs = new EZDBBigDecimalItem[scrnMsg.Q.length()];
        EZDBBigDecimalItem[] outputs = new EZDBBigDecimalItem[scrnMsg.Q.length()];
        for (int i = 0; i < scrnMsg.Q.length(); i++) {
            if (i < ctacPsnPkArray.length) {
                scrnMsg.Q.no(i).ctacPsnPk_Q1.setValue(new BigDecimal(ctacPsnPkArray[i]));
            }
            inputs[i] = scrnMsg.Q.no(i).ctacPsnPk_Q1;
            outputs[i] = scrnMsg.Q.no(i).ctacPsnPk_Q2;
        }

        Object[] params = new Object[33];
        // START 2017/07/19 J.Kim [QC#19868,MOD]
        // scrnMsg.xxPopPrm_P3.setValue(scrnMsg.dsAcctNum_H1.getValue());
        scrnMsg.xxPopPrm_P4.setValue(scrnMsg.locNum_H1.getValue());
        // END 2017/07/19 J.Kim [QC#19868,MOD]
        scrnMsg.xxPopPrm_PB.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxPopPrm_PC.setValue(ZYPConstant.FLG_ON_Y);
        // QC#7781
        scrnMsg.xxPopPrm_PJ.setValue("M");

        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.xxPopPrm_P2;
        params[3] = scrnMsg.xxPopPrm_P3;
        params[4] = scrnMsg.xxPopPrm_P4;
        params[5] = scrnMsg.xxPopPrm_P5;
        params[6] = scrnMsg.xxPopPrm_P6;
        params[7] = scrnMsg.xxPopPrm_P7;
        params[8] = scrnMsg.xxPopPrm_P8;
        params[9] = scrnMsg.xxPopPrm_P9;
        params[10] = scrnMsg.xxPopPrm_PA;
        params[11] = scrnMsg.xxPopPrm_PB;
        params[12] = scrnMsg.xxPopPrm_PC;
        params[13] = scrnMsg.xxPopPrm_PD;
        params[14] = scrnMsg.xxPopPrm_PE;
        params[15] = scrnMsg.ctacPsnPk_15;
        params[16] = scrnMsg.ctacPsnPk_16;
        params[17] = scrnMsg.ctacPsnPk_17;
        params[18] = new ArrayList<Object>();
        // QC#7781
        // params[19] = aBMsg.ctacPsnPk_A1;
        params[19] = null;
        params[20] = null;
        params[21] = null;
        params[22] = null;
        params[23] = null;
        params[24] = null;
        params[25] = null;
        params[26] = null;
        params[27] = null;
        params[28] = null;
        params[29] = scrnMsg.xxPopPrm_PJ;  // mode
        params[30] = inputs; 
        // QC#20780 mod Start
//        params[31] = outputs;
        params[31] = null;
        params[32] = outputs;
        // QC#20780 mod End

        setArgForSubScreen(params);

    }
    
    private boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!isNumber(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNumber(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }
}
