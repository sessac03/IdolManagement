package com.agent.manage.company

import com.agent.manage.ConsoleReader

fun showCompanyMenu() {
    val companyManage = CompanyManage()

    while (true) {
        println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        println("회사 관리 메뉴:    1. 회사 조회    2. 회사 등록    3. 회사 검색    4. 회사 수정    5. 회사 삭제    6. 뒤로가기")
        println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        var line: String?
        line = ConsoleReader.consoleScanner()
        val menu = line.trim().toInt()
        when (menu) {
            // 회사 조회
            1 -> {
                val companyList = companyManage.getCompanies()
                println("[조회 결과]")
                for (index in companyList.indices) {
                    println("회사명: ${companyList[index].name}\n주소: ${companyList[index].address}\n연락처: ${companyList[index].contactNumber}\n소속 그룹: ${companyList[index].group}")
                    println("------------------------------")
                }
                break
            }
            // 회사 등록
            2 -> {
                companyManage.addCompany()
                break
            }
            // 회사 검색
            3 -> {
                companyManage.searchCompany()
                break
            }

            4 -> {
                companyManage.updateCompany()
                break
            }
            5 -> {
                companyManage.deleteCompany()
                break
            }
            6 -> break
        }
    }
}