package com.ziroom.newvideoinfomation.base;

import com.ziroom.http.request.host.IHost;

/**
 * Author:关震
 * Date:2020/4/11 13:16
 * Description:HostManager
 **/
public interface HostManager {
    IHost jjHost = new IHost() {
        @Override
        public String getHost() {
            return "http://v.juhe.cn";
        }

        @Override
        public String getDefaultPath() {
            return "/joke/content/list.php";
        }
    };
}
