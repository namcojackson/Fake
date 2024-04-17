/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0010.NFDL0010CMsg;
import business.servlet.NFDL0010.common.NFDL0010CommonLogic;
import business.servlet.NFDL0010.constant.NFDL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/06/29   Hitachi         K.Kojima        Update          QC#10166
 * 2016/07/08   Hitachi         K.Kojima        Update          QC#11417
 * 2016/12/27   Hitachi         E.Kameishi      Update          QC#16808
 * 2018/02/05   Hitachi         T.Tsuchida      Update          QC#23969
 * 2018/02/20   Fujitsu         T.Murai         Update          QC#21175
 *</pre>
 */
public class NFDL0010Scrn00_OnClick_Search extends S21CommonHandler implements NFDL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;
        // START 2018/02/05 T.Tsuchida [QC#23969,DEL]
//        // START 2017/01/19 E.Kameishi [QC#16808,ADD]
//        if (!ZYPCommonFunc.hasValue(scrnMsg.cltDispTpCd_H)) {
//            if(!ZYPCommonFunc.hasValue(scrnMsg.xxQueryFltrTxt_H2) && !ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_H)) {
//                scrnMsg.xxQueryFltrTxt_H2.setErrorInfo(1, "NFCM0850E");
//                scrnMsg.dsAcctNm_H.setErrorInfo(1, "NFCM0850E");
//            }
//        }
//        // END 2017/01/19E.Kameishi [QC#16808,ADD]
        // END 2018/02/05 T.Tsuchida [QC#23969,DEL]

        // START 2018/02/20 [QC#21175,ADD]
        if (!ZYPCommonFunc.hasValue(scrnMsg.cltDispTpCd_H) && !ZYPCommonFunc.hasValue(scrnMsg.cltPtfoNm_H) // 
                && !ZYPCommonFunc.hasValue(scrnMsg.xxQueryFltrTxt_H1) && !ZYPCommonFunc.hasValue(scrnMsg.cltPsnNm_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.xxQueryFltrTxt_H2) && !ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_H)) {
            scrnMsg.setMessageInfo(NFDM0047E);
            throw new EZDFieldErrorException();
        }
        // END 2018/02/20 [QC#21175,ADD]
        NFDL0010CommonLogic.checkInput_NFDL0010Scrn00_Search(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;

        NFDL0010CMsg bizMsg = new NFDL0010CMsg();
        bizMsg.setBusinessID("NFDL0010");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;
        NFDL0010CMsg bizMsg = (NFDL0010CMsg) cMsg;

        // START 2017/01/19 E.Kameishi [QC#16808,MOD]
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        S21TableColorController tblColor = new S21TableColorController("NFDL0010Scrn00", scrnMsg);
        tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);
        NFDL0010CommonLogic.initialize(this, scrnMsg);
        //tblColor.clearRowsBG(TABLE_A, scrnMsg.A);
        // START 2016/07/08 K.Kojima [QC#11417,ADD]
        //NFDL0010CommonLogic.checkInput_NFDL0010Scrn00_Search(scrnMsg);
        //scrnMsg.putErrorScreen();
        // END 2016/07/08 K.Kojima [QC#11417,ADD]
        // START 2016/06/29 K.Kojima [QC#10166,DEL]
        // NFDL0010CommonLogic.setTableColor(scrnMsg);
        // END 2016/06/29 K.Kojima [QC#10166,DEL]
        // tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);
        // END 2017/01/19 E.Kameishi [QC#16808,MOD]
    }
}
