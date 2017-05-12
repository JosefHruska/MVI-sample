package com.example.pepah.rxapp.ui

import android.view.View
import android.widget.Toast
import com.example.pepah.rxapp.R
import com.jakewharton.rxbinding2.view.clicks
import cz.filipproch.reactor.base.translator.SimpleTranslatorFactory
import cz.filipproch.reactor.base.translator.TranslatorFactory
import cz.filipproch.reactor.base.view.ReactorUiAction
import cz.filipproch.reactor.base.view.ReactorUiEvent
import cz.filipproch.reactor.base.view.ReactorUiModel
import cz.filipproch.reactor.extras.ui.views.activity.ExtendedReactorActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit

class MainActivity : ExtendedReactorActivity<MainTranslator>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override val translatorFactory: TranslatorFactory<MainTranslator>
        get() = SimpleTranslatorFactory(MainTranslator::class.java)

    override fun onConnectModelChannel(modelStream: Observable<out ReactorUiModel>) {
        super.onConnectModelChannel(modelStream)
        modelStream.ofType(MainUiModel::class.java).consumeOnUi {
            vProgressBar.visibility = if (it.isLoading) View.VISIBLE else View.GONE

            when {
                it.success == true -> {
                    vName.setError(it.postTitle)
                    vPassword.setError(it.postContent)
                }
                it.success == false -> {
                    Toast.makeText(this, "Wrong credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }
        doBakaStuff()
    }

    override fun onConnectActionChannel(actionStream: Observable<out ReactorUiAction>) {
        super.onConnectActionChannel(actionStream)
    }

    override fun onEmittersInit() {
        super.onEmittersInit()
        registerEmitter(vAction.clicks().map { MasterButtonClicked })
    }

    object MasterButtonClicked : ReactorUiEvent

    fun doBakaStuff() {
        val url ="https://bakalari.gymberoun.cz/bakaweb/"
        Retrofit.Builder().baseUrl(url).addConverterFactory(SimpleCo)
    }
}
