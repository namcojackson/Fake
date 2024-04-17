/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2018/04/03   Hitachi         J.Kim           Update          QC#24729
 * 2018/06/21   Hitachi         Y.Takeno        Update          QC#25080
 *</pre>
 */
public class NFDL0020Scrn00_Click_NoteDetail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltNoteDtlPk_FH, scrnMsg.F.no(selectIdx).cltNoteDtlPk_FD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cratDt_FH, scrnMsg.F.no(selectIdx).cratDt_FD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cratUsrId_FH, scrnMsg.F.no(selectIdx).cratUsrId_FD);
        // START 2018/04/03 J.Kim [QC#25096,MOD]
        // ZYPEZDItemValueSetter.setValue(scrnMsg.dtlNoteTxt_FH, scrnMsg.F.no(selectIdx).dtlNoteTxt_FD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMlBodyTxt_FH, scrnMsg.F.no(selectIdx).xxMlBodyTxt_FD);
        // END 2018/04/03 J.Kim [QC#25096,MOD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.colNoteSubjTxt_FH, scrnMsg.F.no(selectIdx).colNoteSubjTxt_FD);
        // START 2018/06/21 [QC#25080, MOD]
        // ZYPEZDItemValueSetter.setValue(scrnMsg.arCltNoteTpCd_FH, scrnMsg.F.no(selectIdx).arCltNoteTpCd_FD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cltNoteTpCd_FH, scrnMsg.F.no(selectIdx).cltNoteTpCd_FD);
        // END   2018/06/21 [QC#25080, MOD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_FH, scrnMsg.F.no(selectIdx).xxPsnNm_FD);
        // START 2018/04/03 J.Kim [QC#25096,MOD]
        // ZYPEZDItemValueSetter.setValue(scrnMsg.dtlNoteTxt_FH, scrnMsg.F.no(selectIdx).dtlNoteTxt_FD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMlBodyTxt_FH, scrnMsg.F.no(selectIdx).xxMlBodyTxt_FD);
        // END 2018/04/03 J.Kim [QC#25096,MOD]

        // START 2018/06/21 [QC#25080, ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.cratTsDplyTxt_FH, scrnMsg.F.no(selectIdx).cratTsDplyTxt_FD);
        // END   2018/06/21 [QC#25080, ADD]

        // START 2018/07/09 J.Kim [QC#26801,ADD]
        setButtonProperties("Click_NoteAttachment", "Click_NoteAttachment", "Attachment", 1, null);
        // END 2018/07/09 J.Kim [QC#26801,ADD]

        scrnMsg.xxYesNoCd_FH.setValue("0");
        scrnMsg.cltNoteDtlPk_FH.setInputProtected(true);
        scrnMsg.cratDt_FH.setInputProtected(true);
        scrnMsg.cratUsrId_FH.setInputProtected(true);
        // START 2018/04/03 J.Kim [QC#25096,MOD]
        // scrnMsg.dtlNoteTxt_FH.setInputProtected(true);
        scrnMsg.xxMlBodyTxt_FH.setInputProtected(true);
        // END 2018/04/03 J.Kim [QC#25096,MOD]
        scrnMsg.colNoteSubjTxt_FH.setInputProtected(true);
        // START 2018/06/21 [QC#25080, MOD]
        // scrnMsg.arCltNoteTpCd_FH.setInputProtected(true);
        scrnMsg.cltNoteTpCd_FH.setInputProtected(true);
        // END   2018/06/21 [QC#25080, MOD]
        scrnMsg.xxPsnNm_FH.setInputProtected(true);
    }
}
