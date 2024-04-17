/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1010;

import static business.servlet.NPAL1010.constant.NPAL1010Constant.COL_ST_CD;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.COL_ST_NM;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.EVENT_NM_SEARCH_STATECODE;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.SCREEN_STATE_CD_SEARCH;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.TBL_ST;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1010.common.NPAL1010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NPAL1010 Location Info Pop Up
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/06/26   Fujitsu         S.Noguchi       Create          N/A
 *</pre>
 */
public class NPAL1010Scrn00_Search_StateCode extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do Nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;
        scrnMsg.stNm.clear();

        NPAL1010CommonLogic.clearParamNPAL6050(scrnMsg);

        Object[] params = new Object[11];

        // [0]:TBL_NM
        scrnMsg.xxTblNm.setValue(TBL_ST);
        // [1]:TBL_CD_COL_NM
        scrnMsg.xxTblCdColNm.setValue(COL_ST_CD);
        // [2]:TBL_NM_COL_NM
        scrnMsg.xxTblNmColNm.setValue(COL_ST_NM);
        // [3]:TBL_SORT_COL_NM
        scrnMsg.xxTblSortColNm.setValue(COL_ST_CD);
        // [4]:SCR_NM
        scrnMsg.xxScrNm.setValue(SCREEN_STATE_CD_SEARCH);
        // [5]:HDR_CD_LB_NM
        scrnMsg.xxHdrCdLbNm.setValue(COL_ST_CD);
        // [6]:HDR_NM_LB_NM
        scrnMsg.xxHdrNmLbNm.setValue(COL_ST_NM);
        // [7]:DTL_CD_LB_NM
        scrnMsg.xxDtlCdLbNm.setValue(COL_ST_CD);
        // [8]:DTL_NM_LB_NM
        scrnMsg.xxDtlNmLbNm.setValue(COL_ST_NM);

        params[0] = scrnMsg.xxTblNm;
        params[1] = scrnMsg.xxTblCdColNm;
        params[2] = scrnMsg.xxTblNmColNm;
        params[3] = scrnMsg.xxTblSortColNm;
        params[4] = scrnMsg.xxScrNm;
        params[5] = scrnMsg.xxHdrCdLbNm;
        params[6] = scrnMsg.xxHdrNmLbNm;
        params[7] = scrnMsg.xxDtlCdLbNm;
        params[8] = scrnMsg.xxDtlNmLbNm;

        // [9]:ST_CD
        params[9] = scrnMsg.stCd;
        // [10]:ST_NM
        params[10] = scrnMsg.stNm;

        // Set Event Name.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_EV, EVENT_NM_SEARCH_STATECODE);

        setArgForSubScreen(params);

        scrnMsg.setFocusItem(scrnMsg.stCd);
    }
}
