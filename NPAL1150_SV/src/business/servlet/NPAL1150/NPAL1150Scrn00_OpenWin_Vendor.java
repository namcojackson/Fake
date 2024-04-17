/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1150;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1150.common.NPAL1150CommonLogic;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NPAL1150 ASN Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/15   Hitachi         T.Kawazu        Create          N/A
 * 2016/10/26   CITS            T.Hakodate      Update          QC#15060
 * 2017/10/27   CITS            M.Naito         Update          QC#20380
 *</pre>
 */
public class NPAL1150Scrn00_OpenWin_Vendor extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NPAL1150BMsg scrnMsg = (NPAL1150BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1150BMsg scrnMsg = (NPAL1150BMsg) bMsg;

        NPAL1150CommonLogic.clearParamNPAL6050(scrnMsg);

        Object[] params = new Object[11];

        // QC#15060
        // [0]:TBL_NM
        // scrnMsg.xxTblNm.setValue("VND_DOM_V");
        scrnMsg.xxTblNm.setValue("PO_VND_V");
        // [1]:TBL_CD_COL_NM
        scrnMsg.xxTblCdColNm.setValue("VND_CD");
        // [2]:TBL_NM_COL_NM
        scrnMsg.xxTblNmColNm.setValue("DPLY_VND_NM");
        // [3]:TBL_SORT_COL_NM
        scrnMsg.xxTblSortColNm.setValue("VND_CD");
        // [4]:SCR_NM
        // scrnMsg.xxScrNm.setValue("Domestic Vendor Search");
        scrnMsg.xxScrNm.setValue("Supplier Search");
        // [5]:HDR_CD_LB_NM
        // scrnMsg.xxHdrCdLbNm.setValue("Domestic Vendor Code");
        scrnMsg.xxHdrCdLbNm.setValue("Supplier Code");
        // [6]:HDR_NM_LB_NM
        // scrnMsg.xxHdrNmLbNm.setValue("Domestic Vendor Name");
        scrnMsg.xxHdrNmLbNm.setValue("Supplier Name");
        // [7]:DTL_CD_LB_NM
        // scrnMsg.xxDtlCdLbNm.setValue("Domestic Vendor Code");
        scrnMsg.xxDtlCdLbNm.setValue("Supplier Code");
        // [8]:DTL_NM_LB_NM
        // scrnMsg.xxDtlNmLbNm.setValue("Domestic Vendor Name");
        scrnMsg.xxDtlNmLbNm.setValue("Supplier Name");

        params[0] = scrnMsg.xxTblNm;
        params[1] = scrnMsg.xxTblCdColNm;
        params[2] = scrnMsg.xxTblNmColNm;
        params[3] = scrnMsg.xxTblSortColNm;
        params[4] = scrnMsg.xxScrNm;
        params[5] = scrnMsg.xxHdrCdLbNm;
        params[6] = scrnMsg.xxHdrNmLbNm;
        params[7] = scrnMsg.xxDtlCdLbNm;
        params[8] = scrnMsg.xxDtlNmLbNm;

        // [9]:COND_CD
        params[9] = scrnMsg.vndCd_A1;
        // [10]:no data
        // 2017/10/27 QC20380 M.Naito Mod Start
//        scrnMsg.vndNm_A1.clear();
//        params[10] = scrnMsg.vndNm_A1;
        scrnMsg.dplyVndNm_A1.clear();
        params[10] = scrnMsg.dplyVndNm_A1;
        // 2017/10/27 QC20380 M.Naito Mod End

        setArgForSubScreen(params);

    }
}
