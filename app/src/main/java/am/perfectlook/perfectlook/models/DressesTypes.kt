package am.perfectlook.perfectlook.models

object DressesTypes {
    val baseTypes = arrayListOf("Tops", "Bottoms", "Dresses", "Jumpsuits")
    val childTypes = hashMapOf<String, ArrayList<String>>()

    init {
        childTypes["tops"] = arrayListOf("Neckline", "Top", "Jacket")
        childTypes["bottoms"] = arrayListOf("Skirts", "Pants")
    }
}