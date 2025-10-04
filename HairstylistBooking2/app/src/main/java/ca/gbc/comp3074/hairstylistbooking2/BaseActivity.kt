package ca.gbc.comp3074.hairstylistbooking2

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

open class BaseActivity(@LayoutRes private val layoutId:Int) : AppCompatActivity() {

    protected lateinit var drawerLayout: DrawerLayout
    protected lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        drawerLayout = findViewById(R.id.drawerLayout)
        navView = findViewById(R.id.navView)


        findViewById<ImageButton>(R.id.btnMenu)?.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END)
        }

        navView.setNavigationItemSelectedListener { item: MenuItem ->
            drawerLayout.closeDrawers()
            when (item.itemId) {
                R.id.nav_home -> {
                    if (this !is MainActivity) startActivity(Intent(this, MainActivity::class.java))
                }
                R.id.nav_book -> {
                    if (this !is BookActivity) startActivity(Intent(this, BookActivity::class.java))
                }
                R.id.nav_portfolio -> {
                    if (this !is PortfolioActivity) startActivity(Intent(this, PortfolioActivity::class.java))
                }
                R.id.nav_blog -> {
                    if (this !is BlogActivity) startActivity(Intent(this, BlogActivity::class.java))
                }
                R.id.nav_admin -> {
                    if (this !is AdminActivity) startActivity(Intent(this, AdminActivity::class.java))
                }
            }
            true
        }
    }
}