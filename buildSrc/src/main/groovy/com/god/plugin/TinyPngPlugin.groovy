package com.god.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class TinyPngPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.extensions.create("tinyInfo", TinyPngInfo)

        project.tasks.create("tinyPng", TinyPngTask)

        //自定义Task的执行逻辑
        project.afterEvaluate {

        }
    }
}