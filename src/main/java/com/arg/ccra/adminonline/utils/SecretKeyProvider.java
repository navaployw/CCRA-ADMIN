package com.arg.ccra.adminonline.utils;

import org.springframework.beans.factory.annotation.Value;

public interface SecretKeyProvider {
    @Value("${com.arg.ccra.adminonline.utils.secretAlgor}")
    public static String PROVIDER_SECRET_KEY = "";
    @Value("${com.arg.ccra.adminonline.utils.secretAlgor}")
    public static String USER_CIPHER_ALGOR = "";
    @Value("${com.arg.ccra.adminonline.utils.userAlgor}")
    public static String USER_KEY_ALGOR = "";
    @Value("${com.arg.ccra.adminonline.utils.secretAlgor}")
    public static String USER_KEY = "";
}

