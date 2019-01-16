package com.structure.app.structuremvp.utils;

import android.Manifest;

/**
 * Created by ankurrawal on 22/8/18.
 */
public interface AppUtils {

    String[] STORAGE_PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    String[] STORAGE_CAMERA_PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    String[] LOCATION_PERMISSIONS = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    /**
     * login keys
     */
    int empty_id = 101;
    int invalid_mail = 102;
    int empty_password = 103;
    int empty_old_password = 104;
    int empty_confirm_password = 105;
    int match_confirm_password = 106;

    /**
     * Intent request code
     */
    int REQUEST_CODE_CAMERA = 202;
    int SERVER_ERROR = 203;
    int NO_INTERNET=204;
    int STATUS_SCAN_CODE = 205;
    /**
     * dialog keys
     */
    int dialog_ok_click = 301;
    int dialog_request_succes = 301;
    int dialog_ok_to_finish = 302;

    /**
     * api status code
     */
    String STATUS_FAIL = "0";
    String STATUS_SUCCESS = "1";





}
