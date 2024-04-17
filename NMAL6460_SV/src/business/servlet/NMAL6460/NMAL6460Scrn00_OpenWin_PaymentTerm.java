/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.NMAL6460;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6460.constant.NMAL6460Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6460Scrn00_OpenWin_PaymentTerm extends S21CommonHandler implements NMAL6460Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6460BMsg scrnMsg = (NMAL6460BMsg) bMsg;

        scrnMsg.xxListNum.setValue(getButtonSelectNumber());
        
		Object[] params = new Object[11];
        
        // [0]:TBL_NM
        scrnMsg.xxTblNm.setValue(PMT_TERM.class.getSimpleName());
        // [1]:TBL_CD_COL_NM
        scrnMsg.xxTblCdColNm.setValue(scrnMsg.xxTblNm.getValue() + "_CD");
        // [2]:TBL_NM_COL_NM
        scrnMsg.xxTblNmColNm.setValue(scrnMsg.xxTblNm.getValue() + "_NM");
        // [3]:TBL_SORT_COL_NM
        scrnMsg.xxTblSortColNm.setValue(scrnMsg.xxTblNm.getValue() + "_SORT_NUM");
        // [4]:SCR_NM
        scrnMsg.xxScrNm.setValue("Payment Term Search");
        // [5]:HDR_CD_LB_NM
        scrnMsg.xxHdrCdLbNm.setValue("Payment Term Code");
        // [6]:HDR_NM_LB_NM
        scrnMsg.xxHdrNmLbNm.setValue("Payment Term Name");
        // [7]:DTL_CD_LB_NM
        scrnMsg.xxDtlCdLbNm.setValue("Payment Term Code");
        // [8]:DTL_NM_LB_NM
        scrnMsg.xxDtlNmLbNm.setValue("Payment Term Name");
            
        params[0] = scrnMsg.xxTblNm;
        params[1] = scrnMsg.xxTblCdColNm;
        params[2] = scrnMsg.xxTblNmColNm;
        params[3] = scrnMsg.xxTblSortColNm;
        params[4] = scrnMsg.xxScrNm;
        params[5] = scrnMsg.xxHdrCdLbNm;
        params[6] = scrnMsg.xxHdrNmLbNm;
        params[7] = scrnMsg.xxDtlCdLbNm;
        params[8] = scrnMsg.xxDtlNmLbNm;
        
        // [9]:COND_CD
        params[9] = scrnMsg.A.no(getButtonSelectNumber()).pmtTermCd_A1;
        // [10]:COND_NM
        params[10] = scrnMsg.A.no(getButtonSelectNumber()).pmtTermNm_A1;
        
        setArgForSubScreen(params);
	}

}
