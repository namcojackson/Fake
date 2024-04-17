package business.servlet.NLBL0100.common;

import business.servlet.NLBL0100.NLBL0100BMsg;
import business.servlet.NLBL0100.constant.NLBL0100Constant;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class NLBL0100CommonLogic implements NLBL0100Constant {
    
    /**
     * Setup Background color for Search Result List. 
     * @param bMessage NLBL0100BMsg
     */
    public static void setTblBackColor(NLBL0100BMsg bMessage) {

        S21TableColorController tblColor = new S21TableColorController("NLBL0100Scrn00", bMessage);

        bMessage.clearGUIAttribute("NLBL0100Scrn00", "A_Table");

        tblColor.setAlternateRowsBG("A_Table", bMessage.A);
    }

    // 10/14/2015 add start
    /**
     * Set input protect 
     * @param bMessage NLBL0100BMsg
     */
    public static void setInputProtected(NLBL0100BMsg bMessage) {
        for (int i = 0; i < bMessage.A.getValidCount(); i++) {
            bMessage.A.no(i).podStsNm.setInputProtected(true);
            bMessage.A.no(i).podSrcTpNm.setInputProtected(true);
            bMessage.A.no(i).podExNm.setInputProtected(true);
            // 2016/04/13 add start QC6899
            bMessage.A.no(i).podWt.setAppFracDigit(2);
            // 2016/04/13 add end QC6899
        }
    }
    // 10/14/2015 add end
}
