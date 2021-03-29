package com.example.proba.main.model


import com.example.proba.main.model.enums.Job_Position
import com.example.proba.main.model.enums.Workplace
import java.io.Serializable

class TetaMenza constructor(
    var first_name :String,
    var last_name : String,
    var workplace : Workplace,
    var age : Int,
    var OIB : Int,
    var gender : String,
    var job_position : Job_Position,
    var job_shift : String,
    var email : String,
    var address : String
):Serializable{

    override fun toString(): String {
        return first_name+" "+last_name+" "+workplace.toString()+" "+ age.toString()+" "+OIB.toString()+" "+gender+
                " "+job_position.toString()+" "+job_shift+" "+email+" "+address
    }
}