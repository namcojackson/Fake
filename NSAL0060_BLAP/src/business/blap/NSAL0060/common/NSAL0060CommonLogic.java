/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0060.common;

import static business.blap.NSAL0060.constant.NSAL0060Constant.NZZM0000E;
import static business.blap.NSAL0060.constant.NSAL0060Constant.NZZM0001W;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0060.NSAL0060CMsg;
import business.blap.NSAL0060.NSAL0060SMsg;
import business.blap.NSAL0060.constant.NSAL0060Constant;
import business.db.DS_MDLTMsg;
import business.db.DS_MDL_GRPTMsg;
import business.db.DS_MDL_GRPTMsgArray;
import business.db.GPC_RATETMsg;
import business.db.TECH_TNG_HISTTMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
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
 *
 * Model Group Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/08/2013   Hitachi         Y.Igarashi      Create          N/A
 * 08/29/2013   Hitachi         T.Aoyagi        Update          QC1955
 * 06/27/2016   Hitachi         M.Gotou         Update          CSA QC7886
 * 2016/12/02   Hitachi         K.Kojima        Update          QC#14204
 * 2016/12/14   Hitachi         T.Mizuki        Update          QC#16419
 *</pre>
 */
public class NSAL0060CommonLogic {

    /**
     * 
     * searchMdlGrp
     * 
     * @param cMsg NSAL0060CMsg
     * @param sMsg NSAL0060SMsg
     * @param glblCmpyCd Global Company Code
     * @param isSubmit Submit flag
     */
    public static void searchMdlGrp(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg, String glblCmpyCd, boolean isSubmit) {
        clearTable(cMsg, sMsg);

        // mod start 2016/12/14 CSA QC#16419
        cMsg.setCommitSMsg(true);
        // mod end 2016/12/14 CSA QC#16419
        DS_MDL_GRPTMsg inMsg = new DS_MDL_GRPTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);

        if (hasValue(cMsg.mdlGrpNm_SC) && hasValue(cMsg.mdlGrpDescTxt_SC)) {
            inMsg.setSQLID("002");
            inMsg.setConditionValue("mdlGrpNm01", cMsg.mdlGrpNm_SC.getValue());
            inMsg.setConditionValue("mdlGrpDescTxt01", cMsg.mdlGrpDescTxt_SC.getValue());
        } else if (hasValue(cMsg.mdlGrpNm_SC)) {
            inMsg.setSQLID("003");
            inMsg.setConditionValue("mdlGrpNm01", cMsg.mdlGrpNm_SC.getValue());
        // START 2013/08/29 T.Aoyagi [QC1955,MOD]
        // } else {
        } else if (hasValue(cMsg.mdlGrpDescTxt_SC)) {
        // END 2013/08/29 T.Aoyagi [QC1955,MOD]
            inMsg.setSQLID("004");
            inMsg.setConditionValue("mdlGrpDescTxt01", cMsg.mdlGrpDescTxt_SC.getValue());
        // START 2013/08/29 T.Aoyagi [QC1955,ADD]
        } else if (!hasValue(cMsg.mdlGrpNm_SC) && !hasValue(cMsg.mdlGrpDescTxt_SC)) {
            inMsg.setSQLID("005");
        // END 2013/08/29 T.Aoyagi [QC1955,ADD]
        }

        DS_MDL_GRPTMsgArray outAry = (DS_MDL_GRPTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outAry.getValidCount() < 1) {
            if (!isSubmit) {
                cMsg.setMessageInfo(NZZM0000E);
            }
            return;
        }

        int dataCount = outAry.getValidCount();
        if (outAry.getValidCount() > sMsg.A.length()) {
            // too many
            if (!isSubmit) {
                cMsg.setMessageInfo(NZZM0001W);
            }
            dataCount = sMsg.A.length();
        }

        for (int i = 0; i < dataCount; i++) {
            EZDMsg.copy(outAry.no(i), "", sMsg.A.no(i), "SR");
            setValue(sMsg.A.no(i).ezUpTime_SR, outAry.no(i).ezUpTime);
            setValue(sMsg.A.no(i).ezUpTimeZone_SR, outAry.no(i).ezUpTimeZone);
            // START 2016/12/02 K.Kojima [QC#14204,ADD]
            setValue(sMsg.A.no(i).xxRecHistCratTs_A, outAry.no(i).ezInTime);
            setValue(sMsg.A.no(i).xxRecHistCratByNm_A, outAry.no(i).ezInUserID);
            setValue(sMsg.A.no(i).xxRecHistUpdTs_A, outAry.no(i).ezUpTime);
            setValue(sMsg.A.no(i).xxRecHistUpdByNm_A, outAry.no(i).ezUpUserID);
            setValue(sMsg.A.no(i).xxRecHistTblNm_A, outAry.no(i).ezTableID);
            // END 2016/12/02 K.Kojima [QC#14204,ADD]
        }
        sMsg.A.setValidCount(dataCount);
        EZDMsg.copy(sMsg.A, null, sMsg.B, null);
        copySMsgToCMsg(cMsg, sMsg);
        setPageNum(cMsg, 1, cMsg.A.getValidCount(), dataCount);
    }

    /**
     * 
     * clearTable
     * 
     * @param cMsg NSAL0060CMsg
     * @param sMsg NSAL0060SMsg
     */
    public static void clearTable(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        cMsg.A.setValidCount(0);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C);
        sMsg.A.setValidCount(0);
        sMsg.B.setValidCount(0);
        sMsg.C.setValidCount(0);
    }

    /**
     * 
     * isSameMdlGrpNm
     * 
     * @param mdlGrpNm Model Group Name
     * @param glblCmpyCd Global Company Code
     * @return true : same / false : other
     */
    public static boolean isSameMdlGrpNm(String mdlGrpNm, String glblCmpyCd) {
        DS_MDL_GRPTMsg inMsg = new DS_MDL_GRPTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdlGrpNm01", mdlGrpNm);
        inMsg.setSQLID("001");
        int count = EZDTBLAccessor.count(inMsg);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 
     * existRelatedInfo
     * 
     * @param mdlGrpId Model Group ID
     * @param glblCmpyCd Global Company Code
     * @return true : exist / false : other
     */
    public static boolean existRelatedInfo(BigDecimal mdlGrpId, String glblCmpyCd) {
        if (existGpcRateInfo(mdlGrpId, glblCmpyCd)) {
            return true;
        }
        if (existTechTngHistInfo(mdlGrpId, glblCmpyCd)) {
            return true;
        }
        if (existDsMdlInfo(mdlGrpId, glblCmpyCd)) {
            return true;
        }
        return false;
    }

    private static boolean existGpcRateInfo(BigDecimal mdlGrpId, String glblCmpyCd) {
        GPC_RATETMsg inMsg = new GPC_RATETMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdlGrpId01", mdlGrpId);
        inMsg.setSQLID("001");
        int count = EZDTBLAccessor.count(inMsg);
        if (count > 0) {
            return true;
        }
        return false;
    }

    private static boolean existTechTngHistInfo(BigDecimal mdlGrpId, String glblCmpyCd) {
        TECH_TNG_HISTTMsg inMsg = new TECH_TNG_HISTTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdlGrpId01", mdlGrpId);
        inMsg.setSQLID("001");
        int count = EZDTBLAccessor.count(inMsg);
        if (count > 0) {
            return true;
        }
        return false;
    }

    private static boolean existDsMdlInfo(BigDecimal mdlGrpId, String glblCmpyCd) {
        DS_MDLTMsg inMsg = new DS_MDLTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdlGrpId01", mdlGrpId);
        inMsg.setSQLID("001");
        int count = EZDTBLAccessor.count(inMsg);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 
     * locDsMdlGrp
     * 
     * @param mdlGrpId DS_MDL_GRP_ID
     * @param glblCmpyCd Global Company Code
     * @return DS_MDL_GRPTMsg
     */
    public static DS_MDL_GRPTMsg locDsMdlGrp(BigDecimal mdlGrpId, String glblCmpyCd) {
        DS_MDL_GRPTMsg inMsg = new DS_MDL_GRPTMsg();

        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.mdlGrpId, mdlGrpId);
        return (DS_MDL_GRPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
    }

    /**
     * 
     * copyCMsgToSMsg
     * 
     * @param cMsg NSAL0060CMsg
     * @param sMsg NSAL0060SMsg
     */
    public static void copyCMsgToSMsg(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg) {
        copyCMsgToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt() - 1);
    }

    /**
     * 
     * copyCMsgToSMsg
     * 
     * @param cMsg NSAL0060CMsg
     * @param sMsg NSAL0060SMsg
     * @param fromCnt From Count
     */
    public static void copyCMsgToSMsg(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg, int fromCnt) {
        int curCnt = fromCnt;
        int cMsgCnt = 0;
        for (int i = curCnt; i < (curCnt + cMsg.A.getValidCount()); i++) {
            EZDMsg.copy(cMsg.A.no(cMsgCnt), null, sMsg.A.no(i), null);
            cMsgCnt++;
        }
    }

    /**
     * 
     * setPagenation
     * 
     * @param cMsg NSAL0060CMsg
     * @param sMsg NSAL0060SMsg
     * @param page set page
     */
    public static void setPagenation(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg, int page) {
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(page);
        cMsg.xxPageShowToNum.clear();

        NSAL0060CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * 
     * copySMsgToCMsg
     * 
     * @param cMsg NSAL0060CMsg
     * @param sMsg NSAL0060SMsg
     */
    public static void copySMsgToCMsg(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg) {
        // 1page copy（SMsg -> CMsg）
        int i = 0;
        for (; i < sMsg.A.getValidCount(); i++) {
            if (i == cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(i);
    }

    /**
     * 
     * copyFromSMsgOntoCmsg
     * 
     * @param cMsg NSAL0060CMsg
     * @param sMsg NSAL0060SMsg
     */
    public static void copyFromSMsgOntoCmsg(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);
        setPageNum(cMsg, (pagenationFrom + 1), (pagenationFrom + cMsg.A.getValidCount()), sMsg.A.getValidCount());
    }

    /**
     * 
     * setPageNum
     * 
     * @param cMsg NSAL0060CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNum(NSAL0060CMsg cMsg, int fromCnt, int toCnt, int allCnt) {
        cMsg.xxPageShowFromNum.setValue(fromCnt);
        cMsg.xxPageShowFromNum_BK.setValue(fromCnt);
        cMsg.xxPageShowToNum.setValue(toCnt);
        cMsg.xxPageShowOfNum.setValue(allCnt);
    }

    /**
     * 
     * clearPageNum
     * 
     * @param cMsg NSAL0060CMsg
     */
    public static void clearPageNum(NSAL0060CMsg cMsg) {
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
    }

    // add start 2016/06/27 CSA Defect#7886
    /**
     * Get Log
     */
    private static Logger logger = Logger.getLogger(NSAL0060CommonLogic.class);

    /**
    * invokeMasterDataMessaging
    * @param isModel String
    * @param ezUpTime String
    * @param mdlGrpId String
    * @param mdlGrpNm String
    */
    public static void invokeMasterDataMessaging(String isModel, String ezUpTime, String mdlGrpId, String mdlGrpNm) {

        try {
            Event event = new Event();
            event.setReferencedEntity(NSAL0060Constant.REF_ENTITY_ITEM);

            if (NSAL0060Constant.UPDATE.equals(isModel)) {
                event.setOperationType(OPERATIONTYPES.UPDATE);
            } else if (NSAL0060Constant.CREATE.equals(isModel)) {
                event.setOperationType(OPERATIONTYPES.CREATE);
            } else {
                event.setOperationType(OPERATIONTYPES.DELETE);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSS");
            Date formatDate = sdf.parse(ezUpTime);

            GregorianCalendar c = new GregorianCalendar();
            c.setTime(formatDate);
            XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            event.setCreateStamp(date2);

            event.setId(1);

            ArrayOfKeyReference ak = new ArrayOfKeyReference();

            // Set Key Model ID
            KeyReference kr = new KeyReference();
            kr.setType(REFERENCETYPES.TECHNICAL);

            ArrayOfAttribute aa = new ArrayOfAttribute();
            Attribute a = new Attribute();
            a.setName(NSAL0060Constant.KEY_NAME_MDL_GRP_ID);
            a.setValue(mdlGrpId);
            aa.getAttribute().add(a);
            kr.setAttributes(aa);

            ak.getKeyReference().add(kr);

            // Set Key Model Name
            kr = new KeyReference();
            kr.setType(REFERENCETYPES.MNEMONICAL);

            aa = new ArrayOfAttribute();
            a = new Attribute();
            a.setName(NSAL0060Constant.KEY_NAME_MDL_GRP_NM);
            a.setValue(mdlGrpNm);
            aa.getAttribute().add(a);
            kr.setAttributes(aa);

            ak.getKeyReference().add(kr);

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
    // add end 2016/06/27 CSA Defect#7886
}
