/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2010   Fujitsu         K.Tajima        Create          N/A
 *</pre>
 */
package business.servlet.NWDL0260;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWDL0260.constant.NWDL0260Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NWDL0260Scrn00_OpenWin_WH extends S21CommonHandler implements NWDL0260Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing to do.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWDL0260BMsg scrnMsg = (NWDL0260BMsg) bMsg;

        setArgForSubScreen(createNMAL6050Parameters(scrnMsg).toArray(new EZDBStringItem[0]));

        // regist this event-name to BMsg.
        scrnMsg.xxScrEventNm.setValue(getClass().getSimpleName());
    }

    private static List<EZDBStringItem> createNMAL6050Parameters(NWDL0260BMsg scrnMsg) {

        final List<EZDBStringItem> params = new ArrayList<EZDBStringItem>();

        // [0]:TBL_NM
        scrnMsg.xxTblNm_PP.setValue("SHIP_FROM_LOC_LIST_V");
        params.add(scrnMsg.xxTblNm_PP);

        // [1]:TBL_CD_COL_NM
        scrnMsg.xxTblCdColNm_PP.setValue("INVTY_LOC_CD");
        params.add(scrnMsg.xxTblCdColNm_PP);

        // [2]:TBL_NM_COL_NM
        scrnMsg.xxTblNmColNm_PP.setValue("INVTY_LOC_NM");
        params.add(scrnMsg.xxTblNmColNm_PP);

        // [3]:TBL_SORT_COL_NM
        scrnMsg.xxTblSortColNm_PP.setValue("INVTY_LOC_CD");
        params.add(scrnMsg.xxTblSortColNm_PP);

        // [4]:SCR_NM
        scrnMsg.xxScrNm_PP.setValue("WH Pop Up");
        params.add(scrnMsg.xxScrNm_PP);

        // [5]:HDR_CD_LB_NM
        scrnMsg.xxHdrCdLbNm_PP.setValue("WH Code");
        params.add(scrnMsg.xxHdrCdLbNm_PP);

        // [6]:HDR_NM_LB_NM
        scrnMsg.xxHdrNmLbNm_PP.setValue("WH Name");
        params.add(scrnMsg.xxHdrNmLbNm_PP);

        // [7]:DTL_CD_LB_NM
        scrnMsg.xxDtlCdLbNm_PP.setValue("WH Code");
        params.add(scrnMsg.xxDtlCdLbNm_PP);

        // [8]:DTL_NM_LB_NM
        scrnMsg.xxDtlNmLbNm_PP.setValue("WH Name");
        params.add(scrnMsg.xxDtlNmLbNm_PP);

        // [9]:COND_CD
        params.add(scrnMsg.invtyLocCd);

        // [10]:COND_NM
        scrnMsg.xxCondNm_PP.clear();
        params.add(scrnMsg.xxCondNm_PP);

        return params;
    }

}
