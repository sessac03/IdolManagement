package com.agent.manage.main

import com.agent.manage.util.ConsoleReader
import com.agent.manage.database.CompanyDB.Companion.getCompanyFileDB
import com.agent.manage.database.EventDB.Companion.getEventFileDB
import com.agent.manage.database.IdolDB.Companion.getIdolFileDB
import com.agent.manage.management.company.showCompanyMenu
import com.agent.manage.management.idol.showIdolMenu
import com.agent.manage.management.event.showEventMenu

fun startManage() {
    getCompanyFileDB()
    getIdolFileDB()
    getEventFileDB()
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
                showIdolMenu()
            }
            // 행사 관리
            3 -> {
                showEventMenu()
            }
        }
    }
}

fun main() {
    startManage()
}