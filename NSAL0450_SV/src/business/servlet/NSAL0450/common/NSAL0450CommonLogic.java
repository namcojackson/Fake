/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0450.common;

import static business.servlet.NSAL0450.constant.NSAL0450Constant.SCREEN_ID;
import parts.common.EZDBMsgArray;
import business.servlet.NSAL0450.NSAL0450BMsg;
import business.servlet.NSAL0450.NSAL0450_ABMsg;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/27   Hitachi         T.Iwamoto       Create          QC#1759
 * 2019/01/22   Fujitsu         R.Nakamura      Update          QC#29782
 *</pre>
 */
public class NSAL0450CommonLogic {
    /**
     * set Row Colors
     * @param scrnMsg NSAL0450BMsg
     */
    public static void setRowColors(NSAL0450BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // Add Start 2019/01/22 QC#29782
    public static void cotrolDetailField(NSAL0450BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0450_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.mdseDescShortTxt.setInputProtected(true);
        }
    }
    // Add End 2019/01/22 QC#29782
}
