package com.example.uiprototypebeta

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class BookingActivity : BaseDrawerActivity() {

    private lateinit var cardHaircut: MaterialCardView
    private lateinit var cardHaircutBeard: MaterialCardView
    private lateinit var cardEyebrows: MaterialCardView
    private lateinit var btnContinue: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.content_booking)
        setToolbarTitle("Select Service")
        setCheckedDrawerItem(R.id.m_book)

        cardHaircut = findViewById(R.id.cardHaircut)
        cardHaircutBeard = findViewById(R.id.cardHaircutBeard)
        cardEyebrows = findViewById(R.id.cardEyebrows)
        btnContinue = findViewById(R.id.btnContinue)

        val all = listOf(cardHaircut, cardHaircutBeard, cardEyebrows)

        // make cards checkable + place check icon at TOP_END
        all.forEach { card ->
            card.isCheckable = true
            card.checkedIcon = AppCompatResources
                .getDrawable(this, R.drawable.ic_check_24)
                ?.mutate() // ensure cards don't share stateful drawable
            card.checkedIconGravity = MaterialCardView.CHECKED_ICON_GRAVITY_TOP_END
        }

        fun select(card: MaterialCardView) {
            all.forEach { it.isChecked = (it == card) }
            btnContinue.isEnabled = all.any { it.isChecked }
        }

        all.forEach { card -> card.setOnClickListener { select(card) } }

        btnContinue.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.login_required_title)
                .setMessage(R.string.login_required_message)
                .setPositiveButton(R.string.action_ok) { _, _ ->
                    startActivity(Intent(this, LoginActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    })
                }
                .setNegativeButton(R.string.action_cancel, null)
                .show()
        }
    }
}


