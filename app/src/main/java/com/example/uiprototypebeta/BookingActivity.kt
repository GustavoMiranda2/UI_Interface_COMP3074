package com.example.uiprototypebeta

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class BookingActivity : BaseDrawerActivity() {

    private lateinit var cardHaircut: MaterialCardView
    private lateinit var cardBeard: MaterialCardView
    private lateinit var cardHaircutBeard: MaterialCardView
    private lateinit var cbBrows: CheckBox
    private lateinit var btnConfirm: MaterialButton

    private val mainServices = listOf(
        MainService("haircut", "Haircut", 20, 45),
        MainService("beard", "Beard", 15, 30),
        MainService("combo", "Haircut & Beard", 25, 60)
    )
    private val browAddOn = AddOn("brows", "Eyebrows", 5, 15)

    private var selectedServiceId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.content_booking)
        setToolbarTitle("Book appointment")
        setCheckedDrawerItem(R.id.m_book)

        cardHaircut = findViewById(R.id.cardHaircut)
        cardBeard = findViewById(R.id.cardBeard)
        cardHaircutBeard = findViewById(R.id.cardHaircutBeard)
        cbBrows = findViewById(R.id.cbBrows)
        btnConfirm = findViewById(R.id.btnConfirm)

        val cards = mapOf(
            "haircut" to cardHaircut,
            "beard" to cardBeard,
            "combo" to cardHaircutBeard
        )

        cards.values.forEach { card ->
            card.isCheckable = true
            card.checkedIcon = AppCompatResources
                .getDrawable(this, R.drawable.ic_check_24)
                ?.mutate()
            card.checkedIconGravity = MaterialCardView.CHECKED_ICON_GRAVITY_TOP_END
        }

        cards.forEach { (id, card) ->
            card.setOnClickListener { selectService(cards, id) }
        }

        btnConfirm.setOnClickListener {
            val service = selectedServiceId?.let { id -> mainServices.find { it.id == id } }
            if (service != null) {
                val intent = Intent(this, BookingScheduleActivity::class.java).apply {
                    putExtra("service_id", service.id)
                    putExtra("service_title", service.title)
                    putExtra("service_price", service.price)
                    putExtra("service_duration", service.durationMinutes)
                    putExtra("add_brows", cbBrows.isChecked)
                    putExtra("add_brows_price", browAddOn.price)
                    putExtra("add_brows_duration", browAddOn.durationMinutes)
                }
                startActivity(intent)
            }
        }

        updateConfirmState()
    }

    private fun selectService(cards: Map<String, MaterialCardView>, serviceId: String) {
        cards.forEach { (id, card) -> card.isChecked = (id == serviceId) }
        selectedServiceId = serviceId
        updateConfirmState()
    }

    private fun updateConfirmState() {
        btnConfirm.isEnabled = selectedServiceId != null
    }

    data class MainService(
        val id: String,
        val title: String,
        val price: Int,
        val durationMinutes: Int
    )

    data class AddOn(
        val id: String,
        val title: String,
        val price: Int,
        val durationMinutes: Int
    )
}


