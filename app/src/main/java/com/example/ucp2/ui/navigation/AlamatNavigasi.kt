package com.example.ucp2.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}

object DestinasiListBrg : AlamatNavigasi {
    override val route: String = "list_Brg"
}

object DestinasiListSplr : AlamatNavigasi {
    override val route: String = "list_Splr"
}

object DestinasiInsertBrg : AlamatNavigasi {
    override val route: String = "insert_Brg"
}

object DestinasiInsertSplr : AlamatNavigasi {
    override val route: String = "insert_Splr"
}

object DestinasiDetailBrg : AlamatNavigasi {
    override val route = "detail_brg"
    const val ID = "id"
    val routesWithArg = "$route/{$ID}"
}

object DestinasiUpdateBrg : AlamatNavigasi {
    override val route = "update_brg"
    const val ID = "id"
    val routesWithArg = "$route/{$ID}"
}





