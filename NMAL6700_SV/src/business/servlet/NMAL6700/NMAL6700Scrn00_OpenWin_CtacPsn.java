/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import static business.servlet.NMAL6700.constant.NMAL6700Constant.NMAM8075E;
import static business.servlet.NMAL6700.constant.NMAL6700Constant.ZZM9001E;

import java.math.BigDecimal;
import java.util.ArrayList;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6700.common.NMAL6700CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/06   Fujitsu         N.Sugiura       Update          QC##6633
 * 2017/08/28   Fujitsu         H.Nagashima     Update          QC#20780
 * 2017/09/14   Hitachi         J.Kim           Update          QC#21083
 *</pre>
 */
public class NMAL6700Scrn00_OpenWin_CtacPsn extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIdx);
        NMAL6700_GBMsg gBMsg = scrnMsg.G.no(selectIdx);
        scrnMsg.addCheckItem(gBMsg.xxGenlFldAreaTxt_G2);
        scrnMsg.putErrorScreen();

        String ctacPsnPkList = gBMsg.xxGenlFldAreaTxt_G2.getValue();
        String[] ctacPsnPkArray = NMAL6700CommonLogic.splitByComma(ctacPsnPkList);
        for (String ctacPsnPk : ctacPsnPkArray) {
            if (!isNumber(ctacPsnPk)) {
                gBMsg.xxGenlFldAreaTxt_G2.setErrorInfo(1, NMAM8075E, new String[] {"single byte numeric character, and can be separated by comma." });
                scrnMsg.addCheckItem(gBMsg.xxGenlFldAreaTxt_G2);
                break;
            } else {
                if (ctacPsnPk.length() > scrnMsg.Q.no(0).getAttr("ctacPsnPk_Q1").getDigit()) {
                    gBMsg.xxGenlFldAreaTxt_G2.setErrorInfo(1, ZZM9001E, new String[] {"External Email Contact" });
                    scrnMsg.addCheckItem(gBMsg.xxGenlFldAreaTxt_G2);
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

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        scrnMsg.xxScrEventNm.clear();
        scrnMsg.dsAcctNm_P.clear();

        int selectIdx = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIdx);
        NMAL6700_GBMsg gBMsg = scrnMsg.G.no(selectIdx);

        NMAL6700CommonLogic.clearParams(scrnMsg);

        ZYPTableUtil.clear(scrnMsg.Q);
        String ctacPsnPkList = gBMsg.xxGenlFldAreaTxt_G2.getValue();
        String[] ctacPsnPkArray = NMAL6700CommonLogic.splitByComma(ctacPsnPkList);
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
        scrnMsg.xxPopPrm_P3.setValue(scrnMsg.dsAcctNum_H1.getValue());
        scrnMsg.xxPopPrm_PB.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxPopPrm_PC.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxPopPrm_P2.setValue("M");

        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.dsAcctNm_P;
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
        params[29] = scrnMsg.xxPopPrm_P2;  // mode
        // START 2017/09/14 J.Kim [QC#21083,ADD]
        params[30] = inputs;
        // END 2017/09/14 J.Kim [QC#21083,ADD]

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
