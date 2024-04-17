/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7010.common;

import static business.blap.NMAL7010.constant.NMAL7010Constant.HIGH_VAL_DT;
import static business.blap.NMAL7010.constant.NMAL7010Constant.MODE_CD_KEY_NAME;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM0163E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM0179E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8020E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.NMAM8216E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.RATE_SCALE;
import static business.blap.NMAL7010.constant.NMAL7010Constant.REF_ENTITY_ITEM;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_EQUIP;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_LEASE_RATE;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_LEASE_RTRN_FEE;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_QTY_DISC;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SPLY_PGM;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_SPEC_CHRG;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_TIER;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_TI;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_TRX_DRV_EQUIP;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_TRX_DRV_SVC;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TAB_TRX_DRV_SVC_TIER;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TBL_A;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TBL_B;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TBL_C;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TBL_D;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TBL_E;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TBL_F;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TBL_G;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TBL_H;
import static business.blap.NMAL7010.constant.NMAL7010Constant.TBL_I;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDCStringItem;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7010.NMAL7010CMsg;
import business.blap.NMAL7010.NMAL7010Query;
import business.blap.NMAL7010.NMAL7010SMsg;
import business.blap.NMAL7010.NMAL7010_ACMsg;
import business.blap.NMAL7010.NMAL7010_ASMsg;
import business.blap.NMAL7010.NMAL7010_ISMsg;
import business.blap.NMAL7010.NMAL7010_ISMsgArray;
import business.blap.NMAL7010.NMAL7010_JCMsgArray;
import business.blap.NMAL7010.NMAL7010_JSMsg;
import business.blap.NMAL7010.NMAL7010_JSMsgArray;
import business.blap.NMAL7010.NMAL7010_QCMsg;
import business.blap.NMAL7010.constant.NMAL7010Constant;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.UPLD_CSV_HDRTMsg;
import business.db.UPLD_CSV_HDRTMsgArray;
import business.file.NMA7010001FMsg;
import business.file.NMA7010002FMsg;
import business.file.NMA7010003FMsg;
import business.file.NMA7010004FMsg;
import business.file.NMA7010005FMsg;
import business.file.NMA7010006FMsg;
import business.file.NMA7010007FMsg;
import business.file.NMA7010008FMsg;
import business.file.NMAL7010F11FMsg;
import business.file.NMAL7010F12FMsg;
import business.file.NMAL7010F13FMsg;
import business.file.NMAL7010F14FMsg;
import business.file.NMAL7010F15FMsg;
import business.file.NMAL7010F16FMsg;
import business.file.NMAL7010F17FMsg;
import business.file.NMAL7010F18FMsg;
import business.parts.NWZC170001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC170001.NWZC170001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfAttribute;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfEvent;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfKeyReference;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.Attribute;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.Event;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.KeyReference;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.OPERATIONTYPES;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.REFERENCETYPES;
import com.canon.usa.s21.integration.service.masterdata.messaging.wrapper.MasterDataMessagingServiceProxy;

/**
 *<pre>
 * NMAL7010CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/09   SRA             E.Inada         Update          QC#2174
 * 2016/03/22   Fujitsu         Y.Kanefusa      Update          QC#4767
 * 2016/04/12   SRA             E.Inada         Update          QC#6378
 * 2016/06/07   SRA             E.Inada         Update          QC#688
 * 2016/07/11   Fujitsu         W.Honda         Update          QC#10987
 * 2016/08/26   Fujitsu         R.Nakamura      Update          QC#3934
 * 2016/09/12   Fujitsu         R.Nakamura      Update          QC#11615
 * 2016/10/17   Fujitsu         W.Honda         Update          QC#15193
 * 2018/02/27   Fujitsu         A.Kosai         Update          QC#24422
 * 2018/04/06   Fujitsu         R.Nakamura      Update          QC#22556
 * 2018/05/08   Fujitsu         T.Noguchi       Update          QC#20269
 * 2018/05/15   Fujitsu         K.Ishizuka      Update          QC#26015
 * 2018/06/06   Fujitsu         H.Kumagai       Update          QC#26292
 * 2018/07/17   Fujitsu         H.Kumagai       Update          QC#20267
 * 2018/08/13   Fujitsu         M.Ishii         Update          QC#26092
 * 2018/08/22   Fujitsu         T.Noguchi       Update          QC#26664
 * 2018/08/29   Fujitsu         T.Gotoda        Update          QC#27787
 * 2018/11/17   Fujitsu         N.Sugiura       Update          QC#29147
 * 2018/11/27   Fujitsu         K.Kato          Update          QC#29319
 * 2018/12/18   Fujitsu         K.Kato          Update          QC#29661
 * 2019/02/05   Fujitsu         C.Hara          Update          QC#30114
 *</pre>
 */
public class NMAL7010CommonLogic {

    private static Logger logger = Logger.getLogger(NMAL7010CommonLogic.class);
    /**
     * loadOnePageToCMsg
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     */
    public static void loadOnePageToCMsg(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.A, glblMsg.A);
        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.B, glblMsg.B);
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.C, glblMsg.C);
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.D, glblMsg.D);
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.E, glblMsg.E);
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.F, glblMsg.F);
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.G, glblMsg.G);
        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
            loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.H, glblMsg.H);
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            loadOnePageToCMsg(bizMsg, glblMsg, bizMsg.I, glblMsg.I);
            loadQtyDtlToCMsg(bizMsg, bizMsg.J, glblMsg.J);
        }

        // Header Msg
        loadHeaderToCMsg(bizMsg, glblMsg);
    }

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDSMsg sMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NMAL7010CMsg bizMsg = (NMAL7010CMsg) cMsg;
        NMAL7010SMsg glblMsg = (NMAL7010SMsg) sMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        if (isPageAval(glblMsg)) {
            maxDisplayRows = getPerPageSize(glblMsg, cMsgArray);
        }
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + maxDisplayRows; i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);

                // Add Start 2018/04/09 QC#22556
                if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
                    createPullDownForUOM(bizMsg, indexOfCMsg);
                }
                // Add End 2018/04/09 QC#22556

            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
        if (!isPageAval(glblMsg)) {
            bizMsg.xxPageShowOfNum.setValue(bizMsg.xxPageShowToNum.getValue());
        }

        setTableMessage(bizMsg, glblMsg, cMsgArray, sMsgArray);
    }

    /**
     * 
     * @param bizMsg NMAL7010CMsg
     * @param jcMsg NMAL7010_JCMsgArray
     * @param jsMsg NMAL7010_JSMsgArray
     */
    public static void loadQtyDtlToCMsg(NMAL7010CMsg bizMsg, NMAL7010_JCMsgArray jcMsg, NMAL7010_JSMsgArray jsMsg) {
        int idx = bizMsg.xxRadioBtn_LI.getValueInt();
        int cnt = 0;

        ZYPTableUtil.clear(jcMsg);

        for (int i = 0; i < jsMsg.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.I.no(idx).prcListQtyDiscPk_PI)
                    && bizMsg.I.no(idx).prcListQtyDiscPk_PI.getValue().equals(
                            jsMsg.no(i).prcListQtyDiscPk_PJ.getValue())) {
                EZDMsg.copy(jsMsg.no(i), null, jcMsg.no(cnt), null);
                cnt++;
            } else if (ZYPCommonFunc.hasValue(bizMsg.I.no(idx).xxDtlCnt_PI)
                        && bizMsg.I.no(idx).xxDtlCnt_PI.getValue().equals(jsMsg.no(i).xxDtlCnt_PJ.getValue())) {
                EZDMsg.copy(jsMsg.no(i), null, jcMsg.no(cnt), null);
                cnt++;
            }
        }
        bizMsg.J.setValidCount(cnt);
    }

    /**
     * getQtyDiscSMsg.
     * @param isMsgArray NMAL7010_ISMsgArray
     * @param jsMsg NMAL7010_JSMsg
     * @return NMAL7010_ISMsg
     */
    public static NMAL7010_ISMsg getQtyDiscSMsg(NMAL7010_ISMsgArray isMsgArray, NMAL7010_JSMsg jsMsg) {
        NMAL7010_ISMsg isMsg   = null;
        for (int i = 0; i < isMsgArray.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(jsMsg.prcListQtyDiscPk_PJ)
                    && jsMsg.prcListQtyDiscPk_PJ.getValue().equals(
                            isMsgArray.no(i).prcListQtyDiscPk_PI.getValue())) {
                isMsg = isMsgArray.no(i);
                break;
            } else if (ZYPCommonFunc.hasValue(isMsgArray.no(i).xxDtlCnt_PI)
                    && isMsgArray.no(i).xxDtlCnt_PI.getValue().equals(jsMsg.xxDtlCnt_PJ.getValue())) {
                isMsg = isMsgArray.no(i);
                break;
            }
        }
        return isMsg;
    }

    /**
     * Update the global Message.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     */
    public static void updateGlblMsg(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {

        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;

        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (NMAL7010CheckLogic.isChangeForEquip(bizMsg.A.no(i), glblMsg.A.no(ixG + i))) {
                    glblMsg.A.no(ixG + i).xxModeCd_A1.setValue(NMAL7010Constant.MODE_MODIFY);
                }
                EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
            }
        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                if (NMAL7010CheckLogic.isChangeForSvc(bizMsg.B.no(i), glblMsg.B.no(ixG + i))) {
                    glblMsg.B.no(ixG + i).xxModeCd_B1.setValue(NMAL7010Constant.MODE_MODIFY);
                }
                EZDMsg.copy(bizMsg.B.no(i), null, glblMsg.B.no(ixG + i), null);
            }
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                if (NMAL7010CheckLogic.isChangeForSvcTier(bizMsg.C.no(i), glblMsg.C.no(ixG + i))) {
                    glblMsg.C.no(ixG + i).xxModeCd_C1.setValue(NMAL7010Constant.MODE_MODIFY);
                }
                EZDMsg.copy(bizMsg.C.no(i), null, glblMsg.C.no(ixG + i), null);
            }
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                if (NMAL7010CheckLogic.isChangeForSvcSpecChrg(bizMsg.D.no(i), glblMsg.D.no(ixG + i))) {
                    glblMsg.D.no(ixG + i).xxModeCd_D1.setValue(NMAL7010Constant.MODE_MODIFY);
                }
                EZDMsg.copy(bizMsg.D.no(i), null, glblMsg.D.no(ixG + i), null);
            }
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
                if (NMAL7010CheckLogic.isChangeForSplyPgm(bizMsg.E.no(i), glblMsg.E.no(ixG + i))) {
                    glblMsg.E.no(ixG + i).xxModeCd_E1.setValue(NMAL7010Constant.MODE_MODIFY);
                }
                EZDMsg.copy(bizMsg.E.no(i), null, glblMsg.E.no(ixG + i), null);
            }
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < bizMsg.F.getValidCount(); i++) {
                if (NMAL7010CheckLogic.isChangeForLeaseRate(bizMsg.F.no(i), glblMsg.F.no(ixG + i))) {
                    glblMsg.F.no(ixG + i).xxModeCd_F1.setValue(NMAL7010Constant.MODE_MODIFY);
                }
                EZDMsg.copy(bizMsg.F.no(i), null, glblMsg.F.no(ixG + i), null);
            }
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < bizMsg.G.getValidCount(); i++) {
                if (NMAL7010CheckLogic.isChangeForLeaseRtrnFee(bizMsg.G.no(i), glblMsg.G.no(ixG + i))) {
                    glblMsg.G.no(ixG + i).xxModeCd_G1.setValue(NMAL7010Constant.MODE_MODIFY);
                }
                EZDMsg.copy(bizMsg.G.no(i), null, glblMsg.G.no(ixG + i), null);
            }
        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
                if (NMAL7010CheckLogic.isChangeForTradeIn(bizMsg.H.no(i), glblMsg.H.no(ixG + i))) {
                    glblMsg.H.no(ixG + i).xxModeCd_H1.setValue(NMAL7010Constant.MODE_MODIFY);
                }
                EZDMsg.copy(bizMsg.H.no(i), null, glblMsg.H.no(ixG + i), null);
            }
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
                EZDMsg.copy(bizMsg.I.no(i), null, glblMsg.I.no(ixG + i), null);
            }
            for (int i = 0; i < bizMsg.J.getValidCount(); i++) {
                EZDMsg.copy(bizMsg.J.no(i), null, glblMsg.J.no(bizMsg.J.no(i).xxSortNum_PJ.getValueInt() - 1), null);
            }
        }

        // Header Msg
        loadHeaderToSMsg(bizMsg, glblMsg);

        bizMsg.setCommitSMsg(true);
    }

    /**
     * Select Unselect check Flag.
     * @param dplyTabD1 String
     * @param glblMsg NMAL7010SMsg
     * @param chkFlg String
     */
    public static void selectUnselect(String dplyTabD1, NMAL7010SMsg glblMsg, String chkFlg) {
        if (TAB_PRC_LIST_VAL_EQUIP.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(chkFlg)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxChkBox_PA, chkFlg);
                } else {
                    glblMsg.A.no(i).xxChkBox_PA.clear();
                }
            }
        } else if (TAB_PRC_LIST_VAL_SVC.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(chkFlg)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).xxChkBox_PB, chkFlg);
                } else {
                    glblMsg.B.no(i).xxChkBox_PB.clear();
                }
            }
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(chkFlg)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).xxChkBox_PC, chkFlg);
                } else {
                    glblMsg.C.no(i).xxChkBox_PC.clear();
                }
            }
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(chkFlg)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).xxChkBox_PD, chkFlg);
                } else {
                    glblMsg.D.no(i).xxChkBox_PD.clear();
                }
            }
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.E.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(chkFlg)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.E.no(i).xxChkBox_PE, chkFlg);
                } else {
                    glblMsg.E.no(i).xxChkBox_PE.clear();
                }
            }
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.F.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(chkFlg)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.F.no(i).xxChkBox_PF, chkFlg);
                } else {
                    glblMsg.F.no(i).xxChkBox_PF.clear();
                }
            }
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.G.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(chkFlg)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.G.no(i).xxChkBox_PG, chkFlg);
                } else {
                    glblMsg.G.no(i).xxChkBox_PG.clear();
                }
            }
        } else if (TAB_PRC_LIST_VAL_TI.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.H.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(chkFlg)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.H.no(i).xxChkBox_PH, chkFlg);
                } else {
                    glblMsg.H.no(i).xxChkBox_PH.clear();
                }
            }
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(chkFlg)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.I.no(i).xxChkBox_PI, chkFlg);
                } else {
                    glblMsg.I.no(i).xxChkBox_PI.clear();
                }
            }
        }
    }

    /**
     * setPrcStruTp
     * @param bizMsg NMAL7010CMsg
     */
    public static void setDplyTab(NMAL7010CMsg bizMsg) {

        // Price List Value
        if (PRC_LIST_STRU_TP.EQUIPMENT.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_D1, TAB_PRC_LIST_VAL_EQUIP);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm_DH, TBL_A);
        } else if (PRC_LIST_STRU_TP.SERVICE.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_D1, TAB_PRC_LIST_VAL_SVC);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm_DH, TBL_B);
        } else if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_D1, TAB_PRC_LIST_VAL_SVC_TIER);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm_DH, TBL_C);
        } else if (PRC_LIST_STRU_TP.SERVICE_SPECIAL_CHARGES.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_D1, TAB_PRC_LIST_VAL_SVC_SPEC_CHRG);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm_DH, TBL_D);
        } else if (PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_D1, TAB_PRC_LIST_VAL_SPLY_PGM);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm_DH, TBL_E);
        } else if (PRC_LIST_STRU_TP.LEASE_RATES.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_D1, TAB_PRC_LIST_VAL_LEASE_RATE);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm_DH, TBL_F);
        } else if (PRC_LIST_STRU_TP.LEASE_RETURN_FEES.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_D1, TAB_PRC_LIST_VAL_LEASE_RTRN_FEE);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm_DH, TBL_G);
        } else if (PRC_LIST_STRU_TP.TRADE_INS.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_D1, TAB_PRC_LIST_VAL_TI);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm_DH, TBL_H);
        } else if (PRC_LIST_STRU_TP.QTY_DISCOUNT.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_D1, TAB_PRC_LIST_VAL_QTY_DISC);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm_DH, TBL_I);
        // QC#10987 2016/07/11 Mod start
//        }
        } else {
            bizMsg.xxDplyTab_D1.clear();
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm_DH, TBL_A);
        }
        // QC#10987 2016/07/11 Mod end

        // Transaction Drivers
        if (PRC_LIST_STRU_TP.EQUIPMENT.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            // ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_D1, TAB_TRX_DRV_EQUIP);
            // ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm_DH, TBL_A);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H2, TAB_TRX_DRV_EQUIP);
        } else if (PRC_LIST_STRU_TP.SERVICE.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            // ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_D1, TAB_TRX_DRV_SVC);
            // ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm_DH, TBL_B);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H2, TAB_TRX_DRV_SVC);
        } else if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            // ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_D1, TAB_TRX_DRV_SVC_TIER);
            // ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm_DH, TBL_C);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H2, TAB_TRX_DRV_SVC_TIER);
        } else {
            bizMsg.xxDplyTab_H2.clear();
        }
    }

    /**
     * clearOtherTabData.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     */
    public static void clearOtherTabData(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        if (!PRC_LIST_STRU_TP.EQUIPMENT.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            bizMsg.prcQlfyTpCd_D1.clear();
            bizMsg.prcQlfyValTxt_D1.clear();
            // 2018/11/27 QC#29319 Add Start
            bizMsg.xxPrcQlfyValSrchTxt_D1.clear();
            // 2018/11/27 QC#29319 Add End
            ZYPTableUtil.clear(bizMsg.A);
            ZYPTableUtil.clear(glblMsg.A);
        } else if (!PRC_LIST_STRU_TP.SERVICE.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            bizMsg.mdlNm_D1.clear();
            bizMsg.prcMtrPkgNm_D1.clear();
            ZYPTableUtil.clear(bizMsg.B);
            ZYPTableUtil.clear(glblMsg.B);
        } else if (!PRC_LIST_STRU_TP.SERVICE_TIERS.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            bizMsg.mdlNm_D2.clear();
            bizMsg.prcMtrPkgNm_D2.clear();
            ZYPTableUtil.clear(bizMsg.C);
            ZYPTableUtil.clear(glblMsg.C);
        } else if (!PRC_LIST_STRU_TP.SERVICE_SPECIAL_CHARGES.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            ZYPTableUtil.clear(bizMsg.D);
            ZYPTableUtil.clear(glblMsg.D);
        } else if (!PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            bizMsg.mdlNm_D3.clear();
            bizMsg.prcMtrPkgNm_D3.clear();
            ZYPTableUtil.clear(bizMsg.E);
            ZYPTableUtil.clear(glblMsg.E);
        } else if (!PRC_LIST_STRU_TP.LEASE_RATES.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            bizMsg.prcPgmTpCd_DH.clear();
            bizMsg.prcEquipTpCd_DH.clear();
            bizMsg.mdlNm_D4.clear();
            ZYPTableUtil.clear(bizMsg.F);
            ZYPTableUtil.clear(glblMsg.F);
        } else if (!PRC_LIST_STRU_TP.LEASE_RETURN_FEES.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            bizMsg.svcSegCd_DH.clear();
            bizMsg.prcInOutRgCd_DH.clear();
            ZYPTableUtil.clear(bizMsg.G);
            ZYPTableUtil.clear(glblMsg.G);
        } else if (!PRC_LIST_STRU_TP.TRADE_INS.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            bizMsg.mdlNm_D5.clear();
            ZYPTableUtil.clear(bizMsg.H);
            ZYPTableUtil.clear(glblMsg.H);
        } else if (!PRC_LIST_STRU_TP.QTY_DISCOUNT.equals(bizMsg.prcListStruTpCd_H1.getValue())) {
            bizMsg.prcQlfyTpCd_D2.clear();
            bizMsg.prcQlfyValTxt_D2.clear();
            ZYPTableUtil.clear(bizMsg.I);
            ZYPTableUtil.clear(glblMsg.I);
            ZYPTableUtil.clear(bizMsg.J);
            ZYPTableUtil.clear(glblMsg.J);
        }
    }

    /**
     * getMdseNm.
     * @param glblCmpyCd String
     * @param mdseVal EZDSStringItem
     * @return String
     */
    public static String getMdseNm(String glblCmpyCd, EZDSStringItem mdseVal) {
        return getMdseNm(glblCmpyCd, mdseVal, false);
    }

    /**
     * getMdseNm.
     * @param glblCmpyCd String
     * @param mdseVal EZDSStringItem
     * @param isSvc boolean
     * @return String
     */
    public static String getMdseNm(String glblCmpyCd, EZDSStringItem mdseVal, boolean isSvcItem) {

        String rtrnVal;
        String mdseCd = "";
        boolean isOver = false;

        ORD_TAKE_MDSETMsg ordTakeInTMsg = new ORD_TAKE_MDSETMsg();

        if (mdseVal.getValue().length() > 8) {
            mdseCd = mdseVal.getValue().substring(0, 8);
            isOver = true;
        } else {
            mdseCd = mdseVal.getValue();
        }

        ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.ordTakeMdseCd, mdseCd);
        ORD_TAKE_MDSETMsg ordTakeOutTMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeInTMsg);
        if (ordTakeOutTMsg == null) {
            mdseCd = mdseVal.getValue();
        } else {
            if (isOver) {
                mdseVal.setErrorInfo(1, NMAM8216E);
                rtrnVal = null;
                return rtrnVal;
            }
            mdseCd = ordTakeOutTMsg.mdseCd.getValue();
        }

        MDSETMsg mdseInTMsg = new MDSETMsg();
        // Mod End 2016/09/12 QC#11615
        ZYPEZDItemValueSetter.setValue(mdseInTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseInTMsg.mdseCd, mdseCd);
        MDSETMsg mdseOutTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseInTMsg);
        if (mdseOutTMsg == null) {
            mdseVal.setErrorInfo(1, NMAM0163E, new String[] {mdseCd, "Merchandise"});
            rtrnVal = null;
            return rtrnVal;
        } else {
            rtrnVal = mdseOutTMsg.mdseDescShortTxt.getValue();
        }

        // Service Item check QC#688
        if (isSvcItem) {

            if (!checkSvcItem(mdseOutTMsg)) {
                mdseVal.setErrorInfo(1, NMAM0179E, new String[] {mdseCd, "No Service Item"});
                rtrnVal = null;
                return rtrnVal;
            }
        }

        return rtrnVal;
    }

    /**
     * setColData.
     * @param bizMsg NMAL7010CMsg
     * @param glblMsg NMAL7010SMsg
     */
    public static void setColData(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPGUITableColumn.getColData(bizMsg, glblMsg, "AHEAD");
        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPGUITableColumn.getColData(bizMsg, glblMsg, "BHEAD");
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPGUITableColumn.getColData(bizMsg, glblMsg, "CHEAD");
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPGUITableColumn.getColData(bizMsg, glblMsg, "DHEAD");
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPGUITableColumn.getColData(bizMsg, glblMsg, "EHEAD");
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPGUITableColumn.getColData(bizMsg, glblMsg, "FHEAD");
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPGUITableColumn.getColData(bizMsg, glblMsg, "GHEAD");
        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPGUITableColumn.getColData(bizMsg, glblMsg, "HHEAD");
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ZYPGUITableColumn.getColData(bizMsg, glblMsg, "IHEAD");
        }
    }

    /**
     * toStr.
     * @param val BigDecimal
     * @return String
     */
    public static BigDecimal toBigDecimal(String val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return new BigDecimal(val);
        } else {
            return null;
        }
    }

    /**
     * toStr.
     * @param val BigDecimal
     * @return String
     */
    public static String toStr(BigDecimal val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val.toString();
        } else {
            return "";
        }
    }

    /**
     * fmlaApiCall.
     * @param aSMsg NMAL7010_ASMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @param stdCcyCd String
     * @return boolean
     */
    public static boolean fmlaApiCall(NMAL7010_ASMsg aSMsg, String glblCmpyCd, String slsDt, String stdCcyCd) {

        NWZC170001PMsg fmlaApiPMsg = new NWZC170001PMsg();
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.xxModeCd, NWZC170001.MODE_NO_BASE);
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.prcBaseDt, slsDt);
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.prcFmlaPk, aSMsg.prcFmlaPk_PA);
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.ccyCd, stdCcyCd);

        if (PRC_QLFY_TP.ITEM_CODE.equals(aSMsg.prcQlfyTpCd_PA.getValue())) {
            if (ZYPCommonFunc.hasValue(aSMsg.prcQlfyValTxt_PA)) {
                ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.mdseCd, aSMsg.prcQlfyValTxt_PA);
                if (aSMsg.prcQlfyValTxt_PA.getValue().length() <= 8) {
                    ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.ordTakeMdseCd, aSMsg.prcQlfyValTxt_PA);
                }
            }
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_1.equals(aSMsg.prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.firstProdCtrlCd, aSMsg.prcQlfyValTxt_PA);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_2.equals(aSMsg.prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.scdProdCtrlCd, aSMsg.prcQlfyValTxt_PA);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_3.equals(aSMsg.prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.thirdProdCtrlCd, aSMsg.prcQlfyValTxt_PA);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_4.equals(aSMsg.prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.frthProdCtrlCd, aSMsg.prcQlfyValTxt_PA);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_5.equals(aSMsg.prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.fifthProdCtrlCd, aSMsg.prcQlfyValTxt_PA);
        } else if (PRC_QLFY_TP.MERCHANDISE_TYPE.equals(aSMsg.prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.coaMdseTpCd, aSMsg.prcQlfyValTxt_PA);
        }
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.ordCustUomQty, BigDecimal.ONE);

        if (PRC_QLFY_TP.ITEM_CODE.equals(aSMsg.prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.pkgUomCd, aSMsg.pkgUomCd_PA);
        } else {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.pkgUomCd, PKG_UOM.EACH);
        }

        NWZC170001 api = new NWZC170001();
        api.execute(fmlaApiPMsg, ONBATCH_TYPE.ONLINE);
        if (fmlaApiPMsg.xxMsgIdList.getValidCount() > 0) {
            aSMsg.prcFmlaPk_PA.setErrorInfo(1, fmlaApiPMsg.xxMsgIdList.no(0).xxMsgId.getValue()
                    , new String[] {fmlaApiPMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue()
                , fmlaApiPMsg.xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue()
                , fmlaApiPMsg.xxMsgIdList.no(0).xxMsgPrmTxt_2.getValue()});
            return false;
        }

        ZYPEZDItemValueSetter.setValue(aSMsg.xxCalcTotPrcAmt_PA, fmlaApiPMsg.xxCalcPrcAmt);

        return true;
    }

    /**
     * fmlaApiCall.
     * @param aCMsg NMAL7010_ASMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @param stdCcyCd String
     * @return boolean
     */
    public static boolean fmlaApiCall(NMAL7010_ACMsg aCMsg, String glblCmpyCd, String slsDt, String stdCcyCd) {

        NWZC170001PMsg fmlaApiPMsg = new NWZC170001PMsg();
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.xxModeCd, NWZC170001.MODE_NO_BASE);
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.prcBaseDt, slsDt);
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.prcFmlaPk, aCMsg.prcFmlaPk_PA);
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.ccyCd, stdCcyCd);

        if (PRC_QLFY_TP.ITEM_CODE.equals(aCMsg.prcQlfyTpCd_PA.getValue())) {
            if (ZYPCommonFunc.hasValue(aCMsg.prcQlfyValTxt_PA)) {
                ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.mdseCd, aCMsg.prcQlfyValTxt_PA);
                if (aCMsg.prcQlfyValTxt_PA.getValue().length() <= 8) {
                    ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.ordTakeMdseCd, aCMsg.prcQlfyValTxt_PA);
                }
            }
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_1.equals(aCMsg.prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.firstProdCtrlCd, aCMsg.prcQlfyValTxt_PA);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_2.equals(aCMsg.prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.scdProdCtrlCd, aCMsg.prcQlfyValTxt_PA);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_3.equals(aCMsg.prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.thirdProdCtrlCd, aCMsg.prcQlfyValTxt_PA);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_4.equals(aCMsg.prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.frthProdCtrlCd, aCMsg.prcQlfyValTxt_PA);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_5.equals(aCMsg.prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.fifthProdCtrlCd, aCMsg.prcQlfyValTxt_PA);
        } else if (PRC_QLFY_TP.MERCHANDISE_TYPE.equals(aCMsg.prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.coaMdseTpCd, aCMsg.prcQlfyValTxt_PA);
        }
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.ordCustUomQty, BigDecimal.ONE);

        if (PRC_QLFY_TP.ITEM_CODE.equals(aCMsg.prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.pkgUomCd, aCMsg.pkgUomCd_PA);
        } else {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.pkgUomCd, PKG_UOM.EACH);
        }

        NWZC170001 api = new NWZC170001();
        api.execute(fmlaApiPMsg, ONBATCH_TYPE.ONLINE);
        if (fmlaApiPMsg.xxMsgIdList.getValidCount() > 0) {
            aCMsg.prcFmlaPk_PA.setErrorInfo(1, fmlaApiPMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            return false;
        }

        ZYPEZDItemValueSetter.setValue(aCMsg.xxCalcTotPrcAmt_PA, fmlaApiPMsg.xxCalcPrcAmt);

        return true;
    }

    /**
     * toHighValDate.
     * @param dateVal String
     * @return String
     */
    public static String toHighValDate(String dateVal) {
        if (ZYPCommonFunc.hasValue(dateVal)) {
            return dateVal;
        }
        return HIGH_VAL_DT;
    }

    // QC#2174 Add Start
    /**
     * sortQtyDisc
     * @param bizMsg NMAL7010CMsg
     */
    public static void sortQtyDisc(NMAL7010CMsg bizMsg) {
        List<NMAL7010_QCMsg> list = new ArrayList<NMAL7010_QCMsg>();

        for (int i = 0; i < bizMsg.Q.getValidCount(); i++) {
            list.add((NMAL7010_QCMsg) bizMsg.Q.no(i).clone());
        }

        Collections.sort(list, new QtyDiscSort());

        for (int i = 0; i < bizMsg.Q.getValidCount(); i++) {
            EZDMsg.copy(list.get(i), null, bizMsg.Q.no(i), null);
        }
    }

    /**
     * Sort Class for Qty Discount
     */
    private static class QtyDiscSort implements Comparator<NMAL7010_QCMsg> {

        @Override
        public int compare(NMAL7010_QCMsg o1, NMAL7010_QCMsg o2) {

            int flg = compare(o1.qtyDiscDtlQty_QP.getValue(), o2.qtyDiscDtlQty_QP.getValue());
            if (flg == 0) {
                flg = compare(o1.delFlg_QP.getValue(), o2.delFlg_QP.getValue());
                if (flg == 0) {
                    flg = compare(o1.ezUpTime_QP.getValue(), o2.ezUpTime_QP.getValue());
                }
            }

            return flg;
        }
        
        private int compare(String val1, String val2) {
            if (val1 == null && val2 == null) {
                return 0;
            } else if (val1 == null && val2 != null) {
                return -1;
            } else if (val1 != null && val2 == null) {
                return 1;
            } else {
                return val1.compareTo(val2);
            }
        }
        private int compare(BigDecimal val1, BigDecimal val2) {
            if (val1 == null && val2 == null) {
                return 0;
            } else if (val1 == null && val2 != null) {
                return -1;
            } else if (val1 != null && val2 == null) {
                return 1;
            } else {
                return val1.compareTo(val2);
            }
        }
    }
    // QC#2174 End

    public static void setTableMessage(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {
        if (cMsgArray.getValidCount() < 1) {
            bizMsg.xxAbendMsgTxt_H1.clear();
            glblMsg.xxTotCnt_SM.clear();
            return;
        }
        BigDecimal totSize = glblMsg.xxTotCnt_SM.getValue();
        BigDecimal curSize = new BigDecimal(cMsgArray.getValidCount());
        if (!ZYPCommonFunc.hasValue(totSize) || totSize.intValue() < sMsgArray.getValidCount()) {
            totSize = new BigDecimal(sMsgArray.getValidCount());
        }
        String msg = curSize.intValue() + " out of " + totSize.intValue() + " rows are currently displayed.";
        if (sMsgArray.getValidCount() < totSize.intValue()) {
            // 2019/02/05 QC#30114 Mod Start
            //bizMsg.xxAbendMsgTxt_H1.setValue("The first " + msg + " Please use Filter to reduce the number of rows.");
            bizMsg.xxAbendMsgTxt_H1.setValue(msg + " Please use Filter to reduce the number of rows.");
            // 2019/02/05 QC#30114 Mod End
        } else {
            bizMsg.xxAbendMsgTxt_H1.setValue(msg);
        }
    }

    public static void getNumConst(String glblCmpyCd, NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        
        BigDecimal maxNum = ZYPCodeDataUtil.getNumConstValue(NMAL7010Constant.NMAL7010_MAX_FETCH_SIZE, glblCmpyCd);
        BigDecimal pageNum = ZYPCodeDataUtil.getNumConstValue(NMAL7010Constant.NMAL7010_PER_PAGE_SIZE, glblCmpyCd);
        String flg = ZYPCodeDataUtil.getVarCharConstValue(NMAL7010Constant.NMAL7010_PAGE_AVAL_FLG, glblCmpyCd);
        String defTermUom = ZYPCodeDataUtil.getVarCharConstValue(NMAL7010Constant.NMAL7010_DEF_TERM_UOM, glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(glblMsg.xxMaxSrchCnt_SM, maxNum);
        ZYPEZDItemValueSetter.setValue(glblMsg.xxMaxSrchCnt_CM, pageNum);
        ZYPEZDItemValueSetter.setValue(glblMsg.xxPgFlg, flg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxSfxKeyTxt_H1, defTermUom);
    }

    public static int getMaxFetchSize(NMAL7010SMsg glblMsg, EZDCMsgArray cMsgArray, EZDSMsgArray glblMsgArray) {
        if (!isPageAval(glblMsg)) {
            return getPerPageSize(glblMsg, cMsgArray);
        }
        if (!ZYPCommonFunc.hasValue(glblMsg.xxMaxSrchCnt_SM)) {
            return glblMsgArray.length();
        }
        return Math.min(glblMsg.xxMaxSrchCnt_SM.getValueInt(), glblMsgArray.length());
    }

    public static int getPerPageSize(NMAL7010SMsg glblMsg, EZDCMsgArray cMsgArray) {
        if (!ZYPCommonFunc.hasValue(glblMsg.xxMaxSrchCnt_CM)) {
            return cMsgArray.length();
        }
        return Math.min(glblMsg.xxMaxSrchCnt_CM.getValueInt(), cMsgArray.length());
    }

    public static boolean isPageAval(NMAL7010SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(glblMsg.xxPgFlg)) {
            return true;
        }
        return ZYPConstant.FLG_ON_Y.equals(glblMsg.xxPgFlg.getValue());
    }

    public static void clearFilter(NMAL7010CMsg bizMsg) {
        bizMsg.prcQlfyTpCd_D1.clear();
        bizMsg.prcQlfyValTxt_D1.clear();
        // 2018/11/27 QC#29319 Add Start
        bizMsg.xxPrcQlfyValSrchTxt_D1.clear();
        // 2018/11/27 QC#29319 Add End
        bizMsg.mdlNm_D1.clear();
        bizMsg.mdlNm_D2.clear();
        bizMsg.mdlNm_D3.clear();
        bizMsg.mdlNm_D4.clear();
        bizMsg.mdlNm_D5.clear();
        bizMsg.mdlNm_DH.clear();
        bizMsg.prcMtrPkgNm_D1.clear();
        bizMsg.prcMtrPkgNm_D2.clear();
        bizMsg.prcMtrPkgNm_D3.clear();
        bizMsg.prcPgmTpCd_DH.clear();
        bizMsg.prcEquipTpCd_DH.clear();
        bizMsg.svcSegCd_DH.clear();
        bizMsg.prcInOutRgCd_DH.clear();
        bizMsg.prcListEquipConfigNum_D1.clear();
        bizMsg.prcListEquipConfigNm_D1.clear();
        bizMsg.pkgUomCd_D1.clear();
        bizMsg.prcRateTpCd_D1.clear();
        bizMsg.prcSvcPlnTpCd_D1.clear();
        bizMsg.prcSvcPlnTpCd_D2.clear();
        bizMsg.prcSvcPlnTpCd_D3.clear();
        bizMsg.prcSvcContrTpCd_D1.clear();
        bizMsg.prcSvcContrTpCd_D2.clear();
        bizMsg.prcSvcContrTpCd_D3.clear();
        bizMsg.mtrLbNm_D1.clear();
        bizMsg.mtrLbNm_D2.clear();
        bizMsg.mtrLbNm_D3.clear();
        // 2018/11/17 QC#29147 Mod Start
        bizMsg.prcListBandDescTxt_D1.clear();
        bizMsg.prcListBandDescTxt_D2.clear();
        bizMsg.prcListBandDescTxt_D3.clear();
        // 2018/11/17 QC#29147 Mod End
        bizMsg.prcSvcTierTpCd_D2.clear();
        bizMsg.splyAgmtPlnNm_D3.clear();
        bizMsg.mdseCd_DH.clear();
        bizMsg.prcAddlChrgTpCd_DH.clear();
        bizMsg.mktMdlSegCd_DH.clear();
        bizMsg.prcLeaseCmpyAbbrNm_D4.clear();
        bizMsg.prcLeaseCmpyAbbrNm_D5.clear();
        bizMsg.dsAcctNm_D4.clear();
        bizMsg.mdseDescShortTxt_Z1.clear();
        // Mod Start 2018/11/17 QC#20267
        bizMsg.mnfItemCd_Z1.clear();
        // 2018/11/27 QC#29319 Add Start
        bizMsg.xxMnfItemCdSrchTxt_Z1.clear();
        // 2018/11/27 QC#29319 Add End
        // Mod End 2018/11/17 QC#20267
        // Mod Start 2016/10/17 QC#15193
//        bizMsg.coaMdseTpNm_Z1.clear();
        bizMsg.coaProjNm_Z1.clear();
        // Mod End 2016/10/17 QC#15193
        bizMsg.mdseItemTpNm_Z1.clear();
        bizMsg.coaProdNm_Z1.clear();
        bizMsg.t_MdlNm_Z1.clear();
        // 2018/11/27 QC#29319 Add Start
        bizMsg.xxTMdlNmSrchTxt_Z1.clear();
        // 2018/11/27 QC#29319 Add End
        bizMsg.zerothProdCtrlCd_Z1.clear();
        bizMsg.firstProdCtrlCd_Z1.clear();
        bizMsg.scdProdCtrlCd_Z1.clear();
        bizMsg.thirdProdCtrlCd_Z1.clear();
        bizMsg.frthProdCtrlCd_Z1.clear();
        bizMsg.prcTermAot_Z1.clear();
        bizMsg.prcTermUomCd_Z1.clear();
        bizMsg.custBidQty_Z1.clear();
        bizMsg.prcFmlaNm_Z1.clear();
        bizMsg.openMktFlg_Z1.clear();
        bizMsg.compCd_Z1.clear();
        bizMsg.xxYesNoCd_Z1.clear();
        bizMsg.termFromMthAot_Z1.clear();
        bizMsg.termThruMthAot_Z1.clear();
        bizMsg.prcSvcZoneCd_Z1.clear();
        bizMsg.mdseCd_Z1.clear();
        // Mod Start 2016/09/12 QC#11615
//        bizMsg.mdseNm_Z1.clear();
        bizMsg.mdseDescShortTxt_Z2.clear();
        // Mod End 2016/09/12 QC#11615
        bizMsg.prcListMdseCd_Z1.clear();
        bizMsg.effFromDt_Z1.clear();
        bizMsg.effFromDt_Z2.clear();
        bizMsg.effThruDt_Z1.clear();
        bizMsg.effThruDt_Z2.clear();
        bizMsg.xxFullNm_Z1.clear();
        bizMsg.xxFullNm_Z2.clear();
        bizMsg.cratDt_Z1.clear();
        bizMsg.cratDt_Z2.clear();
        bizMsg.lastUpdDt_Z1.clear();
        bizMsg.lastUpdDt_Z2.clear();
    }

    public static void setPageFrom(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, EZDCMsgArray cMsgArray, int idx) {
        int size = getPerPageSize(glblMsg, cMsgArray);
        int startpage = idx / size;
        int startfrom = startpage * size + 1;
        bizMsg.xxPageShowFromNum.setValue(startfrom);
    }

    public static void loadHeaderToCMsg(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_H1, glblMsg.ezUpTime_H1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_H2, glblMsg.ezUpTime_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_G1, glblMsg.ezUpTime_G1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_CA, glblMsg.ezUpTime_CA.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_CB, glblMsg.ezUpTime_CB.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_CC, glblMsg.ezUpTime_CC.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_H1, glblMsg.ezUpTimeZone_H1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_H2, glblMsg.ezUpTimeZone_H2.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_G1, glblMsg.ezUpTimeZone_G1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_CA, glblMsg.ezUpTimeZone_CA.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_CB, glblMsg.ezUpTimeZone_CB.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_CC, glblMsg.ezUpTimeZone_CC.getValue());
        EZDMsg.copy(glblMsg.W, null, bizMsg.W, null);
        EZDMsg.copy(glblMsg.X, null, bizMsg.X, null);
        EZDMsg.copy(glblMsg.Y, null, bizMsg.Y, null);
    }

    public static void loadHeaderToSMsg(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTime_H1, bizMsg.ezUpTime_H1.getValue());
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTime_H2, bizMsg.ezUpTime_H2.getValue());
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTime_G1, bizMsg.ezUpTime_G1.getValue());
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTime_CA, bizMsg.ezUpTime_CA.getValue());
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTime_CB, bizMsg.ezUpTime_CB.getValue());
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTime_CC, bizMsg.ezUpTime_CC.getValue());
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTimeZone_H1, bizMsg.ezUpTimeZone_H1.getValue());
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTimeZone_H2, bizMsg.ezUpTimeZone_H2.getValue());
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTimeZone_G1, bizMsg.ezUpTimeZone_G1.getValue());
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTimeZone_CA, bizMsg.ezUpTimeZone_CA.getValue());
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTimeZone_CB, bizMsg.ezUpTimeZone_CB.getValue());
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTimeZone_CC, bizMsg.ezUpTimeZone_CC.getValue());
        EZDMsg.copy(bizMsg.W, null, glblMsg.W, null);
        EZDMsg.copy(bizMsg.X, null, glblMsg.X, null);
        EZDMsg.copy(bizMsg.Y, null, glblMsg.Y, null);
    }

    public static void clearScrollPosition(NMAL7010CMsg bizMsg) {
        bizMsg.xxCellIdx_X1.clear();
        bizMsg.xxCellIdx_Y1.clear();
    }

    // QC#4763
    public static void createPullDownForPrcListTp(NMAL7010CMsg bizMsg, String prcListStruTpCd) {

        // QC#10987 2016/07/12 Add start
        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_H1, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_H1, ZYPConstant.FLG_ON_Y);
        }
        // QC#10987 2016/07/12 Add end
        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcListTpList(bizMsg, prcListStruTpCd);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM8020E);
            return;
        }
        bizMsg.prcListTpCd_L1.clear();
        bizMsg.prcListTpDescTxt_L1.clear();

        List<?> rslTrxtList = (List<?>) ssmResult.getResultObject();
        for (int i = 0; i < rslTrxtList.size(); i++) {
            Map<?, ?> map = (Map<?, ?>) rslTrxtList.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.prcListTpCd_L1.no(i), (String) map.get("PRC_LIST_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.prcListTpDescTxt_L1.no(i), (String) map.get("PRC_LIST_TP_NM"));
        }
    }

    // for error handling
    public static void setErrorPage(NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg, int idx) {

        ZYPEZDItemValueSetter.setValue(bizMsg.xxTabProt_D1, ZYPConstant.FLG_ON_Y);

        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            setPageFrom(bizMsg, glblMsg, bizMsg.A, idx);

        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            setPageFrom(bizMsg, glblMsg, bizMsg.B, idx);

        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            setPageFrom(bizMsg, glblMsg, bizMsg.C, idx);

        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            setPageFrom(bizMsg, glblMsg, bizMsg.D, idx);

        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            setPageFrom(bizMsg, glblMsg, bizMsg.E, idx);

        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            setPageFrom(bizMsg, glblMsg, bizMsg.F, idx);

        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {
            setPageFrom(bizMsg, glblMsg, bizMsg.G, idx);

        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {
            setPageFrom(bizMsg, glblMsg, bizMsg.H, idx);
        }
    }

    public static void openHeaderTab(NMAL7010CMsg bizMsg) {
        bizMsg.xxTabProt_D1.clear();
        bizMsg.xxDplyTab_D1.clear();
    }

    public static void openCustAudcTab(NMAL7010CMsg bizMsg) {
        openHeaderTab(bizMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1, NMAL7010Constant.TAB_PRC_LIST_CUST_AUDC);
    }

    public static void openTrxAudcTab(NMAL7010CMsg bizMsg) {
        openHeaderTab(bizMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1, NMAL7010Constant.TAB_PRC_LIST_TRX_AUDC);
    }

    public static void writeCsvFile(NMAL7010CMsg bizMsg, ResultSet rs, String glblCmpyCd, NMAL7010CMsg hdrMsg) throws SQLException {
        if (!rs.next()) {
            bizMsg.setMessageInfo(NMAL7010Constant.NZZM0000E);
            return;
        }

        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {

            NMAL7010F11FMsg fMsg = new NMAL7010F11FMsg();

            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NMAL7010Constant.CSV_FILE_NAME), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);

            // Mod #4767 2016/03/22 Start
            // csvOutFile.writeHeader(NMAL7010Constant.CSV_DOWNLOAD_COLUMN_EQUIP);
            int col1 = NMAL7010Constant.CSV_DOWNLOAD_COLUMN_EQUIP.length;
            int col2 = NMAL7010Constant.CSV_DOWNLOAD_COLUMN_EQUIP_2.length;
            int colAll = col1 + col2 + 5;

            String[] hdr = new String[colAll];

            System.arraycopy(NMAL7010Constant.CSV_DOWNLOAD_COLUMN_EQUIP, 0, hdr, 0, col1);
            hdr[col1++] = bizMsg.mdseStruElmntTpDescTxt_T1.getValue();
            hdr[col1++] = bizMsg.mdseStruElmntTpDescTxt_T2.getValue();
            hdr[col1++] = bizMsg.mdseStruElmntTpDescTxt_T3.getValue();
            hdr[col1++] = bizMsg.mdseStruElmntTpDescTxt_T4.getValue();
            hdr[col1++] = bizMsg.mdseStruElmntTpDescTxt_T5.getValue();
            System.arraycopy(NMAL7010Constant.CSV_DOWNLOAD_COLUMN_EQUIP_2, 0, hdr, col1++, col2);
            // Mod #4767 2016/03/22 End
            csvOutFile.writeHeader(hdr);

            //Header -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgCd, hdrMsg.prcCatgCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, hdrMsg.prcCatgNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListDplyNm, hdrMsg.prcListDplyNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt, getCodeName(hdrMsg.prcListTpCd_H1.getValue(), bizMsg.prcListTpCd_L1, bizMsg.prcListTpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.actvFlg, hdrMsg.actvFlg_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H1, convertDateFormat(hdrMsg.effFromDt_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H2, convertDateFormat(hdrMsg.effThruDt_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.custRgtnReqFlg, hdrMsg.custRgtnReqFlg_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcSlsVisTpDescTxt, getCodeName(hdrMsg.prcSlsVisTpCd_H1.getValue(), bizMsg.prcSlsVisTpCd_L1, bizMsg.prcSlsVisTpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.prcListGrpDescTxt, getCodeName(hdrMsg.prcListGrpCd_H1.getValue(), bizMsg.prcListGrpCd_L1, bizMsg.prcListGrpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNum, hdrMsg.prcContrNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNm, hdrMsg.prcContrNm_H1.getValue());

            //write contents
            do {
                //resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.delFlg, rs.getString("DEL_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcListEquipConfigNum, rs.getBigDecimal("PRC_LIST_EQUIP_CONFIG_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcListEquipConfigNm, rs.getString("PRC_LIST_EQUIP_CONFIG_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcQlfyTpNm, rs.getString("PRC_QLFY_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcQlfyValTxt, rs.getString("PRC_QLFY_VAL_TXT"));
                // Mod Start 2018/07/17 QC#20267
                ZYPEZDItemValueSetter.setValue(fMsg.mnfItemCd, rs.getString("MNF_ITEM_CD"));
                // Mod End 2018/07/17 QC#20267
                ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
                // Mod Start 2016/10/17 QC#15193
//                ZYPEZDItemValueSetter.setValue(fMsg.coaMdseTpNm, rs.getString("COA_MDSE_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.coaProjNm, rs.getString("COA_MDSE_TP_NM"));
                // Mod End 2016/10/17 QC#15193
                ZYPEZDItemValueSetter.setValue(fMsg.mdseItemTpNm, rs.getString("MDSE_ITEM_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.coaProdNm, rs.getString("COA_PROD_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.t_MdlNm, rs.getString("T_MDL_NM"));
                // Add #4767 2016/03/22 Start
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_P0, rs.getString("ZEROTH_PROD_CTRL"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_P1, rs.getString("FIRST_PROD_CTRL"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_P2, rs.getString("SCD_PROD_CTRL"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_P3, rs.getString("THIRD_PROD_CTRL"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_P4, rs.getString("FRTH_PROD_CTRL"));
                // Add #4767 2016/03/22 End
                // Mod Start 2018/08/29 QC#27787
//                ZYPEZDItemValueSetter.setValue(fMsg.pkgUomNm, rs.getString("PKG_UOM_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.pkgUomDescTxt, rs.getString("PKG_UOM_DESC_TXT"));
                // Mod End   2018/08/29 QC#27787
                ZYPEZDItemValueSetter.setValue(fMsg.prcListEquipPrcAmt, rs.getBigDecimal("PRC_LIST_EQUIP_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.minPrcAmt, rs.getBigDecimal("MIN_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.maxPrcAmt, rs.getBigDecimal("MAX_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcTermAot, rs.getBigDecimal("PRC_TERM_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcTermUomNm, rs.getString("PRC_TERM_UOM_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.custBidQty, rs.getBigDecimal("CUST_BID_QTY"));
                ZYPEZDItemValueSetter.setValue(fMsg.mlyPmtAmt, rs.getBigDecimal("MLY_PMT_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.minLeasePmtAmt, rs.getBigDecimal("MIN_LEASE_PMT_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.maxLeasePmtAmt, rs.getBigDecimal("MAX_LEASE_PMT_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcFmlaPk, rs.getBigDecimal("PRC_FMLA_PK"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcFmlaNm, rs.getString("PRC_FMLA_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.openMktFlg, rs.getString("OPEN_MKT_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.quotRevAmt, rs.getBigDecimal("QUOT_REV_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.compCd, rs.getString("COMP_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D1, convertDateFormat(rs.getString("EFF_FROM_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D2, convertDateFormat(rs.getString("EFF_THRU_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrStsTxt, rs.getString("XX_SCR_STS_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_D1, rs.getString("CRAT_USR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D3, convertDateFormat(rs.getString("CRAT_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_D2, rs.getString("UPD_USR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D4, convertDateFormat(rs.getString("UPD_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxYesNoCd, rs.getString("XX_YES_NO_CD_PA"));

                if (ZYPCommonFunc.hasValue(fMsg.prcFmlaPk)) {
                    NMAL7010_ASMsg asMsg = new NMAL7010_ASMsg();
                    ZYPEZDItemValueSetter.setValue(asMsg.prcFmlaPk_PA, fMsg.prcFmlaPk.getValue());
                    ZYPEZDItemValueSetter.setValue(asMsg.prcQlfyTpCd_PA, rs.getString("PRC_QLFY_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(asMsg.prcQlfyValTxt_PA, fMsg.prcQlfyValTxt.getValue());
                    ZYPEZDItemValueSetter.setValue(asMsg.pkgUomCd_PA, rs.getString("PKG_UOM_CD"));

                    NMAL7010CommonLogic.fmlaApiCall(asMsg, glblCmpyCd, ZYPDateUtil.getSalesDate(), bizMsg.stdCcyCd.getValue());

                    ZYPEZDItemValueSetter.setValue(fMsg.xxCalcTotPrcAmt, asMsg.xxCalcTotPrcAmt_PA.getValue());
                } else {
                    fMsg.xxCalcTotPrcAmt.clear();
                }

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {

            NMAL7010F12FMsg fMsg = new NMAL7010F12FMsg();

            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NMAL7010Constant.CSV_FILE_NAME), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);

            csvOutFile.writeHeader(NMAL7010Constant.CSV_DOWNLOAD_COLUMN_SVC);

            //Header -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgCd, hdrMsg.prcCatgCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, hdrMsg.prcCatgNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListDplyNm, hdrMsg.prcListDplyNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt, getCodeName(hdrMsg.prcListTpCd_H1.getValue(), bizMsg.prcListTpCd_L1, bizMsg.prcListTpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.actvFlg, hdrMsg.actvFlg_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H1, convertDateFormat(hdrMsg.effFromDt_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H2, convertDateFormat(hdrMsg.effThruDt_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.custRgtnReqFlg, hdrMsg.custRgtnReqFlg_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcSlsVisTpDescTxt, getCodeName(hdrMsg.prcSlsVisTpCd_H1.getValue(), bizMsg.prcSlsVisTpCd_L1, bizMsg.prcSlsVisTpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.prcListGrpDescTxt, getCodeName(hdrMsg.prcListGrpCd_H1.getValue(), bizMsg.prcListGrpCd_L1, bizMsg.prcListGrpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNum, hdrMsg.prcContrNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNm, hdrMsg.prcContrNm_H1.getValue());

            //write contents
            do {
                //resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.delFlg, rs.getString("DEL_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcRateTpNm, rs.getString("PRC_RATE_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdlNm, rs.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcMtrPkgNm, rs.getString("PRC_MTR_PKG_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcListMdseCd, rs.getString("PRC_LIST_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcPlnTpNm, rs.getString("PRC_SVC_PLN_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcContrTpNm, rs.getString("PRC_SVC_CONTR_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mtrLbNm, rs.getString("MTR_LB_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.minCopyVolCnt, rs.getBigDecimal("MIN_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.maxCopyVolCnt, rs.getBigDecimal("MAX_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcListBandNm, rs.getString("PRC_LIST_BAND_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.baseAmt, rs.getBigDecimal("BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.minBaseAmt, rs.getBigDecimal("MIN_BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.maxBaseAmt, rs.getBigDecimal("MAX_BASE_AMT"));
                // 2018/08/22 QC#26664 Mod Start
                //ZYPEZDItemValueSetter.setValue(fMsg.cpcAmtRate, rs.getBigDecimal("CPC_AMT_RATE"));
                //ZYPEZDItemValueSetter.setValue(fMsg.minCpcAmtRate, rs.getBigDecimal("MIN_CPC_AMT_RATE"));
                //ZYPEZDItemValueSetter.setValue(fMsg.maxCpcAmtRate, rs.getBigDecimal("MAX_CPC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(fMsg.cpcAmtRate, formatRate(rs.getBigDecimal("CPC_AMT_RATE")));
                ZYPEZDItemValueSetter.setValue(fMsg.minCpcAmtRate, formatRate(rs.getBigDecimal("MIN_CPC_AMT_RATE")));
                ZYPEZDItemValueSetter.setValue(fMsg.maxCpcAmtRate, formatRate(rs.getBigDecimal("MAX_CPC_AMT_RATE")));
                // 2018/08/22 QC#26664 Mod End
                ZYPEZDItemValueSetter.setValue(fMsg.termFromMthAot, rs.getBigDecimal("TERM_FROM_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.termThruMthAot, rs.getBigDecimal("TERM_THRU_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcZoneNm, rs.getString("PRC_SVC_ZONE_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
                // Mod Start 2016/09/12 QC#11615
//                ZYPEZDItemValueSetter.setValue(fMsg.mdseNm, rs.getString("MDSE_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_2, rs.getString("MDSE_DESC_SHORT_TXT_PZ"));
                // Mod End 2016/09/12 QC#11615
                ZYPEZDItemValueSetter.setValue(fMsg.mtrLbDescTxt, rs.getString("MTR_LB_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.quotRevAmt, rs.getBigDecimal("QUOT_REV_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.compCd, rs.getString("COMP_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D1, convertDateFormat(rs.getString("EFF_FROM_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D2, convertDateFormat(rs.getString("EFF_THRU_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrStsTxt, rs.getString("XX_SCR_STS_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_D1, rs.getString("CRAT_USR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D3, convertDateFormat(rs.getString("CRAT_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_D2, rs.getString("UPD_USR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D4, convertDateFormat(rs.getString("UPD_DT")));

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {

            NMAL7010F13FMsg fMsg = new NMAL7010F13FMsg();

            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NMAL7010Constant.CSV_FILE_NAME), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);

            csvOutFile.writeHeader(NMAL7010Constant.CSV_DOWNLOAD_COLUMN_SVC_TIER);

            //Header -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgCd, hdrMsg.prcCatgCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, hdrMsg.prcCatgNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListDplyNm, hdrMsg.prcListDplyNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt, getCodeName(hdrMsg.prcListTpCd_H1.getValue(), bizMsg.prcListTpCd_L1, bizMsg.prcListTpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.actvFlg, hdrMsg.actvFlg_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H1, convertDateFormat(hdrMsg.effFromDt_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H2, convertDateFormat(hdrMsg.effThruDt_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.custRgtnReqFlg, hdrMsg.custRgtnReqFlg_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcSlsVisTpDescTxt, getCodeName(hdrMsg.prcSlsVisTpCd_H1.getValue(), bizMsg.prcSlsVisTpCd_L1, bizMsg.prcSlsVisTpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.prcListGrpDescTxt, getCodeName(hdrMsg.prcListGrpCd_H1.getValue(), bizMsg.prcListGrpCd_L1, bizMsg.prcListGrpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNum, hdrMsg.prcContrNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNm, hdrMsg.prcContrNm_H1.getValue());

            //write contents
            do {
                //resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.delFlg, rs.getString("DEL_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdlNm, rs.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcTierTpNm, rs.getString("PRC_SVC_TIER_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcMtrPkgNm, rs.getString("PRC_MTR_PKG_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcPlnTpNm, rs.getString("PRC_SVC_PLN_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcContrTpNm, rs.getString("PRC_SVC_CONTR_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mtrLbNm, rs.getString("MTR_LB_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.avgCopyVolCnt, rs.getBigDecimal("AVG_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.minCopyVolCnt, rs.getBigDecimal("MIN_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.maxCopyVolCnt, rs.getBigDecimal("MAX_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcListBandNm, rs.getString("PRC_LIST_BAND_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.baseAmt, rs.getBigDecimal("BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.minBaseAmt, rs.getBigDecimal("MIN_BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.maxBaseAmt, rs.getBigDecimal("MAX_BASE_AMT"));
                // 2018/08/22 QC#26664 Mod Start
                //ZYPEZDItemValueSetter.setValue(fMsg.cpcAmtRate, rs.getBigDecimal("CPC_AMT_RATE"));
                //ZYPEZDItemValueSetter.setValue(fMsg.minCpcAmtRate, rs.getBigDecimal("MIN_CPC_AMT_RATE"));
                //ZYPEZDItemValueSetter.setValue(fMsg.maxCpcAmtRate, rs.getBigDecimal("MAX_CPC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(fMsg.cpcAmtRate, formatRate(rs.getBigDecimal("CPC_AMT_RATE")));
                ZYPEZDItemValueSetter.setValue(fMsg.minCpcAmtRate, formatRate(rs.getBigDecimal("MIN_CPC_AMT_RATE")));
                ZYPEZDItemValueSetter.setValue(fMsg.maxCpcAmtRate, formatRate(rs.getBigDecimal("MAX_CPC_AMT_RATE")));
                // 2018/08/22 QC#26664 Mod End
                ZYPEZDItemValueSetter.setValue(fMsg.termFromMthAot, rs.getBigDecimal("TERM_FROM_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.termThruMthAot, rs.getBigDecimal("TERM_THRU_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
                // Mod Start 2016/09/12 QC#11615
//                ZYPEZDItemValueSetter.setValue(fMsg.mdseNm, rs.getString("MDSE_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
                // Mod End 2016/09/12 QC#11615
                ZYPEZDItemValueSetter.setValue(fMsg.mtrLbDescTxt, rs.getString("MTR_LB_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D1, convertDateFormat(rs.getString("EFF_FROM_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D2, convertDateFormat(rs.getString("EFF_THRU_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrStsTxt, rs.getString("XX_SCR_STS_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_D1, rs.getString("CRAT_USR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D3, convertDateFormat(rs.getString("CRAT_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_D2, rs.getString("UPD_USR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D4, convertDateFormat(rs.getString("UPD_DT")));

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {

            NMAL7010F14FMsg fMsg = new NMAL7010F14FMsg();

            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NMAL7010Constant.CSV_FILE_NAME), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);

            csvOutFile.writeHeader(NMAL7010Constant.CSV_DOWNLOAD_COLUMN_SVC_SPEC_CHRG);

            //Header -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgCd, hdrMsg.prcCatgCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, hdrMsg.prcCatgNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListDplyNm, hdrMsg.prcListDplyNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt, getCodeName(hdrMsg.prcListTpCd_H1.getValue(), bizMsg.prcListTpCd_L1, bizMsg.prcListTpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.actvFlg, hdrMsg.actvFlg_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H1, convertDateFormat(hdrMsg.effFromDt_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H2, convertDateFormat(hdrMsg.effThruDt_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.custRgtnReqFlg, hdrMsg.custRgtnReqFlg_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcSlsVisTpDescTxt, getCodeName(hdrMsg.prcSlsVisTpCd_H1.getValue(), bizMsg.prcSlsVisTpCd_L1, bizMsg.prcSlsVisTpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.prcListGrpDescTxt, getCodeName(hdrMsg.prcListGrpCd_H1.getValue(), bizMsg.prcListGrpCd_L1, bizMsg.prcListGrpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNum, hdrMsg.prcContrNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNm, hdrMsg.prcContrNm_H1.getValue());

            //write contents
            do {
                //resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.delFlg, rs.getString("DEL_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
                // Mod Start 2016/09/12 QC#11615
//                ZYPEZDItemValueSetter.setValue(fMsg.mdseNm, rs.getString("MDSE_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
                // Mod End 2016/09/12 QC#11615
                ZYPEZDItemValueSetter.setValue(fMsg.prcAddlChrgTpNm, rs.getString("PRC_ADDL_CHRG_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mktMdlSegNm, rs.getString("MKT_MDL_SEG_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdlNm, rs.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.addlChrgPrcAmt, rs.getBigDecimal("ADDL_CHRG_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D1, convertDateFormat(rs.getString("EFF_FROM_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D2, convertDateFormat(rs.getString("EFF_THRU_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrStsTxt, rs.getString("XX_SCR_STS_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_D1, rs.getString("CRAT_USR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D3, convertDateFormat(rs.getString("CRAT_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_D2, rs.getString("UPD_USR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D4, convertDateFormat(rs.getString("UPD_DT")));

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            NMAL7010F15FMsg fMsg = new NMAL7010F15FMsg();

            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NMAL7010Constant.CSV_FILE_NAME), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);

            csvOutFile.writeHeader(NMAL7010Constant.CSV_DOWNLOAD_COLUMN_SPLY_PGM);

            //Header -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgCd, hdrMsg.prcCatgCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, hdrMsg.prcCatgNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListDplyNm, hdrMsg.prcListDplyNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt, getCodeName(hdrMsg.prcListTpCd_H1.getValue(), bizMsg.prcListTpCd_L1, bizMsg.prcListTpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.actvFlg, hdrMsg.actvFlg_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H1, convertDateFormat(hdrMsg.effFromDt_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H2, convertDateFormat(hdrMsg.effThruDt_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.custRgtnReqFlg, hdrMsg.custRgtnReqFlg_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcSlsVisTpDescTxt, getCodeName(hdrMsg.prcSlsVisTpCd_H1.getValue(), bizMsg.prcSlsVisTpCd_L1, bizMsg.prcSlsVisTpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.prcListGrpDescTxt, getCodeName(hdrMsg.prcListGrpCd_H1.getValue(), bizMsg.prcListGrpCd_L1, bizMsg.prcListGrpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNum, hdrMsg.prcContrNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNm, hdrMsg.prcContrNm_H1.getValue());

            //write contents
            do {
                //resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.delFlg, rs.getString("DEL_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdlNm, rs.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcMtrPkgNm, rs.getString("PRC_MTR_PKG_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcPlnTpNm, rs.getString("PRC_SVC_PLN_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcContrTpNm, rs.getString("PRC_SVC_CONTR_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mtrLbNm, rs.getString("MTR_LB_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.minCopyVolCnt, rs.getBigDecimal("MIN_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.maxCopyVolCnt, rs.getBigDecimal("MAX_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcListBandNm, rs.getString("PRC_LIST_BAND_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.totBaseAmt, rs.getBigDecimal("TOT_BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.splyBaseAmt, rs.getBigDecimal("SPLY_BASE_AMT"));
                // 2018/08/22 QC#26664 Mod Start
                //ZYPEZDItemValueSetter.setValue(fMsg.cpcAmtRate, rs.getBigDecimal("CPC_AMT_RATE"));
                //ZYPEZDItemValueSetter.setValue(fMsg.minCpcAmtRate, rs.getBigDecimal("MIN_CPC_AMT_RATE"));
                //ZYPEZDItemValueSetter.setValue(fMsg.maxCpcAmtRate, rs.getBigDecimal("MAX_CPC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(fMsg.cpcAmtRate, formatRate(rs.getBigDecimal("CPC_AMT_RATE")));
                ZYPEZDItemValueSetter.setValue(fMsg.minCpcAmtRate, formatRate(rs.getBigDecimal("MIN_CPC_AMT_RATE")));
                ZYPEZDItemValueSetter.setValue(fMsg.maxCpcAmtRate, formatRate(rs.getBigDecimal("MAX_CPC_AMT_RATE")));
                // 2018/08/22 QC#26664 Mod End
                ZYPEZDItemValueSetter.setValue(fMsg.termFromMthAot, rs.getBigDecimal("TERM_FROM_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.termThruMthAot, rs.getBigDecimal("TERM_THRU_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
                // Mod Start 2016/09/12 QC#11615
//                ZYPEZDItemValueSetter.setValue(fMsg.mdseNm, rs.getString("MDSE_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
                // Mod End 2016/09/12 QC#11615
                ZYPEZDItemValueSetter.setValue(fMsg.mtrLbDescTxt, rs.getString("MTR_LB_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcZoneNm, rs.getString("PRC_SVC_ZONE_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.splyAgmtPlnNm, rs.getString("SPLY_AGMT_PLN_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D1, convertDateFormat(rs.getString("EFF_FROM_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D2, convertDateFormat(rs.getString("EFF_THRU_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrStsTxt, rs.getString("XX_SCR_STS_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_D1, rs.getString("CRAT_USR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D3, convertDateFormat(rs.getString("CRAT_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_D2, rs.getString("UPD_USR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D4, convertDateFormat(rs.getString("UPD_DT")));

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {

            NMAL7010F16FMsg fMsg = new NMAL7010F16FMsg();

            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NMAL7010Constant.CSV_FILE_NAME), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);

            csvOutFile.writeHeader(NMAL7010Constant.CSV_DOWNLOAD_COLUMN_LEASE_RATE);

            //Header -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgCd, hdrMsg.prcCatgCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, hdrMsg.prcCatgNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListDplyNm, hdrMsg.prcListDplyNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt, getCodeName(hdrMsg.prcListTpCd_H1.getValue(), bizMsg.prcListTpCd_L1, bizMsg.prcListTpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.actvFlg, hdrMsg.actvFlg_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H1, convertDateFormat(hdrMsg.effFromDt_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H2, convertDateFormat(hdrMsg.effThruDt_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.custRgtnReqFlg, hdrMsg.custRgtnReqFlg_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcSlsVisTpDescTxt, getCodeName(hdrMsg.prcSlsVisTpCd_H1.getValue(), bizMsg.prcSlsVisTpCd_L1, bizMsg.prcSlsVisTpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.prcListGrpDescTxt, getCodeName(hdrMsg.prcListGrpCd_H1.getValue(), bizMsg.prcListGrpCd_L1, bizMsg.prcListGrpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNum, hdrMsg.prcContrNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNm, hdrMsg.prcContrNm_H1.getValue());

            //write contents
            do {
                //resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.delFlg, rs.getString("DEL_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcLeaseCmpyAbbrNm, rs.getString("PRC_LEASE_CMPY_ABBR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcPgmTpNm, rs.getString("PRC_PGM_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcEquipTpNm, rs.getString("PRC_EQUIP_TP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdlNm, rs.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.minTotFinAmt, rs.getBigDecimal("MIN_TOT_FIN_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.maxTotFinAmt, rs.getBigDecimal("MAX_TOT_FIN_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.termFromMthAot, rs.getBigDecimal("TERM_FROM_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.termThruMthAot, rs.getBigDecimal("TERM_THRU_MTH_AOT"));
                // 2018/08/22 QC#26664 Mod Start
                //ZYPEZDItemValueSetter.setValue(fMsg.leaseRate, rs.getBigDecimal("LEASE_RATE"));
                ZYPEZDItemValueSetter.setValue(fMsg.leaseRate, formatRate(rs.getBigDecimal("LEASE_RATE")));
                // 2018/08/22 QC#26664 Mod End
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D1, convertDateFormat(rs.getString("EFF_FROM_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D2, convertDateFormat(rs.getString("EFF_THRU_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrStsTxt, rs.getString("XX_SCR_STS_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_D1, rs.getString("CRAT_USR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D3, convertDateFormat(rs.getString("CRAT_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_D2, rs.getString("UPD_USR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D4, convertDateFormat(rs.getString("UPD_DT")));

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {

            NMAL7010F17FMsg fMsg = new NMAL7010F17FMsg();

            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NMAL7010Constant.CSV_FILE_NAME), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);

            csvOutFile.writeHeader(NMAL7010Constant.CSV_DOWNLOAD_COLUMN_LEASE_RTRN_FEES);

            //Header -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgCd, hdrMsg.prcCatgCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, hdrMsg.prcCatgNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListDplyNm, hdrMsg.prcListDplyNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt, getCodeName(hdrMsg.prcListTpCd_H1.getValue(), bizMsg.prcListTpCd_L1, bizMsg.prcListTpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.actvFlg, hdrMsg.actvFlg_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H1, convertDateFormat(hdrMsg.effFromDt_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H2, convertDateFormat(hdrMsg.effThruDt_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.custRgtnReqFlg, hdrMsg.custRgtnReqFlg_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcSlsVisTpDescTxt, getCodeName(hdrMsg.prcSlsVisTpCd_H1.getValue(), bizMsg.prcSlsVisTpCd_L1, bizMsg.prcSlsVisTpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.prcListGrpDescTxt, getCodeName(hdrMsg.prcListGrpCd_H1.getValue(), bizMsg.prcListGrpCd_L1, bizMsg.prcListGrpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNum, hdrMsg.prcContrNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNm, hdrMsg.prcContrNm_H1.getValue());

            //write contents
            do {
                //resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.delFlg, rs.getString("DEL_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcLeaseCmpyAbbrNm, rs.getString("PRC_LEASE_CMPY_ABBR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.svcSegNm, rs.getString("SVC_SEG_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcInOutRgNm, rs.getString("PRC_IN_OUT_RG_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.dstMileAmt, rs.getBigDecimal("DST_MILE_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.rtrnFeeAmt, rs.getBigDecimal("RTRN_FEE_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D1, convertDateFormat(rs.getString("EFF_FROM_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D2, convertDateFormat(rs.getString("EFF_THRU_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrStsTxt, rs.getString("XX_SCR_STS_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_D1, rs.getString("CRAT_USR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D3, convertDateFormat(rs.getString("CRAT_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_D2, rs.getString("UPD_USR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D4, convertDateFormat(rs.getString("UPD_DT")));

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {

            NMAL7010F18FMsg fMsg = new NMAL7010F18FMsg();

            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NMAL7010Constant.CSV_FILE_NAME), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);

            csvOutFile.writeHeader(NMAL7010Constant.CSV_DOWNLOAD_COLUMN_TRADE_IN);

            //Header -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgCd, hdrMsg.prcCatgCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, hdrMsg.prcCatgNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListDplyNm, hdrMsg.prcListDplyNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt, getCodeName(hdrMsg.prcListTpCd_H1.getValue(), bizMsg.prcListTpCd_L1, bizMsg.prcListTpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.actvFlg, hdrMsg.actvFlg_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H1, convertDateFormat(hdrMsg.effFromDt_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H2, convertDateFormat(hdrMsg.effThruDt_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.custRgtnReqFlg, hdrMsg.custRgtnReqFlg_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcSlsVisTpDescTxt, getCodeName(hdrMsg.prcSlsVisTpCd_H1.getValue(), bizMsg.prcSlsVisTpCd_L1, bizMsg.prcSlsVisTpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.prcListGrpDescTxt, getCodeName(hdrMsg.prcListGrpCd_H1.getValue(), bizMsg.prcListGrpCd_L1, bizMsg.prcListGrpDescTxt_L1));
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNum, hdrMsg.prcContrNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNm, hdrMsg.prcContrNm_H1.getValue());

            //write contents
            do {
                //resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.delFlg, rs.getString("DEL_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdlNm, rs.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.tiAmt, rs.getBigDecimal("TI_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.mtrRngReqFlg, rs.getString("MTR_RNG_REQ_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.fromMtrCopyVolCnt, rs.getBigDecimal("FROM_MTR_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.thruMtrCopyVolCnt, rs.getBigDecimal("THRU_MTR_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D1, convertDateFormat(rs.getString("EFF_FROM_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D2, convertDateFormat(rs.getString("EFF_THRU_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrStsTxt, rs.getString("XX_SCR_STS_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_D1, rs.getString("CRAT_USR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D3, convertDateFormat(rs.getString("CRAT_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_D2, rs.getString("UPD_USR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D4, convertDateFormat(rs.getString("UPD_DT")));

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();
        }
    }

    // Add #4767 2016/03/22 Start
    public static void getProductLevelName(String glblCmpyCd, NMAL7010CMsg bizMsg, NMAL7010SMsg glblMsg) {
        S21SsmEZDResult result = NMAL7010Query.getInstance().getProductLevelName(bizMsg, glblCmpyCd);
        if (result.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> list = (List<Map<String, String>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            for (Map<String, String> map : list) {
                if (map != null && map.get("MDSE_STRU_ELMNT_TP_CD") != null) {
                    //PLG
                    if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        bizMsg.mdseStruElmntTpDescTxt_T1.setValue((String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                        //PL
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        bizMsg.mdseStruElmntTpDescTxt_T2.setValue((String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                        //PL2
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        bizMsg.mdseStruElmntTpDescTxt_T3.setValue((String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                        //PL3
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        bizMsg.mdseStruElmntTpDescTxt_T4.setValue((String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                        //PL4
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        bizMsg.mdseStruElmntTpDescTxt_T5.setValue((String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                    }
                }
            }
        }
    }
    // Add #4767 2016/03/22 End
    private static String convertDateFormat(String dt) {
        return dt == null ? null : dt.replaceAll(NMAL7010Constant.DT_CONV_FORMAT[0], NMAL7010Constant.DT_CONV_FORMAT[1]);
    }

    /**
     * get PrcCatgCd
     * @param prcCatgNm String
     * @param prcListStruTpCd String
     * @return prcCatgCd String
     */
    public static String getPrcCatgCd(String prcCatgNm, String prcListStruTpCd) {
        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPrcCatgCd(prcCatgNm, prcListStruTpCd);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        return (String) ssmResult.getResultObject();
    }

    private static String getCodeName(String code, EZDCStringItemArray codeArr, EZDCStringItemArray nameArr) {
        for (int i = 0; i < codeArr.length(); i++) {
            if (!ZYPCommonFunc.hasValue(codeArr.no(i))) {
                return null;
            }
            if (codeArr.no(i).getValue().equals(code)) {
                return nameArr.no(i).getValue();
            }
        }
        return null;
    }

    // QC#688 Add start
    private static boolean checkSvcItem(MDSETMsg mdseTMsg) {
        if (mdseTMsg == null) {
            return false;
        }

        List<Map<String, String>> svcItemTpList = getSvcItemTpListCache(mdseTMsg.glblCmpyCd.getValue());

        if (svcItemTpList == null) {
            return false;
        }

        for (Map<String, String> svcItemTpMap : svcItemTpList) {
            if (S21StringUtil.isEquals(mdseTMsg.mdseItemTpCd.getValue(), svcItemTpMap.get("MDSE_ITEM_TP_CD")) //
                    && S21StringUtil.isEquals(mdseTMsg.dsIntgMdseTpCd.getValue(), svcItemTpMap.get("FIRST_BIZ_CTX_ATTRB_TXT"))) {
                return true;
            }
        }
        return false;
    }

    private static List<Map<String, String>> getSvcItemTpListCache(String glblCmpyCd) {
        @SuppressWarnings("unchecked")
        List<Map<String, String>> svcItemTpCache = (List<Map<String, String>>) S21ApplicationCacheHolder.get(NMAL7010Constant.KEY_SVC_ITEM_TP_CACHE);

        if (svcItemTpCache != null) {
            return svcItemTpCache;
        }

        svcItemTpCache = getSvcItemTpList(NMAL7010Constant.ITEM_TP_CTX_TP_CD);

        S21ApplicationCacheHolder.put(NMAL7010Constant.KEY_SVC_ITEM_TP_CACHE, svcItemTpCache);

        return svcItemTpCache;
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, String>> getSvcItemTpList(String itemTpCtxTpCd) {
        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getSvcItemTpList(itemTpCtxTpCd);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        return (List<Map<String, String>>) ssmResult.getResultObject();
    }
    // QC#688 end
    
    public static void invokeMasterDataMessaging(List<String> mdseCdListToDealConfig) {

        try {

            for (String mdseCd : mdseCdListToDealConfig) {
	            logger.debug("**************Deal Config Send Mdse Code is " + mdseCd + ".************");
            }
        	
            Event event = new Event();
            event.setReferencedEntity(REF_ENTITY_ITEM);

            event.setOperationType(OPERATIONTYPES.UPDATE);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSS");
            //Date formatDate = sdf.parse(ezUpTime);
            Date formatDate = sdf.parse(sdf.format(new Date()));
            
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(formatDate);
            XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            event.setCreateStamp(date2);

            event.setId(1);

            ArrayOfKeyReference ak = new ArrayOfKeyReference();
            
            KeyReference kr = new KeyReference();
            kr.setType(REFERENCETYPES.TECHNICAL_MNEMONICAL);

            ArrayOfAttribute aa = new ArrayOfAttribute();

            //Set MDSE Code
            // 2018/02/27 QC#24422 Mod Start
//            Attribute a = new Attribute();
//            a.setName(MODE_CD_KEY_NAME);
//            for (String mdseCd : mdseCdListToDealConfig) {
//	            a.setValue(mdseCd);
//	            
//	            aa.getAttribute().add(a);
//	            kr.setAttributes(aa);
//
//	            ak.getKeyReference().add(kr);
//            }
            for (String mdseCd : mdseCdListToDealConfig) {
                Attribute a = new Attribute();
                a.setName(MODE_CD_KEY_NAME);
                a.setValue(mdseCd);

                aa.getAttribute().add(a);
            }
            kr.setAttributes(aa);
            ak.getKeyReference().add(kr);
            // 2018/02/27 QC#24422 Mod End
            event.setKeyReferences(ak);

            ArrayOfEvent ae = new ArrayOfEvent();
            ae.getEvent().add(event);

            printRequest(ae);

            logger.debug("Proxy invocation starting");

            // Invoke the proxy
            new MasterDataMessagingServiceProxy().synchronizationMessage(ae);

            logger.debug("Proxy invocation complete");

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static void printRequest(ArrayOfEvent ae) throws JAXBException {
        StringWriter stringWriter = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(ArrayOfEvent.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // format the XML output
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        QName qName = new QName("com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfEvent", "ArrayOfEvent");
        JAXBElement<ArrayOfEvent> root = new JAXBElement<ArrayOfEvent>(qName, ArrayOfEvent.class, ae);

        jaxbMarshaller.marshal(root, stringWriter);

        String result = stringWriter.toString();

        logger.debug(result);
    }

    // Add Start 2016/08/26 QC#3934
    /**
     * Check Input Value
     * @param bizMsg NWAL1500CMsg
     * @return check OK : true
     */
    @SuppressWarnings("unchecked")
    public static void checkValue(NMAL7010CMsg bizMsg, String glblCmpyCd) {

        int idx = bizMsg.xxCellIdx.getValueInt();
        EZDCStringItem ezdStrItemMdse = bizMsg.A.no(idx).prcQlfyValTxt_PA;
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_H1, ZYPConstant.FLG_OFF_N);
        // Mod Start 2018/07/17 QC#20267
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_H2, ZYPConstant.FLG_OFF_N);

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getEquipmentMdseList(ezdStrItemMdse.getValue());
        boolean mnfItemFlg = true;
        String baseMdseCd = null;

        if (ssmResult.isCodeNotFound()) {
            ssmResult = NMAL7010Query.getInstance().getEquipmentMnfItemList(ezdStrItemMdse.getValue());

            if (ssmResult.isCodeNotFound()) {
                String errValue = ezdStrItemMdse.getValue();
                // Mod Start 2018/07/17 QC#20267
                bizMsg.A.no(idx).mnfItemCd_PA.clear();
                // Mod End 2018/07/17 QC#20267
                bizMsg.A.no(idx).mdseDescShortTxt_PA.clear();
                // Mod Start 2016/10/17 QC#15193
//                bizMsg.A.no(idx).coaMdseTpNm_PA.clear();
                bizMsg.A.no(idx).coaProjNm_PA.clear();
                // Mod End 2016/10/17 QC#15193
                bizMsg.A.no(idx).mdseItemTpNm_PA.clear();
                bizMsg.A.no(idx).coaProdNm_PA.clear();
                bizMsg.A.no(idx).t_MdlNm_PA.clear();
                bizMsg.A.no(idx).xxScrItem61Txt_P0.clear();
                bizMsg.A.no(idx).xxScrItem61Txt_P1.clear();
                bizMsg.A.no(idx).xxScrItem61Txt_P2.clear();
                bizMsg.A.no(idx).xxScrItem61Txt_P3.clear();
                bizMsg.A.no(idx).xxScrItem61Txt_P4.clear();
                ezdStrItemMdse.clear();
                ezdStrItemMdse.setErrorInfo(1, NMAM0163E, new String[] {errValue, "Value"});
                return;
            } 

            List<Map<String, Object>> mnfItemList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (1 < ssmResult.getQueryResultCount()) {

                Set<String> mdse8Set = new HashSet<String>();
                for (Map<String, Object> mnfItem : mnfItemList) {
                    String mdseCd = (String) mnfItem.get("MDSE_CD");
                    if (8 < mdseCd.length()) {
                        mdseCd = mdseCd.substring(0, 8);
                    }
                    mdse8Set.add(mdseCd);
                }
                if (1 < mdse8Set.size()) {
                    // Open Popup
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_H1, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_H2, ZYPConstant.FLG_ON_Y);
                    return;

                }else {
                    // Found one MnfItemCd
                    for (String mdse8 : mdse8Set) {
                        baseMdseCd = mdse8;
                        break;
                    }
                    ORD_TAKE_MDSETMsg tMsg = getOrdTakeMdse(glblCmpyCd, baseMdseCd);
                    if (tMsg != null) {
                        mnfItemFlg = false;
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_H1, ZYPConstant.FLG_ON_Y);
                        return;
                    }
                }
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_H2, ZYPConstant.FLG_ON_Y);
            }
        }

        List<Map<?, ?>> prcQlfyTpCdList = (List<Map<?, ?>>) ssmResult.getResultObject();
        if(mnfItemFlg){
            if (prcQlfyTpCdList.size() != 1) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_H1, ZYPConstant.FLG_ON_Y);
                return;
            }
        }

        // Add Start 2018/04/06 QC#22556
        createPullDownForUOM(bizMsg, idx);
        // Add End 2018/04/06 QC#22556

        if(mnfItemFlg){
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).prcQlfyValTxt_PA, (String)prcQlfyTpCdList.get(0).get("MDSE_CD"));
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).prcQlfyValTxt_PA, baseMdseCd);
        }
        // Mod End 2018/07/17 QC#20267

        // Mod Start 2018/07/17 QC#20267
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).mnfItemCd_PA, (String)prcQlfyTpCdList.get(0).get("MNF_ITEM_CD"));
        // Mod End 2018/07/17 QC#20267
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).mdseDescShortTxt_PA, (String)prcQlfyTpCdList.get(0).get("MDSE_DESC_SHORT_TXT"));
        // Mod Start 2016/10/17 QC#15193
//        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).coaMdseTpCd_PA, (String)prcQlfyTpCdList.get(0).get("COA_MDSE_TP_CD"));
//        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).coaMdseTpNm_PA, (String)prcQlfyTpCdList.get(0).get("COA_MDSE_TP_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).coaProjCd_PA, (String)prcQlfyTpCdList.get(0).get("COA_MDSE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).coaProjNm_PA, (String)prcQlfyTpCdList.get(0).get("COA_MDSE_TP_NM"));
      // Mod End 2016/10/17 QC#15193
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).mdseItemTpNm_PA, (String)prcQlfyTpCdList.get(0).get("MDSE_ITEM_TP_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).coaProdNm_PA, (String)prcQlfyTpCdList.get(0).get("COA_PROD_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).t_MdlNm_PA, (String)prcQlfyTpCdList.get(0).get("T_MDL_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).xxScrItem61Txt_P0, (String)prcQlfyTpCdList.get(0).get("ZEROTH_PROD_CTRL"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).xxScrItem61Txt_P1, (String)prcQlfyTpCdList.get(0).get("FIRST_PROD_CTRL"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).xxScrItem61Txt_P2, (String)prcQlfyTpCdList.get(0).get("SCD_PROD_CTRL"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).xxScrItem61Txt_P3, (String)prcQlfyTpCdList.get(0).get("THIRD_PROD_CTRL"));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).xxScrItem61Txt_P4, (String)prcQlfyTpCdList.get(0).get("FRTH_PROD_CTRL"));

        return;
    }

    /**
     * Check Input Price MDSE
     * @param bizMsg NWAL1500CMsg
     * @return check OK : true
     */
    @SuppressWarnings("unchecked")
    public static void checkPriceMdse(NMAL7010CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        EZDCStringItem ezdStrItemMdse = bizMsg.B.no(idx).prcListMdseCd_PB;
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_H1, ZYPConstant.FLG_OFF_N);

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPriceMdseInfo(ezdStrItemMdse.getValue());
        if (ssmResult.isCodeNotFound()) {
            String errValue = ezdStrItemMdse.getValue();
            bizMsg.B.no(idx).mdseDescShortTxt_PB.clear();
            ezdStrItemMdse.clear();
            ezdStrItemMdse.setErrorInfo(1, NMAM0163E, new String[] {errValue, "Value" });
            return;
        }

        List<Map<?, ?>> prcMdseList = (List<Map<?, ?>>) ssmResult.getResultObject();
        if (prcMdseList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_H1, ZYPConstant.FLG_ON_Y);
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).prcListMdseCd_PB, (String) prcMdseList.get(0).get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).mdseDescShortTxt_PB, (String) prcMdseList.get(0).get("MDSE_DESC_SHORT_TXT"));

        return;
    }

    /**
     * Check Input MDSE
     * @param bizMsg NWAL1500CMsg
     * @return check OK : true
     */
    @SuppressWarnings("unchecked")
    public static void checkMdse(NMAL7010CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        
        EZDCStringItem ezdStrItemMdse = null;
        EZDCStringItem ezdStrItemTxt = null;

        if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ezdStrItemMdse = bizMsg.B.no(idx).mdseCd_PB;
            // Mod Start 20165/09/12 QC#11615
//            ezdStrItemTxt = bizMsg.B.no(idx).mdseNm_PB;
            ezdStrItemTxt = bizMsg.B.no(idx).mdseDescShortTxt_PZ;
            // Mod End 20165/09/12 QC#11615
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ezdStrItemMdse = bizMsg.C.no(idx).mdseCd_PC;
            // Mod Start 20165/09/12 QC#11615
//            ezdStrItemTxt = bizMsg.C.no(idx).mdseNm_PC;
            ezdStrItemTxt = bizMsg.C.no(idx).mdseDescShortTxt_PC;
            // Mod End 20165/09/12 QC#11615
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ezdStrItemMdse = bizMsg.D.no(idx).mdseCd_PD;
            // Mod Start 20165/09/12 QC#11615
//            ezdStrItemTxt = bizMsg.D.no(idx).mdseNm_PD;
            ezdStrItemTxt = bizMsg.D.no(idx).mdseDescShortTxt_PD;
            // Mod End 20165/09/12 QC#11615
        }  else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {
            ezdStrItemMdse = bizMsg.E.no(idx).mdseCd_PE;
            // Mod Start 20165/09/12 QC#11615
//            ezdStrItemTxt = bizMsg.E.no(idx).mdseNm_PE;
            ezdStrItemTxt = bizMsg.E.no(idx).mdseDescShortTxt_PE;
            // Mod End 20165/09/12 QC#11615
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_H1, ZYPConstant.FLG_OFF_N);

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getPriceMdseInfo(ezdStrItemMdse.getValue());
        if (ssmResult.isCodeNotFound()) {
            String errValue = ezdStrItemMdse.getValue();
            ezdStrItemTxt.clear();
            ezdStrItemMdse.clear();
            ezdStrItemMdse.setErrorInfo(1, NMAM0163E, new String[] {errValue, "Value" });
            return;
        }

        List<Map<?, ?>> prcMdseList = (List<Map<?, ?>>) ssmResult.getResultObject();
        if (prcMdseList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_H1, ZYPConstant.FLG_ON_Y);
            return;
        }

        ZYPEZDItemValueSetter.setValue(ezdStrItemMdse, (String) prcMdseList.get(0).get("MDSE_CD"));
        // Mod Start 2016/09/12 QC#11615
//        ZYPEZDItemValueSetter.setValue(ezdStrItemTxt, (String) prcMdseList.get(0).get("MDSE_NM"));
        ZYPEZDItemValueSetter.setValue(ezdStrItemTxt, (String) prcMdseList.get(0).get("MDSE_DESC_SHORT_TXT"));
        // Mod End 2016/09/12 QC#11615

        return;
    }
    // Add Start 2016/08/26 QC#3934

    // 2018/02/27 QC#24422 Add Start
    public static String getMdseCd(String glblCmpyCd, String mdseCd) {

        if (!ZYPCommonFunc.hasValue(mdseCd)) {

            return mdseCd;
        }

        ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, mdseCd.length() > 8 ? mdseCd.substring(0, 8) : mdseCd);

        tMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return mdseCd;
        }

        return tMsg.mdseCd.getValue();
    }
    // 2018/02/27 QC#24422 Add End

    // Add Start 2018/04/06 QC#22556
    public static void createPullDownForUOM(NMAL7010CMsg bizMsg, int idx) {

        bizMsg.A.no(idx).pkgUomCd_LA.clear();
        bizMsg.A.no(idx).pkgUomDescTxt_LA.clear();

        String mdseCd;
        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(idx).prcQlfyValTxt_PA) //
                    && ZYPCommonFunc.hasValue(bizMsg.A.no(idx).prcQlfyTpCd_PA) //
                    && PRC_QLFY_TP.ITEM_CODE.equals(bizMsg.A.no(idx).prcQlfyTpCd_PA.getValue())) {
                mdseCd = bizMsg.A.no(idx).prcQlfyValTxt_PA.getValue();
            } else {
                ZYPCodeDataUtil.createPulldownList(PKG_UOM.class, bizMsg.A.no(idx).pkgUomCd_LA, bizMsg.A.no(idx).pkgUomDescTxt_LA);
                return;
            }
        } else {
            return;
        }

        S21SsmEZDResult ssmResult = NMAL7010Query.getInstance().getUOMPulldownList(mdseCd);

        if (ssmResult.isCodeNotFound()) {
            ZYPCodeDataUtil.createPulldownList(PKG_UOM.class, bizMsg.A.no(idx).pkgUomCd_LA, bizMsg.A.no(idx).pkgUomDescTxt_LA);
            return;
        }

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.A.no(idx).pkgUomCd_LA.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).pkgUomCd_LA.no(i), (String) resultMap.get("PKG_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).pkgUomDescTxt_LA.no(i), (String) resultMap.get("PKG_UOM_DESC_TXT"));

            // 2018/05/15 S21_NA#26015 Del Start
            //if (ZYPCommonFunc.hasValue((String) resultMap.get("PKG_UOM_CD"))) {
            //    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).pkgUomCd_PA, (String) resultMap.get("PKG_UOM_CD"));
            //}
            // 2018/05/15 S21_NA#26015 Del End
        }
        // 2018/05/15 S21_NA#26015 Add Start
        if (!ZYPCommonFunc.hasValue(bizMsg.A.no(idx).pkgUomCd_PA)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).pkgUomCd_PA, (String) ((Map<String, Object>) resultList.get(0)).get("PKG_UOM_CD"));
        }
        // 2018/05/15 S21_NA#26015 Add End
    }
    // Add End 2018/04/06 QC#22556

    // 2018/05/08 QC#20269 Add Start
    /**
     * writeCsvFileAsTemplate.
     * @param bizMsg NMAL7010CMsg
     * @param rs ResultSet
     * @param glblCmpyCd String
     * @param hdrMsg NMAL7010CMsg
     * @throws SQLException
     */
    public static void writeCsvFileAsTemplate(NMAL7010CMsg bizMsg, ResultSet rs, String glblCmpyCd, NMAL7010CMsg hdrMsg) throws SQLException {
        if (!rs.next()) {
            bizMsg.setMessageInfo(NMAL7010Constant.NZZM0000E);
            return;
        }

        // Price List Structure : Equipment(01)
        if (TAB_PRC_LIST_VAL_EQUIP.equals(bizMsg.xxDplyTab_D1.getValue())) {

            NMA7010001FMsg fMsg = new NMA7010001FMsg();
            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NMAL7010Constant.ASTEMPLATE_CSV_FILE_NAME), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);

            // write HeaderLine
            String[] headerStrings = getAsTemplateHeaderStrings(glblCmpyCd, NMAL7010Constant.UPLD_CSV_ID_EQUIP_WRK);
            csvOutFile.writeHeader(headerStrings);

            // HeaderContents -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, hdrMsg.prcCatgNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt, hdrMsg.prcListTpDescTxt_L1.no(0).getValue());

            //write contents
            do {
                //resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.prcListEquipConfigNum, rs.getBigDecimal("PRC_LIST_EQUIP_CONFIG_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcListEquipConfigNm, rs.getString("PRC_LIST_EQUIP_CONFIG_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcQlfyTpDescTxt, rs.getString("PRC_QLFY_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcQlfyValTxt, rs.getString("PRC_QLFY_VAL_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.pkgUomDescTxt, rs.getString("PKG_UOM_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcListEquipPrcAmt, rs.getBigDecimal("PRC_LIST_EQUIP_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.minPrcAmt, rs.getBigDecimal("MIN_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.maxPrcAmt, rs.getBigDecimal("MAX_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcTermAot, rs.getBigDecimal("PRC_TERM_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcTermUomDescTxt, rs.getString("PRC_TERM_UOM_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.custBidQty, rs.getBigDecimal("CUST_BID_QTY"));
                ZYPEZDItemValueSetter.setValue(fMsg.mlyPmtAmt, rs.getBigDecimal("MLY_PMT_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.minLeasePmtAmt, rs.getBigDecimal("MIN_LEASE_PMT_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.maxLeasePmtAmt, rs.getBigDecimal("MAX_LEASE_PMT_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcFmlaNm, rs.getString("PRC_FMLA_NM"));
                // Mod Start 2018/06/06 QC#26292
//                ZYPEZDItemValueSetter.setValue(fMsg.openMktFlg, rs.getString("OPEN_MKT_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.openMktDescTxt, rs.getString("OPEN_MKT_FLG"));
                // Mod End 2018/06/06 QC#26292
                ZYPEZDItemValueSetter.setValue(fMsg.quotRevAmt, rs.getBigDecimal("QUOT_REV_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.compCd, rs.getString("COMP_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.effFromDt, rs.getString("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.effThruDt, rs.getString("EFF_THRU_DT"));

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

        // Price List Structure : Service(02)
        } else if (TAB_PRC_LIST_VAL_SVC.equals(bizMsg.xxDplyTab_D1.getValue())) {

            NMA7010002FMsg fMsg = new NMA7010002FMsg();
            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NMAL7010Constant.ASTEMPLATE_CSV_FILE_NAME), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);

            // write HeaderLine
            String[] headerStrings = getAsTemplateHeaderStrings(glblCmpyCd, NMAL7010Constant.UPLD_CSV_ID_SVC_WRK);
            csvOutFile.writeHeader(headerStrings);

            // HeaderContents -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, hdrMsg.prcCatgNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt, hdrMsg.prcListTpDescTxt_L1.no(0).getValue());

            //write contents
            do {
                //resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.prcRateTpDescTxt, rs.getString("PRC_RATE_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdlNm, rs.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcMtrPkgNm, rs.getString("PRC_MTR_PKG_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcPlnTpDescTxt, rs.getString("PRC_SVC_PLN_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcContrTpDescTxt, rs.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.mtrLbNm, rs.getString("MTR_LB_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.minCopyVolCnt, rs.getBigDecimal("MIN_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.maxCopyVolCnt, rs.getBigDecimal("MAX_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcListBandDescTxt, rs.getString("PRC_LIST_BAND_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.baseAmt, rs.getBigDecimal("BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.minBaseAmt, rs.getBigDecimal("MIN_BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.maxBaseAmt, rs.getBigDecimal("MAX_BASE_AMT"));
                // 2018/08/22 QC#26664 Mod Start
                //ZYPEZDItemValueSetter.setValue(fMsg.cpcAmtRate, rs.getBigDecimal("CPC_AMT_RATE"));
                //ZYPEZDItemValueSetter.setValue(fMsg.minCpcAmtRate, rs.getBigDecimal("MIN_CPC_AMT_RATE"));
                //ZYPEZDItemValueSetter.setValue(fMsg.maxCpcAmtRate, rs.getBigDecimal("MAX_CPC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(fMsg.cpcAmtRate, formatRate(rs.getBigDecimal("CPC_AMT_RATE")));
                ZYPEZDItemValueSetter.setValue(fMsg.minCpcAmtRate, formatRate(rs.getBigDecimal("MIN_CPC_AMT_RATE")));
                ZYPEZDItemValueSetter.setValue(fMsg.maxCpcAmtRate, formatRate(rs.getBigDecimal("MAX_CPC_AMT_RATE")));
                // 2018/08/22 QC#26664 Mod End
                ZYPEZDItemValueSetter.setValue(fMsg.termFromMthAot, rs.getBigDecimal("TERM_FROM_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.termThruMthAot, rs.getBigDecimal("TERM_THRU_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcZoneDescTxt, rs.getString("PRC_SVC_ZONE_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.effFromDt, rs.getString("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.effThruDt, rs.getString("EFF_THRU_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.quotRevAmt, rs.getBigDecimal("QUOT_REV_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.compCd, rs.getString("COMP_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcListMdseCd, rs.getString("PRC_LIST_MDSE_CD"));

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

        // Price List Structure : Service Tiers(03)
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(bizMsg.xxDplyTab_D1.getValue())) {

            NMA7010003FMsg fMsg = new NMA7010003FMsg();
            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NMAL7010Constant.ASTEMPLATE_CSV_FILE_NAME), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);

            // write HeaderLine
            String[] headerStrings = getAsTemplateHeaderStrings(glblCmpyCd, NMAL7010Constant.UPLD_CSV_ID_SVC_TIER_WRK);
            csvOutFile.writeHeader(headerStrings);

            // HeaderContents -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, hdrMsg.prcCatgNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt, hdrMsg.prcListTpDescTxt_L1.no(0).getValue());

            //write contents
            do {
                // resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.mdlNm, rs.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcTierTpDescTxt, rs.getString("PRC_SVC_TIER_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcMtrPkgNm, rs.getString("PRC_MTR_PKG_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcPlnTpDescTxt, rs.getString("PRC_SVC_PLN_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcContrTpDescTxt, rs.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.mtrLbNm, rs.getString("MTR_LB_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.avgCopyVolCnt, rs.getBigDecimal("AVG_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.minCopyVolCnt, rs.getBigDecimal("MIN_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.maxCopyVolCnt, rs.getBigDecimal("MAX_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcListBandDescTxt, rs.getString("PRC_LIST_BAND_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.baseAmt, rs.getBigDecimal("BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.minBaseAmt, rs.getBigDecimal("MIN_BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.maxBaseAmt, rs.getBigDecimal("MAX_BASE_AMT"));
                // 2018/08/22 QC#26664 Mod Start
                //ZYPEZDItemValueSetter.setValue(fMsg.cpcAmtRate, rs.getBigDecimal("CPC_AMT_RATE"));
                //ZYPEZDItemValueSetter.setValue(fMsg.minCpcAmtRate, rs.getBigDecimal("MIN_CPC_AMT_RATE"));
                //ZYPEZDItemValueSetter.setValue(fMsg.maxCpcAmtRate, rs.getBigDecimal("MAX_CPC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(fMsg.cpcAmtRate, formatRate(rs.getBigDecimal("CPC_AMT_RATE")));
                ZYPEZDItemValueSetter.setValue(fMsg.minCpcAmtRate, formatRate(rs.getBigDecimal("MIN_CPC_AMT_RATE")));
                ZYPEZDItemValueSetter.setValue(fMsg.maxCpcAmtRate, formatRate(rs.getBigDecimal("MAX_CPC_AMT_RATE")));
                // 2018/08/22 QC#26664 Mod End
                ZYPEZDItemValueSetter.setValue(fMsg.termFromMthAot, rs.getBigDecimal("TERM_FROM_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.termThruMthAot, rs.getBigDecimal("TERM_THRU_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.effFromDt, rs.getString("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.effThruDt, rs.getString("EFF_THRU_DT"));
                // 2018/08/13 QC#26092 Del Start
//                ZYPEZDItemValueSetter.setValue(fMsg.prcTierSvcOfferDescTxt, "");
//                ZYPEZDItemValueSetter.setValue(fMsg.allowSvcToDclnFlg, ZYPConstant.FLG_OFF_N);
                // 2018/08/13 QC#26092 Del End

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

        // Price List Structure : Service Special Changes(04)
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(bizMsg.xxDplyTab_D1.getValue())) {

            NMA7010004FMsg fMsg = new NMA7010004FMsg();
            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NMAL7010Constant.ASTEMPLATE_CSV_FILE_NAME), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);

            // write HeaderLine
            String[] headerStrings = getAsTemplateHeaderStrings(glblCmpyCd, NMAL7010Constant.UPLD_CSV_ID_ADDL_CHRG_WRK);
            csvOutFile.writeHeader(headerStrings);

            // HeaderContents -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, hdrMsg.prcCatgNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt, hdrMsg.prcListTpDescTxt_L1.no(0).getValue());

            //write contents
            do {
                // resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcAddlChrgTpDescTxt, rs.getString("PRC_ADDL_CHRG_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.mktMdlSegDescTxt, rs.getString("MKT_MDL_SEG_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdlNm, rs.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.addlChrgPrcAmt, rs.getBigDecimal("ADDL_CHRG_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.effFromDt, rs.getString("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.effThruDt, rs.getString("EFF_THRU_DT"));

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

        // Price List Structure : Supply Program(05)
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(bizMsg.xxDplyTab_D1.getValue())) {

            NMA7010005FMsg fMsg = new NMA7010005FMsg();
            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NMAL7010Constant.ASTEMPLATE_CSV_FILE_NAME), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);

            // write HeaderLine
            String[] headerStrings = getAsTemplateHeaderStrings(glblCmpyCd, NMAL7010Constant.UPLD_CSV_ID_SPLY_PGM_WRK);
            csvOutFile.writeHeader(headerStrings);

            // HeaderContents -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, hdrMsg.prcCatgNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt, hdrMsg.prcListTpDescTxt_L1.no(0).getValue());

            //write contents
            do {
                // resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.mdlNm, rs.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcMtrPkgNm, rs.getString("PRC_MTR_PKG_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcPlnTpDescTxt, rs.getString("PRC_SVC_PLN_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcContrTpDescTxt, rs.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.mtrLbNm, rs.getString("MTR_LB_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.minCopyVolCnt, rs.getBigDecimal("MIN_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.maxCopyVolCnt, rs.getBigDecimal("MAX_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcListBandDescTxt, rs.getString("PRC_LIST_BAND_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.totBaseAmt, rs.getBigDecimal("TOT_BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.splyBaseAmt, rs.getBigDecimal("SPLY_BASE_AMT"));
                // 2018/08/22 QC#26664 Mod Start
                //ZYPEZDItemValueSetter.setValue(fMsg.cpcAmtRate, rs.getBigDecimal("CPC_AMT_RATE"));
                //ZYPEZDItemValueSetter.setValue(fMsg.minCpcAmtRate, rs.getBigDecimal("MIN_CPC_AMT_RATE"));
                //ZYPEZDItemValueSetter.setValue(fMsg.maxCpcAmtRate, rs.getBigDecimal("MAX_CPC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(fMsg.cpcAmtRate, formatRate(rs.getBigDecimal("CPC_AMT_RATE")));
                ZYPEZDItemValueSetter.setValue(fMsg.minCpcAmtRate, formatRate(rs.getBigDecimal("MIN_CPC_AMT_RATE")));
                ZYPEZDItemValueSetter.setValue(fMsg.maxCpcAmtRate, formatRate(rs.getBigDecimal("MAX_CPC_AMT_RATE")));
                // 2018/08/22 QC#26664 Mod End
                ZYPEZDItemValueSetter.setValue(fMsg.termFromMthAot, rs.getBigDecimal("TERM_FROM_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.termThruMthAot, rs.getBigDecimal("TERM_THRU_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcSvcZoneDescTxt, rs.getString("PRC_SVC_ZONE_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.splyAgmtPlnNm, rs.getString("SPLY_AGMT_PLN_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.effFromDt, rs.getString("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.effThruDt, rs.getString("EFF_THRU_DT"));

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

        // Price List Structure : Lease Rates(06)
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(bizMsg.xxDplyTab_D1.getValue())) {

            NMA7010006FMsg fMsg = new NMA7010006FMsg();
            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NMAL7010Constant.ASTEMPLATE_CSV_FILE_NAME), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);

            // write HeaderLine
            String[] headerStrings = getAsTemplateHeaderStrings(glblCmpyCd, NMAL7010Constant.UPLD_CSV_ID_LEASE_RATE_WRK);
            csvOutFile.writeHeader(headerStrings);

            // HeaderContents -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, hdrMsg.prcCatgNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt, hdrMsg.prcListTpDescTxt_L1.no(0).getValue());

            //write contents
            do {
                // resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.prcLeaseCmpyAbbrNm, rs.getString("PRC_LEASE_CMPY_ABBR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcPgmTpDescTxt, rs.getString("PRC_PGM_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcEquipTpDescTxt, rs.getString("PRC_EQUIP_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdlNm, rs.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.minTotFinAmt, rs.getBigDecimal("MIN_TOT_FIN_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.maxTotFinAmt, rs.getBigDecimal("MAX_TOT_FIN_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.termFromMthAot, rs.getBigDecimal("TERM_FROM_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.termThruMthAot, rs.getBigDecimal("TERM_THRU_MTH_AOT"));
                // 2018/08/22 QC#26664 Mod Start
                //ZYPEZDItemValueSetter.setValue(fMsg.leaseRate, rs.getBigDecimal("LEASE_RATE"));
                ZYPEZDItemValueSetter.setValue(fMsg.leaseRate, formatRate(rs.getBigDecimal("LEASE_RATE")));
                // 2018/08/22 QC#26664 Mod End
                ZYPEZDItemValueSetter.setValue(fMsg.effFromDt, rs.getString("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.effThruDt, rs.getString("EFF_THRU_DT"));

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

        // Price List Structure : Lease Return Fees(07)
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(bizMsg.xxDplyTab_D1.getValue())) {

            NMA7010007FMsg fMsg = new NMA7010007FMsg();
            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NMAL7010Constant.ASTEMPLATE_CSV_FILE_NAME), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);

            // write HeaderLine
            String[] headerStrings = getAsTemplateHeaderStrings(glblCmpyCd, NMAL7010Constant.UPLD_CSV_ID_LEASE_RTRN_WRK);
            csvOutFile.writeHeader(headerStrings);

            // HeaderContents -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, hdrMsg.prcCatgNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt, hdrMsg.prcListTpDescTxt_L1.no(0).getValue());

            //write contents
            do {
                // resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.prcLeaseCmpyAbbrNm, rs.getString("PRC_LEASE_CMPY_ABBR_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.svcSegDescTxt, rs.getString("SVC_SEG_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcInOutRgDescTxt, rs.getString("PRC_IN_OUT_RG_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.dstMileAmt, rs.getBigDecimal("DST_MILE_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.rtrnFeeAmt, rs.getBigDecimal("RTRN_FEE_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.effFromDt, rs.getString("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.effThruDt, rs.getString("EFF_THRU_DT"));

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

        // Price List Structure : Trade Ins(08)
        } else if (TAB_PRC_LIST_VAL_TI.equals(bizMsg.xxDplyTab_D1.getValue())) {

            NMA7010008FMsg fMsg = new NMA7010008FMsg();
            bizMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NMAL7010Constant.ASTEMPLATE_CSV_FILE_NAME), ".csv");
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_DL.getTempFilePath(), fMsg);

            // write HeaderLine
            String[] headerStrings = getAsTemplateHeaderStrings(glblCmpyCd, NMAL7010Constant.UPLD_CSV_ID_TI_VAL_WRK);
            csvOutFile.writeHeader(headerStrings);

            // HeaderContents -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, hdrMsg.prcCatgNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcListTpDescTxt, hdrMsg.prcListTpDescTxt_L1.no(0).getValue());

            //write contents
            do {
                // resultSet -> fMsg
                ZYPEZDItemValueSetter.setValue(fMsg.mdlNm, rs.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.tiAmt, rs.getBigDecimal("TI_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.mtrRngReqFlg, rs.getString("MTR_RNG_REQ_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.fromMtrCopyVolCnt, rs.getBigDecimal("FROM_MTR_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.thruMtrCopyVolCnt, rs.getBigDecimal("THRU_MTR_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.effFromDt, rs.getString("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.effThruDt, rs.getString("EFF_THRU_DT"));

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();
        }
    }

    private static String[] getAsTemplateHeaderStrings(String glblCmpyCd, String csvId) {

        UPLD_CSV_HDRTMsg inTMsg = new UPLD_CSV_HDRTMsg();
        inTMsg.setSQLID("050");
        inTMsg.setConditionValue("ezCancelFlag01", "0");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("upldCsvId01", csvId);

        UPLD_CSV_HDRTMsgArray outTMsgArray = (UPLD_CSV_HDRTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);

        String[] headerStrings = new String[outTMsgArray.length()];

        for (int i = 0; i < outTMsgArray.length(); i++) {
            UPLD_CSV_HDRTMsg outTMsg = (UPLD_CSV_HDRTMsg) outTMsgArray.get(i);
            StringBuffer header = new StringBuffer();
            header.append("\"<");
            header.append(outTMsg.upldCsvHdrNm.getValue());
            header.append(">(<");
            header.append(outTMsg.upldCsvHdrDataTpNm.getValue());
            header.append(">(");
            header.append(outTMsg.upldCsvHdrDataLg.getValue());
            header.append("))\"");

            headerStrings[i] = header.toString();
        }

        return headerStrings;
    }
    // 2018/05/08 QC#20269 Add End

    // Mod Start 2018/07/17 QC#20267
    /**
     * Get Ord Take Mdse
     * @param glblCmpyCd String
     * @param mdseCd Merchandise Code
     * @return ALL_MDSE_V Array
     */
    private static ORD_TAKE_MDSETMsg getOrdTakeMdse(String glblCmpyCd, String mdseCd) {

        ORD_TAKE_MDSETMsg ordTakeMdse = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(ordTakeMdse.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTakeMdse.ordTakeMdseCd, mdseCd);

        return (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdse);
    }
    // Mod End 2018/07/17 QC#20267

    // 2018/08/22 QC#26664 Add Start
    /**
     * formatRate
     * @param val BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal formatRate(BigDecimal val) {

        if (val == null) {
            return val;
        }
        return val.setScale(RATE_SCALE);
    }
    // 2018/08/22 QC#26664 Add End

    // 2018/12/18 QC#29661 Add Start
    /**
     * Update Effective Through Date.
     * @param dplyTabD1 String
     * @param glblMsg NMAL7010SMsg
     */
    public static void updateEffDateTo(String dplyTabD1, NMAL7010SMsg glblMsg, NMAL7010CMsg bizMsg) {
        if (TAB_PRC_LIST_VAL_EQUIP.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).xxChkBox_PA.getValue())) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).effThruDt_PA, bizMsg.effThruDt_DH);
                    glblMsg.A.no(i).xxModeCd_A1.setValue(NMAL7010Constant.MODE_MODIFY);
                }
            }
        } else if (TAB_PRC_LIST_VAL_SVC.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.B.no(i).xxChkBox_PB.getValue())) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).effThruDt_PB, bizMsg.effThruDt_DH);
                    glblMsg.B.no(i).xxModeCd_B1.setValue(NMAL7010Constant.MODE_MODIFY);
                }
            }
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.C.no(i).xxChkBox_PC.getValue())) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).effThruDt_PC, bizMsg.effThruDt_DH);
                    glblMsg.C.no(i).xxModeCd_C1.setValue(NMAL7010Constant.MODE_MODIFY);
                }
            }
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.D.no(i).xxChkBox_PD.getValue())) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).effThruDt_PD, bizMsg.effThruDt_DH);
                    glblMsg.D.no(i).xxModeCd_D1.setValue(NMAL7010Constant.MODE_MODIFY);
                }
            }
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.E.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.E.no(i).xxChkBox_PE.getValue())) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.E.no(i).effThruDt_PE, bizMsg.effThruDt_DH);
                    glblMsg.E.no(i).xxModeCd_E1.setValue(NMAL7010Constant.MODE_MODIFY);
                }
            }
        } else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.F.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.F.no(i).xxChkBox_PF.getValue())) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.F.no(i).effThruDt_PF, bizMsg.effThruDt_DH);
                    glblMsg.F.no(i).xxModeCd_F1.setValue(NMAL7010Constant.MODE_MODIFY);
                }
            }
        } else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.G.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.G.no(i).xxChkBox_PG.getValue())) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.G.no(i).effThruDt_PG, bizMsg.effThruDt_DH);
                    glblMsg.G.no(i).xxModeCd_G1.setValue(NMAL7010Constant.MODE_MODIFY);
                }
            }
        } else if (TAB_PRC_LIST_VAL_TI.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.H.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.H.no(i).xxChkBox_PH.getValue())) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.H.no(i).effThruDt_PH, bizMsg.effThruDt_DH);
                    glblMsg.H.no(i).xxModeCd_H1.setValue(NMAL7010Constant.MODE_MODIFY);
                }
            }
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(dplyTabD1)) {
            for (int i = 0; i < glblMsg.I.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.I.no(i).xxChkBox_PI.getValue())) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.I.no(i).effThruDt_PI, bizMsg.effThruDt_DH);
                }
            }
        }
    }
    // 2018/12/18 QC#29661 Add End

}
