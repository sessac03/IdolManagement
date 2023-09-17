package com.agent.manage.data

//파일 입출력하기 쉽게 하려고 List<IdolGroup> 이 아닌 아이돌그룹명으로만 저장함,,
data class Company(
    val name: String,
    val address: String,
    val contactNumber: String,
    val group: List<String>? = null
)

data class IdolGroup(
    val company: String?,
    val name: String,
    val count: Int,
    val members:List<String>? = null,
    val events: List<String>? = null
)

data class Event(
    val name:String,
    val date: String,
    val castedGroup: List<String>
)

