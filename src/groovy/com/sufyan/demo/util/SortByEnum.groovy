package com.sufyan.demo.util

/**
 * Created by sufyanshoaibcobone on 22/01/2015.
 */
enum SortByEnum {
    DATE("startDate"), TITLE("title"), DESCRIPTION("description"), DISTANCE("distance")

    String value

    SortByEnum(String value) {
        this.value = value
    }

    static SortByEnum find(String sortBy) {
        println("SortByEnum.values()::${SortByEnum.values()}")
        return SortByEnum.values().find {
            it.value == sortBy
        }
    }
}