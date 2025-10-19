package com.example.uiprototypebeta

import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class BookingActivity : BaseDrawerActivity() {

    private lateinit var cardHaircut: MaterialCardView
    private lateinit var cardHaircutBeard: MaterialCardView
    private lateinit var cardEyebrows: MaterialCardView
    private lateinit var btnContinue: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.content_booking)
        setToolbarTitle("Select Service")
        // NOTE: no setCheckedDrawerItem(...) here because "Book" was removed from the drawer

        cardHaircut = findViewById(R.id.cardHaircut)
        cardHaircutBeard = findViewById(R.id.cardHaircutBeard)
        cardEyebrows = findViewById(R.id.cardEyebrows)
        btnContinue = findViewById(R.id.btnContinue)

        val all = listOf(cardHaircut, cardHaircutBeard, cardEyebrows)

        // make cards checkable + place check icon at TOP_END
        val check = AppCompatResources.getDrawable(this, R.drawable.ic_check_24)
        all.forEach { card ->
            card.isCheckable = true
            card.checkedIcon = check
            card.checkedIconGravity = MaterialCardView.CHECKED_ICON_GRAVITY_TOP_END
        }

        fun select(card: MaterialCardView) {
            all.forEach { it.isChecked = (it == card) }
            btnContinue.isEnabled = all.any { it.isChecked }
        }

        all.forEach { card -> card.setOnClickListener { select(card) } }

        // Optional: preselect one
        // select(cardHaircut)
    }
}





