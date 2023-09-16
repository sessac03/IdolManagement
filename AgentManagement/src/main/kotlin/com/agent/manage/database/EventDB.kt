package com.agent.manage.database

import com.agent.manage.data.Event
import java.io.*

class EventDB {
    companion object {
        val eventDB = HashMap<Int, Event>()

        val file = File("./managementfile/EventFile.dat")
        fun getEventFileDB() {
            BufferedReader(FileReader(file)).use { br ->
                br.lines().forEach {
                    val str = it.split(",")
                    val groups = str.subList(3, str.size)
                    val data = Event(str[1], str[2], groups)
                    eventDB.put(str[0].toInt(), data)
                }
            }
        }

        fun updateEventFileDB() {
            println("EventDB updateEventFileDB $eventDB")
            var fileOut = BufferedWriter(FileWriter("./managementfile/EventFile.dat"))

            with(fileOut) {
                eventDB.forEach {
                    var str: String = ""
                    for (index in it.value.castedGroup!!.indices) {
                        println("EventDB updateEventFileDB $str")
                        str = str.plus(",")
                        str = str.plus(it.value.castedGroup!![index])
                    }
                    write("${it.key},${it.value.name},${it.value.date}${str}\n")
                    flush()
                }
                close()
            }
        }
    }
}