/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1290.common;

import static business.blap.NSAL1290.constant.NSAL1290Constant.NZZM0001W;
import static business.blap.NSAL1290.constant.NSAL1290Constant.NZZM0002I;
import static business.blap.NSAL1290.constant.NSAL1290Constant.ZZZM9001E;
import static business.blap.NSAL1290.constant.NSAL1290Constant.ZZZM9005W;
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
import business.blap.NSAL1290.NSAL1290CMsg;
import business.blap.NSAL1290.NSAL1290Query;
import business.blap.NSAL1290.NSAL1290SMsg;
import business.blap.NSAL1290.NSAL1290_ACMsg;
import business.blap.NSAL1290.NSAL1290_ACMsgArray;
import business.blap.NSAL1290.NSAL1290_ASMsg;
import business.blap.NSAL1290.constant.NSAL1290Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
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
 * Billing Counter Mapping Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/01   Hitachi         M.Gotou         Create          N/A
 * 2016/07/13   Hitachi         A.Kohinata      Update          QC#11813
 *</pre>
 */
public class NSAL1290CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSAL1290CMsg
     * @param sMsg NSAL1290SMsg
     */
    public static void clearMsg(NSAL1290CMsg cMsg, NSAL1290SMsg sMsg) {
        // add start 2016/07/13 QC#11813
        String mtrLbNm_P = cMsg.mtrLbNm_P.getValue();
        // add end 2016/07/13 QC#11813
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        // add start 2016/07/13 QC#11813
        setValue(cMsg.mtrLbNm_P, mtrLbNm_P);
        // add end 2016/07/13 QC#11813
    }

    /**
     * Get Search Data
     * @param cMsg NSAL1290CMsg
     * @param sMsg NSAL1290SMsg
     */
    public static void getSearchData(NSAL1290CMsg cMsg, NSAL1290SMsg sMsg) {

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);

        S21SsmEZDResult ssmResult = NSAL1290Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
            setValue(cMsg.mdlMtrLbCd_H, sMsg.A.no(0).mdlMtrLbCd_RG);
            setValue(cMsg.mtrIdxCd_H, sMsg.A.no(0).mtrIdxCd_RG);
            setValue(cMsg.mtrLbNm_H, sMsg.A.no(0).mtrLbNm_RG);
            copyToBSMsg(sMsg);
        } else {
            // No result
            if ("NSAL1290Scrn00_CMN_Submit".equals(cMsg.getScreenAplID())) {
                cMsg.setMessageInfo(ZZZM9005W);
                return;
            }
            cMsg.setMessageInfo(ZZZM9001E);
        }
    }

    private static void copyToBSMsg(NSAL1290SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            setValue(sMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i));
            EZDMsg.copy(sMsg.A.no(i), null, sMsg.B.no(i), null);
        }
        sMsg.B.setValidCount(sMsg.A.getValidCount());
    }

    /**
     * Copy CMsg To SMsg
     * @param cMsg NSAL1290CMsg
     * @param sMsg NSAL1290SMsg
     */
    public static void copyCMsgToSMsg(NSAL1290CMsg cMsg, NSAL1290SMsg sMsg) {

        NSAL1290_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL1290_ACMsg acMsg = (NSAL1290_ACMsg) acMsgArray.no(i);
            NSAL1290_ASMsg asMsg = (NSAL1290_ASMsg) sMsg.A.get(i);
            EZDMsg.copy(acMsg, null, asMsg, null);
            if (!hasValue(asMsg.actvFlg_A)) {
                setValue(asMsg.actvFlg_A, ZYPConstant.FLG_OFF_N);
            }
        }
    }

    /**
     * Copy SMsg To CMsg
     * @param cMsg NSAL1290CMsg
     * @param sMsg NSAL1290SMsg
     */
    public static void copySMsgToCMsg(NSAL1290CMsg cMsg, NSAL1290SMsg sMsg) {

        int num = 0;
        ZYPTableUtil.clear(cMsg.A);
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (num >= cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(num), null);
            num++;
        }
        cMsg.A.setValidCount(num);
    }

    /**
     * Get Log
     */
    private static Logger logger = Logger.getLogger(NSAL1290CommonLogic.class);

    /**
    * invokeMasterDataMessaging
    * @param isExistsModel boolean
    * @param ezUpTime String
    * @param bllgMtrLbCd String
    * @param mtrLbNm String
    */
    public static void invokeMasterDataMessaging(boolean isExistsModel, String ezUpTime, String bllgMtrLbCd, String mtrLbNm) {

        try {
            Event event = new Event();
            event.setReferencedEntity(NSAL1290Constant.REF_ENTITY_ITEM);

            if (isExistsModel) {
                event.setOperationType(OPERATIONTYPES.UPDATE);
            } else {
                event.setOperationType(OPERATIONTYPES.CREATE);
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
            a.setName(NSAL1290Constant.KEY_NAME_BLLG_MTR_LB_CD);
            a.setValue(bllgMtrLbCd);
            aa.getAttribute().add(a);
            kr.setAttributes(aa);

            ak.getKeyReference().add(kr);

            // Set Key Model Name
            kr = new KeyReference();
            kr.setType(REFERENCETYPES.MNEMONICAL);

            aa = new ArrayOfAttribute();
            a = new Attribute();
            a.setName(NSAL1290Constant.KEY_NAME_MTR_LB_NM);
            a.setValue(mtrLbNm);
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
}
