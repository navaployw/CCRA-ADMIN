package com.arg.ccra.adminonline.utils;

public interface InquiryConstants
{
    //~ Inner Classes ----------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @author <a href="mailto:$author$">$author$</a>
     * @version $Revision: 2.0 $
      */
    public static class ALERT_CONSTANTS
    {
        //~ Static fields/initializers -----------------------------------------

        /**
         * DOCUMENT ME!
         */
        public static final String ENQUIRY_ALERT = "E";

        /**
         * DOCUMENT ME!
         */
        public static final String MONITORING_ALERT = "M";
    }

    /**
     * DOCUMENT ME!
     *
     * @author <a href="mailto:$author$">$author$</a>
     * @version $Revision: 2.0 $
      */
    public static class BASE_REASONCODE
    {
        //~ Static fields/initializers -----------------------------------------

        /**
         * DOCUMENT ME!
         */
        public static final String DISCHARGECODE = "C";

        /**
         * DOCUMENT ME!
         */
        public static final String DELETECODE = "D";

        /**
         * DOCUMENT ME!
         */
        public static final String INQUIRYCODE = "I";

        //~ Inner Classes ------------------------------------------------------

        /**
         * DOCUMENT ME!
         *
         * @author <a href="mailto:$author$">$author$</a>
         * @version $Revision: 2.0 $
          */
        public static class DELETECODES
        {
        }

        /**
         * DOCUMENT ME!
         *
         * @author <a href="mailto:$author$">$author$</a>
         * @version $Revision: 2.0 $
          */
        public static class DISCHARGECODES
        {
            //~ Static fields/initializers -------------------------------------

            /**
             * DOCUMENT ME!
             */
            public static final int DEFAULT = 100;
        }

        /**
         * DOCUMENT ME!
         *
         * @author <a href="mailto:$author$">$author$</a>
         * @version $Revision: 2.0 $
          */
        public static class INQUIRYCODES
        {
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @author <a href="mailto:$author$">$author$</a>
     * @version $Revision: 2.0 $
      */
    public static class CHARSETS
    {
        //~ Static fields/initializers -----------------------------------------

        /**
         * DOCUMENT ME!
         */
        public static final String UTF8 = "UTF-8";

        /**
         * DOCUMENT ME!
         */
        public static final String UTF16 = "UTF-16";

        /**
         * DOCUMENT ME!
         */
        public static final String ISO88591 = "ISO-8859-1";
    }

    /**
     * DOCUMENT ME!
     *
     * @author <a href="mailto:$author$">$author$</a>
     * @version $Revision: 2.0 $
      */
    public static class DOCUMENT_LANGUAGE
    {
        //~ Static fields/initializers -----------------------------------------

        /**
         * DOCUMENT ME!
         */
        public static final String ENGLISH = "E";

        /**
         * DOCUMENT ME!
         */
        public static final String LOCAL = "L";
    }

    /**
     * DOCUMENT ME!
     *
     * @author <a href="mailto:$author$">$author$</a>
     * @version $Revision: 2.0 $
      */
    public static class FLAGEXPENSE
    {
        //~ Static fields/initializers -----------------------------------------

        /**
         * DOCUMENT ME!
         */
        public static final String CREATE_REPORT = "C";

        /**
         * DOCUMENT ME!
         */
        public static final String DROP_SEARCH = "D";
    }

    /**
     * DOCUMENT ME!
     *
     * @author <a href="mailto:$author$">$author$</a>
     * @version $Revision: 2.0 $
      */
    public static class OBJECT_TYPE
    {
        //~ Static fields/initializers -----------------------------------------

        /**
         * DOCUMENT ME!
         */
        public static final int SEARCH = 47;

        /**
         * DOCUMENT ME!
         */
        public static final int BASIC_REPORT = 50;

        /**
         * DOCUMENT ME!
         */
        public static final int CHINESE_REPORT = 51;

        /**
         * DOCUMENT ME!
         */
        public static final int MONITORING_ALERT = 54;

        /**
         * DOCUMENT ME!
         */
        public static final int ENQUIRY_ALERT = 56;

        /**
         * DOCUMENT ME!
         */
        public static final int SUBSCRIBE_MONITORING_ALERT = 55;

        /**
         * DOCUMENT ME!
         */
        public static final int SUBSCRIBE_ENQUIRY_ALERT = 57;
    }

    /**
     * DOCUMENT ME!
     *
     * @author <a href="mailto:$author$">$author$</a>
     * @version $Revision: 2.0 $
      */
    public static class PRODUCT_DATA_CONSTANTS
    {
        //~ Static fields/initializers -----------------------------------------

        /**
         * DOCUMENT ME!
         */
        public static final int EXECUTIVE_SUMMARY = 2;

        /**
         * DOCUMENT ME!
         */
        public static final int EXECUTIVE_SUMMARY_EN = 3;

        /**
         * DOCUMENT ME!
         */
        public static final int EXECUTIVE_SUMMARY_LO = 4;

        /**
         * DOCUMENT ME!
         */
        public static final int PROFILE = 5;

        /**
         * DOCUMENT ME!
         */
        public static final int PROFILE_EN = 6;

        /**
         * DOCUMENT ME!
         */
        public static final int PROFILE_LO = 7;

        /**
         * DOCUMENT ME!
         */
        public static final int COMPANY_GROUP_STRUCTURE = 8;

        /**
         * DOCUMENT ME!
         */
        public static final int COMPANY_GROUP_STRUCTURE_EN = 9;

        /**
         * DOCUMENT ME!
         */
        public static final int COMPANY_GROUP_STRUCTURE_LO = 10;

        /**
         * DOCUMENT ME!
         */
        public static final int COURT = 11;

        /**
         * DOCUMENT ME!
         */
        public static final int COURT_EN = 12;

        /**
         * DOCUMENT ME!
         */
        public static final int COURT_LO = 13;

        /**
         * DOCUMENT ME!
         */
        public static final int REVOCATION_OF_CONSENT = 14;

        /**
         * DOCUMENT ME!
         */
        public static final int REVOCATION_OF_CONSENT_EN = 15;

        /**
         * DOCUMENT ME!
         */
        public static final int REVOCATION_OF_CONSENT_LO = 16;

        /**
         * DOCUMENT ME!
         */
        public static final int POSITIVE_LOAN_DATA = 18;

        /**
         * DOCUMENT ME!
         */
        public static final int NEGATIVE_LOAN_DATA = 19;

        /**
         * DOCUMENT ME!
         */
        public static final int WRITEOFF = 20;

        /**
         * DOCUMENT ME!
         */
        public static final int AI_ORDERED_LIST = 23;
    }

    /**
     * DOCUMENT ME!
     *
     * @author <a href="mailto:$author$">$author$</a>
     * @version $Revision: 2.0 $
      */
    public static class REASON_CODE
    {
        //~ Static fields/initializers -----------------------------------------

        /**
         * DOCUMENT ME!
         */
        public static final int NEW_REASON = 1;

        /**
         * DOCUMENT ME!
         */
        public static final int ANNUAL_REVIEW_REASON = 2;

        /**
         * DOCUMENT ME!
         */
        public static final int REQUEST_ADJUSTMENT_REASON = 3;

        /**
         * DOCUMENT ME!
         */
        public static final int ACCOUNT_MONITORING_REASON = 4;

        /**
         * DOCUMENT ME!
         */
        public static final int GUANRANTOR_REASON = 5;
    }

    /**
     * DOCUMENT ME!
     *
     * @author <a href="mailto:$author$">$author$</a>
     * @version $Revision: 2.0 $
      */
    public static class REQUESTTYPE
    {
        //~ Static fields/initializers -----------------------------------------

        /**
         * DOCUMENT ME!
         */
        public static final String ONLINE = "O";

        /**
         * DOCUMENT ME!
         */
        public static final String BULK = "B";
    }

    /**
     * DOCUMENT ME!
     *
     * @author <a href="mailto:$author$">$author$</a>
     * @version $Revision: 2.0 $
      */
    public static class SEARCH_ID_TYPE
    {
        //~ Static fields/initializers -----------------------------------------

        /**
         * DOCUMENT ME!
         */
        public static final int BRC = 1;

        /**
         * DOCUMENT ME!
         */
        public static final int CI = 2;

        /**
         * DOCUMENT ME!
         */
        public static final int OTHER_REG = 3;
    }

    /**
     * DOCUMENT ME!
     *
     * @author <a href="mailto:$author$">$author$</a>
     * @version $Revision: 2.0 $
      */
    public static class STR_SEPARATOR
    {
        //~ Static fields/initializers -----------------------------------------

        /**
         * DOCUMENT ME!
         */
        public static final String FIELD_SEPARATOR = "|";

        /**
         * DOCUMENT ME!
         */
        public static final String ROW_SEPARATOR = "\t";
    }
}
