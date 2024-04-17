/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0240;

import static business.blap.NMAL0240.constant.NMAL0240Constant.BOM;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CHECKBOX_A;
import static business.blap.NMAL0240.constant.NMAL0240Constant.COMPONENT;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_ACTIVE;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_BOM_ITEM;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_BOM_TP;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_CMP;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_CUSA_SET;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_DESC;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_EFF_FROM;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_EFF_THRU;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_FILE_EXT;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_FILE_NAME_DOWNLOAD;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_MAX_ROW;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_MERCH_TP;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_PROD_CD;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_QTY;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_REV;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_REV_CMNT;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_SEQ1;
import static business.blap.NMAL0240.constant.NMAL0240Constant.CSV_SEQ2;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NMAM0050E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NMAM8493E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NZZM0000E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NZZM0001W;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NMAM8390E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NMAM8560E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NMAM8598I;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL0240.common.NMAL0240CommonLogic;
import business.file.NMAL0240F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL0240BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Fujitsu         C.Tanaka        Create          CSA
 * 2016/02/18   SRAA            Y.Chen          Update          QC#2645
 * 2016/06/02   SRAA            K.Aratani       Update          QC#9451
 * 2016/06/16   SRAA            K.Aratani       Update          QC#10313
 * 2016/08/24   Fujitsu         N.Sugiura       Update          QC#2872
 * 2023/04/27   CITS            R.Azucena       Update          QC#61434
 *</pre>
 */
public class NMAL0240BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL0240CMsg bizMsg = (NMAL0240CMsg) cMsg;
            NMAL0240SMsg glblMsg = (NMAL0240SMsg) sMsg;

            if ("NMAL0240_INIT".equals(screenAplID)) {
                doProcess_NMAL0240_INIT(bizMsg, glblMsg);

            } else if ("NMAL0240Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL0240Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL0240Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL0240_INIT(bizMsg, glblMsg);

            } else if ("NMAL0240Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL0240_INIT(bizMsg, glblMsg);

            } else if ("NMAL0240Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NMAL0240_INIT(bizMsg, glblMsg);

            } else if ("NMAL0240Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NMAL0240Scrn00_DeleteRow(bizMsg, glblMsg);

            } else if ("NMAL0240Scrn00_GetBomInfo".equals(screenAplID)) {
                doProcess_NMAL0240Scrn00_GetBomInfo(bizMsg, glblMsg);

            } else if ("NMAL0240Scrn00_GetMdse".equals(screenAplID)) {
                doProcess_NMAL0240Scrn00_GetMdse(bizMsg, glblMsg);

            } else if ("NMAL0240Scrn00_OpenCmpsnA".equals(screenAplID)) {
                doProcess_NMAL0240Scrn00_OpenCmpsnA(bizMsg, glblMsg);

            } else if ("NMAL0240Scrn00_OpenCmpsnB".equals(screenAplID)) {
                doProcess_NMAL0240Scrn00_OpenCmpsnB(bizMsg, glblMsg);

            } else if ("NMAL0240Scrn00_CloseCmpsnA".equals(screenAplID)) {
                doProcess_NMAL0240Scrn00_CloseCmpsnA(bizMsg, glblMsg);

            } else if ("NMAL0240Scrn00_CloseCmpsnB".equals(screenAplID)) {
                doProcess_NMAL0240Scrn00_CloseCmpsnB(bizMsg, glblMsg);

            } else if ("NMAL0240Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NMAL0240Scrn00_InsertRow(bizMsg, glblMsg);

            } else if ("NMAL0240_NMAL0260".equals(screenAplID)) {
                doProcess_NMAL0240_NMAL0260(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg BusinessMsg
     * @param glblMsg GlobalMsg
     */
    private void doProcess_NMAL0240_INIT(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {
        String mdseCd = bizMsg.mdseCd.getValue();
        bizMsg.clear();
        glblMsg.clear();

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.X);

        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd, mdseCd);

        ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_TP.class, bizMsg.mdseItemTpCd_L, bizMsg.mdseItemTpDescTxt_L);
        ZYPCodeDataUtil.createPulldownList(COA_PROJ.class, bizMsg.coaMdseTpCd_L, bizMsg.coaProjDescTxt_L);

        if (ZYPCommonFunc.hasValue(bizMsg.mdseCd)) {
            getBomInfo(bizMsg, glblMsg);
        }
    }

    /**
     * CMN_Download Event
     * @param bizMsg BusinessMsg
     * @param glblMsg GlobalMsg
     */
    private void doProcess_NMAL0240Scrn00_CMN_Download(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {
        NMAL0240Query.getInstance().getRevisionForCsv(bizMsg, new CreateDownloadData(bizMsg));
    }

    /**
     * DeleteRow_A Event
     * @param bizMsg BusinessMsg
     * @param glblMsg GlobalMsg
     */
    private void doProcess_NMAL0240Scrn00_DeleteRow(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.A, CHECKBOX_A, ZYPConstant.CHKBOX_ON_Y);
        ArrayList<Integer> arrList = new ArrayList<Integer>();

        int lengthX = glblMsg.X.getValidCount();
        int cnt = 0;
        if (0 < lengthX) {
            cnt = lengthX - 1;
        }

        BigDecimal seq = null;
        NMAL0240_ACMsg aCMsg = null;
        NMAL0240_XSMsg xSMsg = null;
        NMAL0240_ASMsg aSMsg = null;
        for (int index : checkList) {
            aCMsg = bizMsg.A.no(index);
            xSMsg = glblMsg.X.no(cnt);
            arrList.add(aCMsg.xxRowNum_A1.getValue().intValue());

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(index).cmpsnChngReqPk_A1)) {
                ZYPEZDItemValueSetter.setValue(xSMsg.cmpsnChngReqPk_X1, bizMsg.A.no(index).cmpsnChngReqPk_A1);
                ZYPEZDItemValueSetter.setValue(xSMsg.cmpsnPk_X1, bizMsg.A.no(index).cmpsnPk_A1.getValue());
                glblMsg.X.setValidCount(++lengthX);
                cnt++;
            }

            seq = aCMsg.xxNum_A1.getValue();
            for (int j = 0; j < glblMsg.A.getValidCount(); j++) {
                aSMsg = glblMsg.A.no(j);
                if (ZYPCommonFunc.hasValue(aSMsg.xxNum_A2) && seq.equals(aSMsg.xxNum_A1.getValue())) {
                    arrList.add(aSMsg.xxRowNum_A1.getValue().intValue());
                }
            }
        }

        List<Integer> delList = (List<Integer>) arrList;
        ZYPTableUtil.deleteRows(glblMsg.A, delList);
        NMAL0240CommonLogic.resetSequence(bizMsg, glblMsg);
        NMAL0240CommonLogic.reloadCurrentRevision(bizMsg, glblMsg);
    }

    /**
     * GetBomInfo Event
     * @param bizMsg BusinessMsg
     * @param glblMsg GlobalMsg
     */
    private void doProcess_NMAL0240Scrn00_GetBomInfo(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {
        getBomInfo(bizMsg, glblMsg);
    }

    /**
     * GetMdseA Event
     * @param bizMsg BusinessMsg
     * @param glblMsg GlobalMsg
     */
    private void doProcess_NMAL0240Scrn00_GetMdse(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {
        int idx = bizMsg.xxCellIdx_A.getValueInt();
        if (!NMAL0240CommonLogic.getMdse(bizMsg, glblMsg, idx)) {
            return;
        }
        NMAL0240CommonLogic.reloadCurrentRevision(bizMsg, glblMsg);
    }

    /**
     * OpenCmpsnA Event
     * @param bizMsg BusinessMsg
     * @param glblMsg GlobalMsg
     */
    private void doProcess_NMAL0240Scrn00_OpenCmpsnA(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {
        bizMsg.A.clear();

        int sel = bizMsg.xxCellIdx_A.getValue().intValue();
        BigDecimal selSeq = glblMsg.A.no(sel).xxNum_A1.getValue();
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(sel).xxPgFlg_A1, ZYPConstant.FLG_OFF_N);

        int cnt = 0;
        BigDecimal seq = null;
        NMAL0240_ACMsg aCMsg = null;
        NMAL0240_ASMsg aSMsg = null;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            aSMsg = glblMsg.A.no(i);
            aCMsg = bizMsg.A.no(cnt);
            seq = aSMsg.xxNum_A1.getValue();

            if (!seq.equals(selSeq)) {
                if (ZYPConstant.FLG_ON_Y.equals(aSMsg.xxDplyCtrlFlg_A1.getValue())) {
                    EZDMsg.copy(aSMsg, null, aCMsg, null);
                    cnt++;
                } else if (!ZYPCommonFunc.hasValue(aSMsg.xxNum_A2)) {
                    EZDMsg.copy(aSMsg, null, aCMsg, null);
                    cnt++;
                }
            } else {
                ZYPEZDItemValueSetter.setValue(aSMsg.xxDplyCtrlFlg_A1, ZYPConstant.FLG_ON_Y);
                EZDMsg.copy(aSMsg, null, aCMsg, null);
                cnt++;
            }
        }
        bizMsg.A.setValidCount(cnt);
    }

    /**
     * OpenCmpsnB Event
     * @param bizMsg BusinessMsg
     * @param glblMsg GlobalMsg
     */
    private void doProcess_NMAL0240Scrn00_OpenCmpsnB(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {
        bizMsg.B.clear();

        int sel = bizMsg.xxCellIdx_B.getValue().intValue();
        BigDecimal selSeq = glblMsg.B.no(sel).xxNum_B1.getValue();
        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(sel).xxPgFlg_B1, ZYPConstant.FLG_OFF_N);

        int cnt = 0;
        BigDecimal seq = null;
        NMAL0240_BCMsg bCMsg = null;
        NMAL0240_BSMsg bSMsg = null;
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            bSMsg = glblMsg.B.no(i);
            bCMsg = bizMsg.B.no(cnt);
            seq = bSMsg.xxNum_B1.getValue();

            if (!seq.equals(selSeq)) {
                if (ZYPConstant.FLG_ON_Y.equals(bSMsg.xxDplyCtrlFlg_B1.getValue())) {
                    EZDMsg.copy(bSMsg, null, bCMsg, null);
                    cnt++;
                } else if (!ZYPCommonFunc.hasValue(bSMsg.xxNum_B2)) {
                    EZDMsg.copy(bSMsg, null, bCMsg, null);
                    cnt++;
                }
            } else {
                ZYPEZDItemValueSetter.setValue(bSMsg.xxDplyCtrlFlg_B1, ZYPConstant.FLG_ON_Y);
                EZDMsg.copy(bSMsg, null, bCMsg, null);
                cnt++;
            }
        }
        bizMsg.B.setValidCount(cnt);
    }

    /**
     * CloseCmpsnA Event
     * @param bizMsg BusinessMsg
     * @param glblMsg GlobalMsg
     */
    private void doProcess_NMAL0240Scrn00_CloseCmpsnA(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {
        bizMsg.A.clear();

        int sel = bizMsg.xxCellIdx_A.getValue().intValue();
        BigDecimal selSeq = glblMsg.A.no(sel).xxNum_A1.getValue();
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(sel).xxPgFlg_A1, ZYPConstant.FLG_ON_Y);

        int cnt = 0;
        BigDecimal seq = null;
        NMAL0240_ACMsg aCMsg = null;
        NMAL0240_ASMsg aSMsg = null;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            aSMsg = glblMsg.A.no(i);
            aCMsg = bizMsg.A.no(cnt);
            seq = aSMsg.xxNum_A1.getValue();

            if (!seq.equals(selSeq)) {
                if (ZYPConstant.FLG_ON_Y.equals(aSMsg.xxDplyCtrlFlg_A1.getValue())) {
                    EZDMsg.copy(aSMsg, null, aCMsg, null);
                    cnt++;
                } else if (!ZYPCommonFunc.hasValue(aSMsg.xxNum_A2)) {
                    EZDMsg.copy(aSMsg, null, aCMsg, null);
                    cnt++;
                }
            } else {
                ZYPEZDItemValueSetter.setValue(aSMsg.xxDplyCtrlFlg_A1, ZYPConstant.FLG_OFF_N);
                if (!ZYPCommonFunc.hasValue(aSMsg.xxNum_A2)) {
                    EZDMsg.copy(aSMsg, null, aCMsg, null);
                    cnt++;
                }
            }
        }
        bizMsg.A.setValidCount(cnt);
    }

    /**
     * CloseCmpsnB Event
     * @param bizMsg BusinessMsg
     * @param glblMsg GlobalMsg
     */
    private void doProcess_NMAL0240Scrn00_CloseCmpsnB(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {
        bizMsg.B.clear();

        int sel = bizMsg.xxCellIdx_B.getValue().intValue();
        BigDecimal selSeq = glblMsg.B.no(sel).xxNum_B1.getValue();
        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(sel).xxPgFlg_B1, ZYPConstant.FLG_ON_Y);

        int cnt = 0;
        BigDecimal seq = null;
        NMAL0240_BCMsg bCMsg = null;
        NMAL0240_BSMsg bSMsg = null;
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            bSMsg = glblMsg.B.no(i);
            bCMsg = bizMsg.B.no(cnt);
            seq = bSMsg.xxNum_B1.getValue();

            if (!seq.equals(selSeq)) {
                if (ZYPConstant.FLG_ON_Y.equals(bSMsg.xxDplyCtrlFlg_B1.getValue())) {
                    EZDMsg.copy(bSMsg, null, bCMsg, null);
                    cnt++;
                } else if (!ZYPCommonFunc.hasValue(bSMsg.xxNum_B2)) {
                    EZDMsg.copy(bSMsg, null, bCMsg, null);
                    cnt++;
                }
            } else {
                ZYPEZDItemValueSetter.setValue(bSMsg.xxDplyCtrlFlg_B1, ZYPConstant.FLG_OFF_N);
                if (!ZYPCommonFunc.hasValue(bSMsg.xxNum_B2)) {
                    EZDMsg.copy(bSMsg, null, bCMsg, null);
                    cnt++;
                }
            }
        }
        bizMsg.B.setValidCount(cnt);
    }

    /**
     * InsertRow_A Event
     * @param bizMsg BusinessMsg
     * @param glblMsg GlobalMsg
     */
    private void doProcess_NMAL0240Scrn00_InsertRow(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {
        EZDMsg.copy(bizMsg.A, null, glblMsg.A, null);

        int index = glblMsg.A.getValidCount();
        if (index >= glblMsg.A.length()) {
            bizMsg.setMessageInfo(NMAM0050E);
            return;
        }

        int newSeq = 1;
        if (index != 0) {
            newSeq = glblMsg.A.no(index - 1).xxNum_A1.getValue().intValue() + 1;
        }

        NMAL0240_ASMsg aSMsg = glblMsg.A.no(index);
        // QC#2645
        aSMsg.clear();
        ZYPEZDItemValueSetter.setValue(aSMsg.xxNum_A1, new BigDecimal(newSeq));
        ZYPEZDItemValueSetter.setValue(aSMsg.xxPgFlg_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(aSMsg.xxDplyCtrlFlg_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(aSMsg.xxRowNum_A1, new BigDecimal(index));
        glblMsg.A.setValidCount(index + 1);

        NMAL0240CommonLogic.reloadCurrentRevision(bizMsg, glblMsg);

    }

    /**
     * NMAL0260 Event
     * @param bizMsg BusinessMsg
     * @param glblMsg GlobalMsg
     */
    private void doProcess_NMAL0240_NMAL0260(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {

        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.A);

        if (ZYPCommonFunc.hasValue(bizMsg.cmpsnRevnNum_A)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.cmpsnRevnNum_B, bizMsg.cmpsnRevnNum_A.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.cmpsnRevnCmntTxt_B, bizMsg.cmpsnRevnCmntTxt_A.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_B, bizMsg.effFromDt_A.getValue());
        }
        
        String slsDt = ZYPDateUtil.getSalesDate();
        String inputDate = !ZYPCommonFunc.hasValue(bizMsg.effFromDt_C) ? "" : bizMsg.effFromDt_C.getValue();
        boolean todayFlg = false;
        if (0 == ZYPDateUtil.compare(slsDt, inputDate)) {
        	todayFlg = true;
        }
        boolean preDataTodatFlg = false;
        if (0 == ZYPDateUtil.compare(slsDt, bizMsg.effFromDt_B.getValue())) {
        	preDataTodatFlg = true;
        }
        
        int i = 0;
        int cnt1 = 0;
        int cnt2 = 0;
        NMAL0240_ASMsg aSMsg = null;
        NMAL0240_BSMsg bSMsg = null;
        for (i = 0; i < glblMsg.A.getValidCount(); i++) {
            aSMsg = glblMsg.A.no(i);
            bSMsg = glblMsg.B.no(i);

            ZYPEZDItemValueSetter.setValue(bSMsg.xxPgFlg_B1, aSMsg.xxPgFlg_A1.getValue());
            ZYPEZDItemValueSetter.setValue(bSMsg.xxDplyCtrlFlg_B1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(bSMsg.xxChkBox_B1, aSMsg.xxChkBox_A1.getValue());
            ZYPEZDItemValueSetter.setValue(bSMsg.xxNum_B1, aSMsg.xxNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(bSMsg.xxNum_B2, aSMsg.xxNum_A2.getValue());
            ZYPEZDItemValueSetter.setValue(bSMsg.xxRowNum_B1, aSMsg.xxRowNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(bSMsg.cmpsnPk_B1, aSMsg.cmpsnPk_A1.getValue());
            ZYPEZDItemValueSetter.setValue(bSMsg.mdseCd_B1, aSMsg.mdseCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(bSMsg.mdseDescShortTxt_B1, aSMsg.mdseDescShortTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(bSMsg.childMdseQty_B1, aSMsg.childMdseQty_A1.getValue());
            ZYPEZDItemValueSetter.setValue(bSMsg.coaProjDescTxt_B1, aSMsg.coaProjDescTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(bSMsg.coaProdCd_B1, aSMsg.coaProdCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(bSMsg.effFromDt_B1, aSMsg.effFromDt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(bSMsg.effThruDt_B1, bizMsg.effThruDt_B.getValue());
            ZYPEZDItemValueSetter.setValue(bSMsg.xxScrItem10Txt_B1, aSMsg.xxScrItem10Txt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(bSMsg.ezUpTime_B1, aSMsg.ezUpTime_A1.getValue());
            ZYPEZDItemValueSetter.setValue(bSMsg.ezUpTimeZone_B1, aSMsg.ezUpTimeZone_A1.getValue());

            if (!ZYPCommonFunc.hasValue(bSMsg.xxNum_B2)) {
                EZDMsg.copy(bSMsg, null, bizMsg.B.no(cnt1), null);
                cnt1++;
            }

            aSMsg.cmpsnChngReqPk_A1.clear();
            aSMsg.cmpsnPk_A1.clear();
            aSMsg.ezUpTime_A1.clear();
            aSMsg.ezUpTimeZone_A1.clear();
            aSMsg.effFromDt_A1.clear();
            aSMsg.effThruDt_A1.clear();
            aSMsg.xxScrItem10Txt_A1.clear();

            if (!ZYPCommonFunc.hasValue(aSMsg.xxNum_A2)) {
                EZDMsg.copy(aSMsg, null, bizMsg.A.no(cnt2), null);
                cnt2++;
            }
        }

        glblMsg.B.setValidCount(i);
        bizMsg.B.setValidCount(cnt1);
        bizMsg.A.setValidCount(cnt2);

        int newRev = 1;
        if (ZYPCommonFunc.hasValue(bizMsg.cmpsnRevnNum_A) && todayFlg && preDataTodatFlg) {
            newRev = bizMsg.cmpsnRevnNum_A.getValue().intValue();
        } else if (ZYPCommonFunc.hasValue(bizMsg.cmpsnRevnNum_A)) {
            newRev = bizMsg.cmpsnRevnNum_A.getValue().intValue() + 1;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.cmpsnRevnNum_A, new BigDecimal(newRev));

        bizMsg.cmpsnRevnCmntTxt_A.clear();
        bizMsg.effFromDt_A.clear();
        bizMsg.effThruDt_A.clear();
    }

    @SuppressWarnings("unchecked")
    private void getBomInfo(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {

        // CUSA Set Header
        boolean cusaSet = false;
        
        //QC#10313
        glblMsg.A.clear();
        glblMsg.B.clear();
        
        //QC#9451
        boolean cusaConnectFlg = false;
        try {
	        S21SsmEZDResult ssmResult = NMAL0240Query.getInstance().getCusaHeader(bizMsg, glblMsg);
	        cusaConnectFlg = ssmResult.isCodeNormal();
        } catch (Throwable e) {
            bizMsg.setMessageInfo(NMAM8560E);
            return;
        }
        if (cusaConnectFlg) {
            EZDMsg.copy(glblMsg, null, bizMsg, null);
            cusaSet = true;
            
            //if CUSA MDSE.RGTN_STS_CD <> 'P20', Error
            if (!RGTN_STS.READY_FOR_ORDER_TAKING.equals(NMAL0240CommonLogic.getCusaMdseRgtnSts(getGlobalCompanyCode(), bizMsg.mdseCd.getValue()))) {
            	//Error
            	//NMAM8493E=0,This SET item code exists as CUSA SET but set up is not completed by CUSA . Please confirm item status to CUSA Marketing dept .
            	bizMsg.setMessageInfo(NMAM8493E);
            }

            //QC#10313
            //If there is no composition, show the message.
            if (!NMAL0240CommonLogic.existsCmpsn(getGlobalCompanyCode(), bizMsg.mdseCd.getValue())) {
                //NMAM8598I=0,Displayed composition information is comes from CUSA Item Master ,when you need to register it , please click submit.
            	bizMsg.setMessageInfo(NMAM8598I);
            }
            
        } else {
            // Other Header
            S21SsmEZDResult ssmResult2 = NMAL0240Query.getInstance().getHeader(bizMsg, glblMsg);
            if (ssmResult2.isCodeNormal()) {
                EZDMsg.copy(glblMsg, null, bizMsg, null);
            } else {
                bizMsg.setMessageInfo(NZZM0000E);
                return;
            }
        }

        // Revision
        List<Map<String, Object>> parentList = null;
        S21SsmEZDResult parentResult = NMAL0240Query.getInstance().getRevision(bizMsg);
        if (parentResult.isCodeNormal()) {
            parentList = (List<Map<String, Object>>) parentResult.getResultObject();
        } else {
            if (cusaSet) {
                // Cusa Revision
                S21SsmEZDResult cusaResult = NMAL0240Query.getInstance().getCusaRevision(bizMsg);
                if (cusaResult.isCodeNormal()) {
                    parentList = (List<Map<String, Object>>) cusaResult.getResultObject();
                } else {
                    bizMsg.setMessageInfo(NZZM0000E);
                    return;
                }
            }
        }

        // showRevision
        if (null != parentList) {
            showRevision(bizMsg, glblMsg, parentList);
        }

        // Display parent only
        int disCnt = 0;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).xxNum_A2)) {
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(disCnt), null);
                disCnt++;
            }
        }
        bizMsg.A.setValidCount(disCnt++);

        disCnt = 0;
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(i).xxNum_B2)) {
                EZDMsg.copy(glblMsg.B.no(i), null, bizMsg.B.no(disCnt), null);
                disCnt++;
            }
        }
        bizMsg.B.setValidCount(disCnt++);

    }

    @SuppressWarnings("unchecked")
    private void showRevision(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg, List<Map<String, Object>> parentList) {

        int seq1 = 1;
        int seq2 = 1;
        int idx = 0;
        int nextIdx = 0;
        BigDecimal rev = null;
        NMAL0240_ASMsg aSMsg = null;
        String slsDt = ZYPDateUtil.getSalesDate();
        
        // Current Revision
        for (int i = 0; i < parentList.size(); i++) {
            aSMsg = glblMsg.A.no(idx);
            Map parentMap = parentList.get(i);
            if (i == 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.cmpsnRevnNum_A, (BigDecimal) parentMap.get("CMPSN_REVN_NUM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.cmpsnRevnCmntTxt_A, (String) parentMap.get("CMPSN_REVN_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_A, (String) parentMap.get("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(bizMsg.effThruDt_A, (String) parentMap.get("EFF_THRU_DT"));

                rev = bizMsg.cmpsnRevnNum_A.getValue();
            } else {
                if (null != (BigDecimal) parentMap.get("CMPSN_REVN_NUM") && !rev.equals((BigDecimal) parentMap.get("CMPSN_REVN_NUM"))) {
                    nextIdx = i;
                    break;
                }
            }

            ZYPEZDItemValueSetter.setValue(aSMsg.cmpsnPk_A1, (BigDecimal) parentMap.get("CMPSN_PK"));
            ZYPEZDItemValueSetter.setValue(aSMsg.cmpsnChngReqPk_A1, (BigDecimal) parentMap.get("CMPSN_CHNG_REQ_PK"));
            ZYPEZDItemValueSetter.setValue(aSMsg.ezUpTime_A1, (String) parentMap.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(aSMsg.ezUpTimeZone_A1, (String) parentMap.get("EZUPTIMEZONE"));
            // 2016/08/24 QC#2872 Add Start
            ZYPEZDItemValueSetter.setValue(aSMsg.xxRecHistCratTs_A1, (String) parentMap.get("XX_REC_HIST_CRAT_TS"));
            ZYPEZDItemValueSetter.setValue(aSMsg.xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) parentMap.get("XX_REC_HIST_CRAT_BY_NM")));
            ZYPEZDItemValueSetter.setValue(aSMsg.xxRecHistUpdTs_A1, (String) parentMap.get("XX_REC_HIST_UPD_TS"));
            ZYPEZDItemValueSetter.setValue(aSMsg.xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) parentMap.get("XX_REC_HIST_UPD_BY_NM")));
            ZYPEZDItemValueSetter.setValue(aSMsg.xxRecHistTblNm_A1, (String) parentMap.get("XX_REC_HIST_TBL_NM"));
            // 2016/08/24 QC#2872 Add End
            ZYPEZDItemValueSetter.setValue(aSMsg.xxRowNum_A1, new BigDecimal(idx));
            ZYPEZDItemValueSetter.setValue(aSMsg.xxNum_A1, new BigDecimal(seq1));
            ZYPEZDItemValueSetter.setValue(aSMsg.xxPgFlg_A1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(aSMsg.xxDplyCtrlFlg_A1, ZYPConstant.FLG_OFF_N);

            ZYPEZDItemValueSetter.setValue(aSMsg.mdseCd_A1, (String) parentMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(aSMsg.mdseDescShortTxt_A1, (String) parentMap.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(aSMsg.childMdseQty_A1, (BigDecimal) parentMap.get("CHILD_MDSE_QTY"));
            ZYPEZDItemValueSetter.setValue(aSMsg.coaProjDescTxt_A1, (String) parentMap.get("COA_PROJ_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(aSMsg.coaProdCd_A1, (String) parentMap.get("COA_PROD_CD"));
            ZYPEZDItemValueSetter.setValue(aSMsg.xxScrItem10Txt_A1, (String) parentMap.get("ACTIVE"));
            ZYPEZDItemValueSetter.setValue(aSMsg.effFromDt_A1, (String) parentMap.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(aSMsg.effThruDt_A1, (String) parentMap.get("EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(aSMsg.mdseCmpsnTpCd_A1, (String) parentMap.get("MDSE_CMPSN_TP_CD"));

            if (!ZYPCommonFunc.hasValue((String) parentMap.get("RGTN_STS_CD")) || 
            		!RGTN_STS.READY_FOR_ORDER_TAKING.equals((String) parentMap.get("RGTN_STS_CD"))) {
            	//NMAM8390E=0,[@] exists in CUSA components and must be registered to CSA Item Master  as Active. Please verify CSA Item master status .
            	bizMsg.setMessageInfo(NMAM8390E, new String[]{(String) parentMap.get("MDSE_CD")});
            }
            
            // Child
            String mdseItemTp = (String) parentMap.get("MDSE_ITEM_TP_CD");
            if (MDSE_ITEM_TP.SET.equals(mdseItemTp) || MDSE_ITEM_TP.KIT.equals(mdseItemTp)) {
            	
            	String baseDt = slsDt;
		        if (0 >= ZYPDateUtil.compare(slsDt, bizMsg.effFromDt_A.getValue())) {
		        	baseDt = bizMsg.effFromDt_A.getValue();
		        }
                S21SsmEZDResult childResult = NMAL0240Query.getInstance().getChildren(bizMsg, (String) parentMap.get("MDSE_CD"), baseDt);
                if (childResult.isCodeNormal()) {
                    ZYPEZDItemValueSetter.setValue(aSMsg.xxPgFlg_A1, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(aSMsg.xxDplyCtrlFlg_A1, ZYPConstant.FLG_ON_Y);

                    List<Map<String, Object>> childList = (List<Map<String, Object>>) childResult.getResultObject();
                    seq2 = 1;
                    for (int j = 0; j < childList.size(); j++) {

                        Map childMap = childList.get(j);
                        idx++;
                        aSMsg = glblMsg.A.no(idx);

                        ZYPEZDItemValueSetter.setValue(aSMsg.xxRowNum_A1, new BigDecimal(idx));
                        ZYPEZDItemValueSetter.setValue(aSMsg.xxNum_A1, new BigDecimal(seq1));
                        ZYPEZDItemValueSetter.setValue(aSMsg.xxNum_A2, new BigDecimal(seq2));
                        ZYPEZDItemValueSetter.setValue(aSMsg.xxPgFlg_A1, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(aSMsg.xxDplyCtrlFlg_A1, ZYPConstant.FLG_OFF_N);

                        ZYPEZDItemValueSetter.setValue(aSMsg.mdseCd_A1, (String) childMap.get("MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(aSMsg.mdseDescShortTxt_A1, (String) childMap.get("MDSE_DESC_SHORT_TXT"));
                        ZYPEZDItemValueSetter.setValue(aSMsg.childMdseQty_A1, (BigDecimal) childMap.get("CHILD_MDSE_QTY"));
                        ZYPEZDItemValueSetter.setValue(aSMsg.coaProjDescTxt_A1, (String) childMap.get("COA_PROJ_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(aSMsg.coaProdCd_A1, (String) childMap.get("COA_PROD_CD"));
                        ZYPEZDItemValueSetter.setValue(aSMsg.xxScrItem10Txt_A1, (String) childMap.get("ACTIVE"));
                        ZYPEZDItemValueSetter.setValue(aSMsg.effFromDt_A1, (String) childMap.get("EFF_FROM_DT"));
                        ZYPEZDItemValueSetter.setValue(aSMsg.effThruDt_A1, (String) childMap.get("EFF_THRU_DT"));

                        seq2++;
                    }
                }
            }

            if (idx == bizMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
            }
            seq1++;
            idx++;
        }
        glblMsg.A.setValidCount(idx);

        // Previous Revision
        NMAL0240_BSMsg bSMsg = null;
        seq1 = 1;
        seq2 = 1;
        idx = 0;
        rev = null;

        if (nextIdx == 0) {
            nextIdx = parentList.size();
        }

        for (int i = nextIdx; i < parentList.size(); i++) {
            bSMsg = glblMsg.B.no(idx);
            Map parentMap = parentList.get(i);
            if (i == nextIdx) {
                ZYPEZDItemValueSetter.setValue(bizMsg.cmpsnRevnNum_B, (BigDecimal) parentMap.get("CMPSN_REVN_NUM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.cmpsnRevnCmntTxt_B, (String) parentMap.get("CMPSN_REVN_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_B, (String) parentMap.get("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(bizMsg.effThruDt_B, (String) parentMap.get("EFF_THRU_DT"));
                rev = bizMsg.cmpsnRevnNum_B.getValue();
            } else {
                if (!rev.equals((BigDecimal) parentMap.get("CMPSN_REVN_NUM"))) {
                    nextIdx = i;
                    break;
                }
            }

            ZYPEZDItemValueSetter.setValue(bSMsg.cmpsnPk_B1, (BigDecimal) parentMap.get("CMPSN_PK"));
            ZYPEZDItemValueSetter.setValue(bSMsg.ezUpTime_B1, (String) parentMap.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(bSMsg.ezUpTimeZone_B1, (String) parentMap.get("EZUPTIMEZONE"));
            // 2016/08/24 QC#2872 Add Start
            ZYPEZDItemValueSetter.setValue(bSMsg.xxRecHistCratTs_B1, (String) parentMap.get("XX_REC_HIST_CRAT_TS"));
            ZYPEZDItemValueSetter.setValue(bSMsg.xxRecHistCratByNm_B1, ZYPRecHistUtil.getFullNameForRecHist((String) parentMap.get("XX_REC_HIST_CRAT_BY_NM")));
            ZYPEZDItemValueSetter.setValue(bSMsg.xxRecHistUpdTs_B1, (String) parentMap.get("XX_REC_HIST_UPD_TS"));
            ZYPEZDItemValueSetter.setValue(bSMsg.xxRecHistUpdByNm_B1, ZYPRecHistUtil.getFullNameForRecHist((String) parentMap.get("XX_REC_HIST_UPD_BY_NM")));
            ZYPEZDItemValueSetter.setValue(bSMsg.xxRecHistTblNm_B1, (String) parentMap.get("XX_REC_HIST_TBL_NM"));
            // 2016/08/24 QC#2872 Add End
            ZYPEZDItemValueSetter.setValue(bSMsg.xxRowNum_B1, new BigDecimal(idx));
            ZYPEZDItemValueSetter.setValue(bSMsg.xxNum_B1, new BigDecimal(seq1));
            ZYPEZDItemValueSetter.setValue(bSMsg.xxPgFlg_B1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(bSMsg.xxDplyCtrlFlg_B1, ZYPConstant.FLG_OFF_N);

            ZYPEZDItemValueSetter.setValue(bSMsg.mdseCd_B1, (String) parentMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(bSMsg.mdseDescShortTxt_B1, (String) parentMap.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(bSMsg.childMdseQty_B1, (BigDecimal) parentMap.get("CHILD_MDSE_QTY"));
            ZYPEZDItemValueSetter.setValue(bSMsg.coaProjDescTxt_B1, (String) parentMap.get("COA_PROJ_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bSMsg.coaProdCd_B1, (String) parentMap.get("COA_PROD_CD"));
            ZYPEZDItemValueSetter.setValue(bSMsg.xxScrItem10Txt_B1, (String) parentMap.get("ACTIVE"));
            ZYPEZDItemValueSetter.setValue(bSMsg.effFromDt_B1, (String) parentMap.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(bSMsg.effThruDt_B1, (String) parentMap.get("EFF_THRU_DT"));

            // Child
            String mdseItemTp = (String) parentMap.get("MDSE_ITEM_TP_CD");
            if (MDSE_ITEM_TP.SET.equals(mdseItemTp) || MDSE_ITEM_TP.KIT.equals(mdseItemTp)) {
            	String effFromDt_B = slsDt;
            	if (ZYPCommonFunc.hasValue(bizMsg.effThruDt_B)) {
            		effFromDt_B = bizMsg.effThruDt_B.getValue();
            	}
                S21SsmEZDResult childResult = NMAL0240Query.getInstance().getChildren(bizMsg, (String) parentMap.get("MDSE_CD"), effFromDt_B);
                if (childResult.isCodeNormal()) {
                    ZYPEZDItemValueSetter.setValue(bSMsg.xxPgFlg_B1, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(bSMsg.xxDplyCtrlFlg_B1, ZYPConstant.FLG_ON_Y);

                    List<Map<String, Object>> childList = (List<Map<String, Object>>) childResult.getResultObject();
                    seq2 = 1;
                    for (int j = 0; j < childList.size(); j++) {
                        Map childMap = childList.get(j);

                        idx++;
                        bSMsg = glblMsg.B.no(idx);

                        ZYPEZDItemValueSetter.setValue(bSMsg.xxRowNum_B1, new BigDecimal(idx));
                        ZYPEZDItemValueSetter.setValue(bSMsg.xxNum_B1, new BigDecimal(seq1));
                        ZYPEZDItemValueSetter.setValue(bSMsg.xxNum_B2, new BigDecimal(seq2));
                        ZYPEZDItemValueSetter.setValue(bSMsg.xxPgFlg_B1, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(bSMsg.xxDplyCtrlFlg_B1, ZYPConstant.FLG_OFF_N);

                        ZYPEZDItemValueSetter.setValue(bSMsg.mdseCd_B1, (String) childMap.get("MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(bSMsg.mdseDescShortTxt_B1, (String) childMap.get("MDSE_DESC_SHORT_TXT"));
                        ZYPEZDItemValueSetter.setValue(bSMsg.childMdseQty_B1, (BigDecimal) childMap.get("CHILD_MDSE_QTY"));
                        ZYPEZDItemValueSetter.setValue(bSMsg.coaProjDescTxt_B1, (String) childMap.get("COA_PROJ_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(bSMsg.coaProdCd_B1, (String) childMap.get("COA_PROD_CD"));
                        ZYPEZDItemValueSetter.setValue(bSMsg.xxScrItem10Txt_B1, (String) childMap.get("ACTIVE"));
                        ZYPEZDItemValueSetter.setValue(bSMsg.effFromDt_B1, (String) childMap.get("EFF_FROM_DT"));
                        ZYPEZDItemValueSetter.setValue(bSMsg.effThruDt_B1, (String) childMap.get("EFF_THRU_DT"));

                        seq2++;
                    }
                }
            }

            if (idx == bizMsg.B.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
            }
            seq1++;
            idx++;
        }

        glblMsg.B.setValidCount(idx);
    }

    /**
     * Create Download Data class
     * @author C.Tanaka
     */
    private static class CreateDownloadData extends S21SsmBooleanResultSetHandlerSupport {

        /**
         * bizMsg NMAL0240CMsg
         */
        private NMAL0240CMsg bizMsg = null;

        public CreateDownloadData(NMAL0240CMsg cMsg) {
            this.bizMsg = cMsg;
        }

        @Override
        protected Boolean doProcessQueryResult(ResultSet result) throws SQLException {

            if (!result.next()) {
                bizMsg.setMessageInfo(NZZM0000E);
                return Boolean.FALSE;
            }

            NMAL0240F00FMsg fMsg = new NMAL0240F00FMsg();
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_DOWNLOAD), CSV_FILE_EXT);

            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

            String[] csvHeader = new String[] {CSV_BOM_ITEM, BOM + " " + CSV_DESC, CSV_BOM_TP, BOM + " " + CSV_MERCH_TP, BOM + " " + CSV_PROD_CD, CSV_CUSA_SET, BOM + " " + CSV_ACTIVE };
            csvOutFile.writeHeader(csvHeader);
            int rowCount = 1;

            fMsg.clear();
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A1, bizMsg.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.xxCustRsnTxt_A1, bizMsg.mdseDescShortTxt.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A1, bizMsg.mdseItemTpDescTxt.getValue());
            // START 2023/04/27 R.Azucena [QC#61434 MOD]
            // ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A2, bizMsg.coaProjDescTxt.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem500Txt_A1, bizMsg.coaProjDescTxt.getValue());
            // END 2023/04/27 R.Azucena [QC#61434 MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A1, bizMsg.xxScrItem61Txt.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A3, bizMsg.xxChkBox.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A1, bizMsg.actvFlg.getValue());
            csvOutFile.write();
            rowCount = 2;

            BigDecimal curRev = null;
            BigDecimal preRev = new BigDecimal(0);
            String curMdseCd = null;
            String preMdseCd = null;
            int seq1 = 0;
            int seq2 = 1;

            do {
                if (rowCount > CSV_MAX_ROW) {
                    bizMsg.setMessageInfo(NZZM0001W);
                    break;
                }
                curRev = result.getBigDecimal("CMPSN_REVN_NUM");
                curMdseCd = result.getString("MDSE_CD");

                if (!curRev.equals(preRev)) {
                    fMsg.clear();
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A1, CSV_REV);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxCustRsnTxt_A1, CSV_REV_CMNT);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A1, CSV_EFF_FROM);
                    // START 2023/04/27 R.Azucena [QC#61434 MOD]
                    // ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A2, CSV_EFF_THRU);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem500Txt_A1, CSV_EFF_THRU);
                    // END 2023/04/27 R.Azucena [QC#61434 MOD]
                    csvOutFile.write();
                    rowCount++;

                    fMsg.clear();
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A1, result.getString("CMPSN_REVN_NUM"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxCustRsnTxt_A1, result.getString("CMPSN_REVN_CMNT_TXT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A1, result.getString("EFF_FROM_DT"));
                    // START 2023/04/27 R.Azucena [QC#61434 MOD]
                    // ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A2, result.getString("EFF_THRU_DT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem500Txt_A1, result.getString("EFF_THRU_DT"));
                    // END 2023/04/27 R.Azucena [QC#61434 MOD]
                    csvOutFile.write();
                    rowCount++;

                    fMsg.clear();
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A1, CSV_SEQ1);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxCustRsnTxt_A1, CSV_SEQ2);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A1, CSV_CMP);
                    // START 2023/04/27 R.Azucena [QC#61434 MOD]
                    // ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A2, COMPONENT + " " + CSV_DESC);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem500Txt_A1, COMPONENT + " " + CSV_DESC);
                    // END 2023/04/27 R.Azucena [QC#61434 MOD]
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A1, CSV_QTY);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A3, CSV_MERCH_TP);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A1, CSV_PROD_CD);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A2, CSV_EFF_FROM);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A3, CSV_EFF_THRU);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A2, CSV_ACTIVE);
                    csvOutFile.write();
                    rowCount++;

                    preRev = curRev;
                    seq1 = 0;
                }

                fMsg.clear();

                if (!curMdseCd.equals(preMdseCd)) {
                    seq1++;
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A1, seq1 + "");
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A1, result.getString("MDSE_CD"));
                    // START 2023/04/27 R.Azucena [QC#61434 MOD]
                    // ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A2, result.getString("MDSE_DESC_SHORT_TXT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem500Txt_A1, result.getString("MDSE_DESC_SHORT_TXT"));
                    // END 2023/04/27 R.Azucena [QC#61434 MOD]
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A1, result.getString("CHILD_MDSE_QTY"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A3, result.getString("COA_PROJ_DESC_TXT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A1, result.getString("COA_PROD_CD"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A2, result.getString("EFF_FROM_DT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A3, result.getString("EFF_THRU_DT"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A2, result.getString("ACTIVE"));
                    csvOutFile.write();
                    rowCount++;

                    preMdseCd = curMdseCd;
                    seq2 = 1;
                }

                String childMdseCd = result.getString("MDSE_CD_CH");
                String childMdseCd2 = result.getString("MDSE_CD_CH2");
                if (ZYPCommonFunc.hasValue(childMdseCd) || ZYPCommonFunc.hasValue(childMdseCd2)) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A1, seq1 + "");
                    ZYPEZDItemValueSetter.setValue(fMsg.xxCustRsnTxt_A1, seq2 + "");
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A1, result.getString("CHILD_MDSE_QTY_CH"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A2, result.getString("EFF_FROM_DT_CH"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem19Txt_A3, result.getString("EFF_THRU_DT_CH"));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A2, result.getString("ACTIVE_CH"));

                    if (ZYPCommonFunc.hasValue(childMdseCd)) {
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A1, childMdseCd);
                        // START 2023/04/27 R.Azucena [QC#61434 MOD]
                        // ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A2, result.getString("MDSE_DESC_SHORT_TXT_CH"));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem500Txt_A1, result.getString("MDSE_DESC_SHORT_TXT_CH"));
                        // END 2023/04/27 R.Azucena [QC#61434 MOD]
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A3, result.getString("COA_PROJ_DESC_TXT_CH"));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A1, result.getString("COA_PROD_CD_CH"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A1, childMdseCd2);
                        // START 2023/04/27 R.Azucena [QC#61434 MOD]
                        // ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A2, result.getString("MDSE_DESC_SHORT_TXT_CH2"));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem500Txt_A1, result.getString("MDSE_DESC_SHORT_TXT_CH2"));
                        // END 2023/04/27 R.Azucena [QC#61434 MOD]
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem50Txt_A3, result.getString("COA_PROJ_DESC_TXT_CH2"));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_A1, result.getString("COA_PROD_CD_CH2"));
                    }

                    csvOutFile.write();
                    rowCount++;
                    seq2++;
                }

            } while (result.next());
            csvOutFile.close();

            return Boolean.TRUE;
        }
    }
}
