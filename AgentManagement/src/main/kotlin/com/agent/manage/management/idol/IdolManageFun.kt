package com.agent.manage.management.idol

import com.agent.manage.data.Company
import com.agent.manage.data.Event
import com.agent.manage.util.ConsoleReader
import com.agent.manage.data.IdolGroup
import com.agent.manage.database.CompanyDB
import com.agent.manage.database.CompanyDB.Companion.companyDB
import com.agent.manage.database.EventDB
import com.agent.manage.database.EventDB.Companion.eventDB
import com.agent.manage.database.EventDB.Companion.updateEventFileDB
import com.agent.manage.database.IdolDB
import com.agent.manage.database.IdolDB.Companion.idolDB
import com.agent.manage.database.IdolDB.Companion.updateIdolFileDB
import com.agent.manage.util.RandomIdGenerator

class IdolManageFun {
    fun getIdol() {
        for (idol in idolDB) {
            println("소속사: ${idol.value.company}")
            println("그룹명: ${idol.value.name}")
            println("멤버수: ${idol.value.count}")
            print("아이돌 리스트: ")
            idol.value.members?.forEach {
                print("${it} ")
            }
            println()
            print("행사 리스트: ")
            idol.value.events?.forEach {
                print("${it} ")
            }
            println()
            println("-----------------------------------")
        }
    }

    fun addIdol() {
        var line: String?
        line = ConsoleReader.consoleScanner()
        if (!line.isNullOrEmpty()) {
            val str = line.split(',')
            //TODO 소속사도 EXIST 검사해야 하나
            val members = str.subList(3, str.size)
            val data = IdolGroup(str[0], str[1], str[2].toInt(), members)
            addAndUpdateIdolToCompanyDB(str[0],str[1])
            idolDB.put(RandomIdGenerator.randomId, data)
            println("AddIdol 결과: $idolDB")
            updateIdolFileDB()
        }
    }

    fun searchIdol() {
        var idolName: String?
        idolName = ConsoleReader.consoleScanner()
        if (!idolName.isNullOrEmpty()) {
            var flag = false
            for (idol in idolDB) {
                if (idolName.equals(idol.value.name)) {
                    flag = true
                    println("[$idolName] 검색결과")
                    println("소속사: ${idol.value.company}")
                    println("그룹명: ${idol.value.name}")
                    println("멤버수: ${idol.value.count}")
                    print("아이돌 리스트: ")
                    idol.value.members?.forEach {
                        print("${it} ")
                    }
                    println()
                    print("행사 리스트: ")
                    idol.value.events?.forEach {
                        print("${it} ")
                    }
                    println()
                    break
                }
            }
            println()

            if (flag == false) {
                println("존재하지 않는 그룹명입니다.")
            }
        }
    }

    fun updateIdol() {
        var idolName: String?
        idolName = ConsoleReader.consoleScanner()
        if (!idolName.isNullOrEmpty()) {
            var flag = false
            var idolKey = 0
            var events = listOf<String>()
            for (idol in idolDB) {
                if (idolName.equals(idol.value.name)) {
                    flag = true
                    idolKey = idol.key
                    events = idol.value.events as List<String>
                    break
                }
            }
            if (flag == false) {
                println("존재하지 않는 그룹명입니다.")
                return
            }

            println("회사명,그룹명,멤버수,멤버 형식으로 입력해주세요.(그룹명은 변경불가)")
            val newData = ConsoleReader.consoleScanner()
            if (!newData.isNullOrEmpty()) {
                val str = newData.split(',')
                val members = str.subList(3, str.size)
                val data = IdolGroup(str[0], str[1], str[2].toInt(), members, events)
                addAndUpdateIdolToCompanyDB(str[0],str[1])
                idolDB.put(idolKey, data)
                println("AddIdol 결과: $idolDB")
            }
        }
        updateIdolFileDB()
    }

    fun deleteIdol() {
        var IdolName: String?
        IdolName = ConsoleReader.consoleScanner()
        if (!IdolName.isNullOrEmpty()) {
            var flag = false
            for (idol in idolDB) {
                if (IdolName.equals(idol.value.name)) {
                    flag = true
                    deleteIdolInEventDB(IdolName) //삭제했을 때 이벤트목록에서 아이돌리스트도 제거되야함
                    deleteIdolInCompanyDB(IdolName) //삭제했을 회사의 아이돌목록에서 아이돌이 삭제되어야함.
                    idolDB.remove(idol.key)
                    break
                }
            }
            if(flag){
                println("삭제 완료!")
            } else {
                println("존재하지 않는 그룹명입니다.")
            }
        }
        updateIdolFileDB()
    }
    fun deleteIdolInEventDB(idolName: String){
        for (event in eventDB) {
            for (idol in event.value.castedGroup) {
                if (idolName == idol) {
                    var castedGroup = event.value.castedGroup.toMutableList()
                    castedGroup.remove(idol)
                    val data =
                        Event(event.value.name, event.value.date,castedGroup)
                    eventDB.replace(event.key, data)
                }
                //break 해야하나
            }
        }
       EventDB.updateEventFileDB()
    }

    fun addAndUpdateIdolToCompanyDB(companyName:String, idolName: String){ //테스트
        for (company in companyDB) {
            if (companyName == company.value.name) {
                var groupList = mutableListOf<String>()
                if(company.value.group != null){
                    groupList = company.value.group!!.toMutableList()
                    groupList.add(idolName)
                }else{
                    groupList.add(idolName)
                }
                val data = Company(company.value.name, company.value.address, company.value.contactNumber, groupList)
                companyDB.replace(company.key, data)
            }
        }
        CompanyDB.updateCompanyFileDB()
    }


    fun deleteIdolInCompanyDB(idolName: String){
        for (company in companyDB) {
            for (group in company.value.group as List<String>) {
                if (idolName == group) {
                    var groupList = company.value.group!!.toMutableList()
                    groupList.remove(idolName)
                    val data = Company(company.value.name, company.value.address, company.value.contactNumber, groupList)
                    companyDB.replace(company.key, data)
                }
                //break 해야하나
            }
        }
        CompanyDB.updateCompanyFileDB()
    }
}