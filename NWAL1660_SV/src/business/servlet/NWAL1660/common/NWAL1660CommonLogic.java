/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1660.common;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_0;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_1;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_2;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_3;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_4;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_5;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_6;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.BTN_ADD_CHARGES;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.BTN_ADD_SELL_PRICE;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.BTN_ANALYSIS_CHARGES;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.BTN_ANALYSIS_SELL_PRICE;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.BTN_CALCULATE;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.BTN_CMN_CLS;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.BTN_DEL_CHARGES;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.BTN_DEL_SELL_PRICE;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.BTN_HISTORY;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.BTN_RESET;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.MAX_INPUT_PARAM_NUM;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.MODE_REGIST;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.NWAM0186E;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.NWAM0553E;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.SCRN_ID_00;
import static business.servlet.NWAL1660.constant.NWAL1660Constant.ZZM9000E;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDMsg;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NWAL1660.NWAL1660CMsg;
import business.servlet.NWAL1660.NWAL1660BMsg;
import business.servlet.NWAL1660.NWAL1660_ABMsg;
import business.servlet.NWAL1660.NWAL1660_ABMsgArray;
import business.servlet.NWAL1660.NWAL1660_BBMsg;
import business.servlet.NWAL1660.NWAL1660_BBMsgArray;
import business.servlet.NWAL1660.NWAL1660_CBMsg;
import business.servlet.NWAL1660.NWAL1660_CBMsgArray;
import business.servlet.NWAL1660.constant.NWAL1660Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ADJ_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_MOD_TP;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL1660CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Fujitsu         M.Yamada        Create          N/A
 * 2015/11/26   Fujitsu         T.Ishii         Update          S21_NA#1183
 * 2015/12/16   Fujitsu         Y.Kanefusa      Update          #1090
 * 2016/02/20   Fujitsu         S.Yoshida       Update          QC#2177
 * 2016/03/04   Fujitsu         M.Ohno          Update          QC#2176
 * 2016/09/09   Fujitsu         R.Nakamura      Update          QC#11614
 * 2017/08/17   Fujitsu         H.Ikeda         Update          QC#20673
 * 2018/01/25   Fujitsu         K.Ishizuka      Update          QC#23334
 * 2018/04/11   Fujitsu         Y.Kanefusa      Update          S21_NA#22965
 * 2018/05/13   Fujitsu         S.Takami        Update          S21_NA#25251
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 * 2018/11/27   Fujitsu         M.Ishii         Update          QC#29361
 *</pre>
 */
public class NWAL1660CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        // S21_NA#1183 modify start
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        // S21_NA#1183 modify end
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);

    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1660BMsg
     * @param aScrnMsgAry NWAL1660_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL1660BMsg scrnMsg, NWAL1660_ABMsgArray aScrnMsgAry, String tblId) {
        if (aScrnMsgAry.getValidCount() > 0) {
            setRowsBGWithClear(scrnMsg, aScrnMsgAry, tblId, 1);
        }
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1660BMsg
     * @param bScrnMsgAry NWAL1660_BBMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL1660BMsg scrnMsg, NWAL1660_BBMsgArray bScrnMsgAry, String tblId) {
        if (bScrnMsgAry.getValidCount() > 0) {
            setRowsBGWithClear(scrnMsg, bScrnMsgAry, tblId, 1);
        }
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1660BMsg
     * @param cScrnMsgAry NWAL1660_CBMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL1660BMsg scrnMsg, NWAL1660_CBMsgArray cScrnMsgAry, String tblId) {
        if (cScrnMsgAry.getValidCount() > 0) {
            setRowsBGWithClear(scrnMsg, cScrnMsgAry, tblId, 1);
        }
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1660BMsg
     * @param aScrnMsgAry NWAL1660_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL1660BMsg scrnMsg, NWAL1660_ABMsgArray aScrnMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, aScrnMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, aScrnMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1660BMsg
     * @param bScrnMsgAry NWAL1660_BBMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL1660BMsg scrnMsg, NWAL1660_BBMsgArray bScrnMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, bScrnMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, bScrnMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1660BMsg
     * @param cScrnMsgAry NWAL1660_CBMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL1660BMsg scrnMsg, NWAL1660_CBMsgArray cScrnMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, cScrnMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, cScrnMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWAL1660BMsg
     * @param scrnAMsgAry NWAL1660_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL1660BMsg scrnMsg, NWAL1660_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * <pre>
     * @param scrnMsg   NWAL1660BMsg
     * @param arg       input parameters(Object[])
     * </pre>
     */
    public static void setInputParam(NWAL1660BMsg scrnMsg, Object[] arg) {
        if (!(arg instanceof Object[])) {
            return;
        }

        if (arg.length < MAX_INPUT_PARAM_NUM) {
            return;
        }

        int paramIx = 0;

        EZDBStringItem mode = null;
        // 0.Input mode
        if (arg[paramIx] instanceof EZDBStringItem) {
            mode = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, mode);

        EZDBStringItem modePM = null;
        // 0-1.Header / Line
        if (arg[paramIx] instanceof EZDBStringItem) {
            modePM = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_PM, modePM);

        // QC#9700  2018/09/03 Add Start
        EZDBStringItem xxViewChngLogSrcCd = null;
        if (arg[paramIx] instanceof EZDBStringItem) {
            xxViewChngLogSrcCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxViewChngLogSrcCd, xxViewChngLogSrcCd);
        // QC#9700  2018/09/03 Add End
        
        // 2018/01/25 S21_NA#23334 Mod Start
        //EZDBStringItem trxHdrNum = null;
        EZDBStringItem ordSrcRefNum = null;
        // 1.Transaction Header Number
        if (arg[paramIx] instanceof EZDBStringItem) {
            //trxHdrNum = (EZDBStringItem) arg[paramIx++];
            ordSrcRefNum = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        //ZYPEZDItemValueSetter.setValue(scrnMsg.trxHdrNum, trxHdrNum);
        ZYPEZDItemValueSetter.setValue(scrnMsg.ordSrcRefNum, ordSrcRefNum);
        // 2018/01/25 S21_NA#23334 Mod End

        EZDBDateItem prcBaseDt = null;
        // 2.Price Based Date
        if (arg[paramIx] instanceof EZDBDateItem) {
            prcBaseDt = (EZDBDateItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcBaseDt, prcBaseDt);

        EZDBDateItem prcCalcDt = null;
        // 3.Pricing Calculation Date
        if (arg[paramIx] instanceof EZDBDateItem) {
            prcCalcDt = (EZDBDateItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcCalcDt, prcCalcDt);

        EZDBStringItem dsOrdTpCd = null;
        // 4.DS Order Type Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            dsOrdTpCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdTpCd, dsOrdTpCd);

        EZDBStringItem dsOrdCatgCd = null;
        // 5.DS Order Category Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            dsOrdCatgCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgCd, dsOrdCatgCd);

        EZDBStringItem lob = null;
        // 6.Line of Business
        if (arg[paramIx] instanceof EZDBStringItem) {
            lob = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.lineBizTpCd, lob);

        EZDBStringItem cpoSrcTp = null;
        // 7.CPO Source Type
        if (arg[paramIx] instanceof EZDBStringItem) {
            cpoSrcTp = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.cpoSrcTpCd, cpoSrcTp);

        EZDBStringItem custPoNum = null;
        // 8.Customer PO Number
        if (arg[paramIx] instanceof EZDBStringItem) {
            custPoNum = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.custIssPoNum, custPoNum);

        EZDBStringItem dsPmtMethCd = null;
        // 9.DS Payment Method Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            dsPmtMethCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsPmtMethCd, dsPmtMethCd);

        EZDBStringItem spclHdlgTpCd = null;
        // 10.Special Handling Type
        if (arg[paramIx] instanceof EZDBStringItem) {
            spclHdlgTpCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.spclHdlgTpCd, spclHdlgTpCd);

        EZDBStringItem leasePrchOptCd = null;
        // 11.Lease End Term Parchase Option Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            leasePrchOptCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.leasePrchOptCd, leasePrchOptCd);

        EZDBStringItem dsOrdPosnNum = null;
        // 12.Config Group#
        if (arg[paramIx] instanceof EZDBStringItem) {
            dsOrdPosnNum = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum_P, dsOrdPosnNum);

        EZDBStringItem trxLineNum = null;
        // 13.Transaction Line Number
        if (arg[paramIx] instanceof EZDBStringItem) {
            trxLineNum = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.trxLineNum_P, trxLineNum);

        EZDBStringItem trxLineSubNum = null;
        // 14.Transaction Line Sub Number
        if (arg[paramIx] instanceof EZDBStringItem) {
            trxLineSubNum = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.trxLineSubNum_P, trxLineSubNum);

        EZDBStringItem configCatgCd = null;
        // 15.Config Category Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            configCatgCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.configCatgCd_P, configCatgCd);

        EZDBBigDecimalItem inEachQty = null;
        // 16.Each Quantity
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            inEachQty = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.inEachQty_P, inEachQty);

        EZDBStringItem ship1Addr = null;
        // 17.Ship-To First Line Address
        if (arg[paramIx] instanceof EZDBStringItem) {
            ship1Addr = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToFirstLineAddr_P, ship1Addr);

        EZDBStringItem ship2Addr = null;
        // 18.Ship-To 2nd Line Address
        if (arg[paramIx] instanceof EZDBStringItem) {
            ship2Addr = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToScdLineAddr_P, ship2Addr);

        EZDBStringItem shipCtyAddr = null;
        // 19.Ship-To City Address
        if (arg[paramIx] instanceof EZDBStringItem) {
            shipCtyAddr = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCtyAddr_P, shipCtyAddr);

        EZDBStringItem shipStCd = null;
        // 20.Ship-To State Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            shipStCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToStCd_P, shipStCd);

        EZDBStringItem shipCnty = null;
        // 21.Ship-To County Name
        if (arg[paramIx] instanceof EZDBStringItem) {
            shipCnty = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCntyNm_P, shipCnty);

        EZDBStringItem shipPost = null;
        // 22.Ship-To Postal Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            shipPost = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToPostCd_P, shipPost);

        EZDBStringItem shipCtry = null;
        // 23.Ship-To Country Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            shipCtry = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCtryCd_P, shipCtry);

        EZDBStringItem prcCatg = null;
        // 24.Price Category Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            prcCatg = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgCd_P, prcCatg);

        EZDBStringItem csmpNum = null;
        // 25.CSMP#
        if (arg[paramIx] instanceof EZDBStringItem) {
            csmpNum = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.csmpNum_P, csmpNum);

        EZDBStringItem dlrRefNum = null;
        // 26.CSA CSMP Reference Number
        if (arg[paramIx] instanceof EZDBStringItem) {
            dlrRefNum = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.dlrRefNum_P, dlrRefNum);

        EZDBStringItem prcContrNum = null;
        // 27.CSA Price Contract Number
        if (arg[paramIx] instanceof EZDBStringItem) {
            prcContrNum = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcContrNum_P, prcContrNum);

        EZDBStringItem coaBrCd = null;
        // 28.COA Branch Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            coaBrCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.coaBrCd_P, coaBrCd);

        EZDBStringItem mdseCd = null;
        // 29.Merchandise Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            mdseCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_P, mdseCd);

        EZDBStringItem billToCustCd = null;
        // 30.Bill to Customer Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            billToCustCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd_P, billToCustCd);

        EZDBStringItem billToAcct = null;
        // 31.Bill to Account Number
        if (arg[paramIx] instanceof EZDBStringItem) {
            billToAcct = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustAcctCd_P, billToAcct);

        EZDBStringItem sellToCust = null;
        // 32.Sell to Customer Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            sellToCust = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.sellToCustCd_P, sellToCust);

        EZDBStringItem soldToLoc = null;
        // 33.Sold to Location Number
        if (arg[paramIx] instanceof EZDBStringItem) {
            soldToLoc = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.soldToCustLocCd_P, soldToLoc);

        EZDBStringItem shipToCust = null;
        // 34.Ship to Customer Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            shipToCust = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_P, shipToCust);

        EZDBStringItem shipToAcct = null;
        // 35.Ship to Account Number
        if (arg[paramIx] instanceof EZDBStringItem) {
            shipToAcct = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustAcctCd_P, shipToAcct);

        EZDBStringItem frtCondCd = null;
        // 36.Freight Condition Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            frtCondCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.frtCondCd_P, frtCondCd);

        EZDBStringItem shpgSvcLvlCd = null;
        // 37.Shipping Service Level Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            shpgSvcLvlCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.shpgSvcLvlCd_P, shpgSvcLvlCd);

        EZDBStringItem ccyCd = null;
        // 38.Currency Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            ccyCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.ccyCd_P, ccyCd);

        EZDBStringItem uomCd = null;
        // 39.Unit of Measure Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            uomCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.uomCd_P, uomCd);

        EZDBBigDecimalItem ordQty = null;
        // 40.Order Unit of Measure Quantity
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            ordQty = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.ordCustUomQty_P, ordQty);

        EZDBStringItem dsCpoLineCatgCd = null;
        // 41.DS CPO Line Category Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            dsCpoLineCatgCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineCatgCd_P, dsCpoLineCatgCd);

        EZDBBigDecimalItem invQty = null;
        // 42.Invoiced Quantity
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            invQty = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.invQty_P, invQty);

        EZDBBigDecimalItem mdlId = null;
        // 43.Model ID
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            mdlId = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.mdlId_P, mdlId);

        EZDBStringItem rtrnRsnCd = null;
        // 44.Return Reason Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            rtrnRsnCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnRsnCd_P, rtrnRsnCd);

        EZDBStringItem coaExtnCd = null;
        // 45.COA Extension Code (Business Unit)
        if (arg[paramIx] instanceof EZDBStringItem) {
            coaExtnCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.coaExtnCd_P, coaExtnCd);

        EZDBStringItem slsRepOrSlsTeamTocCd = null;
        // 46.Sales Rep or Sales Team TOC Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            slsRepOrSlsTeamTocCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepOrSlsTeamTocCd_P, slsRepOrSlsTeamTocCd);

        EZDBStringItem rtlWhCd = null;
        // 47.Retail Warehouse Code
        if (arg[paramIx] instanceof EZDBStringItem) {
            rtlWhCd = (EZDBStringItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_P, rtlWhCd);

        EZDBBigDecimalItem totBasePrc = null;
        // 48.Base Price Total
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            totBasePrc = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotBaseAmt_P, totBasePrc);

        EZDBBigDecimalItem subTotAmt = null;
        // 49.Sub Total Amount
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            subTotAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSubTotCalcPrcAmt_P, subTotAmt);

        EZDBBigDecimalItem totChrgAmt = null;
        // 50.Total Charges Amount
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            totChrgAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotChrgPrcAmt_P, totChrgAmt);

        EZDBBigDecimalItem totDiscAmt = null;
        // 51.Discount Total
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            totDiscAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotDiscAmt_P, totDiscAmt);

        EZDBBigDecimalItem totSpclAmt = null;
        // 52.Special Price Total
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            totSpclAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotSpclPrcAmt_P, totSpclAmt);

        EZDBBigDecimalItem totNetDiscAmt = null;
        // 53.Net Discount Total
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            totNetDiscAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotNetDiscAmt_P, totNetDiscAmt);

        EZDBBigDecimalItem unitNetAmt = null;
        // 54.Unit Net Price
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            unitNetAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxUnitNetPrcAmt_P, unitNetAmt);

        EZDBBigDecimalItem unitGrsAmt = null;
        // 55.Unit Gross Price
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            unitGrsAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxUnitGrsPrcAmt_P, unitGrsAmt);

        EZDBBigDecimalItem netAmt = null;
        // 56.Net Amount
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            netAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotNetPrcAmt_P, netAmt);

        EZDBBigDecimalItem grsAmt = null;
        // 57.Gross Amount
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            grsAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxGrsAmt_P, grsAmt);

        EZDBBigDecimalItem unitFrtAmt = null;
        // 58.Unit Freight
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            unitFrtAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxUnitFrtAmt_P, unitFrtAmt);

        EZDBBigDecimalItem totFrtAmt = null;
        // 59.Freight Total
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            totFrtAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotFrtAmt_P, totFrtAmt);

        EZDBBigDecimalItem unitSpclChrgAmt = null;
        // 60.Unit Special Charge
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            unitSpclChrgAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxUnitSpclChrgAmt_P, unitSpclChrgAmt);

        EZDBBigDecimalItem totSpclChrgAmt = null;
        // 61.Special Charge Total
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            totSpclChrgAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotSpclChrgAmt_P, totSpclChrgAmt);

        EZDBBigDecimalItem subTotFrtAmt = null;
        // 62.Freight Sub Total
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            subTotFrtAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotFrtSubAmt_P, subTotFrtAmt);

        EZDBBigDecimalItem unitRestkFeeAmt = null;
        // 63.Unit Restocking Fee
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            unitRestkFeeAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxUnitRestkFeeAmt_P, unitRestkFeeAmt);

        EZDBBigDecimalItem totRestkFeeAmt = null;
        // 64.Restocking Fee Total
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            totRestkFeeAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotRestkFeeAmt_P, totRestkFeeAmt);

        EZDBBigDecimalItem netAmt2 = null;
        // 65.Net Amount2
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            netAmt2 = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotNetPrcAmt_P2, netAmt2);

        EZDBBigDecimalItem totTaxAmt = null;
        // 66.Tax Total
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            totTaxAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotTaxAmt_P, totTaxAmt);

        EZDBBigDecimalItem totPrcAmt = null;
        // 67.Total Amount
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            totPrcAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxUnitPrc_P, totPrcAmt);

        EZDBBigDecimalItem totAmt = null;
        // 68.Line Total
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            totAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxLineTotPrcAmt_P, totAmt);

        EZDBBigDecimalItem listPrcAmt = null;
        // 69.List Price(Unit Gross Price)
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            listPrcAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.dealPrcListPrcAmt_P, listPrcAmt);
        //
        EZDBBigDecimalItem dsCpoLineNum = null;
        // 70.DS CPO Line Num
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            dsCpoLineNum = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineNum, dsCpoLineNum);

        EZDBBigDecimalItem dsCpoLineSubNum = null;
        // 71.DS CPO Line Sub Num
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            dsCpoLineSubNum = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineSubNum, dsCpoLineSubNum);

        EZDBBigDecimalItem dealSvcRevTrnsfAmt = null;
        // 72.
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            dealSvcRevTrnsfAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.dealSvcRevTrnsfAmt_P, dealSvcRevTrnsfAmt);

        EZDBBigDecimalItem dealSvcCostTrnsfAmt = null;
        // 73.
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            dealSvcCostTrnsfAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.dealSvcCostTrnsfAmt_P, dealSvcCostTrnsfAmt);

        EZDBBigDecimalItem dealManFlAdjAmt = null;
        // 74.
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            dealManFlAdjAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.dealManFlAdjAmt_P, dealManFlAdjAmt);

        EZDBBigDecimalItem dealManRepRevAdjAmt = null;
        // 75.
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            dealManRepRevAdjAmt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.dealManRepRevAdjAmt_P, dealManRepRevAdjAmt);

        EZDBBigDecimalItem xxTotUnitNetWt = null;
        // 76.
        if (arg[paramIx] instanceof EZDBBigDecimalItem) {
            xxTotUnitNetWt = (EZDBBigDecimalItem) arg[paramIx++];
        } else {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotUnitNetWt_P, xxTotUnitNetWt);

        //
        String sfxKeyPL = null;
        // 77.suffix key Price List
        if (arg[paramIx] instanceof EZDBStringItem) {
            sfxKeyPL = ((EZDBStringItem) arg[paramIx++]).getValue();
        } else {
            return;
        }

        EZDBMsgArray prcList = null;
        // 78.Price List
        if (arg[paramIx] instanceof EZDBMsgArray) {
            prcList = (EZDBMsgArray) arg[paramIx++];
        } else {
            return;
        }
        EZDMsg.copy(prcList, sfxKeyPL, scrnMsg.L, "PL");

        // 2018/05/13 S21_NA#25251 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxAuthFlg_PR, ZYPConstant.FLG_OFF_N);
        if (arg.length > MAX_INPUT_PARAM_NUM + 1) { // 2018/05/14 S21_NA#25251-2
            // 80.Price Change Authorization
            Object prmPriceChangeAuth = arg[paramIx++];
            if (prmPriceChangeAuth instanceof EZDBStringItem //
                    && ZYPCommonFunc.hasValue((EZDBStringItem) prmPriceChangeAuth)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxAuthFlg_PR, (EZDBStringItem) prmPriceChangeAuth);
            } else if (prmPriceChangeAuth instanceof String //
                    && ZYPCommonFunc.hasValue((String) prmPriceChangeAuth)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxAuthFlg_PR, (String) prmPriceChangeAuth);
            }
        }
        // 2018/05/13 S21_NA#25251 Add End
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxModeCd)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {"Mode" });
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcBaseDt)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {"Price Based Date" });
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcCalcDt)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {"Pricing Calculation Date" });
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdTpCd)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {"DS Order Type Code" });
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgCd)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {"DS Order Category Code" });
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.lineBizTpCd)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {"Line of Business" });
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.cpoSrcTpCd)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {"CPO Source Type" });
            return;
        }
        if(!NWAL1660Constant.PROCESS_LVL_HEADER.equals(scrnMsg.xxModeCd_PM.getValue())){ // QC#22965 2018/04/11 Add
            if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum_P)) {
                scrnMsg.setMessageInfo(ZZM9000E, new String[] {"Config ID" });
                return;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.trxLineNum_P)) {
                scrnMsg.setMessageInfo(ZZM9000E, new String[] {"Transaction Line Number" });
                return;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.inEachQty_P)) {
                scrnMsg.setMessageInfo(ZZM9000E, new String[] {"Each Quantity" });
                return;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.mdseCd_P)) {
                scrnMsg.setMessageInfo(ZZM9000E, new String[] {"Merchandise Code" });
                return;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.uomCd_P)) {
                scrnMsg.setMessageInfo(ZZM9000E, new String[] {"Unit of Measure Code" });
                return;
            }
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.ordCustUomQty_P)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {"Unit of Measure Quantity" });
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.invQty_P)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {"Invoiced Quantity" });
            return;
        }
    }

    /**
     * <pre>
     * @param handler   EZDCommonHandler
     * @param scrnMsg   NWAL1660BMsg
     * </pre>
     */
    public static void setScreenItemCondition(EZDCommonHandler handler, NWAL1660BMsg scrnMsg) {

        boolean isProtect = !MODE_REGIST.equals(scrnMsg.xxModeCd.getValue());
        boolean isHeaderLvl = NWAL1660Constant.PROCESS_LVL_HEADER.equals(scrnMsg.xxModeCd_PM.getValue());

        scrnMsg.xxSubTotCalcPrcAmt.setInputProtected(true);
        scrnMsg.xxTotChrgPrcAmt.setInputProtected(true);
        scrnMsg.xxTotTaxAmt.setInputProtected(true);
        scrnMsg.xxLineTotPrcAmt.setInputProtected(true);
        scrnMsg.ccyCd.setInputProtected(true);
        scrnMsg.ordCustUomQty.setInputProtected(true);
        scrnMsg.uomCd.setInputProtected(true);
        scrnMsg.inEachQty.setInputProtected(true);
        // Add Start 2016/09/09 QC#11614
        scrnMsg.mdseDescShortTxt.setInputProtected(true);
        // Add End 2016/09/09 QC#11614

        scrnMsg.dealSvcRevTrnsfAmt.setInputProtected(isProtect || isHeaderLvl);
        scrnMsg.dealSvcCostTrnsfAmt.setInputProtected(isProtect || isHeaderLvl);
        scrnMsg.dealManFlAdjAmt.setInputProtected(isProtect || isHeaderLvl);
        scrnMsg.dealManRepRevAdjAmt.setInputProtected(isProtect || isHeaderLvl);

        scrnMsg.specCondPrcPk_AS.setInputProtected(isProtect);
        scrnMsg.specCondPrcPk_BS.setInputProtected(isProtect);

        // 2018/05/13 S21_NA#25251 Add Start
        boolean isPrcChangeAuthorized = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, scrnMsg.xxAuthFlg_PR.getValue());
        // 2018/05/13 S21_NA#25251 Add End

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NWAL1660_ABMsg abMsg = scrnMsg.A.no(i);
            // QC#22965 2018/04/11 Add Start
            boolean isRuleHeaderLvl = PRC_RULE_ADJ_LVL.HEADER.equals(abMsg.prcRuleAdjLvlCd_A.getValue());
            // QC#22965 2018/04/11 Add End
            abMsg.prcRuleNm_A.setInputProtected(true);
            abMsg.prcRuleHdrPk_A.setInputProtected(true);
            abMsg.prcRuleCatgDescTxt_A.setInputProtected(true);
            abMsg.prcRuleAttrbDescTxt_A.setInputProtected(true);
            abMsg.prcRuleDtlChrgNm_A.setInputProtected(true);
            abMsg.prcRuleAdjTpDescTxt_A.setInputProtected(true);
            abMsg.applyAutoFlg_A.setInputProtected(true);
            if (isProtect) {
                abMsg.prcCondManDelFlg_A.setInputProtected(true);
                abMsg.manPrcAmtRate_A.setInputProtected(true);
                abMsg.unitPrcAmt_A.setInputProtected(true);
            // QC#22965 2018/04/11 Add Start
            } else if (isHeaderLvl != isRuleHeaderLvl) {
                abMsg.prcCondManDelFlg_A.setInputProtected(true);
                abMsg.manPrcAmtRate_A.setInputProtected(true);
                abMsg.unitPrcAmt_A.setInputProtected(true);
            // QC#22965 2018/04/11 Add End
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(abMsg.applyAutoFlg_A.getValue())) {
                    if ((ZYPConstant.FLG_ON_Y.equals(abMsg.ovrdFlg_A.getValue()))) {
                        abMsg.prcCondManDelFlg_A.setInputProtected(false);
                        abMsg.manPrcAmtRate_A.setInputProtected(!PRC_COND_UNIT.PCT.equals(abMsg.prcCondUnitCd_A.getValue()));
                        abMsg.unitPrcAmt_A.setInputProtected(PRC_COND_UNIT.PCT.equals(abMsg.prcCondUnitCd_A.getValue()));
                    } else {
                        abMsg.prcCondManDelFlg_A.setInputProtected(true);
                        abMsg.manPrcAmtRate_A.setInputProtected(true);
                        abMsg.unitPrcAmt_A.setInputProtected(true);
                    }
                } else {
                    abMsg.prcCondManDelFlg_A.setInputProtected(false);
                    abMsg.manPrcAmtRate_A.setInputProtected(!PRC_COND_UNIT.PCT.equals(abMsg.prcCondUnitCd_A.getValue()));
                    abMsg.unitPrcAmt_A.setInputProtected(PRC_COND_UNIT.PCT.equals(abMsg.prcCondUnitCd_A.getValue()));
                }
            }
            abMsg.funcUnitListPrcAmt_A.setInputProtected(true);
            abMsg.funcNetSellPrcAmt_A.setInputProtected(true);
            // 2018/05/13 S21_NA#25251 Add Start
            if (!isPrcChangeAuthorized) {
                abMsg.unitPrcAmt_A.setInputProtected(true);
            }
            // 2018/05/13 S21_NA#25251 Add End
        }
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            NWAL1660_BBMsg bbMsg = scrnMsg.B.no(i);
            boolean isRuleHeaderLvl = PRC_RULE_ADJ_LVL.HEADER.equals(bbMsg.prcRuleAdjLvlCd_B.getValue());

            bbMsg.prcRuleNm_B.setInputProtected(true);
            bbMsg.prcRuleHdrPk_B.setInputProtected(true);
            bbMsg.prcRuleCatgDescTxt_B.setInputProtected(true);
            bbMsg.prcRuleAttrbDescTxt_B.setInputProtected(true);
            bbMsg.prcRuleDtlChrgNm_B.setInputProtected(true);
            bbMsg.prcRuleAdjTpDescTxt_B.setInputProtected(true);
            bbMsg.applyAutoFlg_B.setInputProtected(true);
            if (isProtect) {
                bbMsg.prcCondManDelFlg_B.setInputProtected(true);
                bbMsg.manPrcAmtRate_B.setInputProtected(true);
                bbMsg.unitPrcAmt_B.setInputProtected(true);
            // QC#22965 2018/04/11 Add Start
            } else if (isHeaderLvl != isRuleHeaderLvl) {
                bbMsg.prcCondManDelFlg_B.setInputProtected(true);
                bbMsg.manPrcAmtRate_B.setInputProtected(true);
                bbMsg.unitPrcAmt_B.setInputProtected(true);
            // QC#22965 2018/04/11 Add End
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(bbMsg.applyAutoFlg_B.getValue())) {
                    if ((ZYPConstant.FLG_ON_Y.equals(bbMsg.ovrdFlg_B.getValue()))) {
                        bbMsg.prcCondManDelFlg_B.setInputProtected(false);
                        bbMsg.manPrcAmtRate_B.setInputProtected(!PRC_COND_UNIT.PCT.equals(bbMsg.prcCondUnitCd_B.getValue()));
                        bbMsg.unitPrcAmt_B.setInputProtected(PRC_COND_UNIT.PCT.equals(bbMsg.prcCondUnitCd_B.getValue()));
                    } else {
                        bbMsg.prcCondManDelFlg_B.setInputProtected(true);
                        bbMsg.manPrcAmtRate_B.setInputProtected(true);
                        bbMsg.unitPrcAmt_B.setInputProtected(true);
                    }
                } else {
                    bbMsg.prcCondManDelFlg_B.setInputProtected(false);
                    bbMsg.manPrcAmtRate_B.setInputProtected(!PRC_COND_UNIT.PCT.equals(bbMsg.prcCondUnitCd_B.getValue()));
                    bbMsg.unitPrcAmt_B.setInputProtected(PRC_COND_UNIT.PCT.equals(bbMsg.prcCondUnitCd_B.getValue()));
                }
            }
            // 2018/05/13 S21_NA#25251 Add Start
            if (!isPrcChangeAuthorized) {
                bbMsg.unitPrcAmt_B.setInputProtected(true);
            }
            // 2018/05/13 S21_NA#25251 Add End
        }
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            NWAL1660_CBMsg abMsg = scrnMsg.C.no(i);
            abMsg.prcCondTpDescTxt_C.setInputProtected(true);
            abMsg.autoPrcAmtRate_C.setInputProtected(true);
            abMsg.calcPrcAmtRate_C.setInputProtected(true);
        }
    }

    /**
     * addCheckItemBizLayerErr
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemBizLayerErr(NWAL1660BMsg scrnMsg) {

        // Header
        scrnMsg.addCheckItem(scrnMsg.dealSvcRevTrnsfAmt);
        scrnMsg.addCheckItem(scrnMsg.dealSvcCostTrnsfAmt);
        scrnMsg.addCheckItem(scrnMsg.dealManFlAdjAmt);
        scrnMsg.addCheckItem(scrnMsg.dealManRepRevAdjAmt);

        // Detail
        scrnMsg.addCheckItem(scrnMsg.specCondPrcPk_AS);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1660_ABMsg aScrnMsg = scrnMsg.A.no(i);

            scrnMsg.addCheckItem(aScrnMsg.prcCondManDelFlg_A);
            scrnMsg.addCheckItem(aScrnMsg.manPrcAmtRate_A);
            scrnMsg.addCheckItem(aScrnMsg.unitPrcAmt_A);
        }
        scrnMsg.addCheckItem(scrnMsg.specCondPrcPk_BS);
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1660_BBMsg bScrnMsg = scrnMsg.B.no(i);

            scrnMsg.addCheckItem(bScrnMsg.prcCondManDelFlg_B);
            scrnMsg.addCheckItem(bScrnMsg.manPrcAmtRate_B);
            scrnMsg.addCheckItem(bScrnMsg.unitPrcAmt_B);
        }
    }

    /**
     * set output parameter
     * @param arg Object[]
     * @param bizMsg NWAL1660BMsg
     */
    public static void setOutputParam(Object[] arg, NWAL1660CMsg bizMsg) {

        int prmIx = 0;

        EZDBStringItem xxModeCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxModeCd, bizMsg.xxModeCd);

        // QC#22965 2018/04/11 Add Start
        EZDBStringItem xxModeCd_PM = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxModeCd_PM, bizMsg.xxModeCd_PM);
        // QC#22965 2018/04/11 Add End

        // QC#9700  2018/09/03 Add Start
        EZDBStringItem xxViewChngLogSrcCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxViewChngLogSrcCd, bizMsg.xxViewChngLogSrcCd);
        // QC#9700  2018/09/03 Add End

        // 2018/01/25 S21_NA#23334 Mod Start
//        EZDBStringItem trxHdrNum = (EZDBStringItem) arg[prmIx++];
//        ZYPEZDItemValueSetter.setValue(trxHdrNum, bizMsg.trxHdrNum);
        EZDBStringItem ordSrcRefNum = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(ordSrcRefNum, bizMsg.ordSrcRefNum);
        // 2018/01/25 S21_NA#23334 Mod End

        EZDBDateItem prcBaseDt = (EZDBDateItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(prcBaseDt, bizMsg.prcBaseDt);

        EZDBDateItem prcCalcDt = (EZDBDateItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(prcCalcDt, bizMsg.prcCalcDt);

        EZDBStringItem dsOrdTpCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(dsOrdTpCd, bizMsg.dsOrdTpCd);

        EZDBStringItem dsOrdCatgCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(dsOrdCatgCd, bizMsg.dsOrdCatgCd);

        EZDBStringItem lineBizTpCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(lineBizTpCd, bizMsg.lineBizTpCd);

        EZDBStringItem cpoSrcTpCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(cpoSrcTpCd, bizMsg.cpoSrcTpCd);

        EZDBStringItem custIssPoNum = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(custIssPoNum, bizMsg.custIssPoNum);

        EZDBStringItem dsPmtMethCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(dsPmtMethCd, bizMsg.dsPmtMethCd);

        EZDBStringItem spclHdlgTpCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(spclHdlgTpCd, bizMsg.spclHdlgTpCd);

        EZDBStringItem leasePrchOptCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(leasePrchOptCd, bizMsg.leasePrchOptCd);

        // Order Line Info.
        EZDBStringItem dsOrdPosnNum = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(dsOrdPosnNum, bizMsg.dsOrdPosnNum);

        EZDBStringItem trxLineNum = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(trxLineNum, bizMsg.trxLineNum_P);

        EZDBStringItem trxLineSubNum = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(trxLineSubNum, bizMsg.trxLineSubNum_P);

        EZDBStringItem configCatgCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(configCatgCd, bizMsg.configCatgCd_P);

        EZDBBigDecimalItem inEachQty = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(inEachQty, bizMsg.inEachQty);

        EZDBStringItem shipToFirstLineAddr = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(shipToFirstLineAddr, bizMsg.shipToFirstLineAddr_P);

        EZDBStringItem shipToScdLineAddr = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(shipToScdLineAddr, bizMsg.shipToScdLineAddr_P);

        EZDBStringItem shipToCtyAddr = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(shipToCtyAddr, bizMsg.shipToCtyAddr_P);

        EZDBStringItem shipToStCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(shipToStCd, bizMsg.shipToStCd_P);

        EZDBStringItem shipToCntyNm = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(shipToCntyNm, bizMsg.shipToCntyNm_P);

        EZDBStringItem shipToPostCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(shipToPostCd, bizMsg.shipToPostCd_P);

        EZDBStringItem shipToCtryCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(shipToCtryCd, bizMsg.shipToCtryCd_P);

        EZDBStringItem prcCatgCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(prcCatgCd, bizMsg.prcCatgCd_P);

        EZDBStringItem csmpNum = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(csmpNum, bizMsg.csmpNum_P);

        EZDBStringItem dlrRefNum = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(dlrRefNum, bizMsg.dlrRefNum_P);

        EZDBStringItem prcContrNum = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(prcContrNum, bizMsg.prcContrNum_P);

        EZDBStringItem coaBrCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(coaBrCd, bizMsg.coaBrCd_P);

        EZDBStringItem mdseCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(mdseCd, bizMsg.mdseCd_P);

        EZDBStringItem billToCustCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(billToCustCd, bizMsg.billToCustCd_P);

        EZDBStringItem billToCustAcctCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(billToCustAcctCd, bizMsg.billToCustAcctCd_P);

        EZDBStringItem sellToCustCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(sellToCustCd, bizMsg.sellToCustCd_P);

        EZDBStringItem soldToCustLocCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(soldToCustLocCd, bizMsg.soldToCustLocCd_P);

        EZDBStringItem shipToCustCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(shipToCustCd, bizMsg.shipToCustCd_P);

        EZDBStringItem shipToCustAcctCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(shipToCustAcctCd, bizMsg.shipToCustAcctCd_P);

        EZDBStringItem frtCondCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(frtCondCd, bizMsg.frtCondCd_P);

        EZDBStringItem shpgSvcLvlCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(shpgSvcLvlCd, bizMsg.shpgSvcLvlCd_P);

        EZDBStringItem ccyCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(ccyCd, bizMsg.ccyCd);

        EZDBStringItem uomCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(uomCd, bizMsg.uomCd);

        EZDBBigDecimalItem ordCustUomQty = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(ordCustUomQty, bizMsg.ordCustUomQty);

        EZDBStringItem dsCpoLineCatgCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(dsCpoLineCatgCd, bizMsg.dsCpoLineCatgCd_P);

        EZDBBigDecimalItem invQty = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(invQty, bizMsg.invQty_P);

        EZDBBigDecimalItem mdlId = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(mdlId, bizMsg.mdlId_P);

        EZDBStringItem rtrnRsnCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(rtrnRsnCd, bizMsg.rtrnRsnCd_P);

        EZDBStringItem coaExtnCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(coaExtnCd, bizMsg.coaExtnCd_P);

        EZDBStringItem slsRepOrSlsTeamTocCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(slsRepOrSlsTeamTocCd, bizMsg.slsRepOrSlsTeamTocCd_P);

        EZDBStringItem rtlWhCd = (EZDBStringItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(rtlWhCd, bizMsg.rtlWhCd_P);

        EZDBBigDecimalItem xxTotBaseAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxTotBaseAmt, bizMsg.xxTotBaseAmt_P);

        EZDBBigDecimalItem xxSubTotCalcPrcAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxSubTotCalcPrcAmt, bizMsg.xxSubTotCalcPrcAmt);

        EZDBBigDecimalItem xxTotChrgPrcAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxTotChrgPrcAmt, bizMsg.xxTotChrgPrcAmt);

        EZDBBigDecimalItem xxTotDiscAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxTotDiscAmt, bizMsg.xxTotDiscAmt_P);

        EZDBBigDecimalItem xxTotSpclPrcAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxTotSpclPrcAmt, bizMsg.xxTotSpclPrcAmt_P);

        EZDBBigDecimalItem xxTotNetDiscAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxTotNetDiscAmt, bizMsg.xxTotNetDiscAmt_P);

        EZDBBigDecimalItem xxUnitNetPrcAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxUnitNetPrcAmt, bizMsg.xxUnitNetPrcAmt_P);

        EZDBBigDecimalItem xxUnitGrsPrcAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxUnitGrsPrcAmt, bizMsg.xxUnitGrsPrcAmt_P);

        EZDBBigDecimalItem xxTotNetPrcAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxTotNetPrcAmt, bizMsg.xxTotNetPrcAmt_P);

        EZDBBigDecimalItem xxGrsAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxGrsAmt, bizMsg.xxGrsAmt_P);

        EZDBBigDecimalItem xxUnitFrtAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxUnitFrtAmt, bizMsg.xxUnitFrtAmt_P);

        EZDBBigDecimalItem xxTotFrtAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxTotFrtAmt, bizMsg.xxTotFrtAmt_P);

        EZDBBigDecimalItem xxUnitSpclChrgAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxUnitSpclChrgAmt, bizMsg.xxUnitSpclChrgAmt_P);

        EZDBBigDecimalItem xxTotSpclChrgAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxTotSpclChrgAmt, bizMsg.xxTotSpclChrgAmt_P);

        EZDBBigDecimalItem xxTotFrtSubAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxTotFrtSubAmt, bizMsg.xxTotFrtSubAmt_P);

        EZDBBigDecimalItem xxUnitRestkFeeAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxUnitRestkFeeAmt, bizMsg.xxUnitRestkFeeAmt_P);

        EZDBBigDecimalItem xxTotRestkFeeAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxTotRestkFeeAmt, bizMsg.xxTotRestkFeeAmt_P);

        EZDBBigDecimalItem xxTotNetPrcAmt2 = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxTotNetPrcAmt2, bizMsg.xxTotNetPrcAmt_P2);

        EZDBBigDecimalItem xxTotTaxAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxTotTaxAmt, bizMsg.xxTotTaxAmt);

        EZDBBigDecimalItem totPrcAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(totPrcAmt, bizMsg.xxUnitPrc_P);

        EZDBBigDecimalItem xxLineTotPrcAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxLineTotPrcAmt, bizMsg.xxLineTotPrcAmt);

        EZDBBigDecimalItem dealPrcListPrcAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(dealPrcListPrcAmt, bizMsg.dealPrcListPrcAmt_P);

        EZDBBigDecimalItem dsCpoLineNum = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(dsCpoLineNum, bizMsg.dsCpoLineNum);

        EZDBBigDecimalItem dsCpoLineSubNum = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(dsCpoLineSubNum, bizMsg.dsCpoLineSubNum);

        EZDBBigDecimalItem dealSvcRevTrnsfAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(dealSvcRevTrnsfAmt, bizMsg.dealSvcRevTrnsfAmt);

        EZDBBigDecimalItem dealSvcCostTrnsfAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(dealSvcCostTrnsfAmt, bizMsg.dealSvcCostTrnsfAmt);

        EZDBBigDecimalItem dealManFlAdjAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(dealManFlAdjAmt, bizMsg.dealManFlAdjAmt);

        EZDBBigDecimalItem dealManRepRevAdjAmt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(dealManRepRevAdjAmt, bizMsg.dealManRepRevAdjAmt);

        EZDBBigDecimalItem xxTotUnitNetWt = (EZDBBigDecimalItem) arg[prmIx++];
        ZYPEZDItemValueSetter.setValue(xxTotUnitNetWt, bizMsg.xxTotUnitNetWt_P);

        EZDBStringItem pSfx = (EZDBStringItem) arg[prmIx++];

        EZDBMsgArray pPMsgArray = (EZDBMsgArray) arg[prmIx++];
        EZDMsg.copy(bizMsg.L, "PL", pPMsgArray, pSfx.getValue());

        return;
    }

    /**
     * <pre>
     * @param scrnMsg   NWAL1660BMsg
     * </pre>
     */
    public static void checkDuplicatePriceRuleForCharges(NWAL1660BMsg scrnMsg) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.specCondPrcPk_BS)) {
            scrnMsg.specCondPrcPk_BS.setErrorInfo(1, ZZM9000E, new String[] {"Price Rule" });
            return;
        }
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1660_BBMsg bScrnMsg = scrnMsg.B.no(i);
            if (scrnMsg.specCondPrcPk_BS.getValue().equals(bScrnMsg.specCondPrcPk_B.getValue())) {
                scrnMsg.specCondPrcPk_BS.setErrorInfo(1, NWAM0553E);
                return;
            }
        }
    }

    /**
     * <pre>
     * @param scrnMsg   NWAL1660BMsg
     * </pre>
     */
    public static void checkDuplicatePriceRuleForSellPrice(NWAL1660BMsg scrnMsg) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.specCondPrcPk_AS)) {
            scrnMsg.specCondPrcPk_AS.setErrorInfo(1, ZZM9000E, new String[] {"Price Rule" });
            return;
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1660_ABMsg aScrnMsg = scrnMsg.A.no(i);
            if (scrnMsg.specCondPrcPk_AS.getValue().equals(aScrnMsg.specCondPrcPk_A.getValue())) {
                scrnMsg.specCondPrcPk_AS.setErrorInfo(1, NWAM0553E);
                return;
            }
        }
    }

    /**
     * <pre>
     * @param scrnMsg   NWAL1660BMsg
     * </pre>
     */
    public static void checkSelectedForCharges(NWAL1660BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).prcCondManDelFlg_B.getValue())) {
                return;
            }
        }
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            // Mod Start 2017/08/17 QC#20673
            // scrnMsg.B.no(i).prcCondManDelFlg_B.setErrorInfo(i, NWAM0186E);
            scrnMsg.B.no(i).prcCondManDelFlg_B.setErrorInfo(1, NWAM0186E);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcCondManDelFlg_B);
            // Mod End   2017/08/17 QC#20673
        }
    }

    /**
     * <pre>
     * @param scrnMsg   NWAL1660BMsg
     * </pre>
     */
    public static void checkSelectedForSellPrice(NWAL1660BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).prcCondManDelFlg_A.getValue())) {
                return;
            }
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // Mod Start 2017/08/17 QC#20673
            //scrnMsg.A.no(i).prcCondManDelFlg_A.setErrorInfo(i, NWAM0186E);
            scrnMsg.A.no(i).prcCondManDelFlg_A.setErrorInfo(1, NWAM0186E);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcCondManDelFlg_A);
            // Mod End   2017/08/17 QC#20673
        }
    }

    /**
     * <pre>
     * @param handler   S21CommonHandler
     * @param scrnMsg   NWAL1660BMsg
     * </pre>
     */
    public static void initBizBtnProp(S21CommonHandler handler, NWAL1660BMsg scrnMsg) {
        int btnSts = 1; // enable
        int calcBtnSts = 1; // enable
        if (!MODE_REGIST.equals(scrnMsg.xxModeCd.getValue())) {
            btnSts = 0; // disable
        }
        calcBtnSts = btnSts;
        if(NWAL1660Constant.PROCESS_LVL_HEADER.equals(scrnMsg.xxModeCd_PM.getValue())){
            calcBtnSts = 0;
        }

        handler.setButtonProperties(BTN_CALCULATE[0], BTN_CALCULATE[1], BTN_CALCULATE[2], calcBtnSts, null);
        handler.setButtonProperties(BTN_RESET[0], BTN_RESET[1], BTN_RESET[2], btnSts, null);

        handler.setButtonProperties(BTN_ADD_SELL_PRICE[0], BTN_ADD_SELL_PRICE[1], BTN_ADD_SELL_PRICE[2], btnSts, null);
        handler.setButtonProperties(BTN_DEL_SELL_PRICE[0], BTN_DEL_SELL_PRICE[1], BTN_DEL_SELL_PRICE[2], btnSts, null);
        // 2016/03/04 QC#2176 Start
        handler.setButtonProperties(BTN_ADD_CHARGES[0], BTN_ADD_CHARGES[1], BTN_ADD_CHARGES[2], btnSts, null);
        handler.setButtonProperties(BTN_DEL_CHARGES[0], BTN_DEL_CHARGES[1], BTN_DEL_CHARGES[2], btnSts, null);
        // 2016/03/04 QC#2176 End
        if (!ZYPCommonFunc.hasValue(scrnMsg.specCondPrcPk_AL.no(0))) {
            btnSts = 0; // disable
            handler.setButtonProperties(BTN_ADD_SELL_PRICE[0], BTN_ADD_SELL_PRICE[1], BTN_ADD_SELL_PRICE[2], btnSts, null);
            handler.setButtonProperties(BTN_DEL_SELL_PRICE[0], BTN_DEL_SELL_PRICE[1], BTN_DEL_SELL_PRICE[2], btnSts, null);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.specCondPrcPk_BL.no(0))) {
            btnSts = 0; // disable
            handler.setButtonProperties(BTN_ADD_CHARGES[0], BTN_ADD_CHARGES[1], BTN_ADD_CHARGES[2], btnSts, null);
            handler.setButtonProperties(BTN_DEL_CHARGES[0], BTN_DEL_CHARGES[1], BTN_DEL_CHARGES[2], btnSts, null);
        }

        // 2018/05/13 S21_NA#25251 Add Start
        if (!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, scrnMsg.xxAuthFlg_PR.getValue())) {
            // Sell Price, Add Button and Delete Button
            handler.setButtonProperties(BTN_ADD_SELL_PRICE[0], BTN_ADD_SELL_PRICE[1], BTN_ADD_SELL_PRICE[2], 0, null);
            handler.setButtonProperties(BTN_DEL_SELL_PRICE[0], BTN_DEL_SELL_PRICE[1], BTN_DEL_SELL_PRICE[2], 0, null);

            // Charges, Add Button and Delete Button
            handler.setButtonProperties(BTN_ADD_CHARGES[0], BTN_ADD_CHARGES[1], BTN_ADD_CHARGES[2], 0, null);
            handler.setButtonProperties(BTN_DEL_CHARGES[0], BTN_DEL_CHARGES[1], BTN_DEL_CHARGES[2], 0, null);
        }
        // 2018/05/13 S21_NA#25251 Add End
        // QC#9700  2018/09/03 Add Start
        handler.setButtonProperties(BTN_ANALYSIS_SELL_PRICE[0], BTN_ANALYSIS_SELL_PRICE[1], BTN_ANALYSIS_SELL_PRICE[2], 1, null);
        handler.setButtonProperties(BTN_ANALYSIS_CHARGES[0], BTN_ANALYSIS_CHARGES[1], BTN_ANALYSIS_CHARGES[2], 1, null);

        handler.setButtonProperties(BTN_HISTORY[0], BTN_HISTORY[1], BTN_HISTORY[2], 1, null);
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxViewChngLogSrcCd) || !ZYPCommonFunc.hasValue(scrnMsg.ordSrcRefNum)) {
            handler.setButtonProperties(BTN_HISTORY[0], BTN_HISTORY[1], BTN_HISTORY[2], 0, null);
        }
        // QC#9700  2018/09/03 Add End
    }

    //Add Start NA QC#2177
    /**
     * Set Display Line Number
     * 
     * @param scrnMsg NWAL1660BMsg
     */
    public static void setDispLineNum(NWAL1660BMsg scrnMsg) {
        // QC#22965 2018/04/11 Add Start
        if(!ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum)){
            scrnMsg.xxLineNum_DI.clear();
            return;
        }
        // QC#22965 2018/04/11 Add End
        StringBuilder dispLineNum = new StringBuilder();
        dispLineNum.append(scrnMsg.dsOrdPosnNum.getValue());
        dispLineNum.append(".");
        dispLineNum.append(scrnMsg.dsCpoLineNum.getValue());
        scrnMsg.xxLineNum_DI.setValue(dispLineNum.toString());
    }
    //Add End   NA QC#2177

    // QC#9700  2018/09/03 Add Start
    public static Object[] getParamNWAL1900ForSellPrice(NWAL1660BMsg scrnMsg){
        // 2018/11/27 QC#29361 Mod Start
//        Object[] params = new Object[4];
//        params[IDX_0] = scrnMsg.xxModeCd_PM.getValue();
//        params[IDX_1] = scrnMsg.lineBizTpCd.getValue();
//        params[IDX_2] = PRC_RULE_MOD_TP.SELL_PRICE;
//        params[IDX_3] = scrnMsg.L;
        Object[] params = new Object[6];
        params[IDX_0] = scrnMsg.xxModeCd_PM.getValue();
        params[IDX_1] = scrnMsg.lineBizTpCd.getValue();
        params[IDX_2] = PRC_RULE_MOD_TP.SELL_PRICE;
        params[IDX_3] = scrnMsg.L;
        params[IDX_4] = scrnMsg.dsOrdCatgCd.getValue();
        params[IDX_5] = scrnMsg.dsOrdTpCd.getValue();
        // 2018/11/27 QC#29361 Mod End
        return params;
    }

    public static Object[] getParamNWAL1900ForCharges(NWAL1660BMsg scrnMsg){
        // 2018/11/27 QC#29361 Mod Start
//        Object[] params = new Object[4];
//        params[IDX_0] = scrnMsg.xxModeCd_PM.getValue();
//        params[IDX_1] = scrnMsg.lineBizTpCd.getValue();
//        params[IDX_2] = PRC_RULE_MOD_TP.CHARGES;
//        params[IDX_3] = scrnMsg.L;
        Object[] params = new Object[6];
        params[IDX_0] = scrnMsg.xxModeCd_PM.getValue();
        params[IDX_1] = scrnMsg.lineBizTpCd.getValue();
        params[IDX_2] = PRC_RULE_MOD_TP.CHARGES;
        params[IDX_3] = scrnMsg.L;
        params[IDX_4] = scrnMsg.dsOrdCatgCd.getValue();
        params[IDX_5] = scrnMsg.dsOrdTpCd.getValue();
        // 2018/11/27 QC#29361 Mod End
        return params;
    }
    public static Object[] getParamNWAL1910(NWAL1660BMsg scrnMsg){
        Object[] params = new Object[IDX_6];
        params[IDX_0] = scrnMsg.xxViewChngLogSrcCd;
        params[IDX_1] = scrnMsg.ordSrcRefNum;
        params[IDX_2] = scrnMsg.dsOrdPosnNum;
        params[IDX_3] = scrnMsg.dsCpoLineNum;
        params[IDX_4] = scrnMsg.xxModeCd_PM;
        params[IDX_5] = scrnMsg.configCatgCd_P;
        return params;
    }
    // QC#9700  2018/09/03 Add End

}
