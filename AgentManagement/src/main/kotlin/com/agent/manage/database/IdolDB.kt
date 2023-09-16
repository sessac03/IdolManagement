package com.agent.manage.database

import com.agent.manage.data.IdolGroup
import java.io.*

class IdolDB {
    companion object {
        val idolDB = HashMap<Int, IdolGroup>()

        val file = File("./managementfile/IdolFile.dat")
        fun getIdolFileDB() {
            BufferedReader(FileReader(file)).use { br ->
                br.lines().forEach {
                    val str = it.split(",")
                    val members = str.subList(4, str.size)
                    val data = IdolGroup(str[1], str[2], str[3].toInt(), members, null)
                    idolDB.put(str[0].toInt(), data)
                }
            }
        }

//        fun updateIdolFileDB() {
//            println("CompanyDB updateCompanyFileDB $idolDB")
//            var fileOut = BufferedWriter(FileWriter("./managementfile/CompanyFile.dat"))
//            with(fileOut) {
//                for (index in idolDB.indices) {
//                    write("${idolDB[index].get("name")},${idolDB[index].get("address")},${idolDB[index].get("callNum")}\n")
//                    flush()
//                }
//                close()
//            }
//        }
    }
}