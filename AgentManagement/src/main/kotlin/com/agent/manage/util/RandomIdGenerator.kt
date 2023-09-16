package com.agent.manage.util

import java.util.*

class RandomIdGenerator {

    companion object{
        var randomId : Int = Random().nextInt(1000)
    }
}