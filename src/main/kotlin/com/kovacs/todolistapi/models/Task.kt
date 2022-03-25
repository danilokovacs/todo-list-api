package com.kovacs.todolistapi.models

import javax.persistence.*

@Entity
class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id = 0

    @Column
    var fk_user = 0

    @Column
    var resume = ""

    @Column
    var description = ""

    @Column
    var status = ""

    @Column
    var date_changed = ""
}