/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0240.common;

import static business.blap.NMAL0240.constant.NMAL0240Constant.COMPONENT;
import static business.blap.NMAL0240.constant.NMAL0240Constant.NMAM8111E;
import static business.blap.NMAL0240.constant.NMAL0240Constant.REF_ENTITY_ITEM;
import static business.blap.NMAL0240.constant.NMAL0240Constant.MODE_CD_KEY_NAME;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import parts.common.EZDMsg;
import business.blap.NMAL0240.NMAL0240CMsg;
import business.blap.NMAL0240.NMAL0240Query;
import business.blap.NMAL0240.NMAL0240SMsg;
import business.blap.NMAL0240.NMAL0240_ACMsg;
import business.blap.NMAL0240.NMAL0240_ASMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
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
 * NMAL0240CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Fujitsu         C.Tanaka        Create          CSA
 * 2016/06/16   SRAA            K.Aratani       Update          QC#10313
 * 2016/06/23   SRAA            K.Aratani       Update          QC#10653
 * 
 *</pre>
 */
public class NMAL0240CommonLogic {

    private static Logger logger = Logger.getLogger(NMAL0240CommonLogic.class);
    /**
     * Reload Current Revision
     * @param bizMsg NMAL0240CMsg
     * @param glblMsg NMAL0240SMsg
     */
    public static void reloadCurrentRevision(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {
        bizMsg.A.clear();

        NMAL0240_ASMsg aSMsg = null;
        NMAL0240_ACMsg aCMsg = null;
        int cnt = 0;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            aSMsg = glblMsg.A.no(i);
            aCMsg = bizMsg.A.no(cnt);
            if (ZYPConstant.FLG_ON_Y.equals(aSMsg.xxDplyCtrlFlg_A1.getValue())) {
                EZDMsg.copy(aSMsg, null, aCMsg, null);
                cnt++;
            } else if (!ZYPCommonFunc.hasValue(aSMsg.xxNum_A2)) {
                EZDMsg.copy(aSMsg, null, aCMsg, null);
                cnt++;
            }
        }
        bizMsg.A.setValidCount(cnt);
    }

    /**
     * Get MDSE
     * @param bizMsg NMAL0240CMsg
     * @param glblMsg NMAL0240SMsg
     * @param idx integer
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean getMdse(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg, int idx) {

        NMAL0240_ACMsg aCMsg = bizMsg.A.no(idx);
        int row = aCMsg.xxRowNum_A1.getValueInt();
        NMAL0240_ASMsg aSMsg = glblMsg.A.no(row);

        String mdseCd = bizMsg.A.no(idx).mdseCd_A1.getValue();
        S21SsmEZDResult ssmResult = NMAL0240Query.getInstance().getMdse(mdseCd);
        if (ssmResult.isCodeNormal()) {
            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(aSMsg.mdseCd_A1, mdseCd);
            ZYPEZDItemValueSetter.setValue(aSMsg.mdseDescShortTxt_A1, (String) resultMap.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(aSMsg.coaProjDescTxt_A1, (String) resultMap.get("COA_PROJ_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(aSMsg.coaProdCd_A1, (String) resultMap.get("COA_PROD_CD"));
        } else {
            aCMsg.mdseCd_A1.setErrorInfo(1, NMAM8111E, new String[] {COMPONENT });
            return false;
        }
        return true;
    }

    /**
     * Reset Sequence
     * @param bizMsg NMAL0240CMsg
     * @param glblMsg NMAL0240SMsg
     */
    public static void resetSequence(NMAL0240CMsg bizMsg, NMAL0240SMsg glblMsg) {
        int seq1 = 1;
        int seq2 = 1;
        int idx = 0;
        NMAL0240_ASMsg aSMsg = null;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            aSMsg = glblMsg.A.no(i);
            aSMsg.xxRowNum_A1.setValue(idx);
            aSMsg.xxNum_A1.setValue(seq1);
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).xxNum_A2)) {
                idx++;
                aSMsg.xxRowNum_A1.setValue(idx);
                aSMsg.xxNum_A1.setValue(seq2);
                seq2++;
            }
            seq1++;
            idx++;
        }
    }
    @SuppressWarnings("unchecked")
    public static String getCusaMdseRgtnSts(String glblCmpyCd, String mdseCd) {
    	String ret = null;
        S21SsmEZDResult r = NMAL0240Query.getInstance().getCusaMdseRgtnSts(glblCmpyCd, mdseCd);
        if (r.isCodeNormal()) {
            Map<String, Object> resultMap = (Map<String, Object>) r.getResultObject();
            if (resultMap != null && resultMap.get("RGTN_STS_CD") != null) {
            	ret = (String) resultMap.get("RGTN_STS_CD");
            }
        }
        return ret;
    }
    
    //QC#10313
    public static boolean existsCmpsn(String glblCmpyCd, String mdseCd) {
    	boolean ret = true;
        S21SsmEZDResult r = NMAL0240Query.getInstance().getCmpsn(glblCmpyCd, mdseCd);
        if (!r.isCodeNormal()) {
        	ret = false;
        }
        return ret;
    }
    
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
