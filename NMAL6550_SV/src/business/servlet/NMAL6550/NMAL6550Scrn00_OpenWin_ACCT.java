/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.servlet.NMAL6550;


import java.util.ArrayList;
import java.util.List;

import parts.common.*;
import parts.servletcommon.*;

import business.db.IMPT_INV_CNSGNTMsg;
import business.servlet.NMAL6550.constant.NMAL6550Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6550Scrn00_OpenWin_ACCT extends S21CommonHandler implements NMAL6550Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6550BMsg scrnMsg = (NMAL6550BMsg) bMsg;

        int eventOccuredLine = getButtonSelectNumber();
        scrnMsg.xxNum.setValue(eventOccuredLine);

        // create parameters to open [NMAL6050:Common Pop-up].
        List<EZDBStringItem> params = createNMAL6050Parameters(scrnMsg, eventOccuredLine);
        setArgForSubScreen(params.toArray(new EZDBStringItem[0]));

        // regist this event-name to BMsg.
        scrnMsg.xxScrEventNm.setValue(getClass().getSimpleName());

	}

    private List<EZDBStringItem> createNMAL6050Parameters(NMAL6550BMsg scrnMsg, int line) {

        List<EZDBStringItem> param = new ArrayList<EZDBStringItem>();

        EZDBStringItem paramItem;

        // [0]:TBL_NM
        paramItem = scrnMsg.xxTblNm_PP;
        paramItem.setValue(new IMPT_INV_CNSGNTMsg().getTableName());
        param.add(paramItem);

        // [1]:TBL_CD_COL_NM
        paramItem = scrnMsg.xxTblCdColNm_PP;
        paramItem.setValue(scrnMsg.xxTblNm_PP.getValue() + "_CD");
        param.add(paramItem);

        // [2]:TBL_NM_COL_NM
        paramItem = scrnMsg.xxTblNmColNm_PP;
        paramItem.setValue(scrnMsg.xxTblNm_PP.getValue() + "_NM");
        param.add(paramItem);

        // [3]:TBL_SORT_COL_NM
        paramItem = scrnMsg.xxTblSortColNm_PP;
        paramItem.setValue(scrnMsg.xxTblNm_PP.getValue() + "_CD");
        param.add(paramItem);

        // [4]:SCR_NM
        paramItem = scrnMsg.xxScrNm_PP;
        paramItem.setValue("Import Invoice Consignee Search");
        param.add(paramItem);

        // [5]:HDR_CD_LB_NM
        paramItem = scrnMsg.xxHdrCdLbNm_PP;
        paramItem.setValue("Import Invoice Consignee Code");
        param.add(paramItem);

        // [6]:HDR_NM_LB_NM
        paramItem = scrnMsg.xxHdrNmLbNm_PP;
        paramItem.setValue("Import Invoice Consignee Name");
        param.add(paramItem);

        // [7]:DTL_CD_LB_NM
        paramItem = scrnMsg.xxDtlCdLbNm_PP;
        paramItem.setValue(scrnMsg.xxHdrCdLbNm_PP.getValue());
        param.add(paramItem);

        // [8]:DTL_NM_LB_NM
        paramItem = scrnMsg.xxDtlNmLbNm_PP;
        paramItem.setValue(scrnMsg.xxHdrNmLbNm_PP.getValue());
        param.add(paramItem);

        // [9]:COND_CD
        paramItem = scrnMsg.xxCondCd_PP;
        paramItem.setValue(scrnMsg.A.no(line).acctCd_A1.getValue());
        param.add(paramItem);

        // [10]:COND_NM
        paramItem = scrnMsg.xxCondNm_PP;
        paramItem.clear();
        param.add(paramItem);

        return param;
    }

    
}
