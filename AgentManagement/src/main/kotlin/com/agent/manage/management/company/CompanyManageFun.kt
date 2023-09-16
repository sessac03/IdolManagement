package com.agent.manage.management.company

import com.agent.manage.data.CompanyInfo
import com.agent.manage.util.ConsoleReader
import com.agent.manage.data.IdolGroup
import com.agent.manage.database.CompanyDB.Companion.companyDB
import com.agent.manage.database.CompanyDB.Companion.updateCompanyFileDB
import com.agent.manage.database.IdolDB.Companion.idolDB
import com.agent.manage.util.RandomIdGenerator

class CompanyManageFun {
    fun getCompanies() {
        for (company in companyDB) {
            println("이름: ${company.value.name}")
            println("주소: ${company.value.address}")
            println("연락처: ${company.value.contactNumber}")
            val idolList = idolDB.filter {
                it.value.company == company.value.name
            }.toList()
            print("아이돌 리스트: ")
            idolList.forEach {
                print("${it.second.name} ")
            }
            println()
            println("-----------------------------------")
        }
    }

    fun addCompany() {
        var line: String?
        line = ConsoleReader.consoleScanner()
        if (!line.isNullOrEmpty()) {
            val str = line.split(',')
            val id = RandomIdGenerator.randomId
            val data = CompanyInfo(str[0], str[1], str[2])
            companyDB.put(id, data)
            println("AddCompany 결과: $companyDB")
            updateCompanyFileDB()
        }
    }

    fun searchCompany() {
        var companyName: String?
        companyName = ConsoleReader.consoleScanner()
        if (!companyName.isNullOrEmpty()) {
            var flag = false
            for (company in companyDB) {
                if (companyName.equals(company.value.name)) {
                    flag = true
                    println("[$companyName] 검색결과")
                    println("이름: ${company.value.name}")
                    println("주소: ${company.value.address}")
                    println("연락처: ${company.value.contactNumber}")
                    val idolList = idolDB.filter {
                        it.value.company == companyName
                    }.toList()
                    print("아이돌 리스트: ")
                    idolList.forEach {
                        print("${it.second.name} ")
                    }
                }
            }
            println()
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
            var companyKey = 0
            for (company in companyDB) {
                if (companyName.equals(company.value.name)) {
                    flag = true
                    companyKey = company.key
                    break
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
                val data = CompanyInfo(str[0], str[1], str[2])
                companyDB.put(companyKey, data)
                println("AddCompany 결과: $companyDB")
            }
        }
        updateCompanyFileDB()
    }

    fun deleteCompany() {
        var companyName: String?
        companyName = ConsoleReader.consoleScanner()
        if (!companyName.isNullOrEmpty()) {
            var flag = false
            for (company in companyDB) {
                if (companyName.equals(company.value.name)) {
                    flag = true
                    companyDB.remove(company.key)
                    break
                }
            }
            if(flag){
                println("삭제 완료!")
            } else {
                println("존재하지 않는 회사입니다.")
            }
        }
        updateCompanyFileDB()
    }
}