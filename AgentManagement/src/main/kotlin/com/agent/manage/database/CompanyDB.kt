package com.agent.manage.database

import com.agent.manage.data.Company
import java.io.*

class CompanyDB {
    companion object {
        val companyDB = HashMap<Int, Company>()

        val file = File("./managementfile/CompanyFile.dat")
        fun getCompanyFileDB(){
            BufferedReader(FileReader(file)).use { br ->
                br.lines().forEach {
                    val str = it.split(",")
                    var groups = listOf<String>()
                    if(str.size>3){
                        groups = str.subList(4, str.size)
                    }
                    val data = Company(str[1], str[2],str[3],groups)
                    companyDB.put(str[0].toInt(), data)
                }
            }
        }

        fun updateCompanyFileDB() {
//            println("CompanyDB updateCompanyFileDB $companyDB")
            var fileOut = BufferedWriter(FileWriter("./managementfile/CompanyFile.dat"))
            with(fileOut) {
                for (company in companyDB) {
                    var groupStr = ""
                    for (index in company.value.group!!.indices) {
                        if (index != 0) {
                            groupStr += ","
                        }
                        groupStr += company.value.group!![index]
                    }
                    write("${company.key},${company.value.name},${company.value.address},${company.value.contactNumber},${groupStr}\n")
                    flush()
                }
                close()
            }
        }
    }
}