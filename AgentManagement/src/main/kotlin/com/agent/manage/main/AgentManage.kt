package com.agent.manage.main

import com.agent.manage.ConsoleReader
import com.agent.manage.database.CompanyDB.Companion.getCompanyFileDB
import com.agent.manage.database.IdolDB.Companion.getIdolFileDB
import com.agent.manage.management.company.showCompanyMenu

fun startManage() {
    getCompanyFileDB()
    getIdolFileDB()
    while (true) {
        println("+++++++++++++++++++++++++++++++++++++++++++++++++")
        println("전체 메뉴:    1. 회사 관리    2. 아이돌 관리   3. 행사관리")
        println("+++++++++++++++++++++++++++++++++++++++++++++++++")
        var line: String?
        line = ConsoleReader.consoleScanner()
        val menu = line.trim().toInt()
        when (menu) {
            // 회사 관리
            1 -> {
                showCompanyMenu()
            }
            // 아이돌 관리
            2 -> {
//                showIdolMenu()
            }
            // 행사 관리
            3 -> {

            }
        }
    }
}

fun main() {
    startManage()
}