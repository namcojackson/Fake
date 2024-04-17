/********************************************************************************************
  * TYPE/PURPOSE      :Procedure to insert UGW Error emails for E307 interface  *
  * NAME              :                                                                                             *
  * INPUT PARAMETERS  : None                                                                          *
  * OUTPUT PARAMETERS : None                                                                        *
  * Assumptions       : None                                                                                   *
  * Pending Issues    : None                                                                                   *
  * Author                      Date          Version     Description                                     *
  * ----------------------------------------------------------------------------------------        *
  * Ajay Gurram        11-Nov-2016      1.0     INITIAL REVISION                           *
  * Ajay Gurram        06-Dec-2016      2.0     REVISION                           *
  ********************************************************************************************/
  
  
CREATE OR REPLACE PACKAGE CANON_E307_PRCMAIL_PKG
AS

   g_glbl_cmpy_cd       VARCHAR2 (10)
                           := canon_e307_constants.g_global_company_code;
   g_cancel_flg         VARCHAR2 (10) := canon_e307_constants.g_cancel_flg;
   g_ascc_source_name   VARCHAR2 (30) := 'ASCC';


   PROCEDURE INSERT_DATA (p_device_id           IN VARCHAR,
                          p_error_code          IN VARCHAR,
                          p_occurred_date       IN DATE,
                          p_error_description   IN VARCHAR);
                          
  PROCEDURE load_ugwerr_dtls;
  
   PROCEDURE DEBUG_MSG (l_program_name   IN VARCHAR2,
                        l_attribute1     IN VARCHAR2 DEFAULT NULL,
                        l_attribute2     IN VARCHAR2 DEFAULT NULL,
                        l_attribute3     IN VARCHAR2 DEFAULT NULL,
                        l_attribute4     IN VARCHAR2 DEFAULT NULL,
                        l_attribute5     IN VARCHAR2 DEFAULT NULL,
                        l_error_msg      IN VARCHAR2);
                        
   PROCEDURE INSERT_UGW_EMAIL_ARCH ( p_from_email IN VARCHAR2
			       ,p_subject IN VARCHAR2
			       ,p_body IN CLOB
			       ,p_message_id IN VARCHAR2
			       ,p_received_date IN DATE
			       ,o_id OUT NUMBER
			       ,o_status OUT VARCHAR2);
			       
	      PROCEDURE getserialinfo (
      p_in_serial                  VARCHAR2,
      p_contract_number   OUT      VARCHAR2,
      p_line_start_date   OUT      VARCHAR2,
      p_line_end_date     OUT      VARCHAR2,
      p_line_status       OUT      VARCHAR2,
	  p_category          OUT      VARCHAR2,
	  p_contact           OUT      VARCHAR2,
      p_install_party     OUT      VARCHAR2,
      p_instance_id       OUT      NUMBER, 
      p_contract_id       OUT      NUMBER, 	  
      p_category_id       OUT      NUMBER,
      p_party_id          OUT      NUMBER,    
      p_branch            OUT      VARCHAR2, 
      p_branch_desc       OUT      VARCHAR2,
      p_tag                  OUT      VARCHAR2  
      );	
      
   PROCEDURE get_open_sr_num (p_in_serial IN VARCHAR2, o_sr_num OUT VARCHAR2);      
                       
END CANON_E307_PRCMAIL_PKG;
/

CREATE OR REPLACE PACKAGE BODY CANON_E307_PRCMAIL_PKG
AS
   PROCEDURE INSERT_DATA (p_device_id           IN VARCHAR,
                          p_error_code          IN VARCHAR,
                          p_occurred_date       IN DATE,
                          p_error_description   IN VARCHAR)
   IS
      --l_user_id     VARCHAR2(100) := 'Q05528';
   BEGIN
   DBMS_OUTPUT.put_line('In INSERT_DATA');
      INSERT INTO canon_e307_smart_disp_tbl (serial_number,
                                             error_code,
                                             error_date,
                                             error_description,
                                             created_by,
                                             creation_date,
                                             last_updated_by,
                                             last_update_date)
           VALUES (p_device_id,
                   p_error_code,
                   p_occurred_date,
                   p_error_description,
                   -1,
                   SYSDATE,
                   -1,
                   SYSDATE);
                   
            
      
      COMMIT;
                               
   END INSERT_DATA;
   
 
   
PROCEDURE load_ugwerr_dtls IS

    v_active_flag        VARCHAR2(1)   := 'N';
    v_incident_number    VARCHAR2(30);
    v_call_type          VARCHAR2(30);
    v_call_status        VARCHAR2(30);
    v_incident_date      DATE;
    v_task_number        VARCHAR2(30);
    v_task_status        VARCHAR2(30);
    v_source_name        VARCHAR2(30);
    v_flag               VARCHAR2(1);

    l_o_user_id          NUMBER        := NULL;
    l_o_serial           VARCHAR2(30)  := NULL;
    l_o_tag              VARCHAR2(30)  := NULL;
    l_o_contract_number  VARCHAR2(30)  := NULL;
    l_o_contract_type    VARCHAR2(30)  := NULL;
    l_o_contract_status  VARCHAR2(30)  := NULL;
    l_o_hdr_start_date   DATE          := NULL;
    l_o_hdr_end_date     DATE          := NULL;
    l_o_contract_id      NUMBER        := NULL;
    l_line_start_date  VARCHAR2(30)          := NULL;
    l_line_end_date    VARCHAR2(30)          := NULL;
    l_o_line_start_date  DATE          := NULL;
    l_o_line_end_date    DATE          := NULL;
    l_o_line_status      VARCHAR2(30)  := NULL;
    l_o_topline_id       NUMBER        := NULL;
    l_o_subline_id       NUMBER        := NULL;
    l_o_category         VARCHAR2(30)  := NULL;
    l_o_category_id      NUMBER        := NULL;
    l_o_contact          VARCHAR2(2000):= NULL;
    l_o_equip_or_acc     VARCHAR2(30)  := NULL;
    l_o_install_party    VARCHAR2(360) := NULL;
    l_o_instance_id      NUMBER        := NULL;
    l_o_install_party_id NUMBER        := NULL;
    l_o_branch           VARCHAR2(150) := NULL;
    l_o_branch_desc      VARCHAR2(150) := NULL;
    l_ser_err_exists     VARCHAR2(1)   := NULL;
 /*   
    l_resp_time              VARCHAR2(100)  := NULL;
    l_vip_flag              VARCHAR2(30)  := NULL;
    l_mdl_dur              VARCHAR2(100)  := NULL;
    l_mach_tbl           CANON_E307_MAC_SER_TBL%ROWTYPE;
    l_ugw_tbl              CANON_E307_UGW_ERR_CODE_TBL%ROWTYPE;
    l_prob_tbl                CANON_E307_PROB_CODE_TBL%ROWTYPE;
    l_call_info_tbl           CANON_E307_CALL_INFO_TBL%ROWTYPE;
    l_contract_details_tbl    CANON_E307_CONTRACT_TBL%ROWTYPE;
    l_cust_loc_tbl            CANON_E307_CUST_LOC_TBL%ROWTYPE;
    l_bill_to_tbl             CANON_E307_CUST_LOC_TBL%ROWTYPE;
    l_notes_tbl               CANON_E307_DEBRIEF_NOTE_TBL%ROWTYPE;*/
    
    CURSOR cur_serial_active(p_serial_number VARCHAR2) IS
      SELECT 'Y'
      FROM svc_mach_mstr cii
      WHERE 1=1
      AND cii.ser_num = p_serial_number
      AND cii.SVC_MACH_USG_STS_CD = 30 -- At Customer
      AND cii.glbl_cmpy_cd = 'ADB'
      AND cii.ezcancelflag = '0'
      AND NVL(cii.EFF_THRU_DT,SYSDATE + 1) >= SYSDATE;

	  
	  
    CURSOR cur_imageware_links IS
      SELECT  msib1.MDSE_CD machine_item_id,
      ib.SVC_MACH_MSTR_PK,
       ib.MDSE_CD,
       e307.ROWID row_id,
       e307.ERROR_CODE,
       e307.error_date,
       e307.ERROR_DESCRIPTION
  FROM mdse msib1,
       mdse msib2,
       svc_mach_mstr ib,
       svc_mach_usg_sts mus,
       canon_e307_smart_disp_tbl e307,
       CANON_IMAGEWARE_LINKS_V ffv
 WHERE     ib.MDSE_CD = msib1.MDSE_CD
       AND NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
              TO_CHAR (SYSDATE, 'YYYYMMDD')
       AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
              TO_CHAR (SYSDATE, 'YYYYMMDD')
       AND mus.SVC_MACH_USG_STS_CD = ib.SVC_MACH_USG_STS_CD
       AND mus.SVC_MACH_USG_STS_NM = 'At Customer'
       AND mus.GLBL_CMPY_CD = 'ADB'
       AND e307.serial_number = ib.ser_num
       AND NVL (e307.processed_flag, 'E') = 'E'
       AND ffv.attribute1 = msib2.MDSE_CD
       AND ffv.flex_value = msib1.MDSE_CD
       AND msib1.GLBL_CMPY_CD = 'ADB'
       AND msib1.ezcancelflag = 0
       AND msib2.GLBL_CMPY_CD = 'ADB'
       AND msib2.ezcancelflag = 0;

    CURSOR cur_ugwerrors IS
      SELECT *
      FROM canon_e307_smart_disp_tbl
      WHERE processed_flag IS NULL;

    CURSOR cur_serial_err_exists(p_serial VARCHAR2,p_error_code VARCHAR2) IS
      SELECT 'Y'
      FROM canon_e307_smart_disp_dtls_tbl
      WHERE complete_flag = 'N'
      AND serial_number = p_serial
      AND error_code = p_error_code
      AND open_call IS NULL;

  BEGIN
DBMS_OUTPUT.put_line('In load_ugwerr_dtls');
    DECLARE
      v_machine_serial_no VARCHAR2(30);
    BEGIN

      FOR rec IN cur_imageware_links LOOP
        v_machine_serial_no := NULL;

        BEGIN
        -- If one machine has the config master pk same as main machine one, the machine is in same configuration.
          select config.svc_mach_mstr_pk
          INTO v_machine_serial_no
          from svc_mach_mstr ib, svc_config_mstr config
          where  ib.svc_config_mstr_pk = config.svc_config_mstr_pk
          and ib.svc_mach_mstr_pk = rec.svc_mach_mstr_pk;
        EXCEPTION
          WHEN OTHERS THEN
          debug_msg (
                  l_program_name   => 'load_ugwerr_dtls',
                  l_error_msg      => SUBSTR (SQLERRM, 1,2000));
            v_machine_serial_no := NULL;
        END;
       /*   SELECT cii.serial_number
          INTO v_machine_serial_no
          FROM csi_ii_relationships cir, svc_mach_mstr   cii
          WHERE NVL(cir.active_end_date, SYSDATE) >= SYSDATE
          AND cir.relationship_type_code = 'COMPONENT-OF'
          AND cir.subject_id = rec.instance_id
          AND cii.SVC_MACH_MSTR_PK = cir.object_id
          AND cii.MDSE_CD = rec.machine_item_id
            UNION
          SELECT cii.serial_number
          FROM csi_ii_relationships cir, svc_mach_mstr   cii
          WHERE NVL(cir.active_end_date, SYSDATE) >= SYSDATE
          AND cir.relationship_type_code = 'COMPONENT-OF'
          AND cir.object_id = rec.instance_id
          AND cii.SVC_MACH_MSTR_PK = cir.subject_id
          AND cii.MDSE_CD = rec.machine_item_id
          ; 
        EXCEPTION
          WHEN OTHERS THEN
            v_machine_serial_no := NULL;
        END; */
        IF (v_machine_serial_no IS NOT NULL) THEN
--DBMS_OUTPUT.PUT_LINE('IN LOOP');
          BEGIN
            INSERT INTO canon_e307_smart_disp_tbl
            (
              serial_number
              ,error_code
              ,error_date
              ,error_description
              ,created_by
              ,creation_date
              ,last_updated_by
              ,last_update_date
            )
            VALUES
            (
              v_machine_serial_no
              ,rec.error_code
              ,rec.error_date
              ,rec.error_description
              ,-1--fnd_global.user_id
              ,SYSDATE
              ,-1--fnd_global.user_id
              ,SYSDATE
            );

          EXCEPTION
            WHEN OTHERS THEN
                      debug_msg (
                  l_program_name   => 'load_ugwerr_dtls',
                  l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
              NULL;
          END;

          UPDATE canon_e307_smart_disp_tbl
          SET
            processed_flag = 'P'
            ,last_updated_by = -1--fnd_global.user_id
            ,last_update_date = SYSDATE
          WHERE rowid = rec.row_id;

        ELSE

          UPDATE canon_e307_smart_disp_tbl
          SET
            processed_flag = 'E'
            ,last_updated_by = -1--fnd_global.user_id
            ,last_update_date = SYSDATE
          WHERE rowid = rec.row_id;

        END IF;
      END LOOP;
      COMMIT;

    EXCEPTION
      WHEN OTHERS THEN
       -- fnd_file.put_line(fnd_file.log, 'Exception : Others : ' || SQLERRM);
          debug_msg (
                  l_program_name   => 'load_ugwerr_dtls',
                  l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
    END;

    FOR i IN cur_ugwerrors LOOP
      v_active_flag := 'N';
      BEGIN
        OPEN cur_serial_active(i.serial_number);
        FETCH cur_serial_active INTO v_active_flag;
        CLOSE cur_serial_active;
      EXCEPTION
        WHEN OTHERS THEN
                  debug_msg (
                  l_program_name   => 'load_ugwerr_dtls',
                  l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
          v_active_flag := 'N';
      END;
DBMS_OUTPUT.PUT_LINE('serial_number: '|| i.serial_number);
DBMS_OUTPUT.PUT_LINE('v_active_flag: '|| v_active_flag);
      IF(v_active_flag = 'Y') THEN

        v_incident_number    := NULL;
        v_call_type          := NULL;
        v_call_status        := NULL;
        v_incident_date      := NULL;
        v_task_number        := NULL;
        v_task_status        := NULL;
        v_source_name        := NULL;
        v_flag               := NULL;

        l_o_user_id          := NULL;
        l_o_serial           := i.serial_number;
        l_o_tag              := NULL;
        l_o_contract_number  := NULL;
        l_o_contract_type    := NULL;
        l_o_contract_status  := NULL;
        l_o_hdr_start_date   := NULL;
        l_o_hdr_end_date     := NULL;
        l_o_contract_id      := NULL;
        l_o_line_start_date  := NULL;
        l_o_line_end_date    := NULL;
        l_o_line_status      := NULL;
        l_o_category         := NULL;
        l_o_category_id      := NULL;
        l_o_contact          := NULL;
        l_o_equip_or_acc     := NULL;
        l_o_install_party    := NULL;
        l_o_instance_id      := NULL;
        l_o_install_party_id := NULL;
        l_o_branch           := NULL;
        l_o_branch_desc      := NULL;
        l_ser_err_exists     := NULL;

DBMS_OUTPUT.PUT_LINE('CALLING GET_OPEN_SR_NUM....'||l_o_serial);

BEGIN
      get_open_sr_num
        (
          p_in_serial    =>   l_o_serial
          ,o_sr_num     =>   v_incident_number
        ); 
        EXCEPTION
            WHEN OTHERS
            THEN
            DBMS_OUTPUT.PUT_LINE('error CALLING GET_OPEN_SR_NUM '|| SQLERRM);
                      debug_msg (
                  l_program_name   => 'load_ugwerr_dtls',
                  l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
         END;
        


DBMS_OUTPUT.PUT_LINE('CALLING getserialinfo....');
BEGIN
        getserialinfo
        (
           p_in_serial          =>l_o_serial
          ,p_contract_number =>l_o_contract_number
          ,p_line_start_date =>l_line_start_date
          ,p_line_end_date   =>l_line_end_date
          ,p_line_status     =>l_o_line_status
          ,p_category        =>l_o_category
          ,p_contact         =>l_o_contact
          ,p_install_party   =>l_o_install_party
          ,p_instance_id     =>l_o_instance_id
          ,p_contract_id     =>l_o_contract_id
          ,p_category_id     =>l_o_category_id
          ,p_party_id        =>l_o_install_party_id
          ,p_branch          =>l_o_branch
          ,p_branch_desc     =>l_o_branch_desc
          ,p_tag                    =>l_o_tag
        );
        EXCEPTION
            WHEN OTHERS
            THEN
            DBMS_OUTPUT.PUT_LINE('error CALLING getserialinfo '|| SQLERRM);
                      debug_msg (
                  l_program_name   => 'load_ugwerr_dtls',
                  l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
         END;
        

        
        IF(l_line_start_date is NOT NULL) THEN
        SELECT TO_DATE(l_line_start_date,'yyyymmdd') INTO l_o_line_start_date FROM dual;
        END IF;
        
        IF(l_line_end_date is NOT NULL) THEN
                SELECT TO_DATE(l_line_end_date,'yyyymmdd') INTO l_o_line_end_date FROM dual;
        END IF;
        
       /* DBMS_OUTPUT.PUT_LINE('l_o_contract_number: '|| l_o_contract_number);
        DBMS_OUTPUT.PUT_LINE('l_o_line_start_date: '|| l_o_line_start_date);
        DBMS_OUTPUT.PUT_LINE('l_o_line_end_date: '|| l_o_line_end_date);
        DBMS_OUTPUT.PUT_LINE('l_o_category: '|| l_o_category);
        DBMS_OUTPUT.PUT_LINE('l_o_contact: '|| l_o_contact);
        DBMS_OUTPUT.PUT_LINE('l_o_install_party: '|| l_o_install_party);
        DBMS_OUTPUT.PUT_LINE('l_o_instance_id: '|| l_o_instance_id);
        DBMS_OUTPUT.PUT_LINE('l_o_contract_id: '|| l_o_contract_id);
        DBMS_OUTPUT.PUT_LINE('l_o_category_id: '|| l_o_category_id);
        DBMS_OUTPUT.PUT_LINE('l_o_branch: '|| l_o_branch);
        DBMS_OUTPUT.PUT_LINE('l_o_branch_desc: '|| l_o_branch_desc);*/

      --  IF (v_flag = 'Y') THEN
       IF (v_incident_number is NOT NULL) THEN
       -- IF open call exists for the serial number
         -- INSERT into canon_e307_smart_disp_tbl
         --   complete_flag 'Y', last_updated_by = -1
         --   attribute1 = "Open Call Call# exists"
          INSERT INTO canon_e307_smart_disp_dtls_tbl
          (
            instance_id
            ,party_id
            ,party_name
            ,branch
            ,contact_person
            ,model
            ,serial_number
            ,error_code
            ,error_date
            ,error_description
            ,priority
            ,contract_number
            ,start_date
            ,end_date
            ,contract_id
            ,open_call
            ,org_id
            ,complete_flag
            ,last_update_date
            ,contract_status
            ,tag_number
            ,created_by
            ,creation_date
            ,last_updated_by
            ,branch_code_desc
            ,category_id
            ,comments
          ) VALUES
          (
            l_o_instance_id
            ,l_o_install_party_id
            ,l_o_install_party
            ,l_o_branch
            ,l_o_contact
            ,l_o_category
            ,i.serial_number
            ,i.error_code
            ,i.error_date
            ,i.error_description
            ,'Critical'
            ,l_o_contract_number
            ,l_o_line_start_date
            ,l_o_line_end_date
            ,l_o_contract_id
            ,NULL
            ,'ADB'-- Check 1fnd_profile.value('ORG_ID')
            ,'Y'
            ,SYSDATE
            ,l_o_line_status
            ,l_o_tag
            ,-1
            ,SYSDATE
            ,-1
            ,l_o_branch_desc
            ,l_o_category_id
            ,'Open Call exists. FSR# '||v_incident_number
          );
        ELSE
          OPEN cur_serial_err_exists(i.serial_number,i.error_code);

          FETCH cur_serial_err_exists INTO l_ser_err_exists;
          
          DBMS_OUTPUT.PUT_LINE('l_ser_err_exists: ' || l_ser_err_exists);

          IF (NVL(l_ser_err_exists,'N') = 'Y') THEN

       -- IF record exists for serial number, error code combination
         -- INSERT into canon_e307_smart_disp_tbl
         --   complete_flag 'Y', last_updated_by = -1
         --   attribute1 = "Duplicate Error Code reported"
            INSERT INTO canon_e307_smart_disp_dtls_tbl
            (
              instance_id
              ,party_id
              ,party_name
              ,branch
              ,contact_person
              ,model
              ,serial_number
              ,error_code
              ,error_date
              ,error_description
              ,priority
              ,contract_number
              ,start_date
              ,end_date
              ,contract_id
              ,open_call
              ,org_id
              ,complete_flag
              ,last_update_date
              ,contract_status
              ,tag_number
              ,created_by
              ,creation_date
              ,last_updated_by
              ,branch_code_desc
              ,category_id
              ,comments
            ) VALUES
            (
              l_o_instance_id
              ,l_o_install_party_id
              ,l_o_install_party
              ,l_o_branch
              ,l_o_contact
              ,l_o_category
              ,i.serial_number
              ,i.error_code
              ,i.error_date
              ,i.error_description
              ,'Critical'
              ,l_o_contract_number
              ,l_o_line_start_date
              ,l_o_line_end_date
              ,l_o_contract_id
              ,NULL
              ,'ADB'
              ,'Y'
              ,SYSDATE
              ,l_o_line_status
              ,l_o_tag
              ,-1
              ,SYSDATE
              ,-1
              ,l_o_branch_desc
              ,l_o_category_id
              ,'Serial# and Error Code combination already exists'
            );
          ELSE

         -- INSERT above data into canon_e307_smart_disp_dtls_tbl
         --   with complete flag NULL and who columns
         DBMS_OUTPUT.PUT_LINE('INSERTING....');
            INSERT INTO canon_e307_smart_disp_dtls_tbl
            (
              instance_id
              ,party_id
              ,party_name
              ,branch
              ,contact_person
              ,model
              ,serial_number
              ,error_code
              ,error_date
              ,error_description
              ,priority
              ,contract_number
              ,start_date
              ,end_date
              ,contract_id
              ,open_call
              ,org_id
              ,complete_flag
              ,last_update_date
              ,contract_status
              ,tag_number
              ,created_by
              ,creation_date
              ,last_updated_by
              ,branch_code_desc
              ,category_id
            ) VALUES
            (
              l_o_instance_id
              ,l_o_install_party_id
              ,l_o_install_party
              ,l_o_branch
              ,l_o_contact
              ,l_o_category
              ,i.serial_number
              ,i.error_code
              ,i.error_date
              ,i.error_description
              ,'Critical'
              ,l_o_contract_number
              ,l_o_line_start_date
              ,l_o_line_end_date
              ,l_o_contract_id
              ,NULL
              ,30
              ,'N'
              ,SYSDATE
              ,l_o_line_status
              ,l_o_tag
              ,-1
              ,SYSDATE
              ,-1
              ,l_o_branch_desc
              ,l_o_category_id
            );

          END IF;

          IF cur_serial_err_exists%ISOPEN THEN
            CLOSE cur_serial_err_exists;
          END IF;

        END IF; -- End Open call does not exist

       -- Update processed_flag = 'P' in canon_e307_smart_disp_tbl
        UPDATE canon_e307_smart_disp_tbl
        SET
          processed_flag = 'P'
          ,last_updated_by = -1
          ,last_update_date = SYSDATE
        WHERE serial_number = i.serial_number
        AND error_code = i.error_code
        AND error_date = i.error_date
        AND processed_flag IS NULL;

        COMMIT;
     -- Start: Added for ITG#210324
      ELSE

       -- Update processed_flag = 'P' in canon_e307_smart_disp_tbl
        UPDATE canon_e307_smart_disp_tbl
        SET
          processed_flag = 'P'
          ,last_updated_by = -1
          ,last_update_date = SYSDATE
          ,attribute1 = 'Instance is not active'
        WHERE serial_number = i.serial_number
        AND error_code = i.error_code
        AND error_date = i.error_date
        AND processed_flag IS NULL;

        COMMIT;

      END IF;
     -- End: Added for ITG#210324

    END LOOP;
  EXCEPTION
    WHEN OTHERS THEN
              debug_msg (
                  l_program_name   => 'load_ugwerr_dtls',
                  l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
      --p_retcode := 2;
      --p_errbuf := SQLERRM;
      ROLLBACK;
  END load_ugwerr_dtls;
   
   
   
      PROCEDURE debug_msg (l_program_name   IN VARCHAR2,
                        l_attribute1     IN VARCHAR2 DEFAULT NULL,
                        l_attribute2     IN VARCHAR2 DEFAULT NULL,
                        l_attribute3     IN VARCHAR2 DEFAULT NULL,
                        l_attribute4     IN VARCHAR2 DEFAULT NULL,
                        l_attribute5     IN VARCHAR2 DEFAULT NULL,
                        l_error_msg      IN VARCHAR2)
   IS
      PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN
      INSERT INTO CANON_E307_UGW_ERRORS
           VALUES (l_program_name,
                   SUBSTR (l_attribute1, 200),
                   l_attribute2,
                   l_attribute3,
                   l_attribute4,
                   l_attribute5,
                   l_error_msg,
                   SYSDATE);

      COMMIT;
   END debug_msg;
   
   
 /*******************************************************************************************
 Procedure Name: INSERT_UGW_EMAIL_STG
 Description: Procedure to insert data into CANON_E307_UGW_EMAIL_ARCH table
 Input Parameters: None
                               
 Output Parameters: p_out_status
 
*******************************************************************************************/ 

PROCEDURE INSERT_UGW_EMAIL_ARCH ( p_from_email IN VARCHAR2
			       ,p_subject IN VARCHAR2
			       ,p_body IN CLOB
			       ,p_message_id IN VARCHAR2
			       ,p_received_date IN DATE
			       ,o_id OUT NUMBER
			       ,o_status OUT VARCHAR2)
IS

lv_id  NUMBER;
  
BEGIN

	lv_id:=CANON_E307_UGW_EMAIL_S.NEXTVAL;
	
	INSERT INTO CANON_E307_UGW_EMAIL_ARCH
	(id,subject ,body ,from_email ,creation_date,process_flag,message_id,received_date)
	VALUES(lv_id,p_subject,p_body,p_from_email,SYSDATE,'N',p_message_id,p_received_date);
	COMMIT;
o_id:=	lv_id;
o_status:='S';

delete from CANON_E307_UGW_EMAIL_ARCH where CREATION_DATE < sysdate - 30;
	COMMIT;
	
EXCEPTION
WHEN OTHERS THEN
o_id:=NULL;
o_status:='E';
          debug_msg (
                  l_program_name   => 'load_ugwerr_dtls',
                  l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
   
END INSERT_UGW_EMAIL_ARCH;
   
   
   
   
   
   
PROCEDURE getserialinfo (
      p_in_serial                  VARCHAR2,
      p_contract_number   OUT      VARCHAR2,
      p_line_start_date   OUT      VARCHAR2,
      p_line_end_date     OUT      VARCHAR2,
      p_line_status       OUT      VARCHAR2,
	  p_category          OUT      VARCHAR2,
	  p_contact           OUT      VARCHAR2,
      p_install_party     OUT      VARCHAR2,
      p_instance_id       OUT      NUMBER, 
      p_contract_id       OUT      NUMBER, 	  
      p_category_id       OUT      NUMBER,
      p_party_id          OUT      NUMBER,    
      p_branch            OUT      VARCHAR2, 
      p_branch_desc       OUT      VARCHAR2,
      p_tag                  OUT      VARCHAR2
      )
   IS
    l_cur_loc_acct_num        VARCHAR2(100)        := NULL;
    l_contract_number          VARCHAR2(100)        := NULL;
    l_line_start_date            VARCHAR2(100)        := NULL;
    l_line_end_date             VARCHAR2(100)        := NULL;
    l_line_status                    VARCHAR2(100)        := NULL;
	l_category                      VARCHAR2(100)        := NULL;
	l_contact                         VARCHAR2(100)        := NULL;
    l_install_party                  VARCHAR2(100)        := NULL;
    l_instance_id                    NUMBER := NULL;
    l_contract_id                    NUMBER := NULL;	  
    l_category_id                   NUMBER := NULL;
    l_party_id                         NUMBER := NULL; 
    l_branch                            VARCHAR2(100)        := NULL;
    l_branch_desc                   VARCHAR2(100)        := NULL;  
    l_tag                   VARCHAR2(100)        := NULL;  

   BEGIN
       DBMS_OUTPUT.PUT_LINE('IN GETSERIAL:'||p_in_serial);
          BEGIN
         SELECT ib.cur_loc_acct_num, ib.svc_mach_mstr_pk,ib.cust_mach_ctrl_num
          INTO l_cur_loc_acct_num, l_instance_id,l_tag      
           FROM svc_mach_mstr ib
          WHERE     1 = 1
                AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ib.ser_num = p_in_serial
                AND ib.ezcancelflag = g_cancel_flg;        
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'getserialinfo',
                  l_attribute2     => 'Details not fund for serial: ',
                  l_attribute3     => p_in_serial,
                  l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
               END;      
               
               
               
          BEGIN
         SELECT cont_header.ds_contr_num , cont_header.ds_contr_pk,
                cont_detail.contr_eff_from_dt, cont_detail.contr_eff_thru_dt, cont_det_sts.ds_contr_dtl_sts_nm
                INTO
                l_contract_number, l_contract_id,
                l_line_start_date, l_line_end_date, l_line_status
           FROM svc_mach_mstr ib,
                ds_contr_dtl cont_detail,
                ds_contr cont_header,
                ds_contr_tp cont_type,
                ds_contr_sts cont_hdr_sts,
                ds_contr_dtl_sts cont_det_sts
          WHERE     1 = 1
                AND ib.svc_mach_mstr_pk = cont_detail.svc_mach_mstr_pk
                AND cont_detail.DS_CONTR_PK = cont_header.DS_CONTR_PK
                AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ib.ser_num = p_in_serial
                AND ib.ezcancelflag = g_cancel_flg
                AND cont_detail.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND cont_header.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND cont_header.DS_CONTR_TP_CD = cont_type.DS_CONTR_TP_CD
                AND cont_header.DS_CONTR_STS_CD =cont_hdr_sts.DS_CONTR_STS_CD
                AND cont_detail.DS_CONTR_DTL_STS_CD =cont_det_sts.DS_CONTR_DTL_STS_CD
                AND cont_type.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND cont_hdr_sts.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND cont_det_sts.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND cont_detail.ezcancelflag = g_cancel_flg
                AND cont_header.ezcancelflag = g_cancel_flg
                AND cont_type.ezcancelflag = g_cancel_flg
                AND cont_hdr_sts.ezcancelflag = g_cancel_flg
                AND cont_det_sts.ezcancelflag = g_cancel_flg;
                
         EXCEPTION
            WHEN OTHERS
            THEN
                   DBMS_OUTPUT.PUT_LINE('Contract Details not fund for :'||p_in_serial);
               debug_msg (
                  l_program_name   => 'getserialinfo',
                  l_attribute2     => 'Contract Details not fund for serial: ',
                  l_attribute3     => p_in_serial,
                  l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
               END;      
               
                --DBMS_OUTPUT.PUT_LINE('IN p_contract_number:'||p_contract_number);
     BEGIN           
       CANON_E307_CREATE_SR_PKG.get_equip_branch (p_in_serial, l_branch, l_branch_desc, l_instance_id);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'getserialinfo',
                  l_attribute2     => 'get_equip_branch ',
                  l_attribute3     => p_in_serial,
                  l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
               l_branch := NULL;
               l_branch_desc := NULL;
               END;
               
                --DBMS_OUTPUT.PUT_LINE('IN l_branch:'||l_branch);
        BEGIN
            SELECT cust_acct.sell_to_cust_cd, cust_acct.ds_acct_nm cust_name
              INTO l_party_id, l_install_party
              FROM sell_to_cust cust_acct
             WHERE  cust_acct.sell_to_cust_cd =l_cur_loc_acct_num
             AND cust_acct.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND NVL (cust_acct.eff_thru_dt,
                            TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (cust_acct.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD');
         EXCEPTION
            WHEN OTHERS
            THEN
             DBMS_OUTPUT.PUT_LINE(SUBSTR (SQLERRM, 2000));
               l_party_id := NULL;
               l_install_party := NULL;
               debug_msg (
                  l_program_name   => 'getserialinfo',
                  l_attribute3     => p_in_serial,
                  l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
         END;
         
           --DBMS_OUTPUT.PUT_LINE('IN l_party_id:'||l_party_id);
         BEGIN
SELECT cp.CTAC_PSN_FIRST_NM || ', ' || cp.CTAC_PSN_LAST_NM              
              INTO l_contact
              FROM SVC_MACH_CTAC_PSN smcp,
                   SVC_CTAC_TP sct,
                   DS_CTAC_PNT dcp,
                   CTAC_PSN cp
             WHERE     SVC_MACH_MSTR_PK =l_instance_id
                   AND sct.SVC_CTAC_TP_CD = smcp.SVC_CTAC_TP_CD
                   AND SVC_CTAC_TP_NM = 'Svc-Key Operator'
                   AND smcp.DS_CTAC_PNT_PK = dcp.DS_CTAC_PNT_PK
                   AND cp.CTAC_PSN_PK = dcp.CTAC_PSN_PK
                   AND dcp.DS_CTAC_PNT_ACTV_FLG = 'Y'
                   AND smcp.GLBL_CMPY_CD = 'ADB'
                   AND cp.GLBL_CMPY_CD = 'ADB'
                   AND dcp.GLBL_CMPY_CD = 'ADB'
                   AND smcp.GLBL_CMPY_CD = 'ADB'
                   AND cp.EZCANCELFLAG = 0
                   AND dcp.EZCANCELFLAG = 0
                   AND smcp.EZCANCELFLAG = 0
                   AND NVL (smcp.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (smcp.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'getserialinfo',
                  l_attribute2     => 'Contact Details not fund for serial: ',
                   l_attribute3     => p_in_serial,
                  l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
         END;

 BEGIN
            SELECT  mdl.t_mdl_nm,  mdl.t_mdl_id
              INTO l_category, l_category_id
           FROM svc_mach_mstr ib,
                      svc_config_mstr config,
                      mdl_nm mdl
          WHERE     1 = 1
                AND ib.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
                AND config.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND mdl.T_MDL_ID = config.MDL_ID
                AND ib.ser_num = p_in_serial;
                
      EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'getserialinfo',
                  l_attribute2     => 'Category Details not fund for serial: ',
                  l_attribute3     => p_in_serial,
                  l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
         END;

          
      p_contract_number      :=      l_contract_number;
      p_line_start_date         :=      l_line_start_date;
      p_line_end_date          :=      l_line_end_date;    
      p_line_status                :=      l_line_status;
	  p_category                 :=      l_category;
	  p_contact                    :=      l_contact;
      p_install_party            :=      l_install_party;
      p_instance_id             :=      l_instance_id; 
      p_contract_id             :=      l_contract_id; 	
      p_category_id           :=      l_category_id;
      p_party_id                 :=      l_party_id;    
      p_branch                    :=      l_branch; 
      p_branch_desc           :=      l_branch_desc; 
      p_tag                          :=     l_tag;
      
       /* DBMS_OUTPUT.PUT_LINE('p_contract_number: '|| p_contract_number);
        DBMS_OUTPUT.PUT_LINE('p_line_start_date: '|| p_line_start_date);
        DBMS_OUTPUT.PUT_LINE('p_line_end_date: '|| p_line_end_date);              
        DBMS_OUTPUT.PUT_LINE('p_line_status: '|| p_line_status);
        DBMS_OUTPUT.PUT_LINE('p_category: '|| p_category);
        DBMS_OUTPUT.PUT_LINE('p_contract_id: '|| p_contract_id);
        DBMS_OUTPUT.PUT_LINE('p_category_id: '|| p_category_id);
        DBMS_OUTPUT.PUT_LINE('p_instance_id: '|| p_instance_id);
        DBMS_OUTPUT.PUT_LINE('p_contract_id: '|| p_contract_id);     
        DBMS_OUTPUT.PUT_LINE('p_category_id: '|| p_category_id);
        DBMS_OUTPUT.PUT_LINE('p_party_id: '|| p_party_id);                                                 
        DBMS_OUTPUT.PUT_LINE('p_branch: '|| p_branch);
        DBMS_OUTPUT.PUT_LINE('p_branch_desc: '|| p_branch_desc);    
        DBMS_OUTPUT.PUT_LINE('p_tag: '|| p_tag);    */
          
    EXCEPTION
      WHEN OTHERS
      THEN
      DBMS_OUTPUT.PUT_LINE('IN EXCEPTION:'||SQLERRM);   
      debug_msg (
                  l_program_name   => 'getserialinfo',
                  l_attribute3     => p_in_serial,
                  l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
                  
      p_contract_number      :=      NULL;
      p_line_start_date    :=      NULL;
      p_line_end_date      :=      NULL;
      p_line_status        :=      NULL;
	  p_category           :=      NULL;
	  p_contact            :=      NULL;
      p_install_party      :=      NULL;
      p_instance_id        :=      NULL; 
      p_contract_id        :=      NULL; 	  
      p_category_id        :=      NULL;
      p_party_id           :=      NULL;    
      p_branch             :=      NULL; 
      p_branch_desc        :=      NULL; 
      p_tag        :=      NULL; 
   END getserialinfo;
   
   
   PROCEDURE get_open_sr_num (p_in_serial IN VARCHAR2, o_sr_num OUT VARCHAR2)
   IS
   BEGIN
      SELECT MAX (fsr_num)
        INTO o_sr_num
        FROM SVC_TASK st, SVC_TASK_STS sts
       WHERE     st.ser_num = p_in_serial          
             AND st.SVC_TASK_STS_CD = sts.SVC_TASK_STS_CD
             AND UPPER (SVC_TASK_STS_NM) NOT IN ('CANCELLED',
                                                 'COMPLETED',
                                                 'CUSTOMER CANCELLED',
                                                 'CLOSED');
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_OPEN_SR_NUM',
                    l_attribute3     => p_in_serial,
                    l_error_msg      => SUBSTR (SQLERRM, 1, 2000));
         o_sr_num := NULL;
   END GET_OPEN_SR_NUM;

   

END CANON_E307_PRCMAIL_PKG;
/

SHOW ERRORS