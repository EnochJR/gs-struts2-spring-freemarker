package com.tom.filter;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 * Created by tom on 2016/5/11.
 */
public class WebConstants {
    public static final String SESSION_CURRENT_USER = "SESSION_CURRENT_USER";//save current user
    public static final String SESSION_ACCESS_RIGHT_LIST = "SESSION_ACCESS_RIGHT_LIST";
    public static final String SA = "SA";
    public static String WEBSITE_ROOT = new File(FilenameUtils.getPathNoEndSeparator(WebConstants.class.getClassLoader().getResource("com/tom/web/config").getFile())).getParentFile().getParent();

}
