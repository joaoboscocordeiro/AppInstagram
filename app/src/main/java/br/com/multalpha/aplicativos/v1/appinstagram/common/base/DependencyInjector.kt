package br.com.multalpha.aplicativos.v1.appinstagram.common.base

import android.content.Context
import br.com.multalpha.aplicativos.v1.appinstagram.ui.add.data.AddLocalDataSource
import br.com.multalpha.aplicativos.v1.appinstagram.ui.add.data.AddRepository
import br.com.multalpha.aplicativos.v1.appinstagram.ui.add.data.FireAddDataSource
import br.com.multalpha.aplicativos.v1.appinstagram.ui.home.data.FeedMemoryCache
import br.com.multalpha.aplicativos.v1.appinstagram.ui.home.data.HomeDataSourceFactory
import br.com.multalpha.aplicativos.v1.appinstagram.ui.home.data.HomeRepository
import br.com.multalpha.aplicativos.v1.appinstagram.ui.login.data.FireLoginDataSource
import br.com.multalpha.aplicativos.v1.appinstagram.ui.login.data.LoginRepository
import br.com.multalpha.aplicativos.v1.appinstagram.ui.post.data.PostLocalDataSource
import br.com.multalpha.aplicativos.v1.appinstagram.ui.post.data.PostRepository
import br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data.PostListMemoryCache
import br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data.ProfileDataSourceFactory
import br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data.ProfileMemoryCache
import br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data.ProfileRepository
import br.com.multalpha.aplicativos.v1.appinstagram.ui.register.data.FireRegisterDataSource
import br.com.multalpha.aplicativos.v1.appinstagram.ui.register.data.RegisterRepository
import br.com.multalpha.aplicativos.v1.appinstagram.ui.search.data.FireSearchDataSource
import br.com.multalpha.aplicativos.v1.appinstagram.ui.search.data.SearchRepository
import br.com.multalpha.aplicativos.v1.appinstagram.ui.splash.data.FireSplashDataSource
import br.com.multalpha.aplicativos.v1.appinstagram.ui.splash.data.SplashRepository

/**
 * Created by João Bosco on 02/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */

object DependencyInjector {

    fun splashRepository(): SplashRepository {
        return SplashRepository(FireSplashDataSource())
    }

    fun loginRepository(): LoginRepository {
        return LoginRepository(FireLoginDataSource())
    }

    fun registerEmailRepository(): RegisterRepository {
        return RegisterRepository(FireRegisterDataSource())
    }

    fun searchRepository(): SearchRepository {
        return SearchRepository(FireSearchDataSource())
    }

    fun profileRepository(): ProfileRepository {
        return ProfileRepository(ProfileDataSourceFactory(ProfileMemoryCache, PostListMemoryCache))
    }

    fun homeRepository(): HomeRepository {
        return HomeRepository(HomeDataSourceFactory(FeedMemoryCache))
    }

    fun addRepository(): AddRepository {
        return AddRepository(FireAddDataSource(), AddLocalDataSource())
    }

    fun postRepository(context: Context): PostRepository {
        return PostRepository(PostLocalDataSource(context))
    }

}