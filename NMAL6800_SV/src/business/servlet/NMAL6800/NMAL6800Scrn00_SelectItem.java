/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6800;


import static business.servlet.NMAL6800.constant.NMAL6800Constant.BUSINESS_ID;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_00;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_01;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_02;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_03;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_04;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_05;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_06;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_07;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_08;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.IDX_09;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6800.NMAL6800CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL6800Scrn00_SelectItem
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         E.Yoshitake     Create          N/A
 *</pre>
 */
public class NMAL6800Scrn00_SelectItem extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6800BMsg scrnMsg = (NMAL6800BMsg) bMsg;
        NMAL6800CMsg bizMsg = new NMAL6800CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6800BMsg scrnMsg = (NMAL6800BMsg) bMsg;
        NMAL6800CMsg bizMsg = (NMAL6800CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        Object[] params = (Object[]) getArgForSubScreen();
        if (params instanceof Object[]) {
            int index = getButtonSelectNumber();

            // Item Number
            EZDBStringItem param0 = (EZDBStringItem) params[IDX_00];
            ZYPEZDItemValueSetter.setValue(param0, scrnMsg.A.no(index).mdseCd_A1);

            // Item Description
            EZDBStringItem param1 = (EZDBStringItem) params[IDX_01];
            ZYPEZDItemValueSetter.setValue(param1, scrnMsg.A.no(index).mdseNm_A1);

            // Item Type
            EZDBStringItem param2 = (EZDBStringItem) params[IDX_02];
            ZYPEZDItemValueSetter.setValue(param2, scrnMsg.A.no(index).mdseItemTpCd_A1);

            // Item Classification
            EZDBStringItem param3 = (EZDBStringItem) params[IDX_03];
            ZYPEZDItemValueSetter.setValue(param3, scrnMsg.A.no(index).mdseItemClsTpCd_A1);

            // Merchandise Type
            EZDBStringItem param4 = (EZDBStringItem) params[IDX_04];
            ZYPEZDItemValueSetter.setValue(param4, scrnMsg.A.no(index).coaMdseTpCd_A1);

            // COA Product Code
            EZDBStringItem param5 = (EZDBStringItem) params[IDX_05];
            ZYPEZDItemValueSetter.setValue(param5, scrnMsg.A.no(index).coaProdCd_A1);

            // Planning Group
            EZDBStringItem param6 = (EZDBStringItem) params[IDX_06];
            ZYPEZDItemValueSetter.setValue(param6, scrnMsg.A.no(index).prchGrpCd_A1);
            
            if (params.length > 7) {
                EZDBStringItem param7 = (EZDBStringItem) params[IDX_07];
                ZYPEZDItemValueSetter.setValue(param7, scrnMsg.A.no(index).mdseDescShortTxt_A1);
            }
            if (params.length > 8) {
                EZDBStringItem param8 = (EZDBStringItem) params[IDX_08];
                ZYPEZDItemValueSetter.setValue(param8, scrnMsg.A.no(index).mdseDescLongTxt_A1);
            }
            if (params.length > 9) {
                EZDBStringItem param9 = (EZDBStringItem) params[IDX_09];
                ZYPEZDItemValueSetter.setValue(param9, scrnMsg.xxModeCd_H1);
            }
        }
    }
}
