package com.sample.kintai.api;

import com.sample.kintai.service.NyuryokuService;
import com.sample.kintai.service.SyukeiService;

public class KintaiApi {
    public static void main(String[] args) {
        Verify verify = new Verify();
        verify.inputCheck(args);
        try {
            String methodType = args[0];
            if ("input".equals(methodType)) {
                NyuryokuService service = new NyuryokuService();
                service.nyuryoku(args);
            } else if ("total".equals(methodType)) {
                String yearMonth = args[1];
                SyukeiService service = new SyukeiService();
                service.syukei(args,yearMonth);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
