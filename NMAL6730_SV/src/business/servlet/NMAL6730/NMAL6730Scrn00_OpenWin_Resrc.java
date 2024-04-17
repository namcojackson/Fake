/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

import static business.servlet.NMAL6730.constant.NMAL6730Constant.NMAM8075E;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.ZZM9001E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6730.common.NMAL6730CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Fujitsu         N.Sugiura       Create          N/A
 * 2016/06/08   SRAA            Y.Chen          Update          QC#7781
 *</pre>
 */
public class NMAL6730Scrn00_OpenWin_Resrc extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // QC#7811
        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();
        NMAL6730_ABMsg aBMsg = scrnMsg.A.no(selectIdx);
        
        scrnMsg.addCheckItem(aBMsg.xxGenlFldAreaTxt_A1);
        scrnMsg.putErrorScreen();
        
        String psnCdList = aBMsg.xxGenlFldAreaTxt_A1.getValue();
        String[] psnCdArray = NMAL6730CommonLogic.splitByComma(psnCdList);
        for(String psnCd : psnCdArray){
            if(psnCd.length() > scrnMsg.Q.no(0).getAttr("psnCd_Q1").getDigit()){
                aBMsg.xxGenlFldAreaTxt_A1.setErrorInfo(1, ZZM9001E, new String[] {"Internal Email Review" });
                scrnMsg.addCheckItem(aBMsg.xxGenlFldAreaTxt_A1);
                break;
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

        Object[] params = NMAL6730CommonLogic.setParamForResourceSearchPopup(scrnMsg);
        setArgForSubScreen(params);

    }
}
