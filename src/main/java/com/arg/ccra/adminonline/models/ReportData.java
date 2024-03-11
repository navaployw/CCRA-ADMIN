package com.arg.ccra.adminonline.models;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.arg.ccra.adminonline.utils.InquiryConstants;
import com.arg.ccra.adminonline.utils.Serializer;

public abstract class ReportData
    implements Serializable
    {
    //~ Static fields/initializers ---------------------------------------------

    /**
     * DOCUMENT ME!
     */
    private static final int MAX_OBJECT_LENGTH = 4000;

    //~ Instance fields --------------------------------------------------------

    /**
     * DOCUMENT ME!
     */
    protected String documentLanguage =
        InquiryConstants.DOCUMENT_LANGUAGE.ENGLISH;

    //~ Constructors -----------------------------------------------------------

    /**
     * @param documentLanguage
     */
    public ReportData(String documentLanguage)
    {
        this.documentLanguage = documentLanguage;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @param
     * @return
     */
    public String[] convertToStream()
    {
        String stream = null;

        try
        {
            stream = Serializer.objectAsString(this);

            /* REMOVE_ME: This is for debug purpose */
            /*
            try
            {
                // verify serialized report data
                Serializer.deserialize(stream);
            }
            catch (Exception e)
            {
                e.printStackTrace();

                throw new RuntimeException("Verification of ReportData Serialization failed.", e);
            }
            */
        }
        catch (IOException ignore)
        {
            ignore.printStackTrace();
        }

        if ((null == stream) || (stream.length() == 0))
        {
            return null;
        }

        List serializedData = new ArrayList<>();
        int currPos = 0;
        int objLength = stream.length();

        do
        {
            serializedData.add(stream.substring(currPos,
                    ((currPos + MAX_OBJECT_LENGTH) > objLength) ? objLength
                                                                : (currPos
                    + MAX_OBJECT_LENGTH)));
            currPos += MAX_OBJECT_LENGTH;
        }
        while (currPos <= objLength);

        return (String[]) serializedData.toArray(new String[0]);
    }
}