/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1070;

import static business.servlet.NPAL1070.constant.NPAL1070Constant.BIZ_APP_ID;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_MDSE_CD;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_MRP_PLN_NM;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_RTL_SWH_CD;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_RTL_WH;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_RTL_WH_CD;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_SRC_RTL_SWH_CD;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_SRC_RTL_WH;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_SRC_RTL_WH_CD;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_SOURCE_WH_SWH;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.EVENT_ADDING_PLAN;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.NPAM0016E;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.NPAM0073E;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.NPAM1195E;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.SCRN_ID;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.ZZM9000E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1070.NPAL1070CMsg;
import business.servlet.NPAL1070.common.NPAL1070CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1070 Min-Max Planning Entry
 * Function Name : Add
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/24/2016   CITS            Keiichi Masaki  Create          N/A
 * 08/17/2017   CITS            S.Endo          Update          Sol#013(QC#18717)
 * 01/09/2018   CITS            T.Tokutomi      Update          QC#21885
 * 01/08/2019   CITS            T.Tokutomi      Update          QC#29761
 * 11/26/2019   CITS            M.Naito         Update          QC#54836
 * 2023/04/17   Hitachi         S.Dong          Update          QC#61348
 *</pre>
 */
public class NPAL1070Scrn00_Add extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;

        // mandatory check
        if (!ZYPCommonFunc.hasValue(scrnMsg.mrpPlnNm)) {
            scrnMsg.mrpPlnNm.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_NM_MRP_PLN_NM});
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.mdseCd)) {
            scrnMsg.mdseCd.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_NM_MDSE_CD});
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)) {
            scrnMsg.rtlWhCd.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_NM_RTL_WH_CD});
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd)) {
            scrnMsg.rtlSwhCd.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_NM_RTL_SWH_CD});
        }
        //08/17/2017 CITS S.Endo Mod Sol#013(QC#18717) START
        //if (PROCR_TP.WAREHOUSE.equals(scrnMsg.procrTpCd_SL.getValue())) {
        if (PROCR_TP.WAREHOUSE.equals(scrnMsg.procrTpCd_SL.getValue()) || PROCR_TP.WAREHOUSE_SUPPLIER.equals(scrnMsg.procrTpCd_SL.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.srcRtlWhCd)) {
                scrnMsg.srcRtlWhCd.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_NM_SRC_RTL_WH_CD});
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.srcRtlSwhCd)) {
                scrnMsg.srcRtlSwhCd.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_NM_SRC_RTL_SWH_CD});
            }
        }
        //08/17/2017 CITS S.Endo Mod Sol#013(QC#18717) END

        if (PROCR_TP.SUPPLIER.equals(scrnMsg.procrTpCd_SL.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.srcRtlWhCd)
                || (ZYPCommonFunc.hasValue(scrnMsg.srcRtlSwhCd))) {
                scrnMsg.srcRtlWhCd.setErrorInfo(1, NPAM0016E, new String[] {DISPLAY_SOURCE_WH_SWH});
                scrnMsg.srcRtlSwhCd.setErrorInfo(1, NPAM0016E, new String[] {DISPLAY_SOURCE_WH_SWH});
            }
        }

        // QC#21885 Add check.
        if (PROCR_TP.WAREHOUSE.equals(scrnMsg.procrTpCd_SL.getValue())) {
            String rtlWh = scrnMsg.rtlWhCd.getValue() + scrnMsg.rtlSwhCd.getValue();
            String srcWh = scrnMsg.srcRtlWhCd.getValue() + scrnMsg.srcRtlSwhCd.getValue();

            if (ZYPCommonFunc.hasValue(rtlWh) && rtlWh.equals(srcWh)) {
                scrnMsg.rtlWhCd.setErrorInfo(1, NPAM1195E, new String[] {DISPLAY_NM_RTL_WH, DISPLAY_NM_SRC_RTL_WH });
                scrnMsg.rtlSwhCd.setErrorInfo(1, NPAM1195E, new String[] {DISPLAY_NM_RTL_WH, DISPLAY_NM_SRC_RTL_WH });
                scrnMsg.srcRtlWhCd.setErrorInfo(1, NPAM1195E, new String[] {DISPLAY_NM_RTL_WH, DISPLAY_NM_SRC_RTL_WH });
                scrnMsg.srcRtlSwhCd.setErrorInfo(1, NPAM1195E, new String[] {DISPLAY_NM_RTL_WH, DISPLAY_NM_SRC_RTL_WH });
            }
        }

        // duplicate check
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).mdseCd_A1 == scrnMsg.mdseCd
                && scrnMsg.A.no(i).rtlWhCd_A1 == scrnMsg.rtlWhCd
                && scrnMsg.A.no(i).rtlSwhCd_A1 == scrnMsg.rtlSwhCd) {
                scrnMsg.mdseCd.setErrorInfo(1, NPAM0073E, new String[] {EVENT_ADDING_PLAN});
                scrnMsg.rtlWhCd.setErrorInfo(1, NPAM0073E, new String[] {EVENT_ADDING_PLAN});
                scrnMsg.rtlSwhCd.setErrorInfo(1, NPAM0073E, new String[] {EVENT_ADDING_PLAN});
            }
        }

        // START 2019/11/26 M.Naito [QC#54836,ADD] -- rollback#29761
        // QC#29761 Delete the day of week check.
        // START 2023/04/17 S.Dong [QC#61348,DEL]
//        if (!ZYPCommonFunc.hasValue(scrnMsg.rplshDlyFlg)
//            && !ZYPCommonFunc.hasValue(scrnMsg.rplshMonFlg)
//            && !ZYPCommonFunc.hasValue(scrnMsg.rplshTueFlg)
//            && !ZYPCommonFunc.hasValue(scrnMsg.rplshWedFlg)
//            && !ZYPCommonFunc.hasValue(scrnMsg.rplshThuFlg)
//            && !ZYPCommonFunc.hasValue(scrnMsg.rplshFriFlg)) {
//            scrnMsg.rplshDlyFlg.setErrorInfo(1, NMAM0835E, new String[] {});
//            scrnMsg.rplshMonFlg.setErrorInfo(1, NMAM0835E, new String[] {});
//            scrnMsg.rplshTueFlg.setErrorInfo(1, NMAM0835E, new String[] {});
//            scrnMsg.rplshWedFlg.setErrorInfo(1, NMAM0835E, new String[] {});
//            scrnMsg.rplshThuFlg.setErrorInfo(1, NMAM0835E, new String[] {});
//            scrnMsg.rplshFriFlg.setErrorInfo(1, NMAM0835E, new String[] {});
//        }
        // END 2023/04/17 S.Dong [QC#61348,DEL]
        // END 2019/11/26 M.Naito [QC#54836,ADD]

        scrnMsg.addCheckItem(scrnMsg.mrpPlnNm);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.srcRtlSwhCd);
        // START 2019/11/26 M.Naito [QC#54836,ADD] -- rollback#29761
        scrnMsg.addCheckItem(scrnMsg.rplshDlyFlg);
        scrnMsg.addCheckItem(scrnMsg.rplshMonFlg);
        scrnMsg.addCheckItem(scrnMsg.rplshTueFlg);
        scrnMsg.addCheckItem(scrnMsg.rplshWedFlg);
        scrnMsg.addCheckItem(scrnMsg.rplshThuFlg);
        scrnMsg.addCheckItem(scrnMsg.rplshFriFlg);
        // END 2019/11/26 M.Naito [QC#54836,ADD]
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;

        NPAL1070CMsg bizMsg = new NPAL1070CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;
        NPAL1070CMsg bizMsg = (NPAL1070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        NPAL1070CommonLogic.setAuthorityProtect(this, scrnMsg, functionList);

        if ("E".equals(bizMsg.getMessageKind()) || scrnMsg.mrpPlnNm.isError() || scrnMsg.mdseCd.isError() || scrnMsg.rtlWhCd.isError() || scrnMsg.rtlSwhCd.isError() || scrnMsg.srcRtlWhCd.isError() || scrnMsg.srcRtlSwhCd.isError()
                || scrnMsg.procrTpCd_SL.isError()) {
            scrnMsg.addCheckItem(scrnMsg.mrpPlnNm);
            scrnMsg.addCheckItem(scrnMsg.mdseCd);
            scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
            scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);
            scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd);
            scrnMsg.addCheckItem(scrnMsg.srcRtlSwhCd);
            scrnMsg.addCheckItem(scrnMsg.procrTpCd_SL);

            scrnMsg.putErrorScreen();
            // Set focus
            scrnMsg.setFocusItem(scrnMsg.A.no(0).mrpPlnNm_A1);
        } else {
            // Set focus
            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).mrpPlnNm_A1);
        }

        // Search Condition Clear
        scrnMsg.mdseCd.clear();
        scrnMsg.mdseDescShortTxt.clear();

    }
}
