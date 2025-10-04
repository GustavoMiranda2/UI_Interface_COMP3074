package ca.gbc.comp3074.hairstylistbooking2

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class AdminActivity : BaseActivity(R.layout.activity_admin) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listTab = findViewById<TextView>(R.id.tabList)
        val calTab  = findViewById<TextView>(R.id.tabCalendar)
        val sectionList = findViewById<LinearLayout>(R.id.sectionList)
        val sectionCalendar = findViewById<LinearLayout>(R.id.sectionCalendar)

        fun select(isList:Boolean){
            sectionList.visibility = if (isList) LinearLayout.VISIBLE else LinearLayout.GONE
            sectionCalendar.visibility = if (isList) LinearLayout.GONE else LinearLayout.VISIBLE
            listTab.isSelected = isList
            calTab.isSelected  = !isList
        }

        listTab.setOnClickListener { select(true) }
        calTab.setOnClickListener  { select(false) }

        // começa no modo "List"
        select(true)
    }
}