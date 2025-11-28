# Assignment 7 - Automation Script

## 👤 Thông tin sinh viên
- Mã sinh viên: 22130068
- Họ tên: Lê Phước Hải 

---

## 🎯 Mô tả bài làm
- Bài assignment yêu cầu xây dựng script automation cho các chức năng đã chọn.
- Công cụ sử dụng: **Katalon Studio**
- Ngôn ngữ: **Groovy (Selenium WebDriver)**  
- Website kiểm thử: HyperS Admin  
- Loại bài nộp: File `.md` + video quay demo chạy script

---
## 📌 Danh sách Testcase đã thực hiện

### ✅ Testcase Banner_01: Kiểm tra chức năng lọc banner theo trạng thái “Ngưng hoạt động”.
**Mô tả:**  
Kiểm tra chức năng lọc banner theo trạng thái “Ngưng hoạt động”.

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

// 1. Login
WebUI.callTestCase(findTestCase('Login_success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

// 2. Truy cập trang "Quản lý Banner"
WebUI.click(findTestObject('Page_HyperS/div_quan_ly_banner'))

// 3. Verify hiển thị trang danh sách banner
WebUI.verifyElementText(findTestObject('Page_HyperS/div_danh_sach_banner'), 'DANH SÁCH BANNER')

// 4. Bỏ chọn filter "Đang được sử dụng"
WebUI.click(findTestObject('Page_HyperS/checkbox_dang_duoc_su_dung'))

// 5. Chọn filter "Ngưng hoạt động"
WebUI.click(findTestObject('Page_HyperS/checkbox_ngung_su_dung'), FailureHandling.STOP_ON_FAILURE)

WebUI.delay(1)

// 6. Lấy toàn bộ giá trị cột Trạng thái trong bảng
TestObject statusColTO = findTestObject('Page_HyperS/trang_thai')

// timeout 10s để chờ table load xong
List<WebElement> statusElements = WebUI.findWebElements(statusColTO, 10)

// (Optional) log số dòng tìm được
WebUI.comment('Số dòng trạng thái tìm được: ' + statusElements.size())

// 7. Kiểm tra tất cả các dòng đều có trạng thái "Ngưng hoạt động"
int rowIndex = 1

for (WebElement el : statusElements) {
    String statusText = el.getText().trim()

    WebUI.comment((('Dòng ' + rowIndex) + ' - Trạng thái: ') + statusText)

    // Nếu có bất kỳ trạng thái nào khác "Ngưng hoạt động" thì testcase sẽ Fail
    WebUI.verifyMatch(statusText, 'Ngưng hoạt động', false)

    rowIndex++
}

WebUI.closeBrowser()
```
### ✅ Testcase Banner_02: Kiểm tra lọc theo loại banner (Hình ảnh/Video).
**Mô tả:**  
Kiểm tra lọc theo loại banner (Hình ảnh/Video).

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

// 1. Login
WebUI.callTestCase(findTestCase('Login_success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

// 2. Truy cập trang "Quản lý Banner"
WebUI.click(findTestObject('Page_HyperS/div_quan_ly_banner'))

// 3. Verify hiển thị trang danh sách banner
WebUI.verifyElementText(findTestObject('Page_HyperS/div_danh_sach_banner'), 'DANH SÁCH BANNER')

// 4. Bỏ chọn filter "Đang được sử dụng"
WebUI.click(findTestObject('Page_HyperS/checkbox_dang_duoc_su_dung'))

WebUI.delay(1)

// 5. Chọn loại banner = "Đoạn video"
WebUI.click(findTestObject('Page_HyperS/span_chon_loai_banner'))
WebUI.click(findTestObject('Page_HyperS/span_doan_video'))

// Chờ grid load lại
WebUI.delay(1)

// 6. Verify tất cả dòng trong bảng đều có loại = "Đoạn video"
TestObject colLoaiBanner = findTestObject('Page_HyperS/loai_banner')

// Lấy toàn bộ WebElement của cột loại banner
List<WebElement> typeElements = WebUI.findWebElements(colLoaiBanner, 10)

WebUI.comment('Số dòng tìm được sau khi lọc: ' + typeElements.size())

int rowIndex = 1
for (WebElement el : typeElements) {
    String typeText = el.getText().trim()
    WebUI.comment("Dòng " + rowIndex + " - Loại banner: " + typeText)
    
    // Nếu có dòng nào không phải "Đoạn video" -> test fail
    WebUI.verifyMatch(typeText, 'Đoạn video', false)
    
    rowIndex++
}

WebUI.closeBrowser()
```
### ✅ Testcase Banner_03: Kiểm tra tìm kiếm chính xác theo tiêu đề banner.
**Mô tả:**  
Kiểm tra tìm kiếm chính xác theo tiêu đề banner.

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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// 1. Login
WebUI.callTestCase(findTestCase('Login_Success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

// 2. Truy cập trang "Quản lý Banner"
WebUI.click(findTestObject('Page_HyperS/div_quan_ly_banner'))

// 3. Verify hiển thị trang danh sách banner
WebUI.verifyElementText(findTestObject('Page_HyperS/div_danh_sach_banner'), 'DANH SÁCH BANNER')

// 4. Bỏ chọn filter "Đang được sử dụng"
WebUI.click(findTestObject('Page_HyperS/checkbox_dang_duoc_su_dung'))

WebUI.delay(1)

// 5. Nhập từ khóa "new" vào ô tìm kiếm tiêu đề và nhấn Enter
TestObject inputSearch = findTestObject('Page_HyperS/tim_kiem_theo_tieu_de')

WebUI.click(inputSearch)
WebUI.setText(inputSearch, 'new')
WebUI.sendKeys(inputSearch, Keys.chord(Keys.ENTER))

// Chờ grid load lại
WebUI.delay(1)

// 6. Verify tất cả dòng trong bảng đều có tiêu đề chứa "new"

// Tạo TestObject động cho cột tiêu đề (column-2)
TestObject colTieuDe = new TestObject('dynamic_colTieuDe')
colTieuDe.addProperty(
        'xpath',
        ConditionType.EQUALS,
        "//table[contains(@class,'k-grid-table')]//tbody//tr/td[2]//div[contains(@class,'column-2')]"
)

// Lấy toàn bộ WebElement của cột tiêu đề
List<WebElement> titleElements = WebUI.findWebElements(colTieuDe, 10)

WebUI.comment('Số dòng tìm được sau khi search: ' + titleElements.size())

// Nếu không có dòng nào thì fail (tùy rule của bạn, có thể cho pass nếu cho phép kết quả rỗng)
WebUI.verifyGreaterThan(titleElements.size(), 0)

// 7. Kiểm tra từng dòng: tiêu đề phải chứa "new" (không phân biệt hoa/thường)
int rowIndex = 1
for (WebElement el : titleElements) {
    String titleText = el.getText().trim()
    WebUI.comment("Dòng " + rowIndex + " - Tiêu đề: " + titleText)

    // so sánh không phân biệt hoa/thường
    String lowerTitle = titleText.toLowerCase()
    WebUI.verifyMatch(lowerTitle, '.*new.*', true)   // regex: phải chứa "new"

    rowIndex++
}

WebUI.closeBrowser()
```
### ✅ Testcase Banner_04: Kiểm tra tìm kiếm kết hợp cùng bộ lọc loại banner.
**Mô tả:**  
Kiểm tra tìm kiếm kết hợp cùng bộ lọc loại banner.

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

// 1. Login
WebUI.callTestCase(findTestCase('Login_Success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

// 2. Vào trang Quản lý Banner
WebUI.click(findTestObject('Page_HyperS/div_quan_ly_banner'))

WebUI.verifyElementText(findTestObject('Page_HyperS/div_danh_sach_banner'), 'DANH SÁCH BANNER')

// 3. Nhập từ khóa "new"
TestObject inputSearch = findTestObject('Page_HyperS/tim_kiem_theo_tieu_de')

WebUI.click(inputSearch)

WebUI.setText(inputSearch, 'new')

WebUI.sendKeys(inputSearch, Keys.chord(Keys.ENTER))

WebUI.delay(1)

// 4. Chọn loại banner = Hình ảnh
WebUI.click(findTestObject('Page_HyperS/span_chon_loai_banner'))

WebUI.delay(1)

WebUI.click(findTestObject('Page_HyperS/span_hinh_anh'))

WebUI.delay(1)

// 5. Verify kết quả: tiêu đề chứa "new" + loại = "Hình ảnh"
// --- Cột tiêu đề ---
TestObject colTitle = new TestObject('dynamic_title')

colTitle.addProperty('xpath', ConditionType.EQUALS, '//table[contains(@class,\'k-grid-table\')]//tbody//tr/td[2]//div[contains(@class,\'column-2\')]')

// --- Cột loại banner ---
TestObject colLoai = new TestObject('dynamic_type')

colLoai.addProperty('xpath', ConditionType.EQUALS, '//table[contains(@class,\'k-grid-table\')]//tbody//tr/td[1]//div[contains(@class,\'banner-type\')]')

List<WebElement> titleList = WebUI.findWebElements(colTitle, 10)

List<WebElement> typeList = WebUI.findWebElements(colLoai, 10)

WebUI.comment('Số dòng tìm được: ' + titleList.size())

if (titleList.size() == 0) {
    // Không có kết quả -> verify hiển thị "No records available."
    TestObject noRecordMsg = new TestObject('dynamic_noRecordMsg')

    noRecordMsg.addProperty('xpath', ConditionType.EQUALS, '//tr[contains(@class,\'k-grid-norecords\')]/td[contains(normalize-space(),\'No records available.\')]')

    WebUI.verifyElementPresent(noRecordMsg, 10)

    WebUI.verifyElementText(noRecordMsg, 'No records available.')

    WebUI.comment('Không có bản ghi nào phù hợp. Hệ thống hiển thị "No records available." đúng như mong đợi.') // Có kết quả -> verify từng dòng
    // Check title có chứa "new"
    // Check loại = Hình ảnh
} else {
    for (int i = 0; i < titleList.size(); i++) {
        String titleText = (titleList[i]).getText().trim().toLowerCase()

        String typeText = (typeList[i]).getText().trim()

        WebUI.comment((((('Dòng ' + (i + 1)) + ' | Tiêu đề: ') + titleText) + ' | Loại: ') + typeText)

        WebUI.verifyMatch(titleText, '.*new.*', true)

        WebUI.verifyMatch(typeText, 'Hình ảnh', false)
    }
    
    WebUI.comment('Đã xác nhận tất cả dòng đều có tiêu đề chứa \'new\' và loại \'Hình ảnh\'')
}
```
### ✅ Testcase Banner_05: Kiểm tra nút “Xóa bộ lọc”
**Mô tả:**  
Kiểm tra nút “Xóa bộ lọc”

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// ================== TIỀN ĐIỀU KIỆN ==================
// Phụ thuộc vào Banner_04 -> chạy trước để đang ở trạng thái đã filter
WebUI.callTestCase(findTestCase('Banner_04'), [:], FailureHandling.STOP_ON_FAILURE)

// ================== BẮT ĐẦU TEST "XÓA BỘ LỌC" ==================
// 1. Click nút "Xóa bộ lọc"
// Có thể tạo Test Object riêng trong Repository, nhưng ở đây mình tạo dynamic cho nhanh
TestObject btnClearFilter = new TestObject('btn_clear_filter')

btnClearFilter.addProperty('xpath', ConditionType.EQUALS, '//div[@class=\'label-delete-filter\' and normalize-space()=\'Xóa bộ lọc\']')

WebUI.click(findTestObject('Page_HyperS/btn_xoa_bo_loc'))

// 2. Chờ hệ thống load lại
WebUI.delay(1)

// ================== VERIFY KẾT QUẢ ==================
// 2.1 Trường tìm kiếm quay về mặc định (trống)
TestObject inputSearch = findTestObject('Page_HyperS/tim_kiem_theo_tieu_de')

String searchValue = WebUI.getAttribute(inputSearch, 'value')

WebUI.comment(('Giá trị ô search sau khi Xóa bộ lọc: "' + searchValue) + '"')

WebUI.verifyMatch(searchValue, '', false)

// 2.2 Dropdown "Chọn loại banner" quay về default
TestObject ddLoaiBanner = findTestObject('Page_HyperS/span_chon_loai_banner')

// span_chon_loai_banner phải trỏ tới span có text dạng "-- Chọn loại banner --"
WebUI.verifyElementText(findTestObject('Page_HyperS/span_chon_loai_banner'), '-- Chọn loại banner --')

// 2.3 Checkbox "Đang được sử dụng" đang được bật (filter trạng thái đang được sử dụng)
TestObject cbDangDuocSuDung = findTestObject('Page_HyperS/checkbox_dang_duoc_su_dung')

WebUI.verifyElementChecked(findTestObject('Page_HyperS/checkbox_dang_duoc_su_dung'), 5)

// 2.4 Danh sách chỉ hiển thị trạng thái "Đang được sử dụng"
// Tạo dynamic TestObject cho cột trạng thái (column-6)
TestObject colTrangThai = new TestObject('dynamic_colTrangThai')

colTrangThai.addProperty('xpath', ConditionType.EQUALS, '//table[contains(@class,\'k-grid-table\')]//tbody//tr/td[6]//div[contains(@class,\'column-6\')]')

// Lấy tất cả cell trạng thái
List<WebElement> statusList = WebUI.findWebElements(findTestObject('Page_HyperS/trang_thai'), 10)

WebUI.comment('Số dòng trạng thái sau khi Xóa bộ lọc: ' + statusList.size())

// Có ít nhất 1 bản ghi (tùy business, nếu hệ thống có thể không có bản ghi thì bỏ dòng này)
if (statusList.size() == 0) {
    WebUI.comment('Không có bản ghi nào sau khi Xóa bộ lọc - cần kiểm tra lại dữ liệu test.') // Tất cả đều phải là "Đang được sử dụng"
} else {
    int rowIdx = 1

    for (WebElement el : statusList) {
        String statusText = el.getText().trim()

        WebUI.comment((('Dòng ' + rowIdx) + ' - Trạng thái: ') + statusText)

        WebUI.verifyMatch(statusText, 'Đang được sử dụng', false)

        rowIdx++
    }
}

WebUI.comment('Đã xác nhận: Xóa bộ lọc -> search trống, loại banner về mặc định, danh sách lọc theo "Đang được sử dụng"')

// Kết thúc (tùy flow framework của bạn có closeBrowser ở teardown hay không)
WebUI.closeBrowser()
```
### ✅ Testcase Banner_06: Kiểm tra quy trình thêm banner mới với dữ liệu hợp lệ.
**Mô tả:**  
Kiểm tra quy trình thêm banner mới với dữ liệu hợp lệ.

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// ================== 1. DATA GIẢ ==================
String page = 'Trang chủ Ecom' // giá trị phải đúng với option trong dropdown

String location = 'Vị trí 1'

String title = 'Auto Banner NEW ' + System.currentTimeMillis()

String type = 'Đoạn video' // ví dụ: "Hình ảnh" / "Đoạn video" tuỳ hệ thống

WebUI.comment('=== DATA TEST ===')

WebUI.comment('Page     = ' + page)

WebUI.comment('Location = ' + location)

WebUI.comment('Title    = ' + title)

WebUI.comment('Type     = ' + type)

// ================== 2. LOGIN ==================
WebUI.callTestCase(findTestCase('Login_Success'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

// ================== 3. VÀO TRANG QUẢN LÝ BANNER ==================
WebUI.click(findTestObject('Page_HyperS/div_quan_ly_banner'))

WebUI.verifyElementText(findTestObject('Page_HyperS/div_danh_sach_banner'), 'DANH SÁCH BANNER')

// ================== 4. ĐẾM SỐ DÒNG TRƯỚC KHI THÊM ==================
TestObject rowObject = new TestObject('dynamic_rows')

rowObject.addProperty('xpath', ConditionType.EQUALS, '//table[contains(@class,\'k-grid-table\')]//tbody//tr[not(contains(@class,\'k-grid-norecords\'))]')

List<WebElement> rowsBefore = WebUI.findWebElements(rowObject, 10)

int countBefore = rowsBefore.size()

WebUI.comment('Số dòng trước khi thêm: ' + countBefore)

// ================== 5. NHẤN NÚT "THÊM MỚI" (MỞ DRAWER) ==================
WebUI.click(findTestObject('Page_HyperS/Banner_06/btn_them_moi'))

WebUI.delay(1)

// --- 6.1 Trang hiển thị (dropdown) ---
WebUI.click(findTestObject('Page_HyperS/Banner_06/trang_hien_thi'))

TestObject optPage = new TestObject('optPage')
optPage.addProperty('xpath', ConditionType.EQUALS,
        "//li[contains(@class,'k-list-item')]//span[normalize-space(text())='" + page + "']")

WebUI.waitForElementVisible(optPage, 10)
WebUI.waitForElementClickable(optPage, 10)
WebUI.click(optPage)

// --- 6.2 Vị trí hiển thị ---
WebUI.click(findTestObject('Page_HyperS/Banner_06/vi_tri_hien_thi'))

TestObject optLocation = new TestObject('optLocation') 

optLocation.addProperty('xpath', ConditionType.EQUALS, "//li[contains(@class,'k-list-item')]//span[normalize-space(text())='" + location + "']") 

WebUI.waitForElementVisible(optLocation, 10)
WebUI.waitForElementClickable(optLocation, 10)
WebUI.click(optLocation)

// --- 6.3 Tiêu đề (text input) ---
WebUI.setText(findTestObject('Page_HyperS/Banner_06/tieu_de'), title)

// --- 6.4 Loại banner (dropdown) ---
WebUI.click(findTestObject('Page_HyperS/Banner_06/them_moi_loai_banner'))

TestObject optType = new TestObject('optType')
optType.addProperty('xpath', ConditionType.EQUALS,
        "//li[contains(@class,'k-list-item')]//span[normalize-space(text())='" + type + "']")

WebUI.waitForElementVisible(optType, 10)
WebUI.waitForElementClickable(optType, 10)
WebUI.click(optType)

// 6.4.1 Đường dẫn video
TestObject inputVideoUrl = new TestObject('inputVideoUrl')
inputVideoUrl.addProperty(
	'xpath',
	ConditionType.EQUALS,
	"//label[contains(normalize-space(),'Đường dẫn đoạn video')]/following::input[@class='k-input-inner' and @placeholder='Nhập đường dẫn của video...'][1]"
)

WebUI.setText(inputVideoUrl, "https://www.youtube.com/embed/UbpuZCB3x9k")


// ================== 7. NHẤN "THÊM MỚI" ==================
WebUI.click(findTestObject('Page_HyperS/Banner_06/btn_luu_them_moi'))

WebUI.delay(2)

// ================== 8. VERIFY KẾT QUẢ (CHECK THEO TÊN) ==================

TestObject newBanner = new TestObject('newBanner')
newBanner.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//table[contains(@class,'k-grid-table')]//tbody//tr" +
    "/td[2]//div[contains(@class,'column-2') and normalize-space(text())='${title}']"
)

// Chờ banner xuất hiện trong danh sách
WebUI.waitForElementVisible(newBanner, 10)

// Kiểm tra tồn tại
WebUI.verifyElementPresent(newBanner, 10)

WebUI.comment('✅ TIM THẤY BANNER VỪA TẠO: ' + title)

// ================== 9. KẾT THÚC ==================
WebUI.closeBrowser()
```
### ✅ Testcase Banner_07: Kiểm tra validation khi không nhập trường bắt buộc.
**Mô tả:**  
Kiểm tra validation khi không nhập trường bắt buộc.

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// ================== 1. DATA GIẢ ==================
String page     = 'Trang chủ Ecom'
String location = 'Vị trí 1'
String title    = 'Auto Banner IMG ' + System.currentTimeMillis()
String type     = 'Hình ảnh'   // <-- để trigger flow hình ảnh

WebUI.comment('=== DATA TEST ===')
WebUI.comment("Page     = ${page}")
WebUI.comment("Location = ${location}")
WebUI.comment("Title    = ${title}")
WebUI.comment("Type     = ${type}")

// ================== 2. LOGIN ==================
WebUI.callTestCase(findTestCase('Login_Success'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(2)

// ================== 3. VÀO TRANG QUẢN LÝ BANNER ==================
WebUI.click(findTestObject('Page_HyperS/div_quan_ly_banner'))
WebUI.verifyElementText(findTestObject('Page_HyperS/div_danh_sach_banner'), 'DANH SÁCH BANNER')

// ================== 4. NHẤN "THÊM MỚI" MỞ POPUP ==================
WebUI.click(findTestObject('Page_HyperS/Banner_06/btn_them_moi'))
WebUI.delay(1)

// ================== 5. ĐIỀN FORM NHƯ BÌNH THƯỜNG ==================

// --- 5.1 Trang hiển thị ---
WebUI.click(findTestObject('Page_HyperS/Banner_06/trang_hien_thi'))

TestObject optPage = new TestObject('optPage')
optPage.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//li[contains(@class,'k-list-item')]//*[normalize-space(text())='${page}']"
)
WebUI.waitForElementVisible(optPage, 10)
WebUI.waitForElementClickable(optPage, 10)
WebUI.click(optPage)

// --- 5.2 Vị trí hiển thị ---
WebUI.click(findTestObject('Page_HyperS/Banner_06/vi_tri_hien_thi'))

TestObject optLocation = new TestObject('optLocation')
optLocation.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//li[contains(@class,'k-list-item')]//*[normalize-space(text())='${location}']"
)
WebUI.waitForElementVisible(optLocation, 10)
WebUI.waitForElementClickable(optLocation, 10)
WebUI.click(optLocation)

// --- 5.3 Tiêu đề ---
WebUI.setText(findTestObject('Page_HyperS/Banner_06/tieu_de'), title)

// --- 5.4 Loại banner = Hình ảnh ---
WebUI.click(findTestObject('Page_HyperS/Banner_06/them_moi_loai_banner'))

TestObject optType = new TestObject('optType')
optType.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//li[contains(@class,'k-list-item')]//*[normalize-space(text())='${type}']"
)
WebUI.waitForElementVisible(optType, 10)
WebUI.waitForElementClickable(optType, 10)
WebUI.click(optType)

// **Lưu ý:** KHÔNG chọn hình ảnh -> để trống control upload/image position

// ================== 6. NHẤN "THÊM MỚI" ==================
WebUI.click(findTestObject('Page_HyperS/Banner_06/btn_luu_them_moi'))

// ================== 7. VERIFY THÔNG BÁO VALIDATION ==================
TestObject toastImageRequired = new TestObject('toastImageRequired')
toastImageRequired.addProperty(
	'xpath',
	ConditionType.EQUALS,
	"//div[contains(@class,'k-notification-content') and contains(normalize-space(),'Vui lòng chọn hình ảnh')]"
)

// Chờ toast xuất hiện (present + visible cho chắc)
WebUI.waitForElementPresent(toastImageRequired, 10)
WebUI.waitForElementVisible(toastImageRequired, 10)

// Lấy text thực tế
String actualMsg = WebUI.getText(toastImageRequired).trim()
WebUI.comment("Notification message: " + actualMsg)

// So sánh đúng thông điệp mong đợi (cho phép dư khoảng trắng 2 bên)
WebUI.verifyMatch(actualMsg, '.*Vui lòng chọn hình ảnh.*', true)

WebUI.comment('✅ VALIDATION KHÔNG CHỌN HÌNH ẢNH HIỂN THỊ ĐÚNG THÔNG ĐIỆP')

// ================== 8. KẾT THÚC ==================
WebUI.closeBrowser()
```
### ✅ Testcase Banner_08: Kiểm tra chỉnh sửa thông tin banner thành công.
**Mô tả:**  
Kiểm tra chỉnh sửa thông tin banner thành công.

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys as Keys

// ================== 1. DATA ==================
String oldTitle = 'trtrtrtr'
String newTitle = 'Banner 3'

WebUI.comment('=== EDIT BANNER TEST ===')
WebUI.comment("Old title = ${oldTitle}")
WebUI.comment("New title = ${newTitle}")

// ================== 2. LOGIN ==================
WebUI.callTestCase(findTestCase('Login_Success'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(2)

// ================== 3. VÀO TRANG QUẢN LÝ BANNER ==================
WebUI.click(findTestObject('Page_HyperS/div_quan_ly_banner'))
WebUI.verifyElementText(findTestObject('Page_HyperS/div_danh_sach_banner'), 'DANH SÁCH BANNER')

// ================== 4. CLICK NÚT "..." Ở DÒNG TIÊU ĐỀ = "Phổ biến" ==================
TestObject btnMore = new TestObject('btnMore')
btnMore.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//table[contains(@class,'k-grid-table')]//tbody//tr[" +
        ".//td[2]//div[contains(@class,'column-2') and normalize-space(text())='${oldTitle}']" +
    "]//div[contains(@class,'tool-box-container')]//i[contains(@class,'fa-ellipsis')]"
)

WebUI.waitForElementVisible(btnMore, 10)
WebUI.waitForElementClickable(btnMore, 10)
WebUI.click(btnMore)

// ================== 5. CHỌN "CHỈNH SỬA" ==================
TestObject btnEdit = new TestObject('btnEdit')
btnEdit.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//div[contains(@class,'list-action')]//span[normalize-space(text())='Chỉnh sửa']"
)

WebUI.waitForElementVisible(btnEdit, 10)
WebUI.click(btnEdit)

// ================== 6. SỬA TIÊU ĐỀ TRONG POPUP/ DRAWER ==================

// Input Tiêu đề (giống cấu trúc khi thêm mới)
TestObject inputTitle = new TestObject('inputTitle')
inputTitle.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//label[contains(normalize-space(),'Tiêu đề')]/following::input[@class='k-input-inner' and @placeholder='Nhập tiêu đề...'][1]"
)

WebUI.waitForElementVisible(inputTitle, 10)
WebUI.click(inputTitle)

// CÁCH 1: dùng clearText cho đơn giản
WebUI.clearText(inputTitle)

// nhập title mới
WebUI.setText(inputTitle, newTitle)

// ================== 7. NHẤN "CẬP NHẬT" ==================
TestObject btnUpdate = new TestObject('btnUpdate')
btnUpdate.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//span[normalize-space(text())='Cập nhật']"
)

WebUI.waitForElementClickable(btnUpdate, 10)
WebUI.click(btnUpdate)

WebUI.delay(2)

// ================== 8. VERIFY KẾT QUẢ ==================

TestObject newTitleObj = new TestObject('newTitleObj')
newTitleObj.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//table[contains(@class,'k-grid-table')]//tbody//tr/td[2]//div[contains(@class,'column-2') and normalize-space(text())='${newTitle}']"
)

WebUI.verifyElementPresent(newTitleObj, 10)

TestObject oldTitleObj = new TestObject('oldTitleObj')
oldTitleObj.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//table[contains(@class,'k-grid-table')]//tbody//tr/td[2]//div[contains(@class,'column-2') and normalize-space(text())='${oldTitle}']"
)

WebUI.verifyElementNotPresent(oldTitleObj, 3)

WebUI.comment("✅ Banner '${oldTitle}' đã được cập nhật thành '${newTitle}' thành công.")

// ================== 9. ĐÓNG BROWSER ==================
WebUI.closeBrowser()
```
### ✅ Testcase Brand_Type_01: Thêm mới Thương hiệu hợp lệ
**Mô tả:**  
Thêm mới Thương hiệu hợp lệ

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration

// =============== 1. DATA TEST ===============
String brandCode = 'BR01_' + System.currentTimeMillis()    // để unique
String brandName = 'Brand One Auto ' + System.currentTimeMillis()

WebUI.comment('=== DATA TEST BRAND ===')
WebUI.comment("Code = ${brandCode}")
WebUI.comment("Name = ${brandName}")

// Đường dẫn ảnh mẫu trong project -> chỉnh lại path nếu khác
String imagePath = RunConfiguration.getProjectDir() + '/Data Files/CNTT.png'


// =============== 2. LOGIN ===============
WebUI.callTestCase(findTestCase('Login_Success'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(2)

// =============== 3. ĐI TỚI MÀN QUẢN LÝ THƯƠNG HIỆU ===============
// Click menu "QUẢN LÝ SẢN PHẨM"
WebUI.click(findTestObject('Page_HyperS/Brand_Type/dropdown_quan_ly_san_pham'))

// Click sub menu "Thương hiệu và phân loại"
WebUI.click(findTestObject('Page_HyperS/Brand_Type/dropdown_option_thuong_hieu_va_phan_loai'))

WebUI.delay(2)


// =============== 4. NHẬP FORM THƯƠNG HIỆU ===============

// --- 4.1 Mã thương hiệu ---
TestObject inputBrandCode = new TestObject('inputBrandCode')
inputBrandCode.addProperty(
        'xpath',
        ConditionType.EQUALS,
        "//label[contains(normalize-space(),'Mã thương hiệu')]/following::input[@class='k-input-inner' and @placeholder='Nhập mã thương hiệu...'][1]"
)
WebUI.setText(inputBrandCode, brandCode)

// --- 4.2 Tên thương hiệu ---
TestObject inputBrandName = new TestObject('inputBrandName')
inputBrandName.addProperty(
        'xpath',
        ConditionType.EQUALS,
        "//label[contains(normalize-space(),'Tên thương hiệu')]/following::input[@class='k-input-inner' and @placeholder='Nhập tên thương hiệu...'][1]"
)
WebUI.setText(inputBrandName, brandName)

// --- 4.3 Upload ảnh thương hiệu ---
TestObject inputFile = new TestObject('inputBrandImage')
inputFile.addProperty(
        'xpath',
        ConditionType.EQUALS,
        "//kendo-fileselect//input[@type='file' and contains(@id,'k-fileselect-input')]"
)

// upload file
WebUI.uploadFile(inputFile, imagePath)
WebUI.delay(1)


// =============== 5. CLICK "THÊM MỚI THƯƠNG HIỆU" ===============
TestObject btnAddBrand = new TestObject('btnAddBrand')
btnAddBrand.addProperty(
        'xpath',
        ConditionType.EQUALS,
        "//span[normalize-space(text())='Thêm mới thương hiệu']"
)

WebUI.click(btnAddBrand)


// =============== 6. VERIFY TOAST THÀNH CÔNG ===============
String expectedMsg = 'Thêm mới thương hiệu thành công'   // chỉnh lại đúng message thực tế nếu khác

TestObject toastSuccess = new TestObject('toastSuccess')
toastSuccess.addProperty(
        'xpath',
        ConditionType.EQUALS,
        "//div[contains(@class,'k-notification-content') and contains(normalize-space(),'${expectedMsg}')]"
)

// chờ toast hiện
WebUI.waitForElementVisible(toastSuccess, 10)

// lấy text & verify
String actualMsg = WebUI.getText(toastSuccess).trim()
WebUI.comment("Toast message: " + actualMsg)

// dùng regex chứa expectedMsg cho chắc
WebUI.verifyMatch(actualMsg, ".*${expectedMsg}.*", true)

WebUI.comment("✅ Thêm mới thương hiệu '${brandCode}' - '${brandName}' hiển thị toast thành công.")

// =============== 7. CLOSE ===============
WebUI.closeBrowser()
```
### ✅ Testcase Brand_Type_02: Thêm Thương hiệu có tên trùng lặp
**Mô tả:**  
Thêm Thương hiệu có tên trùng lặp

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration

// =============== 1. DATA TEST ===============
String existedBrandCode = 'FIFA1'          // MÃ ĐÃ CÓ SẴN TRÊN HỆ THỐNG
String brandName        = 'Brand Duplicate ' + System.currentTimeMillis()

WebUI.comment('=== DATA TEST BRAND DUPLICATE ===')
WebUI.comment("Code = ${existedBrandCode}")
WebUI.comment("Name = ${brandName}")

// ảnh dummy – chỉnh lại path nếu khác
String imagePath = RunConfiguration.getProjectDir() + '/Data Files/CNTT.png'


// =============== 2. LOGIN ===============
WebUI.callTestCase(findTestCase('Login_Success'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(2)

// =============== 3. ĐI TỚI MÀN QUẢN LÝ THƯƠNG HIỆU ===============
// Menu "QUẢN LÝ SẢN PHẨM"
WebUI.click(findTestObject('Page_HyperS/Brand_Type/dropdown_quan_ly_san_pham'))

// Sub menu "Thương hiệu và phân loại"
WebUI.click(findTestObject('Page_HyperS/Brand_Type/dropdown_option_thuong_hieu_va_phan_loai'))

WebUI.delay(2)


// =============== 4. NHẬP FORM THƯƠNG HIỆU ===============

// 4.1 Mã thương hiệu (trùng lặp)
TestObject inputBrandCode = new TestObject('inputBrandCode')
inputBrandCode.addProperty(
        'xpath',
        ConditionType.EQUALS,
        "//label[contains(normalize-space(),'Mã thương hiệu')]/following::input[@class='k-input-inner' and @placeholder='Nhập mã thương hiệu...'][1]"
)
WebUI.setText(inputBrandCode, existedBrandCode)

// 4.2 Tên thương hiệu
TestObject inputBrandName = new TestObject('inputBrandName')
inputBrandName.addProperty(
        'xpath',
        ConditionType.EQUALS,
        "//label[contains(normalize-space(),'Tên thương hiệu')]/following::input[@class='k-input-inner' and @placeholder='Nhập tên thương hiệu...'][1]"
)
WebUI.setText(inputBrandName, brandName)

// 4.3 Upload logo thương hiệu
TestObject inputFile = new TestObject('inputBrandImage')
inputFile.addProperty(
        'xpath',
        ConditionType.EQUALS,
        "//kendo-fileselect//input[@type='file' and contains(@id,'k-fileselect-input')]"
)

WebUI.uploadFile(inputFile, imagePath)
WebUI.delay(1)


// =============== 5. CLICK "THÊM MỚI THƯƠNG HIỆU" ===============
TestObject btnAddBrand = new TestObject('btnAddBrand')
btnAddBrand.addProperty(
        'xpath',
        ConditionType.EQUALS,
        "//span[normalize-space(text())='Thêm mới thương hiệu']"
)

WebUI.click(btnAddBrand)


// =============== 6. VERIFY TOAST LỖI MÃ TRÙNG ===============
String expectedMsg = 'Mã thương hiệu đã bị trùng lặp'   // đúng theo spec

TestObject toastDuplicate = new TestObject('toastDuplicate')
toastDuplicate.addProperty(
        'xpath',
        ConditionType.EQUALS,
        "//div[contains(@class,'k-notification-content') and contains(normalize-space(),'${expectedMsg}')]"
)

// chờ toast lỗi xuất hiện
WebUI.waitForElementVisible(toastDuplicate, 10)

// lấy nội dung message
String actualMsg = WebUI.getText(toastDuplicate).trim()
WebUI.comment("Toast duplicate message: " + actualMsg)

// verify có chứa đúng thông báo
WebUI.verifyMatch(actualMsg, ".*${expectedMsg}.*", true)

WebUI.comment('✅ Validate mã thương hiệu trùng lặp hiển thị đúng thông báo lỗi.')

// =============== 7. CLOSE ===============
WebUI.closeBrowser()
```
### ✅ Testcase Brand_Type_03: Thêm Thương hiệu bị thiếu trường bắt buộc
**Mô tả:**  
Thêm Thương hiệu bị thiếu trường bắt buộc

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// =============== 1. DATA TEST ===============
String brandCode = 'BR_NOIMG_' + System.currentTimeMillis()
String brandName = 'Brand No Image ' + System.currentTimeMillis()

WebUI.comment('=== DATA TEST BRAND MISSING IMAGE ===')
WebUI.comment("Code = ${brandCode}")
WebUI.comment("Name = ${brandName}")

// =============== 2. LOGIN ===============
WebUI.callTestCase(findTestCase('Login_Success'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(2)

// =============== 3. VÀO MÀN QUẢN LÝ THƯƠNG HIỆU ===============
WebUI.click(findTestObject('Page_HyperS/Brand_Type/dropdown_quan_ly_san_pham'))
WebUI.click(findTestObject('Page_HyperS/Brand_Type/dropdown_option_thuong_hieu_va_phan_loai'))
WebUI.delay(2)

// =============== 4. NHẬP FORM (KHÔNG UPLOAD ẢNH) ===============

// 4.1 Mã thương hiệu
TestObject inputBrandCode = new TestObject('inputBrandCode')
inputBrandCode.addProperty(
        'xpath',
        ConditionType.EQUALS,
        "//label[contains(normalize-space(),'Mã thương hiệu')]/following::input[@class='k-input-inner' and @placeholder='Nhập mã thương hiệu...'][1]"
)
WebUI.setText(inputBrandCode, brandCode)

// 4.2 Tên thương hiệu
TestObject inputBrandName = new TestObject('inputBrandName')
inputBrandName.addProperty(
        'xpath',
        ConditionType.EQUALS,
        "//label[contains(normalize-space(),'Tên thương hiệu')]/following::input[@class='k-input-inner' and @placeholder='Nhập tên thương hiệu...'][1]"
)
WebUI.setText(inputBrandName, brandName)

// **KHÔNG** thực hiện uploadFile -> để trống ảnh thương hiệu

// =============== 5. CLICK "THÊM MỚI THƯƠNG HIỆU" ===============
TestObject btnAddBrand = new TestObject('btnAddBrand')
btnAddBrand.addProperty(
        'xpath',
        ConditionType.EQUALS,
        "//span[normalize-space(text())='Thêm mới thương hiệu']"
)
WebUI.click(btnAddBrand)

// =============== 6. VERIFY TOAST LỖI THIẾU ẢNH ===============
String expectedMsg = 'Vui lòng chọn ảnh cho thương hiệu'

TestObject toastMissingImage = new TestObject('toastMissingImage')
toastMissingImage.addProperty(
        'xpath',
        ConditionType.EQUALS,
        "//div[contains(@class,'k-notification-content') and contains(normalize-space(),'${expectedMsg}')]"
)

// chờ toast hiện
WebUI.waitForElementVisible(toastMissingImage, 10)

// lấy text và kiểm tra
String actualMsg = WebUI.getText(toastMissingImage).trim()
WebUI.comment("Toast message: " + actualMsg)

WebUI.verifyMatch(actualMsg, ".*${expectedMsg}.*", true)

WebUI.comment('✅ Validate thiếu ảnh thương hiệu hiển thị đúng thông báo lỗi.')

// =============== 7. ĐÓNG TRÌNH DUYỆT ===============
WebUI.closeBrowser()
```
### ✅ Testcase Brand_Type_04: Cập nhật Thương hiệu (Sửa) thành công
**Mô tả:**  
Cập nhật Thương hiệu (Sửa) thành công

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// =============== 1. DATA ===============
String brandCode = 'FIFA01'      // mã thương hiệu có sẵn trong grid
String oldName   = 'FIFA01'       // tên cũ (tùy bạn, có thể đổi cho khớp data thật)
String newName   = 'FIFA1'      // tên mới
String expectedToast = 'Cập nhật thương hiệu thành công'

WebUI.comment('=== UPDATE BRAND TEST ===')
WebUI.comment("Brand code  = ${brandCode}")
WebUI.comment("Old name    = ${oldName}")
WebUI.comment("New name    = ${newName}")

// =============== 2. LOGIN ===============
WebUI.callTestCase(findTestCase('Login_Success'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(2)

// =============== 3. VÀO MÀN QUẢN LÝ THƯƠNG HIỆU ===============
WebUI.click(findTestObject('Page_HyperS/Brand_Type/dropdown_quan_ly_san_pham'))
WebUI.click(findTestObject('Page_HyperS/Brand_Type/dropdown_option_thuong_hieu_va_phan_loai'))
WebUI.delay(2)

// =============== 4. CLICK VÀO DÒNG BRAND CÓ MÃ = brandCode ===============
TestObject rowBrand = new TestObject('rowBrand')
rowBrand.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//kendo-grid//table[contains(@class,'k-grid-table')]" +
    "//tbody//tr[.//div[contains(@class,'column-2') and normalize-space(.)='${brandCode}']]"
)

WebUI.waitForElementVisible(rowBrand, 10)
WebUI.click(rowBrand)
WebUI.delay(1) // chờ form load

// =============== 5. SỬA TÊN THƯƠNG HIỆU TRONG FORM ===============
TestObject inputBrandName = new TestObject('inputBrandName')
inputBrandName.addProperty(
	'xpath',
	ConditionType.EQUALS,
	"//label[contains(normalize-space(),'Tên thương hiệu')]" +
	"/following::input[@class='k-input-inner' and @placeholder='Nhập tên thương hiệu...'][1]"
)

WebUI.waitForElementVisible(inputBrandName, 10)
WebUI.clearText(inputBrandName)
WebUI.setText(inputBrandName, newName)

// =============== 6. CLICK NÚT "CẬP NHẬT" ===============
WebUI.click(findTestObject('Page_HyperS/Brand_Type/btn_cap_nhat'))

// =============== 7. VERIFY TOAST ===============
TestObject toastSuccess = new TestObject('toastSuccess')
toastSuccess.addProperty(
	'xpath',
	ConditionType.EQUALS,
	"//div[contains(@class,'k-notification-content') and " +
	"contains(normalize-space(),'${expectedToast}')]"
)

WebUI.waitForElementVisible(toastSuccess, 10)
String actualMsg = WebUI.getText(toastSuccess).trim()
WebUI.verifyMatch(actualMsg, expectedToast, false)
WebUI.comment("✅ Cập nhật thương hiệu thành công, toast hiển thị đúng: '${expectedToast}'")

WebUI.closeBrowser()
```
### ✅ Testcase Brand_Type_05: Thêm mới Loại sản phẩm hợp lệ
**Mô tả:**  
Thêm mới Loại sản phẩm hợp lệ

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// =============== 1. DATA TEST ===============
String typeCode = 'TYPE_' + System.currentTimeMillis()
String typeName = 'Loại SP Auto ' + System.currentTimeMillis()
String expectedToast = 'Thêm mới loại sản phẩm thành công'

WebUI.comment('=== DATA TEST PHÂN LOẠI SẢN PHẨM ===')
WebUI.comment("Mã loại  = ${typeCode}")
WebUI.comment("Tên loại = ${typeName}")

// =============== 2. LOGIN ===============
WebUI.callTestCase(findTestCase('Login_Success'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(2)

// =============== 3. VÀO MÀN QUẢN LÝ THƯƠNG HIỆU & PHÂN LOẠI ===============
WebUI.click(findTestObject('Page_HyperS/Brand_Type/dropdown_quan_ly_san_pham'))
WebUI.click(findTestObject('Page_HyperS/Brand_Type/dropdown_option_thuong_hieu_va_phan_loai'))
WebUI.delay(2)

// =============== 4. NHẬP FORM PHÂN LOẠI SẢN PHẨM ===============

// --- 4.1 Mã loại sản phẩm ---
TestObject inputTypeCode = new TestObject('inputTypeCode')
inputTypeCode.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//label[contains(normalize-space(),'Mã loại sản phẩm')]" +
    "/following::input[@class='k-input-inner' and @placeholder='Nhập mã loại sản phẩm...'][1]"
)
WebUI.setText(inputTypeCode, typeCode)

// --- 4.2 Tên loại sản phẩm ---
TestObject inputTypeName = new TestObject('inputTypeName')
inputTypeName.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//label[contains(normalize-space(),'Tên loại sản phẩm')]" +
    "/following::input[@class='k-input-inner' and @placeholder='Nhập tên loại sản phẩm...'][1]"
)
WebUI.setText(inputTypeName, typeName)

// =============== 5. CLICK "THÊM MỚI LOẠI" ===============
TestObject btnAddType = new TestObject('btnAddType')
btnAddType.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//div[contains(@class,'block-type')]//span[normalize-space(text())='Thêm mới loại']"
)

WebUI.click(btnAddType)

// =============== 6. VERIFY TOAST "Thêm mới loại sản phẩm thành công" ===============
TestObject toastSuccess = new TestObject('toastSuccess')
toastSuccess.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//div[contains(@class,'k-notification-content') and " +
    "contains(normalize-space(),'${expectedToast}')]"
)

// chờ toast xuất hiện
WebUI.waitForElementVisible(toastSuccess, 10)

// lấy text thực tế
String actualMsg = WebUI.getText(toastSuccess).trim()
WebUI.comment("Toast message = " + actualMsg)

// so sánh đúng thông báo
WebUI.verifyMatch(actualMsg, expectedToast, false)

WebUI.comment("✅ Thêm mới loại sản phẩm thành công, toast hiển thị đúng: '${expectedToast}'")

// =============== 7. CLOSE ===============
WebUI.closeBrowser()
```
### ✅ Testcase Brand_Type_06: Thêm Loại sản phẩm thiếu trường bắt buộc
**Mô tả:**  
Thêm Loại sản phẩm thiếu trường bắt buộc

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// =============== 1. DATA TEST ===============
String typeName = 'Loại SP thiếu mã ' + System.currentTimeMillis()
String expectedToast = 'Vui lòng nhập mã loại sản phẩm'   // chỉnh theo message thật trên hệ thống

WebUI.comment('=== THÊM LOẠI SP THIẾU TRƯỜNG BẮT BUỘC ===')
WebUI.comment("Tên loại = ${typeName}")

// =============== 2. LOGIN ===============
WebUI.callTestCase(findTestCase('Login_Success'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(2)

// =============== 3. VÀO MÀN QUẢN LÝ THƯƠNG HIỆU & PHÂN LOẠI ===============
WebUI.click(findTestObject('Page_HyperS/Brand_Type/dropdown_quan_ly_san_pham'))
WebUI.click(findTestObject('Page_HyperS/Brand_Type/dropdown_option_thuong_hieu_va_phan_loai'))
WebUI.delay(2)

// =============== 4. CHỈ NHẬP TÊN LOẠI SẢN PHẨM, KHÔNG NHẬP MÃ ===============

// --- KHÔNG đụng vào "Mã loại sản phẩm" -> để trống ---

// --- 4.1 Tên loại sản phẩm ---
TestObject inputTypeName = new TestObject('inputTypeName')
inputTypeName.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//label[contains(normalize-space(),'Tên loại sản phẩm')]" +
    "/following::input[@class='k-input-inner' and @placeholder='Nhập tên loại sản phẩm...'][1]"
)

WebUI.setText(inputTypeName, typeName)

// =============== 5. CLICK "THÊM MỚI LOẠI" ===============
TestObject btnAddType = new TestObject('btnAddType')
btnAddType.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//div[contains(@class,'block-type')]//span[normalize-space(text())='Thêm mới loại']"
)

WebUI.click(btnAddType)

// =============== 6. VERIFY TOAST LỖI ===============
TestObject toastError = new TestObject('toastError')
toastError.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//div[contains(@class,'k-notification-content') and " +
    "contains(normalize-space(),'${expectedToast}')]"
)

// chờ toast xuất hiện
WebUI.waitForElementVisible(toastError, 10)

// lấy text thực tế
String actualMsg = WebUI.getText(toastError).trim()
WebUI.comment("Toast message = " + actualMsg)

// so sánh đúng thông báo
WebUI.verifyMatch(actualMsg, expectedToast, false)

WebUI.comment("✅ Hiển thị đúng validation khi thiếu Mã loại sản phẩm: '${expectedToast}'")

// =============== 7. CLOSE ===============
WebUI.closeBrowser()
```
### ✅ Testcase Brand_Type_07: Cập nhật Loại sản phẩm thành công
**Mô tả:**  
Cập nhật Loại sản phẩm thành công

```groovy
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// =============== 1. DATA ===============
String typeCode      = 'RUN01'          // Mã loại sản phẩm có sẵn trong grid
String oldTypeName   = 'Running Shoes'  // Tên cũ (tuỳ data thật)
String newTypeName   = 'Running Shoes Updated ' + System.currentTimeMillis()
String expectedToast = 'Cập nhật loại sản phẩm thành công'

WebUI.comment('=== UPDATE LOẠI SẢN PHẨM TEST ===')
WebUI.comment("Code      = ${typeCode}")
WebUI.comment("Old name  = ${oldTypeName}")
WebUI.comment("New name  = ${newTypeName}")

// =============== 2. LOGIN ===============
WebUI.callTestCase(findTestCase('Login_Success'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(2)

// =============== 3. VÀO MÀN QUẢN LÝ THƯƠNG HIỆU & PHÂN LOẠI ===============
WebUI.click(findTestObject('Page_HyperS/Brand_Type/dropdown_quan_ly_san_pham'))
WebUI.click(findTestObject('Page_HyperS/Brand_Type/dropdown_option_thuong_hieu_va_phan_loai'))
WebUI.delay(2)

// =============== 4. CLICK DÒNG LOẠI SẢN PHẨM CÓ MÃ = typeCode ===============
TestObject rowType = new TestObject('rowType')
rowType.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//div[contains(@class,'block-type')]//table[1]" +
    "//tbody//tr[td[1][normalize-space(.)='${typeCode}']]"  // cột 1 = Mã loại sản phẩm
)

WebUI.waitForElementVisible(rowType, 10)
WebUI.click(rowType)
WebUI.delay(1) // chờ form load data lên

// =============== 5. SỬA TÊN LOẠI SẢN PHẨM ===============
TestObject inputTypeName = new TestObject('inputTypeName')
inputTypeName.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//label[contains(normalize-space(),'Tên loại sản phẩm')]" +
    "/following::input[@class='k-input-inner' and @placeholder='Nhập tên loại sản phẩm...'][1]"
)

WebUI.waitForElementVisible(inputTypeName, 10)

// clear + nhập tên mới
WebUI.clearText(inputTypeName)
WebUI.setText(inputTypeName, newTypeName)

// =============== 6. CLICK NÚT "CẬP NHẬT" ===============
TestObject btnUpdateType = new TestObject('btnUpdateType')
btnUpdateType.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//div[contains(@class,'block-type')]//span[normalize-space(text())='Cập nhật']"
)

WebUI.waitForElementClickable(btnUpdateType, 10)
WebUI.click(btnUpdateType)

// =============== 7. VERIFY TOAST "Cập nhật loại sản phẩm thành công" ===============
TestObject toastSuccess = new TestObject('toastSuccessType')
toastSuccess.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//div[contains(@class,'k-notification-content') and " +
    "contains(normalize-space(),'${expectedToast}')]"
)

WebUI.waitForElementVisible(toastSuccess, 10)

// lấy text thực tế
String actualMsg = WebUI.getText(toastSuccess).trim()
WebUI.comment("Toast message = " + actualMsg)

// so sánh đúng thông báo
WebUI.verifyMatch(actualMsg, expectedToast, false)

WebUI.comment("✅ Cập nhật loại sản phẩm thành công, toast hiển thị đúng: '${expectedToast}'")

// =============== 8. CLOSE ===============
WebUI.closeBrowser()
```


