/* __VER_INFO__  :  */
/****************************************************************************************
* Author        : Md. Meher Dewan
* Date          : 02/02/2020
* Description   : LMS pdf generation stored procedured
*****************************************************************************************/
#include <nSMART_SQL.h>

#define _SP_NAME        {ACT_report};
#define _TABLE_NAME     {T_LOAN};
#define _PRIMARY_KEY    {id_loan_key};
#define _VERSION        {id_loan_ver};

_DROP_PROC

_CREATE_PROC (_SP_NAME)
    
    _SP_PARAM_HEADER

    , @_PRIMARY_KEY                     INT                    = NULL       OUTPUT
    , @_VERSION                         INT                    = NULL   

    , @id_creator_key                   INT                   = NULL
    , @dtt_create                       DATETIME              = NULL
    , @id_customer_key                  INT                   = NULL       OUTPUT

    , @tx_application_no                VARCHAR(256)           = NULL    
    , @id_loan_type_key                 INT                    = NULL
    , @id_customer_type_key             INT                    = NULL
    , @tx_loan_tracking_id              INT                    = NULL       OUTPUT

    , @dec_applied_loan_amount          DECIMAL(20,2)          = NULL
    , @tx_loan_purpose                  VARCHAR(256)           = NULL
    , @int_over_loan                    INT                    = NULL
    , @dec_net_monthly_income           DECIMAL(20,2)          = NULL
    , @dec_tenor_year                   DECIMAL(20,2)          = NULL
    , @dec_existing_loan_amount         DECIMAL(20,2)          = NULL
    , @dec_interest_rate                DECIMAL(20,2)          = NULL
    , @dec_total_emi                    DECIMAL(20,2)          = NULL
    , @dec_monthly_installment          DECIMAL(20,2)          = NULL
    , @dec_disposable_income            DECIMAL(20,2)          = NULL
    , @tx_propose_emi_date              VARCHAR(256)           = NULL
    , @tx_duplications                  VARCHAR(256)           = NULL
    , @dtt_cib_generation_date          DATETIME               = NULL
    , @dec_proposed_dbr                 DECIMAL(20,2)          = NULL
    , @dec_allowed_dbr                  DECIMAL(20,2)          = NULL
    , @tx_cib_status                    VARCHAR(256)           = NULL
    , @dec_price_quotation_amount       DECIMAL(20,2)          = NULL
    , @tx_bank_participation            VARCHAR(256)           = NULL
    , @dec_business_recommed_amnt    DECIMAL(20,2)          = NULL
    , @dec_recommed_for_approval     DECIMAL(20,2)          = NULL

    , @tx_security                      VARCHAR(256)           = NULL
    , @tx_dob_of_pg_year                VARCHAR(256)           = NULL
    , @tx_guarantor_elibiblity          VARCHAR(256)           = NULL
    , @dtt_dob_of_pg                    DATETIME               = NULL
    , @dec_remaining_amt_aft_eml        DECIMAL(20,2)          = NULL
    , @dec_gross_salary_per_month       DECIMAL(20,2)          = NULL
    , @tx_borrower_participation        VARCHAR(256)           = NULL
    , @id_legal_entity_key              INT                    = NULL
    , @id_customer_ver                  INT                    = NULL
    , @tx_name_of_guarantor             VARCHAR(256)           = NULL
    , @tx_relationship_with_applicant   VARCHAR(256)           = NULL

    , @tx_account_no                    VARCHAR(256)           = NULL
    , @tx_bp_no                         VARCHAR(256)           = NULL
    , @tx_nid                           VARCHAR(256)           = NULL
    , @tx_phone                         VARCHAR(256)           = NULL
    , @tx_from_date                     VARCHAR(256)           = NULL
    , @tx_to_date                       VARCHAR(256)           = NULL
    , @tx_ui_action_name                VARCHAR(256)           = NULL
    , @tx_staffId                       VARCHAR(32)            = NULL
    , @tx_condition                     VARCHAR(MAX)            = NULL     OUTPUT
    , @loan_entry_time                  VARCHAR(32)            = NULL
    , @ppc_start_time                   VARCHAR(32)            = NULL
    , @crm_start_time                   VARCHAR(32)            = NULL
    , @crm_end_time                     VARCHAR(32)            = NULL
    , @cad_start_time                   VARCHAR(32)            = NULL
    , @cad_end_time                     VARCHAR(32)            = NULL
    , @dtt_re_submit_date                datetime            = NULL
    , @dec_gPF_amount             DECIMAL(20,2)          = NULL
    , @tx_loan_group_id                    VARCHAR(32)            = NULL

    _SP_PARAM_FOOTER

AS

{
    _SP_HEADER

    IF ( (@tx_action_name = 'LMS_HEAD_OFFICE_LOAN_PDF') OR (@tx_action_name = 'LMS_BRANCH_LOAN_PDF'))
    {   
        SELECT L.*
        INTO    #TEMP_LOAN_TABLE
        FROM    T_LOAN L
        WHERE   id_loan_key = @id_loan_key
        AND     is_active = 1

        SELECT  tx_rs_type = 'RS_TYPE_LOAN' ,TL.*
                , C.tx_value1 AS tx_loan_type 
                , S.tx_state_name
                , S.tx_display_text as tx_state_display_label
        FROM    #TEMP_LOAN_TABLE TL
        JOIN    T_FSM_STATE S ON TL.id_state_key = S.id_fsm_state_key
        JOIN    T_CONFIGURATION C ON C.id_configuration_key = TL.id_loan_type_key

        --SELECT @tx_condition = (SELECT tx_condition FROM #TEMP_LOAN_TABLE)
        SELECT tx_rs_type = 'RS_TYPE_CONDITION', tx_condition FROM #TEMP_LOAN_TABLE


        SELECT @id_customer_key = (SELECT id_customer_key FROM #TEMP_LOAN_TABLE)
        SELECT @id_customer_type_key = (SELECT id_customer_type_key FROM #TEMP_LOAN_TABLE)
        SELECT @id_loan_type_key = (SELECT id_loan_type_key FROM #TEMP_LOAN_TABLE)

        SELECT  tx_rs_type = 'RS_TYPE_CUSTOMER' , C.* 
                , 'Permanent Address: ' + C.tx_permanet_addr + ' Office Address : ' + c.tx_office_addr + '  Mobile : ' + C.tx_mobile + ', '+ C.tx_emer_phone AS tx_address_contact_details
               ,(SELECT tx_value1  FROM T_CONFIGURATION WHERE id_configuration_key = @id_customer_type_key) AS tx_customer_type
                ,TLP.tx_application_no      AS tx_application_no
                ,(SELECT tx_value1  FROM T_CONFIGURATION WHERE id_configuration_key = @id_loan_type_key) AS tx_loan_type
                ,TLP.tx_loan_purpose
                ,TLP.int_over_loan
                , (SELECT tx_staff_id FROM #TEMP_LOAN_TABLE) AS tx_staffId
                , (SELECT tx_sourcing_brc FROM #TEMP_LOAN_TABLE) AS tx_staff_branch_name
        FROM    T_CUSTOMER C
        JOIN    #TEMP_LOAN_TABLE TLP ON TLP.id_customer_key = C.id_customer_key
        JOIN    T_USER U ON TLP.id_creator_key = U.id_user_key
        JOIN    T_LEGAL_ENTITY L ON U.id_legal_entity_key =  L.id_legal_entity_key
        WHERE   C.id_customer_key =  @id_customer_key
        AND     C.is_active = 1


        SELECT  tx_rs_type = 'RS_TYPE_EXISTING_LIABILITY' , L.*
        INTO            #TEMP_EXIS_LIAB_TABLE
        FROM    T_EXISTING_LIABILITY L
        WHERE   id_loan_key = @id_loan_key
        AND     is_active = 1

        IF EXISTS (SELECT  1 FROM #TEMP_EXIS_LIAB_TABLE)
        {
            SELECT  * FROM #TEMP_EXIS_LIAB_TABLE
        }
        ELSE
        {
            SELECT tx_rs_type = 'RS_TYPE_EXISTING_LIABILITY' ,id_existing_liability_key = 0, dtt_mod = GETDATE(), id_loan_key = @id_loan_key, tx_bank_name = null, 
            tx_product_name = null, dec_disbursed = null, dec_current_outstanding = null,   dec_emi_size = null,    tx_remarks = null
        }
        
        SELECT tx_rs_type = 'RS_TYPE_TRACKING_ID' , TLTFB.* FROM #TEMP_LOAN_TABLE TLTFB

        SELECT  tx_rs_type = 'RS_TYPE_LOAN_TYPE' , CON.tx_value3 AS tx_loan_type
        FROM    T_CONFIGURATION CON 
            JOIN    #TEMP_LOAN_TABLE LN ON  LN.id_loan_type_key = CON.id_configuration_key 
            

        IF (@tx_action_name = 'LMS_HEAD_OFFICE_LOAN_PDF')
        { 

            SELECT  tx_rs_type = 'RS_TYPE_ANALYSTS_COMMENTS' , C.*
            INTO            #TEMP_ANALYSTS_COMMENTS
            FROM    T_COMMENT C
            WHERE   tx_object_type = 'LOAN'
            AND     tx_comment_type = 'ANALYSTS_COMMENTS'
            AND     id_ref_key = @id_loan_key
            AND     is_active = 1

            IF EXISTS (SELECT  1 FROM #TEMP_ANALYSTS_COMMENTS)
            {
                SELECT  * FROM #TEMP_ANALYSTS_COMMENTS
            }
            ELSE
            {
                SELECT tx_rs_type = 'RS_TYPE_ANALYSTS_COMMENTS', id_comment_key = 0, dtt_mod = GETDATE(), id_ref_key = @id_loan_key,
                 tx_comment = null, tx_commented_by = null,  tx_object_type = null, tx_comment_type = null
            }

            SELECT  tx_rs_type = 'RS_TYPE_EXCEPTION_DETAILS' , C.*
            INTO    #TEMP_EXCEPTION_DETAILS
            FROM    T_COMMENT C
            WHERE   tx_object_type = 'LOAN'
            AND     tx_comment_type = 'EXCEPTION_DETAILS'
            AND     id_ref_key = @id_loan_key
            AND     is_active = 1

            IF EXISTS (SELECT  1 FROM #TEMP_EXCEPTION_DETAILS)
            {
                SELECT  * FROM #TEMP_EXCEPTION_DETAILS
            }
            ELSE
            {
                SELECT tx_rs_type = 'RS_TYPE_EXCEPTION_DETAILS', id_comment_key = 0, dtt_mod = GETDATE(), id_ref_key = @id_loan_key,
                 tx_comment = null, tx_commented_by = null,  tx_object_type = null, tx_comment_type = null
            }

            SELECT  tx_rs_type = 'RS_TYPE_INSTRUCTION_TO_CAD' , C.*
            INTO    #TEMP_INSTRUCTION_TO_CAD
            FROM    T_COMMENT C
            WHERE   tx_object_type = 'LOAN'
            AND     tx_comment_type = 'INSTRUCTION_TO_CAD'
            AND     id_ref_key = @id_loan_key
            AND     is_active = 1

            IF EXISTS (SELECT  1 FROM #TEMP_INSTRUCTION_TO_CAD)
            {
                SELECT  * FROM #TEMP_INSTRUCTION_TO_CAD
            }
            ELSE
            {
                SELECT tx_rs_type = 'RS_TYPE_INSTRUCTION_TO_CAD', id_comment_key = 0, dtt_mod = GETDATE(), id_ref_key = @id_loan_key,
                 tx_comment = null, tx_commented_by = null,  tx_object_type = null, tx_comment_type = null
            }

            UPDATE #TEMP_LOAN_TABLE 
            SET tx_dob_of_pg_year = ''
            WHERE dtt_dob_of_pg IS NULL
            
            SELECT tx_rs_type = 'RS_TYPE_LOAN_2' , TL.* FROM #TEMP_LOAN_TABLE TL


            SELECT TOP 1 tx_rs_type = 'RS_TYPE_MANAGER', USR.tx_login_name AS tx_user_name, USR.tx_designation
            FROM T_LOAN_AUDIT AU join T_FSM_STATE ST ON ST.id_fsm_state_key=AU.id_state_key
            JOIN T_USER USR ON USR.id_user_key=AU.id_user_mod_key
            WHERE id_loan_key = @id_loan_key
            AND 
            (
                ST.tx_state_name = 'RM_APPROVED' 
                OR ST.tx_state_name = 'RM_RE_APPROVED' 
                OR ST.tx_state_name = 'UH_APPROVED'
                OR ST.tx_state_name = 'UH_RE_APPROVED' 
                OR 
                (
                    ST.tx_state_name = 'RM_RECOMMENDED' 
                    OR ST.tx_state_name = 'UH_RECOMMENDED'
                    AND 
                    (
                        SELECT tx_role_name FROM T_ROLE ROL 
                        WHERE ROL.id_role_key = AU.int_recommend_group_key
                    ) = 'HO_CRM'
                )
            )
            ORDER BY AU.dtt_mod DESC
        }
        ELSE IF ( @tx_action_name = 'LMS_BRANCH_LOAN_PDF' )
        {
            SELECT  tx_rs_type = 'RS_TYPE_LOAN_DOCUMENT' , D.*
            FROM    T_LOAN_DOCUMENT D
            WHERE   id_loan_key = @id_loan_key
            AND     is_active = 1

            SELECT  tx_rs_type = 'RS_TYPE_WAIVER_SOUGHT' , C.*
            INTO            #TEMP_WAIVER_SOUGHT
            FROM    T_COMMENT C
            WHERE   tx_object_type = 'LOAN'
            AND     tx_comment_type = 'COMMENTS_WAIVER_SOUGHT'
            AND     id_ref_key = @id_loan_key
            AND     is_active = 1

            IF EXISTS (SELECT  1 FROM #TEMP_WAIVER_SOUGHT)
            {
                SELECT  * FROM #TEMP_WAIVER_SOUGHT
            }
            ELSE
            {
                SELECT tx_rs_type = 'RS_TYPE_WAIVER_SOUGHT', id_comment_key = 0, dtt_mod = GETDATE(), id_ref_key = @id_loan_key,
                tx_comment = null, tx_commented_by = null,  tx_object_type = null, tx_comment_type = null
            }

            SELECT  tx_rs_type = 'RS_TYPE_COMMENTS_JUSTIFICATION' , C.*
            INTO            #TEMP_COMM_JUS
            FROM    T_COMMENT C
            WHERE   tx_object_type = 'LOAN'
            AND     tx_comment_type = 'COMMENTS_JUSTIFICATION'
            AND     id_ref_key = @id_loan_key
            AND     is_active = 1

            IF EXISTS (SELECT  1 FROM #TEMP_COMM_JUS)
            {
                SELECT  * FROM #TEMP_COMM_JUS
            }
            ELSE
            {
                SELECT tx_rs_type = 'RS_TYPE_COMMENTS_JUSTIFICATION', id_comment_key = 0, dtt_mod = GETDATE(), id_ref_key = @id_loan_key,
                tx_comment = null, tx_commented_by = null,  tx_object_type = null, tx_comment_type = null
            }

            SELECT  tx_rs_type = 'RS_TYPE_SO_RECOMM}ATION' , C.*
            INTO            #TEMP_SO_RECO
            FROM    T_COMMENT C
            WHERE   tx_object_type = 'LOAN'
            AND     tx_comment_type = 'SO_RECOMM}ATION'
            AND     id_ref_key = @id_loan_key
            AND     is_active = 1

            IF EXISTS (SELECT  1 FROM #TEMP_SO_RECO)
            {
                SELECT  * FROM #TEMP_SO_RECO
            }
            ELSE
            {
                SELECT tx_rs_type = 'RS_TYPE_SO_RECOMM}ATION', id_comment_key = 0, dtt_mod = GETDATE(), id_ref_key = @id_loan_key,
                tx_comment = null, tx_commented_by = null,  tx_object_type = null, tx_comment_type = null
            }

            SELECT  tx_rs_type = 'RS_TYPE_BM_RECOMM}ATION' , C.*
            INTO            #TEMP_BM_RECO
            FROM    T_COMMENT C
            WHERE   tx_object_type = 'LOAN'
            AND     tx_comment_type = 'BM_RECOMM}ATION'
            AND     id_ref_key = @id_loan_key
            AND     is_active = 1

            IF EXISTS (SELECT  1 FROM #TEMP_BM_RECO)
            {
                SELECT  * FROM #TEMP_BM_RECO
            }
            ELSE
            {
                SELECT tx_rs_type = 'RS_TYPE_BM_RECOMM}ATION', id_comment_key = 0, dtt_mod = GETDATE(), id_ref_key = @id_loan_key,
                tx_comment = null, tx_commented_by = null,  tx_object_type = null, tx_comment_type = null
            }

            SELECT @id_legal_entity_key = id_legal_entity_key FROM T_USER 
            WHERE id_user_key = (
                SELECT id_creator_key FROM T_LOAN_AUDIT WHERE id_loan_key = @id_loan_key AND id_loan_ver = 0
            ) 
            AND is_active = 1

            SELECT tx_rs_type = 'RS_TYPE_BRANCH_NAME_STUFF_ID'
            , tx_staff_id AS tx_staffId
            , tx_sourcing_brc AS tx_staff_branch_name 
            FROM T_LOAN
            WHERE id_loan_key = @id_loan_key
        } 
    } 

    IF(@tx_action_name ='LMS_MEMO_REPORT_PDF')
    {

        SELECT tx_rs_type = 'RS_TYPE_LOAN_VIEW_MEMO'
        , ROW_NUMBER() OVER (ORDER BY T.tx_loan_tracking_id) AS Sl_Number
        , T.tx_loan_tracking_id
        , C.tx_bp_no
        , C.tx_customer_name
        , C.tx_designation
        , T.dec_proposed_dbr
        , ( (convert(DECIMAL(10,2),(SUBSTRING(C.tx_remaining_year_of_retirement, CHARINDEX('y', C.tx_remaining_year_of_retirement)-2,2))))+
            (convert(DECIMAL(10,2),(SUBSTRING(C.tx_remaining_year_of_retirement, CHARINDEX(' ', C.tx_remaining_year_of_retirement)+1,1)))/12)
          ) as dec_remaining_year
        , (CASE 
             WHEN (C.int_salary_disbursed_with_cbbl = 1 AND C.int_salary_disbursed_with_cbbl = 1) THEN 1
             ELSE 0 
          END) as int_top_up_take_over
        ,( CASE 
             WHEN C.tx_district is not null  
                THEN C.tx_district
             ELSE C.tx_division
           END) AS tx_division 
        ,(
            CASE 
             WHEN (T.dec_recommended_for_approval=-2147483648.00)  THEN 0 
               
             ELSE T.dec_recommended_for_approval
            END
        )AS dec_recommended_for_approval

        FROM T_LOAN T
        JOIN T_CUSTOMER C ON T.id_customer_key=C.id_customer_key where tx_loan_group_id=@tx_loan_group_id;

        SELECT tx_rs_type = 'RS_TYPE_TOTAL_AMOUNT_FILE'
            , COUNT(T.tx_loan_tracking_id) AS TotalFile 
            ,SUM(CASE 
                 WHEN (T.dec_recommended_for_approval=-2147483648.00)  THEN 0 
                   
                 ELSE T.dec_recommended_for_approval
            END
            ) AS TotalAmount        
        FROM T_LOAN T
        JOIN T_CUSTOMER C ON T.id_customer_key=C.id_customer_key 
        where tx_loan_group_id = @tx_loan_group_id;
    }

    IF (@tx_action_name = 'LMS_LOAN_EXCEL_REPORT' OR @tx_action_name = 'PPC_EXCEL_REPORT')
    {

        IF (@tx_action_name = 'LMS_LOAN_EXCEL_REPORT')
        {
            IF((SELECT COUNT(AUD.dtt_mod) FROM T_LOAN_AUDIT AUD JOIN T_FSM_STATE ST ON ST.id_fsm_state_key=AUD.id_state_key WHERE AUD.id_loan_key = @id_loan_key AND ST.tx_state_name = 'SO_RECOMMENDED' ) > 1) 
            {
                SET @dtt_re_submit_date = (SELECT TOP 1 AUD.dtt_mod FROM T_LOAN_AUDIT AUD JOIN T_FSM_STATE ST ON ST.id_fsm_state_key=AUD.id_state_key WHERE AUD.id_loan_key = @id_loan_key AND ST.tx_state_name = 'SO_RECOMMENDED' ORDER BY AUD.dtt_mod DESC)
            }

            SELECT tx_rs_type = 'RS_TYPE_LMS_LOAN_EXCEL_REPORT'
            , L.*
            , (SELECT TOP 1 ST.tx_state_name FROM T_LOAN_AUDIT ADT JOIN T_FSM_STATE ST ON ST.id_fsm_state_key = ADT.id_state_key WHERE ADT.id_loan_key=@id_loan_key ORDER BY ADT.dtt_mod DESC) AS tx_loan_current_status
            , (SELECT TOP 1 CON.tx_value1 FROM T_CONFIGURATION CON  WHERE id_configuration_key = (SELECT top 1 T.id_loan_type_key FROM T_LOAN T WHERE id_loan_key = @id_loan_key ORDER BY T.dtt_mod DESC) ORDER BY CON.dtt_mod DESC) AS tx_loan_type
            , (SELECT TOP 1 AUD.dtt_mod from T_LOAN_AUDIT AUD JOIN T_FSM_STATE ST ON ST.id_fsm_state_key=AUD.id_state_key WHERE AUD.id_loan_key = @id_loan_key AND ST.tx_state_name IN('MIS_RECEIVED','CA_RECEIVED') ORDER BY AUD.dtt_mod DESC) AS dtt_crm_received_date
            , (SELECT TOP 1 U.tx_login_name FROM T_USER U JOIN T_LOAN_AUDIT A ON A.id_user_mod_key=U.id_user_key JOIN T_FSM_STATE ST ON ST.id_fsm_state_key=A.id_state_key WHERE A.id_loan_key=@id_loan_key AND st.tx_state_name='CA_RECOMMENDED' ORDER BY A.dtt_mod DESC) AS tx_analyst
            , (SELECT TOP 1 U.tx_first_name + ' ' + U.tx_last_name FROM T_USER U JOIN T_LOAN_AUDIT A ON A.id_user_mod_key=U.id_user_key JOIN T_FSM_STATE ST ON ST.id_fsm_state_key=A.id_state_key WHERE A.id_loan_key=@id_loan_key AND st.tx_state_name='SO_RECOMMENDED' ORDER BY A.dtt_mod DESC) AS tx_user_name 
            , (SELECT TOP 1 ADT.dtt_mod FROM T_LOAN_AUDIT ADT JOIN T_FSM_STATE STE ON ADT.id_state_key = STE.id_fsm_state_key WHERE id_loan_key = @id_loan_key AND STE.tx_state_name = 'CA_SENT_QUERY' ORDER BY ADT.dtt_mod DESC) AS dtt_of_query
            , (SELECT TOP 1 AUD.dtt_mod  FROM T_LOAN_AUDIT AUD JOIN T_FSM_STATE ST ON ST.id_fsm_state_key=AUD.id_state_key WHERE AUD.id_loan_key = @id_loan_key AND (ST.tx_state_name = 'BM_RETURNED' OR ST.tx_state_name = 'MIS_RETURNED') ORDER BY AUD.dtt_mod DESC)  AS dtt_return_to_source_date
            , (SELECT TOP 1 C.tx_comment FROM T_COMMENT C WHERE tx_object_type = 'LOAN' AND tx_comment_type = 'ANALYSTS_COMMENTS' AND id_ref_key = @id_loan_key AND is_active = 1 ORDER BY C.dtt_mod DESC) AS tx_analyst_comments
            , (SELECT TOP 1 AUD.dtt_mod from T_LOAN_AUDIT AUD JOIN T_FSM_STATE ST ON ST.id_fsm_state_key=AUD.id_state_key WHERE AUD.id_loan_key = @id_loan_key AND ST.tx_state_name like '%APPROVED%' ORDER BY AUD.dtt_mod DESC) AS dtt_approved_date
            , (SELECT TOP 1 AUD.dtt_mod from T_LOAN_AUDIT AUD JOIN T_FSM_STATE ST ON ST.id_fsm_state_key=AUD.id_state_key WHERE AUD.id_loan_key = @id_loan_key AND ST.tx_state_name='SENT_TO_CAD' ORDER BY AUD.dtt_mod DESC) AS dtt_sent_to_cad
            , (SELECT DISTINCT US.tx_cbs_user_id from T_USER US JOIN T_LOAN_AUDIT AUD  ON  AUD.id_creator_key = US.id_user_key WHERE AUD.id_loan_key = @id_loan_key) AS tx_cbs_user_id
            , (SELECT TOP 1 CON.tx_value1  FROM T_CONFIGURATION CON WHERE id_configuration_key = (SELECT TOP 1 AUD.id_customer_type_key FROM T_LOAN_AUDIT AUD WHERE id_loan_key = @id_loan_key ORDER BY AUD.dtt_mod DESC)ORDER BY CON.dtt_mod DESC) AS tx_customer_type
            , (SELECT DISTINCT US.tx_login_name from T_USER US JOIN T_LOAN_AUDIT AUD  ON  AUD.id_creator_key = US.id_user_key WHERE AUD.id_loan_key = @id_loan_key) AS tx_input_by
            FROM T_LOAN L 
            WHERE L.id_loan_key = @id_loan_key
        }

        ELSE IF (@tx_action_name = 'PPC_EXCEL_REPORT')
        {
            /*For Source TAT*/
            SET @loan_entry_time =(SELECT TOP 1 ADT.dtt_mod FROM  T_LOAN_AUDIT ADT JOIN T_FSM_STATE FS ON FS.id_fsm_state_key=ADT.id_state_key WHERE id_loan_key=@id_loan_key ORDER BY ADT.dtt_mod ASC) 
            SET @ppc_start_time =(SELECT TOP 1 adt.dtt_mod FROM  T_LOAN_AUDIT ADT JOIN T_FSM_STATE FS ON FS.id_fsm_state_key=ADT.id_state_key WHERE id_loan_key=@id_loan_key AND  FS.tx_state_name  = 'SO_RECOMMENDED' ORDER BY ADT.dtt_mod DESC) 

            /*For CRM TAT*/
            SET @crm_start_time =(SELECT TOP 1 ADT.dtt_mod FROM  T_LOAN_AUDIT ADT JOIN T_FSM_STATE FS ON FS.id_fsm_state_key=ADT.id_state_key WHERE id_loan_key=@id_loan_key AND  FS.tx_state_name  = 'PEND_RECEIVED') 
            SET @crm_end_time = (SELECT TOP 1 ADT.dtt_mod FROM  T_LOAN_AUDIT ADT JOIN T_FSM_STATE FS ON FS.id_fsm_state_key=ADT.id_state_key WHERE id_loan_key=@id_loan_key AND  FS.tx_state_name  NOT LIKE 'SO%' AND FS.tx_state_name  NOT LIKE 'FO%' AND FS.tx_state_name != 'SENT_TO_CAD' AND FS.tx_state_name != 'SL_GENERATED' ORDER BY ADT.dtt_mod DESC)

            /*For CAD TAT*/
            SET @cad_start_time =(SELECT TOP 1 ADT.dtt_mod FROM  T_LOAN_AUDIT ADT JOIN T_FSM_STATE FS ON FS.id_fsm_state_key=ADT.id_state_key WHERE id_loan_key=@id_loan_key AND  FS.tx_state_name  = 'SENT_TO_CAD') 
            SET @cad_end_time = (SELECT TOP 1 ADT.dtt_mod FROM  T_LOAN_AUDIT ADT JOIN T_FSM_STATE FS ON FS.id_fsm_state_key=ADT.id_state_key WHERE id_loan_key=@id_loan_key AND FS.tx_state_name = 'SENT_TO_CAD' OR FS.tx_state_name = 'SL_GENERATED' ORDER BY ADT.dtt_mod DESC)


            SELECT tx_rs_type = 'RS_TYPE_LMS_MIS_PPC_EXCEL_REPORT'
            ,L.*
            , (SELECT TOP 1 ST.tx_state_name FROM T_LOAN_AUDIT ADT JOIN T_FSM_STATE ST ON ST.id_fsm_state_key = ADT.id_state_key WHERE ADT.id_loan_key=@id_loan_key ORDER BY ADT.dtt_mod DESC) AS tx_loan_current_status
            , (SELECT DISTINCT L.tx_legal_entity_name FROM T_LEGAL_ENTITY L JOIN T_USER U ON U.id_legal_entity_key=L.id_legal_entity_key JOIN T_LOAN_AUDIT ADT ON ADT.id_creator_key=U.id_user_key WHERE ADT.id_loan_key=@id_loan_key) AS tx_legal_entity_name
            , (SELECT TOP 1 AUD.dtt_mod from T_LOAN_AUDIT AUD JOIN T_FSM_STATE ST ON ST.id_fsm_state_key=AUD.id_state_key WHERE AUD.id_loan_key = @id_loan_key AND ST.tx_state_name='SENT_TO_CAD' ORDER BY AUD.dtt_mod DESC) AS dtt_sent_to_cad
            , (SELECT TOP 1 AUD.dtt_mod from T_LOAN_AUDIT AUD JOIN T_FSM_STATE ST ON ST.id_fsm_state_key=AUD.id_state_key WHERE AUD.id_loan_key = @id_loan_key AND ST.tx_state_name IN('MIS_RECEIVED','CA_RECEIVED') ORDER BY AUD.dtt_mod DESC) AS dtt_crm_received_date            
            , (SELECT DISTINCT U.tx_login_name FROM  T_USER U JOIN T_LOAN_AUDIT ADT ON ADT.id_creator_key=U.id_user_key WHERE ADT.id_loan_key=@id_loan_key) AS tx_loan_creator
            , (SELECT TOP 1 CON.tx_value1 FROM T_CONFIGURATION CON  WHERE id_configuration_key = (SELECT top 1 T.id_loan_type_key FROM T_LOAN T WHERE id_loan_key = @id_loan_key ORDER BY T.dtt_mod DESC) ORDER BY CON.dtt_mod DESC) AS tx_loan_type
            , (SELECT TOP 1 AUD.dtt_mod from T_LOAN_AUDIT AUD JOIN T_FSM_STATE ST ON ST.id_fsm_state_key=AUD.id_state_key WHERE AUD.id_loan_key = @id_loan_key AND ST.tx_state_name='SO_RECOMMENDED' ORDER BY AUD.dtt_mod DESC) AS dtt_ppc_received_date
            , (SELECT TOP 1 AUD.dtt_mod from T_LOAN_AUDIT AUD JOIN T_FSM_STATE ST ON ST.id_fsm_state_key=AUD.id_state_key WHERE AUD.id_loan_key = @id_loan_key AND ST.tx_state_name='SENT_TO_CIB' ORDER BY AUD.dtt_mod DESC) AS dtt_sent_to_cib
            , (SELECT TOP 1 ADT.dtt_mod FROM  T_LOAN_AUDIT ADT WHERE id_loan_key=101164 AND ADT.tx_cib_status IS NOT NULL AND ADT.tx_cib_status !='?' ORDER BY ADT.dtt_mod DESC) AS dtt_cib_upload_date
            , (SELECT TOP 1 AUD.dtt_mod from T_LOAN_AUDIT AUD JOIN T_FSM_STATE ST ON ST.id_fsm_state_key=AUD.id_state_key WHERE AUD.id_loan_key = @id_loan_key AND ST.tx_state_name='PEND_RECEIVED' ORDER BY AUD.dtt_mod DESC) AS dtt_sent_to_crm
            , (SELECT DATEDIFF(minute, @loan_entry_time, @ppc_start_time)) AS tx_source_tat
            , (SELECT TOP 1 FS.tx_state_name FROM  T_LOAN_AUDIT ADT JOIN T_FSM_STATE FS ON FS.id_fsm_state_key=ADT.id_state_key WHERE id_loan_key=@id_loan_key AND  FS.tx_state_name NOT LIKE 'FO%' AND FS.tx_state_name NOT LIKE 'SO%' ORDER BY ADT.dtt_mod DESC) AS tx_crm_status
            , (SELECT DATEDIFF(minute, @crm_start_time, @crm_end_time)) AS tx_crm_tat
            , (SELECT TOP 1 FS.tx_state_name FROM  T_LOAN_AUDIT ADT JOIN T_FSM_STATE FS ON FS.id_fsm_state_key=ADT.id_state_key WHERE id_loan_key=@id_loan_key AND  (FS.tx_state_name  = 'SENT_TO_CAD' OR FS.tx_state_name = 'SL_GENERATED') ORDER BY ADT.dtt_mod DESC) AS tx_cad_satatus
            , (SELECT DATEDIFF(minute, @cad_start_time, @cad_end_time)) AS tx_cad_tat
            , (SELECT TOP 1 U.tx_first_name + ' ' + U.tx_last_name FROM T_USER U JOIN T_LOAN_AUDIT A ON A.id_user_mod_key=U.id_user_key JOIN T_FSM_STATE ST ON ST.id_fsm_state_key=A.id_state_key WHERE A.id_loan_key=@id_loan_key AND st.tx_state_name='SO_RECOMMENDED' ORDER BY A.dtt_mod DESC) AS tx_user_name  
            , (SELECT TOP 1 (CASE  WHEN C.tx_district is not null  THEN C.tx_district ELSE C.tx_division END) FROM T_CUSTOMER C JOIN T_LOAN A ON A.id_customer_key=C.id_customer_key  WHERE A.id_loan_key=@id_loan_key  ORDER BY A.dtt_mod DESC) AS tx_district_division                                  
            , (SELECT TOP 1 AUD.dtt_mod from T_LOAN_AUDIT AUD JOIN T_FSM_STATE ST ON ST.id_fsm_state_key=AUD.id_state_key WHERE AUD.id_loan_key = @id_loan_key AND ST.tx_state_name like '%APPROVED%' ORDER BY AUD.dtt_mod DESC) AS dtt_approved_date
            , (SELECT TOP 1 AUD.dtt_mod from T_LOAN_AUDIT AUD JOIN T_FSM_STATE ST ON ST.id_fsm_state_key=AUD.id_state_key WHERE AUD.id_loan_key = @id_loan_key AND ST.tx_state_name IN('CAD_SENT_QUERY_TO_CA','CAD_SENT_QUERY_TO_SO') ORDER BY AUD.dtt_mod DESC) AS dtt_cad_quries_date
            FROM T_LOAN L
            WHERE L.id_loan_key = @id_loan_key

        }

        SELECT tx_rs_type = 'RS_TYPE_CUSTOMER'
        , C.*
        FROM T_LOAN L
        JOIN T_CUSTOMER C ON C.id_customer_key = L.id_customer_key 
        WHERE L.id_loan_key = @id_loan_key
        
    }

    _SP_FOOTER
}
go

_GRANT_PERM_SP