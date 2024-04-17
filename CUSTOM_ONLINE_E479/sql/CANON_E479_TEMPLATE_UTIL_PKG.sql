CREATE OR REPLACE PACKAGE CANON_E479_TEMPLATE_UTIL_PKG
AS
 /************************************************************************************************
     *                                                                                          *
     * File NAME       : CANON_E479_TEMPLATE_UTIL_PKG.sql                                       *
     * DESCRIPTION     : Util Package for Custom Billing Template Setup                          *
	 *                                                                                          *
     * REVISION HISTORY:                                                                        *
     *                                                                                          *
     * DEVELOPER         DATE           DESCRIPTION                                             *
     * -------------     -----------    ---------------------------                             *
     * Lakshmi Gopalsami 9/15/2015     Initial Creation
   *************************************************************************************************/
   
   TYPE RESULT_CURSOR IS REF CURSOR;

   l_package_name   VARCHAR2 (30) := 'CANON_E479_TEMPLATE_UTIL_PKG';

   C_PROD_DBASE     VARCHAR2 (30) := 'CANPRD';

   PROCEDURE is_prod (x_flag OUT VARCHAR2);

   PROCEDURE get_db_name (x_name OUT VARCHAR2);

   PROCEDURE get_biller_detail (p_header_id              IN     NUMBER,
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
END CANON_E479_TEMPLATE_UTIL_PKG;
/

show errors;

CREATE OR REPLACE PACKAGE BODY CANON_E479_TEMPLATE_UTIL_PKG
AS
   /************************************************************************************************
     *                                                                                          *
     * File NAME       : CANON_E479_TEMPLATE_UTIL_PKG.sql                                       *
     * DESCRIPTION     : Util Package for Custom Billing Template Setup                          *
	 *                                                                                          *
     * REVISION HISTORY:                                                                        *
     *                                                                                          *
     * DEVELOPER         DATE           DESCRIPTION                                             *
     * -------------     -----------    ---------------------------                             *
     * Lakshmi Gopalsami 9/15/2015     Initial Creation
   *************************************************************************************************/
   PROCEDURE is_prod (x_flag OUT VARCHAR2)
   AS
      l_procedure_name   VARCHAR2 (20) := 'is_prod';
      l_db_name          VARCHAR2 (100);
   BEGIN
      x_flag := 'N';

      SELECT SYS_CONTEXT ('userenv', 'db_name') INTO l_db_name FROM DUAL;

      IF (l_db_name = C_PROD_DBASE)
      THEN
         x_flag := 'Y';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_flag := 'N';

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
   END is_prod;

   PROCEDURE get_db_name (x_name OUT VARCHAR2)
   AS
      l_procedure_name   VARCHAR2 (20) := 'get_db_name';
      l_db_name          VARCHAR2 (100);
   BEGIN
      /* select name into x_name
       from v$database;
       */
      SELECT SYS_CONTEXT ('userenv', 'db_name') INTO x_name FROM DUAL;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_name := ' ';

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
   END get_db_name;

   PROCEDURE get_biller_detail (p_header_id              IN     NUMBER,
                                x_biller_last_name          OUT VARCHAR2,
                                x_biller_first_name         OUT VARCHAR2,
                                x_biller_phone_number       OUT VARCHAR2,
                                x_biller_fax_number         OUT VARCHAR2,
                                x_biller_email_address      OUT VARCHAR2,
                                x_emp_number                OUT VARCHAR2)
   AS
      l_account_name      VARCHAR2 (200);
      l_location          VARCHAR2 (50);
      l_party_name        VARCHAR2 (200);
      l_procedure_name    VARCHAR2 (200) := 'get_biller_detail';
      lv_template_level   VARCHAR2 (50);
   BEGIN
      SELECT template_level,
             parent_customer_name,
             customer_name,
             bill_to_location,
             biller_contact_no,
             biller_fax_no,
             biller_email_id,
             biller_last_name,
             biller_first_name
        INTO lv_template_level,
             l_account_name,
             l_party_name,
             l_location,
             x_biller_phone_number,
             x_biller_fax_number,
             x_biller_email_address,
             x_biller_last_name,
             x_biller_first_name
        FROM CANON_E479_WEB_TEMPL_HEADER
       WHERE header_id = p_header_id;
        
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
                
               x_biller_last_name := NULL;
               x_biller_first_name := NULL;
               x_biller_phone_number := NULL;
               x_biller_fax_number := NULL;
               x_biller_email_address := NULL;
               x_emp_number := NULL;
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
END CANON_E479_TEMPLATE_UTIL_PKG;
/

show errors;