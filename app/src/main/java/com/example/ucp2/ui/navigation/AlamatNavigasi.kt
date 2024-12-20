package com.example.ucp2.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}

object DestinasiInsertBrg : AlamatNavigasi {
    override val route: String = "insert_Brg"
}

object DestinasiInserSplr : AlamatNavigasi {
    override val route: String = "insert_Brg"
}

object DestinasiDetailBrg : AlamatNavigasi {
    override val route = "detail_brg"
    const val ID = "id"
    val routesWithArg = "$route/{$ID}"
}

object DestinasiDetailSplr : AlamatNavigasi {
    override val route = "detail_splr"
    const val ID = "id"
    val routesWithArg = "$route/{$ID}"
}

object DestinasiUpdateBrg : AlamatNavigasi {
    override val route = "update_brg"
    const val ID = "id"
    val routesWithArg = "$route/{$ID}"
}

object DestinasiUpdateSplr : AlamatNavigasi {
    override val route = "update"
    const val ID = "id"
    val routesWithArg = "$route/{$ID}"
}




