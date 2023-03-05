package uz.itschool.secondprojectforbsb

data class Foods(
    val img: String,
    val name: String,
    val weight: Int,
    val calories: Int,
    val location: String,
    val direction: Double,
    val price: Int
): java.io.Serializable
