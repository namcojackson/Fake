CREATE OR REPLACE PACKAGE CANON_E479_CONVERSION_UTIL_PKG
AS
   /************************************************************************************************
       *                                                                                          *
       * File NAME       : CANON_E479_CONVERSION_UTIL_PKG.sql                                     *
       * DESCRIPTION     : All procedures related to conversion                                   *
    *                                                                                          *
       * REVISION HISTORY:                                                                        *
       *                                                                                          *
       * DEVELOPER         DATE           DESCRIPTION                                             *
       * -------------     -----------    ---------------------------                             *
       * Lakshmi Gopalsami 4/17/2017      Initial Creation
     *************************************************************************************************/

   TYPE RESULT_CURSOR IS REF CURSOR;

   l_package_name   VARCHAR2 (30) := 'CANON_E479_CONVERSION_UTIL_PKG';

   
   PROCEDURE get_biller_detail (p_header_id            IN       NUMBER,
                                 p_customer_group       IN       VARCHAR2, 
								 p_customer_name        IN       VARCHAR2,
								 p_bill_to_loc          IN       VARCHAR2,
                                 x_biller_last_name          OUT VARCHAR2,
                                 x_biller_first_name         OUT VARCHAR2,
                                 x_biller_phone_number       OUT VARCHAR2,
                                 x_biller_fax_number         OUT VARCHAR2,
                                 x_biller_email_address      OUT VARCHAR2,
                                 x_emp_number                OUT VARCHAR2);

   PROCEDURE get_default_biller_detail (
      x_biller_last_name       OUT VARCHAR2,
      x_biller_first_name      OUT VARCHAR2,
      x_biller_phone_number    OUT VARCHAR2,
      x_biller_fax_number      OUT VARCHAR2,
      x_biller_email_address   OUT VARCHAR2,
      x_emp_number             OUT VARCHAR2);


   PROCEDURE get_biller_detail_for_party (
      p_party_name             IN     VARCHAR2,
      x_biller_last_name          OUT VARCHAR2,
      x_biller_first_name         OUT VARCHAR2,
      x_biller_phone_number       OUT VARCHAR2,
      x_biller_fax_number         OUT VARCHAR2,
      x_biller_email_address      OUT VARCHAR2,
      x_emp_number                OUT VARCHAR2);



   PROCEDURE get_biller_detail_for_profile (
      p_profile_name           IN     VARCHAR2,
      x_biller_last_name          OUT VARCHAR2,
      x_biller_first_name         OUT VARCHAR2,
      x_biller_phone_number       OUT VARCHAR2,
      x_biller_fax_number         OUT VARCHAR2,
      x_biller_email_address      OUT VARCHAR2,
      x_emp_number                OUT VARCHAR2);

   PROCEDURE get_biller_detail_for_location (
      p_location               IN     VARCHAR2,
      x_biller_last_name          OUT VARCHAR2,
      x_biller_first_name         OUT VARCHAR2,
      x_biller_phone_number       OUT VARCHAR2,
      x_biller_fax_number         OUT VARCHAR2,
      x_biller_email_address      OUT VARCHAR2,
      x_emp_number                OUT VARCHAR2);
	  
   FUNCTION get_S21_location_number(
      p_customer_name           IN     VARCHAR2,
      p_orcl_bill_to_use_loc    IN     VARCHAR2)
	RETURN VARCHAR2;
	 
   PROCEDURE log_error (p_script_name      IN VARCHAR2,
                        p_key1                    IN VARCHAR2,
                        p_key2                    IN VARCHAR2,
                        p_key3                    IN VARCHAR2,
                        p_key4                    IN VARCHAR2,
                        p_key5                    IN VARCHAR2,
                        p_key6                    IN VARCHAR2,
                        p_error_message           IN LONG);

						
END CANON_E479_CONVERSION_UTIL_PKG;
/

SHOW ERRORS;

CREATE OR REPLACE PACKAGE BODY CANON_E479_CONVERSION_UTIL_PKG
AS
   /************************************************************************************************
      *                                                                                          *
      * File NAME       : CANON_E479_CONVERSION_UTIL_PKG.sql                                     *
      * DESCRIPTION     : All procedures related to conversion                                   *
   *                                                                                          *
      * REVISION HISTORY:                                                                        *
      *                                                                                          *
      * DEVELOPER         DATE           DESCRIPTION                                             *
      * -------------     -----------    ---------------------------                             *
      * Lakshmi Gopalsami 4/17/2017     Initial Creation
    *************************************************************************************************/
   
   PROCEDURE get_biller_detail (p_header_id            IN       NUMBER,
                                 p_customer_group       IN       VARCHAR2, 
								 p_customer_name        IN       VARCHAR2,
								 p_bill_to_loc          IN       VARCHAR2,
                                 x_biller_last_name          OUT VARCHAR2,
                                 x_biller_first_name         OUT VARCHAR2,
                                 x_biller_phone_number       OUT VARCHAR2,
                                 x_biller_fax_number         OUT VARCHAR2,
                                 x_biller_email_address      OUT VARCHAR2,
                                 x_emp_number                OUT VARCHAR2)
   AS
      l_account_name     VARCHAR2 (200);
      l_location         VARCHAR2 (50);
      l_party_name       VARCHAR2 (200);
      l_procedure_name   VARCHAR2 (200) := 'get_biller_detail';
   BEGIN
      /*SELECT account_name, party_name, bill_to_location
        INTO l_account_name, l_party_name, l_location
        FROM XXIGI.IGIAR_CUSTINV_WEB_HEADER
       WHERE header_id = p_header_id;
	   */
       l_account_name := p_customer_group;
	   l_party_name := p_customer_name;
	   l_location := p_bill_to_loc;

      IF (l_account_name IS NOT NULL)
      THEN
         get_biller_detail_for_profile (l_account_name,
                                        x_biller_last_name,
                                        x_biller_first_name,
                                        x_biller_phone_number,
                                        x_biller_fax_number,
                                        x_biller_email_address,
                                        x_emp_number);
      ELSIF (l_party_name IS NOT NULL AND NVL (l_location, -1) < 0)
      THEN
         get_biller_detail_for_party (l_party_name,
                                      x_biller_last_name,
                                      x_biller_first_name,
                                      x_biller_phone_number,
                                      x_biller_fax_number,
                                      x_biller_email_address,
                                      x_emp_number);
      ELSIF (l_location IS NOT NULL AND NVL (l_location, -1) > 0)
      THEN
         get_biller_detail_for_location (l_location,
                                         x_biller_last_name,
                                         x_biller_first_name,
                                         x_biller_phone_number,
                                         x_biller_fax_number,
                                         x_biller_email_address,
                                         x_emp_number);
      ELSE
         get_default_biller_detail (x_biller_last_name,
                                    x_biller_first_name,
                                    x_biller_phone_number,
                                    x_biller_fax_number,
                                    x_biller_email_address,
                                    x_emp_number);
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         get_default_biller_detail (x_biller_last_name,
                                    x_biller_first_name,
                                    x_biller_phone_number,
                                    x_biller_fax_number,
                                    x_biller_email_address,
                                    x_emp_number);


         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  l_account_name,
                                                  l_party_name,
                                                  l_location,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END get_biller_detail;


   PROCEDURE get_default_biller_detail (
      x_biller_last_name       OUT VARCHAR2,
      x_biller_first_name      OUT VARCHAR2,
      x_biller_phone_number    OUT VARCHAR2,
      x_biller_fax_number      OUT VARCHAR2,
      x_biller_email_address   OUT VARCHAR2,
      x_emp_number             OUT VARCHAR2)
   AS
      l_procedure_name   VARCHAR2 (200) := 'get_default_biller_detail';
   BEGIN
      BEGIN
         /* Replace with S21 code table
		 SELECT ' ' LAST_NAME,
                FLEX_VALUE_MEANING FirstName,
                ' ' emp_number,
                ATTRIBUTE2 Contact_phone,
                ATTRIBUTE3 fax_phone,
                ATTRIBUTE1 email_address
           INTO x_biller_last_name,
                x_biller_first_name,
                x_emp_number,
                x_biller_phone_number,
                x_biller_fax_number,
                x_biller_email_address
           FROM fnd_flex_values_vl@CANMTH.CUSA.CANON.COM
          WHERE     flex_value_set_id IN
                       (SELECT flex_value_set_id
                          FROM fnd_flex_value_sets@CANMTH.CUSA.CANON.COM
                         WHERE flex_value_set_name =
                                  'XXIGI_CB_UNASSIGNED_BILLERS')
                AND enabled_flag = 'Y'
                AND SYSDATE BETWEEN NVL (start_date_active, SYSDATE)
                                AND NVL (end_date_active, SYSDATE); */
          SELECT 
		        last_name last_name,
                first_name firstname,
                ' ' emp_number,
                phone contact_phone,
                fax fax_phone,
                email_address email_address
           INTO x_biller_last_name,
                x_biller_first_name,
                x_emp_number,
                x_biller_phone_number,
                x_biller_fax_number,
                x_biller_email_address
           FROM CANON_E479_BILLER_LIST_V
			WHERE ACTIVE_FLAG ='Y'
			AND ROWNUM=1;		   
      EXCEPTION
         WHEN OTHERS
         THEN
            CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                     l_procedure_name,
                                                     'SQL',
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     SQLERRM);

            BEGIN
               SELECT billerDetail.BILLER_FIRST_NAME,
                      billerDetail.BILLER_LAST_NAME,
                      ' ' emp_number,
                      billerDetail.BILLER_CONTACT_NO,
                      billerDetail.BILLER_FAX_NO,
                      billerDetail.BILLER_EMAIL_ID
                 INTO x_biller_last_name,
                      x_biller_first_name,
                      x_emp_number,
                      x_biller_phone_number,
                      x_biller_fax_number,
                      x_biller_email_address
                 FROM canon_e490_cust_prof_main_tbl@CANMTH.CUSA.CANON.COM mainProfile,
                      CANON_E490_BILLER_DETAILS_TBL@CANMTH.CUSA.CANON.COM billerDetail
                WHERE     mainProfile.profile_id = billerDetail.profile_id
                      AND TRUNC (SYSDATE) BETWEEN TRUNC (
                                                     NVL (
                                                        mainProfile.start_date_active,
                                                        SYSDATE))
                                              AND TRUNC (
                                                     NVL (
                                                        mainProfile.end_date_active,
                                                        SYSDATE))
                      AND mainProfile.profile_name = 'DEFAULT_PROFILE'
                      AND ROWNUM < 2;
            EXCEPTION
               WHEN OTHERS
               THEN
                  x_biller_last_name := NULL;
                  x_biller_first_name := NULL;
                  x_biller_phone_number := NULL;
                  x_biller_fax_number := NULL;
                  x_biller_email_address := NULL;
                  x_emp_number := NULL;

                  CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                           l_procedure_name,
                                                           'SQL',
                                                           NULL,
                                                           NULL,
                                                           NULL,
                                                           NULL,
                                                           NULL,
                                                           NULL,
                                                           SQLERRM);
            END;
      END;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_biller_last_name := NULL;
         x_biller_first_name := NULL;
         x_biller_phone_number := NULL;
         x_biller_fax_number := NULL;
         x_biller_email_address := NULL;
         x_emp_number := NULL;



         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END get_default_biller_detail;


   PROCEDURE get_biller_detail_for_party (
      p_party_name             IN     VARCHAR2,
      x_biller_last_name          OUT VARCHAR2,
      x_biller_first_name         OUT VARCHAR2,
      x_biller_phone_number       OUT VARCHAR2,
      x_biller_fax_number         OUT VARCHAR2,
      x_biller_email_address      OUT VARCHAR2,
      x_emp_number                OUT VARCHAR2)
   AS
      l_procedure_name   VARCHAR2 (200) := 'get_biller_detail_for_party';
   BEGIN
      BEGIN
         SELECT billerDetail.BILLER_FIRST_NAME,
                billerDetail.BILLER_LAST_NAME,
                ' ' emp_number,
                billerDetail.BILLER_CONTACT_NO,
                billerDetail.BILLER_FAX_NO,
                billerDetail.BILLER_EMAIL_ID
           INTO x_biller_last_name,
                x_biller_first_name,
                x_emp_number,
                x_biller_phone_number,
                x_biller_fax_number,
                x_biller_email_address
           FROM canon_e490_cust_prof_main_tbl@CANMTH.CUSA.CANON.COM mainProfile,
                canon_e490_cust_prof_asign_tbl@CANMTH.CUSA.CANON.COM profAssign,
                CANON_E490_BILLER_DETAILS_TBL@CANMTH.CUSA.CANON.COM billerDetail,
                hz_parties@CANMTH.CUSA.CANON.COM hp
          WHERE     mainProfile.profile_id = profAssign.profile_id
                AND profAssign.profile_Id = billerDetail.profile_id
                AND TRUNC (SYSDATE) BETWEEN TRUNC (
                                               NVL (
                                                  mainProfile.start_date_active,
                                                  SYSDATE))
                                        AND TRUNC (
                                               NVL (
                                                  mainProfile.end_date_active,
                                                  SYSDATE))
                AND TRUNC (SYSDATE) BETWEEN TRUNC (
                                               NVL (profAssign.start_date,
                                                    SYSDATE))
                                        AND TRUNC (
                                               NVL (profAssign.end_date,
                                                    SYSDATE))
                AND profAssign.party_id = hp.party_id
                AND hp.party_name = p_party_name
                AND billerDetail.BILLER_EMAIL_ID IS NOT NULL
                AND ROWNUM < 2;
      EXCEPTION
         WHEN OTHERS
         THEN
            get_default_biller_detail (x_biller_last_name,
                                       x_biller_first_name,
                                       x_biller_phone_number,
                                       x_biller_fax_number,
                                       x_biller_email_address,
                                       x_emp_number);

            CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                     l_procedure_name,
                                                     'SQL',
                                                     p_party_name,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     SQLERRM);
      END;
   END get_biller_detail_for_party;


   PROCEDURE get_biller_detail_for_profile (
      p_profile_name           IN     VARCHAR2,
      x_biller_last_name          OUT VARCHAR2,
      x_biller_first_name         OUT VARCHAR2,
      x_biller_phone_number       OUT VARCHAR2,
      x_biller_fax_number         OUT VARCHAR2,
      x_biller_email_address      OUT VARCHAR2,
      x_emp_number                OUT VARCHAR2)
   AS
      l_account_name     VARCHAR2 (200);
      l_location         VARCHAR2 (50);
      l_party_name       VARCHAR2 (200);

      l_procedure_name   VARCHAR2 (200) := 'get_biller_detail_for_profile';
   BEGIN
      BEGIN
         SELECT billerDetail.BILLER_FIRST_NAME,
                billerDetail.BILLER_LAST_NAME,
                ' ' emp_number,
                billerDetail.BILLER_CONTACT_NO,
                billerDetail.BILLER_FAX_NO,
                billerDetail.BILLER_EMAIL_ID
           INTO x_biller_last_name,
                x_biller_first_name,
                x_emp_number,
                x_biller_phone_number,
                x_biller_fax_number,
                x_biller_email_address
           FROM canon_e490_cust_prof_main_tbl@CANMTH.CUSA.CANON.COM mainProfile,
                CANON_E490_BILLER_DETAILS_TBL@CANMTH.CUSA.CANON.COM billerDetail
          WHERE     mainProfile.profile_id = billerDetail.profile_id
                AND TRUNC (SYSDATE) BETWEEN TRUNC (
                                               NVL (
                                                  mainProfile.start_date_active,
                                                  SYSDATE))
                                        AND TRUNC (
                                               NVL (
                                                  mainProfile.end_date_active,
                                                  SYSDATE))
                AND mainProfile.profile_name = p_profile_name;
      EXCEPTION
         WHEN TOO_MANY_ROWS
         THEN
            SELECT billerDetail.BILLER_FIRST_NAME,
                   billerDetail.BILLER_LAST_NAME,
                   ' ' emp_number,
                   billerDetail.BILLER_CONTACT_NO,
                   billerDetail.BILLER_FAX_NO,
                   billerDetail.BILLER_EMAIL_ID
              INTO x_biller_last_name,
                   x_biller_first_name,
                   x_emp_number,
                   x_biller_phone_number,
                   x_biller_fax_number,
                   x_biller_email_address
              FROM canon_e490_cust_prof_main_tbl@CANMTH.CUSA.CANON.COM mainProfile,
                   CANON_E490_BILLER_DETAILS_TBL@CANMTH.CUSA.CANON.COM billerDetail
             WHERE     mainProfile.profile_id = billerDetail.profile_id
                   AND TRUNC (SYSDATE) BETWEEN TRUNC (
                                                  NVL (
                                                     mainProfile.start_date_active,
                                                     SYSDATE))
                                           AND TRUNC (
                                                  NVL (
                                                     mainProfile.start_date_active,
                                                     SYSDATE))
                   AND mainProfile.profile_name = p_profile_name
                   AND ROWNUM < 2;

            CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                     l_procedure_name,
                                                     'SQL',
                                                     p_profile_name,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     SQLERRM);
         WHEN NO_DATA_FOUND
         THEN
            get_default_biller_detail (x_biller_last_name,
                                       x_biller_first_name,
                                       x_biller_phone_number,
                                       x_biller_fax_number,
                                       x_biller_email_address,
                                       x_emp_number);

            CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                     l_procedure_name,
                                                     'SQL',
                                                     p_profile_name,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     SQLERRM);
         WHEN OTHERS
         THEN
            get_default_biller_detail (x_biller_last_name,
                                       x_biller_first_name,
                                       x_biller_phone_number,
                                       x_biller_fax_number,
                                       x_biller_email_address,
                                       x_emp_number);

            CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                     l_procedure_name,
                                                     'SQL',
                                                     p_profile_name,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     SQLERRM);
      END;
   EXCEPTION
      WHEN OTHERS
      THEN
         get_default_biller_detail (x_biller_last_name,
                                    x_biller_first_name,
                                    x_biller_phone_number,
                                    x_biller_fax_number,
                                    x_biller_email_address,
                                    x_emp_number);

         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_profile_name,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END get_biller_detail_for_profile;


   PROCEDURE get_biller_detail_for_location (
      p_location               IN     VARCHAR2,
      x_biller_last_name          OUT VARCHAR2,
      x_biller_first_name         OUT VARCHAR2,
      x_biller_phone_number       OUT VARCHAR2,
      x_biller_fax_number         OUT VARCHAR2,
      x_biller_email_address      OUT VARCHAR2,
      x_emp_number                OUT VARCHAR2)
   AS
      l_procedure_name   VARCHAR2 (200) := 'get_biller_detail_for_location';
   BEGIN
      SELECT billerDetail.BILLER_FIRST_NAME,
             billerDetail.BILLER_LAST_NAME,
             ' ' emp_number,
             billerDetail.BILLER_CONTACT_NO,
             billerDetail.BILLER_FAX_NO,
             billerDetail.BILLER_EMAIL_ID
        INTO x_biller_last_name,
             x_biller_first_name,
             x_emp_number,
             x_biller_phone_number,
             x_biller_fax_number,
             x_biller_email_address
        FROM canon_e490_cust_prof_main_tbl@CANMTH.CUSA.CANON.COM mainProfile,
             canon_e490_cust_prof_asign_tbl@CANMTH.CUSA.CANON.COM profAssign,
             CANON_E490_BILLER_DETAILS_TBL@CANMTH.CUSA.CANON.COM billerDetail,
             hz_cust_site_uses_all@CANMTH.CUSA.CANON.COM hcsua,
             hz_cust_acct_sites_all@CANMTH.CUSA.CANON.COM hcasa
       WHERE     mainProfile.profile_id = profAssign.profile_id
             AND profAssign.profile_Id = billerDetail.profile_id
             AND TRUNC (SYSDATE) BETWEEN TRUNC (
                                            NVL (
                                               mainProfile.start_date_active,
                                               SYSDATE))
                                     AND TRUNC (
                                            NVL (mainProfile.end_date_active,
                                                 SYSDATE))
             AND TRUNC (SYSDATE) BETWEEN TRUNC (
                                            NVL (profAssign.start_date,
                                                 SYSDATE))
                                     AND TRUNC (
                                            NVL (profAssign.end_date,
                                                 SYSDATE))
             AND billerDetail.CUST_ACCT_SITE_ID = hcasa.CUST_ACCT_SITE_ID
             AND hcasa.CUST_ACCT_SITE_ID = hcsua.CUST_ACCT_SITE_ID
             AND hcsua.location = p_location
             AND ROWNUM < 2;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         BEGIN
            SELECT billerDetail.BILLER_FIRST_NAME,
                   billerDetail.BILLER_LAST_NAME,
                   ' ' emp_number,
                   billerDetail.BILLER_CONTACT_NO,
                   billerDetail.BILLER_FAX_NO,
                   billerDetail.BILLER_EMAIL_ID
              INTO x_biller_last_name,
                   x_biller_first_name,
                   x_emp_number,
                   x_biller_phone_number,
                   x_biller_fax_number,
                   x_biller_email_address
              FROM canon_e490_cust_prof_main_tbl@CANMTH.CUSA.CANON.COM mainProfile,
                   canon_e490_cust_prof_asign_tbl@CANMTH.CUSA.CANON.COM profAssign,
                   CANON_E490_BILLER_DETAILS_TBL@CANMTH.CUSA.CANON.COM billerDetail,
                   hz_cust_site_uses_all@CANMTH.CUSA.CANON.COM hcsua,
                   hz_cust_acct_sites_all@CANMTH.CUSA.CANON.COM hcasa,
                   hz_cust_accounts@CANMTH.CUSA.CANON.COM hca,
                   hz_parties@CANMTH.CUSA.CANON.COM hp
             WHERE     mainProfile.profile_id = profAssign.profile_id
                   AND profAssign.profile_Id = billerDetail.profile_id
                   AND TRUNC (SYSDATE) BETWEEN TRUNC (
                                                  NVL (
                                                     mainProfile.start_date_active,
                                                     SYSDATE))
                                           AND TRUNC (
                                                  NVL (
                                                     mainProfile.end_date_active,
                                                     SYSDATE))
                   AND TRUNC (SYSDATE) BETWEEN TRUNC (
                                                  NVL (profAssign.start_date,
                                                       SYSDATE))
                                           AND TRUNC (
                                                  NVL (profAssign.end_date,
                                                       SYSDATE))
                   AND billerDetail.CUST_ACCT_SITE_ID =
                          hcasa.CUST_ACCT_SITE_ID
                   AND hcasa.CUST_ACCT_SITE_ID = hcsua.CUST_ACCT_SITE_ID
                   AND hcsua.location = p_location
                   AND hcasa.cust_account_id = hca.cust_account_id
                   AND hca.party_id = hp.party_id
                   AND hp.party_id = profAssign.party_id
                   AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               get_default_biller_detail (x_biller_last_name,
                                          x_biller_first_name,
                                          x_biller_phone_number,
                                          x_biller_fax_number,
                                          x_biller_email_address,
                                          x_emp_number);



               CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                        l_procedure_name,
                                                        'SQL',
                                                        p_location,
                                                        NULL,
                                                        NULL,
                                                        NULL,
                                                        NULL,
                                                        NULL,
                                                        SQLERRM);
         END;
      WHEN OTHERS
      THEN
         get_default_biller_detail (x_biller_last_name,
                                    x_biller_first_name,
                                    x_biller_phone_number,
                                    x_biller_fax_number,
                                    x_biller_email_address,
                                    x_emp_number);



         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_location,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END get_biller_detail_for_location;
   
   FUNCTION get_S21_location_number(
      p_customer_name           IN     VARCHAR2,
      p_orcl_bill_to_use_loc    IN     VARCHAR2)
	RETURN VARCHAR2
	  --p_s21_loc_num        OUT VARCHAR2   
   IS
      lv_s21_bill_to_cust_cd VARCHAR2(30);
	  ln_s21_count           NUMBER := 0;
	  
	  lv_orcl_account_number VARCHAR2(30 CHAR);
	  lv_orcl_psn            VARCHAR2(30 CHAR);
	  lv_s21_psn             VARCHAR2(30 CHAR);


   BEGIN 
   
   
     /* Check if this location already exists in Mapping Table for this customer */
	 
	 BEGIN 
	    SELECT s21_bill_to_cust_cd
		   INTO lv_s21_bill_to_cust_cd
		   FROM CANON_E479_ORCL_S21_CONVMAP
		  WHERE orcl_account_name = p_customer_name
		    AND s21_account_name  = p_customer_name
		    AND orcl_bill_to_use  = p_orcl_bill_to_use_loc;
	 EXCEPTION 
       WHEN OTHERS THEN 
	      lv_s21_bill_to_cust_cd := NULL;
	 END;
	 
	 IF lv_s21_bill_to_cust_cd IS NULL THEN 
    
		 /* Try with exact matching address */ 
		 SELECT  
			bill_to.bill_to_cust_cd s21_bill_to_use,
			hca.account_number oracle_account_number,
			hps.party_site_number orcle_PSN,
			loc.loc_num
			INTO lv_s21_bill_to_cust_cd,
			lv_orcl_account_number,
			lv_orcl_psn,
			lv_s21_psn
		   /*
			templ.customer_name,
			hca.account_number oracle_account_number,
			hps.party_site_number orcle_PSN,
			hcsua.location oracle_bill_to_use,
			hzl.address1 orcl_add1,
			hzl.address2 orcl_add2,
			hzl.city orcl_city,
			hzl.state orcl_state,
			hzl.postal_code orcle_zipcode,
			loc.ds_acct_num s21_acct_num,
			loc.loc_num  s21_loc_num,
			bill_to.bill_to_cust_cd s21_bill_to_use,
			 bill_to.first_line_addr s21_addr1,
			 bill_to.scd_line_addr s21_addr2,
			 bill_to.cty_addr s21_city,
			 bill_to.st_cd s21_state,
			 bill_to.post_cd s21_zipcode
			 */
		  FROM 
			   --ds_acct_cust acct,  S21 DB2 changes
			   sell_to_cust  acct,
			   acct_loc loc,
			   bill_to_cust bill_to,
			   ds_xref_acct dxa,
			   hz_cust_accounts@CANMTH.CUSA.CANON.COM hca,
			   hz_locations@CANMTH.CUSA.CANON.COM hzl,
			   hz_party_sites@CANMTH.CUSA.CANON.COM hps,
			   hz_cust_site_uses_all@CANMTH.CUSA.CANON.COM hcsua,
			   hz_cust_acct_sites_all@CANMTH.CUSA.CANON.COM hcasa
		 WHERE     1 = 1
			   AND loc.ezcancelflag = '0'
			   AND loc.glbl_cmpy_cd = 'ADB'
			   AND dxa.ezcancelflag = '0'
			   AND dxa.glbl_cmpy_cd = 'ADB'
			   AND acct.ezcancelflag = '0'
			   AND acct.glbl_cmpy_cd = 'ADB'
			   AND bill_to.ezcancelflag = '0'
			   AND bill_to.glbl_cmpy_cd = 'ADB'
			   AND acct.ds_acct_nm = p_customer_name
			   AND loc.loc_num = bill_to.loc_num
			   AND loc.ds_acct_num = hca.account_number
			   --AND loc.ds_acct_num = acct.ds_acct_num  S21 DB2 changes
			   AND loc.ds_acct_num = acct.sell_to_cust_cd
			   AND acct.ds_acct_nm = dxa.ds_xref_acct_nm
			   AND dxa.ds_xref_acct_tp_cd ='30'
			   AND dxa.ds_xref_acct_cd = hps.party_site_number
			   and dxa.loc_num = loc.loc_num
			   AND hzl.address1 = bill_to.first_line_addr
			   AND NVL (hzl.address2, '~') = NVL (bill_to.scd_line_addr, '~')
			   AND hzl.city = bill_to.cty_addr
			   AND hzl.state = bill_to.st_cd
			   AND hzl.postal_code = bill_to.post_cd
			   AND hca.party_id = hps.party_id
			   AND hca.cust_account_id = hcasa.cust_account_id
			   --AND hps.status ='A'
			   AND hzl.location_id = hps.location_id
			   AND hcasa.party_site_id = hps.party_site_id
			   AND hcasa.CUST_ACCT_SITE_ID = hcsua.CUST_ACCT_SITE_ID
			   AND hcsua.location = p_orcl_bill_to_use_loc;
       END IF;			   
				
	   IF lv_s21_bill_to_cust_cd IS NOT NULL THEN 
	      /* Check if it is available in mapping table 
		   * if not, insert into mapping table
		   */
		   
		    SELECT COUNT(*)
		       INTO ln_s21_count
		       FROM CANON_E479_ORCL_S21_CONVMAP
		      WHERE orcl_account_name   = p_customer_name
			    AND s21_account_name  = p_customer_name
		        AND orcl_bill_to_use    = p_orcl_bill_to_use_loc
				AND s21_bill_to_cust_cd =lv_s21_bill_to_cust_cd;
		    
			IF ln_s21_count = 0 THEN 
			   INSERT INTO CANON_E479_ORCL_S21_CONVMAP(
					ORCL_ACCOUNT_NUMBER,
					ORCL_ACCOUNT_NAME,
					S21_ACCOUNT_NUMBER,
					S21_ACCOUNT_NAME,
					ORCL_PSN,
					S21_PSN,
					ORCL_BILL_TO_USE,
					S21_BILL_TO_CUST_CD,
					CREATION_DATE,
					CREATED_BY,
					LAST_UPDATE_DATE,
					LAST_UPDATED_BY,
					COMMENTS,
					REMARKS
					)
				VALUES(
				   lv_orcl_account_number,
				   p_customer_name,
				   lv_orcl_account_number,
				   p_customer_name,
				   lv_orcl_psn,
				   lv_s21_psn,
				   p_orcl_bill_to_use_loc,
				   lv_s21_bill_to_cust_cd,
				   SYSDATE,
				   -100000001,
				   SYSDATE,
				   -100000001,
				   'Mapping for Oracle11i-S21 Account/PSN/Location',
				   'Mapping for Oracle11i-S21 Account/PSN/Location'
				);
			END IF;
	   END IF;
	   
	   RETURN lv_s21_bill_to_cust_cd;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN 
	    BEGIN 
		    /* Try without address */ 
			 SELECT  
					bill_to.bill_to_cust_cd s21_bill_to_use,
					hca.account_number oracle_account_number,
					hps.party_site_number orcle_PSN,
					loc.loc_num
				INTO 
					lv_s21_bill_to_cust_cd,
					lv_orcl_account_number,
					lv_orcl_psn,
					lv_s21_psn
			   /*
				templ.customer_name,
				hca.account_number oracle_account_number,
				hps.party_site_number orcle_PSN,
				hcsua.location oracle_bill_to_use,
				hzl.address1 orcl_add1,
				hzl.address2 orcl_add2,
				hzl.city orcl_city,
				hzl.state orcl_state,
				hzl.postal_code orcle_zipcode,
				loc.ds_acct_num s21_acct_num,
				loc.loc_num  s21_loc_num,
				bill_to.bill_to_cust_cd s21_bill_to_use,
				 bill_to.first_line_addr s21_addr1,
				 bill_to.scd_line_addr s21_addr2,
				 bill_to.cty_addr s21_city,
				 bill_to.st_cd s21_state,
				 bill_to.post_cd s21_zipcode
				 */
			  FROM 
				   --ds_acct_cust acct,  S21 DB2 changes
			       sell_to_cust  acct,
				   acct_loc loc,
				   bill_to_cust bill_to,
				   ds_xref_acct dxa,
				   hz_cust_accounts@CANMTH.CUSA.CANON.COM hca,
				   --hz_locations@CANMTH.CUSA.CANON.COM hzl,
				   hz_party_sites@CANMTH.CUSA.CANON.COM hps,
				   hz_cust_site_uses_all@CANMTH.CUSA.CANON.COM hcsua,
				   hz_cust_acct_sites_all@CANMTH.CUSA.CANON.COM hcasa
			 WHERE     1 = 1
				   AND loc.ezcancelflag = '0'
				   AND loc.glbl_cmpy_cd = 'ADB'
				   AND dxa.ezcancelflag = '0'
				   AND dxa.glbl_cmpy_cd = 'ADB'
				   AND acct.ezcancelflag = '0'
				   AND acct.glbl_cmpy_cd = 'ADB'
				   AND bill_to.ezcancelflag = '0'
				   AND bill_to.glbl_cmpy_cd = 'ADB'
				   AND acct.ds_acct_nm = p_customer_name
				   AND loc.loc_num = bill_to.loc_num
				   AND loc.ds_acct_num = hca.account_number
				   --AND loc.ds_acct_num = acct.ds_acct_num  S21 DB2 changes
			       AND loc.ds_acct_num = acct.sell_to_cust_cd
				   AND acct.ds_acct_nm = dxa.ds_xref_acct_nm
				   AND dxa.ds_xref_acct_tp_cd ='30'
				   AND dxa.ds_xref_acct_cd = hps.party_site_number
				   and dxa.loc_num = loc.loc_num
				   /*AND hzl.address1 = bill_to.first_line_addr
				   AND NVL (hzl.address2, '~') = NVL (bill_to.scd_line_addr, '~')
				   AND hzl.city = bill_to.cty_addr
				   AND hzl.state = bill_to.st_cd
				   AND hzl.postal_code = bill_to.post_cd
				   */
				   AND hca.party_id = hps.party_id
				   AND hca.cust_account_id = hcasa.cust_account_id
				   --AND hps.status ='A'
				   --AND hzl.location_id = hps.location_id
				   AND hcasa.party_site_id = hps.party_site_id
				   AND hcasa.CUST_ACCT_SITE_ID = hcsua.CUST_ACCT_SITE_ID
				   AND hcsua.location = p_orcl_bill_to_use_loc;
              IF lv_s21_bill_to_cust_cd IS NOT NULL THEN 
			  /* Check if it is available in mapping table 
			   * if not, insert into mapping table
			   */
		   
				SELECT COUNT(*)
				   INTO ln_s21_count
				   FROM CANON_E479_ORCL_S21_CONVMAP
				  WHERE orcl_account_name   = p_customer_name
					AND s21_account_name  = p_customer_name
					AND orcl_bill_to_use    = p_orcl_bill_to_use_loc
					AND s21_bill_to_cust_cd =lv_s21_bill_to_cust_cd;
		    
				IF ln_s21_count = 0 THEN 
				   INSERT INTO CANON_E479_ORCL_S21_CONVMAP(
						ORCL_ACCOUNT_NUMBER,
						ORCL_ACCOUNT_NAME,
						S21_ACCOUNT_NUMBER,
						S21_ACCOUNT_NAME,
						ORCL_PSN,
						S21_PSN,
						ORCL_BILL_TO_USE,
						S21_BILL_TO_CUST_CD,
						CREATION_DATE,
						CREATED_BY,
						LAST_UPDATE_DATE,
						LAST_UPDATED_BY,
						COMMENTS,
						REMARKS
						)
					VALUES(
					   lv_orcl_account_number,
					   p_customer_name,
					   lv_orcl_account_number,
					   p_customer_name,
					   lv_orcl_psn,
					   lv_s21_psn,
					   p_orcl_bill_to_use_loc,
					   lv_s21_bill_to_cust_cd,
					   SYSDATE,
					   -100000001,
					   SYSDATE,
					   -100000001,
					   'Mapping for Oracle11i-S21 Account/PSN/Location',
					   'Mapping for Oracle11i-S21 Account/PSN/Location'
					);
				END IF;
	          END IF;				    
           RETURN lv_s21_bill_to_cust_cd;				     
		EXCEPTION 
		  WHEN OTHERS THEN 
		     RETURN NULL;
		END;
      WHEN OTHERS
      THEN
         RETURN NULL;
   END get_S21_location_number;
   
   
   PROCEDURE log_error (p_script_name      IN VARCHAR2,
                        p_key1                    IN VARCHAR2,
                        p_key2                    IN VARCHAR2,
                        p_key3                    IN VARCHAR2,
                        p_key4                    IN VARCHAR2,
                        p_key5                    IN VARCHAR2,
                        p_key6                    IN VARCHAR2,
                        p_error_message           IN LONG)
   IS
          PRAGMA AUTONOMOUS_TRANSACTION;						
   BEGIN
      INSERT INTO CANON_E479_UPGRADE_LOG(
					SCRIPT_NAME         ,
					MESSAGE1            ,
					MESSAGE2            ,
					MESSAGE3            ,
					MESSAGE4            ,
					MESSAGE5            ,
					MESSAGE6            ,
					ERROR_MESSAGE       ,
					CREATION_DATE       )
           VALUES (p_script_name,
                   p_key1,
                   p_key2,
                   p_key3,
                   p_key4,
                   p_key5,
                   p_key6,
				   p_error_message,
                   SYSDATE
                   );


      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
   END log_error;
   
   
END CANON_E479_CONVERSION_UTIL_PKG;
/

SHOW ERRORS;