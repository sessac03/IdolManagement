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
                    var members = listOf<String>()
                    if(str.size>3){
                        members = str.subList(4, str.size)
                    }
                    val data = IdolGroup(str[1], str[2], str[3].toInt(), members, null)
                    idolDB.put(str[0].toInt(), data)
                }
            }
            //TODO NULL 처리
        }

        fun updateIdolFileDB() {
            println("IodlDB updateIodlFileDB $idolDB")
            var fileOut = BufferedWriter(FileWriter("./managementfile/IdolFile.dat"))
            with(fileOut) {
                for (idol in idolDB) {
                   var str = ""
                        for (index in idol.value.members!!.indices) {
                            if(index!=0){
                                str += ","
                            }
                            str += idol.value.members!![index]
                        }
                    println(str)
                    write("${idol.key},${idol.value.company},${idol.value.name},${idol.value.count},${str}\n")
                    flush()
                }
                close()
            }
        }
    }
}