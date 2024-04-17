/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0010.common.NSAL0010CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2013/09/05   SRA             E.Inada         Update          QC#2135
 *</pre>
 */
public class NSAL0010Scrn00_OpenWin_Tech extends S21CommonHandler {

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
//        scrnMsg.xxTblNm.setValue("TECH_MSTR");
//        // [1]:TBL_CD_COL_NM
//        scrnMsg.xxTblCdColNm.setValue("TECH_TOC_CD");
//        // [2]:TBL_NM_COL_NM
//        scrnMsg.xxTblNmColNm.setValue("TECH_NM");
//        // [3]:TBL_SORT_COL_NM
//        scrnMsg.xxTblSortColNm.setValue("TECH_TOC_CD");
//        // [4]:SCR_NM
//        scrnMsg.xxScrNm.setValue("Technician Serach");
//        // [5]:HDR_CD_LB_NM
//        scrnMsg.xxHdrCdLbNm.setValue("Technician Code");
//        // [6]:HDR_NM_LB_NM
//        scrnMsg.xxHdrNmLbNm.setValue("Technician Name");
//        // [7]:DTL_CD_LB_NM
//        scrnMsg.xxDtlCdLbNm.setValue("Technician Code");
//        // [8]:DTL_NM_LB_NM
//        scrnMsg.xxDtlNmLbNm.setValue("Technician Name");
//
//        scrnMsg.xxPopPrm_11.clear();
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
//
//        // [9]:COND_CD
//        params[9] = scrnMsg.prfTechCd;
//        // [10]:COND_NM
//        params[10] = scrnMsg.xxPopPrm_11;
//
//        setArgForSubScreen(params);
    }
}
