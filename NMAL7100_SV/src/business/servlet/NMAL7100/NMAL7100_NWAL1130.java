/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100;

import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_OPENWIN_MKTAUDVAL_01_CMN;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_OPENWIN_MKTAUDVAL_02_CMN;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_OPENWIN_MKTAUDVAL_03_CMN;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_OPENWIN_MKTPRMO;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_OPENWIN_PRMOQLFY_MDL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7100.common.NMAL7100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;


/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   Fujitsu         M.Hara          Create          N/A
 * 02/12/2016   Fujitsu         T.Murai         Update          #4032
 * 2016/02/24   Fujitsu         W.Honda         Update          CSA-QC#4043
 * 2019/01/08   Fujitsu         C.Hara          Update          QC#55221
 *</pre>
 */
public class NMAL7100_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // 2020/01/08 QC#55221 Del Start
        // if ("CMN_Close".equals(getLastGuard())) {
        //     return;
        // }
        // 2020/01/08 QC#55221 Del End

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;

        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        String eventNmString = scrnMsg.xxScrEventNm_H1.getValue();

        // 2020/01/08 QC#55221 Add Start
        if ("CMN_Close".equals(getLastGuard())) {
            if (EVT_OPENWIN_PRMOQLFY_MDL.equals(eventNmString)) {
                scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxCellIdx_H1.getValueInt()).prcQlfyValTxt_DA);
            }
            return;
        }
        // 2020/01/08 QC#55221 Add End

        if (EVT_OPENWIN_MKTPRMO.equals(eventNmString)) {
            setOpenWin_MktPrmo_Result(scrnMsg);
        } else if (EVT_OPENWIN_MKTAUDVAL_01_CMN.equals(eventNmString)) {
            setOpenWin_MktAudVal_01_Cmn_Result(scrnMsg);
        } else if (EVT_OPENWIN_MKTAUDVAL_02_CMN.equals(eventNmString)) {
            setOpenWin_MktAudVal_02_Cmn_Result(scrnMsg);
        } else if (EVT_OPENWIN_MKTAUDVAL_03_CMN.equals(eventNmString)) {
            setOpenWin_MktAudVal_03_Cmn_Result(scrnMsg);
            // Add Start #4032 02/12/2016
        } else if (EVT_OPENWIN_PRMOQLFY_MDL.equals(eventNmString)) {
            setOpenWin_PrmoQlfy_Mdl_Result(scrnMsg);
            // Add End #4032 02/12/2016
            // Add Start #4043 02/24/2016
        } else if (EVT_OPENWIN_PRCCATG.equals(eventNmString)) {
            setOpenWin_PrcCatg_Result(scrnMsg);
            // Add End #4043 02/24/2016
        }

        scrnMsg.xxScrEventNm_H1.clear();
        scrnMsg.xxCellIdx_H1.clear();

    }

    private void setOpenWin_MktPrmo_Result(NMAL7100BMsg scrnMsg) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcMktPrmoPk_H1, NMAL7100CommonLogic.convToBigDecimal(scrnMsg.R.no(0).xxComnScrColValTxt));
    }

    private void setOpenWin_MktAudVal_01_Cmn_Result(NMAL7100BMsg scrnMsg) {

        int rowIdx = scrnMsg.xxCellIdx_H1.getValueInt();

        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(rowIdx).xxScrItem30Txt_X1, scrnMsg.R.no(0).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(rowIdx).xxRecNmTxt_X1, scrnMsg.R.no(1).xxComnScrColValTxt);

    }

    private void setOpenWin_MktAudVal_02_Cmn_Result(NMAL7100BMsg scrnMsg) {

        int rowIdx = scrnMsg.xxCellIdx_H1.getValueInt();

        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(rowIdx).xxScrItem30Txt_X2, scrnMsg.R.no(0).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(rowIdx).xxRecNmTxt_X2, scrnMsg.R.no(1).xxComnScrColValTxt);

    }

    private void setOpenWin_MktAudVal_03_Cmn_Result(NMAL7100BMsg scrnMsg) {

        int rowIdx = scrnMsg.xxCellIdx_H1.getValueInt();

        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(rowIdx).xxScrItem30Txt_X3, scrnMsg.R.no(0).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(rowIdx).xxRecNmTxt_X3, scrnMsg.R.no(1).xxComnScrColValTxt);

    }

// Add Start #4032 02/12/2016
    private void setOpenWin_PrmoQlfy_Mdl_Result(NMAL7100BMsg scrnMsg) {

        int rowIdx = scrnMsg.xxCellIdx_H1.getValueInt();

        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(rowIdx).prcQlfyValTxt_DA, scrnMsg.R.no(1).xxComnScrColValTxt);
        scrnMsg.setFocusItem(scrnMsg.A.no(rowIdx).prcQlfyValTxt_DA); // 2019/01/08 QC#55221 Add
    }
// Add End #4032 02/12/2016

 // Add Start #4043 02/24/2016
     private void setOpenWin_PrcCatg_Result(NMAL7100BMsg scrnMsg) {

         int rowIdx = scrnMsg.xxCellIdx_H1.getValueInt();

         ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(rowIdx).prcCatgCd_CX, scrnMsg.R.no(0).xxComnScrColValTxt);
         ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(rowIdx).prcCatgNm_CX, scrnMsg.R.no(1).xxComnScrColValTxt);
     }
 // Add End #4043 02/24/2016

}
