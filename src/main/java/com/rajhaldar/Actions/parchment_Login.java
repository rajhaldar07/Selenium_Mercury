package com.parchment.Actions;

import com.google.common.base.Verify;
import com.parchment.PageObjects.Alpha.AlphaLoginHomePage;
import com.parchment.PageObjects.Analyze.AnalyzeHomePage;
import com.parchment.PageObjects.Exchange.AwardLoginNextpage;
import com.parchment.PageObjects.Exchange.CMSHomePage;
import com.parchment.PageObjects.Exchange.DeltaTPOPage;
import com.parchment.PageObjects.Exchange.DeltaTPORegister;
import com.parchment.PageObjects.Exchange.ExchangeDFAdminLoginHomePage;
import com.parchment.PageObjects.Exchange.ExchangeLearnerRepositoryPage;
import com.parchment.PageObjects.Exchange.ExchangeLoginHomePage;
import com.parchment.PageObjects.Exchange.ExchangeloginPage;
import com.parchment.PageObjects.Exchange.NewUIDFAdminSupportToolPage;
import com.parchment.PageObjects.Pcom.ParchmentHomePage;
import com.parchment.PageObjects.Pcom.StudentLoginHomePage;
import com.parchment.PageObjects.Pcom.StudentLoginPage;
import com.parchment.PageObjects.Pcom.StudentSignUpNonSSOPage;
import com.parchment.Util.Environment;
import com.parchment.Util.IWait;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Login {

    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    private String sendingSchool, receiverCollege;

    public String getSendingSchool() {
        return sendingSchool;
    }

    private void setSendingSchool(String sendingSchool) {
        this.sendingSchool = sendingSchool;
    }

    public String getReceiverCollege() {
        return receiverCollege;
    }

    private void setReceiverCollege(String receiverCollege) {
        this.receiverCollege = receiverCollege;
    }

    public boolean loginViaSchoolSearch(WebDriver driver, String schoolName) {
        return loginViaSchoolSearch(driver, schoolName, null);
    }

    public boolean loginViaSchoolSearch(WebDriver driver, String schoolName, String userName) {
        try {
            IWait iWait = new IWait();
            iWait.implicit_wait(driver, 5);
            ParchmentHomePage parchmentHomePage = PageFactory.initElements(driver, ParchmentHomePage.class);
            parchmentHomePage.schoolSearchInput.sendKeys(schoolName);
            parchmentHomePage.schoolSearchButton.click();
            Thread.sleep(2000);
            new WebDriverWait(driver, 30).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[.='" + schoolName + "']")));
            driver.findElement(By.xpath("//h4[.='" + schoolName + "']")).click();
            StudentSignUpNonSSOPage studentSignUpNonSSOPage = PageFactory
                    .initElements(driver, StudentSignUpNonSSOPage.class);
            studentSignUpNonSSOPage.userName.sendKeys(userName);
            studentSignUpNonSSOPage.password.sendKeys("Qwerty@1");
            studentSignUpNonSSOPage.signUpBtn.click();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }

    public boolean loginWithOutUserName(WebDriver driver, String loginType, String studentEmail) {
        return login(driver, loginType, studentEmail);
    }

    private String getUrl(String env, String loginType) {
        String url;
        if (loginType.equalsIgnoreCase("supporttool")) {
            url = new ConstantSource().getValue(env + "supporttoolurl");
        } else {
            url = new ConstantSource().getValue(env);
        }
        return url;
    }

    public boolean login(WebDriver driver, String loginType, String userName) {
        try {
            IWait iWait = new IWait();
            String env = Environment.get();
            if (loginType.equalsIgnoreCase("supporttool")) {
                driver.get(getUrl(env, loginType));
            } else {
                driver.get(new ConstantSource().getValue(env));
            }
            iWait.implicit_wait(driver, 2);
            CMSHomePage cmsHomePage = PageFactory.initElements(driver, CMSHomePage.class);
            ExchangeloginPage exchangeloginPage = PageFactory.initElements(driver, ExchangeloginPage.class);
            StudentLoginPage studentLoginPage = PageFactory.initElements(driver, StudentLoginPage.class);
            StudentLoginHomePage studentLoginHomePage = PageFactory.initElements(driver, StudentLoginHomePage.class);
            ExchangeLoginHomePage exchangeLoginHomePage = PageFactory.initElements(driver, ExchangeLoginHomePage.class);
            ExchangeDFAdminLoginHomePage exchangeDFAdminLoginHomePage = PageFactory
                    .initElements(driver, ExchangeDFAdminLoginHomePage.class);
            AlphaLoginHomePage alphaLoginHomePage = PageFactory.initElements(driver, AlphaLoginHomePage.class);
            DeltaTPOPage deltaTPOPage = PageFactory.initElements(driver, DeltaTPOPage.class);
            AwardLoginNextpage awardLoginNextpage = PageFactory.initElements(driver, AwardLoginNextpage.class);
            AnalyzeHomePage analyzeHomePage = PageFactory.initElements(driver, AnalyzeHomePage.class);
            if (!loginType.equalsIgnoreCase("supporttool")) {
                iWait.explicit_wait(driver, cmsHomePage.login);
                Verify.verify(cmsHomePage.login.isDisplayed(), "verification failed");
                cmsHomePage.login.click();
            }
            if (!loginType.equalsIgnoreCase("student") && !loginType.equalsIgnoreCase("supporttool")) {
                iWait.explicit_wait(driver, cmsHomePage.adminlogin);
                cmsHomePage.adminlogin.click();
                iWait.explicit_wait(driver, exchangeloginPage.username);
                Verify.verify(exchangeloginPage.username.isDisplayed(), "verification failed");
                String password = getPassword(env, userName);
                exchangeloginPage.username.sendKeys(userName);
                exchangeloginPage.password.sendKeys(password);
                exchangeloginPage.SignInbtn.click();
                new ExchangeClosePopUp().close(driver);
            }
            switch (loginType.toLowerCase()) {
                case "student":
                    iWait.explicit_wait(driver, cmsHomePage.studentlogin);
                    cmsHomePage.studentlogin.click();
                    iWait.explicit_wait(driver, studentLoginPage.UserName);
                    Verify.verify(studentLoginPage.UserName.isDisplayed(), "verification failed");
                    String password = new ConstantSource().getValue("password");
                    studentLoginPage.UserName.sendKeys(userName);
                    studentLoginPage.Password.sendKeys(password);
                    studentLoginPage.SignInbtn.click();
                    boolean invalidAccount;
                    try {
                        Verify.verify(studentLoginPage.InvalidLogin.isDisplayed(), "verification failed");
                        invalidAccount = true;
                    } catch (Exception e) {
                        invalidAccount = false;
                    }
                    if (invalidAccount) {
                        logger.info("User name and password are not valid. please enter a valid username and password");
                        return false;
                    }
                    try {
                        iWait.explicit_wait(driver, studentLoginPage.collegeAdmissionSurvey, 30);
                        studentLoginPage.closeCollegeAdmissionSurveyModal.click();
                    } catch (Exception e) {
                    }
                    iWait.explicit_wait(driver, studentLoginHomePage.Dashboard);
                    Verify.verify(studentLoginHomePage.Dashboard.isDisplayed(), "verification failed");
                    Thread.sleep(5000);
                    break;
                case "supporttool":
                    NewUIDFAdminSupportToolPage ndfastp = PageFactory
                            .initElements(driver, NewUIDFAdminSupportToolPage.class);
                    iWait.explicit_wait(driver, ndfastp.signIntoYourAccount);
                    Verify.verify(ndfastp.signIntoYourAccount.isDisplayed(), "verification failed");
                    iWait.explicit_wait(driver, ndfastp.userName);
                    Verify.verify(ndfastp.userName.isDisplayed(), "verification failed");
                    password = new ConstantSource().getValue("password");
                    ndfastp.userName.sendKeys(userName);
                    ndfastp.password.sendKeys(password);
                    ndfastp.signInBtn.click();
                    try {
                        Verify.verify(ndfastp.authenticationError.isDisplayed(), "verification failed");
                        invalidAccount = true;
                    } catch (Exception e) {
                        invalidAccount = false;
                    }
                    if (invalidAccount) {
                        logger.info("User name and password are not valid. please enter a valid username and password");
                        return false;
                    }
                    iWait.explicit_wait(driver, ndfastp.orderSearch);
                    Verify.verify(ndfastp.orderSearch.isDisplayed(), "verification failed");
                    break;
                case "sender":
                    String senderName = new DatabaseConnection().schoolname(userName);
                    setSendingSchool(senderName);
                    break;
                case "receiver":
                    String receiverName = new DatabaseConnection().schoolname(userName);
                    setReceiverCollege(receiverName);
                    break;
                case "credentialissuer":
                    try {
                        iWait.explicit_wait(driver, awardLoginNextpage.loginAwardBtn);
                        awardLoginNextpage.loginAwardBtn.click();
                    } catch (Exception e) {
                    }
                    iWait.explicit_wait(driver, exchangeLoginHomePage.award);
                    Verify.verify(exchangeLoginHomePage.award.isDisplayed(), "verification failed");
                    return true;
                case "credentialreceiver":
                    try {
                        iWait.explicit_wait(driver, exchangeDFAdminLoginHomePage.receiver, 5);
                        exchangeDFAdminLoginHomePage.receiver.click();
                    } catch (Exception e) {
                    }
                    iWait.explicit_wait(driver, exchangeLoginHomePage.Profile);
                    Verify.verify(exchangeLoginHomePage.Profile.isDisplayed(), "verification failed");
                    senderName = new DatabaseConnection().schoolname(userName);
                    setSendingSchool(senderName);
                    return true;
                case "learneradmin":
                case "learnerviewer":
                    ExchangeLearnerRepositoryPage exchangeLearnerRepositoryPage = PageFactory
                            .initElements(driver, ExchangeLearnerRepositoryPage.class);
                    iWait.explicit_wait(driver, exchangeLearnerRepositoryPage.signout);
                    Verify.verify(exchangeLearnerRepositoryPage.signout.isDisplayed(), "verification failed");
                    return true;
                case "alphaadmin":
                case "alphasuperadmin":
                    try {
                        iWait.explicit_wait(driver, alphaLoginHomePage.exchangesendlink, 5);
                        alphaLoginHomePage.exchangesendlink.click();
                    } catch (Exception e) {
                    }
                    iWait.explicit_wait(driver, alphaLoginHomePage.storefront);
                    Verify.verify(alphaLoginHomePage.storefront.isDisplayed(), "verification failed");
                    return true;
                case "studentexchange":
                    closeAdminSurvey(driver);
                    iWait.explicit_wait(driver, studentLoginHomePage.Dashboard);
                    Verify.verify(studentLoginHomePage.Dashboard.isDisplayed(), "verification failed");
                    return true;
                case "analyze":
                    exchangeLoginHomePage.Profile.click();
                    iWait.explicit_wait(driver, analyzeHomePage.analyzeDropdown);
                    analyzeHomePage.analyzeDropdown.click();
                    return true;
                case "tpo":
                    Verify.verify(deltaTPOPage.tpoLandingPage.isDisplayed(), "verification failed");
                    return true;
                default:
                    try {
                        iWait.explicit_wait(driver, exchangeDFAdminLoginHomePage.receiver, 5);
                        exchangeDFAdminLoginHomePage.receiver.click();
                    } catch (Exception e) {
                    }
                    new ExchangeClosePopUp().close(driver);
                    try {
                        iWait.explicit_wait(driver, exchangeLoginHomePage.Profile);
                        Verify.verify(exchangeLoginHomePage.Profile.isDisplayed(), "verification failed");
                    } catch (Exception e) {
                        Verify.verify(exchangeLoginHomePage.sendersignout.isDisplayed(), "verification failed");
                    }
                    break;
            }
            /*try{
				elhp.receiveSection.click();
				iWait.implicit_wait(driver, 3);
			}
			catch(Exception e){

			}*/
            logger.info("logged in successfully");
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }

    private String getPassword(String env, String userName) {
        try {
            String password;
            if (userName.equalsIgnoreCase("qa.admin@test.tom") || userName.contains("@avowsystems.com")) {
                if (env.equalsIgnoreCase("qaex") || env.equalsIgnoreCase("beta")) {
                    if (userName.equals("eapi@avowsystems.com")) {
                        password = new ConstantSource().getValue("eapialphapassword");
                    } else {
                        password = new ConstantSource().getValue("alphapassword");
                    }
                } else {
                    switch (userName.toLowerCase()) {
                        case "qs+super@avowsystems.com":
                            password = new ConstantSource().getValue("betaalphasuperadminpassword");
                            break;
                        case "admin@avowsystems.com":
                            password = new ConstantSource().getValue("prodalphasuperadminpassword");
                            break;
                        default:
                            password = new ConstantSource().getValue("password");
                            break;
                    }
                }
            } else {
                password = new ConstantSource().getValue("password");
            }
            return password;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return null;
        }
    }

    public boolean analyzeLogin(WebDriver driver, String loginType, String userName) {
        try {
            IWait iWait = new IWait();
            String env = Environment.get();
            String url = new ConstantSource().getValue(env + "analyzeurl");
            driver.get(url);
            AnalyzeHomePage analyzeHomePage = PageFactory.initElements(driver, AnalyzeHomePage.class);
            String password = new ConstantSource().getValue("password");
            iWait.explicit_wait(driver, analyzeHomePage.userName);
            analyzeHomePage.userName.sendKeys(userName);
            analyzeHomePage.password.sendKeys(password);
            analyzeHomePage.submit.click();
            switch (loginType) {
                case "analyze":
                    iWait.explicit_wait(driver, analyzeHomePage.activityTab);
                    Verify.verify(analyzeHomePage.activityTab.isDisplayed(), "verification failed");
                    Verify.verify(analyzeHomePage.analysisTab.isDisplayed(), "verification failed");
                    Verify.verify(analyzeHomePage.peersTab.isDisplayed(), "verification failed");
                    Verify.verify(analyzeHomePage.regionTab.isDisplayed(), "verification failed");
                    break;
                case "student":
                    iWait.explicit_wait(driver, analyzeHomePage.analyzeLoginAlert);
                    Verify.verify(analyzeHomePage.analyzeLoginAlert.isDisplayed(), "verification failed");
                    break;
                default:
                    iWait.explicit_wait(driver, analyzeHomePage.acessDenied);
                    Verify.verify(analyzeHomePage.acessDenied.isDisplayed(), "verification failed");
                    break;
            }
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }

    public boolean deltaSchoolSearch(WebDriver driver, String env) {
        try {
            IWait iWait = new IWait();
            logger.info("Landing to https://qa-www.parchment.com for searching a school");
            String url = new ConstantSource().getValue(env);
            driver.get(url);
            iWait.implicit_wait(driver, 2);
            DeltaTPORegister dtr = PageFactory.initElements(driver, DeltaTPORegister.class);
            Verify.verify(dtr.schoolSearchBox.isDisplayed(), "verification failed");
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }

    private void closeAdminSurvey(WebDriver driver) {
        try {
            IWait iWait = new IWait();
            StudentLoginPage studentLoginPage = PageFactory.initElements(driver, StudentLoginPage.class);
            iWait.explicit_wait(driver, studentLoginPage.collegeAdmissionSurvey);
            studentLoginPage.closeCollegeAdmissionSurveyModal.click();
        } catch (Exception e) {

        }
    }

}
