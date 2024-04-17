/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/06/22   Hitachi         K.Kojima        Update          QC#10529
 * 2018/04/03   Hitachi         J.Kim           Update          QC#24729
 * 2018/06/21   Hitachi         Y.Takeno        Update          QC#25080
 * 2022/08/30   Hitachi         M.Hashino       Update          QC#60404
 *</pre>
 */
public class NFDL0020Scrn00_Click_NoteNew extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2022/08/30 M.Hashino [QC#60404,ADD]
        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        scrnMsg.xxListNum_LY.setValue(0);
        // END 2022/08/30 M.Hashino [QC#60404,ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg; 
        scrnMsg.cltNoteDtlPk_FH.clear();
        scrnMsg.cratDt_FH.clear();
        scrnMsg.cratUsrId_FH.clear();
        scrnMsg.xxPsnNm_FH.clear();
        // START 2018/06/21 [QC#25080, MOD]
        // scrnMsg.arCltNoteTpCd_FH.clear();
        scrnMsg.cltNoteTpCd_FH.clear();
        // END  START 2018/06/21 [QC#25080, MOD]
        scrnMsg.colNoteSubjTxt_FH.clear();
        // START 2018/04/03 J.Kim [QC#25096,MOD]
        // scrnMsg.dtlNoteTxt_FH.clear();
        scrnMsg.xxMlBodyTxt_FH.clear();
        // END 2018/04/03 J.Kim [QC#25096,MOD]
        scrnMsg.xxYesNoCd_FH.setValue("1");
        // START 2018/06/21 [QC#25080, ADD]
        scrnMsg.cratTsDplyTxt_FH.clear();
        // START 2018/06/21 [QC#25080, ADD]

        // START 2018/06/21 [QC#25080, MOD]
        // scrnMsg.arCltNoteTpCd_FH.setInputProtected(false);
        scrnMsg.cltNoteTpCd_FH.setInputProtected(false);
        // END   2018/06/21 [QC#25080, MOD]
        scrnMsg.colNoteSubjTxt_FH.setInputProtected(false);
        // START 2018/04/03 J.Kim [QC#25096,MOD]
        // scrnMsg.dtlNoteTxt_FH.setInputProtected(false);
        scrnMsg.xxMlBodyTxt_FH.setInputProtected(false);
        // END 2018/04/03 J.Kim [QC#25096,MOD]

        // START 2016/06/22 K.Kojima [QC#10529,ADD]
        // START 2018/06/21 [QC#25080, MOD]
        // scrnMsg.setFocusItem(scrnMsg.arCltNoteTpCd_FH);
        scrnMsg.setFocusItem(scrnMsg.cltNoteTpCd_FH);
        // END   2018/06/21 [QC#25080, MOD]
        // END 2016/06/22 K.Kojima [QC#10529,ADD]
        // START 2018/07/09 J.Kim [QC#26801,ADD]
        setButtonProperties("Click_NoteAttachment", "Click_NoteAttachment", "Attachment", 0, null);
        // END 2018/07/09 J.Kim [QC#26801,ADD]
    }
}
