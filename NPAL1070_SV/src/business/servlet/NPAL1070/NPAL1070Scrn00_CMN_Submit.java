/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1070;

import static business.servlet.NPAL1070.constant.NPAL1070Constant.BIZ_APP_ID;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_MAX_QTY;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_MIN_QTY;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_REORDER_QTY;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.SCRN_ID;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.NPAM1656W;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.NPAM1657W;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1070.NPAL1070CMsg;
import business.servlet.NPAL1070.common.NPAL1070CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1070 Min-Max Planning Entry
 * Function Name : Common Submit
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/24/2016   CITS            Keiichi Masaki  Create          N/A
 * 12/05/2016   CITS            S.Endo          Update          QC#16380
 * 08/17/2017   CITS            S.Endo          Update          Sol#013(QC#18717)
 * 01/08/2019   CITS            T.Tokutomi      Update          QC#29761
 * 11/26/2019   CITS            M.Naito         Update          QC#54836
 * 2023/04/17   Hitachi         S.Dong          Update          QC#61348
 *</pre>
 */
public class NPAL1070Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.mrpPlnNm);

        // START 2019/11/26 M.Naito [QC#54836,ADD] -- rollback#29761
        // QC#29761 Delete the day of week check.
        // START 2023/04/17 S.Dong [QC#61348,DEL]
//        if (!ZYPCommonFunc.hasValue(scrnMsg.rplshDlyFlg)
//        && !ZYPCommonFunc.hasValue(scrnMsg.rplshMonFlg)
//        && !ZYPCommonFunc.hasValue(scrnMsg.rplshTueFlg)
//        && !ZYPCommonFunc.hasValue(scrnMsg.rplshWedFlg)
//        && !ZYPCommonFunc.hasValue(scrnMsg.rplshThuFlg)
//        && !ZYPCommonFunc.hasValue(scrnMsg.rplshFriFlg)) {
//            scrnMsg.rplshDlyFlg.setErrorInfo(1, "NMAM0835E");
//            scrnMsg.rplshMonFlg.setErrorInfo(1, "NMAM0835E");
//            scrnMsg.rplshTueFlg.setErrorInfo(1, "NMAM0835E");
//            scrnMsg.rplshWedFlg.setErrorInfo(1, "NMAM0835E");
//            scrnMsg.rplshThuFlg.setErrorInfo(1, "NMAM0835E");
//            scrnMsg.rplshFriFlg.setErrorInfo(1, "NMAM0835E");
//        }
        // END 2023/04/17 S.Dong [QC#61348,DEL]
        // END 2019/11/26 M.Naito [QC#54836,ADD]

        int i = 0;
        for (; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).ropQty_A1);
            scrnMsg.A.no(i).ropQty_A1.setNameForMessage(DISPLAY_MIN_QTY);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).maxInvtyQty_A1);
            scrnMsg.A.no(i).maxInvtyQty_A1.setNameForMessage(DISPLAY_MAX_QTY);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).ovrdMaxInvtyQty_A1);
            scrnMsg.A.no(i).ovrdMaxInvtyQty_A1.setNameForMessage(DISPLAY_REORDER_QTY);
        }

        //08/17/2017 CITS S.Endo Add Sol#013(QC#18717) START
        scrnMsg.addCheckItem(scrnMsg.rplshDlyFlg);
        scrnMsg.addCheckItem(scrnMsg.rplshMonFlg);
        scrnMsg.addCheckItem(scrnMsg.rplshTueFlg);
        scrnMsg.addCheckItem(scrnMsg.rplshWedFlg);
        scrnMsg.addCheckItem(scrnMsg.rplshThuFlg);
        scrnMsg.addCheckItem(scrnMsg.rplshFriFlg);
        //08/17/2017 CITS S.Endo Add Sol#013(QC#18717) START
        scrnMsg.putErrorScreen();
        // START 2023/04/17 S.Dong [QC#61348,ADD]
        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxWrnSkipFlg_SB.getValue()) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSelFlg.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.rplshDlyFlg)
                && !ZYPCommonFunc.hasValue(scrnMsg.rplshMonFlg)
                && !ZYPCommonFunc.hasValue(scrnMsg.rplshTueFlg)
                && !ZYPCommonFunc.hasValue(scrnMsg.rplshWedFlg)
                && !ZYPCommonFunc.hasValue(scrnMsg.rplshThuFlg)
                && !ZYPCommonFunc.hasValue(scrnMsg.rplshFriFlg)) {
                scrnMsg.rplshDlyFlg.setErrorInfo(2, NPAM1656W);
                scrnMsg.rplshMonFlg.setErrorInfo(2, NPAM1656W);
                scrnMsg.rplshTueFlg.setErrorInfo(2, NPAM1656W);
                scrnMsg.rplshWedFlg.setErrorInfo(2, NPAM1656W);
                scrnMsg.rplshThuFlg.setErrorInfo(2, NPAM1656W);
                scrnMsg.rplshFriFlg.setErrorInfo(2, NPAM1656W);
                scrnMsg.setMessageInfo(NPAM1657W);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_SB, ZYPConstant.FLG_ON_Y);
                scrnMsg.addCheckItem(scrnMsg.rplshDlyFlg);
                scrnMsg.addCheckItem(scrnMsg.rplshMonFlg);
                scrnMsg.addCheckItem(scrnMsg.rplshTueFlg);
                scrnMsg.addCheckItem(scrnMsg.rplshWedFlg);
                scrnMsg.addCheckItem(scrnMsg.rplshThuFlg);
                scrnMsg.addCheckItem(scrnMsg.rplshFriFlg);
                scrnMsg.putErrorScreen();
                throw new EZDFieldErrorException();
            }
        }
        // END 2023/04/17 S.Dong [QC#61348,ADD]   
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;
        NPAL1070CMsg bizMsg = new NPAL1070CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;
        NPAL1070CMsg bizMsg  = (NPAL1070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        scrnMsg.addCheckItem(scrnMsg.mrpPlnNm);

        scrnMsg.addCheckItem(scrnMsg.rplshDlyFlg);
        scrnMsg.addCheckItem(scrnMsg.rplshMonFlg);
        scrnMsg.addCheckItem(scrnMsg.rplshTueFlg);
        scrnMsg.addCheckItem(scrnMsg.rplshWedFlg);
        scrnMsg.addCheckItem(scrnMsg.rplshThuFlg);
        scrnMsg.addCheckItem(scrnMsg.rplshFriFlg);

        int i = 0;
        for (i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).srcRtlWhCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).srcRtlSwhCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).ropQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).maxInvtyQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).ovrdMaxInvtyQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).procrTpCd_AS);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mrpEnblFlg_A1);

        }

        scrnMsg.putErrorScreen();

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NPAL1070CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);
        // START 2023/04/17 S.Dong [QC#61348,ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_SB, ZYPConstant.FLG_OFF_N);
        // END 2023/04/17 S.Dong [QC#61348,ADD]

        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }
        // Set Focus (Plan Name)
        scrnMsg.setFocusItem(scrnMsg.mrpPlnNm);

    }
}
