package ru.n00byara.notificationcode.hook

import com.highcapable.yukihookapi.YukiHookAPI.encase
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import ru.n00byara.notificationcode.Constants

import ru.n00byara.notificationcode.hook.entity.SystemUIHooker

@InjectYukiHookWithXposed
object Hook : IYukiHookXposedInit {
    override fun onHook() = encase {
        loadApp(Constants.HOOK_PACKAGE_NAME) {
            loadHooker(SystemUIHooker)
        }
    }
}