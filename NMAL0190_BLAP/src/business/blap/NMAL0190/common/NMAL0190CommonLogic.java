/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0190.common;

import static business.blap.NMAL0190.constant.NMAL0190Constant.COL_EZUPTIME;
import static business.blap.NMAL0190.constant.NMAL0190Constant.COL_EZUPTIMEZONE;
import static business.blap.NMAL0190.constant.NMAL0190Constant.COL_FIRST_NM;
import static business.blap.NMAL0190.constant.NMAL0190Constant.COL_LAST_NM;
import static business.blap.NMAL0190.constant.NMAL0190Constant.COL_MDSE_DESC_SHORT_TXT_FR;
import static business.blap.NMAL0190.constant.NMAL0190Constant.COL_MDSE_DESC_SHORT_TXT_TO;
import static business.blap.NMAL0190.constant.NMAL0190Constant.COL_MDSE_ITEM_STS_CD;
import static business.blap.NMAL0190.constant.NMAL0190Constant.COL_SUPD_FROM_MDSE_CD;
import static business.blap.NMAL0190.constant.NMAL0190Constant.COL_SUPD_RELN_STAGE_DT;
import static business.blap.NMAL0190.constant.NMAL0190Constant.COL_SUPD_TO_MDSE_CD;
import static business.blap.NMAL0190.constant.NMAL0190Constant.COL_USR_NM;
import static business.blap.NMAL0190.constant.NMAL0190Constant.UP_USER_MAX_TEXT_SIZE;
import static com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant.SUPD_RELN_SQ;
import static business.blap.NMAL0190.constant.NMAL0190Constant.REF_ENTITY_ITEM;
import static business.blap.NMAL0190.constant.NMAL0190Constant.MODE_CD_KEY_NAME;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL0190.NMAL0190CMsg;
import business.blap.NMAL0190.NMAL0190Query;
import business.blap.NMAL0190.NMAL0190SMsg;
import business.blap.NMAL0190.NMAL0190_ACMsg;
import business.blap.NMAL0190.NMAL0190_ASMsg;
import business.blap.NMAL0190.NMAL0190_BCMsg;
import business.blap.NMAL0190.NMAL0190_OSMsg;
import business.db.MDSETMsg;
import business.db.MDSE_ITEM_FLIP_SETTMsg;
import business.db.MDSE_ITEM_STSTMsg;
import business.db.SUPDTMsg;
import business.db.SUPD_RELNTMsg;
import business.db.SUPD_RELN_STAGETMsg;
import business.file.NMAL0190F00FMsg;
import org.apache.log4j.Logger;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;
import com.canon.cusa.s21.framework.log.S21AbendException;
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
 * NMAL0190CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         T.Arima         Create          N/A
 * 2016/03/02   CITS            S.Tanikawa      UPDATE          QC#2669
 * 2016/06/23   SRAA            K.Aratani       UPDATE          QC#10653
 * 2016/11/10   Fujitsu         R.Nakamura      UPDATE          QC#2872
 * 
 *</pre>
 */

public class NMAL0190CommonLogic {

    private static Logger logger = Logger.getLogger(NMAL0190CommonLogic.class);
    // ADD START 2016/03/03 QC#2669
    /**
     * get glblMsg.A info
     * @param glblMsg NMAL0190SMsg
     * @param keyMap Map<String,String>
     * @param i int(index)
     */
    public static void makeHdrInfo(NMAL0190SMsg glblMsg, Map<String, String> keyMap, int i) {
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).supdToMdseCd, keyMap.get(COL_SUPD_TO_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).supdFromMdseCd, keyMap.get(COL_SUPD_FROM_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).mdseDescShortTxt_TO, keyMap.get(COL_MDSE_DESC_SHORT_TXT_TO));
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).mdseDescShortTxt_FR, keyMap.get(COL_MDSE_DESC_SHORT_TXT_FR));
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpTime_H, keyMap.get(COL_EZUPTIME));
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpTimeZone_H, keyMap.get(COL_EZUPTIMEZONE));
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).mdseItemStsCd_SL, keyMap.get(COL_MDSE_ITEM_STS_CD));
        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).supdRelnStageDt, keyMap.get(COL_SUPD_RELN_STAGE_DT));
    }

    /**
     * getMdseCompatibleInfo
     * @param glblMsg NMAL0190SMsg
     * @param i int(index)
     */
    public static void getMdseCompatibleInfo(NMAL0190SMsg glblMsg, int i) {

        // set condition for MDSE_ITEM_FLIP_SET
        String sToMdseCd = glblMsg.A.no(i).supdToMdseCd.getValue();
        String relnMdseCd = glblMsg.A.no(i).relnMdseCd.getValue();

        // search Compatible Forward (param1 = supdTo, param2 = relnMdseCd)
        S21SsmEZDResult ssmResultItemFlipFor = NMAL0190Query.getInstance().getMdseItemFlipSet(sToMdseCd, relnMdseCd);
        if (ssmResultItemFlipFor.isCodeNormal()) {
            Map<String, String> flipSetMapFor = (Map<String, String>) ssmResultItemFlipFor.getResultObject();

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxSupdFlg_FW, ZYPConstant.CHKBOX_ON_Y);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxSupdFlg_FH, ZYPConstant.CHKBOX_ON_Y);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpTimeZone_F, flipSetMapFor.get(COL_EZUPTIMEZONE));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpUserID_F, flipSetMapFor.get(COL_USR_NM));

            if (flipSetMapFor.get(COL_EZUPTIME) != null) {
                String ezUpDateFormat = ZYPDateUtil.convertFormat(flipSetMapFor.get(COL_EZUPTIME).substring(0, 8), S21CalendarUtilConstants.TYPE_YYYYMMDD, S21CalendarUtilConstants.TYPE_MMDDYYYY, Character.valueOf('/'));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpTime_F, ezUpDateFormat);
            }

            String updateUser = "";
            if (flipSetMapFor.get(COL_USR_NM) != null) {
                updateUser = flipSetMapFor.get(COL_USR_NM) + " - ";
            }
            if (flipSetMapFor.get(COL_FIRST_NM) != null) {
                updateUser += flipSetMapFor.get(COL_FIRST_NM) + " ";
            }
            if (flipSetMapFor.get(COL_LAST_NM) != null) {
                updateUser += flipSetMapFor.get(COL_LAST_NM);
            }

            if (updateUser.length() > UP_USER_MAX_TEXT_SIZE) {
                updateUser = updateUser.substring(0, UP_USER_MAX_TEXT_SIZE);
            }
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxAuthByNm, updateUser);
        }
        // search Compatible Backward (param1 = relnMdseCd, param2 = supdTo)
        S21SsmEZDResult ssmResultItemFlipBck = NMAL0190Query.getInstance().getMdseItemFlipSet(relnMdseCd, sToMdseCd);
        if (ssmResultItemFlipBck.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxSupdFlg_BW, ZYPConstant.CHKBOX_ON_Y);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxSupdFlg_BH, ZYPConstant.CHKBOX_ON_Y);
        }
    }

    /**
     * createPullDownMdseItemStatus
     * @param cMsg NMAL0190_ACMsg
     */
    public static void createPullDownMdseItemStatus(NMAL0190_ACMsg cMsg) {
        S21SsmEZDResult result = NMAL0190Query.getInstance().getItemStatusList();
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                if (map != null && map.get("MDSE_ITEM_STS_CD") != null) {
                    cMsg.mdseItemStsCd_PD.no(i).setValue((String) map.get("MDSE_ITEM_STS_CD"));
                    cMsg.mdseItemStsNm_PD.no(i).setValue((String) map.get("MDSE_ITEM_STS_NM"));
                    i++;
                }
            }
        }
    }

    // ADD END 2016/03/03 QC#2669
    /**
     * Copy bizMsg.A and bizMsg.B from glblMsg
     * @param bizMsg NMAL0190CMsg
     * @param glblMsg NMAL0190SMsg
     */
    public static void copyBizMsgFromGlblMsg(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg) {

        // Table "A" is only 1 record.
        NMAL0190_ASMsg sLineMsg = glblMsg.A.no(0);
        NMAL0190_ACMsg aLineMsg = bizMsg.A.no(0);

        // UPDATE START 2016/03/02 QC#2669
        ZYPEZDItemValueSetter.setValue(aLineMsg.supdToMdseCd, sLineMsg.supdToMdseCd);
        ZYPEZDItemValueSetter.setValue(aLineMsg.mdseDescShortTxt_TO, sLineMsg.mdseDescShortTxt_TO);
        ZYPEZDItemValueSetter.setValue(aLineMsg.supdFromMdseCd, sLineMsg.supdFromMdseCd);
        ZYPEZDItemValueSetter.setValue(aLineMsg.mdseDescShortTxt_FR, sLineMsg.mdseDescShortTxt_FR);
        // ZYPEZDItemValueSetter.setValue(aLineMsg.dsctnFlg,
        // sLineMsg.dsctnFlg);
        ZYPEZDItemValueSetter.setValue(aLineMsg.mdseItemStsCd_SL, sLineMsg.mdseItemStsCd_SL);
        // create Pull Down List
        createPullDownMdseItemStatus(aLineMsg);
        ZYPEZDItemValueSetter.setValue(aLineMsg.supdRelnStageDt, sLineMsg.supdRelnStageDt);
        ZYPEZDItemValueSetter.setValue(aLineMsg.ezUpTime_H, sLineMsg.ezUpTime_H);
        ZYPEZDItemValueSetter.setValue(aLineMsg.ezUpTimeZone_H, sLineMsg.ezUpTimeZone_H);
        bizMsg.A.setValidCount(1);

        // if (FLG_ON_Y.equals(sLineMsg.dsctnFlg.getValue()) ||
        // MDSE_ITEM_STS.OBSOLETE.equals(sLineMsg.mdseItemStsCd.getValue()))
        // {
        // ZYPEZDItemValueSetter.setValue(aLineMsg.dsctnFlg,
        // FLG_ON_Y);
        // } else {
        // ZYPEZDItemValueSetter.setValue(aLineMsg.dsctnFlg,
        // FLG_OFF_N);
        // }

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            sLineMsg = glblMsg.A.no(i);
            NMAL0190_BCMsg bLineMsg = bizMsg.B.no(i);
            ZYPEZDItemValueSetter.setValue(bLineMsg.supdToMdseCd_B, sLineMsg.supdToMdseCd);
            ZYPEZDItemValueSetter.setValue(bLineMsg.relnMdseCd, sLineMsg.relnMdseCd);

            ZYPEZDItemValueSetter.setValue(bLineMsg.xxSupdFlg_FW, sLineMsg.xxSupdFlg_FW);
            ZYPEZDItemValueSetter.setValue(bLineMsg.xxSupdFlg_FH, sLineMsg.xxSupdFlg_FH);
            ZYPEZDItemValueSetter.setValue(bLineMsg.xxSupdFlg_BW, sLineMsg.xxSupdFlg_BW);
            ZYPEZDItemValueSetter.setValue(bLineMsg.xxSupdFlg_BH, sLineMsg.xxSupdFlg_BH);

            ZYPEZDItemValueSetter.setValue(bLineMsg.xxAuthByNm, sLineMsg.xxAuthByNm);
            ZYPEZDItemValueSetter.setValue(bLineMsg.ezUpUserID_F, sLineMsg.ezUpUserID_F);
            ZYPEZDItemValueSetter.setValue(bLineMsg.ezUpTime_F, sLineMsg.ezUpTime_F);
            ZYPEZDItemValueSetter.setValue(bLineMsg.ezUpTimeZone_F, sLineMsg.ezUpTimeZone_F);

            // Add Start 2016/11/10 QC#2872
            ZYPEZDItemValueSetter.setValue(bLineMsg.xxRecHistCratTs_B1, sLineMsg.xxRecHistCratTs_A1);
            ZYPEZDItemValueSetter.setValue(bLineMsg.xxRecHistCratByNm_B1, sLineMsg.xxRecHistCratByNm_A1);
            ZYPEZDItemValueSetter.setValue(bLineMsg.xxRecHistUpdTs_B1, sLineMsg.xxRecHistUpdTs_A1);
            ZYPEZDItemValueSetter.setValue(bLineMsg.xxRecHistUpdByNm_B1, sLineMsg.xxRecHistUpdByNm_A1);
            ZYPEZDItemValueSetter.setValue(bLineMsg.xxRecHistTblNm_B1, sLineMsg.xxRecHistTblNm_A1);
            // Add End 2016/11/10 QC#2872
        }
        bizMsg.B.setValidCount(glblMsg.A.getValidCount());

        ZYPEZDItemValueSetter.setValue(bizMsg.xxAdvcFlg, ZYPConstant.FLG_OFF_N);
        // UPDATE END  2016/03/02 QC#2669
    }
    /**
     * Get SUPD_RELN_STAGE_SQ
     * @return sequence.
     */
    public static BigDecimal getSupdRelnSeq() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(SUPD_RELN_SQ);
    }

    /**
     * Exist SUPD
     * @param glblCmpyCd Global Company Code.
     * @param mdseCd Mdse Code.
     * @return MDSETMsg
     */
    public static MDSETMsg existMdse(String glblCmpyCd, String mdseCd) {

        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return null;
        }

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.ezCancelFlag, "0");
        MDSETMsg retVal = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
        return retVal;

    }

    /**
     * Exist SUPD
     * @param glblCmpyCd Global Company Code.
     * @param mdseCd Mdse Code.
     * @return MDSETMsg
     */
    public static SUPDTMsg existSupd(String glblCmpyCd, String mdseCd) {

        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return null;
        }

        SUPDTMsg supdTMsg = new SUPDTMsg();
        ZYPEZDItemValueSetter.setValue(supdTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(supdTMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(supdTMsg.ezCancelFlag, "0");
        SUPDTMsg retVal = (SUPDTMsg) S21CacheTBLAccessor.findByKey(supdTMsg);
        return retVal;

    }

    // UPDATE START 2016/03/07 QC#2669
    /**
     * Exist SUPD_RELN
     * @param glblCmpyCd Global Company Code.
     * @param supdToMdseCd String
     * @param supdFromMdseCd String
     * @return MDSETMsg
     */
    public static SUPD_RELNTMsg existSupdReln(String glblCmpyCd, String supdToMdseCd, String supdFromMdseCd) {

        if (!ZYPCommonFunc.hasValue(supdToMdseCd) || !ZYPCommonFunc.hasValue(supdFromMdseCd)) {
            return null;
        }

        SUPD_RELNTMsg supdRelnMsg = new SUPD_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(supdRelnMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(supdRelnMsg.supdToMdseCd, supdToMdseCd);
        ZYPEZDItemValueSetter.setValue(supdRelnMsg.supdFromMdseCd, supdFromMdseCd);
        ZYPEZDItemValueSetter.setValue(supdRelnMsg.ezCancelFlag, "0");
        SUPD_RELNTMsg retVal = (SUPD_RELNTMsg) S21CacheTBLAccessor.findByKey(supdRelnMsg);
        return retVal;

    }
    // UPDATE END 2016/03/07 QC#2669

    // ADD START 2016/03/07 QC#2669
    /**
     * isExistSupdFrom
     * @param lineMsg NMAL0190_ACMsg
     * @return boolean
     */
    public static boolean isExistSupdFrom(NMAL0190_ACMsg lineMsg) {

        Integer resultCnt = NMAL0190Query.getInstance().getCountSupdFrom(lineMsg);

        if (resultCnt > 0) {
            return true;
        }
        return false;
    }
    /**
     * isLoopSupd
     * @param lineMsg NMAL0190_ACMsg
     * @return boolean
     */
    public static boolean isLoopSupd(NMAL0190_ACMsg lineMsg) {

        S21SsmEZDResult result = NMAL0190Query.getInstance().getSupdReln(lineMsg.supdFromMdseCd.getValue());

        if (result.isCodeNormal()) {
            List<Map<String, String>> list = (List<Map<String, String>>) result.getResultObject();
            if (list != null && list.size() > 0) {
                for (Map<String, String> map : list) {
                    if (lineMsg.supdToMdseCd.getValue().equals(map.get(COL_SUPD_FROM_MDSE_CD))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    // ADD END 2016/03/07 QC#2669

    /**
     * Find SUPD_RELN_STAGE
     * @param glblCmpyCd Global Company Code.
     * @param seq SupdRelnStagePk
     * @param isNoWait True(findByKeyForUpdateNoWait)
     * @return MDSETMsg
     */
    public static SUPD_RELN_STAGETMsg findSupdRelnStage(String glblCmpyCd, BigDecimal seq, boolean isNoWait) {
        SUPD_RELN_STAGETMsg retVal = null;

        if (!ZYPCommonFunc.hasValue(seq)) {
            return retVal;
        }

        SUPD_RELN_STAGETMsg supdRelnStageTMsg = new SUPD_RELN_STAGETMsg();
        ZYPEZDItemValueSetter.setValue(supdRelnStageTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(supdRelnStageTMsg.supdRelnStagePk, seq);

        if (isNoWait) {
            retVal = (SUPD_RELN_STAGETMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(supdRelnStageTMsg);
        } else {
            retVal = (SUPD_RELN_STAGETMsg) S21CacheTBLAccessor.findByKey(supdRelnStageTMsg);
        }
        return retVal;

    }
    // UPDATE START 2016/03/07 QC#2669
    /**
     * find MdseItemSts
     * @param glblCmpyCd String
     * @param mdseItemStsCd String
     * @return MDSE_ITEM_STSTMsg
     */
    public static MDSE_ITEM_STSTMsg findMdseItemSts(String glblCmpyCd, String mdseItemStsCd) {
        MDSE_ITEM_STSTMsg resTMsg = null;
        if (!ZYPCommonFunc.hasValue(mdseItemStsCd)) {
            return null;
        }

        MDSE_ITEM_STSTMsg tMsg = new MDSE_ITEM_STSTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemStsCd, mdseItemStsCd);

        resTMsg = (MDSE_ITEM_STSTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return resTMsg;
    }

    /**
     * find DsMdseInfo
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param isNoWait true(findByKeyForUpdateNoWait)
     * @return MDSETMsg
     */
    public static MDSETMsg findDsMdseInfo(String glblCmpyCd, String mdseCd, boolean isNoWait) {
        MDSETMsg resTMsg = null;
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return null;
        }

        MDSETMsg tMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);

        if (isNoWait) {
            resTMsg = (MDSETMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        } else {
            resTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
        }

        return resTMsg;
    }
    /**
     * Find Mdse
     * @param glblCmpyCd Global Company Code.
     * @param mdseCd Mdse Code.
     * @param isNoWait True(findByKeyForUpdateNoWait)
     * @return MDSETMsg
     */
    public static MDSETMsg findMdse(String glblCmpyCd, String mdseCd, boolean isNoWait) {
        MDSETMsg resTMsg = null;
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return null;
        }

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);

        if (isNoWait) {
            resTMsg = (MDSETMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(mdseTMsg);
        } else {
            resTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
        }

        return resTMsg;

    }

    /**
     * Exist MDSE_ITEM_FLIP_SET
     * @param glblCmpyCd Global Company Code.
     * @param mdseCd Mdse Code.
     * @param relnMdseCd Reln Mdse Code.
     * @return MDSETMsg
     */
    public static MDSE_ITEM_FLIP_SETTMsg existMdseItemFlipSet(String glblCmpyCd, String mdseCd, String relnMdseCd) {

        if (!ZYPCommonFunc.hasValue(mdseCd) || !ZYPCommonFunc.hasValue(relnMdseCd)) {
            return null;
        }

        MDSE_ITEM_FLIP_SETTMsg itemSetTMsg = new MDSE_ITEM_FLIP_SETTMsg();
        ZYPEZDItemValueSetter.setValue(itemSetTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(itemSetTMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(itemSetTMsg.relnMdseCd, relnMdseCd);
        MDSE_ITEM_FLIP_SETTMsg retVal = (MDSE_ITEM_FLIP_SETTMsg) S21CacheTBLAccessor.findByKey(itemSetTMsg);
        return retVal;

    }
    //UPDATE END 2016/03/07 QC#2669

    /**
     * Build Supersession Header
     * @param glblMsg Global Message
     */
    public static void buildSupdHdr(NMAL0190SMsg glblMsg) {
        NMAL0190_OSMsg csvMsg = glblMsg.O.no(0);
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C1, "Item#");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C2, "Relationship Type");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C3, "");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C4, "Related Item#");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C5, "Related Description");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C6, "Date");
        glblMsg.O.setValidCount(1);
    }

    /**
     * Build Supersession Bottom
     * @param glblMsg Global Message
     */
    public static void buildSupdBtm(NMAL0190SMsg glblMsg) {
        int index = glblMsg.O.getValidCount();
        NMAL0190_OSMsg csvMsg = glblMsg.O.no(index);
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C1, "Planning From Updates");
        index++;
        glblMsg.O.setValidCount(index);
    }
    /**
     * Build Old Revision Header
     * @param glblMsg Global Message
     */
    public static void buildOldRevHdr(NMAL0190SMsg glblMsg) {
        int index = glblMsg.O.getValidCount();
        NMAL0190_OSMsg csvMsg = glblMsg.O.no(index);
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C1, "Disabled the blow Planning Records");
        index++;
        glblMsg.O.setValidCount(index);
        buildOldRevSubHdr(glblMsg);
    }
    /**
     * Build Old Revision Sub Header
     * @param glblMsg Global Message
     */
    private static void buildOldRevSubHdr(NMAL0190SMsg glblMsg) {
        int index = glblMsg.O.getValidCount();
        NMAL0190_OSMsg csvMsg = glblMsg.O.no(index);

        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C1, "Item#");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C2, "Item Description");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C3, "");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C4, "WAREHOUSE");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C5, "SUBWAREHOUSE");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C6, "Action");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C7, "Min Qty");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C8, "Max Qty");
        index++;
        glblMsg.O.setValidCount(index);
    }
    /**
     * Build New Revision Header
     * @param glblMsg Global Message
     */
    public static void buildNewRevHdr(NMAL0190SMsg glblMsg) {
        int index = glblMsg.O.getValidCount();
        // new Linew
        index++;
        glblMsg.O.setValidCount(index);

        NMAL0190_OSMsg csvMsg = glblMsg.O.no(index);
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C1, "Created the Planning Records");
        index++;
        glblMsg.O.setValidCount(index);
        buildNewRevSubHdr(glblMsg);
    }
    /**
     * Build New Revision Sub Header
     * @param glblMsg Global Message
     */
    private static void buildNewRevSubHdr(NMAL0190SMsg glblMsg) {
        int index = glblMsg.O.getValidCount();
        NMAL0190_OSMsg csvMsg = glblMsg.O.no(index);

        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C1, "Item#");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C2, "Item Description");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C3, "");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C4, "WAREHOUSE");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C5, "SUBWAREHOUSE");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C6, "Action");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C7, "Min Qty");
        ZYPEZDItemValueSetter.setValue(csvMsg.xxEdtCdNm_C8, "Max Qty");
        index++;
        glblMsg.O.setValidCount(index);
    }
    /**
     * Write CSV Message
     * @param csvFilePath CSV File Path
     * @param glblMsg NMAL0190SMsg
     */
    public static void writeCsvMsg(String csvFilePath, NMAL0190SMsg glblMsg) {
        NMAL0190F00FMsg csvMsg = new NMAL0190F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(csvFilePath, csvMsg);

        csvOutFile.writeHeader(new String[] {"Item Master Setup updates" });

        for (int i = 0; i < glblMsg.O.getValidCount(); i++) {
            csvMsg.clear();
            if (glblMsg.O.no(i).xxEdtCdNm_C1 == null) {
                ZYPEZDItemValueSetter.setValue(glblMsg.O.no(i).xxEdtCdNm_C1, "");
            }
            if (glblMsg.O.no(i).xxEdtCdNm_C2 == null) {
                ZYPEZDItemValueSetter.setValue(glblMsg.O.no(i).xxEdtCdNm_C2, "");
            }
            if (glblMsg.O.no(i).xxEdtCdNm_C3 == null) {
                ZYPEZDItemValueSetter.setValue(glblMsg.O.no(i).xxEdtCdNm_C3, "");
            }
            if (glblMsg.O.no(i).xxEdtCdNm_C4 == null) {
                ZYPEZDItemValueSetter.setValue(glblMsg.O.no(i).xxEdtCdNm_C4, "");
            }
            if (glblMsg.O.no(i).xxEdtCdNm_C5 == null) {
                ZYPEZDItemValueSetter.setValue(glblMsg.O.no(i).xxEdtCdNm_C5, "");
            }
            if (glblMsg.O.no(i).xxEdtCdNm_C6 == null) {
                ZYPEZDItemValueSetter.setValue(glblMsg.O.no(i).xxEdtCdNm_C6, "");
            }

            if (glblMsg.O.no(i).xxEdtCdNm_C7 == null) {
                ZYPEZDItemValueSetter.setValue(glblMsg.O.no(i).xxEdtCdNm_C7, "");
            }

            if (glblMsg.O.no(i).xxEdtCdNm_C8 == null) {
                ZYPEZDItemValueSetter.setValue(glblMsg.O.no(i).xxEdtCdNm_C8, "");
            }
            EZDMsg.copy(glblMsg.O.no(i), null, csvMsg, null);
            csvOutFile.write();
        }
        csvOutFile.close();
    }

    /**
     * Format CSV Date
     * @param yyyyMMdd Date
     * @return dd-MMM-yyyy
     */
    public static String formatCsvDate(String yyyyMMdd) {
        if (yyyyMMdd == null) {
            return null;
        }
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyyMMdd");
        inFormat.setLenient(false);
        Date inputDate;
        try {
            inputDate = inFormat.parse(yyyyMMdd);
        } catch (ParseException e) {
            throw new S21AbendException(e);
        }
        SimpleDateFormat sdf;
        try {
            sdf = new SimpleDateFormat("dd-MMM-yyyy");
        } catch (Exception e) {
            throw new S21AbendException(e);
        }
        return sdf.format(inputDate);
    }
    
    //QC#10653
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
            Attribute a = new Attribute();
            a.setName(MODE_CD_KEY_NAME);
            for (String mdseCd : mdseCdListToDealConfig) {
	            a.setValue(mdseCd);
	            
	            aa.getAttribute().add(a);
	            kr.setAttributes(aa);

	            ak.getKeyReference().add(kr);
            }
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
}
