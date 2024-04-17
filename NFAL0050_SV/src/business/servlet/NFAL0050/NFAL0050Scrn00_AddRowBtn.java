/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0050.NFAL0050CMsg;
import business.servlet.NFAL0050.common.NFAL0050CommonLogic;
import business.servlet.NFAL0050.constant.NFAL0050Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

import parts.common.EZDDebugOutput;

/**
 * Class name: Screen Component ID : NFAL0050Scrn00_AddRowBtn
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050Scrn00_AddRowBtn extends S21CommonHandler implements NFAL0050Constant {

    /** Singleton instance. */
    private NFAL0050CommonLogic common = new NFAL0050CommonLogic();

    /** Index of Idx/DC */
    private int addIndex = -1;

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.jrnlCatgNm);

        scrnMsg.addCheckItem(scrnMsg.ajeCoaCcCd);
        scrnMsg.addCheckItem(scrnMsg.ajeCoaAcctCd);
        scrnMsg.addCheckItem(scrnMsg.ajeCoaProdCd);
        scrnMsg.addCheckItem(scrnMsg.ajeCoaAfflCd);
        scrnMsg.addCheckItem(scrnMsg.ajeCoaProjCd);

        scrnMsg.addCheckItem(scrnMsg.ajeLineIdxNum_3);
        scrnMsg.addCheckItem(scrnMsg.ajeCoaCmpyCd_3);
      //---- start mod 2016/01/21
        scrnMsg.addCheckItem(scrnMsg.ajeCoaBrCd);
        //---- end 2016/01/21

        scrnMsg.addCheckItem(scrnMsg.ajeCoaChCd_3);
        scrnMsg.addCheckItem(scrnMsg.ajeCoaExtnCd_3);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        NFAL0050CMsg bizMsg = new NFAL0050CMsg();
        
        addIndex = getExistingIndDCIndex(scrnMsg);
                
        bizMsg.setBusinessID("NFAL0050");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

        // return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.ajeCoaCcCd);
        scrnMsg.addCheckItem(scrnMsg.ajeCoaAcctCd);
        scrnMsg.addCheckItem(scrnMsg.ajeCoaProdCd);
        scrnMsg.addCheckItem(scrnMsg.ajeCoaAfflCd);
        scrnMsg.addCheckItem(scrnMsg.ajeCoaProjCd);

        scrnMsg.putErrorScreen();

        if (addIndex == -1) {
            // if Idx/DC is a new i.e. (not in the list)
            // the new line will be added one atfer last row
            addIndex = scrnMsg.A.getValidCount();
                
            scrnMsg.A.setValidCount(addIndex + 1);
            
        }

        scrnMsg.A.no(addIndex).ajeLineIdxNum_A.setValue(common.getLineIdxNum(scrnMsg));
        scrnMsg.A.no(addIndex).drCrTpCd_A.setValue(common.getDrCrTpCd(scrnMsg));
        scrnMsg.A.no(addIndex).ajeCoaCmpyCd_A.setValue(scrnMsg.ajeCoaCmpyCd_3.getValue());
        //---- start mod 2016/01/21
        scrnMsg.A.no(addIndex).ajeCoaBrCd_A.setValue(scrnMsg.ajeCoaBrCd.getValue());
        //---- end 2016/01/21
        scrnMsg.A.no(addIndex).ajeCoaCcCd_A.setValue(scrnMsg.ajeCoaCcCd.getValue());
        scrnMsg.A.no(addIndex).ajeCoaAcctCd_A.setValue(scrnMsg.ajeCoaAcctCd.getValue());

        scrnMsg.A.no(addIndex).ajeLineIndTpCd_A.setValue(scrnMsg.ajeLineIndTpCd_3.getValue());
        scrnMsg.A.no(addIndex).ajeLineIndTpNm_A.setValue(getLineIndTpNm(cMsg, scrnMsg.ajeLineIndTpCd_3.getValue()));
        scrnMsg.A.no(addIndex).ajeLineIndTpNm_A.setInputProtected(true);

        scrnMsg.A.no(addIndex).ajeLineIdxDescTxt_A.setValue(scrnMsg.ajeLineIdxDescTxt.getValue());
        scrnMsg.A.no(addIndex).ajeLineIdxDescTxt_A.setInputProtected(true);
        scrnMsg.A.no(addIndex).ajeCoaProdCd_A.setValue(scrnMsg.ajeCoaProdCd.getValue());
        scrnMsg.A.no(addIndex).ajeCoaChCd_A.setValue(scrnMsg.ajeCoaChCd_3.getValue());
        scrnMsg.A.no(addIndex).ajeCoaAfflCd_A.setValue(scrnMsg.ajeCoaAfflCd.getValue());
        scrnMsg.A.no(addIndex).ajeCoaProjCd_A.setValue(scrnMsg.ajeCoaProjCd.getValue());
        scrnMsg.A.no(addIndex).ajeCoaExtnCd_A.setValue(scrnMsg.ajeCoaExtnCd_3.getValue());

        common.clearAddRow(scrnMsg);

        // scrnMsg.A.setValidCount(addIndex + 1);

        // Doesn't let add row when exceeding max row num
        common.enableAddRowButton(scrnMsg, this);

        // Clear checked box
        common.clearChkBoxes(scrnMsg);
        // common.enableAddRow(scrnMsg, this, false);
        common.setSubmitDeleteBtn(scrnMsg, this);
        // Doesn't let editable for indicator list boxes
        common.setInputProtectedIndicatorList(scrnMsg, true);
        common.protectSearchableFileds(scrnMsg, this, true);
        common.setSelelctUnSelectAllBtn(scrnMsg, this);
        common.changeTableColorByRow(ctx, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.ajeLineIdxNum_3);
    }

    /*
     * Return AJE_LINE_IND_TP_NM based on AJE_LINE_IND_TP_CD
     */
    private String getLineIndTpNm(EZDCMsg cMsg, String lineIndTpCdParam) {

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
        for (int i = 0; i < bizMsg.ajeLineIndTpCd_1.length(); i++) {
            if (bizMsg.ajeLineIndTpCd_1.no(i).getValue().equals(lineIndTpCdParam)) {
                return bizMsg.ajeLineIndTpNm_2.no(i).getValue();
            }
        }
        return BLANK;
    }

    /*
     * Return the index of Ind/DC to add or update if exist
     */
    private int getExistingIndDCIndex(NFAL0050BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String ajeLineIdxNum = scrnMsg.A.no(i).ajeLineIdxNum_A.getValue();
            String drCrTpCd = scrnMsg.A.no(i).drCrTpCd_A.getValue();
            if (ZYPCommonFunc.hasValue(ajeLineIdxNum) && ajeLineIdxNum.length() > 0) {
                ajeLineIdxNum = Integer.toString(Integer.parseInt(ajeLineIdxNum));
            }
            String idxDc = ajeLineIdxNum + drCrTpCd;
            if (idxDc.equals(scrnMsg.ajeLineIdxNum_3.getValue())) {
                return i;
            }
        }
        return -1;
    }
}
