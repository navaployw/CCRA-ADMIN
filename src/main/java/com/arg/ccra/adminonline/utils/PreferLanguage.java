/**
 * Author(s): Thachapon
 * Date:      13 July 2004
 * Copyright Notice:
 *     Copyright 2003 Advanced Research Group Co., Ltd. All rights reserved.
 *     ARG PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Created on 26 ??.?. 2547
 *
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.arg.ccra.adminonline.utils;

import java.io.Serializable;

import java.util.Locale;

/**
 * @author Administrator
 *
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class PreferLanguage
    implements Serializable
{
    //~ Static fields/initializers ---------------------------------------------

    /**
     * DOCUMENT ME!
     */
    public static PreferLanguage ENGLISH =
        new PreferLanguage(InquiryConstants.DOCUMENT_LANGUAGE.ENGLISH);

    /**
     * DOCUMENT ME!
     */
    public static PreferLanguage CHINESE =
        new PreferLanguage(InquiryConstants.DOCUMENT_LANGUAGE.LOCAL);

    //~ Instance fields --------------------------------------------------------

    /**
     * DOCUMENT ME!
     */
    private String preferLang = InquiryConstants.DOCUMENT_LANGUAGE.ENGLISH;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new PreferLanguage object.
     *
     * @param preferLang DOCUMENT ME!
     */
    public PreferLanguage(String preferLang)
    {
        this.preferLang = preferLang;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param obj DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean equals(Object obj)
    {
        if (null == obj)
        {
            return false;
        }

        return obj.equals(preferLang);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getLabel()
    {
        if (InquiryConstants.DOCUMENT_LANGUAGE.LOCAL.equals(preferLang))
        {
            return InquiryConstants.DOCUMENT_LANGUAGE.LOCAL;
        }

        return InquiryConstants.DOCUMENT_LANGUAGE.ENGLISH;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Locale getLocale()
    {
        if (InquiryConstants.DOCUMENT_LANGUAGE.LOCAL.equals(preferLang))
        {
            return Locale.TRADITIONAL_CHINESE;
        }

        return Locale.ENGLISH;
    }
}
