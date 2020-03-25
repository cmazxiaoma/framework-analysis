package com.cmazxiaoma.caiji;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Objects;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/11/24 13:24
 */
public class RemoteWebDriverPool {

    private int MAX_COUNT = 0;

    private RemoteWebDriver[] webDrivers = null;

    private Boolean[] driverFlag = null;

    public RemoteWebDriverPool(int maxCount) {
        this.MAX_COUNT = maxCount;
        webDrivers = new RemoteWebDriver[MAX_COUNT];
        driverFlag = new Boolean[MAX_COUNT];

        for (int i = 0; i < MAX_COUNT; i++) {
            driverFlag[i] = true;
        }

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                for (WebDriver webDriver :webDrivers) {
                    if (!Objects.isNull(webDriver)) {
                        webDriver.quit();
                    }
                }
            }
        }));
    }

    public RemoteWebDriver createDriver() {
        RemoteWebDriver remoteWebDriver = null;
        try {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setJavascriptEnabled(true);

            remoteWebDriver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"),
                    DesiredCapabilities.chrome()
            );

            /**
             * 窗口设置
             */
            //remoteWebDriver.manage().window().maximize();

            remoteWebDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
                    .pageLoadTimeout(10, TimeUnit.SECONDS);
        } catch (Exception ex) {
            //
        }
        return remoteWebDriver;
    }

    public RemoteWebDriver getIdleDriver() {
        RemoteWebDriver remoteWebDriver = null;
        int index = -1;
        for (int i = 0; i < driverFlag.length; i++) {
            if (driverFlag[i]) {
                driverFlag[i] = false;
                index = i;
                break;
            }
        }

        if (index != -1) {
            if (Objects.isNull(webDrivers[index])) {
                webDrivers[index] = createDriver();
                remoteWebDriver = webDrivers[index];
            }
        }
        return remoteWebDriver;
    }


}
