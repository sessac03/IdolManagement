package com.agent.manage.data


data class CompanyInfo(
    val name: String,
    val address: String,
    val contactNumber: String,
    val group: List<IdolGroup>? = null
)

data class IdolGroup(
    val company: CompanyInfo?,
    val name: String,
    val members:List<String>?,
    val events: List<Event>?
)

data class Event(
    val name:String,
    val date: String,
    val castedGroup: List<IdolGroup>?
)
