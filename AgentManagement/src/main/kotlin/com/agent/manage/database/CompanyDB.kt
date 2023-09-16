package com.agent.manage.database

import com.agent.manage.data.CompanyInfo
import com.agent.manage.data.IdolGroup
import java.io.*

class CompanyDB {
    companion object {
        val companyDB = HashMap<Int, CompanyInfo>()

        val file = File("./managementfile/CompanyFile.dat")
        fun getCompanyFileDB(){
            BufferedReader(FileReader(file)).use { br ->
                br.lines().forEach {
                    val str = it.split(",")
                    val data = CompanyInfo(str[1], str[2],str[3])
                    companyDB.put(str[0].toInt(), data)
                }
            }
        }

        fun updateCompanyFileDB() {
//            println("CompanyDB updateCompanyFileDB $companyDB")
            var fileOut = BufferedWriter(FileWriter("./managementfile/CompanyFile.dat"))
            with(fileOut) {
                for (company in companyDB) {
                    write("${company.key},${company.value.name},${company.value.address},${company.value.contactNumber}\n")
                    flush()
                }
                close()
            }
        }
    }
}