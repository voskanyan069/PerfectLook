package am.perfectlook.perfectlook.data

import am.perfectlook.perfectlook.states.BodyShape

object SuitedDresses {
    val suitedDresses: HashMap<String, HashMap<String, ArrayList<String>>> = hashMapOf(
        Pair(BodyShape.HOURGLASS.value, hashMapOf(
            Pair("neckline", arrayListOf(
                "Sweetheart",
                "U-neck",
                "Across",
                "V-neck",
                "Off shoulders",
                "Boat shaped",
                "Round",
                "Square",
                "Halter",
                "Draped",
                "Sabrina",
                "Keyhole",
                "Asymmetric",
                "Cowl"
            )),
            Pair("top", arrayListOf(
                "Fitted T-shirt",
                "Fitted shirt",
                "Fitted wrap",
                "Belted",
                "Peplum",
            )),
            Pair("jacket", arrayListOf()),
            Pair("skirts", arrayListOf(
                "Pencil",
                "Wraparound",
                "A-line",
                "Trumpet",
                "Miniskirts",
            )),
            Pair("pants", arrayListOf()),
            Pair("dresses", arrayListOf(
                "A-line",
                "Baby doll",
                "Raised waist",
                "Sheath",
                "Tube",
            )),
            Pair("jumpsuits", arrayListOf())
        )),
        Pair(BodyShape.ROUND.value, hashMapOf(
            Pair("neckline", arrayListOf(
                "Sweetheart",
                "U-neck",
                "Across",
                "V-neck",
                "Off shoulders",
                "Boat shaped",
                "Round",
                "Square",
                "Halter",
                "Draped",
                "Sabrina",
                "Keyhole",
                "Asymmetric",
                "Cowl"
            )),
            Pair("top", arrayListOf(
                "Trapeze",
                "Pussy Bow",
                "Draping",
                "Tunic",
                "Wrap bust",
            )),
            Pair("jacket", arrayListOf()),
            Pair("skirts", arrayListOf(
                "Pencil",
                "Wraparound",
                "A-line",
                "Basque",
                "Straight",
            )),
            Pair("pants", arrayListOf()),
            Pair("dresses", arrayListOf(
                "A-line",
                "Raised waist",
                "Shirt",
                "Straight",
                "Wrap"
            )),
            Pair("jumpsuits", arrayListOf())
        )),
        Pair(BodyShape.TRIANGLE.value, hashMapOf(
            Pair("neckline", arrayListOf(
                "Sweetheart",
                "U-neck",
                "Across",
                "V-neck",
                "Off shoulders",
                "Boat shaped",
                "Round",
                "Square",
                "Halter",
                "Draped",
                "Sabrina",
                "Keyhole",
                "Asymmetric",
                "Cowl"
            )),
            Pair("top", arrayListOf(
                "Crop",
                "Wrap bust",
                "Ruffle shoulder",
                "Stripes",
                "Bust Pockets"
            )),
            Pair("jacket", arrayListOf()),
            Pair("skirts", arrayListOf(
                "Pencil",
                "A-line",
                "Trumpet",
                "Straight",
                "Floor-length"
            )),
            Pair("pants", arrayListOf()),
            Pair("dresses", arrayListOf(
                "Basque",
                "Bodycan",
                "Shirt",
                "Straight",
                "Wrap"
            )),
            Pair("jumpsuits", arrayListOf())
        )),
        Pair(BodyShape.RECTANGLE.value, hashMapOf(
            Pair("neckline", arrayListOf(
                "Sweetheart",
                "U-neck",
                "Across",
                "V-neck",
                "Off shoulders",
                "Boat shaped",
                "Round",
                "Square",
                "Halter",
                "Draped",
                "Sabrina",
                "Keyhole",
                "Asymmetric",
                "Cowl"
            )),
            Pair("top", arrayListOf(
                "Fitted wrap",
                "Belted",
                "Ruffle",
                "Button-down",
                "Pussy Bow",
            )),
            Pair("jacket", arrayListOf()),
            Pair("skirts", arrayListOf(
                "Tulip",
                "Poodle",
                "Pleated",
                "Basque",
                "Multi-layered",
            )),
            Pair("pants", arrayListOf()),
            Pair("dresses", arrayListOf(
                "Basque",
                "Bodycan",
                "Sheath",
                "Tube",
                "Wrap"
            )),
            Pair("jumpsuits", arrayListOf())
        )),
        Pair(BodyShape.UPTURNED_TRIANGLE.value, hashMapOf(
            Pair("neckline", arrayListOf(
                "Sweetheart",
                "U-neck",
                "Across",
                "V-neck",
                "Off shoulders",
                "Boat shaped",
                "Round",
                "Square",
                "Halter",
                "Draped",
                "Sabrina",
                "Keyhole",
                "Asymmetric",
                "Cowl"
            )),
            Pair("top", arrayListOf(
                "Trapeze",
                "Pussy Bow",
                "Draping",
                "Tunic",
                "Wrap bust",
            )),
            Pair("jacket", arrayListOf()),
            Pair("skirts", arrayListOf(
                "Tulip",
                "Poodle",
                "Pleated",
                "Miniskirts",
                "Basque",
                "Multi-layered",
            )),
            Pair("pants", arrayListOf()),
            Pair("dresses", arrayListOf(
                "A-line",
                "Baby doll",
                "Raised waist",
                "Shirt",
                "Tube",
            )),
            Pair("jumpsuits", arrayListOf())
        )),
    )
}