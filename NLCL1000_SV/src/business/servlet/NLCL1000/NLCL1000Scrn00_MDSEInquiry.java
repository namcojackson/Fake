/**
 *<pre>
 * Copyright (c) 2012 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.servlet.NLCL1000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL1000.constant.NLCL1000Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Stock Over View Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/07/17   Fujitsu         Y.Mori          Create          N/A
 *</pre>
 */
public class NLCL1000Scrn00_MDSEInquiry extends S21CommonHandler implements NLCL1000Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // nothing to do.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // nothing to do.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

         NLCL1000BMsg scrnMsg = (NLCL1000BMsg) bMsg;

         // TODO start
//         clearPopUpParam(scrnMsg);
//         scrnMsg.xxPopPrm_01.setValue(TBL_NM_MDSE);
//         scrnMsg.xxPopPrm_02.setValue(TBL_CD_COLUMN_CD_MDSE);
//         scrnMsg.xxPopPrm_03.setValue(TBL_CD_COLUMN_NM_MDSE);
//         scrnMsg.xxPopPrm_04.setValue(TBL_SORT_NUM_COLUMN_NM_MDSE);
//         scrnMsg.xxPopPrm_05.setValue(SCR_NM_MDSE);
//         scrnMsg.xxPopPrm_06.setValue(HDR_CD_LABLE_MDSE);
//         scrnMsg.xxPopPrm_07.setValue(HDR_NM_LABEL_MDSE);
//         scrnMsg.xxPopPrm_08.setValue(DTL_CD_LABEL_MDSE);
//         scrnMsg.xxPopPrm_09.setValue(DTL_NM_LABEL_MDSE);
//         scrnMsg.xxPopPrm_11.setValue(STR_BLANK);
//         scrnMsg.xxPopPrm_12.setValue(RGTN_STS_CD);
//         scrnMsg.xxPopPrm_13.setValue(SRCH_FUNC_CD);
//
//         Object[] params = new Object[13];
//         params[0] = scrnMsg.xxPopPrm_01;
//         params[1] = scrnMsg.xxPopPrm_02;
//         params[2] = scrnMsg.xxPopPrm_03;
//         params[3] = scrnMsg.xxPopPrm_04;
//         params[4] = scrnMsg.xxPopPrm_05;
//         params[5] = scrnMsg.xxPopPrm_06;
//         params[6] = scrnMsg.xxPopPrm_07;
//         params[7] = scrnMsg.xxPopPrm_08;
//         params[8] = scrnMsg.xxPopPrm_09;
//         params[9] = scrnMsg.mdseCd;
//         params[10] = scrnMsg.xxPopPrm_11;
//         params[11] = scrnMsg.xxPopPrm_12;
//         params[12] = scrnMsg.xxPopPrm_13;
//
//         setArgForSubScreen(params);
         // TODO end
    }

}
