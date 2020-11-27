package com.god.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class Study implements Plugin<Project> {

    @Override
    void apply(Project project) {
        //插件的执行逻辑  gradlew cleanTask
        println("wo shi chajian")
        //配置Task 与 Plugin 绑定
        project.tasks.create("cleanTask", CleanTask)

        //自定义Task的执行逻辑
        project.afterEvaluate {
            Task clean = project.tasks.getByName("clean");
            if(clean != null) {
                clean.finalizedBy "cleanTask"
            }
        }
    }
}