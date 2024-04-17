/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import static business.servlet.NFDL0020.constant.NFDL0020Constant.NFDM0057E;
import static business.servlet.NFDL0020.constant.NFDL0020Constant.SCRN_ID_00;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0020.NFDL0020CMsg;
import business.servlet.NFDL0020.common.NFDL0020CommonLogic;
import business.servlet.NFDL0020.constant.NFDL0020Constant.DUN_LTR_WRK_ITEM_CD;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_ITEM_STS;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/06/16   Hitachi         K.Kojima        Update          QC#10200
 * 2016/06/22   Hitachi         K.Kojima        Update          QC#10529
 * 2016/07/07   Hitachi         K.Kojima        Update          QC#10337
 * 2016/07/20   Hitachi         K.Kojima        Update          QC#10188
 * 2016/07/28   Hitachi         K.Kojima        Update          QC#12096
 * 2018/04/03   Hitachi         J.Kim           Update          QC#24729
 * 2018/06/21   Hitachi         Y.Takeno        Update          QC#25080
 * 2018/07/25   Hitachi         Y.Takeno        Update          QC#25767
 * 2019/02/12   Fujitsu         S.Ohki          Update          QC#30143
 * 2021/09/17   CITS            G.Delgado       Update          QC#59070
 * 2022/12/12   Hitachi         S.Fujita        Update          QC#60406
 *</pre>
 */
public class NFDL0020Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        /*if (scrnMsg.xxDplyTab_H.getValue().equals("Note")) {
            if (ZYPCommonFunc.hasValue(scrnMsg.cltNoteDtlPk_FH)) {
                scrnMsg.setMessageInfo("NFAM9999E");
                throw new EZDFieldErrorException();
            } else if (!ZYPCommonFunc.hasValue(scrnMsg.dtlNoteTxt_FH)) {
                scrnMsg.setMessageInfo("NFAM9999E");
                throw new EZDFieldErrorException();
            }
        } else if (scrnMsg.xxDplyTab_H.getValue().equals("Task")) {
            if (ZYPCommonFunc.hasValue(scrnMsg.cltTaskPk_GH)) {
                scrnMsg.setMessageInfo("NFAM9999E");
                throw new EZDFieldErrorException();
            } else {
                scrnMsg.addCheckItem(scrnMsg.cltTaskSubjTxt_GH);
                if (!ZYPCommonFunc.hasValue(scrnMsg.cltTaskSubjTxt_GH)) {
                    scrnMsg.cltTaskSubjTxt_GH.setErrorInfo(1, "NFAM9999E");
                }
            }
        }*/

        // START 2021/09/17 G.Delgado [QC#59070,ADD]
        if (scrnMsg.xxDplyTab_H.getValue().equals("Strategy")) {
            List<DUN_LTR_WRK_ITEM_CD> dunLtrWrkItemList = new ArrayList<DUN_LTR_WRK_ITEM_CD>();

            // Check Open or Pending work items
            for (int idx = 0; idx < scrnMsg.D.getValidCount(); idx++) {
                String cltWrkItemStsCd = scrnMsg.D.no(idx).cltWrkItemStsCd_DD.getValue();
                String cltWrkItemCd = scrnMsg.D.no(idx).cltWrkItemCd_DD.getValue();

                if (CLT_WRK_ITEM_STS.PENDING.equals(cltWrkItemStsCd) || CLT_WRK_ITEM_STS.OPEN.equals(cltWrkItemStsCd)) {
                    // Check if dunning letter work item
                    DUN_LTR_WRK_ITEM_CD dunLtrWrkItem = DUN_LTR_WRK_ITEM_CD.fromValue(cltWrkItemCd);
                    if (dunLtrWrkItem != null) {
                        if (dunLtrWrkItemList.contains(dunLtrWrkItem)) {
                            // Error if same work item already exists
                            scrnMsg.setMessageInfo(NFDM0057E);
                            throw new EZDFieldErrorException();
                        } else {
                            dunLtrWrkItemList.add(dunLtrWrkItem);
                        }
                    }
                }
            }
        }
        // END 2021/09/17 G.Delgado [QC#59070,ADD]

        // START 2016/06/22 K.Kojima [QC#10529,ADD]
        NFDL0020CommonLogic.addCheckItemForSubmit(scrnMsg);
        // END 2016/06/22 K.Kojima [QC#10529,ADD]

        // START 2018/06/21 [QC#25080, DEL]
        // Start 2022/12/12 S.Fujita [QC#60406, MOD]
//        if (ZYPCommonFunc.hasValue(scrnMsg.xxMlBodyTxt_FH) && scrnMsg.xxMlBodyTxt_FH.getValue().length() > 4000) {
//            scrnMsg.xxMlBodyTxt_FH.setErrorInfo(1, "ZZM9001E", new String[] {"Note"} );
//        }
        if (ZYPCommonFunc.hasValue(scrnMsg.xxMlBodyTxt_FH) && scrnMsg.xxMlBodyTxt_FH.getValue().length() > 65536) {
            scrnMsg.xxMlBodyTxt_FH.setErrorInfo(1, "ZZM9001E", new String[] {"Note"} );
        }
        // End 2022/12/12 S.Fujita [QC#60406, MOD]
        // END   2018/06/21 [QC#25080, MOD]

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        NFDL0020CMsg bizMsg = new NFDL0020CMsg();
        bizMsg.setBusinessID("NFDL0020");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/06/22 K.Kojima [QC#10529,ADD]
        NFDL0020CommonLogic.addCheckItemForSubmit(scrnMsg);
        scrnMsg.putErrorScreen();
        // END 2016/06/22 K.Kojima [QC#10529,ADD]

        // START 2018/06/21 [QC#25080, MOD]
        // scrnMsg.arCltNoteTpCd_FH.setInputProtected(true);
        scrnMsg.cltNoteTpCd_FH.setInputProtected(true);
        // END   2018/06/21 [QC#25080, MOD]
        scrnMsg.colNoteSubjTxt_FH.setInputProtected(true);
        // START 2018/04/03 J.Kim [QC#25096,MOD]
        // scrnMsg.dtlNoteTxt_FH.setInputProtected(true);
        scrnMsg.xxMlBodyTxt_FH.setInputProtected(true);
        // END 2018/04/03 J.Kim [QC#25096,MOD]
        scrnMsg.cltTaskPk_GH.setInputProtected(true);
        scrnMsg.cltTaskStsCd_GH.setInputProtected(false);
        scrnMsg.cltTaskTpCd_GH.setInputProtected(true);
        scrnMsg.cltTaskPrtyCd_GH.setInputProtected(true);
        scrnMsg.cltTaskOwnrId_GH.setInputProtected(true);
        // START 2016/07/07 K.Kojima [QC#10337,MOD]
        // scrnMsg.xxPsnNm_G1.setInputProtected(true);
        scrnMsg.cltPsnNm_G1.setInputProtected(true);
        // END 2016/07/07 K.Kojima [QC#10337,MOD]
        scrnMsg.cratUsrId_GH.setInputProtected(true);
        scrnMsg.xxPsnNm_G2.setInputProtected(true);
        // START 2018/07/25 [QC#25767, ADD]
        scrnMsg.updUsrId_GH.setInputProtected(true);
        scrnMsg.xxPsnNm_G5.setInputProtected(true);
        scrnMsg.cltTaskUpdDt_GH.setInputProtected(true);
        // END   2018/07/25 [QC#25767, ADD]
        scrnMsg.cltTaskDescTxt_GH.setInputProtected(true);
        scrnMsg.cltTaskSubjTxt_GH.setInputProtected(true);
        scrnMsg.cltTaskRwsdDt_GH.setInputProtected(true);
        scrnMsg.cltTaskRwedDt_GH.setInputProtected(true);
        scrnMsg.cltTaskCtacNm_GH.setInputProtected(true);
        scrnMsg.cltTaskCtacPhoNum_GH.setInputProtected(true);
        // START 2016/07/28 K.Kojima [QC#12096,ADD]
        NFDL0020CommonLogic.setInputProtected_B(scrnMsg);
        // END 2016/07/28 K.Kojima [QC#12096,ADD]
        NFDL0020CommonLogic.setTabStrategyEnabled(this, scrnMsg);
        
        // START 2016/06/16 K.Kojima [QC#10200,ADD]
        if (scrnMsg.cltTaskStsCd_GH.getValue().equals(CLT_TASK_STS.OPEN)) {
            scrnMsg.xxYesNoCd_GH.setValue("1");
            // START 2016/06/22 K.Kojima [QC#10529,MOD]
            // NFDL0020CommonLogic.setTaskInputProtectFalse(scrnMsg);
            NFDL0020CommonLogic.setTaskInputProtectFalse(this, scrnMsg);
            // END 2016/06/22 K.Kojima [QC#10529,MOD]
        } else {
            scrnMsg.xxYesNoCd_GH.setValue("0");
            // START 2016/06/22 K.Kojima [QC#10529,MOD]
            // NFDL0020CommonLogic.setTaskInputProtectTrue(scrnMsg);
            NFDL0020CommonLogic.setTaskInputProtectTrue(this, scrnMsg);
            // END 2016/06/22 K.Kojima [QC#10529,MOD]
        }
        // END 2016/06/16 K.Kojima [QC#10200,ADD]

        // START 2016/07/20 K.Kojima [QC#10188,ADD]
        if (scrnMsg.getMessageType() != EZDMessageInfo.MSGTYPE_ERROR) {
            scrnMsg.setMessageInfo("NZZM0002I");
        }
        // END 2016/07/20 K.Kojima [QC#10188,ADD]

        // START 2019/02/12 S.Ohki [QC#30143,ADD]
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.F.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.G.no(0).getBaseContents());
        // END 2019/02/12 S.Ohki [QC#30143,ADD]
    }
}
