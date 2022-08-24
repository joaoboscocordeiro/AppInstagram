package br.com.multalpha.aplicativos.v1.appinstagram.common.base

import android.content.Context
import br.com.multalpha.aplicativos.v1.appinstagram.ui.add.data.AddFakeRemoteDataSource
import br.com.multalpha.aplicativos.v1.appinstagram.ui.add.data.AddLocalDataSource
import br.com.multalpha.aplicativos.v1.appinstagram.ui.add.data.AddRepository
import br.com.multalpha.aplicativos.v1.appinstagram.ui.home.data.FeedMemoryCache
import br.com.multalpha.aplicativos.v1.appinstagram.ui.home.data.HomeDataSourceFactory
import br.com.multalpha.aplicativos.v1.appinstagram.ui.home.data.HomeRepository
import br.com.multalpha.aplicativos.v1.appinstagram.ui.login.data.FakeDataSource
import br.com.multalpha.aplicativos.v1.appinstagram.ui.login.data.LoginRepository
import br.com.multalpha.aplicativos.v1.appinstagram.ui.post.data.PostLocalDataSource
import br.com.multalpha.aplicativos.v1.appinstagram.ui.post.data.PostRepository
import br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data.PostListMemoryCache
import br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data.ProfileDataSourceFactory
import br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data.ProfileMemoryCache
import br.com.multalpha.aplicativos.v1.appinstagram.ui.profile.data.ProfileRepository
import br.com.multalpha.aplicativos.v1.appinstagram.ui.register.data.FakeRegisterDataSource
import br.com.multalpha.aplicativos.v1.appinstagram.ui.register.data.RegisterRepository
import br.com.multalpha.aplicativos.v1.appinstagram.ui.splash.data.FakeLocalDataSource
import br.com.multalpha.aplicativos.v1.appinstagram.ui.splash.data.SplashRepository

/**
 * Created by João Bosco on 02/11/2021.
 * e-mail - Support: ti.junior@gmail.com
 */

object DependencyInjector {

    fun splashRepository(): SplashRepository {
        return SplashRepository(FakeLocalDataSource())
    }

    fun loginRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository(): RegisterRepository {
        return RegisterRepository(FakeRegisterDataSource())
    }

    fun profileRepository(): ProfileRepository {
        return ProfileRepository(ProfileDataSourceFactory(ProfileMemoryCache, PostListMemoryCache))
    }

    fun homeRepository(): HomeRepository {
        return HomeRepository(HomeDataSourceFactory(FeedMemoryCache))
    }

    fun addRepository(): AddRepository {
        return AddRepository(AddFakeRemoteDataSource(), AddLocalDataSource())
    }

    fun postRepository(context: Context): PostRepository {
        return PostRepository(PostLocalDataSource(context))
    }

}