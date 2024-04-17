/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import static business.servlet.NSAL0010.constant.NSAL0010Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0010.NSAL0010CMsg;
import business.servlet.NSAL0010.common.NSAL0010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/04   Hitachi         T.Tomita        Create          QC#3312
 * 2016/04/25   Hitachi         T.Tomita        Update          QC#5522
 * 2016/05/12   Hitachi         T.Tomita        Update          QC#7832
 * 2016/05/16   Hitachi         T.Tomita        Update          QC#4578
 * 2016/05/26   Hitachi         T.Tomita        Update          QC#8894
 * 2016/12/22   Hitachi         K.Kojima        Update          QC#16600
 */
public class NSAL0010_NSAL1240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/05/12 T.Tomita [QC#7832, MOD]
        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        NSAL0010CMsg bizMsg = new NSAL0010CMsg();

        if (OPENWIN_SERIALNUM.equals(scrnMsg.xxScrEventNm.getValue())) {
            setScrPrmSerialNum(scrnMsg);
        } else if (OPENWIN_PARENTSERIALNUM.equals(scrnMsg.xxScrEventNm.getValue())) {
            setScrPrmParentSerialNum(scrnMsg);
        } else if (OPENWIN_MACHIDA.equals(scrnMsg.xxScrEventNm.getValue())) {
            int rowNum = getButtonSelectNumber();
            // del start 2016/04/25 CSA Defect#5522
//            setScrPrmMachIdA(scrnMsg);
            // del end 2016/04/25 CSA Defect#5522
            setValue(scrnMsg.xxRowNum, BigDecimal.valueOf(rowNum));
        } else if (SEARCH.equals(scrnMsg.xxScrEventNm.getValue())) {
            setScrPrmSearch(scrnMsg);
        }

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // END 2016/05/12 T.Tomita [QC#7832, MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        getArgForSubScreen();

        // mod start 2016/04/25 CSA Defect#5522
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        // START 2016/05/26 T.Tomita [QC#8894, MOD]
        if (SEARCH.equals(scrnMsg.xxScrEventNm.getValue()) || OPENWIN_SERIALNUM.equals(scrnMsg.xxScrEventNm.getValue())) {
            if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxSetFlg_MM.getValue())) {
                NSAL0010CommonLogic.controlScreenFields(this, scrnMsg, bizMsg.getUserID(), true, getUserProfileService());
            }
            scrnMsg.setFocusItem(scrnMsg.serNum_H1);
        } else if (OPENWIN_LINKNEWCONFIG.equals(scrnMsg.xxScrEventNm.getValue()) || OPENWIN_MACHIDA.equals(scrnMsg.xxScrEventNm.getValue())) {
            NSAL0010CommonLogic.controlScreenFields(this, scrnMsg, bizMsg.getUserID(), true, getUserProfileService());
        }
        // END 2016/05/26 T.Tomita [QC#8894, MOD]
        // mod end 2016/04/25 CSA Defect#5522
    }

    private void setScrPrmSerialNum(NSAL0010BMsg scrnMsg) {
        // START 2016/05/26 T.Tomita [QC#8894, DEL]
//        if (hasValue(scrnMsg.O.no(0).serNum_O)) {
//            setValue(scrnMsg.serNum_H1, scrnMsg.O.no(0).serNum_O);
//        }
//        if (hasValue(scrnMsg.O.no(0).mdseCd_O)) {
//            setValue(scrnMsg.mdseCd_H1, scrnMsg.O.no(0).mdseCd_O);
//        }
        // END 2016/05/26 T.Tomita [QC#8894, DEL]
        if (hasValue(scrnMsg.O.no(0).svcMachMstrPk_O)) {
            setValue(scrnMsg.svcMachMstrPk_H1, scrnMsg.O.no(0).svcMachMstrPk_O);
        }
        // START 2016/05/26 T.Tomita [QC#8894, DEL]
//        if (hasValue(scrnMsg.O.no(0).svcMachMstrStsCd_O)) {
//            setValue(scrnMsg.svcMachMstrStsCd_H3, scrnMsg.O.no(0).svcMachMstrStsCd_O);
//        }
//        if (hasValue(scrnMsg.O.no(0).curLocNum_O)) {
//            // START 2016/05/16 T.Tomita [QC#4578, MOD]
//            setValue(scrnMsg.indCurLocNum_M3, scrnMsg.O.no(0).indCurLocNum_O);
//            // END 2016/05/16 T.Tomita [QC#4578, MOD]
//        }
        // END 2016/05/26 T.Tomita [QC#8894, DEL]
    }

    private void setScrPrmParentSerialNum(NSAL0010BMsg scrnMsg) {
        if (hasValue(scrnMsg.O.no(0).serNum_O)) {
            setValue(scrnMsg.prntSerNum_H1, scrnMsg.O.no(0).serNum_O);
        }
    }

    // del start 2016/04/25 CSA Defect#5522
//    private void setScrPrmMachIdA(NSAL0010BMsg scrnMsg) {
//        int rowNum = getButtonSelectNumber();
//        if (ZYPCommonFunc.hasValue(scrnMsg.O.no(0).serNum_O)) {
//            setValue(scrnMsg.A.no(rowNum).serNum_A, scrnMsg.O.no(0).serNum_O);
//        }
//        if (ZYPCommonFunc.hasValue(scrnMsg.O.no(0).mdseCd_O)) {
//            setValue(scrnMsg.A.no(rowNum).mdseCd_A, scrnMsg.O.no(0).mdseCd_O);
//        }
//        if (ZYPCommonFunc.hasValue(scrnMsg.O.no(0).svcMachMstrStsCd_O)) {
//            setValue(scrnMsg.A.no(rowNum).svcMachMstrStsCd_A, scrnMsg.O.no(0).svcMachMstrStsCd_O);
//        }
//        if (ZYPCommonFunc.hasValue(scrnMsg.O.no(0).svcMachMstrPk_O)) {
//            setValue(scrnMsg.A.no(rowNum).svcMachMstrPk_A1, scrnMsg.O.no(0).svcMachMstrPk_O);
//        }
//    }
    // del end 2016/04/25 CSA Defect#5522

    private void setScrPrmSearch(NSAL0010BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.O.no(0).serNum_O)) {
            setValue(scrnMsg.serNum_H1, scrnMsg.O.no(0).serNum_O);
            // START 2016/12/22 K.Kojima [QC#16600,ADD]
            setValue(scrnMsg.svcMachMstrPk_H1, scrnMsg.O.no(0).svcMachMstrPk_O);
            // END 2016/12/22 K.Kojima [QC#16600,ADD]
        }
    }

    // START 2016/05/12 T.Tomita [QC#7832, DEL]
//    private BigDecimal getSelectSvcConfigMstrPk(NSAL0010BMsg scrnMsg) {
//        if (hasValue(scrnMsg.svcConfigMstrPk_O)) {
//            return scrnMsg.svcConfigMstrPk_O.getValue();
//        }
//        return null;
//    }
//
//    private BigDecimal getSelectSvcMachMstrPk(NSAL0010BMsg scrnMsg) {
//        if (hasValue(scrnMsg.O.no(0).svcMachMstrPk_O)) {
//            return scrnMsg.O.no(0).svcMachMstrPk_O.getValue();
//        }
//        return null;
//    }
    // END 2016/05/12 T.Tomita [QC#7832, DEL]
}
