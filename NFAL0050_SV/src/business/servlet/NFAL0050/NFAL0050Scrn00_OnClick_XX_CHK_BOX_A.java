/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0050;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL0050.common.NFAL0050CommonLogic;
import business.servlet.NFAL0050.constant.NFAL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID :
 * NFAL0050Scrn00_OnClick_XX_CHK_BOX_A
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050Scrn00_OnClick_XX_CHK_BOX_A extends S21CommonHandler implements NFAL0050Constant {

    /** Singleton instance. */
    private NFAL0050CommonLogic common = new NFAL0050CommonLogic();

    /** List<Integer> checkBoxList */
    private List<Integer> checkBoxList = new ArrayList<Integer>();

    /** int index */
    private int index = -1;

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (CHECKED.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                checkBoxList.add(i);
                index = i;
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

        // NFAL0050CMsg bizMsg = new NFAL0050CMsg();
        // bizMsg.setBusinessID("NFAL0050");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        // NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (index >= 0 && scrnMsg.A.getValidCount() <= scrnMsg.A.length()) {
            // Co
            scrnMsg.ajeCoaCmpyCd_3.setValue(getAjeCoaCmpyCd(scrnMsg, index));
            // Br
            //---- start mod 2016/01/21
            scrnMsg.ajeCoaBrCd.setValue(scrnMsg.A.no(index).ajeCoaBrCd_A.getValue());
            //---- end 2016/01/21
            // CC
            scrnMsg.ajeCoaCcCd.setValue(scrnMsg.A.no(index).ajeCoaCcCd_A.getValue());
            // Acct
            scrnMsg.ajeCoaAcctCd.setValue(scrnMsg.A.no(index).ajeCoaAcctCd_A.getValue());
            // Line Ind Type
            scrnMsg.ajeLineIndTpCd_3.setValue(scrnMsg.A.no(index).ajeLineIndTpCd_A.getValue());
            // Line Desc
            scrnMsg.ajeLineIdxDescTxt.setValue(scrnMsg.A.no(index).ajeLineIdxDescTxt_A.getValue());
            // Prod
            scrnMsg.ajeCoaProdCd.setValue(scrnMsg.A.no(index).ajeCoaProdCd_A.getValue());
            // Ch
            scrnMsg.ajeCoaChCd_3.setValue(getAjeCoaChCd(scrnMsg, index));
            // Affl
            scrnMsg.ajeCoaAfflCd.setValue(scrnMsg.A.no(index).ajeCoaAfflCd_A.getValue());
            // Proj
            scrnMsg.ajeCoaProjCd.setValue(scrnMsg.A.no(index).ajeCoaProjCd_A.getValue());
            // Ext
            scrnMsg.ajeCoaExtnCd_3.setValue(getAjeCoaExtnCd(scrnMsg, index));
        }
        if (checkBoxList.isEmpty()) {
            common.clearAddRow(scrnMsg);
            common.enableDeleteRow(scrnMsg, this, false);
        } else {
            common.enableDeleteRow(scrnMsg, this, true);
        }
        // Doesn't let add row when exceeding max row num
        common.enableAddRowButton(scrnMsg, this);
        common.setSelelctUnSelectAllBtn(scrnMsg, this);
        scrnMsg.setFocusItem(scrnMsg.ajeLineIdxNum_3);
    }

    private String getAjeCoaCmpyCd(NFAL0050BMsg scrnMsg, int rowNum) {
        String code = scrnMsg.A.no(rowNum).ajeCoaCmpyCd_A.getValue();
        for (int i = 0; i < scrnMsg.ajeCoaCmpyCd_1.length(); i++) {
            if (code.equals(scrnMsg.ajeCoaCmpyCd_1.no(i).getValue())) {
                return code;
            }
        }
        return BLANK;
    }

    /*
    private String getAjeCoaBrCd(NFAL0050BMsg scrnMsg, int rowNum) {
        String code = scrnMsg.A.no(rowNum).ajeCoaBrCd_A.getValue();
        for (int i = 0; i < scrnMsg.ajeCoaBrCd_1.length(); i++) {
            if (code.equals(scrnMsg.ajeCoaBrCd_1.no(i).getValue())) {
                return code;
            }
        }
        return BLANK;
    }
    */

    private String getAjeCoaChCd(NFAL0050BMsg scrnMsg, int rowNum) {
        String code = scrnMsg.A.no(rowNum).ajeCoaChCd_A.getValue();
        for (int i = 0; i < scrnMsg.ajeCoaChCd_1.length(); i++) {
            if (code.equals(scrnMsg.ajeCoaChCd_1.no(i).getValue())) {
                return code;
            }
        }
        return BLANK;
    }

    private String getAjeCoaExtnCd(NFAL0050BMsg scrnMsg, int rowNum) {
        String code = scrnMsg.A.no(rowNum).ajeCoaExtnCd_A.getValue();
        for (int i = 0; i < scrnMsg.ajeCoaExtnCd_1.length(); i++) {
            if (code.equals(scrnMsg.ajeCoaExtnCd_1.no(i).getValue())) {
                return code;
            }
        }
        return BLANK;
    }
}
