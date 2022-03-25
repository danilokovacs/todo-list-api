package com.kovacs.todolistapi.models

import javax.persistence.*

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id = 0

    @Column
    var name = ""

    @Column
    var email = ""

    @Column
    var password = ""
}