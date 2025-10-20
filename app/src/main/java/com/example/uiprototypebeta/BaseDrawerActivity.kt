package com.example.uiprototypebeta

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

open class BaseDrawerActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: MaterialToolbar
    private lateinit var navView: NavigationView
    private lateinit var contentFrame: FrameLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_base)

        drawerLayout = findViewById(R.id.drawerLayout)
        toolbar = findViewById(R.id.toolbar)
        navView = findViewById(R.id.navView)
        contentFrame = findViewById(R.id.contentFrame)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        toolbar.setNavigationOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END)
            } else {
                drawerLayout.openDrawer(GravityCompat.END)
            }
        }

        navView.setNavigationItemSelectedListener { onDrawerItem(it) }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.END)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    protected fun setContentLayout(@LayoutRes layoutRes: Int) {
        LayoutInflater.from(this).inflate(layoutRes, contentFrame, true)
    }

    protected fun setToolbarTitle(text: CharSequence) { toolbar.title = text }

    protected fun setCheckedDrawerItem(@IdRes menuId: Int) {
        navView.menu.findItem(menuId)?.let { navView.setCheckedItem(menuId) }
    }

    private fun onDrawerItem(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.m_home      -> if (this !is HomeActivity) startActivity(Intent(this, HomeActivity::class.java))
            R.id.m_book      -> if (this !is BookingActivity) startActivity(Intent(this, BookingActivity::class.java))
            R.id.m_portfolio -> if (this !is PortfolioActivity) startActivity(Intent(this, PortfolioActivity::class.java))
            R.id.m_blog      -> if (this !is BlogActivity) startActivity(Intent(this, BlogActivity::class.java))
            R.id.m_admin     -> if (this !is AdminLoginActivity) startActivity(Intent(this, AdminLoginActivity::class.java))
        }
        drawerLayout.closeDrawer(GravityCompat.END)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (::toggle.isInitialized && toggle.onOptionsItemSelected(item)) return true
        return super.onOptionsItemSelected(item)
    }
}


