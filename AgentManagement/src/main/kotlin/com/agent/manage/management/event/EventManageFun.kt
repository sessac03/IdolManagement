package com.agent.manage.management.event

import com.agent.manage.ConsoleReader
import com.agent.manage.data.Event
import com.agent.manage.database.CompanyDB
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
            eventDB.put(abs(Random.nextInt()), data)
//            println("AddEvent 결과: ${eventDB}")
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
            var eventKey = -1
            for (event in eventDB) {
                if (eventName == event.value.name) {
                    eventKey = event.key
                    break
                }
            }
            if (eventKey != -1) {
                println("수정할 정보를 입력해주세요.(형식: 행사명,행사 날짜(0000-00-00),캐스팅된 아이돌)")
                val newData = ConsoleReader.consoleScanner()
                if (!newData.isNullOrEmpty()) {
                    val str = newData.split(",")
                    val groupList = str.subList(2, str.size)
                    val data = Event(str[0], str[1], groupList)
                    eventDB.replace(eventKey, data)
                }
            } else {
                println("존재하지 않는 행사입니다.")
            }
        }
        updateEventFileDB()
    }

    fun deleteEvent() {
        var eventName: String?
        eventName = ConsoleReader.consoleScanner()
        if (!eventName.isNullOrEmpty()) {
            var eventKey = -1
            for (event in eventDB) {
                if (eventName == event.value.name) {
                    eventKey = event.key
                    break
                }
            }
            if (eventKey != -1) {
                eventDB.remove(eventKey)
            } else {
                println("존재하지 않는 행사입니다.")
            }
        }
        updateEventFileDB()
    }
}