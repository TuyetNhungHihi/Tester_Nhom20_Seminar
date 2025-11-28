# Assignment 7 - Triển khai Script Automation cho Chức Năng Cá Nhân

## Thông tin sinh viên
- Mã sinh viên: 22130200
- Họ tên: Nguyễn Thị Tuyết Nhung

## Thông tin Test Plan
- Trang web kiểm thử: Trang web bán giày HyperS https://hypershop-scys.onrender.com/login
- Link tổng hợp Test Case của dự án: https://docs.google.com/spreadsheets/d/1xVrq3FplyCkeqJZKiuPxqo2Y64ymvdl8o_tNVp0C1TE/edit?usp=sharing 
- Công cụ sử dụng: **Katalon Studio**
- Ngôn ngữ: **Groovy (Selenium WebDriver)**  

## Danh sách Test Case
Quản lý thông tin khách hàng:
- Customer_Filter_Activity_01: Lọc đúng danh sách khách hàng "Đang hoạt động"
- Customer_Filter_Disable_02: Lọc đúng danh sách khách hàng "Bị vô hiệu hóa"
- Customer_Search_Name_03: Tìm kiếm theo tên hiển thị đúng danh sách khách hàng phù hợp
- Customer_Search_ID_04: Tìm kiếm theo ID hiển thị đúng khách hàng tương ứng
- Customer_Status_Disable_05: Cập nhật đúng trạng thái của tài khoản sau khi bị vô hiệu hóa
- Customer_Status_Activty_06: Cập nhật đúng trạng thái của tài khoản sau khi được kích hoạt
- Customer_Valid_Info_07: Hiển thị đúng thông tin tài khoản
Quản lý tài khoản nhân viên:    
- AccEmployee_Insert_Miss_ID_01: Thông báo lỗi khi thêm thành viên mà thiếu trường "Mã nhân viên"
- AccEmployee_Insert_Miss_Name_02: Thông báo lỗi khi thêm thành viên mà thiếu trường "Tên nhân viên"
- AccEmployee_Insert_Miss_Email_03: Thông báo lỗi khi thêm thành viên mà thiếu trường "Email"
- AccEmployee_Insert_Duplicate_Email_04: Thông báo lỗi khi thêm thành viên mà trùng trường "Email"
- AccEmployee_Insert_Duplicate_ID_05: Thông báo lỗi khi thêm thành viên mà trùng trường "Mã nhân viên"
- AccEmployee_Insert_Success_06: Thêm nhân viên thành công
- AccEmployee_Insert_Invalid_Email_07: Thông báo lỗi khi thêm thành viên mà trường "Email" sai định dạng
- AccEmployee_Insert_Invalid_Phone_08: Thông báo lỗi khi thêm thành viên mà trường "Số điện thoại" sai định dạng
- AccEmployee_Insert_Invalid_Date_09: Thông báo lỗi khi thêm nhân viên mà ngày sinh được chọn ra dưới 15 tuổi tính từ năm 2025
- AccEmployee_Update_Name_Success_10: Sửa "Tên" nhân viên thành công
- AccEmployee_Cancel_Update_11: Ngưng quá quá trình sửa tên nhân viên
- AccEmployee_Status_Disable_12: Cập nhật đúng trạng thái của tài khoản sau khi bị vô hiệu hóa
- AccEmployee_Status_Activity_13: Cập nhật đúng trạng thái của tài khoản sau khi được kích hoạt                                        

---


# Danh sách Script của từng Testcase đã thực hiện

## Customer_Filter_Activity_01: Lọc đúng danh sách khách hàng "Đang hoạt động"
- Mô tả: Passed khi danh sách chỉ hiển thị tài khoản có trạng thái "Đang hoạt động". Failed khi có trạng thái khác

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Login_success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(findTestObject('Page_HyperS/button_quan_ly_tai_khoan'))

WebUI.verifyElementText(findTestObject('Page_HyperS/button_thong_tin_khach_hang'), 'Thông tin khách hàng')

WebUI.click(findTestObject('Page_HyperS/button_thong_tin_khach_hang'))


WebUI.click(findTestObject('Page_HyperS/button_tong_acc'))

WebUI.click(findTestObject('Page_HyperS/button_loc_hoat_dong'))


//phân trang
List<String> allStatuses = []

while (true) {
    WebUI.delay(1)

    allStatuses.addAll(getStatusListInPage())

    if (!(hasNextPage())) {
        println('>> HẾT TRANG')

        break
    }
    
    WebUI.waitForElementClickable(findTestObject('Page_HyperS/button_next_page'), 5)

    WebUI.click(findTestObject('Page_HyperS/button_next_page'))

    WebUI.delay(1)
}

boolean isValid = true

allStatuses.each({ def st ->
        if (!(st.contains('hoạt động'))) {
            isValid = false

            println(' Phát hiện trạng thái không hợp lệ: ' + st)
        }
    })

if (!(isValid)) {
    com.kms.katalon.core.util.KeywordUtil.markFailed(' FAILED: Danh sách chứa trạng thái KHÔNG PHẢI \'Hoạt động\'!')
} else {
    com.kms.katalon.core.util.KeywordUtil.markPassed(' PASSED: Tất cả trạng thái đều là \'Hoạt động\'.')
}


// lấy trạng thái trong 1 trang
List<String> getStatusListInPage() {
    List<WebElement> statusElements = com.kms.katalon.core.webui.common.WebUiCommonHelper.findWebElements(findTestObject(
            'Page_HyperS/col_Status'), 10)

    return statusElements.collect({ 
            it.getText().trim().toLowerCase()
        })
}

boolean hasNextPage() {
    boolean isDisabled = WebUI.verifyElementPresent(findTestObject('Page_HyperS/button_nextPage_disable'), 1, FailureHandling.OPTIONAL)

    if (isDisabled) {
        println('>> Đã đến trang cuối')

        return false
    }
    
    boolean isEnabled = WebUI.verifyElementPresent(findTestObject('Page_HyperS/button_next_page'), 1, FailureHandling.OPTIONAL)

    if (isEnabled) {
        println('>> Còn trang tiếp theo')

        return true
    }
    
    println('>> Không tìm thấy nút NEXT (DOM đang load). Chờ 1s...')

    WebUI.delay(1)

    return hasNextPage()
}
```


---

## Customer_Filter_Disable_02: Lọc đúng danh sách khách hàng "Bị vô hiệu hóa"
- Mô tả: Passed khi danh sách chỉ hiển thị tài khoản có trạng thái "Vô hiệu". Failed khi có trạng thái khác

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Login_success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(findTestObject('Page_HyperS/button_quan_ly_tai_khoan'))

WebUI.verifyElementText(findTestObject('Page_HyperS/button_thong_tin_khach_hang'), 'Thông tin khách hàng')

WebUI.click(findTestObject('Page_HyperS/button_thong_tin_khach_hang'))

WebUI.click(findTestObject('Page_HyperS/button_tong_acc'))

WebUI.click(findTestObject('Page_HyperS/button_loc_vo_hieu_hoa'))

//phân trang
List<String> allStatuses = []

while (true) {
    WebUI.delay(1)

    allStatuses.addAll(getStatusListInPage())

    if (!(hasNextPage())) {
        println('>> HẾT TRANG')

        break
    }
    
    WebUI.waitForElementClickable(findTestObject('Page_HyperS/button_next_page'), 5)

    WebUI.click(findTestObject('Page_HyperS/button_next_page'))

    WebUI.delay(1)
}

boolean isValid = true

allStatuses.each({ def st ->
        if (!(st.contains('vô hiệu'))) {
            isValid = false

            println(' Phát hiện trạng thái không hợp lệ: ' + st)
        }
    })

if (!(isValid)) {
    com.kms.katalon.core.util.KeywordUtil.markFailed(' FAILED: Danh sách chứa trạng thái KHÔNG PHẢI \'vô hiệu\'!')
} // lấy trạng thái trong 1 trang
else {
    com.kms.katalon.core.util.KeywordUtil.markPassed(' PASSED: Tất cả trạng thái đều là \'vô hiệu\'.')
}

List<String> getStatusListInPage() {
    List<WebElement> statusElements = com.kms.katalon.core.webui.common.WebUiCommonHelper.findWebElements(findTestObject(
            'Page_HyperS/col_Status'), 10)

    return statusElements.collect({ 
            it.getText().trim().toLowerCase()
        })
}

boolean hasNextPage() {
    boolean isDisabled = WebUI.verifyElementPresent(findTestObject('Page_HyperS/button_nextPage_disable'), 1, FailureHandling.OPTIONAL)

    if (isDisabled) {
        println('>> Đã đến trang cuối')

        return false
    }
    
    boolean isEnabled = WebUI.verifyElementPresent(findTestObject('Page_HyperS/button_next_page'), 1, FailureHandling.OPTIONAL)

    if (isEnabled) {
        println('>> Còn trang tiếp theo')

        return true
    }
    
    println('>> Không tìm thấy nút NEXT (DOM đang load). Chờ 1s...')

    WebUI.delay(1)

    return hasNextPage()
}

```
## Customer_Search_Name_03: Tìm kiếm theo tên hiển thị đúng danh sách khách hàng phù hợp
- Mô tả: Passed khi danh sách chỉ hiển thị tài khoản có tên đúng với từ khóa tìm kiếm là "Đỗ Quốc Thành", xét cả ký tự in hoa. Failed khi có tên khác xuất hiện.

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.util.concurrent.ConcurrentHashMap.KeySetView as KeySetView
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Login_success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

WebUI.click(findTestObject('Page_HyperS/button_quan_ly_tai_khoan'))

WebUI.verifyElementText(findTestObject('Page_HyperS/button_thong_tin_khach_hang'), 'Thông tin khách hàng')

WebUI.click(findTestObject('Page_HyperS/button_thong_tin_khach_hang'))

String keyword = "Đỗ Quốc Thành"

WebUI.setText(findTestObject('TC_find/Page_HyperS/input_key_find'), keyword)
WebUI.sendKeys(findTestObject('TC_find/Page_HyperS/input_key_find'), Keys.chord(Keys.ENTER))
WebUI.delay(2)

// LẤY DANH SÁCH TÊN TRÊN TẤT CẢ CÁC TRANG
List<String> allNames = []

while (true) {

    List<WebElement> nameElements = WebUiCommonHelper.findWebElements(
        findTestObject('TC_find/Page_HyperS/colName'),
        10
    )

    // Lấy text với loại bỏ prefix "Tên: "
    allNames.addAll(
        nameElements.collect { 
            it.getText()
              .replace("Tên:", "")
              .trim()
        }
    )

    boolean isLastPage = WebUI.verifyElementPresent(
        findTestObject('Page_HyperS/button_nextPage_disable'),
        2,
        FailureHandling.OPTIONAL
    )

    if (isLastPage) {
        println(">> Hết trang")
        break
    }

    // --- KIỂM TRA NÚT NEXT ENABLED ---
    boolean canNext = WebUI.verifyElementPresent(
        findTestObject('Page_HyperS/button_next_page'),
        2,
        FailureHandling.OPTIONAL
    )

    if (!canNext) {
        println("KHÔNG tìm thấy nút NEXT ENABLED -> dừng vòng lặp")
        break
    }

    WebUI.click(findTestObject('Page_HyperS/button_next_page'))
    WebUI.delay(1)
}

// KIỂM TRA TỪNG TÊN

boolean valid = true

allNames.each { fullName ->
    if (!fullName.equals(keyword)) {
        valid = false
        println("PHÁT HIỆN TÊN SAI: " + fullName)
    }
}

if (!valid) {
    KeywordUtil.markFailed("FAILED: Có tên không trùng khớp với '" + keyword + "'")
} else {
    KeywordUtil.markPassed("PASSED: Tất cả kết quả đều chính xác là: '" + keyword + "'")
}

WebUI.closeBrowser()
```
## Customer_Search_ID_04: Tìm kiếm theo ID hiển thị đúng khách hàng tương ứng
- Mô tả: Passed khi danh sách chỉ hiển thị tài khoản có Mã khách hàng đúng với từ khóa tìm kiếm là "USER001", xét cả ký tự in hoa. Kiểm tra bằng cách chọn vào tài khoản của người có ID đó trong danh sách, sau khi thông tin hiện lên thẻ "Thông tin khách hàng" thì quan sát trường "Mã khách hàng". Failed khi có Mã khác với Mã đã tìm kiếm.


```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Login_success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(findTestObject('Page_HyperS/button_quan_ly_tai_khoan'))

WebUI.verifyElementText(findTestObject('Page_HyperS/button_thong_tin_khach_hang'), 'Thông tin khách hàng')

WebUI.click(findTestObject('Page_HyperS/button_thong_tin_khach_hang'))

String keyword = "USER001"

WebUI.setText(findTestObject('TC_find/Page_HyperS/input_key_find'), keyword)

WebUI.sendKeys(findTestObject('TC_find/Page_HyperS/input_key_find'), Keys.chord(Keys.ENTER))

WebUI.waitForPageLoad(5)

WebUI.delay(1)

List<WebElement> rows = WebUiCommonHelper.findWebElements(
    findTestObject('TC_find/Page_HyperS/rowItem'), 
    10
)

// id thì chỉ có duy nhất 1 người nên chỉ trả 1 dùng

if (rows.size() != 1) {
    KeywordUtil.markFailed("FAILED: Phải chỉ có 1 kết quả, nhưng tìm thấy: " + rows.size())
    WebUI.closeBrowser()
    return
}

rows[0].click()
WebUI.delay(1)

TestObject customerIdField = findTestObject('TC_find/Page_HyperS/id_in_info')

WebUI.waitForElementVisible(customerIdField, 10)

String formCustomerId = WebUI.getAttribute(customerIdField, 'value').trim()

println("→ ID trong form hiển thị: " + formCustomerId)

// kiểm tra nếu id trong thẻ thông tin khách hàng có trùng với id trong tìm kiếm
 
if (formCustomerId.equals(keyword)) {
    KeywordUtil.markPassed("PASSED: ID trong form trùng với từ khóa '${keyword}'")
} else {
    KeywordUtil.markFailed("FAILED: ID trong form = '${formCustomerId}', nhưng phải là '${keyword}'")
}

WebUI.closeBrowser()

```
## Customer_Status_Disable_05: Cập nhật đúng trạng thái của tài khoản sau khi bị vô hiệu hóa
- Mô tả: Chọn vào dấu "..." ở cột trạng thái của tài khoản đang hoạt động "Đỗ Quốc thành" sau đó chọn "Vô hiệu hóa". Passed khi cột trạng thái đổi sang "Vô hiệu", failed nếu cột trạng thái không đổi hoặc có trạng thái khác

```groovy
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Login_success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(findTestObject('Page_HyperS/button_quan_ly_tai_khoan'))

WebUI.verifyElementText(findTestObject('Page_HyperS/button_thong_tin_khach_hang'), 'Thông tin khách hàng')

WebUI.click(findTestObject('Page_HyperS/button_thong_tin_khach_hang'))

// CLICK NÚT ⋯ Ở CUỐI HÀNG CỦA ACC HOẠT ĐỘNG
WebUI.waitForElementClickable(findTestObject('TC_Status/Page_HyperS/menu_trang_thai'), 10)

WebUI.click(findTestObject('TC_Status/Page_HyperS/menu_trang_thai'))

// 2) CLICK “Vô hiệu hóa” TRONG MENU 
WebUI.verifyElementText(findTestObject('TC_Status/Page_HyperS/button_vo_hieu_hoa'), 'Vô hiệu hóa')

WebUI.click(findTestObject('TC_Status/Page_HyperS/button_vo_hieu_hoa'))

WebUI.delay(2)

// KIỂM TRA TRẠNG THÁI TRẢ VỀ "Vô hiệu"
String status = WebUI.getText(findTestObject('TC_Status/Page_HyperS/trang_thai')).trim()

println('→ Trạng thái trả về: ' + status)

if (status.equalsIgnoreCase('Vô hiệu') || status.equalsIgnoreCase('Vô hiệu hóa')) {
    KeywordUtil.markPassed('PASSED: Tài khoản đã chuyển sang trạng thái VÔ HIỆU.')
} else {
    KeywordUtil.markFailed(('FAILED: Trạng thái sai! Nhận được: \'' + status) + '\'')
}


```
## Customer_Status_Activty_06: Cập nhật đúng trạng thái của tài khoản sau khi được kích hoạt
- Mô tả: Sau khi chạy test case Customer_Disable_Activty_05 thì tài khoản "Đỗ Quốc Thành" đã chuyển sang trạng thái "Vô hiệu". Tiếp tục chạy bước Chọn vào dấu "..." ở cột trạng thái của tài khoản đang hoạt động "Đỗ Quốc thành" sau đó chọn "Kích hoạt". Passed khi cột trạng thái đổi sang "Hoạt động", failed nếu cột trạng thái không đổi hoặc có trạng thái khác

```groovy
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.Keys as Keys


WebUI.callTestCase(findTestCase('Customer_Status_Disable_05'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

// CLICK NÚT ⋯ Ở CUỐI HÀNG CỦA ACC HOẠT ĐỘNG
WebUI.waitForElementClickable(findTestObject('TC_Status/Page_HyperS/menu_trang_thai'), 10)

WebUI.click(findTestObject('TC_Status/Page_HyperS/menu_trang_thai'))

// 2) CLICK “kích hoạt” TRONG MENU 
WebUI.verifyElementText(findTestObject('TC_Status/Page_HyperS/button_kich_hoat'), 'Kích hoạt')

WebUI.click(findTestObject('TC_Status/Page_HyperS/button_kich_hoat'))

WebUI.delay(2)

// KIỂM TRA TRẠNG THÁI TRẢ VỀ "hoạt động"
String status = WebUI.getText(findTestObject('TC_Status/Page_HyperS/trang_thai')).trim()

println('→ Trạng thái trả về: ' + status)

if (status.equalsIgnoreCase('Hoạt động') || status.equalsIgnoreCase('Đang hoạt động')) {
    KeywordUtil.markPassed('PASSED: Tài khoản đã chuyển sang trạng thái KÍCH HOẠT.')
} else {
    KeywordUtil.markFailed(('FAILED: Trạng thái sai! Nhận được: \'' + status) + '\'')
}

```
## Customer_Valid_Info_07: Hiển thị đúng thông tin tài khoản
- Mô tả: Chọn vào tài khoản "Đỗ Quốc Thành". Sau khi thẻ "Thông tin khách hàng" hiện lên, kiểm tra thông tin trên thẻ có hiển thị đúng với thông tin tài khoản dưới danh sách. Passed nếu tất cả các trường đề đúng, Failed nếu có 1 trường sai

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase("Login_success"), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

WebUI.click(findTestObject('Page_HyperS/button_quan_ly_tai_khoan'))

WebUI.click(findTestObject('Page_HyperS/button_thong_tin_khach_hang'))

WebUI.delay(5)

WebUI.click(findTestObject('TC_find/Page_HyperS/Page_HyperS/danh_sach_acc'))

var_name = WebUI.getText(findTestObject('TC_find/Page_HyperS/Page_HyperS/cell_row_name'))

var_date = WebUI.getText(findTestObject('TC_find/Page_HyperS/Page_HyperS/cell_row_date'))

var_gender = WebUI.getText(findTestObject('TC_find/Page_HyperS/Page_HyperS/cell_row_gender'))

var_phone = WebUI.getText(findTestObject('TC_find/Page_HyperS/Page_HyperS/cell_row_phone'))

var_mail = WebUI.getText(findTestObject('TC_find/Page_HyperS/Page_HyperS/cell_row_mail'))

var_name_form = WebUI.getAttribute(findTestObject('TC02/input_name_form'), 'value')

var_gender_form = WebUI.getText(findTestObject('TC02/input_gender_form')).trim()

var_date_form = WebUI.getAttribute(findTestObject('TC02/input_date_form'), 'value')

var_phone_form = WebUI.getAttribute(findTestObject('TC02/input_phone_form'), 'value')

var_mail_form = WebUI.getAttribute(findTestObject('TC02/input_mail_form'), 'value')

String acctualName = WebUI.getText(findTestObject('Object Repository/TC_find/Page_HyperS/Page_HyperS/cell_row_name'))

acctualName = acctualName.replace('Tên:', '').trim()

WebUI.verifyMatch(acctualName, 'Đỗ Quốc Thành', false)

WebUI.verifyMatch(var_gender, var_gender_form, false)

String actualDate = WebUI.getText(findTestObject('Object Repository/TC_find/Page_HyperS/Page_HyperS/cell_row_date')).trim()

String expectedDate = '25/09/2003'

actualDate = actualDate.replace('-', '/')

expectedDate = expectedDate.replace('/', '-')

WebUI.verifyMatch(var_date.replace("-", "/"), var_date_form.replace("-", "/"), false)

WebUI.closeBrowser()


```
### AccEmployee_Insert_Miss_ID_01: Thông báo lỗi khi thêm thành viên mà thiếu trường "Mã nhân viên"
- Mô tả: Trong form thêm mới nhân viên với trường đầu tiên là "Mã nhân viên", bỏ qua trường này và nhập trường "Tên nhân viên" sau đó click vào button "Thêm mới nhân viên". Passed nếu hệ thống trả về thông báo lỗi "Vui lòng nhập lại thông tin ID". Failed nếu hệ thống thông báo tin nhắn khác

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Login_success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(findTestObject('Page_HyperS/button_quan_ly_tai_khoan'))

WebUI.verifyElementText(findTestObject('TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien'), 'Thông tin nhân viên')

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien'))

WebUI.verifyElementText(findTestObject('TC_insert_employee/Page_HyperS/the_thong_tin_nhan_vien'), 'THÔNG TIN NHÂN VIÊN')

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_ten'), 'Nguyễn Hihi')

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_them_moi_nhan_vien'))

WebUI.delay(0.4)

boolean hasError = WebUI.verifyTextPresent('Vui lòng nhập lại thông tin ID', false, FailureHandling.OPTIONAL)

if (hasError) {
    WebUI.comment('→ PASSED: Hệ thống hiển thị thông báo lỗi đúng.')
} else {
    WebUI.takeScreenshot()

    WebUI.verifyFailed('FAILED: Không tìm thấy thông báo \'Vui lòng nhập lại thông tin ID\'')
}


```
## AccEmployee_Insert_Miss_Name_02: Thông báo lỗi khi thêm thành viên mà thiếu trường "Tên nhân viên"
- Mô tả: Trong form thêm mới nhân viên, nhập vào trường "Mã nhân viên" sau đó click vào button "Thêm mới nhân viên". Passed nếu hệ thống trả về thông báo lỗi "Vui lòng nhập lại thông tin họ tên". Failed nếu hệ thống thông báo tin nhắn khác

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Login_success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(findTestObject('Page_HyperS/button_quan_ly_tai_khoan'))

WebUI.verifyElementText(findTestObject('TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien'), 'Thông tin nhân viên')

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien'))

WebUI.verifyElementText(findTestObject('TC_insert_employee/Page_HyperS/the_thong_tin_nhan_vien'), 'THÔNG TIN NHÂN VIÊN')

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_id'), 'USER100')

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_them_moi_nhan_vien'))

WebUI.delay(0.4)

boolean hasError = WebUI.verifyTextPresent('Vui lòng nhập lại thông tin họ tên', false, FailureHandling.OPTIONAL)

if (hasError) {
    WebUI.comment('→ PASSED: Hệ thống hiển thị thông báo lỗi đúng.')
} else {
    WebUI.takeScreenshot()

    WebUI.verifyFailed('FAILED: Không tìm thấy thông báo')
}


```
## AccEmployee_Insert_Miss_Email_03: Thông báo lỗi khi thêm thành viên mà thiếu trường "Email"
- Mô tả: Trong form thêm mới nhân viên, nhập vào trường "Mã nhân viên" và "Tên nhân viên" sau đó click vào button "Thêm mới nhân viên". Passed nếu hệ thống trả về thông báo lỗi "Vui lòng nhập lại thông tin Email theo dạng @ .com". Failed nếu hệ thống thông báo tin nhắn khác

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Login_success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(findTestObject('Page_HyperS/button_quan_ly_tai_khoan'))

WebUI.verifyElementText(findTestObject('TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien'), 'Thông tin nhân viên')

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien'))

WebUI.verifyElementText(findTestObject('TC_insert_employee/Page_HyperS/the_thong_tin_nhan_vien'), 'THÔNG TIN NHÂN VIÊN')

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_id'), 'USER100')

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_ten'), 'Nguyễn Hihi')

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_them_moi_nhan_vien'))

WebUI.delay(0.4)

boolean hasError = WebUI.verifyTextPresent('Vui lòng nhập lại thông tin Email theo dạng @ .com', false, FailureHandling.OPTIONAL)

if (hasError) {
    WebUI.comment('→ PASSED: Hệ thống hiển thị thông báo lỗi đúng.')
} else {
    WebUI.takeScreenshot()

    WebUI.verifyFailed('FAILED: Không tìm thấy thông báo')
}

WebUI.closeBrowser()


```
## AccEmployee_Insert_Duplicate_Email_04: Thông báo lỗi khi thêm thành viên mà trùng trường "Email"
- Mô tả: Trong form thêm mới nhân viên, nhập đầy đủ thông tin nhưng đối với trường "Email" thì nhập lại một Email đã tồn tại rồi và nhấn button "Thêm mới nhân viên". Passed nếu hệ thống trả về thông báo lỗi "Email đã tồn tại" và không cho phéo thêm nhân viên. Failed nếu hệ thống thông báo tin nhắn khác hoặc không thông báo và thêm mới nhân viên thành công

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Login_success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(findTestObject('Page_HyperS/button_quan_ly_tai_khoan'))

WebUI.verifyElementText(findTestObject('TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien'), 'Thông tin nhân viên')

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien'))

WebUI.verifyElementText(findTestObject('TC_insert_employee/Page_HyperS/the_thong_tin_nhan_vien'), 'THÔNG TIN NHÂN VIÊN')

WebUI.scrollToElement(findTestObject('TC_insert_employee/Page_HyperS/input_id'), 1)

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_id'), 'USER100')

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_ten'), 'Nguyễn Hihi')

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_mail'), 'testnv@gmail.com')

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_sdt'), '0345093066')

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/input_ngay_sinh'))

WebUI.delay(1)

// 7. kiểm tra lịch
boolean isCalendarVisible = WebUI.verifyElementPresent(findTestObject('TC_insert_employee/Page_HyperS/popup_lich'), 3, FailureHandling.OPTIONAL)

if (!(isCalendarVisible)) {
    WebUI.verifyFailed('FAILED: Hệ thống KHÔNG hiển thị lịch khi click vào trường Ngày sinh')
} else {
    WebUI.comment('→ PASSED: Lịch hiển thị đúng khi click trường Ngày sinh.')
}

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/chon_ngay_sinh'), FailureHandling.STOP_ON_FAILURE)

WebUI.scrollToElement(findTestObject('TC_insert_employee/Page_HyperS/input_gioi_tinh'), 1)

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/input_gioi_tinh'))

// 8. KIỂM TRA DROPDOWN GIỚI TÍNH
boolean hasNam = WebUI.verifyTextPresent('Nam', false, FailureHandling.OPTIONAL)

boolean hasNu = WebUI.verifyTextPresent('Nữ', false, FailureHandling.OPTIONAL)

if (hasNam && hasNu) {
    WebUI.comment('→ PASSED: Dropdown giới tính hiển thị đúng: Nam & Nữ.')
} else {
    WebUI.verifyFailed('FAILED: Dropdown giới tính không hiển thị đủ 2 lựa chọn Nam/Nữ')
}

// Chọn Nữ
WebUI.executeJavaScript('arguments[0].click()', [WebUI.findWebElement(findTestObject('TC_insert_employee/Page_HyperS/gioi_tinh_nu'))])

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_ten_duong'), 'Số 10')

// 11.KIỂM TRA DROP LIST CHỨC DANH
WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_dia_chi_cu_the'), 'Nông Lâm')

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/input_chuc_danh'))

boolean hasBM = WebUI.verifyTextPresent('BillManager', false, FailureHandling.OPTIONAL)

boolean hasEM = WebUI.verifyTextPresent('EventManager', false, FailureHandling.OPTIONAL)

boolean hasPM = WebUI.verifyTextPresent('ProductManager', false, FailureHandling.OPTIONAL)

if ((hasBM && hasEM) && hasPM) {
    WebUI.comment('→ PASSED: Dropdown chức danh hiển thị đúng 3 lựa chọn.')
} else {
    WebUI.verifyFailed('FAILED: Dropdown chức danh không đủ 3 lựa chọn yêu cầu.')
}

// Chọn BillManager
WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/billManager'))

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_them_moi_nhan_vien'))

WebUI.delay(0.6)

// KIỂM TRA LỖI "EMAIL ĐÃ TỒN TẠI"
boolean hasError = WebUI.verifyTextPresent('Email đã tồn tại', false, FailureHandling.OPTIONAL)

if (hasError) {
    WebUI.comment('→ PASSED: Hệ thống hiển thị thông báo lỗi đúng.')
} else {
    WebUI.takeScreenshot()

    KeywordUtil.markFailed('FAILED: Không hiển thị lỗi \'Email đã tồn tại\'')
}

WebUI.closeBrowser()


```
## AccEmployee_Insert_Duplicate_ID_05: Thông báo lỗi khi thêm thành viên mà trùng trường "Mã nhân viên"
- Mô tả: Trong form thêm mới nhân viên, nhập đầy đủ thông tin nhưng đối với trường "Mã nhân viên" thì nhập lại một ID đã tồn tại rồi và nhấn button "Thêm mới nhân viên". Passed nếu hệ thống trả về thông báo lỗi "ID đã tồn tại" và không cho phéo thêm nhân viên. Failed nếu hệ thống thông báo tin nhắn khác hoặc không thông báo và thêm mới nhân viên thành công

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Login_success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(findTestObject('Page_HyperS/button_quan_ly_tai_khoan'))

WebUI.verifyElementText(findTestObject('TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien'), 'Thông tin nhân viên')

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien'))

WebUI.verifyElementText(findTestObject('TC_insert_employee/Page_HyperS/the_thong_tin_nhan_vien'), 'THÔNG TIN NHÂN VIÊN')

WebUI.scrollToElement(findTestObject('TC_insert_employee/Page_HyperS/input_id'), 1)

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_id'), 'TTTEM')

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_ten'), 'Nguyễn Hihi')

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_mail'), 'Hihi@gmail.com')

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_sdt'), '0345093066')

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/input_ngay_sinh'))

WebUI.delay(1)

// 7. kiểm tra lịch
boolean isCalendarVisible = WebUI.verifyElementPresent(findTestObject('TC_insert_employee/Page_HyperS/popup_lich'), 3, FailureHandling.OPTIONAL)

if (!(isCalendarVisible)) {
    WebUI.verifyFailed('FAILED: Hệ thống KHÔNG hiển thị lịch khi click vào trường Ngày sinh')
} else {
    WebUI.comment('→ PASSED: Lịch hiển thị đúng khi click trường Ngày sinh.')
}

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/chon_ngay_sinh'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/input_gioi_tinh'))

// 8. KIỂM TRA DROPDOWN GIỚI TÍNH
boolean hasNam = WebUI.verifyTextPresent('Nam', false, FailureHandling.OPTIONAL)

boolean hasNu = WebUI.verifyTextPresent('Nữ', false, FailureHandling.OPTIONAL)

if (hasNam && hasNu) {
    WebUI.comment('→ PASSED: Dropdown giới tính hiển thị đúng: Nam & Nữ.')
} else {
    WebUI.verifyFailed('FAILED: Dropdown giới tính không hiển thị đủ 2 lựa chọn Nam/Nữ')
}

// Chọn Nam
WebUI.executeJavaScript('arguments[0].click()', [WebUI.findWebElement(findTestObject('TC_insert_employee/Page_HyperS/gioi_tinh_nam'))])

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_ten_duong'), 'Số 10')

// 11.KIỂM TRA DROP LIST CHỨC DANH
WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_dia_chi_cu_the'), 'Nông Lâm')

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/input_chuc_danh'))

boolean hasBM = WebUI.verifyTextPresent('BillManager', false, FailureHandling.OPTIONAL)

boolean hasEM = WebUI.verifyTextPresent('EventManager', false, FailureHandling.OPTIONAL)

boolean hasPM = WebUI.verifyTextPresent('ProductManager', false, FailureHandling.OPTIONAL)

if ((hasBM && hasEM) && hasPM) {
    WebUI.comment('→ PASSED: Dropdown chức danh hiển thị đúng 3 lựa chọn.')
} else {
    WebUI.verifyFailed('FAILED: Dropdown chức danh không đủ 3 lựa chọn yêu cầu.')
}

// Chọn BillManager
WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/billManager'))

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_them_moi_nhan_vien'))

WebUI.delay(0.6)

// KIỂM TRA LỖI "MÃ NHÂN VIÊN ĐÃ TỒN TẠI"
boolean hasError = WebUI.verifyTextPresent('Mã nhân viên đã tồn tại', false, FailureHandling.OPTIONAL)

if (hasError) {
    WebUI.comment('→ PASSED: Hệ thống hiển thị thông báo lỗi đúng.')
} else {
    WebUI.takeScreenshot()

    KeywordUtil.markFailed('FAILED: Không hiển thị lỗi \'Email đã tồn tại\'')
}

WebUI.closeBrowser()


```
## AccEmployee_Insert_Success_06: Thêm nhân viên thành công
- Mô tả: Trong form thêm mới nhân viên, nhập đầy đủ thông tin hợp lệ và nhấn button "Thêm mới nhân viên". Passed nếu hệ thống trả về thông báo lỗi "Thêm mới nhân viên thành công" và ở cuối danh sách trả về tài khoản có thông tin trùng khớp với thông tin nhân viên vừa được thêm vào. Failed nếu có thông báo khác trả về hoặc có 1 thông tin không trùng khớp với thông tin vừa được thêm

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.Keys as Keys


TestObject createChild(String baseXpath, String childName, String className) {
    TestObject child = new TestObject(childName)
    child.addProperty("xpath", ConditionType.EQUALS, baseXpath + "//div[@class='" + className + "']")
    return child
}

// DATA TEST

String newID = "USER106"
String newName = "Nguyễn Hehe"
String newEmail = "Hehe06@gmail.com"
String newPhone = "0345093073"
String newBirthday = "26-10-2025"
String newStatus = "Hoạt động"

WebUI.callTestCase(findTestCase('Login_success'), [:], com.kms.katalon.core.model.FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(findTestObject('Page_HyperS/button_quan_ly_tai_khoan'))
WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien'))

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_id'), newID)
WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_ten'), newName)
WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_mail'), newEmail)
WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_sdt'), newPhone)

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/input_ngay_sinh'))
WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/chon_ngay_sinh'))
WebUI.clickOffset(findTestObject('TC_insert_employee/Page_HyperS/the_thong_tin_nhan_vien'), -200, 0)

WebUI.scrollToElement(findTestObject('TC_insert_employee/Page_HyperS/input_gioi_tinh'), 1)
WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/input_gioi_tinh'))
WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/gioi_tinh_nu'))

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_ten_duong'), 'Số 10')
WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_dia_chi_cu_the'), 'Nông Lâm')

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/input_chuc_danh'))
WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/billManager'))

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_them_moi_nhan_vien'))
WebUI.verifyTextPresent('Thêm mới thành công', false)

// NEXT PAGE TỚI TRANG CUỐI

TestObject lastPage = findTestObject('TC_insert_employee/Page_HyperS/button_last_page')

WebUI.waitForElementClickable(lastPage, 5)

WebUI.click(lastPage)

WebUI.delay(1)

// KIỂM TRA ROW MỚI TẠO

String baseXpath = "//tr[.//div[@class='id'][contains(text(),'ID: " + newID + "')]]"

TestObject row = new TestObject("row_newID")
row.addProperty("xpath", ConditionType.EQUALS, baseXpath)

WebUI.verifyElementPresent(row, 5)

TestObject idObj = createChild(baseXpath, "id", "id")
TestObject nameObj = createChild(baseXpath, "name", "name")
TestObject genderObj = createChild(baseXpath, "gender", "gender")
TestObject birthObj = createChild(baseXpath, "birthday", "birthday")
TestObject phoneObj = createChild(baseXpath, "phone-number", "phone-number")
TestObject mailObj = createChild(baseXpath, "email", "email")
TestObject statusObj = createChild(baseXpath, "status", "status")

// Lấy text
String idText = WebUI.getText(idObj).trim()
String nameText = WebUI.getText(nameObj).replace("Tên:", "").trim()
String genderText = WebUI.getText(genderObj).trim()
String birthText = WebUI.getText(birthObj).trim()
String phoneText = WebUI.getText(phoneObj).trim()
String mailText = WebUI.getText(mailObj).trim()
String statusText = WebUI.getText(statusObj).trim()

assert idText == "ID: " + newID
assert nameText == newName
assert genderText == "Nữ"
assert birthText == newBirthday
assert phoneText == newPhone
assert mailText == newEmail
assert statusText.contains(newStatus)

KeywordUtil.markPassed("PASSED: hiển thị đúng toàn bộ thông tin!")

WebUI.closeBrowser()

```
## AccEmployee_Insert_Invalid_Email_07: Thông báo lỗi khi thêm thành viên mà trường "Email" sai định dạng
- Mô tả: Trong form thêm mới nhân viên, nhập đầy đủ thông tin nhưng đối với trường "Email" thì lần lượt nhập các trường hợp sai định dạnh như: testgmail.com; test@; @gmail.com; test@.com. Sau đó nhấn button "Thêm mới nhân viên". Passed nếu hệ thống trả về thông báo lỗi "Vui lòng nhập lại thông tin Email theo dạng @ .com" và không cho phéo thêm nhân viên. Failed nếu hệ thống thông báo tin nhắn khác hoặc không thông báo và thêm mới nhân viên thành công

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

WebUI.callTestCase(findTestCase('Login_success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(findTestObject('Page_HyperS/button_quan_ly_tai_khoan'))

WebUI.verifyElementText(findTestObject('TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien'), 'Thông tin nhân viên')

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien'))

WebUI.verifyElementText(findTestObject('TC_insert_employee/Page_HyperS/the_thong_tin_nhan_vien'), 'THÔNG TIN NHÂN VIÊN')

List<String> invalidEmails = [
    "testgmail.com",
    "test@",
    "@gmail.com",
    "test@.com"
]

String expectedError = "Vui lòng nhập lại thông tin Email theo dạng @ .com"

for (String email : invalidEmails) {

    WebUI.comment("===== TEST EMAIL SAI: " + email + " =====")

    WebUI.clearText(findTestObject('TC_insert_employee/Page_HyperS/input_id'))
    WebUI.clearText(findTestObject('TC_insert_employee/Page_HyperS/input_ten'))
    WebUI.clearText(findTestObject('TC_insert_employee/Page_HyperS/input_mail'))

    WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_id'), 'USER100')
    WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_ten'), 'Nguyễn Hihi')
    WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_mail'), email)

    WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_them_moi_nhan_vien'))

    WebUI.delay(0.5)

    boolean hasError = WebUI.verifyTextPresent(expectedError, false, FailureHandling.OPTIONAL)

    if (hasError) {
        WebUI.comment("PASSED: Lỗi hiển thị đúng với email: " + email)
    } else {
        WebUI.takeScreenshot()
        KeywordUtil.markFailed("FAILED: KHÔNG xuất hiện lỗi với email sai: " + email)
    }

    WebUI.delay(0.8)
}

WebUI.closeBrowser()

```
## AccEmployee_Insert_Invalid_Phone_08: Thông báo lỗi khi thêm thành viên mà trường "Số điện thoại" sai định dạng
- Mô tả: Trong form thêm mới nhân viên, nhập đầy đủ thông tin nhưng đối với trường "Số điện thoại" thì lần lượt nhập các trường hợp sai định dạnh như: Nhập chữ: abcxyz; Nhập ít hơn 10 số: 12345; Nhập nhiều hơn 11 số: 012345678912; Nhập ký tự đặc biệt: @@@@. Sau đó nhấn button "Thêm mới nhân viên". Passed nếu hệ thống trả về thông báo lỗi "Vui lòng nhập lại thông tin số điện thoại" và không cho phéo thêm nhân viên. Failed nếu hệ thống thông báo tin nhắn khác hoặc không thông báo và thêm mới nhân viên thành công

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

WebUI.callTestCase(findTestCase('Login_success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(1)

WebUI.click(findTestObject('Page_HyperS/button_quan_ly_tai_khoan'))
WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien'))

WebUI.verifyElementText(
        findTestObject('TC_insert_employee/Page_HyperS/the_thong_tin_nhan_vien'),
        'THÔNG TIN NHÂN VIÊN'
)



List<String> invalidPhones = [
        "abcxyz",       // nhập chữ
        "12345",        // < 10 số
        "012345678912", // > 11 số
        "@@@@",         // ký tự đặc biệt
]

String expectedError = "Vui lòng nhập lại thông tin số điện thoại"

for (String phone : invalidPhones) {

    WebUI.comment("===== TEST PHONE SAI: " + phone + " =====")

    WebUI.clearText(findTestObject('TC_insert_employee/Page_HyperS/input_id'))
    WebUI.clearText(findTestObject('TC_insert_employee/Page_HyperS/input_ten'))
    WebUI.clearText(findTestObject('TC_insert_employee/Page_HyperS/input_mail'))
	WebUI.clearText(findTestObject('TC_insert_employee/Page_HyperS/input_sdt'))


    WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_id'), 'USER100')
    WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_ten'), 'Nguyễn Hihi')
	WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_mail'), 'Hihi@gmail.com')
	
    WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_sdt'), phone)

    WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_them_moi_nhan_vien'))

    WebUI.delay(0.5)

    boolean hasError = WebUI.verifyTextPresent(expectedError, false, FailureHandling.OPTIONAL)

    if (hasError) {
        WebUI.comment("PASSED: Lỗi hiển thị đúng với số điện thoại: " + phone)
    } else {
        WebUI.takeScreenshot()
        KeywordUtil.markFailed("FAILED: Không xuất hiện lỗi cho phone sai: " + phone)
    }

    WebUI.delay(0.8)
}

WebUI.closeBrowser()

```
## AccEmployee_Insert_Invalid_Date_09: Thông báo lỗi khi thêm nhân viên mà ngày sinh được chọn ra dưới 15 tuổi tính từ năm 2025
- Mô tả: Trong form thêm mới nhân viên, nhập đầy đủ thông tin nhưng đối với trường "Ngày sinh" thì chọn ngày gần đây "26/11/2025". Sau đó nhấn button "Thêm mới nhân viên". Tính từ thời điểm 2025 thì ngày sinh của khách hàng mới tạo chưa được 1 tuối, tức nhỏ hơn 15 tuổi nên không thể tạo tài khoản. Passed nếu hệ thống trả về thông báo lỗi "Tuổi không hợp lệ" và không cho phéo thêm nhân viên. Failed nếu hệ thống thông báo tin nhắn khác hoặc thêm mới nhân viên thành công


```groovy
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil

import java.time.LocalDate
import java.time.format.DateTimeFormatter


// LOGIN
WebUI.callTestCase(findTestCase('Login_success'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(1)


// VÀO TRANG THÔNG TIN NHÂN VIÊN
WebUI.click(findTestObject('Page_HyperS/button_quan_ly_tai_khoan'))
WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien'))

WebUI.verifyElementText(
        findTestObject('TC_insert_employee/Page_HyperS/the_thong_tin_nhan_vien'),
        'THÔNG TIN NHÂN VIÊN'
)


// NHẬP THÔNG TIN CƠ BẢN
WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_id'), "USER111")
WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_ten'), "Nguyễn Hehe")
WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_mail'), "Hehe111@gmail.com")
WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_sdt'), "0345093011")


// CHỌN NGÀY SINH
WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/input_ngay_sinh'))
WebUI.delay(0.5)

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/chon_ngay_sinh'))  // chọn ngày 26
WebUI.clickOffset(findTestObject('TC_insert_employee/Page_HyperS/the_thong_tin_nhan_vien'), -200, 0)
WebUI.delay(0.5)


// LẤY GIÁ TRỊ NGÀY SINH THỰC TẾ (input k-input-inner)
TestObject dobInput = new TestObject("dob_value")
dobInput.addProperty("xpath", ConditionType.EQUALS,
        "//kendo-dateinput//input[contains(@class,'k-input-inner')]"
)

String dobString = WebUI.getAttribute(dobInput, "value")

if (dobString == null || dobString.trim() == "") {
    KeywordUtil.markFailedAndStop("Không lấy được ngày sinh từ ô input thực (k-input-inner).")
}

println("Ngày sinh lấy được: " + dobString)


// TÍNH TUỔI
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
LocalDate dob = LocalDate.parse(dobString.trim(), formatter)
LocalDate today = LocalDate.now()

int age = today.getYear() - dob.getYear()
if (today.getDayOfYear() < dob.getDayOfYear()) {
    age--
}

println("Tuổi hiện tại: " + age)


// NHẬP CÁC TRƯỜNG CÒN LẠI
WebUI.scrollToElement(findTestObject('TC_insert_employee/Page_HyperS/input_gioi_tinh'), 1)
WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/input_gioi_tinh'))
WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/gioi_tinh_nu'))

WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_ten_duong'), "Số 10")
WebUI.setText(findTestObject('TC_insert_employee/Page_HyperS/input_dia_chi_cu_the'), "Nông Lâm")

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/input_chuc_danh'))
WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/billManager'))

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_them_moi_nhan_vien'))
WebUI.delay(0.5)


// KIỂM TRA KẾT QUẢ
if (age < 15) {

    boolean hasError = WebUI.verifyTextPresent("Tuổi không hợp lệ", false, FailureHandling.OPTIONAL)

    if (hasError) {
        KeywordUtil.markPassed("PASSED — Dưới 15 tuổi → hệ thống báo lỗi đúng.")
    } else {
        KeywordUtil.markFailed("FAILED — Dưới 15 tuổi nhưng hệ thống lại CHO PHÉP TẠO!")
    }

} else {

    boolean created = WebUI.verifyTextPresent("Thêm mới thành công", false, FailureHandling.OPTIONAL)

    if (created) {
        KeywordUtil.markPassed("PASSED — Tuổi hợp lệ → thêm mới thành công.")
    } else {
        KeywordUtil.markFailed(" FAILED — Tuổi hợp lệ nhưng KHÔNG tạo được tài khoản.")
    }
}

WebUI.closeBrowser()

```

## AccEmployee_Update_Name_Success_10: Sửa "Tên" nhân viên thành công
- Mô tả: Chọn vào tài khoản bất kỳ, khi form thông tin nhân viên hiện lên thông tin của tài khoản đó thì click vào trường "Tên" và sửa lại thành tên khác, sau đó nhấn button "Cập nhật". Passed nếu sau cập nhật tài khoản dưới danh sách có tên thay đổi thành tên mới vừa sửa. Failed nếu sau cập nhật tên vẫn còn như cũ

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys

String oldName = "Trương Thiện Tin"
String newName = "Trương Thiện"

String oldDisplay = "Tên: " + oldName
String newDisplay = "Tên: " + newName

WebUI.callTestCase(findTestCase("Login_success"), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject("Page_HyperS/button_quan_ly_tai_khoan"))
WebUI.click(findTestObject("TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien"))
WebUI.verifyElementText(findTestObject("TC_insert_employee/Page_HyperS/the_thong_tin_nhan_vien"), "THÔNG TIN NHÂN VIÊN")

String rowXpath = "//tr[.//div[@class='name'][normalize-space()='${oldDisplay}']]"
TestObject row = new TestObject("row_old")
row.addProperty("xpath", ConditionType.EQUALS, rowXpath)

if (!WebUI.verifyElementPresent(row, 10, FailureHandling.OPTIONAL)) {
    KeywordUtil.markFailedAndStop("❌ Không tìm thấy nhân viên: ${oldDisplay}")
}

WebUI.click(row)
WebUI.delay(1)

String current = WebUI.getAttribute(findTestObject("TC_insert_employee/Page_HyperS/input_ten"), "value")
if (current.trim() != oldName) {
    KeywordUtil.markFailedAndStop("Form không load đúng tên. Hiện tại: ${current}")
}

WebUI.clearText(findTestObject("TC_insert_employee/Page_HyperS/input_ten"))
WebUI.setText(findTestObject("TC_insert_employee/Page_HyperS/input_ten"), newName)
WebUI.click(findTestObject("TC_update_employee/Page_HyperS/button_cap_nhat"))
WebUI.delay(0.4)

if (!WebUI.verifyTextPresent("Cập nhật thành công", false, FailureHandling.OPTIONAL)) {
    KeywordUtil.markFailedAndStop(" Không có thông báo cập nhật thành công")
}

String newRowXpath = "//tr[.//div[@class='name'][normalize-space()='${newDisplay}']]"
TestObject newRow = new TestObject("row_new")
newRow.addProperty("xpath", ConditionType.EQUALS, newRowXpath)

if (!WebUI.verifyElementPresent(newRow, 10, FailureHandling.OPTIONAL)) {
    KeywordUtil.markFailedAndStop(" Không tìm thấy tên mới: ${newDisplay}")
}



KeywordUtil.markPassed(" Đã đổi từ '${oldDisplay}' → '${newDisplay}' thành công!")
```

## AccEmployee_Cancel_Update_11: Ngưng quá quá trình sửa tên nhân viên
- Mô tả: Chọn vào tài khoản bất kỳ, khi form thông tin nhân viên hiện lên thông tin của tài khoản đó thì click vào trường "Tên" và sửa lại thành tên khác, sau đó nhấn button "Ngưng cập nhật". Passed nếu sau khi ngưng cập nhật tên vẫn còn như cũ nếu sau. Failed nếu sau khi ngưng cập nhật tài khoản dưới danh sách có tên thay đổi thành tên mới vừa sửa.

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys

// DATA
String oldName = "Phú"
String newName = "Hello Phú"

String oldDisplay = "Tên: " + oldName
String newDisplay = "Tên: " + newName

// --- LOGIN ---
WebUI.callTestCase(findTestCase("Login_success"), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject("Page_HyperS/button_quan_ly_tai_khoan"))
WebUI.click(findTestObject("TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien"))
WebUI.verifyElementText(findTestObject("TC_insert_employee/Page_HyperS/the_thong_tin_nhan_vien"), "THÔNG TIN NHÂN VIÊN")

String rowXpath = "//tr[.//div[@class='name'][normalize-space()='${oldDisplay}']]"
TestObject row = new TestObject("row_old")
row.addProperty("xpath", ConditionType.EQUALS, rowXpath)

if (!WebUI.verifyElementPresent(row, 10, FailureHandling.OPTIONAL)) {
    KeywordUtil.markFailedAndStop("Không tìm thấy nhân viên: ${oldDisplay}")
}

WebUI.click(row)
WebUI.delay(1)

String current = WebUI.getAttribute(findTestObject("TC_insert_employee/Page_HyperS/input_ten"), "value")
if (current.trim() != oldName) {
    KeywordUtil.markFailedAndStop("Form không load đúng tên. Hiện tại: ${current}")
}

WebUI.clearText(findTestObject("TC_insert_employee/Page_HyperS/input_ten"))
WebUI.setText(findTestObject("TC_insert_employee/Page_HyperS/input_ten"), newName)

WebUI.click(findTestObject("TC_update_employee/Page_HyperS/button_ngung_cap_nhat"))

WebUI.waitForElementAttributeValue(
	findTestObject("TC_insert_employee/Page_HyperS/input_ten"),
	"value",
	oldName,
	5,
	FailureHandling.OPTIONAL
)


String reverted = WebUI.getAttribute(findTestObject("TC_insert_employee/Page_HyperS/input_ten"), "value")

if (reverted.trim() == newName) {
    KeywordUtil.markFailedAndStop("Ngưng cập nhật nhưng form vẫn giữ tên mới '${newName}'")
}

if (!WebUI.verifyElementPresent(row, 5, FailureHandling.OPTIONAL)) {
    KeywordUtil.markFailedAndStop("Bảng không còn tên cũ sau khi ngưng → API đang update sai!")
}


KeywordUtil.markPassed("PASSED — Ngưng cập nhật hoạt động đúng. Không có thay đổi dữ liệu!")
```

## AccEmployee_Status_Disable_12: Cập nhật đúng trạng thái của tài khoản sau khi bị vô hiệu hóa
- Mô tả: Chọn vào dấu "..." ở một tài khoản còn ở trạng thái hoạt động, sau đó chọn button "Vô hiệu hóa". Passed nếu sau đó tài khoản đó chuyển trạng thái từ "Hoạt động" sang thành "Vô hiệu hóa". Failed nếu cột trạng thái vẫn không đổi hoặc hiện trại thái khác

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys

String targetName = 'Phú'

String targetDisplay = 'Tên: ' + targetName

WebUI.callTestCase(findTestCase('Login_success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_HyperS/button_quan_ly_tai_khoan'))

WebUI.click(findTestObject('TC_insert_employee/Page_HyperS/button_thong_tin_nhan_vien'))

WebUI.verifyElementText(findTestObject('TC_insert_employee/Page_HyperS/the_thong_tin_nhan_vien'), 'THÔNG TIN NHÂN VIÊN')

String rowXpath = "//tr[.//div[@class='name'][normalize-space()='$targetDisplay']]"

TestObject row = new TestObject('row_target')

row.addProperty('xpath', ConditionType.EQUALS, rowXpath)

if (!(WebUI.verifyElementPresent(row, 10, FailureHandling.OPTIONAL))) {
    KeywordUtil.markFailedAndStop("❌ Không tìm thấy nhân viên '$targetDisplay' để vô hiệu hóa!")
}

WebUI.delay(1)

String menuXpath = rowXpath + "//i[contains(@class,'fa-ellipsis')]/ancestor::div[contains(@class,'component-button')]"

TestObject menuBtn = new TestObject("menu_status_dynamic")
menuBtn.addProperty("xpath", ConditionType.EQUALS, menuXpath)

if (!WebUI.verifyElementPresent(menuBtn, 10, FailureHandling.OPTIONAL)) {
    KeywordUtil.markFailedAndStop(" Không tìm thấy menu ⋯ trong dòng '$targetDisplay'")
}

WebUI.click(menuBtn)
WebUI.delay(0.5)

WebUI.verifyElementText(findTestObject('TC_Status/Page_HyperS/button_vo_hieu_hoa'), 'Vô hiệu hóa')

WebUI.click(findTestObject('TC_Status/Page_HyperS/button_vo_hieu_hoa'))

WebUI.delay(1.5)


String statusXpath = rowXpath + "//div[contains(@class,'status')]"

TestObject statusObj = new TestObject("status_dynamic")
statusObj.addProperty("xpath", ConditionType.EQUALS, statusXpath)

if (!WebUI.verifyElementPresent(statusObj, 5, FailureHandling.OPTIONAL)) {
    KeywordUtil.markFailedAndStop("Không tìm thấy trạng thái của dòng '$targetDisplay'")
}

String status = WebUI.getText(statusObj).trim()

println("→ Trạng thái nhận được: " + status)


if (status.equalsIgnoreCase('Vô hiệu') || status.equalsIgnoreCase('Vô hiệu hóa')) {
    KeywordUtil.markPassed('PASSED — Trạng thái đã chuyển sang **VÔ HIỆU** đúng yêu cầu!')
} else {
    KeywordUtil.markFailed(" FAILED — Trạng thái sai! Nhận được: '$status'")
}

```

## AccEmployee_Status_Activity_13: Cập nhật đúng trạng thái của tài khoản sau khi được kích hoạt   
- Mô tả: Sau khi hoàn thành test case AccEmployee_Status_Disable_12, tiếp tục quy trình chọn vào dấu "..." ở tài khoản đó, sau đó chọn button "Kích hoạt". Passed nếu sau đó tài khoản đó chuyển trạng thái từ "Vô hiệu hóa" sang thành "Hoạt động". Failed nếu cột trạng thái vẫn không đổi hoặc hiện trại thái khác

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys

String targetName = 'Phú'

String targetDisplay = 'Tên: ' + targetName

WebUI.callTestCase(findTestCase('AccEmployee_Status_Disable_12'), [:], FailureHandling.STOP_ON_FAILURE)

String rowXpath = "//tr[.//div[@class='name'][normalize-space()='$targetDisplay']]"

TestObject row = new TestObject('row_target')

row.addProperty('xpath', ConditionType.EQUALS, rowXpath)

if (!(WebUI.verifyElementPresent(row, 10, FailureHandling.OPTIONAL))) {
    KeywordUtil.markFailedAndStop("❌ Không tìm thấy nhân viên '$targetDisplay' để kích hoạt!")
}

WebUI.delay(1)

String menuXpath = rowXpath + '//i[contains(@class,\'fa-ellipsis\')]/ancestor::div[contains(@class,\'component-button\')]'

TestObject menuBtn = new TestObject('menu_status_dynamic')

menuBtn.addProperty('xpath', ConditionType.EQUALS, menuXpath)

if (!(WebUI.verifyElementPresent(menuBtn, 10, FailureHandling.OPTIONAL))) {
    KeywordUtil.markFailedAndStop(" Không tìm thấy menu ⋯ trong dòng '$targetDisplay'")
}

WebUI.click(menuBtn)

WebUI.delay(0.5)

WebUI.verifyElementText(findTestObject('TC_Status_Employee/Button_kich_hoat'), 'Kích hoạt')

WebUI.click(findTestObject('TC_Status_Employee/Button_kich_hoat'))

WebUI.delay(1.5)

String statusXpath = rowXpath + '//div[contains(@class,\'status\')]'

TestObject statusObj = new TestObject('status_dynamic')

statusObj.addProperty('xpath', ConditionType.EQUALS, statusXpath)

if (!(WebUI.verifyElementPresent(statusObj, 5, FailureHandling.OPTIONAL))) {
    KeywordUtil.markFailedAndStop("Không tìm thấy trạng thái của dòng '$targetDisplay'")
}

String status = WebUI.getText(statusObj).trim()

println('→ Trạng thái nhận được: ' + status)

if (status.equalsIgnoreCase('Hoạt động') || status.equalsIgnoreCase('Đang hoạt động')) {
    KeywordUtil.markPassed('PASSED — Trạng thái đã chuyển sang HOẠT ĐỘNG đúng yêu cầu!')
} else {
    KeywordUtil.markFailed(" FAILED — Trạng thái sai! Nhận được: '$status'")
}

```