/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0090;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/22   Fujitsu         Mori            Create          N/A
 *</pre>
 */
public class NLBL0090Scrn00_OpenWin_Carrier extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.carrCd_H2);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;

        // 1. create parameters to open screen "NMAL6050:Common
        // Pop-up".
        List<EZDBStringItem> params = createNMAL6050Parameters(scrnMsg);
        setArgForSubScreen(params.toArray(new EZDBStringItem[0]));

        // 2. regist this event-name to BMsg.
        scrnMsg.xxScrEventNm.setValue(getClass().getSimpleName());
    }

    private List<EZDBStringItem> createNMAL6050Parameters(NLBL0090BMsg scrnMsg) {

        List<EZDBStringItem> param = new ArrayList<EZDBStringItem>();

        // [0]:TBL_NM
        scrnMsg.xxTblNm_PP.setValue("OTBD_CARR_V");
        param.add(scrnMsg.xxTblNm_PP);

        // [1]:TBL_CD_COL_NM
        scrnMsg.xxTblCdColNm_PP.setValue("CARR_CD");
        param.add(scrnMsg.xxTblCdColNm_PP);

        // [2]:TBL_NM_COL_NM
        scrnMsg.xxTblNmColNm_PP.setValue("CARR_NM");
        param.add(scrnMsg.xxTblNmColNm_PP);

        // [3]:TBL_SORT_COL_NM
        scrnMsg.xxTblSortColNm_PP.setValue("CARR_SORT_NUM");
        param.add(scrnMsg.xxTblSortColNm_PP);

        // [4]:SCR_NM
        scrnMsg.xxScrNm_PP.setValue("Carrier Popup");
        param.add(scrnMsg.xxScrNm_PP);

        // [5]:HDR_CD_LB_NM
        scrnMsg.xxHdrCdLbNm_PP.setValue("Carrier Code");
        param.add(scrnMsg.xxHdrCdLbNm_PP);

        // [6]:HDR_NM_LB_NM
        scrnMsg.xxHdrNmLbNm_PP.setValue("Carrier Name");
        param.add(scrnMsg.xxHdrNmLbNm_PP);

        // [7]:DTL_CD_LB_NM
        scrnMsg.xxDtlCdLbNm_PP.setValue("Carrier Code");
        param.add(scrnMsg.xxDtlCdLbNm_PP);

        // [8]:DTL_NM_LB_NM
        scrnMsg.xxDtlNmLbNm_PP.setValue("Carrier Name");
        param.add(scrnMsg.xxDtlNmLbNm_PP);

        // [9]:COND_CD
        param.add(scrnMsg.carrCd_H2);

        // [10]:COND_NM
        scrnMsg.xxCondNm_PP.clear();
        param.add(scrnMsg.xxCondNm_PP);

        return param;
    }
}
