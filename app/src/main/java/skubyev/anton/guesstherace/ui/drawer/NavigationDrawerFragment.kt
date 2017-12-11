package skubyev.anton.guesstherace.ui.drawer

import android.content.Context
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_nav_drawer.*
import skubyev.anton.guesstherace.R
import skubyev.anton.guesstherace.presentation.drawer.NavigationDrawerPresenter
import skubyev.anton.guesstherace.presentation.drawer.NavigationDrawerView
import skubyev.anton.guesstherace.toothpick.DI
import skubyev.anton.guesstherace.ui.global.BaseFragment
import skubyev.anton.guesstherace.ui.global.ConfirmDialog
import skubyev.anton.guesstherace.ui.launch.MainActivity
import toothpick.Toothpick

class NavigationDrawerFragment : BaseFragment(), NavigationDrawerView, ConfirmDialog.OnClickListener {
    override val layoutRes = R.layout.fragment_nav_drawer
    private var mainActivity: MainActivity? = null

    private val itemClickListener = { view: View ->
        presenter.onMenuItemClick(view.tag as NavigationDrawerView.MenuItem)
    }

    override val dialogConfirm: (tag: String) -> Unit = { tag ->
        when (tag) {
            CONFIRM_LOGOUT_TAG -> presenter.onLogoutClick()
        }
    }

    @InjectPresenter lateinit var presenter: NavigationDrawerPresenter

    @ProvidePresenter
    fun providePresenter(): NavigationDrawerPresenter {
        return Toothpick
                .openScope(DI.MAIN_ACTIVITY_SCOPE)
                .getInstance(NavigationDrawerPresenter::class.java)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeMI.tag = NavigationDrawerView.MenuItem.HOME
        feedbackMI.tag = NavigationDrawerView.MenuItem.FEEDBACK
        settingsMI.tag = NavigationDrawerView.MenuItem.SETTINGS
        ratingMI.tag = NavigationDrawerView.MenuItem.RATING

        homeMI.setOnClickListener(itemClickListener)
        feedbackMI.setOnClickListener(itemClickListener)
        settingsMI.setOnClickListener(itemClickListener)
        ratingMI.setOnClickListener(itemClickListener)
    }

    override fun selectMenuItem(item: NavigationDrawerView.MenuItem) {
        (0 until navDrawerMenuContainer.childCount)
                .map { navDrawerMenuContainer.getChildAt(it) }
                .forEach { menuItem -> menuItem.tag?.let { menuItem.isSelected = it == item } }
    }

    fun onScreenChanged(item: NavigationDrawerView.MenuItem) {
        presenter.onScreenChanged(item)
    }

    private companion object {
        private val CONFIRM_LOGOUT_TAG = "confirm_logout_tag"
    }
}