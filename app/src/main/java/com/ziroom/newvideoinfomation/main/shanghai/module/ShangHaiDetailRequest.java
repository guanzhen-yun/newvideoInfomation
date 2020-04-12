package com.ziroom.newvideoinfomation.main.shanghai.module;

import com.ziroom.http.request.IRequest;
import com.ziroom.http.annotation.RequestMethod;
import com.ziroom.newvideoinfomation.base.JHRequest;
import com.ziroom.newvideoinfomation.main.shanghai.dto.ShangHaiDetailBean;

/**
 * Author:关震
 * Date:2020/4/11 13:10
 * Description:ShangHaiDetailRequest
 **/
public interface ShangHaiDetailRequest {
    IRequest xiaoHuaRequest = JHRequest.sendHttp("/joke/content/list.php", RequestMethod.Get, ShangHaiDetailBean.class);
}
