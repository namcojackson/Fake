/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0010.NSAL0010CMsg;
//import business.servlet.NSAL0010.constant.NSAL0010Constant;

import business.servlet.NSAL0010.common.NSAL0010CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NSAL0010Scrn00_OpenWin_Country extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//        NSAL0010CommonLogic.clearParamNPAL6050(scrnMsg);
//        Object[] params = new Object[11];
//
//        // [0]:TBL_NM
//        scrnMsg.xxTblNm.setValue("CTRY");
//        // [1]:TBL_CD_COL_NM
//        scrnMsg.xxTblCdColNm.setValue("CTRY_CD");
//        // [2]:TBL_NM_COL_NM
//        scrnMsg.xxTblNmColNm.setValue("CTRY_NM");
//        // [3]:TBL_SORT_COL_NM
//        scrnMsg.xxTblSortColNm.setValue("CTRY_SORT_NUM");
//        // [4]:SCR_NM
//        scrnMsg.xxScrNm.setValue("Country Search");
//        // [5]:HDR_CD_LB_NM
//        scrnMsg.xxHdrCdLbNm.setValue("Country Code");
//        // [6]:HDR_NM_LB_NM
//        scrnMsg.xxHdrNmLbNm.setValue("Country Name");
//        // [7]:DTL_CD_LB_NM
//        scrnMsg.xxDtlCdLbNm.setValue("Country Code");
//        // [8]:DTL_NM_LB_NM
//        scrnMsg.xxDtlNmLbNm.setValue("Country Name");
//
//        params[0] = scrnMsg.xxTblNm;
//        params[1] = scrnMsg.xxTblCdColNm;
//        params[2] = scrnMsg.xxTblNmColNm;
//        params[3] = scrnMsg.xxTblSortColNm;
//        params[4] = scrnMsg.xxScrNm;
//        params[5] = scrnMsg.xxHdrCdLbNm;
//        params[6] = scrnMsg.xxHdrNmLbNm;
//        params[7] = scrnMsg.xxDtlCdLbNm;
//        params[8] = scrnMsg.xxDtlNmLbNm;
//        // [9]:COND_CD
//        params[9] = scrnMsg.ctryCd;
//        // [10]:COND_NM
//        params[10] = scrnMsg.ctryNm;
//
//        setArgForSubScreen(params);
    }
}
