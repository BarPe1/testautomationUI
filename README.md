# testautomationUI
This is a test automation framework for handling Web UI tests

Tools:
- JetBrains IntelliJ IDE
- Java 17 or higher
- Maven
- Notepad ++
- Chromium (WebDriver for Chrome browser)
- Dependencies from mvn repos (https://mvnrepository.com/)
- JUnit
- TestNG
- F12 developer tool (click F12 button on your keyboard when Chrome is opened)
- Playwright (as dependency in pom.xml)
- Selenium  (as dependency in pom.xml)

Check version of Chrome
 - Open PowerShell and run the command: (Get-Item "C:\Program Files\Google\Chrome\Application\chrome.exe").VersionInfo.ProductVersion

Download Chromium WebDriver:
- For current browser version https://googlechromelabs.github.io/chrome-for-testing/
- Old versions: https://storage.googleapis.com/chrome-for-testing-public/xxx.x.xxxx.xx/win64/chromedriver-win64.zip , NOTE: change 'x' to IP address from your current browser by json endpoint: https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json (use ctrl + f to find IP address)

Create chromeDriver folder in resources (in the project tree)
Unzip download Chromium
Copy/paste to chromeDriver folder

Check dependencies in pom.xml file by click right mouse button > Maven > Sync project
Add Maven clean install command - click Edit configuration (close to green triangle), in Run/Debug configuration pop-up click '+' and click Maven, next copy/paste Maven command in Run dialog field: clean install -Dmaven.test.skip=true ; and click Apply, and OK

Run Maven command by click green triangle

Tests are run in TestRun class, by small, green triangle on the left
