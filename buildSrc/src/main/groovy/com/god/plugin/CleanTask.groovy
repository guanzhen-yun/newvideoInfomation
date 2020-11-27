package com.god.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class CleanTask extends DefaultTask {

    //相当于Task的执行入口
    @TaskAction
    void doAction() {
        println("wo shi cleanTask")
    }
}