package com.agent.manage.management.company

import com.agent.manage.ConsoleReader
import com.agent.manage.database.CompanyDB.Companion.companyDB
import com.agent.manage.database.CompanyDB.Companion.idolDB
import com.agent.manage.database.CompanyDB.Companion.updateCompanyFileDB
import kotlin.math.abs
import kotlin.random.Random

class CompanyManageFun {
    fun getCompanies() {
        for (index in companyDB.indices) {
            println("이름: ${companyDB[index].get("name")}")
            println("주소: ${companyDB[index].get("address")}")
            println("연락처: ${companyDB[index].get("callNum")}")
            println("-----------------------------------")
        }
    }

    fun addCompany() {
        var line: String?
        line = ConsoleReader.consoleScanner()
        if (!line.isNullOrEmpty()) {
            val companyInfo = line.split(',')
            val companyHash = HashMap<String, String>()
            companyHash.put("id", "${abs(Random.nextInt())}")
            companyHash.put("name", companyInfo[0])
            companyHash.put("address", companyInfo[1])
            companyHash.put("callNum", companyInfo[2])
            companyDB.add(companyHash)
            println("AddCompany 결과: $companyDB")
            updateCompanyFileDB()
        }
    }

    fun searchCompany() {
        var companyName: String?
        companyName = ConsoleReader.consoleScanner()
        if (!companyName.isNullOrEmpty()) {
            var flag = false
            for (index in companyDB.indices) {
                if (companyName == companyDB[index].get("name")) {
                    println("[$companyName] 검색결과")
                    println("이름: ${companyDB[index].get("name")}")
                    println("주소: ${companyDB[index].get("address")}")
                    println("연락처: ${companyDB[index].get("callNum")}")
                    println("아이돌 리스트: ${idolDB}")
                    for (i in idolDB.indices) {
                        println("[소속 아이돌]")
                        if (companyName == idolDB[i].get("company")) {
                            print("${idolDB[i].get("name")} ")
                        }
                    }
                    flag = true
                }
            }
            if (flag == false) {
                println("존재하지 않는 회사입니다.")
            }
        }
    }

    fun updateCompany() {
        var companyName: String?
        companyName = ConsoleReader.consoleScanner()
        if (!companyName.isNullOrEmpty()) {
            var flag = false
            var idx = 0
            for (index in companyDB.indices) {
                if (companyName == companyDB[index].get("name")) {
                    idx = index
                    flag = true
                }
            }
            if (flag == false) {
                println("존재하지 않는 회사입니다.")
                return
            }
            println("회사명,주소,전화번호 형식으로 입력해주세요.")
            val newData = ConsoleReader.consoleScanner()
            if (!newData.isNullOrEmpty()) {
                val str = newData.split(",")
                companyDB[idx].replace("name", str[0])
                companyDB[idx].replace("address", str[1])
                companyDB[idx].replace("callNum", str[2])
            }
        }
        updateCompanyFileDB()
    }

    fun deleteCompany() {
        var line: String?
        line = ConsoleReader.consoleScanner()
        println("companyManageFun deleteCompany $companyDB")
        if (!line.isNullOrEmpty()) {
            var indexDelete = -1
            for (index in companyDB.indices) {
                println(line)
                if (line.equals(companyDB[index].get("name"))) {
                    indexDelete = index
                    break
                }
            }
            if (indexDelete != -1) {
                companyDB.removeAt(indexDelete)
                println("삭제 완료!")
            } else {
                println("존재하지 않는 회사입니다.")
            }
        }
        updateCompanyFileDB()
    }
}