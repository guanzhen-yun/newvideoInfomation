package com.ziroom.newvideoinfomation.main.tools;

import androidx.annotation.IntDef;

import static com.ziroom.newvideoinfomation.main.tools.MainConstantTool.BEIJING;
import static com.ziroom.newvideoinfomation.main.tools.MainConstantTool.HANGZHOU;
import static com.ziroom.newvideoinfomation.main.tools.MainConstantTool.SHANGHAI;
import static com.ziroom.newvideoinfomation.main.tools.MainConstantTool.SHENZHEN;

/**
 * Author:关震
 * Date:2020/4/5 20:55
 * Description:MainConstantTool
 **/
@IntDef({SHANGHAI, HANGZHOU, BEIJING, SHENZHEN})
public @interface MainConstantTool {
    int SHANGHAI = 0;
    int HANGZHOU = 1;
    int BEIJING = 2;
    int SHENZHEN = 3;
}
