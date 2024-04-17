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

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID : NFAL0050Scrn00_CMN_Submit
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050Scrn00_CMN_Submit extends S21CommonHandler implements NFAL0050Constant {

    /** Singleton instance. */
    private NFAL0050CommonLogic common = new NFAL0050CommonLogic();

    @SuppressWarnings("unchecked")
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

        String notAppropriateCd = checkDCPair(scrnMsg);
        if (!notAppropriateCd.equals(BLANK)) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (notAppropriateCd.equals(scrnMsg.A.no(i).ajeLineIdxNum_A.getValue())) {
                    scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NFAM0047E", new String[] {notAppropriateCd });
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
                }
            }
        }

        checkPairForLineIndTp(scrnMsg);

        //---- start mod 2016/01/22
        if (scrnMsg.jrnlCatgCd.isClear()) {
            scrnMsg.jrnlCatgCd.setErrorInfo(1, "NFAM0052E", new String[] {"Journal Category" });
        }
        //---- end 2016/01/22

        scrnMsg.addCheckItem(scrnMsg.ajePtrnIndTpCd_1V);
        scrnMsg.addCheckItem(scrnMsg.ajePtrnIndTpCd_2V);
        scrnMsg.addCheckItem(scrnMsg.ajePtrnIndTpCd_3V);
        scrnMsg.addCheckItem(scrnMsg.ajePtrnActlCd_1V);
        scrnMsg.addCheckItem(scrnMsg.ajePtrnActlCd_2V);
        scrnMsg.addCheckItem(scrnMsg.ajePtrnActlCd_3V);

        scrnMsg.addCheckItem(scrnMsg.jrnlCatgCd);
        
        scrnMsg.putErrorScreen();
    }

    private String checkDCPair(NFAL0050BMsg scrnMsg) {

        // Check if Line Idx has a pair
        String notAppropriateCd = BLANK;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String lineIdxCd = scrnMsg.A.no(i).ajeLineIdxNum_A.getValue();
            if (countLineIndCd(scrnMsg, lineIdxCd) != 2) {
                // Invalid number of Line Idx
                return lineIdxCd;
            } else {
                notAppropriateCd = checkPair(scrnMsg, i);
                if (notAppropriateCd.length() > 0) {
                    return notAppropriateCd;
                }
            }
        }
        return notAppropriateCd;
    }

    private int countLineIndCd(NFAL0050BMsg scrnMsg, String lineIdxCd) {
        int count = 0;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (lineIdxCd.equals(scrnMsg.A.no(i).ajeLineIdxNum_A.getValue())) {
                count++;
            }
        }
        return count;
    }

    private String checkPair(NFAL0050BMsg scrnMsg, int index) {

        String lineIdxCd = scrnMsg.A.no(index).ajeLineIdxNum_A.getValue();
        String dcCode = scrnMsg.A.no(index).drCrTpCd_A.getValue();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (i == index) {
                continue;
            }
            if (lineIdxCd.equals(scrnMsg.A.no(i).ajeLineIdxNum_A.getValue()) && dcCode.equals(scrnMsg.A.no(i).drCrTpCd_A.getValue())) {
                return scrnMsg.A.no(i).ajeLineIdxNum_A.getValue();
            }
        }
        return BLANK;
    }

    private void checkPairForLineIndTp(NFAL0050BMsg scrnMsg) {

        // Compare Line Indicator Type with its pair
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            int index = findPairIndex(scrnMsg, i);
            if (index == -1) {
                return;
            } else {
                String lineIndTpNmDr = scrnMsg.A.no(i).ajeLineIndTpNm_A.getValue();
                String lineIndTpNmCr = scrnMsg.A.no(index).ajeLineIndTpNm_A.getValue();

                if (!lineIndTpNmDr.equals(lineIndTpNmCr)) {
                    scrnMsg.A.no(i).ajeLineIndTpNm_A.setErrorInfo(1, "NFAM0057E", new String[] {scrnMsg.A.no(i).ajeLineIdxNum_A.getValue() + DR_AND_CR, IND_TP_CD });
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).ajeLineIndTpNm_A);

                    scrnMsg.A.no(index).ajeLineIndTpNm_A.setErrorInfo(1, "NFAM0057E", new String[] {scrnMsg.A.no(index).ajeLineIdxNum_A.getValue() + DR_AND_CR, IND_TP_CD });
                    scrnMsg.addCheckItem(scrnMsg.A.no(index).ajeLineIndTpNm_A);
                    return;
                }
            }
        }
    }

    private int findPairIndex(NFAL0050BMsg scrnMsg, int index) {

        String lineIdxNum = scrnMsg.A.no(index).ajeLineIdxNum_A.getValue();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (i == index) {
                continue;
            } else {
                String pairOflineIdxNum = scrnMsg.A.no(i).ajeLineIdxNum_A.getValue();
                if (lineIdxNum.equals(pairOflineIdxNum)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        NFAL0050CMsg bizMsg = new NFAL0050CMsg();
        bizMsg.setBusinessID("NFAL0050");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

        // return null;
    }

    private boolean hasSegProdPtrn(NFAL0050BMsg scrnMsg) {

        // Find #PR or #PRBR in Br
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String ajeCoaBrCd = scrnMsg.A.no(i).ajeCoaBrCd_A.getValue();
            if (ajeCoaBrCd.equals(PR) || ajeCoaBrCd.equals(WH)) {
                scrnMsg.eventId_SG.setValue(ajeCoaBrCd);
                return true;
            }
        }
        // Find #PR or #PRBR in CC
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String ajeCoaCcCd = scrnMsg.A.no(i).ajeCoaCcCd_A.getValue();
            if (ajeCoaCcCd.equals(PR) || ajeCoaCcCd.equals(WH)) {
                scrnMsg.eventId_SG.setValue(ajeCoaCcCd);
                return true;
            }
        }
        // Find #PR or #PRBR in Ch
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String ajeCoaChCd = scrnMsg.A.no(i).ajeCoaChCd_A.getValue();
            if (ajeCoaChCd.equals(PR) || ajeCoaChCd.equals(WH)) {
                scrnMsg.eventId_SG.setValue(ajeCoaChCd);
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setResult("GoToNFAL0050");
        int meg = scrnMsg.getMessageType();
        // When no error message
        if (meg != ERROR) {

            // Go to NFAL0070
            // for #PR or #PRBR
            //---- mod 2016/01/22  no data for #PR, #PRBR for CSA
            /*
            if (hasSegProdPtrn(scrnMsg)) {
                setResult("GoToNFAL0070");

                Object[] param = new Object[2];
                param[0] = scrnMsg.ajeId;
                param[1] = scrnMsg.eventId_SG;
                // Set param
                setArgForSubScreen(param);
            }
            */
            //---- end 2016/01/22

            common.setSubmitDeleteBtn(scrnMsg, this);
            common.enableSearchAje(scrnMsg, this, false);
            common.setInputProtectedIndicatorList(scrnMsg, false);

            common.afterSearch(ctx, scrnMsg, this);
            common.setPasteMode(scrnMsg, this, false);
            // scrnMsg.ajeId.setInputProtected(true);
            // Sucess mesage
            scrnMsg.setMessageInfo("ZZM8100I");
        } else {
            // For Mutual Exclusion,
            // lets a user to redo search
            common.protectSearchableFileds(scrnMsg, this, false);
            scrnMsg.addCheckItem(scrnMsg.jrnlCatgCd);
        }
        common.clearMessageOnScrn(scrnMsg);
        common.setFocus(scrnMsg);
    }
}
