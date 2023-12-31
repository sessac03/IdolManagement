package com.agent.manage.management.company

import com.agent.manage.util.ConsoleReader

fun showCompanyMenu() {
    val companyManage = CompanyManageFun()

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
                println("[조회 결과]")
                println("-----------------------------------")
                companyManage.getCompanies()
                break
            }
            // 회사 등록
            2 -> {
                println("회사명,주소,전화번호 형식으로 입력해주세요.")
                companyManage.addCompany()
                break
            }
            // 회사 검색
            3 -> {
                print("회사명을 입력해주세요: ")
                companyManage.searchCompany()
                break
            }

            4 -> {
                print("수정할 회사명을 입력해주세요: ")
                companyManage.updateCompany()
                break
            }
            5 -> {
                print("삭제할 회사명을 입력해주세요: ")
                companyManage.deleteCompany()
                break
            }
            6 -> break
        }
    }
}