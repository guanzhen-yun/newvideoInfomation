package com.god.plugin

import com.tinify.Tinify
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.text.SimpleDateFormat

class TinyPngTask extends DefaultTask {

    def TinyPngInfo info

    public TinyPngTask() {
        info = project.tinyInfo
    }

    //相当于Task的执行入口
    @TaskAction
    void doAction() {
        //如何来做自动化图片压缩
        /**
         * 1.使用Tiny-java库做图片压缩算法
         * 2.这个插件类似一个工具类，有输入和输出
         * 3.使用Tiny-Java Api 做图片压缩
         */
        ArrayList<String> srcList = info.resourcesSrc
        String apiKey = info.apiKey
        for (int i = 0; i < srcList.size(); i++) {
            String src = srcList.get(i)
            File fileDir = new File(project.projectDir, src)
            println(project.projectDir)
            if(fileDir.exists() && fileDir.isDirectory()) {
                //判断文件是否存在及是否是文件夹
                File[] files = fileDir.listFiles()
                File ff = new File(project.projectDir, "pngfile.txt")
                if(!ff.exists()) {
                    ff.createNewFile()
                }
                FileWriter writer=new FileWriter(ff.getName())
                writer.write("fileNames:")
                for (int j = 0; j < files.size(); j++) {
                    File file = files[j]
                    String fileName = file.getName()
                    writer.write(fileName)
                    writer.write(",")
//                    String filePath = file.getPath()
//                    //使用TinyPng的Api
//                    Tinify.setKey(apiKey)
//                    Tinify.fromFile(filePath).toFile(filePath);
//                    long time = file.lastModified()
//                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                    Calendar cal = Calendar.getInstance()
//                    cal.setTimeInMillis(time)
//                    println("修改时间[2] " + formatter.format(cal.getTime()))
//                    println("图片压缩完成")
                }
                writer.close()
            }
        }
    }
}