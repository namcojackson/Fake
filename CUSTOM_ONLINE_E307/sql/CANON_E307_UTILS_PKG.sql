CREATE OR REPLACE PACKAGE CANON_E307_UTILS
AS
   /*******************************************************************************************
      Package Name: CANON_E307_UTILS_SPEC
      Description:  Package to be used by ASCC
      Dependencies: NA
      Action History:
      -----------------------------------------------------------------------------------------
      Author              Version              Date                     Comments
      -----------------------------------------------------------------------------------------
      Sesh Ragavachari      1.0                  01-Sep-2015              Inital Version
    *****************************************************************************************/

   FUNCTION format_date_func (p_date     IN VARCHAR2,
                              p_time     IN VARCHAR2,
                              p_format   IN VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION format_timerange_func (p_prefix      IN VARCHAR2,
                                   p_time1       IN VARCHAR2,
                                   p_time2       IN VARCHAR2,
                                   p_separator   IN VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION format_date_range_string (p_date_from   IN VARCHAR2,
                                      p_date_to     IN VARCHAR2,
                                      p_format      IN VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION add_time_and_format_datetime (p_date               IN VARCHAR2,
                                          p_time               IN VARCHAR2,
                                          p_add_time_in_mins   IN VARCHAR2,
                                          p_format             IN VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION date_diff (p_from_date   IN VARCHAR2,
                       p_from_time   IN VARCHAR2,
                       p_to_date     IN VARCHAR2,
                       p_to_time     IN VARCHAR2)
      RETURN NUMBER;

   FUNCTION date_diff_format1 (p_from_date   IN VARCHAR2,
                               p_from_time   IN VARCHAR2,
                               p_to_date     IN VARCHAR2,
                               p_to_time     IN VARCHAR2)
      RETURN NUMBER;

   FUNCTION date_diff_format2 (p_from_date   IN VARCHAR2,
                               p_from_time   IN VARCHAR2,
                               p_to_date     IN VARCHAR2,
                               p_to_time     IN VARCHAR2,
                               p_from_ampm   IN VARCHAR2,
                               p_to_ampm     IN VARCHAR2)
      RETURN NUMBER;

   FUNCTION format_date (p_date IN VARCHAR2, p_format IN VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION format_time (p_time IN VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION format_time (p_time       IN VARCHAR2,
                         p_time_uom   IN VARCHAR2,
                         p_format     IN VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION format_address (p_cur_addr_line   IN VARCHAR2,
                            p_cur_city        IN VARCHAR2,
                            p_cur_st_cd       IN VARCHAR2,
                            p_cur_post_cd     IN VARCHAR2,
                            p_cur_ctry_cd     IN VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION format_date2_func (p_date IN VARCHAR2, p_format IN VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION format_date3_func (p_date IN VARCHAR2, p_format IN VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION FORMAT_DATE_AND_TIME (p_date IN VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION FORMAT_NEW_TIME (p_time IN VARCHAR2)
      RETURN VARCHAR2;
   FUNCTION FORMAT_NEW_SERV_TIME (p_time1 IN VARCHAR2,
                                  p_time2 IN VARCHAR2)
   RETURN VARCHAR2;
   FUNCTION GET_USER_EDIT_ROLE (p_user IN VARCHAR2)
      RETURN VARCHAR2;
END CANON_E307_UTILS;
/
CREATE OR REPLACE PACKAGE BODY CANON_E307_UTILS
AS
   /*******************************************************************************************
    Package Name: CANON_E307_UTILS_BODY
    Description:  Package to be used by ASCC
    Dependencies: NA
    Action History:
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Sesh Ragavachari      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   /*******************************************************************************************
    Function Name: FORMAT_DATE_FUNC
    Description: Combine and format the date and time.
    Input Parameters: p_date,
         p_time,
         p_format

    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Sesh Ragavachari      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   FUNCTION FORMAT_DATE_FUNC (p_date     IN VARCHAR2,
                              p_time     IN VARCHAR2,
                              p_format   IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_date_time_formatted   VARCHAR2 (50) := NULL;
   BEGIN
      IF p_date IS NOT NULL AND p_time IS NOT NULL
      THEN
         IF p_format = 'FORMAT1'
         THEN
            BEGIN
               l_date_time_formatted :=
                     TO_CHAR (TO_DATE (p_date, 'YYYYMMDD'), 'DD-MON-YYYY')
                  || ' '
                  || SUBSTR (p_time, 1, 2)
                  || ':'
                  || SUBSTR (p_time, 3, 2)
                  || ':'
                  || SUBSTR (p_time, 5, 2);
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_date_time_formatted := NULL;
            END;
         END IF;

         IF p_format = 'FORMAT2'
         THEN
            BEGIN
               l_date_time_formatted :=
                     TO_CHAR (TO_DATE (p_date, 'YYYYMMDD'), 'MM/DD/YYYY')
                  || ' '
                  || SUBSTR (p_time, 1, 2)
                  || ':'
                  || SUBSTR (p_time, 3, 2);
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_date_time_formatted := NULL;
            END;
         END IF;
      END IF;

      IF p_date IS NOT NULL AND p_time IS NOT NULL
      THEN
         IF p_format = 'FORMAT3'
         THEN
            BEGIN
               l_date_time_formatted :=
                     TO_CHAR (TO_DATE (p_date, 'YYYYMMDD'), 'DD-MON-YYYY')
                  || ' '
                  || SUBSTR (p_time, 1, 2)
                  || ':'
                  || SUBSTR (p_time, 3, 2);
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_date_time_formatted := NULL;
            END;
         END IF;

         IF p_format = 'FORMAT4'
         THEN
            BEGIN
               l_date_time_formatted :=
                     TO_CHAR (TO_DATE (p_date, 'YYYYMMDD'), 'Mon DD yyyy')
                  || ' '
                  || TO_CHAR (TO_DATE (SUBSTR (p_time, 1, 4), 'HH24MI'),
                              'hh:mi PM');
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_date_time_formatted := NULL;
            END;
         END IF;
      END IF;

      RETURN l_date_time_formatted;
   END FORMAT_DATE_FUNC;

   /*******************************************************************************************
    Function Name: FORMAT_TIMERANGE_FUNC
    Description: Combine and format the time.
    Input Parameters: p_prefix,
          p_time1,
          p_time2,
          p_separator

     -----------------------------------------------------------------------------------------
     Author              Version              Date                     Comments
     -----------------------------------------------------------------------------------------
     Sesh Ragavachari      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/

   FUNCTION FORMAT_TIMERANGE_FUNC (p_prefix      IN VARCHAR2,
                                   p_time1       IN VARCHAR2,
                                   p_time2       IN VARCHAR2,
                                   p_separator   IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_timerange_formatted   VARCHAR2 (50);
   BEGIN
      IF p_time1 IS NOT NULL AND p_time2 IS NOT NULL
      THEN
         BEGIN
            l_timerange_formatted :=
                  p_prefix
               || ' '
               || SUBSTR (p_time1, 1, 2)
               || ':'
               || SUBSTR (p_time1, 3, 2)
               || p_separator
               || SUBSTR (p_time2, 1, 2)
               || ':'
               || SUBSTR (p_time2, 3, 2);
         EXCEPTION
            WHEN OTHERS
            THEN
               l_timerange_formatted := NULL;
         END;
      ELSE
         l_timerange_formatted := NULL;
      END IF;

      RETURN l_timerange_formatted;
   END FORMAT_TIMERANGE_FUNC;

   /*******************************************************************************************
    Function Name: ADD_TIME_AND_FORMAT_DATETIME
    Description: Combine and format the time.
    Input Parameters: p_prefix,
          p_time1,
          p_time2,
          p_separator

     -----------------------------------------------------------------------------------------
     Author              Version              Date                     Comments
     -----------------------------------------------------------------------------------------
     Sesh Ragavachari      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/

   FUNCTION ADD_TIME_AND_FORMAT_DATETIME (p_date               IN VARCHAR2,
                                          p_time               IN VARCHAR2,
                                          p_add_time_in_mins   IN VARCHAR2,
                                          p_format             IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_date                 DATE;
      l_new_date             DATE;
      l_new_date_formatted   VARCHAR2 (50);
      l_add_time_in_days     NUMBER;
   BEGIN
      DBMS_OUTPUT.put_line (p_date);
      DBMS_OUTPUT.put_line (p_time);
      DBMS_OUTPUT.put_line (p_add_time_in_mins);
      l_date := TO_DATE (p_date || p_time, 'YYYYMMDDHH24MISS');

      --- Add Time in Days
      BEGIN
         l_add_time_in_days := TO_NUMBER (p_add_time_in_mins) / (60 * 24);
      EXCEPTION
         WHEN OTHERS
         THEN
            -- If some issue just add 4 Hours by Default
            l_add_time_in_days := 4 / 24;
      END;

      l_new_date := l_date + l_add_time_in_days;

      IF p_format = 'FORMAT1'
      THEN
         l_new_date_formatted := TO_CHAR (l_new_date, 'DD-MON-YYYY HH24:MI');
      END IF;

      IF p_format = 'FORMAT2'
      THEN
         l_new_date_formatted :=
            TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 12), 'YYYYMMDDHH24MI'),
                     'Mon DD yyyy hh:mi PM');
      END IF;

      RETURN l_new_date_formatted;
   END ADD_TIME_AND_FORMAT_DATETIME;

   /*******************************************************************************************
     Function Name: DATE_DIFF
     Description: Get difference between 2 dates which are in YYYYMMDDHH24MISS format.
     Input Parameters: p_from_date,
           p_from_time,
           p_to_date,
           p_to_time

     -----------------------------------------------------------------------------------------
     Author              Version              Date                     Comments
     -----------------------------------------------------------------------------------------
     Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/

   FUNCTION DATE_DIFF (p_from_date   IN VARCHAR2,
                       p_from_time   IN VARCHAR2,
                       p_to_date     IN VARCHAR2,
                       p_to_time     IN VARCHAR2)
      RETURN NUMBER
   IS
      l_from_date            DATE;
      l_to_date              DATE;
      l_new_date             DATE;
      l_new_date_formatted   VARCHAR2 (50);
      l_diff                 NUMBER;
   BEGIN
      DBMS_OUTPUT.put_line (p_from_date);
      DBMS_OUTPUT.put_line (p_from_time);
      DBMS_OUTPUT.put_line (p_to_date);
      DBMS_OUTPUT.put_line (p_to_time);
      l_from_date := TO_DATE (p_from_date || p_from_time, 'YYYYMMDDHH24MISS');
      l_to_date := TO_DATE (p_to_date || p_to_time, 'YYYYMMDDHH24MISS');

      --- Add Time in Days
      BEGIN
         l_diff := ROUND ( (l_to_date - l_from_date) * 24, 2);
      EXCEPTION
         WHEN OTHERS
         THEN
            l_diff := 0;
      END;

      RETURN l_diff;
   END DATE_DIFF;

   /*******************************************************************************************
     Function Name: DATE_DIFF_FORMAT1
     Description: Get difference between 2 dates which are in in DD-MON-YYYY HH24:MI format.
     Input Parameters: p_from_date,
           p_from_time,
           p_to_date,
           p_to_time

     -----------------------------------------------------------------------------------------
     Author              Version              Date                     Comments
     -----------------------------------------------------------------------------------------
     Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   FUNCTION DATE_DIFF_FORMAT1 (p_from_date   IN VARCHAR2,
                               p_from_time   IN VARCHAR2,
                               p_to_date     IN VARCHAR2,
                               p_to_time     IN VARCHAR2)
      RETURN NUMBER
   IS
      l_from_date            DATE;
      l_to_date              DATE;
      l_new_date             DATE;
      l_new_date_formatted   VARCHAR2 (50);
      l_diff                 NUMBER;
   BEGIN
      l_from_date :=
         TO_DATE (p_from_date || p_from_time, 'DD-MON-YYYY HH24:MI');
      l_to_date := TO_DATE (p_to_date || p_to_time, 'DD-MON-YYYY HH24:MI');

      --- Add Time in Days
      BEGIN
         l_diff := ROUND ( (l_to_date - l_from_date) * 24, 2);
      EXCEPTION
         WHEN OTHERS
         THEN
            l_diff := 0;
      END;

      RETURN l_diff;
   END DATE_DIFF_FORMAT1;

   FUNCTION DATE_DIFF_FORMAT2 (p_from_date   IN VARCHAR2,
                               p_from_time   IN VARCHAR2,
                               p_to_date     IN VARCHAR2,
                               p_to_time     IN VARCHAR2,
                               p_from_ampm   IN VARCHAR2,
                               p_to_ampm     IN VARCHAR2)
      RETURN NUMBER
   IS
      l_from_date            DATE;
      l_to_date              DATE;
      l_new_date             DATE;
      l_new_date_formatted   VARCHAR2 (50);
      l_diff                 NUMBER;
      l_time_from            VARCHAR2 (20);
      l_time_to              VARCHAR2 (20);
   BEGIN
      l_time_from :=
         TO_CHAR (TO_DATE (p_from_time || ' ' || p_from_ampm, 'HH:MI AM'),
                  'HH24:MI:SS');
      l_time_to :=
         TO_CHAR (TO_DATE (p_to_time || ' ' || p_to_ampm, 'HH:MI AM'),
                  'HH24:MI:SS');

      l_from_date :=
         TO_DATE (p_from_date || SUBSTR (l_time_from, 1, 5),
                  'Mon DD yyyy HH24:MI');
      l_to_date :=
         TO_DATE (p_to_date || SUBSTR (l_time_to, 1, 5),
                  'Mon DD yyyy HH24:MI');

      --- Add Time in Days
      BEGIN
         l_diff := ROUND ( (l_to_date - l_from_date) * 24, 2);
      EXCEPTION
         WHEN OTHERS
         THEN
            l_diff := 0;
      END;

      RETURN l_diff;
   END DATE_DIFF_FORMAT2;

   /*******************************************************************************************
     Function Name: FORMAT_ADDRESS
     Description: Format the address
     Input Parameters: p_cur_addr_line,
           p_cur_city,
           p_cur_st_cd,
           p_cur_post_cd,
           p_cur_ctry_cd

     -----------------------------------------------------------------------------------------
     Author              Version              Date                     Comments
     -----------------------------------------------------------------------------------------
     Sesh Ragavachari      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   FUNCTION FORMAT_ADDRESS (p_cur_addr_line   IN VARCHAR2,
                            p_cur_city        IN VARCHAR2,
                            p_cur_st_cd       IN VARCHAR2,
                            p_cur_post_cd     IN VARCHAR2,
                            p_cur_ctry_cd     IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_return_string   VARCHAR2 (100);
   BEGIN
      l_return_string :=
            p_cur_addr_line
         || ' '
         || p_cur_city
         || ' '
         || p_cur_st_cd
         || ' '
         || p_cur_post_cd;
      RETURN l_return_string;
   END FORMAT_ADDRESS;

   /*******************************************************************************************
     Function Name: FORMAT_DATE2_FUNC
     Description: Format the date which is in YYYYMMDDHHMISS format
     Input Parameters: p_date,
           p_format

     -----------------------------------------------------------------------------------------
     Author              Version              Date                     Comments
     -----------------------------------------------------------------------------------------
     Sesh Ragavachari      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   FUNCTION FORMAT_DATE2_FUNC (p_date IN VARCHAR2, p_format IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_date_time_formatted   VARCHAR2 (50) := NULL;
   BEGIN
      IF p_date IS NOT NULL
      THEN
         IF p_format = 'FORMAT1'
         THEN
            BEGIN
               l_date_time_formatted :=
                     TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 8), 'YYYYMMDD'),
                              'DD-MON-YYYY')
                  || ' '
                  || SUBSTR (p_date, 9, 2)
                  || ':'
                  || SUBSTR (p_date, 11, 2);
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_date_time_formatted := NULL;
            END;
         END IF;

         IF p_format = 'FORMAT2'
         THEN
            BEGIN
               l_date_time_formatted :=
                     TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 8), 'YYYYMMDD'),
                              'MM/DD/YYYY')
                  || ' '
                  || SUBSTR (p_date, 9, 2)
                  || ':'
                  || SUBSTR (p_date, 11, 2);
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_date_time_formatted := NULL;
            END;
         END IF;
      END IF;

      RETURN l_date_time_formatted;
   END FORMAT_DATE2_FUNC;

   /*******************************************************************************************
     Function Name: FORMAT_DATE3_FUNC
     Description: Format the date which is in YYYYMMDD format
     Input Parameters: p_date,
           p_format

     -----------------------------------------------------------------------------------------
     Author              Version              Date                     Comments
     -----------------------------------------------------------------------------------------
     Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   FUNCTION FORMAT_DATE3_FUNC (p_date IN VARCHAR2, p_format IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_date_time_formatted   VARCHAR2 (50);
   BEGIN
      IF p_format = 'FORMAT1'
      THEN
         BEGIN
            l_date_time_formatted :=
               TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 8), 'YYYYMMDD'),
                        'DD-MON-YYYY');
         EXCEPTION
            WHEN OTHERS
            THEN
               l_date_time_formatted := NULL;
         END;
      END IF;

      IF p_format = 'FORMAT2'
      THEN
         BEGIN
            l_date_time_formatted :=
               TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 8), 'YYYYMMDD'),
                        'MM/DD/YYYY');
         EXCEPTION
            WHEN OTHERS
            THEN
               l_date_time_formatted := NULL;
         END;
      END IF;

      IF p_format = 'FORMAT3'
      THEN
         BEGIN
            l_date_time_formatted :=
               TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 8), 'YYYYMMDD'),
                        'MM/DD/YYYY');
         EXCEPTION
            WHEN OTHERS
            THEN
               l_date_time_formatted := NULL;
         END;
      END IF;

      RETURN l_date_time_formatted;
   END FORMAT_DATE3_FUNC;

   /*******************************************************************************************
     Function Name: FORMAT_DATE_RANGE_STRING
     Description: Combine and Format the date from and date to
     Input Parameters: p_date_from,
           p_date_to,
           p_format

     -----------------------------------------------------------------------------------------
     Author              Version              Date                     Comments
     -----------------------------------------------------------------------------------------
     Sesh Ragavachari      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/

   FUNCTION FORMAT_DATE_RANGE_STRING (p_date_from   IN VARCHAR2,
                                      p_date_to     IN VARCHAR2,
                                      p_format      IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_date_range_string   VARCHAR2 (100);
   BEGIN
      IF p_format = 'FORMAT1'
      THEN
         BEGIN
            l_date_range_string :=
                  TO_CHAR (TO_DATE (p_date_from, 'YYYYMMDD'), 'DD-MON-YYYY')
               || '/'
               || TO_CHAR (TO_DATE (p_date_to, 'YYYYMMDD'), 'DD-MON-YYYY');
         EXCEPTION
            WHEN OTHERS
            THEN
               l_date_range_string := NULL;
         END;
      END IF;

      IF p_format = 'FORMAT2'
      THEN
         BEGIN
            l_date_range_string :=
                  TO_CHAR (TO_DATE (p_date_from, 'YYYYMMDD'), 'MM/DD/YYYY')
               || '/'
               || TO_CHAR (TO_DATE (p_date_to, 'YYYYMMDD'), 'MM/DD/YYYY');
         EXCEPTION
            WHEN OTHERS
            THEN
               l_date_range_string := NULL;
         END;
      END IF;

      RETURN l_date_range_string;
   END FORMAT_DATE_RANGE_STRING;

   /*******************************************************************************************
     Function Name: FORMAT_DATE
     Description: Format Date
     Input Parameters: p_date,
           p_format

     -----------------------------------------------------------------------------------------
     Author              Version              Date                     Comments
     -----------------------------------------------------------------------------------------
     Sesh Ragavachari      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   FUNCTION FORMAT_DATE (p_date IN VARCHAR2, p_format IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_date_formatted   VARCHAR2 (50);
   BEGIN
      IF p_format = 'FORMAT1'
      THEN
         BEGIN
            l_date_formatted :=
               TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 8), 'YYYYMMDD'),
                        'DD-MON-YYYY');
         EXCEPTION
            WHEN OTHERS
            THEN
               l_date_formatted := NULL;
         END;
      END IF;

      IF p_format = 'FORMAT2'
      THEN
         BEGIN
            l_date_formatted :=
               TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 8), 'YYYYMMDD'),
                        'MM/DD/YYYY');
         EXCEPTION
            WHEN OTHERS
            THEN
               l_date_formatted := NULL;
         END;
      END IF;

      IF p_format = 'FORMAT3'
      THEN
         BEGIN
            l_date_formatted :=
               TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 8), 'YYYYMMDD'),
                        'DD-Mon-YYYY');
         EXCEPTION
            WHEN OTHERS
            THEN
               l_date_formatted := NULL;
         END;
      END IF;

      IF p_format = 'FORMAT4'
      THEN
         BEGIN
            l_date_formatted :=
               TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 8), 'YYYYMMDD'),
                        'Mon DD yyyy');
         EXCEPTION
            WHEN OTHERS
            THEN
               l_date_formatted := NULL;
         END;
      END IF;

      RETURN l_date_formatted;
   END FORMAT_DATE;

   /*******************************************************************************************
     Function Name: FORMAT_TIME
     Description: Format Time
     Input Parameters: p_time

     -----------------------------------------------------------------------------------------
     Author              Version              Date                     Comments
     -----------------------------------------------------------------------------------------
      Sesh Ragavachari    1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/

   FUNCTION FORMAT_TIME (p_time IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_time_formatted   VARCHAR2 (50);
   BEGIN
      BEGIN
         l_time_formatted :=
            SUBSTR (p_time, 1, 2) || ':' || SUBSTR (p_time, 3, 2);
      EXCEPTION
         WHEN OTHERS
         THEN
            l_time_formatted := NULL;
      END;

      RETURN l_time_formatted;
   END FORMAT_TIME;

   /*******************************************************************************************
     Function Name: FORMAT_TIME
     Description: Format Time
     Input Parameters: p_time,
                  p_time_uom,
                  p_format

     -----------------------------------------------------------------------------------------
     Author              Version              Date                     Comments
     -----------------------------------------------------------------------------------------
    Sesh Ragavachari      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/

   FUNCTION FORMAT_TIME (p_time       IN VARCHAR2,
                         p_time_uom   IN VARCHAR2,
                         p_format     IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_formatted_time   VARCHAR2 (100);
      l_minutes_part     NUMBER;
      l_hour_part        NUMBER;
   BEGIN
      DBMS_OUTPUT.put_line (p_time);

      IF p_format = 'FORMAT1'
      THEN
         BEGIN
            IF p_time_uom = 'MIN'
            THEN
               l_minutes_part := MOD (TO_NUMBER (p_time), 60);
               l_hour_part :=
                  ROUND ( (TO_NUMBER (p_time) - (l_minutes_part)) / 60);
               l_formatted_time := l_hour_part || ' HRS';

               IF l_minutes_part > 0
               THEN
                  l_formatted_time :=
                     l_formatted_time || ' ' || l_minutes_part || ' MINS';
               END IF;
            END IF;
         EXCEPTION
            WHEN OTHERS
            THEN
               -- If some issue just add 4 Hours by Default
               l_formatted_time := p_time;
         END;
      END IF;

      RETURN l_formatted_time;
   END FORMAT_TIME;

   FUNCTION FORMAT_DATE_AND_TIME (p_date IN VARCHAR2)
      RETURN VARCHAR2
   AS
      l_time_formatted   VARCHAR2 (50);
   BEGIN
      BEGIN
         l_time_formatted :=
            TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 12), 'YYYYMMDDHH24MI'),
                     'Mon DD yyyy hh:mi PM');
      EXCEPTION
         WHEN OTHERS
         THEN
            l_time_formatted := NULL;
      END;

      RETURN l_time_formatted;
   END FORMAT_DATE_AND_TIME;

   FUNCTION FORMAT_NEW_TIME (p_time IN VARCHAR2)
      RETURN VARCHAR2
   AS
      l_time_formatted   VARCHAR2 (50);
   BEGIN
      BEGIN
         l_time_formatted :=
            TO_CHAR (TO_DATE (SUBSTR (p_time, 1, 4), 'HH24MI'), 'hh:mi PM');
      EXCEPTION
         WHEN OTHERS
         THEN
            l_time_formatted := NULL;
      END;

      RETURN l_time_formatted;
   END FORMAT_NEW_TIME;
   FUNCTION FORMAT_NEW_SERV_TIME (p_time1 IN VARCHAR2,
                                  p_time2 IN VARCHAR2)
     RETURN VARCHAR2
   AS
      l_time_form2   VARCHAR2 (50);
      l_timerange_formatted  VARCHAR2(50);
   BEGIN
   IF p_time2 IS NOT NULL
   THEN
      BEGIN
         l_time_form2 :=
            TO_CHAR (TO_DATE (SUBSTR (p_time2, 1, 2), 'HH24MI'), 'hh:mi');
      EXCEPTION
         WHEN OTHERS
         THEN
            l_time_form2 := NULL;
      END;
  END IF;
  IF p_time1 IS NOT NULL AND l_time_form2 IS NOT NULL
      THEN
         BEGIN
            l_timerange_formatted :=
                 SUBSTR (p_time1, 1, 2)
               || ':'
               || SUBSTR (p_time1, 3, 2)
               || ' to '
               || l_time_form2
               || ' '
               || 'M-Sat';
         EXCEPTION
            WHEN OTHERS
            THEN
               l_timerange_formatted := NULL;
         END;      
        END IF;
      RETURN l_timerange_formatted;
   END FORMAT_NEW_SERV_TIME;   

   FUNCTION GET_USER_EDIT_ROLE (p_user IN VARCHAR2)
      RETURN VARCHAR2
   AS
      l_role_nm   VARCHAR2 (50);
   BEGIN
      BEGIN
         SELECT DISTINCT r.role_nm
           INTO l_role_nm
           FROM role r, usr_role ur, auth_psn u
          WHERE     1 = 1
                AND r.actv_flg = 'Y'
                AND r.role_id = ur.role_id
                AND ur.auth_psn_id = u.auth_psn_id
                AND u.actv_flg = 'Y'
                AND r.role_nm = 'E307_DIS'
                AND UPPER (u.usr_nm) = UPPER (p_user)
                AND r.glbl_cmpy_cd = 'ADB'
                AND u.glbl_cmpy_cd = 'ADB'
                AND r.ezcancelflag = '0'
                AND u.ezcancelflag = '0';
      EXCEPTION
         WHEN OTHERS
         THEN
            l_role_nm := NULL;
      END;

      IF l_role_nm IS NULL
      THEN
         BEGIN
            SELECT DISTINCT r.role_nm
              INTO l_role_nm
              FROM role r, usr_role ur, auth_psn u
             WHERE     1 = 1
                   AND r.actv_flg = 'Y'
                   AND r.role_id = ur.role_id
                   AND ur.auth_psn_id = u.auth_psn_id
                   AND u.actv_flg = 'Y'
                   AND r.role_nm = 'E307_SRM'
                   AND UPPER (u.usr_nm) = UPPER (p_user)
                   AND r.glbl_cmpy_cd = 'ADB'
                   AND u.glbl_cmpy_cd = 'ADB'
                   AND r.ezcancelflag = '0'
                   AND u.ezcancelflag = '0';
         EXCEPTION
            WHEN OTHERS
            THEN
               l_role_nm := NULL;
         END;
      END IF;

      IF l_role_nm IS NULL
      THEN
         BEGIN
            SELECT DISTINCT r.role_nm
              INTO l_role_nm
              FROM role r, usr_role ur, auth_psn u
             WHERE     1 = 1
                   AND r.actv_flg = 'Y'
                   AND r.role_id = ur.role_id
                   AND ur.auth_psn_id = u.auth_psn_id
                   AND u.actv_flg = 'Y'
                   AND r.role_nm = 'E307_OTH'
                   AND UPPER (u.usr_nm) = UPPER (p_user)
                   AND r.glbl_cmpy_cd = 'ADB'
                   AND u.glbl_cmpy_cd = 'ADB'
                   AND r.ezcancelflag = '0'
                   AND u.ezcancelflag = '0';
         EXCEPTION
            WHEN OTHERS
            THEN
               l_role_nm := NULL;
         END;
      END IF;

      RETURN l_role_nm;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN '';
   END GET_USER_EDIT_ROLE;
END CANON_E307_UTILS;
/
SHOW ERRORS;