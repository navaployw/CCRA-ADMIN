package com.arg.ccra.adminonline.models;

import java.util.Locale;

import com.arg.ccra.adminonline.utils.InquiryConstants;

public class DocumentLanguage
{
    //~ Static fields/initializers ---------------------------------------------

    /**
     * DOCUMENT ME!
     */
    public static DocumentLanguage ENGLISH =
        new DocumentLanguage(InquiryConstants.DOCUMENT_LANGUAGE.ENGLISH);

    /**
     * DOCUMENT ME!
     */
    public static DocumentLanguage CHINESE =
        new DocumentLanguage(InquiryConstants.DOCUMENT_LANGUAGE.LOCAL);

    //~ Instance fields --------------------------------------------------------

    /**
     * DOCUMENT ME!
     */
    private String docLang = InquiryConstants.DOCUMENT_LANGUAGE.ENGLISH;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new DocumentLanguage object.
     *
     * @param docLang DOCUMENT ME!
     */
    public DocumentLanguage(String docLang)
    {
        this.docLang = docLang;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Locale getLocale()
    {
        if (InquiryConstants.DOCUMENT_LANGUAGE.LOCAL.equals(docLang))
        {
            return Locale.CHINESE;
        }

        return Locale.ENGLISH;
    }
}
