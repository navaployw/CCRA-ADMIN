/**
 * Author(s): Thachapon
 * Date:      13 July 2004
 * Copyright Notice:
 *     Copyright 2003 Advanced Research Group Co., Ltd. All rights reserved.
 *     ARG PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.arg.ccra.adminonline.models;

import com.arg.ccra.adminonline.utils.GenericException;

/**
 * @author Administrator
 *
 * Window - Preferences - Java - Code Generation - Code
 *         and Comments
 */
public class DateFormatException
    extends GenericException
{
    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new DateFormatException object.
     */
    public DateFormatException()
    {
        super();
    }

    /**
     * Creates a new DateFormatException object.
     *
     * @param message DOCUMENT ME!
     */
    public DateFormatException(String message)
    {
        super(message);
    }

    /**
     * Creates a new DateFormatException object.
     *
     * @param errCode DOCUMENT ME!
     * @param message DOCUMENT ME!
     */
    public DateFormatException(String errCode, String message)
    {
        super(errCode, message);
    }

    /**
     * Creates a new DateFormatException object.
     *
     * @param message DOCUMENT ME!
     * @param cause DOCUMENT ME!
     */
    public DateFormatException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Creates a new DateFormatException object.
     *
     * @param errCode DOCUMENT ME!
     * @param message DOCUMENT ME!
     * @param cause DOCUMENT ME!
     */
    public DateFormatException(String errCode, String message, Throwable cause)
    {
        super(errCode, message, cause);
    }

    /**
     * Creates a new DateFormatException object.
     *
     * @param cause DOCUMENT ME!
     */
    public DateFormatException(Throwable cause)
    {
        super(cause);
    }
}
