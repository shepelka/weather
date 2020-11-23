package com.usetech.x5weather.base

import android.content.res.Resources
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.MvpPresenter
import com.usetech.x5weather.BuildConfig
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BasePresenter<V : BaseView> : MvpPresenter<V>() {

    private val compositeDisposable = CompositeDisposable()
    private val compositeDisposableForDetach = CompositeDisposable()

    open fun unsubscribe() {
        compositeDisposable.clear()
    }

    open fun unsubscribeInDetach() {
        compositeDisposableForDetach.clear()
    }

    protected fun addToCompositeDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun addToCompositeDisposableForDetach(disposable: Disposable) {
        compositeDisposableForDetach.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        unsubscribe()
    }

    override fun detachView(view: V) {
        super.detachView(view)
        unsubscribeInDetach()
    }

}

abstract class BaseActivity : MvpAppCompatActivity(), BaseView {
    abstract fun showError(fullScreenError: Boolean, message: String)

}

abstract class BaseFragment : MvpAppCompatFragment(), BaseView {

    private var isLoadDataFirst = true

    @Suppress("ConstantConditionIf")
    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        try {
            if (nextAnim != 0x0 && enter) {
                val animator = AnimationUtils.loadAnimation(activity, nextAnim)
                animator.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {

                    }

                    override fun onAnimationEnd(animation: Animation) {
                        if (isLoadDataFirst) {
                            connectToListenDataOnScreen()
                        }
                    }

                    override fun onAnimationRepeat(animation: Animation) {

                    }
                })
                return animator
            }
        } catch (e: Resources.NotFoundException) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace()
            }
        }
        if (isLoadDataFirst) {
            connectToListenDataOnScreen()
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    abstract fun showError(fullScreenError: Boolean, message: String)

    open fun onBackPressed(): Boolean {
        return false
    }

    override fun onResume() {
        super.onResume()
        if (!isLoadDataFirst) {
            connectToListenDataOnScreen()
        }
    }

    open fun connectToListenDataOnScreen() {
        isLoadDataFirst = false
    }
}



