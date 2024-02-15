package com.mxtgames.selectableitemdemo.ui.selectabledemo.data

enum class ItemType(val value: Int) {
    UNKNOWN(0),
    COTTON_ECHO(1),
    COTTONS(2),
    SYNTHETICS(3),
    MIXED(4),
    DELICATES(5),
    SPORTS(6),
    BED_LINEN(7),
}

fun Int?.toItemType(): ItemType {
    return when (this) {
        1 -> ItemType.COTTON_ECHO
        2 -> ItemType.COTTONS
        3 -> ItemType.SYNTHETICS
        4 -> ItemType.MIXED
        5 -> ItemType.DELICATES
        6 -> ItemType.SPORTS
        7 -> ItemType.BED_LINEN
        else -> ItemType.UNKNOWN
    }
}