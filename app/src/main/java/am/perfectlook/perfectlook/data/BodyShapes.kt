package am.perfectlook.perfectlook.data

import am.perfectlook.perfectlook.R
import am.perfectlook.perfectlook.models.Shape
import am.perfectlook.perfectlook.states.BodyShape

object BodyShapes {
    val shapes = HashMap<String, Shape>()

    init {
        shapes[BodyShape.HOURGLASS.value] = Shape(
            "Hourglass",
            "#d0aead",
            "The hourglass body shape " +
                    "characterized by hip and bust measurements nearly equal in size, with " +
                    "a narrower waist measurement. The shoulders are slightly round and the " +
                    "legs are in proportion with the upper body",
            R.drawable.shape_hourglass,
            listOf()
        )
        shapes[BodyShape.ROUND.value] = Shape(
            "Round",
            "#3b5856",
            "Typical characteristics of the apple body shape are a large bust, " +
                    "narrow hips and a full midsection. Often, apples tend to have slender " +
                    "legs and flat bottoms",
            R.drawable.shape_round,
            listOf()
        )
        shapes[BodyShape.TRIANGLE.value] = Shape(
            "Triangle",
            "#757575",
            "The pear body shape is characterized by large hips that are wider than " +
                    "the bust and shoulders. Other characteristics of this body shape are a " +
                    "defined waist, and proportionately slim arms and shoulders",
            R.drawable.shape_triangle,
            listOf()
        )
        shapes[BodyShape.RECTANGLE.value] = Shape(
            "Rectangle",
            "#4e7471",
            "The rectangle body shape is characterized by an equal bust, waist, and " +
                    "hip measurements. Rectangles tend to be tall and lean. They are not " +
                    "particularly curvy, the waist is not well-defined, and the bottom is " +
                    "rather flat",
            R.drawable.shape_rectangle,
            listOf()
        )
        shapes[BodyShape.UPTURNED_TRIANGLE.value] = Shape(
            "Upturned triangle",
            "#d59d9d",
            "The inverted triangle body shape is characterized by broad shoulders " +
                    "and/or bust that narrow down to the hips. The strong shoulders often lend " +
                    "this body shape an athletic-looking physique",
            R.drawable.shape_upturned_triangle,
            listOf()
        )
    }
}