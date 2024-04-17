/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL2200.common.NWAL2200CommonLogic;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_OpenWin_FreightTerms
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2017/02/24   Fujitsu         Y.Kanefusa      Update          S21_NA#13688
 *</pre>
 */
public class NWAL2200Scrn00_OpenWin_FreightTerms extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        // QC#13688 2017/02/24 Mod Start
        // Object[] params = getParamNMAL6050ForFrtTerm(scrnMsg);
        Object[] params = NWAL2200CommonLogic.getParamNMAL6050ForFrtTerm(scrnMsg);
        // QC#13688 2017/02/24 Mod End
        setArgForSubScreen(params);
    }

    /**
     * Get Param NMAL6050 For Freight Term
     * @param scrnMsg NWAL2200BMsg
     * @return Param NMAL6050
     */
    public static Object[] getParamNMAL6050ForFrtTerm(NWAL2200BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();

        // [0]:TBL_NM
        scrnMsg.P.no(0).xxPopPrm.setValue("FRT_COND");
        paramList.add(scrnMsg.P.no(0).xxPopPrm);

        // [1]:TBL_CD_COL_NM
        scrnMsg.P.no(1).xxPopPrm.setValue("FRT_COND_CD");
        paramList.add(scrnMsg.P.no(1).xxPopPrm);

        // [2]:TBL_NM_COL_NM
        scrnMsg.P.no(2).xxPopPrm.setValue("FRT_COND_DESC_TXT");
        paramList.add(scrnMsg.P.no(2).xxPopPrm);

        // [3]:TBL_SORT_COL_NM
        scrnMsg.P.no(3).xxPopPrm.setValue("FRT_COND_SORT_NUM");
        paramList.add(scrnMsg.P.no(3).xxPopPrm);

        // [4]:SCR_NM
        scrnMsg.P.no(4).xxPopPrm.setValue("Freight Terms Search");
        paramList.add(scrnMsg.P.no(4).xxPopPrm);

        // [5]:HDR_CD_LB_NM
        scrnMsg.P.no(5).xxPopPrm.setValue("Freight Terms Code");
        paramList.add(scrnMsg.P.no(5).xxPopPrm);

        // [6]:HDR_NM_LB_NM
        scrnMsg.P.no(6).xxPopPrm.setValue("Freight Terms Name");
        paramList.add(scrnMsg.P.no(6).xxPopPrm);

        // [7]:DTL_CD_LB_NM
        scrnMsg.P.no(7).xxPopPrm.setValue("Freight Terms Code");
        paramList.add(scrnMsg.P.no(7).xxPopPrm);

        // [8]:DTL_NM_LB_NM
        scrnMsg.P.no(8).xxPopPrm.setValue("Freight Terms Name");
        paramList.add(scrnMsg.P.no(8).xxPopPrm);

        // [9]:COND_CD
        scrnMsg.P.no(9).xxPopPrm.clear();
        paramList.add(scrnMsg.frtCondCd);

        // [10]:COND_NM
        paramList.add(scrnMsg.frtCondDescTxt);

        return paramList.toArray(new EZDBItem[0]);
    }
}
