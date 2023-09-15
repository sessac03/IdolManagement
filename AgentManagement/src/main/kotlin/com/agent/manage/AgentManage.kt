package com.agent.manage

import java.io.File

fun startManage(){
    val fileOut = File("./managementfile/manage.dat").bufferedWriter(Charsets.UTF_8)
    println("++++++++++++++++++++++++++++++++++++++++++++++++")
    println("메뉴:    1. 회사 관리    2. 아이돌 관리   4. 행사관리")
    println("++++++++++++++++++++++++++++++++++++++++++++++++")

    var line: String?
    line = ConsoleReader.consoleLineInput()
    while (!line.isNotEmpty() && !line.equals("exit", true)){

    }
}

fun main(){
    startManage()
}