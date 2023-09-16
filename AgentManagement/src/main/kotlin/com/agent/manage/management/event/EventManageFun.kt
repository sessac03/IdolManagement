package com.agent.manage.management.event

import com.agent.manage.ConsoleReader
import com.agent.manage.data.Event
import com.agent.manage.database.CompanyDB
import com.agent.manage.database.EventDB
import com.agent.manage.database.EventDB.Companion.eventDB
import com.agent.manage.database.EventDB.Companion.updateEventFileDB
import kotlin.math.abs
import kotlin.random.Random

class EventManageFun {
    fun getEvents() {
        eventDB.forEach { event ->
            println("행사명: ${event.value.name}")
            println("행사 날짜: ${event.value.date}")
            println("출연 아이돌: ${event.value.castedGroup}")
            println("-----------------------------------")
        }
    }

    fun addEvent() {
        val line: String?
        line = ConsoleReader.consoleScanner()
        if (!line.isNullOrEmpty()) {
            val eventInfo = line.split(',')
            val groupList = eventInfo.subList(2, eventInfo.size)
            val data = Event(eventInfo[0], eventInfo[1], groupList)
            EventDB.eventDB.put(abs(Random.nextInt()), data)
            println("AddEvent 결과: ${EventDB.eventDB}")
            updateEventFileDB()
        }
    }

    fun searchEvent() {
        val eventName: String?
        eventName = ConsoleReader.consoleScanner()
        if (!eventName.isNullOrEmpty()) {
            println("[$eventName] 검색 결과")
            eventDB.filter {
                it.value.name == eventName
            }.forEach {
                println("행사명: ${it.value.name}")
                println("행사 날짜: ${it.value.date}")
                println("출연 아이돌: ${it.value.castedGroup}")
            }
        }
    }

    fun updateEvent() {
        val eventName: String?
        eventName = ConsoleReader.consoleScanner()
        if (!eventName.isNullOrEmpty()) {
            val key = eventDB.filterValues {
                it.name == eventName
            }.keys
            val newData = ConsoleReader.consoleScanner()
            if (!newData.isNullOrEmpty()) {
                val str = newData.split(",")
                val groupList = str.subList(2, str.size)
                val data = Event(str[0], str[1], groupList)
//                eventDB.replace(key, data)
            }
//            var flag = false
//            var idx = 0
//            for (index in CompanyDB.companyDB.indices) {
//                if (eventName == CompanyDB.companyDB[index].get("name")) {
//                    idx = index
//                    flag = true
//                }
//            }
//            if (flag == false) {
//                println("존재하지 않는 회사입니다.")
//                return
//            }
//            println("회사명,주소,전화번호 형식으로 입력해주세요.")
//            val newData = ConsoleReader.consoleScanner()
//            if (!newData.isNullOrEmpty()) {
//                val str = newData.split(",")
//                CompanyDB.companyDB[idx].replace("name", str[0])
//                CompanyDB.companyDB[idx].replace("address", str[1])
//                CompanyDB.companyDB[idx].replace("callNum", str[2])
//            }
        }
        updateEventFileDB()
    }

    fun deleteEvent() {
        var line: String?
        line = ConsoleReader.consoleScanner()
        println("EventManageFun deleteEvent ${eventDB}")
        if (!line.isNullOrEmpty()) {
            var indexDelete = -1
            for (index in CompanyDB.companyDB.indices) {
                println(line)
                if (line.equals(CompanyDB.companyDB[index].get("name"))) {
                    indexDelete = index
                    break
                }
            }
            if (indexDelete != -1) {
                CompanyDB.companyDB.removeAt(indexDelete)
                println("삭제 완료!")
            } else {
                println("존재하지 않는 회사입니다.")
            }
        }
        CompanyDB.updateCompanyFileDB()
    }
}