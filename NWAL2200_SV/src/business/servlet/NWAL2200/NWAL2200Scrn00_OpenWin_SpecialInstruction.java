/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.NWZM2274W;

//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL2200.NWAL2200CMsg;
import business.servlet.NWAL2200.common.NWAL2200CommonLogic;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

//import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2018/07/10   Fujitsu         T.Noguchi       Update          S21_NA#26661,26713
 * 2018/07/25   Fujitsu         Mz.Takahashi    Update          S21_NA#14307
 *</pre>
 */
public class NWAL2200Scrn00_OpenWin_SpecialInstruction extends S21CommonHandler {


    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CMsg bizMsg = new NWAL2200CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CMsg bizMsg = (NWAL2200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setResult(ZYPConstant.FLG_OFF_N);

        // Add Start 2018/07/25 S21_NA#14307
        if (ZYPConstant.FLG_ON_1.equals(scrnMsg.xxRqstFlg.getValue())){
            setResult(ZYPConstant.FLG_ON_1);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            setArgForSubScreen(NWAL2200CommonLogic.getArgForSubScreen(scrnMsg, this.getGlobalCompanyCode()));
            return;
        } else {
            scrnMsg.setMessageInfo(NWZM2274W);
            scrnMsg.putErrorScreen();
        }
        // Add End 2018/07/25 S21_NA#14307

        // Del Start 2018/07/25 S21_NA#14307
        //// Mod Start 2018/07/25 S21_NA#14307
        //setArgForSubScreen(NWAL2200CommonLogic.getArgForSubScreen(scrnMsg, this.getGlobalCompanyCode()));
        //// Mod End 2018/07/25 S21_NA#14307
        // Del End 2018/07/25 S21_NA#14307
    }

    // Del Start 2018/07/25 S21_NA#14307
    //private Serializable getArgForSubScreen(NWAL2200BMsg scrnMsg) {

    //    List<Object> parmeters = new ArrayList<Object>();

    //    // [0] : Global Company Code
    //    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, getGlobalCompanyCode());
    //    parmeters.add(scrnMsg.P.no(0).xxPopPrm);

    //    // [1] : Function ID
    //    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, BIZ_ID);
    //    parmeters.add(scrnMsg.P.no(1).xxPopPrm);

    //    // [2] : Function Category ID
    //    // 2018/07/10 S21_NA#26661,26713 Del Start
    //    // ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, "");
    //    // 2018/07/10 S21_NA#26661,26713 Del End
    //    parmeters.add(scrnMsg.P.no(2).xxPopPrm);

    //    // [3] : Transaction Type
    //    parmeters.add(scrnMsg.P.no(3).xxPopPrm);

    //    // [4] : Business Area
    //    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, "");
    //    parmeters.add(scrnMsg.P.no(4).xxPopPrm);

    //    // [5] : Customer Special Instruction Line Suffix
    //    parmeters.add("QL");

    //    // [6] : Customer Special Instruction Line
    //    int index = 0;
    //    ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).dsAcctNum_QL, scrnMsg.sellToCustCd);
    //    ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, "");
    //    ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index++).shipToCustCd_QL, "");

    //    ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).dsAcctNum_QL, "");
    //    ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, scrnMsg.billToCustCd);
    //    ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index++).shipToCustCd_QL, "");

    //    ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).dsAcctNum_QL, "");
    //    ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, "");
    //    ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index++).shipToCustCd_QL, scrnMsg.shipToCustCd);
    //    scrnMsg.Q.setValidCount(index);
    //    parmeters.add(scrnMsg.Q);

    //    // 2018/07/10 S21_NA#26661,26713 Add Start
    //    // [7] : Line of Business Code
    //    ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, scrnMsg.lineBizTpCd);
    //    parmeters.add(scrnMsg.P.no(5).xxPopPrm);
    //    // 2018/07/10 S21_NA#26661,26713 Add End

    //    return parmeters.toArray(new Object[0]);
    //}
    // Del End 2018/07/25 S21_NA#14307
}
