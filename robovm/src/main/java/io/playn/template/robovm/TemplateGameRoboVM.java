package io.playn.template.robovm;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.*;
import org.robovm.objc.annotation.CustomClass;
import org.robovm.rt.bro.annotation.ForceLinkClass;

import playn.robovm.RoboPlatform;
import io.playn.template.core.TemplateGame;

@ForceLinkClass(TemplateGameRoboVM.SceneDelegate.class)
public class TemplateGameRoboVM extends UIApplicationDelegateAdapter {

  @Override
  public boolean didFinishLaunching (UIApplication app, UIApplicationLaunchOptions launchOpts) {
    return true;
  }

  @CustomClass(preload = true)
  public static class SceneDelegate extends UIWindowSceneDelegateAdapter {
    private UIWindow window;

    @Override
    public void willConnect(UIScene scene, UISceneSession session,
                            UISceneConnectionOptions connectionOptions) {
      if (!(scene instanceof UIWindowScene)) {
        return;
      }

      window = new UIWindow((UIWindowScene) scene);
      RoboPlatform.Config config = new RoboPlatform.Config();
      config.orients = UIInterfaceOrientationMask.All;
      RoboPlatform plat = RoboPlatform.create(window, config);
      new TemplateGame(plat);
      window.makeKeyAndVisible();
    }
  }

  public static void main (String[] args) {
    NSAutoreleasePool pool = new NSAutoreleasePool();
    UIApplication.main(args, null, TemplateGameRoboVM.class);
    pool.close();
  }
}
