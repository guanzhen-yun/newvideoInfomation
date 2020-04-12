package com.ziroom.http.annotation;

import androidx.annotation.IntDef;

import static com.ziroom.http.annotation.RequestMethod.Get;
import static com.ziroom.http.annotation.RequestMethod.Post;

/**
 * Author:关震
 * Date:2020/4/11 13:12
 * Description:RequestMethod
 **/
@IntDef({Get, Post})
public @interface RequestMethod {
    int Get = 1;
    int Post = 2;
}
