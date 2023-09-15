package com.agent.manage.company

import com.agent.manage.ConsoleReader
import com.agent.manage.data.CompanyInfo
import java.io.*

class CompanyManage {
    var companyList = mutableListOf<CompanyInfo>()

    init {
        val file = File("./managementfile/CompanyFile.dat")
        try {
            BufferedReader(FileReader(file)).use { br ->
                br.lines().forEach {
                    val str = it.split(",")
//                    val memberList = str.subList(3, str.size)
                    companyList.add(CompanyInfo(str[0], str[1], str[2]))
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun getCompanies(): List<CompanyInfo> {
        companyList.clear()
        val file = File("./managementfile/CompanyFile.dat")
        try {
            BufferedReader(FileReader(file)).use { br ->
                br.lines().forEach {
                    val str = it.split(",")
//                    val memberList = str.subList(3, str.size)
                    companyList.add(CompanyInfo(str[0], str[1], str[2]))
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return companyList
    }

    fun addCompany() {
        println("회사명,주소,전화번호,소속 아이돌 형식으로 입력해주세요.")
        var line: String?
        line = ConsoleReader.consoleScanner()
        if (!line.isNullOrEmpty()) {
            val companyInfo = line.split(',')
//            val idolList = companyInfo.subList(3, companyInfo.size)
            companyList.add(CompanyInfo(companyInfo[0], companyInfo[1], companyInfo[2]))
            updateFile()
        }
    }

    fun searchCompany() {
        print("회사명을 입력해주세요: ")
        var line: String?
        line = ConsoleReader.consoleScanner()
        if (!line.isNullOrEmpty()) {
            for (index in companyList.indices) {
                if (line == companyList[index].name) {
                    println("[$line 검색 결과]\n회사명: ${companyList[index].name}\n주소: ${companyList[index].address}\n연락처: ${companyList[index].contactNumber}\n소속 그룹: ${companyList[index].group}")
                }
            }
        }
    }

    fun deleteCompany(){
        print("삭제할 회사명을 입력해주세요: ")
        var line: String?
        line = ConsoleReader.consoleScanner()
        if (!line.isNullOrEmpty()) {
            for (index in companyList.indices) {
                if (line.equals(companyList[index].name)) {
                    companyList.removeAt(index)
                }
            }
        }
        updateFile()
    }

    fun updateCompany(){
        print("수정할 회사명을 입력해주세요: ")
        var companyName: String?
        companyName = ConsoleReader.consoleScanner()
        val line = ConsoleReader.consoleScanner()
        val str = line.split(",")
        if (!line.isNullOrEmpty()) {
            for (index in companyList.indices) {
                if (companyName == companyList[index].name) {
                    companyList[index] = CompanyInfo(str[0],str[1],str[2])
                }
            }
        }
        updateFile()
    }

    fun updateFile() {
        var fileOut = BufferedWriter(FileWriter("./managementfile/CompanyFile.dat"))
        with(fileOut) {
            for (index in companyList.indices) {
                write("${companyList[index].name},${companyList[index].address},${companyList[index].contactNumber}\n")
                flush()
            }
        }
    }
}